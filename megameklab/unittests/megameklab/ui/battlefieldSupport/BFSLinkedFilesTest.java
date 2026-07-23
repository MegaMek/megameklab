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
package megameklab.ui.battlefieldSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.EquipmentType;
import megamek.common.loaders.MekFileParser;
import megamek.logging.MMLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** Verifies the pure {@code .bfs} sidecar path rules used when saving a linked Battlefield Support Asset. */
class BFSLinkedFilesTest {

    private static final MMLogger LOGGER = MMLogger.create(BFSLinkedFilesTest.class);

    private static final String DIR = "units" + File.separator + "vehicles";

    @BeforeAll
    static void beforeAll() {
        EquipmentType.initializeTypes();
    }

    private static File baseFile() {
        return new File(DIR, "Maxim Heavy Hover Transport.blk");
    }

    @Test
    void inPlaceSaveKeepsExistingCoLocatedSidecar() {
        String existing = new File(DIR, "Maxim Heavy Hover Transport.bfs").getPath();
        File target = BFSLinkedFiles.resolveSaveTarget(baseFile(), existing, false);
        assertEquals(existing, target.getPath());
    }

    @Test
    void inPlaceSaveKeepsExistingMismatchedNameSidecar() {
        // The asset need not match the base file name (e.g. Elemental); an in-place save writes back to it.
        String existing = new File(DIR, "Mobile Long Tom Artillery.bfs").getPath();
        File target = BFSLinkedFiles.resolveSaveTarget(baseFile(), existing, false);
        assertEquals(existing, target.getPath());
    }

    @Test
    void inPlaceSaveWithNoExistingFileWritesCoLocatedSidecar() {
        File target = BFSLinkedFiles.resolveSaveTarget(baseFile(), null, false);
        assertEquals(new File(DIR, "Maxim Heavy Hover Transport.bfs").getPath(), target.getPath());
    }

    @Test
    void inPlaceSaveOfZippedAssetFallsBackToCoLocatedSidecar() {
        // A cached asset inside a zip can't be overwritten; the sidecar is written next to the base file instead.
        String zipped = new File(DIR, "vehicles.zip").getPath() + "|Maxim Heavy Hover Transport.bfs";
        File target = BFSLinkedFiles.resolveSaveTarget(baseFile(), zipped, false);
        assertEquals(new File(DIR, "Maxim Heavy Hover Transport.bfs").getPath(), target.getPath());
    }

    @Test
    void saveAsAlwaysWritesFreshCoLocatedSidecar() {
        String existing = new File(DIR, "Some Other Name.bfs").getPath();
        File newBase = new File("elsewhere", "Renamed Maxim.blk");
        File target = BFSLinkedFiles.resolveSaveTarget(newBase, existing, true);
        assertEquals(new File("elsewhere", "Renamed Maxim.bfs").getPath(), target.getPath());
    }

    @Test
    void coLocatedSidecarReplacesExtension() {
        assertEquals(new File(DIR, "Maxim Heavy Hover Transport.bfs").getPath(),
              BFSLinkedFiles.coLocatedSidecar(baseFile()).getPath());
    }

    @Test
    void isInsideZipDetectsZipPaths() {
        assertTrue(BFSLinkedFiles.isInsideZip("data/mekfiles/vehicles.zip"));
        assertTrue(BFSLinkedFiles.isInsideZip("data/mekfiles/vehicles.zip|Maxim Heavy Hover Transport.blk"));
        assertFalse(BFSLinkedFiles.isInsideZip("data/mekfiles/Maxim.blk"));
        assertFalse(BFSLinkedFiles.isInsideZip("data/mekfiles/Maxim.blk|alternate"));
        assertFalse(BFSLinkedFiles.isInsideZip(null));
    }

    @Test
    void stripExtensionRemovesLastExtensionOnly() {
        assertEquals("Maxim Heavy Hover Transport", BFSLinkedFiles.stripExtension("Maxim Heavy Hover Transport.blk"));
        assertEquals("archive.tar", BFSLinkedFiles.stripExtension("archive.tar.gz"));
        assertEquals("noext", BFSLinkedFiles.stripExtension("noext"));
    }

