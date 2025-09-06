/*
 * Copyright (C) 2011-2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.util;

import static megamek.client.ui.util.UIUtil.alternateTableBGColor;

import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import megamek.common.RangeType;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.Tank;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtoMek;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.gaussRifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
import megamek.common.weapons.missiles.thuunderbolt.ThunderboltWeapon;
import megamek.common.weapons.mortars.MekMortarWeapon;
import megameklab.util.CConfig;
import megameklab.util.InfantryUtil;

/**
 * this model was not being used by anything, so I totally redid so that it can be used as the model for the equipment
 * tab. It will be a sortable, filterable table of equipment, similar to the tables in MHQ
 *
 * @author Jay lawson
 */
public class EquipmentTableModel extends AbstractTableModel {

    public final static int ROW_HEIGHT_PADDING = 6;
    public final static String VARIABLE = "variable";

    public final static int COL_NAME = 0;
    public final static int COL_DAMAGE = 1;
    public final static int COL_DIVISOR = 2;
    public final static int COL_SPECIAL = 3;
    public final static int COL_HEAT = 4;
    public final static int COL_MEDIUM_RANGE = 5;
    public final static int COL_RANGE = 6;
    public final static int COL_SHOTS = 7;
    public final static int COL_TECH = 8;
    public final static int COL_TECH_LEVEL = 9;
    public final static int COL_TECH_RATING = 10;
    public final static int COL_DATE_PROTOTYPE = 11;
    public final static int COL_DATE_PRODUCTION = 12;
    public final static int COL_DATE_COMMON = 13;
    public final static int COL_DATE_EXTINCT = 14;
    public final static int COL_DATE_REINTRODUCED = 15;
    public final static int COL_COST = 16;
    public final static int COL_CREW = 17;
    public final static int COL_BV = 18;
    public final static int COL_TON = 19;
    public final static int COL_CRIT = 20;
    public final static int COL_REF = 21;
    public final static int N_COL = 22;

    private ArrayList<EquipmentType> data = new ArrayList<>();
    private final Entity entity;
    final private ITechManager techManager;

