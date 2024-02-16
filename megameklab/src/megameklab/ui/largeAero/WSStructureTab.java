/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
import java.util.List;

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
import megameklab.ui.listeners.AdvancedAeroBuildListener;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.AeroUtil;
import megameklab.util.UnitUtil;

/**
 * @author Neoancient
 */
public class WSStructureTab extends ITab implements AdvancedAeroBuildListener, ArmorAllocationListener {
    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private WSChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private FuelView panFuel;
    private HeatSinkView panHeat;
    private LACrewView panCrew;
    private WSGravDeckView panGravDecks;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private IconView iconView;

    RefreshListener refresh = null;

    public WSStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getJumpship().getConstructionTechAdvancement());
        panChassis = new WSChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new FuelView();
        panHeat = new HeatSinkView(panInfo);
        panCrew = new LACrewView(panInfo);
        panGravDecks = new WSGravDeckView();
        iconView = new IconView();
        panArmorAllocation = new ArmorAllocationView(panInfo, Entity.ETYPE_AERO);
        panSummary = new SummaryView(eSource,
                new UnitTypeSummaryItem(),
                new StructureSummaryItem(),
                new EngineSummaryItem(),
                new FuelSummaryItem(),
                new HeatsinkSummaryItem(),
                new ControlsSummaryItem(),
                new LfBatterySummaryItem(),
                new KfDriveSummaryItem(),
                new SailSummaryItem(),
                new ArmorSummaryItem(),
                new WeaponsSummaryItem(),
                new AmmoSummaryItem(),
                new MiscEquipmentSummaryItem(),
                new CrewSummaryItem(),
                new TransportSummaryItem(),
                new HardpointSummaryItem(),
                new GravDeckSummaryItem(),
                new LifeBoatSummaryItem(),
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
        leftPanel.add(panHeat);
        leftPanel.add(panCrew);

        midPanel.add(panMovement);
        panMovement.setVisible(getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP));
        midPanel.add(panFuel);
        midPanel.add(panGravDecks);
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
        panGravDecks.setBorder(BorderFactory.createTitledBorder("Gravity Decks"));
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
        
        panInfo.setFromEntity(getJumpship());
        panChassis.setFromEntity(getJumpship());
        panHeat.setFromAero(getJumpship());
        panFuel.setFromEntity(getJumpship());
        panMovement.setFromEntity(getJumpship());
        panArmor.setFromEntity(getJumpship());
        panCrew.setFromEntity(getJumpship());
        panGravDecks.setFromEntity(getJumpship());
        panArmorAllocation.setFromEntity(getJumpship());
        iconView.setFromEntity(getEntity());
        
        panMovement.setVisible(getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP));
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
        panGravDecks.removeListener(this);
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
        panGravDecks.addListener(this);
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
        panCrew.setFromEntity(getJumpship());
    }

    @Override
    public void chassisChanged(String chassis) {
        getJumpship().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getJumpship().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getJumpship().setYear(year);
        if (getJumpship().isPrimitive()) {
            getJumpship().setOriginalBuildYear(year);
            panChassis.refresh();
            panSummary.refresh();
            // Weight-free heat sinks may change due to change in engine weight.
            panHeat.setFromAero(getJumpship());
        }
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getJumpship().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getJumpship().isClan()) || (mixed != getJumpship().isMixedTech())) {
            getJumpship().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }
    
    @Override
    public void updateTechLevel() {
        getJumpship().setTechLevel(panInfo.getTechLevel().getCompoundTechLevel(panInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getJumpship(), panInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.setFromEntity(getJumpship());
        panArmor.refresh();
        panHeat.setFromAero(getJumpship());
        heatSinksChanged(panHeat.getHeatSinkIndex(), panHeat.getCount());
        panArmorAllocation.setFromEntity(getJumpship());
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
        getJumpship().setHeatType(index);
        getJumpship().setHeatSinks(count);
        getJumpship().setOHeatSinks(count);
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
        getJumpship().setArmorTechLevel(aTechLevel);
        getJumpship().setArmorType(at);
        // recalculate tonnage
        getJumpship().setArmorTonnage(getJumpship().getArmorWeight());
        panArmorAllocation.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getJumpship().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getJumpship());
        panCrew.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getJumpship());
        getJumpship().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getJumpship());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getJumpship())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getJumpship());
        double totalTonnage = getJumpship().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = MathUtility.clamp(getJumpship().getArmorWeight() + remainingTonnage, 0,
                UnitUtil.getMaximumArmorTonnage(getJumpship()));
        getJumpship().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getJumpship());
        panArmor.addListener(this);
        
        panArmorAllocation.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        getJumpship().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        panChassis.setMaxThrust(getJumpship().getRunMP());
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getJumpship());
        panHeat.setFromAero(getJumpship());
        panChassis.setFromEntity(getJumpship());
        panFuel.setFromEntity(getJumpship());
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
        getJumpship().setWeight(tonnage);
        getJumpship().initializeKFIntegrity();
        getJumpship().initializeSailIntegrity();
        panChassis.setFromEntity(getJumpship());
        panCrew.setFromEntity(getJumpship());
        getJumpship().autoSetInternal();
        refresh();
        refresh.refreshTransport();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void lfBatteryChanged(boolean battery) {
        getJumpship().setLF(battery);
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    @Override
    public void militaryChanged(boolean military) {
        getJumpship().setDesignType(military ? Aero.MILITARY : Aero.CIVILIAN);
        panHeat.setFromAero(getJumpship());
        panFuel.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void modularChanged(boolean modular) {
        if (getJumpship() instanceof SpaceStation) {
            ((SpaceStation) getJumpship()).setModularOrKFAdapter(modular);
        }
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void sailChanged(boolean sail) {
        getJumpship().setSail(sail);
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void baseTypeChanged(int type) {
        switch (type) {
            case WSChassisView.TYPE_JUMPSHIP:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)
                        && !getJumpship().hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
                    return;
                }
                eSource.createNewUnit(Entity.ETYPE_JUMPSHIP, getJumpship());
                break;
            case WSChassisView.TYPE_WARSHIP:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)) {
                    eSource.createNewUnit(Entity.ETYPE_WARSHIP, getJumpship());
                } else if (getJumpship().getDriveCoreType() == Jumpship.DRIVE_CORE_SUBCOMPACT) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_COMPACT);
                } else {
                    return;
                }
                break;
            case WSChassisView.TYPE_SUBCOMPACT:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
                    eSource.createNewUnit(Entity.ETYPE_WARSHIP, getJumpship());
                } else if (getJumpship().getDriveCoreType() != Jumpship.DRIVE_CORE_SUBCOMPACT) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
                } else {
                    return;
                }
                break;
            case WSChassisView.TYPE_STATION:
                if (getJumpship().hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
                    return;
                }
                eSource.createNewUnit(Entity.ETYPE_SPACE_STATION, getJumpship());
                break;
        }
        refresh();
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshTransport();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }
    
    @Override
    public void rangeChanged(int range) {
        getJumpship().setJumpRange(range);
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void siChanged(int si) {
        getJumpship().set0SI(si);
        panArmor.setFromEntity(getJumpship());
        // Change in SI can reduce the maximum armor tonnage
        if (getJumpship().getLabArmorTonnage() != panArmor.getArmorTonnage()) {
            armorTonnageChanged(panArmor.getArmorTonnage());
        }
        panArmorAllocation.setFromEntity(getJumpship());
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
    
    @Override
    public void fuelTonnageChanged(double tonnage) {
        double fuelTons = Math.round(tonnage * 2) / 2.0;
        getJumpship().setFuelTonnage(fuelTons);
        panFuel.setFromEntity(getJumpship());
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
        getJumpship().initializeArmor(front, location);
        getJumpship().initializeThresh(location);
        panArmorAllocation.setFromEntity(getJumpship());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        // ignore unarmored system-wide location and warship broadsides
        final int ARMOR_FACINGS = getJumpship() instanceof Warship ?
                getJumpship().locations() - 3 : getJumpship().locations() - 1;
        for (int loc = 0; loc < ARMOR_FACINGS; loc++) {
            getJumpship().initializeArmor(0, loc);
        }
        
        // divide armor (in excess of bonus from SI) among positions, with more toward the front
        int bonusPerFacing = (int) Math.floor(UnitUtil.getSIBonusArmorPoints(getJumpship()) / ARMOR_FACINGS);
        int points = UnitUtil.getArmorPoints(getJumpship(), getJumpship().getLabArmorTonnage())
                - bonusPerFacing * 6;
        int nose = (int)Math.floor(points * 0.22);
        int foresides = (int)Math.floor(points * 0.18);
        int aftsides = (int) Math.floor(points * 0.16);
        int aft = (int)Math.floor(points * 0.10);
        int remainder = points - nose - foresides * 2 - aftsides * 2 - aft;
        
        // spread remainder among nose and fore sides
        switch(remainder % 6) {
            case 1:
                nose++;
                break;
            case 2:
                foresides++;
                break;
            case 3:
                nose++;
                foresides++;
                break;
            case 4:
                nose += 2;
                foresides++;
                break;
            case 5:
                nose += 3;
                foresides++;
                break;
        }
        getJumpship().initializeArmor(nose + bonusPerFacing, Jumpship.LOC_NOSE);
        getJumpship().initializeArmor(foresides + bonusPerFacing, Jumpship.LOC_FRS);
        getJumpship().initializeArmor(foresides + bonusPerFacing, Jumpship.LOC_FLS);
        getJumpship().initializeArmor(aftsides + bonusPerFacing, Jumpship.LOC_ARS);
        getJumpship().initializeArmor(aftsides + bonusPerFacing, Jumpship.LOC_ALS);
        getJumpship().initializeArmor(aft + bonusPerFacing, Jumpship.LOC_AFT);
        getJumpship().autoSetThresh();

        panArmorAllocation.setFromEntity(getJumpship());
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
        getJumpship().setNCrew(nCrew + getJumpship().getNGunners() + getJumpship().getBayPersonnel());
        // May need to adjust number of officers
        panCrew.setFromEntity(getJumpship());
        refresh.refreshPreview();
    }

    @Override
    public void officersChanged(int nOfficers) {
        getJumpship().setNOfficers(nOfficers);
        panCrew.setFromEntity(getJumpship());
        refresh.refreshPreview();
    }

    @Override
    public void gunnersChanged(int nGunners) {
        getJumpship().setNCrew(getJumpship().getNCrew() + nGunners - getJumpship().getNGunners());
        getJumpship().setNGunners(nGunners);
        panCrew.setFromEntity(getJumpship());
        refresh.refreshPreview();
    }

    @Override
    public void passengersChanged(int nPassengers) {
        getJumpship().setNPassenger(nPassengers);
        refresh.refreshPreview();
    }

    @Override
    public void marinesChanged(int nMarines) {
        getJumpship().setNMarines(nMarines);
        refresh.refreshPreview();
    }

    @Override
    public void baMarinesChanged(int nBAMarines) {
        getJumpship().setNBattleArmor(nBAMarines);
        refresh.refreshPreview();
    }

    @Override
    public void quartersChanged(int officer, int standard, int secondclass, int steerage) {
        AeroUtil.assignQuarters(getJumpship(), officer, standard, secondclass, steerage);
        panCrew.setFromEntity(getJumpship());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void autoAssignQuarters() {
        AeroUtil.autoAssignQuarters(getJumpship());
        panCrew.setFromEntity(getJumpship());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void escapeChanged(int lifeBoats, int escapePods) {
        getJumpship().setLifeBoats(lifeBoats);
        getJumpship().setEscapePods(escapePods);
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void gravDecksChanged(List<Integer> deckSizes) {
        getJumpship().getGravDecks().clear();
        getJumpship().getGravDecks().addAll(deckSizes);
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }


    @Override
    public void mulIdChanged(int mulId) {
        getJumpship().setMulId(mulId);
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
    }
}
