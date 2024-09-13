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

/**
 * This interface may implemented by all windows that exit the application. They
 * may override the
 * exit() method to provide exit handling such as saving global preferences or
 * window settings or
 * show a safety dialog before exiting.
 *
 * @author Simon (Juliez)
 */
public interface AppCloser {

    /**
     * Override to provide specific exit handling. Return false to prevent exiting
     * the application,
     * true to confirm it. By default, this method does nothing and returns true.
     *
     * @return False to prevent exiting, true to confirm
     */
    default boolean exit() {
        return true;
    }
}
