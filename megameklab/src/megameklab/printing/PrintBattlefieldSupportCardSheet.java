/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.cardDrawer.BattlefieldSupportCard;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A record sheet that tiles Battlefield Support (BFS) Asset cards onto a page for printing, so the sheet can be cut
 * apart into individual playing-card-sized cards. Unlike other record sheets there is no decorated template; the page
 * is a plain grid of cards generated in code.
 *
 * <p>Each card is drawn once by {@link BattlefieldSupportCard} onto a Batik {@link SVGGraphics2D} and placed into a
 * computed grid slot. Batik emits card labels as SVG {@code <text>} elements and line art as vector geometry. The grid
 * dimensions are derived from the selected paper size, and the sheet paginates automatically when there are more cards
 * than fit on one page.</p>
 */
public class PrintBattlefieldSupportCardSheet extends PrintRecordSheet {

    /** A standard playing card is 3.5in wide x 2.5in tall (landscape), matching the card renderer's 1.4:1 aspect. */
    private static final double CARD_WIDTH_POINTS = 3.5 * 72;
    private static final double CARD_HEIGHT_POINTS = 2.5 * 72;
    /** Margin left around the card grid so cards clear the printable-area edges. */
    private static final double MARGIN_POINTS = 0.25 * 72;

    private final List<BattlefieldSupportAsset> assets;

    /**
     * @param assets    the Battlefield Support Assets to tile onto the sheet (may span multiple pages)
     * @param startPage the index of the first page of this sheet in the overall print job
     * @param options   the record sheet options (paper size drives the tiling)
     */
    public PrintBattlefieldSupportCardSheet(List<BattlefieldSupportAsset> assets, int startPage,
          RecordSheetOptions options) {
        super(startPage, options);
        this.assets = new ArrayList<>(assets);
    }

    /** The grid layout (columns, rows and centered origin) for the current paper size, computed in page points. */
    private record CardGrid(int columns, int rows, double originX, double originY) {
        int perPage() {
            return columns * rows;
        }
    }

    private CardGrid cardGrid() {
        double paperWidth = options.getPaperSize().pxWidth;
        double paperHeight = options.getPaperSize().pxHeight;
        int columns = Math.max(1, (int) ((paperWidth - 2 * MARGIN_POINTS) / CARD_WIDTH_POINTS));
        int rows = Math.max(1, (int) ((paperHeight - 2 * MARGIN_POINTS) / CARD_HEIGHT_POINTS));
        double originX = (paperWidth - columns * CARD_WIDTH_POINTS) / 2.0;
        double originY = (paperHeight - rows * CARD_HEIGHT_POINTS) / 2.0;
        return new CardGrid(columns, rows, originX, originY);
    }

    @Override
    public int getPageCount() {
        int perPage = cardGrid().perPage();
        return Math.max(1, (assets.size() + perPage - 1) / perPage);
    }

    /** Maps the record-sheet color option to the card's color mode (the card colors its BATTLETECH logo accordingly). */
    private BattlefieldSupportCard.ColorMode cardColorMode() {
        return switch (options.useColor()) {
            case ALL -> BattlefieldSupportCard.ColorMode.ALL;
            case LOGO_ONLY -> BattlefieldSupportCard.ColorMode.LOGO_ONLY;
            case NONE -> BattlefieldSupportCard.ColorMode.NONE;
        };
    }

    @Override
    public List<String> getBookmarkNames() {
        List<String> names = new ArrayList<>();
        for (BattlefieldSupportAsset asset : assets) {
            names.add(asset.getShortNameRaw());
        }
        return names;
    }

    @Override
    public List<String> getBookmarkNames(int pageIndex) {
        CardGrid grid = cardGrid();
        int firstCard = pageIndex * grid.perPage();
        if ((firstCard < 0) || (firstCard >= assets.size())) {
            return List.of();
        }
        int lastCard = Math.min(firstCard + grid.perPage(), assets.size());
        return assets.subList(firstCard, lastCard).stream().map(BattlefieldSupportAsset::getShortNameRaw).toList();
    }

