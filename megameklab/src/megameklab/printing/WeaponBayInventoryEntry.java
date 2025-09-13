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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Aero;
import megamek.common.units.Dropship;
import megamek.common.util.AeroAVModCalculator;
import megamek.common.weapons.CLIATMWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;

/**
 * Formats text for an entry for a weapon bay in the weapons and equipment inventory section of the record sheet.
 */
public class WeaponBayInventoryEntry implements InventoryEntry {
    private final Aero ship;
    private final WeaponBayText bay;
    private final boolean isCapital;
    private final boolean isAR10;
    private String location = "";
    private int heat;
    private double baySRV, bayMRV, bayLRV, bayERV;
    private double standardBaySRV, standardBayMRV, standardBayLRV, standardBayERV;
    private boolean artemisIV = false;
    private boolean artemisV = false;
    private boolean apollo = false;
    private final List<String> weaponNames = new ArrayList<>();
    private final List<String> quantities = new ArrayList<>();
    public final int index;

    public WeaponBayInventoryEntry(Aero ship, int index, WeaponBayText bay, boolean isCapital) {
        this.ship = ship;
        this.index = index;
        this.bay = bay;
        this.isCapital = isCapital;
        this.isAR10 = bay.weapons.keySet().stream().anyMatch(w -> w.getAmmoType() == AmmoType.AmmoTypeEnum.AR10);
        processBay();
    }

    @Override
    public String getUniqueId() {
        return "bay_"+String.valueOf(index);
    }

    private void processBay() {
        for (WeaponType weaponType : bay.weapons.keySet()) {
            int numWeapons = bay.weapons.get(weaponType);
            heat += weaponType.getHeat() * numWeapons;
            if (isCapital) {
                baySRV += weaponType.getShortAV() * numWeapons;
                bayMRV += weaponType.getMedAV() * numWeapons;
                bayLRV += weaponType.getLongAV() * numWeapons;
                bayERV += weaponType.getExtAV() * numWeapons;
            } else if (weaponType instanceof ATMWeapon || weaponType instanceof CLIATMWeapon) {
                // The default AVs assume standard ammo. Per footnote on TW, p. 304, the SRV is multiplied
                // by 1.5 for HE and the long and extreme by 0.5 for ER, both rounded up.
                double av;
                if (weaponType instanceof CLIATMWeapon) {
                    av = weaponType.getShortAV() * numWeapons;
                } else {
                    av = Math.ceil(weaponType.getShortAV() * numWeapons * 0.5);
                }
                baySRV += Math.round(Math.ceil(av * 1.5) / 10.0);
                bayMRV += Math.round(av / 10.0);
                long bayRV = Math.round(Math.ceil(av * 0.5) / 10.0);
                bayLRV += bayRV;
                bayERV += bayRV;
                standardBaySRV += Math.ceil(av * 1.5);
                standardBayMRV += av;
                standardBayLRV += Math.ceil(av * 0.5);
                standardBayERV += Math.ceil(av * 0.5);
            } else {
                int bonus = 0;
                if (bay.augmentations.containsKey(weaponType)) {
                    bonus = bay.augmentations.get(
                                weaponType).entrySet().stream()
                          .mapToInt(e -> AeroAVModCalculator.calculateBonus(weaponType, e.getKey(), true)
                                * e.getValue()).sum();
                }
                if (weaponType.getShortAV() > 0) {
                    double av = weaponType.getShortAV() * numWeapons + bonus;
                    if (weaponType instanceof MMLWeapon) {
                        av *= 2; // SRM ammo
                    }
                    baySRV += Math.round(av / 10.0);
                    standardBaySRV += weaponType.getShortAV() * numWeapons + bonus;
                }
                if (weaponType.getMedAV() > 0) {
                    bayMRV += Math.round((weaponType.getMedAV() * numWeapons + bonus) / 10);
                    standardBayMRV += weaponType.getMedAV() * numWeapons + bonus;
                }
                if (weaponType.getLongAV() > 0) {
                    bayLRV += Math.round((weaponType.getLongAV() * numWeapons + bonus) / 10);
                    standardBayLRV += weaponType.getLongAV() * numWeapons + bonus;
                }
                if (weaponType.getExtAV() > 0) {
                    bayERV += Math.round((weaponType.getExtAV() * numWeapons + bonus) / 10);
                    standardBayERV += weaponType.getExtAV() * numWeapons + bonus;
                }
            }
        }
        // PPC capacitors in bays are considered always charged
        if (!isCapital) {
            for (Map<EquipmentType, Integer> entry : bay.augmentations.values()) {
                heat += entry.entrySet().stream()
                      .filter(e -> e.getKey().hasFlag(MiscType.F_PPC_CAPACITOR))
                      .mapToInt(e -> e.getValue() * 5).sum();
            }
        }
        artemisIV = bay.countAugmentations(MiscType.F_ARTEMIS) > 0;
        /* Per the rules, if any have artemis or apollo, all eligible launchers must have it, and the types
         * can't be mixed. But it's no great effort to accommodate illegal designs. Based on precedents elsewhere
         * in the rules, every weapon in the bay would require Artemis V or Apollo to get the to-hit bonus.
         */
        artemisV = bay.allHaveAugmentation(MiscType.F_ARTEMIS_V);
        apollo = bay.allHaveAugmentation((MiscType.F_APOLLO));

        for (WeaponType weaponType : bay.weapons.keySet()) {
            StringJoiner locString = new StringJoiner("/");
            for (int i = 0; i < bay.loc.size(); i++) {
                // Show official names of DropShip side arcs. Rear-mounted wing bays
                // are indicated by (R) appended to the name field.
                if (ship instanceof Dropship
                      && (bay.loc.get(i) == Dropship.LOC_LEFT_WING
                      || bay.loc.get(i) == Dropship.LOC_RIGHT_WING)) {
                    if (ship.isSpheroid()) {
                        if (bay.loc.get(i) == Dropship.LOC_LEFT_WING) {
                            locString.add(bay.rear ? "ALS" : "FLS");
                        } else {
                            locString.add(bay.rear ? "ARS" : "FRS");
                        }
                    } else {
                        if (bay.loc.get(i) == Dropship.LOC_LEFT_WING) {
                            locString.add("LW");
                        } else {
                            locString.add("RW");
                        }
                    }
                } else {
                    locString.add(ship.getLocationAbbr(bay.loc.get(i)));
                }
            }
            location = locString.toString();
            StringBuilder nameString = new StringBuilder(weaponType.getShortName());
            if (bay.weaponAmmo.containsKey(weaponType)) {
                List<Mounted<?>> ammoList = bay.weaponAmmo.get(weaponType);
                Mounted<?> ammo = ammoList.get(0);
                if (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.AR10) {
                    nameString.append(" (");

                    StringJoiner ammoDetails = new StringJoiner(", ");
                    ammoList.forEach(e -> {
                        if (e instanceof AmmoMounted am) {
                            ammoDetails.add(am.getBaseShotsLeft() + " " + am.getShortName());
                        }
                    });
                    nameString.append(ammoDetails);
                    nameString.append(")");
                } else if (weaponType.isCapital() && weaponType.hasFlag(WeaponType.F_MISSILE)) {
                    nameString.append(" (").append(ammo.getBaseShotsLeft()).append(" missiles)");
                } else {
                    nameString.append(" (").append(ammo.getBaseShotsLeft()).append(" rounds)");
                }
            }
            int capacitors = bay.countAugmentations(weaponType, MiscType.F_PPC_CAPACITOR);
            if (capacitors == bay.weapons.get(weaponType)) {
                nameString.append(" w/Capacitor");
            } else if (capacitors > 0) {
                nameString.append(" w/").append(capacitors);
                if (capacitors > 1) {
                    nameString.append(" Capacitors");
                } else {
                    nameString.append(" Capacitor");
                }
            }
            if (weaponNames.isEmpty()) {
                if (artemisIV) {
                    nameString.append("*");
                } else if (artemisV) {
                    nameString.append(DAGGER);
                } else if (apollo) {
                    nameString.append(DOUBLE_DAGGER);
                }
                if (bay.weapons.size() > 1) {
                    nameString.append(",");
                }
            }
            weaponNames.add(nameString.toString());
            quantities.add(String.valueOf(bay.weapons.get(weaponType)));
        }
    }

