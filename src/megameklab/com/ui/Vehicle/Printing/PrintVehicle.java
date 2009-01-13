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

import java.awt.Dimension;
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

import megamek.common.Engine;
import megamek.common.Tank;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintVehicle implements Printable {

    protected Image awtImage = null;
    private Tank tank = null;
    private Tank tank2 = null;
    private ArrayList<Tank> tankList;

    public PrintVehicle(ArrayList<Tank> list) {
        awtImage = ImageHelper.getRecordSheet(list.get(0), list.get(0).getOInternal(Tank.LOC_TURRET) > 0);
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
        printImage(g2d, awtImage, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, Image image, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        System.gc();

        g2d.drawImage(image, 18, 18, 558, 368, null);

        if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawImage(ImageHelper.getTurretImage(tank), 18, 18, 558, 368, null);
        }
        if (tank2 == null) {
            g2d.drawImage(ImageHelper.getTableImage(tank), 18, 391, 558, 366, null);
        } else {
            g2d.drawImage(ImageHelper.getRecordSheet(tank2, false), 18, 391, 558, 366, null);
            if (tank2.getOInternal(Tank.LOC_TURRET) > 0) {
                g2d.drawImage(ImageHelper.getTurretImage(tank), 18, 391, 558, 366, null);
            }
        }

        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, tank.getOArmor(Tank.LOC_FRONT), true);

        // Internal Pips
        printFrontStruct(g2d);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printTankData(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        g2d.drawString(tank.getChassis().toUpperCase() + " " + tank.getModel().toUpperCase(), 49, 121);

        font = UnitUtil.deriveFont(8.0f);
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
        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        g2d.drawString(tank.getChassis().toUpperCase() + " " + tank.getModel().toUpperCase(), 49, 492);

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(tank.getWalkMP()), 79, 515);
        g2d.drawString(Integer.toString(tank.getRunMP()), 79, 526);

        g2d.drawString(tank.getMovementModeAsString(), 88, 537);

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

        g2d.drawString(engineName, 79, 548);

        int tonnage = (int) Math.ceil(tank.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 505);

        String techBase = "Inner Sphere";
        if (tank.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 516);

        g2d.drawString(Integer.toString(tank.getYear()), 188, 526);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(tank.calculateBattleValue(true, true)), 150, 728);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(tank.getCost()) + " C-bills", 52, 728);

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

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printTankWeaponsNEquipment(tank, g2d);
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
                    awtImage = ImageHelper.getRecordSheet(tank, tank.getOInternal(Tank.LOC_TURRET) > 0);
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

    private void printExtraFrontArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        float[] topColumn = { 442, 90 };
        float[] middleColumn = { 498, 125 };
        float[] bottomColumn = { 493, 136.2f };
        float[] pipShift = { 6, 6 };

        float[][] extraArmor = { { 429, 90 }, { 429, 97 }, { 518, 90 }, { 518, 97 }, { 436f, 93.5f }, { 436f, 100.5f }, { 513f, 93.5f }, { 513f, 100.5f }, { 513f, 107.5f }, { 436f, 107.5f } };

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 72; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
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
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, middleColumn[0], middleColumn[1]);
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
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
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
                ImageHelper.drawArmorPip(g2d, extraArmor[pos][0], extraArmor[pos][1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
        }

        if (totalArmor > 0) {
            printExtraFrontArmor(g2d, totalArmor, false);
        }

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        float[] topColumn = { 444, 90 };
        float[] middleColumn = { 500, 125 };
        float[] bottomColumn = { 493, 132 };
        float[] pipShift = { 7, 7 };

        float[][] extraArmor = { { 430, 90 }, { 430, 97 }, { 519, 90 }, { 519, 97 }, { 437f, 93.5f }, { 437f, 100.5f }, { 513f, 93.5f }, { 513f, 100.5f }, { 513f, 107.5f }, { 437f, 107.5f } };

        /*
         * Dimension topColumn = new Dimension(464, 105); Dimension middleColumn
         * = new Dimension(481, 172); Dimension bottomColumn = new
         * Dimension(475, 185); Dimension pipShift = new Dimension(6, 6);
         */
        if (totalArmor < 1) {
            return;
        }

        if (totalArmor > 86) {
            printExtraFrontArmor(g2d, totalArmor, firstPass);
            return;
        }

        for (int pos = 1; pos <= 50; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
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
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawTankArmorPip(g2d, middleColumn[0], middleColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 18; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
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
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawTankArmorPip(g2d, extraArmor[pos - 1][0], extraArmor[pos - 1][1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
        }

        if (totalArmor > 0) {
            printFrontArmor(g2d, totalArmor, false);
        }

    }

    private void printFrontStruct(Graphics2D g2d) {
        Dimension column = new Dimension(506, 413);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = tank.getInternal(Tank.LOC_FRONT);

        int pips = Math.min(16, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width -= pipShift.width;

            if (pos % 4 == 0) {
                column.width += 2;
            }

        }

        if (totalArmor > 0) {
            column.height += pipShift.height;
            ImageHelper.drawISPip(g2d, column.width, column.height);
        }
    }

}