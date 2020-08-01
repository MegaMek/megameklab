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
package megameklab.com.printing;

import megamek.common.*;
import megameklab.com.MegaMekLab;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

/**
 * Lays out a record sheet block for a single protomech
 */
public class PrintProtomech extends PrintEntity {

    private final Protomech proto;
    private final int unitIndex;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param proto        The protomech to print
     * @param startPage    The print job page number for this sheet
     * @param unitIndex    The index of this unit on the page
     * @param options      Overrides the global options for which elements are printed
     */
    public PrintProtomech(Protomech proto, int startPage, int unitIndex, RecordSheetOptions options) {
        super(startPage, options);
        this.proto = proto;
        this.unitIndex = unitIndex;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param proto        The protomech to print
     * @param startPage    The print job page number for this sheet
     * @param unitIndex    The index of this unit on the page
     */
    public PrintProtomech(Protomech proto, int startPage, int unitIndex) {
        this(proto, startPage, unitIndex, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (proto.isQuad()) {
            return "protomech_quad.svg";
        } else if (proto.isGlider()) {
            return "protomech_glider.svg";
        } else {
            return "protomech_biped.svg";
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
        setTextField(PROTOMECH_INDEX, "PROTOMECH " + (unitIndex + 1));
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
     * Checks whether the unit name is too large to fit into the primary field without kerning.
     * If so, looks for a break point and puts the remainder on a second line.
     */
    private void splitName() {
        Element element = getSVGDocument().getElementById(TYPE);
        if (null != element) {
            String fieldWidth = parseStyle(element, MML_FIELD_WIDTH);
            if (null != fieldWidth) {
                try {
                    double width = Double.parseDouble(fieldWidth);
                    // Clear any kerning that has already been applied so we can measure the full text length
                    element.removeAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE);
                    element.removeAttribute(SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
                    build();
                    double textWidth = SVGLocatableSupport.getBBox(element).getWidth();
                    if (textWidth > width) {
                        String name = element.getTextContent();
                        // The goal is a 2:1 split, but we start back a little to avoid just missing a break point
                        int pos = name.indexOf(" ", (int) (name.length() * 0.6));
                        if (pos < 0) {
                            // If we don't find a break point, give up and assign the text again to reset the kerning
                            setTextField(TYPE, name);
                        } else {
                            setTextField(TYPE, name.substring(0, pos));
                            setTextField(TYPE2, name.substring(pos + 1));
                        }
                    }
                } catch (NumberFormatException ex) {
                    MegaMekLab.getLogger().warning(getClass(),
                            "setTextField(String, String, boolean)",
                            "Could not parse fieldWidth: " + fieldWidth);
                }
            }
        }
    }

    private void printTorsoCritChart() {
        int roll = 1;
        for (int i = 0; i < 6; i++) {
            Mounted weapon = proto.getTorsoWeapon(Protomech.SYSTEM_TORSO_WEAPON_A + i);
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
    protected void drawArmor() {
        super.drawArmor();
        String armorName = EquipmentType.getArmorTypeName(proto.getArmorType(Protomech.LOC_TORSO),
                TechConstants.isClan(proto.getArmorTechLevel(Protomech.LOC_TORSO)));
        EquipmentType armor = EquipmentType.get(armorName);
        if (armor != null) {
            setTextField(ARMOR_TYPE, armor.getShortName(), true);
        }
    }

    @Override
    String structurePipFill() {
        return FILL_SHADOW;
    }
}
