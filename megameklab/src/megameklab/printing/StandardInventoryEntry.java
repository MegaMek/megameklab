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

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import megamek.common.RangeType;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.enums.TechBase;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentFlag;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.Sensor;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.options.IOption;
import megamek.common.options.WeaponQuirks;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.SmallCraft;
import megamek.common.units.Tank;
import megamek.common.units.TripodMek;
import megamek.common.weapons.CLIATMWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lasers.VariableSpeedPulseLaserWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.other.innerSphere.ISCenturionWeaponSystem;
import megameklab.util.CConfig;
import megameklab.util.StringUtils;

/**
 * Formats text for an entry in the weapons and equipment inventory section of the record sheet. This is for single
 * pieces of equipment. WeaponBays should use {@link WeaponBayInventoryEntry}.
 */
public class StandardInventoryEntry implements InventoryEntry, Comparable<StandardInventoryEntry> {
    // Cache for whether equipment on a mixed tech unit needs to state explicitly whether
    // it's IS or Clan
    private static final Map<EquipmentType, Boolean> showMixedTechBase = new HashMap<>();

    private final Mounted<?> mount;

    private final String[][] ranges;
    private final boolean isMML;
    private final boolean isATM;
    private final boolean hasInsulator;
    private final boolean hasPulseModule;
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
    private final boolean isSquadSupport;

    private int quantity = 1;

    private final static int MML_LRM_ROW = 1;
    private final static int MML_SRM_ROW = 2;

    private final static int ATM_STANDARD_ROW = 1;
    private final static int ATM_ER_ROW = 2;
    private final static int ATM_HE_ROW = 3;

    private final static String[] SPHEROID_ARCS = { "NOS", "FLS", "FRS", "AFT", "HULL", "ALS", "ARS" };
    private final static String[] AERODYNE_ARCS = { "NOS", "LWG", "RWG", "AFT", "HULL" };
    private final static String[][] MML_RANGE = {
          { "", "", "", "", "" }, formatRange(6, 7, 14, 21), formatRange(0, 3, 6, 9)
    };
    private final static String[][] MML_ARTEMIS_RANGE = { { "", "", "", "", "" },
                                                          { "", "", "", "", "" }, formatRange(6, 7, 14, 21),
                                                          formatRange(0, 3, 6, 9)
    };
    private final static String[][] ATM_RANGE = {
          { "", "", "", "", "" }, formatRange(4, 5, 10, 15),
          formatRange(4, 9, 18, 27), formatRange(0, 3, 6, 9)
    };
    private final static String[][] CENTURION_RANGE = {
          { "", formatCenturion(6, 1), formatCenturion(12, 2), formatCenturion(18, 3) }
    };

    private static String[] formatRange(int min, int sht, int med, int lng) {
        return new String[] {
              (min > 0) ? CConfig.formatScale(min, false) : DASH,
              CConfig.formatScale(sht, false),
              CConfig.formatScale(med, false),
              CConfig.formatScale(lng, false),
              };
    }

    private static String formatCenturion(int susceptible, int resistant) {
        return String.format("%s(%s)", CConfig.formatScale(susceptible, false),
              CConfig.formatScale(resistant, false));
    }

