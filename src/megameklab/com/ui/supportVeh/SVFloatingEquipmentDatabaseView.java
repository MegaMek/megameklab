package megameklab.com.ui.supportVeh;

import megameklab.com.ui.EntitySource;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class SVFloatingEquipmentDatabaseView extends SVEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_TECH, COL_TON);

    public SVFloatingEquipmentDatabaseView(EntitySource eSource) {
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
