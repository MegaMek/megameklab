/*
 * MegaMekLab - Copyright (C) 2011
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.util;

import java.awt.Component;
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

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtomech;
import megamek.common.weapons.autocannons.UACWeapon;
import megamek.common.weapons.gaussrifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
import megamek.common.weapons.missiles.ThunderBoltWeapon;
import megamek.common.weapons.mortars.MekMortarWeapon;

/**
 * this model was not being used by anything, so I totally redid so that it can
 * be used as the model for the equipment tab. It will be a sortable, filterable
 * table of equipment, similar to the tables in MHQ
 *
 * @author Jay lawson
 */
public class EquipmentTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5207167419079014157L;

    public final static String VARIABLE = "variable";

    public final static int COL_NAME = 0;
    public final static int COL_DAMAGE = 1;
    public final static int COL_DIVISOR = 2;
    public final static int COL_SPECIAL = 3;
    public final static int COL_HEAT = 4;
    public final static int COL_MRANGE = 5;
    public final static int COL_RANGE = 6;
    public final static int COL_SHOTS = 7;
    public final static int COL_TECH = 8;
    public final static int COL_TLEVEL = 9;
    public final static int COL_TRATING = 10;
    public final static int COL_DPROTOTYPE = 11;
    public final static int COL_DPRODUCTION = 12;
    public final static int COL_DCOMMON = 13;
    public final static int COL_DEXTINCT = 14;
    public final static int COL_DREINTRO = 15;
    public final static int COL_COST = 16;
    public final static int COL_CREW = 17;
    public final static int COL_BV = 18;
    public final static int COL_TON = 19;
    public final static int COL_CRIT = 20;
    public final static int COL_REF = 21;
    public final static int N_COL = 22;

    private ArrayList<EquipmentType> data = new ArrayList<>();
    private Entity entity;
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
        switch (column) {
            case COL_NAME:
                return "Name";
            case COL_DAMAGE:
                return "Damage";
            case COL_DIVISOR:
                return "Divisor";
            case COL_SPECIAL:
                return "Special";
            case COL_HEAT:
                return "Heat";
            case COL_MRANGE:
                return "Min R";
            case COL_RANGE:
                return "Range";
            case COL_TON:
                return "Ton";
            case COL_CRIT:
                if (entity instanceof Tank) {
                    return "Slots";
                }
                return "Crit";
            case COL_CREW:
                return "Crew";
            case COL_TECH:
                return "Base";
            case COL_TLEVEL:
                return "Level";
            case COL_TRATING:
                return "Rating";
            case COL_COST:
                return "Cost";
            case COL_SHOTS:
                return "Shots";
            case COL_BV:
                return "BV";
            case COL_DPROTOTYPE:
                return "Prototype";
            case COL_DPRODUCTION:
                return "Production";
            case COL_DCOMMON:
                return "Common";
            case COL_DEXTINCT:
                return "Extinct";
            case COL_DREINTRO:
                return "Re-intro";
            case COL_REF:
                return "Reference";
            default:
                return "?";
        }
    }

    public int getColumnWidth(int c) {
        switch (c) {
            case COL_NAME:
                return 120;
                /*
                 * case COL_DATES: return 100;
                 */
            case COL_RANGE:
            case COL_COST:
            case COL_TRATING:
                return 50;
                /*
                 * case COL_TRATING: case COL_COST: return 20;
                 */
            case COL_TON:
            case COL_CRIT:
            case COL_MRANGE:
                return 5;
            default:
                return 30;
        }
    }

    private int getAlignment(int col) {
        if (col == COL_NAME) {
            return SwingConstants.LEFT;
        }
        return SwingConstants.CENTER;
    }

    public Comparator<?> getSorter(int col) {
        switch(col) {
            case COL_DAMAGE:
            case COL_RANGE:
                return RANGE_DAMAGE_SORTER;
            case COL_HEAT:
            case COL_MRANGE:
            case COL_TON:
            case COL_CRIT:
            case COL_COST:
            case COL_SHOTS:
            case COL_BV:
                return NUMBER_SORTER;
            case COL_DPROTOTYPE:
            case COL_DPRODUCTION:
            case COL_DCOMMON:
            case COL_DEXTINCT:
            case COL_DREINTRO:
                return Comparator.comparingInt(EquipmentTableModel::parseDate);
            case COL_REF:
                return REFERENCE_SORTER;
            default:
                return Comparator.naturalOrder();
        }
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
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

    @Override
    public Object getValueAt(int row, int col) {
        EquipmentType type;
        WeaponType wtype = null;
        AmmoType atype = null;
        MiscType mtype = null;
        if (data.isEmpty()) {
            return "";
        } else {
            type = data.get(row);
        }
        if (type instanceof WeaponType) {
            wtype = (WeaponType) type;
        }
        if (type instanceof AmmoType) {
            atype = (AmmoType) type;
        }
        if (type instanceof MiscType) {
            mtype = (MiscType) type;
        }
        DecimalFormat formatter = new DecimalFormat();

        if (col == COL_NAME) {
            return UnitUtil.trimInfantryWeaponNames(type.getName());
        } else if (col == COL_DAMAGE) {
            if (null != wtype) {
                return getDamageString(wtype, entity instanceof Aero);
            } else {
                return "-";
            }
        } else if (col == COL_DIVISOR) {
            if (mtype != null && mtype.hasFlag(MiscType.F_ARMOR_KIT)){
                if ((mtype.getSubType() & MiscType.S_ENCUMBERING) == 0) {
                    return String.valueOf(mtype.getDamageDivisor());
                } else {
                    return mtype.getDamageDivisor() + "E";
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
            if (type.hasFlag(MiscType.F_ARMOR_KIT)) {
                if ((type.getSubType() & MiscType.S_DEST) != 0) {
                    special += "DEST ";
                }
                if ((type.getSubType() & MiscType.S_SNEAK_CAMO) != 0) {
                    special += "Camo ";
                }
                if ((type.getSubType() & MiscType.S_SNEAK_IR) != 0) {
                    special += "IR ";
                }
                if ((type.getSubType() & MiscType.S_SNEAK_ECM) != 0) {
                    special += "ECM ";
                }
                if ((type.getSubType() & MiscType.S_SPACE_SUIT) != 0) {
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
                special += Math.max(2, (int)Math.ceil(type.getTonnage(entity))); 
            }
            return special;
        } else if (col == COL_HEAT) {
            int heat = type.getHeat();
            if ((null != wtype) && (entity instanceof Aero)) {
                heat *= Mounted.getNumShots(wtype,  null,  true);
            }
            if (heat == 0) {
                return "-";
            } else {
                return Integer.toString(heat);
            }
        } else if (col == COL_SHOTS) {
            if (null != atype) {
                return Integer.toString(atype.getShots());
            } else {
                return "-";
            }
        } else if (col == COL_RANGE) {
            if (null != wtype) {
                if (entity instanceof Aero) {
                    switch (wtype.getMaxRange(null)) {
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
                if (wtype instanceof InfantryWeapon) {
                    return ((InfantryWeapon) wtype).getInfantryRange() + "";
                }
                return wtype.getShortRange() + "/" + wtype.getMediumRange()
                        + "/" + wtype.getLongRange();
            } else {
                return "-";
            }
        } else if (col == COL_MRANGE) {
            if (null != wtype) {
                if (entity instanceof Aero) {
                    return "-";
                }
                int minRange = wtype.getMinimumRange();
                if (minRange < 0) {
                    minRange = 0;
                }
                return Integer.toString(minRange);
            } else {
                return "-";
            }
        } else if (col == COL_TON) {
            final double weight = type.getTonnage(entity);
            if ((atype != null) && (entity.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)
                    || entity.hasETypeFlag(Entity.ETYPE_PROTOMECH))) {
                return String.format("%.2f kg/shot", atype.getKgPerShot());
            } else if (type.isVariableTonnage()) {
                return VARIABLE;
            } else if (TestEntity.usesKgStandard(entity) || ((weight > 0.0) && (weight < 0.1))) {
                return String.format("%.0f kg", type.getTonnage(entity) * 1000);
            } else {
                return formatter.format(weight);
            }
        } else if (col == COL_CRIT) {
            if (type.isVariableCriticals()
                    && (entity.isSupportVehicle() || (entity instanceof Mech))) {
                // Only Mechs and support vehicles require multiple slots for equipment
                return "variable";
            } else if (entity.isSupportVehicle()) {
                return type.getSupportVeeSlots(entity);
            } else if (entity instanceof Tank) {
                return type.getTankSlots(entity);
            } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
                return TestProtomech.requiresSlot(type)? 1 : 0;
            }
            return type.getCriticals(entity);
        } else if (col == COL_TRATING) {
            return type.getFullRatingName(entity.isClan());
        } else if (col == COL_COST) {
            if (type.isVariableCost()) {
                return "variable";
            }
            return formatter.format(type
                    .getCost(entity, false, Entity.LOC_NONE));
        } else if (col == COL_BV) {
            if (type.isVariableBV()) {
                return "variable";
            }
            return type.getBV(entity);
        } else if (col == COL_DPROTOTYPE) {
            return entity.isMixedTech()? type.getTechAdvancement().getPrototypeDateName() :
                    type.getTechAdvancement().getPrototypeDateName(entity.isClan());
        } else if (col == COL_DPRODUCTION) {
            return entity.isMixedTech()? type.getTechAdvancement().getProductionDateName() :
                type.getTechAdvancement().getProductionDateName(entity.isClan());
        } else if (col == COL_DCOMMON) {
            return entity.isMixedTech()? type.getTechAdvancement().getCommonDateName() :
                type.getTechAdvancement().getCommonDateName(entity.isClan());
        } else if (col == COL_DEXTINCT) {
            return entity.isMixedTech()? type.getTechAdvancement().getExtinctionDateName() :
                type.getTechAdvancement().getExtinctionDateName(entity.isClan());
        } else if (col == COL_DREINTRO) {
            return entity.isMixedTech()? type.getTechAdvancement().getReintroductionDateName() :
                type.getTechAdvancement().getReintroductionDateName(entity.isClan());
        } else if (col == COL_TLEVEL) {
            if ((null != techManager) && CConfig.getBooleanParam(CConfig.TECH_PROGRESSION)) {
                return type.getSimpleLevel(techManager.getGameYear(), techManager.useClanTechBase(),
                        techManager.getTechFaction()).toString();
            } else {
                return type.getStaticTechLevel().toString();
            }
        } else if (col == COL_TECH) {
            switch(type.getTechBase()) {
            case TechAdvancement.TECH_BASE_ALL:
                return "All";
            case TechAdvancement.TECH_BASE_IS:
                return "IS";
            case TechAdvancement.TECH_BASE_CLAN:
                return "Clan";
            }
        } else if (col == COL_REF) {
            return type.getRulesRefs();
        }
        return "?";
    }

    private static String getDamageString(WeaponType wtype, boolean isAero) {
        // Aeros should print AV instead
        if (isAero) {
            int[] attackValue = new int[RangeType.RANGE_EXTREME + 1];
            attackValue[RangeType.RANGE_SHORT] = wtype.getRoundShortAV();
            attackValue[RangeType.RANGE_MEDIUM] = wtype.getRoundMedAV();
            attackValue[RangeType.RANGE_LONG] = wtype.getRoundLongAV();
            attackValue[RangeType.RANGE_EXTREME] = wtype.getRoundExtAV();
            boolean allEq = true;
            for (int i = 2; i <= wtype.getMaxRange(null); i++) {
                if (attackValue[i - 1] != attackValue[i]) {
                    allEq = false;
                    break;
                }                    
            }
            StringBuilder avString = new StringBuilder();
            avString.append(attackValue[RangeType.RANGE_SHORT]);
            if (!allEq) {
                for (int i = 2; i <= wtype.getMaxRange(null); i++) {
                    avString.append('/').append(attackValue[i]);
                }
            }
            return avString.toString();
        }
        // Damage for non-Aeros
        if (wtype instanceof InfantryWeapon) {
            return Double
                    .toString(((InfantryWeapon) wtype).getInfantryDamage());
        }

        if (wtype.getDamage() == WeaponType.DAMAGE_VARIABLE) {
            return wtype.getDamage(wtype.getShortRange()) + "/"
                    + wtype.getDamage(wtype.getMediumRange()) + "/"
                    + wtype.getDamage(wtype.getLongRange());
        } else if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTERTABLE) {
            if (wtype instanceof HAGWeapon) {
                return wtype.getRackSize() + ""; 
            } else if (wtype instanceof MekMortarWeapon) {
                return "Special";
            } else if (wtype instanceof MissileWeapon) {
                int dmg;
                if (wtype instanceof ThunderBoltWeapon) {
                    switch (wtype.getAmmoType()) {
                        case AmmoType.T_TBOLT_5:
                            return "5";
                        case AmmoType.T_TBOLT_10:
                            return "10";
                        case AmmoType.T_TBOLT_15:
                            return "15";
                        case AmmoType.T_TBOLT_20:
                            return "20";
                        default :
                            return "0";
                    }
                } else if ((wtype instanceof ATMWeapon) 
                        ||(wtype.getAmmoType() == AmmoType.T_SRM)  
                        || (wtype.getAmmoType() == AmmoType.T_SRM_STREAK)) {
                    dmg = 2;
                } else {
                    dmg = 1;
                }
                return dmg + "/msl";
            }            
            return "Cluster";
        } else if (wtype.getDamage() == WeaponType.DAMAGE_ARTILLERY) {
            return wtype.getRackSize() + "A";
        } else if (wtype instanceof UACWeapon) {
            return wtype.getDamage() + "/Shot";            
        } else if (wtype.getDamage() < 0) {
            return "Special";
        } else {
            return Integer.toString(wtype.getDamage());
        }
    }

    public EquipmentTableModel.Renderer getRenderer() {
        return new EquipmentTableModel.Renderer();
    }

    public class Renderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 9054581142945717303L;

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            int actualCol = table.convertColumnIndexToModel(column);
            int actualRow = table.convertRowIndexToModel(row);
            setHorizontalAlignment(getAlignment(actualCol));
            EquipmentType etype = ((EquipmentTableModel) table.getModel()).getType(actualRow);
            if (null != techManager && !techManager.isLegal(etype)) {
                setForeground(UIManager.getColor("Label.disabledForeground"));
            } else {
                setForeground(UIManager.getColor("Label.foreground"));
            }
            return this;
        }
    }

    /**
     * Comparator for numeric columns. Non-numeric values such as "variable" or "special" are sorted
     * alphabetically and placed at the end (if in descending order). Strings ending in "kg" are parsed
     * as numbers and converted to tons.
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
                // Handle potentially commafied number
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
     * Sorter for a series of one or more values separated by slashes. This handles weapon ranges
     * and also deals with multiple damage values.
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
     * Sorter for reference column. References give the page number then the work separated by a comma.
     * Sorts by the work first, then the page number.
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
     * @return     The year represented
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
