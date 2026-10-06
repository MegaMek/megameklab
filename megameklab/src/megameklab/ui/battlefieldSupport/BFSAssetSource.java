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

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;

/**
 * Implemented by an eligible base-unit editor (Combat/Support Vehicle, Battle Armor, Conventional Infantry, Gun
 * Emplacement) that can hold a linked Battlefield Support Asset alongside its base unit. The {@link BFSStructureTab},
 * when built in linked mode, reads and writes the carrier through this source and toggles the linkage via the enable
 * flag. The carrier persists for the session even while disabled (so re-enabling loses no work); only the enable flag
 * governs whether it is saved and whether it counts toward dirty/undo state.
 */
public interface BFSAssetSource {

    /**
     * @return the Battlefield Support Asset carrier being edited, creating it (seeded from the base unit) on first
     *       access. Never {@code null} while a linked editor's Asset tab is present, regardless of the enable flag.
     */
    BattlefieldSupportAsset getBattlefieldSupportAssetCarrier();

    /**
     * @return the existing carrier without creating one, or {@code null} when this editor has never held an asset
     */
    @Nullable
    BattlefieldSupportAsset getExistingBattlefieldSupportAssetCarrier();

    /** @return whether the linked asset is currently enabled (checked) */
    boolean isBattlefieldSupportAssetEnabled();

    /**
     * Enables or disables the linked asset. Disabling keeps the carrier and its data in memory for the session; it only
     * stops the asset from being written on save and from counting toward the unit's saved/dirty state.
     *
     * @param enabled whether the asset should be enabled
     */
    void setBattlefieldSupportAssetEnabled(boolean enabled);

    /**
    * @return the carrier when the asset is enabled, otherwise {@code null}. Used by the editor's save hooks so a
    *       disabled asset is not written.
     */
    @Nullable
    BattlefieldSupportAsset getEnabledAsset();

    /**
     * Adopts an asset carrier loaded or restored from a file/memento, enabling the linkage when the asset is present.
     *
     * @param asset the carrier to adopt, or {@code null} to clear it (leaving no linked asset)
     */
    void adoptAsset(@Nullable BattlefieldSupportAsset asset);

    /**
     * @return the file the linked asset was loaded from / last saved to (a {@code .bfs} path), or {@code null} for a
     *       newly-added asset that has never been written. Used to decide the {@code .bfs} save target and to locate a
     *       sidecar for deletion when the asset is disabled.
     */
    @Nullable
    String getAssetFilePath();

    /**
     * Records the file the linked asset is associated with (its {@code .bfs} path).
     *
     * @param assetFilePath the {@code .bfs} path, or {@code null} for none
     */
    void setAssetFilePath(@Nullable String assetFilePath);
}
