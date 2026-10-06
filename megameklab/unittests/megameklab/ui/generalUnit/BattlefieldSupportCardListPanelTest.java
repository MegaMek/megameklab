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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import megamek.common.battlefieldSupport.BFSDamage;
import megamek.common.battlefieldSupport.BFSRange;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.EquipmentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Smoke test for the multi-select BFS card preview: building the panel and setting assets exercises the config-driven
 * font/color resolution (CConfig / RecordSheetOptions) and the card rendering path without throwing.
 */
class BattlefieldSupportCardListPanelTest {

    @BeforeAll
    static void beforeAll() {
        EquipmentType.initializeTypes();
    }

    private static BattlefieldSupportAsset asset() {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Test Asset");
        asset.setMp(8);
        asset.setTmm(3);
        asset.setRange(new BFSRange(3, 6, 9));
        asset.setSkill(6);
        asset.setDamage(new BFSDamage(5, 4));
        asset.setCost(23);
        return asset;
    }

    @Test
    void buildingAndSettingAssetsDoesNotThrow() {
        BattlefieldSupportAsset asset = asset();
        assertDoesNotThrow(() -> {
            BattlefieldSupportCardListPanel panel = new BattlefieldSupportCardListPanel();
            panel.setAssets(List.of(asset, asset));
            // Empty selection shows the placeholder without error.
            panel.setAssets(List.of());
        });
    }
}
