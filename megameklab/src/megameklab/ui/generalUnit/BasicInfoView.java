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
package megameklab.ui.generalUnit;

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
import javax.swing.JLabel;
import javax.swing.JTextField;

import megamek.common.Entity;
import megamek.common.ITechManager;
import megamek.common.ITechnology;
import megamek.common.SimpleTechLevel;
import megamek.common.TechAdvancement;
import megamek.common.util.EncodeControl;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.FactionComboBox;
import megameklab.ui.util.IntRangeTextField;
import megameklab.ui.listeners.BuildListener;
import megameklab.util.CConfig;

/**
 * Basic information common to all unit types: name, year, tech level.
 * 
 * @author Neoancient
 *
 */
public class BasicInfoView extends BuildView implements ITechManager, ActionListener, FocusListener {
    
    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BuildListener l) {
        if (null != l) {
            listeners.add(l);
        }
    }
    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }
    
    private static final TechAdvancement TA_MIXED_TECH = Entity.getMixedTechAdvancement();
    private static final int CLAN_START = 2807;
    private static final int IS_MIXED_START = TA_MIXED_TECH.getIntroductionDate(false);
    private static final int CLAN_MIXED_START = TA_MIXED_TECH.getIntroductionDate(true);
    
    private static final int BASE_IS = 0;
    private static final int BASE_CLAN = 1;
    private static final int BASE_IS_MIXED = 2;
    private static final int BASE_CLAN_MIXED = 3;
    
    private String[] techBaseNames;
    private TechAdvancement baseTA;
    
    private final JTextField txtChassis = new JTextField(5);
    private final JTextField txtModel = new JTextField(5);
    private final IntRangeTextField txtYear = new IntRangeTextField(3);
    private final FactionComboBox cbFaction = new FactionComboBox();
    private final JLabel lblFaction = createLabel("", labelSize);
    private final JTextField txtSource = new JTextField(3);
    private final CustomComboBox<Integer> cbTechBase = new CustomComboBox<>(i -> String.valueOf(techBaseNames[i]));
    private final JComboBox<SimpleTechLevel> cbTechLevel = new JComboBox<>();
    private final IntRangeTextField txtManualBV = new IntRangeTextField(3);
    
    private int prevYear = 3145;

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
        txtChassis.setToolTipText(resourceMap.getString("BasicInfoView.txtChassis.tooltip")); //$NON-NLS-1$
        add(txtChassis, gbc);
        setFieldSize(txtChassis, controlSize);
        txtChassis.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap.getString("BasicInfoView.txtModel.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtModel.setToolTipText(resourceMap.getString("BasicInfoView.txtModel.tooltip")); //$NON-NLS-1$
        add(txtModel, gbc);
        setFieldSize(txtModel, controlSize);
        txtModel.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel(resourceMap.getString("BasicInfoView.txtYear.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtYear, gbc);
        txtYear.setToolTipText(resourceMap.getString("BasicInfoView.txtYear.tooltip")); //$NON-NLS-1$
        setFieldSize(txtYear, controlSize);
        txtYear.setMaximum(9999);
        txtYear.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lblFaction.setText(resourceMap.getString("BasicInfoView.cbFaction.text")); //$NON-NLS-1$
        add(lblFaction, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        cbFaction.setToolTipText(resourceMap.getString("BasicInfoView.cbFaction.tooltip")); //$NON-NLS-1$
        add(cbFaction, gbc);
        setFieldSize(cbFaction, controlSize);
        cbFaction.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel(resourceMap.getString("BasicInfoView.txtSource.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 4;
        setFieldSize(txtSource, controlSize);
        txtSource.setToolTipText(resourceMap.getString("BasicInfoView.txtSource.tooltip")); //$NON-NLS-1$
        add(txtSource, gbc);
        txtSource.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createLabel(resourceMap.getString("BasicInfoView.cbTechBase.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 5;
        setFieldSize(cbTechBase, controlSize);
        cbTechBase.setToolTipText(resourceMap.getString("BasicInfoView.cbTechBase.tooltip")); //$NON-NLS-1$
        add(cbTechBase, gbc);
        cbTechBase.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(createLabel(resourceMap.getString("BasicInfoView.cbTechLevel.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 6;
        setFieldSize(cbTechLevel, controlSize);
        cbTechLevel.setToolTipText(resourceMap.getString("BasicInfoView.cbTechLevel.tooltip")); //$NON-NLS-1$
        add(cbTechLevel, gbc);
        cbTechLevel.addActionListener(this);
        refreshTechBase();

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(createLabel(resourceMap.getString("BasicInfoView.txtManualBV.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 7;
        setFieldSize(txtManualBV, controlSize);
        txtManualBV.setToolTipText(resourceMap.getString("BasicInfoView.txtManualBV.tooltip")); //$NON-NLS-1$
        add(txtManualBV, gbc);
        txtManualBV.addFocusListener(this);
        
        if (CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            lblFaction.setVisible(true);
            cbFaction.setVisible(true);
        } else {
            lblFaction.setVisible(false);
            cbFaction.setVisible(false);
        }
    }

    public void setFromEntity(Entity en) {
        boolean useTP = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        baseTA = en.getConstructionTechAdvancement();
        txtYear.setMinimum(Math.max(baseTA.getIntroductionDate(), ITechnology.DATE_PS));
        refreshTechBase();
        setChassis(en.getChassis());
        setModel(en.getModel());
        setYear(Math.max(en.getYear(), txtYear.getMinimum()));
        setSource(en.getSource());
        cbTechBase.removeActionListener(this);
        setTechBase(en.isClan(), en.isMixedTech());
        cbTechBase.addActionListener(this);
        cbTechLevel.removeActionListener(this);
        SimpleTechLevel lvl = useTP? en.getSimpleLevel(getGameYear()) : en.getStaticTechLevel();
        setTechLevel(SimpleTechLevel.max(lvl,
                SimpleTechLevel.convertCompoundToSimple(en.getTechLevel())));
        cbTechLevel.addActionListener(this);
        if (en.getManualBV() >= 0) {
            setManualBV(en.getManualBV());
        }
        
        refreshFaction();
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

    @Override
    public int getTechIntroYear() {
        return txtYear.getIntVal();
    }
    
    public void setYear(int year) {
        txtYear.setIntVal(year);
        refreshTechBase();
    }
    
    @Override
    public int getTechFaction() {
        if (!CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            return ITechnology.F_NONE;
        }
        Integer retVal = (Integer) cbFaction.getSelectedItem();
        if (retVal == null) {
            return ITechnology.F_NONE;
        }
        return retVal;
    }
    
    public void setTechFaction(int techFaction) {
        cbFaction.setSelectedItem(techFaction);
    }

    @Override
    public int getGameYear() {
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            return Math.max(getTechIntroYear(), CConfig.getIntParam(CConfig.TECH_YEAR));
        }
        return getTechIntroYear();
    }
    
    public String getSource() {
        return txtSource.getText();
    }
    
    public void setSource(String source) {
        txtSource.setText(source);
    }

    /**
     * @return the entered manual BV value or -1 if it can't be parsed.
     */
    public int getManualBV() {
        return txtManualBV.getIntVal(-1);
    }
    
    public void setManualBV(int bv) {
        if (bv >= 0) {
            txtManualBV.setIntVal(bv);
        } else {
            txtManualBV.setText("");
        }
    }
    
    @Override
    public boolean useClanTechBase() {
        if (getTechIntroYear() < CLAN_START) {
            return false;
        } else {
            Integer selected = (Integer) cbTechBase.getSelectedItem();
            return ((null != selected)
                    && ((selected == BASE_CLAN) || (selected == BASE_CLAN_MIXED)));
        }
    }
    
    @Override
    public boolean useMixedTech() {
        if (getTechIntroYear() < CLAN_START) {
            return false;
        }
        Integer selected = (Integer) cbTechBase.getSelectedItem();
        return ((null != selected)
                && ((selected == BASE_IS_MIXED) || (selected == BASE_CLAN_MIXED)));
    }
    
    public void setTechBase(boolean clan, boolean mixed) {
        int item = 0;
        if (clan && (getTechIntroYear() > CLAN_START)) {
            item++;
        }
        if (mixed) {
            item += 2;
        }
        cbTechBase.setSelectedItem(item);
        refreshFaction();
    }
    
    @Override
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
        Integer prev = (Integer) cbTechBase.getSelectedItem();
        cbTechBase.removeActionListener(this);
        cbTechBase.removeAllItems();
        // IS is available to anything that doesn't require a Clan tech base (e.g. QuadVee, ProtoMech).
        // Clan is available to anything that doesn't require an IS tech base, is built after the Clans
        // are formed, and not built by an IS faction before the Clan invasion.
        final boolean clanFaction = (getTechFaction() >= ITechnology.F_CLAN) || (getTechFaction() < 0);
        final boolean sphereAvailable = baseTA.getTechBase() != ITechnology.TECH_BASE_CLAN;
        final boolean clanAvailable = (getTechIntroYear() >= CLAN_START)
                && (baseTA.getTechBase() != ITechnology.TECH_BASE_IS)
                && (clanFaction || (getTechIntroYear() >= IS_MIXED_START));
        final boolean mixedTechAvailable = (getTechIntroYear() >= IS_MIXED_START)
                || ((getTechIntroYear() >= CLAN_MIXED_START) && clanFaction);
        if (sphereAvailable) {
            cbTechBase.addItem(BASE_IS);
        }
        if (clanAvailable) {
            cbTechBase.addItem(BASE_CLAN);
        }
        if (sphereAvailable && mixedTechAvailable) {
            cbTechBase.addItem(BASE_IS_MIXED);
        }
        if (clanAvailable && mixedTechAvailable) {
            cbTechBase.addItem(BASE_CLAN_MIXED);
        }
        if (null != prev) {
            cbTechBase.setSelectedItem(prev);
        }
        cbTechBase.addActionListener(this);
        if (cbTechBase.getSelectedItem() == null || !cbTechBase.getSelectedItem().equals(prev)) {
            cbTechBase.setSelectedItem(0);
        }
        refreshTechLevel();
        refreshFaction();
    }
    
    private void refreshTechLevel() {
        SimpleTechLevel prev = getTechLevel();
        cbTechLevel.removeActionListener(this);
        cbTechLevel.removeAllItems();
        SimpleTechLevel baseLevel;
        if (CConfig.getBooleanParam(CConfig.TECH_PROGRESSION)) {
            baseLevel = baseTA.getSimpleLevel(getGameYear());
            if (useMixedTech() && (baseLevel.ordinal() <
                    Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear()).ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear());
            }
        } else {
            baseLevel = baseTA.getStaticTechLevel();
            if (useMixedTech()
                    && (baseLevel.ordinal() < Entity.getMixedTechAdvancement().getStaticTechLevel().ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getStaticTechLevel();
            }
        }
        if (!useClanTechBase() && SimpleTechLevel.INTRO.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.INTRO);
        }
        if (SimpleTechLevel.STANDARD.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.STANDARD);
        }
        if (SimpleTechLevel.ADVANCED.ordinal() >= baseLevel.ordinal()) {
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
    
    private void refreshFaction() {
        if (CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            cbFaction.removeActionListener(this);
            Integer prevFaction = (Integer) cbFaction.getSelectedItem();
            cbFaction.refresh(getTechIntroYear());
            cbFaction.setSelectedItem(prevFaction);
            cbFaction.addActionListener(this);
            if (cbFaction.getSelectedIndex() < 0) {
                cbFaction.setSelectedIndex(0);
            }
            lblFaction.setVisible(true);
            cbFaction.setVisible(true);
        } else {
            lblFaction.setVisible(false);
            cbFaction.setVisible(false);
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(txtYear)) {
            prevYear = getTechIntroYear();
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
                int year = Math.max(getTechIntroYear(), baseTA.getIntroductionDate(useClanTechBase()));
                listeners.forEach(l -> l.yearChanged(year));
                prevYear = year;
            } catch (NumberFormatException ignored) {
                // If text is not a legal integer value, reset to the previous value
            } finally {
                setYear(prevYear);
            }
        } else if (e.getSource() == txtSource) {
            listeners.forEach(l -> l.sourceChanged(getSource()));
        } else if (e.getSource() == txtManualBV) {
            int manualBv = getManualBV();
            txtManualBV.setText((manualBv > 0) ? String.valueOf(manualBv) : "");
            listeners.forEach(l -> l.manualBVChanged(manualBv));
        }
        listeners.forEach(BuildListener::refreshSummary);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbFaction) {
            listeners.forEach(BuildListener::updateTechLevel);
            refreshTechBase();
        } else if (e.getSource() == cbTechBase) {
            listeners.forEach(l -> l.techBaseChanged(useClanTechBase(), useMixedTech()));
            refreshTechLevel();
        } else if (e.getSource() == cbTechLevel) {
            listeners.forEach(l -> l.techLevelChanged(getTechLevel()));
        }
        listeners.forEach(BuildListener::refreshSummary);
    }
    
    @Override
    public boolean unofficialNoYear() {
        return CConfig.getBooleanParam(CConfig.TECH_UNOFFICAL_NO_YEAR);
    }
    
    @Override
    public boolean useVariableTechLevel() {
        return CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
    }
    
    @Override
    public boolean showExtinct() {
        return CConfig.getBooleanParam(CConfig.TECH_EXTINCT);
    }
    
}
