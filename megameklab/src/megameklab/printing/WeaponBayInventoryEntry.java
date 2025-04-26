/*
 * Copyright (c) 2020-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.Dropship;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
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

    public WeaponBayInventoryEntry(Aero ship, WeaponBayText bay, boolean isCapital) {
        this.ship = ship;
        this.bay = bay;
        this.isCapital = isCapital;
        this.isAR10 = bay.weapons.keySet().stream().anyMatch(w -> w.getAmmoType() == AmmoType.T_AR10);
        processBay();
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
                double av = Math.ceil(weaponType.getShortAV() * numWeapons * 0.5);
                baySRV += Math.round(Math.ceil(av * 1.5) / 10.0);
                bayMRV += Math.round(av / 10.0);
                bayLRV += Math.round(Math.ceil(av * 0.5) / 10.0);
                bayERV += Math.round(Math.ceil(av * 0.5) / 10.0);
                standardBaySRV += Math.ceil(av * 1.5);
                standardBayMRV += av;
                standardBayLRV += Math.ceil(av * 0.5);
                standardBayERV += Math.ceil(av * 0.5);
            } else {
                int bonus = 0;
                if (bay.augmentations.containsKey(weaponType)) {
                    bonus = bay.augmentations.get(
                            weaponType).entrySet().stream()
                        .mapToInt(e -> aeroAVMod(weaponType, e.getKey(), true) * e.getValue()).sum();
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
        // PPC capacitors in bays are considered alway charged
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
                        && (bay.loc.get(i) == Dropship.LOC_LWING
                            || bay.loc.get(i) == Dropship.LOC_RWING)) {
                    if (ship.isSpheroid()) {
                        if (bay.loc.get(i) == Dropship.LOC_LWING) {
                            locString.add(bay.rear ? "ALS" : "FLS");
                        } else {
                            locString.add(bay.rear ? "ARS" : "FRS");
                        }
                    } else {
                        if (bay.loc.get(i) == Dropship.LOC_LWING) {
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
                Mounted<?> ammo = bay.weaponAmmo.get(weaponType);
                if (weaponType.getAmmoType() == AmmoType.T_AR10) {
                    nameString.append(" (").append((int) ammo.getSize()).append(" ton capacity)");
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
