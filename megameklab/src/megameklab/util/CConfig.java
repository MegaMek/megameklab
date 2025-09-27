/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JFrame;

import megamek.common.Configuration;
import megamek.common.enums.WeaponSortOrder;
import megamek.logging.MMLogger;
import megameklab.printing.MekChassisArrangement;
import megameklab.printing.PrintRecordSheet;
import megameklab.printing.RecordSheetOptions;
import megameklab.ui.MMLStartUp;
import megameklab.ui.MegaMekLabTabbedUI;
import megameklab.ui.MenuBarOwner;
import megameklab.ui.PopupMessages;
import megameklab.ui.battleArmor.BAMainUI;
import megameklab.ui.combatVehicle.CVMainUI;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.handheldWeapon.HHWMainUI;
import megameklab.ui.infantry.CIMainUI;
import megameklab.ui.largeAero.DSMainUI;
import megameklab.ui.largeAero.WSMainUI;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.protoMek.PMMainUI;
import megameklab.ui.supportVehicle.SVMainUI;

/**
 * Class for Client's configuration.
 */
public final class CConfig {
    private static final MMLogger logger = MMLogger.create(CConfig.class);

    public static final String CONFIG_DIR = "./mmconf";
    public static final String CONFIG_FILE = "./mmconf/megameklab.properties";
    public static final String CONFIG_BACKUP_FILE = "./mmconf/megameklab.properties.bak";

    public static final String MISC_STARTUP = "StartupGui";
    public static final String MISC_SUMMARY_FORMAT_TRO = "useTROFormat";
    public static final String MISC_SKIP_SAFETY_PROMPTS = "skipSafetyPrompts";
    public static final String MISC_APPLICATION_EXIT_PROMPT = "applicationExitPrompt";
    public static final String MISC_MUL_OPEN_BEHAVIOUR = "mulDndBehaviour";
    public static final String MISC_INCLUDE_LICENSE = "includeLicense";

    public static final String GUI_PLAF = "lookAndFeel";
    public static final String GUI_COLOR_WEAPONS = "Weapons";
    public static final String GUI_COLOR_AMMO = "Ammo";
    public static final String GUI_COLOR_EQUIPMENT = "Equipment";
    public static final String GUI_COLOR_SYSTEMS = "Systems";
    public static final String GUI_COLOR_EMPTY = "Empty";
    public static final String GUI_COLOR_NON_HITTABLE = "Non Hittable";
    public static final String GUI_FOREGROUND = "-Foreground";
    public static final String GUI_BACKGROUND = "-Background";

    public static final String GUI_FULLSCREEN = "FullScreen";
    public static final String GUI_BM_MAIN_UI_WINDOW = "BMWindow";
    public static final String GUI_CV_MAIN_UI_WINDOW = "CVWindow";
    public static final String GUI_AS_MAIN_UI_WINDOW = "ASWindow";
    public static final String GUI_SV_MAIN_UI_WINDOW = "SVWindow";
    public static final String GUI_PM_MAIN_UI_WINDOW = "PMWindow";
    public static final String GUI_BA_MAIN_UI_WINDOW = "BAWindow";
    public static final String GUI_CI_MAIN_UI_WINDOW = "CIWindow";
    public static final String GUI_DS_MAIN_UI_WINDOW = "DSWindow";
    public static final String GUI_WS_MAIN_UI_WINDOW = "WSWindow";
    public static final String GUI_HHW_MAIN_UI_WINDOW = "HHWWindow";
    public static final String GUI_TABBED_WINDOW = "TabbedWindow";

    public static final int RECENT_FILE_COUNT = 10;
    public static final String FILE_RECENT_PREFIX = "Save_File_";
    public static final String FILE_LAST_DIRECTORY = "Last_directory";
    public static final String FILE_CHOOSER_WINDOW = "File_Chooser_Window";
    public static final String FORCE_BUILD_WINDOW = "Force_Build_Window";

    public static final String TECH_PROGRESSION = "techProgression";
    public static final String TECH_USE_YEAR = "techUseYear";
    public static final String TECH_YEAR = "techYear";
    public static final String TECH_SHOW_FACTION = "techShowFaction";
    public static final String TECH_EXTINCT = "techShowExtinct";
    public static final String TECH_UNOFFICIAL_NO_YEAR = "techUnofficialNoYear";

