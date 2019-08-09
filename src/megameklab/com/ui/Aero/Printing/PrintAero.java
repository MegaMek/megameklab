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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import megamek.common.Aero;
import megamek.common.Crew;
// TODO: uncomment when print issue is fixed and pilot data is ready to position
// import megamek.common.Crew;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperAero;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintAero implements Printable {

    private Aero aero;
    // TODO: uncomment when print issue is fixed and pilot data is ready to position
    // private int topMargin = 6;
    // private int leftMargin = 11;

    public PrintAero(Aero aero) {
        this.aero = aero;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
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

        g2d.drawImage(ImageHelper.getRecordSheet(aero), 18, 18, 558, 738, Color.BLACK, null);
        printAeroImage(g2d, ImageHelper.getFluffImage(aero, ImageHelper.imageAero));

        printAeroData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printHeatSinks(g2d);

        // Armor Pips
        printFrontArmor(g2d, aero.getOArmor(Aero.LOC_NOSE));
        printWingArmor(g2d, aero.getOArmor(Aero.LOC_LWING), 350, 325, -1);
        printWingArmor(g2d, aero.getOArmor(Aero.LOC_RWING), 430, 325, 1);
        printRearArmor(g2d, aero.getOArmor(Aero.LOC_AFT));

        // Internal Pips
        printStruct(g2d, aero.get0SI());

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printAeroData(Graphics2D g2d) {
        String aeroName = aero.getChassis() + " " + aero.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, aeroName, true, 180, 10.0f));
        g2d.drawString(aeroName, 49, 119);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((aero.getCrew() != null)
                && !aero.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = aero.getCrew();
            g2d.drawString(pilot.getName(), 270, 524);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 536);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 536);
        }

        g2d.drawString(Integer.toString(aero.getWalkMP()), 99, 143);
        g2d.drawString(Integer.toString(aero.getRunMP()), 99, 154);

        int tonnage = (int) Math.ceil(aero.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 132.5f);

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;
        if (!aero.isPrimitive()) {
            switch (aero.getTechLevel()) {

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
        } else {
            ImageHelper.printCenterString(g2d, "(Primitive)", font, startLine, nextDataLine);
            nextDataLine += lineFeed;
        }

        String techBase = "Inner Sphere";

        if (aero.isMixedTech()) {
            if (aero.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (aero.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 143.5f);

        if ((aero.getSource() != null) && (aero.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, aero.getSource(), false, 51, 8.0f);

            g2d.setFont(font);

            g2d.drawString(aero.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", aero.getYear()), 177, nextDataLine);

        }

        // g2d.drawString(Integer.toString(aero.getYear()), 188, 155);

        // Cost/BV
        double bv = aero.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 35, 346.2f);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", aero.calculateBattleValue(true, true)), 50, 346.2f);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills", aero.getCost(true)),
        // 52, 346.2f);

        font = UnitUtil.deriveFont(true, 7);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 62.5f, 744.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(aero), 335, 107);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_NOSE), aero.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 300, 139);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_RWING), aero.getArmor(Aero.LOC_RWING)), g2d.getFont(), 495, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_LWING), aero.getArmor(Aero.LOC_LWING)), g2d.getFont(), 290, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_AFT), aero.getArmor(Aero.LOC_AFT)), g2d.getFont(), 398, 487);

        g2d.drawString(String.format("%1$S", aero.get0SI()), 390, 268);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            g2d.drawString(String.format("%1$s (%2$s)", aero.getHeatSinks(), aero.getHeatSinks() * 2), 502, 535);
            g2d.drawString("Double", 502, 543);
        } else {
            g2d.drawString(String.format("%1$s (%1$s)", aero.getHeatSinks()), 502, 535);
            g2d.drawString("Single", 502, 543);
        }

        Dimension column = new Dimension(504, 552);
        Dimension pipShift = new Dimension(9, 9);

        for (int pos = 1; pos <= aero.getHeatSinks(); pos++) {
            ImageHelper.drawHeatSinkPip(g2d, column.width, column.height);
            column.height += pipShift.height;

            if ((pos % 10) == 0) {
                column.height -= pipShift.height * 10;
                column.width += pipShift.width;
            }

        }

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperAero.printAeroWeaponsNEquipment(aero, g2d);

    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {
        // The number of pips in each row, forward to aft
        final int[] pipsPerRow = { 8, 12, 26, 28, 28, 28, 28, 28, 24, 22, 16, 12 };
        // The number of pips to skip in the middle of the row
        final int[] gap = { 10, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final int maxPips = Arrays.stream(pipsPerRow).sum();
        
        float centerx = 393;
        float ypos = 157;
        float[] pipShift = { 7, 7 };
        
        // Calculate possible positions
        List<float[]> pipPlotter = new ArrayList<>();
        for (int row = 0; row < pipsPerRow.length; row++) {
            float xpos = centerx - pipShift[0] * ((pipsPerRow[row] + gap[row]) / 2.0f);
            for (int i = 0; i < pipsPerRow[row]; i++) {
                if (i == pipsPerRow[row] / 2) {
                    xpos += pipShift[0] * gap[row];
                }
                pipPlotter.add(new float[] { xpos, ypos });
                xpos += pipShift[0];
            }
            ypos += pipShift[1];
        }
        
        // Spread pips out among available positions
        int pipSpace = Math.max(1, maxPips / totalArmor);
        for (int pos = 0; pos < maxPips; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        // Normally we would use 6 columns, but if the armor exceeds a certain amount, we add up to two additional
        // columns
        int maxColumns = Math.min(9, Math.max(6, totalArmor / 21 + 1));
        // Compute the number of rows needed for the number of columns we have. For extreme armor levels,
        // this could end up drawing pips off the bottom of the diagram, but that's preferable to silently
        // ignoring some of the pips
        int maxRows = Math.max(21, totalArmor / maxColumns + 1);
        int maxPips = maxRows * maxColumns;
        float[] topColumn = new float[]
                { 393 - maxColumns * 3.5f, 303 };
        float[] pipShift = new float[]
                { 7, 7 };

        List<float[]> pipPlotter = new ArrayList<>();
        for (int pos = 1; pos <= maxPips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % maxColumns) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = Math.max(1, 132 / totalArmor);
        for (int pos = 0; pos < maxPips; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    /**
     * Draws the armor pips for the wings. Normally we leave some whitespace where the diagram suggests
     * tail fins, but with very high armor levels this is needed for additional wing armor pips.
     * 
     * @param g2d The graphics object for the record sheet image
     * @param totalArmor The total number of armor points for the wing
     * @param x The x coordinate of the pip at the forward edge of the wing closest to the fuselage
     * @param y The y coordinate of the row of pips at the forward edge of the wing
     * @param direction The direction away from the fuselage. This is 1 for the right wing and -1 for the left.
     */
    private void printWingArmor(Graphics2D g2d, int totalArmor, float x, float y, int direction) {
        // The number of pips for each armor row
        final int[] numPerRow = { 3, 4, 5, 6, 7, 9, 14, 15, 15, 15, 14, 14, 13, 10, 8 };
        // The offset away from centerline for the start of each row
        final float[] startOffset = { 0, 0, 0, 0, 5, 5, 9.5f, 9.5f, 9.5f, 12.6f, 18.9f, 18.9f, 18.9f, 22.2f, 22.2f};
        // Calculate the maximum number of pips for the wing space. If higher, we'll need to put some in what
        // would normally be empty whitespace next to the aft section.
        int highArmorThreshold = Arrays.stream(numPerRow).sum();
        // The number of pips for each row if we exceed the standard capacity. The offset for each row is zero.
        final int[] numPerRowHighArmor = { 3, 4, 5, 6, 8, 10, 15, 16, 16, 17, 17, 16, 16, 14, 11, 3, 3, 3 };
        int maxArmor = Arrays.stream(numPerRowHighArmor).sum();
        
        
        float[] topColumn = new float[] { x, y };
        float[] pipShift = new float[] { 6.3f * direction, 7 };

        int currentInRow = 0;
        int currentRow = 0;
        int[] rowLength = totalArmor > highArmorThreshold? numPerRowHighArmor : numPerRow;
        List<float[]> pipPlotter = new ArrayList<>();

        for (int pos = 0; pos < maxArmor; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];

            if (++currentInRow == rowLength[currentRow]) {
                currentInRow = 0;
                currentRow++;
                if (currentRow == rowLength.length) {
                    // Don't need the extra pips
                    break;
                }
                topColumn[0] = x;
                topColumn[1] += pipShift[1];
                if (totalArmor <= highArmorThreshold) {
                    topColumn[0] += startOffset[currentRow] * direction;
                }
            }
       }

        int pipSpace = Math.max(1, (totalArmor > highArmorThreshold? maxArmor : highArmorThreshold) / totalArmor);
        for (int pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        int posY = 369;
        int posX = 269;
        int[] topColumn = new int[]
            { posY, posX };
        int[] pipShift = new int[]
            { 6, 6 };

        ArrayList<int[]> pipPlotter = new ArrayList<>(132);
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

    private void printAeroImage(Graphics2D g2d, Image img) {

        int width = Math.min(220, img.getWidth(null));
        int height = Math.min(130, img.getHeight(null));
        int drawingX = 18 + ((220 - width) / 2);
        int drawingY = 365 + ((130 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

}