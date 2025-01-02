package megameklab.ui.util;

import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

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
    public boolean saveUnit(MegaMekLabMainUI owner, Entity entity) {
        UnitUtil.compactCriticals(entity);
        owner.refreshAll(); // The crits may have moved

        String filePathName = owner.getFileName();
        // For safety, save automatically only to .mtf or .blk files, otherwise ask
        if (!(filePathName.endsWith(".mtf") || filePathName.endsWith(".blk"))
            || !new File(filePathName).exists()
            || owner.hasEntityNameChanged()) {
            File selectedFile = chooseSaveFile(owner, entity);
            if (selectedFile == null) {
                return false;
            }

            filePathName = selectedFile.getPath();
        }

        CConfig.setMostRecentFile(filePathName);
        return saveUnitTo(owner, new File(filePathName), entity);
    }

    public void saveUnitAs(MegaMekLabMainUI owner, Entity entity) {
        UnitUtil.compactCriticals(entity);
        owner.refreshAll(); // The crits may have moved

        File saveFile = chooseSaveFile(owner, entity);
        if (saveFile != null) {
            CConfig.setMostRecentFile(saveFile.toString());
            saveUnitTo(owner, saveFile, entity);
        }
    }

    private @Nullable File chooseSaveFile(MegaMekLabMainUI owner, Entity entity) {
        if (entity instanceof Mek) {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Mek files", "mtf"));
        } else {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files", "blk"));
        }
        saveUnitFileChooser.setSelectedFile(new File(createUnitFilename(owner.getEntity())));
        int result = saveUnitFileChooser.showSaveDialog(owner.getFrame());
        if ((result != JFileChooser.APPROVE_OPTION) || (saveUnitFileChooser.getSelectedFile() == null)) {
            return null;
        } else {
            return saveUnitFileChooser.getSelectedFile();
        }
    }

    private boolean saveUnitTo(MegaMekLabMainUI owner, File file, Entity entity) {
        if (entity == null) {
            return false;
        }
        try {
            if (entity instanceof Mek) {
                try (FileOutputStream fos = new FileOutputStream(file);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mek) owner.getEntity()).getMtf());
                }
            } else {
                BLKFile.encode(file.getPath(), entity);
            }
            PopupMessages.showUnitSavedMessage(owner.getFrame(), entity, file);
            owner.setFileName(file.toString());
            return true;
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(owner.getFrame(), ex.getMessage());
            logger.error("", ex);
            return false;
        }
    }
}
