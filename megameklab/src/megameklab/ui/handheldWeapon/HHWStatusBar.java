package megameklab.ui.handheldWeapon;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.AmmoType;
import megamek.common.MiscType;
import megamek.common.equipment.WeaponMounted;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;
import java.util.Iterator;

public class HHWStatusBar extends StatusBar {
    private static final String SLOTS_LABEL = "Free Slots: %d / %d";
    private final JLabel slotsLabel = new JLabel();

    public HHWStatusBar(HHWMainUI parent) {
        super(parent);
        add(slotsLabel);
    }

    @Override
    protected void additionalRefresh() {
        refreshSlots();
    }

    public void refreshSlots() {
        var maxSlots = 6;
        if (!getEntity().getMiscEquipment(MiscType.F_CLUB).isEmpty()) {
            maxSlots = 1;
        }
        var curSlots = getEntity().getEquipment().stream()
            .filter(m -> !(m.getType() instanceof AmmoType) && !m.getType().hasFlag(MiscType.F_WEAPON_ENHANCEMENT))
            .count();
        slotsLabel.setText(SLOTS_LABEL.formatted(maxSlots - curSlots, maxSlots));
        slotsLabel.setForeground(curSlots > maxSlots ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
