/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import static megamek.common.equipment.enums.MiscTypeFlag.*;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.weapons.attacks.LegAttack;
import megamek.common.weapons.attacks.StopSwarmAttack;
import megamek.common.weapons.attacks.SwarmAttack;
import megamek.common.weapons.attacks.SwarmWeaponAttack;
import megamek.common.weapons.infantry.rifle.InfantryRifleAutoRifleWeapon;
import megameklab.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;

import java.util.concurrent.atomic.AtomicInteger;

public final class PrintUtil {

    public static boolean isPrintableEquipment(EquipmentType eq, RecordSheetOptions options) {
        return isPrintableEquipment(eq, false, options);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq     The equipment to test The equipment
     * @param entity The Entity it's mounted on
     *
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, Entity entity, RecordSheetOptions options) {
        if (eq instanceof AmmoType) {
            return ((AmmoType) eq).getAmmoType() == AmmoType.AmmoTypeEnum.COOLANT_POD;
        } else if (entity instanceof BattleArmor) {
            return isPrintableBAEquipment(eq);
        } else {
            return isPrintableEquipment(eq, entity instanceof Mek, options);
        }
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq    The equipment to test The equipment
     * @param isMek Whether the equipment is mounted on a Mek
     *
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, boolean isMek, RecordSheetOptions options) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isFixedLocationSpreadEquipment(eq)
              && !(eq instanceof MiscType) && eq.hasFlag(MiscType.F_TALON)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }
        if (!eq.isHittable() && isMek) {
            return false;
        }

        if ((eq instanceof MiscType)
              && (eq.hasFlag(MiscType.F_CASE)
              || eq.hasFlag(MiscType.F_ARTEMIS)
              || eq.hasFlag(MiscType.F_ARTEMIS_PROTO)
              || eq.hasFlag(MiscType.F_ARTEMIS_V)
              || eq.hasFlag(MiscType.F_APOLLO)
              || eq.hasFlag(MiscType.F_PPC_CAPACITOR)
              || (eq.hasFlag(MiscType.F_MASC) && isMek)
              || eq.hasFlag(MiscType.F_HARJEL)
              || eq.hasFlag(MiscType.F_MASS)
              || eq.hasFlag(MiscType.F_CHASSIS_MODIFICATION)
              || eq.hasFlag(MiscType.F_SPONSON_TURRET)
              || eq.hasFlag(MiscType.F_EXTERNAL_STORES_HARDPOINT)
              || eq.hasFlag(MiscType.F_BASIC_FIRE_CONTROL)
              || eq.hasFlag(MiscType.F_ADVANCED_FIRE_CONTROL)
              || eq.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE)
              || eq.hasFlag(MiscType.F_LASER_INSULATOR))) {
            return false;
        }

        if (eq instanceof MiscType mt && mt.hasFlag(MiscType.F_TALON)) {
            return RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE.equals(options.intrinsicPhysicalAttacks());
        }

        return !UnitUtil.isHeatSink(eq);
    }

    private PrintUtil() {
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq The equipment to test
     *
     * @return True when it should appear on the record sheet
     */
    public static boolean isPrintableBAEquipment(EquipmentType eq) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }


        if ((eq instanceof MiscType)
              && ((eq.hasFlag(F_AP_MOUNT) && !eq.hasFlag(F_BA_MANIPULATOR))
              || eq.hasAnyFlag(
              F_FIRE_RESISTANT,
              F_ARTEMIS,
              F_ARTEMIS_V,
              F_APOLLO,
              F_HARJEL,
              F_MASS,
              F_DETACHABLE_WEAPON_PACK,
              F_MODULAR_WEAPON_MOUNT,
              F_JUMP_BOOSTER
        ))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        return (!(eq instanceof LegAttack)) && (!(eq instanceof SwarmAttack))
              && (!(eq instanceof StopSwarmAttack))
              && (!(eq instanceof InfantryRifleAutoRifleWeapon))
              && (!(eq instanceof SwarmWeaponAttack));
    }

    static Element createCapitalPip(double pipWidth, double pipHeight, String fillColor,
          double currX, double currY, boolean stroke, String className, String location, Document svgDocument) {
        Element box = svgDocument.createElementNS(PrintRecordSheet.svgNS, SVGConstants.SVG_RECT_TAG);
        box.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(currX));
        box.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(currY));
        box.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pipWidth));
        box.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pipHeight));
        if (stroke) {
            String classAttr = "pip square";
            if (className != null && !className.isEmpty()) {
                classAttr += " " + className;
            }
            box.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, classAttr);
            if (location != null && !location.isEmpty()) {
                box.setAttributeNS(null, "loc", location);
            }
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, PrintRecordSheet.FILL_BLACK);
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(0.5));
        }
        box.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fillColor);
        return box;
    }

    /**
     * Helper function to print an armor pip block. Can print up to 100 points of armor. Any unprinted armor pips are
     * returned.
     *
     * @param svgDocument The document to draw the pips in
     * @param damageFillColor The color to use for damaged pips
     * @param pipsPerRow How many pips to draw per row
     * @param maxPipRows How many rows of pips are allowed
     * @param startX  The x coordinate of the top left of the block
     * @param startY  The y coordinate of the top left of the block
     * @param parent  The parent node of the bounding rectangle
     * @param numPips The number of pips to print
     * @param shadow  Whether to add a drop shadow
     *
     * @return The Y location of the end of the block
     */
    static int printCapitalPipBlock(double startX, double startY, SVGElement parent, int numPips, double pipWidth,
          double pipHeight, String fillColor, boolean shadow, AtomicInteger remainingDamage,
          String className, String location, Document svgDocument, String damageFillColor, int pipsPerRow,
          int maxPipRows) {

        final double shadowOffsetX = pipWidth * PrintCapitalShip.SHADOW_OFFSET;
        final double shadowOffsetY = pipHeight * PrintCapitalShip.SHADOW_OFFSET;
        double currX, currY;
        currY = startY;
        for (int row = 0; row < maxPipRows; row++) {
            int numRowPips = Math.min(numPips, pipsPerRow);
            // Adjust row start if it's not a complete row
            currX = startX + ((((pipsPerRow - numRowPips) / 2f) * pipWidth) + 0.5);
            for (int col = 0; col < numRowPips; col++) {
                boolean isDamaged = (remainingDamage.decrementAndGet() >= 0);
                if (shadow) {
                    parent.appendChild(createCapitalPip(pipWidth, pipHeight, PrintRecordSheet.FILL_SHADOW, currX + shadowOffsetX,
                          currY + shadowOffsetY, false, null, null, svgDocument));
                }
                final Element pip = createCapitalPip(pipWidth, pipHeight, isDamaged ?
                            damageFillColor : fillColor, currX,
                      currY, true, className, location,
                      svgDocument);
                parent.appendChild(pip);

                currX += pipWidth;
                numPips--;
                // Check to see if we're done
                if (numPips <= 0) {
                    return 0;
                }
            }
            currY += pipHeight;
        }
        return numPips;
    }
}
