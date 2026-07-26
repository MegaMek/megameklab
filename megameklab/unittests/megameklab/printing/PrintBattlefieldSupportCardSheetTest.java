/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import megamek.common.battlefieldSupport.BFSAssetType;
import megamek.common.battlefieldSupport.BFSDamage;
import megamek.common.battlefieldSupport.BFSRange;
import megamek.common.battlefieldSupport.BFSSpecial;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.cardDrawer.BattlefieldSupportCard;
import megamek.common.units.EntityMovementMode;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.CConfig;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Verifies {@link PrintBattlefieldSupportCardSheet} tiles BFS asset cards onto a page, paginates when there are more
 * cards than fit, and produces a valid SVG document. Also rasterizes a full page to {@code build/bfs-cards-sheet/} for
 * visual inspection of the tiling.
 */
@ExtendWith(value = InitializeTypes.class)
class PrintBattlefieldSupportCardSheetTest {

    private static BattlefieldSupportAsset sampleAsset(String title) {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis(title);
        asset.setCardTitle(title);
        asset.setCardSubtitle("Hover Transport");
        asset.setAssetType(BFSAssetType.VEHICLE);
        asset.setMovementMode(EntityMovementMode.HOVER);
        asset.setMp(8);
        asset.setTmm(3);
        asset.setRange(new BFSRange(3, 6, 9));
        asset.setSkill(6);
        asset.setVeteranSkill(5);
        asset.setDamage(new BFSDamage(5, 4));
        asset.setODestroyCheck(7);
        asset.setThreshold(5);
        asset.setCost(23);
        asset.setVeteranCost(27);
        asset.setSpecials(List.of(BFSSpecial.parse("APC1"), BFSSpecial.parse("IF2")));
        return asset;
    }

