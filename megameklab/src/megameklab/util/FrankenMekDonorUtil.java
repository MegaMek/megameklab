/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import megamek.common.CriticalSlot;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.logging.MMLogger;

public final class FrankenMekDonorUtil {
    private static final MMLogger LOGGER = MMLogger.create(FrankenMekDonorUtil.class);

    private record LocationEquipmentCopy(Mounted<?> mounted, int criticalSlots, boolean rearMounted) {}

    /**
     * Imports one FrankenMek donor location into the target, including structure data, armor, equipment, and any
     * location-specific systems that are copied with that body section.
     *
     * @param target   The FrankenMek being modified
     * @param donor    The unit donating the body section
     * @param location The location index to import
     * @throws LocationFullException If copied equipment cannot be added to the target
     * @throws EntityLoadingException If post-load initialization fails after the import
     */
    public static void importLocation(Mek target, Mek donor, int location)
          throws LocationFullException, EntityLoadingException {
        int donorLocationTonnage = getDonorLocationTonnage(donor, location);
        target.setFrankenMekStructureType(location,
              donor.isFrankenMek() ? donor.getFrankenMekStructureType(location) : donor.getStructureType(),
              donor.isFrankenMek() ? donor.getFrankenMekStructureTechLevel(location) : donor.getStructureTechLevel());
        target.setFrankenMekStructureTonnage(location, donorLocationTonnage);
        updateTechBaseForDonorLocation(target, donor, location);
        target.applyFrankenMekDonorLocationArmor(location, donor);
        replaceLocationEquipment(target, donor, location);
        MekFileParser.postLoadInit(target);
    }

    /**
     * Replaces the target location's donor-owned equipment with copies from the donor location while preserving system
     * criticals that belong to the target unit. Equipment that cannot fit is moved to unallocated storage.
     *
     * @param target   The unit receiving equipment
     * @param donor    The unit donating equipment
     * @param location The location index to replace
     * @throws LocationFullException If copied equipment cannot be added to the target or unallocated storage
     */
    public static void replaceLocationEquipment(Mek target, Mek donor, int location)
          throws LocationFullException {
        List<LocationEquipmentCopy> donorEquipment = collectLocationEquipmentCopies(donor, location);
        Map<Mounted<?>, Mounted<?>> copiedEquipment = new LinkedHashMap<>();
        deleteLocationEquipment(target, location);
        copyLocationSystems(target, donor, location);
        for (LocationEquipmentCopy equipmentCopy : donorEquipment) {
            addLocationEquipmentCopy(target, location, equipmentCopy, copiedEquipment);
        }
        restoreCopiedEquipmentLinks(copiedEquipment);
        addFallbackFrankenMekStructureCriticals(target, location, donorEquipment);
    }

    /**
     * Gets the tonnage that should be used for a donated location. FrankenMek donors keep per-location tonnage;
     * standard units donate body sections at their whole-unit weight.
     *
     * @param donor    The unit donating the body section
     * @param location The location index being donated
     * @return The donor tonnage to apply to that location
     */
    public static int getDonorLocationTonnage(Mek donor, int location) {
        return donor.isFrankenMek()
              ? donor.getFrankenMekStructureTonnage(location)
              : (int) Math.ceil(donor.getWeight());
    }

