/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import megamek.common.units.Entity;

/**
 * This class contains static methods that each show a commonly used popup message, such as the "unit is invalid"
 * warning or common errors. The parent frame should ideally be not null.
 */
public final class PopupMessages {

    private static final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.PopupMessages");

    public static void showMostRecentUnitMissingError(Component parent) {
        showInfoMessage(parent, resources.getString("mostRecentNotFound"));
    }

    public static void showNoMostRecentUnitError(Component parent) {
        showInfoMessage(parent, resources.getString("noMostRecentUnit"));
    }

    public static void showSettingsImported(Component parent) {
        showInfoMessage(parent, resources.getString("settingsImported"));
    }

    public static void showSettingsImportHelp(Component parent) {
        showInfoMessage(parent, resources.getString("importSettingsHelp"));
    }

    public static void showUiLoadError(Component parent) {
        showErrorMessage(parent, resources.getString("loadUiError"));
    }

    public static void showInvalidLocationInfo(Component parent, String equipmentName, String locationName) {
        showErrorMessage(parent, String.format(resources.getString("invalidLocation"), equipmentName, locationName));
    }

    public static void showFileReadError(Component parent, String fileName, String errorMessage) {
        showErrorMessage(parent, String.format(resources.getString("fileReadError"), fileName, errorMessage));
    }

    public static void showFileReadError(Component parent, String fileName) {
        showErrorMessage(parent, String.format(resources.getString("fileReadErrorNoEx"), fileName));
    }

    public static void showLocationFullError(Component parent, String equipmentName) {
        showErrorMessage(parent, String.format(resources.getString("locationFull"), equipmentName));
    }

    public static void showLocationFullError(Component parent) {
        showErrorMessage(parent, String.format(resources.getString("locationFull"), "equipment"));
    }

    public static void showUnitInvalidWarning(Component parent) {
        showWarningMessage(parent, resources.getString("invalidUnit"));
    }

    public static void showUnitInvalidWarning(Component parent, String validationResult) {
        showWarningMessage(parent, String.format(resources.getString("invalidUnit"), validationResult));
    }

    public static void showUnitIsValid(Component parent) {
        showInfoMessage(parent, resources.getString("validUnit"));
    }

    public static void showFileWriteError(Component parent, String errorMessage) {
        showErrorMessage(parent, String.format(resources.getString("fileWriteError"), errorMessage));
    }

    public static void showUnitSavedMessage(Component parent, Entity entity, File file) {
        showInfoMessage(parent, String.format(resources.getString("unitSaved"),
              entity.getChassis(), entity.getModel(), file));
    }

    public static void showLookAndFeelError(Component parent, String errorMessage) {
        showErrorMessage(parent, String.format(resources.getString("lookAndFeelError"), errorMessage));
    }

    public static void showUncaughtException(Component parent, Throwable throwable) {
        final String name = throwable.getClass().getName();
        showErrorMessage(parent, String.format(resources.getString("uncaughtException"), name));
    }

    public static void showImproperFileType(Component parent) {
        showErrorMessage(parent, resources.getString("improperFileType"));
    }

    public static void showNoFluffImage(Component parent) {
        showErrorMessage(parent, resources.getString("noFluffImage"));
    }

    public static void showWrongIconSize(Component parent) {
        showErrorMessage(parent, resources.getString("wrongIconSize"));
    }

    // ############ Internal message handlers

    private static void showErrorMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, possiblyWrapInScrollBar(message),
              "MML encountered a problem", JOptionPane.ERROR_MESSAGE);
    }

    private static void showWarningMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, possiblyWrapInScrollBar(message),
              "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private static void showInfoMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, possiblyWrapInScrollBar(message),
              "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private static Object possiblyWrapInScrollBar(String message) {
        if (message.length() < 200) {
            return message;
        } else {
            JTextArea textArea = new JTextArea(message);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(600, 500));
            return scrollPane;
        }
    }

    private PopupMessages() {}
}
