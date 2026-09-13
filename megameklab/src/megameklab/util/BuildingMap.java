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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import megamek.common.bays.Bay;
import megamek.common.board.CubeCoords;
import megamek.common.equipment.BuildingEquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.BuildingDoors;
import megamek.common.units.IBuilding;

/** Feature identity shared by the editing map and printed structure maps. */
public final class BuildingMap {
    private static final List<Feature> FILL_PRIORITY = List.of(Feature.ELEVATOR, Feature.BAY, Feature.DECK, Feature.TURRET);

    private BuildingMap() { }

    public enum Feature {
        BAY("Bay / quarters", "#f3b0b4", "B"), ELEVATOR("Elevator", "#efcb8d", "E"), DECK("Roof facility", "#b5d1bf", "D"),
        TURRET("Roof turret", "#aca6d2", "T"), DOOR("Door", "#ffffff", ""),
        LARGE_DOOR("Large Door", "#ffffff", ""),
        ELEVATOR_DOOR("Elevator door", "#efcb8d", "");

        public final String label;
        public final String color;
        public final String glyph;

        Feature(String label, String color, String glyph) {
            this.label = label;
            this.color = color;
            this.glyph = glyph;
        }

        public String symbol() {
            return name().toLowerCase(Locale.ROOT).replace('_', '-');
        }
    }

    /** A rendered door on one hexside; elevator access remains separate from structural doors in the design. */
    public record DoorMarker(int facing, Feature feature, BuildingDoors.Geometry geometry) {
        public DoorMarker(int facing, Feature feature) {
            this(facing, feature, null);
        }
    }

    /** Immutable map-feature snapshot for one render or refresh; rebuild after editing the building. */
    public static final class FeatureIndex {
        private final Map<BuildingDesign.Position, List<Feature>> features = new HashMap<>();
        private final Map<BuildingDesign.Position, List<DoorMarker>> doors = new HashMap<>();

        private FeatureIndex(AbstractBuildingEntity building, List<BuildingDesign.Door> mapDoors) {
            Map<CubeCoords, Integer> roofLevels = new HashMap<>();
            for (CubeCoords hex : building.getInternalBuilding().getOriginalCoordsList()) {
                roofLevels.put(hex, building.getInternalBuilding().getHeight(hex) - 1);
            }
            Map<BuildingDesign.Position, EnumSet<Feature>> indexed = new HashMap<>();
            for (Bay transportBay : building.getTransportBays()) {
                for (BuildingDesign.Space space : BuildingConstruction.baySpaces(building, transportBay)) {
                    if (space.tons() > 0) {
                        add(indexed, space.position(), Feature.BAY);
                    }
                }
            }

            for (BuildingDesign.Elevator lift : building.getDesign().getElevators()) {
                if (!lift.exits().isEmpty()) {
                    int firstLevel = Math.max(firstMapLevel(building, lift.hex()), lift.lowerLevel());
                    long endLevel = Math.min(endMapLevel(building, lift.hex()), (long) lift.upperLevel() + 1);
                    for (long level = firstLevel; level < endLevel; level++) {
                        add(indexed, new BuildingDesign.Position(lift.hex(), (int) level), Feature.ELEVATOR);
                    }
                }
            }

            for (Mounted<?> mount : building.getEquipment()) {
                if (mount.isOneShotAmmo() || mount.isWeaponGroup()) {
                    continue;
                }
                boolean deck = mount.getType() instanceof BuildingEquipmentType facility && facility.getFacility().isRoof();
                boolean turret = mount.isSponsonTurretMounted();
                if (!deck && !turret) {
                    continue;
                }
                for (BuildingDesign.Position position : BuildingConstruction.equipmentPositions(building, mount)) {
                    Integer roofLevel = roofLevels.get(position.hex());
                    if (roofLevel == null) {
                        continue;
                    }
                    BuildingDesign.Position roof = new BuildingDesign.Position(position.hex(), roofLevel);
                    if (deck) {
                        add(indexed, roof, Feature.DECK);
                    }
                    if (turret) {
                        add(indexed, roof, Feature.TURRET);
                    }
                }
            }

            var linked = BuildingDoors.geometry(building.getDesign().getDoors(), mapDoors::contains);
            for (BuildingDesign.Door door : mapDoors) {
                long start = door.position().level();
                long end = start + (long) door.height();
                long firstLevel = Math.max(firstMapLevel(building, door.position().hex()), start);
                long endLevel = Math.min(endMapLevel(building, door.position().hex()), end);
                for (long level = firstLevel; level < endLevel; level++) {
                    BuildingDesign.Position position = new BuildingDesign.Position(door.position().hex(), (int) level);
                    add(indexed, position, linked.containsKey(door) ? Feature.LARGE_DOOR : Feature.DOOR);
                    doors.computeIfAbsent(position, ignored -> new ArrayList<>()).add(new DoorMarker(door.facing(), Feature.DOOR, linked.get(door)));
                }
            }

            Map<BuildingDesign.Position, Integer> elevatorAccess = new HashMap<>();
            for (BuildingDesign.Elevator lift : building.getDesign().getElevators()) {
                lift.exits().forEach((level, mask) -> {
                    if (level >= firstMapLevel(building, lift.hex()) && level < endMapLevel(building, lift.hex())) {
                        elevatorAccess.merge(new BuildingDesign.Position(lift.hex(), level), mask, (a, b) -> a | b);
                    }
                });
            }
            elevatorAccess.forEach((position, mask) -> {
                for (int facing = 0; facing < 6; facing++) {
                    if ((mask & (1 << facing)) != 0) {
                        add(indexed, position, Feature.ELEVATOR_DOOR);
                        doors.computeIfAbsent(position, ignored -> new ArrayList<>())
                              .add(new DoorMarker(facing, Feature.ELEVATOR_DOOR));
                    }
                }
            });

            indexed.forEach((position, values) -> features.put(position, List.copyOf(values)));
            doors.replaceAll((position, values) -> List.copyOf(values));
        }

