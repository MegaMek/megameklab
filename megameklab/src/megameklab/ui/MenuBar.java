/*
 * Copyright (C) 2011-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import java.awt.Component;
import java.awt.Toolkit;
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
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.dialogs.abstractDialogs.BVDisplayDialog;
import megamek.client.ui.dialogs.abstractDialogs.CostDisplayDialog;
import megamek.client.ui.dialogs.abstractDialogs.WeightDisplayDialog;
import megamek.client.ui.dialogs.unitSelectorDialogs.EntityReadoutDialog;
import megamek.client.ui.entityreadout.EntityReadout;
import megamek.client.ui.util.UIUtil;
import megamek.client.ui.util.ViewFormatting;
import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.loaders.MekFileParser;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.templates.TROView;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.SmallCraft;
import megamek.common.units.Tank;
import megamek.logging.MMLogger;
import megameklab.MMLConstants;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.dialog.settings.SettingsDialog;
import megameklab.ui.util.OSUtil;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;

/**
 * @author jtighe (torren@users.sourceforge.net)
 * @author Justin "Windchild" Bowen
 * @author Simon (Juliez)
 */
public class MenuBar extends JMenuBar implements ClipboardOwner {
    private static final MMLogger logger = MMLogger.create(MenuBar.class);

    private final MenuBarOwner owner;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");
    private final MMLFileChooser loadUnitFileChooser = new MMLFileChooser();
    public final MMLFileChooser loadImageFileChooser = new MMLFileChooser();
    private final JMenu fileMenu = new JMenu(resources.getString("fileMenu.text"));
    private final JMenu editMenu = new JMenu(resources.getString("editMenu.text"));
    private final JMenuItem miUndo = new JMenuItem(resources.getString("miUndo.text"));
    private final JMenuItem miRedo = new JMenuItem(resources.getString("miRedo.text"));
    private final JMenuItem miReload = new JMenuItem(resources.getString("miReload.text"));
    private final JMenuItem miAddToForce = new JMenuItem(resources.getString("miAddToForce.text"));

    public MenuBar(MenuBarOwner owner) {
        this.owner = owner;
        initialize();
    }

