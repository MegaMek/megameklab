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

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.MiscType;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.units.Mek;
import megamek.common.units.QuadMek;
import megamek.common.units.Tank;
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
    private static final int[] TARGET_MOVEMENT_RANGES = { 0, 3, 5, 7, 10, 18, 25 };

    public GroundToHitMods(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    public GroundToHitMods(PrintRecordSheet sheet, Entity entity) {
        super(sheet, 0.02, 0.08, 0.8);
        this.entity = entity;
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        if (entity instanceof Mek) {
            addMekAttackerMods();
        } else if (entity instanceof Tank) {
            addVeeAttackerMods();
        }
        addTerrainMods();
        addTargetMods();
        if (entity instanceof Mek) {
            addDamageMods();
        }
    }

    private void addMekAttackerMods() {
        addRow(SECTION_HEADER + bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("walked"), "+1");
        addRow("", bundle.getString("ran"), "+2");
        if (entity.getOriginalJumpMP() > 0 ||
              ((entity instanceof Mek mek) && (mek.getOriginalMechanicalJumpBoosterMP() > 0))) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        if (!(entity instanceof QuadMek)) {
            addRow("", bundle.getString("prone"), "+2");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (((Mek) entity).getCockpitType() == Mek.COCKPIT_PRIMITIVE_INDUSTRIAL) {
            addRow("", bundle.getString("noFireCon"), "+2");
        } else if (!((Mek) entity).hasAdvancedFireControl()) {
            addRow("", bundle.getString("basicFireCon"), "+1");
        }
    }

    private void addVeeAttackerMods() {
        addRow(SECTION_HEADER + bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("cruised"), "+1");
        addRow("", bundle.getString("flanked"), "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (entity.isSupportVehicle() && !entity.hasWorkingMisc(MiscType.F_ADVANCED_FIRE_CONTROL)) {
            if (entity.hasWorkingMisc(MiscType.F_BASIC_FIRE_CONTROL)) {
                addRow("", bundle.getString("basicFireCon"), "+1");
            } else {
                addRow("", bundle.getString("noFireCon"), "+2");
            }
        }
    }

    private void addTerrainMods() {
        addRow(SECTION_HEADER + bundle.getString("terrain"), "", "");
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
        addRow(SECTION_HEADER + bundle.getString("target"), "", "");
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
        addRow(SECTION_HEADER + bundle.getString("damage"), "", "");
        addRow("", bundle.getString("sensorHit"), "+2");
        addRow("", bundle.getString("shoulderHit"), "+4");
        addRow("", bundle.getString("armActuator"), "+1");
    }
}
