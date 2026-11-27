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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import megamek.common.TechConstants;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGRectElement;

@ExtendWith(value = InitializeTypes.class)
class InventoryWriterTest {

    private static List<Mounted<?>> rightTorso(String... names) throws Exception {
        Mek mek = new BipedMek();
        mek.setWeight(50);
        mek.setEngine(new Engine(200, Engine.NORMAL_ENGINE, 0));
        for (String name : names) {
            mek.addEquipment(EquipmentType.get(name), Mek.LOC_RIGHT_TORSO);
        }
        MekFileParser.postLoadInit(mek);
        return mek.getEquipment().stream().filter(mount -> mount.getLocation() == Mek.LOC_RIGHT_TORSO).toList();
    }

    /** An ATM entry prints several rows although nothing is linked to the launcher */
    @Test
    void anAtmNamesNothingOnItsSecondRow() throws Exception {
        assertNull(secondRowMount(rightTorso("CLATM6").get(0)));
    }

    @Test
    void anMmlNamesItsArtemisOnItsSecondRow() throws Exception {
        List<Mounted<?>> mounts = rightTorso("ISMML5", "ISArtemisIV");

        assertSame(mounts.get(1), secondRowMount(mounts.get(0)));
    }

    /** An aerospace MML 5 with Artemis IV prints 4 and 8 on its LRM and SRM rows (TW p. 303), under its Artemis row */
    @Test
    void anAerospaceMmlPrintsItsAttackValuesUnderItsArtemisRow() throws Exception {
        AeroSpaceFighter fighter = new AeroSpaceFighter();
        fighter.setWeight(50);
        Mounted<?> launcher = fighter.addEquipment(EquipmentType.get("ISMML5"), Aero.LOC_NOSE);
        fighter.addEquipment(EquipmentType.get("ISArtemisIV"), Aero.LOC_NOSE).setLinked(launcher);
        StandardInventoryEntry entry = new StandardInventoryEntry(launcher);

        assertEquals("", entry.getShortField(1));
        assertEquals("LRM", entry.getNameField(2));
        assertEquals("SRM", entry.getNameField(3));
        assertEquals("4", entry.getShortField(2));
        assertEquals("8", entry.getShortField(3));
    }

    @Test
    void aLaserWithAnInsulatorAndAPulseModuleNamesTheModule() throws Exception {
        List<Mounted<?>> mounts = rightTorso("Medium Laser", "ISLaserInsulator", "ISRISCLaserPulseModule");
        StandardInventoryEntry entry = new StandardInventoryEntry(mounts.get(0));

        assertSame(mounts.get(2), entry.getSecondRowMount());
        // the second row fires in pulse mode, -2 to-hit (IO:AE p.87)
        assertEquals(InventoryEntry.DASH, entry.getModField(0, false));
        assertEquals(InventoryEntry.MINUS + "2", entry.getModField(1, false));
    }

    @Test
    void protoMekInventoryOmitsBuiltInEnhancedImagingInterface() throws LocationFullException {
        ProtoMek proto = new ProtoMek();
        proto.setWeight(6);
        Mounted<?> ei = proto.addEquipment(EquipmentType.get(EquipmentTypeLookup.EI_INTERFACE), ProtoMek.LOC_BODY);
        Mounted<?> laser = proto.addEquipment(EquipmentType.get("CLSmallPulseLaser"), ProtoMek.LOC_TORSO);

        Document inventory = renderInventory(proto);

        assertNull(inventory.getElementById(new StandardInventoryEntry(ei).getUniqueId()));
        assertNotNull(inventory.getElementById(new StandardInventoryEntry(laser).getUniqueId()));
    }

    @Test
    void mekInventoryListsInstalledEnhancedImagingInterface() throws LocationFullException {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_CLAN_TW);
        Mounted<?> ei = mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.EI_INTERFACE), Mek.LOC_HEAD);

        Document inventory = renderInventory(mek);

        assertNotNull(inventory.getElementById(new StandardInventoryEntry(ei).getUniqueId()));
    }

    @Test
    void battleArmorInventoryListsInstalledEnhancedImagingInterface() throws LocationFullException {
        BattleArmor battleArmor = new BattleArmor();
        battleArmor.setTechLevel(TechConstants.T_CLAN_TW);
        Mounted<?> ei = battleArmor.addEquipment(EquipmentType.get(EquipmentTypeLookup.EI_INTERFACE),
              BattleArmor.LOC_SQUAD);

        Document inventory = renderInventory(battleArmor);

        assertNotNull(inventory.getElementById(new StandardInventoryEntry(ei).getUniqueId()));
    }

    private static Document renderInventory(Entity entity) {
        RecordSheetOptions options = new RecordSheetOptions();
        options.setIntrinsicPhysicalAttacks(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE);
        PrintEntity sheet = new PrintEntity(0, options) {
            @Override
            public Entity getEntity() {
                return entity;
            }

            @Override
            protected String getSVGFileName(int pageNumber) {
                throw new UnsupportedOperationException();
            }

            @Override
            protected String getRecordSheetTitle() {
                return "";
            }

            @Override
            protected boolean supportsAlternateArmorGrouping() {
                return false;
            }

            @Override
            public double getTextLength(String text, float fontSize, String weight, String fontStyle) {
                return text.length() * fontSize / 2;
            }

            @Override
            public float getFontHeight(float fontSize) {
                return fontSize * 1.2f;
            }
        };
        Document document = SVGDOMImplementation.getDOMImplementation().createDocument(
              PrintRecordSheet.svgNS, SVGConstants.SVG_SVG_TAG, null);
        sheet.setSVGDocument(document);
        SVGRectElement region = (SVGRectElement) document.createElementNS(
              PrintRecordSheet.svgNS, SVGConstants.SVG_RECT_TAG);
        region.setAttribute("width", "400");
        region.setAttribute("height", "200");
        document.getDocumentElement().appendChild(region);
        new InventoryWriter(sheet, region).writeEquipment();
        return document;
    }

    private static Mounted<?> secondRowMount(Mounted<?> weapon) {
        return new StandardInventoryEntry(weapon).getSecondRowMount();
    }
}
