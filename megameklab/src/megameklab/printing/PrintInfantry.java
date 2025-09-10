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

import static megamek.common.options.PilotOptions.EDGE_ADVANTAGES;
import static megameklab.printing.InventoryEntry.DASH;

import java.util.Enumeration;
import java.util.StringJoiner;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.util.CConfig;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Lays out a record sheet block for a single infantry unit
 */
public class PrintInfantry extends PrintEntity {

    private final Infantry infantry;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param infantry  The infantry to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintInfantry(Infantry infantry, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.infantry = infantry;
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

        int infantrySize = options.showDamage() ? infantry.getShootingStrength() : infantry.getOInternal(0);
        for (int j = 1; j <= 30; j++) {
            if (j > infantrySize) {
                hideElement(SOLDIER + j, true);
                hideElement(NO_SOLDIER + j, false);
            } else {
                setTextField(DAMAGE + j, (int) Math.round(infantry.getDamagePerTrooper() * j));
            }
        }
        InfantryWeapon rangeWeapon = infantry.getPrimaryWeapon();
        if (infantry.getSecondaryWeapon() != null && infantry.getSecondaryWeaponsPerSquad() > 1
              && !infantry.getSecondaryWeapon().hasFlag(WeaponType.F_TAG)) {
            rangeWeapon = infantry.getSecondaryWeapon();
        }
        boolean scuba = infantry.getMovementMode() == EntityMovementMode.INF_UMU
              || infantry.getMovementMode() == EntityMovementMode.SUBMARINE;
        hideElement(UW_LABEL, !scuba);
        InfantryWeapon singleSecondary = (infantry.getSecondaryWeaponsPerSquad() == 1) ? infantry.getSecondaryWeapon()
              : null;
        for (int j = 0; j <= 21; j++) {
            setTextField(RANGE_MOD + j, rangeMod(j, rangeWeapon, singleSecondary, false));
            if (scuba) {
                setTextField(UW_RANGE_MOD + j, rangeMod(j, rangeWeapon, singleSecondary, true), true);
            }
        }

        setTextField(TRANSPORT_WT, String.format("%.1f tons", infantry.getWeight()));

        String mode1,
              mode2 = null,
              mp1,
              mp2 = null;
        switch (infantry.getMovementMode()) {
            case INF_JUMP:
                mp1 = formatMovement(infantry.getJumpMP());
                mode1 = "Jump";
                mp2 = formatGroundMP();
                mode2 = "Ground";
                break;
            case INF_UMU:
                mp1 = formatMovement(infantry.getActiveUMUCount());
                if (infantry.getOriginalJumpMP() > 1) {
                    mode1 = "SCUBA (Motorized)";
                } else {
                    mode1 = "SCUBA";
                }
                break;
            case HOVER:
                mp1 = formatGroundMP();
                mode1 = "Mechanized Hover";
                break;
            case TRACKED:
                mp1 = formatGroundMP();
                mode1 = "Mechanized Tracked";
                break;
            case WHEELED:
                mp1 = formatGroundMP();
                mode1 = "Mechanized Wheeled";
                break;
            case VTOL:
                mp1 = formatMovement(infantry.getJumpMP());
                if (infantry.hasMicrolite()) {
                    mode1 = "VTOL (Microlite)";
                } else {
                    mode1 = "VTOL (Micro-copter)";
                }
                break;
            case SUBMARINE:
                mp1 = formatMovement(infantry.getActiveUMUCount());
                mode1 = "Mechanized SCUBA";
                // As of writing, the only time Mechanized SCUBA infantry might have nonzero
                // walk mp is if it is beast-mounted.
                if (infantry.getOriginalWalkMP() > 0) {
                    mp2 = formatGroundMP();
                    mode2 = "Ground";
                }
                break;
            case INF_MOTORIZED:
                mp1 = formatMovement(infantry.getWalkMP());
                mode1 = "Motorized";
                break;
            case INF_LEG:
            default:
                mp1 = formatGroundMP();
                mode1 = "Ground";
                break;
        }

        // Add name of beast for beast-mounted infantry
        if (infantry.getMount() != null) {
            mode1 = String.format("%s [beast: %s]", mode1, infantry.getMount().name());
        }

        setTextField(MP_1, mp1);
        setTextField(MODE_1, mode1);
        if (mode2 != null) {
            setTextField(MP_2, mp2, true);
            setTextField(MODE_2, mode2, true);
            hideElement(MP_2_LABEL, false);
            hideElement(MODE_2_LABEL, false);
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
            while ((fontSize > 5.0)
                  && (height < (getFontHeight(fontSize) + 1) * getTextLength(notes, fontSize) / width)) {
                fontSize = Math.max(5f, fontSize - 1f);
            }

            addMultilineTextElement((Element) rect.getParentNode(), x, y, width, getFontHeight(fontSize),
                  String.join("; ", notes), fontSize, SVGConstants.SVG_START_VALUE,
                  SVGConstants.SVG_NORMAL_VALUE);
        }
        Element element = getSVGDocument().getElementById(RANGE_IN_HEXES);
        if (element != null) {
            element.setTextContent(element.getTextContent().replace("HEXES",
                  CConfig.getParam(CConfig.RS_SCALE_UNITS)));
        }
        if (CConfig.getIntParam(CConfig.RS_SCALE_FACTOR) != 1) {
            for (int r = 0; r <= 21; r++) {
                setTextField(RANGE + r, CConfig.formatScale(r, false));
            }
        }
    }

