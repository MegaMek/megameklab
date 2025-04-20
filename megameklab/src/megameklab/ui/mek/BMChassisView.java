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
 */
package megameklab.ui.mek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.*;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.MekBuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.TechComboBox;

/**
 * Construction options and systems for Meks.
 *
 * @author Neoancient
 */
public class BMChassisView extends BuildView implements ActionListener, ChangeListener {
    List<MekBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(MekBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(MekBuildListener l) {
        listeners.remove(l);
    }

    public static final int BASE_TYPE_STANDARD = 0;
    public static final int BASE_TYPE_INDUSTRIAL = 1;
    public static final int BASE_TYPE_LAM = 2;
    public static final int BASE_TYPE_QUADVEE = 3;

    public static final int MOTIVE_TYPE_BIPED = 0;
    public static final int MOTIVE_TYPE_QUAD = 1;
    public static final int MOTIVE_TYPE_TRIPOD = 2;

    public static final int MOTIVE_TYPE_LAM_STD = 0;
    public static final int MOTIVE_TYPE_LAM_BM = 1;

    public static final int MOTIVE_TYPE_QV_TRACKED = 0;
    public static final int MOTIVE_TYPE_QV_WHEELED = 1;

    // Engines that can be used by Meks and the order they appear in the combobox
    private final static int[] ENGINE_TYPES = { Engine.NORMAL_ENGINE, Engine.XL_ENGINE, Engine.XXL_ENGINE,
                                                Engine.FUEL_CELL, Engine.LIGHT_ENGINE, Engine.COMPACT_ENGINE,
                                                Engine.FISSION, Engine.COMBUSTION_ENGINE };
    // Industrial (and primitive) Meks can use non-fusion engines under standard rules but cannot use
    // any fusion engines other than a standard.
    private final static int[] INDUSTRIAL_ENGINE_TYPES = { Engine.NORMAL_ENGINE, Engine.FUEL_CELL, Engine.FISSION,
                                                           Engine.COMBUSTION_ENGINE };
    // LAMs can only use fusion engines that are contained entirely within the center torso.
    private final static int[] LAM_ENGINE_TYPES = { Engine.NORMAL_ENGINE, Engine.COMPACT_ENGINE };

    // Internal structure for non-industrial Meks
    private final static int[] STRUCTURE_TYPES = { EquipmentType.T_STRUCTURE_STANDARD,
                                                   EquipmentType.T_STRUCTURE_ENDO_STEEL,
                                                   EquipmentType.T_STRUCTURE_ENDO_PROTOTYPE,
                                                   EquipmentType.T_STRUCTURE_REINFORCED,
                                                   EquipmentType.T_STRUCTURE_COMPOSITE,
                                                   EquipmentType.T_STRUCTURE_ENDO_COMPOSITE };

    // Internal structure for superheavy battleMeks
    private final static int[] SUPERHEAVY_STRUCTURE_TYPES = { EquipmentType.T_STRUCTURE_STANDARD,
                                                              EquipmentType.T_STRUCTURE_ENDO_STEEL,
                                                              EquipmentType.T_STRUCTURE_ENDO_COMPOSITE };

    final private SpinnerNumberModel tonnageModel = new SpinnerNumberModel(20, 20, 100, 5);
    final private JSpinner spnTonnage = new JSpinner(tonnageModel);
    final private JCheckBox chkOmni = new JCheckBox("Omni");
    final private JComboBox<String> cbBaseType = new JComboBox<>();
    final private JComboBox<String> cbMotiveType = new JComboBox<>();
    final private TechComboBox<EquipmentType> cbStructure = new TechComboBox<>(EquipmentType::getName);
    final private TechComboBox<Engine> cbEngine = new TechComboBox<>(e -> e.getEngineName().replaceAll("^\\d+ ", ""));
    final private CustomComboBox<Integer> cbGyro = new CustomComboBox<>(Mek::getGyroTypeShortString);
    final private CustomComboBox<Integer> cbCockpit = new CustomComboBox<>(i -> Mek.getCockpitTypeString(i,
          isIndustrial()));
    final private TechComboBox<EquipmentType> cbEnhancement = new TechComboBox<>(EquipmentType::getName);
    final private JCheckBox chkFullHeadEject = new JCheckBox();
    final private JButton btnResetChassis = new JButton();

    private ComboBoxModel<String> baseTypesModel;
    private ComboBoxModel<String> standardTypesModel;
    private ComboBoxModel<String> lamTypesModel;
    private ComboBoxModel<String> qvTypesModel;
    private ComboBoxModel<String> primitiveTypesModel;
    private ComboBoxModel<String> primitiveMotiveTypesModel;

    private boolean primitive = false;
    private int engineRating = 20;

    private static final int[] GENERAL_COCKPITS = { Mek.COCKPIT_STANDARD, Mek.COCKPIT_SMALL,
                                                    Mek.COCKPIT_COMMAND_CONSOLE, Mek.COCKPIT_SMALL_COMMAND_CONSOLE,
                                                    Mek.COCKPIT_TORSO_MOUNTED, Mek.COCKPIT_DUAL, Mek.COCKPIT_INTERFACE,
                                                    Mek.COCKPIT_VRRP };

    private static final int[] INDUSTRIAL_COCKPITS = { Mek.COCKPIT_INDUSTRIAL, Mek.COCKPIT_STANDARD,
                                                       Mek.COCKPIT_COMMAND_CONSOLE, Mek.COCKPIT_TORSO_MOUNTED };

    private static final String[] ENHANCEMENT_NAMES = { EquipmentTypeLookup.IS_MASC, EquipmentTypeLookup.CLAN_MASC,
                                                        EquipmentTypeLookup.TSM, EquipmentTypeLookup.P_TSM,
                                                        EquipmentTypeLookup.SCM };

    private final ITechManager techManager;
    private String stdMotiveTooltip;
    private String lamMotiveTooltip;
    private String qvMotiveTooltip;

    public BMChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        baseTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.baseType.values").split(","));
        standardTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.motiveType.values")
                                                              .split(","));
        lamTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.lamType.values").split(","));
        qvTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.qvType.values").split(","));
        primitiveTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.primitiveType.values")
                                                               .split(","));
        primitiveMotiveTypesModel = new DefaultComboBoxModel<>(resourceMap.getString(
              "MekChassisView.primitiveMotiveType.values").split(","));
        stdMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.tooltip");
        lamMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.LAM.tooltip");
        qvMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.QuadVee.tooltip");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = STANDARD_INSETS;
        add(createLabel(resourceMap,
              "lblTonnage",
              "MekChassisView.spnTonnage.text",
              "MekChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        spnTonnage.setToolTipText(resourceMap.getString("MekChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        add(spnTonnage, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        chkOmni.setText(resourceMap.getString("MekChassisView.chkOmni.text"));
        chkOmni.setToolTipText(resourceMap.getString("MekChassisView.chkOmni.tooltip"));
        add(chkOmni, gbc);
        chkOmni.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap,
              "lblBaseType",
              "MekChassisView.cbBaseType.text",
              "MekChassisView.cbBaseType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        cbBaseType.setToolTipText(resourceMap.getString("MekChassisView.cbBaseType.tooltip"));
        add(cbBaseType, gbc);
        cbBaseType.addActionListener(this);
        cbBaseType.setPrototypeDisplayValue(CB_SIZE_VALUE);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap,
              "lblMotiveType",
              "MekChassisView.cbMotiveType.text",
              "MekChassisView.cbMotiveType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        cbMotiveType.setToolTipText(resourceMap.getString("MekChassisView.cbMotiveType.tooltip"));
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);
        cbMotiveType.setPrototypeDisplayValue(CB_SIZE_VALUE);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap,
              "lblStructure",
              "MekChassisView.cbStructure.text",
              "MekChassisView.cbStructure.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        cbStructure.setToolTipText(resourceMap.getString("MekChassisView.cbStructure.tooltip"));
        add(cbStructure, gbc);
        cbStructure.addActionListener(this);
        cbEnhancement.setPrototypeDisplayValue(CB_SIZE_EQUIPMENT);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblEngine", "MekChassisView.cbEngine.text", "MekChassisView.cbEngine.tooltip"),
              gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        cbEngine.setToolTipText(resourceMap.getString("MekChassisView.cbEngine.tooltip"));
        add(cbEngine, gbc);
        cbEngine.addActionListener(this);
        cbEngine.setPrototypeDisplayValue(CB_SIZE_ENGINE);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblGyro", "MekChassisView.cbGyro.text", "MekChassisView.cbGyro.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        cbGyro.setToolTipText(resourceMap.getString("MekChassisView.cbGyro.tooltip"));
        add(cbGyro, gbc);
        cbGyro.addActionListener(this);
        cbGyro.setPrototypeDisplayValue(1);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblCockpit", "MekChassisView.cbCockpit.text", "MekChassisView.cbCockpit.tooltip"),
              gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        cbCockpit.setToolTipText(resourceMap.getString("MekChassisView.cbCockpit.tooltip"));
        add(cbCockpit, gbc);
        cbCockpit.addActionListener(this);
        cbCockpit.setPrototypeDisplayValue(0);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap,
              "lblEnhancement",
              "MekChassisView.cbEnhancement.text",
              "MekChassisView.cbEnhancement.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        cbEnhancement.setNullValue(resourceMap.getString("MekChassisView.cbEnhancement.null"));
        cbEnhancement.setToolTipText(resourceMap.getString("MekChassisView.cbEnhancement.tooltip"));
        add(cbEnhancement, gbc);
        cbEnhancement.addActionListener(this);
        cbEnhancement.setPrototypeDisplayValue(CB_SIZE_EQUIPMENT);

        chkFullHeadEject.setText(resourceMap.getString("MekChassisView.chkFullHeadEject.text"));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        add(chkFullHeadEject, gbc);
        chkFullHeadEject.setToolTipText(resourceMap.getString("MekChassisView.chkFullHeadEject.tooltip"));
        chkFullHeadEject.addActionListener(this);

        btnResetChassis.setText(resourceMap.getString("MekChassisView.btnResetChassis.text"));
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        add(btnResetChassis, gbc);
        btnResetChassis.setToolTipText(resourceMap.getString("MekChassisView.btnResetChassis.tooltip"));
        btnResetChassis.addActionListener(this);
    }

    public void setFromEntity(Mek mek) {
        primitive = mek.isPrimitive();
        engineRating = mek.getEngine().getRating();
        refresh();
        setTonnage(mek.getWeight());
        setOmni(mek.isOmni());
        chkOmni.setEnabled(!mek.isPrimitive() &&
                                 !mek.isIndustrial() &&
                                 techManager.isLegal(Entity.getOmniAdvancement()));
        cbBaseType.removeActionListener(this);
        cbMotiveType.removeActionListener(this);
        if (primitive) {
            cbBaseType.setModel(primitiveTypesModel);
        } else {
            cbBaseType.setModel(baseTypesModel);
        }
        if (mek instanceof LandAirMek) {
            chkOmni.setEnabled(false);
            setBaseTypeIndex(BASE_TYPE_LAM);
            cbMotiveType.setModel(lamTypesModel);
            setMotiveTypeIndex(((LandAirMek) mek).getLAMType());
            cbMotiveType.setToolTipText(lamMotiveTooltip);
        } else if (mek instanceof QuadVee) {
            setBaseTypeIndex(BASE_TYPE_QUADVEE);
            cbMotiveType.setModel(qvTypesModel);
            setMotiveTypeIndex(((QuadVee) mek).getMotiveType());
            cbMotiveType.setToolTipText(qvMotiveTooltip);
        } else {
            setBaseTypeIndex(mek.isIndustrial() ? BASE_TYPE_INDUSTRIAL : BASE_TYPE_STANDARD);
            if (primitive) {
                cbMotiveType.setModel(primitiveMotiveTypesModel);
            } else {
                cbMotiveType.setModel(standardTypesModel);
            }
            if ((mek.getEntityType() & Entity.ETYPE_TRIPOD_MEK) != 0) {
                setMotiveTypeIndex(MOTIVE_TYPE_TRIPOD);
            } else if ((mek.getEntityType() & Entity.ETYPE_QUAD_MEK) != 0) {
                setMotiveTypeIndex(MOTIVE_TYPE_QUAD);
            } else {
                setMotiveTypeIndex(MOTIVE_TYPE_BIPED);
            }
            cbMotiveType.setToolTipText(stdMotiveTooltip);
        }
        cbBaseType.addActionListener(this);
        cbMotiveType.addActionListener(this);
        setStructureType(EquipmentType.getStructureTypeName(mek.getStructureType(),
              TechConstants.isClan(mek.getStructureTechLevel())));
        setEngine(mek.getEngine());
        setGyroType(mek.getGyroType());
        setCockpitType(mek.getCockpitType());
        // A simple hasWorkingMisc() will not tell us whether we have IS or Clan MASC, so we need to search
        // the list for the first matching.
        Optional<MiscType> enh = mek.getMisc()
                                       .stream()
                                       .map(Mounted::getType)
                                       .filter(et -> (et.hasFlag(MiscType.F_MASC) && et.getSubType() == 0) ||
                                                           et.hasFlag(MiscType.F_TSM) ||
                                                           et.hasFlag(MiscType.F_INDUSTRIAL_TSM) ||
                                                           et.hasFlag(MiscType.F_SCM))
                                       .findFirst();
        if (enh.isPresent()) {
            setEnhancement(enh.get());
        } else {
            setEnhancement(null);
        }
        setFullHeadEject(mek.hasFullHeadEject());
        btnResetChassis.setEnabled(mek.isOmni());
    }

    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbBaseType.setEnabled(false);
        cbMotiveType.setEnabled(false);
    }

    public boolean isSuperheavy() {
        return getTonnage() > 100;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public boolean isIndustrial() {
        return getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL;
    }

    public int getEngineRating() {
        return engineRating;
    }

    public void setEngineRating(int rating) {
        engineRating = rating;
    }

    public void refresh() {
        refreshTonnage();
        refreshStructure();
        refreshEngine();
        refreshGyro();
        refreshCockpit();
        refreshEnhancement();
        refreshFullHeadEject();

        chkOmni.removeActionListener(this);
        chkOmni.setEnabled(!isPrimitive() &&
                                 (getBaseTypeIndex() != BASE_TYPE_INDUSTRIAL) &&
                                 (getBaseTypeIndex() != BASE_TYPE_LAM) &&
                                 techManager.isLegal(Entity.getOmniAdvancement()));
        chkOmni.addActionListener(this);
    }

    private void refreshTonnage() {
        int min = 20;
        int max = 100;
        spnTonnage.removeChangeListener(this);
        if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            max = 55;
        } else if (((getBaseTypeIndex() == BASE_TYPE_STANDARD) || (getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL)) &&
                         techManager.isLegal(Mek.getTechAdvancement(Entity.ETYPE_MEK,
                               false,
                               getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL,
                               EntityWeightClass.WEIGHT_SUPER_HEAVY))) {
            max = 200;
        }
        if (techManager.isLegal(Mek.getTechAdvancement(Entity.ETYPE_MEK,
              false,
              false,
              EntityWeightClass.WEIGHT_ULTRA_LIGHT))) {
            min = 10;
        }
        tonnageModel.setMinimum(min);
        tonnageModel.setMaximum(max);
        spnTonnage.addChangeListener(this);
        if (tonnageModel.getNumber().doubleValue() < min) {
            tonnageModel.setValue(min);
        } else if (tonnageModel.getNumber().doubleValue() > max) {
            tonnageModel.setValue(max);
        }
    }

    private void refreshStructure() {
        boolean isMixed = techManager.useMixedTech();
        boolean isClan = techManager.useClanTechBase();
        cbStructure.removeActionListener(this);
        EquipmentType prevStructure = (EquipmentType) cbStructure.getSelectedItem();
        cbStructure.removeAllItems();
        cbStructure.showTechBase(isMixed);
        // Primitive/retro can only use standard/industrial structure. Industrial can only use industrial
        // at standard rules level. Super-heavies can only use standard.
        if (isIndustrial()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_INDUSTRIAL, isClan);
            cbStructure.addItem(EquipmentType.get(name));
        } else if (isPrimitive()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_STANDARD, isClan);
            cbStructure.addItem(EquipmentType.get(name));
        } else {
            int[] structureTypes = isSuperheavy() ? SUPERHEAVY_STRUCTURE_TYPES : STRUCTURE_TYPES;
            for (int i : structureTypes) {
                String name = EquipmentType.getStructureTypeName(i, isClan);
                EquipmentType structure = EquipmentType.get(name);
                // LAMs cannot use bulky structure
                if ((null != structure) &&
                          techManager.isLegal(structure) &&
                          ((getBaseTypeIndex() != BASE_TYPE_LAM) || (structure.getCriticals(null) == 0))) {
                    cbStructure.addItem(structure);
                }
                name = EquipmentType.getStructureTypeName(i, !isClan);
                EquipmentType structure2 = EquipmentType.get(name);
                if ((null != structure2) &&
                          (structure2 != structure) &&
                          techManager.isLegal(structure2) &&
                          ((getBaseTypeIndex() != BASE_TYPE_LAM) || (structure2.getCriticals(null) == 0))) {
                    cbStructure.addItem(structure2);
                    // If we are allowing the opposite tech base, it may be because we are using mixed tech. However, it
                    // also may be that we are in the transitional early Clan stage when IS equipment is available
                    // without a mixed base.
                    cbStructure.showTechBase(true);
                }
            }
        }
        cbStructure.setSelectedItem(prevStructure);
        cbStructure.addActionListener(this);
        if (cbStructure.getSelectedIndex() < 0) {
            cbStructure.setSelectedIndex(0);
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
        if (cbEngine.getSelectedIndex() < 0) {
            cbEngine.setSelectedIndex(0);
        }
    }

    private void refreshGyro() {
        cbGyro.removeActionListener(this);
        Integer prev = (Integer) cbGyro.getSelectedItem();
        cbGyro.removeAllItems();
        if (isSuperheavy()) {
            cbGyro.addItem(Mek.GYRO_SUPERHEAVY);
        } else if (isPrimitive() || isIndustrial()) {
            cbGyro.addItem(Mek.GYRO_STANDARD);
        } else {
            for (int i = 0; i < Mek.GYRO_NONE; i++) {
                if (techManager.isLegal(Mek.getGyroTechAdvancement(i)) &&
                          ((i != Mek.GYRO_XL) || (getBaseTypeIndex() != BASE_TYPE_LAM))) {
                    cbGyro.addItem(i);
                }
            }
        }
        if ((cbCockpit.getSelectedItem() != null) && cbCockpit.getSelectedItem().equals(Mek.COCKPIT_INTERFACE)) {
            cbGyro.addItem(Mek.GYRO_NONE);
        }
        cbGyro.setSelectedItem(prev);
        cbGyro.addActionListener(this);
        if (cbGyro.getSelectedIndex() < 0) {
            cbGyro.setSelectedIndex(0);
        }
    }

    private void refreshCockpit() {
        cbCockpit.removeActionListener(this);
        Integer prev = (Integer) cbCockpit.getSelectedItem();
        cbCockpit.removeAllItems();
        if (((getBaseTypeIndex() == BASE_TYPE_STANDARD) || getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL) &&
                  (getMotiveTypeIndex() == MOTIVE_TYPE_TRIPOD)) {
            if (isIndustrial()) {
                cbCockpit.addItem(isSuperheavy() ?
                                        Mek.COCKPIT_SUPERHEAVY_TRIPOD_INDUSTRIAL :
                                        Mek.COCKPIT_TRIPOD_INDUSTRIAL);
            }
            cbCockpit.addItem(isSuperheavy() ? Mek.COCKPIT_SUPERHEAVY_TRIPOD : Mek.COCKPIT_TRIPOD);
        } else if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            cbCockpit.addItem(Mek.COCKPIT_STANDARD);
            cbCockpit.addItem(Mek.COCKPIT_SMALL);
        } else if (getBaseTypeIndex() == BASE_TYPE_QUADVEE) {
            cbCockpit.addItem(Mek.COCKPIT_QUADVEE);
        } else if (isSuperheavy()) {
            if (isIndustrial()) {
                cbCockpit.addItem(Mek.COCKPIT_SUPERHEAVY_INDUSTRIAL);
            }
            cbCockpit.addItem(Mek.COCKPIT_SUPERHEAVY);
            if (techManager.isLegal(Mek.getCockpitTechAdvancement(Mek.COCKPIT_SUPERHEAVY_COMMAND_CONSOLE))) {
                cbCockpit.addItem(Mek.COCKPIT_SUPERHEAVY_COMMAND_CONSOLE);
            }
        } else if (isPrimitive()) {
            if (isIndustrial()) {
                cbCockpit.addItem(Mek.COCKPIT_PRIMITIVE_INDUSTRIAL);
                // If the date is late enough, include a primitive cockpit with advanced fire control
                if (techManager.isLegal(Mek.getCockpitTechAdvancement(Mek.COCKPIT_PRIMITIVE))) {
                    cbCockpit.addItem(Mek.COCKPIT_PRIMITIVE);
                }
            } else {
                cbCockpit.addItem(Mek.COCKPIT_PRIMITIVE);
            }
        } else if (isIndustrial()) {
            for (int cockpitType : INDUSTRIAL_COCKPITS) {
                if (techManager.isLegal(Mek.getCockpitTechAdvancement(cockpitType))) {
                    cbCockpit.addItem(cockpitType);
                }
            }
        } else {
            for (int cockpitType : GENERAL_COCKPITS) {
                if (techManager.isLegal(Mek.getCockpitTechAdvancement(cockpitType))) {
                    cbCockpit.addItem(cockpitType);
                }
            }
        }
        cbCockpit.setSelectedItem(prev);
        cbCockpit.addActionListener(this);
        if ((cbCockpit.getSelectedIndex() < 0) && (cbCockpit.getModel().getSize() > 0)) {
            cbCockpit.setSelectedIndex(0);
        }
    }

    private void refreshEnhancement() {
        cbEnhancement.removeActionListener(this);
        EquipmentType prev = (EquipmentType) cbEnhancement.getSelectedItem();
        cbEnhancement.removeAllItems();
        cbEnhancement.addItem(null);
        if (!isSuperheavy() && !isPrimitive()) {
            if (isIndustrial()) {
                EquipmentType eq = EquipmentType.get(EquipmentTypeLookup.ITSM);
                if (techManager.isLegal(eq)) {
                    cbEnhancement.addItem(eq);
                }
            } else {
                cbEnhancement.showTechBase(techManager.useMixedTech());
                for (String name : ENHANCEMENT_NAMES) {
                    EquipmentType eq = EquipmentType.get(name);
                    if (techManager.isLegal(eq)) {
                        cbEnhancement.addItem(eq);
                    }
                }
            }
        }
        cbEnhancement.setSelectedItem(prev);
        cbEnhancement.addActionListener(this);
        if (cbEnhancement.getSelectedIndex() < 0) {
            cbEnhancement.setSelectedIndex(0);
        }
    }

    private void refreshFullHeadEject() {
        chkFullHeadEject.removeActionListener(this);
        final Integer cockpitType = (Integer) cbCockpit.getSelectedItem();
        chkFullHeadEject.setEnabled((cockpitType != null) &&
                                          (cockpitType != Mek.COCKPIT_TORSO_MOUNTED) &&
                                          (cockpitType != Mek.COCKPIT_VRRP) &&
                                          (cockpitType != Mek.COCKPIT_COMMAND_CONSOLE) &&
                                          techManager.isLegal(Mek.getFullHeadEjectAdvancement()));
        chkFullHeadEject.addActionListener(this);
    }


    public List<Engine> getAvailableEngines() {
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
        int[] engineTypes = ENGINE_TYPES;
        if (isPrimitive() || isIndustrial()) {
            engineTypes = INDUSTRIAL_ENGINE_TYPES;
        } else if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            engineTypes = LAM_ENGINE_TYPES;
        }
        // Non-superheavies can use non-fusion engines under experimental rules
        // industrials and primitives can use non-fusion under standard rules
        boolean allowNonFusion = isIndustrial() ||
                                       isPrimitive() ||
                                       (!isSuperheavy() &&
                                              techManager.getTechLevel().compareTo(SimpleTechLevel.EXPERIMENTAL) >= 0);
        for (int i : engineTypes) {
            Engine e = new Engine(getEngineRating(), i, flags);
            if (e.engineValid && (e.isFusion() || allowNonFusion) && techManager.isLegal(e)) {
                retVal.add(e);
            }
            // Only add the opposite tech base if the engine is different.
            if (isMixed && e.getSideTorsoCriticalSlots().length > 0) {
                e = new Engine(getEngineRating(), i, altFlags);
                if (e.engineValid && (e.isFusion() || allowNonFusion) && techManager.isLegal(e)) {
                    retVal.add(e);
                }
            }
        }
        return retVal;
    }

    public double getTonnage() {
        return tonnageModel.getNumber().doubleValue();
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

    public int getBaseTypeIndex() {
        return cbBaseType.getSelectedIndex();
    }

    public void setBaseTypeIndex(int index) {
        cbBaseType.setSelectedIndex(index);
    }

    public long getEntityType() {
        if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            return Entity.ETYPE_LAND_AIR_MEK;
        } else if (getBaseTypeIndex() == BASE_TYPE_QUADVEE) {
            return Entity.ETYPE_QUADVEE;
        } else if (getMotiveTypeIndex() == MOTIVE_TYPE_TRIPOD) {
            return Entity.ETYPE_TRIPOD_MEK;
        } else if (getMotiveTypeIndex() == MOTIVE_TYPE_QUAD) {
            return Entity.ETYPE_QUAD_MEK;
        } else {
            return Entity.ETYPE_BIPED_MEK;
        }
    }

    public int getMotiveTypeIndex() {
        return cbMotiveType.getSelectedIndex();
    }

    public void setMotiveTypeIndex(int index) {
        cbMotiveType.setSelectedIndex(index);
    }

    public EquipmentType getStructure() {
        return (EquipmentType) cbStructure.getSelectedItem();
    }

    /**
     * @deprecated no indicated uses
     */
    @Deprecated(since = "0.50.06", forRemoval = true)
    public void setStructureType(EquipmentType structure) {
        cbStructure.setSelectedItem(structure);
    }

    public void setStructureType(String structureName) {
        EquipmentType structure = EquipmentType.get(structureName);
        cbStructure.setSelectedItem(structure);
    }

    public Engine getEngine() {
        Engine e = (Engine) cbEngine.getSelectedItem();
        if (null == e) {
            return null;
        }
        // Clan flag is specific to the engine. The superheavy and large flags depend on the mek
        // and rating and may have changed from the last refresh.
        int flags = e.getFlags() & Engine.CLAN_ENGINE;
        if (getEngineRating() > 400) {
            flags |= Engine.LARGE_ENGINE;
        }
        if (isSuperheavy()) {
            flags |= Engine.SUPERHEAVY_ENGINE;
        }
        return new Engine(getEngineRating(), e.getEngineType(), flags);
    }

    /**
     * Select the first engine in the list that matches an engine type and flags, ignoring any flags other than Clan. If
     * no match can be found based on type and flags, disregards a Clan flag as well.
     *
     * @param engine The engine to match
     */
    public void setEngine(Engine engine) {
        if (null != engine) {
            int type = engine.getEngineType();
            int flags = engine.getFlags() & Engine.CLAN_ENGINE;
            int nextBest = -1;
            for (int i = 0; i < cbEngine.getModel().getSize(); i++) {
                final Engine e = cbEngine.getItemAt(i);
                if (e.getEngineType() == type) {
                    if ((e.getFlags() & Engine.CLAN_ENGINE) == flags) {
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

    public int getGyroType() {
        Object gyro = cbGyro.getSelectedItem();

        if (gyro instanceof Integer value) {
            return value;
        }
        return 0;
    }

    public void setGyroType(int gyro) {
        cbGyro.setSelectedItem(gyro);
    }

    public int getCockpitType() {
        Object cockpit = cbCockpit.getSelectedItem();

        if (cockpit instanceof Integer value) {
            return value;
        }

        return 0;
    }

    public void setCockpitType(int cockpit) {
        cbCockpit.setSelectedItem(cockpit);
    }

    public EquipmentType getEnhancement() {
        return (EquipmentType) cbEnhancement.getSelectedItem();
    }

    public void setEnhancement(EquipmentType enhancement) {
        cbEnhancement.setSelectedItem(enhancement);
    }

    /**
     * @deprecated no indicated uses
     */
    @Deprecated(since = "0.50.06", forRemoval = true)
    public boolean hasFullHeadEject() {
        return chkFullHeadEject.isSelected() && chkFullHeadEject.isEnabled();
    }

    public void setFullHeadEject(boolean eject) {
        chkFullHeadEject.setSelected(eject);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkOmni) {
            listeners.forEach(l -> l.omniChanged(isOmni()));
        } else if ((e.getSource() == cbBaseType) || (e.getSource() == cbMotiveType)) {
            listeners.forEach(l -> l.typeChanged(getBaseTypeIndex(), getMotiveTypeIndex(), getEntityType()));
        } else if (e.getSource() == cbStructure) {
            listeners.forEach(l -> l.structureChanged(getStructure()));
        } else if (e.getSource() == cbEngine) {
            listeners.forEach(l -> l.engineChanged(getEngine()));
        } else if (e.getSource() == cbGyro) {
            listeners.forEach(l -> l.gyroChanged(getGyroType()));
        } else if (e.getSource() == cbCockpit) {
            listeners.forEach(l -> l.cockpitChanged(getCockpitType()));
            refreshFullHeadEject();
        } else if (e.getSource() == cbEnhancement) {
            listeners.forEach(l -> l.enhancementChanged(getEnhancement()));
        } else if (e.getSource() == chkFullHeadEject) {
            listeners.forEach(l -> l.fullHeadEjectChanged(chkFullHeadEject.isSelected()));
        } else if (e.getSource() == btnResetChassis) {
            listeners.forEach(MekBuildListener::resetChassis);
        }
        refresh();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        }
        // Change from standard to superheavy or reverse will cause the structure tab to call setEntity()
        // and so cause a refresh
    }
}
