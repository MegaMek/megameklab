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
package megameklab.ui.util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is a specific WindowAdapter that acts on a windowClosing event by
 * calling the given AppCloser's
 * exit() method and exits the application if exit() returned true. Add this to
 * any Frame that
 * should exit the application by closing its window.
 *
 * @author Simon (Juliez)
 */
public final class ExitOnWindowClosingListener extends WindowAdapter {

    private final AppCloser frame;

    /**
     * Returns a new window listener for the given frame that will react to
     * windowClosing events by calling
     * the frame's exit() method and closing the application when exit() returns
     * true.
     *
     * @param frame The frame (implementing AppCloser) that this window listener is
     *              for
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