        private static void add(Map<BuildingDesign.Position, EnumSet<Feature>> indexed,
              BuildingDesign.Position position, Feature feature) {
            indexed.computeIfAbsent(position, ignored -> EnumSet.noneOf(Feature.class)).add(feature);
        }

        private static int firstMapLevel(AbstractBuildingEntity building, CubeCoords hex) {
            return building.getBldgClass() == IBuilding.BRIDGE ? building.getDesign().bridgeDeck(hex) : 0;
        }

        private static long endMapLevel(AbstractBuildingEntity building, CubeCoords hex) {
            return building.getBldgClass() == IBuilding.BRIDGE ? (long) building.getDesign().bridgeDeck(hex) + 1
                  : building.getInternalBuilding().getHeight(hex);
        }

        public List<Feature> features(CubeCoords hex, int level) {
            return features.getOrDefault(new BuildingDesign.Position(hex, level), List.of());
        }

        public List<DoorMarker> doors(CubeCoords hex, int level) {
            return doors.getOrDefault(new BuildingDesign.Position(hex, level), List.of());
        }
    }

    public static FeatureIndex featureIndex(AbstractBuildingEntity building, List<BuildingDesign.Door> mapDoors) {
        return new FeatureIndex(building, mapDoors);
    }

    public static Feature fill(List<Feature> features) {
        for (Feature feature : FILL_PRIORITY) {
            if (features.contains(feature)) {
                return feature;
            }
        }
        return null;
    }

    /** Center the triangle on its hexside; affine projection preserves that alignment. */
    public static double[][] doorPoints(double[] a, double[] b) {
        double dx = (a[0] + b[0]) / 2, dy = (a[1] + b[1]) / 2;
        return new double[][] { { dx * 1.3, dy * 1.3 },
            { dx * .85 - (b[0] - a[0]) * .18, dy * .85 - (b[1] - a[1]) * .18 },
            { dx * .85 + (b[0] - a[0]) * .18, dy * .85 + (b[1] - a[1]) * .18 } };
    }
}
