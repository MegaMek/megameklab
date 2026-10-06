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
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import megamek.common.equipment.EquipmentType;
import megamek.common.game.Game;
import megamek.common.rules.RulesManager;
import megamek.common.rules.core.CoreRulesManager;
import megamek.common.rules.totalwarfare.TWRulesManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class ChargeRecordSheetRulesTest {

    private RulesManager originalRulesManager;

    @BeforeEach
    void rememberRulesManager() {
        originalRulesManager = Game.rulesManager;
    }

    @AfterEach
    void restoreRulesManager() {
        Game.rulesManager = originalRulesManager;
    }

    @Test
    void coreDisplaysWeightMultiplierAndSeparatesSpikesFromTmm() throws Exception {
        BipedMek mek = chargeMek(true);
        Game.rulesManager = new CoreRulesManager();

        assertEquals("14×(TMM+1)+2", chargeEntry(mek).getDamageField(0));
    }

    @Test
    void totalWarfareRetainsDamagePerHex() throws Exception {
        BipedMek mek = chargeMek(true);
        Game.rulesManager = new TWRulesManager();

        assertEquals("7/hex+2", chargeEntry(mek).getDamageField(0));
    }

    @Test
    void coreOmitsTheSpikesTermWhenNoneAreInstalled() throws Exception {
        BipedMek mek = chargeMek(false);
        Game.rulesManager = new CoreRulesManager();

        assertEquals("14×(TMM+1)", chargeEntry(mek).getDamageField(0));
    }

    private BipedMek chargeMek(boolean withSpikes) throws Exception {
        BipedMek mek = new BipedMek();
        mek.setWeight(70);
        if (withSpikes) {
            mek.initializeInternal(20, Mek.LOC_CENTER_TORSO);
            mek.addEquipment(EquipmentType.get("Spikes"), Mek.LOC_CENTER_TORSO);
        }
        return mek;
    }

    private IntrinsicPhysicalInventoryEntry chargeEntry(BipedMek mek) {
        return IntrinsicPhysicalInventoryEntry.getEntriesFor(mek).stream()
              .filter(IntrinsicPhysicalInventoryEntry.class::isInstance)
              .map(IntrinsicPhysicalInventoryEntry.class::cast)
              .filter(entry -> entry.name().equals("Charge"))
              .findFirst()
              .orElseThrow();
    }
}
