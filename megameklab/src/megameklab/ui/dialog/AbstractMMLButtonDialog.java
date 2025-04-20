/*
 * Copyright (C) 2022-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.dialog;

import java.util.ResourceBundle;
import javax.swing.JFrame;

import megamek.client.ui.baseComponents.AbstractButtonDialog;
import megameklab.MegaMekLab;

/**
 * This is the Base Dialog for a dialog with buttons in MML. It extends Base Dialog and adds a button panel with base Ok
 * and Cancel buttons. It also includes an enum tracker for the result of the dialog.
 * <p>
 * Inheriting classes must call initialize() in their constructors and override createCenterPane()
 * <p>
 * The resources associated with this dialog need to contain at least the following keys: - "Ok.text" - text for the ok
 * button - "Ok.toolTipText" - toolTipText for the ok button - "Cancel.text" - text for the cancel button -
 * "Cancel.toolTipText" - toolTipText for the cancel button
 */
public abstract class AbstractMMLButtonDialog extends AbstractButtonDialog {
    //region Constructors

    /**
     * This creates a modal AbstractMMLButtonDialog using the default MML resource bundle. This is the normal
     * constructor to use for an AbstractMMLButtonDialog.
     *
     * @deprecated replaced by {@link #AbstractMMLButtonDialog(JFrame, boolean, String, String)}
     */
    @Deprecated(since = "0.50.06", forRemoval = true)
    protected AbstractMMLButtonDialog(final JFrame frame, final String name, final String title) {
        this(frame, true, name, title);
    }

    /**
     * This creates an AbstractMMLButtonDialog using the default MML resource bundle. It allows one to create non-modal
     * button dialogs, which is not recommended by default.
     */
    protected AbstractMMLButtonDialog(final JFrame frame, final boolean modal, final String name, final String title) {
        this(frame, modal, ResourceBundle.getBundle("megameklab.resources.Dialogs"), name, title);
    }

    /**
     * This creates an AbstractMMLButtonDialog using the specified resource bundle. This is not recommended by default.
     */
    protected AbstractMMLButtonDialog(final JFrame frame, final boolean modal, final ResourceBundle resources,
          final String name, final String title) {
        super(frame, modal, resources, name, title);
    }
    //endregion Constructors

    /**
     * This override forces the preferences for this class to be tracked in MML instead of MegaMek
     *
     * @throws Exception if there's an issue initializing the preferences. Normally this means a component has
     *                   <strong>not</strong> had its name value set.
     */
    @Override
    protected void setPreferences() throws Exception {
        setPreferences(MegaMekLab.getMMLPreferences().forClass(getClass()));
    }
}
