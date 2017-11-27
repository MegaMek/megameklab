/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import megamek.common.Aero;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Mech;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.BuildListener;

/**
 * Displays a list of comboboxes with labels that displays the current armor type per location for
 * patchwork armor and allows it to be changed.
 * 
 * @author Neoancient
 *
 */
public class PatchworkArmorView extends BuildView implements ActionListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -16930846399307224L;

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BuildListener l) {
        listeners.add(l);
    }
    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }
    
    private final static int MAX_LOC = 10;
    
    private final List<JLabel> labels = new ArrayList<>();
    private final List<TechComboBox<EquipmentType>> combos = new ArrayList<>();
    
    private final ITechManager techManager;
    private boolean ignoreEvents = false;
    
    public PatchworkArmorView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        setLayout(new GridBagLayout());
        
        setBorder(BorderFactory.createTitledBorder(
                null, resourceMap.getString("ArmorAllocationView.panPatwork.title"), //$NON-NLS-1$
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        for (int loc = 0; loc < MAX_LOC; loc++) {
            JLabel label = new JLabel();
            TechComboBox<EquipmentType> combo = new TechComboBox<>(eq -> eq.getName());
            combo.setActionCommand(Integer.toString(loc));
            combo.addActionListener(this);
            labels.add(label);
            combos.add(combo);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(label, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(combo, gbc);
            gbc.gridy++;
        }
    }
    
    public void setFromEntity(Entity en) {
        List<EquipmentType> armors = TestEntity.legalArmorsFor(en.getEntityType(),
                (en instanceof Mech) && ((Mech)en).isIndustrial(),
                en.getMovementMode(), techManager);
        ignoreEvents = true;
        for (int loc = 0; loc < combos.size(); loc++) {
            if ((loc < en.locations())
                    && !((en.hasETypeFlag(Entity.ETYPE_TANK) && (loc == Tank.LOC_BODY)))
                    && !((en.hasETypeFlag(Entity.ETYPE_AERO) && (loc == Aero.LOC_WINGS)))) {
                labels.get(loc).setText(en.getLocationName(loc));
                combos.get(loc).removeAllItems();
                for (EquipmentType eq : armors) {
                    combos.get(loc).addItem(eq);
                }
                String name = EquipmentType.getArmorTypeName(en.getArmorType(loc),
                        TechConstants.isClan(en.getArmorTechLevel(loc)));
                combos.get(loc).setSelectedItem(EquipmentType.get(name));
                labels.get(loc).setVisible(true);
                combos.get(loc).setVisible(true);
            } else {
                labels.get(loc).setVisible(false);
                combos.get(loc).setVisible(false);
            }
        }
        ignoreEvents = false;
    }
    
    public EquipmentType getArmor(int location) {
        return (EquipmentType)combos.get(location).getSelectedItem();
    }
    
    public void setArmorType(EquipmentType armor, int location) {
        combos.get(location).setSelectedItem(armor);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ignoreEvents) {
            final int location = Integer.parseInt(e.getActionCommand());
            listeners.forEach(l -> l.patchworkChanged(location, getArmor(location)));
        }
    }

}