    public static final String RS_PAPER_SIZE = "rs_paper_size";
    public static final String RS_COLOR = "rs_color";
    public static final String RS_HEAT_SCALE_MARKER = "rs_heat_scale_marker";
    public static final String RS_ROW_SHADING = "rs_row_shading";
    public static final String RS_FONT = "rs_font";
    public static final String RS_PROGRESS_BAR = "rs_progress_bar";
    public static final String RS_SHOW_QUIRKS = "rs_show_quirks";
    public static final String RS_SHOW_C3BV = "rs_show_c3bv";
    public static final String RS_SHOW_PILOT_DATA = "rs_show_pilot_data";
    public static final String RS_SHOW_ERA = "rs_show_era";
    public static final String RS_SHOW_ROLE = "rs_show_role";
    public static final String RS_HEAT_PROFILE = "rs_heat_profile";
    public static final String RS_TAC_OPS_HEAT = "rs_tac_ops_heat";
    public static final String RS_REFERENCE = "rs_reference";
    public static final String RS_CONDENSED_REFERENCE = "rs_condensed_reference";
    public static final String RS_SCALE_FACTOR = "rs_scale_factor";
    public static final String RS_SCALE_UNITS = "rs_scale_units";
    public static final String RS_MEK_NAMES = "rs_mek_names";
    public static final String RS_ARMOR_GROUPING = "rs_armor_grouping";
    public static final String RS_FRAMELESS = "rs_frameless";
    public static final String RS_BOLD_TYPE = "rs_bold_type";
    public static final String RS_DAMAGE = "rs_damage";
    public static final String RS_DAMAGE_COLOR = "rs_damage_color";
    public static final String RS_WEAPONS_ORDER = "rs_weapons_order";
    public static final String RS_MERGE_IDENTICAL_EQUIPMENT = "rs_merge_identical_equipment";
    public static final String RS_HIT_MOD = "rs_hit_mod";
    public static final String RS_INTRINSIC_PHYSICALS = "rs_intrinsic_physicals";
    public static final String RS_EXPLICIT_ZERO_MOD = "rs_explicit_zero_mod";
    public static final String RS_EXTRA_PHYSICALS = "rs_extra_physicals";
    public static final String RS_FANCY_PIPS = "rs_fancy_pips";

    public static final String NAG_EQUIPMENT_CTRL_CLICK = "nag_equipment_ctrlclick";
    public static final String NAG_IMPORT_SETTINGS = "nag_import_settings";

    public static final String MEK_AUTOFILL = "mekAutofill";
    public static final String MEK_AUTO_SORT = "mekAutoSort";
    public static final String MEK_AUTO_COMPACT = "mekAutoCompact";

    public static final String PQ_SINGLE_PRINT = "pqSinglePrint";
    public static final String PQ_ADJUSTED_BV = "pqAdjustedBV";
    public static final String PQ_DAMAGE = "pqDamage";
    public static final String PQ_SHOW_PILOT_DATA = "pqShowPilotData";

    private static final Properties config = getDefaults();

