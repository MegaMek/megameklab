package megameklab.ui.generalUnit.summary;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.verifier.TestMech;
import megameklab.util.UnitUtil;

public class HeatsinkSummaryItem extends AbstractSummaryItem {
    @Override
    public String getName() {
        return "Heatsinks";
    }

    @Override
    public void refresh(Entity entity) {
        if (entity instanceof Mech) {
            TestMech testMech = (TestMech) UnitUtil.getEntityVerifier(entity);
            Mech mek = (Mech) entity;
            int numberSinks = UnitUtil.countActualHeatSinks(mek);
            numberSinks = Math.max(0, numberSinks - UnitUtil.getCriticalFreeHeatSinks(mek, mek.hasCompactHeatSinks()));
            int critSinks = numberSinks;
            if (UnitUtil.hasClanDoubleHeatSinks(mek)) {
                critSinks = numberSinks * 2;
            } else if (mek.hasDoubleHeatSinks()) {
                critSinks = numberSinks * 3;
            } else if (mek.hasCompactHeatSinks()) {
                critSinks = (critSinks / 2) + (critSinks % 2);
            }
            EquipmentType hsType = EquipmentType.get(getHeatSinkType(mek));
            if (hsType != null) {
                availabilityLabel.setText(hsType.getFullRatingName(mek.isClan()));
            } else {
                availabilityLabel.setText("-");
            }
            weightLabel.setText(formatWeight(testMech.getWeightHeatSinks()));
            critLabel.setText(formatCrits(critSinks));
        }
    }

    public @Nullable String getHeatSinkType(Mech mek) {
        for (Mounted m : mek.getMisc()) {
            if (m.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return m.getType().getInternalName();
            }
        }
        return null;
    }
}
