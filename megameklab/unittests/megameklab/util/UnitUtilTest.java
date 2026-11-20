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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import megamek.common.CriticalSlot;
import megamek.common.TechAdvancement;
import megamek.common.TechConstants;
import megamek.common.bays.CargoBay;
import megamek.common.enums.TechBase;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechnology;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.SmallCraft;
import megamek.common.verifier.TestAero;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class UnitUtilTest {

    @Test
    void keepsEveryEnhancementOfALaserWithItAndCountsItsInsulator() throws Exception {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        Mounted<?> laser = mek.addEquipment(EquipmentType.get("Medium Laser"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> other = mek.addEquipment(EquipmentType.get("Medium Laser"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> insulator = mek.addEquipment(EquipmentType.get("ISLaserInsulator"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> module = mek.addEquipment(EquipmentType.get("ISRISCLaserPulseModule"), Mek.LOC_RIGHT_TORSO);
        insulator.setLinked(laser);
        module.setLinked(laser);

        assertEquals(List.of(insulator, module), laser.getLinkedEquipment());
        List<CriticalSlot> slots = new ArrayList<>();
        for (Mounted<?> mount : List.of(laser, other, insulator, module)) {
            slots.add(new CriticalSlot(mount));
        }
        // The sort keeps both enhancements right behind their laser, which is how files link them
        assertEquals(List.of(laser, insulator, module, other),
              MekUtil.reOrderLinkedEquipment(slots).stream().map(CriticalSlot::getMount).toList());
        // The insulator counts although the pulse module was linked last: (3 - 1) + 3, plus 2 for the module itself
        assertEquals(7, UnitUtil.getTotalHeatGeneration(mek, true));
    }

    /**
     * Auto-sort follows canon files: underwater maneuvering units go with the jump jets, an AMS after the other weapons
     * but before its ammo, and stealth armor with the armor although it links the ECM suite
     */
    @Test
    void sortingFollowsCanonForUmusAmsAndStealthArmor() throws Exception {
        Mek mek = new BipedMek();
        List<Mounted<?>> mounts = new ArrayList<>();
        for (String name : List.of("IS Stealth", "IS Endo Steel", "ISGuardianECMSuite", "ISAMS Ammo",
              "ISAntiMissileSystem", "Medium Laser", "UMU")) {
            mounts.add(mek.addEquipment(EquipmentType.get(name), Mek.LOC_LEFT_TORSO));
        }
        mounts.get(0).setLinked(mounts.get(2));

        MekUtil.sortCritSlots(mek, Mek.LOC_LEFT_TORSO);

        List<String> sorted = new ArrayList<>();
        for (int slot = 0; slot < mek.getNumberOfCriticalSlots(Mek.LOC_LEFT_TORSO); slot++) {
            CriticalSlot criticalSlot = mek.getCritical(Mek.LOC_LEFT_TORSO, slot);
            String name = (criticalSlot == null) ? null : criticalSlot.getMount().getType().getInternalName();
            if ((name != null) && (sorted.isEmpty() || !sorted.getLast().equals(name))) {
                sorted.add(name);
            }
        }
        assertEquals(List.of("UMU", "Medium Laser", "ISAntiMissileSystem", "ISAMS Ammo", "ISGuardianECMSuite",
              "IS Endo Steel", "IS Stealth"), sorted);
    }

    @Test
    void isLegalUsesOriginalBuildYear() {
        ITechnology lostech = new TechAdvancement(TechBase.IS).setISAdvancement(2500,
              2600,
              2700,
              3025,
              ITechnology.DATE_NONE);
        ITechnology currenttech = new TechAdvancement(TechBase.IS).setISAdvancement(3030,
              3040,
              3050,
              ITechnology.DATE_NONE,
              ITechnology.DATE_NONE);
        ITechnology futuretech = new TechAdvancement(TechBase.IS).setISAdvancement(3100,
              3110,
              3150,
              ITechnology.DATE_NONE,
              ITechnology.DATE_NONE);
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
    void isLegalIgnoresExtinctionForFrankenMeks() {
        ITechnology lostech = new TechAdvancement(TechBase.IS).setISAdvancement(2500,
              2600,
              2700,
              3025,
              ITechnology.DATE_NONE);
        Mek mek = new BipedMek();
        mek.setYear(3050);
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        mek.setFrankenMek(true);

        assertTrue(UnitUtil.isLegal(mek, lostech));
    }

    @Test
    void saveWithoutGeneratorKeepsMtfUUIDFirst() {
        Mek mek = new BipedMek();

        String saved = UnitUtil.saveUnitToString(mek, false);

        assertTrue(saved.startsWith("uuid:" + mek.getUnitFileUUID()));
        assertFalse(saved.contains("generator:"));
    }

    @Test
    void saveWithoutGeneratorKeepsCompleteBlkHeader() throws Exception {
        Entity entity = new MekFileParser(new File("testresources/Aquarius Escort.blk")).getEntity();

        String saved = UnitUtil.saveUnitToString(entity, false);

        int uuidIndex = saved.indexOf("<UUID>");
        assertTrue(uuidIndex >= 0);
        assertTrue(uuidIndex < saved.indexOf("<UnitType>"));
        assertTrue(saved.indexOf(entity.getUnitFileUUID()) > uuidIndex);
    }

    @Test
    void updateLoadedUnitRemovesQuartersAndPreservesPersonnelForSmallCraftAtThreshold() {
        SmallCraft craft = cargoOnlySmallCraft(5.0);
        craft.addTransporter(TestAero.Quarters.STANDARD.newQuarters(3));

        UnitUtil.updateLoadedUnit(craft);

        assertEquals(1, craft.getNCrew());
        assertEquals(0, craft.getNOfficers());
        assertEquals(0, craft.getNGunners());
        assertEquals(1, craft.getTransportBays().size());
        assertFalse(craft.getTransportBays().get(0).isQuarters());
    }

    @Test
    void updateLoadedUnitStillAutoFillsSmallCraftAboveThreshold() {
        SmallCraft craft = cargoOnlySmallCraft(30);

        UnitUtil.updateLoadedUnit(craft);

        assertTrue(craft.getNCrew() >= 3);
        assertTrue(craft.getNOfficers() >= 1);
        assertTrue(craft.getTransportBays().stream().anyMatch(bay -> bay.isQuarters()));
    }

    @Test
    void updateLoadedUnitMaterializesAndDematerializesAutoFilledCrewAcrossThreshold() {
        SmallCraft craft = cargoOnlySmallCraft(5.0);

        UnitUtil.updateLoadedUnit(craft);
        assertEquals(1, craft.getNCrew());
        assertEquals(0, craft.getNOfficers());
        assertEquals(0, craft.getNGunners());
        assertEquals(1, craft.getTransportBays().size());

        craft.setWeight(30.0);
        UnitUtil.updateLoadedUnit(craft);
        assertEquals(3, craft.getNCrew());
        assertEquals(1, craft.getNOfficers());
        assertEquals(0, craft.getNGunners());
        assertTrue(craft.getTransportBays().stream().anyMatch(bay -> bay.isQuarters()));

        craft.setWeight(5.0);
        UnitUtil.updateLoadedUnit(craft);
        assertEquals(1, craft.getNCrew());
        assertEquals(0, craft.getNOfficers());
        assertEquals(0, craft.getNGunners());
        assertEquals(1, craft.getTransportBays().size());
        assertFalse(craft.getTransportBays().get(0).isQuarters());
    }

    private SmallCraft cargoOnlySmallCraft(double tonnage) {
        SmallCraft craft = new SmallCraft();
        craft.setWeight(tonnage);
        craft.setNCrew(1);
        craft.setNOfficers(0);
        craft.setNGunners(0);
        craft.addTransporter(new CargoBay(1.0, 1, 1));
        return craft;
    }
}
