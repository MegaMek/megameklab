/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.battleArmor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JCheckBox;

import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.EquipmentTypeLookup;
import megamek.common.ITechManager;
import megamek.common.MiscType;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.BABuildListener;

/**
 * Structure tab panel for BA movement enhancements
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
    private final JCheckBox chkMechJumpBooster = new JCheckBox();
    private final JCheckBox chkMyomerBooster = new JCheckBox();

    private boolean ignoreEvents = false;

    private final EquipmentType partialWing = EquipmentType.get(EquipmentTypeLookup.BA_PARTIAL_WING);
    private final EquipmentType jumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_JUMP_BOOSTER);
    private final EquipmentType mechJumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_MECHANICAL_JUMP_BOOSTER);
    private final EquipmentType myomerBooster = EquipmentType.get(EquipmentTypeLookup.BA_MYOMER_BOOSTER);

    public BAEnhancementView() {
        initUI();
    }

    /**
     * @deprecated Use {@link #BAEnhancementView()} instead.
     */
    @Deprecated(since = "0.50.06")
    public BAEnhancementView(ITechManager techManager) {
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        chkPartialWing.setText(resourceMap.getString("BAEnhancementView.chkPartialWing.text"));
        chkPartialWing.setToolTipText(resourceMap.getString("BAEnhancementView.chkPartialWing.tooltip"));
        add(chkPartialWing, gbc);
        chkPartialWing.addActionListener(this);

        gbc.gridx++;
        chkJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkJumpBooster.text"));
        chkJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkJumpBooster.tooltip"));
        add(chkJumpBooster, gbc);
        chkJumpBooster.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        chkMechJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.text"));
        chkMechJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.tooltip"));
        add(chkMechJumpBooster, gbc);
        chkMechJumpBooster.addActionListener(this);

        gbc.gridx++;
        chkMyomerBooster.setText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.text"));
        chkMyomerBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.tooltip"));
        add(chkMyomerBooster, gbc);
        chkMyomerBooster.addActionListener(this);
    }

    public void setFromEntity(BattleArmor ba) {
        ignoreEvents = true;
        chkPartialWing.setSelected(ba.hasWorkingMisc(MiscType.F_PARTIAL_WING));
        chkJumpBooster.setSelected(ba.hasWorkingMisc(MiscType.F_JUMP_BOOSTER));
        chkMechJumpBooster.setSelected(ba.hasWorkingMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER));
        chkMyomerBooster.setSelected(ba.hasWorkingMisc(MiscType.F_MASC));
        ignoreEvents = false;
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
        } else if (e.getSource() == chkMechJumpBooster) {
            listeners.forEach(l -> l.enhancementChanged(mechJumpBooster, chkMechJumpBooster.isSelected()));
        } else if (e.getSource() == chkMyomerBooster) {
            listeners.forEach(l -> l.enhancementChanged(myomerBooster, chkMyomerBooster.isSelected()));
        }
    }
}
