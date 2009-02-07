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

package megameklab.com.ui.Mek.tabs;

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
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megameklab.com.ui.Mek.views.BuildView;
import megameklab.com.ui.Mek.views.CriticalView;
import megameklab.com.util.CConfig;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class BuildTab extends ITab implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    RefreshListener refresh = null;
    private CriticalView critView = null;
    private CriticalTableModel critList;
    private BuildView buildView = null;
    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private JButton autoFillButton = new JButton("Auto Fill");
    private JButton resetButton = new JButton("Reset");
    private JButton compactButton = new JButton("Compact");

    private String AUTOFILLCOMMAND = "autofillbuttoncommand";
    private String RESETCOMMAND = "resetbuttoncommand";
    private String COMPACTCOMMAND = "compactbuttoncommand";

    public BuildTab(Mech unit, EquipmentTab equipment, WeaponTab weapons, CConfig config) {
        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        critView = new CriticalView(this.unit, true, refresh, config);
        buildView = new BuildView(this.unit);

        mainPanel.add(buildView);

        autoFillButton.setMnemonic('A');
        autoFillButton.setActionCommand(AUTOFILLCOMMAND);
        resetButton.setMnemonic('R');
        resetButton.setActionCommand(RESETCOMMAND);
        compactButton.setMnemonic('C');
        compactButton.setActionCommand(COMPACTCOMMAND);
        buttonPanel.add(autoFillButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(compactButton);

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
        critView.updateMech(unit);
        buildView.updateMech(unit);
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
        } else if (e.getActionCommand().equals(COMPACTCOMMAND)) {
            compactCrits();
        }
    }

    private void autoFillCrits() {

        for (EquipmentType eq : buildView.getTableModel().getCrits()) {
            int externalEngineHS = UnitUtil.getBaseChassisHeatSinks(unit);
            for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {

                if ( eq instanceof MiscType && (eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON))){
                    if ( unit instanceof QuadMech ){
                        continue;
                    }

                    if ( location != Mech.LOC_RARM && location != Mech.LOC_LARM ){
                        continue;
                    }
                }

                int continuousNumberOfCrits = UnitUtil.getHighestContinuousNumberOfCrits(unit, location);
                int critsUsed = UnitUtil.getCritsUsed(unit, eq);
                if ( continuousNumberOfCrits < critsUsed ) {
                    continue;
                }

                Mounted foundMount = null;
                for (Mounted mount : unit.getEquipment()) {
                    if (mount.getLocation() == Entity.LOC_NONE && mount.getType().getInternalName().equals(eq.getInternalName())) {
                        if (UnitUtil.isHeatSink(mount) && externalEngineHS-- > 0) {
                            continue;
                        }
                        foundMount = mount;
                        break;
                    }
                }

                if (foundMount != null ) {
                    try {

                        if (foundMount.getType().isSpreadable() || foundMount.isSplitable() && critsUsed > 1) {
                            for (int count = 0; count < critsUsed; count++) {
                                unit.addEquipment(foundMount, location, false);
                            }
                        } else {
                            unit.addEquipment(foundMount, location, false);
                        }
                        UnitUtil.changeMountStatus(unit, foundMount, location, Entity.LOC_NONE, false);
                        break;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        refresh.refreshAll();

    }

    private void resetCrits() {
        for (Mounted mount : unit.getEquipment()) {
            UnitUtil.removeCriticals(unit, mount);
        }
        for (Mounted mount : unit.getEquipment()) {
            UnitUtil.changeMountStatus(unit, mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
        }
        refresh.refreshAll();
    }

    private void compactCrits() {
        UnitUtil.compactCriticals(unit);
        UnitUtil.reIndexCrits(unit);
        refresh.refreshAll();
    }

    public void removeAllActionListeners() {
        autoFillButton.removeActionListener(this);
        resetButton.removeActionListener(this);
        compactButton.removeActionListener(this);
    }

    public void addAllActionListeners() {
        autoFillButton.addActionListener(this);
        resetButton.addActionListener(this);
        compactButton.addActionListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        critView.updateRefresh(refresh);
    }

    public void addCrit(EquipmentType eq) {
        critList.addCrit(eq);
    }

}