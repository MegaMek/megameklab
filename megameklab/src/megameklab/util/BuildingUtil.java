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

import java.util.List;

import megamek.common.CriticalSlot;
import megamek.common.TechConstants;
import megamek.common.board.Coords;
import megamek.common.board.CubeCoords;
import megamek.common.enums.BuildingType;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.PowerGeneratorType;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.ConstructionUtil;
import megamek.common.units.Entity;
import megamek.common.units.IBuilding;

/** Building construction operations and the shared editor/record-sheet coordinate system. */
public final class BuildingUtil {
    public static final List<String> FACINGS = List.of("N", "NE", "SE", "S", "SW", "NW");
    private BuildingUtil() {
    }

    public static megamek.common.units.BuildingEntity newBuilding() {
        megamek.common.units.BuildingEntity entity = new megamek.common.units.BuildingEntity(BuildingType.MEDIUM, IBuilding.STANDARD);
        entity.setEngine(new Engine(0, Engine.NONE, 0));
        entity.setChassis("New");
        entity.setModel("Building");
        entity.setYear(3145);
        entity.setTechLevel(TechConstants.T_IS_ADVANCED);
        entity.configureConstruction(BuildingType.MEDIUM, IBuilding.STANDARD, 1, 40, 0, List.of(CubeCoords.ZERO));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setArmorTechLevel(entity.getTechLevel());
        return entity;
    }

    public static String roofLevelLabel(AbstractBuildingEntity entity, int level) {
        return level == entity.getInternalBuilding().getBuildingHeight()
              ? "Roof (" + entity.getLevelLabel(level) + ")" : entity.getLevelLabel(level);
    }

    public static megamek.common.units.MobileStructure newMobileStructure() {
        var entity = new megamek.common.units.MobileStructure(BuildingType.MEDIUM, IBuilding.STANDARD);
        entity.setChassis("New");
        entity.setModel("Mobile Structure");
        entity.setYear(3145);
        entity.setTechLevel(TechConstants.T_IS_ADVANCED);
        entity.configureConstruction(BuildingType.MEDIUM, IBuilding.STANDARD, 1, 40, 0,
              List.of(CubeCoords.ZERO, new CubeCoords(1, 0, -1)));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setArmorTechLevel(entity.getTechLevel());
        return entity;
    }

    public static String absoluteHexLabel(CubeCoords hex) {
        return "%d,%d".formatted((int) hex.q(), (int) hex.r());
    }

    public static String facingLabel(int facing) {
        return facing < 0 || facing >= FACINGS.size() ? "?" : FACINGS.get(facing);
    }

    public static int exteriorFacing(AbstractBuildingEntity entity, CubeCoords hex) {
        for (int side = 0; side < 6; side++) {
            if (!entity.getInternalBuilding().getOriginalCoordsList().contains(hex.toOffset().translated(side).toCube())) {
                return side;
            }
        }
        return 0;
    }

    /** A cube translation, rather than an offset translation, keeps odd/even column adjacency intact. */
    public record SheetGrid(int columns, int rows, int shiftQ, int shiftRow) {
        public Coords position(CubeCoords hex) {
            int column = (int) hex.q() + shiftQ;
            return new Coords(column, (int) hex.r() + Math.floorDiv(column, 2) + shiftRow);
        }

        public String label(CubeCoords hex) {
            Coords position = position(hex);
            return "%02d%02d".formatted(position.getX() + 1, position.getY() + 1);
        }
    }

    /** Number the footprint from its top-left bounds at 0101, independently of its authored origin. */
    public static SheetGrid sheetGrid(List<CubeCoords> hexes) {
        int minQ = hexes.stream().mapToInt(c -> (int) c.q()).min().orElse(0);
        int maxQ = hexes.stream().mapToInt(c -> (int) c.q()).max().orElse(0);
        int columns = Math.max(9, maxQ - minQ + 1);
        int shiftQ = -minQ;
        // Translate the cube column before finding rows to preserve staggered-column adjacency.
        int minRow = hexes.stream().mapToInt(c -> (int) c.r() + Math.floorDiv((int) c.q() + shiftQ, 2))
              .min().orElse(0);
        int maxRow = hexes.stream().mapToInt(c -> (int) c.r() + Math.floorDiv((int) c.q() + shiftQ, 2))
              .max().orElse(0);
        return new SheetGrid(columns, Math.max(7, maxRow - minRow + 1), shiftQ, -minRow);
    }

