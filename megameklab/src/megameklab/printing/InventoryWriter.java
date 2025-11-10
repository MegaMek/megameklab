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

import static megameklab.printing.PrintRecordSheet.FILL_BLACK;
import static megameklab.printing.PrintRecordSheet.FILL_WHITE;
import static megameklab.printing.PrintRecordSheet.FONT_SIZE_MEDIUM;
import static megameklab.printing.PrintRecordSheet.FONT_SIZE_VERY_SMALL;
import static megameklab.printing.PrintRecordSheet.svgNS;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.bays.BattleArmorBay;
import megamek.common.bays.Bay;
import megamek.common.bays.InfantryBay;
import megamek.common.bays.ProtoMekBay;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.QuadVee;
import megamek.common.units.SmallCraft;
import megamek.common.units.SupportTank;
import megameklab.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Formats text for the record sheet's "Weapons and Equipment Inventory" section. For most units, an instance is created
 * for the sheet using {@link #InventoryWriter(PrintEntity, SVGRectElement)}, then the text is added to the SVG document
 * using {@link #writeEquipment()}. This adds the appropriate header, for the unit, equipment table, and
 * quirks/features/ammo at the bottom of the space. Large craft can have multiple sections and should invoke the
 * appropriate section writers, using each return value for the vertical placement of the next section. Multipage record
 * sheets can instantiate using {@link #InventoryWriter(PrintEntity)} to process the equipment and find the size of each
 * section, then call {@link #setRegion(SVGRectElement)} for each page before calling the section writers.
 */
public class InventoryWriter {

    // Proportion of the region width to indent subsequent lines of the same equipment entry
    private static final double INDENT = 0.02;
    private static final int DAMAGE_LINE_THROUGH_MARGIN = 8;

    /**
     * The minimum font size to use when scaling inventory text to fit into available space
     */
    private static final float MIN_FONT_SIZE = 4.5f;
    private static final float QUIRKS_FONT_SCALING = 0.9f;
    private static final float FOOTER_TEXT_WIDTH_RATIO = 0.95f;
    /**
     * The amount of space between lines, as a factor of the font height determined by {@link java.awt.FontMetrics}
     */
    public static final float MIN_LINE_SPACING = 0.7f;

    enum Column {
        QUANTITY("#", 0.025),
        NAME("Type", 0.05),
        // NAME ("Type", 0.062),
        NAME_NO_QTY("Type", 0.025),
        BAY("Bay", 0.02),
        LOCATION("Loc", 0.40, 0.49),
        LOCATION_NO_HEAT("Loc", 0.45, 0.54),
        HEAT("Ht", 0.465, 0.57),
        DAMAGE("Dmg", 0.5, 0.5, 0.43),
        MOD("Tn", 0.725, 0.625, 0.65),
        MIN("Min", 0.785, 0.785, 0.72),
        SHORT("Sht", 0.843, 0.843, 0.803),
        MEDIUM("Med", 0.905, 0.905, 0.885),
        LONG("Lng", 0.97),
        SRV("SRV", 0.69),
        MRV("MRV", 0.77),
        LRV("LRV", 0.85),
        ERV("ERV", 0.93);

        final String header;
        final double groundX;
        final double aeroX;
        final double baX;

        Column(String header, double groundX, double aeroX, double baX) {
            this.header = header;
            this.groundX = groundX;
            this.aeroX = aeroX;
            this.baX = baX;
        }

        Column(String header, double groundX, double aeroX) {
            this(header, groundX, aeroX, groundX);
        }

        Column(String header, double x) {
            this(header, x, x, x);
        }

        double xFor(Entity en, boolean offsetForMod) {
            double x;
            if (en.isAero()) {
                x = aeroX;
            } else if (en instanceof BattleArmor) {
                x = baX;
            } else {
                x = groundX;
            }
            return x;
        }

        static Column[] colsFor(Entity en, boolean mergeInventoryAllowed) {
            if (en.isAero()) {
                if (en.tracksHeat() || en.isLargeCraft()) {
                    return new Column[] { QUANTITY, NAME, LOCATION, HEAT, SRV, MRV, LRV, ERV };
                } else {
                    return new Column[] { QUANTITY, NAME, LOCATION_NO_HEAT, SRV, MRV, LRV, ERV };
                }
            } else if (en instanceof BattleArmor) {
                return new Column[] { QUANTITY, NAME, DAMAGE, MIN, SHORT, MEDIUM, LONG };
            } else {
                if (mergeInventoryAllowed) {
                    if (en.tracksHeat()) {
                        return new Column[] { QUANTITY, NAME, LOCATION, HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG };
                    } else {
                        return new Column[] { QUANTITY, NAME, LOCATION_NO_HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG };
                    }
                } else {
                    if (en.tracksHeat()) {
                        return new Column[] { NAME_NO_QTY, LOCATION, HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG };
                    } else {
                        return new Column[] { NAME_NO_QTY, LOCATION_NO_HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG };
                    }
                }
            }
        }

        /**
         * Column types when printing weapon bays
         */
        final static Column[] BAY_COLUMNS = { BAY, LOCATION, HEAT, SRV, MRV, LRV, ERV };
    }

    private final List<InventoryEntry> equipment;
    private final List<WeaponBayInventoryEntry> standardBays;
    private final List<WeaponBayInventoryEntry> capitalBays;
    private final List<String> bayFootnotes;
    private final Map<String, Integer> ammo;
    private final List<Bay> transportBays;
    private final PrintEntity sheet;
    private Element canvas;
    private double viewX;
    private double viewY;
    private double viewWidth;
    private double viewHeight;
    private double indent;
    private final Column[] columnTypes;
    private final double[] colX;
    private final double[] bayColX;
    private final String ammoText;
    private final String fuelText;
    private final String featuresText;
    private final String miscNotesText;
    private final String quirksText;
    private final boolean mergeInventoryAllowed;
    private final RecordSheetOptions.HitModStyle includeHitMod;
    private final RecordSheetOptions.IntrinsicPhysicalAttacksStyle includeIntrinsicPhysicals;

    /**
     * Creates a new instance, determines column positions, and parses equipment.
     *
     * @param sheet The record sheet layout instance
     */
    public InventoryWriter(PrintEntity sheet) {

        this.sheet = sheet;
        boolean isSmallUnit = sheet.getEntity().isBattleArmor() || sheet.getEntity().isProtoMek() || sheet.getEntity()
              .isInfantry();
        // If the unit is a small unit, we always merge equipment when possible
        this.mergeInventoryAllowed = isSmallUnit || sheet.options.mergeIdenticalEquipment();
        this.includeHitMod = sheet.options.includeHitMod();
        this.includeIntrinsicPhysicals = sheet.options.intrinsicPhysicalAttacks();

        var columnTypes = Column.colsFor(sheet.getEntity(), this.mergeInventoryAllowed);
        if (includeHitMod.equals(RecordSheetOptions.HitModStyle.COLUMN)) {
            var newColumns = new Column[columnTypes.length + 1];

            int minIndex = -1;
            for (int i = 0; i < columnTypes.length; i++) {
                if (columnTypes[i] == Column.MIN || columnTypes[i] == Column.SRV) {
                    minIndex = i;
                    break;
                }
            }
            java.lang.System.arraycopy(columnTypes, 0, newColumns, 0, minIndex);
            newColumns[minIndex] = Column.MOD;
            java.lang.System.arraycopy(columnTypes, minIndex, newColumns, minIndex + 1, columnTypes.length - minIndex);
            columnTypes = newColumns;
        }
        this.columnTypes = columnTypes;

        colX = new double[columnTypes.length];
        bayColX = new double[Column.BAY_COLUMNS.length];

        this.equipment = new ArrayList<>();
        this.capitalBays = new ArrayList<>();
        this.standardBays = new ArrayList<>();
        this.bayFootnotes = new ArrayList<>();
        this.ammo = new TreeMap<>();
        this.transportBays = sheet.getEntity().getTransports().stream().filter(t -> t instanceof Bay)
              .map(t -> (Bay) t).filter(b -> !b.isQuarters()).collect(Collectors.toList());
        if (sheet.getEntity().usesWeaponBays()) {
            parseBays();
        } else {
            parseEquipment();
        }
        String str;
        if (!sheet.getEntity().isHandheldWeapon()) {
            str = ammo.entrySet().stream()
                  .map(e -> String.format("(%s) %d", e.getKey(), e.getValue()))
                  .collect(Collectors.joining(", "));
            String ammoPrefix = "Ammo: ";
            if (sheet.getEntity().hasWorkingMisc(MiscType.F_CASE)
                  && ((sheet.getEntity().getEntityType() & Entity.ETYPE_MEK) == 0)) {
                ammoPrefix = "Ammo (CASE): ";
            }
            ammoText = str.isEmpty() ? str : ammoPrefix + str;
        } else {
            ammoText = "";
        }
        fuelText = sheet.formatTacticalFuel();
        str = sheet.formatFeatures();
        featuresText = str.isEmpty() ? str : "Features " + str;
        miscNotesText = sheet.formatMiscNotes();
        str = sheet.formatQuirks();
        quirksText = str.isEmpty() ? str : "Quirks: " + str;
    }

    public InventoryWriter(PrintEntity sheet, SVGRectElement svgRect) {
        this(sheet);
        setRegion(svgRect);
    }

    /**
     * Recalculates column positions for a new page
     *
     * @param svgRect The bounding rectangle of the print region
     */
    public void setRegion(SVGRectElement svgRect) {
        this.canvas = (Element) svgRect.getParentNode();
        this.viewWidth = svgRect.getWidth().getBaseVal().getValue();
        this.viewHeight = svgRect.getHeight().getBaseVal().getValue();
        this.viewX = svgRect.getX().getBaseVal().getValue();
        this.viewY = svgRect.getY().getBaseVal().getValue();
        this.indent = viewWidth * INDENT;
        for (int i = 0; i < columnTypes.length; i++) {
            colX[i] = viewX + viewWidth * columnTypes[i].xFor(sheet.getEntity(),
                  includeHitMod.equals(RecordSheetOptions.HitModStyle.COLUMN));
        }
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            bayColX[i] = viewX + viewWidth * Column.BAY_COLUMNS[i].aeroX;
        }
    }

    private void parseEquipment() {
        WeaponSortOrder sortOrder = sheet.options.getWeaponsOrder();
        List<Mounted<?>> sortedMounted = sheet.getEntity().getEquipment();
        if (sortOrder != WeaponSortOrder.DEFAULT) {
            sortedMounted = new ArrayList<>(sortedMounted); // Copy to sort without affecting the original
            Comparator<WeaponMounted> weaponSortComparator = sortOrder.getWeaponSortComparator(sheet.getEntity());
            sortedMounted.sort((m1, m2) -> {
                boolean w1 = m1 instanceof WeaponMounted;
                boolean w2 = m2 instanceof WeaponMounted;
                if (w1 && w2) {
                    return weaponSortComparator.compare((WeaponMounted) m1, (WeaponMounted) m2);
                } else if (w1) {
                    return -1;
                } else if (w2) {
                    return 1;
                }
                return 0;
            });
        }
        for (Mounted<?> m : sortedMounted) {
            if (m.isWeaponGroup()) {
                continue;
            }
            if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType()
                  != AmmoType.AmmoTypeEnum.COOLANT_POD)) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = m.getType().getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    ammo.merge(shortName.trim(), m.getBaseShotsLeft(), Integer::sum);
                } else if ((sheet.getEntity() instanceof ProtoMek)
                      && (((AmmoType) m.getType()).getAmmoType() == AmmoType.AmmoTypeEnum.IATM)) {
                    // A bit of an ugly hack to get fusillade ammo to show up and identify as fusillade instead of iATM3
                    ammo.merge("Fusillade", m.getBaseShotsLeft(), Integer::sum);
                }
                continue;
            }
            if (UnitUtil.isMineDispenser(m.getType()) || UnitUtil.isRemoteSensorDispenser(m.getType())) {
                ammo.merge(m.getShortName(), m.getBaseShotsLeft(), Integer::sum);
            }
            if ((m.getLocation() == Entity.LOC_NONE)
                  || !PrintUtil.isPrintableEquipment(m.getType(), sheet.getEntity(), sheet.options)) {
                continue;
            }
            if ((sheet.getEntity() instanceof QuadVee)
                  && (m.getType() instanceof MiscType)
                  && m.getType().hasFlag(MiscType.F_TRACKS)) {
                continue;
            }
            /*
             * BattleArmor have their own special mount points.
             * We can't rely on LOC_NONE as a filter because it matches LOC_SQUAD (which is a valid location for BA).
             * The solution is to filter out everything that has critical slots (means can't be for squad only)
             * and is mounted on MOUNT_LOC_NONE
             *
             * The exception is weapons mounted in a DWP, these have MOUNT_LOC_NONE for some reason but should be
             * included as if they were normal weapons.
             */
            if ((sheet.getEntity() instanceof BattleArmor)
                  && (m.getNumCriticalSlots() > 0)
                  && (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)
                  && !(
                        m.getLinkedBy() != null && m.getLinkedBy().getType().hasFlag(MiscTypeFlag.F_DETACHABLE_WEAPON_PACK)
                  )
            ) {
                continue;
            }
            StandardInventoryEntry entry = new StandardInventoryEntry(m);
            // If the unit is a Mek, we check for the merge equipment option.
            // If is not a mek, we always merge if possible.
            if (this.mergeInventoryAllowed) {
                StandardInventoryEntry same =
                      equipment.stream()
                            .filter(e -> e instanceof StandardInventoryEntry)
                            .map(e -> (StandardInventoryEntry) e)
                            .filter(entry::equals)
                            .findFirst()
                            .orElse(null);
                if (null == same) {
                    equipment.add(entry);
                } else {
                    same.incrementQty();
                }
            } else {
                equipment.add(entry);
            }
        }

        // Special entries for mek features which aren't represented by a MiscType.
        if (sheet.getEntity() instanceof Mek mek && mek.hasRiscHeatSinkOverrideKit()) {
            var mounted = new MiscMounted(sheet.getEntity(), new MiscType() {{
                name = "RISC Heat Sink Override Kit";
                shortName = "RISC HS Override Kit";
                internalName = "RISC Heat Sink Override Kit";
            }});
            mounted.setLocation(Mek.LOC_NONE);
            equipment.add(new StandardInventoryEntry(mounted));
        }
        if (sheet.getEntity() instanceof Mek mek && mek.hasFullHeadEject()) {
            var mounted = new MiscMounted(sheet.getEntity(), new MiscType() {{
                name = "Full Head Ejection System";
                shortName = "Full Head Eject System";
                internalName = "Full Head Ejection System";
            }});
            mounted.setLocation(Mek.LOC_NONE);
            equipment.add(new StandardInventoryEntry(mounted));
        }

        if (includeIntrinsicPhysicals.equals(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.EQUIPMENT)
              || includeIntrinsicPhysicals.equals(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.FOOTER)) {
            List<InventoryEntry> physicalEntries = IntrinsicPhysicalInventoryEntry.getEntriesFor(sheet.getEntity());
            if (!sheet.options.extraPhysicals()) {
                physicalEntries.removeIf(entry -> entry instanceof IntrinsicPhysicalInventoryEntry e && e.optional());
            }
            if (!physicalEntries.isEmpty()) {
                if (includeIntrinsicPhysicals.equals(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.EQUIPMENT)) {
                    physicalEntries.add(0, new IntrinsicPhysicalInventoryEntry.HeaderEntry());
                }
                equipment.addAll(physicalEntries);
            }
        }
    }

    private void parseBays() {
        int weaponBayIndex = 0;
        List<WeaponMounted> standardWeapons = new ArrayList<>();
        List<WeaponMounted> capitalWeapons = new ArrayList<>();
        for (WeaponMounted m : sheet.getEntity().getWeaponList()) {
            if (m.getType().isCapital()) {
                capitalWeapons.add(m);
            } else {
                standardWeapons.add(m);
            }
        }
        List<AmmoMounted> ammoMountedList = sheet.getEntity().getAmmo();
        List<WeaponBayText> list = computeWeaponBayTexts(capitalWeapons, ammoMountedList);
        for (WeaponBayText text : list) {
            capitalBays.add(new WeaponBayInventoryEntry((Aero) sheet.getEntity(), ++weaponBayIndex, text, true));
        }
        list = computeWeaponBayTexts(standardWeapons, ammoMountedList);
        boolean artemisIV = false;
        boolean artemisV = false;
        boolean apollo = false;
        for (WeaponBayText text : list) {
            WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry((Aero) sheet.getEntity(), ++weaponBayIndex,
                  text, false);
            standardBays.add(entry);
            artemisIV |= entry.hasArtemisIV();
            artemisV |= entry.hasArtemisV();
            apollo |= entry.hasApollo();
        }
        if (artemisIV) {
            bayFootnotes.add("* w/Artemis IV");
        }
        if (artemisV) {
            bayFootnotes.add(InventoryEntry.DAGGER + " w/Artemis V (-1 to hit)");
        }
        if (apollo) {
            bayFootnotes.add(InventoryEntry.DOUBLE_DAGGER + " w/Apollo (ignore +1 to hit)");
        }
    }

    /**
     * Iterate through a list of weapons and create information about what weapons belong in what bays, how many, the
     * bay damage, and also condense entries when possible.
     *
     * @param weapons A list of weapon bays
     *
     * @return A list of bays condensed by weapon type and symmetric location
     */
    private List<WeaponBayText> computeWeaponBayTexts(List<WeaponMounted> weapons, List<AmmoMounted> ammoMountedList) {
        List<WeaponBayText> weaponBayTexts = new ArrayList<>();
        // Collection info on weapons to print
        for (WeaponMounted bay : weapons) {
            WeaponBayText wbt = new WeaponBayText(bay.getLocation(), bay.isRearMounted());
            for (WeaponMounted weaponMounted : bay.getBayWeapons()) {
                if (!wbt.addBayWeapon(weaponMounted)) {continue;}
                for (AmmoMounted ammo : ammoMountedList) {
                    if (ammo.getLocation() == weaponMounted.getLocation()
                          && weaponMounted.getType().getAmmoType() == ammo.getType().getAmmoType()) {
                        wbt.addBayAmmo(weaponMounted.getType(), ammo);
                    }
                }
            }
            // Combine or add
            boolean combined = false;
            for (WeaponBayText combine : weaponBayTexts) {
                if (combine.canCombine(wbt)) {
                    combine.combine(wbt);
                    combined = true;
                    break;
                }
            }
            if (!combined) {
                weaponBayTexts.add(wbt);
            }
        }
        Collections.sort(weaponBayTexts);
        return weaponBayTexts;
    }

    public double startingY() {
        return viewY + sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
    }

    public int capitalBayLines() {
        return capitalBays.stream().mapToInt(WeaponBayInventoryEntry::nRows).sum();
    }

    /**
     * Calculates the number of additional lines required to print the capital scale weapon bay block due to wrapping
     * long names to another line.
     *
     * @param fontSize The font size used to print
     *
     * @return The number of extra lines required by the table
     */
    public int extraCapitalBayLines(float fontSize) {
        if (capitalBays == null || capitalBays.isEmpty()) {
            return 0;
        }
        return extraLines(capitalBays, bayColX, fontSize, 0);
    }

    /**
     * Calculates the number of additional lines required to print the standard scale weapon bay block due to wrapping
     * long names to another line.
     *
     * @param fontSize The font size used to print
     *
     * @return The number of extra lines required by the table
     */
    public int extraStandardBayLines(float fontSize) {
        if (standardBays == null || standardBays.isEmpty()) {
            return 0;
        }
        return extraLines(standardBays, bayColX, fontSize, 0);
    }

    private int extraLines(List<? extends InventoryEntry> list, double[] colX, float fontSize, int nameIndex) {
        int lines = 0;
        final double nameWidth = colX[nameIndex + 1] - colX[nameIndex] - indent;
        for (InventoryEntry entry : list) {
            // Take in consideration the location field for possible reduction of name width on the first row
            final double nameWidthRow0 = nameWidth - sheet.getTextLength(entry.getLocationField(0), fontSize) * 0.5;
            for (int r = 0; r < entry.nRows(); r++) {
                if (sheet.getTextLength(entry.getNameField(r), fontSize) >= (r == 0 ? nameWidthRow0 : nameWidth)) {
                    lines++;
                }
            }
            if (sheet.showQuirks() && entry.hasQuirks()) {
                lines += (int) Math.ceil(sheet.getItalicTextLength(entry.getQuirksField(), fontSize) / ((viewWidth
                      * 0.96) - (colX[0] + indent)));
            }
        }
        return lines;
    }

    /**
     * @return The size of the transport bay block, not including header
     */
    public int transportBayLines() {
        return transportBays.size();
    }

    public int standardBayLines() {
        return standardBays.stream().mapToInt(WeaponBayInventoryEntry::nRows).sum()
              + bayFootnotes.size();
    }

    public void writeEquipment() {
        double yPosition = startingY();
        if (sheet.getEntity().isAero()) {
            yPosition = printAeroStandardHeader(yPosition);
        } else {
            yPosition = printColumnHeaders(yPosition);
        }
        float[] metrics = scaleText(viewHeight - (yPosition - viewY), this::calcLineCount);
        if (includeIntrinsicPhysicals.equals(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.FOOTER)) {
            List<InventoryEntry> equipmentWithoutPhysicalAttacks = equipment.stream()
                  .filter(entry -> !(entry instanceof IntrinsicPhysicalInventoryEntry))
                  .toList();
            yPosition = printEquipmentTable(canvas, equipmentWithoutPhysicalAttacks, yPosition, metrics[0], metrics[1]);
        } else {
            yPosition = printEquipmentTable(canvas, equipment, yPosition, metrics[0], metrics[1]);
        }
        if ((sheet.getEntity() instanceof SmallCraft || sheet.getEntity() instanceof SupportTank)
              && !transportBays.isEmpty()) {
            yPosition = printBayInfo(metrics[0], metrics[1], yPosition);
        }
        if (sheet.showHeatProfile()) {
            yPosition += metrics[1] / 2; // Add half line for heat profile text spacing
            sheet.addTextElement(canvas, viewX + viewWidth * 0.025,
                  yPosition, sheet.heatProfileText(),
                  FONT_SIZE_MEDIUM, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                  SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK, "heatProfile", null);
        }
        writeFooterBlock(metrics[0], metrics[1]);
    }

    /**
     * Adds the text for the standard scale weapon bays.
     *
     * @param fontSize   The size of font to size for the table text
     * @param lineHeight The amount to increase the y coordinate for each line
     * @param currY      The starting y coordinate
     *
     * @return The y coordinate of the bottom of the table
     */
    public double writeStandardBays(float fontSize, double lineHeight, double currY) {
        currY = printAeroStandardHeader(currY, Column.BAY_COLUMNS, bayColX);
        currY = printEquipmentTable(canvas, standardBays, currY, fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
        for (String note : bayFootnotes) {
            sheet.addTextElement(canvas, bayColX[0], currY, note, fontSize,
                  SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            currY += lineHeight;
        }
        return currY;
    }

    /**
     * Adds the text for the capital scale weapon bays.
     *
     * @param fontSize   The size of font to size for the table text
     * @param lineHeight The amount to increase the y coordinate for each line
     * @param currY      The starting y coordinate
     *
     * @return The y coordinate of the bottom of the table
     */
    public double writeCapitalBays(float fontSize, double lineHeight, double currY) {
        currY = printCapitalHeader(currY);
        return printEquipmentTable(canvas, capitalBays, currY, fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
    }

    /**
     * Rescales text to allow space for a fixed number of lines using the entire region. Used for large craft, which
     * have a number of possible block types and may need a second page.
     *
     * @param calcLines A supplier for the number of lines. Since reducing the font size may allow for fewer lines, the
     *                  supplier gives an opportunity to recalculate after each resizing.
     *
     * @return A tuple of the new font height and line height, in that order
     */
    public float[] scaleText(Function<Float, Integer> calcLines) {
        return scaleText(viewHeight, calcLines);
    }

    static private final float INITIAL_LINE_SPACING = 1.2f; // the initial line spacing factor
    static private final float LINE_SPACING_REDUCTION_STEP = 0.05f; // the amount we reduce the line spacing each attempt
    static private final float FONT_SIZE_REDUCTION_STEP = 0.25f; // the amount we reduce the font each attempt
    // the minimum line spacing during the first "line spacing only" attempts
    // if we can't fit the text, we will roll back, and we will start reducing the
    // font size and line spacing
    static private final float MIN_LINE_SPACING_IN_SPECIAL_ATTEMPTS = 1.0f;
    // the ratio of attempts between font size and line spacing.
    // if this number is > 1, we will do more attempts to reduce line spacing compared to font size
    static private final float RATIO_FONT_SIZE_LINE_SPACING_ATTEMPTS = 2;

    /**
     * If the lines do not fit in the available space, we will need to reduce the font size and possible the amount of
     * space between lines. We first try to reduce the line spacing factor until a certain threshold, and if it fails we
     * start an alternate cycle of attempts between font size and line spacing reduction.
     *
     * @param height    The height of the region the text needs to fit in
     * @param calcLines A supplier for the number of lines. Since reducing the font size may allow for fewer lines, the
     *                  supplier gives an opportunity to recalculate after each resizing.
     *
     * @return A tuple of the new font height and line height, in that order
     */
    public float[] scaleText(double height, Function<Float, Integer> calcLines) {
        float currentFontSize = FONT_SIZE_MEDIUM;
        float currentLineSpacingFactor = INITIAL_LINE_SPACING;
        int lines;
        float actualLineHeight;
        int alternateReductionState = 0; // 0 = prioritize font size, 1 = prioritize line spacing
        boolean attemptForLineSpacingOnly = true;
        while (true) {
            lines = calcLines.apply(currentFontSize);
            float fontMetricsHeight = sheet.getFontHeight(currentFontSize);
            actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;

            if (actualLineHeight * lines < height) {
                // Text fits! break the loop
                break;
            }

            // We try first to reduce the line spacing factor to see if it is enough
            // to fit the text. If it fails, we roll back, and we start an alternate
            // cycle of attempts between font and line spacing.
            if (attemptForLineSpacingOnly) {
                attemptForLineSpacingOnly = false;
                boolean fitFoundInSpecialPhase = false;
                final float originalLineSpacingFactor = currentLineSpacingFactor;
                while (true) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING,
                          currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    if (currentLineSpacingFactor < MIN_LINE_SPACING_IN_SPECIAL_ATTEMPTS) {
                        // Too much reduction, we failed
                        break;
                    }
                    actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;
                    if (actualLineHeight * lines < height) {
                        fitFoundInSpecialPhase = true;
                        break;
                    }
                }
                if (fitFoundInSpecialPhase) {
                    // We found the fit! The main loop will to the final check.
                    continue;
                } else {
                    // Rollback
                    currentLineSpacingFactor = originalLineSpacingFactor;
                    actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;
                }
            }


            boolean reductionMade = false;
            // This is the alternate reduction cycle between font size and line spacing factor
            if (alternateReductionState == 0) { // Prioritize reducing font size
                if (currentFontSize > MIN_FONT_SIZE) {
                    currentFontSize = Math.max(MIN_FONT_SIZE, currentFontSize - FONT_SIZE_REDUCTION_STEP);
                    reductionMade = true;
                } else if (currentLineSpacingFactor > MIN_LINE_SPACING) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING,
                          currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    reductionMade = true;
                }
            } else { // Prioritize reducing line spacing factor
                if (currentLineSpacingFactor > MIN_LINE_SPACING) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING,
                          currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    reductionMade = true;
                } else if (currentFontSize > MIN_FONT_SIZE) {
                    currentFontSize = Math.max(MIN_FONT_SIZE, currentFontSize - FONT_SIZE_REDUCTION_STEP);
                    reductionMade = true;
                }
            }

            alternateReductionState++;
            if (alternateReductionState > RATIO_FONT_SIZE_LINE_SPACING_ATTEMPTS) {
                alternateReductionState = 0;
            }

            if (!reductionMade) {
                // No more reductions possible, break the loop
                break;
            }
        }
        return new float[] { currentFontSize, actualLineHeight };
    }

    /**
     * Displays ammo, fuel, features, and quirks as free-flowing text at the bottom of the inventory box
     *
     * @param fontSize   The size of the font for the text
     * @param lineHeight The height of each line of text
     */
    public void writeFooterBlock(float fontSize, float lineHeight) {
        List<InventoryEntry> equipmentOnlyPhysicalAttacks;
        if (includeIntrinsicPhysicals.equals(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.FOOTER)) {
            equipmentOnlyPhysicalAttacks = equipment.stream()
                  .filter(entry -> (entry instanceof IntrinsicPhysicalInventoryEntry))
                  .toList();
        } else {
            equipmentOnlyPhysicalAttacks = new ArrayList<>(); // No intrinsic physical attacks to display in footer
        }
        if (equipmentOnlyPhysicalAttacks.size()
              + ammo.size()
              + fuelText.length()
              + featuresText.length()
              + miscNotesText.length()
              + quirksText.length() > 0) {
            Element svgGroup = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            canvas.appendChild(svgGroup);
            final double xPosition = (viewX + viewWidth * 0.025);
            final double textWidth = viewWidth * FOOTER_TEXT_WIDTH_RATIO;
            double yPosition = 0;
            if (!equipmentOnlyPhysicalAttacks.isEmpty()) {
                yPosition = printEquipmentTable(svgGroup, equipmentOnlyPhysicalAttacks, yPosition, fontSize,
                      lineHeight);
                yPosition += lineHeight * 0.5; // Add half line for spacing
            }
            if (!ammoText.isEmpty()) {
                Element ammoGroup = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
                ammoGroup.setAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE, "ammoProfile");
                svgGroup.appendChild(ammoGroup);
                yPosition += lineHeight * sheet.addMultilineTextElement(ammoGroup, xPosition, yPosition, textWidth,
                      lineHeight,
                      ammoText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!fuelText.isEmpty()) {
                yPosition += lineHeight * sheet.addMultilineTextElement(svgGroup, xPosition,
                      yPosition, textWidth, lineHeight,
                      fuelText, fontSize,
                      SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!featuresText.isEmpty()) {
                yPosition += lineHeight * sheet.addMultilineTextElement(svgGroup, xPosition,
                      yPosition, textWidth, lineHeight,
                      featuresText, fontSize,
                      SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!miscNotesText.isEmpty()) {
                yPosition += lineHeight * sheet.addMultilineTextElement(svgGroup, xPosition,
                      yPosition, textWidth, lineHeight,
                      miscNotesText, fontSize,
                      SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!quirksText.isEmpty()) {
                yPosition += lineHeight * sheet.addMultilineTextElement(svgGroup,
                      xPosition,
                      yPosition,
                      textWidth,
                      (lineHeight * QUIRKS_FONT_SCALING),
                      quirksText,
                      (fontSize * QUIRKS_FONT_SCALING),
                      SVGConstants.SVG_START_VALUE,
                      SVGConstants.SVG_NORMAL_VALUE,
                      SVGConstants.SVG_ITALIC_VALUE,
                      "unitQuirks");
            }
            final double totalHeight = yPosition > 0 ?
                  (yPosition - lineHeight) :
                  0; //We remove the last line from the total height
            // We position this svg group at the bottom of the inventory box with a margin equal to half of the line height
            svgGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                  String.format("%s(0,%f)", SVGConstants.SVG_TRANSLATE_VALUE,
                        viewY + viewHeight - totalHeight - (lineHeight * 0.5)));
        }
    }

    private double printColumnHeaders(double currY) {
        return printColumnHeaders(currY, columnTypes, colX);
    }

    private double printColumnHeaders(double currY, Column[] columnTypes, double[] colX) {
        for (int i = 0; i < columnTypes.length; i++) {
            String anchor;
            if (Column.NAME.equals(columnTypes[i])
                  || Column.NAME_NO_QTY.equals(columnTypes[i])
                  || Column.BAY.equals(columnTypes[i])
                  || Column.DAMAGE.equals(columnTypes[i])) {
                anchor = SVGConstants.SVG_START_VALUE;
            } else {
                anchor = SVGConstants.SVG_MIDDLE_VALUE;
            }
            sheet.addTextElement(canvas, colX[i], currY, columnTypes[i].header, FONT_SIZE_MEDIUM,
                  anchor, SVGConstants.SVG_BOLD_VALUE);
        }
        return currY + sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.1;
    }

    /**
     * Calculates the total line height at the given font size for units that have a single equipment block and possible
     * footer, e.g. those other than large craft. Because lines breaks are inserted between words, it's possible that
     * the number of lines may be more.
     *
     * @param fontSize The size of the font
     *
     * @return The estimated line count.
     */
    private int calcLineCount(float fontSize) {
        // The width of the name field varies depending on aero/ground or whether there is a heat column and if we
        // are merging inventory or not.
        double baseNameWidth = Double.MAX_VALUE;
        for (int i = 0; i < columnTypes.length; i++) {
            if (Column.NAME.equals(columnTypes[i]) || Column.NAME_NO_QTY.equals(columnTypes[i])) {
                baseNameWidth = colX[i + 1] - colX[i] - viewWidth * INDENT;
                break;
            }
        }
        double damageWidth = Double.MAX_VALUE;
        for (int i = 0; i < columnTypes.length; i++) {
            if (Column.DAMAGE.equals(columnTypes[i])) {
                damageWidth = colX[i + 1] - colX[i] - (fontSize / 2);
                break;
            }
        }
        int lines = 0;
        for (InventoryEntry line : equipment) {
            int rows = line.nRows();
            lines += rows;
            double nameWidth = baseNameWidth;
            if (!(sheet.getEntity() instanceof BattleArmor)) {
                // getTextLength thinks some letters are much wider than other when in reality they pretty much all
                // have the same width. This hack prevents some weapon names from getting split on some lines but not
                // others for no reason apparent to the user.
                nameWidth -= sheet.getTextLength(line.getLocationField(0).replaceAll("[A-Z]", "L"), fontSize) * 0.5;
            }
            if (sheet.getTextLength(line.getNameField(0), fontSize) > nameWidth) {
                lines++;
            } else if (sheet.getTextLength(line.getDamageField(0), fontSize) > damageWidth) {
                lines++;
            }
            if (sheet.showQuirks() && line.hasQuirks()) {
                lines += (int) Math.ceil(sheet.getItalicTextLength(line.getQuirksField(), fontSize) / (viewWidth
                      * 0.96));
            }
        }
        if (transportBayLines() > 0) {
            lines += transportBayLines() + 1; // add extra for header
        }
        lines += footerLines(fontSize);
        if (sheet.showHeatProfile()) {
            lines++;
            lines++;
        }
        return lines;
    }

    public int footerLines(float fontSize) {
        final double textWidth = viewWidth * FOOTER_TEXT_WIDTH_RATIO;
        return (int) Math.ceil(sheet.getTextLength(ammoText, fontSize) / textWidth)
              + (int) Math.ceil(sheet.getTextLength(fuelText, fontSize) / textWidth)
              + (int) Math.ceil(sheet.getTextLength(featuresText, fontSize) / textWidth)
              + (int) Math.ceil(sheet.getTextLength(miscNotesText, fontSize) / textWidth)
              + (int) Math.ceil(sheet.getItalicTextLength(quirksText, fontSize) / textWidth);
    }

    /**
     * Adds a section to the inventory section. An entry that does not fit into the allocated space will wrap to the
     * next line. This is tracked using the repurposed lines local variable. Some entries are already given multiple
     * lines (such as missile launchers with Artemis), which will be handled in the inner loop. We need to compare the
     * two to make sure we don't add extra line feeds. This algorithm works on the assumption that pre-splitting values
     * into multiple rows ensures that they will fit and not need to wrap.
     *
     * @param list       The list of entries for this table
     * @param fontSize   The size of font to use for printing the table
     * @param yPosition  The starting y coordinate relative to the parent element
     * @param lineHeight The amount to add to the y coordinate for each line
     */
    private double printEquipmentTable(Element canvas, List<? extends InventoryEntry> list,
          double yPosition, float fontSize, double lineHeight) {
        return printEquipmentTable(canvas, list, yPosition, fontSize, lineHeight, columnTypes, colX);
    }

    /**
     * Adds a section to the inventory section. An entry that does not fit into the allocated space will wrap to the
     * next line. This is tracked using the repurposed lines local variable. Some entries are already given multiple
     * lines (such as missile launchers with Artemis), which will be handled in the inner loop. We need to compare the
     * two to make sure we don't add extra line feeds. This algorithm works on the assumption that pre-splitting values
     * into multiple rows ensures that they will fit and not need to wrap.
     *
     * @param list        The list of entries for this table
     * @param fontSize    The size of font to use for printing the table
     * @param yPosition   The starting y coordinate relative to the parent element
     * @param lineHeight  The amount to add to the y coordinate for each line
     * @param columnTypes The columns to include in the table. Used for overriding when printing bays.
     * @param colX        The x coordinate of each column, corresponding to the same index in <code>columnTypes</code>
     */
    private double printEquipmentTable(Element canvas, List<? extends InventoryEntry> list,
          double yPosition, float fontSize, double lineHeight, Column[] columnTypes, double[] colX) {
        for (InventoryEntry line : list) {
            if (line instanceof IntrinsicPhysicalInventoryEntry.HeaderEntry) {
                // Custom print for header entry (TODO: generalize this to a generic header entry)
                sheet.addTextElement(canvas,
                      colX[0] - (indent / 2),
                      yPosition,
                      line.getNameField(0),
                      fontSize,
                      SVGConstants.SVG_START_VALUE,
                      SVGConstants.SVG_BOLD_VALUE,
                      SVGConstants.SVG_NORMAL_VALUE,
                      FILL_BLACK,
                      null,
                      "header");
                yPosition += lineHeight;
                continue;
            }
            Element rowGroup = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            if (line instanceof WeaponBayInventoryEntry) {
                rowGroup.setAttributeNS(null, "class", "inventoryEntry bay");
            } else {
                rowGroup.setAttributeNS(null, "class", "inventoryEntry");
            }
            String uniqueId = line.getUniqueId();
            if (null != uniqueId && !uniqueId.isEmpty()) {
                rowGroup.setAttributeNS(null, "id", uniqueId);
                final String baseHitMod = line.getModField(0, true);
                if (baseHitMod != null && !baseHitMod.isEmpty()) {
                    rowGroup.setAttributeNS(null, "baseHitMod", baseHitMod);
                }
                final String hitMod = getModFieldWithSettings(line, 0);
                if (hitMod != null && !hitMod.isEmpty()) {
                    rowGroup.setAttributeNS(null, "hitMod", hitMod);
                }
                final String hitMod2 = getModFieldWithSettings(line, 1);
                if (hitMod2 != null && !hitMod2.isEmpty()) {
                    rowGroup.setAttributeNS(null, "hitMod2", hitMod2);
                }
                if (line instanceof IntrinsicPhysicalInventoryEntry) {
                    rowGroup.setAttributeNS(null, "iPhysAtk", line.getNameField(0).toLowerCase());
                }
                if (line instanceof StandardInventoryEntry sie) {
                    if (sie.getMounted().isSquadSupportWeapon()) {
                        rowGroup.setAttributeNS(null, "SSW", "1");
                    }
                }
            }
            canvas.appendChild(rowGroup);
            Element rootGroup = rowGroup;
            for (int row = 0; row < line.nRows(); row++) {
                rowGroup = rootGroup;
                if (row > 0) {
                    if (line instanceof StandardInventoryEntry stdInv) {
                        Mounted<?> modMount = stdInv.getMounted().getLinkedBy();
                        if (modMount != null) {
                            final String name = modMount.getType().getInternalName();
                            final String location = modMount.getEntity().getLocationAbbr(modMount.getLocation());
                            final int position = modMount.getEntity().slotNumber(modMount);
                            final String uniqueId2 = name + "@" + location + "#" + position;
                            if ((uniqueId == null) || !uniqueId.equals(uniqueId2)) {
                                Element rowGroup2 = sheet.getSVGDocument().createElementNS(svgNS,
                                      SVGConstants.SVG_G_TAG);
                                rowGroup2.setAttributeNS(null, "id", uniqueId2);
                                rowGroup2.setAttributeNS(null, "class", "inventoryEntry linked");
                                rootGroup.appendChild(rowGroup2);
                                rowGroup = rowGroup2; // For this row, we use the new subgroup
                            }
                        }
                    }
                }
                int lines = 1;
                if (line.isDamaged()) {
                    sheet.addLineThrough(rowGroup,
                          DAMAGE_LINE_THROUGH_MARGIN,
                          yPosition - (fontSize * 0.3),
                          viewWidth - DAMAGE_LINE_THROUGH_MARGIN);
                }
                for (int i = 0; i < columnTypes.length; i++) {
                    switch (columnTypes[i]) {
                        case QUANTITY:
                            if (row == 0) {
                                sheet.addTextElement(rowGroup,
                                      colX[i],
                                      yPosition,
                                      line.getQuantityField(row),
                                      fontSize,
                                      SVGConstants.SVG_MIDDLE_VALUE,
                                      SVGConstants.SVG_NORMAL_VALUE,
                                      SVGConstants.SVG_NORMAL_VALUE,
                                      FILL_BLACK,
                                      null,
                                      "quantity");
                            }
                            break;
                        case NAME:
                        case NAME_NO_QTY:
                        case BAY:
                            // Calculate the width of the name field to determine if wrapping is required.
                            // The following column is always location, which is centered, so we need
                            // to subtract half the width of that field as well, plus a bit of extra space.

                            double width = colX[i + 1] - colX[i] - indent;
                            if (!(sheet.getEntity() instanceof BattleArmor)) {
                                // getTextLength thinks some letters are much wider than other when in reality they pretty much all
                                // have the same width. This hack prevents some weapon names from getting split on
                                // some lines but not
                                // others for no reason apparent to the user.
                                width -= sheet.getTextLength(line.getLocationField(row).replaceAll("[A-Z]", "L"),
                                      fontSize) * 0.5;
                            }
                            if (row == 0) {
                                lines = sheet.addMultilineTextElement(rowGroup, colX[i],
                                      yPosition, width, lineHeight,
                                      line.getNameField(row), fontSize, SVGConstants.SVG_START_VALUE,
                                      SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK, "name");
                            } else {
                                lines = sheet.addMultilineTextElement(rowGroup, line.indentMultiline() ?
                                            colX[i] + indent : colX[i],
                                      yPosition, width, lineHeight,
                                      line.getNameField(row), fontSize, SVGConstants.SVG_START_VALUE,
                                      SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK, "name");
                            }
                            break;
                        case LOCATION:
                        case LOCATION_NO_HEAT:
                            sheet.addTextElement(rowGroup,
                                  colX[i],
                                  yPosition,
                                  line.getLocationField(row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "location");
                            break;
                        case HEAT:
                            sheet.addTextElement(rowGroup,
                                  colX[i],
                                  yPosition,
                                  line.getHeatField(row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "heat");
                            break;
                        case DAMAGE:
                            final float rightPadding = (fontSize / 2);
                            lines = Math.max(lines, sheet.addMultilineTextElement(rowGroup, colX[i],
                                  yPosition,
                                  colX[i + 1] - colX[i] - rightPadding, lineHeight, line.getDamageField(row),
                                  fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK
                                  , "damage"));
                            break;
                        case MOD:
                            // RecordSheetOptions.HitModStyle.COLUMN
                            sheet.addTextElement(rowGroup,
                                  colX[i],
                                  yPosition,
                                  getModFieldWithSettings(line, row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "hitmod");
                            break;
                        case MIN:
                            sheet.addTextElement(rowGroup,
                                  colX[i],
                                  yPosition,
                                  line.getMinField(row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "range_min");
                            break;
                        case SHORT:
                        case SRV:
                            sheet.addTextElementToFit(rowGroup,
                                  colX[i],
                                  yPosition,
                                  colX[i + 1] - colX[i] - 1,
                                  line.getShortField(row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "range_short");
                            break;
                        case MEDIUM:
                        case MRV:
                            sheet.addTextElementToFit(rowGroup,
                                  colX[i],
                                  yPosition,
                                  colX[i + 1] - colX[i] - 1,
                                  line.getMediumField(row),
                                  fontSize,
                                  SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK,
                                  null,
                                  "range_medium");
                            break;
                        case LONG:
                        case LRV:
                            sheet.addTextElementToFit(rowGroup, colX[i],
                                  yPosition, colX[i] - colX[i - 1] - 1,
                                  line.getLongField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK, null, "range_long");
                            break;
                        case ERV:
                            sheet.addTextElementToFit(rowGroup, colX[i],
                                  yPosition, colX[i] - colX[i - 1] - 1,
                                  line.getExtremeField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                  SVGConstants.SVG_NORMAL_VALUE,
                                  FILL_BLACK, null, "range_extreme");
                            break;
                    }
                }

                if (sheet.options.includeHitMod().equals(RecordSheetOptions.HitModStyle.EDGE)) {
                    String hitMod = getModFieldWithSettings(line, row);
                    if (hitMod != null && !hitMod.isEmpty()) {
                        // Create black square background for hit mod
                        double rectHeight = fontSize * 1.25; // Height of the rectangle
                        double rectWidth = fontSize * 1.5; // Width of the rectangle
                        Element rect = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_RECT_TAG);
                        rect.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(-rectWidth / 2));
                        rect.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE,
                              String.valueOf(yPosition - rectHeight * 0.75));
                        rect.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(rectWidth));
                        rect.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(rectHeight));
                        rect.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, FILL_BLACK);
                        rowGroup.appendChild(rect);

                        sheet.addTextElement(rowGroup, 0, yPosition, getModFieldWithSettings(line, row), fontSize,
                              SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE,
                              SVGConstants.SVG_NORMAL_VALUE, FILL_WHITE, null, "hitmod");
                    }
                }

                yPosition += lineHeight * lines;
            }
            if (sheet.showQuirks() && line.hasQuirks()) {
                int lines = sheet.addMultilineTextElement(rootGroup, colX[0] + indent,
                      yPosition, (viewWidth * 0.96) - (colX[0] + indent), (lineHeight * QUIRKS_FONT_SCALING),
                      line.getQuirksField(), fontSize * QUIRKS_FONT_SCALING, SVGConstants.SVG_START_VALUE,
                      SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_ITALIC_VALUE, "weaponQuirks");
                yPosition += lineHeight * lines;
            }
        }
        return yPosition;
    }

    private double printCapitalHeader(double currY) {
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            switch (Column.BAY_COLUMNS[i]) {
                case NAME:
                case NAME_NO_QTY:
                case BAY:
                    sheet.addTextElement(canvas, bayColX[i], currY, "Capital Scale", FONT_SIZE_MEDIUM,
                          SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case SRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                          "(1-12)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case MRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                          "(13-24)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case LRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                          "(25-40)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case ERV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i] - bayColX[i - 1] - 1,
                          "(41-50)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        return printColumnHeaders(currY, Column.BAY_COLUMNS, bayColX);
    }

    private double printAeroStandardHeader(double currY) {
        return printAeroStandardHeader(currY, columnTypes, colX);
    }

    private double printAeroStandardHeader(double currY, Column[] columnTypes, double[] colX) {
        for (int i = 0; i < columnTypes.length; i++) {
            switch (columnTypes[i]) {
                case NAME:
                case NAME_NO_QTY:
                case BAY:
                    // Use first bay field to left-justify whether this unit uses bays or not
                    sheet.addTextElement(canvas, bayColX[0], currY, "Standard Scale", FONT_SIZE_MEDIUM,
                          SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case SRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                          "(1-6)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case MRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                          "(7-12)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case LRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                          "(13-20)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case ERV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i] - colX[i - 1] - 1,
                          "(21-25)", FONT_SIZE_VERY_SMALL, SVGConstants.SVG_MIDDLE_VALUE,
                          SVGConstants.SVG_NORMAL_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        return printColumnHeaders(currY);
    }

    /**
     * Adds a static table showing the details of the types of AR10 missiles.
     *
     * @param fontSize   The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY      The y coordinate of the top of the table
     *
     * @return The y coordinate of the bottom of the table
     */
    public double printAR10Block(float fontSize, double lineHeight, double currY) {
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            switch (Column.BAY_COLUMNS[i]) {
                case BAY:
                    sheet.addTextElement(canvas, bayColX[i], currY, "AR10 Munitions", FONT_SIZE_MEDIUM,
                          SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case LOCATION:
                    sheet.addTextElement(canvas, bayColX[i], currY, "Tons", FONT_SIZE_MEDIUM,
                          SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case HEAT:
                case SRV:
                case MRV:
                case LRV:
                case ERV:
                    sheet.addTextElement(canvas, bayColX[i], currY, Column.BAY_COLUMNS[i].header, FONT_SIZE_MEDIUM,
                          SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        currY = printEquipmentTable(canvas, Collections.singletonList(new AR10InventoryEntry()), currY,
              fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
        return currY;
    }

    /**
     * Prints the gravity deck block
     *
     * @param ship       The jumpship, warship, or space station
     * @param fontSize   The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY      The y coordinate of the top of the table
     *
     * @return The y coordinate of the bottom of the table
     */
    public double printGravityDecks(Jumpship ship, float fontSize, double lineHeight, double currY) {
        if (ship.getTotalGravDeck() > 0) {
            double xPosition = bayColX[0];
            sheet.addTextElement(canvas,
                  xPosition, currY, "Grav Decks:",
                  FONT_SIZE_MEDIUM, SVGConstants.SVG_START_VALUE,
                  SVGConstants.SVG_BOLD_VALUE);
            currY += lineHeight;
            double yPosition = currY;
            int count = 1;
            for (int size : ship.getGravDecks()) {
                String gravityString = "Grav Deck #" + count + ": " + size + "-meters";
                sheet.addTextElement(canvas,
                      xPosition,
                      yPosition,
                      gravityString,
                      fontSize,
                      SVGConstants.SVG_START_VALUE,
                      SVGConstants.SVG_NORMAL_VALUE);
                yPosition += lineHeight;
                if (count == (ship.getGravDecks().size() / 2)) {
                    yPosition = currY;
                    xPosition += viewWidth / 2.0;
                }
                count++;
            }
            currY += lineHeight * (((double) ((ship.getGravDecks().size() + 1) / 2)) + 1);
        }
        return currY;
    }

    /**
     * Convenience method for printing information related to cargo and transport bays.
     *
     * @param fontSize   The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY      The y coordinate of the top of the table
     *
     * @return The y coordinate of the bottom of the table
     */
    public double printBayInfo(float fontSize, double lineHeight, double currY) {
        if (!transportBays.isEmpty()) {
            sheet.addTextElement(canvas, bayColX[0], currY, "Cargo:", FONT_SIZE_MEDIUM,
                  SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
            currY += lineHeight;
            // We can have multiple Bay instances within one conceptual bay on the ship
            // We need to gather all bays with the same ID to print out the string
            Map<Integer, List<Bay>> bayMap = new TreeMap<>();
            for (Bay bay : transportBays) {
                List<Bay> bays = bayMap.get(bay.getBayNumber());
                if (bays == null) {
                    bays = new ArrayList<>();
                    bays.add(bay);
                    bayMap.put(bay.getBayNumber(), bays);
                } else {
                    bays.add(bay);
                }
            }
            // Print each bay
            for (Integer bayNum : bayMap.keySet()) {
                StringBuilder bayTypeString = new StringBuilder();
                StringBuilder bayCapacityString = new StringBuilder();
                bayCapacityString.append(" (");
                List<Bay> bays = bayMap.get(bayNum);
                // Display larger storage first
                bays.sort(Comparator.comparing(Bay::getCapacity));
                int doors = 0;
                for (int i = 0; i < bays.size(); i++) {
                    Bay bay = bays.get(i);
                    bayTypeString.append(bay.getNameForRecordSheets());
                    // BA bays are shown per suit rather than squad
                    double capacity = getCapacity(bay);
                    bayCapacityString.append(NumberFormat.getInstance().format(capacity));
                    if ((i + 1) < bays.size()) {
                        bayTypeString.append('/');
                        bayCapacityString.append('/');
                    }
                    doors = Math.max(doors, bay.getDoors());
                }
                bayCapacityString.append(')');
                String bayString = "Bay " + bayNum + ": " + bayTypeString
                      + bayCapacityString + " (" + doors + (doors == 1 ? " Door)" : " Doors)");
                sheet.addTextElement(canvas, bayColX[0], currY, bayString, fontSize,
                      SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                currY += lineHeight;
            }
            currY += lineHeight;
        }
        return currY;
    }

    private static double getCapacity(Bay b) {
        double capacity = b.getCapacity();
        if (b instanceof BattleArmorBay) {
            if (b.isClan()) {
                capacity *= 5;
            } else if (((BattleArmorBay) b).isComStar()) {
                capacity *= 6;
            } else {
                capacity *= 4;
            }
        } else if (b instanceof InfantryBay) {
            // Divide total weight by weight required by platoon to get platoon capacity
            capacity /= ((InfantryBay) b).getPlatoonType().getWeight();
        } else if (b instanceof ProtoMekBay) {
            capacity *= 5;
        }
        return capacity;
    }

    /**
     * Add single line of text explaining where to find the standard scale bays for ships that cannot fit both capital
     * and standard on the obverse.
     *
     * @param lineHeight The height of each table row
     * @param currY      The y coordinate of the top of the table
     *
     * @return The y coordinate of the bottom of the table
     */
    public double printReverseSideMessage(double lineHeight, double currY) {
        sheet.addTextElement(canvas, bayColX[0],
              currY, "Standard Scale on Reverse", FONT_SIZE_MEDIUM,
              SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        return currY + lineHeight * 2;
    }

    private String getModFieldWithSettings(InventoryEntry line, int row) {
        var mod = line.getModField(row);
        if (mod != null) {
            return mod.replace(InventoryEntry.DASH, sheet.options.explicitZeroModifier().getModString());
        } else {
            return null;
        }
    }
}
