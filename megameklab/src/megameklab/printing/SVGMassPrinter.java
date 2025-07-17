/*
 * Copyright (c) 2024-2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.printing;

import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.util.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.Messages;
import megamek.client.ui.util.FluffImageHelper;
import megamek.common.*;
import megamek.common.actions.ClubAttackAction;
import megamek.common.actions.KickAttackAction;
import megamek.common.alphaStrike.ASUnitType;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.Quirks;
import megamek.common.util.C3Util;
import megamek.common.verifier.TestProtoMek;
import megamek.common.weapons.autocannons.UACWeapon;
import megamek.common.weapons.bayweapons.BayWeapon;
import megamek.common.weapons.gaussrifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
import megamek.common.weapons.missiles.ThunderBoltWeapon;
import megamek.common.weapons.mortars.MekMortarWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.ui.util.EquipmentDatabaseCategory;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.util.CConfig;
import megameklab.util.SVGOptimizer;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGDocument;

import java.io.FileOutputStream;
import java.util.stream.Collectors;

import static megamek.common.MiscTypeFlag.*;
import static megamek.common.WeaponType.*;

/**
 * @author drake
 * Generates SVG sheets for all units in the Mek Summary Cache and saves them
 */
public class SVGMassPrinter {
    private final static boolean SKIP_SVG = true; // Set to true to skip SVG generation
    private final static boolean SKIP_EQUIPMENT = true; // Set to true to skip equipment generation


    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final String TYPEFACE = "Roboto";
    private static final String SHEETS_DIR = "sheets";
    private static final String VERSION_FILE = "version.json";
    private static final String UNIT_FILE = "units.json";
    private static final String EQUIPMENT_FILE = "equipment.json";
    private static final String ROOT_FOLDER = "svgexport";
    private static final int DEFAULT_MARGINS = 0; // Default margins for the page
    private final static RATGenerator RAT_GENERATOR = RATGenerator.getInstance();

