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
import megamek.common.CriticalSlot;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.ATMWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.SRMWeapon;

public class PrintAdvancedMech implements Printable {

    protected Image awtImage = null;
    private Mech mech = null;

    public PrintAdvancedMech(Image image, Mech unit) {
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
            g2d.drawString("X", 294, 205);
            break;
        case Mech.COCKPIT_SMALL:
            g2d.drawString("X", 294, 214);
            break;
        case Mech.COCKPIT_DUAL:
            g2d.drawString("X", 367, 196);
            break;
        case Mech.COCKPIT_COMMAND_CONSOLE:
            g2d.drawString("X", 367, 205);
            break;
        case Mech.COCKPIT_TORSO_MOUNTED:
            g2d.drawString("X", 367, 214);
            break;
        }

        switch (mech.getArmorType()) {
        case EquipmentType.T_ARMOR_STANDARD:
            g2d.drawString("X", 367, 249);
            break;
        case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            g2d.drawString("X", 367, 258);
            break;
        case EquipmentType.T_ARMOR_LAMELLOR_FERRO_CARBIDE:
            g2d.drawString("X", 367, 267);
            break;
        case EquipmentType.T_ARMOR_LIGHT_FERRO:
            g2d.drawString("X", 367, 276);
            break;
        case EquipmentType.T_ARMOR_HEAVY_FERRO:
            g2d.drawString("X", 367, 285);
            break;
        case EquipmentType.T_ARMOR_STEALTH:
            g2d.drawString("X", 367, 294);
            break;
        case EquipmentType.T_ARMOR_HARDENED:
            g2d.drawString("X", 367, 309);
            break;
        case EquipmentType.T_ARMOR_REACTIVE:
            g2d.drawString("X", 367, 319);
            break;
        case EquipmentType.T_ARMOR_REFLECTIVE:
            g2d.drawString("X", 367, 329);
            break;
        case EquipmentType.T_ARMOR_COMMERCIAL:
            g2d.drawString("X", 367, 345);
            break;
        default:
            g2d.drawString("X", 367, 249);
            break;

        }

        switch (mech.getStructureType()) {
        case EquipmentType.T_STRUCTURE_STANDARD:
            g2d.drawString("X", 297, 259);
            break;
        case EquipmentType.T_STRUCTURE_ENDO_STEEL:
            g2d.drawString("X", 297, 268);
            break;
        case EquipmentType.T_STRUCTURE_REINFORCED:
            g2d.drawString("X", 297, 286);
            break;
        case EquipmentType.T_STRUCTURE_COMPOSITE:
            g2d.drawString("X", 297, 295);
            break;
        case EquipmentType.T_STRUCTURE_INDUSTRIAL:
            g2d.drawString("X", 297, 303);
            break;

        }

        if (mech.isClan()) {
            g2d.drawString("X", 209, 154);
        } else if (mech.isMixedTech()) {
            g2d.drawString("X", 209, 174);
        } else {
            g2d.drawString("X", 209, 164);
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
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_HEAD)), 485, 48);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT)), 435, 61);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT)), 509, 61);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT)), 475, 222);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LARM)), 397, 217);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RARM)), 546, 217);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LLEG)), 390, 273);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RLEG)), 554, 273);
        // Rear
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT, true)), 403, 363);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT, true)), 480, 279);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT, true)), 545, 363);
        // Internal
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LT)), 432, 404);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RT)), 525, 404);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LARM)), 391, 479);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RARM)), 531, 481);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_CT)), 460, 511);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LLEG)), 403, 550);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RLEG)), 519, 550);
    }

    private void printLACrits(Graphics2D g2d) {

        int lineStart = 58;
        int linePoint = 410;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 294;
        int linePoint = 410;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 176;
        int linePoint = 469;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 58;
        int linePoint = 545;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 294;
        int linePoint = 545;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 176;
        int linePoint = 401;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 58;
        int linePoint = 682;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

        int lineStart = 294;
        int linePoint = 682;
        int lineFeed = 8;

        Font font = new Font("Arial", Font.PLAIN, 9);
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

                g2d.drawString(name, typePoint, linePoint);
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
}