/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.mek;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import megamek.common.CriticalSlot;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Mek;
import megamek.common.units.TripodMek;
import megameklab.ui.EntitySource;
import megameklab.ui.util.BAASBMDropTargetCriticalList;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

/**
 * The Crit Slots view for a Mek (including Quad and Tripod)
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author Simon (Juliez)
 */
public class BMCriticalView extends IView implements ActionListener {

    private static final String CASE_NONE_LABEL = "None";

    /** All CASE-family equipment types, built once from F_CASE / F_CASEII / F_CASEP flags. */
    private static List<EquipmentType> allCaseTypes;

    private final JPanel laPanel = new JPanel();
    private final JPanel raPanel = new JPanel();
    private final JPanel llPanel = new JPanel();
    private final JPanel rlPanel = new JPanel();
    private final JPanel clPanel = new JPanel();
    private final JPanel ltPanel = new JPanel();
    private final JPanel rtPanel = new JPanel();
    private final JPanel ctPanel = new JPanel();
    private final JPanel hdPanel = new JPanel();
    private RefreshListener refresh;

    private final Map<Integer, JComponent> mekPanels = Map.of(Mek.LOC_HEAD,
          hdPanel,
          Mek.LOC_LEFT_ARM,
          laPanel,
          Mek.LOC_RIGHT_ARM,
          raPanel,
          Mek.LOC_CENTER_TORSO,
          ctPanel,
          Mek.LOC_LEFT_TORSO,
          ltPanel,
          Mek.LOC_RIGHT_TORSO,
          rtPanel,
          Mek.LOC_LEFT_LEG,
          llPanel,
          Mek.LOC_RIGHT_LEG,
          rlPanel,
          Mek.LOC_CENTER_LEG,
          clPanel);

    private final List<BAASBMDropTargetCriticalList<String>> currentCritBlocks = new ArrayList<>();

    /** Per-location CASE dropdown panels (label + combo) */
    private final Map<Integer, JPanel> casePanels = new HashMap<>();
    /** Per-location CASE combo boxes storing EquipmentType (null = None) */
    private final Map<Integer, JComboBox<EquipmentType>> caseComboBoxes = new HashMap<>();
    /** Flag to suppress combo ActionEvents during programmatic updates */
    private boolean updatingCaseCombos = false;

