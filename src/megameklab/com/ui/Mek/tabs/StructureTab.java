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

package megameklab.com.ui.Mek.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
    JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);
    Dimension maxSize = new Dimension();
    JLabel baseChassisHeatSinksLabel = new JLabel("Base Heat Sinks:", SwingConstants.TRAILING);
    JLabel structureLabel = new JLabel("Structure:", SwingConstants.TRAILING);
    JPanel masterPanel;

    private CriticalView critView = null;
    private ImagePanel unitImage = null;

    public StructureTab(Mech unit) {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setWheelScrollingEnabled(true);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, enginePanel(), scroll);

        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // this.add(enginePanel());
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        critView = new CriticalView(getMech(), false, refresh);
        unitImage = new ImagePanel(getMech(), ImageHelper.imageMech);
        scrollPanel.add(critView);
        scrollPanel.add(unitImage);

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

        setFieldSize(walkMP, maxSize);
        setFieldSize(era, maxSize);
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
        era.setText(Integer.toString(getMech().getYear()));
        source.setText(getMech().getSource());
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

        cockpitType.setSelectedIndex(getMech().getCockpitType());
        structureCombo.setSelectedIndex(getMech().getStructureType());
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

        walkMP.setSelectedIndex(getMech().getWalkMP() - 1);

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

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            try {
                // we need to do cockpit also here, because cockpitType
                // determines
                // if a mech is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(walkMP) || combo.equals(cockpitType)) {
                    if (combo.equals(cockpitType)) {
                        getMech().setCockpitType(combo.getSelectedIndex());
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
                        if (dRating % 5 != 0) {
                            dRating = dRating - dRating % 5 + 5;
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
                        if (getMech().isClan()) {
                            getMech().setEngine(new Engine(rating, convertEngineType(engineType.getSelectedItem().toString()), Engine.CLAN_ENGINE));
                        } else {
                            getMech().setEngine(new Engine(rating, convertEngineType(engineType.getSelectedItem().toString()), 0));
                        }
                        getMech().addEngineCrits();
                        int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                        UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
                    }
                } else if (combo.equals(structureCombo)) {
                    UnitUtil.removeISorArmorMounts(getMech(), true);
                    getMech().setStructureType(structureCombo.getSelectedIndex());
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
                        if (dRating % 5 != 0) {
                            dRating = dRating - dRating % 5 + 5;
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
                    UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
                } else if (combo.equals(baseChassisHeatSinks)) {
                    getMech().getEngine().setBaseChassisHeatSinks(Math.max(0, baseChassisHeatSinks.getSelectedIndex()));
                    int autoSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
                    UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
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
                    } else if ((techType.getSelectedIndex() == 0) && (getMech().isClan() || getMech().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setMixedTech(false);
                        createEngineList(false);
                        createHeatSinkList();

                    } else if ((techType.getSelectedIndex() == 2) && (!getMech().isMixedTech() || getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_IS_ADVANCED);
                        getMech().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        getMech().setMixedTech(true);
                        createEngineList(false);
                        createHeatSinkList();

                    } else if ((techType.getSelectedIndex() == 3) && (!getMech().isMixedTech() || !getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        getMech().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        getMech().setMixedTech(true);
                        createEngineList(true);
                        createHeatSinkList();
                    } else {
                        createEngineList(getMech().isClan());
                        createHeatSinkList();
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
                UnitUtil.updateHeatSinks(getMech(), heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
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
        omniCB.removeActionListener(this);
        quadCB.removeActionListener(this);
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
        omniCB.addActionListener(this);
        quadCB.addActionListener(this);
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
        isCount = EquipmentType.get(EquipmentType.getStructureTypeName(getMech().getStructureType())).getCriticals(getMech());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMech().addEquipment(new Mounted(getMech(), EquipmentType.get(EquipmentType.getStructureTypeName(getMech().getStructureType()))), Entity.LOC_NONE, false);
            } catch (Exception ex) {
            }
        }
    }

    private int convertEngineType(int engineType) {

        if (getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
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
        gyroType.removeAllItems();
        int structCount = EquipmentType.structureNames.length;
        int cockpitCount = Mech.COCKPIT_SHORT_STRING.length;
        int gyroCount = Mech.GYRO_SHORT_STRING.length;
        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                structCount = 1;
                cockpitCount = 1;
                gyroCount = 1;
                break;
            case TechConstants.T_CLAN_TW:
                gyroCount = 2;
                structCount = 3;
                cockpitCount = 5;
                break;
            case TechConstants.T_IS_TW_NON_BOX:
                structCount = 3;
                cockpitCount = 5;
                break;
            case TechConstants.T_CLAN_ADVANCED:
                structCount = 3;
                gyroCount = 2;
                cockpitCount = 6;
                break;
            case TechConstants.T_IS_ADVANCED:
                structCount = 3;
                cockpitCount = 6;
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
                cockpitCount = 7;
                gyroCount = 2;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
                cockpitCount = 7;
                break;
            case TechConstants.T_CLAN_UNOFFICIAL:
                gyroCount = 2;
                cockpitCount = 8;
                break;
            case TechConstants.T_IS_UNOFFICIAL:
                cockpitCount = 8;
                break;
        }

        for (int index = 0; index < structCount; index++) {
            structureCombo.addItem(EquipmentType.structureNames[index]);
        }

        for (int index = 0; index < cockpitCount; index++) {
            cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[index]);
        }

        for (int index = 0; index < gyroCount; index++) {
            gyroType.addItem(Mech.GYRO_SHORT_STRING[index]);
        }

    }

    private void createHeatSinkList() {

        int heatSinkCount = 0;
        String[] heatSinkList;
        heatSinkType.removeAllItems();

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

        for (int index = 0; index < heatSinkCount; index++) {
            heatSinkType.addItem(heatSinkList[index]);
        }
    }

    private void createEngineList(boolean isClan) {

        int engineCount = 1;
        String[] engineList;

        engineType.removeAllItems();

        if (isClan) {
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

        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

    }
}
