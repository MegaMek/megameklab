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

package megameklab.com.ui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SpringLayoutHelper;

import megamek.common.Mech;

public class Header extends JPanel implements KeyListener{

    private Mech unit;
    private RefreshListener refresh;
    private JTextField chassie = new JTextField(5);
    private JTextField model = new JTextField(5);
    
    /**
     * 
     */
    private static final long serialVersionUID = -5806920722514383555L;

    
    public Header(Mech unit){
        this.unit = unit;
        this.setMinimumSize(new Dimension(300,300));
        chassie.setText(unit.getChassis());
        model.setText(unit.getModel());
        
        chassie.setMaximumSize(new Dimension(100,10));
        model.setMaximumSize(new Dimension(100,10));
        this.setLayout(new SpringLayout());
        this.add(new JLabel("Chassie:",JLabel.TRAILING));
        this.add(chassie);
        this.add(new JLabel("Model:",JLabel.TRAILING));
        this.add(model);
        SpringLayoutHelper.setupSpringGrid(this, 4);
        chassie.addKeyListener(this);
        model.addKeyListener(this);

    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        unit.setChassis(chassie.getText().trim());
        unit.setModel(model.getText().trim());
        refresh.refreshHeader();
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }
}