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
package megameklab.ui.battlefieldSupport;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megameklab.ui.MegaMekLabMainUI;

/**
 * The MegaMekLab editor for a standalone Battlefield Support Asset (a {@code .bfs} unit). Unlike other unit editors it
 * has a single tab - {@link BFSStructureTab} - which itself lays out the stat form, the Specials editor and a live card
 * preview in three columns. Assets have no construction rules, so there is no equipment/criticals/armor tab.
 */
public class BFSMainUI extends MegaMekLabMainUI {

    private BFSStructureTab structureTab;
    private BFSStatusBar statusBar;

    public BFSMainUI() {
        super();
        createNewUnit(Entity.ETYPE_BATTLEFIELD_SUPPORT_ASSET);
        requestDirtyCheck();
    }

    public BFSMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new BFSStructureTab(this);
        structureTab.addRefreshedListener(this);

        statusBar = new BFSStatusBar(this);
        statusBar.addRefreshedListener(this);

        configPane.addTab("Battlefield Support Asset", structureTab);

        add(configPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        refreshAll();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        BattlefieldSupportAsset newUnit = new BattlefieldSupportAsset();
        newUnit.setChassis("New");
        newUnit.setModel("Asset");
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        if (structureTab != null) {
            structureTab.refresh();
        }
        if (statusBar != null) {
            statusBar.refresh();
        }
        refreshHeader();
    }

    @Override
    protected void commitPendingEditorChanges() {
        super.commitPendingEditorChanges();
        if (structureTab != null) {
            structureTab.commitChanges();
        }
    }

    @Override
    public void refreshStructure() {
        super.refreshStructure();
        if (statusBar != null) {
            statusBar.refresh();
        }
    }

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
}