    public static String locationLabel(AbstractBuildingEntity entity, int location) {
        if (location < 0 || location >= entity.locations()) {
            return "Unallocated";
        }
        int height = entity.getInternalBuilding().getBuildingHeight();
        List<CubeCoords> hexes = entity.getInternalBuilding().getOriginalCoordsList();
        CubeCoords hex = hexes.get(location / height);
        int level = entity.getBldgClass() == IBuilding.BRIDGE ? entity.getDesign().bridgeDeck(hex) : location % height;
        return sheetGrid(hexes).label(hex) + "/" + entity.getLevelLabel(level, true);
    }

    public static void assignEquipment(AbstractBuildingEntity entity, Mounted<?> mount, int location) {
        if (location != mount.getLocation()) {
            entity.getDesign().getEquipmentSpace().remove(mount);
        }
        ConstructionUtil.removeCriticalSlots(entity, mount);
        ConstructionUtil.changeMountStatus(entity, mount, location, Entity.LOC_NONE, false);
        if (location != Entity.LOC_NONE) {
            entity.addCritical(location, new CriticalSlot(mount));
        }
    }

    public static void configure(AbstractBuildingEntity entity, BuildingType type, int buildingClass, int levels, int cf,
          int armor, List<CubeCoords> hexes) {
        entity.configureConstruction(type, buildingClass, levels, cf, armor, hexes);
        entity.getEquipment().stream().filter(m -> m.getLocation() == Entity.LOC_NONE && !m.isOneShotAmmo()).toList()
              .forEach(m -> ConstructionUtil.removeMounted(entity, m));
        entity.getDesign().removeDeletedComponents(entity);
    }

    public record TopologyDoorChanges(int count, String description, Runnable remove) { }

    /** Detect only openings made invalid by adding/removing hexes, before construction rebases coordinates. */
    public static TopologyDoorChanges topologyDoorChanges(AbstractBuildingEntity entity, List<CubeCoords> hexes) {
        var before = entity.getInternalBuilding().getOriginalCoordsList();
        java.util.function.BiPredicate<BuildingDesign.Position, Integer> obstructed = (position, facing) -> {
            var neighbor = position.hex().toOffset().translated(facing).toCube();
            return hexes.contains(position.hex()) && !before.contains(neighbor) && hexes.contains(neighbor);
        };
        var doors = entity.getDesign().getDoors().stream().filter(door -> obstructed.test(door.position(), door.facing())).toList();
        var bayDoors = entity.getDesign().getBayDoors().stream().filter(door -> obstructed.test(door.position(), door.facing())).toList();
        var elevators = new java.util.HashMap<BuildingDesign.Elevator, BuildingDesign.Elevator>();
        int elevatorDoors = 0;
        for (var lift : entity.getDesign().getElevators()) {
            if (!hexes.contains(lift.hex())) {
                continue;
            }
            int removed = 0;
            for (int side = 0; side < 6; side++) {
                var neighbor = lift.hex().toOffset().translated(side).toCube();
                if (before.contains(neighbor) && !hexes.contains(neighbor)) {
                    removed |= 1 << side;
                }
            }
            var exits = new java.util.HashMap<Integer, Integer>();
            for (var stop : lift.exits().entrySet()) {
                elevatorDoors += Integer.bitCount(stop.getValue() & removed);
                exits.put(stop.getKey(), stop.getValue() & ~removed);
            }
            elevators.put(lift, new BuildingDesign.Elevator(lift.hex(), lift.capacity(), exits));
        }
        return new TopologyDoorChanges(doors.size() + bayDoors.size() + elevatorDoors,
              "%d exterior door(s) would face an occupied hex; %d elevator access door(s) would face a removed hex."
                    .formatted(doors.size() + bayDoors.size(), elevatorDoors), () -> {
                        entity.getDesign().getDoors().removeAll(doors);
                        entity.getDesign().getBayDoors().removeAll(bayDoors);
                        entity.getDesign().getElevators().replaceAll(lift -> elevators.getOrDefault(lift, lift));
                    });
    }

