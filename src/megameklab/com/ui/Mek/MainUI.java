/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.MegaMek;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.BipedMech;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MechSummaryCache;
import megamek.common.QuadMech;
import megamek.common.TechConstants;
import megamek.common.UnitType;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.Mek.tabs.ArmorTab;
import megameklab.com.ui.Mek.tabs.BuildTab;
import megameklab.com.ui.Mek.tabs.EquipmentTab;
import megameklab.com.ui.Mek.tabs.StructureTab;
import megameklab.com.ui.Mek.tabs.WeaponTab;
import megameklab.com.ui.dialog.UnitViewerDialog;
import megameklab.com.util.CConfig;
import megameklab.com.util.ConfigurationDialog;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitPrintManager;
import megameklab.com.util.UnitUtil;

public class MainUI extends JFrame implements RefreshListener {

    /**
     *
     */
    private static final long serialVersionUID = -5836932822468918198L;

    Mech entity = null;
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");
    JMenu validate = new JMenu("Validate");

    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private ArmorTab armorTab;
    private EquipmentTab equipmentTab;
    private WeaponTab weaponTab;
    private BuildTab buildTab;
    private Header header;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();

    public MainUI() {

        UnitUtil.loadFonts();
        new CConfig();
        System.out.println("Staring MegaMekLab version: " + MegaMekLab.VERSION);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Setting look and feel failed: ");
            e.printStackTrace();
        }

        loadFileMenuOptions();

