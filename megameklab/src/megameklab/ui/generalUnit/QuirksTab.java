/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
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
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import megamek.client.ui.swing.DialogOptionComponent;
import megamek.client.ui.swing.DialogOptionListener;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.Quirks;
import megamek.common.options.WeaponQuirks;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;

public class QuirksTab extends ITab implements DialogOptionListener {

    private static final boolean SORT_QUIRKS_ALPHABETICALLY = true;
    private final Map<JPanel, List<DialogOptionComponent>> groupLayoutMap = new LinkedHashMap<>();
    private final Map<DialogOptionComponent, Dimension> originalPreferredSizes = new HashMap<>();
    RefreshListener refresh = null;
    private int globalMaxItemWidth = 0;
    private int lastCalculatedNumCols = -1;

    private static class GroupInfo {
        final String title;
        final List<DialogOptionComponent> quirks;

        GroupInfo(String title, List<DialogOptionComponent> quirks) {
            this.title = title;
            this.quirks = quirks;
        }
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
        groupLayoutMap.clear();
        originalPreferredSizes.clear();
        globalMaxItemWidth = 0;
        lastCalculatedNumCols = -1;

        // Collect all quirks
        List<DialogOptionComponent> allQuirks = new ArrayList<>();
        List<GroupInfo> groupsToDisplay = new ArrayList<>();
        collectGeneralQuirksInfo(groupsToDisplay, allQuirks);
        collectWeaponQuirksInfo(groupsToDisplay, allQuirks);

        // Calculate the maximum width of all quirks
        calculateGlobalMaxWidth(allQuirks);

        // Create all groups and quirks entries
        createAndAddGroupPanels(groupsToDisplay);

        // Relayout all columns
        SwingUtilities.invokeLater(this::triggerRelayoutCheck);

        validate();
        repaint();
    }

    /**
     * Collects general quirks
     */
    private void collectGeneralQuirksInfo(List<GroupInfo> groupsToDisplay,
            List<DialogOptionComponent> quirksList) {
        for (Enumeration<IOptionGroup> i = getEntity().getQuirks().getGroups(); i.hasMoreElements();) {
            IOptionGroup group = i.nextElement();
            List<DialogOptionComponent> quirks = collecQuirksForOptionGroup(group, quirksList);
            if (!quirks.isEmpty()) {
                groupsToDisplay.add(new GroupInfo(group.getDisplayableName(), quirks));
            }
        }
    }

    /**
     * Collect weapon quirks
     */
    private void collectWeaponQuirksInfo(List<GroupInfo> groupsToDisplay,
            List<DialogOptionComponent> quirksList) {
        List<Mounted<?>> equipmentList = new ArrayList<>(getEntity().getWeaponList());
        for (Mounted<?> miscItem : getEntity().getMisc()) {
            if (miscItem.getType().hasFlag(MiscType.F_CLUB)) {
                equipmentList.add(miscItem);
            }
        }
        for (Mounted<?> m : equipmentList) {
            List<DialogOptionComponent> quirks = collectQuirksForWeapon(m, quirksList);
            if (!quirks.isEmpty()) {
                String title = m.getName() + " (" + getEntity().getLocationName(m.getLocation()) + ")";
                groupsToDisplay.add(new GroupInfo(title, quirks));
            }
        }
    }

