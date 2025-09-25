/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import megamek.common.equipment.MiscType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.units.Entity;
import megamek.common.units.LandAirMek;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;

public record IntrinsicPhysicalInventoryEntry(String name, String location, String damage, String mod, boolean optional)
      implements InventoryEntry {
    private final static DecimalFormat doubleFormat = new DecimalFormat("#.##");

    private static IntrinsicPhysicalInventoryEntry e(String name, String location, String damage, String mod) {
        return new IntrinsicPhysicalInventoryEntry(name, location, damage, mod, false);
    }

    private static IntrinsicPhysicalInventoryEntry e(String name, String location, String damage, String mod,
          boolean optional) {
        return new IntrinsicPhysicalInventoryEntry(name, location, damage, mod, optional);
    }

    private static String formatDamage(double d) {
        return String.valueOf((int) Math.ceil(d));
    }

    private static String formatDamage(double d, boolean tsm) {
        if (tsm) {
            var dmg = (int) Math.ceil(d);
            return "%d [%d]".formatted(dmg, dmg * 2);
        } else {
            return formatDamage(d);
        }
    }

    public static ArrayList<InventoryEntry> getEntriesFor(Entity entity) {
        if (entity instanceof Mek mek) {
            return getEntriesForMek(mek);
        }
        if (entity instanceof ProtoMek protoMek) {
            int dmg;
            var weight = protoMek.getWeight();
            if (weight <= 5) {
                dmg = 1;
            } else if (weight <= 9) {
                dmg = 2;
            } else {
                dmg = 3;
            }

            if (protoMek.isGlider()) {
                dmg--;
                if (dmg < 1) {
                    dmg = 1;
                }
            }

            if (protoMek.hasMisc(MiscTypeFlag.F_PROTOMEK_MELEE)) {
                dmg += (int) Math.ceil(weight / 5);
            }

            return new ArrayList<>(List.of(e("Frenzy", DASH, String.valueOf(dmg), "*")));
        } else if (entity.canCharge()) {
            return new ArrayList<>(List.of(e("Charge", DASH, doubleFormat.format(entity.getWeight() / 10) + "/hex",
                  "*", true)));
        }

        return new ArrayList<>();
    }

    private static ArrayList<InventoryEntry> getEntriesForMek(Mek mek) {
        ArrayList<InventoryEntry> entries = new ArrayList<>();

        var hasTsm = mek.hasTSM(true);

        var hasLHand = mek.hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_LEFT_ARM);
        var hasRHand = mek.hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_RIGHT_ARM);
        boolean hasLLowerActuator = mek.hasSystem(Mek.ACTUATOR_LOWER_ARM, Mek.LOC_LEFT_ARM);
        boolean hasRLowerActuator = mek.hasSystem(Mek.ACTUATOR_LOWER_ARM, Mek.LOC_RIGHT_ARM);

        // Punches
        boolean hasLArmAES = mek.hasFunctionalArmAES(Mek.LOC_LEFT_ARM);
        boolean hasRArmAES = mek.hasFunctionalArmAES(Mek.LOC_RIGHT_ARM);
        if (!mek.isQuadMek()) {
            var baseDmg = Math.ceil(mek.getWeight() / 10);

            if (mek instanceof LandAirMek) {
                baseDmg /= 2;
            }

            var lDmg = formatDamage(hasLLowerActuator ? baseDmg : Math.floor(baseDmg / 2), hasTsm);
            var rDmg = formatDamage(hasRLowerActuator ? baseDmg : Math.floor(baseDmg / 2), hasTsm);

            int mod;
            boolean explicitZero;

            if (!mek.hasClaw(Mek.LOC_LEFT_ARM)) {
                explicitZero = false;
                if (hasLHand) {
                    mod = 0;
                } else if (hasLLowerActuator) {
                    mod = 1;
                } else {
                    mod = 2;
                }
                if (hasLArmAES) {
                    mod--;
                    explicitZero = true;
                }
                entries.add(e("Punch", "LA", lDmg, mod != 0 ? "%+d".formatted(mod) : (explicitZero ? DASH : "")));
            }

            if (!mek.hasClaw(Mek.LOC_RIGHT_ARM)) {
                explicitZero = false;
                if (hasRHand) {
                    mod = 0;
                } else if (hasRLowerActuator) {
                    mod = 1;
                } else {
                    mod = 2;
                }
                if (hasRArmAES) {
                    mod--;
                    explicitZero = true;
                }
                entries.add(e("Punch", "RA", rDmg, mod != 0 ? "%+d".formatted(mod) : (explicitZero ? DASH : "")));
            }
        }

        // Kicks
        {
            var baseDmg = Math.ceil(mek.getWeight() / 5);
            if (mek.hasMisc(MiscTypeFlag.F_TALON)) {
                baseDmg = Math.ceil(baseDmg * 1.5);
                var dmg = formatDamage(baseDmg, hasTsm);
                if (mek instanceof LandAirMek) {
                    var airMekDamage = baseDmg / 2;
                    dmg = "%s/%s".formatted(dmg, formatDamage(airMekDamage, hasTsm));
                }
                entries.add(e("Kick [Talons]", DASH, dmg, mek.hasFunctionalLegAES() ? "-3" : "-2"));
            } else {
                var dmg = formatDamage(baseDmg, hasTsm);
                if (mek instanceof LandAirMek) {
                    var airMekDamage = baseDmg / 2;
                    dmg = "%s/%s".formatted(dmg, formatDamage(airMekDamage, hasTsm));
                }
                entries.add(e("Kick", DASH, dmg, mek.hasFunctionalLegAES() ? "-3" : "-2"));
            }
        }

        // Club
        if (hasLHand && hasRHand) {
            var dmg = formatDamage(mek.getWeight() / 5, hasTsm);
            var mod = -1;
            if (mek.hasWorkingMisc(MiscTypeFlag.F_CLUB, MiscType.S_CLAW)) {
                mod += 2;
            }
            if (hasLArmAES && hasRArmAES) {
                mod -= 1;
            }

            // For mod to be 0 it has to come from AES, hence explicit DASH.
            // If in the future, this is no longer true, there should be a check for explicit DASH vs blank string
            entries.add(e("Club", DASH, dmg, mod != 0 ? "%+d".formatted(mod) : DASH, true));
        }

        // DFA
        if (mek.getOriginalJumpMP(true) > 0) {
            if (mek.hasMisc(MiscTypeFlag.F_TALON)) {
                var dmg = formatDamage(Math.ceil(mek.getWeight() / 10 * 3) * 1.5);
                entries.add(e("DFA [Talons]", DASH, dmg, "Vs", true));
            } else {
                var dmg = formatDamage(mek.getWeight() / 10 * 3);
                entries.add(e("Death From Above", DASH, dmg, "Vs", true));
            }
        }

        // Charge
        {
            var baseDmg = mek.getWeight() / 10;
            if (mek.hasMisc(MiscTypeFlag.F_RAM_PLATE)) {
                baseDmg *= 1.5;
            }
            var spikesDmg = mek.getMiscEquipment(MiscTypeFlag.F_SPIKES).size() * 2;
            String dmg;
            if (spikesDmg > 0) {
                dmg = "%s/hex+%d".formatted(doubleFormat.format(baseDmg), spikesDmg);
            } else {
                dmg = "%s/hex".formatted(doubleFormat.format(baseDmg));
            }
            entries.add(e("Charge", DASH, dmg, "Vs", true));
        }
        if (mek instanceof LandAirMek) {
            var dmg = "%s/hex".formatted(doubleFormat.format(mek.getWeight() / 5));
            // CHECKSTYLE IGNORE ForbiddenWords FOR 1 LINES
            entries.add(e("AirMech Ram", DASH, dmg, "Vs", true));
        }

        // Push
        if (!mek.isQuadMek()) {
            var mod = -1;
            if (hasLArmAES && hasRArmAES) {
                mod--;
            }
            entries.add(e("Push", DASH, DASH, "%+d".formatted(mod), true));
        }

        return entries;
    }

    /**
     * @return true if this physical should only be shown when the "Extra physicals" option is enabled.
     */
    @Override
    public boolean optional() {
        return optional;
    }

    @Override
    public int nRows() {
        return 1;
    }

    @Override
    public String getUniqueId() {
        return name + "@" + location;
    }

    @Override
    public String getQuantityField(int row) {
        return "";
    }

    @Override
    public String getNameField(int row) {
        return name;
    }

    @Override
    public boolean isDamaged() {
        return false;
    }

    @Override
    public String getLocationField(int row) {
        return location;
    }

    @Override
    public String getHeatField(int row) {
        return DASH;
    }

    @Override
    public String getDamageField(int row) {
        return damage;
    }

    @Override
    public String getMinField(int row) {
        return DASH;
    }

    @Override
    public String getShortField(int row) {
        return DASH;
    }

    @Override
    public String getMediumField(int row) {
        return DASH;
    }

    @Override
    public String getLongField(int row) {
        return DASH;
    }

    @Override
    public String getExtremeField(int row) {
        return DASH;
    }

    @Override
    public String getModField(int row, boolean baseOnly) {
        if (row == 0) {
            return mod.replace("-", MINUS);
        }
        return "";
    }

    @Override
    public boolean indentMultiline() {
        return false;
    }

    @Override
    public boolean hasQuirks() {
        return false;
    }

    @Override
    public String getQuirksField() {
        return "";
    }

    public static class HeaderEntry implements InventoryEntry {

        @Override
        public int nRows() {
            return 1;
        }

        @Override
        public String getUniqueId() {
            return "HEADER";
        }

        @Override
        public String getQuantityField(int row) {
            return "";
        }

        @Override
        public String getNameField(int row) {
            return "Physical Attacks";
        }

        @Override
        public boolean isDamaged() {
            return false;
        }

        @Override
        public String getLocationField(int row) {
            return "";
        }

        @Override
        public String getHeatField(int row) {
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

        @Override
        public String getShortField(int row) {
            return "";
        }

        @Override
        public String getMediumField(int row) {
            return "";
        }

        @Override
        public String getLongField(int row) {
            return "";
        }

        @Override
        public String getExtremeField(int row) {
            return "";
        }

        @Override
        public String getModField(int row, boolean baseOnly) {
            return "";
        }

        @Override
        public boolean indentMultiline() {
            return false;
        }

        @Override
        public boolean hasQuirks() {
            return false;
        }

        @Override
        public String getQuirksField() {
            return "";
        }
    }
}
