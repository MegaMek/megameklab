/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
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

package megameklab.util;

import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.MekFileParser;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.EntitySavingException;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Helps to determine if a tab should have an "Unsaved Work" indicator and if the "would you like to save" prompt should appear.
 */
public class EntityChangedUtil {
    private static final MMLogger logger = MMLogger.create(EntityChangedUtil.class);

    private static final Map<String, Entity> cache = new ConcurrentHashMap<>();

    /**
     * Determines if the editor has unsaved changes.
     * @param editor The MainUI to check for changes
     * @return true if saving the unit would produce a different unit than the one saved already, or if there is no existing file for the unit.
     */
    public static boolean hasEntityChanged(MegaMekLabMainUI editor) {
        var filename = editor.getFileName();
        if (filename == null || filename.isBlank()) {
            return true;
        }

        // The idea behind how this works is to store a copy of the unit based on that unit's current file.
        // By encoding both the original and current version of the entity to mtf/blk at the same time,
        // We ensure that identical units will encode to identical mtf/blk strings, which indicates no unsaved work.

        if (!cache.containsKey(filename)) {
            var f = new File(filename);
            if (!f.exists()) {
                return true;
            }

            try {
                var e = new MekFileParser(f).getEntity();
                cache.put(filename, e);
            } catch (Exception ex) {
                logger.error("Entity loading failure:", ex);
                cache.put(filename, null);
            }
        }

        try {
            var o = encode(cache.get(filename));
            var n = encode(editor.getEntity());
            return !o.equals(n);
        } catch (EntitySavingException e) {
            logger.error("Entity encoding failure:", e);
            return true;
        }
    }

    /**
     *
     * @param editor The editor containing the unit that was just saved.
     */
    public static void editorSaved(MegaMekLabMainUI editor) {
        var filename = editor.getFileName();
        if (filename == null || filename.isBlank()) {
            return;
        }

        try {
            var bis = new ByteArrayInputStream(encode(editor.getEntity()).getBytes());
            cache.put(editor.getFileName(), new MekFileParser(bis, editor.getFileName()).getEntity());
        } catch (EntitySavingException | EntityLoadingException e) {
            cache.remove(filename);
            logger.error("Entity encoding failure:", e);
        }
    }

    private static String encode(Entity e) throws EntitySavingException {
        if (e == null) {
            return "";
        }

        if (e instanceof Mek m) {
            return m.getMtf();
        } else {
            var blk = BLKFile.getBlock(e);
            return String.join("\n", blk.getAllDataAsString());
        }
    }

    private EntityChangedUtil() {}
}
