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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Mech;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.views.EquipmentView;

public class EquipmentTab extends JPanel implements ActionListener, ChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3978675469713289404L;

    private EquipmentView equipment;
    private RefreshListener refresh;
    
    public EquipmentTab(Mech unit){
        this.equipment = new EquipmentView(unit);
        this.setLayout(new SpringLayout());
        this.add(equipment);
        SpringLayoutHelper.setupSpringGrid(this,1);
        
        refresh();
    }

    public void refresh() {
        removeAllListeners();
        equipment.refresh();
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        equipment.addRefreshedListener(l);
    }


    private void addAllListeners() {
    }
    
    private void removeAllListeners() {
    }
    
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void stateChanged(ChangeEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}