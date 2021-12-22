package megameklab.com.ui.mek;

import megameklab.com.ui.EntitySource;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class BMSmallEquipmentDatabaseView extends BMEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_TECH, COL_TON);

    BMSmallEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(columns, true);
    }
}
