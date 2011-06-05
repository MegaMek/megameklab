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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
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
import megamek.common.SmallCraft;
import megamek.common.Tank;
import megamek.common.VTOL;

public class ImageHelper {
    public static String recordSheetPath = "./data/images/recordsheets/";
    public static String fluffPath = "./data/images/fluff/";
    public static String imagePath = "./data/images/";

    public static String imageMech = "mech";
    public static String imageAero = "aero";
    public static String imageBattleArmor = "BattleArmor";
    public static String imageVehicle = "vehicle";
    public static String imageNaval = "naval";
    public static String imageLargeSupportVehicle = "largesupportvehicle";
    public static String imageProto = "protomech";
    public static String imageDropship = "dropship";

    public static Image armorPip = null;

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
            if (unit.getMovementMode() == EntityMovementMode.NAVAL) {
                String imageName = "twnaval.png";
                recordSheet = new ImageIcon(path + imageName).getImage();
            } else if (advanced) {
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
            } else if (unit instanceof SmallCraft) {
                if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                    recordSheet = new ImageIcon(path + "twaero-smallcraft.png").getImage();
                } else {
                    recordSheet = new ImageIcon(path + "twspheroid-smallcraft.png").getImage();
                }

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

        fluff = ImageHelper.getFluffImage(unit.getFluff().getMMLImagePath());

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

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableEquipment(eq.getType(), true)) {
                continue;
            }

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                equipmentName += " (R)";
            }
            if (eq.isMechTurretMounted()) {
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

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 65, 7.0f));

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
                    } else if (eqi.isAMS) {
                        g2d.drawLine(damagePoint, (int) linePoint - 2, damagePoint + 6, (int) linePoint - 2);
                        g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                        g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
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
                        if (eqi.shtRange > 0) {
                            g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        } else {
                            g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.medRange > 0) {
                            g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        }  else {
                            g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, linePoint);
                        } else {
                            g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    if (eqi.heat > 0) {
                        g2d.drawString(Integer.toString(eqi.heat), heatPoint, linePoint);
                    } else {
                        g2d.drawLine(heatPoint, (int) linePoint - 2, heatPoint + 6, (int) linePoint - 2);
                    }
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

    public static void printVehicleAmmo(Entity vehicle, Graphics2D g2d, int offset) {

        int pointY = 340 + offset;
        int pointX = 22;

        HashMap<String, Integer> ammoHash = new HashMap<String, Integer>();

        g2d.setFont(UnitUtil.deriveFont(7.0f));

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
        for (Mounted misc : vehicle.getMisc()) {
            if (misc.getType().hasFlag(MiscType.F_SENSOR_DISPENSER)) {
                if (ammoHash.get("Remote Sensors") == null) {
                    ammoHash.put("Remote Sensors", misc.getShotsLeft());
                } else {
                    ammoHash.put("Remote Sensors", misc.getShotsLeft() + ammoHash.get("Remote Sensors"));
                }
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

    public static void printC3iName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Improved C  CPU", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Improved C", c3Font);
        } else {
            g2d.drawString("Improved C  CPU", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "Improved C", c3Font);
        }
        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3sName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {

        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C  Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);

    }

    public static void printBC3iName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Improved BC", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Improved BC", c3Font);
        } else {
            g2d.drawString("Improved BC", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "Improved BC", c3Font);
        }
        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printBC3Name(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O BC", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O BC", c3Font);
        } else {
            g2d.drawString("BC", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "BC", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);

    }

    public static void printC3sbName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Boosted Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C  Boosted Slave", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3mName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C  Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3mbName(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C  Boosted Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C  Boosted Master", lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printMashCore(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored, Entity entity) {
        int theaters = entity.countWorkingMisc(MiscType.F_MASH_EXTRA) + 1;
        String theaterString = theaters > 1 ? " theaters)" : " theater)";
        String printString;
        if (isArmored) {
            printString = "O MASH Equipment (" + theaters + theaterString;
        } else {
            printString = "MASH Equipment (" + theaters + theaterString;
        }
        g2d.setFont(UnitUtil.getNewFont(g2d, printString, false, 85, font.getSize2D()));
        g2d.drawString(printString, lineStart, linePoint);
        g2d.setFont(UnitUtil.deriveFont(7));
    }

    public static void printDroneControl(Graphics2D g2d, int lineStart, float linePoint, Font font, boolean isArmored, Entity entity) {
        int drones = entity.countWorkingMisc(MiscType.F_DRONE_EXTRA);
        String droneString = drones > 1 ? " drones)" : " drone)";
        String printString;
        if (isArmored) {
            printString = "O Drone Carrier Control System (" + drones + droneString;
        } else {
            printString = "Drone Carrier Control System (" + drones + droneString;
        }
        g2d.setFont(UnitUtil.getNewFont(g2d, printString, false, 85, font.getSize2D()));
        g2d.drawString(printString, lineStart, linePoint);
        g2d.setFont(UnitUtil.deriveFont(7));
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

    public static void drawISPip(Graphics2D g2d, float width, float height) {
        Font font = new Font("Arial", Font.PLAIN, 6);
        g2d.setFont(font);
        g2d.drawString("O", width, height);
    }

    public static void drawHeatSinkPip(Graphics2D g2d, float width, float height) {
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
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

    public static Vector<float[]> getPointsAlongLine(float[] start, float[] end, int points) {

        float xDifference = end[0] - start[0];
        float yDifference = end[1] - start[1];

        float xStep = xDifference / (points - 1);
        float yStep = yDifference / (points - 1);

        Vector<float[]> result = new Vector<float[]>();
        if (points == 1) {
            xStep = xDifference / 2;
            yStep = yDifference / 2;
            float x = start[0] + (xStep);
            float y = start[1] + (yStep);
            result.add(new float[]
                { x, y });
            return result;
        } else if (points == 0) {
            return result;
        }

        for (int i = 0; i < points; i++) {
            float x = start[0] + (xStep * i);
            float y = start[1] + (yStep * i);

            result.add(new float[]
                { x, y });
        }
        return result;
    }

}