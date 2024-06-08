package megameklab.printing;

import megamek.common.*;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static java.lang.System.exit;
import static megameklab.util.UnitPrintManager.getExportFile;

public class CGLMassPrinter {

    public static void main(String[] args) {
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MechSummaryCache cache = MechSummaryCache.getInstance(true);
//        try {
//            Entity entity = new MechFileParser(new File("data/mechfiles/mechs/3039u/Atlas AS7-D.mtf")).getEntity();
//            if (entity != null) {
//                File file = new File("sheets", "atlas.pdf");
//                try {
//                    UnitPrintManager.exportUnits(Collections.singletonList(entity), file, true);
//                    System.out.println("printed ");
//                } catch (Exception e) {
//                    System.out.println("print error ");
//                }
//            } else {
//                System.out.println("null entity ");
//            }
//        } catch (Exception ex) {
//            LogManager.getLogger().error("", ex);
//        }

        int i = 0;
        for (MechSummary ms : cache.getAllMechs()) {
            if (!ms.isCanon()) {
                continue;
            }
            Entity entity = ms.loadEntity();
            if (entity != null) {
                File file = new File("sheets", sanitize(ms.getName()) + ".pdf");
                try {
                    UnitPrintManager.exportUnits(Collections.singletonList(entity), file, true);
                    System.out.println("printed " + file);
                    i++;
                } catch (Exception e) {
                    System.out.println("print error " + ms.getName());
                }
            } else {
                System.out.println("null entity " + ms.getName());
            }
            if (i>0) exit(0);
        }
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }
}
