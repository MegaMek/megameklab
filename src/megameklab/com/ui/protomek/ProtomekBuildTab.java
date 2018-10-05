/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.ui.protomek;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.tabs.EquipmentTab;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

/**
 * Tab for assigning equipment to locations
 * 
 * @author Neoancient
 *
 */
public class ProtomekBuildTab extends ITab implements ActionListener {

    private static final long serialVersionUID = -1408764780198728574L;

    private RefreshListener refresh = null;
    private ProtomekCriticalView critView = null;
    private CriticalTableModel critList;
    private ProtomekBuildView buildView = null;
    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private JButton autoFillButton = new JButton("Auto Fill");
    private JButton resetButton = new JButton("Reset");

    private String AUTOFILLCOMMAND = "autofillbuttoncommand";
    private String RESETCOMMAND = "resetbuttoncommand";

    public ProtomekBuildTab(EntitySource eSource, EquipmentTab equipment, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;
        this.critList = equipment.getEquipmentList();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        critView = new ProtomekCriticalView(eSource, refresh);
        buildView = new ProtomekBuildView(eSource, refresh);

        mainPanel.add(buildView);

        autoFillButton.setMnemonic('A');
        autoFillButton.setActionCommand(AUTOFILLCOMMAND);
        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESETCOMMAND);
        buttonPanel.add(autoFillButton);
        buttonPanel.add(resetButton);

        mainPanel.add(buttonPanel);

        this.add(critView);
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

    public void refresh() {
        removeAllActionListeners();
        critView.refresh();
        buildView.refresh();
        addAllActionListeners();
    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        label.setMaximumSize(maxSize);
        label.setMinimumSize(maxSize);
        label.setPreferredSize(maxSize);

        return label;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(AUTOFILLCOMMAND)) {
            autoFillCrits();
        } else if (e.getActionCommand().equals(RESETCOMMAND)) {
            resetCrits();
        }
    }

    private void autoFillCrits() {

        for (Mounted mount : buildView.getTableModel().getCrits()) {
            for (int location = 0; location < getProtomech().locations(); location++) {
                if (UnitUtil.protomechHasRoom(getProtomech(), location, mount)) {
                    UnitUtil.changeMountStatus(getProtomech(), mount, location, Entity.LOC_NONE, false);
                }
            }
        }
        refresh.refreshAll();

    }

    private void resetCrits() {
        for (Mounted mount : getProtomech().getEquipment()) {
            // Fixed shouldn't be removed
            if (TestProtomech.requiresSlot(mount.getType())) {
                UnitUtil.changeMountStatus(getProtomech(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
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
        critView.updateRefresh(refresh);
        buildView.addRefreshedListener(refresh);
    }

    public void addCrit(Mounted mount) {
        critList.addCrit(mount);
    }

    public void refreshAll() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    /**
     * @return the critList
     */
    public CriticalTableModel getCritList() {
        return critList;
    }

}
