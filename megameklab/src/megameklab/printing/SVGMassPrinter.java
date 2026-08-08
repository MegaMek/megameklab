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
import java.math.BigDecimal;
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
import javax.swing.JComponent;
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
import com.fasterxml.jackson.core.StreamWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import megamek.MMConstants;
import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.Messages;
import megamek.client.ui.clientGUI.calculationReport.CalculationReport;
import megamek.client.ui.clientGUI.calculationReport.TextCalculationReport;
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
import megamek.common.enums.Faction;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.OptionsConstants;
import megamek.common.options.Quirks;
import megamek.common.verifier.TestProtoMek;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.autoCannons.RACWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megamek.common.weapons.gaussRifles.HAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.missiles.MissileWeapon;
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
    record AlphaStrikeConversion(AlphaStrikeElement element, String report) {}

    static ResourceBundle resourcesTabs = ResourceBundle.getBundle("megameklab.resources.Tabs");
    // The following are defaults that can be overridden via command-line arguments (see parseArgs / printUsage).
    private static boolean SKIP_SVG = false; // Set to true to skip SVG generation
    private static boolean SKIP_UNITS = false; // Set to true to skip units generation
    private static boolean SKIP_EQUIPMENT = false; // Set to true to skip equipment generation
    private static boolean SKIP_UNIT_FILES = true; // Set to true to skip BLK/MTF re-save generation
    private static boolean SKIP_DETAILED_CALCULATIONS = true; // Set to true to skip the detailed BV/Cost calculations
    private static final boolean EXPORT_CALCULATION_DETAILS_TO_FILES = true; // Set to true to not embed the detailed BV/Cost calculations into the units.json but in a subfolder keyed by name
    private static boolean EXPORT_CALCULATIONS_AS_TEXT = false;

    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final int SUSTAINED_TURNS = 10; // Number of turns for sustained DPT calculation
    private static String TYPEFACE = "Roboto";
    private static String SHEETS_DIR = "sheets";
    private static String UNIT_FILES_DIR = "unitfiles";
    private static final String UNIT_FILE = "units.json";
    private static final String UNIT_FLUFF_FILE = "units-fluff.json";
    private static final String EQUIPMENT_FILE = "equipment2.json";
    private static String ROOT_FOLDER = "../../svgexport";
    // When non-empty, only the units in these files are exported instead of the entire official unit cache.
    private static final List<File> UNIT_FILE_OVERRIDES = new ArrayList<>();
    // True once --units/--unit has been passed, even if it resolved to no files (so we can fail instead of
    // silently exporting the whole cache).
    private static boolean unitOverrideRequested = false;
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
        public Integer cw; // Crew required to man this equipment, if applicable
        public int os; // If is an oneshot weapon or a double oneshot weapon (1 or 2), if applicable
        public Collection<ExportInventoryEntry> bay; // Bay weapons, if applicable
    }

    public static class CalculationDetail {
        public String type;
        public String calculation;
        public String result;
        public CalculationReport.LineType lineType;
        public boolean informational;

        private CalculationDetail(String type, String calculation, String result, CalculationReport.LineType lineType,
              boolean informational) {
            this.type = type;
            this.calculation = calculation;
            this.result = result;
            this.lineType = lineType;
            this.informational = informational;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class BVDetail {
        public String type;
        public String calculation;
        public BigDecimal total;
        public BigDecimal delta;
        public List<BVDetail> details;

        private BVDetail(String type, String calculation) {
            this.type = type;
            this.calculation = calculation;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class CostDetail {
        public String type;
        public String calculation;
        public BigDecimal amount;
        public BigDecimal factor;
        public BigDecimal subtotal;
        public boolean informational;

        private CostDetail(String type, String calculation) {
            this.type = type;
            this.calculation = calculation;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class CostDetails {
        public List<CostDetail> steps = new ArrayList<>();
        public BigDecimal total;
    }

    private static class ExportCalculationReport implements CalculationReport {
        private final List<CalculationDetail> details = new ArrayList<>();
        private final TextCalculationReport textReport = new TextCalculationReport();
        private List<CalculationDetail> tentativeDetails;

        private void addDetail(String type, String calculation, String result, CalculationReport.LineType lineType,
              boolean informational) {
            List<CalculationDetail> target = (tentativeDetails == null) ? details : tentativeDetails;
            target.add(new CalculationDetail(type, calculation, result, lineType, informational));
        }

        List<CalculationDetail> getDetails() {
            return List.copyOf(details);
        }

        String getText() {
            return textReport.toString();
        }

        public CalculationReport addLine(String type, String calculation, String result) {
            addDetail(type, calculation, result, CalculationReport.LineType.LINE, false);
            textReport.addLine(type, calculation, result);
            return this;
        }

        @Override
        public CalculationReport addInformationalLine(String type, String calculation, String result) {
            addDetail(type, calculation, result, CalculationReport.LineType.LINE, true);
            textReport.addLine(type, calculation, result);
            return this;
        }

        public CalculationReport addSubHeader(String text) {
            addDetail(text, "", "", CalculationReport.LineType.SUBHEADER, false);
            textReport.addSubHeader(text);
            return this;
        }

        public CalculationReport addHeader(String text) {
            addDetail(text, "", "", CalculationReport.LineType.HEADER, false);
            textReport.addHeader(text);
            return this;
        }

        public CalculationReport addResultLine(String type, String calculation, String result) {
            addDetail(type, calculation, result, CalculationReport.LineType.RESULT_LINE, false);
            textReport.addResultLine(type, calculation, result);
            return this;
        }

        public JComponent toJComponent() {
            return null;
        }

        public void startTentativeSection() {
            if (tentativeDetails == null) {
                tentativeDetails = new ArrayList<>();
            }
            textReport.startTentativeSection();
        }

        public void endTentativeSection() {
            if (tentativeDetails != null) {
                details.addAll(tentativeDetails);
                tentativeDetails = null;
            }
            textReport.endTentativeSection();
        }

        public void discardTentativeSection() {
            tentativeDetails = null;
            textReport.discardTentativeSection();
        }
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
        public Map<String, ExportInventoryEntry> comps = new LinkedHashMap<>();

        @JsonProperty("comp")
        public Collection<ExportInventoryEntry> getComp() {
            return List.copyOf(comps.values());
        }

        private String getMWCategory(EquipmentType eq) {
            if (eq instanceof WeaponType wp) {
                AmmoType.AmmoTypeEnum ammoType = wp.getAmmoType();
                AmmoType.AmmoCategory ammoCategory = (ammoType == null) ? null : ammoType.getCategory();
                if (wp.hasFlag(WeaponType.F_ENERGY)
                    || (wp.hasFlag(WeaponType.F_PLASMA) && (ammoType == AmmoType.AmmoTypeEnum.PLASMA))
                    || (ammoCategory == AmmoType.AmmoCategory.Energy)) {
                    return "E";
                }
                if (wp.hasFlag(WeaponType.F_ARTILLERY)
                    || (ammoCategory == AmmoType.AmmoCategory.Artillery)) {
                    return "A";
                }
                if (wp.hasFlag(WeaponType.F_BALLISTIC)
                    || (ammoCategory == AmmoType.AmmoCategory.Ballistic)) {
                    return "B";
                }
                if (wp.hasFlag(WeaponType.F_MISSILE)
                    || (ammoCategory == AmmoType.AmmoCategory.Missile)) {
                    return "M";
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

            if (wtype.hasFlag(WeaponType.F_LARGE_MISSILE)) {
                return Integer.toString(getLargeMissileDamage(wtype));
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
                    if ((wtype instanceof ATMWeapon)
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
                    return dmg + "/Msl";
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

        private int getLargeMissileDamage(WeaponType weapon) {
            return AmmoType.getMunitionsFor(weapon.getAmmoType()).stream()
                  .filter(ammo -> ammo.getRackSize() == weapon.getRackSize())
                  .filter(ammo -> ammo.getMunitionType().contains(AmmoType.Munitions.M_STANDARD))
                  .mapToInt(AmmoType::getDamagePerShot)
                  .findFirst()
                  .orElse(0);
        }

        private final String replacePattern = "\\s*(?:\\((?:[^()\\[\\]]|\\[[^\\]]*\\])*\\)|\\[(?:[^()\\[\\]]|\\([^)]*\\))*\\])";
        private String cleanupName(String name) {
            return name;
            // Remove any text in parentheses, e.g. "Laser Rifle (ER [Sunbeam Starfire])"
//            return name.replaceAll(replacePattern, "").trim();
        }

          private ExportInventoryEntry addWeaponEntry(Map<String, ExportInventoryEntry> list, Entity entity,
              @Nullable WeaponMounted mounted, WeaponType type,
              String location, int locId) {
            final String name = type.getShortName();
            final boolean rearMounted = mounted.isRearMounted();
            final String key = type.getInternalName() + "_" + location + (rearMounted ? "_rear" : "");
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
                entry.md = String.valueOf(type.hasFlag(WeaponType.F_LARGE_MISSILE)
                    ? getLargeMissileDamage(type)
                    : SVGMassPrinter.getMaxDamage(entity, type));
                if (type.hasFlag(WeaponTypeFlag.F_DOUBLE_ONE_SHOT)) {
                    entry.os = 2; // If the weapon is double oneshot
                } else if (type.hasFlag(WeaponTypeFlag.F_ONE_SHOT)) {
                    entry.os = 1; // If the weapon is oneshot
                }
                entry.c = getCriticals(entity, type);
                if ((entity instanceof ConvInfantry infantry) && (locId == ConvInfantry.LOC_FIELD_GUNS)) {
                    entry.cw = Math.max(2, (int) Math.ceil(type.getTonnage(infantry)));
                }
                list.put(key, entry);
                return entry;
            }
        }

        public Components(Entity entity) {
            addImplicitStructureEntry(this.comps, entity);
            addSyntheticArmorEntries(this.comps, entity);
            if (entity.usesWeaponBays()) {
                parseBays(this.comps, entity);
            } else {
                parseComponents(this.comps, entity);
            }
        }

          private ExportInventoryEntry addWeaponBay(Map<String, ExportInventoryEntry> list, Entity entity,
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


        private void parseBays(Map<String, ExportInventoryEntry> list, Entity entity) {
            for (WeaponMounted bay : entity.getWeaponList()) {
                Map<String, ExportInventoryEntry> bayList = new LinkedHashMap<>();
                for (WeaponMounted weaponMounted : bay.getBayWeapons()) {
                    addWeaponEntry(bayList, entity, bay, weaponMounted.getType(), "", 0);
                }
                ExportInventoryEntry weaponBay = addWeaponBay(list, entity, bay.getType(),
                      entity.joinLocationAbbr(bay.allLocations(), 2), bay.getLocation());
                weaponBay.bay = bayList.values();
                //TODO: add artemisIV, artemisV and apollo
            }
        }

        private void parseComponents(Map<String, ExportInventoryEntry> list, Entity entity) {
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

            if (entity instanceof Mek mek) {
                addMekSystemEntry(list, mek, Mek.SYSTEM_COCKPIT, "cockpit",
                      withSystemSuffix(Mek.getCockpitTypeString(mek.getCockpitType()), "Cockpit"));
                if (mek.getGyroType() != Mek.GYRO_NONE) {
                    addMekSystemEntry(list, mek, Mek.SYSTEM_GYRO, "gyro",
                          withSystemSuffix(mek.getGyroTypeString(), "Gyro"));
                }
                addActuatorEntry(list, mek, Mek.ACTUATOR_LOWER_ARM, Mek.LOC_LEFT_ARM, "lower-arm");
                addActuatorEntry(list, mek, Mek.ACTUATOR_HAND, Mek.LOC_LEFT_ARM, "hand");
                addActuatorEntry(list, mek, Mek.ACTUATOR_LOWER_ARM, Mek.LOC_RIGHT_ARM, "lower-arm");
                addActuatorEntry(list, mek, Mek.ACTUATOR_HAND, Mek.LOC_RIGHT_ARM, "hand");
            }

            List<Mounted<?>> mountedList = entity.getEquipment();
            for (Mounted<?> m : mountedList) {
                if (m.isWeaponGroup()) {
                    continue;
                }
                // Structure is exported once from the entity's selected structure type, not per critical slot.
                if (m.getType() instanceof StructureType) {
                    continue;
                }
                // Armor is exported from the entity's effective armor configuration, not per critical slot.
                if (m.getType() instanceof ArmorType) {
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

        private void addMekSystemEntry(Map<String, ExportInventoryEntry> list, Mek mek, int system,
              String id, String name) {
            int location = findSystemLocation(mek, system);
            if (location == Entity.LOC_NONE) {
                return;
            }

            ExportInventoryEntry entry = new ExportInventoryEntry();
            entry.id = id;
            entry.n = name;
            entry.t = "S";
            entry.q = 1;
            entry.p = location;
            entry.l = mek.getLocationAbbr(location);
            entry.c = Integer.toString(mek.getNumberOfCriticalSlots(CriticalSlot.TYPE_SYSTEM, system, location));
            list.put(id, entry);
        }

        private int findSystemLocation(Mek mek, int system) {
            for (int location = 0; location < mek.locations(); location++) {
                if (mek.getNumberOfCriticalSlots(CriticalSlot.TYPE_SYSTEM, system, location) > 0) {
                    return location;
                }
            }
            return Entity.LOC_NONE;
        }

        private String withSystemSuffix(String name, String suffix) {
            return name.endsWith(suffix) ? name : name + " " + suffix;
        }

        private void addActuatorEntry(Map<String, ExportInventoryEntry> list, Mek mek, int actuator,
              int location, String id) {
            if (!mek.hasSystem(actuator, location)) {
                return;
            }

            String locationAbbreviation = mek.getLocationAbbr(location);
            ExportInventoryEntry entry = new ExportInventoryEntry();
            entry.id = id;
            entry.n = mek.getSystemName(actuator) + " Actuator";
            entry.t = "S";
            entry.q = 1;
            entry.p = location;
            entry.l = locationAbbreviation;
            entry.c = "1";
            list.put(locationAbbreviation + ":" + id, entry);
        }

        /**
                 * Exports the entity's selected internal structure as one normalized component.
                 * Mounted structure critical slots are deliberately omitted from the inventory export.
         */
        private void addImplicitStructureEntry(Map<String, ExportInventoryEntry> list, Entity entity) {
                        if (entity.getStructureType() < 0) {
                return;
            }

            StructureType structure = EquipmentType.getStructureFromName(
                  EquipmentType.getStructureTypeName(entity.getStructureType(), entity.isClan()));
            if (structure == null) {
                return;
            }

            ExportInventoryEntry entry = new ExportInventoryEntry();
            entry.id = structure.getInternalName();
            entry.n = withMaterialSuffix(cleanupName(structure.getShortName()), "Structure");
            entry.t = "S";
            entry.q = 1;
            entry.p = -1;
            entry.c = getCriticals(entity, structure);
            list.put(structure.getInternalName() + "__S", entry);
        }

        /**
         * Exports armor as normalized entity-level entries. Patchwork retains its marker and exposes every distinct
         * effective armor material, while location-specific armor critical slots remain an implementation detail.
         */
        private void addSyntheticArmorEntries(Map<String, ExportInventoryEntry> list, Entity entity) {
            if (entity.locations() == 0) {
                return;
            }

            if (entity.hasPatchworkArmor()) {
                addSyntheticArmorEntry(list, entity, EquipmentType.T_ARMOR_PATCHWORK, false, "__patchwork");
                Set<String> emittedArmor = new HashSet<>();
                for (int location = 0; location < entity.locations(); location++) {
                    int armorType = entity.getArmorType(location);
                    boolean clanArmor = entity.isClanArmor(location);
                    String key = armorType + ":" + clanArmor;
                    if (emittedArmor.add(key)) {
                        addSyntheticArmorEntry(list, entity, armorType, clanArmor, "__armor_" + key);
                    }
                }
            } else {
                addSyntheticArmorEntry(list, entity, entity.getArmorType(0), entity.isClanArmor(0), "__armor");
            }
        }

        private void addSyntheticArmorEntry(Map<String, ExportInventoryEntry> list, Entity entity, int armorType,
                                             boolean clanArmor, String suffix) {
            if (armorType < 0) {
                return;
            }

            ArmorType armor = EquipmentType.getArmorFromName(EquipmentType.getArmorTypeName(armorType, clanArmor));
            if (armor == null) {
                return;
            }

            ExportInventoryEntry entry = new ExportInventoryEntry();
            entry.id = armor.getInternalName();
            entry.n = withMaterialSuffix(cleanupName(armor.getShortName()), "Armor");
            entry.t = "S";
            entry.q = 1;
            entry.p = -1;
            entry.c = getCriticals(entity, armor);
            list.put(armor.getInternalName() + suffix, entry);
        }

        private String withMaterialSuffix(String name, String suffix) {
            return name.endsWith(suffix) ? name : name + " " + suffix;
        }

          private void addMiscEntry(Map<String, ExportInventoryEntry> list, Entity entity, MiscMounted mounted,
              MiscType type,
              String location, int locId, boolean isStructural) {
            final String name = type.getShortName();
            final String key = type.getInternalName() + "_" + location + '_' + (isStructural ? "S" : "C");
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

          private void addAmmoEntry(Map<String, ExportInventoryEntry> list, Entity entity, AmmoMounted mounted,
              AmmoType type,
              String location, int locId) {
            final String name = type.getShortName().replace("Ammo", "").trim()+" Ammo";
            final String key = type.getInternalName() + "_" + location;
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

          private void addPhysicalWeapon(Map<String, ExportInventoryEntry> list, Entity entity, MiscMounted mounted,
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
            final String key = type.getInternalName() + "_" + location;
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
        public double loadoutTons; // Weight of loadout
        public int bv; // Battle Value, rounded to the nearest integer
        public int pv; // AS PV, legacy, to be removed (not used anymore)
        public double offSpeedFactor; // Offensive Speed factor (used to compensate Custom Ammo)
        public long cost; // Cost in C-Bills, rounded to the nearest integer
        public String level; // Tech level as a string, e.g. "Introductory", "Standard", etc.
        public String techBase;
        public boolean mixed;
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
        public boolean canAntiMech;
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
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public CostDetails costDetail;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public List<BVDetail> bvDetails;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public String weightBreakdown;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public String techLevelBreakdown;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public String asConversionReport;
        @JsonIgnore
        public String costDetailText;
        @JsonIgnore
        public String bvDetailText;
        //        public String summary;



        /** @return True if this ASUnitType is an Aerospace type, i.e. AF, CF, SC, DS, DA, SS, JS, WS. */
        private boolean isAerospace(ASUnitType tp) { return tp.isAnyOf(ASUnitType.AF, ASUnitType.CF, ASUnitType.SC,
              ASUnitType.DS, ASUnitType.DA, ASUnitType.SS, ASUnitType.JS, ASUnitType.WS); }

        private void loadASUnitData(Entity entity) {
            this.as = null;
            AlphaStrikeConversion conversion = convertToAlphaStrike(entity);
            if (conversion != null) {
                AlphaStrikeElement asElement = conversion.element();
                if (!SKIP_DETAILED_CALCULATIONS) {
                    this.asConversionReport = conversion.report();
                }
                this.pv = asElement.getPointValue();
                this.as = new HashMap<>();
                this.as.put("PV", asElement.getPointValue());
                ASUnitType asUnitType = ASUnitType.getUnitType(entity);
                this.as.put("TP", (asUnitType != ASUnitType.UNKNOWN) ? asUnitType.name() : "XX");
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

        static List<String> getFeatures(Entity entity) {
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
                if (hasArmActuator(mek, Mek.ACTUATOR_UPPER_ARM)
                    && !hasArmActuator(mek, Mek.ACTUATOR_HAND)
                    && !hasArmActuator(mek, Mek.ACTUATOR_LOWER_ARM)
                    && !hasArmTorsoSplitEquipment(mek)) {
                    feats.add("Reversible Arms");
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

        private static boolean hasArmActuator(Mek mek, int actuator) {
            return mek.hasSystem(actuator, Mek.LOC_LEFT_ARM)
                  || mek.hasSystem(actuator, Mek.LOC_RIGHT_ARM);
        }

        private static boolean hasArmTorsoSplitEquipment(Mek mek) {
            return mek.getEquipment().stream()
                  .filter(Mounted::isSplit)
                  .anyMatch(mounted -> isArmTorsoPair(mounted.getLocation(), mounted.getSecondLocation()));
        }

        private static boolean isArmTorsoPair(int firstLocation, int secondLocation) {
            return ((firstLocation == Mek.LOC_LEFT_ARM) && (secondLocation == Mek.LOC_LEFT_TORSO))
                  || ((firstLocation == Mek.LOC_LEFT_TORSO) && (secondLocation == Mek.LOC_LEFT_ARM))
                  || ((firstLocation == Mek.LOC_RIGHT_ARM) && (secondLocation == Mek.LOC_RIGHT_TORSO))
                  || ((firstLocation == Mek.LOC_RIGHT_TORSO) && (secondLocation == Mek.LOC_RIGHT_ARM));
        }

        public UnitData(MekSummary mekSummary, Entity entity, RecordSheetOptions options) {
            this.id = entity.getMulId();
            this.chassis = entity.getFullChassis();
            this.model = entity.getModel();
            this.year = entity.getYear();
            this.weightClass = entity.getWeightClassName();
            this.tons = entity.getWeight();
            this.loadoutTons = calculateLoadoutTonnage(entity);
            ExportCalculationReport bvReport = new ExportCalculationReport();
            this.bv = entity.getBvCalculator().calculateBV(true, true, bvReport);
            if (!SKIP_DETAILED_CALCULATIONS) {
                this.bvDetails = formatBVDetails(bvReport.getDetails());
                this.bvDetailText = bvReport.getText();
            }
            this.offSpeedFactor = entity.getBvCalculator().getOffensiveSpeedFactorMultiplier();
            ExportCalculationReport costReport = new ExportCalculationReport();
            this.cost = Math.round(entity.getCost(costReport, false));
            if (!SKIP_DETAILED_CALCULATIONS) {
                this.costDetail = formatCostDetails(costReport.getDetails());
                this.costDetailText = costReport.getText();
            }
            this.techBase = formatTechBase(entity);
            this.mixed = entity.isMixedTech();
            this.techRating = entity.getFullRatingName();
            this.level = formatRulesLevel(entity, options);
            if (entity.hasEngine() && !(entity instanceof SmallCraft || entity instanceof Jumpship)) {
                Engine unitEngine = entity.getEngine();
                this.engineRating = unitEngine.getRating();
                this.engine = Engine.getEngineTypeName(unitEngine.getEngineType()).trim();
                if (this.engine.equals("XL") || this.engine.equals("XXL")) {
                    this.engine+=(unitEngine.isClan() ? " (Clan)" : " (IS)");
                }
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
            this.canAntiMech = canAntiMech(entity);
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
            this.jump2 = entity.getAnyTypeMaxJumpMP();
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
            if (!SKIP_DETAILED_CALCULATIONS) {
                this.weightBreakdown = createWeightBreakdown(entity);
                this.techLevelBreakdown = createTechLevelBreakdown(entity);
            }
            //            final MekView mekView = new MekView(entity, false, false, ViewFormatting.HTML);
            //            this.summary = mekView.getMekReadout();

            if ((entity instanceof Infantry inf) && !(entity instanceof BattleArmor)) {
                this.dpt = Math.round(calculateSustainedDPTForInfantry(entity));
            } else {
                this.dpt = Math.round(calculateSustainedDPT(entity) * 10) / 10.0;
            }
        }

        static boolean canAntiMech(Entity entity) {
            if (entity instanceof ConvInfantry infantry) {
                return infantry.hasAntiMekGear();
            }
            if (entity instanceof BattleArmor battleArmor) {
                return battleArmor.getWeaponList().stream()
                      .map(mounted -> mounted.getType().getInternalName())
                      .anyMatch(internalName -> Infantry.LEG_ATTACK.equals(internalName)
                            || Infantry.SWARM_MEK.equals(internalName));
            }
            return false;
        }

        private static List<BVDetail> formatBVDetails(List<CalculationDetail> calculationDetails) {
            List<BVDetail> bvDetails = new ArrayList<>();
            BVDetail currentSection = null;
            BVDetail activeGroup = null;
            BigDecimal previousTotal = null;
            Map<BVDetail, BigDecimal> groupStartingTotals = new IdentityHashMap<>();

            for (int index = 0; index < calculationDetails.size(); index++) {
                CalculationDetail detail = calculationDetails.get(index);
                if (detail.lineType == CalculationReport.LineType.HEADER) {
                    continue;
                }
                if (detail.lineType == CalculationReport.LineType.SUBHEADER) {
                    currentSection = new BVDetail(normalizeDetailType(detail.type), null);
                    currentSection.details = new ArrayList<>();
                    bvDetails.add(currentSection);
                    activeGroup = null;
                    previousTotal = null;
                    continue;
                }

                if (detail.type.isBlank()) {
                    if (detail.calculation.isBlank() && detail.result.isBlank()) {
                        continue;
                    }
                    BigDecimal total = parseBVTotal(detail.result);
                    if ((currentSection != null) && currentSection.details.isEmpty() && (currentSection.calculation == null)) {
                        currentSection.calculation = detail.calculation.isBlank() ? null : detail.calculation;
                        if (total != null) {
                            currentSection.total = total;
                            currentSection.delta = total.subtract((previousTotal == null) ? BigDecimal.ZERO : previousTotal);
                            previousTotal = total;
                        }
                    } else {
                        BVDetail unlabeledDetail = new BVDetail(null,
                              detail.calculation.isBlank() ? null : detail.calculation);
                        if (total != null) {
                            unlabeledDetail.total = total;
                            unlabeledDetail.delta = total.subtract((previousTotal == null) ? BigDecimal.ZERO : previousTotal);
                            previousTotal = total;
                        }
                        addBVDetail(bvDetails, currentSection, unlabeledDetail);
                    }
                    continue;
                }

                String type = normalizeDetailType(detail.type);
                if (isBVGroup(calculationDetails, index)) {
                    activeGroup = new BVDetail(type, null);
                    activeGroup.details = new ArrayList<>();
                    groupStartingTotals.put(activeGroup,
                          (previousTotal == null) ? BigDecimal.ZERO : previousTotal);
                    addBVDetail(bvDetails, currentSection, activeGroup);
                    continue;
                }

                BVDetail bvDetail = new BVDetail(type, detail.calculation.isBlank() ? null : detail.calculation);
                BigDecimal total = parseBVTotal(detail.result);
                if (total != null) {
                    bvDetail.total = total;
                    bvDetail.delta = total.subtract((previousTotal == null) ? BigDecimal.ZERO : previousTotal);
                    previousTotal = total;
                }

                if ((activeGroup != null) &&
                      (detail.type.stripLeading().startsWith("-") ||
                            (activeGroup.details.isEmpty() && detail.result.isBlank()))) {
                    activeGroup.details.add(bvDetail);
                    continue;
                }

                activeGroup = null;
                addBVDetail(bvDetails, currentSection, bvDetail);
            }
            groupStartingTotals.forEach(UnitData::finalizeBVGroup);
            return bvDetails;
        }

        private static void finalizeBVGroup(BVDetail group, BigDecimal startingTotal) {
            BigDecimal total = findLastBVTotal(group);
            if (total != null) {
                group.total = total;
                group.delta = total.subtract(startingTotal);
            }
        }

        private static @Nullable BigDecimal findLastBVTotal(BVDetail detail) {
            if ((detail.details != null) && !detail.details.isEmpty()) {
                for (int index = detail.details.size() - 1; index >= 0; index--) {
                    BigDecimal childTotal = findLastBVTotal(detail.details.get(index));
                    if (childTotal != null) {
                        return childTotal;
                    }
                }
            }
            return detail.total;
        }

        private static boolean isBVGroup(List<CalculationDetail> calculationDetails, int groupIndex) {
            CalculationDetail group = calculationDetails.get(groupIndex);
            if (!group.calculation.isBlank() || !group.result.isBlank()) {
                return false;
            }
            for (int index = groupIndex + 1; index < calculationDetails.size(); index++) {
                CalculationDetail candidate = calculationDetails.get(index);
                if (candidate.lineType == CalculationReport.LineType.SUBHEADER ||
                      (candidate.type.isBlank() && candidate.calculation.isBlank() && candidate.result.isBlank()) ||
                      (!candidate.type.isBlank() && candidate.calculation.isBlank() && candidate.result.isBlank())) {
                    return false;
                }
                if (candidate.type.stripLeading().startsWith("-")) {
                    return true;
                }
                if (!candidate.result.isBlank()) {
                    return false;
                }
            }
            return false;
        }

        private static void addBVDetail(List<BVDetail> bvDetails, @Nullable BVDetail currentSection,
                                        BVDetail detail) {
            if (currentSection != null) {
                currentSection.details.add(detail);
            } else {
                bvDetails.add(detail);
            }
        }

        private static CostDetails formatCostDetails(List<CalculationDetail> calculationDetails) {
            CostDetails costDetails = new CostDetails();
            BigDecimal subtotal = BigDecimal.ZERO;
            for (CalculationDetail detail : calculationDetails) {
                if ((detail.lineType == CalculationReport.LineType.HEADER) || detail.type.isBlank()) {
                    continue;
                }
                if (detail.lineType == CalculationReport.LineType.RESULT_LINE) {
                    costDetails.total = parseNumber(detail.result);
                    continue;
                }

                CostDetail costDetail = new CostDetail(normalizeDetailType(detail.type),
                      detail.calculation.isBlank() ? null : detail.calculation);
                String result = detail.result.trim();
                if (result.startsWith("x ")) {
                    costDetail.factor = parseNumber(result.substring(2));
                    if (costDetail.factor != null) {
                        subtotal = subtotal.multiply(costDetail.factor);
                        costDetail.subtotal = normalizeNumber(subtotal);
                    }
                } else {
                    costDetail.amount = result.equals("N/A") ? BigDecimal.ZERO : parseNumber(result);
                    if (costDetail.amount != null) {
                        if (detail.informational) {
                            costDetail.informational = true;
                        } else {
                            subtotal = subtotal.add(costDetail.amount);
                            costDetail.subtotal = normalizeNumber(subtotal);
                        }
                    }
                }
                costDetails.steps.add(costDetail);
            }
            return costDetails;
        }

        private static String normalizeDetailType(String type) {
            return type.replaceFirst("^-+\\s*", "").replaceFirst(":$", "");
        }

        private static @Nullable BigDecimal parseBVTotal(String result) {
            String numericResult = result.trim();
            if (numericResult.startsWith("=")) {
                numericResult = numericResult.substring(1);
            }
            return parseNumber(numericResult);
        }

        private static @Nullable BigDecimal parseNumber(String value) {
            String numericValue = value.trim().replace(",", "");
            if (!numericValue.matches("-?\\d+(?:\\.\\d+)?")) {
                return null;
            }
            return normalizeNumber(new BigDecimal(numericValue));
        }

        private static BigDecimal normalizeNumber(BigDecimal value) {
            return value.stripTrailingZeros();
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
                bayEntry.put("n", bayNum);
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

        static String formatTechBase(Entity entity) {
            return entity.isClan() ? "Clan" : "Inner Sphere";
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

    /**
     * Parses command-line arguments, overriding the built-in configuration defaults.
     *
     * @param args the raw command-line arguments
     *
     * @return {@code true} if processing should continue, {@code false} if an invalid argument was encountered.
     *       Note: {@code --help} prints usage and exits the JVM with code 0.
     */
    private static boolean parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            String inlineValue = null;
            int eq = arg.indexOf('=');
            if (arg.startsWith("--") && (eq >= 0)) {
                inlineValue = arg.substring(eq + 1);
                arg = arg.substring(0, eq);
            }
            try {
                switch (arg) {
                    case "-h", "--help" -> {
                        printUsage();
                        System.exit(0);
                    }
                    case "-o", "--output", "--root" -> {
                        ROOT_FOLDER = inlineValue != null ? inlineValue : args[++i];
                    }
                    case "--sheets-dir" -> SHEETS_DIR = inlineValue != null ? inlineValue : args[++i];
                    case "--unit-files-dir" -> UNIT_FILES_DIR = inlineValue != null ? inlineValue : args[++i];
                    case "--typeface" -> TYPEFACE = inlineValue != null ? inlineValue : args[++i];
                    case "--skip-svg" -> SKIP_SVG = parseBool(inlineValue);
                    case "--skip-units" -> SKIP_UNITS = parseBool(inlineValue);
                    case "--skip-equipment" -> SKIP_EQUIPMENT = parseBool(inlineValue);
                    case "--skip-unit-files" -> SKIP_UNIT_FILES = parseBool(inlineValue);
                    case "--save-unit-files" -> SKIP_UNIT_FILES = !parseBool(inlineValue);
                    case "--save-calculations" -> SKIP_DETAILED_CALCULATIONS = !parseBool(inlineValue);
                    case "--calculations-as-text" -> EXPORT_CALCULATIONS_AS_TEXT = parseBool(inlineValue);
                    case "--units", "--unit" -> {
                        unitOverrideRequested = true;
                        if (inlineValue != null) {
                            collectUnitFiles(inlineValue);
                        }
                        // Consume all following non-option tokens as file/directory paths.
                        while ((i + 1 < args.length) && !args[i + 1].startsWith("-")) {
                            collectUnitFiles(args[++i]);
                        }
                    }
                    default -> {
                        logger.error("Unknown argument: {}", arg);
                        printUsage();
                        return false;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.error("Missing value for argument {}", arg);
                printUsage();
                return false;
            } catch (IllegalArgumentException e) {
                logger.error("Invalid value for argument {}: {}", arg, e.getMessage());
                printUsage();
                return false;
            }
        }
        return true;
    }

    private static boolean parseBool(String value) {
        if (value == null) {
            return true; // bare flag means "on"
        }
        return switch (value.toLowerCase(Locale.ROOT)) {
            case "true", "1", "yes", "on" -> true;
            case "false", "0", "no", "off" -> false;
            default -> throw new IllegalArgumentException("expected a boolean, got '" + value + "'");
        };
    }

    /**
     * Adds the given path to {@link #UNIT_FILE_OVERRIDES}. If the path is a directory it is scanned recursively for
     * {@code .blk} and {@code .mtf} files.
     */
    private static void collectUnitFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            logger.warn("Unit path does not exist, skipping: {}", path);
            return;
        }
        if (file.isDirectory()) {
            try (var walk = Files.walk(file.toPath())) {
                walk.filter(Files::isRegularFile)
                      .map(Path::toFile)
                      .filter(SVGMassPrinter::isUnitFile)
                      .forEach(UNIT_FILE_OVERRIDES::add);
            } catch (IOException e) {
                logger.warn("Failed to scan directory {}: {}", path, e.getMessage());
            }
        } else if (isUnitFile(file)) {
            UNIT_FILE_OVERRIDES.add(file);
        } else {
            logger.warn("Not a .blk/.mtf unit file, skipping: {}", path);
        }
    }

    private static boolean isUnitFile(File file) {
        String name = file.getName().toLowerCase(Locale.ROOT);
        return name.endsWith(".blk") || name.endsWith(".mtf");
    }

    private static MekSummary[] loadSummariesFromOverrides() {
        List<MekSummary> summaries = new ArrayList<>();
        for (File file : UNIT_FILE_OVERRIDES) {
            MekSummary summary = MekSummaryCache.getSummaryFromFile(file);
            if (summary == null) {
                logger.warn("Failed to load unit from {}", file.getPath());
                continue;
            }
            summaries.add(summary);
        }
        return summaries.toArray(new MekSummary[0]);
    }

    /**
     * Renders a single-line progress bar to standard out, updated at most once per whole percentage point so
     * parallel worker threads don't flood the console.
     *
     * @param done                 the number of units processed so far
     * @param total                the total number of units
     * @param startMillis          the wall-clock time (ms) when processing began, for elapsed/ETA calculation
     * @param lastReportedPercent  shared counter tracking the last percentage that was printed
     */
    private static void reportProgress(int done, int total, long startMillis, AtomicInteger lastReportedPercent) {
        if (total <= 0) {
            return;
        }
        int percent = (int) ((done * 100L) / total);
        int previous;
        do {
            previous = lastReportedPercent.get();
            if (percent <= previous) {
                // Another thread already reported this percentage (or higher); nothing new to show.
                return;
            }
        } while (!lastReportedPercent.compareAndSet(previous, percent));

        final int barWidth = 40;
        int filled = (percent * barWidth) / 100;
        String bar = "=".repeat(filled) + " ".repeat(barWidth - filled);
        long elapsed = System.currentTimeMillis() - startMillis;
        long eta = (done > 0) ? (elapsed * (total - (long) done)) / done : 0;
        System.out.printf("\r[%s] %3d%% (%d/%d) elapsed %s ETA %s   ",
              bar, percent, done, total, formatDuration(elapsed), formatDuration(eta));
        System.out.flush();
        if (done >= total) {
            System.out.println();
        }
    }

    private static String formatDuration(long millis) {
        long totalSeconds = millis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    private static void printUsage() {
        String usage = """
              Usage: SVGMassPrinter [options]

              Options:
                -o, --output, --root <dir>    Output root folder (default: %s)
                --sheets-dir <name>           Sub-folder for generated SVG sheets (default: %s)
                --unit-files-dir <name>       Sub-folder for re-saved BLK/MTF files (default: %s)
                --typeface <name>             Record sheet typeface (default: %s)
                --skip-svg[=bool]             Skip SVG generation (default: %s)
                --skip-units[=bool]           Skip unit generation (default: %s)
                --skip-equipment[=bool]       Skip equipment generation (default: %s)
                --skip-unit-files[=bool]      Skip BLK/MTF re-save (default: %s)
                --save-unit-files[=bool]      Enable BLK/MTF re-save (inverse of --skip-unit-files)
                --save-calculations[=bool]    Export calculation details (default: %s)
                --calculations-as-text[=bool] Export calculation details as .txt rather than structured .json (default: %s)
                --units <file|dir> [...]      Export only these .blk/.mtf files (or all such files in a
                                              directory, scanned recursively) instead of the whole unit cache.
                                              May be repeated; accepts multiple paths.
                -h, --help                    Show this help and exit

              Boolean flags accept true/false/1/0/yes/no/on/off; a bare flag (e.g. --skip-svg) means true.
              """.formatted(ROOT_FOLDER, SHEETS_DIR, UNIT_FILES_DIR, TYPEFACE,
              SKIP_SVG, SKIP_UNITS, SKIP_EQUIPMENT, SKIP_UNIT_FILES,
              !SKIP_DETAILED_CALCULATIONS, EXPORT_CALCULATIONS_AS_TEXT);
        System.out.println(usage);
    }

    public static void main(String[] args) {
        if (!parseArgs(args)) {
            System.exit(1);
        }
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
        mapper.getFactory().configure(StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN.mappedFeature(), true);
        long timestamp = System.currentTimeMillis();
        Map<String, Entity> uniqueUnitTypes = new ConcurrentHashMap<>();

        RecordSheetOptions recordSheetOptions = getRecordSheetOptions();

        MekSummary[] meks;
        if (!unitOverrideRequested) {
            MekSummaryCache cache = MekSummaryCache.getInstance(true);
            meks = cache.getAllMeks();
            logger.info("Processing {} meks from the unit cache...", meks.length);
        } else {
            if (UNIT_FILE_OVERRIDES.isEmpty()) {
                logger.error("--units was specified but no valid .blk/.mtf files were found.");
                System.exit(1);
            }
            meks = loadSummariesFromOverrides();
            if (meks.length == 0) {
                logger.error("No valid units could be loaded from the supplied unit files.");
                System.exit(1);
            }
            logger.info("Processing {} meks from {} supplied unit file(s)...", meks.length,
                  UNIT_FILE_OVERRIDES.size());
        }

        PageFormat pf = new PageFormat();
        PaperSize paperDef = recordSheetOptions.getPaperSize();

        final AtomicInteger processedCounter = new AtomicInteger(0);
        final AtomicInteger unitFilesSavedCounter = new AtomicInteger(0);
        final AtomicInteger progressCounter = new AtomicInteger(0);
        final AtomicInteger lastReportedPercent = new AtomicInteger(-1);
        final int totalUnits = meks.length;
        final long progressStart = System.currentTimeMillis();
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
              reportProgress(progressCounter.incrementAndGet(), totalUnits, progressStart, lastReportedPercent);
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

              unitData.su = isSmallUnit ? 1 : 0;

              if (!uniqueUnitTypes.containsKey(unitData.type)) {
                  uniqueUnitTypes.put(unitData.type, entity);
              }
              processedCounter.incrementAndGet();
              return unitData;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());


        if (EXPORT_CALCULATION_DETAILS_TO_FILES && !SKIP_DETAILED_CALCULATIONS) {
            exportCalculationDetails(mapper, unitDataList);
        }

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
                    entry.put("key", key);
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
                    entry.put("key", key);
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

    private static void exportCalculationDetails(ObjectMapper mapper, List<UnitData> unitDataList) {
        exportCalculationDetails(mapper, unitDataList, Path.of(ROOT_FOLDER), EXPORT_CALCULATIONS_AS_TEXT);
    }

    static void exportCalculationDetails(ObjectMapper mapper, List<UnitData> unitDataList, Path rootDirectory,
          boolean calculationsAsText) {
        Path costDirectory = rootDirectory.resolve("cost");
        Path bvDirectory = rootDirectory.resolve("bv");
        Path weightDirectory = rootDirectory.resolve("weight");
        Path techLevelDirectory = rootDirectory.resolve("tech-level");
        Path asConversionDirectory = rootDirectory.resolve("alpha-strike");
        try {
            Files.createDirectories(costDirectory);
            Files.createDirectories(bvDirectory);
            Files.createDirectories(weightDirectory);
            Files.createDirectories(techLevelDirectory);
            Files.createDirectories(asConversionDirectory);
        } catch (IOException e) {
            logger.error("Failed to create calculation detail export folders.");
            return;
        }
        for (UnitData unitData : unitDataList) {
            try {
                if (calculationsAsText) {
                    writeTextReport(costDirectory, unitData.name, unitData.costDetailText);
                    writeTextReport(bvDirectory, unitData.name, unitData.bvDetailText);
                } else {
                    mapper.writeValue(costDirectory.resolve(unitData.name + ".json").toFile(), unitData.costDetail);
                    mapper.writeValue(bvDirectory.resolve(unitData.name + ".json").toFile(), unitData.bvDetails);
                }
                writeTextReport(weightDirectory, unitData.name, unitData.weightBreakdown);
                writeTextReport(techLevelDirectory, unitData.name, unitData.techLevelBreakdown);
                writeTextReport(asConversionDirectory, unitData.name, unitData.asConversionReport);
                unitData.bvDetails = null;
                unitData.costDetail = null;
                unitData.bvDetailText = null;
                unitData.costDetailText = null;
                unitData.weightBreakdown = null;
                unitData.techLevelBreakdown = null;
                unitData.asConversionReport = null;
            } catch (IOException e) {
                logger.error("Failed to export calculation details for {}: {}", unitData.name, e.getMessage());
            }
        }
    }

    private static void writeTextReport(Path directory, String unitName, @Nullable String report) throws IOException {
        if ((report != null) && !report.isBlank()) {
            Files.writeString(directory.resolve(unitName + ".txt"), report);
        }
    }

    static double calculateLoadoutTonnage(Entity entity) {
        TestEntity verifier = UnitUtil.getEntityVerifier(entity);
        if (verifier == null) {
            // throw new IllegalArgumentException("No weight verifier for entity type " + entity.getClass().getName());
            return 0;
        }
        return verifier.calculateWeight() + UnitUtil.getUnallocatedAmmoTonnage(entity);
    }

    static String createWeightBreakdown(Entity entity) {
        if (entity instanceof ConvInfantry infantry) {
            TextCalculationReport report = new TextCalculationReport();
            TestInfantry.getWeightExact(infantry, report);
            return report.toString();
        }
        TestEntity verifier = UnitUtil.getEntityVerifier(entity);
        return (verifier == null) ? "" : verifier.printEntity().toString();
    }

    static String createTechLevelBreakdown(Entity entity) {
        return CompositeTechLevelReport.toPlainText(entity, Faction.NONE, entity.getYear(), true);
    }

    static @Nullable AlphaStrikeConversion convertToAlphaStrike(Entity entity) {
        if (!ASConverter.canConvert(entity)) {
            return null;
        }
        ExportCalculationReport report = new ExportCalculationReport();
        AlphaStrikeElement element = ASConverter.convert(entity, report);
        return (element == null) ? null : new AlphaStrikeConversion(element, report.getText());
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
