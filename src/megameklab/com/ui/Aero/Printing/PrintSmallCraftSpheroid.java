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
import java.io.File;
import java.util.Calendar;
import java.util.Vector;

import com.kitfox.svg.SVGException;

import megamek.common.Aero;
import megamek.common.SmallCraft;
//TODO: uncomment when print issue is fixed and pilot data is ready to position
//import megamek.common.Crew;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperAero;
import megameklab.com.util.UnitUtil;

public class PrintSmallCraftSpheroid implements Printable {

    private SmallCraft smallCraft = null;
    // TODO: uncomment when print issue is fixed and pilot data is ready to position
    // private int topMargin = 6;
    // private int leftMargin = 11;

    public PrintSmallCraftSpheroid(SmallCraft smallCraft) {
        this.smallCraft = smallCraft;
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

        // g2d.drawImage(ImageHelper.getRecordSheet(smallCraft), 18, 18, 558,
        // 738, Color.BLACK, null);
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/SpheroidSmallScraftTemplate.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
        g2d.setColor(Color.BLACK);
        printSmallCraftSpheroidImage(g2d,
                ImageHelper.getFluffImage(smallCraft, ImageHelper.imageAero));

        printSmallCraftSpheroidData(g2d);
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

        g2d.scale(pageFormat.getImageableWidth(),
                pageFormat.getImageableHeight());

    }