        JMenuItem item = new JMenuItem();
        item.setText("About");
        item.setMnemonic(KeyEvent.VK_A);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed();
            }
        });
        help.add(item);

        item = new JMenuItem();
        item.setText("Record Sheet Images");
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpFluff_actionPerformed();
            }
        });
        help.add(item);

        validate.add(loadBVMenuOptions());

        validate.add(loadValidateMenuOptions());

        validate.add(loadSpecsMenuOptions());

        validate.add(loadUnitCostBreakdownMenuOptions());

        menuBar.add(file);
        menuBar.add(validate);
        menuBar.add(help);

        setLocation(getLocation().x + 10, getLocation().y);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
                // Only save position and size if not maximized or minimized.
                if (getExtendedState() == Frame.NORMAL) {
                    CConfig.setParam("WINDOWHEIGHT", Integer.toString(getHeight()));
                    CConfig.setParam("WINDOWWIDTH", Integer.toString(getWidth()));
                    CConfig.setParam("WINDOWLEFT", Integer.toString(getX()));
                    CConfig.setParam("WINDOWTOP", Integer.toString(getY()));
                }
                CConfig.saveConfig();

                System.exit(0);
            }
        });

        // ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewMech(false);
        MechSummaryCache.getInstance();
        setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
        setJMenuBar(menuBar);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);
        Dimension maxSize = new Dimension(CConfig.getIntParam("WINDOWWIDTH"), CConfig.getIntParam("WINDOWHEIGHT"));
        // masterPanel.setPreferredSize(new Dimension(600,400));
        // scroll.setPreferredSize(maxSize);
        setResizable(true);
        setSize(maxSize);
        setMaximumSize(maxSize);
        setPreferredSize(maxSize);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
        setLocation(CConfig.getIntParam("WINDOWLEFT"), CConfig.getIntParam("WINDOWTOP"));

        reloadTabs();
        setVisible(true);
        repaint();
        refreshAll();

    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.MEK);

        if (!(viewer.getSelectedEntity() instanceof Mech)) {
            return;
        }
        entity = (Mech) viewer.getSelectedEntity();

        CConfig.updateSaveFiles("");
        UnitUtil.updateLoadedMech(entity);
        viewer.setVisible(false);
        viewer.dispose();

        if (viewer.getSelectedMechSummary().getSourceFile().getName().endsWith(".zip")) {
            String fileName = viewer.getSelectedMechSummary().getSourceFile().getAbsolutePath();
            fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
            fileName = fileName + viewer.getSelectedMechSummary().getName() + ".mtf";
            CConfig.updateSaveFiles(fileName);
        } else {
            CConfig.updateSaveFiles(viewer.getSelectedMechSummary().getSourceFile().getAbsolutePath());
        }
        reloadTabs();
        setVisible(true);
        this.repaint();
        refreshAll();
    }

    private void loadUnitFromFile() {
        loadUnitFromFile(-1);
    }

    private void loadUnitFromFile(int fileNumber) {

        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

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
            JFileChooser f = new JFileChooser(filePathName);
            f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
            f.setDialogTitle("Load Mech");
            f.setDialogType(JFileChooser.OPEN_DIALOG);
            f.setMultiSelectionEnabled(false);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

            // Add a filter for mul files
            f.setFileFilter(filter);

            int returnVal = f.showOpenDialog(this);
            if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
                // I want a file, y'know!
                return;
            }
            unitFile = f.getSelectedFile();
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();
            if (!(tempEntity instanceof Mech)) {
                return;
            }

            entity = (Mech) tempEntity;
            UnitUtil.updateLoadedMech(entity);
            if (UnitUtil.validateUnit(entity).trim().length() > 0) {
                JOptionPane.showMessageDialog(this, "Warning:Invalid unit, it might load incorrectly!");
            }

            CConfig.updateSaveFiles(unitFile.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        }

        reloadTabs();
        setVisible(true);
        this.repaint();
        refreshAll();
    }

    public void jMenuLoadEntity_actionPerformed(ActionEvent event) {
        loadUnit();
    }

    public void jMenuLoadEntityFromFile_actionPerformed(ActionEvent event) {
        loadUnitFromFile();
    }

    public void jMenuLoadEntityFromFile_actionPerformed(int fileNumber) {
        loadUnitFromFile(fileNumber);
    }

    public void jMenuResetEntity_actionPerformed(ActionEvent event) {
        CConfig.updateSaveFiles("Reset Unit");
        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");
        createNewMech(false);
        reloadTabs();
        setVisible(true);
        this.repaint();
        refreshAll();
    }

    public void jMenuSaveEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(entity).length() > 0) {
            JOptionPane.showMessageDialog(this, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        UnitUtil.compactCriticals(entity);
        UnitUtil.reIndexCrits(entity);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if (filePathName.trim().length() < 1) {
            FileDialog fDialog = new FileDialog(this, "Save As", FileDialog.SAVE);

            filePathName = new File(System.getProperty("user.dir").toString() + "/data/mechfiles/").getAbsolutePath();

            fDialog.setDirectory(filePathName);
            fDialog.setFile(entity.getChassis() + " " + entity.getModel() + ".mtf");
            fDialog.setLocationRelativeTo(this);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
            } else {
                return;
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(filePathName);
            PrintStream p = new PrintStream(out);
            p.println(entity.getMtf());
            p.close();
            out.close();
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, entity.getChassis() + " " + entity.getModel() + " saved to " + filePathName);

    }

    public void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(entity).length() > 0) {
            JOptionPane.showMessageDialog(this, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        UnitUtil.compactCriticals(entity);
        UnitUtil.reIndexCrits(entity);

        FileDialog fDialog = new FileDialog(this, "Save As", FileDialog.SAVE);

        String filePathName = new File(System.getProperty("user.dir").toString() + "/data/mechfiles/").getAbsolutePath();

        fDialog.setDirectory(filePathName);
        fDialog.setFile(entity.getChassis() + " " + entity.getModel() + ".mtf");
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            FileOutputStream out = new FileOutputStream(filePathName);
            PrintStream p = new PrintStream(out);
            p.println(entity.getMtf());
            p.close();
            out.close();
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, entity.getChassis() + " " + entity.getModel() + " saved to " + filePathName);

    }

    // Show BV Calculations

    public void jMenuBVCalculations_actionPerformed() {
        entity.calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(entity.getBVText(), this);
    }

    public void jMenuUnitCostBreakdown_actionPerformed() {
        UnitUtil.showUnitCostBreakDown(entity, this);
    }

    public void jMenuUnitSpecs_actionPerformed() {
        UnitUtil.showUnitSpecs(entity, this);
    }

    // Show Validation data.
    public void jMenuValidateUnit_actionPerformed() {
        UnitUtil.showValidation(entity, this);
    }

    // Show data about MegaMekLab
    public void jMenuHelpAbout_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(this, "MegaMekLab Info");

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JLabel mekwars = new JLabel("MegaMekLab Version: " + MegaMekLab.VERSION);
        JLabel version = new JLabel("MegaMek Version: " + MegaMek.VERSION);
        JLabel license1 = new JLabel("MegaMekLab software is under GPL. See");
        JLabel license2 = new JLabel("license.txt in ./Docs/licenses for details.");
        JLabel license3 = new JLabel("Project Info:");
        JLabel license4 = new JLabel("       http://www.sourceforge.net/projects/megameklab       ");

        // center everything
        mekwars.setAlignmentX(Component.CENTER_ALIGNMENT);
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        license1.setAlignmentX(Component.CENTER_ALIGNMENT);
        license2.setAlignmentX(Component.CENTER_ALIGNMENT);
        license3.setAlignmentX(Component.CENTER_ALIGNMENT);
        license4.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(new JLabel("\n"));
        child.add(mekwars);
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

        // set the location of the dialog
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    // Show how to create fluff images for Record Sheets
    public void jMenuHelpFluff_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(this, "Image Help");

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JTextArea recordSheetImageHelp = new JTextArea();

        recordSheetImageHelp.setEditable(false);

        recordSheetImageHelp.setText("To add a fluff image to a record sheet the following steps need to be taken\nPlease Note that currently only \'Mechs use fluff Images\nPlace the image you want to use in the data/images/fluff folder\nMegaMekLab will attempt to match the name of the \'Mech your are printing\nwith the images in the fluff folder.\nThe following is an example of how MegaMekLab look for the image\nExample\nYour \'Mech is called Archer ARC-7Q\nMegaMekLab would look for the following\n\nArcher ARC-7Q.jpg/png/gif\nARC-7Q.jpg/png/gif\nArcher.jpg/png/gif\nhud.png\n");
        // center everything
        recordSheetImageHelp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(recordSheetImageHelp);

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);

        // set the location of the dialog
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    public void jMenuConfiguration_actionPerformed(ActionEvent event) {
        new ConfigurationDialog();
    }

    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        structureTab = new StructureTab(entity);

        armorTab = new ArmorTab(entity);
        armorTab.setArmorType(entity.getArmorType());
        armorTab.refresh();

        header = new Header(entity);
        statusbar = new StatusBar(entity);
        equipmentTab = new EquipmentTab(entity);
        weaponTab = new WeaponTab(entity);
        buildTab = new BuildTab(entity, equipmentTab, weaponTab);
        header.addRefreshedListener(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        weaponTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);

        ConfigPane.addTab("Structure", structureTab);
        ConfigPane.addTab("Armor", armorTab);
        ConfigPane.addTab("Equipment", equipmentTab);
        ConfigPane.addTab("Weapons", weaponTab);
        ConfigPane.addTab("Build", buildTab);

        masterPanel.add(header);
        masterPanel.add(ConfigPane);
        masterPanel.add(statusbar);

        refreshHeader();
        this.repaint();
    }

    public void jMenuExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    public void createNewMech(boolean isQuad) {

        if (isQuad) {
            entity = new QuadMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD);
        } else {
            entity = new BipedMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD);
        }

        entity.setYear(2750);
        entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setWeight(25);
        entity.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        entity.addGyro();
        entity.addEngineCrits();
        entity.addCockpit();
        UnitUtil.updateHeatSinks(entity, 10, "Single");

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            entity.setArmor(0, loc);
            entity.setArmor(0, loc, true);
        }

        entity.setChassis("New");
        entity.setModel("Mek");

    }

    public void refreshAll() {

        if ((structureTab.isQuad() && !(entity instanceof QuadMech)) || (!structureTab.isQuad() && (entity instanceof QuadMech))) {
            String model = entity.getModel();
            String chassis = entity.getChassis();

            createNewMech(structureTab.isQuad());

            entity.setChassis(chassis);
            entity.setModel(model);

            // setVisible(false);
            reloadTabs();
            // setVisible(true);
            repaint();
            refreshAll();
        }
        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        weaponTab.refresh();
        buildTab.refresh();
        refreshHeader();
    }

    public void refreshArmor() {
        armorTab.refresh();
    }

    public void refreshBuild() {
        buildTab.refresh();
    }

    public void refreshEquipment() {
        equipmentTab.refresh();

    }

    public void refreshHeader() {

        String title = entity.getChassis() + " " + entity.getModel() + ".mtf";

        if (UnitUtil.validateUnit(entity).length() > 0) {
            title += "  (Invalid)";
            setForeground(Color.red);
        } else {
            setForeground(Color.BLACK);
        }
        setTitle(title);
        loadFileMenuOptions();

    }

    public void refreshStatus() {
        statusbar.refresh();
    }

    public void refreshStructure() {
        structureTab.refresh();
    }

    public void refreshWeapons() {
        weaponTab.refresh();
    }

    private void jMenuLoadVehicle() {
        new megameklab.com.ui.Vehicle.MainUI();
        dispose();
    }

    private void jMenuLoadBattleArmor() {
        new megameklab.com.ui.BattleArmor.MainUI();
        dispose();
    }

    private void jMenuPrintCurrentUnit() {
        UnitPrintManager.printEntity(entity);
    }

    private JMenu loadBVMenuOptions() {
        JMenu bv = new JMenu("BV Calculations");
        JMenuItem item = new JMenuItem();
        item.setText("Current Units BV Calculations");
        item.setMnemonic(KeyEvent.VK_B);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuBVCalculations_actionPerformed();
            }
        });
        bv.add(item);

        item = new JMenuItem();
        item.setText("BV Calculations From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBVFromFile_actionPerformed();
            }
        });
        bv.add(item);

        item = new JMenuItem();
        item.setText("BV Calculations From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBVFromCache_actionPerformed();
            }
        });
        bv.add(item);
        return bv;
    }

    private JMenu loadValidateMenuOptions() {
        JMenu entityValidation = new JMenu("Unit Validation");
        JMenuItem item = new JMenuItem();
        item.setText("Validate Current Unit");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuValidateUnit_actionPerformed();
            }
        });
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText("Validate Unit From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitValidationFromFile_actionPerformed();
            }
        });
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText("Validate Unit From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitValidationFromCache_actionPerformed();
            }
        });
        entityValidation.add(item);
        return entityValidation;
    }

    private JMenu loadUnitCostBreakdownMenuOptions() {
        JMenu entityBreakdown = new JMenu("Unit Cost Breakdown");
        JMenuItem item = new JMenuItem();
        item.setText("Breakdown Current Unit");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuUnitCostBreakdown_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBreakdownFromFile_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBreakdownFromCache_actionPerformed();
            }
        });
        entityBreakdown.add(item);
        return entityBreakdown;
    }

    private JMenu loadSpecsMenuOptions() {
        JMenu unitSpecs = new JMenu("Unit Specs");
        JMenuItem item = new JMenuItem();
        item.setText("Current Unit Specs");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuUnitSpecs_actionPerformed();
            }
        });
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText("Unit Specs From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitSpecsFromFile_actionPerformed();
            }
        });
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText("Unit Specs From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitSpecsFromCache_actionPerformed();
            }
        });
        unitSpecs.add(item);
        return unitSpecs;
    }

    private void loadFileMenuOptions() {

        file.removeAll();

        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem();

        JMenu unitMenu = new JMenu("New Unit");
        unitMenu.setMnemonic(KeyEvent.VK_N);
        item.setText("Tank");
        item.setMnemonic(KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadVehicle();
            }

        });
        unitMenu.add(item);

        item = new JMenuItem();
        item.setText("BattleArmor");
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadBattleArmor();
            }

        });
        unitMenu.add(item);
        file.add(unitMenu);

        item = new JMenuItem();
        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic(KeyEvent.VK_L);

        item.setText("From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        item = new JMenuItem();
        item.setText("From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntityFromFile_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        file.add(loadMenu);

        item = new JMenuItem(String.format("Current Unit"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuPrintCurrentUnit();
            }
        });

        file.add(UnitPrintManager.printMenu(this, item));

        item = new JMenuItem();
        item.setText("Save");
        item.setMnemonic(KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem();
        item.setText("Save As");
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveAsEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Reset");
        item.setMnemonic(KeyEvent.VK_R);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuResetEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Configuration");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuConfiguration_actionPerformed(e);
            }
        });
        file.add(item);

        int fileNumber = 1;
        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1).length() > 1) {
            file.addSeparator();
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(1);
                }
            });

            file.add(item);
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
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(2);
                }
            });

            file.add(item);
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
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(3);
                }
            });

            file.add(item);
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
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(4);
                }
            });

            file.add(item);
            fileNumber++;
        }

        file.addSeparator();

        item = new JMenuItem();
        item.setText("Exit");
        item.setMnemonic(KeyEvent.VK_X);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExit_actionPerformed(e);
            }
        });
        file.add(item);

    }

    private void jMenuGetUnitBVFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getSelectedEntity();
        tempEntity.calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(tempEntity.getBVText(), this);

    }

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getSelectedEntity();
        UnitUtil.showValidation(tempEntity, this);

    }

    private void jMenuGetUnitSpecsFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getSelectedEntity();
        UnitUtil.showUnitSpecs(tempEntity, this);

    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getSelectedEntity();
        UnitUtil.showUnitCostBreakDown(tempEntity, this);

    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(this);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            tempEntity.calculateBattleValue(true, true);
            UnitUtil.showBVCalculations(tempEntity.getBVText(), this);
        }

    }

    private void jMenuGetUnitValidationFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(this);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showValidation(tempEntity, this);
        }
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(this);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showUnitCostBreakDown(tempEntity, this);
        }
    }

    private void jMenuGetUnitSpecsFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(this);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showUnitSpecs(tempEntity, this);
        }
    }

}