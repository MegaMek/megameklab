/*
 * MegaMekLab - Copyright (C) 2008
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
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.util.Calendar;
import java.util.Vector;

import com.kitfox.svg.SVGException;

import megamek.common.Crew;
import megamek.common.Engine;
import megamek.common.MiscType;
import megamek.common.SupportVTOL;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.VTOL;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintVTOL implements Printable {

    private VTOL vtol = null;

    public PrintVTOL(VTOL vtol) {
        this.vtol = vtol;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        System.gc();

        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/VTOLCommonTemplate.svg")).render(g2d);
            if (vtol.hasNoTurret()) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/VTOLNoTurretTemplate.svg")).render(g2d);
            } else {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/VTOLTurretTemplate.svg")).render(g2d);
            }
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/VTOLCatalystLogo.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }

        printVTOLData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, vtol.getOArmor(Tank.LOC_FRONT));
        printLeftArmor(g2d, vtol.getOArmor(Tank.LOC_LEFT));
        printRightArmor(g2d, vtol.getOArmor(Tank.LOC_RIGHT));
        printRearArmor(g2d, vtol.getOArmor(Tank.LOC_REAR));
        printRotorArmor(g2d, vtol.getOArmor(VTOL.LOC_ROTOR));
        if (!vtol.hasNoTurret()) {
            printTurretArmor(g2d, vtol.getOArmor(VTOL.LOC_TURRET));
        }

        // Internal Pips
        printFrontStruct(g2d, vtol.getOInternal(Tank.LOC_FRONT));
        printLeftStruct(g2d, vtol.getOInternal(Tank.LOC_LEFT));
        printRightStruct(g2d, vtol.getOInternal(Tank.LOC_RIGHT));
        printRearStruct(g2d, vtol.getOInternal(Tank.LOC_REAR));
        printRotorStruct(g2d, vtol.getOInternal(VTOL.LOC_ROTOR));
        if (!vtol.hasNoTurret()) {
            printTurretStruct(g2d, vtol.getOInternal(VTOL.LOC_TURRET));
        }

        printVTOLImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(),
                pageFormat.getImageableHeight());

    }

    private void printVTOLData(Graphics2D g2d) {
        String VTOLName = vtol.getChassis() + " " + vtol.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, VTOLName, true, 180, 10.0f));
        g2d.drawString(VTOLName, 55, 119);

        Font font = UnitUtil.deriveFont(true, 13.5f);
        g2d.setFont(font);

        if (vtol instanceof SupportVTOL) {
            g2d.drawString("SUPPORT", 73, 86.5f);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((vtol.getCrew() != null)
                && !vtol.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = vtol.getCrew();
            g2d.drawString(pilot.getName(), 276, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 301, 131);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 371, 132);
        }

        g2d.drawString(Integer.toString(vtol.getWalkMP()), 85, 144);
        if (!vtol.hasWorkingMisc(MiscType.F_MASC, MiscType.S_JETBOOSTER)) {
            g2d.drawString(Integer.toString(vtol.getRunMP()), 85, 154);
        } else {
            int mascMP = vtol.getRunMP();
            g2d.drawString(Integer.toString(vtol.getRunMPwithoutMASC()) + " ["
                    + mascMP + "]", 85, 154);
        }

        String engineName = "Fusion Engine";

        switch (vtol.getEngine().getEngineType()) {
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

        g2d.drawString(engineName, 85, 165);

        if (vtol.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(vtol.getWeight());
            g2d.drawString(Integer.toString(tonnage), 183, 133);
        } else {
            g2d.drawString(String.format("%1$,d", vtol.getWeight()), 183, 133);
        }

        int nextDataLine = 153;
        float startLine = 200;
        int lineFeed = 8;

        String techBase = "Inner Sphere";

        if (vtol.isMixedTech()) {
            if (vtol.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (vtol.isClan()) {
            techBase = "Clan";
        }

        font = UnitUtil.getNewFont(g2d, techBase, false, 52, 8);
        g2d.setFont(font);
        g2d.drawString(techBase, 183, 144.5f);

        switch (vtol.getTechLevel()) {

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


        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((vtol.getSource() != null)
                && (vtol.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 150, nextDataLine);

            font = UnitUtil.getNewFont(g2d, vtol.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(vtol.getSource(), 183, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 150, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", vtol.getYear()), 183,
                    nextDataLine);

        }

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = vtol.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 365);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(
                    String.format("%1$,d",
                            vtol.calculateBattleValue(true, true)), 50, 365);
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(vtol.getCost(true)) + " C-bills",
        // 52, 357);

        if (UnitUtil.hasBAR(vtol)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(vtol), 400, 55);
        }

        font = UnitUtil.deriveFont(true, 6.5f);
        g2d.setFont(font);

        g2d.drawString(
                Integer.toString(Calendar.getInstance().get(Calendar.YEAR)),
                39.5f, 763.3f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);
        ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(vtol), g2d.getFont(), 438, 45);
        //g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(vtol), 463,
                //45);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_FRONT)),
                480 + (vtol.hasNoTurret() ? 0 : 60),
                64 + (vtol.hasNoTurret() ? 0 : 48.5f));

        if (vtol.hasNoTurret()) {
            g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_RIGHT)), 544,
                    262 + (vtol.hasNoTurret() ? 0 : 36.5f));

            g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_LEFT)), 417,
                    195 + (vtol.hasNoTurret() ? 0 : 36.5f));
        } else {
            AffineTransform at = new AffineTransform();
            at.setToRotation(Math.toRadians(90), 547, 292);
            AffineTransform oldat = g2d.getTransform();
            g2d.transform(at);
            g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_RIGHT)), 547,
                    292);
            g2d.setTransform(oldat);

            at.setToRotation(Math.toRadians(270), 427, 232);
            g2d.transform(at);
            g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_LEFT)), 427,
                    232);
            g2d.setTransform(oldat);
        }


        g2d.drawString(Integer.toString(vtol.getArmor(Tank.LOC_REAR)), 482,
                344 + (vtol.hasNoTurret() ? 0 : 33));

        g2d.drawString(Integer.toString(vtol.getArmor(VTOL.LOC_ROTOR)),
                548 + (vtol.hasNoTurret() ? 0 : 8),
                140 + (vtol.hasNoTurret() ? 0 : 36.5f));

        if (!vtol.hasNoTurret()) {
            g2d.drawString(Integer.toString(vtol.getArmor(VTOL.LOC_TURRET)),
                    444, 75);
        }

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printVTOLWeaponsNEquipment(vtol, g2d, 6, 9);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {
        float turretVTOLOffset = vtol.hasNoTurret() ? 0 : 25;
        float[] topColumn = { 471, 82 + turretVTOLOffset };
        float[] middleColumn = { 466, 88 + turretVTOLOffset };
        float[] pipShift = { 6, 6 };

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(5, totalArmor);
        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                    topColumn[1], 8.0f);
            topColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, middleColumn[0],
                    middleColumn[1], 8.0f);
            middleColumn[0] += pipShift[0];
            if ((pos % 7) == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
            if (pos == 28) {
                middleColumn[0] += pipShift[0];
            }
        }
    }

    private void printTurretArmor(Graphics2D g2d, int totalArmor) {
        if (totalArmor < 1) {
            return;
        }
        if (totalArmor <= 10) {
            float[] topColumn = { 473, 62 };
            float[] middleColumn = { 473, 70 };
            float[] pipShift = { 6, 6 };

            int pips = Math.min(5, totalArmor);
            totalArmor -= pips;
            for (int pos = 1; pos <= pips; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                        topColumn[1], 8.0f);
                topColumn[0] += pipShift[0];
            }

            for (int pos = 1; pos <= totalArmor; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, middleColumn[0],
                        middleColumn[1], 8.0f);
                middleColumn[0] += pipShift[0];
            }
        } else  {
            float[] topColumn = { 473.5f, 57.5f };
            float[] middleColumn = { 471.5f, 64f };
            float[] midldeColumn2 = { 466f, 70.5f};
            float[] bottomColumn = { 469.5f, 77f};
            float[] pipShift = { 5.5f, 5.5f };
            int pips = Math.min(5, totalArmor);
            totalArmor -= pips;
            for (int pos = 1; pos <= pips; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                        topColumn[1], 7.0f);
                topColumn[0] += pipShift[0];
            }
            pips = Math.min(6, totalArmor);
            totalArmor -= pips;
            for (int pos = 1; pos <= pips; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, middleColumn[0],
                        middleColumn[1], 7.0f);
                middleColumn[0] += pipShift[0];
            }
            pips = Math.min(8, totalArmor);
            totalArmor -= pips;
            for (int pos = 1; pos <= pips; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, midldeColumn2[0],
                        midldeColumn2[1], 7.0f);
                midldeColumn2[0] += pipShift[0];
            }
            for (int pos = 1; pos <= totalArmor; pos++) {
                ImageHelperVehicle.drawTankArmorPip(g2d, bottomColumn[0],
                        bottomColumn[1], 7.0f);
                bottomColumn[0] += pipShift[0];
            }

        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float turretVTOLOffset = vtol.hasNoTurret() ? 0 : 30.5f;
        float[] topColumn = new float[] { 485f, 256 + turretVTOLOffset };
        float[] pipShift = new float[] { 6, 6 };
        float fontSize = 8.0f;

        if (totalArmor > 11) {
            fontSize = 7;
            pipShift[0] = pipShift[1] = 5;
        }

        if (totalArmor > 13) {
            fontSize = 6;
            pipShift[0] = pipShift[1] = 4.5f;
        }

        if (totalArmor > 15) {
            fontSize = 7;
            float[] firstColStart = new float[] { 481, 256 + turretVTOLOffset };
            float[] firstColEnd = new float[] { 481, 307 + turretVTOLOffset };
            float[] secondColStart = new float[] { 489, 256 + turretVTOLOffset };
            float[] secondColEnd = new float[] { 489, 307 + turretVTOLOffset };
            int firstColPips = totalArmor / 2;
            int secondColPips = (totalArmor / 2);
            Vector<float[]> first = ImageHelper.getPointsAlongLine(
                    firstColStart, firstColEnd, firstColPips);
            Vector<float[]> second = ImageHelper.getPointsAlongLine(
                    secondColStart, secondColEnd, secondColPips);
            for (float[] pip : first) {
                ImageHelperVehicle.drawTankArmorPip(g2d, pip[0], pip[1],
                        fontSize);
            }
            for (float[] pip : second) {
                ImageHelperVehicle.drawTankArmorPip(g2d, pip[0], pip[1],
                        fontSize);
            }
            if ((totalArmor % 2) != 0) {
                ImageHelperVehicle.drawTankArmorPip(g2d, 485,
                        312 + turretVTOLOffset, fontSize);
            }
            return;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                    topColumn[1], fontSize);
            topColumn[1] += pipShift[1];
        }

    }

    private void printRotorArmor(Graphics2D g2d, int totalArmor) {
        float turretVTOLOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float[][] armor = { { 405, 154.5f + turretVTOLOffset },
                { 562, 154.5f + turretVTOLOffset } };
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, armor[pos][0],
                    armor[pos][1]);
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : -10;
        float[] topColumn = new float[] { 454 + turretVTOLXOffset,
                106 + turretVTOLYOffset };
        float[] pipShift = new float[] { 6, 6 };
        float fontSize = 8.0f;

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                    topColumn[1], fontSize);
            switch (pos) {
                case 0:
                    topColumn[1] += pipShift[1];
                    break;
                case 2:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += pipShift[0];
                    break;
                case 3:
                    pipShift[0] *= -1;
                    topColumn[0] += pipShift[0];
                    break;
                case 5:
                case 8:
                case 11:
                case 14:
                case 19:
                case 21:
                case 23:
                case 25:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 17:
                    topColumn[1] += pipShift[1] * 3.75;
                    topColumn[0] += pipShift[0] * -2.25;
                    break;
                case 27:
                    pipShift[0] *= -1;
                    topColumn[1] += pipShift[1];
                    break;
                case 28:
                    topColumn[0] += pipShift[0];
                    break;
                case 29:
                case 30:
                case 31:
                    topColumn[1] += pipShift[1];
                    break;
                default:
                    topColumn[0] += pipShift[0];
                    break;
            }
        }
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float turretVTOLOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 10;
        float[] topColumn = new float[] { 513 + turretVTOLXOffset,
                106 + turretVTOLOffset };
        float[] pipShift = new float[] { 6, 6 };
        float fontSize = 8.0f;

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0],
                    topColumn[1], fontSize);

            switch (pos) {
                case 0:
                    topColumn[1] += pipShift[1];
                    break;
                case 2:
                    topColumn[1] += pipShift[1];
                    topColumn[0] -= pipShift[0];
                    break;
                case 3:
                    pipShift[0] *= -1;
                    topColumn[0] -= pipShift[0];
                    break;
                case 5:
                case 8:
                case 11:
                case 14:
                case 19:
                case 21:
                case 23:
                case 25:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 17:
                    topColumn[1] += pipShift[1] * 3.75;
                    topColumn[0] -= pipShift[0] * -2.10;
                    break;
                case 27:
                    pipShift[0] *= -1;
                    topColumn[1] += pipShift[1];
                    break;
                case 28:
                    topColumn[0] -= pipShift[0];
                    break;
                case 29:
                case 30:
                case 31:
                    topColumn[1] += pipShift[1];
                    break;
                default:
                    topColumn[0] -= pipShift[0];
                    break;
            }
        }
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor) {
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 1;
        float[][] struct = {
                { 483.5f + turretVTOLXOffset, 119 + turretVTOLYOffset },
                { 483.5f + turretVTOLXOffset, 128 + turretVTOLYOffset },
                { 483.5f + turretVTOLXOffset, 137 + turretVTOLYOffset } };

        if (totalArmor > 3) {
            struct = new float[][] {
                    { 477 + turretVTOLXOffset, 119 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 128 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 137 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 119 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 128 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 137 + turretVTOLYOffset } };

        }

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }

    }

    private void printRotorStruct(Graphics2D g2d, int totalArmor) {
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 1;
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float[][] struct = {
                { 483.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                { 433.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                { 533.5f + turretVTOLXOffset, 148 + turretVTOLYOffset } };

        if (totalArmor > 3) {
            struct = new float[][] {
                    { 483.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                    { 433.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                    { 533.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                    { 463.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                    { 453.5f + turretVTOLXOffset, 148 + turretVTOLYOffset },
                    { 513.5f + turretVTOLXOffset, 148 + turretVTOLYOffset } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }

    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor) {
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 1f;
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float[][] struct = {
                { 477 + turretVTOLXOffset, 164 + turretVTOLYOffset },
                { 477 + turretVTOLXOffset, 178 + turretVTOLYOffset },
                { 477 + turretVTOLXOffset, 192 + turretVTOLYOffset } };

        if (totalArmor > 3) {
            struct = new float[][] {
                    { 477 + turretVTOLXOffset, 164 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 171 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 178 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 185 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 192 + turretVTOLYOffset },
                    { 477 + turretVTOLXOffset, 199 + turretVTOLYOffset } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }
    }

    private void printTurretStruct(Graphics2D g2d, int totalArmor) {
        float[][] struct = { { 476, 79 }, { 485, 79 }, { 494, 79 },
                { 476, 86 }, { 485, 86 }, { 494, 86 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }

    }

    private void printRightStruct(Graphics2D g2d, int totalArmor) {
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 1f;
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float[][] struct = {
                { 490 + turretVTOLXOffset, 164 + turretVTOLYOffset },
                { 490 + turretVTOLXOffset, 178 + turretVTOLYOffset },
                { 490 + turretVTOLXOffset, 192 + turretVTOLYOffset } };

        if (totalArmor > 3) {
            struct = new float[][] {
                    { 490 + turretVTOLXOffset, 164 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 171 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 178 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 185 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 192 + turretVTOLYOffset },
                    { 490 + turretVTOLXOffset, 199 + turretVTOLYOffset } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }

    }

    private void printRearStruct(Graphics2D g2d, int totalArmor) {
        float turretVTOLXOffset = vtol.hasNoTurret() ? 0 : 1;
        float turretVTOLYOffset = vtol.hasNoTurret() ? 0 : 36.5f;
        float[][] struct = {
                { 483.5f + turretVTOLXOffset, 216 + turretVTOLYOffset },
                { 483.5f + turretVTOLXOffset, 225 + turretVTOLYOffset },
                { 483.5f + turretVTOLXOffset, 234 + turretVTOLYOffset } };

        if (totalArmor > 3) {
            struct = new float[][] {
                    { 479 + turretVTOLXOffset, 216 + turretVTOLYOffset },
                    { 479 + turretVTOLXOffset, 225 + turretVTOLYOffset },
                    { 479 + turretVTOLXOffset, 234 + turretVTOLYOffset },
                    { 488.5f + turretVTOLXOffset, 216 + turretVTOLYOffset },
                    { 488.5f + turretVTOLXOffset, 225 + turretVTOLYOffset },
                    { 488.5f + turretVTOLXOffset, 234 + turretVTOLYOffset } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0],
                    struct[pos][1]);
        }
    }

    private void printVTOLImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(vtol, ImageHelper.imageVehicle);
        int width, height, drawingX, drawingY;
        if (vtol.hasNoTurret()) {
            width = Math.min(148, img.getWidth(null));
            height = Math.min(120, img.getHeight(null));
            drawingX = 246 + ((148 - width) / 2);
            drawingY = 258 + ((120 - height) / 2);
        } else {
            width = Math.min(148, img.getWidth(null));
            height = Math.min(97, img.getHeight(null));
            drawingX = 246 + ((148 - width) / 2);
            drawingY = 280 + ((97 - height) / 2);
        }
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }
}