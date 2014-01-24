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
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.infantry.InfantryWeapon;

/**
 * this model was not being used by anything, so I totally redid so that it can
 * be used as the model for the equipment tab. It will be a sortable, filterable
 * table of equipment, similar to the tables in MHQ
 *
 * @author Jay lawson
 */
public class EquipmentTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5207167419079014157L;

    public final static int COL_NAME = 0;
    public final static int COL_DAMAGE = 1;
    public final static int COL_SPECIAL = 2;
    public final static int COL_HEAT = 3;
    public final static int COL_MRANGE = 4;
    public final static int COL_RANGE = 5;
    public final static int COL_SHOTS = 6;
    public final static int COL_TECH = 7;
    public final static int COL_TRATING = 8;
    public final static int COL_AVSL = 9;
    public final static int COL_AVSW = 10;
    public final static int COL_AVCL = 11;
    public final static int COL_DINTRO = 12;
    public final static int COL_DEXTINCT = 13;
    public final static int COL_DREINTRO = 14;
    public final static int COL_COST = 15;
    public final static int COL_CREW = 16;
    public final static int COL_BV = 17;
    public final static int COL_TON = 18;
    public final static int COL_CRIT = 19;
    public final static int N_COL = 20;

    private ArrayList<EquipmentType> data = new ArrayList<EquipmentType>();
    private Entity entity = null;

    public EquipmentTableModel(Entity e) {
        entity = e;
    }

    public int getRowCount() {
        return data.size();
    }

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
                return "Crit";
            case COL_CREW:
                return "Crew";
            case COL_TECH:
                return "Base";
            case COL_TRATING:
                return "Rating";
            case COL_AVSL:
                return "SL";
            case COL_AVSW:
                return "SW";
            case COL_AVCL:
                return "CL";
            case COL_COST:
                return "Cost";
            case COL_SHOTS:
                return "Shots";
            case COL_BV:
                return "BV";
            case COL_DINTRO:
                return "Intro";
            case COL_DEXTINCT:
                return "Extinct";
            case COL_DREINTRO:
                return "Re-intro";
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
                return 50;
                /*
                 * case COL_TRATING: case COL_COST: return 20;
                 */
            case COL_AVSL:
            case COL_AVSW:
            case COL_AVCL:
            case COL_TON:
            case COL_CRIT:
            case COL_MRANGE:
                return 5;
            default:
                return 30;
        }
    }

    public int getAlignment(int col) {
        switch (col) {
            case COL_NAME:
                // case COL_DATES:
                return SwingConstants.LEFT;
            default:
                return SwingConstants.CENTER;
        }
    }

    public String getTooltip(int row, int col) {
        //EquipmentType type = data.get(row);
        switch (col) {
            default:
                return null;
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

    public Object getValueAt(int row, int col) {
        EquipmentType type;
        WeaponType wtype = null;
        AmmoType atype = null;
        //MiscType mtype = null;
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
            //mtype = (MiscType) type;
        }
        DecimalFormat formatter = new DecimalFormat();

        if (col == COL_NAME) {
            return UnitUtil.trimInfantryWeaponNames(type.getName());
        }
        if (col == COL_DAMAGE) {
            if (null != wtype) {
                return getDamageString(wtype);
            } else {
                return "-";
            }
        }
        if (col == COL_SPECIAL) {
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
                        || type.hasFlag(WeaponType.F_INFERNO)) {
                    special += "F";
                }
            }
            return special;
        }
        if (col == COL_CREW) {
            String special = "";
            if (type instanceof InfantryWeapon) {
                special += Integer.toString(((InfantryWeapon) type).getCrew());
                if (type.hasFlag(WeaponType.F_INF_ENCUMBER)) {
                    special += "E";
                }
            }
            return special;
        }
        if (col == COL_HEAT) {
            if (null != wtype) {
                return Integer.toString(wtype.getHeat());
            } else {
                return "-";
            }
        }
        if (col == COL_SHOTS) {
            if (null != atype) {
                return Integer.toString(atype.getShots());
            } else {
                return "-";
            }
        }
        if (col == COL_RANGE) {
            if (null != wtype) {
                if (wtype instanceof InfantryWeapon) {
                    return ((InfantryWeapon) wtype).getInfantryRange();
                }
                return wtype.getShortRange() + "/" + wtype.getMediumRange()
                        + "/" + wtype.getLongRange();
            } else {
                return "-";
            }
        }
        if (col == COL_MRANGE) {
            if (null != wtype) {
                int minRange = wtype.getMinimumRange();
                if (minRange < 0) {
                    minRange = 0;
                }
                return Integer.toString(minRange);
            } else {
                return "-";
            }
        }
        if (col == COL_TON) {
            if ((entity instanceof BattleArmor) && (atype != null)){
                return (atype.getKgPerShot() * atype.getShots())/1000;
            } else {
                return type.getTonnage(entity);
            }
        }
        if (col == COL_CRIT) {
            return type.getCriticals(entity);
        }
        if (col == COL_TRATING) {
            return type.getTechRatingName();
        }
        if (col == COL_COST) {
            return formatter.format(type
                    .getCost(entity, false, Entity.LOC_NONE));
        }
        if (col == COL_BV) {
            return type.getBV(entity);
        }
        if (col == COL_DINTRO) {
            return EquipmentType.getEquipDateAsString(type
                    .getIntroductionDate());
        }
        if (col == COL_DEXTINCT) {
            return EquipmentType.getEquipDateAsString(type.getExtinctionDate());
        }
        if (col == COL_DREINTRO) {
            return EquipmentType.getEquipDateAsString(type
                    .getReintruductionDate());
        }
        if (col == COL_AVSL) {
            return type.getAvailabilityName(EquipmentType.ERA_SL);
        }
        if (col == COL_AVSW) {
            return type.getAvailabilityName(EquipmentType.ERA_SW);
        }
        if (col == COL_AVCL) {
            return type.getAvailabilityName(EquipmentType.ERA_CLAN);
        }
        if (col == COL_TECH) {
            return TechConstants.isClan(type.getTechLevel(entity.getTechLevelYear())) ? "Clan" : "IS";
        }
        return "?";
    }

    private static String getDamageString(WeaponType wtype) {
        if (wtype instanceof InfantryWeapon) {
            return Double
                    .toString(((InfantryWeapon) wtype).getInfantryDamage());
        }
        if (wtype.getDamage() == WeaponType.DAMAGE_VARIABLE) {
            return wtype.getDamage(wtype.getShortRange()) + "/"
                    + wtype.getDamage(wtype.getMediumRange()) + "/"
                    + wtype.getDamage(wtype.getLongRange());
        } else if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTERTABLE) {
            return "Cluster";
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
            setOpaque(true);
            // setFont(new Font("Arial", Font.PLAIN, 12));
            int actualCol = table.convertColumnIndexToModel(column);
            int actualRow = table.convertRowIndexToModel(row);
            setHorizontalAlignment(getAlignment(actualCol));
            setToolTipText(getTooltip(actualRow, actualCol));
            return this;
        }
    }

}