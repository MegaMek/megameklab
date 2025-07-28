/*
 * MegaMekLab - Copyright (C) 2025 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.printing;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.LandAirMek;
import megamek.common.Mek;
import megamek.common.MiscType;
import megamek.common.MiscTypeFlag;
import megamek.common.ProtoMek;

public class IntrinsicPhysicalInventoryEntry implements InventoryEntry{
    private final static DecimalFormat doubleFormat = new DecimalFormat("#.##");

    private final String name;
    private final String location;
    private final String damage;
    private final String mod;

    private IntrinsicPhysicalInventoryEntry(String name, String location, String damage, String mod) {
        this.name = name;
        this.location = location;
        this.damage = damage;
        this.mod = mod;
    }

    private static IntrinsicPhysicalInventoryEntry e(String name, String location, String damage, String mod) {
        return new IntrinsicPhysicalInventoryEntry(name , location, damage, mod);
    }

    private static String formatDamage(double d) {
        return String.valueOf((int) Math.ceil(d));
    }

    private static String formatDamage(double d, boolean tsm) {
        if (tsm) {
            var dmg = (int) Math.ceil(d);
            return "%d [%d]".formatted(dmg, dmg*2);
        } else {
            return formatDamage(d);
        }
    }

    public static List<InventoryEntry> getEntriesFor(Entity entity){
        if (entity instanceof Mek mek) {
            return getEntriesForMek(mek);
        } if (entity instanceof ProtoMek protoMek) {
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

            return List.of(new HeaderEntry(), e("Frenzy", DASH, String.valueOf(dmg), "*"));
        } else if (entity.getMovementMode() == EntityMovementMode.RAIL) {
            return List.of(new HeaderEntry(), e("Choo Choo", "FR", "*", "*"));
        } else if (entity.canCharge()) {
            return List.of(new HeaderEntry(), e("Charge", DASH, doubleFormat.format(entity.getWeight() / 10) + "/hex", "*"));
        }

        return List.of();
    }

    private static List<InventoryEntry> getEntriesForMek(Mek mek) {
        ArrayList<InventoryEntry> entries = new ArrayList<>();
        entries.add(new HeaderEntry());

        var hasTsm = mek.hasTSM(true);

        var hasLHand = mek.hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_LARM);
        var hasRHand = mek.hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_RARM);

        // Punches
        boolean hasLArmAES = mek.hasFunctionalArmAES(Mek.LOC_LARM);
        boolean hasRArmAES = mek.hasFunctionalArmAES(Mek.LOC_RARM);
        if (!mek.isQuadMek()) {
            var baseDmg = Math.ceil(mek.getWeight() / 10);
            var dmg = formatDamage(baseDmg, hasTsm);
            if (mek instanceof LandAirMek) {
                var airMekDamage = baseDmg / 2;
                dmg = "%s/%s".formatted(dmg, formatDamage(airMekDamage, hasTsm));
            }
            int mod;

            if (!mek.hasClaw(Mek.LOC_LARM)) {
                if (hasLHand) {
                    mod = 0;
                } else if (mek.hasSystem(Mek.ACTUATOR_LOWER_ARM, Mek.LOC_LARM)) {
                    mod = 1;
                } else {
                    mod = 2;
                }
                if (hasLArmAES) {
                    mod--;
                }
                entries.add(e("Punch", "LA", dmg, mod == 0 ? "" : "%+d".formatted(mod)));
            }

            if (!mek.hasClaw(Mek.LOC_RARM)) {
                if (hasRHand) {
                    mod = 0;
                } else if (mek.hasSystem(Mek.ACTUATOR_LOWER_ARM, Mek.LOC_RARM)) {
                    mod = 1;
                } else {
                    mod = 2;
                }
                if (hasRArmAES) {
                    mod--;
                }
                entries.add(e("Punch", "RA", dmg, mod == 0 ? "" : "%+d".formatted(mod)));
            }
        }

        // Kicks
        {
            var baseDmg =  Math.ceil(mek.getWeight() / 5);
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

            entries.add(e("Club", DASH, dmg, "%+d".formatted(mod)));
        }

        // DFA
        if (mek.getOriginalJumpMP(true) > 0) {
            if (mek.hasMisc(MiscTypeFlag.F_TALON)) {
                var dmg = formatDamage(Math.ceil(mek.getWeight() / 10 * 3) * 1.5);
                entries.add(e("DFA [Talons]", DASH, dmg, "*"));
            } else {
                var dmg = formatDamage(mek.getWeight() / 10 * 3);
                entries.add(e("Death From Above", DASH, dmg, "*"));
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
            entries.add(e("Charge", DASH, dmg, "*"));
        }
        if (mek instanceof LandAirMek) {
            var dmg = "%s/hex".formatted(doubleFormat.format(mek.getWeight() / 5));
            // CHECKSTYLE IGNORE ForbiddenWords FOR 1 LINES
            entries.add(e("AirMech Ram",  DASH, dmg, "*"));
        }

        // Push
        if (!mek.isQuadMek()) {
            var mod = -1;
            if (hasLArmAES && hasRArmAES) {
                mod--;
            }
            entries.add(e("Push", DASH, DASH, "%+d".formatted(mod)));
        }

        return entries;
    }

    @Override
    public int nRows() {
        return 1;
    }

    @Override
    public String getUniqueId() {
        return String.valueOf(hashCode());
    }

    @Override
    public String getQuantityField(int row) {
        return "1";
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
    public String getModField(int row) {
        return mod;
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

    private static class HeaderEntry implements InventoryEntry {

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
            return "\u00A0- Physical Attacks -";
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
        public String getModField(int row) {
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
