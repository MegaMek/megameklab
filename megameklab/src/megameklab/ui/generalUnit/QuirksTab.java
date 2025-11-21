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
package megameklab.ui.generalUnit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;

import megamek.client.ui.clientGUI.DialogOptionListener;
import megamek.client.ui.panels.DialogOptionComponentYPanel;
import megamek.client.ui.util.VerticalGridLayout;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.Quirks;
import megamek.common.options.WeaponQuirks;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;

public class QuirksTab extends ITab implements DialogOptionListener {

    private static final boolean SORT_QUIRKS_ALPHABETICALLY = true;
    private final Map<JPanel, List<DialogOptionComponentYPanel>> panelQuirksMap = new LinkedHashMap<>();
    private final Map<DialogOptionComponentYPanel, Dimension> originalPreferredSizes = new HashMap<>();
    private final Map<JPanel, Integer> panelLastCalculatedCols = new HashMap<>();
    RefreshListener refresh = null;
    private int globalMaxItemWidth = 0;

    private JPanel positiveQuirksPanel;
    private JPanel negativeQuirksPanel;
    private JPanel weaponQuirksPanel;

    private record GroupInfo(String title, List<DialogOptionComponentYPanel> quirks) {
    }

    public QuirksTab(EntitySource eSource) {
        super(eSource);
        setLayout(new GridBagLayout());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                triggerRelayoutCheck();
            }
        });
        refreshQuirks();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    /**
     * Refreshes the quirks tab
     */
    public void refresh() {
        refreshQuirks();
    }

    /**
     * Reloads and displays all relevant quirks for the current entity.
     */
    private void refreshQuirks() {
        // Cleanup
        removeAll();
        panelQuirksMap.clear();
        originalPreferredSizes.clear();
        panelLastCalculatedCols.clear();
        globalMaxItemWidth = 0;

        // Collect all quirks into lists
        List<DialogOptionComponentYPanel> allQuirks = new ArrayList<>();
        List<DialogOptionComponentYPanel> positiveQuirksList = new ArrayList<>();
        List<DialogOptionComponentYPanel> negativeQuirksList = new ArrayList<>();

        // Create positive and negative quirks panels
        positiveQuirksPanel = new JPanel(new GridBagLayout());
        positiveQuirksPanel.setBorder(BorderFactory.createTitledBorder("Chassis Quirks (Positive)"));
        negativeQuirksPanel = new JPanel(new GridBagLayout());
        negativeQuirksPanel.setBorder(BorderFactory.createTitledBorder("Chassis Quirks (Negative)"));

        // Collect general (chassis) quirks and separate into positive/negative
        collectGeneralQuirks(positiveQuirksList, negativeQuirksList, allQuirks);

        // Sort quirks alphabetically if enabled
        if (SORT_QUIRKS_ALPHABETICALLY) {
            positiveQuirksList.sort(Comparator.comparing(comp -> comp.getOption().getDisplayableName()));
            negativeQuirksList.sort(Comparator.comparing(comp -> comp.getOption().getDisplayableName()));
        }

        // Create main weapon quirks container panel
        JPanel weaponQuirksContainer = new JPanel(new GridBagLayout());
        weaponQuirksContainer.setBorder(BorderFactory.createTitledBorder("Weapon Quirks"));

        // Collect weapon quirks as titled groups
        collectWeaponQuirks(weaponQuirksContainer, allQuirks);

        // Calculate global max width across ALL quirks
        calculateGlobalMaxWidth(allQuirks);

        // Initially populate chassis quirk panels with single column (no responsive layout)
        relayoutPanel(positiveQuirksPanel, positiveQuirksList, 1);
        relayoutPanel(negativeQuirksPanel, negativeQuirksList, 1);

        // Note: Chassis quirk panels are NOT added to panelQuirksMap, so they stay single-column
        // Only weapon panels get responsive multi-column layout

        // Wrap panels in scroll panes
        JScrollPane positiveScrollPane = new JScrollPane(positiveQuirksPanel);
        positiveScrollPane.setBorder(null);
        JScrollPane negativeScrollPane = new JScrollPane(negativeQuirksPanel);
        negativeScrollPane.setBorder(null);
        JScrollPane weaponScrollPane = new JScrollPane(weaponQuirksContainer);
        weaponScrollPane.setBorder(null);

        // Create nested split panes for three-way horizontal split
        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            positiveScrollPane, negativeScrollPane);
        leftSplitPane.setResizeWeight(0.5);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            leftSplitPane, weaponScrollPane);
        mainSplitPane.setResizeWeight(0.67);

        // Add the split pane to the main panel
        setLayout(new GridBagLayout());
        add(mainSplitPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        validate();
        repaint();

        // Set divider locations and trigger layout after component is shown
        if (isShowing()) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                leftSplitPane.setDividerLocation(0.5);
                mainSplitPane.setDividerLocation(0.67);
                // Small delay to ensure dividers are positioned before responsive layout
                javax.swing.SwingUtilities.invokeLater(this::triggerRelayoutCheck);
            });
        } else {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    leftSplitPane.setDividerLocation(0.5);
                    mainSplitPane.setDividerLocation(0.67);
                    // Small delay to ensure dividers are positioned before responsive layout
                    javax.swing.SwingUtilities.invokeLater(() -> triggerRelayoutCheck());
                    removeComponentListener(this);
                }
            });
        }
    }

    /**
     * Collects general (chassis) quirks and separates into positive/negative lists.
     */
    private void collectGeneralQuirks(List<DialogOptionComponentYPanel> positiveList,
          List<DialogOptionComponentYPanel> negativeList,
          List<DialogOptionComponentYPanel> allQuirks) {
        for (Enumeration<IOptionGroup> i = getEntity().getQuirks().getGroups(); i.hasMoreElements(); ) {
            IOptionGroup group = i.nextElement();
            boolean isPositive = Quirks.POS_QUIRKS.equals(group.getKey());
            boolean isNegative = Quirks.NEG_QUIRKS.equals(group.getKey());

            if (isPositive || isNegative) {
                List<DialogOptionComponentYPanel> targetList = isPositive ? positiveList : negativeList;

                for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements(); ) {
                    IOption option = j.nextElement();

                    if (null == option || Quirks.isQuirkDisallowed(option, getEntity())) {
                        continue;
                    }

                    addQuirkInfo(option, targetList, allQuirks);
                }
            }
        }
    }

    /**
     * Collects weapon quirks as titled groups within the weapon container.
     */
    private void collectWeaponQuirks(JPanel weaponContainer, List<DialogOptionComponentYPanel> allQuirks) {
        List<Mounted<?>> equipmentList = new ArrayList<>(getEntity().getWeaponList());
        for (Mounted<?> miscItem : getEntity().getMisc()) {
            if (miscItem.getType().hasFlag(MiscType.F_CLUB)) {
                equipmentList.add(miscItem);
            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, 2, 2, 2);

        for (Mounted<?> m : equipmentList) {
            List<DialogOptionComponentYPanel> weaponQuirksList = collectQuirksForWeapon(m, allQuirks);

            // Sort weapon quirks if enabled
            if (SORT_QUIRKS_ALPHABETICALLY && !weaponQuirksList.isEmpty()) {
                weaponQuirksList.sort(Comparator.comparing(comp -> comp.getOption().getDisplayableName()));
            }

            // Create a titled panel for this weapon if it has quirks
            if (!weaponQuirksList.isEmpty()) {
                JPanel weaponPanel = new JPanel(new GridBagLayout());
                String weaponTitle = m.getName() + " (" + getEntity().getLocationName(m.getLocation()) + ")";
                weaponPanel.setBorder(BorderFactory.createTitledBorder(weaponTitle));

                // Initially populate with single-column layout
                relayoutPanel(weaponPanel, weaponQuirksList, 1);

                weaponContainer.add(weaponPanel, gbc);
                gbc.gridy++;

                // Track this weapon panel for responsive layout
                panelQuirksMap.put(weaponPanel, weaponQuirksList);
            }
        }

        // Add vertical glue to push weapon groups to the top
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        weaponContainer.add(new JPanel(), gbc);
    }

    /**
     * Collects quirks for a specific weapon.
     */
    private List<DialogOptionComponentYPanel> collectQuirksForWeapon(Mounted<?> m,
          List<DialogOptionComponentYPanel> allQuirksList) {
        WeaponQuirks wq = m.getQuirks();
        if (wq == null) {
            return Collections.emptyList();
        }

        List<DialogOptionComponentYPanel> weaponComps = new ArrayList<>();
        for (Enumeration<IOptionGroup> i = wq.getGroups(); i.hasMoreElements(); ) {
            IOptionGroup group = i.nextElement();
            for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements(); ) {
                IOption option = j.nextElement();
                if (WeaponQuirks.isQuirkDisallowed(option, getEntity(), m.getType())) {
                    continue;
                }
                addQuirkInfo(option, weaponComps, allQuirksList);
            }
        }
        return weaponComps;
    }

    /**
     * Updates the font style of a quirk component based on its selection state
     */
    private void updateQuirkFontStyle(DialogOptionComponentYPanel comp, boolean selected) {
        for (Component child : comp.getComponents()) {
            Font currentFont = child.getFont();
            if (currentFont != null) {
                if (selected) {
                    child.setForeground(Color.YELLOW);
                } else {
                    child.setForeground(null);
                }
            }
        }
        comp.invalidate();
        comp.repaint();
    }

    /**
     * Creates a quirk entry, stores its info, and adds it to lists.
     */
    private void addQuirkInfo(IOption option, List<DialogOptionComponentYPanel> groupList,
          List<DialogOptionComponentYPanel> allList) {
        DialogOptionComponentYPanel comp = new DialogOptionComponentYPanel(this, option, true);
        originalPreferredSizes.put(comp, comp.getPreferredSize()); // Store for layout
        updateQuirkFontStyle(comp, option.booleanValue());
        groupList.add(comp);
        allList.add(comp);
    }

    /**
     * Calculates the maximum width of all quirks
     */
    private void calculateGlobalMaxWidth(List<DialogOptionComponentYPanel> allQuirks) {
        globalMaxItemWidth = 0;
        for (DialogOptionComponentYPanel comp : allQuirks) {
            Dimension originalSize = originalPreferredSizes.get(comp);
            globalMaxItemWidth = Math.max(globalMaxItemWidth,
                  (originalSize != null) ? originalSize.width : comp.getPreferredSize().width);
        }
        if (globalMaxItemWidth <= 0) {
            globalMaxItemWidth = 150; // Fallback width
        }
    }

    /**
     * Calculates the number of columns based on available width and max item width.
     */
    private int calculateNumberOfColumns(int containerWidth, int maxItemWidth) {
        if (containerWidth <= 0 || maxItemWidth <= 0) {return 1;}
        return Math.max(1, containerWidth / (maxItemWidth + 8));
    }

    /**
     * Checks if relayout is needed based on width changes and triggers it for each panel.
     */
    private void triggerRelayoutCheck() {
        if (!isShowing() || panelQuirksMap.isEmpty() || globalMaxItemWidth <= 0) {
            return;
        }

        for (Map.Entry<JPanel, List<DialogOptionComponentYPanel>> entry : panelQuirksMap.entrySet()) {
            JPanel panel = entry.getKey();
            List<DialogOptionComponentYPanel> quirks = entry.getValue();

            int availableWidth = calculateAvailableWidthInPanel(panel);
            if (availableWidth <= 0) {
                continue;
            }

            int currentNumCols = calculateNumberOfColumns(availableWidth, globalMaxItemWidth);
            Integer lastNumCols = panelLastCalculatedCols.get(panel);

            // Only relayout if the number of columns needs to change
            if (lastNumCols == null || currentNumCols != lastNumCols) {
                panelLastCalculatedCols.put(panel, currentNumCols);
                relayoutPanel(panel, quirks, currentNumCols);
            }
        }
    }

    /**
     * Gets the width of the visible area (viewport or panel itself).
     */
    private int getVisibleContainerWidth() {
        Container parent = getParent();
        if (parent instanceof JViewport) {
            return parent.getWidth(); // Use viewport width
        } else {
            return getWidth(); // Fallback
        }
    }

    /**
     * Calculates the usable width inside a panel's content area.
     */
    private int calculateAvailableWidthInPanel(JPanel panel) {
        Container scrollPaneParent = panel.getParent();
        if (scrollPaneParent instanceof JViewport) {
            JViewport viewport = (JViewport) scrollPaneParent;
            int viewportWidth = viewport.getWidth();
            Insets panelInsets = panel.getInsets();
            return viewportWidth - panelInsets.left - panelInsets.right;
        }
        return panel.getWidth() - panel.getInsets().left - panel.getInsets().right;
    }

    /**
     * Arranges quirks within a panel using GridBagLayout for natural component heights.
     */
    private void relayoutPanel(JPanel panel, List<DialogOptionComponentYPanel> quirks, int numCols) {
        panel.removeAll();
        if (!quirks.isEmpty() && (numCols > 0)) {
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(0, 2, 0, 2);

            int currentCol = 0;
            for (DialogOptionComponentYPanel quirk : quirks) {
                gbc.gridx = currentCol;
                panel.add(quirk, gbc);

                currentCol++;
                if (currentCol >= numCols) {
                    currentCol = 0;
                    gbc.gridy++;
                }
            }

            // Add vertical glue to push content to top
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = numCols;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            panel.add(new JPanel(), gbc);
        }
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void optionClicked(DialogOptionComponentYPanel comp, IOption option, boolean state) {
        option.setValue(state);
        updateQuirkFontStyle(comp, state);
        if (refresh != null) {
            refresh.refreshPreview();
        }
    }

    @Override
    public void optionSwitched(DialogOptionComponentYPanel comp, IOption option, int i) {
    }

    public ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent e) {
            refreshQuirks();
        }
    };
}
