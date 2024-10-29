/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
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

import megamek.logging.MMLogger;
import megameklab.ui.MenuBarOwner;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

public class MMLFileDropTarget extends DropTarget {
    private static final MMLogger logger = MMLogger.create(MMLFileDropTarget.class);

    private final MenuBarOwner owner;

    public MMLFileDropTarget(MenuBarOwner owner) {
        this.owner = owner;
    }

    @Override
    public synchronized void drop(DropTargetDropEvent event) {
        try {
            event.acceptDrop(DnDConstants.ACTION_COPY);
            var files = (List<File>) event.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            if (files.size() != 1) {
                event.dropComplete(false);
                return;
            }

            var file = files.get(0);
            var name = file.getName();

            if (name.endsWith(".mtf") || name.endsWith(".blk")) {
                owner.getMMLMenuBar().loadFile(file);
                event.dropComplete(true);
            } else if (name.endsWith(".mul")) {
                UnitPrintManager.printMUL(owner.getFrame(), CConfig.getBooleanParam(CConfig.MISC_MUL_DND_BEHAVIOUR), file);
                event.dropComplete(true);
            } else {
                event.dropComplete(false);
            }


        } catch (Exception e) {
            event.dropComplete(false);
            logger.warn(e);
        }
    }
}
