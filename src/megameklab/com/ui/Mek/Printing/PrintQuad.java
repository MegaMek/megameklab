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
import java.util.Vector;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
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

        // System.out.println("Width: " + awtImage.getWidth(null));
        // System.out.println("Height: " + awtImage.getHeight(null));

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
        String mekName = mech.getChassis().toUpperCase() + " " + mech.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, mekName, true, 180, 10.0f));
        g2d.drawString(mekName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if (mech.hasTSM()) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 79, 155);
        } else if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 79, 155);
        }

        if (mech.hasUMU()) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = "UMU: ";
            g2d.drawString(movment, 34, 166);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            g2d.drawString(Integer.toString(mech.getAllUMUCount()), 79, 166);
        } else if (mech.getJumpMP() > 0) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = "Jumping: ";
            g2d.drawString(movment, 34, 166);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            g2d.drawString(Integer.toString(mech.getJumpMP()), 79, 166);
        }

        int tonnage = (int) Math.ceil(mech.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        String techBase = "Inner Sphere";

        if (mech.isMixedTech()) {
            if (mech.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (mech.isClan()) {
            techBase = "Clan";
        }

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        if (mech.isIndustrial()) {
            if (mech.isPrimitive()) {
                ImageHelper.printCenterString(g2d, "(Primitive Industrial)", font, startLine, nextDataLine);
            } else {
                ImageHelper.printCenterString(g2d, "(Industrial)", font, startLine, nextDataLine);
            }

            nextDataLine += lineFeed;
        } else if (mech.isPrimitive()) {
            ImageHelper.printCenterString(g2d, "(Primitive)", font, startLine, nextDataLine);
            nextDataLine += lineFeed;
        } else {

            switch (mech.getTechLevel()) {

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
        }

        String yearFluff = mech.getYear() + " " + mech.getSource();
        ImageHelper.printCenterString(g2d, yearFluff.trim(), font, startLine, nextDataLine);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(mech.calculateBattleValue(true, true)), 150, 350);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost()) + " C-bills", 52, 350);

        String isName = "";
        if (mech.hasCompositeStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_COMPOSITE);
        } else if (mech.hasReinforcedStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_REINFORCED);
        }

        if (isName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, isName, true, 44, 10.0f));
            g2d.drawString(isName, 444, 556);
        }

        String armorName = "";

        if ( mech.getArmorType() == EquipmentType.T_ARMOR_HARDENED ){
            armorName = EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_HARDENED);
        }

        if (armorName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString(armorName, 461, 279);
        }

        g2d.setFont(UnitUtil.getNewFont(g2d, techBase, false, 51, 10.0f));
        g2d.drawString(techBase, 177, 145);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 106.5f, 744.5f);

        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            g2d.drawImage(ImageHelper.getGyroPipImage(), 235, 588, 9, 8, null);
        }

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
            ImageHelper.drawHeatSinkPip(g2d, column.width, column.height);
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
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_HEAD)), 485, 45);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LT)), 393, 138);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RT)), 553, 138);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_CT)), 475, 209);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LARM)), 401, 309);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RARM)), 549, 310);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LLEG)), 448, 297);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RLEG)), 501, 300);
        // Rear
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LT, true)), 406, 357);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_CT, true)), 506, 366);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RT, true)), 542, 357);
        // Internal
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LT)), 400, 418);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RT)), 521, 418);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LARM)), 398, 483);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RARM)), 523, 484);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_CT)), 459, 511);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LLEG)), 395, 532);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RLEG)), 526, 532);
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

                    mech = currentMech;
                    awtHud = ImageHelper.getFluffImage(currentMech, "mech");
                    pj.setJobName(mech.getChassis() + " " + mech.getModel());

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

    private void printRLArmor(Graphics2D g2d) {
        float[] column = { 504, 142 };
        float[] pipShift = { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);
        int pips = 4;
        int pipsPerColumn = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
        }

        pips = 30;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % pipsPerColumn == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

            if (pos % 8 == 0) {
                column[1]++;
            }
        }

        pips = 8;

        column[0] += pipShift[0] * 2;
        pipShift[0] *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLArmor(Graphics2D g2d) {
        float[] column = { 448, 142 };
        float[] pipShift = { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);
        int pips = 4;
        int pipsPerColumn = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
        }

        if (totalArmor < 1) {
            return;
        }

        pips = 30;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % pipsPerColumn == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

            if (pos % 8 == 0) {
                column[1]++;
            }
        }

        pips = 8;

        column[0] += pipShift[0] * 2;
        pipShift[0] *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLAArmor(Graphics2D g2d) {
        float[] column = { 422, 145 };
        float[] pipShift = { 6, 6 };
        int pipsPerColumn = 2;

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % pipsPerColumn == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
                column[0] -= 1;
            }

            if (pos % 4 == 0) {
                column[0] += 1;
            }

            if (pos % 8 == 0) {
                column[1] += 1;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAArmor(Graphics2D g2d) {
        float[] column = { 525, 147 };
        float[] pipShift = { 6, 6 };
        int pipsPerColumn = 2;

        int totalArmor = mech.getArmor(Mech.LOC_RARM);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % pipsPerColumn == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
                column[0] += 1;
            }

            if (pos % 4 == 0) {
                column[0] -= 1;
            }

            if (pos % 8 == 0) {
                column[1] += 1;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTArmor(Graphics2D g2d) {
        float[] column = { 424, 69 };
        float[] pipShift = { 5, 7 };
        int totalArmor = mech.getArmor(Mech.LOC_LT);
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % pipsPerLine == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
            if (pos == 40) {
                column[0] += pipShift[0] * 4;
                pipShift[0] *= -1;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTRArmor(Graphics2D g2d) {
        float[] column = { 450, 311 };
        float[] pipShift = { 6, 6 };

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_LT, true));

        int pips = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 3;

        column[1] += pipShift[1];
        pipShift[0] *= -1;
        column[0] += pipShift[0];
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 4;

        column[1] += pipShift[1];
        // column[0] += pipShift[0];
        pipShift[0] *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 10;

        column[1] += pipShift[1];
        pipShift[0] *= -1;
        column[0] += pipShift[0];
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % 5 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 8;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 3;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRTArmor(Graphics2D g2d) {
        float[] column = { 507, 69 };
        float[] pipShift = { 5, 7 };
        int totalArmor = mech.getArmor(Mech.LOC_RT);
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % pipsPerLine == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRTRArmor(Graphics2D g2d) {
        float[] column = { 496, 311 };
        float[] pipShift = { 6, 6 };

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_RT, true));

        int pips = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 3;

        column[1] += pipShift[1];
        // pipShift[0] *= -1;
        column[0] -= pipShift[0] * 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 4;

        column[1] += pipShift[1];
        column[0] -= pipShift[0] * 3;
        // pipShift[0] *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 10;

        column[1] += pipShift[1];
        // pipShift[0] *= -1;
        column[0] -= pipShift[0] * 4;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % 5 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 8;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 3;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printCTArmor(Graphics2D g2d) {
        float[] column = { 462, 108 };
        float[] pipShift = { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_CT);

        int pipsPerLine = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 62; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % pipsPerLine == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
            if (pos == 60) {
                column[0] += pipShift[0] * 2;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printHeadArmor(Graphics2D g2d) {

        Dimension head = new Dimension(466, 83);
        Dimension pipShift = new Dimension(7, 6);

        int pips = mech.getArmor(Mech.LOC_HEAD);

        for (int pos = 1; pos <= pips; pos++) {
            ImageHelper.drawArmorPip(g2d, head.width, head.height);
            head.width += pipShift.width;
            if ((pos == 4) || (pos == 7)) {
                head.height += pipShift.height;
                pipShift.width *= -1;
                head.width += pipShift.width;
                head.width += pipShift.width / 2;
            }

        }

    }

    private void printCTRArmor(Graphics2D g2d) {
        float[] column = { 464, 308 };
        float[] pipShift = { 6, 6 };

        int totalArmor = Math.min(45, mech.getArmor(Mech.LOC_CT, true));

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 45; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
            if (pos % 5 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLAStruct(Graphics2D g2d) {
        float[] column = { 427, 471 };
        float[] pipShift = { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_LARM);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[0] -= 1;
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLStruct(Graphics2D g2d) {
        float[] column = { 445, 465 };
        float[] pipShift = { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] += pipShift[0];
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRLStruct(Graphics2D g2d) {
        float[] column = { 480, 465 };
        float[] pipShift = { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
            column[0] += pipShift[0];
            pipShift[0] *= -1;
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAStruct(Graphics2D g2d) {
        float[] column = { 498, 471 };
        float[] pipShift = { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(pips);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] -= pipShift[0];

            if (pos % 4 == 0) {
                column[0] += 1;
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLTStruct(Graphics2D g2d) {
        float[] column = { 424, 416 };
        float[] pipShift = { 6, 6 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 20; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRTStruct(Graphics2D g2d) {
        float[] column = { 484, 416 };
        float[] pipShift = { 6, 6 };

        int totalArmor = mech.getInternal(Mech.LOC_RT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 20; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

            if (pos == 20) {
                column[0] += pipShift[0] * 3;
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printCTStruct(Graphics2D g2d) {
        float[] column = { 454, 433 };
        float[] pipShift = { 6, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = 28;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 4 == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        pips = 3;

        column[0] += pipShift[0] / 2;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[] { column[0], column[1] });
            column[0] += pipShift[0];
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printHeadStruct(Graphics2D g2d) {
        ImageHelper.drawISPip(g2d, 462, 414);
        ImageHelper.drawISPip(g2d, 458, 421);
        ImageHelper.drawISPip(g2d, 467, 421);
    }

    private void setCritConnection(Mounted m, int startx, int starty, int endx, int endy, Graphics2D g2d) {
        if (m == null) {
            printCritConnection(g2d, startMountx, startMounty, endMountx, endMounty);
            startingMount = null;
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if ((startingMount == null) && (UnitUtil.getCritsUsed(mech, m.getType()) > 1)) {
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
                if (mech.isPrimitive()) {
                    engineName = "Primitive Fusion Engine";
                }

                switch (mech.getEngine().getEngineType()) {
                case Engine.COMBUSTION_ENGINE:
                    engineName = "I.C.E.";
                    if (mech.isPrimitive()) {
                        engineName = "Primitive I.C.E.";
                    }
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

                if (cs.isArmored()) {
                        engineName = "O " + engineName;
                }

                g2d.drawString(engineName, lineStart, linePoint);
                } else {
                    String critName = mech.getSystemName(cs.getIndex());

                    if (critName.indexOf("Standard") > -1) {
                        critName = critName.replace("Standard ", "");
                    }

                    if (cs.isArmored()) {
                        critName = "O " + critName;
                    }
                    g2d.drawString(critName, lineStart, linePoint);
                }
                setCritConnection(null, lineStart, linePoint, lineStart, linePoint, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = cs.getMount();

                setCritConnection(m, lineStart, linePoint, lineStart, linePoint, g2d);

                StringBuffer critName = new StringBuffer(UnitUtil.getCritName(mech, m.getType()));

                if (m.isArmored()) {
                    critName.insert(0, "O ");
                }

                if (UnitUtil.isTSM(m.getType())) {
                    critName.setLength(0);
                    critName.append("Triple-Strength Myomer");
                }

                if (m.isRearMounted()) {
                    critName.append("(R)");
                } else if (m.getType() instanceof MiscType && (m.getType().hasFlag(MiscType.F_MODULAR_ARMOR))) {
                    critName.append("[OOOOOOOOOO]");
                } else if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                    AmmoType ammo = (AmmoType) m.getType();

                    critName = new StringBuffer("Ammo (");
                    // Remove Text (Clan) from the name
                    critName.append(ammo.getShortName().replace('(', '.').replace(')', '.').replaceAll(".Clan.", "").trim());
                    // Remove any additional Ammo text.
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

                g2d.setFont(UnitUtil.getNewFont(g2d, critName.toString(), m.getType().isHittable(), 86, 7.0f));

                if ((m.getType() instanceof MiscType) && m.getType().hasFlag(MiscType.F_C3I)) {
                    ImageHelper.printC3iName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((m.getType() instanceof MiscType) && m.getType().hasFlag(MiscType.F_C3S)) {
                    ImageHelper.printC3sName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((m.getType() instanceof WeaponType) && m.getType().hasFlag(WeaponType.F_C3M)) {
                    ImageHelper.printC3mName(g2d, lineStart, linePoint, font, m.isArmored());
                } else {
                    g2d.drawString(critName.toString(), lineStart, linePoint);
                }
            }
            linePoint += lineFeed;

            if ((slot > 0) && (slot % 2 == 0)) {
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

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printISPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawISPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

}