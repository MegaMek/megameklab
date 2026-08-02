/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 */
package megameklab.ui.battlefieldSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.units.UnitRole;
import org.junit.jupiter.api.Test;

class BFSLinkedAssetSupportTest {

    @Test
    void sharedIdentitySynchronizesRoleFromBase() {
        BattlefieldSupportAsset base = new BattlefieldSupportAsset();
        base.setUnitRole(UnitRole.SCOUT);
        BattlefieldSupportAsset carrier = new BattlefieldSupportAsset();
        carrier.setUnitRole(UnitRole.BRAWLER);

        BFSLinkedAssetSupport.applySharedIdentity(base, carrier);

        assertEquals(UnitRole.SCOUT, carrier.getRole());
    }
}