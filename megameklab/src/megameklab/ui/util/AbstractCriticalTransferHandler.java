package megameklab.ui.util;

import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

import javax.swing.*;

public class AbstractCriticalTransferHandler extends TransferHandler {

    protected final EntitySource eSource;
    protected RefreshListener refresh;

    public AbstractCriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        this.eSource = eSource;
        this.refresh = refresh;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    protected void changeMountStatus(Mounted eq, int location) {
        changeMountStatus(eq, location, -1);
    }

    protected void changeMountStatus(Mounted eq, int location, int secondaryLocation) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, false);
        doRefresh();
    }

    protected Entity getUnit() {
        return eSource.getEntity();
    }

    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    protected void doRefresh() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }
}
