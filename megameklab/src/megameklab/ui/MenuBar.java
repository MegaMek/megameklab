/*
 * MegaMekLab
 * Copyright (c) 2011-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.ui;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.common.templates.TROView;
import megamek.common.util.EncodeControl;
import megameklab.MMLConstants;
import megameklab.ui.dialog.LoadingDialog;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import megameklab.ui.dialog.settings.SettingsDialog;
import megameklab.util.CConfig;
import megameklab.util.ImageHelper;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * @author jtighe (torren@users.sourceforge.net)
 */
public class MenuBar extends JMenuBar implements ClipboardOwner {
    //region Variable Declarations
    private final MegaMekLabMainUI frame;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu", new EncodeControl());
    //endregion Variable Declarations

    //region Constructors
    public MenuBar(final MegaMekLabMainUI frame) {
        this.frame = frame;
        initialize();
    }
    //endregion Constructors

    //region Getters
    public MegaMekLabMainUI getFrame() {
        return frame;
    }
    //endregion Getters

    //region Initialization
    /**
     * The main menu uses the following Mnemonic keys as of 27-MAR-2022:
     * F, H, U
     */
    private void initialize() {
        getAccessibleContext().setAccessibleName("Main Menu");
        add(createFileMenu());
        add(createUnitValidationMenu());
        add(createReportsMenu());
        add(createHelpMenu());
        add(createUnsortedMenu());
    }

