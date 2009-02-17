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
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintVehicle implements Printable {

    private Tank tank = null;
    private Tank tank2 = null;
    private ArrayList<Tank> tankList;
    private int secondPageMargin = 373; // How far down the text should be printed for a second vehicle.

    public PrintVehicle(ArrayList<Tank> list) {
        tankList = list;

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

        g2d.drawImage(ImageHelper.getRecordSheet(tank, false), 18, 18, 558, 368, null);

        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawImage(ImageHelper.getTurretImage(), 18, 18, 558, 368, null);
        }
        if (tank2 == null) {
            g2d.drawImage(ImageHelper.getTableImage(tank), 18, 18 + secondPageMargin, 558, 366, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(tank2, false), 18, 18 + secondPageMargin, 558, 366, null);
            if (tank2.getOInternal(Tank.LOC_TURRET) > 0) {
                g2d.drawImage(ImageHelper.getTurretImage(), 18, 18 + secondPageMargin, 558, 366, null);
            }
        }

        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, tank.getOArmor(Tank.LOC_FRONT), true, false);
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
            printFrontArmor(g2d, tank2.getOArmor(Tank.LOC_FRONT), true, true);
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
        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printTankData(Graphics2D g2d) {
        String tankName = tank.getChassis().toUpperCase() + " " + tank.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

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
        default:
            break;
        }

        g2d.drawString(engineName, 79, 177);

        int tonnage = (int) Math.ceil(tank.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        String techBase = "Inner Sphere";
        if (tank.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        g2d.drawString(Integer.toString(tank.getYear()), 188, 155);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(tank.calculateBattleValue(true, true)), 150, 357);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(tank.getCost()) + " C-bills", 52, 357);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 105f, 374.5f);

        if ( tank2 != null ){
            printTank2Data(g2d);
        } else {
            g2d.drawString("2009", 105f, 745.5f);
        }
    }

    private void printTank2Data(Graphics2D g2d) {
        String tankName = tank2.getChassis().toUpperCase() + " " + tank2.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, tankName, true, 180, 10.0f));
        g2d.drawString(tankName, 49, 494);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(tank2.getWalkMP()), 79, 515);
        g2d.drawString(Integer.toString(tank2.getRunMP()), 79, 526);

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
        default:
            break;
        }

        g2d.drawString(engineName, 79, 548);

        int tonnage = (int) Math.ceil(tank2.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 505);

        String techBase = "Inner Sphere";
        if (tank2.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 516);

        g2d.drawString(Integer.toString(tank2.getYear()), 188, 526);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(tank2.calculateBattleValue(true, true)), 150, 728);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(tank2.getCost()) + " C-bills", 52, 728);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 105f, 745.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);
        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_FRONT)) + ")", 467, 64);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_RIGHT)) + ")", 559, 230);

        g2d.drawString("(" + tank.getArmor(Tank.LOC_LEFT) + ")", 384, 175);

        g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_REAR)) + ")", 467, 342);

        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET)) + ")", 455, 186);
        }

        if (tank2 != null) {
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

        ImageHelper.printTankWeaponsNEquipment(tank, g2d);

        if (tank2 != null) {
            ImageHelper.printTankWeaponsNEquipment(tank2, g2d, secondPageMargin);
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

                for (int pos = 0; pos < tankList.size(); pos++) {

                    tank = tankList.get(pos);
                    pj.setJobName(tank.getChassis() + " " + tank.getModel());

                    if (++pos < tankList.size()) {
                        tank2 = tankList.get(pos);
                    } else {
                        tank2 = null;
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

    private void printExtraFrontArmor(Graphics2D g2d, int totalArmor, boolean firstPass, boolean secondImage) {
        float[] topColumn = { 442, 90 };
        float[] middleColumn = { 498, 125 };
        float[] bottomColumn = { 493, 136.2f };
        float[] pipShift = { 6, 6 };

        float[][] extraArmor = { { 429, 90 }, { 429, 97 }, { 518, 90 }, { 518, 97 }, { 436f, 93.5f }, { 436f, 100.5f }, { 513f, 93.5f }, { 513f, 100.5f }, { 513f, 107.5f }, { 436f, 107.5f } };

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

        for (int pos = 1; pos <= 72; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], 8.0f);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn[0] += pipShift[0];
            if (pos % 12 == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        pipShift[0] *= -1;
        for (int pos = 1; pos <= 16; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1], 8.0f);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn[0] += pipShift[0];
            if (pos % 8 == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 18; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, bottomColumn[0], bottomColumn[1], 8.0f);
                if (--totalArmor == 0) {
                    return;
                }
            }
            bottomColumn[0] += pipShift[0];
            if (pos % 6 == 0) {
                bottomColumn[1] += pipShift[1] - 0.5f;
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        if (firstPass) {
            for (int pos = 0; pos < 10; pos++) {
                ImageHelper.drawTankArmorPip(g2d, extraArmor[pos][0], extraArmor[pos][1], 8.0f);
                if (--totalArmor == 0) {
                    return;
                }
            }
        }

        if (totalArmor > 0) {
            printExtraFrontArmor(g2d, totalArmor, false, secondImage);
        }

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean firstPass, boolean secondImage) {
        float[] topColumn = { 444, 90 };
        float[] middleColumn = { 500, 125 };
        float[] bottomColumn = { 493, 132 };
        float[] pipShift = { 7, 7 };

        float[][] extraArmor = { { 430, 90 }, { 430, 97 }, { 519, 90 }, { 519, 97 }, { 437f, 93.5f }, { 437f, 100.5f }, { 513f, 93.5f }, { 513f, 100.5f }, { 513f, 107.5f }, { 437f, 107.5f } };

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
            printExtraFrontArmor(g2d, totalArmor, firstPass, secondImage);
            return;
        }

        for (int pos = 1; pos <= 50; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn[0] += pipShift[0];
            if (pos % 10 == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 8; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 18; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            bottomColumn[0] += pipShift[0];
            if (pos % 6 == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 10; pos++) {
            if ((firstPass && (pos % 2 == 0)) || (!firstPass && (pos % 2 != 0))) {
                ImageHelper.drawTankArmorPip(g2d, extraArmor[pos - 1][0], extraArmor[pos - 1][1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
        }

        if (totalArmor > 0) {
            printFrontArmor(g2d, totalArmor, false, secondImage);
        }

    }

    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[] { 422, 291 };
        int[] middleColumn = new int[] { 422, 298 };
        int[] bottomColumn = new int[] { 422, 305 };
        int[] pipShift = new int[] { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            middleColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = Math.min(17, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((17 - pips) / 2);

        for (int pos = 1; pos <= pips; pos++) {
                ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(17, totalArmor);
        totalArmor -= pips;
        middleColumn[0] += pipShift[0] * ((17 - pips) / 2);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1]);
            middleColumn[0] += pipShift[0];
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(17, totalArmor);
        bottomColumn[0] += pipShift[0] * ((17 - pips) / 2);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawTankArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }

    }

    private void printTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {

        if (totalArmor < 1) {
            return;
        }

        float[] topColumn = new float[] { 458f, 234 };
        float[] bottomColumn = new float[] { 451.5f, 241 };
        float[] pipShift = new float[] { 6f, 6f };
        float fontSize = 8.0f;

        if ( totalArmor > 52 ){
            fontSize = 7.0f;
            pipShift = new float[] { 5f, 5f };
            topColumn = new float[] { 460.5f, 232 };
            bottomColumn = new float[] { 455.5f, 236 };
        }
        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = Math.min(7, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((7 - pips) / 2);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawTankArmorPip(g2d, topColumn[0], topColumn[1], fontSize);
            topColumn[0] += pipShift[0];
        }

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankArmorPip(g2d, bottomColumn[0], bottomColumn[1], fontSize);
            bottomColumn[0] += pipShift[0];
            if (pos % 9 == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];

                if (totalArmor - pos < 9) {
                    bottomColumn[0] += pipShift[0] * ((9 - (totalArmor - pos)) / 2);
                }
            }
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = new float[] { 418, 93 };
        float[] pipShift = new float[] { 7, 7 };
        float fontSize = 9.0f;

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
            case 1:
                topColumn[1] += pipShift[1];
                break;
            case 3:
            case 5:
            case 8:
            case 11:
            case 15:
            case 19:
            case 24:
            case 30:
            case 37:
            case 40:
            case 43:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 58:
            case 61:
            case 64:
            case 67:
            case 70:
            case 76:
            case 77:
            case 78:
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                break;
            case 34:
            case 46:
            case 74:
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
                break;
            case 54:
                topColumn[1] += pipShift[1];
                topColumn[0] -= pipShift[0] * .25;
                pipShift[0] *= -1;
                break;
            case 73:
                topColumn[1] += pipShift[1];
                topColumn[0] -= pipShift[0];
                pipShift[0] *= -1;
                break;
            default:
                topColumn[0] += pipShift[0];
                break;
            }
        }
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage) {
        float[] topColumn = new float[] { 530.5f, 93 };
        float[] pipShift = new float[] { 7, 7 };
        float fontSize = 9.0f;

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
            case 1:
                topColumn[1] += pipShift[1];
                break;
            case 3:
            case 5:
            case 8:
            case 11:
            case 15:
            case 19:
            case 24:
            case 30:
            case 37:
            case 40:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 58:
            case 61:
            case 64:
            case 67:
            case 70:
            case 76:
            case 77:
            case 78:
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                break;
            case 34:
            case 46:
            case 74:
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] -= pipShift[0];
                break;
            case 43:
                topColumn[1] += pipShift[1];
                topColumn[0] -= pipShift[0] * .25;
                pipShift[0] *= -1;
                break;
            case 54:
                topColumn[1] += pipShift[1];
                topColumn[0] += pipShift[0] * .25;
                pipShift[0] *= -1;
                break;
            case 73:
                topColumn[1] += pipShift[1];
                topColumn[0] += pipShift[0];
                pipShift[0] *= -1;
                break;
            default:
                topColumn[0] -= pipShift[0];
                break;
            }
        }
    }

    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[] { 462, 148 };
        int[] bottomColumn = new int[] { 462, 156 };
        int[] pipShift = new int[] { 7, 7 };

        if (secondImage) {
            topColumn[1] += secondPageMargin;
            bottomColumn[1] += secondPageMargin;
        }

        int pips = Math.min(5, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((5 - pips) / 2);
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawTankISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    private void printTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[] { 462, 210 };
        int[] bottomColumn = new int[] { 462, 218 };
        int[] pipShift = new int[] { 7, 7 };

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
            ImageHelper.drawTankISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[] { 444, 164 };
        int[] pipShift = new int[] { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankISPip(g2d, column[0], column[1]);
            column[0] -= pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[] { 505, 164 };
        int[] pipShift = new int[] { 2, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
            column[1] += pipShift[1];
        }
    }

    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[] { 445, 275 };
        int[] pipShift = new int[] { 7, 7 };

        if (secondImage) {
            column[1] += secondPageMargin;
        }

        column[0] += pipShift[0] * ((10 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelper.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
        }
    }
}