/*
 * Copyright (c) 2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.ui.generalUnit;

import megamek.common.*;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.BLKTankFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.EntitySavingException;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = InitializeTypes.class)
class BasicInfoViewTest {

    // Filenames must be preceded with a slash to load from the testresources path
    private final String resourcesPath = "/ui/generalUnit/BasicInfoViewTest/";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private Entity loadEntity(String filename) throws EntityLoadingException {
        String path = resourcesPath + filename;
        InputStream is = getClass().getResourceAsStream(path);
        assertNotNull(is);
        return new MekFileParser(is, filename).getEntity();
    }

    @Test
    void testSetTechAdvancementFromEarlyISUnofficial() throws EntityLoadingException, EntitySavingException {
        String fname = "Puma Assault Tank PAT-001.blk";
        Entity te = loadEntity(fname);

        // Confirm expected Tech Base (IS) and Tech Level (Simple Intro)
        int techBase = te.getTechBase();
        int techLevel = te.getTechLevel();
        assertEquals(TechAdvancement.TECH_BASE_IS, techBase);
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
        assertEquals(TechAdvancement.TECH_BASE_IS, techBase);
        assertEquals(TechConstants.T_IS_UNOFFICIAL, techLevel);
        assertFalse(newTE.isClan());
    }
}
