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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
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
    private Dimension fillRec = new Dimension(9, 9);
    private Dimension fillRecArc = new Dimension(4, 4);

    public PrintMech(Image image, Mech unit) {
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
    }

    private void printMechData(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);

        g2d.drawString(mech.getChassis() + " " + mech.getModel(), 49, 119);
        g2d.drawString(Integer.toString(mech.getWalkMP()), 79, 144);
        g2d.drawString(Integer.toString(mech.getRunMP()), 79, 155);
        g2d.drawString(Integer.toString(mech.getJumpMP()), 79, 166);
        g2d.drawString(Float.toString(mech.getWeight()), 173, 134);

        if (mech.isClan()) {
            g2d.fillRoundRect(204, 147, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        } else {
            g2d.fillRoundRect(204, 157, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }

        // Cost/BV
        g2d.drawString(Integer.toString(mech.calculateBattleValue(true)), 159, 349);

        DecimalFormat myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost()) + " C", 54, 349);
    }

    private void printHeatSinks(Graphics2D g2d) {
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        // Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()), 497, 598);
        if (mech.hasDoubleHeatSinks()) {
            g2d.drawString(Integer.toString(mech.heatSinks() * 2), 520, 598);
            g2d.fillRoundRect(526, 716, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        } else {
            g2d.drawString(Integer.toString(mech.heatSinks()), 520, 598);
            g2d.fillRoundRect(526, 701, fillRec.width, fillRec.height, fillRecArc.width, fillRecArc.height);
        }

    }

    private void printArmor(Graphics2D g2d) {
        // Armor
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_HEAD)), 485, 47);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT)), 434, 61);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT)), 510, 61);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT)), 475, 222);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LARM)), 397, 217);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RARM)), 547, 217);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LLEG)), 390, 273);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RLEG)), 555, 273);
        // Rear
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT, true)), 402, 363);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT, true)), 480, 279);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT, true)), 545, 363);
        // Internal
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LT)), 432, 404);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RT)), 525, 404);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LARM)), 391, 479);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RARM)), 531, 481);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_CT)), 460, 511);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LLEG)), 403, 541);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RLEG)), 519, 541);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 408;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 12; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_LARM, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

            if (slot == 5) {
                linePoint += lineFeed - 1;
            }
        }
    }

    private void printRACrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 408;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 12; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_RARM, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

            if (slot == 5) {
                linePoint += lineFeed - 1;
            }
        }
    }

    private void printCTCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 469;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 12; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_CT, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

            if (slot == 5) {
                linePoint += lineFeed - 1;
            }
        }
    }

    private void printLTCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 545;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 12; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_LT, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

            if (slot == 5) {
                linePoint += lineFeed - 1;
            }
        }
    }

    private void printRTCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 545;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 12; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_RT, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

            if (slot == 5) {
                linePoint += lineFeed - 1;
            }
        }
    }

    private void printHeadCrits(Graphics2D g2d) {

        int lineStart = 174;
        int linePoint = 401;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 6; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_HEAD, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

        }
    }

    private void printLLCrits(Graphics2D g2d) {

        int lineStart = 56;
        int linePoint = 682;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 6; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_LLEG, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

        }
    }

    private void printRLCrits(Graphics2D g2d) {

        int lineStart = 292;
        int linePoint = 682;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.BOLD, 6);
        g2d.setFont(font);

        for (int slot = 0; slot < 6; slot++) {
            CriticalSlot cs = mech.getCritical(Mech.LOC_RLEG, slot);

            if (cs == null) {
                g2d.drawString("Roll Again", lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                g2d.drawString(mech.getSystemName(cs.getIndex()), lineStart, linePoint);
            } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted m = mech.getEquipment(cs.getIndex());
                StringBuffer critName = new StringBuffer(m.getName());
                if (critName.length() > 20) {
                    critName.setLength(20);
                    critName.append("...");
                }
                if (m.isRearMounted()) {
                    critName.append("(R)");
                }
                g2d.drawString(critName.toString(), lineStart, linePoint);
            }
            linePoint += lineFeed;

        }
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
        int linePoint = 206;

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

        Font font = new Font("Arial", Font.PLAIN, 8);
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
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                if (name.length() > 20) {
                    name = name.substring(0, 18) + "..";
                }
                if (eqi.isRear) {
                    name += "(R)";
                }

                font = new Font("Arial", Font.BOLD, 6);
                g2d.setFont(font);
                g2d.drawString(name, typePoint, linePoint);
                font = new Font("Arial", Font.PLAIN, 8);
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
                //Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);
                pj.setJobName(mech.getChassis() + " " + mech.getModel());

                pj.print();

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
        Dimension circle = new Dimension(6, 6);
        Dimension topColumn = new Dimension(499, 177);
        Dimension middleColumn = new Dimension(509, 248);
        Dimension bottomColumn = new Dimension(529, 266);
        Dimension footColumn = new Dimension(519, 290);
        Dimension pipShift = new Dimension(8, -2);

        int totalArmor = mech.getArmor(Mech.LOC_RLEG);

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            topColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                topColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                topColumn.height += pipShift.height + 7;
            }

            if (pos % 4 == 0) {
                topColumn.width += 2;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(middleColumn.width, middleColumn.height, circle.width, circle.width);
            middleColumn.width += pipShift.width;
            middleColumn.height += pipShift.height;
            if (pos % 4 == 0) {
                pipShift.width *= -1;
                middleColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                middleColumn.height += pipShift.height + 7;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(bottomColumn.width, bottomColumn.height, circle.width, circle.width);
            bottomColumn.width += pipShift.width;
            bottomColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width + 1;
                pipShift.height *= -1;
                bottomColumn.height += pipShift.height + 7;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(footColumn.width, footColumn.height, circle.width, circle.width);
            footColumn.width += pipShift.width;
        }

    }

    private void printLLArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(6, 6);
        Dimension topColumn = new Dimension(443, 175);
        Dimension middleColumn = new Dimension(420, 243);
        Dimension bottomColumn = new Dimension(430, 268);
        Dimension footColumn = new Dimension(406, 290);
        Dimension pipShift = new Dimension(8, 2);

        int totalArmor = mech.getArmor(Mech.LOC_LLEG);

        int pips = Math.min(20, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            topColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                topColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                topColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }

            if (pos % 4 == 0) {
                topColumn.width -= 2;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(middleColumn.width, middleColumn.height, circle.width, circle.width);
            middleColumn.width += pipShift.width;
            middleColumn.height += pipShift.height;
            if (pos % 4 == 0) {
                pipShift.width *= -1;
                middleColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                middleColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }

            // if ( pos % 4 == 0 ) {
            // topColumn.width -=2;
            // }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(bottomColumn.width, bottomColumn.height, circle.width, circle.width);
            bottomColumn.width += pipShift.width;
            bottomColumn.height += pipShift.height;
            if (pos % 2 == 0) {
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width - 1;
                pipShift.height *= -1;
                bottomColumn.height += pipShift.height + 7;
                // topColumn.height += pipShift.height;
            }
        }

        pips = Math.min(4, totalArmor);

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(footColumn.width, footColumn.height, circle.width, circle.width);
            footColumn.width += pipShift.width;
        }

    }

    private void printLAArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(6, 6);
        Dimension rightColumn = new Dimension(417, 73);
        Dimension centerColumn = new Dimension(409, 80);
        Dimension leftColumn = new Dimension(402, 86);
        Dimension pipShift = new Dimension(-1, 7);

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(centerColumn.width, centerColumn.height, circle.width, circle.width);
            centerColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                centerColumn.width += pipShift.width;
            }

            if (pos == 7 || pos == 8) {
                centerColumn.height++;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(rightColumn.width, rightColumn.height, circle.width, circle.width);
            rightColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                rightColumn.width += pipShift.width;
            }

            if (pos == 8) {
                rightColumn.height += pipShift.height + 3;
                rightColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(leftColumn.width, leftColumn.height, circle.width, circle.width);
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }
            if (pos == 6) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

    }

    private void printRAArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(6, 6);
        Dimension rightColumn = new Dimension(548, 86);
        Dimension centerColumn = new Dimension(540, 80);
        Dimension leftColumn = new Dimension(533, 73);
        Dimension pipShift = new Dimension(1, 7);

        int totalArmor = mech.getArmor(Mech.LOC_LARM);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(centerColumn.width, centerColumn.height, circle.width, circle.width);
            centerColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                centerColumn.width += pipShift.width;
            }

            if (pos == 7 || pos == 8) {
                centerColumn.height++;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(12, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(leftColumn.width, leftColumn.height, circle.width, circle.width);
            leftColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                leftColumn.width += pipShift.width;
            }

            if (pos == 8) {
                leftColumn.height += pipShift.height + 3;
                leftColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(rightColumn.width, rightColumn.height, circle.width, circle.width);
            rightColumn.height += pipShift.height;
            if (pos % 3 != 0) {
                rightColumn.width += pipShift.width;
            }
            if (pos == 6) {
                rightColumn.height += pipShift.height + 3;
                rightColumn.width += pipShift.width;
            }
        }

    }

    private void printLTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(430, 84);
        Dimension middleColumn = new Dimension(452, 120);
        Dimension bottomColumn = new Dimension(437, 155);
        Dimension pipShift = new Dimension(6, 7);
    
        int totalArmor = mech.getArmor(Mech.LOC_LT);
    
        int pips = Math.min(25, totalArmor);
    
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }
    
        if (totalArmor < 1) {
            return;
        }
    
        pips = Math.min(10, totalArmor);
    
        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(middleColumn.width, middleColumn.height, circle.width, circle.width);
            middleColumn.width += pipShift.width;
            if (pos % 2 == 0) {
                middleColumn.height += pipShift.height;
                middleColumn.width += 1;
                pipShift.width *= -1;
                middleColumn.width += pipShift.width;
            }
    
        }
    
        if (totalArmor < 1) {
            return;
        }
    
        pips = Math.min(7, totalArmor);
    
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(bottomColumn.width, bottomColumn.height, circle.width, circle.width);
            bottomColumn.width += pipShift.width;
            if (pos == 2) {
                bottomColumn.width++;
            } else if (pos == 4) {
                bottomColumn.height += pipShift.height;
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width;
            } else if (pos == 6) {
                bottomColumn.width--;
            }
        }
    
    }

    private void printLTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(437, 308);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getArmor(Mech.LOC_LT,true);

        int pips = Math.min(35, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                if ( pos >= 30 ) {
                    topColumn.width += pipShift.width;
                } else {
                    topColumn.width += pipShift.width*2;
                }
            }
        }
    }

    private void printRTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(497, 84);
        Dimension middleColumn = new Dimension(505, 120);
        Dimension bottomColumn = new Dimension(495, 155);
        Dimension pipShift = new Dimension(6, 7);

        int totalArmor = mech.getArmor(Mech.LOC_RT);

        int pips = Math.min(25, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(10, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(middleColumn.width, middleColumn.height, circle.width, circle.width);
            middleColumn.width += pipShift.width;
            if (pos % 2 == 0) {
                middleColumn.height += pipShift.height;
                middleColumn.width -= 1;
                pipShift.width *= -1;
                middleColumn.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(7, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(bottomColumn.width, bottomColumn.height, circle.width, circle.width);
            bottomColumn.width += pipShift.width;
            if (pos == 2) {
                bottomColumn.width++;
            } else if (pos == 4) {
                bottomColumn.height += pipShift.height;
                pipShift.width *= -1;
                bottomColumn.width += pipShift.width - 5;
            } else if (pos == 5) {
                bottomColumn.width -= 2;
            }
        }

    }

    private void printRTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(495, 308);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getArmor(Mech.LOC_RT,true);

        int pips = Math.min(35, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                if ( pos >= 30 ) {
                    topColumn.width += pipShift.width;
                } 
            }
        }
    }

    private void printCTArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension topColumn = new Dimension(464, 100);
        Dimension middleColumn = new Dimension(481, 167);
        Dimension bottomColumn = new Dimension(475, 180);
        Dimension pipShift = new Dimension(6, 6);

        int totalArmor = mech.getArmor(Mech.LOC_CT);

        int pips = Math.min(55, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 5 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width;
            }
        }

        if (totalArmor < 1) {
            return;
        }

        pips = Math.min(6, totalArmor);

        totalArmor -= pips;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(middleColumn.width, middleColumn.height, circle.width, circle.width);
            middleColumn.width += pipShift.width;
            if (pos % 3 == 0) {
                middleColumn.height += pipShift.height;
                pipShift.width *= -1;
                middleColumn.width += pipShift.width;
            }

        }

        if (totalArmor < 1) {
            return;
        }

        g2d.drawOval(bottomColumn.width, bottomColumn.height, circle.width, circle.width);

    }

    private void printHeadArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(5, 5);
        Dimension fillCircle = new Dimension(3, 3);

        if (mech.getArmor(Mech.LOC_HEAD) >= 1) {
            g2d.setColor(Color.black);
            g2d.fillOval(475, 69, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(476, 70, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 2) {
            g2d.setColor(Color.black);
            g2d.fillOval(472, 73, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(473, 74, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 3) {
            g2d.setColor(Color.black);
            g2d.fillOval(478, 73, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(479, 74, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 4) {
            g2d.setColor(Color.black);
            g2d.fillOval(469, 78, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(470, 79, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 5) {
            g2d.setColor(Color.black);
            g2d.fillOval(475, 78, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(476, 79, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 6) {
            g2d.setColor(Color.black);
            g2d.fillOval(481, 78, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(482, 79, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 7) {
            g2d.setColor(Color.black);
            g2d.fillOval(469, 83, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(470, 84, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 8) {
            g2d.setColor(Color.black);
            g2d.fillOval(475, 83, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(476, 84, fillCircle.width, fillCircle.height);
        }

        if (mech.getArmor(Mech.LOC_HEAD) >= 9) {
            g2d.setColor(Color.black);
            g2d.fillOval(481, 83, circle.width, circle.height);
            g2d.setColor(Color.white);
            g2d.fillOval(482, 84, fillCircle.width, fillCircle.height);
        }
        g2d.setColor(Color.black);
    }

    private void printCTRArmor(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension topColumn = new Dimension(470, 295);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getArmor(Mech.LOC_CT,true);

        int pips = Math.min(56, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(topColumn.width, topColumn.height, circle.width, circle.width);
            topColumn.width += pipShift.width;
            if (pos % 4 == 0) {
                topColumn.height += pipShift.height;
                pipShift.width *= -1;
                topColumn.width += pipShift.width*2;
            }
        }
    }
    
    private void printLAStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(419, 409);
        Dimension pipShift = new Dimension(4, 4);
    
        int totalArmor = mech.getInternal(Mech.LOC_LARM);
    
        int pips = Math.min(16, totalArmor);
    
        totalArmor -= pips;
    
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width += pipShift.width;
    
            if ( pos % 4 == 0 ) {
                column.width -= 2;
            }
            
        }
        
        if ( totalArmor > 0 ) {
            column.height += pipShift.height;
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
        }
    }

    private void printLLStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(441, 471);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_LLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            column.width += pipShift.width;
            pipShift.width *= -1;

            if ( pos % 4 == 0 ) {
                column.width -= 3;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height+2;            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        column.height -= 3;
        column.width -= pipShift.width+1;
        g2d.drawOval(column.width, column.height, circle.width, circle.width);
    }

    private void printRLStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(484, 471);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RLEG);

        int pips = Math.min(18, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            column.width -= pipShift.width;
            pipShift.width *= -1;

            if ( pos % 4 == 0 ) {
                column.width += 3;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        
        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height+2;            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }
        column.height -= 3;
        column.width += pipShift.width+1;
        g2d.drawOval(column.width, column.height, circle.width, circle.width);
    }

    private void printRAStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(506, 409);
        Dimension pipShift = new Dimension(4, 4);

        int totalArmor = mech.getInternal(Mech.LOC_RARM);

        int pips = Math.min(16, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
            pipShift.width *= -1;
            column.width -= pipShift.width;

            if ( pos % 4 == 0 ) {
                column.width += 2;
            }
            
        }
        
        if ( totalArmor > 0 ) {
            column.height += pipShift.height;
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
        }
    }

    private void printLTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(435, 411);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 3 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width*2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }

        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }

        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width += pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;
        pipShift.width *= -1;
        column.height += pipShift.height/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;
            
            if ( pos % 2 == 0 ) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }
    
    private void printRTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(481, 411);
        Dimension pipShift = new Dimension(5, 5);

        int totalArmor = mech.getInternal(Mech.LOC_LT);

        int pips = Math.min(12, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 3 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }

        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(2, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }

        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(1, totalArmor);

        totalArmor -= pips;

        column.width -= pipShift.width/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.height += pipShift.height;
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;
        //pipShift.width *= -1;
        column.height += pipShift.height/2;
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;
            
            if ( pos % 2 == 0 ) {
                pipShift.width *= -1;
                column.height += pipShift.height;
            }
        }

    }

    private void printCTStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        Dimension column = new Dimension(457, 419);
        Dimension pipShift = new Dimension(5,5);

        int totalArmor = mech.getInternal(Mech.LOC_CT);

        int pips = Math.min(27, totalArmor);

        totalArmor -= pips;

        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;

            if ( pos % 3 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
            
        }
        
        if ( totalArmor < 1 ) {
            return;
        }

        pips = Math.min(4, totalArmor);

        totalArmor -= pips;

        column.height += pipShift.height;
        column.width += pipShift.width/2;
        
        for (int pos = 1; pos <= pips; pos++) {
            g2d.drawOval(column.width, column.height, circle.width, circle.width);
            column.width += pipShift.width;
            if ( pos % 2 == 0 ) {
                column.height += pipShift.height;
                pipShift.width *= -1;
                column.width += pipShift.width;
            }
        }
    }
    
    private void printHeadStruct(Graphics2D g2d) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        Dimension circle = new Dimension(4, 4);
        
        g2d.drawOval(462, 398, circle.width, circle.width);
        g2d.drawOval(458, 405, circle.width, circle.width);
        g2d.drawOval(467, 405, circle.width, circle.width);
    }
}