package megameklab.com.ui.fighterAero;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class ASEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

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
                int location = (equip instanceof AmmoType) ? Aero.LOC_FUSELAGE : Aero.LOC_NONE;
                getAero().addEquipment(mount, location, false);
                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
                    UnitUtil.removeOneShotAmmo(getAero());
                }
            } catch (LocationFullException ignored) {
                // location maximum is currently checked in menus and dragndrop
            }
        }
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }

}