    /**
     * Private method that loads hardcoded defaults. These are loaded before the players config values, adding any new
     * configs in their default position and ensuring that no config value is even missing.
     */
    private static Properties getDefaults() {
        Properties defaults = new Properties();
        defaults.setProperty(GUI_PLAF, "com.formdev.flatlaf.FlatDarculaLaf");
        defaults.setProperty(GUI_FULLSCREEN, Boolean.toString(false));
        defaults.setProperty(MISC_SUMMARY_FORMAT_TRO, Boolean.toString(true));
        defaults.setProperty(MISC_SKIP_SAFETY_PROMPTS, Boolean.toString(false));
        defaults.setProperty(MISC_APPLICATION_EXIT_PROMPT, Boolean.toString(true));
        defaults.setProperty(MISC_INCLUDE_LICENSE, Boolean.toString(false));
        defaults.setProperty(RS_PROGRESS_BAR, Boolean.toString(true));
        defaults.setProperty(RS_COLOR, RecordSheetOptions.ColorMode.LOGO_ONLY.name());
        defaults.setProperty(RS_HEAT_SCALE_MARKER, RecordSheetOptions.HeatScaleMarker.ASTERISK.name());
        defaults.setProperty(RS_SHOW_QUIRKS, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ERA, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_ROLE, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_PILOT_DATA, Boolean.toString(true));
        defaults.setProperty(RS_SHOW_C3BV, Boolean.toString(false));
        defaults.setProperty(RS_SCALE_FACTOR, "1");
        defaults.setProperty(RS_SCALE_UNITS, RSScale.HEXES.toString());
        defaults.setProperty(RS_MEK_NAMES, MekChassisArrangement.IS_CLAN.name());
        defaults.setProperty(RS_BOLD_TYPE, Boolean.toString(false));
        defaults.setProperty(RS_DAMAGE, Boolean.toString(false));
        defaults.setProperty(RS_DAMAGE_COLOR, PrintRecordSheet.FILL_RED);
        defaults.setProperty(NAG_EQUIPMENT_CTRL_CLICK, Boolean.toString(true));
        defaults.setProperty(MEK_AUTOFILL, Boolean.toString(true));
        defaults.setProperty(MEK_AUTO_SORT, Boolean.toString(true));
        defaults.setProperty(MEK_AUTO_COMPACT, Boolean.toString(true));
        defaults.setProperty(FILE_LAST_DIRECTORY, Configuration.unitsDir().toString());
        defaults.setProperty(FILE_CHOOSER_WINDOW, "");
        defaults.setProperty(MISC_STARTUP, MMLStartUp.SPLASH_SCREEN.name());
        defaults.setProperty(NAG_IMPORT_SETTINGS, Boolean.toString(true));
        defaults.setProperty(PQ_SHOW_PILOT_DATA, Boolean.toString(true));
        defaults.setProperty(RS_WEAPONS_ORDER, WeaponSortOrder.DEFAULT.name());
        defaults.setProperty(RS_MERGE_IDENTICAL_EQUIPMENT, Boolean.toString(true));
        defaults.setProperty(RS_HIT_MOD, RecordSheetOptions.HitModStyle.NONE.name());
        defaults.setProperty(RS_INTRINSIC_PHYSICALS, RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE.name());
        defaults.setProperty(RS_EXPLICIT_ZERO_MOD, RecordSheetOptions.ExplicitZeroModifierStyle.DASH.name());
        defaults.setProperty(RS_EXTRA_PHYSICALS, Boolean.toString(false));
        defaults.setProperty(RS_FANCY_PIPS, Boolean.toString(false));
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
     * Tries to import settings from the given properties file. When successful, also applies some of the settings and
     * shows a popup message.
     *
     * @param menuBarOwner The MenuBar owner frame calling this
     * @param settingsFile The file (should always be megameklab.properties in another MML install)
     */
    public static void importSettings(MenuBarOwner menuBarOwner, File settingsFile) {
        try (FileInputStream fis = new FileInputStream(settingsFile)) {
            config.load(fis);
        } catch (Exception ex) {
            PopupMessages.showFileReadError(menuBarOwner.getFrame(), settingsFile.toString(), ex.getMessage());
            logger.error("", ex);
            return;
        }
        applyImportedSettings(menuBarOwner);
        PopupMessages.showSettingsImported(menuBarOwner.getFrame());
    }

    /**
     * Loads the Config file.
     */
    public synchronized static void loadConfigFile() {
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
                logger.error("", ex);
            }
        } catch (Exception ex) {
            logger.error("", ex);
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
                    logger.error("Cannot launch MML: Failed to create Configuration Directory");
                    System.exit(0);
                }
            }

            try {
                if (!configurationFile.createNewFile()) {
                    logger.error("Cannot launch MML: Failed to create Configuration File");
                    System.exit(0);
                }
            } catch (Exception ex) {
                logger.error("Cannot launch MML: Exception while creating Configuration File", ex);
                System.exit(0);
            }
        }
    }

    /**
     * Get a config value, with a default value to be used if the value is not found.
     *
     * @param param      The key
     * @param defaultVal The value to return if the entry is not found
     *
     * @return The value associated with the key
     */
    public static String getParam(String param, String defaultVal) {
        if (param.endsWith(":")) {
            param = param.substring(0, param.lastIndexOf(":"));
        }

        String tParam = config.getProperty(param);
        if (tParam == null) {
            tParam = defaultVal;
        }

        return tParam;
    }

    /**
     * Get a config value.
     *
     * @param param The key
     *
     * @return The value associated with the key. If not found, an empty String is returned
     */
    public static String getParam(String param) {
        return getParam(param, "");
    }

    /**
     * Set a config value.
     *
     * @param param the name of the parameter
     * @param value the value to set the parameter to
     */
    public static void setParam(String param, String value) {
        config.setProperty(param, value);
    }

    /**
     * Return the int value of a given config property. Return the provided default value if the property is a
     * non-number.
     *
     * @param param      The parameter name
     * @param defaultVal The value to return if the property does not exist or is not a valid string representation of
     *                   the integer
     *
     * @return The integer value of the property
     */
    public static int getIntParam(String param, int defaultVal) {
        try {
            return Integer.parseInt(CConfig.getParam(param));
        } catch (Exception ex) {
            return defaultVal;
        }
    }

    /**
     * Return the int value of a given config property. Return a 0 if the property is a non-number.
     *
     * @param param The parameter name
     *
     * @return The integer value of the property
     */
    public static int getIntParam(String param) {
        return getIntParam(param, 0);
    }

    /**
     * Return the enum value of a given config property.
     *
     */
    public static <E extends Enum<E>> E getEnumParam(String key, Class<E> enumClass, E defaultVal) {
        String name = getParam(key, defaultVal.name());
        try {
            return Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException ex) {
            return defaultVal;
        }
    }

    /**
     * Set a config value to the name of the enum.
     *
     * @param key   the name of the parameter
     * @param value the value to set the parameter to
     */
    public static <E extends Enum<E>> void setEnumParam(String key, E value) {
        setParam(key, value.name());
    }

    /**
     * @param param the parameter's name
     *
     * @return the boolean value of a given config property. Return a false if the property does not exist.
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
    public synchronized static void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_BACKUP_FILE);
              PrintStream ps = new PrintStream(fos)) {
            config.store(ps, "Client Config Backup");
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            logger.error("", ex);
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
              PrintStream ps = new PrintStream(fos)) {
            config.store(ps, "Client Config");
        } catch (FileNotFoundException ignored) {

        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    public static Color getForegroundColor(String fieldName) {
        try {
            return Color.getColor("", Integer.parseInt(CConfig.getParam(fieldName + CConfig.GUI_FOREGROUND)));
        } catch (Exception e) {
            return Color.BLACK;
        }
    }

    public static Color getBackgroundColor(String fieldName) {
        try {
            return Color.getColor("", Integer.parseInt(CConfig.getParam(fieldName + CConfig.GUI_BACKGROUND)));
        } catch (Exception e) {
            return Color.WHITE;
        }
    }

    public static void setMostRecentFile(final String newFile) {
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

    public static List<String> getRecentFiles() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= RECENT_FILE_COUNT; i++) {
            if (!getRecentFile(i).isBlank()) {
                result.add(getRecentFile(i));
            }
        }
        return result;
    }

    private static void setRecentFiles(List<String> files) {
        for (int i = 0; i < RECENT_FILE_COUNT; i++) {
            if (i < files.size()) {
                setParam(FILE_RECENT_PREFIX + (i + 1), files.get(i));
            } else {
                setParam(FILE_RECENT_PREFIX + (i + 1), "");
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
     *
     * @return A String representation of the scaled value
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
        return getWindowSize(FILE_CHOOSER_WINDOW);
    }

    public static Optional<Point> getFileChooserPosition() {
        return getWindowPosition(FILE_CHOOSER_WINDOW);
    }

    public static void writeFileChooserSettings(JDialog dialog) {
        writeWindowSettings(FILE_CHOOSER_WINDOW, dialog);
    }

    public static Optional<Point> getForceBuildPosition() {
        return getWindowPosition(FORCE_BUILD_WINDOW);
    }

    public static void writeForceBuildPosition(JFrame frame) {
        writeWindowSettings(FORCE_BUILD_WINDOW, frame);
    }

    public static Optional<Dimension> getMainUiWindowSize(MenuBarOwner mainUi) {
        return getWindowSize(settingForMainUi(mainUi));
    }

    public static Optional<Point> getMainUiWindowPosition(MenuBarOwner mainUi) {
        return getWindowPosition(settingForMainUi(mainUi));
    }

    public static Optional<Dimension> getNamedWindowSize(String name) {
        return getWindowSize(name);
    }

    public static void writeMainUiWindowSettings(MenuBarOwner mainUi) {
        writeWindowSettings(settingForMainUi(mainUi), (Component) mainUi);
    }

    public static void writeNamedWindowSize(String name, Window component) {
        writeWindowSettings(name, component);
    }

    public static String getRecentFile(int recentFileNumber) {
        return CConfig.getParam(CConfig.FILE_RECENT_PREFIX + recentFileNumber);
    }

    public static MMLStartUp getStartUpType() {
        return MMLStartUp.parse(CConfig.getParam(CConfig.MISC_STARTUP));
    }

    public static MekChassisArrangement getMekNameArrangement() {
        return MekChassisArrangement.parse(CConfig.getParam(CConfig.RS_MEK_NAMES));
    }

    public static boolean includeLicense() {
        return CConfig.getBooleanParam(CConfig.MISC_INCLUDE_LICENSE);
    }

    public static void resetWindowPositions() {
        setParam(GUI_FULLSCREEN, Boolean.toString(false));
        setParam(FILE_CHOOSER_WINDOW, "");
        setParam(GUI_BM_MAIN_UI_WINDOW, "");
        setParam(GUI_CV_MAIN_UI_WINDOW, "");
        setParam(GUI_AS_MAIN_UI_WINDOW, "");
        setParam(GUI_SV_MAIN_UI_WINDOW, "");
        setParam(GUI_PM_MAIN_UI_WINDOW, "");
        setParam(GUI_BA_MAIN_UI_WINDOW, "");
        setParam(GUI_CI_MAIN_UI_WINDOW, "");
        setParam(GUI_DS_MAIN_UI_WINDOW, "");
        setParam(GUI_WS_MAIN_UI_WINDOW, "");
        setParam(GUI_HHW_MAIN_UI_WINDOW, "");
        setParam(GUI_TABBED_WINDOW, "");
        saveConfig();
    }

    // Internals ####################

    private static Optional<Dimension> getWindowSize(String cConfigSetting) {
        try {
            String param = getParam(cConfigSetting);
            if (param.isBlank()) {
                return Optional.empty();
            }
            String[] values = param.split(";");
            int sizeX = Integer.parseInt(values[2]);
            int sizeY = Integer.parseInt(values[3]);
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int clampedWidth = Math.max(50, Math.min(sizeX, screen.width)); // 50 minimum width
            int clampedHeight = Math.max(50, Math.min(sizeY, screen.height)); // 50 minimum height
            return Optional.of(new Dimension(clampedWidth, clampedHeight));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<Point> getWindowPosition(String cConfigSetting) {
        try {
            String param = getParam(cConfigSetting);
            if (param.isBlank()) {
                return Optional.empty();
            }
            String[] values = param.split(";");
            int posX = Integer.parseInt(values[0]);
            int posY = Integer.parseInt(values[1]);
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int clampedX = Math.max(0, Math.min(posX, screen.width - 100)); // -100 to avoid the right edge
            int clampedY = Math.max(0, Math.min(posY, screen.height - 100)); // -100 to avoid the taskbar
            return Optional.of(new Point(clampedX, clampedY));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static void writeWindowSettings(String cConfigSetting, Component component) {
        Dimension size = component.getSize();
        Point pos = component.getLocation();
        setParam(cConfigSetting, pos.x + ";" + pos.y + ";" + size.width + ";" + size.height);
        saveConfig();
    }

    private static String settingForMainUi(MenuBarOwner ui) {
        if (ui instanceof BMMainUI) {
            return GUI_BM_MAIN_UI_WINDOW;
        } else if (ui instanceof CVMainUI) {
            return GUI_CV_MAIN_UI_WINDOW;
        } else if (ui instanceof DSMainUI) {
            return GUI_DS_MAIN_UI_WINDOW;
        } else if (ui instanceof ASMainUI) {
            return GUI_AS_MAIN_UI_WINDOW;
        } else if (ui instanceof PMMainUI) {
            return GUI_PM_MAIN_UI_WINDOW;
        } else if (ui instanceof BAMainUI) {
            return GUI_BA_MAIN_UI_WINDOW;
        } else if (ui instanceof CIMainUI) {
            return GUI_CI_MAIN_UI_WINDOW;
        } else if (ui instanceof SVMainUI) {
            return GUI_SV_MAIN_UI_WINDOW;
        } else if (ui instanceof WSMainUI) {
            return GUI_WS_MAIN_UI_WINDOW;
        } else if (ui instanceof HHWMainUI) {
            return GUI_WS_MAIN_UI_WINDOW;
        } else if (ui instanceof MegaMekLabTabbedUI) {
            return GUI_TABBED_WINDOW;
        }
        throw new IllegalArgumentException("Unknown MenuBarOwner: " + ui.getClass().getName());
    }

    private static void applyImportedSettings(MenuBarOwner menuBarOwner) {
        menuBarOwner.changeTheme(getParam(GUI_PLAF));
        menuBarOwner.refreshAll();
    }

    private CConfig() {
    }
}
