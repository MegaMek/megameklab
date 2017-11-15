/*
 * MegaMekLab - Copyright (C) 2011
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

package megameklab.com.ui.Vehicle.Printing;

import java.awt.Color;
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

import megamek.common.Engine;
import megamek.common.MiscType;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImageHelperVehicle;
import megameklab.com.util.UnitUtil;

public class PrintNavalVehicle implements Printable {

    private Tank sub = null;
    private int topmargin = 0;
    private int leftmargin = 8;


    public PrintNavalVehicle(Tank sub) {
        this.sub = sub;

    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }

        System.gc();

        //g2d.drawImage(ImageHelper.getRecordSheet(sub, false), 18, 18, 558, 736, null);
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/NavalArmorDiagram.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/NavalCritTable.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/NavalData.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/NavalHitTable.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/NavalMotiveDmgTable.svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/TankSheetCopyrightInfo.svg")).render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }


        printTankData(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);

        // Armor/IS Pips
        try {
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_Left_Armor_"+sub.getArmor(Tank.LOC_LEFT)+".svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_Rear_Armor_"+sub.getArmor(Tank.LOC_REAR)+".svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_Front_Armor_"+sub.getArmor(Tank.LOC_FRONT)+".svg")).render(g2d);
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_Right_Armor_"+sub.getArmor(Tank.LOC_RIGHT)+".svg")).render(g2d);
            if (!sub.hasNoTurret()) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_Turret_Armor_"+sub.getArmor(Tank.LOC_TURRET)+".svg")).render(g2d);
            }
            ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_IS_"+sub.getInternal(Tank.LOC_LEFT)+".svg")).render(g2d);

            if (!sub.hasNoTurret()) {
                ImageHelper.loadSVGImage(new File("data/images/recordsheets/Naval_TURRET_IS_"+sub.getInternal(Tank.LOC_TURRET)+".svg")).render(g2d);
            }

        } catch (SVGException e) {
            e.printStackTrace();
        }
        /*
        printFrontArmor(g2d, sub.getOArmor(Tank.LOC_FRONT), false, sub.hasModularArmor(Tank.LOC_FRONT));
        printLeftArmor(g2d, sub.getOArmor(Tank.LOC_LEFT), false, sub.hasModularArmor(Tank.LOC_LEFT));
        printRightArmor(g2d, sub.getOArmor(Tank.LOC_RIGHT), false, sub.hasModularArmor(Tank.LOC_RIGHT));
        printRearArmor(g2d, sub.getOArmor(Tank.LOC_REAR), false, sub.hasModularArmor(Tank.LOC_REAR));
        printTurretArmor(g2d, sub.getOArmor(Tank.LOC_TURRET), false, sub.hasModularArmor(Tank.LOC_TURRET));
        */

        // Internal Pips
        /*
        printFrontStruct(g2d, sub.getOInternal(Tank.LOC_FRONT), false);
        printLeftStruct(g2d, sub.getOInternal(Tank.LOC_LEFT), false);
        printRightStruct(g2d, sub.getOInternal(Tank.LOC_RIGHT), false);
        printRearStruct(g2d, sub.getOInternal(Tank.LOC_REAR), false);

        printTurretStruct(g2d, sub.getOInternal(Tank.LOC_TURRET), false);
         */
        printTankImage(g2d);

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    }

    private void printTankData(Graphics2D g2d) {
        String subName = sub.getChassis() + " " + sub.getModel();

        g2d.setFont(UnitUtil.getNewFont(g2d, subName, true, 180, 10.0f));
        g2d.drawString(subName, 50+leftmargin, topmargin+119) ;

        Font font = UnitUtil.deriveFont(8.0f);
        g2d.setFont(font);

        /*if ((sub.getCrew() != null) && !sub.getCrew().getName().equalsIgnoreCase("unnamed")) {
            Crew pilot = sub.getCrew();
            g2d.drawString(pilot.getName(), 270+leftmargin, topmargin+117) ;
            g2d.drawString(String.valueOf(pilot.getGunnery()), 295+leftmargin, topmargin+132) ;
            g2d.drawString(String.valueOf(pilot.getPiloting()), 365+leftmargin, topmargin+132) ;
        }*/

        g2d.drawString(Integer.toString(sub.getWalkMP()), 79+leftmargin, topmargin+144) ;
        if (!sub.hasWorkingMisc(MiscType.F_MASC, MiscType.S_SUPERCHARGER)) {
            g2d.drawString(Integer.toString(sub.getRunMP()), 79+leftmargin, topmargin+154) ;
        } else {
            int mascMP = sub.getRunMP();
            g2d.drawString(Integer.toString(sub.getRunMPwithoutMASC()) + " [" + mascMP + "]", 79+leftmargin, topmargin+154) ;
        }

        g2d.drawString(sub.getMovementModeAsString(), 90+leftmargin, topmargin+165) ;

        String engineName = "Fusion Engine";

        switch (sub.getEngine().getEngineType()) {
            case Engine.COMBUSTION_ENGINE:
                engineName = "I.C.E.";
                break;
            case Engine.LIGHT_ENGINE:
                engineName = "Light Fusion Engine";
                break;
            case Engine.XL_ENGINE:
                engineName = "XL Fusion Engine";
                break;
            case Engine.XXL_ENGINE:
                engineName = "XXL Fusion Engine";
                break;
            case Engine.COMPACT_ENGINE:
                engineName = "Compact Fusion Engine";
                break;
            case Engine.FUEL_CELL:
                engineName = "Fuel Cell Engine";
                break;
            default:
                break;
        }

        g2d.drawString(engineName, 79f+leftmargin, topmargin+176.5f) ;

        if (sub.getWeight() >= 5) {
            int tonnage = (int) Math.ceil(sub.getWeight());

            g2d.drawString(Integer.toString(tonnage), 177+leftmargin, topmargin+133) ;
        } else {
            // DecimalFormatSymbols unusualSymbols =
            // new DecimalFormatSymbols();
            // unusualSymbols.setDecimalSeparator('.');
            // unusualSymbols.setGroupingSeparator(',');
            // DecimalFormat myFormatter = new DecimalFormat("#.###",
            // unusualSymbols);
            g2d.drawString(String.format("%1$,d", sub.getWeight()), 177+leftmargin, topmargin+134) ;
        }

        int nextDataLine = 153 + topmargin;
        int startLine = 188 + leftmargin;
        int lineFeed = 8;

        switch (sub.getTechLevel()) {

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

        if (sub.isMixedTech()) {
            if (sub.isClan()) {
                techBase = "Mixed Tech (Clan)";
            } else {
                techBase = "Mixed Tech (I.S.)";
            }
        } else if (sub.isClan()) {
            techBase = "Clan";
        }
        g2d.drawString(techBase, 178+leftmargin, topmargin+144) ;

        if ((sub.getSource() != null) && (sub.getSource().trim().length() > 0)) {
            String sourceFluff = "Era: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sourceFluff, 142 + leftmargin, nextDataLine);

            font = UnitUtil.getNewFont(g2d, sub.getSource(), false, 51, 8.0f);
            g2d.setFont(font);

            g2d.drawString(sub.getSource(), 178 + leftmargin, nextDataLine);

        } else {
            String yearFluff = "Year: ";
            font = UnitUtil.deriveFont(true, 8.0f);
            g2d.setFont(font);

            g2d.drawString(yearFluff, 143 + leftmargin, nextDataLine);

            font = UnitUtil.deriveFont(8.0f);
            g2d.setFont(font);

            g2d.drawString(String.format("%1$s", sub.getYear()), 177+ leftmargin, nextDataLine);

        }

        // g2d.drawString(Integer.toString(sub.getYear()), 188+leftmargin, topmargin+155) ;

        // Cost/BV
        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // unusualSymbols.setGroupingSeparator(',');
        // DecimalFormat myFormatter = new DecimalFormat("#,###",
        // unusualSymbols);
        double bv = sub.calculateBattleValue(true, true);
        if (bv != -1) {
            font = UnitUtil.deriveFont(true, 8);
            g2d.setFont(font);
            g2d.drawString("BV: ", 30+leftmargin, topmargin+368) ;
            font = UnitUtil.deriveFont(false, 8);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$,d", sub.calculateBattleValue(true, true)), 45+leftmargin, topmargin+368) ;
        }

        // myFormatter = new DecimalFormat("#,###.##", unusualSymbol);
        // g2d.drawString(myFormatter.format(sub.getCost(true)) + " C-bills",
        // 52+leftmargin, topmargin+357) ;

        if (UnitUtil.hasBAR(sub)) {
            font = UnitUtil.deriveFont(true, 9.0f);
            g2d.drawString("BAR: " + UnitUtil.getLowestBARRating(sub), 400+leftmargin, topmargin+64) ;
        }

        g2d.setFont(UnitUtil.deriveFont(true, 7.0f));

        g2d.drawString(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), leftmargin+42, topmargin+770);

    }

    private void printArmor(Graphics2D g2d) {

        // Armor
        Font font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        font = UnitUtil.deriveFont(true, 11.0f);
        g2d.setFont(font);
        ImageHelper.printCenterString(g2d, ImageHelperVehicle.getVehicleArmorTypeString(sub), g2d.getFont(), 478+leftmargin, topmargin+48);
        //g2d.drawString(ImageHelperVehicle.getVehicleArmorTypeString(sub), 463+leftmargin, topmargin+48);
        font = UnitUtil.deriveFont(true, 9.0f);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(sub.getArmor(Tank.LOC_FRONT)), 476+leftmargin, topmargin+55.f);
        ImageHelper.printRotatedText(g2d, Integer.toString(sub.getArmor(Tank.LOC_RIGHT)), 90, 568+leftmargin, topmargin+374);
        ImageHelper.printRotatedText(g2d, Integer.toString(sub.getArmor(Tank.LOC_LEFT)), 270, 397+leftmargin, topmargin+316);
        g2d.drawString(Integer.toString(sub.getArmor(Tank.LOC_REAR)), 475+leftmargin, topmargin+665.5f);

        if (sub.getOInternal(Tank.LOC_TURRET) > 0) {
            g2d.drawString(Integer.toString(sub.getArmor(Tank.LOC_TURRET)), 474+leftmargin, topmargin+398.5f);
        }
    }

    private void printWeaponsNEquipment(Graphics2D g2d) {

        ImageHelperVehicle.printTankWeaponsNEquipment(sub, g2d, 3, 14);

    }

    private void printExtraFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn =
            { 442, 90 };
        float[] middleColumn =
            { 498, 125 };
        float[] bottomColumn =
            { 493, 136.2f };
        float[] pipShift =
            { 6, 6 };

        float[][] extraArmor =
            {
                { 429, 90 },
                { 429, 97 },
                { 518, 90 },
                { 518, 97 },
                { 436f, 93.5f },
                { 436f, 100.5f },
                { 513f, 93.5f },
                { 513f, 100.5f },
                { 513f, 107.5f },
                { 436f, 107.5f } };

        if (totalArmor < 1) {
            return;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 72; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 12) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        pipShift[0] *= -1;
        for (int pos = 1; pos <= 16; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
            if ((pos % 8) == 0) {
                middleColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                middleColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 18; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 6) == 0) {
                bottomColumn[1] += pipShift[1] - 0.5f;
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        for (int pos = 0; pos < 10; pos++) {
            pipPlotter.add(new float[]
                { extraArmor[pos][0], extraArmor[pos][1] });
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    @SuppressWarnings("unused")
    private void printFrontArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn =
            { 444, 90 };
        float[] middleColumn =
            { 500, 125 };
        float[] bottomColumn =
            { 493, 132 };
        float[] pipShift =
            { 7, 7 };

        float[][] extraArmor =
            {
                { 430, 90 },
                { 430, 97 },
                { 519, 90 },
                { 519, 97 },
                { 437f, 93.5f },
                { 437f, 100.5f },
                { 513f, 93.5f },
                { 513f, 100.5f },
                { 513f, 107.5f },
                { 437f, 107.5f } };

        if (totalArmor < 1) {
            return;
        }

        if (totalArmor > 86) {
            printExtraFrontArmor(g2d, totalArmor, secondImage, hasModularArmor);
            return;
        }

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 50; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
            if ((pos % 10) == 0) {
                topColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                topColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 8; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 18; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 6) == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }

        for (int pos = 1; pos <= 10; pos++) {
            pipPlotter.add(new float[]
                { extraArmor[pos - 1][0], extraArmor[pos - 1][1] });
        }

        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    @SuppressWarnings("unused")
    private void printRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {

        if (totalArmor > 51) {
            printExtraRearArmor(g2d, totalArmor, secondImage, hasModularArmor);
            return;
        }

        int[] topColumn = new int[]
            { 422, 291 };
        int[] middleColumn = new int[]
            { 422, 298 };
        int[] bottomColumn = new int[]
            { 422, 305 };
        int[] pipShift = new int[]
            { 7, 7 };

        int pips = 17;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        pips = 17;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        pips = 17;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);

    }

    private void printExtraRearArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        int[] topColumn = new int[]
            { 420, 291 };
        int[] middleColumn = new int[]
            { 420, 298 };
        int[] bottomColumn = new int[]
            { 420, 305 };
        int[] pipShift = new int[]
            { 5, 5 };

        int pips = 24;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        pips = 24;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { middleColumn[0], middleColumn[1] });
            middleColumn[0] += pipShift[0];
        }

        pips = 24;
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
        }

        pipPlotter.add(new float[]
            { 482, 312 });

        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, 7.0f, hasModularArmor);

    }

    @SuppressWarnings("unused")
    private void printTurretArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {

        if (totalArmor < 1) {
            return;
        }


        float[] topColumn = new float[]
            { 458f, 233f };
        float[] bottomColumn = new float[]
            { 451.5f, 240f };
        float[] pipShift = new float[]
            { 6f, 6f };
        // float fontSize = 8.0f;

        int pips = 7;

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= pips; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });
            topColumn[0] += pipShift[0];
        }

        for (int pos = 1; pos <= 45; pos++) {
            pipPlotter.add(new float[]
                { bottomColumn[0], bottomColumn[1] });
            bottomColumn[0] += pipShift[0];
            if ((pos % 9) == 0) {
                bottomColumn[1] += pipShift[1];
                pipShift[0] *= -1;
                bottomColumn[0] += pipShift[0];
            }
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    @SuppressWarnings("unused")
    private void printLeftArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn = new float[]
            { 418.5f, 92.5f };
        float[] pipShift = new float[]
            { 7, 7 };

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 90; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });

            switch (pos) {
                case 1:
                case 2:
                    topColumn[1] += pipShift[1];
                    break;
                case 4:
                case 6:
                case 9:
                case 12:
                case 21:
                case 27:
                case 46:
                case 49:
                case 52:
                case 70:
                case 74:
                case 85:
                case 88:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 78:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= 1.3;
                    break;
                case 82:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] += 1.3;
                    break;
                case 43:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] += pipShift[0];
                    break;
                case 39:
                    topColumn[1] += pipShift[1];
                    pipShift[0] = Math.abs(pipShift[0]);
                    topColumn[0] -= pipShift[0] * 5.2;
                    break;
                case 55:
                    topColumn[1] += pipShift[1];
                    topColumn[0] -= pipShift[0] * 2.2;
                    break;
                case 58:
                case 60:
                case 62:
                case 64:
                    topColumn[1] += pipShift[1];
                    topColumn[0] -= pipShift[0] * 1.25;
                    break;
                case 66:
                    topColumn[1] += pipShift[1] * 1.3;
                    topColumn[0] -= pipShift[0];
                    break;
                case 16:
                case 33:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= pipShift[0] * .5;
                    break;
                case 79:
                case 81:
                case 87:
                    topColumn[0] += pipShift[0] * .85;
                    break;
                default:
                    topColumn[0] += pipShift[0];
                    break;
            }

        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    @SuppressWarnings("unused")
    private void printRightArmor(Graphics2D g2d, int totalArmor, boolean secondImage, boolean hasModularArmor) {
        float[] topColumn = new float[]
            { 530f, 92 };
        float[] pipShift = new float[]
            { 7, 7 };

        Vector<float[]> pipPlotter = new Vector<float[]>(20);
        for (int pos = 1; pos <= 90; pos++) {
            pipPlotter.add(new float[]
                { topColumn[0], topColumn[1] });

            switch (pos) {
                case 1:
                case 2:
                    topColumn[1] += pipShift[1];
                    break;
                case 4:
                case 6:
                case 9:
                case 12:
                case 16:
                case 21:
                case 27:
                case 33:
                case 46:
                case 49:
                case 52:
                case 70:
                case 74:
                    // case 78:
                case 85:
                case 82:
                case 88:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    break;
                case 78:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += 1.5f;
                    pipShift[0] *= -.9f;
                    break;
                case 43:
                    topColumn[1] += pipShift[1];
                    pipShift[0] *= -1;
                    topColumn[0] -= pipShift[0];
                    break;
                case 39:
                    topColumn[1] += pipShift[1];
                    pipShift[0] = Math.abs(pipShift[0]);
                    topColumn[0] += pipShift[0] * 5.5;
                    break;
                case 55:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += pipShift[0] * 2.2;
                    break;
                case 58:
                case 60:
                case 62:
                case 64:
                    topColumn[1] += pipShift[1];
                    topColumn[0] += pipShift[0] * 1.25;
                    break;
                case 66:
                    topColumn[1] += pipShift[1] * 1.25;
                    topColumn[0] += pipShift[0];
                    break;
                case 89:
                    topColumn[0] -= pipShift[0] * .9;
                    break;
                default:
                    topColumn[0] -= pipShift[0];
                    break;
            }
        }
        ImageHelperVehicle.printArmorPoints(g2d, pipPlotter, totalArmor, hasModularArmor);
    }

    @SuppressWarnings("unused")
    private void printFrontStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] topColumn = new int[]
            { 462, 148 };
        int[] bottomColumn = new int[]
            { 462, 156 };
        int[] pipShift = new int[]
            { 7, 7 };

        int pips = Math.min(5, totalArmor);

        totalArmor -= pips;
        topColumn[0] += pipShift[0] * ((5 - pips) / 2);
        for (int pos = 1; pos <= pips; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, topColumn[0], topColumn[1]);
            topColumn[0] += pipShift[0];
        }

        bottomColumn[0] += pipShift[0] * ((5 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, bottomColumn[0], bottomColumn[1]);
            bottomColumn[0] += pipShift[0];
        }
    }

    @SuppressWarnings("unused")
    private void printTurretStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {

        float[] leftStart = new float[]
                { 447, 287 };
        float[] leftEnd = new float[]
                { 447, 342 };
        float[] midStart = new float[]
                { 454, 287 };
        float[] midEnd = new float[]
                { 454, 342 };
        float[] rightStart = new float[]
                { 461, 287 };
        float[] rightEnd = new float[]
                { 461, 342 };

        int pipsPerLine = totalArmor/3;
        int rest = totalArmor%3;
        Vector<float[]> leftPips;
        Vector<float[]> rightPips;
        Vector<float[]> midPips;
        if (rest == 2) {
            leftPips = ImageHelper.getPointsAlongLine(leftStart, leftEnd, pipsPerLine+1);
            midPips = ImageHelper.getPointsAlongLine(midStart, midEnd, pipsPerLine);
            rightPips = ImageHelper.getPointsAlongLine(rightStart, rightEnd, pipsPerLine+1);
        } else if (rest == 1) {
            leftPips = ImageHelper.getPointsAlongLine(leftStart, leftEnd, pipsPerLine);
            midPips = ImageHelper.getPointsAlongLine(midStart, midEnd, pipsPerLine+1);
            rightPips = ImageHelper.getPointsAlongLine(rightStart, rightEnd, pipsPerLine);
        } else {
            leftPips = ImageHelper.getPointsAlongLine(leftStart, leftEnd, pipsPerLine);
            midPips = ImageHelper.getPointsAlongLine(midStart, midEnd, pipsPerLine);
            rightPips = ImageHelper.getPointsAlongLine(rightStart, rightEnd, pipsPerLine);
        }
        for (float[] pip : leftPips) {
            ImageHelperVehicle.drawTankISPip(g2d, pip[0], pip[1]);
        }
        for (float[] pip : midPips) {
            ImageHelperVehicle.drawTankISPip(g2d, pip[0], pip[1]);
        }
        for (float[] pip : rightPips) {
            ImageHelperVehicle.drawTankISPip(g2d, pip[0], pip[1]);
        }
    }

    @SuppressWarnings("unused")
    private void printLeftStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 444, 164 };
        int[] pipShift = new int[]
            { 2, 7 };

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] -= pipShift[0];
            column[1] += pipShift[1];
        }
    }

    @SuppressWarnings("unused")
    private void printRightStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 505, 164 };
        int[] pipShift = new int[]
            { 2, 7 };

        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
            column[1] += pipShift[1];
        }
    }

    @SuppressWarnings("unused")
    private void printRearStruct(Graphics2D g2d, int totalArmor, boolean secondImage) {
        int[] column = new int[]
            { 445, 275 };
        int[] pipShift = new int[]
            { 7, 7 };

        column[0] += pipShift[0] * ((10 - totalArmor) / 2);
        for (int pos = 1; pos <= totalArmor; pos++) {
            ImageHelperVehicle.drawTankISPip(g2d, column[0], column[1]);
            column[0] += pipShift[0];
        }
    }

    private void printTankImage(Graphics2D g2d) {

        Image img = ImageHelper.getFluffImage(sub, ImageHelper.imageVehicle);
        int width = Math.min(148, img.getWidth(null));
        int height = Math.min(99, img.getHeight(null));
        int drawingX = 247 + ((148 - width) / 2);
        int drawingY = 280 + ((99 - height) / 2);
        g2d.drawImage(img, drawingX, drawingY, width, height, Color.BLACK, null);

    }

}