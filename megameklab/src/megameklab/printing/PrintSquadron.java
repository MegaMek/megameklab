/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

import megamek.common.MPCalculationSetting;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.Entity;
import megamek.common.units.FighterSquadron;
import megamek.logging.MMLogger;
import megameklab.util.UnitUtil;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PrintSquadron extends PrintEntity {
    private final MMLogger logger = MMLogger.create(PrintSquadron.class);

    private final FighterSquadron squadron;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintSquadron(FighterSquadron squadron, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.squadron = squadron;
        squadron.updateSensors();
        squadron.updateSkills();
        // applyBombs also calls updateWeaponGroups()
        squadron.applyBombs();
        // We have to set a name here or else pilot data will be skipped
        squadron.getCrew().setName("IGNORED", 0);
    }

    @Override
    public Entity getEntity() {
        return squadron;
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "squadron.svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        return "Fighter Squadron Record Sheet";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(TEXT_SI, squadron.getOSI());
        setTextField(TOTAL_FUEL, squadron.getFuel());
        setTextField(HEAT_CAPACITY, formatHeat());

        for (int i = 0; i < squadron.getSubEntities().size(); i++) {
            writeTextFieldsForFighter(i);
        }
    }

    protected void writeTextFieldsForFighter(int i) {
        var fighter = (AeroSpaceFighter) squadron.getSubEntities().get(i);

        setTextField(TYPE, UnitUtil.getPrintName(fighter), i);

        var crew = fighter.getCrew();
        setTextField(G_P_SKILL_TEXT, "%d/%d".formatted(crew.getGunnery(), crew.getPiloting()), i);

        setTextField(TOTAL_FUEL, fighter.getFuel(), i);
        setTextField(HEAT_CAPACITY, formatHeatForFighter(fighter), i);
        setTextField(TEXT_SI, "(%d)".formatted(fighter.getOSI()), i);
        setTextField(TEXT_ARMOR_AND_THRESHOLD, "(%d/%d)".formatted(fighter.getCap0Armor(), fighter.getFatalThresh()), i);
        setTextField(MP_WALK, formatWalkForFighter(fighter), i);
        setTextField(MP_RUN, formatRunForFighter(fighter), i);
    }

    private void setTextField(String id, Object value, int fighter) {
        setTextField("%s:%d".formatted(id, fighter), value.toString());
    }

    @Override
    protected String formatWalk() {
        var baseSafeThrust = squadron.getWalkMP(MPCalculationSetting.BV_CALCULATION);
        // Movement with bombs
        var burdenedSafeThrust = squadron.getWalkMP(MPCalculationSetting.STANDARD);
        return formatMovement(burdenedSafeThrust, baseSafeThrust);
    }

    protected String formatWalkForFighter(AeroSpaceFighter fighter) {
        return formatMovement(fighter.getWalkMP(MPCalculationSetting.STANDARD), fighter.getWalkMP(MPCalculationSetting.BV_CALCULATION));
    }

    @Override
    protected String formatRun() {
        var baseSafeThrust = squadron.getRunMP(MPCalculationSetting.BV_CALCULATION);
        // Movement with bombs
        var burdenedSafeThrust = squadron.getRunMP(MPCalculationSetting.STANDARD);
        return formatMovement(burdenedSafeThrust, baseSafeThrust);
    }

    protected String formatRunForFighter(AeroSpaceFighter fighter) {
        return formatMovement(fighter.getRunMP(MPCalculationSetting.STANDARD), fighter.getRunMP(MPCalculationSetting.BV_CALCULATION));
    }

    protected String formatHeat() {
        var shs = fighters().filter(fighter -> fighter.getHeatType() == 0).mapToInt(AeroSpaceFighter::getHeatSinks).sum();
        var dhs = fighters().filter(fighter -> fighter.getHeatType() == 1).mapToInt(AeroSpaceFighter::getHeatSinks).sum();

        String label = "Heat Capacity (Current):\u00A0";

        if (dhs == 0) {
            return "%s %d".formatted(label, shs);
        } else if (shs == 0) {
            return "%s %d [%d doubles]".formatted(label, dhs * 2, dhs);
        } else {
            return "%s %d [%d SHS, %d DHS]".formatted(label, shs + dhs * 2, shs, dhs);
        }
    }

    protected String formatHeatForFighter(AeroSpaceFighter fighter) {
        if (fighter.getHeatType() == 0) {
            return Integer.toString(fighter.getHeatSinks());
        } else {
            return "%d [%d doubles]".formatted(fighter.getHeatSinks() * 2, fighter.getHeatSinks());
        }
    }

    private Stream<AeroSpaceFighter> fighters() {
        return squadron.getSubEntities().stream().map(AeroSpaceFighter.class::cast);
    }

    @Override
    protected void drawArmor() {
        for (int i = 0; i < squadron.getSubEntities().size(); i++) {
            drawArmorForFighter(i, (AeroSpaceFighter) squadron.getSubEntities().get(i));
        }
    }

    protected void drawArmorForFighter(int i, AeroSpaceFighter fighter) {
        // SI
        var element = getElementById("%s:%d".formatted(SI_PIPS, i));
        if (element instanceof SVGRectElement rect) {
            drawArmorPips(rect, fighter.getOSI(), 3);
        } else {
            logger.error("No SVGRectElement found with id {}:{}", SI_PIPS, i);
        }

        // Armor
        element = getElementById("%s:%d".formatted(ARMOR_PIPS, i));
        if (element instanceof SVGRectElement rect) {
            drawArmorPips(rect, fighter.getCap0Armor(), 16);
        } else {
            logger.error("No SVGRectElement found with id {}:{}", ARMOR_PIPS, i);
        }
    }

    public static final double PIP_SIZE = 4.85;
    protected void drawArmorPips(SVGRectElement element, int pips, int cols) {
        Rectangle2D bbox = getRectBBox(element);


        PrintUtil.printCapitalPipBlock(
              bbox.getX(),
              bbox.getY(),
              (SVGElement) element.getParentNode(),
              pips,
              PIP_SIZE,
              PIP_SIZE,
              FILL_WHITE,
              true,
              new AtomicInteger(0),
              "structure",
              "N/A",
              getSVGDocument(),
              getDamageFillColor(),
              cols,
   5
        );
    }
}
