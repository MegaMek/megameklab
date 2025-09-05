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
package megameklab.printing;

import megamek.common.TechConstants;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megamek.logging.MMLogger;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

/**
 * Lays out a record sheet block for a single ProtoMek
 */
public class PrintProtoMek extends PrintEntity {
    private static final MMLogger logger = MMLogger.create(PrintProtoMek.class);

    private final ProtoMek proto;
    private final int unitIndex;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param proto     The protoMek to print
     * @param startPage The print job page number for this sheet
     * @param unitIndex The index of this unit on the page
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintProtoMek(ProtoMek proto, int startPage, int unitIndex, RecordSheetOptions options) {
        super(startPage, options);
        this.proto = proto;
        this.unitIndex = unitIndex;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (proto.isQuad()) {
            return "protomek_quad.svg";
        } else if (proto.isGlider()) {
            return "protomek_glider.svg";
        } else {
            return "protomek_biped.svg";
        }
    }

    @Override
    public Entity getEntity() {
        return proto;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(PROTOMEK_INDEX, "PROTOMEK " + (unitIndex + 1));
        splitName();
        if (proto.isGlider()) {
            setTextField(MP_GROUND, formatMovement(1));
        }
        if (getEntity().hasUMU()) {
            setTextField(LBL_JUMP, "Underwater:");
            setTextField(MP_JUMP, formatMovement(getEntity().getAllUMUCount()));
        }
        printTorsoCritChart();
        if (!proto.hasMainGun()) {
            hideElement(MAIN_GUN_ARMOR);
            hideElement(MAIN_GUN_SHADOW);
            hideElement(MAIN_GUN_TEXT);
        }
    }

    /**
     * Checks whether the unit name is too large to fit into the primary field without kerning. If so, looks for a break
     * point and puts the remainder on a second line.
     */
    private void splitName() {
        Element element = getSVGDocument().getElementById(TYPE);
        if (null != element) {
            String fieldWidth = parseStyle(element, MML_FIELD_WIDTH);
            if (null != fieldWidth) {
                try {
                    double width = Double.parseDouble(fieldWidth);
                    // Clear any kerning that has already been applied so we can measure the full
                    // text length
                    element.removeAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE);
                    element.removeAttribute(SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
                    build();
                    double textWidth = SVGLocatableSupport.getBBox(element).getWidth();
                    if (textWidth > width) {
                        String name = element.getTextContent();
                        // The goal is a 2:1 split, but we start back a little to avoid just missing a
                        // break point
                        int pos = name.indexOf(" ", (int) (name.length() * 0.6));
                        if (pos < 0) {
                            // If we don't find a break point, give up and assign the text again to reset
                            // the kerning
                            setTextField(TYPE, name);
                        } else {
                            setTextField(TYPE, name.substring(0, pos));
                            setTextField(TYPE2, name.substring(pos + 1));
                        }
                    }
                } catch (NumberFormatException ex) {
                    logger.warn("Could not parse fieldWidth: {}", fieldWidth);
                }
            }
        }
    }

    private void printTorsoCritChart() {
        int roll = 1;
        for (int i = 0; i < 6; i++) {
            Mounted<?> weapon = proto.getTorsoWeapon(ProtoMek.SYSTEM_TORSO_WEAPON_A + i);
            StringBuilder sb = new StringBuilder();
            if (weapon != null) {
                sb.append(roll);
                roll++;
                if (!proto.isQuad()) {
                    sb.append("-").append(roll);
                    roll++;
                }
                sb.append(": ").append(weapon.getType().getShortName());
                setTextField(TORSO_WEAPON + i, sb.toString());
            } else if (i == 0) {
                setTextField(TORSO_WEAPON + i, "No Torso Weapons");
                break;
            } else {
                sb.append(roll);
                if (roll < 6) {
                    sb.append("-6");
                }
                sb.append(": No Effect");
                setTextField(TORSO_WEAPON + i, sb.toString());
                break;
            }
        }
        if (proto.hasWorkingMisc(MiscType.F_MAGNETIC_CLAMP)) {
            hideElement(MAG_CLAMP_NOTE, false);
        }
    }

    @Override
    protected String formatRun() {
        if (proto.getMASC() != null) {
            return formatMovement(1.5 * proto.getWalkMP(), 2 * proto.getWalkMP());
        } else {
            return formatMovement(1.5 * proto.getWalkMP());
        }
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

    @Override
    protected void drawArmor() {
        super.drawArmor();
        String armorName = EquipmentType.getArmorTypeName(proto.getArmorType(ProtoMek.LOC_TORSO),
              TechConstants.isClan(proto.getArmorTechLevel(ProtoMek.LOC_TORSO)));
        EquipmentType armor = EquipmentType.get(armorName);
        if (armor != null) {
            setTextField(ARMOR_TYPE, armor.getShortName(), true);
        }
    }

    @Override
    String structurePipFill() {
        return FILL_SHADOW;
    }

    static final String GUN_HIT = "gun_hit_";
    static final String LEGS_HIT = "legs_hit_";
    static final String TORSO_HIT = "torso_hit_";
    static final String HEAD_HIT = "head_hit_";
    static final String RIGHT_ARM_HIT = "ra_hit_";
    static final String LEFT_ARM_HIT = "la_hit_";

    @Override
    protected void applyCoreComponentsCriticalDamage() {
        if (!options.showDamage()) {return;}
        super.applyCoreComponentsCriticalDamage();
        fillCoreComponentCriticalDamage(GUN_HIT, proto.getCritsHit(ProtoMek.LOC_MAIN_GUN));
        fillCoreComponentCriticalDamage(LEGS_HIT, proto.getCritsHit(ProtoMek.LOC_LEG));
        fillCoreComponentCriticalDamage(TORSO_HIT, proto.getCritsHit(ProtoMek.LOC_TORSO));
        fillCoreComponentCriticalDamage(HEAD_HIT, proto.getCritsHit(ProtoMek.LOC_HEAD));
        fillCoreComponentCriticalDamage(LEFT_ARM_HIT, proto.getCritsHit(ProtoMek.LOC_LEFT_ARM));
        fillCoreComponentCriticalDamage(RIGHT_ARM_HIT, proto.getCritsHit(ProtoMek.LOC_RIGHT_ARM));
    }
}
