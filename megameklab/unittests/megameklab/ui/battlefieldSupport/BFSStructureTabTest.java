/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 */
package megameklab.ui.battlefieldSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Component;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.util.ImageUtil;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.util.IntRangeTextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class BFSStructureTabTest {

    @Test
    void pendingTextIsCommittedBeforeRefresh() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setChassis("Original");
        BFSMainUI editor = new BFSMainUI(asset, "asset.bfs");
        SwingUtilities.invokeAndWait(editor::onActivated);
        BFSStructureTab tab = find(editor, BFSStructureTab.class);
        JTextField chassis = findAll(tab, JTextField.class)
              .filter(field -> "Original".equals(field.getText()))
              .findFirst().orElseThrow();

        SwingUtilities.invokeAndWait(() -> chassis.setText("Pending Name"));
        SwingUtilities.invokeAndWait(tab::commitChanges);

        assertEquals("Pending Name", asset.getChassis());
    }

    @Test
    void focusedSaveRetainsPriorYearForEmptyPendingInput() throws Exception {
        YearEditor fixture = createYearEditor();
        int priorYear = fixture.asset().getYear();

        SwingUtilities.invokeAndWait(() -> fixture.field().setText(""));
        assertEquals(priorYear, fixture.asset().getYear());
        SwingUtilities.invokeAndWait(fixture.editor()::commitForSave);

        assertEquals(priorYear, fixture.asset().getYear());
        assertEquals(String.valueOf(priorYear), fixture.field().getText());
    }

    @Test
    void focusedSaveClampsPendingYearBelowMinimum() throws Exception {
        YearEditor fixture = createYearEditor();
        int priorYear = fixture.asset().getYear();
        int minimum = fixture.field().getMinimum();

        SwingUtilities.invokeAndWait(() -> fixture.field().setText(String.valueOf(minimum - 1)));
        assertEquals(priorYear, fixture.asset().getYear());
        SwingUtilities.invokeAndWait(fixture.editor()::commitForSave);

        assertEquals(minimum, fixture.asset().getYear());
        assertEquals(String.valueOf(minimum), fixture.field().getText());
    }

    @Test
    void focusedSaveClampsPendingYearAboveMaximum() throws Exception {
        YearEditor fixture = createYearEditor();
        int priorYear = fixture.asset().getYear();
        int maximum = fixture.field().getMaximum();

        SwingUtilities.invokeAndWait(() -> fixture.field().setText(String.valueOf(maximum + 1)));
        assertEquals(priorYear, fixture.asset().getYear());
        SwingUtilities.invokeAndWait(fixture.editor()::commitForSave);

        assertEquals(maximum, fixture.asset().getYear());
        assertEquals(String.valueOf(maximum), fixture.field().getText());
    }

    @Test
    void standaloneEmbeddedArtChangesNotifyDirtyTrackingAndCanBeReverted() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        BFSMainUI editor = new BFSMainUI(asset, "asset.bfs");
        SwingUtilities.invokeAndWait(editor::onActivated);
        BFSStructureTab tab = find(editor, BFSStructureTab.class);
        flushEdt();

        String encodedIcon = ImageUtil.base64TextEncodeImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        SwingUtilities.invokeAndWait(() -> tab.setEmbeddedIcon(encodedIcon));
        flushEdt();
        assertTrue(((BattlefieldSupportAsset) editor.getEntity()).hasEmbeddedIcon());
        assertTrue(editor.isDirty());
        assertTrue(editor.hasUndo());

        SwingUtilities.invokeAndWait(editor::undo);
        flushEdt();
        assertFalse(((BattlefieldSupportAsset) editor.getEntity()).hasEmbeddedIcon());
    }

    @Test
    void regularSkillZeroCannotHaveVeteranProfile() {
        assertFalse(BFSStructureTab.canHaveVeteranProfile(0));
        assertTrue(BFSStructureTab.canHaveVeteranProfile(1));
        assertTrue(BFSStructureTab.canHaveVeteranProfile(12));
    }

    private static YearEditor createYearEditor() throws Exception {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        asset.setYear(3075);
        TestBFSMainUI editor = new TestBFSMainUI(asset);
        SwingUtilities.invokeAndWait(editor::onActivated);
        BFSStructureTab tab = find(editor, BFSStructureTab.class);
        IntRangeTextField field = find(tab, IntRangeTextField.class);
        return new YearEditor(asset, editor, field);
    }

    private record YearEditor(BattlefieldSupportAsset asset, TestBFSMainUI editor, IntRangeTextField field) { }

    private static final class TestBFSMainUI extends BFSMainUI {
        private TestBFSMainUI(BattlefieldSupportAsset asset) {
            super(asset, "asset.bfs");
        }

        private void commitForSave() {
            commitPendingEditorChanges();
        }
    }

    private static <T extends Component> T find(Container root, Class<T> type) {
        return findAll(root, type).findFirst().orElseThrow();
    }

    private static <T extends Component> java.util.stream.Stream<T> findAll(Container root, Class<T> type) {
        return Arrays.stream(root.getComponents()).flatMap(component -> {
            java.util.stream.Stream<T> own = type.isInstance(component)
                  ? java.util.stream.Stream.of(type.cast(component)) : java.util.stream.Stream.empty();
            return (component instanceof Container child)
                  ? java.util.stream.Stream.concat(own, findAll(child, type)) : own;
        });
    }

    private static void flushEdt() throws Exception {
        SwingUtilities.invokeAndWait(() -> { });
        SwingUtilities.invokeAndWait(() -> { });
    }
}