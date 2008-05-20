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

package megameklab.com.ui.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.TechConstants;
import megameklab.com.ui.util.RefreshListener;

public class EquipmentView extends JPanel implements KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private Mech unit;
    private RefreshListener refresh;
    private JPanel mainPanel = new JPanel();
    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");
    private JComboBox equipmentCombo = new JComboBox();
    private JList equipmentList = new JList();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    
    public EquipmentView (Mech unit){
        this.unit = unit;
    
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        
        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        rightPanel.add(equipmentList);
        rightPanel.add(removeButton);
        rightPanel.add(removeAllButton);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        this.add(mainPanel);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void loadEquipmentCombo(){
        TreeMap<String, EquipmentType>equipmentTypes = new TreeMap<String, EquipmentType>();
        TreeSet<String>equipmentNames = new TreeSet<String>();
        
        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        
        while ( miscTypes.hasMoreElements() ){
            EquipmentType eq = miscTypes.nextElement();
            
            if ( (eq.getTechLevel() == TechConstants.T_ALL 
                    || eq.getTechLevel() <= unit.getTechLevel())
                    && eq instanceof MiscType){
                equipmentTypes.put(eq.getName(),eq);
                equipmentNames.add(eq.getName());
            }
        }
        
        equipmentCombo = new JComboBox(equipmentNames.toArray());

    }

    public void refresh() {
        loadEquipmentCombo();
        
        leftPanel.removeAll();
        leftPanel.add(equipmentCombo);
        leftPanel.add(addButton);
    }
    
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}