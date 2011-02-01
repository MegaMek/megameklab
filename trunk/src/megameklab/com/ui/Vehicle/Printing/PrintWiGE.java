/*
 * MegaMekLab - Copyright (C) 2010
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
import megamek.common.Pilot;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintWiGE implements Printable {

    private Tank tank = null;
    private Tank tank2 = null;
    private ArrayList<Tank> tankList;
    private int secondPageMargin = 373; // How far down the text should be
    private boolean singlePrint = false;
    PrinterJob masterPrintJob;

    // printed for a second vehicle.

    public PrintWiGE(ArrayList<Tank> list, boolean singlePrint, PrinterJob masterPrintJob) {
        tankList = list;
        this.singlePrint = singlePrint;
        this.masterPrintJob = masterPrintJob;

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

        g2d.drawImage(ImageHelper.getRecordSheet(tank, false), 18, 18, 558, 368, null);
        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawImage(ImageHelperVehicle.getTurretImage(tank), 436, 173, 77, 96, null);
            g2d.drawImage(ImageHelperVehicle.getTurretLabelImage(), 297, 248, 34, 11, null);
            g2d.setFont(UnitUtil.deriveFont(true, 8.0f));
            g2d.drawString("Turret Armor", 517, 51);
        }

        if (tank2 == null) {
            g2d.drawImage(ImageHelperVehicle.getTableImage(tank), 18, 18 + secondPageMargin, 558, 368, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(tank2, false), 18, 18 + secondPageMargin, 558, 368, null);
            if (tank2.getOInternal(Tank.LOC_TURRET) > 0) {
                g2d.drawImage(ImageHelperVehicle.getTurretImage(tank2), 436, 173 + secondPageMargin, 77, 96, null);
                g2d.drawImage(ImageHelperVehicle.getTurretLabelImage(), 297, 248 + secondPageMargin, 34, 11, null);
                g2d.setFont(UnitUtil.deriveFont(true, 8.0f));
                g2d.drawString("Turret Armor", 517, 51 + secondPageMargin);
            }
        }

        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        // Armor Pips
        printFrontArmor(g2d, tank.getOArmor(Tank.LOC_FRONT), false);
        printLeftArmor(g2d, tank.getOArmor(Tank.LOC_LEFT), false);
        printRightArmor(g2d, tank.getOArmor(Tank.LOC_RIGHT), false);
        printRearArmor(g2d, tank.getOArmor(Tank.LOC_REAR), false);
        printTurretArmor(g2d, tank.getOArmor(Tank.LOC_TURRET), false);

        // Internal Pips
        printFrontStruct(g2d, tank.getOInternal(Tank.LOC_FRONT), false);
        printLeftStruct(g2d, tank.getOInternal(Tank.LOC_LEFT), false);
        printRightStruct(g2d, tank.getOInternal(Tank.LOC_RIGHT), false);
        printRearStruct(g2d, tank.getOInternal(Tank.LOC_REAR), false);

        printTurretStruct(g2d, tank.getOInternal(Tank.LOC_TURRET), false);

        if (tank2 != null) {
            // Armor Pips
            printFrontArmor(g2d, tank2.getOArmor(Tank.LOC_FRONT), true);
            printLeftArmor(g2d, tank2.getOArmor(Tank.LOC_LEFT), true);
            printRightArmor(g2d, tank2.getOArmor(Tank.LOC_RIGHT), true);
            printRearArmor(g2d, tank2.getOArmor(Tank.LOC_REAR), true);
            printTurretArmor(g2d, tank2.getOArmor(Tank.LOC_TURRET), true);

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
        g2d.drawString(tankName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((tank.getCrew() != null) && !tank.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = tank.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(tank.getWalkMP()), 79, 144);
        g2d.drawString(Integer.toString(tank.getRunMP()), 79, 155);

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
            DecimalFormat myFormatter = new DecimalFormat("#.###");
            g2d.drawString(myFormatter.format(tank.getWeight()), 177, 134);
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
        if (tank.isClan()) {
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
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(tank.calculateBattleValue(true, true)), 150, 357);

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(tank.getCost(true)) + " C-bills",
        // 52, 357);

        if (UnitUtil.hasBAR(tank)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(tank), 400, 64);
        }

        font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);

        g2d.drawString("2010", 62.5f, 374f);

        if (tank2 != null) {
            printTank2Data(g2d);
        } else {
            g2d.drawString("2010", 62.5f, 374f + secondPageMargin);
        }
    }

    private void printTank2Data(Graphics2D g2d) {
        String tankName = tank2.getChassis().toUpperCase() + " " + tank2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 494);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((tank2.getCrew() != null) && !tank2.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = tank2.getCrew();
            g2d.drawString(pilot.getName(), 270, 120 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132 + secondPageMargin);
        }

        g2d.drawString(Integer.toString(tank2.getWalkMP()), 79, 144 + secondPageMargin);
        g2d.drawString(Integer.toString(tank2.getRunMP()), 79, 155 + secondPageMargin);

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
            DecimalFormat myFormatter = new DecimalFormat("#.###");
            g2d.drawString(myFormatter.format(tank2.getWeight()), 177, 505);
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
        if (tank2.isClan()) {
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
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(tank2.calculateBattleValue(true, true)), 150, 728);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(tank2.getCost(true)) + " C-bills", 52, 728);

        if (UnitUtil.hasBAR(tank2)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(tank2), 400, 64 + secondPageMargin);
        }

        font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(tank), 463, 48);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_FRONT)) + ")", 467, 60);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_RIGHT) + ")", 554, 210);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_LEFT) + ")", 384, 155);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_REAR)) + ")", 467, 342);

        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET)) + ")", 535, 60);
        }

        if (tank2 != null) {
            font = UnitUtil.deriveFont(true, 11.0f);
            g2d.setFont(font);
            g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(tank2), 463, 48 + secondPageMargin);
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);
            g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_FRONT)) + ")", 467, 60 + secondPageMargin);

            g2d.drawString("(" + tank2.getArmor(Tank.LOC_RIGHT) + ")", 554, 210 + secondPageMargin);

            g2d.drawString("(" + tank2.getArmor(Tank.LOC_LEFT) + ")", 384, 155 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_REAR)) + ")", 467, 342 + secondPageMargin);

            if (tank2.getOInternal(Tank.LOC_TURRET) > 0) {
                g2d.drawString("(" + Integer.toString(tank2.getArmor(Tank.LOC_TURRET)) + ")", 535, 60 + secondPageMargin);
            }
        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printTankWeaponsNEquipment(tank, g2d);

        if (tank2 != null) {
            ImageHelperVehicle.printTankWeaponsNEquipment(tank2, g2d, secondPageMargin);
        }

    }

    public void print() {

        try {
            for (int pos = 0; pos < tankList.size(); pos++) {
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

                tank = tankList.get(pos);
                pj.setJobName(tank.getChassis() + " " + tank.getModel());

                if (!singlePrint && (pos + 1 < tankList.size())) {
                    tank2 = tankList.get(++pos);
                } else {
                    tank2 = null;
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

        if (totalArmor < 1) {
            return;
        }

        float baseX = 465.0f;
        float baseY = 75.0f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 3;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(86, 1);

        for (int lineCount = 1; lineCount <= 12; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount < 8) {
                pipsPerLine += 1;
                baseX -= shiftX / 2;
            } else if (lineCount > 9) {
                pipsPerLine -= 1;
                baseX += shiftX / 2;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 426;
        float baseY = 289f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6f;
        float shiftY = 6f;
        int pipsPerLine = 16;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(86, 1);

        for (int lineCount = 1; lineCount <= 5; lineCount++) {
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

    private void printTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float baseX = 453f;
        float baseY = 232f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6f;
        float shiftY = 6f;
        int pipsPerLine = 7;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(86, 1);

        for (int lineCount = 1; lineCount <= 6; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if ((lineCount == 2) || (lineCount == 4)) {
                baseX -= shiftX;
                pipsPerLine += 2;
            }
            pointY += shiftY;
            pointX = baseX;
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float baseX = 434f;
        float baseY = 155.5f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 2;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(86, 1);

        for (int lineCount = 1; lineCount <= 20; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                pipsPerLine += 1;
                baseX -= shiftX / 2;
            } else if ((lineCount == 2)) {
                pipsPerLine += 1;
                baseX -= shiftX;
            } else if (lineCount == 3) {
                pipsPerLine += 2;
                baseX -= shiftX;
            } else if (lineCount == 4) {
                pipsPerLine -= 1;
                baseX -= shiftX / 2;
            } else if (lineCount == 5) {
                baseX -= shiftX / 2;
            } else if ((lineCount > 6) && (lineCount < 9)) {
                baseX -= shiftX / 2;
            } else if (lineCount == 10) {
                baseX -= shiftX / 2;
            } else if (lineCount == 12) {
                pipsPerLine += 1;
                baseX -= shiftX * 1.5;
            } else if ((lineCount == 14) || (lineCount == 18)) {
                pipsPerLine -= 1;
            } else if (lineCount == 19) {
                pipsPerLine = 1;
                baseX += shiftX * 1.5;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float baseX = 502f;
        float baseY = 157.5f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 2;

        if (secondImage) {
            baseY += secondPageMargin;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(86, 1);

        for (int lineCount = 1; lineCount <= 20; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                pipsPerLine += 1;
            } else if ((lineCount == 2)) {
                pipsPerLine += 1;
            } else if (lineCount == 3) {
                pipsPerLine += 2;
                baseX -= shiftX * 1.25f;
            } else if (lineCount == 4) {
                pipsPerLine -= 1;
                baseX += shiftX;
            } else if (lineCount == 5) {
                baseX += shiftX * .75f;
            } else if ((lineCount > 6) && (lineCount < 9)) {
                baseX += shiftX / 2;
            } else if (lineCount == 10) {
                baseX += shiftX / 2;
            } else if (lineCount == 12) {
                pipsPerLine += 1;
                baseX += shiftX * .5;
            } else if ((lineCount == 14) || (lineCount == 18)) {
                pipsPerLine -= 1;
                baseX += shiftX;
            } else if (lineCount == 19) {
                pipsPerLine = 1;
                baseX += shiftX * 2f;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[]
            { 454, 155 };
        int[] bottomColumn = new int[]
            { 454, 163 };
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
            ImageHelperVehicle.drawWiGEISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawWiGEISPip(g2d, bottomColumn[0], bottomColumn[1]);
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
            ImageHelperVehicle.drawWiGEISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawWiGEISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 446, 194 };
        int[] pipShift = new int[]
            { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawWiGEISPip(g2d, column[0], column[1]);
            column[0] -= pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 496, 194 };
        int[] pipShift = new int[]
            { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawWiGEISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 420, 272 };
        int[] pipShift = new int[]
            { 7, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        column[0] += pipShift[0] * ((10 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawWiGEISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
        }
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        printArmorPoints(g2d, pipPoints, totalArmor, 8.0f);
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor, float fontSize) {
        ImageHelperVehicle.printArmorPoints(g2d, pipPoints, totalArmor, false);
        /*pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperVehicle.drawTankArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1], fontSize);
            if (--totalArmor <= 0) {
                return;
            }
        }*/
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