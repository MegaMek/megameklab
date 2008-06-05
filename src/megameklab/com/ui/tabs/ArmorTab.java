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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.util.UnitUtil;
import megameklab.com.ui.views.ArmorView;

import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;

public class ArmorTab extends JPanel implements ActionListener, ChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private Mech unit;
    private ArmorView armor;
    private RefreshListener refresh = null;
    private JComboBox armorCombo = new JComboBox(EquipmentType.armorNames);
    private JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);

    private JButton allocateArmorButton = new JButton("Allocate");
    private JSlider armorSlider = new JSlider();

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(Mech unit) {

        this.unit = unit;
        armor = new ArmorView(this.unit);

        this.setLayout(new SpringLayout());
        this.add(ButtonPanel());
        this.add(armor);
        SpringLayoutHelper.setupSpringGrid(this, 1);

        refresh();

        addAllListeners();

    }

    public void refresh() {
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
            unit.setStructureType(structureCombo.getSelectedIndex());
            removeArmorMounts();
            createArmorMounts();
            refresh.refreshAll();
        }
        addAllListeners();
    }

    public JPanel ButtonPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());

        masterPanel.add(new JLabel("Armor", JLabel.TRAILING));
        masterPanel.add(armorCombo);
        masterPanel.add(new JLabel("Structure", JLabel.TRAILING));
        masterPanel.add(structureCombo);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        buttonPanel.add(masterPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        allocateArmorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                allocateArmorActionPerformed();
            }
        });

        armorSlider.setMinimum(0);
        armorSlider.setMaximum(100);
        armorSlider.setMinorTickSpacing(5);
        armorSlider.setMajorTickSpacing(10);
        armorSlider.setValue(100);
        armorSlider.setPaintTicks(true);
        armorSlider.setPaintLabels(true);
        armorSlider.setPaintTrack(true);
        armorSlider.addChangeListener(this);
        armorSlider.setSnapToTicks(true);

        JPanel sliderPanel = new JPanel(new SpringLayout());
        sliderPanel.add(armorSlider);
        sliderPanel.add(allocateArmorButton);
        SpringLayoutHelper.setupSpringGrid(sliderPanel, 2);

        buttonPanel.add(sliderPanel);

        return buttonPanel;
    }

    private void addAllListeners() {
        armorCombo.addActionListener(this);
        structureCombo.addActionListener(this);
    }

    private void removeAllListeners() {
        armorCombo.removeActionListener(this);
        structureCombo.removeActionListener(this);
    }

    private void allocateArmorActionPerformed() {

        armor.allocateArmor(armorSlider.getValue());

    }

    public void stateChanged(ChangeEvent arg0) {
        armorSlider.setToolTipText(Integer.toString(armorSlider.getValue()));

    }

    private void createArmorMounts() {
        int armorCount = 0;
        int ISCount = 0;
        boolean isClan = unit.isClan();

        switch (unit.getArmorType()) {
        case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            if (isClan) {
                armorCount = 7;
            } else {
                armorCount = 14;
            }
            break;
        case EquipmentType.T_ARMOR_HEAVY_FERRO:
            armorCount = 21;
            break;
        case EquipmentType.T_ARMOR_LIGHT_FERRO:
            armorCount = 7;
        }
        switch (unit.getStructureType()) {
        case EquipmentType.T_STRUCTURE_ENDO_STEEL:
            if (isClan) {
                ISCount = 7;
            } else {
                ISCount = 14;
            }

        }

        if (armorCount < 1 && ISCount < 1) {
            return;
        }

        for ( ; armorCount > 0; armorCount-- ){
            try{
                unit.addEquipment(new Mounted(unit,EquipmentType.get(EquipmentType.getArmorTypeName(unit.getArmorType()))) , Entity.LOC_NONE,false);
            }catch (Exception ex){
                
            }
        }
        
        for ( ; ISCount > 0; ISCount-- ){
            try{
                unit.addEquipment(new Mounted(unit,EquipmentType.get(EquipmentType.getStructureTypeName(unit.getStructureType()))) , Entity.LOC_NONE,false);
            }catch (Exception ex){
                
            }
        }
        
    }

    private void removeArmorMounts() {

        removeArmorCrits();
        
        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (UnitUtil.isArmorOrStructure(mount.getType())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }

        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if (UnitUtil.isArmorOrStructure(mount.getType())) {
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
                if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if (mount != null && UnitUtil.isArmorOrStructure(mount.getType())) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

}