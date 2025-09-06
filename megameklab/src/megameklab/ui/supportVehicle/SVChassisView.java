/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.TechAdvancement;
import megamek.common.enums.TechRating;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.SupportTank;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.SVBuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.TechComboBox;

/**
 * Chassis panel for support vehicles
 */
public class SVChassisView extends BuildView implements ActionListener, ChangeListener {

    private final List<SVBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(SVBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(SVBuildListener l) {
        listeners.remove(l);
    }

    /** Subset of possible types that does not include those that are not yet supported */
    private final List<TestSupportVehicle.SVType> SV_TYPES = Arrays.stream(TestSupportVehicle.SVType.values())
          .filter(t -> !t.equals(TestSupportVehicle.SVType.AIRSHIP)
                && !t.equals(TestSupportVehicle.SVType.SATELLITE)).toList();
    private final Map<TestSupportVehicle.SVType, String> typeNames = new EnumMap<>(TestSupportVehicle.SVType.class);

    private final static TechAdvancement TA_DUAL_TURRET = Tank.getDualTurretTA();
    private final static TechAdvancement TA_CHIN_TURRET = VTOL.getChinTurretTA();

    // We have separate models for small support vehicles, since they are on the kilogram standard and have different
    // step sizes.
    private final SpinnerNumberModel spnTonnageModel = new SpinnerNumberModel(20.0, 5.0, 300.0, 0.5);
    private final SpinnerNumberModel spnTonnageModelSmall = new SpinnerNumberModel(4000.0, 100.0, 4999.0, 1.0);
    private final SpinnerNumberModel spnTurretWtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnTurret2WtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnSponsonPintleWtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private String[] turretNames;
    private final SpinnerNumberModel spnFireConWtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);

    private final JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    private final JCheckBox chkSmall = new JCheckBox();
    private final CustomComboBox<TechRating> cbStructureTechRating = new CustomComboBox<>(TechRating::getName);
    private final CustomComboBox<TestSupportVehicle.SVType> cbType = new CustomComboBox<>(t -> typeNames.getOrDefault(t,
          "?"));
    private final TechComboBox<TestSupportVehicle.SVEngine> cbEngine = new TechComboBox<>(e -> e.engine.getEngineName()
          .replaceAll("^\\d+ ", "").replace("[SV]", ""));
    private final CustomComboBox<TechRating> cbEngineTechRating = new CustomComboBox<>(TechRating::getName);
    private final CustomComboBox<Integer> cbTurrets = new CustomComboBox<>(i -> turretNames[i]);
    private final JCheckBox chkSponson = new JCheckBox();
    private final JLabel lblPintle = createLabel("lblPintle", "");
    private final JCheckBox chkPintleLeft = new JCheckBox();
    private final JCheckBox chkPintleRight = new JCheckBox();
    private final JCheckBox chkPintleFront = new JCheckBox();
    private final JCheckBox chkPintleRear = new JCheckBox();
    private final JSpinner spnChassisTurretWt = new JSpinner(spnTurretWtModel);
    private final JSpinner spnChassisTurret2Wt = new JSpinner(spnTurret2WtModel);
    private final JSpinner spnChassisSponsonPintleWt = new JSpinner(spnSponsonPintleWtModel);
    private final JComboBox<String> cbFireControl = new JComboBox<>();
    private final JSpinner spnFireConWt = new JSpinner(spnFireConWtModel);

    private final JPanel omniPanel = new JPanel();

    private final ITechManager techManager;

