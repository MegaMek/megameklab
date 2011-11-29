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
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.BattleArmor;
import megamek.common.EntityMovementMode;
import megamek.common.MiscType;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperBattleArmor;
import megameklab.com.util.UnitUtil;

public class PrintBattleArmor implements Printable {

    private BattleArmor battleArmor = null;
    private ArrayList<BattleArmor> battleArmorList;
    private int pageMarginBase = 131; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;
    private boolean isAdvanced = false;
    PrinterJob masterPrintJob;

    public PrintBattleArmor(ArrayList<BattleArmor> list, PrinterJob masterPrintJob) {
        battleArmorList = list;
        this.masterPrintJob = masterPrintJob;

        /*
         * if (awtImage != null) { System.out.println("Width: " +
         * awtImage.getWidth(null)); System.out.println("Height: " +
         * awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex != 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        isAdvanced = false;
        currentMargin = 0;
        Image baSquad = ImageHelperBattleArmor.getBASquad();
        int x = 18;
        int y = 78;
        g2d.drawImage(ImageHelper.getRecordSheet(battleArmor, false), 18, 18, 558, 738, null);
        g2d.drawImage(ImageHelper.getFluffImage(battleArmor, ImageHelper.imageBattleArmor), 410, 23, 25, 45, null);

        int stop = Math.min(5, battleArmorList.size() - currentPosition);
        for (int pos = 0; pos < stop; pos++) {
            battleArmor = battleArmorList.get(pos + currentPosition);
            g2d.drawImage(baSquad, x, y, 365, 136, null);
            g2d.drawImage(ImageHelperBattleArmor.getBASquadNumber(pos), 197, 84 + currentMargin, 10, 9, null);
            printBattleArmorData(g2d);
            printSquadTroopers(g2d);
            printWeaponsNEquipment(g2d);

            y += pageMarginBase;
            currentMargin += pageMarginBase;
        }
        System.gc();
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("2011", 62.5f, 744.5f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }

    private void printBattleArmorAbilities(Graphics2D g2d) {

        Image checkBox = ImageHelperBattleArmor.getBACheckBox();

        if (battleArmor.canDoMechanizedBA()) {
            g2d.drawImage(checkBox, 71, 189 + currentMargin, 12, 11, null);
        }

        if (UnitUtil.canSwarm(battleArmor)) {
            g2d.drawImage(checkBox, 119, 189 + currentMargin, 12, 11, null);
        }

        if (UnitUtil.canLegAttack(battleArmor)) {
            g2d.drawImage(checkBox, 158, 189 + currentMargin, 12, 11, null);
        }

        if (battleArmor.countWorkingMisc(MiscType.F_AP_MOUNT) > 0) {
            g2d.drawImage(checkBox, 195, 189 + currentMargin, 12, 11, null);
        }
    }

    private void printBattleArmorData(Graphics2D g2d) {
        String BattleArmorName = battleArmor.getChassis() + " " + battleArmor.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, BattleArmorName, true, 85, 10.0f));
        g2d.drawString(BattleArmorName, 47, 108 + currentMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((battleArmor.getSource() != null) && (battleArmor.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 7.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 133, 108 + currentMargin);

            font = UnitUtil.getNewFont(g2d, battleArmor.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(battleArmor.getSource(), 147, 108 + currentMargin);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 133, 108 + currentMargin);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format(" %1$s", battleArmor.getYear()), 151, 108 + currentMargin);

        }

        font = UnitUtil.deriveFont(true, 12.0f);
        g2d.setFont(font);

        if (!isAdvanced && ((battleArmor.getTechLevel() == TechConstants.T_IS_ADVANCED) || (battleArmor.getTechLevel() == TechConstants.T_CLAN_ADVANCED))) {
            g2d.drawString("(Advanced)", 470, 66);
            isAdvanced = true;
        } else if (!isAdvanced && ((battleArmor.getTechLevel() == TechConstants.T_IS_EXPERIMENTAL) || (battleArmor.getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL))) {
            g2d.drawString("(Experimental)", 466, 66);
            isAdvanced = true;
        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);
        String groundMP = Integer.toString(battleArmor.getWalkMP(true, true, false, false, false));
        if (battleArmor.hasDWP()) {
            groundMP = groundMP + " [" + Integer.toString(battleArmor.getWalkMP(true, true, false, true, false)) + "]";
        }
        g2d.drawString(groundMP, 79, 130 + currentMargin);
        int secondaryMP = battleArmor.getJumpMP(true, true, true);
        if (battleArmor.getMovementMode() == EntityMovementMode.INF_UMU) {
            secondaryMP = battleArmor.getRunMP(true, true, true);

        }
        if (secondaryMP > 0) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = String.format("%1$s: ", battleArmor.getMovementModeAsString());

            if (battleArmor.getMovementMode() == EntityMovementMode.INF_LEG) {
                movment = "Jump:";
            }

            g2d.drawString(movment, 133, 130 + currentMargin);

            float positionY = 133 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            String printString = Integer.toString(secondaryMP);
            if (battleArmor.hasDWP() || battleArmor.isBurdened()) {
                printString = "[" + printString + "]";
            }
            g2d.drawString(printString, positionY, 130 + currentMargin);
        }
        printBattleArmorAbilities(g2d);

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
            g2d.drawString("BV: ", 35, 346.2f);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", battleArmor.calculateBattleValue(true, true)) + "/" + String.format("%1$,d", battleArmor.calculateBattleValue(true, true, true)), 150, 206 + currentMargin);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(battleArmor.getCost(true)) +
        // " C-bills", 235, 206 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelperBattleArmor.printBattleArmorWeaponsNEquipment(battleArmor, g2d, currentMargin);
    }

    private void printSquadTroopers(Graphics2D g2d) {
        int lineFeed = (18 * 6) / battleArmor.getNumberActiverTroopers();
        int x = 215;
        int y = 90 + currentMargin;
        for (int pos = 1; pos <= battleArmor.getNumberActiverTroopers(); pos++) {
            Image trooper = ImageHelperBattleArmor.getBATrooper(pos);
            g2d.drawImage(trooper, x, y, 158, 18, null);
            g2d.drawImage(ImageHelper.getFluffImage(battleArmor, ImageHelper.imageBattleArmor), x + 10, y + 2, 10, 14, null);
            ImageHelperBattleArmor.drawBAISPip(g2d, x + 23, y + 6);
            float pipX = x + 31;
            float pipY = y + 12.5f;
            for (int armor = 0; armor < battleArmor.getOArmor(pos); armor++) {
                ImageHelperBattleArmor.drawBAArmorPip(g2d, pipX, pipY);
                pipX += 7;
            }
            y += lineFeed;
        }
    }

    public void print() {

        try {
            for (; currentPosition < battleArmorList.size(); currentPosition += 5) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                battleArmor = battleArmorList.get(currentPosition);
                pj.setJobName(battleArmor.getChassis() + " " + battleArmor.getModel());

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
}