/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import megamek.codeUtilities.MathUtility;
import megamek.common.CriticalSlot;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.bays.Bay;
import megamek.common.enums.Faction;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.Transporter;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.InfantryCompartment;
import megamek.common.units.UnitRole;
import megamek.common.verifier.BayData;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.ArmorAllocationView;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.FuelView;
import megameklab.ui.generalUnit.HeatSinkView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MVFArmorView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.generalUnit.PatchworkArmorView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.AeroBuildListener;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class ASStructureTab extends ITab implements AeroBuildListener, ArmorAllocationListener {
    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private ASChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private FuelView panFuel;
    private HeatSinkView panHeat;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private PatchworkArmorView panPatchwork;
    private ASTransportView panTransport;
    private IconView iconView;

    RefreshListener refresh = null;

    public ASStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getAero().getConstructionTechAdvancement());
        panChassis = new ASChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new FuelView();
        panHeat = new HeatSinkView(panInfo);
        panArmorAllocation = new ArmorAllocationView(panInfo, Entity.ETYPE_AERO);
        panPatchwork = new PatchworkArmorView(panInfo);
        panTransport = new ASTransportView();
        iconView = new IconView();
        if (getAero().hasPatchworkArmor()) {
            panArmorAllocation.showPatchwork(true);
        } else {
            panPatchwork.setVisible(false);
        }
        panSummary = new SummaryView(eSource,
              new UnitTypeSummaryItem(),
              new StructureSummaryItem(),
              new EngineSummaryItem(),
              new CockpitSummaryItem(),
              new FuelSummaryItem(),
              new HeatSinkSummaryItem(),
              new ControlsSummaryItem(),
              new ArmorSummaryItem(),
              new WeaponsSummaryItem(),
              new AmmoSummaryItem(),
              new MiscEquipmentSummaryItem(),
              new OtherSummaryItem());

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

        midPanel.add(panMovement);
        midPanel.add(panFuel);
        midPanel.add(panSummary);
        midPanel.add(Box.createHorizontalStrut(300));

        rightPanel.add(panArmor);
        rightPanel.add(panTransport);
        rightPanel.add(panArmorAllocation);
        rightPanel.add(panPatchwork);

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

        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panFuel.setBorder(BorderFactory.createTitledBorder("Fuel"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
        panPatchwork.setBorder(BorderFactory.createTitledBorder("Patchwork Armor"));
        panTransport.setBorder(BorderFactory.createTitledBorder("Transport"));
    }

    public ITechManager getTechManager() {
        return panInfo;
    }

    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(Faction techFaction) {
        panInfo.setTechFaction(techFaction);
    }

    public void refresh() {
        removeAllListeners();

        panInfo.setFromEntity(getAero());
        panChassis.setFromEntity(getAero());
        panHeat.setFromAero(getAero());
        panFuel.setFromEntity(getAero());
        panMovement.setFromEntity(getAero());
        panArmor.setFromEntity(getAero());
        panArmorAllocation.setFromEntity(getAero());
        panPatchwork.setFromEntity(getAero());
        panTransport.setFromEntity(getAero());
        iconView.setFromEntity(getEntity());

        panHeat.setVisible(!getAero().hasETypeFlag(Entity.ETYPE_CONV_FIGHTER));

        setAeroStructuralIntegrity();

        panSummary.refresh();
        addAllListeners();
    }

    public JLabel createLabel(String text, Dimension maxSize) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        setFieldSize(label, maxSize);
        return label;
    }

    @Override
    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     *
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = TestAero.calculateEngineRating(getAero(), (int) tonnage, walkMP);
        int oldRating = getAero().getEngine().getRating();
        if (oldRating != rating) {
            panChassis.setEngineRating(rating);
            Engine engine = panChassis.getEngine();
            if (!engine.engineValid || !panInfo.isLegal(engine)) {
                JOptionPane.showMessageDialog(
                      this, String.format("The required engine rating of %d exceeds the maximum.", rating),
                      "Bad Engine", JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            }
        }
        return true;
    }

    public void removeSystemCrits(int systemType) {
        for (int loc = 0; loc < getAero().locations(); loc++) {
            for (int slot = 0; slot < getAero().getNumberOfCriticalSlots(loc); slot++) {
                CriticalSlot cs = getAero().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getAero().setCritical(loc, slot, null);
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
        panArmorAllocation.removeListener(this);
        panPatchwork.removeListener(this);
        panTransport.removeListener(this);
    }

    public void addAllListeners() {
        panInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panFuel.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
        panArmorAllocation.addListener(this);
        panPatchwork.addListener(this);
        panTransport.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void setAsCustomization() {
        panInfo.setAsCustomization();
        panChassis.setAsCustomization();
    }

    /**
     * Sets the structural integrity for Aerospace and Conventional fighters. For these units, the SI is equal to the
     * safe thrust rating or 10% of the units tonnage, whichever is greater.  The SI for fighters does not take up any
     * tonnage.
     */
    public void setAeroStructuralIntegrity() {
        int si = (int) Math.max(panChassis.getTonnage() * 0.1, panMovement.getWalk());
        getAero().setSI(si);
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getAero().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getAero().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getAero().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getAero().setSource(source);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getAero().isClan()) || (mixed != getAero().isMixedTech())) {
            getAero().setMixedTech(mixed);
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
        getAero().setTechLevel(panInfo.getTechLevel().getCompoundTechLevel(panInfo.useClanTechBase()));
        if (panArmor.isPatchwork() && !getTechManager().isLegal(Entity.getPatchworkArmorAdvancement())) {
            panArmor.setPatchwork(false);
            armorTypeChanged(panArmor.getArmorType(), panArmor.getArmorTechConstant());
        }
        if (getAero().hasPatchworkArmor()) {
            for (int loc = 0; loc < getAero().locations(); loc++) {
                if (!getTechManager().isLegal(panPatchwork.getArmor(loc))) {
                    getAero().setArmorType(EquipmentType.T_ARMOR_STANDARD, TechConstants.T_INTRO_BOX_SET);
                    UnitUtil.resetArmor(getAero(), loc);
                }
            }
        } else if (!getTechManager().isLegal(panArmor.getArmor())) {
            UnitUtil.removeISorArmorMounts(getAero(), false);
        }
        // If we have a large engine, a drop in tech level may make it unavailable, and we will need
        // to reduce speed to a legal value.
        if (getAero().getEngine().hasFlag(Engine.LARGE_ENGINE) && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            if (getAero().isPrimitive()) {
                walk = 400 / (int) (getAero().getWeight() * 1.2);
            } else {
                walk = 400 / (int) getAero().getWeight();
            }
            if (!panChassis.isConventional()) {
                walk += 2;
            }
            recalculateEngineRating(walk, getAero().getWeight());
            getAero().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getAero());
            JOptionPane.showMessageDialog(
                  this, String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                  "Bad Engine", JOptionPane.ERROR_MESSAGE);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getAero(), panInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panHeat.refresh();
        heatSinksChanged(panHeat.getHeatSinkIndex(), panHeat.getCount());
        panArmor.refresh();
        panMovement.refresh();
        panArmorAllocation.setFromEntity(getAero());
        panPatchwork.setFromEntity(getAero());
        addAllListeners();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        getAero().setHeatType(index);
        getAero().setOHeatSinks(count);
        getAero().setHeatSinks(count);
        if (getAero().isOmni()) {
            getAero().setPodHeatSinks(Math.max(0, count - panHeat.getBaseCount()));
        }
        panHeat.setFromAero(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        getAero().getEngine().setBaseChassisHeatSinks(Math.max(0, count));
        getAero().setPodHeatSinks(getAero().getHeatSinks() - count);
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            UnitUtil.removeISorArmorMounts(getAero(), false);
            UnitUtil.compactCriticalSlots(getAero());
            getAero().setArmorTechLevel(aTechLevel);
            getAero().setArmorType(at);
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getAero());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmor.setFromEntity(getAero(), true);
        panArmorAllocation.setFromEntity(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorTonnageChanged(double tonnage) {
        getAero().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getAero());
        getAero().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getAero());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getAero()).calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getAero());
        double totalTonnage = getAero().getWeight();
        double remainingTonnage = TestEntity.floor(totalTonnage - currentTonnage, Ceil.HALF_TON);

        double maxArmor = MathUtility.clamp(getAero().getArmorWeight() + remainingTonnage, 0,
              UnitUtil.getMaximumArmorTonnage(getAero()));
        getAero().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getAero());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getAero());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        if (!recalculateEngineRating(walkMP, panChassis.getTonnage())) {
            panMovement.setFromEntity(getAero());
            return;
        }
        getAero().setEngine(panChassis.getEngine());
        setAeroStructuralIntegrity();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getAero());
        panHeat.setFromAero(getAero());
        panChassis.setFromEntity(getAero());
        panFuel.setFromEntity(getAero());
        refresh.refreshSummary();
        refresh.refreshPreview();
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
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage)) {
            panChassis.setFromEntity(getAero());
            return;
        }
        getAero().setWeight(tonnage);
        // Force recalculation of walk MP
        getAero().setEngine(panChassis.getEngine());
        setAeroStructuralIntegrity();
        panChassis.setFromEntity(getAero());
        getAero().autoSetInternal();
        refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getAero().setOmni(omni);
        getAero().getEngine().setBaseChassisHeatSinks(
              omni ? Math.max(0, panHeat.getBaseCount()) : -1);
        panHeat.setFromAero(getAero());
        panTransport.setOmni(omni);
        refresh.refreshPreview();
    }

    @Override
    public void vstolChanged(boolean vstol) {
        getAero().setVSTOL(vstol);
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    @Override
    public void fighterTypeChanged(int type) {
        if ((ASChassisView.TYPE_AEROSPACE == type)
              && (getAero().getEntityType() != Entity.ETYPE_AERO)) {
            eSource.createNewUnit(Entity.ETYPE_AERO, getAero());
        } else if ((ASChassisView.TYPE_CONVENTIONAL == type)
              && (getAero().getEntityType() != Entity.ETYPE_CONV_FIGHTER)) {
            eSource.createNewUnit(Entity.ETYPE_CONV_FIGHTER, getAero());
        }
        refresh();
        refresh.refreshAll();
    }

    @Override
    public void engineChanged(Engine engine) {
        // Make sure we keep same number of base heat sinks for OmniMeks
        engine.setBaseChassisHeatSinks(getAero().getEngine()
              .getBaseChassisHeatSinks(false));
        getAero().setEngine(engine);
        panMovement.setFromEntity(getAero());
        panFuel.setFromEntity(getAero());
        refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void cockpitChanged(int cockpitType) {
        getAero().setCockpitType(cockpitType);
        refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getAero());
        panTransport.clearPodSpace();
        refresh.scheduleRefresh();
    }

    /**
     * Notify of a change in the size of any infantry compartment
     *
     * @param fixed The weight in tons of the infantry compartment
     * @param pod   The weight in tons of any pod-mounted infantry compartment
     */
    @Override
    public void troopSpaceChanged(double fixed, double pod) {
        List<Transporter> toRemove = getAero().getTransports().stream()
              .filter(t -> t instanceof InfantryCompartment).toList();
        toRemove.forEach(t -> getAero().removeTransporter(t));
        double troopTons = Math
              .round((fixed) * 2) / 2.0;
        if (troopTons > 0) {
            getAero().addTransporter(new InfantryCompartment(troopTons), false);
        }
        troopTons = Math.round(pod * 2) / 2.0;
        if (troopTons > 0) {
            getAero().addTransporter(new InfantryCompartment(troopTons), true);
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    /**
     * Notify of a change in the size of a cargo bay
     *
     * @param bayType The type of bay
     * @param fixed   The size of a fixed bay
     * @param pod     The size of a pod-mounted bay
     */
    @Override
    public void cargoSpaceChanged(BayData bayType, double fixed, double pod) {
        List<Transporter> toRemove = getAero().getTransports().stream()
              .filter(t -> (t instanceof Bay)
                    && (bayType == BayData.getBayType((Bay) t)))
              .toList();
        toRemove.forEach(t -> getAero().removeTransporter(t));
        double bayTons = Math
              .round((fixed) * 2) / 2.0;
        int lastBay = getAero().getTransportBays().stream().mapToInt(Bay::getBayNumber).max().orElse(0);
        if (bayTons > 0) {
            getAero().addTransporter(bayType.newBay(bayTons, lastBay + 1), false);
            lastBay++;
        }
        bayTons = Math.round(pod * 2) / 2.0;
        if (bayTons > 0) {
            getAero().addTransporter(bayType.newBay(bayTons, lastBay + 1), true);
        }
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void fuelTonnageChanged(double tonnage) {
        double fuelTons = Math.round(tonnage * 2) / 2.0;
        getAero().setFuelTonnage(fuelTons);
        panFuel.setFromEntity(getAero());
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
        getAero().initializeArmor(front, location);
        getAero().initializeThresh(location);
        if (panArmor.getArmorType() == EquipmentType.T_ARMOR_PATCHWORK) {
            getAero().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getAero()));
        }
        panArmor.setFromEntity(getAero(), true);
        panArmorAllocation.setFromEntity(getAero());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        for (int loc = 0; loc < getAero().locations(); loc++) {
            getAero().initializeArmor(0, loc);
        }

        // divide armor among positions, with more toward the front
        int points = TestEntity.getArmorPoints(getAero());
        int nose = (int) Math.floor(points * 0.3);
        int wing = (int) Math.floor(points * 0.25);
        int aft = (int) Math.floor(points * 0.2);
        int remainder = points - nose - wing - wing - aft;

        // spread remainder among nose and wings
        switch (remainder % 4) {
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
        getAero().initializeArmor(nose, Aero.LOC_NOSE);
        getAero().initializeArmor(wing, Aero.LOC_LEFT_WING);
        getAero().initializeArmor(wing, Aero.LOC_RIGHT_WING);
        getAero().initializeArmor(aft, Aero.LOC_AFT);
        getAero().autoSetThresh();

        panArmorAllocation.setFromEntity(getAero());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();

    }

    @Override
    public void patchworkChanged(int location, ArmorType armor) {
        UnitUtil.resetArmor(getAero(), location);

        int crits = armor.getPatchworkSlotsCVFtr();
        if (getAero().getEmptyCriticalSlots(location) < crits) {
            JOptionPane.showMessageDialog(null,
                  String.format("%s does not fit in location %s. Resetting to Standard Armor in this location.",
                        armor.getName(), getAero().getLocationName(location)),
                  "Error", JOptionPane.INFORMATION_MESSAGE);
            getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD, location);
            getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOX_SET);
        } else {
            getAero().setArmorType(armor.getArmorType(), location);
            getAero().setArmorTechLevel(armor.getTechLevel(getTechManager().getGameYear(), armor.isClan()));
            for (; crits > 0; crits--) {
                try {
                    getAero().addEquipment(Mounted.createMounted(getAero(), armor), location, false);
                } catch (LocationFullException ignored) {
                }
            }
        }
        getAero().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getAero()));
        panArmor.setFromEntity(getAero());
        panArmorAllocation.setFromEntity(getAero());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }


    @Override
    public void mulIdChanged(int mulId) {
        getAero().setMulId(mulId);
        refresh.refreshSummary();

    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
