/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import java.io.File;
import java.util.Vector;
import javax.swing.JFrame;

import megamek.common.annotations.Nullable;
import megamek.common.loaders.MULParser;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.ForceBuildUI;
import megameklab.ui.MulDndBehaviour;

public class MULManager {
    private static final MMLogger logger = MMLogger.create(MULManager.class);

    public static void processMULFile(File file, @Nullable JFrame owner) {
        int behaviourValue = CConfig.getIntParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR);
        MulDndBehaviour[] allValues = MulDndBehaviour.values();
        MulDndBehaviour selectedBehaviour = MulDndBehaviour.PRINT;
        if (behaviourValue >= 0 && behaviourValue < allValues.length) {
            selectedBehaviour = allValues[behaviourValue];
        }
        if (selectedBehaviour == MulDndBehaviour.LOAD_FORCE) {
            loadForceFromMUL(file);
            return;
        }
        JFrame dummyOwner = null;
        if (owner == null) {
            dummyOwner = new JFrame("MegaMekLab - Print Queue");
            dummyOwner.setUndecorated(true);
            dummyOwner.setSize(0, 0);
            dummyOwner.setLocationRelativeTo(null);
            dummyOwner.setVisible(true);
            owner = dummyOwner;
        }
        try {
            UnitPrintManager.printMUL(owner, selectedBehaviour == MulDndBehaviour.EXPORT, file);
        } finally {
            if (dummyOwner != null) {
                dummyOwner.dispose();
            }
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
