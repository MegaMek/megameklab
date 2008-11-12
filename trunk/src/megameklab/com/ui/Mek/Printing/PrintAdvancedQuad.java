/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek.Printing;

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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.ATMWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.SRMWeapon;
import megameklab.com.util.UnitUtil;

public class PrintAdvancedQuad implements Printable {

    protected Image awtImage = null;
    private Mech mech = null;
    private ArrayList<Mech> mechList;

    private Dimension fillRec = new Dimension(8,8);
    private Dimension fillRecArc = new Dimension(4,4);

    private Mounted startingMount = null;
    private int startMountx = 0;
    private int startMounty = 0;
    private int endMountx = 0;
    private int endMounty = 0;
    
    public PrintAdvancedQuad(Image image, ArrayList<Mech>list) {
        awtImage = image;
        mechList = list;

        System.out.println("Width: " + awtImage.getWidth(null));
        System.out.println("Height: " + awtImage.getHeight(null));
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, awtImage, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    public void printImage(Graphics2D g2d, Image image, PageFormat pageFormat) {
        //System.out.println("printImage(Graphics2D g2d, Image image)");
        if ( g2d == null )
            return;
        
        //g2d.drawImage(image, 2, 0, (int)pageFormat.getImageableWidth(), (int)pageFormat.getImageableHeight(), null);
        g2d.drawImage(image, 18, 18, 558, 738, null);
        

        printMechData(g2d);
        printHeatSinks(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
        printLACrits(g2d);
        printRACrits(g2d);
        printCTCrits(g2d);
        printLTCrits(g2d);
        printRTCrits(g2d);
        printHeadCrits(g2d);
        printLLCrits(g2d);
        printRLCrits(g2d);


        // Armor Pips
        printLAArmor(g2d);
        printRAArmor(g2d);
        printLTArmor(g2d);
        printRTArmor(g2d);
        printCTArmor(g2d);
        printLLArmor(g2d);
        printRLArmor(g2d);
        printLTRArmor(g2d);
        printRTRArmor(g2d);
        printCTRArmor(g2d);
        printHeadArmor(g2d);
        
        //Internal Pips
        printLAStruct(g2d);
        printRAStruct(g2d);
        printLTStruct(g2d);
        printRTStruct(g2d);
        printCTStruct(g2d);
        printHeadStruct(g2d);
        printLLStruct(g2d);
        printRLStruct(g2d);
        
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.scale(pageFormat.getImageableWidth(),pageFormat.getImageableHeight());

    }

    private void printMechData(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 11);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis() + " " + mech.getModel(), 49, 119);
        g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
        g2d.drawString(Integer.toString(mech.getRunMP()), 79, 154);
        g2d.drawString(Integer.toString(mech.getWalkMP() * 2), 79, 164);
        g2d.drawString(Integer.toString(mech.getJumpMP()), 79, 174);
        g2d.drawString(Float.toString(mech.getWeight()), 173, 134);

        switch (mech.getCockpitType()) {
        case Mech.COCKPIT_STANDARD:
            g2d.fillRoundRect(294, 197, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.COCKPIT_SMALL:
            g2d.fillRoundRect(294, 206, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.COCKPIT_DUAL:
            g2d.fillRoundRect(367, 188, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.COCKPIT_COMMAND_CONSOLE:
            g2d.fillRoundRect(367, 197, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.COCKPIT_TORSO_MOUNTED:
            g2d.fillRoundRect(367, 206, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        }

        switch (mech.getArmorType()) {
        case EquipmentType.T_ARMOR_STANDARD:
            g2d.fillRoundRect(367, 241, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            g2d.fillRoundRect(367, 250, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_LAMELLOR_FERRO_CARBIDE:
            g2d.fillRoundRect(367, 259, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_LIGHT_FERRO:
            g2d.fillRoundRect(367, 268, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_HEAVY_FERRO:
            g2d.fillRoundRect(367, 277, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_STEALTH:
            g2d.fillRoundRect(367, 286, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_HARDENED:
            g2d.fillRoundRect(367, 301, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_REACTIVE:
            g2d.fillRoundRect(367, 311, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_REFLECTIVE:
            g2d.fillRoundRect(367, 321, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_ARMOR_COMMERCIAL:
            g2d.fillRoundRect(367, 337, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        default:
            g2d.fillRoundRect(367, 241, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;

        }

        switch (mech.getStructureType()) {
        case EquipmentType.T_STRUCTURE_STANDARD:
            g2d.fillRoundRect(297, 251, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_STRUCTURE_ENDO_STEEL:
            g2d.fillRoundRect(297, 260, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_STRUCTURE_REINFORCED:
            g2d.fillRoundRect(297, 278, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_STRUCTURE_COMPOSITE:
            g2d.fillRoundRect(297, 287, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case EquipmentType.T_STRUCTURE_INDUSTRIAL:
            g2d.fillRoundRect(297, 295, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;

        }

        switch ( mech.getGyroType() ) {
        case Mech.GYRO_STANDARD:
            g2d.fillRoundRect(297, 311, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.GYRO_COMPACT:
            g2d.fillRoundRect(297, 320, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.GYRO_HEAVY_DUTY:
            g2d.fillRoundRect(297, 329, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        case Mech.GYRO_XL:
            g2d.fillRoundRect(297, 338, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
            break;
        }
        
        if ( UnitUtil.hasCompactHeatSinks(mech) ) {
            g2d.fillRoundRect(367, 355, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }else if ( mech.hasLaserHeatSinks() ) {
            g2d.fillRoundRect(367, 364, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }else if ( mech.hasDoubleHeatSinks() ) {
            g2d.fillRoundRect(297, 361, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }else {
            g2d.fillRoundRect(297, 352, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }
        
        if (mech.isClan()) {
            g2d.fillRoundRect(209, 146, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        } else {
            g2d.fillRoundRect(209, 156, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }

        if (mech.isMixedTech()) {
            g2d.fillRoundRect(209, 166, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }
        
        // Cost/BV
        g2d.drawString(Integer.toString(mech.calculateBattleValue(true)), 159, 359);

        DecimalFormat myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost()) + " C", 54, 359);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        // Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()), 402, 594);
        if (mech.hasDoubleHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks() * 2), 424, 594);
        } else {
            g2d.drawString(Integer.toString(mech.heatSinks()), 424, 594);
        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = new Font("Eurostile Regular", Font.BOLD, 8);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_HEAD)), 485, 47);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT)), 393, 138);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT)), 553, 138);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT)), 475, 209);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LARM)), 401, 309);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RARM)), 549, 310);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LLEG)), 448, 297);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RLEG)), 501, 300);
        //Rear
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT,true)), 406, 357);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT,true)), 506, 368);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT,true)), 542, 357);
        //Internal
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LT)), 400, 418);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RT)), 521, 418);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LARM)), 398, 483);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RARM)), 523, 484);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_CT)), 459, 511);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LLEG)), 395, 532);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RLEG)), 526, 532);
    }

    private void printLACrits(Graphics2D g2d) {
        
        int lineStart = 60;
        int linePoint = 440;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LARM, lineStart, linePoint, lineFeed);
    }
    
    private void printRACrits(Graphics2D g2d) {
        
        int lineStart = 298;
        int linePoint = 440;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RARM, lineStart, linePoint, lineFeed);
    }
    
    private void printCTCrits(Graphics2D g2d) {
        
        int lineStart = 178;
        int linePoint = 469;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_CT, lineStart, linePoint, lineFeed);
    }
    
    private void printLTCrits(Graphics2D g2d) {
        
        int lineStart = 60;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LT, lineStart, linePoint, lineFeed);
    }
    
    private void printRTCrits(Graphics2D g2d) {
        
        int lineStart = 298;
        int linePoint = 545;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RT, lineStart, linePoint, lineFeed);
    }
    
    private void printHeadCrits(Graphics2D g2d) {
        
        int lineStart = 178;
        int linePoint = 401;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_HEAD, lineStart, linePoint, lineFeed);
    }
    
    private void printLLCrits(Graphics2D g2d) {
        
        int lineStart = 60;
        int linePoint = 683;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_LLEG, lineStart, linePoint, lineFeed);
    }
    
    private void printRLCrits(Graphics2D g2d) {
        
        int lineStart = 298;
        int linePoint = 683;
        int lineFeed = 8;

        printLocationCriticals(g2d, Mech.LOC_RLEG, lineStart, linePoint, lineFeed);
    }
    
    private void printWeaponsNEquipment(Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 109;
        int heatPoint = 128;
        int damagePoint = 145;
        int minPoint = 167;
        int shtPoint = 181;
        int medPoint = 199;
        int longPoint = 215;
        int linePoint = 210;

        int lineFeed = 11;

        ArrayList<Hashtable<String, equipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, equipmentInfo>>(Mech.LOC_LLEG + 1);

        for (int pos = 0; pos <= Mech.LOC_LLEG; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, equipmentInfo>());
        }

        for (Mounted eq : mech.getEquipment()) {

            if (eq.getType() instanceof AmmoType || eq.getLocation() == Mech.LOC_NONE || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Hashtable<String, equipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            if (eqHash.containsKey(eq.getName())) {
                equipmentInfo eqi = eqHash.get(eq.getName());

                if (eq.getType().getTechLevel() != eqi.techLevel) {
                    eqi = new equipmentInfo(eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(eq.getName(), eqi);
            } else {
                equipmentInfo eqi = new equipmentInfo(eq);
                eqHash.put(eq.getName(), eqi);
            }

        }

        Font font = new Font("Eurostile Bold", Font.BOLD, 10);
        g2d.setFont(font);

        for (int pos = Mech.LOC_HEAD; pos <= Mech.LOC_LLEG; pos++) {

            Hashtable<String, equipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            for (equipmentInfo eqi : eqHash.values()) {
                if (count >= 12) {
                    break;
                }
                font = new Font("Eurostile Bold", Font.BOLD, 8);
                g2d.setFont(font);
                
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                if (eqi.isRear) {
                    name += "(R)";
                }

                if (name.length() > 70) {
                    font = new Font("Eurostile Bold", Font.BOLD, 1);
                    g2d.setFont(font);
                }else if (name.length() > 60) {
                    font = new Font("Eurostile Bold", Font.BOLD, 2);
                    g2d.setFont(font);
                }else if (name.length() > 50) {
                    font = new Font("Eurostile Bold", Font.BOLD, 3);
                    g2d.setFont(font);
                }else if (name.length() > 40) {
                    font = new Font("Eurostile Bold", Font.BOLD, 4);
                    g2d.setFont(font);
                }else if (name.length() > 30) {
                    font = new Font("Eurostile Bold", Font.BOLD, 5);
                    g2d.setFont(font);
                }else if (name.length() > 20) {
                    font = new Font("Eurostile Bold", Font.BOLD, 6);
                    g2d.setFont(font);
                }else if (name.length() >= 10) {
                    font = new Font("Eurostile Bold", Font.BOLD, 7);
                    g2d.setFont(font);
                }


                g2d.drawString(name, typePoint, linePoint);
                font = new Font("Eurostile Bold", Font.BOLD, 8);
                g2d.setFont(font);
                
                g2d.drawString(mech.getLocationAbbr(pos), locPoint, linePoint);
                if (eqi.isWeapon) {
                    g2d.drawString(Integer.toString(eqi.heat), heatPoint, linePoint);
                    g2d.drawString(eqi.damage, damagePoint, linePoint);
                    g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                    g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                    g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                    g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                }
                linePoint += lineFeed;
                count++;
            }
        }

    }

    public void print() {

        try {
            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.defaultPage();
                paper.setImageableArea(0, 0, 612, 792);
                paper.setSize(612, 792);
                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                pj.setPrintable(this, pageFormat);
                for ( Mech currentMech : mechList ) {
                    
                    this.mech = currentMech;
                    pj.setJobName(mech.getChassis() + " " + mech.getModel());
    
                    pj.print();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class equipmentInfo {
        public int count = 0;
        public String name = "";
        public int minRange = 0;
        public int shtRange = 0;
        public int medRange = 0;
        public int longRange = 0;
        public String damage = "";
        public int heat = 0;
        public int techLevel = TechConstants.T_INTRO_BOXSET;
        public boolean isWeapon = false;
        public boolean isRear = false;

        public equipmentInfo(Mounted mount) {
            this.name = mount.getName();
            this.count = 1;
            this.techLevel = mount.getType().getTechLevel();
            this.isRear = mount.isRearMounted();

            if (mount.getType() instanceof WeaponType) {
                WeaponType weapon = (WeaponType) mount.getType();
                this.minRange = Math.max(0, weapon.minimumRange);
                this.isWeapon = true;
                if (weapon.getDamage() < 0) {
                    if (weapon instanceof SRMWeapon) {
                        damage = "2/hit";
                    } else if (weapon instanceof LRMWeapon) {
                        damage = "1/hit";
                    } else if (weapon instanceof ATMWeapon) {
                        damage = "3/2/1";
                    } else {
                        damage = Integer.toString(weapon.getRackSize());
                    }
                } else {
                    this.damage = Integer.toString(weapon.getDamage());
                }
                this.shtRange = weapon.shortRange;
                this.medRange = weapon.mediumRange;
                this.longRange = weapon.longRange;
                this.heat = weapon.getHeat();
            }
        }
    }
    
    private void printRLArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(504, 136);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_RLEG); 
        int pips = Math.min(4, totalArmor); 
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.height += pipShift.height;
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        topColumn.width -= pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 2 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            
            if ( pos % 8 == 0 ) {
                topColumn.height++;
            }
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        topColumn.width += pipShift.width*2;
        pipShift.width *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 4 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            
        }
    }

    private void printLLArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(448, 136);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_LLEG); 
        int pips = Math.min(4, totalArmor); 
        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.height += pipShift.height;
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        topColumn.width -= pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 2 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            
            if ( pos % 8 == 0 ) {
                topColumn.height++;
            }
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(30, totalArmor);

        totalArmor -= pips;

        topColumn.width += pipShift.width*2;
        pipShift.width *= -1;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 4 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            
        }
    }

    private void printLAArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension centerColumn = new Dimension(422, 141);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getArmor(Mech.LOC_LARM);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(centerColumn.width, centerColumn.height, circle.width, circle.width);
            centerColumn.width += pipShift.width;
            if (pos % 2 == 0) {
                centerColumn.height += pipShift.height;
                pipShift.width *= -1;
                centerColumn.width += pipShift.width;
                centerColumn.width -= 1;
            }
            
            if ( pos % 4 == 0 ) {
                centerColumn.width += 1;
            }
            
            if ( pos % 8 == 0) {
                centerColumn.height += 1;
            }
        }

    }

    private void printRAArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension centerColumn = new Dimension(525, 142);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getArmor(Mech.LOC_RARM);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(centerColumn.width, centerColumn.height, circle.width, circle.width);
            centerColumn.width += pipShift.width;
            if (pos % 2 == 0) {
                centerColumn.height += pipShift.height;
                pipShift.width *= -1;
                centerColumn.width += pipShift.width;
                centerColumn.width += 1;
            }
            
            if ( pos % 4 == 0 ) {
                centerColumn.width -= 1;
            }
            
            if ( pos % 8 == 0) {
                centerColumn.height += 1;
            }
        }

    }

    private void printLTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(424, 65);
        Dimension pipShift = new Dimension(5,7);
    
        int pips = mech.getArmor(Mech.LOC_LT);
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            if ( pos == 40 ) {
                topColumn.width += pipShift.width *4;
                pipShift.width *= -1;
            }
        }
    
    }

    private void printLTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(451, 306);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_LT,true));

        int pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        pipShift.width *= -1;
        topColumn.width += pipShift.width;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        //topColumn.width += pipShift.width;
        pipShift.width *= -1;
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        pipShift.width *= -1;
        topColumn.width += pipShift.width;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if ( pos % 5 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        
        pips = Math.min(8, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 4 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(3, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
    }

    private void printRTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(507, 65);
        Dimension pipShift = new Dimension(5,7);

        int pips = mech.getArmor(Mech.LOC_RT);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
    }

    private void printRTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(497, 307);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = Math.min(30, mech.getArmor(Mech.LOC_RT,true));

        int pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        //pipShift.width *= -1;
        topColumn.width -= pipShift.width*2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        topColumn.width -= pipShift.width*3;
        //pipShift.width *= -1;
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        topColumn.height += pipShift.height;
        //pipShift.width *= -1;
        topColumn.width -= pipShift.width*4;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if ( pos % 5 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        
        pips = Math.min(8, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            
            if ( pos % 4 == 0 ) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
        
        if ( totalArmor  < 1 ) {
            return;
        }
        
        pips = Math.min(3, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
        }
    }

    private void printCTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(462, 102);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getArmor(Mech.LOC_CT);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 6 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
            if ( pos == 60 ) {
                topColumn.width += pipShift.width*2;
            }
        }
    }

    private void printHeadArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);

        Dimension head = new Dimension(466, 79);
        Dimension pipShift = new Dimension(7, 6);

        int pips = mech.getArmor(Mech.LOC_HEAD);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(head.width, head.height, circle.width, circle.width);
            head.width += pipShift.width;
            if (pos  == 4 || pos == 7 ) {
                head.height += pipShift.height;
                pipShift.width *= -1;
                head.width += pipShift.width;
                head.width += pipShift.width/2;
            }

        }
        
    }

    private void printCTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(465, 304);
        Dimension pipShift = new Dimension(6, 6);

        int pips = Math.min(45, mech.getArmor(Mech.LOC_CT,true));

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
    }
    
    private void printLAStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(427, 467);
        Dimension pipShift = new Dimension(4, 4);
    
        int totalArmor = mech.getInternal(Mech.LOC_LARM);
    
        int pips = Math.min(21, totalArmor);
    
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;
    
            if ( pos % 4 == 0 ) {
                column.width -= 1;
            }
            
        }
        
    }

    private void printLLStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(445, 461);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);
        
        int pips = Math.min(21, totalArmor);
    
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;
        }
        
    }

    private void printRLStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(480, 461);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = Math.min(21, totalArmor);
        
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            column.width += pipShift.width;
            pipShift.width *= -1;
        }
    }

    private void printRAStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(498, 467);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = Math.min(21, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width -= pipShift.width;

            if ( pos % 4 == 0 ) {
                column.width += 1;
            }
            
        }
        
    }

    private void printLTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(424, 412);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getInternal(Mech.LOC_LT);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 4 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
        }

    }
    
    private void printRTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(484, 412);
        Dimension pipShift = new Dimension(6, 6);

        int pips = mech.getInternal(Mech.LOC_RT);
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 4== 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
            if ( pos == 20 ) {
                column.width += pipShift.width*3;
            }
            
        }
        
    }

    private void printCTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(454, 429);
        Dimension pipShift = new Dimension(6,5);

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = Math.min(28, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 4 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(3, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width/2;
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;
        }
    }
    
    private void printHeadStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        
        g2d.drawOval(462, 410, circle.width, circle.width);
        g2d.drawOval(458, 417, circle.width, circle.width);
        g2d.drawOval(467, 417, circle.width, circle.width);
    }
    
    private void setCritConnection(Mounted m, int startx, int starty, int endx, int endy, Graphics2D g2d) {
        if ( m == null ) {
            printCritConnection(g2d, startMountx, startMounty, endMountx, endMounty );
            startingMount = null;
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        }
        else if ( startingMount == null && UnitUtil.getCritsUsed(mech, m.getType()) > 1) {
            startingMount = m;
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if ( !m.equals(startingMount) ){
            printCritConnection(g2d, startMountx, startMounty, endMountx, endMounty );
            if ( UnitUtil.getCritsUsed(mech, m.getType()) > 1 ) {
                startingMount = m;
            } else {
                startingMount = null;
            }
            startMountx = startx;
            startMounty = starty;
            endMountx = endx;
            endMounty = endy;
        } else if ( m.equals(startingMount) ) {
            endMounty = endy;
        }

    }
    
    private void printCritConnection(Graphics2D g2d, int startx, int starty, int endx, int endy) {
        if ( starty == endy) {
            return;
        }
        
        g2d.drawLine(startx-1, starty, startx-4, starty);
        g2d.drawLine(startx-4, starty, endx-4, endy);
        g2d.drawLine(endx-1, endy, endx-4, endy);
    }
    
    private void printLocationCriticals(Graphics2D g2d, int location, int lineStart, int linePoint, int lineFeed) {
        Font font;
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            font = new Font("Eurostile Bold", Font.BOLD, 8);
            g2d.setFont(font);
            CriticalSlot cs = mech.getCritical(location, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
                setCritConnection(null, lineStart, linePoint-lineFeed/2, lineStart, linePoint - lineFeed/2, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
                setCritConnection(null, lineStart, linePoint-lineFeed/2, lineStart, linePoint - lineFeed/2, g2d);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                setCritConnection(m, lineStart, linePoint-lineFeed/2, lineStart, linePoint - lineFeed/2, g2d);

                StringBuffer critName = new StringBuffer(m.getName());
                
                if ( m.getType() instanceof AmmoType ) {
                    critName.append(" (");
                    critName.append(((AmmoType)m.getType()).getShots());
                    critName.append(")");
                }
                if (critName.length() >= 44) {
                    font = new Font("Eurostile Bold", Font.BOLD, 1);
                    g2d.setFont(font);
                }else if (critName.length() >= 40) {
                    font = new Font("Eurostile Bold", Font.BOLD, 2);
                    g2d.setFont(font);
                }else if (critName.length() >= 36) {
                    font = new Font("Eurostile Bold", Font.BOLD, 3);
                    g2d.setFont(font);
                }else if (critName.length() >= 32) {
                    font = new Font("Eurostile Bold", Font.BOLD, 4);
                    g2d.setFont(font);
                }else if (critName.length() >= 28) {
                    font = new Font("Eurostile Bold", Font.BOLD, 5);
                    g2d.setFont(font);
                }else if (critName.length() >= 24) {
                    font = new Font("Eurostile Bold", Font.BOLD, 6);
                    g2d.setFont(font);
                }else if (critName.length() >= 20) {
                    font = new Font("Eurostile Bold", Font.BOLD, 7);
                    g2d.setFont(font);
                }
                
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;
            
            if ( slot > 0 && slot % 2 == 0 ) {
                linePoint++;
            }
            
            if ( slot == 5 ) {
                linePoint += lineFeed/2;
            }

        }
        setCritConnection(null, lineStart, linePoint-lineFeed/2, lineStart, linePoint - lineFeed/2, g2d);

    }

}