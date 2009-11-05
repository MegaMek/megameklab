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
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    String[] isEngineTypes = { "I.C.E.", "Standard", "XL", "XXL", "Fuel Cell", "Light", "Compact", "Fission" };
    String[] clanEngineTypes = { "I.C.E.", "Standard", "XL", "XXL", "Fuel Cell" };
    JComboBox engineType = new JComboBox(isEngineTypes);
    JComboBox walkMP;
    JComboBox gyroType = new JComboBox(Mech.GYRO_SHORT_STRING);
    JComboBox weightClass;
    JComboBox cockpitType = new JComboBox(Mech.COCKPIT_SHORT_STRING);
    String[] clanHeatSinkTypes = { "Single", "Double", "Laser" };
    String[] isHeatSinkTypes = { "Single", "Double", "Compact" };
    JComboBox heatSinkType = new JComboBox(isHeatSinkTypes);
    JComboBox heatSinkNumber;
    JComboBox baseChassisHeatSinks;
    String[] techTypes = { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels = { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental", "Unoffical" };
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
        critView = new CriticalView(getMech(), false, refresh);
        scroll.setViewportView(critView);

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
        gyroType.setSelectedIndex(getMech().getGyroType());
        weightClass.setSelectedIndex((int) (getMech().getWeight() / 5) - 2);
        cockpitType.setSelectedIndex(getMech().getCockpitType());
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
            engineType.removeAllItems();
            techLevel.removeAllItems();
            heatSinkType.removeAllItems();
            for (String item : clanEngineTypes) {
                engineType.addItem(item);
            }
            for (String item : clanHeatSinkTypes) {
                heatSinkType.addItem(item);
            }
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
        } else {
            engineType.removeAllItems();
            techLevel.removeAllItems();
            heatSinkType.removeAllItems();
            for (String item : isEngineTypes) {
                engineType.addItem(item);
            }
            for (String item : isHeatSinkTypes) {
                heatSinkType.addItem(item);
            }
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }

        }

        engineType.setSelectedIndex(getMech().getEngine().getEngineType());
        structureCombo.setSelectedIndex(getMech().getStructureType());

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
                        int type = engineType.getSelectedIndex();
                        if (getMech().isClan()) {
                            getMech().setEngine(new Engine(rating, type, Engine.CLAN_ENGINE));
                        } else {
                            getMech().setEngine(new Engine(rating, type, 0));
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
                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!getMech().isClan() || getMech().isMixedTech())) {
                        engineType.removeAllItems();
                        techLevel.removeAllItems();
                        heatSinkType.removeAllItems();

                        for (String item : clanEngineTypes) {
                            engineType.addItem(item);
                        }
                        for (String item : clanHeatSinkTypes) {
                            heatSinkType.addItem(item);
                        }
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setMixedTech(false);
                    } else if ((techType.getSelectedIndex() == 0) && (getMech().isClan() || getMech().isMixedTech())) {
                        engineType.removeAllItems();
                        techLevel.removeAllItems();
                        heatSinkType.removeAllItems();

                        for (String item : isEngineTypes) {
                            engineType.addItem(item);
                        }
                        for (String item : isHeatSinkTypes) {
                            heatSinkType.addItem(item);
                        }
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setMixedTech(false);

                    } else if ((techType.getSelectedIndex() == 2) && (!getMech().isMixedTech() || getMech().isClan())) {
                        engineType.removeAllItems();
                        techLevel.removeAllItems();
                        heatSinkType.removeAllItems();

                        for (String item : isEngineTypes) {
                            engineType.addItem(item);
                        }
                        for (String item : isHeatSinkTypes) {
                            heatSinkType.addItem(item);
                        }
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_IS_ADVANCED);
                        getMech().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        getMech().setMixedTech(true);

                    } else if ((techType.getSelectedIndex() == 3) && (!getMech().isMixedTech() || !getMech().isClan())) {
                        engineType.removeAllItems();
                        techLevel.removeAllItems();
                        heatSinkType.removeAllItems();

                        for (String item : clanEngineTypes) {
                            engineType.addItem(item);
                        }
                        for (String item : clanHeatSinkTypes) {
                            heatSinkType.addItem(item);
                        }
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        getMech().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        getMech().setMixedTech(true);
                    } else {
                        addAllActionListeners();
                        return;
                    }
                    addAllActionListeners();
                    engineType.setSelectedIndex(1);
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

}