    //region File Menu
    /**
     * The File menu uses the following Mnemonic keys as of 27-MAR-2022:
     *
     * @return the created file menu
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(createLoadMenu());
        fileMenu.add(createImportMenu());
        fileMenu.add(createExportMenu());
        fileMenu.add(createRefreshMenu());
        fileMenu.add(createOptionsMenu());
        fileMenu.add(createThemesMenu());
        fileMenu.addSeparator();

        final JMenuItem miExit = new JMenuItem();
        miExit.setText(resources.getString("miExit.text"));
        miExit.setMnemonic(KeyEvent.VK_E);
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
        miExit.addActionListener(evt -> getFrame().exit());
        fileMenu.add(miExit);

        return fileMenu;
    }

    private JMenu createLoadMenu() {
        final JMenu loadMenu = new JMenu(resources.getString("loadMenu.text"));
        loadMenu.setName("loadMenu");
        loadMenu.setMnemonic(KeyEvent.VK_L);

        final JMenuItem miLoadUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miLoadUnitFromCache.setName("miLoadUnitFromCache");
        miLoadUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miLoadUnitFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miLoadUnitFromCache.addActionListener(evt -> loadUnit());
        loadMenu.add(miLoadUnitFromCache);

        final JMenuItem miLoadUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miLoadUnitFromFile.setName("miLoadUnitFromFile");
        miLoadUnitFromFile.setMnemonic(KeyEvent.VK_F);
        miLoadUnitFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miLoadUnitFromFile.addActionListener(evt -> loadUnitFromFile(-1));
        loadMenu.add(miLoadUnitFromFile);

        return loadMenu;
    }

    private JMenu createImportMenu() {
        final JMenu importMenu = new JMenu(resources.getString("importMenu.text"));
        importMenu.setName("importMenu");
        importMenu.setMnemonic(KeyEvent.VK_I);

        return importMenu;
    }

    private JMenu createExportMenu() {
        final JMenu exportMenu = new JMenu(resources.getString("exportMenu.text"));
        exportMenu.setName("exportMenu");
        exportMenu.setMnemonic(KeyEvent.VK_X);

        exportMenu.add(createPDFUnitExportMenu());
        exportMenu.add(createHTMLUnitExportMenu());
        exportMenu.add(createTextUnitExportMenu());
        exportMenu.add(createClipboardUnitExportMenu());

        return exportMenu;
    }

    private JMenu createPDFUnitExportMenu() {
        final JMenu pdfUnitExportMenu = new JMenu(resources.getString("pdfUnitExportMenu.text"));
        pdfUnitExportMenu.setName("pdfUnitExportMenu");
        pdfUnitExportMenu.setMnemonic(KeyEvent.VK_P);

        final JMenuItem miExportCurrentUnitToPDF = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToPDF.setName("miExportCurrentUnitToPDF");
        miExportCurrentUnitToPDF.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportCurrentUnitToPDF.addActionListener(evt -> UnitPrintManager.exportEntity(getFrame().getEntity(), getFrame()));
        pdfUnitExportMenu.add(miExportCurrentUnitToPDF);

        final JMenuItem miExportUnitFromCacheToPDF = new JMenuItem(resources.getString("FromCache.text"));
        miExportUnitFromCacheToPDF.setName("miExportUnitFromCacheToPDF");
        miExportUnitFromCacheToPDF.setMnemonic(KeyEvent.VK_C);
        miExportUnitFromCacheToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miExportUnitFromCacheToPDF.addActionListener(evt -> UnitPrintManager.printSelectedUnit(getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitFromCacheToPDF);

        final JMenuItem miExportUnitFromFileToPDF = new JMenuItem(resources.getString("FromFile.text"));
        miExportUnitFromFileToPDF.setName("miExportUnitFromFileToPDF");
        miExportUnitFromFileToPDF.setMnemonic(KeyEvent.VK_F);
        miExportUnitFromFileToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miExportUnitFromFileToPDF.addActionListener(evt -> UnitPrintManager.printUnitFile(getFrame(), false, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToPDF);

        final JMenuItem miExportUnitFromFileToSinglePDFPage = new JMenuItem(resources.getString("miExportUnitFromFileToSinglePDFPage.text"));
        miExportUnitFromFileToSinglePDFPage.setName("miExportUnitFromFileToSinglePDFPage");
        miExportUnitFromFileToSinglePDFPage.setMnemonic(KeyEvent.VK_U);
        miExportUnitFromFileToSinglePDFPage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportUnitFromFileToSinglePDFPage.addActionListener(evt -> UnitPrintManager.printUnitFile(getFrame(), true, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToSinglePDFPage);

        final JMenuItem miExportUnitQueueToPDF = new JMenuItem(resources.getString("miExportUnitQueueToPDF.text"));
        miExportUnitQueueToPDF.setName("miExportUnitQueueToPDF");
        miExportUnitQueueToPDF.setMnemonic(KeyEvent.VK_Q);
        miExportUnitQueueToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        miExportUnitQueueToPDF.addActionListener(evt -> new PrintQueueDialog(getFrame(), true).setVisible(true));
        pdfUnitExportMenu.add(miExportUnitQueueToPDF);

        final JMenuItem miExportUnitsFromMULFileToPDF = new JMenuItem(resources.getString("miExportUnitsFromMULFileToPDF.text"));
        miExportUnitsFromMULFileToPDF.setName("miExportUnitsFromMULFileToPDF");
        miExportUnitsFromMULFileToPDF.setMnemonic(KeyEvent.VK_U);
        miExportUnitsFromMULFileToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportUnitsFromMULFileToPDF.addActionListener(evt -> UnitPrintManager.exportMUL(getFrame(), false));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToPDF);

        final JMenuItem miExportUnitsFromMULFileToSinglePDFPages = new JMenuItem(resources.getString("miExportUnitsFromMULFileToSinglePDFPages.text"));
        miExportUnitsFromMULFileToSinglePDFPages.setName("miExportUnitsFromMULFileToSinglePDFPages");
        miExportUnitsFromMULFileToSinglePDFPages.setMnemonic(KeyEvent.VK_U);
        miExportUnitsFromMULFileToSinglePDFPages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportUnitsFromMULFileToSinglePDFPages.addActionListener(evt -> UnitPrintManager.exportMUL(getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToSinglePDFPages);

        return pdfUnitExportMenu;
    }

    private JMenu createHTMLUnitExportMenu() {
        final JMenu htmlUnitExportMenu = new JMenu(resources.getString("htmlUnitExportMenu.text"));
        htmlUnitExportMenu.setName("htmlUnitExportMenu");
        htmlUnitExportMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem miExportCurrentUnitToHTML = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToHTML.setName("miExportCurrentUnitToHTML");
        miExportCurrentUnitToHTML.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToHTML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportCurrentUnitToHTML.addActionListener(evt -> exportSummary(true));
        htmlUnitExportMenu.add(miExportCurrentUnitToHTML);

        return htmlUnitExportMenu;
    }

    private JMenu createTextUnitExportMenu() {
        final JMenu textUnitExportMenu = new JMenu(resources.getString("textUnitExportMenu.text"));
        textUnitExportMenu.setName("textUnitExportMenu");
        textUnitExportMenu.setMnemonic(KeyEvent.VK_T);

        final JMenuItem miExportCurrentUnitToText = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToText.setName("miExportCurrentUnitToText");
        miExportCurrentUnitToText.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miExportCurrentUnitToText.addActionListener(evt -> exportSummary(false));
        textUnitExportMenu.add(miExportCurrentUnitToText);

        return textUnitExportMenu;
    }

    private JMenu createClipboardUnitExportMenu() {
        final JMenu clipboardUnitExportMenu = new JMenu(resources.getString("clipboardUnitExportMenu.text"));
        clipboardUnitExportMenu.setName("clipboardUnitExportMenu");
        clipboardUnitExportMenu.setMnemonic(KeyEvent.VK_C);

        final JMenuItem miExportCurrentUnitToClipboard = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToClipboard.setName("miExportCurrentUnitToClipboard");
        miExportCurrentUnitToClipboard.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToClipboard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        miExportCurrentUnitToClipboard.addActionListener(evt -> {
            StringSelection stringSelection = new StringSelection(entitySummaryText(false));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, this);
        });
        clipboardUnitExportMenu.add(miExportCurrentUnitToClipboard);

        return clipboardUnitExportMenu;
    }

    private JMenu createRefreshMenu() {
        final JMenu refreshMenu = new JMenu(resources.getString("refreshMenu.text"));
        refreshMenu.setName("refreshMenu");
        refreshMenu.setMnemonic(KeyEvent.VK_R);

        final JMenuItem miRefreshUnitCache = new JMenuItem(resources.getString("miRefreshUnitCache.text"));
        miRefreshUnitCache.setName("miRefreshUnitCache");
        miRefreshUnitCache.setMnemonic(KeyEvent.VK_U);
        miRefreshUnitCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miRefreshUnitCache.addActionListener(evt -> MechSummaryCache.refreshUnitData(false));
        refreshMenu.add(miRefreshUnitCache);

        return refreshMenu;
    }

    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu(resources.getString("optionsMenu.text"));
        optionsMenu.setName("optionsMenu");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        final JMenuItem miConfiguration = new JMenuItem(resources.getString("miConfiguration.text"));
        miConfiguration.setName("miConfiguration");
        miConfiguration.setMnemonic(KeyEvent.VK_C);
        miConfiguration.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miConfiguration.addActionListener(evt -> {
            new SettingsDialog(getFrame()).setVisible(true);
            getFrame().refreshAll();
        });
        optionsMenu.add(miConfiguration);

        int fileNumber = 1;

        final JMenuItem miCConfig1 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_1, fileNumber++);
        if (miCConfig1 != null) {
            optionsMenu.add(miCConfig1);
        }

        final JMenuItem miCConfig2 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_2, fileNumber++);
        if (miCConfig2 != null) {
            optionsMenu.add(miCConfig2);
        }

        final JMenuItem miCConfig3 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_3, fileNumber++);
        if (miCConfig3 != null) {
            optionsMenu.add(miCConfig3);
        }

        final JMenuItem miCConfig4 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_4, fileNumber++);
        if (miCConfig4 != null) {
            optionsMenu.add(miCConfig4);
        }

        return optionsMenu;
    }

    private @Nullable JMenuItem createCConfigMenuItem(final String configSaveFile,
                                                      final int fileNumber) {
        final String newFile = CConfig.getParam(configSaveFile);
        if (newFile.length() < 1) {
            return null;
        }
        final JMenuItem miCConfig;
        if (newFile.length() > 35) {
            miCConfig = new JMenuItem(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
        } else {
            miCConfig = new JMenuItem(fileNumber + ". " + newFile);
        }
        miCConfig.setName("miCConfig");
        miCConfig.addActionListener(evt -> loadUnitFromFile(fileNumber));
        return miCConfig;
    }

    /**
     * Creates a menu that includes all installed look and feel options
     *
     * @return The new menu
     */
    private JMenu createThemesMenu() {
        final JMenu themesMenu = new JMenu(resources.getString("themesMenu.text"));
        themesMenu.setName("themesMenu");
        themesMenu.setMnemonic(KeyEvent.VK_T);

        for (final LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            final JCheckBoxMenuItem miLookAndFeel = new JCheckBoxMenuItem(laf.getName());
            miLookAndFeel.setName("miLookAndFeel");
            miLookAndFeel.setSelected(laf.getName().equalsIgnoreCase(UIManager.getLookAndFeel().getName()));
            miLookAndFeel.addActionListener(evt -> {
                getFrame().changeTheme(laf);
                for (int i = 0; i < themesMenu.getItemCount(); i++) {
                    final JMenuItem item = themesMenu.getItem(i);
                    if (item instanceof JCheckBoxMenuItem) {
                        item.setSelected(item.getText().equals(laf.getName()));
                    }
                }
            });
            themesMenu.add(miLookAndFeel);
        }

        return themesMenu;
    }
    //endregion File Menu

