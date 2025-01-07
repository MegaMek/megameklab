package megameklab;

import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.MekFileParser;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.EntitySavingException;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityChangedUtil {
    private static MMLogger logger = MMLogger.create(EntityChangedUtil.class);

    private static Map<String, String> cache = new ConcurrentHashMap<>();

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
                cache.put(filename, encode(e));
            } catch (Exception ex) {
                logger.error("Entity loading failure:", ex);
            }
        }

        try {
            var o = cache.get(filename);
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
            cache.put(editor.getFileName(), encode(editor.getEntity()));
        } catch (EntitySavingException e) {
            logger.error("Entity encoding failure:", e);
        }
    }

    private static String encode(Entity e) throws EntitySavingException {
        if (e instanceof Mek m) {
            return m.getMtf();
        } else {
            var blk = BLKFile.getBlock(e);
            return String.join("\n", blk.getAllDataAsString());
        }
    }

    private EntityChangedUtil() {}
}
