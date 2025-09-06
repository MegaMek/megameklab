/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.mek;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import megamek.client.ui.util.UIUtil;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class BMBuildTab extends ITab {

    private RefreshListener refresh = null;
    private final BMCriticalView critView;

    public BMBuildView getBuildView() {
        return buildView;
    }

    private final BMBuildView buildView;
    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Tabs");

    private final JToggleButton autoFillUnHitTables = new JToggleButton(resources.getString(
          "BuildTab.autoFillUnhitTables.text"));
    private final JToggleButton autoCompact = new JToggleButton(resources.getString("BuildTab.autoCompact.text"));
    private final JToggleButton autoSort = new JToggleButton(resources.getString("BuildTab.autoSort.text"));

    public BMBuildTab(EntitySource eSource) {
        super(eSource);
        autoFillUnHitTables.setSelected(CConfig.getBooleanParam(CConfig.MEK_AUTOFILL));
        autoSort.setSelected(CConfig.getBooleanParam(CConfig.MEK_AUTO_SORT));
        autoCompact.setSelected(CConfig.getBooleanParam(CConfig.MEK_AUTO_COMPACT));
        critView = new BMCriticalView(eSource, refresh);
        buildView = new BMBuildView(eSource, refresh, critView);

        Box leftSide = Box.createVerticalBox();
        leftSide.add(createButtonPanel());
        leftSide.add(critView);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(leftSide);
        add(buildView);
        refresh();
    }

    private JComponent createButtonPanel() {
        autoFillUnHitTables.addActionListener(e -> refresh());
        autoFillUnHitTables.setToolTipText(resources.getString("BuildTab.autoFillUnhitTables.tooltip"));
        autoCompact.addActionListener(e -> refresh());
        autoCompact.setToolTipText(resources.getString("BuildTab.autoCompact.tooltip"));
        autoSort.addActionListener(e -> refresh());
        autoSort.addActionListener(e -> autoCompact.setEnabled(!autoSort.isSelected()));
        autoSort.setToolTipText(resources.getString("BuildTab.autoSort.tooltip"));

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

        JPanel critBlocks = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.LEFT));
        critBlocks.setOpaque(false);
        critBlocks.add(autoFillUnHitTables);
        critBlocks.add(autoCompact);
        critBlocks.add(autoSort);
        critBlocks.add(Box.createHorizontalStrut(20));
        critBlocks.add(fillButton);
        critBlocks.add(compactButton);
        critBlocks.add(sortButton);

        JPanel unallocatedList = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.RIGHT));
        unallocatedList.setOpaque(false);
        unallocatedList.add(resetButton);

        Box buttonPanel = Box.createHorizontalBox();
        buttonPanel.setBackground(UIUtil.alternateTableBGColor());
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
              new LineBorder(getBackground(), 10),
              new EmptyBorder(5, 10, 5, 10)));
        buttonPanel.add(critBlocks);
        buttonPanel.add(unallocatedList);
        return buttonPanel;
    }

    public void refresh() {
        CConfig.setParam(CConfig.MEK_AUTOFILL, Boolean.toString(autoFillUnHitTables.isSelected()));
        CConfig.setParam(CConfig.MEK_AUTO_SORT, Boolean.toString(autoSort.isSelected()));
        CConfig.setParam(CConfig.MEK_AUTO_COMPACT, Boolean.toString(autoCompact.isSelected()));
        autoFillUnHitTables();
        autoCompactCrits();
        autoSortCrits();
        critView.refresh();
        buildView.refresh();
    }

    private void autoFillUnHitTables() {
        if (autoFillUnHitTables.isSelected()) {
            MekUtil.fillInFMU(getMek());
        }
    }

    private void fillInEquipment() {
        MekUtil.fillInAllEquipment(getMek());
        refresh.refreshAll();
    }

    private void resetCrits() {
        UnitUtil.removeAllCriticalSlots(getMek());
        refresh.refreshAll();
    }

    /**
     * Called during refresh when Auto-Compact is activated. Auto-Sorting already results in compacting, therefore this
     * can be skipped when Auto-sorting is activated. It is important that this method does not call a refresh to avoid
     * a loop!
     */
    private void autoCompactCrits() {
        if (autoCompact.isSelected() && !autoSort.isSelected()) {
            MekUtil.compactCriticalSlots(getMek());
        }
    }

    /**
     * Called from the Compact button. Must not be called from within a refresh as this calls a refresh and will result
     * in a loop!
     */
    private void compactCrits() {
        MekUtil.compactCriticalSlots(getMek());
        refresh.refreshAll();
    }

    /**
     * Called from the Sort button. Must not be called from within a refresh as this calls a refresh and will result in
     * a loop!
     */
    private void sortCrits() {
        MekUtil.sortCrits(getMek());
        refresh();
    }

    /**
     * Called during refresh when Auto-Sorting is activated. It is important that this method does not call a refresh to
     * avoid a loop!
     */
    private void autoSortCrits() {
        if (autoSort.isSelected()) {
            MekUtil.sortCrits(getMek());
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