    //region Unit Validation Menu
    private JMenu createUnitValidationMenu() {
        final JMenu unitValidationMenu = new JMenu(resources.getString("unitValidationMenu.text"));
        unitValidationMenu.setName("unitValidationMenu");
        unitValidationMenu.setMnemonic(KeyEvent.VK_U);

        final JMenuItem miValidateCurrentUnit = new JMenuItem(resources.getString("CurrentUnit.text"));
        miValidateCurrentUnit.setName("miValidateCurrentUnit");
        miValidateCurrentUnit.setMnemonic(KeyEvent.VK_U);
        miValidateCurrentUnit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miValidateCurrentUnit.addActionListener(evt -> UnitUtil.showValidation(getFrame().getEntity(), getFrame()));
        unitValidationMenu.add(miValidateCurrentUnit);

        final JMenuItem miValidateUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miValidateUnitFromCache.setName("miValidateUnitFromCache");
        miValidateUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miValidateUnitFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miValidateUnitFromCache.addActionListener(evt -> jMenuGetUnitValidationFromCache_actionPerformed());
        unitValidationMenu.add(miValidateUnitFromCache);

        final JMenuItem miValidateUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miValidateUnitFromFile.setName("miValidateUnitFromFile");
        miValidateUnitFromFile.setMnemonic(KeyEvent.VK_F);
        miValidateUnitFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miValidateUnitFromFile.addActionListener(evt -> jMenuGetUnitValidationFromFile_actionPerformed());
        unitValidationMenu.add(miValidateUnitFromFile);

        return unitValidationMenu;
    }
    //endregion Unit Validation Menu

    //region Reports Menu
    private JMenu createReportsMenu() {
        final JMenu reportsMenu = new JMenu(resources.getString("reportsMenu.text"));
        reportsMenu.setName("reportsMenu");
        reportsMenu.setMnemonic(KeyEvent.VK_U);

        reportsMenu.add(createUnitSpecsReportMenu());
        reportsMenu.add(createUnitBVBreakdownMenu());
        reportsMenu.add(createUnitCostBreakdownMenu());
        reportsMenu.add(createUnitWeightBreakdownMenu());

        return reportsMenu;
    }

    private JMenu createUnitSpecsReportMenu() {
        final JMenu unitSpecsReportMenu = new JMenu(resources.getString("unitSpecsReportMenu.text"));
        unitSpecsReportMenu.setName("unitSpecsReportMenu");
        unitSpecsReportMenu.setMnemonic(KeyEvent.VK_S);

        final JMenuItem miCurrentUnitSpecs = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitSpecs.setName("miCurrentUnitSpecs");
        miCurrentUnitSpecs.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitSpecs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miCurrentUnitSpecs.addActionListener(evt -> UnitUtil.showUnitSpecs(getFrame().getEntity(), getFrame()));
        unitSpecsReportMenu.add(miCurrentUnitSpecs);

        final JMenuItem miUnitSpecsFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitSpecsFromCache.setName("miUnitSpecsFromCache");
        miUnitSpecsFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitSpecsFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miUnitSpecsFromCache.addActionListener(evt -> jMenuGetUnitSpecsFromCache_actionPerformed());
        unitSpecsReportMenu.add(miUnitSpecsFromCache);

        final JMenuItem miUnitSpecsFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitSpecsFromFile.setName("miUnitSpecsFromFile");
        miUnitSpecsFromFile.setMnemonic(KeyEvent.VK_F);
        miUnitSpecsFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miUnitSpecsFromFile.addActionListener(evt -> jMenuGetUnitSpecsFromFile_actionPerformed());
        unitSpecsReportMenu.add(miUnitSpecsFromFile);

        return unitSpecsReportMenu;
    }

