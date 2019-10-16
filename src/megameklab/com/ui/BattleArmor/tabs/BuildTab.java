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

package megameklab.com.ui.BattleArmor.tabs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import megamek.common.BattleArmor;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.BattleArmor.CriticalSuit;
import megameklab.com.ui.BattleArmor.views.BuildView;
import megameklab.com.ui.BattleArmor.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

/**
 * A component that creates a table for building the criticals of a unit.  This
 * tab shows a table of the un-allocated equipment and displays criticals for
 * the whole <code>BattleArmor</code> squad.
 *
 * @author Taharqa
 * @author arlith
 *
 */
public class BuildTab extends ITab implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private RefreshListener refresh = null;
    private ArrayList<CriticalView> critViews = new ArrayList<CriticalView>();
    private BuildView buildView = null;

    private JScrollPane critScrollPanel;

    /**
     * Panel for displaying the critical trees for each trooper in the squad.
     */
    private JPanel critPanel = new JPanel();
    /**
     * Panel that displays autoFill and reset buttons.
     */

    private JPanel buttonPanel = new JPanel();
    /**
     * Panel that holds the <code>UnallocatedView</code> and buttonPanel
     */
    private JPanel mainPanel = new JPanel();

    private JButton autoFillButton = new JButton("Auto Fill");
    private JButton resetButton = new JButton("Reset");

    private String AUTOFILLCOMMAND = "autofillbuttoncommand";
    private String RESETCOMMAND = "resetbuttoncommand";

    public BuildTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        critPanel.setLayout(new BoxLayout(critPanel, BoxLayout.Y_AXIS));
        critScrollPanel = new JScrollPane(critPanel);
        critScrollPanel.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        critScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        critScrollPanel.setMinimumSize(new java.awt.Dimension(400, 500));
        critScrollPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        critScrollPanel.getVerticalScrollBar().setUnitIncrement(20);

        createCriticalViews();

        buildView = new BuildView(eSource);

        mainPanel.add(buildView);

        autoFillButton.setMnemonic('A');
        autoFillButton.setActionCommand(AUTOFILLCOMMAND);
        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESETCOMMAND);
        buttonPanel.add(autoFillButton);
        buttonPanel.add(resetButton);

        mainPanel.add(buttonPanel);

        this.add(critScrollPanel);
        this.add(mainPanel);
        refresh();
    }

    public JPanel availableCritsPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());
        Dimension maxSize = new Dimension();

        masterPanel.add(buildView);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 1);
        maxSize.setSize(300, 5);
        masterPanel.setPreferredSize(maxSize);
        masterPanel.setMinimumSize(maxSize);
        masterPanel.setMaximumSize(maxSize);
        return masterPanel;
    }

    /**
     * Unlike other units, BattleArmor have multiple suits in the squad that
     * have crits that can be assigned.  Different types of BattleArmor squads
     * have different numbers of troops, so instead of one
     * <code>CriticalView</code> we have several.  If the squad size changes,
     * we will need to adjust the number of <code>CriticalView</code>s.  This
     * method will create the proper number of <code>CriticalView</code>s.
     */
    private void createCriticalViews(){
        critViews.clear();
        for (int i = 0; i < (getBattleArmor()).getTroopers(); i++){
            critViews.add(new CriticalView(eSource, i + 1, true,
                    refresh));
            critPanel.add(critViews.get(i));
            critPanel.add(Box.createVerticalStrut(20));
        }
    }

    public void refresh() {
        removeAllActionListeners();

        // We need to have a CritView for each trooper
        if (critViews.size() != (getBattleArmor()).getTroopers()){
            critPanel.removeAll();
            createCriticalViews();
        }

        for (CriticalView critView : critViews){
            critView.refresh();
        }
        critPanel.validate();

        buildView.refresh();

        addAllActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(AUTOFILLCOMMAND)) {
            autoFillCrits();
        } else if (e.getActionCommand().equals(RESETCOMMAND)) {
            resetCrits();
        }
    }

    private void autoFillCrits() {
        // BattleArmor doesn't track crits implicitly, so they need to be
        // tracked explicitly
        CriticalSuit crits = new CriticalSuit(getBattleArmor());
        for (Mounted mount : buildView.getTableModel().getCrits()) {
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
        refresh.refreshAll();
    }

    private void resetCrits() {
        for (Mounted mount : getBattleArmor().getEquipment()) {
            if (UnitUtil.isFixedLocationSpreadEquipment(mount.getType())){
                continue;
            }
            mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
        }
        refresh.refreshAll();
    }

    public void removeAllActionListeners() {
        autoFillButton.removeActionListener(this);
        resetButton.removeActionListener(this);
    }

    public void addAllActionListeners() {
        autoFillButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        for (CriticalView critView : critViews){
            critView.updateRefresh(refresh);
        }
        buildView.addRefreshedListener(refresh);
    }

    public void refreshAll() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

}