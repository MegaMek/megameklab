/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek.Printing;

import java.awt.Color;
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

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintMech implements Printable {

    protected Image awtImage = null;
    protected Image awtHud = null;
    private Mech mech = null;
    private ArrayList<Mech> mechList;

    private Mounted startingMount = null;
    private int startMountx = 0;
    private int startMounty = 0;
    private int endMountx = 0;
    private int endMounty = 0;

    public PrintMech(ArrayList<Mech> list) {
        awtImage = ImageHelper.getRecordSheet(list.get(0), false);
        mechList = list;

        /*
         * if (awtImage != null) { System.out.println("Width: " + awtImage.getWidth(null)); System.out.println("Height: " + awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, awtImage, awtHud, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, Image image, Image hud, PageFormat pageFormat) {
        // System.out.println("printImage(Graphics2D g2d, Image image)");
        if (g2d == null) {
            return;
        }

        System.gc();
        // g2d.drawImage(image, 2, 0, (int)pageFormat.getImageableWidth(),
        // (int)pageFormat.getImageableHeight(), null);
        g2d.drawImage(image, 18, 18, 558, 738, Color.BLACK, null);
        printMekImage(g2d, hud);

        printMechData(g2d);
        printHeatSinks(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printLACrits(g2d);
        printRACrits(g2d);
        printCTCrits(g2d);
        printLTCrits(g2d);
        printRTCrits(g2d);
        printHeadCrits(g2d);
        printLLCrits(g2d);
        printRLCrits(g2d);

        // If its clan print case for the crits.
        if (mech.isClan()) {
            printLACase(g2d);
            printLLCase(g2d);
            printLTCase(g2d);
            printHeadCase(g2d);
            printCTCase(g2d);
            printRACase(g2d);
            printRTCase(g2d);
            printRLCase(g2d);
        }

        // Armor Pips
        printLAArmor(g2d, mech.getArmor(Mech.LOC_LARM), true);
        printRAArmor(g2d, mech.getArmor(Mech.LOC_RARM), true);
        printLTArmor(g2d, mech.getArmor(Mech.LOC_LT), true);
        printRTArmor(g2d, mech.getArmor(Mech.LOC_RT), true);
        printCTArmor(g2d, mech.getArmor(Mech.LOC_CT), true);
        printLLArmor(g2d);
        printRLArmor(g2d);
        printLTRArmor(g2d, mech.getArmor(Mech.LOC_LT, true), true);
        printRTRArmor(g2d, mech.getArmor(Mech.LOC_RT, true), true);
        printCTRArmor(g2d, mech.getArmor(Mech.LOC_CT, true), true);
        printHeadArmor(g2d);

        // Internal Pips
        printLAStruct(g2d);
        printRAStruct(g2d);
        printLTStruct(g2d);
        printRTStruct(g2d);
        printCTStruct(g2d);
        printHeadStruct(g2d);
        printLLStruct(g2d);
        printRLStruct(g2d);

        // g2d.translate(pageFormat.getImageableX(),
        // pageFormat.getImageableY());
        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printMechData(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis().toUpperCase() + " " + mech.getModel().toUpperCase(), 49, 121);

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if (mech.hasTSM()) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 79, 155);
        } else if (mech.getMASC() != null && mech.getSuperCharger() != null) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else if (mech.getMASC() != null || mech.getSuperCharger() != null) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 79, 155);
        }
        g2d.drawString(Integer.toString(mech.getJumpMP()), 79, 166);

        int tonnage = (int) Math.ceil(mech.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        String techBase = "Inner Sphere";
        if (mech.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 145);

        g2d.drawString(Integer.toString(mech.getYear()), 188, 155);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(mech.calculateBattleValue(true, true)), 150, 350);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost()) + " C-bills", 52, 350);

        font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        g2d.drawString("2009", 102.5f, 745f);

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (mech.hasDoubleHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.heatSinks() * 2) + ")", 502, 595);
            g2d.drawString("Double", 502, 603);
        } else {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.heatSinks()) + ")", 502, 595);
            g2d.drawString("Single", 502, 603);
        }

        Dimension column = new Dimension(504, 612);
        Dimension pipShift = new Dimension(9, 9);

        for (int pos = 1; pos <= mech.heatSinks(); pos++) {
            ImageHelper.drawHeatSinkPip(g2d, column.height, column.width);
            column.height += pipShift.height;

            if (pos % 10 == 0) {
                column.height -= pipShift.height * 10;
                column.width += pipShift.width;
            }

        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = UnitUtil.deriveFont(7.0f);
        g2d.setFont(font);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_HEAD)) + ")", 485, 46);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT)) + ")", 434, 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT)) + ")", 506, 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT)) + ")", 472, 222);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LARM)) + ")", 398, 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RARM)) + ")", 546, 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LLEG)) + ")", 389, 273);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RLEG)) + ")", 554, 273);
        // Rear
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT, true)) + ")", 403, 362);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT, true)) + ")", 481, 278);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT, true)) + ")", 546, 362);
        // Internal
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LT)) + ")", 432, 403);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RT)) + ")", 525, 403);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LARM)) + ")", 390, 480);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RARM)) + ")", 530, 480);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_CT)) + ")", 459, 509);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LLEG)) + ")", 403, 538);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RLEG)) + ")", 518, 539);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 408;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart, linePoint, lineFeed);
    }

    private void printRACrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 408;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RARM, lineStart, linePoint, lineFeed);
    }

    private void printCTCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 469;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_CT, lineStart, linePoint, lineFeed);
    }

    private void printLTCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart, linePoint, lineFeed);
    }

    private void printRTCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RT, lineStart, linePoint, lineFeed);
    }

    private void printHeadCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 401;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_HEAD, lineStart, linePoint, lineFeed);
    }

    private void printLLCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 682;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart, linePoint, lineFeed);
    }

    private void printRLCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 682;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart, linePoint, lineFeed);
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printMechWeaponsNEquipment(mech, g2d);
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

                for (Mech currentMech : mechList) {

                    mech = currentMech;
                    awtHud = ImageHelper.getFluffImage(currentMech);
                    pj.setJobName(mech.getChassis() + " " + mech.getModel());

                    pj.print();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printRLArmor(Graphics2D g2d) {
        float[] topColumn = { 499, 181 };
        float[] middleColumn = { 509, 253 };
        float[] bottomColumn = { 531, 270 };
        float[] footColumn = { 519, 296 };
        float[] pipShift = { 8, -2 };

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        int maxPipsPerTopColumn = 2;
        if (pips < 17) {
            maxPipsPerTopColumn = 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
            topColumn[1] += pipShift[1];
            if (pos % maxPipsPerTopColumn == 0) {
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0] + 1.8f;
                pipShift[1] *= -1;
                topColumn[1] += pipShift[1] + 7;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, middleColumn[0], middleColumn[1]);
            middleColumn[0] += pipShift[0];
            middleColumn[1] += pipShift[1];
            if (pos % 4 == 0) {
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0] + 1.8f;
                pipShift[1] *= -1;
                middleColumn[1] += pipShift[1] + 7;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
            bottomColumn[1] += pipShift[1];
            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0] + 1.8f;
                pipShift[1] *= -1;
                bottomColumn[1] += pipShift[1] + 7;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, footColumn[0], footColumn[1]);
            footColumn[0] += pipShift[0];
        }

    }

    private void printLLArmor(Graphics2D g2d) {
        float[] topColumn = { 443, 179 };
        float[] middleColumn = { 417, 247 };
        float[] bottomColumn = { 427.6f, 272 };
        float[] footColumn = { 406, 296 };
        float[] pipShift = { 8, 2 };

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        int maxPipsPerTopColumn = 2;
        if (pips < 17) {
            maxPipsPerTopColumn = 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
            topColumn[1] += pipShift[1];
            if (pos % maxPipsPerTopColumn == 0) {
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0] - 1.8f;
                pipShift[1] *= -1;
                topColumn[1] += pipShift[1] + 7;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, middleColumn[0], middleColumn[1]);
            middleColumn[0] += pipShift[0];
            middleColumn[1] += pipShift[1];
            if (pos % 4 == 0) {
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0] - 1.8f;
                pipShift[1] *= -1;
                middleColumn[1] += pipShift[1] + 7;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
            bottomColumn[1] += pipShift[1];
            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0] - 1.9f;
                pipShift[1] *= -1;
                bottomColumn[1] += pipShift[1] + 7;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, footColumn[0], footColumn[1]);
            footColumn[0] += pipShift[0];
        }

    }

    private void printLAArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension rightColumn = new Dimension(417, 77);
        Dimension centerColumn = new Dimension(409, 84);
        Dimension leftColumn = new Dimension(402, 90);
        Dimension pipShift = new Dimension(-1, 7);

        if (totalArmor < 1) {
            return;
        }

        int pips = 12;

        // totalArmor -= pips;

        // if (pips < 12) {
        // pipShift.height = (pipShift.height * 12) / pips;
        // }
        for (int pos = 1; pos <= pips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, centerColumn.width, centerColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            centerColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                centerColumn.width += pipShift.width;
            }

            if (pos == 7 || pos == 8) {
                centerColumn.height++;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = 12;

        /*
         * if (pips < 8) { pipShift.height = (pipShift.height 8) / pips; }
         */

        // totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, rightColumn.width, rightColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            rightColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                rightColumn.width += pipShift.width;
            }

            if (pos == 8) {
                rightColumn.height += pipShift.height + 3;
                rightColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = 10;

        /*
         * if (pips < 6) { pipShift.height = (pipShift.height 6) / pips; leftColumn.width -= 2; }
         */

        // totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, leftColumn.width, leftColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }
            if (pos == 6) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

        if (totalArmor > 0) {
            printLAArmor(g2d, totalArmor, false);
        }
    }

    private void printRAArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension rightColumn = new Dimension(548, 90);
        Dimension centerColumn = new Dimension(540, 84);
        Dimension leftColumn = new Dimension(533, 77);
        Dimension pipShift = new Dimension(1, 7);

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 12; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, centerColumn.width, centerColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            centerColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                centerColumn.width += pipShift.width;
            }

            if (pos == 7 || pos == 8) {
                centerColumn.height++;
            }
        }

        for (int pos = 1; pos <= 12; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, leftColumn.width, leftColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }

            if (pos == 8) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

        for (int pos = 1; pos <= 10; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, rightColumn.width, rightColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            rightColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                rightColumn.width += pipShift.width;
            }
            if (pos == 6) {
                rightColumn.height += pipShift.height + 3;
                rightColumn.width += pipShift.width;
            }
        }

        if (totalArmor > 0) {
            printRAArmor(g2d, totalArmor, false);
        }
    }

    private void printLTArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension topColumn = new Dimension(430, 88);
        Dimension middleColumn = new Dimension(445, 126);
        Dimension bottomColumn = new Dimension(437, 161);
        Dimension topPipShift = new Dimension(6, 7);
        Dimension middlePipShift = new Dimension(6, 7);
        Dimension bottomPipShift = new Dimension(6, 7);

        if (totalArmor < 1) {
            return;
        }

        int maxTopPips = 25;
        int maxMiddlePips = 10;
        int maxBottemPips = 7;

        int topPipsPerLine = 5;
        int middlePipsPerLine = 2;

        /*
         * if (totalArmor < 36) { topPipsPerLine = (int) Math.ceil(totalArmor / 8d); topPipShift.width += 4 - (int) Math.ceil(totalArmor / 10d); topPipShift.height += Math.max(0, 2 - (int) Math.ceil(totalArmor / 10d)); maxTopPips = Math.min(20, (totalArmor 60) / 100); maxMiddlePips = (totalArmor 24) / 100; maxBottemPips = totalArmor - (maxTopPips + maxMiddlePips); if (maxMiddlePips <= 5) { middlePipsPerLine = 1; } }
         */

        for (int pos = 1; pos <= maxTopPips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn.width, topColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn.width += topPipShift.width;
            if (pos % topPipsPerLine == 0) {
                topColumn.height += topPipShift.height;
                topPipShift.width *= -1;
                topColumn.width += topPipShift.width;
            }
        }

        for (int pos = 1; pos <= maxMiddlePips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, middleColumn.width, middleColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn.width += middlePipShift.width;
            if (pos % middlePipsPerLine == 0) {
                middleColumn.height += middlePipShift.height;
                middleColumn.width += 1;
                middlePipShift.width *= -1;
                if (middlePipsPerLine > 1) {
                    middleColumn.width += middlePipShift.width;
                }
            }
        }

        for (int pos = 1; pos <= maxBottemPips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, bottomColumn.width, bottomColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            bottomColumn.width += bottomPipShift.width;
            if (pos % 4 == 0) {
                bottomColumn.height += bottomPipShift.height;
                bottomColumn.width += 1;
                bottomPipShift.width *= -1;
                bottomColumn.width += bottomPipShift.width;
            }
        }

        if (totalArmor > 0) {
            printLTArmor(g2d, totalArmor, false);
        }
    }

    private void printLTRArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension topColumn = new Dimension(437, 312);
        Dimension pipShift = new Dimension(5, 5);
        int pipsPerLine = 5;

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 35; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn.width, topColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn.width += pipShift.width;
            if (pos % pipsPerLine == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                if (pos >= 30) {
                    topColumn.width += pipShift.width;
                } else {
                    topColumn.width += pipShift.width * 2;
                }
            }
        }

        if (totalArmor > 0) {
            printLTRArmor(g2d, totalArmor, false);
        }
    }

    private void printRTArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension topColumn = new Dimension(520, 88);
        Dimension middleColumn = new Dimension(504, 126);
        Dimension bottomColumn = new Dimension(512, 161);
        Dimension topPipShift = new Dimension(6, 7);
        Dimension middlePipShift = new Dimension(6, 7);
        Dimension bottomPipShift = new Dimension(6, 7);

        if (totalArmor < 1) {
            return;
        }

        int maxTopPips = 25;
        int maxMiddlePips = 10;
        int maxBottemPips = 7;

        int topPipsPerLine = 5;
        int middlePipsPerLine = 2;

        for (int pos = 1; pos <= maxTopPips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn.width, topColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn.width -= topPipShift.width;
            if (pos % topPipsPerLine == 0) {
                topColumn.height += topPipShift.height;
                topPipShift.width *= -1;
                topColumn.width -= topPipShift.width;
            }
        }

        for (int pos = 1; pos <= maxMiddlePips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, middleColumn.width, middleColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn.width -= middlePipShift.width;
            if (pos % middlePipsPerLine == 0) {
                middleColumn.height += middlePipShift.height;
                middleColumn.width -= 1;
                middlePipShift.width *= -1;
                if (middlePipsPerLine > 1) {
                    middleColumn.width -= middlePipShift.width;
                }
            }
        }

        for (int pos = 1; pos <= maxBottemPips; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, bottomColumn.width, bottomColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            bottomColumn.width -= bottomPipShift.width;
            if (pos % 4 == 0) {
                bottomColumn.height += bottomPipShift.height;
                bottomColumn.width -= 1;
                bottomPipShift.width *= -1;
                bottomColumn.width -= bottomPipShift.width;
            }
        }

        if (totalArmor > 0) {
            printRTArmor(g2d, totalArmor, false);
        }
    }

    private void printRTRArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        Dimension topColumn = new Dimension(514, 312);
        Dimension pipShift = new Dimension(5, 5);

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 35; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn.width, topColumn.height);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn.width -= pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                if (pos >= 30) {
                    topColumn.width -= pipShift.width;
                } else {
                    topColumn.width -= pipShift.width * 2;
                }
            }
        }

        if (totalArmor > 0) {
            printRTRArmor(g2d, totalArmor, false);
        }
    }

    private void printCTArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        float[] topColumn = { 464, 105 };
        float[] middleColumn = { 481, 172 };
        float[] bottomColumn = { 475, 185 };
        float[] pipShift = { 6, 6 };
        /*
         * Dimension topColumn = new Dimension(464, 105); Dimension middleColumn = new Dimension(481, 172); Dimension bottomColumn = new Dimension(475, 185); Dimension pipShift = new Dimension(6, 6);
         */
        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 55; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn[0] += pipShift[0];
            if (pos % 5 == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
                if (pos >= 30) {
                    pipShift[0] -= .1;
                }
            }
        }

        for (int pos = 1; pos <= 6; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, middleColumn[0], middleColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            middleColumn[0] += pipShift[0];
            if (pos % 3 == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }

        }

        if (!firstPass) {
            ImageHelper.drawArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
            if (--totalArmor == 0) {
                return;
            }
        }

        if (totalArmor > 0) {
            printCTArmor(g2d, totalArmor, false);
        }
    }

    private void printHeadArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);

        Dimension fillCircle = new Dimension(4, 4);

        if (mech.getArmor(Mech.LOC_HEAD) >= 1) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 68, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 73);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 2) {
            g2d.setColor(Color.white);
            g2d.fillOval(473, 74, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 472, 79);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 3) {
            g2d.setColor(Color.white);
            g2d.fillOval(479, 74, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 478, 79);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 4) {
            g2d.setColor(Color.white);
            g2d.fillOval(470, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 5) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 6) {
            g2d.setColor(Color.white);
            g2d.fillOval(482, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 7) {
            g2d.setColor(Color.white);
            g2d.fillOval(470, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469, 90);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 8) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 90);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 9) {
            g2d.setColor(Color.white);
            g2d.fillOval(482, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481, 90);
        }
        g2d.setColor(Color.black);
    }

    private void printCTRArmor(Graphics2D g2d, int totalArmor, boolean firstPass) {
        float[] topColumn = new float[] { 470, 301 };
        float[] pipShift = new float[] { 5f, 5f };

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 56; pos++) {
            if ((firstPass && pos % 2 == 0) || (!firstPass && pos % 2 != 0)) {
                ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
                if (--totalArmor == 0) {
                    return;
                }
            }
            topColumn[0] += pipShift[0];
            if (pos % 4 == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0] * 2;
            }
        }

        if (totalArmor > 0) {
            printCTRArmor(g2d, totalArmor, false);
        }
    }

    private void printLAStruct(Graphics2D g2d) {
        Dimension column = new Dimension(419, 413);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LARM);

        int pips = Math.min(16, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.width -= 2;
            }

        }

        if (totalArmor > 0) {
            column.height += pipShift.height;
            ImageHelper.drawISPip(g2d, column.width, column.height);
        }
    }

    private void printLLStruct(Graphics2D g2d) {
        Dimension column = new Dimension(441, 475);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
            column.width += pipShift.width;
            pipShift.width *= -1;

            if (pos % 4 == 0) {
                column.width -= 3;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height + 2;
        }

        if (totalArmor < 1) {
            return;
        }
        column.height -= 3;
        column.width -= pipShift.width + 1;
        ImageHelper.drawISPip(g2d, column.width, column.height);
    }

    private void printRLStruct(Graphics2D g2d) {
        Dimension column = new Dimension(484, 475);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
            column.width -= pipShift.width;
            pipShift.width *= -1;

            if (pos % 4 == 0) {
                column.width += 3;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height + 2;
        }

        if (totalArmor < 1) {
            return;
        }
        column.height -= 3;
        column.width += pipShift.width + 1;
        ImageHelper.drawISPip(g2d, column.width, column.height);
    }

    private void printRAStruct(Graphics2D g2d) {
        Dimension column = new Dimension(506, 413);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

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

    private void printLTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(435, 415);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;

            if (pos % 3 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width * 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;
        pipShift.width *= -1;
        column.height += pipShift.height / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;

            if (pos % 2 == 0) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }

    private void printRTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(481, 415);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;

            if (pos % 3 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;
        // pipShift.width *= -1;
        column.height += pipShift.height / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;

            if (pos % 2 == 0) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }

    private void printCTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(457, 423);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = Math.min(27, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;

            if (pos % 3 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        column.width += pipShift.width / 2;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d, column.width, column.height);
            column.width += pipShift.width;
            if (pos % 2 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }
    }

    private void printHeadStruct(Graphics2D g2d) {
        ImageHelper.drawISPip(g2d, 463, 403);
        ImageHelper.drawISPip(g2d, 460, 410);
        ImageHelper.drawISPip(g2d, 466, 410);
    }

    private void setCritConnection(Mounted m, int startx, int starty, int endx, int endy, Graphics2D g2d) {
        if (m == null) {
            printCritConnection(g2d, startMountx, startMounty, endMountx, endMounty);
            startingMount = null;
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if (startingMount == null && UnitUtil.getCritsUsed(mech, m.getType()) > 1) {
            startingMount = m;
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if (!m.equals(startingMount)) {
            printCritConnection(g2d, startMountx, startMounty, endMountx, endMounty);
            if (UnitUtil.getCritsUsed(mech, m.getType()) > 1) {
                startingMount = m;
            } else {
                startingMount = null;
            }
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if (m.equals(startingMount)) {
            endMounty = endy;
        }

    }

    private void printCritConnection(Graphics2D g2d, int startx, int starty, int endx, int endy) {
        if (starty == endy) {
            return;
        }

        g2d.drawLine(startx - 1, starty - 6, startx - 4, starty - 6);
        g2d.drawLine(startx - 4, starty - 6, endx - 4, endy);
        g2d.drawLine(endx - 1, endy, endx - 4, endy);
    }

    private void printLocationCriticals(Graphics2D g2d, int location, int lineStart, int linePoint, int lineFeed) {
        Font font;
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            font = UnitUtil.deriveFont(true, 7.0f);
            g2d.setFont(font);
            CriticalSlot cs = mech.getCritical(location, slot);

            if (cs == null) {
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);
                g2d.drawString("Roll Again", lineStart, linePoint);
                setCritConnection(null, lineStart, linePoint, lineStart, linePoint, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                if (cs.getIndex() == Mech.SYSTEM_ENGINE) {
                    String engineName = "Fusion Engine";

                    switch (mech.getEngine().getEngineType()) {
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
                    g2d.drawString(engineName, lineStart, linePoint);
                } else {
                    String critName = mech.getSystemName(cs.getIndex());

                    if (critName.indexOf("Standard") > -1) {
                        critName = critName.replace("Standard ", "");
                    }

                    if ((cs.getIndex() >= Mech.ACTUATOR_UPPER_ARM && cs.getIndex() <= Mech.ACTUATOR_HAND) || (cs.getIndex() >= Mech.ACTUATOR_UPPER_LEG && cs.getIndex() <= Mech.ACTUATOR_FOOT)) {
                        critName += " Actuator";
                    }
                    g2d.drawString(critName, lineStart, linePoint);
                }
                setCritConnection(null, lineStart, linePoint, lineStart, linePoint, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = cs.getMount();
                setCritConnection(m, lineStart, linePoint, lineStart, linePoint, g2d);

                StringBuffer critName = new StringBuffer(m.getName());

                if (UnitUtil.isTSM(m.getType())) {
                    critName.setLength(0);
                    critName.append("Triple-Strength Myomer");
                }

                if (m.isRearMounted()) {
                    critName.append("(R)");
                } else if (m.getType() instanceof AmmoType) {
                    AmmoType ammo = (AmmoType) m.getType();

                    critName = new StringBuffer("Ammo (");
                    critName.append(ammo.getShortName().trim());
                    if (critName.toString().endsWith("Ammo")) {
                        critName.setLength(critName.length() - 5);
                        critName.trimToSize();
                    }

                    if (critName.indexOf("-capable") > -1) {
                        int startPos = critName.indexOf("-capable");
                        critName.delete(startPos, startPos + "-capable".length());
                        critName.trimToSize();
                    }
                    critName.append(") ");
                    critName.append(ammo.getShots());

                }

                if (!m.getType().isHittable()) {
                    font = UnitUtil.deriveFont(true, 7.0f);
                    g2d.setFont(font);
                } else {
                    g2d.setFont(UnitUtil.getNewFont(g2d, critName.toString(), true, 86, 7.0f));
                }

                if (m.getType() instanceof MiscType && m.getType().hasFlag(MiscType.F_C3I)) {
                    ImageHelper.printC3iName(g2d, lineStart, linePoint, font);
                } else if (m.getType() instanceof MiscType && m.getType().hasFlag(MiscType.F_C3S)) {
                    ImageHelper.printC3sName(g2d, lineStart, linePoint, font);
                } else if (m.getType() instanceof WeaponType && m.getType().hasFlag(WeaponType.F_C3M)) {
                    ImageHelper.printC3mName(g2d, lineStart, linePoint, font);
                } else {
                    g2d.drawString(critName.toString(), lineStart, linePoint);
                }
            }
            linePoint += lineFeed;

            if (slot > 0 && slot % 2 == 0) {
                linePoint++;
            }

            if (slot == 5) {
                linePoint += lineFeed / 2;
            }

        }
        setCritConnection(null, lineStart, linePoint, lineStart, linePoint, g2d);

    }

    private void printMekImage(Graphics2D g2d, Image img) {

        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(200, img.getHeight(null));
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 172 + ((200 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);
        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            g2d.drawImage(ImageHelper.getGyroPipImage(), 235, 588, 9, 8, null);
        }

    }

    private void printLACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LARM)) {
            return;
        }

        int lineStart = 98;
        int linePoint = 398;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printRACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RARM)) {
            return;
        }

        int lineStart = 342;
        int linePoint = 398;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printLLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LLEG)) {
            return;
        }

        int lineStart = 93;
        int linePoint = 671;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printLTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LT)) {
            return;
        }

        int lineStart = 104;
        int linePoint = 534;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printHeadCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_HEAD)) {
            return;
        }

        int lineStart = 196;
        int linePoint = 388;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printRTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RT)) {
            return;
        }

        int lineStart = 348;
        int linePoint = 534;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printRLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RLEG)) {
            return;
        }

        int lineStart = 338;
        int linePoint = 671;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }

    private void printCTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_CT)) {
            return;
        }

        int lineStart = 236;
        int linePoint = 458;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        g2d.drawString("(CASE)", lineStart, linePoint);
    }
}