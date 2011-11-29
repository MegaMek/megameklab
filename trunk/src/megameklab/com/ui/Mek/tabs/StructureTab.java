/*
 * MegaMekLab - Copyright (C) 2008
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

package megameklab.com.ui.Mek.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.TechConstants;
import megameklab.com.ui.Mek.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImagePanel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private static final String ENGINESTANDARD = "Standard";
    private static final String ENGINEXL = "XL";
    private static final String ENGINELIGHT = "Light";
    private static final String ENGINECOMPACT = "Compact";
    private static final String ENGINEFISSION = "Fission";
    private static final String ENGINEFUELCELL = "Fuel Cell";
    private static final String ENGINEXXL = "XXL";
    private static final String ENGINEICE = "I.C.E";

    String[] isEngineTypes =
        { ENGINESTANDARD, ENGINEXL, ENGINELIGHT, ENGINECOMPACT, ENGINEFISSION, ENGINEFUELCELL, ENGINEXXL };
    String[] isIndustrialEngineTypes =
        { ENGINESTANDARD, ENGINEICE, ENGINEFUELCELL, ENGINEFISSION };
    String[] clanEngineTypes =
        { ENGINESTANDARD, ENGINEXL, ENGINEFUELCELL, ENGINEXXL };
    String[] clanIndustrialEngineTypes =
        { ENGINESTANDARD, ENGINEICE, ENGINEFUELCELL, ENGINEFISSION };
    private int clanEngineFlag = 0;

    JComboBox engineType = new JComboBox(isEngineTypes);
    JComboBox walkMP;
    JComboBox gyroType = new JComboBox(Mech.GYRO_SHORT_STRING);
    JComboBox weightClass;
    JComboBox cockpitType = new JComboBox(Mech.COCKPIT_SHORT_STRING);
    String[] clanHeatSinkTypes =
        { "Single", "Double", "Laser" };
    String[] isHeatSinkTypes =
        { "Single", "Double", "Compact" };
    JComboBox heatSinkType = new JComboBox(isHeatSinkTypes);
    JComboBox heatSinkNumber;
    JComboBox baseChassisHeatSinks;
    String[] techTypes =
        { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels =
        { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    JComboBox techLevel = new JComboBox(isTechLevels);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox quadCB = new JCheckBox("Quad");
    JCheckBox fullHeadEjectCB = new JCheckBox("Full Head Ejection");
    JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);
    Dimension maxSize = new Dimension();
    JLabel baseChassisHeatSinksLabel = new JLabel("Base Heat Sinks:", SwingConstants.TRAILING);
    JLabel structureLabel = new JLabel("Structure:", SwingConstants.TRAILING);
    JPanel masterPanel;
    JTextField manualBV = new JTextField(3);

    private CriticalView critView = null;
    private ImagePanel unitImage = null;
    private JButton browseButton = null;

    public StructureTab(Mech unit) {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, enginePanel(), scroll);

        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // this.add(enginePanel());
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        critView = new CriticalView(getMech(), false, refresh);

        JPanel imagePanel = new JPanel();

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        unitImage = new ImagePanel(getMech(), ImageHelper.imageMech);
        browseButton = new JButton("Browse");
        browseButton.addActionListener(this);

        imagePanel.add(unitImage);
        imagePanel.add(browseButton);

        scrollPanel.add(critView);
        scrollPanel.add(imagePanel);
        scroll.setViewportView(scrollPanel);

        this.add(splitter);
        refresh();
    }

    public JPanel enginePanel() {
        masterPanel = new JPanel(new SpringLayout());

        Vector<String> walkMPs = new Vector<String>(26, 1);

        for (int pos = 1; pos <= 25; pos++) {
            walkMPs.add(Integer.toString(pos));
        }

        walkMP = new JComboBox(walkMPs.toArray());

        Vector<String> weightClasses = new Vector<String>(1, 1);

        for (int weight = 10; weight < 101; weight += 5) {
            weightClasses.add(Integer.toString(weight));
        }

        weightClass = new JComboBox(weightClasses.toArray());

        heatSinkNumber = new JComboBox();
        baseChassisHeatSinks = new JComboBox();

        maxSize.setSize(110, 20);

        masterPanel.add(omniCB);
        masterPanel.add(quadCB);

        masterPanel.add(createLabel("Year:", maxSize));
        masterPanel.add(era);

        masterPanel.add(createLabel("Source/Era:", maxSize));
        masterPanel.add(source);

        masterPanel.add(createLabel("Tech:", maxSize));
        masterPanel.add(techType);
        masterPanel.add(createLabel("Tech Level:", maxSize));
        masterPanel.add(techLevel);

        masterPanel.add(createLabel("Engine Type:", maxSize));
        masterPanel.add(engineType);
        masterPanel.add(createLabel("Walk MP:", maxSize));
        masterPanel.add(walkMP);

        masterPanel.add(createLabel("Gyro:", maxSize));
        masterPanel.add(gyroType);

        masterPanel.add(createLabel("Cockpit:", maxSize));
        masterPanel.add(cockpitType);

        masterPanel.add(createLabel("Weight:", maxSize));
        masterPanel.add(weightClass);

        masterPanel.add(createLabel("Heat Sinks:", maxSize));
        masterPanel.add(heatSinkType);

        masterPanel.add(createLabel("Number:", maxSize));
        masterPanel.add(heatSinkNumber);

        masterPanel.add(structureLabel);
        masterPanel.add(structureCombo);

        masterPanel.add(createLabel("Manual BV:", maxSize));
        masterPanel.add(manualBV);

        masterPanel.add(fullHeadEjectCB);

        setFieldSize(walkMP, maxSize);
        setFieldSize(era, maxSize);
        setFieldSize(manualBV, maxSize);
        setFieldSize(source, maxSize);
        setFieldSize(techType, maxSize);
        setFieldSize(techLevel, maxSize);
        setFieldSize(engineType, maxSize);
        setFieldSize(gyroType, maxSize);
        setFieldSize(cockpitType, maxSize);
        setFieldSize(weightClass, maxSize);
        setFieldSize(heatSinkNumber, maxSize);
        setFieldSize(heatSinkType, maxSize);
        setFieldSize(structureCombo, maxSize);
        setFieldSize(structureLabel, maxSize);
        setFieldSize(baseChassisHeatSinks, maxSize);
        setFieldSize(baseChassisHeatSinksLabel, maxSize);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        omniCB.setSelected(getMech().isOmni());
        quadCB.setSelected(unit instanceof QuadMech);
        fullHeadEjectCB.setSelected(getMech().hasFullHeadEject());
        era.setText(Integer.toString(getMech().getYear()));
        source.setText(getMech().getSource());
        manualBV.setText(Integer.toString(Math.max(0, getMech().getManualBV())));
        weightClass.setSelectedIndex((int) (getMech().getWeight() / 5) - 2);
        heatSinkNumber.removeAllItems();
        baseChassisHeatSinks.removeAllItems();

        for (int count = getMech().getEngine().getWeightFreeEngineHeatSinks(); count < 50; count++) {
            heatSinkNumber.addItem(Integer.toString(count));
        }

        for (int count = 0; count <= getMech().getEngine().integralHeatSinkCapacity(); count++) {
            baseChassisHeatSinks.addItem(Integer.toString(count));
        }

        heatSinkNumber.setSelectedIndex(getMech().heatSinks() - getMech().getEngine().getWeightFreeEngineHeatSinks());
        baseChassisHeatSinks.setSelectedIndex(Math.max(0, getMech().getEngine().getBaseChassisHeatSinks()));

        if (getMech().isOmni()) {
            masterPanel.remove(structureLabel);
            masterPanel.remove(structureCombo);
            masterPanel.add(baseChassisHeatSinksLabel);
            masterPanel.add(baseChassisHeatSinks);
            masterPanel.add(structureLabel);
            masterPanel.add(structureCombo);
            getMech().getEngine().setBaseChassisHeatSinks(Math.max(0, baseChassisHeatSinks.getSelectedIndex()));
            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        } else {
            masterPanel.remove(baseChassisHeatSinksLabel);
            masterPanel.remove(baseChassisHeatSinks);
            getMech().getEngine().setBaseChassisHeatSinks(-1);
            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        }

        if (getMech().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
            createEngineList(true);
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
            createEngineList(false);

        }

        engineType.setSelectedIndex(convertEngineType(getMech().getEngine().getEngineType()));

        createSystemList();
        createHeatSinkList();
        createGyroList();

        cockpitType.setSelectedItem(Mech.COCKPIT_SHORT_STRING[getMech().getCockpitType()]);
        setStructureCombo();
        gyroType.setSelectedIndex(getMech().getGyroType());

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {

                techType.setSelectedIndex(3);
                if (getMech().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getMech().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getMech().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getMech().isClan()) {

            techType.setSelectedIndex(1);
            if (getMech().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getMech().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    heatSinkType.setSelectedIndex(3);
                } else if (getMech().hasDoubleHeatSinks()) {
                    if (UnitUtil.hasCompactHeatSinks(getMech())) {
                        heatSinkType.setSelectedIndex(4);
                    } else {
                        if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                            heatSinkType.setSelectedIndex(1);
                        } else {
                            heatSinkType.setSelectedIndex(2);
                        }
                    }
                } else {

                    heatSinkType.setSelectedIndex(0);
                }
            } else {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    heatSinkType.setSelectedIndex(3);
                } else if (getMech().hasDoubleHeatSinks()) {
                    if (UnitUtil.hasCompactHeatSinks(getMech())) {
                        heatSinkType.setSelectedIndex(4);
                    } else {
                        if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                            heatSinkType.setSelectedIndex(1);
                        } else {
                            heatSinkType.setSelectedIndex(2);
                        }
                    }
                } else {
                    heatSinkType.setSelectedIndex(0);
                }
            }
        } else {
            if (UnitUtil.hasLaserHeatSinks(getMech())) {
                heatSinkType.setSelectedIndex(2);
            } else if (getMech().hasDoubleHeatSinks()) {
                if (UnitUtil.hasCompactHeatSinks(getMech())) {
                    heatSinkType.setSelectedIndex(2);
                } else {
                    heatSinkType.setSelectedIndex(1);
                }
            } else {
                heatSinkType.setSelectedIndex(0);
            }
        }
        walkMP.setSelectedIndex(getMech().getWalkMP(true, false, true) - 1);

        critView.updateUnit(getMech());
        critView.refresh();

        unitImage.updateUnit(getMech());
        unitImage.refresh();

        addAllActionListeners();

    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void actionPerformed(ActionEvent e) {

        if ((e.getSource() instanceof JButton) && e.getSource().equals(browseButton)) {
            FileDialog fDialog = new FileDialog(new JFrame(), "Image Path", FileDialog.LOAD);

            if (getMech().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(getMech().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
                fDialog.setFile(getMech().getChassis() + " " + getMech().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(this);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                getMech().getFluff().setMMLImagePath(relativeFilePath);
                refresh();
            }
            return;
        }

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            try {
                // we need to do cockpit also here, because cockpitType
                // determines
                // if a mech is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(walkMP) || combo.equals(cockpitType)) {
                    if (combo.equals(cockpitType)) {
                        getMech().setCockpitType(Mech.getCockpitTypeForString(combo.getSelectedItem().toString()));
                        getMech().clearCockpitCrits();
                        switch (getMech().getCockpitType()) {
                            case Mech.COCKPIT_COMMAND_CONSOLE:
                                getMech().addCommandConsole();
                                break;
                            case Mech.COCKPIT_DUAL:
                                getMech().addDualCockpit();
                                break;
                            case Mech.COCKPIT_SMALL:
                                getMech().addSmallCockpit();
                                break;
                            case Mech.COCKPIT_TORSO_MOUNTED:
                                removeSystemCrits(Mech.SYSTEM_ENGINE);
                                getMech().addEngineCrits();
                                getMech().addTorsoMountedCockpit();
                                break;
                            case Mech.COCKPIT_INDUSTRIAL:
                                getMech().addIndustrialCockpit();
                                break;
                            case Mech.COCKPIT_PRIMITIVE:
                                getMech().addPrimitiveCockpit();
                                break;
                            case Mech.COCKPIT_PRIMITIVE_INDUSTRIAL:
                                getMech().addIndustrialPrimitiveCockpit();
                                break;
                            default:
                                getMech().addCockpit();
                        }
                    }
                    int rating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (getMech().isPrimitive()) {
                        double dRating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                        dRating *= 1.2;
                        if ((dRating % 5) != 0) {
                            dRating = (dRating - (dRating % 5)) + 5;
                        }
                        rating = (int) dRating;
                    }
                    if ((rating > 400) && (getMech().getGyroType() == Mech.GYRO_XL)) {
                        JOptionPane.showMessageDialog(this, "That speed would require a large engine, which doesn't fit", "Bad Engine", JOptionPane.ERROR_MESSAGE);
                    }
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        getMech().clearEngineCrits();
                        getMech().setEngine(new Engine(rating, convertEngineType(engineType.getSelectedItem().toString()), clanEngineFlag));
                        getMech().addEngineCrits();
                        int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                        UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedItem().toString());
                    }
                } else if (combo.equals(structureCombo)) {
                    UnitUtil.removeISorArmorMounts(getMech(), true);
                    createISMounts();
                } else if (combo.equals(gyroType)) {
                    if (getMech().getEngine().hasFlag(Engine.LARGE_ENGINE) && (combo.getSelectedIndex() == Mech.GYRO_XL)) {
                        JOptionPane.showMessageDialog(this, "A XL gyro does not fit with a large engine installed", "Bad Gyro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        getMech().setGyroType(combo.getSelectedIndex());
                        getMech().clearGyroCrits();

                        switch (getMech().getGyroType()) {
                            case Mech.GYRO_COMPACT:
                                getMech().addCompactGyro();
                                getMech().clearEngineCrits();
                                getMech().addEngineCrits();
                                break;
                            case Mech.GYRO_HEAVY_DUTY:
                                getMech().addHeavyDutyGyro();
                                break;
                            case Mech.GYRO_XL:
                                getMech().addXLGyro();
                                break;
                            default:
                                getMech().addGyro();
                        }
                    }
                } else if (combo.equals(weightClass)) {
                    int rating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (getMech().isPrimitive()) {
                        double dRating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                        dRating *= 1.2;
                        if ((dRating % 5) != 0) {
                            dRating = (dRating - (dRating % 5)) + 5;
                        }
                        rating = (int) dRating;
                    }
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        getMech().setWeight(Float.parseFloat(weightClass.getSelectedItem().toString()));
                        getMech().autoSetInternal();
                        addAllActionListeners();
                        engineType.setSelectedIndex(engineType.getSelectedIndex());
                        removeAllActionListeners();
                    }
                } else if (combo.equals(heatSinkType) || combo.equals(heatSinkNumber)) {
                    int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                    UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedItem().toString());
                } else if (combo.equals(baseChassisHeatSinks)) {
                    getMech().getEngine().setBaseChassisHeatSinks(Math.max(0, baseChassisHeatSinks.getSelectedIndex()));
                    int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                    UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedItem().toString());
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (getMech().isClan()) {
                        switch (unitTechLevel) {
                            case 0:
                                getMech().setTechLevel(TechConstants.T_CLAN_TW);
                                getMech().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                addAllActionListeners();
                                techType.setSelectedIndex(1);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getMech().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                                getMech().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                                break;
                            case 2:
                                getMech().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                getMech().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                break;
                            case 3:
                                getMech().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                getMech().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                break;
                            default:
                                getMech().setTechLevel(TechConstants.T_CLAN_TW);
                                getMech().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                break;
                        }

                    } else {
                        switch (unitTechLevel) {
                            case 0:
                                getMech().setTechLevel(TechConstants.T_INTRO_BOXSET);
                                getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getMech().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                getMech().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 2:
                                getMech().setTechLevel(TechConstants.T_IS_ADVANCED);
                                getMech().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                                break;
                            case 3:
                                getMech().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                getMech().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                break;
                            default:
                                getMech().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                getMech().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                break;
                        }

                    }

                    createSystemList();
                    createGyroList();
                    createEngineList(getMech().isClan());
                    createHeatSinkList();
                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!getMech().isClan() || getMech().isMixedTech())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setMixedTech(false);
                        createEngineList(true);
                        createHeatSinkList();
                        heatSinkType.setSelectedItem("Double");
                    } else if ((techType.getSelectedIndex() == 0) && (getMech().isClan() || getMech().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setMixedTech(false);
                        UnitUtil.removeAllMounteds(getMech(), MiscType.F_TALON);
                        createEngineList(false);
                        createHeatSinkList();

                    } else if ((techType.getSelectedIndex() == 2) && (!getMech().isMixedTech() || getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to
                        // experimental if
                        // we're not already unofficial
                        if ((getMech().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getMech().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            getMech().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                        techLevel.setSelectedIndex(techLevel.getModel().getSize() - 2);
                        getMech().setMixedTech(true);
                        createEngineList(false);
                        createHeatSinkList();

                    } else if ((techType.getSelectedIndex() == 3) && (!getMech().isMixedTech() || !getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to advanced if
                        // we're not already experimental or unofficial
                        if (getMech().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) {
                            getMech().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            getMech().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                        techLevel.setSelectedIndex(techLevel.getModel().getSize() - 2);
                        getMech().setMixedTech(true);
                        createEngineList(true);
                        createHeatSinkList();
                        heatSinkType.setSelectedItem("Double");
                    } else {
                        createEngineList(getMech().isClan());
                        createHeatSinkList();
                        heatSinkType.setSelectedItem(getMech().isClan() ? "Double" : "Single");
                        addAllActionListeners();
                        return;
                    }
                    addAllActionListeners();
                    engineType.setSelectedIndex(0);
                    removeAllActionListeners();
                }
                addAllActionListeners();
                refresh.refreshAll();
            } catch (Exception ex) {
                ex.printStackTrace();
                addAllActionListeners();
            }
        } else if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();

            if (check.equals(omniCB)) {
                getMech().setOmni(omniCB.isSelected());
                if (getMech().isOmni()) {
                    masterPanel.remove(structureLabel);
                    masterPanel.remove(structureCombo);
                    masterPanel.add(baseChassisHeatSinksLabel);
                    masterPanel.add(baseChassisHeatSinks);
                    masterPanel.add(structureLabel);
                    masterPanel.add(structureCombo);
                    getMech().getEngine().setBaseChassisHeatSinks(10 + baseChassisHeatSinks.getSelectedIndex());
                } else {
                    masterPanel.remove(baseChassisHeatSinksLabel);
                    masterPanel.remove(baseChassisHeatSinks);
                    getMech().getEngine().setBaseChassisHeatSinks(-1);
                }
                SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
                masterPanel.setVisible(false);
                masterPanel.setVisible(true);
                int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedItem().toString());
            }
            if (check.equals(fullHeadEjectCB)) {
                getMech().setFullHeadEject(fullHeadEjectCB.isSelected());
            }
            refresh.refreshAll();

        }

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getMech().locations(); loc++) {
            for (int slot = 0; slot < getMech().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getMech().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getMech().setCritical(loc, slot, null);
                }
            }
        }
    }

    public void removeAllActionListeners() {
        gyroType.removeActionListener(this);
        engineType.removeActionListener(this);
        weightClass.removeActionListener(this);
        cockpitType.removeActionListener(this);
        heatSinkNumber.removeActionListener(this);
        heatSinkType.removeActionListener(this);
        walkMP.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        quadCB.removeActionListener(this);
        fullHeadEjectCB.removeActionListener(this);
        structureCombo.removeActionListener(this);
        baseChassisHeatSinks.removeActionListener(this);
    }

    public void addAllActionListeners() {
        gyroType.addActionListener(this);
        engineType.addActionListener(this);
        weightClass.addActionListener(this);
        cockpitType.addActionListener(this);
        heatSinkNumber.addActionListener(this);
        heatSinkType.addActionListener(this);
        walkMP.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        quadCB.addActionListener(this);
        fullHeadEjectCB.addActionListener(this);
        structureCombo.addActionListener(this);
        baseChassisHeatSinks.addActionListener(this);

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                getMech().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getMech().setYear(2075);
            }
        } else if (e.getSource().equals(source)) {
            getMech().setSource(source.getText());
        } else if (e.getSource().equals(manualBV)) {
            if(!manualBV.getText().equals("-")) {
                int bv = Integer.parseInt(manualBV.getText());
                if (bv == 0) {
                    getMech().setUseManualBV(false);
                    getMech().setManualBV(0);
                } else {
                    getMech().setUseManualBV(true);
                    getMech().setManualBV(bv);
                }
            }

        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public boolean isQuad() {
        return quadCB.isSelected();
    }

    private void createISMounts() {
        int isCount = 0;
        int structType = structureCombo.getSelectedIndex();
        String structName = EquipmentType.getStructureTypeName(structType);

        if (getMech().isMixedTech()) {
            structName = structureCombo.getSelectedItem().toString();
            boolean clanStruct = getMech().isClan() ? structName.indexOf(" (IS)") == -1 : structName.indexOf(" (Clan)") > -1;

            structName = structureCombo.getSelectedItem().toString().replace(" (IS)", "").replace(" (Clan)", "");

            for (structType = 0; structType < EquipmentType.structureNames.length; structType++) {
                if (EquipmentType.getStructureTypeName(structType).equals(structName)) {
                    break;
                }
            }

            if (clanStruct) {
                structName = String.format("Clan %1$s", structName);
            }
        }

        getMech().setStructureType(structType);
        isCount = EquipmentType.get(structName).getCriticals(getMech());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMech().addEquipment(new Mounted(getMech(), EquipmentType.get(structName)), Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private int convertEngineType(int engineType) {

        if (getMech().isMixedTech()) {
            // Clan Chassis with Clan Engine
            if (getMech().isClan() && getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.FUEL_CELL:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 9;
                }
            }// Clan Chassis with IS Engine
            else if (getMech().isClan() && !getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.LIGHT_ENGINE:
                        return 4;
                    case Engine.COMPACT_ENGINE:
                        return 5;
                    case Engine.FISSION:
                        return 6;
                    case Engine.FUEL_CELL:
                        return 8;
                    case Engine.XXL_ENGINE:
                        return 10;
                }
            }// IS Chassis with Clan Engine
            else if (!getMech().isClan() && getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.FUEL_CELL:
                        return 8;
                    case Engine.XXL_ENGINE:
                        return 10;
                }
            }// IS Chassis with IS Engine
            else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.LIGHT_ENGINE:
                        return 4;
                    case Engine.COMPACT_ENGINE:
                        return 5;
                    case Engine.FISSION:
                        return 6;
                    case Engine.FUEL_CELL:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 9;
                }

            }
        } else if (getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            if (getMech().isIndustrial() || getMech().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.COMBUSTION_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.FISSION:
                        return 3;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.XXL_ENGINE:
                        return 3;
                }
            }
        } else {
            if (getMech().isIndustrial() || getMech().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.COMBUSTION_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.FISSION:
                        return 3;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.LIGHT_ENGINE:
                        return 2;
                    case Engine.COMPACT_ENGINE:
                        return 3;
                    case Engine.FISSION:
                        return 4;
                    case Engine.FUEL_CELL:
                        return 5;
                    case Engine.XXL_ENGINE:
                        return 6;
                }
            }
        }

        return 0;
    }

    private int convertEngineType(String engineType) {

        if (getMech().isMixedTech()) {
            if (engineType.startsWith("(")) {
                if (engineType.startsWith("(Clan")) {
                    clanEngineFlag = Engine.CLAN_ENGINE;
                } else {
                    clanEngineFlag = 0;
                }

                engineType = engineType.substring(engineType.lastIndexOf(")") + 1).trim();
            } else {
                if (getMech().isClan()) {
                    clanEngineFlag = Engine.CLAN_ENGINE;
                } else {
                    clanEngineFlag = 0;
                }
            }
        }

        if (engineType.equals(ENGINESTANDARD)) {
            return Engine.NORMAL_ENGINE;
        }
        if (engineType.equals(ENGINEXL)) {
            return Engine.XL_ENGINE;
        }
        if (engineType.equals(ENGINEXXL)) {
            return Engine.XXL_ENGINE;
        }
        if (engineType.equals(ENGINEICE)) {
            return Engine.COMBUSTION_ENGINE;
        }
        if (engineType.equals(ENGINECOMPACT)) {
            return Engine.COMPACT_ENGINE;
        }
        if (engineType.equals(ENGINEFISSION)) {
            return Engine.FISSION;
        }
        if (engineType.equals(ENGINEFUELCELL)) {
            return Engine.FUEL_CELL;
        }
        if (engineType.equals(ENGINELIGHT)) {
            return Engine.LIGHT_ENGINE;
        }

        return Engine.NORMAL_ENGINE;
    }

    private void createSystemList() {
        structureCombo.removeAllItems();
        cockpitType.removeAllItems();
        int structCount = EquipmentType.structureNames.length;

        boolean isClan = getMech().isClan();
        boolean isMixed = getMech().isMixedTech();

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                structCount = 1;
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                break;
            case TechConstants.T_CLAN_TW:
                structCount = 3;
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                break;
            case TechConstants.T_IS_TW_NON_BOX:
                structCount = 3;
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                break;
            case TechConstants.T_CLAN_ADVANCED:
                structCount = 3;
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_IS_ADVANCED:
                structCount = 3;
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                break;
            case TechConstants.T_CLAN_UNOFFICIAL:
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_DUAL]);

                break;
            case TechConstants.T_IS_UNOFFICIAL:
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_DUAL]);
                break;
        }

        for (int index = 0; index < structCount; index++) {
            if (isMixed && !EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_STANDARD]) && !EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_INDUSTRIAL])) {
                if (isClan) {
                    structureCombo.addItem(String.format("%1$s (IS)", EquipmentType.structureNames[index]));
                    if (EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                        structureCombo.addItem(EquipmentType.structureNames[index]);
                    } else if (EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                        structureCombo.addItem(EquipmentType.structureNames[index]);
                    }
                } else {
                    if (EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                        structureCombo.addItem(String.format("%1$s (Clan)", EquipmentType.structureNames[index]));
                    } else if (EquipmentType.structureNames[index].equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                        structureCombo.addItem(String.format("%1$s (Clan)", EquipmentType.structureNames[index]));
                    }
                    structureCombo.addItem(EquipmentType.structureNames[index]);
                }
            } else {
                structureCombo.addItem(EquipmentType.structureNames[index]);
            }
        }

    }

    private void createHeatSinkList() {

        int heatSinkCount = 0;
        String[] heatSinkList;
        heatSinkType.removeAllItems();

        if (getMech().isMixedTech()) {
            heatSinkCount = (clanHeatSinkTypes.length + isHeatSinkTypes.length) - 1;
            heatSinkList = new String[heatSinkCount];
            int clanPos = 1;
            int heatSinkPos = 0;
            if (getMech().isClan()) {
                clanPos = 0;
                for (String isHeatSink : isHeatSinkTypes) {
                    heatSinkList[heatSinkPos] = clanHeatSinkTypes[clanPos];
                    heatSinkPos++;
                    clanPos++;
                    if (isHeatSink.equals("Single")) {
                        continue;
                    }
                    heatSinkList[heatSinkPos] = String.format("(IS) %1$s", isHeatSink);
                    heatSinkPos++;
                }
            } else {
                for (String isHeatSink : isHeatSinkTypes) {
                    heatSinkList[heatSinkPos] = isHeatSink;
                    heatSinkPos++;
                    if (clanPos < clanHeatSinkTypes.length) {
                        heatSinkList[heatSinkPos] = String.format("(Clan) %1$s", clanHeatSinkTypes[clanPos]);
                    }
                    clanPos++;
                    heatSinkPos++;
                }
            }
        } else {
            if (getMech().isClan()) {

                heatSinkCount = clanHeatSinkTypes.length;
                heatSinkList = clanHeatSinkTypes;

                switch (getMech().getTechLevel()) {
                    case TechConstants.T_CLAN_TW:
                        heatSinkCount = 2;
                        break;
                    case TechConstants.T_CLAN_ADVANCED:
                    case TechConstants.T_CLAN_EXPERIMENTAL:
                    case TechConstants.T_CLAN_UNOFFICIAL:
                        heatSinkCount = 3;
                        break;
                }
            } else {
                heatSinkList = isHeatSinkTypes;
                switch (getMech().getTechLevel()) {
                    case TechConstants.T_INTRO_BOXSET:
                        heatSinkCount = 1;
                        break;
                    case TechConstants.T_IS_TW_NON_BOX:
                    case TechConstants.T_IS_ADVANCED:
                        heatSinkCount = 2;
                        break;
                    case TechConstants.T_IS_EXPERIMENTAL:
                    case TechConstants.T_IS_UNOFFICIAL:
                        heatSinkCount = 3;
                        break;
                }
            }
        }

        for (int index = 0; index < heatSinkCount; index++) {
            heatSinkType.addItem(heatSinkList[index]);
        }
    }

    private void createEngineList(boolean isClan) {

        int engineCount = 1;
        String[] engineList = new String[0];

        engineType.removeAllItems();

        if (getMech().isMixedTech()) {
            if (isClan) {
                engineCount = clanEngineTypes.length + isEngineTypes.length;
                engineList = new String[engineCount];
                int clanPos = 0;
                int enginePos = 0;
                for (String isEngine : isEngineTypes) {
                    if (clanEngineTypes[clanPos].equals(isEngine)) {
                        engineList[enginePos] = clanEngineTypes[clanPos];
                        clanPos++;
                        enginePos++;
                    }
                    engineList[enginePos] = String.format("(IS) %1$s", isEngine);
                    enginePos++;
                }
            } else {
                engineCount = clanEngineTypes.length + isEngineTypes.length;
                engineList = new String[engineCount];
                int clanPos = 0;
                int enginePos = 0;
                for (String isEngine : isEngineTypes) {
                    engineList[enginePos] = isEngine;
                    enginePos++;
                    if (clanEngineTypes[clanPos].equals(isEngine)) {
                        engineList[enginePos] = String.format("(Clan) %1$s", clanEngineTypes[clanPos]);
                        clanPos++;
                        enginePos++;
                    }
                }
            }
        } else {
            if (isClan) {
                clanEngineFlag = Engine.CLAN_ENGINE;
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = clanIndustrialEngineTypes;
                    engineCount = clanIndustrialEngineTypes.length;
                } else {
                    engineList = clanEngineTypes;
                    switch (getMech().getTechLevel()) {
                        case TechConstants.T_INTRO_BOXSET:
                            engineCount = 1;
                            break;
                        case TechConstants.T_CLAN_TW:
                        case TechConstants.T_IS_TW_NON_BOX:
                            engineCount = 2;
                            break;
                        case TechConstants.T_CLAN_ADVANCED:
                        case TechConstants.T_IS_ADVANCED:
                            engineCount = 3;
                            break;
                        case TechConstants.T_CLAN_EXPERIMENTAL:
                        case TechConstants.T_IS_EXPERIMENTAL:
                            engineCount = 4;
                            break;
                        case TechConstants.T_CLAN_UNOFFICIAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 4;
                            break;
                    }
                }
            } else {
                clanEngineFlag = 0;
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = isIndustrialEngineTypes;
                    engineCount = isIndustrialEngineTypes.length;
                } else {
                    engineList = isEngineTypes;
                    switch (getMech().getTechLevel()) {
                        case TechConstants.T_INTRO_BOXSET:
                            engineCount = 1;
                            break;
                        case TechConstants.T_CLAN_TW:
                        case TechConstants.T_IS_TW_NON_BOX:
                            engineCount = 4;
                            break;
                        case TechConstants.T_CLAN_ADVANCED:
                        case TechConstants.T_IS_ADVANCED:
                            engineCount = 6;
                            break;
                        case TechConstants.T_CLAN_EXPERIMENTAL:
                        case TechConstants.T_IS_EXPERIMENTAL:
                            engineCount = 7;
                            break;
                        case TechConstants.T_CLAN_UNOFFICIAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 7;
                            break;
                    }
                }
            }
        }
        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

    }

    private void createGyroList() {

        String[] gyroList = new String[0];

        gyroType.removeAllItems();

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {
                int gyroPos = 0;
                gyroList = new String[Mech.GYRO_SHORT_STRING.length];
                for (String gyro : Mech.GYRO_SHORT_STRING) {
                    if (gyroPos == 0) {
                        gyroList[gyroPos] = gyro;
                    } else {
                        gyroList[gyroPos] = String.format("(IS) %1$s", gyro);
                    }
                    gyroPos++;
                }
            } else {
                gyroList = Mech.GYRO_SHORT_STRING.clone();
            }
        } else {
            if (getMech().isClan()) {
                gyroList = new String[1];
                gyroList[0] = Mech.GYRO_SHORT_STRING[0];
            } else {
                gyroList = Mech.GYRO_SHORT_STRING.clone();
            }
        }
        for (String gyro : gyroList) {
            gyroType.addItem(gyro);
        }

    }

    private void setStructureCombo() {

        if (getMech().isMixedTech()) {
            String structName;
            if (getMech().isClan()) {
                structName = EquipmentType.structureNames[getMech().getStructureType()] + " (IS)";
                if (getMech().hasWorkingMisc("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL];
                } else if (getMech().hasWorkingMisc("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE];
                }
            } else {
                structName = EquipmentType.structureNames[getMech().getStructureType()];
                if (getMech().hasWorkingMisc("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL] + " (Clan)";
                } else if (getMech().hasWorkingMisc("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE] + " (Clan)";
                }
            }
            structureCombo.setSelectedIndex(0);
            for (int pos = 0; pos < structureCombo.getItemCount(); pos++) {
                if (structureCombo.getItemAt(pos).equals(structName)) {
                    structureCombo.setSelectedIndex(pos);
                    break;
                }
            }
        } else {
            structureCombo.setSelectedIndex(getMech().getStructureType());
        }
    }
}
