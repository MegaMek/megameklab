package megameklab.ui.handheldWeapon;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;

public class HHWEquipmentTab extends AbstractEquipmentTab {

    public HHWEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new HHWEquipmentDatabaseView(eSource);
    }
}
