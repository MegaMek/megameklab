/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JCheckBox;

import megamek.common.*;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.view.listeners.BABuildListener;

/**
 * Structure tab panel for BA movement enhancements
 * 
 * @author Neoancient
 *
 */
public class BAEnhancementView extends BuildView implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 6181555446271444880L;

    private List<BABuildListener> listeners = new CopyOnWriteArrayList<>();
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

    private ITechManager techManager;
    private boolean ignoreEvents = false;
    
    private final EquipmentType partialWing = EquipmentType.get(EquipmentTypeLookup.BA_PARTIAL_WING);
    private final EquipmentType jumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_JUMP_BOOSTER);
    private final EquipmentType mechJumpBooster = EquipmentType.get(EquipmentTypeLookup.BA_MECHANICAL_JUMP_BOOSTER);
    private final EquipmentType myomerBooster = EquipmentType.get(EquipmentTypeLookup.BA_MYOMER_BOOSTER);

    public BAEnhancementView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        chkPartialWing.setText(resourceMap.getString("BAEnhancementView.chkPartialWing.text"));
        chkPartialWing.setToolTipText(resourceMap.getString("BAEnhancementView.chkPartialWing.tooltip")); //$NON-NLS-1$
        add(chkPartialWing, gbc);
        chkPartialWing.addActionListener(this);
        
        gbc.gridx++;
        chkJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkJumpBooster.text"));
        chkJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkJumpBooster.tooltip")); //$NON-NLS-1$
        add(chkJumpBooster, gbc);
        chkJumpBooster.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        chkMechJumpBooster.setText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.text"));
        chkMechJumpBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMechJumpBooster.tooltip")); //$NON-NLS-1$
        add(chkMechJumpBooster, gbc);
        chkMechJumpBooster.addActionListener(this);

        gbc.gridx++;
        chkMyomerBooster.setText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.text"));
        chkMyomerBooster.setToolTipText(resourceMap.getString("BAEnhancementView.chkMyomerBooster.tooltip")); //$NON-NLS-1$
        add(chkMyomerBooster, gbc);
        chkMyomerBooster.addActionListener(this);
    }
    
    public void setFromEntity(BattleArmor ba) {
        if ((ba.getMovementMode() == EntityMovementMode.INF_JUMP)
                && (ba.getOriginalJumpMP() > 0)
                && !ba.hasWorkingMisc(MiscType.F_JUMP_BOOSTER)
                && techManager.isLegal(partialWing)) {
            chkPartialWing.setEnabled(true);
            ignoreEvents = true;
            chkPartialWing.setSelected(ba.hasWorkingMisc(MiscType.F_PARTIAL_WING));
            ignoreEvents = false;
        } else {
            chkPartialWing.setEnabled(false);
            chkPartialWing.setSelected(false);
        }
        
        if ((ba.getMovementMode() == EntityMovementMode.INF_JUMP)
                && (ba.getOriginalJumpMP() > 0)
                && !ba.hasWorkingMisc(MiscType.F_PARTIAL_WING)
                && techManager.isLegal(jumpBooster)) {
            chkJumpBooster.setEnabled(true);
            ignoreEvents = true;
            chkJumpBooster.setSelected(ba.hasWorkingMisc(MiscType.F_JUMP_BOOSTER));
            ignoreEvents = false;
        } else {
            chkJumpBooster.setEnabled(false);
            chkJumpBooster.setSelected(false);
        }
        
        if (techManager.isLegal(mechJumpBooster)
                && !ba.hasWorkingMisc(MiscType.F_MASC)) {
            chkMechJumpBooster.setEnabled(true);
            ignoreEvents = true;
            chkMechJumpBooster.setSelected(ba.hasWorkingMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER));
            ignoreEvents = false;
        } else {
            chkMechJumpBooster.setEnabled(false);
            chkMechJumpBooster.setSelected(false);
        }
        
        if (techManager.isLegal(myomerBooster)
                && !ba.hasWorkingMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER)) {
            chkMyomerBooster.setEnabled(true);
            ignoreEvents = true;
            chkMyomerBooster.setSelected(ba.hasWorkingMisc(MiscType.F_MASC));
            ignoreEvents = false;
        } else {
            chkMyomerBooster.setEnabled(false);
            chkMyomerBooster.setSelected(false);
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
        } else if (e.getSource() == chkMechJumpBooster) {
            listeners.forEach(l -> l.enhancementChanged(mechJumpBooster, chkMechJumpBooster.isSelected()));
        } else if (e.getSource() == chkMyomerBooster) {
            listeners.forEach(l -> l.enhancementChanged(myomerBooster, chkMyomerBooster.isSelected()));
        }
    }
}