    private String generateNotesText(InfantryWeapon rangeWeapon) {
        StringJoiner sj = new StringJoiner(" ");
        if (infantry.hasSpaceSuit()) {
            sj.add("Can operate in vacuum.");
        }
        if (infantry.getMount() != null) {
            if (infantry.getMount().getUWEndurance() > 0) {
                sj.add(String.format("Must surface every %d turns.", infantry.getMount().getUWEndurance()));
            }
        }
        int burst = 0;
        if (rangeWeapon.hasFlag(WeaponType.F_INF_BURST) ||
              infantry.primaryWeaponDamageCapped()) {
            burst = 1;
        }
        if (infantry.getMount() != null) {
            burst += infantry.getMount().getBurstDamageDice();
        }
        if (burst > 0) {
            sj.add(String.format("+%dD6 damage vs. conventional infantry.", burst));
        }
        if (infantry.getMount() != null) {
            if (infantry.getMount().vehicleDamage() > 0) {
                sj.add(String.format("+%d damage vs. vehicles and 'Meks", infantry.getMount().vehicleDamage()));
            }
            if (infantry.getMount().size().toHitMod != 0) {
                sj.add(String.format("%d attacker to-hit", infantry.getMount().size().toHitMod));
            }
        }
        if (rangeWeapon.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
            sj.add("Can only damage conventional infantry units.");
        }
        if (isFlameBased(infantry.getPrimaryWeapon())
              || ((infantry.getSecondaryWeapon() != null)
              && isFlameBased(infantry.getSecondaryWeapon()))) {
            sj.add("Flame-based weapon.");
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
            sj.add(
                  "Mountain climbing equipment. Unit can traverse 3 levels per hex. Unit is immune to the effects of Thin Atmosphere.");
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

        StringJoiner enhancements = getEnhancements();
        if (enhancements.length() > 0) {
            sj.add("Cybernetically enhanced: " + enhancements);
        }

        if (sj.length() > 0) {
            return sj.toString();
        } else {
            return "None";
        }
    }

    private StringJoiner getEnhancements() {
        StringJoiner enhancements = new StringJoiner(", ");
        var spas = infantry.getCrew().getOptions();
        for (Enumeration<IOptionGroup> e = spas.getGroups(); e.hasMoreElements(); ) {
            final IOptionGroup optionGroup = e.nextElement();
            if (optionGroup.getKey().equals(EDGE_ADVANTAGES)) {
                // Don't print Edge abilities, only SPAs and Cybernetics
                continue;
            }
            if (spas.count(optionGroup.getKey()) > 0) {
                for (Enumeration<IOption> options = optionGroup.getOptions(); options.hasMoreElements(); ) {
                    IOption option = options.nextElement();
                    if (option != null && option.booleanValue()) {
                        enhancements
                              .add(option.getDisplayableNameWithValue().replaceAll("\\s+\\(Not Implemented\\)", ""));
                    }
                }
            }
        }
        return enhancements;
    }

    private boolean isFlameBased(WeaponType type) {
        return (type.hasFlag(WeaponType.F_PLASMA)
              || type.hasFlag(WeaponType.F_INCENDIARY_NEEDLES)
              || type.hasFlag(WeaponType.F_INFERNO)
              || type.hasFlag(WeaponType.F_FLAMER));
    }

    private void writeFieldGuns() {
        int numGuns = 0;
        int numShots = 0;
        WeaponType gun = null;
        for (Mounted<?> m : infantry.getEquipment()) {
            if (m.getLocation() == Infantry.LOC_FIELD_GUNS) {
                if (m.getType() instanceof WeaponType) {
                    gun = (WeaponType) m.getType();
                    numGuns++;
                } else if (m.getType() instanceof AmmoType) {
                    numShots += ((AmmoType) m.getType()).getShots();
                }
            }
        }
        if (gun == null) {
            return;
        }
        hideElement(FIELD_GUN_COLUMNS, false);
        setTextField(FIELD_GUN_QTY, numGuns);
        setTextField(FIELD_GUN_TYPE, gun.getName());
        /*
         * We don't use StringUnits.getEquipmentInfo() to format the damage
         * string because gauss explosion flags do not apply, and switchable
         * only applies for non-LBX.
         */
        if (gun instanceof ArtilleryWeapon) {
            setTextField(FIELD_GUN_DMG, gun.getRackSize() + " [AE,S,F]");
        } else if (gun instanceof ArtilleryCannonWeapon) {
            setTextField(FIELD_GUN_DMG, gun.getRackSize() + " [DB,AE]");
        } else {
            StringBuilder sb = new StringBuilder(Integer.toString(gun.getDamage()));
            switch (gun.getAmmoType()) {
                case AC_ULTRA:
                case AC_ULTRA_THB:
                    sb.append("/Sht, R2");
                    setTextField(FIELD_GUN_DMG_2, "[DB,R/S/C]");
                    break;
                case AC_ROTARY:
                    sb.append("/Sht, R6");
                    setTextField(FIELD_GUN_DMG_2, "[DB,R/S/C]");
                    break;
                case AC:
                case AC_PRIMITIVE:
                case LAC:
                    setTextField(FIELD_GUN_DMG_2, "[DB,C/S/F]");
                    break;
                case AC_LBX:
                case AC_LBX_THB:
                    setTextField(FIELD_GUN_DMG_2, "[DB,C/F]");
                    break;
                default:
                    sb.append(" [DB]");
            }
            setTextField(FIELD_GUN_DMG, sb.toString());
        }
        if (gun.getMinimumRange() > 0) {
            setTextField(FIELD_GUN_MIN_RANGE, gun.getMinimumRange());
        } else {
            setTextField(FIELD_GUN_MIN_RANGE, DASH);
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
        } else {

            if (infantry.hasDEST()) {
                setTextField(ARMOR_KIT, "Custom DEST");
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
                    setTextField(ARMOR_KIT, "Custom Sneak(" + sj + ")");
                } else if (infantry.getCustomArmorDamageDivisor() != 1.0) {
                    setTextField(ARMOR_KIT, "Custom");
                }
            }
        }
        setTextField(ARMOR_DIVISOR, infantry.calcDamageDivisor()
              + (infantry.isArmorEncumbering() ? "E" : ""));
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

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

    private static final int[][] RANGE_MODS = {
          { 0 },
          { -2, 0, 2, 4 },
          { -2, 0, 0, 2, 2, 4, 4 },
          { -2, 0, 0, 0, 2, 2, 2, 4, 4, 4 },
          { -2, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 },
          { -1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4 },
          { -1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 5, 5, 5 },
          { -1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 6, 6, 6, 6 }
    };

    /**
     * Calculate range mod as a string value.
     *
     * @param range       - the range to the target.
     * @param weapon      - the primary weapon if there are no more than one secondary, otherwise secondary
     * @param otherWeapon - secondary weapon if there is exactly one, otherwise null. This is used to account for
     *                    point-blank or encumbering penalties when the secondary weapon is not the basis for range
     *                    mods.
     * @param underwater  - whether the base range should be halved for underwater use by SCUBA platoons.
     *
     * @return - the range mod as a formatted String.
     */
    private String rangeMod(int range, InfantryWeapon weapon, InfantryWeapon otherWeapon,
          boolean underwater) {
        int[] mods = RANGE_MODS[weapon.getInfantryRange()];
        if (underwater) {
            mods = RANGE_MODS[weapon.getInfantryRange() / 2];
        }

        if (range >= mods.length) {
            return DASH;
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
        int walk = infantry.getWalkMP();
        if (walk == 0) {
            return "0*";
        } else {
            return formatMovement(walk);
        }
    }
}
