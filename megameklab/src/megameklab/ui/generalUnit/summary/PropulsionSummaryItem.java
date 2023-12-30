package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megamek.common.Tank;
import megamek.common.verifier.TestTank;
import megameklab.util.UnitUtil;

public class PropulsionSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Lift/Dive/Rotor";
    }

    @Override
    public void refresh(Entity entity) {
        if (entity instanceof Tank) {
            TestTank testTank = (TestTank) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testTank.getTankWeightLifting()));
        }
    }
}