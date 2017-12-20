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

package megameklab.com.ui.Dropship.tabs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import megamek.common.Aero;
import megamek.common.Bay;
import megamek.common.CriticalSlot;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.SimpleTechLevel;
import megamek.common.SmallCraft;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestAero.Quarters;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Dropship.views.SummaryView;
import megameklab.com.ui.view.AeroFuelView;
import megameklab.com.ui.view.AerospaceCrewView;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.DropshipChassisView;
import megameklab.com.ui.view.HeatSinkView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.listeners.DropshipBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * 
 * @author Neoancient
 *
 */

public class DropshipStructureTab extends ITab implements DropshipBuildListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;
    
    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private DropshipChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private AeroFuelView panFuel;
    private HeatSinkView panHeat;
    private AerospaceCrewView panCrew;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;

    RefreshListener refresh = null;

    public DropshipStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getSmallCraft().getConstructionTechAdvancement());
        panChassis = new DropshipChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new AeroFuelView();
        panHeat = new HeatSinkView(panInfo);
        panCrew = new AerospaceCrewView(panInfo);
        panArmorAllocation = new ArmorAllocationView(panInfo, Entity.ETYPE_AERO);
        panSummary = new SummaryView(eSource);

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
        
        panInfo.setFromEntity(getSmallCraft());
        panChassis.setFromEntity(getSmallCraft());
        panHeat.setFromAero(getSmallCraft());
        panFuel.setFromEntity(getSmallCraft());
        panMovement.setFromEntity(getSmallCraft());
        panArmor.setFromEntity(getSmallCraft());
        panCrew.setFromEntity(getSmallCraft());
        panArmorAllocation.setFromEntity(getSmallCraft());
        
        panSummary.refresh();
        addAllListeners();

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getSmallCraft().locations(); loc++) {
            for (int slot = 0; slot < getSmallCraft().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getSmallCraft().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getSmallCraft().setCritical(loc, slot, null);
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
    }

    @Override
    public void modelChanged(String model) {
        getSmallCraft().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
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
        panArmorAllocation.setFromEntity(getSmallCraft());
        panSummary.refresh();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getSmallCraft().setManualBV(manualBV);
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        getSmallCraft().setHeatType(index);
        getSmallCraft().setHeatSinks(count);
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
        
        double maxArmor = Math.min(getSmallCraft().getArmorWeight() + remainingTonnage,
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
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void militaryChanged(boolean military) {
        getSmallCraft().setDesignType(military? SmallCraft.MILITARY : SmallCraft.CIVILIAN);
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
        }
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void baseTypeChanged(int type) {
        if ((DropshipChassisView.TYPE_SMALL_CRAFT == type)
                && getSmallCraft().hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            eSource.createNewUnit(Entity.ETYPE_SMALL_CRAFT, getSmallCraft());
        } else if ((DropshipChassisView.TYPE_DROPSHIP == type)
                && !getSmallCraft().hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            eSource.createNewUnit(Entity.ETYPE_DROPSHIP, getSmallCraft());
        }
        refresh();
        refresh.refreshEquipmentTable();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }


    @Override
    public void chassisTypeChanged(int type) {
        if (type == DropshipChassisView.CHASSIS_SPHEROID) {
            getSmallCraft().setSpheroid(true);
            getSmallCraft().setMovementMode(EntityMovementMode.SPHEROID);
        } else {
            getSmallCraft().setSpheroid(false);
            getSmallCraft().setMovementMode(EntityMovementMode.AERODYNE);
        }
        panArmor.setFromEntity(getSmallCraft());
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void siChanged(int si) {
        getSmallCraft().set0SI(si);
        panArmor.setFromEntity(getSmallCraft());
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
        for (int loc = 0; loc < getSmallCraft().locations(); loc++) {
            getSmallCraft().initializeArmor(0, loc);
        }
        
        // divide armor among positions, with more toward the front
        int points = UnitUtil.getArmorPoints(getSmallCraft(), getSmallCraft().getLabArmorTonnage())
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
        getSmallCraft().initializeArmor(nose, Aero.LOC_NOSE);
        getSmallCraft().initializeArmor(wing, Aero.LOC_LWING);
        getSmallCraft().initializeArmor(wing, Aero.LOC_RWING);
        getSmallCraft().initializeArmor(aft, Aero.LOC_AFT);
        getSmallCraft().autoSetThresh();

        panArmorAllocation.setFromEntity(getSmallCraft());
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
        EnumMap<TestAero.Quarters, Integer> sizes = new EnumMap<>(TestAero.Quarters.class);
        for (Bay bay : getSmallCraft().getTransportBays()) {
            Quarters q = TestAero.Quarters.getQuartersForBay(bay);
            if (null != q) {
                sizes.merge(q, (int) bay.getCapacity(), Integer::sum);
            }
        }
        if (sizes.getOrDefault(TestAero.Quarters.FIRST_CLASS, 0) != officer) {
            setQuarters(TestAero.Quarters.FIRST_CLASS, officer);
        }
        if (sizes.getOrDefault(TestAero.Quarters.STANDARD, 0) != standard) {
            setQuarters(TestAero.Quarters.STANDARD, standard);
        }
        if (sizes.getOrDefault(TestAero.Quarters.SECOND_CLASS, 0) != secondclass) {
            setQuarters(TestAero.Quarters.SECOND_CLASS, secondclass);
        }
        if (sizes.getOrDefault(TestAero.Quarters.STEERAGE, 0) != steerage) {
            setQuarters(TestAero.Quarters.STEERAGE, steerage);
        }
        panCrew.setFromEntity(getSmallCraft());
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    private void setQuarters(TestAero.Quarters quarters, int size) {
        List<Bay> toRemove = new ArrayList<>();
        for (Bay bay : getSmallCraft().getTransportBays()) {
            if (TestAero.Quarters.getQuartersForBay(bay) == quarters) {
                toRemove.add(bay);
            }
        }
        for (Bay bay : toRemove) {
            getSmallCraft().removeTransporter(bay);
        }
        getSmallCraft().addTransporter(quarters.newQuarters(size));
    }

    @Override
    public void autoAssignQuarters() {
        int standard = getSmallCraft().getNCrew() - getSmallCraft().getBayPersonnel();
        standard = Math.max(0, standard - getSmallCraft().getNOfficers());
        standard += getSmallCraft().getNPassenger() + getSmallCraft().getNMarines() + getSmallCraft().getNBattleArmor();
        quartersChanged(getSmallCraft().getNOfficers(), standard, getSmallCraft().getNPassenger(), 0);
    }

    @Override
    public void escapeChanged(int lifeBoats, int escapePods) {
        getSmallCraft().setLifeBoats(lifeBoats);
        getSmallCraft().setEscapePods(escapePods);
        refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }


}
