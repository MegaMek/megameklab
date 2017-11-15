/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.Vehicle.Printing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Calendar;
import java.util.Vector;

import megamek.common.Crew;
import megamek.common.Engine;
import megamek.common.MiscType;
import megamek.common.SupportTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintVehicle implements Printable {

    private Tank tank = null;
    private Tank tank2 = null;
    private int secondPageMargin = 371; // How far down the text should be

    // printed for a second vehicle.

    public PrintVehicle(Tank tank, Tank tank2) {
        this.tank = tank;
        this.tank2 = tank2;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        System.gc();

        g2d.drawImage(ImageHelper.getRecordSheet(tank, false), 18, 18, 558, 368, null);

        if (tank.hasNoDualTurret() && !tank.hasNoTurret()) {
            g2d.drawImage(ImageHelperVehicle.getTurretImage(tank), 441, 173, 77, 96, null);
            g2d.drawImage(ImageHelperVehicle.getTurretLabelImage(), 297, 248, 34, 11, null);
        }

        if (tank2 == null) {
            g2d.drawImage(ImageHelperVehicle.getTableImage(tank), 18, 18 + secondPageMargin, 558, 368, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(tank2, false), 18, 18 + secondPageMargin, 558, 368, null);
            if (tank2.hasNoDualTurret() && !tank2.hasNoTurret()) {
                g2d.drawImage(ImageHelperVehicle.getTurretImage(tank2), 441, 173 + secondPageMargin, 77, 96, null);
                g2d.drawImage(ImageHelperVehicle.getTurretLabelImage(), 297, 248 + secondPageMargin, 34, 11, null);
            }
        }

        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, tank.getOArmor(Tank.LOC_FRONT), false, tank.hasModularArmor(Tank.LOC_FRONT));
        printLeftArmor(g2d, tank.getOArmor(Tank.LOC_LEFT), false, tank.hasModularArmor(Tank.LOC_LEFT));
        printRightArmor(g2d, tank.getOArmor(Tank.LOC_RIGHT), false, tank.hasModularArmor(Tank.LOC_RIGHT));
        printRearArmor(g2d, tank.getOArmor(Tank.LOC_REAR), false, tank.hasModularArmor(Tank.LOC_REAR));
        printTurretArmor(g2d, tank.getOArmor(Tank.LOC_TURRET), false, tank.hasModularArmor(Tank.LOC_TURRET));

        // Internal Pips
        printFrontStruct(g2d, tank.getOInternal(Tank.LOC_FRONT), false);
        printLeftStruct(g2d, tank.getOInternal(Tank.LOC_LEFT), false);
        printRightStruct(g2d, tank.getOInternal(Tank.LOC_RIGHT), false);
        printRearStruct(g2d, tank.getOInternal(Tank.LOC_REAR), false);

        printTurretStruct(g2d, tank.getOInternal(Tank.LOC_TURRET), false);

        if (tank2 != null) {
            // Armor Pips
            printFrontArmor(g2d, tank2.getOArmor(Tank.LOC_FRONT), true, tank2.hasModularArmor(Tank.LOC_FRONT));
            printLeftArmor(g2d, tank2.getOArmor(Tank.LOC_LEFT), true, tank2.hasModularArmor(Tank.LOC_LEFT));
            printRightArmor(g2d, tank2.getOArmor(Tank.LOC_RIGHT), true, tank2.hasModularArmor(Tank.LOC_RIGHT));
            printRearArmor(g2d, tank2.getOArmor(Tank.LOC_REAR), true, tank2.hasModularArmor(Tank.LOC_REAR));
            printTurretArmor(g2d, tank2.getOArmor(Tank.LOC_TURRET), true, tank2.hasModularArmor(Tank.LOC_TURRET));

            // Internal Pips
            printFrontStruct(g2d, tank2.getOInternal(Tank.LOC_FRONT), true);
            printLeftStruct(g2d, tank2.getOInternal(Tank.LOC_LEFT), true);
            printRightStruct(g2d, tank2.getOInternal(Tank.LOC_RIGHT), true);
            printRearStruct(g2d, tank2.getOInternal(Tank.LOC_REAR), true);
            printTurretStruct(g2d, tank2.getOInternal(Tank.LOC_TURRET), true);

        }

        printTankImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printTankData(Graphics2D g2d) {
        String tankName = tank.getChassis() + " " + tank.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 120);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((tank.getCrew() != null) && !tank.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = tank.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(tank.getWalkMP()), 79, 144);
        if (!tank.hasWorkingMisc(MiscType.F_MASC, MiscType.S_SUPERCHARGER)) {
            g2d.drawString(Integer.toString(tank.getRunMP()), 79, 154);
        } else {
            int mascMP = tank.getRunMP();
            g2d.drawString(Integer.toString(tank.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 154);
        }

        if (tank.getJumpMP() > 0){
            font = UnitUtil.deriveFont(true, 8f);
            g2d.setFont(font);
            g2d.drawString("Jump:", 92, 154f);
            font = UnitUtil.deriveFont(false, 8f);
            g2d.setFont(font);
            g2d.drawString(Integer.toString(tank.getJumpMP()), 120, 154f);


        }

        g2d.drawString(tank.getMovementModeAsString(), 88, 165);

        String engineName = "Fusion Engine";

        switch (tank.getEngine().getEngineType()) {
            case Engine.COMBUSTION_ENGINE:
                engineName = "I.C.E.";
                break;
            case Engine.LIGHT_ENGINE:
                engineName = "Light Fusion Engine";
                break;
            case Engine.XL_ENGINE:
                engineName = "XL Fusion Engine";
                break;
            case Engine.XXL_ENGINE:
                engineName = "XXL Fusion Engine";
                break;
            case Engine.COMPACT_ENGINE:
                engineName = "Compact Fusion Engine";
                break;
            case Engine.FUEL_CELL:
                if (tank instanceof SupportTank) {
                    engineName = "Electric (Fuel Cell) Engine";
                } else {
                    engineName = "Fuel Cell Engine";
                }
                break;
            case Engine.FISSION:
                engineName = "Fission Engine";
                break;
            case Engine.SOLAR:
                engineName = "Electric (Solar) Engine";
                break;
            case Engine.BATTERY:
                engineName = "Electric (Batteries) Engine";
                break;
            case Engine.STEAM:
                engineName = "Steam Engine";
                break;
            case Engine.MAGLEV:
                engineName = "MagLev Engine";
                break;
            case Engine.NONE:
                engineName = "None";
                break;
            default:
                break;
        }

        g2d.drawString(engineName, 79, 176);

        if (tank.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(tank.getWeight());

            g2d.drawString(Integer.toString(tonnage), 177, 133);
        } else {
            g2d.drawString(String.format("%1$,.1f", tank.getWeight()), 177, 133);
        }

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        switch (tank.getTechLevel()) {

            case TechConstants.T_INTRO_BOXSET:
                ImageHelper.printCenterString(g2d, "(Intro)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_IS_TW_ALL:
            case TechConstants.T_CLAN_TW:
                break;
            case TechConstants.T_IS_ADVANCED:
            case TechConstants.T_CLAN_ADVANCED:
                ImageHelper.printCenterString(g2d, "(Advanced)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_EXPERIMENTAL:
                ImageHelper.printCenterString(g2d, "(Experimental)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_UNOFFICIAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
                ImageHelper.printCenterString(g2d, "(Unofficial)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
        }

        String techBase = "Inner Sphere";

        if (tank.isMixedTech()) {
            if (tank.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (tank.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 144);

        if ((tank.getSource() != null) && (tank.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, tank.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(tank.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", tank.getYear()), 177, nextDataLine);

        }

        // g2d.drawString(Integer.toString(tank.getYear()), 188, 155);

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = tank.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 357);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", tank.calculateBattleValue(true, true)), 50, 357);
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(tank.getCost(true)) + " C-bills",
        // 52, 357);

        if (UnitUtil.hasBAR(tank)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(tank), 400, 64);
        }

        font = UnitUtil.deriveFont(true, 7f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 374.5f);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 374.5f + secondPageMargin);
        if (tank2 != null) {
            printTank2Data(g2d);
        }
    }

    private void printTank2Data(Graphics2D g2d) {
        String tankName = tank2.getChassis().toUpperCase() + " " + tank2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 120 + secondPageMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((tank2.getCrew() != null) && !tank2.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = tank2.getCrew();
            g2d.drawString(pilot.getName(), 270, 120 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132 + secondPageMargin);
        }

        g2d.drawString(Integer.toString(tank2.getWalkMP()), 79, 144 + secondPageMargin);
        if (!tank.hasWorkingMisc(MiscType.F_MASC, MiscType.S_SUPERCHARGER)) {
            g2d.drawString(Integer.toString(tank.getRunMP()), 79, 155 + secondPageMargin);
        } else {
            int mascMP = tank.getRunMP();
            g2d.drawString(Integer.toString(tank.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155 + secondPageMargin);
        }
        g2d.drawString(tank2.getMovementModeAsString(), 88, 166 + secondPageMargin);

        String engineName = "Fusion Engine";

        switch (tank2.getEngine().getEngineType()) {
            case Engine.COMBUSTION_ENGINE:
                engineName = "I.C.E.";
                break;
            case Engine.LIGHT_ENGINE:
                engineName = "Light Fusion Engine";
                break;
            case Engine.XL_ENGINE:
                engineName = "XL Fusion Engine";
                break;
            case Engine.XXL_ENGINE:
                engineName = "XXL Fusion Engine";
                break;
            case Engine.COMPACT_ENGINE:
                engineName = "Compact Fusion Engine";
                break;
            case Engine.FUEL_CELL:
                engineName = "Fuel Cell Engine";
                break;
            case Engine.NONE:
                engineName = "None";
                break;
            default:
                break;
        }

        g2d.drawString(engineName, 79, 177 + secondPageMargin);

        if (tank2.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(tank2.getWeight());
            g2d.drawString(Integer.toString(tonnage), 177, 134 + secondPageMargin);
        } else {
            // DecimalFormatSymbols unusualSymbols =
            // new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,d", tank2.getWeight()), 177, 134 + secondPageMargin);
        }

        int nextDataLine = 155 + secondPageMargin;
        int startLine = 188;
        int lineFeed = 8;

        switch (tank2.getTechLevel()) {

            case TechConstants.T_INTRO_BOXSET:
                ImageHelper.printCenterString(g2d, "(Intro)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_IS_TW_ALL:
            case TechConstants.T_CLAN_TW:
                break;
            case TechConstants.T_IS_ADVANCED:
            case TechConstants.T_CLAN_ADVANCED:
                ImageHelper.printCenterString(g2d, "(Advanced)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_EXPERIMENTAL:
                ImageHelper.printCenterString(g2d, "(Experimental)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_UNOFFICIAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
                ImageHelper.printCenterString(g2d, "(Unofficial)", font, startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
        }

        String techBase = "Inner Sphere";

        if (tank2.isMixedTech()) {
            if (tank2.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (tank2.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145 + secondPageMargin);

        if ((tank2.getSource() != null) && (tank2.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, tank2.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(tank2.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", tank2.getYear()), 177, nextDataLine);

        }

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = tank2.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 357 + secondPageMargin);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", tank2.calculateBattleValue(true, true)), 50, 357 + secondPageMargin);
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(tank2.getCost(true)) + " C-bills",
        // 52, 357 + secondPagemMargin);

        if (UnitUtil.hasBAR(tank2)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(tank2), 400, 64 + secondPageMargin);
        }

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(tank), g2d.getFont(), 478, 48);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_FRONT)) + ")", 467, 64);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_RIGHT) + ")", 559, 230);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_LEFT) + ")", 384, 175);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_REAR)) + ")", 467, 342);

        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET)) + ")", 455, 186);
        }

        if (tank2 != null) {
            font = UnitUtil.deriveFont(true, 11.0f);
            g2d.setFont(font);
            ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(tank2), g2d.getFont(), 478, 48 + secondPageMargin);
            //g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(tank2), 463, 48 + secondPageMargin);
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);

            g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_FRONT)) + ")", 467, 64 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_RIGHT)) + ")", 559, 230 + secondPageMargin);

            g2d.drawString("(" + tank2.getArmor(Tank.LOC_LEFT) + ")", 384, 175 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_REAR)) + ")", 467, 342 + secondPageMargin);

            if (tank2.getOInternal(Tank.LOC_TURRET) > 0) {
                g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_TURRET)) + ")", 455, 186 + secondPageMargin);
            }

        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printTankWeaponsNEquipment(tank, g2d);

        if (tank2 != null) {
            ImageHelperVehicle.printTankWeaponsNEquipment(tank2, g2d, secondPageMargin);
        }

    }

    private void printExtraFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn =
            { 442, 90 };
        float[] middleColumn =
            { 498, 125 };
        float[] bottomColumn =
            { 493, 136.2f };
        float[] pipShift =
            { 6, 6 };

        float[][] extraArmor =
            {
                { 429, 90 },
                { 429, 97 },
                { 518, 90 },
                { 518, 97 },
                { 436f, 93.5f },
                { 436f, 100.5f },
                { 513f, 93.5f },
                { 513f, 100.5f },
                { 513f, 107.5f },
                { 436f, 107.5f } };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            middleColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;

            for (int pos = 0; pos < extraArmor.length; pos++) {
                extraArmor[pos][1] += secondPageMargin;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 72; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 12) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        pipShift[0] *= -1;
        for (int pos = 1; pos <= 16; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
            if ((pos % 8) == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 18; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 6) == 0) {
                bottomColumn[1] += pipShift[1] - 0.5f;
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        for (int pos = 0; pos < 10; pos++) {
            pipPlotter.add(new float[]
                { extraArmor[pos][0], extraArmor[pos][1] });
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn =
            { 444, 90 };
        float[] middleColumn =
            { 500, 125 };
        float[] bottomColumn =
            { 493, 132 };
        float[] pipShift =
            { 7, 7 };

        float[][] extraArmor =
            {
                { 430, 90 },
                { 430, 97 },
                { 519, 90 },
                { 519, 97 },
                { 437f, 93.5f },
                { 437f, 100.5f },
                { 513f, 93.5f },
                { 513f, 100.5f },
                { 513f, 107.5f },
                { 437f, 107.5f } };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            middleColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;

            for (int pos = 0; pos < extraArmor.length; pos++) {
                extraArmor[pos][1] += secondPageMargin;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        if (totalArmor > 86) {
            printExtraFrontArmor(g2d, totalArmor, secondImage, hasModularArmor);
            return;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 50; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 10) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 8; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 18; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 6) == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 10; pos++) {
            pipPlotter.add(new float[]
                { extraArmor[pos - 1][0], extraArmor[pos - 1][1] });
        }

        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {

        if (totalArmor > 51) {
            printExtraRearArmor(g2d, totalArmor, secondImage, hasModularArmor);
            return;
        }

        int[] topColumn = new int[]
            { 422, 291 };
        int[] middleColumn = new int[]
            { 422, 298 };
        int[] bottomColumn = new int[]
            { 422, 305 };
        int[] pipShift = new int[]
            { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            middleColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = 17;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        pips = 17;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        pips = 17;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);

    }

    private void printExtraRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        int[] topColumn = new int[]
            { 420, 291 };
        int[] middleColumn = new int[]
            { 420, 298 };
        int[] bottomColumn = new int[]
            { 420, 305 };
        int[] pipShift = new int[]
            { 5, 5 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            middleColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = 24;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        pips = 24;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        pips = 24;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
        }

        pipPlotter.add(new float[]
            { 482, 312 });

        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, 7.0f, hasModularArmor);

    }

    private void printTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {

        if (totalArmor < 1) {
            return;
        }

        if (totalArmor > 52) {
            printExtraTurretArmor(g2d, totalArmor, secondImage, hasModularArmor);
            return;
        }

        float[] topColumn = new float[]
            { 458f, 233f };
        float[] bottomColumn = new float[]
            { 451.5f, 240f };
        float[] pipShift = new float[]
            { 6f, 6f };
        // float fontSize = 8.0f;

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = 7;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 45; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 9) == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    private void printExtraTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {

        if (totalArmor < 1) {
            return;
        }

        float[] topColumn = new float[]
            { 456.5f, 232f };
        float[] bottomColumn = new float[]
            { 453.5f, 238f };
        float[] pipShift = new float[]
            { 4.5f, 4.5f };
        float fontSize = 5.5f;

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = 9;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 70; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 11) == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, fontSize, hasModularArmor);
    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn = new float[]
            { 418.5f, 92.5f };
        float[] pipShift = new float[]
            { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 90; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });

            switch (pos) {
                case 1:
                case 2:
                    topColumn[1] += pipShift[1];
                    break;
                case 4:
                case 6:
                case 9:
                case 12:
                case 21:
                case 27:
                case 46:
                case 49:
                case 52:
                case 70:
                case 74:
                case 85:
                case 88:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 78:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= 1.3;
                    break;
                case 82:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] += 1.3;
                    break;
                case 43:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] += pipShift[0];
                    break;
                case 39:
                    topColumn[1] += pipShift[1];
                    pipShift[0] = Math.abs(pipShift[0]);
                    topColumn[0] -= pipShift[0] * 5.2;
                    break;
                case 55:
                    topColumn[1] += pipShift[1];
                    topColumn[0] -= pipShift[0] * 2.2;
                    break;
                case 58:
                case 60:
                case 62:
                case 64:
                    topColumn[1] += pipShift[1];
                    topColumn[0] -= pipShift[0] * 1.25;
                    break;
                case 66:
                    topColumn[1] += pipShift[1] * 1.3;
                    topColumn[0] -= pipShift[0];
                    break;
                case 16:
                case 33:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= pipShift[0] * .5;
                    break;
                case 79:
                case 81:
                case 87:
                    topColumn[0] += pipShift[0] * .85;
                    break;
                default:
                    topColumn[0] += pipShift[0];
                    break;
            }

        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn = new float[]
            { 530f, 92 };
        float[] pipShift = new float[]
            { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 90; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });

            switch (pos) {
                case 1:
                case 2:
                    topColumn[1] += pipShift[1];
                    break;
                case 4:
                case 6:
                case 9:
                case 12:
                case 16:
                case 21:
                case 27:
                case 33:
                case 46:
                case 49:
                case 52:
                case 70:
                case 74:
                    // case 78:
                case 85:
                case 82:
                case 88:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 78:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += 1.5f;
                    pipShift[0] *= -.9f;
                    break;
                case 43:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= pipShift[0];
                    break;
                case 39:
                    topColumn[1] += pipShift[1];
                    pipShift[0] = Math.abs(pipShift[0]);
                    topColumn[0] += pipShift[0] * 5.5;
                    break;
                case 55:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += pipShift[0] * 2.2;
                    break;
                case 58:
                case 60:
                case 62:
                case 64:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += pipShift[0] * 1.25;
                    break;
                case 66:
                    topColumn[1] += pipShift[1] * 1.25;
                    topColumn[0] += pipShift[0];
                    break;
                case 89:
                    topColumn[0] -= pipShift[0] * .9;
                    break;
                default:
                    topColumn[0] -= pipShift[0];
                    break;
            }
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[]
            { 462, 148 };
        int[] bottomColumn = new int[]
            { 462, 156 };
        int[] pipShift = new int[]
            { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = Math.min(5, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((5 - pips) / 2);
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    private void printTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[]
            { 462, 210 };
        int[] bottomColumn = new int[]
            { 462, 218 };
        int[] pipShift = new int[]
            { 7, 7 };

        if (totalArmor < 1) {
            return;
        }

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = Math.min(5, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((5 - pips) / 2);
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 444, 164 };
        int[] pipShift = new int[]
            { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] -= pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 505, 164 };
        int[] pipShift = new int[]
            { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 445, 275 };
        int[] pipShift = new int[]
            { 7, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        column[0] += pipShift[0] * ((10 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
        }
    }

    private void printTankImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(tank, ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(99, img.getHeight(null));
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 270 + ((99 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

        if (tank2 != null) {
            img = ImageHelper.getFluffImage(tank2, ImageHelper.imageVehicle);
            width = Math.min(148, img.getWidth(null));
            height = Math.min(99, img.getHeight(null));
            drawingX = 235 + ((148 - width) / 2);
            drawingY = 268 + ((99 - height) / 2) + secondPageMargin;
            g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
        }
    }

}