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
import java.util.List;
import java.util.Locale;

import megamek.client.ui.swing.util.FluffImageHelper;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.GunEmplacement;
import megamek.common.MekSummary;
import megamek.common.MekSummaryCache;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;

public class CGLMassPrinter {
    private static final MMLogger logger = MMLogger.create(CGLMassPrinter.class);

    public static void main(String[] args) {
        File sheetsDir = new File("sheets");

        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            sheetsDir.mkdirs();
            logger.error("Error: sheets directory does not exist, creating it.");
        }

        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        for (MekSummary mekSummary : cache.getAllMeks()) {
            logger.info("Looking at {}", mekSummary.getName());

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

            if (entity != null && !(entity instanceof GunEmplacement)) {
                File sheetPath = new File("sheets", FluffImageHelper.getFluffPath(entity));

                if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                    logger.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }

                File file = normalizePath(sheetPath, mekSummary);

                try {
                    List<Entity> units = printableListOfUnits(entity);
                    UnitPrintManager.exportUnits(units, file, true);
                    logger.info("Printed: {}", file);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                }

                // Added to keep memory usage from ballooning during processing.
                System.gc();
            }
        }

        logger.info("Done.");
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
        String fileName = String.format("%s_%s_%s.pdf",
                unit.getMulId(),
                sanitize(unit.getChassis()),
                sanitize(unit.getModel()));
        return new File(path, fileName);
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }

    private CGLMassPrinter() {
        throw new IllegalStateException();
    }
}
