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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.TechComboBox;

/**
 * Structure table armor panel for units that allocate armor by point instead of ton.
 *
 * @author Neoancient
 */
public class BAProtoArmorView extends BuildView implements ActionListener, ChangeListener {
    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }

    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }

    private final static String CMD_MAXIMIZE = "MAXIMIZE";
    private final static String CMD_REMAINING = "REMAINING";

    private final TechComboBox<ArmorType> cbArmorType = new TechComboBox<>(EquipmentType::getName);
    private final SpinnerNumberModel spnArmorPointsModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnArmorPoints = new JSpinner(spnArmorPointsModel);
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();

    private final ITechManager techManager;
    private long etype;

    public BAProtoArmorView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblArmorType", "ArmorView.cbArmorType.text",
              "ArmorView.cbArmorType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip"));
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblArmorPoints", "ArmorView.spnArmorPoints.text",
              "ArmorView.spnArmorPoints.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        spnArmorPoints.setToolTipText(resourceMap.getString("ArmorView.spnArmorPoints.tooltip"));
        add(spnArmorPoints, gbc);
        spnArmorPoints.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text"));
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip"));
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text"));
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip"));
        add(btnUseRemaining, gbc);
        btnUseRemaining.addActionListener(this);
    }

    public void setFromEntity(Entity en) {
        etype = en.getEntityType();
        refresh();
        cbArmorType.removeActionListener(this);
        spnArmorPoints.removeChangeListener(this);
        cbArmorType.setSelectedItem(ArmorType.forEntity(en));
        if (en.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            spnArmorPointsModel.setValue(Math.min(((BattleArmor) en).getMaximumArmorPoints(),
                  en.getOArmor(BattleArmor.LOC_TROOPER_1)));
            spnArmorPointsModel.setMaximum(((BattleArmor) en).getMaximumArmorPoints());
        } else if (en.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
            final int max = TestProtoMek.maxArmorFactor((ProtoMek) en);
            spnArmorPointsModel.setValue(Math.min(max,
                  (int) TestEntity.getRawArmorPoints(en, en.getLabArmorTonnage())));
            spnArmorPointsModel.setMaximum(max);
        } else {
            spnArmorPointsModel.setValue(en.getTotalOArmor());
        }

        cbArmorType.addActionListener(this);
        spnArmorPoints.addChangeListener(this);
        refresh();
    }

    public @Nullable ArmorType getArmor() {
        return (ArmorType) cbArmorType.getSelectedItem();
    }

    public int getArmorPoints() {
        return spnArmorPointsModel.getNumber().intValue();
    }

    public void refresh() {
        EquipmentType prev = (EquipmentType) cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();

        MiscTypeFlag flag = MiscTypeFlag.values()[0];
        if ((etype & Entity.ETYPE_BATTLEARMOR) != 0) {
            flag = MiscType.F_BA_EQUIPMENT;
        } else if ((etype & Entity.ETYPE_PROTOMEK) != 0) {
            flag = MiscType.F_PROTOMEK_EQUIPMENT;
        }
        for (ArmorType armor : ArmorType.allArmorTypes()) {
            if (armor.hasFlag(flag) && techManager.isLegal(armor)) {
                cbArmorType.addItem(armor);
            }
        }
        if (cbArmorType.getItemCount() > 0) {
            spnArmorPoints.setEnabled(true);
        } else {
            cbArmorType.addItem(ArmorType.of(EquipmentType.T_ARMOR_UNKNOWN, false));
            spnArmorPoints.setValue(0);
            spnArmorPoints.setEnabled(false);
        }

        cbArmorType.setSelectedItem(prev);
        cbArmorType.addActionListener(this);
        if ((cbArmorType.getSelectedIndex() < 0)
              && (cbArmorType.getModel().getSize() > 0)) {
            cbArmorType.setSelectedIndex(0);
        }
        cbArmorType.showTechBase(techManager.useMixedTech());
    }

    @Override
    public void stateChanged(ChangeEvent evt) {
        if (evt.getSource() == spnArmorPoints) {
            listeners.forEach(l -> l.armorFactorChanged(spnArmorPointsModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == cbArmorType) {
            listeners.forEach(l -> l.armorTypeChanged((ArmorType) cbArmorType.getSelectedItem()));
        } else if (CMD_MAXIMIZE.equals(evt.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::maximizeArmor);
        } else if (CMD_REMAINING.equals(evt.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::useRemainingTonnageArmor);
        }
    }
}
