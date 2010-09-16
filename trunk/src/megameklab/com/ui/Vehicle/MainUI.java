/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle;

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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
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
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLEditorKit;

import megamek.MegaMek;
import megamek.client.ui.swing.MechView;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.MechFileParser;
import megamek.common.MechSummaryCache;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.UnitType;
import megamek.common.loaders.BLKTankFile;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestTank;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.Vehicle.tabs.ArmorTab;
import megameklab.com.ui.Vehicle.tabs.BuildTab;
import megameklab.com.ui.Vehicle.tabs.EquipmentTab;
import megameklab.com.ui.Vehicle.tabs.StructureTab;
import megameklab.com.ui.Vehicle.tabs.WeaponTab;
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

    Tank entity = null;
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
        createNewTank(false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".blk");
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
        MechSummaryCache.getInstance();
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, UnitType.TANK);

        if (!(viewer.getSelectedEntity() instanceof Tank)) {
            return;
        }
        entity = (Tank) viewer.getSelectedEntity();

        // UnitUtil.updateLoadedTank(entity);
        viewer.setVisible(false);
        viewer.dispose();

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

        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(this.getLocation().x + 150, this.getLocation().y + 100);
        f.setDialogTitle("Load Vehicle");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "hmv");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(this);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        try {
            Entity tempEntity = new MechFileParser(f.getSelectedFile()).getEntity();
            if (!(tempEntity instanceof Tank)) {
                return;
            }

            entity = (Tank) tempEntity;
            // UnitUtil.updateLoadedTank(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            createNewTank(false);
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

    public void jMenuResetEntity_actionPerformed(ActionEvent event) {
        createNewTank(false);
        reloadTabs();
        setVisible(true);
        this.repaint();
        refreshAll();
    }

    public void jMenuSaveEntity_actionPerformed(ActionEvent event) {
        // UnitUtil.compactCriticals(entity);
        // UnitUtil.reIndexCrits(entity);

        FileDialog fDialog = new FileDialog(this, "Save As", FileDialog.SAVE);

        String filePathName = System.getProperty("user.dir").toString() + "/data/Mechfiles/";

        fDialog.setDirectory(filePathName);
        fDialog.setFile(entity.getChassis() + " " + entity.getModel() + ".blk");
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            BLKTankFile.encode(filePathName, entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, entity.getChassis() + " " + entity.getModel() + " saved to " + filePathName);

    }

    // Show BV Calculations
    public void jMenuBVCalculations_actionPerformed() {

        HTMLEditorKit kit = new HTMLEditorKit();
        entity.calculateBattleValue(true, true);
        String bvText = entity.getBVText();

        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(bvText);

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2, CConfig.getIntParam("WINDOWHEIGHT"));

        jdialog.setPreferredSize(size);
        jdialog.setMinimumSize(size);
        scroll.setPreferredSize(size);
        scroll.setMinimumSize(size);
        // text.setPreferredSize(size);

        jdialog.setLocationRelativeTo(this);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ex) {
        }

        // JOptionPane.showMessageDialog(this, bvText, "BV Calculations",
        // JOptionPane.INFORMATION_MESSAGE);
    }

    public void jMenuUnitSpecs_actionPerformed() {

        HTMLEditorKit kit = new HTMLEditorKit();

        MechView tankView = null;
        try {
            tankView = new MechView(entity, true);
        } catch (Exception e) {
            // error unit didn't load right. this is bad news.
        }

        StringBuffer unitSpecs = new StringBuffer("<html><body>");
        unitSpecs.append(tankView.getMechReadoutBasic());
        unitSpecs.append(tankView.getMechReadoutLoadout());
        unitSpecs.append("</body></html>");

        // System.err.println(unitSpecs.toString());
        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(unitSpecs.toString());

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2, CConfig.getIntParam("WINDOWHEIGHT"));

        jdialog.setPreferredSize(size);
        jdialog.setMinimumSize(size);
        scroll.setPreferredSize(size);
        scroll.setMinimumSize(size);
        // text.setPreferredSize(size);

        jdialog.setLocationRelativeTo(this);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ex) {
        }

        // JOptionPane.showMessageDialog(this, bvText, "BV Calculations",
        // JOptionPane.INFORMATION_MESSAGE);
    }

    // Show Validation data.
    public void jMenuValidateUnit_actionPerformed() {

        EntityVerifier entityVerifier = new EntityVerifier(new File("data/Tankfiles/UnitVerifierOptions.xml"));
        StringBuffer sb = new StringBuffer();
        TestEntity testEntity = null;

        testEntity = new TestTank(entity, entityVerifier.tankOption, null);

        testEntity.correctEntity(sb, true);

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Unit Validation", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Validation Passed", "Unit Validation", JOptionPane.INFORMATION_MESSAGE);
        }

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

        recordSheetImageHelp.setText("To add a fluff image to a record sheet the following steps need to be taken\nPlease Note that currently only \'Tanks use fluff Images\nPlace the image you want to use in the data/images/fluff folder\nMegaMekLab will attempt to match the name of the \'Tank your are printing\nwith the images in the fluff folder.\nThe following is an example of how MegaMekLab look for the image\nExample\nYour \'Tank is called Archer ARC-7Q\nMegaMekLab would look for the following\n\nArcher ARC-7Q.jpg/png/gif\nARC-7Q.jpg/png/gif\nArcher.jpg/png/gif\nhud.png\n");
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

    public void createNewTank(boolean hasTurret) {

        entity = new Tank();

        entity.setHasNoTurret(!hasTurret);
        entity.setYear(2750);
        entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setWeight(25);
        entity.setMovementMode(EntityMovementMode.HOVER);
        entity.setEngine(new Engine(Math.max(10, 25 - entity.getSuspensionFactor()), Engine.NORMAL_ENGINE, Engine.TANK_ENGINE));
        entity.setOriginalWalkMP(1);
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            entity.setArmor(0, loc);
            entity.setArmor(0, loc, true);
        }

        entity.setChassis("New");
        entity.setModel("Tank");

    }

    public void refreshAll() {

        if ((structureTab.hasTurret() && entity.hasNoTurret()) || (!structureTab.hasTurret() && !entity.hasNoTurret())) {

            String chassis = entity.getChassis();
            String model = entity.getModel();

            createNewTank(structureTab.hasTurret());

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
        setTitle(entity.getChassis() + " " + entity.getModel() + ".blk");
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

    private void jMenuLoadMech() {
        new megameklab.com.ui.Mek.MainUI();
        dispose();
    }

    private void jMenuPrintCurrentUnit() {
        UnitPrintManager.printEntity(entity);
    }

    private void loadFileMenuOptions() {

        file.removeAll();

        file.setMnemonic('F');
        JMenuItem item = new JMenuItem();

        JMenu unitMenu = new JMenu("New Unit");
        unitMenu.setMnemonic('N');
        item.setText("Mech");
        item.setMnemonic('M');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadMech();
            }

        });
        unitMenu.add(item);
        file.add(unitMenu);

        item = new JMenuItem();
        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic('L');

        item.setText("From Cache");
        item.setMnemonic('C');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        item = new JMenuItem();
        item.setText("From File");
        item.setMnemonic('F');
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
        item.setMnemonic('C');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuPrintCurrentUnit();
            }
        });

        file.add(UnitPrintManager.printMenu(this, item));

        item = new JMenuItem();
        item.setText("Save");
        item.setMnemonic('S');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem();
        item.setText("Save As");
        item.setMnemonic('A');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveAsEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Reset");
        item.setMnemonic('R');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuResetEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Configuration");
        item.setMnemonic('C');
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
        item.setMnemonic('x');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExit_actionPerformed(e);
            }
        });
        file.add(item);

    }

    public void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(entity).length() > 0) {
            JOptionPane.showMessageDialog(this, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        FileDialog fDialog = new FileDialog(this, "Save As", FileDialog.SAVE);

        String filePathName = new File(System.getProperty("user.dir").toString() + "/data/mechfiles/").getAbsolutePath();

        fDialog.setDirectory(filePathName);
        fDialog.setFile(entity.getChassis() + " " + entity.getModel() + ".blk");
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            BLKTankFile.encode(filePathName, entity);
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, entity.getChassis() + " " + entity.getModel() + " saved to " + filePathName);

    }

    public void jMenuLoadEntityFromFile_actionPerformed(int fileNumber) {
        loadUnitFromFile(fileNumber);
    }
}