/*
 * MegaMek - Copyright (C) 2024 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.ui.util;

import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.logging.MMLogger;
import megameklab.ui.FileNameManager;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.util.CConfig;
import megameklab.util.EntityChangedUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MegaMekLabFileSaver {

    private final MMLFileChooser saveUnitFileChooser = new MMLFileChooser();
    private final MMLogger logger;

    public MegaMekLabFileSaver(MMLogger mainLogger, String dialogTitle) {
        logger = mainLogger;
        saveUnitFileChooser.setDialogTitle(dialogTitle);
    }

    /**
     * Constructs a file name for the current Entity using the chassis and model
     * name and the
     * correct extension for the unit type. Any character that is not legal for a
     * Windows filename
     * is replaced by an underscore.
     *
     * @param entity The Entity
     * @return A default filename for the Entity
     */
    public static String createUnitFilename(Entity entity) {
        String fileName = (entity.getChassis() + ' ' + entity.getModel()).trim();
        fileName = fileName.replaceAll("[/\\\\<>:\"|?*]", "_");
        return fileName + ((entity instanceof Mek) ? ".mtf" : ".blk");
    }

    /**
     * Tries to save the unit directly to its file, if it has a filename already. If
     * it hasn't, it performs a Save As... Returns true when it successfully saves
     * the
     * unit, false if not.
     *
     * @return True when the unit was actually saved, false otherwise
     */
    public String saveUnit(JFrame ownerFrame, FileNameManager fileNameManager, Entity entity) {
        String filePathName = fileNameManager.getFileName();
        // For safety, save automatically only to .mtf or .blk files, otherwise ask
        if (!(filePathName.endsWith(".mtf") || filePathName.endsWith(".blk"))
            || !new File(filePathName).exists()
            || fileNameManager.hasEntityNameChanged()) {
            File selectedFile = chooseSaveFile(ownerFrame, entity);
            if (selectedFile == null) {
                return null;
            }
            filePathName = selectedFile.getPath();
        }

        CConfig.setMostRecentFile(filePathName);
        return saveUnitTo(ownerFrame, new File(filePathName), entity);
    }

    public String saveUnitAs(JFrame ownerFrame, Entity entity) {

        File saveFile = chooseSaveFile(ownerFrame, entity);
        if (saveFile != null) {
            CConfig.setMostRecentFile(saveFile.toString());
            return saveUnitTo(ownerFrame, saveFile, entity);
        }
        return null;
    }

    // Replace owner class with EntitySource... somehow.
    private @Nullable File chooseSaveFile(JFrame ownerFrame, Entity entity) {
        if (entity instanceof Mek) {
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
            if (entity instanceof Mek) {
                try (FileOutputStream fos = new FileOutputStream(file);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mek) entity).getMtf());
                }
            } else {
                BLKFile.encode(file.getPath(), entity);
            }

            if (ownerFrame instanceof MegaMekLabMainUI mui) {
                // Since we've saved the entity, update the entity being compared against to determine if the user has unsaved work.
                EntityChangedUtil.editorSaved(mui);
            }

            PopupMessages.showUnitSavedMessage(ownerFrame, entity, file);
            return file.toString();
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(ownerFrame, ex.getMessage());
            logger.error("", ex);
            return null;
        }
    }
}