    public static void setHexHeight(AbstractBuildingEntity entity, CubeCoords hex, int height) {
        if (!(entity instanceof megamek.common.units.MobileStructure) || height < 1
              || height > entity.getInternalBuilding().getBuildingHeight()) {
            throw new IllegalArgumentException("Only a Mobile Structure can have individual hex heights");
        }
        entity.getInternalBuilding().setHeight(height, hex);
        int roof = entity.getInternalBuilding().getBuildingHeight();
        java.util.function.Predicate<megamek.common.units.BuildingDesign.Position> removed = position ->
              position.hex().equals(hex) && position.level() >= height && position.level() < roof;
        var hexEquipment = java.util.Set.copyOf(entity.getEquipmentInHex(hex));
        entity.getEquipment().stream().filter(mount -> {
            return hexEquipment.contains(mount) && entity.getLocationLevel(mount.getLocation()) >= height
                  && entity.getLocationLevel(mount.getLocation()) < roof
                  || entity.getDesign().getEquipmentSpace().getOrDefault(mount, List.of()).stream().anyMatch(removed);
        }).toList().forEach(mount -> ConstructionUtil.removeMounted(entity, mount));
        entity.getDesign().getBaySpace().entrySet().stream()
              .filter(entry -> entry.getValue().stream().anyMatch(space -> removed.test(space.position())))
              .map(java.util.Map.Entry::getKey).toList().forEach(entity::removeTransporter);
        entity.getDesign().getDoors().removeIf(door -> door.position().hex().equals(hex)
              && door.position().level() + door.height() > height);
        entity.getDesign().remap(position -> removed.test(position) ? null : position, facing -> facing);
        entity.getDesign().removeDeletedComponents(entity);
    }

    /** Rotate the footprint and weapon facings together, keeping the equipment on the same physical floor. */
    public static void rotate(AbstractBuildingEntity entity) {
        transform(entity, c -> new CubeCoords(-(int) c.r(), -(int) c.s(), -(int) c.q()), facing -> (facing + 1) % 6);
    }

    public static void transform(AbstractBuildingEntity entity, java.util.function.UnaryOperator<CubeCoords> transform,
          java.util.function.IntUnaryOperator facingTransform) {
        var building = entity.getInternalBuilding();
        entity.configureConstruction(entity.getBuildingType(), entity.getBldgClass(), building.getBuildingHeight(),
              entity.getOInternal(0), entity.getOArmor(0), building.getCoordsList().stream().map(transform).toList(),
              transform, facingTransform);
    }

    public static double equipmentWeight(AbstractBuildingEntity entity) {
        return UnitUtil.getEntityVerifier(entity).calculateWeight();
    }

    public static String powerDescription(AbstractBuildingEntity entity) {
        if (entity instanceof megamek.common.units.MobileStructure mobile) {
            return mobile.getPowerSystem().toString();
        }
        if (BuildingConstruction.hasNoInterior(entity) || BuildingConstruction.usesHexsides(entity)) {
            return "NA";
        }
        if (BuildingConstruction.isLiquidStorageOnly(entity)) {
            return "Not required (liquid storage)";
        }
        if (entity.getEquipment().stream().noneMatch(m -> m.getType() instanceof PowerGeneratorType)) {
            return "External supply";
        }
        return entity.hasPower() ? "Available" : "Insufficient";
    }

    public static List<String> constructionIssues(AbstractBuildingEntity entity) {
        StringBuffer issues = new StringBuffer();
        UnitUtil.getEntityVerifier(entity).correctEntity(issues, entity.getTechLevel());
        return issues.toString().lines().filter(line -> !line.isBlank()).toList();
    }
}