    /**
     * Collects and optionally sorts quirks for a general group
     */
    private List<DialogOptionComponent> collecQuirksForOptionGroup(IOptionGroup group,
            List<DialogOptionComponent> quirksList) {
        List<DialogOptionComponent> quirksInGroup = new ArrayList<>();
        for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements();) {
            IOption option = j.nextElement();
            if (null == option || Quirks.isQuirkDisallowed(option, getEntity()))
                continue;
            addQuirkInfo(option, quirksInGroup, quirksList);
        }
        if (SORT_QUIRKS_ALPHABETICALLY && !quirksInGroup.isEmpty()) {
            quirksInGroup.sort(Comparator.comparing(comp -> comp.getOption().getDisplayableName()));
        }
        return quirksInGroup;
    }

    /**
     * Collects and optionally sorts quirks for a specific weapon
     */
    private List<DialogOptionComponent> collectQuirksForWeapon(Mounted<?> m,
            List<DialogOptionComponent> allQuirksList) {
        WeaponQuirks wq = m.getQuirks();
        if (wq == null)
            return Collections.emptyList();

        List<DialogOptionComponent> weaponComps = new ArrayList<>();
        for (Enumeration<IOptionGroup> i = wq.getGroups(); i.hasMoreElements();) {
            IOptionGroup group = i.nextElement();
            for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements();) {
                IOption option = j.nextElement();
                if (WeaponQuirks.isQuirkDisallowed(option, getEntity(), m.getType()))
                    continue;
                addQuirkInfo(option, weaponComps, allQuirksList);
            }
        }
        if (SORT_QUIRKS_ALPHABETICALLY && !weaponComps.isEmpty()) {
            weaponComps.sort(Comparator.comparing(comp -> comp.getOption().getDisplayableName()));
        }
        return weaponComps;
    }

    /**
     * Updates the font style of a quirk component based on its selection state
     */
    private void updateQuirkFontStyle(DialogOptionComponent comp, boolean selected) {
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
    private void addQuirkInfo(IOption option, List<DialogOptionComponent> groupList,
            List<DialogOptionComponent> allList) {
        DialogOptionComponent comp = new DialogOptionComponent(this, option, true);
        originalPreferredSizes.put(comp, comp.getPreferredSize()); // Store for layout
        updateQuirkFontStyle(comp, option.booleanValue());
        groupList.add(comp);
        allList.add(comp);
    }

    /**
     * Calculates the maximum width of all quirks
     */
    private void calculateGlobalMaxWidth(List<DialogOptionComponent> allQuirks) {
        globalMaxItemWidth = 0;
        for (DialogOptionComponent comp : allQuirks) {
            Dimension originalSize = originalPreferredSizes.get(comp);
            globalMaxItemWidth = Math.max(globalMaxItemWidth,
                    (originalSize != null) ? originalSize.width : comp.getPreferredSize().width);
        }
        if (globalMaxItemWidth <= 0)
            globalMaxItemWidth = 150; // Fallback width
    }

    /**
     * Creates group panels and adds them to the layout.
     */
    private void createAndAddGroupPanels(List<GroupInfo> groupsToDisplay) {
        // Creates the main container
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1;
        mainGbc.weighty = 0;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.anchor = GridBagConstraints.NORTHWEST;
        mainGbc.insets = new Insets(5, 5, 0, 5);
        // Add each group panel to the main layout
        for (GroupInfo info : groupsToDisplay) {
            // Create a new group panel
            JPanel groupPanel = new JPanel(new GridBagLayout());
            groupPanel.setBorder(BorderFactory.createTitledBorder(info.title));
            add(groupPanel, mainGbc);
            groupLayoutMap.put(groupPanel, info.quirks);
            mainGbc.gridy++;
        }
        // Add a vertical spacer to push content to the top
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.BOTH;
        add(new JPanel(), mainGbc);
    }

    /**
     * Calculates the number of columns based on available width and max item width.
     */
    private int calculateNumberOfColumns(int containerWidth, int maxItemWidth) {
        if (containerWidth <= 0 || maxItemWidth <= 0)
            return 1;
        return Math.max(1, containerWidth / (maxItemWidth + 8));
    }

    /**
     * Checks if relayout is needed based on width changes (number of columns
     * drawable) and triggers it.
     */
    private void triggerRelayoutCheck() {
        if (!isShowing() || groupLayoutMap.isEmpty() || globalMaxItemWidth <= 0) {
            return;
        }

        int containerWidth = getVisibleContainerWidth();
        if (containerWidth <= 0) {
            return;
        }

        int availableWidthInPanel = calculateAvailableWidthInPanel(containerWidth);
        if (availableWidthInPanel <= 0) {
            return;
        }

        int currentNumCols = calculateNumberOfColumns(availableWidthInPanel, globalMaxItemWidth);

        // Only relayout if the number of columns needs to change
        if (currentNumCols != lastCalculatedNumCols) {
            lastCalculatedNumCols = currentNumCols;
            relayoutAllGroups(currentNumCols);
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
     * Calculates the usable width inside a group panel's content area.
     */
    private int calculateAvailableWidthInPanel(int containerWidth) {
        Insets tabInsets = getInsets(); // Insets of the QuirksTab
        int availableWidthForPanels = containerWidth - tabInsets.left - tabInsets.right - (5 + 5);
        JPanel firstPanel = groupLayoutMap.keySet().iterator().next();
        Insets panelInsets = firstPanel.getInsets();
        return availableWidthForPanels - panelInsets.left - panelInsets.right;
    }

    /**
     * Relayouts all group panels based on the calculated number of columns.
     */
    private void relayoutAllGroups(int numCols) {
        for (Map.Entry<JPanel, List<DialogOptionComponent>> entry : groupLayoutMap.entrySet()) {
            relayoutGroupPanel(entry.getKey(), entry.getValue(), numCols);
        }
        revalidate();
        repaint();
    }

    /**
     * Arranges quirks within a single group panel and makes them fixed-width
     * from globalMaxItemWidth
     */
    private void relayoutGroupPanel(JPanel groupPanel, List<DialogOptionComponent> quirks, int numCols) {
        groupPanel.removeAll();
        if (quirks.isEmpty() || numCols <= 0) {
            groupPanel.revalidate();
            groupPanel.repaint();
            return;
        }

        int totalItems = quirks.size();
        int itemsPerCol = (int) Math.ceil((double) totalItems / numCols);
        if (itemsPerCol <= 0)
            itemsPerCol = 1;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(1, 4, 1, 4);
        gbc.weighty = 0;
        gbc.weightx = 1; // We auto-space them horizontally (if we put 0 they stay aligned to the left)
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add quirks to the group panel in a grid layout
        for (int i = 0; i < totalItems; i++) {
            final int colIndex = i % itemsPerCol;
            gbc.gridx = i / itemsPerCol;
            gbc.gridy = colIndex;

            DialogOptionComponent comp = quirks.get(i);
            // Set preferred width so we match the width of the widest item
            Dimension originalSize = originalPreferredSizes.get(comp);
            int currentHeight = (originalSize != null) ? originalSize.height : comp.getPreferredSize().height;
            comp.setPreferredSize(new Dimension(globalMaxItemWidth, currentHeight));

            groupPanel.add(comp, gbc);
        }

        // Because we are auto-spacing them horizontally (bgc.weightx = 1 above), we
        // create fake columns in case
        // this group doesn't have enough (usually weapons)
        final int lastColUsed = totalItems % itemsPerCol;
        for (int i = lastColUsed + 1; i < numCols; i++) {
            gbc.gridx = numCols - 1;
            gbc.gridy = i;
            groupPanel.add(Box.createHorizontalStrut(globalMaxItemWidth), gbc);
        }

        // Fill space to the right of the last column (forces everything aligned left)
        // We need this if we don't do the trick above with the fake columns and
        // auto-spacing
        // gbc.gridx = numCols;
        // gbc.gridy = 0;
        // gbc.weightx = 1;
        // gbc.gridheight = itemsPerCol + 1;
        // groupPanel.add(Box.createHorizontalGlue(), gbc);
        // gbc.gridheight = 1;

        groupPanel.revalidate();
        groupPanel.repaint();
    }

    @Override
    public void optionClicked(DialogOptionComponent comp, IOption option, boolean state) {
        option.setValue(state);
        updateQuirkFontStyle(comp, state);
        refresh.refreshPreview();
        refresh.requestDirtyCheck();
    }

    @Override
    public void optionSwitched(DialogOptionComponent comp, IOption option, int i) {
    }

    public ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent e) {
            refreshQuirks();
        }
    };
}