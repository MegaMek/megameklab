/*
 * Copyright (C) 2017-2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.battleArmor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JCheckBox;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.BABuildListener;

/**
 * Panel for BA movement enhancements
 *
 * @author Neoancient
 */
public class BAEnhancementView extends BuildView implements ActionListener {
    private final List<BABuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(BABuildListener l) {
        listeners.add(l);
    }

    public void removeListener(BABuildListener l) {
        listeners.remove(l);
    }

    private final JCheckBox chkPartialWing = new JCheckBox();
    private final JCheckBox chkJumpBooster = new JCheckBox();
    private final JCheckBox chkMechanicalJumpBooster = new JCheckBox();
    private final JCheckBox chkMyomerBooster = new JCheckBox();
    private final JCheckBox chkDNICockpitMod = new JCheckBox();
    private final JCheckBox chkEIInterface = new JCheckBox();

    private boolean ignoreEvents = false;

    private final EquipmentType partialWing = EquipmentType.get(EquipmentTypeLookup.BA_PARTIAL_WING);
    private final EquipmentType jumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_JUMP_BOOSTER);
    private final EquipmentType mechJumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_MECHANICAL_JUMP_BOOSTER);
    private final EquipmentType myomerBooster = EquipmentType.get(EquipmentTypeLookup.BA_MYOMER_BOOSTER);
    private final EquipmentType dniCockpitMod = EquipmentType.get(EquipmentTypeLookup.DNI_COCKPIT_MOD);
    private final EquipmentType eiInterface = EquipmentType.get(EquipmentTypeLookup.EI_INTERFACE);

    private final ITechManager techManager;

    public BAEnhancementView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        chkPartialWing.setText(resourceMap.getString("BAEnhancementView.chkPartialWing.text"));
        chkPartialWing.setToolTipText(resourceMap.getString("BAEnhancementView.chkPartialWing.tooltip"));
        chkJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkJumpBooster.text"));
        chkJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkJumpBooster.tooltip"));
        chkMechanicalJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.text"));
        chkMechanicalJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.tooltip"));
        chkMyomerBooster.setText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.text"));
        chkMyomerBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.tooltip"));
        chkDNICockpitMod.setText(resourceMap.getString("BAEnhancementView.chkDNICockpitMod.text"));
        chkDNICockpitMod.setToolTipText(resourceMap.getString("BAEnhancementView.chkDNICockpitMod.tooltip"));
        chkEIInterface.setText(resourceMap.getString("BAEnhancementView.chkEIInterface.text"));
        chkEIInterface.setToolTipText(resourceMap.getString("BAEnhancementView.chkEIInterface.tooltip"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = STANDARD_INSETS;
        // a little padding; these checkboxes are awfully tight otherwise
        gbc.ipadx = 3;
        gbc.ipady = 3;

        add(chkPartialWing, gbc);
        add(chkJumpBooster, gbc);
        add(chkMechanicalJumpBooster, gbc);
        add(chkMyomerBooster, gbc);
        add(chkDNICockpitMod, gbc);
        add(chkEIInterface, gbc);

        chkPartialWing.addActionListener(this);
        chkDNICockpitMod.addActionListener(this);
        chkMechanicalJumpBooster.addActionListener(this);
        chkJumpBooster.addActionListener(this);
        chkMyomerBooster.addActionListener(this);
        chkEIInterface.addActionListener(this);
    }

    public void setFromEntity(BattleArmor ba) {
        try {
            ignoreEvents = true;
            chkPartialWing.setSelected(ba.hasMisc(EquipmentTypeLookup.BA_PARTIAL_WING));
            chkJumpBooster.setSelected(ba.hasMisc(EquipmentTypeLookup.BA_JUMP_BOOSTER));
            chkMechanicalJumpBooster.setSelected(ba.hasMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER));
            chkMyomerBooster.setSelected(ba.hasMisc(MiscType.F_MASC));

            // DNI Cockpit Mod - IS only
            boolean dniLegal = (dniCockpitMod != null) && techManager.isLegal(dniCockpitMod);
            chkDNICockpitMod.setEnabled(dniLegal);
            chkDNICockpitMod.setSelected(dniLegal && ba.hasMisc(EquipmentTypeLookup.DNI_COCKPIT_MOD));

            // EI Interface - Clan only
            boolean eiLegal = (eiInterface != null) && techManager.isLegal(eiInterface);
            chkEIInterface.setEnabled(eiLegal);
            chkEIInterface.setSelected(eiLegal && ba.hasMisc(EquipmentTypeLookup.EI_INTERFACE));
        } finally {
            ignoreEvents = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ignoreEvents) {
            return;
        }
        if (e.getSource() == chkPartialWing) {
            listeners.forEach(l -> l.enhancementChanged(partialWing, chkPartialWing.isSelected()));
        } else if (e.getSource() == chkJumpBooster) {
            listeners.forEach(l -> l.enhancementChanged(jumpBooster, chkJumpBooster.isSelected()));
        } else if (e.getSource() == chkMechanicalJumpBooster) {
            listeners.forEach(l -> l.enhancementChanged(mechJumpBooster, chkMechanicalJumpBooster.isSelected()));
        } else if (e.getSource() == chkMyomerBooster) {
            listeners.forEach(l -> l.enhancementChanged(myomerBooster, chkMyomerBooster.isSelected()));
        } else if (e.getSource() == chkDNICockpitMod) {
            listeners.forEach(l -> l.enhancementChanged(dniCockpitMod, chkDNICockpitMod.isSelected()));
        } else if (e.getSource() == chkEIInterface) {
            listeners.forEach(l -> l.enhancementChanged(eiInterface, chkEIInterface.isSelected()));
        }
    }
}
