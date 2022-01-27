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
package megameklab.com;

import megamek.MegaMek;
import megamek.client.ui.preferences.SuitePreferences;
import megamek.common.Configuration;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megamek.common.QuirksHandler;
import megameklab.com.ui.StartupGUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
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
        MegaMek.showInfo(MMLConstants.PROJECT_NAME);
        showInfo(MMLConstants.PROJECT_NAME);
        MegaMek.handleLegacyLogging();

        // Third, let's set some default properties
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name","MegaMekLab");

        // Fourth, register any fonts in the fonts directory
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        List<Font> fontList = new ArrayList<>();
        collectFontsFromDir(Configuration.fontsDir(), fontList);
        for (Font font : fontList) {
            ge.registerFont(font);
        }

        // Finally, let's handle startup
        startup();
    }

    /**
     * Recursively search a directory and attempt to create a truetype font from
     * every file with the ttf suffix
     *
     * @param dir  The directory to search
     * @param list The list to add fonts to as they are created
     */
    private static void collectFontsFromDir(File dir, List<Font> list) {
        File[] files = dir.listFiles();
        if (null != files) {
            for (File f : files) {
                if (f.isDirectory() && !f.getName().startsWith(".")) {
                    collectFontsFromDir(f, list);
                } else if (f.getName().toLowerCase().endsWith(".ttf")) {
                    try {
                        list.add(Font.createFont(Font.TRUETYPE_FONT, f));
                    } catch (IOException | FontFormatException ex) {
                        LogManager.getLogger().error("Error creating font from " + f, ex);
                    }
                }
            }
        }
    }

    /**
     * Prints some information about MegaMekLab. Used in log files to figure out the JVM and version
     * of MegaMekLab.
     * @param originProject the project launching MegaMekLab
     */
    public static void showInfo(final String originProject) {
        String msg = "Starting MegaMekLab v" + MMLConstants.VERSION;
        msg += "\n\tToday is " + LocalDate.now()
                + "\n\tOrigin Project: " + originProject
                + "\n\tJava Vendor: " + System.getProperty("java.vendor")
                + "\n\tJava Version: " + System.getProperty("java.version")
                + "\n\tPlatform: " + System.getProperty("os.name") + " " + System.getProperty("os.version")
                + " (" + System.getProperty("os.arch") + ")"
                + "\n\tTotal memory available to MegaMek: "
                + NumberFormat.getInstance().format(Runtime.getRuntime().maxMemory()) + " GB";
        LogManager.getLogger().info(msg);
    }
    
    private static void startup() {
        EquipmentType.initializeTypes();
        MechSummaryCache.getInstance();
        try {
            QuirksHandler.initQuirksList();
        } catch (Exception e) {
            LogManager.getLogger().warn("Could not load quirks");
        }
        CConfig.load();
        UnitUtil.loadFonts();

        MegaMek.getMMPreferences().loadFromFile(MMLConstants.MM_PREFERENCES_FILE);
        getMMLPreferences().loadFromFile(MMLConstants.MML_PREFERENCES_FILE);

        // TODO : Individual localizations
        Locale.setDefault(getMMLOptions().getLocale());

        // Add additional themes
        UIManager.installLookAndFeel("Flat Light", "com.formdev.flatlaf.FlatLightLaf");
        UIManager.installLookAndFeel("Flat IntelliJ", "com.formdev.flatlaf.FlatIntelliJLaf");
        UIManager.installLookAndFeel("Flat Dark", "com.formdev.flatlaf.FlatDarkLaf");
        UIManager.installLookAndFeel("Flat Darcula", "com.formdev.flatlaf.FlatDarculaLaf");

        setLookAndFeel();

        // Create a startup frame and display it
        StartupGUI sud = new StartupGUI();
        sud.setVisible(true);
    }
    
    private static void setLookAndFeel() {
        try {
            String plaf = CConfig.getParam(CConfig.CONFIG_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(plaf);
        } catch (Exception e) {
            LogManager.getLogger().error("", e);
       }
    }
    
    /**
     * Helper function that calculates the maximum screen width available locally.
     * @return Maximum screen width.
     */
    public static double calculateMaxScreenWidth() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        double maxWidth = 0;
        for (GraphicsDevice g : gs) {
            Rectangle b = g.getDefaultConfiguration().getBounds();
            if (b.getWidth() > maxWidth) { // Update the max size found on this monitor
                maxWidth = b.getWidth();
            }
        }
        
        return maxWidth;
    }

    public static SuitePreferences getMMLPreferences() {
        return mmlPreferences;
    }

    public static MMLOptions getMMLOptions() {
        return mmlOptions;
    }
}
