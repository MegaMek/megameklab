/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.supportvehicle;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.ChassisModView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.SVChassisView;
import megameklab.com.ui.view.listeners.SVBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Structure tab for support vehicle construction
 */
class SVStructureTab extends ITab implements SVBuildListener {

    private RefreshListener refresh = null;
    private JPanel masterPanel;
    private BasicInfoView panBasicInfo;
    private SVChassisView panChassis;
    private MovementView panMovement;
    private SVSummaryView panSummary;
    private ChassisModView panChassisMod;

    SVStructureTab(EntitySource eSource) {
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
        panSummary = new SVSummaryView(eSource);
        panChassisMod = new ChassisModView(panBasicInfo);

        GridBagConstraints gbc;

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createGlue());

        midPanel.add(panMovement);
        midPanel.add(panSummary);
        midPanel.add(Box.createVerticalGlue());

        rightPanel.add(panChassisMod);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(midPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panChassisMod.setBorder(BorderFactory.createTitledBorder("Chassis Modifications"));
    }

    public void refresh() {
        removeAllListeners();

        panBasicInfo.setFromEntity(getSV());
        panChassis.setFromEntity(getSV());
        panMovement.setFromEntity(getSV());
        panChassisMod.setFromEntity(getSV());
        panSummary.refresh();

        addAllListeners();
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    private void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panChassisMod.removeListener(this);
    }

    private void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panChassisMod.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getSV().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getSV().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getSV().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        if (UnitUtil.checkEquipmentByTechLevel(getSV(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panMovement.setFromEntity(getSV());
        panChassisMod.setFromEntity(getSV());
        // TODO: Update armor tab
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
        getSV().setManualBV(manualBV);
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
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        if (null != jumpJet) {
            UnitUtil.removeAllMiscMounteds(getSV(), MiscType.F_JUMP_JET);
            getSV().setOriginalJumpMP(0);
            for (int i = 0; i < jumpMP; i++) {
                try {
                    getSV().addEquipment(jumpJet, Tank.LOC_BODY);
                } catch (LocationFullException e) {
                    e.printStackTrace();
                }
            }
            panSummary.refresh();
            refresh.refreshBuild();
            refresh.refreshStatus();
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
        getSV().setWeight(TestEntity.ceil(tonnage, tonnage < 5 ?
                TestEntity.Ceil.KILO : TestEntity.Ceil.HALFTON));
        panChassisMod.setFromEntity(getSV());
        // TODO: refresh armor
        refresh.refreshEquipmentTable();
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void typeChanged(TestSupportVehicle.SVType type) {
        TestSupportVehicle.SVType oldType = TestSupportVehicle.SVType.getVehicleType(getSV());
        if (!oldType.equals(type)) {
            if (type.equals(TestSupportVehicle.SVType.FIXED_WING)) {
                eSource.createNewUnit(Entity.ETYPE_FIXED_WING_SUPPORT, getSV());
            } else if (type.equals(TestSupportVehicle.SVType.VTOL)) {
                eSource.createNewUnit(Entity.ETYPE_SUPPORT_VTOL, getSV());
            } else if (oldType.equals(TestSupportVehicle.SVType.FIXED_WING)
                    || oldType.equals(TestSupportVehicle.SVType.VTOL)) {
                eSource.createNewUnit(Entity.ETYPE_SUPPORT_TANK, getSV());
            }
            getSV().setMovementMode(type.defaultMovementMode);
            panChassis.setFromEntity(getSV());
            panMovement.setFromEntity(getSV());
            panChassisMod.setFromEntity(getSV());
            panSummary.refresh();
            refresh.refreshEquipmentTable();
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshPreview();
            //TODO: Refresh other views
        }
    }

    @Override
    public void structuralTechRatingChanged(int techRating) {
        getSV().setStructuralTechRating(techRating);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void engineChanged(Engine engine) {
        getSV().setEngine(engine);
        // Make sure the engine tech rating is at least the minimum for the engine type
        if (getSV().getEngineTechRating() < engine.getTechRating()) {
            getSV().setEngineTechRating(engine.getTechRating());
        }
        // Fixed Wing support vehicles require the prop mod for an electric engine
        if ((TestSupportVehicle.SVType.getVehicleType(getSV()) == TestSupportVehicle.SVType.FIXED_WING)
                && TestSupportVehicle.SVEngine.getEngineType(engine).electric
                && !getSV().hasMisc(MiscType.F_PROP)) {
            setChassisMod(TestSupportVehicle.ChassisModification.PROP.equipment, true);
        }
        // MagLev trains cannot use the external power pickup mod.
        if (engine.getEngineType() == Engine.MAGLEV) {
            setChassisMod(TestSupportVehicle.ChassisModification.EXTERNAL_POWER_PICKUP.equipment, false);
        }
        // The chassis view needs to refresh the available engine rating combobox
        panChassis.removeListener(this);
        panChassis.setFromEntity(getSV());
        panChassis.addListener(this);
        panChassisMod.setFromEntity(getSV());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void engineTechRatingChanged(int techRating) {
        getSV().setEngineTechRating(techRating);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void setChassisMod(EquipmentType mod, boolean installed) {
        final Mounted current = getSV().getMisc().stream().filter(m -> m.getType().equals(mod)).findFirst().orElse(null);
        if (installed && (null == current)) {
            try {
                getSV().addEquipment(mod, getSV().isAero() ? FixedWingSupport.LOC_BODY : Tank.LOC_BODY);
            } catch (LocationFullException e) {
                // This should not be possible since chassis mods don't occupy slots
                MegaMekLab.getLogger().error(getClass(), "setChassisMod(EquipmentType, boolean)",
                        "LocationFullException when adding chassis mod " + mod.getName());
            }
        } else if (!installed && (null != current)) {
            getSV().getMisc().remove(current);
            getSV().getEquipment().remove(current);
            UnitUtil.removeCriticals(getSV(), current);
        }
        if (mod.equals(TestSupportVehicle.ChassisModification.OMNI.equipment)) {
            getSV().setOmni(installed);
            panChassis.setFromEntity(getSV());
        }
        panChassisMod.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
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
            getTank().setBaseChassisTurret2Weight(-1);
        }
        if ((turretConfig == SVBuildListener.TURRET_NONE)
                && !getTank().hasNoTurret()) {
            removeTurret(getTank().getLocTurret());
            getTank().setHasNoTurret(true);
            getTank().setBaseChassisTurretWeight(-1);
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
        //TODO: Refresh armor tab
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void turretBaseWtChanged(double turret1, double turret2) {
        getTank().setBaseChassisTurretWeight(turret1);
        getTank().setBaseChassisTurret2Weight(turret2);
        panSummary.refresh();
        refresh.refreshStatus();
    }

    private void removeTurret(int loc) {
        for (int slot = 0; slot < getTank().getNumberOfCriticals(loc); slot++) {
            getTank().setCritical(loc, slot, null);
        }
        for (Mounted mount : getTank().getEquipment()) {
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
}
