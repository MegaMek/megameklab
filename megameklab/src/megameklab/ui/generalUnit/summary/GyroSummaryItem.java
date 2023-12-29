package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.verifier.TestMech;
import megameklab.util.UnitUtil;

public class GyroSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Gyro";
    }

    @Override
    public void refresh(Entity entity) {
        if ((entity instanceof Mech) && (entity.getGyroType() != Mech.GYRO_NONE)) {
            TestMech testMech = (TestMech) UnitUtil.getEntityVerifier(entity);
            availabilityLabel.setText("tbd");
            weightLabel.setText(formatWeight(testMech.getWeightGyro()));
            critLabel.setText(formatCrits(getGyroCrits(entity)));
        } else {
            availabilityLabel.setText("-");
            weightLabel.setText("-");
            critLabel.setText("-");
        }
    }

    private int getGyroCrits(Entity entity) {
        switch(entity.getGyroType()) {
            case Mech.GYRO_COMPACT:
                return 2;
            case Mech.GYRO_XL:
                return 6;
            default:
                return 4;
        }
    }
}