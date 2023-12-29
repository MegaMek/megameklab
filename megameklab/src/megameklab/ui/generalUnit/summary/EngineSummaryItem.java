package megameklab.ui.generalUnit.summary;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.verifier.TestEntity;
import megameklab.util.UnitUtil;

public class EngineSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Engine";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
        if (entity.hasEngine()) {
            availabilityLabel.setText(entity.getEngine().getFullRatingName(entity.isClan()));
            weightLabel.setText(formatWeight(testEntity.getWeightEngine()));
            critLabel.setText(formatCrits(getEngineCrits(entity)));
        } else {
            availabilityLabel.setText("-");
            weightLabel.setText("-");
            critLabel.setText("-");
        }
    }

    private int getEngineCrits(Entity entity) {
        if (entity.getEngine().getEngineType() == Engine.COMPACT_ENGINE) {
            return 3;
        }
        return 6 + (2 * entity.getEngine().getSideTorsoCriticalSlots().length);
    }
}