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
import megamek.common.Mounted;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

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
        fillButton.addActionListener(e -> fillInEquipment());

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
        if (autoFillUnHittables.isSelected()) {
            BMUtils.fillInFMU(getMech());
        }
    }

    private void fillInEquipment() {
        BMUtils.fillInAllEquipment(getMech());
        refresh.refreshAll();
    }

    private void resetCrits() {
        for (Mounted mounted : getMech().getEquipment()) {
            if (!UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
                UnitUtil.removeCriticals(getMech(), mounted);
                BMUtils.clearMountedLocationAndLinked(mounted);
            }
        }
        refresh.refreshAll();
    }

    /**
     * Called during refresh when Auto-Compact is activated. Auto-Sorting already results
     * in compacting, therefore this can be skipped when Auto-sorting is activated.
     * It is important that this method does not call a refresh to avoid a loop!
     */
    private void autoCompactCrits() {
        if (autoCompact.isSelected() && !autoSort.isSelected()) {
            BMUtils.compactCriticals(getMech());
        }
    }

    /**
     * Called from the Compact button. Must not be called from within a refresh as this
     * calls a refresh and will result in a loop!
     */
    private void compactCrits() {
        BMUtils.compactCriticals(getMech());
        refresh.refreshAll();
    }

    /**
     * Called from the Sort button. Must not be called from within a refresh as this
     * calls a refresh and will result in a loop!
     */
    private void sortCrits() {
        BMUtils.sortCrits(getMech());
        refresh();
    }

    /**
     * Called during refresh when Auto-Sorting is activated.
     * It is important that this method does not call a refresh to avoid a loop!
     */
    private void autoSortCrits() {
        if (autoSort.isSelected()) {
            BMUtils.sortCrits(getMech());
        }
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