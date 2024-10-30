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

import java.io.File;
import java.io.ObjectInputFilter;
import java.util.Locale;

import javax.swing.*;

import megamek.client.ui.swing.GUIPreferences;

import io.sentry.Sentry;
import megamek.MMLoggingConstants;
import megamek.MegaMek;
import megamek.SuiteConstants;
import megamek.client.ui.preferences.SuitePreferences;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MekFileParser;
import megamek.common.MekSummaryCache;
import megamek.common.net.marshalling.SanityInputFilter;
import megamek.logging.MMLogger;
import megameklab.ui.PopupMessages;
import megameklab.ui.StartupGUI;
import megameklab.ui.dialog.UiLoader;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;

public class MegaMekLab {
    private static final SuitePreferences mmlPreferences = new SuitePreferences();
    private static final MMLOptions mmlOptions = new MMLOptions();
    private static final SanityInputFilter sanityInputFilter = new SanityInputFilter();
    private static final MMLogger logger = MMLogger.create(MegaMekLab.class);

    public static void main(String... args) {
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
            logger.error(t, message, title);
        });

        MegaMek.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMekLab.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMek.initializeSuiteGraphicalSetups(MMLConstants.PROJECT_NAME);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        ToolTipManager.sharedInstance().setReshowDelay(50);
        startup(args);
    }

    public static void initializeLogging(final String originProject) {
        logger.info(getUnderlyingInformation(originProject));
    }

    /**
     * @param originProject the project that launched MegaMekLab
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

        if (args.length == 1) {
            try {
                var name = args[0];
                logger.info("Trying to open file {}", name);
                if (name.toLowerCase().endsWith(".blk") || name.endsWith(".mtf")) {
                    var file = new File(name);
                    Entity e = new MekFileParser(file).getEntity();
                    if (!UnitUtil.validateUnit(e).isBlank()) {
                        PopupMessages.showUnitInvalidWarning(null, UnitUtil.validateUnit(e));
                    }
                    UiLoader.loadUi(e, file.toString());
                    return;
                } else if (name.toLowerCase().endsWith(".mul")) {
                    SwingUtilities.invokeLater(() -> {
                        var frame = new JFrame();
                        UnitPrintManager.printMUL(frame,  CConfig.getBooleanParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR), new File(name));
                        frame.dispose();
                    });
                }
            } catch (Exception e) {
                logger.warn(e);
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
            case NEW_SUPPORTVEE -> UiLoader.loadUi(Entity.ETYPE_SUPPORT_TANK, false, false);
            case NEW_BATTLEARMOR -> UiLoader.loadUi(Entity.ETYPE_BATTLEARMOR, false, false);
            case NEW_CONVINFANTRY -> UiLoader.loadUi(Entity.ETYPE_INFANTRY, false, false);
            case RECENT_UNIT -> {
                if (!loadMostRecentUnit()) {
                    new StartupGUI().setVisible(true);
                }
            }
            default -> {
                new StartupGUI().setVisible(true);
            }
        }
    }

    private static void setLookAndFeel() {
        try {
            String plaf = CConfig.getParam(CConfig.GUI_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(plaf);
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
     * Tries loading the most recent unit. Returns true when successful, false when
     * no such unit could be found or the unit doesn't load.
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
            final String title = String.format(MMLoggingConstants.UNHANDLED_EXCEPTION_TITLE, unitFile.toString());
            logger.error(ex, message, title);
            return false;
        }
    }

    private MegaMekLab() {
    }
}
