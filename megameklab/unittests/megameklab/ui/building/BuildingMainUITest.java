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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.building;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import megamek.common.board.CubeCoords;
import megamek.common.enums.BuildingType;
import megamek.common.equipment.EquipmentType;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingDesign;
import megamek.common.units.IBuilding;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.BuildingMap;
import megameklab.util.BuildingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InitializeTypes.class)
class BuildingMainUITest {
    private static final CubeCoords EAST = new CubeCoords(1, 0, -1);

    @Test
    void invalidServiceValuesRemainEditableForRepair() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, EAST));
        building.getDesign().getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 0, 0));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 0, Map.of(-1, 4, 0, 4)));
        SwingUtilities.invokeAndWait(() -> {
            var editor = editor(building);
            var doorHeight = find(editor, "N door height", javax.swing.JSpinner.class);
            var capacity = find(editor, "Elevator capacity", javax.swing.JSpinner.class);
            var from = find(editor, "Elevator lowest level", javax.swing.JSpinner.class);
            var to = find(editor, "Elevator highest level", javax.swing.JSpinner.class);
            assertEquals(0, doorHeight.getValue());
            assertEquals(0.0, capacity.getValue());
            assertEquals(-1, from.getValue());
            doorHeight.setValue(1);
            capacity.setValue(20.0);
            from.setValue(0);
            to.setValue(1);
            assertEquals(1, building.getDesign().getDoors().getFirst().height());
            var lift = building.getDesign().getElevators().getFirst();
            assertEquals(20.0, lift.capacity());
            assertEquals(Map.of(0, 4, 1, 4), lift.exits());
        });
    }

    @Test
    void servicePanelsFollowSelectionAndEditLinkedDoorsAndElevatorSides() throws Exception {
        var building = BuildingUtil.newBuilding();
        var south = new CubeCoords(0, 1, -1);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, south));
        var doors = building.getDesign().getDoors();
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 1));
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 2, 1));
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(south, 0), 1, 1));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(south, 20, Map.of(0, 1, 1, 1)));
        SwingUtilities.invokeAndWait(() -> {
            var editor = editor(building);
            editor.getConfigPane().setSelectedIndex(editor.getConfigPane().indexOfTab("Construction & Services"));
            layout(editor);
            assertNotNull(find(editor, "NE door height", javax.swing.JSpinner.class));
            assertNotNull(find(editor, "SE door height", javax.swing.JSpinner.class));
            assertNull(find(editor, "Elevator capacity", javax.swing.JSpinner.class));
            find(editor, "Link NE door with SE", javax.swing.JButton.class).doClick();
            editor.refreshAll();
            find(editor, "Link SE door with " + editor.hexLabel(south) + "/NE", javax.swing.JButton.class).doClick();
            editor.refreshAll();
            find(editor, "NE door height", javax.swing.JSpinner.class).setValue(2);
            assertEquals(List.of(2, 2, 2), doors.stream().map(BuildingDesign.Door::height).toList());
            editor.refreshAll();
            for (String name : List.of("Building footprint legend", "Building pancake legend")) {
                Container legend = find(editor, name, Container.class);
                assertTrue(java.util.Arrays.stream(legend.getComponents())
                      .anyMatch(entry -> entry instanceof JLabel label && label.getText().equals("Large Door")
                            && label.isVisible() && label.getIcon().getIconWidth() > label.getIcon().getIconHeight() * 2));
            }
            layout(editor);
            try {
                var output = java.nio.file.Path.of("build", "building-review", "building-linked-services.png");
                java.nio.file.Files.createDirectories(output.getParent());
                javax.imageio.ImageIO.write(paint(editor), "png", output.toFile());
            } catch (java.io.IOException ex) {
                throw new java.io.UncheckedIOException(ex);
            }
            editor.selectLocation(south, 0);
            assertNull(find(editor, "SE door height", javax.swing.JSpinner.class));
            assertNotNull(find(editor, "Elevator capacity", javax.swing.JSpinner.class));
            var elevatorSide = find(editor, "Elevator 1 level " + BuildingUtil.roofLevelLabel(building, 0) + " access N", javax.swing.JButton.class);
            assertNotNull(elevatorSide);
            elevatorSide.doClick();
            assertEquals(0, building.getDesign().getElevators().getFirst().exits().get(0));
            assertEquals(1, building.getDesign().getElevators().getFirst().exits().get(1));
        });
    }

    @BeforeEach
    void requireGraphicsEnvironment() {
        assumeFalse(GraphicsEnvironment.isHeadless(), "The editor's drag-and-drop tables require a display");
    }

    @Test
    void elevatorControlsWrapIntoFloorRowsWithoutHorizontalScrolling() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0, List.of(CubeCoords.ZERO, EAST));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 2, 1, 2, 2, 2, 3, 2)));
        SwingUtilities.invokeAndWait(() -> {
            var editor = editor(building);
            var services = new BuildingServicePanels(editor);
            services.refresh();
            var panel = services.elevators;
            var scroll = find(panel, "Building elevator scroll", javax.swing.JScrollPane.class);
            var capacity = find(panel, "Elevator capacity", javax.swing.JSpinner.class);
            for (int width : new int[] { 500, panel.getMinimumSize().width, 750, 500 }) {
                panel.setSize(width, 700);
                // Settle width-dependent preferred heights just as Swing validation does after a resize.
                for (int pass = 0; pass < 4; pass++) {
                    panel.invalidate();
                    layout(panel);
                }
                var floors = find(panel, "Elevator 1 floors", JPanel.class);
                var cells = floors.getComponents();
                assertEquals(4, cells.length);
                int columns = width == 500 ? 2 : width == 750 ? 3 : 1;
                assertEquals(columns, java.util.Arrays.stream(cells).filter(cell -> cell.getY() == cells[0].getY()).count());
                assertEquals(scroll.getViewport().getWidth(), scroll.getViewport().getView().getWidth());
                assertFalse(scroll.getHorizontalScrollBar().isVisible());
                assertTrue(capacity.getWidth() < 120, "Capacity must remain a compact numeric field");
                for (Component cell : cells) {
                    assertEquals(cell.getPreferredSize(), cell.getSize());
                    assertTrue(cell.getX() >= 0 && cell.getX() + cell.getWidth() <= floors.getWidth());
                    assertTrue(cell.getY() + cell.getHeight() <= floors.getHeight(), "Panel width " + width
                          + ": floor " + cell.getBounds() + " outside " + floors.getSize() + ", preferred " + floors.getPreferredSize());
                }
                if (width == 500) {
                    assertFalse(scroll.getVerticalScrollBar().isVisible(), "Four floors fit as a 2 by 2 grid");
                    try {
                        var output = java.nio.file.Path.of("build", "building-review", "building-elevator-grid.png");
                        java.nio.file.Files.createDirectories(output.getParent());
                        javax.imageio.ImageIO.write(paint(panel), "png", output.toFile());
                    } catch (java.io.IOException ex) {
                        throw new java.io.UncheckedIOException(ex);
                    }
                }
            }
        });
    }

    @Test
    void pancakeSelectsHexAndFloorWithoutLeavingTheCurrentTab() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        building.addEquipment(EquipmentType.get("ISMediumLaser"), 5);
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            var tabs = editor.getConfigPane();
            editor.showEquipment();
            Component equipment = tabs.getSelectedComponent();
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            assertFalse(SwingUtilities.isDescendingFrom(pancake, tabs));
            JTable loadout = find(editor, "Building equipment", JTable.class);
            assertEquals(0, loadout.getRowCount());
            paint(pancake);
            click(pancake, pointFor(pancake, editor.hexLabel(EAST) + "/2"));

            assertSame(equipment, tabs.getSelectedComponent());
            assertEquals(EAST, editor.selectedHex());
            assertEquals(2, editor.selectedFloor());
            assertEquals(5, editor.selectedLocation());
            assertEquals(1, loadout.getRowCount());
            JComponent top = find(editor, "Building footprint", JComponent.class);
            paint(top);
            assertTrue(top.getToolTipText(mouse(top, pointFor(top, editor.hexLabel(EAST) + "/2"))).endsWith("/2"));
            assertTrue(find(editor, "Editing location", JLabel.class).getText().endsWith("Level 2"));

            click(pancake, new Point(1, 1));
            assertEquals(EAST, editor.selectedHex(), "Empty space must not select an arbitrary hex");
            assertEquals(2, editor.selectedFloor());
        });
    }

    @Test
    void navigatorKeepsItsWidthAndScrollPositionAcrossEveryTab() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 20, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            JSplitPane split = find(editor, "Building editor split", JSplitPane.class);
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            JScrollPane scroll = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, pancake);
            int originalWidth = split.getRightComponent().getWidth();
            split.setDividerLocation(split.getLeftComponent().getWidth() - 100);
            layout(editor);
            assertTrue(split.getRightComponent().getWidth() > originalWidth);
            scroll.getVerticalScrollBar().setValue(150);
            int divider = split.getDividerLocation();
            Point position = scroll.getViewport().getViewPosition();
            assertTrue(position.y > 0, "Tall buildings must scroll");

            for (int index = 0; index < editor.getConfigPane().getTabCount(); index++) {
                editor.getConfigPane().setSelectedIndex(index);
                layout(editor);
                assertSame(pancake, find(editor, "Building pancake", JComponent.class));
                assertTrue(split.getRightComponent().isVisible());
                assertEquals(divider, split.getDividerLocation());
                assertEquals(position, scroll.getViewport().getViewPosition());
            }
            int width = split.getRightComponent().getWidth();
            editor.setSize(1500, 900);
            layout(editor);
            assertEquals(width, split.getRightComponent().getWidth(), "Window resizing should grow the editor area");
        });
    }

    @Test
    void changingLocationRevealsTheSelectedHexInTallBuildings() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 20, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        AtomicReference<BuildingMainUI> editorReference = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> editorReference.set(editor(building)));
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editorReference.get();
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            assertTrue(pancake.getVisibleRect().contains(selectionPixel(paint(pancake))));
            editor.selectLocation(EAST, 19);
            paint(pancake);
        });
        SwingUtilities.invokeAndWait(() -> {
            JComponent pancake = find(editorReference.get(), "Building pancake", JComponent.class);
            assertTrue(pancake.getVisibleRect().contains(selectionPixel(paint(pancake))));
            JScrollPane scroll = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, pancake);
            scroll.getVerticalScrollBar().setValue(150);
            paint(pancake);
        });
        SwingUtilities.invokeAndWait(() -> {
            JComponent pancake = find(editorReference.get(), "Building pancake", JComponent.class);
            assertEquals(150, pancake.getVisibleRect().y, "Repainting must preserve manual scrolling");
        });
    }

    @Test
    void bridgeDeckClicksUseTheDeckElevationAndNativeFloorZero() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.BRIDGE, 1, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        building.getDesign().getBridgeDecks().put(CubeCoords.ZERO, 2);
        building.getDesign().getBridgeDecks().put(EAST, 4);
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            paint(pancake);
            click(pancake, pointFor(pancake, editor.hexLabel(EAST) + "/4"));
            assertEquals(EAST, editor.selectedHex());
            assertEquals(0, editor.selectedFloor());
            assertTrue(find(editor, "Editing location", JLabel.class).getText().endsWith("Deck 4"));
        });
    }

    @Test
    void selectionRemainsValidWhenMobileFloorsAndHexesAreRemoved() throws Exception {
        var building = BuildingUtil.newMobileStructure();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        building.getDesign().setBaseLevel(-2);
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            paint(pancake);
            click(pancake, pointFor(pancake, editor.hexLabel(EAST) + "/Ground"));
            assertEquals(2, editor.selectedFloor());
            BuildingUtil.setHexHeight(building, EAST, 1);
            editor.refreshAll();
            assertEquals(0, editor.selectedFloor());
            assertEquals(EAST, editor.selectedHex());
            BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
                  List.of(CubeCoords.ZERO));
            editor.refreshAll();
            assertEquals(CubeCoords.ZERO, editor.selectedHex());
            assertEquals(0, editor.selectedFloor());
        });
    }

    @Test
    void selectedHexHasAStrokeOnPlainAndColoredFloors() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(EAST, 20, Map.of(0, 1, 1, 1)));
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            JComponent pancake = find(editor, "Building pancake", JComponent.class);
            BufferedImage plain = paint(pancake);
            Point plainStroke = selectionPixel(plain);
            click(pancake, pointFor(pancake, editor.hexLabel(EAST) + "/1"));
            BufferedImage colored = paint(pancake);
            Point coloredStroke = selectionPixel(colored);
            assertNotEquals(plainStroke, coloredStroke);
            assertNotEquals(plain.getRGB(plainStroke.x, plainStroke.y), colored.getRGB(plainStroke.x, plainStroke.y));
        });
    }

    @Test
    void selectionPreservesDoorAndElevatorMarksInBothViews() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        for (int facing = 0; facing < 6; facing++) {
            building.getDesign().getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(EAST, 1), facing, 1));
        }
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(EAST, 20, Map.of(0, 1, 2, 1)));
        var doors = List.copyOf(building.getDesign().getDoors());
        var elevators = List.copyOf(building.getDesign().getElevators());
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            for (String name : List.of("Building footprint", "Building pancake")) {
                editor.selectLocation(CubeCoords.ZERO, 1);
                JComponent view = find(editor, name, JComponent.class);
                BufferedImage features = paint(view);
                building.getDesign().getDoors().clear();
                building.getDesign().getElevators().clear();
                BufferedImage plain = paint(view);
                building.getDesign().getDoors().addAll(doors);
                building.getDesign().getElevators().addAll(elevators);
                editor.selectLocation(EAST, 1);
                BufferedImage selected = paint(view);
                int symbolPixels = 0;
                int shaftPixels = 0;
                for (int y = 0; y < features.getHeight(); y++) {
                    for (int x = 0; x < features.getWidth(); x++) {
                        int color = features.getRGB(x, y);
                        if (color == plain.getRGB(x, y)) {
                            continue;
                        }
                        if (color == Color.BLACK.getRGB()) {
                            symbolPixels++;
                            assertEquals(color, selected.getRGB(x, y), name + " obscured a feature symbol at " + x + "," + y);
                        } else if (name.equals("Building pancake")
                              && color == Color.decode(BuildingMap.Feature.ELEVATOR.color).getRGB()
                              && view.getToolTipText(mouse(view, new Point(x, y))) == null) {
                            shaftPixels++;
                            assertEquals(color, selected.getRGB(x, y), "Selection obscured an elevator shaft at " + x + "," + y);
                        }
                    }
                }
                assertTrue(symbolPixels > 20, "The fixture must have visible door and elevator symbols");
                if (name.equals("Building pancake")) {
                    assertTrue(shaftPixels > 20, "The fixture must have visible elevator shafts");
                }
                selectionPixel(selected);
            }
        });
    }

    @Test
    void elevatorAccessSidesAppearAsAmberDoorTrianglesInBothViews() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        var throughFloor = new BuildingDesign.Elevator(EAST, 20, Map.of(0, 1, 2, 1));
        var withAccess = new BuildingDesign.Elevator(EAST, 20, Map.of(0, 1, 1, 63, 2, 1));
        building.getDesign().getElevators().add(throughFloor);
        SwingUtilities.invokeAndWait(() -> {
            BuildingMainUI editor = editor(building);
            editor.selectLocation(EAST, 1);
            for (String name : List.of("Building footprint", "Building pancake")) {
                JComponent view = find(editor, name, JComponent.class);
                building.getDesign().getElevators().set(0, throughFloor);
                BufferedImage withoutDoors = paint(view);
                building.getDesign().getElevators().set(0, withAccess);
                BufferedImage withDoors = paint(view);
                int amber = Color.decode(BuildingMap.Feature.ELEVATOR_DOOR.color).getRGB();
                int doorPixels = 0;
                for (int y = 0; y < withDoors.getHeight(); y++) {
                    for (int x = 0; x < withDoors.getWidth(); x++) {
                        if (withDoors.getRGB(x, y) == amber && withoutDoors.getRGB(x, y) != amber) {
                            String tooltip = view.getToolTipText(mouse(view, new Point(x, y)));
                            if (tooltip == null || !tooltip.startsWith(editor.hexLabel(EAST) + "/")) {
                                doorPixels++;
                            }
                        }
                    }
                }
                assertTrue(doorPixels > 20, name + " must draw amber doors projecting beyond the selected hex");
                selectionPixel(withDoors);
            }
            editor.refreshAll();
            for (String name : List.of("Building footprint legend", "Building pancake legend")) {
                Container legend = find(editor, name, Container.class);
                assertTrue(java.util.Arrays.stream(legend.getComponents())
                      .anyMatch(entry -> entry instanceof JLabel label && label.getText().equals("Elevator door")
                            && label.isVisible() && label.getIcon() != null));
            }
        });
    }

    private static BuildingMainUI editor(AbstractBuildingEntity building) {
        BuildingMainUI editor = new BuildingMainUI(building, "");
        editor.onActivated();
        editor.setSize(1400, 900);
        layout(editor);
        paint(editor);
        return editor;
    }

    private static void layout(Container container) {
        container.doLayout();
        for (Component child : container.getComponents()) {
            if (child instanceof Container nested) {
                layout(nested);
            }
        }
    }

    private static <T extends Component> T find(Container parent, String name, Class<T> type) {
        for (Component child : parent.getComponents()) {
            if (name.equals(child.getName())) {
                return type.cast(child);
            }
            if (child instanceof Container nested) {
                T result = find(nested, name, type);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private static BufferedImage paint(JComponent component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        component.paint(graphics);
        graphics.dispose();
        return image;
    }

    private static MouseEvent mouse(JComponent component, Point point) {
        return new MouseEvent(component, MouseEvent.MOUSE_CLICKED, 0, 0, point.x, point.y, 1, false, MouseEvent.BUTTON1);
    }

    private static void click(JComponent component, Point point) {
        component.dispatchEvent(mouse(component, point));
    }

    private static Point pointFor(JComponent component, String label) {
        for (int y = 4; y < component.getHeight(); y += 4) {
            for (int x = 4; x < component.getWidth(); x += 4) {
                Point point = new Point(x, y);
                String tooltip = component.getToolTipText(mouse(component, point));
                if (tooltip != null && (tooltip.equals(label) || tooltip.startsWith(label + " —"))) {
                    return point;
                }
            }
        }
        return fail("No clickable hex found for " + label);
    }

    private static Point selectionPixel(BufferedImage image) {
        int stroke = new Color(30, 105, 210).getRGB();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) == stroke) {
                    return new Point(x, y);
                }
            }
        }
        return fail("The selected hex must have a visible blue stroke");
    }
}
