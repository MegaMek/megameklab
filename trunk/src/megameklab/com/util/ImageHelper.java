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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.QuadMech;
import megamek.common.Tank;
import megamek.common.VTOL;

public class ImageHelper {
    public static String recordSheetPath = "./data/images/recordsheets/";
    public static String fluffPath = "./data/images/fluff/";
    public static String imagePath = "./data/images/";

    public static String imageMech = "mech";
    public static String imageAero = "aero";
    public static String imageBA = "BattleArmor";
    public static String imageVehicle = "vehicle";
    public static String imageProto = "protomech";

    public static Image getRecordSheet(Entity unit) {
        return ImageHelper.getRecordSheet(unit, false);
    }

    public static Image getRecordSheet(Entity unit, boolean advanced) {

        Image recordSheet = null;

        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        if (unit instanceof BipedMech) {
            if (advanced) {
                recordSheet = new ImageIcon(path + "tobiped.png").getImage();
            } else {
                recordSheet = new ImageIcon(path + "twbiped.png").getImage();
            }
        } else if (unit instanceof QuadMech) {
            if (advanced) {
                recordSheet = new ImageIcon(path + "toquad.png").getImage();
            } else {
                recordSheet = new ImageIcon(path + "twquad.png").getImage();
            }
        } else if (unit instanceof VTOL) {
            recordSheet = new ImageIcon(path + "twvee-vtol.png").getImage();
        } else if (unit instanceof Tank) {
            String imageName = "twvee-" + unit.getMovementModeAsString().toLowerCase().trim() + ".png";
            recordSheet = new ImageIcon(path + imageName).getImage();
        } else if (unit instanceof Aero) {
            recordSheet = new ImageIcon(path + "twaero.png").getImage();
        } else if (unit instanceof BattleArmor) {
            recordSheet = new ImageIcon(path + "twba.png").getImage();
        } else if (unit instanceof Protomech) {
            recordSheet = new ImageIcon(path + "twproto.png").getImage();
        }

        return recordSheet;
    }

    public static Image getTableImage(Entity unit) {
        Image table = null;
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        if (unit instanceof VTOL) {
            table = new ImageIcon(path + "twvee-vtoltables.png").getImage();
        } else if (unit instanceof Tank) {
            table = new ImageIcon(path + "twvee-groundtables.png").getImage();
        }
        return table;
    }

    public static Image getTurretImage() {
        Image table = null;
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        table = new ImageIcon(path + "twvee-turret.png").getImage();

        return table;
    }

    public static Image getTurretLabelImage() {
        Image table = null;
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        table = new ImageIcon(path + "twvee-turretlabel.png").getImage();

        return table;
    }

