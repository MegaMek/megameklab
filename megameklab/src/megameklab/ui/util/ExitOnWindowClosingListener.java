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
package megameklab.ui.util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is a specific WindowAdapter that acts on a windowClosing event by calling the given AppCloser's exit() method
 * and exits the application if exit() returned true. Add this to any Frame that should exit the application by closing
 * its window.
 *
 * @author Simon (Juliez)
 */
public final class ExitOnWindowClosingListener extends WindowAdapter {

    private final AppCloser frame;

    /**
     * Returns a new window listener for the given frame that will react to windowClosing events by calling the frame's
     * exit() method and closing the application when exit() returns true.
     *
     * @param frame The frame (implementing AppCloser) that this window listener is for
     */
    public ExitOnWindowClosingListener(AppCloser frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent evt) {
        if (frame.exit()) {
            System.exit(0);
        }
    }
}
