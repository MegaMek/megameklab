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

import java.awt.Color;
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

import megamek.common.Protomech;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperBattleArmor;
import megameklab.com.util.ImageHelperProto;
import megameklab.com.util.UnitUtil;

public class PrintProtomech implements Printable {

    private Protomech protoMech = null;
    private List<Protomech> protoMechList;
    private int pageMarginBase = 130; // How far down the text should be printed
    // for a second vehicle.
    private int currentPosition = 0;
    private int currentMargin = 0;

    public PrintProtomech(List<Protomech> list) {
        protoMechList = list;
        if (list.size() > 0) {
            protoMech = list.get(0);
        }

        /*
         * if (awtImage != null) { System.out.println("Width: " +
         * awtImage.getWidth(null)); System.out.println("Height: " +
         * awtImage.getHeight(null)); }
         */
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if ((null == g2d) || (null == protoMech)) {
            return;
        }

        currentMargin = 0;
        // g2d.drawImage(ImageHelper.getRecordSheet(protoMech, false), 18, 18,
        // 558, 738, null);

        int stop = Math.min(5, protoMechList.size() - currentPosition);

        for (int pos = 1; pos <= stop; pos++) {

            protoMech = protoMechList.get((pos + currentPosition) - 1);

            try {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/ProtomechTemplate"+pos+".svg")).render(g2d);
            } catch (SVGException e) {
                e.printStackTrace();
            }

            g2d.setColor(Color.BLACK);
            printProtomechData(g2d);
            printWeaponsNEquipment(g2d);
            drawArmor(g2d);

            currentMargin += pageMarginBase;

        }
        System.gc();
        Font font = UnitUtil.deriveFont(true, 6.5f);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 119f, 759.5f);
        g2d.drawImage(
                ImageHelper.getFluffImage(protoMech, ImageHelper.imageProto),
                410, 23, 35, 45, null);
        g2d.scale(pageFormat.getImageableWidth(),
                pageFormat.getImageableHeight());

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
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 522,
                    102 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 519,
                    105 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 524.5f,
                    105 + currentMargin, 4);
        }
    }

    private void drawMainGunIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_MAINGUN) >= 1) {
            ImageHelperProto.drawProtoISPip(g2d, 521, 100 + currentMargin);
        }
    }

    private void drawHeadArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_HEAD);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 534,
                    113 + currentMargin, 4);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 531,
                    114 + currentMargin, 4);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 537,
                    114 + currentMargin, 4);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 534,
                    123 + currentMargin, 4);
        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 532,
                    121 + currentMargin, 4);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 536,
                    121 + currentMargin, 4);
        }
    }

    private void drawHeadIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_HEAD) == 2) {
            ImageHelperProto.drawProtoISPip(g2d, 531, 113 + currentMargin);
        }
        ImageHelperProto.drawProtoISPip(g2d, 535, 113 + currentMargin);
    }

    private void drawLAArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_LARM);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 508,
                    122 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 503,
                    127 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 513,
                    127 + currentMargin, 4);
        }

        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 508,
                    132 + currentMargin, 4);
        }
    }

    private void drawLAIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_LARM) > 1) {
            ImageHelperProto.drawProtoISPip(g2d, 498, 129 + currentMargin);
        }
        ImageHelperProto.drawProtoISPip(g2d, 501, 132 + currentMargin);
    }

    private void drawRAArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_LARM);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 558,
                    122 + currentMargin, 4);
        }

        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 553,
                    127 + currentMargin, 4);
        }

        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 563,
                    127 + currentMargin, 4);
        }

        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 558,
                    132 + currentMargin, 4);
        }
    }

    private void drawRAIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_RARM) > 1) {
            ImageHelperProto.drawProtoISPip(g2d, 568, 129 + currentMargin);
        }
        ImageHelperProto.drawProtoISPip(g2d, 563, 132 + currentMargin);
    }

    private void drawLegsArmor(Graphics2D g2d) {

        int armorTotal = protoMech.getArmor(Protomech.LOC_LEG);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 520,
                    179 + currentMargin, 4.0f);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 549,
                    179 + currentMargin, 4.0f);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 514,
                    179 + currentMargin, 4.0f);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 556,
                    179 + currentMargin, 4.0f);
        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 520,
                    189 + currentMargin, 4.0f);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 549,
                    189 + currentMargin, 4.0f);
        }
        if (armorTotal >= 7) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 514,
                    189 + currentMargin, 4.0f);
        }
        if (armorTotal >= 8) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 556,
                    189 + currentMargin, 4.0f);
        }
        if (armorTotal >= 9) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 517,
                    195 + currentMargin, 4.0f);
        }
        if (armorTotal >= 10) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 552,
                    195 + currentMargin, 4.0f);
        }
    }

    private void drawLegsIS(Graphics2D g2d) {
        if (protoMech.getInternal(Protomech.LOC_LEG) > 4) {
            ImageHelperProto.drawProtoISPip(g2d, 533, 156 + currentMargin);
        }
        if (protoMech.getInternal(Protomech.LOC_LEG) > 3) {
            ImageHelperProto.drawProtoISPip(g2d, 543, 156 + currentMargin);
        }
        if (protoMech.getInternal(Protomech.LOC_LEG) > 2) {
            ImageHelperProto.drawProtoISPip(g2d, 523, 156 + currentMargin);
        }
        if (protoMech.getInternal(Protomech.LOC_LEG) > 1) {
            ImageHelperProto.drawProtoISPip(g2d, 518, 166 + currentMargin);
        }
        ImageHelperProto.drawProtoISPip(g2d, 548, 166 + currentMargin);
    }

    private void drawBodyArmor(Graphics2D g2d) {
        int armorTotal = protoMech.getArmor(Protomech.LOC_TORSO);

        if (armorTotal >= 1) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 532,
                    133 + currentMargin, 4.0f);
        }
        if (armorTotal >= 2) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 537,
                    133 + currentMargin, 4.0f);
        }
        if (armorTotal >= 3) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 532,
                    137 + currentMargin, 4.0f);
        }
        if (armorTotal >= 4) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 537,
                    137 + currentMargin, 4.0f);

        }
        if (armorTotal >= 5) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 544,
                    129 + currentMargin, 4.0f);
        }
        if (armorTotal >= 6) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 524,
                    129 + currentMargin, 4.0f);

        }
        if (armorTotal >= 7) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 542,
                    131 + currentMargin, 4.0f);
        }
        if (armorTotal >= 8) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526,
                    131 + currentMargin, 4.0f);
        }
        if (armorTotal >= 9) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 546,
                    131 + currentMargin, 4.0f);
        }
        if (armorTotal >= 10) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 522,
                    131 + currentMargin, 4.0f);

        }
        if (armorTotal >= 11) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 542,
                    135 + currentMargin, 4.0f);
        }
        if (armorTotal >= 12) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526,
                    135 + currentMargin, 4.0f);
        }
        if (armorTotal >= 13) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 546,
                    135 + currentMargin, 4.0f);
        }
        if (armorTotal >= 14) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 522,
                    135 + currentMargin, 4.0f);

        }
        if (armorTotal >= 15) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 542,
                    139 + currentMargin, 4.0f);
        }
        if (armorTotal >= 16) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 526,
                    139 + currentMargin, 4.0f);
        }
        if (armorTotal >= 17) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 546,
                    139 + currentMargin, 4.0f);
        }
        if (armorTotal >= 18) {
            ImageHelperBattleArmor.drawBAArmorPip(g2d, 522,
                    139 + currentMargin, 4.0f);
        }
    }

    private void drawBodyIS(Graphics2D g2d) {
        int totalIS = protoMech.getInternal(Protomech.LOC_TORSO);
        if (totalIS == 9) {
            ImageHelperProto.drawProtoISPip(g2d, 533, 138 + currentMargin);
        }
        if (totalIS >= 8) {
            ImageHelperProto.drawProtoISPip(g2d, 537, 138 + currentMargin);
        }
        if (totalIS >= 7) {
            ImageHelperProto.drawProtoISPip(g2d, 541, 138 + currentMargin);
        }
        if (totalIS >= 6) {
            ImageHelperProto.drawProtoISPip(g2d, 545, 138 + currentMargin);
        }
        if (totalIS >= 5) {
            ImageHelperProto.drawProtoISPip(g2d, 529, 138 + currentMargin);
        }
        if (totalIS >= 4) {
            ImageHelperProto.drawProtoISPip(g2d, 525, 138 + currentMargin);
        }
        if (totalIS >= 3) {
            ImageHelperProto.drawProtoISPip(g2d, 521, 138 + currentMargin);
        }
        if (totalIS >= 2) {
            ImageHelperProto.drawProtoISPip(g2d, 535, 141 + currentMargin);
            ImageHelperProto.drawProtoISPip(g2d, 531, 141 + currentMargin);
        }

    }

    private void printProtomechData(Graphics2D g2d) {
        String protomechName = protoMech.getChassis() + " "
                + protoMech.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, protomechName, true, 88, 10.0f));
        g2d.drawString(protomechName, 55, 107 + currentMargin);

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

        g2d.drawString(weight, 55, 119 + currentMargin);

        /*
         * if ((protoMech.getSource() != null) &&
         * (protoMech.getSource().trim().length() > 0)) { String sourceFluff =
         * "Era: "; font = UnitUtil.deriveFont(true, 7.0f); g2d.setFont(font);
         *
         * g2d.drawString(sourceFluff, 33, 127 + currentMargin);
         *
         * font = UnitUtil.getNewFont(g2d, protoMech.getSource(), false, 51,
         * 8.0f); g2d.setFont(font);
         *
         * g2d.drawString(protoMech.getSource(), 47, 127 + currentMargin);
         *
         * } else { String yearFluff = "Year: "; font =
         * UnitUtil.deriveFont(true, 8.0f); g2d.setFont(font);
         *
         * g2d.drawString(yearFluff, 27, 127 + currentMargin);
         *
         * font = UnitUtil.deriveFont(8.0f); g2d.setFont(font);
         *
         * g2d.drawString(String.format("%1$s", protoMech.getYear()), 47, 127 +
         * currentMargin);
         *
         * }
         */

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if (protoMech.hasMyomerBooster()) {
            int run = (int) Math.ceil(protoMech.getWalkMP() * 1.5);
            if (protoMech.getJumpMP() > 0) {
                g2d.drawString(
                        String.format("%1$s / %2$s [%3$s] / %4$s",
                                protoMech.getWalkMP(), run,
                                protoMech.getRunMP(), protoMech.getJumpMP()),
                        55, 157 + currentMargin);
            } else {
                g2d.drawString(
                        String.format("%1$s / %2$s [%3$s]",
                                protoMech.getWalkMP(), run,
                                protoMech.getRunMP()), 55, 157 + currentMargin);
            }
        } else {
            if (protoMech.getJumpMP() > 0) {
                g2d.drawString(String.format("%1$s / %2$s / %3$s",
                        protoMech.getWalkMP(), protoMech.getRunMP(),
                        protoMech.getJumpMP()), 55, 157 + currentMargin);
            } else {
                g2d.drawString(String.format("%1$s / %2$s",
                        protoMech.getWalkMP(), protoMech.getRunMP()), 55,
                        157 + currentMargin);
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
            g2d.drawString(
                    String.format("%1$,d",
                            protoMech.calculateBattleValue(true, true)), 150,
                    203 + currentMargin);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(myFormatter.format(protoMech.getCost(true)) +
        // " C-bills", 147, 203 + currentMargin);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {
        ImageHelperProto.printProtomechWeaponsNEquipment(protoMech, g2d,
                currentMargin);
    }
}