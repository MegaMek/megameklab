package megameklab.com.ui.battleArmor;

import megameklab.com.ui.EntitySource;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class BAFloatingEquipmentDatabaseView extends BAEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_TECH, COL_TON, COL_CRIT);

    public BAFloatingEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(columns, true);
    }

    @Override
    protected boolean useSwitchTableColumns() {
        return false;
    }
}