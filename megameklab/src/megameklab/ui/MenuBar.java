/*
 * MegaMekLab
 * Copyright (c) 2011-2023 - The MegaMek Team. All Rights Reserved.
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
 * @author Justin "Windchild" Bowen
 * @author Simon (Juliez)
 */
public class MenuBar extends JMenuBar implements ClipboardOwner {
    //region Variable Declarations
    private final MenuBarOwner owner;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");
    //endregion Variable Declarations

    //region Constructors
    public MenuBar(MenuBarOwner owner) {
        this.owner = owner;
        initialize();
    }
    //endregion Constructors

    //region Getters
    public @Nullable MegaMekLabMainUI getUnitMainUi() {
        if (owner instanceof MegaMekLabMainUI) {
            return (MegaMekLabMainUI) owner;
        } else {
            return null;
        }
    }
    //endregion Getters

    /**
     * The File menu's exit handler pops up a modal that produces a boolean;
     * to match the functionality of ExitOnWindowClosingListener, we want to
     * exit if that boolean is true.
     * Otherwise, do nothing.
     *
     * @param okayToExit When true, will exit MML, otherwise do nothing
     */
    private void conditionalExit(boolean okayToExit) {
        if (okayToExit) {
            System.exit(0);
        }
    }

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
    }

    //region File Menu
    /**
     * @return the created file menu
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem miResetCurrentUnit = new JMenuItem(resources.getString("miResetCurrentUnit.text"));
        miResetCurrentUnit.setName("miResetCurrentUnit");
        miResetCurrentUnit.setMnemonic(KeyEvent.VK_R);
        miResetCurrentUnit.addActionListener(this::jMenuResetEntity_actionPerformed);
        miResetCurrentUnit.setEnabled(isUnitGui());
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

        final JMenuItem miCConfig4 = createCConfigMenuItem(CConfig.CONFIG_SAVE_FILE_4, fileNumber);
        if (miCConfig4 != null) {
            fileMenu.add(miCConfig4);
            hasCConfigMenuItem = true;
        }

        if (hasCConfigMenuItem) {
            fileMenu.addSeparator();
        }

        final JMenuItem miExit = new JMenuItem(resources.getString("miExit.text"));
        miExit.setName("miExit");
        miExit.setMnemonic(KeyEvent.VK_X);
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        miExit.addActionListener(evt -> conditionalExit(owner.exit()));
        fileMenu.add(miExit);

        return fileMenu;
    }

    /**
     * @return the created Switch Unit Type menu
     */
    private JMenu createSwitchUnitTypeMenu() {
        final JMenu switchUnitTypeMenu = new JMenu(resources.getString("switchUnitTypeMenu.text"));
        switchUnitTypeMenu.setName("switchUnitTypeMenu");
        switchUnitTypeMenu.setMnemonic(KeyEvent.VK_W);

        final Entity entity = owner.getEntity();

        if ((entity == null) || (!(entity instanceof Mech) || entity.isPrimitive())) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, false));
            switchUnitTypeMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && entity.isPrimitive()))) {
            final JMenuItem miSwitchToFighter = new JMenuItem(resources.getString("miSwitchToFighter.text"));
            miSwitchToFighter.setName("miSwitchToFighter");
            miSwitchToFighter.setMnemonic(KeyEvent.VK_A);
            miSwitchToFighter.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, false));
            switchUnitTypeMenu.add(miSwitchToFighter);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || entity.isPrimitive())) {
            final JMenuItem item = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            item.setName("miSwitchToDropShipSmallCraft");
            item.setMnemonic(KeyEvent.VK_D);
            item.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, false));
            switchUnitTypeMenu.add(item);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || entity.isPrimitive())){
            final JMenuItem miSwitchToAdvancedAero = new JMenuItem(resources.getString("miSwitchToAdvancedAero.text"));
            miSwitchToAdvancedAero.setName("miSwitchToAdvancedAero");
            miSwitchToAdvancedAero.setMnemonic(KeyEvent.VK_J);
            miSwitchToAdvancedAero.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP, false));
            switchUnitTypeMenu.add(miSwitchToAdvancedAero);
        }

        if ((entity == null) || (!(entity instanceof Tank) || entity.isSupportVehicle())) {
            final JMenuItem miSwitchToCombatVehicle = new JMenuItem(resources.getString("miSwitchToCombatVehicle.text"));
            miSwitchToCombatVehicle.setName("miSwitchToCombatVehicle");
            miSwitchToCombatVehicle.setMnemonic(KeyEvent.VK_C);
            miSwitchToCombatVehicle.addActionListener(evt -> newUnit(Entity.ETYPE_TANK, false));
            switchUnitTypeMenu.add(miSwitchToCombatVehicle);
        }

        if ((entity == null) || (!entity.isSupportVehicle())) {
            final JMenuItem miSwitchToSupportVehicle = new JMenuItem(resources.getString("miSwitchToSupportVehicle.text"));
            miSwitchToSupportVehicle.setName("miSwitchToSupportVehicle");
            miSwitchToSupportVehicle.setMnemonic(KeyEvent.VK_S);
            miSwitchToSupportVehicle.addActionListener(evt -> newUnit(Entity.ETYPE_SUPPORT_TANK, false));
            switchUnitTypeMenu.add(miSwitchToSupportVehicle);
        }

        if (!(entity instanceof BattleArmor)) {
            final JMenuItem miSwitchToBattleArmor = new JMenuItem(resources.getString("miSwitchToBattleArmor.text"));
            miSwitchToBattleArmor.setName("miSwitchToBattleArmor");
            miSwitchToBattleArmor.setMnemonic(KeyEvent.VK_B);
            miSwitchToBattleArmor.addActionListener(evt -> newUnit(Entity.ETYPE_BATTLEARMOR, false));
            switchUnitTypeMenu.add(miSwitchToBattleArmor);
        }

        if ((entity == null) || (!entity.isConventionalInfantry())) {
            final JMenuItem miSwitchToInfantry = new JMenuItem(resources.getString("miSwitchToInfantry.text"));
            miSwitchToInfantry.setName("miSwitchToInfantry");
            miSwitchToInfantry.setMnemonic(KeyEvent.VK_I);
            miSwitchToInfantry.addActionListener(evt -> newUnit(Entity.ETYPE_INFANTRY, false));
            switchUnitTypeMenu.add(miSwitchToInfantry);
        }

        if ((entity == null) || (!entity.hasETypeFlag(Entity.ETYPE_PROTOMECH))) {
            final JMenuItem miSwitchToProtoMek = new JMenuItem(resources.getString("miSwitchToProtoMek.text"));
            miSwitchToProtoMek.setName("miSwitchToProtoMek");
            miSwitchToProtoMek.setMnemonic(KeyEvent.VK_P);
            miSwitchToProtoMek.addActionListener(evt -> newUnit(Entity.ETYPE_PROTOMECH, false));
            switchUnitTypeMenu.add(miSwitchToProtoMek);
        }

        switchUnitTypeMenu.add(createPrimitiveMenu(entity));

        return switchUnitTypeMenu;
    }

    /**
     * @return the created Primitive menu
     */
    private JMenu createPrimitiveMenu(final Entity entity) {
        final JMenu primitiveMenu = new JMenu(resources.getString("primitiveMenu.text"));
        primitiveMenu.setName("primitiveMenu");
        primitiveMenu.setMnemonic(KeyEvent.VK_R);

        if ((entity == null) || (!(entity instanceof Mech) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.addActionListener(evt -> newUnit(Entity.ETYPE_MECH, true));
            primitiveMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && !entity.isPrimitive()))) {
            final JMenuItem miSwitchToAero = new JMenuItem(resources.getString("miSwitchToAero.text"));
            miSwitchToAero.setName("miSwitchToAero");
            miSwitchToAero.setMnemonic(KeyEvent.VK_A);
            miSwitchToAero.addActionListener(evt -> newUnit(Entity.ETYPE_AERO, true));
            primitiveMenu.add(miSwitchToAero);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToDropShipSmallCraft = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            miSwitchToDropShipSmallCraft.setName("miSwitchToDropShipSmallCraft");
            miSwitchToDropShipSmallCraft.setMnemonic(KeyEvent.VK_D);
            miSwitchToDropShipSmallCraft.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP, true));
            primitiveMenu.add(miSwitchToDropShipSmallCraft);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToJumpShip = new JMenuItem(resources.getString("miSwitchToJumpShip.text"));
            miSwitchToJumpShip.setName("miSwitchToJumpShip");
            miSwitchToJumpShip.setMnemonic(KeyEvent.VK_J);
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
        miLoadUnitFromCache.addActionListener(evt -> StartupGUI.selectAndLoadUnitFromCache(owner.getFrame()));
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
        miSave.setEnabled(isUnitGui());
        saveMenu.add(miSave);

        final JMenuItem miSaveAs = new JMenuItem(resources.getString("SaveAs.text"));
        miSaveAs.setName("miSaveAs");
        miSaveAs.setMnemonic(KeyEvent.VK_A);
        miSaveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        miSaveAs.addActionListener(evt -> jMenuSaveAsEntity_actionPerformed());
        miSaveAs.setEnabled(isUnitGui());
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
        miImportFluffImage.setEnabled(isUnitGui());
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
        exportMenu.setMnemonic(KeyEvent.VK_E);

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
        miExportCurrentUnitToPDF.addActionListener(evt -> UnitPrintManager.exportEntity(owner.getEntity(), owner.getFrame()));
        miExportCurrentUnitToPDF.setEnabled(isUnitGui());
        pdfUnitExportMenu.add(miExportCurrentUnitToPDF);

        final JMenuItem miExportUnitFromCacheToPDF = new JMenuItem(resources.getString("FromCache.text"));
        miExportUnitFromCacheToPDF.setName("miExportUnitFromCacheToPDF");
        miExportUnitFromCacheToPDF.setMnemonic(KeyEvent.VK_C);
        miExportUnitFromCacheToPDF.addActionListener(evt -> UnitPrintManager.printSelectedUnit(owner.getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitFromCacheToPDF);

        final JMenuItem miExportUnitFromFileToPDF = new JMenuItem(resources.getString("FromFile.text"));
        miExportUnitFromFileToPDF.setName("miExportUnitFromFileToPDF");
        miExportUnitFromFileToPDF.setMnemonic(KeyEvent.VK_F);
        miExportUnitFromFileToPDF.addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), false, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToPDF);

        final JMenuItem miExportUnitFromFileToSinglePDFPage = new JMenuItem(resources.getString("FromFileSingle.text"));
        miExportUnitFromFileToSinglePDFPage.setName("miExportUnitFromFileToSinglePDFPage");
        miExportUnitFromFileToSinglePDFPage.setMnemonic(KeyEvent.VK_S);
        miExportUnitFromFileToSinglePDFPage.addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), true, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToSinglePDFPage);

        final JMenuItem miExportUnitQueueToPDF = new JMenuItem(resources.getString("miExportUnitQueueToPDF.text"));
        miExportUnitQueueToPDF.setName("miExportUnitQueueToPDF");
        miExportUnitQueueToPDF.setMnemonic(KeyEvent.VK_Q);
        miExportUnitQueueToPDF.addActionListener(evt -> new PrintQueueDialog(owner.getFrame(), true).setVisible(true));
        pdfUnitExportMenu.add(miExportUnitQueueToPDF);

        final JMenuItem miExportUnitsFromMULFileToPDF = new JMenuItem(resources.getString("FromMUL.text"));
        miExportUnitsFromMULFileToPDF.setName("miExportUnitsFromMULFileToPDF");
        miExportUnitsFromMULFileToPDF.setMnemonic(KeyEvent.VK_M);
        miExportUnitsFromMULFileToPDF.addActionListener(evt -> UnitPrintManager.exportMUL(owner.getFrame(), false));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToPDF);

        final JMenuItem miExportUnitsFromMULFileToSinglePDFPages = new JMenuItem(resources.getString("FromMULSingle.text"));
        miExportUnitsFromMULFileToSinglePDFPages.setName("miExportUnitsFromMULFileToSinglePDFPages");
        miExportUnitsFromMULFileToSinglePDFPages.setMnemonic(KeyEvent.VK_L);
        miExportUnitsFromMULFileToSinglePDFPages.addActionListener(evt -> UnitPrintManager.exportMUL(owner.getFrame(), true));
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
        miExportCurrentUnitToHTML.addActionListener(evt -> exportSummary(true));
        miExportCurrentUnitToHTML.setEnabled(isUnitGui());
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
        miExportCurrentUnitToText.addActionListener(evt -> exportSummary(false));
        miExportCurrentUnitToText.setEnabled(isUnitGui());
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
        miExportCurrentUnitToClipboard.addActionListener(evt -> {
            StringSelection stringSelection = new StringSelection(entitySummaryText(false));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, this);
        });
        miExportCurrentUnitToClipboard.setEnabled(isUnitGui());
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
        miPrintCurrentUnit.addActionListener(evt -> UnitPrintManager.printEntity(owner.getEntity()));
        miPrintCurrentUnit.setEnabled(isUnitGui());
        printMenu.add(miPrintCurrentUnit);

        final JMenuItem miPrintUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miPrintUnitFromCache.setName("miPrintUnitFromCache");
        miPrintUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miPrintUnitFromCache.addActionListener(evt -> UnitPrintManager.printSelectedUnit(owner.getFrame(), false));
        printMenu.add(miPrintUnitFromCache);

        final JMenuItem miPrintUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miPrintUnitFromFile.setName("miPrintUnitFromFile");
        miPrintUnitFromFile.setMnemonic(KeyEvent.VK_F);
        miPrintUnitFromFile.addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), false, false));
        printMenu.add(miPrintUnitFromFile);

        final JMenuItem miPrintUnitFromFileToSinglePage = new JMenuItem(resources.getString("FromFileSingle.text"));
        miPrintUnitFromFileToSinglePage.setName("miPrintUnitFromFileToSinglePage");
        miPrintUnitFromFileToSinglePage.setMnemonic(KeyEvent.VK_S);
        miPrintUnitFromFileToSinglePage.addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), true, false));
        printMenu.add(miPrintUnitFromFileToSinglePage);

        final JMenuItem miPrintUnitQueue = new JMenuItem(resources.getString("miPrintUnitQueue.text"));
        miPrintUnitQueue.setName("miPrintUnitQueue");
        miPrintUnitQueue.setMnemonic(KeyEvent.VK_Q);
        miPrintUnitQueue.addActionListener(evt -> new PrintQueueDialog(owner.getFrame(), false).setVisible(true));
        printMenu.add(miPrintUnitQueue);

        final JMenuItem miPrintUnitsFromMULFile = new JMenuItem(resources.getString("FromMUL.text"));
        miPrintUnitsFromMULFile.setName("miPrintUnitsFromMULFile");
        miPrintUnitsFromMULFile.setMnemonic(KeyEvent.VK_M);
        miPrintUnitsFromMULFile.addActionListener(evt -> UnitPrintManager.printMUL(owner.getFrame(), false));
        printMenu.add(miPrintUnitsFromMULFile);

        final JMenuItem miPrintUnitsFromMULFileToSinglePages = new JMenuItem(resources.getString("FromMULSingle.text"));
        miPrintUnitsFromMULFileToSinglePages.setName("miPrintUnitsFromMULFileToSinglePages");
        miPrintUnitsFromMULFileToSinglePages.setMnemonic(KeyEvent.VK_L);
        miPrintUnitsFromMULFileToSinglePages.addActionListener(evt -> UnitPrintManager.printMUL(owner.getFrame(), true));
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
        refreshMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem miRefreshUnitCache = new JMenuItem(resources.getString("miRefreshUnitCache.text"));
        miRefreshUnitCache.setName("miRefreshUnitCache");
        miRefreshUnitCache.setMnemonic(KeyEvent.VK_U);
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
        miConfiguration.addActionListener(evt -> {
            new SettingsDialog(owner.getFrame()).setVisible(true);
            owner.refreshAll();
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
                owner.changeTheme(laf);
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
        if (newFile.isEmpty()) {
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
        miCConfig.setMnemonic(KeyEvent.VK_1);
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
        miValidateCurrentUnit.addActionListener(evt -> UnitUtil.showValidation(owner.getEntity(), owner.getFrame()));
        miValidateCurrentUnit.setEnabled(isUnitGui());
        unitValidationMenu.add(miValidateCurrentUnit);

        final JMenuItem miValidateUnitFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miValidateUnitFromCache.setName("miValidateUnitFromCache");
        miValidateUnitFromCache.setMnemonic(KeyEvent.VK_C);
        miValidateUnitFromCache.addActionListener(evt -> jMenuGetUnitValidationFromCache_actionPerformed());
        unitValidationMenu.add(miValidateUnitFromCache);

        final JMenuItem miValidateUnitFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miValidateUnitFromFile.setName("miValidateUnitFromFile");
        miValidateUnitFromFile.setMnemonic(KeyEvent.VK_F);
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
     * @return the created Unit Specs Report menu
     */
    private JMenu createUnitSpecsReportMenu() {
        final JMenu unitSpecsReportMenu = new JMenu(resources.getString("unitSpecsReportMenu.text"));
        unitSpecsReportMenu.setName("unitSpecsReportMenu");
        unitSpecsReportMenu.setMnemonic(KeyEvent.VK_S);

        final JMenuItem miUnitSpecsFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitSpecsFromFile.setName("miUnitSpecsFromFile");
        miUnitSpecsFromFile.setMnemonic(KeyEvent.VK_F);
        miUnitSpecsFromFile.addActionListener(evt -> jMenuGetUnitSpecsFromFile_actionPerformed());
        unitSpecsReportMenu.add(miUnitSpecsFromFile);

        return unitSpecsReportMenu;
    }

    /**
     * @return the created Unit BV Breakdown menu
     */
    private JMenu createUnitBVBreakdownMenu() {
        final JMenu unitBVBreakdownMenu = new JMenu(resources.getString("unitBVBreakdownMenu.text"));
        unitBVBreakdownMenu.setName("unitBVBreakdownMenu");
        unitBVBreakdownMenu.setMnemonic(KeyEvent.VK_B);

        final JMenuItem miCurrentUnitBVBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitBVBreakdown.setName("miCurrentUnitBVBreakdown");
        miCurrentUnitBVBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitBVBreakdown.addActionListener(evt -> UnitUtil.showBVCalculations(owner.getFrame(), owner.getEntity()));
        miCurrentUnitBVBreakdown.setEnabled(isUnitGui());
        unitBVBreakdownMenu.add(miCurrentUnitBVBreakdown);

        final JMenuItem miUnitBVBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitBVBreakdownFromFile.setName("miUnitBVBreakdownFromFile");
        miUnitBVBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
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
        miCurrentUnitCostBreakdown.addActionListener(evt -> new CostDisplayDialog(owner.getFrame(), owner.getEntity()).setVisible(true));
        miCurrentUnitCostBreakdown.setEnabled(isUnitGui());
        unitCostBreakdownMenu.add(miCurrentUnitCostBreakdown);

        final JMenuItem miUnitCostBreakdownFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitCostBreakdownFromCache.setName("miUnitCostBreakdownFromCache");
        miUnitCostBreakdownFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitCostBreakdownFromCache.addActionListener(evt -> jMenuGetUnitBreakdownFromCache_actionPerformed());
        unitCostBreakdownMenu.add(miUnitCostBreakdownFromCache);

        final JMenuItem miUnitCostBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitCostBreakdownFromFile.setName("miUnitCostBreakdownFromFile");
        miUnitCostBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
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
        miCurrentUnitWeightBreakdown.addActionListener(evt -> UnitUtil.showUnitWeightBreakDown(owner.getEntity(), owner.getFrame()));
        miCurrentUnitWeightBreakdown.setEnabled(isUnitGui());
        unitWeightBreakdownMenu.add(miCurrentUnitWeightBreakdown);

        final JMenuItem miUnitWeightBreakdownFromCache = new JMenuItem(resources.getString("FromCache.text"));
        miUnitWeightBreakdownFromCache.setName("miUnitWeightBreakdownFromCache");
        miUnitWeightBreakdownFromCache.setMnemonic(KeyEvent.VK_C);
        miUnitWeightBreakdownFromCache.addActionListener(evt -> jMenuGetUnitWeightBreakdownFromCache_actionPerformed());
        unitWeightBreakdownMenu.add(miUnitWeightBreakdownFromCache);

        final JMenuItem miUnitWeightBreakdownFromFile = new JMenuItem(resources.getString("FromFile.text"));
        miUnitWeightBreakdownFromFile.setName("miUnitWeightBreakdownFromFile");
        miUnitWeightBreakdownFromFile.setMnemonic(KeyEvent.VK_F);
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
        miAbout.addActionListener(evt -> aboutAction());
        helpMenu.add(miAbout);

        final JMenuItem miRecordSheetImages = new JMenuItem(resources.getString("miRecordSheetImages.text"));
        miRecordSheetImages.setName("miRecordSheetImages");
        miRecordSheetImages.setMnemonic(KeyEvent.VK_R);
        miRecordSheetImages.addActionListener(evt -> recordSheetImagesAction());
        helpMenu.add(miRecordSheetImages);

        return helpMenu;
    }
    //endregion Help Menu
    //endregion Initialization

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if (null == tempEntity) {
            return;
        }
        UnitUtil.showValidation(tempEntity, owner.getFrame());
    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);
        new CostDisplayDialog(owner.getFrame(), viewer.getChosenEntity()).setVisible(true);
    }

    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if (null == tempEntity) {
            return;
        }
        UnitUtil.showUnitWeightBreakDown(tempEntity, owner.getFrame());
    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            UnitUtil.showBVCalculations(owner.getFrame(), new MechFileParser(unitFile).getEntity());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(),
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
            UnitUtil.showValidation(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private @Nullable File loadUnitFile() {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/"; // TODO : remove inline file path
        FileDialog fDialog = new FileDialog(owner.getFrame(),
                resources.getString("dialog.chooseUnit.title"),
                FileDialog.LOAD);
        fDialog.setLocationRelativeTo(owner.getFrame());
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
            new CostDisplayDialog(owner.getFrame(), new MechFileParser(unitFile).getEntity()).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(),
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
            UnitUtil.showUnitWeightBreakDown(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(),
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
            UnitUtil.showUnitSpecs(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(),
                    String.format(resources.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void importFluffImageAction() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if (!UnitUtil.validateUnit(owner.getEntity()).isBlank()) {
                JOptionPane.showMessageDialog(owner.getFrame(),
                        resources.getString("message.invalidUnit.text"));
            }

            FileDialog fDialog = new FileDialog(owner.getFrame(),
                    resources.getString("dialog.imagePath.title"), FileDialog.LOAD);

            if (!owner.getEntity().getFluff().getMMLImagePath().isBlank()) {
                String fullPath = new File(owner.getEntity().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "mech" + File.separatorChar);
                fDialog.setFile(owner.getEntity().getChassis() + ' ' + owner.getEntity().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(owner.getFrame());

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
                owner.getEntity().getFluff().setMMLImagePath(relativeFilePath);
                BLKFile.encode(unitFile.getAbsolutePath(), tempEntity);
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
    }

    // Show data about MegaMekLab
    private void aboutAction() {
        // make the dialog
        JDialog dlg = new JDialog(owner.getFrame(), resources.getString("menu.help.about.title"));

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
        dlg.setLocationRelativeTo(owner.getFrame());
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    // Show how to create fluff images for Record Sheets
    private void recordSheetImagesAction() {
        // make the dialog
        JDialog dlg = new JDialog(owner.getFrame(), resources.getString("menu.help.imageHelp.title"));

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
        dlg.setLocationRelativeTo(owner.getFrame());
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    private void jMenuResetEntity_actionPerformed(ActionEvent event) {
        if (!owner.safetyPrompt() || !isUnitGui()) {
            return;
        }

        CConfig.updateSaveFiles("Reset Unit");
        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");
        Entity en = owner.getEntity();
        if (en instanceof Tank) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_TANK);
        } else if (en instanceof Mech) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_BIPED_MECH, en.isPrimitive(), ((Mech)en).isIndustrial());
        } else if (en.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_DROPSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_SMALL_CRAFT, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_SPACE_STATION);
        } else if (en.hasETypeFlag(Entity.ETYPE_WARSHIP)) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_WARSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_JUMPSHIP);
        } else if (owner.getEntity() instanceof Aero) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_AERO, en.isPrimitive());
        } else if (owner.getEntity() instanceof BattleArmor) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_BATTLEARMOR);
        } else if (owner.getEntity() instanceof Infantry) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_INFANTRY);
        } else if (owner.getEntity() instanceof Protomech) {
            getUnitMainUi().createNewUnit(Entity.ETYPE_PROTOMECH);
        } else {
            LogManager.getLogger().warn("Received unknown entityType!");
        }
        setVisible(true);
        reload();
        refresh();
        getUnitMainUi().setVisible(true);
        getUnitMainUi().repaint();
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
        saveEntity(owner.getEntity());
    }

    public boolean saveEntity(final @Nullable Entity entity) {
        if (entity == null) {
            return false;
        } else if (!UnitUtil.validateUnit(entity).isBlank()) {
            JOptionPane.showMessageDialog(owner.getFrame(),
                    resources.getString("message.invalidUnit.text"));
        }

        String fileName = createUnitFilename(entity);
        UnitUtil.compactCriticals(entity);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if (filePathName.isBlank() || !filePathName.contains(fileName)) {
            FileDialog fDialog = new FileDialog(owner.getFrame(),
                    resources.getString("dialog.saveAs.title"), FileDialog.SAVE);

            filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

            fDialog.setDirectory(filePathName);
            fDialog.setFile(fileName);
            fDialog.setLocationRelativeTo(owner.getFrame());

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

        JOptionPane.showMessageDialog(owner.getFrame(),
                String.format(resources.getString("dialog.saveAs.message.format"),
                        entity.getChassis(),
                        entity.getModel(), filePathName));
        return true;
    }

    private void jMenuSaveAsEntity_actionPerformed() {
        if (!UnitUtil.validateUnit(owner.getEntity()).isBlank()) {
            JOptionPane.showMessageDialog(owner.getFrame(),
                    resources.getString("message.savingInvalidUnit.text"));
        }

        UnitUtil.compactCriticals(owner.getEntity());

        FileDialog fDialog = new FileDialog(owner.getFrame(),
                resources.getString("dialog.saveAs.title"), FileDialog.SAVE);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

        fDialog.setDirectory(filePathName);
        fDialog.setFile(createUnitFilename(owner.getEntity()));
        fDialog.setLocationRelativeTo(owner.getFrame());

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
            CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
        } else {
            return;
        }

        try {
            if (owner.getEntity() instanceof Mech) {
                try (FileOutputStream fos = new FileOutputStream(filePathName);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mech) owner.getEntity()).getMtf());
                }
            } else {
                BLKFile.encode(filePathName, owner.getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }

        JOptionPane.showMessageDialog(owner.getFrame(),
                String.format(resources.getString("dialog.saveAs.message.format"),
                        owner.getEntity().getChassis(),
                        owner.getEntity().getModel(), filePathName));
    }

    private String entitySummaryText(boolean html) {
        if (CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO)) {
            TROView view = TROView.createView(owner.getEntity(), html);
            return view.processTemplate();
        } else {
            MechView view = new MechView(owner.getEntity(), !html, false, html);
            return view.getMechReadout();
        }
    }

    private void exportSummary(boolean html) {
        if (!UnitUtil.validateUnit(owner.getEntity()).isBlank()) {
            JOptionPane.showMessageDialog(owner.getFrame(),
                    resources.getString("message.exportingInvalidUnit.text"));
        }

        String unitName = owner.getEntity().getChassis() + ' ' + owner.getEntity().getModel();

        FileDialog fDialog = new FileDialog(owner.getFrame(),
                resources.getString("dialog.saveAs.title"), FileDialog.SAVE);
        String filePathName = new File(System.getProperty("user.dir")).getAbsolutePath();
        fDialog.setDirectory(filePathName);
        fDialog.setFile(unitName + (html?".html" : ".txt"));
        fDialog.setLocationRelativeTo(owner.getFrame());
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
            unitFile = loadUnitFile();
            if (unitFile == null) {
                return;
            }
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if ((null == tempEntity) || !owner.safetyPrompt()) {
                return;
            }

            if (!UnitUtil.validateUnit(tempEntity).isBlank()) {
                JOptionPane.showMessageDialog(owner.getFrame(), String.format(
                        resources.getString("message.invalidUnit.format"),
                        UnitUtil.validateUnit(tempEntity)));
            }

            if (isStartupGui() || (tempEntity.getEntityType() != owner.getEntity().getEntityType())) {
                owner.getFrame().setVisible(false);
                owner.getFrame().dispose();
                UiLoader.loadUi(tempEntity);
                return;
            } else {
                getUnitMainUi().setEntity(tempEntity);
                UnitUtil.updateLoadedUnit(owner.getEntity());
                CConfig.updateSaveFiles(unitFile.getAbsolutePath());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner.getFrame(), String.format(
                    resources.getString("message.invalidUnit.format"),
                    ex.getMessage()));
        }
        reload();
        refresh();
        getUnitMainUi().setVisible(true);
    }

    private void refresh() {
        owner.refreshAll();
    }

    private void reload() {
        getUnitMainUi().reloadTabs();
    }

    /**
     * This function will create a new mainUI frame (via the loading dialog) for the
     * given unit type and get rid of the existing frame
     * @param type an <code>int</code> corresponding to the unit type to construct
     */
    private void newUnit(long type, boolean primitive) {
        if (owner.safetyPrompt()) {
            owner.getFrame().setVisible(false);
            owner.getFrame().dispose();
            UiLoader.loadUi(type, primitive, false);
        }
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) { }

    private boolean isStartupGui() {
        return owner instanceof StartupGUI;
    }

    private boolean isUnitGui() {
        return !isStartupGui();
    }
}