/*
 * Copyright (c) 2023, 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the unit weight / crit / availability summary table on the structure tab. To construct it,
 * it needs an {@link EntitySource} as well as a list of {@link SummaryItem} that can be directly constructed
 * in the constructor call of this view.
 */
public class SummaryView extends IView {

    private final List<SummaryItem> summaryItemList = new ArrayList<>();
    private final JLabel earliestYear = new JLabel("");


    /**
     * Constructs a new summary table having the given summary items in the order they are listed.
     *
     * @param entitySource The EntitySource (cannot be null)
     * @param summaryItems The SummaryItems to show
     */
    public SummaryView(EntitySource entitySource, SummaryItem... summaryItems) {
        super(entitySource);
        initialize(true, Arrays.asList(summaryItems));
    }

    /**
     * Constructs a new summary table having the given summary items in the order they are listed.
     *
     * @param entitySource The EntitySource (cannot be null)
     * @param showCrits    When false, the crits column is hidden
     * @param summaryItems The SummaryItems to show
     */
    public SummaryView(EntitySource entitySource, boolean showCrits, SummaryItem... summaryItems) {
        super(entitySource);
        initialize(showCrits, Arrays.asList(summaryItems));
    }

    public void refresh() {
        refresh(getEntity());
    }

    // Internals ######################

    private void initialize(boolean showCrits, List<SummaryItem> getSummaryItems) {

        summaryItemList.clear();
        summaryItemList.addAll(getSummaryItems);

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Summary"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(createLabel("", SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            add(createLabel(summaryItem.getName() + ":", SwingConstants.RIGHT), gbc);
            gbc.gridy++;
        }

        gbc.gridx++;
        gbc.gridy = 0;
        add(createLabel("Weight", SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            add(summaryItem.getWeightComponent(), gbc);
            gbc.gridy++;
        }

        if (showCrits) {
            gbc.gridx++;
            gbc.gridy = 0;
            add(createLabel("Crits", SwingConstants.CENTER), gbc);
            gbc.gridy++;
            for (SummaryItem summaryItem : summaryItemList) {
                add(summaryItem.getCritsComponent(), gbc);
                gbc.gridy++;
            }
        }

        gbc.gridx++;
        gbc.gridy = 0;
        add(createLabel("Availability", SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            add(summaryItem.getAvailabilityComponent(), gbc);
            gbc.gridy++;
        }

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy++;
        add(Box.createVerticalStrut(12), gbc);

        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        add(earliestYear, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy++;
        add(Box.createVerticalStrut(12), gbc);
    }

    private JLabel createLabel(String text, int align) {
        JLabel label = new JLabel(text, SwingConstants.TRAILING);
        label.setHorizontalAlignment(align);
        return label;
    }

    private void refresh(Entity entity) {
        summaryItemList.forEach(summaryItem -> summaryItem.refresh(entity));
        entity.recalculateTechAdvancement();
        earliestYear.setText("Earliest Possible Year: " + entity.getEarliestTechDate());
    }
}