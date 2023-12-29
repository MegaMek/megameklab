package megameklab.ui.generalUnit.summary;

import javax.swing.*;

public class SummaryWeightLabel extends JLabel {

    public SummaryWeightLabel(String text) {
        super(text, SwingConstants.RIGHT);
        initialize();
    }

    public SummaryWeightLabel() {
        this("");
    }

    private void initialize() {
        setBorder(SummaryItem.labelBorder);
    }
}
