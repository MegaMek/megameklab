package megameklab.ui.handheldWeapon;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.HandheldWeapon;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.util.*;

import javax.swing.*;
import javax.swing.event.ListDataListener;
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
                critNames.add("%s (%d)".formatted(m.getName(), m.getOriginalShots()));
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
