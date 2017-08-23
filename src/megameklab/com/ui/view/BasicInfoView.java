/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import megamek.common.Entity;
import megamek.common.ITechnology;
import megamek.common.SimpleTechLevel;
import megamek.common.TechAdvancement;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.util.IntRangeTextField;

/**
 * Basic information common to all unit types: name, year, tech level.
 * 
 * @author Neoancient
 *
 */
public class BasicInfoView extends MainUIView implements ITechManager, ActionListener, FocusListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6831478201489228066L;

    public interface BasicInfoListener {
        void refreshSummary();
        void chassisChanged(String chassis);
        void modelChanged(String model);
        void yearChanged(int year);
        void sourceChanged(String source);
        void techBaseChanged(boolean clan, boolean mixed);
        void techLevelChanged(SimpleTechLevel techLevel);
        void manualBVChanged(int manualBV);
    }
    
    private List<BasicInfoListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BasicInfoListener l) {
        if (null != l) {
            listeners.add(l);
        }
    }
    public void removeListener(BasicInfoListener l) {
        listeners.remove(l);
    }
    
    private static final TechAdvancement TA_MIXED_TECH = Entity.getMixedTechAdvancement();
    private static final int CLAN_START = 2807;
    private static final int IS_MIXED_START = TA_MIXED_TECH.getIntroductionDate(false);
    private static final int CLAN_MIXED_START = TA_MIXED_TECH.getIntroductionDate(true);
    
    private static final int TECH_BASE_IS           = 0;
    private static final int TECH_BASE_CLAN         = 1;
    private static final int TECH_BASE_IS_MIXED     = 2;
    private static final int TECH_BASE_CLAN_MIXED   = 3;
    
    private String[] techBaseNames;
    private TechAdvancement baseTA;
    
    private JTextField txtChassis = new JTextField(5);
    private JTextField txtModel = new JTextField(5);
    private IntRangeTextField txtYear = new IntRangeTextField(3);
    private JTextField txtSource = new JTextField(3);
    private CustomComboBox<Integer> cbTechBase = new CustomComboBox<>(i -> String.valueOf(techBaseNames[i]));
    private JComboBox<SimpleTechLevel> cbTechLevel = new JComboBox<>();
    private IntRangeTextField txtManualBV = new IntRangeTextField(3);
    
    private int prevYear = 3067;
    private int prevBV = 0;
    
    public BasicInfoView(TechAdvancement baseTA) {
        this.baseTA = baseTA;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        techBaseNames = resourceMap.getString("BasicInfoView.cbTechBase.values").split(",");
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        add(createLabel(resourceMap.getString("BasicInfoView.txtChassis.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtChassis, gbc);
        setFieldSize(txtChassis, controlSize);
        txtChassis.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap.getString("BasicInfoView.txtModel.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtModel, gbc);
        setFieldSize(txtModel, controlSize);
        txtModel.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel(resourceMap.getString("BasicInfoView.txtYear.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtYear, gbc);
        setFieldSize(txtYear, controlSize);
        txtYear.setMaximum(9999);
        txtYear.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createLabel(resourceMap.getString("BasicInfoView.txtSource.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 3;
        setFieldSize(txtSource, controlSize);
        add(txtSource, gbc);
        txtSource.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel(resourceMap.getString("BasicInfoView.cbTechBase.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 4;
        setFieldSize(cbTechBase, controlSize);
        add(cbTechBase, gbc);
        refreshTechBase();
        cbTechBase.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createLabel(resourceMap.getString("BasicInfoView.cbTechLevel.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 5;
        setFieldSize(cbTechLevel, controlSize);
        add(cbTechLevel, gbc);
        cbTechLevel.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(createLabel(resourceMap.getString("BasicInfoView.txtManualBV.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 6;
        setFieldSize(txtManualBV, controlSize);
        add(txtManualBV, gbc);
        txtManualBV.addFocusListener(this);
        
    }

    public void setFromEntity(Entity en) {
        baseTA = en.getConstructionTechAdvancement();
        txtYear.setMinimum(baseTA.getIntroductionDate());
        refreshTechBase();
        setChassis(en.getChassis());
        setModel(en.getModel());
        setYear(en.getYear());
        setSource(en.getSource());
        setTechBase(en.isClan(), en.isMixedTech());
        setTechLevel(SimpleTechLevel.max(en.getStaticTechLevel(),
                SimpleTechLevel.convertCompoundToSimple(en.getTechLevel())));
        if (en.getManualBV() >= 0) {
            setManualBV(en.getManualBV());
        }
    }
    
    public void setAsCustomization() {
        txtChassis.setEditable(false);
        txtChassis.setEnabled(false);
        txtYear.setEditable(false);
        txtYear.setEnabled(false);
    }
    
    public String getChassis() {
        return txtChassis.getText();
    }
    
    public void setChassis(String chassis) {
        txtChassis.setText(chassis);
    }

    public String getModel() {
        return txtModel.getText();
    }
    
    public void setModel(String model) {
        txtModel.setText(model);
    }

    public int getYear() {
        return txtYear.getIntVal();
    }
    
    public void setYear(int year) {
        txtYear.setIntVal(year);
        refreshTechBase();
    }

    //TODO: allow setting of tech year apart from origin for era-based option
    @Override
    public int getTechYear() {
        return getYear();
    }

    public String getSource() {
        return txtSource.getText();
    }
    
    public void setSource(String source) {
        txtSource.setText(source);
    }

    public int getManualBV() {
        return txtManualBV.getIntVal();
    }
    
    public void setManualBV(int bv) {
        txtManualBV.setIntVal(bv);
    }
    
    @Override
    public boolean isClan() {
        if (getYear() < CLAN_START) {
            return false;
        } else {
            Integer selected = (Integer)cbTechBase.getSelectedItem();
            return ((null != selected)
                    && ((selected == TECH_BASE_CLAN) || (selected == TECH_BASE_CLAN_MIXED)));
        }
    }
    
    @Override
    public boolean isMixedTech() {
        if (getYear() < CLAN_START) {
            return false;
        }
        Integer selected = (Integer)cbTechBase.getSelectedItem();
        return ((null != selected)
                && ((selected == TECH_BASE_IS_MIXED) || (selected == TECH_BASE_CLAN_MIXED)));
    }
    
    public void setTechBase(boolean clan, boolean mixed) {
        int item = 0;
        if (clan && (getYear() > CLAN_START)) {
            item++;
        }
        if (mixed) {
            item++;
        }
        cbTechBase.setSelectedItem(item);
    }
    
    public SimpleTechLevel getTechLevel() {
        if (cbTechLevel.getSelectedItem() == null) {
            return SimpleTechLevel.STANDARD;
        }
        return (SimpleTechLevel)cbTechLevel.getSelectedItem();
    }
    
    public void setTechLevel(SimpleTechLevel level) {
        cbTechLevel.setSelectedItem(level);
    }
    
    private void refreshTechBase() {
        Integer prev = (Integer)cbTechBase.getSelectedItem();
        cbTechBase.removeActionListener(this);
        cbTechBase.removeAllItems();
        if (baseTA.getTechBase() != ITechnology.TECH_BASE_CLAN) {
            cbTechBase.addItem(TECH_BASE_IS);
        }
        if ((getYear() >= CLAN_START) && (baseTA.getTechBase() != ITechnology.TECH_BASE_IS)) {
            cbTechBase.addItem(TECH_BASE_CLAN);
        }
        if ((getYear() >= IS_MIXED_START) && (baseTA.getTechBase() != ITechnology.TECH_BASE_CLAN)) {
            cbTechBase.addItem(TECH_BASE_IS_MIXED);
        }
        if ((getYear() >= CLAN_MIXED_START) && (baseTA.getTechBase() != ITechnology.TECH_BASE_IS)) {
            cbTechBase.addItem(TECH_BASE_CLAN_MIXED);
        }
        if (null != prev) {
            cbTechBase.setSelectedItem(prev);
        }
        cbTechBase.addActionListener(this);
        if (cbTechBase.getSelectedItem() == null || !cbTechBase.getSelectedItem().equals(prev)) {
            cbTechBase.setSelectedItem(0);
        }
        refreshTechLevel();
    }
    
    private void refreshTechLevel() {
        SimpleTechLevel prev = getTechLevel();
        cbTechLevel.removeActionListener(this);
        cbTechLevel.removeAllItems();
        if (!isClan() && !isMixedTech() && SimpleTechLevel.INTRO.ordinal() >= baseTA.getStaticTechLevel().ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.INTRO);
        }
        if (!isMixedTech() && SimpleTechLevel.STANDARD.ordinal() >= baseTA.getStaticTechLevel().ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.STANDARD);
        }
        if (SimpleTechLevel.ADVANCED.ordinal() >= baseTA.getStaticTechLevel().ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.ADVANCED);
        }
        cbTechLevel.addItem(SimpleTechLevel.EXPERIMENTAL);
        cbTechLevel.addItem(SimpleTechLevel.UNOFFICIAL);
        cbTechLevel.setSelectedItem(prev);
        cbTechLevel.addActionListener(this);
        if (cbTechLevel.getSelectedItem() == null || cbTechLevel.getSelectedItem() != prev) {
            cbTechLevel.setSelectedIndex(0);
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(txtYear)) {
            prevYear = getYear();
        } else if (e.getSource().equals(txtManualBV)) {
            prevBV = getManualBV();
        }
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtChassis) {
            listeners.forEach(l -> l.chassisChanged(getChassis()));
        } else if (e.getSource() == txtModel) {
            listeners.forEach(l -> l.modelChanged(getModel()));
        } else if (e.getSource() == txtYear) {
            try {
                int year = Math.max(getYear(), baseTA.getIntroductionDate(isClan()));
                listeners.forEach(l -> l.yearChanged(year));
                prevYear = year;
            } catch (NumberFormatException ex) {
            } finally {
                setYear(prevYear);
            }
        } else if (e.getSource() == txtSource) {
            listeners.forEach(l -> l.sourceChanged(getSource()));
        } else if (e.getSource() == txtManualBV) {
            try {
                int bv = getManualBV();
                listeners.forEach(l -> l.yearChanged(bv));
            } catch (NumberFormatException ex) {
                setManualBV(prevBV);
            }
        }
        listeners.forEach(l -> l.refreshSummary());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbTechBase) {
            cbTechBase.removeActionListener(this);
            listeners.forEach(l -> l.techBaseChanged(isClan(), isMixedTech()));
            refreshTechLevel();
            cbTechBase.addActionListener(this);
        } else if (e.getSource() == cbTechLevel) {
            cbTechLevel.removeActionListener(this);
            listeners.forEach(l -> l.techLevelChanged(getTechLevel()));
            cbTechLevel.addActionListener(this);
        }
        listeners.forEach(l -> l.refreshSummary());
    }
    
}
