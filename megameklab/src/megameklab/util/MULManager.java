/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMek is distributed in the hope that it will be useful,
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
 */
package megameklab.util;

import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;

import megamek.common.Entity;
import megamek.common.MULParser;
import megamek.logging.MMLogger;
import megameklab.ui.ForceBuildUI;
import megameklab.ui.MulDndBehaviour;

public class MULManager {
    private static final MMLogger logger = MMLogger.create(MULManager.class);
    
    public static void processMULFile(File file, JFrame owner) {
        int behaviourValue = CConfig.getIntParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR);
        MulDndBehaviour[] allValues = MulDndBehaviour.values();
        MulDndBehaviour selectedBehaviour = MulDndBehaviour.PRINT;
        if (behaviourValue >= 0 && behaviourValue < allValues.length) {
            selectedBehaviour = allValues[behaviourValue];
        }
        switch (selectedBehaviour) {
            case PRINT:
                // Print
                UnitPrintManager.printMUL(owner, false, file);
                break;
            case EXPORT:
                // Export to PDF
                UnitPrintManager.printMUL(owner, true, file);
                break;
            case LOAD_FORCE:
                // Load into Force Builder
                loadForceFromMUL(file);
                break;
        }
    }

    public static void loadForceFromMUL(File file) {
        try {
            ForceBuildUI forceBuildUI = ForceBuildUI.getInstance();
            Vector<Entity> loadedUnits = new MULParser(file, forceBuildUI.getGame().getOptions()).getEntities();
            loadedUnits.trimToSize();
            forceBuildUI.clear();
            for (Entity entity : loadedUnits) {
                forceBuildUI.addEntity(entity);
            }
            forceBuildUI.setVisible(true);
        } catch (Exception e) {
            logger.error("Error loading MUL file", e);
        }
    }
}
