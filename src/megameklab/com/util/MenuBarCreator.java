/*
 * MegaMekLab - Copyright (C) 2011-2019 The MegaMek Team
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.util;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.BLKFile;
import megamek.common.templates.TROView;
import megamek.common.util.EncodeControl;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.dialog.LoadingDialog;
import megameklab.com.ui.dialog.MegaMekLabUnitSelectorDialog;

public class MenuBarCreator extends JMenuBar implements ClipboardOwner {

    private static final long serialVersionUID = -3998342610654551481L;
    private final JMenu themeMenu;
    private final MegaMekLabMainUI parentFrame;

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Menu", new EncodeControl());

    public MenuBarCreator(MegaMekLabMainUI parent) {

        parentFrame = parent;

        themeMenu = createThemeMenu();
        JMenu fileMenu = createFileMenu();

        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.help.about"));
        item.setMnemonic(KeyEvent.VK_A);
        item.addActionListener(e -> jMenuHelpAbout_actionPerformed());
        JMenu help = new JMenu(resourceMap.getString("menu.help"));
        help.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.help.recordSheetImages"));
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(e -> jMenuHelpFluff_actionPerformed());
        help.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.help.insertImage"));
        item.setMnemonic(KeyEvent.VK_I);
        item.addActionListener(e -> jMenuInsertImageFile_actionPerformed());
        help.add(item);

        JMenu validate = new JMenu(resourceMap.getString("menu.validate"));
        validate.add(loadBVMenuOptions());

        validate.add(loadValidateMenuOptions());

        validate.add(loadSpecsMenuOptions());

        validate.add(loadUnitCostBreakdownMenuOptions());
        
        validate.add(loadUnitWeightBreakdownMenuOptions());

        this.add(fileMenu);
        this.add(validate);
        this.add(help);

    }

    private JMenu loadBVMenuOptions() {
        JMenu bv = new JMenu(resourceMap.getString("menu.validate.bv"));
        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.bv.currentUnit"));
        item.setMnemonic(KeyEvent.VK_B);
        item.addActionListener(e -> jMenuBVCalculations_actionPerformed());
        bv.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.bv.fromFile"));
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(e -> jMenuGetUnitBVFromFile_actionPerformed());
        bv.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.bv.fromCache"));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(e -> jMenuGetUnitBVFromCache_actionPerformed());
        bv.add(item);
        return bv;
    }

    private JMenu loadValidateMenuOptions() {
        JMenu entityValidation = new JMenu(resourceMap.getString("menu.validate"));
        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.currentUnit"));
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(e -> jMenuValidateUnit_actionPerformed());
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.fromFile"));
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(e -> jMenuGetUnitValidationFromFile_actionPerformed());
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.fromCache"));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(e -> jMenuGetUnitValidationFromCache_actionPerformed());
        entityValidation.add(item);
        return entityValidation;
    }

    private JMenu loadUnitCostBreakdownMenuOptions() {
        JMenu entityBreakdown = new JMenu(resourceMap.getString("menu.validate.cost"));
        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.cost.currentUnit"));
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(e -> jMenuUnitCostBreakdown_actionPerformed());
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.cost.fromFile"));
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(e -> jMenuGetUnitBreakdownFromFile_actionPerformed());
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.cost.fromCache"));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(e -> jMenuGetUnitBreakdownFromCache_actionPerformed());
        entityBreakdown.add(item);
        return entityBreakdown;
    }
    
    private JMenu loadUnitWeightBreakdownMenuOptions() {
        JMenu entityBreakdown = new JMenu(resourceMap.getString("menu.validate.weight"));
        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.weight.currentUnit"));
        item.addActionListener(e -> jMenuUnitWeightBreakdown_actionPerformed());
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.weight.fromFile"));
        item.addActionListener(e -> jMenuGetUnitWeightBreakdownFromFile_actionPerformed());
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.weight.fromCache"));
        item.addActionListener(e -> jMenuGetUnitWeightBreakdownFromCache_actionPerformed());
        entityBreakdown.add(item);
        return entityBreakdown;
    }

    private JMenu loadSpecsMenuOptions() {
        JMenu unitSpecs = new JMenu(resourceMap.getString("menu.validate.specs"));
        JMenuItem item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.specs.currentUnit"));
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(e -> jMenuUnitSpecs_actionPerformed());
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.specs.fromFile"));
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(e -> jMenuGetUnitSpecsFromFile_actionPerformed());
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.validate.specs.fromCache"));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(e -> jMenuGetUnitSpecsFromCache_actionPerformed());
        unitSpecs.add(item);
        return unitSpecs;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu(resourceMap.getString("menu.file"));

        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem(resourceMap.getString("menu.file.resetCurrentUnit"));
        item.setMnemonic(KeyEvent.VK_R);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(this::jMenuResetEntity_actionPerformed);
        fileMenu.add(item);

        JMenu unitMenu = new JMenu(resourceMap.getString("menu.file.switchUnitType"));
        unitMenu.setMnemonic(KeyEvent.VK_S);
        Entity en = parentFrame.getEntity();

        if (!(en instanceof Mech)
                || ((Mech)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.mech"));
            item.setMnemonic(KeyEvent.VK_M);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadMech());
            unitMenu.add(item);
        }

        if (!en.isFighter()
                || ((en instanceof Aero) && ((Aero)en).isPrimitive())) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.fighter"));
            item.setMnemonic(KeyEvent.VK_A);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadAero());
            unitMenu.add(item);
        }

        if (!(en instanceof SmallCraft)
                || ((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.dropshipSmallCraft"));
            item.setMnemonic(KeyEvent.VK_D);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadDropship());
            unitMenu.add(item);
        }

        if (!(en instanceof Jumpship)
                || ((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.advancedAero"));
            item.setMnemonic(KeyEvent.VK_J);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadAdvAero());
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof Tank)
                || parentFrame.getEntity().isSupportVehicle()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.combatVehicle"));
            item.setMnemonic(KeyEvent.VK_T);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadVehicle());
            unitMenu.add(item);
        }

        if (!parentFrame.getEntity().isSupportVehicle()) {
            item = new JMenuItem();
            item.setText("Support Vehicle");
            item.addActionListener(e -> jMenuLoadSupportVehicle());
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.battleArmor"));
            item.setMnemonic(KeyEvent.VK_B);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadBattleArmor());
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof Infantry) || (parentFrame.getEntity() instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.infantry"));
            item.setMnemonic(KeyEvent.VK_I);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadInfantry());
            unitMenu.add(item);
        }
        
        if (!parentFrame.getEntity().hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.protomech"));
            item.setMnemonic(KeyEvent.VK_P);
            item.addActionListener(ev -> jMenuLoadProtomech());
            unitMenu.add(item);
        }

        JMenu pMenu = new JMenu(resourceMap.getString("menu.file.unitType.primitive"));
        if (!(en instanceof Mech)
                || !((Mech)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.mech"));
            item.addActionListener(e ->jMenuLoadPrimitiveMech());
            pMenu.add(item);
        }
        
        if (!(en.isFighter())
                || (en instanceof Aero && !((Aero)en).isPrimitive())) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.aero"));
            item.addActionListener(e ->jMenuLoadPrimitiveAero());
            pMenu.add(item);
        }
        
        if (!(en instanceof SmallCraft)
                || !((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.dropshipSmallCraft"));
            item.addActionListener(e ->jMenuLoadPrimitiveDropship());
            pMenu.add(item);
        }
        
        if (!(en instanceof Jumpship)
                || !((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText(resourceMap.getString("menu.file.unitType.jumpship"));
            item.addActionListener(e ->jMenuLoadPrimitiveJumpship());
            pMenu.add(item);
        }
        
        unitMenu.add(pMenu);

        fileMenu.add(unitMenu);

        JMenu loadMenu = new JMenu(resourceMap.getString("menu.file.load"));
        loadMenu.setMnemonic(KeyEvent.VK_L);
        item = new JMenuItem();

        item.setText(resourceMap.getString("menu.file.load.fromCache"));
        item.setMnemonic(KeyEvent.VK_C);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(this::jMenuLoadEntity_actionPerformed);
        loadMenu.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.file.load.fromFile"));
        item.setMnemonic(KeyEvent.VK_F);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(this::jMenuLoadEntityFromFile_actionPerformed);
        loadMenu.add(item);

        fileMenu.add(loadMenu);

        fileMenu.add(UnitPrintManager.printMenu(parentFrame));
        fileMenu.add(UnitPrintManager.exportMenu(parentFrame));

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.file.save"));
        item.setMnemonic(KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(this::jMenuSaveEntity_actionPerformed);
        fileMenu.add(item);

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.file.saveAs"));
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(this::jMenuSaveAsEntity_actionPerformed);
        fileMenu.add(item);

        JMenu exportMenu = new JMenu(resourceMap.getString("menu.file.export"));
        
        item = new JMenuItem(resourceMap.getString("menu.file.export.toHTML"));
        item.addActionListener(e -> exportSummary(true));
        exportMenu.add(item);

        item = new JMenuItem(resourceMap.getString("menu.file.export.toText"));
        item.addActionListener(e -> exportSummary(false));
        exportMenu.add(item);

        item = new JMenuItem(resourceMap.getString("menu.file.export.toClipboard"));
        item.addActionListener(e -> exportSummaryClipboard());
        exportMenu.add(item);

        fileMenu.add(exportMenu);
        
        fileMenu.add(themeMenu);

        item = new JMenuItem(resourceMap.getString("menu.file.configuration"));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(this::jMenuConfiguration_actionPerformed);
        fileMenu.add(item);

        int fileNumber = 1;
        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1).length() > 1) {
            fileMenu.addSeparator();
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(e -> jMenuLoadEntityFromFile_actionPerformed(1));

            fileMenu.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(e -> jMenuLoadEntityFromFile_actionPerformed(2));

            fileMenu.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(e -> jMenuLoadEntityFromFile_actionPerformed(3));

            fileMenu.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(e -> jMenuLoadEntityFromFile_actionPerformed(4));

            fileMenu.add(item);
        }

        fileMenu.addSeparator();

        item = new JMenuItem();
        item.setText(resourceMap.getString("menu.file.exit"));
        item.setMnemonic(KeyEvent.VK_X);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(ev -> parentFrame.exit());
        fileMenu.add(item);

        return fileMenu;
    }
    
    /**
     * Creates a menu that includes all installed look and feel options
     * 
     * @return The new menu
     */
    private JMenu createThemeMenu() {
        JMenu menu = new JMenu(resourceMap.getString("menu.file.themes"));
        JCheckBoxMenuItem item;
        for (LookAndFeelInfo plaf : UIManager.getInstalledLookAndFeels()) {
            item = new JCheckBoxMenuItem(plaf.getName());
            if (plaf.getName().equalsIgnoreCase(
                    UIManager.getLookAndFeel().getName())) {
                item.setSelected(true);
            }
            menu.add(item);
            item.addActionListener(ev -> {
                parentFrame.changeTheme(plaf);
                refreshThemeMenu(plaf.getName());
            });
        }
        return menu;
    }
    
    /**
     * Updates the checkbox items on the theme menu to show which is currently selected.
     * 
     * @param currentThemeName The name returned by {@link LookAndFeelInfo#getName()}
     */
    private void refreshThemeMenu(String currentThemeName) {
        for (int i = 0; i < themeMenu.getItemCount(); i++) {
            final JMenuItem item = themeMenu.getItem(i);
            if (item instanceof JCheckBoxMenuItem) {
                item.setSelected(item.getText().equals(currentThemeName));
            }
        }
    }

    private void jMenuGetUnitBVFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        tempEntity.calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(tempEntity.getBVText(), parentFrame);

    }

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showValidation(tempEntity, parentFrame);

    }

    private void jMenuGetUnitSpecsFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitSpecs(tempEntity, parentFrame);

    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitCostBreakDown(tempEntity, parentFrame);

    }
    
    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitWeightBreakDown(tempEntity, parentFrame);

    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            tempEntity.calculateBattleValue(true, true);
            UnitUtil.showBVCalculations(tempEntity.getBVText(), parentFrame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    String.format(resourceMap.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }

    }

    private void jMenuGetUnitValidationFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) return;

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showValidation(tempEntity, parentFrame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    String.format(resourceMap.getString("message.invalidUnit.format"),
                    ex.getMessage()));
        }
    }

    private @Nullable
    File loadUnitFile() {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/";
        FileDialog fDialog = new FileDialog(parentFrame,
                resourceMap.getString("dialog.chooseUnit.title"),
                FileDialog.LOAD);
        fDialog.setLocationRelativeTo(parentFrame);
        fDialog.setMultipleMode(false);
        fDialog.setDirectory(filePathName);
        fDialog.setFilenameFilter(unitFilesFilter);

        fDialog.setVisible(true);
        if (fDialog.getFile() == null) {
            return null;
        }
        return new File(fDialog.getDirectory(), fDialog.getFile());
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            UnitUtil.showUnitCostBreakDown(tempEntity, parentFrame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    String.format(resourceMap.getString("message.invalidUnit.format"),
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
            UnitUtil.showUnitWeightBreakDown(tempEntity, parentFrame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    String.format(resourceMap.getString("message.invalidUnit.format"),
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
            UnitUtil.showUnitSpecs(tempEntity, parentFrame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    String.format(resourceMap.getString("message.invalidUnit.format"),
                            ex.getMessage()));
        }
    }

    private void jMenuInsertImageFile_actionPerformed() {
        File unitFile = loadUnitFile();
        if (unitFile == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if (UnitUtil.validateUnit(parentFrame.getEntity()).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame,
                        resourceMap.getString("message.invalidUnit.text"));
            }

            FileDialog fDialog = new FileDialog(parentFrame,
                    resourceMap.getString("dialog.imagePath.title"), FileDialog.LOAD);

            if (parentFrame.getEntity().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(parentFrame.getEntity().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath() + File.separatorChar + "mech" + File.separatorChar);
                fDialog.setFile(parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
                parentFrame.getEntity().getFluff().setMMLImagePath(relativeFilePath);
                BLKFile.encode(unitFile.getAbsolutePath(), tempEntity);
            }
        } catch (Exception ex) {
            MegaMekLab.getLogger().error(getClass(), "jMenuInsertImageFile_actionPerformed()", ex);
        }
    }

    // Show BV Calculations

    private void jMenuBVCalculations_actionPerformed() {
        parentFrame.getEntity().calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(parentFrame.getEntity().getBVText(), parentFrame);
    }

    private void jMenuUnitCostBreakdown_actionPerformed() {
        UnitUtil.showUnitCostBreakDown(parentFrame.getEntity(), parentFrame);
    }
    
    private void jMenuUnitWeightBreakdown_actionPerformed() {
        UnitUtil.showUnitWeightBreakDown(parentFrame.getEntity(), parentFrame);
    }

    private void jMenuUnitSpecs_actionPerformed() {
        UnitUtil.showUnitSpecs(parentFrame.getEntity(), parentFrame);
    }

    // Show Validation data.
    private void jMenuValidateUnit_actionPerformed() {
        UnitUtil.showValidation(parentFrame.getEntity(), parentFrame);
    }

    // Show data about MegaMekLab
    private void jMenuHelpAbout_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(parentFrame, resourceMap.getString("menu.help.about.title"));

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JLabel version = new JLabel(String.format(resourceMap.getString("menu.help.about.version.format"),
                MegaMekLab.VERSION));
        JLabel license1 = new JLabel(resourceMap.getString("menu.help.about.license.1"));
        JLabel license2 = new JLabel(resourceMap.getString("menu.help.about.license.2"));
        JLabel license3 = new JLabel(resourceMap.getString("menu.help.about.info.1"));
        JLabel license4 = new JLabel(resourceMap.getString("menu.help.about.info.2"));

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
        dlg.setLocationRelativeTo(parentFrame);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    // Show how to create fluff images for Record Sheets
    private void jMenuHelpFluff_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(parentFrame, resourceMap.getString("menu.help.imageHelp.title"));

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JTextArea recordSheetImageHelp = new JTextArea();

        recordSheetImageHelp.setEditable(false);

        recordSheetImageHelp.setText(resourceMap.getString("menu.help.imageHelp.text"));
        // center everything
        recordSheetImageHelp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(recordSheetImageHelp);

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);

        // set the location of the dialog
        dlg.setLocationRelativeTo(parentFrame);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    private void jMenuConfiguration_actionPerformed(ActionEvent event) {
        new ConfigurationDialog(parentFrame).setVisible(true);
        parentFrame.refreshAll();
    }

    private void jMenuLoadVehicle() {
        newUnit(Entity.ETYPE_TANK, false, false, null);
    }

    private void jMenuLoadSupportVehicle() {
        newUnit(Entity.ETYPE_SUPPORT_TANK, false, false, null);
    }

    private void jMenuLoadBattleArmor() {
        newUnit(Entity.ETYPE_BATTLEARMOR, false, false, null);
    }

    private void jMenuLoadMech() {
        newUnit(Entity.ETYPE_MECH, false, false, null);
    }
    
    private void jMenuLoadPrimitiveMech() {
        newUnit(Entity.ETYPE_MECH, true, false, null);
    }

    private void jMenuLoadAero() {
        newUnit(Entity.ETYPE_AERO, false, false, null);
    }

    private void jMenuLoadPrimitiveAero() {
        newUnit(Entity.ETYPE_AERO, true, false, null);

    }
    
    private void jMenuLoadDropship() {
        newUnit(Entity.ETYPE_DROPSHIP, false, false, null);
    }

    private void jMenuLoadPrimitiveDropship() {
        newUnit(Entity.ETYPE_DROPSHIP, true, false, null);
    }
    
    private void jMenuLoadAdvAero() {
        newUnit(Entity.ETYPE_JUMPSHIP, false, false, null);
    }

    private void jMenuLoadPrimitiveJumpship() {
        newUnit(Entity.ETYPE_JUMPSHIP, true, false, null);
    }

    private void jMenuLoadInfantry() {
        newUnit(Entity.ETYPE_INFANTRY, false, false, null);
    }

    private void jMenuLoadProtomech() {
        newUnit(Entity.ETYPE_PROTOMECH, false, false, null);

    }

    private void jMenuLoadEntity_actionPerformed(ActionEvent event) {
        loadUnit();
    }

    private void jMenuLoadEntityFromFile_actionPerformed(ActionEvent event) {
        loadUnitFromFile();
    }

    private void jMenuLoadEntityFromFile_actionPerformed(int fileNumber) {
        loadUnitFromFile(fileNumber);
    }

    private void jMenuResetEntity_actionPerformed(ActionEvent event) {
        CConfig.updateSaveFiles("Reset Unit");
        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");
        Entity en = parentFrame.getEntity();
        if (en instanceof Tank) {
            parentFrame.createNewUnit(Entity.ETYPE_TANK);
        } else if (en instanceof Mech) {
            parentFrame.createNewUnit(Entity.ETYPE_BIPED_MECH, en.isPrimitive(), ((Mech)en).isIndustrial());
        } else if (en.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            parentFrame.createNewUnit(Entity.ETYPE_DROPSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            parentFrame.createNewUnit(Entity.ETYPE_SMALL_CRAFT, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
            parentFrame.createNewUnit(Entity.ETYPE_SPACE_STATION);
        } else if (en.hasETypeFlag(Entity.ETYPE_WARSHIP)) {
            parentFrame.createNewUnit(Entity.ETYPE_WARSHIP, en.isPrimitive());
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            parentFrame.createNewUnit(Entity.ETYPE_JUMPSHIP);
        } else if (parentFrame.getEntity() instanceof Aero) {
            parentFrame.createNewUnit(Entity.ETYPE_AERO, en.isPrimitive());
        } else if (parentFrame.getEntity() instanceof BattleArmor) {
            parentFrame.createNewUnit(Entity.ETYPE_BATTLEARMOR);
        } else if (parentFrame.getEntity() instanceof Infantry) {
            parentFrame.createNewUnit(Entity.ETYPE_INFANTRY);
        } else if (parentFrame.getEntity() instanceof Protomech) {
            parentFrame.createNewUnit(Entity.ETYPE_PROTOMECH);
        } else {
            MegaMekLab.getLogger().warning(getClass(),
            "jMenuResetEntity_actionPerformed(ActionEvent)",
                "util.MenuBarCreator: Received unknown entityType!");
        }
        setVisible(true);
        reload();
        refresh();
        parentFrame.setVisible(true);
        parentFrame.repaint();
    }

    /**
     * Constructs a file name for the current Entity using the chassis and model name and the
     * correct extension for the unit type. Any character that is not legal for a Windows filename
     * is replace by an underscore.
     *
     * @param entity The Entity
     * @return       A default filename for the Entity
     */
    private String createUnitFilename(Entity entity) {
        String fileName = (entity.getChassis() + " " + entity.getModel()).trim();
        fileName = fileName.replaceAll("[/\\\\<>:\"|?*]", "_");
        if (entity instanceof Mech) {
            return fileName + ".mtf";
        } else {
            return fileName + ".blk";
        }
    }

    private void jMenuSaveEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame,
                    resourceMap.getString("message.invalidUnit.text"));
        }

        String fileName = createUnitFilename(parentFrame.getEntity());
        UnitUtil.compactCriticals(parentFrame.getEntity());

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if ((filePathName.trim().length() < 1) || !filePathName.contains(fileName)) {
            FileDialog fDialog = new FileDialog(parentFrame,
                    resourceMap.getString("dialog.saveAs.title"),
                    FileDialog.SAVE);

            filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

            fDialog.setDirectory(filePathName);
            fDialog.setFile(fileName);
            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
                CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
            } else {
                return;
            }
        }
        try {
            if (parentFrame.getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) parentFrame.getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, parentFrame.getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame,
                String.format(resourceMap.getString("dialog.saveAs.message.format"),
                        parentFrame.getEntity().getChassis(),
                        parentFrame.getEntity().getModel(), filePathName));

    }

    private void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {
        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame,
                    resourceMap.getString("message.savingInvalidUnit.text"));
        }

        UnitUtil.compactCriticals(parentFrame.getEntity());

        FileDialog fDialog = new FileDialog(parentFrame,
                resourceMap.getString("dialog.saveAs.title"), FileDialog.SAVE);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

        fDialog.setDirectory(filePathName);
        fDialog.setFile(createUnitFilename(parentFrame.getEntity()));
        fDialog.setLocationRelativeTo(parentFrame);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
            CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
        } else {
            return;
        }

        try {
            if (parentFrame.getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) parentFrame.getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, parentFrame.getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame,
                String.format(resourceMap.getString("dialog.saveAs.message.format"),
                        parentFrame.getEntity().getChassis(),
                        parentFrame.getEntity().getModel(), filePathName));
    }
    
    private String entitySummaryText(boolean html) {
        if (CConfig.getBooleanParam(CConfig.SUMMARY_FORMAT_TRO)) {
            TROView view = TROView.createView(parentFrame.getEntity(), html);
            return view.processTemplate();
        } else {
            MechView view = new MechView(parentFrame.getEntity(), !html, false, html);
            return view.getMechReadout();
        }
    }

    private void exportSummary(boolean html) {
        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame,
                    resourceMap.getString("message.exportingInvalidUnit.text"));
        }

        String unitName = parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel();

        FileDialog fDialog = new FileDialog(parentFrame,
                resourceMap.getString("dialog.saveAs.title"), FileDialog.SAVE);
        String filePathName = new File(System.getProperty("user.dir")).getAbsolutePath();
        fDialog.setDirectory(filePathName);
        fDialog.setFile(unitName + (html?".html" : ".txt"));
        fDialog.setLocationRelativeTo(parentFrame);
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
            MegaMekLab.getLogger().error(getClass(), "exportSummary(boolean)", ex);
        }
    }

    private void exportSummaryClipboard() {
        final String summaryText = entitySummaryText(false);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(summaryText);
        clipboard.setContents(stringSelection, this);
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parentFrame, unitLoadingDialog);

        Entity newUnit = viewer.getChosenEntity();
        viewer.setVisible(false);
        viewer.dispose();

        if (null == newUnit) {
            return;
        }

        if (UnitUtil.validateUnit(newUnit).trim().length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, String.format(
                    resourceMap.getString("message.invalidUnit.format"),
                            UnitUtil.validateUnit(newUnit)));
        }

        if (newUnit.getEntityType() != parentFrame.getEntity().getEntityType()) {
            if (newUnit.isSupportVehicle()) {
                newUnit(Entity.ETYPE_SUPPORT_TANK, false, false, newUnit);
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                newUnit(Entity.ETYPE_DROPSHIP, ((Aero)newUnit).isPrimitive(), false, newUnit);
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                newUnit(Entity.ETYPE_JUMPSHIP, ((Aero)newUnit).isPrimitive(), false, newUnit);
            } else if ((newUnit instanceof Aero)
                    && !(newUnit instanceof FixedWingSupport)) {
                newUnit(Entity.ETYPE_AERO, ((Aero)newUnit).isPrimitive(), false, newUnit);
            } else if (newUnit instanceof BattleArmor) {
                newUnit(Entity.ETYPE_BATTLEARMOR, false, false, newUnit);
            } else if (newUnit instanceof Infantry) {
                newUnit(Entity.ETYPE_INFANTRY, false, false, newUnit);
            } else if (newUnit instanceof Mech) {
                newUnit(Entity.ETYPE_MECH, false, false, newUnit);
            } else if (newUnit instanceof Protomech) {
                newUnit(Entity.ETYPE_PROTOMECH, false, false, newUnit);
            } else if ((newUnit instanceof Tank)
                    && !(newUnit instanceof GunEmplacement)) {
                newUnit(Entity.ETYPE_TANK, false, false, newUnit);
            } else {
                JOptionPane.showMessageDialog(parentFrame,
                        resourceMap.getString("message.abortUnitLoad.text"));
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
        parentFrame.setEntity(newUnit);
        reload();
        refresh();
        parentFrame.setVisible(true);
    }

    private void loadUnitFromFile() {
        loadUnitFromFile(-1);
    }

    private void loadUnitFromFile(int fileNumber) {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/";

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

        loadUnitFromFile(unitFile);
    }

    private void loadUnitFromFile(File unitFile) {
        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();


            if (null == tempEntity) {
                return;
            }

            if (UnitUtil.validateUnit(tempEntity).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame, String.format(
                        resourceMap.getString("message.invalidUnit.format"),
                                UnitUtil.validateUnit(tempEntity)));
            }

            if (tempEntity.getEntityType() != parentFrame.getEntity().getEntityType()) {
                if (tempEntity.isSupportVehicle()) {
                    newUnit(Entity.ETYPE_SUPPORT_TANK, false, false, tempEntity);
                } else if (tempEntity.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                    newUnit(Entity.ETYPE_DROPSHIP, ((Aero)tempEntity).isPrimitive(), false, tempEntity);
                } else if (tempEntity.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                    newUnit(Entity.ETYPE_JUMPSHIP, ((Aero)tempEntity).isPrimitive(), false, tempEntity);
                } else if ((tempEntity instanceof Aero)
                        && !(tempEntity instanceof FixedWingSupport)) {
                    newUnit(Entity.ETYPE_AERO, ((Aero)tempEntity).isPrimitive(), false, tempEntity);
                } else if (tempEntity instanceof BattleArmor) {
                    newUnit(Entity.ETYPE_BATTLEARMOR, false, false, tempEntity);
                } else if (tempEntity instanceof Infantry) {
                    newUnit(Entity.ETYPE_INFANTRY, false, false, tempEntity);
                } else if (tempEntity instanceof Mech) {
                    newUnit(Entity.ETYPE_MECH, false, false, tempEntity);
                } else if (tempEntity instanceof Protomech) {
                    newUnit(Entity.ETYPE_PROTOMECH, false, false, tempEntity);
                } else if ((tempEntity instanceof Tank)
                        && !(tempEntity instanceof GunEmplacement)) {
                    newUnit(Entity.ETYPE_TANK, false, false, tempEntity);
                } else {
                    JOptionPane.showMessageDialog(parentFrame,
                            resourceMap.getString("message.abortUnitLoad.text"));
                }
                return;
            }
            parentFrame.setEntity(tempEntity);
            UnitUtil.updateLoadedUnit(parentFrame.getEntity());

            CConfig.updateSaveFiles(unitFile.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format(
                    resourceMap.getString("message.invalidUnit.format"),
                    ex.getMessage()));
        }
        reload();
        refresh();
        parentFrame.setVisible(true);
    }

    private void refresh() {
        parentFrame.refreshAll();
    }

    private void reload() {
        parentFrame.reloadTabs();
    }
    
    /**
     * This function will create a new mainUI frame (via the loading dialog) for the 
     * given unit type and get rid of the existing frame
     * @param type an <code>int</code> corresponding to the unit type to construct
     */
    private void newUnit(long type, boolean primitive, boolean industrial, Entity en) {
        parentFrame.setVisible(false);
        LoadingDialog ld = new LoadingDialog(parentFrame, type, primitive, industrial, en);
        ld.setVisible(true);
    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {

    }

    private final FilenameFilter unitFilesFilter =
            (dir, filename) -> {
                String fn = filename.toLowerCase();
                return fn.endsWith(".mtf")
                        || fn.endsWith(".blk")
                        || fn.endsWith(".hmp");
            };
}
