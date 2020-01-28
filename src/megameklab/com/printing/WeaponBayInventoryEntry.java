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

import java.util.*;

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
    private List<String> weaponNames = new ArrayList<>();
    private List<String> quantities = new ArrayList<>();

    public WeaponBayInventoryEntry(Aero ship, WeaponBayText bay, boolean isCapital) {
        this.ship = ship;
        this.bay = bay;
        this.isCapital = isCapital;
        this.isAR10 = bay.weapons.keySet().stream().anyMatch(w -> w.getAmmoType() == AmmoType.T_AR10);
        processBay();
    }

    private void processBay() {
        for (WeaponType wtype : bay.weapons.keySet()) {
            int numWeapons = bay.weapons.get(wtype);
            heat += wtype.getHeat() * numWeapons;
            if (isCapital) {
                baySRV += wtype.getShortAV() * numWeapons;
                bayMRV += wtype.getMedAV() * numWeapons;
                bayLRV += wtype.getLongAV() * numWeapons;
                bayERV += wtype.getExtAV() * numWeapons;
            } else {
                int bonus = 0;
                if (bay.augmentations.containsKey(wtype)) {
                    bonus = bay.augmentations.get(wtype).entrySet().stream()
                        .mapToInt(e -> aeroAVMod(wtype, e.getKey(), true) * e.getValue()).sum();
                }
                if (wtype.getShortAV() > 0) {
                    baySRV += Math.round((wtype.getShortAV() * numWeapons + bonus) / 10);
                    standardBaySRV += wtype.getShortAV() * numWeapons + bonus;
                }
                if (wtype.getMedAV() > 0) {
                    bayMRV += Math.round((wtype.getMedAV() * numWeapons + bonus) / 10);
                    standardBayMRV += wtype.getMedAV() * numWeapons + bonus;
                }
                if (wtype.getLongAV() > 0) {
                    bayLRV += Math.round((wtype.getLongAV() * numWeapons + bonus) / 10);
                    standardBayLRV += wtype.getLongAV() * numWeapons + bonus;
                }
                if (wtype.getExtAV() > 0) {
                    bayERV += Math.round((wtype.getExtAV() * numWeapons + bonus) / 10);
                    standardBayERV += wtype.getExtAV() * numWeapons + bonus;
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
         * can't be mixed. But it's no great effort to accomodate illegal designs. Based on precedents elsewhere
         * in the rules, every weapon in the bay would require Artemis V or Apollo to get the to-hit bonus.
         */
        artemisV = bay.allHaveAugmentation(MiscType.F_ARTEMIS_V);
        apollo = bay.allHaveAugmentation((MiscType.F_APOLLO));

        for (WeaponType wtype : bay.weapons.keySet()) {
            StringJoiner locString = new StringJoiner("/");
            for (int i = 0; i < bay.loc.size(); i++) {
                locString.add(ship.getLocationAbbr(bay.loc.get(i)));
            }
            location = locString.toString();
            StringBuilder nameString = new StringBuilder(wtype.getShortName());
            if (bay.weaponAmmo.containsKey(wtype)) {
                Mounted ammo = bay.weaponAmmo.get(wtype);
                if (wtype.getAmmoType() == AmmoType.T_AR10) {
                    nameString.append(" (").append((int) ammo.getAmmoCapacity()).append(" ton capacity)");
                } else if (wtype.isCapital() && wtype.hasFlag(WeaponType.F_MISSILE)) {
                    nameString.append(" (").append(ammo.getBaseShotsLeft()).append(" missiles)");
                } else {
                    nameString.append(" (").append(ammo.getBaseShotsLeft()).append(" rounds)");
                }
            }
            int capacitors = bay.countAugmentations(wtype, MiscType.F_PPC_CAPACITOR);
            if (capacitors == bay.weapons.get(wtype)) {
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
            quantities.add(String.valueOf(bay.weapons.get(wtype)));
        }
    }

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
        if (row < weaponNames.size()) {
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
        if (av == 0) {
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
