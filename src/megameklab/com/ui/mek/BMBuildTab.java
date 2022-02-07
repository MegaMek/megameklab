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
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.Mounted;
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

public class BMBuildTab extends ITab {

    private RefreshListener refresh = null;
    private final BMCriticalView critView;
    private final BMBuildView buildView;

    public BMBuildTab(EntitySource eSource) {
        super(eSource);
        critView = new BMCriticalView(eSource, refresh);
        buildView = new BMBuildView(eSource, refresh);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel buttonPanel = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(UIUtil.alternateTableBGColor());
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(getBackground(), 10),
                new EmptyBorder(5, 10, 5, 10)));
        JButton autoFillButton = new JButton("Auto Fill");
        autoFillButton.setMnemonic(KeyEvent.VK_A);
        autoFillButton.addActionListener(e -> autoFillCrits());
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetCrits());
        JButton compactButton = new JButton("Compact");
        compactButton.setMnemonic(KeyEvent.VK_C);
        compactButton.addActionListener(e -> compactCrits());
        buttonPanel.add(autoFillButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(compactButton);

        Box leftSide = Box.createVerticalBox();
        leftSide.add(buttonPanel);
        leftSide.add(critView);

        add(leftSide);
        add(buildView);
        refresh();
    }

    public void refresh() {
        critView.refresh();
        buildView.refresh();
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
        for (Mounted mount : getMech().getEquipment()) {
            if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                UnitUtil.removeCriticals(getMech(), mount);
                UnitUtil.changeMountStatus(getMech(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
        refresh.refreshAll();
    }

    private void compactCrits() {
        UnitUtil.compactCriticals(getMech());
        refresh.refreshAll();
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