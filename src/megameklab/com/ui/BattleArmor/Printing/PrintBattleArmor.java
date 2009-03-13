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

import megamek.common.BattleArmor;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintBattleArmor implements Printable {

    private BattleArmor BattleArmor = null;
    private ArrayList<BattleArmor> BattleArmorList;
    private int pageMarginBase = 131; // How far down the text should be printed for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;

    public PrintBattleArmor(ArrayList<BattleArmor> list) {
        BattleArmorList = list;

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
        g2d.drawImage(ImageHelper.getRecordSheet(BattleArmor, false), 18, 18, 558, 738, null);
        g2d.drawImage(ImageHelper.getFluffImage(BattleArmor, "BattleArmor"), 410, 23, 25, 45, null);

        int stop = Math.min(5, BattleArmorList.size() - currentPosition);
        for (int pos = 0; pos < stop; pos++) {
            BattleArmor = BattleArmorList.get(pos + currentPosition);
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
        g2d.drawString("2009", 105f, 744.5f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }

    private void printBattleArmorAbilities(Graphics2D g2d) {

        Image checkBox = ImageHelper.getBACheckBox();

        if (UnitUtil.canRide(BattleArmor)) {
            g2d.drawImage(checkBox, 72, 189 + currentMargin, 11, 11, null);
        }

        if (UnitUtil.canSwarm(BattleArmor)) {
            g2d.drawImage(checkBox, 119, 189 + currentMargin, 11, 11, null);
        }

        if (UnitUtil.canLegAttack(BattleArmor)) {
            g2d.drawImage(checkBox, 159, 189 + currentMargin, 11, 11, null);
        }

        if (UnitUtil.hasInfantryWeapons(BattleArmor)) {
            g2d.drawImage(checkBox, 195, 189 + currentMargin, 11, 11, null);
        }
    }

    private void printBattleArmorData(Graphics2D g2d) {
        String BattleArmorName = BattleArmor.getChassis().toUpperCase() + " " + BattleArmor.getModel().toUpperCase();

        g2d.setFont(UnitUtil.getNewFont(g2d, BattleArmorName, true, 180, 10.0f));
        g2d.drawString(BattleArmorName, 49, 108 + currentMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(BattleArmor.getRunMP()), 79, 130 + currentMargin);

        if (BattleArmor.getJumpMP() > 0) {
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);
            String movment = String.format("%1$s: ", BattleArmor.getMovementModeAsString());
            g2d.drawString(movment, 133, 130 + currentMargin);

            float positionY = 133 + ImageHelper.getStringWidth(g2d, movment, font);
            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);
            g2d.drawString(Integer.toString(BattleArmor.getJumpMP()), positionY, 130 + currentMargin);
        }
        printBattleArmorAbilities(g2d);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(BattleArmor.calculateBattleValue(true, true)), 330, 206 + currentMargin);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(BattleArmor.getCost()) + " C-bills", 235, 206 + currentMargin);

    }


    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelper.printBattleArmorWeaponsNEquipment(BattleArmor, g2d, currentMargin);
    }

    private void printSquadTroopers(Graphics2D g2d) {
        int lineFeed = (18 * 6) / BattleArmor.getNumberActiverTroopers();
        int x = 215;
        int y = 90 + currentMargin;
        for (int pos = 1; pos <= BattleArmor.getNumberActiverTroopers(); pos++) {
            Image trooper = ImageHelper.getBATrooper(pos);
            g2d.drawImage(trooper, x, y, 158, 18, null);
            g2d.drawImage(ImageHelper.getFluffImage(BattleArmor, "BattleArmor"), x + 10, y + 2, 10, 14, null);
            ImageHelper.drawBAISPip(g2d, x + 25, y + 6);
            float pipX = x + 33;
            float pipY = y + 12.5f;
            for (int armor = 0; armor < BattleArmor.getOArmor(pos); armor++) {
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
                // Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                for (; currentPosition < BattleArmorList.size(); currentPosition += 5) {

                    BattleArmor = BattleArmorList.get(currentPosition);
                    pj.setJobName(BattleArmor.getChassis() + " " + BattleArmor.getModel());

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
}