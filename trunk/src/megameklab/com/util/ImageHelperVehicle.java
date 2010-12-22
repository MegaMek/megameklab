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
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.LargeSupportTank;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.VTOL;

public class ImageHelperVehicle {
    public static Image getTableImage(Entity unit) {
        Image table = null;
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;

        if (unit instanceof VTOL) {
            table = new ImageIcon(path + "twvee-vtoltables.png").getImage();
        } else if (unit instanceof Tank) {
            table = new ImageIcon(path + "twvee-groundtables.png").getImage();
        }
        return table;
    }

    public static Image getTurretImage(Entity unit) {
        Image table = null;
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;

        if ((unit instanceof Tank) && (unit.getMovementMode() == EntityMovementMode.WIGE)) {
            table = new ImageIcon(path + "twvee-wige-turret.png").getImage();
        } else {
            table = new ImageIcon(path + "twvee-turret.png").getImage();
        }
        return table;
    }

    public static Image getTurretLabelImage() {
        Image table = null;
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;

        table = new ImageIcon(path + "twvee-turretlabel.png").getImage();
        return table;
    }

    public static void drawLSVISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(5, 5);
        Dimension fillCircle = new Dimension(3, 3);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void drawTankArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelperVehicle.drawTankArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawTankArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 9);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawTankISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(7, 7);
        Dimension fillCircle = new Dimension(5, 5);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void drawTankISTurretPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(6, 6);
        Dimension fillCircle = new Dimension(4, 4);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void printTankWeaponsNEquipment(Tank tank, Graphics2D g2d) {
        ImageHelperVehicle.printTankWeaponsNEquipment(tank, g2d, 0);
    }

    public static void printLargeSupportTankWeaponsNEquipment(LargeSupportTank tank, Graphics2D g2d) {
        ImageHelperVehicle.printLargeSupportTankWeaponsNEquipment(tank, g2d, 0);
    }

    public static void printTankWeaponsNEquipment(Tank tank, Graphics2D g2d, float offset) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 124;
        int damagePoint = 150;
        int minPoint = 166;
        int shtPoint = 177;
        int medPoint = 195;
        int longPoint = 211;
        float maxHeight = 137.0f;
        float linePoint = 212f + offset;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(tank.locations());

        for (int pos = 0; pos <= tank.locations(); pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : tank.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || (!UnitUtil.isPrintableEquipment(eq.getType()))) {
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
                    eqi = new EquipmentInfo(tank, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(tank, eq);
                eqHash.put(equipmentName, eqi);
            }
        }

        if (tank.getTroopCarryingSpace() > 0) {
            maxHeight -= lineFeed;
        }

        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            maxHeight -= lineFeed;
        }

        if (tank.getAmmo().size() > 0) {
            maxHeight -= lineFeed;
        }

        g2d.setFont(UnitUtil.deriveFont(false, 7.0f));
        Font font = ImageHelperVehicle.getVehicleWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, 7.0f);
        g2d.setFont(font);
        float stringHeight = ImageHelper.getStringHeight(g2d, "H", font);

        lineFeed = stringHeight;

        for (int pos = Tank.LOC_BODY; pos <= tank.locations(); pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }
                // font = UnitUtil.deriveFont(7.0f);
                // g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();
                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, font.getSize2D()));

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
                    g2d.drawString(name, typePoint, linePoint);
                }
                // font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                String location = tank.getLocationAbbr(pos);

                if (location.equalsIgnoreCase("TU")) {
                    if (!tank.hasNoDualTurret()) {
                        location = "RT";
                    } else {
                        location = "T";
                    }
                } else if (location.equalsIgnoreCase("TU2")) {
                    location = "FT";
                }
                // this is hacky, but works, left side and right side abbrevs
                // are
                // LS and RS, which results in "LSpon" and "RSpon"
                if (eqi.isSponsonMounted) {
                    location += "po";
                }
                g2d.drawString(location, locPoint, linePoint);
                if (eqi.isWeapon) {
                    if (eqi.isAMS) {
                        g2d.drawLine(damagePoint, (int) linePoint - 2, damagePoint + 6, (int) linePoint - 2);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    } else if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        if (ImageHelper.getStringWidth(g2d, eqi.damage.trim(), font) > 22) {
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(eqi.damage.indexOf('[')), font, damagePoint, linePoint + lineFeed - 1.0f);
                            newLineNeeded = true;
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                        } else {
                            g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        }
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                        } else {
                            g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
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
                count++;
            }
        }
        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            linePoint += lineFeed;
            String troopString = "Infantry Bay (";
            if (Math.floor(troopspace) - troopspace > 0) {
                troopString += String.valueOf(troopspace);
            } else {
                troopString += String.valueOf((int) troopspace);
            }
            if (troopspace == 1) {
                troopString += " Ton)";
            } else {
                troopString += " Tons)";
            }
            g2d.drawString(troopString, qtyPoint, linePoint);
        }
        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            linePoint += lineFeed;
            String chassisMods = "Chassis Modifications: ";
            for (Mounted misc : tank.getMisc()) {
                if (misc.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION)) {
                    chassisMods += misc.getName() + ", ";
                }
            }
            chassisMods = chassisMods.substring(0, chassisMods.length() - 2);
            g2d.setFont(UnitUtil.getNewFont(g2d, chassisMods, false, 205, 7.0f));
            g2d.drawString(chassisMods, qtyPoint, linePoint);
            g2d.setFont(font);
        }

        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    public static void printLargeSupportTankWeaponsNEquipment(LargeSupportTank tank, Graphics2D g2d, float offset) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 124;
        int damagePoint = 150;
        int minPoint = 166;
        int shtPoint = 177;
        int medPoint = 195;
        int longPoint = 211;
        float maxHeight = 137.0f;
        float linePoint = 212f + offset;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(tank.locations());

        for (int pos = 0; pos <= tank.locations(); pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : tank.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType())) {
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
                    eqi = new EquipmentInfo(tank, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(tank, eq);
                eqHash.put(equipmentName, eqi);
            }
        }

        if (tank.getTroopCarryingSpace() > 0) {
            maxHeight -= lineFeed;
        }

        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            maxHeight -= lineFeed;
        }

        if (tank.getAmmo().size() > 0) {
            maxHeight -= lineFeed;
        }

        g2d.setFont(UnitUtil.deriveFont(false, 7.0f));
        Font font = ImageHelperVehicle.getVehicleWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, 7.0f);
        g2d.setFont(font);
        float stringHeight = ImageHelper.getStringHeight(g2d, "H", font);

        lineFeed = stringHeight;

        for (int pos = 0; pos <= tank.locations(); pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

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
                    g2d.drawString(name, typePoint, linePoint);
                }
                g2d.setFont(font);

                String location = tank.getLocationAbbr(pos);

                if (location.equalsIgnoreCase("TU")) {
                    location = "T";
                }
                g2d.drawString(location, locPoint, linePoint);
                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        if (ImageHelper.getStringWidth(g2d, eqi.damage.trim(), font) > 22) {
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(eqi.damage.indexOf('[')), font, damagePoint, linePoint + lineFeed - 1.0f);
                            newLineNeeded = true;
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                        } else {
                            g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        }
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                        } else {
                            g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
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
                count++;
            }
        }

        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            linePoint += lineFeed;
            String troopString = "Infantry Bay (";
            if (Math.floor(troopspace) - troopspace > 0) {
                troopString += String.valueOf(troopspace);
            } else {
                troopString += String.valueOf((int) troopspace);
            }
            if (troopspace == 1) {
                troopString += " Ton)";
            } else {
                troopString += " Tons)";
            }
            g2d.drawString(troopString, qtyPoint, linePoint);
        }
        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            linePoint += lineFeed;
            String chassisMods = "Chassis Modifications: ";
            for (Mounted misc : tank.getMisc()) {
                if (misc.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION)) {
                    chassisMods += misc.getName() + ", ";
                }
            }
            chassisMods = chassisMods.substring(0, chassisMods.length() - 2);
            g2d.setFont(UnitUtil.getNewFont(g2d, chassisMods, false, 68, 7.0f));
            g2d.drawString(chassisMods, qtyPoint, linePoint);
            g2d.setFont(font);
        }

        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    public static void printVTOLWeaponsNEquipment(Tank tank, Graphics2D g2d) {
        ImageHelperVehicle.printVTOLWeaponsNEquipment(tank, g2d, 0);
    }

    public static void printVTOLWeaponsNEquipment(Tank tank, Graphics2D g2d, float offset) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 124;
        int damagePoint = 150;
        int minPoint = 166;
        int shtPoint = 177;
        int medPoint = 195;
        int longPoint = 211;
        float maxHeight = 130.3f;
        float linePoint = 202f + offset;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(Tank.LOC_TURRET + 1);

        for (int pos = 0; pos <= Tank.LOC_TURRET; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : tank.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType())) {
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
                    eqi = new EquipmentInfo(tank, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(tank, eq);
                eqHash.put(equipmentName, eqi);
            }

        }

        if (tank.getTroopCarryingSpace() > 0) {
            maxHeight -= lineFeed;
        }

        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            maxHeight -= lineFeed;
        }

        g2d.setFont(UnitUtil.deriveFont(false, 7.0f));
        Font font = ImageHelperVehicle.getVehicleWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, 7.0f);
        g2d.setFont(font);
        float stringHeight = ImageHelper.getStringHeight(g2d, "H", font);

        lineFeed = stringHeight;

        for (int pos = Tank.LOC_BODY; pos <= VTOL.LOC_ROTOR; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            EquipmentInfo artemisEQ = null;

            if (eqHash.containsKey("Artemis IV FCS")) {
                artemisEQ = eqHash.get("Artemis IV FCS");
                artemisEQ.count = 1;
                eqHash.remove("Artemis IV FCS");
            }

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (int eqPos = 0; eqPos < equipmentList.size(); eqPos++) {
                EquipmentInfo eqi = equipmentList.get(eqPos);
                if ((eqi.isMML || (eqi.name.indexOf("LRM") > -1) || (eqi.name.indexOf("SRM") > -1)) && (artemisEQ != null)) {
                    equipmentList.add(++eqPos, artemisEQ);
                }
            }

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                // if (count >= 12) {
                // break;
                // }
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, font.getSize2D()));

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
                    g2d.drawString(name, typePoint, linePoint);
                }
                g2d.setFont(font);

                String location = tank.getLocationAbbr(pos);

                if (location.equalsIgnoreCase("TU")) {
                    location = "T";
                }

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 4, font.getSize2D()));
                g2d.drawString(location, locPoint, linePoint);
                g2d.setFont(font);
                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font, damagePoint, linePoint);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        if (ImageHelper.getStringWidth(g2d, eqi.damage.trim(), font) > 22) {
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            Font smallFont = UnitUtil.deriveFont(font.getSize2D() - 1.0f);
                            g2d.setFont(smallFont);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(eqi.damage.indexOf('[')), smallFont, damagePoint, linePoint + lineFeed - 1.0f);
                            newLineNeeded = true;
                            g2d.setFont(font);
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                        } else {
                            g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        }
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                        } else {
                            g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
            }
        }
        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            linePoint += lineFeed;
            String troopString = "Infantry Bay (";
            if (Math.floor(troopspace) - troopspace > 0) {
                troopString += String.valueOf(troopspace);
            } else {
                troopString += String.valueOf((int) troopspace);
            }
            if (troopspace == 1) {
                troopString += " Ton)";
            } else {
                troopString += " Tons)";
            }
            g2d.drawString(troopString, qtyPoint, linePoint);
        }
        if (tank.hasWorkingMisc(MiscType.F_CHASSIS_MODIFICATION)) {
            linePoint += lineFeed;
            String chassisMods = "Chassis Modifications: ";
            for (Mounted misc : tank.getMisc()) {
                if (misc.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION)) {
                    chassisMods += misc.getName() + ", ";
                }
            }
            chassisMods = chassisMods.substring(0, chassisMods.length() - 2);
            g2d.drawString(chassisMods, qtyPoint, linePoint);
        }

        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    static public void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor, boolean hasModularArmor) {
        printArmorPoints(g2d, pipPoints, totalArmor, 8.0f, hasModularArmor);
    }

    static public void printArmorPoints(Graphics2D g2d, Vector<float[]> pipPoints, float totalArmor, float fontSize, boolean hasModularArmor) {
        pipPoints.trimToSize();
        float pipSpace = pipPoints.size() / totalArmor;
        if (hasModularArmor) {
            pipSpace = pipPoints.size() / (totalArmor + 15);
        }
        int currentPip = 0;
        for (float pos = 0; pos < pipPoints.size(); pos += pipSpace) {
            if (totalArmor == 0) {
                break;
            }
            currentPip = (int) pos;
            ImageHelperVehicle.drawTankArmorPip(g2d, pipPoints.get(currentPip)[0], pipPoints.get(currentPip)[1], fontSize);
            totalArmor--;
        }

        if (hasModularArmor) {
            for (float pos = 0; pos < 10; pos++) {
                currentPip += pipSpace;
                ImageHelper.drawDiamond(g2d, (int) pipPoints.get(currentPip)[0], (int) pipPoints.get(currentPip)[1]);
            }
        }
    }

    public static Font getVehicleWeaponsNEquipmentFont(Graphics2D g2d, boolean bold, float stringHeight, ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations, float pointSize) {

        Font font = g2d.getFont();

        int weaponCount = 1;
        for (int pos = 0; pos < equipmentLocations.size(); pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);
            if (eqHash.size() < 1) {
                continue;
            }

            for (EquipmentInfo eqi : eqHash.values()) {

                weaponCount++;
                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        weaponCount++;
                    } else if (eqi.isATM) {
                        weaponCount++;
                    } else if (eqi.hasArtemis || eqi.hasArtemisV) {
                        weaponCount++;
                    }
                }
            }
        }

        while (((ImageHelper.getStringHeight(g2d, "H", font) * weaponCount) > stringHeight) && (pointSize > 0)) {
            pointSize -= .1;
            font = UnitUtil.deriveFont(bold, pointSize);
        }

        return font;
    }

}