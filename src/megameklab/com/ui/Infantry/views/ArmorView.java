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

package megameklab.com.ui.Infantry.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class ArmorView extends IView implements ActionListener, ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private RefreshListener refresh = null;
    JCheckBox chEncumber = new JCheckBox();
    JCheckBox chSpaceSuit = new JCheckBox();
    JCheckBox chDEST = new JCheckBox();
    JCheckBox chSneakCamo = new JCheckBox();
    JCheckBox chSneakIR = new JCheckBox();
    JCheckBox chSneakECM = new JCheckBox();
    private JSpinner armorValue = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 2.0, 1));
    private Dimension maxSize = new Dimension();

    
    public ArmorView(Infantry unit) {
        super(unit);
        setUpPanels();
        refresh();
    }
    
    private void setUpPanels() {
        JPanel divisorPanel = new JPanel();
        JPanel choicePanel = new JPanel(new GridLayout(3,2));
        maxSize.setSize(110, 20);

        divisorPanel.add(createLabel("Damage Divisor:", maxSize));
        divisorPanel.add(armorValue);
        JFormattedTextField tf = ((JSpinner.DefaultEditor)armorValue.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        chEncumber.setText("Encumbering");
        chSpaceSuit.setText("Space Suit");
        chDEST.setText("DEST");
        chSneakCamo.setText("Sneak (CAMO)");
        chSneakIR.setText("Sneak (IR)");
        chSneakECM.setText("Sneak (ECM)");

        choicePanel.add(chEncumber);
        choicePanel.add(chSpaceSuit);
        choicePanel.add(chDEST);
        choicePanel.add(chSneakCamo);
        choicePanel.add(chSneakIR);
        choicePanel.add(chSneakECM);

        setLayout(new BorderLayout());
        add(divisorPanel, BorderLayout.NORTH);
        add(choicePanel, BorderLayout.CENTER);
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
        armorValue.setValue((double)getInfantry().getDamageDivisor());
        chEncumber.setSelected(getInfantry().isArmorEncumbering());
        chSpaceSuit.setSelected(getInfantry().hasSpaceSuit());
        chDEST.setSelected(getInfantry().hasDEST());
        chSneakCamo.setSelected(getInfantry().hasSneakCamo());
        chSneakIR.setSelected(getInfantry().hasSneakIR());
        chSneakECM.setSelected(getInfantry().hasSneakECM());
        if(unit.getTechLevel() < TechConstants.T_TW_ALL) {
            armorValue.setEnabled(false);
            chEncumber.setEnabled(false);
            chSpaceSuit.setEnabled(false);
            chDEST.setEnabled(false);
            chSneakCamo.setEnabled(false);
            chSneakIR.setEnabled(false);
            chSneakECM.setEnabled(false);
        } else {            
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
            refresh.refreshPreview();
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