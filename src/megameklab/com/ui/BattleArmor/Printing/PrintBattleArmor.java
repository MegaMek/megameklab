/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.BattleArmor.Printing;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.kitfox.svg.SVGException;

import megamek.common.BattleArmor;
import megamek.common.MiscType;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperBattleArmor;
import megameklab.com.util.UnitUtil;

public class PrintBattleArmor implements Printable {

    private BattleArmor battleArmor = null;
    private List<BattleArmor> battleArmorList;
    private int pageMarginBase = 135; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;
    private boolean isAdvanced = false;

    public PrintBattleArmor(List<BattleArmor> list) {
        battleArmorList = list;
        if (list.size() > 0) {
            battleArmor = list.get(0);
        }

        /*
         * if (awtImage != null) { System.out.println("Width: " +
         * awtImage.getWidth(null)); System.out.println("Height: " +
         * awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if ((null == g2d) || (null == battleArmor)) {
            return;
        }

        isAdvanced = false;
        currentMargin = 0;
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/BATemplate.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
        g2d.drawImage(ImageHelper.getFluffImage(battleArmor, ImageHelper.imageBattleArmor), 420, 20, 25, 45, null);

        int stop = Math.min(5, battleArmorList.size() - currentPosition);
        for (int pos = 0; pos < stop; pos++) {
            battleArmor = battleArmorList.get(pos + currentPosition);
            int squadNumber = pos+1;
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/Squad"+squadNumber+"_"+battleArmor.getTroopers()+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }

            printBattleArmorData(g2d, squadNumber);
            if (battleArmor.getArmor(1) > 0) {
                try {
                    ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/Squad"+squadNumber+"_"+battleArmor.getTroopers()+"_"+battleArmor.getArmor(1)+".svg")).render(g2d);
                } catch (SVGException e) {
                    e.printStackTrace();
                }
            }

            printWeaponsNEquipment(g2d);

            currentMargin += pageMarginBase;
        }
        System.gc();
        Font font = UnitUtil.deriveFont(true, 6);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 41f, 769.5f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }

    private void printBattleArmorData(Graphics2D g2d, int squadNumber) {
        String battleArmorName = battleArmor.getChassis() + " " + battleArmor.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, battleArmorName, true, 85, 8.0f));
        g2d.drawString(battleArmorName, 53, 107.6f + currentMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((battleArmor.getSource() != null) && (battleArmor.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 143, 107.6f + currentMargin);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(battleArmor.getSource(), 158, 107.6f + currentMargin);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 143, 107.6f + currentMargin);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format(" %1$s", battleArmor.getYear()), 161, 107.6f + currentMargin);
        }

        font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);
        String techBase = "IS";

        if (battleArmor.isMixedTech()) {
            if (battleArmor.isClan()) {
                techBase = "Mixed Clan";
            } else {
                techBase = "Mixed IS";
            }
        } else if (battleArmor.isClan()) {
            techBase = "Clan";
        }

        if (!isAdvanced && ((battleArmor.getTechLevel() == TechConstants.T_IS_ADVANCED) || (battleArmor.getTechLevel() == TechConstants.T_CLAN_ADVANCED))) {
            techBase += " (Advanced)";
            isAdvanced = true;
        } else if (!isAdvanced && ((battleArmor.getTechLevel() == TechConstants.T_IS_EXPERIMENTAL) || (battleArmor.getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL))) {
            techBase+= " (Experimental)";
            isAdvanced = true;
        }
        if (squadNumber == 0) {
            ImageHelper.printCenterString(g2d, techBase, g2d.getFont(), 520, 64);
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);
        String groundMP = Integer.toString(battleArmor.getWalkMP(true, true, false, false, false));
        if (battleArmor.hasDWP()) {
            groundMP = groundMP + " [" + Integer.toString(battleArmor.getWalkMP(true, true, false, true, false)) + "]";
        }
        g2d.drawString(groundMP, 78, 131.3f + currentMargin);
        int jumpMP = battleArmor.getJumpMP(true, true, true);
        int umuMP = battleArmor.getActiveUMUCount();
        if ((jumpMP > 0) && (umuMP == 0)) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = String.format("%1$s: ", battleArmor.getMovementModeAsString());
            g2d.drawString(movment, 143, 131.3f + currentMargin);

            float positionX = 143 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            String printString = Integer.toString(jumpMP);
            if (battleArmor.hasDWP() || battleArmor.isBurdened()) {
                printString = "[" + printString + "]";
            }
            g2d.drawString(printString, positionX, 131.3f + currentMargin);
        } else if ((umuMP > 0) && (jumpMP == 0)) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = String.format("%1$s: ", battleArmor.getMovementModeAsString());
            g2d.drawString(movment, 143, 131.3f + currentMargin);

            float positionX = 143 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            String printString = Integer.toString(umuMP);
            if (battleArmor.hasDWP() || battleArmor.isBurdened()) {
                printString = "[" + printString + "]";
            }
            g2d.drawString(printString, positionX, 131.3f + currentMargin);
        } else if ((umuMP > 0) && (jumpMP > 0)) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = "Jump: ";
            g2d.drawString(movment, 110, 131.3f + currentMargin);

            float positionX = 110 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            String printString = Integer.toString(jumpMP);
            if (battleArmor.hasDWP() || battleArmor.isBurdened()) {
                printString = "[" + printString + "]";
            }
            g2d.drawString(printString, positionX, 131.3f + currentMargin);
            movment = String.format("%1$s: ", battleArmor.getMovementModeAsString());
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            g2d.drawString(movment, 160, 131.3f + currentMargin);

            positionX = 160 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            printString = Integer.toString(umuMP);
            if (battleArmor.hasDWP() || battleArmor.isBurdened()) {
                printString = "[" + printString + "]";
            }
            g2d.drawString(printString, positionX, 131.3f + currentMargin);
        }

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = battleArmor.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 230, 209.5f + currentMargin);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", battleArmor.calculateBattleValue(true, true)) + "/" + String.format("%1$,d", battleArmor.calculateBattleValue(true, true, true)), 245, 209.5f + currentMargin);
        }
        if (battleArmor.canDoMechanizedBA()) {

            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/Mechanized"+squadNumber+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

        if (UnitUtil.canSwarm(battleArmor)) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/Swarm"+squadNumber+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

        if (UnitUtil.canLegAttack(battleArmor)) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/Leg"+squadNumber+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

        if (battleArmor.countWorkingMisc(MiscType.F_AP_MOUNT) > 0) {
            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/BA/AP"+squadNumber+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(battleArmor.getCost(true)) +
        // " C-bills", 235, 206 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelperBattleArmor.printBattleArmorWeaponsNEquipment(battleArmor, g2d, currentMargin);
    }
}