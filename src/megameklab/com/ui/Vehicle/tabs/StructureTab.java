/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle.tabs;

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
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Engine;
import megamek.common.EntityMovementMode;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.TroopSpace;
import megameklab.com.ui.Vehicle.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImagePanel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class StructureTab extends ITab implements ActionListener, KeyListener, ChangeListener {

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
        { ENGINESTANDARD, ENGINEICE, ENGINEXL, ENGINELIGHT, ENGINECOMPACT, ENGINEFISSION, ENGINEFUELCELL, ENGINEXXL };
    String[] clanEngineTypes =
        { ENGINESTANDARD, ENGINEICE, ENGINEXL, ENGINEFUELCELL, ENGINEXXL };

    JComboBox engineType = new JComboBox(isEngineTypes);
    JComboBox cruiseMP;
    JComboBox weightClass;
    String[] techTypes =
        { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels =
        { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    JComboBox techLevel = new JComboBox(isTechLevels);
    String[] tankMotiveTypes =
        { "Hover", "Wheeled", "Tracked" };
    JComboBox tankMotiveType = new JComboBox(tankMotiveTypes);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    String[] turretTypes =
        { "None", "Single", "Dual" };
    JComboBox turretCombo = new JComboBox(turretTypes);
    Dimension maxSize = new Dimension();
    JLabel baseChassisHeatSinksLabel = new JLabel("Base Heat Sinks:", SwingConstants.TRAILING);
    JPanel masterPanel;
    JSpinner troopStorage = null;
    int maxTonnage = 50;

    private CriticalView critView = null;
    private ImagePanel unitImage = null;
    private JButton browseButton = null;

    public StructureTab(Tank unit) {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, enginePanel(), scroll);

        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        critView = new CriticalView(unit, false, refresh);

        JPanel imagePanel = new JPanel();

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        unitImage = new ImagePanel(getTank(), ImageHelper.imageVehicle);
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
        troopStorage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));

        maxSize.setSize(110, 20);

        masterPanel.add(omniCB);
        masterPanel.add(new JLabel(""));

        masterPanel.add(createLabel("Turret:", maxSize));
        masterPanel.add(turretCombo);

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

        masterPanel.add(createLabel("Movment Type:", maxSize));
        masterPanel.add(tankMotiveType);

        masterPanel.add(createLabel("Cruise MP:", maxSize));
        masterPanel.add(cruiseMP);

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
        setFieldSize(turretCombo, maxSize);

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        omniCB.setSelected(unit.isOmni());

        if (!getTank().hasNoDualTurret()) {
            turretCombo.setSelectedIndex(2);
        } else if (!getTank().hasNoTurret()) {
            turretCombo.setSelectedIndex(1);
        } else {
            turretCombo.setSelectedIndex(0);
        }

        era.setText(Integer.toString(unit.getYear()));
        source.setText(unit.getSource());

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

        switch (unit.getMovementMode()) {
            case HOVER:
                tankMotiveType.setSelectedIndex(0);
                break;
            case WHEELED:
                tankMotiveType.setSelectedIndex(1);
                break;
            case TRACKED:
                tankMotiveType.setSelectedIndex(2);
                break;
        }

        populateWeight((int) unit.getWeight());

        cruiseMP.setSelectedIndex(unit.getWalkMP() - 1);

        updateEngineTypes(getTank().isClan());
        updateTroopSpaceAllowed();

        engineType.setSelectedIndex(convertEngineType(getTank().getEngine().getEngineType()));

        critView.updateUnit(unit);
        critView.refresh();

        unitImage.updateUnit(getTank());
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

        if (refresh == null) {
            return;
        }

        if ((e.getSource() instanceof JButton) && e.getSource().equals(browseButton)) {
            FileDialog fDialog = new FileDialog(new JFrame(), "Image Path", FileDialog.LOAD);

            if (getTank().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(getTank().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageVehicle + File.separatorChar);
                fDialog.setFile(getTank().getChassis() + " " + getTank().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(this);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                getTank().getFluff().setMMLImagePath(relativeFilePath);
                refresh();
            }
            return;
        }

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            try {
                // if a Tank is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(cruiseMP) || combo.equals(weightClass)) {
                    int rating = Math.max(10, (cruiseMP.getSelectedIndex() + 1) * Integer.parseInt(weightClass.getSelectedItem().toString()) - ((Tank) unit).getSuspensionFactor());
                    if (rating > 500) {
                        JOptionPane.showMessageDialog(this, "That speed would create an engine with a rating over 500.", "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
                    } else {
                        unit.setWeight(Float.parseFloat(weightClass.getSelectedItem().toString()));
                        ((SpinnerNumberModel) troopStorage.getModel()).setMaximum(Double.parseDouble(weightClass.getSelectedItem().toString()));
                        unit.autoSetInternal();
                        int type = convertEngineType(engineType.getSelectedItem().toString());
                        if (getTank().isClan()) {
                            getTank().setEngine(new Engine(rating, type, Engine.CLAN_ENGINE | Engine.TANK_ENGINE));
                        } else {
                            getTank().setEngine(new Engine(rating, type, Engine.TANK_ENGINE));
                        }
                        getTank().setOriginalWalkMP(cruiseMP.getSelectedIndex() + 1);
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
                    updateEngineTypes(getTank().isClan());

                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    // Reset the engine
                    if (engineNumber >= engineType.getItemCount()) {
                        engineType.setSelectedIndex(0);
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
                    int engineNumber = engineType.getSelectedIndex();
                    updateEngineTypes(getTank().isClan());

                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    // Reset the engine
                    if (engineNumber >= engineType.getItemCount()) {
                        engineType.setSelectedIndex(0);
                    } else {
                        engineType.setSelectedIndex(engineNumber);
                    }
                    removeAllActionListeners();
                } else if (combo.equals(tankMotiveType)) {
                    int currentTonnage = weightClass.getSelectedIndex() + 1;

                    populateWeight(currentTonnage);
                } else if (combo.equals(turretCombo)) {

                    getTank().setHasNoTurret(combo.getSelectedIndex() == 0);
                    getTank().setHasNoDualTurret(combo.getSelectedIndex() <= 1);

                    getTank().autoSetInternal();
                    for (int loc = 0; loc < getTank().locations(); loc++) {
                        getTank().setArmor(0, loc);
                        getTank().setArmor(0, loc, true);
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
        turretCombo.removeActionListener(this);
        tankMotiveType.removeActionListener(this);
        troopStorage.getModel().removeChangeListener(this);
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
        turretCombo.addActionListener(this);
        tankMotiveType.addActionListener(this);
        troopStorage.getModel().addChangeListener(this);
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
        return turretCombo.getSelectedIndex() > 0;
    }

    public boolean hasDualTurret() {
        return turretCombo.getSelectedIndex() > 1;
    }

    private void updateTroopSpaceAllowed() {
        float troops = unit.getTroopCarryingSpace();

        if (troops > (int) unit.getWeight()) {
            unit.removeAllTransporters();
            ((SpinnerNumberModel) troopStorage.getModel()).setValue(0);
        } else {
            ((SpinnerNumberModel) troopStorage.getModel()).setValue((double) troops);
        }

    }

    private void populateWeight(int currentTonnage) {
        weightClass.removeAllItems();

        switch (tankMotiveType.getSelectedIndex()) {
            case 0:
                maxTonnage = 50;
                unit.setMovementMode(EntityMovementMode.HOVER);
                break;
            case 1:
                maxTonnage = 80;
                unit.setMovementMode(EntityMovementMode.WHEELED);
                break;
            case 2:
                maxTonnage = 100;
                unit.setMovementMode(EntityMovementMode.TRACKED);
                break;
        }

        currentTonnage = Math.min(currentTonnage, maxTonnage);
        unit.setWeight(currentTonnage);
        ((SpinnerNumberModel) troopStorage.getModel()).setMaximum((double) currentTonnage);

        for (int pos = 1; pos <= maxTonnage; pos++) {
            weightClass.addItem(pos);
        }

        weightClass.setSelectedIndex((int) unit.getWeight() - 1);
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

    private void updateEngineTypes(boolean isClan) {

        int engineCount = 1;
        String[] engineList;

        engineType.removeAllItems();

        if (isClan) {
            engineList = clanEngineTypes;
            switch (getTank().getTechLevel()) {
                case TechConstants.T_CLAN_TW:
                    engineCount = 3;
                    break;
                case TechConstants.T_CLAN_ADVANCED:
                    engineCount = 4;
                    break;
                case TechConstants.T_CLAN_EXPERIMENTAL:
                case TechConstants.T_CLAN_UNOFFICIAL:
                    engineCount = 5;
                    break;
            }
        } else {

            engineList = isEngineTypes;
            switch (getTank().getTechLevel()) {
                case TechConstants.T_INTRO_BOXSET:
                    engineCount = 2;
                    break;
                case TechConstants.T_IS_TW_NON_BOX:
                    engineCount = 5;
                    break;
                case TechConstants.T_IS_ADVANCED:
                    engineCount = 7;
                    break;
                case TechConstants.T_IS_EXPERIMENTAL:
                case TechConstants.T_IS_UNOFFICIAL:
                    engineCount = 8;
                    break;
            }
        }

        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

    }

    private int convertEngineType(int engineType) {

        if (getTank().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            switch (engineType) {
                case Engine.NORMAL_ENGINE:
                    return 0;
                case Engine.COMBUSTION_ENGINE:
                    return 1;
                case Engine.XL_ENGINE:
                    return 2;
                case Engine.FUEL_CELL:
                    return 3;
                case Engine.XXL_ENGINE:
                    return 4;
            }
        } else {
            switch (engineType) {
                case Engine.NORMAL_ENGINE:
                    return 0;
                case Engine.COMBUSTION_ENGINE:
                    return 1;
                case Engine.XL_ENGINE:
                    return 2;
                case Engine.LIGHT_ENGINE:
                    return 3;
                case Engine.COMPACT_ENGINE:
                    return 4;
                case Engine.FISSION:
                    return 5;
                case Engine.FUEL_CELL:
                    return 6;
                case Engine.XXL_ENGINE:
                    return 7;
            }
        }

        return 0;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(troopStorage.getModel())) {
            removeAllActionListeners();
            unit.removeAllTransporters();
            if (((SpinnerNumberModel) troopStorage.getModel()).getNumber().doubleValue() > 0) {
                unit.addTransporter(new TroopSpace(((SpinnerNumberModel) troopStorage.getModel()).getNumber().doubleValue()));
            }
            addAllActionListeners();
            refresh.refreshAll();
        }

    }
}
