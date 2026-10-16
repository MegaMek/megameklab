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

package megameklab.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import megamek.common.bays.FirstClassQuartersCargoBay;
import megamek.common.board.CubeCoords;
import megamek.common.enums.BuildingType;
import megamek.common.equipment.EquipmentType;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.BLKStructureFile;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.BuildingDoors;
import megamek.common.units.BuildingEntity;
import megamek.common.units.IBuilding;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.BuildingMap.DoorMarker;
import megameklab.util.BuildingMap.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InitializeTypes.class)
class BuildingUtilTest {
    @Test
    void linkedOpeningsSurviveNativeRoundTripAndTransforms() throws Exception {
        var building = BuildingUtil.newBuilding();
        var south = new CubeCoords(0, 1, -1);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, south));
        var doors = building.getDesign().getDoors();
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 2));
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 2, 2));
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(south, 0), 1, 2));
        BuildingDoors.link(doors, doors.get(0), doors.get(1));
        BuildingDoors.link(doors, doors.get(1), doors.get(2));
        assertEquals(List.of(3), BuildingDoors.groups(doors).stream().map(List::size).toList());
        for (var geometry : BuildingDoors.geometry(doors).values()) {
            assertTrue(geometry.line().stream().allMatch(point -> Math.abs(point.x() - .75) < .0001));
            assertEquals(1.01, geometry.arrow().getFirst().x(), .0001);
        }
        var geometry = BuildingDoors.geometry(doors);
        var lineY = doors.stream().flatMapToDouble(door -> geometry.get(door).line().stream()
              .mapToDouble(point -> point.y() + Math.sqrt(3) * door.position().hex().r())).summaryStatistics();
        assertEquals(-Math.sqrt(3) / 4, lineY.getMin(), .0001);
        assertEquals(3 * Math.sqrt(3) / 4, lineY.getMax(), .0001);
        var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(building)).getEntity();
        assertEquals(doors, loaded.getDesign().getDoors());
        BuildingUtil.transform(loaded, hex -> new CubeCoords(-hex.r(), -hex.s(), -hex.q()), side -> (side + 1) % 6);
        assertEquals(List.of(3), BuildingDoors.groups(loaded.getDesign().getDoors()).stream().map(List::size).toList());
        BuildingDoors.geometry(loaded.getDesign().getDoors()).forEach((door, opening) -> {
            var vertices = BuildingDoors.vertices(door);
            var hex = door.position().hex();
            double centerX = (vertices.getFirst().x() + vertices.getLast().x()) / 4 - 1.5 * hex.q();
            double centerY = (vertices.getFirst().y() + vertices.getLast().y()) * Math.sqrt(3) / 4
                  - Math.sqrt(3) * (hex.r() + hex.q() / 2.0);
            assertEquals(centerX, opening.arrow().stream().mapToDouble(BuildingDoors.Point::x).average().orElseThrow(), .0001);
            assertEquals(centerY, opening.arrow().stream().mapToDouble(BuildingDoors.Point::y).average().orElseThrow(), .0001);
        });
        BuildingDoors.unlink(doors, doors.get(1));
        assertTrue(doors.stream().allMatch(door -> door.linkGroup() == 0));
    }

    @Test
    void footprintCleanupCanRemoveOrKeepAffectedOpeningsWithoutRemovingElevators() {
        var building = BuildingUtil.newBuilding();
        var north = new CubeCoords(0, -1, 1);
        var northeast = new CubeCoords(1, -1, 0);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, north));
        var doors = building.getDesign().getDoors();
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 2));
        doors.add(new BuildingDesign.Door(new BuildingDesign.Position(north, 0), 2, 1));
        var next = List.of(CubeCoords.ZERO, north, northeast);
        var changes = BuildingUtil.topologyDoorChanges(building, next);
        assertEquals(2, changes.count());
        assertEquals(2, doors.size(), "Inspection or declining cleanup must not remove doors");
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, next);
        assertEquals(2, doors.size(), "Keeping invalid doors retains the footprint edit");
        changes.remove().run();
        assertTrue(doors.isEmpty());
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 3, 1, 3, 2, 1)));
        changes = BuildingUtil.topologyDoorChanges(building, List.of(CubeCoords.ZERO, northeast));
        assertEquals(3, changes.count());
        changes.remove().run();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, northeast));
        assertEquals(1, building.getDesign().getElevators().size());
        assertEquals(Map.of(0, 2, 1, 2, 2, 0), building.getDesign().getElevators().getFirst().exits());
    }

    @Test
    void featureIndexBoundsMalformedLevelRangesToTheBuildingHeight() {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO));
        var door = new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 1), 0, Integer.MAX_VALUE);
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20,
              Map.of(1, 1, Integer.MAX_VALUE, 1)));

        var index = BuildingMap.featureIndex(building, List.of(door));

        assertEquals(List.of(BuildingMap.Feature.ELEVATOR, BuildingMap.Feature.DOOR, BuildingMap.Feature.ELEVATOR_DOOR),
              index.features(CubeCoords.ZERO, 1));
        assertEquals(List.of(new DoorMarker(0, Feature.DOOR), new DoorMarker(0, Feature.ELEVATOR_DOOR)),
              index.doors(CubeCoords.ZERO, 1));
        assertTrue(index.features(CubeCoords.ZERO, 2).isEmpty());
        assertTrue(index.doors(CubeCoords.ZERO, 2).isEmpty());
    }

    @Test
    void featureIndexUsesBridgeDeckElevationInsteadOfStructuralHeight() {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.RAIL, IBuilding.BRIDGE, 1, 80, 0, List.of(CubeCoords.ZERO));
        building.getDesign().getBridgeDecks().put(CubeCoords.ZERO, 5);
        var door = new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 5), 2, 1);
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 1, 5, 1)));

        var index = BuildingMap.featureIndex(building, List.of(door));

        assertEquals(List.of(BuildingMap.Feature.ELEVATOR, BuildingMap.Feature.DOOR, BuildingMap.Feature.ELEVATOR_DOOR),
              index.features(CubeCoords.ZERO, 5));
        assertEquals(List.of(new DoorMarker(2, Feature.DOOR), new DoorMarker(0, Feature.ELEVATOR_DOOR)),
              index.doors(CubeCoords.ZERO, 5));
    }

    @Test
    void featureIndexPreservesFeatureOrderRoofProjectionAndDoorOrder() throws Exception {
        var building = BuildingUtil.newMobileStructure();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0, List.of(CubeCoords.ZERO, EAST));
        BuildingUtil.setHexHeight(building, EAST, 2);
        var quarters = new FirstClassQuartersCargoBay(2);
        building.addTransporter(quarters);
        building.getDesign().getBaySpace().put(quarters, List.of(
              new BuildingDesign.Space(new BuildingDesign.Position(CubeCoords.ZERO, 2), 10),
              new BuildingDesign.Space(new BuildingDesign.Position(EAST, 0), 0),
              new BuildingDesign.Space(new BuildingDesign.Position(EAST, 1), 10)));
        var deck = building.addEquipment(EquipmentType.get("Building Flight Deck"), 0);
        deck.setSponsonTurretMounted(true);
        var outside = new CubeCoords(-1, 0, 1);
        building.getDesign().getEquipmentSpace().put(deck, List.of(
              new BuildingDesign.Position(CubeCoords.ZERO, 0), new BuildingDesign.Position(EAST, 0),
              new BuildingDesign.Position(outside, 0)));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 1, 3, 1)));
        var door = new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 1), 2, 2);
        var secondDoor = new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 2), 4, 1);
        var doors = List.of(door, secondDoor);

        var index = BuildingMap.featureIndex(building, doors);

        assertEquals(List.of(BuildingMap.Feature.BAY, BuildingMap.Feature.ELEVATOR, BuildingMap.Feature.DECK,
              BuildingMap.Feature.TURRET, BuildingMap.Feature.DOOR), index.features(CubeCoords.ZERO, 2));
        assertEquals(List.of(BuildingMap.Feature.BAY, BuildingMap.Feature.DECK, BuildingMap.Feature.TURRET),
              index.features(EAST, 1));
        assertTrue(index.features(EAST, 0).isEmpty());
        assertTrue(index.features(outside, -1).isEmpty());
        assertEquals(List.of(new DoorMarker(2, Feature.DOOR), new DoorMarker(4, Feature.DOOR)), index.doors(CubeCoords.ZERO, 2));
        assertEquals(List.of(new DoorMarker(2, Feature.DOOR)), index.doors(CubeCoords.ZERO, 1));
        assertEquals(BuildingMap.Feature.ELEVATOR, BuildingMap.fill(index.features(CubeCoords.ZERO, 2)));

        building.getDesign().getElevators().clear();
        deck.setSponsonTurretMounted(false);
        var refreshed = BuildingMap.featureIndex(building, List.of());
        assertEquals(List.of(BuildingMap.Feature.BAY, BuildingMap.Feature.DECK), refreshed.features(CubeCoords.ZERO, 2));
        assertEquals(5, index.features(CubeCoords.ZERO, 2).size(), "A render's snapshot must remain stable");
    }

    @Test
    void shorterMobileHexRemovesEquipmentAndDoorsOnDeletedFloors() throws Exception {
        var mobile = BuildingUtil.newMobileStructure();
        var hexes = List.copyOf(mobile.getInternalBuilding().getOriginalCoordsList());
        BuildingUtil.configure(mobile, BuildingType.MEDIUM, IBuilding.FORTRESS, 3, 40, 0, hexes);
        var retained = mobile.addEquipment(EquipmentType.get("ISMediumLaser"), 0);
        var removed = mobile.addEquipment(EquipmentType.get("ISMediumLaser"), 2);
        mobile.getDesign().getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 2), 0, 1));
        BuildingUtil.setHexHeight(mobile, CubeCoords.ZERO, 1);
        assertTrue(mobile.getEquipment().contains(retained));
        assertFalse(mobile.getEquipment().contains(removed));
        assertTrue(mobile.getDesign().getDoors().isEmpty());
        assertEquals(1, mobile.getInternalBuilding().getHeight(CubeCoords.ZERO));
        assertEquals(3, mobile.getInternalBuilding().getHeight(hexes.get(1)));
        var loaded = new BLKStructureFile(BLKFile.getBlock(mobile)).getEntity();
        assertInstanceOf(megamek.common.units.MobileStructure.class, loaded);
        assertEquals(1, ((megamek.common.units.MobileStructure) loaded).getInternalBuilding().getHeight(CubeCoords.ZERO));
        assertEquals(1, loaded.getEquipment().size());
    }

    private static final CubeCoords EAST = new CubeCoords(1, 0, -1);

    @Test
    void elevatorDoorMarkersUseExactStopsAndMergeSharedAccessSides() {
        var building = BuildingUtil.newMobileStructure();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        BuildingUtil.setHexHeight(building, EAST, 1);
        var design = building.getDesign();
        design.getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 5, 2, 32, 3, 8)));
        design.getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 1, 2, 32)));
        design.getElevators().add(new BuildingDesign.Elevator(EAST, 20, Map.of(0, 8, 1, 16)));
        var structuralDoor = new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 4, 2);
        design.getDoors().add(structuralDoor);
        var index = BuildingMap.featureIndex(building, design.getMapDoors());

        assertEquals(List.of(new DoorMarker(4, Feature.DOOR), new DoorMarker(0, Feature.ELEVATOR_DOOR),
              new DoorMarker(2, Feature.ELEVATOR_DOOR)), index.doors(CubeCoords.ZERO, 0));
        assertEquals(List.of(new DoorMarker(4, Feature.DOOR)), index.doors(CubeCoords.ZERO, 1));
        assertEquals(List.of(new DoorMarker(5, Feature.ELEVATOR_DOOR)), index.doors(CubeCoords.ZERO, 2));
        assertTrue(index.doors(CubeCoords.ZERO, 3).isEmpty(), "Roof access must not appear on an interior floor");
        assertEquals(List.of(new DoorMarker(3, Feature.ELEVATOR_DOOR)), index.doors(EAST, 0));
        assertTrue(index.doors(EAST, 1).isEmpty(), "A shorter hex has no interior at its roof level");
        assertFalse(index.features(CubeCoords.ZERO, 1).contains(Feature.ELEVATOR_DOOR));
        assertTrue(index.features(CubeCoords.ZERO, 2).contains(Feature.ELEVATOR_DOOR));
        assertEquals(List.of(structuralDoor), design.getMapDoors(), "Rendering must not add structural doors");
        design.getElevators().clear();
        assertEquals(List.of(new DoorMarker(5, Feature.ELEVATOR_DOOR)), index.doors(CubeCoords.ZERO, 2));
        assertTrue(BuildingMap.featureIndex(building, design.getMapDoors()).doors(CubeCoords.ZERO, 2).isEmpty());
    }

    @Test
    void groundReferenceRoundTripsWithoutMovingEquipmentDoorsOrElevators() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 4, 80, 0, List.of(CubeCoords.ZERO));
        entity.addEquipment(EquipmentType.get("ISMediumLaser"), 1);
        entity.getDesign().getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 2), 0, 1));
        entity.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 0, 1, 0, 2, 0, 3, 0, 4, 0)));
        entity.getDesign().setBaseLevel(-2);
        var block = BLKFile.getBlock(entity);
        assertTrue(List.of(block.getDataAsString("building_options")).contains("base_level=-2"));
        var loaded = (BuildingEntity) new BLKStructureFile(block).getEntity();
        assertEquals(-2, loaded.getDesign().getBaseLevel());
        assertEquals(List.of("0101/-2", "0101/-1", "0101/G", "0101/1"), java.util.stream.IntStream.range(0, 4)
              .mapToObj(loc -> BuildingUtil.locationLabel(loaded, loc)).toList());
        assertEquals(1, loaded.getEquipment().getFirst().getLocation());
        assertEquals(entity.getDesign().getDoors(), loaded.getDesign().getDoors());
        assertEquals(entity.getDesign().getElevators(), loaded.getDesign().getElevators());
        assertEquals("Roof (2)", BuildingUtil.roofLevelLabel(loaded, 4));
        assertEquals(entity.getWeight(), loaded.getWeight());
    }

    @Test
    void automaticSubsurfaceNumberingUsesRoofCoverAndAnExplicitZeroOverridesIt() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0, List.of(CubeCoords.ZERO));
        assertEquals(0, BuildingConstruction.baseLevel(entity));
        for (var site : List.of(BuildingDesign.Site.UNDERGROUND, BuildingDesign.Site.UNDERWATER)) {
            entity.getDesign().setSite(site);
            entity.getDesign().setDepth(2);
            var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(entity)).getEntity();
            assertNull(loaded.getDesign().getBaseLevel());
            assertEquals(-5, BuildingConstruction.baseLevel(loaded));
            assertEquals("0101/-5", BuildingUtil.locationLabel(loaded, 0));
            assertEquals("Roof (-2)", BuildingUtil.roofLevelLabel(loaded, 3));
        }
        entity.getDesign().setBaseLevel(0);
        var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(entity)).getEntity();
        assertEquals(0, loaded.getDesign().getBaseLevel());
        assertEquals("0101/G", BuildingUtil.locationLabel(loaded, 0));
        loaded.getDesign().setBaseLevel(null);
        assertEquals(-5, BuildingConstruction.baseLevel(loaded));
        loaded.getDesign().setSite(BuildingDesign.Site.SURFACE);
        assertEquals(0, BuildingConstruction.baseLevel(loaded));
    }

    @Test
    void numbersSingleHexFrom0101AndPreservesAdjacencyAcrossColumnParity() {
        assertEquals("0101", BuildingUtil.sheetGrid(List.of(CubeCoords.ZERO)).label(CubeCoords.ZERO));
        List<CubeCoords> hexes = List.of(CubeCoords.ZERO, EAST, new CubeCoords(-1, 0, 1));
        var grid = BuildingUtil.sheetGrid(hexes);
        for (CubeCoords a : hexes) {
            for (CubeCoords b : hexes) {
                assertEquals(a.toOffset().distance(b.toOffset()), grid.position(a).distance(grid.position(b)));
            }
        }
        assertEquals("0101/G", BuildingUtil.locationLabel(BuildingUtil.newBuilding(), 0));
    }

    @Test
    void numbersFrom0101RegardlessOfTheAuthoredOriginOrInputOrder() {
        var footprint = java.util.stream.IntStream.range(0, 14)
              .mapToObj(i -> new CubeCoords(i / 7, i % 7, -i / 7 - i % 7)).toList();
        for (var origin : List.of(CubeCoords.ZERO, new CubeCoords(-10, -10, 20),
              new CubeCoords(37, -51, 14), new CubeCoords(-94, 63, 31))) {
            var hexes = footprint.stream().map(hex -> hex.add(origin)).toList();
            var grid = BuildingUtil.sheetGrid(hexes);
            assertEquals(9, grid.columns());
            assertEquals(7, grid.rows());
            assertEquals("0101", grid.label(hexes.getFirst()));
            assertEquals("0207", grid.label(hexes.getLast()));
            assertEquals("0101", BuildingUtil.sheetGrid(List.of(origin)).label(origin));
            assertEquals(grid, BuildingUtil.sheetGrid(hexes.reversed()), "Input order must not affect placement");
            for (var a : hexes) {
                var position = grid.position(a);
                assertTrue(position.getX() >= 0 && position.getX() < grid.columns());
                assertTrue(position.getY() >= 0 && position.getY() < grid.rows());
                for (var b : hexes) {
                    assertEquals(a.toOffset().distance(b.toOffset()), position.distance(grid.position(b)));
                }
            }
        }
    }

    @Test
    void irregularFootprintsKeepPositiveLabelsAndLeaveMissingCornersEmpty() {
        var footprint = List.of(CubeCoords.ZERO, new CubeCoords(0, -1, 1), new CubeCoords(1, -1, 0),
              new CubeCoords(1, 0, -1), new CubeCoords(0, 1, -1), new CubeCoords(-1, 1, 0), new CubeCoords(-1, 0, 1));
        for (var origin : List.of(CubeCoords.ZERO, new CubeCoords(-10, -10, 20), new CubeCoords(37, -51, 14))) {
            var hexes = footprint.stream().map(hex -> hex.add(origin)).toList();
            var grid = BuildingUtil.sheetGrid(hexes);
            assertEquals(List.of("0202", "0201", "0302", "0303", "0203", "0103", "0102"),
                  hexes.stream().map(grid::label).toList());
            for (var a : hexes) {
                for (var b : hexes) {
                    assertEquals(a.toOffset().distance(b.toOffset()), grid.position(a).distance(grid.position(b)));
                }
            }
        }
    }

    @Test
    void resizingRemapsSurvivingEquipmentAndRemovesDeletedFloorsAndHexes() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 32,
              List.of(CubeCoords.ZERO, EAST));
        var ground = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 0);
        var upper = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 2);
        var eastern = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 4);
        assertEquals(100, entity.getNumberOfCriticalSlots(4));
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 32,
              List.of(CubeCoords.ZERO, EAST));
        assertEquals(4, entity.locations());
        assertFalse(entity.getEquipment().contains(upper));
        assertFalse(entity.getWeaponList().contains(upper));
        assertEquals(3, eastern.getLocation());
        assertEquals(0, ground.getLocation());
        assertEquals(eastern, entity.getCritical(3, 0).getMount());
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 32, List.of(CubeCoords.ZERO));
        assertEquals(2, entity.locations());
        assertEquals(List.of(ground), entity.getEquipment());
        assertEquals(32, entity.getArmor(0));
        assertEquals(80, entity.getOInternal(1));
    }

    @Test
    void nativeBlkRoundTripRetainsGeometryArmorFacingTurretSizeAndAmmo() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 32,
              List.of(CubeCoords.ZERO, EAST));
        var weapon = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 4);
        weapon.setFacing(5);
        weapon.setSponsonTurretMounted(true);
        var generator = entity.addEquipment(EquipmentType.get("FUSION PowerGenerator"), 0);
        generator.setSize(12.5);
        var ammo = entity.addEquipment(EquipmentType.get("IS Ammo AC/5"), 2);
        ammo.setOriginalShots(17);
        ammo.setShotsLeft(17);
        entity.addTransporter(new FirstClassQuartersCargoBay(2));
        var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(entity)).getEntity();
        assertEquals(6, loaded.locations());
        assertEquals(List.of(CubeCoords.ZERO, EAST), loaded.getInternalBuilding().getCoordsList());
        var loadedWeapon = loaded.getWeaponList().getFirst();
        assertEquals(4, loadedWeapon.getLocation());
        assertEquals(5, loadedWeapon.getFacing());
        assertTrue(loadedWeapon.isSponsonTurretMounted());
        assertEquals(12.5, loaded.getMisc().getFirst().getSize());
        assertEquals(17, loaded.getAmmo().getFirst().getBaseShotsLeft());
        assertEquals(ammo.getTonnage(), loaded.getAmmo().getFirst().getTonnage());
        assertEquals(20, loaded.getTransportBays().getFirst().getWeight());
        assertEquals(80, loaded.getOInternal(5));
        assertEquals(32, loaded.getArmor(5));
        assertFalse(UnitUtil.saveUnitToString(loaded, false).contains("Unallocated Equipment"));
        loaded.getAmmo().getFirst().setShotsLeft(0);
        var empty = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(loaded)).getEntity();
        assertEquals(0, empty.getAmmo().getFirst().getBaseShotsLeft());
    }

    @Test
    void rotationKeepsEquipmentAndFacingWithItsPhysicalHex() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0,
              List.of(CubeCoords.ZERO, EAST));
        var weapon = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 3);
        weapon.setFacing(5);
        for (int i = 0; i < 6; i++) {
            BuildingUtil.rotate(entity);
            assertTrue(entity.getEquipment().contains(weapon));
            assertEquals(3, weapon.getLocation());
        }
        assertEquals(List.of(CubeCoords.ZERO, EAST), entity.getInternalBuilding().getCoordsList());
        assertEquals(5, weapon.getFacing());
        assertEquals(weapon, entity.getCritical(3, 0).getMount());
    }

    @Test
    void removingTheOriginRebasesSurvivorsWithoutLosingTheirEquipment() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, EAST));
        var removed = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 0);
        var retained = entity.addEquipment(EquipmentType.get("ISMediumLaser"), 3);
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(EAST));
        assertEquals(List.of(CubeCoords.ZERO), entity.getInternalBuilding().getCoordsList());
        assertFalse(entity.getEquipment().contains(removed));
        assertEquals(List.of(retained), entity.getEquipment());
        assertEquals(1, retained.getLocation());
        assertEquals("0101/1", BuildingUtil.locationLabel(entity, retained.getLocation()));
    }

    @Test
    void geometryEditsKeepServicesAndSharedMassWithTheirPhysicalLocations() throws Exception {
        var entity = BuildingUtil.newBuilding();
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0, List.of(CubeCoords.ZERO, EAST));
        var generator = entity.addEquipment(EquipmentType.get("FUSION PowerGenerator"), 4);
        generator.setSize(6);
        var quarters = new FirstClassQuartersCargoBay(2);
        entity.addTransporter(quarters);
        var design = entity.getDesign();
        design.getEquipmentSpace().put(generator, List.of(new BuildingDesign.Position(EAST, 1),
              new BuildingDesign.Position(CubeCoords.ZERO, 1)));
        design.getBaySpace().put(quarters, List.of(new BuildingDesign.Space(new BuildingDesign.Position(EAST, 0), 20)));
        design.getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(EAST, 0), 2, 2));
        design.getElevators().add(new BuildingDesign.Elevator(EAST, 20, Map.of(0, 32, 1, 32, 2, 32, 3, 32)));
        for (int i = 0; i < 6; i++) {
            BuildingUtil.rotate(entity);
        }
        assertEquals(new BuildingDesign.Door(new BuildingDesign.Position(EAST, 0), 2, 2), design.getDoors().getFirst());
        assertEquals(Map.of(0, 32, 1, 32, 2, 32, 3, 32), design.getElevators().getFirst().exits());
        BuildingUtil.configure(entity, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(EAST));
        assertEquals(List.of(new BuildingDesign.Position(CubeCoords.ZERO, 1)), design.getEquipmentSpace().get(generator));
        assertEquals(1, generator.getLocation());
        assertEquals(20, BuildingConstruction.bayWeightInHex(entity, CubeCoords.ZERO));
        assertEquals(CubeCoords.ZERO, design.getDoors().getFirst().position().hex());
        assertEquals(CubeCoords.ZERO, design.getElevators().getFirst().hex());
        assertEquals(Map.of(0, 32, 1, 32, 2, 32), design.getElevators().getFirst().exits(), "The old roof follows the new roof");
    }
}
