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
import megamek.common.options.IOption;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.util.Enumeration;
import java.util.StringJoiner;

/**
 * Lays out a record sheet block for a single infantry unit
 */
public class PrintInfantry extends PrintEntity {

    private final Infantry infantry;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param infantry The infantry to print
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed
     */
    public PrintInfantry(Infantry infantry, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.infantry = infantry;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param infantry The infantry to print
     * @param startPage The print job page number for this sheet
     */
    public PrintInfantry(Infantry infantry, int startPage) {
        this(infantry, startPage, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "conventional_infantry_platoon.svg";
    }


    @Override
    public Entity getEntity() {
        return infantry;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();

        if (!infantry.canMakeAntiMekAttacks()) {
            setTextField(PILOTING_SKILL + "0", "N/A");
        }
        writeFieldGuns();

        for (int j = 1; j <= 30; j++) {
            if (j > infantry.getShootingStrength()) {
                hideElement(SOLDIER + j, true);
                hideElement(NO_SOLDIER + j, false);
            } else {
                setTextField(DAMAGE + j, (int)Math.round(infantry.getDamagePerTrooper() * j));
            }
        }
        InfantryWeapon rangeWeapon = infantry.getPrimaryWeapon();
        if (infantry.getSecondaryWeapon() != null && infantry.getSecondaryN() > 1
                && !infantry.getSecondaryWeapon().hasFlag(WeaponType.F_TAG)) {
            rangeWeapon = infantry.getSecondaryWeapon();
        }
        boolean scuba = infantry.getMovementMode() == EntityMovementMode.INF_UMU
                || infantry.getMovementMode() == EntityMovementMode.SUBMARINE;
        hideElement(UW_LABEL, !scuba);
        InfantryWeapon singleSecondary = (infantry.getSecondaryN() == 1)? infantry.getSecondaryWeapon() : null;
        for (int j = 0; j <= 21; j++) {
            setTextField(RANGE_MOD + j, rangeMod(j, rangeWeapon, singleSecondary, false));
            if (scuba) {
                setTextField(UW_RANGE_MOD + j, rangeMod(j, rangeWeapon, singleSecondary, true), true);
            }
        }

        setTextField(TRANSPORT_WT, String.format("%.1f tons", infantry.getWeight()));

        switch(infantry.getMovementMode()) {
            case INF_JUMP:
                setTextField(MP_1, infantry.getJumpMP(false));
                setTextField(MODE_1, "Jump");
                setTextField(MP_2, formatGroundMP(), true);
                setTextField(MODE_2, "Ground", true);
                hideElement(MP_2_LABEL, false);
                hideElement(MODE_2_LABEL, false);
                break;
            case INF_UMU:
                setTextField(MP_1, infantry.getActiveUMUCount());
                if (infantry.getOriginalJumpMP() > 1) {
                    setTextField(MODE_1, "SCUBA (Motorized)");
                } else {
                    setTextField(MODE_1, "SCUBA");
                }
                setTextField(MP_2, formatGroundMP(), true);
                setTextField(MODE_2, "Ground", true);
                hideElement(MP_2_LABEL, false);
                hideElement(MODE_2_LABEL, false);
                break;
            case HOVER:
                setTextField(MP_1, formatGroundMP());
                setTextField(MODE_1, "Mechanized Hover");
                break;
            case TRACKED:
                setTextField(MP_1, formatGroundMP());
                setTextField(MODE_1, "Mechanized Tracked");
                break;
            case WHEELED:
                setTextField(MP_1, formatGroundMP());
                setTextField(MODE_1, "Mechanized Wheeled");
                break;
            case VTOL:
                setTextField(MP_1, infantry.getJumpMP(false));
                if (infantry.hasMicrolite()) {
                    setTextField(MODE_1, "VTOL (Microlite)");
                } else {
                    setTextField(MODE_1, "VTOL (Micro-copter)");
                }
                break;
            case SUBMARINE:
                setTextField(MP_1, infantry.getActiveUMUCount());
                setTextField(MODE_1, "Mechanized SCUBA");
                break;
            case INF_MOTORIZED:
                setTextField(MP_1, infantry.getWalkMP(true, true, false));
                setTextField(MODE_1, "Motorized");
                break;
            case INF_LEG:
            default:
                setTextField(MP_1, formatGroundMP());
                setTextField(MODE_1, "Ground");
                break;
        }

        final String notes = generateNotesText(rangeWeapon);

        Element rect = getSVGDocument().getElementById(NOTES);
        if (rect instanceof SVGRectElement) {
            final double x = ((SVGRectElement) rect).getX().getBaseVal().getValue();
            final double y = ((SVGRectElement) rect).getY().getBaseVal().getValue();
            final double width = ((SVGRectElement) rect).getWidth().getBaseVal().getValue();
            final double height = ((SVGRectElement) rect).getHeight().getBaseVal().getValue();
            float fontSize = FONT_SIZE_MEDIUM;
            // Reduce the font size if necessary to fit the text into the space
            while ((fontSize > 5.0) && (height < (getFontHeight(fontSize) + 1) * getTextLength(notes, fontSize) / width)) {
                fontSize = Math.max(5f, fontSize - 1f);
            }

            addMultilineTextElement((Element) rect.getParentNode(), x, y, width, getFontHeight(fontSize),
                    String.join("; ", notes), fontSize, SVGConstants.SVG_START_VALUE,
                    SVGConstants.SVG_NORMAL_VALUE);
        }
    }

    private String generateNotesText(InfantryWeapon rangeWeapon) {
        StringJoiner sj = new StringJoiner(" ");
        if (infantry.hasSpaceSuit()) {
            sj.add("Can operate in vacuum.");
        }
        if (rangeWeapon.hasFlag(WeaponType.F_INF_BURST)) {
            sj.add("+1D6 damage vs. conventional infantry.");
        }
        if (rangeWeapon.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
            sj.add("Can only damage conventional infantry units.");
        }
        if (infantry.getPrimaryWeapon().hasFlag(WeaponType.F_INFERNO)
                || (infantry.getSecondaryWeapon() != null
                && infantry.getSecondaryWeapon().hasFlag(WeaponType.F_INFERNO))) {
            sj.add("Flame-based weapon.");
        } else {
            for (int i = 0; i < infantry.getPrimaryWeapon().getModesCount(); i++) {
                if (infantry.getPrimaryWeapon().getMode(i).equals("Heat")) {
                    sj.add("Flame-based weapon.");
                    break;
                }
            }
            if (infantry.getSecondaryWeapon() != null) {
                for (int i = 0; i < infantry.getSecondaryWeapon().getModesCount(); i++) {
                    if (infantry.getSecondaryWeapon().getMode(i).equals("Heat")) {
                        sj.add("Flame-based weapon.");
                    }
                }
            }
        }
        if (infantry.getPrimaryWeapon().hasFlag(WeaponType.F_INF_AA)
                || (infantry.getSecondaryWeapon() != null
                && infantry.getSecondaryWeapon().hasFlag(WeaponType.F_INF_AA))) {
            sj.add("May attack airborne targets that attack their hex.");
        }
        if (infantry.hasSpecialization(Infantry.BRIDGE_ENGINEERS)) {
            sj.add("Bridge-building equipment");
        }
        if (infantry.hasSpecialization(Infantry.DEMO_ENGINEERS)) {
            sj.add("Equipped with demolition gear.");
        }
        if (infantry.hasSpecialization(Infantry.FIRE_ENGINEERS)) {
            sj.add("Firefighting equipment.");
        }
        if (infantry.hasSpecialization(Infantry.MINE_ENGINEERS)) {
            sj.add("Minesweeper equipment");
        }
        if (infantry.hasSpecialization(Infantry.TRENCH_ENGINEERS)) {
            sj.add("Trench/Fieldwork equipment");
        }
        if (infantry.hasSpecialization(Infantry.MARINES)) {
            sj.add("No penalties for vacuum or zero-G");
        }
        if (infantry.hasSpecialization(Infantry.MOUNTAIN_TROOPS)) {
            sj.add("Mountain climbing equipment. Unit can traverse 3 levels per hex. Unit is immune to the effects of Thin Atmosphere.");
        }
        if (infantry.hasSpecialization(Infantry.PARAMEDICS)) {
            sj.add("Paramedic equipment.");
        }
        if (infantry.hasSpecialization(Infantry.PARATROOPS)) {
            sj.add("May use Atmospheric Drops rules.");
        }
        if (infantry.hasSpecialization(Infantry.SENSOR_ENGINEERS)) {
            sj.add("Surveillance and communication equipment");
        }
        if (infantry.hasSpecialization(Infantry.TAG_TROOPS)) {
            sj.add("Equipped with TAG (Range 3/6/9)");
        }
        if (infantry.isXCT()) {
            sj.add("Xenoplanetary Condition-Trained");
        }
        if (infantry.hasSneakECM()) {
            sj.add("Invisible to standard/light active probes.");
        }

        StringJoiner enhancements = new StringJoiner(", ");
        for (Enumeration<IOption> e = infantry.getCrew().getOptions().getOptions(); e.hasMoreElements(); ) {
            final IOption option = e.nextElement();
            if (option.booleanValue()) {
                enhancements.add(option.getDisplayableName().replaceAll("\\s+\\(Not Implemented\\)", ""));
            }
        }
        if (enhancements.length() > 0) {
            sj.add("Cybernetically enhanced: " + enhancements.toString());
        }

        if (sj.length() > 0) {
            return sj.toString();
        } else {
            return "None";
        }
    }

    private void writeFieldGuns() {
        int numGuns = 0;
        int numShots = 0;
        WeaponType gun = null;
        for (Mounted m : infantry.getEquipment()) {
            if (m.getLocation() == Infantry.LOC_FIELD_GUNS) {
                if (m.getType() instanceof WeaponType) {
                    gun = (WeaponType)m.getType();
                    numGuns++;
                } else if (m.getType() instanceof AmmoType) {
                    numShots += ((AmmoType)m.getType()).getShots();
                }
            }
        }
        if (gun == null) {
            return;
        }
        hideElement(FIELD_GUN_COLUMNS, false);
        setTextField(FIELD_GUN_QTY, numGuns);
        setTextField(FIELD_GUN_TYPE, gun.getName());
        /* We don't use StringUnits.getEquipmentInfo() to format the damage
         * string because gauss explosion flags do not apply, and switchable
         * only applies for non-LBX.
         */
        if (gun instanceof ArtilleryWeapon) {
            setTextField(FIELD_GUN_DMG, gun.getRackSize() + " [AE,S,F]");
        } else {
            StringBuilder sb = new StringBuilder(Integer.toString(gun.getDamage()));
            switch (gun.getAmmoType()) {
                case AmmoType.T_AC_ULTRA:
                case AmmoType.T_AC_ULTRA_THB:
                    sb.append("/Sht, R2 [DB,R/S/C]");
                    break;
                case AmmoType.T_AC_ROTARY:
                    sb.append("/Sht, R6 [DB,R/S/C]");
                    break;
                case AmmoType.T_AC:
                case AmmoType.T_AC_PRIMITIVE:
                case AmmoType.T_LAC:
                    sb.append(" [DB,C/S/F]");
                    break;
                case AmmoType.T_AC_LBX:
                case AmmoType.T_AC_LBX_THB:
                    sb.append(" [DB,C/F]");
                    break;
                default:
                    sb.append(" [DB]");
            }
            setTextField(FIELD_GUN_DMG, sb.toString());
        }
        if (gun.getMinimumRange() > 0) {
            setTextField(FIELD_GUN_MIN_RANGE, gun.getMinimumRange());
        } else {
            setTextField(FIELD_GUN_MIN_RANGE, "—");
        }
        setTextField(FIELD_GUN_SHORT, gun.getShortRange());
        setTextField(FIELD_GUN_MED, gun.getMediumRange());
        setTextField(FIELD_GUN_LONG, gun.getLongRange());
        setTextField(FIELD_GUN_AMMO, numShots);
        setTextField(FIELD_GUN_CREW, (int) Math.ceil(gun.getTonnage(infantry)));
    }

    @Override
    protected void drawArmor() {
        EquipmentType armor = infantry.getArmorKit();
        if (armor != null) {
            setTextField(ARMOR_KIT, armor.getName());
        } else if (infantry.hasDEST()) {
            setTextField(ARMOR_KIT, "DEST");
        } else {
            StringJoiner sj = new StringJoiner("/");
            if (infantry.hasSneakCamo()) {
                sj.add("Camo");
            }
            if (infantry.hasSneakIR()) {
                sj.add("IR");
            }
            if (infantry.hasSneakECM()) {
                sj.add("ECM");
            }
            if (sj.length() > 0) {
                setTextField(ARMOR_KIT, "Sneak(" + sj.toString() + ")");
            }
        }
        setTextField(ARMOR_DIVISOR, infantry.calcDamageDivisor()
                + (infantry.isArmorEncumbering()? "E" : ""));
        if (infantry.hasDEST()) {
            hideElement(DEST_MODS, false);
            hideElement(SNEAK_IR_MODS, false);
        } else if (infantry.hasSneakCamo()) {
            hideElement(SNEAK_CAMO_MODS, false);
        }
        if (infantry.hasSneakIR()) {
            hideElement(SNEAK_IR_MODS, false);
        }

    }

    private static final int[][] RANGE_MODS = {
            {0},
            {-2, 0, 2, 4},
            {-2, 0, 0, 2, 2, 4, 4},
            {-2, 0, 0, 0, 2, 2, 2, 4, 4, 4},
            {-2, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4},
            {-1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4},
            {-1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 5, 5, 5},
            {-1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 6, 6, 6, 6}
    };

    /**
     * Calculate range mod as a string value.
     * @param range - the range to the target.
     * @param weapon - the primary weapon if there are no more than one secondary, otherwise secondary
     * @param otherWeapon - secondary weapon if there is exactly one, otherwise null. This is used
     * 							to account for point blank or encumbering penalties when the secondary
     * 							weapon is not the basis for range mods.
     * @param underwater - whether the base range should be halved for underwater use by SCUBA platoons.
     * @return - the range mod as a formatted String.
     */
    private String rangeMod(int range, InfantryWeapon weapon, InfantryWeapon otherWeapon,
                            boolean underwater) {
        int[] mods = RANGE_MODS[weapon.getInfantryRange()];
        if (underwater) {
            mods = RANGE_MODS[weapon.getInfantryRange() / 2];
        }

        if (range >= mods.length) {
            return "—";
        }
        int mod = mods[range];
        if (range == 0) {
            if (weapon.hasFlag(WeaponType.F_INF_BURST)) {
                mod--;
            }
            if (weapon.hasFlag(WeaponType.F_INF_POINT_BLANK)
                    || (otherWeapon != null && otherWeapon.hasFlag(WeaponType.F_INF_POINT_BLANK))) {
                mod++;
            }
            if (weapon.hasFlag(WeaponType.F_INF_ENCUMBER)
                    || (otherWeapon != null && otherWeapon.hasFlag(WeaponType.F_INF_ENCUMBER))) {
                mod++;
            }
        }
        if (mod > 0) {
            return "+" + mod;
        }
        return Integer.toString(mod);
    }

    private String formatGroundMP() {
        int walk = infantry.getWalkMP(true, true, false);
        if (walk == 0) {
            return "0*";
        } else {
            return String.valueOf(walk);
        }
    }
}
