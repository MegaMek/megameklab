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
import java.awt.geom.Ellipse2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
        g2d.drawImage(image, 18, 18, 558, 738, Color.BLACK, null);
        printMekImage(g2d, hud);
        if (mech.hasShield()) {

            if ((UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) && (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_LARM) > 0)) {
                g2d.drawImage(ImageHelper.getShieldImage(), 382, 18, 193, 351, null, null);
            } else if (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) {
                g2d.drawImage(ImageHelper.getRightShieldImage(), 382, 18, 193, 351, null, null);
            } else {
                g2d.drawImage(ImageHelper.getLeftShieldImage(), 382, 18, 193, 351, null, null);
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
        g2d.drawString(mekName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((mech.getCrew() != null) && !mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = mech.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        if (mech.hasTSM() && (mech.getSuperCharger() == null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 79, 155);
        } else if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else if (mech.hasTSM() && (mech.getSuperCharger() != null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSMandSuperCharge = (walkTSM * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + runTSMandSuperCharge + "]", 79, 155);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79, 155);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 79, 155);
        }

        if (mech.hasUMU()) {
            g2d.drawImage(ImageHelper.getUMImage(), 31, 156, 40, 15, null);
            g2d.drawString(Integer.toString(mech.getAllUMUCount()), 79, 166);
        } else {
            g2d.drawString(Integer.toString(mech.getJumpMP()), 79, 166);
        }

        int tonnage = (int) Math.ceil(mech.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134);

        if (mech.isIndustrial()) {
            if (mech.isPrimitive()) {
                g2d.drawString("(Primitive Industrial)", 155, 97);
            } else {
                g2d.drawString("(Industrial)", 155, 97);
            }
        } else if (mech.isPrimitive()) {
            g2d.drawString("(Primitive)", 155, 97);
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

        // full head eject
        if (mech.hasFullHeadEject()) {
            String ejectString = "Note: If playing under Advanced Rules, treat head";
            g2d.setFont(UnitUtil.deriveFont(false, 8));
            g2d.drawString(ejectString, 25, 325);
            ejectString = "as having a Full-Head Ejection System.";
            g2d.drawString(ejectString, 45, 335);
        }

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(mech.calculateBattleValue(true, true)), 150, 350);

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
            g2d.drawString(isName, 442, 553);
        }

        String armorName = "";

        if (mech.hasHardenedArmor() && !mech.hasPatchworkArmor()) {
            armorName = EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_HARDENED);
        }

        if (armorName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString(armorName, 461, 249);
        }

        if (UnitUtil.hasBAR(mech) && !mech.hasPatchworkArmor()) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString("BAR " + UnitUtil.getLowestBARRating(mech), 461, 249);
        }

        if ((mech.getSource() != null) && (mech.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, mech.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(mech.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", mech.getYear()), 177, nextDataLine);

        }

        g2d.setFont(UnitUtil.getNewFont(g2d, techBase, false, 51, 10.0f));
        g2d.drawString(techBase, 177, 145);

        font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        g2d.drawString("2011", 62.5f, 745f);

        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            g2d.drawImage(ImageHelper.getGyroPipImage(), 235, 588, 9, 8, null);
        }

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (mech.hasLaserHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502, 595);
            g2d.drawString("Laser", 502, 603);
        } else if (mech.hasDoubleHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502, 595);
            g2d.drawString("Double", 502, 603);
        } else {
            g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity()) + ")", 502, 595);
            g2d.drawString("Single", 502, 603);
        }

        Dimension column = new Dimension(504, 612);
        Dimension pipShift = new Dimension(9, 9);

        int pipsPerColumn = (int) Math.max(10, Math.ceil(mech.heatSinks() / 4.0));

        for (int pos = 1; pos <= mech.heatSinks(); pos++) {
            ImageHelper.drawHeatSinkPip(g2d, column.width, column.height);
            column.height += pipShift.height;

            if (pos % pipsPerColumn == 0) {
                column.height -= pipShift.height * pipsPerColumn;
                column.width += pipShift.width;
            }

        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = UnitUtil.deriveFont(7.0f);
        g2d.setFont(font);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_HEAD)) + ")", 485, 45);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT)) + ")", 434, 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT)) + ")", 506, 60);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT)) + ")", 472, 222);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LARM)) + ")", 394, 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RARM)) + ")", 546, 215);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LLEG)) + ")", 384, 273);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RLEG)) + ")", 554, 273);
        // Rear
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT, true)) + ")", 403, 362);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT, true)) + ")", 480, 277);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT, true)) + ")", 546, 362);
        // patchwork armor info
        if (mech.hasPatchworkArmor()) {
            font = UnitUtil.deriveFont(5.5f);
            g2d.setFont(font);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_HEAD), 494, 45);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 434, 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 506, 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 472, 230);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LARM), 394, 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RARM), 546, 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LLEG), 384, 280);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RLEG), 554, 280);
            // Rear
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 416, 362);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 493, 277);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 559, 362);
            font = UnitUtil.deriveFont(7.0f);
            g2d.setFont(font);
        }
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
        int linePoint = 399;
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
            { 494, 179 };
        float[] rightColumnEnd =
            { 525, 297 };
        float[] midColumnStart =
            { 500, 179 };
        float[] midColumnEnd =
            { 532, 297 };
        float[] leftColumnStart =
            { 506, 179 };
        float[] leftColumnEnd =
            { 538, 297 };

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

        /*
        for (int pos = 1; pos <= 20; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            // ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
            topColumn[1] += pipShift[1];
            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0] + 1.8f;
                pipShift[1] *= -1;
                topColumn[1] += pipShift[1] + 7;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        for (int pos = 1; pos <= 12; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
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

        for (int pos = 1; pos <= 6; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            bottomColumn[1] += pipShift[1];
            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0] + 1.8f;
                pipShift[1] *= -1;
                bottomColumn[1] += pipShift[1] + 7;
            }
        }

        for (int pos = 1; pos <= 4; pos++) {
            pipPlotter.add(new float[]
                { footColumn[0], footColumn[1] });
            footColumn[0] += pipShift[0];
        }
        */
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLLArmor(Graphics2D g2d) {
        float[] leftColumnStart =
            { 443, 179 };
        float[] leftColumnEnd =
            { 412, 297 };
        float[] midColumnStart =
            { 449, 179 };
        float[] midColumnEnd =
            { 418, 297 };
        float[] rightColumnStart =
            { 455, 179 };
        float[] rightColumnEnd =
            { 424, 297 };

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
            { 404, 80 };
        float[] leftColumnEnd =
            { 395, 165 };
        float[] midColumnStart =
            { 410, 80 };
        float[] midColumnEnd =
            { 401, 165 };
        float[] rightColumnStart =
            { 416, 80 };
        float[] rightColumnEnd =
            { 407, 165 };

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
            { 533, 80 };
        float[] leftColumnEnd =
            { 542, 165 };
        float[] midColumnStart =
            { 539, 80 };
        float[] midColumnEnd =
            { 548, 165 };
        float[] rightColumnStart =
            { 546, 80 };
        float[] rightColumnEnd =
            { 555, 165 };

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
            { 429, 88 };
        float[] col1End =
            { 429, 118 };
        float[] col2Start =
            { 435, 88 };
        float[] col2End =
            { 435, 118 };
        float[] col3Start =
            { 441, 88 };
        float[] col3End =
            { 441, 118 };
        float[] col4Start =
            { 447, 88 };
        float[] col4End =
            { 447, 118 };
        float[] col5Start =
            { 453, 88 };
        float[] col5End =
            { 453, 118 };
        float[] col6Start =
            { 447, 124 };
        float[] col6End =
            { 450, 154 };
        float[] col7Start =
            { 453, 124 };
        float[] col7End =
            { 456, 154 };
        float[] col8Start =
            { 436, 160 };
        float[] col8End =
            { 456, 160 };
        float[] col9Start =
            { 436, 166 };
        float[] col9End =
            { 456, 166 };

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
            { 430, 311.5f };
        float[] col1End =
            { 431, 345.5f };
        float[] col2Start =
            { 436, 311.5f };
        float[] col2End =
            { 436, 345.5f };
        float[] col3Start =
            { 441, 311.5f };
        float[] col3End =
            { 441, 345.5f };
        float[] col4Start =
            { 446, 311.5f };
        float[] col4End =
            { 446, 345.5f };
        float[] col5Start =
            { 451, 311.5f };
        float[] col5End =
            { 451, 345.5f };
        float[] col6Start =
            { 456, 311.5f };
        float[] col6End =
            { 456, 332.5f };

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
            { 521, 88 };
        float[] col1End =
            { 521, 118 };
        float[] col2Start =
            { 515, 88 };
        float[] col2End =
            { 515, 118 };
        float[] col3Start =
            { 509, 88 };
        float[] col3End =
            { 509, 118 };
        float[] col4Start =
            { 503, 88 };
        float[] col4End =
            { 503, 118 };
        float[] col5Start =
            { 497, 88 };
        float[] col5End =
            { 497, 118 };
        float[] col6Start =
            { 503, 124 };
        float[] col6End =
            { 500, 154 };
        float[] col7Start =
            { 497, 124 };
        float[] col7End =
            { 494, 154 };
        float[] col8Start =
            { 514, 160 };
        float[] col8End =
            { 494, 160 };
        float[] col9Start =
            { 514, 166 };
        float[] col9End =
            { 494, 166 };

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
            { 519.5f, 311.5f };
        float[] col1End =
            { 519.5f, 345.5f };
        float[] col2Start =
            { 514.5f, 311.5f };
        float[] col2End =
            { 514.5f, 345.5f };
        float[] col3Start =
            { 509.5f, 311.5f };
        float[] col3End =
            { 509.5f, 345.5f };
        float[] col4Start =
            { 504.5f, 311.5f };
        float[] col4End =
            { 504.5f, 345.5f };
        float[] col5Start =
            { 499.5f, 311.5f };
        float[] col5End =
            { 499.5f, 345.5f };
        float[] col6Start =
            { 494.5f, 311.5f };
        float[] col6End =
            { 494.5f, 332.5f };

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
            { 465, 105 };
        float[] col1End =
            { 465, 176 };
        float[] col2Start =
            { 470, 105 };
        float[] col2End =
            { 470, 176 };
        float[] col3Start =
            { 475, 105 };
        float[] col3End =
            { 475, 176 };
        float[] col4Start =
            { 480, 105 };
        float[] col4End =
            { 480, 176 };
        float[] col5Start =
            { 485, 105 };
        float[] col5End =
            { 485, 176 };

        int totalArmor = mech.getOArmor(Mech.LOC_CT);

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
                    col3End[1] += 71 / (float) (totalArmor / 5 - 1);
                }
                break;
            case 2:
                pips1++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col5End[1] += 71 / (float) (totalArmor / 5 - 1);
                }
                break;
            case 3:
                pips1++;
                pips3++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col3End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col5End[1] += 71 / (float) (totalArmor / 5 - 1);
                }
                break;
            case 4:
                pips1++;
                pips2++;
                pips4++;
                pips5++;
                if (totalArmor > 40) {
                    col1End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col2End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col4End[1] += 71 / (float) (totalArmor / 5 - 1);
                    col5End[1] += 71 / (float) (totalArmor / 5 - 1);
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
            Ellipse2D ellipse = new Ellipse2D.Double(475.75, 68, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 72.5f);
        }

        if (totalArmor >= 8) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(472.75, 74, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 472, 78.5f);
        }

        if (totalArmor >= 7) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(478.75, 74, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 478, 78.5f);
        }

        if (totalArmor >= 6) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(469.75, 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469, 83.5f);
        }

        if (totalArmor >= 5) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(475.75, 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 83.5f);
        }

        if (totalArmor >= 4) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(481.75, 79, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481, 83.5f);
        }

        if (totalArmor >= 3) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(469.75, 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 469, 89.5f);
        }

        if (totalArmor >= 2) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(475.75, 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 475, 89.5f);
        }

        if (totalArmor >= 1) {
            g2d.setColor(Color.white);
            Ellipse2D ellipse = new Ellipse2D.Double(481.75, 85, fillCircle.width, fillCircle.height);
            g2d.fill(ellipse);
            g2d.setColor(Color.black);
            ImageHelper.drawArmorPip(g2d, 481, 89.5f);
        }
        g2d.setColor(Color.black);
    }

    private void printCTRArmor(Graphics2D g2d) {

        float[] col1Start =
            { 465, 301 };
        float[] col1End =
            { 465, 360 };
        float[] col2Start =
            { 470, 301 };
        float[] col2End =
            { 470, 360 };
        float[] col3Start =
            { 475, 301 };
        float[] col3End =
            { 475, 360 };
        float[] col4Start =
            { 480, 301 };
        float[] col4End =
            { 480, 360 };
        float[] col5Start =
            { 485, 301 };
        float[] col5End =
            { 485, 360 };

        int totalArmor = mech.getOArmor(Mech.LOC_CT, true);

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
            { 414, 417 };
        float[] col1End =
            { 409, 473 };
        float[] col2Start =
            { 418, 413 };
        float[] col2End =
            { 413, 469 };
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
                col2End[1] += 56 / (float) (totalArmor / 2 - 1);
                col2End[0] -= 5 / (float) (totalArmor / 2 - 1);
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
            { 443, 478 };
        float[] col1End =
            { 430, 548 };
        float[] col2Start =
            { 446, 475 };
        float[] col2End =
            { 432, 545 };
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
                col2End[1] += 70 / (float) (totalArmor / 2 - 1);
                col2End[0] -= 14 / (float) (totalArmor / 2 - 1);
            }
        }

        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRLStruct(Graphics2D g2d) {
        float[] col2Start =
            { 481, 478 };
        float[] col2End =
            { 494.5f, 548 };
        float[] col1Start =
            { 478, 475 };
        float[] col1End =
            { 491.5f, 545 };
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
                col1End[1] += 70 / (float) (totalArmor / 2 - 1);
                col1End[0] += 14 / (float) (totalArmor / 2 - 1);
            }
        }
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col1Start, col1End, col1pips));
        pipPlotter.addAll(ImageHelper.getPointsAlongLine(col2Start, col2End, col2pips));
        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRAStruct(Graphics2D g2d) {
        float[] col1Start =
            { 510, 417 };
        float[] col1End =
            { 515, 473 };
        float[] col2Start =
            { 506, 413 };
        float[] col2End =
            { 511, 469 };
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
                col2End[1] += 56 / (float) (totalArmor / 2 - 1);
                col2End[0] += 5 / (float) (totalArmor / 2 - 1);
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
            { 435, 415 };
        float[] pipShift =
            { 5, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 12; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 3 == 0) {
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

            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                column[1] += pipShift[1];
            }
        }

        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRTStruct(Graphics2D g2d) {
        float[] column =
            { 480f, 415 };
        float[] pipShift =
            { 5, 5 };

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = 12;
        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { column[0], column[1] });
            column[0] += pipShift[0];

            if (pos % 3 == 0) {
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

            if (pos % 2 == 0) {
                pipShift[0] *= -1;
                column[1] += pipShift[1];
            }
        }

        printISPoints(g2d, pipPlotter, totalArmor);

    }

    private void printCTStruct(Graphics2D g2d) {

        float[] col1Start =
            { 457.5f, 423 };
        float[] col1End =
            { 457.5f, 471 };
        float[] col2Start =
            { 462.5f, 423 };
        float[] col2End =
            { 462.5f, 471 };
        float[] col3Start =
            { 467.5f, 423 };
        float[] col3End =
            { 467.5f, 471 };
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
                    col2End[1] += 49 / (float) (totalArmor / 3 - 1);
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
        ImageHelper.drawISPip(g2d, 462.5f, 403);
        ImageHelper.drawISPip(g2d, 459.5f, 410);
        ImageHelper.drawISPip(g2d, 465.5f, 410);
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
                    critName.append(ammo.getShots());

                }

                g2d.setFont(UnitUtil.getNewFont(g2d, critName.toString(), m.getType().isHittable(), 85, 7.0f));

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
        int drawingX = 235 + ((148 - width) / 2);
        int drawingY = 172 + ((200 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

    private void printLACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LARM)) {
            return;
        }

        int lineStart = 98;
        int linePoint = 398;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (mech.hasCASEII(Mech.LOC_LARM)) {
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            g2d.drawString("(CASE II)", lineStart, linePoint);

        } else {
            g2d.drawString("(CASE)", lineStart, linePoint);
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
            { 390, 32 };
        int[] lineFeed =
            { 5, 6 };
        int DA = UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_LARM);
        int DC = UnitUtil.getShieldDamageCapacity(mech, Mech.LOC_LARM);

        for (int pos = 1; pos <= DC; pos++) {
            ImageHelper.drawArmorPip(g2d, DCColumn[0], DCColumn[1]);
            DCColumn[0] += lineFeed[0];

            if (pos % 4 == 0) {
                lineFeed[0] *= -1;
                DCColumn[0] += lineFeed[0];
                DCColumn[1] += lineFeed[1];
            }
        }

        int[] DAColumn =
            { 387, 82 };
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
            { 562, 32 };
        int[] lineFeed =
            { -5, 6 };
        int DA = UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM);
        int DC = UnitUtil.getShieldDamageCapacity(mech, Mech.LOC_RARM);

        for (int pos = 1; pos <= DC; pos++) {
            ImageHelper.drawArmorPip(g2d, DCColumn[0], DCColumn[1]);

            DCColumn[0] += lineFeed[0];

            if (pos % 4 == 0) {
                lineFeed[0] *= -1;
                DCColumn[0] += lineFeed[0];
                DCColumn[1] += lineFeed[1];
            }
        }

        int[] DAColumn =
            { 565, 82 };
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