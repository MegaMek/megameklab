/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.ui.Vehicle.Printing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.LargeSupportTank;
import megamek.common.Pilot;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintLargeSupportVehicle implements Printable {

    private LargeSupportTank largesupportank = null;
    private LargeSupportTank largesupportank2 = null;
    private ArrayList<LargeSupportTank> largesupportankList;
    private int secondPageMargin = 373; // How far down the text should be
    private boolean singlePrint = false;
    PrinterJob masterPrintJob;

    // printed for a second vehicle.

    public PrintLargeSupportVehicle(ArrayList<LargeSupportTank> list, boolean singlePrint, PrinterJob masterPrintJob) {
        largesupportankList = list;
        this.singlePrint = singlePrint;
        this.masterPrintJob = masterPrintJob;
        /*
         * if (awtImage != null) { System.out.println("Width: " +
         * awtImage.getWidth(null)); System.out.println("Height: " +
         * awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }

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

        g2d.drawImage(ImageHelper.getRecordSheet(largesupportank, false), 18, 18, 558, 368, null);

        if (largesupportank2 == null) {
            g2d.drawImage(ImageHelper.getTableImage(largesupportank), 18, 18 + secondPageMargin, 558, 368, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(largesupportank2, false), 18, 18 + secondPageMargin, 558, 368, null);
        }

        printLargeSupportTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_FRONT), false);
        printFrontLeftArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_FRONTLEFT), false);
        printFrontRightArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_FRONTRIGHT), false);
        printRearLeftArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_REARLEFT), false);
        printRearRightArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_REARRIGHT), false);
        printRearArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_REAR), false);
        printTurretArmor(g2d, largesupportank.getOArmor(LargeSupportTank.LOC_TURRET), false);

        // Internal Pips
        printFrontStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_FRONT), false);
        printLeftFrontStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_FRONTLEFT), false);
        printRightFrontStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_REARRIGHT), false);
        printLeftRearStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_REARLEFT), false);
        printRightRearStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_REARRIGHT), false);
        printRearStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_REAR), false);
        printTurretStruct(g2d, largesupportank.getOInternal(LargeSupportTank.LOC_TURRET), false);

        if (largesupportank2 != null) {
            // Armor Pips
            printFrontArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_FRONT), true);
            printFrontLeftArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_FRONTLEFT), true);
            printFrontRightArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_FRONTRIGHT), true);
            printRearLeftArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_REARLEFT), true);
            printRearRightArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_REARRIGHT), true);
            printRearArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_REAR), true);
            printTurretArmor(g2d, largesupportank2.getOArmor(LargeSupportTank.LOC_TURRET), true);

            // Internal Pips
            printFrontStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_FRONT), true);
            printLeftFrontStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_FRONTLEFT), true);
            printRightFrontStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_REARRIGHT), true);
            printLeftRearStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_REARLEFT), true);
            printRightRearStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_REARRIGHT), true);
            printRearStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_REAR), true);
            printTurretStruct(g2d, largesupportank2.getOInternal(LargeSupportTank.LOC_TURRET), true);

        }

        printLargeSupportTankImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printLargeSupportTankData(Graphics2D g2d) {
        String largesupportankName = largesupportank.getChassis() + " " + largesupportank.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, largesupportankName, true, 180, 10.0f));
        g2d.drawString(largesupportankName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((largesupportank.getCrew() != null) && !largesupportank.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = largesupportank.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(largesupportank.getWalkMP(false, true)), 79, 144);
        g2d.drawString(Integer.toString(largesupportank.getRunMP(false, true)), 79, 155);

        g2d.drawString(largesupportank.getMovementModeAsString(), 88, 166);

        String engineName = "Fusion Engine";

        switch (largesupportank.getEngine().getEngineType()) {
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

        int tonnage = (int) Math.ceil(largesupportank.getWeight());

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        switch (largesupportank.getTechLevel()) {

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
        if (largesupportank.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        if ((largesupportank.getSource() != null) && (largesupportank.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, largesupportank.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(largesupportank.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", largesupportank.getYear()), 177, nextDataLine);

        }

        // g2d.drawString(Integer.toString(largesupportank.getYear()), 188,
        // 155);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(largesupportank.calculateBattleValue(true, true)), 150, 357);

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(largesupportank.getCost(true)) +
        // " C-bills", 52, 357);

        if (largesupportank.hasBARArmor()) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString(Integer.toString(largesupportank.getBARRating()), 413, 53);
        }

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 62.5f, 374f);

        if (largesupportank2 != null) {
            printLargeSupportTank2Data(g2d);
        } else {
            g2d.drawString("2010", 62.5f, 374f + secondPageMargin);
        }
    }

    private void printLargeSupportTank2Data(Graphics2D g2d) {
        String largesupportankName = largesupportank2.getChassis().toUpperCase() + " " + largesupportank2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, largesupportankName, true, 180, 10.0f));
        g2d.drawString(largesupportankName, 49, 494);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((largesupportank2.getCrew() != null) && !largesupportank2.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = largesupportank2.getCrew();
            g2d.drawString(pilot.getName(), 270, 120 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132 + secondPageMargin);
        }

        g2d.drawString(Integer.toString(largesupportank2.getWalkMP()), 79, 515);
        g2d.drawString(Integer.toString(largesupportank2.getRunMP()), 79, 526);

        g2d.drawString(largesupportank2.getMovementModeAsString(), 88, 537);

        String engineName = "Fusion Engine";

        switch (largesupportank2.getEngine().getEngineType()) {
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

        int tonnage = (int) Math.ceil(largesupportank2.getWeight());

        g2d.drawString(Integer.toString(tonnage), 177, 505);

        int nextDataLine = 155;
        int startLine = 188;
        int lineFeed = 8;

        switch (largesupportank2.getTechLevel()) {

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
        if (largesupportank2.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        if ((largesupportank2.getSource() != null) && (largesupportank2.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, largesupportank2.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(largesupportank2.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", largesupportank2.getYear()), 177, nextDataLine);

        }

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(largesupportank2.calculateBattleValue(true, true)), 150, 728);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(largesupportank2.getCost(true)) + " C-bills", 52, 728);

        if (largesupportank.hasBARArmor()) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + largesupportank.getBARRating(), 400, 64 + secondPageMargin);
        }

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        if ((largesupportank.getArmorType() == EquipmentType.T_ARMOR_STEALTH) || (largesupportank.getArmorType() == EquipmentType.T_ARMOR_REACTIVE) || (largesupportank.getArmorType() == EquipmentType.T_ARMOR_REFLECTIVE) || (largesupportank.getArmorType() == EquipmentType.T_ARMOR_HARDENED)) {
            font = UnitUtil.deriveFont(true, 11.0f);
            g2d.setFont(font);
            g2d.drawString(EquipmentType.getArmorTypeName(largesupportank.getArmorType()), 463, 48);
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);
        }
        g2d.drawString("(" + Integer.toString(largesupportank.getArmor(LargeSupportTank.LOC_FRONT)) + ")", 467, 64);

        g2d.drawString("(" + Integer.toString(largesupportank.getArmor(LargeSupportTank.LOC_FRONTRIGHT)) + ")", 555, 185);

        g2d.drawString("(" + largesupportank.getArmor(LargeSupportTank.LOC_FRONTLEFT) + ")", 386, 110);

        g2d.drawString("(" + Integer.toString(largesupportank.getArmor(LargeSupportTank.LOC_REARRIGHT)) + ")", 555, 302);

        g2d.drawString("(" + largesupportank.getArmor(LargeSupportTank.LOC_REARLEFT) + ")", 386, 228);

        g2d.drawString("(" + Integer.toString(largesupportank.getArmor(LargeSupportTank.LOC_REAR)) + ")", 467, 342);

        if (largesupportank.getOInternal(LargeSupportTank.LOC_TURRET) > 0) {
            g2d.drawString("(" + Integer.toString(largesupportank.getArmor(LargeSupportTank.LOC_TURRET)) + ")", 405, 360);
        }

        if (largesupportank2 != null) {
            if ((largesupportank2.getArmorType() == EquipmentType.T_ARMOR_STEALTH) || (largesupportank2.getArmorType() == EquipmentType.T_ARMOR_REACTIVE) || (largesupportank2.getArmorType() == EquipmentType.T_ARMOR_REFLECTIVE) || (largesupportank2.getArmorType() == EquipmentType.T_ARMOR_HARDENED)) {
                font = UnitUtil.deriveFont(true, 11.0f);
                g2d.setFont(font);
                g2d.drawString(EquipmentType.getArmorTypeName(largesupportank2.getArmorType()), 463, 48 + secondPageMargin);
                font = UnitUtil.deriveFont(true, 9.0f);
                g2d.setFont(font);
            }
            g2d.drawString("(" + Integer.toString(largesupportank2.getArmor(LargeSupportTank.LOC_FRONT)) + ")", 467, 64 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(largesupportank2.getArmor(Tank.LOC_RIGHT)) + ")", 559, 230 + secondPageMargin);

            g2d.drawString("(" + largesupportank2.getArmor(Tank.LOC_LEFT) + ")", 384, 175 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(largesupportank2.getArmor(LargeSupportTank.LOC_REAR)) + ")", 467, 342 + secondPageMargin);

            if (largesupportank2.getOInternal(LargeSupportTank.LOC_TURRET) > 0) {
                g2d.drawString("(" + Integer.toString(largesupportank2.getArmor(LargeSupportTank.LOC_TURRET)) + ")", 455, 186 + secondPageMargin);
            }

        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printLargeSupportTankWeaponsNEquipment(largesupportank, g2d);

        if (largesupportank2 != null) {
            ImageHelper.printLargeSupportTankWeaponsNEquipment(largesupportank2, g2d, secondPageMargin);
        }

    }

    public void print() {

        try {
            for (int pos = 0; pos < largesupportankList.size(); pos++) {
                PrinterJob pj = PrinterJob.getPrinterJob();

                pj.setPrintService(masterPrintJob.getPrintService());
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                largesupportank = largesupportankList.get(pos);
                pj.setJobName(largesupportank.getChassis() + " " + largesupportank.getModel());

                if (!singlePrint && (pos + 1 < largesupportankList.size())) {
                    largesupportank2 = largesupportankList.get(++pos);
                } else {
                    largesupportank2 = null;
                }

                try {
                    pj.print(aset);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    System.gc();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 425.5f;
        float baseY = 77f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 5.8f;
        float shiftY = 5.8f;
        int pipsPerLine = 18;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(130, 1);

        for (int lineCount = 0; lineCount < 9; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
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

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 437f;
        float baseY = 283f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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

    private void printTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 454.5f;
        float baseY = 203f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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
    }

    private void printFrontLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 417.5f;
        float baseY = 112f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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
    }

    private void printRearLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float baseX = 410.5f;
        float baseY = 210f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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
    }

    private void printFrontRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 531.5f;
        float baseY = 112f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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
    }

    private void printRearRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float baseX = 538.5f;
        float baseY = 210f;
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
                pipPlotter.add(new float[]
                    { pointX, pointY });
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
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int baseX = 450;
        int baseY = 113;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 7;
        int shiftY = 7;
        int pipsPerLine = 8;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 3; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        int baseX = 461;
        int baseY = 176;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 5;
        int pipsPerLine = 6;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 4; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLeftFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {

        int baseX = 448;
        int baseY = 135;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 6;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 10; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
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

    private void printLeftRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int baseX = 448;
        int baseY = 240;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 6;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 5; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 3) {
                pipsPerLine--;
                baseX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRightFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int baseX = 480;
        int baseY = 135;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 6;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 10; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                pipsPerLine -= 2;
                baseX += shiftX * 2;
            } else if ((lineCount == 3) || (lineCount == 5)) {
                pipsPerLine--;
                baseX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRightRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int baseX = 480;
        int baseY = 240;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 6;
        int pipsPerLine = 5;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 5; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 3) {
                pipsPerLine--;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int baseX = 449;
        int baseY = 269;
        int pointX = baseX;
        int pointY = baseY;
        int shiftX = 6;
        int shiftY = 6;
        int pipsPerLine = 10;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<int[]> pipPlotter = new Vector<int[]>(24, 1);

        for (int lineCount = 0; lineCount < 3; lineCount++) {

            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new int[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            pipsPerLine -= 2;
            baseX += shiftX;

            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        printArmorPoints(g2d, pipPoints, totalArmor, 8.0f);
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor, float fontSize) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawTankArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1], fontSize);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printISPoints(Graphics2D g2d, Vector<int[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawLSVISPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printLargeSupportTankImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(largesupportank, ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(99, img.getHeight(null));
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 270 + ((99 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

        if (largesupportank2 != null) {
            img = ImageHelper.getFluffImage(largesupportank2, ImageHelper.imageVehicle);
            width = Math.min(148, img.getWidth(null));
            height = Math.min(99, img.getHeight(null));
            drawingX = 235 + ((148 - width) / 2);
            drawingY = 268 + ((99 - height) / 2) + secondPageMargin;
            g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
        }
    }

}