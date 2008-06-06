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

package megameklab.com.ui.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.util.UnitUtil;
import megameklab.com.ui.views.CriticalView;

import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;

public class StructureTab extends JPanel implements ActionListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = -6756011847500605874L;

    Mech unit;
    String[] engineTypes = { "I.C.E.", "Standard", "XL", "Light", "XXL", "Compact" };
    JComboBox engineType = new JComboBox(engineTypes);
    JComboBox engineRating;
    JComboBox gyroType = new JComboBox(Mech.GYRO_SHORT_STRING);
    JComboBox weightClass;
    JComboBox cockpitType = new JComboBox(Mech.COCKPIT_SHORT_STRING);
    String[] heatSinkTypes = { "Single", "Double", "Compact", "Laser" };
    JComboBox heatSinkType = new JComboBox(heatSinkTypes);
    JComboBox heatSinkNumber;
    String[] techTypes = { "I.S.", "Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] techLevels = { "1", "2", "3" };
    JComboBox techLevel = new JComboBox(techLevels);
    JTextField era = new JTextField(3);
    RefreshListener refresh = null;
    private CriticalView critView = null;

    public StructureTab(Entity unit) {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setWheelScrollingEnabled(true);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, enginePanel(), scroll);

        this.unit = (Mech) unit;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // this.add(enginePanel());
        critView = new CriticalView((Mech) unit, false, refresh);
        scroll.setViewportView(critView);

        this.add(splitter);
        refresh();
    }

    public JPanel enginePanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());
        Dimension maxSize = new Dimension();

        Vector<String> engineRatings = new Vector<String>();

        for (int pos = 0; pos < 81; pos++) {
            engineRatings.add(Integer.toString(pos * 5));
        }

        engineRating = new JComboBox(engineRatings.toArray());

        maxSize.setSize(80, 5);

        masterPanel.add(createLabel("Era:", maxSize));
        masterPanel.add(era);

        masterPanel.add(createLabel("Tech:", maxSize));
        masterPanel.add(techType);
        masterPanel.add(createLabel("Tech Level:", maxSize));
        masterPanel.add(techLevel);

        masterPanel.add(createLabel("Engine Type:", maxSize));
        masterPanel.add(engineType);
        masterPanel.add(createLabel("Engine Rating:", maxSize));
        masterPanel.add(engineRating);

        masterPanel.add(createLabel("Gyro:", maxSize));
        masterPanel.add(gyroType);

        masterPanel.add(createLabel("Cockpit:", maxSize));
        masterPanel.add(cockpitType);

        Vector<String> weightClasses = new Vector<String>(1, 1);

        for (int weight = 10; weight < 101; weight += 5)
            weightClasses.add(Integer.toString(weight));

        weightClass = new JComboBox(weightClasses.toArray());

        masterPanel.add(createLabel("Weight:", maxSize));
        masterPanel.add(weightClass);

        Vector<String> heatSinks = new Vector<String>(1, 1);
        for (int count = 0; count < 40; count++)
            heatSinks.add(Integer.toString(count));

        heatSinkNumber = new JComboBox(heatSinks.toArray());

        masterPanel.add(createLabel("Heat Sinks:", maxSize));
        masterPanel.add(heatSinkType);

        masterPanel.add(createLabel("Number:", maxSize));
        masterPanel.add(heatSinkNumber);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        era.setText(Integer.toString(unit.getYear()));
        gyroType.setSelectedIndex(unit.getGyroType());
        engineType.setSelectedIndex(unit.getEngine().getEngineType());
        weightClass.setSelectedIndex((int) (unit.getWeight() / 5) - 2);
        cockpitType.setSelectedIndex(unit.getCockpitType());
        try {
            //heatSinkNumber.setSelectedIndex(unit.heatSinks() + unit.getEngine().getCountEngineHeatSinks());
            heatSinkNumber.setSelectedIndex(unit.heatSinks());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (unit.isClan()) {
            techType.setSelectedIndex(1);
            if (unit.getTechLevel() <= TechConstants.T_CLAN_LEVEL_2)
                techLevel.setSelectedIndex(1);
            else
                techLevel.setSelectedIndex(2);
        } else {
            techType.setSelectedIndex(0);

            if (unit.getTechLevel() >= TechConstants.T_IS_LEVEL_3)
                techLevel.setSelectedIndex(2);
            else if (unit.getTechLevel() >= TechConstants.T_IS_LEVEL_2)
                techLevel.setSelectedIndex(1);
            else
                techLevel.setSelectedIndex(0);

        }

        if (unit.hasLaserHeatSinks())
            heatSinkType.setSelectedIndex(3);
        else if (unit.hasDoubleHeatSinks()) {
            if (hasCompactHeatSinks(unit))
                heatSinkType.setSelectedIndex(2);
            else
                heatSinkType.setSelectedIndex(1);
        } else
            heatSinkType.setSelectedIndex(0);

        engineRating.setSelectedIndex(unit.getEngine().getRating() / 5);

        critView.refresh();

        addAllActionListeners();
    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, JLabel.TRAILING);

        label.setMaximumSize(maxSize);
        label.setMinimumSize(maxSize);
        label.setPreferredSize(maxSize);

        return label;
    }

    private boolean hasCompactHeatSinks(Mech unit) {

        if (!unit.hasDoubleHeatSinks() || unit.hasLaserHeatSinks())
            return false;

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) || mounted.getType().hasFlag(MiscType.F_HEAT_SINK)) {

                if (mounted.getType().getInternalName().indexOf("Compact") > -1)
                    return true;

                return false;
            }
        }

        return false;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            if (combo.equals(engineType) || combo.equals(engineRating)) {
                removeSystemCrits(Mech.SYSTEM_ENGINE);
                int rating = Integer.parseInt(engineRating.getSelectedItem().toString());
                unit.setEngine(new Engine(rating, engineType.getSelectedIndex(), 0));
                unit.addEngineCrits();
                updateHeatSinks();
            } else if (combo.equals(gyroType)) {
                unit.setGyroType(combo.getSelectedIndex());
                removeSystemCrits(Mech.SYSTEM_GYRO);

                switch (unit.getGyroType()) {
                case Mech.GYRO_COMPACT:
                    unit.addCompactGyro();
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
            } else if (combo.equals(cockpitType)) {
                unit.setCockpitType(combo.getSelectedIndex());
                removeSystemCrits(Mech.SYSTEM_COCKPIT);
                removeSystemCrits(Mech.SYSTEM_LIFE_SUPPORT);
                removeSystemCrits(Mech.SYSTEM_SENSORS);

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
                default:
                    unit.addCockpit();
                }
            } else if (combo.equals(weightClass)) {
                unit.setWeight(Float.parseFloat(weightClass.getSelectedItem().toString()));
                unit.autoSetInternal();
            } else if (combo.equals(heatSinkType) || combo.equals(heatSinkNumber)) {
                updateHeatSinks();
            } else if (combo.equals(techLevel) || combo.equals(techType)) {
                if (techType.getSelectedIndex() > 0) {
                    if (techLevel.getSelectedIndex() < 2) {
                        unit.setTechLevel(TechConstants.T_CLAN_LEVEL_2);
                    } else
                        unit.setTechLevel(TechConstants.T_CLAN_LEVEL_3);
                } else {
                    switch (techLevel.getSelectedIndex()) {
                    case 0:
                        unit.setTechLevel(TechConstants.T_IS_LEVEL_1);
                        break;
                    case 1:
                        unit.setTechLevel(TechConstants.T_IS_LEVEL_2);
                        break;
                    default:
                        unit.setTechLevel(TechConstants.T_IS_LEVEL_3);
                        break;

                    }
                }
            }
            UnitUtil.resetCriticalsAndMounts(unit);
            addAllActionListeners();
            refresh.refreshAll();
        }

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = unit.getCritical(loc, slot);

                if (cs == null || cs.getType() != CriticalSlot.TYPE_SYSTEM)
                    continue;

                if (cs.getIndex() == systemType)
                    unit.setCritical(loc, slot, null);
            }
        }
    }

    public void removeHeatSinks() {

        ConcurrentLinkedQueue<Mounted> equipmentList = new ConcurrentLinkedQueue<Mounted>(unit.getMisc());
        for (Mounted eq : equipmentList) {
            if (eq.getType().hasFlag(MiscType.F_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_LASER_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK))
                unit.getMisc().remove(eq);
        }
    }

    private void addHeatSinkMounts() {
        int heatSinks = heatSinkNumber.getSelectedIndex() - unit.getEngine().integralHeatSinkCapacity();
        EquipmentType sinkType;

        if (heatSinkType.getSelectedIndex() == 1 || heatSinkType.getSelectedIndex() == 3) {
            sinkType = EquipmentType.get(unit.isClan() ? "CLDoubleHeatSink" : "ISDoubleHeatSink");
        } else {
            sinkType = EquipmentType.get("Heat Sink");
        }

        for (; heatSinks > 0; heatSinks--) {

            try {
                unit.addEquipment(new Mounted(unit, sinkType), Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
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
        engineRating.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
    }

    public void addAllActionListeners() {
        gyroType.addActionListener(this);
        engineType.addActionListener(this);
        weightClass.addActionListener(this);
        cockpitType.addActionListener(this);
        heatSinkNumber.addActionListener(this);
        heatSinkType.addActionListener(this);
        engineRating.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);

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
    
    private void updateHeatSinks(){
        removeHeatSinks();
        refresh.refreshEquipment();
        if (heatSinkType.getSelectedIndex() == 1 || heatSinkType.getSelectedIndex() == 3)
            unit.addEngineSinks(heatSinkNumber.getSelectedIndex(), true);
        else
            unit.addEngineSinks(heatSinkNumber.getSelectedIndex(), false);
        addHeatSinkMounts();
    }
}