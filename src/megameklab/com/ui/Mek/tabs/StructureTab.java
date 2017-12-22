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

package megameklab.com.ui.Mek.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import megamek.common.LandAirMech;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadVee;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Mek.views.SummaryView;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.HeatSinkView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MekChassisView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.PatchworkArmorView;
import megameklab.com.ui.view.listeners.MekBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements MekBuildListener {
    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private BasicInfoView panBasicInfo;
    private MekChassisView panChassis;
    private MVFArmorView panArmor;
    private MovementView panMovement;
    private HeatSinkView panHeat;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private PatchworkArmorView panPatchwork;

    RefreshListener refresh = null;
    JPanel masterPanel;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getMech().getConstructionTechAdvancement());
        panChassis = new MekChassisView(panBasicInfo);
        panArmor = new MVFArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panHeat = new HeatSinkView(panBasicInfo);
        panArmorAllocation = new ArmorAllocationView(panBasicInfo, Entity.ETYPE_MECH);
        panPatchwork = new PatchworkArmorView(panBasicInfo);
        panSummary = new SummaryView(eSource);
        if (getMech().hasPatchworkArmor()) {
            panArmorAllocation.showPatchwork(true);
        } else {
            panPatchwork.setVisible(false);
        }

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getMech());
        panChassis.setFromEntity(getMech());
        panArmor.setFromEntity(getMech());
        panMovement.setFromEntity(getMech());
        panHeat.setFromMech(getMech());
        panArmorAllocation.setFromEntity(getMech());
        panPatchwork.setFromEntity(getMech());

        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(panHeat);
        leftPanel.add(Box.createGlue());

        centerPanel.add(panArmor);
        centerPanel.add(panMovement);       
        centerPanel.add(panSummary);
        centerPanel.add(Box.createVerticalGlue());

        rightPanel.add(panArmorAllocation);
        rightPanel.add(panPatchwork);
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
        masterPanel.add(centerPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
        panPatchwork.setBorder(BorderFactory.createTitledBorder("Patchwork Armor"));

    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getMech());
        panChassis.setFromEntity(getMech());
        panArmor.setFromEntity(getMech());
        panHeat.setFromMech(getMech());
        panMovement.setFromEntity(getMech());
        panArmorAllocation.setFromEntity(getMech());
        panPatchwork.setFromEntity(getMech());

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

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(int techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    private void resetSystemCrits() {
        getMech().clearCockpitCrits();
        getMech().clearGyroCrits();
        getMech().clearEngineCrits();
        removeSystemCrits(LandAirMech.LAM_LANDING_GEAR, Mech.LOC_CT);

        int[] ctEngine = getMech().getEngine().getCenterTorsoCriticalSlots(getMech().getGyroType());
        int lastEngine = ctEngine[ctEngine.length - 1];
        for (int slot = 0; slot <= lastEngine; slot++) {
            clearCrit(Mech.LOC_CT, slot);
        }
        for (int slot : getMech().getEngine().getSideTorsoCriticalSlots()) {
            clearCrit(Mech.LOC_RT, slot);
            clearCrit(Mech.LOC_LT, slot);
        }
        getMech().addEngineCrits();
        switch (getMech().getGyroType()) {
            case Mech.GYRO_COMPACT:
                clearCritsForGyro(2);
                getMech().addCompactGyro();
                break;
            case Mech.GYRO_HEAVY_DUTY:
                clearCritsForGyro(4);
                getMech().addHeavyDutyGyro();
                break;
            case Mech.GYRO_XL:
                clearCritsForGyro(6);
                getMech().addXLGyro();
                break;
            case Mech.GYRO_NONE:
                UnitUtil.compactCriticals(getMech(), Mech.LOC_CT);
                break;
            default:
                clearCritsForGyro(4);
                getMech().addGyro();
        }

        switch (getMech().getCockpitType()) {
            case Mech.COCKPIT_COMMAND_CONSOLE:
                clearCritsForCockpit(false, true);
                getMech().addCommandConsole();
                break;
            case Mech.COCKPIT_DUAL:
                clearCritsForCockpit(false, true);
                getMech().addDualCockpit();
                break;
            case Mech.COCKPIT_SMALL:
                clearCritsForCockpit(true, false);
                getMech().addSmallCockpit();
                break;
            case Mech.COCKPIT_INTERFACE:
                clearCritsForCockpit(false, true);
                getMech().addInterfaceCockpit();
                break;
            case Mech.COCKPIT_TORSO_MOUNTED:
            case Mech.COCKPIT_VRRP:
                if (lastEngine + 2 < getMech().getNumberOfCriticals(Mech.LOC_CT)) {
                    clearCrit(Mech.LOC_CT, lastEngine + 1);
                    clearCrit(Mech.LOC_CT, lastEngine + 2);
                }
                clearCrit(Mech.LOC_HEAD, 0);
                clearCrit(Mech.LOC_HEAD, 1);
                if (getMech().getEmptyCriticals(Mech.LOC_LT) < 1) {
                    for (int i = 0; i < getMech().getNumberOfCriticals(Mech.LOC_LT); i++) {
                        if (getMech().getCritical(Mech.LOC_LT, i) != null
                                && getMech().getCritical(Mech.LOC_LT, i).getType() == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mech.LOC_LT, i);
                            break;
                        }
                    }
                }
                if (getMech().getEmptyCriticals(Mech.LOC_RT) < 1) {
                    for (int i = 0; i < getMech().getNumberOfCriticals(Mech.LOC_RT); i++) {
                        if (getMech().getCritical(Mech.LOC_RT, i) != null
                                && getMech().getCritical(Mech.LOC_RT, i).getType() == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mech.LOC_RT, i);
                            break;
                        }
                    }
                }
                getMech().addTorsoMountedCockpit(getMech().getCockpitType() == Mech.COCKPIT_VRRP);
                break;
            case Mech.COCKPIT_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMech().addIndustrialCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mech.COCKPIT_PRIMITIVE:
                clearCritsForCockpit(false, false);
                getMech().addPrimitiveCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_PRIMITIVE);
                break;
            case Mech.COCKPIT_PRIMITIVE_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMech().addIndustrialPrimitiveCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_COMMERCIAL);
                break;
            default:
                clearCritsForCockpit(false, false);
                getMech().addCockpit();
        }
        // For LAMs we want to put the landing gear in the first available slot after the engine and gyro.
        if (getMech().hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            int lgSlot = 10;
            for (int i = 0; i < getMech().getNumberOfCriticals(Mech.LOC_CT); i++) {
                final CriticalSlot slot = getMech().getCritical(Mech.LOC_CT, i);
                if ((null == slot) || (slot.getType() == CriticalSlot.TYPE_EQUIPMENT)
                        || ((slot.getIndex() != Mech.SYSTEM_ENGINE) && (slot.getIndex() != Mech.SYSTEM_GYRO))) {
                    lgSlot = i;
                    break;
                }
            }
            CriticalSlot crit = new CriticalSlot(CriticalSlot.TYPE_SYSTEM,
                    LandAirMech.LAM_LANDING_GEAR);
            getMech().removeCriticals(Mech.LOC_CT, crit);
            clearCrit(Mech.LOC_CT, lgSlot);
            getMech().setCritical(Mech.LOC_CT, lgSlot, crit);
        }
        refresh.refreshBuild();
    }

    /**
     * Removes equipment placed in head locations that are needed for a cockpit. For most cockpit
     * types, this is all but the fourth slot.
     * 
     * @param small If true, only clears the first four slots.
     * @param dual  If true, removes all equipment mounted in the head.
     */
    private void clearCritsForCockpit(boolean small, boolean dual) {
        for (int slot = 0; slot < (small?4:6); slot++) {
            if ((slot == 3) && !dual) {
                continue;
            }
            clearCrit(Mech.LOC_HEAD, slot);
        }
    }
    
    private void clearCritsForGyro(int numSlots) {
        for (int i = 3; i < 3 + numSlots; i++) {
            clearCrit(Mech.LOC_CT, i);
            getMech().setCritical(Mech.LOC_CT, i, null);
        }
    }

    /**
     * Removes equipment placed in the given critical slot to clear the space for a system critical
     */
    private void clearCrit(int loc, int slotNum) {
        final CriticalSlot crit = getMech().getCritical(loc, slotNum);
        Mounted mounted = null;
        if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
            mounted = crit.getMount();
        }
        if (mounted == null) {
            return;
        }
        UnitUtil.removeCriticals(getMech(), mounted);
        if (crit.getMount2() != null) {
            UnitUtil.removeCriticals(getMech(), crit.getMount2());
        }

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getMech());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            UnitUtil.changeMountStatus(getMech(), mounted, Entity.LOC_NONE, Entity.LOC_NONE,
                    false);
            if (crit.getMount2() != null) {
                UnitUtil.changeMountStatus(getMech(), crit.getMount2(), Entity.LOC_NONE, Entity.LOC_NONE,
                        false);
            }
        }

    }
    
    private void removeSystemCrits(int systemType, int loc) {
        for (int slot = 0; slot < getMech().getNumberOfCriticals(loc); slot++) {
            CriticalSlot cs = getMech().getCritical(loc, slot);

            if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                continue;
            }

            if (cs.getIndex() == systemType) {
                getMech().setCritical(loc, slot, null);
            }
        }
    }

    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panHeat.removeListener(this);
        panArmor.removeListener(this);
        panMovement.removeListener(this);
        panArmorAllocation.removeListener(this);
        panPatchwork.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panArmor.addListener(this);
        panMovement.addListener(this);
        panArmorAllocation.addListener(this);
        panPatchwork.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public boolean isQuad() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_STANDARD)
                && (panChassis.getMotiveTypeIndex() == MekChassisView.MOTIVE_TYPE_QUAD);
    }

    public boolean isTripod() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_STANDARD)
                && (panChassis.getMotiveTypeIndex() == MekChassisView.MOTIVE_TYPE_TRIPOD);
    }

    public boolean isLAM() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_LAM);
    }

    public boolean isQuadVee() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_QUADVEE);
    }

    private void createISMounts(EquipmentType structure) {
        int isCount = 0;
        getMech().setStructureType(EquipmentType.getStructureType(structure));
        getMech().setStructureTechLevel(structure.getStaticTechLevel().getCompoundTechLevel(structure.isClan()));

        isCount = structure.getCriticals(getMech());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMech().addEquipment(
                        new Mounted(getMech(), structure),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = walkMP * (int)tonnage;
        if (getMech().isPrimitive()) {
            rating = (int)Math.ceil((rating * 1.2) / 5.0) * 5; 
        }
        int oldRating = getMech().getEngine().getRating();
        if (oldRating != rating) {
            panChassis.setEngineRating(rating);
            Engine engine = panChassis.getEngine();
            if (!engine.engineValid || !panBasicInfo.isLegal(engine)) {
                JOptionPane.showMessageDialog(
                        this, String.format("The required engine rating of %d exceeds the maximum.", rating),
                        "Bad Engine", JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            } else if ((tonnage <= 100)
                    && !hasCTSpace(engine, getMech().getGyroType(), getMech().getCockpitType())) {
                JOptionPane.showMessageDialog(
                        this, "There is not enough space in the center torso for the required engine.",
                        "Bad Engine", JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            } else {
                engine.setBaseChassisHeatSinks(getMech().getEngine()
                        .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks()));
                getMech().setEngine(engine);
                UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
            }
        }
        return true;
    }

    private boolean hasCTSpace(Engine engine, int gyroType, int cockpitType) {
        if (getMech().isSuperHeavy()) {
            return true;
        }
        int crits = 10;
        if (engine.getEngineType() == Engine.COMPACT_ENGINE) {
            crits -= 3;
        } else if (engine.hasFlag(Engine.LARGE_ENGINE)) {
            crits += 2;
        }
        if (gyroType == Mech.GYRO_COMPACT) {
            crits -= 2;
        } else if (gyroType == Mech.GYRO_XL) {
            crits += 2;
        }
        if ((cockpitType == Mech.COCKPIT_TORSO_MOUNTED)
                || (cockpitType == Mech.COCKPIT_VRRP)) {
            crits += 2;
        }
        return crits <= 12;
    }

    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {
        getMech().setArmorTechLevel(aTechLevel);
        getMech().setArmorType(at);
        final EquipmentType armor = EquipmentType.get(EquipmentType.getArmorTypeName(at,
                TechConstants.isClan(aTechLevel)));
        int armorCount = armor.getCriticals(getMech());

        if (armorCount < 1) {
            return;
        }
        // auto-place stealth crits
        if (getMech().getArmorType(0) == EquipmentType.T_ARMOR_STEALTH) {
            Mounted mount = UnitUtil.createSpreadMounts(
                    getMech(),
                    EquipmentType.get(EquipmentType.getArmorTypeName(
                            getMech().getArmorType(0), false)));
            if (mount == null) {
                JOptionPane.showMessageDialog(null,
                        "Stealth Armor does not fit in location.",
                        "Resetting to Standard Armor",
                        JOptionPane.INFORMATION_MESSAGE);
                getMech().setArmorType(EquipmentType.T_ARMOR_STANDARD);
                getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                panArmor.setFromEntity(getMech());
            }
        } else {
            for (; armorCount > 0; armorCount--) {
                try {
                    getMech().addEquipment(new Mounted(getMech(),
                            armor), Entity.LOC_NONE, false);
                } catch (Exception ex) {
                }
            }
        }
    }

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
        getMech().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getMech().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getMech().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getMech().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getMech().isClan()) || (mixed != getMech().isMixedTech())) {
            getMech().setMixedTech(mixed);
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
        getMech().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (!getMech().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getMech(), false);
        }
        // If we have a large engine, a drop in tech level may make it unavailable and we will need
        // to reduce speed to a legal value.
        if (getMech().getEngine().hasFlag(Engine.LARGE_ENGINE)
                && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            if (getMech().isPrimitive()) {
                walk = 400 / (int)(getMech().getWeight() * 1.2);
            } else {
                walk = 400 / (int)getMech().getWeight();
            }
            recalculateEngineRating(walk, getMech().getWeight());
            getMech().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getMech());
            JOptionPane.showMessageDialog(
                    this, String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getMech(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panHeat.refresh();
        panArmor.refresh();
        panMovement.refresh();
        panArmorAllocation.setFromEntity(getMech());
        panPatchwork.setFromEntity(getMech());
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getMech().setManualBV(manualBV);
    }

    @Override
    public void tonnageChanged(double tonnage) {
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage)) {
            panChassis.setFromEntity(getMech());
            return;
        }
        boolean changedSuperHeavyStatus = getMech().isSuperHeavy() != tonnage > 100;

        if (changedSuperHeavyStatus) {
            // if we switch from being superheavy to not being superheavy,
            // remove crits
            for (Mounted mount : getMech().getEquipment()) {
                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                    UnitUtil.removeCriticals(getMech(), mount);
                    UnitUtil.changeMountStatus(getMech(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
                }
            }
        }
        getMech().setWeight(tonnage);
        // Force recalculation of walk MP. Set from chassis panel in case superheavy flag changed
        getMech().setEngine(panChassis.getEngine());
        getMech().autoSetInternal();
        if (getMech().isSuperHeavy()) {
            getMech().setOriginalJumpMP(0);
        }
        if (changedSuperHeavyStatus) {
            // Internal structure crits may change
            UnitUtil.removeISorArmorMounts(getMech(), true);
            createISMounts(panChassis.getStructure());
            resetSystemCrits();
            panMovement.setFromEntity(getMech());
        }
        refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getMech().setOmni(omni);
        getMech().getEngine().setBaseChassisHeatSinks(
                omni? Math.max(0, panHeat.getBaseCount()) : -1);
        panHeat.setFromMech(getMech());
        UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
        refresh.refreshPreview();
    }

    @Override
    public void typeChanged(int baseType, int motiveType, long etype) {
        boolean industrial = false;
        switch (baseType) {
            case MekChassisView.BASE_TYPE_INDUSTRIAL:
                industrial = true;
                //fall through
            case MekChassisView.BASE_TYPE_STANDARD:
                boolean primitive = getMech().isPrimitive();
                if (motiveType == MekChassisView.MOTIVE_TYPE_BIPED) {
                    if  (((getMech().getEntityType() & Entity.ETYPE_BIPED_MECH) == 0)
                            || ((getMech().getEntityType() & Entity.ETYPE_LAND_AIR_MECH) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_BIPED_MECH, primitive, industrial, getMech());
                    }
                } else if (motiveType == MekChassisView.MOTIVE_TYPE_QUAD) {
                    if  (((getMech().getEntityType() & Entity.ETYPE_QUAD_MECH) == 0)
                            || ((getMech().getEntityType() & Entity.ETYPE_QUADVEE) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_QUAD_MECH, primitive, industrial, getMech());
                    }
                } else if ((getMech().getEntityType() & Entity.ETYPE_TRIPOD_MECH) == 0) {
                    eSource.createNewUnit(Entity.ETYPE_TRIPOD_MECH, primitive, industrial, getMech());
                }
                break;
            case MekChassisView.BASE_TYPE_LAM:
                if (getMech() instanceof LandAirMech) {
                    ((LandAirMech)getMech()).setLAMType(motiveType);
                } else {
                    eSource.createNewUnit(Entity.ETYPE_LAND_AIR_MECH, getMech());
                }
                break;
            case MekChassisView.BASE_TYPE_QUADVEE:
                if (getMech() instanceof QuadVee) {
                    if (motiveType != ((QuadVee)getMech()).getMotiveType()) {
                        Optional<Mounted> mount = getMech().getMisc().stream()
                                .filter(m -> m.getType().hasFlag(MiscType.F_TRACKS))
                                .findAny();
                        if (mount.isPresent()) {
                            UnitUtil.removeMounted(getMech(), mount.get());
                        }

                        if (motiveType == QuadVee.MOTIVE_WHEEL) {
                            ((QuadVee)getMech()).setMotiveType(QuadVee.MOTIVE_WHEEL);
                            UnitUtil.createSpreadMounts(getMech(), EquipmentType.get("Wheels"));
                        } else {
                            ((QuadVee)getMech()).setMotiveType(QuadVee.MOTIVE_TRACK);
                            UnitUtil.createSpreadMounts(getMech(), EquipmentType.get("Tracks"));
                        }
                    }
                } else {
                    eSource.createNewUnit(Entity.ETYPE_QUADVEE, getMech());
                }
                break;
        }
        if (getMech().isIndustrial() != industrial) {
            getMech().setStructureType(industrial?
                    EquipmentType.T_STRUCTURE_INDUSTRIAL : EquipmentType.T_STRUCTURE_STANDARD);
        }

        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void structureChanged(EquipmentType structure) {
        UnitUtil.removeISorArmorMounts(getMech(), true);
        createISMounts(structure);
        refreshSummary();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void engineChanged(Engine engine) {
        if (!hasCTSpace(engine, panChassis.getGyroType(), panChassis.getCockpitType())) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this engine.",
                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setEngine(getMech().getEngine());
            panChassis.addListener(this);
        } else {
            // Make sure we keep same number of base heat sinks for omnis
            engine.setBaseChassisHeatSinks(getMech().getEngine()
                    .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks()));
            getMech().setEngine(engine);
            resetSystemCrits();
            UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
            panMovement.setFromEntity(getMech());
            refreshSummary();
            refresh.refreshPreview();
            refresh.refreshStatus();
        }
    }

    @Override
    public void gyroChanged(int gyroType) {
        if (!hasCTSpace(panChassis.getEngine(), gyroType, panChassis.getCockpitType())) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this gyro.",
                    "Bad Gyro", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setGyroType(getMech().getGyroType());
            panChassis.addListener(this);
        } else {
            getMech().setGyroType(gyroType);
            resetSystemCrits();
        }
        refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void cockpitChanged(int cockpitType) {
        if (!hasCTSpace(panChassis.getEngine(), panChassis.getGyroType(), cockpitType)) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this cockpit.",
                    "Bad Gyro", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setCockpitType(getMech().getCockpitType());
            panChassis.addListener(this);
        } else {
            getMech().setCockpitType(cockpitType);
            resetSystemCrits();
            refreshSummary();
            refresh.refreshPreview();
            refresh.refreshStatus();
        }
    }

    @Override
    public void enhancementChanged(EquipmentType enhancement) {
        UnitUtil.removeEnhancements(getMech());
        if (null != enhancement) {
            if (enhancement.hasFlag(MiscType.F_MASC)) {
                Mounted mount = new Mounted(getMech(), enhancement);
                try {
                    getMech().addEquipment(mount, Entity.LOC_NONE, false);
                } catch (LocationFullException lfe) {
                    // this can't happen, we add to Entity.LOC_NONE
                }
            } else {
                UnitUtil.createSpreadMounts(getMech(), enhancement);
            }
        }
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void fullHeadEjectChanged(boolean eject) {
        getMech().setFullHeadEject(eject);
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getMech());
        refresh.refreshAll();
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        // Method for ASFs
    }

    @Override
    public void heatSinksChanged(EquipmentType hsType, int count) {
        // if we have the same type of heat sink, then we should not remove the
        // existing heat sinks
        int currentSinks = UnitUtil.countActualHeatSinks(getMech());
        if (getMech().hasWorkingMisc(hsType.getInternalName())) {
            if (count < currentSinks) {
                UnitUtil.removeHeatSinks(getMech(), currentSinks - count);
            } else if (count > currentSinks) {
                UnitUtil.addHeatSinkMounts(getMech(), count - currentSinks,
                        hsType);
            }
        } else {
            UnitUtil.removeHeatSinks(getMech(), count);
            UnitUtil.addHeatSinkMounts(getMech(), count, hsType);
        }
        getMech().resetSinks();
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        getMech().getEngine().setBaseChassisHeatSinks(
                Math.max(0, count));
        UnitUtil.updateAutoSinks(getMech(), panHeat.getHeatSinkType().hasFlag(MiscType.F_COMPACT_HEAT_SINK));
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        UnitUtil.removeISorArmorMounts(getMech(), false);
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            createArmorMountsAndSetArmorType(at, aTechLevel);
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getMech());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmorAllocation.setFromEntity(getMech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorTonnageChanged(double tonnage) {
        getMech().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getMech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getMech());
        getMech().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMech());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getMech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getMech())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getMech());
        double totalTonnage = getMech().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);

        double maxArmor = Math.min(getMech().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getMech()));
        getMech().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMech());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getMech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        if (!recalculateEngineRating(walkMP, panChassis.getTonnage())) {
            panMovement.setFromEntity(getMech());
            return;
        }
        getMech().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getMech());
        panHeat.setFromMech(getMech());
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // Don't set jumpMP for UMU.
        if (null == jumpJet) {
            getMech().setOriginalJumpMP(0);
            jumpMP = 0;
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET) || jumpJet.hasFlag(MiscType.F_JUMP_BOOSTER)) {
            getMech().setOriginalJumpMP(jumpMP);
        } else {
            getMech().setOriginalJumpMP(0);
        }
        List<Mounted> jjs = getMech().getMisc().stream()
                .filter(m -> m.getType() == jumpJet)
                .collect(Collectors.toList());
        if (jumpJet.hasFlag(MiscType.F_JUMP_BOOSTER)) {
            if (!getMech().hasWorkingMisc(MiscType.F_JUMP_BOOSTER)) {
                UnitUtil.createSpreadMounts(getMech(), jumpJet);
            }
        } else {
            while (jjs.size() > jumpMP) {
                UnitUtil.removeMounted(getMech(), jjs.remove(jjs.size() - 1));
            }
            while (jumpMP > jjs.size()) {
                try {
                    UnitUtil.addMounted(getMech(), new Mounted(getMech(), jumpJet),
                            Entity.LOC_NONE, false);
                } catch (LocationFullException e) {
                    // Adding to LOC_NONE
                }
                jumpMP--;
            }
        }
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getMech());
    }

    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        List<Mounted> jjs = getMech().getMisc().stream()
                .filter(m -> m.getType().hasFlag(MiscType.F_JUMP_JET)
                        || m.getType().hasFlag(MiscType.F_UMU)
                        || m.getType().hasFlag(MiscType.F_JUMP_BOOSTER))
                .filter(m -> m.getType() != jumpJet)
                .collect(Collectors.toList());
        jjs.forEach(jj -> UnitUtil.removeMounted(getMech(), jj));
        jumpChanged(panMovement.getJump(), jumpJet);
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getMech().initializeArmor(front, location);
        if (getMech().hasRearArmor(location)) {
            getMech().initializeRearArmor(rear, location);
        }
        if (panArmor.getArmorType() == EquipmentType.T_ARMOR_PATCHWORK) {
            getMech().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getMech()));
        }
        panArmorAllocation.setFromEntity(getMech());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        double pointsToAllocate = UnitUtil.getArmorPoints(getMech(), getMech().getLabArmorTonnage());
        double maxArmor = UnitUtil.getMaximumArmorPoints(getMech());
        if (pointsToAllocate > maxArmor) {
            pointsToAllocate = maxArmor;
        }
        double percent = pointsToAllocate / maxArmor;
        int headMaxArmor = 9;
        if (getMech().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        // put 5 times the percentage of total possible armor into the head
        int headArmor = (int) Math.min(Math.floor(percent * headMaxArmor * 5), headMaxArmor);
        getMech().initializeArmor(headArmor, Mech.LOC_HEAD);
        pointsToAllocate -= headArmor;
        maxArmor -= headMaxArmor;
        // recalculate percentage for remainder
        percent = pointsToAllocate / maxArmor;
        for (int location = 0; location < getMech().locations(); location++) {
            double IS = (getMech().getInternal(location) * 2);
            double allocate = Math.min(IS * percent, pointsToAllocate);
            switch (location) {
                case Mech.LOC_HEAD:
                    break;
                case Mech.LOC_CT:
                case Mech.LOC_LT:
                case Mech.LOC_RT:
                    int rear = (int) Math.floor(allocate * .25);
                    int front = (int) Math.ceil(allocate * .75);
                    // Make sure rounding doesn't add an additional point to this location,
                    // which could cause us to run out of armor before we get to the end.
                    if (rear + front > allocate) {
                        if (front > rear * 3) {
                            front--;
                        } else {
                            rear--;
                        }
                    }
                    pointsToAllocate -= rear;
                    pointsToAllocate -= front;
                    getMech().initializeArmor(front, location);
                    getMech().initializeRearArmor(rear, location);
                    break;
                default:
                    getMech().initializeArmor((int) allocate, location);
                    pointsToAllocate -= (int) allocate;
                    break;
            }
        }
        allocateLeftoverPoints(pointsToAllocate);

        panArmorAllocation.setFromEntity(getMech());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    /**
     * allocate any leftover points one-by-one
     *
     * @param points
     *            the amount of points left over
     */
    private void allocateLeftoverPoints(double points) {
        int headMaxArmor = 9;
        if (getMech().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        while (points >= 1) {
            // if two or more are left, add armor to symmetrical locations,
            // to torso, legs, arms, in that order
            if (points >= 2) {
                if (((getMech().getOArmor(Mech.LOC_LT) + getMech().getOArmor(Mech.LOC_LT,
                        true)) < (getMech().getOInternal(Mech.LOC_LT) * 2))
                        && ((getMech().getOArmor(Mech.LOC_RT) + getMech().getOArmor(
                                Mech.LOC_RT, true)) < (getMech()
                                        .getOInternal(Mech.LOC_RT) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LT) + 1,
                            Mech.LOC_LT);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RT) + 1,
                            Mech.LOC_RT);
                    points -= 2;
                } else if ((getMech().getOArmor(Mech.LOC_LLEG) < (getMech()
                        .getOInternal(Mech.LOC_LLEG) * 2))
                        && (getMech().getOArmor(Mech.LOC_RLEG) < (getMech()
                                .getOInternal(Mech.LOC_RLEG) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LLEG) + 1,
                            Mech.LOC_LLEG);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RLEG) + 1,
                            Mech.LOC_RLEG);
                    points -= 2;
                } else if ((getMech().getOArmor(Mech.LOC_LARM) < (getMech()
                        .getOInternal(Mech.LOC_LARM) * 2))
                        && (getMech().getOArmor(Mech.LOC_RARM) < (getMech()
                                .getOInternal(Mech.LOC_RARM) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LARM) + 1,
                            Mech.LOC_LARM);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RARM) + 1,
                            Mech.LOC_RARM);
                    points -= 2;
                }
                // otherwise, first add to the head, and then even out uneven
                // allocation
            } else if (getMech().getOArmor(Mech.LOC_HEAD) < headMaxArmor) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_HEAD) + 1,
                        Mech.LOC_HEAD);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LT) < getMech()
                    .getOArmor(Mech.LOC_RT)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LT) + 1,
                        Mech.LOC_LT);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RT) < getMech()
                    .getOArmor(Mech.LOC_LT)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RT) + 1,
                        Mech.LOC_RT);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RARM) < getMech()
                    .getOArmor(Mech.LOC_LARM)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RARM) + 1,
                        Mech.LOC_RARM);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LARM) < getMech()
                    .getOArmor(Mech.LOC_RARM)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LARM) + 1,
                        Mech.LOC_LARM);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RLEG) < getMech()
                    .getArmor(Mech.LOC_LLEG)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RLEG) + 1,
                        Mech.LOC_RLEG);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LLEG) < getMech()
                    .getOArmor(Mech.LOC_RLEG)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LLEG) + 1,
                        Mech.LOC_LLEG);
                points--;
                // if nothing is uneven, add to the CT
            } else if (((getMech().getOArmor(Mech.LOC_CT) + getMech().getOArmor(
                    Mech.LOC_CT, true)) < (getMech().getOInternal(Mech.LOC_CT) * 2))) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_CT) + 1,
                        Mech.LOC_CT);
                points--;
            }
            // if only one is left, and head and CT have max, remove one from CT
            // so symmetric locations can get extra, unless they are already at
            // max
            if (points == 1) {
                if ((getMech().getOArmor(Mech.LOC_HEAD) == headMaxArmor)
                        && ((getMech().getOArmor(Mech.LOC_CT) + getMech().getOArmor(
                                Mech.LOC_CT, true)) == (getMech()
                                        .getOInternal(Mech.LOC_CT) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_CT) - 1,
                            Mech.LOC_CT);
                    points++;
                }
            }
            // if all locations have max, return
            boolean toReturn = true;
            for (int location = 0; location < getMech().locations(); location++) {
                double is = (getMech().getInternal(location) * 2);
                switch (location) {
                    case Mech.LOC_HEAD:
                        int headPoints = 3;
                        if (getMech().isSuperHeavy()) {
                            headPoints = 4;
                        }
                        if ((is + headPoints) > getMech().getOArmor(location)) {
                            toReturn = false;
                        }
                        break;
                    case Mech.LOC_CT:
                    case Mech.LOC_LT:
                    case Mech.LOC_RT:
                        if (is > (getMech().getOArmor(location) + getMech().getOArmor(
                                location, true))) {
                            toReturn = false;
                        }
                        break;
                    default:
                        if (is > getMech().getOArmor(location)) {
                            toReturn = false;
                        }
                        break;
                }
            }
            if (toReturn) {
                return;
            }
        }
    }

    @Override
    public void patchworkChanged(int location, EquipmentType armor) {
        UnitUtil.resetArmor(getMech(), location);

        //TODO: move this construction data out of the ui
        int crits = 0;
        switch (EquipmentType.getArmorType(armor)) {
            case EquipmentType.T_ARMOR_STEALTH:
            case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                crits = 2;
                break;
            case EquipmentType.T_ARMOR_HEAVY_FERRO:
                crits = 3;
                break;
            case EquipmentType.T_ARMOR_LIGHT_FERRO:
                crits = 1;
                break;
            case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            case EquipmentType.T_ARMOR_REFLECTIVE:
            case EquipmentType.T_ARMOR_REACTIVE:
                if (armor.isClan()) {
                    crits = 1;
                } else {
                    crits = 2;
                }
                break;
        }
        if (getMech().getEmptyCriticals(location) < crits) {
            JOptionPane .showMessageDialog(
                    null, armor.getName()
                    + " does not fit in location "
                    + getMech().getLocationName(location)
                    + ". Resetting to Standard Armor in this location.",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            getMech().setArmorType(EquipmentType.getArmorType(armor), location);
            getMech().setArmorTechLevel(armor.getTechLevel(getTechManager().getGameYear(), armor.isClan()));
            for (; crits > 0; crits--) {
                try {
                    getMech().addEquipment( new Mounted(getMech(), armor), location, false);
                } catch (LocationFullException ex) {
                }
            }
        }
        panArmor.refresh();
        panArmorAllocation.setFromEntity(getMech());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

}