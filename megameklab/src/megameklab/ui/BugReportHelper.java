/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import java.awt.Container;
import java.awt.Window;
import javax.swing.Action;
import javax.swing.SwingUtilities;

import megamek.client.ui.BugReportMessages;
import megamek.client.ui.CopySystemDataAction;
import megamek.client.ui.Messages;
import megamek.client.ui.ShowBugReportDialogAction;
import megamek.client.ui.clientGUI.BugReportDialog;
import megamek.client.ui.widget.HazardButton;
import megamek.logging.MMLogger;
import megameklab.MMLConstants;

/** Creates the shared MegaMekLab entry points into the suite's bug report helper. */
public final class BugReportHelper {
    private static final BugReportMessages I18N = new BugReportMessages();

    /**
     * Creates the action used by the Help menu.
     *
     * @param owner the window and current-unit provider
     *
     * @return an action that opens the shared bug report helper
     */
    public static Action createDialogAction(MenuBarOwner owner) {
        return new ShowBugReportDialogAction(owner.getFrame(),
              new CopySystemDataAction(MMLConstants.PROJECT_NAME));
    }

    /**
     * Creates the conspicuous report button used on the startup screen and editor status bars.
     *
     * @param parent the component used to locate the owning window
     *
     * @return the configured button
     */
    public static HazardButton createButton(Container parent) {
        HazardButton button = new HazardButton(I18N.get("package.reportBug"));
        button.addActionListener(event -> showDialog(parent));
        return button;
    }

    /**
     * Offers the report helper directly on exception dialogs, while the failure and useful logs are still fresh.
     *
     * @param owner the currently active MegaMekLab window
     */
    public static void installOnErrorDialogs(MenuBarOwner owner) {
        MMLogger.setErrorDialogButton(new MMLogger.ErrorDialogButton(
              I18N.get("package.reportBug"), Messages.getString("Okay"),
              () -> showDialog(owner.getFrame())));
    }

    private static void showDialog(Container parent) {
        Window parentWindow = (parent instanceof Window window) ? window : SwingUtilities.getWindowAncestor(parent);
        new BugReportDialog(parentWindow,
              new CopySystemDataAction(MMLConstants.PROJECT_NAME)).show();
    }

    private BugReportHelper() {}
}