    private static final HashMap<Integer, String> unitTypes = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ExportInventoryEntry {
        public String id; // Internal name of the weapon type
        public int q; // Quantity of this weapon type
        public String n; // Name of the weapon type
        public String t; // Type of the weapon
        public int p; // Location id of the weapon, if applicable
        public String l; // Location of the weapon, if applicable
        public String r; // Range of the weapon, if applicable
        public String m; // Min range, if applicable
        public String d; // Damage type, if applicable
        public String md; // Max Damage, if applicable
        public String c; // Critical slots
        public int os; // If is an oneshot weapon or a double oneshot weapon (1 or 2), if applicable
        public Collection<ExportInventoryEntry> bay; // Bay weapons, if applicable
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Components {
        @JsonIgnore
        public HashMap<String, ExportInventoryEntry> comps = new HashMap<>();

        @JsonProperty("comp")
        public Collection<ExportInventoryEntry> getComp() {
            return comps.values();
        }

        private String getWeaponCategory(WeaponType eq) {
            if (eq.hasFlag(F_ENERGY) || ((eq.hasFlag(F_PLASMA) && (eq.getAmmoType() == AmmoType.AmmoTypeEnum.PLASMA))) || eq.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Energy)) {
                return "E";
            }
            if (eq.hasFlag(WeaponType.F_MISSILE) || eq.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Missile)) {
                return "M";
            }
            if (eq.hasFlag(WeaponType.F_BALLISTIC) || eq.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Ballistic)) {
                return "B";
            }
            if (eq.hasFlag(WeaponType.F_ARTILLERY) || eq.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Artillery)) {
                return "A";
            }
            if (EquipmentDatabaseCategory.isIndustrialEquipment(eq) || UnitUtil.isPhysicalWeapon(eq)) {
                return "P";
            }
            return "O";
        }

        private String getWeaponRange(Entity entity, WeaponType wtype) {
            if (entity instanceof Aero) {
                switch (wtype.getMaxRange()) {
                    case RangeType.RANGE_SHORT:
                        return "Short";
                    case RangeType.RANGE_MEDIUM:
                        return "Medium";
                    case RangeType.RANGE_LONG:
                        return "Long";
                    case RangeType.RANGE_EXTREME:
                        return "Extreme";
                }
            }
            if (wtype instanceof InfantryWeapon) {
                return ((InfantryWeapon) wtype).getInfantryRange() + "";
            }
            return wtype.getShortRange() + "/" + wtype.getMediumRange()
                  + "/" + wtype.getLongRange();
        }

        private String getMinRange(Entity entity, WeaponType wtype) {
            if (entity instanceof Aero) {
                return "-";
            }
            int minRange = wtype.getMinimumRange();
            if (minRange < 0) {
                minRange = 0;
            }
            return Integer.toString(minRange);
        }

        private String getCriticals(Entity entity, EquipmentType type) {
            if (type.isVariableCriticals()
                  && (entity.isSupportVehicle() || (entity instanceof Mek))) {
                // Only Meks and support vehicles require multiple slots for equipment
                return "V";
            } else if (entity.isSupportVehicle()) {
                return String.valueOf(type.getSupportVeeSlots(entity));
            } else if (entity instanceof Tank) {
                return String.valueOf(type.getTankSlots(entity));
            } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
                return String.valueOf(TestProtoMek.requiresSlot(type)? 1 : 0);
            }
            return String.valueOf(type.getCriticals(entity));
        }

        private String getDamage(Entity entity, WeaponType wtype) {
                // Aeros should print AV instead
                if (entity instanceof Aero) {
                    int[] attackValue = new int[RangeType.RANGE_EXTREME + 1];
                    attackValue[RangeType.RANGE_SHORT] = wtype.getRoundShortAV();
                    attackValue[RangeType.RANGE_MEDIUM] = wtype.getRoundMedAV();
                    attackValue[RangeType.RANGE_LONG] = wtype.getRoundLongAV();
                    attackValue[RangeType.RANGE_EXTREME] = wtype.getRoundExtAV();
                    boolean allEq = true;
                    for (int i = 2; i <= wtype.getMaxRange(); i++) {
                        if (attackValue[i - 1] != attackValue[i]) {
                            allEq = false;
                            break;
                        }
                    }
                    StringBuilder avString = new StringBuilder();
                    avString.append(attackValue[RangeType.RANGE_SHORT]);
                    if (!allEq) {
                        for (int i = 2; i <= wtype.getMaxRange(); i++) {
                            avString.append('/').append(attackValue[i]);
                        }
                    }
                    return avString.toString();
                }
                // Damage for non-Aeros
                if (wtype instanceof InfantryWeapon wi) {
                    return Double.toString(wi.getInfantryDamage());
                }

                if (wtype.getDamage() == WeaponType.DAMAGE_VARIABLE) {
                    if (wtype.getDamage(1) <= 0) {
                        return "0";
                    } else {
                        return wtype.getDamage(wtype.getShortRange()) + "/"
                              + wtype.getDamage(wtype.getMediumRange()) + "/"
                              + wtype.getDamage(wtype.getLongRange());
                    }
                } else if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTERTABLE) {
                    if (wtype instanceof HAGWeapon) {
                        return wtype.getRackSize() + "";
                    } else if (wtype instanceof MekMortarWeapon) {
                        return "Special";
                    } else if (wtype instanceof MissileWeapon) {
                        int dmg;
                        if (wtype instanceof ThunderBoltWeapon) {
                            switch (wtype.getAmmoType()) {
                                case TBOLT_5:
                                    return "5";
                                case TBOLT_10:
                                    return "10";
                                case TBOLT_15:
                                    return "15";
                                case TBOLT_20:
                                    return "20";
                                default :
                                    return "0";
                            }
                        } else if ((wtype instanceof ATMWeapon)
                              ||(wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM)
                              || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_STREAK)
                              || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_ADVANCED)
                              || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_IMP)
                              || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_PRIMITIVE)
                              || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_TORPEDO)) {
                            dmg = 2;
                        } else {
                            dmg = 1;
                        }
                        return dmg + "/msl";
                    }
                    return "Cluster";
                } else if (wtype.getDamage() == WeaponType.DAMAGE_ARTILLERY) {
                    return wtype.getRackSize() + "A";
                } else if (wtype instanceof UACWeapon) {
                    return wtype.getDamage() + "/Shot";
                } else if (wtype.getDamage() < 0) {
                    return "Special";
                } else {
                    return Integer.toString(wtype.getDamage());
                }
            }

        private double getMaxDamage(Entity entity, WeaponType wtype) {
            if (entity instanceof Aero) {
                int[] attackValue = new int[RangeType.RANGE_EXTREME + 1];
                attackValue[RangeType.RANGE_SHORT] = wtype.getRoundShortAV();
                attackValue[RangeType.RANGE_MEDIUM] = wtype.getRoundMedAV();
                attackValue[RangeType.RANGE_LONG] = wtype.getRoundLongAV();
                attackValue[RangeType.RANGE_EXTREME] = wtype.getRoundExtAV();
                int maxDamage = attackValue[RangeType.RANGE_SHORT];
                for (int i = RangeType.RANGE_SHORT + 1; i <= wtype.getMaxRange(); i++) {
                    if (attackValue[i] > maxDamage) {
                        maxDamage = attackValue[i];
                    }
                }
                return maxDamage;
            }
            if (wtype instanceof InfantryWeapon iw) {
                int infantryCount = 1;
                if (entity.isConventionalInfantry()) {
                    infantryCount = entity.getInternal(Infantry.LOC_INFANTRY);
                } else if (entity instanceof BattleArmor ba) {
                    infantryCount = ba.getNumberActiverTroopers();
                }
                return iw.getInfantryDamage() * infantryCount;
            }
            if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTERTABLE) {
                int perMissile = 1;
                if ((wtype instanceof SRMWeapon) || (wtype instanceof SRTWeapon) || (wtype instanceof MMLWeapon)) {
                    perMissile = 2;
                }
                return wtype.getRackSize() * perMissile;
            } else if (wtype.getDamage() == WeaponType.DAMAGE_VARIABLE) {
                return Math.max(0, wtype.getDamage(1));
            } else if (wtype.getDamage() == WeaponType.DAMAGE_SPECIAL) {
                return 0;
            } else if (wtype.getDamage() == WeaponType.DAMAGE_ARTILLERY) {
                return wtype.getRackSize();
            }
            int damage = wtype.getDamage();
            if (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.AC_ROTARY) {
                damage *= 6;
            } else if ((wtype.getAmmoType() == AmmoType.AmmoTypeEnum.AC_ULTRA)
                  || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.AC_ULTRA_THB)) {
                damage *= 2;
            }
            return damage;

        }

        private String cleanupName(String name) {
            // Remove any text in parentheses, e.g. "Laser (Large)"
            return name.replaceAll("\\s*[(\\[].*?[)\\]]", "");
        }

        private void addWeaponEntry(HashMap<String, ExportInventoryEntry> list, Entity entity, WeaponType type,
              String location, int locId) {
            final String name = type.getShortName();
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                list.get(key).q += 1;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = getWeaponCategory(type);
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                entry.d = getDamage(entity, type);
                entry.r = getWeaponRange(entity, type);
                entry.m = getMinRange(entity, type);
                entry.md = String.valueOf(Math.floor(getMaxDamage(entity, type)));
                if (type.hasFlag(WeaponTypeFlag.F_ONESHOT)) {
                    entry.os = 1; // If the weapon is oneshot
                } else
                if (type.hasFlag(WeaponTypeFlag.F_DOUBLE_ONESHOT)) {
                    entry.os = 2; // If the weapon is double oneshot
                }
                entry.c = getCriticals(entity, type);
                list.put(key, entry);
            }
        }

        public Components(Entity entity) {
            if (entity.usesWeaponBays()) {
                parseBays(this.comps, entity);
            } else {
                parseComponents(this.comps, entity);
            }
        }

        private ExportInventoryEntry addWeaponBay(HashMap<String, ExportInventoryEntry> list, Entity entity, WeaponType type,
              String location, int locId) {
            String key = UUID.randomUUID().toString();
            final String name = type.getShortName();
            ExportInventoryEntry entry = new ExportInventoryEntry();
            entry.id = type.getInternalName();
            entry.n = cleanupName(name);
            entry.t = getWeaponCategory(type);
            entry.q = 1;
            entry.p = locId;
            entry.l = location;
            list.put(key, entry);
            return entry;
        }


        private void parseBays(HashMap<String, ExportInventoryEntry> list, Entity entity) {
            for (WeaponMounted bay : entity.getWeaponList()) {
                HashMap<String, ExportInventoryEntry> bayList = new HashMap<>();
                for (WeaponMounted weaponMounted : bay.getBayWeapons()) {
                    addWeaponEntry(bayList, entity, weaponMounted.getType(),"", 0);
                }
                ExportInventoryEntry weaponBay = addWeaponBay(list, entity, bay.getType(),
                      entity.joinLocationAbbr(bay.allLocations(), 2), bay.getLocation());
                weaponBay.bay = bayList.values();
                //TODO: add artemisIV, artemisV and apollo
            }
        }

        private void parseComponents(HashMap<String, ExportInventoryEntry> list, Entity entity) {
            if ((entity instanceof Infantry inf) && !(entity instanceof BattleArmor)) {
                if (null != inf.getPrimaryWeapon()) {
                    addWeaponEntry(list, entity, inf.getPrimaryWeapon(), "Troop", 0);
                }
                if (null != inf.getSecondaryWeapon()) {
                    addWeaponEntry(list, entity, inf.getSecondaryWeapon(), "Field", 1);
                }
            }

            List<Mounted<?>> mountedList = entity.getEquipment();
            for (Mounted<?> m : mountedList) {
                if (m.isWeaponGroup()) {
                    continue;
                }
                if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType()
                      != AmmoType.AmmoTypeEnum.COOLANT_POD)) {
                    continue;
                }
                if ((entity instanceof QuadVee)
                      && (m.getType() instanceof MiscType)
                      && m.getType().hasFlag(MiscType.F_TRACKS)) {
                    continue;
                }
                if ((entity instanceof BattleArmor)
                      && (m.getCriticals() > 0)
                      && (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)) {
                    continue;
                }
                if (m.getLocation() == Entity.LOC_NONE) {
                    continue;
                }
                if (m.getType() instanceof WeaponType wtype) {

                    if (wtype.hasFlag(WeaponTypeFlag.INTERNAL_REPRESENTATION)) {
                        continue;
                    }
                    if ((entity instanceof Infantry inf) && !(entity instanceof BattleArmor)) {
                        continue; // Infantry weapons are handled separately at the beginning
                    }

                    addWeaponEntry(list, entity, wtype, entity.joinLocationAbbr(m.allLocations(), 2),
                          m.getLocation());
                    // if this is a weapon bay, then cycle through weapons
                    if ((m instanceof WeaponMounted wm) && (wtype instanceof BayWeapon)) {
                        for (WeaponMounted bm : wm.getBayWeapons()) {
                            addWeaponEntry(list, entity, bm.getType(), entity.joinLocationAbbr(wm.allLocations(), 2),
                                  wm.getLocation());
                        }
                    }
                } else if ((m instanceof MiscMounted mm)) {
                    MiscType mtype = mm.getType();
                    if (mtype.hasFlag(F_ARMOR_KIT)) {
                        continue;
                    }
                    if (UnitUtil.isArmorOrStructure(mtype)) {
                        continue;
                    }
                    if (UnitUtil.isJumpJet(mtype)) {
                        continue;
                    }
                    if (UnitUtil.isHeatSink(mtype)) {
                        continue;
                    }
                    if (entity instanceof BattleArmor) {
                        if ((mtype instanceof MiscType)
                              && ((mtype.hasFlag(F_AP_MOUNT) && !mtype.hasFlag(F_BA_MANIPULATOR))
                              || mtype.hasAnyFlag(
                              F_FIRE_RESISTANT,
                              F_ARTEMIS,
                              F_ARTEMIS_V,
                              F_APOLLO,
                              F_HARJEL,
                              F_MASS,
                              F_DETACHABLE_WEAPON_PACK,
                              F_MODULAR_WEAPON_MOUNT
                        ))) {
                            continue;
                        }
                    } else {
                        if (UnitUtil.isFixedLocationSpreadEquipment(mtype) && !mtype.hasFlag(MiscType.F_TALON)) {
                            continue;
                        }
                    }
                    if (mtype.hasFlag(MiscType.F_CLUB) || mtype.hasFlag(MiscType.F_HAND_WEAPON) || mtype.hasFlag(
                          MiscType.F_TALON)) {
                        if (mtype.isVibroblade()) {
                            // manually set vibros to active to get correct damage
                            m.setMode(1);
                        }
                        addPhysicalWeapon(list, entity, mm, entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation());
                    } else if (m.is(EquipmentTypeLookup.COOLANT_POD)) {
                        addMiscEntry(list, entity, mtype, entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation());
                    } else {
                        addMiscEntry(list, entity, mtype, entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation());
                    }
                }
            }
            // Special entries for mek features which aren't represented by a MiscType.
            if (entity instanceof Mek mek && mek.hasRiscHeatSinkOverrideKit()) {
                var mounted = new MiscMounted(entity, new MiscType() {{
                    name = "RISC Heat Sink Override Kit";
                    shortName = "RISC HS Override Kit";
                    internalName = "RISC Heat Sink Override Kit";
                }});
                mounted.setLocation(Mek.LOC_CT);
                addMiscEntry(list, entity, mounted.getType(), entity.joinLocationAbbr(mounted.allLocations(), 2), mounted.getLocation());
            }
            if (entity instanceof Mek mek && mek.hasFullHeadEject()) {
                var mounted = new MiscMounted(entity, new MiscType() {{
                    name = "Full Head Ejection System";
                    shortName = "Full Head Eject System";
                    internalName = "Full Head Ejection System";
                }});
                mounted.setLocation(Mek.LOC_HEAD);
                addMiscEntry(list, entity, mounted.getType(), entity.joinLocationAbbr(mounted.allLocations(), 2), mounted.getLocation());
            }
        }

        private void addMiscEntry(HashMap<String, ExportInventoryEntry> list, Entity entity, MiscType type,
              String location, int locId) {
            final String name = type.getShortName();
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                list.get(key).q += 1;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = "O"; // Other
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                entry.c = getCriticals(entity, type);
                list.put(key, entry);
            }
        }

        private void addPhysicalWeapon(HashMap<String, ExportInventoryEntry> list, Entity entity, MiscMounted mounted,
              String location, int locId) {
            MiscType type = mounted.getType();
            String damage;
            String maxDamage;
            if (type.hasFlag(MiscType.F_TALON)) {
                damage = Integer.toString(KickAttackAction.getDamageFor(entity, Mek.LOC_LLEG, false));
                maxDamage = damage;
            } else
            if (type.hasSubType(MiscType.S_CLAW) || type.hasSubType(MiscType.S_CLAW_THB)) {
                damage = Integer.toString((int) Math.ceil(entity.getWeight() / 7.0));
                maxDamage = damage;
            } else {
                damage = Integer.toString(ClubAttackAction.getDamageFor(entity, (MiscMounted) mounted, false, false));
                if ((entity instanceof BipedMek) && ((BipedMek) entity).canZweihander()) {
                    maxDamage = Integer.toString(ClubAttackAction.getDamageFor(entity, (MiscMounted) mounted, false,
                          true));
                } else {
                    maxDamage = damage;
                }
            }
            final String name = type.getShortName();
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                list.get(key).q += 1;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = "P"; // Physical weapon
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                entry.d = damage;
                entry.md = String.valueOf(Math.floor(Double.parseDouble(maxDamage)));
                entry.c = getCriticals(entity, type);
                list.put(key, entry);
            }
        }

    }

    public static class UnitData {
        public String name; // Unique name of the unit, used for deduplication
        public int id; // Unique identifier for the unit on MUL
        public String chassis; // Name of the unit (Chassis)
        public String model; // Model of the unit
        public int year; // Year of introduction
        public String weightClass; // Weight class
        public double tons; // Weight in tons, rounded to the nearest integer
        public int bv; // Battle Value, rounded to the nearest integer
        public int pv; // Point Value, rounded to the nearest integer
        public long cost; // Cost in C-Bills, rounded to the nearest integer
//        public int level; // Tech Level
        public String level; // Tech level as a string, e.g. "Introductory", "Standard", etc.
        public String techBase;
        public String techRating;
        public String engine;
        public String type; // Major type, "Mek", "Vehicle", etc.
        public String subtype; // Subtype, "Assault", "Light", etc.
        public String source; // Source of the unit, e.g. "TRO 3050"
        public String role; // Role, "Assault", "Scout", etc.
        public int armor; // Total armor
        public int internal; // Total internal structure
        public int heat; // Total heat generation
        public int dissipation; // Heat capacity
        public int walk; // Walk MP
        public int run; // Run MP
        public int jump; // Jump MP
        public String c3; // C3 system, if applicable
        public List<String> quirks;
        public Collection<ExportInventoryEntry> comp;
        public int su; // 1 for small units (Battle Armor, ProtoMek, Infantry), 0 for others
        public int crewSize; // Number of crew members, if applicable
        public List<String> sheets; // Path to the SVG sheet
//        public String summary;



        private static String unitTypeAsString(Entity entity) {
            String result = "";
//            if (entity.isPrimitive()) {
//                result += Messages.getString("MekView.unitType.primitive") + " ";
//            }
            if ((entity.isDropShip() || entity.isSmallCraft())) {
                if (!entity.isMilitary()) {
                    result += Messages.getString("MekView.unitType.civilian") + " ";
                }
                if (entity.isAerodyne()) {
                    result += Messages.getString("MekView.unitType.aerodyne") + " ";
                } else {
                    result += Messages.getString("MekView.unitType.spheroid") + " ";
                }
            }
            if (entity instanceof Infantry inf && !entity.isBattleArmor() && inf.isMechanized()) {
                result += Messages.getString("MekView.unitType.mechanized") + " ";
            } else if (entity.getMovementMode().isMotorizedInfantry()) {
                result += Messages.getString("MekView.unitType.motorized") + " ";
            }
//            if (entity.isSuperHeavy()) {
//                result += Messages.getString("MekView.unitType.superHeavy") + " ";
//            }
            if (entity instanceof LandAirMek) {
                result += "Land-Air "; // Special case for Land-Air Meks
            } else if (entity.isTripodMek()) {
                result += Messages.getString("MekView.unitType.tripod") + " ";
            } else if (entity instanceof QuadVee) {
                result += Messages.getString("MekView.unitType.quadVee") + " ";
            } else if (entity.isQuadMek() || (entity instanceof ProtoMek pm && pm.isQuad())) {
                result += Messages.getString("MekView.unitType.quad") + " ";
            }
            if (entity.isIndustrialMek()) {
                result += Messages.getString("MekView.unitType.industrial") + " ";
            }
            if (entity.isConventionalFighter()) {
                result += Messages.getString("MekView.unitType.conventional") + " ";
            } else if (entity.isAerospaceFighter()) {
                result += Messages.getString("MekView.unitType.aerospace") + " ";
            }
            if (entity.isCombatVehicle() && !(entity instanceof GunEmplacement)) {
                result += Messages.getString("MekView.unitType.combat") + " ";
            } else if (entity.isFixedWingSupport()) {
                result += Messages.getString("MekView.unitType.fixedWingSupport") + " ";
            } else if (entity.isSupportVehicle()) {
                result += Messages.getString("MekView.unitType.support") + " ";
            }

            if (entity.isSpaceStation()) {
                if (entity.isMilitary()) {
                    result += Messages.getString("MekView.unitType.military") + " ";
                } else {
                    result += Messages.getString("MekView.unitType.civilian") + " ";
                }
                result += Messages.getString("MekView.unitType.spaceStation");
            } else if (entity.isJumpShip()) {
                result += Messages.getString("MekView.unitType.jumpShip");
            } else if (entity.isWarShip()) {
                result += Messages.getString("MekView.unitType.warShip");
            } else if (entity.isDropShip()) {
                result += Messages.getString("MekView.unitType.dropShip");
            } else if (entity.isSmallCraft()) {
                result += Messages.getString("MekView.unitType.smallCraft");
            } else if (entity.isProtoMek()) {
                result += Messages.getString("MekView.unitType.protoMek");
            } else if (entity.isBattleArmor()) {
                result += Messages.getString("MekView.unitType.battleArmor");
            } else if (entity.isConventionalInfantry()) {
                result += Messages.getString("MekView.unitType.infantry");
            } else if (entity.isMek() && !entity.isIndustrialMek()) {
                result += Messages.getString("MekView.unitType.battleMek");
            } else if (entity instanceof GunEmplacement) {
                result += Messages.getString("MekView.unitType.gunEmplacement");
            } else if (entity.isIndustrialMek()) {
                result += Messages.getString("MekView.unitType.onlyMek");
            } else if (entity.isVehicle() || entity.isFixedWingSupport()) {
                result += Messages.getString("MekView.unitType.vehicle");
            } else if (entity.isFighter() && !entity.isSupportVehicle()) {
                result += Messages.getString("MekView.unitType.fighter");
            } else if (entity instanceof HandheldWeapon) {
                result += Messages.getString("MekView.unitType.handHeld");
            }
            String addendum = "";
            if (entity.isVehicle() && !entity.isSupportVehicle()) {
                if (entity.getMovementMode().isSubmarine()) {
                    addendum += Messages.getString("MekView.unitType.submarine");
//                } else if (entity.getMovementMode().isVTOL()) {
//                    addendum += Messages.getString("MekView.unitType.vtol");
                } else if (entity.getMovementMode().isHover()) {
                    addendum += Messages.getString("MekView.unitType.hover");
                } else if (entity.getMovementMode().isRail()) {
                    addendum += Messages.getString("MekView.unitType.rail");
                } else if (entity.getMovementMode().isNaval() || entity.getMovementMode().isHydrofoil()) {
                    addendum += Messages.getString("MekView.unitType.naval");
                } else if (entity.getMovementMode().isWiGE()) {
                    addendum += Messages.getString("MekView.unitType.wige");
                }
            }
            if (addendum.isBlank()) {
                return result.trim();
            } else {
                return addendum.trim();
            }
        }

        private String getC3Property(Entity entity) {
            for (WeaponMounted m : entity.getWeaponList()) {
                if (m.getType().hasFlag(WeaponType.F_C3M) || m.getType().hasFlag(WeaponType.F_C3MBS)) {
                    return m.getType().getShortName();
                }
            }
            for (MiscMounted m : entity.getMisc()) {
                if (m.getType().isC3Equipment()) {
                    return m.getType().getShortName();
                }
            }
            return "None";
        }

        private List<String> getQuirks(Entity entity) {
            List<String> sj = new ArrayList<>();
            Quirks quirks = entity.getQuirks();
            for (Enumeration<IOptionGroup> optionGroups = quirks.getGroups(); optionGroups.hasMoreElements();) {
                IOptionGroup optiongroup = optionGroups.nextElement();
                if (quirks.count(optiongroup.getKey()) > 0) {
                    for (Enumeration<IOption> options = optiongroup.getOptions(); options.hasMoreElements();) {
                        IOption option = options.nextElement();
                        if (option != null && option.booleanValue()) {
                            sj.add(option.getDisplayableNameWithValue());
                        }
                    }
                }
            }
            return sj;
        }

        public UnitData(MekSummary mekSummary, Entity entity, RecordSheetOptions options) {
            this.id = entity.getMulId();
            this.chassis = entity.getFullChassis();
            this.model = entity.getModel();
            this.year = entity.getYear();
            this.weightClass = entity.getWeightClassName();
            this.tons = entity.getWeight();
            this.bv = entity.getBvCalculator().calculateBV(false,true);
            this.cost = Math.round(entity.getCost(false));
            this.techBase = formatTechBase(entity);
            this.techRating = entity.getFullRatingName();
            this.level = formatRulesLevel(entity, options);
            this.engine = (entity.getEngine() != null) ? entity.getEngine().getShortEngineName() : null;
            // This is over-convoluted for no reason, should be simplified and unified at the source
            final String majorType = Entity.getEntityMajorTypeName(entity.getEntityType());
            final String type = Entity.getEntityTypeName(entity.getEntityType());
            int unitTypeId = UnitType.determineUnitTypeCode(mekSummary.getUnitType());
            if (entity.isNaval()) {
                this.type = unitTypes.get(unitTypeId);
            } else {
                this.type = majorType;
            }
            this.subtype = unitTypeAsString(entity).trim();
//            if (mekSummary.isSupport()) {
//                this.subtype = unitTypes.get(UnitType.SIZE);
//            } else
//            if (majorType.equals(type)) {
//                this.subtype = unitTypes.get(unitTypeId);
//            } else {
//                this.subtype = type;
//            }
            this.source = entity.getSource();
            this.role = formatRole(entity);
            this.armor = entity.getTotalOArmor();
            this.internal = entity.getTotalInternal();
            this.heat = UnitUtil.getTotalHeatGeneration(entity);
            this.dissipation = entity.getHeatCapacity();
            this.walk = entity.getWalkMP();
            this.run = entity.getRunMP();
            this.jump = entity.getJumpMP();
            this.crewSize = entity.getCrew().getSlotCount();
            this.comp = (new Components(entity)).getComp();
            this.c3 = getC3Property(entity);
            this.quirks = getQuirks(entity);
            this.sheets = new ArrayList<>();
//            final MekView mekView = new MekView(entity, false, false, ViewFormatting.HTML);
//            this.summary = mekView.getMekReadout();
        }

        private String formatTechBase(Entity entity) {
            if (entity.isMixedTech()) {
                return "Mixed";
            } else if (entity.isClan()) {
                return "Clan";
            } else {
                return "Inner Sphere";
            }
        }

        private String formatRole(Entity entity) {
            UnitRole role = entity.getRole();
            if (role != UnitRole.UNDETERMINED) {
                return role.toString();
            } else {
                return "None";
            }
        }
    }

    protected static String formatRulesLevel(Entity entity, RecordSheetOptions options) {
        SimpleTechLevel level;
        if (options.useEraBaseProgression()) {
            level = entity.getSimpleLevel(entity.getYear(), entity.isClan());
        } else {
            level = entity.getStaticTechLevel();
        }
        return level.toString().substring(0, 1)
                + level.toString().substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        logger.info("Starting SVG Mass Printer...");
        final String rootPath = ROOT_FOLDER + File.separator + SHEETS_DIR;
        File sheetsDir = new File(rootPath);

        if (sheetsDir.exists()) {
            try (var walk = Files.walk(sheetsDir.toPath())) {
                walk.sorted(Comparator.reverseOrder())
                      .map(Path::toFile)
                      .forEach(file -> {
                          if (!file.delete()) {
                              logger.warn("Failed to delete file: {}", file.getPath());
                          }
                      });
                logger.info("Deleted existing sheets directory: {}", sheetsDir.getPath());
            } catch (IOException e) {
                logger.error("Failed to delete sheets directory: {}", e.getMessage());
            }
        }
        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            if (!sheetsDir.mkdirs()) {
                logger.error("Failed to create sheets directory: {}", sheetsDir.getPath());
                System.exit(1);
            } else {
                logger.info("Sheets directory created: {}", sheetsDir.getPath());
            }
        }

        for (int i = 0; i < UnitType.SIZE; i++) {
            // the AERO type does not match any units and there are no preconstructed life boats or escape pods
            if (i != UnitType.AERO) {
                unitTypes.put(i, UnitType.getTypeDisplayableName(i));
            }
        }
        unitTypes.put(UnitType.SIZE, Messages.getString("MekSelectorDialog.SupportVee"));

        HashSet<String> processedFiles = new HashSet<>();
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        CConfig.setParam(CConfig.RS_FONT, TYPEFACE);
        RecordSheetOptions recordSheetOptions = getRecordSheetOptions();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);

        int processedCount = 0;
        MekSummary[] meks = cache.getAllMeks();
        logger.info("Processing {} meks...", meks.length);
        long timestamp = System.currentTimeMillis();
        
        try (FileWriter versionWriter = new FileWriter(ROOT_FOLDER + File.separator + VERSION_FILE)) {
            versionWriter.write("{\"units\":"+timestamp+", \"equipment\":"+timestamp+"}");
            logger.info("Version file written: {}", timestamp);
        } catch (IOException e) {
            logger.error("Failed to write version file: {}", e.getMessage());
        }
        
        PageFormat pf = new PageFormat();
        PaperSize paperDef = recordSheetOptions.getPaperSize();
        HashMap<String, Entity> uniqueUnitTypes = new HashMap<>();
        try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FILE)) {
            jsonWriter.write("{\"version\":"+ timestamp +",\n");
            jsonWriter.write("\"units\":[\n");
            boolean firstUnit = true;
            for (MekSummary mekSummary : meks) {
//                if (!mekSummary.getName().contains("Clan Anti-Infantry")) {
//                    continue;
//                }
//                 logger.info("{}", mekSummary.getName());

                // if (i > 10) break; // For testing, remove this line in production
                /*
                 * if (!mekSummary.isProtoMek() && !mekSummary.isCombatVehicle()) {
                 * continue;
                 * }
                 *
                 * // 1 - uncomment this block and cycle all the start characters A-Z (only
                 * // uppercase)
                 * if (!mekSummary.getName().toUpperCase().startsWith("C")) {
                 * continue;
                 * }
                 *
                 * // 2 - uncomment this block, comment the above block, run once more
                 * if (mekSummary.getName().toUpperCase().charAt(0) <= 'Z' &&
                 * mekSummary.getName().toUpperCase().charAt(0) >= 'A') {
                 * continue;
                 * }
                 *
                 */
                Entity entity = mekSummary.loadEntity();
                if ((entity == null) || (entity instanceof GunEmplacement)) {
//                    logger.info("Skipping: {}", mekSummary.getName());
                    System.gc();
                    continue;
                }
                UnitUtil.updateLoadedUnit(entity);
                for (int i = 0; i < entity.getCrew().getSlotCount(); i++) {
                    entity.getCrew().setName("", i);
                }
                if (entity.getId() == -1) {
                    entity.setId(entity.getGame().getNextEntityId());
                }

                C3Util.wireC3(entity.getGame(), entity);
                String svgPath = FluffImageHelper.getFluffPath(entity).toLowerCase().replaceAll("[^a-zA-Z0-9_]", "");
                File sheetPath = new File(sheetsDir.getPath(), svgPath);

                if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                    logger.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }
                String name = generateName(entity);
                if (processedFiles.contains(name)) {
                    logger.warn("Duplication detected! Hash {} already exists for {} {}", name,
                          mekSummary.getFullChassis(), mekSummary.getModel());
                    continue;
                }
                processedFiles.add(name);

                UnitData unitData = new UnitData(mekSummary, entity, recordSheetOptions);
                unitData.name = name;
                boolean isSmallUnit = entity.isBattleArmor() || entity.isProtoMek() || entity.isInfantry();
                try {
                    // List<Entity> units = printableListOfUnits(entity);
                    List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(List.of(entity), true, recordSheetOptions);
                    if (sheets.isEmpty()) {
                        logger.error("No sheets generated for {}", mekSummary.getName());
                        System.exit(1);
                    }
                    if (SKIP_SVG) {
                        int pageCount = 0;
                        for (PrintRecordSheet sheet : sheets) {
                            pageCount += sheet.getPageCount();
                        }
                        for (int idx = 0; idx < pageCount; idx++) {
                            String baseSvgFilename = unitData.name + (idx > 0 ? "_" + idx : "");
                            String unoptimizedSvgFilename = baseSvgFilename + ".svg";
                            String pathToSave = (svgPath + File.separator + unoptimizedSvgFilename).replace("\\", "/");
                            unitData.sheets.add(pathToSave);
                        }
                    } else {
                        List<Document> svgDocs = new ArrayList<>();
                        for (PrintRecordSheet sheet : sheets) {
                            if (sheet instanceof PrintSmallUnitSheet) {
                                pf.setPaper(paperDef.createPaper());
                            } else {
                                pf.setPaper(paperDef.createPaper());
//                                pf.setPaper(paperDef.createPaper(DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS));
                            }
                            int pageCount = sheet.getPageCount();
                            for (int pageIndexInSheet = 0; pageIndexInSheet < pageCount; pageIndexInSheet++) {
                                sheet.createDocument(pageIndexInSheet, pf, true);
                                if (pageCount > 1) {
                                    // Multiple pages, clone the SVG document for each page to prevent overwriting
                                    svgDocs.add((Document) sheet.getSVGDocument().cloneNode(true));
                                } else {
                                    // Single page, add directly
                                    svgDocs.add(sheet.getSVGDocument());
                                }
                            }
                        }
                        if (svgDocs.isEmpty()) {
                            logger.error("No SVG documents for {}", mekSummary.getName());
                            System.exit(1);
                        }
                        int idx = 0;
                        for (Document svgDoc : svgDocs) {
                            SVGOptimizer.optimize((SVGDocument) svgDoc);
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "no");
                            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                            String baseSvgFilename = unitData.name + (idx > 0 ? "_" + idx : "");
                            String unoptimizedSvgFilename = baseSvgFilename + ".svg";
                            File finalUnoptimizedFilename = new File(sheetPath, unoptimizedSvgFilename);
                            try (FileOutputStream fos = new FileOutputStream(finalUnoptimizedFilename)) {
                                DOMSource source = new DOMSource(svgDoc);
                                StreamResult result = new StreamResult(fos);
                                transformer.transform(source, result);
                            }
                            String pathToSave = (svgPath + File.separator + unoptimizedSvgFilename).replace("\\", "/");
                            unitData.sheets.add(pathToSave);
                            idx++;
                        }
                    }
                    // logger.info("Printed: {}", finalFilename);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                    System.exit(1);
                }

                // Set additional fields
                unitData.pv = mekSummary.getPointValue();
                unitData.su = isSmallUnit ? 1 : 0; // 1 for small units, 0 for others

                String jsonLine = mapper.writeValueAsString(unitData);
                if (!firstUnit) {
                    jsonWriter.write(",\n");
                }
                jsonWriter.write(jsonLine);

                firstUnit = false;
                processedCount++;
                if (!uniqueUnitTypes.containsKey(unitData.type)) {
                    uniqueUnitTypes.put(unitData.type, entity);
                }
                System.gc();
            }
            jsonWriter.write("\n]}");
        } catch (IOException e) {
            logger.error("Failed to write JSON Lines file: {}", e.getMessage());
        }

        logger.info("Done. Processed {} units.", processedCount);

        if (!SKIP_EQUIPMENT) {
            processedCount=0;
            Map<String, Map<String, Object>> equipmentJsonMap = new HashMap<>();
            for (Map.Entry<String, Entity> entry : uniqueUnitTypes.entrySet()) {
                String unitTypeKey = entry.getKey();
                Entity entity = entry.getValue();
                EquipmentTableModel equipmentTableModel = new EquipmentTableModel(entity, null);
                equipmentTableModel.setData((ArrayList<EquipmentType>) EquipmentType.allTypes());
                List<String> normalizedKeys = new ArrayList<>();
                for (int j = 1; j < equipmentTableModel.getColumnCount(); j++) { // Skip column 0, is the name
                    String key = equipmentTableModel.getColumnName(j);
                    if ("Slots".equals(key)) {
                        key = "Crit";
                    }
                    // Lowercase and remove non-alphanumeric characters
                    key = key.toLowerCase().replaceAll("[^a-z0-9]", "");
                    normalizedKeys.add(key);
                }

                Map<String, Object> equipmentMap = new HashMap<>();
                for (int i = 0; i < equipmentTableModel.getRowCount(); i++) {
                    EquipmentType eq = equipmentTableModel.getType(i);
                    Map<String, Object> rowMap = new HashMap<>();
                    rowMap.put("name", eq.getName()); // Use full name
                    for (int j = 1; j < equipmentTableModel.getColumnCount(); j++) {
                        String key = normalizedKeys.get(j - 1);
                        Object value = equipmentTableModel.getValueAt(i, j);
                        rowMap.put(key, value);
                    }
                    equipmentMap.put(eq.getInternalName(), rowMap);
                }
                equipmentJsonMap.put(unitTypeKey, equipmentMap);
            }
            try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + EQUIPMENT_FILE)) {
                jsonWriter.write("{\"version\":"+ timestamp +",\n");
                jsonWriter.write("\"equipment\":{\n");
                boolean firstType = true;
                for (Map.Entry<String, Map<String, Object>> typeEntry : equipmentJsonMap.entrySet()) {
                    if (!firstType) {
                        jsonWriter.write(",\n");
                    }
                    jsonWriter.write("\"" + typeEntry.getKey() + "\":");
                    String equipmentJson = mapper.writeValueAsString(typeEntry.getValue());
                    jsonWriter.write(equipmentJson);
                    firstType = false;
                }
                jsonWriter.write("\n}}");
            } catch (IOException e) {
                logger.error("Failed to write JSON Lines file: {}", e.getMessage());
            }
            logger.info("Done. Processed {} equipments.", processedCount);
        }
        System.exit(0);
    }

    private static RecordSheetOptions getRecordSheetOptions() {
        RecordSheetOptions recordSheetOptions = new RecordSheetOptions();
        recordSheetOptions.setColor(RecordSheetOptions.ColorMode.LOGO_ONLY);
        recordSheetOptions.setHeatScaleMarker(RecordSheetOptions.HeatScaleMarker.ARROW);
        recordSheetOptions.setC3inBV(true);
        recordSheetOptions.setBoldType(true);
        recordSheetOptions.setHeatProfile(true);
        recordSheetOptions.setCondensedReferenceCharts(true);
        recordSheetOptions.setRole(true);
        recordSheetOptions.setEraIcon(true);
        recordSheetOptions.setQuirks(true);
        recordSheetOptions.setDamage(false);
        recordSheetOptions.setWeaponsOrder(WeaponSortOrder.RANGE_HIGH_LOW);
        recordSheetOptions.setPaperSize(PaperSize.US_LETTER);
        recordSheetOptions.setMergeIdenticalEquipment(false);
        return recordSheetOptions;
    }

    /**
     * Combines multiple SVG documents side by side into a single SVG
     * Assumes Letter size (612x792 points) for each sheet
     */
    private static Document combineSVGDocuments(List<Document> svgDocs) throws Exception {
        if (svgDocs.isEmpty()) {
            throw new IllegalArgumentException("No SVG documents to combine");
        }
        Document firstDoc = svgDocs.get(0);
        Element firstRoot = firstDoc.getDocumentElement();
        double sheetWidth = getSheetWidth(firstRoot);
        double sheetHeight = getSheetHeight(firstRoot);
        logger.debug("Detected sheet dimensions: {}x{}", sheetWidth, sheetHeight);
    
        // Create new SVG document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document combinedDoc = builder.newDocument();
        
        // Create root SVG element
        Element svgRoot = combinedDoc.createElementNS("http://www.w3.org/2000/svg", "svg");
        svgRoot.setAttribute("version", "1.1");
        svgRoot.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        svgRoot.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
        
        // Set combined dimensions (width = number of sheets * sheet width)
        double totalWidth = svgDocs.size() * sheetWidth;
        svgRoot.setAttribute("width", String.valueOf(totalWidth));
        svgRoot.setAttribute("height", String.valueOf(sheetHeight));
        svgRoot.setAttribute("viewBox", String.format("0 0 %.1f %.1f", totalWidth, sheetHeight));
        
        combinedDoc.appendChild(svgRoot);
        
        // Add each sheet side by side
        for (int i = 0; i < svgDocs.size(); i++) {
            Document sourceDoc = svgDocs.get(i);
            Element sourceRoot = sourceDoc.getDocumentElement();
            
            // Create a group for this sheet with translation
            Element group = combinedDoc.createElementNS("http://www.w3.org/2000/svg", "g");
            double xOffset = i * sheetWidth;
            group.setAttribute("transform", String.format("translate(%.1f,0)", xOffset));
            
            // Copy all child elements from source SVG to the group
            copyChildElements(sourceRoot, group, combinedDoc);
            
            svgRoot.appendChild(group);
        }
        
        return combinedDoc;
    }

    /**
     * Extracts the width from an SVG root element
     */
    private static double getSheetWidth(Element svgRoot) {
        String widthAttr = svgRoot.getAttribute("width");
        if (!widthAttr.isEmpty()) {
            // Remove units (pt, px, etc.) and parse
            String numericWidth = widthAttr.replaceAll("[^0-9.]", "");
            try {
                return Double.parseDouble(numericWidth);
            } catch (NumberFormatException e) {
                logger.warn("Could not parse width '{}', using viewBox", widthAttr);
            }
        }
        
        // Fallback to viewBox width
        String viewBox = svgRoot.getAttribute("viewBox");
        if (!viewBox.isEmpty()) {
            String[] parts = viewBox.split("\\s+");
            if (parts.length >= 3) {
                try {
                    return Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    logger.warn("Could not parse viewBox width from '{}'", viewBox);
                }
            }
        }
        
        // Final fallback to Letter size width
        logger.warn("Could not determine SVG width, using default 612pt");
        return 612.0;
    }

    /**
     * Extracts the height from an SVG root element
     */
    private static double getSheetHeight(Element svgRoot) {
        String heightAttr = svgRoot.getAttribute("height");
        if (!heightAttr.isEmpty()) {
            // Remove units (pt, px, etc.) and parse
            String numericHeight = heightAttr.replaceAll("[^0-9.]", "");
            try {
                return Double.parseDouble(numericHeight);
            } catch (NumberFormatException e) {
                logger.warn("Could not parse height '{}', using viewBox", heightAttr);
            }
        }
        
        // Fallback to viewBox height
        String viewBox = svgRoot.getAttribute("viewBox");
        if (!viewBox.isEmpty()) {
            String[] parts = viewBox.split("\\s+");
            if (parts.length >= 4) {
                try {
                    return Double.parseDouble(parts[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Could not parse viewBox height from '{}'", viewBox);
                }
            }
        }
        
        // Final fallback to Letter size height
        logger.warn("Could not determine SVG height, using default 792pt");
        return 792.0;
    }


    /**
     * Recursively copies child elements from source to target
     */
    private static void copyChildElements(Element source, Element target, Document targetDoc) {
        NodeList children = source.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            Node importedNode = targetDoc.importNode(child, true);
            target.appendChild(importedNode);
        }
    }

    private static String generateName(Entity entity) {
        ASUnitType asUnitType = ASUnitType.getUnitType(entity);
        return String.format("%s%s_%s", (asUnitType != ASUnitType.UNKNOWN) ? asUnitType.name() : "",
                    entity.getChassis(),
                    entity.getModel())
                            .replaceAll("[^a-zA-Z0-9_]", "")
                            .replaceAll("_+", "_")
                            .replaceAll("^_+|_+$", "");
    }

    private SVGMassPrinter() {
        throw new IllegalStateException();
    }
}
