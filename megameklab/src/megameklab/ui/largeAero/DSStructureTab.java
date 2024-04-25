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
package megameklab.ui.largeAero;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import megamek.codeUtilities.MathUtility;
import megamek.common.*;
import megamek.common.equipment.ArmorType;
import megamek.common.verifier.TestEntity;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.*;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.DropshipBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.AeroUtil;
import megameklab.util.UnitUtil;

/**
 * @author Neoancient
 */
public class DSStructureTab extends ITab implements DropshipBuildListener, ArmorAllocationListener {
    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private DSChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private FuelView panFuel;
    private HeatSinkView panHeat;
    private LACrewView panCrew;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private IconView iconView;

    RefreshListener refresh = null;

    public DSStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getSmallCraft().getConstructionTechAdvancement());
        panChassis = new DSChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new FuelView();
        panHeat = new HeatSinkView(panInfo);
        panCrew = new LACrewView(panInfo);
        panArmorAllocation = new ArmorAllocationView(panInfo, Entity.ETYPE_AERO);
        iconView = new IconView();
        panSummary = new SummaryView(eSource,
                new UnitTypeSummaryItem(),
                new StructureSummaryItem(),
                new EngineSummaryItem(),
                new FuelSummaryItem(),
                new HeatsinkSummaryItem(),
                new ControlsSummaryItem(),
                new ArmorSummaryItem(),
                new WeaponsSummaryItem(),
                new AmmoSummaryItem(),
                new MiscEquipmentSummaryItem(),
                new CrewSummaryItem(),
                new TransportSummaryItem(),
                new SpecialsSummaryItem());

        GridBagConstraints gbc;

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
        leftPanel.add(iconView);
        leftPanel.add(panChassis);
        leftPanel.add(panCrew);

        midPanel.add(panHeat);
        midPanel.add(panMovement);
        midPanel.add(panFuel);
        midPanel.add(panSummary);
        midPanel.add(Box.createHorizontalStrut(300));

        rightPanel.add(panArmor);
        rightPanel.add(panArmorAllocation);

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

        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panFuel.setBorder(BorderFactory.createTitledBorder("Fuel"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panCrew.setBorder(BorderFactory.createTitledBorder("Crew and Quarters"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }
    
    public ITechManager getTechManager() {
        return panInfo;
    }
    
    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(int techFaction) {
        panInfo.setTechFaction(techFaction);
    }

    public void refresh() {
        removeAllListeners();
        
        panInfo.setFromEntity(getSmallCraft());
        panChassis.setFromEntity(getSmallCraft());
        panHeat.setFromAero(getSmallCraft());
        panFuel.setFromEntity(getSmallCraft());
        panMovement.setFromEntity(getSmallCraft());
        panArmor.setFromEntity(getSmallCraft());
        panCrew.setFromEntity(getSmallCraft());
        panArmorAllocation.setFromEntity(getSmallCraft());
        iconView.setFromEntity(getEntity());
        
        panSummary.refresh();
        addAllListeners();

    }

    public void removeAllListeners() {
        panInfo.removeListener(this);
        panChassis.removeListener(this);
        panHeat.removeListener(this);
        panFuel.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
        panCrew.removeListener(this);
        panArmorAllocation.removeListener(this);
    }

    public void addAllListeners() {
        panInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panFuel.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
        panCrew.addListener(this);
        panArmorAllocation.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void setAsCustomization() {
        panInfo.setAsCustomization();
        panChassis.setAsCustomization();
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
        // We're going to cheat and recalculate minimum crew values here in case the number of gunners changed.
        panCrew.setFromEntity(getSmallCraft());
    }

    @Override
    public void chassisChanged(String chassis) {
        getSmallCraft().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getSmallCraft().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getSmallCraft().setYear(year);
        if (getSmallCraft().isPrimitive()) {
            getSmallCraft().setOriginalBuildYear(year);
            panChassis.refresh();
            panSummary.refresh();
            // Weight-free heat sinks may change due to change in engine weight.
            panHeat.setFromAero(getSmallCraft());
        }
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getSmallCraft().setSource(source);
    }

    @Override
    public void mulIdChanged(int mulId) {
        getSmallCraft().setMulId(mulId);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getSmallCraft().isClan()) || (mixed != getSmallCraft().isMixedTech())) {
            getSmallCraft().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }
    
    @Override
    public void updateTechLevel() {
        getSmallCraft().setTechLevel(panInfo.getTechLevel().getCompoundTechLevel(panInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getSmallCraft(), panInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panArmor.refresh();
        panHeat.setFromAero(getSmallCraft());
        heatSinksChanged(panHeat.getHeatSinkIndex(), panHeat.getCount());
        panArmorAllocation.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshTransport();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        getSmallCraft().setHeatType(index);
        getSmallCraft().setHeatSinks(count);
        getSmallCraft().setOHeatSinks(count);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        // Only used for omnifighters
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        getSmallCraft().setArmorTechLevel(aTechLevel);
        getSmallCraft().setArmorType(at);
        panArmorAllocation.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getSmallCraft().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getSmallCraft());
        panCrew.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getSmallCraft());
        getSmallCraft().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getSmallCraft());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getSmallCraft())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getSmallCraft());
        double totalTonnage = getSmallCraft().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = MathUtility.clamp(getSmallCraft().getArmorWeight() + remainingTonnage, 0,
                UnitUtil.getMaximumArmorTonnage(getSmallCraft()));
        getSmallCraft().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getSmallCraft());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        getSmallCraft().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        panChassis.setMaxThrust(getSmallCraft().getRunMP());
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getSmallCraft());
        panHeat.setFromAero(getSmallCraft());
        panChassis.setFromEntity(getSmallCraft());
        panFuel.setFromEntity(getSmallCraft());
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // Ignore
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        // Ignore
    }

    @Override
    public void tonnageChanged(double tonnage) {
        getSmallCraft().setWeight(tonnage);
        panChassis.setFromEntity(getSmallCraft());
        panCrew.setFromEntity(getSmallCraft());
        getSmallCraft().autoSetInternal();
        refresh();
        refresh.refreshTransport();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void militaryChanged(boolean military) {
        getSmallCraft().setDesignType(military ? Aero.MILITARY : Aero.CIVILIAN);
        panHeat.setFromAero(getSmallCraft());
        panFuel.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void kfBoomChanged(boolean hasBoom) {
        if (getSmallCraft() instanceof Dropship) {
            if (!hasBoom) {
                ((Dropship) getSmallCraft()).setCollarType(Dropship.COLLAR_NO_BOOM);
            } else if (Dropship.getCollarTA().getProductionDate() > getTechManager().getTechIntroYear()) {
                ((Dropship) getSmallCraft()).setCollarType(Dropship.COLLAR_PROTOTYPE);
            } else {
                ((Dropship) getSmallCraft()).setCollarType(Dropship.COLLAR_STANDARD);
            }
            getEntity().recalculateTechAdvancement();
        }
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void baseTypeChanged(int type) {
        if ((DSChassisView.TYPE_SMALL_CRAFT == type)
                && getSmallCraft().hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            eSource.createNewUnit(Entity.ETYPE_SMALL_CRAFT, getSmallCraft());
        } else if ((DSChassisView.TYPE_DROPSHIP == type)
                && !getSmallCraft().hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            eSource.createNewUnit(Entity.ETYPE_DROPSHIP, getSmallCraft());
        }
        refresh();
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshTransport();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }


    @Override
    public void chassisTypeChanged(int type) {
        if (type == DSChassisView.CHASSIS_SPHEROID) {
            getSmallCraft().setSpheroid(true);
            getSmallCraft().setMovementMode(EntityMovementMode.SPHEROID);
        } else {
            getSmallCraft().setSpheroid(false);
            getSmallCraft().setMovementMode(EntityMovementMode.AERODYNE);
        }
        panArmor.setFromEntity(getSmallCraft());
        panHeat.setFromAero(getSmallCraft());
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void siChanged(int si) {
        getSmallCraft().set0SI(si);
        panArmor.setFromEntity(getSmallCraft());
        // Change in SI can reduce the maximum armor tonnage
        if (getSmallCraft().getLabArmorTonnage() != panArmor.getArmorTonnage()) {
            armorTonnageChanged(panArmor.getArmorTonnage());
        }
        panArmorAllocation.setFromEntity(getSmallCraft());
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
    
    @Override
    public void fuelTonnageChanged(double tonnage) {
        double fuelTons = Math.round(tonnage * 2) / 2.0;
        getSmallCraft().setFuelTonnage(fuelTons);
        panFuel.setFromEntity(getSmallCraft());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void fuelCapacityChanged(int capacity) {
        getAero().setFuel(capacity);
        panFuel.setFromEntity(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getSmallCraft().initializeArmor(front, location);
        getSmallCraft().initializeThresh(location);
        panArmorAllocation.setFromEntity(getSmallCraft());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        // Ignore unarmored system-wide location
        final int ARMOR_FACINGS = getSmallCraft().locations() - 1;
        for (int loc = 0; loc < ARMOR_FACINGS; loc++) {
            getSmallCraft().initializeArmor(0, loc);
        }
        
        // divide armor (in excess of bonus from SI) among positions, with more toward the front
        int bonusPerFacing = (int) UnitUtil.getSIBonusArmorPoints(getSmallCraft()) / ARMOR_FACINGS;
        int points = UnitUtil.getArmorPoints(getSmallCraft(), getSmallCraft().getLabArmorTonnage())
                - bonusPerFacing * 4;
        int nose = (int)Math.floor(points * 0.3);
        int wing = (int)Math.floor(points * 0.25);
        int aft = (int)Math.floor(points * 0.2);
        int remainder = points - nose - wing - wing - aft;
        
        // spread remainder among nose and wings
        switch(remainder % 4) {
            case 1:
                nose++;
                break;
            case 3:
                nose++;
                wing++;
                break;
            case 2:
                wing++;
                break;
        }
        getSmallCraft().initializeArmor(nose + bonusPerFacing, Aero.LOC_NOSE);
        getSmallCraft().initializeArmor(wing + bonusPerFacing, Aero.LOC_LWING);
        getSmallCraft().initializeArmor(wing + bonusPerFacing, Aero.LOC_RWING);
        getSmallCraft().initializeArmor(aft + bonusPerFacing, Aero.LOC_AFT);
        getSmallCraft().autoSetThresh();

        panArmorAllocation.setFromEntity(getSmallCraft());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();

    }

    @Override
    public void patchworkChanged(int location, ArmorType armor) {
        // Cannot have patchwork armor
    }

    @Override
    public void baseCrewChanged(int nCrew) {
        getSmallCraft().setNCrew(nCrew + getSmallCraft().getNGunners() + getSmallCraft().getBayPersonnel());
        // May need to adjust number of officers
        panCrew.setFromEntity(getSmallCraft());
        refresh.refreshPreview();
    }

    @Override
    public void officersChanged(int nOfficers) {
        getSmallCraft().setNOfficers(nOfficers);
        panCrew.setFromEntity(getSmallCraft());
        refresh.refreshPreview();
    }

    @Override
    public void gunnersChanged(int nGunners) {
        getSmallCraft().setNCrew(getSmallCraft().getNCrew() + nGunners - getSmallCraft().getNGunners());
        getSmallCraft().setNGunners(nGunners);
        panCrew.setFromEntity(getSmallCraft());
        refresh.refreshPreview();
    }

    @Override
    public void passengersChanged(int nPassengers) {
        getSmallCraft().setNPassenger(nPassengers);
        refresh.refreshPreview();
    }

    @Override
    public void marinesChanged(int nMarines) {
        getSmallCraft().setNMarines(nMarines);
        refresh.refreshPreview();
    }

    @Override
    public void baMarinesChanged(int nBAMarines) {
        getSmallCraft().setNBattleArmor(nBAMarines);
        refresh.refreshPreview();
    }

    @Override
    public void quartersChanged(int officer, int standard, int secondclass, int steerage) {
        AeroUtil.assignQuarters(getSmallCraft(), officer, standard, secondclass, steerage);
        panCrew.setFromEntity(getSmallCraft());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void autoAssignQuarters() {
        AeroUtil.autoAssignQuarters(getSmallCraft());
        panCrew.setFromEntity(getSmallCraft());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void escapeChanged(int lifeBoats, int escapePods) {
        getSmallCraft().setLifeBoats(lifeBoats);
        getSmallCraft().setEscapePods(escapePods);
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
    }
}
