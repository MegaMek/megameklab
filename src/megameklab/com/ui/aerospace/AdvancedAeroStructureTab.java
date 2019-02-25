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
package megameklab.com.ui.aerospace;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import megamek.common.Aero;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Jumpship;
import megamek.common.SimpleTechLevel;
import megamek.common.SpaceStation;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.AdvancedAeroChassisView;
import megameklab.com.ui.view.AeroFuelView;
import megameklab.com.ui.view.AerospaceCrewView;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.GravDeckView;
import megameklab.com.ui.view.HeatSinkView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.listeners.AdvancedAeroBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * @author Neoancient
 *
 */
public class AdvancedAeroStructureTab extends ITab implements AdvancedAeroBuildListener {

    /**
     * 
     */
    private static final long serialVersionUID = 544925067532464071L;
    
    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private AdvancedAeroChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private AeroFuelView panFuel;
    private HeatSinkView panHeat;
    private AerospaceCrewView panCrew;
    private GravDeckView panGravDecks;
    private AdvancedAeroSummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;

    RefreshListener refresh = null;

    public AdvancedAeroStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getJumpship().getConstructionTechAdvancement());
        panChassis = new AdvancedAeroChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new AeroFuelView();
        panHeat = new HeatSinkView(panInfo);
        panCrew = new AerospaceCrewView(panInfo);
        panGravDecks = new GravDeckView();
        panArmorAllocation = new ArmorAllocationView(panInfo, Entity.ETYPE_AERO);
        panSummary = new AdvancedAeroSummaryView(eSource);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
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
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
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
        
        panMovement.setVisible(getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP));
        panSummary.refresh();
        addAllListeners();

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getJumpship().locations(); loc++) {
            for (int slot = 0; slot < getJumpship().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getJumpship().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getJumpship().setCritical(loc, slot, null);
                }
            }
        }
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
    }

    @Override
    public void modelChanged(String model) {
        getJumpship().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
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
        panArmorAllocation.setFromEntity(getJumpship());
        panSummary.refresh();
        refresh.refreshTransport();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getJumpship().setManualBV(manualBV);
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        getJumpship().setHeatType(index);
        getJumpship().setHeatSinks(count);
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
        
        double maxArmor = Math.min(getJumpship().getArmorWeight() + remainingTonnage,
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
        getJumpship().setDesignType(military? Aero.MILITARY : Aero.CIVILIAN);
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void modularChanged(boolean modular) {
        if (getJumpship() instanceof SpaceStation) {
            ((SpaceStation) getJumpship()).setModular(modular);
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
            case AdvancedAeroChassisView.TYPE_JUMPSHIP:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)
                        && !getJumpship().hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
                    return;
                }
                eSource.createNewUnit(Entity.ETYPE_JUMPSHIP, getJumpship());
                break;
            case AdvancedAeroChassisView.TYPE_WARSHIP:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)) {
                    eSource.createNewUnit(Entity.ETYPE_WARSHIP, getJumpship());
                } else if (getJumpship().getDriveCoreType() == Jumpship.DRIVE_CORE_SUBCOMPACT) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_COMPACT);
                } else {
                    return;
                }
                break;
            case AdvancedAeroChassisView.TYPE_SUBCOMPACT:
                if (!getJumpship().hasETypeFlag(Entity.ETYPE_WARSHIP)) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
                    eSource.createNewUnit(Entity.ETYPE_WARSHIP, getJumpship());
                } else if (getJumpship().getDriveCoreType() != Jumpship.DRIVE_CORE_SUBCOMPACT) {
                    getJumpship().setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
                } else {
                    return;
                }
                break;
            case AdvancedAeroChassisView.TYPE_STATION:
                if (getJumpship().hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
                    return;
                }
                eSource.createNewUnit(Entity.ETYPE_SPACE_STATION, getJumpship());
                break;
        }
        refresh();
        refresh.refreshEquipmentTable();
        refresh.refreshBuild();
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
        for (int loc = 0; loc < getJumpship().locations(); loc++) {
            getJumpship().initializeArmor(0, loc);
        }
        
        // divide armor among positions, with more toward the front
        int points = UnitUtil.getArmorPoints(getJumpship(), getJumpship().getLabArmorTonnage())
                + getAero().getSI() * getAero().locations();
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
        getJumpship().initializeArmor(nose, Aero.LOC_NOSE);
        getJumpship().initializeArmor(wing, Aero.LOC_LWING);
        getJumpship().initializeArmor(wing, Aero.LOC_RWING);
        getJumpship().initializeArmor(aft, Aero.LOC_AFT);
        getJumpship().autoSetThresh();

        panArmorAllocation.setFromEntity(getJumpship());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();

    }

    @Override
    public void patchworkChanged(int location, EquipmentType armor) {
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
        UnitUtil.assignQuarters(getJumpship(), officer, standard, secondclass, steerage);
        panCrew.setFromEntity(getJumpship());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void autoAssignQuarters() {
        UnitUtil.autoAssignQuarters(getJumpship());
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

}
