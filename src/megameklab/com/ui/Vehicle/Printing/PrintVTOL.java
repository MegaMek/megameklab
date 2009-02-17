/*
 * MegaMekLab - Copyright (C) 2008
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

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;

import megamek.common.Engine;
import megamek.common.Tank;
import megamek.common.VTOL;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintVTOL implements Printable {

    private VTOL vtol = null;
    private VTOL vtol2 = null;
    private ArrayList<VTOL> vtolList;
    private int secondPageMargin = 373; // How far down the text should be printed for a second vehicle.

    public PrintVTOL(ArrayList<VTOL> list) {
        vtolList = list;

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
            g2d.drawImage(ImageHelper.getTableImage(vtol), 18, 18 + secondPageMargin, 558, 366, null);
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
        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printVTOLData(Graphics2D g2d) {
        String VTOLName = vtol.getChassis().toUpperCase() + " " + vtol.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, VTOLName, true, 180, 10.0f));
        g2d.drawString(VTOLName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(vtol.getWalkMP()), 79, 144);
        g2d.drawString(Integer.toString(vtol.getRunMP()), 79, 155);

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
        default:
            break;
        }

        g2d.drawString(engineName, 79, 166);

        int tonnage = (int) Math.ceil(vtol.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        String techBase = "Inner Sphere";
        if (vtol.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        g2d.drawString(Integer.toString(vtol.getYear()), 188, 155);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(vtol.calculateBattleValue(true, true)), 150, 357);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(vtol.getCost()) + " C-bills", 52, 357);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 105f, 374.5f);

        if (vtol2 != null) {
            printVTOL2Data(g2d);
        } else {
            g2d.drawString("2009", 105f, 745.5f);
        }
    }

    private void printVTOL2Data(Graphics2D g2d) {
        String VTOLName = vtol2.getChassis().toUpperCase() + " " + vtol2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, VTOLName, true, 180, 10.0f));
        g2d.drawString(VTOLName, 49, 494);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(vtol2.getWalkMP()), 79, 515);
        g2d.drawString(Integer.toString(vtol2.getRunMP()), 79, 526);

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
        default:
            break;
        }

        g2d.drawString(engineName, 79, 537);

        int tonnage = (int) Math.ceil(vtol2.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 505);

        String techBase = "Inner Sphere";
        if (vtol2.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 516);

        g2d.drawString(Integer.toString(vtol2.getYear()), 188, 526);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(vtol2.calculateBattleValue(true, true)), 150, 728);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(vtol2.getCost()) + " C-bills", 52, 728);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);
        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_FRONT)) + ")", 467, 64);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_RIGHT)) + ")", 535, 253);

        g2d.drawString("(" + vtol.getArmor(Tank.LOC_LEFT) + ")", 407, 195);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(Tank.LOC_REAR)) + ")", 472, 336);

        g2d.drawString("(" + Integer.toString(vtol.getArmor(VTOL.LOC_ROTOR)) + ")", 535, 140);

        if (vtol2 != null) {
            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_FRONT)) + ")", 467, 64 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_RIGHT)) + ")", 535, 253 + secondPageMargin);

            g2d.drawString("(" + vtol2.getArmor(Tank.LOC_LEFT) + ")", 407, 195 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(Tank.LOC_REAR)) + ")", 472, 336 + secondPageMargin);

            g2d.drawString("(" + Integer.toString(vtol2.getArmor(VTOL.LOC_ROTOR)) + ")", 535, 140 + secondPageMargin);

        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printVTOLWeaponsNEquipment(vtol, g2d);

        if (vtol2 != null) {
            ImageHelper.printVTOLWeaponsNEquipment(vtol2, g2d, secondPageMargin);
        }

    }

    public void print() {

        try {
            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                // Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                for (int pos = 0; pos < vtolList.size(); pos++) {

                    vtol = vtolList.get(pos);
                    pj.setJobName(vtol.getChassis() + " " + vtol.getModel());

                    if (++pos < vtolList.size()) {
                        vtol2 = vtolList.get(pos);
                    } else {
                        vtol2 = null;
                    }

                    try {
                        pj.print();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.gc();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = { 461, 82 };
        float[] middleColumn = { 456, 88 };
        float[] pipShift = { 6, 6 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(5, totalArmor);
        totalArmor -= pips;
        for (int pos = 1; pos <= 5; pos++) {
            ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], 8.0f);
            topColumn[0] += pipShift[0];
        }


        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1], 8.0f);
            middleColumn[0] += pipShift[0];
            if (pos % 7 == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[] { 475, 252 };
        int[] pipShift = new int[] { 6, 6 };
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
                ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);
            topColumn[1] += pipShift[1];
        }

    }

    private void printRotorArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {


        float[][] armor = { { 392, 154.5f }, { 552, 154.5f } };
        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelper.drawTankArmorPip(g2d, armor[pos][0], armor[pos][1]);
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = new float[] { 443, 106 };
        float[] pipShift = new float[] { 6, 6 };
        float fontSize = 8.0f;

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);

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
        float[] topColumn = new float[] { 503, 106 };
        float[] pipShift = new float[] { 6, 6 };
        float fontSize = 8.0f;

        /*        if (totalArmor > 68) {
                    fontSize = 8.0f;
                    pipShift = new float[] { 6f, 6f };
                    topColumn = new float[] { 418.5f, 92 };
                }*/

        if (secondImage) {
            topColumn[1] += secondPageMargin;
        }

        for (int pos = 0; pos < totalArmor; pos++) {
            ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);

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
        int[][] struct = { { 472, 119 }, { 472, 128 }, { 472, 137 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            if ( secondImage ){
                struct[pos][1] += secondPageMargin;
            }
            ImageHelper.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRotorStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct = { { 472, 148 }, { 422, 148 }, { 522, 148 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelper.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct = { { 467, 164 }, { 467, 178 }, { 467, 192 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelper.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct = { { 480, 164 }, { 480, 178 }, { 480, 192 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelper.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }

    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[][] struct = { { 473, 216 }, { 473, 225 }, { 473, 234 } };

        for (int pos = 0; pos < totalArmor; pos++) {
            if (secondImage) {
                struct[pos][1] += secondPageMargin;
            }
            ImageHelper.drawTankISPip(g2d, struct[pos][0], struct[pos][1]);
        }
   }
}