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

package megameklab.com.ui.Mek;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.Mech;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class Header extends ITab implements KeyListener {

    private RefreshListener refresh;
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);

    /**
     *
     */
    private static final long serialVersionUID = -5806920722514383555L;

    public Header(Mech unit) {
        this.unit = unit;
        setMinimumSize(new Dimension(300, 300));
        chassis.setText(unit.getChassis());
        model.setText(unit.getModel());

        chassis.setMaximumSize(new Dimension(100, 10));
        model.setMaximumSize(new Dimension(100, 10));
        setLayout(new SpringLayout());

        setOpaque(false);
        this.add(new JLabel("Chassis:", SwingConstants.TRAILING));
        this.add(chassis);
        this.add(new JLabel("Model:", SwingConstants.TRAILING));
        this.add(model);
        SpringLayoutHelper.setupSpringGrid(this, 4);
        chassis.addKeyListener(this);
        model.addKeyListener(this);

    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        unit.setChassis(chassis.getText().trim());
        unit.setModel(model.getText().trim());
        refresh.refreshHeader();
    }

    public void refresh() {

    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }
}