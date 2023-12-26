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
package megameklab.ui;

import megamek.common.Entity;
import megamek.common.annotations.Nullable;
import megameklab.ui.util.AppCloser;

import javax.swing.*;

/**
 * This interface must be implemented by classes that display the {@link MenuBar}.
 */
public interface MenuBarOwner extends AppCloser {

    /** @return A frame of this menubar owner to use as a parent for dialogs. */
    JFrame getFrame();

    /** @return The entity currently worked on or null. */
    @Nullable Entity getEntity();

    /**
     * This method is called when an action will cause the currently edited unit to be discarded (exit MML,
     * load a new unit or switch unit type).
     * <BR><BR>
     * It should return true only when MML should continue with the action that called this method. If there is
     * any data to be lost by this menubar owner, then this method should display a safety dialog prompting
     * the user to consider saving the currently entered unit. If the user selects NO or selects YES and
     * actually saves the unit, true should be returned.
     * <BR><BR>
     * If there is no data to be lost or when the setting "Disable save prompts" is active (see Misc Settings), it
     * may directly return true. See also {@link megameklab.ui.dialog.settings.MiscSettingsPanel}.
     * <BR><BR>
     * By default, this method directly returns true.
     *
     * @return True only when the user agrees to continue or has deactivated these prompts, false otherwise
     */
    default boolean safetyPrompt() {
        return true;
    }

    /**
     * This method is called when this menubar owner should refresh all displayed content (all tabs in the
     * unit main UIs). By default, this method does nothing.
     */
    default void refreshAll() { }

    /**
     * Sets the look and feel for the application.
     *
     * @param plaf The look and feel to use for the application.
     */
    default void changeTheme(UIManager.LookAndFeelInfo plaf) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(plaf.getClassName());
                SwingUtilities.updateComponentTreeUI(getFrame());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(getFrame(),
                        "Can't change look and feel", "Invalid PLAF",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}