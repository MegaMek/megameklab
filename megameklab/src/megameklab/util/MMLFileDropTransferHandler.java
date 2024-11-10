package megameklab.util;

import megamek.logging.MMLogger;
import megameklab.ui.MenuBarOwner;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

public class MMLFileDropTransferHandler extends TransferHandler {
    private static final MMLogger logger = MMLogger.create(MMLFileDropTransferHandler.class);
    private final MenuBarOwner owner;

    public MMLFileDropTransferHandler(MenuBarOwner owner) {
        this.owner = owner;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }


    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            var files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            if (files.size() != 1) {
                logger.error("Cannot open multiple files at a time!", "Import error");
                return false;
            }

            var file = files.get(0);
            var name = file.getName();

            if (name.toLowerCase().endsWith(".mtf") || name.toLowerCase().endsWith(".blk")) {
                owner.getMMLMenuBar().loadFile(file);
                return true;
            } else if (name.toLowerCase().endsWith(".mul")) {
                UnitPrintManager.printMUL(owner.getFrame(), CConfig.getBooleanParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR), file);
                return true;
            } else {
                logger.error("Can only open files of type .mtf, .blk, and .mul", "Import error");
                return false;
            }


        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
}
