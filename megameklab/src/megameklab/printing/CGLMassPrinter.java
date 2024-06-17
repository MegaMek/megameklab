package megameklab.printing;

import megamek.common.*;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class CGLMassPrinter {

    public static void main(String[] args) throws InterruptedException {
        File sheetsDir = new File("sheets");
        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            System.err.println("Error: sheets directory does not exist or is not a directory");
            System.exit(1);
        }
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MechSummaryCache cache = MechSummaryCache.getInstance(true);

        for (MechSummary ms : cache.getAllMechs()) {
            if (!ms.isCanon()) {
                continue;
            }


            // 1 - uncomment this block and cycle all the start characters A-Z (only uppercase)
            if (!ms.getName().toUpperCase().startsWith("Y")) {
                continue;
            }

            // 2 - uncomment this block, comment the above block, run once more
//            if (ms.getName().toUpperCase().charAt(0) <= 'Z' && ms.getName().toUpperCase().charAt(0) >= 'A') {
//                continue;
//            }

            Entity entity = ms.loadEntity();
            if (entity != null && !(entity instanceof GunEmplacement)) {
                File file = new File("sheets", ms.getMulId() + "_"
                        + sanitize(ms.getChassis()) + "_"
                        + sanitize(ms.getModel()) + ".pdf");
                try {
                    UnitPrintManager.exportUnits(List.of(entity), file, true);
                    System.out.println("printed " + file);
                } catch (Exception e) {
                    System.out.println("print error ");
                }
                Thread.sleep(2000);
            }
        }

        Thread.sleep(10000);
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }
}
