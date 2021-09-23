/*
 * MegaMekLab - Copyright (C) 2008-2020 The MegaMek Team
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

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import megamek.common.Entity;

public class ImageHelper {
    public static String imageMech = "mech";
    public static String imageAero = "aero";
    public static String imageBattleArmor = "BattleArmor";
    public static String imageInfantry = "infantry";
    public static String imageVehicle = "vehicle";
    public static String imageNaval = "naval";
    public static String imageLargeSupportVehicle = "largesupportvehicle";
    public static String imageProto = "protomech";
    public static String imageDropship = "dropship";
    public static String imageJumpship = "jumpship";
    public static String imageWarship = "warship";
    public static String imageSpaceStation = "spacestation";

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
        String path = new File(CConfig.getFluffImagesPath()).getAbsolutePath();
        File f;
        
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

        Image fluff;

        String path = new File(CConfig.getFluffImagesPath()).getAbsolutePath()
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
        Image fluff;

        String path = new File(CConfig.getFluffImagesPath()).getAbsolutePath()
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
}