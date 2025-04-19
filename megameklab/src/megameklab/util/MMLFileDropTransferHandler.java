/*
 * Copyright (c) 2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
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
