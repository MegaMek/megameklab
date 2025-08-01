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
package megameklab.printing;

import megamek.client.ui.util.UIUtil;
import megamek.common.*;
import megamek.common.battlevalue.BattleArmorBVCalculator;
import megameklab.util.BattleArmorUtil;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
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
                setTextField(MP_2, formatMovement(battleArmor.getJumpMP(MPCalculationSetting.BA_UNBURDENED)));
                break;
            case VTOL:
                setTextField(MODE_2, "VTOL MP:");
                setTextField(MP_2, formatMovement(battleArmor.getJumpMP(MPCalculationSetting.BA_UNBURDENED)));
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
        hideElement(CHECK_SWARM, !BattleArmorUtil.canSwarm(battleArmor));
        hideElement(CHECK_LEG, !BattleArmorUtil.canLegAttack(battleArmor));
        hideElement(CHECK_AP, battleArmor.countWorkingMisc(MiscType.F_AP_MOUNT) == 0);

        String bvValue;
        int baseBvValue = getEntity().calculateBattleValue(true, !showPilotInfo());
        if (showC3()) {
            int adjustedBvValue = getEntity().calculateBattleValue(false, !showPilotInfo());
            if (adjustedBvValue == baseBvValue) {
                bvValue = NumberFormat.getInstance().format(baseBvValue);
            } else {
                bvValue = NumberFormat.getInstance().format(baseBvValue) + UIUtil.CONNECTED_SIGN
                        + NumberFormat.getInstance().format(adjustedBvValue);
            }
        } else {
            bvValue = NumberFormat.getInstance().format(baseBvValue);
        }
        setTextField(BV, bvValue + "/" + ((BattleArmorBVCalculator)battleArmor.getBvCalculator()).singleTrooperBattleValue());
    }

    @Override
    protected void drawArmor() {
        final String armorName = EquipmentType.getArmorTypeName(battleArmor.getArmorType(BattleArmor.LOC_SQUAD));
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
                    final boolean alive = battleArmor.getInternal(BattleArmor.LOC_TROOPER_1 + i) > 0;
                    int currentArmor = battleArmor.getArmor(BattleArmor.LOC_TROOPER_1 + i);
                    for (int p = 0; p < pipCount; p++) {
                        String fill;
                        if (p == 0) {
                            fill = alive ? FILL_GREY : getDamageFillColor();
                        } else {
                            final boolean isDamaged = p > currentArmor;
                            fill = (!alive || isDamaged) ? getDamageFillColor() : FILL_WHITE;
                        }
                        Element pip = createPip(viewX + size * p, y, radius, strokeWidth, PipType.CIRCLE, fill,
                              "armor", "T"+i, false);
                        canvas.appendChild(pip);
                    }
                }
            } else {
                hideElement(SUIT + i, true);
            }
        }
    }

    @Override
    protected String formatWalk() {
        if (battleArmor.hasDWP()) {
            return formatMovement(battleArmor.getWalkMP(),
                    battleArmor.getWalkMP(MPCalculationSetting.BA_UNBURDENED));
        } else {
            return super.formatWalk();
        }
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

    @Override
    public String formatMiscNotes() {
        final StringJoiner sj = new StringJoiner(" ");
        if (battleArmor.isBurdened() && ((battleArmor.getJumpMP(MPCalculationSetting.BA_UNBURDENED) > 0)
                || BattleArmorUtil.canLegAttack(battleArmor) || BattleArmorUtil.canSwarm(battleArmor))) {
            sj.add("Must detach missiles before jumping or swarm/leg attacks.");
        }
        if (battleArmor.hasDWP()) {
            if (battleArmor.getJumpMP(MPCalculationSetting.BA_UNBURDENED) > 0) {
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
   /*
    * Battle Armor troopers use different names based on faction and tech base. Any unaccounted-for squad configurations will be called "SQUAD".
    * 1 Trooper: Trooper (NIU suits, The Bounty Hunter)
    * 3 Troopers: Un (Society)
    * 4 Troopers: Squad (Inner Sphere)
    * 5 Troopers: Point (Clans), Maniple (Taurian Concordat)
    * 6 Troopers: Level I (ComStar, Word of Blake)
    */
    private String squadName() {
        switch (battleArmor.getTroopers()) {
            case 1:
                return "SUIT";
            case 3:
                return "UN";
            case 5:
                return "POINT";
            case 6:
                return "LEVEL I";
            default:
            case 4:
                return "SQUAD";
        }
    }
}
