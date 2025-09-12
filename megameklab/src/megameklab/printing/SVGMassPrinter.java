/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import megamek.MMConstants;
import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.Messages;
import megamek.client.ui.clientGUI.calculationReport.FlexibleCalculationReport;
import megamek.client.ui.tileset.MMStaticDirectoryManager;
import megamek.client.ui.tileset.MekTileset;
import megamek.client.ui.util.FluffImageHelper;
import megamek.common.*;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.*;
import megamek.common.equipment.enums.AmmoTypeFlag;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.loaders.MekSummary;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.units.*;
import megamek.common.actions.ClubAttackAction;
import megamek.common.actions.KickAttackAction;
import megamek.common.alphaStrike.ASUnitType;
import megamek.common.alphaStrike.AlphaStrikeElement;
import megamek.common.alphaStrike.conversion.ASConverter;
import megamek.common.annotations.Nullable;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.OptionsConstants;
import megamek.common.options.Quirks;
import megamek.common.util.C3Util;
import megamek.common.verifier.TestProtoMek;
import megamek.common.weapons.autoCannons.ACWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megamek.common.weapons.gaussRifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
import megamek.common.weapons.missiles.thuunderbolt.ThunderboltWeapon;
import megamek.common.weapons.mortars.MekMortarWeapon;
import megamek.common.weapons.other.clan.CLFussilade;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

/**
 * @author drake Generates SVG sheets for all units in the Mek Summary Cache and saves them
 */
public class SVGMassPrinter {
    private final static boolean SKIP_SVG = true; // Set to true to skip SVG generation
    private final static boolean SKIP_UNITS = false; // Set to true to skip units generation
    private final static boolean SKIP_EQUIPMENT = false; // Set to true to skip equipment generation

    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final String TYPEFACE = "Roboto";
    private static final String SHEETS_DIR = "sheets";
    private static final String VERSION_FILE = "version.json";
    private static final String UNIT_FILE = "units.json";
    private static final String EQUIPMENT_FILE = "equipment.json";
    private static final String ROOT_FOLDER = "../../svgexport";
    private static final int DEFAULT_MARGINS = 0; // Default margins for the page
    private final static RATGenerator RAT_GENERATOR = RATGenerator.getInstance();
    private final static MekTileset tileset = MMStaticDirectoryManager.getMekTileset();

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

