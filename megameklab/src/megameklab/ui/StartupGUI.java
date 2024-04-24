/*
 * MegaMekLab
 * Copyright (c) 2019-2022 - The MegaMek Team. All Rights Reserved.
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
import megamek.client.ui.swing.util.UIUtil;
import megamek.client.ui.swing.widget.MegamekButton;
import megamek.client.ui.swing.widget.SkinSpecification;
import megamek.client.ui.swing.widget.SkinSpecification.UIComponents;
import megamek.client.ui.swing.widget.SkinXMLHandler;
import megamek.client.ui.swing.widget.SkinnedJPanel;
import megamek.common.Configuration;
import megamek.common.Entity;
import megamek.common.MechSummary;
import megameklab.MMLConstants;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * A startup splash screen for MegaMekLab
 * @author Taharqa
 */
public class StartupGUI extends SkinnedJPanel implements MenuBarOwner {
    JFrame frame;
    MenuBar mmlMenuBar;
    
    /** A map of resolution widths to file names for the startup screen */
    private final TreeMap<Integer, String> startupScreenImages = new TreeMap<>();
    {
        startupScreenImages.put(0, Configuration.miscImagesDir() + "/mml_start_spooky_hd.jpg"); // TODO : Remove inline filename
        startupScreenImages.put(1441, Configuration.miscImagesDir() + "/mml_start_spooky_fhd.jpg"); // TODO : Remove inline filename
        startupScreenImages.put(1921, Configuration.miscImagesDir() + "/mml_start_spooky_uhd.jpg"); // TODO : Remove inline filename
    }
    
    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Splash");
    
