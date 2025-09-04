/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.dialog;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import megamek.client.ui.util.UIUtil;
import megamek.common.Configuration;
import megamek.common.units.Entity;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.MegaMekLabTabbedUI;
import megameklab.ui.StartupGUI;
import megameklab.ui.battleArmor.BAMainUI;
import megameklab.ui.combatVehicle.CVMainUI;
import megameklab.ui.combatVehicle.GEMainUI;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.handheldWeapon.HHWMainUI;
import megameklab.ui.infantry.CIMainUI;
import megameklab.ui.largeAero.DSMainUI;
import megameklab.ui.largeAero.WSMainUI;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.protoMek.PMMainUI;
import megameklab.ui.supportVehicle.SVMainUI;
import megameklab.ui.util.TabUtil;
import megameklab.util.UnitUtil;

/**
 * This class prepares a new editing UI for either a given Entity or an Entity type (new unit). While preparation runs,
 * a splash screen is shown.
 *
 * @author Taharqa
 * @author Simon (Juliez)
 */
public class UiLoader {

    private static final int MINIMUM_SPLASH_TIME = 0;

    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("megameklab.resources.Menu");
    private static final String LOAD_SCREEN_IMAGE = Configuration.miscImagesDir() + "/load.jpg";
    private final JDialog splashImage;
    private final long type;
    private final boolean primitive;
    private final boolean industrial;
    private final Entity newUnit;
    private final String fileName;
    private boolean restore = false;

    public static void loadUi(Entity newUnit, String fileName) {
        new UiLoader(UnitUtil.getEditorTypeForEntity(newUnit),
              newUnit.isPrimitive(),
              newUnit.isIndustrialMek(),
              newUnit,
              fileName).show();
    }

    public static void loadUi(long type, boolean primitive, boolean industrial) {
        new UiLoader(type, primitive, industrial, null, "").show();
    }

    public static void restoreTabbedUi() {
        new UiLoader(true).show();
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

        splashImage = makeSplash();
    }

    private UiLoader(boolean restore) {
        this(0, false, false, null, null);

        if (!restore) {
            throw new IllegalArgumentException("Impossible!");
        }

        this.restore = true;
    }

    private static JDialog makeSplash() {
        var splashImage = new JDialog((JFrame) null, "MML Loading Splash");
        splashImage.setUndecorated(true);
        splashImage.add(UIUtil.createSplashComponent(LOAD_SCREEN_IMAGE, splashImage), BorderLayout.CENTER);
        splashImage.pack();
        splashImage.setLocationRelativeTo(null);
        return splashImage;
    }

    /**
     * Shows the splash image, hides the calling frame and starts loading the new unit's UI.
     */
    public void show() {
        splashImage.setVisible(true);
        SwingUtilities.invokeLater(this::loadNewUi);
    }

    private void loadNewUi() {
        try {
            long start = java.lang.System.currentTimeMillis();
            MegaMekLabTabbedUI tabbedUi;
            if (!restore) {
                MegaMekLabMainUI newUI;
                if (newUnit != null) {
                    UnitUtil.updateLoadedUnit(newUnit);
                    newUI = getUI(newUnit, fileName);
                } else {
                    newUI = getUI(type, primitive, industrial);
                }
                long loadTime = java.lang.System.currentTimeMillis() - start;
                if (loadTime < MINIMUM_SPLASH_TIME) {
                    // Show the splash for at least the minimum time
                    Thread.sleep(MINIMUM_SPLASH_TIME - loadTime);
                }
                tabbedUi = new MegaMekLabTabbedUI(newUI);
                tabbedUi.setVisible(true);
            } else {
                try {
                    var editors = TabUtil.loadTabState().toArray(new MegaMekLabMainUI[0]);
                    if (editors.length == 0) {
                        throw new IllegalStateException("Could not restore tabs");
                    }
                    long loadTime = java.lang.System.currentTimeMillis() - start;
                    if (loadTime < MINIMUM_SPLASH_TIME) {
                        // Show the splash for at least the minimum time
                        Thread.sleep(MINIMUM_SPLASH_TIME - loadTime);
                    }
                    tabbedUi = new MegaMekLabTabbedUI(editors);
                    tabbedUi.setVisible(true);
                } catch (IOException | IllegalStateException e) {
                    StartupGUI.getInstance().setVisible(true);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            splashImage.setVisible(false);
            splashImage.dispose();
        }
    }

    public static void initializeFromBlankUI(Consumer<MegaMekLabTabbedUI> initializer) {
        var splash = makeSplash();
        splash.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            try {
                var newUI = new MegaMekLabTabbedUI();
                initializer.accept(newUI);
                newUI.setVisible(true);
            } finally {
                splash.setVisible(false);
                splash.dispose();
            }
        });
    }

    /**
     * @return The correct MainUI for an Entity of the given type (ETYPE_xxx), primitive and industrial flag.
     */
    public static MegaMekLabMainUI getUI(long type, boolean primitive, boolean industrial) {
        if (type == Entity.ETYPE_TANK) {
            return new CVMainUI();
        } else if (type == Entity.ETYPE_SUPPORT_TANK) {
            return new SVMainUI();
        } else if (type == Entity.ETYPE_PROTOMEK) {
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
        } else if (type == Entity.ETYPE_HANDHELD_WEAPON) {
            return new HHWMainUI();
        } else if (type == Entity.ETYPE_GUN_EMPLACEMENT) {
            return new GEMainUI();
        } else {
            return new BMMainUI(primitive, industrial);
        }
    }

    /**
     * @return The correct MainUI for an Entity
     */
    public static MegaMekLabMainUI getUI(Entity entity, String filename) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        long type = UnitUtil.getEditorTypeForEntity(entity);
        if (type == Entity.ETYPE_TANK) {
            return new CVMainUI(entity, filename);
        } else if (type == Entity.ETYPE_SUPPORT_TANK) {
            return new SVMainUI(entity, filename);
        } else if (type == Entity.ETYPE_PROTOMEK) {
            return new PMMainUI(entity, filename);
        } else if (type == Entity.ETYPE_BATTLEARMOR) {
            return new BAMainUI(entity, filename);
        } else if (type == Entity.ETYPE_INFANTRY) {
            return new CIMainUI(entity, filename);
        } else if (type == Entity.ETYPE_AERO) {
            return new ASMainUI(entity, filename);
        } else if (type == Entity.ETYPE_DROPSHIP) {
            return new DSMainUI(entity, filename);
        } else if (type == Entity.ETYPE_JUMPSHIP) {
            return new WSMainUI(entity, filename);
        } else if (type == Entity.ETYPE_HANDHELD_WEAPON) {
            return new HHWMainUI(entity, filename);
        } else if (type == Entity.ETYPE_GUN_EMPLACEMENT) {
            return new GEMainUI(entity, filename);
        } else {
            return new BMMainUI(entity, filename);
        }
    }
}
