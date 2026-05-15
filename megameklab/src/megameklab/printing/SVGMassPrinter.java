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
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import megamek.MMConstants;
import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.Messages;
import megamek.client.ui.clientGUI.calculationReport.FlexibleCalculationReport;
import megamek.client.ui.tileset.MMStaticDirectoryManager;
import megamek.client.ui.tileset.MekTileset;
import megamek.client.ui.util.FluffImageHelper;
import megamek.common.*;
import megamek.common.alphaStrike.ASDamageVector;
import megamek.common.alphaStrike.ASSpecialAbilityCollection;
import megamek.common.alphaStrike.AlphaStrikeHelper;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.battleArmor.BattleArmorHandles;
import megamek.common.bays.BattleArmorBay;
import megamek.common.bays.Bay;
import megamek.common.bays.InfantryBay;
import megamek.common.bays.ProtoMekBay;
import megamek.common.equipment.*;
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
import megamek.common.verifier.TestProtoMek;
import megamek.common.weapons.autoCannons.RACWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megamek.common.weapons.gaussRifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
import megamek.common.weapons.missiles.thunderbolt.ThunderboltWeapon;
import megamek.common.weapons.mortars.MekMortarWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.ui.util.EquipmentDatabaseCategory;
import megameklab.util.CConfig;
import megameklab.util.SVGOptimizer;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static megamek.common.equipment.EquipmentType.T_ARMOR_BA_STANDARD;
import static megamek.common.equipment.EquipmentType.T_ARMOR_STANDARD;
import static megamek.common.equipment.EquipmentType.T_ARMOR_STANDARD_PROTOMEK;
import static megamek.common.equipment.WeaponType.DAMAGE_ARTILLERY;
import static megamek.common.equipment.WeaponType.DAMAGE_BY_CLUSTER_TABLE;
import static megamek.common.equipment.WeaponType.DAMAGE_SPECIAL;
import static megamek.common.equipment.WeaponType.DAMAGE_VARIABLE;

/**
 * @author drake
 * Generates SVG sheets for all units in the Mek Summary Cache and saves them
 */
public class SVGMassPrinter {
    static ResourceBundle resourcesTabs = ResourceBundle.getBundle("megameklab.resources.Tabs");
    private final static boolean SKIP_SVG = false; // Set to true to skip SVG generation
    private final static boolean SKIP_UNITS = false; // Set to true to skip units generation
    private final static boolean SKIP_EQUIPMENT = false; // Set to true to skip equipment generation
    private final static boolean SKIP_UNIT_FILES = true; // Set to true to skip BLK/MTF re-save generation

    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final int SUSTAINED_TURNS = 10; // Number of turns for sustained DPT calculation
    private static final String TYPEFACE = "Roboto";
    private static final String SHEETS_DIR = "sheets";
    private static final String UNIT_FILES_DIR = "unitfiles";
    private static final String UNIT_FILE = "units.json";
    private static final String UNIT_FLUFF_FILE = "units-fluff.json";
    private static final String EQUIPMENT_FILE = "equipment2.json";
    private static final String ROOT_FOLDER = "../../svgexport";
    private static final String LICENSE_HEADER = """
        # MegaMek Data (C) %s by The MegaMek Team is licensed under CC BY-NC-SA 4.0.
        # To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-sa/4.0/
        #
        # NOTICE: The MegaMek organization is a non-profit group of volunteers
        # creating free software for the BattleTech community.
        #
        # MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
        # of The Topps Company, Inc. All Rights Reserved.
        #
        # Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
        # InMediaRes Productions, LLC.
        #
        # MechWarrior Copyright Microsoft Corporation. MegaMek Data was created under
        # Microsoft's "Game Content Usage Rules"
        # <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
        # affiliated with Microsoft.
        """.formatted(Calendar.getInstance().get(Calendar.YEAR));
    private static final int DEFAULT_MARGINS = 0; // Default margins for the page
    private final static RATGenerator RAT_GENERATOR = RATGenerator.getInstance();
    private final static MekTileset tileset = MMStaticDirectoryManager.getMekTileset();

