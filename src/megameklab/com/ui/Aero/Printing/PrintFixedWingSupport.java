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
import megamek.common.FixedWingSupport;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperAero;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintFixedWingSupport implements Printable {

    private FixedWingSupport fixedWingSupport = null;

    public PrintFixedWingSupport(FixedWingSupport fixedWingSupport) {
        this.fixedWingSupport = fixedWingSupport;
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

        g2d.drawImage(ImageHelper.getRecordSheet(fixedWingSupport), 18, 18, 558, 738, Color.BLACK, null);
        printFixedWingSupportImage(g2d, ImageHelper.getFluffImage(fixedWingSupport, ImageHelper.imageAero));

        printFixedWingSupportData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor Pips
        printFrontArmor(g2d, fixedWingSupport.getOArmor(Aero.LOC_NOSE));
        printLeftArmor(g2d, fixedWingSupport.getOArmor(Aero.LOC_LWING));
        printRightArmor(g2d, fixedWingSupport.getOArmor(Aero.LOC_RWING));
        printRearArmor(g2d, fixedWingSupport.getOArmor(Aero.LOC_AFT));

        // Internal Pips
        printStruct(g2d, fixedWingSupport.get0SI());

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printFixedWingSupportData(Graphics2D g2d) {
        String FixedWingSupportName = fixedWingSupport.getChassis() + " " + fixedWingSupport.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, FixedWingSupportName, true, 180, 10.0f));
        g2d.drawString(FixedWingSupportName, 49, 118);

        Font font = UnitUtil.deriveFont(true, 15.0f);
        g2d.setFont(font);

        g2d.drawString("FIXED WING SUPPORT VEHICLE RECORD SHEET", 60, 88);

        font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);
        if ((fixedWingSupport.getCrew() != null) && !fixedWingSupport.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = fixedWingSupport.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(fixedWingSupport.getWalkMP()), 99, 143);
        g2d.drawString(Integer.toString(fixedWingSupport.getRunMP()), 99, 154);

        if (fixedWingSupport.isVSTOL()) {
            g2d.drawString("VSTOL", 99, 165);
        } else if (fixedWingSupport.isSTOL()) {
            g2d.drawString("STOL", 99, 165);
        }

        int tonnage = (int) Math.ceil(fixedWingSupport.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 132.5f);

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

        switch (fixedWingSupport.getTechLevel()) {

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

        if (fixedWingSupport.isMixedTech()) {
            if (fixedWingSupport.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (fixedWingSupport.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 143.5f);

        if ((fixedWingSupport.getSource() != null) && (fixedWingSupport.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, fixedWingSupport.getSource(), false, 51, 8.0f);

            g2d.setFont(font);

            g2d.drawString(fixedWingSupport.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", fixedWingSupport.getYear()), 177, nextDataLine);

        }

        // g2d.drawString(Integer.toString(fixedWingSupport.getYear()), 188,
        // 155);

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols =
        // new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = fixedWingSupport.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 346.2f);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", fixedWingSupport.calculateBattleValue(true, true)), 50, 346.2f);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills",
        // fixedWingSupport.getCost(true)),
        // 52, 346.2f);

        if (UnitUtil.hasBAR(fixedWingSupport) && !fixedWingSupport.hasPatchworkArmor()) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(fixedWingSupport), 245, 120);
        }

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 745f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(fixedWingSupport), font, 352, 108);
        //g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(fixedWingSupport), 337, 108);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", fixedWingSupport.getThresh(Aero.LOC_NOSE), fixedWingSupport.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 315, 162);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", fixedWingSupport.getThresh(Aero.LOC_RWING), fixedWingSupport.getArmor(Aero.LOC_RWING)), g2d.getFont(), 495, 333);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", fixedWingSupport.getThresh(Aero.LOC_LWING), fixedWingSupport.getArmor(Aero.LOC_LWING)), g2d.getFont(), 290, 333);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", fixedWingSupport.getThresh(Aero.LOC_AFT), fixedWingSupport.getArmor(Aero.LOC_AFT)), g2d.getFont(), 398, 487);

        g2d.drawString(String.format("%1$S", fixedWingSupport.get0SI()), 390, 268);

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperAero.printAeroWeaponsNEquipment(fixedWingSupport, g2d);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 388f;
        float baseY = 107f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 8f;
        float shiftY = 8f;

        float rightPointX = 0;
        float rightPointY = 0;
        int pipsPerLine = 2;

        Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

        for (int lineCount = 1; lineCount <= 32; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            if (lineCount == 2) {
                pipsPerLine += 2;
                baseX -= shiftX;
            } else if (lineCount == 6) {
                rightPointY = pointY;
                rightPointX = baseX;
                rightPointX += shiftX * 3;
                pipsPerLine = 1;
            } else if ((lineCount == 7) || (lineCount == 8)) {
                baseX -= shiftX / 4;
            } else if ((lineCount >= 10) && (lineCount < 16)) {
                baseX -= shiftX;
                pipsPerLine++;
            } else if (lineCount == 16) {
                baseX -= shiftX;
                pipsPerLine = 4;
            } else if (lineCount == 19) {
                pipsPerLine = 1;
                pointY = rightPointY;
                baseX = rightPointX;
            } else if ((lineCount == 20) || (lineCount == 21)) {
                baseX += shiftX / 4;
            } else if ((lineCount >= 23) && (lineCount < 29)) {
                pipsPerLine++;
            } else if (lineCount == 29) {
                pipsPerLine = 4;
                baseX += shiftX * 4;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 372;
        float baseY = 303f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 8f;
        float shiftY = 8f;
        int pipsPerLine = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

        for (int lineCount = 1; lineCount <= 17; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            if (lineCount == 12) {
                pipsPerLine = 4;
                baseX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 353f;
        float baseY = 330f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 8f;
        float shiftY = 8f;
        int pipsPerLine = 1;

        Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

        for (int lineCount = 1; lineCount <= 12; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            if (lineCount < 10) {
                pipsPerLine++;
                baseX -= shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {

        float baseX = 432f;
        float baseY = 330f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 8f;
        float shiftY = 8f;
        int pipsPerLine = 1;

        Vector<float[]> pipPlotter = new Vector<float[]>(92, 1);

        for (int lineCount = 1; lineCount <= 12; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            if (lineCount < 10) {
                pipsPerLine++;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        float baseX = 362;
        float baseY = 270f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 6.5f;
        float shiftY = 7f;
        int pipsPerLine = 10;

        Vector<float[]> pipPlotter = new Vector<float[]>(30, 1);

        for (int lineCount = 1; lineCount <= 3; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printISPoints(g2d, pipPlotter, totalArmor);
    }

    private void printFixedWingSupportImage(Graphics2D g2d, Image img) {

        int width = Math.min(220, img.getWidth(null));
        int height = Math.min(130, img.getHeight(null));
        int drawingX = 18 + ((220 - width) / 2);
        int drawingY = 365 + ((130 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

    private void printISPoints(Graphics2D g2d, Vector<float[]> pipPlotter, float totalArmor) {
        pipPlotter.trimToSize();
        float pipSpace = pipPlotter.size() / totalArmor;
        for (float pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperAero.drawAeroISPip(g2d, (int) pipPlotter.get(currentPip)[0], (int) pipPlotter.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    private void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPlotter, float totalArmor) {
        pipPlotter.trimToSize();
        float pipSpace = pipPlotter.size() / totalArmor;
        for (float pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperAero.drawAeroArmorPip(g2d, (int) pipPlotter.get(currentPip)[0], (int) pipPlotter.get(currentPip)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

}