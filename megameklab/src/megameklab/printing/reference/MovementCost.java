/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing.reference;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.StringJoiner;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.MiscType;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.Infantry;
import megamek.common.units.LandAirMek;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.Tank;
import megameklab.printing.PrintEntity;
import megameklab.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;

/**
 * General table for movement costs
 */
public class MovementCost extends ReferenceTable {
    private final Entity entity;

    private final static double[] HEADER_OFFSETS = { 0.02, 0.08, 0.14, 0.8 };
    private final static double[] HEADER_OFFSETS_EXTRA = { 0.02, 0.08, 0.14, 0.6, 0.9 };

    public MovementCost(PrintRecordSheet sheet, Entity entity) {
        super(sheet, 0.02, 0.08, 0.14, 0.8);
        this.entity = entity;
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(2, SVGConstants.SVG_START_VALUE);
        if (entity.getMovementMode().equals(EntityMovementMode.NAVAL)
              || entity.getMovementMode().equals(EntityMovementMode.HYDROFOIL)
              || entity.getMovementMode().equals(EntityMovementMode.SUBMARINE)) {
            addNavalMods();
        } else if (entity.getMovementMode().equals(EntityMovementMode.VTOL)
              || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addAerialMods();
        } else if (entity.getMovementMode().equals(EntityMovementMode.RAIL)
              || entity.getMovementMode().equals(EntityMovementMode.MAGLEV)) {
            addRailMods();
            addNote(bundle.getString("rail.note.1"));
            addNote(bundle.getString("rail.note.2"));
        } else {
            addGroundMods();
        }
        if (entity.hasMisc(MiscType.F_LIMITED_AMPHIBIOUS)) {
            addNote(bundle.getString("limitedAmphibious.note.1"));
            addNote(bundle.getString("limitedAmphibious.note.2"));
        }
    }

