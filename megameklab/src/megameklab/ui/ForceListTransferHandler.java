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
package megameklab.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import jakarta.annotation.Nonnull;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.util.MULManager;

class ForceListTransferHandler extends TransferHandler {
    private final static MMLogger LOGGER = MMLogger.create(ForceListTransferHandler.class);

    private final ForceBuildUI forceBuildUI;
    private final DataFlavor localObjectFlavor = new DataFlavor(Integer.class, "Integer Row Index");
    private int[] rows = null;

    public ForceListTransferHandler(ForceBuildUI forceBuildUI) {this.forceBuildUI = forceBuildUI;}

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTable table = (JTable) c;
        rows = table.getSelectedRows();
        if (rows == null || rows.length == 0) {
            return null;
        }
        // Only allow single row drag for reordering
        if (rows.length > 1) {
            return null;
        }
        return new RowTransferable(rows[0]);
    }

    @Override
    public boolean canImport(TransferSupport support) {
        // Allow drop if it's our internal row move OR if it's a file list
        if (support.isDataFlavorSupported(localObjectFlavor)) {
            if (!support.isDrop()) {
                return false;
            }
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            // Do not allow dropping onto the source row
            if (rows != null && dl.getRow() == rows[0]) {
                return false;
            }
            // Ensure insertion line is SHOWN for row moves
            support.setShowDropLocation(true);
            return true;
        }
        if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            // We check if is a file, and we allow without showing the insertion line
            support.setShowDropLocation(false);
            return true;
        }
        // Reject other flavors
        support.setShowDropLocation(false);
        // Reject other flavors
        return false;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    public boolean importData(TransferSupport support) {
        // Handle internal row move first
        if (support.isDataFlavorSupported(localObjectFlavor)) {
            // Check if the import is valid
            if (!canImport(support)) {
                return false;
            }
            JTable target = (JTable) support.getComponent();
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            int index = dl.getRow();
            int max = forceBuildUI.getTableModel().getRowCount();
            if (index < 0 || index > max) {
                index = max;
            }
            try {
                int rowFrom = (Integer) support.getTransferable().getTransferData(localObjectFlavor);
                if (rowFrom != -1 && rowFrom != index) {
                    // Adjust drop index if dragging downwards
                    int dropIndex = (rowFrom < index) ? index - 1 : index;
                    // Reorder the underlying forceList
                    Entity movedEntity = forceBuildUI.getAllEntities().remove(rowFrom);
                    forceBuildUI.getAllEntities().add(dropIndex, movedEntity);
                    forceBuildUI.rebuildTable();
                    // Select the moved row after the update
                    target.setRowSelectionInterval(dropIndex, dropIndex);
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException e) {
                LOGGER.error("Error importing row move data: ", e);
            }
            return false; // Row move failed or was invalid
        }

        // Handle file list drop
        if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            try {
                @SuppressWarnings("unchecked") List<File> files = (List<File>) support.getTransferable()
                      .getTransferData(DataFlavor.javaFileListFlavor);
                boolean processed = false;
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".mul")) {
                        // Process the MUL file using MULManager
                        MULManager.loadForceFromMUL(file);
                        processed = true;
                    }
                }
                return processed;
            } catch (UnsupportedFlavorException | IOException e) {
                LOGGER.error("Error importing dropped files: ", e);
            }
            return false; // File import failed
        }

        return false;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        // Only cleanup if it was a row move
        if (data instanceof RowTransferable) {
            cleanup(action == MOVE);
        }
    }

    private void cleanup(boolean move) {
        rows = null;
    }

    // Helper Transferable class for row moves
    class RowTransferable implements Transferable {
        private final Integer row;

        public RowTransferable(Integer row) {
            this.row = row;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { localObjectFlavor };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return localObjectFlavor.equals(flavor);
        }

        @Override
        @Nonnull
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return row;
        }
    }
}
