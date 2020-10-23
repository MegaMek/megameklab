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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Class for Client's configuration.
 */
public class CConfig {

    /**
     * Scale to use when printing record sheets
     */
    public enum RSScale {
        HEXES("hexes", ""),
        INCHES("inches", "\""),
        CENTIMETERS("centimeters", " cm");

        public final String fullName;
        public final String abbreviation;

        RSScale(String fullName, String abbreviation) {
            this.fullName = fullName;
            this.abbreviation = abbreviation;
        }

        /**
         * Used for display name when space is limited. If the full name is longer than six characters
         * the abbreviation is used.
         * @return A short name for display
         */
        public String shortName() {
            if (fullName.length() > 6) {
                return abbreviation.trim();
            } else {
                return fullName;
            }
        }
    }

    // VARIABLES
    public static final String CONFIG_DIR = "./mmconf";
    public static final String CONFIG_FILE = "./mmconf/megameklab.properties";
    public static final String CONFIG_BACKUP_FILE = "./mmconf/megameklab.properties.bak";

    public static final String CONFIG_WEAPONS = "Weapons";
    public static final String CONFIG_AMMO = "Ammo";
    public static final String CONFIG_EQUIPMENT = "Equipment";
    public static final String CONFIG_SYSTEMS = "Systems";
    public static final String CONFIG_EMPTY = "Empty";

    public static final String CONFIG_SAVE_FILE_1 = "Save_File_One";
    public static final String CONFIG_SAVE_FILE_2 = "Save_File_Two";
    public static final String CONFIG_SAVE_FILE_3 = "Save_File_Three";
    public static final String CONFIG_SAVE_FILE_4 = "Save_File_Four";

    public static final String CONFIG_FOREGROUND = "-Foreground";
    public static final String CONFIG_BACKGROUND = "-Background";

    public static final String CONFIG_WEAPONS_FOREGROUND = "Weapons-Foreground";
    public static final String CONFIG_WEAPONS_BACKGROUND = "Weapons-Background";
    public static final String CONFIG_AMMO_FOREGROUND = "Ammo-Foreground";
    public static final String CONFIG_AMMO_BACKGROUND = "Ammo-Background";
    public static final String CONFIG_EQUIPMENT_FOREGROUND = "Equipment-Foreground";
    public static final String CONFIG_EQUIPMENT_BACKGROUND = "Equipment-Background";
    public static final String CONFIG_SYSTEMS_FOREGROUND = "Systems-Foreground";
    public static final String CONFIG_SYSTEMS_BACKGROUND = "Systems-Background";
    public static final String CONFIG_EMPTY_FOREGROUND = "Empty-Foreground";
    public static final String CONFIG_EMPTY_BACKGROUND = "Empty-Background";
    
    public static final String TECH_PROGRESSION = "techProgression";
    public static final String TECH_USE_YEAR = "techUseYear";
    public static final String TECH_YEAR = "techYear";
    public static final String TECH_SHOW_FACTION = "techShowFaction";
    public static final String TECH_EXTINCT = "techShowExtinct";
    public static final String TECH_UNOFFICAL_NO_YEAR = "techUnofficialNoYear";
    
    public static final String SUMMARY_FORMAT_TRO = "useTROFormat";
    
    public static final String CONFIG_SAVE_LOC = "Save-Location-Default";
    public static final String CONFIG_PLAF = "lookAndFeel";

    public static final String RS_PAPER_SIZE = "rs_paper_size";
    public static final String RS_COLOR = "rs_color";
    public static final String RS_FONT = "rs_font";
    public static final String RS_PROGRESS_BAR = "rs_progress_bar";
    public static final String RS_SHOW_QUIRKS = "rs_show_quirks";
    public static final String RS_SHOW_PILOT_DATA = "rs_show_pilot_data";
    public static final String RS_SHOW_ERA = "rs_show_era";
    public static final String RS_SHOW_ROLE = "rs_show_role";
    public static final String RS_HEAT_PROFILE = "rs_heat_profile";
    public static final String RS_TAC_OPS_HEAT = "rs_tac_ops_heat";
    public static final String RS_REFERENCE = "rs_reference";
    public static final String RS_SCALE_FACTOR = "rs_scale_factor";
    public static final String RS_SCALE_UNITS = "rs_scale_units";

