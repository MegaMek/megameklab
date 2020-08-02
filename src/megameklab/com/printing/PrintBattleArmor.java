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

import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megameklab.com.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.util.StringJoiner;

/**
 * Lays out a record sheet block for a single BattleArmor unit
 */
public class PrintBattleArmor extends PrintEntity {

    private final BattleArmor battleArmor;
    private final int squadIndex;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param battleArmor  The battlearmor to print
     * @param squadIndex   The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     * @param options      Overrides the global options for which elements are printed
     */
    public PrintBattleArmor(BattleArmor battleArmor, int squadIndex, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.battleArmor = battleArmor;
        this.squadIndex = squadIndex;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param battleArmor  The BattleArmor to print
     * @param squadIndex   The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     */
    public PrintBattleArmor(BattleArmor battleArmor, int squadIndex, int startPage) {
        this(battleArmor, startPage, squadIndex, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "battle_armor_squad.svg";
    }


    @Override
    public Entity getEntity() {
        return battleArmor;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();

        setTextField(SQUAD, "BATTLE ARMOR: " + squadName() + " " + (squadIndex + 1));
        switch (getEntity().getMovementMode()) {
            case INF_JUMP:
                setTextField(MODE_2, "Jump MP:");
                setTextField(MP_2, formatMovement(battleArmor.getJumpMP(true, true, true)));
                break;
            case VTOL:
                setTextField(MODE_2, "VTOL MP:");
                setTextField(MP_2, formatMovement(battleArmor.getJumpMP(true, true, true)));
                break;
            case INF_UMU:
                setTextField(MODE_2, "UW MP:");
                setTextField(MP_2, formatMovement(battleArmor.getActiveUMUCount()));
                break;
            default:
                hideElement(MODE_2, true);
                hideElement(MP_2, true);
                break;
        }
        hideElement(CHECK_MECHANIZED, !battleArmor.canDoMechanizedBA());
        hideElement(CHECK_SWARM, !UnitUtil.canSwarm(battleArmor));
        hideElement(CHECK_LEG, !UnitUtil.canLegAttack(battleArmor));
        hideElement(CHECK_AP, battleArmor.countWorkingMisc(MiscType.F_AP_MOUNT) == 0);

        setTextField(BV, battleArmor.calculateBattleValue(true, false, false)
            + "/" + battleArmor.calculateBattleValue(true, false, true));
    }

    @Override
    protected void drawArmor() {
        final String armorName = EquipmentType.getBaArmorTypeName(battleArmor.getArmorType(BattleArmor.LOC_SQUAD));
        setTextField(ARMOR_TYPE, armorName.replace("BA ", ""));
        for (int i = 0; i < 6; i++) {
            if (i < battleArmor.getTroopers()) {
                Element element = getSVGDocument().getElementById(PIPS + i);
                if (element instanceof SVGRectElement) {
                    Rectangle2D bbox = getRectBBox((SVGRectElement) element);
                    Element canvas = (Element) element.getParentNode();
                    int viewWidth = (int)bbox.getWidth();
                    int viewHeight = (int)bbox.getHeight();
                    int viewX = (int)bbox.getX();
                    int viewY = (int)bbox.getY();
                    // Extra pip for trooper
                    final int pipCount = battleArmor.getOArmor(BattleArmor.LOC_TROOPER_1 + i) + 1;

                    // Max armor for any BA unit is 18
                    double size = viewWidth / 19.0;
                    double radius = size * 0.36;
                    double strokeWidth = 0.9;
                    double y = viewY + viewHeight * 0.5 - radius;
                    for (int p = 0; p < pipCount; p++) {
                        Element pip = createPip(viewX + size * p, y, radius, strokeWidth);
                        if (p == 0) {
                            pip.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, FILL_GREY);
                        }
                        canvas.appendChild(pip);
                    }
                }
            } else {
                hideElement(SUIT + i, true);
            }
        }
    }

    @Override
    public String formatMiscNotes() {
        final StringJoiner sj = new StringJoiner(" ");
        if (battleArmor.isBurdened() && ((battleArmor.getJumpMP(false, true, true) > 0)
                || UnitUtil.canLegAttack(battleArmor) || UnitUtil.canSwarm(battleArmor))) {
            sj.add("Must detach missiles before jumping or swarm/leg attacks.");
        }
        if (battleArmor.hasDWP()) {
            if (battleArmor.getJumpMP(true, true, true) > 0) {
                sj.add("Must detach DWP before jumping or moving full ground speed.");
            } else {
                sj.add("Must detach DWP before moving full ground speed.");
            }
        }
        if (battleArmor.isExoskeleton() && !battleArmor.hasWorkingMisc(MiscType.F_EXTENDED_LIFESUPPORT)) {
            sj.add("Unsealed Exoskeleton.");
        }
        return sj.toString();
    }

    private String squadName() {
        switch (battleArmor.getTroopers()) {
            case 6:
                return "LEVEL I";
            case 5:
                return battleArmor.isClan() ? "POINT" : "SQUAD";
            case 3:
                return "TREY";
            default:
            case 4:
                return "SQUAD";
        }
    }
}
