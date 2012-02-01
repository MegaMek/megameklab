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

package megameklab.com.ui.Mek.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
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

import megamek.client.ui.GBC;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.EquipmentType;
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
        { EquipmentType.armorNames[EquipmentType.T_ARMOR_STANDARD],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_LIGHT_FERRO],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_FERRO],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_STEALTH],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_COMMERCIAL],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_INDUSTRIAL],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_LAMELLOR],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_HARDENED],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_REACTIVE],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_REFLECTIVE],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_PRIMITIVE],
          EquipmentType.armorNames[EquipmentType.T_ARMOR_PATCHWORK]};
    private JComboBox armorCombo = new JComboBox(armorNames);

    private JButton allocateArmorButton = new JButton("Allocate");
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JSpinner armorTonnage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));
    private JCheckBox clanArmor = new JCheckBox("Clan Armor");

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(Mech unit) {

        armorCombo.setName("armorcombo");
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
        clanArmor.setSelected(unit.isClanArmor(0));
        createSystemList();
        setTotalTonnage();
        addAllListeners();
        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil.getMaximumArmorTonnage(unit));
        if (!unit.hasPatchworkArmor()) {
            setArmorType(unit.getArmorType(0));
        }
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
                    unit.setArmorTechLevel(TechConstants.getOppositeTechLevel(unit.getTechLevel()));
                }
            } else if (unit.isClan()) {
                unit.setArmorTechLevel(TechConstants.getOppositeTechLevel(unit.getTechLevel()));
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
        if (getArmorType(armorCombo) == EquipmentType.T_ARMOR_PATCHWORK) {
            JComboBox headArmor = new JComboBox();
            headArmor.setName("head");
            JComboBox laArmor = new JComboBox();
            laArmor.setName("la");
            JComboBox ltArmor = new JComboBox();
            ltArmor.setName("lt");
            JComboBox ctArmor = new JComboBox();
            ctArmor.setName("ct");
            JComboBox rtArmor = new JComboBox();
            rtArmor.setName("rt");
            JComboBox raArmor = new JComboBox();
            raArmor.setName("ra");
            JComboBox llArmor = new JComboBox();
            llArmor.setName("ll");
            JComboBox rlArmor = new JComboBox();
            rlArmor.setName("rl");
            for (int index = 0; index < (armorNames.length-1); index++) {
                headArmor.addItem(armorNames[index]);
                laArmor.addItem(armorNames[index]);
                ltArmor.addItem(armorNames[index]);
                ctArmor.addItem(armorNames[index]);
                rtArmor.addItem(armorNames[index]);
                raArmor.addItem(armorNames[index]);
                llArmor.addItem(armorNames[index]);
                rlArmor.addItem(armorNames[index]);
            }
            setArmorType(headArmor, unit.getArmorType(Mech.LOC_HEAD), false);
            setArmorType(laArmor, unit.getArmorType(Mech.LOC_LARM), false);
            setArmorType(ltArmor, unit.getArmorType(Mech.LOC_LT), false);
            setArmorType(ctArmor, unit.getArmorType(Mech.LOC_CT), false);
            setArmorType(rtArmor, unit.getArmorType(Mech.LOC_RT), false);
            setArmorType(raArmor, unit.getArmorType(Mech.LOC_RARM), false);
            setArmorType(llArmor, unit.getArmorType(Mech.LOC_LLEG), false);
            setArmorType(rlArmor, unit.getArmorType(Mech.LOC_RLEG), false);
            JCheckBox headClan = new JCheckBox("clan", unit.isClan());
            JCheckBox laClan = new JCheckBox("clan", unit.isClan());
            JCheckBox ltClan = new JCheckBox("clan", unit.isClan());
            JCheckBox ctClan = new JCheckBox("clan", unit.isClan());
            JCheckBox rtClan = new JCheckBox("clan", unit.isClan());
            JCheckBox raClan = new JCheckBox("clan", unit.isClan());
            JCheckBox llClan = new JCheckBox("clan", unit.isClan());
            JCheckBox rlClan = new JCheckBox("clan", unit.isClan());
            JLabel headLabel = new JLabel("Head:");
            JLabel laLabel = new JLabel(unit instanceof BipedMech?"Left Arm:":"Front Left Leg");
            JLabel ltLabel = new JLabel("Left Torso:");
            JLabel ctLabel = new JLabel("Center Torso:");
            JLabel rtLabel = new JLabel("Right Torso:");
            JLabel raLabel = new JLabel(unit instanceof BipedMech?"Right Arm:":"Front Right Leg");
            JLabel llLabel = new JLabel(unit instanceof BipedMech?"Left Leg:":"Rear Left Leg");
            JLabel rlLabel = new JLabel(unit instanceof BipedMech?"Right Leg:":"Rear Right Leg");
            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(headLabel, GBC.std());
            panel.add(headClan, GBC.std());
            panel.add(headArmor, GBC.eol());
            panel.add(laLabel, GBC.std());
            panel.add(laClan, GBC.std());
            panel.add(laArmor, GBC.eol());
            panel.add(ltLabel, GBC.std());
            panel.add(ltClan, GBC.std());
            panel.add(ltArmor, GBC.eol());
            panel.add(ctLabel, GBC.std());
            panel.add(ctClan, GBC.std());
            panel.add(ctArmor, GBC.eol());
            panel.add(rtLabel, GBC.std());
            panel.add(rtClan, GBC.std());
            panel.add(rtArmor, GBC.eol());
            panel.add(raLabel, GBC.std());
            panel.add(raClan, GBC.std());
            panel.add(raArmor, GBC.eol());
            panel.add(llLabel, GBC.std());
            panel.add(llClan, GBC.std());
            panel.add(llArmor, GBC.eol());
            panel.add(rlLabel, GBC.std());
            panel.add(rlClan, GBC.std());
            panel.add(rlArmor, GBC.eol());
            if (!unit.isMixedTech()) {
                headClan.setVisible(false);
                laClan.setVisible(false);
                ltClan.setVisible(false);
                ctClan.setVisible(false);
                rtClan.setVisible(false);
                raClan.setVisible(false);
                llClan.setVisible(false);
                rlClan.setVisible(false);
            }
            JOptionPane.showMessageDialog(this, panel, "Please choose the armor types", JOptionPane.QUESTION_MESSAGE);
            unit.setArmorType(getArmorType(headArmor), Mech.LOC_HEAD);
            unit.setArmorType(getArmorType(laArmor), Mech.LOC_LARM);
            unit.setArmorType(getArmorType(ltArmor), Mech.LOC_LT);
            unit.setArmorType(getArmorType(ctArmor), Mech.LOC_CT);
            unit.setArmorType(getArmorType(rtArmor), Mech.LOC_RT);
            unit.setArmorType(getArmorType(raArmor), Mech.LOC_RARM);
            unit.setArmorType(getArmorType(llArmor), Mech.LOC_LLEG);
            unit.setArmorType(getArmorType(rlArmor), Mech.LOC_RLEG);
            setArmorTechLevel(Mech.LOC_HEAD, headClan.isSelected());
            setArmorTechLevel(Mech.LOC_LARM, laClan.isSelected());
            setArmorTechLevel(Mech.LOC_LT, ltClan.isSelected());
            setArmorTechLevel(Mech.LOC_CT, ctClan.isSelected());
            setArmorTechLevel(Mech.LOC_RT, rtClan.isSelected());
            setArmorTechLevel(Mech.LOC_RARM, raClan.isSelected());
            setArmorTechLevel(Mech.LOC_LLEG, llClan.isSelected());
            setArmorTechLevel(Mech.LOC_RLEG, rlClan.isSelected());
            for (int i = 0; i < unit.locations(); i++) {
                int armorCount = 0;
                switch (unit.getArmorType(i)) {
                    case EquipmentType.T_ARMOR_STANDARD:
                    case EquipmentType.T_ARMOR_HARDENED:
                    case EquipmentType.T_ARMOR_INDUSTRIAL:
                    case EquipmentType.T_ARMOR_COMMERCIAL:
                    case EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL:
                        armorCount = 0;
                        break;
                    case EquipmentType.T_ARMOR_STEALTH:
                    case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                        armorCount = 2;
                        break;
                    case EquipmentType.T_ARMOR_HEAVY_FERRO:
                        armorCount = 3;
                        break;
                    case EquipmentType.T_ARMOR_FERRO_FIBROUS:
                    case EquipmentType.T_ARMOR_REFLECTIVE:
                    case EquipmentType.T_ARMOR_REACTIVE:
                        if (TechConstants.isClan(unit.getArmorTechLevel(i))) {
                            armorCount = 1;
                        } else {
                            armorCount = 2;
                        }
                        break;
                    default:
                        break;
                }
                if (armorCount < 1) {
                    break;
                }
                // auto-place stealth crits
                /*if (getArmorType() == EquipmentType.T_ARMOR_STEALTH) {
                    for (int loc = 0; loc < getMech().locations(); loc++) {
                        if ((loc != Mech.LOC_HEAD) && (loc != Mech.LOC_CT)) {
                            try {
                                getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(loc)))), loc, false);
                                getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(loc)))), loc, false);
                            } catch (LocationFullException lfe) {
                                JOptionPane.showMessageDialog(null, lfe.getMessage(), "Stealth Armor does not fit in location. Resetting to Standard Armor", JOptionPane.INFORMATION_MESSAGE);
                                setArmorType(EquipmentType.T_ARMOR_STANDARD);
                            }
                        }
                    }
                } else {*/
                    for (; armorCount > 0; armorCount--) {
                        try {
                            getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(i)))), Entity.LOC_NONE, false);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                //}
            }
            if (!unit.hasPatchworkArmor()) {
                setArmorType(EquipmentType.T_ARMOR_STANDARD);
            }
        } else {
            unit.setArmorType(getArmorType(armorCombo));
            int armorCount = 0;

            armorCount = EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(0))).getCriticals(unit);

            if (armorCount < 1) {
                return;
            }
            // auto-place stealth crits
            if (getArmorType(armorCombo) == EquipmentType.T_ARMOR_STEALTH) {
                Mounted mount = UnitUtil.createSpreadMounts(getMech(), EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(0))));
                if (mount == null) {
                    JOptionPane.showMessageDialog(null, "Stealth Armor does not fit in location.", "Resetting to Standard Armor", JOptionPane.INFORMATION_MESSAGE);
                    setArmorType(armorCombo, EquipmentType.T_ARMOR_STANDARD, false);
                    unit.setArmorType(getArmorType(armorCombo));
                }
            } else {
                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType(0)))), Entity.LOC_NONE, false);
                    } catch (Exception ex) {
                    }
                }
            }
        }

    }

    private void setTotalTonnage() {
        double currentTonnage = unit.getArmorWeight();
        armorTonnage.setValue(currentTonnage);
        armorTonnage.setToolTipText("Max Tonnage: " + UnitUtil.getMaximumArmorTonnage(unit));
    }

    private void setArmorType(JComboBox combo, int type, boolean removeListeners) {
        if (removeListeners) {
            removeAllListeners();
        }

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (EquipmentType.armorNames[type].equals(armorNames[pos])) {
                combo.setSelectedIndex(pos);
                break;
            }
        }
        if (removeListeners) {
            addAllListeners();
        }

    }

    public void setArmorType(int type) {
        setArmorType(armorCombo, type, true);
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

    private int getArmorType(JComboBox combo) {
        String armorType = combo.getSelectedItem().toString();

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (armorType.equals(EquipmentType.armorNames[pos])) {
                return pos;
            }
        }

        return EquipmentType.T_ARMOR_STANDARD;
    }

    private void setArmorTechLevel(int loc, boolean clan) {
        if (clan) {
            if (unit.isClan()) {
                unit.setArmorTechLevel(unit.getTechLevel(), loc);
            } else {
                unit.setArmorTechLevel(TechConstants.getOppositeTechLevel(unit.getTechLevel()), loc);
            }
        } else if (unit.isClan()) {
            unit.setArmorTechLevel(TechConstants.getOppositeTechLevel(unit.getTechLevel()), loc);
        } else {
            unit.setArmorTechLevel(unit.getTechLevel(), loc);
        }
    }
}