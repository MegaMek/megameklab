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
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

import com.kitfox.svg.SVGException;

// TODO Remove this when implemented
@SuppressWarnings("unused")
public class PrintTripod implements Printable {

    protected Image awtHud = null;
    private Mech mech = null;
    private ArrayList<Mech> mechList;

    private Mounted startingMount = null;
    private float startMountx = 0;
    private float startMounty = 0;
    private float endMountx = 0;
    private float endMounty = 0;
    private PrinterJob masterPrintJob;
    private int topMargin = 6;
    private int leftMargin = 11;

    public PrintTripod(ArrayList<Mech> list, PrinterJob masterPrintJob) {
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
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodMechTemplate.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
        printImage(g2d, awtHud, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, Image hud, PageFormat pageFormat) {
        // System.out.println("printImage(Graphics2D g2d, Image image)");
        if (g2d == null) {
            return;
        }

        System.gc();
        printMekImage(g2d, hud);

        if (mech.hasShield()) {
            if ((UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) && (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_LARM) > 0)) {
                try {
                    ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodBothShields.svg")).render(g2d);
                } catch (SVGException e) {
                    e.printStackTrace();
                }
            } else if (UnitUtil.getShieldDamageAbsorbtion(mech, Mech.LOC_RARM) > 0) {
                try {
                    ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodRightShields.svg")).render(g2d);
                } catch (SVGException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodLeftShields.svg")).render(g2d);
                } catch (SVGException e) {
                    e.printStackTrace();
                }
            }
            g2d.setColor(Color.BLACK);
            printLeftShield(g2d);
            printRightShield(g2d);
        } else {
            g2d.setColor(Color.BLACK);
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodNoShields.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

        g2d.setColor(Color.BLACK);


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
        printCLCrits(g2d);

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

        /*
        try {

            // Armor Pips
            if (mech.getArmor(Mech.LOC_CT) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_CT_"+mech.getArmor(Mech.LOC_CT)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_LT) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_LT_"+mech.getArmor(Mech.LOC_LT)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_RT) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_RT_"+mech.getArmor(Mech.LOC_RT)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_LLEG) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_LLEG_"+mech.getArmor(Mech.LOC_LLEG)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_RLEG) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_RLEG_"+mech.getArmor(Mech.LOC_RLEG)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_LARM) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_LARM_"+mech.getArmor(Mech.LOC_LARM)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_RARM) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_RARM_"+mech.getArmor(Mech.LOC_RARM)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_HEAD, true) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_HEAD_"+mech.getArmor(Mech.LOC_HEAD)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_CT, true) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_CT_R_"+mech.getArmor(Mech.LOC_CT, true)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_LT, true) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_LT_R_"+mech.getArmor(Mech.LOC_LT, true)+"_Humanoid.svg")).render(g2d);
            }
            if (mech.getArmor(Mech.LOC_RT, true) > 0) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Armor_RT_R_"+mech.getArmor(Mech.LOC_RT, true)+"_Humanoid.svg")).render(g2d);
            }
            // Internal Pips
            //ImageHelper.loadSVGImage(new File("data/images/recordsheets/TripodIS"+(int)mech.getWeight()+".svg")).render(g2d);

        } catch (SVGException e1) {
            e1.printStackTrace();
        }

        // Internal Pips
        printLAStruct(g2d);
        printRAStruct(g2d);
        printLTStruct(g2d);
        printRTStruct(g2d);
        printCTStruct(g2d);
        printHeadStruct(g2d);
        printLLStruct(g2d);
        printRLStruct(g2d);
        printCLStruct(g2d);
        */

        printHeatSinks(g2d);
        // g2d.translate(pageFormat.getImageableX(),
        // pageFormat.getImageableY());
        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());


    }