    private static Properties config;// config. player values.

    // CONSTRUCTOR
    public CConfig() {
        
        if(!new File(CONFIG_DIR).exists()) {
            new File(CONFIG_DIR).mkdir();
        }
        
        config = setDefaults();
        // check to see if a config is present. if not, make one.
        if (!(new File(CONFIG_FILE).exists()) && !(new File(CONFIG_BACKUP_FILE).exists())) {
            createConfig();
        }

        CConfig.loadConfigFile();
    }

    // METHODS
    /**
     * Private method that loads hardcoded defaults. These are loaded before the
     * players config values, adding any new configs in their default position
     * and ensuring that no config value is even missing.
     */
    private Properties setDefaults() {
        Properties defaults = new Properties();

        // Window Locations
        defaults.setProperty("WINDOWSTATE", "0");
        defaults.setProperty("WINDOWHEIGHT", "600");
        defaults.setProperty("WINDOWWIDTH", "800");
        defaults.setProperty("WINDOWLEFT", "0");
        defaults.setProperty("WINDOWTOP", "0");
        defaults.setProperty(CONFIG_SAVE_LOC,
                new File(System.getProperty("user.dir")
                        + "/data/mechfiles/").getAbsolutePath());
        defaults.setProperty(SUMMARY_FORMAT_TRO, Boolean.toString(true));
        defaults.setProperty(RS_PROGRESS_BAR, Boolean.toString(true));
        defaults.setProperty(RS_COLOR, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_QUIRKS, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ERA, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ROLE, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_PILOT_DATA, Boolean.toString(true));
        defaults.setProperty(RS_SCALE_FACTOR, "1");
        defaults.setProperty(RS_SCALE_UNITS, RSScale.HEXES.toString());

        return defaults;
    }

