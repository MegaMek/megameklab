/*
 * MegaMekLab - Copyright (C) 2008
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

package megameklab.com.ui.Aero.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.SimpleTechLevel;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Aero.views.ArmorView;
import megameklab.com.ui.Aero.views.SummaryView;
import megameklab.com.ui.view.AeroFuelView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.FighterChassisView;
import megameklab.com.ui.view.HeatSinkView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements 
        BasicInfoView.BasicInfoListener,
        FighterChassisView.ChassisListener,
        HeatSinkView.HeatSinkListener,
        AeroFuelView.FuelListener,
        MovementView.MovementListener,
        MVFArmorView.ArmorListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private JPanel masterPanel;
    private BasicInfoView panInfo;
    private FighterChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private AeroFuelView panFuel;
    private HeatSinkView panHeat;
    private SummaryView panSummary;
    private ArmorView armorView;

    RefreshListener refresh = null;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        armorView = new ArmorView(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new BasicInfoView(getAero().getConstructionTechAdvancement());
        panChassis = new FighterChassisView(panInfo);
        panArmor = new MVFArmorView(panInfo);
        panMovement = new MovementView(panInfo);
        panFuel = new AeroFuelView();
        panHeat = new HeatSinkView(panInfo);
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
        //leftPanel.add(Box.createGlue());
        //leftPanel.add(Box.createVerticalGlue());

        midPanel.add(panMovement);
        midPanel.add(panFuel);
        midPanel.add(panSummary);
        midPanel.add(Box.createHorizontalStrut(300));

        rightPanel.add(panArmor);
        rightPanel.add(armorView);

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
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armorView.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }
    
    public ITechManager getTechManager() {
        return panInfo;
    }

    public void refresh() {
        removeAllListeners();
        
        panInfo.setFromEntity(getAero());
        panChassis.setFromEntity(getAero());
        panHeat.setFromAero(getAero());
        panFuel.setFromEntity(getAero());
        panMovement.setFromEntity(getAero());
        panArmor.setFromEntity(getAero());
        
        panHeat.setVisible(!getAero().hasETypeFlag(Entity.ETYPE_CONV_FIGHTER));
        
        setAeroStructuralIntegrity();

        armorView.refresh();
        panSummary.refresh();
        addAllListeners();

    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.RIGHT);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = TestAero.calculateEngineRating(getAero(), (int)tonnage, walkMP);
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
            for (int slot = 0; slot < getAero().getNumberOfCriticals(loc); slot++) {
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
    }

    public void addAllListeners() {
        panInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panFuel.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armorView.addRefreshedListener(l);
    }

    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {
        /* TODO: Patchwork armor needs support from ArmorView
        if (EquipmentType.T_ARMOR_PATCHWORK == at) {
            boolean isMixed = panInfo.isMixedTech();
            List<EquipmentType> armors = panArmor.getAllArmors();
            List<TechComboBox<EquipmentType>> combos = new ArrayList<>();
            JPanel panel = new JPanel(new GridBagLayout());
            // Start with 1 to skip body
            for (int loc = 0; loc < Aero.LOC_WINGS; loc++) {
                TechComboBox<EquipmentType> cbLoc = new TechComboBox<>(eq -> eq.getName());
                cbLoc.showTechBase(isMixed);
                armors.forEach(a -> cbLoc.addItem(a));
                EquipmentType locArmor = EquipmentType.get(EquipmentType
                        .getArmorTypeName(getAero().getArmorType(loc),
                                TechConstants.isClan(getAero().getArmorTechLevel(loc))));
                cbLoc.setSelectedItem(locArmor);
                combos.add(cbLoc);
                JLabel label = new JLabel(getAero().getLocationName(loc));
                panel.add(label, GBC.std());
                panel.add(cbLoc, GBC.eol());
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            UnitUtil.removeISorArmorMounts(getAero(), false);
            for (int loc = 0; loc < Aero.LOC_WINGS; loc++) {
                EquipmentType armor = (EquipmentType)combos.get(loc).getSelectedItem();
                getAero().setArmorTechLevel(armor.getTechLevel(panInfo.getTechYear()), loc);
                getAero().setArmorType(EquipmentType.getArmorType(armor), loc);
            }
            panArmor.removeListener(this);
            panArmor.setFromEntity(getAero());
            panArmor.addListener(this);
        } else {
         * 
         */
            getAero().setArmorTechLevel(aTechLevel);
            getAero().setArmorType(at);
        //}
    }

    public void setAsCustomization() {
        panInfo.setAsCustomization();
        panChassis.setAsCustomization();
    }

    /**
     * Sets the structural integrity for Aerospace and Conventional fighters.
     * For these units, the SI is equal to the safe thrust rating or 10% of the
     * units tonnage, whichever is greater.  The SI for fighters does not take
     * up any tonnage.
     */
    public void setAeroStructuralIntegrity(){
        int si = (int)Math.max(panChassis.getTonnage() * 0.1,
                panMovement.getWalk());
        getAero().setSI(si);
    }

    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getAero().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getAero().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getAero().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getAero().setSource(source);
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
        if (!getAero().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getAero(), false);
        }
        createArmorMountsAndSetArmorType(getAero().getArmorType(0), getAero().getArmorTechLevel(0));
        // If we have a large engine, a drop in tech level may make it unavailable and we will need
        // to reduce speed to a legal value.
        if (getAero().getEngine().hasFlag(Engine.LARGE_ENGINE)
                && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            if (getAero().isPrimitive()) {
                walk = 400 / (int)(getAero().getWeight() * 1.2);
            } else {
                walk = 400 / (int)getAero().getWeight();
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
        panArmor.refresh();
        panMovement.refresh();
        armorView.resetArmorPoints();
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getAero().setManualBV(manualBV);
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        getAero().setHeatType(index);
        getAero().setHeatSinks(count);
        getAero().setPodHeatSinks(Math.max(0, count
                - panHeat.getBaseCount()));
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinksChanged(EquipmentType hsType, int count) {
        // Not used by aerospace units
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        getAero().getEngine().setBaseChassisHeatSinks(Math.max(0,  count));
        getAero().setPodHeatSinks(getAero().getHeatSinks() - count);
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        if (!getAero().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getAero(), false);
        }
        createArmorMountsAndSetArmorType(at, aTechLevel);
        if (!getAero().hasPatchworkArmor()) {
            armorView.resetArmorPoints();
        }
        
        armorView.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getAero().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        armorView.resetArmorPoints();
        
        armorView.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getAero());
        getAero().setArmorTonnage(maxArmor);
        armorView.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getAero());
        panArmor.addListener(this);
        
        armorView.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getAero())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getAero());
        double totalTonnage = getAero().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(getAero().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getAero()));
        getAero().setArmorTonnage(maxArmor);
        armorView.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getAero());
        panArmor.addListener(this);
        
        armorView.refresh();
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
                omni? Math.max(0, panHeat.getBaseCount()) : -1);
        panHeat.setFromAero(getAero());
        refresh.refreshPreview();
    }

    @Override
    public void vstolChanged(boolean vstol) {
        getAero().setVSTOL(vstol);
    }
    
    @Override
    public void fighterTypeChanged(int type) {
        if ((FighterChassisView.TYPE_AEROSPACE == type)
                && (getAero().getEntityType() != Entity.ETYPE_AERO)) {
            eSource.createNewUnit(Entity.ETYPE_AERO, getAero());
        } else if ((FighterChassisView.TYPE_CONVENTIONAL == type)
                && (getAero().getEntityType() != Entity.ETYPE_CONV_FIGHTER)) {
            eSource.createNewUnit(Entity.ETYPE_CONV_FIGHTER, getAero());
        }
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void engineChanged(Engine engine) {
        // Make sure we keep same number of base heat sinks for omnis
        engine.setBaseChassisHeatSinks(getAero().getEngine()
                .getBaseChassisHeatSinks(false));
        getAero().setEngine(engine);
        panMovement.setFromEntity(getAero());
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
        refresh.refreshAll();
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

}
