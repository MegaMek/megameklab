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
package megameklab.util;

import megamek.common.Configuration;
import megameklab.ui.MMLStartUp;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Class for Client's configuration.
 */
public final class CConfig {

    public static final String CONFIG_DIR = "./mmconf";
    public static final String CONFIG_FILE = "./mmconf/megameklab.properties";
    public static final String CONFIG_BACKUP_FILE = "./mmconf/megameklab.properties.bak";

    public static final String STARTUP = "StartupGui";

    public static final String COLOR_WEAPONS = "Weapons";
    public static final String COLOR_AMMO = "Ammo";
    public static final String COLOR_EQUIPMENT = "Equipment";
    public static final String COLOR_SYSTEMS = "Systems";
    public static final String COLOR_EMPTY = "Empty";
    public static final String COLOR_NONHITTABLE = "Nonhittable";

    public static final int RECENT_FILE_COUNT = 4;
    public static final String RECENT_FILE_PREFIX = "Save_File_";
    public static final String CONFIG_SAVE_FILE_1 = RECENT_FILE_PREFIX + "1";
    public static final String CONFIG_SAVE_FILE_2 = RECENT_FILE_PREFIX + "2";
    public static final String CONFIG_SAVE_FILE_3 = RECENT_FILE_PREFIX + "3";
    public static final String CONFIG_SAVE_FILE_4 = RECENT_FILE_PREFIX + "4";
    public static final String LAST_DIRECTORY = "Last_directory";

    public static final String FILE_CHOOSER_WINDOW = "File_Chooser_Window";

    public static final String CONFIG_FOREGROUND = "-Foreground";
    public static final String CONFIG_BACKGROUND = "-Background";

    public static final String TECH_PROGRESSION = "techProgression";
    public static final String TECH_USE_YEAR = "techUseYear";
    public static final String TECH_YEAR = "techYear";
    public static final String TECH_SHOW_FACTION = "techShowFaction";
    public static final String TECH_EXTINCT = "techShowExtinct";
    public static final String TECH_UNOFFICAL_NO_YEAR = "techUnofficialNoYear";

    public static final String MISC_SUMMARY_FORMAT_TRO = "useTROFormat";
    public static final String MISC_SKIP_SAFETY_PROMPTS = "skipSafetyPrompts";
    
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
    public static final String RS_CONDENSED_REFERENCE = "rs_condensed_reference";
    public static final String RS_SCALE_FACTOR = "rs_scale_factor";
    public static final String RS_SCALE_UNITS = "rs_scale_units";

    public static final String NAG_EQUIPMENT_CTRLCLICK = "nag_equipment_ctrlclick";

    public static final String MEK_AUTOFILL = "mekAutofill";
    public static final String MEK_AUTOSORT = "mekAutosort";
    public static final String MEK_AUTOCOMPACT = "mekAutocompact";

    /**
     * Player configuration values.
     */
    private static final Properties config = getDefaults();

    // METHODS
    /**
     * Private method that loads hardcoded defaults. These are loaded before the
     * players config values, adding any new configs in their default position
     * and ensuring that no config value is even missing.
     */
    private static Properties getDefaults() {
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
        defaults.setProperty(MISC_SUMMARY_FORMAT_TRO, Boolean.toString(true));
        defaults.setProperty(MISC_SKIP_SAFETY_PROMPTS, Boolean.toString(false));
        defaults.setProperty(RS_PROGRESS_BAR, Boolean.toString(true));
        defaults.setProperty(RS_COLOR, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_QUIRKS, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ERA, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ROLE, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_PILOT_DATA, Boolean.toString(true));
        defaults.setProperty(RS_SCALE_FACTOR, "1");
        defaults.setProperty(RS_SCALE_UNITS, RSScale.HEXES.toString());
        defaults.setProperty(NAG_EQUIPMENT_CTRLCLICK, Boolean.toString(true));
        defaults.setProperty(MEK_AUTOFILL, Boolean.toString(true));
        defaults.setProperty(MEK_AUTOSORT, Boolean.toString(true));
        defaults.setProperty(MEK_AUTOCOMPACT, Boolean.toString(true));
        defaults.setProperty(LAST_DIRECTORY, Configuration.unitsDir().toString());
        defaults.setProperty(FILE_CHOOSER_WINDOW, "");
        defaults.setProperty(STARTUP, MMLStartUp.SPLASH_SCREEN.name());
        return defaults;
    }

    /**
     * Loads the MegaMekLab configuration.
     */
    public static void load() {
        ensureConfigFileExists();
        loadConfigFile();
    }