    public static Image getGyroPipImage() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image image = new ImageIcon(path + "gyropip.png").getImage();
        return image;
    }

    public static Image getUMImage() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image image = new ImageIcon(path + "UM.png").getImage();
        return image;
    }

    public static Image getShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shields.png").getImage();
        return image;
    }

    public static Image getRightShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shield-right.png").getImage();
        return image;
    }

    public static Image getLeftShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shield-left.png").getImage();
        return image;
    }

    public static Image getFluffImage(String image) {
        Image fluff = null;

        String path = new File(fluffPath).getAbsolutePath() + File.separatorChar + image;

        if (!(new File(path).exists())) {
            return null;
        }
        fluff = new ImageIcon(path).getImage();
        return fluff;
    }

    public static Image getFluffImage(Entity unit, String dir) {
        Image fluff = null;

        String path = new File(fluffPath).getAbsolutePath() + File.separatorChar + dir + File.separatorChar;
        fluff = ImageHelper.getFluffPNG(unit, path);

        if (fluff == null) {
            fluff = ImageHelper.getFluffJPG(unit, path);
        }

        if (fluff == null) {
            fluff = ImageHelper.getFluffGIF(unit, path);
        }

        if (fluff == null) {
            fluff = new ImageIcon(path + "hud.png").getImage();
        }
        return fluff;
    }

    public static Image getFluffPNG(Entity unit, String path) {
        Image fluff = null;

        String fluffFile = path + unit.getChassis() + " " + unit.getModel() + ".png";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = path + unit.getModel() + ".png";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = path + unit.getChassis() + ".png";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static Image getFluffJPG(Entity unit, String path) {
        Image fluff = null;

        String fluffFile = path + unit.getChassis() + " " + unit.getModel() + ".jpg";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = path + unit.getModel() + ".jpg";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = path + unit.getChassis() + ".jpg";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static Image getFluffGIF(Entity unit, String path) {
        Image fluff = null;

        String fluffFile = path + unit.getChassis() + " " + unit.getModel() + ".gif";
        if (new File(fluffFile.toLowerCase()).exists()) {
            fluff = new ImageIcon(fluffFile).getImage();
        }

        if (fluff == null) {
            fluffFile = path + unit.getModel() + ".gif";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        if (fluff == null) {
            fluffFile = path + unit.getChassis() + ".gif";
            if (new File(fluffFile.toLowerCase()).exists()) {
                fluff = new ImageIcon(fluffFile).getImage();
            }
        }

        return fluff;
    }

    public static void printMechWeaponsNEquipment(Mech mech, Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 115;
        int heatPoint = 128;
        int damagePoint = 150;
        int minPoint = 167;
        int shtPoint = 181;
        int medPoint = 199;
        int longPoint = 215;
        float linePoint = 201f;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(Mech.LOC_LLEG + 1);

        for (int pos = 0; pos <= Mech.LOC_LLEG; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : mech.getEquipment()) {

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
                    eqi = new EquipmentInfo(mech, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(mech, eq);
                eqHash.put(equipmentName, eqi);
            }

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Mech.LOC_HEAD; pos <= Mech.LOC_LLEG; pos++) {

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
                    EquipmentInfo clone = artemisEQ.clone();
                    clone.count = eqi.count;
                    equipmentList.add(++eqPos, clone);
                }
            }

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                String location = mech.getLocationAbbr(pos);

                if (eqi.secondaryLocation != Entity.LOC_NONE) {
                    location = String.format("%1$s/%2$s", mech.getLocationAbbr(pos), mech.getLocationAbbr(eqi.secondaryLocation));
                }

                ImageHelper.printCenterString(g2d, location, font, locPoint, linePoint);
                if (eqi.isWeapon) {
                    g2d.drawString(Integer.toString(eqi.heat), heatPoint, linePoint);

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
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
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
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                    }
                } else {
                    g2d.drawLine(heatPoint, (int) linePoint - 2, heatPoint + 6, (int) linePoint - 2);
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

    public static void printVehicleAmmo(Entity vehicle, Graphics2D g2d, int offset) {

        if (vehicle.getAmmo().size() < 1) {
            return;
        }

        int pointY = 340 + offset;
        int pointX = 22;

        HashMap<String, Integer> ammoHash = new HashMap<String, Integer>();

        for (Mounted ammo : vehicle.getAmmo()) {
            // don't print one shot ammo
            if (ammo.getLocation() == Entity.LOC_NONE) {
                continue;
            }
            AmmoType aType = (AmmoType) ammo.getType();
            String shortName = aType.getShortName().replace("Ammo", "");
            shortName = shortName.replace('(', '.').replace(')', '.').replace(".Clan.", "");
            shortName = shortName.replace("-capable", "");
            shortName += " ";
            if ((aType.getAmmoType() == AmmoType.T_AC) || (aType.getAmmoType() == AmmoType.T_MML) || (aType.getAmmoType() == AmmoType.T_SRM) || (aType.getAmmoType() == AmmoType.T_SRM_STREAK) || (aType.getAmmoType() == AmmoType.T_SRM_TORPEDO) || (aType.getAmmoType() == AmmoType.T_LRM) || (aType.getAmmoType() == AmmoType.T_LRM_STREAK) || (aType.getAmmoType() == AmmoType.T_LRM_TORPEDO) || (aType.getAmmoType() == AmmoType.T_MML) || (aType.getAmmoType() == AmmoType.T_AC) || (aType.getAmmoType() == AmmoType.T_AC_LBX) || (aType.getAmmoType() == AmmoType.T_AC_LBX_THB) || (aType.getAmmoType() == AmmoType.T_AC_ROTARY) || (aType.getAmmoType() == AmmoType.T_AC_ULTRA) || (aType.getAmmoType() == AmmoType.T_AC_ULTRA_THB) || (aType.getAmmoType() == AmmoType.T_MRM)
                    || (aType.getAmmoType() == AmmoType.T_MRM_STREAK) || (aType.getAmmoType() == AmmoType.T_ATM) || (aType.getAmmoType() == AmmoType.T_HAG) || (aType.getAmmoType() == AmmoType.T_EXLRM)) {
                shortName = shortName.replaceFirst(" ", " " + aType.getRackSize() + " ");
                shortName = shortName.replaceFirst("  Artemis", " Artemis");
            }
            shortName = shortName.trim();

            if (ammoHash.containsKey(shortName)) {
                int currentAmmo = ammoHash.get(shortName);
                currentAmmo += aType.getShots();
                ammoHash.put(shortName, currentAmmo);
            } else {
                int currentAmmo = aType.getShots();
                ammoHash.put(shortName, currentAmmo);
            }
        }
        if (ammoHash.keySet().size() == 0) {
            return;
        }
        StringBuffer sb = new StringBuffer("Ammo: ");

        int linecount = 0;
        for (String ammo : ammoHash.keySet()) {
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
        }

        double stringLength = ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        linecount = (int) Math.floor(stringLength / 160);

        sb.setLength(0);
        sb.append("Ammo: ");

        if (vehicle.hasWorkingMisc(MiscType.F_CASE, -1)) {
            sb = new StringBuffer("Ammo (CASE): ");
        }

        g2d.drawString(sb.toString(), pointX, pointY - (linecount) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont()));
        pointX += ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        sb = new StringBuffer();
        int linesprinted = 0;
        int currentStringLength = 0;

        for (String ammo : ammoHash.keySet()) {
            currentStringLength = sb.length();
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
            if ((ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont()) > 160) && (linesprinted < linecount)) {
                sb.setLength(sb.length() - ((sb.length() - currentStringLength) + 2));
                g2d.drawString(sb.toString(), pointX, pointY - ((linecount - linesprinted) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
                linesprinted++;
                sb.setLength(0);
                sb.append("(");
                sb.append(ammo);
                sb.append(") ");
                sb.append(ammoHash.get(ammo));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
            g2d.drawString(sb.toString(), pointX, pointY - ((linecount - linesprinted) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
            pointY += ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont());
        }
    }

    public static void printTankWeaponsNEquipment(Tank tank, Graphics2D g2d) {
        ImageHelper.printTankWeaponsNEquipment(tank, g2d, 0);
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
        float linePoint = 212f + offset;

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

        int troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            EquipmentInfo eqi = new EquipmentInfo();
            eqi.name = "Infantry Bay (" + troopspace + " tons)";
            eqi.damage = "  [E]";
            eqi.count = 1;
            equipmentLocations.get(Tank.LOC_BODY).put("Infantry cargo bay: " + troopspace, eqi);
        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Tank.LOC_BODY; pos <= Tank.LOC_TURRET; pos++) {

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

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
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
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
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

        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    public static void printVTOLWeaponsNEquipment(Tank tank, Graphics2D g2d) {
        ImageHelper.printVTOLWeaponsNEquipment(tank, g2d, 0);
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
        int troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            EquipmentInfo eqi = new EquipmentInfo();
            eqi.name = "Infantry Bay (" + troopspace + " tons)";
            eqi.damage = "  [E]";
            eqi.count = 1;
            equipmentLocations.get(Tank.LOC_BODY).put("Infantry cargo bay: " + troopspace, eqi);
        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

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

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
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
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
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
        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    public static void printC3iName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Improved C  CPU", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Improved C", font);
        } else {
            g2d.drawString("Improved C  CPU", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "Improved C", font);
        }
        font = font.deriveFont(attrMap);
        g2d.setFont(font);

        g2d.drawString("3", lineStart + stringWidth, linePoint);
    }

    public static void printC3sName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C  Slave", font);
        } else {
            g2d.drawString("C  Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C  Slave", font);
        }

        stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);

    }

    public static void printC3mName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C  Master", font);
        } else {
            g2d.drawString("C  Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C  Master", font);
        }

        stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
    }

    public static void printCenterString(Graphics2D g2d, String info, Font font, int printWidth, float printHeight) {
        int textWidth = ImageHelper.getStringWidth(g2d, info, font);

        g2d.drawString(info, printWidth - (textWidth / 2), printHeight);

    }

    public static int getStringWidth(Graphics2D g2d, String info, Font font) {
        FontMetrics fm = g2d.getFontMetrics(font);
        Rectangle2D rect = fm.getStringBounds(info, g2d);

        return (int) (rect.getWidth());
    }

    public static float getStringHeight(Graphics2D g2d, String info, Font font) {
        FontMetrics fm = g2d.getFontMetrics(font);
        Rectangle2D rect = fm.getStringBounds(info, g2d);

        return (float) rect.getHeight();
    }

    public static void drawArmorPip(Graphics2D g2d, float width, float height) {
        Font font = new Font("Arial", Font.PLAIN, 7);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }

    public static void drawTankArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelper.drawTankArmorPip(g2d, width, height, 9.0f);
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

    public static void drawISPip(Graphics2D g2d, float width, float height) {
        Font font = new Font("Arial", Font.PLAIN, 6);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }

    public static void drawAeroArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelper.drawAeroArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawAeroArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 9);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawAeroISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(7, 7);
        Dimension fillCircle = new Dimension(5, 5);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void drawHeatSinkPip(Graphics2D g2d, float width, float height) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString("O", width, height);
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

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim() + " " + eqi.damage.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                String location = aero.getLocationAbbr(pos);

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

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
            }
        }

        ImageHelper.printVehicleAmmo(aero, g2d, -18);
        ImageHelper.printAeroFuel(aero, g2d);
    }

    public static void printBAArmor(BattleArmor ba, Graphics2D g2d, float offset) {
        float positionX = 27;
        float positionY = 187 + offset;
        String armorString = "Armor: ";
        if (ba.isStealthActive()) {
            Font font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);
            g2d.drawString(armorString, positionX, positionY);
            positionX += ImageHelper.getStringWidth(g2d, armorString, font);

            font = UnitUtil.deriveFont(9.0f);
            g2d.setFont(font);
            g2d.drawString(String.format("%1$s (+%2$s/+%3$s/+%4$s)", ba.getStealthName(), ba.getShortStealthMod(), ba.getMediumStealthMod(), ba.getLongStealthMod()), positionX, positionY);
        }
    }

    public static void printBattleArmorWeaponsNEquipment(BattleArmor ba, Graphics2D g2d) {
        ImageHelper.printBattleArmorWeaponsNEquipment(ba, g2d, 0);
    }

    public static void printBattleArmorWeaponsNEquipment(BattleArmor ba, Graphics2D g2d, float offset) {
        int typePoint = 26;
        int damagePoint = 130;
        int minPoint = 150;
        int shtPoint = 166;
        int medPoint = 180;
        int longPoint = 198;
        float linePoint = 148.5f + offset;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(BattleArmor.LOC_TROOPER_6 + 1);

        for (int pos = 0; pos <= BattleArmor.LOC_TROOPER_6; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : ba.getEquipment()) {

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
                    eqi = new EquipmentInfo(ba, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(ba, eq);
                eqHash.put(equipmentName, eqi);
            }

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = BattleArmor.LOC_SQUAD; pos <= BattleArmor.LOC_TROOPER_6; pos++) {

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

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
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
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
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

        ImageHelper.printBAArmor(ba, g2d, offset);
    }

    public static Image getBASquad() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "twba-squad.png").getImage();
    }

    public static Image getBASquadNumber(int position) {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        position++;
        return new ImageIcon(path + "twba-squad-" + position + ".png").getImage();
    }

    public static Image getProtoMech(int number) {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        if (number == 2) {
            return new ImageIcon(path + "twproto-singlemiddle.png").getImage();
        }

        return new ImageIcon(path + "twproto-singletop.png").getImage();
    }

    public static Image getProtoLogo() {

        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        return new ImageIcon(path + "twproto-logo.png").getImage();

    }

    public static Image getBATrooper(int position) {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "twba-trooper" + position + ".png").getImage();
    }

    public static Image getBACheckBox() {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "checkbox.png").getImage();
    }

    public static void drawBAISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(7, 7);
        Dimension fillCircle = new Dimension(5, 5);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.GRAY);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static void drawProtoISPip(Graphics2D g2d, int width, int height) {
        Dimension fillCircle = new Dimension(3, 3);
        g2d.setColor(Color.GRAY);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
        g2d.setColor(Color.BLACK);
    }

    public static void drawBAArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelper.drawBAArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawBAArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 9);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static float getDiamondSize(Graphics2D g2d) {
        double size = 0;
        Font font = new Font("Arial", Font.PLAIN, 8);
        GlyphVector gv = font.createGlyphVector(new FontRenderContext(g2d.getTransform(), true, true), "\u02C4");
        size = gv.getOutline().getBounds2D().getHeight();
        gv = font.createGlyphVector(new FontRenderContext(g2d.getTransform(), true, true), "\u02C5");
        size += gv.getOutline().getBounds2D().getHeight();
        size += .5f;
        return (float) size;
    }

    public static void drawDiamond(Graphics2D g2d, int xPos, int yPos) {
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
        Image img = new ImageIcon(path + "shielddiamond.png").getImage();
        g2d.drawImage(img, xPos, yPos, 5, 5, null);

    }

    public static void printProtomechWeaponsNEquipment(Protomech ba, Graphics2D g2d) {
        ImageHelper.printProtomechWeaponsNEquipment(ba, g2d, 0);
    }

    public static void printProtomechWeaponsNEquipment(Protomech proto, Graphics2D g2d, float offset) {
        int locationPoint = 115;
        int typePoint = 143;
        int damagePoint = 216;
        int minPoint = 234;
        int shtPoint = 252;
        int medPoint = 266;
        int longPoint = 282;
        float linePoint = 119f + offset;
        boolean torsoWeaponUsed = false;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(Protomech.LOC_MAINGUN + 1);

        for (int pos = 0; pos <= Protomech.LOC_MAINGUN; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : proto.getEquipment()) {

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
                    eqi = new EquipmentInfo(proto, eq);
                } else {
                    eqi.count++;
                }
                eqHash.put(equipmentName, eqi);
            } else {
                EquipmentInfo eqi = new EquipmentInfo(proto, eq);
                eqHash.put(equipmentName, eqi);
            }

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Protomech.LOC_HEAD; pos <= Protomech.LOC_MAINGUN; pos++) {

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

                if (count >= 12) {
                    break;
                }

                font = UnitUtil.deriveFont(5.0f);
                g2d.setFont(font);

                if ((pos == Protomech.LOC_TORSO) && !torsoWeaponUsed) {
                    g2d.drawString(String.format("%1$9s A:", proto.getLocationName(pos)), locationPoint, linePoint);
                    torsoWeaponUsed = true;
                } else if ((pos == Protomech.LOC_TORSO) && torsoWeaponUsed) {
                    g2d.drawString(String.format("%1$9s B:", proto.getLocationName(pos)), locationPoint, linePoint);
                } else {
                    g2d.drawString(String.format("%1$9s:", proto.getLocationName(pos)), locationPoint, linePoint);
                }

                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font, damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
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
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
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

        ImageHelper.printProtoAmmo(proto, g2d, (int) offset);
    }

    public static void printProtoAmmo(Entity proto, Graphics2D g2d, int offset) {
        if (proto.getAmmo().size() < 1) {
            return;
        }

        int pointY = 160 + offset;
        int pointX = 115;

        HashMap<String, Integer> ammoHash = new HashMap<String, Integer>();

        for (Mounted ammo : proto.getAmmo()) {
            // don't print one shot ammo
            if (ammo.getLocation() == Entity.LOC_NONE) {
                continue;
            }
            AmmoType aType = (AmmoType) ammo.getType();
            String shortName = aType.getShortName().replace("Ammo", "");
            shortName = shortName.replace('(', '.').replace(')', '.').replace(".Clan.", "");
            shortName = shortName.replace("-capable", "");
            shortName += " ";
            if ((aType.getAmmoType() == AmmoType.T_AC) || (aType.getAmmoType() == AmmoType.T_MML) || (aType.getAmmoType() == AmmoType.T_SRM) || (aType.getAmmoType() == AmmoType.T_SRM_STREAK) || (aType.getAmmoType() == AmmoType.T_SRM_TORPEDO) || (aType.getAmmoType() == AmmoType.T_LRM) || (aType.getAmmoType() == AmmoType.T_LRM_STREAK) || (aType.getAmmoType() == AmmoType.T_LRM_TORPEDO) || (aType.getAmmoType() == AmmoType.T_MML) || (aType.getAmmoType() == AmmoType.T_AC) || (aType.getAmmoType() == AmmoType.T_AC_LBX) || (aType.getAmmoType() == AmmoType.T_AC_LBX_THB) || (aType.getAmmoType() == AmmoType.T_AC_ROTARY) || (aType.getAmmoType() == AmmoType.T_AC_ULTRA) || (aType.getAmmoType() == AmmoType.T_AC_ULTRA_THB) || (aType.getAmmoType() == AmmoType.T_MRM)
                    || (aType.getAmmoType() == AmmoType.T_MRM_STREAK) || (aType.getAmmoType() == AmmoType.T_ATM) || (aType.getAmmoType() == AmmoType.T_HAG) || (aType.getAmmoType() == AmmoType.T_EXLRM)) {
                shortName = shortName.replaceFirst(" ", " " + aType.getRackSize() + " ");
                shortName = shortName.replaceFirst("  Artemis", " Artemis");
            }
            shortName = shortName.trim();

            if (ammoHash.containsKey(shortName)) {
                int currentAmmo = ammoHash.get(shortName);
                currentAmmo += aType.getShots();
                ammoHash.put(shortName, currentAmmo);
            } else {
                int currentAmmo = aType.getShots();
                ammoHash.put(shortName, currentAmmo);
            }
        }
        if (ammoHash.keySet().size() == 0) {
            return;
        }
        StringBuffer sb = new StringBuffer("Ammo: ");
        g2d.setFont(UnitUtil.getNewFont(g2d, sb.toString(), false, 20, 7.0f));

        sb = new StringBuffer("Ammo: ");
        int linecount = 0;
        for (String ammo : ammoHash.keySet()) {
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
        }

        double stringLength = ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        linecount = (int) Math.floor(stringLength / 160);

        sb.setLength(0);
        sb.append("Ammo: ");

        g2d.drawString(sb.toString(), pointX, pointY - (linecount) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont()));
        pointX += ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        sb = new StringBuffer();
        int linesprinted = 0;
        int currentStringLength = 0;

        for (String ammo : ammoHash.keySet()) {
            currentStringLength = sb.length();
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
            if ((ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont()) > 160) && (linesprinted < linecount)) {
                sb.setLength(sb.length() - ((sb.length() - currentStringLength) + 2));
                g2d.drawString(sb.toString(), pointX, pointY - ((linecount - linesprinted) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
                linesprinted++;
                sb.setLength(0);
                sb.append("(");
                sb.append(ammo);
                sb.append(") ");
                sb.append(ammoHash.get(ammo));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
            g2d.drawString(sb.toString(), pointX, pointY - ((linecount - linesprinted) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
            pointY += ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont());
        }
    }
}