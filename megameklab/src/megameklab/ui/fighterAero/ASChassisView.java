/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.fighterAero;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.equipment.Engine;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.AeroBuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.TechComboBox;

/**
 * Structure tab chassis panel for aerospace and conventional fighters.
 *
 * @author Neoancient
 */
public class ASChassisView extends BuildView implements ActionListener, ChangeListener {
    private final List<AeroBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(AeroBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(AeroBuildListener l) {
        listeners.remove(l);
    }

    public final static int TYPE_AEROSPACE = 0;
    public final static int TYPE_CONVENTIONAL = 1;

    // Engines that can be used by aerospace or conventional fighters and the order they appear in the combobox
    private final static int[] ENGINE_TYPES = {
          Engine.NORMAL_ENGINE, Engine.XL_ENGINE, Engine.XXL_ENGINE, Engine.LIGHT_ENGINE,
          Engine.COMPACT_ENGINE, Engine.FISSION, Engine.COMBUSTION_ENGINE
    };
    private final SpinnerNumberModel spnTonnageModel = new SpinnerNumberModel(20, 5, 100, 5);
    private String[] fighterTypeNames;

    final private JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    final private JCheckBox chkOmni = new JCheckBox();
    final private JTextField txtSI = new JTextField();
    final private JCheckBox chkVSTOL = new JCheckBox();
    final private CustomComboBox<Integer> cbFighterType = new CustomComboBox<>(i -> fighterTypeNames[i]);
    final private TechComboBox<Engine> cbEngine = new TechComboBox<>(e -> e.getEngineName().replaceAll("^\\d+ ", ""));
    final private CustomComboBox<Integer> cbCockpit = new CustomComboBox<>(Aero::getCockpitTypeString);
    final private JButton btnResetChassis = new JButton();

    private final ITechManager techManager;
    private boolean primitive = false;
    private boolean conventional = false;
    private int engineRating;

    public ASChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    public void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        fighterTypeNames = resourceMap.getString("FighterChassisView.cbFighterType.values").split(",");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblTonnage", "FighterChassisView.spnTonnage.text",
              "FighterChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        spnTonnage.setToolTipText(resourceMap.getString("FighterChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        chkOmni.setText(resourceMap.getString("FighterChassisView.chkOmni.text"));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        chkOmni.setToolTipText(resourceMap.getString("FighterChassisView.chkOmni.tooltip"));
        add(chkOmni, gbc);
        chkOmni.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblSI", "FighterChassisView.txtSI.text",
              "FighterChassisView.txtSI.tooltip"), gbc);
        txtSI.setToolTipText(resourceMap.getString("FighterChassisView.txtSI.tooltip"));
        txtSI.setEditable(false);

        chkVSTOL.setText(resourceMap.getString("FighterChassisView.chkVSTOL.text"));
        chkVSTOL.setToolTipText(resourceMap.getString("FighterChassisView.chkVSTOL.tooltip"));
        gbc.gridx = 1;
        add(txtSI, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        chkVSTOL.setToolTipText(resourceMap.getString("FighterChassisView.chkVSTOL.tooltip"));
        add(chkVSTOL, gbc);
        chkVSTOL.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblFighterType", "FighterChassisView.cbFighterType.text",
              "FighterChassisView.cbFighterType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        cbFighterType.setToolTipText(resourceMap.getString("FighterChassisView.cbFighterType.tooltip"));
        add(cbFighterType, gbc);
        cbFighterType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblEngine", "FighterChassisView.cbEngine.text",
              "FighterChassisView.cbEngine.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        cbEngine.setToolTipText(resourceMap.getString("FighterChassisView.cbEngine.tooltip"));
        add(cbEngine, gbc);
        cbEngine.addActionListener(this);

        btnResetChassis.setText(resourceMap.getString("FighterChassisView.btnResetChassis.text"));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblCockpit", "FighterChassisView.cbCockpit.text",
              "FighterChassisView.cbCockpit.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        cbCockpit.setToolTipText(resourceMap.getString("FighterChassisView.cbCockpit.tooltip"));
        add(cbCockpit, gbc);
        cbCockpit.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        btnResetChassis.setToolTipText(resourceMap.getString("FighterChassisView.btnResetChassis.tooltip"));
        add(btnResetChassis, gbc);
        btnResetChassis.addActionListener(this);
    }

    public void setFromEntity(Aero aero) {
        primitive = aero.isPrimitive();
        conventional = (aero.getEntityType() & Entity.ETYPE_CONV_FIGHTER) != 0;
        engineRating = aero.getEngine().getRating();
        refresh();
        spnTonnage.removeChangeListener(this);
        setTonnage(aero.getWeight());
        spnTonnage.addChangeListener(this);
        chkOmni.removeActionListener(this);
        setOmni(aero.isOmni());
        chkOmni.addActionListener(this);
        chkOmni.setEnabled(!aero.isPrimitive() && !conventional
              && techManager.isLegal(Entity.getOmniAdvancement()));
        txtSI.setText(String.valueOf(aero.getSI()));
        chkVSTOL.removeActionListener(this);
        chkVSTOL.setSelected(aero.isVSTOL());
        chkVSTOL.addActionListener(this);
        chkVSTOL.setEnabled(conventional);
        cbFighterType.removeActionListener(this);
        cbFighterType.setSelectedItem(conventional ? TYPE_CONVENTIONAL : TYPE_AEROSPACE);
        cbFighterType.addActionListener(this);
        setEngine(aero.getEngine());
        setCockpitType(aero.getCockpitType());
        btnResetChassis.setEnabled(aero.isOmni());
    }

    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
    }

    public int getEngineRating() {
        return engineRating;
    }

    public void setEngineRating(int rating) {
        engineRating = rating;
    }

    public void refresh() {
        refreshTonnage();
        refreshFighterType();
        refreshEngine();
        refreshCockpit();

        chkOmni.setEnabled(!isPrimitive()
              && techManager.isLegal(Entity.getOmniAdvancement()));

    }

    private void refreshTonnage() {
        int prev = spnTonnageModel.getNumber().intValue();
        int min = primitive ? 10 : 5;
        int max = conventional ? 50 : 100;
        spnTonnageModel.setMinimum(min);
        spnTonnageModel.setMaximum(max);
        if (prev < min) {
            spnTonnage.setValue(min);
        }
        if (prev > max) {
            spnTonnage.setValue(max);
        }
    }

    private void refreshFighterType() {
        // aerospace and conventional fighters have the same tech progression,
        // so we only need to change this if switch between standard and primitive,
        // which use support vehicle rules for conventional fighters.
        int size = cbFighterType.getModel().getSize();
        if ((size == 0) || (isPrimitive() != (size == 1))) {
            Integer prev = (Integer) cbFighterType.getSelectedItem();
            cbFighterType.removeActionListener(this);
            cbFighterType.removeAllItems();
            cbFighterType.addItem(TYPE_AEROSPACE);
            if (!isPrimitive()) {
                cbFighterType.addItem(TYPE_CONVENTIONAL);
            }
            cbFighterType.setSelectedItem(prev);
            cbFighterType.addActionListener(this);
            if (cbFighterType.getSelectedIndex() < 0) {
                cbFighterType.setSelectedIndex(0);
            }
        }
    }

    private void refreshEngine() {
        cbEngine.removeActionListener(this);
        Engine prevEngine = (Engine) cbEngine.getSelectedItem();
        cbEngine.removeAllItems();
        for (Engine e : getAvailableEngines()) {
            cbEngine.addItem(e);
        }
        setEngine(prevEngine);
        cbEngine.addActionListener(this);
        if ((cbEngine.getSelectedIndex() < 0) && (cbEngine.getModel().getSize() > 0)) {
            cbEngine.setSelectedIndex(0);
        }
    }

    public List<Engine> getAvailableEngines() {
        if (isPrimitive()) {
            return Collections.singletonList(new Engine(getEngineRating(),
                  Engine.NORMAL_ENGINE, 0));
        }
        List<Engine> retVal = new ArrayList<>();
        boolean isMixed = techManager.useMixedTech();
        int flags = 0;
        if (techManager.useClanTechBase()) {
            flags |= Engine.CLAN_ENGINE;
        }
        if (getEngineRating() > 400) {
            flags |= Engine.LARGE_ENGINE;
        }
        int altFlags = flags ^ Engine.CLAN_ENGINE;
        for (int i : ENGINE_TYPES) {
            Engine e = new Engine(getEngineRating(), i, flags);
            if (e.engineValid && techManager.isLegal(e) && (validCFEngine(e) || validASFEngine(e))) {
                retVal.add(e);
            }
            // Only add the opposite tech base if the engine is different.
            if (isMixed && e.getSideTorsoCriticalSlots().length > 0) {
                e = new Engine(getEngineRating(), i, altFlags);
                if (e.engineValid && techManager.isLegal(e) && (validCFEngine(e) || validASFEngine(e))) {
                    retVal.add(e);
                }
            }
        }
        return retVal;
    }

    private boolean validCFEngine(Engine engine) {
        return conventional && ((engine.getEngineType() == Engine.NORMAL_ENGINE)
              || (engine.getEngineType() == Engine.COMBUSTION_ENGINE));
    }

    private boolean validASFEngine(Engine engine) {
        return !conventional && engine.isFusion();
    }

    private void refreshCockpit() {
        cbCockpit.removeActionListener(this);
        Integer prev = (Integer) cbCockpit.getSelectedItem();
        cbCockpit.removeAllItems();
        if (isPrimitive()) {
            cbCockpit.addItem(Aero.COCKPIT_PRIMITIVE);
        } else if (isConventional()) {
            cbCockpit.addItem(Aero.COCKPIT_STANDARD);
        } else {
            for (int cockpitType = 0; cockpitType < Aero.COCKPIT_STRING.length; cockpitType++) {
                if ((Aero.COCKPIT_PRIMITIVE != cockpitType)
                      && techManager.isLegal(Aero.getCockpitTechAdvancement(cockpitType))) {
                    cbCockpit.addItem(cockpitType);
                }
            }
        }
        cbCockpit.setSelectedItem(prev);
        cbCockpit.addActionListener(this);
        if (cbCockpit.getSelectedIndex() < 0) {
            cbCockpit.setSelectedIndex(0);
        }
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public boolean isConventional() {
        return conventional;
    }

    public double getTonnage() {
        return spnTonnageModel.getNumber().doubleValue();
    }

    public void setTonnage(double tonnage) {
        spnTonnage.setValue((int) Math.ceil(tonnage));
    }

    public boolean isOmni() {
        return chkOmni.isSelected() && chkOmni.isEnabled();
    }

    public void setOmni(boolean omni) {
        chkOmni.setSelected(omni);
    }

    public boolean isVSTOL() {
        return chkVSTOL.isSelected();
    }

    public void setVSTOL(boolean vstol) {
        chkOmni.setSelected(vstol);
    }

    public int getFighterType() {
        Object value = cbFighterType.getSelectedItem();

        if (value instanceof Integer intValue) {
            return intValue;
        }

        return 0;
    }

    public Engine getEngine() {
        Engine e = (Engine) cbEngine.getSelectedItem();
        if (null == e) {
            return null;
        }
        return new Engine(getEngineRating(), e.getEngineType(), e.getFlags());
    }

    /**
     * Select the first engine in the list that matches engine type and flags, ignoring large engine flag. If no match
     * can be found based on type and flags, disregards flags as well.
     *
     * @param engine The engine to match
     */
    public void setEngine(Engine engine) {
        if (null != engine) {
            int type = engine.getEngineType();
            int flags = engine.getFlags() & ~Engine.LARGE_ENGINE;
            int nextBest = -1;
            for (int i = 0; i < cbEngine.getModel().getSize(); i++) {
                final Engine e = cbEngine.getItemAt(i);
                if (e.getEngineType() == type) {
                    if ((e.getFlags() & ~Engine.LARGE_ENGINE) == flags) {
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

    public int getCockpitType() {
        Object value = cbCockpit.getSelectedItem();

        if (value instanceof Integer intValue) {
            return intValue;
        }
        return 0;
    }

    public void setCockpitType(int cockpit) {
        cbCockpit.setSelectedItem(cockpit);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkOmni) {
            listeners.forEach(l -> l.omniChanged(isOmni()));
        } else if (e.getSource() == chkVSTOL) {
            listeners.forEach(l -> l.vstolChanged(isVSTOL()));
        } else if (e.getSource() == cbFighterType) {
            Object value = cbFighterType.getSelectedItem();
            if (value instanceof Integer intValue) {
                listeners.forEach(l -> l.fighterTypeChanged(intValue));
            }
        } else if (e.getSource() == cbEngine) {
            listeners.forEach(l -> l.engineChanged(getEngine()));
        } else if (e.getSource() == cbCockpit) {
            listeners.forEach(l -> l.cockpitChanged(getCockpitType()));
        } else if (e.getSource() == btnResetChassis) {
            listeners.forEach(AeroBuildListener::resetChassis);
        }
    }
}
