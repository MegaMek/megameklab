package megameklab.ui.handheldWeapon;

import megamek.common.AmmoType;
import megamek.common.HandheldWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.*;

import java.util.Vector;

public class HHWEquipmentView extends IView {
    private HHWEquipmentList mountList;
    private final RefreshListener refresh;

    public HHWEquipmentView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;
    }

    public synchronized void refresh() {
        if (mountList != null) {
            remove(mountList);
        }
        Vector<String> critNames = new Vector<>();
        for (var m : getEntity().getEquipment()) {
            if (m.getType() instanceof AmmoType) {
                critNames.add("%s (%d)".formatted(m.getName(), (int) m.getSize()));
            } else {
                critNames.add(m.getName());
            }
        }
        if (critNames.isEmpty()) {
            critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
        }
        mountList = new HHWEquipmentList((HandheldWeapon) getEntity(), refresh);
        add(mountList);
        validate();
    }
}
