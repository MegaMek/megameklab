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
package megameklab.ui.generalUnit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.TechConstants;
import megamek.common.enums.TechRating;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megamek.common.ui.SmallFontHelpTextLabel;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.Mek;
import megamek.common.verifier.TestEntity;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.TechComboBox;
import megameklab.util.UnitUtil;

/**
 * Panel for assigning armor type and tonnage for most heavy units.
 *
 * @author Neoancient
 */
public class MVFArmorView extends BuildView implements ActionListener, ChangeListener {
    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }

    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    private final static String CMD_MAXIMIZE = "MAXIMIZE";
    private final static String CMD_REMAINING = "REMAINING";

    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(EquipmentType::getName);
    private final CustomComboBox<TechRating> cbSVTechRating = new CustomComboBox<>(TechRating::getName);
    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel factorModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);
    private final JCheckBox chkPatchwork = new JCheckBox();
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    private final JLabel lblArmorTonnage = createLabel("lblArmorTonnage", "");
    private final JLabel lblArmorType = createLabel(I18N,
          "lblArmorType",
          "ArmorView.cbArmorType.text",
          "ArmorView.cbArmorType.tooltip");
    private final JLabel patchworkInfo = new SmallFontHelpTextLabel(I18N.getString("ArmorView.patchworkInfo"));

    private final ITechManager techManager;

    private long etype;
    private boolean industrial;
    private boolean primitive;
    private EntityMovementMode movementMode;
    private boolean svLimitedArmor;

    /**
     * Create the armor panel
     *
     * @param techManager The supplier of tech level parameters
     */
    public MVFArmorView(ITechManager techManager) {
        this(techManager, false);
    }

    /**
     * Create the armor panel
     *
     * @param techManager The supplier of tech level parameters
     * @param supportVee  If true, the amount of armor will be assigned by points rather than weight.
     */
    public MVFArmorView(ITechManager techManager, boolean supportVee) {
        this.techManager = techManager;
        initUI(supportVee);
    }

    private void initUI(boolean supportVee) {
        chkPatchwork.setText(I18N.getString("ArmorView.chkPatchwork.text"));
        chkPatchwork.setToolTipText(I18N.getString("ArmorView.chkPatchwork.tooltip"));
        cbArmorType.setToolTipText(I18N.getString("ArmorView.cbArmorType.tooltip"));
        lblArmorTonnage.setText(I18N.getString("ArmorView.spnTonnage.text"));
        spnTonnage.setToolTipText(I18N.getString("ArmorView.spnTonnage.tooltip"));
        btnMaximize.setText(I18N.getString("ArmorView.btnMaximize.text"));
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        btnMaximize.setToolTipText(I18N.getString("ArmorView.btnMaximize.tooltip"));
        btnUseRemaining.setText(I18N.getString("ArmorView.btnRemaining.text"));
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        btnUseRemaining.setToolTipText(I18N.getString("ArmorView.btnRemaining.tooltip"));
        cbSVTechRating.setToolTipText(I18N.getString("ArmorView.cbSVTechRating.tooltip"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = STANDARD_INSETS;

        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(chkPatchwork, gbc);

        gbc.gridy++;
        add(Box.createVerticalStrut(4), gbc);

        gbc.gridy++;
        add(patchworkInfo, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(lblArmorType, gbc);
        add(cbArmorType, gbc);

        if (supportVee) {
            for (TechRating r : TechRating.values()) {
                cbSVTechRating.addItem(r);
            }
            gbc.gridy++;
            JLabel label = createLabel(I18N,
                  "lblSVTechRating",
                  "ArmorView.cbSVTechRating.text",
                  "ArmorView.cbSVTechRating.tooltip");
            add(label, gbc);
            add(cbSVTechRating, gbc);
        }

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(lblArmorTonnage, gbc);
        add(spnTonnage, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy++;
        add(Box.createVerticalStrut(4), gbc);

        gbc.gridy++;
        add(btnMaximize, gbc);

        gbc.gridy++;
        add(btnUseRemaining, gbc);

        chkPatchwork.addActionListener(this);
        cbArmorType.addActionListener(this);
        spnTonnage.addChangeListener(this);
        btnMaximize.addActionListener(this);
        btnUseRemaining.addActionListener(this);
        cbSVTechRating.addActionListener(this);
    }

    /**
     * Sets the values of all fields from the current Entity.
     *
     * @param en The Entity currently being edited
     */
    public void setFromEntity(Entity en) {
        setFromEntity(en, false);
    }

    /**
     * Sets the values of all fields from the current Entity, with the option of ignoring whether the {@code Entity} has
     * patchwork armor. This is because the Entity does not report patchwork armor unless it actually has multiple armor
     * types, and we don't want to clear the patchwork checkbox unless we're loading a new unit.
     *
     * @param en                    The Entity being edited
     * @param ignoreEntityPatchwork Whether to ignore whether the Entity has patchwork armor.
     */
    public void setFromEntity(Entity en, boolean ignoreEntityPatchwork) {
        etype = en.getEntityType();
        industrial = (en instanceof Mek) && ((Mek) en).isIndustrial();
        primitive = en.isPrimitive();
        movementMode = en.getMovementMode();
        svLimitedArmor = en.isSupportVehicle() && !en.hasArmoredChassis();
        refresh();
        cbArmorType.removeActionListener(this);
        cbSVTechRating.setEnabled(true);
        spnTonnage.removeChangeListener(this);
        chkPatchwork.removeActionListener(this);
        cbArmorType.setSelectedItem(ArmorType.forEntity(en));
        if ((!ignoreEntityPatchwork && en.hasPatchworkArmor()) || (ignoreEntityPatchwork && isPatchwork())) {
            cbArmorType.setEnabled(false);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en), en.getLabArmorTonnage()));
            factorModel.setValue(en.getTotalOArmor());
            spnTonnage.setEnabled(false);
            chkPatchwork.setVisible(true);
            chkPatchwork.setSelected(true);
            btnMaximize.setEnabled(false);
            btnUseRemaining.setEnabled(false);

            lblArmorType.setVisible(false);
            cbArmorType.setVisible(false);
            lblArmorTonnage.setVisible(false);
            spnTonnage.setVisible(false);
            btnMaximize.setVisible(false);
            btnUseRemaining.setVisible(false);
            patchworkInfo.setVisible(true);
        } else {
            cbArmorType.setEnabled(cbArmorType.getItemCount() >= 2);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en), en.getLabArmorTonnage()));
            tonnageModel.setMaximum(UnitUtil.getMaximumArmorTonnage(en));
            factorModel.setMaximum(UnitUtil.getMaximumArmorPoints(en));
            factorModel.setValue(Math.min(en.getLabTotalArmorPoints(), (Integer) factorModel.getMaximum()));
            spnTonnage.setEnabled(true);
            chkPatchwork.setSelected(false);
            btnMaximize.setEnabled(true);
            btnUseRemaining.setEnabled(true);

            lblArmorType.setVisible(true);
            cbArmorType.setVisible(true);
            lblArmorTonnage.setVisible(true);
            spnTonnage.setVisible(true);
            btnMaximize.setVisible(true);
            btnUseRemaining.setVisible(true);
            patchworkInfo.setVisible(false);
        }
        if (en.isSupportVehicle() && !en.hasPatchworkArmor()) {
            if (ArmorType.forEntity(en).hasFlag(MiscType.F_SUPPORT_VEE_BAR_ARMOR)) {
                cbSVTechRating.removeActionListener(this);
                cbSVTechRating.setSelectedItem(en.getArmorTechRating());
                cbSVTechRating.addActionListener(this);
            } else {
                // Disable for non-BAR armor
                cbSVTechRating.setEnabled(false);
            }
        }
        if (en.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            lblArmorTonnage.setText(I18N.getString("ArmorView.spnFactor.text"));
            spnTonnage.setToolTipText(I18N.getString("ArmorView.spnFactor.tooltip"));
            spnTonnage.setModel(factorModel);
        } else {
            lblArmorTonnage.setText(I18N.getString("ArmorView.spnTonnage.text"));
            spnTonnage.setToolTipText(I18N.getString("ArmorView.spnTonnage.tooltip"));
            spnTonnage.setModel(tonnageModel);
        }

        cbArmorType.addActionListener(this);
        spnTonnage.addChangeListener(this);
        chkPatchwork.addActionListener(this);
    }

    public void refresh() {
        EquipmentType previousArmor = (EquipmentType) cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();
        boolean previousArmorAllowed = false;
        List<ArmorType> allArmors = TestEntity.legalArmorsFor(etype, industrial, primitive,
              movementMode, techManager);
        for (ArmorType armor : allArmors) {
            // Check whether SV armor exists at this tech rating or it requires the armored chassis mod.
            if (armor.hasFlag(MiscType.F_SUPPORT_VEE_BAR_ARMOR) && ((armor.getSVWeightPerPoint(getTechRating()) == 0.0)
                  || (svLimitedArmor && armor.getSVWeightPerPoint(getTechRating()) > 0.050))) {
                continue;
            }
            cbArmorType.addItem(armor);
            previousArmorAllowed |= armor == previousArmor;
        }
        if (((etype & (Entity.ETYPE_SMALL_CRAFT | Entity.ETYPE_JUMPSHIP)) == 0)
              && techManager.isLegal(Entity.getPatchworkArmorAdvancement())
              && !svLimitedArmor) {
            chkPatchwork.setVisible(true);
        } else {
            chkPatchwork.setVisible(false);
            chkPatchwork.setSelected(false);
        }
        if (previousArmorAllowed) {
            cbArmorType.setSelectedItem(previousArmor); // no event necessary, armor stays the same
            cbArmorType.addActionListener(this);
        } else if (cbArmorType.getModel().getSize() > 0) {
            cbArmorType.addActionListener(this); // fire an event for changing to an allowed armor to update the unit
            cbArmorType.setSelectedIndex(0);
        }
        cbArmorType.showTechBase(techManager.useMixedTech());
        cbArmorType.setEnabled(cbArmorType.getItemCount() >= 2);
    }

    /**
     * @return The selected armor equipment
     */
    public EquipmentType getArmor() {
        return (EquipmentType) cbArmorType.getSelectedItem();
    }

    /**
     * Enables or disables the patchwork checkbox and refreshes
     *
     * @param patchwork Whether the patchwork checkbox should be enabled
     */
    public void setPatchwork(boolean patchwork) {
        chkPatchwork.setSelected(patchwork);
        refresh();
    }

    /**
     * @return True when the patchwork checkbox is selected; this is not equivalent to the unit having patchwork armor;
     *       entity.hasPatchworkArmor() only returns true when there are actually at least two different armor types
     *       installed.
     */
    public boolean isPatchwork() {
        return chkPatchwork.isSelected();
    }

    /**
     * @return The armor type constant for the selected armor
     */
    public int getArmorType() {
        if (isPatchwork()) {
            return EquipmentType.T_ARMOR_PATCHWORK;
        } else {
            ArmorType armor = (ArmorType) cbArmorType.getSelectedItem();
            return (armor == null) ? EquipmentType.T_ARMOR_UNKNOWN : armor.getArmorType();
        }
    }

    public int getArmorTechConstant() {
        EquipmentType armor = (EquipmentType) cbArmorType.getSelectedItem();
        if (null == armor) {
            return TechConstants.T_TECH_UNKNOWN;
        }
        return (armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
    }

    public TechRating getTechRating() {
        TechRating selected = (TechRating) cbSVTechRating.getSelectedItem();
        return selected != null ? selected : TechRating.A;
    }

    public double getArmorTonnage() {
        return tonnageModel.getNumber().doubleValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            if (spnTonnage.getModel() == tonnageModel) {
                listeners.forEach(l -> l.armorTonnageChanged(tonnageModel.getNumber().doubleValue()));
            } else {
                listeners.forEach(l -> l.armorFactorChanged(factorModel.getNumber().intValue()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == cbArmorType) || ((e.getSource() == chkPatchwork) && !isPatchwork())) {
            listeners.forEach(l -> l.armorTypeChanged(getArmorType(), getArmorTechConstant()));
        } else if (e.getSource() == chkPatchwork) {
            listeners.forEach(l -> l.armorTypeChanged(EquipmentType.T_ARMOR_PATCHWORK,
                  Entity.getPatchworkArmorAdvancement()
                        .getTechLevel(techManager.getGameYear(), techManager.useClanTechBase())));
        } else if (CMD_MAXIMIZE.equals(e.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::maximizeArmor);
        } else if (CMD_REMAINING.equals(e.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::useRemainingTonnageArmor);
        } else if (e.getSource() == cbSVTechRating) {
            listeners.forEach(l -> l.armorTechRatingChanged(getTechRating()));
        }
    }
}
