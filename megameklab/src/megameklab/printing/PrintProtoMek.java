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
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGTextElement;

/**
 * Lays out a record sheet block for a single ProtoMek
 */
public class PrintProtoMek extends PrintEntity {
    // Keep movement labels clear of the Pilot Data border on Letter sheets.
    private static final float STATS_SPACING_SCALE = 0.95f;
    private static final double HEAD_PIP_SCALE = 0.85;

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
        layoutNameAndStats();
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

    private void layoutNameAndStats() {
        if (!(getElementById(TYPE) instanceof SVGTextElement firstLine)
              || !(getElementById(TYPE2) instanceof SVGTextElement secondLine)) {
            return;
        }

        boolean wrapped = splitName(firstLine, secondLine);
        float secondLineY = secondLine.getY().getBaseVal().getItem(0).getValue();
        float statsY = wrapped ? secondLineY + (secondLineY - firstLine.getY().getBaseVal().getItem(0).getValue())
              : secondLineY;
        // The text siblings after the second name line are the stat labels and values.
        for (Node row = secondLine.getNextSibling(); row != null; row = row.getNextSibling()) {
            if (row instanceof SVGTextElement text) {
                var baseline = text.getY().getBaseVal().getItem(0);
                baseline.setValue(statsY + (baseline.getValue() - secondLineY) * STATS_SPACING_SCALE);
            }
        }
    }

    /**
     * Wraps the unit name if it needed compression to fit on one line and has a word break.
     *
     * @return whether the name occupies two lines
     */
    private boolean splitName(SVGTextElement firstLine, SVGTextElement secondLine) {
        if (!firstLine.hasAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE)) {
            return false;
        }

        String name = firstLine.getTextContent().strip();
        int preferredBreak = (int) (name.length() * 0.6);
        int split = name.indexOf(' ', preferredBreak);
        if (split < 0) {
            split = name.lastIndexOf(' ', preferredBreak);
        }
        if (split < 0) {
            return false;
        }

        firstLine.removeAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE);
        firstLine.removeAttribute(SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE);
        secondLine.setAttribute(SVGConstants.SVG_X_ATTRIBUTE, firstLine.getAttribute(SVGConstants.SVG_X_ATTRIBUTE));
        secondLine.setAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE,
              firstLine.getAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE));
        setTextField(TYPE, name.substring(0, split).stripTrailing());
        setTextField(TYPE2, name.substring(split + 1).stripLeading());
        return true;
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
    PipType structurePipType() {
        return PipType.SQUARE;
    }

    @Override
    protected Element createPip(double x, double y, double radius, double strokeWidth, PipType type, String fill,
          String className, String loc, boolean rear) {
        if (proto.getLocationAbbr(ProtoMek.LOC_HEAD).equals(loc)) {
            // Shrink the pip without changing its center or the layout's row spacing.
            double scaledRadius = radius * HEAD_PIP_SCALE;
            x += radius - scaledRadius;
            y += radius - scaledRadius;
            radius = scaledRadius;
        }
        return super.createPip(x, y, radius, strokeWidth, type, fill, className, loc, rear);
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
