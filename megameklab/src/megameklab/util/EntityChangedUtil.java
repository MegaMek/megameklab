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

public class EntityChangedUtil {
    private static final MMLogger logger = MMLogger.create(EntityChangedUtil.class);

    private static final Map<String, Entity> cache = new ConcurrentHashMap<>();

    public static boolean hasEntityChanged(MegaMekLabMainUI editor) {
        var filename = editor.getFileName();
        if (filename == null || filename.isBlank()) {
            return true;
        }


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
