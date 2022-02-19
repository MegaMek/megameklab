/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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
package megameklab.ui.combatVeh;

import megamek.common.Entity;
import megamek.common.MechFileParser;
import megamek.common.Mounted;
import megamek.common.loaders.EntityLoadingException;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.UnallocatedView;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CVBuildTab extends ITab implements ActionListener {
    private RefreshListener refresh = null;
    private CVCriticalView critView;
    private UnallocatedView unallocatedView;

    private JButton autoFillButton = new JButton("Auto Fill");
    private JButton resetButton = new JButton("Reset");

    private String AUTOFILLCOMMAND = "autofillbuttoncommand";
    private String RESETCOMMAND = "resetbuttoncommand";

    public CVBuildTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        critView = new CVCriticalView(eSource, refresh);
        unallocatedView = new UnallocatedView(eSource, () -> refresh);

        mainPanel.add(unallocatedView);

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

    public void refresh() {
        removeAllActionListeners();
        critView.refresh();
        unallocatedView.refresh();
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

        for (Mounted mount : unallocatedView.getTableModel().getCrits()) {
            for (int location = 0; location < getTank().locations(); location++) {
                try {
                    getTank().addEquipment(mount, location, false);
                    UnitUtil.changeMountStatus(getTank(), mount, location, Entity.LOC_NONE, false);
                    break;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
        refresh.refreshAll();

    }

    private void resetCrits() {
        for (Mounted mount : getTank().getEquipment()) {
            // Fixed shouldn't be removed
            if (UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                continue;
            }
            UnitUtil.removeCriticals(getTank(), mount);
            UnitUtil.changeMountStatus(getTank(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
        }
        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getTank());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }

        refresh.refreshAll();
    }

    private void removeAllActionListeners() {
        autoFillButton.removeActionListener(this);
        resetButton.removeActionListener(this);
    }

    private void addAllActionListeners() {
        autoFillButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        critView.updateRefresh(refresh);
        unallocatedView.addRefreshedListener(refresh);
    }
}