    public StandardInventoryEntry(Mounted<?> m) {
        this.mount = m;
        name = formatName();
        location = formatLocation();
        isRear = m.isRearMounted();
        isTurret = m.isMekTurretMounted();
        isSquadSupport = m.isSquadSupportWeapon();
        isMML = m.getType() instanceof MMLWeapon;
        isATM = m.getType() instanceof ATMWeapon || m.getType() instanceof CLIATMWeapon;
        hasInsulator = hasLinkedEquipment(m, MiscType.F_LASER_INSULATOR);
        hasPulseModule = hasLinkedEquipment(m, MiscType.F_RISC_LASER_PULSE_MODULE);
        hasArtemis = hasLinkedEquipment(m, MiscType.F_ARTEMIS);
        hasArtemisProto = hasLinkedEquipment(m, MiscType.F_ARTEMIS_PROTO);
        hasArtemisV = hasLinkedEquipment(m, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(m, MiscType.F_APOLLO);
        hasCapacitor = hasLinkedEquipment(m, MiscType.F_PPC_CAPACITOR);
        ranges = setRanges();
    }

    public String getUniqueId() {
        final String name = this.mount.getType().getInternalName();
        final String location = this.mount.getEntity().getLocationAbbr(this.mount.getLocation());
        final int position = this.mount.getEntity().slotNumber(this.mount);
        return name + "@" + location + "#" + position;
    }

    public Mounted<?> getMounted() {
        return mount;
    }

    private String[][] setRanges() {
        if (isMML) {
            if (mount.getEntity().isAero()) {
                return mmlAV();
            } else {
                return (hasArtemis || hasArtemisV || hasApollo || hasArtemisProto) ? MML_ARTEMIS_RANGE : MML_RANGE;
            }
        } else if (isATM) {
            return mount.getEntity().isAero() ? atmAV() : ATM_RANGE;
        } else if (mount.getType() instanceof ISCenturionWeaponSystem) {
            return CENTURION_RANGE;
        } else {
            String[] r = new String[4];
            Arrays.fill(r, DASH);
            if (mount.getType() instanceof InfantryWeapon weapon) {
                r[RangeType.RANGE_SHORT] = CConfig.formatScale(weapon.getInfantryRange(), false);
                if (weapon.getInfantryRange() > 0) {
                    r[RangeType.RANGE_MEDIUM] = CConfig.formatScale(weapon.getInfantryRange() * 2, false);
                    r[RangeType.RANGE_LONG] = CConfig.formatScale(weapon.getInfantryRange() * 3, false);
                }
            } else if (mount.getType() instanceof WeaponType weaponType) {
                if (weaponType.getMinimumRange() > 0) {
                    r[RangeType.RANGE_MINIMUM] = CConfig.formatScale(weaponType.getMinimumRange(), false);
                }
                if ((weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.LRM_TORPEDO)
                      || (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_TORPEDO)) {
                    r[RangeType.RANGE_SHORT] = CConfig.formatScale(weaponType.getWShortRange(), false);
                    if (weaponType.getWMediumRange() > weaponType.getWShortRange()) {
                        r[RangeType.RANGE_MEDIUM] = CConfig.formatScale(weaponType.getWMediumRange(), false);
                    }
                    if (weaponType.getWLongRange() > weaponType.getWMediumRange()) {
                        r[RangeType.RANGE_LONG] = CConfig.formatScale(weaponType.getWLongRange(), false);
                    }
                } else {
                    r[RangeType.RANGE_SHORT] = CConfig.formatScale(weaponType.getShortRange(), false);
                    if (weaponType.getMediumRange() > weaponType.getShortRange()) {
                        r[RangeType.RANGE_MEDIUM] = CConfig.formatScale(weaponType.getMediumRange(), false);
                    }
                    if (weaponType.getLongRange() > weaponType.getMediumRange()) {
                        r[RangeType.RANGE_LONG] = CConfig.formatScale(weaponType.getLongRange(), false);
                    }
                }
            } else if ((mount.getType() instanceof MiscType)
                  && (mount.getType().hasFlag(MiscType.F_ECM) || mount.getType().hasFlag(MiscType.F_BAP))) {
                r[RangeType.RANGE_SHORT] = DASH;
                r[RangeType.RANGE_MEDIUM] = DASH;
                r[RangeType.RANGE_LONG] = ewMaxRange();
            }
            String[][] retVal = new String[1][];
            retVal[0] = r;
            return retVal;
        }
    }

    private String ewMaxRange() {
        if (mount.getType().hasFlag(MiscType.F_ANGEL_ECM) && mount.getType().hasFlag(MiscType.F_BA_EQUIPMENT)) {
            return "2";
        } else if (mount.getType().hasFlag(MiscType.F_EW_EQUIPMENT)
              || mount.getType().hasFlag(MiscType.F_WATCHDOG)
              || mount.getType().hasFlag(MiscType.F_NOVA)) {
            return CConfig.formatScale(3, false);
        } else if (mount.getType().hasFlag(MiscType.F_SINGLE_HEX_ECM)) {
            return "0";
        } else if (mount.getType().hasFlag(MiscType.F_ECM)) {
            // Guardian ECM, Clan ECM, non-BA Angel ECM
            return CConfig.formatScale(6, false);
        } else if (mount.getType().hasFlag(MiscType.F_BLOODHOUND)) {
            return CConfig.formatScale(8, false);
        } else if (mount.getType().getInternalName().equals(Sensor.LIGHT_AP)
              || mount.getType().getInternalName().equals(Sensor.IS_BA_LIGHT_AP)
              || mount.getType().getInternalName().equals(Sensor.CL_BA_LIGHT_AP)) {
            return CConfig.formatScale(3, false);
        } else if (mount.getType().getInternalName().equals(Sensor.IS_IMPROVED)
              || mount.getType().getInternalName().equals(Sensor.CL_IMPROVED)) {
            return CConfig.formatScale(2, false);
        } else if (mount.getType().isClan()) {
            return CConfig.formatScale(5, false); // Clan active probe
        } else {
            return CConfig.formatScale(4, false); // Beagle active probe
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
     *
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
        StringBuilder name = getName();
        // For mixed tech units, we want to append the tech base if there is ambiguity, and it isn't already part of
        // the name.
        if (showTechBase()) {
            name.append(mount.getType().isClan() ? " (C)" : " (IS)");
        }
        // Spheroid Small Craft / DropShips use a different location name for aft side weapons
        if (mount.isRearMounted()
              && !(mount.getEntity() instanceof SmallCraft && mount.getEntity().isSpheroid())) {
            name.append(" (R)");
        }
        if (mount.isMekTurretMounted()) {
            name.append(" (T)");
        }
        if (mount.isSponsonTurretMounted()) {
            name.append(" (S)");
        }
        if (mount.isPintleTurretMounted()) {
            name.append(" (P)");
        }
        if (mount instanceof WeaponMounted && mount.getEntity().isBattleArmor()) {
            if (mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_BODY) {
                name.append(" (Body)");
            } else
            if (!mount.isMekTurretMounted() && mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_TURRET) {
                name.append(" (T)");
            }
        }
        if (mount.isSquadSupportWeapon()) {
            name.append(" (SSW: Trooper 1)");
        }
        if (mount.isDWPMounted()) {
            name.append(" (DWP)");
        }
        if (mount.getEntity().isAero()) {
            name.append(" ").append(StringUtils.getAeroEquipmentInfo(mount));
        }
        if (mount.getEntity().isSupportVehicle() && mount.getType() instanceof InfantryWeapon) {
            name.append(" [")
                  .append((int) mount.getSize() * ((InfantryWeapon) mount.getType()).getShots())
                  .append(" shots]");
        }
        return name.toString().trim();
    }

    private StringBuilder getName() {
        String eqName = mount.getName();
        if (eqName.length() > 20) {
            eqName = mount.getShortName();
        }
        // If this is not a mixed tech unit, remove trailing IS or Clan tag in brackets or parentheses,
        // including possible leading space. For mixed tech units this is presumably needed to remove
        // ambiguity.
        if (!mount.getEntity().isMixedTech()) {
            eqName = eqName.replaceAll(" ?[\\[(](Clan|IS)[])]", "");
        }
        return new StringBuilder(eqName);
    }

    /**
     * Determines whether we should indicate whether the equipment is IS or Clan for units with a mixed tech base. Only
     * specify when there is another piece of equipment with the same name but different tech base.
     *
     * @return Whether the tech base should be shown for the equipment
     */
    private boolean showTechBase() {
        if (!mount.getEntity().isMixedTech()
              || (mount.getType().getTechBase() == TechBase.ALL)) {
            return false;
        }
        if (showMixedTechBase.containsKey(mount.getType())) {
            return showMixedTechBase.get(mount.getType());
        }
        final Enumeration<EquipmentType> e = EquipmentType.getAllTypes();
        while (e.hasMoreElements()) {
            final EquipmentType et = e.nextElement();
            if ((et.getTechBase() != mount.getType().getTechBase())
                  && et.getName().equals(mount.getType().getName())
                  && !et.isUnofficial()) {
                showMixedTechBase.put(mount.getType(), true);
                showMixedTechBase.put(et, true);
                return true;
            }
        }
        showMixedTechBase.put(mount.getType(), false);
        return false;
    }

    private String formatLocation() {
        if (mount.getLocation() == Entity.LOC_NONE) {
            return DASH;
        }
        if ((mount.getEntity() instanceof Tank)
              && mount.getLocation() == Tank.LOC_TURRET
              && !((Tank) mount.getEntity()).hasNoDualTurret()) {
            return "RT";
        }
        if (mount.getEntity() instanceof SmallCraft) {
            if (mount.getEntity().isSpheroid()) {
                return SPHEROID_ARCS[mount.isRearMounted() ? mount.getLocation() + 4 : mount.getLocation()];
            } else {
                return AERODYNE_ARCS[mount.getLocation()];
            }
        }
        if (mount.getEntity() instanceof BattleArmor) {
            return BattleArmor.getBaMountLocAbbr(mount.getBaMountLoc());
        }
        return mount.getEntity().joinLocationAbbr(mount.allLocations(), 2);
    }

    /**
     * Handles some special conditions where equipment occupying three or more locations can be abbreviated to conserve
     * space.
     *
     * @param locations The list of locations the equipment occupies
     *
     * @return The abbreviated location string
     */
    private String formatMekLocations(List<Integer> locations) {
        if (locations.stream().allMatch(l -> mount.getEntity().locationIsLeg(l))) {
            if ((mount.getEntity().entityIsQuad() && (locations.size() == 4))
                   || ((mount.getEntity() instanceof TripodMek) && (locations.size() == 3))) {
                return "Legs";
            }
        } else if (locations.stream().allMatch(l -> ((Mek) mount.getEntity()).locationIsTorso(l))) {
            return "R/L/CT";
        }
        return "*";
    }

    @Override
    public boolean isDamaged() {
        return mount.isHit() || mount.isDestroyed() || mount.isMissing();
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
            if (row == MML_LRM_ROW + mmlArtemisRowDelta()) {
                return "LRM";
            } else if (row == MML_SRM_ROW + mmlArtemisRowDelta()) {
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
        }

        if (hasArtemis) {
            return "w/Artemis IV";
        } else if (hasArtemisProto) {
            return "w/Prototype Artemis IV";
        } else if (hasArtemisV) {
            return "w/Artemis V";
        } else if (hasApollo) {
            return "w/Apollo";
        } else if (hasCapacitor) {
            return "w/Capacitor";
        } else if (hasPulseModule) {
            return "w/RISC Laser Module";
        } else if (hasInsulator) {
            return "w/Laser Insulator";
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
            if (hasInsulator) {
                return Integer.toString(mount.getType().getHeat() - 1) + '*';
            } else if (mount.getType().getHeat() > 0) {
                return Integer.toString(mount.getType().getHeat());
            } else {
                return DASH;
            }
        } else if (row == 1) {
            if (hasPulseModule) {
                return Integer.toString(mount.getType().getHeat() + 2);
            } else if (hasCapacitor) {
                return Integer.toString(mount.getType().getHeat() + 5);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    @Override
    public String getDamageField(int row) {
        if (isMML) {
            if (row == MML_LRM_ROW + mmlArtemisRowDelta()) {
                return "1/Msl";
            } else if (row == MML_SRM_ROW + mmlArtemisRowDelta()) {
                return "2/Msl";
            } else if (row == 0) {
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
            return "[PB]";
        } else if (mount.getType() instanceof ISCenturionWeaponSystem) {
            return "0";
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BA_MANIPULATOR)) {
            // TODO : Put capacity of cargo lifting manipulator here when the implementation is
            // TODO : corrected to allow capacity to be assigned
            return "";
        } else if (row == 0) {
            return StringUtils.getEquipmentInfo(mount.getEntity(), mount);
        } else if (row == 1 && hasCapacitor) {
            return StringUtils.getEquipmentInfo(mount.getEntity(), mount, true);
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
                return String.valueOf(((WeaponType) mount.getType()).getRoundShortAV() + aeroAVMod(mount));
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
            if ((row == 0) && (mount instanceof WeaponMounted)
                  && ((WeaponType) mount.getType()).getMaxRange((WeaponMounted) mount) >= WeaponType.RANGE_MED) {
                return String.valueOf(((WeaponType) mount.getType()).getRoundMedAV() + aeroAVMod(mount));
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
            if ((row == 0) && (mount instanceof WeaponMounted)
                  && ((WeaponType) mount.getType()).getMaxRange((WeaponMounted) mount) >= WeaponType.RANGE_LONG) {
                return String.valueOf(((WeaponType) mount.getType()).getRoundLongAV() + aeroAVMod(mount));
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
            if ((row == 0) && (mount instanceof WeaponMounted)
                  && ((WeaponType) mount.getType()).getMaxRange((WeaponMounted) mount) >= WeaponType.RANGE_EXT) {
                return String.valueOf(((WeaponType) mount.getType()).getRoundExtAV() + aeroAVMod(mount));
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

    @Override
    public String getModField(int row, boolean baseOnly) {
        if (baseOnly) {
            if (row != 0) {
                return "";
            }
            var mod = mount.getType().getToHitModifier(mount);
            return (mod != 0) ? "%+d".formatted(mod).replace("-", MINUS) : "0";
        }
        var hasTargComp = mount.getEntity().hasTargComp();
        boolean hasAes = mount.getEntity().hasFunctionalArmAES(mount.getLocation());

        if (hasPulseModule) {
            var mod = 0;
            if (hasTargComp) {
                mod--;
            }
            if (hasAes) {
                mod--;
            }
            if (row == 0) {
                return (mod != 0) ? "%+d".formatted(mod).replace("-", MINUS) : DASH;
            } else if (row == 1) {
                return (mod != 0) ? "%+d".formatted(mod - 2).replace("-", MINUS) : DASH;
            } else {
                return "";
            }
        }

        if (row != 0) {
            return "";
        }

        if (mount.getType() instanceof VariableSpeedPulseLaserWeapon) {
            return "*";
        }

        boolean explicitZero = false;

        var mod = mount.getType().getToHitModifier(mount);

        var linked = mount.getLinkedBy();
        if (linked != null) {
            if (hasArtemisV || hasApollo) {
                mod--;
                explicitZero = true;
            }
        }

        if (
              hasTargComp
                    && mount instanceof WeaponMounted wm
                    && wm.getType().hasFlag(WeaponType.F_DIRECT_FIRE)
                    && !wm.getType().hasAnyFlag(WeaponType.F_CWS, WeaponType.F_TASER)
        ) {
            mod--;
            explicitZero = true;
        }

        if (
              hasAes
                    && (
                    mount instanceof WeaponMounted
                          || (mount instanceof MiscMounted mm && mm.getType().hasFlag(MiscTypeFlag.F_CLUB))
              )
                    && mount.getSecondLocation() == Entity.LOC_NONE
        ) {
            mod--;
            explicitZero = true;
        }

        return (mod != 0) ? "%+d".formatted(mod).replace("-", MINUS) : (explicitZero ? DASH : "");
    }

    @Override
    public int nRows() {
        if (isMML) {
            return 3 + mmlArtemisRowDelta();
        } else if (isATM) {
            return 4;
        } else if (hasArtemis
              || hasArtemisV
              || hasApollo
              || hasArtemisProto
              || hasCapacitor
              || hasPulseModule
              || hasInsulator) {
            return 2;
        }
        return 1;
    }

    /** @return 1 when this entry has Artemis, 0 otherwise (to be used for MML only to account for the additional. row). */
    private int mmlArtemisRowDelta() {
        return (hasArtemis || hasArtemisV || hasApollo || hasArtemisProto) ? 1 : 0;
    }

    private boolean hasLinkedEquipment(Mounted<?> eq, EquipmentFlag flag) {
        return (eq.getLinkedBy() != null) && (eq.getLinkedBy().getType() instanceof MiscType)
              && eq.getLinkedBy().getType().hasFlag(flag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        StandardInventoryEntry that = (StandardInventoryEntry) o;
        return isRear == that.isRear &&
              isTurret == that.isTurret &&
              isSquadSupport == that.isSquadSupport &&
              name.equals(that.name) &&
              location.equals(that.location) &&
              hasInsulator == that.hasInsulator &&
              hasPulseModule == that.hasPulseModule &&
              hasCapacitor == that.hasCapacitor &&
              isDamaged() == that.isDamaged();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, isRear, isTurret, isSquadSupport);
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

    @Override
    public boolean hasQuirks() {
        return mount.countQuirks() > 0;
    }

    @Override
    public String getQuirksField() {
        StringBuilder sb = new StringBuilder();
        final WeaponQuirks quirks = mount.getQuirks();
        for (IOption quirk : quirks.activeQuirks()) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(quirk.getDisplayableName());
        }
        return sb.toString();
    }
}
