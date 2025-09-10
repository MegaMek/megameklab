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
package megameklab;

import java.awt.Window;
import java.io.File;
import java.io.ObjectInputFilter;
import java.lang.management.ManagementFactory;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import io.sentry.Sentry;
import megamek.MMLoggingConstants;
import megamek.MegaMek;
import megamek.SuiteConstants;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.preferences.SuitePreferences;
import megamek.client.ui.util.UIUtil;
import megamek.common.equipment.EquipmentType;
import megamek.common.loaders.MekFileParser;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.net.marshalling.SanityInputFilter;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabTabbedUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.StartupGUI;
import megameklab.ui.dialog.UiLoader;
import megameklab.util.CConfig;
import megameklab.util.MULManager;
import megameklab.util.SingleInstanceService;
import megameklab.util.UnitUtil;

public class MegaMekLab {
    private static final SuitePreferences mmlPreferences = new SuitePreferences();
    private static final MMLOptions mmlOptions = new MMLOptions();
    private static final SanityInputFilter sanityInputFilter = new SanityInputFilter();
    private static final MMLogger LOGGER = MMLogger.create(MegaMekLab.class);
    private static SingleInstanceService singleInstanceService;
    private static final String APPLICATION_ID = "MegaMekLab-Instance";
    private static boolean noStartup;