    private JMenu createUnitBVBreakdownMenu() {
        final JMenu unitBVBreakdownMenu = new JMenu(resources.getString("unitBVBreakdownMenu.text"));
        unitBVBreakdownMenu.setName("unitBVBreakdownMenu");
        unitBVBreakdownMenu.setMnemonic(KeyEvent.VK_B);

        final JMenuItem miCurrentUnitBVBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitBVBreakdown.setName("miCurrentUnitBVBreakdown");
        miCurrentUnitBVBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitBVBreakdown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miCurrentUnitBVBreakdown.addActionListener(evt -> UnitUtil.showBVCalculations(getFrame(), getFrame().getEntity()));
        unitBVBreakdownMenu.add(miCurrentUnitBVBreakdown);

        final JMenuItem miUnitBVBreakdownFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitBVBreakdownFromCache.setName("miUnitBVBreakdownFromCache");
        miUnitBVBreakdownFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitBVBreakdownFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miUnitBVBreakdownFromCache.addActionListener(evt -> jMenuGetUnitBVFromCache_actionPerformed());
        unitBVBreakdownMenu.add(miUnitBVBreakdownFromCache);

        final JMenuItem miUnitBVBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitBVBreakdownFromFile.setName("miUnitBVBreakdownFromFile");
        miUnitBVBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
        miUnitBVBreakdownFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miUnitBVBreakdownFromFile.addActionListener(evt -> jMenuGetUnitBVFromFile_actionPerformed());
        unitBVBreakdownMenu.add(miUnitBVBreakdownFromFile);

        return unitBVBreakdownMenu;
    }

    private JMenu createUnitCostBreakdownMenu() {
        final JMenu unitCostBreakdownMenu = new JMenu(resources.getString("unitCostBreakdownMenu.text"));
        unitCostBreakdownMenu.setName("unitCostBreakdownMenu");
        unitCostBreakdownMenu.setMnemonic(KeyEvent.VK_C);

        final JMenuItem miCurrentUnitCostBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitCostBreakdown.setName("miCurrentUnitCostBreakdown");
        miCurrentUnitCostBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitCostBreakdown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miCurrentUnitCostBreakdown.addActionListener(evt -> UnitUtil.showUnitCostBreakDown(getFrame(), getFrame().getEntity()));
        unitCostBreakdownMenu.add(miCurrentUnitCostBreakdown);

        final JMenuItem miUnitCostBreakdownFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitCostBreakdownFromCache.setName("miUnitCostBreakdownFromCache");
        miUnitCostBreakdownFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitCostBreakdownFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miUnitCostBreakdownFromCache.addActionListener(evt -> jMenuGetUnitBreakdownFromCache_actionPerformed());
        unitCostBreakdownMenu.add(miUnitCostBreakdownFromCache);

        final JMenuItem miUnitCostBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitCostBreakdownFromFile.setName("miUnitCostBreakdownFromFile");
        miUnitCostBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
        miUnitCostBreakdownFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miUnitCostBreakdownFromFile.addActionListener(evt -> jMenuGetUnitBreakdownFromFile_actionPerformed());
        unitCostBreakdownMenu.add(miUnitCostBreakdownFromFile);

        return unitCostBreakdownMenu;
    }

    private JMenu createUnitWeightBreakdownMenu() {
        final JMenu unitWeightBreakdownMenu = new JMenu(resources.getString("unitWeightBreakdownMenu.text"));
        unitWeightBreakdownMenu.setName("unitWeightBreakdownMenu");
        unitWeightBreakdownMenu.setMnemonic(KeyEvent.VK_W);

        final JMenuItem miCurrentUnitWeightBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitWeightBreakdown.setName("miCurrentUnitWeightBreakdown");
        miCurrentUnitWeightBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitWeightBreakdown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miCurrentUnitWeightBreakdown.addActionListener(evt -> UnitUtil.showUnitWeightBreakDown(getFrame().getEntity(), getFrame()));
        unitWeightBreakdownMenu.add(miCurrentUnitWeightBreakdown);

        final JMenuItem miUnitWeightBreakdownFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitWeightBreakdownFromCache.setName("miUnitWeightBreakdownFromCache");
        miUnitWeightBreakdownFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitWeightBreakdownFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miUnitWeightBreakdownFromCache.addActionListener(evt -> jMenuGetUnitWeightBreakdownFromCache_actionPerformed());
        unitWeightBreakdownMenu.add(miUnitWeightBreakdownFromCache);

        final JMenuItem miUnitWeightBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitWeightBreakdownFromFile.setName("miUnitWeightBreakdownFromFile");
        miUnitWeightBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
        miUnitWeightBreakdownFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miUnitWeightBreakdownFromFile.addActionListener(evt -> jMenuGetUnitWeightBreakdownFromFile_actionPerformed());
        unitWeightBreakdownMenu.add(miUnitWeightBreakdownFromFile);

        return unitWeightBreakdownMenu;
    }
    //endregion Reports Menu

