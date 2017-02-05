/*
 * MegaMekLab - Copyright (C) 2017 The MegaMek Team
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
package megameklab.com.ui.Infantry.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.weapons.infantry.InfantryTAGWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * View for selecting infantry specializations, including xenoplanetary conditions training (XCT).
 * 
 * @author Neoancient
 *
 */
public class SpecializationView extends IView implements ActionListener {

    private static final long serialVersionUID = -5851020780074510576L;

    private RefreshListener refresh;
    
    private JCheckBox[] checks = new JCheckBox[Infantry.NUM_SPECIALIZATIONS - 1];
    
    private boolean handleEvents;
    
    public SpecializationView(EntitySource eSource) {
        super(eSource);
        
        JPanel specPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 10);
        
        specPanel.add(new JLabel("Specialization"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        specPanel.add(new JLabel("Max Squad Size"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("Max Squads"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("Max Secondary Weapons"), gbc);
        
        for (int i = 0; i < checks.length; i++) {
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            int spec = 1 << i;
            JCheckBox chk = new JCheckBox(Infantry.getSpecializationName(spec));
            chk.setToolTipText(Infantry.getSpecializationTooltip(spec));
            chk.setActionCommand(String.valueOf(spec));
            specPanel.add(chk, gbc);
            checks[i] = chk;
            chk.addActionListener(this);
            
            gbc.gridx++;
            gbc.anchor = GridBagConstraints.CENTER;
            if (spec == Infantry.TAG_TROOPS || spec == Infantry.PARATROOPS) {
                specPanel.add(new JLabel("-"), gbc);
            } else {
                specPanel.add(new JLabel("10"), gbc);
            }
            gbc.gridx++;
            if (spec == Infantry.TAG_TROOPS || spec == Infantry.PARATROOPS) {
                specPanel.add(new JLabel("-"), gbc);
            } else if (spec < Infantry.MARINES || spec == Infantry.MOUNTAIN_TROOPS) {
                specPanel.add(new JLabel("2"), gbc);
            } else if (spec == Infantry.PARATROOPS) {
                specPanel.add(new JLabel("3"), gbc);
            } else {
                specPanel.add(new JLabel("4"), gbc);
            }
            gbc.gridx++;
            if (spec == Infantry.TAG_TROOPS || spec == Infantry.PARATROOPS) {
                specPanel.add(new JLabel("-"), gbc);
            } else if (spec == Infantry.MARINES) {
                specPanel.add(new JLabel("2"), gbc);
            } else if (spec < Infantry.MARINES || spec == Infantry.SCUBA) {
                specPanel.add(new JLabel("0"), gbc);
            } else {
                specPanel.add(new JLabel("1"), gbc);
            }
        }
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(4, 10, 4, 10);
        gbc.anchor = GridBagConstraints.WEST;
        specPanel.add(new JLabel("SCUBA (Foot)"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        specPanel.add(new JLabel("10"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("4"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("0"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        specPanel.add(new JLabel("SCUBA (Motorized)"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        specPanel.add(new JLabel("6"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("2"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("1"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        specPanel.add(new JLabel("SCUBA (Mechanized)"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        specPanel.add(new JLabel("5"), gbc);
        gbc.gridx++;
        specPanel.add(new JLabel("4"), gbc);
        gbc.gridx++;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        specPanel.add(new JLabel("2"), gbc);

        add(new JScrollPane(specPanel));
        handleEvents = true;
    }
    
    public void refresh() {
        handleEvents = false;
        for (int i = 0; i < checks.length; i++) {
            checks[i].setSelected((getInfantry().getSpecializations() & (1 << i)) != 0);
        }
        handleEvents = true;
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!handleEvents) {
            return;
        }
        if (e.getSource() instanceof JCheckBox) {
            int spec = Integer.parseInt(e.getActionCommand());
            if (((JCheckBox)e.getSource()).isSelected()) {
                getInfantry().setSpecializations(getInfantry()
                        .getSpecializations() | spec);
                if (spec == Infantry.TAG_TROOPS) {
                    getInfantry().setSecondaryN(2);
                    UnitUtil.replaceMainWeapon(getInfantry(),
                            (InfantryWeapon)EquipmentType.get("InfantryTAG"), true);
                }
            } else {
                getInfantry().setSpecializations(getInfantry()
                        .getSpecializations() & ~spec);
                if (getInfantry().getSecondaryWeapon() != null
                        && getInfantry().getSecondaryWeapon() instanceof InfantryTAGWeapon) {
                    getInfantry().setSecondaryN(0);
                    UnitUtil.replaceMainWeapon(getInfantry(), null, true);
                }
            }
            if (refresh != null) {
                refresh.refreshStructure();
                refresh.refreshPreview();
            }
        }
    }
    

}