    private void printMechData(Graphics2D g2d) {
        String mekName = mech.getChassis() + " " + mech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, mekName, true, 180, 10.0f));
        g2d.drawString(mekName, 49 + leftMargin, topMargin + 118.5f);
        Font font;
        if (mech instanceof LandAirMech) {
            font = UnitUtil.deriveFont(true, 13.0f);
            g2d.setFont(font);
            g2d.drawString("LAND-AIR", 70 + leftMargin, topMargin + 86.2f);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);


        if ((mech.getCrew() != null) && !mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = mech.getCrew();
            g2d.drawString(pilot.getName(), 264 + leftMargin, topMargin + 119);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295 + leftMargin, topMargin + 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365 + leftMargin, topMargin + 132);
        }
        
        //TODO: Need to add code for getting and displaying gunner also.
        // I assume the code would be similar to the above, but substitute gunner?
        // **The below coordinates are the correct alignment.**
        //  if ((mech.getCrew() != null) && !mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
        //      Crew gunner = mech.getCrew();
        //    g2d.drawString(gunner.getName(), 276 + leftMargin, topMargin + 180);
        //    g2d.drawString(String.valueOf(gunner.getGunnery()), 295 + leftMargin, topMargin + 193);
        //    g2d.drawString(String.valueOf(gunner.getPiloting()), 365 + leftMargin, topMargin + 193);
        //  }

        if (mech.hasTSM() && (mech.getSuperCharger() == null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSM = (int) Math.ceil(walkTSM * 1.5) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP()) + " [" + runTSM + "]", 83 + leftMargin, topMargin + 154.3f);
        } else if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            int mascMP = (int) Math.ceil((mech.getWalkMP() * 2.5)) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 83 + leftMargin, topMargin + 154.3f);
        } else if (mech.hasTSM() && (mech.getSuperCharger() != null)) {
            int walkTSM = mech.getWalkMP() + 1;
            int runTSMandSuperCharge = (walkTSM * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()) + " [" + walkTSM + "]", 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + runTSMandSuperCharge + "]", 83 + leftMargin, topMargin + 154.3f);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            int mascMP = (mech.getWalkMP() * 2) - (mech.hasMPReducingHardenedArmor() ? 1 : 0);
            g2d.drawString(Integer.toString(mech.getWalkMP()), 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMPwithoutMASC()) + " [" + mascMP + "]", 83 + leftMargin, topMargin + 154.3f);
        } else if (mech instanceof LandAirMech) {
            LandAirMech lam = (LandAirMech)mech;
            g2d.drawString(Integer.toString(mech.getWalkMP())+"/"+Integer.toString(lam.getAirMechWalkMP())+"/"+Integer.toString(lam.getFighterModeWalkMP()), 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP())+"/"+Integer.toString(lam.getAirMechRunMP())+"/"+Integer.toString(lam.getFighterModeRunMP()), 83 + leftMargin, topMargin + 154.3f);
        } else {
            g2d.drawString(Integer.toString(mech.getWalkMP()), 83 + leftMargin, topMargin + 144);
            g2d.drawString(Integer.toString(mech.getRunMP()), 83 + leftMargin, topMargin + 154.3f);
        }
        if (mech.hasUMU()) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BipedMechUnderwater.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
            g2d.drawString(Integer.toString(mech.getActiveUMUCount()), 83 + leftMargin, topMargin + 164.4f);
        } else if (mech.getJumpMP() > 0){
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BipedMechJumping.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
            g2d.drawString(Integer.toString(mech.getJumpMP()), 83 + leftMargin, topMargin + 164.4f);
        }

        int tonnage = (int) Math.ceil(mech.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177 + leftMargin, topMargin + 132.5f);

        if (mech.isIndustrial()) {
            g2d.drawString("(Industrial)", 155 + leftMargin, topMargin + 95);
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
        float lineFeed = 8;

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
            g2d.drawString(ejectString, 25 + leftMargin, topMargin + 327);
            ejectString = "as having a Full-Head Ejection System.";
            g2d.drawString(ejectString, 45 + leftMargin, topMargin + 337);
        }

        // Cost/BV
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
        // 52, 350);

        String isName = "";

        if (mech.hasCompositeStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_COMPOSITE);
        } else if (mech.hasReinforcedStructure()) {
            isName = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_REINFORCED);
        }

        if (isName.trim().length() > 0) {
            g2d.setFont(UnitUtil.getNewFont(g2d, isName, true, 44, 10.0f));
            g2d.drawString(isName, 446 + leftMargin, topMargin + 558);
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
            g2d.drawString(armorName, 461 + leftMargin, topMargin + 249);
        }

        if (UnitUtil.hasBAR(mech) && !mech.hasPatchworkArmor()) {
            g2d.setFont(UnitUtil.getNewFont(g2d, armorName, true, 38, 10.0f));
            g2d.drawString("BAR " + UnitUtil.getLowestBARRating(mech), 464 + leftMargin, topMargin + 249);
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

        font = UnitUtil.deriveFont(true, 6);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 46f, topMargin + 762.4f);

        if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BipedMech3rdGyro.svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()) + " (" + Integer.toString(mech.getHeatCapacity(true,false)) + ")", 507 + leftMargin, topMargin + 600);
        if (mech.hasLaserHeatSinks()) {
            g2d.drawString("Laser", 507 + leftMargin, topMargin + 608);
        } else if (mech.hasDoubleHeatSinks()) {
            g2d.drawString("Double", 507 + leftMargin, topMargin + 608);
        } else {
            g2d.drawString("Single", 507 + leftMargin, topMargin + 608);
        }


        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Heat_Sinks_"+mech.heatSinks()+".svg")).render(g2d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = UnitUtil.deriveFont(6.0f);
        g2d.setFont(font);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_HEAD)) + ")", 485 + leftMargin, topMargin + 42);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT)) + ")", 456 + leftMargin, topMargin + 42);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT)) + ")", 538 + leftMargin, topMargin + 42);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT)) + ")", 448 + leftMargin, topMargin + 222);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LARM)) + ")", 389 + leftMargin, topMargin + 190);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RARM)) + ")", 557 + leftMargin, topMargin + 190);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LLEG)) + ")", 380 + leftMargin, topMargin + 270);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RLEG)) + ")", 569 + leftMargin, topMargin + 270);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CLEG)) + ")", 502 + leftMargin, topMargin + 288);
        // Rear
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_LT, true)) + ")", 410 + leftMargin, topMargin + 360);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_CT, true)) + ")", 506 + leftMargin, topMargin + 372);
        g2d.drawString("(" + Integer.toString(mech.getArmor(Mech.LOC_RT, true)) + ")", 550 + leftMargin, topMargin + 360);
        // patchwork armor info
        if (mech.hasPatchworkArmor()) {
            font = UnitUtil.deriveFont(5.5f);
            g2d.setFont(font);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_HEAD), 494 + leftMargin, topMargin + 42);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 436 + leftMargin, topMargin + 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 508 + leftMargin, topMargin + 68);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 475 + leftMargin, topMargin + 230);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LARM), 398 + leftMargin, topMargin + 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RARM), 548 + leftMargin, topMargin + 223);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LLEG), 389 + leftMargin, topMargin + 280);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RLEG), 556 + leftMargin, topMargin + 280);
            // Rear
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_LT), 415 + leftMargin, topMargin + 363);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_CT), 493 + leftMargin, topMargin + 277);
            g2d.drawString(UnitUtil.getArmorString(mech, Mech.LOC_RT), 559 + leftMargin, topMargin + 363);
            font = UnitUtil.deriveFont(7.0f);
            g2d.setFont(font);
        }
        // Internal
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_HEAD)) + ")", 460 + leftMargin, topMargin + 400);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LT)) + ")", 427 + leftMargin, topMargin + 409);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RT)) + ")", 528 + leftMargin, topMargin + 409);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LARM)) + ")", 390 + leftMargin, topMargin + 486);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RARM)) + ")", 533 + leftMargin, topMargin + 486);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_CT)) + ")", 482 + leftMargin, topMargin + 540);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_LLEG)) + ")", 403 + leftMargin, topMargin + 547);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_RLEG)) + ")", 522 + leftMargin, topMargin + 547);
        g2d.drawString("(" + Integer.toString(mech.getInternal(Mech.LOC_CLEG)) + ")", 440 + leftMargin, topMargin + 547);
    }

    private void printLACrits(Graphics2D g2d) {

        float lineStart = 56;
        float linePoint = 415;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRACrits(Graphics2D g2d) {

        float lineStart = 294;
        float linePoint = 415;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RARM, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printCTCrits(Graphics2D g2d) {

        float lineStart = 174;
        float linePoint = 477f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_CT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printLTCrits(Graphics2D g2d) {

        float lineStart = 56;
        float linePoint = 555.7f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRTCrits(Graphics2D g2d) {

        float lineStart = 294;
        float linePoint = 555.7f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RT, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printHeadCrits(Graphics2D g2d) {

        float lineStart = 174.2f;
        float linePoint = 405;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_HEAD, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printLLCrits(Graphics2D g2d) {

        float lineStart = 56;
        float linePoint = 696.5f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printRLCrits(Graphics2D g2d) {

        float lineStart = 294;
        float linePoint = 696.5f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart + leftMargin, topMargin + linePoint, lineFeed);
    }

    private void printCLCrits(Graphics2D g2d) {

        float lineStart = 175;
        float linePoint = 696.5f;
        float lineFeed = 8.2f;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart + leftMargin, topMargin + linePoint, lineFeed);
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

        int width = Math.min(116, img.getWidth(null));
        int height = Math.min(145, img.getHeight(null));
        int drawingX = 245 + ((116 - width) / 2);
        int drawingY = 233 + ((145 - height) / 2);
        g2d.setColor(Color.BLACK);
        g2d.drawImage(img, drawingX + leftMargin, topMargin + drawingY, width, height, null);

    }

    private void printLACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LARM)) {
            return;
        }

        float lineStart = 98;
        float linePoint = 405;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LARM)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRACase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RARM)) {
            return;
        }

        float lineStart = 344;
        float linePoint = 405;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RARM)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }

    }

    private void printLLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LLEG)) {
            return;
        }

        float lineStart = 93.5f;
        float linePoint = 686;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LLEG)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printLTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_LT)) {
            return;
        }

        float lineStart = 104;
        float linePoint = 545;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_LT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printHeadCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_HEAD)) {
            return;
        }

        float lineStart = 196;
        float linePoint = 395.5f;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_HEAD)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RT)) {
            return;
        }

        float lineStart = 350;
        float linePoint = 545;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printRLCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_RLEG)) {
            return;
        }

        float lineStart = 339;
        float linePoint = 686;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_RLEG)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printCTCase(Graphics2D g2d) {

        if (!UnitUtil.hasAmmo(mech, Mech.LOC_CT)) {
            return;
        }

        float lineStart = 238f;
        float linePoint = 466;

        g2d.setFont(UnitUtil.deriveFont(7.0f));
        if (!mech.hasCASEII(Mech.LOC_CT)) {
            //g2d.drawString("(CASE II)", lineStart + leftMargin, topMargin + linePoint);
            g2d.drawString("(CASE)", lineStart + leftMargin, topMargin + linePoint);
        }
    }

    private void printLeftShield(Graphics2D g2d) {
        try {
            for (Mounted mount:mech.getEquipment()) {
                if ((mount.getLocation() == Mech.LOC_LARM) && (mount.getType() instanceof MiscType)) {
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_LARGE)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Large.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Large_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Large_No_LA.svg")).render(g2d);
                        }
                    }
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_MEDIUM)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Medium.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Medium_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Medium_No_LA.svg")).render(g2d);
                        }
                    }
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_SMALL)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Small.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_LARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Small_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_L_Small_No_LA.svg")).render(g2d);
                        }
                    }
                }
            }
        } catch (SVGException e) {
            e.printStackTrace();
        }
    }

    private void printRightShield(Graphics2D g2d) {
        try {
            for (Mounted mount:mech.getEquipment()) {
                if ((mount.getLocation() == Mech.LOC_RARM) && (mount.getType() instanceof MiscType)) {
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_LARGE)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Large.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Large_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Large_No_LA.svg")).render(g2d);
                        }
                    }
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_MEDIUM)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Medium.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Medium_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Medium_No_LA.svg")).render(g2d);
                        }
                    }
                    if (mount.getType().hasFlag(MiscType.F_CLUB) && mount.getType().hasSubType(MiscType.S_SHIELD_SMALL)) {
                        if (mech.hasWorkingSystem(Mech.ACTUATOR_HAND, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Small.svg")).render(g2d);
                        } else if (mech.hasWorkingSystem(Mech.ACTUATOR_LOWER_ARM, Mech.LOC_RARM)) {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Small_No_Hand.svg")).render(g2d);
                        } else {
                            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Shield_R_Small_No_LA.svg")).render(g2d);
                        }
                    }
                }
            }
        } catch (SVGException e) {
            e.printStackTrace();
        }
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

    private void printCLStruct(Graphics2D g2d) {
        float[] column =
            { 463.5f + leftMargin, topMargin + 475};
        float[] pipShift =
            { 0, 4 };

        int totalArmor = mech.getInternal(Mech.LOC_CLEG);

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