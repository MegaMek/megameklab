/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.com.ui.Dropship.Printing;

import java.awt.Color;
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
import megamek.common.Dropship;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperDropShip;
import megameklab.com.util.UnitUtil;

/**
 * @author Torren
 *
 */
public class PrintSpheroid implements Printable {

    private Dropship dropship = null;

    public PrintSpheroid(Dropship dropship) {
        this.dropship = dropship;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
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

        g2d.drawImage(ImageHelper.getRecordSheet(dropship), 18, 18, 558, 774,
                Color.BLACK, null);
        printDropshipImage(g2d,
                ImageHelper.getFluffImage(dropship, ImageHelper.imageDropship));

        printDropshipData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printHeatSinks(g2d);
        printFiringArcHeat(g2d);

        // Armor Pips
        printFrontArmor(g2d, dropship.getOArmor(Aero.LOC_NOSE));
        printLeftArmor(g2d, dropship.getOArmor(Aero.LOC_LWING));
        printRightArmor(g2d, dropship.getOArmor(Aero.LOC_RWING));
        printRearArmor(g2d, dropship.getOArmor(Aero.LOC_AFT));

        // Internal Pips
        printStruct(g2d, dropship.get0SI());

        g2d.scale(pageFormat.getImageableWidth(),
                pageFormat.getImageableHeight());

    }

