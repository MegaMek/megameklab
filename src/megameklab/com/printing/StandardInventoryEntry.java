/*
 * MegaMekLab - unit design companion of MegaMek
 * Copyright (C) 2020 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package megameklab.com.printing;

import megamek.common.*;
import megamek.common.weapons.CLIATMWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.other.ISCenturionWeaponSystem;
import megameklab.com.util.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * Formats text for an entry in the weapons and equipment inventory section of the record sheet.
 * This is for single pieces of equipment. WeaponBays should use {@link WeaponBayInventoryEntry}.
 */
public class StandardInventoryEntry implements InventoryEntry, Comparable<StandardInventoryEntry> {
    private Mounted mount;

    private final String[][] ranges;
    private final boolean isMML;
    private final boolean isATM;
    private final boolean hasArtemis;
    private final boolean hasArtemisProto;
    private final boolean hasArtemisV;
    private final boolean hasApollo;
    private final boolean hasCapacitor;
    // Saved as member fields for hash and equals
    private final String name;
    private final String location;
    private final boolean isRear;
    private final boolean isTurret;

    private int quantity = 1;

    private static final int MML_LRM_ROW = 1;
    private static final int MML_SRM_ROW = 2;

    private static final int ATM_STANDARD_ROW = 1;
    private static final int ATM_ER_ROW = 2;
    private static final int ATM_HE_ROW = 3;

    private static final String[] SPHEROID_ARCS = { "NOS", "FLS", "FRS", "AFT", "HULL", "ALS", "ARS" };
    private static final String[][] MML_RANGE = {
            {"", "", "", "", ""}, {"6", "7", "14", "21"}, {DASH, "3", "6", "9"}
    };
    private static final String[][] ATM_RANGE = {
            {"", "", "", "", ""}, {"4", "5", "10", "15"}, {"4", "9", "18", "27"}, {DASH, "3", "6", "9"}
    };
    private static final String[][] CENTURION_RANGE = {
            {"", "6(1)", "12(2)", "18(3)"}
    };

    public StandardInventoryEntry(Mounted m) {
        this.mount = m;
        name = formatName();
        location = formatLocation();
        isRear = m.isRearMounted();
        isTurret = m.isMechTurretMounted();
        isMML = m.getType() instanceof MMLWeapon;
        isATM = m.getType() instanceof ATMWeapon || m.getType() instanceof CLIATMWeapon;
        hasArtemis = hasLinkedEquipment(m, MiscType.F_ARTEMIS);
        hasArtemisProto = hasLinkedEquipment(m, MiscType.F_ARTEMIS_PROTO);
        hasArtemisV = hasLinkedEquipment(m, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(m, MiscType.F_APOLLO);
        hasCapacitor = hasLinkedEquipment(m, MiscType.F_PPC_CAPACITOR);
        ranges = setRanges();
    }

    private String[][] setRanges() {
        if (isMML) {
            return mount.getEntity().isAero() ? mmlAV() : MML_RANGE;
        } else if (isATM) {
            return mount.getEntity().isAero() ? atmAV() : ATM_RANGE;
        } else if (mount.getType() instanceof ISCenturionWeaponSystem) {
            return CENTURION_RANGE;
        } else {
            String[] r = new String[4];
            Arrays.fill(r, DASH);
            if (mount.getType() instanceof WeaponType) {
                final WeaponType wtype = (WeaponType) mount.getType();
                if (wtype.getMinimumRange() > 0) {
                    r[RangeType.RANGE_MINIMUM] = String.valueOf(wtype.getMinimumRange());
                }
                r[RangeType.RANGE_SHORT] = String.valueOf(wtype.getShortRange());
                r[RangeType.RANGE_MEDIUM] = String.valueOf(wtype.getMediumRange());
                r[RangeType.RANGE_LONG] = String.valueOf(wtype.getLongRange());
            }
            String[][] retVal = new String[1][];
            retVal[0] = r;
            return retVal;
        }
    }

    /**
     * @return The AV values for each MML munition type
     */
    private String[][] mmlAV() {
        String[][] retVal = new String[3][];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = new String[5];
            Arrays.fill(retVal[i], i == 0 ? "" : DASH);
        }
        int avMod = aeroAVMod(mount);
        // The base AV is the number of missiles.
        int av = (int) ((WeaponType) mount.getType()).getShortAV() + avMod;
        retVal[MML_LRM_ROW][RangeType.RANGE_SHORT] = String.valueOf(av);
        retVal[MML_SRM_ROW][RangeType.RANGE_SHORT] = String.valueOf(av * 2);
        retVal[MML_LRM_ROW][RangeType.RANGE_MEDIUM] = String.valueOf(av);
        retVal[MML_LRM_ROW][RangeType.RANGE_LONG] = String.valueOf(av);
        return retVal;
    }

