/*
 * MegaMekLab
 * Copyright (c) 2008-2022 - The MegaMek Team. All Rights Reserved.
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

import megamek.MegaMek;
import megamek.client.ui.preferences.SuitePreferences;
import megamek.common.Configuration;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megamek.common.QuirksHandler;
import megameklab.ui.StartupGUI;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MegaMekLab {
    private static final SuitePreferences mmlPreferences = new SuitePreferences();
    private static final MMLOptions mmlOptions = new MMLOptions();

    public static void main(String... args) {
        // First, create a global default exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, t) -> {
            LogManager.getLogger().error("Uncaught Exception Detected", t);
            final String name = t.getClass().getName();
            JOptionPane.showMessageDialog(null,
                    String.format("Uncaught %s detected. Please open up an issue containing all logs and the current unit file at https://github.com/MegaMek/megameklab/issues", name),
                    "Uncaught " + name, JOptionPane.ERROR_MESSAGE);
        });

        // Second, let's handle logging
        MegaMek.initializeLogging(MMLConstants.PROJECT_NAME);
        MegaMekLab.initializeLogging(MMLConstants.PROJECT_NAME);

        // Third, let's handle suite graphical setup initialization
        MegaMek.initializeSuiteGraphicalSetups(MMLConstants.PROJECT_NAME);

        // Finally, let's handle startup
        startup();
    }

    public static void initializeLogging(final String originProject) {
        LogManager.getLogger().info(getUnderlyingInformation(originProject));
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
        try {
            QuirksHandler.initQuirksList();
        } catch (Exception ex) {
            LogManager.getLogger().warn("Could not load quirks", ex);
        }
        CConfig.load();
        UnitUtil.loadFonts();

        MegaMek.getMMPreferences().loadFromFile(MMLConstants.MM_PREFERENCES_FILE);
        getMMLPreferences().loadFromFile(MMLConstants.MML_PREFERENCES_FILE);

        // TODO : Individual localizations
        Locale.setDefault(getMMLOptions().getLocale());

        setLookAndFeel();

        // Create a startup frame and display it
        StartupGUI sud = new StartupGUI();
        sud.setVisible(true);
    }

    private static void setLookAndFeel() {
        try {
            String plaf = CConfig.getParam(CConfig.CONFIG_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(plaf);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
       }
    }
    
    public static SuitePreferences getMMLPreferences() {
        return mmlPreferences;
    }

    public static MMLOptions getMMLOptions() {
        return mmlOptions;
    }
}
