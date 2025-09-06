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

import java.io.File;
import java.util.List;
import java.util.Locale;

import megamek.client.ui.util.FluffImageHelper;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.GunEmplacement;
import megamek.common.loaders.MekSummary;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;

public class CGLMassPrinter {
    private static final MMLogger LOGGER = MMLogger.create(CGLMassPrinter.class);

    public static void main(String[] args) {
        File sheetsDir = new File("sheets");

        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            sheetsDir.mkdirs();
            LOGGER.error("Error: sheets directory does not exist, creating it.");
        }

        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        for (MekSummary mekSummary : cache.getAllMeks()) {
            LOGGER.info("Looking at {}", mekSummary.getName());

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
                    LOGGER.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }

                File file = normalizePath(sheetPath, mekSummary);

                try {
                    List<Entity> units = printableListOfUnits(entity);
                    UnitPrintManager.exportUnits(units, file, true);
                    LOGGER.info("Printed: {}", file);
                } catch (Exception e) {
                    LOGGER.error(e, "Printing Error");
                }

                // Added to keep memory usage from ballooning during processing.
                System.gc();
            }
        }

        LOGGER.info("Done.");
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
