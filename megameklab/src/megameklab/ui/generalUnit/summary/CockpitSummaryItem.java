package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.verifier.TestMech;
import megameklab.util.UnitUtil;

public class CockpitSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Cockpit";
    }

    @Override
    public void refresh(Entity entity) {
        if ((entity instanceof Mech) && (((Mech) entity).getCockpitType() != Mech.COCKPIT_UNKNOWN)) {
            TestMech testMech = (TestMech) UnitUtil.getEntityVerifier(entity);
            availabilityLabel.setText("");
            weightLabel.setText(formatWeight(testMech.getWeightCockpit(), entity));
            critLabel.setText(formatCrits(getCockpitCrits((Mech) entity)));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private int getCockpitCrits(Mech mek) {
        return (mek.getCockpitType() == Mech.COCKPIT_COMMAND_CONSOLE) ? 2 : 1;
    }
}
