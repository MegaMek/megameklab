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
    String[] techTypes = { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels = { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental", "Unoffical" };
    JComboBox techLevel = new JComboBox(isTechLevels);
    JTextField era = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox quadCB = new JCheckBox("Quad");
    JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);

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
        critView = new CriticalView(unit, false, refresh);
        scroll.setViewportView(critView);

        this.add(splitter);
        refresh();
    }

    public JPanel enginePanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());
        Dimension maxSize = new Dimension();

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

        maxSize.setSize(110, 20);

        masterPanel.add(omniCB);
        masterPanel.add(quadCB);

        masterPanel.add(createLabel("Era:", maxSize));
        masterPanel.add(era);

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

        masterPanel.add(createLabel("Structure:", maxSize));
        masterPanel.add(structureCombo);

        this.setFieldSize(walkMP, maxSize);
        this.setFieldSize(walkMP, maxSize);
        this.setFieldSize(era, maxSize);
        this.setFieldSize(techType, maxSize);
        this.setFieldSize(techLevel, maxSize);
        this.setFieldSize(engineType, maxSize);
        this.setFieldSize(gyroType, maxSize);
        this.setFieldSize(cockpitType, maxSize);
        this.setFieldSize(weightClass, maxSize);
        this.setFieldSize(heatSinkNumber, maxSize);
        this.setFieldSize(heatSinkType, maxSize);
        this.setFieldSize(structureCombo, maxSize);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        quadCB.setSelected(unit instanceof QuadMech);
        omniCB.setSelected(unit.isOmni());
        era.setText(Integer.toString(unit.getYear()));
        gyroType.setSelectedIndex(unit.getGyroType());
        weightClass.setSelectedIndex((int) (unit.getWeight() / 5) - 2);
        cockpitType.setSelectedIndex(unit.getCockpitType());
        heatSinkNumber.removeAllItems();
        Vector<String> heatSinks = new Vector<String>(1, 1);
        for (int count = unit.getEngine().getWeightFreeEngineHeatSinks(); count < 50; count++) {
            heatSinks.add(Integer.toString(count));
        }
        for (String sinkNumber: heatSinks) {
            heatSinkNumber.addItem(sinkNumber);
        }
        heatSinkNumber.setSelectedIndex(unit.heatSinks() - unit.getEngine().getWeightFreeEngineHeatSinks());
        engineType.setSelectedIndex(unit.getEngine().getEngineType());
        structureCombo.setSelectedIndex(unit.getStructureType());

        if (unit.isMixedTech()) {
            if (unit.isClan()) {
                techType.setSelectedIndex(3);
                if (unit.getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (unit.getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (unit.getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (unit.getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (unit.isClan()) {

            techType.setSelectedIndex(1);
            if (unit.getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (unit.getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (unit.getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (unit.getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (unit.getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (unit.getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (unit.getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        if (UnitUtil.hasLaserHeatSinks(unit)) {
            heatSinkType.setSelectedIndex(2);
        } else if (unit.hasDoubleHeatSinks()) {
            if (UnitUtil.hasCompactHeatSinks(unit)) {
                heatSinkType.setSelectedIndex(2);
            } else {
                heatSinkType.setSelectedIndex(1);
            }
        } else {
            heatSinkType.setSelectedIndex(0);
        }

        walkMP.setSelectedIndex(unit.getWalkMP() - 1);

        critView.updateMech(unit);
        critView.refresh();

        addAllActionListeners();
    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        label.setMaximumSize(maxSize);
        label.setMinimumSize(maxSize);
        label.setPreferredSize(maxSize);

        return label;
    }

    public void setFieldSize(JComboBox box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void setFieldSize(JTextField box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            try {
                // we need to do cockpit also here, because cockpitType determines
                // if a mech is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(walkMP) || combo.equals(cockpitType)) {
                    if (combo.equals(cockpitType)) {
                        unit.setCockpitType(combo.getSelectedIndex());
                        unit.clearCockpitCrits();
                        switch (unit.getCockpitType()) {
                        case Mech.COCKPIT_COMMAND_CONSOLE:
                            unit.addCommandConsole();
                            break;
                        case Mech.COCKPIT_DUAL:
                            unit.addDualCockpit();
                            break;
                        case Mech.COCKPIT_SMALL:
                            unit.addSmallCockpit();
                            break;
                        case Mech.COCKPIT_TORSO_MOUNTED:
                            removeSystemCrits(Mech.SYSTEM_ENGINE);
                            unit.addTorsoMountedCockpit();
                            unit.addEngineCrits();
                            break;
                        case Mech.COCKPIT_INDUSTRIAL:
                            unit.addIndustrialCockpit();
                            break;
                        case Mech.COCKPIT_PRIMITIVE:
                            unit.addPrimitiveCockpit();
                            break;
                        case Mech.COCKPIT_PRIMITIVE_INDUSTRIAL:
                            unit.addIndustrialPrimitiveCockpit();
                            break;
                        default:
                            unit.addCockpit();
                        }
                    }
                    int rating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (unit.isPrimitive()) {
                        double dRating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                        dRating *= 1.2;
                        if (dRating % 5 != 0) {
                            dRating = dRating - dRating % 5 + 5;
                        }
                        rating = (int)dRating;
                    }
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        unit.clearEngineCrits();
                        int type = engineType.getSelectedIndex();
                        if (unit.isClan()) {
                            unit.setEngine(new Engine(rating, type, Engine.CLAN_ENGINE));
                        } else {
                            unit.setEngine(new Engine(rating, type, 0));
                        }
                        unit.addEngineCrits();
                        int autoSinks = unit.getEngine().getWeightFreeEngineHeatSinks();
                        UnitUtil.updateHeatSinks(unit, heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
                    }
                } else if (combo.equals(structureCombo)) {
                    removeISMounts();
                    unit.setStructureType(structureCombo.getSelectedIndex());
                    createISMounts();
                } else if (combo.equals(gyroType)) {
                    unit.setGyroType(combo.getSelectedIndex());
                    unit.clearGyroCrits();

                    switch (unit.getGyroType()) {
                    case Mech.GYRO_COMPACT:
                        unit.addCompactGyro();
                        unit.clearEngineCrits();
                        unit.addEngineCritsWithCompactGyro();
                        break;
                    case Mech.GYRO_HEAVY_DUTY:
                        unit.addHeavyDutyGyro();
                        break;
                    case Mech.GYRO_XL:
                        unit.addXLGyro();
                        break;
                    default:
                        unit.addGyro();
                    }
                } else if (combo.equals(weightClass)) {
                    int rating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (unit.isPrimitive()) {
                        double dRating = (walkMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                        dRating *= 1.2;
                        if (dRating % 5 != 0) {
                            dRating = dRating - dRating % 5 + 5;
                        }
                        rating = (int)dRating;
                    }
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        unit.setWeight(Float.parseFloat(weightClass.getSelectedItem().toString()));
                        unit.autoSetInternal();
                        addAllActionListeners();
                        engineType.setSelectedIndex(engineType.getSelectedIndex());
                        removeAllActionListeners();
                    }
                } else if (combo.equals(heatSinkType) || combo.equals(heatSinkNumber)) {
                    int autoSinks = unit.getEngine().getWeightFreeEngineHeatSinks();
                    UnitUtil.updateHeatSinks(unit, heatSinkNumber.getSelectedIndex() + autoSinks, heatSinkType.getSelectedIndex());
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (unit.isClan()) {
                        switch (unitTechLevel) {
                        case 0:
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                            addAllActionListeners();
                            techType.setSelectedIndex(1);
                            removeAllActionListeners();
                            break;
                        case 1:
                            unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            unit.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            unit.setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                            break;
                        }

                    } else {
                        switch (unitTechLevel) {
                        case 0:
                            unit.setTechLevel(TechConstants.T_INTRO_BOXSET);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 1:
                            unit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 2:
                            unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                            unit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            unit.setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            break;
                        }

                    }
                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!unit.isClan() || unit.isMixedTech())) {
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

                        unit.setTechLevel(TechConstants.T_CLAN_TW);
                        unit.setMixedTech(false);
                    } else if ((techType.getSelectedIndex() == 0) && (unit.isClan() || unit.isMixedTech())) {
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

                        unit.setTechLevel(TechConstants.T_INTRO_BOXSET);
                        unit.setMixedTech(false);

                    } else if ((techType.getSelectedIndex() == 2) && (!unit.isMixedTech() || unit.isClan())) {
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

                        unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                        unit.setMixedTech(true);

                    } else if ((techType.getSelectedIndex() == 3) && (!unit.isMixedTech() || !unit.isClan())) {
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

                        unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        unit.setMixedTech(true);
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
                unit.setOmni(omniCB.isSelected());
            } else {
                // Create Quad
                refresh.refreshAll();
            }
        }

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = unit.getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    unit.setCritical(loc, slot, null);
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
        omniCB.removeActionListener(this);
        quadCB.removeActionListener(this);
        structureCombo.removeActionListener(this);
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
        omniCB.addActionListener(this);
        quadCB.addActionListener(this);
        structureCombo.addActionListener(this);

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        try {
            unit.setYear(Integer.parseInt(era.getText()));
        } catch (Exception ex) {
            unit.setYear(2075);
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
        isCount = EquipmentType.get(EquipmentType.getStructureTypeName(unit.getStructureType())).getCriticals(unit);
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                unit.addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getStructureTypeName(unit.getStructureType()))), Entity.LOC_NONE, false);
            } catch (Exception ex) {
            }
        }
    }

    private void removeISCrits() {
        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if ((mount != null) && (mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ENDO_STEEL)) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

    private void removeISMounts() {
        removeISCrits();
        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ENDO_STEEL)) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }
        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ENDO_STEEL)) {
                unit.getMisc().remove(pos);
            } else {
                pos++;
            }
        }
    }
}