package megameklab.ui.util;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/** Contains constants and utils for a unified crit cell display across unit types. */
public final class CritCellUtil {

    /** The base width of Crit Cells across units with 3 columns of crit lists */
    public static final int CRITCELL_WIDTH = 250;

    /** The width of Crit Cells for Meks; their layout has 5 columns */
    public static final int CRITCELL_MEK_WIDTH = (int) (0.6 * CRITCELL_WIDTH);

    /** The width of Crit Cells for Vehicles; their layout has 4 columns */
    public static final int CRITCELL_VEH_WIDTH = (int) (0.8 * CRITCELL_WIDTH);

    /** The height added to the text height of Crit Cells (padding) */
    public static final int CRITCELL_ADD_HEIGHT = 5;

    /** The minimum height of Crit Cells (safeguard for empty blocks) */
    public static final int CRITCELL_MIN_HEIGHT = 25;
    public static final Color CRITCELL_BORDER_COLOR = Color.BLACK;
    public static final String EMPTY_CRITCELL_TEXT = "- Empty -";

    /**
     * @param title the title for this component
     * @return a titled border using the given string as title placed centered atop the
     * Component and using a {@link LocationBorder} as a border. To be used for crit
     * location blocks, especially when they have additional information ("Slots: 0/2")
     * above or below the crits to group them visually.
     */
    public static Border locationBorder(String title) {
        return BorderFactory.createTitledBorder(
                new LocationBorder(CRITCELL_BORDER_COLOR, 2f),
                " " + title + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION);
    }

    /**
     * @param title the title for this Component
     * @return a titled but otherwise empty border using the given string as the title placed
     * centered atop the Component.
     */
    public static Border locationBorderNoLine(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                " " + title + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION);
    }

    /**
     * Applies crit cell formatting to the given JLabel cell, which is assumed to display
     * the given mounted in the given entity at the given crit cell index.
     * The JLabel cell should be a ListCellRenderer or TreeCellRenderer return value.
     */
    public static void formatCell(JLabel cell, @Nullable Mounted mounted, boolean useColor,
                                  Entity entity, int index) {
        if (useColor) {
            if (mounted == null) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_EMPTY));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_EMPTY));
            } else if (!mounted.getType().isHittable()) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_NONHITTABLE));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_NONHITTABLE));
            } else if (mounted.getType() instanceof WeaponType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_WEAPONS));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_WEAPONS));
            } else if (mounted.getType() instanceof AmmoType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_AMMO));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_AMMO));
            } else {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_EQUIPMENT));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_EQUIPMENT));
            }
        }

        if (mounted == null) {
            cell.setText(" " + EMPTY_CRITCELL_TEXT);
            cell.setToolTipText(null);
        } else {
            String name = UnitUtil.getCritName(entity, mounted.getType());
            name += mounted.isRearMounted() ? " (R)" : "";
            name += mounted.isArmored() ? " (A)" : "";
            name += mounted.isMechTurretMounted() ? " (T)" : "";
            name += mounted.isSponsonTurretMounted() ? " (ST)" : "";
            name += mounted.isPintleTurretMounted() ? " (PT)" : "";
            name += mounted.isDWPMounted() ? " (DWP)" : "";
            if ((mounted.getType() instanceof AmmoType) && !mounted.is(EquipmentTypeLookup.COOLANT_POD)) {
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

            String toolTipText = EquipmentToolTip.getToolTipInfo(entity, mounted);
            // distinguish tooltips of equal adjacent one-slot equipment (e.g. ammo) to make the tip renew itself
            // when crossing from one such slot to the next (avoids them feeling like a single equipment)
            if (mounted.getCriticals() == 1) {
                toolTipText += " ".repeat(index);
            }
            cell.setToolTipText(toolTipText);
        }
    }

}