    /**
     * Returns the unit main UI, if this menubar is attached to one (instead of the StartupGUI aka splash screen), null
     * otherwise. Under the Tabbed UI, returns the main UI for the currently selected tab.
     *
     * @return The unit main UI of this menubar or null
     */
    public @Nullable MegaMekLabMainUI getUnitMainUi() {
        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            return tabbedUI.getActiveEditor();
        } else {
            return null;
        }
    }

    /**
     * The File menu's exit handler pops up a modal that produces a boolean; to match the functionality of
     * ExitOnWindowClosingListener, we want to exit if that boolean is true. Otherwise, do nothing.
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
        if (!isStartupGui()) {
            add(createEditMenu());
        }
        add(createUnitValidationMenu());
        add(createForceBuildMenu());
        add(createReportsMenu());
        add(createHelpMenu());
        loadUnitFileChooser.setDialogTitle(resources.getString("dialog.chooseUnit.title"));
        loadUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files",
              "mtf", "blk", "hmp", "hmv", "mep", "tdb"));
        loadImageFileChooser.setDialogTitle(resources.getString("dialog.chooseUnit.title"));
        loadImageFileChooser.setFileFilter(new FileNameExtensionFilter("Image files (.png, .jpg, .gif)",
              "png", "jpg", "jpeg", "gif"));
    }

    private JMenuItem newUnitItem(String text, int mnemonic, long type, boolean primitive) {
        final JMenuItem miNewUnit = new JMenuItem(text);
        if (mnemonic > 0) {
            miNewUnit.setMnemonic(mnemonic);
        }
        miNewUnit.addActionListener(evt -> {
            MegaMekLabTabbedUI tabbedUI;
            if (owner instanceof MegaMekLabTabbedUI) {
                tabbedUI = (MegaMekLabTabbedUI) owner;
            } else {
                tabbedUI = new MegaMekLabTabbedUI();
                tabbedUI.setVisible(true);
                if (isStartupGui()) {
                    owner.getFrame().setVisible(false);
                    owner.getFrame().dispose();
                }
            }
            tabbedUI.createNewUnit(type, primitive, false);
        });
        return miNewUnit;
    }

    private JMenu createFileMenu() {
        fileMenu.removeAll();
        fileMenu.setName("fileMenu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenu miNewTab = new JMenu(resources.getString("miNewTab.text"));
        miNewTab.setName("miNewTab");
        miNewTab.setMnemonic(KeyEvent.VK_N);
        fileMenu.add(miNewTab);

        JMenuItem newMek = newUnitItem("Mek", KeyEvent.VK_M, Entity.ETYPE_MEK, false);
        newMek.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        miNewTab.add(newMek);
        miNewTab.add(newUnitItem("Fighter", KeyEvent.VK_F, Entity.ETYPE_AERO, false));
        miNewTab.add(newUnitItem("DropShip/Small Craft", KeyEvent.VK_D, Entity.ETYPE_DROPSHIP, false));
        miNewTab.add(newUnitItem("Advanced Aerospace", KeyEvent.VK_A, Entity.ETYPE_JUMPSHIP, false));
        miNewTab.add(newUnitItem("Tank", KeyEvent.VK_T, Entity.ETYPE_TANK, false));
        miNewTab.add(newUnitItem("Support Vehicle", KeyEvent.VK_V, Entity.ETYPE_SUPPORT_TANK, false));
        miNewTab.add(newUnitItem("Battle Armor", KeyEvent.VK_B, Entity.ETYPE_BATTLEARMOR, false));
        miNewTab.add(newUnitItem("Conventional Infantry", KeyEvent.VK_I, Entity.ETYPE_INFANTRY, false));
        miNewTab.add(newUnitItem("ProtoMek", KeyEvent.VK_P, Entity.ETYPE_PROTOMEK, false));
        miNewTab.add(newUnitItem("Handheld Weapon", KeyEvent.VK_H, Entity.ETYPE_HANDHELD_WEAPON, false));
        miNewTab.add(newUnitItem("Gun Emplacement", KeyEvent.VK_G, Entity.ETYPE_GUN_EMPLACEMENT, false));

        JMenu primitive = new JMenu("Primitive...");
        primitive.add(newUnitItem("Mek", KeyEvent.VK_M, Entity.ETYPE_MEK, true));
        primitive.add(newUnitItem("Fighter", KeyEvent.VK_F, Entity.ETYPE_AERO, true));
        primitive.add(newUnitItem("DropShip/Small Craft", KeyEvent.VK_D, Entity.ETYPE_DROPSHIP, true));
        primitive.add(newUnitItem("JumpShip", KeyEvent.VK_J, Entity.ETYPE_JUMPSHIP, true));
        miNewTab.add(primitive);

        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            final JMenuItem miCloseTab = new JMenuItem(resources.getString("miCloseTab.text"));
            miCloseTab.setName("miCloseTab");
            miCloseTab.setMnemonic(KeyEvent.VK_W);
            miCloseTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
            miCloseTab.addActionListener(e -> {
                if (tabbedUI.safetyPrompt()) {
                    tabbedUI.closeCurrentTab();
                }
            });
            fileMenu.add(miCloseTab);

            final JMenuItem miReopenTab = new JMenuItem(resources.getString("miReopenTab.text"));
            miReopenTab.setName("miReopenTab");
            miReopenTab.setAccelerator(
                  KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
            miReopenTab.addActionListener(e -> tabbedUI.reopenTab());
            miReopenTab.setEnabled(tabbedUI.hasClosedTabs());
            fileMenu.add(miReopenTab);
        }

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

    private void switchUnitType(long type) {
        switchUnitType(type, false);
    }

    private void switchUnitType(long type, boolean primitive) {
        if (!isStartupGui() && !getUnitMainUi().safetyPrompt()) {
            return;
        }
        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            tabbedUI.switchUnit(type, primitive);
        }
    }

    private JMenu createSwitchUnitTypeMenu() {
        final JMenu switchUnitTypeMenu = new JMenu(resources.getString("switchUnitTypeMenu.text"));
        switchUnitTypeMenu.setName("switchUnitTypeMenu");
        switchUnitTypeMenu.setMnemonic(KeyEvent.VK_S);

        final Entity entity = owner.getEntity();

        if ((entity == null) || (!(entity instanceof Mek) || entity.isPrimitive())) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.addActionListener(evt -> switchUnitType(Entity.ETYPE_MEK));
            switchUnitTypeMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && entity.isPrimitive()))) {
            final JMenuItem miSwitchToFighter = new JMenuItem(resources.getString("miSwitchToFighter.text"));
            miSwitchToFighter.setName("miSwitchToFighter");
            miSwitchToFighter.setMnemonic(KeyEvent.VK_A);
            miSwitchToFighter.addActionListener(evt -> switchUnitType(Entity.ETYPE_AERO));
            switchUnitTypeMenu.add(miSwitchToFighter);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || entity.isPrimitive())) {
            final JMenuItem item = new JMenuItem(resources.getString("miSwitchToDropShipSmallCraft.text"));
            item.setName("miSwitchToDropShipSmallCraft");
            item.setMnemonic(KeyEvent.VK_D);
            item.addActionListener(evt -> switchUnitType(Entity.ETYPE_DROPSHIP));
            switchUnitTypeMenu.add(item);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || entity.isPrimitive())) {
            final JMenuItem miSwitchToAdvancedAero = new JMenuItem(resources.getString("miSwitchToAdvancedAero.text"));
            miSwitchToAdvancedAero.setName("miSwitchToAdvancedAero");
            miSwitchToAdvancedAero.setMnemonic(KeyEvent.VK_J);
            miSwitchToAdvancedAero.addActionListener(evt -> switchUnitType(Entity.ETYPE_JUMPSHIP));
            switchUnitTypeMenu.add(miSwitchToAdvancedAero);
        }

        if ((entity == null) || (!(entity instanceof Tank) || entity.isSupportVehicle())) {
            final JMenuItem miSwitchToCombatVehicle = new JMenuItem(
                  resources.getString("miSwitchToCombatVehicle.text"));
            miSwitchToCombatVehicle.setName("miSwitchToCombatVehicle");
            miSwitchToCombatVehicle.setMnemonic(KeyEvent.VK_C);
            miSwitchToCombatVehicle.addActionListener(evt -> switchUnitType(Entity.ETYPE_TANK));
            switchUnitTypeMenu.add(miSwitchToCombatVehicle);
        }

        if ((entity == null) || (!entity.isSupportVehicle())) {
            final JMenuItem miSwitchToSupportVehicle = new JMenuItem(
                  resources.getString("miSwitchToSupportVehicle.text"));
            miSwitchToSupportVehicle.setName("miSwitchToSupportVehicle");
            miSwitchToSupportVehicle.setMnemonic(KeyEvent.VK_S);
            miSwitchToSupportVehicle.addActionListener(evt -> switchUnitType(Entity.ETYPE_SUPPORT_TANK));
            switchUnitTypeMenu.add(miSwitchToSupportVehicle);
        }

        if (!(entity instanceof BattleArmor)) {
            final JMenuItem miSwitchToBattleArmor = new JMenuItem(resources.getString("miSwitchToBattleArmor.text"));
            miSwitchToBattleArmor.setName("miSwitchToBattleArmor");
            miSwitchToBattleArmor.setMnemonic(KeyEvent.VK_B);
            miSwitchToBattleArmor.addActionListener(evt -> switchUnitType(Entity.ETYPE_BATTLEARMOR));
            switchUnitTypeMenu.add(miSwitchToBattleArmor);
        }

        if ((entity == null) || (!entity.isConventionalInfantry())) {
            final JMenuItem miSwitchToInfantry = new JMenuItem(resources.getString("miSwitchToInfantry.text"));
            miSwitchToInfantry.setName("miSwitchToInfantry");
            miSwitchToInfantry.setMnemonic(KeyEvent.VK_I);
            miSwitchToInfantry.addActionListener(evt -> switchUnitType(Entity.ETYPE_INFANTRY));
            switchUnitTypeMenu.add(miSwitchToInfantry);
        }

        if ((entity == null) || (!entity.hasETypeFlag(Entity.ETYPE_PROTOMEK))) {
            final JMenuItem miSwitchToProtoMek = new JMenuItem(resources.getString("miSwitchToProtoMek.text"));
            miSwitchToProtoMek.setName("miSwitchToProtoMek");
            miSwitchToProtoMek.setMnemonic(KeyEvent.VK_P);
            miSwitchToProtoMek.addActionListener(evt -> switchUnitType(Entity.ETYPE_PROTOMEK));
            switchUnitTypeMenu.add(miSwitchToProtoMek);
        }

        if ((entity == null) || (!entity.hasETypeFlag(Entity.ETYPE_HANDHELD_WEAPON))) {
            final JMenuItem miSwitchToHandheldWeapon = new JMenuItem(
                  resources.getString("miSwitchToHandheldWeapon.text"));
            miSwitchToHandheldWeapon.setName("miSwitchToHandheldWeapon");
            miSwitchToHandheldWeapon.setMnemonic(KeyEvent.VK_H);
            miSwitchToHandheldWeapon.addActionListener(evt -> switchUnitType(Entity.ETYPE_HANDHELD_WEAPON));
            switchUnitTypeMenu.add(miSwitchToHandheldWeapon);
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

        if ((entity == null) || (!(entity instanceof Mek) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToMek = new JMenuItem(resources.getString("miSwitchToMek.text"));
            miSwitchToMek.setName("miSwitchToMek");
            miSwitchToMek.setMnemonic(KeyEvent.VK_M);
            miSwitchToMek.addActionListener(evt -> switchUnitType(Entity.ETYPE_MEK, true));
            primitiveMenu.add(miSwitchToMek);
        }

        if ((entity == null) || (!entity.isFighter() || ((entity instanceof Aero) && !entity.isPrimitive()))) {
            final JMenuItem miSwitchToAero = new JMenuItem(resources.getString("miSwitchToAero.text"));
            miSwitchToAero.setName("miSwitchToAero");
            miSwitchToAero.setMnemonic(KeyEvent.VK_A);
            miSwitchToAero.addActionListener(evt -> switchUnitType(Entity.ETYPE_AERO, true));
            primitiveMenu.add(miSwitchToAero);
        }

        if ((entity == null) || (!(entity instanceof SmallCraft) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToDropShipSmallCraft = new JMenuItem(
                  resources.getString("miSwitchToDropShipSmallCraft.text"));
            miSwitchToDropShipSmallCraft.setName("miSwitchToDropShipSmallCraft");
            miSwitchToDropShipSmallCraft.setMnemonic(KeyEvent.VK_D);
            miSwitchToDropShipSmallCraft.addActionListener(evt -> switchUnitType(Entity.ETYPE_DROPSHIP, true));
            primitiveMenu.add(miSwitchToDropShipSmallCraft);
        }

        if ((entity == null) || (!(entity instanceof Jumpship) || !entity.isPrimitive())) {
            final JMenuItem miSwitchToJumpShip = new JMenuItem(resources.getString("miSwitchToJumpShip.text"));
            miSwitchToJumpShip.setName("miSwitchToJumpShip");
            miSwitchToJumpShip.setMnemonic(KeyEvent.VK_J);
            miSwitchToJumpShip.addActionListener(evt -> switchUnitType(Entity.ETYPE_JUMPSHIP, true));
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

    public void saveUnitAs() {
        MegaMekLabMainUI mainUI = getUnitMainUi();
        if (mainUI == null) {
            return;
        }
        mainUI.saveUnitAs();
    }

    public void saveUnit() {
        MegaMekLabMainUI mainUI = getUnitMainUi();
        if (mainUI == null) {
            return;
        }
        mainUI.saveUnit();
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
        exportMenu.add(createDiscordClipboardUnitExportMenu());

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

        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            final JMenuItem miExportAllUnitsToPDF = new JMenuItem(resources.getString("AllUnits.text"));
            miExportAllUnitsToPDF.setName("miExportAllUnitsToPDF");
            miExportAllUnitsToPDF.setMnemonic(KeyEvent.VK_A);
            miExportAllUnitsToPDF.addActionListener(
                  evt -> new PrintQueueDialog(tabbedUI, true, tabbedUI.getAllEntities(), false, "").setVisible(true));
            pdfUnitExportMenu.add(miExportAllUnitsToPDF);
        }

        final JMenuItem miExportUnitFromCacheToPDF = new JMenuItem(resources.getString("FromCache.text"));
        miExportUnitFromCacheToPDF.setName("miExportUnitFromCacheToPDF");
        miExportUnitFromCacheToPDF.setMnemonic(KeyEvent.VK_C);
        miExportUnitFromCacheToPDF.addActionListener(evt -> UnitPrintManager.printSelectedUnit(owner.getFrame(), true));
        pdfUnitExportMenu.add(miExportUnitFromCacheToPDF);

        final JMenuItem miExportUnitFromFileToPDF = new JMenuItem(resources.getString("FromFile.text"));
        miExportUnitFromFileToPDF.setName("miExportUnitFromFileToPDF");
        miExportUnitFromFileToPDF.setMnemonic(KeyEvent.VK_F);
        miExportUnitFromFileToPDF
              .addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), false, true));
        pdfUnitExportMenu.add(miExportUnitFromFileToPDF);

        final JMenuItem miExportUnitFromFileToSinglePDFPage = new JMenuItem(resources.getString("FromFileSingle.text"));
        miExportUnitFromFileToSinglePDFPage.setName("miExportUnitFromFileToSinglePDFPage");
        miExportUnitFromFileToSinglePDFPage.setMnemonic(KeyEvent.VK_S);
        miExportUnitFromFileToSinglePDFPage
              .addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), true, true));
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
        miExportCurrentUnitToHTML.addActionListener(evt -> exportSummary(ViewFormatting.HTML));
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
        miExportCurrentUnitToText.addActionListener(evt -> exportSummary(ViewFormatting.NONE));
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
            StringSelection stringSelection = new StringSelection(entitySummaryText(ViewFormatting.NONE));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, this);
        });
        miExportCurrentUnitToClipboard.setEnabled(isUnitGui());
        clipboardUnitExportMenu.add(miExportCurrentUnitToClipboard);

        return clipboardUnitExportMenu;
    }

    /**
     * @return the created Clipboard Unit Export menu
     */
    private JMenu createDiscordClipboardUnitExportMenu() {
        final JMenu clipboardUnitExportMenu = new JMenu(resources.getString("discordClipboardUnitExportMenu.text"));
        clipboardUnitExportMenu.setName("discordClipboardUnitExportMenu");
        clipboardUnitExportMenu.setMnemonic(KeyEvent.VK_D);

        final JMenuItem miExportCurrentUnitToClipboard = new JMenuItem(resources.getString("CurrentUnit.text"));
        miExportCurrentUnitToClipboard.setName("miExportCurrentUnitToClipboard");
        miExportCurrentUnitToClipboard.setMnemonic(KeyEvent.VK_U);
        miExportCurrentUnitToClipboard.addActionListener(evt -> {
            warnOnInvalid();
            StringSelection stringSelection = new StringSelection(entitySummaryText(ViewFormatting.DISCORD));
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

        if (owner instanceof MegaMekLabTabbedUI tabbedUI) {
            final JMenuItem miPrintAllUnits = new JMenuItem(resources.getString("AllUnits.text"));
            miPrintAllUnits.setName("miPrintAllUnits");
            miPrintAllUnits.setMnemonic(KeyEvent.VK_A);
            miPrintAllUnits.setAccelerator(
                  KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
            miPrintAllUnits.addActionListener(
                  evt -> new PrintQueueDialog(tabbedUI, false, tabbedUI.getAllEntities(), false, "")
                        .setVisible(true));
            printMenu.add(miPrintAllUnits);
        }

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
        miPrintUnitFromFileToSinglePage
              .addActionListener(evt -> UnitPrintManager.printUnitFile(owner.getFrame(), true, false));
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
        miRefreshUnitCache.addActionListener(evt -> MekSummaryCache.refreshUnitData(false));
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
     *
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
            miLookAndFeel.setSelected(laf.getClassName().equals(UIManager.getLookAndFeel().getClass().getName()));
            miLookAndFeel.addActionListener(evt -> {
                owner.changeTheme(laf);
                CConfig.setParam(CConfig.GUI_PLAF, laf.getClassName());
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

    private @Nullable JMenuItem createCConfigMenuItem(final String recentFileName, final int fileNumber) {
        File recent = new File(recentFileName);
        String path = recent.getParent();
        String mmlDirectory = System.getProperty("user.dir");
        if (recentFileName.startsWith(mmlDirectory)) {
            path = path.substring(mmlDirectory.length());
            if (path.length() > 40) {
                path = path.substring(0, 40) + "...";
            }
        }

        final JMenuItem miCConfig = getMiCConfig(fileNumber, recent, path);
        miCConfig.setName("miCConfig");
        miCConfig.addActionListener(evt -> loadUnitFromFile(fileNumber));
        miCConfig.setMnemonic(48 + fileNumber); // the number itself, i.e. 1, 2, 3 etc.
        return miCConfig;
    }

    private static JMenuItem getMiCConfig(int fileNumber, File recent, String path) {
        String content;
        if (OSUtil.isMac()) {
            content = "%d. %s ".formatted(fileNumber, recent.getName()) + "(" + path + ")";
        } else {
            String html = "<HTML><HEAD><STYLE>%s</STYLE></HEAD><BODY>%s</BODY></HTML>";
            String style = ".small { font-size:smaller; color:gray; }";
            content = html.formatted(style, "<NOBR>%d. %s<BR>".formatted(fileNumber,
                  recent.getName()) + UIUtil.spanCSS("small", path));
        }

        return new JMenuItem(content);
    }

    private boolean activeEditorHasRedo() {
        if (!isUnitGui()) {
            return false;
        }
        MegaMekLabMainUI mainUi = getUnitMainUi();
        if (mainUi != null) {
            return mainUi.hasRedo();
        }
        return false;
    }

    private boolean activeEditorHasUndo() {
        if (!isUnitGui()) {
            return false;
        }
        MegaMekLabMainUI mainUi = getUnitMainUi();
        if (mainUi != null) {
            return mainUi.hasUndo();
        }
        return false;
    }

    private boolean activeEditorCanReload() {
        if (!isUnitGui()) {
            return false;
        }
        MegaMekLabMainUI mainUi = getUnitMainUi();
        if (mainUi != null) {
            return mainUi.canReload();
        }
        return false;
    }


    private JMenuItem miSwitchUnitType;

    /**
     * * Refreshes the state of the Edit menu items based on the current state of the active editor.
     */
    public void refreshEditMenu() {
        miUndo.setEnabled(activeEditorHasUndo());
        miRedo.setEnabled(activeEditorHasRedo());
        miReload.setEnabled(activeEditorCanReload());

        if (miSwitchUnitType != null) {
            editMenu.remove(miSwitchUnitType);
        }
        miSwitchUnitType = createSwitchUnitTypeMenu();
        editMenu.add(miSwitchUnitType);
    }

    private void refreshForceMenu() {
        miAddToForce.setEnabled(isUnitGui());
    }

    /**
     * @return the created Edit menu
     */
    private JMenu createEditMenu() {
        editMenu.removeAll();
        editMenu.setName("editMenu");
        editMenu.setMnemonic(KeyEvent.VK_E);

        miUndo.setName("miUndo");
        miUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        miUndo.setMnemonic(KeyEvent.VK_U);
        miUndo.addActionListener(evt -> {
            MegaMekLabMainUI mainUi = getUnitMainUi();
            if (mainUi != null) {
                mainUi.undo();
            }
        });
        editMenu.add(miUndo);

        miRedo.setName("miRedo");
        miRedo.setMnemonic(KeyEvent.VK_R);
        miRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        miRedo.addActionListener(evt -> {
            MegaMekLabMainUI mainUi = getUnitMainUi();
            if (mainUi != null) {
                mainUi.redo();
            }
        });
        editMenu.add(miRedo);

        editMenu.addSeparator();

        miReload.setName("miReload");
        miReload.setMnemonic(KeyEvent.VK_L);
        miReload.addActionListener(evt -> {
            MegaMekLabMainUI mainUi = getUnitMainUi();
            if (mainUi != null) {
                if (JOptionPane.showConfirmDialog(owner.getFrame(),
                      "All unsaved changes in the current unit will be discarded.\n" +
                            "Are you sure you want to reload the unit?",
                      "Reload Unit",
                      JOptionPane.YES_NO_OPTION,
                      JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    mainUi.reload();
                }
            }
        });
        editMenu.add(miReload);

        editMenu.addSeparator();

        final JMenuItem miResetCurrentUnit = new JMenuItem(resources.getString("miResetCurrentUnit.text"));
        miResetCurrentUnit.setName("miResetCurrentUnit");
        miResetCurrentUnit.setMnemonic(KeyEvent.VK_E);
        miResetCurrentUnit.addActionListener(this::jMenuResetEntity_actionPerformed);
        miResetCurrentUnit.setEnabled(isUnitGui());
        editMenu.add(miResetCurrentUnit);

        refreshEditMenu();
        return editMenu;
    }

    /**
     * @return the created Unit Validation menu
     */
    private JMenu createUnitValidationMenu() {
        final JMenu unitValidationMenu = new JMenu(resources.getString("unitValidationMenu.text"));
        unitValidationMenu.setName("unitValidationMenu");
        unitValidationMenu.setMnemonic(KeyEvent.VK_V);

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
     * Creates the force menu
     *
     * @return the created force menu
     */
    private JMenu createForceBuildMenu() {
        final JMenu forceMenu = new JMenu(resources.getString("forceBuildMenu.text"));
        forceMenu.setName("forceBuildMenu");
        forceMenu.setMnemonic(KeyEvent.VK_B);

        miAddToForce.setName("miAddToForce");
        miAddToForce.setMnemonic(KeyEvent.VK_A);
        miAddToForce.addActionListener(evt -> addUnitToForce(owner.getEntity()));
        forceMenu.add(miAddToForce);

        final JMenuItem miViewForce = new JMenuItem(resources.getString("miViewForce.text"));
        miViewForce.setName("miViewForce");
        miViewForce.setMnemonic(KeyEvent.VK_V);
        miViewForce.addActionListener(evt -> viewForce());
        forceMenu.add(miViewForce);

        refreshForceMenu();

        return forceMenu;

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
        miCurrentUnitCostBreakdown
              .addActionListener(evt -> new CostDisplayDialog(owner.getFrame(), owner.getEntity()).setVisible(true));
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
        miCurrentUnitWeightBreakdown
              .addActionListener(evt -> showUnitWeightBreakDown(owner.getEntity(), owner.getFrame()));
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
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog,
              false);
        try {
            Entity chosenEntity = viewer.getChosenEntity();
            if (chosenEntity != null) {
                UnitUtil.showValidation(chosenEntity, owner.getFrame());
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog,
              false);
        try {
            Entity chosenEntity = viewer.getChosenEntity();
            if (chosenEntity != null) {
                new CostDisplayDialog(owner.getFrame(), chosenEntity).setVisible(true);
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(owner.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(owner.getFrame(), unitLoadingDialog,
              false);
        try {
            Entity chosenEntity = viewer.getChosenEntity();
            if (chosenEntity != null) {
                showUnitWeightBreakDown(chosenEntity, owner.getFrame());
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {
        File unitFile = chooseUnitFileToLoad();
        if (unitFile == null) {
            return;
        }

        try {
            showBVCalculations(owner.getFrame(), new MekFileParser(unitFile).getEntity());
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
            Entity tempEntity = new MekFileParser(unitFile).getEntity();
            UnitUtil.showValidation(tempEntity, owner.getFrame());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    /**
     * Shows a file chooser for a unit file to open. Returns the unit file if one was chosen; returns null if none was
     * chosen or the dialog was cancelled.
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
            new CostDisplayDialog(owner.getFrame(), new MekFileParser(unitFile).getEntity()).setVisible(true);
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
            Entity tempEntity = new MekFileParser(unitFile).getEntity();
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
            Entity tempEntity = new MekFileParser(unitFile).getEntity();
            new EntityReadoutDialog(owner.getFrame(), tempEntity).setVisible(true);
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
        child.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // set the text up.
        JLabel version = new JLabel(String.format(resources.getString("menu.help.about.version.format"),
              MMLConstants.VERSION));
        JEditorPane body = new JEditorPane();
        body.setContentType("text/html");
        body.setEditable(false);
        body.setOpaque(false);
        body.setText(resources.getString("menu.help.about.text"));

        body.addHyperlinkListener(e -> {
            if (e.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
                if (java.awt.Desktop.isDesktopSupported()) {
                    try {
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        logger.error(ex, "Could not open link: {}", e.getURL());
                    }
                }
            }
        });

        // center everything
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        body.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(new JLabel("\n"));
        child.add(version);
        child.add(new JLabel("\n"));
        child.add(body);

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
        JEditorPane recordSheetImageHelp = new JEditorPane();
        recordSheetImageHelp.setContentType("text/html");
        recordSheetImageHelp.setEditable(false);
        recordSheetImageHelp.setOpaque(false);
        recordSheetImageHelp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recordSheetImageHelp.setText(resources.getString("menu.help.imageHelp.text"));

        recordSheetImageHelp.addHyperlinkListener(e -> {
            if (e.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
                if (java.awt.Desktop.isDesktopSupported()) {
                    try {
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        logger.error(ex, "Could not open link: {}", e.getURL());
                    }
                }
            }
        });

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
        MegaMekLabMainUI mainUI = getUnitMainUi();
        if (mainUI == null) {
            return;
        }
        if (en instanceof Tank) {
            mainUI.createNewUnit(Entity.ETYPE_TANK);
        } else if (en instanceof Mek) {
            mainUI.createNewUnit(Entity.ETYPE_BIPED_MEK, en.isPrimitive(), ((Mek) en).isIndustrial());
        } else if (en.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            mainUI.createNewUnit(Entity.ETYPE_DROPSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            mainUI.createNewUnit(Entity.ETYPE_SMALL_CRAFT, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
            mainUI.createNewUnit(Entity.ETYPE_SPACE_STATION);
        } else if (en.hasETypeFlag(Entity.ETYPE_WARSHIP)) {
            mainUI.createNewUnit(Entity.ETYPE_WARSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            mainUI.createNewUnit(Entity.ETYPE_JUMPSHIP);
        } else if (owner.getEntity() instanceof Aero) {
            mainUI.createNewUnit(Entity.ETYPE_AERO, en.isPrimitive());
        } else if (owner.getEntity() instanceof BattleArmor) {
            mainUI.createNewUnit(Entity.ETYPE_BATTLEARMOR);
        } else if (owner.getEntity() instanceof Infantry) {
            mainUI.createNewUnit(Entity.ETYPE_INFANTRY);
        } else if (owner.getEntity() instanceof ProtoMek) {
            mainUI.createNewUnit(Entity.ETYPE_PROTOMEK);
        } else {
            logger.warn("Received unknown entityType!");
        }
        reload();
        refresh();
        mainUI.repaint();
    }

    private String entitySummaryText(ViewFormatting formatting) {
        if (CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO) && formatting != ViewFormatting.DISCORD) {
            TROView view = TROView.createView(owner.getEntity(), formatting);
            return view.processTemplate();
        } else {
            EntityReadout view = EntityReadout.createReadout(owner.getEntity(),
                  formatting == ViewFormatting.NONE,
                  false);
            return view.getFullReadout(formatting);
        }
    }

    private void exportSummary(ViewFormatting formatting) {
        warnOnInvalid();

        String unitName = owner.getEntity().getChassis() + ' ' + owner.getEntity().getModel();

        MMLFileChooser fileChooser = new MMLFileChooser();
        fileChooser.setDialogTitle(resources.getString("dialog.saveAs.title"));
        fileChooser.setSelectedFile(new File(unitName + (formatting == ViewFormatting.HTML ? ".html" : ".txt")));
        int result = fileChooser.showSaveDialog(owner.getFrame());
        if ((result != JFileChooser.APPROVE_OPTION) || (fileChooser.getSelectedFile() == null)) {
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile());
              PrintStream ps = new PrintStream(fos)) {
            ps.println(entitySummaryText(formatting));
        } catch (Exception ex) {
            PopupMessages.showFileWriteError(owner.getFrame(), ex.getMessage());
            logger.error("", ex);
        }
    }

    public void loadUnitFromFile(int fileNumber) {
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

        loadFile(unitFile);
    }

    public void loadFile(File unitFile) {
        try {
            Entity loadedUnit = new MekFileParser(unitFile).getEntity();

            if (loadedUnit == null) {
                throw new Exception();
            }

            warnOnInvalid(loadedUnit);

            newRecentUnit(unitFile.toString());
            if (owner instanceof MegaMekLabTabbedUI tabbedUi) {
                tabbedUi.addUnit(loadedUnit, unitFile.toString(), true);
                refresh();
            } else if (isStartupGui() || (loadedUnit.getEntityType() != owner.getEntity().getEntityType())) {
                owner.getFrame().setVisible(false);
                owner.getFrame().dispose();
                UiLoader.loadUi(loadedUnit, unitFile.toString());
            }
        } catch (Exception ex) {
            PopupMessages.showFileReadError(owner.getFrame(), unitFile.toString(), ex.getMessage());
        }
    }

    /**
     * Adds a new most recent unit, moving the previous recent units down the list. Renews this menubar to reflect the
     * change.
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
        MegaMekLabMainUI mainUi = getUnitMainUi();
        if (mainUi != null) {
            mainUi.reloadTabs();
        }
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {
    }

    private boolean isStartupGui() {
        return owner instanceof StartupGUI;
    }

    private boolean isUnitGui() {
        return !isStartupGui();
    }

    /**
     * Refreshes this menubar to show options relevant to the currently selected unit.
     */
    public void refreshMenuBar() {
        createFileMenu();
        refreshEditMenu();
        refreshForceMenu();
    }

    /**
     * Performs a settings import, currently only for the megameklab.properties file (CConfig). Shows a help message
     * before showing a file chooser for selecting a directory. The directory should always be MML's main directory
     * (which contains the mmconf directory with the megameklab.properties file).
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

    public void viewForce() {
        ForceBuildUI.showWindow();
    }

    public void addUnitToForce(Entity entity) {
        if (entity != null) {
            ForceBuildUI.showAndAddEntity(entity);
        }
    }
}
