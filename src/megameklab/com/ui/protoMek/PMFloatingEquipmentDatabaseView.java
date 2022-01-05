package megameklab.com.ui.protoMek;

import megameklab.com.ui.EntitySource;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class PMFloatingEquipmentDatabaseView extends PMEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_TECH, COL_TON);

    public PMFloatingEquipmentDatabaseView(EntitySource eSource) {
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