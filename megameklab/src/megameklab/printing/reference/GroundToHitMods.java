/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.printing.reference;

import megamek.common.*;
import megameklab.printing.PrintEntity;
import megameklab.printing.PrintRecordSheet;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import org.apache.batik.util.SVGConstants;

/**
 * To-Hit modifiers for all Meks and vehicles
 */
public class GroundToHitMods extends ReferenceTable {
    private final Entity entity;

    /** The beginning of each range of hex counts for target movement mods */
    private static final int[] TARGET_MOVEMENT_RANGES = {0, 3, 5, 7, 10, 18, 25};

    public GroundToHitMods(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    public GroundToHitMods(PrintRecordSheet sheet, Entity entity) {
        super(sheet, 0.02, 0.08, 0.8);
        this.entity = entity;
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        if (entity instanceof Mech) {
            addMekAttackerMods();
        } else if (entity instanceof Tank) {
            addVeeAttackerMods();
        }
        addTerrainMods();
        addTargetMods();
        if (entity instanceof Mech) {
            addDamageMods();
        }
    }

    private void addMekAttackerMods() {
        addRow(bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("walked"), "+1");
        addRow("", bundle.getString("ran"), "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        if (!(entity instanceof QuadMech)) {
            addRow("", bundle.getString("prone"), "+2");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (((Mech) entity).getCockpitType() == Mech.COCKPIT_PRIMITIVE_INDUSTRIAL) {
            addRow("", bundle.getString("noFireCon"), "+2");
        } else if (!((Mech) entity).hasAdvancedFireControl()) {
            addRow("", bundle.getString("basicFireCon"), "+1");
        }
    }

    private void addVeeAttackerMods() {
        addRow(bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("cruised"), "+1");
        addRow("", bundle.getString("flanked"), "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (entity.isSupportVehicle() && !entity.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            if (entity.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
                addRow("", bundle.getString("basicFireCon"), "+1");
            } else {
                addRow("", bundle.getString("noFireCon"), "+2");
            }
        }
    }

    private void addTerrainMods() {
        addRow(bundle.getString("terrain"), "", "");
        String hexName;
        if (CConfig.scaleUnits().equals(RSScale.HEXES)) {
            hexName = "hex";
        } else {
            hexName = CConfig.getIntParam(CConfig.RS_SCALE_FACTOR)
                    + CConfig.scaleUnits().abbreviation;
        }
        addRow("", bundle.getString("lightWoods"), "+1/" + hexName);
        addRow("", bundle.getString("heavyWoods"), "+2/" + hexName);
        addRow("", bundle.getString("partialCover"), "+1");
    }

    private void addTargetMods() {
        addRow(bundle.getString("target"), "", "");
        if (CConfig.scaleUnits().equals(RSScale.HEXES)) {
            addRow("", bundle.getString("proneAdjacent"), "-2");
        } else {
            addRow("", bundle.getString("proneBasesToBase"), "-2");
        }
        addRow("", bundle.getString("proneOther"), "+1");
        addRow("", bundle.getString("immobile"), "-4");
        addRow("", bundle.getString("skidding"), "-2");

        final int scale = CConfig.getIntParam(CConfig.RS_SCALE_FACTOR);
        final String units = CConfig.scaleUnits().shortName();
        for (int i = 0; i < TARGET_MOVEMENT_RANGES.length; i++) {
            if (i < TARGET_MOVEMENT_RANGES.length - 1) {
                addRow("", String.format(bundle.getString("movedRange"),
                        TARGET_MOVEMENT_RANGES[i] * scale, TARGET_MOVEMENT_RANGES[i + 1] * scale - 1,
                        units), String.format("%+d", i));
            } else {
                addRow("", String.format(bundle.getString("movedFinal"),
                        TARGET_MOVEMENT_RANGES[i] * scale, units), String.format("%+d", i));
            }
        }
        addRow("", bundle.getString("jumped"), "+1");
        if (!(entity instanceof Infantry)) {
            addRow("", bundle.getString("baTarget"), "+1");
        }
        if (entity instanceof BattleArmor) {
            addRow("", bundle.getString("secondaryTarget"), "+1");
        } else if (!(entity instanceof Infantry)) {
            addRow("", bundle.getString("secondaryTargetFront"), "+1");
            addRow("", bundle.getString("secondaryTargetSideRear"), "+2");
        }
        addRow("", bundle.getString("largeTarget"), "-1");
    }

    private void addDamageMods() {
        addRow(bundle.getString("damage"), "", "");
        addRow("", bundle.getString("sensorHit"), "+2");
        addRow("", bundle.getString("shoulderHit"), "+4");
        addRow("", bundle.getString("armActuator"), "+1");
    }
}
