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
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintDualTurretVehicle implements Printable {

    private Tank tank = null;
    private Tank tank2 = null;
    private int secondPageMargin = 373; // How far down the text should be

    // printed for a second vehicle.

    public PrintDualTurretVehicle(Tank tank, Tank tank2) {
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

        g2d.drawImage(ImageHelper.getRecordSheet(tank, true), 18, 18, 558, 368, null);

        if (tank2 == null) {
            g2d.drawImage(ImageHelperVehicle.getTableImage(tank), 18, 18 + secondPageMargin, 558, 368, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(tank2, true), 18, 18, 558, 368, null);
        }

        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips

        printFrontArmor(g2d, tank.getOArmor(Tank.LOC_FRONT), false);
        printLeftArmor(g2d, tank.getOArmor(Tank.LOC_LEFT), false);
        printRightArmor(g2d, tank.getOArmor(Tank.LOC_RIGHT), false);
        printRearArmor(g2d, tank.getOArmor(Tank.LOC_REAR), false);
        printRearTurretArmor(g2d, tank.getOArmor(Tank.LOC_TURRET), false);
        printFrontTurretArmor(g2d, tank.getOArmor(Tank.LOC_TURRET_2), false);

        // Internal Pips
        printFrontStruct(g2d, tank.getOInternal(Tank.LOC_FRONT), false);
        printLeftStruct(g2d, tank.getOInternal(Tank.LOC_LEFT), false);
        printRightStruct(g2d, tank.getOInternal(Tank.LOC_RIGHT), false);
        printRearStruct(g2d, tank.getOInternal(Tank.LOC_REAR), false);
        printRearTurretStruct(g2d, tank.getOInternal(Tank.LOC_TURRET), false);
        printFrontTurretStruct(g2d, tank.getOInternal(Tank.LOC_TURRET_2), false);

        if (tank2 != null) {
            // Armor Pips
            printFrontArmor(g2d, tank2.getOArmor(Tank.LOC_FRONT), true);
            printLeftArmor(g2d, tank2.getOArmor(Tank.LOC_LEFT), true);
            printRightArmor(g2d, tank2.getOArmor(Tank.LOC_RIGHT), true);
            printRearArmor(g2d, tank2.getOArmor(Tank.LOC_REAR), true);
            printRearTurretArmor(g2d, tank2.getOArmor(Tank.LOC_TURRET), true);
            printFrontTurretArmor(g2d, tank2.getOArmor(Tank.LOC_TURRET_2), true);

            // Internal Pips
            printFrontStruct(g2d, tank2.getOInternal(Tank.LOC_FRONT), true);
            printLeftStruct(g2d, tank2.getOInternal(Tank.LOC_LEFT), true);
            printRightStruct(g2d, tank2.getOInternal(Tank.LOC_RIGHT), true);
            printRearStruct(g2d, tank2.getOInternal(Tank.LOC_REAR), true);
            printRearTurretStruct(g2d, tank2.getOInternal(Tank.LOC_TURRET), true);
            printFrontTurretStruct(g2d, tank2.getOInternal(Tank.LOC_TURRET_2), true);

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
            g2d.drawString(Integer.toString(tank.getRunMP()), 79, 155);
        } else {
            int mascMP = tank.getRunMP();
            g2d.drawString(Integer.toString(tank.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        }

        g2d.drawString(tank.getMovementModeAsString(), 88, 166);

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
                engineName = "Fuel Cell Engine";
                break;
            default:
                break;
        }

        g2d.drawString(engineName, 79, 177);

        if (tank.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(tank.getWeight());

            g2d.drawString(Integer.toString(tonnage), 177, 134);
        } else {
            // DecimalFormatSymbols unusualSymbols =
            // new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,.1f", tank.getWeight()), 177, 134);
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
        g2d.drawString(techBase, 177, 145);

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
        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
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

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 374f);

        if (tank2 != null) {
            printTank2Data(g2d);
        } else {
            g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 374f + secondPageMargin);
        }
    }

    private void printTank2Data(Graphics2D g2d) {
        String tankName = tank2.getChassis().toUpperCase() + " " + tank2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 493);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((tank2.getCrew() != null) && !tank2.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = tank2.getCrew();
            g2d.drawString(pilot.getName(), 270, 120 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132 + secondPageMargin);
        }

        g2d.drawString(Integer.toString(tank2.getWalkMP()), 79, 515);
        if (!tank.hasWorkingMisc(MiscType.F_MASC, MiscType.S_SUPERCHARGER)) {
            g2d.drawString(Integer.toString(tank.getRunMP()), 79, 526);
        } else {
            int mascMP = tank.getRunMP();
            g2d.drawString(Integer.toString(tank.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 526);
        }

        g2d.drawString(tank2.getMovementModeAsString(), 88, 537);

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
                engineName = "Fuel Cell";
                break;
            default:
                break;
        }

        g2d.drawString(engineName, 79, 548);

        if (tank2.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(tank2.getWeight());
            g2d.drawString(Integer.toString(tonnage), 177, 505);
        } else {
            // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,d", tank2.getWeight()), 177, 505);
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
        g2d.drawString(techBase, 177, 145);

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
        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = tank.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 357 + secondPageMargin);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", tank.calculateBattleValue(true, true)), 50, 357 + secondPageMargin);
        }

        // g2d.drawString(myFormatter.format(tank2.getCost(true)) + " C-bills",
        // 52, 728);

        if (UnitUtil.hasBAR(tank2)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(tank2), 400, 64 + secondPageMargin);
        }

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(tank), font, 478, 48);
        //g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(tank), 463, 48);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_FRONT)) + ")", 467, 59);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_RIGHT) + ")", 559, 245);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_LEFT) + ")", 386, 193);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_REAR)) + ")", 467, 352);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET)) + ")", 457, 200);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET_2)) + ")", 442, 115);

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

    @SuppressWarnings("unused")
    private void printExtraFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
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
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        if (totalArmor < 1) {
            return;
        }

        float baseX = 438f;
        float baseY = 72f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.5f;
        float shiftY = 6.5f;
        int pipsPerLine = 14;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(73, 1);

        pipPlotter.add(new float[]
            { 423, 65 });
        pipPlotter.add(new float[]
            { 531, 65 });

        for (int lineCount = 1; lineCount <= 9; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 4) {
                pipsPerLine = 4;
                baseX += shiftX * 9f;
            }

            if (lineCount == 6) {
                pipsPerLine = 3;
                baseX += shiftX;
            }

            if (lineCount == 8) {
                pipsPerLine = 1;
                baseX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 428f;
        float baseY = 306f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.5f;
        float shiftY = 6.5f;
        int pipsPerLine = 16;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(64, 1);
        for (int lineCount = 1; lineCount <= 4; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    @SuppressWarnings("unused")
    private void printExtraRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
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

        printArmorPoints(g2d, pipPlotter, totalArmor, 7.0f);

    }

    private void printRearTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 449f;
        float baseY = 253f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.25f;
        float shiftY = 6.25f;
        int pipsPerLine = 10;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(60, 1);

        for (int lineCount = 1; lineCount <= 6; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFrontTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 463f;
        float baseY = 123f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 5.5f;
        float shiftY = 5.5f;
        int pipsPerLine = 6;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(46, 1);

        for (int lineCount = 1; lineCount <= 5; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                pipsPerLine = 10;
                baseX -= shiftX * 2;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    @SuppressWarnings("unused")
    private void printExtraTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

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
        printArmorPoints(g2d, pipPlotter, totalArmor, fontSize);
    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 441f;
        float baseY = 163f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.5f;
        float shiftY = 6.5f;
        int pipsPerLine = 3;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(90, 1);

        for (int lineCount = 1; lineCount <= 6; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointY += shiftY;
            }

            if (lineCount == 1) {
                pipsPerLine = 8;
                baseY -= shiftY * 3.5f;
            }

            if (lineCount == 2) {
                pipsPerLine = 17;
                baseY -= shiftY * 3.5f;
            }

            if (lineCount == 3) {
                pipsPerLine = 5;
                baseY += shiftY * 21.5f;
                pointX += shiftX;
            }

            if (lineCount == 4) {
                pipsPerLine = 31;
                baseY -= shiftY * 24.5f;
            }

            if (lineCount == 5) {
                pipsPerLine = 21;
                baseY += shiftY * 10.5f;
            }

            pointY = baseY;
            pointX -= shiftX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        if (totalArmor < 1) {
            return;
        }

        float baseX = 514f;
        float baseY = 163f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.5f;
        float shiftY = 6.5f;
        int pipsPerLine = 3;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(90, 1);

        for (int lineCount = 1; lineCount <= 6; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointY += shiftY;
            }

            if (lineCount == 1) {
                pipsPerLine = 8;
                baseY -= shiftY * 3.5f;
            }

            if (lineCount == 2) {
                pipsPerLine = 17;
                baseY -= shiftY * 3.5f;
            }

            if (lineCount == 3) {
                pipsPerLine = 5;
                baseY += shiftY * 21.5f;
                pointX -= shiftX;
            }

            if (lineCount == 4) {
                pipsPerLine = 31;
                baseY -= shiftY * 24.5f;
            }

            if (lineCount == 5) {
                pipsPerLine = 21;
                baseY += shiftY * 10.5f;
            }

            pointY = baseY;
            pointX += shiftX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        if (totalArmor < 1) {
            return;
        }

        float baseX = 462f;
        float baseY = 163f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(73, 1);

        for (int lineCount = 1; lineCount <= 2; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printStructPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        if (totalArmor < 1) {
            return;
        }

        float baseX = 462f;
        float baseY = 220f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(73, 1);

        for (int lineCount = 1; lineCount <= 2; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printStructPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFrontTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        if (totalArmor < 1) {
            return;
        }

        float baseX = 464f;
        float baseY = 147f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6f;
        float shiftY = 6f;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
            pointY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(73, 1);

        for (int lineCount = 1; lineCount <= 2; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printTurretISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 447, 178 };
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
            { 506, 178 };
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
            { 445, 291 };
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

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        printArmorPoints(g2d, pipPoints, totalArmor, 8.0f);
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor, float fontSize) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawTankArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1], fontSize);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printTurretISPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawTankISTurretPip(g2d, (int) pipPoints.get(currentPip)[0], (int) pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printStructPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawTankISPip(g2d, (int) pipPoints.get(currentPip)[0], (int) pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printTankImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(tank, ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(93, img.getHeight(null));
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 276 + ((93 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

        if (tank2 != null) {
            img = ImageHelper.getFluffImage(tank2, ImageHelper.imageVehicle);
            width = Math.min(148, img.getWidth(null));
            height = Math.min(93, img.getHeight(null));
            drawingX = 235 + ((148 - width) / 2);
            drawingY = 276 + ((93 - height) / 2) + secondPageMargin;
            g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
        }
    }
}