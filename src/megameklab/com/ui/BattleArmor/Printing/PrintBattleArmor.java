/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.BattleArmor;
import megamek.common.MiscType;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintBattleArmor implements Printable {

    private BattleArmor battleArmor = null;
    private ArrayList<BattleArmor> battleArmorList;
    private int pageMarginBase = 131; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;

    public PrintBattleArmor(ArrayList<BattleArmor> list) {
        battleArmorList = list;

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

        currentMargin = 0;
        Image baSquad = ImageHelper.getBASquad();
        int x = 18;
        int y = 78;
        g2d.drawImage(ImageHelper.getRecordSheet(battleArmor, false), 18, 18, 558, 738, null);
        g2d.drawImage(ImageHelper.getFluffImage(battleArmor, ImageHelper.imageBA), 410, 23, 25, 45, null);

        int stop = Math.min(5, battleArmorList.size() - currentPosition);
        for (int pos = 0; pos < stop; pos++) {
            battleArmor = battleArmorList.get(pos + currentPosition);
            g2d.drawImage(baSquad, x, y, 365, 136, null);
            g2d.drawImage(ImageHelper.getBASquadNumber(pos), 197, 84 + currentMargin, 10, 9, null);
            printBattleArmorData(g2d);
            printSquadTroopers(g2d);
            printWeaponsNEquipment(g2d);

            y += pageMarginBase;
            currentMargin += pageMarginBase;
        }
        System.gc();
        Font font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 62.5f, 745f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }

    private void printBattleArmorAbilities(Graphics2D g2d) {

        Image checkBox = ImageHelper.getBACheckBox();

        if (battleArmor.canDoMechanizedBA()) {
            g2d.drawImage(checkBox, 72, 189 + currentMargin, 11, 11, null);
        }

        if (UnitUtil.canSwarm(battleArmor)) {
            g2d.drawImage(checkBox, 119, 189 + currentMargin, 11, 11, null);
        }

        if (UnitUtil.canLegAttack(battleArmor)) {
            g2d.drawImage(checkBox, 159, 189 + currentMargin, 11, 11, null);
        }

        if (battleArmor.countWorkingMisc(MiscType.F_AP_MOUNT) > 0) {
            g2d.drawImage(checkBox, 195, 189 + currentMargin, 11, 11, null);
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

            g2d.drawString(String.format("%1$s", battleArmor.getYear()), 151, 108 + currentMargin);

        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(battleArmor.getRunMP()), 79, 130 + currentMargin);

        if (battleArmor.getJumpMP() > 0) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = String.format("%1$s: ", battleArmor.getMovementModeAsString());
            g2d.drawString(movment, 133, 130 + currentMargin);

            float positionY = 133 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            g2d.drawString(Integer.toString(battleArmor.getJumpMP()), positionY, 130 + currentMargin);
        }
        printBattleArmorAbilities(g2d);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(battleArmor.calculateBattleValue(true, true)) + "/" + myFormatter.format(battleArmor.calculateBattleValue(true, true, true)), 330, 206 + currentMargin);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(battleArmor.getCost(true)) + " C-bills", 235, 206 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelper.printBattleArmorWeaponsNEquipment(battleArmor, g2d, currentMargin);
    }

    private void printSquadTroopers(Graphics2D g2d) {
        int lineFeed = (18 * 6) / battleArmor.getNumberActiverTroopers();
        int x = 215;
        int y = 90 + currentMargin;
        for (int pos = 1; pos <= battleArmor.getNumberActiverTroopers(); pos++) {
            Image trooper = ImageHelper.getBATrooper(pos);
            g2d.drawImage(trooper, x, y, 158, 18, null);
            g2d.drawImage(ImageHelper.getFluffImage(battleArmor, ImageHelper.imageBA), x + 10, y + 2, 10, 14, null);
            ImageHelper.drawBAISPip(g2d, x + 23, y + 6);
            float pipX = x + 31;
            float pipY = y + 12.5f;
            for (int armor = 0; armor < battleArmor.getOArmor(pos); armor++) {
                ImageHelper.drawBAArmorPip(g2d, pipX, pipY);
                pipX += 7;
            }
            y += lineFeed;
        }
    }

    public void print() {

        try {
            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                for (; currentPosition < battleArmorList.size(); currentPosition += 5) {

                    battleArmor = battleArmorList.get(currentPosition);
                    pj.setJobName(battleArmor.getChassis() + " " + battleArmor.getModel());

                    try {
                        pj.print(aset);
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
}