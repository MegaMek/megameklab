/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.largeaero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.fighteraero.ASEquipmentTab;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * Build tab for Small Craft and Dropships
 * 
 * @author Neoancient
 *
 */
public class DSBuildTab extends ITab implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -6625026210117558378L;
    
    private RefreshListener refresh = null;
    private LACriticalView critView = null;
    private LABuildView buildView = null;
    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private JButton resetButton = new JButton("Reset");

    private String RESETCOMMAND = "resetbuttoncommand";

    public DSBuildTab(EntitySource eSource, ASEquipmentTab equipment) {
        super(eSource);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buildView = new LABuildView(eSource,refresh);
        critView = new LACriticalView(eSource, refresh);
        critView.addAllocationListeners(buildView);

        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESETCOMMAND);
        buttonPanel.add(resetButton);

        buildView.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        mainPanel.add(buildView);
        mainPanel.add(buttonPanel);

        add(Box.createHorizontalGlue());
        add(critView);
        add(Box.createHorizontalGlue());
        add(mainPanel);
        add(Box.createHorizontalGlue());
        refresh();
    }

    public void refresh() {
        removeAllActionListeners();
        critView.refresh();
        critView.validate();
        buildView.refresh();
        addAllActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(RESETCOMMAND)) {
            resetCrits();
        }
    }

    private void resetCrits() {
        for (Mounted mount : getAero().getEquipment()) {
            if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                UnitUtil.removeCriticals(getAero(), mount);
                UnitUtil.changeMountStatus(getAero(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }

        refresh.refreshAll();
    }

    public void removeAllActionListeners() {
        resetButton.removeActionListener(this);
    }

    public void addAllActionListeners() {
        resetButton.addActionListener(this);
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
