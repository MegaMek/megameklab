/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle.tabs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import megamek.common.Bay;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.SimpleTechLevel;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.Transporter;
import megamek.common.TroopSpace;
import megamek.common.VTOL;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.BayData;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Vehicle.views.SummaryView;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.CVChassisView;
import megameklab.com.ui.view.CVTransportView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.PatchworkArmorView;
import megameklab.com.ui.view.listeners.CVBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements CVBuildListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private RefreshListener refresh = null;
    private JPanel masterPanel;
    private BasicInfoView panBasicInfo; 
    private CVChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private PatchworkArmorView panPatchwork;
    private CVTransportView panTransport;
    
    public StructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getTank().getConstructionTechAdvancement());
        panChassis = new CVChassisView(panBasicInfo);
        panArmor = new MVFArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panArmorAllocation = new ArmorAllocationView(panBasicInfo, Entity.ETYPE_TANK);
        panPatchwork = new PatchworkArmorView(panBasicInfo);
        panSummary = new SummaryView(eSource);
        panTransport = new CVTransportView();
        if (getTank().hasPatchworkArmor()) {
            panArmorAllocation.showPatchwork(true);
        } else {
            panPatchwork.setVisible(false);
        }

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getTank());
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());
        panArmorAllocation.setFromEntity(getTank());
        panPatchwork.setFromEntity(getTank());
        panTransport.setFromEntity(getTank());

        gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panMovement);
        leftPanel.add(Box.createGlue());
        
        midPanel.add(panArmor);
        midPanel.add(panTransport);
        midPanel.add(panSummary);
        midPanel.add(Box.createVerticalGlue());
        
        rightPanel.add(panArmorAllocation);
        rightPanel.add(panPatchwork);

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
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
        panPatchwork.setBorder(BorderFactory.createTitledBorder("Patchwork Armor"));
        panTransport.setBorder(BorderFactory.createTitledBorder("Transport"));
    }

    public void refresh() {
        removeAllListeners();
        
        panBasicInfo.setFromEntity(getTank());
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());
        panArmorAllocation.setFromEntity(getTank());
        panPatchwork.setFromEntity(getTank());
        panTransport.setFromEntity(getTank());

        panSummary.refresh();

        addAllListeners();
    }
    
    public ITechManager getTechManager() {
        return panBasicInfo;
    }
    
    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(int techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
        panArmorAllocation.removeListener(this);
        panPatchwork.removeListener(this);
        panTransport.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
        panArmorAllocation.addListener(this);
        panPatchwork.addListener(this);
        panTransport.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
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

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = walkMP * (int)tonnage - Tank.getSuspensionFactor(panChassis.getMovementMode(), tonnage);
        if (rating < 10) {
            rating = 10;
        }
        int oldRating = getTank().getEngine().getRating();
        if (oldRating != rating) {
            panChassis.setEngineRating(rating);
            Engine engine = panChassis.getEngine();
            if (!engine.engineValid || !panBasicInfo.isLegal(engine)) {
                JOptionPane.showMessageDialog(
                        this, String.format("The required engine rating of %d exceeds the maximum.", rating),
                        "Bad Engine", JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            } else {
                engine.setBaseChassisHeatSinks(-1);
                getTank().setEngine(engine);
            }
        }
        return true;
    }
    
    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getTank().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getTank().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getTank().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getTank().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getTank().isClan()) || (mixed != getTank().isMixedTech())) {
            getTank().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }
    
    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getTank().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (getTank().hasPatchworkArmor()) {
            for (int loc = 0; loc < getTank().locations(); loc++) {
                if (!getTechManager().isLegal(panPatchwork.getArmor(loc))) {
                    getTank().setArmorType(EquipmentType.T_ARMOR_STANDARD, TechConstants.T_INTRO_BOXSET);
                    UnitUtil.resetArmor(getTank(), loc);
                }
            }
        } else if (!getTechManager().isLegal(panArmor.getArmor())) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
        }
        // If we have a large engine, a drop in tech level may make it unavailable and we will need
        // to reduce speed to a legal value.
        if (getTank().getEngine().hasFlag(Engine.LARGE_ENGINE)
                && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            walk = (400 + Tank.getSuspensionFactor(getTank().getMovementMode(), getTank().getWeight()))
                    / (int)getTank().getWeight();
            recalculateEngineRating(walk, getTank().getWeight());
            getTank().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getTank());
            JOptionPane.showMessageDialog(
                    this, String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getTank(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panArmor.refresh();
        panMovement.refresh();
        panArmorAllocation.setFromEntity(getTank());
        panPatchwork.setFromEntity(getTank());
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getTank().setManualBV(manualBV);
    }

    @Override
    public void walkChanged(int walkMP) {
        if (!recalculateEngineRating(walkMP, panChassis.getTonnage())) {
            panChassis.removeListener(this);
            panChassis.setFromEntity(getTank());
            panChassis.addListener(this);
            return;
        }
        getTank().setOriginalWalkMP((getTank().getEngine().getRating() + getTank().getSuspensionFactor())
                / (int)getTank().getWeight());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.removeListener(this);
        panMovement.setFromEntity(getTank());
        panMovement.addListener(this);
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        if (null != jumpJet) {
            UnitUtil.removeAllMiscMounteds(getTank(), MiscType.F_JUMP_JET);
            getTank().setOriginalJumpMP(0);
            for (int i = 0; i < jumpMP; i++) {
                try {
                    getTank().addEquipment(jumpJet, Tank.LOC_BODY);
                } catch (LocationFullException e) {
                    e.printStackTrace();
                }
            }
            panSummary.refresh();
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshPreview();
            panMovement.removeListener(this);
            panMovement.setFromEntity(getTank());
            panMovement.addListener(this);
        }
    }

    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        // Vehicles ignore
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        UnitUtil.removeISorArmorMounts(getTank(), false);
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            getTank().setArmorTechLevel(aTechLevel);
            getTank().setArmorType(at);
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getTank());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getTank().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getTank());
        getTank().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getTank())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());
        double totalTonnage = getTank().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(getTank().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getTank()));
        getTank().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void tonnageChanged(double tonnage) {
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage)) {
            panChassis.removeListener(this);
            panChassis.setFromEntity(getTank());
            panChassis.addListener(this);
            return;
        }
        getTank().setWeight(tonnage);
        getTank().setOriginalWalkMP((getTank().getEngine().getRating() + getTank().getSuspensionFactor())
                / (int)getTank().getWeight());
        getTank().autoSetInternal();
        refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getTank().setOmni(omni);
        if (!omni) {
            getTank().getEngine().setBaseChassisHeatSinks(-1);
        }
        panChassis.removeListener(this);
        panChassis.setFromEntity(getTank());
        panChassis.addListener(this);
        panTransport.setOmni(omni);
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }
    
    @Override
    public void superheavyChanged(boolean superheavy) {
        // Non-VTOLs require creating a new Entity
        if (getTank().getMovementMode() != EntityMovementMode.VTOL) {
            if (superheavy) {
                eSource.createNewUnit(Entity.ETYPE_SUPER_HEAVY_TANK, getTank());
            } else {
                eSource.createNewUnit(Entity.ETYPE_TANK, getTank());
            }
        }
        panChassis.refresh();
        panSummary.refresh();
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshPreview();
        refresh.refreshBuild();
        refresh.refreshStatus();
    }

    @Override
    public void motiveChanged(EntityMovementMode motive) {
        // If we are changing to or from VTOL we need a new Entity
        if ((motive == EntityMovementMode.VTOL)
                && (getTank().getMovementMode() != EntityMovementMode.VTOL)) {
            eSource.createNewUnit(Entity.ETYPE_VTOL, getTank());
        } else if ((motive != EntityMovementMode.VTOL)
                && (getTank().getMovementMode() == EntityMovementMode.VTOL)) {
            if (panChassis.isSuperheavy()) {
                eSource.createNewUnit(Entity.ETYPE_SUPER_HEAVY_TANK, getTank());
            } else {
                eSource.createNewUnit(Entity.ETYPE_TANK, getTank());
            }
        }
        if (!recalculateEngineRating(panMovement.getWalk(), panChassis.getTonnage())) {
            panChassis.removeListener(this);
            panChassis.setFromEntity(getTank());
            panChassis.addListener(this);
            return;
        }
        getTank().setMovementMode(motive);
        panChassis.removeListener(this);
        panChassis.setFromEntity(getTank());
        panChassis.addListener(this);
        panMovement.removeListener(this);
        panMovement.setFromEntity(getTank());
        panMovement.addListener(this);
        panArmor.setFromEntity(getTank());
        panArmorAllocation.setFromEntity(getTank());
        panPatchwork.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void engineChanged(Engine engine) {
        getTank().setEngine(engine);
        panMovement.removeListener(this);
        panMovement.setFromEntity(getTank());
        panMovement.addListener(this);
        refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void turretChanged(int turretConfig) {
        if ((turretConfig != CVChassisView.TURRET_DUAL)
                && !getTank().hasNoDualTurret()) {
            removeTurret(getTank().getLocTurret2());
            getTank().setHasNoDualTurret(true);
            getTank().setBaseChassisTurret2Weight(-1);
        }
        if ((turretConfig == CVChassisView.TURRET_NONE)
                && !getTank().hasNoTurret()) {
            removeTurret(getTank().getLocTurret());
            getTank().setHasNoTurret(true);
            getTank().setBaseChassisTurretWeight(-1);
        }
    
        if (getTank().hasNoTurret() && (turretConfig != CVChassisView.TURRET_NONE)) {
            getTank().setHasNoTurret(false);
            getTank().autoSetInternal();
            initTurretArmor(getTank().getLocTurret());
        }
        if (getTank().hasNoDualTurret() && (turretConfig == CVChassisView.TURRET_DUAL)) {
            getTank().setHasNoDualTurret(false);
            getTank().autoSetInternal();
            initTurretArmor(getTank().getLocTurret2());
        }
        getTank().autoSetInternal();
        panChassis.setFromEntity(getTank());
        panArmorAllocation.setFromEntity(getTank());
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

    @Override
    public void troopSpaceChanged(double fixed, double pod) {
        List<Transporter> toRemove = getTank().getTransports().stream()
                .filter(t -> t instanceof TroopSpace).collect(Collectors.toList());
        toRemove.forEach(t -> getTank().removeTransporter(t));
        double troopTons = Math
                .round((fixed) * 2) / 2.0;
        if (troopTons > 0) {
            getTank().addTransporter(new TroopSpace(troopTons), false);
        }
        troopTons = Math.round(pod * 2) / 2.0;
        if (troopTons > 0) {
            getTank().addTransporter(new TroopSpace(troopTons), true);
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void cargoSpaceChanged(BayData bayType, double fixed, double pod) {
        List<Transporter> toRemove = getTank().getTransports().stream()
                .filter(t -> (t instanceof Bay)
                        && (bayType == BayData.getBayType((Bay) t)))
                        .collect(Collectors.toList());
        toRemove.forEach(t -> getTank().removeTransporter(t));
        double bayTons = Math
                .round((fixed) * 2) / 2.0;
        int lastBay = getTank().getTransportBays().stream().mapToInt(Bay::getBayNumber).max().orElse(0);
        if (bayTons > 0) {
            getTank().addTransporter(bayType.newBay(bayTons, lastBay + 1), false);
            lastBay++;
        }
        bayTons = Math.round(pod * 2) / 2.0;
        if (bayTons > 0) {
            getTank().addTransporter(bayType.newBay(bayTons, lastBay + 1), true);
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getTank());
        panChassis.setFromEntity(getTank());
        panTransport.clearPodSpace();
        panSummary.refresh();
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getTank().initializeArmor(front, location);
        if (panArmor.getArmorType() == EquipmentType.T_ARMOR_PATCHWORK) {
            getTank().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getTank()));
        }
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        int pointsToAllocate = UnitUtil.getArmorPoints(getTank(), getTank().getLabArmorTonnage());

        for (int location = 0; location < getTank().locations(); location++) {
            getTank().initializeArmor(0, location);
        }
        
        // Discount body, as it's not armored
        int numLocations = getTank().locations() - 1;

        // Make sure that the VTOL rotor has the 2 armor it should have
        if (getTank().hasETypeFlag(Entity.ETYPE_VTOL)) {
            getTank().initializeArmor(Math.min(pointsToAllocate, 2), VTOL.LOC_ROTOR);
            pointsToAllocate -= 2;
            numLocations--;
        }
        
        // Determine the percentage of total armor each location should get
        double otherPercent = 1.0 / numLocations;
        double remainingPercent = 1.0 - (otherPercent * (numLocations - 2));
        // Front should be slightly more armored and rear slightly less
        double frontPercent = remainingPercent * 0.6;
        double rearPercent = remainingPercent * 0.4;
        
        // With the percentage of total for each location, assign armor
        int allocatedPoints = 0;
        int rear = Tank.LOC_REAR;
        if (getTank().hasETypeFlag(Entity.ETYPE_SUPER_HEAVY_TANK)) {
            rear = SuperHeavyTank.LOC_REAR;
        }
        for (int location = 1; location < getTank().locations(); location++) {
            if ((getTank().hasETypeFlag(Entity.ETYPE_VTOL)) && (location == VTOL.LOC_ROTOR)) {
                continue;
            }
            int armorToAllocate = 0;
            if (location == Tank.LOC_FRONT) {
                armorToAllocate = (int)(pointsToAllocate * frontPercent);
            } else if (location == rear) {
                armorToAllocate = (int)(pointsToAllocate * rearPercent);
            } else {
                armorToAllocate = (int)(pointsToAllocate * otherPercent);
            }
            getTank().initializeArmor(armorToAllocate, location);
            allocatedPoints += armorToAllocate;
        }
        
        // Because of rounding, may have leftover armor: allocate it to front
        int unallocated = pointsToAllocate - allocatedPoints;
        int currentFrontArmor = getTank().getOArmor(Tank.LOC_FRONT);
        getTank().initializeArmor(currentFrontArmor + unallocated, Tank.LOC_FRONT);

        
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void patchworkChanged(int location, EquipmentType armor) {
        UnitUtil.resetArmor(getAero(), location);

        //TODO: move this construction data out of the ui
        int crits = 0;
        switch (EquipmentType.getArmorType(armor)) {
            case EquipmentType.T_ARMOR_STEALTH_VEHICLE:
            case EquipmentType.T_ARMOR_LIGHT_FERRO:
            case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            case EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO:
            case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
            case EquipmentType.T_ARMOR_REFLECTIVE:
            case EquipmentType.T_ARMOR_REACTIVE:
                crits = 1;
                break;
            case EquipmentType.T_ARMOR_HEAVY_FERRO:
                crits = 2;
                break;
        }
        if (getAero().getEmptyCriticals(location) < crits) {
            JOptionPane .showMessageDialog(
                    null, armor.getName()
                    + " does not fit in location "
                    + getAero().getLocationName(location)
                    + ". Resetting to Standard Armor in this location.",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            getAero().setArmorType(EquipmentType.getArmorType(armor), location);
            getAero().setArmorTechLevel(armor.getTechLevel(getTechManager().getGameYear(), armor.isClan()));
            for (; crits > 0; crits--) {
                try {
                    getAero().addEquipment( new Mounted(getAero(), armor), location, false);
                } catch (LocationFullException ex) {
                }
            }
        }
        panArmor.refresh();
        panArmorAllocation.setFromEntity(getAero());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }
}
