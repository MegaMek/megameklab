package megameklab.ui.generalUnit.summary;

import javax.swing.*;

public class SummaryAvailabilityLabel extends JLabel {

    public SummaryAvailabilityLabel(String text) {
        super(text, SwingConstants.LEFT);
        initialize();
    }

    public SummaryAvailabilityLabel() {
        this("");
    }

    private void initialize() {
        setBorder(SummaryItem.labelBorder);
    }
}