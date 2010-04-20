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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megameklab.com.ui.Mek.views.ArmorView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class ArmorTab extends ITab implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private ArmorView armor;
    private RefreshListener refresh = null;
    private String[] armorNames = new String[]
        { EquipmentType.armorNames[EquipmentType.T_ARMOR_STANDARD], EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS], EquipmentType.armorNames[EquipmentType.T_ARMOR_LIGHT_FERRO], EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_FERRO], EquipmentType.armorNames[EquipmentType.T_ARMOR_STEALTH], EquipmentType.armorNames[EquipmentType.T_ARMOR_COMMERCIAL], EquipmentType.armorNames[EquipmentType.T_ARMOR_INDUSTRIAL], EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL], EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_CARBIDE], EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO], EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_IMP], EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_LAMELLOR],
                EquipmentType.armorNames[EquipmentType.T_ARMOR_HARDENED], EquipmentType.armorNames[EquipmentType.T_ARMOR_REACTIVE], EquipmentType.armorNames[EquipmentType.T_ARMOR_REFLECTIVE], EquipmentType.armorNames[EquipmentType.T_ARMOR_PATCHWORK] };
    private JComboBox armorCombo = new JComboBox(armorNames);

    private JButton allocateArmorButton = new JButton("Allocate");
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JSpinner armorTonnage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));
    private JCheckBox clanArmor = new JCheckBox("Clan Armor");

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(Mech unit) {

        this.unit = unit;
        armor = new ArmorView(getMech());

        Dimension comboSize = new Dimension(150, 20);

        armorCombo.setMaximumSize(comboSize);
        armorCombo.setPreferredSize(comboSize);
        armorCombo.setMinimumSize(comboSize);

        setLayout(new SpringLayout());
        this.add(ButtonPanel());
        this.add(armor);
        SpringLayoutHelper.setupSpringGrid(this, 1);
        setTotalTonnage();
        addAllListeners();
    }

    public void refresh() {
        removeAllListeners();
        clanArmor.setVisible(unit.isMixedTech());
        clanArmor.setSelected(unit.isClanArmor());
        createSystemList();
        setTotalTonnage();
        addAllListeners();
        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil.getMaximumArmorTonnage(unit));
        armor.updateUnit(unit);
        armor.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource() instanceof JComboBox) {
            UnitUtil.removeISorArmorMounts(unit, false);
            createArmorMountsAndSetArmorType();
        }
        if (arg0.getSource().equals(allocateArmorButton)) {
            armor.allocateArmor((Double) armorTonnage.getValue());
        }
        if (arg0.getSource().equals(maximizeArmorButton)) {
            maximizeArmor();
        }
        if (arg0.getSource().equals(clanArmor)) {
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
            createArmorMountsAndSetArmorType();
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

        allocateArmorButton.addActionListener(this);

        maximizeArmorButton.addActionListener(this);
        clanArmor.addActionListener(this);

        armorTonnage.setToolTipText("Total Tonnage of Armor");
        Dimension size = new Dimension(45, 10);
        armorTonnage.setMaximumSize(size);
        armorTonnage.setPreferredSize(size);

        JPanel sliderPanel = new JPanel(new SpringLayout());
        sliderPanel.add(new JLabel("Armor Tonnage:", SwingConstants.TRAILING));
        sliderPanel.add(armorTonnage);
        sliderPanel.add(allocateArmorButton);
        sliderPanel.add(maximizeArmorButton);
        SpringLayoutHelper.setupSpringGrid(sliderPanel, 4);

        buttonPanel.add(sliderPanel);

        return buttonPanel;
    }

    private void addAllListeners() {
        armorCombo.addActionListener(this);
    }

    private void removeAllListeners() {
        armorCombo.removeActionListener(this);
    }

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(unit);
        armor.allocateArmor(maxArmor);
        armorTonnage.setValue(maxArmor);
    }

    private void createArmorMountsAndSetArmorType() {
        unit.setArmorType(getArmorType());
        int armorCount = 0;

        armorCount = EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType())).getCriticals(unit);

        if (armorCount < 1) {
            return;
        }
        // auto-place stealth crits
        if (getArmorType() == EquipmentType.T_ARMOR_STEALTH) {
            for (int loc = 0; loc < getMech().locations(); loc++) {
                if ((loc != Mech.LOC_HEAD) && (loc != Mech.LOC_CT)) {
                    try {
                        getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType()))), loc, false);
                        getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType()))), loc, false);
                    } catch (LocationFullException lfe) {
                        JOptionPane.showMessageDialog(null, lfe.getMessage(), "Stealth Armor does not fit in location. Resetting to Standard Armor", JOptionPane.INFORMATION_MESSAGE);
                        setArmorType(EquipmentType.T_ARMOR_STANDARD);
                    }
                }
            }
        } else {
            for (; armorCount > 0; armorCount--) {
                try {
                    getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType()))), Entity.LOC_NONE, false);
                } catch (Exception ex) {
                }
            }
        }
    }

    private void setTotalTonnage() {
        double currentTonnage = unit.getArmorWeight();
        armorTonnage.setValue(currentTonnage);
        armorTonnage.setToolTipText("Max Tonnage: " + UnitUtil.getMaximumArmorTonnage(unit));
    }

    public void setArmorType(int type) {
        removeAllListeners();

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (EquipmentType.armorNames[type].equals(armorNames[pos])) {
                armorCombo.setSelectedIndex(pos);
                break;
            }
        }
        addAllListeners();
    }

    private void createSystemList() {
        int selectedIndex = armorCombo.getSelectedIndex();
        armorCombo.removeAllItems();
        int armorCount = armorNames.length;

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                armorCount = 1;
                break;
            case TechConstants.T_CLAN_TW:
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_CLAN_ADVANCED:
            case TechConstants.T_IS_ADVANCED:
                armorCount = 8;
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
            case TechConstants.T_IS_UNOFFICIAL:
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

    private int getArmorType() {
        String armorType = armorCombo.getSelectedItem().toString();

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (armorType.equals(EquipmentType.armorNames[pos])) {
                return pos;
            }
        }

        return EquipmentType.T_ARMOR_STANDARD;
    }
}