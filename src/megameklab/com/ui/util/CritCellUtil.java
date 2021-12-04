package megameklab.com.ui.util;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megameklab.com.util.CConfig;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/** Contains constants and utils for a unified crit cell display across unit types. */
public final class CritCellUtil {

    /** The base width of Crit Cells across units with 3 columns of crit lists */
    public static final int CRITCELL_WIDTH = 250;

    /** The width of Crit Cells for Meks; their layout has 5 columns */
    public static final int CRITCELL_MEKWIDTH = (int) (0.6 * CRITCELL_WIDTH);

    /** The width of Crit Cells for Vehicles; their layout has 4 columns */
    public static final int CRITCELL_VEHWIDTH = (int) (0.8 * CRITCELL_WIDTH);

    public static final int CRITCELL_ADDHEIGHT = 5;
    public static final int CRITCELL_MINHEIGHT = 25;
    public static final Color CRITCELL_BORDERCOLOR = Color.BLACK;
    public static final String EMPTYCELLTEXT = "- Empty -";

    public static Border locationBorder(String title) {
        return BorderFactory.createTitledBorder(
                new LocationBorder(CRITCELL_BORDERCOLOR, 2f),
                " " + title + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION);
    }

    public static Border locationBorderNoLine(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                " " + title + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION);
    }

    public static void formatCell(JLabel cell, @Nullable Mounted mounted, boolean useColor,
                                  Entity entity, int index) {
        if (useColor) {
            if (mounted == null) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_EMPTY));
                cell.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_EMPTY));
            } else if (mounted.getType() instanceof WeaponType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS));
                cell.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_WEAPONS));
            } else if (mounted.getType() instanceof AmmoType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
                cell.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_AMMO));
            } else {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_EQUIPMENT));
                cell.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_EQUIPMENT));
            }
        }

        if (mounted == null) {
            cell.setText(" " + EMPTYCELLTEXT);
            cell.setToolTipText(null);
        } else {
            String name = UnitUtil.getCritName(entity, mounted.getType());
            name += mounted.isRearMounted() ? " (R)" : "";
            name += mounted.isArmored() ? " (A)" : "";
            name += mounted.isMechTurretMounted() ? " (T)" : "";
            name += mounted.isSponsonTurretMounted() ? " (ST)" : "";
            name += mounted.isPintleTurretMounted() ? " (PT)" : "";
            name += mounted.isDWPMounted() ? " (DWP)" : "";
            if (mounted.getType() instanceof AmmoType) {
                name = mounted.getBaseShotsLeft() + " shot" + (mounted.getBaseShotsLeft() > 1 ? "s " : " ") + name;
            }
            if (entity.isOmni() && !mounted.getType().isOmniFixedOnly()) {
                if (mounted.isOmniPodMounted()) {
                    name += " (Pod)";
                } else {
                    name += " (Fixed)";
                    cell.setFont(cell.getFont().deriveFont(Font.ITALIC));
                }
            }
            if ((mounted.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    || mounted.getType().hasFlag(MiscType.F_AP_MOUNT))
                    && mounted.getLinked() != null) {
                name += " (attached " + mounted.getLinked().getName() + ")";
            }

            cell.setText(" " + name);

            String toolTipText = UnitUtil.getToolTipInfo(entity, mounted);
            // distinguish tooltips of equal adjacent one-slot equipment (e.g. ammo) to make the tip renew itself
            // when crossing from one such slot to the next (avoids them feeling like a single equipment)
            if (mounted.getCriticals() == 1) {
                toolTipText += " ".repeat(index);
            }
            cell.setToolTipText(toolTipText);
        }
    }

}