    private static List<BattlefieldSupportAsset> assets(int count) {
        List<BattlefieldSupportAsset> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(sampleAsset("Card " + (i + 1)));
        }
        return list;
    }

    private static PageFormat letterPortrait() {
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        Paper paper = new Paper();
        paper.setSize(612, 792);
        paper.setImageableArea(0, 0, 612, 792);
        pf.setPaper(paper);
        return pf;
    }

    @Test
    void singlePageHoldsEightCardsOnLetter() {
        RecordSheetOptions options = new RecordSheetOptions();
        var sheet = new PrintBattlefieldSupportCardSheet(assets(8), 0, options);
        assertEquals(1, sheet.getPageCount());
    }

    @Test
    void paginatesWhenMoreThanOnePageOfCards() {
        RecordSheetOptions options = new RecordSheetOptions();
        // 9 cards on Letter (8/page) needs two pages.
        var sheet = new PrintBattlefieldSupportCardSheet(assets(9), 0, options);
        assertEquals(2, sheet.getPageCount());
    }

    @Test
    void rendersCardsWhenSheetHasNonZeroFirstPage() throws Exception {
        // In a mixed print queue the card sheet follows other record sheets, so its first page index is > 0.
        // createDocument takes an absolute (book-wide) page index and subtracts the first page internally; the caller
        // (e.g. the preview) must pass firstPage + local page. Passing a sheet-relative index used to drop the card
        // page entirely (negative internal index -> nothing rendered). Guard that here.
        RecordSheetOptions options = new RecordSheetOptions();
        int firstPage = 5;
        var sheet = new PrintBattlefieldSupportCardSheet(assets(8), firstPage, options);
        assertEquals(1, sheet.getPageCount());

        assertTrue(sheet.createDocument(firstPage, letterPortrait(), true, true),
              "Document should be created for a sheet whose first page is non-zero");
        String svg = documentToString(sheet);
        assertTrue(svg.contains("CARD 8"), "all eight cards should render even when the sheet's first page is > 0");
    }

    @Test
    void createsDocumentAndRendersPageToPng() throws Exception {
        RecordSheetOptions options = new RecordSheetOptions();
        var sheet = new PrintBattlefieldSupportCardSheet(assets(8), 0, options);
        PageFormat pf = letterPortrait();

        assertTrue(sheet.createDocument(0, pf, true, true), "Document should be created for the first page");

        // Serialize the built document and confirm it holds real <text> elements (so downstream tools like mekbay can
        // read/manipulate the card text) as well as vector line art.
        String svg = documentToString(sheet);
        assertTrue(svg.contains("<text"), "sheet SVG should contain real <text> elements for the card text");
        assertTrue(svg.contains("<path") || svg.contains("<polygon"), "sheet SVG should contain vector card geometry");
        // Card outlines (border, CHECK box) are stroked black; Batik drops stroke="black" as a default, which we
        // restore so the borders don't render invisibly. Guard against that regression.
        assertTrue(svg.contains("stroke=\"black\""), "stroked card outlines must carry an explicit black stroke");

        File outDir = new File("build/bfs-cards-sheet");
        assertTrue(outDir.exists() || outDir.mkdirs());
        Files.writeString(new File(outDir, "letter_8up.svg").toPath(), svg);

        PNGTranscoder png = new PNGTranscoder();
        png.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 850f);
        ByteArrayOutputStream pngBytes = new ByteArrayOutputStream();
        png.transcode(new TranscoderInput(new StringReader(svg)), new TranscoderOutput(pngBytes));
        Files.write(new File(outDir, "letter_8up.png").toPath(), pngBytes.toByteArray());
        assertTrue(pngBytes.size() > 0);
    }

    @Test
    void rendersFullColorPageToPng() throws Exception {
        // Full-color mode: the cost badge, border and labels use the record-sheet gold. Verify the gold accents
        // survive the print pipeline (SVGGraphics2D -> PNG) and that the gold border stroke is not overwritten black.
        RecordSheetOptions options = new RecordSheetOptions();
        options.setColor(RecordSheetOptions.ColorMode.ALL);
        var sheet = new PrintBattlefieldSupportCardSheet(assets(8), 0, options);

        assertTrue(sheet.createDocument(0, letterPortrait(), true, true));
        String svg = documentToString(sheet);
        // The record-sheet gold (#E0AD2A = rgb(224,173,42)) must be present for the accents.
        assertTrue(svg.contains("224,173,42") || svg.contains("E0AD2A"),
              "full-color sheet should contain the gold accent color");

        File outDir = new File("build/bfs-cards-sheet");
        assertTrue(outDir.exists() || outDir.mkdirs());
        Files.writeString(new File(outDir, "letter_8up_color.svg").toPath(), svg);

        PNGTranscoder png = new PNGTranscoder();
        png.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 850f);
        ByteArrayOutputStream pngBytes = new ByteArrayOutputStream();
        png.transcode(new TranscoderInput(new StringReader(svg)), new TranscoderOutput(pngBytes));
        Files.write(new File(outDir, "letter_8up_color.png").toPath(), pngBytes.toByteArray());
        assertTrue(pngBytes.size() > 0);
    }

    @Test
    void usesConfiguredRecordSheetFont() throws Exception {
        String previousFont = CConfig.getParam(CConfig.RS_FONT, PrintRecordSheet.DEFAULT_TYPEFACE);
        try {
            CConfig.setParam(CConfig.RS_FONT, Font.MONOSPACED);
            var sheet = new PrintBattlefieldSupportCardSheet(assets(1), 0, new RecordSheetOptions());

            assertTrue(sheet.createDocument(0, letterPortrait(), true, true));
            String svg = documentToString(sheet);
            File outDir = new File("build/bfs-cards-sheet");
            assertTrue(outDir.exists() || outDir.mkdirs());
            Files.writeString(new File(outDir, "configured_font.svg").toPath(), svg);

            assertTrue(svg.contains("font-family=\"monospace\""),
                  "BFS card SVG should use the configured record-sheet font");
            assertTrue(svg.contains("letter-spacing=\"" + BattlefieldSupportCard.STAT_LABEL_LETTER_SPACING_PX + "px\""),
                  "card-specific SVG styles must preserve the configured font");
        } finally {
            CConfig.setParam(CConfig.RS_FONT, previousFont);
        }
    }

    private static String documentToString(PrintBattlefieldSupportCardSheet sheet) throws Exception {
        java.io.StringWriter writer = new java.io.StringWriter();
        org.apache.batik.dom.util.DOMUtilities.writeDocument(sheet.getSVGDocument(), writer);
        return writer.toString();
    }
}
