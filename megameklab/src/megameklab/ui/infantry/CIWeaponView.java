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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import megamek.client.ui.util.DisplayTextField;
import megamek.common.SimpleTechLevel;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.verifier.TestInfantry;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.ui.util.WidthControlComponent;
import megameklab.util.InfantryUtil;

/**
 * Panel for conventional infantry weapons (primary, secondary, field gun). The only editable controls are for changing
 * the number of secondary weapons and field guns.
 *
 * @author Neoancient
 */
public class CIWeaponView extends BuildView implements ActionListener {
    private final List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }

    private final EnumSet<EntityMovementMode> FIELD_GUN_MODES = EnumSet.of(
          EntityMovementMode.TRACKED, EntityMovementMode.WHEELED, EntityMovementMode.INF_MOTORIZED);

    private final DisplayTextField txtPrimary = new DisplayTextField(WidthControlComponent.TEXT_FIELD_COLUMNS);
    private final DisplayTextField txtSecondary = new DisplayTextField(WidthControlComponent.TEXT_FIELD_COLUMNS);
    private final DisplayTextField txtGuns = new DisplayTextField(WidthControlComponent.TEXT_FIELD_COLUMNS);
    private final JComboBox<Integer> cbNumSecondary = new JComboBox<>();
    private final JComboBox<Integer> cbNumGuns = new JComboBox<>();
    private final JCheckBox chkAntiMek = new JCheckBox();

    private final ITechManager techManager;
    private final String fgMotiveMsg;
    private final String noneMsg;

    public CIWeaponView(ITechManager techManager, CIStructureTab structureTab) {
        this.techManager = techManager;
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        fgMotiveMsg = resourceMap.getString("InfantryWeaponView.txtGuns.badMotive");
        noneMsg = resourceMap.getString("InfantryWeaponView.none");

        // Always allow up to 20 field guns; show as invalid when invalid; this is necessary to prevent the
        // number that is shown to be different from the actual field guns that the infantry has
        for (int i = 0; i <= 20; i++) {
            cbNumGuns.addItem(i);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = STANDARD_INSETS;
        gbc.weightx = 1;
        add(new StandardBuildLabel(resourceMap.getString("InfantryWeaponView.txtPrimary.text")), gbc);
        gbc.weightx = 0;
        txtPrimary.setToolTipText(resourceMap.getString("InfantryWeaponView.txtPrimary.tooltip"));
        add(txtPrimary, gbc);
        txtPrimary.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showWeaponChoiceTable();
            }
        });

        gbc.gridy++;
        add(Box.createVerticalStrut(8), gbc);
        add(new WidthControlComponent(), gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("InfantryWeaponView.txtSecondary.text")), gbc);
        txtSecondary.setToolTipText(resourceMap.getString("InfantryWeaponView.txtSecondary.tooltip"));
        add(txtSecondary, gbc);
        txtSecondary.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showWeaponChoiceTable();
            }
        });

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("InfantryWeaponView.cbNumSecondary.text")), gbc);
        cbNumSecondary.setToolTipText(resourceMap.getString("InfantryWeaponView.cbNumSecondary.tooltip"));
        add(cbNumSecondary, gbc);
        cbNumSecondary.addActionListener(this);

        gbc.gridy++;
        add(Box.createVerticalStrut(8), gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("InfantryWeaponView.txtGuns.text")), gbc);
        txtGuns.setToolTipText(resourceMap.getString("InfantryWeaponView.txtGuns.tooltip"));
        add(txtGuns, gbc);
        txtGuns.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showFieldGunChoiceTable();
            }
        });

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("InfantryWeaponView.cbNumGuns.text")), gbc);
        cbNumGuns.setToolTipText(resourceMap.getString("InfantryWeaponView.cbNumGuns.tooltip"));
        add(cbNumGuns, gbc);
        cbNumGuns.addActionListener(this);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 2;
        var antiMekPanel = new JPanel();
        antiMekPanel.add(chkAntiMek);
        chkAntiMek.setText(resourceMap.getString("InfantryWeaponView.chkAntiMek.text"));
        chkAntiMek.setToolTipText(resourceMap.getString("InfantryWeaponView.chkAntiMek.tooltip"));
        chkAntiMek.setHorizontalTextPosition(SwingConstants.LEFT);
        chkAntiMek.addActionListener(this);
        add(antiMekPanel, gbc);
    }

    public void setFromEntity(Infantry inf) {
        if (inf.getPrimaryWeapon() != null) {
            txtPrimary.setText(InfantryUtil.trimInfantryWeaponNames(inf.getPrimaryWeapon().getName()));
        } else {
            txtPrimary.setText(noneMsg);
        }

        if (inf.getSecondaryWeapon() != null) {
            txtSecondary.setText(InfantryUtil.trimInfantryWeaponNames(inf.getSecondaryWeapon().getName()));
        } else {
            txtSecondary.setText(noneMsg);
        }

        cbNumSecondary.removeActionListener(this);
        cbNumSecondary.removeAllItems();
        cbNumSecondary.addItem(0);
        if (inf.hasSpecialization(Infantry.TAG_TROOPS)) {
            cbNumSecondary.addItem(2);
        } else {
            for (int i = 1; i <= TestInfantry.maxSecondaryWeapons(inf); i++) {
                cbNumSecondary.addItem(i);
            }
        }
        cbNumSecondary.setSelectedItem(inf.getSecondaryWeaponsPerSquad());
        if (cbNumSecondary.getSelectedIndex() < 0) {
            cbNumSecondary.setSelectedIndex(0);
        }
        cbNumSecondary.addActionListener(this);

        if (!inf.hasFieldWeapon()) {
            cbNumGuns.setEnabled(false);
            if (!FIELD_GUN_MODES.contains(inf.getMovementMode())) {
                txtGuns.setText(fgMotiveMsg);
            } else {
                txtGuns.setText(noneMsg);
            }
        } else {
            cbNumGuns.setEnabled(true);
            cbNumGuns.removeActionListener(this);
            List<Mounted<?>> fieldGuns = inf.originalFieldWeapons();
            cbNumGuns.setSelectedIndex(fieldGuns.size());
            cbNumGuns.addActionListener(this);
            txtGuns.setText(fieldGuns.get(0).getName());
        }

        if (techManager.getTechLevel().ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtGuns.setEnabled(true);
            cbNumGuns.setEnabled(true);
        } else {
            txtGuns.setEnabled(false);
            cbNumGuns.setEnabled(false);
        }

        chkAntiMek.removeActionListener(this);
        chkAntiMek.setSelected(inf.hasAntiMekGear());
        chkAntiMek.addActionListener(this);
    }

    private int selectedFieldGunCount() {
        return (int) Objects.requireNonNullElse(cbNumGuns.getSelectedItem(), 0);
    }

    private int selectedSecondaryWeaponCount() {
        return (int) Objects.requireNonNullElse(cbNumSecondary.getSelectedItem(), 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbNumSecondary) {
            listeners.forEach(l -> l.numSecondaryChanged(selectedSecondaryWeaponCount()));

        } else if (e.getSource() == cbNumGuns) {
            listeners.forEach(l -> l.numFieldGunsChanged(selectedFieldGunCount()));

        } else if (e.getSource() == chkAntiMek) {
            listeners.forEach(l -> l.antiMekChanged(chkAntiMek.isSelected()));
        }
    }
}
