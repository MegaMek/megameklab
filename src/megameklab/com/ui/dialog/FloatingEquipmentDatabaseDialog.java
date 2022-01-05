package megameklab.com.ui.dialog;

import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.ui.util.RefreshListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FloatingEquipmentDatabaseDialog extends AbstractMMLDialog {

    private final AbstractEquipmentDatabaseView equipmentDatabase;

    public FloatingEquipmentDatabaseDialog(JFrame frame, AbstractEquipmentDatabaseView equipmentDatabase) {
        super(frame,"FloatingEquipmentDatabaseDialog", "FloatingEquipmentDatabaseDialog.title");
        this.equipmentDatabase = equipmentDatabase;
        equipmentDatabase.setBorder(new EmptyBorder(8,8,8,8));
        equipmentDatabase.refreshTable();
        setAutoRequestFocus(false);
        setFocusable(false);
        setFocusableWindowState(false);
        initialize();
    }

    public void setRefresh(RefreshListener refresh) {
        equipmentDatabase.setRefresh(refresh);
    }

    public void refresh() {
        equipmentDatabase.refreshTable();
    }

    @Override
    protected Container createCenterPane() {
        return equipmentDatabase;
    }
}
