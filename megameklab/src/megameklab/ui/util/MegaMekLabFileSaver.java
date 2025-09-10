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
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.logging.MMLogger;
import megameklab.ui.FileNameManager;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.util.CConfig;

public class MegaMekLabFileSaver {

    private final MMLFileChooser saveUnitFileChooser = new MMLFileChooser();
    private final MMLogger logger;

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
        return fileName + ((entity instanceof Mek) ? ".mtf" : ".blk");
    }

    /**
     * Tries to save the unit directly to its file, if it has a filename already. If it hasn't, it performs a Save
     * As...
     *
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

            PopupMessages.showUnitSavedMessage(ownerFrame, entity, file);
            return file.toString();
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(ownerFrame, ex.getMessage());
            logger.error("", ex);
            return null;
        }
    }
}
