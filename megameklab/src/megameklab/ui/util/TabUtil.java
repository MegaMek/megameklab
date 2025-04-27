/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.ui.util;

import megamek.common.*;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.EntitySavingException;
import megamek.common.loaders.MtfFile;
import megamek.common.preference.PreferenceManager;
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

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class TabUtil {
    private final static MMLogger logger = MMLogger.create(TabUtil.class);

    private final static String TAB_STATE_DIRECTORY = ".mml_tmp";
    private final static String TAB_STATE_CLEAN = "clean";
    private final static String FILENAME_ASSOCIATIONS = "filenames.db";

    public static void saveTabState(List<MegaMekLabMainUI> editors) throws IOException {
        var dir = getTabStateDirectory(true);

        var clean = new File(dir, TAB_STATE_CLEAN);
        if (clean.exists()) {
            if (!clean.delete()) {
                throw new IOException("Could not delete " + clean);
            }
        }

        FileUtils.cleanDirectory(dir);

        Map<File, String> filenameAssociations = new LinkedHashMap<>();

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
                    logger.fatal("Failed to write unit while saving tab state.", e);
                    return;
                }
            } else {
                unitFile = File.createTempFile("mml_unit_", ".blk.tmp", dir);
                try {
                    BLKFile.encode(unitFile.getPath(), editor.getEntity());
                } catch (EntitySavingException e) {
                    logger.fatal("Failed to write unit while saving tab state.", e);
                    return;
                }
            }

            // Meta File Format:
            // First line: an integer n
            // Next n lines: equipment added to the unit but not allocated to a location
            var metaFile = new File(unitFile.getAbsolutePath().replaceFirst("\\.tmp$", ".meta"));
            try (
                var fos = new FileOutputStream(metaFile);
                var ps = new PrintStream(fos);
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
                logger.fatal("Failed to write unit metadata while saving tab state.", e);
                return;
            }

            var fileName = editor.getFileName();
            if (fileName == null || fileName.isBlank()) {
                fileName = " ";
            }

            filenameAssociations.put(unitFile, fileName);
        }

        File filenameAssociationsFile = new File(dir, FILENAME_ASSOCIATIONS);
        try (
            var fos = new FileOutputStream(filenameAssociationsFile);
            var ps = new PrintStream(fos)
        ) {
            for (var entry : filenameAssociations.entrySet()) {
                ps.print(entry.getKey().getPath() + '\0' + entry.getValue() + '\0');
            }
        }

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

        var parts = Files.readString(Paths.get(db.getAbsolutePath())).split(Pattern.quote("\0"));
        for (int i = 0; i < parts.length; i += 2) {
            var entityFile = new File(parts[i]);

            var newFile = new File(entityFile.getAbsolutePath().replaceFirst("\\.tmp$", ""));
            FileUtils.copyFile(entityFile, newFile);

            var fileName = parts[i + 1];
            if (fileName.isBlank()) {
                fileName = "";
            }

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
                        logger.warn("Could not read meta file for entity file %s:%s".formatted(entityFile, fileName), e);
                    }
                }

                var editor = UiLoader.getUI(loadedUnit, fileName);
                editors.add(editor);
            } catch (EntityLoadingException e) {
                logger.warn("Could not restore tab for entity file %s:%s".formatted(entityFile, fileName), e);
            } finally {
                if (!newFile.delete()) {
                    logger.warn("Could not delete temporary file %s".formatted(newFile));
                }
            }
        }

        if (!clean.delete()) {
            logger.error("Could not mark tab state as dirty on load!");
        }

        return editors;
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
                Files.setAttribute(Paths.get(tabStateDir.getAbsolutePath()), "dos:hidden", true);
            }
        }

        return tabStateDir;
    }

    public static void loadMany(List<File> files, MenuBarOwner owner) {
        loadMany(
            files.stream().map( f -> {
                try {
                    return new MekFileParser(f).getEntity();
                } catch (EntityLoadingException e) {
                    logger.errorDialog(e, "Failed to load entity.", "Entity load error.");
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
            ProgressMonitor progress = new ProgressMonitor(tabbedUI, "Loading units...", "Loaded 0 / %d".formatted(entities.size()), 0, entities.size());
            SwingUtilities.invokeLater(() -> insertTabs(0, entities, fileNames, tabbedUI, progress));
        } else if (owner instanceof StartupGUI) {
            owner.getFrame().dispose();
            UiLoader.initializeFromBlankUI((tabbedUI) -> {
                ProgressMonitor progress = new ProgressMonitor(tabbedUI, "Loading units...", "Loaded 0 / %d".formatted(entities.size()), 0, entities.size());
                insertTabs(0, entities, fileNames, tabbedUI, progress);
            });
        }

    }

    private static void insertTabs(int i, List<Entity> entities, List<String> fileNames, MegaMekLabTabbedUI tabbedUI, ProgressMonitor progress) {
        if (entities.isEmpty()) {
            return;
        }
        var newUnit = entities.get(i);
        try {
            tabbedUI.addUnit(newUnit, fileNames.get(i),  i == 0);
            String validationResult = UnitUtil.validateUnit(newUnit);
            if (!validationResult.isBlank()) {
                PopupMessages.showUnitInvalidWarning(tabbedUI.getFrame(), validationResult);
            }

            CConfig.setMostRecentFile(fileNames.get(i));
        } catch (Exception e) {
            logger.errorDialog(e, "Failed to load the unit %s.", "Entity load error.", newUnit.getDisplayName());
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