    private static double getMaxDamage(Entity entity, WeaponType wtype) {
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
            return iw.getInfantryDamage();
        }
        if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTER_TABLE) {
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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Components {
        @JsonIgnore
        public HashMap<String, ExportInventoryEntry> comps = new HashMap<>();

        @JsonProperty("comp")
        public Collection<ExportInventoryEntry> getComp() {
            return comps.values();
        }

        private String getWeaponCategory(WeaponType eq) {
            if (eq.hasFlag(WeaponType.F_ENERGY)
                  || ((eq.hasFlag(WeaponType.F_PLASMA) && (eq.getAmmoType() == AmmoType.AmmoTypeEnum.PLASMA)))
                  || eq.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Energy)) {
                return "E";
            }
            if (eq.hasFlag(WeaponType.F_MISSILE) || eq.getAmmoType()
                  .getCategory()
                  .equals(AmmoType.AmmoCategory.Missile)) {
                return "M";
            }
            if (eq.hasFlag(WeaponType.F_BALLISTIC) || eq.getAmmoType()
                  .getCategory()
                  .equals(AmmoType.AmmoCategory.Ballistic)) {
                return "B";
            }
            if (eq.hasFlag(WeaponType.F_ARTILLERY) || eq.getAmmoType()
                  .getCategory()
                  .equals(AmmoType.AmmoCategory.Artillery)) {
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
            if (type.isVariableCriticalSlots()
                  && (entity.isSupportVehicle() || (entity instanceof Mek))) {
                // Only Meks and support vehicles require multiple slots for equipment
                return "V";
            } else if (entity.isSupportVehicle()) {
                return String.valueOf(type.getSupportVeeSlots(entity));
            } else if (entity instanceof Tank) {
                return String.valueOf(type.getTankSlots(entity));
            } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
                return String.valueOf(TestProtoMek.requiresSlot(type) ? 1 : 0);
            }
            return String.valueOf(type.getNumCriticalSlots(entity));
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
            } else if (wtype.getDamage() == WeaponType.DAMAGE_BY_CLUSTER_TABLE) {
                if (wtype instanceof HAGWeapon) {
                    return wtype.getRackSize() + "";
                } else if (wtype instanceof MekMortarWeapon) {
                    return "Special";
                } else if (wtype instanceof MissileWeapon) {
                    int dmg;
                    if (wtype instanceof ThunderboltWeapon) {
                        switch (wtype.getAmmoType()) {
                            case TBOLT_5:
                                return "5";
                            case TBOLT_10:
                                return "10";
                            case TBOLT_15:
                                return "15";
                            case TBOLT_20:
                                return "20";
                            default:
                                return "0";
                        }
                    } else if ((wtype instanceof ATMWeapon)
                          || (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.SRM)
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

        private final String replacePattern = "\\s*(?:\\((?:[^()\\[\\]]|\\[[^\\]]*\\])*\\)|\\[(?:[^()\\[\\]]|\\([^)]*\\))*\\])";
        private String cleanupName(String name) {
            // Remove any text in parentheses, e.g. "Laser Rifle (ER [Sunbeam Starfire])"
            return name.replaceAll(replacePattern, "").trim();
        }

        private ExportInventoryEntry addWeaponEntry(HashMap<String, ExportInventoryEntry> list, Entity entity,
              @Nullable WeaponMounted mounted, WeaponType type,
              String location, int locId) {
            final String name = type.getShortName();
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
                return entry;
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
                entry.md = String.valueOf(SVGMassPrinter.getMaxDamage(entity, type));
                if (type.hasFlag(WeaponTypeFlag.F_ONE_SHOT)) {
                    entry.os = 1; // If the weapon is oneshot
                } else if (type.hasFlag(WeaponTypeFlag.F_DOUBLE_ONE_SHOT)) {
                    entry.os = 2; // If the weapon is double oneshot
                }
                entry.c = getCriticals(entity, type);
                list.put(key, entry);
                return entry;
            }
        }

        public Components(Entity entity) {
            if (entity.usesWeaponBays()) {
                parseBays(this.comps, entity);
            } else {
                parseComponents(this.comps, entity);
            }
        }

        private ExportInventoryEntry addWeaponBay(HashMap<String, ExportInventoryEntry> list, Entity entity,
              WeaponType type,
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
                    addWeaponEntry(bayList, entity, bay, weaponMounted.getType(), "", 0);
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
                    InfantryWeapon primaryWeapon = inf.getPrimaryWeapon();
                    ExportInventoryEntry entry = new ExportInventoryEntry();
                    entry.id = primaryWeapon.getInternalName();
                    entry.n = cleanupName(primaryWeapon.getShortName());
                    entry.t = getWeaponCategory(primaryWeapon);
                    entry.q = (inf.getSquadSize() - inf.getSecondaryWeaponsPerSquad()) * inf.getSquadCount();
                    entry.p = 0;
                    entry.l = "Troop";
                    double dmg = Math.min(MMConstants.INFANTRY_PRIMARY_WEAPON_DAMAGE_CAP,
                          primaryWeapon.getInfantryDamage());
                    entry.d = String.valueOf(dmg);
                    entry.r = getWeaponRange(entity, primaryWeapon);
                    entry.m = getMinRange(entity, primaryWeapon);
                    entry.md = String.valueOf(dmg);
                    list.put("1st", entry);
                }
                if (null != inf.getSecondaryWeapon()) {
                    InfantryWeapon secondaryWeapon = inf.getSecondaryWeapon();
                    ExportInventoryEntry entry = new ExportInventoryEntry();
                    entry.id = secondaryWeapon.getInternalName();
                    entry.n = cleanupName(secondaryWeapon.getShortName());
                    entry.t = getWeaponCategory(secondaryWeapon);
                    entry.q = inf.getSecondaryWeaponsPerSquad() * inf.getSquadCount();
                    entry.p = 0;
                    entry.l = "Troop";
                    double dmg = secondaryWeapon.getInfantryDamage();
                    entry.d = String.valueOf(dmg);
                    entry.r = getWeaponRange(entity, secondaryWeapon);
                    entry.m = getMinRange(entity, secondaryWeapon);
                    entry.md = String.valueOf(dmg);
                    list.put("2nd", entry);
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
                      && (m.getNumCriticalSlots() > 0)
                      && (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)) {
                    continue;
                }
                if (m.getLocation() == Entity.LOC_NONE) {
                    continue;
                }
                if ((m instanceof WeaponMounted wm) && (m.getType() instanceof WeaponType wtype)) {

                    if (wtype.hasFlag(WeaponTypeFlag.INTERNAL_REPRESENTATION)) {
                        continue;
                    }
                    if ((entity instanceof Infantry inf) && !(entity instanceof BattleArmor)) {
                        if (m.getLocation() == Infantry.LOC_INFANTRY) {
                            continue; // Infantry weapons are handled separately at the beginning
                        }
                    }
                    if (entity.isBattleArmor() && wm.isSquadSupportWeapon()) {
                        addWeaponEntry(list, entity, wm, wtype,
                              "SSW",
                              10);
                    } else {
                        addWeaponEntry(list, entity, wm, wtype,
                              entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation());
                    }
                    // if this is a weapon bay, then cycle through weapons
                    if (wtype instanceof BayWeapon) {
                        for (WeaponMounted bm : wm.getBayWeapons()) {
                            addWeaponEntry(list, entity, wm, bm.getType(), entity.joinLocationAbbr(wm.allLocations(),
                                        2),
                                  wm.getLocation());
                        }
                    }
                } else if ((m instanceof MiscMounted mm)) {
                    MiscType mtype = mm.getType();
                    if (mtype.hasFlag(MiscType.F_ARMOR_KIT)) {
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
                              && ((mtype.hasFlag(MiscType.F_AP_MOUNT) && !mtype.hasFlag(MiscType.F_BA_MANIPULATOR))
                              || mtype.hasAnyFlag(
                              MiscType.F_FIRE_RESISTANT,
                              MiscType.F_ARTEMIS,
                              MiscType.F_ARTEMIS_V,
                              MiscType.F_APOLLO,
                              MiscType.F_HARJEL,
                              MiscType.F_MASS,
                              MiscType.F_DETACHABLE_WEAPON_PACK,
                              MiscType.F_MODULAR_WEAPON_MOUNT
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
                        addMiscEntry(list, entity, mm, mtype, entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation());
                    } else {
                        addMiscEntry(list, entity, mm, mtype, entity.joinLocationAbbr(m.allLocations(), 2),
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
                mounted.setLocation(Mek.LOC_CENTER_TORSO);
                addMiscEntry(list,
                      entity,
                      mounted,
                      mounted.getType(),
                      entity.joinLocationAbbr(mounted.allLocations(), 2),
                      mounted.getLocation());
            }
            if (entity instanceof Mek mek && mek.hasFullHeadEject()) {
                var mounted = new MiscMounted(entity, new MiscType() {{
                    name = "Full Head Ejection System";
                    shortName = "Full Head Eject System";
                    internalName = "Full Head Ejection System";
                }});
                mounted.setLocation(Mek.LOC_HEAD);
                addMiscEntry(list,
                      entity,
                      mounted,
                      mounted.getType(),
                      entity.joinLocationAbbr(mounted.allLocations(), 2),
                      mounted.getLocation());
            }
        }

        private void addMiscEntry(HashMap<String, ExportInventoryEntry> list, Entity entity, MiscMounted mounted,
              MiscType type,
              String location, int locId) {
            final String name = type.getShortName();
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
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
                damage = Integer.toString(KickAttackAction.getDamageFor(entity, Mek.LOC_LEFT_LEG, false));
                maxDamage = damage;
            } else if (type.hasSubType(MiscType.S_CLAW) || type.hasSubType(MiscType.S_CLAW_THB)) {
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
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = "P"; // Physical weapon
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                entry.d = damage;
                entry.md = maxDamage;
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
        public double dpt; // Damage per Turn, if applicable
        public List<String> quirks;
        public Collection<ExportInventoryEntry> comp;
        public int su; // 1 for small units (Battle Armor, ProtoMek, Infantry), 0 for others
        public int crewSize; // Number of crew members, if applicable
        public String icon; // Path to the unit icon
        public List<String> sheets; // Path to the SVG sheet
        public HashMap<String, Object> as = null;
        //        public String summary;

        private void loadASUnitData(Entity entity) {
            this.as = null;
            if (ASConverter.canConvert(entity)) {
                AlphaStrikeElement asElement = ASConverter.convert(entity, new FlexibleCalculationReport());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.valueToTree(asElement);
                ((ObjectNode) node).remove(Arrays.asList("type", "id", "chassis", "model"));
                this.as = new HashMap<>();
                node.fields().forEachRemaining(entry -> {
                    final String value = entry.getValue().asText();
                    if (value.isEmpty()) {
                        return; // Skip empty values
                    }
                    this.as.put(entry.getKey(), value);
                });
                // Convert "specials" from a comma-separated string to an array
                if (this.as.containsKey("specials")) {
                    Object specialsValue = this.as.get("specials");
                    if (specialsValue instanceof String specialsStr) {
                        String[] specialsArr = Arrays.stream(specialsStr.split(","))
                              .map(String::trim)
                              .filter(s -> !s.isEmpty())
                              .toArray(String[]::new);
                        this.as.put("specials", specialsArr);
                    }
                }
            }
        }

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
            for (Enumeration<IOptionGroup> optionGroups = quirks.getGroups(); optionGroups.hasMoreElements(); ) {
                IOptionGroup optiongroup = optionGroups.nextElement();
                if (quirks.count(optiongroup.getKey()) > 0) {
                    for (Enumeration<IOption> options = optiongroup.getOptions(); options.hasMoreElements(); ) {
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
            this.bv = entity.getBvCalculator().calculateBV(false, true);
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
            if (entity.tracksHeat()) {
                this.heat = UnitUtil.getTotalHeatGeneration(entity);
                this.dissipation = entity.getHeatCapacity();
            } else {
                this.heat = -1;
                this.dissipation = -1;
            }
            this.walk = entity.getWalkMP();
            this.run = entity.getRunMP();
            this.jump = entity.getJumpMP();
            this.crewSize = entity.getCrew().getSlotCount();
            this.comp = (new Components(entity)).getComp();
            this.c3 = getC3Property(entity);
            this.quirks = getQuirks(entity);
            this.icon = getEntityIcon(entity);
            this.sheets = new ArrayList<>();
            this.loadASUnitData(entity);
            //            final MekView mekView = new MekView(entity, false, false, ViewFormatting.HTML);
            //            this.summary = mekView.getMekReadout();

            if ((entity instanceof Infantry inf) && !(entity instanceof BattleArmor)) {
                this.dpt = Math.round(calculateSustainedDPTForInfantry(entity));
            } else {
                this.dpt = Math.round(calculateSustainedDPT(entity) * 10) / 10.0;
            }
        }

        private String getEntityIcon(Entity entity) {
            if (entity == null || tileset == null) {
                return "";
            }
            try {
                MekTileset.MekEntry entry = tileset.entryFor(entity, -1);
                if (entry != null) {
                    return entry.getImageFile();
                }
            } catch (Exception ignored) {
            }
            return "";
        }

        private double calculateSustainedDPTForInfantry(Entity entity) {
            double totalDPTTroops = 0;
            double totalDPTField = 0;
            for (ExportInventoryEntry comp : this.comp) {
                if (comp.md == null || comp.md.isEmpty()) {
                    continue; // Skip components without damage data
                }
                if (comp.l.equals("Troop")) {
                    totalDPTTroops += Double.parseDouble(comp.md) * comp.q;
                } else {
                    totalDPTField += Double.parseDouble(comp.md) * comp.q;
                }
            }
            return Math.max(totalDPTTroops, totalDPTField);
        }

        /**
         * Calculates sustained Damage per Turn (DPT) considering heat limits.
         */
        public double calculateSustainedDPT(Entity entity) {
            double totalDPT = 0;
            double fireFraction = 1;
            List<WeaponMounted> allWeapons = new ArrayList<>();
            for (WeaponMounted weapon : entity.getWeaponList()) {
                allWeapons.add(weapon);
                if (weapon.getType() instanceof BayWeapon) {
                    allWeapons.addAll(weapon.getBayWeapons());
                }
            }

            if (entity.tracksHeat()) {
                int maxHeat = this.dissipation;
                int totalWeaponHeat = 0;
                for (WeaponMounted weapon : allWeapons) {
                    totalWeaponHeat += weapon.getType().getHeat();
                }
                if (this.heat > totalWeaponHeat) {
                    // If the total heat is less than the heat generated by the entity, use the entity's heat
                    totalWeaponHeat = this.heat;
                }
                fireFraction = totalWeaponHeat > maxHeat ? (double) maxHeat / totalWeaponHeat : 1.0;
            }

            for (WeaponMounted weapon : allWeapons) {
                double damage = SVGMassPrinter.getMaxDamage(entity, weapon.getType());
                double damageModifier = getDamageMultiplier(entity, weapon, weapon.getType());
                totalDPT += damage * damageModifier * fireFraction;
            }
            return totalDPT;
        }

        private static float[] expectedHitsByRackSize = { 0.0f, 1.0f, 1.58f, 2.0f,
                                                          2.63f, 3.17f, 4.0f, 4.49f, 4.98f, 5.47f, 6.31f, 7.23f, 8.14f,
                                                          8.59f, 9.04f, 9.5f, 10.1f, 10.8f, 11.42f, 12.1f, 12.7f };

        private double getDamageMultiplier(Entity entity, Mounted<?> weapon, WeaponType weaponType) {
            double damageModifier = 1d;
            // Oneshot or Fusillade
            if (weaponType.hasFlag(WeaponType.F_ONE_SHOT) && !(weaponType instanceof CLFussilade)) {
                damageModifier *= .1;
            }

            // cluster weapons or Battle Armor (cluster table)
            if ((weaponType.getDamage() == WeaponType.DAMAGE_BY_CLUSTER_TABLE)) {
                if ((weaponType.getRackSize() != 40) && (weaponType.getRackSize() != 30)) {
                    final double expectedHits = (expectedHitsByRackSize[weaponType.getRackSize()]);
                    damageModifier *= expectedHits / weaponType.getRackSize();
                } else {
                    final double expectedHits = (2.0f * expectedHitsByRackSize[weaponType.getRackSize() / 2]);
                    damageModifier *= expectedHits / weaponType.getRackSize();
                }
            }

            if (entity instanceof BattleArmor ba && !weapon.isSquadSupportWeapon()) {
                // We have an entry of a single weapon but in real is N weapons equal to the squad size so we use the
                // cluster table
                damageModifier *=  (expectedHitsByRackSize[ba.getSquadSize()]);
            }

            // Targeting Computer
            if (entity.hasTargComp() && weaponType.hasFlag(WeaponType.F_DIRECT_FIRE)) {
                damageModifier *= 1.10;
            }

            // Actuator Enhancement System
            if (weapon != null && entity.hasWorkingMisc(MiscType.F_ACTUATOR_ENHANCEMENT_SYSTEM, -1,
                  weapon.getLocation()) &&
                  ((weapon.getLocation() == Mek.LOC_LEFT_ARM) || (weapon.getLocation() == Mek.LOC_RIGHT_ARM))) {
                damageModifier *= 1.05;
            }

            return damageModifier;
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

        int processedCount = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        long timestamp = System.currentTimeMillis();
        HashMap<String, Entity> uniqueUnitTypes = new HashMap<>();

        try (FileWriter versionWriter = new FileWriter(ROOT_FOLDER + File.separator + VERSION_FILE)) {
            versionWriter.write("{\"units\":"
                  + timestamp
                  + ", \"equipment\":"
                  + timestamp
                  + ", \"quirks\":"
                  + timestamp
                  + "}");
            logger.info("Version file written: {}", timestamp);
        } catch (IOException e) {
            logger.error("Failed to write version file: {}", e.getMessage());
        }

        if (!SKIP_UNITS) {
            RecordSheetOptions recordSheetOptions = getRecordSheetOptions();
            MekSummaryCache cache = MekSummaryCache.getInstance(true);

            MekSummary[] meks = cache.getAllMeks();
            logger.info("Processing {} meks...", meks.length);

            PageFormat pf = new PageFormat();
            PaperSize paperDef = recordSheetOptions.getPaperSize();
            try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FILE)) {
                jsonWriter.write("{\"version\":" + timestamp + ",\n");
                jsonWriter.write("\"units\":[\n");
                boolean firstUnit = true;
                for (MekSummary mekSummary : meks) {
//                    if (!mekSummary.getName().contains("Field Gun Infantry")) {
//                        continue;
//                    }
//                    if (!mekSummary.isMek()) continue;
//                    if (mekSummary.getMulId() != 8596) continue;
//                    logger.info("{}", mekSummary.getName());
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
                    String svgPath = FluffImageHelper.getFluffPath(entity)
                          .toLowerCase()
                          .replaceAll("[^a-zA-Z0-9_]", "");
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
                        List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(List.of(entity),
                              true,
                              recordSheetOptions);
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
                                String pathToSave = (svgPath + File.separator + unoptimizedSvgFilename).replace("\\",
                                      "/");
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
                                String pathToSave = (svgPath + File.separator + unoptimizedSvgFilename).replace("\\",
                                      "/");
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
        }

        try (FileWriter quirksWriter = new FileWriter(ROOT_FOLDER + File.separator + "quirks.json")) {
            ResourceBundle quirksBundle = ResourceBundle.getBundle("megamek.common.options.messages");
            List<Map<String, String>> quirksList = new ArrayList<>();

            // Positive quirks
            for (var field : OptionsConstants.class.getFields()) {
                if (field.getName().startsWith("QUIRK_POS_")) {
                    String key = field.get(null).toString();
                    String name = quirksBundle.getString("QuirksInfo.option." + key + ".displayableName");
                    String desc = quirksBundle.getString("QuirksInfo.option." + key + ".description");
                    desc = filterQuirkDescription(desc);
                    Map<String, String> entry = new HashMap<>();
                    entry.put("name", name);
                    entry.put("description", desc);
                    entry.put("type", "positive");
                    quirksList.add(entry);
                }
            }
            // Negative quirks
            for (var field : OptionsConstants.class.getFields()) {
                if (field.getName().startsWith("QUIRK_NEG_")) {
                    String key = field.get(null).toString();
                    String name = quirksBundle.getString("QuirksInfo.option." + key + ".displayableName");
                    String desc = quirksBundle.getString("QuirksInfo.option." + key + ".description");
                    desc = filterQuirkDescription(desc);
                    Map<String, String> entry = new HashMap<>();
                    entry.put("name", name);
                    entry.put("description", desc);
                    entry.put("type", "negative");
                    quirksList.add(entry);
                }
            }
            quirksWriter.write("{\"version\":" + timestamp + ",\n\"quirks\":");
            quirksWriter.write(mapper.writeValueAsString(quirksList));
            quirksWriter.write("}");
            logger.info("Exported quirks.json");
        } catch (Exception e) {
            logger.error("Failed to export quirks: {}", e.getMessage());
        }

        logger.info("Done. Processed {} units.", processedCount);

        if (!SKIP_EQUIPMENT) {
            processedCount = 0;
            Map<String, Map<String, Object>> equipmentJsonMap = new HashMap<>();
            for (Map.Entry<String, Entity> entry : uniqueUnitTypes.entrySet()) {
                String unitTypeKey = entry.getKey();
                Entity entity = entry.getValue();
                EquipmentTableModel equipmentTableModel = new EquipmentTableModel(entity, null);
                equipmentTableModel.setData((ArrayList<EquipmentType>) EquipmentType.allTypes());
                List<String> normalizedKeys = new ArrayList<>();
                for (int j = 0; j < equipmentTableModel.getColumnCount(); j++) { // Skip column 0, is the name
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
                    rowMap.put("internalName", eq.getInternalName());
                    rowMap.put("name", eq.getName()); // Use full name
                    rowMap.put("shortName", eq.getShortName());
                    String equipmentType = "equipment";
                    if (eq instanceof WeaponType) {
                        equipmentType = "weapon";
                    } else if (eq instanceof AmmoType) {
                        equipmentType = "ammo";
                    } else if (eq instanceof MiscType) {
                        equipmentType = "misc";
                    }
                    rowMap.put("type", equipmentType);
                    rowMap.put("hittable", eq.isHittable()?1:0);
                    rowMap.put("spreadable", eq.isSpreadable()?1:0);
                    if (eq instanceof MiscType misc) {
                        String[] flagStrings = eq.getFlags().getSetFlagNamesAsArray(MiscTypeFlag.class);
                        rowMap.put("flags", flagStrings);
                    } else if (eq instanceof WeaponType weapon) {
                        String[] flagStrings = eq.getFlags().getSetFlagNamesAsArray(WeaponTypeFlag.class);
                        rowMap.put("flags", flagStrings);
                        if (!(weapon instanceof ACWeapon) && (weapon.getRackSize() > 0)) {
                            rowMap.put("rackSize", weapon.getRackSize());
                        }
                        rowMap.put("ammoType", weapon.getAmmoType().getName());
                    } else if (eq instanceof AmmoType ammo) {
                        String[] flagStrings = eq.getFlags().getSetFlagNamesAsArray(AmmoTypeFlag.class);
                        rowMap.put("flags", flagStrings);
                        rowMap.put("ammoType", ammo.getAmmoType().getName());
                        rowMap.put("category", ammo.getAmmoType().getCategory().name());
                        rowMap.put("rackSize", ammo.getRackSize());
                        rowMap.put("damagePerShot", ammo.getDamagePerShot());
                        rowMap.put("shots", ammo.getShots());
                        rowMap.put("kgPerShot", ammo.getKgPerShot());
                        if (ammo.getBaseAmmo() != null) {
                            rowMap.put("baseAmmo", ammo.getBaseAmmo().getInternalName());
                        }
                        rowMap.put("capital", ammo.isCapital());
                        rowMap.put("ammoRatio", ammo.getAmmoRatio());
                        rowMap.put("subMunition", ammo.getSubMunitionName());
                        String[] munitionStrings = ammo.getMunitionType()
                              .stream()
                              .map(munition -> munition.name())
                              .toArray(String[]::new);
                        rowMap.put("munitionType", munitionStrings);
                    }
                    for (int j = 0; j < equipmentTableModel.getColumnCount(); j++) {
                        if (j == EquipmentTableModel.COL_NAME) {continue;}
                        if (j == EquipmentTableModel.COL_TECH_RATING) {continue;}
                        if (j == EquipmentTableModel.COL_DATE_PROTOTYPE) {continue;}
                        if (j == EquipmentTableModel.COL_DATE_PRODUCTION) {continue;}
                        if (j == EquipmentTableModel.COL_DATE_COMMON) {continue;}
                        if (j == EquipmentTableModel.COL_DATE_EXTINCT) {continue;}
                        if (j == EquipmentTableModel.COL_DATE_REINTRODUCED) {continue;}
                        String key = normalizedKeys.get(j);
                        Object value = equipmentTableModel.getValueAt(i, j);
                        rowMap.put(key, value);
                    }
                    rowMap.put("rating", Map.of(
                          "is", eq.getFullRatingName(false),
                          "clan", eq.getFullRatingName(true)
                    ));
                    rowMap.put("dates", Map.of(
                          "is", Map.of(
                                "t", eq.getTechAdvancement().getPrototypeDateName(false),
                                "p", eq.getTechAdvancement().getProductionDateName(false),
                                "c", eq.getTechAdvancement().getCommonDateName(false),
                                "x", eq.getTechAdvancement().getExtinctionDateName(false),
                                "r", eq.getTechAdvancement().getReintroductionDateName(false)
                          ),
                          "clan", Map.of(
                                "t", eq.getTechAdvancement().getPrototypeDateName(true),
                                "p", eq.getTechAdvancement().getProductionDateName(true),
                                "c", eq.getTechAdvancement().getCommonDateName(true),
                                "x", eq.getTechAdvancement().getExtinctionDateName(true),
                                "r", eq.getTechAdvancement().getReintroductionDateName(true)
                          ),
                          "mixed", Map.of(
                                "t", eq.getTechAdvancement().getPrototypeDateName(),
                                "p", eq.getTechAdvancement().getProductionDateName(),
                                "c", eq.getTechAdvancement().getCommonDateName(),
                                "x", eq.getTechAdvancement().getExtinctionDateName(),
                                "r", eq.getTechAdvancement().getReintroductionDateName()
                          )
                    ));
                    equipmentMap.put(eq.getInternalName(), rowMap);
                }
                equipmentJsonMap.put(unitTypeKey, equipmentMap);
            }
            try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + EQUIPMENT_FILE)) {
                jsonWriter.write("{\"version\":" + timestamp + ",\n");
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

        // Export Quirks


        System.exit(0);
    }

    private static String filterQuirkDescription(String desc) {
        desc = desc.replace("\n", " ");
        // Remove "No game effect in MegaMek, included for completeness and external programs such as MekHQ."
        desc = desc.replaceAll(
              "No game effect in MegaMek,\\s*included for completeness and external programs such as MekHQ\\.?",
              "");
        // Remove "No game effect," and similar phrases
        desc = desc.replaceAll("No game effect,?\\s*(included for completeness\\.?|currently\\.)?\\s*", "");
        // Remove "Not Implemented" and "Not coded for use."
        desc = desc.replaceAll("Not Implemented\\s*", "");
        desc = desc.replaceAll("Not coded for use\\.\\s*", "");
        // Remove "included for completeness." and similar phrases
        desc = desc.replaceAll("\\.?\\s*included for completeness\\.?\\s*", "");
        desc = desc.replaceAll("\\.?\\s*Included for completeness\\.?\\s*", "");
        // Remove trailing "Not Implemented (...)" and keep the reference
        desc = desc.replaceAll("\\.\\s*Not Implemented\\s*\\(([^)]+)\\)", ". ($1)");
        // Remove trailing "Not yet implemented. (...)" and keep the reference
        desc = desc.replaceAll("\\.\\s*Not yet implemented\\.\\s*\\(([^)]+)\\)", ". ($1)");
        // Remove trailing "Not yet implemented." without parentheses
        desc = desc.replaceAll("\\.\\s*Not yet implemented\\s*", ".");
        // Remove "Not implemented (...)" if it's the whole description, keep only the reference
        desc = desc.replaceAll("^Not implemented \\(([^)]+)\\)\\s*$", "$1");
        // Remove trailing "Not implemented." after a parenthetical reference
        desc = desc.replaceAll("(\\([^)]+\\))\\s*Not implemented\\.?$", "$1");
        // Remove trailing "Not yet implemented." after a parenthetical reference
        desc = desc.replaceAll("(\\([^)]+\\))\\s*Not yet implemented\\.?$", "$1");
        // Remove any leading punctuation and whitespace before a parenthetical reference
        desc = desc.replaceAll("^[\\s.,;:]+\\(([^)]+)\\)", "$1");
        // If only a reference in parentheses remains, keep just that
        desc = desc.replaceAll("^\\s*\\(([^)]+)\\)\\s*$", "$1");
        // Ensure a space before any parenthesis that follows a word character
        desc = desc.replaceAll("(\\w)\\(", "$1 (");
        // Remove any leftover leading/trailing whitespace
        return desc.trim();
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
        recordSheetOptions.setIncludeHitMod(RecordSheetOptions.HitModStyle.NONE);
        recordSheetOptions.setIntrinsicPhysicalAttacks(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.FOOTER);
        recordSheetOptions.setExplicitZeroModifier(RecordSheetOptions.ExplicitZeroModifierStyle.PLUS_ZERO);
        recordSheetOptions.setExtraPhysicals(true);
        return recordSheetOptions;
    }

    /**
     * Combines multiple SVG documents side by side into a single SVG Assumes Letter size (612x792 points) for each
     * sheet
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
