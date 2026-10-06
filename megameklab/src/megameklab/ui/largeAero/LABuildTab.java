/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.largeAero;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * Build tab for Small Craft and DropShips
 *
 * @author Neoancient
 */
public class LABuildTab extends ITab implements ActionListener {
    private RefreshListener refresh = null;
    private final LACriticalView critView;

    public LABuildView getBuildView() {
        return buildView;
    }

    private final LABuildView buildView;

    private final JButton resetButton = new JButton("Reset");

    private final String RESET_COMMAND = "resetButtonCommand";

    public LABuildTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        JPanel critPanel = new JPanel();
        critPanel.setLayout(new BoxLayout(critPanel, BoxLayout.Y_AXIS));
        JPanel buildPanel = new JPanel();
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buildView = new LABuildView(eSource, refresh);
        critView = new LACriticalView(eSource, refresh);
        critView.addAllocationListeners(buildView);

        critPanel.add(Box.createVerticalGlue());
        critPanel.add(critView);
        critPanel.add(Box.createVerticalGlue());

        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESET_COMMAND);
        buttonPanel.add(resetButton);

        buildPanel.add(buildView);
        buildPanel.add(buttonPanel);

        this.add(critPanel, BorderLayout.CENTER);
        this.add(buildPanel, BorderLayout.EAST);
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
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(RESET_COMMAND)) {
            resetCrits();
        }
    }

    private void resetCrits() {
        UnitUtil.removeAllCriticalSlots(getAero());
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
