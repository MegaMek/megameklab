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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JOptionPane;

import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.EquipmentType;
import megamek.common.loaders.MekFileParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

/** Verifies the pure {@code .bfs} sidecar path rules used when saving a linked Battlefield Support Asset. */
class BFSLinkedFilesTest {

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

        BFSLinkedFiles.handleSidecarOnSave(null, source, savedBaseFile, finalBaseUuid, true, null);

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

        BFSLinkedFiles.handleSidecarOnSave(
              null, source, new File(dir, "New Name.blk"), "base-uuid", true, null);

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
        BFSLinkedFiles.handleSidecarOnSave(null, source, baseFile, "base-uuid", true, null);
        String uuidAfterFirstSave = asset.getUnitFileUUID();

        // In-place resave (base unchanged) keeps the same asset UUID.
        BFSLinkedFiles.handleSidecarOnSave(null, source, baseFile, "base-uuid", false, null);
        assertEquals(uuidAfterFirstSave, asset.getUnitFileUUID());
    }

    @Test
    void sidecarWriteFailureIsReportedToTheCaller() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Unwritable Asset");
        StubSource source = new StubSource(asset);
        File parentFile = Files.createTempFile("bfs-parent-file", ".tmp").toFile();
        parentFile.deleteOnExit();
        File baseFile = new File(parentFile, "Unwritable Asset.blk");

        assertThrows(IOException.class,
              () -> BFSLinkedFiles.handleSidecarOnSave(null, source, baseFile, "base-uuid", true, null));
          assertNull(source.assetFilePath, "a failed write must not commit the sidecar destination");
    }

    @Test
    void saveAsSidecarFailureRetriesNewPathWithoutOverwritingOldSidecar() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Retry Asset");
        StubSource source = new StubSource(asset);
        File oldSidecar = Files.createTempFile("old-asset", ".bfs").toFile();
        Files.writeString(oldSidecar.toPath(), "old sidecar");
        source.assetFilePath = oldSidecar.getPath();

        File blockedParent = Files.createTempFile("blocked-parent", ".tmp").toFile();
        File newBase = new File(blockedParent, "Retry Asset.blk");
        assertThrows(IOException.class,
              () -> BFSLinkedFiles.handleSidecarOnSave(null, source, newBase, "base-uuid", true, null));
        File intendedSidecar = new File(blockedParent, "Retry Asset.bfs");
          assertEquals(oldSidecar.getPath(), source.assetFilePath,
              "a failed pair must retain the old base/sidecar association");
        assertEquals("old sidecar", Files.readString(oldSidecar.toPath()));

        assertTrue(blockedParent.delete());
        assertTrue(blockedParent.mkdir());
          assertTrue(BFSLinkedFiles.handleSidecarOnSave(null, source, newBase, "base-uuid", true, null));
        assertTrue(intendedSidecar.isFile());
          assertEquals(intendedSidecar.getPath(), source.assetFilePath);
        assertEquals("old sidecar", Files.readString(oldSidecar.toPath()));
    }

    @Test
    void disabledSaveAsDoesNotDeleteOriginalSidecar() throws Exception {
        StubSource source = new StubSource(new BattlefieldSupportAsset());
        source.enabled = false;
        File original = Files.createTempFile("original", ".bfs").toFile();
        source.assetFilePath = original.getPath();
        File newDir = Files.createTempDirectory("disabled-save-as").toFile();

        assertTrue(BFSLinkedFiles.handleSidecarOnSave(null, source, new File(newDir, "New.blk"),
              "base-uuid", true, null));
        assertTrue(original.isFile());
                assertNull(source.assetFilePath, "the new base must no longer be associated with the original sidecar");

                assertTrue(BFSLinkedFiles.handleSidecarOnSave(null, source, new File(newDir, "New.blk"),
              "base-uuid", false, null));
                assertTrue(original.isFile(), "a later in-place save must not target the original sidecar");
    }

    @Test
    void declinedDeletionIsUnresolvedAndKeepsPath() throws Exception {
        StubSource source = new StubSource(new BattlefieldSupportAsset());
        source.enabled = false;
        File base = Files.createTempFile("declined", ".blk").toFile();
        File sidecar = BFSLinkedFiles.coLocatedSidecar(base);
        Files.writeString(sidecar.toPath(), "asset");
        source.assetFilePath = sidecar.getPath();

        try (MockedStatic<JOptionPane> dialogs = mockStatic(JOptionPane.class)) {
            dialogs.when(() -> JOptionPane.showConfirmDialog(any(), any(), any(),
                        any(Integer.class), any(Integer.class)))
                  .thenReturn(JOptionPane.NO_OPTION);
            assertFalse(BFSLinkedFiles.handleSidecarOnSave(null, source, base, "base-uuid", false, null));
        }
        assertTrue(sidecar.isFile());
        assertEquals(sidecar.getPath(), source.assetFilePath);
    }

    @Test
    void successfulDeletionResolvesSaveAndClearsPath() throws Exception {
        StubSource source = new StubSource(new BattlefieldSupportAsset());
        source.enabled = false;
        File base = Files.createTempFile("deleted", ".blk").toFile();
        File sidecar = BFSLinkedFiles.coLocatedSidecar(base);
        Files.writeString(sidecar.toPath(), "asset");
        source.assetFilePath = sidecar.getPath();

        try (MockedStatic<JOptionPane> dialogs = mockStatic(JOptionPane.class)) {
            dialogs.when(() -> JOptionPane.showConfirmDialog(any(), any(), any(),
                        any(Integer.class), any(Integer.class)))
                  .thenReturn(JOptionPane.YES_OPTION);
            assertTrue(BFSLinkedFiles.handleSidecarOnSave(null, source, base, "base-uuid", false, null));
        }
        assertFalse(sidecar.exists());
        assertNull(source.assetFilePath);
    }

    @Test
    void failedDeletionIsUnresolvedAndKeepsPath() throws Exception {
        StubSource source = new StubSource(new BattlefieldSupportAsset());
        source.enabled = false;
        File base = Files.createTempFile("failed-delete", ".blk").toFile();
        File sidecar = BFSLinkedFiles.coLocatedSidecar(base);
        Files.writeString(sidecar.toPath(), "asset");
        source.assetFilePath = sidecar.getPath();

        try (MockedStatic<JOptionPane> dialogs = mockStatic(JOptionPane.class);
              MockedStatic<Files> files = mockStatic(Files.class)) {
            dialogs.when(() -> JOptionPane.showConfirmDialog(any(), any(), any(),
                        any(Integer.class), any(Integer.class)))
                  .thenReturn(JOptionPane.YES_OPTION);
            files.when(() -> Files.deleteIfExists(any(Path.class))).thenThrow(new IOException("denied"));

            assertFalse(BFSLinkedFiles.handleSidecarOnSave(null, source, base, "base-uuid", false, null));
        }
        assertTrue(sidecar.isFile());
        assertEquals(sidecar.getPath(), source.assetFilePath);
    }

    @Test
    void archiveSidecarDeletionIsUnresolved() throws Exception {
        StubSource source = new StubSource(new BattlefieldSupportAsset());
        source.enabled = false;
        source.assetFilePath = "units.zip|Asset.bfs";
        File base = Files.createTempFile("archive-base", ".blk").toFile();

        try (MockedStatic<JOptionPane> ignored = mockStatic(JOptionPane.class)) {
            assertFalse(BFSLinkedFiles.handleSidecarOnSave(null, source, base, "base-uuid", false, null));
        }
        assertEquals("units.zip|Asset.bfs", source.assetFilePath);
    }

    /** Minimal in-memory {@link BFSAssetSource} for exercising the sidecar save path. */
    private static final class StubSource implements BFSAssetSource {
        private final BattlefieldSupportAsset asset;
        private String assetFilePath;
        private boolean enabled = true;

        private StubSource(BattlefieldSupportAsset asset) {
            this.asset = asset;
        }

        @Override
        public BattlefieldSupportAsset getBattlefieldSupportAssetCarrier() {
            return asset;
        }

        @Override
        public BattlefieldSupportAsset getExistingBattlefieldSupportAssetCarrier() {
            return asset;
        }

        @Override
        public boolean isBattlefieldSupportAssetEnabled() {
            return enabled;
        }

        @Override
        public void setBattlefieldSupportAssetEnabled(boolean enabled) { }

        @Override
        public BattlefieldSupportAsset getEnabledAsset() {
            return enabled ? asset : null;
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
