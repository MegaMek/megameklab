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
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.WeaponMounted;
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
    /** The bay's attack values on its first row, unless its munition lines print them all */
    private @Nullable double[] bayLine;
    /** An MML or ATM bay's munitions that print their own values, each on a line under the weapon names */
    private final List<MunitionLine> munitionLines = new ArrayList<>();
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
        return "bay_" + index;
    }

    private void processBay() {
        List<String> carried = MUNITIONS.stream()
              .filter(munition -> bay.weapons.keySet().stream().anyMatch(weaponType -> munitions(weaponType).contains(munition)))
              .toList();
        if (isCapital || (carried.size() <= 1)) {
            bayLine = bayValues(carried.isEmpty() ? null : carried.getFirst());
        } else if (atmBayProfile()) {
            // Short range times 1.5, medium unchanged, long and extreme half the standard value, each rounded up
            double[] standard = bayValues("Standard");
            bayLine = new double[] { Math.ceil(standard[0] * 1.5), standard[1], Math.ceil(standard[1] / 2),
                                     Math.ceil(standard[1] / 2) };
            // Improved Magnetic Pulse missiles don't count toward the profile, so they print a line of their own
            if (carried.contains("Improved Magnetic Pulse")) {
                munitionLines.add(new MunitionLine("Improved Magnetic Pulse", bayValues("Improved Magnetic Pulse")));
            }
        } else {
            carried.forEach(munition -> munitionLines.add(new MunitionLine(munition, bayValues(munition))));
        }
        // Each weapon's heat with its laser insulator (TO:AUE p. 134), and an installed PPC capacitor's 5, which a bay
        // counts as always charged (TO:AUE p. 149), as the bay's damage counts it
        for (WeaponMounted weapon : bay.bayWeapons) {
            heat += (int) weapon.laserEnhancedHeat(weapon.getType().getHeat(), false)
                  + ((weapon.getEnhancement(MiscType.F_PPC_CAPACITOR) != null) ? 5 : 0);
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
            // A munition printing a line of its own carries its rounds there
            Map<String, Integer> totals = ammoTotals(weaponType);
            munitionLines.forEach(line -> totals.remove(line.munition()));
            if (!totals.isEmpty()) {
                nameString.append(formatAmmo(weaponType, totals));
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

    /** A munition's attack values, on a line named for the munition and its rounds: LRM (78 rounds) */
    private record MunitionLine(String munition, double[] values) {}

    /** The rounds of a munition the bay carries */
    private int rounds(String munition) {
        return bay.weapons.keySet().stream().filter(weaponType -> canFire(weaponType, munition))
              .mapToInt(weaponType -> ammoTotals(weaponType).getOrDefault(munition, 0)).sum();
    }

    /**
     * A weapon type's rounds of each munition the bay carries for it, in the order the bay lists them: an MML or ATM
     * munition by the name its line prints, any other ammo by its short name
     */
    private Map<String, Integer> ammoTotals(WeaponType weaponType) {
        return bay.ammoTotals(weaponType, ammo -> {
            String munition = (ammo.getType() instanceof AmmoType ammoType) ? munition(weaponType, ammoType) : null;
            return (munition != null) ? munition : ammo.getType().getShortName();
        });
    }

    /**
     * A bay weapon's ammo, one total per munition: a single munition as its rounds (or missiles), several as each
     * total under the words their names don't share, so an AR10's missiles print as (200 B, 100 WS, 50 KW) and an
     * LB-X's slugs and cluster as (10 Standard, 10 Cluster). An MML's or ATM's only munition prints by name unless it
     * is the launcher's first (LRM, Standard): (10 Extended Range).
     */
    private static String formatAmmo(WeaponType weaponType, Map<String, Integer> shots) {
        if ((shots.size() == 1) && (weaponType.getAmmoType() != AmmoType.AmmoTypeEnum.AR10)) {
            String munition = shots.keySet().iterator().next();
            String first = MUNITIONS.stream().filter(other -> canFire(weaponType, other)).findFirst().orElse(null);
            String unit = (MUNITIONS.contains(munition) && !munition.equals(first)) ? munition
                  : (weaponType.isCapital() && weaponType.hasFlag(WeaponType.F_MISSILE)) ? "missiles" : "rounds";
            return " (" + shots.get(munition) + " " + unit + ")";
        }
        List<String[]> words = shots.keySet().stream().map(name -> name.split("[ /]")).toList();
        int shortest = words.stream().mapToInt(name -> name.length).min().orElse(0);
        int head = 0;
        while ((head < shortest) && sharesWord(words, head, false)) {
            head++;
        }
        int tail = 0;
        while ((head + tail < shortest) && sharesWord(words, tail, true)) {
            tail++;
        }
        StringJoiner totals = new StringJoiner(", ", " (", ")");
        int index = 0;
        for (int count : shots.values()) {
            String[] name = words.get(index++);
            String label = String.join(" ", Arrays.copyOfRange(name, head, name.length - tail));
            totals.add(count + " " + (label.isEmpty() ? "Standard" : label));
        }
        return totals.toString();
    }

    /** Whether every name has the same word at the offset, counted from the end when {@code fromEnd} */
    private static boolean sharesWord(List<String[]> names, int offset, boolean fromEnd) {
        if (names.size() < 2) {
            return false;
        }
        String first = word(names.getFirst(), offset, fromEnd);
        return names.stream().allMatch(name -> word(name, offset, fromEnd).equals(first));
    }

    private static String word(String[] name, int offset, boolean fromEnd) {
        return name[fromEnd ? name.length - 1 - offset : offset];
    }

    /** The munitions an MML or ATM bay prints a line for, in the order of TW p. 303, then the iATM's (IO:AE p. 61) */
    private static final List<String> MUNITIONS = List.of("LRM", "SRM", "Standard", "Extended Range", "High Explosive",
          "Improved Magnetic Pulse");

    /** The munitions a weapon type carries in the bay */
    private Set<String> munitions(WeaponType weaponType) {
        Set<String> carried = new LinkedHashSet<>(ammoTotals(weaponType).keySet());
        carried.removeIf(munition -> !canFire(weaponType, munition));
        return carried;
    }

    private static @Nullable String munition(WeaponType weaponType, AmmoType ammoType) {
        if (weaponType instanceof MMLWeapon) {
            return ammoType.hasFlag(AmmoType.F_MML_LRM) ? "LRM" : "SRM";
        } else if (!(weaponType instanceof ATMWeapon) && !(weaponType instanceof CLIATMWeapon)) {
            return null;
        }
        EnumSet<AmmoType.Munitions> types = ammoType.getMunitionType();
        if (types.contains(AmmoType.Munitions.M_EXTENDED_RANGE)) {
            return "Extended Range";
        } else if (types.contains(AmmoType.Munitions.M_HIGH_EXPLOSIVE)) {
            return "High Explosive";
        } else if (types.contains(AmmoType.Munitions.M_IATM_IMP)) {
            return "Improved Magnetic Pulse";
        }
        return types.contains(AmmoType.Munitions.M_STANDARD) ? "Standard" : null;
    }

    /**
     * Whether every ATM in the bay carries at least one ton of each munition, which gives the bay its own profile: short
     * range times 1.5, medium unchanged, long and extreme half the standard value, each rounded up (TW p. 304)
     */
    private boolean atmBayProfile() {
        List<WeaponType> atms = bay.weapons.keySet().stream()
              .filter(weaponType -> (weaponType instanceof ATMWeapon) || (weaponType instanceof CLIATMWeapon)).toList();
        return !atms.isEmpty() && atms.stream().allMatch(weaponType -> Stream.of("Standard", "Extended Range",
              "High Explosive").allMatch(munition -> bay.weaponAmmo.getOrDefault(weaponType, List.of()).stream()
              .mapToDouble(ammo -> ((ammo.getType() instanceof AmmoType ammoType)
                    && munition.equals(munition(weaponType, ammoType)))
                    ? (ammo.getBaseShotsLeft() / (double) ammoType.getShots()) : 0)
              .sum() >= bay.weapons.get(weaponType)));
    }

    /**
     * The bay's combined values at each range, at standard scale for standard weapons: each weapon with its Artemis,
     * firing the given munition where it carries it. An MML or ATM fires only a munition it can fire and carries; one
     * without ammo keeps its listed values.
     */
    private double[] bayValues(@Nullable String munition) {
        double[] total = new double[4];
        for (WeaponType weaponType : bay.weapons.keySet()) {
            Set<String> carried = munitions(weaponType);
            String fired = canFire(weaponType, munition) ? munition : null;
            if ((munition != null) && MUNITIONS.stream().anyMatch(other -> canFire(weaponType, other))
                  && ((fired == null) || (!carried.isEmpty() && !carried.contains(fired)))) {
                continue;
            }
            // Each launcher that fires the munition spends a round of it (TW p. 103)
            int numWeapons = ((fired != null) && carried.contains(fired))
                  ? Math.min(bay.weapons.get(weaponType), ammoTotals(weaponType).getOrDefault(fired, 0))
                  : bay.weapons.get(weaponType);
            double[] values = { weaponType.getShortAV(), weaponType.getMedAV(), weaponType.getLongAV(),
                                weaponType.getExtAV() };
            int bonus = 0;
            if (!isCapital && bay.augmentations.containsKey(weaponType)) {
                bonus = bay.augmentations.get(weaponType).entrySet().stream()
                      .mapToInt(augmentation -> AeroAVModCalculator.calculateBonus(weaponType, augmentation.getKey(), true)
                            * Math.min(augmentation.getValue(), numWeapons))
                      .sum();
            }
            for (int range = 0; range < values.length; range++) {
                values[range] = (values[range] > 0) ? ((values[range] * numWeapons) + bonus) : 0;
            }
            values = InventoryEntry.munitionAttackValues(values, fired);
            for (int range = 0; range < total.length; range++) {
                total[range] += values[range];
            }
        }
        return total;
    }

    /**
     * Whether a weapon type fires the munition: an MML its LRM and SRM, an ATM its standard, ER and HE missiles, and an
     * iATM also its Improved Magnetic Pulse missiles
     */
    private static boolean canFire(WeaponType weaponType, @Nullable String munition) {
        if (weaponType instanceof MMLWeapon) {
            return "LRM".equals(munition) || "SRM".equals(munition);
        }
        if ((weaponType instanceof CLIATMWeapon) && "Improved Magnetic Pulse".equals(munition)) {
            return true;
        }
        return ((weaponType instanceof ATMWeapon) || (weaponType instanceof CLIATMWeapon))
              && ("Standard".equals(munition) || "Extended Range".equals(munition) || "High Explosive".equals(munition));
    }

    @Override
    public int nRows() {
        return bay.weapons.size() + munitionLines.size();
    }

    /** The munition line a row prints, under the weapon names */
    private @Nullable MunitionLine munitionLine(int row) {
        int munitionRow = row - bay.weapons.size();
        return ((munitionRow >= 0) && (munitionRow < munitionLines.size())) ? munitionLines.get(munitionRow) : null;
    }

    /** The values a row prints: the bay's on its first row, or a munition's on that munition's line */
    private @Nullable double[] rowValues(int row) {
        if (row == 0) {
            return bayLine;
        }
        MunitionLine line = munitionLine(row);
        return (line == null) ? null : line.values();
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
        // Each munition's line carries its rounds: LRM (78 rounds)
        MunitionLine line = munitionLine(row);
        return (line == null) ? "" : line.munition() + " (" + rounds(line.munition()) + " rounds)";
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
            // An AR10's heat depends on the missile it fires (TW p. 304)
            return isAR10 ? "*" : String.valueOf(heat);
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

    /**
     * A bay's value at one range, from its combined total: a capital bay's rounds normally (SO p. 88); a standard bay's
     * prints its capital value, the standard value over 10 rounded normally, then the standard value (SO p. 10)
     */
    private String formatAV(int row, int range) {
        double[] values = rowValues(row);
        if (values == null) {
            return "";
        } else if (isAR10) {
            return "*"; // depends on missile type loaded
        }
        double value = values[range];
        if (value <= 0) {
            return DASH;
        } else if (isCapital) {
            return String.valueOf(Math.round(value));
        }
        return Math.round(value / 10) + " (" + ((int) value) + ")";
    }

    @Override
    public String getShortField(int row) {
        return formatAV(row, 0);
    }

    @Override
    public String getMediumField(int row) {
        return formatAV(row, 1);
    }

    @Override
    public String getLongField(int row) {
        return formatAV(row, 2);
    }

    @Override
    public String getExtremeField(int row) {
        return formatAV(row, 3);
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
