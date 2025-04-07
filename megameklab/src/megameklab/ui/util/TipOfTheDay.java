/*
 * MegaMek -
 * Copyright (C) 2000-2008 Ben Mazur (bmazur@sev.org)
 * Copyright © 2013 Nicholas Walczak (walczak@cs.umn.edu)
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */
package megameklab.ui.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Drake
 * 
 * Provides a daily tip functionality for MegaMekLab
 */
public final class TipOfTheDay {
    // Prevent instantiation
    private TipOfTheDay() {
    }
    
    // Static tips array
    private static final List<String> TIPS = Arrays.asList(
        "You can copy a Record Sheet as image by doing right click and \"Copy to clipboard\" on it. You can then paste it in any image editor or even discord.",
        "Press CTRL+S to quickly save your current unit.",
        "Use CTRL+Z to undo and CTRL+Y to redo recent changes to your unit design.",
        "Customize your workspace by dragging tabs to reorder or separate them from the main window.",
        "Right-click on equipment in the \"Assign Criticals\" tab for additional options.",
        "The status bar shows any validation problems with your design.",
        "Use \"Help\"->\"Validate Unit\" to check if your design has any errors.",
        "You can drag and drop MUL files onto the start screen to open them directly.",
        "Use the BV Calculator to see how battle value is calculated for your unit.",
        "MegaMekLab supports many rule levels and technology bases.",
        "Double-check your heat sinks when designing 'Mechs.",
        "Zoom in or out on Record Sheets using the mouse wheel, and click-drag to pan the view.",
        "In the unit selection screen, you can select multiple units by holding down the CTRL key.",
        "In the Status Bar, you can click on Weight, BV and Dry Cost for additional informations."
    );

    /**
     * Gets the tip for today based on the current date
     * @return A tip string for today
     */
    public static String getTodaysTip() {
        LocalDate today = LocalDate.now();
        int dayOfYear = today.getDayOfYear();
        int tipIndex = dayOfYear % TIPS.size();
        return TIPS.get(tipIndex);
    }

    /**
     * Gets a random tip from the list
     * @return A random tip string
     */
    public static String getRandomTip() {
        int randomIndex = (int) (Math.random() * TIPS.size());
        return TIPS.get(randomIndex);
    }
}