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
 */
package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import megamek.common.battlefieldSupport.BFSAssetType;
import megamek.common.battlefieldSupport.BFSDamage;
import megamek.common.battlefieldSupport.BFSRange;
import megamek.common.battlefieldSupport.BFSSpecial;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.BattlefieldSupportAssetData;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.EntityMovementMode;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Verifies that {@link UnitUtil#saveUnitToString} serializes a {@link BattlefieldSupportAsset} to the {@code .bfs} YAML
 * format and that the result parses back into an equivalent asset (the MegaMekLab save path for standalone assets).
 */
@ExtendWith(value = InitializeTypes.class)
class BattlefieldSupportAssetSaveTest {

    private static BattlefieldSupportAsset sampleAsset() {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Test Asset");
        asset.setModel("TA-1");
        asset.setAssetType(BFSAssetType.VEHICLE);
        asset.setYear(3151);
        asset.setAssetTechBase(BattlefieldSupportAssetData.TECH_BASE_CLAN);
        asset.setSource("Test Source");
        asset.setMovementMode(EntityMovementMode.HOVER);
        asset.setMp(9);
        asset.setTmm(4);
        asset.setRange(new BFSRange(4, 8, 12));
        asset.setSkill(6);
        asset.setVeteranSkill(5);
        asset.setDamage(new BFSDamage(3, 2));
        asset.setODestroyCheck(8);
        asset.setThreshold(6);
        asset.setCost(30);
        asset.setVeteranCost(36);
        asset.setSpecials(List.of(BFSSpecial.of("IF", 2), BFSSpecial.of("Spotter")));
        return asset;
    }

    @Test
    void savedAssetIsBfsYamlAndRoundTrips() throws Exception {
        BattlefieldSupportAsset original = sampleAsset();

        String saved = UnitUtil.saveUnitToString(original, true);
        assertTrue(saved.contains("assetType:"), "saved asset should be .bfs YAML");
        assertTrue(saved.contains("techBase: \"Clan\""), "tech base should be serialized");
        assertTrue(saved.startsWith("# Saved from version "),
              "saved asset should carry a datestamp header comment");

        // The datestamp header must be omitted when the generator header is not requested; the document then starts
        // with the asset's uuid field (assets always carry a unit-file UUID).
        String headerless = UnitUtil.saveUnitToString(original, false);
        assertFalse(headerless.startsWith("#"), "headerless save should have no datestamp comment");
        assertTrue(headerless.startsWith("uuid:"), "headerless save should start with the uuid field");
        assertTrue(headerless.contains("chassis:"));

        File file = File.createTempFile("bfs-save-test", ".bfs");
        file.deleteOnExit();
        Files.writeString(file.toPath(), saved, StandardCharsets.UTF_8);

        BattlefieldSupportAsset restored = assertInstanceOf(BattlefieldSupportAsset.class,
              new MekFileParser(file).getEntity());

        assertEquals(original.getChassis(), restored.getChassis());
        assertEquals(original.getModel(), restored.getModel());
        assertEquals(original.getAssetType(), restored.getAssetType());
        assertEquals(original.getYear(), restored.getYear());
        assertEquals(original.getAssetTechBase(), restored.getAssetTechBase());
        assertEquals(original.getSource(), restored.getSource());
        assertEquals(original.getMovementMode(), restored.getMovementMode());
        assertEquals(original.getMp(), restored.getMp());
        assertEquals(original.getTmm(), restored.getTmm());
        assertEquals(original.getRange(), restored.getRange());
        assertEquals(original.getSkillDisplay(), restored.getSkillDisplay());
        assertEquals(original.getCostDisplay(), restored.getCostDisplay());
        assertEquals(original.getDamageDisplay(), restored.getDamageDisplay());
        assertEquals(original.getSpecialsDisplay(), restored.getSpecialsDisplay());
    }
}
