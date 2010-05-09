/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.ui.Aero.Printing;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import megamek.common.Aero;
import megamek.common.Pilot;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintAero implements Printable {

    private Aero aero = null;
    private ArrayList<Aero> aeroList;

    public PrintAero(ArrayList<Aero> list) {
        aeroList = list;

    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
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

        System.gc();

        g2d.drawImage(ImageHelper.getRecordSheet(aero), 18, 18, 558, 738, Color.BLACK, null);
        printAeroImage(g2d, ImageHelper.getFluffImage(aero, ImageHelper.imageAero));

        printAeroData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printHeatSinks(g2d);

        // Armor Pips
        printFrontArmor(g2d, aero.getOArmor(Aero.LOC_NOSE));
        printLeftArmor(g2d, aero.getOArmor(Aero.LOC_LWING));
        printRightArmor(g2d, aero.getOArmor(Aero.LOC_RWING));
        printRearArmor(g2d, aero.getOArmor(Aero.LOC_AFT));

        // Internal Pips
        printStruct(g2d, aero.get0SI());

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printAeroData(Graphics2D g2d) {
        String aeroName = aero.getChassis() + " " + aero.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, aeroName, true, 180, 10.0f));
        g2d.drawString(aeroName, 49, 118);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((aero.getCrew() != null) && !aero.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = aero.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(aero.getWalkMP()), 99, 143);
        g2d.drawString(Integer.toString(aero.getRunMP()), 99, 154);

        int tonnage = (int) Math.ceil(aero.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 132.5f);

        int nextDataLine = 153;
        int startLine = 188;
        int lineFeed = 8;

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

        String techBase = "Inner Sphere";
        if (aero.isClan()) {
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
        g2d.drawString(String.format("%1$,d", aero.calculateBattleValue(true, true)), 150, 346.2f);

        // myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(String.format("%1$,.0f C-bills", aero.getCost(true)), 52, 346.2f);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 62.5f, 745f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_NOSE), aero.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 300, 139);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_RWING), aero.getArmor(Aero.LOC_RWING)), g2d.getFont(), 495, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_LWING), aero.getArmor(Aero.LOC_LWING)), g2d.getFont(), 290, 310);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", aero.getThresh(Aero.LOC_AFT), aero.getArmor(Aero.LOC_AFT)), g2d.getFont(), 398, 487);

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

            if (pos % 10 == 0) {
                column.height -= pipShift.height * 10;
                column.width += pipShift.width;
            }

        }

    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printAeroWeaponsNEquipment(aero, g2d);

    }

    public void print() {

        try {
            PrinterJob masterPrintJob = PrinterJob.getPrinterJob();

            if (masterPrintJob.printDialog()) {
                for (int pos = 0; pos < aeroList.size(); pos++) {
                    PrinterJob pj = PrinterJob.getPrinterJob();
                    pj.setPrintService(masterPrintJob.getPrintService());
                    if (pj.printDialog()) {
                        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                        aset.add(PrintQuality.HIGH);

                        PageFormat pageFormat = new PageFormat();
                        pageFormat = pj.getPageFormat(null);

                        Paper p = pageFormat.getPaper();
                        p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                        pageFormat.setPaper(p);

                        pj.setPrintable(this, pageFormat);
                        aero = aeroList.get(pos);
                        pj.setJobName(aero.getChassis() + " " + aero.getModel());

                        try {
                            pj.print(aset);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        System.gc();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn =
            { 302, 167 };
        float[] pipShift =
            { 7, 7 };
        float maxColumns = 25;

        Vector<float[]> pipPlotter = new Vector<float[]>(200);
        for (int pos = 1; pos <= 200; pos++) {
            // ImageHelper.drawAeroArmorPip(g2d, topColumn[0], topColumn[1]);
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if (pos % maxColumns == 0) {
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
            ImageHelper.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn = new float[]
            { 374, 303 };
        float[] pipShift = new float[]
            { 7, 7 };
        float maxColumns = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(132);
        for (int pos = 1; pos <= 132; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if (pos % maxColumns == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = 132 / totalArmor;
        for (int pos = 0; pos < 132; pos += pipSpace) {
            ImageHelper.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn = new float[]
            { 336f, 325 };
        float[] pipShift = new float[]
            { 6.3f, 7 };

        int numberPerRow = 3;
        int curretNumber = 0;
        Vector<float[]> pipPlotter = new Vector<float[]>(132);

        for (int pos = 1; pos < 148; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];

            if (++curretNumber == numberPerRow) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;

                curretNumber = 0;
                if (pos == 18) {
                    topColumn[0] -= pipShift[0] * .8;
                    numberPerRow++;
                } else if (pos == 48) {
                    topColumn[0] -= Math.abs(pipShift[0]) * 1.6;
                } else if (pos == 76) {
                    topColumn[0] -= Math.abs(pipShift[0]) * 1.6;
                } else if (pos == 104) {
                    numberPerRow = 13;
                    topColumn[0] -= Math.abs(pipShift[0]) * 1.5;
                } else if (pos == 130) {
                    numberPerRow = 11;
                    topColumn[0] -= Math.abs(pipShift[0]) * 1.5;
                } else if (pos == 141) {
                    numberPerRow = 8;
                    topColumn[0] += Math.abs(pipShift[0]) * 3;
                } else if (numberPerRow < 13) {
                    if (numberPerRow == 7) {
                        numberPerRow = 9;
                        // topColumn[0] += Math.abs(pipShift[0]);
                    } else if (numberPerRow == 9) {
                        numberPerRow = 14;
                        topColumn[0] -= pipShift[0] * 4;
                    } else {
                        numberPerRow++;
                    }

                    if (pipShift[0] < 0) {
                        topColumn[0] += pipShift[0];
                    }
                } else {
                    topColumn[0] += pipShift[0];
                }

            }
        }

        int pipSpace = 148 / totalArmor;
        for (int pos = 0; pos < 148; pos += pipSpace) {
            ImageHelper.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float[] topColumn = new float[]
            { 430f, 325 };
        float[] pipShift = new float[]
            { 6.3f, 7 };

        int numberPerRow = 3;
        int curretNumber = 0;
        Vector<float[]> pipPlotter = new Vector<float[]>(132);

        for (int pos = 1; pos < 148; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });

            topColumn[0] += pipShift[0];

            if (++curretNumber == numberPerRow) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;

                curretNumber = 0;
                if (pos == 18) {
                    topColumn[0] += pipShift[0] * 2;
                } else if (pos == 103) {
                    numberPerRow = 13;
                    topColumn[0] += pipShift[0] * 1.5;
                } else if (pos == 116) {
                    numberPerRow = 12;
                    topColumn[0] += pipShift[0];
                } else if (pos == 128) {
                    numberPerRow = 11;
                    topColumn[0] += pipShift[0] * 1.5;
                } else if (pos == 139) {
                    numberPerRow = 8;
                    topColumn[0] += pipShift[0];
                } else if (numberPerRow < 13) {
                    if (numberPerRow == 7) {
                        numberPerRow = 12;
                        topColumn[0] += Math.abs(pipShift[0] * 4);
                    } else if (numberPerRow == 6) {
                        numberPerRow = 9;
                        topColumn[0] -= pipShift[0] * 2;
                    } else if (numberPerRow == 9) {
                        numberPerRow = 14;
                    } else {
                        numberPerRow++;
                    }

                    if (pipShift[0] > 0) {
                        topColumn[0] += pipShift[0];
                    }
                } else if (pipShift[0] > 0) {
                    topColumn[0] += pipShift[0] * .8;
                } else if (pipShift[0] < 0) {
                    topColumn[0] += pipShift[0] * .2;
                }

            }
        }

        int pipSpace = 148 / totalArmor;
        for (int pos = 0; pos < 148; pos += pipSpace) {
            ImageHelper.drawAeroArmorPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        int[] topColumn = new int[]
            { 366, 265 };
        int[] pipShift = new int[]
            { 7, 7 };

        Vector<int[]> pipPlotter = new Vector<int[]>(132);
        for (int pos = 1; pos <= 32; pos++) {
            pipPlotter.add(new int[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if (pos % 8 == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        int pipSpace = 32 / totalArmor;
        for (int pos = 0; pos < 32; pos += pipSpace) {
            ImageHelper.drawAeroISPip(g2d, pipPlotter.get(pos)[0], pipPlotter.get(pos)[1]);
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