    //region Help Menu
    private JMenu createHelpMenu() {
        final JMenu helpMenu = new JMenu(resources.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem miAbout = new JMenuItem(resources.getString("miAbout.text"));
        miAbout.setName("miAbout");
        miAbout.setMnemonic(KeyEvent.VK_A);
        miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        miAbout.addActionListener(evt -> aboutAction());
        helpMenu.add(miAbout);

        final JMenuItem miRecordSheetImages = new JMenuItem(resources.getString("miRecordSheetImages.text"));
        miRecordSheetImages.setName("miRecordSheetImages");
        miRecordSheetImages.setMnemonic(KeyEvent.VK_R);
        miRecordSheetImages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
        miRecordSheetImages.addActionListener(evt -> recordSheetImagesAction());
        helpMenu.add(miRecordSheetImages);

        return helpMenu;
    }
    //endregion Help Menu

    //region Unsorted Menu
    private JMenu createUnsortedMenu() {
        final JMenu unsortedMenu = new JMenu(resources.getString("unsortedMenu.text"));
        unsortedMenu.setName("unsortedMenu");

        final JMenuItem miInsertImage = new JMenuItem(resources.getString("miInsertImage.text"));
        miInsertImage.setName("miInsertImage");
        miInsertImage.setMnemonic(KeyEvent.VK_I);
        miInsertImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
        miInsertImage.addActionListener(evt -> insertImageAction());
        unsortedMenu.add(miInsertImage);

        return unsortedMenu;
    }
    //endregion Unsorted Menu
    //endregion Initialization

    public JMenu printMenu(final MegaMekLabMainUI parent) {
        JMenu printMenu = new JMenu(resources.getString("menu.file.print"));
        printMenu.setMnemonic(KeyEvent.VK_P);

        JMenuItem item = new JMenuItem(resources.getString("menu.file.print.currentUnit"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(evt -> UnitPrintManager.printEntity(parent.getEntity()));
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem(resources.getString("menu.file.print.queueUnits"));
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(evt -> new PrintQueueDialog(parent, false));

        printMenu.add(item);
        printMenu.addSeparator();

        item = new JMenuItem(resources.getString("menu.file.print.otherUnit"));
        item.setMnemonic(KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        item.addActionListener(evt -> UnitPrintManager.printSelectedUnit(parent, false));
        printMenu.add(item);

        item = new JMenuItem(resources.getString("menu.file.print.fromFile"));
        item.setMnemonic(KeyEvent.VK_I);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        item.addActionListener(evt -> UnitPrintManager.printUnitFile(parent, false, false));
        printMenu.add(item);

        item = new JMenuItem(resources.getString("menu.file.print.fromFileSingle"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        item.addActionListener(evt -> UnitPrintManager.printUnitFile(parent, true, false));
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem(resources.getString("menu.file.print.fromMUL"));
        item.setMnemonic(KeyEvent.VK_M);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        item.addActionListener(evt -> UnitPrintManager.printMUL(parent, false));
        printMenu.add(item);

        item = new JMenuItem(resources.getString("menu.file.print.fromMULSingle"));
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(evt -> UnitPrintManager.printMUL(parent, true));
        printMenu.add(item);

        return printMenu;
    }

    private JMenu oldCreateFileMenu() {
        JMenu fileMenu = new JMenu(resources.getString("menu.file"));
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem item = new JMenuItem(resources.getString("menu.file.resetCurrentUnit"));
        item.setMnemonic(KeyEvent.VK_R);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
        item.addActionListener(this::jMenuResetEntity_actionPerformed);
        fileMenu.add(item);

        JMenu unitMenu = new JMenu(resources.getString("menu.file.switchUnitType"));
        unitMenu.setMnemonic(KeyEvent.VK_S);
        Entity en = getFrame().getEntity();

        if (!(en instanceof Mech) || en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.mech"));
            item.setMnemonic(KeyEvent.VK_M);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, false, null));
            unitMenu.add(item);
        }

        if (!en.isFighter() || ((en instanceof Aero) && en.isPrimitive())) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.fighter"));
            item.setMnemonic(KeyEvent.VK_A);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, false, null));
            unitMenu.add(item);
        }

        if (!(en instanceof SmallCraft) || en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.dropshipSmallCraft"));
            item.setMnemonic(KeyEvent.VK_D);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, false, null));
            unitMenu.add(item);
        }

