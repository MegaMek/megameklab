package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SummaryView extends JPanel {

    private final Dimension weightCritSize = new Dimension(55, 25);
    private final Dimension availSize = new Dimension(110, 25);
    private final Dimension categorySize = new Dimension(110, 25);

    private final List<SummaryItem> summaryItemList = new ArrayList<>();

    public SummaryView(List<SummaryItem> summaryItems) {
        initialize(summaryItems, true);
    }

    public SummaryView(List<SummaryItem> summaryItems, boolean showCrits) {
        initialize(summaryItems, showCrits);
    }

    public void initialize(List<SummaryItem> getSummaryItems, boolean showCrits) {

        summaryItemList.clear();
        summaryItemList.addAll(getSummaryItems);

        for (SummaryItem summaryItem : summaryItemList) {
            summaryItem.getWeightComponent().setPreferredSize(weightCritSize);
            summaryItem.getCritsComponent().setPreferredSize(weightCritSize);
            summaryItem.getAvailabilityComponent().setPreferredSize(availSize);
        }

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Summary"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(createLabel("Category", categorySize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(createLabel(summaryItem.getName(), categorySize, SwingConstants.RIGHT), gbc);
            gbc.gridy++;
        }

        gbc.gridx++;
        gbc.gridy = 0;
        add(createLabel("Weight", weightCritSize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(summaryItem.getWeightComponent(), gbc);
            gbc.gridy++;
        }

        if (showCrits) {
            gbc.gridx++;
            gbc.gridy = 0;
            add(createLabel("Crits", weightCritSize, SwingConstants.CENTER), gbc);
            gbc.gridy++;
            for (SummaryItem summaryItem : summaryItemList) {
                this.add(summaryItem.getCritsComponent(), gbc);
                gbc.gridy++;
            }
        }

        gbc.gridx++;
        gbc.gridy = 0;
        add(createLabel("Availability", weightCritSize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(summaryItem.getAvailabilityComponent(), gbc);
            gbc.gridy++;
        }
    }

    private JLabel createLabel(String text, Dimension size,int align){
        JLabel label = new JLabel(text + ":", SwingConstants.TRAILING);
        setFieldSize(label, size);
        label.setHorizontalAlignment(align);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize){
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh(Entity entity) {
        summaryItemList.forEach(summaryItem -> summaryItem.refresh(entity));
    }
}