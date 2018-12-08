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
package megameklab.com.ui.protomek;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
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

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.IArmorState;
import megamek.common.ITechManager;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.BAProtoArmorView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.ProtomekChassisView;
import megameklab.com.ui.view.listeners.ProtomekBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * Structure tab for protomechs
 * 
 * @author Neoancient
 *
 */
public class ProtomekStructureTab extends ITab implements ProtomekBuildListener {

    private static final long serialVersionUID = 6498316429919711881L;
    
    private BasicInfoView panBasicInfo;
    private ProtomekChassisView panChassis;
    private BAProtoArmorView panArmor;
    private MovementView panMovement;
    private ProtomekSummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;

    RefreshListener refresh = null;
    JPanel masterPanel;

    public ProtomekStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getProtomech().getConstructionTechAdvancement());
        panChassis = new ProtomekChassisView(panBasicInfo);
        panArmor = new BAProtoArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panArmorAllocation = new ArmorAllocationView(panBasicInfo, Entity.ETYPE_PROTOMECH);
        panSummary = new ProtomekSummaryView(eSource);

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getProtomech());
        panChassis.setFromEntity(getProtomech());
        panArmor.setFromEntity(getProtomech());
        panMovement.setFromEntity(getProtomech());
        panArmorAllocation.setFromEntity(getProtomech());

        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createGlue());

        centerPanel.add(panArmor);
        centerPanel.add(panMovement);
        centerPanel.add(panSummary);
        centerPanel.add(Box.createVerticalGlue());

        rightPanel.add(panArmorAllocation);
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
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getProtomech());
        panChassis.setFromEntity(getProtomech());
        panArmor.setFromEntity(getProtomech());
        panMovement.setFromEntity(getProtomech());
        panArmorAllocation.setFromEntity(getProtomech());

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
        panArmor.removeListener(this);
        panMovement.removeListener(this);
        panArmorAllocation.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panArmor.addListener(this);
        panMovement.addListener(this);
        panArmorAllocation.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public boolean isQuad() {
        return panChassis.getMotiveType() == ProtomekChassisView.MOTIVE_TYPE_QUAD;
    }

    public boolean isGlider() {
        return panChassis.getMotiveType() == ProtomekChassisView.MOTIVE_TYPE_GLIDER;
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     * 
     * @param walkMP The base walk MP
     * @param tonnage The design weight
     * @param quadOrGlider Whether the Protomech is a quad or glider configuration
     * @return Whether the engine rating changed 
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage, boolean quadOrGlider) {
        int rating = TestProtomech.calcEngineRating(walkMP, tonnage, quadOrGlider);
        int oldRating = getProtomech().getEngine().getRating();
        if (oldRating != rating) {
            Engine engine = new Engine(rating, Engine.NORMAL_ENGINE, Engine.CLAN_ENGINE);
            getProtomech().setEngine(engine);
            return true;
        }
        return false;
    }
    
    /**
     * Creates room for installing fixed equipment in a location by removing as much non-fixed
     * equipment as necessary (if any) until there are a sufficient number of slots.
     * 
     * @param loc    The location to clear
     * @param count  The number of slots required
     * @return       Whether enough space was freed up
     */
    private boolean freeUpSpace(int loc, int count) {
        int maxAvail = TestProtomech.maxSlotsByLocation(loc, getProtomech());
        List<Mounted> optional = new ArrayList<>();
        for (Mounted m : getProtomech().getEquipment()) {
            if ((m.getLocation() != loc) || !TestProtomech.requiresSlot(m.getType())) {
                continue;
            }
            if (UnitUtil.isFixedLocationSpreadEquipment(m.getType())) {
                maxAvail--;
            } else {
                optional.add(m);
            }
        }
        if (count > maxAvail) {
            return false;
        }
        int empty = maxAvail - optional.size();
        if (empty >= count) {
            return true;
        }
        for (int i = optional.size() - 1; i >= 0; i--) {
            UnitUtil.changeMountStatus(getProtomech(), optional.get(i), Entity.LOC_NONE, Entity.LOC_NONE, false);
            empty++;
            if (empty >= count) {
                return true;
            }
        }
        return false;
    }

    private void createArmorMountsAndSetArmorType(EquipmentType armor) {
        TestProtomech.ProtomechArmor pmArmor = TestProtomech.ProtomechArmor
                .getArmor(EquipmentType.getArmorType(armor), true);
        List<Mounted> armorMounts = getProtomech().getMisc().stream()
                .filter(m -> EquipmentType.getArmorType(m.getType()) != EquipmentType.T_ARMOR_UNKNOWN)
                .collect(Collectors.toList());
        for (Mounted m : armorMounts) {
            UnitUtil.removeMounted(getProtomech(), m);
        }
        if (null == pmArmor) {
            getProtomech().setArmorType(EquipmentType.T_ARMOR_STANDARD);
            getProtomech().setArmorTechLevel(TechConstants.T_ALL_CLAN);
        } else {
            getProtomech().setArmorType(pmArmor.getType());
            getProtomech().setArmorTechLevel(pmArmor.getArmorTech());
        }
        if (pmArmor.getTorsoSlots() > 0) {
            if (freeUpSpace(Protomech.LOC_TORSO, pmArmor.getTorsoSlots())) {
                try {
                    Mounted mount = new Mounted(getProtomech(), pmArmor.getArmorEqType());
                    getProtomech().addEquipment(mount, Protomech.LOC_TORSO, false);
                    return;
                } catch (LocationFullException e) {
                    // fall through
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Requires free torso slot. Resetting to Standard Armor",
                    "Location Full",
                    JOptionPane.INFORMATION_MESSAGE);
            getProtomech().setArmorType(EquipmentType.T_ARMOR_STANDARD);
            getProtomech().setArmorTechLevel(TechConstants.T_ALL_CLAN);
            panArmor.setFromEntity(getProtomech());
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
        getProtomech().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getProtomech().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getProtomech().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getProtomech().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getProtomech().isClan()) || (mixed != getProtomech().isMixedTech())) {
            getProtomech().setMixedTech(mixed);
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
        getProtomech().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (!getTechManager().isLegal(panArmor.getArmor())) {
            UnitUtil.removeISorArmorMounts(getProtomech(), false);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getProtomech(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        if (!panBasicInfo.isLegal(Protomech.TA_INTERFACE_COCKPIT)) {
            getProtomech().setInterfaceCockpit(false);
        }
        panChassis.setFromEntity(getProtomech());
        panMovement.setFromEntity(getProtomech());
        panArmor.refresh();
        panArmorAllocation.setFromEntity(getProtomech());
        refresh.refreshBuild();
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getProtomech().setManualBV(manualBV);
    }

    @Override
    public void tonnageChanged(double tonnage) {
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage,
                panChassis.getMotiveType() != ProtomekChassisView.MOTIVE_TYPE_BIPED)) {
            panChassis.setFromEntity(getProtomech());
            return;
        }
        getProtomech().setWeight(tonnage);
        getProtomech().autoSetInternal();
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void typeChanged(int motiveType) {
        boolean wasQuad = getProtomech().isQuad();
        switch (motiveType) {
            case ProtomekChassisView.MOTIVE_TYPE_BIPED:
                getProtomech().setMovementMode(EntityMovementMode.BIPED);
                getProtomech().setIsQuad(false);
                getProtomech().setIsGlider(false);
                break;
            case ProtomekChassisView.MOTIVE_TYPE_QUAD:
                getProtomech().setMovementMode(EntityMovementMode.QUAD);
                getProtomech().setIsQuad(true);
                getProtomech().setIsGlider(false);
                getProtomech().getEquipment().stream()
                    .filter(m -> (m.getLocation() == Protomech.LOC_LARM)
                            || (m.getLocation() == Protomech.LOC_RARM))
                    .forEach(m -> m.setLocation(Entity.LOC_NONE));
                getProtomech().initializeArmor(0, Protomech.LOC_LARM);
                getProtomech().initializeArmor(0, Protomech.LOC_RARM);
                break;
            case ProtomekChassisView.MOTIVE_TYPE_GLIDER:
                getProtomech().setMovementMode(EntityMovementMode.WIGE);
                getProtomech().setIsQuad(false);
                getProtomech().setIsGlider(true);
                break;
        }
        getProtomech().autoSetInternal();
        if (wasQuad) {
            getProtomech().autoSetInternal();
            getProtomech().initializeArmor(0, Protomech.LOC_LARM);
            getProtomech().initializeArmor(0, Protomech.LOC_RARM);
            Optional<Mounted> qms = getProtomech().getMisc().stream().filter(m -> m.getType()
                    .hasFlag(MiscType.F_CLUB) && m.getType().hasSubType(MiscType.S_PROTO_QMS)).findFirst();
            if (qms.isPresent()) {
                UnitUtil.removeMounted(getProtomech(), qms.get());
            }
        }
        List<Mounted> toRemove = getProtomech().getMisc().stream()
                .filter(m -> !UnitUtil.isProtomechEquipment(m.getType(), getProtomech(), true))
                .collect(Collectors.toList());
        toRemove.forEach(m -> UnitUtil.removeMounted(getProtomech(), m));
        panMovement.setFromEntity(getProtomech());
        recalculateEngineRating(panMovement.getWalk(), panChassis.getTonnage(),
                motiveType != ProtomekChassisView.MOTIVE_TYPE_BIPED);
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void armorTypeChanged(EquipmentType armor) {
        UnitUtil.removeISorArmorMounts(getProtomech(), false);
        createArmorMountsAndSetArmorType(armor);
        panArmorAllocation.setFromEntity(getProtomech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorValueChanged(int points) {
        double tonnage = EquipmentType.getProtomechArmorWeightPerPoint(
                getProtomech().getArmorType(Protomech.LOC_TORSO))
                * points;
        getProtomech().setArmorTonnage(tonnage);
        panArmorAllocation.setFromEntity(getProtomech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getProtomech());
        getProtomech().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getProtomech());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getProtomech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getProtomech())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getProtomech());
        double totalTonnage = getProtomech().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.KILO);

        double maxArmor = Math.min(getProtomech().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getProtomech()));
        getProtomech().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getProtomech());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getProtomech());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        recalculateEngineRating(walkMP, panChassis.getTonnage(),
                panChassis.getMotiveType() != ProtomekChassisView.MOTIVE_TYPE_BIPED);
        getProtomech().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getProtomech());
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // Don't set jumpMP for UMU.
        if (null == jumpJet) {
            getProtomech().setOriginalJumpMP(0);
            jumpMP = 0;
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET)) {
            getProtomech().setOriginalJumpMP(jumpMP);
        } else {
            getProtomech().setOriginalJumpMP(0);
        }
        List<Mounted> jjs = getProtomech().getMisc().stream()
                .filter(m -> m.getType() == jumpJet)
                .collect(Collectors.toList());
        while (jjs.size() > jumpMP) {
            UnitUtil.removeMounted(getProtomech(), jjs.remove(jjs.size() - 1));
        }
        while (jumpMP > jjs.size()) {
            try {
                UnitUtil.addMounted(getProtomech(), new Mounted(getProtomech(), jumpJet),
                        Protomech.LOC_BODY, false);
            } catch (LocationFullException e) {
                // Shouldn't be able to fill location
            }
            jumpMP--;
        }
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getProtomech());
    }

    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        List<Mounted> jjs = getProtomech().getMisc().stream()
                .filter(m -> m.getType().hasFlag(MiscType.F_JUMP_JET)
                        || m.getType().hasFlag(MiscType.F_UMU))
                .filter(m -> m.getType() != jumpJet)
                .collect(Collectors.toList());
        jjs.forEach(jj -> UnitUtil.removeMounted(getProtomech(), jj));
        jumpChanged(panMovement.getJump(), jumpJet);
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getProtomech().initializeArmor(front, location);
        panArmorAllocation.setFromEntity(getProtomech());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        double pointsToAllocate = panArmor.getArmorPoints();
        double maxArmor = UnitUtil.getMaximumArmorPoints(getProtomech());
        if (pointsToAllocate > maxArmor) {
            pointsToAllocate = maxArmor;
        }
        double percent = pointsToAllocate / maxArmor;
        final double totalIS = getProtomech().getTotalOInternal();
        for (int location = getProtomech().firstArmorIndex(); location < getProtomech().locations(); location++) {
            double allocate = Math.min(UnitUtil.getMaximumArmorPoints(getProtomech(), location) * percent, pointsToAllocate);
            getProtomech().initializeArmor((int) allocate, location);
            pointsToAllocate -= (int) allocate;
        }
        allocateLeftoverPoints(pointsToAllocate);

        panArmorAllocation.setFromEntity(getProtomech());
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
        while (points >= 1) {
            // Assign with the priority torso, legs, head, arms, main gun (if any)
            // If there are exactly two points left after the legs, skip the head and assign them
            // symmetrically to the arms
            if (!getProtomech().isQuad()) {
                addArmorPoint(Protomech.LOC_TORSO);
                points--;
                if (points > 0) {
                    addArmorPoint(Protomech.LOC_LEG);
                    points--;
                }
                if ((points > 0) && (points != 2)) {
                    addArmorPoint(Protomech.LOC_HEAD);
                    points--;
                }
                if (points >= 2) {
                    addArmorPoint(Protomech.LOC_LARM);
                    addArmorPoint(Protomech.LOC_RARM);
                    points -= 2;
                }
                if ((points > 0) && getProtomech().hasMainGun()) {
                    addArmorPoint(Protomech.LOC_HEAD);
                    points--;
                }
            } else {
                // For quads we have no arm armor and we prioritize the legs over the torso.
                addArmorPoint(Protomech.LOC_LEG);
                points--;
                if (points > 0) {
                    addArmorPoint(Protomech.LOC_TORSO);
                    points--;
                }
                if (points > 0) {
                    addArmorPoint(Protomech.LOC_HEAD);
                    points--;
                }
                if ((points > 0) && getProtomech().hasMainGun()) {
                    addArmorPoint(Protomech.LOC_HEAD);
                    points--;
                }
            }
        }
    }
    
    /**
     * Convenience method for armor auto-allocation
     * 
     * @param location
     */
    private void addArmorPoint(int location) {
        if (getProtomech().getOArmor(location) < UnitUtil.getMaximumArmorPoints(getProtomech(), location)) {
            getProtomech().initializeArmor(getProtomech().getOArmor(location) + 1, location);
        }
    }

    @Override
    public void mainGunChanged(boolean mainGun) {
        getProtomech().setHasMainGun(mainGun);
        if (!mainGun) {
            getProtomech().initializeArmor(IArmorState.ARMOR_NA, Protomech.LOC_MAINGUN);
            getProtomech().getEquipment().forEach(m -> {
                if (m.getLocation() == Protomech.LOC_MAINGUN) {
                    m.setLocation(Entity.LOC_NONE);
                }
            });
        } else {
            getProtomech().initializeArmor(0, Protomech.LOC_MAINGUN);
            getProtomech().setArmorType(getProtomech().getArmorType(Protomech.LOC_TORSO));
            getProtomech().setArmorTechLevel(getProtomech().getArmorTechLevel(Protomech.LOC_TORSO));
        }
        getProtomech().autoSetInternal();
        panArmor.setFromEntity(getProtomech());
        panArmorAllocation.setFromEntity(getProtomech());
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void setEnhancement(EquipmentType eq, boolean selected) {
        if (null ==  eq) {
            return;
        }
        if (selected) {
            for (Mounted m : getProtomech().getMisc()) {
                if (m.getType() == eq) {
                    return;
                }
            }
            Mounted m = new Mounted(getProtomech(), eq);
            try {
                if (TestProtomech.requiresSlot(eq) && this.freeUpSpace(Protomech.LOC_TORSO, 1)) {
                        getProtomech().addEquipment(m, Protomech.LOC_TORSO, false);
                } else {
                    getProtomech().addEquipment(m,  Protomech.LOC_BODY, false);
                }
            } catch (LocationFullException e) {
                // We've already checked for enough space where there are limits
            }
        } else {
            Optional<Mounted> mounted = getProtomech().getMisc().stream()
                    .filter(m -> m.getType() == eq).findFirst();
            if (mounted.isPresent()) {
                UnitUtil.removeMounted(getProtomech(), mounted.get());
            }
        }
        panMovement.setFromEntity(getProtomech());
        panSummary.refresh();
        refresh.refreshPreview();
        refresh.refreshBuild();
        refresh.refreshStatus();
    }

    @Override
    public void setISInterface(boolean selected) {
        getProtomech().setInterfaceCockpit(selected);
    }

}
