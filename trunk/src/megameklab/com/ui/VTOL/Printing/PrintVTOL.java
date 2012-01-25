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

package megameklab.com.ui.VTOL.Printing;

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
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.Engine;
import megamek.common.MiscType;
import megamek.common.Pilot;
import megamek.common.SupportVTOL;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.VTOL;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintVTOL implements Printable {

    private VTOL vtol = null;
    private VTOL vtol2 = null;
    private ArrayList<VTOL> vtolList;
    private int secondPageMargin = 373; // How far down the text should be
    private boolean singlePrint = false;
    PrinterJob masterPrintJob;

    // printed for a second vehicle.

    public PrintVTOL(ArrayList<VTOL> list, boolean singlePrint, PrinterJob masterPrintJob) {
        vtolList = list;
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

        g2d.drawImage(ImageHelper.getRecordSheet(vtol, false), 18, 18, 558, 368, null);

        if (vtol2 == null) {
            g2d.drawImage(ImageHelperVehicle.getTableImage(vtol), 18, 18 + secondPageMargin, 558, 366, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(vtol2, false), 18, 18 + secondPageMargin, 558, 366, null);
        }

        printVTOLData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, vtol.getOArmor(Tank.LOC_FRONT), false);
        printLeftArmor(g2d, vtol.getOArmor(Tank.LOC_LEFT), false);
        printRightArmor(g2d, vtol.getOArmor(Tank.LOC_RIGHT), false);
        printRearArmor(g2d, vtol.getOArmor(Tank.LOC_REAR), false);
        printRotorArmor(g2d, vtol.getOArmor(VTOL.LOC_ROTOR), false);

        // Internal Pips
        printFrontStruct(g2d, vtol.getOInternal(Tank.LOC_FRONT), false);
        printLeftStruct(g2d, vtol.getOInternal(Tank.LOC_LEFT), false);
        printRightStruct(g2d, vtol.getOInternal(Tank.LOC_RIGHT), false);
        printRearStruct(g2d, vtol.getOInternal(Tank.LOC_REAR), false);
        printRotorStruct(g2d, vtol.getOInternal(VTOL.LOC_ROTOR), false);

        if (vtol2 != null) {
            // Armor Pips
            printFrontArmor(g2d, vtol2.getOArmor(Tank.LOC_FRONT), true);
            printLeftArmor(g2d, vtol2.getOArmor(Tank.LOC_LEFT), true);
            printRightArmor(g2d, vtol2.getOArmor(Tank.LOC_RIGHT), true);
            printRearArmor(g2d, vtol2.getOArmor(Tank.LOC_REAR), true);
            printRotorArmor(g2d, vtol2.getOArmor(VTOL.LOC_ROTOR), true);

            // Internal Pips
            printFrontStruct(g2d, vtol2.getOInternal(Tank.LOC_FRONT), true);
            printLeftStruct(g2d, vtol2.getOInternal(Tank.LOC_LEFT), true);
            printRightStruct(g2d, vtol2.getOInternal(Tank.LOC_RIGHT), true);
            printRearStruct(g2d, vtol2.getOInternal(Tank.LOC_REAR), true);
            printRotorStruct(g2d, vtol2.getOInternal(VTOL.LOC_ROTOR), true);

        }

        printVTOLImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printVTOLData(Graphics2D g2d) {
        String VTOLName = vtol.getChassis() + " " + vtol.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, VTOLName, true, 180, 10.0f));
        g2d.drawString(VTOLName, 49, 120);

        Font font = UnitUtil.deriveFont(true, 15.0f);
        g2d.setFont(font);

        if (vtol instanceof SupportVTOL) {
            g2d.drawString("Support", 70, 89);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((vtol.getCrew() != null) && !vtol.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = vtol.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(vtol.getWalkMP()), 79, 144);
        if (!vtol.hasWorkingMisc(MiscType.F_MASC, MiscType.S_JETBOOSTER)) {
            g2d.drawString(Integer.toString(vtol.getRunMP()), 79, 155);
        } else {
            int mascMP = vtol.getRunMP();
            g2d.drawString(Integer.toString(vtol.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
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

        g2d.drawString(engineName, 79, 164);

        if (vtol.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(vtol.getWeight());

            g2d.drawString(Integer.toString(tonnage), 177, 134);
        } else {
            // DecimalFormatSymbols unusualSymbols =
            // new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,d", vtol.getWeight()), 177, 134);
        }

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        switch (vtol.getTechLevel()) {

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

        if (vtol.isMixedTech()) {
            if (vtol.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (vtol.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        if ((vtol.getSource() != null) && (vtol.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, vtol.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(vtol.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", vtol.getYear()), 177, nextDataLine);

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
            g2d.drawString("BV: ", 35, 357);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", vtol.calculateBattleValue(true, true)), 50, 357);
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(vtol.getCost(true)) + " C-bills",
        // 52, 357);

        if (UnitUtil.hasBAR(vtol)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(vtol), 400, 55);
        }

        font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("2012", 62.5f, 374.5f);

        if (vtol2 != null) {
            printVTOL2Data(g2d);
        } else {
            g2d.drawString("2012", 62.5f, 745f);
        }
    }

    private void printVTOL2Data(Graphics2D g2d) {
        String VTOLName = vtol2.getChassis() + " " + vtol2.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, VTOLName, true, 180, 10.0f));
        g2d.drawString(VTOLName, 49, 493);

        Font font = UnitUtil.deriveFont(true, 15.0f);
        g2d.setFont(font);

        if (vtol2 instanceof SupportVTOL) {
            g2d.drawString("Support", 70, 89 + secondPageMargin);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((vtol2.getCrew() != null) && !vtol2.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = vtol2.getCrew();
            g2d.drawString(pilot.getName(), 270, 120 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132 + secondPageMargin);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132 + secondPageMargin);
        }

        g2d.drawString(Integer.toString(vtol2.getWalkMP()), 79, 515);
        if (!vtol2.hasWorkingMisc(MiscType.F_MASC, MiscType.S_JETBOOSTER)) {
            g2d.drawString(Integer.toString(vtol2.getRunMP()), 79, 526);
        } else {
            int mascMP = vtol2.getRunMP();
            g2d.drawString(Integer.toString(vtol2.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 526);
        }

        String engineName = "Fusion Engine";

        switch (vtol2.getEngine().getEngineType()) {
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

        g2d.drawString(engineName, 79, 537);

        if (vtol2.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(vtol2.getWeight());

            g2d.drawString(Integer.toString(tonnage), 177, 505);
        } else {
            // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,d", vtol2.getWeight()), 177, 505);
        }

        int nextDataLine = 526;
        int startLine = 188;
        int lineFeed = 8;

        switch (vtol2.getTechLevel()) {

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

        if (vtol2.isMixedTech()) {
            if (vtol2.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (vtol2.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        if ((vtol2.getSource() != null) && (vtol2.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, vtol2.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(vtol2.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", vtol2.getYear()), 177, nextDataLine);

        }

        // // Cost/BV
        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = vtol2.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 357 + secondPageMargin);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", vtol2.calculateBattleValue(true, true)), 50, 357 + secondPageMargin);
        }
        // myFormatter = new DecimalFormat("#,###.##", unusualSymbols);
        // g2d.drawString(myFormatter.format(vtol2.getCost(true)) + " C-bills",
        // 52, 728);

        if (UnitUtil.hasBAR(vtol2)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(vtol2), 400, 55 + secondPageMargin);
        }

        font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("2012", 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);
        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(vtol), 463, 48);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_FRONT)) + ")", 467, 64);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_RIGHT)) + ")", 535, 253);

        g2d.drawString("(" + vtol.getArmor(Tank.LOC_LEFT) + ")", 407, 195);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_REAR)) + ")", 472, 336);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(VTOL.LOC_ROTOR)) + ")", 535, 140);

        if (vtol2 != null) {
            font = UnitUtil.deriveFont(true, 11.0f);
            g2d.setFont(font);
            g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(vtol2), 463, 48 + secondPageMargin);
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_FRONT)) + ")", 467, 64 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_RIGHT)) + ")", 535, 253 + secondPageMargin);

            g2d.drawString("(" + vtol2.getArmor(Tank.LOC_LEFT) + ")", 407, 195 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_REAR)) + ")", 472, 336 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(VTOL.LOC_ROTOR)) + ")", 535, 140 + secondPageMargin);

        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printVTOLWeaponsNEquipment(vtol, g2d);

        if (vtol2 != null) {
            ImageHelperVehicle.printVTOLWeaponsNEquipment(vtol2, g2d, secondPageMargin);
        }

    }

    public void print() {

        try {
            for (int pos = 0; pos < vtolList.size(); pos++) {
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

                vtol = vtolList.get(pos);
                pj.setJobName(vtol.getChassis() + " " + vtol.getModel());

                if (((pos + 1) < vtolList.size()) && !singlePrint) {
                    vtol2 = vtolList.get(++pos);
                } else {
                    vtol2 = null;
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
        float[] topColumn =
            { 461, 82 };
        float[] middleColumn =
            { 456, 88 };
        float[] pipShift =
            { 6, 6 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(5, totalArmor);
        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0], topColumn[1], 8.0f);
            topColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1], 8.0f);
            middleColumn[0] += pipShift[0];
            if ((pos % 7) == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[]
            { 475, 252 };
        int[] pipShift = new int[]
            { 6, 6 };
        float fontSize = 8.0f;

        if (totalArmor > 11) {
            fontSize = 7;
            pipShift[0] = pipShift[1] = 5;
        }

        if (totalArmor > 13) {
            fontSize = 6;
            pipShift[0] = pipShift[1] = 4;
        }

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);
            topColumn[1] += pipShift[1];
        }

    }

    private void printRotorArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float[][] armor =
            {
                { 392, 154.5f },
                { 552, 154.5f } };
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, armor[pos][0], armor[pos][1]);
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = new float[]
            { 443, 106 };
        float[] pipShift = new float[]
            { 6, 6 };
        float fontSize = 8.0f;

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);

            switch (pos) {
                case 0:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    topColumn[1] += pipShift[1];
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 14:
                case 16:
                case 18:
                case 20:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 12:
                    topColumn[1] += pipShift[1] * 3.75;
                    topColumn[0] += pipShift[0] * -2.25;
                    break;

                default:
                    topColumn[0] += pipShift[0];
                    break;
            }
        }
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = new float[]
            { 503, 106 };
        float[] pipShift = new float[]
            { 6, 6 };
        float fontSize = 8.0f;

        /*
         * if (totalArmor > 68) { fontSize = 8.0f; pipShift = new float[] { 6f,
         * 6f }; topColumn = new float[] { 418.5f, 92 }; }
         */

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelperVehicle.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);

            switch (pos) {
                case 0:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    topColumn[1] += pipShift[1];
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 14:
                case 16:
                case 18:
                case 20:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 12:
                    topColumn[1] += pipShift[1] * 3.75;
                    topColumn[0] -= pipShift[0] * -2.10;
                    break;
                default:
                    topColumn[0] -= pipShift[0];
                    break;
            }
        }
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct =
            {
                { 472, 119 },
                { 472, 128 },
                { 472, 137 } };

        if (totalArmor > 3) {
            struct = new int[][]
                {
                    { 468, 119 },
                    { 468, 128 },
                    { 468, 137 },
                    { 477, 119 },
                    { 477, 128 },
                    { 477, 137 } };

        }

        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRotorStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct =
            {
                { 472, 148 },
                { 422, 148 },
                { 522, 148 } };

        if (totalArmor > 3) {
            struct = new int[][]
                {
                    { 472, 148 },
                    { 422, 148 },
                    { 522, 148 },
                    { 452, 148 },
                    { 442, 148 },
                    { 502, 148 } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct =
            {
                { 467, 164 },
                { 467, 178 },
                { 467, 192 } };

        if (totalArmor > 3) {
            struct = new int[][]
                {
                    { 467, 164 },
                    { 467, 170 },
                    { 467, 176 },
                    { 467, 182 },
                    { 467, 188 },
                    { 467, 194 } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct =
            {
                { 480, 164 },
                { 480, 178 },
                { 480, 192 } };

        if (totalArmor > 3) {
            struct = new int[][]
                {
                    { 480, 164 },
                    { 480, 170 },
                    { 480, 176 },
                    { 480, 182 },
                    { 480, 188 },
                    { 480, 194 } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct =
            {
                { 473, 216 },
                { 473, 225 },
                { 473, 234 } };

        if (totalArmor > 3) {
            struct = new int[][]
                {
                    { 468, 216 },
                    { 468, 225 },
                    { 468, 234 },
                    { 478, 216 },
                    { 478, 225 },
                    { 478, 234 } };

        }
        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelperVehicle.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }
    }

    private void printVTOLImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(vtol, ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(120, img.getHeight(null));
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 250 + ((120 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

        if (vtol2 != null) {
            img = ImageHelper.getFluffImage(vtol2, ImageHelper.imageVehicle);
            width = Math.min(148, img.getWidth(null));
            height = Math.min(119, img.getHeight(null));
            drawingX = 235 + ((148 - width) / 2);
            drawingY = 248 + ((119 - height) / 2) + secondPageMargin;
            g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
        }
    }
}