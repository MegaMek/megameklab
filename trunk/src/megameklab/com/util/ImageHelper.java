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

package megameklab.com.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import megamek.common.AmmoType;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.QuadMech;

public class ImageHelper {
    public static String recordSheetPath = "./data/images/recordsheets/";
    public static String fluffPath = "./data/images/fluff/";

    
    public static Image getRecordSheet(Entity unit, boolean advanced) {

        Image recordSheet = null;

        if (unit instanceof BipedMech) {
            if (advanced) {
                recordSheet = new ImageIcon(recordSheetPath + "tobiped.png").getImage();
            } else {
                recordSheet = new ImageIcon(recordSheetPath + "twbiped.png").getImage();
            }
        } else if (unit instanceof QuadMech) {
            if (advanced) {
                recordSheet = new ImageIcon(recordSheetPath + "toquad.png").getImage();
            } else {
                recordSheet = new ImageIcon(recordSheetPath + "twquad.png").getImage();
            }
        }

        return recordSheet;
    }

    public static Image getFluffImage(Entity unit) {
        Image fluff = null;

        fluff = getFluffPNG(unit);

        if (fluff == null) {
            fluff = getFluffJPG(unit);
        }

        if (fluff == null) {
            fluff = getFluffGIF(unit);
        }

        if (fluff == null) {
            fluff = new ImageIcon(fluffPath + "hud.png").getImage();
        }
        return fluff;
    }

    public static Image getFluffPNG(Entity unit) {
        Image fluff = null;

        String fluffFile = fluffPath + unit.getChassis() + " " + unit.getModel() + ".png";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getModel() + ".png";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getChassis() + ".png";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static Image getFluffJPG(Entity unit) {
        Image fluff = null;

        String fluffFile = fluffPath + unit.getChassis() + " " + unit.getModel() + ".jpg";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getModel() + ".jpg";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getChassis() + ".jpg";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static Image getFluffGIF(Entity unit) {
        Image fluff = null;

        String fluffFile = fluffPath + unit.getChassis() + " " + unit.getModel() + ".gif";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getModel() + ".gif";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = fluffPath + unit.getChassis() + ".gif";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static void printMechWeaponsNEquipment(Mech mech, Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 109;
        int heatPoint = 128;
        int damagePoint = 150;
        int minPoint = 167;
        int shtPoint = 181;
        int medPoint = 199;
        int longPoint = 215;
        int linePoint = 204;

        int lineFeed = 10;

        boolean newLineNeeded = false;
        
        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(Mech.LOC_LLEG + 1);

        for (int pos = 0; pos <= Mech.LOC_LLEG; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : mech.getEquipment()) {

            if (eq.getType() instanceof AmmoType || eq.getLocation() == Mech.LOC_NONE || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            if (eqHash.containsKey(eq.getName())) {
                EquipmentInfo eqi = eqHash.get(eq.getName());

                if (eq.getType().getTechLevel() != eqi.techLevel) {
                    eqi = new EquipmentInfo(mech, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(eq.getName(), eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(mech, eq);
                eqHash.put(eq.getName(), eqi);
            }

        }

        Font font = UnitUtil.deriveFont(true,10.0f);
        g2d.setFont(font);

        for (int pos = Mech.LOC_HEAD; pos <= Mech.LOC_LLEG; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            for (EquipmentInfo eqi : eqHash.values()) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == eqi.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font);
                } else if (eqi.c3Level == eqi.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font);
                } else if (eqi.c3Level == eqi.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(mech.getLocationAbbr(pos), locPoint, linePoint);
                if (eqi.isWeapon) {
                    g2d.drawString(Integer.toString(eqi.heat), heatPoint, linePoint);

                    if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 2;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 2;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, linePoint - 2, minPoint + 6, linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 2;
                        
                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 2;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 2;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, linePoint - 2, minPoint + 6, linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        if (eqi.damage.trim().length() > 6) {
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(eqi.damage.indexOf('[')), font, damagePoint, linePoint + lineFeed - 2);
                            newLineNeeded = true;
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                        } else {
                            g2d.drawLine(minPoint, linePoint - 2, minPoint + 6, linePoint - 2);
                        }
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                    }
                } else {
                    g2d.drawLine(heatPoint, linePoint - 2, heatPoint + 6, linePoint - 2);
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawLine(minPoint, linePoint - 2, minPoint + 6, linePoint - 2);
                    g2d.drawLine(shtPoint, linePoint - 2, shtPoint + 6, linePoint - 2);
                    g2d.drawLine(medPoint, linePoint - 2, medPoint + 6, linePoint - 2);
                    if ( eqi.longRange > 0 ){
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                    }else {
                        g2d.drawLine(longPoint, linePoint - 2, longPoint + 6, linePoint - 2);
                    }
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
            }
        }

    }

    public static void printC3iName(Graphics2D g2d, int lineStart, int linePoint, Font font) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        g2d.drawString("Improved C  CPU", lineStart, linePoint);
        int stringWidth = ImageHelper.getStringWidth(g2d, "Improved C", font);
        font = font.deriveFont(attrMap);
        g2d.setFont(font);

        g2d.drawString("3", lineStart + stringWidth, linePoint);
    }

    public static void printC3sName(Graphics2D g2d, int lineStart, int linePoint, Font font) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        g2d.drawString("C  Slave", lineStart, linePoint);
        int stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);

    }

    public static void printC3mName(Graphics2D g2d, int lineStart, int linePoint, Font font) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        g2d.drawString("C  Master", lineStart, linePoint);
        int stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
    }

    public static void printCenterString(Graphics2D g2d, String info, Font font, int printWidth, int printHeight) {
        int textWidth = ImageHelper.getStringWidth(g2d, info, font);

        g2d.drawString(info, printWidth - (textWidth / 2), printHeight);

    }

    public static int getStringWidth(Graphics2D g2d, String info, Font font) {
        FontMetrics fm = g2d.getFontMetrics(font);
        Rectangle2D rect = fm.getStringBounds(info, g2d);

        return (int) (rect.getWidth());
    }

    public static void drawArmorPip(Graphics2D g2d, int width, int height){
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }
    
    public static void drawISPip(Graphics2D g2d, int width, int height){
        Font font = new Font("Arial", Font.PLAIN, 6);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }
    
    public static void drawHeatSinkPip(Graphics2D g2d, int width, int height){
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }


}