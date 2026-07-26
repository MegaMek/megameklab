/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Objects;
import java.util.function.BiFunction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.logging.MMLogger;
import megameklab.ui.FileNameManager;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

public class MegaMekLabFileSaver {
    private static final String LICENSE_HEADER = """
          # MegaMek Data (C) %s by The MegaMek Team is licensed under CC BY-NC-SA 4.0.
          # To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-sa/4.0/
          #
          # NOTICE: The MegaMek organization is a non-profit group of volunteers
          # creating free software for the BattleTech community.
          #
          # MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
          # of The Topps Company, Inc. All Rights Reserved.
          #
          # Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
          # InMediaRes Productions, LLC.
          #
          # MechWarrior Copyright Microsoft Corporation. MegaMek Data was created under
          # Microsoft's "Game Content Usage Rules"
          # <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
          # affiliated with Microsoft.
          """.formatted(Calendar.getInstance().get(Calendar.YEAR));


    private static final MMLogger STATIC_LOGGER = MMLogger.create(MegaMekLabFileSaver.class);

    private final MMLFileChooser saveUnitFileChooser = new MMLFileChooser();
    private final MMLogger logger;
    /** The UUID conflict choice from the most recent save that hit a conflict, so a linked sidecar can follow it. */
    private PopupMessages.UnitFileUUIDChoice lastUuidConflictChoice;

    public MegaMekLabFileSaver(MMLogger mainLogger, String dialogTitle) {
        logger = mainLogger;
        saveUnitFileChooser.setDialogTitle(dialogTitle);
    }

    /**
     * Constructs a file name for the current Entity using the chassis and model name and the correct extension for the
     * unit type. Any character that is not legal for a Windows filename is replaced by an underscore.
     *
     * @param entity The Entity
     *
     * @return A default filename for the Entity
     */
    public static String createUnitFilename(Entity entity) {
        String fileName = (entity.getChassis() + ' ' + entity.getModel()).trim();
        fileName = fileName.replaceAll("[/\\\\<>:\"|?*]", "_");
        if (entity instanceof BattlefieldSupportAsset) {
            return fileName + ".bfs";
        }
        return fileName + ((entity instanceof Mek) ? ".mtf" : ".blk");
    }

    /**
     * Tries to save the unit directly to its file, if it has a filename already. If it hasn't, it performs a Save
     * As...
     *
     */
    public String saveUnit(JFrame ownerFrame, FileNameManager fileNameManager, Entity entity) {
        String filePathName = fileNameManager.getFileName();
        // For safety, save automatically only to .mtf, .blk or .bfs files, otherwise ask
        if (!(filePathName.endsWith(".mtf") || filePathName.endsWith(".blk") || filePathName.endsWith(".bfs"))
              || !new File(filePathName).exists()
              || fileNameManager.hasEntityNameChanged()) {
            File selectedFile = chooseSaveFile(ownerFrame, entity);
            if (selectedFile == null) {
                return null;
            }
            return saveUnitAsTo(ownerFrame, selectedFile, entity);
        }

        CConfig.setMostRecentFile(filePathName);
        return saveUnitTo(ownerFrame, new File(filePathName), entity);
    }

    public String saveUnitAs(JFrame ownerFrame, Entity entity) {

        File saveFile = chooseSaveFile(ownerFrame, entity);
        if (saveFile != null) {
            return saveUnitAsTo(ownerFrame, saveFile, entity);
        }
        return null;
    }

    String saveUnitAsTo(JFrame ownerFrame, File saveFile, Entity entity) {
        String previousUUID = entity.getUnitFileUUID();
        if (!prepareUnitFileUUID(ownerFrame, saveFile, entity)) {
            return null;
        }
        String savedFile = saveUnitTo(ownerFrame, saveFile, entity);
        if (savedFile == null) {
            entity.setUnitFileUUID(previousUUID);
        } else {
            entity.storeSavedUnitData();
            CConfig.setMostRecentFile(saveFile.toString());
        }
        return savedFile;
    }

    private boolean prepareUnitFileUUID(JFrame ownerFrame, File saveFile, Entity entity) {
        lastUuidConflictChoice = resolveUnitFileUUID(saveFile, entity,
              (currentUnit, targetUnit) -> PopupMessages.showUnitFileUUIDConflict(ownerFrame, currentUnit, targetUnit));
        return lastUuidConflictChoice != PopupMessages.UnitFileUUIDChoice.CANCEL;
    }

