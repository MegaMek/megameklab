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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import megamek.common.game.Game;
import megamek.common.options.OptionsConstants;
import megamek.common.rules.RulesManager;
import megamek.common.rules.core.CoreRulesManager;
import megamek.common.rules.totalwarfare.TWRulesManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CConfigTest {

    private String originalRulesSystem;
    private RulesManager originalRulesManager;

    @BeforeEach
    void rememberRulesConfiguration() {
        originalRulesSystem = CConfig.getParam(CConfig.MISC_RULES_SYSTEM);
        originalRulesManager = Game.rulesManager;
    }

    @AfterEach
    void restoreRulesConfiguration() {
        CConfig.setParam(CConfig.MISC_RULES_SYSTEM, originalRulesSystem);
        Game.rulesManager = originalRulesManager;
    }

    @Test
    void unknownRulesSystemFallsBackToMegaMekCoreDefault() {
        CConfig.setParam(CConfig.MISC_RULES_SYSTEM, "Unknown Rules");

        assertEquals(OptionsConstants.RULES_CORE, CConfig.getRulesSystem());
        CConfig.applyRulesSystem();
        assertInstanceOf(CoreRulesManager.class, Game.rulesManager);
    }

    @Test
    void totalWarfareSelectionChangesTheActiveRulesManager() {
        CConfig.setParam(CConfig.MISC_RULES_SYSTEM, OptionsConstants.RULES_TW);

        CConfig.applyRulesSystem();

        assertEquals(OptionsConstants.RULES_TW, CConfig.getRulesSystem());
        assertInstanceOf(TWRulesManager.class, Game.rulesManager);
    }
}
