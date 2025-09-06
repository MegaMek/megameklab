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

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import megamek.common.SimpleTechLevel;
import megamek.common.bays.CrewQuartersCargoBay;
import megamek.common.bays.EjectionSeatCargoBay;
import megamek.common.bays.FirstClassQuartersCargoBay;
import megamek.common.bays.PillionSeatCargoBay;
import megamek.common.bays.SecondClassQuartersCargoBay;
import megamek.common.bays.StandardSeatCargoBay;
import megamek.common.bays.SteerageQuartersCargoBay;
import megamek.common.enums.Faction;
import megamek.common.enums.TechRating;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.Transporter;
import megamek.common.equipment.enums.FuelType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.Tank;
import megamek.common.units.UnitRole;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.FuelView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.SVBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * Structure tab for support vehicle construction
 */
public class SVStructureTab extends ITab implements SVBuildListener {
    private static final MMLogger LOGGER = MMLogger.create(SVStructureTab.class);

    private RefreshListener refresh = null;
    private JPanel masterPanel;
    private BasicInfoView panBasicInfo;
    private SVChassisView panChassis;
    private MovementView panMovement;
    private FuelView panFuel;
    private SummaryView panSummary;
    private SVChassisModView panChassisMod;
    private SVCrewView panCrew;
    private IconView iconView;

    public SVStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setupPanels();
        add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private Entity getSV() {
        return eSource.getEntity();
    }

