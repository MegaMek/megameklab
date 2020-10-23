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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.ITechManager;
import megamek.common.Tank;
import megamek.common.TechAdvancement;
import megamek.common.VTOL;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestTank;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.CVBuildListener;

/**
 * Chassis panel for combat vehicles
 * 
 * @author Neoancient
 *
 */
public class CVChassisView extends BuildView implements ActionListener, ChangeListener {
    
    private static final long serialVersionUID = -5860627963911641227L;

    List<CVBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(CVBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(CVBuildListener l) {
        listeners.remove(l);
    }
    
    private final static String CMD_RESET_CHASSIS = "resetChassis"; //$NON-NLS-1$
    
    private final static EntityMovementMode[] MOTIVE_TYPES = {
            EntityMovementMode.TRACKED, EntityMovementMode.WHEELED, EntityMovementMode.HOVER,
            EntityMovementMode.VTOL, EntityMovementMode.WIGE,
            EntityMovementMode.NAVAL, EntityMovementMode.HYDROFOIL, EntityMovementMode.SUBMARINE
    };

    // Engines that can be used by mechs and the order they appear in the combobox
    private final static int[] ENGINE_TYPES = {
            Engine.COMBUSTION_ENGINE, Engine.NORMAL_ENGINE, Engine.XL_ENGINE, Engine.XXL_ENGINE,
            Engine.FUEL_CELL, Engine.LIGHT_ENGINE, Engine.COMPACT_ENGINE, Engine.FISSION
    };
    private final Engine NO_ENGINE = new Engine(0, Engine.NONE, Engine.TANK_ENGINE);

    public final static int TURRET_NONE   = 0;
    public final static int TURRET_SINGLE = 1;
    public final static int TURRET_DUAL   = 2;
    public final static int TURRET_CHIN   = 3;
    
    private final static TechAdvancement TA_DUAL_TURRET = Tank.getDualTurretTA();
    private final static TechAdvancement TA_CHIN_TURRET = VTOL.getChinTurretTA();
    
    private final SpinnerNumberModel spnTonnageModel = new SpinnerNumberModel(20, 1, 100, 1);
    private final SpinnerNumberModel spnTurretWtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnTurret2WtModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private String[] turretNames;
    private final Map<EntityMovementMode,String> motiveNames = new EnumMap<>(EntityMovementMode.class);

    private final JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    private final JCheckBox chkOmni = new JCheckBox();
    private final JCheckBox chkSuperheavy = new JCheckBox();
    private final JCheckBox chkTrailer = new JCheckBox();
    private final JCheckBox chkControlSystems = new JCheckBox();
    private final CustomComboBox<EntityMovementMode> cbMotiveType = new CustomComboBox<>(motiveNames::get);
    private final TechComboBox<Engine> cbEngine = new TechComboBox<>(e -> e.getEngineName()
            .replaceAll("^\\d+ ", "").replace(" [Vehicle]", ""));
    private final CustomComboBox<Integer> cbTurrets = new CustomComboBox<>(i -> turretNames[i]);
    private final JSpinner spnChassisTurretWt = new JSpinner(spnTurretWtModel);
    private final JSpinner spnChassisTurret2Wt = new JSpinner(spnTurret2WtModel);
    private final JSpinner spnExtraSeats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    
    private final List<JComponent> omniComponents = new ArrayList<>();

    private final ITechManager techManager;
    private int engineRating;
    private boolean isTrailer;

    public CVChassisView(ITechManager techManager) {
        super();
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        turretNames = resourceMap.getString("CVChassisView.turrets.values").split(","); //$NON-NLS-1$
        for (EntityMovementMode m : MOTIVE_TYPES) {
            motiveNames.put(m, resourceMap.getString("MovementMode." + m.toString()));
        }
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap.getString("CVChassisView.spnTonnage.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        setFieldSize(spnTonnage, spinnerSize);
        spnTonnage.setToolTipText(resourceMap.getString("CVChassisView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
        chkOmni.setText(resourceMap.getString("CVChassisView.chkOmni.text")); //$NON-NLS-1$
        gbc.gridx = 2;
        chkOmni.setToolTipText(resourceMap.getString("CVChassisView.chkOmni.tooltip")); //$NON-NLS-1$
        add(chkOmni, gbc);
        chkOmni.addActionListener(this);
        
        chkSuperheavy.setText(resourceMap.getString("CVChassisView.chkSuperheavy.text")); //$NON-NLS-1$
        gbc.gridx = 3;
        chkSuperheavy.setToolTipText(resourceMap.getString("CVChassisView.chkSuperheavy.tooltip")); //$NON-NLS-1$
        add(chkSuperheavy, gbc);
        chkSuperheavy.addActionListener(this);
        gbc.gridy++;

        chkTrailer.setText(resourceMap.getString("CVChassisView.chkTrailer.text")); //$NON-NLS-1$
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        chkTrailer.setToolTipText(resourceMap.getString("CVChassisView.chkTrailer.tooltip")); // $NON-NLS-1$
        chkTrailer.setHorizontalAlignment(SwingConstants.RIGHT);
        add(chkTrailer, gbc);
        chkTrailer.addActionListener(this);
        chkControlSystems.setText(resourceMap.getString("CVChassisView.chkControlSystems.text")); //$NON-NLS-1$
        gbc.gridx = 2;
        chkControlSystems.setToolTipText(resourceMap.getString("CVChassisView.chkControlSystems.tooltip")); // $NON-NLS-1$
        add(chkControlSystems, gbc);
        chkControlSystems.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridy++;

        cbMotiveType.setModel(new DefaultComboBoxModel<>(MOTIVE_TYPES));
        gbc.gridx = 0;
        add(createLabel(resourceMap.getString("CVChassisView.cbMotiveType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(cbMotiveType, controlSize);
        cbMotiveType.setToolTipText(resourceMap.getString("CVChassisView.cbMotiveType.tooltip")); //$NON-NLS-1$
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("CVChassisView.cbEngine.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(cbEngine, controlSize);
        cbEngine.setToolTipText(resourceMap.getString("CVChassisView.cbEngine.tooltip")); //$NON-NLS-1$
        add(cbEngine, gbc);
        cbEngine.addActionListener(this);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap.getString("CVChassisView.spnExtraSeats.text"), labelSize), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        setFieldSize(spnExtraSeats, spinnerSize);
        spnExtraSeats.setToolTipText("CVChassisView.spnExtraSeats.tooltip");
        add(spnExtraSeats, gbc);
        spnExtraSeats.addChangeListener(this);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("CVChassisView.cbTurrets.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(cbTurrets, controlSize);
        cbTurrets.setToolTipText(resourceMap.getString("CVChassisView.cbTurrets.tooltip")); //$NON-NLS-1$
        add(cbTurrets, gbc);
        cbTurrets.addActionListener(this);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        JLabel lbl = createLabel(resourceMap.getString("CVChassisView.spnTurretWt.text"), labelSize); //$NON-NLS-1$
        add(lbl, gbc);
        gbc.gridx = 3;
        setFieldSize(spnChassisTurretWt, spinnerSize);
        spnChassisTurretWt.setToolTipText(resourceMap.getString("CVChassisView.spnTurretWt.tooltip")); //$NON-NLS-1$
        add(spnChassisTurretWt, gbc);
        spnChassisTurretWt.addChangeListener(this);
        omniComponents.add(lbl);
        omniComponents.add(spnChassisTurretWt);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        lbl = createLabel(resourceMap.getString("CVChassisView.spnTurret2Wt.text"), labelSize); //$NON-NLS-1$
        add(lbl, gbc);
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        setFieldSize(spnChassisTurret2Wt, spinnerSize);
        spnChassisTurret2Wt.setToolTipText(resourceMap.getString("CVChassisView.spnTurret2Wt.tooltip")); //$NON-NLS-1$
        add(spnChassisTurret2Wt, gbc);
        spnChassisTurret2Wt.addChangeListener(this);
        omniComponents.add(lbl);
        omniComponents.add(spnChassisTurret2Wt);
        gbc.gridy++;

        JButton btnResetChassis = new JButton(resourceMap.getString("CVChassisView.btnResetChassis.text")); //$NON-NLS-1$
        btnResetChassis.setActionCommand(CMD_RESET_CHASSIS);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(btnResetChassis, controlSize);
        btnResetChassis.setToolTipText(resourceMap.getString("CVChassisView.btnResetChassis.tooltip")); //$NON-NLS-1$
        add(btnResetChassis, gbc);
        btnResetChassis.addActionListener(this);
        omniComponents.add(btnResetChassis);
    }
    
    public void setFromEntity(Tank tank) {
        engineRating = tank.hasEngine()? tank.getEngine().getRating() : 0;
        isTrailer = tank.isTrailer();
        refresh();
        setTonnage(tank.getWeight());
        chkOmni.setSelected(tank.isOmni());
        chkSuperheavy.setSelected(tank.isSuperHeavy());
        chkTrailer.setSelected(tank.isTrailer());
        chkControlSystems.setSelected(!tank.hasNoControlSystems());
        cbMotiveType.setSelectedItem(tank.getMovementMode());
        setEngine(tank.getEngine());

        chkTrailer.setEnabled(tank.getMovementMode().equals(EntityMovementMode.WHEELED)
                || tank.getMovementMode().equals(EntityMovementMode.TRACKED));
        chkControlSystems.setEnabled(isTrailer);
        spnExtraSeats.setValue(tank.getExtraCrewSeats());
        if (!tank.hasNoDualTurret()) {
            cbTurrets.setSelectedItem(TURRET_DUAL);
        } else if (!tank.hasNoTurret()) {
            cbTurrets.setSelectedItem((getMovementMode() == EntityMovementMode.VTOL)?
                    TURRET_CHIN : TURRET_SINGLE);
        } else {
            cbTurrets.setSelectedItem(TURRET_NONE);
        }
        spnTurretWtModel.setValue(Math.max(0, tank.getBaseChassisTurretWeight()));
        spnTurret2WtModel.setValue(Math.max(0, tank.getBaseChassisTurret2Weight()));
        
        omniComponents.forEach(c -> c.setEnabled(chkOmni.isSelected()));
        spnChassisTurretWt.setEnabled(chkOmni.isSelected() && (cbTurrets.getSelectedIndex() > 0));
        spnChassisTurret2Wt.setEnabled(chkOmni.isSelected() && (cbTurrets.getSelectedIndex() > 1));
    }

    /**
     * Disables controls that cannot be changed when customizing a refit.
     */
    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbMotiveType.setEnabled(false);
    }

    public void refresh() {
        refreshTonnage();
        chkOmni.removeActionListener(this);
        chkOmni.setEnabled(techManager.isLegal(Entity.getOmniAdvancement()));
        chkOmni.addActionListener(this);
        refreshEngine();
        refreshTurrets();
        
        omniComponents.forEach(c -> c.setEnabled(chkOmni.isSelected()));
        spnChassisTurretWt.setEnabled(chkOmni.isSelected() && (cbTurrets.getSelectedIndex() > 0));
        spnChassisTurret2Wt.setEnabled(chkOmni.isSelected() && (cbTurrets.getSelectedIndex() > 1));
    }

    private void refreshTonnage() {
        spnTonnage.removeChangeListener(this);
        int max = (int) TestTank.maxTonnage(getMovementMode(), isSuperheavy());
        int min = 1;
        if (isSuperheavy()) {
            min = (int) TestTank.maxTonnage(getMovementMode(), false) + 1;
        }
        spnTonnageModel.setMaximum(max);
        spnTonnageModel.setMinimum(min);

        if (spnTonnageModel.getNumber().intValue() < min) {
            spnTonnageModel.setValue(min);
        } else if (spnTonnageModel.getNumber().intValue() > max) {
            spnTonnageModel.setValue(max);
        }
        spnTonnage.addChangeListener(this);
    }
    
    private void refreshEngine() {
        cbEngine.removeActionListener(this);
        Engine prevEngine = (Engine)cbEngine.getSelectedItem();
        cbEngine.removeAllItems();
        for (Engine e : getAvailableEngines()) {
            cbEngine.addItem(e);
        }
        if (isTrailer) {
            cbEngine.addItem(NO_ENGINE);
        }
        setEngine(prevEngine);
        cbEngine.addActionListener(this);
        if (cbEngine.getSelectedIndex() < 0) {
            cbEngine.setSelectedIndex(0);
        }
    }
    
    private void refreshTurrets() {
        Integer prev = (Integer) cbTurrets.getSelectedItem();
        cbTurrets.removeActionListener(this);
        cbTurrets.removeAllItems();
        cbTurrets.addItem(TURRET_NONE);
        if (getMovementMode() == EntityMovementMode.VTOL) {
            if (techManager.isLegal(TA_CHIN_TURRET)) {
                cbTurrets.addItem(TURRET_CHIN);
            }
        } else {
            cbTurrets.addItem(TURRET_SINGLE);
            if (!EntityMovementMode.WIGE.equals(getMovementMode()) && techManager.isLegal(TA_DUAL_TURRET)) {
                cbTurrets.addItem(TURRET_DUAL);
            }
        }
        cbTurrets.setSelectedItem(prev);
        cbTurrets.addActionListener(this);
        if (cbTurrets.getSelectedIndex() < 0) {
            cbTurrets.setSelectedIndex(0);
        }
        double maxWt = getTonnage();
        spnTurretWtModel.setMaximum(maxWt);
        if (spnTurretWtModel.getNumber().doubleValue() > maxWt) {
            spnTurretWtModel.removeChangeListener(this);
            spnTurretWtModel.setValue(maxWt);
            spnTurretWtModel.addChangeListener(this);
        }
        spnTurret2WtModel.setMaximum(maxWt);
        if (spnTurret2WtModel.getNumber().doubleValue() > maxWt) {
            spnTurret2WtModel.removeChangeListener(this);
            spnTurret2WtModel.setValue(maxWt);
            spnTurret2WtModel.addChangeListener(this);
        }
    }
    
    public double getTonnage() {
        return spnTonnageModel.getNumber().doubleValue();
    }
    
    public void setTonnage(double tonnage) {
        spnTonnageModel.setValue((int)tonnage);
    }
    
    public boolean isSuperheavy() {
        return chkSuperheavy.isEnabled() && chkSuperheavy.isSelected();
    }
    
    public EntityMovementMode getMovementMode() {
        return (EntityMovementMode)cbMotiveType.getSelectedItem();
    }
    
    public Engine getEngine() {
        Engine e = (Engine) cbEngine.getSelectedItem();
        if (null == e) {
            return null;
        }
        return new Engine(getEngineRating(), e.getEngineType(), e.getFlags() | Engine.TANK_ENGINE);
    }

    public int getEngineRating() {
        return engineRating;
    }
    
    public void setEngineRating(int rating) {
        engineRating = rating;
    }
    
    public List<Engine> getAvailableEngines() {
        List<Engine> retVal = new ArrayList<>();
        boolean isMixed = techManager.useMixedTech();
        int flags = Engine.TANK_ENGINE;
        if (techManager.useClanTechBase()) {
            flags |= Engine.CLAN_ENGINE;
        }
        if (getEngineRating() > 400) {
            flags |= Engine.LARGE_ENGINE;
        }
        int altFlags = flags ^ Engine.CLAN_ENGINE;
        for (int i : ENGINE_TYPES) {
            Engine e = new Engine(getEngineRating(), i, flags);
            if (e.engineValid && techManager.isLegal(e)) {
                retVal.add(e);
            }
            // Only add the opposite tech base if the engine is different.
            // (i.e. different slot requirement)
            if (isMixed && e.getSideTorsoCriticalSlots().length > 0) {
                e = new Engine(getEngineRating(), i, altFlags);
                if (e.engineValid && techManager.isLegal(e)) {
                    retVal.add(e);
                }
            }
        }
        return retVal;
    }
    
    /**
     * Select the first engine in the list that matches engine type and flags, disregarding the large
     * engine flag. If the engine type and Clan flag cannot be matched, tries to match the type without
     * regard to the Clan flag.
     * 
     * @param engine  The engine to match
     */
    public void setEngine(Engine engine) {
        if (null != engine) {
            int type = engine.getEngineType();
            int clanFlag = engine.getFlags() & Engine.CLAN_ENGINE;
            int nextBest = -1;
            for (int i = 0; i < cbEngine.getModel().getSize(); i++) {
                final Engine e = cbEngine.getItemAt(i);
                if (e.getEngineType() == type) {
                    if ((e.getFlags() & Engine.CLAN_ENGINE) == clanFlag) {
                        cbEngine.setSelectedIndex(i);
                        return;
                    } else {
                        nextBest = i;
                    }
                }
            }
            if (nextBest >= 0) {
                cbEngine.setSelectedIndex(nextBest);
            }
        }
    }

    /**
     * The turret configuration should be one of {@link CVChassisView#TURRET_NONE TURRET_NONE},
     * {@link CVChassisView#TURRET_SINGLE TURRET_SINGLE}, {@link CVChassisView#TURRET_DUAL TURRET_DUAL}, or
     * {@link CVChassisView#TURRET_CHIN TURRET_CHIN}.
     * @return The currently selected turret configuration.
     */
    public int getTurretConfiguration() {
        Integer config = (Integer) cbTurrets.getSelectedItem();
        if (config == null) {
            return TURRET_NONE; // Failsafe in case this gets called while in an indeterminate state
        } else {
            return config;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        } else if ((e.getSource() == spnChassisTurretWt)
                || (e.getSource() == spnChassisTurret2Wt)){
            listeners.forEach(l -> l.turretBaseWtChanged(spnTurretWtModel.getNumber().doubleValue(),
                    spnTurret2WtModel.getNumber().doubleValue()));
        } else if (e.getSource() == spnExtraSeats) {
            listeners.forEach(l -> l.extraSeatsChanged((int) spnExtraSeats.getValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkOmni) {
            listeners.forEach(l -> l.omniChanged(chkOmni.isSelected()));
        } else if (e.getSource() == chkSuperheavy) {
            listeners.forEach(l -> l.superheavyChanged(chkSuperheavy.isSelected()));
        } else if (e.getSource() == chkTrailer) {
            listeners.forEach(l -> l.trailerChanged(chkTrailer.isSelected()));
        } else if (e.getSource() == chkControlSystems) {
            listeners.forEach(l -> l.controlSystemsChanged(chkControlSystems.isSelected()));
        } else if (e.getSource() == cbMotiveType) {
            listeners.forEach(l -> l.motiveChanged((EntityMovementMode)cbMotiveType.getSelectedItem()));
        } else if (e.getSource() == cbEngine) {
            listeners.forEach(l -> l.engineChanged((Engine)cbEngine.getSelectedItem()));
        } else if (e.getSource() == cbTurrets) {
            listeners.forEach(l -> l.turretChanged(getTurretConfiguration()));
        } else if (e.getActionCommand().equals(CMD_RESET_CHASSIS)) {
            listeners.forEach(CVBuildListener::resetChassis);
        }
    }
}
