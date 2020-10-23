/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.com.printing;

import megamek.common.*;
import megameklab.com.util.ImageHelper;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.print.PageFormat;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lays out a record sheet for infantry, BA, or protomechs
 */
public class PrintSmallUnitSheet extends PrintRecordSheet {

    private final List<Entity> entities;

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables.
     *
     * @param entities   The units to print
     * @param startPage  The index of this page in the print job
     * @param options    Options for printing
     */
    public PrintSmallUnitSheet(Collection<? extends Entity> entities, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.entities = new ArrayList<>(entities);
    }

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables, with default
     * options
     *
     * @param entities   The units to print
     * @param startPage  The index of this page in the print job
     */
    public PrintSmallUnitSheet(Collection<? extends Entity> entities, int startPage) {
        this(entities, startPage, new RecordSheetOptions());
    }

    @Override
    public List<String> getBookmarkNames() {
        return entities.stream().map(Entity::getShortNameRaw).distinct().collect(Collectors.toList());
    }

    @Override
    protected void processImage(int startPage, PageFormat pageFormat) {
        final Element element = getSVGDocument().getElementById(COPYRIGHT);
        if (element != null) {
            element.setTextContent(String.format(element.getTextContent(), LocalDate.now().getYear()));
        }
        int count = 0;
        for (Entity entity : entities) {
            Element g = getSVGDocument().getElementById("unit_" + count);
            if (g != null) {
                PrintEntity sheet = getBlockFor(entity, count);
                sheet.createDocument(startPage, pageFormat);
                g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
                getSVGDocument().getDocumentElement().appendChild(g);
            }
            count++;
        }
        drawFluffImage();
    }

    private PrintEntity getBlockFor(Entity entity, int index) {
        if (entity instanceof BattleArmor) {
            return new PrintBattleArmor((BattleArmor) entity, index, getFirstPage(), options);
        } else if (entity instanceof Infantry) {
            return new PrintInfantry((Infantry) entity, getFirstPage(), options);
        } else if (entity instanceof Protomech) {
            return new PrintProtomech((Protomech) entity, getFirstPage(), index, options);
        }
        throw new IllegalArgumentException("Cannot create block for "
                + UnitType.getTypeDisplayableName(entity.getUnitType()));
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (entities.get(0) instanceof BattleArmor) {
            return "battle_armor_default.svg";
        } else if (entities.get(0) instanceof Infantry) {
            if (entities.size() < 4) {
                return "conventional_infantry_tables.svg";
            } else {
                return "conventional_infantry_default.svg";
            }
        } else if (entities.get(0) instanceof Protomech) {
            return "protomech_default.svg";
        }
        return "";
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used by composite sheet
        return "";
    }

    private void drawFluffImage() {
        if (entities.size() > 1) {
            for (int i = 1; i < entities.size(); i++) {
                if (!entities.get(i).getChassis().equals(entities.get(0).getChassis())) {
                    return;
                }
            }
        }
        File f = null;
        if (entities.get(0) instanceof BattleArmor) {
            f = ImageHelper.getFluffFile(entities.get(0), ImageHelper.imageBattleArmor);
        } else if (entities.get(0) instanceof Infantry) {
            f = ImageHelper.getFluffFile(entities.get(0), ImageHelper.imageInfantry);
        } else if (entities.get(0) instanceof Protomech) {
            f = ImageHelper.getFluffFile(entities.get(0), ImageHelper.imageProto);
        }
        if (f != null) {
            Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
            if (rect instanceof SVGRectElement) {
                embedImage(f, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
                hideElement(DEFAULT_FLUFF_IMAGE, true);
            }
        }
    }
}