    public BMCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        // Create CASE combo boxes for each location
        for (int loc : new int[] { Mek.LOC_HEAD, Mek.LOC_LEFT_ARM, Mek.LOC_RIGHT_ARM,
              Mek.LOC_CENTER_TORSO, Mek.LOC_LEFT_TORSO, Mek.LOC_RIGHT_TORSO,
              Mek.LOC_LEFT_LEG, Mek.LOC_RIGHT_LEG, Mek.LOC_CENTER_LEG }) {
            JComboBox<EquipmentType> combo = new JComboBox<>();
            combo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value,
                      int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    setText(value instanceof EquipmentType et ? UnitUtil.getCritName(getMek(), et) : CASE_NONE_LABEL);
                    return this;
                }
            });
            combo.setActionCommand(String.valueOf(loc));
            combo.addActionListener(this);
            caseComboBoxes.put(loc, combo);
            JPanel casePanel = new JPanel();
            casePanel.setLayout(new BoxLayout(casePanel, BoxLayout.X_AXIS));
            casePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            casePanel.add(combo);
            casePanel.setVisible(false);
            casePanels.put(loc, casePanel);
        }

        Box mainPanel = Box.createHorizontalBox();
        Box laAlignPanel = Box.createVerticalBox();
        Box leftAlignPanel = Box.createVerticalBox();
        Box centerAlignPanel = Box.createVerticalBox();
        Box rightAlignPanel = Box.createVerticalBox();
        Box raAlignPanel = Box.createVerticalBox();

        // Pin all columns to the top so showing/hiding dropdowns doesn't shift other columns
        laAlignPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        leftAlignPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        centerAlignPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        rightAlignPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        raAlignPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        hdPanel.setBorder(CritCellUtil.locationBorderNoLine("Head"));
        ltPanel.setBorder(CritCellUtil.locationBorderNoLine("Left Torso"));
        ctPanel.setBorder(CritCellUtil.locationBorderNoLine("Center Torso"));
        rtPanel.setBorder(CritCellUtil.locationBorderNoLine("Right Torso"));
        clPanel.setBorder(CritCellUtil.locationBorderNoLine("Center Leg"));

        laAlignPanel.add(Box.createVerticalStrut(100));
        laAlignPanel.add(laPanel);
        laAlignPanel.add(casePanels.get(Mek.LOC_LEFT_ARM));
        laAlignPanel.add(Box.createVerticalGlue());

        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(ltPanel);
        leftAlignPanel.add(casePanels.get(Mek.LOC_LEFT_TORSO));
        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(llPanel);
        leftAlignPanel.add(casePanels.get(Mek.LOC_LEFT_LEG));

        centerAlignPanel.add(hdPanel);
        centerAlignPanel.add(casePanels.get(Mek.LOC_HEAD));
        centerAlignPanel.add(ctPanel);
        centerAlignPanel.add(casePanels.get(Mek.LOC_CENTER_TORSO));
        centerAlignPanel.add(clPanel);
        centerAlignPanel.add(casePanels.get(Mek.LOC_CENTER_LEG));
        centerAlignPanel.add(Box.createVerticalStrut(75));

        rightAlignPanel.add(Box.createVerticalStrut(50));
        rightAlignPanel.add(rtPanel);
        rightAlignPanel.add(casePanels.get(Mek.LOC_RIGHT_TORSO));
        rightAlignPanel.add(Box.createVerticalStrut(50));
        rightAlignPanel.add(rlPanel);
        rightAlignPanel.add(casePanels.get(Mek.LOC_RIGHT_LEG));

        raAlignPanel.add(Box.createVerticalStrut(100));
        raAlignPanel.add(raPanel);
        raAlignPanel.add(casePanels.get(Mek.LOC_RIGHT_ARM));
        raAlignPanel.add(Box.createVerticalGlue());

        mainPanel.add(laAlignPanel);
        mainPanel.add(leftAlignPanel);
        mainPanel.add(centerAlignPanel);
        mainPanel.add(rightAlignPanel);
        mainPanel.add(raAlignPanel);

        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        currentCritBlocks.clear();
        laPanel.removeAll();
        raPanel.removeAll();
        llPanel.removeAll();
        rlPanel.removeAll();
        clPanel.removeAll();
        ltPanel.removeAll();
        rtPanel.removeAll();
        ctPanel.removeAll();
        hdPanel.removeAll();

        synchronized (getMek()) {
            clPanel.setVisible(getMek() instanceof TripodMek);
            setTitles();

            for (int location = 0; location < getMek().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);
                Set<Mounted<?>> critSlotMounts = new HashSet<>();

                for (int slot = 0; slot < getMek().getNumberOfCriticalSlots(location); slot++) {
                    CriticalSlot cs = getMek().getCritical(location, slot);
                    if (cs == null) {
                        critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> m = cs.getMount();
                        if (m == null) {
                            // Critical didn't get removed. Remove it now.
                            getMek().setCritical(location, slot, null);
                            critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                        } else {
                            critSlotMounts.add(m);
                            StringBuilder critName = new StringBuilder(m.getName());
                            if (m.isRearMounted()) {
                                critName.append(" (R)");
                            }
                            if (m.isMekTurretMounted()) {
                                critName.append(" (T)");
                            }
                            critNames.add(critName.toString());
                        }
                    }
                }

                // Collect 0-crit equipment assigned to this location
                List<Mounted<?>> zeroCritMounts = new ArrayList<>();
                int normalCritCount = getMek().getNumberOfCriticalSlots(location);
                for (Mounted<?> m : getMek().getEquipment()) {
                    if (m.getLocation() == location
                          && !critSlotMounts.contains(m)
                          && UnitUtil.getCritsUsed(m) == 0
                          && !UnitUtil.isArmorOrStructure(m.getType())) {
                        zeroCritMounts.add(m);
                        // Encode as name::eqNum so CritListCellRenderer can look up the mount
                        int eqNum = getMek().getEquipmentNum(m);
                        critNames.add(m.getName() + "::" + eqNum);
                    }
                }

                BAASBMDropTargetCriticalList<String> criticalSlotList = getCriticalSlotList(
                      critNames,
                      location);
                criticalSlotList.setZeroCritMounts(zeroCritMounts, normalCritCount);
                if (mekPanels.containsKey(location)) {
                    mekPanels.get(location).add(criticalSlotList);
                    currentCritBlocks.add(criticalSlotList);
                }
            }

            // Prevent location panels from stretching in the vertical BoxLayout,
            // so CASE dropdowns stay snug against their crit lists
            for (JComponent panel : mekPanels.values()) {
                Dimension pref = panel.getPreferredSize();
                panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));
            }

            refreshCaseDropdowns();
            validate();
        }
    }

    private BAASBMDropTargetCriticalList<String> getCriticalSlotList(Vector<String> critNames,
          int location) {
        BAASBMDropTargetCriticalList<String> criticalSlotList = new BAASBMDropTargetCriticalList<>(
              critNames, eSource, refresh, true, this);
        criticalSlotList.setVisibleRowCount(critNames.size());
        criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalSlotList.setName(location + "");
        criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
        return criticalSlotList;
    }

    private void setTitles() {
        String title = getMek().getLocationName(Mek.LOC_LEFT_ARM) + caseSuffix(Mek.LOC_LEFT_ARM);
        laPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_ARM) + caseSuffix(Mek.LOC_RIGHT_ARM);
        raPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LEFT_LEG) + caseSuffix(Mek.LOC_LEFT_LEG);
        llPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_LEG) + caseSuffix(Mek.LOC_RIGHT_LEG);
        rlPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CENTER_LEG) + caseSuffix(Mek.LOC_CENTER_LEG);
        clPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LEFT_TORSO) + caseSuffix(Mek.LOC_LEFT_TORSO);
        ltPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_TORSO) + caseSuffix(Mek.LOC_RIGHT_TORSO);
        rtPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CENTER_TORSO) + caseSuffix(Mek.LOC_CENTER_TORSO);
        ctPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_HEAD) + caseSuffix(Mek.LOC_HEAD);
        hdPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
    }

    private String caseSuffix(int location) {
        if (getMek().hasCASEII(location)) {
            return " (CASE II)";
        } else if (getMek().locationHasCase(location)) {
            return " (CASE)";
        } else {
            return "";
        }
    }

    /**
     * Refreshes CASE dropdown visibility and selections for all locations.
     * Shows dropdown only where the location has explosive equipment.
     * Populates options based on tech availability using F_CASE / F_CASEII / F_CASEP flags.
     */
    private void refreshCaseDropdowns() {
        updatingCaseCombos = true;
        try {
            Mek mek = getMek();
            ITechManager techManager = eSource.getTechManager();

            for (Map.Entry<Integer, JComboBox<EquipmentType>> entry : caseComboBoxes.entrySet()) {
                int loc = entry.getKey();
                JComboBox<EquipmentType> combo = entry.getValue();
                JPanel casePanel = casePanels.get(loc);

                // Hide for center leg if not a Tripod
                if (loc == Mek.LOC_CENTER_LEG && !(mek instanceof TripodMek)) {
                    casePanel.setVisible(false);
                    continue;
                }

                // Check if this location has explosive equipment
                boolean hasExplosive = locationHasExplosive(mek, loc);

                if (!hasExplosive) {
                    casePanel.setVisible(false);
                    continue;
                }

                // Populate dropdown: null = None, plus all legal CASE types valid for this location
                combo.removeAllItems();
                combo.addItem(null);
                for (EquipmentType caseType : getAllCaseTypes()) {
                    if (techManager.isLegal(caseType)
                          && UnitUtil.isValidLocation(mek, caseType, loc)) {
                        combo.addItem(caseType);
                    }
                }

                // Set current selection based on what's mounted
                EquipmentType currentCase = getCurrentCaseEquipment(mek, loc);
                combo.setSelectedItem(currentCase);

                // Constrain combo width to match the location's crit list panel
                JComponent locPanel = mekPanels.get(loc);
                if (locPanel != null) {
                    int panelWidth = locPanel.getPreferredSize().width;
                    Dimension size = new Dimension(panelWidth, combo.getPreferredSize().height);
                    combo.setMaximumSize(size);
                    casePanel.setMaximumSize(new Dimension(panelWidth,
                          combo.getPreferredSize().height + casePanel.getInsets().top + casePanel.getInsets().bottom));
                }

                casePanel.setVisible(true);
            }
        } finally {
            updatingCaseCombos = false;
        }
    }

    /**
     * Returns all CASE-family equipment types, discovered via the F_CASE, F_CASEII, and F_CASEP
     * MiscType flags. Cached after first call.
     */
    private static List<EquipmentType> getAllCaseTypes() {
        if (allCaseTypes == null) {
            allCaseTypes = new ArrayList<>();
            Enumeration<EquipmentType> allTypes = EquipmentType.getAllTypes();
            while (allTypes.hasMoreElements()) {
                EquipmentType et = allTypes.nextElement();
                if (et instanceof MiscType mt
                      && (mt.hasFlag(MiscType.F_CASE)
                      || mt.hasFlag(MiscType.F_CASEII)
                      || mt.hasFlag(MiscType.F_CASEP))) {
                    allCaseTypes.add(et);
                }
            }
        }
        return allCaseTypes;
    }

    /**

     * Returns true if the given location has any explosive equipment mounted.
     */
    private boolean locationHasExplosive(Mek mek, int loc) {
        for (Mounted<?> m : mek.getEquipment()) {
            if (m.getType().isExplosive(m, true)
                  && ((m.getLocation() == loc) || (m.getSecondLocation() == loc))) {
                // Don't count CASE itself as explosive
                if (!MekUtil.isCASE(m)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the EquipmentType of the CASE currently mounted in the given location,
     * or null if none.
     */
    private @Nullable EquipmentType getCurrentCaseEquipment(Mek mek, int loc) {
        for (Mounted<?> m : mek.getMisc()) {
            if (m.getLocation() == loc && MekUtil.isCASE(m)) {
                return m.getType();
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (updatingCaseCombos) {
            return;
        }
        // Handle CASE combo box changes
        int loc;
        try {
            loc = Integer.parseInt(e.getActionCommand());
        } catch (NumberFormatException ex) {
            return;
        }
        JComboBox<EquipmentType> combo = caseComboBoxes.get(loc);
        if (combo == null) {
            return;
        }
        EquipmentType selected = (EquipmentType) combo.getSelectedItem();
        Mek mek = getMek();
        EquipmentType currentCase = getCurrentCaseEquipment(mek, loc);

        // Nothing changed
        if (selected == currentCase) {
            return;
        }

        // Check if there is enough crit space for the new selection
        if (selected != null) {
            int neededCrits = selected.getNumCriticalSlots(mek);
            if (neededCrits > 0) {
                int currentFreed = (currentCase != null) ? currentCase.getNumCriticalSlots(mek) : 0;
                int freeCrits = countFreeCrits(mek, loc) + currentFreed;
                if (neededCrits > freeCrits) {
                    combo.hidePopup();
                    JOptionPane.showMessageDialog(
                          SwingUtilities.getWindowAncestor(this),
                          "Not enough free critical slots in " + mek.getLocationName(loc)
                                + " (" + neededCrits + " needed, " + (freeCrits) + " available).",
                          "Cannot Add CASE", JOptionPane.ERROR_MESSAGE);
                    // Revert the combo selection
                    updatingCaseCombos = true;
                    combo.setSelectedItem(currentCase);
                    updatingCaseCombos = false;
                    return;
                }
            }
        }

        // Remove existing CASE from this location
        removeCaseFromLocation(mek, loc);

        if (selected != null) {
            try {
                mek.addEquipment(Mounted.createMounted(mek, selected), loc, false);
            } catch (Exception ignored) {
                // Should not happen after the space check above
            }
        }

        // Handle Clan CASE opt-out logic
        updateClanCaseOptOut(mek, loc, selected);

        if (refresh != null) {
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
    }

    /**
     * Counts free (empty) critical slots in the given location.
     */
    private int countFreeCrits(Mek mek, int loc) {
        int free = 0;
        for (int slot = 0; slot < mek.getNumberOfCriticalSlots(loc); slot++) {
            if (mek.getCritical(loc, slot) == null) {
                free++;
            }
        }
        return free;
    }

    /**
     * Removes any CASE/CASE II/CASE-P from the given location.
     */
    private void removeCaseFromLocation(Mek mek, int loc) {
        List<Mounted<?>> toRemove = new ArrayList<>();
        for (Mounted<?> m : mek.getMisc()) {
            if (m.getLocation() == loc && MekUtil.isCASE(m)) {
                toRemove.add(m);
            }
        }
        for (Mounted<?> m : toRemove) {
            UnitUtil.removeMounted(mek, m);
        }
    }

    /**
     * Updates Clan CASE opt-out tracking based on user's dropdown selection.
     * When a Clan/Mixed unit user removes Clan CASE (selects None or non-Clan CASE),
     * that location is marked as opted-out. When they set Clan CASE, the opt-out is cleared.
     */
    private void updateClanCaseOptOut(Mek mek, int loc, @Nullable EquipmentType selected) {
        boolean isClanOrMixed = mek.isClan() || mek.isMixedTech();
        if (!isClanOrMixed) {
            return;
        }

        boolean isClanCase = (selected instanceof MiscType mt) && mt.hasFlag(MiscType.F_CASE) && mt.isClan();
        if (isClanCase) {
            // User is setting Clan CASE - remove opt-out for this location
            mek.removeClanCaseOptOut(loc);
        } else if (locationHasExplosive(mek, loc)) {
            // User chose something other than Clan CASE for an explosive location - mark as opted-out
            mek.addClanCaseOptOut(loc);
        }
    }

    /**
     * Darkens all crit blocks other than the one for the given location
     */
    public void markUnavailableLocations(int location) {
        currentCritBlocks.stream()
              .filter(b -> b.getCritLocation() != location)
              .forEach(b -> b.setDarkened(true));
    }

    /**
     * Darkens all crit blocks that are unavailable to the given equipment, e.g. all but Torsos for CASE.
     */
    public void markUnavailableLocations(@Nullable Mounted<?> equipment) {
        if (equipment != null) {
            currentCritBlocks.stream()
                  .filter(b -> !UnitUtil.isValidLocation(getMek(), equipment.getType(), b.getCritLocation()))
                  .forEach(b -> b.setDarkened(true));
        }
    }

    /** Resets all crit blocks to not darkened. */
    public void unMarkAllLocations() {
        currentCritBlocks.forEach(b -> b.setDarkened(false));
    }

}
