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
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.CriticalSlot;
import megamek.common.TechAdvancement;
import megamek.common.TechConstants;
import megamek.common.enums.TechBase;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechnology;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class UnitUtilTest {

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
    void isLegalUsesOriginalBuildYear() {
        ITechnology lostech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(2500, 2600, 2700, 3025, ITechnology.DATE_NONE);
        ITechnology currenttech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(3030, 3040, 3050, ITechnology.DATE_NONE, ITechnology.DATE_NONE);
        ITechnology futuretech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(3100, 3110, 3150, ITechnology.DATE_NONE, ITechnology.DATE_NONE);
        Mek mek = new BipedMek();
        mek.setYear(3050);
        mek.setTechLevel(TechConstants.T_IS_TW_NON_BOX);

        assertFalse(UnitUtil.isLegal(mek, lostech));
        assertTrue(UnitUtil.isLegal(mek, currenttech));
        assertFalse(UnitUtil.isLegal(mek, futuretech));
        mek.setOriginalBuildYear(3000);

        assertTrue(UnitUtil.isLegal(mek, lostech));
        assertTrue(UnitUtil.isLegal(mek, currenttech));
        assertFalse(UnitUtil.isLegal(mek, futuretech));
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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_RIGHT_ARM);

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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_RIGHT_ARM);

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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_RIGHT_ARM);

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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_LEFT_TORSO);

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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_RIGHT_ARM);

        assertEquals(2, countEquipment(target, doubleHeatSink, Mek.LOC_RIGHT_ARM));
        assertEquals(4, target.getNumberOfCriticalSlots(doubleHeatSink, Mek.LOC_RIGHT_ARM));
    }

    @Test
    void donorLocationCopyPreservesRearMountedStatus() throws Exception {
        EquipmentType mediumLaser = EquipmentType.get("Medium Laser");
        Mek target = newTestMek();
        Mek donor = newTestMek();
        donor.addEquipment(mediumLaser, Mek.LOC_RIGHT_TORSO, true);

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_RIGHT_TORSO);

        assertTrue(target.getEquipment().stream()
              .anyMatch(mounted -> mediumLaser.equals(mounted.getType())
                    && (mounted.getLocation() == Mek.LOC_RIGHT_TORSO) && mounted.isRearMounted()));
    }

    @Test
    void donorLocationCopyMovesWholeComponentToUnallocatedWhenItCannotFit() throws Exception {
        EquipmentType autocannon = EquipmentType.get("Autocannon/20");
        Mek target = newTestMek();
        target.setEngine(new Engine(300, Engine.XL_ENGINE, 0));
        target.addEngineCrits();

        Mek donor = newTestMek();
        donor.addEquipment(autocannon, Mek.LOC_LEFT_TORSO);

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_LEFT_TORSO);

        assertEquals(0, countEquipment(target, autocannon, Mek.LOC_LEFT_TORSO));
        assertEquals(0, target.getNumberOfCriticalSlots(autocannon, Mek.LOC_LEFT_TORSO));
        assertEquals(1, countEquipment(target, autocannon, Entity.LOC_NONE));
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

        UnitUtil.replaceLocationEquipmentFromDonor(target, donor, Mek.LOC_LEFT_TORSO);

        assertEquals(engineSlots, countSystemCriticalSlots(target, Mek.LOC_LEFT_TORSO, Mek.SYSTEM_ENGINE));
        assertEquals(0, countEquipment(target, mediumLaser, Mek.LOC_LEFT_TORSO));
        assertEquals(1, countEquipment(target, largeLaser, Mek.LOC_LEFT_TORSO));
    }

    @Test
    void frankenMekDonorLocationRejectsLegsLighterThanCenterTorso() {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 80);

        assertNotNull(UnitUtil.getFrankenMekDonorLocationInvalidReason(target, Mek.LOC_RIGHT_LEG, 75));
        assertNull(UnitUtil.getFrankenMekDonorLocationInvalidReason(target, Mek.LOC_RIGHT_LEG, 80));
    }

    @Test
    void frankenMekDonorLocationRejectsSuperHeavyPartsWhenCenterTorsoIsNotSuperHeavy() {
        Mek target = newTestMek();
        target.setFrankenMek(true);
        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 100);

        assertNotNull(UnitUtil.getFrankenMekDonorLocationInvalidReason(target, Mek.LOC_RIGHT_ARM, 105));
        assertNull(UnitUtil.getFrankenMekDonorLocationInvalidReason(target, Mek.LOC_CENTER_TORSO, 105));

        target.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 105);

        assertNull(UnitUtil.getFrankenMekDonorLocationInvalidReason(target, Mek.LOC_RIGHT_ARM, 105));
    }
}