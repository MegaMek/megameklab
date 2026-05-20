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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.CriticalSlot;
import megamek.common.TechConstants;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class FrankenMekDonorUtilTest {

    private static class PartiallyAddingMek extends BipedMek {
        private boolean failNextLocationAdd = true;

        @Override
        public void addEquipment(Mounted<?> mounted, int location, boolean rearMounted) throws LocationFullException {
            if (failNextLocationAdd && (location != Entity.LOC_NONE)) {
                failNextLocationAdd = false;
                super.addEquipment(mounted, Entity.LOC_NONE, rearMounted);
                throw new LocationFullException("forced partial add");
            }
            super.addEquipment(mounted, location, rearMounted);
        }
    }

    private static Mek newTestMek() {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        mek.setWeight(25.0);
        mek.setEngine(new Engine(100, Engine.NORMAL_ENGINE, 0));
        return mek;
    }

    private static long countEquipment(Mek mek, EquipmentType equipmentType, int location) {
        return mek.getEquipment().stream()
              .filter(mounted -> equipmentType.equals(mounted.getType()) && (mounted.getLocation() == location))
              .count();
    }

    private static Mounted<?> findEquipment(Mek mek, EquipmentType equipmentType, int location) {
        return mek.getEquipment().stream()
              .filter(mounted -> equipmentType.equals(mounted.getType()) && (mounted.getLocation() == location))
              .findFirst()
              .orElse(null);
    }

    private static long countSystemCriticalSlots(Mek mek, int location, int systemIndex) {
        long count = 0;
        for (int slot = 0; slot < mek.getNumberOfCriticalSlots(location); slot++) {
            CriticalSlot criticalSlot = mek.getCritical(location, slot);
            if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_SYSTEM)
                  && (criticalSlot.getIndex() == systemIndex)) {
                count++;
            }
        }
        return count;
    }

    @Test
    void importClanCenterTorsoChangesInnerSphereTargetToClanMixed() throws Exception {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        Mek donor = newTestMek();
        donor.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);

        FrankenMekDonorUtil.importLocation(target, donor, Mek.LOC_CENTER_TORSO);

        assertTrue(target.isClan());
        assertTrue(target.isMixedTech());
    }

    @Test
    void importInnerSphereCenterTorsoChangesClanTargetToInnerSphereMixed() throws Exception {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
        Mek donor = newTestMek();

        FrankenMekDonorUtil.importLocation(target, donor, Mek.LOC_CENTER_TORSO);

        assertFalse(target.isClan());
        assertTrue(target.isMixedTech());
    }

    @Test
    void importClanNonCenterLocationKeepsInnerSphereBaseAndMakesMixed() throws Exception {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        Mek donor = newTestMek();
        donor.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);

        FrankenMekDonorUtil.importLocation(target, donor, Mek.LOC_RIGHT_ARM);

        assertFalse(target.isClan());
        assertTrue(target.isMixedTech());
    }

    @Test
    void importInnerSphereNonCenterLocationKeepsClanBaseAndMakesMixed() throws Exception {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
        Mek donor = newTestMek();

        FrankenMekDonorUtil.importLocation(target, donor, Mek.LOC_RIGHT_ARM);

        assertTrue(target.isClan());
        assertTrue(target.isMixedTech());
    }

    @Test
    void donorLocationCopyDeletesTargetEquipmentAndAddsFallbackStructure() throws Exception {
        EquipmentType endoSteel = EquipmentType.get(
              EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_ENDO_STEEL, false));
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureType(Mek.LOC_RIGHT_ARM, endoSteel);
        Mounted<?> oldMount = target.addEquipment(mediumLaser, Mek.LOC_RIGHT_ARM);

        Mek donor = newTestMek();
        donor.addEquipment(mediumLaser, Mek.LOC_RIGHT_ARM);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        assertFalse(target.getEquipment().contains(oldMount));
        assertEquals(1, countEquipment(target, mediumLaser, Mek.LOC_RIGHT_ARM));
        assertEquals(2, target.getNumberOfCriticalSlots(endoSteel, Mek.LOC_RIGHT_ARM));
    }

    @Test
    void donorLocationCopyMovesFallbackStructureSlotsToUnallocatedWhenLocationIsFull() throws Exception {
        EquipmentType endoSteel = EquipmentType.get(
              EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_ENDO_STEEL, false));
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureType(Mek.LOC_RIGHT_ARM, endoSteel);

        Mek donor = newTestMek();
        int donorEquipmentSlots = donor.getEmptyCriticalSlots(Mek.LOC_RIGHT_ARM);
        for (int slot = 0; slot < donorEquipmentSlots; slot++) {
            donor.addEquipment(mediumLaser, Mek.LOC_RIGHT_ARM);
        }

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        assertEquals(donorEquipmentSlots, countEquipment(target, mediumLaser, Mek.LOC_RIGHT_ARM));
        assertEquals(0, target.getNumberOfCriticalSlots(endoSteel, Mek.LOC_RIGHT_ARM));
        assertEquals(2, countEquipment(target, endoSteel, Entity.LOC_NONE));
    }

    @Test
    void donorLocationCopyDoesNotDuplicateRepeatedMultiSlotEquipment() throws Exception {
        EquipmentType largeLaser = EquipmentType.get("Large Laser");
        Mek target = newTestMek();
        Mek donor = newTestMek();
        donor.addEquipment(largeLaser, Mek.LOC_RIGHT_ARM);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        assertEquals(1, countEquipment(target, largeLaser, Mek.LOC_RIGHT_ARM));
        assertEquals(2, target.getNumberOfCriticalSlots(largeLaser, Mek.LOC_RIGHT_ARM));
    }

    @Test
    void donorLocationCopyPreservesSeparateArmorCriticalSlots() throws Exception {
        EquipmentType reactiveArmor = EquipmentType.get("IS Reactive");
        Mek target = newTestMek();
        Mek donor = newTestMek();
        Mounted<?> armorMount = donor.addEquipment(reactiveArmor, Mek.LOC_LEFT_TORSO);
        donor.addCritical(Mek.LOC_LEFT_TORSO, new CriticalSlot(armorMount));

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_LEFT_TORSO);

        assertEquals(2, countEquipment(target, reactiveArmor, Mek.LOC_LEFT_TORSO));
        assertEquals(2, target.getNumberOfCriticalSlots(reactiveArmor, Mek.LOC_LEFT_TORSO));
    }

    @Test
    void donorLocationCopyPreservesSeparateRepeatedComponents() throws Exception {
        EquipmentType doubleHeatSink = EquipmentType.get(EquipmentTypeLookup.CLAN_DOUBLE_HS);
        Mek target = newTestMek();
        Mek donor = newTestMek();
        donor.addEquipment(doubleHeatSink, Mek.LOC_RIGHT_ARM);
        donor.addEquipment(doubleHeatSink, Mek.LOC_RIGHT_ARM);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        assertEquals(2, countEquipment(target, doubleHeatSink, Mek.LOC_RIGHT_ARM));
        assertEquals(4, target.getNumberOfCriticalSlots(doubleHeatSink, Mek.LOC_RIGHT_ARM));
    }

    @Test
    void donorLocationCopyPreservesRearMountedStatus() throws Exception {
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = newTestMek();
        Mek donor = newTestMek();
        donor.addEquipment(mediumLaser, Mek.LOC_RIGHT_TORSO, true);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_TORSO);

        assertTrue(target.getEquipment().stream()
              .anyMatch(mounted -> mediumLaser.equals(mounted.getType())
                    && (mounted.getLocation() == Mek.LOC_RIGHT_TORSO) && mounted.isRearMounted()));
    }

    @Test
    void donorLocationCopyPreservesCopiedEquipmentLinks() throws Exception {
        EquipmentType ppc = EquipmentType.get("PPC");
        EquipmentType ppcCapacitor = EquipmentType.get("PPC Capacitor");
        Mek target = newTestMek();
        Mek donor = newTestMek();
        Mounted<?> donorPpc = donor.addEquipment(ppc, Mek.LOC_RIGHT_ARM);
        Mounted<?> donorCapacitor = donor.addEquipment(ppcCapacitor, Mek.LOC_RIGHT_ARM);
        donorCapacitor.setLinked(donorPpc);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        Mounted<?> copiedPpc = findEquipment(target, ppc, Mek.LOC_RIGHT_ARM);
        Mounted<?> copiedCapacitor = findEquipment(target, ppcCapacitor, Mek.LOC_RIGHT_ARM);
        assertNotNull(copiedPpc);
        assertNotNull(copiedCapacitor);
        assertSame(copiedPpc, copiedCapacitor.getLinked());
        assertSame(copiedCapacitor, copiedPpc.getLinkedBy());
    }

    @Test
    void donorLocationCopyMovesWholeComponentToUnallocatedWhenItCannotFit() throws Exception {
        EquipmentType autocannon = EquipmentType.get("Autocannon/20");
        Mek target = newTestMek();
        target.setEngine(new Engine(300, Engine.XL_ENGINE, 0));
        target.addEngineCrits();

        Mek donor = newTestMek();
        donor.addEquipment(autocannon, Mek.LOC_LEFT_TORSO);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_LEFT_TORSO);

        assertEquals(0, countEquipment(target, autocannon, Mek.LOC_LEFT_TORSO));
        assertEquals(0, target.getNumberOfCriticalSlots(autocannon, Mek.LOC_LEFT_TORSO));
        assertEquals(1, countEquipment(target, autocannon, Entity.LOC_NONE));
    }

    @Test
    void donorLocationCopyDoesNotDuplicatePartiallyAddedUnallocatedEquipment() throws Exception {
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = new PartiallyAddingMek();
        target.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        target.setWeight(25.0);
        target.setEngine(new Engine(100, Engine.NORMAL_ENGINE, 0));

        Mek donor = newTestMek();
        donor.addEquipment(mediumLaser, Mek.LOC_RIGHT_ARM);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_RIGHT_ARM);

        assertEquals(0, countEquipment(target, mediumLaser, Mek.LOC_RIGHT_ARM));
        assertEquals(1, countEquipment(target, mediumLaser, Entity.LOC_NONE));
        assertEquals(1, target.getEquipment().stream().filter(mounted -> mediumLaser.equals(mounted.getType())).count());
    }

    @Test
    void donorLocationCopyKeepsSystemCriticalSlotsInTargetLocation() throws Exception {
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        EquipmentType largeLaser = EquipmentType.get("Large Laser");
        Mek target = newTestMek();
        target.setEngine(new Engine(300, Engine.XL_ENGINE, 0));
        target.addEngineCrits();
        long engineSlots = countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE);
        target.addEquipment(mediumLaser, Mek.LOC_LEFT_TORSO);

        Mek donor = newTestMek();
        donor.addEquipment(largeLaser, Mek.LOC_LEFT_TORSO);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_LEFT_TORSO);

        assertEquals(engineSlots, countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE));
        assertEquals(0, countEquipment(target, mediumLaser, Mek.LOC_LEFT_TORSO));
        assertEquals(1, countEquipment(target, largeLaser, Mek.LOC_LEFT_TORSO));
    }

    @Test
    void donorCenterTorsoCopyCopiesEngineGyroHeatSinkTypeAndTorsoCockpit() throws Exception {
        EquipmentType singleHeatSink = EquipmentType.get(EquipmentTypeLookup.SINGLE_HS);
        EquipmentType doubleHeatSink = EquipmentType.get(EquipmentTypeLookup.IS_DOUBLE_HS);
        Mek target = newTestMek();
        target.addEquipment(singleHeatSink, Entity.LOC_NONE);
        target.addCockpit();

        Mek donor = newTestMek();
        donor.setEngine(new Engine(200, Engine.XL_ENGINE, 0));
        donor.addEngineCrits();
        donor.setGyroType(Mek.GYRO_COMPACT);
        donor.addCompactGyro();
        donor.addEquipment(doubleHeatSink, Entity.LOC_NONE);
        donor.setCockpitType(Mek.COCKPIT_TORSO_MOUNTED);
        donor.addTorsoMountedCockpit(false);

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_CENTER_TORSO);

        assertEquals(Engine.XL_ENGINE, target.getEngine().getEngineType());
        assertEquals(100, target.getEngine().getRating());
        assertEquals(Mek.GYRO_COMPACT, target.getGyroType());
        assertTrue(target.hasDoubleHeatSinks());
        assertEquals(Mek.COCKPIT_TORSO_MOUNTED, target.getCockpitType());
        assertEquals(1, countSystemCriticalSlots(target, Mek.LOC_CENTER_TORSO, Mek.SYSTEM_COCKPIT));
        assertEquals(0, countSystemCriticalSlots(target, Mek.LOC_HEAD, Mek.SYSTEM_COCKPIT));
        assertTrue(countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE) > 0);
        assertTrue(countSystemCriticalSlots(target, Mek.LOC_RIGHT_TORSO, Mek.SYSTEM_ENGINE) > 0);
    }

    @Test
    void donorHeadCopyMovesCockpitOutOfCenterTorso() throws Exception {
        Mek target = newTestMek();
        target.setCockpitType(Mek.COCKPIT_TORSO_MOUNTED);
        target.addTorsoMountedCockpit(false);

        Mek donor = newTestMek();
        donor.addCockpit();

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_HEAD);

        assertEquals(Mek.COCKPIT_STANDARD, target.getCockpitType());
        assertEquals(1, countSystemCriticalSlots(target, Mek.LOC_HEAD, Mek.SYSTEM_COCKPIT));
        assertEquals(0, countSystemCriticalSlots(target, Mek.LOC_CENTER_TORSO, Mek.SYSTEM_COCKPIT));
    }

    @Test
    void donorCenterTorsoXlEngineUnallocatesSideTorsoEquipmentForEngineSlots() throws Exception {
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = newTestMek();
        target.addEquipment(mediumLaser, Mek.LOC_LEFT_TORSO);

        Mek donor = newTestMek();
        donor.setEngine(new Engine(100, Engine.XL_ENGINE, 0));
        donor.addEngineCrits();

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_CENTER_TORSO);

        assertEquals(Engine.XL_ENGINE, target.getEngine().getEngineType());
        assertTrue(countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE) > 0);
        assertEquals(0, countEquipment(target, mediumLaser, Mek.LOC_LEFT_TORSO));
        assertEquals(1, countEquipment(target, mediumLaser, Entity.LOC_NONE));
    }

    @Test
    void donorCenterTorsoNormalEngineRemovesOldSideTorsoEngineSlots() throws Exception {
        Mek target = newTestMek();
        target.setEngine(new Engine(100, Engine.XL_ENGINE, 0));
        target.addEngineCrits();
        assertTrue(countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE) > 0);

        Mek donor = newTestMek();
        donor.addEngineCrits();

        FrankenMekDonorUtil.replaceLocationEquipment(target, donor, Mek.LOC_CENTER_TORSO);

        assertEquals(Engine.NORMAL_ENGINE, target.getEngine().getEngineType());
        assertEquals(0, countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE));
        assertEquals(0, countSystemCriticalSlots(target, Mek.LOC_RIGHT_TORSO, Mek.SYSTEM_ENGINE));
    }

    @Test
    void frankenMekDonorLocationRejectsLegsLighterThanCenterTorso() {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 80);

        assertNotNull(FrankenMekDonorUtil.getDonorLocationInvalidReason(target, Mek.LOC_RIGHT_LEG, 75));
        assertNull(FrankenMekDonorUtil.getDonorLocationInvalidReason(target, Mek.LOC_RIGHT_LEG, 80));
    }

    @Test
    void frankenMekDonorLocationRejectsSuperHeavyPartsWhenCenterTorsoIsNotSuperHeavy() {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 100);

        assertNotNull(FrankenMekDonorUtil.getDonorLocationInvalidReason(target, Mek.LOC_RIGHT_ARM, 105));
        assertNull(FrankenMekDonorUtil.getDonorLocationInvalidReason(target, Mek.LOC_CENTER_TORSO, 105));

        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 105);

        assertNull(FrankenMekDonorUtil.getDonorLocationInvalidReason(target, Mek.LOC_RIGHT_ARM, 105));
    }

    @Test
    void mismatchedLegsFollowDonorDisplayNames() {
        Mek target = newTestMek();
        target.setFrankenMek(true);

        target.linkFrankenMekLocationToSource(Mek.LOC_RIGHT_LEG, "Archer XYZ");
        FrankenMekDonorUtil.updateMismatchedLegsFromDonorSources(target);

        assertTrue(target.hasMismatchedFrankenMekLegs());

        target.linkFrankenMekLocationToSource(Mek.LOC_LEFT_LEG, "Archer XYZ");
        FrankenMekDonorUtil.updateMismatchedLegsFromDonorSources(target);

        assertFalse(target.hasMismatchedFrankenMekLegs());

        target.linkFrankenMekLocationToSource(Mek.LOC_LEFT_LEG, "Catapult ABC");
        FrankenMekDonorUtil.updateMismatchedLegsFromDonorSources(target);

        assertTrue(target.hasMismatchedFrankenMekLegs());
    }

    @Test
    void mismatchedLegsClearWhenAllLegDonorDisplayNamesAreBlank() {
        Mek target = newTestMek();
        target.setFrankenMek(true);

        target.setMismatchedFrankenMekLegs(true);
        FrankenMekDonorUtil.updateMismatchedLegsFromDonorSources(target);

        assertFalse(target.hasMismatchedFrankenMekLegs());
    }
}