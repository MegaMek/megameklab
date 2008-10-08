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

package megameklab.com.ui.util;

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
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.ATMWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.SRMWeapon;

public class PrintMech implements Printable {

    protected Image awtImage = null;
    private Mech mech = null;
    
    public PrintMech (Image image, Mech unit) {
        awtImage = image;
        mech = unit;
        
        System.out.println("Width: " + awtImage.getWidth(null));
        System.out.println("Height: " + awtImage.getHeight(null));
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        if (awtImage != null) {
            printImage(g2d, awtImage);
            return Printable.PAGE_EXISTS;
        } else
            return Printable.NO_SUCH_PAGE;
    }

    public void printImage(Graphics2D g2d, Image image) {
        System.out.println("printImage(Graphics2D g2d, Image image)");
        if ((image == null) || (g2d == null))
            return;
        int x = 18;
        int y = 18;
        g2d.drawImage(image, x, y, 558, 738, null);

        printMechData(g2d);
        printHeatSinks(g2d);
        printArmor(g2d);
        printWeaponsNEquipment(g2d);
    }

    private void printMechData(Graphics2D g2d){
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis()+" "+mech.getModel(), 51, 118);
        g2d.drawString(Integer.toString(mech.getWalkMP()), 81, 143);
        g2d.drawString(Integer.toString(mech.getRunMP()), 81, 154);
        g2d.drawString(Integer.toString(mech.getJumpMP()), 81, 165);
        g2d.drawString(Float.toString(mech.getWeight()), 175, 133);

        if ( mech.isClan() ) {
            g2d.drawString("X", 208, 155);
        } else {
            g2d.drawString("X", 208, 165);
        }
        
        //Cost/BV
        g2d.drawString(Integer.toString(mech.calculateBattleValue(true)), 161, 349);

        DecimalFormat myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost())+" C", 56, 349);
    }
    
    private void printHeatSinks(Graphics2D g2d){
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        //Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()), 503, 598);
        if ( mech.hasDoubleHeatSinks() ) {
            g2d.drawString(Integer.toString(mech.heatSinks()*2), 524, 598);
            g2d.drawString("X", 530, 723);
        }else {
            g2d.drawString(Integer.toString(mech.heatSinks()), 524, 598);
            g2d.drawString("X", 530, 708);
        }
        
    }
    
    private void printArmor(Graphics2D g2d){
        //Armor
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_HEAD)), 490, 45);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT)), 438, 59);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT)), 514, 59);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT)), 478, 220);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LARM)), 402, 215);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RARM)), 550, 215);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LLEG)), 393, 271);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RLEG)), 558, 271);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT,true)), 406, 361);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT,true)), 484, 277);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT,true)), 549, 361);
        //Internal
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LT)), 436, 403);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RT)), 529, 403);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LARM)), 395, 478);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RARM)), 535, 480);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_CT)), 464, 510);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LLEG)), 407, 540);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RLEG)), 523, 540);
    }
    
    private void printWeaponsNEquipment(Graphics2D g2d){
        int qtyPoint = 30;
        int typePoint = 42;
        int locPoint = 113;
        int heatPoint = 132;
        int damagePoint = 149;
        int minPoint = 171;
        int shtPoint = 185;
        int medPoint = 203;
        int longPoint = 219;
        int linePoint = 206;
        
        int lineFeed = 11;
        
        ArrayList<Hashtable<String,equipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String,equipmentInfo>>(Mech.LOC_LLEG+1);

        for ( int pos = 0; pos <= Mech.LOC_LLEG; pos++){
            equipmentLocations.add(pos,new Hashtable<String,equipmentInfo>());
        }
        
        for ( Mounted eq : mech.getEquipment() ){
            
            if ( eq.getType() instanceof AmmoType || eq.getLocation() == Mech.LOC_NONE ){
                continue;
            }
            
            Hashtable<String,equipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());
            
            if ( eqHash.containsKey(eq.getName()) ){
                equipmentInfo eqi = eqHash.get(eq.getName());
                
                if ( eq.getType().getTechLevel() != eqi.techLevel ){
                    eqi = new equipmentInfo(eq);
                }else {
                    eqi.count++;
                }
                eqHash.put(eq.getName(),eqi);
            } else {
                equipmentInfo eqi = new equipmentInfo(eq);
                eqHash.put(eq.getName(), eqi );
            }

        }
        
       /* for ( Mounted eq : mech.getWeaponList() ){
            
            if ( eq.getLocation() == Mech.LOC_NONE ){
                continue;
            }
            
            Hashtable<String,equipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());
            
            if ( eqHash.containsKey(eq.getName()) ){
                equipmentInfo eqi = eqHash.get(eq.getName());
                
                if ( eq.getType().getTechLevel() != eqi.techLevel ){
                    eqi = new equipmentInfo(eq);
                }else {
                    eqi.count++;
                }
                eqHash.put(eq.getName(),eqi);
            } else {
                equipmentInfo eqi = new equipmentInfo(eq);
                eqHash.put(eq.getName(), eqi );
            }
        }*/

        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        
        for ( int pos = Mech.LOC_HEAD; pos <= Mech.LOC_LLEG; pos++){
            
            Hashtable<String,equipmentInfo> eqHash = equipmentLocations.get(pos);
            
            if ( eqHash.size() < 1 ){
                continue;
            }
            
            int count = 0;
            
            for ( equipmentInfo eqi : eqHash.values() ){ 
                if ( count >= 12 ){
                    break;
                }
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();
                
                if ( name.length() > 20 ){
                    name = name.substring(0,18)+"..";
                }
                if ( eqi.isRear ){
                    name += "(R)";
                }
                
                g2d.drawString(name, typePoint, linePoint);
                g2d.drawString(mech.getLocationAbbr(pos), locPoint, linePoint);
                if ( eqi.isWeapon ){
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

        try {            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.defaultPage();
                paper.setImageableArea(0, 0, 612, 792);
                paper.setSize(612, 792);
                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                pj.setPrintable(this, pageFormat);
                pj.setJobName(mech.getChassis()+" "+mech.getModel());
                
                pj.print();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private class equipmentInfo{
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
        
        public equipmentInfo(Mounted mount){
            this.name = mount.getName();
            this.count = 1;
            this.techLevel = mount.getType().getTechLevel();
            this.isRear = mount.isRearMounted();
            
            if ( mount.getType() instanceof WeaponType ){
                WeaponType weapon = (WeaponType)mount.getType();
                this.minRange = Math.max(0, weapon.minimumRange);
                this.isWeapon = true;
                if ( weapon.getDamage() < 0 ){
                    if ( weapon instanceof SRMWeapon ){
                        damage = "2/hit";
                    }else if ( weapon instanceof LRMWeapon ){
                        damage = "1/hit";
                    }else if ( weapon instanceof ATMWeapon ){
                        damage = "3/2/1";
                    }else {
                        damage = Integer.toString(weapon.getRackSize());
                    }
                }else {
                    this.damage = Integer.toString(weapon.getDamage());
                }
                this.shtRange = weapon.shortRange;
                this.medRange = weapon.mediumRange;
                this.longRange = weapon.longRange;
                this.heat = weapon.getHeat();
            }
        }
    }
}