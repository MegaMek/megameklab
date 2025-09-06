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

package megameklab.util;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;
import javax.swing.TransferHandler;

import megamek.logging.MMLogger;
import megameklab.ui.MenuBarOwner;
import megameklab.ui.util.TabUtil;

public class MMLFileDropTransferHandler extends TransferHandler {
    private static final MMLogger logger = MMLogger.create(MMLFileDropTransferHandler.class);
    private final MenuBarOwner owner;

    public MMLFileDropTransferHandler(MenuBarOwner owner) {
        this.owner = owner;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            var files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            if (files.size() != 1) {
                TabUtil.loadMany(files, owner);
                return true;
            }

            var file = files.get(0);
            var name = file.getName();

            if (name.toLowerCase().endsWith(".mtf") || name.toLowerCase().endsWith(".blk")) {
                owner.getMMLMenuBar().loadFile(file);
                return true;
            } else if (name.toLowerCase().endsWith(".mul")) {
                MULManager.processMULFile(file, owner.getFrame());
                return true;
            } else {
                logger.errorDialog("Import error", "Can only open files of type .mtf, .blk, and .mul");
                return false;
            }


        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
}
