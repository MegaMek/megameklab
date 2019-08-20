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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.*;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.ArmorAllocationListener;
import megameklab.com.util.UnitUtil;

/**
 * Panel for assigning armor type and tonnage for mechs, (combat) vehicles, and fighters.
 * 
 * @author Neoancient
 *
 */
public class MVFArmorView extends BuildView implements ActionListener, ChangeListener {
    
    private static final long serialVersionUID = 1246552271894765543L;

    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }
    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }
    
    private final static String CMD_MAXIMIZE  = "MAXIMIZE"; //$NON-NLS-1$
    private final static String CMD_REMAINING = "REMAINING"; //$NON-NLS-1$
    
    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(EquipmentType::getName);
    private final CustomComboBox<Integer> cbSVTechRating = new CustomComboBox<>(ITechnology::getRatingName);
    private final JComboBox<Integer> cbBARRating = new JComboBox<>();
    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel factorModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);
    private final JCheckBox chkPatchwork = new JCheckBox();
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    private final JLabel lblArmorTonnage = createLabel("", labelSizeLg);
    
    private final ITechManager techManager;
    
    private long etype;
    private boolean industrial;
    private EntityMovementMode movementMode;
    private boolean svLimitedArmor;

    private final List<JComponent> svControlList = new ArrayList<>();

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$

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
        add(createLabel(resourceMap.getString("ArmorView.cbArmorType.text"), labelSizeLg), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        setFieldSize(cbArmorType, controlSize);
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip")); //$NON-NLS-1$
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);

        if (supportVee) {
            for (int r = ITechnology.RATING_A; r <= ITechnology.RATING_F; r++) {
                cbSVTechRating.addItem(r);
            }
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            JLabel label = createLabel(resourceMap.getString("ArmorView.cbSVTechRating.text"), labelSizeLg); //$NON-NLS-1$
            add(label, gbc);
            svControlList.add(label);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            setFieldSize(cbSVTechRating, controlSize);
            cbSVTechRating.setToolTipText(resourceMap.getString("ArmorView.cbSVTechRating.tooltip")); //$NON-NLS-1$
            add(cbSVTechRating, gbc);
            svControlList.add(cbSVTechRating);
            cbSVTechRating.addActionListener(this);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            label = createLabel(resourceMap.getString("ArmorView.cbBARRating.text"), labelSizeLg); //$NON-NLS-1$
            add(label, gbc);
            svControlList.add(label);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            setFieldSize(cbBARRating, controlSize);
            cbBARRating.setToolTipText(resourceMap.getString("ArmorView.cbBARRating.tooltip")); //$NON-NLS-1$
            add(cbBARRating, gbc);
            svControlList.add(cbBARRating);
            cbBARRating.addActionListener(this);
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnTonnage.text")); //$NON-NLS-1$
        add(lblArmorTonnage, gbc);
        gbc.gridx = 1;
        setFieldSize(spnTonnage, spinnerSizeLg);
        spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        chkPatchwork.setText(resourceMap.getString("ArmorView.chkPatchwork.text")); //$NON-NLS-1$
        setFieldSize(chkPatchwork, controlSize);
        chkPatchwork.setToolTipText(resourceMap.getString("ArmorView.chkPatchwork.tooltip")); //$NON-NLS-1$
        add(chkPatchwork, gbc);
        chkPatchwork.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text")); //$NON-NLS-1$
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        setFieldSize(btnMaximize, controlSize);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip")); //$NON-NLS-1$
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text")); //$NON-NLS-1$
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        setFieldSize(btnUseRemaining, controlSize);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip")); //$NON-NLS-1$
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
                double weight = EquipmentType.getSupportVehicleArmorWeightPerPoint(bar, en.getArmorTechRating());
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
            lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnFactor.text")); //$NON-NLS-1$
            spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnFactor.tooltip")); //$NON-NLS-1$
            spnTonnage.setModel(factorModel);
        } else {
            lblArmorTonnage.setText(resourceMap.getString("ArmorView.spnTonnage.text")); //$NON-NLS-1$
            spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip")); //$NON-NLS-1$
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
            List<EquipmentType> allArmors = TestEntity.legalArmorsFor(etype, industrial, movementMode, techManager);
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
        if (null == prev) {
            cbArmorType.setSelectedIndex(cbArmorType.getModel().getSize() - 1);
        } else {
            cbArmorType.setSelectedItem(prev);
        }
        cbArmorType.addActionListener(this);
        if ((null != prev) && (cbArmorType.getSelectedIndex() < 0)) {
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
