/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.common.*;
import megamek.common.equipment.ArmorType;
import megamek.common.verifier.TestEntity;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.TechComboBox;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for assigning armor type and tonnage for mechs, (combat) vehicles, and fighters.
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
    
    private final static String CMD_MAXIMIZE  = "MAXIMIZE";
    private final static String CMD_REMAINING = "REMAINING";
    
    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(EquipmentType::getName);
    private final CustomComboBox<Integer> cbSVTechRating = new CustomComboBox<>(ITechnology::getRatingName);
    private final JComboBox<Integer> cbBARRating = new JComboBox<>();
    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel factorModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);
    private final JCheckBox chkPatchwork = new JCheckBox();
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    private final JLabel lblArmorTonnage = createLabel("lblArmorTonnage", "", labelSizeLg);
    
    private final ITechManager techManager;
    
    private long etype;
    private boolean industrial;
    private boolean primitive;
    private EntityMovementMode movementMode;
    private boolean svLimitedArmor;

    private final List<JComponent> svControlList = new ArrayList<>();

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblArmorType", "ArmorView.cbArmorType.text",
                "ArmorView.cbArmorType.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        setFieldSize(cbArmorType, controlSize);
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip"));
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);

        if (supportVee) {
            for (int r = ITechnology.RATING_A; r <= ITechnology.RATING_F; r++) {
                cbSVTechRating.addItem(r);
            }
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            JLabel label = createLabel(resourceMap, "lblSVTechRating", "ArmorView.cbSVTechRating.text",
                    "ArmorView.cbSVTechRating.tooltip", labelSizeLg);
            add(label, gbc);
            svControlList.add(label);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            setFieldSize(cbSVTechRating, controlSize);
            cbSVTechRating.setToolTipText(resourceMap.getString("ArmorView.cbSVTechRating.tooltip"));
            add(cbSVTechRating, gbc);
            svControlList.add(cbSVTechRating);
            cbSVTechRating.addActionListener(this);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            label = createLabel(resourceMap, "lblBARRating", "ArmorView.cbBARRating.text",
                    "ArmorView.cbBARRating.tooltip", labelSizeLg);
            add(label, gbc);
            svControlList.add(label);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            setFieldSize(cbBARRating, controlSize);
            cbBARRating.setToolTipText(resourceMap.getString("ArmorView.cbBARRating.tooltip"));
            add(cbBARRating, gbc);
            svControlList.add(cbBARRating);
            cbBARRating.addActionListener(this);
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnTonnage.text"));
        add(lblArmorTonnage, gbc);
        gbc.gridx = 1;
        setFieldSize(spnTonnage, spinnerSizeLg);
        spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        chkPatchwork.setText(resourceMap.getString("ArmorView.chkPatchwork.text"));
        setFieldSize(chkPatchwork, controlSize);
        chkPatchwork.setToolTipText(resourceMap.getString("ArmorView.chkPatchwork.tooltip"));
        add(chkPatchwork, gbc);
        chkPatchwork.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text"));
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        setFieldSize(btnMaximize, controlSize);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip"));
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text"));
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        setFieldSize(btnUseRemaining, controlSize);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip"));
        add(btnUseRemaining, gbc);
        btnUseRemaining.addActionListener(this);
    }
    
    /**
     * Sets the values of all fields from the current Entity.
     * @param en The Entity currently being edited
     */
    public void setFromEntity(Entity en) {
        setFromEntity(en, false);
    }
    
    /**
     * Sets the values of all fields from the current Entity, with the option of ignoring
     * whether the {@code Entity} has patchwork armor. This is because the Entity does not
     * report patchwork armor unless it actually has multiple armor types, and we don't want
     * to clear the patchwork checkbox unless we're loading a new unit.
     * 
     * @param en The Entity being edited
     * @param ignoreEntityPatchwork Whether to ignore whether the Entity has patchwork armor.
     */
    public void setFromEntity(Entity en, boolean ignoreEntityPatchwork) {
        etype = en.getEntityType();
        industrial = (en instanceof Mech) && ((Mech)en).isIndustrial();
        primitive = en.isPrimitive();
        movementMode = en.getMovementMode();
        svLimitedArmor = en.isSupportVehicle() && !en.hasArmoredChassis();
        refresh();
        cbArmorType.removeActionListener(this);
        spnTonnage.removeChangeListener(this);
        chkPatchwork.removeActionListener(this);
        String name = EquipmentType.getArmorTypeName(en.getArmorType(en.firstArmorIndex()),
                TechConstants.isClan(en.getArmorTechLevel(en.firstArmorIndex())));
        EquipmentType eq = EquipmentType.get(name);
        cbArmorType.setSelectedItem(eq);
        if ((!ignoreEntityPatchwork && en.hasPatchworkArmor())
                || (ignoreEntityPatchwork && isPatchwork())) {
            cbArmorType.setEnabled(false);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            factorModel.setValue(en.getTotalOArmor());
            spnTonnage.setEnabled(false);
            chkPatchwork.setVisible(true);
            chkPatchwork.setSelected(true);
            btnMaximize.setEnabled(false);
            btnUseRemaining.setEnabled(false);
        } else {
            cbArmorType.setEnabled(true);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            tonnageModel.setMaximum(UnitUtil.getMaximumArmorTonnage(en));
            factorModel.setMaximum(UnitUtil.getMaximumArmorPoints(en));
            factorModel.setValue(Math.min(en.getLabTotalArmorPoints(), (Integer) factorModel.getMaximum()));
            spnTonnage.setEnabled(true);
            chkPatchwork.setSelected(false);
            btnMaximize.setEnabled(true);
            btnUseRemaining.setEnabled(true);
        }
        if (en.isSupportVehicle() && !en.hasPatchworkArmor()
                && en.getArmorType(en.firstArmorIndex()) == EquipmentType.T_ARMOR_STANDARD) {
            svControlList.forEach(c -> c.setVisible(true));
            cbBARRating.removeActionListener(this);
            cbBARRating.removeAllItems();
            for (int bar = 2; bar <= 10; bar++) {
                double weight = ArmorType.svArmor(bar).getSVWeightPerPoint(en.getArmorTechRating());
                if ((weight > 0.0) && (!svLimitedArmor || (weight <= 0.05))) {
                    cbBARRating.addItem(bar);
                }
            }
            cbBARRating.setSelectedItem(en.getBARRating(en.firstArmorIndex()));
            cbBARRating.addActionListener(this);
            cbSVTechRating.removeActionListener(this);
            cbSVTechRating.setSelectedItem(en.getArmorTechRating());
            cbSVTechRating.addActionListener(this);
            if (cbBARRating.getSelectedIndex() < 0) {
                cbBARRating.setSelectedIndex(cbBARRating.getItemCount() - 1);
            }
        } else {
            svControlList.forEach(c -> c.setVisible(false));
        }
        if (en.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnFactor.text"));
            spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnFactor.tooltip"));
            spnTonnage.setModel(factorModel);
        } else {
            lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnTonnage.text"));
            spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip"));
            spnTonnage.setModel(tonnageModel);
        }

        cbArmorType.addActionListener(this);
        spnTonnage.addChangeListener(this);
        chkPatchwork.addActionListener(this);
    }
    
    public void refresh() {
        EquipmentType prev = (EquipmentType)cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();
        if (!svLimitedArmor) {
            List<EquipmentType> allArmors = TestEntity.legalArmorsFor(etype, industrial, primitive,
                    movementMode, techManager);
            allArmors.forEach(cbArmorType::addItem);
        } else {
            cbArmorType.addItem(EquipmentType.get(EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_STANDARD)));
        }
        if (((etype & (Entity.ETYPE_SMALL_CRAFT | Entity.ETYPE_JUMPSHIP)) == 0)
                && techManager.isLegal(Entity.getPatchworkArmorAdvancement())
                && !svLimitedArmor) {
            chkPatchwork.setVisible(true);
        } else {
            chkPatchwork.setVisible(false);
            chkPatchwork.setSelected(false);
        }
        if ((null == prev) && (cbArmorType.getModel().getSize() > 0)) {
            cbArmorType.setSelectedIndex(cbArmorType.getModel().getSize() - 1);
        } else {
            cbArmorType.setSelectedItem(prev);
        }
        cbArmorType.addActionListener(this);
        /* If there was a type previously set and nothing is selected, the previous choice
         * is not in the list. Select the first in the list after the listener is restored
         * to make sure the Entity is updated. */
        if ((null != prev) && (cbArmorType.getSelectedIndex() < 0) && (cbArmorType.getModel().getSize() > 0)) {
            cbArmorType.setSelectedIndex(0);
        }
        cbArmorType.showTechBase(techManager.useMixedTech());
    }
    
    /**
     * @return The selected armor equipment
     */
    public EquipmentType getArmor() {
        return (EquipmentType)cbArmorType.getSelectedItem();
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
     * @return Whether the patchwork checkbox is selected
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
            return EquipmentType.getArmorType((EquipmentType)cbArmorType.getSelectedItem());
        }
    }
    
    public int getArmorTechConstant() {
        EquipmentType armor = (EquipmentType) cbArmorType.getSelectedItem();
        if (null == armor) {
            return TechConstants.T_TECH_UNKNOWN;
        }
        return (armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
    }

    public int getTechRating() {
        Integer selected = (Integer) cbSVTechRating.getSelectedItem();
        if (null != selected) {
            return selected;
        }
        return ITechnology.RATING_A;
    }
    public int getBARRating() {
        Integer selected = (Integer) cbBARRating.getSelectedItem();
        if (null != selected) {
            return selected;
        }
        return 2;
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
        if ((e.getSource() == cbArmorType)
                || ((e.getSource() == chkPatchwork) && !isPatchwork())) {
            listeners.forEach(l -> l.armorTypeChanged(getArmorType(), getArmorTechConstant()));
        } else if (e.getSource() == chkPatchwork) {
            listeners.forEach(l -> l.armorTypeChanged(EquipmentType.T_ARMOR_PATCHWORK,
                    Entity.getPatchworkArmorAdvancement().getTechLevel(techManager.getGameYear(),
                            techManager.useClanTechBase())));
        } else if (CMD_MAXIMIZE.equals(e.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::maximizeArmor);
        } else if (CMD_REMAINING.equals(e.getActionCommand())) {
            listeners.forEach(ArmorAllocationListener::useRemainingTonnageArmor);
        } else if (e.getSource() == cbSVTechRating) {
            listeners.forEach(l -> l.armorTechRatingChanged(getTechRating()));
        } else if (e.getSource() == cbBARRating) {
            listeners.forEach(l -> l.armorBARRatingChanged(getBARRating()));
        }
    }
    
}