    public MovementCost(PrintRecordSheet sheet, List<Entity> entities) {
        super(sheet, entities.stream().anyMatch(en ->
              EnumSet.of(EntityMovementMode.HOVER,
                    EntityMovementMode.WHEELED,
                    EntityMovementMode.TRACKED).contains(en.getMovementMode())) ?
              HEADER_OFFSETS_EXTRA : HEADER_OFFSETS);
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(2, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(4, SVGConstants.SVG_END_VALUE);
        this.entity = entities.get(0);
        addSmallUnitMods(entities);
    }

    public MovementCost(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    private void addGroundMods() {
        addRow(SECTION_HEADER + bundle.getString("costToEnterAnyHex"), "", "", "1");
        addRow(SECTION_HEADER + bundle.getString("terrainCost"), "", "", "");
        if (entity.isSupportVehicle() && !entity.hasMisc(MiscType.F_OFF_ROAD)) {
            addRow("", bundle.getString("clear"), "", "+1");
        } else {
            addRow("", bundle.getString("clear"), "", "+0");
        }
        addRow("", bundle.getString("paved"), "", "+0");
        addRow("", bundle.getString("road"), "", "+0");
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)) {
            addRow("", bundle.getString("rough"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("rough"), "", "+1");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)
              || entity.getMovementMode().equals(EntityMovementMode.HOVER)
              || entity.getMovementMode().equals(EntityMovementMode.VTOL)
              || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("lightWoods"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("lightWoods"), "", "+1");
        }
        if (entity instanceof Tank) {
            addRow("", bundle.getString("heavyWoods"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("heavyWoods"), "", "+2");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.HOVER)
              || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("water"), "", "+0");
        } else if (entity.hasMisc(MiscType.F_AMPHIBIOUS) || entity.hasMisc(MiscType.F_LIMITED_AMPHIBIOUS)) {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "+0");
            addRow("", "", bundle.getString("depth1plus"), "+1");
        } else if (entity instanceof Tank) {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "+0");
            addRow("", "", bundle.getString("depth1plus"), bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "+0");
            addRow("", "", bundle.getString("depth1"), "+1");
            addRow("", "", bundle.getString("depth2plus"), "+3");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)) {
            addRow("", bundle.getString("rubble"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("rubble"), "", "+1");
        }
        if (!entity.getMovementMode().equals(EntityMovementMode.VTOL)
              && !entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("lightBuilding"), "", "+1");
            addRow("", bundle.getString("mediumBuilding"), "", "+2");
            addRow("", bundle.getString("heavyBuilding"), "", "+3");
            addRow("", bundle.getString("hardenedBuilding"), "", "+4");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.VTOL)) {
            addRow(SECTION_HEADER + bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1perLevel"));
        } else if ((entity instanceof Tank) || (entity instanceof ProtoMek)) {
            addRow(SECTION_HEADER + bundle.getString("levelChangeUpOrDown"), "", "", "");
            addRow("", bundle.getString("1level"), "",
                  (entity instanceof ProtoMek) ? "+1" : "+2");
            addRow("", bundle.getString("2plusLevels"), "", bundle.getString("prohibited"));
        } else {
            addRow(SECTION_HEADER + bundle.getString("levelChangeUpOrDown"), "", "", "");
            addRow("", bundle.getString("1level"), "", "+1");
            addRow("", bundle.getString("2levels"), "", "+2");
            addRow("", bundle.getString("3plusLevels"), "", bundle.getString("prohibited"));
        }
        addRow(SECTION_HEADER + bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexSide"));
        if (entity instanceof Mek) {
            addRow("", bundle.getString("dropToGround"), "", "1");
            addRow("", bundle.getString("standUp"), "", bundle.getString("2perAttempt"));
            if (entity instanceof LandAirMek) {
                addRow("", bundle.getString("liftOffHover"), "", "5");
            }
        } else if ((entity instanceof ProtoMek) && ((ProtoMek) entity).isGlider()) {
            addRow("", bundle.getString("liftOffHover"), "", "3");
        } else if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("liftOff"), "", "5");
            addRow("", bundle.getString("maintainAltitude"), "", "+2");
        }
    }

    private void addNavalMods() {
        addRow(SECTION_HEADER + bundle.getString("water"), "", "", "");
        addRow("", bundle.getString("depth0"), "", bundle.getString("prohibited"));
        addRow("", bundle.getString("depth1plus"), "", "1");
        if (entity.getMovementMode().equals(EntityMovementMode.SUBMARINE)) {
            addRow(SECTION_HEADER + bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1perLevel"));
        }
        addRow(SECTION_HEADER + bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexSide"));
    }

    private void addAerialMods() {
        addRow(SECTION_HEADER + bundle.getString("costToEnterAirborne"), "", "", "1");
        addRow("", bundle.getString("woods"), "", bundle.getString("prohibited"));
        addRow("", "", bundle.getString("exceptAlongRoad"), "");
        addRow("", bundle.getString("building"), "", bundle.getString("prohibited"));
        if (entity.getMovementMode().equals(EntityMovementMode.VTOL)) {
            addRow(SECTION_HEADER + bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1perLevel"));
        } else if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow(SECTION_HEADER + bundle.getString("levelChange"), "", "", "");
            addRow("", bundle.getString("up1level"), "", "+0");
            addRow("", bundle.getString("up2plusLevels"), "", bundle.getString("prohibited"));
            addRow("", bundle.getString("down1plusLevels"), "", "+0");
        }
        addRow(SECTION_HEADER + bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexSide"));
        if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("liftOff"), "", "5");
            addRow("", bundle.getString("maintainAltitude"), "", "+2");
        }
    }

    private void addRailMods() {
        addRow(SECTION_HEADER + bundle.getString("costToEnterAnyHex"), "", "", "1");
        addRow(SECTION_HEADER + bundle.getString("maximumChange"), "", "",
              entity.isSuperHeavy() ? "+/- 1 MP" : "+/- 2 MP");
        if (entity.isTractor()) {
            addRow(SECTION_HEADER + bundle.getString("trailerWeight"), "", "", bundle.getString("cruiseMP"));
            addRow("", "<=" + formatWeightThreshold(0.5), "", String.valueOf(entity.getWalkMP()));
            addRow("", "<=" + formatWeightThreshold(2.0), "",
                  String.valueOf(Math.max(2, entity.getWalkMP() - Math.min(3, entity.getWalkMP() * 2 / 3))));
            addRow("", "<=" + formatWeightThreshold(4.0), "",
                  String.valueOf(Math.max(2, entity.getWalkMP() - entity.getWalkMP() / 2)));
            addRow("", "<=" + formatWeightThreshold(5.0), "",
                  String.valueOf(Math.max(2, entity.getWalkMP() - entity.getWalkMP() / 3)));
            addRow("", ">" + formatWeightThreshold(5.0), "", bundle.getString("prohibited"));
        }
    }

    private String formatWeightThreshold(double multiplier) {
        if (entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            return NumberFormat.getNumberInstance().format((int) (entity.getWeight() * 1000 * multiplier))
                  + " kg";
        } else {
            return NumberFormat.getNumberInstance().format(entity.getWeight() * multiplier) + " tons";
        }
    }

    private void addSmallUnitMods(List<Entity> entities) {
        int umuCount = 0; // This includes motorized and mechanized SCUBA infantry
        int wheeledCount = 0;
        int trackedCount = 0;
        int hoverCount = 0;
        int vtolCount = 0;
        for (Entity en : entities) {
            if (en.hasUMU()) {
                umuCount++;
            }
            if (en.getMovementMode().equals(EntityMovementMode.WHEELED)) {
                wheeledCount++;
            }
            if (en.getMovementMode().equals(EntityMovementMode.TRACKED)) {
                trackedCount++;
            }
            if (en.getMovementMode().equals(EntityMovementMode.HOVER)) {
                hoverCount++;
            }
            if (en.getMovementMode().equals(EntityMovementMode.VTOL)) {
                vtolCount++;
            }
        }
        // This only includes ground mechanized
        int mechanizedCount = wheeledCount + hoverCount + trackedCount;

        addRow(SECTION_HEADER + bundle.getString("move"), "", "", bundle.getString("cost"),
              bundle.getString("prohibited"));
        addRow(SECTION_HEADER + bundle.getString("costToEnterAnyHex"), "", "", "1");
        addRow(SECTION_HEADER + bundle.getString("terrainCost"), "", "", "");
        addRow("", bundle.getString("clear"), "", "+0");
        addRow("", bundle.getString("paved"), "", "+0");
        addRow("", bundle.getString("road"), "", "+0");
        if (wheeledCount > 0) {
            addRow("", bundle.getString("rough"), "", "+1", bundle.getString("wheeled"));
        } else {
            addRow("", bundle.getString("rough"), "", "+1");
        }
        if (wheeledCount + hoverCount > 0) {
            StringJoiner sj = new StringJoiner("\n");
            if (wheeledCount > 0) {
                sj.add(bundle.getString("wheeled"));
            }
            if (hoverCount > 0) {
                sj.add(bundle.getString("hover"));
            }
            addRow("", bundle.getString("lightWoods"), "", "+0", sj.toString());
        } else if (mechanizedCount < entities.size()) {
            addRow("", bundle.getString("lightWoods"), "",
                  (entity instanceof Infantry) ? "+0" : "+1");
        }
        if (trackedCount > 0) {
            addRow("", "", bundle.getString("mechanized"), "+1");
        }
        if (mechanizedCount > 0) {
            StringJoiner sj = new StringJoiner("\n");
            if (wheeledCount > 0) {
                sj.add(bundle.getString("wheeled"));
            }
            if (hoverCount > 0) {
                sj.add(bundle.getString("hover"));
            }
            if (trackedCount > 0) {
                sj.add(bundle.getString("tracked"));
            }
            addRow("", bundle.getString("heavyWoods"), "", "+1", sj.toString());
        } else {
            addRow("", bundle.getString("heavyWoods"), "",
                  (entity instanceof ProtoMek) ? "+2" : "+1");
        }
        addRow("", bundle.getString("water"), "", "");
        if (hoverCount > 0) {
            addRow("", "", bundle.getString("hoverSurface"), "+0");
        }
        if (umuCount > 0) {
            addRow("", "", bundle.getString("depth1plusUmu"), "+0");
        } else {
            addRow("", "", bundle.getString("depth1plus"), bundle.getString("prohibited"));
        }
        if (wheeledCount > 0) {
            addRow("", bundle.getString("rubble"), "", "+1", bundle.getString("wheeled"));
        } else {
            addRow("", bundle.getString("rubble"), "", "+1");
        }
        if (entity instanceof BattleArmor) {
            addRow("", bundle.getString("building"), "", "+0");
        } else if (entity instanceof Infantry) {
            addRow("", bundle.getString("building"));
            if (mechanizedCount < entities.size()) {
                addRow("", "", bundle.getString("nonmechanized"), "+0");
            }
            if (mechanizedCount > 0) {
                addRow("", "", bundle.getString("mechanized"), "+1");
            }
        } else {
            addRow("", bundle.getString("lightBuilding"), "", "+1");
            addRow("", bundle.getString("mediumBuilding"), "", "+2");
            addRow("", bundle.getString("heavyBuilding"), "", "+3");
            addRow("", bundle.getString("hardenedBuilding"), "", "+4");
        }
        addRow(bundle.getString("levelChangeUpOrDown"));
        if (vtolCount < entities.size()) {
            addRow("", bundle.getString("1level"), "", (entity instanceof ProtoMek) ? "+1" : "+2");
            addRow("", bundle.getString("2plusLevels"), "", bundle.getString("prohibited"));
        }
        if (vtolCount > 0) {
            addRow("", bundle.getString("vtol"), "", bundle.getString("1perLevel"));
        }
        if (umuCount > 0) {
            addRow("", bundle.getString("umu"), "", bundle.getString("1perLevel"));
        }
        if (entity instanceof ProtoMek) {
            addRow(bundle.getString("additionalMovementActions"), "", "", "");
            addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexSide"));
            if (((ProtoMek) entity).isGlider()) {
                addRow("", bundle.getString("liftOffHover"), "", "4");
            }
        }
    }
}