    private static final HashMap<Integer, String> unitTypes = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ExportInventoryEntry {
        public String id; // Internal name of the weapon type
        public int q; // Quantity of this weapon type
        public int q2; // Used for ammo, is the amount of rounds
        public String n; // Name of the weapon type
        public String t; // Type of the weapon
        public int p; // Location id of the weapon, if applicable
        public String l; // Location of the weapon, if applicable
        public Boolean rear = null; // if is rear mounted
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
        if (wtype.getDamage() == DAMAGE_BY_CLUSTER_TABLE) {
            int perMissile = 1;
            if ((wtype instanceof SRMWeapon) || (wtype instanceof SRTWeapon) || (wtype instanceof MMLWeapon)) {
                perMissile = 2;
            }
            return wtype.getRackSize() * perMissile;
        } else if (wtype.getDamage() == DAMAGE_VARIABLE) {
            return Math.max(0, wtype.getDamage(1));
        } else if (wtype.getDamage() == DAMAGE_SPECIAL) {
            return 0;
        } else if (wtype.getDamage() == DAMAGE_ARTILLERY) {
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

        private String getMWCategory(EquipmentType eq) {
            if (eq instanceof WeaponType wp) {
                if (wp.hasFlag(WeaponType.F_ENERGY)
                      || ((wp.hasFlag(WeaponType.F_PLASMA) && (wp.getAmmoType() == AmmoType.AmmoTypeEnum.PLASMA)))
                      || wp.getAmmoType().getCategory().equals(AmmoType.AmmoCategory.Energy)) {
                    return "E";
                }
                if (wp.hasFlag(WeaponType.F_MISSILE) || wp.getAmmoType()
                      .getCategory()
                      .equals(AmmoType.AmmoCategory.Missile)) {
                    return "M";
                }
                if (wp.hasFlag(WeaponType.F_BALLISTIC) || wp.getAmmoType()
                      .getCategory()
                      .equals(AmmoType.AmmoCategory.Ballistic)) {
                    return "B";
                }
                if (wp.hasFlag(WeaponType.F_ARTILLERY) || wp.getAmmoType()
                      .getCategory()
                      .equals(AmmoType.AmmoCategory.Artillery)) {
                    return "A";
                }
            } else
            if (eq instanceof AmmoType ammo) {
                return "X";
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

            if (wtype.getDamage() == DAMAGE_VARIABLE) {
                if (wtype.getDamage(1) <= 0) {
                    return "0";
                } else {
                    return wtype.getDamage(wtype.getShortRange()) + "/"
                          + wtype.getDamage(wtype.getMediumRange()) + "/"
                          + wtype.getDamage(wtype.getLongRange());
                }
            } else if (wtype.getDamage() == DAMAGE_BY_CLUSTER_TABLE) {
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
            } else if (wtype.getDamage() == DAMAGE_ARTILLERY) {
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
            return name;
            // Remove any text in parentheses, e.g. "Laser Rifle (ER [Sunbeam Starfire])"
//            return name.replaceAll(replacePattern, "").trim();
        }

        private ExportInventoryEntry addWeaponEntry(HashMap<String, ExportInventoryEntry> list, Entity entity,
              @Nullable WeaponMounted mounted, WeaponType type,
              String location, int locId) {
            final String name = type.getShortName();
            final boolean rearMounted = mounted.isRearMounted();
            final String key = name + "_" + location + (rearMounted ? "_rear" : "");
            if (list.containsKey(key)) {
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
                return entry;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = getMWCategory(type);
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                if (rearMounted) {
                    entry.rear = true;
                }
                entry.d = getDamage(entity, type);
                entry.r = getWeaponRange(entity, type);
                entry.m = getMinRange(entity, type);
                entry.md = String.valueOf(SVGMassPrinter.getMaxDamage(entity, type));
                if (type.hasFlag(WeaponTypeFlag.F_DOUBLE_ONE_SHOT)) {
                    entry.os = 2; // If the weapon is double oneshot
                } else if (type.hasFlag(WeaponTypeFlag.F_ONE_SHOT)) {
                    entry.os = 1; // If the weapon is oneshot
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
            entry.t = getMWCategory(type);
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
            if (entity instanceof ConvInfantry inf) {
                if (null != inf.getPrimaryWeapon()) {
                    InfantryWeapon primaryWeapon = inf.getPrimaryWeapon();
                    ExportInventoryEntry entry = new ExportInventoryEntry();
                    entry.id = primaryWeapon.getInternalName();
                    entry.n = cleanupName(primaryWeapon.getShortName());
                    entry.t = getMWCategory(primaryWeapon);
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
                    entry.t = getMWCategory(secondaryWeapon);
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
                if (m.getType() instanceof AmmoType ammo) { // Includes Coolant Pods since they are technically ammo
                    addAmmoEntry(list, entity, (AmmoMounted) m, ammo, entity.joinLocationAbbr(m.allLocations(), 2),
                          m.getLocation());
                    continue;
                }
                if ((entity instanceof QuadVee)
                      && (m.getType() instanceof MiscType)
                      && m.getType().hasFlag(MiscType.F_TRACKS)) {
                    continue;
                }
                if ((entity instanceof BattleArmor)
                      && (m.getNumCriticalSlots() > 0)
                      && (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)
                      && !(
                      m.getLinkedBy() != null && m.getLinkedBy().getType().hasFlag(MiscTypeFlag.F_DETACHABLE_WEAPON_PACK)
                )) {
                    continue;
                }
                if ((m instanceof WeaponMounted wm) && (m.getType() instanceof WeaponType wtype)) {

                    if (wtype.hasFlag(WeaponTypeFlag.INTERNAL_REPRESENTATION)) {
                        continue;
                    }
                    if (entity instanceof ConvInfantry) {
                        if (m.getLocation() == ConvInfantry.LOC_INFANTRY) {
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
                    boolean isStructural = false;
                    if (mtype.hasFlag(MiscType.F_ARMOR_KIT)) {
                        isStructural=true;
                    }
                    if (UnitUtil.isArmorOrStructure(mtype)) {
                        isStructural=true;
                    }
                    if (UnitUtil.isJumpJet(mtype)) {
//                        hiddenEquip=true;
                    }
                    if (UnitUtil.isHeatSink(mtype)) {
//                        isStructural=true;
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
                            isStructural=true;
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
                              m.getLocation(), isStructural);
                    } else {
                        // TODO: maybe evaluate for UnitUtil.isFixedLocationSpreadEquipment(mtype) and spread the
                        //  component?
                        addMiscEntry(list, entity, mm, mtype, entity.joinLocationAbbr(m.allLocations(), 2),
                              m.getLocation(), isStructural);
                    }
                }
            }
        }

        private void addMiscEntry(HashMap<String, ExportInventoryEntry> list, Entity entity, MiscMounted mounted,
              MiscType type,
              String location, int locId, boolean isStructural) {
            final String name = type.getShortName();
            final String key = name + "_" + location + '_' + (isStructural ? "S" : "C");
            if (list.containsKey(key)) {
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = isStructural ? "S" : "C"; // Structural (Other)
                entry.q = 1;
                entry.p = locId;
                entry.l = location;
                entry.c = getCriticals(entity, type);
                list.put(key, entry);
            }
        }

        private void addAmmoEntry(HashMap<String, ExportInventoryEntry> list, Entity entity, AmmoMounted mounted,
              AmmoType type,
              String location, int locId) {
            final String name = type.getShortName().replace("Ammo", "").trim()+" Ammo";
            final String key = name + "_" + location;
            if (list.containsKey(key)) {
                ExportInventoryEntry entry = list.get(key);
                entry.q += 1;
                entry.q2 += mounted.getBaseShotsLeft();
            } else {
                ExportInventoryEntry entry = new ExportInventoryEntry();
                entry.id = type.getInternalName();
                entry.n = cleanupName(name);
                entry.t = "X"; //getMWCategory(type);
                entry.q = 1;
                entry.q2 = mounted.getBaseShotsLeft();
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
            } else if (type.hasAnyFlag(MiscTypeFlag.S_CLAW, MiscTypeFlag.S_CLAW_THB)) {
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
        public double offSpeedFactor; // Offensive Speed factor (used to compensate Custom Ammo)
        public int pv; // Point Value, rounded to the nearest integer
        public long cost; // Cost in C-Bills, rounded to the nearest integer
        public String level; // Tech level as a string, e.g. "Introductory", "Standard", etc.
        public String techBase;
        public String techRating;
        public String engine;
        public int engineRating;
        public String type; // Major type, "Mek", "Vehicle", etc.
        public String subtype; // Subtype, "Assault", "Light", etc.
        public int omni; // 1 if the unit is Omni
        public List<String> source; // Source(s) of the unit, e.g. ["TR:3050"]
        public List<String> published; // Source(s) where the record sheet has been published, e.g. ["RS:AS"]
        public boolean canon; // True if the unit is canon, false if is not (e.g. alt-universe or april fools units)
        public String role; // Role, "Assault", "Scout", etc.
        public String armorType; // Armor Type
        public String structureType; // Internal Structure Type
        public int armor; // Total armor
        public double armorPer; // Armor %
        public int internal; // Total internal structure
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public int squads;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public int squadSize;
        public int heat; // Total heat generation
        public int dissipation; // Heat capacity
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public int[] diss; // Max Dissipation
        public int engineHS; // Number of engine-integrated (critical-free) heat sinks
        public String engineHSType; // Type of heat sink (e.g. "Single", "Double", "Compact", etc.)
        public String moveType; // Movement type
        public int walk; // Walk MP
        public int walk2; // Walk MP
        public int run; // Run MP (basic)
        public int run2; // Run MP (with MASC and stuffs)
        public int jump; // Jump MP
        public int jump2; // Jump MP
        public int umu; // UMU MP
        public String c3; // C3 system, if applicable
        public double dpt; // Damage per Turn, if applicable
        public List<String> quirks;
        public List<String> features;
        public Collection<ExportInventoryEntry> comp;
        public int su; // 1 for small units (Battle Armor, ProtoMek, Infantry), 0 for others
        public int crewSize; // Number of crew members, if applicable
        public String icon; // Path to the unit icon
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public Map<String, Object> fluff;
        @JsonIgnore
        public Map<String, Object> detachedFluff;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public List<Object> cargo;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public Map<String, Object> capital;
        public List<String> sheets; // Path to the SVG sheet
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public String unitFile; // Path to the unit file (MTF/BLK), relative to the unitfiles output folder
        public HashMap<String, Object> as = null;
        //        public String summary;



        /** @return True if this ASUnitType is an Aerospace type, i.e. AF, CF, SC, DS, DA, SS, JS, WS. */
        private boolean isAerospace(ASUnitType tp) { return tp.isAnyOf(ASUnitType.AF, ASUnitType.CF, ASUnitType.SC,
              ASUnitType.DS, ASUnitType.DA, ASUnitType.SS, ASUnitType.JS, ASUnitType.WS); }

        private void loadASUnitData(Entity entity) {
            this.as = null;
            if (ASConverter.canConvert(entity)) {
                AlphaStrikeElement asElement = ASConverter.convert(entity, new FlexibleCalculationReport());
                ObjectMapper mapper = new ObjectMapper();
                this.as = new HashMap<>();
                this.as.put("PV", asElement.getPointValue());
                this.as.put("TP", asElement.getASUnitType().toString());
                this.as.put("SZ", asElement.getSize());
                if (!this.isAerospace(asElement.getASUnitType())) {
                    this.as.put("TMM", asElement.getTMM());
                } else {
                    this.as.put("TMM", null);
                }
                this.as.put("OV", asElement.getOV());
                this.as.put("usesOV", asElement.usesOV());
                this.as.put("MV", AlphaStrikeHelper.getMovementAsString(asElement));
                this.as.put("MVm", asElement.getMovement());
                this.as.put("MVp", asElement.getPrimaryMovementMode());
                this.as.put("usesArcs", asElement.usesArcs());
                this.as.put("dmg", dmgData(asElement.getStandardDamage()));
                this.as.put("usesE", asElement.usesSMLE());
                this.as.put("Arm", asElement.getFullArmor());
                this.as.put("Th", asElement.getThreshold());
                this.as.put("usesTh", asElement.usesThreshold());
                this.as.put("Str", asElement.getFullStructure());
                String[] specialsArr = splitCommasOutsideParens(asElement.getSpecialAbilities()
                            .getSpecialsDisplayString(",", asElement));
                this.as.put("specials", specialsArr);
                if (asElement.usesArcs()) {
                    this.as.put("frontArc", arcData(asElement, asElement.getFrontArc()));
                    this.as.put("leftArc", arcData(asElement, asElement.getLeftArc()));
                    this.as.put("rightArc", arcData(asElement, asElement.getRightArc()));
                    this.as.put("rearArc", arcData(asElement, asElement.getRearArc()));
                }
            }
        }

        private HashMap<String, Object> arcData(AlphaStrikeElement element, ASSpecialAbilityCollection arc) {
            HashMap<String, Object> arcData = new HashMap<>();
            arcData.put("STD", dmgData(arc.getStdDamage()));
            arcData.put("CAP", dmgData(arc.getCAP()));
            arcData.put("SCAP", dmgData(arc.getSCAP()));
            arcData.put("MSL", dmgData(arc.getMSL()));
            arcData.put("specials", splitCommasOutsideParens(arc.getSpecialsDisplayString(element)));
            return arcData;
        }

        private HashMap<String, Object> dmgData(ASDamageVector dmg) {
            HashMap<String, Object> dmgData = new HashMap<>();
            dmgData.put("dmgS", dmg.S().toStringWithZero());
            dmgData.put("dmgM", dmg.M().toStringWithZero());
            dmgData.put("dmgL", dmg.L().toStringWithZero());
            dmgData.put("dmgE", dmg.E().toStringWithZero());
            return dmgData;
        }


    private static String[] splitCommasOutsideParens(String input) {
            if (input == null || input.isEmpty()) {
                return new String[0];
            }
            List<String> parts = new ArrayList<>();
            StringBuilder cur = new StringBuilder();
            int depth = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '(') {
                    depth++;
                    cur.append(c);
                } else if (c == ')') {
                    if (depth > 0) depth--;
                    cur.append(c);
                } else if (c == ',' && depth == 0) {
                    parts.add(cur.toString().trim());
                    cur.setLength(0);
                } else {
                    cur.append(c);
                }
            }
            if (cur.length() > 0) {
                parts.add(cur.toString().trim());
            }
            return parts.stream().filter(s -> !s.isEmpty()).toArray(String[]::new);
        }

        private static List<String> splitSourceList(@Nullable String input) {
            if (input == null || input.isBlank()) {
                return List.of();
            }

            List<String> sources = new ArrayList<>();
            Set<String> seen = new HashSet<>();
            for (String entry : input.split(",")) {
                String source = entry.trim();
                String sourceKey = source.toLowerCase(Locale.ROOT);
                if (source.isEmpty() || sourceKey.equals("none") || !seen.add(sourceKey)) {
                    continue;
                }

                sources.add(source);
            }
            return sources;
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
            // Check weapons for C3 Master computers (these are weapon-mounted)
            for (WeaponMounted m : entity.getWeaponList()) {
                if (m.getType().hasFlag(WeaponType.F_C3M) || m.getType().hasFlag(WeaponType.F_C3MBS)) {
                    return "C3";
                }
            }
            // Check misc equipment for other C3 systems
            for (MiscMounted m : entity.getMisc()) {
                MiscType type = m.getType();
                // C3 family: F_C3S, F_C3SBS, F_C3EM
                if (type.hasFlag(MiscType.F_C3S) || type.hasFlag(MiscType.F_C3SBS) || type.hasFlag(MiscType.F_C3EM)) {
                    return "C3";
                }
                // C3i family
                if (type.hasFlag(MiscType.F_C3I)) {
                    return "C3i";
                }
                // Naval C3
                if (type.hasFlag(MiscType.F_NAVAL_C3)) {
                    return "Naval C3";
                }
                // Nova CEWS
                if (type.hasFlag(MiscType.F_NOVA)) {
                    return "Nova CEWS";
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

        private List<String> getFeatures(Entity entity) {
            List<String> feats = new ArrayList<>();

            // Cockpit type for Aero (if not standard/primitive)
            if (entity instanceof Aero aero) {
                if ((aero.getCockpitType() != Aero.COCKPIT_STANDARD)
                      && (aero.getCockpitType() != Aero.COCKPIT_PRIMITIVE)) {
                    feats.add(aero.getCockpitTypeString());
                }
                // VSTOL for conventional fighters
                if ((aero instanceof ConvFighter) && aero.isVSTOL()) {
                    feats.add("VSTOL Equipment");
                }
                // LF Battery for Jumpships
                if (aero instanceof Jumpship && ((Jumpship) aero).hasLF()) {
                    feats.add("LF Battery");
                }
            }

            if (entity instanceof Mek mek) {
                if (mek.getCockpitType() != Mek.COCKPIT_STANDARD
                      && mek.getCockpitType() != Mek.COCKPIT_UNKNOWN
                      && mek.getCockpitType() != Mek.COCKPIT_PRIMITIVE
                      && mek.getCockpitType() != Mek.COCKPIT_INDUSTRIAL
                      && mek.getCockpitType() != Mek.COCKPIT_PRIMITIVE_INDUSTRIAL
                      && mek.getCockpitType() != Mek.COCKPIT_TRIPOD
                      && mek.getCockpitType() != Mek.COCKPIT_QUADVEE
                      && mek.getCockpitType() != Mek.COCKPIT_SUPERHEAVY
                      && mek.getCockpitType() != Mek.COCKPIT_SUPERHEAVY_TRIPOD
                      && mek.getCockpitType() != Mek.COCKPIT_SUPERHEAVY_INDUSTRIAL
                      && mek.getCockpitType() != Mek.COCKPIT_TRIPOD_INDUSTRIAL
                      && mek.getCockpitType() != Mek.COCKPIT_SUPERHEAVY_TRIPOD_INDUSTRIAL) {
                    feats.add(mek.getCockpitTypeString());
                }
                if (mek.getGyroType() != Mek.GYRO_STANDARD
                && mek.getGyroType() != Mek.GYRO_NONE) {
                    feats.add(mek.getGyroTypeString());
                }
                if (mek.hasFullHeadEject()) {
                    feats.add(Mek.FULL_HEAD_EJECT_STRING);
                }
                if (mek.hasRiscHeatSinkOverrideKit()) {
                    feats.add(Mek.RISC_HEAT_SINK_OVERRIDE_KIT);
                }
                if (mek.isFrankenMek()) {
                    feats.add("FrankenMek");
                }
            }
            // Chassis modifications (for support vehicles and tanks)
            if (entity.isSupportVehicle() || entity instanceof Tank) {
                List<String> chassisMods = entity.getMisc()
                      .stream()
                      .filter(m -> m.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION))
                      .map(m -> "Chassis Mod: " + m.getType().getShortName())
                      .distinct()
                      .collect(Collectors.toList());
                feats.addAll(chassisMods);
            }

            // Fire control systems
            if (entity.hasWorkingMisc(MiscType.F_ADVANCED_FIRE_CONTROL)) {
                feats.add("Advanced Fire Control");
            } else if (entity.hasWorkingMisc(MiscType.F_BASIC_FIRE_CONTROL)) {
                feats.add("Basic Fire Control");
            }

            // Transport types (just the type names, no capacities)
            Set<String> transportTypes = new HashSet<>();
            for (Transporter transporter : entity.getTransports()) {
                if (transporter instanceof InfantryCompartment) {
                    transportTypes.add("Infantry Compartment");
                } else if (transporter instanceof Bay bay && !bay.isQuarters()) {
                    transportTypes.add("Bay: " + transporter.getTransporterType());
                }
            }
            feats.addAll(transportTypes);

            return feats;
        }

        public UnitData(MekSummary mekSummary, Entity entity, RecordSheetOptions options) {
            this.id = entity.getMulId();
            this.chassis = entity.getFullChassis();
            this.model = entity.getModel();
            this.year = entity.getYear();
            this.weightClass = entity.getWeightClassName();
            this.tons = entity.getWeight();
            this.bv = entity.getBvCalculator().calculateBV(false, true);
            this.offSpeedFactor = entity.getBvCalculator().getOffensiveSpeedFactorMultiplier();
            this.cost = Math.round(entity.getCost(false));
            this.techBase = formatTechBase(entity);
            this.techRating = entity.getFullRatingName();
            this.level = formatRulesLevel(entity, options);
            if (entity.hasEngine() && !(entity instanceof SmallCraft || entity instanceof Jumpship)) {
                this.engineRating = entity.getEngine().getRating();
                this.engine = Engine.getEngineTypeName(entity.getEngine().getEngineType()).trim();
            }
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
            if (entity.isOmni()) {
                this.subtype += " Omni";
            }
            //            if (mekSummary.isSupport()) {
            //                this.subtype = unitTypes.get(UnitType.SIZE);
            //            } else
            //            if (majorType.equals(type)) {
            //                this.subtype = unitTypes.get(unitTypeId);
            //            } else {
            //                this.subtype = type;
            //            }
            this.omni = entity.isOmni() ? 1 : 0;
            this.source = splitSourceList(entity.getSource());
            this.published = splitSourceList(entity.getPublished());
            this.canon = !entity.isNonCanonBySource();
            this.role = formatRole(entity);
            this.armorType = getArmorType(entity);
            this.structureType = getStructureType(entity);
            int maxArmor = UnitUtil.getMaximumArmorPoints(entity);
            this.armor = entity.getTotalOArmor();
            entity.isBattleArmor();
            if (entity instanceof BattleArmor ba) {
                maxArmor *= ba.getTotalInternal(); // for BA this is the number of internal units
            }
            this.armorPer = maxArmor > 0 ? Math.round((double) this.armor / maxArmor * 100d) : 0;
            if (entity instanceof Aero aero) {
                this.internal = aero.getOSI();
            } else {
                this.internal = entity.getTotalInternal();
            }
            if (entity instanceof Infantry inf) {
                this.squads = inf.getSquadCount();
                this.squadSize = inf.getSquadSize();
            }
            if (entity.tracksHeat()) {
                this.heat = UnitUtil.getTotalHeatGeneration(entity);
                this.dissipation = entity.getHeatCapacity();
                this.diss = new int[]{ entity.getHeatCapacity(false), getMaxHeatDissipation(entity) };
                this.engineHSType = getHeatSinkTypeName(entity);
                if (entity instanceof Mek mek) {
                    // this.engineHS = UnitUtil.getCriticalFreeHeatSinks(mek, mek.hasCompactHeatSinks());
                    this.engineHS = 0; // we pass zero, we will handle them from inventory!
                } else if (entity instanceof Aero aero) {
                    this.engineHS = aero.getHeatSinks(); // - aero.getPodHeatSinks();
                }
            } else {
                this.heat = -1;
                this.dissipation = -1;
            }
            this.moveType = getMoveType(entity);
            this.walk = entity.getWalkMP();
            this.walk2 = entity.getWalkMP(MPCalculationSetting.BV_CALCULATION);
            this.run = entity.getRunMPWithoutMASC();
            this.run2 = entity.getRunMP(MPCalculationSetting.BV_CALCULATION);
            this.jump = entity.getJumpMP();
            this.jump2 = entity.getJumpMP(MPCalculationSetting.BV_CALCULATION);
            this.umu = entity.getActiveUMUCount();
            this.crewSize = entity.getCrew().getSlotCount();
            this.comp = (new Components(entity)).getComp();
            this.c3 = getC3Property(entity);
            this.quirks = getQuirks(entity);
            this.features = getFeatures(entity);
            this.icon = getEntityIcon(entity);
            Map<String, Object> fluffMap = getFluffAttributes(entity);
            Object fluffImage = fluffMap.get("img");
            if (fluffImage instanceof String image && !image.isBlank()) {
                this.fluff = Map.of("img", image);
            }
            if (!fluffMap.isEmpty()) {
                this.detachedFluff = fluffMap;
            }
            List<Object> cargoMap = getCargo(entity);
            if (cargoMap != null && !cargoMap.isEmpty()) {
                this.cargo = cargoMap;
            }
            Map<String, Object> capitalMap = getCapitalData(entity);
            if (capitalMap != null && !capitalMap.isEmpty()) {
                this.capital = capitalMap;
            }
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

        private static Map<String, Object> getFluffAttributes(Entity entity) {
            Map<String, Object> fluffMap = new HashMap<>();
            EntityFluff entityFluff = entity.getFluff();
            String fluffPath = FluffImageHelper.getFluffImagePath(entity);
            if (fluffPath != null) {
                if (!fluffPath.endsWith("hud.png")) {
                    fluffPath = fluffPath.replace("\\", "/").replaceFirst(".*/fluff/", "");
                    fluffMap.put("img", fluffPath);
                }
            }
            if (entityFluff != null) {
                if (entityFluff.getCapabilities() != null && !entityFluff.getCapabilities().isBlank()) {
                    fluffMap.put("capabilities", entityFluff.getCapabilities());
                }
                if (entityFluff.getDeployment() != null && !entityFluff.getDeployment().isBlank()) {
                    fluffMap.put("deployment", entityFluff.getDeployment());
                }
                if (entityFluff.getHistory() != null && !entityFluff.getHistory().isBlank()) {
                    fluffMap.put("history", entityFluff.getHistory());
                }
                if (entityFluff.getManufacturer() != null && !entityFluff.getManufacturer().isBlank()) {
                    fluffMap.put("manufacturer", entityFluff.getManufacturer());
                }
                if (entityFluff.getNotes() != null && !entityFluff.getNotes().isBlank()) {
                    fluffMap.put("notes", entityFluff.getNotes());
                }
                if (entityFluff.getOverview() != null && !entityFluff.getOverview().isBlank()) {
                    fluffMap.put("overview", entityFluff.getOverview());
                }
                if (entityFluff.getPrimaryFactory() != null && !entityFluff.getPrimaryFactory().isBlank()) {
                    fluffMap.put("primaryFactory", entityFluff.getPrimaryFactory());
                }

                // Loop through Systems
                List<Map> systems = new ArrayList<>();
                for (megamek.common.units.System system : megamek.common.units.System.values()) {
                    if ((system == megamek.common.units.System.JUMP_JET)
                          && entity.hasETypeFlag(Entity.ETYPE_AERO)) {
                        continue;
                    }

                    // System Label
                    String label = resourcesTabs.getString("FluffTab.System." + system.toString());
                    String manufacturer = entityFluff.getSystemManufacturer(system);
                    String model = entityFluff.getSystemModel(system);
                    Map<String, String> entry = new HashMap<>();
                    if (manufacturer != null && !manufacturer.isBlank()) {
                        entry.put("manufacturer", manufacturer);
                    }
                    if (model != null && !model.isBlank()) {
                        entry.put("model", model);
                    }
                    if (!entry.isEmpty()) {
                        entry.put("label", label);
                        systems.add(entry);
                    }
                }
                if (!systems.isEmpty()) {
                    fluffMap.put("systems", systems);
                }
            }
            return fluffMap;
        }

        public static List<Object> getCargo(Entity entity) {
            List<Transporter> transports = entity.getTransports().stream().toList();
            if (transports.isEmpty()) return null;
            List<Object> output = new ArrayList<>();
            // We can have multiple Bay instances within one conceptual bay on the ship
            // We need to gather all bays with the same ID
            Map<Integer, List<Bay>> bayMap = new TreeMap<>();
            for (Transporter transport : transports) {
                if (transport instanceof BattleArmorHandles) continue; // Is automatic for all Omni
                if (transport instanceof LiftHoist) continue; // We do have the component already
                if (transport instanceof InfantryCompartment) continue; // TODO: need implementation
                if (!(transport instanceof Bay bay)) continue; // TODO: need implementation
                if (bay.isQuarters()) continue; // TODO: need implementation
                List<Bay> bays = bayMap.get(bay.getBayNumber());
                if (bays == null) {
                    bays = new ArrayList<>();
                    bays.add(bay);
                    bayMap.put(bay.getBayNumber(), bays);
                } else {
                    bays.add(bay);
                }
            }
            // Print each bay
            for (Integer bayNum : bayMap.keySet()) {
                StringBuilder bayTypeString = new StringBuilder();
                StringBuilder bayCapacityString = new StringBuilder();
                List<Bay> bays = bayMap.get(bayNum);
                // Display larger storage first
                bays.sort(Comparator.comparing(Bay::getCapacity));
                int doors = 0;
                for (int i = 0; i < bays.size(); i++) {
                    Bay bay = bays.get(i);
                    bayTypeString.append(bay.getNameForRecordSheets());
                    // BA bays are shown per suit rather than squad
                    double capacity = getCapacity(bay);
                    bayCapacityString.append(NumberFormat.getInstance().format(capacity));
                    if ((i + 1) < bays.size()) {
                        bayTypeString.append('/');
                        bayCapacityString.append('/');
                    }
                    doors = Math.max(doors, bay.getDoors());
                }
                Map<String, Object> bayEntry = new HashMap<>();
                bayEntry.put("n", bayNum.toString());
                bayEntry.put("type", bayTypeString.toString());
                bayEntry.put("capacity", bayCapacityString.toString());
                bayEntry.put("doors", doors);
                output.add(bayEntry);
            }
            return output;
        }

        private static double getCapacity(Bay b) {
            double capacity = b.getCapacity();
            if (b instanceof BattleArmorBay) {
                if (b.isClan()) {
                    capacity *= 5;
                } else if (((BattleArmorBay) b).isComStar()) {
                    capacity *= 6;
                } else {
                    capacity *= 4;
                }
            } else if (b instanceof InfantryBay) {
                // Divide total weight by weight required by platoon to get platoon capacity
                capacity /= ((InfantryBay) b).getPlatoonType().getWeight();
            } else if (b instanceof ProtoMekBay) {
                capacity *= 5;
            }
            return capacity;
        }

        public static Map<String, Object> getCapitalData(Entity entity) {
            if (!(entity instanceof Jumpship)) return null;
            Jumpship aero = (Jumpship) entity;
            Map<String, Object> output = new HashMap<>();
            output.put("dropshipCapacity", aero.getDockingCollars().size());
            output.put("escapePods", aero.getEscapePods());
            output.put("lifeBoats", aero.getLifeBoats());
            output.put("gravDecks",  aero.getGravDecks());
            output.put("sailIntegrity", aero.hasSail() ? aero.getSailIntegrity() : 0);
            output.put("kfIntegrity", (aero.getDriveCoreType() != Jumpship.DRIVE_CORE_NONE) ? aero.getKFIntegrity() : 0);
            return output;
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
         * Calculates sustained Damage per Turn (DPT) considering heat limits and ammo availability.
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

            // Calculate fire fraction based on heat FIRST, as it affects ammo consumption
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

            // Pre-calculate ammo multipliers, accounting for reduced fire rate due to heat
            // If we only fire at 50% rate due to heat, we only consume 50% of the ammo
            Map<String, Double> ammoMultipliers = calculateAmmoMultipliers(entity, allWeapons, fireFraction);

            for (WeaponMounted weapon : allWeapons) {
                double damage;
                if (weapon.getType() instanceof RACWeapon || weapon.getType() instanceof UACWeapon) {
                    damage = weapon.getType().getDamage(); // in getDamageMultiplier we will have the coefficient to use
                } else {
                    damage = SVGMassPrinter.getMaxDamage(entity, weapon.getType());
                }
                double damageModifier = getDamageMultiplier(entity, weapon, weapon.getType(), ammoMultipliers);
                totalDPT += damage * damageModifier * fireFraction;
            }
            return totalDPT;
        }

        public int getMaxHeatDissipation(Entity entity) {
            int sinks;

            if (entity instanceof Mek mek) {
                sinks = mek.getActiveSinks();
            } else if (entity instanceof Aero aero) {
                sinks = aero.getHeatSinks();
            } else {
                return 0;
            }

            int capacity = entity.getHeatCapacity(false);

            // Radical Heat Sinks
            if (entity.hasWorkingMisc(MiscType.F_RADICAL_HEATSINK)) {
                capacity += sinks;
            }

            // Coolant Pod
            for (AmmoMounted ammoMounted : entity.getAmmo()) {
                if (ammoMounted.getType().getAmmoType() == AmmoType.AmmoTypeEnum.COOLANT_POD) {
                    capacity += sinks;
                    break;
                }
            }

            // RISC ECS
            for (MiscMounted miscMounted : entity.getMisc()) {
                if (miscMounted.getType().hasFlag(MiscType.F_EMERGENCY_COOLANT_SYSTEM)) {
                    capacity += 6;
                }
            }
            return capacity;
        }

        /**
         * Calculates ammo availability multipliers for all weapon types.
         * For weapons sharing the same ammo type, calculates how many turns worth of ammo is available
         * over the SUSTAINED_TURNS period, accounting for reduced fire rate due to heat.
         *
         * @param entity The entity to analyze
         * @param allWeapons List of all weapons to consider
         * @param fireFraction The fraction of time weapons can fire (0.0 to 1.0), based on heat management
         * @return Map of ammo key (ammoType:rackSize) to multiplier (0.0 to 1.0)
         */
        private Map<String, Double> calculateAmmoMultipliers(Entity entity, List<WeaponMounted> allWeapons,
                                                              double fireFraction) {
            // Map to track total shots needed per ammo type over effective firing turns
            Map<String, Double> shotsNeededPerType = new HashMap<>();
            // Map to track total ammo available per type
            Map<String, Integer> ammoAvailablePerType = new HashMap<>();

            // Effective turns of firing, accounting for heat-limited fire rate
            double effectiveTurns = SUSTAINED_TURNS * fireFraction;

            // Calculate shots needed for each weapon type
            for (WeaponMounted weapon : allWeapons) {
                WeaponType wtype = weapon.getType();
                if (wtype.getAmmoType() == AmmoType.AmmoTypeEnum.NA) {
                    continue; // Weapon doesn't use ammo
                }
                if (wtype.hasFlag(WeaponType.F_ONE_SHOT) || wtype.hasFlag(WeaponType.F_DOUBLE_ONE_SHOT)) {
                    continue; // One-shot weapons already handled separately
                }

                String ammoKey = getAmmoKey(wtype);
                int shotsPerTurn = getShotsPerTurn(wtype);
                double totalShotsNeeded = shotsPerTurn * effectiveTurns;

                // For Battle Armor squad weapons, multiply by expected squad size
                if (entity instanceof BattleArmor ba
                        && weapon.getLocation() == BattleArmor.LOC_SQUAD
                        && !weapon.isSquadSupportWeapon()) {
                    totalShotsNeeded *= ba.getSquadSize();
                }

                shotsNeededPerType.merge(ammoKey, totalShotsNeeded, Double::sum);
            }

            // Calculate total ammo available for each type
            for (AmmoMounted ammo : entity.getAmmo()) {
                AmmoType ammoType = ammo.getType();
                String ammoKey = ammoType.getAmmoType() + ":" + ammoType.getRackSize();
                int shotsAvailable = ammo.getBaseShotsLeft();
                ammoAvailablePerType.merge(ammoKey, shotsAvailable, Integer::sum);
            }

            // Calculate multipliers
            Map<String, Double> multipliers = new HashMap<>();
            for (Map.Entry<String, Double> entry : shotsNeededPerType.entrySet()) {
                String ammoKey = entry.getKey();
                double shotsNeeded = entry.getValue();
                int shotsAvailable = ammoAvailablePerType.getOrDefault(ammoKey, 0);

                if (shotsNeeded <= 0) {
                    multipliers.put(ammoKey, 1.0);
                } else if (shotsAvailable <= 0) {
                    multipliers.put(ammoKey, 0.0);
                } else {
                    multipliers.put(ammoKey, Math.min(1.0, shotsAvailable / shotsNeeded));
                }
            }

            return multipliers;
        }

        /**
         * Gets a unique key for ammo type matching (ammoType:rackSize).
         */
        private String getAmmoKey(WeaponType wtype) {
            return wtype.getAmmoType() + ":" + wtype.getRackSize();
        }

        /**
         * Calculates the number of shots consumed per turn for a weapon type.
         * Accounts for multi-shot weapons like RAC (6 shots) and UAC (2 shots).
         */
        private int getShotsPerTurn(WeaponType wtype) {
            // RAC fires 6 shots per turn at max rate
            if (wtype instanceof RACWeapon) {
                return 6;
            }
            // UAC fires 2 shots per turn in ultra mode
            if (wtype instanceof UACWeapon) {
                return 2;
            }
            // Standard weapons fire 1 shot per turn
            return 1;
        }

        private static float[] expectedHitsByRackSize = { 0.0f, 1.0f, 1.58f, 2.0f,
                                                          2.63f, 3.17f, 4.0f, 4.49f, 4.98f, 5.47f, 6.31f, 7.23f, 8.14f,
                                                          8.59f, 9.04f, 9.5f, 10.1f, 10.8f, 11.42f, 12.1f, 12.7f };

        private double getDamageMultiplier(Entity entity, Mounted<?> weapon, WeaponType weaponType,
                                             Map<String, Double> ammoMultipliers) {
            double damageModifier = 1d;
            // Oneshot or TwoShots
            if (weaponType.hasFlag(WeaponType.F_DOUBLE_ONE_SHOT)) {
                damageModifier *= 2.0 / SUSTAINED_TURNS; // Two shots over SUSTAINED_TURNS
            } else
            if (weaponType.hasFlag(WeaponType.F_ONE_SHOT)) {
                damageModifier *= 1.0 / SUSTAINED_TURNS; // One shot over SUSTAINED_TURNS
            }

            // Apply ammo availability multiplier for ammo-using weapons (non-oneshot)
            if (weaponType.getAmmoType() != AmmoType.AmmoTypeEnum.NA
                  && !weaponType.hasAnyFlag(WeaponType.F_ONE_SHOT, WeaponType.F_DOUBLE_ONE_SHOT)) {
                String ammoKey = getAmmoKey(weaponType);
                double ammoMultiplier = ammoMultipliers.getOrDefault(ammoKey, 1.0);
                damageModifier *= ammoMultiplier;
            }

            // cluster weapons or Battle Armor (cluster table)
            if ((weaponType.getDamage() == DAMAGE_BY_CLUSTER_TABLE)) {
                if ((weaponType.getRackSize() != 40) && (weaponType.getRackSize() != 30)) {
                    final double expectedHits = (expectedHitsByRackSize[weaponType.getRackSize()]);
                    damageModifier *= expectedHits / weaponType.getRackSize();
                } else {
                    final double expectedHits = (2.0f * expectedHitsByRackSize[weaponType.getRackSize() / 2]);
                    damageModifier *= expectedHits / weaponType.getRackSize();
                }
            }

            if (weaponType instanceof RACWeapon) {
                damageModifier *= 3.17; // 5 shots average expected hits
            } else
            if (weaponType instanceof UACWeapon) {
                damageModifier *= 1.42; // Rapid mode
            }

            if (entity instanceof BattleArmor ba && (weapon.getLocation()==BattleArmor.LOC_SQUAD) && !weapon.isSquadSupportWeapon()) {
                // We have an entry of a single weapon but in real is N weapons equal to the squad size so we use the
                // cluster table
                damageModifier *=  (expectedHitsByRackSize[ba.getSquadSize()]);
            }

            // Targeting Computer
//           if (entity.hasTargComp() && weaponType.hasFlag(WeaponType.F_DIRECT_FIRE)) {
//               damageModifier *= 1.10;
//           }
            // Actuator Enhancement System
//           if (weapon != null && entity.hasWorkingMisc(MiscType.F_ACTUATOR_ENHANCEMENT_SYSTEM, null,
//                 weapon.getLocation()) &&
//                 ((weapon.getLocation() == Mek.LOC_LEFT_ARM) || (weapon.getLocation() == Mek.LOC_RIGHT_ARM))) {
//               damageModifier *= 1.05;
//           }

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

        private String getArmorType(Entity entity) {
            if (entity instanceof ConvInfantry infantry) {
                EquipmentType armor = infantry.getArmorKit();
                if (armor != null) {
                    return armor.getName();
                } else {
                    if (infantry.hasDEST()) {
                        return "Custom DEST";
                    } else {
                        StringJoiner sj = new StringJoiner("/");
                        if (infantry.hasSneakCamo()) {
                            sj.add("Camo");
                        }
                        if (infantry.hasSneakIR()) {
                            sj.add("IR");
                        }
                        if (infantry.hasSneakECM()) {
                            sj.add("ECM");
                        }
                        if (sj.length() > 0) {
                            return "Custom Sneak(" + sj + ")";
                        } else if (infantry.getCustomArmorDamageDivisor() != 1.0) {
                            return "Custom";
                        }
                    }
                }
            } else if (entity.isSupportVehicle()
                  && (entity.hasBARArmor(0))) {
                return "BAR: " + entity.getBARRating(0);
            } else if (!entity.hasPatchworkArmor()) {
                final int at = entity.getArmorType(0);
                String armorType = (at == T_ARMOR_STANDARD) ? "Standard Armor" : EquipmentType.getArmorTypeName(at);
                if (entity.hasBARArmor(0)) {
                    armorType += ", BAR: " + entity.getBARRating(0);
                }
                return armorType;
            } else {
                boolean hasSpecial = false;
                for (int loc = 0; loc < entity.locations(); loc++) {
                    if ((entity.getArmorType(loc) != T_ARMOR_STANDARD)
                          && (entity.getArmorType(loc) != T_ARMOR_BA_STANDARD)
                          && (entity.getArmorType(loc) != T_ARMOR_STANDARD_PROTOMEK)
                          // Stealth armor loses special properties when used with patchwork, so we don't
                          // need to show it.
                          && (entity.getArmorType(loc) != EquipmentType.T_ARMOR_STEALTH)
                          && (entity.getArmorType(loc) != EquipmentType.T_ARMOR_STEALTH_VEHICLE)) {
                        hasSpecial = true;
                        break;
                    }
                }
                if (hasSpecial) {
                    return EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK);
                } else {
                    return "Standard Armor";
                }
            }
            return "";
        }

        private @Nullable String getStructureType(Entity entity) {
            if (entity.getStructureType() < 0) {
                return null;
            }
            return EquipmentType.getStructureTypeName(entity.getStructureType());
        }

        /**
         * Determines the heat sink type name for the given entity.
         * For Mek, inspects installed heat sink equipment (prototype doubles, then any heat sink).
         * For Aero, uses the heat type setting (single vs double).
         *
         * @return A string identifying the heat sink type, or null if not applicable.
         */
        private @Nullable String getHeatSinkTypeName(Entity entity) {
            if (entity instanceof Mek mek) {
                // Check for prototype doubles first, then fall back to any heat sink
                Optional<MiscType> hs = mek.getMisc().stream()
                      .map(Mounted::getType)
                      .filter(et -> et.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE))
                      .findAny();
                if (hs.isEmpty()) {
                    hs = mek.getMisc().stream()
                          .map(Mounted::getType)
                          .filter(UnitUtil::isHeatSink)
                          .findAny();
                }
                if (hs.isPresent()) {
                    MiscType hsType = hs.get();
                    // Normalize 2-packed compact HS to the single compact type
                    if (hsType.is(EquipmentTypeLookup.COMPACT_HS_2)) {
                        hsType = (MiscType) EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_1);
                    }
                    return hsType.getInternalName();
                }
                return EquipmentTypeLookup.SINGLE_HS;
            } else if (entity instanceof Aero aero) {
                return (aero.getHeatType() == Aero.HEAT_DOUBLE)
                      ? EquipmentTypeLookup.IS_DOUBLE_HS
                      : EquipmentTypeLookup.SINGLE_HS;
            }
            return null;
        }

        private String getMoveType(Entity entity) {
            return entity.getMovementModeAsString();
        }
    }

    protected static String formatRulesLevel(Entity entity, RecordSheetOptions options) {
        SimpleTechLevel level = entity.getStaticTechLevel();
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

        final File unitFilesDir = new File(ROOT_FOLDER + File.separator + UNIT_FILES_DIR);
        if (!SKIP_UNIT_FILES) {
            if (unitFilesDir.exists()) {
                try (var walk = Files.walk(unitFilesDir.toPath())) {
                    walk.sorted(Comparator.reverseOrder())
                          .forEach(path -> {
                              try {
                                  File file = path.toFile();
                                  if (!file.canWrite()) {
                                      file.setWritable(true);
                                  }
                                  Files.deleteIfExists(path);
                              } catch (IOException e) {
                                  logger.warn("Failed to delete path: {}", path);
                              }
                          });
                    logger.info("Deleted existing unit files directory: {}", unitFilesDir.getPath());
                } catch (IOException e) {
                    logger.error("Failed to delete unit files directory: {}", e.getMessage());
                }
            }
            if (!unitFilesDir.exists() || !unitFilesDir.isDirectory()) {
                if (!unitFilesDir.mkdirs()) {
                    logger.error("Failed to create unit files directory: {}", unitFilesDir.getPath());
                    System.exit(1);
                } else {
                    logger.info("Unit files directory created: {}", unitFilesDir.getPath());
                }
            }
        }

        for (int i = 0; i < UnitType.SIZE; i++) {
            // the AERO type does not match any units and there are no preconstructed life boats or escape pods
            if (i != UnitType.AERO) {
                unitTypes.put(i, UnitType.getTypeDisplayableName(i));
            }
        }
        unitTypes.put(UnitType.SIZE, Messages.getString("MekSelectorDialog.SupportVee"));

        Map<String, String> processedFiles = new ConcurrentHashMap<>();
        Map<String, Set<String>> duplicateUnits = new ConcurrentHashMap<>();
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        CConfig.setParam(CConfig.RS_FONT, TYPEFACE);

        int processedCount = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        long timestamp = System.currentTimeMillis();
        Map<String, Entity> uniqueUnitTypes = new ConcurrentHashMap<>();

        RecordSheetOptions recordSheetOptions = getRecordSheetOptions();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        MekSummary[] meks = cache.getAllMeks();
        logger.info("Processing {} meks...", meks.length);

        PageFormat pf = new PageFormat();
        PaperSize paperDef = recordSheetOptions.getPaperSize();

        final AtomicInteger processedCounter = new AtomicInteger(0);
        final AtomicInteger unitFilesSavedCounter = new AtomicInteger(0);
        int parallelism = ForkJoinPool.getCommonPoolParallelism();
        logger.info("Starting parallel processing with {} threads...", parallelism);

        List<UnitData> unitDataList = new ArrayList<>();

        if (!SKIP_UNITS) {
        final Object loadEntityLock = new Object();
        final Object updateUnitLock = new Object();
        final Object idLock = new Object();
        final Object mkdirLock = new Object();
          final Object saveUnitFileLock = new Object();
        unitDataList = Arrays.stream(meks)
              .parallel()
              .map(mekSummary -> {
//                    if (!mekSummary.isBattleArmor()) return null;
//                    if (mekSummary.getMulId() != 4669) return null;
//                    logger.info("{}", mekSummary.getName());
              Entity entity;
              synchronized (loadEntityLock) {
                  entity = mekSummary.loadEntity();
              }
              if ((entity == null) || (entity instanceof GunEmplacement)) {
                  return null;
              }
              synchronized (updateUnitLock) {
                  UnitUtil.updateLoadedUnit(entity);
              }
              for (int i = 0; i < entity.getCrew().getSlotCount(); i++) {
                    entity.getCrew().setName("", i);
              }
              if (entity.getId() == -1) {
                  synchronized (idLock) {
                      entity.setId(entity.getGame().getNextEntityId());
                  }
              }
              String svgPath = FluffImageHelper.getFluffPath(entity)
                    .toLowerCase()
                    .replaceAll("[^a-zA-Z0-9_]", "");
              File sheetPath = new File(sheetsDir.getPath(), svgPath);
              synchronized (mkdirLock) {
                  if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                      logger.error("Couldn't create folder {}", sheetPath);
                  }
              }
              String name = generateName(entity);
              String unitLogName = describeUnitForLog(mekSummary);
              String existingUnit = processedFiles.putIfAbsent(name, unitLogName);
              if (existingUnit != null) {
                  duplicateUnits.computeIfAbsent(name, ignored -> ConcurrentHashMap.newKeySet())
                        .add(unitLogName);
                  logger.warn("Duplicate export name {}. Keeping {} and skipping {}.",
                        name, existingUnit, unitLogName);
                  return null;
              }

              final File outputFile = resolveUnitFileExportPath(unitFilesDir, mekSummary, entity, name);
              String relativeUnitFilePath = unitFilesDir.toPath().relativize(outputFile.toPath()).toString().replace('\\', '/');

              if (!SKIP_UNIT_FILES) {
                  synchronized (saveUnitFileLock) {
                      File parent = outputFile.getParentFile();
                      if ((parent != null) && !parent.exists() && !parent.mkdirs()) {
                          logger.error("Failed to create folder {}", parent.getPath());
                          return null;
                      }
                      try (FileOutputStream fos = new FileOutputStream(outputFile);
                            PrintStream ps = new PrintStream(fos)) {
                            ps.println(LICENSE_HEADER);
                            ps.println(UnitUtil.saveUnitToString(entity, true));
                            unitFilesSavedCounter.incrementAndGet();
                      } catch (Exception e) {
                          logger.error(e, "Failed to save unit file for " + mekSummary.getName());
                      }
                  }
              }

              UnitData unitData = new UnitData(mekSummary, entity, recordSheetOptions);
              unitData.unitFile = relativeUnitFilePath;
              unitData.name = name;
              boolean isSmallUnit = entity.isBattleArmor() || entity.isProtoMek() || entity.isInfantry();
              try {
                  List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(List.of(entity),
                        true,
                        recordSheetOptions, true);
                  if (sheets.isEmpty()) {
                      logger.error("No sheets generated for {}", mekSummary.getName());
                      return null;
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
                          pf.setPaper(paperDef.createPaper());
                          int pageCount = sheet.getPageCount();
                          for (int pageIndexInSheet = 0; pageIndexInSheet < pageCount; pageIndexInSheet++) {
                              sheet.createDocument(pageIndexInSheet, pf, true);
                              if (pageCount > 1) {
                                  svgDocs.add((Document) sheet.getSVGDocument().cloneNode(true));
                              } else {
                                  svgDocs.add(sheet.getSVGDocument());
                              }
                          }
                      }
                      if (svgDocs.isEmpty()) {
                          logger.error("No SVG documents for {}", mekSummary.getName());
                          return null;
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
              } catch (Exception e) {
                  logger.error(e, "Printing Error for " + mekSummary.getName());
                  return null;
              }

              unitData.pv = mekSummary.getPointValue();
              unitData.su = isSmallUnit ? 1 : 0;

              if (!uniqueUnitTypes.containsKey(unitData.type)) {
                  uniqueUnitTypes.put(unitData.type, entity);
              }
              return unitData;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());


        try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FILE)) {
            jsonWriter.write("{\"version\":" + timestamp + ",\n");
            jsonWriter.write("\"units\":[\n");
            boolean firstUnit = true;
            for (UnitData unitData : unitDataList) {
                String jsonLine = mapper.writeValueAsString(unitData);
                if (!firstUnit) {
                    jsonWriter.write(",\n");
                }
                jsonWriter.write(jsonLine);
                firstUnit = false;
            }
            jsonWriter.write("\n]}");
        } catch (IOException e) {
            logger.error("Failed to write JSON Lines file: {}", e.getMessage());
        }

        Map<String, Map<String, Object>> unitFluffMap = new TreeMap<>();
        for (UnitData unitData : unitDataList) {
            if ((unitData.detachedFluff != null) && !unitData.detachedFluff.isEmpty()) {
                unitFluffMap.put(unitData.name, unitData.detachedFluff);
            }
        }
        Map<String, Object> unitFluffRoot = new LinkedHashMap<>();
        unitFluffRoot.put("version", timestamp);
        unitFluffRoot.put("fluff", unitFluffMap);
        try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FLUFF_FILE)) {
            mapper.writer().writeValue(jsonWriter, unitFluffRoot);
        } catch (IOException e) {
            logger.error("Failed to write unit fluff file: {}", e.getMessage());
        }

        if (!duplicateUnits.isEmpty()) {
            int duplicateCount = duplicateUnits.values().stream().mapToInt(Set::size).sum();
            logger.warn("Skipped {} duplicate unit exports across {} generated names.",
                  duplicateCount, duplicateUnits.size());
            duplicateUnits.entrySet().stream()
                  .sorted(Map.Entry.comparingByKey())
                  .forEach(entry -> {
                      List<String> skippedUnits = new ArrayList<>(entry.getValue());
                      Collections.sort(skippedUnits);
                      logger.warn("Duplicate export name {} summary. Kept {}. Skipped {}.",
                            entry.getKey(),
                            processedFiles.get(entry.getKey()),
                            String.join(" | ", skippedUnits));
                  });
        }

        logger.info("Processed {} units.", processedCounter.get());
        if (!SKIP_UNIT_FILES) {
            logger.info("Saved {} BLK/MTF unit files.", unitFilesSavedCounter.get());
        }
        } // end if (!SKIP_UNITS)

        // Export Quirks
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

        if (!SKIP_EQUIPMENT) {
            processedCount = 0;
            Map<String, Map<String, Object>> equipmentJsonMap2 = new HashMap<>();
            for (EquipmentType equipmentType : EquipmentType.allTypes()) {
                if (equipmentType.getStaticTechLevel() == SimpleTechLevel.UNOFFICIAL) continue;
                equipmentJsonMap2.put(equipmentType.getInternalName(), equipmentType.getYamlData());
            }
            Map<String, Object> rootJson2 = new LinkedHashMap<>();
            rootJson2.put("version", timestamp);
            rootJson2.put("equipment", equipmentJsonMap2);

            try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + EQUIPMENT_FILE)) {
                mapper.writer().writeValue(jsonWriter, rootJson2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.exit(0);
    }

    private static File resolveUnitFileExportPath(File unitFilesDir, MekSummary mekSummary, Entity entity,
                                                  String fallbackName) {
        Path relativePath = getUnitSourceRelativePath(mekSummary);
        if (relativePath == null) {
            final String extension = (entity instanceof Mek) ? ".mtf" : ".blk";
            return new File(unitFilesDir, fallbackName + extension);
        }
        Path normalizedPath = relativePath.normalize();
        if (normalizedPath.isAbsolute() || normalizedPath.startsWith("..")) {
            normalizedPath = Path.of(normalizedPath.getFileName().toString());
        }
        return unitFilesDir.toPath().resolve(normalizedPath).toFile();
    }

    private static @Nullable Path getUnitSourceRelativePath(MekSummary mekSummary) {
        String entryName = mekSummary.getEntryName();
        if ((entryName != null) && !entryName.isBlank()) {
            return Path.of(entryName.replace('\\', '/'));
        }
        File sourceFile = mekSummary.getSourceFile();
        if (sourceFile == null) {
            return null;
        }
        try {
            Path sourcePath = sourceFile.toPath().toAbsolutePath().normalize();
            Path unitsRoot = Configuration.unitsDir().toPath().toAbsolutePath().normalize();
            if (sourcePath.startsWith(unitsRoot)) {
                return unitsRoot.relativize(sourcePath);
            }
        } catch (Exception e) {
            logger.warn("Failed to resolve source path for {}", mekSummary.getName());
        }
        return Path.of(sourceFile.getName());
    }

    private static String normalizeDamage(WeaponType weapon) {
        int dmg = weapon.getDamage();
        if (weapon instanceof InfantryWeapon wi) {
            return Double.toString(wi.getInfantryDamage());
        }
        if (dmg == DAMAGE_BY_CLUSTER_TABLE) return "cluster";
        if (dmg == DAMAGE_VARIABLE) return "variable";
        if (dmg == DAMAGE_SPECIAL) return "special";
        if (dmg == DAMAGE_ARTILLERY) return "artillery";
        return Integer.toString(dmg);
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
        recordSheetOptions.setAlternateArmorGrouping(false);
        recordSheetOptions.setRowShading(true);
        recordSheetOptions.setFancyPips(true);
        recordSheetOptions.setReferenceCharts(false);
        recordSheetOptions.setEraBasedProgression(false);
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

    private static String describeUnitForLog(MekSummary mekSummary) {
        StringBuilder description = new StringBuilder();
        description.append(mekSummary.getFullChassis());
        if ((mekSummary.getModel() != null) && !mekSummary.getModel().isBlank()) {
            description.append(' ').append(mekSummary.getModel());
        }
        description.append(" [MUL ").append(mekSummary.getMulId()).append(']');

        File sourceFile = mekSummary.getSourceFile();
        if (sourceFile != null) {
            description.append(" file=").append(sourceFile.getName());
        }

        String entryName = mekSummary.getEntryName();
        if ((entryName != null) && !entryName.isBlank()) {
            description.append(" entry=").append(entryName);
        }

        return description.toString();
    }

    private SVGMassPrinter() {
        throw new IllegalStateException();
    }
}
