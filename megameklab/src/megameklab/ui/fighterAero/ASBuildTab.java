/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.fighterAero;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class ASBuildTab extends ITab implements ActionListener {

    private RefreshListener refresh = null;
    private final ASCriticalView critView;
    private final ASBuildView buildView;
    private final JButton resetButton = new JButton("Reset");
    private final String RESET_COMMAND = "resetButtonCommand";

    public ASBuildTab(EntitySource eSource) {
        super(eSource);

        critView = new ASCriticalView(eSource, refresh);
        buildView = new ASBuildView(eSource, refresh, critView);
        resetButton.setActionCommand(RESET_COMMAND);

        JPanel unallocatedEquipmentBlock = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        unallocatedEquipmentBlock.add(buildView, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        unallocatedEquipmentBlock.add(resetButton, gbc);

        setLayout(new BorderLayout(5, 5));
        add(critView, BorderLayout.CENTER);
        add(unallocatedEquipmentBlock, BorderLayout.EAST);

        refresh();
    }

    public void refresh() {
        removeAllActionListeners();
        critView.refresh();
        buildView.refresh();
        addAllActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(RESET_COMMAND)) {
            resetCrits();
        }
    }

    private void resetCrits() {
        UnitUtil.removeAllCriticalSlots(getAero());
        refresh.scheduleRefresh();
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
            refresh.scheduleRefresh();
        }
    }

    public ASBuildView getBuildView() {
        return buildView;
    }
}
