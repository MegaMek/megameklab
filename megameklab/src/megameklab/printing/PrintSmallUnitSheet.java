/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import megamek.client.ui.util.FluffImageHelper;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.units.Entity;
import megamek.common.equipment.HandheldWeapon;
import megamek.common.units.Infantry;
import megamek.common.units.ProtoMek;
import megamek.common.units.UnitType;
import megameklab.printing.reference.AntiMekAttackTable;
import megameklab.printing.reference.ClusterHitsTable;
import megameklab.printing.reference.GroundMovementRecord;
import megameklab.printing.reference.GroundToHitMods;
import megameklab.printing.reference.MovementCost;
import megameklab.printing.reference.ProtoMekSpecialHitLocation;
import megameklab.printing.reference.ReferenceTable;
import megameklab.printing.reference.ReferenceTableBase;
import megameklab.printing.reference.SwarmAttackHitLocation;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Lays out a record sheet for infantry, BA, or protoMeks
 */
public class PrintSmallUnitSheet extends PrintRecordSheet {

    private final List<Entity> entities;

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables.
     *
     * @param entities  The units to print
     * @param startPage The index of this page in the print job
     * @param options   Options for printing
     */
    public PrintSmallUnitSheet(Collection<? extends Entity> entities, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.entities = new ArrayList<>(entities);
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
        int clusterTableBlocksSize = 1;
        int index = 0;

        boolean startsWithLargeBlock = false;

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            Element g = getSVGDocument().getElementById("unit_" + index);
            if (g != null) {
                PrintEntity sheet = getBlockFor(entity, count);
                if (sheet instanceof PrintHandheldWeapon hhwSheet) {
                    if (i == 0 && hhwSheet.isLargeLayout()) {
                        startsWithLargeBlock = true;
                    }
                    clusterTableBlocksSize = 2;
                    index += hhwSheet.isLargeLayout() ? 2 : 1;
                } else {
                    index += 1;
                }
                if (sheet.createDocument(startPage, pageFormat, false)) {
                    g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
                }
            }
            count++;
        }
        drawFluffImage();
        if (includeReferenceCharts()) {
            addReferenceCharts(pageFormat);
        } else if (options.showCondensedReferenceCharts()
              && !fillsSheet(entities, options, clusterTableBlocksSize - 1)) {
            addClusterChart(startsWithLargeBlock ? clusterTableBlocksSize / 2 : clusterTableBlocksSize, index);
        }
    }

    private PrintEntity getBlockFor(Entity entity, int index) {
        if (entity instanceof BattleArmor) {
            return new PrintBattleArmor((BattleArmor) entity, index, getFirstPage(), options);
        } else if (entity instanceof Infantry) {
            return new PrintInfantry((Infantry) entity, getFirstPage(), options);
        } else if (entity instanceof ProtoMek) {
            return new PrintProtoMek((ProtoMek) entity, getFirstPage(), index, options);
        } else if (entity instanceof HandheldWeapon) {
            return new PrintHandheldWeapon((HandheldWeapon) entity, getFirstPage(), options);
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
        } else if (entities.get(0) instanceof HandheldWeapon) {
            return "handheld_weapon_default.svg";
        } else if (entities.get(0) instanceof ProtoMek) {
            return "protomek_default.svg";
        }
        return "";
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used by composite sheet
        return "";
    }

    private void drawFluffImage() {
        Entity unit = entities.get(0);
        if (!unit.isProtoMek() && !unit.isInfantry() && !unit.isHandheldWeapon()) {
            return;
        }
        if (entities.size() > 1) {
            for (int i = 1; i < entities.size(); i++) {
                if (!entities.get(i).getChassis().equals(entities.get(0).getChassis())) {
                    return;
                }
            }
        }

        Image fluffImage = FluffImageHelper.getRecordSheetFluffImage(unit);
        if (fluffImage != null) {
            Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
            if (rect instanceof SVGRectElement) {
                embedImage(fluffImage, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true,
                      FLUFF_IMAGE);
                hideElement(DEFAULT_FLUFF_IMAGE, true);
            }
        }
    }

    @Override
    protected boolean includeReferenceCharts() {
        return options.showReferenceCharts();
    }

    @Override
    protected List<ReferenceTable> getRightSideReferenceTables() {
        List<ReferenceTable> list = new ArrayList<>();
        list.add(new GroundToHitMods(this, entities.get(0)));
        list.add(new MovementCost(this, entities));
        if (entities.get(0) instanceof ProtoMek) {
            list.add(new ProtoMekSpecialHitLocation(this));
        } else if (entities.get(0).isConventionalInfantry()) {
            list.add(new AntiMekAttackTable(this));
            list.add(new SwarmAttackHitLocation(this));
        }
        ClusterHitsTable table = new ClusterHitsTable(this, entities, false);
        if (table.required() && table.columnCount() <= 10) {
            list.add(table);
        }
        return list;
    }

    @Override
    protected void addReferenceCharts(PageFormat pageFormat) {
        super.addReferenceCharts(pageFormat);
        ClusterHitsTable clusterTable = new ClusterHitsTable(this, entities, false);
        if (clusterTable.columnCount() > 10) {
            printBottomTable(clusterTable, pageFormat);
        } else {
            printBottomTable(new GroundMovementRecord(this, false,
                  entities.get(0) instanceof ProtoMek), pageFormat);
        }
    }

    private void printBottomTable(ReferenceTableBase table, PageFormat pageFormat) {
        getSVGDocument().getDocumentElement().appendChild(table.createTable(pageFormat.getImageableX(),
              pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
              pageFormat.getImageableWidth() * TABLE_RATIO, pageFormat.getImageableHeight() * 0.2 - 3.0));
    }

    private void addClusterChart(int blocksSize, int index) {
        Element g = getSVGDocument().getElementById("unit_" + index);
        if (g == null) {
            return;
        }

        var table = new ClusterHitsTable(this, entities, true);
        if (!table.required()) {
            return;
        }

        var uaa = new UserAgentAdapter();
        var loader = new DocumentLoader(uaa);
        var ctx = new BridgeContext(uaa, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        new GVTBuilder().build(ctx, getSVGDocument());

        var dims = SVGLocatableSupport.getBBox(getSVGDocument().getElementById("unit_0"));

        var bindingBox = new Rectangle2D.Double(0.9, 10, dims.getWidth() + 3.6, (dims.getHeight() * blocksSize) - 20);

        g.appendChild(table.createTable(bindingBox));
    }

    public static boolean fillsSheet(List<? extends Entity> entities, RecordSheetOptions options) {
        return fillsSheet(entities, options, 0);
    }

    /**
     * Determines if the supplied list of units fills the sheet or if there's room for more
     *
     * @param entities The list of entities to place on the sheet
     * @param options  The record sheet options, as reference tables can reduce available space
     *
     * @return {@code true} if no more entities can be printed on a single sheet
     */
    public static boolean fillsSheet(List<? extends Entity> entities, RecordSheetOptions options,
          int desiredExtraEmptySlots) {
        var numTypes = entities.stream().map(Entity::getClass).distinct().count();
        if (numTypes == 0) {
            return false;
        }
        if (numTypes > 1) {
            throw new IllegalArgumentException("Heterogeneous unit types are not supported");
        }
        if ((entities.get(0) instanceof BattleArmor) || (entities.get(0) instanceof ProtoMek)) {
            return entities.size() > (4 - desiredExtraEmptySlots);
        }
        if (entities.get(0) instanceof Infantry) {
            return entities.size() > ((options.showReferenceCharts() ? 2 : 3) - desiredExtraEmptySlots);
        }
        if (entities.get(0) instanceof HandheldWeapon) {
            int fillSize = 0;
            for (Entity entity : entities) {
                final PrintHandheldWeapon printHandheldWeapon = new PrintHandheldWeapon((HandheldWeapon) entity, 0,
                      null);
                fillSize += printHandheldWeapon.isLargeLayout() ? 2 : 1;
            }
            return fillSize > (8 - desiredExtraEmptySlots);
        }
        throw new IllegalArgumentException("Small unit sheet only supports CI, BA, and ProtoMeks");
    }
}
