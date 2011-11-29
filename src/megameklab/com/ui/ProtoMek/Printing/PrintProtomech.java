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
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.Protomech;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperBattleArmor;
import megameklab.com.util.ImageHelperProto;
import megameklab.com.util.UnitUtil;

public class PrintProtomech implements Printable {

    private Protomech protoMech = null;
    private ArrayList<Protomech> protoMechList;
    private int pageMarginBase = 131; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;
    PrinterJob masterPrintJob;

    public PrintProtomech(ArrayList<Protomech> list, PrinterJob masterPrintJob) {
        protoMechList = list;
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

        currentMargin = 0;
        Image singleProtoMech = ImageHelperProto.getProtoMech(1);
        int x = 18;
        int y = 78;
        g2d.drawImage(ImageHelper.getRecordSheet(protoMech, false), 18, 18, 558, 738, null);
        g2d.drawImage(ImageHelper.getFluffImage(protoMech, ImageHelper.imageProto), 410, 23, 35, 45, null);

        int stop = Math.min(5, protoMechList.size() - currentPosition);

        for (int pos = 0; pos < stop; pos++) {

            protoMech = protoMechList.get(pos + currentPosition);

            if (pos == 1) {
                singleProtoMech = ImageHelperProto.getProtoMech(2);
            }

            g2d.drawImage(singleProtoMech, x, y, 558, 136, null);
            g2d.drawImage(ImageHelperBattleArmor.getBASquadNumber(pos), 100, 86 + currentMargin, 10, 9, null);
            printProtomechData(g2d);
            printWeaponsNEquipment(g2d);
            drawArmor(g2d);

            y += pageMarginBase;
            currentMargin += pageMarginBase;

            if (pos == 4) {
                g2d.drawImage(ImageHelperProto.getProtoLogo(), 22, 728, 60, 26, null);
            }
        }
        System.gc();
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("2011", 100.5f, 745f);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void drawArmor(Graphics2D g2d) {

        drawMainGunArmor(g2d);
        drawLAArmor(g2d);
        drawRAArmor(g2d);
        drawHeadArmor(g2d);
        drawLegsArmor(g2d);
        drawBodyArmor(g2d);

        drawMainGunIS(g2d);
        drawHeadIS(g2d);
        drawLAIS(g2d);
        drawRAIS(g2d);
        drawLegsIS(g2d);
        drawBodyIS(g2d);

    }

    private void drawMainGunArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_MAINGUN);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 511, 103 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 508, 106 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 513.5f, 106 + currentMargin, 4);
        }
    }

    private void drawMainGunIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_MAINGUN) >= 1) {
            ImageHelperProto.drawProtoISPip(g2d, 510, 105 + currentMargin);
        }
    }

    private void drawHeadArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_HEAD);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 523, 114 + currentMargin, 4);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 520, 115 + currentMargin, 4);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526, 115 + currentMargin, 4);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 523, 124 + currentMargin, 4);
        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 521, 122 + currentMargin, 4);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 525, 122 + currentMargin, 4);
        }
    }

    private void drawHeadIS(Graphics2D g2d) {
        ImageHelperProto.drawProtoISPip(g2d, 520, 114 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 524, 114 + currentMargin);
    }

    private void drawLAArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_LARM);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 497, 123 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 492, 128 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 502, 128 + currentMargin, 4);
        }

        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 497, 133 + currentMargin, 4);
        }
    }

    private void drawLAIS(Graphics2D g2d) {
        ImageHelperProto.drawProtoISPip(g2d, 487, 130 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 490, 133 + currentMargin);
    }

    private void drawRAArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_LARM);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 547, 123 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 542, 128 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 552, 128 + currentMargin, 4);
        }

        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 547, 133 + currentMargin, 4);
        }
    }

    private void drawRAIS(Graphics2D g2d) {
        ImageHelperProto.drawProtoISPip(g2d, 557, 130 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 552, 133 + currentMargin);
    }

    private void drawLegsArmor(Graphics2D g2d) {

        int armorTotal = protoMech.getArmor(Protomech.LOC_LEG);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 509, 180 + currentMargin, 4.0f);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 538, 180 + currentMargin, 4.0f);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 503, 180 + currentMargin, 4.0f);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 545, 180 + currentMargin, 4.0f);
        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 509, 190 + currentMargin, 4.0f);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 538, 190 + currentMargin, 4.0f);
        }
        if (armorTotal >= 7) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 503, 190 + currentMargin, 4.0f);
        }
        if (armorTotal >= 8) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 545, 190 + currentMargin, 4.0f);
        }
        if (armorTotal >= 9) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 506, 196 + currentMargin, 4.0f);
        }
        if (armorTotal >= 10) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 541, 196 + currentMargin, 4.0f);
        }
    }

    private void drawLegsIS(Graphics2D g2d) {
        ImageHelperProto.drawProtoISPip(g2d, 522, 157 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 532, 157 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 512, 157 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 507, 167 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 537, 167 + currentMargin);
    }

    private void drawBodyArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_TORSO);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 521, 134 + currentMargin, 4.0f);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526, 134 + currentMargin, 4.0f);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 521, 138 + currentMargin, 4.0f);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526, 138 + currentMargin, 4.0f);

        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 533, 128 + currentMargin, 4.0f);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 513, 128 + currentMargin, 4.0f);

        }
        if (armorTotal >= 7) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 531, 132 + currentMargin, 4.0f);
        }
        if (armorTotal >= 8) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 515, 132 + currentMargin, 4.0f);
        }
        if (armorTotal >= 9) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 535, 132 + currentMargin, 4.0f);
        }
        if (armorTotal >= 10) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 511, 132 + currentMargin, 4.0f);

        }
        if (armorTotal >= 11) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 531, 136 + currentMargin, 4.0f);
        }
        if (armorTotal >= 12) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 515, 136 + currentMargin, 4.0f);
        }
        if (armorTotal >= 13) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 535, 136 + currentMargin, 4.0f);
        }
        if (armorTotal >= 14) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 511, 136 + currentMargin, 4.0f);

        }
        if (armorTotal >= 15) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 531, 140 + currentMargin, 4.0f);
        }
        if (armorTotal >= 16) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 515, 140 + currentMargin, 4.0f);
        }
        if (armorTotal >= 17) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 535, 140 + currentMargin, 4.0f);
        }
        if (armorTotal >= 18) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 511, 140 + currentMargin, 4.0f);
        }
    }

    private void drawBodyIS(Graphics2D g2d) {
        ImageHelperProto.drawProtoISPip(g2d, 522, 139 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 526, 139 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 530, 139 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 534, 139 + currentMargin);

        ImageHelperProto.drawProtoISPip(g2d, 518, 139 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 514, 139 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 510, 139 + currentMargin);

        ImageHelperProto.drawProtoISPip(g2d, 524, 142 + currentMargin);
        ImageHelperProto.drawProtoISPip(g2d, 520, 142 + currentMargin);

    }

    private void printProtomechData(Graphics2D g2d) {
        String ProtomechName = protoMech.getChassis() + " " + protoMech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, ProtomechName, true, 88, 10.0f));
        g2d.drawString(ProtomechName, 47, 110 + currentMargin);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        String weight = Integer.toString((int) protoMech.getWeight());

        if (protoMech.getTechLevel() == TechConstants.T_CLAN_ADVANCED) {
            weight += "     (Advanced)";
        } else if (protoMech.getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL) {
            weight += "     (Experimental)";
        } else if (protoMech.getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL) {
            weight += "     (Unofficial)";
        }

        g2d.drawString(weight, 47, 119 + currentMargin);

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
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = protoMech.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 135, 203 + currentMargin);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", protoMech.calculateBattleValue(true, true)), 150, 203 + currentMargin);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(protoMech.getCost(true)) +
        // " C-bills", 147, 203 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelperProto.printProtomechWeaponsNEquipment(protoMech, g2d, currentMargin);
    }

    public void print() {

        try {
            for (; currentPosition < protoMechList.size(); currentPosition += 5) {
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

                protoMech = protoMechList.get(currentPosition);
                pj.setJobName(protoMech.getChassis() + " " + protoMech.getModel());

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