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
package megameklab.ui.generalUnit;

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
import megamek.common.ITechManager;
import megamek.common.Mek;
import megamek.common.MiscType;
import megamek.common.Tank;
import megamek.common.equipment.ArmorType;
import megamek.common.verifier.TestEntity;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.TechComboBox;

/**
 * Displays a list of comboboxes with labels that displays the current armor type per location for
 * patchwork armor and allows it to be changed.
 *
 * @author Neoancient
 */
public class PatchworkArmorView extends BuildView implements ActionListener {
    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }
    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }

    private final static int MAX_LOC = 10;

    private final List<JLabel> labels = new ArrayList<>();
    private final List<TechComboBox<ArmorType>> combos = new ArrayList<>();

    private final ITechManager techManager;
    private boolean ignoreEvents = false;

    public PatchworkArmorView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createTitledBorder(
                null, resourceMap.getString("ArmorAllocationView.panPatwork.title"),
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        for (int loc = 0; loc < MAX_LOC; loc++) {
            JLabel label = new JLabel();
            TechComboBox<ArmorType> combo = new TechComboBox<>(ArmorType::getName);
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
        List<ArmorType> armors = TestEntity.legalArmorsFor(en.getEntityType(),
                (en instanceof Mek) && ((Mek)en).isIndustrial(),
                en.isPrimitive(),
                en.getMovementMode(), techManager);
        ignoreEvents = true;
        for (int loc = 0; loc < combos.size(); loc++) {
            if ((loc < en.locations())
                    && !((en.hasETypeFlag(Entity.ETYPE_TANK) && (loc == Tank.LOC_BODY)))
                    && !((en.hasETypeFlag(Entity.ETYPE_AERO) && (loc >= Aero.LOC_WINGS)))) {
                labels.get(loc).setText(en.getLocationName(loc));
                combos.get(loc).removeAllItems();
                for (ArmorType armor : armors) {
                    // Check whether SV armor exists at this tech rating or it requires the armored chassis mod.
                    if (armor.hasFlag(MiscType.F_SUPPORT_VEE_BAR_ARMOR)
                            && ((armor.getSVWeightPerPoint(en.getArmorTechRating()) == 0.0)
                            || (!en.hasMisc(MiscType.F_ARMORED_CHASSIS)
                                && armor.getSVWeightPerPoint(en.getArmorTechRating()) >= 0.050))) {
                        continue;
                    }
                    combos.get(loc).addItem(armor);
                }
                combos.get(loc).setSelectedItem(ArmorType.forEntity(en, loc));
                labels.get(loc).setVisible(true);
                combos.get(loc).setVisible(true);
                combos.get(loc).showTechBase(en.isMixedTech());
            } else {
                labels.get(loc).setVisible(false);
                combos.get(loc).setVisible(false);
            }
        }
        ignoreEvents = false;
    }

    public ArmorType getArmor(int location) {
        return (ArmorType) combos.get(location).getSelectedItem();
    }

    public void setArmorType(ArmorType armor, int location) {
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
