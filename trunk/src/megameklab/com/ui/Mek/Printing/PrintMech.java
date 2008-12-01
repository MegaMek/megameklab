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

        if (awtImage != null) {
            System.out.println("Width: " + awtImage.getWidth(null));
            System.out.println("Height: " + awtImage.getHeight(null));
        }
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, awtImage, awtHud, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, Image image, Image hud, PageFormat pageFormat) {
        // System.out.println("printImage(Graphics2D g2d, Image image)");
        if (g2d == null)
            return;

        System.gc();
        // g2d.drawImage(image, 2, 0, (int)pageFormat.getImageableWidth(),
        // (int)pageFormat.getImageableHeight(), null);
        g2d.drawImage(image, 18, 18, 558, 738, null);
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

        // Armor Pips
        printLAArmor(g2d);
        printRAArmor(g2d);
        printLTArmor(g2d);
        printRTArmor(g2d);
        printCTArmor(g2d);
        printLLArmor(g2d);
        printRLArmor(g2d);
        printLTRArmor(g2d);
        printRTRArmor(g2d);
        printCTRArmor(g2d);
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
        Font font = new Font("Eurostile LT Std", Font.BOLD, 10);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis().toUpperCase() + " " + mech.getModel().toUpperCase(), 49, 121);

        font = new Font("Eurostile LT Std", Font.PLAIN, 8);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
        g2d.drawString(Integer.toString(mech.getRunMP()), 79, 155);
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

        g2d.drawString("2008", 102.5f, 745f);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = new Font("Eurostile Bold", Font.PLAIN, 8);
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
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;

            if (pos % 10 == 0) {
                column.height -= pipShift.height * 10;
                column.width += pipShift.width;
            }

        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 6);
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

                    this.mech = currentMech;
                    this.awtHud = ImageHelper.getFluffImage(currentMech);
                    pj.setJobName(mech.getChassis() + " " + mech.getModel());

                    pj.print();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printRLArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(499, 181);
        Dimension middleColumn = new Dimension(509, 252);
        Dimension bottomColumn = new Dimension(529, 270);
        Dimension footColumn = new Dimension(519, 294);
        Dimension pipShift = new Dimension(8, -2);

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        int maxPipsPerTopColumn = 2;
        if ( pips < 17 ) {
            maxPipsPerTopColumn = 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width += pipShift.width;
            topColumn.height += pipShift.height;
            if (pos % maxPipsPerTopColumn == 0) {
                pipShift.width *= -1;
                topColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                topColumn.height += pipShift.height + 7;
            }

            if (pos % 4 == 0) {
                topColumn.width += 2;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",middleColumn.width, middleColumn.height);
            middleColumn.width += pipShift.width;
            middleColumn.height += pipShift.height;
            if (pos % 4 == 0) {
                pipShift.width *= -1;
                middleColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                middleColumn.height += pipShift.height + 7;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",bottomColumn.width, bottomColumn.height);
            bottomColumn.width += pipShift.width;
            bottomColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                bottomColumn.height += pipShift.height + 7;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",footColumn.width, footColumn.height);
            footColumn.width += pipShift.width;
        }

    }

    private void printLLArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(443, 179);
        Dimension middleColumn = new Dimension(417, 247);
        Dimension bottomColumn = new Dimension(430, 272);
        Dimension footColumn = new Dimension(406, 294);
        Dimension pipShift = new Dimension(8, 2);

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        int maxPipsPerTopColumn = 2;
        if ( pips < 17 ) {
            maxPipsPerTopColumn = 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width += pipShift.width;
            topColumn.height += pipShift.height;
            if (pos % maxPipsPerTopColumn == 0) {
                pipShift.width *= -1;
                topColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                topColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }

            if (pos % 4 == 0) {
                topColumn.width -= 2;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",middleColumn.width, middleColumn.height);
            middleColumn.width += pipShift.width;
            middleColumn.height += pipShift.height;
            if (pos % 4 == 0) {
                pipShift.width *= -1;
                middleColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                middleColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }

            // if ( pos % 4 == 0 ) {
            // topColumn.width -=2;
            // }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",bottomColumn.width, bottomColumn.height);
            bottomColumn.width += pipShift.width;
            bottomColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                bottomColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",footColumn.width, footColumn.height);
            footColumn.width += pipShift.width;
        }

    }

    private void printLAArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension rightColumn = new Dimension(417, 77);
        Dimension centerColumn = new Dimension(409, 84);
        Dimension leftColumn = new Dimension(402, 90);
        Dimension pipShift = new Dimension(-1, 7);

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        if ( pips < 12 ) {
            pipShift.height = (pipShift.height*12)/pips;
        }
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",centerColumn.width, centerColumn.height);
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

        pips = Math.min(12, totalArmor);

        if ( pips < 8 ) {
            pipShift.height = (pipShift.height*8)/pips;
        }

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",rightColumn.width, rightColumn.height);
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

        pips = Math.min(10, totalArmor);

        if ( pips < 6 ) {
            pipShift.height = (pipShift.height*6)/pips;
            leftColumn.width -= 2;
        }
        
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",leftColumn.width, leftColumn.height);
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }
            if (pos == 6) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

    }

    private void printRAArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension rightColumn = new Dimension(548, 90);
        Dimension centerColumn = new Dimension(540, 84);
        Dimension leftColumn = new Dimension(533, 77);
        Dimension pipShift = new Dimension(1, 7);

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        if (totalArmor < 1) {
            return;
        }

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        if ( pips < 12 ) {
            pipShift.height = (pipShift.height*12)/pips;
        }

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",centerColumn.width, centerColumn.height);
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

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        if ( pips < 8 ) {
            pipShift.height = (pipShift.height*8)/pips;
        }
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",leftColumn.width, leftColumn.height);
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }

            if (pos == 8) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;
        if ( pips < 6 ) {
            pipShift.height = (pipShift.height*6)/pips;
            leftColumn.width -= 2;
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",rightColumn.width, rightColumn.height);
            rightColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                rightColumn.width += pipShift.width;
            }
            if (pos == 6) {
                rightColumn.height += pipShift.height + 3;
                rightColumn.width += pipShift.width;
            }
        }

    }

    private void printLTArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(430, 88);
        Dimension middleColumn = new Dimension(445, 126);
        Dimension bottomColumn = new Dimension(438, 161);
        Dimension topPipShift = new Dimension(6, 7);
        Dimension middlePipShift = new Dimension(6, 7);
        Dimension bottomPipShift = new Dimension(6, 7);
        
        int maxTopPips = 25;
        int maxMiddlePips = 10;
        int maxBottemPips = 7;
         
        int topPipsPerLine = 5;
        int middlePipsPerLine = 2;

        int totalArmor = mech.getArmor(Mech.LOC_LT);


        if (totalArmor < 36) {
            topPipsPerLine = (int)Math.ceil((double)totalArmor/10d);
            topPipShift.width += 4-(int)Math.ceil((double)totalArmor/10d);
            topPipShift.height += Math.max(0, 2-(int)Math.ceil((double)totalArmor/10d));
            maxTopPips = Math.min(20,(totalArmor*60)/100);
            maxMiddlePips = (totalArmor*24)/100;
            maxBottemPips = totalArmor - (maxTopPips+maxMiddlePips);
            if ( maxMiddlePips <= 5) {
                middlePipsPerLine = 1;
            }
        }

        int pips = Math.min(maxTopPips, totalArmor);
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width += topPipShift.width;
            if (pos % topPipsPerLine == 0) {
                topColumn.height += topPipShift.height;
                topPipShift.width *= -1;
                topColumn.width += topPipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(maxMiddlePips, totalArmor);
        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",middleColumn.width, middleColumn.height);
            middleColumn.width += middlePipShift.width;
            if (pos % middlePipsPerLine == 0) {
                middleColumn.height += middlePipShift.height;
                middleColumn.width += 1;
                middlePipShift.width *= -1;
                if ( middlePipsPerLine > 1) {
                    middleColumn.width += middlePipShift.width;
                }
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(maxBottemPips, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",bottomColumn.width, bottomColumn.height);
            bottomColumn.width += bottomPipShift.width;
            if (pos % 4 == 0) {
                bottomColumn.height += bottomPipShift.height;
                bottomColumn.width += 1;
                bottomPipShift.width *= -1;
                bottomColumn.width += bottomPipShift.width;
            }
        }

    }

    private void printLTRArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(437, 312);
        Dimension pipShift = new Dimension(5, 5);
        int pipsPerLine = 5;
        int totalArmor = mech.getArmor(Mech.LOC_LT, true);

        if ( totalArmor < 1 ) {
            return;
        }
        
        int pips = Math.min(35, totalArmor);

        totalArmor -= pips;

        if ( pips < 30 ) {
            pipsPerLine = (int)Math.ceil((double)pips/5d);
            pipShift.height += Math.max(0,4 - (pips/6));
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
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
    }

    private void printRTArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(520, 88);
        Dimension middleColumn = new Dimension(504, 126);
        Dimension bottomColumn = new Dimension(512, 161);
        Dimension topPipShift = new Dimension(6, 7);
        Dimension middlePipShift = new Dimension(6, 7);
        Dimension bottomPipShift = new Dimension(6, 7);

        int maxTopPips = 25;
        int maxMiddlePips = 10;
        int maxBottemPips = 7;
        
        int topPipsPerLine = 5;
        int middlePipsPerLine = 2;

        int totalArmor = mech.getArmor(Mech.LOC_RT);


        if (totalArmor < 36) {
            topPipsPerLine = (int)Math.ceil((double)totalArmor/10d);
            topPipShift.width += 4-(int)Math.ceil((double)totalArmor/10d);
            topPipShift.height += Math.max(0, 2-(int)Math.ceil((double)totalArmor/10d));
            maxTopPips = Math.min(20,(totalArmor*60)/100);
            maxMiddlePips = (totalArmor*24)/100;
            maxBottemPips = totalArmor - (maxTopPips+maxMiddlePips);
            if ( maxMiddlePips <= 5) {
                middlePipsPerLine = 1;
            }
        }

        int pips = Math.min(maxTopPips, totalArmor);
        totalArmor -= pips;


        for (int pos = 1; pos <= pips; pos++) {
            //g2d.drawString("O",topColumn.width, topColumn.height);
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width -= topPipShift.width;
            if (pos % topPipsPerLine == 0) {
                topColumn.height += topPipShift.height;
                topPipShift.width *= -1;
                topColumn.width -= topPipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(maxMiddlePips, totalArmor);
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",middleColumn.width, middleColumn.height);
            middleColumn.width -= middlePipShift.width;
            if (pos % middlePipsPerLine == 0) {
                middleColumn.height += middlePipShift.height;
                middleColumn.width -= 1;
                middlePipShift.width *= -1;
                if ( middlePipsPerLine > 1) {
                    middleColumn.width -= middlePipShift.width;
                }
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(maxBottemPips, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",bottomColumn.width, bottomColumn.height);
            bottomColumn.width -= bottomPipShift.width;
            if (pos % 4 == 0) {
                bottomColumn.height += bottomPipShift.height;
                bottomColumn.width -= 1;
                bottomPipShift.width *= -1;
                bottomColumn.width -= bottomPipShift.width;
            }
        }

    }

    private void printRTRArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(514, 312);
        Dimension pipShift = new Dimension(5, 5);

        int pipsPerLine = 5;
        int totalArmor = mech.getArmor(Mech.LOC_LT, true);

        if ( totalArmor < 1 ) {
            return;
        }
        
        int pips = Math.min(35, totalArmor);

        totalArmor -= pips;

        if ( pips < 30 ) {
            pipsPerLine = (int)Math.ceil((double)pips/5d);
            pipShift.height += Math.max(0,4 - (pips/6));
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width -= pipShift.width;
            if (pos % pipsPerLine == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                if (pos >= 30) {
                    topColumn.width -= pipShift.width;
                } else {
                    topColumn.width -= pipShift.width * 2;
                }
            }
        }
    }

    private void printCTArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(464, 105);
        Dimension middleColumn = new Dimension(481, 172);
        Dimension bottomColumn = new Dimension(475, 185);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_CT);

        int pips = Math.min(55, totalArmor);

        totalArmor -= pips;

        //spread out the first 55 pips.
        if ( pips <= 35 ) {
            int pipsPerLine = 4;
            boolean threePerLine = true;
            topColumn = new Dimension(462, 105);
            Dimension fourRowColumn = new Dimension(462, 105);
            Dimension threeRowColumn = new Dimension(466, 105);
            pipShift.width += 3;
            pipShift.height += 3;

            for (int pos = 1; pos <= pips; pos++) {
                g2d.drawString("O",topColumn.width, topColumn.height);
                if ( pos == 14 ) {
                    pipShift.width -= 1;
                    fourRowColumn.width += 1;
                    threeRowColumn.width += 1;
                }
                if ( pos == 21 ) {
                    pipShift.width -= 1;
                    fourRowColumn.width += 1;
                    threeRowColumn.width += 1;
                }
                topColumn.width += pipShift.width;
                if (--pipsPerLine == 0) {
                    topColumn.height += pipShift.height;
                    if ( threePerLine ) {
                        pipsPerLine = 3;
                        threePerLine = false;
                        topColumn.width = threeRowColumn.width;
                    }else {
                        threePerLine = true;
                        pipsPerLine = 4;
                        topColumn.width = fourRowColumn.width;
                    }
                }
            }
            return;
        }
        
        if ( pips <= 55 ) {
            int pipsPerLine = 4;
            topColumn = new Dimension(464, 105);
            if ( pips >= 50 ) {
                topColumn = new Dimension(466, 105);
            } else {
                topColumn = new Dimension(464, 105);
            }

            pipShift.width += Math.min(1,5-(pips/10));
            pipShift.height += 5-(pips/10);

            for (int pos = 1; pos <= pips; pos++) {
                g2d.drawString("O",topColumn.width, topColumn.height);
                topColumn.width += pipShift.width;
                if (pos % pipsPerLine == 0) {
                    topColumn.height += pipShift.height;
                    pipShift.width *= -1;
                    topColumn.width += pipShift.width;
                }
            }
            return;
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",middleColumn.width, middleColumn.height);
            middleColumn.width += pipShift.width;
            if (pos % 3 == 0) {
                middleColumn.height += pipShift.height;
                pipShift.width *= -1;
                middleColumn.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        g2d.drawString("O",bottomColumn.width, bottomColumn.height);

    }

    private void printHeadArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);

        Dimension fillCircle = new Dimension(4, 4);

        if (mech.getArmor(Mech.LOC_HEAD) >= 1) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 68, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",475, 73);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 2) {
            g2d.setColor(Color.white);
            g2d.fillOval(473, 74, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",472, 79);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 3) {
            g2d.setColor(Color.white);
            g2d.fillOval(479, 74, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",478, 79);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 4) {
            g2d.setColor(Color.white);
            g2d.fillOval(470, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",469, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 5) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",475, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 6) {
            g2d.setColor(Color.white);
            g2d.fillOval(482, 79, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",481, 84);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 7) {
            g2d.setColor(Color.white);
            g2d.fillOval(470, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",469, 90);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 8) {
            g2d.setColor(Color.white);
            g2d.fillOval(476, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",475, 90);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 9) {
            g2d.setColor(Color.white);
            g2d.fillOval(482, 85, fillCircle.width, fillCircle.height);
            g2d.setColor(Color.black);
            g2d.drawString("O",481, 90);
        }
        g2d.setColor(Color.black);
    }

    private void printCTRArmor(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension topColumn = new Dimension(470, 299);
        Dimension pipShift = new Dimension(5, 5);
        int pipsPerRow = 4;
        int totalArmor = mech.getArmor(Mech.LOC_CT, true);

        if ( totalArmor < 1 ) {
            return;
        }
        
        int pips = Math.min(56, totalArmor);

        totalArmor -= pips;

        if ( pips < 40 ) {
            pipsPerRow = Math.min(4, (int)Math.ceil((double)pips/5d));
            pipShift.height += 11 - (pips/4);
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",topColumn.width, topColumn.height);
            topColumn.width += pipShift.width;
            if (pos % pipsPerRow == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width * 2;
            }
        }
    }

    private void printLAStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(419, 413);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LARM);

        int pips = Math.min(16, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.width -= 2;
            }

        }

        if (totalArmor > 0) {
            column.height += pipShift.height;
            g2d.drawString("O",column.width, column.height);
        }
    }

    private void printLLStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(441, 475);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height + 2;
        }

        if (totalArmor < 1) {
            return;
        }
        column.height -= 3;
        column.width -= pipShift.width + 1;
        g2d.drawString("O",column.width, column.height);
    }

    private void printRLStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(484, 475);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height + 2;
        }

        if (totalArmor < 1) {
            return;
        }
        column.height -= 3;
        column.width += pipShift.width + 1;
        g2d.drawString("O",column.width, column.height);
    }

    private void printRAStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(506, 413);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = Math.min(16, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width -= pipShift.width;

            if (pos % 4 == 0) {
                column.width += 2;
            }

        }

        if (totalArmor > 0) {
            column.height += pipShift.height;
            g2d.drawString("O",column.width, column.height);
        }
    }

    private void printLTStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(435, 415);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.width += pipShift.width;

            if (pos % 2 == 0) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }

    private void printRTStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(481, 415);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.width += pipShift.width;

            if (pos % 2 == 0) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }

    private void printCTStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);
        Dimension column = new Dimension(457, 423);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = Math.min(27, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawString("O",column.width, column.height);
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
            g2d.drawString("O",column.width, column.height);
            column.width += pipShift.width;
            if (pos % 2 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }
    }

    private void printHeadStruct(Graphics2D g2d) {
        Font font = new Font("Eurostile LT Std", Font.PLAIN, 7);
        g2d.setFont(font);

        g2d.drawString("O",462, 398);
        g2d.drawString("O",458, 405);
        g2d.drawString("O",467, 405);
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
            font = new Font("Eurostile LT Std", Font.BOLD, 7);
            g2d.setFont(font);
            CriticalSlot cs = mech.getCritical(location, slot);

            if (cs == null) {
                font = new Font("Eurostile LT Std", Font.PLAIN, 7);
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

                    if ( (cs.getIndex() >= Mech.ACTUATOR_UPPER_ARM && cs.getIndex() <= Mech.ACTUATOR_HAND) 
                            || (cs.getIndex() >= Mech.ACTUATOR_UPPER_LEG && cs.getIndex() <= Mech.ACTUATOR_FOOT)) {
                        critName += " Actuator";
                    }
                    g2d.drawString(critName, lineStart, linePoint);
                }
                setCritConnection(null, lineStart, linePoint, lineStart, linePoint, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                setCritConnection(m, lineStart, linePoint, lineStart, linePoint, g2d);

                StringBuffer critName = new StringBuffer(m.getName());

                if (m.isRearMounted()) {
                    critName.append("(R)");
                } else if (m.getType() instanceof AmmoType) {
                    AmmoType ammo = (AmmoType) m.getType();

                    critName = new StringBuffer("Ammo (");
                    critName.append(ammo.getShortName().trim());
                    critName.append(") ");
                    critName.append(ammo.getShots());
                }

                if (!m.getType().isHittable()) {
                    font = new Font("Eurostile LT Std", Font.PLAIN, 7);
                    g2d.setFont(font);
                } else if (critName.length() >= 80) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 1);
                    g2d.setFont(font);
                } else if (critName.length() >= 70) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 2);
                    g2d.setFont(font);
                } else if (critName.length() >= 60) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 3);
                    g2d.setFont(font);
                } else if (critName.length() >= 50) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 4);
                    g2d.setFont(font);
                } else if (critName.length() >= 40) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 5);
                    g2d.setFont(font);
                } else if (critName.length() >= 30) {
                    font = new Font("Eurostile LT Std", Font.BOLD, 6);
                    g2d.setFont(font);
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
        g2d.drawImage(img, 235, 172, width, height, null);

        // g2d.drawRect(235, 172, 165, 200);
    }

}