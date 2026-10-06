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
 */
package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javax.swing.JDialog;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.MegaMekLabMainUI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Verifies that {@link UnitMemento} tracks the linked Battlefield Support Asset carrier alongside the base entity, so a
 * linked editor's dirty/undo/redo treats the base unit and its asset as a single unit of state.
 */
@ExtendWith(value = InitializeTypes.class)
class UnitMementoTest {

    /** A minimal MainUI stub that only exposes a base entity and an optional linked asset carrier. */
    private static class StubMainUI extends MegaMekLabMainUI {
        private final BattlefieldSupportAsset asset;
        private final boolean enabled;
        private final String path;

        StubMainUI(@Nullable BattlefieldSupportAsset asset) {
            this(asset, asset != null, null);
        }

        StubMainUI(@Nullable BattlefieldSupportAsset asset, boolean enabled, @Nullable String path) {
            this.asset = asset;
            this.enabled = enabled;
            this.path = path;
        }

        @Override
        public @Nullable BattlefieldSupportAsset getBattlefieldSupportAssetCarrier() {
            return asset;
        }

        @Override
        public boolean isBattlefieldSupportAssetEnabled() {
            return enabled;
        }

        @Override
        public @Nullable String getBattlefieldSupportAssetFilePath() {
            return path;
        }

        @Override
        public void reloadTabs() { }

        @Override
        public JDialog getFloatingEquipmentDatabase() {
            return null;
        }

        @Override
        public List<Mounted<?>> getUnallocatedMounted() {
            return List.of();
        }

        @Override
        public ITechManager getTechManager() {
            return null;
        }

        @Override
        public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) { }
    }

    private static BattlefieldSupportAsset baseAsset() {
        BattlefieldSupportAsset base = new BattlefieldSupportAsset();
        base.setChassis("Base Unit");
        base.setModel("BU-1");
        base.setCost(10);
        return base;
    }

    private static BattlefieldSupportAsset linkedAsset(int cost) {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        // Fixed UUID/link so two separately-built "identical" assets serialize identically (a real undo/redo snapshot
        // re-serializes the same asset instance, which keeps its UUID).
        asset.setUnitFileUUID("019f5efd-5a93-78c3-b4f2-dd83804af175");
        asset.setLinkedUnitId("019f583e-e2c6-7b99-a188-ba0759db128e");
        asset.setChassis("Linked Asset");
        asset.setModel("LA-1");
        asset.setCost(cost);
        return asset;
    }

    @Test
    void mementoWithoutAssetHasNoAsset() {
        UnitMemento memento = new UnitMemento(baseAsset(), new StubMainUI(null));
        assertFalse(memento.hasAsset());
        assertNull(memento.createAsset());
    }

    @Test
    void mementoCapturesAndRestoresLinkedAsset() {
        Entity base = baseAsset();
        UnitMemento memento = new UnitMemento(base, new StubMainUI(linkedAsset(17)));

        assertTrue(memento.hasAsset());
        BattlefieldSupportAsset restored = memento.createAsset();
        assertNotNull(restored);
        assertEquals("Linked Asset", restored.getChassis());
        assertEquals(17, restored.getCost());
    }

    @Test
    void changingOnlyTheAssetMakesMementosUnequal() {
        Entity base = baseAsset();
        UnitMemento before = new UnitMemento(base, new StubMainUI(linkedAsset(17)));
        UnitMemento after = new UnitMemento(base, new StubMainUI(linkedAsset(23)));

        assertFalse(before.equals(after), "a change to only the linked asset must register as a change");
    }

    @Test
    void identicalBaseAndAssetAreEqual() {
        Entity base = baseAsset();
        UnitMemento a = new UnitMemento(base, new StubMainUI(linkedAsset(17)));
        UnitMemento b = new UnitMemento(base, new StubMainUI(linkedAsset(17)));

        assertTrue(a.equals(b));
    }

    @Test
    void addingAnAssetMakesMementosUnequal() {
        Entity base = baseAsset();
        UnitMemento without = new UnitMemento(base, new StubMainUI(null));
        UnitMemento with = new UnitMemento(base, new StubMainUI(linkedAsset(17)));

        assertFalse(without.equals(with), "gaining a linked asset must register as a change");
    }

    @Test
    void disabledCarrierAndPathArePreserved() {
        UnitMemento memento = new UnitMemento(baseAsset(),
              new StubMainUI(linkedAsset(17), false, "units/Base.bfs"));

        assertTrue(memento.hasAsset());
        assertFalse(memento.isAssetEnabled());
        assertEquals("units/Base.bfs", memento.getAssetFilePath());
        BattlefieldSupportAsset restored = memento.createAsset();
        assertNotNull(restored);
        assertEquals(17, restored.getCost());
    }

    @Test
    void enabledFlagOrPathChangesMementoEquality() {
        Entity base = baseAsset();
        UnitMemento disabled = new UnitMemento(base, new StubMainUI(linkedAsset(17), false, "old.bfs"));
        UnitMemento enabled = new UnitMemento(base, new StubMainUI(linkedAsset(17), true, "old.bfs"));
        UnitMemento moved = new UnitMemento(base, new StubMainUI(linkedAsset(17), false, "new.bfs"));

        assertFalse(disabled.equals(enabled));
        assertFalse(disabled.equals(moved));
    }
}