    private void setupPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getSV().getConstructionTechAdvancement());
        panChassis = new SVChassisView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panFuel = new FuelView();
        panChassisMod = new SVChassisModView(panBasicInfo);
        panCrew = new SVCrewView();
        iconView = new IconView();
        panSummary = new SummaryView(eSource,
              new UnitTypeSummaryItem(),
              new StructureSummaryItem(),
              new EngineSummaryItem(),
              new FuelSummaryItem(),
              new HeatSinkSummaryItem(),
              new ControlsSummaryItem(),
              new ArmorSummaryItem(),
              new TurretSummaryItem(),
              new PowerAmplifierSummaryItem(),
              new EquipmentSummaryItem());

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        midPanel.setLayout(new GridBagLayout());
        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(panBasicInfo, gbc);
        gbc.gridy++;
        leftPanel.add(iconView, gbc);
        gbc.gridy++;
        leftPanel.add(panChassis, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        midPanel.add(panMovement, gbc);
        gbc.gridy++;
        midPanel.add(panFuel, gbc);
        gbc.gridy++;
        midPanel.add(panSummary, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        rightPanel.add(panChassisMod, gbc);
        gbc.gridy++;
        rightPanel.add(panCrew, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(midPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panFuel.setBorder(BorderFactory.createTitledBorder("Fuel"));
        panChassisMod.setBorder(BorderFactory.createTitledBorder("Chassis Modifications"));
        panCrew.setBorder(BorderFactory.createTitledBorder("Crew and Quarters"));
    }

    public void refresh() {
        removeAllListeners();

        panBasicInfo.setFromEntity(getSV());
        panChassis.setFromEntity(getSV());
        panMovement.setFromEntity(getSV());
        refreshFuel();
        panChassisMod.setFromEntity(getSV());
        panCrew.setFromEntity(getSV());
        panSummary.refresh();
        iconView.setFromEntity(getEntity());

        addAllListeners();
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(Faction techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    private void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panFuel.removeListener(this);
        panChassisMod.removeListener(this);
        panCrew.removeListener(this);
    }

    private void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panFuel.addListener(this);
        panChassisMod.addListener(this);
        panCrew.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    /**
     * Disables controls that cannot be changed when customizing a refit.
     */
    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
        panChassis.setAsCustomization();
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getSV().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshTransport();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getSV().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getSV().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        getEntity().setTechLevel(panBasicInfo.getTechLevel()
              .getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getSV(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        if (!getTechManager().isLegal(TestSupportVehicle.SVType.getVehicleType(getEntity()))) {
            typeChanged(TestSupportVehicle.SVType.WHEELED);
        }
        panChassis.refresh();
        panMovement.setFromEntity(getSV());
        panChassisMod.setFromEntity(getSV());
        refresh.refreshArmor();
        refresh.refreshTransport();
        refresh.refreshPreview();
    }

    @Override
    public void sourceChanged(String source) {
        getSV().setSource(source);
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getSV().isClan()) || (mixed != getSV().isMixedTech())) {
            getSV().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        getSV().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.removeListener(this);
        panMovement.setFromEntity(getSV());
        panMovement.addListener(this);
        panFuel.setFromEntity(getSV());
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        if (null != jumpJet) {
            UnitUtil.removeAllMiscMounted(getSV(), MiscType.F_JUMP_JET);
            getSV().setOriginalJumpMP(0);
            for (int i = 0; i < jumpMP; i++) {
                try {
                    getSV().addEquipment(jumpJet, Tank.LOC_BODY);
                } catch (LocationFullException e) {
                    LOGGER.error("", e);
                }
            }
            panSummary.refresh();
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshSummary();
            refresh.refreshPreview();
            panMovement.removeListener(this);
            panMovement.setFromEntity(getSV());
            panMovement.addListener(this);
        }
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        // Only one type of JJ for vehicles
    }

    @Override
    public void tonnageChanged(double tonnage) {
        boolean wasLarge = getEntity().getWeightClass() == EntityWeightClass.WEIGHT_LARGE_SUPPORT;
        getEntity().setWeight(TestEntity.ceil(tonnage, tonnage < 5 ? Ceil.KILO : Ceil.HALF_TON));
        if (!getEntity().isAero() && !getEntity().getMovementMode().equals(EntityMovementMode.VTOL)) {
            if ((getEntity().getWeightClass() == EntityWeightClass.WEIGHT_LARGE_SUPPORT) != wasLarge) {
                toggleLargeSupport();
            }
        }
        panChassisMod.setFromEntity(getSV());
        panFuel.setFromEntity(getSV());
        panCrew.setFromEntity(getSV());
        refresh.refreshArmor();
        refresh.refreshEquipment();
        refresh.refreshTransport();
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    /**
     * Called when changing to or from large support vee, which may require instantiating a different Entity.
     */
    private void toggleLargeSupport() {
        // fixed wing/airship/satellite/VTOL do not change the number of locations
        if (getEntity().isAero() || getEntity().getMovementMode().equals(EntityMovementMode.VTOL)) {
            return;
        }
        Entity oldEntity = getEntity();
        if (getEntity().getWeightClass() == EntityWeightClass.WEIGHT_LARGE_SUPPORT) {
            eSource.createNewUnit(Entity.ETYPE_LARGE_SUPPORT_TANK, getEntity());
        } else {
            eSource.createNewUnit(Entity.ETYPE_SUPPORT_TANK, getEntity());
        }
        getEntity().setWeight(oldEntity.getWeight());
        getEntity().setMovementMode(oldEntity.getMovementMode());
        panChassis.refresh();
        panSummary.refresh();
        refresh.refreshArmor();
        refresh.refreshEquipment();
        refresh.refreshPreview();
        refresh.refreshBuild();
        refresh.refreshStatus();
    }

    @Override
    public void typeChanged(TestSupportVehicle.SVType type) {
        TestSupportVehicle.SVType oldType = TestSupportVehicle.SVType.getVehicleType(getSV());
        if (!type.equals(oldType)) {
            if (type.equals(TestSupportVehicle.SVType.FIXED_WING)) {
                eSource.createNewUnit(Entity.ETYPE_FIXED_WING_SUPPORT, getSV());
            } else if (type.equals(TestSupportVehicle.SVType.VTOL)) {
                eSource.createNewUnit(Entity.ETYPE_SUPPORT_VTOL, getSV());
            } else if (TestSupportVehicle.SVType.FIXED_WING.equals(oldType)
                  || TestSupportVehicle.SVType.VTOL.equals(oldType)) {
                eSource.createNewUnit(Entity.ETYPE_SUPPORT_TANK, getSV());
            }
            getSV().setMovementMode(type.defaultMovementMode);
            panChassis.setFromEntity(getSV());
            panMovement.setFromEntity(getSV());
            refreshFuel();
            panChassisMod.setFromEntity(getSV());
            panCrew.setFromEntity(getSV());
            panSummary.refresh();
            refresh.refreshAll();
        }
    }

    @Override
    public void structuralTechRatingChanged(TechRating techRating) {
        getSV().setStructuralTechRating(techRating);
        getSV().recalculateTechAdvancement();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void engineChanged(Engine engine) {
        getSV().setEngine(engine);

        if (engine.getEngineType() == Engine.NONE) {
            getSV().setOriginalWalkMP(0);
        }

        // Switching between maglev and non-maglev engines changes movement mode
        if (TestSupportVehicle.SVType.RAIL.equals(TestSupportVehicle.SVType.getVehicleType(getSV()))) {
            getSV().setMovementMode(
                  (engine.getEngineType() == Engine.MAGLEV) ? EntityMovementMode.MAGLEV : EntityMovementMode.RAIL);
        }
        // Make sure the engine tech rating is at least the minimum for the engine type
        if (getSV().getEngineTechRating().getIndex() < engine.getTechRating().getIndex()) {
            getSV().setEngineTechRating(engine.getTechRating());
            getSV().recalculateTechAdvancement();
        }
        // Fixed Wing support vehicles require the prop mod for an electric engine
        if ((TestSupportVehicle.SVType.getVehicleType(getSV()) == TestSupportVehicle.SVType.FIXED_WING)
              && TestSupportVehicle.SVEngine.getEngineType(engine).electric
              && !getSV().hasMisc(MiscType.F_PROP)) {
            setChassisMod(TestSupportVehicle.ChassisModification.PROP.equipment, true);
        }
        if (engine.getEngineType() != Engine.EXTERNAL) {
            setChassisMod(TestSupportVehicle.ChassisModification.EXTERNAL_POWER_PICKUP.equipment, false);
        }
        // The chassis view needs to refresh the available engine rating combobox
        panChassis.removeListener(this);
        panChassis.setFromEntity(getSV());
        panChassis.addListener(this);
        refreshFuel();
        panChassisMod.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshEquipment();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void engineTechRatingChanged(TechRating techRating) {
        getSV().setEngineTechRating(techRating);
        getSV().recalculateTechAdvancement();
        panFuel.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void setChassisMod(EquipmentType mod, boolean installed) {
        final Mounted<?> current = getSV().getMisc().stream().filter(m -> m.getType().equals(mod)).findFirst()
              .orElse(null);
        if (installed && (null == current)) {
            try {
                getSV().addEquipment(mod, getSV().isAero() ? FixedWingSupport.LOC_BODY : Tank.LOC_BODY);
            } catch (LocationFullException e) {
                // This should not be possible since chassis mods don't occupy slots
                LOGGER.error("LocationFullException when adding chassis mod {}", mod.getName());
            }
        } else if (!installed && (null != current)) {
            getSV().getMisc().remove(current);
            getSV().getEquipment().remove(current);
            UnitUtil.removeCriticalSlots(getSV(), current);
            UnitUtil.changeMountStatus(getSV(), current, Entity.LOC_NONE, Entity.LOC_NONE, false);
        }
        if (mod.equals(TestSupportVehicle.ChassisModification.OMNI.equipment)) {
            getEntity().setOmni(installed);
            if (!getEntity().isOmni()) {
                getEntity().getEquipment().forEach(m -> m.setOmniPodMounted(false));
                List<Transporter> podTransports = getEntity().getTransports().stream()
                      .filter(t -> getEntity().isPodMountedTransport(t))
                      .toList();
                podTransports.forEach(t -> {
                    getEntity().removeTransporter(t);
                    getEntity().addTransporter(t, false);
                });
            }
            panChassis.setFromEntity(getEntity());
        } else if (mod.equals(TestSupportVehicle.ChassisModification.ARMORED.equipment)) {
            refresh.refreshArmor();
        }
        if (TestSupportVehicle.SVType.NAVAL.equals(TestSupportVehicle.SVType.getVehicleType(getEntity()))) {
            if (getEntity().hasMisc(MiscType.F_HYDROFOIL)) {
                getEntity().setMovementMode(EntityMovementMode.HYDROFOIL);
            } else if (getEntity().hasMisc(MiscType.F_SUBMERSIBLE)) {
                getEntity().setMovementMode(EntityMovementMode.SUBMARINE);
            } else {
                getEntity().setMovementMode(EntityMovementMode.NAVAL);
            }
        }
        refreshFuel();
        panChassisMod.refresh();
        panSummary.refresh();
        refresh.refreshTransport();
        refresh.refreshStatus();
        refresh.refreshPreview();
        refresh.refreshBuild();
    }

    @Override
    public void turretChanged(int turretConfig) {
        if (!(getSV() instanceof Tank)) {
            return;
        }
        if ((turretConfig != SVBuildListener.TURRET_DUAL)
              && !getTank().hasNoDualTurret()) {
            removeTurret(getTank().getLocTurret2());
            getTank().setHasNoDualTurret(true);
            getTank().setBaseChassisTurret2Weight(Tank.BASE_CHASSIS_TURRET_WT_UNASSIGNED);
        }
        if ((turretConfig == SVBuildListener.TURRET_NONE)
              && !getTank().hasNoTurret()) {
            removeTurret(getTank().getLocTurret());
            getTank().setHasNoTurret(true);
            getTank().setBaseChassisTurretWeight(Tank.BASE_CHASSIS_TURRET_WT_UNASSIGNED);
        }

        if (getTank().hasNoTurret() && (turretConfig != SVBuildListener.TURRET_NONE)) {
            getTank().setHasNoTurret(false);
            getTank().autoSetInternal();
            initTurretArmor(getTank().getLocTurret());
        }
        if (getTank().hasNoDualTurret() && (turretConfig == SVBuildListener.TURRET_DUAL)) {
            getTank().setHasNoDualTurret(false);
            getTank().autoSetInternal();
            initTurretArmor(getTank().getLocTurret2());
        }
        getTank().autoSetInternal();
        panChassis.setFromEntity(getTank());
        refresh.refreshArmor();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void sponsonTurretChanged(boolean installed) {
        final List<Mounted<?>> current = getSV().getMisc().stream()
              .filter(m -> m.getType().hasFlag(MiscType.F_SPONSON_TURRET))
              .collect(Collectors.toList());
        if (installed && current.isEmpty()) {
            try {
                // superheavy FL/FR match L/R
                getSV().addEquipment(EquipmentType.get(EquipmentTypeLookup.SPONSON_TURRET), Tank.LOC_LEFT);
                getSV().addEquipment(EquipmentType.get(EquipmentTypeLookup.SPONSON_TURRET), Tank.LOC_RIGHT);
            } catch (LocationFullException e) {
                // This should not be possible since sponson turrets mods don't occupy slots
                LOGGER.error("LocationFullException when adding sponson turret");
            }
        } else if (!installed) {
            for (Mounted<?> m : getEntity().getEquipment()) {
                m.setSponsonTurretMounted(false);
            }
            for (Mounted<?> sponson : current) {
                if (sponson instanceof MiscMounted) {
                    getSV().getMisc().remove(sponson);
                }

                getSV().getEquipment().remove(sponson);
                UnitUtil.removeCriticalSlots(getSV(), sponson);
                UnitUtil.changeMountStatus(getSV(), sponson, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
        resetSponsonPintleWeight();

        panChassis.setFromEntity(getEntity());
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void pintleTurretChanged(boolean addPintle, final int loc) {
        final Optional<MiscMounted> installedPintle = getSV().getMisc().stream()
              .filter(m -> m.is(EquipmentTypeLookup.PINTLE_TURRET))
              .filter(m -> m.getLocation() == loc)
              .findFirst();
        if (addPintle && installedPintle.isEmpty()) {
            try {
                getSV().addEquipment(EquipmentType.get(EquipmentTypeLookup.PINTLE_TURRET), loc);
            } catch (LocationFullException e) {
                // This should not be possible since sponson turrets mods don't occupy slots
                LOGGER.error("LocationFullException when adding pintle turret");
            }
        } else if (!addPintle && installedPintle.isPresent()) {
            for (Mounted<?> m : getEntity().getEquipment()) {
                if (m.getLocation() == loc) {
                    m.setPintleTurretMounted(false);
                }
            }
            MiscMounted pintle = installedPintle.get();
            getSV().getMisc().remove(pintle);
            getSV().getEquipment().remove(pintle);
            UnitUtil.removeCriticalSlots(getSV(), pintle);
            UnitUtil.changeMountStatus(getSV(), pintle, Entity.LOC_NONE, Entity.LOC_NONE, false);
        }
        resetSponsonPintleWeight();

        panChassis.setFromEntity(getEntity());
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    /**
     * Checks for the presence of sponson or pintle mounts, and if none are found resets the base chassis weight.
     */
    private void resetSponsonPintleWeight() {
        if (getEntity() instanceof Tank) {
            for (Mounted<?> m : getEntity().getMisc()) {
                if (m.getType().hasFlag(MiscType.F_SPONSON_TURRET)
                      || m.getType().hasFlag(MiscType.F_PINTLE_TURRET)) {
                    return;
                }
            }
            getTank().setBaseChassisSponsonPintleWeight(Tank.BASE_CHASSIS_TURRET_WT_UNASSIGNED);
        }
    }

    @Override
    public void turretBaseWtChanged(double turret1, double turret2) {
        if (getSV() instanceof Tank) {
            getTank().setBaseChassisTurretWeight(turret1);
            getTank().setBaseChassisTurret2Weight(turret2);
            panSummary.refresh();
            refresh.refreshStatus();
        }
    }

    @Override
    public void sponsonPintleBaseWtChanged(double turretWeight) {
        if (getSV() instanceof Tank) {
            getTank().setBaseChassisSponsonPintleWeight(turretWeight);
            panSummary.refresh();
            refresh.refreshStatus();
        }
    }

    @Override
    public void fireConChanged(int index) {
        final Mounted<?> current = getSV().getMisc().stream()
              .filter(m -> m.getType().hasFlag(MiscType.F_BASIC_FIRE_CONTROL)
                    || m.getType().hasFlag(MiscType.F_ADVANCED_FIRE_CONTROL))
              .findFirst().orElse(null);
        if (null != current) {
            getSV().getMisc().remove(current);
            getSV().getEquipment().remove(current);
            UnitUtil.removeCriticalSlots(getSV(), current);
            UnitUtil.changeMountStatus(getSV(), current, Entity.LOC_NONE, Entity.LOC_NONE, false);
        }
        EquipmentType eq = null;
        if (index == SVBuildListener.FIRE_CONTROL_BASIC) {
            eq = EquipmentType.get("Basic Fire Control");
        } else if (index == SVBuildListener.FIRE_CONTROL_ADVANCED) {
            eq = EquipmentType.get("Advanced Fire Control");
        }
        if (null != eq) {
            try {
                getSV().addEquipment(eq, getSV().isAero() ? FixedWingSupport.LOC_BODY : Tank.LOC_BODY);
            } catch (LocationFullException e) {
                // This should not be possible since fire control doesn't occupy slots
                LOGGER.error("LocationFullException when adding fire control {}", eq.getName());
            }
        }
        panChassis.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void fireConWtChanged(double weight) {
        getSV().setBaseChassisFireConWeight(weight);
        panSummary.refresh();
        refresh.refreshStatus();
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getEntity());
        refresh.refreshAll();
    }

    @Override
    public void setSeating(int standard, int standardPod,
          int pillion, int pillionPod,
          int ejection, int ejectionPod) {
        // Clear out any existing seating.
        final List<Transporter> current = getSV().getTransports().stream()
              .filter(t -> t instanceof StandardSeatCargoBay)
              .toList();
        for (Transporter t : current) {
            getSV().removeTransporter(t);
        }
        // Create new ones as needed.
        if (standard > 0) {
            getSV().addTransporter(new StandardSeatCargoBay(standard), false);
        }
        if (standardPod > 0) {
            getSV().addTransporter(new StandardSeatCargoBay(standardPod), true);
        }
        if (pillion > 0) {
            getSV().addTransporter(new PillionSeatCargoBay(pillion), false);
        }
        if (pillionPod > 0) {
            getSV().addTransporter(new PillionSeatCargoBay(pillionPod), true);
        }
        if (ejection > 0) {
            getSV().addTransporter(new EjectionSeatCargoBay(ejection), false);
        }
        if (ejectionPod > 0) {
            getSV().addTransporter(new EjectionSeatCargoBay(ejectionPod), true);
        }
        panCrew.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void setQuarters(int firstClass, int firstClassPod,
          int secondClass, int secondClassPod,
          int crew, int crewPod,
          int steerage, int steeragePod) {
        // Clear out any existing standard or pillion seating.
        final List<Transporter> current = getSV().getTransports().stream()
              .filter(t -> (t instanceof FirstClassQuartersCargoBay)
                    || (t instanceof SecondClassQuartersCargoBay)
                    || (t instanceof CrewQuartersCargoBay)
                    || (t instanceof SteerageQuartersCargoBay))
              .toList();
        for (Transporter t : current) {
            getSV().removeTransporter(t);
        }
        // Create new ones as needed.
        if (firstClass > 0) {
            getSV().addTransporter(new FirstClassQuartersCargoBay(firstClass), false);
        }
        if (firstClassPod > 0) {
            getSV().addTransporter(new FirstClassQuartersCargoBay(firstClassPod), true);
        }
        if (secondClass > 0) {
            getSV().addTransporter(new SecondClassQuartersCargoBay(secondClass), false);
        }
        if (secondClassPod > 0) {
            getSV().addTransporter(new SecondClassQuartersCargoBay(secondClassPod), true);
        }
        if (crew > 0) {
            getSV().addTransporter(new CrewQuartersCargoBay(crew), false);
        }
        if (crewPod > 0) {
            getSV().addTransporter(new CrewQuartersCargoBay(crewPod), true);
        }
        if (steerage > 0) {
            getSV().addTransporter(new SteerageQuartersCargoBay(steerage), false);
        }
        if (steeragePod > 0) {
            getSV().addTransporter(new SteerageQuartersCargoBay(steeragePod), true);
        }
        panCrew.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    private void removeTurret(int loc) {
        for (int slot = 0; slot < getTank().getNumberOfCriticalSlots(loc); slot++) {
            getTank().setCritical(loc, slot, null);
        }
        for (Mounted<?> mount : getTank().getEquipment()) {
            if (mount.getLocation() == loc) {
                UnitUtil.changeMountStatus(getTank(), mount,
                      Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
    }

    private void initTurretArmor(int loc) {
        getTank().initializeArmor(0, loc);
        getTank().setArmorTechLevel(
              getTank().getArmorTechLevel(Tank.LOC_FRONT),
              loc);
        getTank().setArmorType(getTank().getArmorType(Tank.LOC_FRONT),
              loc);
    }

    @Override
    public void fuelTonnageChanged(double tonnage) {
        double fuelTons = TestEntity.round(tonnage,
              TestEntity.usesKgStandard(getSV()) ? Ceil.KILO : Ceil.HALF_TON);
        if (getSV().isAero()) {
            getAero().setFuelTonnage(fuelTons);
        } else {
            getTank().setFuelTonnage(fuelTons);
        }
        refreshFuel();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void fuelCapacityChanged(int capacity) {
        if (getEntity().isAero()) {
            getAero().setFuel(capacity);
        } else {
            double tonnage = capacity * getTank().fuelTonnagePer100km() / 100.0;
            if (TestEntity.usesKgStandard(getEntity())) {
                tonnage = TestEntity.round(tonnage, Ceil.KILO);
            } else if (tonnage > getTank().getFuelTonnage()) {
                // Round in the same direction as the change.
                tonnage = TestEntity.ceil(tonnage, Ceil.HALF_TON);
            } else {
                tonnage = TestEntity.floor(tonnage, Ceil.HALF_TON);
            }
            getTank().setFuelTonnage(tonnage);
        }
        panFuel.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void fuelTypeChanged(FuelType fuelType) {
        if (getEntity() instanceof Tank) {
            getTank().setICEFuelType(fuelType);
        }
        panFuel.setFromEntity(getEntity());
    }

    /**
     * Convenience method that removes the fuel if the vehicle does not require fuel mass then refreshes the fuel panel.
     * Changes that can affect this are vehicle type, engine type, and the prop chassis mod.
     */
    private void refreshFuel() {
        if ((getSV() instanceof FixedWingSupport)
              && (((FixedWingSupport) getSV()).kgPerFuelPoint() == 0)) {
            getAero().setFuelTonnage(0);
        } else if ((getSV() instanceof Tank) && (getTank().fuelTonnagePer100km() == 0)) {
            getTank().setFuelTonnage(0);
        }
        panFuel.setFromEntity(getSV());
    }

    @Override
    public void mulIdChanged(int mulId) {
        getEntity().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
