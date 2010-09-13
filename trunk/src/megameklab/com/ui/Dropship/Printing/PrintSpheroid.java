/*
 * MegaMekLab - Copyright (C) 2010
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.ui.Dropship.Printing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import megamek.common.Dropship;
import megamek.common.Pilot;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

/**
 * @author Torren
 * 
 */
public class PrintSpheroid implements Printable {

    private Dropship dropship = null;
    private ArrayList<Dropship> dropshipList;
    PrinterJob masterPrintJob;

    public PrintSpheroid(ArrayList<Dropship> list, PrinterJob masterPrintJob) {
        dropshipList = list;
        this.masterPrintJob = masterPrintJob;

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

        g2d.drawImage(ImageHelper.getRecordSheet(dropship), 18, 18, 558, 738, Color.BLACK, null);
        // printDropshipImage(g2d, ImageHelper.getFluffImage(dropship,
        // ImageHelper.imageDropship));

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

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printDropshipData(Graphics2D g2d) {
        String dropshipName = dropship.getChassis() + " " + dropship.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, dropshipName, true, 180, 10.0f));
        g2d.drawString(dropshipName, 49, 121);

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        if ((dropship.getCrew() != null) && !dropship.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Pilot pilot = dropship.getCrew();
            g2d.drawString(pilot.getName(), 270, 120);
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295, 132);
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365, 132);
        }

        g2d.drawString(Integer.toString(dropship.getWalkMP()), 99, 156);
        g2d.drawString(Integer.toString(dropship.getRunMPwithoutMASC()), 99, 167);

        g2d.drawString(String.format("%1$s / %2$s", dropship.getFighterBays().size(), dropship.getSmallCraftBays().size()), 104, 177);
        g2d.drawString(String.format("%1$s / Turn", dropship.getFighterLaunchRate()), 186, 177);

        int tonnage = (int) Math.ceil(dropship.getWeight());

        if (tonnage % 5 != 0) {
            tonnage += 5 - (tonnage % 5);
        }

        g2d.drawString(Integer.toString(tonnage), 177, 134.5f);

        int nextDataLine = 154;
        int startLine = 188;
        int lineFeed = 8;

        switch (dropship.getTechLevel()) {

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
        if (dropship.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 177, 144.5f);

        if ((dropship.getSource() != null) && (dropship.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 138, nextDataLine);

            font = UnitUtil.getNewFont(g2d, dropship.getSource(), false, 51, 8.0f);

            g2d.setFont(font);

            g2d.drawString(dropship.getSource(), 177, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 138, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", dropship.getYear()), 177, nextDataLine);

        }

        // Cost/BV
        g2d.drawString(String.format("%1$,d", dropship.calculateBattleValue(true, true)), 152, 470.2f);

        // Crew data
        g2d.drawString(String.format("%1$s/%1$s", dropship.getLifeBoats(), dropship.getEscapePods()), 335, 596.6f);

        g2d.drawString(String.format("%1$s", dropship.getNCrew()), 283, 566.6f);
        g2d.drawString(String.format("%1$s", dropship.getNPassenger()), 283, 576.6f);
        g2d.drawString(String.format("%1$s", 0), 283, 586.6f);

        g2d.drawString(String.format("%1$s", UnitUtil.getInfBayCount(dropship)), 357, 565.6f);
        g2d.drawString(String.format("%1$s", 0), 357, 575.6f);
        g2d.drawString(String.format("%1$s", UnitUtil.getBattleArmorBayCount(dropship)), 357, 585.6f);

        // myFormatter = new DecimalFormat("#,###.##");
        // g2d.drawString(String.format("%1$,.0f C-bills",
        // dropship.getCost(true)),
        // 52, 346.2f);

        font = new Font("Arial", Font.BOLD, 7);
        g2d.setFont(font);
        g2d.drawString("2010", 65.5f, 744.5f);
    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", dropship.getThresh(Aero.LOC_NOSE), dropship.getArmor(Aero.LOC_NOSE)), g2d.getFont(), 410, 64);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", dropship.getThresh(Aero.LOC_RWING), dropship.getArmor(Aero.LOC_RWING)), g2d.getFont(), 550, 215);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", dropship.getThresh(Aero.LOC_LWING), dropship.getArmor(Aero.LOC_LWING)), g2d.getFont(), 260, 215);
        ImageHelper.printCenterString(g2d, String.format("%1$S (%2$s)", dropship.getThresh(Aero.LOC_AFT), dropship.getArmor(Aero.LOC_AFT)), g2d.getFont(), 528, 490);
        ImageHelper.printCenterString(g2d, String.format("%1$S", dropship.get0SI()), g2d.getFont(), 441, 284);

    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        int posX = 395;
        int posY = 691;

        // Heat Sinks
        if (dropship.getHeatType() == Aero.HEAT_DOUBLE) {
            g2d.drawString(String.format("%1$s (%2$s)", dropship.getHeatSinks(), dropship.getHeatSinks() * 2), posX, posY);
            g2d.drawString("Double", posX, posY + 8);
        } else {
            g2d.drawString(String.format("%1$s (%1$s)", dropship.getHeatSinks()), posX, posY);
            g2d.drawString("Single", posX, posY + 8);
        }

    }

    private void printFiringArcHeat(Graphics2D g2d) {
        Font font = UnitUtil.deriveFont(true, 8.0f);
        g2d.setFont(font);

        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_NOSE, false)), 478, 694);
        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_LWING, false)), 478, 715);
        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_LWING, true)), 478, 725);

        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_AFT, false)), 540, 694);
        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_RWING, false)), 540, 715);
        g2d.drawString(String.format("%1$s", dropship.getHeatInArc(Aero.LOC_RWING, true)), 540, 725);
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelper.printDropshipWeaponsNEquipment(dropship, g2d);

    }

    public void print() {

        try {
            for (int pos = 0; pos < dropshipList.size(); pos++) {
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
                dropship = dropshipList.get(pos);
                pj.setJobName(dropship.getChassis() + " " + dropship.getModel());

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

    private void printFrontArmor(Graphics2D g2d, int totalArmor) {

        float baseX = 380f;
        float baseY = 93f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 15;

        Vector<float[]> pipPlotter = new Vector<float[]>(105, 1);

        for (int lineCount = 1; lineCount <= 46; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 10) {
                pipsPerLine = 30;
                baseX -= shiftX * 7.5f;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printRearArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 350f;
        float baseY = 342f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 30;

        Vector<float[]> pipPlotter = new Vector<float[]>(105, 1);

        for (int lineCount = 1; lineCount <= 32; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printLeftArmor(Graphics2D g2d, int totalArmor) {

        float baseX = 319f;
        float baseY = 165f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(105, 1);

        for (int lineCount = 1; lineCount <= 70; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                baseX -= shiftX;
                pipsPerLine++;
            } else if ((lineCount == 3) || (lineCount == 8) || (lineCount == 13)) {
                baseX -= shiftX * 2;
                pipsPerLine += 2;
            } else if (lineCount == 65) {
                baseX += shiftX * 3;
                pipsPerLine -= 3;
            }
            pointY += shiftY;
            pointX = baseX;
        }

        printArmorPoints(g2d, pipPlotter, totalArmor);

    }

    private void printRightArmor(Graphics2D g2d, int totalArmor) {
        float baseX = 474f;
        float baseY = 165f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 4f;
        float shiftY = 4f;
        int pipsPerLine = 6;

        Vector<float[]> pipPlotter = new Vector<float[]>(105, 1);

        for (int lineCount = 1; lineCount <= 70; lineCount++) {
            for (int point = 0; point < pipsPerLine; point++) {
                pipPlotter.add(new float[]
                    { pointX, pointY });
                pointX += shiftX;
            }

            if (lineCount == 1) {
                pipsPerLine++;
            } else if ((lineCount == 3) || (lineCount == 8) || (lineCount == 13)) {
                pipsPerLine += 2;
            } else if (lineCount == 65) {
                pipsPerLine -= 3;
            }
            pointY += shiftY;
            pointX = baseX;
        }
        printArmorPoints(g2d, pipPlotter, totalArmor);
    }

    private void printStruct(Graphics2D g2d, int totalArmor) {
        float baseX = 356;
        float baseY = 286f;
        float pointX = baseX;
        float pointY = baseY;
        float shiftX = 7f;
        float shiftY = 7f;
        int pipsPerLine = 15;

        Vector<float[]> pipPlotter = new Vector<float[]>(105, 1);

        for (int lineCount = 1; lineCount <= 6; lineCount++) {
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

    private void printISPoints(Graphics2D g2d, Vector<float[]> pipPlotter, float totalArmor) {
        pipPlotter.trimToSize();
        float pipSpace = pipPlotter.size() / totalArmor;
        for (float pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelper.drawDropshipISPip(g2d, (int) pipPlotter.get(currentPip)[0], (int) pipPlotter.get(currentPip)[1]);
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
            ImageHelper.drawDropshipArmorPip(g2d, pipPlotter.get(currentPip)[0], pipPlotter.get(currentPip)[1], 5.0f);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

}