    public EquipmentTableModel(Entity e, ITechManager techManager) {
        entity = e;
        this.techManager = techManager;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return N_COL;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case COL_NAME -> "Name";
            case COL_DAMAGE -> "Damage";
            case COL_DIVISOR -> "Divisor";
            case COL_SPECIAL -> "Special";
            case COL_HEAT -> "Heat";
            case COL_MEDIUM_RANGE -> "Min R";
            case COL_RANGE -> "Range";
            case COL_TON -> "Weight";
            case COL_CRIT -> {
                if (entity instanceof Tank) {
                    yield "Slots";
                }
                yield "Crit";
            }
            case COL_CREW -> "Crew";
            case COL_TECH -> "Base";
            case COL_TECH_LEVEL -> "Level";
            case COL_TECH_RATING -> "Rating";
            case COL_COST -> "Cost";
            case COL_SHOTS -> "Shots";
            case COL_BV -> "BV";
            case COL_DATE_PROTOTYPE -> "Prototype";
            case COL_DATE_PRODUCTION -> "Production";
            case COL_DATE_COMMON -> "Common";
            case COL_DATE_EXTINCT -> "Extinct";
            case COL_DATE_REINTRODUCED -> "Re-intro";
            case COL_REF -> "Reference";
            default -> "?";
        };
    }

    public int getColumnWidth(int c) {
        return switch (c) {
            case COL_NAME -> 120;
            /*
             * case COL_DATES: return 100;
             */
            case COL_RANGE, COL_COST, COL_TECH_RATING -> 50;
            /*
             * case COL_TECH_RATING: case COL_COST: return 20;
             */
            case COL_TON, COL_CRIT, COL_MEDIUM_RANGE -> 5;
            default -> 30;
        };
    }

    private int getAlignment(int col) {
        if (col == COL_NAME) {
            return SwingConstants.LEFT;
        }
        return SwingConstants.CENTER;
    }

    public Comparator<?> getSorter(int col) {
        return switch (col) {
            case COL_DAMAGE, COL_RANGE -> RANGE_DAMAGE_SORTER;
            case COL_HEAT, COL_MEDIUM_RANGE, COL_TON, COL_CRIT, COL_COST, COL_SHOTS, COL_BV -> NUMBER_SORTER;
            case COL_DATE_PROTOTYPE, COL_DATE_PRODUCTION, COL_DATE_COMMON, COL_DATE_EXTINCT, COL_DATE_REINTRODUCED ->
                  Comparator.comparingInt(EquipmentTableModel::parseDate);
            case COL_REF -> REFERENCE_SORTER;
            default -> Comparator.naturalOrder();
        };
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public EquipmentType getType(int i) {
        if (i >= data.size()) {
            return null;
        }
        return data.get(i);
    }

    // fill table with values
    public void setData(ArrayList<EquipmentType> equip) {
        data = equip;
        fireTableDataChanged();
    }

    // The DecimalFormat constructor is *incredibly* slow. We want to call it as little as we possibly can.
    private static final DecimalFormat defaultDecimalFormatter = new DecimalFormat();
    private static final DecimalFormat kilogramWeightFormatter = new DecimalFormat("#.## kg");

    @Override
    public Object getValueAt(int row, int col) {
        EquipmentType type;
        WeaponType weaponType = null;
        AmmoType ammoType = null;
        MiscType miscType = null;
        if (data.isEmpty()) {
            return "";
        } else {
            type = data.get(row);
        }
        if (type instanceof WeaponType) {
            weaponType = (WeaponType) type;
        }
        if (type instanceof AmmoType) {
            ammoType = (AmmoType) type;
        }
        if (type instanceof MiscType) {
            miscType = (MiscType) type;
        }

        if (col == COL_NAME) {
            return type.getSortingName();
        } else if (col == COL_DAMAGE) {
            if (null != weaponType) {
                return getDamageString(weaponType, entity instanceof Aero);
            } else {
                return "-";
            }
        } else if (col == COL_DIVISOR) {
            if ((miscType != null) && (miscType.hasFlag(MiscType.F_ARMOR_KIT))) {
                if ((miscType.getSubType() & MiscType.S_ENCUMBERING) == 0) {
                    return String.valueOf(miscType.getDamageDivisor());
                } else {
                    return miscType.getDamageDivisor() + "E";
                }
            } else {
                return "-";
            }
        } else if (col == COL_SPECIAL) {
            String special = "";
            if (type instanceof InfantryWeapon) {
                if (type.hasFlag(WeaponType.F_INF_POINT_BLANK)) {
                    special += "(P)";
                }
                if (type.hasFlag(WeaponType.F_INF_AA)) {
                    special += "A";
                }
                if (type.hasFlag(WeaponType.F_INF_BURST)) {
                    special += "B";
                }
                if (type.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
                    special += "N";
                }
                if (type.hasFlag(WeaponType.F_PLASMA)
                      || type.hasFlag(WeaponType.F_INCENDIARY_NEEDLES)
                      || type.hasFlag(WeaponType.F_INFERNO)
                      || type.hasFlag(WeaponType.F_FLAMER)) {
                    special += "F";
                }
            }
            if ((miscType != null) && (miscType.hasFlag(MiscType.F_ARMOR_KIT))) {
                if ((miscType.getSubType() & MiscType.S_DEST) != 0) {
                    special += "DEST ";
                }
                if ((miscType.getSubType() & MiscType.S_SNEAK_CAMO) != 0) {
                    special += "Camo ";
                }
                if ((miscType.getSubType() & MiscType.S_SNEAK_IR) != 0) {
                    special += "IR ";
                }
                if ((miscType.getSubType() & MiscType.S_SNEAK_ECM) != 0) {
                    special += "ECM ";
                }
                if ((miscType.getSubType() & MiscType.S_SPACE_SUIT) != 0) {
                    special += "SPC ";
                }
            }
            return special;
        } else if (col == COL_CREW) {
            String special = "";
            if (type instanceof InfantryWeapon) {
                special += Integer.toString(((InfantryWeapon) type).getCrew());
                if (type.hasFlag(WeaponType.F_INF_ENCUMBER)) {
                    special += "E";
                }
            } else if (type instanceof WeaponType) {
                // Field gun crew size
                special += Math.max(2, (int) Math.ceil(type.getTonnage(entity)));
            }
            return special;
        } else if (col == COL_HEAT) {
            int heat = type.getHeat();
            if ((null != weaponType) && (entity instanceof Aero)) {
                heat *= Mounted.getNumShots(weaponType, null, true);
            }
            if (heat == 0) {
                return "-";
            } else {
                return Integer.toString(heat);
            }
        } else if (col == COL_SHOTS) {
            if (null != ammoType) {
                return Integer.toString(ammoType.getShots());
            } else {
                return "-";
            }
        } else if (col == COL_RANGE) {
            if (null != weaponType) {
                if (entity instanceof Aero) {
                    switch (weaponType.getMaxRange()) {
                        case RangeType.RANGE_SHORT:
                            return "Short";
                        case RangeType.RANGE_MEDIUM:
                            return "Medium";
                        case RangeType.RANGE_LONG:
                            return "Long";
                        case RangeType.RANGE_EXTREME:
                            return "Extreme";
                    }
                }
                if (weaponType instanceof InfantryWeapon) {
                    return ((InfantryWeapon) weaponType).getInfantryRange() + "";
                }
                return weaponType.getShortRange() + "/" + weaponType.getMediumRange()
                      + "/" + weaponType.getLongRange();
            } else {
                return "-";
            }
        } else if (col == COL_MEDIUM_RANGE) {
            if (null != weaponType) {
                if (entity instanceof Aero) {
                    return "-";
                }
                int minRange = weaponType.getMinimumRange();
                if (minRange < 0) {
                    minRange = 0;
                }
                return Integer.toString(minRange);
            } else {
                return "-";
            }
        } else if (col == COL_TON) {
            final double weight = type.getTonnage(entity);
            if ((ammoType != null) && (entity.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)
                  || entity.hasETypeFlag(Entity.ETYPE_PROTOMEK))) {
                return String.format("%.2f kg/shot", ammoType.getKgPerShot());
            } else if (type.isVariableTonnage()) {
                return VARIABLE;
            } else if (TestEntity.usesKgStandard(entity) || ((weight > 0.0) && (weight < 0.1))) {
                return kilogramWeightFormatter.format(type.getTonnage(entity) * 1000);
            } else if (entity.isHandheldWeapon() && type instanceof AmmoType at) {
                return kilogramWeightFormatter.format(at.getKgPerShot());
            } else {
                return defaultDecimalFormatter.format(weight);
            }
        } else if (col == COL_CRIT) {
            if (type.isVariableCriticalSlots()
                  && (entity.isSupportVehicle() || (entity instanceof Mek))) {
                // Only Meks and support vehicles require multiple slots for equipment
                return "variable";
            } else if (entity.isSupportVehicle()) {
                return type.getSupportVeeSlots(entity);
            } else if (entity instanceof Tank) {
                return type.getTankSlots(entity);
            } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
                return TestProtoMek.requiresSlot(type) ? 1 : 0;
            }
            return type.getNumCriticalSlots(entity);
        } else if (col == COL_TECH_RATING) {
            return type.getFullRatingName(entity.isClan());
        } else if (col == COL_COST) {
            if (type.isVariableCost()) {
                return "variable";
            }
            return defaultDecimalFormatter.format(type
                  .getCost(entity, false, Entity.LOC_NONE));
        } else if (col == COL_BV) {
            if (type.isVariableBV()) {
                return "variable";
            }
            return type.getBV(entity);
        } else if (col == COL_DATE_PROTOTYPE) {
            return entity.isMixedTech() ? type.getTechAdvancement().getPrototypeDateName() :
                  type.getTechAdvancement().getPrototypeDateName(entity.isClan());
        } else if (col == COL_DATE_PRODUCTION) {
            return entity.isMixedTech() ? type.getTechAdvancement().getProductionDateName() :
                  type.getTechAdvancement().getProductionDateName(entity.isClan());
        } else if (col == COL_DATE_COMMON) {
            return entity.isMixedTech() ? type.getTechAdvancement().getCommonDateName() :
                  type.getTechAdvancement().getCommonDateName(entity.isClan());
        } else if (col == COL_DATE_EXTINCT) {
            return entity.isMixedTech() ? type.getTechAdvancement().getExtinctionDateName() :
                  type.getTechAdvancement().getExtinctionDateName(entity.isClan());
        } else if (col == COL_DATE_REINTRODUCED) {
            return entity.isMixedTech() ? type.getTechAdvancement().getReintroductionDateName() :
                  type.getTechAdvancement().getReintroductionDateName(entity.isClan());
        } else if (col == COL_TECH_LEVEL) {
            if ((null != techManager) && CConfig.getBooleanParam(CConfig.TECH_PROGRESSION)) {
                return type.getSimpleLevel(techManager.getGameYear(), techManager.useClanTechBase(),
                      techManager.getTechFaction()).toString();
            } else {
                return type.getStaticTechLevel().toString();
            }
        } else if (col == COL_TECH) {
            return getTechBaseAsString(type);
        } else if (col == COL_REF) {
            return type.getRulesRefs();
        }
        return "?";
    }

    /**
     * @param equipment The equipment in question; must not be null
     *
     * @return a String representation of the Tech Base of the given equipment, i.e. "All", "IS" or "Clan" - or
     *       "Unknown" if the Tech Base is not one of the standard values.
     */
    public static String getTechBaseAsString(EquipmentType equipment) {
        return switch (equipment.getTechBase()) {
            case ALL -> "All";
            case IS -> "IS";
            case CLAN -> "Clan";
            default -> "Unknown";
        };
    }

    static String getDamageString(WeaponType weaponType, boolean isAero) {
        // Aerospace should print AV instead
        if (isAero) {
            int[] attackValue = new int[RangeType.RANGE_EXTREME + 1];
            attackValue[RangeType.RANGE_SHORT] = weaponType.getRoundShortAV();
            attackValue[RangeType.RANGE_MEDIUM] = weaponType.getRoundMedAV();
            attackValue[RangeType.RANGE_LONG] = weaponType.getRoundLongAV();
            attackValue[RangeType.RANGE_EXTREME] = weaponType.getRoundExtAV();
            boolean allEq = true;
            for (int i = 2; i <= weaponType.getMaxRange(); i++) {
                if (attackValue[i - 1] != attackValue[i]) {
                    allEq = false;
                    break;
                }
            }
            StringBuilder avString = new StringBuilder();
            avString.append(attackValue[RangeType.RANGE_SHORT]);
            if (!allEq) {
                for (int i = 2; i <= weaponType.getMaxRange(); i++) {
                    avString.append('/').append(attackValue[i]);
                }
            }
            return avString.toString();
        }
        // Damage for non-Aerospace
        if (weaponType instanceof InfantryWeapon) {
            return Double
                  .toString(((InfantryWeapon) weaponType).getInfantryDamage());
        }

        if (weaponType.getDamage() == WeaponType.DAMAGE_VARIABLE) {
            return weaponType.getDamage(weaponType.getShortRange()) + "/"
                  + weaponType.getDamage(weaponType.getMediumRange()) + "/"
                  + weaponType.getDamage(weaponType.getLongRange());
        } else if (weaponType.getDamage() == WeaponType.DAMAGE_BY_CLUSTER_TABLE) {
            if (weaponType instanceof HAGWeapon) {
                return weaponType.getRackSize() + "";
            } else if (weaponType instanceof MekMortarWeapon) {
                return "Special";
            } else if (weaponType instanceof MissileWeapon) {
                int dmg;
                if (weaponType instanceof ThunderboltWeapon) {
                    return switch (weaponType.getAmmoType()) {
                        case TBOLT_5 -> "5";
                        case TBOLT_10 -> "10";
                        case TBOLT_15 -> "15";
                        case TBOLT_20 -> "20";
                        default -> "0";
                    };
                } else if ((weaponType instanceof ATMWeapon)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_STREAK)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_ADVANCED)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_IMP)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_PRIMITIVE)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_TORPEDO)) {
                    dmg = 2;
                } else {
                    dmg = 1;
                }
                return dmg + "/msl";
            }
            return "Cluster";
        } else if (weaponType.getDamage() == WeaponType.DAMAGE_ARTILLERY) {
            return weaponType.getRackSize() + "A";
        } else if (weaponType instanceof UACWeapon) {
            return weaponType.getDamage() + "/Shot";
        } else if (weaponType.getDamage() < 0) {
            return "Special";
        } else {
            return Integer.toString(weaponType.getDamage());
        }
    }

    public EquipmentTableModel.Renderer getRenderer() {
        return new EquipmentTableModel.Renderer();
    }

    public class Renderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
              Object value, boolean isSelected, boolean hasFocus, int row,
              int column) {
            int actualCol = table.convertColumnIndexToModel(column);
            int actualRow = table.convertRowIndexToModel(row);
            EquipmentType etype = ((EquipmentTableModel) table.getModel()).getType(actualRow);
            if (column == COL_NAME) {
                // Reinstate the real name, as the value will be the sorting-optimized name
                value = InfantryUtil.trimInfantryWeaponNames(etype.getName());
            }
            super.getTableCellRendererComponent(table, value, isSelected,
                  hasFocus, row, column);
            setHorizontalAlignment(getAlignment(actualCol));
            if (null != techManager && !techManager.isLegal(etype)) {
                setForeground(UIManager.getColor("Label.disabledForeground"));
            } else {
                setForeground(UIManager.getColor("Label.foreground"));
            }
            if (!isSelected) {
                setBackground(((row % 2) != 0) ? alternateTableBGColor() : table.getBackground());
            }
            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension superPreferredSize = super.getPreferredSize();
            return new Dimension(superPreferredSize.width, superPreferredSize.height + ROW_HEIGHT_PADDING);
        }
    }

    /**
     * Comparator for numeric columns. Non-numeric values such as "variable" or "special" are sorted alphabetically and
     * placed at the end (if in descending order). Strings ending in "kg" are parsed as numbers and converted to tons.
     */
    private static final Comparator<Object> NUMBER_SORTER = (o1, o2) -> {
        double d1 = -1.0;
        double d2 = -1.0;
        try {
            if (o1 instanceof Number) {
                // Simplest processing of Integer and Double
                d1 = ((Number) o1).doubleValue();
            } else if (o1.toString().endsWith("kg")) {
                // Convert kg values to tons
                d1 = Double.parseDouble(o1.toString().replace("kg", "").trim()) / 1000.0;
            } else {
                // Handle potentially comma'd number
                d1 = NumberFormat.getInstance().parse(o1.toString()).doubleValue();
            }
        } catch (NumberFormatException | ParseException ignored) {
            // Not a representation of a number; sort alphabetically
        }
        try {
            if (o2 instanceof Number) {
                d2 = ((Number) o2).doubleValue();
            } else if (o2.toString().endsWith("kg")) {
                d2 = Double.parseDouble(o2.toString().replace("kg", "").trim()) / 1000.0;
            } else {
                d2 = NumberFormat.getInstance().parse(o2.toString()).doubleValue();
            }
        } catch (NumberFormatException | ParseException ignored) {
            // Not a representation of a number; sort alphabetically
        }
        if ((d1 < 0) && (d2 < 0)) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        } else {
            return Double.compare(d1, d2);
        }
    };

    /**
     * Sorter for a series of one or more values separated by slashes. This handles weapon ranges and also deals with
     * multiple damage values.
     */
    private static final Comparator<String> RANGE_DAMAGE_SORTER = (s1, s2) -> {
        String[] r1 = s1.split("/");
        String[] r2 = s2.split("/");
        int retVal = 0;
        for (int i = 0; i < Math.min(r1.length, r2.length); i++) {
            retVal = NUMBER_SORTER.compare(r1[i], r2[i]);
            if (retVal != 0) {
                break;
            }
        }
        return retVal;
    };

    /**
     * Sorter for reference column. References give the page number then the work separated by a comma. Sorts by the
     * work first, then the page number.
     */
    private static final Comparator<String> REFERENCE_SORTER = (s1, s2) -> {
        String[] r1 = s1.split(",\\s*");
        String[] r2 = s2.split(",\\s*");
        if ((r1.length > 1) && (r2.length > 1) && !r1[1].equals(r2[1])) {
            return r1[1].compareTo(r2[1]);
        }
        return NUMBER_SORTER.compare(r1[0], r2[0]);
    };

    /**
     * Converts an entry in the tech advancement table to an integer year for sorting.
     *
     * @param date The date entry
     *
     * @return The year represented
     */
    private static int parseDate(String date) {
        if (date.startsWith("PS")) {
            return 1950;
        } else if (date.startsWith("ES")) {
            return 2100;
        } else if (date.equals("-")) {
            return 0;
        } else {
            try {
                return Integer.parseInt(date.replaceAll("[^0-9]", "").trim());
            } catch (NumberFormatException ex) {
                return 0;
            }
        }
    }
}
