/*
 * Copyright (C) 2021-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.dialog;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.ui.util.RefreshListener;

/**
 * A dialog for the floating equipment database. This dialog is non-focusable so that shortcut keys of the main MML
 * window continue to work when working with this dialog. As a consequence, the text filter cannot be used and pressing
 * Enter to add equipment does not work in this dialog.
 */
public class FloatingEquipmentDatabaseDialog extends AbstractMMLDialog {

    private final AbstractEquipmentDatabaseView equipmentDatabase;

    /**
     * Constructs a dialog for the floating equipment database. An AbstractEquipmentDatabaseView that is suitable for
     * the displayed unit must be provided.
     */
    public FloatingEquipmentDatabaseDialog(JFrame frame, AbstractEquipmentDatabaseView equipmentDatabase) {
        super(frame, "FloatingEquipmentDatabaseDialog", "FloatingEquipmentDatabaseDialog.title");
        this.equipmentDatabase = equipmentDatabase;
        equipmentDatabase.setBorder(new EmptyBorder(8, 8, 8, 8));
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
