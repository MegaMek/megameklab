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
package megameklab.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;

import megamek.common.equipment.Engine;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.PopupMessages;
import megameklab.util.UnitUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

@ExtendWith(value = InitializeTypes.class)
class MegaMekLabFileSaverTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void saveAsWritesAndAdoptsNewUUID() throws Exception {
        Entity entity = new BipedMek();
        String previousUUID = entity.getUnitFileUUID();
        File destination = temporaryDirectory.resolve("saved-as.mtf").toFile();
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        String savedFile;
        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            savedFile = saver.saveUnitAsTo(null, destination, entity);
        }

        assertEquals(destination.toString(), savedFile);
        assertNotEquals(previousUUID, entity.getUnitFileUUID());
        assertTrue(Files.readString(destination.toPath()).contains("uuid:" + entity.getUnitFileUUID()));
    }

    @Test
    void saveAsKeepsUUIDForLoadedUnitWithUnchangedName() throws Exception {
        Entity entity = loadMek(temporaryDirectory.resolve("source.mtf"), "Loaded Chassis", "Loaded Model");
        String previousUUID = entity.getUnitFileUUID();
        File destination = temporaryDirectory.resolve("saved-as.mtf").toFile();
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            assertEquals(destination.toString(), saver.saveUnitAsTo(null, destination, entity));
        }

        assertEquals(previousUUID, entity.getUnitFileUUID());
    }

    @Test
    void saveAsRegeneratesUUIDWhenLoadedUnitNameChanges() throws Exception {
        Entity entity = loadMek(temporaryDirectory.resolve("source.mtf"), "Loaded Chassis", "Loaded Model");
        String previousUUID = entity.getUnitFileUUID();
        entity.setModel("Changed Model");
        File destination = temporaryDirectory.resolve("saved-as.mtf").toFile();
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            assertEquals(destination.toString(), saver.saveUnitAsTo(null, destination, entity));
        }

        assertNotEquals(previousUUID, entity.getUnitFileUUID());
        String savedUUID = entity.getUnitFileUUID();
        File secondDestination = temporaryDirectory.resolve("saved-as-again.mtf").toFile();

        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            assertEquals(secondDestination.toString(), saver.saveUnitAsTo(null, secondDestination, entity));
        }

        assertEquals(savedUUID, entity.getUnitFileUUID());
    }

    @Test
    void saveAsUsesDestinationUUIDWhenNamesMatch() throws Exception {
        Entity target = loadMek(temporaryDirectory.resolve("target-source.mtf"), "Shared Chassis", "Shared Model");
        File destination = temporaryDirectory.resolve("destination.mtf").toFile();
        Files.writeString(destination.toPath(), UnitUtil.saveUnitToString(target, true));
        String targetUUID = target.getUnitFileUUID();
        Entity entity = loadMek(temporaryDirectory.resolve("current-source.mtf"), "Shared Chassis", "Shared Model");
        assertNotEquals(targetUUID, entity.getUnitFileUUID());
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            assertEquals(destination.toString(), saver.saveUnitAsTo(null, destination, entity));
        }

        assertEquals(targetUUID, entity.getUnitFileUUID());
        assertTrue(Files.readString(destination.toPath()).contains("uuid:" + targetUUID));
    }

    @Test
    void saveAsCanKeepCurrentUUIDWhenOverwritingDifferentUnit() throws Exception {
        Entity target = loadMek(temporaryDirectory.resolve("target-source.mtf"), "Target Chassis", "Target Model");
        File destination = temporaryDirectory.resolve("destination.mtf").toFile();
        Files.writeString(destination.toPath(), UnitUtil.saveUnitToString(target, true));
        Entity entity = loadMek(temporaryDirectory.resolve("current-source.mtf"), "Current Chassis", "Current Model");
        String currentUUID = entity.getUnitFileUUID();
        String targetUUID = target.getUnitFileUUID();
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        try (MockedStatic<PopupMessages> messages = mockStatic(PopupMessages.class)) {
            messages.when(() -> PopupMessages.showUnitFileUUIDConflict(
                        any(), any(Entity.class), any(Entity.class)))
                  .thenReturn(PopupMessages.UnitFileUUIDChoice.CURRENT);
            assertEquals(destination.toString(), saver.saveUnitAsTo(null, destination, entity));
        }

        assertNotEquals(targetUUID, currentUUID);
        assertEquals(currentUUID, entity.getUnitFileUUID());
    }

    private Entity loadMek(Path file, String chassis, String model) throws Exception {
        BipedMek mek = new BipedMek();
        mek.setWeight(20.0);
        mek.setEngine(new Engine(100, Engine.NORMAL_ENGINE, 0));
        mek.setChassis(chassis);
        mek.setModel(model);
        Files.writeString(file, UnitUtil.saveUnitToString(mek, true));
        return new MekFileParser(file.toFile()).getEntity();
    }

    @Test
    void failedSaveAsRestoresPreviousUUID() {
        Entity entity = new BipedMek();
        String previousUUID = entity.getUnitFileUUID();
        MegaMekLabFileSaver saver = new MegaMekLabFileSaver(
              MMLogger.create(MegaMekLabFileSaverTest.class), "Save As");

        String savedFile;
        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            savedFile = saver.saveUnitAsTo(null, temporaryDirectory.toFile(), entity);
        }

        assertNull(savedFile);
        assertEquals(previousUUID, entity.getUnitFileUUID());
    }

    @Test
    void writesBfsAsUtf8() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Éclaireur 日本");
        File destination = temporaryDirectory.resolve("unicode.bfs").toFile();

        MegaMekLabFileSaver.writeUnitToFile(destination, asset);

        String content = Files.readString(destination.toPath(), StandardCharsets.UTF_8);
        assertTrue(content.contains("Éclaireur 日本"));
    }

    @Test
    void directWritePropagatesIoFailure() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        File parentFile = temporaryDirectory.resolve("not-a-directory").toFile();
        Files.writeString(parentFile.toPath(), "blocker");

        assertThrows(IOException.class,
              () -> MegaMekLabFileSaver.writeUnitToFile(new File(parentFile, "asset.bfs"), asset));
    }
}
