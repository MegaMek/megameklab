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

package megameklab.com.ui.tabs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.util.UnitUtil;
import megameklab.com.ui.views.BuildView;
import megameklab.com.ui.views.CriticalView;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;

public class BuildTab extends ITab implements ActionListener{

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
    private String AUTOFILLCOMMAND = "autofillbuttoncommand";
    private String RESETCOMMAND = "resetbuttoncommand";
    

    public BuildTab(Mech unit, EquipmentTab equipment, WeaponTab weapons) {
        this.unit = unit;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        critView = new CriticalView(this.unit, true, refresh);
        buildView = new BuildView(this.unit);

        mainPanel.add(buildView);
        
        autoFillButton.setActionCommand(AUTOFILLCOMMAND);
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
        critView.updateMech(unit);
        buildView.updateMech(unit);
        critView.refresh();
        buildView.refresh();
        addAllActionListeners();
    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, JLabel.TRAILING);

        label.setMaximumSize(maxSize);
        label.setMinimumSize(maxSize);
        label.setPreferredSize(maxSize);

        return label;
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getActionCommand().equals(AUTOFILLCOMMAND) ) {
            autoFillCrits();
        }else if ( e.getActionCommand().equals(RESETCOMMAND) ) {
            resetCrits();
        }
    }

    private void autoFillCrits() {

        for ( int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++ ) {
            for ( EquipmentType eq : buildView.getTableModel().getCrits()) {
                int externalEngineHS = unit.getEngine().integralHeatSinkCapacity();
                Mounted foundMount = null;
                for (Mounted mount : unit.getEquipment()) {
                    if (mount.getLocation() == Entity.LOC_NONE && mount.getType().getInternalName().equals(eq.getName())) {
                        if ( UnitUtil.isHeatSink(mount) && externalEngineHS-- > 0 ){
                            continue;
                        }
                        foundMount = mount;
                        break;
                    }
                }

                if ( foundMount != null && eq.getCriticals(unit) <= unit.getEmptyCriticals(location) ) {
                    try {
                        unit.addEquipment(eq, location, false);
                        UnitUtil.changeMountStatus(unit, foundMount, location, Mech.LOC_NONE, false);
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        refresh.refreshAll();
        
    }
    
    private void resetCrits() {
        for ( Mounted mount : unit.getEquipment() ) {
            UnitUtil.removeCriticals(unit, mount);
            UnitUtil.changeMountStatus(unit,mount, Mech.LOC_NONE, Mech.LOC_NONE, false);
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
    }
    
    public void addCrit(EquipmentType eq){
        critList.addCrit(eq);
    }

}