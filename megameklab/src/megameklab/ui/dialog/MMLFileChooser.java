/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.dialog;

import megameklab.util.CConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is a custom JFileChooser that preserves the last used directory as well as window size and position. All
 * those values are saved (see CConfig) when the file chooser is closed.
 */
public class MMLFileChooser extends JFileChooser {

    public MMLFileChooser() {
        super(CConfig.getParam(CConfig.LAST_DIRECTORY));
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
                    CConfig.setParam(CConfig.LAST_DIRECTORY, getCurrentDirectory().toString());
                }
                super.windowClosed(e);
            }
        });
        return dialog;
    }
}