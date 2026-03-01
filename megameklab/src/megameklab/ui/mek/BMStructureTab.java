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
package megameklab.ui.mek;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Comparator;
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
import megamek.common.CriticalSlot;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.enums.Faction;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.LandAirMek;
import megamek.common.units.Mek;
import megamek.common.units.QuadVee;
import megamek.common.units.UnitRole;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestEntity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.ArmorAllocationView;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.HeatSinkView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.MVFArmorView;
import megameklab.ui.generalUnit.MovementView;
import megameklab.ui.generalUnit.PatchworkArmorView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.MekBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class BMStructureTab extends ITab implements MekBuildListener, ArmorAllocationListener {
    private static final MMLogger LOGGER = MMLogger.create(BMStructureTab.class);

    private BasicInfoView panBasicInfo;
    private BMChassisView panChassis;
    private BMLAMFuelView panLAMFuel;
    private MVFArmorView panArmor;
    private SummaryView panSummary;
    private MovementView panMovement;
    private HeatSinkView panHeat;
    private ArmorAllocationView panArmorAllocation;
    private PatchworkArmorView panPatchwork;
    private IconView iconView;

    RefreshListener refresh = null;

    public BMStructureTab(EntitySource eSource) {
        super(eSource);
        setUpPanels();
        refresh();
    }

    private void setUpPanels() {
        setLayout(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getMek().getConstructionTechAdvancement());
        panChassis = new BMChassisView(panBasicInfo);
        panArmor = new MVFArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panHeat = new HeatSinkView(panBasicInfo);
        panLAMFuel = new BMLAMFuelView(eSource);
        panArmorAllocation = new ArmorAllocationView(panBasicInfo, Entity.ETYPE_MEK);
        panPatchwork = new PatchworkArmorView(panBasicInfo);
        iconView = new IconView();
        panSummary = new SummaryView(eSource,
              new UnitTypeSummaryItem(),
              new StructureSummaryItem(),
              new EngineSummaryItem(),
              new GyroSummaryItem(),
              new CockpitSummaryItem(),
              new HeatSinkSummaryItem(),
              new ArmorSummaryItem(),
              new JumpSummaryItem(),
              new EquipmentSummaryItem(),
              new MyomerEnhancementSummaryItem(),
              new OtherSummaryItem());

        if (getMek().hasPatchworkArmor()) {
            panArmorAllocation.showPatchwork(true);
        } else {
            panPatchwork.setVisible(false);
        }

        panBasicInfo.setFromEntity(getMek());
        panChassis.setFromEntity(getMek());
        panArmor.setFromEntity(getMek());
        panMovement.setFromEntity(getMek());
        panHeat.setFromMek(getMek());
        panArmorAllocation.setFromEntity(getMek());
        panPatchwork.setFromEntity(getMek());
        iconView.setFromEntity(getMek());

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
        leftPanel.add(Box.createVerticalGlue());

        centerPanel.add(panHeat);
        centerPanel.add(Box.createVerticalStrut(11));
        centerPanel.add(panMovement);
        centerPanel.add(Box.createVerticalStrut(11));
        centerPanel.add(panLAMFuel);
        centerPanel.add(Box.createVerticalStrut(11));
        centerPanel.add(panSummary);
        centerPanel.add(Box.createVerticalGlue());

        rightPanel.add(panArmor);
        rightPanel.add(Box.createVerticalStrut(11));
        rightPanel.add(panArmorAllocation);
        rightPanel.add(Box.createVerticalStrut(11));
        rightPanel.add(panPatchwork);
        rightPanel.add(Box.createVerticalGlue());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(leftPanel, gbc);
        gbc.gridx = 1;
        add(centerPanel, gbc);
        gbc.gridx = 2;
        add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panLAMFuel.setBorder(BorderFactory.createTitledBorder("Fuel"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panArmorAllocation.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
        panPatchwork.setBorder(BorderFactory.createTitledBorder("Patchwork Armor"));
    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getMek());
        panChassis.setFromEntity(getMek());
        panArmor.setFromEntity(getMek());
        panHeat.setFromMek(getMek());
        panMovement.setFromEntity(getMek());
        panArmorAllocation.setFromEntity(getMek());
        panPatchwork.setFromEntity(getMek());
        panLAMFuel.setFromEntity(getMek());
        panLAMFuel.setVisible(getMek() instanceof LandAirMek);
        panSummary.refresh();
        iconView.setFromEntity(getMek());
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

    public void setTechFaction(Faction techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    private void resetSystemCrits() {
        getMek().clearCockpitCrits();
        getMek().clearGyroCrits();
        getMek().clearEngineCrits();
        MekUtil.removeSystemCrits(getMek(), LandAirMek.LAM_LANDING_GEAR, Mek.LOC_CENTER_TORSO);

        int[] ctEngine = getMek().getEngine().getCenterTorsoCriticalSlots(getMek().getGyroType());
        int lastEngine = ctEngine[ctEngine.length - 1];
        for (int slot = 0; slot <= lastEngine; slot++) {
            clearCrit(Mek.LOC_CENTER_TORSO, slot);
        }
        for (int slot : getMek().getEngine().getSideTorsoCriticalSlots()) {
            clearCrit(Mek.LOC_RIGHT_TORSO, slot);
            clearCrit(Mek.LOC_LEFT_TORSO, slot);
        }
        getMek().addEngineCrits();
        switch (getMek().getGyroType()) {
            case Mek.GYRO_COMPACT:
                clearCritsForGyro(3, 2);
                getMek().addCompactGyro();
                break;
            case Mek.GYRO_HEAVY_DUTY:
                clearCritsForGyro(3, 4);
                getMek().addHeavyDutyGyro();
                break;
            case Mek.GYRO_XL:
                clearCritsForGyro(3, 6);
                getMek().addXLGyro();
                break;
            case Mek.GYRO_NONE:
                UnitUtil.compactCriticalSlots(getMek(), Mek.LOC_CENTER_TORSO);
                break;
            case Mek.GYRO_SUPERHEAVY:
                clearCritsForGyro(lastEngine + 1, 2);
                getMek().addSuperheavyGyro();
                break;
            default:
                clearCritsForGyro(3, 4);
                getMek().addGyro();
        }

        switch (getMek().getCockpitType()) {
            case Mek.COCKPIT_COMMAND_CONSOLE:
                clearCritsForCockpit(false, true);
                getMek().addCommandConsole();
                break;
            case Mek.COCKPIT_DUAL:
                clearCritsForCockpit(false, true);
                getMek().addDualCockpit();
                break;
            case Mek.COCKPIT_SMALL:
                clearCritsForCockpit(true, false);
                getMek().addSmallCockpit();
                break;
            case Mek.COCKPIT_INTERFACE:
                clearCritsForCockpit(false, true);
                getMek().addInterfaceCockpit();
                break;
            case Mek.COCKPIT_TORSO_MOUNTED:
            case Mek.COCKPIT_VRRP:
                if (lastEngine + 2 < getMek().getNumberOfCriticalSlots(Mek.LOC_CENTER_TORSO)) {
                    clearCrit(Mek.LOC_CENTER_TORSO, lastEngine + 1);
                    clearCrit(Mek.LOC_CENTER_TORSO, lastEngine + 2);
                }
                clearCrit(Mek.LOC_HEAD, 0);
                clearCrit(Mek.LOC_HEAD, 1);
                if (getMek().getEmptyCriticalSlots(Mek.LOC_LEFT_TORSO) < 1) {
                    for (int i = 0; i < getMek().getNumberOfCriticalSlots(Mek.LOC_LEFT_TORSO); i++) {
                        if (getMek().getCritical(Mek.LOC_LEFT_TORSO, i) != null
                              && getMek().getCritical(Mek.LOC_LEFT_TORSO, i).getType() == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mek.LOC_LEFT_TORSO, i);
                            break;
                        }
                    }
                }
                if (getMek().getEmptyCriticalSlots(Mek.LOC_RIGHT_TORSO) < 1) {
                    for (int i = 0; i < getMek().getNumberOfCriticalSlots(Mek.LOC_RIGHT_TORSO); i++) {
                        if (getMek().getCritical(Mek.LOC_RIGHT_TORSO, i) != null
                              && getMek().getCritical(Mek.LOC_RIGHT_TORSO, i).getType()
                              == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mek.LOC_RIGHT_TORSO, i);
                            break;
                        }
                    }
                }
                getMek().addTorsoMountedCockpit(getMek().getCockpitType() == Mek.COCKPIT_VRRP);
                break;
            case Mek.COCKPIT_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMek().addIndustrialCockpit();
                getMek().setArmorType(
                      EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mek.COCKPIT_PRIMITIVE:
                clearCritsForCockpit(false, false);
                getMek().addPrimitiveCockpit();
                getMek().setArmorType(
                      EquipmentType.T_ARMOR_PRIMITIVE);
                break;
            case Mek.COCKPIT_PRIMITIVE_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMek().addIndustrialPrimitiveCockpit();
                getMek().setArmorType(
                      EquipmentType.T_ARMOR_COMMERCIAL);
                break;
            case Mek.COCKPIT_QUADVEE:
                clearCritsForCockpit(false, true);
                getMek().addQuadVeeCockpit();
                break;
            case Mek.COCKPIT_SUPERHEAVY_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMek().addSuperheavyIndustrialCockpit();
                getMek().setArmorType(
                      EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mek.COCKPIT_SUPERHEAVY_COMMAND_CONSOLE:
                clearCritsForCockpit(false, true);
                getMek().addSuperheavyCommandConsole();
                break;
            case Mek.COCKPIT_SMALL_COMMAND_CONSOLE:
                clearCritsForCockpit(true, true);
                getMek().addSmallCommandConsole();
                break;
            default:
                clearCritsForCockpit(false, false);
                int cockpitType = getMek().getCockpitType();
                getMek().addCockpit();
                // addCockpit sets the criticalSlots but also sets the type to the default.
                getMek().setCockpitType(cockpitType);
        }

        // For LAMs we want to put the landing gear in the first available slot after
        // the engine and gyro.
        if (getMek().hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            int lgSlot = 10;
            for (int i = 0; i < getMek().getNumberOfCriticalSlots(Mek.LOC_CENTER_TORSO); i++) {
                final CriticalSlot slot = getMek().getCritical(Mek.LOC_CENTER_TORSO, i);
                if ((null == slot) || (slot.getType() == CriticalSlot.TYPE_EQUIPMENT)
                      || ((slot.getIndex() != Mek.SYSTEM_ENGINE) && (slot.getIndex() != Mek.SYSTEM_GYRO))) {
                    lgSlot = i;
                    break;
                }
            }
            CriticalSlot crit = new CriticalSlot(CriticalSlot.TYPE_SYSTEM,
                  LandAirMek.LAM_LANDING_GEAR);
            getMek().removeCriticalSlots(Mek.LOC_CENTER_TORSO, crit);
            clearCrit(Mek.LOC_CENTER_TORSO, lgSlot);
            getMek().setCritical(Mek.LOC_CENTER_TORSO, lgSlot, crit);
        }
        // Replace any fixed spreadable equipment
        List<Mounted<?>> toRemove = getMek().getMisc().stream()
              .filter(m -> (m.getLocation() == Entity.LOC_NONE)
                    && UnitUtil.isFixedLocationSpreadEquipment(m.getType()))
              .collect(Collectors.toList());
        for (Mounted<?> mounted : toRemove) {
            UnitUtil.removeMounted(getMek(), mounted);
            MekUtil.createSpreadMounts(getMek(), mounted.getType());
        }
        refresh.refreshBuild();
    }

    /**
     * Removes equipment placed in head locations that are needed for a cockpit. For most cockpit types, this is all but
     * the fourth slot.
     *
     * @param small If true, only clears the first four slots.
     * @param dual  If true, removes all equipment mounted in the head.
     */
    private void clearCritsForCockpit(boolean small, boolean dual) {
        for (int slot = 0; slot < (small ? 4 : 6); slot++) {
            if ((slot == 3) && !dual && !small) {
                continue;
            }
            clearCrit(Mek.LOC_HEAD, slot);
        }
    }

    private void clearCritsForGyro(int first, int numSlots) {
        for (int i = first; i < first + numSlots; i++) {
            clearCrit(Mek.LOC_CENTER_TORSO, i);
            getMek().setCritical(Mek.LOC_CENTER_TORSO, i, null);
        }
    }

    /**
     * Removes equipment placed in the given critical slot to clear the space for a system critical
     */
    private void clearCrit(int loc, int slotNum) {
        final CriticalSlot crit = getMek().getCritical(loc, slotNum);
        Mounted<?> mounted = null;
        if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
            mounted = crit.getMount();
        }
        if (mounted == null) {
            return;
        }
        UnitUtil.removeCriticalSlots(getMek(), mounted);
        if (crit.getMount2() != null) {
            UnitUtil.removeCriticalSlots(getMek(), crit.getMount2());
        }

        // Check linking after you remove everything.
        try {
            MekFileParser.postLoadInit(getMek());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        if (crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
            UnitUtil.changeMountStatus(getMek(), mounted, Entity.LOC_NONE, Entity.LOC_NONE,
                  false);
            if (crit.getMount2() != null) {
                UnitUtil.changeMountStatus(getMek(), crit.getMount2(), Entity.LOC_NONE, Entity.LOC_NONE,
                      false);
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
        panLAMFuel.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panArmor.addListener(this);
        panMovement.addListener(this);
        panArmorAllocation.addListener(this);
        panPatchwork.addListener(this);
        panLAMFuel.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void createISMounts(EquipmentType structure) {
        int isCount;
        getMek().setStructureType(EquipmentType.getStructureType(structure));
        getMek().setStructureTechLevel(structure.getStaticTechLevel().getCompoundTechLevel(structure.isClan()));

        isCount = structure.getNumCriticalSlots(getMek());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMek().addEquipment(
                      Mounted.createMounted(getMek(), structure),
                      Entity.LOC_NONE, false);
            } catch (Exception ex) {
                LOGGER.error("", ex);
            }
        }
    }

    /**
     * Calculates required engine rating for speed and tonnage and updates engine if possible.
     *
     * @return true if the new engine is legal for rating, space, and tech level
     */
    private boolean recalculateEngineRating(int walkMP, double tonnage) {
        int rating = walkMP * (int) tonnage;
        if (getMek().isPrimitive()) {
            rating = (int) Math.ceil((rating * 1.2) / 5.0) * 5;
        }
        int oldRating = getMek().getEngine().getRating();
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
                  && !hasCTSpace(engine, getMek().getGyroType(), getMek().getCockpitType())) {
                JOptionPane.showMessageDialog(
                      this, "There is not enough space in the center torso for the required engine.",
                      "Bad Engine", JOptionPane.ERROR_MESSAGE);
                panChassis.setEngineRating(oldRating);
                return false;
            } else {
                engine.setBaseChassisHeatSinks(getMek().getEngine()
                      .getBaseChassisHeatSinks(getMek().hasCompactHeatSinks()));
                getMek().setEngine(engine);
                MekUtil.updateAutoSinks(getMek(), getMek().hasCompactHeatSinks());
                resetSystemCrits();
            }
        }
        return true;
    }

    private boolean hasCTSpace(Engine engine, int gyroType, int cockpitType) {
        if (getMek().isSuperHeavy()) {
            return true;
        }
        int crits = 10;
        if (engine.getEngineType() == Engine.COMPACT_ENGINE) {
            crits -= 3;
        } else if (engine.hasFlag(Engine.LARGE_ENGINE)) {
            crits += 2;
        }
        if (gyroType == Mek.GYRO_COMPACT) {
            crits -= 2;
        } else if (gyroType == Mek.GYRO_XL) {
            crits += 2;
        }
        if ((cockpitType == Mek.COCKPIT_TORSO_MOUNTED) || (cockpitType == Mek.COCKPIT_VRRP)) {
            crits += 2;
        }
        return crits <= 12;
    }

    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {
        getMek().setArmorTechLevel(aTechLevel);
        getMek().setArmorType(at);
        final EquipmentType armor = ArmorType.of(at, TechConstants.isClan(aTechLevel));
        int armorCount = armor.getNumCriticalSlots(getMek());

        if (armorCount < 1) {
            return;
        }
        // auto-place stealth crits
        if (getMek().getArmorType(0) == EquipmentType.T_ARMOR_STEALTH) {
            Mounted<?> mount = MekUtil.createSpreadMounts(
                  getMek(),
                  EquipmentType.get(EquipmentType.getArmorTypeName(
                        getMek().getArmorType(0), false)));
            if (mount == null) {
                JOptionPane.showMessageDialog(null,
                      "Stealth Armor does not fit in location.",
                      "Resetting to Standard Armor",
                      JOptionPane.INFORMATION_MESSAGE);
                getMek().setArmorType(EquipmentType.T_ARMOR_STANDARD);
                getMek().setArmorTechLevel(TechConstants.T_INTRO_BOX_SET);
                panArmor.setFromEntity(getMek());
            }
        } else {
            for (; armorCount > 0; armorCount--) {
                try {
                    getMek().addEquipment(Mounted.createMounted(getMek(), armor), Entity.LOC_NONE, false);
                } catch (Exception ignored) {
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
        getMek().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void clanNameChanged(String clanName) {
        getMek().setClanChassisName(clanName);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getMek().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getMek().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getMek().setSource(source);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void mulIdChanged(int mulId) {
        getMek().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getMek().isClan()) || (mixed != getMek().isMixedTech())) {
            getMek().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getMek().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (panArmor.isPatchwork() && !getTechManager().isLegal(Entity.getPatchworkArmorAdvancement())) {
            panArmor.setPatchwork(false);
            armorTypeChanged(panArmor.getArmorType(), panArmor.getArmorTechConstant());
        }
        if (getMek().hasPatchworkArmor()) {
            for (int loc = 0; loc < getMek().locations(); loc++) {
                if (!getTechManager().isLegal(panPatchwork.getArmor(loc))) {
                    getMek().setArmorType(EquipmentType.T_ARMOR_STANDARD, TechConstants.T_INTRO_BOX_SET);
                    UnitUtil.resetArmor(getMek(), loc);
                }
            }
        } else if (!getTechManager().isLegal(panArmor.getArmor())) {
            UnitUtil.removeISorArmorMounts(getMek(), false);
        }
        // If we have a large engine, a drop in tech level may make it unavailable, and we will need to reduce speed
        // to a legal value.
        if (getMek().getEngine().hasFlag(Engine.LARGE_ENGINE)
              && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            if (getMek().isPrimitive()) {
                walk = 400 / (int) (getMek().getWeight() * 1.2);
            } else {
                walk = 400 / (int) getMek().getWeight();
            }
            recalculateEngineRating(walk, getMek().getWeight());
            getMek().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getMek());
            JOptionPane.showMessageDialog(
                  this, String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                  "Bad Engine", JOptionPane.ERROR_MESSAGE);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getMek(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        if (!getTechManager().isLegal(Mek.getRiscHeatSinkOverrideKitAdvancement())) {
            getMek().setRiscHeatSinkOverrideKit(false);
        }
        panChassis.refresh();
        panHeat.refresh();
        panArmor.refresh();
        panArmorAllocation.setFromEntity(getMek());
        panPatchwork.setFromEntity(getMek());
        refresh.refreshBuild();
        addAllListeners();
        panMovement.refresh();
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
        if (!recalculateEngineRating(panMovement.getWalk(), tonnage)) {
            panChassis.setFromEntity(getMek());
            return;
        }
        boolean changedSuperHeavyStatus = getMek().isSuperHeavy() != tonnage > 100;

        getMek().setWeight(tonnage);

        if (changedSuperHeavyStatus) {
            // if we switch from being superheavy to not being superheavy, remove crits
            for (Mounted<?> mount : getMek().getEquipment()) {
                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                    UnitUtil.removeCriticalSlots(getMek(), mount);
                    UnitUtil.changeMountStatus(getMek(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
                }
            }
            if (tonnage > 100) {
                getMek().setGyroType(Mek.GYRO_SUPERHEAVY);
                if (getMek().isTripodMek()) {
                    cockpitChanged(getMek().hasAdvancedFireControl() ? Mek.COCKPIT_SUPERHEAVY_TRIPOD
                          : Mek.COCKPIT_SUPERHEAVY_TRIPOD_INDUSTRIAL);
                } else {
                    cockpitChanged(getMek().hasAdvancedFireControl() ? Mek.COCKPIT_SUPERHEAVY
                          : Mek.COCKPIT_SUPERHEAVY_INDUSTRIAL);
                }
            } else {
                getMek().setGyroType(Mek.GYRO_STANDARD);
                if (getMek().isTripodMek()) {
                    cockpitChanged(
                          getMek().hasAdvancedFireControl() ? Mek.COCKPIT_TRIPOD : Mek.COCKPIT_TRIPOD_INDUSTRIAL);
                } else {
                    cockpitChanged(getMek().hasAdvancedFireControl() ? Mek.COCKPIT_STANDARD : Mek.COCKPIT_INDUSTRIAL);
                }
            }
        }

        // Force recalculation of walk MP. Set from chassis panel in case superheavy
        // flag changed
        final Engine engine = panChassis.getEngine();
        engine.setBaseChassisHeatSinks(getMek().getEngine()
              .getBaseChassisHeatSinks(getMek().hasCompactHeatSinks()));
        getMek().setEngine(engine);
        getMek().autoSetInternal();
        if (getMek().isSuperHeavy()) {
            getMek().setOriginalJumpMP(0);
        }
        if (changedSuperHeavyStatus) {
            // Internal structure crits may change
            UnitUtil.removeISorArmorMounts(getMek(), true);

            // The number of critical slots that the armor should take up may have changed.
            // You might notice this doesn't handle patchwork armor.
            // This is because to my knowledge how patchwork armor is
            //      supposed to work on superheavies has never been explained.
            if (!getMek().hasPatchworkArmor()) {
                int at = getMek().getArmorType(0);
                int aTechLevel = getMek().getArmorTechLevel(0);
                UnitUtil.removeISorArmorMounts(getMek(), false);
                createArmorMountsAndSetArmorType(at, aTechLevel);
            }

            createISMounts(panChassis.getStructure());
            resetSystemCrits();
            panMovement.setFromEntity(getMek());
        }
        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getMek().setOmni(omni);
        getMek().getEngine().setBaseChassisHeatSinks(omni ? Math.max(0, panHeat.getBaseCount()) : -1);
        panHeat.setFromMek(getMek());
        MekUtil.updateAutoSinks(getMek(), getMek().hasCompactHeatSinks());
        refresh.refreshPreview();
    }

    @Override
    public void typeChanged(int baseType, int motiveType, long etype) {
        boolean industrial = false;
        switch (baseType) {
            case BMChassisView.BASE_TYPE_INDUSTRIAL:
                industrial = true;
                // fall through
            case BMChassisView.BASE_TYPE_STANDARD:
                boolean primitive = getMek().isPrimitive();
                if (motiveType == BMChassisView.MOTIVE_TYPE_BIPED) {
                    if (((getMek().getEntityType() & Entity.ETYPE_BIPED_MEK) == 0)
                          || ((getMek().getEntityType() & Entity.ETYPE_LAND_AIR_MEK) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_BIPED_MEK, primitive, industrial, getMek());
                    }
                } else if (motiveType == BMChassisView.MOTIVE_TYPE_QUAD) {
                    if (((getMek().getEntityType() & Entity.ETYPE_QUAD_MEK) == 0)
                          || ((getMek().getEntityType() & Entity.ETYPE_QUADVEE) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_QUAD_MEK, primitive, industrial, getMek());
                    }
                } else if ((getMek().getEntityType() & Entity.ETYPE_TRIPOD_MEK) == 0) {
                    eSource.createNewUnit(Entity.ETYPE_TRIPOD_MEK, primitive, industrial, getMek());
                }
                break;
            case BMChassisView.BASE_TYPE_LAM:
                if (getMek() instanceof LandAirMek) {
                    ((LandAirMek) getMek()).setLAMType(motiveType);
                } else {
                    eSource.createNewUnit(Entity.ETYPE_LAND_AIR_MEK, getMek());
                }
                break;
            case BMChassisView.BASE_TYPE_QUADVEE:
                if (getMek() instanceof QuadVee) {
                    if (motiveType != ((QuadVee) getMek()).getMotiveType()) {
                        Optional<MiscMounted> mount = getMek().getMisc().stream()
                              .filter(m -> m.getType().hasFlag(MiscType.F_TRACKS))
                              .findAny();
                        mount.ifPresent(mounted -> UnitUtil.removeMounted(getMek(), mounted));

                        if (motiveType == QuadVee.MOTIVE_WHEEL) {
                            ((QuadVee) getMek()).setMotiveType(QuadVee.MOTIVE_WHEEL);
                            MekUtil.createSpreadMounts(getMek(),
                                  EquipmentType.get(EquipmentTypeLookup.QUADVEE_WHEELS));
                        } else {
                            ((QuadVee) getMek()).setMotiveType(QuadVee.MOTIVE_TRACK);
                            MekUtil.createSpreadMounts(getMek(),
                                  EquipmentType.get(EquipmentTypeLookup.MEK_TRACKS));
                        }
                    }
                } else {
                    eSource.createNewUnit(Entity.ETYPE_QUADVEE, getMek());
                }
                break;
        }
        if (getMek().isIndustrial() != industrial) {
            getMek().setStructureType(
                  industrial ? EquipmentType.T_STRUCTURE_INDUSTRIAL : EquipmentType.T_STRUCTURE_STANDARD);
        }

        refresh();
        refresh.refreshAll();
    }

    @Override
    public void structureChanged(EquipmentType structure) {
        UnitUtil.removeISorArmorMounts(getMek(), true);
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
            panChassis.setEngine(getMek().getEngine());
            panChassis.addListener(this);
        } else {
            // Make sure we keep same number of base heat sinks for Omni's
            engine.setBaseChassisHeatSinks(getMek().getEngine()
                  .getBaseChassisHeatSinks(getMek().hasCompactHeatSinks()));
            getMek().setEngine(engine);
            resetSystemCrits();
            // If the new engine has more weight-free heat sinks than are currently
            // installed, add the extras.
            int newHS = engine.getWeightFreeEngineHeatSinks() - getMek().heatSinks();
            if (newHS > 0) {
                MekUtil.addHeatSinkMounts(getMek(), newHS, panHeat.getHeatSinkType());
            }
            MekUtil.updateAutoSinks(getMek(), getMek().hasCompactHeatSinks());
            getMek().resetSinks();
            panMovement.setFromEntity(getMek());
            panHeat.setFromMek(getMek());
            refreshSummary();
            refresh.refreshEquipment();
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
            panChassis.setGyroType(getMek().getGyroType());
            panChassis.addListener(this);
        } else {
            getMek().setGyroType(gyroType);
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
            panChassis.setCockpitType(getMek().getCockpitType());
            panChassis.addListener(this);
        } else {
            getMek().setCockpitType(cockpitType);
            if ((cockpitType != Mek.COCKPIT_INTERFACE)
                  && (getMek().getGyroType() == Mek.GYRO_NONE)) {
                gyroChanged(Mek.GYRO_STANDARD);
            }
            panChassis.refresh(); // Changing from interface may require adding a gyro
            resetSystemCrits();
            refreshSummary();
            refresh.refreshPreview();
            refresh.refreshStatus();
        }
    }

    @Override
    public void enhancementChanged(EquipmentType enhancement) {
        MekUtil.removeEnhancements(getMek());
        if (null != enhancement) {
            if (enhancement.hasFlag(MiscType.F_MASC)) {
                Mounted<?> mount = Mounted.createMounted(getMek(), enhancement);
                try {
                    getMek().addEquipment(mount, Entity.LOC_NONE, false);
                } catch (LocationFullException lfe) {
                    // this can't happen, we add to Entity.LOC_NONE
                }
            } else {
                MekUtil.createSpreadMounts(getMek(), enhancement);
            }
        }
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshEquipment();
    }

    @Override
    public void fullHeadEjectChanged(boolean eject) {
        getMek().setFullHeadEject(eject);
    }

    @Override
    public void dniCockpitModChanged(boolean hasMod) {
        if (hasMod && !getMek().hasDNICockpitMod()) {
            MiscType dniMod = (MiscType) EquipmentType.get("DNICockpitModification");
            if (dniMod != null) {
                try {
                    getMek().addEquipment(dniMod, Entity.LOC_NONE);
                } catch (Exception ignored) {
                    // 0-crit equipment shouldn't fail to add
                }
            }
        } else if (!hasMod && getMek().hasDNICockpitMod()) {
            for (MiscMounted mounted : getMek().getMisc()) {
                if (mounted.getType().hasFlag(MiscType.F_DNI_COCKPIT_MOD)) {
                    getMek().removeMisc(mounted.getType().getInternalName());
                    break;
                }
            }
        }
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void eiCockpitChanged(boolean hasEI) {
        if (hasEI && !getMek().hasEiCockpit()) {
            MiscType eiInterface = (MiscType) EquipmentType.get("EIInterface");
            if (eiInterface != null) {
                try {
                    getMek().addEquipment(eiInterface, Entity.LOC_NONE);
                } catch (Exception ignored) {
                    // 0-crit equipment shouldn't fail to add
                }
            }
        } else if (!hasEI && getMek().hasEiCockpit()) {
            for (MiscMounted mounted : getMek().getMisc()) {
                if (mounted.getType().hasFlag(MiscType.F_EI_INTERFACE)) {
                    getMek().removeMisc(mounted.getType().getInternalName());
                    break;
                }
            }
        }
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void damageInterruptCircuitChanged(boolean hasDIC) {
        if (hasDIC && !getMek().hasDamageInterruptCircuit()) {
            MiscType dicMod = (MiscType) EquipmentType.get("DamageInterruptCircuit");
            if (dicMod != null) {
                try {
                    getMek().addEquipment(dicMod, Entity.LOC_NONE);
                } catch (Exception ignored) {
                    // 0-crit equipment shouldn't fail to add
                }
            }
        } else if (!hasDIC && getMek().hasDamageInterruptCircuit()) {
            for (MiscMounted mounted : getMek().getMisc()) {
                if (mounted.getType().hasFlag(MiscType.F_DAMAGE_INTERRUPT_CIRCUIT)) {
                    getMek().removeMisc(mounted.getType().getInternalName());
                    break;
                }
            }
        }
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getMek());
        refresh.refreshAll();
    }

    @Override
    public void heatSinksChanged(EquipmentType hsType, int count) {
        // if we have the same type of heat sink, then we should not remove the existing
        // heat sinks
        int currentSinks = MekUtil.countActualHeatSinks(getMek());
        if (getMek().hasWorkingMisc(hsType.getInternalName())) {
            if (count < currentSinks) {
                MekUtil.removeHeatSinks(getMek(), currentSinks - count);
            } else if (count > currentSinks) {
                MekUtil.addHeatSinkMounts(getMek(), count - currentSinks, hsType);
            }
        } else {
            MekUtil.removeHeatSinks(getMek(), count);
            MekUtil.addHeatSinkMounts(getMek(), count, hsType);
            // If we're switching to prototype doubles, start with the assumption that the
            // integrated sinks are singles,
            // with a minimum of one double
            if (hsType.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) {
                redistributePrototypeHS(Math.max(1, count - UnitUtil.getCriticalFreeHeatSinks(getMek(), false)));
            }
        }
        getMek().resetSinks();
        panHeat.setFromMek(getMek());
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        getMek().getEngine().setBaseChassisHeatSinks(Math.max(0, count));
        MekUtil.updateAutoSinks(getMek(), panHeat.getHeatSinkType().hasFlag(MiscType.F_COMPACT_HEAT_SINK));
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void riscHeatSinkOverrideKitChanged(boolean hasKit) {
        getMek().setRiscHeatSinkOverrideKit(hasKit);
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void redistributePrototypeHS(int prototype) {
        int netChange = prototype - getMek().countWorkingMisc(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE);
        if (netChange < 0) {
            List<Mounted<?>> doubles = getMek().getMisc().stream()
                  .filter(m -> m.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE))
                  .collect(Collectors.toList());
            for (int i = 0; i < -netChange; i++) {
                // Since we're not changing the total count, there should always be enough
                // prototype
                // doubles to switch over.
                if (i >= doubles.size()) {
                    LOGGER.warn("Not enough prototype double heat sinks to switch to single");
                }
                UnitUtil.removeMounted(getMek(), doubles.get(i));
            }
            MekUtil.addHeatSinkMounts(getMek(), -netChange, EquipmentType.get(EquipmentTypeLookup.SINGLE_HS));
        } else if (netChange > 0) {
            // Find all the single heat sinks, and prioritize the ones that are already
            // assigned critical slots
            List<Mounted<?>> singles = getMek().getMisc().stream()
                  .filter(m -> UnitUtil.isHeatSink(m.getType())
                        && !m.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE))
                  .sorted(Comparator.comparingInt(m -> m.getLocation() == Mek.LOC_NONE ? 1 : 0))
                  .collect(Collectors.toList());
            for (int i = 0; i < netChange; i++) {
                if (i >= singles.size()) {
                    LOGGER.warn("Not enough single heat sinks to switch to prototype double");
                }
                UnitUtil.removeMounted(getMek(), singles.get(i));
            }
            MekUtil.addHeatSinkMounts(getMek(), netChange, panHeat.getHeatSinkType());
        }
        MekUtil.updateAutoSinks(getMek(), false);
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshSummary();
        refresh.refreshBuild();
        refresh.refreshPreview();
        panHeat.setFromMek(getMek());
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            UnitUtil.removeISorArmorMounts(getMek(), false);
            createArmorMountsAndSetArmorType(at, aTechLevel);
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getMek());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmor.setFromEntity(getMek(), true);
        panArmorAllocation.setFromEntity(getMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorTonnageChanged(double tonnage) {
        getMek().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        panArmorAllocation.setFromEntity(getMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getMek());
        getMek().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMek());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getMek())
              .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getMek());
        double totalTonnage = getMek().getWeight();
        double remainingTonnage = TestEntity.floor(
              totalTonnage - currentTonnage, Ceil.HALF_TON);

        double maxArmor = MathUtility.clamp(getMek().getArmorWeight() + remainingTonnage, 0,
              UnitUtil.getMaximumArmorTonnage(getMek()));
        getMek().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMek());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getMek());
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void walkChanged(int walkMP) {
        if (!recalculateEngineRating(walkMP, panChassis.getTonnage())) {
            panMovement.setFromEntity(getMek());
            return;
        }
        getMek().setOriginalWalkMP(walkMP);
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getMek());
        panHeat.setFromMek(getMek());
        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, @Nullable EquipmentType jumpJet) {
        // Don't set jumpMP for UMU.
        if (jumpJet == null) {
            getMek().setOriginalJumpMP(0);
            jumpMP = 0;
        } else if (jumpJet.is(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER)) {
            addOrRemoveMekMechanicalJumpBoosters(jumpMP, jumpJet);
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET)) {
            getMek().setOriginalJumpMP(jumpMP);
        } else {
            getMek().setOriginalJumpMP(0);
        }

        // Remove or add jump jet equipment according to jump MP (if not Mek Mechanical Jump Boosters)
        if (jumpJet != null && !jumpJet.is(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER)) {
            List<Mounted<?>> jjs = getMek().getMisc().stream()
                  .filter(m -> jumpJet.equals(m.getType()))
                  .collect(Collectors.toList());
            while (jjs.size() > jumpMP) {
                UnitUtil.removeMounted(getMek(), jjs.remove(jjs.size() - 1));
            }
            while (jumpMP > jjs.size()) {
                try {
                    UnitUtil.addMounted(getMek(), Mounted.createMounted(getMek(), jumpJet), Entity.LOC_NONE, false);
                } catch (LocationFullException ignored) {
                    // Adding to LOC_NONE
                }
                jumpMP--;
            }
        }

        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getMek());
    }

    private void addOrRemoveMekMechanicalJumpBoosters(final int jumpMP, EquipmentType jumpJet) {
        assert (jumpJet.is(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER));
        if (jumpMP == 0) {
            UnitUtil.removeAllMounted(getMek(), jumpJet);
        } else {
            Optional<MiscMounted> mekMechanicalJumpBooster = getMek().getMisc().stream()
                  .filter(m -> m.is(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER))
                  .findFirst();
            if (mekMechanicalJumpBooster.isEmpty()) {
                MiscMounted createdMount = (MiscMounted) MekUtil.createSpreadMounts(getMek(), jumpJet);
                if (createdMount != null) {
                    mekMechanicalJumpBooster = Optional.of(createdMount);
                }
            }
            mekMechanicalJumpBooster.ifPresent(m -> m.setSize(jumpMP));
        }
    }


    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        List<Mounted<?>> jjs = getMek().getMisc().stream()
              .filter(m -> m.getType().hasFlag(MiscType.F_JUMP_JET)
                    || m.getType().hasFlag(MiscType.F_UMU))
              .filter(m -> !jumpJet.equals(m.getType()))
              .collect(Collectors.toList());
        jjs.forEach(jj -> UnitUtil.removeMounted(getMek(), jj));
        jumpChanged(panMovement.getJump(), jumpJet);
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getMek().initializeArmor(front, location);
        if (getMek().hasRearArmor(location)) {
            getMek().initializeRearArmor(rear, location);
        }
        if (panArmor.getArmorType() == EquipmentType.T_ARMOR_PATCHWORK) {
            getMek().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getMek()));
        }
        panArmor.setFromEntity(getMek(), true);
        panArmorAllocation.setFromEntity(getMek());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        double pointsToAllocate = TestEntity.getArmorPoints(getMek());
        double maxArmor = UnitUtil.getMaximumArmorPoints(getMek());
        if (pointsToAllocate > maxArmor) {
            pointsToAllocate = maxArmor;
        }
        double percent = pointsToAllocate / maxArmor;
        int headMaxArmor = 9;
        if (getMek().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        // put 5 times the percentage of total possible armor into the head
        int headArmor = (int) Math.min(Math.floor(percent * headMaxArmor * 5), headMaxArmor);
        getMek().initializeArmor(headArmor, Mek.LOC_HEAD);
        pointsToAllocate -= headArmor;
        maxArmor -= headMaxArmor;
        // recalculate percentage for remainder
        percent = pointsToAllocate / maxArmor;
        for (int location = 0; location < getMek().locations(); location++) {
            double IS = (getMek().getInternal(location) * 2);
            double allocate = Math.min(IS * percent, pointsToAllocate);
            switch (location) {
                case Mek.LOC_HEAD:
                    break;
                case Mek.LOC_CENTER_TORSO:
                case Mek.LOC_LEFT_TORSO:
                case Mek.LOC_RIGHT_TORSO:
                    int rear = (int) Math.floor(allocate * .25);
                    int front = (int) Math.ceil(allocate * .75);
                    // Make sure rounding doesn't add a point to this location,
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
                    getMek().initializeArmor(front, location);
                    getMek().initializeRearArmor(rear, location);
                    break;
                default:
                    getMek().initializeArmor((int) allocate, location);
                    pointsToAllocate -= (int) allocate;
                    break;
            }
        }
        allocateLeftoverPoints(pointsToAllocate);

        panArmorAllocation.setFromEntity(getMek());
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
        int headMaxArmor = 9;
        if (getMek().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        while (points >= 1) {
            // if two or more are left, add armor to symmetrical locations,
            // to torso, legs, arms, in that order
            if (points >= 2) {
                if (((getMek().getOArmor(Mek.LOC_LEFT_TORSO) + getMek().getOArmor(Mek.LOC_LEFT_TORSO,
                      true)) < (getMek().getOInternal(Mek.LOC_LEFT_TORSO) * 2))
                      && ((getMek().getOArmor(Mek.LOC_RIGHT_TORSO) + getMek().getOArmor(
                      Mek.LOC_RIGHT_TORSO, true)) < (getMek()
                      .getOInternal(Mek.LOC_RIGHT_TORSO) * 2))) {
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_TORSO) + 1,
                          Mek.LOC_LEFT_TORSO);
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_TORSO) + 1,
                          Mek.LOC_RIGHT_TORSO);
                    points -= 2;
                } else if ((getMek().getOArmor(Mek.LOC_LEFT_LEG) < (getMek()
                      .getOInternal(Mek.LOC_LEFT_LEG) * 2))
                      && (getMek().getOArmor(Mek.LOC_RIGHT_LEG) < (getMek()
                      .getOInternal(Mek.LOC_RIGHT_LEG) * 2))) {
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_LEG) + 1,
                          Mek.LOC_LEFT_LEG);
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_LEG) + 1,
                          Mek.LOC_RIGHT_LEG);
                    points -= 2;
                } else if ((getMek().getOArmor(Mek.LOC_LEFT_ARM) < (getMek()
                      .getOInternal(Mek.LOC_LEFT_ARM) * 2))
                      && (getMek().getOArmor(Mek.LOC_RIGHT_ARM) < (getMek()
                      .getOInternal(Mek.LOC_RIGHT_ARM) * 2))) {
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_ARM) + 1,
                          Mek.LOC_LEFT_ARM);
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_ARM) + 1,
                          Mek.LOC_RIGHT_ARM);
                    points -= 2;
                }
                // otherwise, first add to the head, and then even out uneven
                // allocation
            } else if (getMek().getOArmor(Mek.LOC_HEAD) < headMaxArmor) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_HEAD) + 1,
                      Mek.LOC_HEAD);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_LEFT_TORSO) < getMek()
                  .getOArmor(Mek.LOC_RIGHT_TORSO)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_TORSO) + 1,
                      Mek.LOC_LEFT_TORSO);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_RIGHT_TORSO) < getMek()
                  .getOArmor(Mek.LOC_LEFT_TORSO)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_TORSO) + 1,
                      Mek.LOC_RIGHT_TORSO);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_RIGHT_ARM) < getMek()
                  .getOArmor(Mek.LOC_LEFT_ARM)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_ARM) + 1,
                      Mek.LOC_RIGHT_ARM);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_LEFT_ARM) < getMek()
                  .getOArmor(Mek.LOC_RIGHT_ARM)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_ARM) + 1,
                      Mek.LOC_LEFT_ARM);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_RIGHT_LEG) < getMek()
                  .getArmor(Mek.LOC_LEFT_LEG)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_RIGHT_LEG) + 1,
                      Mek.LOC_RIGHT_LEG);
                points--;
            } else if (getMek().getOArmor(Mek.LOC_LEFT_LEG) < getMek()
                  .getOArmor(Mek.LOC_RIGHT_LEG)) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_LEFT_LEG) + 1,
                      Mek.LOC_LEFT_LEG);
                points--;
                // if nothing is uneven, add to the CT
            } else if (((getMek().getOArmor(Mek.LOC_CENTER_TORSO) + getMek().getOArmor(
                  Mek.LOC_CENTER_TORSO, true)) < (getMek().getOInternal(Mek.LOC_CENTER_TORSO) * 2))) {
                getMek().initializeArmor(getMek().getOArmor(Mek.LOC_CENTER_TORSO) + 1,
                      Mek.LOC_CENTER_TORSO);
                points--;
            }
            // if only one is left, and head and CT have max, remove one from CT
            // so symmetric locations can get extra, unless they are already at
            // max
            if (points == 1) {
                if ((getMek().getOArmor(Mek.LOC_HEAD) == headMaxArmor)
                      && ((getMek().getOArmor(Mek.LOC_CENTER_TORSO)
                      + getMek().getOArmor(Mek.LOC_CENTER_TORSO, true)) == (getMek().getOInternal(Mek.LOC_CENTER_TORSO)
                      * 2))) {
                    getMek().initializeArmor(getMek().getOArmor(Mek.LOC_CENTER_TORSO) - 1, Mek.LOC_CENTER_TORSO);
                    points++;
                }
            }
            // if all locations have max, return
            boolean toReturn = true;
            for (int location = 0; location < getMek().locations(); location++) {
                double is = (getMek().getInternal(location) * 2);
                switch (location) {
                    case Mek.LOC_HEAD:
                        int headPoints = 3;
                        if (getMek().isSuperHeavy()) {
                            headPoints = 4;
                        }
                        if ((is + headPoints) > getMek().getOArmor(location)) {
                            toReturn = false;
                        }
                        break;
                    case Mek.LOC_CENTER_TORSO:
                    case Mek.LOC_LEFT_TORSO:
                    case Mek.LOC_RIGHT_TORSO:
                        if (is > (getMek().getOArmor(location) + getMek().getOArmor(
                              location, true))) {
                            toReturn = false;
                        }
                        break;
                    default:
                        if (is > getMek().getOArmor(location)) {
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
    public void patchworkChanged(int location, ArmorType armor) {
        UnitUtil.resetArmor(getMek(), location);

        int crits = armor.getPatchworkSlotsMekSV();
        if (getMek().isSuperHeavy()) {
            crits = (crits + 1) / 2;
        }
        if (getMek().getEmptyCriticalSlots(location) < crits) {
            JOptionPane.showMessageDialog(
                  null, armor.getName()
                        + " does not fit in location "
                        + getMek().getLocationName(location)
                        + ". Resetting to Standard Armor in this location.",
                  "Error",
                  JOptionPane.INFORMATION_MESSAGE);
            UnitUtil.resetArmor(getMek(), location);
        } else {
            getMek().setArmorType(armor.getArmorType(), location);
            getMek().setArmorTechLevel(armor.getTechLevel(getTechManager().getGameYear(), armor.isClan()), location);
            for (; crits > 0; crits--) {
                try {
                    getMek().addEquipment(Mounted.createMounted(getMek(), armor), location, false);
                } catch (LocationFullException ignored) {

                }
            }
        }
        getMek().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getMek()));
        panArmor.setFromEntity(getMek());
        panArmorAllocation.setFromEntity(getMek());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }
}
