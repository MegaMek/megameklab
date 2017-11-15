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
import java.io.File;
import java.util.Calendar;
import java.util.Vector;

import com.kitfox.svg.SVGException;

import megamek.common.Crew;
import megamek.common.Engine;
import megamek.common.LargeSupportTank;
import megamek.common.MiscType;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintLargeSupportVehicle implements Printable {

    private Tank largesupporttank = null;
    private int secondPageMargin = 375; // How far down the text should be
    private float yoffset = 7;
    private float xoffset = 17;

    // printed for a second vehicle.

    public PrintLargeSupportVehicle(Tank tank) {
        this.largesupporttank = tank;
        /*
         * if (awtImage != null) { System.out.println("Width: " +
         * awtImage.getWidth(null)); System.out.println("Height: " +
         * awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
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
        try {
            if (largesupporttank instanceof SuperHeavyTank) {
                try {
                    ImageHelper.loadSVGImage(new File("data/images/recordsheets/SuperHeavyTankCritTable.svg")).render(g2d);
                } catch (SVGException e) {
                    e.printStackTrace();
                }
            } else {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/LargeSupportTankCritTable.svg")).render(g2d);
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/LargeSupportTankHitTable.svg")).render(g2d);            }
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/TankMotiveDmgTable.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/TankSheetCopyrightInfo.svg")).render(g2d);

            ImageHelper.loadSVGImage(new File("data/images/recordsheets/SuperHeavyBaseTemplate.svg")).render(g2d);



            if (largesupporttank.hasNoTurret()) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/SuperHeavyNoTurretTemplate.svg")).render(g2d);
            } else if (largesupporttank.hasNoDualTurret()) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/SuperHeavySingleTurretTemplate.svg")).render(g2d);
            } else {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/SuperHeavyDualTurretTemplate.svg")).render(g2d);
            }

        } catch (SVGException e) {
            e.printStackTrace();
        }

        printLargeSupportTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, largesupporttank.getOArmor(Tank.LOC_FRONT), false);
        printFrontLeftArmor(g2d,
                largesupporttank.getOArmor(LargeSupportTank.LOC_FRONTLEFT),
                false);
        printFrontRightArmor(g2d,
                largesupporttank.getOArmor(LargeSupportTank.LOC_FRONTRIGHT),
                false);
        printRearLeftArmor(g2d,
                largesupporttank.getOArmor(LargeSupportTank.LOC_REARLEFT),
                false);
        printRearRightArmor(g2d,
                largesupporttank.getOArmor(LargeSupportTank.LOC_REARRIGHT),
                false);
        printRearArmor(g2d,
                largesupporttank.getOArmor(LargeSupportTank.LOC_REAR), false);
        if (!largesupporttank.hasNoTurret()) {
            printTurretArmor(g2d,
                    largesupporttank.getOArmor(LargeSupportTank.LOC_TURRET),
                    false);
        }
        if (!largesupporttank.hasNoDualTurret()) {
            printFrontTurretArmor(g2d,
                    largesupporttank.getOArmor(LargeSupportTank.LOC_TURRET_2));
        }

        // Internal Pips
        printFrontStruct(g2d, largesupporttank.getOInternal(Tank.LOC_FRONT),
                false);
        printLeftFrontStruct(g2d,
                largesupporttank.getOInternal(LargeSupportTank.LOC_FRONTLEFT),
                false);
        printRightFrontStruct(g2d,
                largesupporttank.getOInternal(LargeSupportTank.LOC_REARRIGHT),
                false);
        printLeftRearStruct(g2d,
                largesupporttank.getOInternal(LargeSupportTank.LOC_REARLEFT),
                false);
        printRightRearStruct(g2d,
                largesupporttank.getOInternal(LargeSupportTank.LOC_REARRIGHT),
                false);
        printRearStruct(g2d,
                largesupporttank.getOInternal(LargeSupportTank.LOC_REAR), false);
        if (!largesupporttank.hasNoTurret()) {
            printTurretStruct(g2d,
                    largesupporttank.getOInternal(LargeSupportTank.LOC_TURRET),
                    false);
        }
        if (!largesupporttank.hasNoDualTurret()) {
            printFrontTurretStruct(g2d,
                    largesupporttank
                            .getOInternal(LargeSupportTank.LOC_TURRET_2));
        }

        printLargeSupportTankImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(),
                pageFormat.getImageableHeight());

    }

    private void printLargeSupportTankData(Graphics2D g2d) {
        String largesupportankName = largesupporttank.getChassis() + " "
                + largesupporttank.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, largesupportankName, true, 180,
                10.0f));
        g2d.drawString(largesupportankName, 49 + xoffset, 120 + yoffset);

        Font font = UnitUtil.deriveFont(true, 15.0f);
        g2d.setFont(font);

        if (largesupporttank instanceof LargeSupportTank) {
            g2d.drawString("LARGE GROUND SUPPORT VEHICLE RECORD SHEET",
                    60 + xoffset, 88 + yoffset);
        } else {
            g2d.drawString("SUPER-HEAVY COMBAT VEHICLE RECORD SHEET",
                    60 + xoffset, 88 + yoffset);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((largesupporttank.getCrew() != null)
                && !largesupporttank.getCrew().getName()
                        .equalsIgnoreCase("unnamed")) {
            Crew pilot = largesupporttank.getCrew();
            g2d.drawString(pilot.getName(), 270 + xoffset, 120 + yoffset);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295 + xoffset,
                    132 + yoffset);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365 + xoffset,
                    132 + yoffset);
        }

        g2d.drawString(Integer.toString(largesupporttank.getWalkMP()),
                79 + xoffset, 144 + yoffset);
        if (!largesupporttank.hasWorkingMisc(MiscType.F_MASC,
                MiscType.S_SUPERCHARGER)) {
            g2d.drawString(Integer.toString(largesupporttank.getRunMP()),
                    79 + xoffset, 155 + yoffset);
        } else {
            int mascMP = largesupporttank.getRunMP();
            g2d.drawString(
                    Integer.toString(largesupporttank.getRunMPwithoutMASC())
                            + " [" + mascMP + "]", 79 + xoffset, 155 + yoffset);
        }

        g2d.drawString(largesupporttank.getMovementModeAsString(),
                88 + xoffset, 166 + yoffset);

        String engineName = "Fusion Engine";

        switch (largesupporttank.getEngine().getEngineType()) {
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

        g2d.drawString(engineName, 79 + xoffset, 177 + yoffset);

        int tonnage = (int) Math.ceil(largesupporttank.getWeight());

        g2d.drawString(Integer.toString(tonnage), 177 + xoffset, 134 + yoffset);

        String techBase = "Inner Sphere";
        if (largesupporttank.isMixedTech()) {
            if (largesupporttank.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (largesupporttank.isClan()) {
            techBase = "Clan";
        }
        font = UnitUtil.getNewFont(g2d, techBase, false, 48, 8f);
        g2d.setFont(font);
        g2d.drawString(techBase, 177 + xoffset, 145 + yoffset);

        float nextDataLine = 153 + yoffset;
        float startLine = 188 + xoffset;
        float lineFeed = 8;

        switch (largesupporttank.getTechLevel()) {

            case TechConstants.T_INTRO_BOXSET:
                ImageHelper.printCenterString(g2d, "(Intro)", font, startLine,
                        nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_IS_TW_ALL:
            case TechConstants.T_CLAN_TW:
                break;
            case TechConstants.T_IS_ADVANCED:
            case TechConstants.T_CLAN_ADVANCED:
                ImageHelper.printCenterString(g2d, "(Advanced)", font,
                        startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_EXPERIMENTAL:
                ImageHelper.printCenterString(g2d, "(Experimental)", font,
                        startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
            case TechConstants.T_IS_UNOFFICIAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
                ImageHelper.printCenterString(g2d, "(Unofficial)", font,
                        startLine, nextDataLine);
                nextDataLine += lineFeed;
                break;
        }

        if ((largesupporttank.getSource() != null)
                && (largesupporttank.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138 + xoffset, nextDataLine);

            font = UnitUtil.getNewFont(g2d, largesupporttank.getSource(),
                    false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(largesupporttank.getSource(), 177 + xoffset,
                    nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138 + xoffset, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", largesupporttank.getYear()),
                    177 + xoffset, nextDataLine);

        }

        // g2d.drawString(Integer.toString(largesupportank.getYear()), 188,
        // 155);

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = largesupporttank.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35 + xoffset, 357 + yoffset);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(
                    String.format("%1$,d",
                            largesupporttank.calculateBattleValue(true, true)),
                    50 + xoffset, 357 + yoffset);
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(largesupportank.getCost(true)) +
        // " C-bills",52 + xoffset, 357 + yoffset);

        if (UnitUtil.hasBAR(largesupporttank)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString(
                    "BAR: "
                            + Integer.toString(UnitUtil
                                    .getLowestBARRating(largesupporttank)),
                    400 + xoffset, 64 + yoffset);
        }

        font = UnitUtil.deriveFont(true, 6f);
        g2d.setFont(font);
        g2d.drawString(
                Integer.toString(Calendar.getInstance().get(Calendar.YEAR)),
                52f, 395f + secondPageMargin);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        ImageHelper.printCenterString(g2d,
                ImageHelperVehicle.getVehicleArmorTypeString(largesupporttank),
                g2d.getFont(), 470 + xoffset, 48 + yoffset);
        // g2d.drawString(
        // ImageHelperVehicle.getVehicleArmorTypeString(largesupporttank),
        // 455 + xoffset, 48 + yoffset);
        font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        g2d.drawString(
                "("
                        + Integer.toString(largesupporttank
                                .getArmor(Tank.LOC_FRONT)) + ")",
                465 + xoffset, 62 + yoffset);

        g2d.drawString(
                "("
                        + Integer.toString(largesupporttank
                                .getArmor(LargeSupportTank.LOC_FRONTRIGHT))
                        + ")", 550 + xoffset, 183 + yoffset);

        g2d.drawString(
                "(" + largesupporttank.getArmor(LargeSupportTank.LOC_FRONTLEFT)
                        + ")", 382 + xoffset, 109 + yoffset);

        g2d.drawString(
                "("
                        + Integer.toString(largesupporttank
                                .getArmor(LargeSupportTank.LOC_REARRIGHT))
                        + ")", 550 + xoffset, 302 + yoffset);

        g2d.drawString(
                "(" + largesupporttank.getArmor(LargeSupportTank.LOC_REARLEFT)
                        + ")", 380 + xoffset, 228 + yoffset);

        g2d.drawString(
                "("
                        + Integer.toString(largesupporttank
                                .getArmor(LargeSupportTank.LOC_REAR)) + ")",
                465 + xoffset, 343 + yoffset);

        if (!largesupporttank.hasNoTurret()) {
            g2d.drawString(
                    "("
                            + Integer.toString(largesupporttank
                                    .getArmor(LargeSupportTank.LOC_TURRET))
                            + ")", 404 + xoffset, 365 + yoffset);
        }
        if (!largesupporttank.hasNoDualTurret()) {
            g2d.drawString(
                    "("
                            + Integer.toString(largesupporttank
                                    .getArmor(LargeSupportTank.LOC_TURRET_2))
                            + ")", 525 + xoffset, 62 + yoffset);
        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printLargeSupportTankWeaponsNEquipment(
                largesupporttank, g2d, yoffset, xoffset);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        Vector<float[]> pipPlotter = new Vector<float[]>(140, 1);
        if (!largesupporttank.hasNoDualTurret()) {
            float[] row1Start = { 432f, 84f };
            float[] row1End = { 538f, 84f };
            int pipsFirstLine = Math.max(22,
                    (int) (Math.ceil((double) totalArmor / 5)) + 1);
            // float shiftX = (row1End[0]-row1Start[0])/pipsFirstLine;
            float fontSize = 6.5f;
            float shiftX = 5f;
            if (totalArmor >= 130) {
                fontSize = 5.5f;
                shiftX = 4.3f;
            } else if (totalArmor >= 100) {
                fontSize = 6f;
                shiftX = 4.5f;
            }
            float shiftY = 5f;
            float[] row2Start = { row1Start[0] + shiftX, row1Start[1] + shiftY };
            float[] row2End = { row1End[0] - shiftX, row1Start[1] + shiftY };
            float[] row3Start = { row2Start[0] + shiftX, row2Start[1] + shiftY };
            float[] row3End = { row2End[0] - shiftX, row2Start[1] + shiftY };
            float[] row4Start = { row3Start[0] + shiftX, row3Start[1] + shiftY };
            float[] row4End = { row3End[0] - shiftX, row3Start[1] + shiftY };
            float[] row5Start = { row4Start[0] + shiftX, row4Start[1] + shiftY };
            float[] row5End = { row4End[0] - shiftX, row4Start[1] + shiftY };
            float[] row6Start = { row5Start[0] + shiftX, row5Start[1] + shiftY };
            float[] row6End = { row5End[0] - shiftX, row5Start[1] + shiftY };
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row1Start,
                    row1End, pipsFirstLine));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row2Start,
                    row2End, pipsFirstLine - 2));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row3Start,
                    row3End, pipsFirstLine - 4));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row4Start,
                    row4End, pipsFirstLine - 6));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row5Start,
                    row5End, pipsFirstLine - 8));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(row6Start,
                    row6End, pipsFirstLine - 10));
            printArmorPoints(g2d, pipPlotter, totalArmor, fontSize);
        } else {
            float baseX = 435f;
            float baseY = 79f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5.8f;
            float shiftY = 5.8f;
            int pipsPerLine = 18;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            for (int lineCount = 0; lineCount < 9; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                    if ((lineCount == 7) && (point == 1)) {
                        pointX += shiftX * 11;
                    }
                    if ((lineCount == 8) && (point == 0)) {
                        pointX += shiftX * 11;
                    }
                }

                if ((lineCount == 5) || (lineCount == 7)) {
                    pipsPerLine -= 2;
                    baseX += shiftX;
                } else if (lineCount == 6) {
                    pipsPerLine = 4;
                    baseX += shiftX / 2;
                }

                pointY += shiftY;
                pointX = baseX;
            }
            printArmorPoints(g2d, pipPlotter, totalArmor);
        }

    }

    private void printRearArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            float baseX = 444f;
            float baseY = 294f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 6f;
            float shiftY = 6f;
            int pipsPerLine = 2;

            Vector<float[]> pipPlotter = new Vector<float[]>(118, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    if (((lineCount == 0) && (point == 0))
                            || ((lineCount == 1) && (point == 1))
                            || ((lineCount == 2) && (point == 2))
                            || ((lineCount == 3) && (point == 3))) {
                        pointX += shiftX * 13;
                    } else {
                        pointX += shiftX;
                    }
                }
                if (lineCount < 4) {
                    pointX -= shiftX * 2;
                    pipsPerLine += 2;
                    baseX -= shiftX;
                }
                if (lineCount == 4) {
                    pipsPerLine -= 2;
                    baseX += shiftX;
                }

                if (lineCount == 3) {
                    pipsPerLine += 12;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 446f;
            float baseY = 293f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5.8f;
            float shiftY = 5.8f;
            int pipsPerLine = 4;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(118, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                    if ((lineCount == 0) && (point == 1)) {
                        pointX += shiftX * 10;
                    }
                    if ((lineCount == 1) && (point == 3)) {
                        pointX += shiftX * 8;
                    }
                }

                if ((lineCount == 0)) {
                    pipsPerLine = 8;
                    baseX -= shiftX;
                } else if (lineCount == 1) {
                    pipsPerLine = 18;
                    baseX -= shiftX;
                } else if (lineCount == 2) {
                    pipsPerLine = 22;
                    baseX -= shiftX * 2;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printFrontTurretArmor(Graphics2D g2d, int totalArmor) {
        if (!largesupporttank.hasNoDualTurret()) {
            float baseX = 453.2f;
            float baseY = 206f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5.5f;
            float shiftY = 5.5f;
            int pipsPerLine = 12;

            Vector<float[]> pipPlotter = new Vector<float[]>(66, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if (((lineCount + 1) % 3) == 0) {
                    pipsPerLine -= 2;
                    baseX += shiftX;
                }

                pointY -= shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);
        }
    }

    private void printTurretArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        if (!largesupporttank.hasNoDualTurret()) {
            float baseX = 453.2f;
            float baseY = 223f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5.5f;
            float shiftY = 5.5f;
            int pipsPerLine = 12;

            Vector<float[]> pipPlotter = new Vector<float[]>(66, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if (((lineCount + 1) % 3) == 0) {
                    pipsPerLine -= 2;
                    baseX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);
        } else if (totalArmor <= 66) {

            float baseX = 465f;
            float baseY = 210f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5.8f;
            float shiftY = 5.8f;
            int pipsPerLine = 8;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(66, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }

                if ((lineCount == 2) || (lineCount == 5)) {
                    pipsPerLine += 2;
                    baseX -= shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 454.5f;
            float baseY = 202f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 4.6f;
            float shiftY = 4.6f;
            int pipsPerLine = 10;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(110, 1);

            for (int lineCount = 0; lineCount < 9; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }

                if ((lineCount == 1) || (lineCount == 5)) {
                    pipsPerLine += 2;
                    baseX -= shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
        }
    }

    private void printFrontLeftArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            if (totalArmor <= 77) {
                float baseX = 425.8f;
                float baseY = 90f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 5.5f;
                float shiftY = 5.5f;
                int pipsPerLine = 1;

                Vector<float[]> pipPlotter = new Vector<float[]>(100, 1);

                for (int lineCount = 0; lineCount < 22; lineCount++) {

                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX += shiftX;
                    }
                    if (lineCount < 4) {
                        pipsPerLine++;
                    }
                    if ((lineCount == 7) || (lineCount == 12)) {
                        pipsPerLine--;
                    }
                    baseX -= 0.35f;
                    pointX = baseX;
                    pointY += shiftY;
                }
                printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);
            } else {
                float baseX = 425.8f;
                float baseY = 88f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 4.5f;
                float shiftY = 4.5f;
                int pipsPerLine = 1;

                Vector<float[]> pipPlotter = new Vector<float[]>(127, 1);

                for (int lineCount = 0; lineCount < 28; lineCount++) {

                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX += shiftX;
                    }
                    if (lineCount < 5) {
                        pipsPerLine++;
                    }
                    if ((lineCount == 9) || (lineCount == 18)) {
                        pipsPerLine--;
                    }
                    baseX -= 0.35f;
                    pointX = baseX;
                    pointY += shiftY;
                }
                printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
            }
        } else if (totalArmor <= 54) {
            float baseX = 426.5f;
            float baseY = 114f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 6f;
            float shiftY = 7f;
            int pipsPerLine = 1;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(55, 1);

            for (int lineCount = 0; lineCount < 14; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if (lineCount < 3) {
                    pipsPerLine++;
                }

                if (lineCount == 9) {
                    pipsPerLine++;
                    baseX -= shiftX;
                }
                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 428f;
            float baseY = 106.5f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5f;
            float shiftY = 5f;
            int pipsPerLine = 1;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

            for (int lineCount = 0; lineCount < 20; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if ((lineCount >= 2) && (lineCount <= 5)) {
                    pipsPerLine++;
                }

                if (lineCount == 9) {
                    pipsPerLine++;
                    baseX -= shiftX;
                }
                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
        }

    }

    private void printRearLeftArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            if (totalArmor <= 67) {
                float baseX = 416f;
                float baseY = 217f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 5.5f;
                float shiftY = 5.5f;
                int pipsPerLine = 3;

                Vector<float[]> pipPlotter = new Vector<float[]>(67, 1);

                for (int lineCount = 0; lineCount < 17; lineCount++) {
                    if ((lineCount == 3) || (lineCount == 9)) {
                        pipsPerLine++;
                    }
                    if (lineCount > 13) {
                        pipsPerLine--;
                    }
                    if (lineCount > 14) {
                        pipsPerLine--;
                    }
                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX += shiftX;
                    }

                    pointX = baseX;
                    pointY += shiftY;
                }

                printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);

            } else {
                float baseX = 416f;
                float baseY = 217.5f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 4.5f;
                float shiftY = 4.5f;
                int pipsPerLine = 4;

                Vector<float[]> pipPlotter = new Vector<float[]>(96, 1);

                for (int lineCount = 0; lineCount < 23; lineCount++) {
                    if ((lineCount == 3) || (lineCount == 9)) {
                        pipsPerLine++;
                    }
                    if (lineCount > 16) {
                        pipsPerLine -= 2;
                    }
                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX += shiftX;
                    }

                    pointX = baseX;
                    pointY += shiftY;
                }

                printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
            }
        } else if (totalArmor <= 57) {
            float baseX = 419.5f;
            float baseY = 216f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 6f;
            float shiftY = 7f;
            int pipsPerLine = 5;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(57, 1);

            for (int lineCount = 0; lineCount < 13; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if ((lineCount == 9) || (lineCount > 10)) {
                    pipsPerLine--;
                } else if (lineCount == 10) {
                    pipsPerLine -= 2;
                }

                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 419.5f;
            float baseY = 216f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5f;
            float shiftY = 5f;
            int pipsPerLine = 6;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(93, 1);

            for (int lineCount = 0; lineCount < 18; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX += shiftX;
                }
                if (lineCount > 11) {
                    pipsPerLine--;
                }

                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
        }

    }

    private void printFrontRightArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            if (totalArmor <= 77) {
                float baseX = 541.8f;
                float baseY = 90f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 5.5f;
                float shiftY = 5.5f;
                int pipsPerLine = 1;

                Vector<float[]> pipPlotter = new Vector<float[]>(100, 1);

                for (int lineCount = 0; lineCount < 22; lineCount++) {

                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX -= shiftX;
                    }
                    if (lineCount < 4) {
                        pipsPerLine++;
                    }
                    if ((lineCount == 7) || (lineCount == 12)) {
                        pipsPerLine--;
                    }
                    baseX += 0.35f;
                    pointX = baseX;
                    pointY += shiftY;
                }

                printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);
            } else {
                float baseX = 542.5f;
                float baseY = 88f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 4.5f;
                float shiftY = 4.5f;
                int pipsPerLine = 1;

                Vector<float[]> pipPlotter = new Vector<float[]>(127, 1);

                for (int lineCount = 0; lineCount < 28; lineCount++) {

                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX -= shiftX;
                    }
                    if (lineCount < 5) {
                        pipsPerLine++;
                    }
                    if ((lineCount == 9) || (lineCount == 18)) {
                        pipsPerLine--;
                    }
                    baseX += 0.35f;
                    pointX = baseX;
                    pointY += shiftY;
                }
                printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
            }
        } else if (totalArmor <= 54) {
            float baseX = 540.5f;
            float baseY = 114f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 6f;
            float shiftY = 7f;
            int pipsPerLine = 1;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(55, 1);

            for (int lineCount = 0; lineCount < 14; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX -= shiftX;
                }
                if (lineCount < 3) {
                    pipsPerLine++;
                }

                if (lineCount == 9) {
                    pipsPerLine++;
                    baseX += shiftX;
                }
                pointX = baseX;
                pointY += shiftY;
            }
            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 540.5f;
            float baseY = 106.5f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5f;
            float shiftY = 5f;
            int pipsPerLine = 1;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

            for (int lineCount = 0; lineCount < 20; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX -= shiftX;
                }
                if ((lineCount >= 2) && (lineCount <= 5)) {
                    pipsPerLine++;
                }

                if (lineCount == 9) {
                    pipsPerLine++;
                    baseX += shiftX;
                }
                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
        }
    }

    private void printRearRightArmor(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        if (!largesupporttank.hasNoDualTurret()) {
            if (totalArmor <= 67) {
                float baseX = 551f;
                float baseY = 217f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 5.5f;
                float shiftY = 5.5f;
                int pipsPerLine = 3;

                Vector<float[]> pipPlotter = new Vector<float[]>(67, 1);

                for (int lineCount = 0; lineCount < 17; lineCount++) {
                    if ((lineCount == 3) || (lineCount == 9)) {
                        pipsPerLine++;
                    }
                    if (lineCount > 13) {
                        pipsPerLine--;
                    }
                    if (lineCount > 14) {
                        pipsPerLine--;
                    }
                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX -= shiftX;
                    }

                    pointX = baseX;
                    pointY += shiftY;
                }

                printArmorPoints(g2d, pipPlotter, totalArmor, 6.5f);

            } else {
                float baseX = 552f;
                float baseY = 217.5f;
                float pointX = baseX;
                float pointY = baseY;
                float shiftX = 4.5f;
                float shiftY = 4.5f;
                int pipsPerLine = 4;

                Vector<float[]> pipPlotter = new Vector<float[]>(96, 1);

                for (int lineCount = 0; lineCount < 23; lineCount++) {
                    if ((lineCount == 3) || (lineCount == 9)) {
                        pipsPerLine++;
                    }
                    if (lineCount > 16) {
                        pipsPerLine -= 2;
                    }
                    for (int point = 0; point < pipsPerLine; point++) {
                        pipPlotter.add(new float[] { pointX, pointY });
                        pointX -= shiftX;
                    }

                    pointX = baseX;
                    pointY += shiftY;
                }

                printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
            }
        } else if (totalArmor <= 57) {
            float baseX = 547.5f;
            float baseY = 216f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 6f;
            float shiftY = 7f;
            int pipsPerLine = 5;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(57, 1);

            for (int lineCount = 0; lineCount < 13; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX -= shiftX;
                }
                if ((lineCount == 9) || (lineCount > 10)) {
                    pipsPerLine--;
                } else if (lineCount == 10) {
                    pipsPerLine -= 2;
                }

                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor);
        } else {
            float baseX = 549f;
            float baseY = 216f;
            float pointX = baseX;
            float pointY = baseY;
            float shiftX = 5f;
            float shiftY = 5f;
            int pipsPerLine = 6;

            if (secondImage) {
                baseY += secondPageMargin;
            }

            Vector<float[]> pipPlotter = new Vector<float[]>(93, 1);

            for (int lineCount = 0; lineCount < 18; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new float[] { pointX, pointY });
                    pointX -= shiftX;
                }
                if (lineCount > 11) {
                    pipsPerLine--;
                }

                pointX = baseX;
                pointY += shiftY;
            }

            printArmorPoints(g2d, pipPlotter, totalArmor, 5.5f);
        }
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 455;
            int baseY = 112;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 8;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                    if (((lineCount == 0) || (lineCount == 1)) && (point == 4)) {
                        pointX += 2 * shiftX;
                    }
                    if ((lineCount == 2) && (point == 3)) {
                        pointX += 4 * shiftX;
                    }
                }
                if (lineCount == 1) {
                    pipsPerLine -= 2;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else {
            int baseX = 460;
            int baseY = 116;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 7;
            int shiftY = 7;
            int pipsPerLine = 8;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printFrontTurretStruct(Graphics2D g2d, int totalArmor) {
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 465;
            int baseY = 143;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 5;
            int shiftY = 5;
            int pipsPerLine = 8;
            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printTurretStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 465;
            int baseY = 262;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 5;
            int shiftY = 5;
            int pipsPerLine = 8;
            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else if (!largesupporttank.hasNoTurret()) {

            int baseX = 469;
            int baseY = 185;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 5;
            int pipsPerLine = 6;

            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printLeftFrontStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 449;
            int baseY = 138;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 2;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 12; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                baseX -= 1;
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else if (largesupporttank.hasNoTurret()) {
            int baseX = 460;
            int baseY = 140;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 3;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else {
            int baseX = 456;
            int baseY = 140;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 5;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 10; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                if (lineCount == 1) {
                    pipsPerLine -= 2;
                } else if ((lineCount == 3) || (lineCount == 5)) {
                    pipsPerLine--;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }

    }

    private void printLeftRearStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 444;
            int baseY = 212;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 2;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 12; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX -= shiftX;
                }
                baseX += 1;
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else if (largesupporttank.hasNoTurret()) {
            int baseX = 460;
            int baseY = 215;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 3;

            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);

        } else {
            int baseX = 456;
            int baseY = 248;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 5;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 5; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printRightFrontStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {

        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 519;
            int baseY = 138;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 2;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 12; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX -= shiftX;
                }
                baseX += 1;
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else if (largesupporttank.hasNoTurret()) {
            int baseX = 495;
            int baseY = 140;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 3;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else {
            int baseX = 513;
            int baseY = 140;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 5;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 10; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX -= shiftX;
                }

                if (lineCount == 1) {
                    pipsPerLine -= 2;
                } else if ((lineCount == 3) || (lineCount == 5)) {
                    pipsPerLine--;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printRightRearStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 524;
            int baseY = 212;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 2;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 12; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }
                baseX -= 1;
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else if (largesupporttank.hasNoTurret()) {
            int baseX = 495;
            int baseY = 215;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 3;

            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 7; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                if (lineCount == 3) {
                    pipsPerLine--;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else {
            int baseX = 490;
            int baseY = 248;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 5;

            Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

            for (int lineCount = 0; lineCount < 5; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printRearStruct(Graphics2D g2d, int totalArmor,
            boolean secondImage) {
        if (!largesupporttank.hasNoDualTurret()) {
            int baseX = 455;
            int baseY = 287;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 5;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 4; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    if ((point == 2) && (lineCount < 2)) {
                        pointX += 6 * shiftX;
                    } else if ((lineCount == 2) && (point == 2)) {
                        pointX += 4 * shiftX;
                    } else if ((lineCount == 3) && (point == 1)) {
                        pointX += 4 * shiftX;
                    } else {
                        pointX += shiftX;
                    }
                }
                if (lineCount == 1) {
                    pipsPerLine = 7;
                }
                if (lineCount == 2) {
                    pipsPerLine = 6;
                    baseX += shiftX;
                }
                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);

        } else if (largesupporttank.hasNoTurret()) {
            int baseX = 460;
            int baseY = 278;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 9;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pipsPerLine -= 2;
                baseX += shiftX;

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        } else {
            int baseX = 458;
            int baseY = 278;
            int pointX = baseX;
            int pointY = baseY;
            int shiftX = 6;
            int shiftY = 6;
            int pipsPerLine = 10;

            Vector<int[]> pipPlotter = new Vector<int[]>(60, 1);

            for (int lineCount = 0; lineCount < 3; lineCount++) {

                for (int point = 0; point < pipsPerLine; point++) {
                    pipPlotter.add(new int[] { pointX, pointY });
                    pointX += shiftX;
                }

                pipsPerLine -= 2;
                baseX += shiftX;

                pointY += shiftY;
                pointX = baseX;
            }

            printISPoints(g2d, pipPlotter, totalArmor);
        }
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints,
            float totalArmor) {
        printArmorPoints(g2d, pipPoints, totalArmor, 7.5f);
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints,
            float totalArmor, float fontSize) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawTankArmorPip(g2d,
                    pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1],
                    fontSize);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printISPoints(Graphics2D g2d, Vector<int[]> pipPoints,
            float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawLSVISPip(g2d, pipPoints.get(currentPip)[0],
                    pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printLargeSupportTankImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(largesupporttank,
                ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(99, img.getHeight(null));
        int drawingX = (int) (235 + xoffset + ((148 - width) / 2));
        int drawingY = (int) (270 + yoffset + ((99 - height) / 2));
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
    }

}