/*
 * Copyright (C) 2024-2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.EntitySavingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.loaders.MtfFile;
import megamek.common.preference.PreferenceManager;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.MegaMekLabTabbedUI;
import megameklab.ui.MenuBarOwner;
import megameklab.ui.PopupMessages;
import megameklab.ui.StartupGUI;
import megameklab.ui.dialog.UiLoader;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import org.apache.commons.io.FileUtils;

public class TabUtil {
    private final static MMLogger LOGGER = MMLogger.create(TabUtil.class);

    private final static String TAB_STATE_DIRECTORY = ".mml_tmp";
    private final static String TAB_STATE_CLEAN = "clean";
    private final static String FILENAME_ASSOCIATIONS = "filenames.db";
    /**
        * Marker written as the first token of {@link #FILENAME_ASSOCIATIONS} for the current (paired) format: six
     * {@code \0}-separated fields per editor (base tmp path, base file name, linked-asset tmp path, linked-asset file
        * path, linked-asset enabled flag, pending paired-save destination). A db without this marker is read in the legacy
        * two-field format (base tmp path, base file name).
     */
    private final static String TAB_STATE_VERSION = "v4";

    public static void saveTabState(List<MegaMekLabMainUI> editors) throws IOException {
        File dir = getTabStateDirectory(true);

        if (dir == null) {
            return;
        }

        File clean = new File(dir, TAB_STATE_CLEAN);
        if (clean.exists()) {
            if (!clean.delete()) {
                throw new IOException("Could not delete " + clean);
            }
        }

        FileUtils.cleanDirectory(dir);

        List<TabStateRecord> records = new ArrayList<>();

        for (var editor : editors) {
            File unitFile;
            if (editor.getEntity() instanceof Mek) {
                unitFile = File.createTempFile("mml_unit_", ".mtf.tmp", dir);
                try (
                      var fos = new FileOutputStream(unitFile);
                      var ps = new PrintStream(fos)
                ) {
                    ps.println(((Mek) editor.getEntity()).getMtf());
                } catch (Exception e) {
                    LOGGER.fatal("Failed to write unit while saving tab state.", e);
                    return;
                }
            } else if (editor.getEntity() instanceof BattlefieldSupportAsset) {
                unitFile = File.createTempFile("mml_unit_", ".bfs.tmp", dir);
                try {
                    Files.writeString(unitFile.toPath(), UnitUtil.saveUnitToString(editor.getEntity(), true)
                          + System.lineSeparator(), StandardCharsets.UTF_8);
                } catch (Exception e) {
                    LOGGER.fatal("Failed to write unit while saving tab state.", e);
                    return;
                }
            } else {
                unitFile = File.createTempFile("mml_unit_", ".blk.tmp", dir);
                try {
                    BLKFile.encode(unitFile.getPath(), editor.getEntity());
                } catch (EntitySavingException e) {
                    LOGGER.fatal("Failed to write unit while saving tab state.", e);
                    return;
                }
            }

            // Meta File Format:
            // First line: an integer n
            // Next n lines: equipment added to the unit but not allocated to a location
            var metaFile = new File(unitFile.getAbsolutePath().replaceFirst("\\.tmp$", ".meta"));
            try (
                  var fos = new FileOutputStream(metaFile);
                  var ps = new PrintStream(fos)
            ) {
                var unallocatedEquipment = editor.getUnallocatedMounted();
                ps.println(unallocatedEquipment.size());
                for (var m : unallocatedEquipment) {
                    var type = m.getType();
                    if (type.isVariableSize()) {
                        ps.printf("%s%s%f\n", type.getInternalName(), MtfFile.SIZE, m.getSize());
                    } else {
                        ps.println(type.getInternalName());
                    }
                }
            } catch (Exception e) {
                LOGGER.fatal("Failed to write unit metadata while saving tab state.", e);
                return;
            }

            var fileName = editor.getFileName();
            if (fileName == null || fileName.isBlank()) {
                fileName = " ";
            }

            // A linked (enabled) Battlefield Support Asset is written to a paired sidecar tmp so its in-progress
            // (possibly unsaved) edits and enabled state are restored alongside the base unit, rather than re-derived
            // from disk. Standalone-asset editors have no linked asset (their entity is the asset, saved above).
            String assetTmpPath = "";
            String assetFilePath = "";
            BattlefieldSupportAsset asset = editor.getBattlefieldSupportAssetCarrier();
            boolean assetEnabled = editor.isBattlefieldSupportAssetEnabled();
            if (asset != null) {
                File assetFile = File.createTempFile("mml_asset_", ".bfs.tmp", dir);
                try {
                    Files.writeString(assetFile.toPath(), UnitUtil.saveUnitToString(asset, true)
                          + System.lineSeparator(), StandardCharsets.UTF_8);
                } catch (Exception e) {
                    LOGGER.fatal("Failed to write linked asset while saving tab state.", e);
                    return;
                }
                assetTmpPath = assetFile.getPath();
                String path = editor.getBattlefieldSupportAssetFilePath();
                assetFilePath = (path == null) ? "" : path;
            }

            String pendingPairedSavePath = Objects.requireNonNullElse(editor.getPendingPairedSaveFileName(), "");
            records.add(new TabStateRecord(unitFile.getPath(), fileName, assetTmpPath, assetFilePath, assetEnabled,
                pendingPairedSavePath));
        }

        File filenameAssociationsFile = new File(dir, FILENAME_ASSOCIATIONS);
        Files.writeString(filenameAssociationsFile.toPath(), serializeTabStateDb(records), StandardCharsets.UTF_8);

        if (!clean.createNewFile()) {
            throw new IOException("Could not create " + clean);
        }
    }

    public static List<MegaMekLabMainUI> loadTabState() throws IOException {
        var dir = getTabStateDirectory(false);

        List<MegaMekLabMainUI> editors = new ArrayList<>();

        if (dir == null) {
            return editors;
        }

        var clean = new File(dir, TAB_STATE_CLEAN);
        if (!clean.exists()) {
            return editors;
        }

        var db = new File(dir, FILENAME_ASSOCIATIONS);
        if (!db.exists()) {
            return editors;
        }

        var content = Files.readString(Paths.get(db.getAbsolutePath()));
        for (TabStateRecord record : parseTabStateDb(content)) {
            var entityFile = new File(record.baseTmpPath());

            var newFile = new File(entityFile.getAbsolutePath().replaceFirst("\\.tmp$", ""));
            FileUtils.copyFile(entityFile, newFile);

            var fileName = record.fileName();

            try {
                Entity loadedUnit = new MekFileParser(newFile).getEntity();
                UnitUtil.updateLoadedUnit(loadedUnit);

                var metaFile = new File(entityFile.getAbsolutePath().replaceFirst("\\.tmp$", ".meta"));
                if (metaFile.exists()) {
                    try (var sc = new Scanner(metaFile)) {
                        int unallocatedEquipment = Integer.parseInt(sc.nextLine());
                        for (int j = 0; j < unallocatedEquipment; j++) {
                            var line = sc.nextLine().split(Pattern.quote(MtfFile.SIZE));
                            var type = EquipmentType.get(line[0]);
                            var mounted = Mounted.createMounted(loadedUnit, type);
                            if (line.length > 1) {
                                mounted.setSize(Double.parseDouble(line[1]));
                            }
                            loadedUnit.addEquipment(mounted, Entity.LOC_NONE, false);
                        }
                    } catch (Exception e) {
                        LOGGER.warn(e, "Could not read meta file for entity file {}:{}", entityFile, fileName);
                    }
                }

                // Restore the saved tab state authoritatively (no disk auto-load): inject the paired linked asset when
                // one was saved, otherwise the unit has no asset even if a .bfs exists on disk.
                BattlefieldSupportAsset restoredAsset = restoreLinkedAsset(record.assetTmpPath());
                var editor = UiLoader.getUIWithoutLinkedAsset(loadedUnit, fileName);
                if (restoredAsset != null) {
                    editor.adoptLinkedBattlefieldSupportAsset(restoredAsset,
                          record.assetFilePath().isBlank() ? null : record.assetFilePath(), record.assetEnabled());
                }
                editor.restorePendingPairedSaveFileName(record.pendingPairedSavePath());
                editors.add(editor);
            } catch (EntityLoadingException e) {
                LOGGER.warn(e, "Could not restore tab for entity file {}:{}", entityFile, fileName);
            } finally {
                if (!newFile.delete()) {
                    LOGGER.warn("Could not delete temporary file {}", newFile);
                }
            }
        }

        if (!clean.delete()) {
            LOGGER.error("Could not mark tab state as dirty on load!");
        }

        return editors;
    }

    /**
     * A single editor's tab-state record: the tmp file its base unit was written to, the base unit's real file name,
     * and (for a linked editor) the tmp file its Battlefield Support Asset was written to and the asset's real file
     * path. The asset fields are blank for an editor with no linked asset.
     */
    record TabStateRecord(String baseTmpPath, String fileName, String assetTmpPath, String assetFilePath,
                          boolean assetEnabled, String pendingPairedSavePath) { }

    /** Serializes the tab-state records to the {@link #FILENAME_ASSOCIATIONS} string (six {@code \0}-separated fields
     * per record, prefixed by the {@link #TAB_STATE_VERSION} marker). */
    static String serializeTabStateDb(List<TabStateRecord> records) {
        StringBuilder sb = new StringBuilder();
        sb.append(TAB_STATE_VERSION).append('\0');
        for (TabStateRecord record : records) {
            sb.append(record.baseTmpPath()).append('\0')
                  .append(record.fileName()).append('\0')
                  .append(record.assetTmpPath()).append('\0')
                  .append(record.assetFilePath()).append('\0')
                .append(record.assetEnabled()).append('\0')
                .append(record.pendingPairedSavePath()).append('\0');
        }
        return sb.toString();
    }

    /**
     * Parses the {@link #FILENAME_ASSOCIATIONS} string into tab-state records. A db beginning with the
    * {@link #TAB_STATE_VERSION} marker is read as six-field (paired) records; {@code v3} is read as five-field paired
    * records, {@code v2} is read as four-field paired records, and any other db is read in the legacy two-field format
    * (base tmp path, base file name), with blank asset and pending-save fields. A blank base file name is normalized to
    * the empty string.
     *
     * @param content the db content
     *
     * @return the parsed records
     */
    static List<TabStateRecord> parseTabStateDb(String content) {
        var parts = content.split(Pattern.quote("\0"), -1);
        boolean v4 = (parts.length > 0) && TAB_STATE_VERSION.equals(parts[0]);
        boolean v3 = (parts.length > 0) && "v3".equals(parts[0]);
        boolean v2 = (parts.length > 0) && "v2".equals(parts[0]);
        boolean versioned = v4 || v3 || v2;
        int start = versioned ? 1 : 0;
        int step = v4 ? 6 : (v3 ? 5 : (v2 ? 4 : 2));
        List<TabStateRecord> records = new ArrayList<>();
        for (int i = start; i + step <= parts.length; i += step) {
            String fileName = parts[i + 1].isBlank() ? "" : parts[i + 1];
            records.add(new TabStateRecord(parts[i], fileName,
                  versioned ? parts[i + 2] : "", versioned ? parts[i + 3] : "",
                  (v4 || v3) ? Boolean.parseBoolean(parts[i + 4]) : versioned && !parts[i + 2].isBlank(),
                  v4 ? parts[i + 5] : ""));
        }
        return records;
    }

    /**
     * Restores a linked Battlefield Support Asset saved to a tab-state tmp file.
     *
     * @param assetTmpPath the path to the {@code .bfs.tmp} file, or blank if the editor had no linked asset
     *
     * @return the restored asset, or {@code null} if there was none or it could not be read
     */
    private static @Nullable BattlefieldSupportAsset restoreLinkedAsset(String assetTmpPath) {
        if ((assetTmpPath == null) || assetTmpPath.isBlank()) {
            return null;
        }
        File assetTmp = new File(assetTmpPath);
        File assetFile = new File(assetTmp.getAbsolutePath().replaceFirst("\\.tmp$", ""));
        try {
            FileUtils.copyFile(assetTmp, assetFile);
            Entity entity = new MekFileParser(assetFile).getEntity();
            if (entity instanceof BattlefieldSupportAsset asset) {
                UnitUtil.updateLoadedUnit(asset);
                return asset;
            }
            return null;
        } catch (Exception e) {
            LOGGER.warn(e, "Could not restore linked asset tab state from %s".formatted(assetTmpPath));
            return null;
        } finally {
            if (assetFile.exists() && !assetFile.delete()) {
                LOGGER.warn("Could not delete temporary file {}", assetFile);
            }
        }
    }

    private static File getTabStateDirectory(boolean create) throws IOException {
        var userDirString = PreferenceManager.getClientPreferences().getUserDir();
        if (userDirString == null || userDirString.isBlank()) {
            userDirString = ".";
        }

        var userDir = new File(userDirString);

        if (!userDir.isDirectory()) {
            throw new IOException("User dir is not a directory: " + userDirString);
        }

        var tabStateDir = new File(userDir, TAB_STATE_DIRECTORY);
        if (!tabStateDir.isDirectory()) {
            if (!create) {
                return null;
            }
            if (!tabStateDir.mkdir()) {
                throw new IOException("Could not create tab state directory: " + tabStateDir);
            } else {
                hideIfSupported(tabStateDir.toPath());
            }
        }

        return tabStateDir;
    }

    /**
     * Marks the tab state directory hidden on filesystems that record hiding as a file attribute.
     *
     * <p>That is a DOS attribute, so it exists on Windows and nowhere else - asking for the {@code dos} view on any
     * other filesystem throws {@link UnsupportedOperationException}. Elsewhere the leading dot in
     * {@value #TAB_STATE_DIRECTORY} already hides the directory by convention, so there is nothing to do.</p>
     *
     * <p>Hiding is cosmetic either way, so a failure is logged rather than propagated: losing the whole session's tab
     * state because its directory could not be made invisible would be a poor trade.</p>
     *
     * @param directory the newly created tab state directory
     */
    private static void hideIfSupported(Path directory) {
        if (!directory.getFileSystem().supportedFileAttributeViews().contains("dos")) {
            return;
        }

        try {
            Files.setAttribute(directory, "dos:hidden", true);
        } catch (IOException | UnsupportedOperationException ex) {
            LOGGER.warn(ex, "Could not hide the tab state directory {}", directory);
        }
    }

    public static void loadMany(List<File> files, MenuBarOwner owner) {
        loadMany(
              files.stream().map(f -> {
                  try {
                      return new MekFileParser(f).getEntity();
                  } catch (EntityLoadingException e) {
                      LOGGER.errorDialog(e, "Failed to load entity.", "Entity load error.");
                      return null;
                  }
              }).filter(Objects::nonNull).toList(),

              files.stream().map(String::valueOf).toList(),

              owner
        );
    }

    public static void loadMany(List<Entity> entities, List<String> fileNames, MenuBarOwner owner) {
        if (entities.isEmpty()) {
            return;
        }
        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            ProgressMonitor progress = new ProgressMonitor(tabbedUI,
                  "Loading units...",
                  "Loaded 0 / %d".formatted(entities.size()),
                  0,
                  entities.size());
            SwingUtilities.invokeLater(() -> insertTabs(0, entities, fileNames, tabbedUI, progress));
        } else if (owner instanceof StartupGUI) {
            owner.getFrame().dispose();
            UiLoader.initializeFromBlankUI((tabbedUI) -> {
                ProgressMonitor progress = new ProgressMonitor(tabbedUI,
                      "Loading units...",
                      "Loaded 0 / %d".formatted(entities.size()),
                      0,
                      entities.size());
                insertTabs(0, entities, fileNames, tabbedUI, progress);
            });
        }

    }

    private static void insertTabs(int i, List<Entity> entities, List<String> fileNames, MegaMekLabTabbedUI tabbedUI,
          ProgressMonitor progress) {
        if (entities.isEmpty()) {
            return;
        }
        var newUnit = entities.get(i);
        try {
            tabbedUI.addUnit(newUnit, fileNames.get(i), i == 0);
            String validationResult = UnitUtil.validateUnit(newUnit);
            if (!validationResult.isBlank()) {
                PopupMessages.showUnitInvalidWarning(tabbedUI.getFrame(), validationResult);
            }

            CConfig.setMostRecentFile(fileNames.get(i));
        } catch (Exception e) {
            LOGGER.errorDialog(e, "Failed to load the unit %s.", "Entity load error.", newUnit.getDisplayName());
        }

        progress.setProgress(i + 1);
        progress.setNote("Loaded %d / %d".formatted(i + 1, entities.size()));
        if (progress.isCanceled() || i + 1 == entities.size()) {
            return;
        }
        SwingUtilities.invokeLater(() -> insertTabs(i + 1, entities, fileNames, tabbedUI, progress));
    }

    private TabUtil() {}
}
