/*
 * MegaMekLab
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.ui.dialog;

import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.ui.util.RefreshListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A dialog for the floating equipment database. This dialog is non-focusable so that shortcut keys
 * of the main MML window continue to work when working with this dialog. As a consequence,
 * the text filter cannot be used and pressing Enter to add equipment does not work in this dialog.
 */
public class FloatingEquipmentDatabaseDialog extends AbstractMMLDialog {

    private final AbstractEquipmentDatabaseView equipmentDatabase;

    /**
     * Constructs a dialog for the floating equipment database. An AbstractEquipmentDatabaseView
     * that is suitable for the displayed unit must be provided.
     */
    public FloatingEquipmentDatabaseDialog(JFrame frame, AbstractEquipmentDatabaseView equipmentDatabase) {
        super(frame,"FloatingEquipmentDatabaseDialog", "FloatingEquipmentDatabaseDialog.title");
        this.equipmentDatabase = equipmentDatabase;
        equipmentDatabase.setBorder(new EmptyBorder(8,8,8,8));
        equipmentDatabase.refreshTable();
        setAutoRequestFocus(false);
        setFocusableWindowState(false);
        initialize();
    }

    public void setRefresh(RefreshListener refresh) {
        equipmentDatabase.setRefresh(refresh);
    }

    public void refresh() {
        equipmentDatabase.refreshTable();
    }

    @Override
    protected Container createCenterPane() {
        return equipmentDatabase;
    }
}
