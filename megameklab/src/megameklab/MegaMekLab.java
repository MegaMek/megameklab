/*
 * MegaMekLab
 * Copyright (c) 2008-2024 - The MegaMek Team. All Rights Reserved.
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
package megameklab;

import java.awt.Window;
import java.awt.event.WindowEvent;
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
import megamek.client.ui.preferences.SuitePreferences;
import megamek.client.ui.swing.GUIPreferences;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MekFileParser;
import megamek.common.MekSummaryCache;
import megamek.common.net.marshalling.SanityInputFilter;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabTabbedUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.StartupGUI;
import megameklab.ui.dialog.UiLoader;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import megameklab.util.SingleInstanceService;

public class MegaMekLab {
    private static final SuitePreferences mmlPreferences = new SuitePreferences();
    private static final MMLOptions mmlOptions = new MMLOptions();
    private static final SanityInputFilter sanityInputFilter = new SanityInputFilter();
    private static final MMLogger logger = MMLogger.create(MegaMekLab.class);
    private static SingleInstanceService singleInstanceService;
    private static final String APPLICATION_ID = "MegaMekLab-Instance";
    private static boolean multiInstanceMode;
    private static boolean noStartup;

    public static void main(String... args) {

        multiInstanceMode = hasArgument(args, "--multi");
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
                logger.info("Another instance of MegaMekLab is already running");

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
                logger.info("Received command from another instance: {}", message);

                SwingUtilities.invokeLater(() -> {
                    if ("ACTIVATE".equals(message)) {
                        bringWindowsToFront();
                    } else if (message.startsWith("FILE=")) {
                        String filePath = message.substring("FILE=".length());
                        openUnitFile(filePath, false);
                    } else {
                        logger.error("Unknown command: {}", message);
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
            logger.info("Starting in multi-instance mode (--multi argument detected)");
        }

        ObjectInputFilter.Config.setSerialFilter(sanityInputFilter);

        Sentry.init(options -> {
            options.setEnableExternalConfiguration(true);
            options.setDsn("https://6dfac298f9ed6fb0d9a9f7e5669d386b@sentry.tapenvy.us/9");
            options.setEnvironment("production");
            options.setTracesSampleRate(0.2);
            options.setDebug(true);
            options.setServerName("MegaMekLabClient");
            options.setRelease(SuiteConstants.VERSION.toString());
        });

        // First, create a global default exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, t) -> {
            final String name = t.getClass().getName();
            final String message = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION, name);
            final String title = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION_TITLE, name);
            logger.errorDialog(t, message, title);
        });

        MegaMek.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMekLab.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMek.initializeSuiteGraphicalSetups(MMLConstants.PROJECT_NAME);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        ToolTipManager.sharedInstance().setReshowDelay(50);
        startup(filteredArgs);

        // log jvm parameters
        logger.info(ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    /**
     * Check if arguments contains specific argument
     * 
     * @param args Command line arguments
     * @param arg  Argument to check for
     * @return True if the argument exists in the command line arguments, false
     * 
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
     * Removes a specific argument from the command line arguments
     * 
     * @param args Command line arguments
     * @return Filtered arguments
     */
    private static String[] filterArgument(String[] args, String arg) {
        return java.util.Arrays.stream(args)
                .filter(argument -> !arg.equals(argument))
                .toArray(String[]::new);
    }

    /**
     * Removes specific arguments from the command line arguments
     * 
     * @param args         Command line arguments
     * @param argsToRemove Arguments to remove
     * @return Filtered arguments
     */
    private static String[] filterArguments(String[] args, String[] argsToRemove) {
        return java.util.Arrays.stream(args)
                .filter(argument -> {
                    for (String arg : argsToRemove) {
                        if (arg.equals(argument)) {
                            return false;
                        }
                    }
                    return true;
                })
                .toArray(String[]::new);
    }

    public static void initializeLogging(final String originProject) {
        logger.info(getUnderlyingInformation(originProject));
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
            };
        }

        // Create a startup frame and display it
        switch (CConfig.getStartUpType()) {
            case NEW_MEK -> UiLoader.loadUi(Entity.ETYPE_MEK, false, false);
            case NEW_TANK -> UiLoader.loadUi(Entity.ETYPE_TANK, false, false);
            case NEW_FIGHTER -> UiLoader.loadUi(Entity.ETYPE_AERO, false, false);
            case NEW_DROPSHIP -> UiLoader.loadUi(Entity.ETYPE_DROPSHIP, false, false);
            case NEW_PROTOMEK -> UiLoader.loadUi(Entity.ETYPE_PROTOMEK, false, false);
            case NEW_JUMPSHIP -> UiLoader.loadUi(Entity.ETYPE_JUMPSHIP, false, false);
            case NEW_SUPPORTVEE -> UiLoader.loadUi(Entity.ETYPE_SUPPORT_TANK, false, false);
            case NEW_BATTLEARMOR -> UiLoader.loadUi(Entity.ETYPE_BATTLEARMOR, false, false);
            case NEW_CONVINFANTRY -> UiLoader.loadUi(Entity.ETYPE_INFANTRY, false, false);
            case RECENT_UNIT -> {
                if (!loadMostRecentUnit()) {
                    new StartupGUI().setVisible(true);
                }
            }
            case RESTORE_TABS -> UiLoader.restoreTabbedUi();
            default -> {
                new StartupGUI().setVisible(true);
            }
        }
    }

    private static void setLookAndFeel() {
        try {
            String profileLookAndFeel = CConfig.getParam(CConfig.GUI_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(profileLookAndFeel);
            UIUtil.updateAfterUiChange();
        } catch (Exception ex) {
            logger.error("setLookAndFeel() Exception {}", ex);
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

    private static void dispatchEvent(WindowEvent windowEvent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispatchEvent'");
    }

    /**
     * Opens a unit file. This is called from command line
     *
     * @param filePath The path to the file to open
     * @param noStartup for .mul files
     */
    private static boolean openUnitFile(String filePath, boolean noStartup) {
        try {
            final File file = new File(filePath).getCanonicalFile();
            if (!file.exists()) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            if (file.getName().toLowerCase().endsWith(".blk") || file.getName().toLowerCase().endsWith(".mtf")) {
                logger.info("Opening file: " + filePath);
                Entity e = new MekFileParser(file).getEntity();
                if (!UnitUtil.validateUnit(e).isBlank()) {
                    PopupMessages.showUnitInvalidWarning(null, UnitUtil.validateUnit(e));
                }
                UiLoader.loadUi(e, file.toString());
            } else if (file.getName().toLowerCase().endsWith(".mul")) {
                logger.info("Printing file: " + filePath);
                Runnable printMul = () -> {
                    var frame = new JFrame();
                    UnitPrintManager.printMUL(frame, CConfig.getBooleanParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR),
                            file);
                    frame.dispose();
                };
                if (noStartup) {
                    printMul.run();
                } else {
                    SwingUtilities.invokeLater(printMul);
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error("Error processing file: " + filePath, e);
        }
        return true;
    }

    /**
     * Tries loading the most recent unit. Returns true when successful, false when
     * no such unit could be found or the
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
            logger.errorDialog(ex, message, title);
            return false;
        }
    }

    private MegaMekLab() {
    }
}
