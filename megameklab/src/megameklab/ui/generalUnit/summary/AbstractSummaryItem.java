package megameklab.ui.generalUnit.summary;

import javax.swing.*;

public abstract class AbstractSummaryItem implements SummaryItem {

    protected final JLabel weightLabel = new SummaryWeightLabel("-");
    protected final JLabel critLabel = new SummaryWeightLabel("-");
    protected final JLabel availabilityLabel = new SummaryAvailabilityLabel("-");

    @Override
    public JComponent getWeightComponent() {
        return weightLabel;
    }

    @Override
    public JComponent getCritsComponent() {
        return critLabel;
    }

    @Override
    public JComponent getAvailabilityComponent() {
        return availabilityLabel;
    }
}