    /**
     * Loads the Config file.
     */
    public static void loadConfigFile() {
        try {
            File configfile = new File(CONFIG_FILE);
            FileInputStream fis = new FileInputStream(configfile);
            File backupfile = new File(CONFIG_BACKUP_FILE);
            if (backupfile.exists()) {
                FileInputStream backupStream = new FileInputStream(backupfile);
                if (fis.available() < backupStream.available()) {
                    try {
                        config.load(backupStream);
                        backupStream.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {
                    config.load(fis);
                }
            } else {
                config.load(fis);
            }
            fis.close();
        } catch (IOException ie) {
            try {
                File configfile = new File(CONFIG_BACKUP_FILE);
                FileInputStream fis = new FileInputStream(configfile);
                config.load(fis);
                fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Creates a new config file
    public void createConfig() {
        try {
            FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
            PrintStream ps = new PrintStream(fos);

            ps.close();
            fos.close();
        } catch (Exception ex) {
            System.exit(0);
        }
    }

    /**
     * Get a config value, with a default value to be used if the value is not found.
     * 
     * @param param      The key
     * @param defaultVal The value to return if the entry is not found
     * @return           The value associated with the key
     */
    public static String getParam(String param, String defaultVal) {
        if (param.endsWith(":")) {
            param = param.substring(0, param.lastIndexOf(":"));
        }
        String tparam = config.getProperty(param);
        if (tparam == null) {
            tparam = defaultVal;
        }
        return tparam;
    }
    
    /**
     * Get a config value.
     * 
     * @param param      The key
     * @return           The value associated with the key. If not found, an empty String is returned
     */
    public static String getParam(String param) {
        return getParam(param, "");
    }

    /**
     * Set a config value.
     */
    public static void setParam(String param, String value) {
        config.setProperty(param, value);
    }

    /**
     * Return the int value of a given config property. Return the provided default value if the
     * property is a non-number.
     *
     * @param param      The parameter name
     * @param defaultVal The value to return if the property does not exist or is not a valid string
     *                   representation of the integer
     * @return The integer value of the property
     */
    public static int getIntParam(String param, int defaultVal) {
        int toReturn;
        try {
            toReturn = Integer.parseInt(CConfig.getParam(param));
        } catch (Exception ex) {
            return defaultVal;
        }
        return toReturn;
    }

    /**
     * Return the int value of a given config property. Return a 0 if the
     * property is a non-number.
     *
     * @param param  The parameter name
     * @return       The integer value of the property
     */
    public static int getIntParam(String param) {
        return getIntParam(param, 0);
    }

    /**
     * Return the boolean value of a given config property. Return a false if the
     * property does not exist.
     */
    public static boolean getBooleanParam(String param) {
        boolean toReturn;
        try {
            toReturn = Boolean.parseBoolean(CConfig.getParam(param));
        } catch (Exception ex) {
            return false;
        }
        return toReturn;
    }

    /**
     * Write the config file out to ./data/mwconfig.txt.
     */
    public static void saveConfig() {

        try {

            FileOutputStream fos = new FileOutputStream(CONFIG_BACKUP_FILE);
            PrintStream ps = new PrintStream(fos);
            config.store(ps, "Client Config Backup");
            fos.close();
            ps.close();
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
            PrintStream ps = new PrintStream(fos);
            config.store(ps, "Client Config");
            fos.close();
            ps.close();
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Color getForegroundColor(String fieldName) {
        Color masterColor = Color.black;
        try {
            masterColor = Color.getColor("", Integer.parseInt(CConfig.getParam(fieldName + CConfig.CONFIG_FOREGROUND)));
        } catch (Exception ignored) {
        }
        return masterColor;
    }

    public static Color getBackgroundColor(String fieldName) {
        Color masterColor = Color.WHITE;

        try {
            masterColor = Color.getColor("", Integer.parseInt(CConfig.getParam(fieldName + CConfig.CONFIG_BACKGROUND)));
        } catch (Exception ignored) {

        }
        return masterColor;
    }

    public static void updateSaveFiles(String newFile) {

        if (newFile.trim().length() < 1) {
            return;
        }
        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4).equalsIgnoreCase(newFile)) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_4, "");
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3).equalsIgnoreCase(newFile)) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_3, "");
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2).equalsIgnoreCase(newFile)) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_2, "");
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1).equalsIgnoreCase(newFile)) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");
        }

        if (!CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4).equalsIgnoreCase(CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3))) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_4, CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3));
        }

        if (!CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3).equalsIgnoreCase(CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2))) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_3, CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2));
        }

        if (!CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2).equalsIgnoreCase(CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1))) {
            CConfig.setParam(CConfig.CONFIG_SAVE_FILE_2, CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1));
        }

        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, newFile);
        CConfig.saveConfig();
    }

    /**
     * @return The currently selected scale units for record sheet printing
     */
    public static RSScale scaleUnits() {
        return RSScale.valueOf(getParam(RS_SCALE_UNITS));
    }

    /**
     * Applies the scale factor to a value and optionally adds the unit abbreviation
     *
     * @param val       The base distance (standard hexes)
     * @param showUnits Whether to append the unit abbreviation
     * @return          A String representation of the scaled value
     */
    public static String formatScale(double val, boolean showUnits) {
        int retVal = (int) Math.round(val * getIntParam(RS_SCALE_FACTOR, 1));
        if (showUnits) {
            return retVal + RSScale.valueOf(getParam(RS_SCALE_UNITS)).abbreviation;
        } else {
            return Integer.toString(retVal);
        }
    }
}
