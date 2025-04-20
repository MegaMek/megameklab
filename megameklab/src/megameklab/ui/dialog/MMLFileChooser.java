/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import megameklab.util.CConfig;

/**
 * This is a custom JFileChooser that preserves the last used directory as well as window size and position. All those
 * values are saved (see CConfig) when the file chooser is closed.
 */
public class MMLFileChooser extends JFileChooser {

    public MMLFileChooser() {
        super(CConfig.getParam(CConfig.FILE_LAST_DIRECTORY));
    }

    @Override
    protected JDialog createDialog(Component parent) throws HeadlessException {
        final JDialog dialog = super.createDialog(parent);
        CConfig.getFileChooserPosition().ifPresent(dialog::setLocation);
        CConfig.getFileChooserSize().ifPresent(dialog::setSize);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                CConfig.writeFileChooserSettings(dialog);
                if (getCurrentDirectory().exists()) {
                    CConfig.setParam(CConfig.FILE_LAST_DIRECTORY, getCurrentDirectory().toString());
                }
                super.windowClosed(e);
            }
        });
        return dialog;
    }
}
