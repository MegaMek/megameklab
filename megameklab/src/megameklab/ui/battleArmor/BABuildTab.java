/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.ui.battleArmor;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import megamek.common.BattleArmor;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * A component that creates a table for building the criticals of a unit.  This
 * tab shows a table of the un-allocated equipment and displays criticals for
 * the whole <code>BattleArmor</code> squad.
 *
 * @author Taharqa
 * @author arlith
 *
 */
public class BABuildTab extends ITab {

    private RefreshListener refresh = null;
    private final ArrayList<BACriticalView> critViews = new ArrayList<>();

    public BABuildView getBuildView() {
        return buildView;
    }

    private final BABuildView buildView;

    /** Panel for displaying the critical trees for each trooper in the squad. */
    private final Box critPanel = Box.createVerticalBox();

    public BABuildTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        createCriticalViews();
        buildView = new BABuildView(eSource);

        JButton autoFillButton = new JButton("Auto Fill");
        autoFillButton.setMnemonic('A');
        autoFillButton.addActionListener(ev -> autoFillCrits());
        JButton resetButton = new JButton("Reset");
        resetButton.setMnemonic('R');
        resetButton.addActionListener(ev -> resetCrits());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoFillButton);
        buttonPanel.add(resetButton);

        Box mainPanel = Box.createVerticalBox();
        mainPanel.add(buildView);
        mainPanel.add(buttonPanel);
        add(critPanel);
        add(mainPanel);
        refresh();
    }

    /**
     * Unlike other units, BattleArmor have multiple suits in the squad that
     * have crits that can be assigned.  Different types of BattleArmor squads
     * have different numbers of troops, so instead of one
     * <code>CriticalView</code> we have several.  If the squad size changes,
     * we will need to adjust the number of <code>CriticalView</code>s.  This
     * method will create the proper number of <code>CriticalView</code>s.
     */
    private void createCriticalViews() {
        critViews.clear();
        for (int i = 0; i < (getBattleArmor()).getTroopers(); i++) {
            critViews.add(new BACriticalView(eSource, i + 1, true, refresh));
            critPanel.add(critViews.get(i));
            critPanel.add(Box.createVerticalStrut(20));
        }
    }

    public void refresh() {
        // We need to have a CritView for each trooper
        if (critViews.size() != (getBattleArmor()).getTroopers()) {
            critPanel.removeAll();
            createCriticalViews();
        }
        for (BACriticalView critView : critViews) {
            critView.refresh();
        }
        critPanel.validate();
        buildView.refresh();
    }

    private void autoFillCrits() {
        // BattleArmor doesn't track crits implicitly, so they need to be tracked explicitly
        BACriticalSuit crits = new BACriticalSuit(getBattleArmor());
        // Populate with equipment that is already installed
        for (Mounted<?> m : getBattleArmor().getEquipment()) {
            if ((m.getLocation() == BattleArmor.LOC_SQUAD)
                    && (m.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE)) {
                crits.addMounted(m.getBaMountLoc(), m);
            }
        }
        for (Mounted<?> mount : buildView.getTableModel().getCrits()) {
            for (int location = BattleArmor.MOUNT_LOC_BODY;
                    location < BattleArmor.MOUNT_NUM_LOCS; location++) {
                if (!UnitUtil.isValidLocation(getBattleArmor(), mount.getType(), location)) {
                    continue;
                }
                // Don't assign change equipment that's already placed
                if (mount.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE) {
                    continue;
                }
                if (crits.canAddMounted(location, mount)) {
                    mount.setBaMountLoc(location);
                    // Just added for record keeping
                    crits.addMounted(location, mount);
                    break;
                }
            }
        }
        refreshAll();
    }

    private void resetCrits() {
        for (Mounted<?> mount : getBattleArmor().getEquipment()) {
            if (UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                continue;
            }
            mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
        }
        refreshAll();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        critViews.forEach(v -> v.updateRefresh(refresh));
        buildView.addRefreshedListener(refresh);
    }

    public void refreshAll() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

}