    /**
     * Validates whether the donor location tonnage can be installed in the target FrankenMek location.
     *
     * @param target       The FrankenMek being modified
     * @param location     The target location index
     * @param donorTonnage The tonnage of the donated body section
     * @return An explanation when the donor location is invalid, or {@code null} when it can be installed
     */
    public static @Nullable String getDonorLocationInvalidReason(Mek target, int location, int donorTonnage) {
        if (!target.isFrankenMek()) {
            return null;
        }
        int centerTorsoTonnage = target.getFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO);
        if (target.locationIsLeg(location) && (donorTonnage < centerTorsoTonnage)) {
            return "Legs cannot be lighter than the center torso (donor leg " + donorTonnage
                  + " tons, CT " + centerTorsoTonnage + " tons).";
        }
        if ((location != Mek.LOC_CENTER_TORSO) && (centerTorsoTonnage <= 100) && (donorTonnage > 100)) {
            return "Super-heavy parts cannot be installed on a center torso that is not Super-heavy.";
        }
        return null;
    }

    public static void updateMismatchedLegsFromDonorSources(Mek mek) {
        String firstLegDonor = null;
        for (int location = 0; location < mek.locations(); location++) {
            if (!mek.locationIsLeg(location)) {
                continue;
            }
            String legDonor = mek.getFrankenMekLocationSourceDisplayName(location);
            if (firstLegDonor == null) {
                firstLegDonor = legDonor;
            } else if (!firstLegDonor.equals(legDonor)) {
                mek.setMismatchedFrankenMekLegs(true);
                return;
            }
        }
        mek.setMismatchedFrankenMekLegs(false);
    }

    private static void updateTechBaseForDonorLocation(Mek target, Mek donor, int location) {
        boolean donorLocationClan = isDonorLocationClan(donor, location);
        if (location == Mek.LOC_CENTER_TORSO) {
            if (target.isClan() != donorLocationClan) {
                setTechBase(target, donorLocationClan, true);
            }
        } else if (!target.isMixedTech() && (target.isClan() != donorLocationClan)) {
            target.setMixedTech(true);
        }
    }

    private static boolean isDonorLocationClan(Mek donor, int location) {
        if (donor.isFrankenMek() && (location < donor.locations())) {
            return TechConstants.isClan(donor.getFrankenMekStructureTechLevel(location));
        }
        return donor.isClan();
    }

    private static void setTechBase(Mek target, boolean clan, boolean mixed) {
        SimpleTechLevel simpleTechLevel = SimpleTechLevel.convertCompoundToSimple(target.getTechLevel());
        target.setTechLevel(simpleTechLevel.getCompoundTechLevel(clan));
        target.setMixedTech(mixed);
    }

    private static void copyLocationSystems(Mek target, Mek donor, int location) {
        if (location == Mek.LOC_CENTER_TORSO) {
            copyCenterTorsoSystems(target, donor);
        } else if ((location == Mek.LOC_HEAD) && hasSystemCritical(donor, location, Mek.SYSTEM_COCKPIT)) {
            target.setCockpitType(donor.getCockpitType());
            resetCockpitCrits(target);
        }
    }

    private static void copyCenterTorsoSystems(Mek target, Mek donor) {
        int baseChassisHeatSinks = target.hasEngine()
              ? target.getEngine().getBaseChassisHeatSinks(target.hasCompactHeatSinks())
              : -1;
        EquipmentType donorHeatSinkType = getHeatSinkType(donor);
        copyHeatSinkType(target, donorHeatSinkType);
        copyEngineType(target, donor, baseChassisHeatSinks);
        target.setGyroType(donor.getGyroType());
        if (hasSystemCritical(donor, Mek.LOC_CENTER_TORSO, Mek.SYSTEM_COCKPIT)) {
            target.setCockpitType(donor.getCockpitType());
        }
        resetEngineAndGyroCrits(target);
        resetCockpitCrits(target);
        ensureMinimumEngineHeatSinks(target, donorHeatSinkType);
        MekUtil.updateAutoSinks(target, donorHeatSinkType.hasFlag(MiscType.F_COMPACT_HEAT_SINK));
        target.resetSinks();
    }

    private static void copyEngineType(Mek target, Mek donor, int baseChassisHeatSinks) {
        if (!donor.hasEngine()) {
            return;
        }
        Engine donorEngine = donor.getEngine();
        int rating = target.hasEngine() ? target.getEngine().getRating() : donorEngine.getRating();
        int flags = donorEngine.hasFlag(Engine.CLAN_ENGINE) ? Engine.CLAN_ENGINE : 0;
        if (target.isSuperHeavy()) {
            flags |= Engine.SUPERHEAVY_ENGINE;
        }
        Engine copiedEngine = new Engine(rating, donorEngine.getEngineType(), flags);
        copiedEngine.setBaseChassisHeatSinks(baseChassisHeatSinks);
        target.setEngine(copiedEngine);
    }

    private static void copyHeatSinkType(Mek target, EquipmentType donorHeatSinkType) {
        int heatSinkCount = MekUtil.countActualHeatSinks(target);
        if ((heatSinkCount == 0) || hasHeatSinkType(target, donorHeatSinkType)) {
            return;
        }
        removeAllHeatSinks(target);
        MekUtil.addHeatSinkMounts(target, heatSinkCount, donorHeatSinkType);
    }

    private static boolean hasHeatSinkType(Mek target, EquipmentType heatSinkType) {
        for (Mounted<?> mounted : target.getMisc()) {
            if (UnitUtil.isHeatSink(mounted) && isSameHeatSinkType(mounted.getType(), heatSinkType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSameHeatSinkType(EquipmentType first, EquipmentType second) {
        if (first.hasFlag(MiscType.F_COMPACT_HEAT_SINK) || second.hasFlag(MiscType.F_COMPACT_HEAT_SINK)) {
            return first.hasFlag(MiscType.F_COMPACT_HEAT_SINK) && second.hasFlag(MiscType.F_COMPACT_HEAT_SINK);
        }
        return first.getInternalName().equals(second.getInternalName());
    }

    private static void removeAllHeatSinks(Mek target) {
        List<Mounted<?>> heatSinks = new ArrayList<>();
        for (Mounted<?> mounted : target.getMisc()) {
            if (UnitUtil.isHeatSink(mounted)) {
                heatSinks.add(mounted);
            }
        }
        for (Mounted<?> heatSink : heatSinks) {
            UnitUtil.removeMounted(target, heatSink);
        }
    }

    private static EquipmentType getHeatSinkType(Mek mek) {
        for (Mounted<?> mounted : mek.getMisc()) {
            if (!UnitUtil.isHeatSink(mounted)) {
                continue;
            }
            if (mounted.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)) {
                return EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_2);
            }
            return mounted.getType();
        }
        return EquipmentType.get(EquipmentTypeLookup.SINGLE_HS);
    }

    private static void ensureMinimumEngineHeatSinks(Mek target, EquipmentType heatSinkType) {
        if (!target.hasEngine()) {
            return;
        }
        int newHeatSinks = target.getEngine().getWeightFreeEngineHeatSinks() - target.heatSinks();
        if (newHeatSinks > 0) {
            MekUtil.addHeatSinkMounts(target, newHeatSinks, heatSinkType);
        }
    }

    private static boolean hasSystemCritical(Mek mek, int location, int systemIndex) {
        for (int slot = 0; slot < mek.getNumberOfCriticalSlots(location); slot++) {
            CriticalSlot criticalSlot = mek.getCritical(location, slot);
            if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_SYSTEM)
                  && (criticalSlot.getIndex() == systemIndex)) {
                return true;
            }
        }
        return false;
    }

    private static void resetEngineAndGyroCrits(Mek target) {
        target.clearGyroCrits();
        target.clearEngineCrits();

        if (target.hasEngine()) {
            for (int slot : target.getEngine().getCenterTorsoCriticalSlots(target.getGyroType())) {
                clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, slot);
            }
            for (int slot : target.getEngine().getSideTorsoCriticalSlots()) {
                clearCriticalSlot(target, Mek.LOC_LEFT_TORSO, slot);
                clearCriticalSlot(target, Mek.LOC_RIGHT_TORSO, slot);
            }
            target.addEngineCrits();
        }
        addGyroCrits(target);
    }

    private static void addGyroCrits(Mek target) {
        switch (target.getGyroType()) {
            case Mek.GYRO_COMPACT:
                clearCriticalSlots(target, Mek.LOC_CENTER_TORSO, 3, 2);
                target.addCompactGyro();
                break;
            case Mek.GYRO_HEAVY_DUTY:
                clearCriticalSlots(target, Mek.LOC_CENTER_TORSO, 3, 4);
                target.addHeavyDutyGyro();
                break;
            case Mek.GYRO_XL:
                clearCriticalSlots(target, Mek.LOC_CENTER_TORSO, 3, 6);
                target.addXLGyro();
                break;
            case Mek.GYRO_NONE:
                UnitUtil.compactCriticalSlots(target, Mek.LOC_CENTER_TORSO);
                break;
            case Mek.GYRO_SUPERHEAVY:
                clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, 2);
                clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, 3);
                clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, 4);
                target.addSuperheavyGyro();
                break;
            default:
                clearCriticalSlots(target, Mek.LOC_CENTER_TORSO, 3, 4);
                target.addGyro();
                break;
        }
    }

    private static void resetCockpitCrits(Mek target) {
        target.clearCockpitCrits();
        switch (target.getCockpitType()) {
            case Mek.COCKPIT_COMMAND_CONSOLE:
                clearCritsForCockpit(target, false, true);
                target.addCommandConsole();
                break;
            case Mek.COCKPIT_DUAL:
                clearCritsForCockpit(target, false, true);
                target.addDualCockpit();
                break;
            case Mek.COCKPIT_SMALL:
                clearCritsForCockpit(target, true, false);
                target.addSmallCockpit();
                break;
            case Mek.COCKPIT_INTERFACE:
                clearCritsForCockpit(target, false, true);
                target.addInterfaceCockpit();
                break;
            case Mek.COCKPIT_TORSO_MOUNTED:
            case Mek.COCKPIT_VRRP:
                clearTorsoMountedCockpitSlots(target);
                target.addTorsoMountedCockpit(target.getCockpitType() == Mek.COCKPIT_VRRP);
                break;
            case Mek.COCKPIT_INDUSTRIAL:
                clearCritsForCockpit(target, false, false);
                target.addIndustrialCockpit();
                target.setArmorType(EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mek.COCKPIT_PRIMITIVE:
                clearCritsForCockpit(target, false, false);
                target.addPrimitiveCockpit();
                target.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE);
                break;
            case Mek.COCKPIT_PRIMITIVE_INDUSTRIAL:
                clearCritsForCockpit(target, false, false);
                target.addIndustrialPrimitiveCockpit();
                target.setArmorType(EquipmentType.T_ARMOR_COMMERCIAL);
                break;
            case Mek.COCKPIT_QUADVEE:
                clearCritsForCockpit(target, false, true);
                target.addQuadVeeCockpit();
                break;
            case Mek.COCKPIT_SUPERHEAVY_INDUSTRIAL:
                clearCritsForCockpit(target, false, false);
                target.addSuperheavyIndustrialCockpit();
                target.setArmorType(EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mek.COCKPIT_SUPERHEAVY_COMMAND_CONSOLE:
                clearCritsForCockpit(target, false, true);
                target.addSuperheavyCommandConsole();
                break;
            case Mek.COCKPIT_SMALL_COMMAND_CONSOLE:
                clearCritsForCockpit(target, true, true);
                target.addSmallCommandConsole();
                break;
            default:
                clearCritsForCockpit(target, false, false);
                int cockpitType = target.getCockpitType();
                target.addCockpit();
                target.setCockpitType(cockpitType);
                break;
        }
    }

    private static void clearTorsoMountedCockpitSlots(Mek target) {
        int[] centerEngineSlots = target.hasEngine()
              ? target.getEngine().getCenterTorsoCriticalSlots(target.getGyroType())
              : new int[] { 2 };
        int firstCockpitSlot = centerEngineSlots[centerEngineSlots.length - 1] + 1;
        if (firstCockpitSlot + 1 < target.getNumberOfCriticalSlots(Mek.LOC_CENTER_TORSO)) {
            clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, firstCockpitSlot);
            clearCriticalSlot(target, Mek.LOC_CENTER_TORSO, firstCockpitSlot + 1);
        }
        clearCriticalSlot(target, Mek.LOC_HEAD, 0);
        clearCriticalSlot(target, Mek.LOC_HEAD, 1);
        clearSideTorsoSlotForTorsoMountedCockpit(target, Mek.LOC_LEFT_TORSO);
        clearSideTorsoSlotForTorsoMountedCockpit(target, Mek.LOC_RIGHT_TORSO);
    }

    private static void clearSideTorsoSlotForTorsoMountedCockpit(Mek target, int location) {
        if (target.getEmptyCriticalSlots(location) >= 1) {
            return;
        }
        for (int slot = 0; slot < target.getNumberOfCriticalSlots(location); slot++) {
            CriticalSlot criticalSlot = target.getCritical(location, slot);
            if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                clearCriticalSlot(target, location, slot);
                return;
            }
        }
    }

    private static void clearCritsForCockpit(Mek target, boolean small, boolean dual) {
        for (int slot = 0; slot < (small ? 4 : 6); slot++) {
            if ((slot == 3) && !dual && !small) {
                continue;
            }
            clearCriticalSlot(target, Mek.LOC_HEAD, slot);
        }
    }

    private static void clearCriticalSlots(Mek target, int location, int firstSlot, int criticalSlots) {
        for (int slot = firstSlot; slot < firstSlot + criticalSlots; slot++) {
            clearCriticalSlot(target, location, slot);
        }
    }

    private static void clearCriticalSlot(Mek target, int location, int slot) {
        if ((slot < 0) || (slot >= target.getNumberOfCriticalSlots(location))) {
            return;
        }
        CriticalSlot criticalSlot = target.getCritical(location, slot);
        if ((criticalSlot == null) || (criticalSlot.getType() != CriticalSlot.TYPE_EQUIPMENT)) {
            return;
        }
        Mounted<?> mounted = criticalSlot.getMount();
        if (mounted != null) {
            UnitUtil.removeCriticalSlots(target, mounted);
            UnitUtil.changeMountStatus(target, mounted, Entity.LOC_NONE, Entity.LOC_NONE, mounted.isRearMounted());
        }
        Mounted<?> secondaryMount = criticalSlot.getMount2();
        if (secondaryMount != null) {
            UnitUtil.removeCriticalSlots(target, secondaryMount);
            UnitUtil.changeMountStatus(target, secondaryMount, Entity.LOC_NONE, Entity.LOC_NONE,
                  secondaryMount.isRearMounted());
        }
    }

    private static List<LocationEquipmentCopy> collectLocationEquipmentCopies(Mek source, int location) {
        List<LocationEquipmentCopy> equipmentCopies = new ArrayList<>();
        Map<Mounted<?>, Integer> groupedCopyIndexes = new LinkedHashMap<>();
        for (int slot = 0; slot < source.getNumberOfCriticalSlots(location); slot++) {
            CriticalSlot criticalSlot = source.getCritical(location, slot);
            if ((criticalSlot == null) || (criticalSlot.getType() != CriticalSlot.TYPE_EQUIPMENT)) {
                continue;
            }
            collectLocationEquipmentCopy(equipmentCopies, groupedCopyIndexes, criticalSlot.getMount());
            collectLocationEquipmentCopy(equipmentCopies, groupedCopyIndexes, criticalSlot.getMount2());
        }
        return equipmentCopies;
    }

    private static void collectLocationEquipmentCopy(List<LocationEquipmentCopy> equipmentCopies,
          Map<Mounted<?>, Integer> groupedCopyIndexes, @Nullable Mounted<?> mounted) {
        if (mounted == null) {
            return;
        }
        if (UnitUtil.isArmorOrStructure(mounted.getType())) {
            equipmentCopies.add(new LocationEquipmentCopy(mounted, 1, mounted.isRearMounted()));
            return;
        }
        Integer existingCopyIndex = groupedCopyIndexes.get(mounted);
        if (existingCopyIndex == null) {
            groupedCopyIndexes.put(mounted, equipmentCopies.size());
            equipmentCopies.add(new LocationEquipmentCopy(mounted, 1, mounted.isRearMounted()));
            return;
        }
        LocationEquipmentCopy existingCopy = equipmentCopies.get(existingCopyIndex);
        equipmentCopies.set(existingCopyIndex, new LocationEquipmentCopy(mounted, existingCopy.criticalSlots() + 1,
              mounted.isRearMounted()));
    }

    private static void deleteLocationEquipment(Mek target, int location) {
        Set<Mounted<?>> mountedEquipment = new LinkedHashSet<>();
        for (int slot = 0; slot < target.getNumberOfCriticalSlots(location); slot++) {
            CriticalSlot criticalSlot = target.getCritical(location, slot);
            if ((criticalSlot == null) || (criticalSlot.getType() != CriticalSlot.TYPE_EQUIPMENT)) {
                continue;
            }
            if (criticalSlot.getMount() != null) {
                mountedEquipment.add(criticalSlot.getMount());
            }
            if (criticalSlot.getMount2() != null) {
                mountedEquipment.add(criticalSlot.getMount2());
            }
        }
        for (Mounted<?> mounted : new ArrayList<>(target.getEquipment())) {
            if ((mounted.getLocation() == location) || (mounted.getSecondLocation() == location)) {
                mountedEquipment.add(mounted);
            }
        }
        for (Mounted<?> mounted : mountedEquipment) {
            deleteLocationEquipment(target, mounted, location);
        }
    }

    private static void deleteLocationEquipment(Mek target, Mounted<?> mounted, int location) {
        if (!mounted.getType().isSpreadable()) {
            UnitUtil.removeMounted(target, mounted);
            return;
        }

        UnitUtil.removeCriticalSlots(target, mounted, location);
        List<Integer> remainingLocations = mounted.allLocations();
        if (remainingLocations.isEmpty()) {
            UnitUtil.removeMounted(target, mounted);
        } else if (mounted.getLocation() == location) {
            UnitUtil.changeMountStatus(target, mounted, remainingLocations.get(0), Entity.LOC_NONE,
                  mounted.isRearMounted());
        }
    }

    private static void addLocationEquipmentCopy(Mek target, int location, LocationEquipmentCopy equipmentCopy,
          Map<Mounted<?>, Mounted<?>> copiedEquipment)
          throws LocationFullException {
        Mounted<?> mounted = copyMounted(target, equipmentCopy.mounted());
        copiedEquipment.put(equipmentCopy.mounted(), mounted);
        int criticalSlots = getCriticalSlotsForLocationCopy(target, mounted, equipmentCopy.criticalSlots());
        addMountedOrUnallocated(target, mounted, location, criticalSlots, equipmentCopy.rearMounted());
    }

    private static void restoreCopiedEquipmentLinks(Map<Mounted<?>, Mounted<?>> copiedEquipment) {
        for (Map.Entry<Mounted<?>, Mounted<?>> equipmentCopy : copiedEquipment.entrySet()) {
            Mounted<?> source = equipmentCopy.getKey();
            Mounted<?> linkedCopy = copiedEquipment.get(source.getLinked());
            if (linkedCopy == null) {
                continue;
            }
            Mounted<?> copied = equipmentCopy.getValue();
            if (source.getLinked().getCrossLinkedBy() == source) {
                copied.setCrossLinked(linkedCopy);
            } else {
                copied.setLinked(linkedCopy);
            }
        }
    }

    private static Mounted<?> copyMounted(Entity target, Mounted<?> source) {
        Mounted<?> copy = Mounted.createMounted(target, source.getType());
        copy.setArmored(source.isArmored());
        copy.setBaMountLoc(source.getBaMountLoc());
        copy.setFacing(source.getFacing());
        copy.setMekTurretMounted(source.isMekTurretMounted());
        copy.setOmniPodMounted(source.isOmniPodMounted());
        copy.setOriginalShots(source.getOriginalShots());
        copy.setShotsLeft(source.getBaseShotsLeft());
        copy.setSize(source.getSize());
        return copy;
    }

    private static int getCriticalSlotsForLocationCopy(Mek target, Mounted<?> mounted, int sourceCriticalSlots) {
        int criticalSlots = mounted.getType().isSpreadable() ? sourceCriticalSlots : mounted.getNumCriticalSlots();
        if (!mounted.getType().isSpreadable() && target.isSuperHeavy()) {
            criticalSlots = (int) Math.ceil(criticalSlots / 2.0);
        }
        return Math.max(1, criticalSlots);
    }

    private static void addMountedOrUnallocated(Mek target, Mounted<?> mounted, int location, int criticalSlots,
          boolean rearMounted)
          throws LocationFullException {
        if (target.getEmptyCriticalSlots(location) < criticalSlots) {
            // The copied equipment exists, but there is not enough room to place all of its crits in this location.
            LOGGER.warn("Not enough critical slots in {} for {} ({} required); moving copied equipment to unallocated.",
                  target.getLocationName(location), mounted.getName(), criticalSlots);
            target.addEquipment(mounted, Entity.LOC_NONE, rearMounted);
            UnitUtil.removeHiddenAmmo(mounted);
            return;
        }

        try {
            // There is enough visible space, but addEquipment/addCritical can still reject the placement.
            target.addEquipment(mounted, location, rearMounted);
            for (int slot = getCriticalSlotsAddedByDefault(target, mounted); slot < criticalSlots; slot++) {
                if (!target.addCritical(location, new CriticalSlot(mounted))) {
                    LOGGER.warn("Could not add all donor critical slots for {} in {}; "
                              + "moving copied equipment to unallocated.",
                          mounted.getName(), target.getLocationName(location));
                    moveMountedOrAddToUnallocated(target, mounted, rearMounted);
                    return;
                }
            }
            UnitUtil.removeHiddenAmmo(mounted);
        } catch (LocationFullException ex) {
            LOGGER.warn(ex, "Unable to place {} in {}; moving copied equipment to unallocated.", mounted.getName(),
                  target.getLocationName(location));
            moveMountedOrAddToUnallocated(target, mounted, rearMounted);
        }
    }

    private static void moveMountedOrAddToUnallocated(Mek target, Mounted<?> mounted, boolean rearMounted)
          throws LocationFullException {
        // Failed critical placement may leave some crit slots behind; always clear those before falling back.
        UnitUtil.removeCriticalSlots(target, mounted);
        if (target.getEquipment().contains(mounted)) {
            // addEquipment may have already inserted the mount before failing, so move that instance instead of adding
            // the same Mounted<?> to the equipment list again.
            UnitUtil.changeMountStatus(target, mounted, Entity.LOC_NONE, Entity.LOC_NONE, rearMounted);
        } else {
            // No partial add happened; this is the first time the mount enters the target entity.
            target.addEquipment(mounted, Entity.LOC_NONE, rearMounted);
        }
        UnitUtil.removeHiddenAmmo(mounted);
    }

    private static int getCriticalSlotsAddedByDefault(Mek target, Mounted<?> mounted) {
        if (mounted.getType().isSpreadable() || mounted.isSplitable()) {
            return 1;
        }
        int criticalSlots = mounted.getNumCriticalSlots();
        if (target.isSuperHeavy()) {
            criticalSlots = (int) Math.ceil(criticalSlots / 2.0);
        }
        return Math.max(1, criticalSlots);
    }

    private static void addFallbackFrankenMekStructureCriticals(Mek target, int location,
          List<LocationEquipmentCopy> copiedEquipment) throws LocationFullException {
        if (!target.isFrankenMek()) {
            return;
        }
        EquipmentType structure = target.getFrankenMekStructureEquipment(location);
        int fallbackSlots = target.getFrankenMekStructureCriticalSlots(location);
        if ((structure == null) || (fallbackSlots <= 0)
              || copiedEquipment.stream().anyMatch(copy -> structure.equals(copy.mounted().getType()))) {
            return;
        }
        for (int slot = 0; slot < fallbackSlots; slot++) {
            addMountedOrUnallocated(target, Mounted.createMounted(target, structure), location, 1, false);
        }
    }

    private FrankenMekDonorUtil() {
    }
}
