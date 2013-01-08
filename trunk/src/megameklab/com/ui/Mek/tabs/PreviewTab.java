/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.com.ui.Mek.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.client.ui.GBC;
import megamek.client.ui.swing.MechView;
import megamek.client.ui.swing.MechViewPanel;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megameklab.com.ui.Mek.views.ArmorView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class PreviewTab extends ITab implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7410436201331568734L;

    private MechViewPanel panelMekView;

	public PreviewTab(Mech unit) {
		this.unit = unit;
		this.setLayout(new BorderLayout());
        panelMekView = new MechViewPanel(350, 500);
        add(panelMekView, BorderLayout.CENTER);
        setBackground(Color.WHITE);
        refresh();
	}
	
	public void refresh() {
		boolean populateTextFields = true;
		MechView mechView = null;
        try {
            mechView = new MechView(unit, false);
        } catch (Exception e) {
            e.printStackTrace();
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields && (mechView != null)) {
            panelMekView.setMech(unit);
        } else {
            panelMekView.reset();
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}