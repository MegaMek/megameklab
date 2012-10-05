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

package megameklab.com.ui.Infantry.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.Messages;
import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.TechConstants;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class ArmorTab extends ITab implements ActionListener, ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private RefreshListener refresh = null;
    private JPanel basicPanel;
    JCheckBox chEncumber = new JCheckBox();
    JCheckBox chSpaceSuit = new JCheckBox();
    JCheckBox chDEST = new JCheckBox();
    JCheckBox chSneakCamo = new JCheckBox();
    JCheckBox chSneakIR = new JCheckBox();
    JCheckBox chSneakECM = new JCheckBox();
    private JSpinner armorValue = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 2.0, 1));
    private Dimension maxSize = new Dimension();

    
    public ArmorTab(Infantry unit) {

        this.unit = unit;

        setLayout(new SpringLayout());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(basicPanel());
        refresh();
    }
    
    private JPanel basicPanel() {
    	basicPanel = new JPanel(new SpringLayout());
        maxSize.setSize(110, 20);

        basicPanel.add(createLabel("Damage Divisor:", maxSize));
        basicPanel.add(armorValue);
        JFormattedTextField tf = ((JSpinner.DefaultEditor)armorValue.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);

        basicPanel.add(createLabel("Encumbering:", maxSize));
        basicPanel.add(chEncumber);
        basicPanel.add(createLabel("Space Suit:", maxSize));
        basicPanel.add(chSpaceSuit);
        basicPanel.add(createLabel("DEST:", maxSize));
        basicPanel.add(chDEST);
        basicPanel.add(createLabel("Sneak (Camo):", maxSize));
        basicPanel.add(chSneakCamo);
        basicPanel.add(createLabel("Sneak (IR):", maxSize));
        basicPanel.add(chSneakIR);
        basicPanel.add(createLabel("Sneak (ECM):", maxSize));
        basicPanel.add(chSneakECM);

        setFieldSize(armorValue, maxSize);
        setFieldSize(chEncumber, maxSize);
        setFieldSize(chSpaceSuit, maxSize);
        setFieldSize(chDEST, maxSize);
        setFieldSize(chSneakCamo, maxSize);
        setFieldSize(chSneakIR, maxSize);
        setFieldSize(chSneakECM, maxSize);
      
        SpringLayoutHelper.setupSpringGrid(basicPanel, 2);
        return basicPanel;
    }
    
    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, maxSize);
        return label;
    }
	
	public void setFieldSize(JComponent box, Dimension maxSize) {
		box.setPreferredSize(maxSize);
		box.setMaximumSize(maxSize);
		box.setMinimumSize(maxSize);
	}

    public void refresh() {
        removeAllListeners();
        if(unit.getTechLevel() < TechConstants.T_TW_ALL) {
        	armorValue.setValue(1);
	        chEncumber.setSelected(false);
	        chSpaceSuit.setSelected(false);
	        chDEST.setSelected(false);
	        chSneakCamo.setSelected(false);
	        chSneakIR.setSelected(false);
	        chSneakECM.setSelected(false);
	        armorValue.setEnabled(false);
	        chEncumber.setEnabled(false);
	        chSpaceSuit.setEnabled(false);
	        chDEST.setEnabled(false);
	        chSneakCamo.setEnabled(false);
	        chSneakIR.setEnabled(false);
	        chSneakECM.setEnabled(false);
        } else {
	        armorValue.setValue((double)getInfantry().getDamageDivisor());
	        chEncumber.setSelected(getInfantry().isArmorEncumbering());
	        chSpaceSuit.setSelected(getInfantry().hasSpaceSuit());
	        chDEST.setSelected(getInfantry().hasDEST());
	        chSneakCamo.setSelected(getInfantry().hasSneakCamo());
	        chSneakIR.setSelected(getInfantry().hasSneakIR());
	        chSneakECM.setSelected(getInfantry().hasSneakECM());
	        armorValue.setEnabled(true);
	        chEncumber.setEnabled(true);
	        chSpaceSuit.setEnabled(true);
	        chDEST.setEnabled(true);
	        chSneakCamo.setEnabled(true);
	        chSneakIR.setEnabled(true);
	        chSneakECM.setEnabled(true);
        }
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource().equals(chEncumber)) {
            getInfantry().setArmorEncumbering(chEncumber.isSelected());
        } 
        else if (arg0.getSource().equals(chSpaceSuit)) {
            getInfantry().setSpaceSuit(chSpaceSuit.isSelected());
        } 
        else if (arg0.getSource().equals(chDEST)) {
            getInfantry().setDEST(chDEST.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakCamo)) {
            getInfantry().setSneakCamo(chSneakCamo.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakIR)) {
            getInfantry().setSneakIR(chSneakIR.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakECM)) {
            getInfantry().setSneakECM(chSneakECM.isSelected());
        } 
        addAllListeners();
        if (refresh != null) {
            refresh.refreshStatus();
        }
    }

    

    private void addAllListeners() {
        chEncumber.addActionListener(this);
        chSpaceSuit.addActionListener(this);
        chDEST.addActionListener(this);
        chSneakCamo.addActionListener(this);
        chSneakIR.addActionListener(this);
        chSneakECM.addActionListener(this);
        armorValue.addChangeListener(this);
    }

    private void removeAllListeners() {
        chEncumber.removeActionListener(this);
        chSpaceSuit.removeActionListener(this);
        chDEST.removeActionListener(this);
        chSneakCamo.removeActionListener(this);
        chSneakIR.removeActionListener(this);
        chSneakECM.removeActionListener(this);
        armorValue.removeChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        double value = (Double) field.getModel().getValue();      
        getInfantry().setDamageDivisor(value);
        if (refresh != null) {
            refresh.refreshStatus();
        }
        refresh();
    }
}