    /**
     * Loads the Config file.
     */
    public static void loadConfigFile() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            File backupConfigurationFile = new File(CONFIG_BACKUP_FILE);
            if (backupConfigurationFile.exists()) {
                try (FileInputStream backupFIS = new FileInputStream(backupConfigurationFile)) {
                    config.load((fis.available() < backupFIS.available()) ? backupFIS : fis);
                }
            } else {
                config.load(fis);
            }
        } catch (IOException ie) {
            try (FileInputStream fis = new FileInputStream(CONFIG_BACKUP_FILE)) {
                config.load(fis);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
    }
    
    /**
     * Creates a new Config file, and directories, if it is missing.
     */
    public static void ensureConfigFileExists() {
        // Check to see if a config is present. if not, make one.
        final File configurationFile = new File(CONFIG_FILE);
        if (!configurationFile.exists() && !new File(CONFIG_BACKUP_FILE).exists()) {
            final File configurationDirectory = new File(CONFIG_DIR);
            if (!configurationDirectory.exists()) {
                if (!new File(CONFIG_DIR).mkdir()) {
                    LogManager.getLogger().error("Cannot launch MML: Failed to create Configuration Directory");
                    System.exit(0);
                }
            }

            try {
                if (!configurationFile.createNewFile()) {
                    LogManager.getLogger().error("Cannot launch MML: Failed to create Configuration File");
                    System.exit(0);
                }
            } catch (Exception ex) {
                LogManager.getLogger().error("Cannot launch MML: Exception while creating Configuration File", ex);
                System.exit(0);
            }
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
     * @param param the name of the parameter
     * @param value the value to set the parameter to
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
     * @param param the parameter's name
     * @return the boolean value of a given config property. Return a false if the
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
        try (FileOutputStream fos = new FileOutputStream(CONFIG_BACKUP_FILE);
             PrintStream ps = new PrintStream(fos)) {
            config.store(ps, "Client Config Backup");
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
             PrintStream ps = new PrintStream(fos)) {
            config.store(ps, "Client Config");
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
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

    public static void updateSaveFiles(final String newFile) {
        if ((newFile == null) || newFile.isBlank()) {
            return;
        }

        List<String> recentFiles = getRecentFiles();
        List<String> recentFilesWithoutDuplicates = recentFiles.stream().distinct().collect(Collectors.toList());
        recentFilesWithoutDuplicates.removeIf(f -> f.equalsIgnoreCase(newFile));
        recentFilesWithoutDuplicates.add(0, newFile);
        setRecentFiles(recentFilesWithoutDuplicates);
        CConfig.saveConfig();
    }

    private static List<String> getRecentFiles() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= RECENT_FILE_COUNT; i++) {
            result.add(getRecentFile(i));
        }
        return result;
    }

    private static void setRecentFiles(List<String> files) {
        for (int i = 1; i <= RECENT_FILE_COUNT; i++) {
            if (i < files.size()) {
                setParam(RECENT_FILE_PREFIX + i, files.get(i));
            } else {
                setParam(RECENT_FILE_PREFIX + i, "");
            }
        }
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

    public static Optional<Dimension> getFileChooserSize() {
        try {
            String[] fileChooserSettings = getParam(FILE_CHOOSER_WINDOW).split(";");
            int sizeX = Integer.parseInt(fileChooserSettings[2]);
            int sizeY = Integer.parseInt(fileChooserSettings[3]);
            return Optional.of(new Dimension(sizeX, sizeY));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Point> getFileChooserPosition() {
        try {
            String[] fileChooserSettings = getParam(FILE_CHOOSER_WINDOW).split(";");
            int posX = Integer.parseInt(fileChooserSettings[0]);
            int posY = Integer.parseInt(fileChooserSettings[1]);
            return Optional.of(new Point(posX, posY));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void writeFileChooserSettings(JDialog dialog) {
        Dimension size = dialog.getSize();
        Point pos = dialog.getLocation();
        setParam(FILE_CHOOSER_WINDOW, pos.x + ";" + pos.y + ";" + size.width + ";" + size.height);
    }

    public static String getRecentFile(int recentFileNumber) {
        String fileName = CConfig.getParam(CConfig.RECENT_FILE_PREFIX + recentFileNumber);
        return (fileName == null) ? "" : fileName;
    }

    public static MMLStartUp getStartUpType() {
        return MMLStartUp.parse(CConfig.getParam(CConfig.STARTUP));
    }

    private CConfig() { }
}
