/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import megamek.codeUtilities.MathUtility;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.enums.Faction;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.IArmorState;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.ProtoMek;
import megamek.common.units.UnitRole;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.ArmorAllocationView;
import megameklab.ui.generalUnit.BAProtoArmorView;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.ProtoMekBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.ProtoMekUtil;
import megameklab.util.UnitUtil;

/**
 * Structure tab for protomeks
 *
 * @author Neoancient
 */
public class PMStructureTab extends ITab implements ProtoMekBuildListener, ArmorAllocationListener {
    private BasicInfoView panBasicInfo;
    private PMChassisView panChassis;
    private BAProtoArmorView panArmor;
    private MovementView panMovement;
    private SummaryView panSummary;
    private ArmorAllocationView panArmorAllocation;
    private IconView iconView;

    RefreshListener refresh = null;
    JPanel masterPanel;

    public PMStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getProtoMek().getConstructionTechAdvancement());
        panChassis = new PMChassisView(panBasicInfo);
        panArmor = new BAProtoArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panArmorAllocation = new ArmorAllocationView(panBasicInfo, Entity.ETYPE_PROTOMEK);
        iconView = new IconView();
        panSummary = new SummaryView(eSource,
              new UnitTypeSummaryItem(),
              new StructureSummaryItem(),
              new EngineSummaryItem(),
              new HeatSinkSummaryItem(),
              new ControlsSummaryItem(),
              new ArmorSummaryItem(),
              new JumpSummaryItem(),
              new WeaponsSummaryItem(),
              new AmmoSummaryItem(),
              new MiscEquipmentSummaryItem(),
              new MyomerEnhancementSummaryItem());

        GridBagConstraints gbc;

        panBasicInfo.setFromEntity(getProtoMek());
        panChassis.setFromEntity(getProtoMek());
        panArmor.setFromEntity(getProtoMek());
        panMovement.setFromEntity(getProtoMek());
        panArmorAllocation.setFromEntity(getProtoMek());
        iconView.setFromEntity(getEntity());

        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(iconView);
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
        gbc.insets = new Insets(5, 5, 5, 5);
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(centerPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getProtoMek());
        panChassis.setFromEntity(getProtoMek());
        panArmor.setFromEntity(getProtoMek());
        panMovement.setFromEntity(getProtoMek());
        panArmorAllocation.setFromEntity(getProtoMek());
        iconView.setFromEntity(getEntity());

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
    public void setTechFaction(Faction techFaction) {
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
        return panChassis.getMotiveType() == PMChassisView.MOTIVE_TYPE_QUAD;
    }

    public boolean isGlider() {
        return panChassis.getMotiveType() == PMChassisView.MOTIVE_TYPE_GLIDER;
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     *
     * @param walkMP       The base walk MP
     * @param tonnage      The design weight
     * @param quadOrGlider Whether the ProtoMek is a quad or glider configuration
     *
     * @return Whether the engine rating changed
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage, boolean quadOrGlider) {
        int rating = TestProtoMek.calcEngineRating(walkMP, tonnage, quadOrGlider);
        int oldRating = getProtoMek().getEngine().getRating();
        if (oldRating != rating) {
            Engine engine = new Engine(rating, Engine.NORMAL_ENGINE, Engine.CLAN_ENGINE);
            getProtoMek().setEngine(engine);
            return true;
        }
        return false;
    }

    /**
     * Creates room for installing fixed equipment in a location by removing as much non-fixed equipment as necessary
     * (if any) until there are a sufficient number of slots.
     *
     * @param loc   The location to clear
     * @param count The number of slots required
     *
     * @return Whether enough space was freed up
     */
    private boolean freeUpSpace(int loc, int count) {
        int maxAvail = TestProtoMek.maxSlotsByLocation(loc, getProtoMek());
        List<Mounted<?>> optional = new ArrayList<>();
        for (Mounted<?> m : getProtoMek().getEquipment()) {
            if ((m.getLocation() != loc) || !TestProtoMek.requiresSlot(m.getType())) {
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
            UnitUtil.changeMountStatus(getProtoMek(), optional.get(i), Entity.LOC_NONE, Entity.LOC_NONE, false);
            empty++;
            if (empty >= count) {
                return true;
            }
        }
        return false;
    }

    private void createArmorMountsAndSetArmorType(ArmorType armor) {
        List<Mounted<?>> armorMounts = getProtoMek().getMisc().stream()
              .filter(m -> m.getType() instanceof ArmorType)
              .collect(Collectors.toList());
        for (Mounted<?> m : armorMounts) {
            UnitUtil.removeMounted(getProtoMek(), m);
        }

        getProtoMek().setArmorType(armor.getArmorType());
        getProtoMek().setArmorTechLevel(armor.getStaticTechLevel().getCompoundTechLevel(armor.isClan()));

        if (armor.getNumCriticalSlots(getProtoMek()) > 0) {
            if (freeUpSpace(ProtoMek.LOC_TORSO, armor.getNumCriticalSlots(getProtoMek()))) {
                try {
                    Mounted<?> mount = Mounted.createMounted(getProtoMek(), armor);
                    getProtoMek().addEquipment(mount, ProtoMek.LOC_TORSO, false);
                    return;
                } catch (LocationFullException ignored) {
                    // fall through
                }
            }
            JOptionPane.showMessageDialog(null,
                  "Requires free torso slot. Resetting to Standard Armor",
                  "Location Full",
                  JOptionPane.INFORMATION_MESSAGE);
            getProtoMek().setArmorType(EquipmentType.T_ARMOR_STANDARD_PROTOMEK);
            getProtoMek().setArmorTechLevel(TechConstants.T_ALL_CLAN);
            panArmor.setFromEntity(getProtoMek());
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
        getProtoMek().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getProtoMek().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getProtoMek().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getProtoMek().setSource(source);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getProtoMek().isClan()) || (mixed != getProtoMek().isMixedTech())) {
            getProtoMek().setMixedTech(mixed);
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
        getProtoMek().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (!getTechManager().isLegal(panArmor.getArmor())) {
            UnitUtil.removeISorArmorMounts(getProtoMek(), false);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getProtoMek(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        if (!panBasicInfo.isLegal(ProtoMek.TA_INTERFACE_COCKPIT)) {
            getProtoMek().setInterfaceCockpit(false);
        }
        panChassis.setFromEntity(getProtoMek());
        panMovement.setFromEntity(getProtoMek());
        panArmor.refresh();
        panArmorAllocation.setFromEntity(getProtoMek());
        refresh.refreshBuild();
        addAllListeners();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void tonnageChanged(double tonnage) {
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage,
              panChassis.getMotiveType() != PMChassisView.MOTIVE_TYPE_BIPED)) {
            panChassis.setFromEntity(getProtoMek());
            return;
        }
        getProtoMek().setWeight(tonnage);
        getProtoMek().autoSetInternal();
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void typeChanged(int motiveType) {
        boolean wasQuad = getProtoMek().isQuad();
        switch (motiveType) {
            case PMChassisView.MOTIVE_TYPE_BIPED:
                getProtoMek().setMovementMode(EntityMovementMode.BIPED);
                getProtoMek().setIsQuad(false);
                getProtoMek().setIsGlider(false);
                break;
            case PMChassisView.MOTIVE_TYPE_QUAD:
                getProtoMek().setMovementMode(EntityMovementMode.QUAD);
                getProtoMek().setIsQuad(true);
                getProtoMek().setIsGlider(false);
                getProtoMek().getEquipment().stream()
                      .filter(m -> (m.getLocation() == ProtoMek.LOC_LEFT_ARM)
                            || (m.getLocation() == ProtoMek.LOC_RIGHT_ARM))
                      .forEach(m -> m.setLocation(Entity.LOC_NONE));
                getProtoMek().initializeArmor(0, ProtoMek.LOC_LEFT_ARM);
                getProtoMek().initializeArmor(0, ProtoMek.LOC_RIGHT_ARM);
                break;
            case PMChassisView.MOTIVE_TYPE_GLIDER:
                getProtoMek().setMovementMode(EntityMovementMode.WIGE);
                getProtoMek().setIsQuad(false);
                getProtoMek().setIsGlider(true);
                break;
        }
        getProtoMek().autoSetInternal();
        if (wasQuad) {
            getProtoMek().autoSetInternal();
            getProtoMek().initializeArmor(0, ProtoMek.LOC_LEFT_ARM);
            getProtoMek().initializeArmor(0, ProtoMek.LOC_RIGHT_ARM);
            Optional<MiscMounted> qms = getProtoMek().getMisc().stream().filter(m -> m.getType()
                  .hasFlag(MiscType.F_CLUB) && m.getType().hasFlag(MiscTypeFlag.S_PROTO_QMS)).findFirst();
            qms.ifPresent(miscMounted -> UnitUtil.removeMounted(getProtoMek(), miscMounted));
        }
        List<Mounted<?>> toRemove = getProtoMek().getMisc().stream()
              .filter(m -> !ProtoMekUtil.isProtoMekEquipment(m.getType(), getProtoMek(), true))
              .collect(Collectors.toList());
        toRemove.forEach(m -> UnitUtil.removeMounted(getProtoMek(), m));
        panMovement.setFromEntity(getProtoMek());
        recalculateEngineRating(panMovement.getWalk(), panChassis.getTonnage(),
              motiveType != PMChassisView.MOTIVE_TYPE_BIPED);
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void armorTypeChanged(ArmorType armor) {
        UnitUtil.removeISorArmorMounts(getProtoMek(), false);
        createArmorMountsAndSetArmorType(armor);
        panArmorAllocation.setFromEntity(getProtoMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorFactorChanged(int points) {
        double tonnage = ArmorType.forEntity(getProtoMek()).getWeightPerPoint() * points;
        getProtoMek().setArmorTonnage(tonnage);
        panArmorAllocation.setFromEntity(getProtoMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getProtoMek());
        getProtoMek().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getProtoMek());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getProtoMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getProtoMek())
              .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getProtoMek());
        double totalTonnage = getProtoMek().getWeight();
        double remainingTonnage = TestEntity.floor(
              totalTonnage - currentTonnage, Ceil.KILO);
        // We can only use remaining tonnage equal to whole points of armor.
        remainingTonnage = (int) TestEntity.getRawArmorPoints(getProtoMek(), remainingTonnage)
              * ArmorType.forEntity(getProtoMek()).getWeightPerPoint();
        double maxArmor = MathUtility.clamp(getProtoMek().getLabArmorTonnage() + remainingTonnage, 0,
              UnitUtil.getMaximumArmorTonnage(getProtoMek()));
        getProtoMek().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getProtoMek());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getProtoMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        recalculateEngineRating(walkMP, panChassis.getTonnage(),
              panChassis.getMotiveType() != PMChassisView.MOTIVE_TYPE_BIPED);
        getProtoMek().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getProtoMek());
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // Don't set jumpMP for UMU.
        if (null == jumpJet) {
            getProtoMek().setOriginalJumpMP(0);
            jumpMP = 0;
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET)) {
            getProtoMek().setOriginalJumpMP(jumpMP);
        } else {
            getProtoMek().setOriginalJumpMP(0);
        }

        List<Mounted<?>> jjs = new ArrayList<>();

        if (jumpJet != null) {
            jjs = getProtoMek().getMisc().stream()
                  .filter(m -> jumpJet.equals(m.getType()))
                  .collect(Collectors.toList());
        }

        while (jjs.size() > jumpMP) {
            UnitUtil.removeMounted(getProtoMek(), jjs.remove(jjs.size() - 1));
        }

        while (jumpMP > jjs.size()) {
            try {
                UnitUtil.addMounted(getProtoMek(),
                      Mounted.createMounted(getProtoMek(), jumpJet),
                      ProtoMek.LOC_BODY,
                      false);
            } catch (LocationFullException e) {
                // Shouldn't be able to fill location
            }
            jumpMP--;
        }

        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getProtoMek());
    }

    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        List<Mounted<?>> jjs = getProtoMek().getMisc().stream()
              .filter(m -> m.getType().hasFlag(MiscType.F_JUMP_JET)
                    || m.getType().hasFlag(MiscType.F_UMU))
              .filter(m -> !jumpJet.equals(m.getType()))
              .collect(Collectors.toList());
        jjs.forEach(jj -> UnitUtil.removeMounted(getProtoMek(), jj));
        jumpChanged(panMovement.getJump(), jumpJet);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getProtoMek().initializeArmor(front, location);
        panArmorAllocation.setFromEntity(getProtoMek());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        double pointsToAllocate = panArmor.getArmorPoints();
        double maxArmor = UnitUtil.getMaximumArmorPoints(getProtoMek());

        if (pointsToAllocate > maxArmor) {
            pointsToAllocate = maxArmor;
        }

        double percent = pointsToAllocate / maxArmor;

        for (int location = getProtoMek().firstArmorIndex(); location < getProtoMek().locations(); location++) {
            double allocate = Math.min(UnitUtil.getMaximumArmorPoints(getProtoMek(), location) * percent,
                  pointsToAllocate);
            getProtoMek().initializeArmor((int) allocate, location);
            pointsToAllocate -= (int) allocate;
        }

        allocateLeftoverPoints(pointsToAllocate);

        panArmorAllocation.setFromEntity(getProtoMek());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    /**
     * allocate any leftover points one-by-one
     *
     * @param points the amount of points left over
     */
    private void allocateLeftoverPoints(double points) {
        while (points >= 1) {
            // Assign with the priority torso, legs, head, arms, main gun (if any). If there are exactly two points
            // left after the legs, skip the head and assign them symmetrically to the arms
            if (!getProtoMek().isQuad()) {
                addArmorPoint(ProtoMek.LOC_TORSO);
                points--;
                if (points > 0) {
                    addArmorPoint(ProtoMek.LOC_LEG);
                    points--;
                }
                if ((points > 0) && (points != 2)) {
                    addArmorPoint(ProtoMek.LOC_HEAD);
                    points--;
                }
                if (points >= 2) {
                    addArmorPoint(ProtoMek.LOC_LEFT_ARM);
                    addArmorPoint(ProtoMek.LOC_RIGHT_ARM);
                    points -= 2;
                }
            } else {
                // For quads, we have no arm armor, and we prioritize the legs over the torso.
                addArmorPoint(ProtoMek.LOC_LEG);
                points--;
                if (points > 0) {
                    addArmorPoint(ProtoMek.LOC_TORSO);
                    points--;
                }
                if (points > 0) {
                    addArmorPoint(ProtoMek.LOC_HEAD);
                    points--;
                }
            }
            if ((points > 0) && getProtoMek().hasMainGun()) {
                addArmorPoint(ProtoMek.LOC_HEAD);
                points--;
            }
        }
    }

    /**
     * Convenience method for armor auto-allocation
     *
     */
    private void addArmorPoint(int location) {
        if (getProtoMek().getOArmor(location) < UnitUtil.getMaximumArmorPoints(getProtoMek(), location)) {
            getProtoMek().initializeArmor(getProtoMek().getOArmor(location) + 1, location);
            refresh.refreshSummary();
            refresh.refreshPreview();
        }
    }

    @Override
    public void mainGunChanged(boolean mainGun) {
        getProtoMek().setHasMainGun(mainGun);
        if (!mainGun) {
            getProtoMek().initializeArmor(IArmorState.ARMOR_NA, ProtoMek.LOC_MAIN_GUN);
            getProtoMek().getEquipment().forEach(m -> {
                if (m.getLocation() == ProtoMek.LOC_MAIN_GUN) {
                    m.setLocation(Entity.LOC_NONE);
                }
            });
        } else {
            getProtoMek().initializeArmor(0, ProtoMek.LOC_MAIN_GUN);
            getProtoMek().setArmorType(getProtoMek().getArmorType(ProtoMek.LOC_TORSO));
            getProtoMek().setArmorTechLevel(getProtoMek().getArmorTechLevel(ProtoMek.LOC_TORSO));
        }
        getProtoMek().autoSetInternal();
        panArmor.setFromEntity(getProtoMek());
        panArmorAllocation.setFromEntity(getProtoMek());
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void setEnhancement(EquipmentType eq, boolean selected) {
        if (null == eq) {
            return;
        }
        if (selected) {
            for (Mounted<?> m : getProtoMek().getMisc()) {
                if (eq.equals(m.getType())) {
                    return;
                }
            }
            Mounted<?> m = Mounted.createMounted(getProtoMek(), eq);
            try {
                if (TestProtoMek.requiresSlot(eq) && this.freeUpSpace(ProtoMek.LOC_TORSO, 1)) {
                    getProtoMek().addEquipment(m, ProtoMek.LOC_TORSO, false);
                } else {
                    getProtoMek().addEquipment(m, ProtoMek.LOC_BODY, false);
                }
            } catch (LocationFullException e) {
                // We've already checked for enough space where there are limits
            }
        } else {
            Optional<MiscMounted> mounted = getProtoMek().getMisc().stream()
                  .filter(m -> eq.equals(m.getType())).findFirst();
            mounted.ifPresent(miscMounted -> UnitUtil.removeMounted(getProtoMek(), miscMounted));
        }
        panMovement.setFromEntity(getProtoMek());
        panSummary.refresh();
        refresh.refreshPreview();
        refresh.refreshBuild();
        refresh.refreshStatus();
    }

    @Override
    public void setISInterface(boolean selected) {
        getProtoMek().setInterfaceCockpit(selected);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void mulIdChanged(int mulId) {
        getProtoMek().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