    public SVChassisView(ITechManager techManager) {
        super();
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        for (TestSupportVehicle.SVType t : TestSupportVehicle.SVType.values()) {
            typeNames.put(t, resourceMap.getString("SVType." + t.toString()));
        }
        turretNames = resourceMap.getString("CVChassisView.turrets.values").split(",");
        final String[] fireConNames = resourceMap.getString("SVChassisView.fireCon.values").split(",");
        cbFireControl.setModel(new DefaultComboBoxModel<>(fireConNames));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblTonnage", "SVChassisView.spnTonnage.text",
              "SVChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 1;
        spnTonnage.setToolTipText(resourceMap.getString("SVChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        gbc.gridx = 2;
        chkSmall.setText(resourceMap.getString("SVChassisView.chkSmall.text"));
        chkSmall.setToolTipText(resourceMap.getString("SVChassisView.chkSmall.tooltip"));
        add(chkSmall, gbc);
        chkSmall.setActionCommand(ACTION_SMALL);
        chkSmall.addActionListener(this);
        for (TechRating r : TechRating.values()) {
            cbStructureTechRating.addItem(r);
        }
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblStructureTechRating", "SVChassisView.cbStructureTechRating.text",
              "SVChassisView.cbStructureTechRating.tooltip"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        cbStructureTechRating.setToolTipText(resourceMap.getString("SVChassisView.cbStructureTechRating.tooltip"));
        add(cbStructureTechRating, gbc);
        cbStructureTechRating.setActionCommand(ACTION_STRUCTURE_RATING);
        cbStructureTechRating.addActionListener(this);

        SV_TYPES.forEach(cbType::addItem);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblType", "SVChassisView.cbType.text",
              "SVChassisView.cbType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        cbType.setToolTipText(resourceMap.getString("SVChassisView.cbType.tooltip"));
        add(cbType, gbc);
        cbType.setActionCommand(ACTION_TYPE);
        cbType.addActionListener(this);

        cbEngine.setModel(new DefaultComboBoxModel<>(TestSupportVehicle.SVEngine.values()));
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblEngine", "SVChassisView.cbEngine.text",
              "SVChassisView.cbEngine.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        cbEngine.setToolTipText(resourceMap.getString("SVChassisView.cbEngine.tooltip"));
        add(cbEngine, gbc);
        cbEngine.setActionCommand(ACTION_ENGINE);
        cbEngine.addActionListener(this);
        for (TechRating r : TechRating.values()) {
            cbEngineTechRating.addItem(r);
        }
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblEngineTechRating", "SVChassisView.cbEngineTechRating.text",
              "SVChassisView.cbEngineTechRating.tooltip"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        cbEngineTechRating.setToolTipText(resourceMap.getString("SVChassisView.cbEngineTechRating.tooltip"));
        add(cbEngineTechRating, gbc);
        cbEngineTechRating.setActionCommand(ACTION_ENGINE_RATING);
        cbEngineTechRating.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblTurrets", "CVChassisView.cbTurrets.text",
              "CVChassisView.cbTurrets.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        cbTurrets.setToolTipText(resourceMap.getString("CVChassisView.cbTurrets.tooltip"));
        add(cbTurrets, gbc);
        cbTurrets.setActionCommand(ACTION_TURRET_CONFIG);
        cbTurrets.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        chkSponson.setText(resourceMap.getString("SVChassisView.chkSponson.text"));
        chkSponson.setToolTipText(resourceMap.getString("SVChassisView.chkSponson.tooltip"));
        add(chkSponson, gbc);
        chkSponson.setActionCommand(ACTION_SPONSON);
        chkSponson.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        lblPintle.setText(resourceMap.getString("SVChassisView.lblPintle.text"));
        add(lblPintle, gbc);
        gbc.gridx = 1;
        chkPintleLeft.setText(resourceMap.getString("SVChassisView.chkPintleLeft.text"));
        chkPintleLeft.setToolTipText(resourceMap.getString("SVChassisView.chkPintle.tooltip"));
        chkPintleLeft.setActionCommand(ACTION_PINTLE_LEFT);
        add(chkPintleLeft, gbc);
        chkPintleLeft.addActionListener(this);
        gbc.gridx = 2;
        chkPintleRight.setText(resourceMap.getString("SVChassisView.chkPintleRight.text"));
        chkPintleRight.setToolTipText(resourceMap.getString("SVChassisView.chkPintle.tooltip"));
        add(chkPintleRight, gbc);
        chkPintleRight.setActionCommand(ACTION_PINTLE_RIGHT);
        chkPintleRight.addActionListener(this);
        gbc.gridy++;
        gbc.gridx = 1;
        chkPintleFront.setText(resourceMap.getString("SVChassisView.chkPintleFront.text"));
        chkPintleFront.setToolTipText(resourceMap.getString("SVChassisView.chkPintle.tooltip"));
        add(chkPintleFront, gbc);
        chkPintleFront.setActionCommand(ACTION_PINTLE_FRONT);
        chkPintleFront.addActionListener(this);
        gbc.gridx = 2;
        chkPintleRear.setText(resourceMap.getString("SVChassisView.chkPintleRear.text"));
        chkPintleRear.setToolTipText(resourceMap.getString("SVChassisView.chkPintle.tooltip"));
        add(chkPintleRear, gbc);
        chkPintleRear.setActionCommand(ACTION_PINTLE_REAR);
        chkPintleRear.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblFireCon", "SVChassisView.cbFireCon.text",
              "SVChassisView.cbFireCon.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        cbFireControl.setToolTipText(resourceMap.getString("SVChassisView.cbFireCon.tooltip"));
        add(cbFireControl, gbc);
        cbFireControl.setActionCommand(ACTION_FIRE_CONTROL);
        cbFireControl.addActionListener(this);

        initOmniPanel(resourceMap);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(omniPanel, gbc);
    }

    private void initOmniPanel(final ResourceBundle resourceMap) {
        omniPanel.setLayout(new GridBagLayout());

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        omniPanel.add(new JLabel(resourceMap.getString("SVChassisView.lblBaseChassisTurretWeight.text")), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        omniPanel.add(createLabel(resourceMap, "lblTurret1Wt", "SVChassisView.spnTurret1Wt.text",
              "CVChassisView.spnTurretWt.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        spnChassisTurretWt.setToolTipText(resourceMap.getString("CVChassisView.spnTurretWt.tooltip"));
        omniPanel.add(spnChassisTurretWt, gbc);
        spnChassisTurretWt.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        omniPanel.add(createLabel(resourceMap, "lblTurret2Wt", "SVChassisView.spnTurret2Wt.text",
              "CVChassisView.spnTurret2Wt.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        spnChassisTurret2Wt.setToolTipText(resourceMap.getString("CVChassisView.spnTurret2Wt.tooltip"));
        omniPanel.add(spnChassisTurret2Wt, gbc);
        spnChassisTurret2Wt.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        omniPanel.add(createLabel(resourceMap, "lblSponsonPintleWt", "SVChassisView.spnSponsonPintleWt.text",
              "SVChassisView.spnSponsonPintleWt.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        spnChassisSponsonPintleWt.setToolTipText(resourceMap.getString("SVChassisView.spnSponsonPintleWt.tooltip"));
        omniPanel.add(spnChassisSponsonPintleWt, gbc);
        spnChassisSponsonPintleWt.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        omniPanel.add(createLabel(resourceMap, "lblFireConWt", "SVChassisView.spnFireConWt.text",
              "SVChassisView.spnFireConWt.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        spnFireConWt.setToolTipText(resourceMap.getString("SVChassisView.spnFireConWt.tooltip"));
        omniPanel.add(spnFireConWt, gbc);
        spnFireConWt.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JButton btnResetChassis = new JButton(resourceMap.getString("SVChassisView.btnResetChassis.text"));
        btnResetChassis.setToolTipText(resourceMap.getString("SVChassisView.btnResetChassis.tooltip"));
        omniPanel.add(btnResetChassis, gbc);
        btnResetChassis.setActionCommand(ACTION_RESET_CHASSIS);
        btnResetChassis.addActionListener(this);
    }

    /**
     * Convenience method to convert weight values to tons based on whether the small size box is checked.
     *
     * @param weight The weight value in either the ton or kg standard as determined by the checkbox
     *
     * @return The weight in tons
     */
    private double convertWeight(double weight) {
        if (chkSmall.isSelected()) {
            return weight / 1000.0;
        } else {
            return weight;
        }
    }

    /**
     * @return The currently selected support vehicle type
     */
    public TestSupportVehicle.SVType getType() {
        return (TestSupportVehicle.SVType) cbType.getSelectedItem();
    }

    /**
     * Used to check eligibility for turret(s).
     *
     * @return Whether the vehicle is an aerospace type
     */
    private boolean isAeroType() {
        final TestSupportVehicle.SVType type = getType();
        return type == TestSupportVehicle.SVType.FIXED_WING
              || type == TestSupportVehicle.SVType.AIRSHIP
              || type == TestSupportVehicle.SVType.SATELLITE;
    }

    /**
     * @return The currently selected structural tech rating, or A if none is selected.
     */
    private TechRating getStructuralTechRating() {
        TechRating selected = (TechRating) cbStructureTechRating.getSelectedItem();
        return selected != null ? selected : TechRating.A;
    }

    /**
     * @return The currently selected engine tech rating, or A if none is selected.
     */
    private TechRating getEngineTechRating() {
        TechRating selected = (TechRating) cbEngineTechRating.getSelectedItem();
        return selected != null ? selected : TechRating.A;
    }

    /**
     * @return The currently selected engine type, or {@link TestSupportVehicle.SVEngine#COMBUSTION COMBUSTION} if none
     *       is selected
     */
    private TestSupportVehicle.SVEngine getEngineType() {
        if (cbEngine.getSelectedItem() != null) {
            return (TestSupportVehicle.SVEngine) cbEngine.getSelectedItem();
        } else {
            return TestSupportVehicle.SVEngine.COMBUSTION;
        }
    }

    public void setFromEntity(Entity entity) {
        refresh();

        spnTonnage.removeChangeListener(this);
        if (entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            chkSmall.setSelected(true);
            spnTonnage.setModel(spnTonnageModelSmall);
            spnTonnageModelSmall.setValue(entity.getWeight() * 1000);
        } else {
            chkSmall.setSelected(false);
            spnTonnage.setModel(spnTonnageModel);
            spnTonnageModel.setValue(entity.getWeight());
        }
        spnTonnage.addChangeListener(this);

        cbEngine.removeActionListener(this);
        cbEngine.removeAllItems();
        for (TestSupportVehicle.SVEngine engine : TestSupportVehicle.SVEngine.values()) {
            if (techManager.isLegal(engine)
                  && engine.isValidFor(entity)) {
                cbEngine.addItem(engine);
            }
        }
        cbEngine.setSelectedItem(TestSupportVehicle.SVEngine.getEngineType(entity.getEngine()));
        cbEngine.addActionListener(this);

        cbStructureTechRating.removeActionListener(this);
        cbStructureTechRating.setSelectedItem(entity.getStructuralTechRating());
        cbStructureTechRating.addActionListener(this);

        cbType.removeActionListener(this);
        cbType.removeAllItems();
        for (TestSupportVehicle.SVType type : SV_TYPES) {
            if (techManager.isLegal(type)) {
                cbType.addItem(type);
            }
        }
        cbType.setSelectedItem(TestSupportVehicle.SVType.getVehicleType(entity));
        cbType.addActionListener(this);

        // Engine::getTechRating will return the minimum tech rating for the engine type.
        cbEngineTechRating.removeActionListener(this);
        cbEngineTechRating.removeAllItems();
        for (int r = entity.getEngine().getTechRating().getIndex(); r <= TechRating.F.getIndex(); r++) {
            cbEngineTechRating.addItem(TechRating.fromIndex(r));
        }
        cbEngineTechRating.setSelectedItem(entity.getEngineTechRating());
        cbEngineTechRating.addActionListener(this);

        // If the previously selected items are no longer valid, select the first in the list. This
        // is done with event handlers turned on so the Entity gets changed.
        if (getStructuralTechRating() != entity.getStructuralTechRating()) {
            cbStructureTechRating.setSelectedIndex(0);
        }
        if (getEngineTechRating() != entity.getEngineTechRating()) {
            cbEngine.setSelectedIndex(0);
        }

        if (entity instanceof Tank tank) {
            if (!tank.hasNoDualTurret()) {
                cbTurrets.setSelectedItem(SVBuildListener.TURRET_DUAL);
            } else if (!tank.hasNoTurret()) {
                cbTurrets.setSelectedItem((tank.getMovementMode() == EntityMovementMode.VTOL) ?
                      SVBuildListener.TURRET_CHIN : SVBuildListener.TURRET_SINGLE);
            } else {
                cbTurrets.setSelectedItem(SVBuildListener.TURRET_NONE);
            }
            final double turret1 = Math.max(0, tank.getBaseChassisTurretWeight());
            final double turret2 = Math.max(0, tank.getBaseChassisTurret2Weight());
            final double side = Math.max(0, tank.getBaseChassisSponsonPintleWeight());
            if (entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
                spnTurretWtModel.setStepSize(1.0);
                spnTurretWtModel.setValue(turret1 * 1000.0);
                spnTurret2WtModel.setStepSize(1.0);
                spnTurret2WtModel.setValue(turret2 * 1000.0);
                spnSponsonPintleWtModel.setStepSize(1.0);
                spnSponsonPintleWtModel.setValue(side * 1000.0);
            } else {
                spnChassisTurretWt.setModel(spnTurretWtModel);
                spnTurretWtModel.setStepSize(0.5);
                spnTurretWtModel.setValue(turret1);
                spnTurret2WtModel.setStepSize(0.5);
                spnTurret2WtModel.setValue(turret2);
                spnSponsonPintleWtModel.setStepSize(0.5);
                spnSponsonPintleWtModel.setValue(side);
            }
            cbTurrets.setEnabled(true);
            chkSponson.setSelected(entity.hasWorkingMisc(MiscType.F_SPONSON_TURRET));
            chkPintleLeft.setSelected(entity.hasMisc(EquipmentTypeLookup.PINTLE_TURRET, SupportTank.LOC_LEFT));
            chkPintleRight.setSelected(entity.hasMisc(EquipmentTypeLookup.PINTLE_TURRET, SupportTank.LOC_RIGHT));
            chkPintleFront.setSelected(entity.hasMisc(EquipmentTypeLookup.PINTLE_TURRET, SupportTank.LOC_FRONT));
            chkPintleRear.setSelected(entity.hasMisc(EquipmentTypeLookup.PINTLE_TURRET, SupportTank.LOC_REAR));
        } else {
            cbTurrets.setEnabled(false);
        }

        if (entity.hasMisc(MiscType.F_ADVANCED_FIRE_CONTROL)) {
            cbFireControl.setSelectedIndex(SVBuildListener.FIRE_CONTROL_ADVANCED);
        } else if (entity.hasMisc(MiscType.F_BASIC_FIRE_CONTROL)) {
            cbFireControl.setSelectedIndex(SVBuildListener.FIRE_CONTROL_BASIC);
        } else {
            cbFireControl.setSelectedIndex(SVBuildListener.FIRE_CONTROL_NONE);
        }
        if (entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            spnFireConWtModel.setStepSize(1.0);
            spnFireConWt.setValue(entity.getBaseChassisFireConWeight() * 1000.0);
        } else {
            spnFireConWtModel.setStepSize(0.5);
            spnFireConWtModel.setValue(entity.getBaseChassisFireConWeight());
        }

        omniPanel.setVisible(true);
        spnChassisTurretWt.setEnabled(!entity.isAero() && entity.isOmni()
              && (cbTurrets.getSelectedIndex() > SVBuildListener.TURRET_NONE));
        spnChassisTurret2Wt.setEnabled(!entity.isAero() && entity.isOmni()
              && Integer.valueOf(SVBuildListener.TURRET_DUAL).equals(cbTurrets.getSelectedItem()));
        spnChassisSponsonPintleWt.setEnabled(!entity.isAero()
              && (chkSponson.isSelected()
              || chkPintleLeft.isSelected()
              || chkPintleRight.isSelected()
              || chkPintleFront.isSelected()
              || chkPintleRear.isSelected()));
        spnFireConWt.setEnabled(entity.isOmni() && (cbFireControl.getSelectedIndex()
              > SVBuildListener.FIRE_CONTROL_NONE));
    }

    /**
     * Disables controls that cannot be changed when customizing a refit.
     */
    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbType.setEnabled(false);
        cbStructureTechRating.setEnabled(false);
    }

    public void refresh() {
        refreshTonnage();
        refreshTurrets();
        refreshSideTurrets();
    }

    private void refreshTonnage() {
        double max = getType().maxTonnage;
        spnTonnageModel.setMaximum(max);
        if (spnTonnageModel.getNumber().doubleValue() > max) {
            spnTonnageModel.setValue(max);
        }
    }

    private void refreshTurrets() {
        Integer prev = (Integer) cbTurrets.getSelectedItem();
        cbTurrets.removeActionListener(this);
        cbTurrets.removeAllItems();
        cbTurrets.addItem(SVBuildListener.TURRET_NONE);
        if (TestSupportVehicle.SVType.VTOL.equals(cbType.getSelectedItem())) {
            if (techManager.isLegal(TA_CHIN_TURRET)) {
                cbTurrets.addItem(SVBuildListener.TURRET_CHIN);
            }
        } else if (!isAeroType()) {
            cbTurrets.addItem(SVBuildListener.TURRET_SINGLE);
            if (!TestSupportVehicle.SVType.WIGE.equals(cbType.getSelectedItem())
                  && techManager.isLegal(TA_DUAL_TURRET)) {
                cbTurrets.addItem(SVBuildListener.TURRET_DUAL);
            }
        }
        cbTurrets.setSelectedItem(prev);
        cbTurrets.addActionListener(this);
        if (cbTurrets.getSelectedIndex() < 0) {
            cbTurrets.setSelectedIndex(0);
        }

        double maxWt = convertWeight((double) spnTonnage.getValue());
        if (chkSmall.isSelected()) {
            maxWt *= 1000.0;
        }
        setSpinnerMaxWeight(spnTurretWtModel, maxWt);
        setSpinnerMaxWeight(spnTurret2WtModel, maxWt);
        setSpinnerMaxWeight(spnSponsonPintleWtModel, maxWt);
    }

    private void setSpinnerMaxWeight(SpinnerNumberModel model, double maxWt) {
        model.setMaximum(maxWt);
        if (model.getNumber().doubleValue() > maxWt) {
            model.removeChangeListener(this);
            model.setValue(maxWt);
            model.addChangeListener(this);
        }
    }

    private void refreshSideTurrets() {
        final boolean showSponson = TestSupportVehicle.sponsonLegal((TestSupportVehicle.SVType) cbType.getSelectedItem(),
              chkSmall.isSelected())
              && techManager.isLegal(EquipmentType.get(EquipmentTypeLookup.SPONSON_TURRET));
        final boolean showPintle = TestSupportVehicle.pintleLegal((TestSupportVehicle.SVType) cbType.getSelectedItem(),
              chkSmall.isSelected())
              && techManager.isLegal(EquipmentType.get(EquipmentTypeLookup.PINTLE_TURRET));
        chkSponson.setVisible(showSponson);
        lblPintle.setVisible(showPintle);
        chkPintleLeft.setVisible(showPintle);
        chkPintleRight.setVisible(showPintle);
        chkPintleFront.setVisible(showPintle);
        chkPintleRear.setVisible(showPintle);
    }

    /**
     * Toggles spinners between ton and kg standard
     */
    private void resetWeightStandard() {
        refreshSideTurrets();
        spnTonnage.removeChangeListener(this);
        spnChassisTurretWt.removeChangeListener(this);
        spnChassisTurret2Wt.removeChangeListener(this);
        spnChassisSponsonPintleWt.removeChangeListener(this);
        spnFireConWt.removeChangeListener(this);
        final double turret1 = spnTurretWtModel.getNumber().doubleValue();
        final double turret2 = spnTurret2WtModel.getNumber().doubleValue();
        final double sideTurret = spnSponsonPintleWtModel.getNumber().doubleValue();
        final double fireCon = spnFireConWtModel.getNumber().doubleValue();
        if (chkSmall.isSelected()) {
            spnTonnage.setModel(spnTonnageModelSmall);
            spnTurretWtModel.setStepSize(1.0);
            spnTurret2WtModel.setStepSize(1.0);
            spnSponsonPintleWtModel.setStepSize(1.0);
            spnFireConWtModel.setStepSize(1.0);
            chkSponson.setSelected(false);
        } else {
            spnTonnage.setModel(spnTonnageModel);
            spnTurretWtModel.setStepSize(0.5);
            spnTurret2WtModel.setStepSize(0.5);
            spnSponsonPintleWtModel.setStepSize(0.5);
            spnFireConWtModel.setStepSize(0.5);
            chkPintleLeft.setSelected(false);
            chkPintleRight.setSelected(false);
            chkPintleFront.setSelected(false);
            chkPintleRear.setSelected(false);
        }
        final double max = ((Number) spnTonnage.getValue()).doubleValue();
        spnTurretWtModel.setMaximum(max);
        spnTurret2WtModel.setMaximum(max);
        spnSponsonPintleWtModel.setMaximum(max);
        spnFireConWtModel.setMaximum(max);
        spnChassisSponsonPintleWt.setEnabled(chkSponson.isSelected()
              || chkPintleLeft.isSelected()
              || chkPintleRight.isSelected()
              || chkPintleFront.isSelected()
              || chkPintleRear.isSelected());

        spnTonnage.addChangeListener(this);
        spnChassisTurretWt.addChangeListener(this);
        spnChassisTurret2Wt.addChangeListener(this);
        spnChassisSponsonPintleWt.addChangeListener(this);
        spnFireConWt.addChangeListener(this);

        if (chkSmall.isSelected()) {
            spnTurretWtModel.setValue(Math.min(turret1 * 1000.0, max));
            spnTurret2WtModel.setValue(Math.min(turret2 * 1000.0, max));
            spnSponsonPintleWtModel.setValue(Math.min(sideTurret * 1000.0, max));
            spnFireConWtModel.setValue(Math.min(fireCon * 1000.0, max));
        } else {
            spnTurretWtModel.setValue(Math.min(Math.ceil(turret1 / 500.0) / 2.0, max));
            spnTurret2WtModel.setValue(Math.min(Math.ceil(turret2 / 500.0) / 2.0, max));
            spnSponsonPintleWtModel.setValue(Math.min(Math.ceil(sideTurret / 500.0) / 2.0, max));
            spnFireConWtModel.setValue(Math.min(Math.ceil(fireCon / 500.0) / 2.0, max));
        }
    }

    private static final String ACTION_SMALL = "small";
    private static final String ACTION_TYPE = "type";
    private static final String ACTION_STRUCTURE_RATING = "structureTechRating";
    private static final String ACTION_ENGINE = "engine";
    private static final String ACTION_ENGINE_RATING = "engineRating";
    private static final String ACTION_TURRET_CONFIG = "turretConfig";
    private static final String ACTION_SPONSON = "sponson";
    private static final String ACTION_PINTLE_LEFT = "pintleLeft";
    private static final String ACTION_PINTLE_RIGHT = "pintleRight";
    private static final String ACTION_PINTLE_FRONT = "pintleFront";
    private static final String ACTION_PINTLE_REAR = "pintleRear";
    private static final String ACTION_FIRE_CONTROL = "fireControl";
    private static final String ACTION_RESET_CHASSIS = "resetChassis";

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_SMALL:
                resetWeightStandard();
                listeners.forEach(l -> l.tonnageChanged(convertWeight((double) spnTonnage.getValue())));
                // Switching between small and M/L makes either sponsons or pintless invalid
                if (chkSmall.isSelected()) {
                    listeners.forEach(l -> l.sponsonTurretChanged(false));
                } else {
                    listeners.forEach(l -> l.pintleTurretChanged(false, Tank.LOC_LEFT));
                    listeners.forEach(l -> l.pintleTurretChanged(false, Tank.LOC_RIGHT));
                    listeners.forEach(l -> l.pintleTurretChanged(false, Tank.LOC_FRONT));
                    listeners.forEach(l -> l.pintleTurretChanged(false, Tank.LOC_REAR));
                }
                break;
            case ACTION_TYPE:
                listeners.forEach(l -> l.typeChanged(getType()));
                break;
            case ACTION_STRUCTURE_RATING:
                listeners.forEach(l -> l.structuralTechRatingChanged(getStructuralTechRating()));
                break;
            case ACTION_ENGINE:
                listeners.forEach(l -> l.engineChanged(getEngineType().engine));
                break;
            case ACTION_ENGINE_RATING:
                listeners.forEach(l -> l.engineTechRatingChanged(getEngineTechRating()));
                break;
            case ACTION_TURRET_CONFIG:
                if (cbTurrets.getSelectedItem() != null) {
                    listeners.forEach(l -> l.turretChanged((Integer) cbTurrets.getSelectedItem()));
                }
                break;
            case ACTION_SPONSON:
                listeners.forEach(l -> l.sponsonTurretChanged(chkSponson.isSelected()));
                break;
            case ACTION_PINTLE_LEFT:
                listeners.forEach(l -> l.pintleTurretChanged(chkPintleLeft.isSelected(), Tank.LOC_LEFT));
                break;
            case ACTION_PINTLE_RIGHT:
                listeners.forEach(l -> l.pintleTurretChanged(chkPintleRight.isSelected(), Tank.LOC_RIGHT));
                break;
            case ACTION_PINTLE_FRONT:
                listeners.forEach(l -> l.pintleTurretChanged(chkPintleFront.isSelected(), Tank.LOC_FRONT));
                break;
            case ACTION_PINTLE_REAR:
                listeners.forEach(l -> l.pintleTurretChanged(chkPintleRear.isSelected(), Tank.LOC_REAR));
                break;
            case ACTION_FIRE_CONTROL:
                listeners.forEach(l -> l.fireConChanged(cbFireControl.getSelectedIndex()));
                break;
            case ACTION_RESET_CHASSIS:
                listeners.forEach(SVBuildListener::resetChassis);
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(convertWeight((double) spnTonnage.getValue())));
        } else if ((e.getSource() == spnChassisTurretWt)
              || (e.getSource() == spnChassisTurret2Wt)) {
            listeners.forEach(l -> l.turretBaseWtChanged(
                  convertWeight(spnTurretWtModel.getNumber().doubleValue()),
                  convertWeight(spnTurret2WtModel.getNumber().doubleValue())));
        } else if (e.getSource() == spnChassisSponsonPintleWt) {
            listeners.forEach(l -> l.sponsonPintleBaseWtChanged(convertWeight(spnSponsonPintleWtModel.getNumber()
                  .doubleValue())));
        } else if (e.getSource() == spnFireConWt) {
            listeners.forEach(l -> l.fireConWtChanged(convertWeight(spnFireConWtModel.getNumber().doubleValue())));
        }
    }
}
