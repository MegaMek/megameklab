package megameklab.ui.generalUnit.summary;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megamek.common.Entity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public interface SummaryItem {

    Border outerLabelBorder = new LineBorder(UIManager.getColor("Separator.foreground"));
    Border innerLabelBorder = new EmptyBorder(0, 10, 0, 10);
    Border labelBorder = new CompoundBorder(outerLabelBorder, innerLabelBorder);

    String getName();

    JComponent getWeightComponent();

    JComponent getCritsComponent();

    JComponent getAvailabilityComponent();

    void refresh(Entity entity);

    default String formatCrits(int crits) {
        return (crits == 0) ? "-" : Integer.toString(crits);
    }

    default String formatWeight(Double weight) {
        return (weight == 0 ? "-" : CalculationReport.formatForReport(weight) + " t");
    }
}
