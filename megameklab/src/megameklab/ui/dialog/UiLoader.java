/*
 * Copyright (c) 2019, 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.dialog;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.*;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.battleArmor.BAMainUI;
import megameklab.ui.combatVehicle.CVMainUI;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.infantry.CIMainUI;
import megameklab.ui.largeAero.DSMainUI;
import megameklab.ui.largeAero.WSMainUI;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.protoMek.PMMainUI;
import megameklab.ui.supportVehicle.SVMainUI;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * This class prepares a new editing UI for either a given Entity or an Entity type (new unit). While
 * preparation runs, a splash screen is shown.
 *
 * @author Taharqa
 * @author Simon (Juliez)
 */
public class UiLoader {

    /**
     * A map of resolution widths to file names for the startup screen
     */
    private static final TreeMap<Integer, String> LOAD_SCREEN_IMAGES = new TreeMap<>(Map.of(
            0, Configuration.miscImagesDir() + "/mml_load_spooky_hd.jpg",
            1441, Configuration.miscImagesDir() + "/mml_load_spooky_fhd.jpg",
            1921, Configuration.miscImagesDir() + "/mml_load_spooky_uhd.jpg"
    ));

    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("megameklab.resources.Menu");
    private final JDialog splashImage;
    private final long type;
    private final boolean primitive;
    private final boolean industrial;
    private final Entity newUnit;
    private final String fileName;

    public static void loadUi(Entity newUnit, String fileName) {
        if ((newUnit == null) || (newUnit instanceof Mech)) {
            new UiLoader(Entity.ETYPE_MECH, false, false, newUnit, fileName).show();
        } else if (newUnit.isSupportVehicle()) {
            new UiLoader(Entity.ETYPE_SUPPORT_TANK, false, false, newUnit, fileName).show();
        } else if (newUnit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            new UiLoader(Entity.ETYPE_DROPSHIP, newUnit.isPrimitive(), false, newUnit, fileName).show();
        } else if (newUnit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            new UiLoader(Entity.ETYPE_JUMPSHIP, newUnit.isPrimitive(), false, newUnit, fileName).show();
        } else if ((newUnit instanceof Aero) && !(newUnit instanceof FixedWingSupport)) {
            new UiLoader(Entity.ETYPE_AERO, newUnit.isPrimitive(), false, newUnit, fileName).show();
        } else if (newUnit instanceof BattleArmor) {
            new UiLoader(Entity.ETYPE_BATTLEARMOR, false, false, newUnit, fileName).show();
        } else if (newUnit instanceof Infantry) {
            new UiLoader(Entity.ETYPE_INFANTRY, false, false, newUnit, fileName).show();
        } else if (newUnit instanceof Protomech) {
            new UiLoader(Entity.ETYPE_PROTOMECH, false, false, newUnit, fileName).show();
        } else if ((newUnit instanceof Tank) && !(newUnit instanceof GunEmplacement)) {
            new UiLoader(Entity.ETYPE_TANK, false, false, newUnit, fileName).show();
        } else {
            PopupMessages.showUiLoadError(null);
            new UiLoader(Entity.ETYPE_MECH, false, false, null, "").show();
        }
    }

    public static void loadUi(long type, boolean primitive, boolean industrial) {
        new UiLoader(type, primitive, industrial, null, "").show();
    }

    /**
     * @param type       - the unit type to load the mainUI from, based on the types in StartupGUI.java
     * @param primitive  - is unit primitive
     * @param industrial - is unit industrial
     * @param newUnit    - a specific <code>Entity</code> to load in rather than default
     * @param fileName   - the file name of the new unit; empty String if the unit has no file
     */
    private UiLoader(long type, boolean primitive, boolean industrial, Entity newUnit, String fileName) {
        this.type = type;
        this.primitive = primitive;
        this.industrial = industrial;
        this.newUnit = newUnit;
        this.fileName = Objects.requireNonNullElse(fileName, "");

        splashImage = new JDialog((JFrame) null, "MML Loading Splash");
        splashImage.setUndecorated(true);
        splashImage.add(UIUtil.createSplashComponent(LOAD_SCREEN_IMAGES, splashImage), BorderLayout.CENTER);
        splashImage.pack();
        splashImage.setLocationRelativeTo(null);
    }

    /** Shows the splash image, hides the calling frame and starts loading the new unit's UI. */
    public void show() {
        splashImage.setVisible(true);
        SwingUtilities.invokeLater(this::loadNewUi);
    }

    private void loadNewUi() {
        MegaMekLabMainUI newUI = getUI(type, primitive, industrial);
        if (newUnit != null) {
            UnitUtil.updateLoadedUnit(newUnit);
            newUI.setEntity(newUnit, fileName);
            newUI.reloadTabs();
            newUI.refreshAll();
        }
        newUI.setVisible(true);
        splashImage.setVisible(false);
        splashImage.dispose();
    }

    /** @return The correct MainUI for an Entity of the given type (ETYPE_xxx), primitive and industrial flag. */
    public static MegaMekLabMainUI getUI(long type, boolean primitive, boolean industrial) {
        if (type == Entity.ETYPE_TANK) {
            return new CVMainUI();
        } else if (type == Entity.ETYPE_SUPPORT_TANK) {
            return new SVMainUI();
        } else if (type == Entity.ETYPE_PROTOMECH) {
            return new PMMainUI();
        } else if (type == Entity.ETYPE_BATTLEARMOR) {
            return new BAMainUI();
        } else if (type == Entity.ETYPE_INFANTRY) {
            return new CIMainUI();
        } else if (type == Entity.ETYPE_AERO) {
            return new ASMainUI(primitive);
        } else if (type == Entity.ETYPE_DROPSHIP) {
            return new DSMainUI(primitive);
        } else if (type == Entity.ETYPE_JUMPSHIP) {
            return new WSMainUI(primitive);
        } else {
            return new BMMainUI(primitive, industrial);
        }
    }
}