    @Test
    void sidecarIsRelinkedToTheBaseUnitFinalUuidOnSave() throws Exception {
        // The asset was linked to the base unit's old UUID; on save the base unit adopted a different UUID (e.g. from
        // an overwritten file). The written sidecar must link to the base unit's final UUID, not the stale one.
        String oldBaseUuid = "019f583e-e2c6-7b99-a188-ba0759db128e";
        String finalBaseUuid = "019f5efd-5a93-78c3-b4f2-dd83804af175";

        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Test Unit");
        asset.setLinkedUnitId(oldBaseUuid);
        StubSource source = new StubSource(asset);

        File dir = Files.createTempDirectory("bfs-relink").toFile();
        dir.deleteOnExit();
        File savedBaseFile = new File(dir, "Test Unit.blk");

        BFSLinkedFiles.handleSidecarOnSave(null, source, savedBaseFile, finalBaseUuid, true, null, LOGGER);

        // The in-memory asset and the written sidecar both link to the base unit's final UUID.
        assertEquals(finalBaseUuid, asset.getLinkedUnitId());
        File sidecar = new File(dir, "Test Unit.bfs");
        assertTrue(sidecar.isFile(), "a co-located sidecar should have been written");
        assertEquals(sidecar.getPath(), source.assetFilePath);

        BattlefieldSupportAsset reloaded = assertInstanceOf(BattlefieldSupportAsset.class,
              new MekFileParser(sidecar).getEntity());
        assertEquals(finalBaseUuid, reloaded.getLinkedUnitId());
    }

    @Test
    void assetRegeneratesItsOwnUuidWhenTheUnitIsRenamedToANewFile() throws Exception {
        // A loaded asset (original identity snapshotted), then the unit is renamed and saved to a new file with no
        // existing sidecar: the asset must get a new UUID (a renamed unit is likely a different unit).
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Old Name");
        asset.setUnitFileUUID("019f5efd-5a93-78c3-b4f2-dd83804af175");
        asset.storeOriginalUnitData();
        String originalUuid = asset.getUnitFileUUID();

        asset.setChassis("New Name");
        StubSource source = new StubSource(asset);
        File dir = Files.createTempDirectory("bfs-rename").toFile();
        dir.deleteOnExit();

        BFSLinkedFiles.handleSidecarOnSave(null, source, new File(dir, "New Name.blk"), "base-uuid", true, null, LOGGER);

        assertNotEquals(originalUuid, asset.getUnitFileUUID(), "a renamed unit's asset should get a fresh UUID");
    }

    @Test
    void assetKeepsItsOwnUuidOnAnInPlaceResave() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Same Unit");
        asset.setUnitFileUUID("019f5efd-5a93-78c3-b4f2-dd83804af175");
        asset.storeOriginalUnitData();
        StubSource source = new StubSource(asset);
        File dir = Files.createTempDirectory("bfs-inplace").toFile();
        dir.deleteOnExit();
        File baseFile = new File(dir, "Same Unit.blk");

        // First save creates the sidecar.
        BFSLinkedFiles.handleSidecarOnSave(null, source, baseFile, "base-uuid", true, null, LOGGER);
        String uuidAfterFirstSave = asset.getUnitFileUUID();

        // In-place resave (base unchanged) keeps the same asset UUID.
        BFSLinkedFiles.handleSidecarOnSave(null, source, baseFile, "base-uuid", false, null, LOGGER);
        assertEquals(uuidAfterFirstSave, asset.getUnitFileUUID());
    }

    /** Minimal in-memory {@link BFSAssetSource} for exercising the sidecar save path. */
    private static final class StubSource implements BFSAssetSource {
        private final BattlefieldSupportAsset asset;
        private String assetFilePath;

        private StubSource(BattlefieldSupportAsset asset) {
            this.asset = asset;
        }

        @Override
        public BattlefieldSupportAsset getBattlefieldSupportAssetCarrier() {
            return asset;
        }

        @Override
        public boolean isBattlefieldSupportAssetEnabled() {
            return true;
        }

        @Override
        public void setBattlefieldSupportAssetEnabled(boolean enabled) { }

        @Override
        public BattlefieldSupportAsset getEnabledAsset() {
            return asset;
        }

        @Override
        public void adoptAsset(@Nullable BattlefieldSupportAsset asset) { }

        @Override
        public @Nullable String getAssetFilePath() {
            return assetFilePath;
        }

        @Override
        public void setAssetFilePath(@Nullable String assetFilePath) {
            this.assetFilePath = assetFilePath;
        }
    }
}