    private void printDropshipData(Graphics2D g2d) {
        String dropshipName = dropship.getChassis() + " " + dropship.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, dropshipName, true, 180, 10.0f));
        g2d.drawString(dropshipName, 49, 125);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((dropship.getCrew() != null)
                && !dropship.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = dropship.getCrew();
            g2d.drawString(pilot.getName(), 270, 125);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 137);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 137);
        }

        g2d.drawString(Integer.toString(dropship.getWalkMP()), 99, 163);
        g2d.drawString(Integer.toString(dropship.getRunMPwithoutMASC()), 99,
                174);

        int tonnage = (int) Math.ceil(dropship.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(String.format("%1$,d", tonnage), 177, 139.5f);

        int nextDataLine = 159;
        int startLine = 188;
        int lineFeed = 8;

        if (dropship.isPrimitive()) {
            ImageHelper.printCenterString(g2d, "(Primitive)", font,
                    startLine, nextDataLine);
            nextDataLine += lineFeed;
        } else {
            switch (dropship.getTechLevel()) {

                case TechConstants.T_INTRO_BOXSET:
                    ImageHelper.printCenterString(g2d, "(Intro)", font,
                            startLine, nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_TW_NON_BOX:
                case TechConstants.T_IS_TW_ALL:
                case TechConstants.T_CLAN_TW:
                    break;
                case TechConstants.T_IS_ADVANCED:
                case TechConstants.T_CLAN_ADVANCED:
                    ImageHelper.printCenterString(g2d, "(Advanced)", font,
                            startLine, nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_EXPERIMENTAL:
                case TechConstants.T_CLAN_EXPERIMENTAL:
                    ImageHelper.printCenterString(g2d, "(Experimental)", font,
                            startLine, nextDataLine);
                    nextDataLine += lineFeed;
                    break;
                case TechConstants.T_IS_UNOFFICIAL:
                case TechConstants.T_CLAN_UNOFFICIAL:
                    ImageHelper.printCenterString(g2d, "(Unofficial)", font,
                            startLine, nextDataLine);
                    nextDataLine += lineFeed;
                    break;
            }
        }

        String techBase = "Inner Sphere";

        if (dropship.isMixedTech()) {
            if (dropship.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (dropship.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 150.5f);

        if ((dropship.getSource() != null)
                && (dropship.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, dropship.getSource(), false, 51,
                    8.0f);

            g2d.setFont(font);

            g2d.drawString(dropship.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", dropship.getYear()), 177,
                    nextDataLine);

        }

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = dropship.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 492.2f);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(
                    String.format("%1$,d",
                            dropship.calculateBattleValue(true, true)), 50,
                    492.2f);
        }

        font = UnitUtil.deriveFont(true, 8);
        g2d.setFont(font);
        g2d.drawString("Fuel: ", 80, 492.2f);
        font = UnitUtil.deriveFont(false, 8);
        g2d.setFont(font);
        g2d.drawString(String.format("%1$,d", dropship.getFuel()), 98, 492.2f);

        // Crew data
        g2d.drawString(
                String.format("%1$s/%2$s", dropship.getLifeBoats(),
                        dropship.getEscapePods()), 335, 624.6f);

        g2d.drawString(String.format("%1$s", dropship.getNCrew()), 283, 594.6f);
        g2d.drawString(String.format("%1$s", dropship.getNPassenger()), 283,
                604.6f);
        g2d.drawString(String.format("%1$s", dropship.getNOtherPassenger()),
                283, 614.6f);

        g2d.drawString(String.format("%1$s", dropship.getNMarines()), 357,
                593.6f);
        g2d.drawString(String.format("%1$s", dropship.getNBattleArmor()), 357,
                613.6f);

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills",
        // dropship.getCost(true)),
        // 52, 346.2f);

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 64f, 779.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)",
                dropship.getThresh(Aero.LOC_NOSE),
                dropship.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 410, 66);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)",
                dropship.getThresh(Aero.LOC_RWING),
                dropship.getArmor(Aero.LOC_RWING)), g2d.getFont(), 555, 225);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)",
                dropship.getThresh(Aero.LOC_LWING),
                dropship.getArmor(Aero.LOC_LWING)), g2d.getFont(), 260, 223);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)",
                dropship.getThresh(Aero.LOC_AFT),
                dropship.getArmor(Aero.LOC_AFT)), g2d.getFont(), 528, 512);
        ImageHelper.printCenterString(g2d,
                String.format("%1$S", dropship.get0SI()), g2d.getFont(), 441,
                297);

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        int posX = 395;
        int posY = 723;

        // Heat Sinks
        if (dropship.getHeatType() == Aero.HEAT_DOUBLE) {
            g2d.drawString(String.format("%1$s (%2$s)",
                    dropship.getHeatSinks(), dropship.getHeatSinks() * 2),
                    posX, posY);
            g2d.drawString("Double", posX, posY + 8);
        } else {
            g2d.drawString(
                    String.format("%1$s (%1$s)", dropship.getHeatSinks()),
                    posX, posY);
            g2d.drawString("Single", posX, posY + 8);
        }

    }

    private void printFiringArcHeat(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_NOSE, false)), 480, 727);
        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_LWING, false)), 480, 743);
        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_RWING, false)), 480, 758);

        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_AFT, false)), 540, 727);
        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_LWING, true)), 540, 743);
        g2d.drawString(
                String.format("%1$s",
                        dropship.getHeatInArc(Aero.LOC_RWING, true)), 540, 758);
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperDropShip.printDropshipWeaponsNEquipment(dropship, g2d);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {

        float baseX = 350f;
        float baseY = 286f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 30;
        int maxLinesTotal = 46;
        int maxLinesFullWidth = 36;
        int pipsPerLineSmallWidth = 15;
        Vector<float[]> pipPlotter = new Vector<float[]>(totalArmor, 1);

        if (totalArmor > 1230) {
            pipsPerLine = 31;
            baseX -= 4;
            pointX -= 4;
            pipsPerLineSmallWidth = 21;
        }
        for (int lineCount = 1; lineCount <= maxLinesTotal; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[] { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == maxLinesFullWidth) {
                baseX += shiftX * (pipsPerLine-pipsPerLineSmallWidth)/2f;
                pipsPerLine = pipsPerLineSmallWidth;
            }
            pointY -= shiftY;
            pointX = baseX;
        }

        ImageHelperDropShip.printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 346f;
        float baseY = 354f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 31;

        Vector<float[]> pipPlotter = new Vector<float[]>(1085, 1);

        for (int lineCount = 1; lineCount <= 35; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[] { pointX, pointY });
                pointX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperDropShip.printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {

        float baseX = 339f;
        float baseY = 166f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 80;

        Vector<float[]> pipPlotter = new Vector<float[]>(1160, 1);

        for (int lineCount = 1; lineCount <= 18; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[] { pointX, pointY });
                pointY += shiftY;
            }

            if ((lineCount == 5)) {
                pipsPerLine -= 4;
                baseY += 3 * shiftY;
            } else if (lineCount == 6) {
                pipsPerLine -= 1;
            } else if (lineCount == 7) {
                pipsPerLine -= 3;
                baseY += shiftY * 2;
            } else if (lineCount == 8) {
                pipsPerLine -= 1;
            } else if (lineCount == 9) {
                pipsPerLine -= 1;
            } else if (lineCount == 10) {
                pipsPerLine -= 1;
            } else if (lineCount == 11) {
                pipsPerLine -= 5;
                baseY += shiftY * 4;
            } else if (lineCount == 12) {
                baseY += shiftY * 6;
                pipsPerLine -= 10;
            } else if (lineCount == 13) {
                baseY += shiftY * 3;
                pipsPerLine -= 4;
            } else if (lineCount == 14) {
                baseY += shiftY * 2;
                pipsPerLine -= 3;
            } else if (lineCount == 15) {
                pipsPerLine -= 8;
                baseY += shiftY * 4;
            } else if ((lineCount == 17)) {
                pipsPerLine -= 5;
            }

            pointX -= shiftX;
            pointY = baseY;
        }

        ImageHelperDropShip.printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 474f;
        float baseY = 166;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 80;

        Vector<float[]> pipPlotter = new Vector<float[]>(1160, 1);

        for (int lineCount = 1; lineCount <= 18; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[] { pointX, pointY });
                pointY += shiftY;
            }

            if ((lineCount == 5)) {
                pipsPerLine -= 4;
                baseY += 3 * shiftY;
            } else if (lineCount == 6) {
                pipsPerLine -= 1;
            } else if (lineCount == 7) {
                pipsPerLine -= 3;
                baseY += shiftY * 2;
            } else if (lineCount == 8) {
                pipsPerLine -= 1;
            } else if (lineCount == 9) {
                pipsPerLine -= 1;
            } else if (lineCount == 10) {
                pipsPerLine -= 5;
                baseY += shiftY * 4;
            } else if (lineCount == 11) {
                pipsPerLine -= 3;
                baseY += shiftY * 2;
            } else if (lineCount == 12) {
                pipsPerLine -= 4;
                baseY += shiftY * 2;
            } else if (lineCount == 13) {
                pipsPerLine -= 4;
                baseY += shiftY * 2;
            } else if (lineCount == 14) {
                baseY += shiftY * 3;
                pipsPerLine -= 3;
            } else if (lineCount == 15) {
                pipsPerLine -= 10;
                baseY += shiftY * 6;
            } else if ((lineCount == 17)) {
                pipsPerLine -= 5;
            }


            pointX += shiftX;
            pointY = baseY;
        }
        ImageHelperDropShip.printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        float baseX = 356;
        float baseY = 298f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 15;
        int lines = 7;
        int circleSize = 7;
        int fillCircleSize = 5;

        if (totalArmor > 105) {
            baseX = 353;
            pointX = baseX;
            pipsPerLine = 19;
            lines = 8;
            shiftX = 6f;
            shiftY = 6f;
            circleSize = 6;
            fillCircleSize = 4;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(150, 1);

        for (int lineCount = 0; lineCount < lines; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[] { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        ImageHelperDropShip.printISPoints(g2d, pipPlotter, totalArmor, circleSize, fillCircleSize);
    }

    private void printDropshipImage(Graphics2D g2d, Image img) {

        int width = Math.min(220, img.getWidth(null));
        int height = Math.min(112, img.getHeight(null));
        int drawingX = 18 + ((220 - width) / 2);
        int drawingY = 508 + ((112 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

}