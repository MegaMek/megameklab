/*
 * MegaMekLab - Copyright (C) 2008
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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Bay;
import megamek.common.BipedMech;
import megamek.common.ConvFighter;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.LargeSupportTank;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.QuadMech;
import megamek.common.Tank;
import megamek.common.VTOL;
import megamek.common.WeaponType;
import megamek.common.weapons.BayWeapon;
import megamek.common.weapons.ISCompactNarc;

public class ImageHelper {
    public static String recordSheetPath = "./data/images/recordsheets/";
    public static String fluffPath = "./data/images/fluff/";
    public static String imagePath = "./data/images/";

    public static String imageMech = "mech";
    public static String imageAero = "aero";
    public static String imageBattleArmor = "BattleArmor";
    public static String imageVehicle = "vehicle";
    public static String imageLargeSupportVehicle = "largesupportvehicle";
    public static String imageProto = "protomech";
    public static String imageDropship = "dropship";

    public static Image armorPip = null;

    public static final String[] LOCATION_ABBRS =
        { "N", "LF", "RF", "A", "FL", "FR", "AL", "AR", "FL/FR", "AL/AR" };
    public static final int[] LOCATION_PRINT =
        { 0, 1, 2, 4, 5, 8, 9, 6, 7, 3 };
    public static final int LOC_FL = 4;
    public static final int LOC_FR = 5;
    public static final int LOC_AL = 6;
    public static final int LOC_AR = 7;
    public static final int LOC_FL_FR = 8;
    public static final int LOC_AL_AR = 9;

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
        } else if (unit instanceof LargeSupportTank) {
            if (unit.getOInternal(LargeSupportTank.LOC_TURRET) > 0) {
                recordSheet = new ImageIcon(path + "twvee-lgsupground-turret.png").getImage();
            } else {
                recordSheet = new ImageIcon(path + "twvee-lgsupground.png").getImage();
            }
        } else if (unit instanceof Tank) {
            if (advanced) {
                String imageName = "twvee-" + unit.getMovementModeAsString().toLowerCase().trim() + "-dualturret.png";
                recordSheet = new ImageIcon(path + imageName).getImage();
            } else {
                String imageName = "twvee-" + unit.getMovementModeAsString().toLowerCase().trim() + ".png";
                recordSheet = new ImageIcon(path + imageName).getImage();
            }
        } else if (unit instanceof Aero) {
            if (unit instanceof Dropship) {
                if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                    recordSheet = new ImageIcon(path + "twaerodyneds.png").getImage();
                } else {
                    recordSheet = new ImageIcon(path + "twspheroidds.png").getImage();
                }
            } else if (unit instanceof ConvFighter) {
                recordSheet = new ImageIcon(path + "twconventionalfighter.png").getImage();
            } else {
                recordSheet = new ImageIcon(path + "twaero.png").getImage();
            }
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

    public static Image getTurretImage(Entity unit) {
        Image table = null;
        String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;

        if ((unit instanceof Tank) && (unit.getMovementMode() == EntityMovementMode.WIGE)) {
            table = new ImageIcon(path + "twvee-wige-turret.png").getImage();
        } else {
            table = new ImageIcon(path + "twvee-turret.png").getImage();
        }
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

        if ((image == null) || (image.trim().length() < 1)) {
            return null;
        }

        Image fluff = null;

        String path = new File(fluffPath).getAbsolutePath() + File.separatorChar + image;

        if (!(new File(path).exists())) {

            path = new File(image).getAbsolutePath();
            if (!(new File(path).exists())) {
                return null;
            }
        }
        fluff = new ImageIcon(path).getImage();
        return fluff;
    }

    public static Image getFluffImage(Entity unit, String dir) {
        Image fluff = null;

        String path = new File(fluffPath).getAbsolutePath() + File.separatorChar + dir + File.separatorChar;

        fluff = getFluffImage(unit.getFluff().getMMLImagePath());

        if (fluff == null) {
            fluff = ImageHelper.getFluffPNG(unit, path);
        }

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
                equipmentName += " (R)";
            }
            if (eq.isTurretMounted()) {
                equipmentName += " (T)";
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

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
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
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                        } else {
                            g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    g2d.drawLine(heatPoint, (int) linePoint - 2, heatPoint + 6, (int) linePoint - 2);
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                    g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
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

    public static void printDropShipCargo(Dropship dropship, Graphics2D g2d, int pointY) {

        if (dropship.getTransportBays().size() < 1) {
            return;
        }

        int pointX = 22;
        double lineFeed = ImageHelper.getStringHeight(g2d, "H", g2d.getFont());

        Font font = UnitUtil.deriveFont(true, g2d.getFont().getSize2D());

        g2d.setFont(font);
        g2d.drawString("Cargo: ", pointX, pointY);

        pointY += lineFeed;

        font = UnitUtil.deriveFont(g2d.getFont().getSize2D());

        g2d.setFont(font);
        for (Bay bay : dropship.getTransportBays()) {
            g2d.drawString(ImageHelper.getBayString(bay), pointX, pointY);
            pointY += lineFeed;
        }

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
                // shortName = shortName.replaceFirst(" ", " " +
                // aType.getRackSize() + " ");
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

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(tank.locations());

        for (int pos = 0; pos <= tank.locations(); pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : tank.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || (!UnitUtil.isPrintableEquipment(eq.getType()))) {

                if (!(eq.getType() instanceof MiscType) || ((eq.getType() instanceof MiscType) && !eq.getType().hasFlag(MiscType.F_ENVIRONMENTAL_SEALING))) {
                    continue;
                }
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

        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            EquipmentInfo eqi = new EquipmentInfo();
            eqi.setTroopSpaceInfo(troopspace);
            equipmentLocations.get(Tank.LOC_BODY).put("Infantry cargo bay: " + (int) troopspace, eqi);
        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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

        ImageHelper.printVehicleAmmo(tank, g2d, (int) offset);
    }

    public static void printLargeSupportTankWeaponsNEquipment(LargeSupportTank tank, Graphics2D g2d) {
        ImageHelper.printLargeSupportTankWeaponsNEquipment(tank, g2d, 0);
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

        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            EquipmentInfo eqi = new EquipmentInfo();
            eqi.setTroopSpaceInfo(troopspace);
            equipmentLocations.get(Tank.LOC_BODY).put("Infantry cargo bay: " + troopspace, eqi);
        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
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
        float troopspace = tank.getTroopCarryingSpace();
        if (troopspace > 0) {
            EquipmentInfo eqi = new EquipmentInfo();

            eqi.setTroopSpaceInfo(troopspace);
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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
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
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", font);
        } else {
            g2d.drawString("C  Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);

    }

    public static void printC3sbName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Boosted Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", font);
        } else {
            g2d.drawString("C  Boosted Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

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
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", font);
        } else {
            g2d.drawString("C  Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        font = font.deriveFont(attrMap);
        g2d.setFont(font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
    }

    public static void printC3mbName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Boosted Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", font);
        } else {
            g2d.drawString("C  Boosted Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

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

    public static void printArmorPip(Graphics2D g2d, float width, float height) {

        if (armorPip == null) {
            String path = new File(recordSheetPath).getAbsolutePath() + File.separatorChar;
            armorPip = new ImageIcon(path + "armordot.png").getImage();
        }
        // armorPip.setAccelerationPriority(1);
        g2d.drawImage(armorPip, (int) width, (int) height, 6, 6, null);

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

    public static void drawLSVISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(5, 5);
        Dimension fillCircle = new Dimension(3, 3);
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

    public static void drawDropshipArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelper.drawDropshipArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawDropshipArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 6);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawDropshipISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(7, 7);
        Dimension fillCircle = new Dimension(5, 5);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
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

    public static void printDropshipWeaponsNEquipment(Dropship dropship, Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 111;
        int heatPoint = 135;
        int shtPoint = 151;
        int medPoint = 169;
        int longPoint = 192;
        int erPoint = 211;
        float linePoint = 209f;
        float lineFeed = 6.7f;
        float maxHeight = 260.9f;
        float stringHeight = 0f;
        float fontSize = 7.0f;
        boolean newLineNeeded = false;
        boolean hasCapital = false;
        boolean hasSubCapital = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(ImageHelper.LOCATION_ABBRS.length);
        ArrayList<Hashtable<String, EquipmentInfo>> capitalEquipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(ImageHelper.LOCATION_ABBRS.length);

        if (dropship.getMovementMode() == EntityMovementMode.AERODYNE) {
            linePoint = 201;
        }

        for (int pos = 0; pos < ImageHelper.LOCATION_ABBRS.length; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
            capitalEquipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : dropship.getEquipment()) {

            if ((eq.isWeaponGroup() || (eq.getType() instanceof AmmoType)) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());
            Hashtable<String, EquipmentInfo> capitalEqHash = capitalEquipmentLocations.get(eq.getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                switch (eq.getLocation()) {
                    case Aero.LOC_LWING:
                        eqHash = equipmentLocations.get(ImageHelper.LOC_AL);
                        capitalEqHash = capitalEquipmentLocations.get(ImageHelper.LOC_AL);
                        break;
                    case Aero.LOC_RWING:
                        eqHash = equipmentLocations.get(ImageHelper.LOC_AR);
                        capitalEqHash = capitalEquipmentLocations.get(ImageHelper.LOC_AR);
                        break;
                }
            }

            if ((eq.getType() instanceof BayWeapon)) {
                continue;
            }

            if ((eq.getType() instanceof WeaponType) && ((WeaponType) eq.getType()).isCapital()) {
                if (capitalEqHash.containsKey(equipmentName)) {
                    EquipmentInfo eqi = capitalEqHash.get(equipmentName);

                    if (eq.getType().getTechLevel() != eqi.techLevel) {
                        eqi = new EquipmentInfo(dropship, eq);
                    } else {
                        eqi.count++;
                    }
                    capitalEqHash.put(equipmentName, eqi);
                } else {
                    EquipmentInfo eqi = new EquipmentInfo(dropship, eq);
                    capitalEqHash.put(equipmentName, eqi);
                }
            } else {
                if (eqHash.containsKey(equipmentName)) {
                    EquipmentInfo eqi = eqHash.get(equipmentName);

                    if (eq.getType().getTechLevel() != eqi.techLevel) {
                        eqi = new EquipmentInfo(dropship, eq);
                    } else {
                        eqi.count++;
                    }
                    eqHash.put(equipmentName, eqi);
                } else {
                    EquipmentInfo eqi = new EquipmentInfo(dropship, eq);
                    eqHash.put(equipmentName, eqi);
                }
            }

        }

        int oppositePos = 0;
        int newPosition = 0;
        for (int pos = Aero.LOC_LWING; pos <= ImageHelper.LOC_AR; pos++) {
            switch (pos) {
                case Aero.LOC_LWING:
                    oppositePos = Aero.LOC_RWING;
                    newPosition = ImageHelper.LOC_FL_FR;
                    break;
                case ImageHelper.LOC_AL:
                    oppositePos = ImageHelper.LOC_AR;
                    newPosition = ImageHelper.LOC_AL_AR;
                    break;
                default:
                    continue;
            }

            Enumeration<String> keyList = equipmentLocations.get(pos).keys();
            while (keyList.hasMoreElements()) {
                String key = keyList.nextElement();
                EquipmentInfo currentInfo = equipmentLocations.get(pos).get(key);
                EquipmentInfo OppositeInfo = equipmentLocations.get(oppositePos).get(key);

                if (currentInfo.count == OppositeInfo.count) {
                    equipmentLocations.get(newPosition).put(key, currentInfo);
                    equipmentLocations.get(pos).remove(key);
                    equipmentLocations.get(oppositePos).remove(key);
                } else if (currentInfo.count > OppositeInfo.count) {
                    equipmentLocations.get(newPosition).put(key, OppositeInfo);
                    equipmentLocations.get(oppositePos).remove(key);
                    currentInfo.count -= OppositeInfo.count;
                } else {
                    equipmentLocations.get(newPosition).put(key, currentInfo);
                    equipmentLocations.get(pos).remove(key);
                    OppositeInfo.count -= currentInfo.count;
                }
            }

            keyList = capitalEquipmentLocations.get(pos).keys();
            while (keyList.hasMoreElements()) {
                String key = keyList.nextElement();
                if (!capitalEquipmentLocations.get(oppositePos).containsKey(key)) {
                    continue;
                }
                EquipmentInfo currentInfo = capitalEquipmentLocations.get(pos).get(key);
                EquipmentInfo OppositeInfo = capitalEquipmentLocations.get(oppositePos).get(key);

                if (currentInfo.count == OppositeInfo.count) {
                    capitalEquipmentLocations.get(newPosition).put(key, currentInfo);
                    capitalEquipmentLocations.get(pos).remove(key);
                    capitalEquipmentLocations.get(oppositePos).remove(key);
                } else if (currentInfo.count > OppositeInfo.count) {
                    capitalEquipmentLocations.get(newPosition).put(key, OppositeInfo);
                    capitalEquipmentLocations.get(oppositePos).remove(key);
                    currentInfo.count -= OppositeInfo.count;
                } else {
                    capitalEquipmentLocations.get(newPosition).put(key, currentInfo);
                    capitalEquipmentLocations.get(pos).remove(key);
                    OppositeInfo.count -= currentInfo.count;
                }
            }
        }

        Font font = UnitUtil.deriveFont(fontSize);
        g2d.setFont(font);

        font = ImageHelper.getDropShipWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, capitalEquipmentLocations, fontSize);
        fontSize = font.getSize2D();
        g2d.setFont(font);
        stringHeight = getStringHeight(g2d, "H", font);

        // linePoint -= stringHeight / 2;

        lineFeed = stringHeight;

        for (int pos = 0; pos < LOCATION_PRINT.length; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = capitalEquipmentLocations.get(LOCATION_PRINT[pos]);

            if (eqHash.isEmpty()) {
                continue;
            }

            if (!hasCapital) {
                hasCapital = true;

                g2d.drawString("Capital Scale", typePoint, linePoint);
                font = UnitUtil.getNewFont(g2d, "(1-12) (13-24) (25-40) (41-50)", true, 75, fontSize);
                g2d.setFont(font);
                g2d.drawString("(1-12) (13-24) (25-40) (41-50)", shtPoint, linePoint);
                linePoint += lineFeed;

                font = UnitUtil.getNewFont(g2d, "Bay", true, 68, fontSize);
                g2d.setFont(font);

                g2d.drawString("Bay", typePoint, linePoint);
                g2d.drawString("Loc", locPoint, linePoint);
                g2d.drawString("Ht", heatPoint, linePoint);
                g2d.drawString("SRV", shtPoint, linePoint);
                g2d.drawString("MRV", medPoint, linePoint);
                g2d.drawString("LRV", longPoint, linePoint);
                g2d.drawString("ERV", erPoint, linePoint);
                linePoint += lineFeed;

                font = UnitUtil.deriveFont(fontSize);
                g2d.setFont(font);
            }

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);
            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = String.format("%1$s %2$s", eqi.name.trim(), eqi.damage.trim());

                font = UnitUtil.getNewFont(g2d, name, false, 68, fontSize);
                g2d.setFont(font);

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
                font = UnitUtil.deriveFont(fontSize);
                g2d.setFont(font);

                String location = ImageHelper.LOCATION_ABBRS[LOCATION_PRINT[pos]];

                ImageHelper.printCenterString(g2d, location, font, locPoint + 5, linePoint);
                ImageHelper.printCenterString(g2d, Integer.toString(eqi.heat * eqi.count), font, heatPoint + 4, linePoint);
                if (eqi.shtRange > 0) {
                    g2d.drawString(String.format("%1$d", eqi.count * eqi.shtRange), shtPoint, (int) linePoint);
                } else {
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                }

                if (eqi.isAMS) {
                    g2d.drawString("Point Defense", medPoint, (int) linePoint);
                } else {
                    if (eqi.medRange > 0) {
                        g2d.drawString(String.format("%1$d", eqi.count * eqi.medRange), medPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.longRange > 0) {
                        g2d.drawString(String.format("%1$d", eqi.count * eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.erRange > 0) {
                        g2d.drawString(String.format("%1$d", eqi.count * eqi.erRange), erPoint, (int) linePoint);
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
                }
                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
            }
        }

        for (int pos = 0; pos < LOCATION_PRINT.length; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(LOCATION_PRINT[pos]);

            if (eqHash.isEmpty()) {
                continue;
            }

            if (!hasSubCapital) {
                hasSubCapital = true;

                g2d.drawString("Standard Scale", typePoint, linePoint);
                font = UnitUtil.getNewFont(g2d, "(1-6) (7-12) (13-20) (21-25)", true, 75, fontSize);
                g2d.setFont(font);
                g2d.drawString("(1-6) (7-12) (13-20) (21-25)", shtPoint, linePoint);
                linePoint += lineFeed;

                font = UnitUtil.getNewFont(g2d, "Bay", true, 68, fontSize);
                g2d.setFont(font);

                g2d.drawString("Bay", typePoint, linePoint);
                g2d.drawString("Loc", locPoint, linePoint);
                g2d.drawString("Ht", heatPoint, linePoint);
                g2d.drawString("SRV", shtPoint, linePoint);
                g2d.drawString("MRV", medPoint, linePoint);
                g2d.drawString("LRV", longPoint, linePoint);
                g2d.drawString("ERV", erPoint, linePoint);
                linePoint += lineFeed;

                font = UnitUtil.deriveFont(fontSize);
                g2d.setFont(font);
            }

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);
            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim() + " " + eqi.damage.trim();

                font = UnitUtil.getNewFont(g2d, name, false, 68, fontSize);
                g2d.setFont(font);

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
                font = UnitUtil.deriveFont(fontSize);
                g2d.setFont(font);

                String location = ImageHelper.LOCATION_ABBRS[LOCATION_PRINT[pos]];

                ImageHelper.printCenterString(g2d, location, font, locPoint + 5, linePoint);
                ImageHelper.printCenterString(g2d, Integer.toString(eqi.heat * eqi.count), font, heatPoint + 4, linePoint);
                if (eqi.shtRange > 0) {
                    String damage = String.format("%1$d (%2$d)", Math.round((eqi.count * eqi.shtRange) / 10), eqi.count * eqi.shtRange);
                    font = UnitUtil.getNewFont(g2d, damage, true, 17, fontSize);
                    g2d.setFont(font);
                    g2d.drawString(damage, shtPoint, (int) linePoint);
                    font = UnitUtil.deriveFont(fontSize);
                    g2d.setFont(font);
                } else {
                    g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                }

                if (eqi.isAMS) {
                    g2d.drawString("Point Defense", medPoint, (int) linePoint);
                } else {
                    if (eqi.medRange > 0) {
                        String damage = String.format("%1$d (%2$d)", Math.round((eqi.count * eqi.medRange) / 10), eqi.count * eqi.medRange);
                        font = UnitUtil.getNewFont(g2d, damage, true, 17, fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, medPoint, (int) linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
                    } else {
                        g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.longRange > 0) {
                        String damage = String.format("%1$d (%2$d)", Math.round((eqi.count * eqi.longRange) / 10), eqi.count * eqi.longRange);
                        font = UnitUtil.getNewFont(g2d, damage, true, 17, fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, longPoint, (int) linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
                    } else {
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.erRange > 0) {
                        String damage = String.format("%1$d (%2$d)", Math.round((eqi.count * eqi.erRange) / 10), eqi.count * eqi.erRange);
                        font = UnitUtil.getNewFont(g2d, damage, true, 17, fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, erPoint, (int) linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
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
                }
                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
            }
        }

        linePoint += lineFeed;
        ImageHelper.printDropShipCargo(dropship, g2d, (int) linePoint);

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

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
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

        ImageHelper.printVehicleAmmo(aero, g2d, -18);
        ImageHelper.printAeroFuel(aero, g2d);
    }

    public static void printBAArmor(BattleArmor ba, Graphics2D g2d, float offset) {
        float positionX = 27;
        float positionY = 187 + offset;
        String armorString = "Armor:";
        StringBuffer buffer = new StringBuffer();

        if (ba.isStealthActive()) {
            buffer.append(String.format("  %1$s (+%2$s/+%3$s/+%4$s)", ba.getStealthName(), ba.getShortStealthMod(), ba.getMediumStealthMod(), ba.getLongStealthMod()));
        }

        if (ba.isFireResistant()) {
            buffer.append(" Fire Resistant");
        }

        if (buffer.length() > 2) {

            Font font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);
            g2d.drawString(armorString, positionX, positionY);

            positionX += ImageHelper.getStringWidth(g2d, armorString, font);
            g2d.setFont(UnitUtil.getNewFont(g2d, buffer.toString(), false, 178, 7.0f));
            g2d.drawString(buffer.toString(), positionX, positionY);
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
        float linePoint = 149.1f + offset;
        float maxHeight = 40.0f;
        float lineFeed = 6.7f;
        float stringHeight = 0f;

        boolean newLineNeeded = false;

        ArrayList<ArrayList<EquipmentInfo>> equipmentLocations = new ArrayList<ArrayList<EquipmentInfo>>(BattleArmor.LOC_TROOPER_6 + 1);

        for (int pos = 0; pos <= BattleArmor.LOC_TROOPER_6; pos++) {
            equipmentLocations.add(pos, new ArrayList<EquipmentInfo>());
        }

        boolean hasNarcCompact = false;
        boolean hasMineLayer = false;
        Mounted glove = null;
        int numberOfGloves = 0;

        for (Mounted eq : ba.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableBAEquipment(eq.getType())) {
                continue;
            }

            if (!hasNarcCompact && (eq.getType() instanceof ISCompactNarc)) {
                hasNarcCompact = true;
            } else if (hasNarcCompact && (eq.getType() instanceof ISCompactNarc)) {
                continue;
            }
            if (!hasMineLayer && eq.getType().hasFlag(MiscType.F_MINE) && eq.getType().hasFlag(MiscType.F_BA_EQUIPMENT)) {
                hasMineLayer = true;
            } else if (hasMineLayer && eq.getType().hasFlag(MiscType.F_MINE) && eq.getType().hasFlag(MiscType.F_BA_EQUIPMENT)) {
                continue;
            }

            if (UnitUtil.isManipulator(eq)) {

                if ((glove != null) && (glove.getName().equals(eq.getName()))) {
                    numberOfGloves++;
                    continue;
                } else {
                    glove = eq;
                    numberOfGloves = 1;
                }

            }

            equipmentLocations.get(eq.getLocation()).add(new EquipmentInfo(ba, eq));

        }

        if (!ba.isFireResistant() && !ba.isStealthActive()) {
            maxHeight += lineFeed;
        }

        Font font = ImageHelper.getBattleArmorWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, 7.0f);
        g2d.setFont(font);
        stringHeight = getStringHeight(g2d, "H", font);

        // linePoint -= stringHeight / 2;

        lineFeed = stringHeight;

        for (int pos = BattleArmor.LOC_SQUAD; pos <= BattleArmor.LOC_TROOPER_6; pos++) {

            ArrayList<EquipmentInfo> equipmentList = equipmentLocations.get(pos);

            if (equipmentList.size() < 1) {
                continue;
            }
            boolean indented = false;
            if ((pos != BattleArmor.LOC_SQUAD) && !hasNarcCompact && !hasMineLayer) {
                String loc = ba.getLocationName(pos);
                g2d.setFont(UnitUtil.getNewFont(g2d, loc, false, 68, font.getSize()));
                g2d.drawString(loc, typePoint, linePoint);
                g2d.setFont(font);
                linePoint += lineFeed;
                typePoint += 5;
                indented = true;
            }

            int count = 0;

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                String name = eqi.name.trim();

                if (eqi.isManipulator && (numberOfGloves > 1)) {
                    name += " (2)";
                }

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 88, font.getSize()));

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
                        g2d.setFont(UnitUtil.getNewFont(g2d, eqi.damage, false, 30, font.getSize()));
                        ImageHelper.printCenterString(g2d, eqi.damage, g2d.getFont(), damagePoint, linePoint);
                        g2d.setFont(font);
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, (int) linePoint);
                        } else {
                            g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        }
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
                    }
                } else {
                    g2d.setFont(UnitUtil.getNewFont(g2d, eqi.damage, false, 30, font.getSize()));
                    ImageHelper.printCenterString(g2d, eqi.damage, g2d.getFont(), damagePoint, linePoint);
                    g2d.setFont(font);
                    if (eqi.minRange > 0) {
                        g2d.drawString(Integer.toString(eqi.minRange), minPoint, (int) linePoint);
                    } else {
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    }
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
                }

                if (eqi.hasAmmo) {
                    if (!newLineNeeded) {
                        newLineNeeded = true;
                    }
                    StringBuilder ammoString = new StringBuilder("Ammo ");

                    if (eqi.isCompactNarc) {
                        for (int baPos = 0; baPos < ba.getNumberActiverTroopers(); baPos++) {
                            for (int ammoCount = 0; ammoCount < eqi.ammoCount; ammoCount++) {
                                ammoString.append("O ");
                            }
                            ammoString.append("/ ");
                        }
                        ammoString.setLength(ammoString.length() - 2);
                    } else {
                        for (int ammoCount = 1; ammoCount <= eqi.ammoCount; ammoCount++) {
                            ammoString.append("O ");
                        }
                    }

                    g2d.setFont(UnitUtil.getNewFont(g2d, ammoString.toString(), false, 138, font.getSize()));
                    g2d.drawString(ammoString.toString(), typePoint + 5, (int) (linePoint + lineFeed));
                    g2d.setFont(font);
                }

                if (eqi.isBAMineLayer) {
                    StringBuilder ammoString = new StringBuilder("Ammo ");
                    for (int baPos = 0; baPos < ba.getNumberActiverTroopers(); baPos++) {
                        ammoString.append("O O / ");
                    }
                    ammoString.setLength(ammoString.length() - 2);
                    g2d.setFont(UnitUtil.getNewFont(g2d, ammoString.toString(), false, 138, font.getSize()));
                    g2d.drawString(ammoString.toString(), typePoint + 5, (int) (linePoint + lineFeed));
                    g2d.setFont(font);
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
                if (indented) {
                    typePoint -= 5;
                }
            }
            if (ba.isBurdened()) {
                String burdenInfo = "must detach missiles before jumping or swarm/leg attacks";
                g2d.setFont(UnitUtil.getNewFont(g2d, burdenInfo, false, 175, 7.0f));
                g2d.drawString(burdenInfo, typePoint, linePoint);
                g2d.setFont(font);
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

        ArrayList<Vector<EquipmentInfo>> equipmentLocations = new ArrayList<Vector<EquipmentInfo>>(Protomech.LOC_MAINGUN + 1);

        for (int pos = 0; pos <= Protomech.LOC_MAINGUN; pos++) {
            equipmentLocations.add(pos, new Vector<EquipmentInfo>());
        }

        for (Mounted eq : proto.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Vector<EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                equipmentName += "(R)";
            }

            EquipmentInfo eqi = new EquipmentInfo(proto, eq);
            eqi.name = equipmentName;
            eqHash.add(eqi);

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Protomech.LOC_HEAD; pos <= Protomech.LOC_MAINGUN; pos++) {

            Vector<EquipmentInfo> eqVector = equipmentLocations.get(pos);

            if (eqVector.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqVector) {
                equipmentList.add(eqi);
            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

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
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false);
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false);
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

        if (proto.hasMyomerBooster()) {
            g2d.drawString("Protomech Myomer Booster", 115, 155 + (int) offset);
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
                // shortName = shortName.replaceFirst(" ", " " +
                // aType.getRackSize() + " ");
                shortName = shortName.replaceFirst("  Artemis", " Artemis");
            }
            shortName = shortName.trim();

            if (ammoHash.containsKey(shortName)) {
                int currentAmmo = ammoHash.get(shortName);
                currentAmmo += ammo.getShotsLeft();
                ammoHash.put(shortName, currentAmmo);
            } else {
                int currentAmmo = ammo.getShotsLeft();
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

    public static void printRotatedText(Graphics2D g2d, String text, double angle, int xLoc, int yLoc) {

        Font font = g2d.getFont();

        FontMetrics fm = g2d.getFontMetrics(font);

        int width = fm.stringWidth(text);
        int height = fm.getHeight();

        AffineTransform at = new AffineTransform();

        // scale image
        at.scale(2.5, 2.5);

        // rotate 45 degrees around image center
        at.rotate(angle * Math.PI / 180.0, width / 2.0, height / 2.0);

        /*
         * AffineTransform translationTransform; translationTransform =
         * findTranslation(at, sourceBI);
         * at.preConcatenate(translationTransform);
         */

        AffineTransform oldTran = g2d.getTransform();
        g2d.setTransform(at);
        // instantiate and apply affine transformation filter
        g2d.drawString(text, xLoc, yLoc);

        g2d.setTransform(oldTran);
    }

    public static Font getBattleArmorWeaponsNEquipmentFont(Graphics2D g2d, boolean bold, float stringHeight, ArrayList<ArrayList<EquipmentInfo>> equipmentLocations, float pointSize) {

        Font font = g2d.getFont();

        int weaponCount = 0;
        for (int pos = BattleArmor.LOC_SQUAD; pos <= BattleArmor.LOC_TROOPER_6; pos++) {

            ArrayList<EquipmentInfo> equipmentList = equipmentLocations.get(pos);

            if (equipmentList.size() < 1) {
                continue;
            }

            if (pos != BattleArmor.LOC_SQUAD) {
                weaponCount++;
            }

            for (EquipmentInfo eqi : equipmentList) {

                weaponCount++;
                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        weaponCount++;
                    } else if (eqi.isATM) {
                        weaponCount++;
                    } /*
                       * else { if (ImageHelper.getStringWidth(g2d,
                       * eqi.damage.trim(), font) > 22) { weaponCount++; } }
                       */

                    if (eqi.hasAmmo) {
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

    public static Font getDropShipWeaponsNEquipmentFont(Graphics2D g2d, boolean bold, float stringHeight, ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations, ArrayList<Hashtable<String, EquipmentInfo>> capitalEquipmentLocations, float pointSize) {

        Font font = g2d.getFont();
        boolean hasCapital = false;
        boolean hasSubCapital = false;

        int weaponCount = 1;
        for (int pos = Aero.LOC_NOSE; pos <= ImageHelper.LOC_AL_AR; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(pos);
            if (eqHash.size() < 1) {
                continue;
            }

            hasSubCapital = true;

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
                    /*
                     * else { if (ImageHelper.getStringWidth(g2d,
                     * eqi.damage.trim(), font) > 22) { weaponCount++; } }
                     */

                    if (eqi.hasAmmo) {
                        weaponCount++;
                    }
                }
            }
        }

        for (int pos = Aero.LOC_NOSE; pos <= Aero.LOC_WINGS; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = capitalEquipmentLocations.get(pos);
            if (eqHash.size() < 1) {
                continue;
            }

            hasCapital = true;

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
                    /*
                     * else { if (ImageHelper.getStringWidth(g2d,
                     * eqi.damage.trim(), font) > 22) { weaponCount++; } }
                     */

                    if (eqi.hasAmmo) {
                        weaponCount++;
                    }
                }
            }
        }

        if (hasCapital) {
            weaponCount += 2;
        }

        if (hasSubCapital) {
            weaponCount += 2;
        }

        while (((ImageHelper.getStringHeight(g2d, "H", font) * weaponCount) > stringHeight) && (pointSize > 0)) {
            pointSize -= .1;
            font = UnitUtil.deriveFont(bold, pointSize);
        }

        return font;
    }

    public static String getBayString(Bay bay) {
        StringBuffer returnString = new StringBuffer(bay.getUnusedString());

        if (bay.getDoors() > 0) {
            returnString.append("(");
            returnString.append(bay.getDoors());
            returnString.append(" doors)");
        }

        return returnString.toString();
    }
}