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

import megamek.client.ui.baseComponents.AbstractDialog;
import megameklab.MegaMekLab;

/**
 * This is the base class for dialogs in MML, inherited from MM. This class handles setting the UI, managing the X
 * button, managing the escape key, and saving the dialog preferences.
 * <p>
 * Inheriting classes must call initialize() in their constructors and override createCenterPane().
 */
public abstract class AbstractMMLDialog extends AbstractDialog {
    //region Constructors

    /**
     * This creates a non-modal AbstractMMLDialog using the default MML dialog resource bundle. This is the normal
     * constructor to use for an AbstractMMLDialog.
     */
    protected AbstractMMLDialog(final JFrame frame, final String name, final String title) {
        this(frame, false, name, title);
    }

    /**
     * This creates an AbstractMMLDialog using the default MML resource bundle. It allows one to create modal dialogs.
     */
    protected AbstractMMLDialog(final JFrame frame, final boolean modal, final String name, final String title) {
        this(frame, modal, ResourceBundle.getBundle("megameklab.resources.Dialogs"), name, title);
    }

    /**
     * This creates an AbstractMMLDialog using the specified resource bundle. This is not recommended by default.
     */
    protected AbstractMMLDialog(final JFrame frame, final boolean modal, final ResourceBundle resources,
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
