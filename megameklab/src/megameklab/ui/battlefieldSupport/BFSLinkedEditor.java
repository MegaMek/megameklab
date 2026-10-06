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

import java.awt.Component;

import megamek.common.ui.EnhancedTabbedPane;

/**
 * Implemented by an eligible base-unit editor ({@code MainUI}) that coordinates a linked Battlefield Support Asset. The
 * base unit's Structure tab hosts the enable checkbox (in its Basic Information view) and drives the linkage through
 * this interface, while the editor owns the actual tab show/hide and asset carrier.
 */
public interface BFSLinkedEditor {

    /**
     * Enables or disables the linked asset: toggles the carrier's enabled state, shows/hides the Asset tab and records
     * the change as a dirty/undo state change.
     *
     * @param enabled whether the unit should have a linked Battlefield Support Asset
     */
    void setBattlefieldSupportAssetLinked(boolean enabled);

    /** @return whether the linked asset is currently enabled. */
    boolean isBattlefieldSupportAssetLinked();

    /**
     * @return whether the base unit's current movement mode is a legal asset motive (see
     *       {@link BFSLinkedAssetSupport#isMotiveEligible}). When {@code false}, the unit cannot have an asset and the
     *       enable checkbox is disabled.
     */
    boolean isBattlefieldSupportAssetMotiveEligible();

    /**
     * Inserts (immediately before {@code beforeTab}) or removes the given asset tab component from {@code pane} to
     * reflect the requested visibility. A no-op if the tab is already in the requested state.
     *
     * @param pane      the editor's tabbed pane
     * @param assetTab  the asset tab component (e.g. the wrapping scroll pane)
     * @param title     the tab title
     * @param beforeTab the tab the asset tab should be inserted before (typically the Preview tab)
     * @param visible   whether the asset tab should be shown
     */
    static void setAssetTabVisible(EnhancedTabbedPane pane, Component assetTab, String title, Component beforeTab,
          boolean visible) {
        boolean present = pane.containsTab(assetTab);
        if (visible) {
            if (!present) {
                int before = pane.indexOfComponent(beforeTab);
                if (before < 0) {
                    before = pane.getTabCount();
                }
                pane.insertTab(title, null, assetTab, null, before);
            }
        } else if (present) {
            // removeTab also closes the floating window when the tab has been detached.
            pane.removeTab(assetTab);
        }
    }
}