    @Override
    protected @Nullable Document loadTemplate(int pageIndex, PageFormat pageFormat,
          boolean useUnitTestTemplateDirectory) {
        // No file template: build a blank page-sized SVG document to hold the tiled cards.
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        Document doc = domImpl.createDocument(svgNS, SVGConstants.SVG_SVG_TAG, null);
        Element svgRoot = doc.getDocumentElement();
        svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pageFormat.getWidth()));
        svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pageFormat.getHeight()));
        return doc;
    }

    @Override
    protected void processImage(int pageIndex, PageFormat pageFormat) {
        CardGrid grid = cardGrid();
        int perPage = grid.perPage();
        int firstCard = pageIndex * perPage;
        int lastCard = Math.min(firstCard + perPage, assets.size());
        double scale = CARD_WIDTH_POINTS / BattlefieldSupportCard.WIDTH;

        // Share one generator context so image ids stay unique across every card drawn on the page.
        SVGGeneratorContext generatorContext = SVGGeneratorContext.createDefault(getSVGDocument());

        for (int i = firstCard; i < lastCard; i++) {
            int slot = i - firstCard;
            int column = slot % grid.columns();
            int row = slot / grid.columns();
            double x = grid.originX() + column * CARD_WIDTH_POINTS;
            double y = grid.originY() + row * CARD_HEIGHT_POINTS;

            SVGGraphics2D cardGraphics = new SVGGraphics2D(generatorContext, false);
            BattlefieldSupportCard card = new BattlefieldSupportCard(assets.get(i));
            card.setColorMode(cardColorMode());
            card.setDamageColor(options.getDamageColorAwt());
            card.setFont(getNormalFont(14f));
            card.drawCard(cardGraphics);
            Element cardGroup = cardGraphics.getTopLevelGroup(true);
            BattlefieldSupportCard.applySvgStyles(cardGroup);
            applyDefaultStrokeColor(cardGroup);

            Element slotGroup = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            slotGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                  String.format("%s(%f 0 0 %f %f %f)", SVGConstants.SVG_MATRIX_VALUE, scale, scale, x, y));
            slotGroup.appendChild(cardGroup);
            getSVGDocument().getDocumentElement().appendChild(slotGroup);
        }
    }

    /**
     * Works around a Batik {@link SVGGraphics2D} quirk: a shape stroked in black is emitted with its stroke
     * <em>style</em> (width/cap) but no {@code stroke} color, because Batik treats black as its default paint and
     * omits it. SVG's initial {@code stroke} value is {@code none}, however, so those outlines render invisibly. The
     * BFS card is monochrome line art, so every unfilled shape ({@code fill="none"}: the card border and the CHECK
     * box) is a black outline; give any such element that lacks a stroke color an explicit black stroke. Elements that
     * already have a stroke - directly or inherited from an ancestor {@code <g>} (e.g. the gold border in full-color
     * mode, which Batik emits on a wrapping group) - are left untouched.
     */
    private void applyDefaultStrokeColor(Node node) {
        applyDefaultStrokeColor(node, false);
    }

    private void applyDefaultStrokeColor(Node node, boolean ancestorHasStroke) {
        boolean strokeInScope = ancestorHasStroke;
        if (node instanceof Element element) {
            boolean ownStroke = !element.getAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE).isEmpty();
            strokeInScope = ancestorHasStroke || ownStroke;
            if (!strokeInScope
                  && SVGConstants.SVG_NONE_VALUE.equals(element.getAttribute(SVGConstants.SVG_FILL_ATTRIBUTE))) {
                element.setAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE, "black");
                strokeInScope = true;
            }
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            applyDefaultStrokeColor(children.item(i), strokeInScope);
        }
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        // Not used: this sheet builds its document in code (see loadTemplate).
        return "";
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used: the sheet is a plain grid of cards with no title.
        return "";
    }
}
