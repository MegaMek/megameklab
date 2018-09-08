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

package megameklab.com;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import megamek.MegaMek;
import megamek.common.QuirksHandler;
import megamek.common.logging.DefaultMmLogger;
import megamek.common.logging.LogLevel;
import megamek.common.logging.MMLogger;
import megamek.common.preference.PreferenceManager;
import megameklab.com.ui.Mek.MainUI;

public class MegaMekLab {
    public static final String VERSION = "0.45.2-SNAPSHOT";

    private static final Logger log = LogManager.getLogger(MegaMekLab.class);

    public static void main(String[] args) {
        final String METHOD_NAME = "main(String[])";
        
    	System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name","MegaMekLab");

        Locale.setDefault(Locale.US);

        //Taharqa: I am not sure why this is here, so I am commenting it
        //out for awhile because I suspect it might be responsible for the
        //partial unit.cache problem in MHQ.
        //new File("./data/mechfiles/units.cache").delete();

        boolean vehicle = false;
        boolean battlearmor = false;

        for (String arg : args) {
            if (arg.equalsIgnoreCase("-vehicle")) {
                vehicle = true;
            }

            if (arg.equalsIgnoreCase("-battlearmor")) {
                battlearmor = true;
            }

            if (arg.equalsIgnoreCase("-nolog")) {
                // nop
            }
        }

        MegaMek.setupLog4j(MegaMekLab.class, Optional.empty());
        
        showInfo();
        
        if (vehicle) {
            Runtime runtime = Runtime.getRuntime();

            getLogger().log(MegaMekLab.class, METHOD_NAME, LogLevel.INFO,
                            "Memory Allocated [" +
                            (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashvehicle.jpg", "-jar", "MegaMekLab.jar", "-vehicle" };

                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    getLogger().error(MegaMekLab.class, METHOD_NAME, ex);
                }
            }

            new megameklab.com.ui.Vehicle.MainUI();
        } else if (battlearmor) {
            Runtime runtime = Runtime.getRuntime();

            getLogger().log(MegaMekLab.class, METHOD_NAME, LogLevel.INFO,
                            "Memory Allocated [" +
                            (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashbattlearmor.jpg", "-jar", "MegaMekLab.jar", "-battlearmor" };

                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    getLogger().error(MegaMekLab.class, METHOD_NAME, ex);
                }
            }

            new megameklab.com.ui.BattleArmor.MainUI();
        } else {

            Runtime runtime = Runtime.getRuntime();

            getLogger().log(MegaMekLab.class, METHOD_NAME, LogLevel.INFO,
                            "Memory Allocated [" +
                            (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {

                    String[] call =
                        { "java", "-Xmx256m", "-jar", "MegaMekLab.jar" };

                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    getLogger().error(MegaMekLab.class, METHOD_NAME, ex);
                }
            }
            try {
                // Needed for record sheet printing, and also displayed in unit preview.
                QuirksHandler.initQuirksList();
            } catch (IOException e) {
                // File is probably missing.
                getLogger().log(MegaMekLab.class, METHOD_NAME, LogLevel.INFO,
                        "Could not load quirks file.");
            }
            new MainUI();
        }
    }


    public static MMLogger getLogger() {
        return DefaultMmLogger.getInstance();
    }
    
    /**
     * Prints some information about MegaMekLab. Used in logfiles to figure out the
     * JVM and version of MegaMekLab.
     */
    private static void showInfo() {
        final long TIMESTAMP = new File(PreferenceManager
                .getClientPreferences().getLogDirectory()
                + File.separator
                + "timestamp").lastModified();
        // echo some useful stuff
        String msg = "Starting MegaMekLab v" + VERSION + " ..."; //$NON-NLS-1$ //$NON-NLS-2$
        if (TIMESTAMP > 0) {
            msg += "\n\tCompiled on " + new Date(TIMESTAMP).toString(); //$NON-NLS-1$
        }
        msg += "\n\tToday is " + new Date().toString(); //$NON-NLS-1$
        msg += "\n\tJava vendor " + System.getProperty("java.vendor"); //$NON-NLS-1$ //$NON-NLS-2$
        msg += "\n\tJava version " + System.getProperty("java.version"); //$NON-NLS-1$ //$NON-NLS-2$
        msg += "\n\tPlatform " //$NON-NLS-1$
               + System.getProperty("os.name") //$NON-NLS-1$
               + " " //$NON-NLS-1$
               + System.getProperty("os.version") //$NON-NLS-1$
               + " (" //$NON-NLS-1$
               + System.getProperty("os.arch") //$NON-NLS-1$
               + ")"; //$NON-NLS-1$
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024;
        msg += "\n\tTotal memory available to MegaMekLab: " + NumberFormat.getInstance().format(maxMemory) + " kB";
        log.info(msg);
    }

}
