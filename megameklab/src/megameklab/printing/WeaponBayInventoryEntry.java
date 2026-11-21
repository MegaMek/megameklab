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
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
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
    /** The bay's attack values: one line, or an MML or ATM bay's line for each munition it carries */
    private final List<DamageLine> damageLines = new ArrayList<>();
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
              .filter(munition -> bay.weapons.keySet().stream().anyMatch(w -> munitions(w).contains(munition)))
              .toList();
        if (isCapital || atmBayProfile() || (carried.size() <= 1)) {
            damageLines.add(new DamageLine("", bayValues(carried.isEmpty() ? null : carried.getFirst())));
        } else {
            // Each munition's line carries its rounds: LRM (78 rounds)
            carried.forEach(munition -> damageLines.add(new DamageLine(munition + " (" + rounds(munition) + " rounds)",
                  bayValues(munition))));
        }
        for (WeaponType weaponType : bay.weapons.keySet()) {
            heat += weaponType.getHeat() * bay.weapons.get(weaponType);
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
            // A bay printing a line per munition carries each munition's rounds on its line
            boolean ammoOnMunitionLines = (damageLines.size() > 1)
                  && MUNITIONS.stream().anyMatch(munition -> canFire(weaponType, munition));
            if (bay.weaponAmmo.containsKey(weaponType) && !ammoOnMunitionLines) {
                nameString.append(formatAmmo(weaponType, ammoTotals(weaponType)));
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

    /** One printed line of attack values, named for its munition and its rounds when the bay prints several */
    private record DamageLine(String name, double[] values) {}

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
     * LB-X's slugs and cluster as (10 Standard, 10 Cluster).
     */
    private static String formatAmmo(WeaponType weaponType, Map<String, Integer> shots) {
        if ((shots.size() == 1) && (weaponType.getAmmoType() != AmmoType.AmmoTypeEnum.AR10)) {
            String unit = weaponType.isCapital() && weaponType.hasFlag(WeaponType.F_MISSILE) ? "missiles" : "rounds";
            return " (" + shots.values().iterator().next() + " " + unit + ")";
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
              .filter(w -> (w instanceof ATMWeapon) || (w instanceof CLIATMWeapon)).toList();
        return !atms.isEmpty() && atms.stream().allMatch(weaponType -> Stream.of("Standard", "Extended Range",
              "High Explosive").allMatch(munition -> bay.weaponAmmo.getOrDefault(weaponType, List.of()).stream()
              .filter(ammo -> (ammo.getType() instanceof AmmoType ammoType)
                    && munition.equals(munition(weaponType, ammoType)))
              .mapToDouble(ammo -> ammo.getBaseShotsLeft() / (double) ((AmmoType) ammo.getType()).getShots())
              .sum() >= bay.weapons.get(weaponType)));
    }

    /**
     * The bay's combined values at each range, at standard scale for standard weapons: each weapon with its Artemis,
     * firing the given munition where it carries it. A launcher without that munition doesn't fire; one without ammo
     * keeps its listed values.
     */
    private double[] bayValues(@Nullable String munition) {
        double[] total = new double[4];
        boolean atmProfile = !isCapital && atmBayProfile();
        for (WeaponType weaponType : bay.weapons.keySet()) {
            int numWeapons = bay.weapons.get(weaponType);
            Set<String> carried = munitions(weaponType);
            String fired = canFire(weaponType, munition) ? munition : null;
            if ((fired != null) && !carried.isEmpty() && !carried.contains(fired)) {
                continue;
            }
            double[] values = { weaponType.getShortAV(), weaponType.getMedAV(), weaponType.getLongAV(),
                                weaponType.getExtAV() };
            int bonus = 0;
            if (!isCapital && bay.augmentations.containsKey(weaponType)) {
                bonus = bay.augmentations.get(weaponType).entrySet().stream()
                      .mapToInt(e -> AeroAVModCalculator.calculateBonus(weaponType, e.getKey(), true) * e.getValue())
                      .sum();
            }
            for (int range = 0; range < values.length; range++) {
                values[range] = (values[range] > 0) ? ((values[range] * numWeapons) + bonus) : 0;
            }
            if (atmProfile && ((weaponType instanceof ATMWeapon) || (weaponType instanceof CLIATMWeapon))) {
                values = new double[] { Math.ceil(values[0] * 1.5), values[1], Math.ceil(values[1] / 2),
                                        Math.ceil(values[1] / 2) };
            } else {
                values = InventoryEntry.munitionAttackValues(values, fired);
            }
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
        return bay.weapons.size() + munitionRows();
    }

    /** The munition lines printed under the weapon names, when the bay prints one per munition */
    private int munitionRows() {
        return (damageLines.size() > 1) ? damageLines.size() : 0;
    }

    /** The damage line a row prints: the bay's on its first row, or a munition's on that munition's row */
    private @Nullable DamageLine damageLine(int row) {
        if (munitionRows() == 0) {
            return (row == 0) ? damageLines.getFirst() : null;
        }
        int munitionRow = row - bay.weapons.size();
        return (munitionRow >= 0) ? damageLines.get(munitionRow) : null;
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
        DamageLine line = damageLine(row);
        return (line == null) ? "" : line.name();
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
        DamageLine line = damageLine(row);
        if (line == null) {
            return "";
        } else if (isAR10) {
            return "*"; // depends on missile type loaded
        }
        double value = line.values()[range];
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