        if (!(en instanceof Jumpship) || en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.advancedAero"));
            item.setMnemonic(KeyEvent.VK_J);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP, false, null));
            unitMenu.add(item);
        }

        if (!(getFrame().getEntity() instanceof Tank)
                || getFrame().getEntity().isSupportVehicle()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.combatVehicle"));
            item.setMnemonic(KeyEvent.VK_T);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_TANK, false, null));
            unitMenu.add(item);
        }

        if (!getFrame().getEntity().isSupportVehicle()) {
            item = new JMenuItem();
            item.setText("Support Vehicle");
            item.addActionListener(evt -> newUnit(Entity.ETYPE_SUPPORT_TANK, false, null));
            unitMenu.add(item);
        }

        if (!(getFrame().getEntity() instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.battleArmor"));
            item.setMnemonic(KeyEvent.VK_B);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_BATTLEARMOR, false, null));
            unitMenu.add(item);
        }

        if (!getFrame().getEntity().isConventionalInfantry()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.infantry"));
            item.setMnemonic(KeyEvent.VK_I);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_INFANTRY, false, null));
            unitMenu.add(item);
        }
        
        if (!getFrame().getEntity().hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.protomech"));
            item.setMnemonic(KeyEvent.VK_P);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_PROTOMECH, false, null));
            unitMenu.add(item);
        }

        JMenu pMenu = new JMenu(resources.getString("menu.file.unitType.primitive"));
        if (!(en instanceof Mech) || !en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.mech"));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, true, null));
            pMenu.add(item);
        }

        if (!(en.isFighter()) || ((en instanceof Aero) && !en.isPrimitive())) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.aero"));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, true, null));
            pMenu.add(item);
        }

        if (!(en instanceof SmallCraft) || !en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.dropshipSmallCraft"));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, true, null));
            pMenu.add(item);
        }

        if (!(en instanceof Jumpship) || !en.isPrimitive()) {
            item = new JMenuItem();
            item.setText(resources.getString("menu.file.unitType.jumpship"));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP, true, null));
            pMenu.add(item);
        }

        unitMenu.add(pMenu);

        fileMenu.add(unitMenu);

        item = new JMenuItem();
        item.setText(resources.getString("menu.file.save"));
        item.setMnemonic(KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        item.addActionListener(this::jMenuSaveEntity_actionPerformed);
        fileMenu.add(item);

        item = new JMenuItem();
        item.setText(resources.getString("menu.file.saveAs"));
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        item.addActionListener(this::jMenuSaveAsEntity_actionPerformed);
        fileMenu.add(item);

        return fileMenu;
    }

    private void jMenuGetUnitBVFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);
        UnitUtil.showBVCalculations(getFrame(), viewer.getChosenEntity());
    }

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if (null == tempEntity) {
            return;
        }
        UnitUtil.showValidation(tempEntity, getFrame());
    }

    private void jMenuGetUnitSpecsFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if (null == tempEntity) {
            return;
        }
        UnitUtil.showUnitSpecs(tempEntity, getFrame());
    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);
        UnitUtil.showUnitCostBreakDown(getFrame(), viewer.getChosenEntity());
    }

    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if (null == tempEntity) {
            return;
        }
        UnitUtil.showUnitWeightBreakDown(tempEntity, getFrame());
    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            UnitUtil.showBVCalculations(getFrame(), new MechFileParser(unitFile).getEntity());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void jMenuGetUnitValidationFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showValidation(tempEntity, getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                    ex.getMessage()));
        }
    }

    private @Nullable File loadUnitFile() {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/"; // TODO : remove inline file path
        FileDialog fDialog = new FileDialog(getFrame(),
                resources.getString("dialog.chooseUnit.title"),
                FileDialog.LOAD);
        fDialog.setLocationRelativeTo(getFrame());
        fDialog.setMultipleMode(false);
        fDialog.setDirectory(filePathName);
        fDialog.setFilenameFilter((dir, filename) -> {
            String fn = filename.toLowerCase();
            return Stream.of(".mtf", ".blk", ".hmp").anyMatch(fn::endsWith);
        });

        fDialog.setVisible(true);
        return (fDialog.getFile() == null) ? null : new File(fDialog.getDirectory(), fDialog.getFile());
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            UnitUtil.showUnitCostBreakDown(getFrame(), new MechFileParser(unitFile).getEntity());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }
    
    private void jMenuGetUnitWeightBreakdownFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showUnitWeightBreakDown(tempEntity, getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void jMenuGetUnitSpecsFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showUnitSpecs(tempEntity, getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void insertImageAction() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if (!UnitUtil.validateUnit(getFrame().getEntity()).isBlank()) {
                JOptionPane.showMessageDialog(getFrame(),
                        resources.getString("message.invalidUnit.text"));
            }

            FileDialog fDialog = new FileDialog(getFrame(),
                    resources.getString("dialog.imagePath.title"), FileDialog.LOAD);

            if (!getFrame().getEntity().getFluff().getMMLImagePath().isBlank()) {
                String fullPath = new File(getFrame().getEntity().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "mech" + File.separatorChar);
                fDialog.setFile(getFrame().getEntity().getChassis() + ' ' + getFrame().getEntity().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(getFrame());

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
                getFrame().getEntity().getFluff().setMMLImagePath(relativeFilePath);
                BLKFile.encode(unitFile.getAbsolutePath(), tempEntity);
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
    }

    // Show data about MegaMekLab
    private void aboutAction() {
        // make the dialog
        JDialog dlg = new JDialog(getFrame(), resources.getString("menu.help.about.title"));

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JLabel version = new JLabel(String.format(resources.getString("menu.help.about.version.format"),
                MMLConstants.VERSION));
        JLabel license1 = new JLabel(resources.getString("menu.help.about.license.1"));
        JLabel license2 = new JLabel(resources.getString("menu.help.about.license.2"));
        JLabel license3 = new JLabel(resources.getString("menu.help.about.info.1"));
        JLabel license4 = new JLabel(resources.getString("menu.help.about.info.2"));

        // center everything
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        license1.setAlignmentX(Component.CENTER_ALIGNMENT);
        license2.setAlignmentX(Component.CENTER_ALIGNMENT);
        license3.setAlignmentX(Component.CENTER_ALIGNMENT);
        license4.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(new JLabel("\n"));
        child.add(version);
        child.add(new JLabel("\n"));
        child.add(license1);
        child.add(license2);
        child.add(new JLabel("\n"));
        child.add(license3);
        child.add(license4);
        child.add(new JLabel("\n"));

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);
        dlg.setLocationRelativeTo(getFrame());
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    // Show how to create fluff images for Record Sheets
    private void recordSheetImagesAction() {
        // make the dialog
        JDialog dlg = new JDialog(getFrame(), resources.getString("menu.help.imageHelp.title"));

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JTextArea recordSheetImageHelp = new JTextArea();

        recordSheetImageHelp.setEditable(false);

        recordSheetImageHelp.setText(resources.getString("menu.help.imageHelp.text"));
        // center everything
        recordSheetImageHelp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(recordSheetImageHelp);

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);

        // set the location of the dialog
        dlg.setLocationRelativeTo(getFrame());
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    private void jMenuResetEntity_actionPerformed(ActionEvent event) {
        CConfig.updateSaveFiles("Reset Unit");
        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");
        Entity en = getFrame().getEntity();
        if (en instanceof Tank) {
            getFrame().createNewUnit(Entity.ETYPE_TANK);
        } else if (en instanceof Mech) {
            getFrame().createNewUnit(Entity.ETYPE_BIPED_MECH, en.isPrimitive(), ((Mech)en).isIndustrial());
        } else if (en.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            getFrame().createNewUnit(Entity.ETYPE_DROPSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getFrame().createNewUnit(Entity.ETYPE_SMALL_CRAFT, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
            getFrame().createNewUnit(Entity.ETYPE_SPACE_STATION);
        } else if (en.hasETypeFlag(Entity.ETYPE_WARSHIP)) {
            getFrame().createNewUnit(Entity.ETYPE_WARSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            getFrame().createNewUnit(Entity.ETYPE_JUMPSHIP);
        } else if (getFrame().getEntity() instanceof Aero) {
            getFrame().createNewUnit(Entity.ETYPE_AERO, en.isPrimitive());
        } else if (getFrame().getEntity() instanceof BattleArmor) {
            getFrame().createNewUnit(Entity.ETYPE_BATTLEARMOR);
        } else if (getFrame().getEntity() instanceof Infantry) {
            getFrame().createNewUnit(Entity.ETYPE_INFANTRY);
        } else if (getFrame().getEntity() instanceof Protomech) {
            getFrame().createNewUnit(Entity.ETYPE_PROTOMECH);
        } else {
            LogManager.getLogger().warn("Received unknown entityType!");
        }
        setVisible(true);
        reload();
        refresh();
        getFrame().setVisible(true);
        getFrame().repaint();
    }

    /**
     * Constructs a file name for the current Entity using the chassis and model name and the
     * correct extension for the unit type. Any character that is not legal for a Windows filename
     * is replaced by an underscore.
     *
     * @param entity The Entity
     * @return A default filename for the Entity
     */
    private String createUnitFilename(Entity entity) {
        String fileName = (entity.getChassis() + ' ' + entity.getModel()).trim();
        fileName = fileName.replaceAll("[/\\\\<>:\"|?*]", "_");
        return fileName + ((entity instanceof Mech) ? ".mtf" : ".blk");
    }

    private void jMenuSaveEntity_actionPerformed(ActionEvent event) {
        if (!UnitUtil.validateUnit(getFrame().getEntity()).isBlank()) {
            JOptionPane.showMessageDialog(getFrame(),
                    resources.getString("message.invalidUnit.text"));
        }

        String fileName = createUnitFilename(getFrame().getEntity());
        UnitUtil.compactCriticals(getFrame().getEntity());

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if ((filePathName.trim().length() < 1) || !filePathName.contains(fileName)) {
            FileDialog fDialog = new FileDialog(getFrame(),
                    resources.getString("dialog.saveAs.title"),
                    FileDialog.SAVE);

            filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

            fDialog.setDirectory(filePathName);
            fDialog.setFile(fileName);
            fDialog.setLocationRelativeTo(getFrame());

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
                CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
            } else {
                return;
            }
        }

        try {
            if (getFrame().getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) getFrame().getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, getFrame().getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }

        JOptionPane.showMessageDialog(getFrame(),
                String.format(resources.getString("dialog.saveAs.message.format"),
                        getFrame().getEntity().getChassis(),
                        getFrame().getEntity().getModel(), filePathName));

    }

    private void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {
        if (!UnitUtil.validateUnit(getFrame().getEntity()).isBlank()) {
            JOptionPane.showMessageDialog(getFrame(),
                    resources.getString("message.savingInvalidUnit.text"));
        }

        UnitUtil.compactCriticals(getFrame().getEntity());

        FileDialog fDialog = new FileDialog(getFrame(),
                resources.getString("dialog.saveAs.title"), FileDialog.SAVE);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

        fDialog.setDirectory(filePathName);
        fDialog.setFile(createUnitFilename(getFrame().getEntity()));
        fDialog.setLocationRelativeTo(getFrame());

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
            CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
        } else {
            return;
        }

        try {
            if (getFrame().getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) getFrame().getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, getFrame().getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }

        JOptionPane.showMessageDialog(getFrame(),
                String.format(resources.getString("dialog.saveAs.message.format"),
                        getFrame().getEntity().getChassis(),
                        getFrame().getEntity().getModel(), filePathName));
    }
    
    private String entitySummaryText(boolean html) {
        if (CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO)) {
            TROView view = TROView.createView(getFrame().getEntity(), html);
            return view.processTemplate();
        } else {
            MechView view = new MechView(getFrame().getEntity(), !html, false, html);
            return view.getMechReadout();
        }
    }

    private void exportSummary(boolean html) {
        if (!UnitUtil.validateUnit(getFrame().getEntity()).isBlank()) {
            JOptionPane.showMessageDialog(getFrame(),
                    resources.getString("message.exportingInvalidUnit.text"));
        }

        String unitName = getFrame().getEntity().getChassis() + ' ' + getFrame().getEntity().getModel();

        FileDialog fDialog = new FileDialog(getFrame(),
                resources.getString("dialog.saveAs.title"), FileDialog.SAVE);
        String filePathName = new File(System.getProperty("user.dir")).getAbsolutePath();
        fDialog.setDirectory(filePathName);
        fDialog.setFile(unitName + (html?".html" : ".txt"));
        fDialog.setLocationRelativeTo(getFrame());
        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            FileOutputStream out = new FileOutputStream(filePathName);
            PrintStream p = new PrintStream(out);
            p.println(entitySummaryText(html));
            p.close();
            out.close();
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(getFrame(), unitLoadingDialog);

        Entity newUnit = viewer.getChosenEntity();
        viewer.setVisible(false);
        viewer.dispose();

        if (null == newUnit) {
            return;
        }

        if (!UnitUtil.validateUnit(newUnit).trim().isBlank()) {
            JOptionPane.showMessageDialog(getFrame(), String.format(
                    resources.getString("message.invalidUnit.format"),
                            UnitUtil.validateUnit(newUnit)));
        }

        if (newUnit.getEntityType() != getFrame().getEntity().getEntityType()) {
            if (newUnit.isSupportVehicle()) {
                newUnit(Entity.ETYPE_SUPPORT_TANK, false, newUnit);
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                newUnit(Entity.ETYPE_DROPSHIP, newUnit.isPrimitive(), newUnit);
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                newUnit(Entity.ETYPE_JUMPSHIP, newUnit.isPrimitive(), newUnit);
            } else if ((newUnit instanceof Aero) && !(newUnit instanceof FixedWingSupport)) {
                newUnit(Entity.ETYPE_AERO, newUnit.isPrimitive(), newUnit);
            } else if (newUnit instanceof BattleArmor) {
                newUnit(Entity.ETYPE_BATTLEARMOR, false, newUnit);
            } else if (newUnit instanceof Infantry) {
                newUnit(Entity.ETYPE_INFANTRY, false, newUnit);
            } else if (newUnit instanceof Mech) {
                newUnit(Entity.ETYPE_MECH, false, newUnit);
            } else if (newUnit instanceof Protomech) {
                newUnit(Entity.ETYPE_PROTOMECH, false, newUnit);
            } else if ((newUnit instanceof Tank) && !(newUnit instanceof GunEmplacement)) {
                newUnit(Entity.ETYPE_TANK, false, newUnit);
            } else {
                JOptionPane.showMessageDialog(getFrame(),
                        resources.getString("message.abortUnitLoad.text"));
            }
            return;
        }

        UnitUtil.updateLoadedUnit(newUnit);

        if (viewer.getChosenMechSummary().getSourceFile().getName().endsWith(".zip")) {
            String fileName = viewer.getChosenMechSummary().getSourceFile().getAbsolutePath();
            fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
            fileName = fileName + createUnitFilename(newUnit);
            CConfig.updateSaveFiles(fileName);
        } else {
            CConfig.updateSaveFiles(viewer.getChosenMechSummary().getSourceFile().getAbsolutePath());
        }
        getFrame().setEntity(newUnit);
        reload();
        refresh();
        getFrame().setVisible(true);
    }

    private void loadUnitFromFile(int fileNumber) {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/"; // TODO : remove inline file path

        if (fileNumber > 0) {
            switch (fileNumber) {
                case 1:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);
                    break;
                case 2:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2);
                    break;
                case 3:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3);
                    break;
                case 4:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4);
                    break;
                default:
                    break;
            }
        }

        File unitFile = new File(filePathName);
        if (!(unitFile.isFile())) {
            unitFile = loadUnitFile();
            if (unitFile == null) {
                // I want a file, y'know!
                return;
            }
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if (null == tempEntity) {
                return;
            }

            if (!UnitUtil.validateUnit(tempEntity).isBlank()) {
                JOptionPane.showMessageDialog(getFrame(), String.format(
                        resources.getString("message.invalidUnit.format"),
                        UnitUtil.validateUnit(tempEntity)));
            }

            if (tempEntity.getEntityType() != getFrame().getEntity().getEntityType()) {
                if (tempEntity.isSupportVehicle()) {
                    newUnit(Entity.ETYPE_SUPPORT_TANK, false, tempEntity);
                } else if (tempEntity.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                    newUnit(Entity.ETYPE_DROPSHIP, tempEntity.isPrimitive(), tempEntity);
                } else if (tempEntity.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                    newUnit(Entity.ETYPE_JUMPSHIP, tempEntity.isPrimitive(), tempEntity);
                } else if ((tempEntity instanceof Aero) && !(tempEntity instanceof FixedWingSupport)) {
                    newUnit(Entity.ETYPE_AERO, tempEntity.isPrimitive(), tempEntity);
                } else if (tempEntity instanceof BattleArmor) {
                    newUnit(Entity.ETYPE_BATTLEARMOR, false, tempEntity);
                } else if (tempEntity instanceof Infantry) {
                    newUnit(Entity.ETYPE_INFANTRY, false, tempEntity);
                } else if (tempEntity instanceof Mech) {
                    newUnit(Entity.ETYPE_MECH, false, tempEntity);
                } else if (tempEntity instanceof Protomech) {
                    newUnit(Entity.ETYPE_PROTOMECH, false, tempEntity);
                } else if ((tempEntity instanceof Tank) && !(tempEntity instanceof GunEmplacement)) {
                    newUnit(Entity.ETYPE_TANK, false, tempEntity);
                } else {
                    JOptionPane.showMessageDialog(getFrame(),
                            resources.getString("message.abortUnitLoad.text"));
                }
                return;
            }
            getFrame().setEntity(tempEntity);
            UnitUtil.updateLoadedUnit(getFrame().getEntity());

            CConfig.updateSaveFiles(unitFile.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(), String.format(
                    resources.getString("message.invalidUnit.format"),
                    ex.getMessage()));
        }
        reload();
        refresh();
        getFrame().setVisible(true);
    }

    private void refresh() {
        getFrame().refreshAll();
    }

    private void reload() {
        getFrame().reloadTabs();
    }

    /**
     * This function will create a new mainUI frame (via the loading dialog) for the
     * given unit type and get rid of the existing frame
     * @param type an <code>int</code> corresponding to the unit type to construct
     */
    private void newUnit(long type, boolean primitive, Entity en) {
        getFrame().setVisible(false);
        LoadingDialog ld = new LoadingDialog(getFrame(), type, primitive, false, en);
        ld.setVisible(true);
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {

    }
}