    /**
     * For AV for various ATM munitions, see footnote on TW, p. 304.
     * @return The AV values for each ATM munition type
     */
    private String[][] atmAV() {
        String[][] retVal = new String[4][];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = new String[5];
            Arrays.fill(retVal[i], i == 0 ? "" : DASH);
        }
        int avMod = aeroAVMod(mount);
        double av = ((WeaponType) mount.getType()).getShortAV() + avMod;
        retVal[ATM_STANDARD_ROW][RangeType.RANGE_SHORT] = String.valueOf((int) av);
        retVal[ATM_ER_ROW][RangeType.RANGE_SHORT] = String.valueOf((int) Math.ceil(av * 0.5));
        retVal[ATM_HE_ROW][RangeType.RANGE_SHORT] = String.valueOf((int) Math.ceil(av * 1.5));
        retVal[ATM_STANDARD_ROW][RangeType.RANGE_MEDIUM] = String.valueOf((int) av);
        retVal[ATM_ER_ROW][RangeType.RANGE_MEDIUM] = String.valueOf((int) Math.ceil(av * 0.5));
        retVal[ATM_ER_ROW][RangeType.RANGE_LONG] = String.valueOf((int) Math.ceil(av * 0.5));
        retVal[ATM_ER_ROW][RangeType.RANGE_EXTREME] = String.valueOf((int) Math.ceil(av * 0.5));
        return retVal;
    }

    private String formatName() {
        String eqName = mount.getType().getName();
        if (eqName.length() > 20) {
            eqName = mount.getType().getShortName();
        }
        // Remove trailing IS or Clan tag in brackets or parentheses, including possible leading space
        StringBuilder name = new StringBuilder(eqName.replaceAll(" ?[\\[(](Clan|IS)[])]", ""));
        if (mount.getEntity().isMixedTech() && (mount.getType().getTechBase() != ITechnology.TECH_BASE_ALL)) {
            name.append(mount.getType().isClan() ? " (Clan)" : " (IS)");
        }
        // Small Craft/Dropships use a different location name for aft side weapons
        if (mount.isRearMounted() && !(mount.getEntity() instanceof SmallCraft)) {
            name.append(" (R)");
        }
        if (mount.isMechTurretMounted()) {
            name.append(" (T)");
        }
        if (mount.isSponsonTurretMounted()) {
            name.append(" (S)");
        }
        if (mount.isPintleTurretMounted()) {
            name.append(" (P)");
        }
        if (mount.getEntity().isAero()) {
            name.append(" ").append(StringUtils.getEquipmentInfo((Aero) mount.getEntity(), mount));
        }
        return name.toString();
    }

    private String formatLocation() {
        if ((mount.getEntity() instanceof Tank)
                && mount.getLocation() == Tank.LOC_TURRET
                && !((Tank) mount.getEntity()).hasNoDualTurret()) {
            return "RT";
        }
        if (mount.getEntity() instanceof SmallCraft
                && (((SmallCraft) mount.getEntity()).isSpheroid())) {
            return SPHEROID_ARCS[mount.isRearMounted() ? mount.getLocation() + 4 : mount.getLocation()];
        }
        return mount.getEntity().getLocationAbbr(mount.getLocation());
    }

    @Override
    public String getQuantityField(int row) {
        if (row == 0) {
            return String.valueOf(quantity);
        }
        return "";
    }

    @Override
    public String getNameField(int row) {
        if (row == 0) {
            return name;
        }
        if (isMML) {
            if (row == MML_LRM_ROW) {
                return "LRM";
            } else if (row == MML_SRM_ROW) {
                return "SRM";
            }
        } else if (isATM) {
            if (row == ATM_STANDARD_ROW) {
                return "Standard";
            } else if (row == ATM_ER_ROW) {
                return "Extended Range";
            } else if (row == ATM_HE_ROW) {
                return "High Explosive";
            }
        } else if (hasArtemis) {
            return "w/Artemis IV";
        } else if (hasArtemisProto) {
            return "w/Prototype Artemis IV";
        } else if (hasArtemisV) {
            return "w/Artemis V";
        } else if (hasApollo) {
            return "w/Apollo";
        } else if (hasCapacitor) {
            return "w/Capacitor";
        }
        return "";
    }

    @Override
    public String getLocationField(int row) {
        if (row == 0) {
            return location;
        } else {
            return "";
        }
    }

    @Override
    public String getHeatField(int row) {
        if (row == 0) {
            if (mount.getType().getHeat() > 0) {
                return Integer.toString(mount.getType().getHeat());
            } else {
                return DASH;
            }
        } else {
            return "";
        }
    }

    @Override
    public String getDamageField(int row) {
        if (isMML) {
            if (row == MML_LRM_ROW) {
                return "1/Msl";
            } else if (row == MML_SRM_ROW){
                return "2/Msl";
            } else {
                return "[M,C,S]";
            }
        } else if (isATM) {
            if (row == ATM_STANDARD_ROW) {
                return "2/Msl";
            } else if (row == ATM_ER_ROW) {
                return "1/Msl";
            } else if (row == ATM_HE_ROW) {
                return "3/Msl";
            } else {
                return "[M,C,S]";
            }
        } else if (mount.getType() instanceof WeaponType && mount.getType().hasFlag(WeaponType.F_AMS)) {
            return "[PD]";
        } else if (mount.getType() instanceof ISCenturionWeaponSystem) {
            return "0";
        } else if (row == 0) {
            return StringUtils.getEquipmentInfo(mount.getEntity(), mount);
        }
        return "";
    }

    @Override
    public String getMinField(int row) {
        if (row >= ranges.length) {
            return "";
        }
        return ranges[row][RangeType.RANGE_MINIMUM];
    }

    @Override
    public String getShortField(int row) {
        if (mount.getEntity().isAero() && !isMML && !isATM) {
            if ((row == 0) && (mount.getType() instanceof WeaponType)) {
                return String.valueOf((int) ((WeaponType) mount.getType()).getShortAV() + aeroAVMod(mount));
            } else if (row == 0) {
                return DASH;
            } else {
                return "";
            }
        }
        if (row < ranges.length) {
            return ranges[row][RangeType.RANGE_SHORT];
        }
        return "";
    }

    @Override
    public String getMediumField(int row) {
        if (mount.getEntity().isAero() && !isMML && !isATM) {
            if ((row == 0) && (mount.getType() instanceof WeaponType)
                    && ((WeaponType) mount.getType()).maxRange >= WeaponType.RANGE_MED) {
                return String.valueOf((int) ((WeaponType) mount.getType()).getMedAV() + aeroAVMod(mount));
            } else if (row == 0) {
                return DASH;
            } else {
                return "";
            }
        }
        if (row < ranges.length) {
            return ranges[row][RangeType.RANGE_MEDIUM];
        }
        return "";
    }

    @Override
    public String getLongField(int row) {
        if (mount.getEntity().isAero() && !isMML && !isATM) {
            if ((row == 0) && (mount.getType() instanceof WeaponType)
                    && ((WeaponType) mount.getType()).maxRange >= WeaponType.RANGE_LONG) {
                return String.valueOf((int) ((WeaponType) mount.getType()).getLongAV() + aeroAVMod(mount));
            } else if (row == 0) {
                return DASH;
            } else {
                return "";
            }
        }
        if (row < ranges.length) {
            return ranges[row][RangeType.RANGE_LONG];
        }
        return "";
    }

    @Override
    public String getExtremeField(int row) {
        if (mount.getEntity().isAero() && !isMML && !isATM) {
            if ((row == 0) && (mount.getType() instanceof WeaponType)
                    && ((WeaponType) mount.getType()).maxRange >= WeaponType.RANGE_EXT) {
                return String.valueOf((int) ((WeaponType) mount.getType()).getExtAV() + aeroAVMod(mount));
            } else if (row == 0) {
                return DASH;
            } else {
                return "";
            }
        }
        if (row < ranges.length) {
            return ranges[row][RangeType.RANGE_EXTREME];
        }
        return "";
    }

    public int nRows() {
        if (isMML) {
            return 3;
        } else if (isATM) {
            return 4;
        } else if (hasArtemis || hasArtemisV || hasApollo || hasArtemisProto || hasCapacitor) {
            return 2;
        }
        return 1;
    }

    private boolean hasLinkedEquipment(Mounted eq, BigInteger flag) {
        return (eq.getLinkedBy() != null) && (eq.getLinkedBy().getType() instanceof MiscType)
                && eq.getLinkedBy().getType().hasFlag(flag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardInventoryEntry that = (StandardInventoryEntry) o;
        return isRear == that.isRear &&
                isTurret == that.isTurret &&
                name.equals(that.name) &&
                location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, isRear, isTurret);
    }

    @Override
    public int compareTo(StandardInventoryEntry o) {
        return Integer.compare(mount.getLocation(), o.mount.getLocation());
    }

    public void incrementQty() {
        quantity++;
    }

    @Override
    public boolean indentMultiline() {
        return true;
    }
}
