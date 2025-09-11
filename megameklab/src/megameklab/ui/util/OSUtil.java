/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

/**
 * Utility class to determine the operating system.
 *
 * @author Luana Coppio
 */
public class OSUtil {
    public enum OS {
        WINDOWS,
        MAC,
        LINUX,
        OTHER
    }

    private static OS os = null;

    /**
     * Returns the current operating system.
     *
     * @return {@link OS}
     */
    public static OS getOS() {
        if (os == null) {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("win")) {
                os = OS.WINDOWS;
            } else if (osName.contains("mac") || osName.contains("darwin")) {
                os = OS.MAC;
            } else if (osName.contains("nix") || osName.contains("nux")) {
                os = OS.LINUX;
            } else {
                os = OS.OTHER;
            }
        }
        return os;
    }

    /**
     * @return true if the current operating system is Mac
     */
    public static boolean isMac() {
        return OS.MAC.equals(getOS());
    }

    /**
     * @return true if the current operating system is Windows
     */
    public static boolean isWindows() {
        return OS.WINDOWS.equals(getOS());
    }

    /**
     * @return true if the current operating system is Linux
     */
    public static boolean isLinux() {
        return OS.LINUX.equals(getOS());
    }

}
