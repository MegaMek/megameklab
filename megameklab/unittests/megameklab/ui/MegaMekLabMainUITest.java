/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 */
package megameklab.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.loaders.MekFileParser;
import megamek.common.preference.PreferenceManager;
import megamek.common.units.Entity;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.battlefieldSupport.BFSAssetSource;
import megameklab.ui.battlefieldSupport.BFSLinkedAssetSupport;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.util.TabUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

@ExtendWith(value = InitializeTypes.class)
class MegaMekLabMainUITest {

    @TempDir
    File tempDirectory;

    @Test
    void failedLinkedSaveAsDoesNotCommitBaseOrSidecarDestination() throws Exception {
        BattlefieldSupportAsset base = new BattlefieldSupportAsset();
        base.setChassis("Base");
        StubMainUI editor = new StubMainUI(base, "old/Base.blk");
        editor.assetSupport.setBattlefieldSupportAssetEnabled(true);
        editor.assetSupport.setAssetFilePath("old/Base.bfs");
        File oldSidecar = Files.createTempFile("old-base", ".bfs").toFile();
        Files.writeString(oldSidecar.toPath(), "old sidecar");
        editor.assetSupport.setAssetFilePath(oldSidecar.getPath());

        File blockedParent = Files.createTempFile("blocked-pair", ".tmp").toFile();
        File newBase = new File(blockedParent, "New Base.blk");
        try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
            assertFalse(editor.completePairedSave(newBase.getPath(), true, null));
        }

        assertEquals(newBase.getPath(), editor.getFileName());
        assertEquals(oldSidecar.getPath(), editor.getBattlefieldSupportAssetFilePath());

        assertTrue(blockedParent.delete());
        assertTrue(blockedParent.mkdir());
        // Simulate a later ordinary Save after Reload/Undo: its normal path classification is in-place (false), but
        // the persistent failed-pair state must still force a fresh sidecar beside the new base.
        assertTrue(editor.completePairedSave(newBase.getPath(), false, null));
        assertTrue(new File(blockedParent, "New Base.bfs").isFile());
        assertEquals("old sidecar", Files.readString(oldSidecar.toPath()));
    }

    @Test
    void sessionRestoreRetainsFailedLinkedSaveAsDestination() throws Exception {
        String originalUserDir = PreferenceManager.getClientPreferences().getUserDir();
        PreferenceManager.getClientPreferences().setUserDir(tempDirectory.getPath());
        try {
            Entity base = loadTestTank();
            File oldSidecar = new File(tempDirectory, "A.bfs");
            Files.writeString(oldSidecar.toPath(), "original A sidecar");
            MegaMekLabMainUI editor = UiLoader.getUIWithoutLinkedAsset(base,
                  new File(tempDirectory, "A.blk").getPath());
            editor.adoptLinkedBattlefieldSupportAsset(new BattlefieldSupportAsset(), oldSidecar.getPath(), true);

            File blockedDestination = new File(tempDirectory, "blocked");
            Files.writeString(blockedDestination.toPath(), "not a directory");
            File newBase = new File(blockedDestination, "B.blk");
            try (MockedStatic<PopupMessages> ignored = mockStatic(PopupMessages.class)) {
                assertFalse(editor.completePairedSave(newBase.getPath(), true, null));
            }

            TabUtil.saveTabState(List.of(editor));
            MegaMekLabMainUI restored = TabUtil.loadTabState().get(0);
            assertEquals(newBase.getPath(), restored.getPendingPairedSaveFileName());

            assertTrue(blockedDestination.delete());
            assertTrue(blockedDestination.mkdir());
            assertTrue(restored.completePairedSave(newBase.getPath(), false, null));
            assertTrue(new File(blockedDestination, "B.bfs").isFile());
            assertEquals("original A sidecar", Files.readString(oldSidecar.toPath()));
        } finally {
            PreferenceManager.getClientPreferences().setUserDir(originalUserDir);
        }
    }

    private static Entity loadTestTank() throws Exception {
        String resource = "/ui/generalUnit/BasicInfoViewTest/Puma Assault Tank PAT-001.blk";
        try (InputStream input = MegaMekLabMainUITest.class.getResourceAsStream(resource)) {
            assertNotNull(input);
            return new MekFileParser(input, resource).getEntity();
        }
    }

    private static final class StubMainUI extends MegaMekLabMainUI {
        private final BFSLinkedAssetSupport assetSupport = new BFSLinkedAssetSupport(this::getEntity);

        private StubMainUI(Entity entity, String fileName) {
            setEntity(entity, fileName);
        }

        @Override
        protected BFSAssetSource getBattlefieldSupportAssetSource() {
            return assetSupport;
        }

        @Override
        protected void applyRestoredAsset(BattlefieldSupportAsset asset) {
            assetSupport.adoptAsset(asset);
        }

        @Override
        public void reloadTabs() { }

        @Override
        public JDialog getFloatingEquipmentDatabase() {
            return null;
        }

        @Override
        public List<Mounted<?>> getUnallocatedMounted() {
            return List.of();
        }

        @Override
        public ITechManager getTechManager() {
            return null;
        }

        @Override
        public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) { }
    }
}
