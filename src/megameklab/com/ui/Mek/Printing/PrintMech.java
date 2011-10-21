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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.geom.Ellipse2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Pilot;
import megamek.common.TechConstants;
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
    private PrinterJob masterPrintJob;
    private int topMargin = 3;
    private int leftMargin = 11;

    public PrintMech(ArrayList<Mech> list, PrinterJob masterPrintJob) {
        awtImage = ImageHelper.getRecordSheet(list.get(0), false);
        mechList = list;
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
        // g2d.drawImage(image, 18, 18, 558, 738, Color.BLACK, null);
        BipedMechTemplate.paint(g2d);
        printMekImage(g2d, hud);
        if (mech.hasShield()) {

            if ((UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) && (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_LARM) > 0)) {
                g2d.drawImage(ImageHelper.getShieldImage(), 382 + leftMargin, topMargin + 18, 193, 351, null, null);
            } else if (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) {
                g2d.drawImage(ImageHelper.getRightShieldImage(), 382 + leftMargin, topMargin + 18, 193, 351, null, null);
            } else {
                g2d.drawImage(ImageHelper.getLeftShieldImage(), 382 + leftMargin, topMargin + 18, 193, 351, null, null);
            }

            printLeftShield(g2d);
            printRightShield(g2d);
        }

        printMechData(g2d);
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
        printHeatSinks(g2d);

        // g2d.translate(pageFormat.getImageableX(),
        // pageFormat.getImageableY());
        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printMechData(Graphics2D g2d) {
        String mekName = mech.getChassis() + " " + mech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, mekName, true, 180, 10.0f));
        g2d.drawString(mekName, 49 + leftMargin, topMargin + 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((mech.getCrew() != null) && !mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = mech.getCrew();
            g2d.drawString(pilot.getName(), 270 + leftMargin, topMargin + 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295 + leftMargin, topMargin + 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365 + leftMargin, topMargin + 132);
        }

        if (mech.hasTSM() && (mech.getSuperCharger() == null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 79 + leftMargin, topMargin + 155);
        } else if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79 + leftMargin, topMargin + 155);
        } else if (mech.hasTSM() && (mech.getSuperCharger() != null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSMandSuperCharge = (walkTSM * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + runTSMandSuperCharge + "]", 79 + leftMargin, topMargin + 155);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79 + leftMargin, topMargin + 155);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 79 + leftMargin, topMargin + 155);
        }

        if (mech.hasUMU()) {
            g2d.drawImage(ImageHelper.getUMImage(), 31 + leftMargin, topMargin + 156, 40, 15, null);
            g2d.drawString(Integer.toString(mech.getActiveUMUCount()), 79 + leftMargin, topMargin + 166);
        } else {
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

        // full head eject
        if (mech.hasFullHeadEject()) {
            String ejectString = "Note: If playing under Advanced Rules, treat head";
            g2d.setFont(UnitUtil.deriveFont(false, 8));
            g2d.drawString(ejectString, 25 + leftMargin, topMargin + 325);
            ejectString = "as having a Full-Head Ejection System.";
            g2d.drawString(ejectString, 45 + leftMargin, topMargin + 335);
        }

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(mech.calculateBattleValue(true, true)), 150 + leftMargin, topMargin + 350);

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(mech.getCost(true)) + " C-bills",
        // 52, 350);

        String isName = "";

        if (mech.hasCompositeStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_COMPOSITE);
        } else if (mech.hasReinforcedStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_REINFORCED);
        }

        if (isName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, isName, true, 44, 10.0f));
            g2d.drawString(isName, 442 + leftMargin, topMargin + 553);
        }

        String armorName = "";

        if (mech.hasHardenedArmor() && !mech.hasPatchworkArmor()) {
            armorName = EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_HARDENED);
        }

        if (armorName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString(armorName, 461 + leftMargin, topMargin + 249);
        }

        if (UnitUtil.hasBAR(mech) && !mech.hasPatchworkArmor()) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString("BAR " + UnitUtil.getLowestBARRating(mech), 461 + leftMargin, topMargin + 249);
        }

        if ((mech.getSource() != null) && (mech.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138 + leftMargin, topMargin + nextDataLine);

            font = UnitUtil.getNewFont(g2d, mech.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(mech.getSource(), 177 + leftMargin, topMargin + nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138 + leftMargin, topMargin + nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", mech.getYear()), 177 + leftMargin, topMargin + nextDataLine);

        }

        g2d.setFont(UnitUtil.getNewFont(g2d, techBase, false, 51, 10.0f));
        g2d.drawString(techBase, 177 + leftMargin, topMargin + 145);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2011", 55f, topMargin + 745f);

        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            g2d.drawImage(ImageHelper.getGyroPipImage(), 235 + leftMargin, topMargin + 588, 9, 8, null);
        }

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (mech.hasLaserHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502 + leftMargin, topMargin + 595);
            g2d.drawString("Laser", 502 + leftMargin, topMargin + 603);
        } else if (mech.hasDoubleHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502 + leftMargin, topMargin + 595);
            g2d.drawString("Double", 502 + leftMargin, topMargin + 603);
        } else {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502 + leftMargin, topMargin + 595);
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
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_HEAD)) + ")", 485 + leftMargin, topMargin + 45);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT)) + ")", 434 + leftMargin, topMargin + 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT)) + ")", 506 + leftMargin, topMargin + 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT)) + ")", 472 + leftMargin, topMargin + 222);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LARM)) + ")", 394 + leftMargin, topMargin + 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RARM)) + ")", 546 + leftMargin, topMargin + 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LLEG)) + ")", 384 + leftMargin, topMargin + 273);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RLEG)) + ")", 554 + leftMargin, topMargin + 273);
        // Rear
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT, true)) + ")", 403 + leftMargin, topMargin + 362);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT, true)) + ")", 480 + leftMargin, topMargin + 277);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT, true)) + ")", 546 + leftMargin, topMargin + 362);
        // patchwork armor info
        if (mech.hasPatchworkArmor()) {
            font = UnitUtil.deriveFont(5.5f);
            g2d.setFont(font);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_HEAD), 494 + leftMargin, topMargin + 45);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 434 + leftMargin, topMargin + 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 506 + leftMargin, topMargin + 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 472 + leftMargin, topMargin + 230);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LARM), 394 + leftMargin, topMargin + 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RARM), 546 + leftMargin, topMargin + 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LLEG), 384 + leftMargin, topMargin + 280);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RLEG), 554 + leftMargin, topMargin + 280);
            // Rear
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 416 + leftMargin, topMargin + 362);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 493 + leftMargin, topMargin + 277);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 559 + leftMargin, topMargin + 362);
            font = UnitUtil.deriveFont(7.0f);
            g2d.setFont(font);
        }
        // Internal
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LT)) + ")", 432 + leftMargin, topMargin + 403);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RT)) + ")", 525 + leftMargin, topMargin + 403);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LARM)) + ")", 390 + leftMargin, topMargin + 480);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RARM)) + ")", 530 + leftMargin, topMargin + 480);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_CT)) + ")", 459 + leftMargin, topMargin + 509);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LLEG)) + ")", 403 + leftMargin, topMargin + 538);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RLEG)) + ")", 518 + leftMargin, topMargin + 539);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 408;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRACrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 408;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RARM, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printCTCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 469;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_CT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printLTCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRTCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printHeadCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 399;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_HEAD, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printLLCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 682;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRLCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 682;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printMechWeaponsNEquipment(mech, g2d, leftMargin, topMargin);
    }

    public void print() {

        try {
            for (Mech currentMech : mechList) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());
                // Paper paper = new Paper();
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

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
        float[] rightColumnStart =
            { 494 + leftMargin, topMargin + 179 };
        float[] rightColumnEnd =
            { 525 + leftMargin, topMargin + 297 };
        float[] midColumnStart =
            { 500 + leftMargin, topMargin + 179 };
        float[] midColumnEnd =
            { 532 + leftMargin, topMargin + 297 };
        float[] leftColumnStart =
            { 506 + leftMargin, topMargin + 179 };
        float[] leftColumnEnd =
            { 538 + leftMargin, topMargin + 297 };

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);

        if (totalArmor < 1) {
            return;
        }
        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor < 10) {
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, totalArmor));
        } else if (totalArmor < 29) {
            int pipsLeft = totalArmor / 2;
            int pipsRight = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest > 0) {
                pipsLeft++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        } else {
            int pipsLeft = totalArmor / 3;
            int pipsCenter = totalArmor / 3;
            int pipsRight = totalArmor / 3;
            int rest = totalArmor % 3;
            if (rest == 2) {
                pipsLeft++;
                pipsRight++;
            } else if (rest == 1) {
                pipsCenter++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, pipsCenter));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLArmor(Graphics2D g2d) {
        float[] leftColumnStart =
            { 443 + leftMargin, topMargin + 179 };
        float[] leftColumnEnd =
            { 412 + leftMargin, topMargin + 297 };
        float[] midColumnStart =
            { 449 + leftMargin, topMargin + 179 };
        float[] midColumnEnd =
            { 418 + leftMargin, topMargin + 297 };
        float[] rightColumnStart =
            { 455 + leftMargin, topMargin + 179 };
        float[] rightColumnEnd =
            { 424 + leftMargin, topMargin + 297 };

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);

        if (totalArmor < 1) {
            return;
        }
        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor < 10) {
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, totalArmor));
        } else if (totalArmor < 29) {
            int pipsLeft = totalArmor / 2;
            int pipsRight = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest > 0) {
                pipsLeft++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        } else {
            int pipsLeft = totalArmor / 3;
            int pipsCenter = totalArmor / 3;
            int pipsRight = totalArmor / 3;
            int rest = totalArmor % 3;
            if (rest == 2) {
                pipsLeft++;
                pipsRight++;
            } else if (rest == 1) {
                pipsCenter++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, pipsCenter));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLAArmor(Graphics2D g2d) {
        ;
        float[] leftColumnStart =
            { 404 + leftMargin, topMargin + 80 };
        float[] leftColumnEnd =
            { 395 + leftMargin, topMargin + 165 };
        float[] midColumnStart =
            { 410 + leftMargin, topMargin + 80 };
        float[] midColumnEnd =
            { 401 + leftMargin, topMargin + 165 };
        float[] rightColumnStart =
            { 416 + leftMargin, topMargin + 80 };
        float[] rightColumnEnd =
            { 407 + leftMargin, topMargin + 165 };

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        if (totalArmor < 1) {
            return;
        }
        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor < 10) {
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, totalArmor));
        } else if (totalArmor < 25) {
            int pipsLeft = totalArmor / 2;
            int pipsRight = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest > 0) {
                pipsLeft++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        } else {
            int pipsLeft = totalArmor / 3;
            int pipsCenter = totalArmor / 3;
            int pipsRight = totalArmor / 3;
            int rest = totalArmor % 3;
            if (rest == 2) {
                pipsLeft++;
                pipsRight++;
            } else if (rest == 1) {
                pipsCenter++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, pipsCenter));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAArmor(Graphics2D g2d) {
        float[] leftColumnStart =
            { 533 + leftMargin, topMargin + 80 };
        float[] leftColumnEnd =
            { 542 + leftMargin, topMargin + 165 };
        float[] midColumnStart =
            { 539 + leftMargin, topMargin + 80 };
        float[] midColumnEnd =
            { 548 + leftMargin, topMargin + 165 };
        float[] rightColumnStart =
            { 546 + leftMargin, topMargin + 80 };
        float[] rightColumnEnd =
            { 555 + leftMargin, topMargin + 165 };

        int totalArmor = mech.getArmor(Mech.LOC_RARM);

        if (totalArmor < 1) {
            return;
        }
        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor < 10) {
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, totalArmor));
        } else if (totalArmor < 25) {
            int pipsLeft = totalArmor / 2;
            int pipsRight = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest > 0) {
                pipsLeft++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        } else {
            int pipsLeft = totalArmor / 3;
            int pipsCenter = totalArmor / 3;
            int pipsRight = totalArmor / 3;
            int rest = totalArmor % 3;
            if (rest == 2) {
                pipsLeft++;
                pipsRight++;
            } else if (rest == 1) {
                pipsCenter++;
            }
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(leftColumnStart, leftColumnEnd, pipsLeft));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(midColumnStart, midColumnEnd, pipsCenter));
            pipPlotter.addAll(ImageHelper.getPointsAlongLine(rightColumnStart, rightColumnEnd, pipsRight));
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTArmor(Graphics2D g2d) {
        float[] col1Start =
            { 429 + leftMargin, topMargin + 88 };
        float[] col1End =
            { 429 + leftMargin, topMargin + 118 };
        float[] col2Start =
            { 435 + leftMargin, topMargin + 88 };
        float[] col2End =
            { 435 + leftMargin, topMargin + 118 };
        float[] col3Start =
            { 441 + leftMargin, topMargin + 88 };
        float[] col3End =
            { 441 + leftMargin, topMargin + 118 };
        float[] col4Start =
            { 447 + leftMargin, topMargin + 88 };
        float[] col4End =
            { 447 + leftMargin, topMargin + 118 };
        float[] col5Start =
            { 453 + leftMargin, topMargin + 88 };
        float[] col5End =
            { 453 + leftMargin, topMargin + 118 };
        float[] col6Start =
            { 447 + leftMargin, topMargin + 124 };
        float[] col6End =
            { 450 + leftMargin, topMargin + 154 };
        float[] col7Start =
            { 453 + leftMargin, topMargin + 124 };
        float[] col7End =
            { 456 + leftMargin, topMargin + 154 };
        float[] col8Start =
            { 436 + leftMargin, topMargin + 160 };
        float[] col8End =
            { 456 + leftMargin, topMargin + 160 };
        float[] col9Start =
            { 436 + leftMargin, topMargin + 166 };
        float[] col9End =
            { 456 + leftMargin, topMargin + 166 };

        int totalArmor = mech.getArmor(Mech.LOC_LT);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = 0;
        int pips2 = 0;
        int pips3 = 0;
        int pips4 = 0;
        int pips5 = 0;
        int pips6 = 0;
        int pips7 = 0;
        int pips8 = 0;
        int pips9 = 0;
        int rest = 0;

        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor <= 10) {
            pips2 = totalArmor / 2;
            pips4 = totalArmor / 2;
            rest = totalArmor % 2;
            if (rest == 1) {
                pips2++;
            }
        } else if (totalArmor <= 20) {
            pips2 = totalArmor / 4;
            pips4 = totalArmor / 4;
            pips8 = totalArmor / 4;
            pips9 = totalArmor / 4;
            rest = totalArmor % 4;
            switch (rest) {
                case 3:
                    pips9++;
                case 2:
                    pips4++;
                case 1:
                    pips2++;
                default:
                    break;
            }
        } else {
            pips1 = totalArmor / 9;
            pips2 = totalArmor / 9;
            pips3 = totalArmor / 9;
            pips4 = totalArmor / 9;
            pips5 = totalArmor / 9;
            pips6 = totalArmor / 9;
            pips7 = totalArmor / 9;
            pips8 = totalArmor / 9;
            pips9 = totalArmor / 9;
            rest = totalArmor % 9;
            switch (rest) {
                case 8:
                    pips1++;
                case 7:
                    pips2++;
                case 6:
                    pips3++;
                case 5:
                    pips4++;
                case 4:
                    pips5++;
                case 3:
                    pips6++;
                case 2:
                    pips7++;
                case 1:
                    pips8++;
                default:
                    break;
            }
        }
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col6Start, col6End, pips6));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col7Start, col7End, pips7));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col8Start, col8End, pips8));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col9Start, col9End, pips9));
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTRArmor(Graphics2D g2d) {
        float[] col1Start =
            { 430 + leftMargin, topMargin + 311.5f };
        float[] col1End =
            { 431 + leftMargin, topMargin + 345.5f };
        float[] col2Start =
            { 436 + leftMargin, topMargin + 311.5f };
        float[] col2End =
            { 436 + leftMargin, topMargin + 345.5f };
        float[] col3Start =
            { 441 + leftMargin, topMargin + 311.5f };
        float[] col3End =
            { 441 + leftMargin, topMargin + 345.5f };
        float[] col4Start =
            { 446 + leftMargin, topMargin + 311.5f };
        float[] col4End =
            { 446 + leftMargin, topMargin + 345.5f };
        float[] col5Start =
            { 451 + leftMargin, topMargin + 311.5f };
        float[] col5End =
            { 451 + leftMargin, topMargin + 345.5f };
        float[] col6Start =
            { 456 + leftMargin, topMargin + 311.5f };
        float[] col6End =
            { 456 + leftMargin, topMargin + 332.5f };

        int totalArmor = mech.getOArmor(Mech.LOC_LT, true);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = 0;
        int pips2 = 0;
        int pips3 = 0;
        int pips4 = 0;
        int pips5 = 0;
        int pips6 = 0;
        int rest = 0;
        if (totalArmor < 20) {
            pips6 = totalArmor > 3 ? 2 : 0;
            int restArmor = Math.max(0, totalArmor - pips6);
            pips2 = restArmor / 3;
            pips3 = restArmor / 3;
            pips4 = restArmor / 3;
            rest = restArmor % 3;
            if (rest == 2) {
                pips2++;
                pips3++;
            } else if (rest == 1) {
                pips2++;
            }
        } else {
            pips6 = totalArmor > 3 ? 2 : 0;
            int restArmor = Math.max(0, totalArmor - pips6);
            pips1 = restArmor / 5;
            pips2 = restArmor / 5;
            pips3 = restArmor / 5;
            pips4 = restArmor / 5;
            pips5 = restArmor / 5;
            rest = restArmor % 5;
            switch (rest) {
                case 1:
                    pips1++;
                    break;
                case 2:
                    pips1++;
                    pips2++;
                    break;
                case 3:
                    pips1++;
                    pips2++;
                    pips3++;
                    break;
                case 4:
                    pips1++;
                    pips2++;
                    pips3++;
                    pips4++;
                    break;
                default:
                    break;
            }
        }

        Vector<float[]> pipPlotter = new Vector<float[]>();
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col6Start, col6End, pips6));
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRTArmor(Graphics2D g2d) {
        float[] col1Start =
            { 521 + leftMargin, topMargin + 88 };
        float[] col1End =
            { 521 + leftMargin, topMargin + 118 };
        float[] col2Start =
            { 515 + leftMargin, topMargin + 88 };
        float[] col2End =
            { 515 + leftMargin, topMargin + 118 };
        float[] col3Start =
            { 509 + leftMargin, topMargin + 88 };
        float[] col3End =
            { 509 + leftMargin, topMargin + 118 };
        float[] col4Start =
            { 503 + leftMargin, topMargin + 88 };
        float[] col4End =
            { 503 + leftMargin, topMargin + 118 };
        float[] col5Start =
            { 497 + leftMargin, topMargin + 88 };
        float[] col5End =
            { 497 + leftMargin, topMargin + 118 };
        float[] col6Start =
            { 503 + leftMargin, topMargin + 124 };
        float[] col6End =
            { 500 + leftMargin, topMargin + 154 };
        float[] col7Start =
            { 497 + leftMargin, topMargin + 124 };
        float[] col7End =
            { 494 + leftMargin, topMargin + 154 };
        float[] col8Start =
            { 514 + leftMargin, topMargin + 160 };
        float[] col8End =
            { 494 + leftMargin, topMargin + 160 };
        float[] col9Start =
            { 514 + leftMargin, topMargin + 166 };
        float[] col9End =
            { 494 + leftMargin, topMargin + 166 };

        int totalArmor = mech.getArmor(Mech.LOC_RT);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = 0;
        int pips2 = 0;
        int pips3 = 0;
        int pips4 = 0;
        int pips5 = 0;
        int pips6 = 0;
        int pips7 = 0;
        int pips8 = 0;
        int pips9 = 0;
        int rest = 0;

        Vector<float[]> pipPlotter = new Vector<float[]>();
        if (totalArmor <= 10) {
            pips2 = totalArmor / 2;
            pips4 = totalArmor / 2;
            rest = totalArmor % 2;
            if (rest == 1) {
                pips2++;
            }
        } else if (totalArmor <= 20) {
            pips2 = totalArmor / 4;
            pips4 = totalArmor / 4;
            pips8 = totalArmor / 4;
            pips9 = totalArmor / 4;
            rest = totalArmor % 4;
            switch (rest) {
                case 3:
                    pips9++;
                case 2:
                    pips4++;
                case 1:
                    pips2++;
                default:
                    break;
            }
        } else {
            pips1 = totalArmor / 9;
            pips2 = totalArmor / 9;
            pips3 = totalArmor / 9;
            pips4 = totalArmor / 9;
            pips5 = totalArmor / 9;
            pips6 = totalArmor / 9;
            pips7 = totalArmor / 9;
            pips8 = totalArmor / 9;
            pips9 = totalArmor / 9;
            rest = totalArmor % 9;
            switch (rest) {
                case 8:
                    pips1++;
                case 7:
                    pips2++;
                case 6:
                    pips3++;
                case 5:
                    pips4++;
                case 4:
                    pips5++;
                case 3:
                    pips6++;
                case 2:
                    pips7++;
                case 1:
                    pips8++;
                default:
                    break;
            }
        }
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col6Start, col6End, pips6));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col7Start, col7End, pips7));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col8Start, col8End, pips8));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col9Start, col9End, pips9));
        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRTRArmor(Graphics2D g2d) {
        float[] col1Start =
            { 519.5f + leftMargin, topMargin + 311.5f };
        float[] col1End =
            { 519.5f + leftMargin, topMargin + 345.5f };
        float[] col2Start =
            { 514.5f + leftMargin, topMargin + 311.5f };
        float[] col2End =
            { 514.5f + leftMargin, topMargin + 345.5f };
        float[] col3Start =
            { 509.5f + leftMargin, topMargin + 311.5f };
        float[] col3End =
            { 509.5f + leftMargin, topMargin + 345.5f };
        float[] col4Start =
            { 504.5f + leftMargin, topMargin + 311.5f };
        float[] col4End =
            { 504.5f + leftMargin, topMargin + 345.5f };
        float[] col5Start =
            { 499.5f + leftMargin, topMargin + 311.5f };
        float[] col5End =
            { 499.5f + leftMargin, topMargin + 345.5f };
        float[] col6Start =
            { 494.5f + leftMargin, topMargin + 311.5f };
        float[] col6End =
            { 494.5f + leftMargin, topMargin + 332.5f };

        int totalArmor = mech.getOArmor(Mech.LOC_RT, true);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = 0;
        int pips2 = 0;
        int pips3 = 0;
        int pips4 = 0;
        int pips5 = 0;
        int pips6 = 0;
        int rest = 0;
        if (totalArmor < 20) {
            pips6 = totalArmor > 3 ? 2 : 0;
            int restArmor = Math.max(0, totalArmor - pips6);
            pips2 = restArmor / 3;
            pips3 = restArmor / 3;
            pips4 = restArmor / 3;
            rest = restArmor % 3;
            if (rest == 2) {
                pips2++;
                pips3++;
            } else if (rest == 1) {
                pips2++;
            }
        } else {
            pips6 = totalArmor > 3 ? 2 : 0;
            int restArmor = Math.max(0, totalArmor - pips6);
            pips1 = restArmor / 5;
            pips2 = restArmor / 5;
            pips3 = restArmor / 5;
            pips4 = restArmor / 5;
            pips5 = restArmor / 5;
            rest = restArmor % 5;
            switch (rest) {
                case 1:
                    pips1++;
                    break;
                case 2:
                    pips1++;
                    pips2++;
                    break;
                case 3:
                    pips1++;
                    pips2++;
                    pips3++;
                    break;
                case 4:
                    pips1++;
                    pips2++;
                    pips3++;
                    pips4++;
                    break;
                default:
                    break;
            }
        }

        Vector<float[]> pipPlotter = new Vector<float[]>();
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col6Start, col6End, pips6));
        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printCTArmor(Graphics2D g2d) {
        float[] col1Start =
            { 465 + leftMargin, topMargin + 105 };
        float[] col1End =
            { 465 + leftMargin, topMargin + 176 };
        float[] col2Start =
            { 470 + leftMargin, topMargin + 105 };
        float[] col2End =
            { 470 + leftMargin, topMargin + 176 };
        float[] col3Start =
            { 475 + leftMargin, topMargin + 105 };
        float[] col3End =
            { 475 + leftMargin, topMargin + 176 };
        float[] col4Start =
            { 480 + leftMargin, topMargin + 105 };
        float[] col4End =
            { 480 + leftMargin, topMargin + 176 };
        float[] col5Start =
            { 485 + leftMargin, topMargin + 105 };
        float[] col5End =
            { 485 + leftMargin, topMargin + 176 };

        int totalArmor = mech.getArmor(Mech.LOC_CT);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = totalArmor / 5;
        int pips2 = totalArmor / 5;
        int pips3 = totalArmor / 5;
        int pips4 = totalArmor / 5;
        int pips5 = totalArmor / 5;
        int rest = totalArmor % 5;
        switch (rest) {
            case 1:
                pips3++;
                if (totalArmor > 40) {
                    col3End[1] += 71 / (float) ((totalArmor / 5) - 1);
                }
                break;
            case 2:
                pips1++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col5End[1] += 71 / (float) ((totalArmor / 5) - 1);
                }
                break;
            case 3:
                pips1++;
                pips3++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col3End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col5End[1] += 71 / (float) ((totalArmor / 5) - 1);
                }
                break;
            case 4:
                pips1++;
                pips2++;
                pips4++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col2End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col4End[1] += 71 / (float) ((totalArmor / 5) - 1);
                    col5End[1] += 71 / (float) ((totalArmor / 5) - 1);
                }
                break;
            default:
                break;
        }
        Vector<float[]> pipPlotter = new Vector<float[]>();
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printHeadArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);

        Dimension fillCircle = new Dimension(4, 4);

        int totalArmor = mech.getArmor(Mech.LOC_HEAD);

        if (totalArmor >= 9) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(475.75 + leftMargin, topMargin + 68, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475 + leftMargin, topMargin + 72.5f);
        }

        if (totalArmor >= 8) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(472.75 + leftMargin, topMargin + 74, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 472 + leftMargin, topMargin + 78.5f);
        }

        if (totalArmor >= 7) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(478.75 + leftMargin, topMargin + 74, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 478 + leftMargin, topMargin + 78.5f);
        }

        if (totalArmor >= 6) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(469.75 + leftMargin, topMargin + 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469 + leftMargin, topMargin + 83.5f);
        }

        if (totalArmor >= 5) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(475.75 + leftMargin, topMargin + 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475 + leftMargin, topMargin + 83.5f);
        }

        if (totalArmor >= 4) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(481.75 + leftMargin, topMargin + 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481 + leftMargin, topMargin + 83.5f);
        }

        if (totalArmor >= 3) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(469.75 + leftMargin, topMargin + 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469 + leftMargin, topMargin + 89.5f);
        }

        if (totalArmor >= 2) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(475.75 + leftMargin, topMargin + 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475 + leftMargin, topMargin + 89.5f);
        }

        if (totalArmor >= 1) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(481.75 + leftMargin, topMargin + 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481 + leftMargin, topMargin + 89.5f);
        }
        g2d.setColor(Color.black);
    }

    private void printCTRArmor(Graphics2D g2d) {

        float[] col1Start =
            { 465 + leftMargin, topMargin + 301 };
        float[] col1End =
            { 465 + leftMargin, topMargin + 360 };
        float[] col2Start =
            { 470 + leftMargin, topMargin + 301 };
        float[] col2End =
            { 470 + leftMargin, topMargin + 360 };
        float[] col3Start =
            { 475 + leftMargin, topMargin + 301 };
        float[] col3End =
            { 475 + leftMargin, topMargin + 360 };
        float[] col4Start =
            { 480 + leftMargin, topMargin + 301 };
        float[] col4End =
            { 480 + leftMargin, topMargin + 360 };
        float[] col5Start =
            { 485 + leftMargin, topMargin + 301 };
        float[] col5End =
            { 485 + leftMargin, topMargin + 360 };

        int totalArmor = mech.getArmor(Mech.LOC_CT, true);

        if (totalArmor < 1) {
            return;
        }
        int pips1 = 0;
        int pips2 = 0;
        int pips3 = 0;
        int pips4 = 0;
        int pips5 = 0;
        int rest = 0;
        if (totalArmor <= 30) {
            pips2 = totalArmor / 3;
            pips3 = totalArmor / 3;
            pips4 = totalArmor / 3;
            rest = totalArmor % 3;
            if (rest == 2) {
                pips2++;
                pips4++;
            } else if (rest == 1) {
                pips3++;
            }
        } else {
            pips1 = totalArmor / 5;
            pips2 = totalArmor / 5;
            pips3 = totalArmor / 5;
            pips4 = totalArmor / 5;
            pips5 = totalArmor / 5;
            rest = totalArmor % 5;
            switch (rest) {
                case 1:
                    pips3++;
                    break;
                case 2:
                    pips1++;
                    pips5++;
                    break;
                case 3:
                    pips1++;
                    pips3++;
                    pips5++;
                    break;
                case 4:
                    pips1++;
                    pips2++;
                    pips4++;
                    pips5++;
                    break;
                default:
                    break;
            }
        }

        Vector<float[]> pipPlotter = new Vector<float[]>();
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, pips1));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, pips2));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, pips3));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col4Start, col4End, pips4));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col5Start, col5End, pips5));

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLAStruct(Graphics2D g2d) {
        float[] col1Start =
            { 414 + leftMargin, topMargin + 417 };
        float[] col1End =
            { 409 + leftMargin, topMargin + 473 };
        float[] col2Start =
            { 418 + leftMargin, topMargin + 413 };
        float[] col2End =
            { 413 + leftMargin, topMargin + 469 };
        Vector<float[]> pipPlotter = new Vector<float[]>();
        int totalArmor = mech.getInternal(Mech.LOC_LARM);
        int col1pips = totalArmor;
        int col2pips = 0;
        if (totalArmor > 11) {
            col1pips = totalArmor / 2;
            col2pips = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest == 1) {
                col2pips++;
                col2End[1] += 56 / (float) ((totalArmor / 2) - 1);
                col2End[0] -= 5 / (float) ((totalArmor / 2) - 1);
            }
        } else {
            col1Start[0] += 1;
            col1End[0] += 1;
            col1End[1] += 5;
        }

        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLStruct(Graphics2D g2d) {
        float[] col1Start =
            { 443 + leftMargin, topMargin + 478 };
        float[] col1End =
            { 430 + leftMargin, topMargin + 548 };
        float[] col2Start =
            { 446 + leftMargin, topMargin + 475 };
        float[] col2End =
            { 432 + leftMargin, topMargin + 545 };
        Vector<float[]> pipPlotter = new Vector<float[]>();
        int totalArmor = mech.getInternal(Mech.LOC_LLEG);
        int col1pips = totalArmor;
        int col2pips = 0;
        if (totalArmor > 14) {
            col1Start[0] -= 3;
            col1End[0] -= 3;
            col1pips = totalArmor / 2;
            col2pips = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest == 1) {
                col2pips++;
                col2End[1] += 70 / (float) ((totalArmor / 2) - 1);
                col2End[0] -= 14 / (float) ((totalArmor / 2) - 1);
            }
        }

        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRLStruct(Graphics2D g2d) {
        float[] col2Start =
            { 481 + leftMargin, topMargin + 478 };
        float[] col2End =
            { 494.5f + leftMargin, topMargin + 548 };
        float[] col1Start =
            { 478 + leftMargin, topMargin + 475 };
        float[] col1End =
            { 491.5f + leftMargin, topMargin + 545 };
        Vector<float[]> pipPlotter = new Vector<float[]>();
        int totalArmor = mech.getInternal(Mech.LOC_RLEG);
        int col1pips = 0;
        int col2pips = totalArmor;
        if (totalArmor > 14) {
            col2Start[0] += 3;
            col2End[0] += 3;
            col1pips = totalArmor / 2;
            col2pips = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest == 1) {
                col1pips++;
                col1End[1] += 70 / (float) ((totalArmor / 2) - 1);
                col1End[0] += 14 / (float) ((totalArmor / 2) - 1);
            }
        }
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAStruct(Graphics2D g2d) {
        float[] col1Start =
            { 510 + leftMargin, topMargin + 417 };
        float[] col1End =
            { 515 + leftMargin, topMargin + 473 };
        float[] col2Start =
            { 506 + leftMargin, topMargin + 413 };
        float[] col2End =
            { 511 + leftMargin, topMargin + 469 };
        Vector<float[]> pipPlotter = new Vector<float[]>();
        int totalArmor = mech.getInternal(Mech.LOC_RARM);
        int col1pips = totalArmor;
        int col2pips = 0;
        if (totalArmor > 11) {
            col1pips = totalArmor / 2;
            col2pips = totalArmor / 2;
            int rest = totalArmor % 2;
            if (rest == 1) {
                col2pips++;
                col2End[1] += 56 / (float) ((totalArmor / 2) - 1);
                col2End[0] += 5 / (float) ((totalArmor / 2) - 1);
            }
        } else {
            col1Start[0] -= 1;
            col1End[0] -= 1;
            col1End[1] += 5;
        }

        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLTStruct(Graphics2D g2d) {
        float[] column =
            { 435 + leftMargin, topMargin + 415 };
        float[] pipShift =
            { 5, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 12; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 3) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        column[0] += pipShift[0] * 2;
        for (int pos = 1; pos <= 2; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        column[0] += pipShift[0] / 2;
        for (int pos = 1; pos <= 2; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        column[0] += pipShift[0] / 2;
        pipPlotter.add(new float[]
            { column[0], column[1] });
        column[1] += pipShift[1];

        pipShift[0] *= -1;
        column[1] += pipShift[1] / 2;
        for (int pos = 1; pos <= 4; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 2) == 0) {
                pipShift[0] *= -1;
                column[1] += pipShift[1];
            }
        }

        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRTStruct(Graphics2D g2d) {
        float[] column =
            { 480f + leftMargin, topMargin + 415 };
        float[] pipShift =
            { 5, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = 12;
        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 3) == 0) {
                column[1] += pipShift[1];
                pipShift[0] *= -1;
                column[0] += pipShift[0];
            }

        }

        pips = 2;

        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        pips = 2;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        pips = 1;

        column[0] -= pipShift[0] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[1] += pipShift[1];
        }

        pips = 4;

        column[1] += pipShift[1] / 2;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if ((pos % 2) == 0) {
                pipShift[0] *= -1;
                column[1] += pipShift[1];
            }
        }

        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printCTStruct(Graphics2D g2d) {

        float[] col1Start =
            { 457.5f + leftMargin, topMargin + 423 };
        float[] col1End =
            { 457.5f + leftMargin, topMargin + 471 };
        float[] col2Start =
            { 462.5f + leftMargin, topMargin + 423 };
        float[] col2End =
            { 462.5f + leftMargin, topMargin + 471 };
        float[] col3Start =
            { 467.5f + leftMargin, topMargin + 423 };
        float[] col3End =
            { 467.5f + leftMargin, topMargin + 471 };
        Vector<float[]> pipPlotter = new Vector<float[]>();
        int totalArmor = mech.getInternal(Mech.LOC_CT);
        int col1pips = totalArmor / 3;
        int col2pips = totalArmor / 3;
        int col3pips = totalArmor / 3;
        int rest = totalArmor % 3;
        switch (rest) {
            case 1:
                col2pips++;
                if (totalArmor == 31) {
                    col2End[1] += 49 / (float) ((totalArmor / 3) - 1);
                }
                break;
            case 2:
                col1pips++;
                col3pips++;
                break;
        }

        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col3Start, col3End, col3pips));
        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printHeadStruct(Graphics2D g2d) {
        ImageHelper.drawISPip(g2d, 462.5f + leftMargin, topMargin + 403);
        ImageHelper.drawISPip(g2d, 459.5f + leftMargin, topMargin + 410);
        ImageHelper.drawISPip(g2d, 465.5f + leftMargin, topMargin + 410);
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

    /**
     * Print the critcals for a Mek in the specific location
     *
     * @param g2d
     *            The 2d Graphics object use to print
     * @param location
     *            Current location of the Mek
     * @param lineStart
     *            Where to start printing x
     * @param linePoint
     *            Where to Start printing y
     * @param lineFeed
     *            How much to move down to the next line.
     */
    private void printLocationCriticals(Graphics2D g2d, int location, int lineStart, int linePoint, int lineFeed) {
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
                                engineName = "Primitive I.C.E";
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
                            break;
                        default:
                            break;
                    }

                    if (cs.isArmored()) {
                        engineName = "O " + engineName;
                    }
                    if(cs.isDestroyed()) {
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
                    if(cs.isDestroyed()) {
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
                    critName.append(m.getShotsLeft());

                }

                font = UnitUtil.getNewFont(g2d, critName.toString(), m.getType().isHittable(), 85, 7.0f);
                if(cs.isDestroyed()) {
                	font = font.deriveFont(strikeThroughAttr);
                }
                g2d.setFont(font);

                if ((m.getType() instanceof MiscType) && m.getType().hasFlag(MiscType.F_C3I)) {
                    ImageHelper.printC3iName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_C3S))))) {
                    ImageHelper.printC3sName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((m.getType() instanceof WeaponType) && m.getType().hasFlag(WeaponType.F_C3M)) {
                    ImageHelper.printC3mName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((((m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_C3SBS))))) {
                    ImageHelper.printC3sbName(g2d, lineStart, linePoint, font, m.isArmored());
                } else if ((m.getType() instanceof WeaponType) && m.getType().hasFlag(WeaponType.F_C3MBS)) {
                    ImageHelper.printC3mbName(g2d, lineStart, linePoint, font, m.isArmored());
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
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 172 + ((200 - height) / 2);
        g2d.drawImage(img, drawingX + leftMargin, topMargin + drawingY, width, height, Color.BLACK, null);

    }

    private void printLACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LARM)) {
            return;
        }

        int lineStart = 98;
        int linePoint = 398;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_LARM)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RARM)) {
            return;
        }

        int lineStart = 342;
        int linePoint = 398;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_RARM)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printLLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LLEG)) {
            return;
        }

        int lineStart = 93;
        int linePoint = 671;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_LLEG)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printLTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LT)) {
            return;
        }

        int lineStart = 104;
        int linePoint = 534;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_LT)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printHeadCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_HEAD)) {
            return;
        }

        int lineStart = 196;
        int linePoint = 388;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_HEAD)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RT)) {
            return;
        }

        int lineStart = 348;
        int linePoint = 534;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_RT)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RLEG)) {
            return;
        }

        int lineStart = 338;
        int linePoint = 671;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_RLEG)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printCTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_CT)) {
            return;
        }

        int lineStart = 236;
        int linePoint = 458;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_CT)) {
            g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1]);
            // ImageHelper.printArmorPip(g2d, pipPoints.get(currentPip)[0],
            // pipPoints.get(currentPip)[1]);
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

    private void printLeftShield(Graphics2D g2d) {

        if (!mech.hasShield()) {
            return;
        }
        int[] DCColumn =
            { 390 + leftMargin, topMargin + 32 };
        int[] lineFeed =
            { 5, 6 };
        int DA = UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_LARM);
        int DC = UnitUtil.getShieldDamageCapacity(mech, Mech.LOC_LARM);

        for (int pos = 1; pos <= DC; pos++) {
            ImageHelper.drawArmorPip(g2d, DCColumn[0], DCColumn[1]);
            DCColumn[0] += lineFeed[0];

            if ((pos % 4) == 0) {
                lineFeed[0] *= -1;
                DCColumn[0] += lineFeed[0];
                DCColumn[1] += lineFeed[1];
            }
        }

        int[] DAColumn =
            { 387 + leftMargin, topMargin + 82 };
        lineFeed = new int[]
            { 4, 6 };

        for (int pos = 1; pos <= DA; pos++) {
            ImageHelper.drawDiamond(g2d, DAColumn[0], DAColumn[1]);

            // DAColumn[0] += lineFeed[0];

            // if (pos % 2 == 0) {
            // lineFeed[0] *= -1;
            // DAColumn[0] += lineFeed[0];
            DAColumn[1] += lineFeed[1];
            // }
        }
        g2d.setStroke(new BasicStroke(1));
    }

    private void printRightShield(Graphics2D g2d) {

        if (!mech.hasShield()) {
            return;
        }
        // g2d.setStroke(new BasicStroke(.5f));
        int[] DCColumn =
            { 562 + leftMargin, topMargin + 32 };
        int[] lineFeed =
            { -5, 6 };
        int DA = UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM);
        int DC = UnitUtil.getShieldDamageCapacity(mech, Mech.LOC_RARM);

        for (int pos = 1; pos <= DC; pos++) {
            ImageHelper.drawArmorPip(g2d, DCColumn[0], DCColumn[1]);

            DCColumn[0] += lineFeed[0];

            if ((pos % 4) == 0) {
                lineFeed[0] *= -1;
                DCColumn[0] += lineFeed[0];
                DCColumn[1] += lineFeed[1];
            }
        }

        int[] DAColumn =
            { 565 + leftMargin, topMargin + 82 };
        lineFeed = new int[]
            { -4, 6 };

        for (int pos = 1; pos <= DA; pos++) {
            ImageHelper.drawDiamond(g2d, DAColumn[0], DAColumn[1]);

            // DAColumn[0] += lineFeed[0];

            // if (pos % 2 == 0) {
            // lineFeed[0] *= -1;
            // DAColumn[0] += lineFeed[0];
            DAColumn[1] += lineFeed[1];
            // }
        }
        g2d.setStroke(new BasicStroke(1));
    }

}