    public StartupGUI() {
        super(UIComponents.MainMenuBorder, 1);
        initComponents();
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag && CConfig.getBooleanParam(CConfig.NAG_IMPORT_SETTINGS)) {
            showImportSettingsDialog();
        }
    }

    private void showImportSettingsDialog() {
        CConfig.setParam(CConfig.NAG_IMPORT_SETTINGS, Boolean.toString(false));
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you wish to import settings from another MML" +
                        "? You can also do this later from the main menu.", "Import Settings?", YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            mmlMenuBar.importSettings();
        }
    }

    private void initComponents() {
        SkinSpecification skinSpec = SkinXMLHandler.getSkin(UIComponents.MainMenuBorder.getComp(), true);

        frame = new JFrame("MegaMekLab");
        setBackground(UIManager.getColor("controlHighlight"));
        mmlMenuBar = new MenuBar(this);
        frame.setJMenuBar(mmlMenuBar);

        Dimension scaledMonitorSize = UIUtil.getScaledScreenSize(frame);
        JLabel splash = UIUtil.createSplashComponent(startupScreenImages, frame);
        add(splash, BorderLayout.CENTER);
        
        JLabel labVersion = new JLabel(resourceMap.getString("version.text") + MMLConstants.VERSION, JLabel.CENTER);
        labVersion.setPreferredSize(new Dimension(250,15));
        if (!skinSpec.fontColors.isEmpty()) {
            labVersion.setForeground(skinSpec.fontColors.get(0));
        }

        MegamekButton btnLoadUnit = new MegamekButton(resourceMap.getString("btnLoadUnit.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnLoadUnit.addActionListener(evt -> selectAndLoadUnitFromCache(this));
        
        MegamekButton btnNewMek = new MegamekButton(resourceMap.getString("btnNewMek.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewMek.addActionListener(evt -> newUnit(Entity.ETYPE_MECH));
        
        MegamekButton btnNewVee = new MegamekButton(resourceMap.getString("btnNewVee.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewVee.addActionListener(evt -> newUnit(Entity.ETYPE_TANK));
        
        MegamekButton btnNewSupportVee = new MegamekButton(resourceMap.getString("btnNewSupportVee.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewSupportVee.addActionListener(evt -> newUnit(Entity.ETYPE_SUPPORT_TANK));
        
        MegamekButton btnNewBA = new MegamekButton(resourceMap.getString("btnNewBA.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewBA.addActionListener(evt -> newUnit(Entity.ETYPE_BATTLEARMOR));
        
        MegamekButton btnNewAero = new MegamekButton(resourceMap.getString("btnNewAero.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewAero.addActionListener(evt -> newUnit(Entity.ETYPE_AERO));

        MegamekButton btnNewDropper = new MegamekButton(resourceMap.getString("btnNewDropper.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewDropper.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP));
        
        MegamekButton btnNewLargeCraft = new MegamekButton(resourceMap.getString("btnNewLargeCraft.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewLargeCraft.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP));
        
        MegamekButton btnNewProto = new MegamekButton(resourceMap.getString("btnNewProto.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewProto.addActionListener(evt -> newUnit(Entity.ETYPE_PROTOMECH));
        
        MegamekButton btnNewPbi = new MegamekButton(resourceMap.getString("btnNewPbi.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewPbi.addActionListener(evt -> newUnit(Entity.ETYPE_INFANTRY));
        
        MegamekButton btnQuit = new MegamekButton(resourceMap.getString("btnQuit.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnQuit.addActionListener(evt -> System.exit(0));

        FontMetrics metrics = btnNewDropper.getFontMetrics(btnNewDropper.getFont());
        int width = metrics.stringWidth(btnNewDropper.getText());
        int height = metrics.getHeight();
        Dimension textDim = new Dimension(width + 50, height + 10);

        // Strive for no more than ~90% of the screen and use golden ratio to make
        // the button width "look" reasonable.
        int maximumWidth = (int) (0.9 * scaledMonitorSize.width) - splash.getPreferredSize().width;

        //no more than 50% of image width
        if (maximumWidth > (int) (0.5 * splash.getPreferredSize().width)) {
            maximumWidth = (int) (0.5 * splash.getPreferredSize().width);
        }

        Dimension minButtonDim = new Dimension((int)(maximumWidth / 1.618), 25);
        if (textDim.getWidth() > minButtonDim.getWidth()) {
            minButtonDim = textDim;
        }
        
        btnLoadUnit.setMinimumSize(minButtonDim);
        btnLoadUnit.setPreferredSize(minButtonDim);
        btnNewMek.setMinimumSize(minButtonDim);
        btnNewMek.setPreferredSize(minButtonDim);
        btnNewVee.setMinimumSize(minButtonDim);
        btnNewVee.setPreferredSize(minButtonDim);
        btnNewSupportVee.setMinimumSize(minButtonDim);
        btnNewSupportVee.setPreferredSize(minButtonDim);
        btnNewBA.setMinimumSize(minButtonDim);
        btnNewBA.setPreferredSize(minButtonDim);
        btnNewAero.setMinimumSize(minButtonDim);
        btnNewAero.setPreferredSize(minButtonDim);
        btnNewDropper.setMinimumSize(minButtonDim);
        btnNewDropper.setPreferredSize(minButtonDim);
        btnNewLargeCraft.setMinimumSize(minButtonDim);
        btnNewLargeCraft.setPreferredSize(minButtonDim);
        btnNewPbi.setMinimumSize(minButtonDim);
        btnNewPbi.setPreferredSize(minButtonDim);
        btnNewProto.setMinimumSize(minButtonDim);
        btnNewProto.setPreferredSize(minButtonDim);
        btnQuit.setMinimumSize(minButtonDim);
        btnQuit.setPreferredSize(minButtonDim);
        
        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Left Column
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 5, 10, 10);
        c.ipadx = 10; c.ipady = 5;
        c.gridx = 0;  c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0; c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 12;
        add(splash, c);
        // Right Column
        c.insets = new Insets(2, 2, 2, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0; c.weighty = 1.0;
        c.ipadx = 0; c.ipady = 0;
        c.gridheight = 1;
        c.gridx = 1; c.gridy = 0;
        add(labVersion, c);
        c.gridy++;
        add(btnLoadUnit, c);
        c.gridy++;
        add(btnNewMek, c);
        c.gridy++;
        add(btnNewVee, c);
        c.gridy++;
        add(btnNewSupportVee, c);
        c.gridy++;
        add(btnNewProto, c);
        c.gridy++;
        add(btnNewBA, c);
        c.gridy++;
        add(btnNewPbi, c);
        c.gridy++;
        add(btnNewAero, c);
        c.gridy++;
        add(btnNewDropper, c);
        c.gridy++;
        add(btnNewLargeCraft, c);
        c.gridy++;
        add(btnQuit, c);
        
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.addWindowListener(new ExitOnWindowClosingListener(this));
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Shows the Unit Selector Window and loads the unit if the user selects one. When the chosen
     * unit fits the MageMekLabMainUI given as previousFrame this frame will be kept and updated
     * to the chosen unit, otherwise, a new UI will be created for the unit and previousFrame will
     * be closed and disposed.
     *
     * @param previousFrame The active frame before loading a new unit; can be the StartupGUI or any
     *                      MegaMekLabMainUI.
     */
    public static void selectAndLoadUnitFromCache(MenuBarOwner previousFrame) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(previousFrame.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(previousFrame.getFrame(), unitLoadingDialog);
        Entity newUnit = viewer.getChosenEntity();
        MechSummary mechSummary = viewer.getSelectedMechSummary();
        viewer.dispose();
        if ((mechSummary == null) || (newUnit == null)) {
            return;
        }

        String fileName = viewer.getSelectedMechSummary().getSourceFile().toString();
        if (fileName.toLowerCase().endsWith(".zip")) {
            fileName = viewer.getSelectedMechSummary().getSourceFile().getAbsolutePath();
            fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
            fileName = fileName + MenuBar.createUnitFilename(newUnit);
        }

        if (!previousFrame.safetyPrompt()) {
            return;
        }

        String validationResult = UnitUtil.validateUnit(newUnit);
        if (!validationResult.isBlank()) {
            PopupMessages.showUnitInvalidWarning(previousFrame.getFrame(), validationResult);
        }

        CConfig.setMostRecentFile(fileName);
        if (!(previousFrame instanceof MegaMekLabMainUI)
                || (newUnit.getEntityType() != previousFrame.getEntity().getEntityType())) {
            previousFrame.getFrame().setVisible(false);
            previousFrame.getFrame().dispose();
            UiLoader.loadUi(newUnit, fileName);
        } else {
            UnitUtil.updateLoadedUnit(newUnit);
            previousFrame.refreshMenuBar();
            MegaMekLabMainUI previousUI = (MegaMekLabMainUI) previousFrame;
            previousUI.setEntity(newUnit, fileName);
            previousUI.reloadTabs();
            previousUI.refreshAll();
        }
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public boolean hasEntityNameChanged() {
        return false;
    }

    @Override
    public void refreshMenuBar() {
        mmlMenuBar.refreshMenuBar();
    }
}