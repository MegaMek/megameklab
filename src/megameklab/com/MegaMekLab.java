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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import megameklab.com.ui.Mek.MainUI;

public class MegaMekLab {
    public static final String VERSION = "0.43.1-git";

    public static void main(String[] args) {
    	System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name","MegaMekLab");

        String logFileName = "./logs/megameklablog.txt";
        Locale.setDefault(Locale.US);

        //Taharqa: I am not sure why this is here, so I am commenting it
        //out for awhile because I suspect it might be responsible for the
        //partial unit.cache problem in MHQ.
        //new File("./data/mechfiles/units.cache").delete();

        boolean logs = true;
        boolean vehicle = false;
        boolean battlearmor = false;

        for (int pos = 0; pos < args.length; pos++) {
            if (args[pos].equalsIgnoreCase("-vehicle")) {
                vehicle = true;
            }

            if (args[pos].equalsIgnoreCase("-battlearmor")) {
                battlearmor = true;
            }

            if (args[pos].equalsIgnoreCase("-nolog")) {
                logs = false;
            }
        }

        if (logs) {
            try {
                if (!new File("./logs/").exists()) {
                    new File("./logs/").mkdir();
                }
                PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFileName), 64));
                System.setOut(ps);
                System.setErr(ps);
            } catch (Exception ex) {
                System.err.println("Unable to redirect output");
            }
        }

        if (vehicle) {
            Runtime runtime = Runtime.getRuntime();

            System.out.println("Memory Allocated [" + (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashvehicle.jpg", "-jar", "MegaMekLab.jar", "-vehicle" };

                    if (!logs) {
                        call = new String[]
                            { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashvehicle.jpg", "-jar", "MegaMekLab.jar", "-vehicle", "-nolog" };
                    }
                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            new megameklab.com.ui.Vehicle.MainUI();
        } else if (battlearmor) {
            Runtime runtime = Runtime.getRuntime();

            System.out.println("Memory Allocated [" + (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashbattlearmor.jpg", "-jar", "MegaMekLab.jar", "-battlearmor" };

                    if (!logs) {
                        call = new String[]
                            { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashbattlearmor.jpg", "-jar", "MegaMekLab.jar", "-battlearmor", "-nolog" };
                    }
                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            new megameklab.com.ui.BattleArmor.MainUI();
        } else {

            Runtime runtime = Runtime.getRuntime();

            System.out.println("Memory Allocated [" + (runtime.maxMemory() / 1000) + "]");
            // Need at least 200m to run MegaMekLab
            if (runtime.maxMemory() < 200000000) {
                try {

                    String[] call =
                        { "java", "-Xmx256m", "-jar", "MegaMekLab.jar" };

                    if (!logs) {
                        call = new String[]
                            { "java", "-Xmx256m", "-jar", "MegaMekLab.jar", "-nolog" };
                    }
                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            new MainUI();
        }
    }
}