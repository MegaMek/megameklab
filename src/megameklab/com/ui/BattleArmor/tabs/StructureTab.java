/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.com.ui.BattleArmor.tabs;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.EntityMovementMode;
import megamek.common.EntityWeightClass;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megameklab.com.ui.BattleArmor.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImagePanel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class StructureTab extends ITab implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private JComboBox weightClass;
    Vector<String> weightClasses = new Vector<String>(5, 1);
    private JComboBox groundMovement;
    private JComboBox jumpMovement;
    private JComboBox umuMovement;
    private JComboBox vtolMovement;

    private String[] techTypes =
        { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    private JComboBox techType = new JComboBox(techTypes);
    private String[] isTechLevels =
        { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private JComboBox techLevel = new JComboBox(isTechLevels);
    private String[] formationSizeArray =
        { "4", "5", "6" };
    private JComboBox formationSize = new JComboBox(formationSizeArray);
    private String[] bodyTypeArray =
        { "Humanoid", "Quad" };
    private JComboBox bodyType = new JComboBox(bodyTypeArray);
    private String[] manipulatorArray =
        { "None", "Armored Glove", "Basic Manipulator", "Basic Manipulator with Mine Clearance", "Battle Claw", "Magnetic Battle Claw", "Vibro Battle Claw", "Heavy Magnetic Battle Claw", "Heavy Vibro Battle Claw", ".5 ton Cargo Lifter", "1 ton Cargo Lifter", "1.5 ton Cargo Lifter", "2 ton Cargo Lifter", "2.5 ton Cargo Lifter", "3 ton Cargo Lifter", "Heavy Battle Claw", "Industrial Drill", "Salvage Arm" };
    private JComboBox rightManipulator = new JComboBox(manipulatorArray);
    private JComboBox leftManipulator = new JComboBox(manipulatorArray);

    private JLabel rightManipulatorLabel = new JLabel("Right Manipulator:", SwingConstants.TRAILING);
    private JLabel leftManipulatorLabel = new JLabel("Left Manipulator:", SwingConstants.TRAILING);
    private JLabel jumpLabel = new JLabel("Jump Movement:", SwingConstants.TRAILING);
    private JLabel umuLabel = new JLabel("UMU Movement:", SwingConstants.TRAILING);
    private JLabel vtolLabel = new JLabel("VTOL Movement:", SwingConstants.TRAILING);

    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);

    private RefreshListener refresh = null;
    private Dimension maxSize = new Dimension();
    private JPanel masterPanel;

    private CriticalView critView = null;
    private ImagePanel unitImage = null;
    private JButton browseButton = null;

    public StructureTab(BattleArmor unit) {
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
        critView = new CriticalView(getBattleArmor(), false, refresh);

        JPanel imagePanel = new JPanel();

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        unitImage = new ImagePanel(getBattleArmor(), ImageHelper.imageBattleArmor);
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

        Vector<String> groundMovements = new Vector<String>(1, 1);

        for (int weight = 0; weight < 5; weight++) {
            weightClasses.add(EntityWeightClass.getClassName(weight));
            groundMovements.add(String.format("%1$d", weight + 1));
        }

        weightClass = new JComboBox(weightClasses.toArray());
        groundMovement = new JComboBox(groundMovements.toArray());
        umuMovement = new JComboBox(groundMovements.toArray());
        jumpMovement = new JComboBox(groundMovements.toArray());
        vtolMovement = new JComboBox(groundMovements.toArray());

        maxSize.setSize(110, 20);

        masterPanel.add(createLabel("Year:", maxSize));
        masterPanel.add(era);

        masterPanel.add(createLabel("Source/Era:", maxSize));
        masterPanel.add(source);

        masterPanel.add(createLabel("Tech:", maxSize));
        masterPanel.add(techType);
        masterPanel.add(createLabel("Tech Level:", maxSize));
        masterPanel.add(techLevel);

        masterPanel.add(createLabel("Formation Size:", maxSize));
        masterPanel.add(formationSize);
        masterPanel.add(createLabel("Body Type:", maxSize));
        masterPanel.add(bodyType);

        masterPanel.add(createLabel("Weight:", maxSize));
        masterPanel.add(weightClass);

        masterPanel.add(createLabel("Ground Movement:", maxSize));
        masterPanel.add(groundMovement);
        masterPanel.add(jumpLabel);
        masterPanel.add(jumpMovement);
        masterPanel.add(rightManipulatorLabel);
        masterPanel.add(rightManipulator);
        masterPanel.add(leftManipulatorLabel);
        masterPanel.add(leftManipulator);

        setFieldSize(rightManipulator, maxSize);
        setFieldSize(leftManipulator, maxSize);
        setFieldSize(rightManipulatorLabel, maxSize);
        setFieldSize(leftManipulatorLabel, maxSize);
        setFieldSize(groundMovement, maxSize);
        setFieldSize(jumpLabel, maxSize);
        setFieldSize(jumpMovement, maxSize);
        setFieldSize(umuMovement, maxSize);
        setFieldSize(vtolMovement, maxSize);
        setFieldSize(umuLabel, maxSize);
        setFieldSize(vtolLabel, maxSize);
        setFieldSize(formationSize, maxSize);
        setFieldSize(bodyType, maxSize);
        setFieldSize(era, maxSize);
        setFieldSize(source, maxSize);
        setFieldSize(techType, maxSize);
        setFieldSize(techLevel, maxSize);
        setFieldSize(weightClass, maxSize);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        era.setText(Integer.toString(getBattleArmor().getYear()));
        source.setText(getBattleArmor().getSource());

        int minGround = 1;
        int maxGround = 3;
        int maxJump = 3;
        int maxVTOL = 7;
        int maxUMU = 5;
        int minWeight = 0;

        // check for Quads so you can place Jump correctly
        if (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_BIPED) {
            masterPanel.remove(jumpLabel);
            masterPanel.remove(jumpMovement);
            masterPanel.add(jumpLabel);
            masterPanel.add(jumpMovement);
        } else {
            masterPanel.remove(jumpLabel);
            masterPanel.remove(jumpMovement);
            minWeight++;
        }

        if (getBattleArmor().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
            masterPanel.add(umuLabel);
            masterPanel.add(umuMovement);
            masterPanel.add(vtolLabel);
            masterPanel.add(vtolMovement);
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
            masterPanel.remove(vtolLabel);
            masterPanel.remove(vtolMovement);
            masterPanel.remove(umuLabel);
            masterPanel.remove(umuMovement);
        }

        // Chassis is checked again this time for manipulator placement
        if (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_BIPED) {
            masterPanel.remove(rightManipulator);
            masterPanel.remove(leftManipulator);
            masterPanel.remove(rightManipulatorLabel);
            masterPanel.remove(leftManipulatorLabel);
            masterPanel.add(rightManipulatorLabel);
            masterPanel.add(rightManipulator);
            masterPanel.add(leftManipulatorLabel);
            masterPanel.add(leftManipulator);
        } else {
            masterPanel.remove(rightManipulator);
            masterPanel.remove(leftManipulator);
            masterPanel.remove(rightManipulatorLabel);
            masterPanel.remove(leftManipulatorLabel);
            minGround = 2;
            maxGround = 5;
        }

        if (getBattleArmor().isMixedTech()) {
            if (getBattleArmor().isClan()) {

                techType.setSelectedIndex(3);
                if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getBattleArmor().isClan()) {

            techType.setSelectedIndex(1);
            if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        if (weightClass.getSelectedIndex() >= 3) {
            maxGround--;
            maxJump--;
            maxVTOL = 0;
            maxUMU -= weightClass.getSelectedIndex() - 1;
        } else if (weightClass.getSelectedIndex() == 1) {
            maxVTOL -= weightClass.getSelectedIndex();
        } else if (weightClass.getSelectedIndex() >= 2) {
            maxUMU -= weightClass.getSelectedIndex() - 1;
            maxVTOL -= weightClass.getSelectedIndex();
        }

        updateGroundMovements(minGround, maxGround);
        updateJumpMovements(maxJump);
        updateVTOLMovements(maxVTOL);
        updateUMUMovements(maxUMU);
        updateWeightClasses(minWeight);
        updateManipulators();

        critView.updateUnit(getBattleArmor());
        critView.refresh();

        unitImage.updateUnit(getBattleArmor());
        unitImage.refresh();

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
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

            if (getBattleArmor().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(getBattleArmor().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "BattleArmor" + File.separatorChar);
                fDialog.setFile(getBattleArmor().getChassis() + " " + getBattleArmor().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(this);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                getBattleArmor().getFluff().setMMLImagePath(relativeFilePath);
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
                // if a BattleArmor is primitive and thus needs a larger engine
                if (combo.equals(weightClass)) {
                    if (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD) {
                        getBattleArmor().setWeightClass(weightClass.getSelectedIndex() + 1);
                    } else {
                        getBattleArmor().setWeightClass(weightClass.getSelectedIndex());
                    }

                    getBattleArmor().setWeight(getBattleArmor().getTroopers());
                    getBattleArmor().setTrooperWeight(EntityWeightClass.getClassLimit(getBattleArmor().getWeightClass(), getBattleArmor()));
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (getBattleArmor().isClan()) {
                        switch (unitTechLevel) {
                            case 0:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                addAllActionListeners();
                                techType.setSelectedIndex(1);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                                break;
                            case 2:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                break;
                            case 3:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                break;
                            default:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                break;
                        }

                    } else {
                        switch (unitTechLevel) {
                            case 0:
                                getBattleArmor().setTechLevel(TechConstants.T_INTRO_BOXSET);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 2:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                                break;
                            case 3:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                break;
                            default:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                break;
                        }

                    }

                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                        getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getBattleArmor().setMixedTech(false);
                        masterPanel.add(umuLabel);
                        masterPanel.add(umuMovement);
                        masterPanel.add(vtolLabel);
                        masterPanel.add(vtolMovement);

                    } else if ((techType.getSelectedIndex() == 0) && (getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getBattleArmor().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getBattleArmor().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getBattleArmor().setMixedTech(false);
                        masterPanel.remove(umuLabel);
                        masterPanel.remove(umuMovement);
                        masterPanel.remove(vtolLabel);
                        masterPanel.remove(vtolMovement);

                    } else if ((techType.getSelectedIndex() == 2) && (!getBattleArmor().isMixedTech() || getBattleArmor().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }
                        if (unit.getYear() < 3090) {
                            //before 3090, mixed tech is experimental
                            if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                                unit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                unit.setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            }
                        } else if (unit.getYear() < 3145) {
                            // between 3090 and 3145, mixed tech is advanced
                            if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                                unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                                unit.setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                            }
                        } else {
                            // from 3145 on, mixed tech is tourney legal
                            if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                                unit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                unit.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            }
                        }
                        getBattleArmor().setMixedTech(true);
                        masterPanel.remove(umuLabel);
                        masterPanel.remove(umuMovement);
                        masterPanel.remove(vtolLabel);
                        masterPanel.remove(vtolMovement);

                    } else if ((techType.getSelectedIndex() == 3) && (!getBattleArmor().isMixedTech() || !getBattleArmor().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }
                        if (unit.getYear() < 3090) {
                            //before 3090, mixed tech is experimental
                            if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                                unit.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                unit.setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            }
                        } else if (unit.getYear() < 3145) {
                            // between 3090 and 3145, mixed tech is advanced
                            if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                                unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                                unit.setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                            }
                        } else {
                            // from 3145 on, mixed tech is tourney legal
                            if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_CLAN_TW)) {
                                unit.setTechLevel(TechConstants.T_CLAN_TW);
                                unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                            }
                        }
                        getBattleArmor().setMixedTech(true);
                        masterPanel.add(umuLabel);
                        masterPanel.add(umuMovement);
                        masterPanel.add(vtolLabel);
                        masterPanel.add(vtolMovement);
                    } else {
                        addAllActionListeners();
                        return;
                    }
                    addAllActionListeners();
                    removeAllActionListeners();
                } else if (combo.equals(bodyType)) {
                    if (combo.getSelectedItem().equals("Quad")) {
                        getBattleArmor().setChassisType(BattleArmor.CHASSIS_TYPE_QUAD);
                        masterPanel.remove(rightManipulator);
                        masterPanel.remove(leftManipulator);
                        masterPanel.remove(rightManipulatorLabel);
                        masterPanel.remove(leftManipulatorLabel);
                    } else {
                        getBattleArmor().setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);
                        masterPanel.remove(rightManipulator);
                        masterPanel.remove(leftManipulator);
                        masterPanel.remove(rightManipulatorLabel);
                        masterPanel.remove(leftManipulatorLabel);
                        masterPanel.add(rightManipulatorLabel);
                        masterPanel.add(rightManipulator);
                        masterPanel.add(leftManipulatorLabel);
                        masterPanel.add(leftManipulator);
                    }
                } else if (combo.equals(groundMovement)) {
                    getBattleArmor().setMovementMode(EntityMovementMode.INF_LEG);
                    getBattleArmor().setOriginalWalkMP(groundMovement.getSelectedIndex() + 1);
                } else if (combo.equals(jumpMovement)) {
                    getBattleArmor().setMovementMode(EntityMovementMode.INF_JUMP);
                    getBattleArmor().setOriginalJumpMP(jumpMovement.getSelectedIndex());
                    umuMovement.setSelectedIndex(0);
                    vtolMovement.setSelectedIndex(0);
                } else if (combo.equals(umuMovement)) {
                    getBattleArmor().setMovementMode(EntityMovementMode.INF_UMU);
                    getBattleArmor().setOriginalJumpMP(umuMovement.getSelectedIndex());
                    vtolMovement.setSelectedIndex(0);
                    jumpMovement.setSelectedIndex(0);
                } else if (combo.equals(vtolMovement)) {
                    getBattleArmor().setMovementMode(EntityMovementMode.VTOL);
                    getBattleArmor().setOriginalJumpMP(vtolMovement.getSelectedIndex());
                    umuMovement.setSelectedIndex(0);
                    jumpMovement.setSelectedIndex(0);
                } else if (combo.equals(rightManipulator)) {
                    int index = combo.getSelectedIndex();

                    if ((index == 3) || (index == 5) || (index == 7) || ((index >= 9) && (index <= 14))) {
                        leftManipulator.setSelectedIndex(index);
                    }

                } else if (combo.equals(leftManipulator)) {
                    int index = combo.getSelectedIndex();

                    if ((index == 3) || (index == 5) || (index == 7) || ((index >= 9) && (index <= 14))) {
                        rightManipulator.setSelectedIndex(index);
                    }

                } else if (combo.equals(formationSize)) {
                    getBattleArmor().setTroopers(formationSize.getSelectedIndex() + 4);
                    getBattleArmor().refreshLocations();
                    getBattleArmor().autoSetInternal();
                    for (int loc = 0; loc < getBattleArmor().locations(); loc++) {
                        getBattleArmor().initializeArmor(0, loc);
                    }
                    getBattleArmor().setWeight(getBattleArmor().getTroopers());
                    getBattleArmor().setTrooperWeight(EntityWeightClass.getClassLimit(getBattleArmor().getWeightClass(), getBattleArmor()));
                }
                addAllActionListeners();
                refresh.refreshAll();
            } catch (Exception ex) {
                ex.printStackTrace();
                addAllActionListeners();
            }
        } else if (e.getSource() instanceof JCheckBox) {
            // JCheckBox check = (JCheckBox) e.getSource();

            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        }
        refresh.refreshAll();

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getBattleArmor().locations(); loc++) {
            for (int slot = 0; slot < getBattleArmor().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getBattleArmor().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getBattleArmor().setCritical(loc, slot, null);
                }
            }
        }
    }

    public void removeAllActionListeners() {
        weightClass.removeActionListener(this);
        groundMovement.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        rightManipulator.removeActionListener(this);
        leftManipulator.removeActionListener(this);
        jumpMovement.removeActionListener(this);
        umuMovement.removeActionListener(this);
        vtolMovement.removeActionListener(this);
        formationSize.removeActionListener(this);
        bodyType.removeActionListener(this);

    }

    public void addAllActionListeners() {
        weightClass.addActionListener(this);
        groundMovement.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        rightManipulator.addActionListener(this);
        leftManipulator.addActionListener(this);
        jumpMovement.addActionListener(this);
        umuMovement.addActionListener(this);
        vtolMovement.addActionListener(this);
        formationSize.addActionListener(this);
        bodyType.addActionListener(this);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                getBattleArmor().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getBattleArmor().setYear(3071);
            }
        } else if (e.getSource().equals(source)) {
            getBattleArmor().setSource(source.getText());
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void updateGroundMovements(int min, int max) {
        groundMovement.removeAllItems();
        for (Integer movement = min; movement <= max; movement++) {
            groundMovement.addItem(movement.toString());
        }

        String currentMovement = Integer.toString(getBattleArmor().getWalkMP());

        for (int pos = 0; pos < groundMovement.getItemCount(); pos++) {
            if (groundMovement.getItemAt(pos).equals(currentMovement)) {
                groundMovement.setSelectedIndex(pos);
                return;
            }
        }
        groundMovement.setSelectedIndex(0);

    }

    private void updateJumpMovements(int max) {
        jumpMovement.removeAllItems();
        for (Integer movement = 0; movement <= max; movement++) {
            jumpMovement.addItem(movement.toString());
        }

        String currentMovement = Integer.toString(getBattleArmor().getOriginalJumpMP());

        if (getBattleArmor().getMovementMode() == EntityMovementMode.INF_JUMP) {
            for (int pos = 0; pos < jumpMovement.getItemCount(); pos++) {
                if (jumpMovement.getItemAt(pos).equals(currentMovement)) {
                    jumpMovement.setSelectedIndex(pos);
                    return;
                }
            }
        }
        jumpMovement.setSelectedIndex(0);

    }

    private void updateVTOLMovements(int max) {
        vtolMovement.removeAllItems();
        for (Integer movement = 0; movement <= max; movement++) {
            vtolMovement.addItem(movement.toString());
        }

        String currentMovement = Integer.toString(getBattleArmor().getOriginalJumpMP());

        if (getBattleArmor().getMovementMode() == EntityMovementMode.VTOL) {
            for (int pos = 0; pos < vtolMovement.getItemCount(); pos++) {
                if (vtolMovement.getItemAt(pos).equals(currentMovement)) {
                    vtolMovement.setSelectedIndex(pos);
                    return;
                }
            }
        }
        vtolMovement.setSelectedIndex(0);

    }

    private void updateUMUMovements(int max) {
        umuMovement.removeAllItems();
        for (Integer movement = 0; movement <= max; movement++) {
            umuMovement.addItem(movement.toString());
        }

        String currentMovement = Integer.toString(getBattleArmor().getOriginalJumpMP());

        if (getBattleArmor().getMovementMode() == EntityMovementMode.INF_UMU) {
            for (int pos = 0; pos < umuMovement.getItemCount(); pos++) {
                if (umuMovement.getItemAt(pos).equals(currentMovement)) {
                    umuMovement.setSelectedIndex(pos);
                    return;
                }
            }
        }
        umuMovement.setSelectedIndex(0);

    }

    private void updateManipulators() {

        boolean rightHand = true;

        for (Mounted mount : unit.getMisc()) {

            for (int pos = 1; pos < manipulatorArray.length; pos++) {
                if (mount.getName().equalsIgnoreCase(manipulatorArray[pos])) {
                    if (rightHand) {
                        rightHand = false;
                        rightManipulator.setSelectedIndex(pos);
                    } else {
                        leftManipulator.setSelectedIndex(pos);
                    }
                }
            }
        }
    }

    private void updateWeightClasses(int min) {
        weightClass.removeAllItems();
        for (int pos = min; pos < weightClasses.size(); pos++) {
            weightClass.addItem(weightClasses.elementAt(pos));
        }

        String currentWeight = getBattleArmor().getWeightClassName();

        for (int pos = 0; pos < weightClass.getItemCount(); pos++) {
            if (weightClass.getItemAt(pos).equals(currentWeight)) {
                weightClass.setSelectedIndex(pos);
                return;
            }
        }
        weightClass.setSelectedIndex(0);

    }
}

