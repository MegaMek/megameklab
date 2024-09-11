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

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import megamek.client.ui.swing.GUIPreferences;
import org.apache.logging.log4j.LogManager;

import io.sentry.Sentry;
import megamek.MegaMek;
import megamek.SuiteConstants;
import megamek.client.ui.preferences.SuitePreferences;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MechFileParser;
import megamek.common.MechSummaryCache;
import megamek.common.net.marshalling.SanityInputFilter;
import megameklab.ui.PopupMessages;
import megameklab.ui.StartupGUI;
import megameklab.ui.dialog.UiLoader;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

public class MegaMekLab {
    private static final SuitePreferences mmlPreferences = new SuitePreferences();
    private static final MMLOptions mmlOptions = new MMLOptions();
    private static final SanityInputFilter sanityInputFilter = new SanityInputFilter();

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
            Sentry.captureException(t);
            LogManager.getLogger().error("Uncaught Exception Detected", t);
            PopupMessages.showUncaughtException(null, t);
        });

        System.setProperty("flatlaf.uiScale", Double.toString(GUIPreferences.getInstance().getGUIScale()));
        System.setProperty("flatlaf.uiScale", "1.7");
        MegaMek.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMekLab.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMek.initializeSuiteGraphicalSetups(MMLConstants.PROJECT_NAME);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        ToolTipManager.sharedInstance().setReshowDelay(50);
        startup();
    }

    public static void initializeLogging(final String originProject) {
        if (LogManager.getLogger().isInfoEnabled()) {
            LogManager.getLogger().info(getUnderlyingInformation(originProject));
        }
    }

    /**
     * @param originProject the project that launched MegaMekLab
     * @return the underlying information for this launch of MegaMekLab
     */
    public static String getUnderlyingInformation(final String originProject) {
        return MegaMek.getUnderlyingInformation(originProject, MMLConstants.PROJECT_NAME);
    }

    private static void startup() {
        EquipmentType.initializeTypes();
        MechSummaryCache.getInstance();
        CConfig.load();
        UnitUtil.loadFonts();

        MegaMek.getMMPreferences().loadFromFile(SuiteConstants.MM_PREFERENCES_FILE);
        getMMLPreferences().loadFromFile(SuiteConstants.MML_PREFERENCES_FILE);

        // TODO : Individual localizations
        Locale.setDefault(getMMLOptions().getLocale());

        setLookAndFeel();

        // Create a startup frame and display it
        switch (CConfig.getStartUpType()) {
            case NEW_MEK -> UiLoader.loadUi(Entity.ETYPE_MECH, false, false);
            case NEW_TANK -> UiLoader.loadUi(Entity.ETYPE_TANK, false, false);
            case NEW_FIGHTER -> UiLoader.loadUi(Entity.ETYPE_AERO, false, false);
            case NEW_DROPSHIP -> UiLoader.loadUi(Entity.ETYPE_DROPSHIP, false, false);
            case NEW_PROTOMEK -> UiLoader.loadUi(Entity.ETYPE_PROTOMECH, false, false);
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
        } catch (Exception ex) {
            Sentry.captureException(ex);
            LogManager.getLogger().error("", ex);
        }
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
            Entity recentUnit = new MechFileParser(unitFile).getEntity();
            if (recentUnit == null) {
                return false;
            } else if (!UnitUtil.validateUnit(recentUnit).isBlank()) {
                PopupMessages.showUnitInvalidWarning(null, UnitUtil.validateUnit(recentUnit));
            }

            UiLoader.loadUi(recentUnit, unitFile.toString());
            return true;
        } catch (Exception ex) {
            Sentry.captureException(ex);
            PopupMessages.showFileReadError(null, unitFile.toString(), ex.getMessage());
            return false;
        }
    }

    private MegaMekLab() {
    }
}