    private void printSmallCraftSpheroidData(Graphics2D g2d) {
        String smallCraftName = smallCraft.getChassis() + " "
                + smallCraft.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, smallCraftName, true, 180, 10.0f));
        g2d.drawString(smallCraftName, 52, 117);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        //TODO: Pilot Data: Fix coords. Below coords are pasted from Mech code.
        //if ((aero.getCrew() != null) && !aero.getCrew().getName().equalsIgnoreCase("unnamed")) {
        //	Crew pilot = aero.getCrew();		
		//	g2d.drawString(pilot.getName(), 270 + leftMargin, topMargin + 119);
		//	g2d.drawString(String.valueOf(pilot.getGunnery()), 295 + leftMargin, topMargin + 132);
		//  g2d.drawString(String.valueOf(pilot.getPiloting()), 365 + leftMargin, topMargin + 132);
        //}
        // Test strings
		//    g2d.drawString("Test Pilot", 270 + leftMargin, topMargin + 119);
		//	g2d.drawString("5", 295 + leftMargin, topMargin + 132);
		//    g2d.drawString("5", 365 + leftMargin, topMargin + 132);

        g2d.drawString(Integer.toString(smallCraft.getWalkMP()), 102, 142);
        g2d.drawString(Integer.toString(smallCraft.getRunMP()), 102, 152.5f);

        int tonnage = (int) Math.ceil(smallCraft.getWeight());

        if ((tonnage % 5) != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 185, 131.5f);

        int nextDataLine = 152;
        int startLine = 188;
        int lineFeed = 8;

        switch (smallCraft.getTechLevel()) {

            case TechConstants.T_INTRO_BOXSET:
                ImageHelper.printCenterString(g2d, "(Intro)", font, startLine,
                        nextDataLine);
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
        g2d.drawString(techBase, 185, 142.5f);

        if ((smallCraft.getSource() != null)
                && (smallCraft.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, smallCraft.getSource(), false, 51,
                    8.0f);

            g2d.setFont(font);

            g2d.drawString(smallCraft.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", smallCraft.getYear()), 177,
                    nextDataLine);

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
            g2d.drawString("BV: ", 35, 348);
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(
                    String.format("%1$,d",
                            smallCraft.calculateBattleValue(true, true)), 50,
                    348);
        }

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills",
        // smallCraft.getCost(true)),
        // 52, 346.2f);

        font = UnitUtil.deriveFont(true, 6);
        g2d.setFont(font);
        g2d.drawString(
                Integer.toString(Calendar.getInstance().get(Calendar.YEAR)),
                40, 762);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(
                g2d,
                String.format("%1$S (%2$s)",
                        smallCraft.getThresh(Aero.LOC_NOSE),
                        smallCraft.getArmor(Aero.LOC_NOSE)), g2d.getFont(),
                445, 92);

        ImageHelper.printCenterString(
                g2d,
                String.format("%1$S (%2$s)",
                        smallCraft.getThresh(Aero.LOC_RWING),
                        smallCraft.getArmor(Aero.LOC_RWING)), g2d.getFont(),
                520, 412);

        ImageHelper.printCenterString(
                g2d,
                String.format("%1$S (%2$s)",
                        smallCraft.getThresh(Aero.LOC_LWING),
                        smallCraft.getArmor(Aero.LOC_LWING)), g2d.getFont(),
                270, 412);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)",
                smallCraft.getThresh(Aero.LOC_AFT),
                smallCraft.getArmor(Aero.LOC_AFT)), g2d.getFont(), 370, 508);

        g2d.drawString(String.format("%1$S", smallCraft.get0SI()), 395, 269f);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        // Heat Sinks
        if (smallCraft.getHeatType() == Aero.HEAT_DOUBLE) {
            g2d.drawString(String.format("%1$s (%2$s)",
                    smallCraft.getHeatSinks(), smallCraft.getHeatSinks() * 2),
                    508, 540);
            g2d.drawString("Double", 508, 546.5f);
        } else {
            g2d.drawString(
                    String.format("%1$s (%1$s)", smallCraft.getHeatSinks()),
                    508, 540);
            g2d.drawString("Single", 508, 546.5f);
        }

        Dimension column = new Dimension(510, 556);
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
        float[] topColumn = { 370, 105 };
        float[] pipShift = { 7, 7 };
        float maxColumns = 8;

        Vector<float[]> pipPlotter = new Vector<float[]>(200);
        for (int pos = 1; pos <= 200; pos++) {
            // ImageHelperAero.drawAeroArmorPip(g2d, topColumn[0],
            // topColumn[1]);
            pipPlotter.add(new float[] { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % maxColumns) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
                /*
                 * if (pos > totalArmor - maxColumns) { topColumn[0] +=
                 * pipShift[0] ((maxColumns - (totalArmor - pos)) / 2); } else {
                 * topColumn[0] += pipShift[0] / 2; }
                 */
            }
        }

        int pipSpace = 200 / totalArmor;
        for (int pos = 0; pos < 200; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0],
                    pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn = new float[] { 370, 310 };
        float[] pipShift = new float[] { 7, 7 };
        float maxColumns = 8;

        Vector<float[]> pipPlotter = new Vector<float[]>(132);
        for (int pos = 1; pos <= 132; pos++) {
            pipPlotter.add(new float[] { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % maxColumns) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = 132 / totalArmor;
        for (int pos = 0; pos < 132; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0],
                    pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {

        float[] topColumn = new float[] { 280, 165 };
        float[] pipShift = new float[] { 7, 7 };
        float maxColumns = 8;

        Vector<float[]> pipPlotter = new Vector<float[]>(200);
        for (int pos = 1; pos <= 200; pos++) {
            pipPlotter.add(new float[] { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % maxColumns) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = 200 / totalArmor;
        for (int pos = 0; pos < 200; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0],
                    pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn = new float[] { 460, 165 };
        float[] pipShift = new float[] { 7, 7 };
        float maxColumns = 8;

        Vector<float[]> pipPlotter = new Vector<float[]>(200);
        for (int pos = 1; pos <= 200; pos++) {
            pipPlotter.add(new float[] { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % maxColumns) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = 200 / totalArmor;
        for (int pos = 0; pos < 200; pos += pipSpace) {
            ImageHelperAero.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0],
                    pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        int posY = 369;
        int posX = 270;
        int[] topColumn = new int[] { posY, posX };
        int[] pipShift = new int[] { 6, 6 };

        Vector<int[]> pipPlotter = new Vector<int[]>(132);
        for (int pos = 1; pos <= 32; pos++) {
            pipPlotter.add(new int[] { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 8) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = (int) Math.floor(32 / totalArmor);
        for (int pos = 0; pos < 32; pos += pipSpace) {
            ImageHelperAero.drawAeroISPip(g2d, pipPlotter.get(pos)[0],
                    pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printSmallCraftSpheroidImage(Graphics2D g2d, Image img) {

        int width = Math.min(220, img.getWidth(null));
        int height = Math.min(130, img.getHeight(null));
        int drawingX = 18 + ((220 - width) / 2);
        int drawingY = 370 + ((130 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

}