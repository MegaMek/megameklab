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

package megameklab.com.ui.Vehicle.tabs;

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

import megamek.common.Engine;
import megamek.common.IEntityMovementMode;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.TroopSpace;
import megameklab.com.ui.Vehicle.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class StructureTab extends ITab implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    String[] isEngineTypes = { "I.C.E.", "Fusion", "XL", "XXL", "Fuel Cell", "Light", "Compact", "Fission" };
    String[] clanEngineTypes = { "I.C.E.", "Fusion", "XL", "XXL", "Fuel Cell" };
    JComboBox engineType = new JComboBox();
    JComboBox cruiseMP;
    JComboBox weightClass;
    String[] techTypes = { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels = { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental", "Unoffical" };
    JComboBox techLevel = new JComboBox(isTechLevels);
    String[] tankMotiveTypes = { "Hover", "Wheeled", "Tracked" };
    JComboBox tankMotiveType = new JComboBox(tankMotiveTypes);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox turretCB = new JCheckBox("Turret");
    Dimension maxSize = new Dimension();
    JLabel baseChassisHeatSinksLabel = new JLabel("Base Heat Sinks:", SwingConstants.TRAILING);
    JPanel masterPanel;
    JComboBox troopStorage = null;
    int maxTonnage = 50;

    private CriticalView critView = null;

    public StructureTab(Tank unit) {
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
        masterPanel = new JPanel(new SpringLayout());

        Vector<String> cruiseMPs = new Vector<String>(26, 1);

        for (int pos = 1; pos <= 25; pos++) {
            cruiseMPs.add(Integer.toString(pos));
        }

        cruiseMP = new JComboBox(cruiseMPs.toArray());

        Vector<String> weightClasses = new Vector<String>(1, 1);

        for (int weight = 1; weight <= maxTonnage; weight++) {
            weightClasses.add(Integer.toString(weight));
        }

        weightClass = new JComboBox(weightClasses.toArray());
        troopStorage = new JComboBox(weightClasses.toArray());

        troopStorage.setSelectedIndex(0);

        maxSize.setSize(110, 20);

        masterPanel.add(omniCB);
        masterPanel.add(turretCB);

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

        masterPanel.add(createLabel("Cruise MP:", maxSize));
        masterPanel.add(cruiseMP);

        masterPanel.add(createLabel("Movment Type:", maxSize));
        masterPanel.add(tankMotiveType);

        masterPanel.add(createLabel("Weight:", maxSize));
        masterPanel.add(weightClass);

        masterPanel.add(createLabel("Troop Space:", maxSize));
        masterPanel.add(troopStorage);

        setFieldSize(cruiseMP, maxSize);
        setFieldSize(era, maxSize);
        setFieldSize(source, maxSize);
        setFieldSize(techType, maxSize);
        setFieldSize(techLevel, maxSize);
        setFieldSize(engineType, maxSize);
        setFieldSize(weightClass, maxSize);
        setFieldSize(tankMotiveType, maxSize);
        setFieldSize(troopStorage, maxSize);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        omniCB.setSelected(unit.isOmni());
        turretCB.setSelected(getTank().getInternal(Tank.LOC_TURRET) > 0);
        era.setText(Integer.toString(unit.getYear()));
        source.setText(unit.getSource());
        weightClass.setSelectedIndex((int) unit.getWeight() - 1);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
        masterPanel.setVisible(false);
        masterPanel.setVisible(true);

        updateEngineTypes();
        updateTroopSpaceAllowed();

        engineType.setSelectedIndex(unit.getEngine().getEngineType());

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

        cruiseMP.setSelectedIndex(unit.getWalkMP() - 1);

        critView.updateUnit(unit);
        critView.refresh();

        addAllActionListeners();

        switch (unit.getMovementMode()) {
        case IEntityMovementMode.HOVER:
            tankMotiveType.setSelectedIndex(0);
            break;
        case IEntityMovementMode.WHEELED:
            tankMotiveType.setSelectedIndex(1);
            break;
        case IEntityMovementMode.TRACKED:
            tankMotiveType.setSelectedIndex(2);
            break;
        }

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
                // if a Tank is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(cruiseMP)) {
                    int rating = (cruiseMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int type = engineType.getSelectedIndex();
                        if (getTank().isClan()) {
                            getTank().setEngine(new Engine(rating, type, Engine.CLAN_ENGINE));
                        } else {
                            getTank().setEngine(new Engine(rating, type, 0));
                        }
                    }
                } else if (combo.equals(troopStorage)) {
                    unit.removeAllTransporters();
                    if (troopStorage.getSelectedIndex() > 0) {
                        unit.addTransporter(new TroopSpace(troopStorage.getSelectedIndex()));
                    }
                } else if (combo.equals(weightClass)) {
                    int rating = (cruiseMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString());
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        unit.setWeight(Float.parseFloat(weightClass.getSelectedItem().toString()));
                        unit.autoSetInternal();
                        addAllActionListeners();
                        engineType.setSelectedIndex(engineType.getSelectedIndex());
                        removeAllActionListeners();
                    }
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (unit.isClan()) {
                        switch (unitTechLevel) {
                        case 0:
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                            addAllActionListeners();
                            techType.setSelectedIndex(1);
                            removeAllActionListeners();
                            break;
                        case 1:
                            unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            unit.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            unit.setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                            break;
                        }

                    } else {
                        switch (unitTechLevel) {
                        case 0:
                            unit.setTechLevel(TechConstants.T_INTRO_BOXSET);
                            unit.setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 1:
                            unit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            unit.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 2:
                            unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                            unit.setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                            unit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            unit.setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            unit.setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            unit.setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            break;
                        }

                    }

                    int engineNumber = engineType.getSelectedIndex();
                    updateEngineTypes();

                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    // Reset the engine
                    if (engineNumber >= engineType.getItemCount()) {
                        engineType.setSelectedIndex(1);
                    } else {
                        engineType.setSelectedIndex(engineNumber);
                    }
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!unit.isClan() || unit.isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        unit.setTechLevel(TechConstants.T_CLAN_TW);
                        unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                        unit.setMixedTech(false);
                    } else if ((techType.getSelectedIndex() == 0) && (unit.isClan() || unit.isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        unit.setTechLevel(TechConstants.T_INTRO_BOXSET);
                        unit.setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        unit.setMixedTech(false);

                    } else if ((techType.getSelectedIndex() == 2) && (!unit.isMixedTech() || unit.isClan())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                        unit.setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        unit.setMixedTech(true);

                    } else if ((techType.getSelectedIndex() == 3) && (!unit.isMixedTech() || !unit.isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        unit.setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        unit.setMixedTech(true);
                    } else {
                        addAllActionListeners();
                        return;
                    }
                    updateEngineTypes();
                    addAllActionListeners();
                    engineType.setSelectedIndex(1);
                    engineType.repaint();
                    removeAllActionListeners();
                } else if (combo.equals(tankMotiveType)) {
                    int currentTonnage = weightClass.getSelectedIndex() + 1;

                    weightClass.removeAll();
                    weightClass.removeAllItems();

                    switch (tankMotiveType.getSelectedIndex()) {
                    case 0:
                        maxTonnage = 50;
                        unit.setMovementMode(IEntityMovementMode.HOVER);
                        break;
                    case 1:
                        maxTonnage = 80;
                        unit.setMovementMode(IEntityMovementMode.WHEELED);
                        break;
                    case 2:
                        maxTonnage = 100;
                        unit.setMovementMode(IEntityMovementMode.TRACKED);
                        break;
                    }

                    currentTonnage = Math.min(currentTonnage, maxTonnage);
                    unit.setWeight(currentTonnage);

                    for (int pos = 1; pos <= maxTonnage; pos++) {
                        weightClass.addItem(pos);
                    }

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
                if (unit.isOmni()) {
                } else {
                    unit.getEngine().setBaseChassisHeatSinks(-1);
                }
                SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
                masterPanel.setVisible(false);
                masterPanel.setVisible(true);
            }
            refresh.refreshAll();

        }

    }

    public void removeAllActionListeners() {
        engineType.removeActionListener(this);
        weightClass.removeActionListener(this);
        cruiseMP.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        omniCB.removeActionListener(this);
        turretCB.removeActionListener(this);
        tankMotiveType.removeActionListener(this);
        troopStorage.removeActionListener(this);
    }

    public void addAllActionListeners() {
        engineType.addActionListener(this);
        weightClass.addActionListener(this);
        cruiseMP.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        omniCB.addActionListener(this);
        turretCB.addActionListener(this);
        tankMotiveType.addActionListener(this);
        troopStorage.addActionListener(this);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                unit.setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                unit.setYear(2075);
            }
        } else if (e.getSource().equals(source)) {
            unit.setSource(source.getText());
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public boolean hasTurret() {
        return turretCB.isSelected();
    }

    private void updateEngineTypes() {
        int maxEngineType = clanEngineTypes.length;
        String[] engineTypes;
        if (unit.isClan()) {
            engineTypes = clanEngineTypes;
        } else {
            engineTypes = isEngineTypes;
            switch (techLevel.getSelectedIndex()) {
            case 0:
                maxEngineType = 2;
                break;
            case 1:
                maxEngineType = 5;
                break;
            case 2:
                maxEngineType = 7;
                break;
            case 3:
            case 4:
                maxEngineType = 8;
                break;
            }
        }

        // engineType.removeAll();
        engineType.removeAllItems();

        for (int pos = 0; pos < maxEngineType; pos++) {
            engineType.addItem(engineTypes[pos]);
        }

    }

    private void updateTroopSpaceAllowed() {
        int troops = unit.getTroopCarryingSpace();

        troopStorage.removeAll();
        troopStorage.removeAllItems();

        for (int space = 0; space <= unit.getWeight(); space++) {
            troopStorage.addItem(space);
        }

        if (troops > (int) unit.getWeight()) {
            unit.removeAllTransporters();
            troopStorage.setSelectedIndex(0);
        } else {
            troopStorage.setSelectedIndex(troops);
        }

    }
}
