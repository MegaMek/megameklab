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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.views.BuildView;
import megameklab.com.ui.views.CriticalView;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;

public class BuildTab extends JPanel implements ActionListener{

    /**
     * 
     */
    private static final long serialVersionUID = -6756011847500605874L;

    Mech unit;
    RefreshListener refresh = null;
    private CriticalView critView = null;
    private CriticalTableModel critList;
    private BuildView buildView = null;
    
    public BuildTab(Entity unit, EquipmentTab equipment, WeaponTab weapons) {
        this.unit = (Mech) unit;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        critView = new CriticalView(this.unit, true, refresh);
        buildView = new BuildView(this.unit);
        
        this.add(critView);
        this.add(buildView);
        refresh();
    }

    public JPanel availableCritsPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());
        Dimension maxSize = new Dimension();
        
        masterPanel.add(buildView);
        //masterPanel.add(new JLabelDragNDrop());
        
        SpringLayoutHelper.setupSpringGrid(masterPanel, 1);
        maxSize.setSize(300, 5);
        masterPanel.setPreferredSize(maxSize);
        masterPanel.setMinimumSize(maxSize);
        masterPanel.setMaximumSize(maxSize);
        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
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

    }

    public void removeAllActionListeners() {
    }

    public void addAllActionListeners() {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        critView.updateRefresh(refresh);
    }
    
    public void addCrit(EquipmentType eq){
        critList.addCrit(eq);
    }

}