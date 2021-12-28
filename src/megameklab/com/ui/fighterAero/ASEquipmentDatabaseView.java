package megameklab.com.ui.fighterAero;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class ASEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_REF);

    ASEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        if ((equip instanceof MiscType) && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getAero())) {
                UnitUtil.updateTC(getAero(), equip);
            }
        } else {
            try {
                Mounted mount = new Mounted(getAero(), equip);
                getAero().addEquipment(mount, Aero.LOC_FUSELAGE, false);
                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
                }
            } catch (LocationFullException lfe) {
                // Shouldn't happen when adding to LOC_FUSELAGE
            }
        }
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }

}
