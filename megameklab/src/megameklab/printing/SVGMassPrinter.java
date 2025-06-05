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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import megamek.client.ratgenerator.ModelRecord;
import megamek.client.ui.util.FluffImageHelper;
import megamek.codeUtilities.StringUtility;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.GunEmplacement;
import megamek.common.MekSummary;
import megamek.common.MekSummaryCache;
import megamek.common.UnitRole;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileWriter;
import java.io.IOException;

public class SVGMassPrinter {
    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final String SHEETS_DIR = "sheets";
    private static final String VERSION_FILE = "version.json";
    private static final String UNIT_FILE = "units.json";
    private static final String ROOT_FOLDER = "svgexport";

    public static class UnitData {
        public int mulId;
        public String chassis;
        public String model;
        public int year;
        public int wc;
        public double tons;
        public int bv;
        public int pv;
        public long cost;
        public int level;
        public String type;
        public String sub;
        public String source;
        public String role;
        public boolean clan;
        public int armor;
        public int internal;
        public int heat;
        public int heatDiss;
        public int walk;
        public int run;
        public int jump;
        public String sheet;

        public UnitData() {
        }

        public UnitData(MekSummary summary) {
            this.mulId = summary.getMulId();
            this.chassis = summary.getFullChassis();
            this.model = summary.getModel();
            this.year = summary.getYear();
            this.wc = summary.getWeightClass();
            this.tons = summary.getTons();
            this.bv = summary.getBV();
            this.pv = summary.getPointValue();
            this.cost = summary.getCost();
            this.type = summary.getUnitType();
            this.sub = summary.getUnitSubType();
            this.clan = summary.isClan();
            this.walk = summary.getWalkMp();
            this.run = summary.getRunMp();
            this.jump = summary.getJumpMp();
        }

        public UnitData(Entity entity) {
            this.mulId = entity.getMulId();
            this.chassis = entity.getFullChassis();
            this.model = entity.getModel();
            this.year = entity.getYear();
            this.wc = entity.getWeightClass();
            this.tons = entity.getWeight();
            this.bv = entity.getBvCalculator().calculateBV(false,true);
            this.cost = Math.round(entity.getCost(false));
            this.level = entity.getStaticTechLevel().ordinal();
            this.type = Entity.getEntityMajorTypeName(entity.getEntityType());
            this.sub = Entity.getEntityTypeName(entity.getEntityType());
            this.source = entity.getSource();
            UnitRole role = entity.getRole();
            if (role != UnitRole.UNDETERMINED) {
                this.role = role.toString();
            } else {
                this.role = "None";
            }
            this.clan = entity.isClan();
            this.armor = entity.getTotalOArmor();
            this.internal = entity.getTotalInternal();
            this.heat = UnitUtil.getTotalHeatGeneration(entity);
            this.heatDiss = entity.getHeatCapacity();
            this.walk = entity.getWalkMP();
            this.run = entity.getRunMP();
            this.jump = entity.getJumpMP();
        }
    }

    public static void main(String[] args) {
        logger.info("Starting SVG Mass Printer...");
        final String rootPath = ROOT_FOLDER + File.separator + SHEETS_DIR;
        File sheetsDir = new File(rootPath);

        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            sheetsDir.mkdirs();
            logger.error("Error: sheets directory does not exist, creating it.");
        }

        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);

        int i = 0;
        int processedCount = 0;
        MekSummary[] meks = cache.getAllMeks();
        logger.info("Processing {} meks...", meks.length);
        long timestamp = System.currentTimeMillis();
        
        try (FileWriter versionWriter = new FileWriter(ROOT_FOLDER + File.separator + VERSION_FILE)) {
            versionWriter.write("{\"version\":"+String.valueOf(timestamp)+"}");
            logger.info("Version file written: {}", timestamp);
        } catch (IOException e) {
            logger.error("Failed to write version file: {}", e.getMessage());
        }
        
        try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FILE)) {
            jsonWriter.write("{\"version\":"+ timestamp +",\n");
            jsonWriter.write("\"units\":[\n");
            boolean firstUnit = true;
            for (MekSummary mekSummary : meks) {
                // if (mekSummary.getUnitType() != "Mek") continue; // Skip non-Mek units
                i++;
                logger.info("{}: {}", i, mekSummary.getName());

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
                    logger.info("Skipping: {}", mekSummary.getName());
                    System.gc();
                    continue;
                }
                File sheetPath = new File(sheetsDir.getPath(), FluffImageHelper.getFluffPath(entity));

                if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                    logger.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }

                File file = normalizePath(sheetPath, mekSummary);

                try {
                    // List<Entity> units = printableListOfUnits(entity);
                    // UnitPrintManager.exportUnits(units, file, true);
                    logger.info("Printed: {}", file);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                    System.exit(1);
                }

                UnitData unitData = new UnitData(entity);
                // Set additional fields
                unitData.sheet = file.getPath().replace(ROOT_FOLDER, "").replace(File.separator, "/");
                unitData.pv = mekSummary.getPointValue();

                String jsonLine = mapper.writeValueAsString(unitData);
                if (!firstUnit) {
                    jsonWriter.write(",\n");
                }
                jsonWriter.write(jsonLine);

                firstUnit = false;
                processedCount++;
                System.gc();
            }
            jsonWriter.write("\n]}");
        } catch (IOException e) {
            logger.error("Failed to write JSON Lines file: {}", e.getMessage());
        }

        logger.info("Done. Processed {} units.", processedCount);
        System.exit(0);
    }

    private static List<Entity> printableListOfUnits(Entity entity) {
        if (entity.isBattleArmor() || entity.isProtoMek()) {
            return List.of(entity, entity, entity, entity, entity);
        } else {
            return List.of(entity);
        }
    }

    private static File normalizePath(File path, MekSummary unit) {
        String fileName = String.format("%s_%s_%s.svg",
                unit.getMulId(),
                sanitize(unit.getChassis()),
                sanitize(unit.getModel()));
        return new File(path, fileName);
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }

    private SVGMassPrinter() {
        throw new IllegalStateException();
    }
}