    /**
     * Resolves and applies the unit-file UUID to {@code entity} for a save to {@code saveFile}, following the standard
     * rules: adopt an overwritten file's UUID when the names match; keep the UUID when the unit already matches that
     * UUID or its name is unchanged; on a name/UUID conflict against an existing file defer to {@code conflictResolver};
     * otherwise regenerate (a renamed/new unit).
     *
     * @param saveFile         the file the unit is about to be saved to
     * @param entity           the unit whose UUID is resolved (and mutated)
     * @param conflictResolver invoked (with the current unit and the existing target unit) only when a name/UUID
     *                         conflict must be resolved
     *
     * @return the choice used to resolve a conflict (for reuse by a linked sidecar), or {@code null} when no conflict
     *       arose; a returned {@link PopupMessages.UnitFileUUIDChoice#CANCEL} means the save should abort
     */
    static PopupMessages.UnitFileUUIDChoice resolveUnitFileUUID(File saveFile, Entity entity,
          BiFunction<Entity, Entity, PopupMessages.UnitFileUUIDChoice> conflictResolver) {
        Entity targetUnit = readExistingUnit(saveFile);
        if (hasOriginalUnitIdentity(targetUnit)) {
            String targetUUID = targetUnit.getOriginalUnitFileUUID();
            boolean namesMatch = Objects.equals(entity.getChassis(), targetUnit.getOriginalChassis())
                  && Objects.equals(entity.getModel(), targetUnit.getOriginalModel());
            if (namesMatch) {
                entity.setUnitFileUUID(targetUUID);
                return null;
            }

            if (Objects.equals(entity.getUnitFileUUID(), targetUUID)) {
                return null;
            }

            if (hasOriginalUnitIdentity(entity)) {
                PopupMessages.UnitFileUUIDChoice choice = conflictResolver.apply(entity, targetUnit);
                if (choice == PopupMessages.UnitFileUUIDChoice.TARGET) {
                    entity.setUnitFileUUID(targetUUID);
                }
                return choice;
            }
        }

        if (hasOriginalUnitIdentity(entity)
              && Objects.equals(entity.getChassis(), entity.getOriginalChassis())
              && Objects.equals(entity.getModel(), entity.getOriginalModel())) {
            return null;
        }

        entity.regenerateUnitFileUUID();
        return null;
    }

    /**
     * Resolves and applies the unit-file UUID to a linked Battlefield Support Asset about to be written to its sidecar
     * {@code sidecarFile}, using the <em>same</em> rules as the base unit (see
     * {@link #resolveUnitFileUUID(File, Entity, BiFunction)}). A name/UUID conflict is resolved non-interactively with
     * {@code baseConflictChoice} — the choice the user already made for the base unit — so the asset follows the base's
     * decision without a second prompt; when the base made no conflict choice, the asset keeps its current UUID on a
     * conflict. This keeps the asset's own UUID lifecycle in lockstep with its base unit (e.g. both regenerate when the
     * unit is renamed to a new file, so no two assets share a UUID).
     *
     * @param sidecarFile        the {@code .bfs} file the asset is about to be written to
     * @param asset              the asset whose UUID is resolved (and mutated)
     * @param baseConflictChoice the conflict choice made for the base unit, or {@code null} if there was no conflict
     */
    public static void prepareLinkedAssetUnitFileUUID(File sidecarFile, BattlefieldSupportAsset asset,
          @Nullable PopupMessages.UnitFileUUIDChoice baseConflictChoice) {
        PopupMessages.UnitFileUUIDChoice effective =
              (baseConflictChoice == null) ? PopupMessages.UnitFileUUIDChoice.CURRENT : baseConflictChoice;
        resolveUnitFileUUID(sidecarFile, asset, (currentUnit, targetUnit) -> effective);
    }

    /**
     * @return the conflict choice made during the most recent {@link #saveUnit}/{@link #saveUnitAs} that resolved a
     *       unit-file UUID conflict, or {@code null} if the last save had no conflict. Used so a linked asset sidecar
     *       can follow the base unit's UUID decision.
     */
    public @Nullable PopupMessages.UnitFileUUIDChoice getLastUnitFileUUIDConflictChoice() {
        return lastUuidConflictChoice;
    }

    private static @Nullable Entity readExistingUnit(File file) {
        if (!file.isFile()) {
            return null;
        }
        try {
            return new MekFileParser(file).getEntity();
        } catch (Exception ex) {
            STATIC_LOGGER.warn("Unable to read existing unit file {} before Save As.", file, ex);
            return null;
        }
    }

    private static boolean hasOriginalUnitIdentity(@Nullable Entity entity) {
        return entity != null
              && entity.getOriginalChassis() != null
              && entity.getOriginalModel() != null
              && entity.getOriginalUnitFileUUID() != null;
    }

    // Replace owner class with EntitySource... somehow.
    private @Nullable File chooseSaveFile(JFrame ownerFrame, Entity entity) {
        if (entity instanceof BattlefieldSupportAsset) {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Asset files", "bfs"));
        } else if (entity instanceof Mek) {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Mek files", "mtf"));
        } else {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files", "blk"));
        }
        saveUnitFileChooser.setSelectedFile(new File(createUnitFilename(entity)));
        int result = saveUnitFileChooser.showSaveDialog(ownerFrame);
        if ((result != JFileChooser.APPROVE_OPTION) || (saveUnitFileChooser.getSelectedFile() == null)) {
            return null;
        } else {
            return saveUnitFileChooser.getSelectedFile();
        }
    }

    private String saveUnitTo(JFrame ownerFrame, File file, Entity entity) {
        if (entity == null) {
            return null;
        }
        try {
            writeUnitToFile(file, entity);
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(ownerFrame, ex.getMessage());
            logger.error("", ex);
            return null;
        }

        try {
            PopupMessages.showUnitSavedMessage(ownerFrame, entity, file);
        } catch (Exception ex) {
            logger.error("Unable to show unit saved message", ex);
        }
        return file.toString();
    }

    /**
     * Writes the given unit to the given file (including the license header when configured), with no dialogs. Used
     * both by the interactive save and by linked Battlefield Support Asset sidecar writing.
     *
     * @param file   the destination file
     * @param entity the unit to write
     *
     * @throws java.io.IOException if the file cannot be written
     */
    public static void writeUnitToFile(File file, Entity entity) throws java.io.IOException {
        String content = (CConfig.includeLicense() ? LICENSE_HEADER + System.lineSeparator() : "")
              + UnitUtil.saveUnitToString(entity, true) + System.lineSeparator();
        Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
    }
}
