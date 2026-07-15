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

import java.io.File;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.loaders.MekFileParser;
import megamek.common.loaders.MekSummary;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;

/**
 * Detects and loads the counterpart of a linked Battlefield Support Asset pair when a unit is opened, so that opening
 * either the base unit or its {@code .bfs} yields the combined editor. Link detection checks <em>both</em> the file's
 * own directory (a same-named counterpart file) <em>and</em> the unit cache (by unit-file UUID: an asset's
 * {@code linkedUnitId} is its base unit's UUID), matching the two ways a pair can be associated.
 */
public final class BFSLinkedOpen {

    private static final MMLogger logger = MMLogger.create(BFSLinkedOpen.class);

    private static final String[] BASE_EXTENSIONS = { ".blk", ".mtf" };

    private BFSLinkedOpen() { }

    /**
     * A loaded counterpart unit and the path it was loaded from.
     *
     * @param entity   the loaded unit (a base unit, or a {@link BattlefieldSupportAsset})
     * @param filePath the file it was loaded from
     */
    public record LoadedLink(Entity entity, String filePath) { }

    /**
     * Finds the linked Battlefield Support Asset for a base unit being opened.
     *
     * @param base         the base unit being opened
     * @param baseFilename the file the base unit was opened from (may be blank/{@code null} for a new unit)
     *
     * @return the loaded asset and its file path, or {@code null} if the base unit has no linked asset
     */
    public static @Nullable LoadedLink findLinkedAssetForBase(Entity base, @Nullable String baseFilename) {
        // 1) A co-located <stem>.bfs next to the opened base file.
        File coLocated = counterpartFile(baseFilename, BFSLinkedFiles.BFS_EXTENSION);
        LoadedLink link = tryLoad(coLocated, null, true);
        if (link != null) {
            return link;
        }
        // 2) Cache linkage by the base unit's UUID (the asset's linkedUnitId).
        MekSummary assetSummary = lookupCache(cache -> cache.getLinkedAssetForUnitFileUUID(base.getUnitFileUUID()));
        return loadFromSummary(assetSummary, true);
    }

    /**
     * Finds the linked base unit for a Battlefield Support Asset being opened.
     *
     * @param asset         the asset being opened
     * @param assetFilename the file the asset was opened from (may be blank/{@code null})
     *
     * @return the loaded base unit and its file path, or {@code null} if the asset is standalone
     */
    public static @Nullable LoadedLink findLinkedBaseForAsset(BattlefieldSupportAsset asset,
          @Nullable String assetFilename) {
        // 1) A co-located <stem>.blk / <stem>.mtf next to the opened .bfs.
        for (String ext : BASE_EXTENSIONS) {
            LoadedLink link = tryLoad(counterpartFile(assetFilename, ext), null, false);
            if (link != null) {
                return link;
            }
        }
        // 2) Cache linkage by the asset's linkedUnitId (the base unit's UUID).
        MekSummary baseSummary = lookupCache(cache -> {
            MekSummary ms = cache.getByUnitFileUUID(asset.getLinkedUnitId());
            return ((ms != null) && !ms.isBattlefieldSupportAsset()) ? ms : null;
        });
        return loadFromSummary(baseSummary, false);
    }

    /** @return the {@code <stem>ext} file next to {@code filename}, or {@code null} if not applicable/not a loose file. */
    private static @Nullable File counterpartFile(@Nullable String filename, String ext) {
        if ((filename == null) || filename.isBlank() || BFSLinkedFiles.isInsideZip(filename)) {
            return null;
        }
        File file = new File(filename);
        File dir = file.getParentFile();
        return new File(dir, BFSLinkedFiles.stripExtension(file.getName()) + ext);
    }

    private static @Nullable LoadedLink loadFromSummary(@Nullable MekSummary summary, boolean expectAsset) {
        if (summary == null) {
            return null;
        }
        File file = summary.getSourceFile();
        return tryLoad(file, summary.getEntryName(), expectAsset, (file == null) ? null : file.getPath());
    }

    private static @Nullable LoadedLink tryLoad(@Nullable File file, @Nullable String entryName, boolean expectAsset) {
        return tryLoad(file, entryName, expectAsset, (file == null) ? null : file.getPath());
    }

    private static @Nullable LoadedLink tryLoad(@Nullable File file, @Nullable String entryName, boolean expectAsset,
          @Nullable String filePath) {
        if ((file == null) || !file.isFile()) {
            return null;
        }
        try {
            Entity entity = new MekFileParser(file, entryName).getEntity();
            boolean isAsset = entity instanceof BattlefieldSupportAsset;
            if (expectAsset != isAsset) {
                return null;
            }
            return new LoadedLink(entity, filePath);
        } catch (Exception ex) {
            logger.error(ex, "Failed to load linked unit file {}", file);
            return null;
        }
    }

    private static @Nullable MekSummary lookupCache(java.util.function.Function<MekSummaryCache, MekSummary> lookup) {
        MekSummaryCache cache = MekSummaryCache.getInstance();
        // Only consult the cache when it is already loaded, so opening a file never blocks the UI thread.
        if (!cache.isInitialized()) {
            return null;
        }
        return lookup.apply(cache);
    }
}