    public static void main(String... args) {
        boolean multiInstanceMode = hasArgument(args, "--multi");
        noStartup = hasArgument(args, "--no-startup");
        // Filter out already read args
        String[] filteredArgs = filterArguments(args, new String[] { "--multi", "--no-startup" });
        if (args.length >= 1) {
            final String filePath = args[0];
            if (noStartup && filePath.toLowerCase().endsWith(".mul")) {
                multiInstanceMode = true;
            }
        }

        // Skip single instance check if in multi-instance mode
        if (!multiInstanceMode) {
            // Initialize single instance service
            singleInstanceService = new SingleInstanceService(APPLICATION_ID);

            boolean isFirstInstance = singleInstanceService.register();

            if (!isFirstInstance) {
                // Another instance is running, send arguments and exit
                LOGGER.info("Another instance of MegaMekLab is already running");

                if (filteredArgs.length >= 1) {
                    singleInstanceService.sendMessage("FILE=" + filteredArgs[0]);
                } else {
                    singleInstanceService.sendMessage("ACTIVATE");
                }

                System.exit(0);
                return;
            }

            // Message handler
            singleInstanceService.setMessageHandler(message -> {
                LOGGER.info("Received command from another instance: {}", message);

                SwingUtilities.invokeLater(() -> {
                    if ("ACTIVATE".equals(message)) {
                        bringWindowsToFront();
                    } else if (message.startsWith("FILE=")) {
                        String filePath = message.substring("FILE=".length());
                        openUnitFile(filePath, false);
                    } else {
                        LOGGER.error("Unknown command: {}", message);
                    }
                });
            });

            // Add shutdown hook to clean up resources
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (singleInstanceService != null) {
                    singleInstanceService.cleanup();
                }
            }));
        } else {
            LOGGER.info("Starting in multi-instance mode (--multi argument detected)");
        }

        ObjectInputFilter.Config.setSerialFilter(sanityInputFilter);

        Sentry.init(options -> {
            options.setEnableExternalConfiguration(true);
            options.setDsn("https://6dfac298f9ed6fb0d9a9f7e5669d386b@sentry.tapenvy.us/9");
            options.setEnvironment("production");
            options.setTracesSampleRate(1.0);
            options.setProfilesSampleRate(1.0);
            options.setEnableAppStartProfiling(true);
            options.setDebug(true);
            options.setServerName("MegaMekLabClient");
            options.setRelease(SuiteConstants.VERSION.toString());
        });

        // First, create a global default exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, t) -> {
            final String name = t.getClass().getName();
            final String message = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION, name);
            final String title = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION_TITLE, name);
            LOGGER.errorDialog(t, message, title);
        });

        MegaMek.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMekLab.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMek.initializeSuiteGraphicalSetups(MMLConstants.PROJECT_NAME);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        ToolTipManager.sharedInstance().setReshowDelay(50);
        startup(filteredArgs);

        // log jvm parameters
        LOGGER.info(ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    /**
     * Check if arguments contains specific argument
     *
     * @param args Command line arguments
     * @param arg  Argument to check for
     *
     * @return True if the argument exists in the command line arguments, false
     */
    private static boolean hasArgument(String[] args, String arg) {
        for (String argument : args) {
            if (arg.equals(argument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes specific arguments from the command line arguments
     *
     * @param args         Command line arguments
     * @param argsToRemove Arguments to remove
     *
     * @return Filtered arguments
     */
    private static String[] filterArguments(String[] args, String[] argsToRemove) {
        return java.util.Arrays.stream(args).filter(argument -> {
            for (String arg : argsToRemove) {
                if (arg.equals(argument)) {
                    return false;
                }
            }
            return true;
        }).toArray(String[]::new);
    }

    public static void initializeLogging(final String originProject) {
        LOGGER.info(getUnderlyingInformation(originProject));
    }

    /**
     * @param originProject the project that launched MegaMekLab
     *
     * @return the underlying information for this launch of MegaMekLab
     */
    public static String getUnderlyingInformation(final String originProject) {
        return MegaMek.getUnderlyingInformation(originProject, MMLConstants.PROJECT_NAME);
    }

    private static void startup(String[] args) {
        EquipmentType.initializeTypes();
        MekSummaryCache.getInstance();
        CConfig.load();
        UnitUtil.loadFonts();

        MegaMek.getMMPreferences().loadFromFile(SuiteConstants.MM_PREFERENCES_FILE);
        getMMLPreferences().loadFromFile(SuiteConstants.MML_PREFERENCES_FILE);

        // TODO : Individual localizations
        Locale.setDefault(getMMLOptions().getLocale());

        updateGuiScaling(); // also sets the look-and-feel

        if (args.length >= 1) {
            String name = args[0];
            if (openUnitFile(name, noStartup)) {
                return;
            }
        }

        // Create a startup frame and display it
        switch (CConfig.getStartUpType()) {
            case NEW_MEK -> UiLoader.loadUi(Entity.ETYPE_MEK, false, false);
            case NEW_TANK -> UiLoader.loadUi(Entity.ETYPE_TANK, false, false);
            case NEW_FIGHTER -> UiLoader.loadUi(Entity.ETYPE_AERO, false, false);
            case NEW_DROPSHIP -> UiLoader.loadUi(Entity.ETYPE_DROPSHIP, false, false);
            case NEW_PROTOMEK -> UiLoader.loadUi(Entity.ETYPE_PROTOMEK, false, false);
            case NEW_JUMPSHIP -> UiLoader.loadUi(Entity.ETYPE_JUMPSHIP, false, false);
            case NEW_SUPPORT_VEE -> UiLoader.loadUi(Entity.ETYPE_SUPPORT_TANK, false, false);
            case NEW_BATTLEARMOR -> UiLoader.loadUi(Entity.ETYPE_BATTLEARMOR, false, false);
            case NEW_CONVENTIONAL_INFANTRY -> UiLoader.loadUi(Entity.ETYPE_INFANTRY, false, false);
            case RECENT_UNIT -> {
                if (!loadMostRecentUnit()) {
                    StartupGUI.getInstance().setVisible(true);
                }
            }
            case RESTORE_TABS -> UiLoader.restoreTabbedUi();
            default -> StartupGUI.getInstance().setVisible(true);
        }
    }

    private static void setLookAndFeel() {
        try {
            String profileLookAndFeel = CConfig.getParam(CConfig.GUI_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(profileLookAndFeel);
            UIUtil.updateAfterUiChange();
        } catch (Exception ex) {
            LOGGER.error(ex, "setLookAndFeel() Exception {}", ex.getMessage());
        }
    }

    public static void updateGuiScaling() {
        System.setProperty("flatlaf.uiScale", Double.toString(GUIPreferences.getInstance().getGUIScale()));
        setLookAndFeel();
    }

    public static SuitePreferences getMMLPreferences() {
        return mmlPreferences;
    }

    public static MMLOptions getMMLOptions() {
        return mmlOptions;
    }

    /**
     * Brings a visible window to the front
     */
    private static void bringWindowsToFront() {
        for (Window window : Window.getWindows()) {
            if (!window.isVisible()) {
                continue;
            }
            if (window instanceof MegaMekLabTabbedUI mainUI) {
                if ((mainUI.getExtendedState() & JFrame.ICONIFIED) != 0) {
                    mainUI.setExtendedState(mainUI.getExtendedState() & ~JFrame.ICONIFIED);
                }
                // Bring the window to the front
                mainUI.setAlwaysOnTop(true);
                mainUI.toFront();
                mainUI.requestFocus();
                mainUI.setAlwaysOnTop(false);
                break; // Exit the loop after bringing the first visible window to the front
            }
        }
    }

    /**
     * Opens a unit file. This is called from command line
     *
     * @param filePath  The path to the file to open
     * @param noStartup for .mul files
     */
    private static boolean openUnitFile(String filePath, boolean noStartup) {
        try {
            final File file = new File(filePath).getCanonicalFile();
            if (!file.exists()) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            if (file.getName().toLowerCase().endsWith(".blk") || file.getName().toLowerCase().endsWith(".mtf")) {
                LOGGER.info("Opening file: {}", filePath);
                Entity e = new MekFileParser(file).getEntity();
                if (!UnitUtil.validateUnit(e).isBlank()) {
                    PopupMessages.showUnitInvalidWarning(null, UnitUtil.validateUnit(e));
                }
                UiLoader.loadUi(e, file.toString());
            } else if (file.getName().toLowerCase().endsWith(".mul")) {
                Runnable printMul = () -> MULManager.processMULFile(file, null);
                if (noStartup) {
                    printMul.run();
                } else {
                    SwingUtilities.invokeLater(printMul);
                    return false;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex, "Error processing file: {}", filePath);
        }
        return true;
    }

    /**
     * Tries loading the most recent unit. Returns true when successful, false when no such unit could be found or the
     * unit doesn't load.
     *
     * @return True when the most recent unit is successfully loaded
     */
    private static boolean loadMostRecentUnit() {
        String mostRecentName = CConfig.getRecentFile(1);
        if (mostRecentName.isBlank()) {
            PopupMessages.showNoMostRecentUnitError(null);
            return false;
        }

        File unitFile = new File(mostRecentName);
        if (!unitFile.isFile()) {
            PopupMessages.showMostRecentUnitMissingError(null);
            return false;
        }

        try {
            Entity recentUnit = new MekFileParser(unitFile).getEntity();
            if (recentUnit == null) {
                return false;
            } else if (!UnitUtil.validateUnit(recentUnit).isBlank()) {
                PopupMessages.showUnitInvalidWarning(null, UnitUtil.validateUnit(recentUnit));
            }

            UiLoader.loadUi(recentUnit, unitFile.toString());
            return true;
        } catch (Exception ex) {
            final String message = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION, ex.getMessage());
            final String title = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION_TITLE, unitFile);
            LOGGER.errorDialog(ex, message, title);
            return false;
        }
    }

    private MegaMekLab() {
    }
}
