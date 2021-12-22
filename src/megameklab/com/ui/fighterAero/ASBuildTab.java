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

package megameklab.com.ui.fighterAero;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.verifier.TestAero;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.generalUnit.AeroEquipmentTab;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class ASBuildTab extends ITab implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private RefreshListener refresh = null;
    private ASCriticalView critView = null;
    private ASBuildView buildView = null;
    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private JButton resetButton = new JButton("Reset");

    private String RESETCOMMAND = "resetbuttoncommand";

    public ASBuildTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        mainPanel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();

        critView = new ASCriticalView(eSource, refresh);
        buildView = new ASBuildView(eSource,refresh);

        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESETCOMMAND);        
        buttonPanel.add(resetButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(buildView, gbc);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.0;
        mainPanel.add(buttonPanel, gbc);
        this.add(critView);
        this.add(mainPanel);
        refresh();
    }

    public void refresh() {
        removeAllActionListeners();
        critView.refresh();
        critView.validate();
        buildView.refresh();
        addAllActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(RESETCOMMAND)) {
            resetCrits();
        }
    }


    private void resetCrits() {
        for (Mounted mount : getAero().getEquipment()) {
            if (!mount.isWeaponGroup() && TestAero.eqRequiresLocation(mount.getType(), true)
                    && !UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
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