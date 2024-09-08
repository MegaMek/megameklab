/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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

    public static void main(String[] args) throws InterruptedException {
        File sheetsDir = new File("sheets");

        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            sheetsDir.mkdirs();
            logger.error("Error: sheets directory does not exist, creating it.");
        }

        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        for (MekSummary ms : cache.getAllMeks()) {
            String message = String.format("Looking at %s", ms.getName());
            logger.info(message);

            if (!ms.isCanon()) {
                continue;
            }

            /*
             * if (!ms.isProtoMek() && !ms.isCombatVehicle()) {
             * continue;
             * }
             *
             * // 1 - uncomment this block and cycle all the start characters A-Z (only
             * // uppercase)
             * if (!ms.getName().toUpperCase().startsWith("C")) {
             * continue;
             * }
             *
             * // 2 - uncomment this block, comment the above block, run once more
             * if (ms.getName().toUpperCase().charAt(0) <= 'Z' &&
             * ms.getName().toUpperCase().charAt(0) >= 'A') {
             * continue;
             * }
             *
             */
            Entity entity = ms.loadEntity();

            if (entity != null && !(entity instanceof GunEmplacement)) {
                File sheetPath = new File("sheets", FluffImageHelper.getFluffPath(entity));

                if (!sheetPath.exists()) {
                    if (!sheetPath.mkdirs()) {
                        message = String.format("Couldn't create folder %s", sheetPath);
                        logger.error(message);
                        System.exit(1);
                    }
                }

                File file = new File(sheetPath, ms.getMulId() + "_"
                        + sanitize(ms.getChassis()) + "_"
                        + sanitize(ms.getModel()) + ".pdf");

                try {
                    if (entity.isBattleArmor() || entity.isProtoMek()) {
                        UnitPrintManager.exportUnits(List.of(entity, entity, entity, entity, entity), file, false);
                    } else {
                        UnitPrintManager.exportUnits(List.of(entity), file, true);
                    }

                    message = String.format("Printed: %s", file);
                    logger.info(message);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                }
            }
        }

        logger.info("Done.");
        System.exit(0);
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }

    private CGLMassPrinter() {
        throw new IllegalStateException();
    }
}
