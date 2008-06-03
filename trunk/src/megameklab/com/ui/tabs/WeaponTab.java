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

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megamek.common.Mech;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;
import megameklab.com.ui.views.WeaponView;

public class WeaponTab extends JPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 3978675469713289404L;
    private WeaponView weapon;
    private RefreshListener refresh;
    
    public WeaponTab(Mech unit){
        this.weapon = new WeaponView(unit);
        this.setLayout(new SpringLayout());
        this.add(weapon);
        SpringLayoutHelper.setupSpringGrid(this,1);
        
        refresh();
    }

    public void refresh() {
        weapon.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        weapon.addRefreshedListener(refresh);
    }

    public WeaponView getView(){
        return weapon;
    }
    
}