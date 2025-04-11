/*
 * Copyright (C) 2009 jtighe (torren@users.sourceforge.net)
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.combatVehicle;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import megamek.codeUtilities.MathUtility;
import megamek.common.*;
import megamek.common.bays.Bay;
import megamek.common.bays.BayData;
import megamek.common.equipment.ArmorType;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestTank;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.ArmorAllocationView;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MVFArmorView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.generalUnit.PatchworkArmorView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.CVBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class CVStructureTab extends ITab implements CVBuildListener, ArmorAllocationListener {
    private static final MMLogger logger = MMLogger.create(CVStructureTab.class);

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
    private IconView iconView;

    public CVStructureTab(EntitySource eSource) {
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
        panTransport = new CVTransportView();
        iconView = new IconView();
        if (getTank().hasPatchworkArmor()) {
            panArmorAllocation.showPatchwork(true);
        } else {
            panPatchwork.setVisible(false);
        }
        panSummary = new SummaryView(eSource,
              new UnitTypeSummaryItem(),
              new StructureSummaryItem(),
              new EngineSummaryItem(),
              new PropulsionSummaryItem(),
              new HeatSinkSummaryItem(),
              new ControlsSummaryItem(),
              new ArmorSummaryItem(),
              new JumpSummaryItem(),
              new TurretSummaryItem(),
              new RearTurretSummaryItem(),
              new SponsonTurretSummaryItem(),
              new PowerAmplifierSummaryItem(),
              new EquipmentSummaryItem());

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getTank());
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());
        panArmorAllocation.setFromEntity(getTank());
        panPatchwork.setFromEntity(getTank());
        panTransport.setFromEntity(getTank());
        iconView.setFromEntity(getEntity());

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(iconView);
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
        gbc.fill = GridBagConstraints.NONE;
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
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
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
        iconView.setFromEntity(getEntity());

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
        for (Mounted<?> mount : getTank().getEquipment()) {
            if (mount.getLocation() == loc) {
                UnitUtil.changeMountStatus(getTank(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
    }

    private void initTurretArmor(int loc) {
        getTank().initializeArmor(0, loc);
        getTank().setArmorTechLevel(getTank().getArmorTechLevel(Tank.LOC_FRONT), loc);
        getTank().setArmorType(getTank().getArmorType(Tank.LOC_FRONT), loc);
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     *
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = walkMP * (int) tonnage - Tank.getSuspensionFactor(panChassis.getMovementMode(), tonnage);
        if (rating < 10) {
            rating = 10;
        } else {
            rating = (int) Math.ceil(rating / 5.0) * 5;
        }
        int oldRating = getTank().getEngine().getRating();
        if (oldRating != rating) {
            panChassis.setEngineRating(rating);
            Engine engine = panChassis.getEngine();
            if (!engine.engineValid || !panBasicInfo.isLegal(engine)) {
                JOptionPane.showMessageDialog(this,
                      String.format("The required engine rating of %d exceeds the maximum.", rating),
                      "Bad Engine",
                      JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            } else {
                engine.setBaseChassisHeatSinks(-1);
                getTank().setEngine(engine);
            }
        }
        return true;
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
        getTank().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshSummary();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getTank().setModel(model);
        refresh.refreshHeader();
        refresh.refreshSummary();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getTank().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getTank().setSource(source);
        refresh.refreshSummary();
        refresh.refreshPreview();
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
        if (panArmor.isPatchwork() && !getTechManager().isLegal(Entity.getPatchworkArmorAdvancement())) {
            panArmor.setPatchwork(false);
            armorTypeChanged(panArmor.getArmorType(), panArmor.getArmorTechConstant());
        }
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
        // If we have a large engine, a drop in tech level may make it unavailable, and we will need to reduce speed
        // to a legal value.
        if (getTank().getEngine().hasFlag(Engine.LARGE_ENGINE) && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            walk = (400 + Tank.getSuspensionFactor(getTank().getMovementMode(), getTank().getWeight())) /
                         (int) getTank().getWeight();
            recalculateEngineRating(walk, getTank().getWeight());
            getTank().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getTank());
            JOptionPane.showMessageDialog(this,
                  String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                  "Bad Engine",
                  JOptionPane.ERROR_MESSAGE);
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
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        if (!recalculateEngineRating(walkMP, panChassis.getTonnage())) {
            panChassis.removeListener(this);
            panChassis.setFromEntity(getTank());
            panChassis.addListener(this);
            return;
        }
        // Some units (e.g. hover) have a minimum engine rating, which may result in a
        // higher MP.
        // Trailers have a minimum of zero even if they have an engine.
        if (getTank().isTrailer() && walkMP == 0) {
            getTank().setOriginalWalkMP(0);
        } else {
            getTank().setOriginalWalkMP((getTank().getEngine().getRating() + getTank().getSuspensionFactor()) /
                                              (int) getTank().getWeight());
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshSummary();
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
                    logger.error("", e);
                }
            }
            panSummary.refresh();
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshSummary();
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
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
            UnitUtil.compactCriticals(getTank());
            getTank().setArmorTechLevel(aTechLevel);
            getTank().setArmorType(at);
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getTank());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmor.setFromEntity(getTank(), true);
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void armorTonnageChanged(double tonnage) {
        getTank().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshSummary();
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
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getTank()).calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());
        double totalTonnage = getTank().getWeight();
        double remainingTonnage = TestEntity.floor(totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);

        double maxArmor = MathUtility.clamp(getTank().getArmorWeight() + remainingTonnage,
              0,
              UnitUtil.getMaximumArmorTonnage(getTank()));
        getTank().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshSummary();
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
        getTank().setOriginalWalkMP((getTank().getEngine().getRating() + getTank().getSuspensionFactor()) /
                                          (int) getTank().getWeight());
        getTank().autoSetInternal();
        refresh();
        refresh.refreshSummary();
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
    public void superheavyChanged(boolean superHeavy) {
        // VTOLs need to have their weight increased or decreased to fit into the new weight range. Non-VTOLs require
        // creating a new Entity
        if (EntityMovementMode.VTOL.equals(getTank().getMovementMode())) {
            double maxStandard = TestTank.maxTonnage(getTank().getMovementMode(), false);
            if (superHeavy) {
                getTank().setWeight(Math.max(getTank().getWeight(), maxStandard + 1.0));
            } else {
                getTank().setWeight(Math.min(getTank().getWeight(), maxStandard));
            }
        } else {
            if (superHeavy) {
                eSource.createNewUnit(Entity.ETYPE_SUPER_HEAVY_TANK, getTank());
            } else {
                eSource.createNewUnit(Entity.ETYPE_TANK, getTank());
            }
        }
        panChassis.setFromEntity(getTank());
        panSummary.refresh();
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshEquipment();
        refresh.refreshSummary();
        refresh.refreshPreview();
        refresh.refreshBuild();
        refresh.refreshStatus();
    }

    @Override
    public void trailerChanged(boolean trailer) {
        getTank().setTrailer(trailer);
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
    }

    @Override
    public void controlSystemsChanged(boolean controlSystems) {
        getTank().setHasNoControlSystems(!controlSystems);
        if (!controlSystems) {
            walkChanged(0);
        }
        panChassis.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void motiveChanged(EntityMovementMode motive) {
        // If we are changing to or from VTOL we need a new Entity
        if ((motive == EntityMovementMode.VTOL) && (getTank().getMovementMode() != EntityMovementMode.VTOL)) {
            eSource.createNewUnit(Entity.ETYPE_VTOL, getTank());
        } else if ((motive != EntityMovementMode.VTOL) && (getTank().getMovementMode() == EntityMovementMode.VTOL)) {
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
        if (getTank().isTrailer() &&
                  !motive.equals(EntityMovementMode.TRACKED) &&
                  !motive.equals(EntityMovementMode.WHEELED)) {
            getTank().setTrailer(false);
            getTank().setHasNoControlSystems(false);
        }
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
        refresh.refreshAll();
    }

    @Override
    public void engineChanged(Engine engine) {
        getTank().setEngine(engine);
        panMovement.removeListener(this);
        panMovement.setFromEntity(getTank());
        panMovement.addListener(this);
        // MP may change due to switching from large engine to type without a large
        // equivalent
        // or trailer removing engine
        if (panMovement.getWalk() != getTank().getOriginalWalkMP()) {
            walkChanged(panMovement.getWalk());
        }
        refreshSummary();
        refresh.refreshEquipment();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void extraSeatsChanged(int seats) {
        getTank().setExtraCrewSeats(seats);
        refresh.refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void turretChanged(int turretConfig) {
        if ((turretConfig != CVChassisView.TURRET_DUAL) && !getTank().hasNoDualTurret()) {
            removeTurret(getTank().getLocTurret2());
            getTank().setHasNoDualTurret(true);
            getTank().setBaseChassisTurret2Weight(-1);
        }
        if ((turretConfig == CVChassisView.TURRET_NONE) && !getTank().hasNoTurret()) {
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
        List<Transporter> toRemove = getTank().getTransports()
                                           .stream()
                                           .filter(t -> t instanceof InfantryCompartment)
                                           .toList();
        toRemove.forEach(t -> getTank().removeTransporter(t));
        double troopTons = Math.round((fixed) * 2) / 2.0;
        if (troopTons > 0) {
            getTank().addTransporter(new InfantryCompartment(troopTons), false);
        }
        troopTons = Math.round(pod * 2) / 2.0;
        if (troopTons > 0) {
            getTank().addTransporter(new InfantryCompartment(troopTons), true);
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void cargoSpaceChanged(BayData bayType, double fixed, double pod) {
        List<Transporter> toRemove = getTank().getTransports()
                                           .stream()
                                           .filter(t -> (t instanceof Bay) && (bayType == ((Bay) t).getBayData()))
                                           .toList();
        toRemove.forEach(t -> getTank().removeTransporter(t));
        double bayTons = Math.round((fixed) * 2) / 2.0;
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
        panArmor.setFromEntity(getTank(), true);
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        int pointsToAllocate = TestEntity.getArmorPoints(getTank());

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
            int armorToAllocate;
            if (location == Tank.LOC_FRONT) {
                armorToAllocate = (int) (pointsToAllocate * frontPercent);
            } else if (location == rear) {
                armorToAllocate = (int) (pointsToAllocate * rearPercent);
            } else {
                armorToAllocate = (int) (pointsToAllocate * otherPercent);
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
    public void patchworkChanged(int location, ArmorType armor) {
        UnitUtil.resetArmor(getTank(), location);

        int crits = armor.getPatchworkSlotsCVFtr();
        if (getTank().getEmptyCriticals(location) < crits) {
            JOptionPane.showMessageDialog(null,
                  armor.getName() +
                        " does not fit in location " +
                        getTank().getLocationName(location) +
                        ". Resetting to Standard Armor in this location.",
                  "Error",
                  JOptionPane.INFORMATION_MESSAGE);
            getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD, location);
            getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
        } else {
            getTank().setArmorType(armor.getArmorType(), location);
            getTank().setArmorTechLevel(armor.getTechLevel(getTechManager().getGameYear(), armor.isClan()));
            for (; crits > 0; crits--) {
                try {
                    getTank().addEquipment(Mounted.createMounted(getTank(), armor), location, false);
                } catch (LocationFullException ignored) {
                }
            }
        }
        getTank().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getTank()));
        panArmor.setFromEntity(getTank(), true);
        panArmorAllocation.setFromEntity(getTank());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void mulIdChanged(int mulId) {
        getTank().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
