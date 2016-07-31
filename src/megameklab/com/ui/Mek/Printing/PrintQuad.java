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

package megameklab.com.ui.Mek.Printing;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.AmmoType;
import megamek.common.Crew;
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

import com.kitfox.svg.SVGException;

public class PrintQuad implements Printable {

    protected Image awtImage = null;
    protected Image awtHud = null;
    private Mech mech = null;
    private ArrayList<Mech> mechList;

    private Mounted startingMount = null;
    private float startMountx = 0;
    private float startMounty = 0;
    private float endMountx = 0;
    private float endMounty = 0;
    PrinterJob masterPrintJob;
    private int topMargin = 6;
    private int leftMargin = 11;

    public PrintQuad(ArrayList<Mech> list, PrinterJob masterPrintJob) {
        awtImage = ImageHelper.getRecordSheet(list.get(0), false);
        mechList = list;
        this.masterPrintJob = masterPrintJob;
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
        //g2d.drawImage(image, 18, 18, 558, 738, null);
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/QuadMechTemplate.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
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
        String mekName = mech.getChassis() + " " + mech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, mekName, true, 180, 10.0f));
        g2d.drawString(mekName, 49 + leftMargin, 119 + topMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);
        
        if ((mech.getCrew() != null) && !mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = mech.getCrew();
            g2d.drawString(pilot.getName(), 271 + leftMargin, topMargin + 119);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295 + leftMargin, topMargin + 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365 + leftMargin, topMargin + 132);
        }

        if (mech.hasTSM()) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79 + leftMargin, 144 + topMargin);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 79 + leftMargin, 155 + topMargin);
        } else if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, 144 + topMargin);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79 + leftMargin, topMargin + 155);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79 + leftMargin, topMargin + 155);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 79 + leftMargin, topMargin + 155);
        }

        if (mech.hasUMU()) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BipedMechUnderwater.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
            //g2d.drawImage(ImageHelper.getUMImage(), 31 + leftMargin, 156 + topMargin, 40, 15, null);
            g2d.drawString(Integer.toString(mech.getAllUMUCount()), 79 + leftMargin, topMargin + 166);
        } else if (mech.getJumpMP() > 0) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BipedMechJumping.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
            g2d.drawString(Integer.toString(mech.getJumpMP()), 79 + leftMargin, topMargin + 166);
        }

        int tonnage = (int) Math.ceil(mech.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177 + leftMargin, topMargin + 134);

        if (mech.isIndustrial()) {
            g2d.drawString("(Industrial)", 155 + leftMargin, topMargin + 97);
        }

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

        if (mech.isPrimitive()) {
            ImageHelper.printCenterString(g2d, "(Primitive)", font, startLine + leftMargin, topMargin + nextDataLine);
            nextDataLine += lineFeed;
        } else {
            switch (mech.getTechLevel()) {

                case TechConstants.T_INTRO_BOXSET:
                    ImageHelper.printCenterString(g2d, "(Intro)", font, startLine + leftMargin, topMargin + nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_TW_NON_BOX:
                case TechConstants.T_IS_TW_ALL:
                case TechConstants.T_CLAN_TW:
                    break;
                case TechConstants.T_IS_ADVANCED:
                case TechConstants.T_CLAN_ADVANCED:
                    ImageHelper.printCenterString(g2d, "(Advanced)", font, startLine + leftMargin, topMargin + nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_EXPERIMENTAL:
                case TechConstants.T_CLAN_EXPERIMENTAL:
                    ImageHelper.printCenterString(g2d, "(Experimental)", font, startLine + leftMargin, topMargin + nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_UNOFFICIAL:
                case TechConstants.T_CLAN_UNOFFICIAL:
                    ImageHelper.printCenterString(g2d, "(Unofficial)", font, startLine + leftMargin, topMargin + nextDataLine);
                    nextDataLine += lineFeed;
                    break;
            }
        }

        if ((mech.getSource() != null) && (mech.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 7.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138+ leftMargin, topMargin + nextDataLine);

            font = UnitUtil.getNewFont(g2d, mech.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(mech.getSource(), 177+ leftMargin, topMargin + nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138+ leftMargin, topMargin + nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", mech.getYear()), 177 + leftMargin, topMargin + nextDataLine);

        }

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = mech.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35 + leftMargin, topMargin + 355);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", mech.calculateBattleValue(true, true)), 50 + leftMargin, topMargin + 355);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(mech.getCost(true)) + " C-bills",
        // 52 + leftMargin, topMargin + 350);

        String isName = "";
        if (mech.hasCompositeStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_COMPOSITE);
        } else if (mech.hasReinforcedStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_REINFORCED);
        }

        if (isName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, isName, true, 44, 10.0f));
            g2d.drawString(isName, 444 + leftMargin, topMargin + 568);
        }

        String armorName = "";

        if (mech.hasHardenedArmor() && !mech.hasPatchworkArmor()) {
            armorName = EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_HARDENED);
        }
        if ((mech.getArmorType(0) == EquipmentType.T_ARMOR_FERRO_LAMELLOR) && !mech.hasPatchworkArmor()) {
            armorName = EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_FERRO_LAMELLOR);
        }

        if (armorName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString(armorName, 461 + leftMargin, topMargin + 279);
        }

        if (UnitUtil.hasBAR(mech) && !mech.hasPatchworkArmor()) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString("BAR " + UnitUtil.getLowestBARRating(mech), 461 + leftMargin, topMargin + 279);
        }

        g2d.setFont(UnitUtil.getNewFont(g2d, techBase, false, 51, 10.0f));
        g2d.drawString(techBase, 177 + leftMargin, topMargin + 145);

        font = UnitUtil.deriveFont(true, 6);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 36 + leftMargin, topMargin + 762.2f);


        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/QuadMech3rdGyro.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
            //g2d.drawImage(ImageHelper.getGyroPipImage(), 235 + leftMargin, topMargin + 588, 9, 8, null);
        }

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity(true,false)) + ")", 502 + leftMargin, topMargin + 595);
        if (mech.hasLaserHeatSinks()) {
            g2d.drawString("Laser", 502 + leftMargin, topMargin + 603);
        } else if (mech.hasDoubleHeatSinks()) {
            g2d.drawString("Double", 502 + leftMargin, topMargin + 603);
        } else {
            g2d.drawString("Single", 502 + leftMargin, topMargin + 603);
        }

        Dimension column = new Dimension(504 + leftMargin, topMargin + 612);
        Dimension pipShift = new Dimension(9, 9);

        int pipsPerColumn = (int) Math.max(10, Math.ceil(mech.heatSinks() / 4.0));

        for (int pos = 1; pos <= mech.heatSinks(); pos++) {
            ImageHelper.drawHeatSinkPip(g2d, column.width, column.height);
            column.height += pipShift.height;

            if ((pos % pipsPerColumn) == 0) {
                column.height -= pipShift.height * pipsPerColumn;
                column.width += pipShift.width;
            }

        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = UnitUtil.deriveFont(7.0f);
        g2d.setFont(font);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_HEAD)), 490 + leftMargin, topMargin + 42);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LT)), 393 + leftMargin, topMargin + 138);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RT)), 553 + leftMargin, topMargin + 138);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_CT)), 475 + leftMargin, topMargin + 209);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LARM)), 401 + leftMargin, topMargin + 309);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RARM)), 549 + leftMargin, topMargin + 310);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LLEG)), 448 + leftMargin, topMargin + 297);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RLEG)), 501 + leftMargin, topMargin + 300);
        // Rear
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_LT, true)), 406 + leftMargin, topMargin + 357);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_CT, true)), 512 + leftMargin, topMargin + 372);
        g2d.drawString(String.format("(%1$s)", mech.getArmor(Mech.LOC_RT, true)), 544 + leftMargin, topMargin + 357);
        // patchwork armor info
        if (mech.hasPatchworkArmor()) {
            font = UnitUtil.deriveFont(5.5f);
            g2d.setFont(font);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_HEAD), 494 + leftMargin, topMargin + 45);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 393 + leftMargin, topMargin + 146);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 553 + leftMargin, topMargin + 146);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 475 + leftMargin, topMargin + 217);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LARM), 401 + leftMargin, topMargin + 317);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RARM), 549 + leftMargin, topMargin + 318);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LLEG), 461 + leftMargin, topMargin + 297);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RLEG), 514 + leftMargin, topMargin + 300);
            // Rear
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 419 + leftMargin, topMargin + 357);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 519 + leftMargin, topMargin + 366);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 555 + leftMargin, topMargin + 357);
            font = UnitUtil.deriveFont(7.0f);
            g2d.setFont(font);
        }
        // Internal
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LT)), 400 + leftMargin, topMargin + 418);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RT)), 521 + leftMargin, topMargin + 418);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LARM)), 398 + leftMargin, topMargin + 489);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RARM)), 523 + leftMargin, topMargin + 489);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_CT)), 459 + leftMargin, topMargin + 518);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_LLEG)), 395 + leftMargin, topMargin + 538);
        g2d.drawString(String.format("(%1$s)", mech.getInternal(Mech.LOC_RLEG)), 526 + leftMargin, topMargin + 538);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 56 + leftMargin;
        float linePoint = 445.5f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart, linePoint, lineFeed);
    }

    private void printRACrits(Graphics2D g2d) {

        int lineStart = 294 + leftMargin;
        float linePoint = 445.5f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RARM, lineStart, linePoint, lineFeed);
    }

    private void printCTCrits(Graphics2D g2d) {

        int lineStart = 174 + leftMargin;
        float linePoint = 477f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_CT, lineStart, linePoint, lineFeed);
    }

    private void printLTCrits(Graphics2D g2d) {

        int lineStart = 56 + leftMargin;
        float linePoint = 555f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart, linePoint, lineFeed);
    }

    private void printRTCrits(Graphics2D g2d) {

        int lineStart = 294 + leftMargin;
        float linePoint = 555f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RT, lineStart, linePoint, lineFeed);
    }

    private void printHeadCrits(Graphics2D g2d) {

        int lineStart = 174 + leftMargin;
        int linePoint = 406 + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_HEAD, lineStart, linePoint, lineFeed);
    }

    private void printLLCrits(Graphics2D g2d) {

        int lineStart = 57 + leftMargin;
        float linePoint = 696.5f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart, linePoint, lineFeed);
    }

    private void printRLCrits(Graphics2D g2d) {

        int lineStart = 294 + leftMargin;
        float linePoint = 696.5f + topMargin;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart, linePoint, lineFeed);
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printMechWeaponsNEquipment(mech, g2d, leftMargin, topMargin);
    }

    public void print(HashPrintRequestAttributeSet aset) {

        try {

            for (Mech currentMech : mechList) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                mech = currentMech;
                awtHud = ImageHelper.getFluffImage(currentMech, ImageHelper.imageMech);
                pj.setJobName(mech.getChassis() + " " + mech.getModel());

                try {
                    pj.print(aset);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.gc();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printRLArmor(Graphics2D g2d) {
        float[] column =
            { 504 + leftMargin, topMargin + 144};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);
        int pips = 4;
        int pipsPerColumn = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        pips = 30;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % pipsPerColumn) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

            if ((pos % 8) == 0) {
                column[1]++;
            }
        }

        pips = 8;

        column[0] += pipShift[0] * 2;
        pipShift[0] *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLArmor(Graphics2D g2d) {
        float[] column =
            { 448 + leftMargin, topMargin + 144};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);
        int pips = 4;
        int pipsPerColumn = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        if (totalArmor < 1) {
            return;
        }

        pips = 30;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % pipsPerColumn) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

            if ((pos % 8) == 0) {
                column[1]++;
            }
        }

        pips = 8;

        column[0] += pipShift[0] * 2;
        pipShift[0] *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLAArmor(Graphics2D g2d) {
        float[] column =
            { 422 + leftMargin, topMargin + 145};
        float[] pipShift =
            { 6, 6 };
        int pipsPerColumn = 2;

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % pipsPerColumn) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
                column[0] -= 1;
            }

            if ((pos % 4) == 0) {
                column[0] += 1;
            }

            if ((pos % 8) == 0) {
                column[1] += 1;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAArmor(Graphics2D g2d) {
        float[] column =
            { 525 + leftMargin, topMargin + 147};
        float[] pipShift =
            { 6, 6 };
        int pipsPerColumn = 2;

        int totalArmor = mech.getArmor(Mech.LOC_RARM);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % pipsPerColumn) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
                column[0] += 1;
            }

            if ((pos % 4) == 0) {
                column[0] -= 1;
            }

            if ((pos % 8) == 0) {
                column[1] += 1;
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTArmor(Graphics2D g2d) {
        float[] column =
            { 424 + leftMargin, topMargin + 69};
        float[] pipShift =
            { 5, 7 };
        int totalArmor = mech.getArmor(Mech.LOC_LT);
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % pipsPerLine) == 0) {
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
        float[] column =
            { 451 + leftMargin, topMargin + 315};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_LT, true));

        int pips = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 3;

        column[1] += pipShift[1];
        pipShift[0] *= -1;
        column[0] += pipShift[0];
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 4;

        column[1] += pipShift[1];
        // column[0] += pipShift[0];
        pipShift[0] *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 10;

        column[1] += pipShift[1];
        pipShift[0] *= -1;
        column[0] += pipShift[0];
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % 5) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 8;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 3;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRTArmor(Graphics2D g2d) {
        float[] column =
            { 507 + leftMargin, topMargin + 69};
        float[] pipShift =
            { 5, 7 };
        int totalArmor = mech.getArmor(Mech.LOC_RT);
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 42; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % pipsPerLine) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRTRArmor(Graphics2D g2d) {
        float[] column =
            { 499 + leftMargin, topMargin + 315};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_RT, true));

        int pips = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 3;

        column[1] += pipShift[1];
        // pipShift[0] *= -1;
        column[0] -= pipShift[0] * 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 4;

        column[1] += pipShift[1];
        column[0] -= pipShift[0] * 3;
        // pipShift[0] *= -1;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        pips = 10;

        column[1] += pipShift[1];
        // pipShift[0] *= -1;
        column[0] -= pipShift[0] * 4;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % 5) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 8;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }

        pips = 3;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printCTArmor(Graphics2D g2d) {
        float[] column =
            { 462 + leftMargin, topMargin + 108};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = mech.getArmor(Mech.LOC_CT);

        int pipsPerLine = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 62; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % pipsPerLine) == 0) {
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

        Dimension head = new Dimension(466 + leftMargin, topMargin + 83);
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
        float[] column =
            { 466 + leftMargin, topMargin + 312};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = Math.min(45, mech.getArmor(Mech.LOC_CT, true));

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 45; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
            if ((pos % 5) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLAStruct(Graphics2D g2d) {
        float[] column =
            { 429 + leftMargin, topMargin + 479};
        float[] pipShift =
            { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_LARM);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[0] -= 1;
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLStruct(Graphics2D g2d) {
        float[] column =
            { 445 + leftMargin, topMargin + 475};
        float[] pipShift =
            { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] += pipShift[0];
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRLStruct(Graphics2D g2d) {
        float[] column =
            { 482 + leftMargin, topMargin + 475};
        float[] pipShift =
            { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
            column[0] += pipShift[0];
            pipShift[0] *= -1;
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAStruct(Graphics2D g2d) {
        float[] column =
            { 500 + leftMargin, topMargin + 479};
        float[] pipShift =
            { 4, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = 21;

        Vector<float[]> pipPlotter = new Vector<float[]>(pips);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
            pipShift[0] *= -1;
            column[0] -= pipShift[0];

            if ((pos % 4) == 0) {
                column[0] += 1;
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLTStruct(Graphics2D g2d) {
        float[] column =
            { 426 + leftMargin, topMargin + 421};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 20; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }
        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRTStruct(Graphics2D g2d) {
        float[] column =
            { 485 + leftMargin, topMargin + 421};
        float[] pipShift =
            { 6, 6 };

        int totalArmor = mech.getInternal(Mech.LOC_RT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 20; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
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
        float[] column =
            { 456 + leftMargin, topMargin + 440};
        float[] pipShift =
            { 6, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = 28;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 4) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        pips = 3;

        column[0] += pipShift[0] / 2;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];
        }
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printHeadStruct(Graphics2D g2d) {
        ImageHelper.drawISPip(g2d, 464 + leftMargin, topMargin + 419);
        ImageHelper.drawISPip(g2d, 460 + leftMargin, topMargin + 426);
        ImageHelper.drawISPip(g2d, 469 + leftMargin, topMargin + 426);
    }

    private void setCritConnection(Mounted m, float startx, float starty, float endx, float endy, Graphics2D g2d) {
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

    private void printCritConnection(Graphics2D g2d, float startx, float starty, float endx, float endy) {
        if (starty == endy) {
            return;
        }

        g2d.setStroke(new BasicStroke());
        g2d.draw(new Line2D.Float(startx - 1, starty - 6, startx - 4, starty - 6));
        g2d.draw(new Line2D.Float(startx - 4, starty - 6, endx - 4, endy));
        g2d.draw(new Line2D.Float(endx - 1, endy, endx - 4, endy));
    }

    private void printLocationCriticals(Graphics2D g2d, int location, float lineStart, float linePoint, float lineFeed) {
        Font font;
        HashMap<TextAttribute, Object> strikeThroughAttr = new HashMap<TextAttribute, Object>();
        strikeThroughAttr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
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
                        case Engine.FUEL_CELL:
                            engineName = "Fuel Cell Engine";
                            if (mech.isPrimitive()) {
                                engineName = "Primitive Fuel Cell Engine";
                            }
                            break;
                        case Engine.FISSION:
                            engineName = "Fission Engine";
                            break;
                        default:
                            break;
                    }

                    if (cs.isArmored()) {
                        engineName = "O " + engineName;
                    }
                    if (cs.isDestroyed()) {
                        font = font.deriveFont(strikeThroughAttr);
                        g2d.setFont(font);

                    }
                    g2d.drawString(engineName, lineStart, linePoint);
                } else {
                    String critName = mech.getSystemName(cs.getIndex());

                    if (critName.indexOf("Standard") > -1) {
                        critName = critName.replace("Standard ", "");
                    }
                    if (mech.isClan() && (cs.getIndex() == Mech.SYSTEM_GYRO) && (mech.getGyroType() > 0)) {
                        critName = String.format("%1$s (IS)", critName);
                    }
                    if (cs.isArmored()) {
                        critName = "O " + critName;
                    }

                    if (((cs.getIndex() >= Mech.ACTUATOR_UPPER_ARM) && (cs.getIndex() <= Mech.ACTUATOR_HAND)) || ((cs.getIndex() >= Mech.ACTUATOR_UPPER_LEG) && (cs.getIndex() <= Mech.ACTUATOR_FOOT))) {
                        critName += " Actuator";
                    }
                    if (cs.isDestroyed()) {
                        font = font.deriveFont(strikeThroughAttr);
                        g2d.setFont(font);

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
                    critName.append(" (R)");
                } else if (m.isMechTurretMounted()) {
                    critName.append(" (T)");
                } else if ((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_MODULAR_ARMOR))) {
                    critName.append(" [OOOOOOOOOO]");
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

                    // Remove Capable with the name
                    if (critName.indexOf("-capable") > -1) {
                        int startPos = critName.indexOf("-capable");
                        critName.delete(startPos, startPos + "-capable".length());
                        critName.trimToSize();
                    }

                    // Trim trailing spaces.
                    while (critName.charAt(critName.length() - 1) == ' ') {
                        critName.setLength(critName.length() - 1);
                    }
                    critName.trimToSize();
                    critName.append(") ");
                    critName.append(m.getUsableShotsLeft());

                }

                if (cs.getMount2() != null) {
                    critName.append(" | ");
                    if (!(cs.getMount2().getType() instanceof AmmoType)) {
                        critName.append(UnitUtil.getCritName(mech, cs.getMount2().getType()));
                    } else {
                        AmmoType ammo = (AmmoType)cs.getMount2().getType();
                        critName.append(ammo.getShortName().replace('(', '.').replace(')', '.').replaceAll(".Clan.", "").trim());
                        // Remove any additional Ammo text.
                        if (critName.toString().endsWith("Ammo")) {
                            critName.setLength(critName.length() - 5);
                            critName.trimToSize();
                        }

                        // Remove Capable with the name
                        if (critName.indexOf("-capable") > -1) {
                            int startPos = critName.indexOf("-capable");
                            critName.delete(startPos, startPos + "-capable".length());
                            critName.trimToSize();
                        }

                        // Trim trailing spaces.
                        while (critName.charAt(critName.length() - 1) == ' ') {
                            critName.setLength(critName.length() - 1);
                        }
                        critName.trimToSize();
                        critName.append(") ");
                        critName.append(m.getUsableShotsLeft());
                    }
                }

                font = UnitUtil.getNewFont(g2d, critName.toString(), m.getType().isHittable(), 85, 7.0f);
                if (cs.isDestroyed()) {
                    font = font.deriveFont(strikeThroughAttr);
                }
                g2d.setFont(font);

                if ((m.getType() instanceof MiscType) && m.getType().hasFlag(MiscType.F_C3I)) {
                    ImageHelper.printC3iName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_C3EM))) {
                    ImageHelper.printC3EmName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_C3S))) {
                    ImageHelper.printC3sName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof WeaponType) && m.getType().hasFlag(WeaponType.F_C3M)) {
                    ImageHelper.printC3mName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_C3SBS))) {
                    ImageHelper.printC3sbName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof WeaponType) && m.getType().hasFlag(WeaponType.F_C3MBS)) {
                    ImageHelper.printC3mbName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof WeaponType) && (((WeaponType)m.getType()).getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR)) {
                    ImageHelper.printC3RemoteSensorName(g2d, lineStart, linePoint, font, m.isArmored(), mech.isMixedTech() && TechConstants.isClan(mech.getTechLevel()));
                } else if ((m.getType() instanceof AmmoType) && (((AmmoType)m.getType()).getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR)) {
                    ImageHelper.printC3RemoteSensorAmmoName(g2d, lineStart, linePoint, font);
                } else {
                    g2d.drawString(critName.toString(), lineStart, linePoint);
                }
            }
            linePoint += lineFeed;

            if ((slot > 0) && ((slot % 2) == 0)) {
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
        g2d.drawImage(img, 237 + leftMargin, 172 + topMargin, width, height, null);

        // g2d.drawRect(235, 172, 165, 200);
    }

    private void printLACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LARM)) {
            return;
        }

        int lineStart = 116 + leftMargin;
        int linePoint = 429 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LARM)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printRACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RARM)) {
            return;
        }

        int lineStart = 349 + leftMargin;
        int linePoint = 438 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RARM)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printLLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LLEG)) {
            return;
        }

        int lineStart = 112 + leftMargin;
        int linePoint = 680 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LLEG)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printLTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LT)) {
            return;
        }

        int lineStart = 95 + leftMargin;
        int linePoint = 538 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printHeadCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_HEAD)) {
            return;
        }

        int lineStart = 186 + leftMargin;
        int linePoint = 388 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_HEAD)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printRTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RT)) {
            return;
        }

        int lineStart = 340 + leftMargin;
        int linePoint = 538 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printRLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RLEG)) {
            return;
        }

        int lineStart = 349 + leftMargin;
        int linePoint = 690 + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RLEG)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printCTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_CT)) {
            return;
        }

        int lineStart = 228 + leftMargin;
        float linePoint = 460f + topMargin;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_CT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        } else {

        }
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            if (totalArmor == 0) {
                return;
            }
            int currentPip = (int) pos;
            ImageHelper.drawArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            totalArmor--;
        }
    }

    private void printISPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            if (totalArmor == 0) {
                return;
            }
            int currentPip = (int) pos;
            ImageHelper.drawISPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            totalArmor--;
        }
    }

}