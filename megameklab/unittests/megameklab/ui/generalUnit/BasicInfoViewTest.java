/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.generalUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;

import megamek.common.TechConstants;
import megamek.common.enums.TechBase;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.BLKTankFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.EntitySavingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class BasicInfoViewTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private Entity loadEntity(String filename) throws EntityLoadingException {
        // Filenames must be preceded with a slash to load from the testresources path
        String resourcesPath = "/ui/generalUnit/BasicInfoViewTest/";
        String path = resourcesPath + filename;
        InputStream is = getClass().getResourceAsStream(path);
        assertNotNull(is);
        return new MekFileParser(is, filename).getEntity();
    }

    @Test
    void testSetTechAdvancementFromEarlyISUnofficial() throws EntityLoadingException, EntitySavingException {
        String fileName = "Puma Assault Tank PAT-001.blk";
        Entity te = loadEntity(fileName);

        // Confirm expected Tech Base (IS) and Tech Level (Simple Intro)
        TechBase techBase = te.getTechBase();
        int techLevel = te.getTechLevel();
        assertEquals(TechBase.IS, techBase);
        assertEquals(TechConstants.T_SIMPLE_INTRO, techLevel);

        // Update Tech Level
        te.setTechLevel(TechConstants.T_IS_UNOFFICIAL);

        // Convert test entity to BLK block and back to a new entity using BLKFile conversion
        BLKTankFile blkTankFile = new BLKTankFile(BLKFile.getBlock(te));
        Entity newTE = blkTankFile.getEntity();

        // Confirm values were saved correctly
        techBase = newTE.getTechBase();
        techLevel = newTE.getTechLevel();
        // Confirm expected Tech Base (IS) and Tech Level (IS Unofficial)
        assertEquals(TechBase.IS, techBase);
        assertEquals(TechConstants.T_IS_UNOFFICIAL, techLevel);
        assertFalse(newTE.isClan());
    }
}
