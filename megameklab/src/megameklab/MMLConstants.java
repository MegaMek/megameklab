/*
 * Copyright (c) 2021-2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab;

import megamek.SuiteConstants;

/**
 * These are constants that hold across MegaMekLab.
 */
public final class MMLConstants extends SuiteConstants {
    // region General Constants
    public static final String PROJECT_NAME = "MegaMekLab";
    // endregion General Constants

    // region GUI Constants
    // endregion GUI Constants

    // region MMLOptions
    // endregion MMLOptions

    // region File Paths
    // endregion File Paths

    public static final SentryAttributes SENTRY_ATTRIBUTES = new SentryAttributes() {
        @Override
        public String serverName() {
            return "MegaMekLabClient";
        }

        @Override
        public String dsn() {
            return "https://6dfac298f9ed6fb0d9a9f7e5669d386b@sentry.tapenvy.us/9";
        }
    };
}
