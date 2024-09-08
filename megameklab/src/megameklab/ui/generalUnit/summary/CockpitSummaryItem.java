package megameklab.ui.generalUnit.summary;

import megamek.common.AeroSpaceFighter;
import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.verifier.TestMech;
import megameklab.util.UnitUtil;

public class CockpitSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Cockpit";
    }

    @Override
    public void refresh(Entity entity) {
        if ((entity instanceof Mek) && (((Mek) entity).getCockpitType() != Mek.COCKPIT_UNKNOWN)) {
            Mek mek = (Mek) entity;
            availabilityLabel.setText(mek.getCockpitTechAdvancement().getFullRatingName(entity.isClan()));
            TestMech testMek = (TestMech) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testMek.getWeightCockpit(), entity));
            critLabel.setText(formatCrits(getCockpitCrits((Mek) entity)));
        } else if (entity instanceof AeroSpaceFighter fighter) {
            availabilityLabel.setText(fighter.getCockpitTechAdvancement().getFullRatingName(entity.isClan()));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private int getCockpitCrits(Mek mek) {
        return (mek.getCockpitType() == Mek.COCKPIT_COMMAND_CONSOLE) ? 2 : 1;
    }
}
