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

package megameklab.com.ui.Aero.Printing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Calendar;
import java.util.Vector;

import megamek.common.Aero;
import megamek.common.Crew;
import megamek.common.SmallCraft;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperAero;
import megameklab.com.util.UnitUtil;

public class PrintSmallCraftAerodyne implements Printable {

    private SmallCraft smallCraft = null;

    public PrintSmallCraftAerodyne(SmallCraft smallCraft) {
        this.smallCraft = smallCraft;

    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        System.gc();

        g2d.drawImage(ImageHelper.getRecordSheet(smallCraft), 18, 18, 558, 738, Color.BLACK, null);
        printSmallCraftAerodyneImage(g2d, ImageHelper.getFluffImage(smallCraft, ImageHelper.imageAero));

        printSmallCraftAerodyneData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printHeatSinks(g2d);

        // Armor Pips
        printFrontArmor(g2d, smallCraft.getOArmor(Aero.LOC_NOSE));
        printLeftArmor(g2d, smallCraft.getOArmor(Aero.LOC_LWING));
        printRightArmor(g2d, smallCraft.getOArmor(Aero.LOC_RWING));
        printRearArmor(g2d, smallCraft.getOArmor(Aero.LOC_AFT));

        // Internal Pips
        printStruct(g2d, smallCraft.get0SI());

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printSmallCraftAerodyneData(Graphics2D g2d) {
        String smallCraftName = smallCraft.getChassis() + " " + smallCraft.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, smallCraftName, true, 180, 10.0f));
        g2d.drawString(smallCraftName, 49, 118);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((smallCraft.getCrew() != null) && !smallCraft.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = smallCraft.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(smallCraft.getWalkMP()), 99, 143);
        g2d.drawString(Integer.toString(smallCraft.getRunMP()), 99, 154);

        int tonnage = (int) Math.ceil(smallCraft.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 132.5f);

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        switch (smallCraft.getTechLevel()) {

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

        String techBase = "Inner Sphere";

        if (smallCraft.isMixedTech()) {
            if (smallCraft.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (smallCraft.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 143.5f);

        if ((smallCraft.getSource() != null) && (smallCraft.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, smallCraft.getSource(), false, 51, 8.0f);

            g2d.setFont(font);

            g2d.drawString(smallCraft.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", smallCraft.getYear()), 177, nextDataLine);

        }

        // g2d.drawString(Integer.toString(smallCraft.getYear()), 188, 155);

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = smallCraft.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 346.2f);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", smallCraft.calculateBattleValue(true, true)), 50, 346.2f);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills",
        // smallCraft.getCost(true)),
        // 52, 346.2f);

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 63f, 744.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", smallCraft.getThresh(Aero.LOC_NOSE), smallCraft.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 442, 93);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", smallCraft.getThresh(Aero.LOC_RWING), smallCraft.getArmor(Aero.LOC_RWING)), g2d.getFont(), 517, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", smallCraft.getThresh(Aero.LOC_LWING), smallCraft.getArmor(Aero.LOC_LWING)), g2d.getFont(), 270, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", smallCraft.getThresh(Aero.LOC_AFT), smallCraft.getArmor(Aero.LOC_AFT)), g2d.getFont(), 447, 487);

        g2d.drawString(String.format("%1$S", smallCraft.get0SI()), 390, 268);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (smallCraft.getHeatType() == Aero.HEAT_DOUBLE) {
            g2d.drawString(String.format("%1$s (%2$s)", smallCraft.getHeatSinks(), smallCraft.getHeatSinks() * 2), 502, 535);
            g2d.drawString("Double", 502, 543);
        } else {
            g2d.drawString(String.format("%1$s (%1$s)", smallCraft.getHeatSinks()), 502, 535);
            g2d.drawString("Single", 502, 543);
        }

        Dimension column = new Dimension(504, 552);
        Dimension pipShift = new Dimension(9, 9);

        for (int pos = 1; pos <= smallCraft.getHeatSinks(); pos++) {
            ImageHelper.drawHeatSinkPip(g2d, column.width, column.height);
            column.height += pipShift.height;

            if ((pos % 10) == 0) {
                column.height -= pipShift.height * 10;
                column.width += pipShift.width;
            }

        }

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperAero.printAeroWeaponsNEquipment(smallCraft, g2d);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 376f;
        float baseY = 108f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(186, 1);

        for (int lineCount = 1; lineCount <= 18; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if ((lineCount == 8)) {
                pipsPerLine += 8;
                baseX -= shiftX * 4;
            }
            if ((lineCount == 14)) {
                pipsPerLine += 4;
                baseX -= shiftX * 2;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperAero.printArmorPoints(g2d, pipPlotter, totalArmor, 186);

    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 358f;
        float baseY = 303f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 10;

        Vector<float[]> pipPlotter = new Vector<float[]>(200, 1);

        for (int lineCount = 1; lineCount <= 20; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperAero.printArmorPoints(g2d, pipPlotter, totalArmor, 200);

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 318f;
        float baseY = 237f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(205, 1);

        for (int lineCount = 1; lineCount <= 29; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 17) {
                pipsPerLine += 5;
                baseX -= shiftX * 5;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperAero.printArmorPoints(g2d, pipPlotter, totalArmor, 205);
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 434f;
        float baseY = 237f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 5;

        Vector<float[]> pipPlotter = new Vector<float[]>(205, 1);

        for (int lineCount = 1; lineCount <= 29; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 17) {
                pipsPerLine += 5;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperAero.printArmorPoints(g2d, pipPlotter, totalArmor, 205);

    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        int posY = 369;
        int posX = 269;
        int[] topColumn = new int[]
            { posY, posX };
        int[] pipShift = new int[]
            { 6, 6 };

        Vector<int[]> pipPlotter = new Vector<int[]>(132);
        for (int pos = 1; pos <= 32; pos++) {
            pipPlotter.add(new int[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 8) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = (int) Math.floor(32 / totalArmor);
        for (int pos = 0; pos < 32; pos += pipSpace) {
            ImageHelperAero.drawAeroISPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printSmallCraftAerodyneImage(Graphics2D g2d, Image img) {

        int width = Math.min(220, img.getWidth(null));
        int height = Math.min(130, img.getHeight(null));
        int drawingX = 18 + ((220 - width) / 2);
        int drawingY = 365 + ((130 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

}