    @Override
    public int nRows() {
        return bay.weapons.size();
    }

    @Override
    public boolean isDamaged() {
        return false;
    }

    @Override
    public String getQuantityField(int row) {
        if (row < quantities.size()) {
            return quantities.get(row);
        }
        return "";
    }

    @Override
    public String getNameField(int row) {
        if (row == 0 && bay.rear && !ship.isSpheroid()) {
            return quantities.get(row) + " " + weaponNames.get(row) + " (R)";
        } else if (row < weaponNames.size()) {
            return quantities.get(row) + " " + weaponNames.get(row);
        }
        return "";
    }

    @Override
    public String getLocationField(int row) {
        if (row == 0) {
            return location;
        }
        return "";
    }

    @Override
    public String getHeatField(int row) {
        if (row == 0) {
            return String.valueOf(heat);
        }
        return "";
    }

    @Override
    public String getDamageField(int row) {
        return "";
    }

    @Override
    public String getMinField(int row) {
        return "";
    }

    private String formatAV(double av, double stdAV) {
        if (isAR10) {
            return "*"; // depends on missile type loaded
        }
        if (av + stdAV == 0) {
            return DASH;
        }
        if (isCapital) {
            return String.valueOf((int) av);
        }
        return ((int) av) + " (" + ((int) stdAV) + ")";
    }

    @Override
    public String getShortField(int row) {
        if (row == 0) {
            return formatAV(baySRV, standardBaySRV);
        }
        return "";
    }

    @Override
    public String getMediumField(int row) {
        if (row == 0) {
            return formatAV(bayMRV, standardBayMRV);
        }
        return "";
    }

    @Override
    public String getLongField(int row) {
        if (row == 0) {
            return formatAV(bayLRV, standardBayLRV);
        }
        return "";
    }

    @Override
    public String getExtremeField(int row) {
        if (row == 0) {
            return formatAV(bayERV, standardBayERV);
        }
        return "";
    }

    @Override
    public String getModField(int row, boolean baseOnly) {
        // todo: get someone who knows what an aerospace is to implement this
        return "";
    }

    @Override
    public boolean indentMultiline() {
        return true;
    }

    @Override
    public boolean hasQuirks() {
        return false;
    }

    @Override
    public String getQuirksField() {
        return "";
    }

    public boolean hasArtemisIV() {
        return artemisIV;
    }

    public boolean hasArtemisV() {
        return artemisV;
    }

    public boolean hasApollo() {
        return apollo;
    }
}
