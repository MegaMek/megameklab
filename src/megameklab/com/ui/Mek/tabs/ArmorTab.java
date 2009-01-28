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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
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
    private JComboBox armorCombo = new JComboBox(EquipmentType.armorNames);


    private JButton allocateArmorButton = new JButton("Allocate");
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JSpinner armorTonnage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(Mech unit) {

        this.unit = unit;
        armor = new ArmorView(this.unit);

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
        setTotalTonnage();
        addAllListeners();
        ((SpinnerNumberModel)armorTonnage.getModel()).setMaximum(UnitUtil.getTotalArmorTonnage(unit));
        armor.updateMech(unit);
        armor.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource() instanceof JComboBox) {
            unit.setArmorType(armorCombo.getSelectedIndex());
            removeArmorMounts();
            createArmorMounts();
            if (refresh != null) {
                refresh.refreshAll();
            }
        }
        addAllListeners();
    }

    public JPanel ButtonPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());

        masterPanel.add(new JLabel("Armor", SwingConstants.TRAILING));
        masterPanel.add(armorCombo);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        buttonPanel.add(masterPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        allocateArmorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                armor.allocateArmor((Double)armorTonnage.getValue());
            }
        });

        maximizeArmorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                maximizeArmor();
            }
        });

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
        double maxArmor = UnitUtil.getTotalArmorTonnage(unit);
        armor.allocateArmor(maxArmor);
        armorTonnage.setValue(maxArmor);
    }

    private void createArmorMounts() {
        int armorCount = 0;

        armorCount = EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType())).getCriticals(unit);

        if (armorCount < 1) {
            return;
        }

        for (; armorCount > 0; armorCount--) {
            try {
                unit.addEquipment(new Mounted(unit, EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType()))), Entity.LOC_NONE, false);
            } catch (Exception ex) {
            }
        }
    }

    private void removeArmorMounts() {

        removeArmorCrits();

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (UnitUtil.isArmor(mount.getType())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }

        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if (UnitUtil.isArmor(mount.getType())) {
                unit.getMisc().remove(pos);
            } else {
                pos++;
            }
        }

    }

    private void removeArmorCrits() {

        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if ((mount != null) && UnitUtil.isArmor(mount.getType())) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

    private void setTotalTonnage() {
        double currentTonnage = unit.getArmorWeight();
        armorTonnage.setValue(currentTonnage);
        armorTonnage.setToolTipText("Max Tonnage: " + UnitUtil.getTotalArmorTonnage(unit));
    }

    public void setArmorType(int type) {
        removeAllListeners();
        armorCombo.setSelectedIndex(type);
        addAllListeners();
    }
}