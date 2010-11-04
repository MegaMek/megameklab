/*
 * MegaMekLab - Copyright (C) 2010
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
package megameklab.com.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mounted;

public class ImageHelperAero {
    private static final String[] LOCATION_ABBRS =
        { "N", "LW", "RW", "A" };

    public static String getLocationAbbrs(int pos) {
        return LOCATION_ABBRS[pos];
    }

    public static void drawAeroArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelperAero.drawAeroArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawAeroArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.BOLD, 9);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawAeroISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(6, 6);
        Dimension fillCircle = new Dimension(4, 4);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void printAeroFuel(Aero aero, Graphics2D g2d) {
        int pointY = 330;
        int pointX = 22;
        String fuel = "Fuel: ";

        g2d.setFont(UnitUtil.getNewFont(g2d, fuel, true, 200, 7.0f));
        g2d.drawString(fuel, pointX, pointY);
        pointX += ImageHelper.getStringWidth(g2d, fuel, g2d.getFont());

        String fuelAmount = String.format("%1$s Points", aero.getFuel());
        g2d.setFont(UnitUtil.getNewFont(g2d, fuelAmount, false, 200, 7.0f));
        g2d.drawString(fuelAmount, pointX, pointY);
    }

    public static void printAeroWeaponsNEquipment(Aero aero, Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 109;
        int heatPoint = 133;
        int shtPoint = 151;
        int medPoint = 169;
        int longPoint = 192;
        int erPoint = 211;
        float linePoint = 204f;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(aero.locations());

        for (int pos = 0; pos <= aero.locations(); pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : aero.getEquipment()) {

            if ((eq.isWeaponGroup() || (eq.getType() instanceof AmmoType)) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                equipmentName += "(R)";
            }

            if (eqHash.containsKey(equipmentName)) {
                EquipmentInfo eqi = eqHash.get(equipmentName);

                if (eq.getType().getTechLevel() != eqi.techLevel) {
                    eqi = new EquipmentInfo(aero, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(aero, eq);
                eqHash.put(equipmentName, eqi);
            }

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Aero.LOC_NOSE; pos <= Aero.LOC_AFT; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim() + " " + eqi.damage.trim();

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
                } else {
                    if (ImageHelper.getStringWidth(g2d, name, font) > 68) {
                        g2d.setFont(UnitUtil.getNewFont(g2d, eqi.name.trim(), false, 68, 7.0f));
                        g2d.drawString(eqi.name.trim(), typePoint, linePoint);
                        linePoint += lineFeed;
                        g2d.drawString(eqi.damage.trim(), typePoint, linePoint);
                    } else {
                        g2d.drawString(name, typePoint, linePoint);
                    }

                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                String location = ImageHelperAero.getLocationAbbrs(pos);

                g2d.drawString(location, locPoint, linePoint);
                ImageHelper.printCenterString(g2d, Integer.toString(eqi.heat), font, heatPoint, linePoint);
                if (eqi.shtRange > 0) {
                    g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, (int) linePoint);
                } else {
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                }

                if (eqi.medRange > 0) {
                    g2d.drawString(Integer.toString(eqi.medRange), medPoint, (int) linePoint);
                } else {
                    g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                }
                if (eqi.longRange > 0) {
                    g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                } else {
                    g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                }
                if (eqi.erRange > 0) {
                    g2d.drawString(Integer.toString(eqi.erRange), erPoint, (int) linePoint);
                } else {
                    g2d.drawLine(erPoint, (int) linePoint - 2, erPoint + 6, (int) linePoint - 2);
                }

                if (eqi.hasArtemis) {
                    g2d.drawString("w/Artemis IV FCS", typePoint, linePoint + lineFeed);
                    newLineNeeded = true;
                } else if (eqi.hasArtemisV) {
                    g2d.drawString("w/Artemis V FCS", typePoint, linePoint + lineFeed);
                    newLineNeeded = true;
                } else if (eqi.hasApollo) {
                    g2d.drawString("w/Apollo FCS", typePoint, linePoint + lineFeed);
                    newLineNeeded = true;
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
            }
        }

        ImageHelper.printVehicleAmmo(aero, g2d, -18);
        ImageHelperAero.printAeroFuel(aero, g2d);
    }

}