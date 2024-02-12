/*
 * MegaMekLab - Copyright (C) 2008-2020 The MegaMek Team
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
package megameklab.util;

import megamek.client.ui.swing.util.FluffImageHelper;
import megamek.common.Configuration;
import megamek.common.Entity;
import megamek.common.annotations.Nullable;
import megamek.common.preference.PreferenceManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ImageHelper {

    /**
     * Checks for a fluff image for the unit starting with any file explicitly associated with the
     * unit then in the default directory for the unit type for a file consisting of the name of the
     * unit with an image format extension.
     * 
     * @param unit The unit to find a fluff image for
     * @return     A file to use for the fluff image, or null if no file is found.
     */
    public static @Nullable File getFluffFile(Entity unit) {
        List<File> fileCandidates = new ArrayList<>();
        var fluffDir = new File(Configuration.fluffImagesDir(), FluffImageHelper.getFluffPath(unit));

        // Internal fluff path matches
        for (String ext : FluffImageHelper.EXTENSIONS_FLUFF_IMAGE_FORMATS) {
            fileCandidates.add(new File(fluffDir, unit.getShortNameRaw() + ext));
        }

        // UserDir matches
        String userDir = PreferenceManager.getClientPreferences().getUserDir();
        if (!userDir.isBlank() && new File(userDir).isDirectory()) {
            var fluffUserDir = new File(userDir, fluffDir.toString());
            for (String ext : FluffImageHelper.EXTENSIONS_FLUFF_IMAGE_FORMATS) {
                fileCandidates.add(new File(fluffUserDir, unit.getChassis() + ext));
                fileCandidates.add(new File(fluffUserDir, unit.getFullChassis() + ext));
            }
        }

        // Chassis matches
        for (String ext : FluffImageHelper.EXTENSIONS_FLUFF_IMAGE_FORMATS) {
            fileCandidates.add(new File(fluffDir, unit.getChassis() + ext));
            fileCandidates.add(new File(fluffDir, unit.getFullChassis() + ext));
        }

        // Fallback
        fileCandidates.add(new File(fluffDir, "hud.png"));

        for (File possibleFile : fileCandidates) {
            if (possibleFile.exists() && !possibleFile.isDirectory()) {
                return possibleFile;
            }
        }
        return null;
    }
}