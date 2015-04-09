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

package megameklab.com.ui.Infantry.tabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import megamek.common.EntityMovementMode;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Infantry.views.ArmorView;
import megameklab.com.ui.Infantry.views.WeaponView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -7985608549543235815L;

    private RefreshListener refresh;

    public static final int M_FOOT    = 0;
    public static final int M_JUMP    = 1;
    public static final int M_MOTOR   = 2;
    public static final int M_HOVER   = 3;
    public static final int M_TRACKED = 4;
    public static final int M_WHEELED = 5;


    private String[] techTypes =
        { "Inner Sphere", "Clan", "Mixed Inner Sphere", "Mixed Clan" };
    private JComboBox<String> techType = new JComboBox<String>(techTypes);
    private String[] isTechLevels =
        { "Introductory", "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private JComboBox<String> techLevel = new JComboBox<String>(isTechLevels);
    private String[] motiveTypeArray =
        { "Foot", "Jump", "Motorized", "Mechanized (Hover)", "Mechanized (Tracked)", "Mechanized (Wheeled)"};
    private JComboBox<String> motiveType = new JComboBox<String>(motiveTypeArray);
    private String[] squadSizeArray =
        { "1","2","3","4", "5", "6","7","8","9","10"};
    private JComboBox<String> squadSize = new JComboBox<String>(squadSizeArray);
    private String[] squadNArray =
        { "1","2","3","4","5"};
    private JComboBox<String> squadN = new JComboBox<String>(squadNArray);
    private String[] secondaryNArray =
        { "0","1","2"};
    private JComboBox<String> secondaryN = new JComboBox<String>(secondaryNArray);
    private JCheckBox antiMekTraining = new JCheckBox("Anti-mek Training");

    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);

    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);

    private JTextField txtPrimary = new JTextField("None");
    private JTextField txtSecondary = new JTextField("None");

	private ArmorView armorView;
	private WeaponView weaponView;

	public StructureTab(EntitySource eSource) {
	    super(eSource);
        armorView = new ArmorView(eSource);
        weaponView = new WeaponView(eSource);
        setUpPanels();
        refresh();
	}

	public void setUpPanels() {
	    JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel basicPanel = new JPanel(new GridBagLayout());
        JPanel squadPanel = new JPanel(new GridBagLayout());
        JPanel weaponPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(200, 25);
        Dimension labelSize = new Dimension(110, 25);

        txtPrimary.setEditable(false);
        txtSecondary.setEditable(false);

        chassis.setText(getInfantry().getChassis());
        model.setText(getInfantry().getModel());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,0,1,2);
        basicPanel.add(createLabel("Chassis:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(chassis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        basicPanel.add(createLabel("Model:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(model, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        basicPanel.add(createLabel("Year:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(era, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        basicPanel.add(createLabel("Source/Era:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(source, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        basicPanel.add(createLabel("Tech:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(techType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        basicPanel.add(createLabel("Tech Level:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(techLevel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        squadPanel.add(createLabel("Motive Type:", labelSize), gbc);
        gbc.gridx = 1;
        squadPanel.add(motiveType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        squadPanel.add(createLabel("# Squads:", labelSize), gbc);
        gbc.gridx = 1;
        squadPanel.add(squadN, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        squadPanel.add(createLabel("Squad Size:", labelSize), gbc);
        gbc.gridx = 1;
        squadPanel.add(squadSize, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        weaponPanel.add(createLabel("Primary:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(txtPrimary, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        weaponPanel.add(createLabel("Secondary:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(txtSecondary, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        weaponPanel.add(createLabel("Secondary #:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(secondaryN, gbc);
        antiMekTraining.setHorizontalTextPosition(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        weaponPanel.add(antiMekTraining, gbc);

        setFieldSize(motiveType, comboSize);
        setFieldSize(squadSize, comboSize);
        setFieldSize(squadN, comboSize);
        setFieldSize(secondaryN, comboSize);
        setFieldSize(era, comboSize);
        setFieldSize(source, comboSize);
        setFieldSize(chassis, comboSize);
        setFieldSize(model, comboSize);
        setFieldSize(techType, comboSize);
        setFieldSize(techLevel, comboSize);
        setFieldSize(txtPrimary, comboSize);
        setFieldSize(txtSecondary, comboSize);


        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        squadPanel.setBorder(BorderFactory.createTitledBorder("Movement and Size"));
        weaponPanel.setBorder(BorderFactory.createTitledBorder("Current Weapons"));
        armorView.setBorder(BorderFactory.createTitledBorder("Armor"));
        weaponView.setBorder(BorderFactory.createTitledBorder("Weapon Selection"));

        leftPanel.add(basicPanel);
        leftPanel.add(squadPanel);
        leftPanel.add(weaponPanel);
        leftPanel.add(armorView);
        leftPanel.add(Box.createVerticalGlue());
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(weaponView, gbc);

    }

	public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.RIGHT);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

	public void refresh() {
	    removeAllActionListeners();
        era.setText(Integer.toString(getInfantry().getYear()));
        source.setText(getInfantry().getSource());

        if (getInfantry().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
        }

        switch(getInfantry().getMovementMode()) {
            case INF_JUMP:
                motiveType.setSelectedIndex(M_JUMP);
                break;
            case INF_MOTORIZED:
                motiveType.setSelectedIndex(M_MOTOR);
                break;
            case HOVER:
                motiveType.setSelectedIndex(M_HOVER);
                break;
            case TRACKED:
                motiveType.setSelectedIndex(M_TRACKED);
                break;
            case WHEELED:
                motiveType.setSelectedIndex(M_WHEELED);
                break;
            default:
                motiveType.setSelectedIndex(M_FOOT);
                break;
        }
        squadN.setSelectedIndex(getInfantry().getSquadN()-1);
        squadSize.setSelectedIndex(getInfantry().getSquadSize()-1);
        secondaryN.setSelectedIndex(getInfantry().getSecondaryN());
        getInfantry().setAntiMekSkill(antiMekTraining.isSelected());

        if (getInfantry().isMixedTech()) {
            if (getInfantry().isClan()) {

                techType.setSelectedIndex(3);
                if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getInfantry().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getInfantry().isClan()) {

            techType.setSelectedIndex(1);
            if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getInfantry().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        }

        if(null != getInfantry().getPrimaryWeapon()) {
            txtPrimary.setText(UnitUtil.trimInfantryWeaponNames(getInfantry().getPrimaryWeapon().getName()));
        } else {
            txtPrimary.setText("None");
        }
        if(null != getInfantry().getSecondaryWeapon()) {
            txtSecondary.setText(UnitUtil.trimInfantryWeaponNames(getInfantry().getSecondaryWeapon().getName()));
        } else {
            txtSecondary.setText("None");
        }

        armorView.refresh();
        weaponView.refresh();
        addAllActionListeners();
	}

	public void addAllActionListeners() {
        motiveType.addActionListener(this);
        squadN.addActionListener(this);
        squadSize.addActionListener(this);
        secondaryN.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        antiMekTraining.addActionListener(this);
    }

    public void removeAllActionListeners() {
        motiveType.removeActionListener(this);
        squadN.removeActionListener(this);
        squadSize.removeActionListener(this);
        secondaryN.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        antiMekTraining.removeActionListener(this);
    }

	public void addRefreshedListener(RefreshListener l) {
	    refresh = l;
	    armorView.addRefreshedListener(refresh);
	    weaponView.addRefreshedListener(refresh);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        removeAllActionListeners();
        if (e.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            if (combo.equals(techLevel)) {
                int unitTechLevel = techLevel.getSelectedIndex();
                if (getInfantry().isClan()) {
                    switch (unitTechLevel) {
                        case 0:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            techType.setSelectedIndex(1);
                            UnitUtil.resetInfantryArmor(getInfantry());
                            break;
                        case 1:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            break;
                    }

                } else {
                    switch (unitTechLevel) {
                        case 0:
                            getInfantry().setTechLevel(TechConstants.T_INTRO_BOXSET);
                            getInfantry().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                            techType.setSelectedIndex(0);
                            UnitUtil.resetInfantryArmor(getInfantry());
                            break;
                        case 1:
                            getInfantry().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            techType.setSelectedIndex(0);
                            UnitUtil.resetInfantryArmor(getInfantry());
                            break;
                        case 2:
                            getInfantry().setTechLevel(TechConstants.T_IS_ADVANCED);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                            getInfantry().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            getInfantry().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            break;
                    }

                }
                UnitUtil.checkEquipmentByTechLevel(getInfantry());
            }
            else if (combo.equals(techType)) {
                if ((techType.getSelectedIndex() == 1) && (!getInfantry().isClan() || getInfantry().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if(!getInfantry().isClan()) {
                        int level = TechConstants.getOppositeTechLevel(getInfantry().getTechLevel());
                        getInfantry().setTechLevel(level);
                        getInfantry().setArmorTechLevel(level);
                    }
                    getInfantry().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 0) && (getInfantry().isClan() || getInfantry().isMixedTech())) {
                    techLevel.removeAllItems();

                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }

                    if(getInfantry().isClan()) {
                        int level = TechConstants.getOppositeTechLevel(getInfantry().getTechLevel());
                        getInfantry().setTechLevel(level);
                        getInfantry().setArmorTechLevel(level);
                    }
                    getInfantry().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 2) && (!getInfantry().isMixedTech() || getInfantry().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getInfantry().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getInfantry().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getInfantry().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (getInfantry().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getInfantry().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getInfantry().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            getInfantry().setTechLevel(TechConstants.T_IS_ADVANCED);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getInfantry().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getInfantry().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getInfantry().getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            getInfantry().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            getInfantry().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        }
                    }
                    getInfantry().setMixedTech(true);
                } else if ((techType.getSelectedIndex() == 3) && (!getInfantry().isMixedTech() || !getInfantry().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getInfantry().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getInfantry().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            getInfantry().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (getInfantry().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getInfantry().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getInfantry().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            getInfantry().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getInfantry().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getInfantry().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getInfantry().getTechLevel() != TechConstants.T_CLAN_TW)) {
                            getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        }
                    }
                    getInfantry().setMixedTech(true);
                } else {
                    addAllActionListeners();
                    return;
                }
                UnitUtil.checkEquipmentByTechLevel(getInfantry());
            }
            else if (combo.equals(motiveType)) {
                switch(motiveType.getSelectedIndex()) {
                case M_JUMP:
                    getInfantry().setMovementMode(EntityMovementMode.INF_JUMP);
                    break;
                case M_MOTOR:
                    getInfantry().setMovementMode(EntityMovementMode.INF_MOTORIZED);
                    break;
                case M_HOVER:
                    getInfantry().setMovementMode(EntityMovementMode.HOVER);
                    break;
                case M_TRACKED:
                    getInfantry().setMovementMode(EntityMovementMode.TRACKED);
                    break;
                case M_WHEELED:
                    getInfantry().setMovementMode(EntityMovementMode.WHEELED);
                    break;
                default:
                    getInfantry().setMovementMode(EntityMovementMode.INF_LEG);
                }
                //first adjust max squad size if necessary
                int currentSquadSize = squadSize.getSelectedIndex()+1;
                int maxSquadSize = getMaxSquadSize();
                squadSize.removeAllItems();
                for (int i = 1; i <= maxSquadSize; i++) {
                    squadSize.addItem(Integer.toString(i));
                }
                if(currentSquadSize > maxSquadSize) {
                    getInfantry().setSquadSize(maxSquadSize);
                    squadSize.setSelectedIndex(maxSquadSize-1);
                }
                //now adjust squad number if necessary
                int currentSquadN = squadN.getSelectedIndex()+1;
                int maxSquadN = getMaxSquadNumber();
                squadN.removeAllItems();
                for (int i = 1; i <= maxSquadN; i++) {
                    squadN.addItem(Integer.toString(i));
                }
                if(currentSquadN > maxSquadN) {
                    getInfantry().setSquadN(maxSquadN);
                    squadN.setSelectedIndex(maxSquadN-1);
                }
                if (getInfantry().isMechanized()) {
                    antiMekTraining.setSelected(false);
                    antiMekTraining.setEnabled(false);
                } else {
                    antiMekTraining.setEnabled(true);
                }
            }
            else if (combo.equals(squadSize)) {
                getInfantry().setSquadSize(squadSize.getSelectedIndex() + 1);
                getInfantry().autoSetInternal();
                int currentSquadN = squadN.getSelectedIndex()+1;
                int maxSquadN = getMaxSquadNumber();
                squadN.removeAllItems();
                for (int i = 1; i <= maxSquadN; i++) {
                    squadN.addItem(Integer.toString(i));
                }
                if(currentSquadN > maxSquadN) {
                    getInfantry().setSquadN(maxSquadN);
                    squadN.setSelectedIndex(maxSquadN-1);
                }
            }
            else if (combo.equals(squadN)) {
                getInfantry().setSquadN(squadN.getSelectedIndex() + 1);
                getInfantry().autoSetInternal();
                int currentSquadSize = squadSize.getSelectedIndex()+1;
                int maxSquadSize = getMaxSquadSize();
                squadSize.removeAllItems();
                for (int i = 1; i <= maxSquadSize; i++) {
                    squadSize.addItem(Integer.toString(i));
                }
                if(currentSquadSize > maxSquadSize) {
                    getInfantry().setSquadSize(maxSquadSize);
                    squadSize.setSelectedIndex(maxSquadSize-1);
                }

            }
            else if (combo.equals(secondaryN)) {
                getInfantry().setSecondaryN(secondaryN.getSelectedIndex());
                checkMainWeapon();
            }
        } else if (e.getSource().equals(antiMekTraining)) {
            getInfantry().setAntiMekSkill(antiMekTraining.isSelected());
        }
        refresh.refreshAll();
    }

	private int getMaxSquadSize() {
        int maxPlatoon = 30;
        int maxSquad = 10;
        switch(motiveType.getSelectedIndex()) {
        case M_HOVER:
            maxPlatoon = 20;
            maxSquad = 5;
            break;
        case M_TRACKED:
            maxPlatoon = 28;
            maxSquad = 7;
            break;
        case M_WHEELED:
            maxPlatoon = 24;
            maxSquad = 6;
            break;
        }
        int currentSquadN = squadN.getSelectedIndex()+1;
        if((maxPlatoon / currentSquadN) < maxSquad) {
            maxSquad = (maxPlatoon / currentSquadN);
        }
        return maxSquad;
    }

    private int getMaxSquadNumber() {
        int maxPlatoon = 30;
        switch(motiveType.getSelectedIndex()) {
        case M_HOVER:
            maxPlatoon = 20;
            break;
        case M_TRACKED:
            maxPlatoon = 28;
            break;
        case M_WHEELED:
            maxPlatoon = 24;
            break;
        }
        int maxSquadN = maxPlatoon;
        int currentSquadSize = squadSize.getSelectedIndex()+1;
        if((maxPlatoon / currentSquadSize) < maxSquadN) {
            maxSquadN = (maxPlatoon / currentSquadSize);
        }
        return maxSquadN;
    }

    private void checkMainWeapon() {
        Mounted existingInfantryMount = null;
        for(Mounted m : getInfantry().getWeaponList()) {
            if((m.getType() instanceof InfantryWeapon) && (m.getLocation() == Infantry.LOC_INFANTRY)) {
                existingInfantryMount = m;
                break;
            }
        }
        if(null != existingInfantryMount) {
            UnitUtil.removeMounted(getInfantry(), existingInfantryMount);
        }

        //if there is more than one secondary weapon per squad, then add that
        // to the unit
        // otherwise add the primary weapon
        if ((getInfantry().getSecondaryN() < 2) || (null == getInfantry().getSecondaryWeapon())) {
            try {
                getInfantry().addEquipment(getInfantry().getPrimaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

            }
        } else {
            try {
                getInfantry().addEquipment(getInfantry().getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

            }
        }
        //also check for zero secondary n
        if(getInfantry().getSecondaryN() <= 0) {
            UnitUtil.replaceMainWeapon(getInfantry(), null, true);
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                getInfantry().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getInfantry().setYear(3145);
            }
        } else if (e.getSource().equals(source)) {
            getInfantry().setSource(source.getText());
        } else if (e.getSource().equals(chassis)) {
            getInfantry().setChassis(chassis.getText().trim());
            refresh.refreshPreview();
        } else if (e.getSource().equals(model)) {
            getInfantry().setModel(model.getText().trim());
            refresh.refreshPreview();
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    public void setAsCustomization() {
        chassis.setEditable(false);
        chassis.setEnabled(false);
        era.setEditable(false);
        era.setEnabled(false);
    }

}