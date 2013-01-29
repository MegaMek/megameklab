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

package megameklab.com.ui.BattleArmor.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class ArmorTab extends ITab implements ActionListener, ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private RefreshListener refresh = null;
    private String[] armorNames = new String[]
        { EquipmentType.armorNames[EquipmentType.T_ARMOR_STANDARD] };
    private JComboBox armorCombo = new JComboBox(armorNames);

    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JSpinner armorPoints = new JSpinner(new SpinnerNumberModel(0, 0, 0, 1));
    private JCheckBox clanArmor = new JCheckBox("Clan Armor");
    private JLabel maxArmorLabel = new JLabel("", SwingConstants.TRAILING);

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(BattleArmor unit) {

        this.unit = unit;

        Dimension comboSize = new Dimension(150, 20);

        armorCombo.setMaximumSize(comboSize);
        armorCombo.setPreferredSize(comboSize);
        armorCombo.setMinimumSize(comboSize);

        setLayout(new SpringLayout());
        this.add(ButtonPanel());
        SpringLayoutHelper.setupSpringGrid(this, 1);
        setTotalPoints();
        addAllListeners();
    }

    public void refresh() {
        removeAllListeners();
        clanArmor.setVisible(unit.isMixedTech());
        clanArmor.setSelected(unit.isClanArmor(0));
        maxArmorLabel.setText(String.format("/ %1$d", UnitUtil.getMaximumArmorPoints(unit)));
        createSystemList();
        setTotalPoints();
        addAllListeners();
        ((SpinnerNumberModel) armorPoints.getModel()).setMaximum(UnitUtil.getMaximumArmorPoints(unit));
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource().equals(maximizeArmorButton)) {
            maximizeArmor();
        } else if (arg0.getSource().equals(clanArmor)) {
            UnitUtil.removeISorArmorMounts(unit, false);
            if (clanArmor.isSelected()) {
                if (unit.isClan()) {
                    unit.setArmorTechLevel(unit.getTechLevel());
                } else {
                    unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                }
            } else if (unit.isClan()) {
                unit.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
            } else {
                unit.setArmorTechLevel(unit.getTechLevel());
            }
        }
        addAllListeners();
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public JPanel ButtonPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());

        masterPanel.add(new JLabel("Armor", SwingConstants.TRAILING));
        masterPanel.add(armorCombo);
        masterPanel.add(clanArmor);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        SpringLayoutHelper.setupSpringGrid(masterPanel, 3);

        buttonPanel.add(masterPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        maximizeArmorButton.addActionListener(this);
        clanArmor.addActionListener(this);

        armorPoints.setToolTipText("Total Points of Armor");
        Dimension size = new Dimension(45, 10);
        armorPoints.setMaximumSize(size);
        armorPoints.setPreferredSize(size);

        JPanel sliderPanel = new JPanel(new SpringLayout());
        sliderPanel.add(new JLabel("Armor Points:", SwingConstants.TRAILING));
        sliderPanel.add(armorPoints);
        sliderPanel.add(maxArmorLabel);
        sliderPanel.add(maximizeArmorButton);
        SpringLayoutHelper.setupSpringGrid(sliderPanel, 4);

        buttonPanel.add(sliderPanel);

        return buttonPanel;
    }

    private void addAllListeners() {
        armorCombo.addActionListener(this);
        armorPoints.addChangeListener(this);
    }

    private void removeAllListeners() {
        armorCombo.removeActionListener(this);
        armorPoints.removeChangeListener(this);
    }

    private void maximizeArmor() {
        armorPoints.setValue(UnitUtil.getMaximumArmorPoints(unit));
    }

    private void setTotalPoints() {
        armorPoints.setValue(unit.getOArmor(BattleArmor.LOC_TROOPER_1));
        armorPoints.setToolTipText("Max Points: " + UnitUtil.getMaximumArmorPoints(unit));
    }

    public void setArmorType(int type) {
        removeAllListeners();

        /*        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
                    if (EquipmentType.armorNames[type].equals(armorNames[pos])) {
                        armorCombo.setSelectedIndex(pos);
                        break;
                    }
                }*/
        armorCombo.setSelectedIndex(0);
        addAllListeners();
    }

    private void createSystemList() {
        int selectedIndex = armorCombo.getSelectedIndex();
        armorCombo.removeAllItems();
        int armorCount = armorNames.length;

        switch (getBattleArmor().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                armorCount = 1;
                break;
        }

        for (int index = 0; index < armorCount; index++) {
            armorCombo.addItem(armorNames[index]);
        }

        if (armorCount <= selectedIndex) {
            armorCombo.setSelectedIndex(0);
        } else {
            armorCombo.setSelectedIndex(selectedIndex);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();

        int value = (Integer) field.getModel().getValue();
        for (int pos = 0; pos < unit.locations(); pos++) {
            unit.initializeArmor(value, pos);
        }
        if (refresh != null) {
            refresh.refreshStatus();
        }
        refresh();
    }
}