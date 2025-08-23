/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit.summary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import megamek.common.units.Entity;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;

/**
 * This class represents the unit weight / crit / availability summary table on the structure tab. To construct it, it
 * needs an {@link EntitySource} as well as a list of {@link SummaryItem} that can be directly constructed in the
 * constructor call of this view.
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
