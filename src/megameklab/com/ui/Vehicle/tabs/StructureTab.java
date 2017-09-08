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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import megamek.client.ui.GBC;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.SimpleTechLevel;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.TroopSpace;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Vehicle.views.ArmorView;
import megameklab.com.ui.Vehicle.views.SummaryView;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.CVChassisView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements
        BasicInfoView.BasicInfoListener,
        CVChassisView.ChassisListener,
        MovementView.MovementListener,
        MVFArmorView.ArmorListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    RefreshListener refresh = null;
    Dimension maxSize = new Dimension();
    JPanel masterPanel;
    BasicInfoView panBasicInfo; 
    CVChassisView panChassis;
    MVFArmorView panArmor;
    MovementView panMovement;
    SummaryView panSummary;
    
    private ArmorView armor;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        armor = new ArmorView(eSource);
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
        panSummary = new SummaryView(eSource);

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getTank());
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());

        gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panMovement);
        leftPanel.add(Box.createGlue());
        
        rightPanel.add(panArmor);
        rightPanel.add(panSummary);
        rightPanel.add(Box.createVerticalGlue());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(rightPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(armor, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armor.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }

    public void refresh() {
        removeAllListeners();
        
        panBasicInfo.setFromEntity(getTank());
        panChassis.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());

        armor.refresh();
        panSummary.refresh();

        addAllListeners();
    }
    
    public ITechManager getTechManager() {
        return panBasicInfo;
    }
    
    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
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
    
    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {
        if (EquipmentType.T_ARMOR_PATCHWORK == at) {
            boolean isMixed = panBasicInfo.useMixedTech();
            List<EquipmentType> armors = panArmor.getAllArmors();
            List<TechComboBox<EquipmentType>> combos = new ArrayList<>();
            JPanel panel = new JPanel(new GridBagLayout());
            // Start with 1 to skip body
            for (int loc = 1; loc < getTank().locations(); loc++) {
                TechComboBox<EquipmentType> cbLoc = new TechComboBox<>(eq -> eq.getName());
                cbLoc.showTechBase(isMixed);
                armors.forEach(a -> cbLoc.addItem(a));
                EquipmentType locArmor = EquipmentType.get(EquipmentType
                        .getArmorTypeName(getTank().getArmorType(loc),
                                TechConstants.isClan(getTank().getArmorTechLevel(loc))));
                cbLoc.setSelectedItem(locArmor);
                combos.add(cbLoc);
                JLabel label = new JLabel(getTank().getLocationName(loc));
                panel.add(label, GBC.std());
                panel.add(cbLoc, GBC.eol());
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            UnitUtil.removeISorArmorMounts(getTank(), false);
            for (int loc = 0; loc < getTank().locations(); loc++) {
                EquipmentType armor = (EquipmentType)combos.get(loc).getSelectedItem();
                getTank().setArmorTechLevel(armor.getTechLevel(panBasicInfo.getGameYear()), loc);
                getTank().setArmorType(EquipmentType.getArmorType(armor), loc);
            }
            panArmor.removeListener(this);
            panArmor.setFromEntity(getTank());
            panArmor.addListener(this);
        } else {
            getTank().setArmorTechLevel(aTechLevel);
            getTank().setArmorType(at);
        }
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
        if (!getTank().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
        }
        createArmorMountsAndSetArmorType(getTank().getArmorType(0), getTank().getArmorTechLevel(0));
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
        armor.resetArmorPoints();
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
        if (!getTank().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
        }
        createArmorMountsAndSetArmorType(at, aTechLevel);
        if (!getTank().hasPatchworkArmor()) {
            armor.resetArmorPoints();
        }
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getTank().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        armor.resetArmorPoints();

        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getTank());
        getTank().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        armor.refresh();
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
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        armor.refresh();
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
        refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getTank().setOmni(omni);
        if (!omni) {
            getTank().getEngine().setBaseChassisHeatSinks(-1);
            troopSpaceChanged(getTank().getTroopCarryingSpace(), 0);
        }
        panChassis.removeListener(this);
        panChassis.setFromEntity(getTank());
        panChassis.addListener(this);
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
        armor.refresh();
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
        panChassis.setFromEntity(getTank());
        armor.refresh();
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
        getTank().removeAllTransporters();
        if (fixed + pod > 0) {
            double troopTons = Math
                    .round((fixed) * 2) / 2.0;
            getTank().addTransporter(new TroopSpace(troopTons), false);
            if (pod > 0) {
                troopTons = Math.round(pod * 2) / 2.0;
                getTank().addTransporter(new TroopSpace(troopTons), true);
            }
        }
        panSummary.refresh();
        refresh.refreshStatus();
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getTank());
        troopSpaceChanged(getTank().getTroopCarryingSpace()
                - getTank().getPodMountedTroopCarryingSpace(), 0);
        panChassis.setFromEntity(getTank());
        panSummary.refresh();
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }
}
