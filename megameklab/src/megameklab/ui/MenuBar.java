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

import megamek.client.ui.dialogs.BVDisplayDialog;
import megamek.client.ui.dialogs.CostDisplayDialog;
import megamek.client.ui.dialogs.WeightDisplayDialog;
import megamek.client.ui.swing.GUIPreferences;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.common.templates.TROView;
import megameklab.MMLConstants;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.dialog.settings.SettingsDialog;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ResourceBundle;

/**
 * @author jtighe (torren@users.sourceforge.net)
 * @author Justin "Windchild" Bowen
 * @author Simon (Juliez)
 */
public class MenuBar extends JMenuBar implements ClipboardOwner {

    private final MenuBarOwner owner;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");
    private final MMLFileChooser loadUnitFileChooser = new MMLFileChooser();
    private final MMLFileChooser saveUnitFileChooser = new MMLFileChooser();
    public final MMLFileChooser loadImageFileChooser = new MMLFileChooser();
    private final JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));

    public MenuBar(MenuBarOwner owner) {
        this.owner = owner;
        initialize();
    }

    /**
     * Returns the unit main UI, if this menubar is attached to one (instead of the StartupGUI
     * aka splash screen), null otherwise.
     *
     * @return The unit main UI of this menubar or null
     */
    public @Nullable MegaMekLabMainUI getUnitMainUi() {
        if (owner instanceof MegaMekLabMainUI) {
            return (MegaMekLabMainUI) owner;
        } else {
            return null;
        }
    }

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

    private void initialize() {
        getAccessibleContext().setAccessibleName(resources.getString("MenuBar.accessibleName"));
        add(createFileMenu());
        add(createUnitValidationMenu());
        add(createReportsMenu());
        add(createHelpMenu());
        loadUnitFileChooser.setDialogTitle(resources.getString("dialog.chooseUnit.title"));
        loadUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files",
                "mtf", "blk", "hmp", "hmv", "mep", "tdb"));
        loadImageFileChooser.setDialogTitle(resources.getString("dialog.chooseUnit.title"));
        loadImageFileChooser.setFileFilter(new FileNameExtensionFilter("Image files (.png, .jpg, .gif)",
                "png", "jpg", "jpeg", "gif"));
        saveUnitFileChooser.setDialogTitle(resources.getString("dialog.saveAs.title"));
    }

    private JMenu createFileMenu() {
        fileMenu.removeAll();
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
        fileMenu.add(createExportMenu());
        fileMenu.add(createPrintMenu());
        fileMenu.add(createRefreshMenu());
        fileMenu.add(createOptionsMenu());

        fileMenu.addSeparator();
        boolean hasCConfigMenuItem = false;
        int count = 1;

        for (String file : CConfig.getRecentFiles()) {
            final JMenuItem miCConfig = createCConfigMenuItem(file, count);
            fileMenu.add(miCConfig);
            hasCConfigMenuItem = true;
            count++;
        }

        if (hasCConfigMenuItem) {
            fileMenu.addSeparator();
        }

        final JMenuItem miExit = new JMenuItem(resources.getString("miExit.text"));
        miExit.setName("miExit");
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        miExit.addActionListener(evt -> conditionalExit(owner.exit()));
        fileMenu.add(miExit);

        return fileMenu;
    }

    private JMenu createSwitchUnitTypeMenu() {
        final JMenu switchUnitTypeMenu = new JMenu(resources.getString("switchUnitTypeMenu.text"));
        switchUnitTypeMenu.setName("switchUnitTypeMenu");
        switchUnitTypeMenu.setMnemonic(KeyEvent.VK_W);

        final Entity entity = owner.getEntity();

        if ((entity == null) || (!(entity instanceof Mech) || entity.isPrimitive())) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.addActionListener(evt -> owner.newUnit(Entity.ETYPE_MECH));
            switchUnitTypeMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && entity.isPrimitive()))) {
            final JMenuItem miSwitchToFighter = new JMenuItem(resources.getString("miSwitchToFighter.text"));
            miSwitchToFighter.setName("miSwitchToFighter");
            miSwitchToFighter.setMnemonic(KeyEvent.VK_A);
            miSwitchToFighter.addActionListener(evt -> owner.newUnit(Entity.ETYPE_AERO));
            switchUnitTypeMenu.add(miSwitchToFighter);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || entity.isPrimitive())) {
            final JMenuItem item = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            item.setName("miSwitchToDropShipSmallCraft");
            item.setMnemonic(KeyEvent.VK_D);
            item.addActionListener(evt -> owner.newUnit(Entity.ETYPE_DROPSHIP));
            switchUnitTypeMenu.add(item);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || entity.isPrimitive())){
            final JMenuItem miSwitchToAdvancedAero = new JMenuItem(resources.getString("miSwitchToAdvancedAero.text"));
            miSwitchToAdvancedAero.setName("miSwitchToAdvancedAero");
            miSwitchToAdvancedAero.setMnemonic(KeyEvent.VK_J);
            miSwitchToAdvancedAero.addActionListener(evt -> owner.newUnit(Entity.ETYPE_JUMPSHIP));
            switchUnitTypeMenu.add(miSwitchToAdvancedAero);
        }

        if ((entity == null) || (!(entity instanceof Tank) || entity.isSupportVehicle())) {
            final JMenuItem miSwitchToCombatVehicle = new JMenuItem(resources.getString("miSwitchToCombatVehicle.text"));
            miSwitchToCombatVehicle.setName("miSwitchToCombatVehicle");
            miSwitchToCombatVehicle.setMnemonic(KeyEvent.VK_C);
            miSwitchToCombatVehicle.addActionListener(evt -> owner.newUnit(Entity.ETYPE_TANK));
            switchUnitTypeMenu.add(miSwitchToCombatVehicle);
        }

        if ((entity == null) || (!entity.isSupportVehicle())) {
            final JMenuItem miSwitchToSupportVehicle = new JMenuItem(resources.getString("miSwitchToSupportVehicle.text"));
            miSwitchToSupportVehicle.setName("miSwitchToSupportVehicle");
            miSwitchToSupportVehicle.setMnemonic(KeyEvent.VK_S);
            miSwitchToSupportVehicle.addActionListener(evt -> owner.newUnit(Entity.ETYPE_SUPPORT_TANK));
            switchUnitTypeMenu.add(miSwitchToSupportVehicle);
        }

        if (!(entity instanceof BattleArmor)) {
            final JMenuItem miSwitchToBattleArmor = new JMenuItem(resources.getString("miSwitchToBattleArmor.text"));
            miSwitchToBattleArmor.setName("miSwitchToBattleArmor");
            miSwitchToBattleArmor.setMnemonic(KeyEvent.VK_B);
            miSwitchToBattleArmor.addActionListener(evt -> owner.newUnit(Entity.ETYPE_BATTLEARMOR));
            switchUnitTypeMenu.add(miSwitchToBattleArmor);
        }

        if ((entity == null) || (!entity.isConventionalInfantry())) {
            final JMenuItem miSwitchToInfantry = new JMenuItem(resources.getString("miSwitchToInfantry.text"));
            miSwitchToInfantry.setName("miSwitchToInfantry");
            miSwitchToInfantry.setMnemonic(KeyEvent.VK_I);
            miSwitchToInfantry.addActionListener(evt -> owner.newUnit(Entity.ETYPE_INFANTRY));
            switchUnitTypeMenu.add(miSwitchToInfantry);
        }

        if ((entity == null) || (!entity.hasETypeFlag(Entity.ETYPE_PROTOMECH))) {
            final JMenuItem miSwitchToProtoMek = new JMenuItem(resources.getString("miSwitchToProtoMek.text"));
            miSwitchToProtoMek.setName("miSwitchToProtoMek");
            miSwitchToProtoMek.setMnemonic(KeyEvent.VK_P);
            miSwitchToProtoMek.addActionListener(evt -> owner.newUnit(Entity.ETYPE_PROTOMECH));
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
            miSwitchToMek.addActionListener(evt -> owner.newUnit(Entity.ETYPE_MECH, true));
            primitiveMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && !entity.isPrimitive()))) {
            final JMenuItem miSwitchToAero = new JMenuItem(resources.getString("miSwitchToAero.text"));
            miSwitchToAero.setName("miSwitchToAero");
            miSwitchToAero.setMnemonic(KeyEvent.VK_A);
            miSwitchToAero.addActionListener(evt -> owner.newUnit(Entity.ETYPE_AERO, true));
            primitiveMenu.add(miSwitchToAero);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToDropShipSmallCraft = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            miSwitchToDropShipSmallCraft.setName("miSwitchToDropShipSmallCraft");
            miSwitchToDropShipSmallCraft.setMnemonic(KeyEvent.VK_D);
            miSwitchToDropShipSmallCraft.addActionListener(evt -> owner.newUnit(Entity.ETYPE_DROPSHIP, true));
            primitiveMenu.add(miSwitchToDropShipSmallCraft);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToJumpShip = new JMenuItem(resources.getString("miSwitchToJumpShip.text"));
            miSwitchToJumpShip.setName("miSwitchToJumpShip");
            miSwitchToJumpShip.setMnemonic(KeyEvent.VK_J);
            miSwitchToJumpShip.addActionListener(evt -> owner.newUnit(Entity.ETYPE_JUMPSHIP, true));
            primitiveMenu.add(miSwitchToJumpShip);
        }

        return primitiveMenu;
    }

    /**
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
        miLoadUnitFromCache.addActionListener(evt -> StartupGUI.selectAndLoadUnitFromCache(owner));
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
        miSave.addActionListener(evt -> saveUnit());
        miSave.setEnabled(isUnitGui());
        saveMenu.add(miSave);

        final JMenuItem miSaveAs = new JMenuItem(resources.getString("SaveAs.text"));
        miSaveAs.setName("miSaveAs");
        miSaveAs.setMnemonic(KeyEvent.VK_A);
        miSaveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        miSaveAs.addActionListener(evt -> saveUnitAs());
        miSaveAs.setEnabled(isUnitGui());
        saveMenu.add(miSaveAs);

        return saveMenu;
    }

    /**
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
     * @return the created PDF Unit Export menu
     */
    private JMenu createPDFUnitExportMenu() {
        final JMenu pdfUnitExportMenu = new JMenu(resources.getString("pdfUnitExportMenu.text"));
        pdfUnitExportMenu.setName("pdfUnitExportMenu");
        pdfUnitExportMenu.setMnemonic(KeyEvent.VK_P);

        final JMenuItem miExportCurrentUnitToPDF = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToPDF.setName("miExportCurrentUnitToPDF");
        miExportCurrentUnitToPDF.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToPDF.addActionListener(evt -> {
            warnOnInvalid();
            UnitPrintManager.exportEntity(owner.getEntity(), owner.getFrame());
        });
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
        miExportUnitsFromMULFileToPDF.addActionListener(evt -> UnitPrintManager.printMUL(owner.getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitsFromMULFileToPDF);

        return pdfUnitExportMenu;
    }

    /**
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
            warnOnInvalid();
            StringSelection stringSelection = new StringSelection(entitySummaryText(false));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, this);
        });
        miExportCurrentUnitToClipboard.setEnabled(isUnitGui());
        clipboardUnitExportMenu.add(miExportCurrentUnitToClipboard);

        return clipboardUnitExportMenu;
    }

    /**
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
        miPrintCurrentUnit.addActionListener(evt -> {
            warnOnInvalid();
            UnitPrintManager.printEntity(owner.getEntity());
        });
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

        return printMenu;
    }

    /**
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
     * @return the created Options menu
     */
    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu(resources.getString("optionsMenu.text"));
        optionsMenu.setName("optionsMenu");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        final JMenuItem miImport = new JMenuItem(resources.getString("miImport.text"));
        miImport.setName("miImport");
        miImport.setMnemonic(KeyEvent.VK_I);
        miImport.addActionListener(evt -> importSettings());
        optionsMenu.add(miImport);

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
            if (!GUIPreferences.isSupportedLookAndFeel(laf)) {
                continue;
            }
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

    private @Nullable JMenuItem createCConfigMenuItem(final String recentFileName,
                                                      final int fileNumber) {
        File recent = new File(recentFileName);
        String path = recent.getParent();
        String mmlDirectory = System.getProperty("user.dir");
        if (recentFileName.startsWith(mmlDirectory)) {
            path = path.substring(mmlDirectory.length());
            if (path.length() > 40) {
                path = path.substring(0, 40) + "...";
            }
        }

        String text = "<HTML>" + fileNumber + ". " + recent.getName() + "<BR><FONT SIZE=\"-2\">" + path;
        final JMenuItem miCConfig = new JMenuItem(text);
        miCConfig.setName("miCConfig");
        miCConfig.addActionListener(evt -> loadUnitFromFile(fileNumber));
        miCConfig.setMnemonic(48 + fileNumber); // the number itself, i.e. 1, 2, 3 etc.
        return miCConfig;
    }

    /**
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

    /**
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
        miCurrentUnitBVBreakdown.addActionListener(evt -> showBVCalculations(owner.getFrame(), owner.getEntity()));
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
     * @return the created Unit Weight Breakdown menu
     */
    private JMenu createUnitWeightBreakdownMenu() {
        final JMenu unitWeightBreakdownMenu = new JMenu(resources.getString("unitWeightBreakdownMenu.text"));
        unitWeightBreakdownMenu.setName("unitWeightBreakdownMenu");
        unitWeightBreakdownMenu.setMnemonic(KeyEvent.VK_W);

        final JMenuItem miCurrentUnitWeightBreakdown = new JMenuItem(resources.getString("CurrentUnit.text"));
        miCurrentUnitWeightBreakdown.setName("miCurrentUnitWeightBreakdown");
        miCurrentUnitWeightBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitWeightBreakdown.addActionListener(evt -> showUnitWeightBreakDown(owner.getEntity(), owner.getFrame()));
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

    /**
     * @return the created Help menu
     */
    private JMenu createHelpMenu() {
        final JMenu helpMenu = new JMenu(resources.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem miResetWindowPos = new JMenuItem(resources.getString("miResetWindow.text"));
        miResetWindowPos.setName("miResetWindows");
        miResetWindowPos.setMnemonic(KeyEvent.VK_W);
        miResetWindowPos.addActionListener(evt -> CConfig.resetWindowPositions());
        helpMenu.add(miResetWindowPos);

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

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);
        Entity chosenEntity = viewer.getChosenEntity();
        if (chosenEntity != null) {
            UnitUtil.showValidation(chosenEntity, owner.getFrame());
        }
        viewer.dispose();
    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);
        Entity chosenEntity = viewer.getChosenEntity();
        if (chosenEntity != null) {
            new CostDisplayDialog(owner.getFrame(), chosenEntity).setVisible(true);
        }
        viewer.dispose();
    }

    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog);

        Entity chosenEntity = viewer.getChosenEntity();
        if (chosenEntity != null) {
            showUnitWeightBreakDown(chosenEntity, owner.getFrame());
        }
        viewer.dispose();
    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            showBVCalculations(owner.getFrame(), new MechFileParser(unitFile).getEntity());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    private void jMenuGetUnitValidationFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showValidation(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    /**
     * Shows a file chooser for a unit file to open. Returns the unit file if one was chosen;
     * returns null if none was chosen or the dialog was cancelled.
     *
     * @return The chosen unit file or null if cancelled or nothing was chosen
     */
    private @Nullable File chooseUnitFileToLoad() {
        loadUnitFileChooser.setCurrentDirectory(new File(CConfig.getParam(CConfig.FILE_LAST_DIRECTORY)));
        int result = loadUnitFileChooser.showOpenDialog(owner.getFrame());
        return result == JFileChooser.APPROVE_OPTION ? loadUnitFileChooser.getSelectedFile() : null;
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            new CostDisplayDialog(owner.getFrame(), new MechFileParser(unitFile).getEntity()).setVisible(true);
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    private void jMenuGetUnitWeightBreakdownFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            showUnitWeightBreakDown(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    private void jMenuGetUnitSpecsFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            showUnitSpecs(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
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
        reload();
        refresh();
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

    /**
     * Tries to save the unit directly to its file, if it has a filename already. If
     * it hasn't, it performs a Save As... Returns true when it successfully saves the
     * unit, false if not.
     *
     * @return True when the unit was actually saved, false otherwise
     */
    public boolean saveUnit() {
        Entity entity = owner.getEntity();
        if (entity == null) {
            LogManager.getLogger().error("Tried to save null entity.");
            return false;
        } else {
            warnOnInvalid();
        }

        UnitUtil.compactCriticals(entity);
        owner.refreshAll(); // The crits may have moved

        String filePathName = owner.getFileName();
        // For safety, save automatically only to .mtf or .blk files, otherwise ask
        if (!(filePathName.endsWith(".mtf") || filePathName.endsWith(".blk"))
                || !new File(filePathName).exists()
                || owner.hasEntityNameChanged()) {
            File selectedFile = chooseSaveFile();
            if (selectedFile == null) {
                return false;
            }

            filePathName = selectedFile.getPath();
        }

        CConfig.setMostRecentFile(filePathName);
        return saveUnitTo(new File(filePathName));
    }

    private void saveUnitAs() {
        warnOnInvalid();

        UnitUtil.compactCriticals(owner.getEntity());
        owner.refreshAll(); // The crits may have moved

        File saveFile = chooseSaveFile();
        if (saveFile != null) {
            CConfig.setMostRecentFile(saveFile.toString());
            saveUnitTo(saveFile);
        }
    }

    private @Nullable File chooseSaveFile() {
        if (getUnitMainUi().getEntity() instanceof Mech) {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Mek files", "mtf"));
        } else {
            saveUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files", "blk"));
        }
        saveUnitFileChooser.setSelectedFile(new File(createUnitFilename(getUnitMainUi().getEntity())));
        int result = saveUnitFileChooser.showSaveDialog(owner.getFrame());
        if ((result != JFileChooser.APPROVE_OPTION) || (saveUnitFileChooser.getSelectedFile() == null)) {
            return null;
        } else {
            return saveUnitFileChooser.getSelectedFile();
        }
    }

    private boolean saveUnitTo(File file) {
        if (getUnitMainUi().getEntity() == null) {
            return false;
        }
        try {
            if (getUnitMainUi().getEntity() instanceof Mech) {
                try (FileOutputStream fos = new FileOutputStream(file);
                     PrintStream ps = new PrintStream(fos)) {
                    ps.println(((Mech) owner.getEntity()).getMtf());
                }
            } else {
                BLKFile.encode(file.getPath(), getUnitMainUi().getEntity());
            }
            PopupMessages.showUnitSavedMessage(owner.getFrame(), getUnitMainUi().getEntity(), file);
            getUnitMainUi().setFileName(file.toString());
            return true;
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(owner.getFrame(), ex.getMessage());
            LogManager.getLogger().error("", ex);
            return false;
        }
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
        warnOnInvalid();

        String unitName = owner.getEntity().getChassis() + ' ' + owner.getEntity().getModel();

        MMLFileChooser fileChooser = new MMLFileChooser();
        fileChooser.setDialogTitle(resources.getString("dialog.saveAs.title"));
        fileChooser.setSelectedFile(new File(unitName + (html ? ".html" : ".txt")));
        int result = fileChooser.showSaveDialog(owner.getFrame());
        if ((result != JFileChooser.APPROVE_OPTION) || (fileChooser.getSelectedFile() == null)) {
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile());
             PrintStream ps = new PrintStream(fos)) {
            ps.println(entitySummaryText(html));
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(owner.getFrame(), ex.getMessage());
            LogManager.getLogger().error("", ex);
        }
    }

    private void loadUnitFromFile(int fileNumber) {
        File unitFile;
        if (fileNumber > 0) {
            String recentFileName = CConfig.getRecentFile(fileNumber);
            if (recentFileName.isBlank()) {
                return;
            }
            unitFile = new File(recentFileName);
        } else {
            unitFile = chooseUnitFileToLoad();
            if (unitFile == null) {
                return;
            }
        }

        try {
            Entity loadedUnit = new MechFileParser(unitFile).getEntity();

            if (loadedUnit == null) {
                throw new Exception();
            }

            if (!owner.safetyPrompt()) {
                return;
            }

            warnOnInvalid(loadedUnit);

            newRecentUnit(unitFile.toString());
            if (isStartupGui() || (loadedUnit.getEntityType() != owner.getEntity().getEntityType())) {
                owner.getFrame().setVisible(false);
                owner.getFrame().dispose();
                UiLoader.loadUi(loadedUnit, unitFile.toString());
            } else {
                getUnitMainUi().setEntity(loadedUnit, unitFile.toString());
                UnitUtil.updateLoadedUnit(getUnitMainUi().getEntity());
                reload();
                refresh();
            }
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    /**
     * Adds a new most recent unit, moving the previous recent units down the list. Renews this
     * menubar to reflect the change.
     *
     * @param latestUnit The filename of the new most recent unit.
     */
    private void newRecentUnit(String latestUnit) {
        CConfig.setMostRecentFile(latestUnit);
        createFileMenu();
    }

    private void refresh() {
        owner.refreshAll();
    }

    private void reload() {
        getUnitMainUi().reloadTabs();
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) { }

    private boolean isStartupGui() {
        return owner instanceof StartupGUI;
    }

    private boolean isUnitGui() {
        return !isStartupGui();
    }

    /**
     * Refreshes this menubar. At least this updates the file menu, showing the latest recent unit
     * changes.
     */
    public void refreshMenuBar() {
        createFileMenu();
    }

    /**
     * Performs a settings import, currently only for the megameklab.properties file (CConfig).
     * Shows a help message before showing a file chooser for selecting a directory. The
     * directory should always be MML's main directory (which contains the mmconf directory
     * with the megameklab.properties file).
     */
    public void importSettings() {
        PopupMessages.showSettingsImportHelp(owner.getFrame());

        MMLFileChooser fileChooser = new MMLFileChooser();
        fileChooser.setDialogTitle(resources.getString("dialog.importSettings"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(owner.getFrame());
        if ((result != JFileChooser.APPROVE_OPTION) || (fileChooser.getSelectedFile() == null)
                || !fileChooser.getSelectedFile().isDirectory()) {
            return;
        }
        File settingsFile = new File(fileChooser.getSelectedFile(), CConfig.CONFIG_FILE);
        CConfig.importSettings(owner, settingsFile);
    }

    public static void showUnitSpecs(Entity unit, JFrame frame) {
        HTMLEditorKit kit = new HTMLEditorKit();

        MechView mechView;
        try {
            mechView = new MechView(unit, true);
        } catch (Exception ex) {
            // error unit didn't load right. this is bad news.
            LogManager.getLogger().error("", ex);
            return;
        }

        String unitSpecs = "<html><body>" + mechView.getMechReadoutBasic() +
                mechView.getMechReadoutLoadout() + "</body></html>";

        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(unitSpecs);

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);

        jdialog.pack();

        jdialog.setLocationRelativeTo(frame);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ignored) {

        }
    }

    private void warnOnInvalid() {
        warnOnInvalid(owner.getEntity());
    }

    private void warnOnInvalid(Entity entity) {
        var report = UnitUtil.validateUnit(entity);
        if (!report.isBlank()) {
            PopupMessages.showUnitInvalidWarning(owner.getFrame(), report);
        }
    }

    public static void showUnitWeightBreakDown(Entity entity, JFrame frame) {
        if (entity != null) {
            new WeightDisplayDialog(frame, entity).setVisible(true);
        }
    }

    public static void showBVCalculations(final JFrame frame, final @Nullable Entity entity) {
        if (entity != null) {
            new BVDisplayDialog(frame, entity).setVisible(true);
        }
    }
}