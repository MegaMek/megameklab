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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.ConvFighter;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.LandAirMech;
import megamek.common.LargeSupportTank;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.QuadMech;
import megamek.common.SmallCraft;
import megamek.common.Tank;
import megamek.common.TechConstants;
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

        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
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
        } else if ((unit instanceof LargeSupportTank)
                || ((unit instanceof Tank) && ((Tank) unit).isSuperHeavy())) {
            if (unit.getOInternal(LargeSupportTank.LOC_TURRET) > 0) {
                recordSheet = new ImageIcon(path
                        + "twvee-lgsupground-turret.png").getImage();
            } else {
                recordSheet = new ImageIcon(path + "twvee-lgsupground.png")
                        .getImage();
            }
        } else if (unit instanceof Tank) {
            if ((unit.getMovementMode() == EntityMovementMode.NAVAL)
                    || (unit.getMovementMode() == EntityMovementMode.SUBMARINE)
                    || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL)) {
                if (unit.getOInternal(((Tank) unit).getLocTurret()) > 0) {
                    recordSheet = new ImageIcon(path + "twnaval-turret.png")
                            .getImage();
                } else {
                    recordSheet = new ImageIcon(path + "twnaval.png")
                            .getImage();
                }
            } else if (advanced) {
                String imageName = "twvee-"
                        + unit.getMovementModeAsString().toLowerCase().trim()
                        + "-dualturret.png";
                recordSheet = new ImageIcon(path + imageName).getImage();
            } else {
                String imageName = "twvee-"
                        + unit.getMovementModeAsString().toLowerCase().trim()
                        + ".png";
                recordSheet = new ImageIcon(path + imageName).getImage();
            }
        } else if (unit instanceof Aero) {
            if (unit instanceof Dropship) {
                if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                    recordSheet = new ImageIcon(path + "twaerodyneds.png")
                            .getImage();
                } else {
                    recordSheet = new ImageIcon(path + "twspheroidds.png")
                            .getImage();
                }
            } else if (unit instanceof ConvFighter) {
                recordSheet = new ImageIcon(path + "twconventionalfighter.png")
                        .getImage();
            } else if (unit instanceof SmallCraft) {
                if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                    recordSheet = new ImageIcon(path + "twaero-smallcraft.png")
                            .getImage();
                } else {
                    recordSheet = new ImageIcon(path
                            + "twspheroid-smallcraft.png").getImage();
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
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image image = new ImageIcon(path + "gyropip.png").getImage();
        return image;
    }

    public static Image getUMImage() {
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image image = new ImageIcon(path + "UM.png").getImage();
        return image;
    }

    public static Image getShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shields.png").getImage();
        return image;
    }

    public static Image getRightShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shield-right.png")
                .getImage();
        return image;
    }

    public static Image getLeftShieldImage() {
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image image = new ImageIcon(path + "twbiped-shield-left.png")
                .getImage();
        return image;
    }
    
    /**
     * Checks for a fluff image for the unit starting with any file explicitly associated with the
     * unit then in the default directory for the unit type for a file consisting of the name of the
     * unit with an image format extension.
     * 
     * @param unit The unit to find a fluff image for
     * @param dir  The directory to check for a default image based on unit name
     * @return     A file to use for the fluff image, or null if no file is found.
     */
    public static File getFluffFile(Entity unit, String dir) {
        String path = new File(fluffPath).getAbsolutePath();
        File f = null;
        
        if (unit.getFluff().getMMLImagePath().length() > 0) {
            f = new File(unit.getFluff().getMMLImagePath());
            if (f.exists()) {
                return f;
            }
            f = new File(path, unit.getFluff().getMMLImagePath());
            if (f.exists()) {
                return f;
            }
        }

        path = new File(path, dir).getAbsolutePath();
        final String [] EXTENSIONS = { ".png", ".PNG", ".jpg", ".JPG", ".jpeg", ".JPEG", ".gif", ".GIF" };
        for (String ext : EXTENSIONS) {
            f = new File(path, unit.getShortNameRaw() + ext);
            if (f.exists()) {
                return f;
            }
        }
        for (String ext : EXTENSIONS) {
            f = new File(path, unit.getChassis() + ext);
            if (f.exists()) {
                return f;
            }
        }
        f = new File(path, "hud.png");
        if (f.exists()) {
            return f;
        }
        return null;
    }

    public static Image getFluffImage(String image) {

        if ((image == null) || (image.trim().length() < 1)) {
            return null;
        }

        Image fluff = null;

        String path = new File(fluffPath).getAbsolutePath()
                + File.separatorChar + image;

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

        String path = new File(fluffPath).getAbsolutePath()
                + File.separatorChar + dir + File.separatorChar;

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

        String fluffFile = path + unit.getChassis() + " " + unit.getModel()
                + ".png";
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

        String fluffFile = path + unit.getChassis() + " " + unit.getModel()
                + ".jpg";
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

        String fluffFile = path + unit.getChassis() + " " + unit.getModel()
                + ".gif";
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
        ImageHelper.printMechWeaponsNEquipment(mech, g2d, 0, 0);
    }

    public static void printMechWeaponsNEquipment(Mech mech, Graphics2D g2d,
            int leftMargin, int topMargin) {
        int qtyPoint = 26 + leftMargin;
        int typePoint = 38 + leftMargin;
        int locPoint = 105 + leftMargin;
        int heatPoint = 118 + leftMargin;
        int damagePoint = 145 + leftMargin;
        int minPoint = 167 + leftMargin;
        int shtPoint = 183 + leftMargin;
        int medPoint = 199 + leftMargin;
        int longPoint = 215 + leftMargin;
        float linePoint = 202f + topMargin;

        float lineFeed = 6.7f;

        boolean newLineNeeded = false;

        ArrayList<Hashtable<String, EquipmentInfo>> equipmentLocations = new ArrayList<Hashtable<String, EquipmentInfo>>(
                Mech.LOC_LLEG + 1);

        for (int pos = 0; pos <= Mech.LOC_LLEG; pos++) {
            equipmentLocations.add(pos, new Hashtable<String, EquipmentInfo>());
        }

        for (Mounted eq : mech.getEquipment()) {

            if ((eq.getType() instanceof AmmoType)
                    || (eq.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(eq.getType(), true)) {
                continue;
            }

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations.get(eq
                    .getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                equipmentName += " (R)";
            }
            if (eq.isMechTurretMounted()) {
                equipmentName += " (T)";
            }

            if (eqHash.containsKey(equipmentName)) {
                EquipmentInfo eqi = eqHash.get(equipmentName);

                if (eq.getType().getTechLevel(mech.getTechLevelYear()) != eqi.techLevel) {
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
        HashMap<TextAttribute, Object> strikeThroughAttr = new HashMap<TextAttribute, Object>();
        strikeThroughAttr.put(TextAttribute.STRIKETHROUGH,
                TextAttribute.STRIKETHROUGH_ON);

        for (int pos = Mech.LOC_HEAD; pos <= Mech.LOC_LLEG; pos++) {

            Hashtable<String, EquipmentInfo> eqHash = equipmentLocations
                    .get(pos);

            if (eqHash.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqHash.values()) {
                equipmentList.add(eqi);

            }

            Collections.sort(equipmentList,
                    StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }
                font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();

                Font nameFont = UnitUtil.getNewFont(g2d, name, false, 60, 7.0f);
                if (eqi.isDestroyed) {
                    nameFont = nameFont.deriveFont(strikeThroughAttr);
                }
                g2d.setFont(nameFont);

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper
                            .printC3iName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3EM) {
                    ImageHelper
                            .printC3EmName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper
                            .printC3sName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper
                            .printC3mName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper
                            .printC3sbName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper
                            .printC3mbName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3REMOTESENSOR) {
                    ImageHelper
                            .printC3RemoteSensorName(
                                    g2d,
                                    typePoint,
                                    linePoint,
                                    font,
                                    false,
                                    mech.isMixedTech()
                                            && TechConstants.isClan(mech
                                                    .getTechLevel()));
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                Font locFont = UnitUtil.getNewFont(g2d, eqi.loc, false, 15,
                        7.0f);
                g2d.setFont(locFont);

                ImageHelper.printCenterString(g2d, eqi.loc, font, locPoint,
                        linePoint);
                g2d.setFont(UnitUtil.deriveFont(7));
                if (eqi.isWeapon) {
                    g2d.drawString(Integer.toString(eqi.heat), heatPoint,
                            linePoint);

                    if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,C,S]", font,
                                damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font,
                                damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font,
                                damagePoint, linePoint);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint
                        // + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,C,S]", font,
                                damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font,
                                damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font,
                                damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font,
                                damagePoint, linePoint);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint
                        // + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);
                    } else if (eqi.isAMS) {
                        ImageHelper.printCenterString(g2d, "\u2014 [PD]", font,
                                damagePoint, linePoint);
                        // g2d.drawLine(damagePoint - 9, (int) linePoint - 2,
                        // damagePoint - 3, (int) linePoint - 2);
                        // ImageHelper.printCenterString(g2d, "[PD]", font,
                        // damagePoint+4, linePoint);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        g2d.drawString("\u2014", shtPoint, linePoint);
                        g2d.drawString("\u2014", medPoint, linePoint);
                        g2d.drawString("\u2014", longPoint, linePoint);
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint
                        // + 6, (int) linePoint - 2);
                        // g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint
                        // + 6, (int) linePoint - 2);
                        // g2d.drawLine(medPoint, (int) linePoint - 2, medPoint
                        // + 6, (int) linePoint - 2);
                        // g2d.drawLine(longPoint, (int) linePoint - 2,
                        // longPoint + 6, (int) linePoint - 2);
                    } else if (eqi.isCenturion) {
                        ImageHelper.printCenterString(g2d, "0", font,
                                damagePoint, linePoint);
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint
                        // + 6, (int) linePoint - 2);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        g2d.drawString("6(1)", shtPoint - 1, linePoint);
                        g2d.drawString("12(2)", medPoint - 6, linePoint);
                        g2d.drawString("18(3)", longPoint - 4, linePoint);
                    } else {
                        if (ImageHelper.getStringWidth(g2d, eqi.damage.trim(),
                                font) > 22) {
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(
                                    g2d,
                                    eqi.damage.substring(0,
                                            eqi.damage.indexOf('[')), font,
                                    damagePoint, linePoint);
                            font = UnitUtil.deriveFont(7.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage
                                    .substring(eqi.damage.indexOf('[')), font,
                                    damagePoint, (linePoint + lineFeed) - 1.0f);
                            newLineNeeded = true;
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage,
                                    font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange),
                                    minPoint, linePoint);
                        } else {
                            g2d.drawString("\u2014", minPoint, linePoint);
                            // g2d.drawLine(minPoint, (int) linePoint - 2,
                            // minPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.shtRange > 0) {
                            g2d.drawString(Integer.toString(eqi.shtRange),
                                    shtPoint, linePoint);
                        } else {
                            g2d.drawString("\u2014", shtPoint, linePoint);
                            // g2d.drawLine(shtPoint, (int) linePoint - 2,
                            // shtPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.medRange > 0) {
                            g2d.drawString(Integer.toString(eqi.medRange),
                                    medPoint, linePoint);
                        } else {
                            g2d.drawString("\u2014", medPoint, linePoint);
                            // g2d.drawLine(medPoint, (int) linePoint - 2,
                            // medPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange),
                                    longPoint, linePoint);
                        } else {
                            g2d.drawString("\u2014", longPoint, linePoint);
                            // g2d.drawLine(longPoint, (int) linePoint - 2,
                            // longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    if (eqi.heat > 0) {
                        g2d.drawString(Integer.toString(eqi.heat), heatPoint,
                                linePoint);
                    } else {
                        g2d.drawString("\u2014", heatPoint, linePoint);
                        // g2d.drawLine(heatPoint, (int) linePoint - 2,
                        // heatPoint + 6, (int) linePoint - 2);
                    }
                    ImageHelper.printCenterString(g2d, eqi.damage, font,
                            damagePoint - 2, linePoint);
                    g2d.drawString("\u2014", minPoint, linePoint);
                    g2d.drawString("\u2014", shtPoint, linePoint);
                    g2d.drawString("\u2014", medPoint, linePoint);
                    // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6,
                    // (int) linePoint - 2);
                    // g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6,
                    // (int) linePoint - 2);
                    // g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6,
                    // (int) linePoint - 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange),
                                longPoint, linePoint);
                    } else {
                        g2d.drawString("\u2014", longPoint, linePoint);
                        // g2d.drawLine(longPoint, (int) linePoint - 2,
                        // longPoint + 6, (int) linePoint - 2);
                    }
                }

                if (eqi.hasArtemis) {
                    g2d.drawString("w/Artemis IV FCS", typePoint, linePoint
                            + lineFeed);
                    newLineNeeded = true;
                } else if (eqi.hasArtemisV) {
                    g2d.drawString("w/Artemis V FCS", typePoint, linePoint
                            + lineFeed);
                    newLineNeeded = true;
                } else if (eqi.hasApollo) {
                    g2d.drawString("w/Apollo FCS", typePoint, linePoint
                            + lineFeed);
                    newLineNeeded = true;
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
            }
        }
        if (mech instanceof LandAirMech) {
            ImageHelper.printLAMFuel((LandAirMech) mech, g2d, qtyPoint);
        }
    }

    public static void printVehicleAmmo(Entity vehicle, Graphics2D g2d,
            int offset) {
        printVehicleAmmo(vehicle, g2d, offset, 0);
    }

    public static void printVehicleAmmo(Entity vehicle, Graphics2D g2d,
            float yoffset, float xoffset) {
        float pointY = 341 + yoffset;
        float pointX = 22 + xoffset;

        HashMap<String, Integer> ammoHash = new HashMap<String, Integer>();

        g2d.setFont(UnitUtil.deriveFont(7.0f));

        for (Mounted ammo : vehicle.getAmmo()) {
            // don't print one shot ammo
            if (ammo.getLocation() == Entity.LOC_NONE) {
                continue;
            }
            AmmoType aType = (AmmoType) ammo.getType();
            String shortName = aType.getShortName().replace("Ammo", "");
            shortName = shortName.replace('(', '.').replace(')', '.')
                    .replace(".Clan.", "");
            shortName = shortName.replace("-capable", "");
            shortName += " ";
            if ((aType.getAmmoType() == AmmoType.T_AC)
                    || (aType.getAmmoType() == AmmoType.T_MML)
                    || (aType.getAmmoType() == AmmoType.T_SRM)
                    || (aType.getAmmoType() == AmmoType.T_SRM_STREAK)
                    || (aType.getAmmoType() == AmmoType.T_SRM_TORPEDO)
                    || (aType.getAmmoType() == AmmoType.T_LRM)
                    || (aType.getAmmoType() == AmmoType.T_LRM_STREAK)
                    || (aType.getAmmoType() == AmmoType.T_LRM_TORPEDO)
                    || (aType.getAmmoType() == AmmoType.T_MML)
                    || (aType.getAmmoType() == AmmoType.T_AC)
                    || (aType.getAmmoType() == AmmoType.T_AC_LBX)
                    || (aType.getAmmoType() == AmmoType.T_AC_LBX_THB)
                    || (aType.getAmmoType() == AmmoType.T_AC_ROTARY)
                    || (aType.getAmmoType() == AmmoType.T_AC_ULTRA)
                    || (aType.getAmmoType() == AmmoType.T_AC_ULTRA_THB)
                    || (aType.getAmmoType() == AmmoType.T_MRM)
                    || (aType.getAmmoType() == AmmoType.T_MRM_STREAK)
                    || (aType.getAmmoType() == AmmoType.T_ATM)
                    || (aType.getAmmoType() == AmmoType.T_HAG)
                    || (aType.getAmmoType() == AmmoType.T_EXLRM)) {
                // shortName = shortName.replaceFirst(" ", " " +
                // aType.getRackSize() + " ");
                shortName = shortName.replaceFirst("  Artemis", " Artemis");
            }
            shortName = shortName.trim();

            if (ammoHash.containsKey(shortName)) {
                int currentAmmo = ammoHash.get(shortName);
                currentAmmo += ammo.getUsableShotsLeft();
                ammoHash.put(shortName, currentAmmo);
            } else {
                int currentAmmo = ammo.getUsableShotsLeft();
                ammoHash.put(shortName, currentAmmo);
            }
        }
        for (Mounted misc : vehicle.getMisc()) {
            if (misc.getType().hasFlag(MiscType.F_SENSOR_DISPENSER)) {
                if (ammoHash.get("Remote Sensors") == null) {
                    ammoHash.put("Remote Sensors", misc.getUsableShotsLeft());
                } else {
                    ammoHash.put("Remote Sensors", misc.getUsableShotsLeft()
                            + ammoHash.get("Remote Sensors"));
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

        double stringLength = ImageHelper.getStringWidth(g2d, sb.toString(),
                g2d.getFont());
        linecount = (int) Math.floor(stringLength / 160);

        sb.setLength(0);
        sb.append("Ammo: ");

        if (vehicle.hasWorkingMisc(MiscType.F_CASE, -1) || vehicle.isClan()) {
            sb = new StringBuffer("Ammo (CASE): ");
        }

        g2d.drawString(
                sb.toString(),
                pointX,
                pointY
                        - ((linecount) * ImageHelper.getStringHeight(g2d,
                                sb.toString(), g2d.getFont())));
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
            if ((ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont()) > 160)
                    && (linesprinted < linecount)) {
                sb.setLength(sb.length()
                        - ((sb.length() - currentStringLength) + 2));
                g2d.drawString(
                        sb.toString(),
                        pointX,
                        pointY
                                - ((linecount - linesprinted) * ImageHelper
                                        .getStringHeight(g2d, sb.toString(),
                                                g2d.getFont())));
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
            g2d.drawString(
                    sb.toString(),
                    pointX,
                    pointY
                            - ((linecount - linesprinted) * ImageHelper
                                    .getStringHeight(g2d, sb.toString(),
                                            g2d.getFont())));
            pointY += ImageHelper.getStringHeight(g2d, sb.toString(),
                    g2d.getFont());
        }
    }

    public static void printC3iName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Improved C   CPU" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Improved C",
                    c3Font);
        } else {
            g2d.drawString("Improved C   CPU" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "Improved C", c3Font);
        }
        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printNavalC3Name(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Naval C   CPU" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Naval C", c3Font);
        } else {
            g2d.drawString("Naval C   CPU" + (mixed ? " (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "Naval C", c3Font);
        }
        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3EmName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {

        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Emergency Master" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Emergency Master" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);

    }

    public static void printC3sName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {

        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Slave" + (mixed ? " (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Slave" + (mixed ? " (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        // stringWidth = ImageHelper.getStringWidth(g2d, "C", font);

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);

    }

    public static void printBC3iName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O Improved BC" + (mixed ? "   (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O Improved BC",
                    c3Font);
        } else {
            g2d.drawString("Improved BC" + (mixed ? "   (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper
                    .getStringWidth(g2d, "Improved BC", c3Font);
        }
        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printBC3Name(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O BC" + (mixed ? "   (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O BC", c3Font);
        } else {
            g2d.drawString("BC" + (mixed ? "   (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "BC", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);

    }

    public static void printC3sbName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Boosted Slave" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Boosted Slave" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3mName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Master" + (mixed ? " (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Master" + (mixed ? " (IS)" : ""), lineStart,
                    linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3mbName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Boosted Master" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Boosted Master" + (mixed ? " (IS)" : ""),
                    lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3RemoteSensorName(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, boolean mixed) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;
        if (isArmored) {
            g2d.drawString("O C   Remote Sensor Launcher"
                    + (mixed ? " (IS)" : ""), lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "O C", c3Font);
        } else {
            g2d.drawString("C   Remote Sensor Launcher"
                    + (mixed ? " (IS)" : ""), lineStart, linePoint);
            stringWidth = ImageHelper.getStringWidth(g2d, "C", c3Font);
        }

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printC3RemoteSensorAmmoName(Graphics2D g2d,
            float lineStart, float linePoint, Font font) {
        Font c3Font = font.deriveFont(font.getStyle(), font.getSize2D());
        HashMap<TextAttribute, Integer> attrMap = new HashMap<TextAttribute, Integer>();
        attrMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
        int stringWidth;

        g2d.drawString("Ammo (C  Remote Sensor) 4", lineStart, linePoint);
        stringWidth = ImageHelper.getStringWidth(g2d, "Ammo (C", c3Font);

        c3Font = font.deriveFont(attrMap);
        g2d.setFont(c3Font);
        g2d.drawString("3", lineStart + stringWidth, linePoint);
        g2d.setFont(font);
    }

    public static void printMashCore(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, Entity entity) {
        int theaters = entity.countWorkingMisc(MiscType.F_MASH_EXTRA) + 1;
        String theaterString = theaters > 1 ? " theaters)" : " theater)";
        String printString;
        if (isArmored) {
            printString = "O MASH (" + theaters + theaterString;
        } else {
            printString = "MASH (" + theaters + theaterString;
        }
        g2d.setFont(UnitUtil.getNewFont(g2d, printString, false, 85,
                font.getSize2D()));
        g2d.drawString(printString, lineStart, linePoint);
        g2d.setFont(UnitUtil.deriveFont(7));
    }

    public static void printDroneControl(Graphics2D g2d, float lineStart,
            float linePoint, Font font, boolean isArmored, Entity entity) {
        int drones = entity.countWorkingMisc(MiscType.F_DRONE_EXTRA);
        String droneString = drones > 1 ? " drones)" : " drone)";
        String printString;
        if (isArmored) {
            printString = "O Drone Carrier Control System (" + drones
                    + droneString;
        } else {
            printString = "Drone Carrier Control System (" + drones
                    + droneString;
        }
        g2d.setFont(UnitUtil.getNewFont(g2d, printString, false, 85,
                font.getSize2D()));
        g2d.drawString(printString, lineStart, linePoint);
        g2d.setFont(UnitUtil.deriveFont(7));
    }

    public static void printCenterString(Graphics2D g2d, String info,
            Font font, float printWidth, float printHeight) {
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
            String path = new File(recordSheetPath).getAbsolutePath()
                    + File.separatorChar;
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
        GlyphVector gv = font
                .createGlyphVector(new FontRenderContext(g2d.getTransform(),
                        true, true), "\u02C4");
        size = gv.getOutline().getBounds2D().getHeight();
        gv = font.createGlyphVector(new FontRenderContext(g2d.getTransform(),
                true, true), "\u02C5");
        size += gv.getOutline().getBounds2D().getHeight();
        size += .5f;
        return (float) size;
    }

    public static void drawDiamond(Graphics2D g2d, int xPos, int yPos) {
        String path = new File(recordSheetPath).getAbsolutePath()
                + File.separatorChar;
        Image img = new ImageIcon(path + "shielddiamond.png").getImage();
        g2d.drawImage(img, xPos, yPos, 5, 5, null);

    }

    public static void printRotatedText(Graphics2D g2d, String text,
            double angle, int xLoc, int yLoc) {
        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(xLoc, yLoc);
        AffineTransform newTransform = (AffineTransform) oldTransform.clone();
        newTransform.concatenate(AffineTransform.getTranslateInstance(xLoc,
                yLoc));
        newTransform.concatenate(AffineTransform.getRotateInstance(Math
                .toRadians(angle)));
        g2d.setTransform(newTransform);
        g2d.drawString(text, 0, 0);
        g2d.setTransform(oldTransform);
    }

    public static Vector<float[]> getPointsAlongLine(float[] start,
            float[] end, int points) {

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
            result.add(new float[] { x, y });
            return result;
        } else if (points == 0) {
            return result;
        }

        for (int i = 0; i < points; i++) {
            float x = start[0] + (xStep * i);
            float y = start[1] + (yStep * i);

            result.add(new float[] { x, y });
        }
        return result;
    }

    public static void printLAMFuel(LandAirMech lam, Graphics2D g2d,
            int leftMargin) {
        int pointY = 330;
        int pointX = leftMargin;
        String fuel = "Fuel: ";

        g2d.setFont(UnitUtil.getNewFont(g2d, fuel, false, 200, 7.0f));
        g2d.drawString(fuel, pointX, pointY);
        pointX += ImageHelper.getStringWidth(g2d, fuel, g2d.getFont());

        String fuelAmount = String.format("%1$s Points", lam.getFuel());
        g2d.setFont(UnitUtil.getNewFont(g2d, fuelAmount, false, 200, 7.0f));
        g2d.drawString(fuelAmount, pointX, pointY);
    }

    public static SVGDiagram loadSVGImage(File file) {
        SVGUniverse universe = new SVGUniverse(); // Can be static final ...
        try (InputStream fileStream = new FileInputStream(file)) {
            URI svgFile = universe.loadSVG(fileStream, file.toString());
            return universe.getDiagram(svgFile);
        } catch (IOException e) {
            return null;
        }
    }

}