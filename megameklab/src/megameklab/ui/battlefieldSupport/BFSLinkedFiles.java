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
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megameklab.ui.PopupMessages;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.util.CConfig;

/**
 * File-side helpers for a linked Battlefield Support Asset: writing the {@code .bfs} sidecar next to its base unit's
 * saved file, and offering to delete a stale sidecar when the asset has been disabled. The {@code .bfs} location is
 * derived from the base file so linked saving stays convenient (only the base file location is ever prompted).
 */
public final class BFSLinkedFiles {

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");
    public static final String BFS_EXTENSION = ".bfs";

    private BFSLinkedFiles() { }

    /**
     * Handles the linked asset's {@code .bfs} sidecar when the base unit is saved to {@code baseFile}. When the asset is
     * enabled it is re-linked to the base unit's final UUID and written to its resolved target; when disabled, a stale
     * sidecar (if any) is offered for deletion.
     *
     * @param owner             the owner frame for any dialogs
     * @param source            the editor's asset source (carrier + enable flag + current asset file path)
     * @param baseFile          the base unit file that was just saved
     * @param baseUnitFileUUID  the base unit's final unit-file UUID (the asset's {@code linkedUnitId} is set to this so
     *                          the sidecar links to the base file it is saved alongside, even if the base adopted a
     *                          different UUID during the save)
     * @param baseMovedOrNew    {@code true} when the base was saved to a new/different file (Save As, rename, or first
     *                          save) — the sidecar is then written fresh next to the new base file rather than to the
     *                          asset's previous location
     * @param baseConflictChoice the UUID conflict choice made for the base unit (so the asset's own UUID follows the
     *                          same decision on a conflict), or {@code null} if the base save had no conflict
     * @throws IOException if an enabled linked Asset cannot be written
     */
    public static void handleSidecarOnSave(JFrame owner, BFSAssetSource source, File baseFile,
          @Nullable String baseUnitFileUUID, boolean baseMovedOrNew,
          @Nullable PopupMessages.UnitFileUUIDChoice baseConflictChoice) throws IOException {
        BattlefieldSupportAsset asset = source.getEnabledAsset();
        if (asset != null) {
            asset.setLinkedUnitId(baseUnitFileUUID);
            File target = resolveSaveTarget(baseFile, source.getAssetFilePath(), baseMovedOrNew);
            // Resolve the asset's own UUID with the same rules as the base unit (adopt/keep/regenerate), following the
            // base unit's conflict choice, so the asset's UUID stays in lockstep with its base (e.g. both regenerate
            // when the unit is renamed to a new file, avoiding two units sharing a UUID).
            MegaMekLabFileSaver.prepareLinkedAssetUnitFileUUID(target, asset, baseConflictChoice);
            MegaMekLabFileSaver.writeUnitToFile(target, asset);
            asset.storeSavedUnitData();
            source.setAssetFilePath(target.getPath());
        } else {
            offerToDeleteStaleSidecar(owner, source, baseFile);
        }
    }

    /**
     * Resolves where the linked asset's {@code .bfs} should be written for a base file saved to {@code baseFile}.
     * <ul>
     *     <li>An in-place save (base file unchanged) of an asset that already has a file writes back to that file
     *     (wherever it was loaded from), even if its name differs from the base stem.</li>
     *     <li>A Save As / rename / first save, or a newly-added asset with no file yet, writes a fresh
     *     {@code <baseStem>.bfs} next to the base file (self-contained co-located copy).</li>
     * </ul>
     *
     * @param baseFile          the just-saved base unit file
     * @param existingAssetPath the asset's current file path, or {@code null} if it has none
     * @param baseMovedOrNew    whether the base file location changed (or is a first save)
     *
     * @return the {@code .bfs} file to write
     */
    public static File resolveSaveTarget(File baseFile, String existingAssetPath, boolean baseMovedOrNew) {
        if (!baseMovedOrNew && (existingAssetPath != null) && !isInsideZip(existingAssetPath)) {
            return new File(existingAssetPath);
        }
        return coLocatedSidecar(baseFile);
    }

    /** @return the {@code <baseStem>.bfs} file in the base file's directory. */
    public static File coLocatedSidecar(File baseFile) {
        File dir = baseFile.getParentFile();
        String stem = stripExtension(baseFile.getName());
        return new File(dir, stem + BFS_EXTENSION);
    }

    private static void offerToDeleteStaleSidecar(JFrame owner, BFSAssetSource source, File baseFile) {
        Set<File> candidates = new LinkedHashSet<>();
        String knownPath = source.getAssetFilePath();
        if (knownPath != null) {
            candidates.add(new File(knownPath));
        }
        candidates.add(coLocatedSidecar(baseFile));

        for (File candidate : candidates) {
            if (isInsideZip(candidate.getPath())) {
                JOptionPane.showMessageDialog(owner,
                      "The Battlefield Support Asset data for this unit is stored inside a zip archive (" +
                            candidate.getName() + ") and cannot be removed from MegaMekLab. Re-enable the asset to " +
                            "keep it, or remove the data from the archive manually.",
                      "Asset Data In Archive", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            if (!candidate.isFile()) {
                continue;
            }
            int choice = JOptionPane.showConfirmDialog(owner,
                  I18N.getString("BFSLinkedFiles.delete.message").formatted(candidate.getPath()),
                  I18N.getString("BFSLinkedFiles.delete.title"),
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                if (candidate.delete()) {
                    if (candidate.getPath().equals(knownPath)) {
                        source.setAssetFilePath(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(owner,
                          I18N.getString("BFSLinkedFiles.deleteFailed.message").formatted(candidate.getPath()),
                          I18N.getString("BFSLinkedFiles.deleteFailed.title"), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /** @return whether the given path points inside a zip archive (a cached asset can't be deleted from one). */
    public static boolean isInsideZip(String path) {
        if (path == null) {
            return false;
        }
        int entryDelimiter = path.indexOf(CConfig.RECENT_ENTRY_DELIMITER);
        String archivePath = (entryDelimiter < 0) ? path : path.substring(0, entryDelimiter);
        return archivePath.toLowerCase(Locale.ROOT).endsWith(".zip");
    }

    /** @return the file name without its extension (the part after the last dot). */
    public static String stripExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot < 0) ? fileName : fileName.substring(0, dot);
    }
}
