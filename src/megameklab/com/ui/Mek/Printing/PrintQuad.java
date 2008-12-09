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

public class PrintQuad implements Printable {

    protected Image awtImage = null;
    protected Image awtHud = null;
    private Mech mech = null;
    private ArrayList<Mech> mechList;

    private Mounted startingMount = null;
    private int startMountx = 0;
    private int startMounty = 0;
    private int endMountx = 0;
    private int endMounty = 0;

    public PrintQuad(ArrayList<Mech> list) {
        awtImage = ImageHelper.getRecordSheet(list.get(0), false);
        mechList = list;

    //    System.out.println("Width: " + awtImage.getWidth(null));
      //  System.out.println("Height: " + awtImage.getHeight(null));

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

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printMechData(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true,10.0f);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis().toUpperCase() + " " + mech.getModel().toUpperCase(), 49, 121);

        font = UnitUtil.deriveFont(8.0f);
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

        font = new Font("Arial",Font.PLAIN,8);
        g2d.setFont(font);
        g2d.drawString("2008", 102.5f, 745f);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true,8.0f);
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
            ImageHelper.drawHeatSinkPip(g2d,column.width, column.height);
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
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_HEAD)) + ")", 485, 47);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT)) + ")", 393, 138);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT)) + ")", 553, 138);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT)) + ")", 475, 209);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LARM)) + ")", 401, 309);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RARM)) + ")", 549, 310);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LLEG)) + ")", 448, 297);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RLEG)) + ")", 501, 300);
        // Rear
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT, true)) + ")", 406, 357);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT, true)) + ")", 506, 368);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT, true)) + ")", 542, 357);
        // Internal
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LT)) + ")", 400, 418);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RT)) + ")", 521, 418);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LARM)) + ")", 398, 483);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RARM)) + ")", 523, 484);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_CT)) + ")", 459, 511);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LLEG)) + ")", 395, 532);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RLEG)) + ")", 526, 532);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 438;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart, linePoint, lineFeed);
    }

    private void printRACrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 438;
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
        int linePoint = 523;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart, linePoint, lineFeed);
    }

    private void printRTCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 523;
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
        int linePoint = 660;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart, linePoint, lineFeed);
    }

    private void printRLCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 660;
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
                Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.defaultPage();
                paper.setImageableArea(0, 0, 612, 792);
                paper.setSize(612, 792);
                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

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
        Dimension column = new Dimension(504, 142);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);
        int pips = Math.min(4, totalArmor);
        int pipsPerColumn = 2;
        
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        if ( pips < 18 ) {
            pipsPerColumn = 1;
        }

        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % pipsPerColumn == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

            if (pos % 8 == 0) {
                column.height++;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width * 2;
        pipShift.width *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }
    }

    private void printLLArmor(Graphics2D g2d) {
        Dimension column = new Dimension(448, 142);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);
        int pips = Math.min(4, totalArmor);
        int pipsPerColumn = 2;
        
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.height += pipShift.height;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        if ( pips < 18 ) {
            pipsPerColumn = 1;
        }
        
        column.width -= pipShift.width / 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % pipsPerColumn == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

            if (pos % 8 == 0) {
                column.height++;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width * 2;
        pipShift.width *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }
    }

    private void printLAArmor(Graphics2D g2d) {
        Dimension column = new Dimension(422, 145);
        Dimension pipShift = new Dimension(6, 6);
        int pipsPerColumn = 2;
        
        int pips = mech.getArmor(Mech.LOC_LARM);

        if ( pips < 22 ) {
            pipsPerColumn = 1;
        }
        
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % pipsPerColumn == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
                column.width -= 1;
            }

            if (pos % 4 == 0) {
                column.width += 1;
            }

            if (pos % 8 == 0) {
                column.height += 1;
            }
        }

    }

    private void printRAArmor(Graphics2D g2d) {
        Dimension column = new Dimension(525, 147);
        Dimension pipShift = new Dimension(6, 6);
        int pipsPerColumn = 2;
        
        int pips = mech.getArmor(Mech.LOC_RARM);

        if ( pips < 22 ) {
            pipsPerColumn = 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % pipsPerColumn == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
                column.width += 1;
            }

            if (pos % 4 == 0) {
                column.width -= 1;
            }

            if (pos % 8 == 0) {
                column.height += 1;
            }
        }

    }

    private void printLTArmor(Graphics2D g2d) {
        Dimension column = new Dimension(424, 69);
        Dimension pipShift = new Dimension(5, 7);
        int pips = mech.getArmor(Mech.LOC_LT);
        int pipsPerLine = 5;

        if (pips <= 10) {
            pipsPerLine = 3;
            pipShift.width += 5;
            pipShift.height += 7;
        } else if (pips <= 15) {
            pipsPerLine = 3;
            pipShift.width += 4;
            pipShift.height += 6;
        } else if (pips <= 20) {
            pipsPerLine = 4;
            pipShift.width += 3;
            pipShift.height += 5;
        }else if (pips <= 30) {
            pipsPerLine = 5;
            pipShift.width += 1;
            pipShift.height += 4;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % pipsPerLine == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            if (pos == 40) {
                column.width += pipShift.width * 4;
                pipShift.width *= -1;
            }
        }

    }

    private void printLTRArmor(Graphics2D g2d) {
        Dimension column = new Dimension(450, 311);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_LT, true));

        int pips = Math.min(2, totalArmor);

        pipShift.height += Math.max(0,4 - (totalArmor/5));
                
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        pipShift.width *= -1;
        column.width += pipShift.width;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        // column.width += pipShift.width;
        pipShift.width *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        pipShift.width *= -1;
        column.width += pipShift.width;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % 5 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(8, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(3, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

    }

    private void printRTArmor(Graphics2D g2d) {
        Dimension column = new Dimension(507, 69);
        Dimension pipShift = new Dimension(5, 7);
        int pips = mech.getArmor(Mech.LOC_RT);
        int pipsPerLine = 5;

        if (pips <= 10) {
            pipsPerLine = 3;
            pipShift.width += 5;
            pipShift.height += 7;
        } else if (pips <= 15) {
            pipsPerLine = 3;
            pipShift.width += 4;
            pipShift.height += 6;
        } else if (pips <= 20) {
            pipsPerLine = 4;
            pipShift.width += 3;
            pipShift.height += 5;
        }else if (pips <= 30) {
            pipsPerLine = 5;
            pipShift.width += 1;
            pipShift.height += 4;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % pipsPerLine == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }
    }

    private void printRTRArmor(Graphics2D g2d) {
        Dimension column = new Dimension(496, 311);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_RT, true));

        int pips = Math.min(2, totalArmor);

        pipShift.height += Math.max(0,4 - (totalArmor/5));
        
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        // pipShift.width *= -1;
        column.width -= pipShift.width * 2;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        column.width -= pipShift.width * 3;
        // pipShift.width *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        // pipShift.width *= -1;
        column.width -= pipShift.width * 4;
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % 5 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(8, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(3, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }
    }

    private void printCTArmor(Graphics2D g2d) {
        Dimension column = new Dimension(462, 108);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getArmor(Mech.LOC_CT);

        int pipsPerLine = 6;

        if (pips <= 20) {
            pipsPerLine = 3;
            pipShift.width += 3;
            pipShift.height += 3;
        } else if (pips <= 30) {
            pipsPerLine = 4;
            pipShift.width += 2;
        } else if (pips <= 40) {
            pipsPerLine = 5;
            pipShift.width += 1;
        }

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % pipsPerLine == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            if (pos == 60) {
                column.width += pipShift.width * 2;
            }
        }
    }

    private void printHeadArmor(Graphics2D g2d) {

        Dimension head = new Dimension(466, 83);
        Dimension pipShift = new Dimension(7, 6);

        int pips = mech.getArmor(Mech.LOC_HEAD);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,head.width, head.height);
            head.width += pipShift.width;
            if (pos == 4 || pos == 7) {
                head.height += pipShift.height;
                pipShift.width *= -1;
                head.width += pipShift.width;
                head.width += pipShift.width / 2;
            }

        }

    }

    private void printCTRArmor(Graphics2D g2d) {
        Dimension column = new Dimension(464, 308);
        Dimension pipShift = new Dimension(6, 6);

        int pips = Math.min(45, mech.getArmor(Mech.LOC_CT, true));

        pipShift.height += Math.max(0,8 - (pips/5));
        
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d,column.width, column.height);
            column.width += pipShift.width;
            if (pos % 5 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }
    }

    private void printLAStruct(Graphics2D g2d) {
        Dimension column = new Dimension(427, 471);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LARM);

        int pips = Math.min(21, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.width -= 1;
            }

        }

    }

    private void printLLStruct(Graphics2D g2d) {
        Dimension column = new Dimension(445, 465);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = Math.min(21, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;
        }

    }

    private void printRLStruct(Graphics2D g2d) {
        Dimension column = new Dimension(480, 465);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = Math.min(21, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.height += pipShift.height;
            column.width += pipShift.width;
            pipShift.width *= -1;
        }
    }

    private void printRAStruct(Graphics2D g2d) {
        Dimension column = new Dimension(498, 471);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = Math.min(21, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width -= pipShift.width;

            if (pos % 4 == 0) {
                column.width += 1;
            }

        }

    }

    private void printLTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(424, 416);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getInternal(Mech.LOC_LT);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }

    }

    private void printRTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(484, 416);
        Dimension pipShift = new Dimension(6, 6);

        
        int pips = mech.getInternal(Mech.LOC_RT);
        
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

            if (pos == 20) {
                column.width += pipShift.width * 3;
            }

        }

    }

    private void printCTStruct(Graphics2D g2d) {
        Dimension column = new Dimension(454, 433);
        Dimension pipShift = new Dimension(6, 5);

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = Math.min(28, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.width += pipShift.width;

            if (pos % 4 == 0) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width / 2;

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawISPip(g2d,column.width, column.height);
            column.width += pipShift.width;
        }
    }

    private void printHeadStruct(Graphics2D g2d) {
        ImageHelper.drawISPip(g2d,462, 414);
        ImageHelper.drawISPip(g2d,458, 421);
        ImageHelper.drawISPip(g2d,467, 421);
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
            font = UnitUtil.deriveFont(true,7.0f);
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
                    font = UnitUtil.deriveFont(7.0f);
                    g2d.setFont(font);
                } else {
                    g2d.setFont(UnitUtil.getNewFont(g2d, critName.toString(), false, 86, 7.0f));
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