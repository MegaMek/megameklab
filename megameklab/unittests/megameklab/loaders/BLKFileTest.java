/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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
package megameklab.loaders;

import megamek.common.*;
import megamek.common.equipment.ArmorType;
import megamek.common.loaders.EntitySavingException;
import megameklab.util.UnitUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BLKFileTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("allMekfiles")
    void loadVerifyUnit(File unitFIle) {
        Entity entity = loadUnit(unitFIle);
        var validation = UnitUtil.verify(entity);
        assertEquals(validation.state(), UnitUtil.UnitValidation.VALID,
            "The unit is invalid:\n\t" + entity.getDisplayName() + "\n" + validation.report());
    }

    // Total of 169 failed entities
    // from 4990 entities
    @ParameterizedTest(name = "{0}")
    @MethodSource("allMekfiles")
    void loadVerifySaveVerify(File file) throws EntitySavingException, IOException {
        Entity entity = loadUnit(file);
        var validation = UnitUtil.verify(entity);

        assertEquals(UnitUtil.UnitValidation.VALID, validation.state(),
            "The unit is invalid:\n\t" + entity.getDisplayName() + "\n" + validation.report());
        File tmpFile;
        if (entity instanceof Mek) {
            tmpFile = File.createTempFile("tmp_mekfiles/" + UUID.randomUUID() + "/" + file.getName(), ".mtf");
        } else {
            tmpFile = File.createTempFile("tmp_mekfiles/" + UUID.randomUUID() + "/" + file.getName(), ".blk");
        }

        if (UnitUtil.persistUnit(tmpFile, entity)) {
            Entity repersistedEntity = loadUnit(tmpFile);
            var reValidation = UnitUtil.verify(repersistedEntity);
            assertEquals(UnitUtil.UnitValidation.VALID, reValidation.state(),
                "The unit is invalid after repersisting:\n\t" + tmpFile + "\n\t" + entity.getDisplayName() + "\n" + reValidation.report());
            assertEquals(reValidation.state(), validation.state());
        }
    }

    public static List<File> allMekfiles() {
        initializeStuff();
        try (Stream<Path> paths = Files.walk(Paths.get("data/mekfiles"))) {
            return paths
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".blk"))
                .map(Path::toFile)
                .toList();
        } catch (IOException e) {
            // do nothing
        }
        return List.of();
    }

    private static void initializeStuff() {
        EquipmentType.initializeTypes();
        AmmoType.initializeTypes();
        ArmorType.initializeTypes();
        WeaponType.initializeTypes();
        MiscType.initializeTypes();
        BombType.initializeTypes();
    }

    private Entity loadUnit(File file) {
        try {
            MekFileParser mfp = new MekFileParser(file);
            return mfp.getEntity();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        throw new IllegalStateException("Should not reach here");
    }

}
