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

package megameklab.com.ui.ProtoMek.Printing;

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

import megamek.common.Protomech;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintProtomech implements Printable {

    private Protomech protoMech = null;
    private ArrayList<Protomech> protoMechList;
    private int pageMarginBase = 131; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;

    public PrintProtomech(ArrayList<Protomech> list) {
        protoMechList = list;

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
        Image singleProtoMech = ImageHelper.getProtoMech();
        int x = 18;
        int y = 78;
        g2d.drawImage(ImageHelper.getRecordSheet(protoMech, false), 18, 18, 558, 738, null);
        g2d.drawImage(ImageHelper.getFluffImage(protoMech, ImageHelper.imageProto), 410, 23, 35, 45, null);

        int stop = Math.min(5, protoMechList.size() - currentPosition);
        for (int pos = 0; pos < stop; pos++) {
            protoMech = protoMechList.get(pos + currentPosition);
            g2d.drawImage(singleProtoMech, x, y, 558, 136, null);
            g2d.drawImage(ImageHelper.getBASquadNumber(pos), 100, 86 + currentMargin, 10, 9, null);
            printProtomechData(g2d);
            printWeaponsNEquipment(g2d);
            drawArmor(g2d);

            y += pageMarginBase;
            currentMargin += pageMarginBase;
        }
        System.gc();
        Font font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2009", 118f, 746.5f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void drawArmor(Graphics2D g2d) {

        drawMainGunArmor(g2d);
        drawMainGunIS(g2d);

        drawHeadArmor(g2d);
        drawHeadIS(g2d);
    }

    private void drawMainGunArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_MAINGUN);

        if (armorTotal >= 1) {
            ImageHelper.drawBAArmorPip(g2d, 511, 103 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelper.drawBAArmorPip(g2d, 508, 106 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelper.drawBAArmorPip(g2d, 513.5f, 106 + currentMargin, 4);
        }
    }

    private void drawMainGunIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_MAINGUN) >= 1) {
            ImageHelper.drawProtoISPip(g2d, 510, 105 + currentMargin);
        }
    }

    private void drawHeadArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_HEAD);

        if (armorTotal >= 1) {
            ImageHelper.drawBAArmorPip(g2d, 523, 114 + currentMargin, 4);
        }
        if (armorTotal >= 2) {
            ImageHelper.drawBAArmorPip(g2d, 520, 115 + currentMargin, 4);
        }
        if (armorTotal >= 3) {
            ImageHelper.drawBAArmorPip(g2d, 526, 115 + currentMargin, 4);
        }
        if (armorTotal >= 4) {
            ImageHelper.drawBAArmorPip(g2d, 523, 124 + currentMargin, 4);
        }
        if (armorTotal >= 5) {
            ImageHelper.drawBAArmorPip(g2d, 521, 122 + currentMargin, 4);
        }
        if (armorTotal >= 6) {
            ImageHelper.drawBAArmorPip(g2d, 525, 122 + currentMargin, 4);
        }
    }

    private void drawHeadIS(Graphics2D g2d) {
        ImageHelper.drawProtoISPip(g2d, 520, 114 + currentMargin);
        ImageHelper.drawProtoISPip(g2d, 524, 114 + currentMargin);
    }

    private void printProtomechData(Graphics2D g2d) {
        String ProtomechName = protoMech.getChassis() + " " + protoMech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, ProtomechName, true, 88, 10.0f));
        g2d.drawString(ProtomechName, 47, 110 + currentMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString((int) protoMech.getWeight()), 47, 119 + currentMargin);

        if ((protoMech.getSource() != null) && (protoMech.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 7.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 33, 127 + currentMargin);

            font = UnitUtil.getNewFont(g2d, protoMech.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(protoMech.getSource(), 47, 127 + currentMargin);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 27, 127 + currentMargin);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", protoMech.getYear()), 47, 127 + currentMargin);

        }

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if (protoMech.hasMyomerBooster()) {
            int run = (int) Math.ceil(protoMech.getWalkMP() * 1.5);
            if (protoMech.getJumpMP() > 0) {
                g2d.drawString(String.format("%1$s / %2$s [%3$s] / %4$s", protoMech.getWalkMP(), run, protoMech.getRunMP(), protoMech.getJumpMP()), 44, 157 + currentMargin);
            } else {
                g2d.drawString(String.format("%1$s / %2$s [%3$s]", protoMech.getWalkMP(), run, protoMech.getRunMP()), 44, 157 + currentMargin);
            }
        } else {
            if (protoMech.getJumpMP() > 0) {
                g2d.drawString(String.format("%1$s / %2$s / %3$s", protoMech.getWalkMP(), protoMech.getRunMP(), protoMech.getJumpMP()), 44, 157 + currentMargin);
            } else {
                g2d.drawString(String.format("%1$s / %2$s", protoMech.getWalkMP(), protoMech.getRunMP()), 44, 157 + currentMargin);
            }
        }

        // printProtomechAbilities(g2d);

        // Cost/BV
        DecimalFormat myFormatter = new DecimalFormat("#,###");
        g2d.drawString(myFormatter.format(protoMech.calculateBattleValue(true, true)), 268, 203 + currentMargin);

        myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(protoMech.getCost(true)) + " C-bills", 147, 203 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelper.printProtomechWeaponsNEquipment(protoMech, g2d, currentMargin);
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

                for (; currentPosition < protoMechList.size(); currentPosition += 5) {

                    protoMech = protoMechList.get(currentPosition);
                    pj.setJobName(protoMech.getChassis() + " " + protoMech.getModel());

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