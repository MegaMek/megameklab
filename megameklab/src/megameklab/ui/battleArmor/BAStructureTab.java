/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import megamek.common.CriticalSlot;
import megamek.common.SimpleTechLevel;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.enums.Faction;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.UnitRole;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestEntity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BAProtoArmorView;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.BABuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

/**
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAStructureTab extends ITab implements BABuildListener, ArmorAllocationListener {
    private static final MMLogger logger = MMLogger.create(BAStructureTab.class);

    private RefreshListener refresh;

    private BasicInfoView panBasicInfo;
    private BAChassisView panChassis;
    private MovementView panMovement;
    private BAProtoArmorView panArmor;
    private BAEnhancementView panEnhancements;
    private IconView iconView;
    private BAManipulatorView panManipulator;

    public BAStructureTab(EntitySource eSource) {
        super(eSource);
        setUpPanels();
        refresh();
    }

    public void setUpPanels() {
        panBasicInfo = new BasicInfoView(getBattleArmor().getConstructionTechAdvancement());
        panChassis = new BAChassisView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panArmor = new BAProtoArmorView(panBasicInfo);
        iconView = new IconView();
        panEnhancements = new BAEnhancementView(panBasicInfo);
        panManipulator = new BAManipulatorView(eSource, panBasicInfo);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panManipulator.setBorder(BorderFactory.createTitledBorder("Manipulators"));
        panEnhancements.setBorder(BorderFactory.createTitledBorder("Enhancements"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Box leftPanel = Box.createVerticalBox();
        Box midPanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();

        leftPanel.add(panBasicInfo);
        leftPanel.add(iconView);

        midPanel.add(panChassis);
        midPanel.add(panArmor);
        midPanel.add(panMovement);

        rightPanel.add(panEnhancements);
        rightPanel.add(panManipulator);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(leftPanel, gbc);
        add(midPanel, gbc);
        add(rightPanel, gbc);
        // add a row that will take up vertical space to top-align the content
        gbc.gridy++;
        gbc.weighty = 1;
        add(Box.createVerticalGlue(), gbc);
    }

    public JLabel createLabel(String text) {
        return new JLabel(text, SwingConstants.RIGHT);
    }

    public void refresh() {
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.setFromEntity(getBattleArmor());
        panMovement.setFromEntity(getBattleArmor());
        panArmor.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        iconView.setFromEntity(getEntity());
        panManipulator.setFromEntity();

        removeAllListeners();
        refreshPreview();
        addAllListeners();
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    /**
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(Faction techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
        panEnhancements.addListener(this);
    }

    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
        panEnhancements.removeListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        panManipulator.addRefreshedListener(l);
    }

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
    }

    public void refreshPreview() {
        if (refresh != null) {
            refresh.refreshPreview();
        }
    }

    @Override
    public void refreshSummary() {
        // no summary
    }

    @Override
    public void chassisChanged(String chassis) {
        getBattleArmor().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getBattleArmor().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getBattleArmor().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getBattleArmor().setSource(source);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void publishedChanged(String published) {
        getBattleArmor().setPublished(published);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void factionChanged(Faction faction) {
        getEntity().setTechFaction(faction);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getBattleArmor().isClan()) || (mixed != getBattleArmor().isMixedTech())) {
            getBattleArmor().setMixedTech(mixed);
            updateTechLevel();
            if (clan) {
                squadSizeChanged(5);
            } else if (getBattleArmor().getSquadSize() == 5) {
                squadSizeChanged(4);
            }
        }
        updateTechLevel();
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getBattleArmor().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getBattleArmor(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panMovement.refresh();
        panEnhancements.setFromEntity(getBattleArmor());
        panArmor.refresh();
        ArmorType armor = panArmor.getArmor();
        // If the current armor is no longer available, switch to the current selection
        if (EquipmentType.getArmorType(armor) != getBattleArmor().getArmorType(BattleArmor.LOC_SQUAD)
              || (armor.getTechLevel(getBattleArmor().getYear()) != getBattleArmor()
              .getArmorTechLevel(BattleArmor.LOC_SQUAD))) {
            armorTypeChanged(armor);
        }
        addAllListeners();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        getBattleArmor().setOriginalWalkMP(walkMP);
        panMovement.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        getBattleArmor().setOriginalJumpMP(jumpMP);
        if (0 == jumpMP) {
            jumpTypeChanged(null);
        } else {
            jumpTypeChanged(jumpJet);
        }
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        if (getEntity().getMisc().stream().noneMatch(m -> m.getType().equals(jumpJet))) {
            UnitUtil.removeAllMiscMounted(getBattleArmor(), MiscType.F_JUMP_JET);
            UnitUtil.removeAllMiscMounted(getBattleArmor(), MiscType.F_BA_VTOL);
            UnitUtil.removeAllMiscMounted(getBattleArmor(), MiscType.F_UMU);
            if (getBattleArmor().getOriginalJumpMP() > 0 && jumpJet != null) {
                try {
                    getBattleArmor().addEquipment(jumpJet, BattleArmor.LOC_NONE);
                } catch (LocationFullException ignored) {
                    // not when we're adding to LOC_NONE
                }
            }
        }
        if ((null == jumpJet) || (getBattleArmor().getOriginalJumpMP() == 0)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_LEG);
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_JUMP);
        } else if (jumpJet.hasFlag(MiscType.F_UMU)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_UMU);
        } else {
            getBattleArmor().setMovementMode(EntityMovementMode.VTOL);
        }
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void chassisTypeChanged(int chassisType) {
        getBattleArmor().setChassisType(chassisType);
        BattleArmorUtil.removeManipulatorEquipment(getBattleArmor());
        BattleArmorUtil.removeAllCriticalSlotsFrom(getBattleArmor(),
              List.of(BattleArmor.MOUNT_LOC_LEFT_ARM, BattleArmor.MOUNT_LOC_RIGHT_ARM, BattleArmor.MOUNT_LOC_TURRET));
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.setFromEntity(getBattleArmor());
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        panManipulator.setFromEntity();
        refreshPreview();
        refresh.refreshStatus();
        refresh.refreshBuild();
    }

    @Override
    public void weightClassChanged(int weightClass) {
        getBattleArmor().setWeightClass(weightClass);
        if (weightClass > EntityWeightClass.WEIGHT_ULTRA_LIGHT) {
            getBattleArmor().setIsExoskeleton(false);
            getBattleArmor().setClanExoWithoutHarjel(false);
        }
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.refresh();
        panMovement.setFromEntity(getBattleArmor());
        panArmor.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void exoskeletonChanged(boolean exoskeleton) {
        getBattleArmor().setIsExoskeleton(exoskeleton);
        if (exoskeleton && !panBasicInfo.useClanTechBase()) {
            getBattleArmor().setClanExoWithoutHarjel(panChassis.hasHarjel());
        }
        panBasicInfo.setFromEntity(getBattleArmor());
        panArmor.refresh();
        refresh.refreshStatus();
        refreshPreview();
    }

    @Override
    public void harjelChanged(boolean harjel) {
        getBattleArmor().setClanExoWithoutHarjel(panChassis.isExoskeleton() && !harjel);
        refresh.refreshStatus();
        refreshPreview();
    }

    @Override
    public void squadSizeChanged(int squadSize) {
        if (squadSize != getBattleArmor().getSquadSize()) {
            // We need to resize several arrays. This clears out the critical slots, so
            // we're going to preserve them before refreshing and restore the data
            // afterward.
            CriticalSlot[][] slots = new CriticalSlot[getBattleArmor().locations()][];
            for (int loc = 0; loc < getBattleArmor().locations(); loc++) {
                slots[loc] = new CriticalSlot[getBattleArmor().getNumberOfCriticalSlots(loc)];
                for (int i = 0; i < getBattleArmor().getNumberOfCriticalSlots(loc); i++) {
                    slots[loc][i] = getBattleArmor().getCritical(loc, i);
                }
            }
            int armor = getBattleArmor().getArmor(BattleArmor.LOC_TROOPER_1);
            getBattleArmor().setSquadSize(squadSize);
            getBattleArmor().refreshLocations();
            for (int loc = 0; loc < Math.min(getBattleArmor().locations(), slots.length); loc++) {
                for (int i = 0; i < slots[loc].length; i++) {
                    getBattleArmor().setCritical(loc, i, slots[loc][i]);
                }
            }
            getBattleArmor().autoSetInternal();
            for (int i = 1; i < getBattleArmor().locations(); i++) {
                getBattleArmor().initializeArmor(armor, i);
            }
            refresh.refreshStatus();
            refresh.refreshSummary();
            refresh.refreshPreview();
            refresh.refreshBuild();
        }
    }

    @Override
    public void turretChanged(int type, int size) {
        getBattleArmor().setModularTurret(type == BAChassisView.TURRET_MODULAR);
        if (type == BAChassisView.TURRET_NONE) {
            size = 0;
        } else {
            size = Math.max(1, size);
        }
        getBattleArmor().setTurretSize(size);

        if (size == 0) {
            for (Mounted<?> mount : getBattleArmor().getEquipment()) {
                if (mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_TURRET) {
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_BODY);
                }
            }
        }
        panChassis.setFromEntity(getBattleArmor());
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
        refresh.refreshBuild();
    }

    @Override
    public void enhancementChanged(EquipmentType eq, boolean selected) {
        if (selected) {
            try {
                int loc = eq.hasFlag(MiscType.F_MASC) ? BattleArmor.MOUNT_LOC_NONE : BattleArmor.MOUNT_LOC_BODY;
                int numTimesToAdd = 1;
                // Spreadable equipment needs to have a Mounted entry
                // for each critical
                if (eq.isSpreadable()) {
                    numTimesToAdd = eq.getNumCriticalSlots(getBattleArmor());
                }
                for (int i = 0; i < numTimesToAdd; i++) {
                    Mounted<?> newMount = Mounted.createMounted(getBattleArmor(), eq);
                    newMount.setBaMountLoc(loc);
                    getBattleArmor().addEquipment(newMount, BattleArmor.LOC_SQUAD, false);
                }
            } catch (Exception ex) {
                // Shouldn't happen with BA
                logger.error("", ex);
            }
        } else {
            List<Mounted<?>> mounts = getBattleArmor().getMisc().stream()
                  .filter(m -> m.getType().equals(eq))
                  .collect(Collectors.toList());
            for (Mounted<?> mount : mounts) {
                UnitUtil.removeMounted(getBattleArmor(), mount);
            }
        }
        panMovement.setFromEntity(getBattleArmor());
        refresh.refreshSummary();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void armorFactorChanged(int points) {
        for (int i = 0; i < getBattleArmor().locations(); i++) {
            getBattleArmor().initializeArmor(points, i);
        }
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void armorTypeChanged(ArmorType armor) {
        UnitUtil.removeISorArmorMounts(getBattleArmor(), false);
        int armorCount = armor.getNumCriticalSlots(getBattleArmor());
        getBattleArmor().setArmorTechLevel(armor.getTechLevel(getBattleArmor().getYear()));
        getBattleArmor().setArmorType(armor.getInternalName());

        for (; armorCount > 0; armorCount--) {
            try {
                getBattleArmor().addEquipment(Mounted.createMounted(getBattleArmor(), armor),
                      BattleArmor.LOC_SQUAD, false);
            } catch (Exception ex) {
                logger.error("", ex);
            }
        }
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        armorFactorChanged(getBattleArmor().getMaximumArmorPoints());
        panArmor.removeListener(this);
        panArmor.setFromEntity(getBattleArmor());
        panArmor.addListener(this);
    }

    @Override
    public void useRemainingTonnageArmor() {
        final TestBattleArmor testBA = (TestBattleArmor) UnitUtil.getEntityVerifier(getBattleArmor());
        double currentTonnage = testBA.calculateWeight(BattleArmor.LOC_SQUAD);
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());
        double totalTonnage = getBattleArmor().getTrooperWeight();
        double remainingTonnage = TestEntity.floor(
              totalTonnage - currentTonnage, Ceil.KILO);
        int points = (int) TestEntity.getRawArmorPoints(getBattleArmor(), remainingTonnage);
        int maxArmor = Math.clamp(getBattleArmor().getMaximumArmorPoints(), 0,
              points + getBattleArmor().getOArmor(BattleArmor.LOC_TROOPER_1));
        armorFactorChanged(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getBattleArmor());
        panArmor.addListener(this);
    }

    @Override
    public void mulIdChanged(int mulId) {
        getBattleArmor().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
