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

import megamek.client.ui.dialogs.CostDisplayDialog;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.common.templates.TROView;
import megameklab.MMLConstants;
import megameklab.ui.dialog.UiLoader;
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
import javax.swing.filechooser.FileNameExtensionFilter;
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

/**
 * @author jtighe (torren@users.sourceforge.net)
 * @author Justin "Windchild" Bowen
 */
public class MenuBar extends JMenuBar implements ClipboardOwner {

    private final MegaMekLabMainUI frame;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");

    private final JFileChooser loadUnitFileChooser = new JFileChooser();
    private JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));


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

    //region Conditional Exit Handler
    /**
     * The File menu's exit handler pops up a modal that produces a boolean;
     * to match the functionality of ExitOnWindowClosingListener, we want to
     * exit if that boolean is true.
     * Otherwise, do nothing.
     * @param okayToExit
     */
    public void conditionalExit( boolean okayToExit){
        if (okayToExit){
            System.exit(0);
        }
    }
    //endregion Conditional Exit Handler

    //region Initialization
    /**
     * The main menu bar uses the following Mnemonic keys as of 30-Jun-2022:
     * F, H, R, U
     *
     * It uses the following Control-based keys as of 30-Jun-2022:
     * A, C, L, P, R, S, U
     */
    private void initialize() {
        getAccessibleContext().setAccessibleName(resources.getString("MenuBar.accessibleName"));
        add(createFileMenu());
        add(createUnitValidationMenu());
        add(createReportsMenu());
        add(createHelpMenu());
        loadUnitFileChooser.setDialogTitle(resources.getString("dialog.chooseUnit.title"));
        loadUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files",
                "mtf", "blk", "hmp", "hmv", "mep", "tdb"));
        loadUnitFileChooser.setCurrentDirectory(new File("data/mechfiles"));
    }

    //region File Menu
    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, E, I, L, O, P, R, S, U, X
     * @return the created file menu
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem miResetCurrentUnit = new JMenuItem(resources.getString("miResetCurrentUnit.text"));
        miResetCurrentUnit.setName("miResetCurrentUnit");
        miResetCurrentUnit.setMnemonic(KeyEvent.VK_C);
        miResetCurrentUnit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        miResetCurrentUnit.addActionListener(this::jMenuResetEntity_actionPerformed);
        fileMenu.add(miResetCurrentUnit);

        fileMenu.add(createSwitchUnitTypeMenu());
        fileMenu.add(createLoadMenu());
        fileMenu.add(createSaveMenu());
        fileMenu.add(createImportMenu());
        fileMenu.add(createExportMenu());
        fileMenu.add(createPrintMenu());
        fileMenu.add(createRefreshMenu());
        fileMenu.add(createOptionsMenu());

        fileMenu.addSeparator();

        int fileNumber = 1;
        boolean hasCConfigMenuItem = false;

        final JMenuItem miCConfig1 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_1, fileNumber++);
        if (miCConfig1 != null) {
            fileMenu.add(miCConfig1);
            hasCConfigMenuItem = true;
        }

        final JMenuItem miCConfig2 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_2, fileNumber++);
        if (miCConfig2 != null) {
            fileMenu.add(miCConfig2);
            hasCConfigMenuItem = true;
        }

        final JMenuItem miCConfig3 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_3, fileNumber++);
        if (miCConfig3 != null) {
            fileMenu.add(miCConfig3);
            hasCConfigMenuItem = true;
        }

        final JMenuItem miCConfig4 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_4, fileNumber++);
        if (miCConfig4 != null) {
            fileMenu.add(miCConfig4);
            hasCConfigMenuItem = true;
        }

        if (hasCConfigMenuItem) {
            fileMenu.addSeparator();
        }

        final JMenuItem miExit = new JMenuItem(resources.getString("miExit.text"));
        miExit.setName("miExit");
        miExit.setMnemonic(KeyEvent.VK_E);
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
        miExit.addActionListener(evt -> conditionalExit(getFrame().exit()));
        fileMenu.add(miExit);

        return fileMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * A, B, C, D, I, J, M, P, R
     * @return the created Switch Unit Type menu
     */
    private JMenu createSwitchUnitTypeMenu() {
        final JMenu switchUnitTypeMenu = new JMenu(resources.getString("switchUnitTypeMenu.text"));
        switchUnitTypeMenu.setName("switchUnitTypeMenu");
        switchUnitTypeMenu.setMnemonic(KeyEvent.VK_U);

        final Entity entity = getFrame().getEntity();

        if (!(entity instanceof Mech) || entity.isPrimitive()) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
            miSwitchToMek.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, false));
            switchUnitTypeMenu.add(miSwitchToMek);
        }

        if (!entity.isFighter() || ((entity instanceof Aero) && entity.isPrimitive())) {
            final JMenuItem miSwitchToFighter = new JMenuItem(resources.getString("miSwitchToFighter.text"));
            miSwitchToFighter.setName("miSwitchToFighter");
            miSwitchToFighter.setMnemonic(KeyEvent.VK_A);
            miSwitchToFighter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
            miSwitchToFighter.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, false));
            switchUnitTypeMenu.add(miSwitchToFighter);
        }

        if (!(entity instanceof SmallCraft) || entity.isPrimitive()) {
            final JMenuItem item = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            item.setName("miSwitchToDropShipSmallCraft");
            item.setMnemonic(KeyEvent.VK_D);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));
            item.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, false));
            switchUnitTypeMenu.add(item);
        }

        if (!(entity instanceof Jumpship) || entity.isPrimitive()) {
            final JMenuItem miSwitchToAdvancedAero = new JMenuItem(resources.getString("miSwitchToAdvancedAero.text"));
            miSwitchToAdvancedAero.setName("miSwitchToAdvancedAero");
            miSwitchToAdvancedAero.setMnemonic(KeyEvent.VK_J);
            miSwitchToAdvancedAero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK));
            miSwitchToAdvancedAero.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP, false));
            switchUnitTypeMenu.add(miSwitchToAdvancedAero);
        }

        if (!(getFrame().getEntity() instanceof Tank)
                || getFrame().getEntity().isSupportVehicle()) {
            final JMenuItem miSwitchToCombatVehicle = new JMenuItem(resources.getString("miSwitchToCombatVehicle.text"));
            miSwitchToCombatVehicle.setName("miSwitchToCombatVehicle");
            miSwitchToCombatVehicle.setMnemonic(KeyEvent.VK_C);
            miSwitchToCombatVehicle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
            miSwitchToCombatVehicle.addActionListener(evt -> newUnit(Entity.ETYPE_TANK, false));
            switchUnitTypeMenu.add(miSwitchToCombatVehicle);
        }

        if (!getFrame().getEntity().isSupportVehicle()) {
            final JMenuItem miSwitchToSupportVehicle = new JMenuItem(resources.getString("miSwitchToSupportVehicle.text"));
            miSwitchToSupportVehicle.setName("miSwitchToSupportVehicle");
            miSwitchToSupportVehicle.setMnemonic(KeyEvent.VK_S);
            miSwitchToSupportVehicle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
            miSwitchToSupportVehicle.addActionListener(evt -> newUnit(Entity.ETYPE_SUPPORT_TANK, false));
            switchUnitTypeMenu.add(miSwitchToSupportVehicle);
        }

        if (!(getFrame().getEntity() instanceof BattleArmor)) {
            final JMenuItem miSwitchToBattleArmor = new JMenuItem(resources.getString("miSwitchToBattleArmor.text"));
            miSwitchToBattleArmor.setName("miSwitchToBattleArmor");
            miSwitchToBattleArmor.setMnemonic(KeyEvent.VK_B);
            miSwitchToBattleArmor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK));
            miSwitchToBattleArmor.addActionListener(evt -> newUnit(Entity.ETYPE_BATTLEARMOR, false));
            switchUnitTypeMenu.add(miSwitchToBattleArmor);
        }

        if (!getFrame().getEntity().isConventionalInfantry()) {
            final JMenuItem miSwitchToInfantry = new JMenuItem(resources.getString("miSwitchToInfantry.text"));
            miSwitchToInfantry.setName("miSwitchToInfantry");
            miSwitchToInfantry.setMnemonic(KeyEvent.VK_I);
            miSwitchToInfantry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
            miSwitchToInfantry.addActionListener(evt -> newUnit(Entity.ETYPE_INFANTRY, false));
            switchUnitTypeMenu.add(miSwitchToInfantry);
        }

        if (!getFrame().getEntity().hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            final JMenuItem miSwitchToProtoMek = new JMenuItem(resources.getString("miSwitchToProtoMek.text"));
            miSwitchToProtoMek.setName("miSwitchToProtoMek");
            miSwitchToProtoMek.setMnemonic(KeyEvent.VK_P);
            miSwitchToProtoMek.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
            miSwitchToProtoMek.addActionListener(evt -> newUnit(Entity.ETYPE_PROTOMECH, false));
            switchUnitTypeMenu.add(miSwitchToProtoMek);
        }

        switchUnitTypeMenu.add(createPrimitiveMenu(entity));

        return switchUnitTypeMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * A, D, J, M
     * @return the created Primitive menu
     */
    private JMenu createPrimitiveMenu(final Entity entity) {
        final JMenu primitiveMenu = new JMenu(resources.getString("primitiveMenu.text"));
        primitiveMenu.setName("primitiveMenu");
        primitiveMenu.setMnemonic(KeyEvent.VK_R);

        if (!(entity instanceof Mech) || !entity.isPrimitive()) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
            miSwitchToMek.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, true));
            primitiveMenu.add(miSwitchToMek);
        }

        if (!entity.isFighter() || ((entity instanceof Aero) && !entity.isPrimitive())) {
            final JMenuItem miSwitchToAero = new JMenuItem(resources.getString("miSwitchToAero.text"));
            miSwitchToAero.setName("miSwitchToAero");
            miSwitchToAero.setMnemonic(KeyEvent.VK_A);
            miSwitchToAero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
            miSwitchToAero.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, true));
            primitiveMenu.add(miSwitchToAero);
        }

        if (!(entity instanceof SmallCraft) || !entity.isPrimitive()) {
            final JMenuItem miSwitchToDropShipSmallCraft = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            miSwitchToDropShipSmallCraft.setName("miSwitchToDropShipSmallCraft");
            miSwitchToDropShipSmallCraft.setMnemonic(KeyEvent.VK_D);
            miSwitchToDropShipSmallCraft.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));
            miSwitchToDropShipSmallCraft.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, true));
            primitiveMenu.add(miSwitchToDropShipSmallCraft);
        }

        if (!(entity instanceof Jumpship) || !entity.isPrimitive()) {
            final JMenuItem miSwitchToJumpShip = new JMenuItem(resources.getString("miSwitchToJumpShip.text"));
            miSwitchToJumpShip.setName("miSwitchToJumpShip");
            miSwitchToJumpShip.setMnemonic(KeyEvent.VK_J);
            miSwitchToJumpShip.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK));
            miSwitchToJumpShip.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP, true));
            primitiveMenu.add(miSwitchToJumpShip);
        }

        return primitiveMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F
     * @return the created Load menu
     */
    private JMenu createLoadMenu() {
        final JMenu loadMenu = new JMenu(resources.getString("loadMenu.text"));
        loadMenu.setName("loadMenu");
        loadMenu.setMnemonic(KeyEvent.VK_L);

        final JMenuItem miLoadUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miLoadUnitFromCache.setName("miLoadUnitFromCache");
        miLoadUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miLoadUnitFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        miLoadUnitFromCache.addActionListener(evt -> StartupGUI.selectAndLoadUnitFromCache(frame));
        loadMenu.add(miLoadUnitFromCache);

        final JMenuItem miLoadUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miLoadUnitFromFile.setName("miLoadUnitFromFile");
        miLoadUnitFromFile.setMnemonic(KeyEvent.VK_F);
        miLoadUnitFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        miLoadUnitFromFile.addActionListener(evt -> loadUnitFromFile(-1));
        loadMenu.add(miLoadUnitFromFile);

        return loadMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * A, S
     * @return the created Save menu
     */
    private JMenu createSaveMenu() {
        final JMenu saveMenu = new JMenu(resources.getString("Save.text"));
        saveMenu.setName("saveMenu");
        saveMenu.setMnemonic(KeyEvent.VK_S);

        final JMenuItem miSave = new JMenuItem(resources.getString("Save.text"));
        miSave.setName("miSave");
        miSave.setMnemonic(KeyEvent.VK_S);
        miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        miSave.addActionListener(evt -> saveEntity());
        saveMenu.add(miSave);

        final JMenuItem miSaveAs = new JMenuItem(resources.getString("SaveAs.text"));
        miSaveAs.setName("miSaveAs");
        miSaveAs.setMnemonic(KeyEvent.VK_A);
        miSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        miSaveAs.addActionListener(this::jMenuSaveAsEntity_actionPerformed);
        saveMenu.add(miSaveAs);

        return saveMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * I
     * @return the created Import menu
     */
    private JMenu createImportMenu() {
        final JMenu importMenu = new JMenu(resources.getString("importMenu.text"));
        importMenu.setName("importMenu");
        importMenu.setMnemonic(KeyEvent.VK_I);

        final JMenuItem miImportFluffImage = new JMenuItem(resources.getString("miImportFluffImage.text"));
        miImportFluffImage.setName("miImportFluffImage");
        miImportFluffImage.setMnemonic(KeyEvent.VK_I);
        miImportFluffImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
        miImportFluffImage.addActionListener(evt -> importFluffImageAction());
        importMenu.add(miImportFluffImage);

        return importMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, H, P, T
     * @return the created Export menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, L, M, Q, S, U
     * @return the created PDF Unit Export menu
     */
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

        final JMenuItem miExportUnitFromFileToSinglePDFPage = new JMenuItem(resources.getString("FromFileSingle.text"));
        miExportUnitFromFileToSinglePDFPage.setName("miExportUnitFromFileToSinglePDFPage");
        miExportUnitFromFileToSinglePDFPage.setMnemonic(KeyEvent.VK_S);
        miExportUnitFromFileToSinglePDFPage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        miExportUnitFromFileToSinglePDFPage.addActionListener(evt -> UnitPrintManager.printUnitFile(getFrame(), true, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToSinglePDFPage);

        final JMenuItem miExportUnitQueueToPDF = new JMenuItem(resources.getString("miExportUnitQueueToPDF.text"));
        miExportUnitQueueToPDF.setName("miExportUnitQueueToPDF");
        miExportUnitQueueToPDF.setMnemonic(KeyEvent.VK_Q);
        miExportUnitQueueToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        miExportUnitQueueToPDF.addActionListener(evt -> new PrintQueueDialog(getFrame(), true).setVisible(true));
        pdfUnitExportMenu.add(miExportUnitQueueToPDF);

        final JMenuItem miExportUnitsFromMULFileToPDF = new JMenuItem(resources.getString("FromMUL.text"));
        miExportUnitsFromMULFileToPDF.setName("miExportUnitsFromMULFileToPDF");
        miExportUnitsFromMULFileToPDF.setMnemonic(KeyEvent.VK_M);
        miExportUnitsFromMULFileToPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
        miExportUnitsFromMULFileToPDF.addActionListener(evt -> UnitPrintManager.exportMUL(getFrame(), false));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToPDF);

        final JMenuItem miExportUnitsFromMULFileToSinglePDFPages = new JMenuItem(resources.getString("FromMULSingle.text"));
        miExportUnitsFromMULFileToSinglePDFPages.setName("miExportUnitsFromMULFileToSinglePDFPages");
        miExportUnitsFromMULFileToSinglePDFPages.setMnemonic(KeyEvent.VK_L);
        miExportUnitsFromMULFileToSinglePDFPages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
        miExportUnitsFromMULFileToSinglePDFPages.addActionListener(evt -> UnitPrintManager.exportMUL(getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToSinglePDFPages);

        return pdfUnitExportMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * U
     * @return the created HTML Unit Export menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * U
     * @return the created Text Unit Export menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * U
     * @return the created Clipboard Unit Export menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, L, M, Q, S, U
     * @return the created Print menu
     */
    private JMenu createPrintMenu() {
        final JMenu printMenu = new JMenu(resources.getString("printMenu.text"));
        printMenu.setName("printMenu");
        printMenu.setMnemonic(KeyEvent.VK_P);

        final JMenuItem miPrintCurrentUnit = new JMenuItem(resources.getString("CurrentUnit.text"));
        miPrintCurrentUnit.setName("miPrintCurrentUnit");
        miPrintCurrentUnit.setMnemonic(KeyEvent.VK_U);
        miPrintCurrentUnit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        miPrintCurrentUnit.addActionListener(evt -> UnitPrintManager.printEntity(getFrame().getEntity()));
        printMenu.add(miPrintCurrentUnit);

        final JMenuItem miPrintUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miPrintUnitFromCache.setName("miPrintUnitFromCache");
        miPrintUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miPrintUnitFromCache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        miPrintUnitFromCache.addActionListener(evt -> UnitPrintManager.printSelectedUnit(getFrame(), false));
        printMenu.add(miPrintUnitFromCache);

        final JMenuItem miPrintUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miPrintUnitFromFile.setName("miPrintUnitFromFile");
        miPrintUnitFromFile.setMnemonic(KeyEvent.VK_F);
        miPrintUnitFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        miPrintUnitFromFile.addActionListener(evt -> UnitPrintManager.printUnitFile(getFrame(), false, false));
        printMenu.add(miPrintUnitFromFile);

        final JMenuItem miPrintUnitFromFileToSinglePage = new JMenuItem(resources.getString("FromFileSingle.text"));
        miPrintUnitFromFileToSinglePage.setName("miPrintUnitFromFileToSinglePage");
        miPrintUnitFromFileToSinglePage.setMnemonic(KeyEvent.VK_S);
        miPrintUnitFromFileToSinglePage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        miPrintUnitFromFileToSinglePage.addActionListener(evt -> UnitPrintManager.printUnitFile(getFrame(), true, false));
        printMenu.add(miPrintUnitFromFileToSinglePage);

        final JMenuItem miPrintUnitQueue = new JMenuItem(resources.getString("miPrintUnitQueue.text"));
        miPrintUnitQueue.setName("miPrintUnitQueue");
        miPrintUnitQueue.setMnemonic(KeyEvent.VK_Q);
        miPrintUnitQueue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        miPrintUnitQueue.addActionListener(evt -> new PrintQueueDialog(getFrame(), false).setVisible(true));
        printMenu.add(miPrintUnitQueue);

        final JMenuItem miPrintUnitsFromMULFile = new JMenuItem(resources.getString("FromMUL.text"));
        miPrintUnitsFromMULFile.setName("miPrintUnitsFromMULFile");
        miPrintUnitsFromMULFile.setMnemonic(KeyEvent.VK_M);
        miPrintUnitsFromMULFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
        miPrintUnitsFromMULFile.addActionListener(evt -> UnitPrintManager.printMUL(getFrame(), false));
        printMenu.add(miPrintUnitsFromMULFile);

        final JMenuItem miPrintUnitsFromMULFileToSinglePages = new JMenuItem(resources.getString("FromMULSingle.text"));
        miPrintUnitsFromMULFileToSinglePages.setName("miPrintUnitsFromMULFileToSinglePages");
        miPrintUnitsFromMULFileToSinglePages.setMnemonic(KeyEvent.VK_L);
        miPrintUnitsFromMULFileToSinglePages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
        miPrintUnitsFromMULFileToSinglePages.addActionListener(evt -> UnitPrintManager.printMUL(getFrame(), true));
        printMenu.add(miPrintUnitsFromMULFileToSinglePages);

        return printMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * U
     * @return the created Refresh menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, T
     * @return the created Options menu
     */
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

        optionsMenu.add(createThemesMenu());

        return optionsMenu;
    }

    /**
     * Creates a menu that includes all installed look and feel options
     * @return The created Themes menu
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
    //endregion File Menu

    //region Unit Validation Menu
    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, U
     * @return the created Unit Validation menu
     */
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
    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * B, C, S, W
     * @return the created reports menu
     */
    private JMenu createReportsMenu() {
        final JMenu reportsMenu = new JMenu(resources.getString("reportsMenu.text"));
        reportsMenu.setName("reportsMenu");
        reportsMenu.setMnemonic(KeyEvent.VK_R);

        reportsMenu.add(createUnitSpecsReportMenu());
        reportsMenu.add(createUnitBVBreakdownMenu());
        reportsMenu.add(createUnitCostBreakdownMenu());
        reportsMenu.add(createUnitWeightBreakdownMenu());

        return reportsMenu;
    }

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, U
     * @return the created Unit Specs Report menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, U
     * @return the created Unit BV Breakdown menu
     */
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, U
     * @return the created Unit Cost Breakdown menu
     */
    private JMenu createUnitCostBreakdownMenu() {
        final JMenu unitCostBreakdownMenu = new JMenu(resources.getString("unitCostBreakdownMenu.text"));
        unitCostBreakdownMenu.setName("unitCostBreakdownMenu");
        unitCostBreakdownMenu.setMnemonic(KeyEvent.VK_C);

        final JMenuItem miCurrentUnitCostBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitCostBreakdown.setName("miCurrentUnitCostBreakdown");
        miCurrentUnitCostBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitCostBreakdown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
        miCurrentUnitCostBreakdown.addActionListener(evt -> new CostDisplayDialog(getFrame(), getFrame().getEntity()).setVisible(true));
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

    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * C, F, U
     * @return the created Unit Weight Breakdown menu
     */
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
    /**
     * This menu uses the following Mnemonic keys as of 30-Jun-2022:
     * A, R
     * @return the created Help menu
     */
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
    //endregion Initialization

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
        new CostDisplayDialog(getFrame(), viewer.getChosenEntity()).setVisible(true);
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
        File unitFile = chooseUnitFileToLoad();
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
        File unitFile = chooseUnitFileToLoad();
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

    private @Nullable File chooseUnitFileToLoad() {
        int result = loadUnitFileChooser.showOpenDialog(frame);
        return result == JFileChooser.APPROVE_OPTION ? loadUnitFileChooser.getSelectedFile() : null;
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            new CostDisplayDialog(getFrame(), new MechFileParser(unitFile).getEntity()).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void jMenuGetUnitWeightBreakdownFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
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
        File unitFile = chooseUnitFileToLoad();
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

    private void importFluffImageAction() {
        File unitFile = chooseUnitFileToLoad();
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
        if (!getFrame().safetyPrompt()) {
            return;
        }

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
    public static String createUnitFilename(Entity entity) {
        String fileName = (entity.getChassis() + ' ' + entity.getModel()).trim();
        fileName = fileName.replaceAll("[/\\\\<>:\"|?*]", "_");
        return fileName + ((entity instanceof Mech) ? ".mtf" : ".blk");
    }

    private void saveEntity() {
        saveEntity(getFrame().getEntity());
    }

    public boolean saveEntity(final @Nullable Entity entity) {
        if (entity == null) {
            return false;
        } else if (!UnitUtil.validateUnit(entity).isBlank()) {
            JOptionPane.showMessageDialog(getFrame(),
                    resources.getString("message.invalidUnit.text"));
        }

        String fileName = createUnitFilename(entity);
        UnitUtil.compactCriticals(entity);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if (filePathName.isBlank() || !filePathName.contains(fileName)) {
            FileDialog fDialog = new FileDialog(getFrame(),
                    resources.getString("dialog.saveAs.title"), FileDialog.SAVE);

            filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

            fDialog.setDirectory(filePathName);
            fDialog.setFile(fileName);
            fDialog.setLocationRelativeTo(getFrame());

            fDialog.setVisible(true);

            if (fDialog.getFile() == null) {
                return false;
            }

            filePathName = fDialog.getDirectory() + fDialog.getFile();
            CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
        }

        try {
            if (entity instanceof Mech) {
                try (FileOutputStream fos = new FileOutputStream(filePathName);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mech) entity).getMtf());
                }
            } else {
                BLKFile.encode(filePathName, entity);
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
            return false;
        }

        JOptionPane.showMessageDialog(getFrame(),
                String.format(resources.getString("dialog.saveAs.message.format"),
                        entity.getChassis(),
                        entity.getModel(), filePathName));
        return true;
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
                try (FileOutputStream fos = new FileOutputStream(filePathName);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mech) getFrame().getEntity()).getMtf());
                }
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

        try (FileOutputStream fos = new FileOutputStream(filePathName);
             PrintStream ps = new PrintStream(fos)) {
            ps.println(entitySummaryText(html));
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
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
            unitFile = chooseUnitFileToLoad();
            if (unitFile == null) {
                return;
            }
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if ((null == tempEntity) || !getFrame().safetyPrompt()) {
                return;
            }

            if (!UnitUtil.validateUnit(tempEntity).isBlank()) {
                JOptionPane.showMessageDialog(getFrame(), String.format(
                        resources.getString("message.invalidUnit.format"),
                        UnitUtil.validateUnit(tempEntity)));
            }

            if (tempEntity.getEntityType() != getFrame().getEntity().getEntityType()) {
                getFrame().setVisible(false);
                getFrame().dispose();
                UiLoader.loadUi(tempEntity);
                return;
            } else {
                getFrame().setEntity(tempEntity);
                UnitUtil.updateLoadedUnit(getFrame().getEntity());
                CConfig.updateSaveFiles(unitFile.getAbsolutePath());
            }
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
    private void newUnit(long type, boolean primitive) {
        if (frame.safetyPrompt()) {
            frame.setVisible(false);
            frame.dispose();
            UiLoader.loadUi(type, primitive, false);
        }
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {

    }
}
