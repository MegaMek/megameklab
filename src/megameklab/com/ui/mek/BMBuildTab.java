/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free  software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.com.ui.mek;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.*;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;

import static java.util.stream.Collectors.toSet;
import static megameklab.com.util.UnitUtil.*;

public class BMBuildTab extends ITab {

    private RefreshListener refresh = null;
    private final BMCriticalView critView;
    private final BMBuildView buildView;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Tabs", new EncodeControl());

    private final JToggleButton autoFillUnHittables = new JToggleButton(resources.getString("BuildTab.autoFillUnhittables.text"));
    private final JToggleButton autoCompact = new JToggleButton(resources.getString("BuildTab.autoCompact.text"));
    private final JToggleButton autoSort = new JToggleButton(resources.getString("BuildTab.autoSort.text"));

    public BMBuildTab(EntitySource eSource) {
        super(eSource);
        critView = new BMCriticalView(eSource, refresh);
        buildView = new BMBuildView(eSource, refresh);

        Box leftSide = Box.createVerticalBox();
        leftSide.add(createButtonPanel());
        leftSide.add(critView);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(leftSide);
        add(buildView);
        refresh();
    }

    private JComponent createButtonPanel() {
        autoFillUnHittables.addActionListener(e -> refresh());
        autoFillUnHittables.setToolTipText(resources.getString("BuildTab.autoFillUnhittables.tooltip"));
        autoFillUnHittables.setSelected(true);
        autoCompact.addActionListener(e -> refresh());
        autoCompact.setToolTipText(resources.getString("BuildTab.autoCompact.tooltip"));
        autoSort.addActionListener(e -> refresh());
        autoSort.addActionListener(e -> autoCompact.setEnabled(!autoSort.isSelected()));
        autoSort.setToolTipText(resources.getString("BuildTab.autoSort.tooltip"));
        autoSort.setSelected(true);

        JButton fillButton = new JButton(resources.getString("BuildTab.Fill.text"));
        fillButton.setToolTipText(resources.getString("BuildTab.Fill.tooltip"));
        fillButton.setMnemonic(KeyEvent.VK_A);
        fillButton.addActionListener(e -> autoFillCrits());

        JButton resetButton = new JButton(resources.getString("BuildTab.Reset.text"));
        resetButton.setToolTipText(resources.getString("BuildTab.Reset.tooltip"));
        resetButton.addActionListener(e -> resetCrits());

        JButton compactButton = new JButton(resources.getString("BuildTab.Compact.text"));
        compactButton.setToolTipText(resources.getString("BuildTab.Compact.tooltip"));
        compactButton.setMnemonic(KeyEvent.VK_C);
        compactButton.addActionListener(e -> compactCrits());

        JButton sortButton = new JButton(resources.getString("BuildTab.Sort.text"));
        sortButton.setToolTipText(resources.getString("BuildTab.Sort.tooltip"));
        sortButton.setMnemonic(KeyEvent.VK_S);
        sortButton.addActionListener(e -> sortCrits());

        JPanel leftSide = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.LEFT));
        leftSide.setOpaque(false);
        leftSide.add(autoFillUnHittables);
        leftSide.add(autoCompact);
        leftSide.add(autoSort);
        leftSide.add(Box.createHorizontalStrut(20));
        leftSide.add(fillButton);
        leftSide.add(compactButton);
        leftSide.add(sortButton);

        JPanel rightSide = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.RIGHT));
        rightSide.setOpaque(false);
        rightSide.add(resetButton);

        Box buttonPanel = Box.createHorizontalBox();
        buttonPanel.setBackground(UIUtil.alternateTableBGColor());
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(getBackground(), 10),
                new EmptyBorder(5, 10, 5, 10)));
        buttonPanel.add(leftSide);
        buttonPanel.add(rightSide);
        return buttonPanel;
    }

    public void refresh() {
        autoFillUnHittables();
        autoCompactCrits();
        autoSortCrits();
        critView.refresh();
        buildView.refresh();
    }

    private void autoFillUnHittables() {
        if (!autoFillUnHittables.isSelected()) {
            return;
        }
        for (Mounted mount : getMech().getEquipment()) {
            if (!UnitUtil.isFreelyMovable(mount) || (mount.getLocation() != Entity.LOC_NONE)) {
                continue;
            }
            for (int location = 0; location < getMech().locations(); location++) {
                if (!isValidLocation(getMech(), mount.getType(), location)
                        || (getHighestContinuousNumberOfCrits(getMech(), location) < getCritsUsed(mount))) {
                    continue;
                }
                try {
                    addMounted(getMech(), mount, location, false);
                    changeMountStatus(getMech(), mount, location, Entity.LOC_NONE, false);
                    break;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
    }

    private void autoFillCrits() {
        for (Mounted mount : buildView.getTableModel().getCrits()) {
            int externalEngineHS = UnitUtil.getCriticalFreeHeatSinks(getMech(), getMech().hasCompactHeatSinks());
            for (int location = Mech.LOC_HEAD; location < getMech().locations(); location++) {

                if (!UnitUtil.isValidLocation(getMech(), mount.getType(), location)) {
                    continue;
                }

                int continuousNumberOfCrits = UnitUtil.getHighestContinuousNumberOfCrits(getMech(), location);
                int critsUsed = UnitUtil.getCritsUsed(mount);
                if (continuousNumberOfCrits < critsUsed) {
                    continue;
                }
                if ((mount.getLocation() == Entity.LOC_NONE)) {
                    if (UnitUtil.isHeatSink(mount) && (externalEngineHS-- > 0)) {
                        continue;
                    }
                }

                try {
                    if (mount.getType().isSpreadable() || (mount.isSplitable() && (critsUsed > 1))) {
                        for (int count = 0; count < critsUsed; count++) {
                            UnitUtil.addMounted(getMech(), mount, location, false);
                        }
                    } else {
                        UnitUtil.addMounted(getMech(), mount, location, false);
                    }
                    UnitUtil.changeMountStatus(getMech(), mount, location, Entity.LOC_NONE, false);
                    break;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
        refresh.refreshAll();
    }

    private void resetCrits() {
        for (Mounted mounted : getMech().getEquipment()) {
            if (!UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
                UnitUtil.removeCriticals(getMech(), mounted);
                clearMountedLocationAndLinked(mounted);
            }
        }
        refresh.refreshAll();
    }

   private void clearMountedLocationAndLinked(Mounted eq) {
        if ((Entity.LOC_NONE != eq.getLocation() && !eq.isOneShot())) {
            if (eq.getLinked() != null) {
                eq.getLinked().setLinkedBy(null);
                eq.setLinked(null);
            }
            if (eq.getLinkedBy() != null) {
                eq.getLinkedBy().setLinked(null);
                eq.setLinkedBy(null);
            }
        }
        eq.setLocation(Entity.LOC_NONE, false);
        eq.setSecondLocation(Entity.LOC_NONE, false);
        eq.setSplit(false);
    }

    /**
     * Called during refresh when Auto-Compact is activated. Auto-Sorting already results
     * in compacting, therefore this can be skipped when Auto-sorting is activated.
     * It is important that this method does not call a refresh to avoid a loop!
     */
    private void autoCompactCrits() {
        if (autoCompact.isSelected() && !autoSort.isSelected()) {
            UnitUtil.compactCriticals(getMech());
        }
    }

    /**
     * Called from the Compact button. Must not be called from within a refresh as this
     * calls a refresh and will result in a loop!
     */
    private void compactCrits() {
        UnitUtil.compactCriticals(getMech());
        refresh.refreshAll();
    }

    /**
     * Called from the Sort button. Must not be called from within a refresh as this
     * calls a refresh and will result in a loop!
     */
    private void sortCrits() {
        for (int location = 0; location < getMech().locations(); location++) {
            if (location != Mech.LOC_HEAD) {
                sortLocationCrits(location);
            }
        }
        refresh();
    }

    /**
     * Called during refresh when Auto-Sorting is activated.
     * It is important that this method does not call a refresh to avoid a loop!
     */
    private void autoSortCrits() {
        if (autoSort.isSelected()) {
            for (int location = 0; location < getMech().locations(); location++) {
                if (location != Mech.LOC_HEAD) {
                    sortLocationCrits(location);
                }
            }
        }
    }

    /**
     * Sorts the crits within the given location. This extricates all non-system
     * crits from the location, sorts them and then refills the location.
     */
    private void sortLocationCrits(int location) {
        List<CriticalSlot> presentGear = new ArrayList<>();
        for (int slot = 0; slot < getMech().getNumberOfCriticals(location); slot++) {
            CriticalSlot critSlot = getMech().getCritical(location, slot);
            if ((critSlot != null) && (critSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                presentGear.add(critSlot);
                getMech().setCritical(location, slot, null);
            }
        }
        presentGear.sort(locationSorter);
        presentGear = reOrderLinkedEquipment(presentGear);
        presentGear.forEach(criticalSlot -> addToTopFreeSlot(location, criticalSlot));
    }

    /**
     * Returns a reordered version of the given presentGear list of critslots wherein
     * LinkedBy mounteds such as Artemis and PPC Capacitors are placed directly behind
     * their weapon.
     */
    private List<CriticalSlot> reOrderLinkedEquipment(List<CriticalSlot> presentGear) {
        List<CriticalSlot> resortedList = new ArrayList<>();
        Set<Mounted> presentMounteds = presentGear.stream().map(CriticalSlot::getMount).collect(toSet());
        // Assemble all the linked gear that is not ammo (ammo is sorted differently)
        Set<Mounted> linkedMounteds = presentMounteds.stream()
                .map(Mounted::getLinkedBy)
                .filter(Objects::nonNull)
                .filter(linked -> linked.getType() instanceof MiscType)
                .filter(presentMounteds::contains)
                .collect(toSet());

        // Now rebuild the list by adding the linkedMounteds behind their weapon
        Mounted lastMounted = null;
        for (CriticalSlot critSlot : presentGear) {
            Mounted currentMounted = critSlot.getMount();
            // after one mounted is fully added, see if there's a linkedMounted to add below it
            if ((lastMounted != null)
                    && (currentMounted != lastMounted)
                    && (lastMounted.getLinkedBy() != null)
                    && (linkedMounteds.contains(lastMounted.getLinkedBy()))) {
                for (CriticalSlot linkedSlot : presentGear) {
                    if (linkedSlot.getMount() == lastMounted.getLinkedBy()) {
                        resortedList.add(linkedSlot);
                    }
                }
            }
            // Add the current crit slot but exclude the linkedMounteds as they are added behind their weapon
            if (!linkedMounteds.contains(critSlot.getMount())) {
                resortedList.add(critSlot);
            }
            lastMounted = currentMounted;
        }
        return resortedList;
    }

    /**
     * A location crit sorter using the official sort order (mostly)
     */
    Comparator<CriticalSlot> locationSorter = (critA, critB) -> {
        int coarseOrderA = getCoarseOrder(critA);
        int coarseOrderB = getCoarseOrder(critB);
        if (coarseOrderA == 4 && coarseOrderB == 4) {
            // compare average damage; using Aero damage here
            double dmgA = 0;
            double dmgB = 0;
            if (critA.getMount().getType() instanceof WeaponType) {
                dmgA = ((WeaponType) critA.getMount().getType()).getShortAV();
            }
            if (critB.getMount().getType() instanceof WeaponType) {
                dmgB = ((WeaponType) critB.getMount().getType()).getShortAV();
            }
            if (dmgA != dmgB) {
                return (dmgA > dmgB) ? -1 : 1;
            } else {
                // equal damage, compare crits
                int critsA = critA.getMount().getCriticals();
                int critsB = critB.getMount().getCriticals();
                if (critsA != critsB) {
                    return (critsA > critsB) ? -1 : 1;
                } else {
                    // equal crits, compare weight
                    double weightA = critA.getMount().getType().getTonnage(getMech());
                    double weightB = critB.getMount().getType().getTonnage(getMech());
                    if (weightA != weightB) {
                        return (weightA > weightB) ? -1 : 1;
                    } else {
                        return 0;
                    }
                }
            }
        } else if (coarseOrderA == 5 && coarseOrderB == 5) {
            // TODO: compare ammo
            return 0;
        } else if (coarseOrderA == coarseOrderB) {
            return 0;
        } else {
            return (coarseOrderA > coarseOrderB) ? 1 : -1;
        }
    };

    private int getCoarseOrder(CriticalSlot critSlot) {
        if (isPartialWingCrit(critSlot)) {
            return 1;
        } else if (UnitUtil.isHeatSink(critSlot.getMount())) {
            return 2;
        } else if (UnitUtil.isJumpJet(critSlot.getMount())) {
            return 3;
        } else if (UnitUtil.isMechWeapon(critSlot.getMount().getType(), getMech())) {
            return 4;
        } else if (critSlot.getMount().getType() instanceof AmmoType) {
            return 5;
        } else if (critSlot.getMount().getType().isHittable()) {
            return 6;
        } else if (isCASECrit(critSlot)) {
            return 7;
        } else if (EquipmentType.isStructureType(critSlot.getMount().getType())) {
            return 8;
        } else if (EquipmentType.isArmorType(critSlot.getMount().getType())) {
            return 9;
        } else {
            return 10;
        }
    }

    private void addToTopFreeSlot(int location, CriticalSlot critSlot) {
        for (int slot = 0; slot < getMech().getNumberOfCriticals(location); slot++) {
            if (getMech().getCritical(location, slot) == null) {
                getMech().setCritical(location, slot, critSlot);
                return;
            }
        }
    }

    private boolean isPartialWingCrit(CriticalSlot critSlot) {
        return (critSlot.getMount().getType() instanceof MiscType)
                && critSlot.getMount().getType().hasFlag(MiscType.F_PARTIAL_WING);

    }

    private boolean isCASECrit(CriticalSlot critSlot) {
        return (critSlot.getMount().getType() instanceof MiscType)
                && (critSlot.getMount().getType().hasFlag(MiscType.F_CASE)
                || critSlot.getMount().getType().hasFlag(MiscType.F_CASEP)
                || critSlot.getMount().getType().hasFlag(MiscType.F_CASEII));
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        critView.updateRefresh(refresh);
        buildView.addRefreshedListener(refresh);
    }

    public void refreshAll() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }
}