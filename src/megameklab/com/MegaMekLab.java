/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import megameklab.com.ui.Mek.MainUI;

public class MegaMekLab {
    public static final String VERSION = "0.0.0.16-dev-116";

    public static void main(String[] args) {
        String logFileName = "./logs/megameklab.log";

        if (args.length < 1) {
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
            Runtime runtime = Runtime.getRuntime();

            System.out.println("Memory Allocated [" + runtime.maxMemory() / 1000 + "]");
            // Need at least 256m to run MegaMekLab
            if (runtime.maxMemory() < 256000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-jar", "MegaMekLab.jar" };
                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            new MainUI();
        } else if (args[0].equalsIgnoreCase("-vehicle")) {
            if (args.length == 1) {
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
            Runtime runtime = Runtime.getRuntime();

            System.out.println("Memory Allocated [" + runtime.maxMemory() / 1000 + "]");
            // Need at least 256m to run MegaMekLab
            if (runtime.maxMemory() < 256000000) {
                try {
                    String[] call =
                        { "java", "-Xmx256m", "-splash:data/images/splash/megameklabsplashvehicle.jpg", "-jar", "MegaMekLab.jar", "-vehicle" };
                    runtime.exec(call);
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            new megameklab.com.ui.Vehicle.MainUI();
        } else {
            new MainUI();
        }
    }
}