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
import java.util.function.Consumer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.LandAirMek;
import megamek.common.units.Mek;
import megamek.common.units.QuadVee;
import megamek.logging.MMLogger;
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
    private static final MMLogger LOGGER = MMLogger.create(BMChassisView.class);

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
    private final static int[] ENGINE_TYPES = {
          Engine.NORMAL_ENGINE, Engine.XL_ENGINE, Engine.XXL_ENGINE, Engine.FUEL_CELL, Engine.LIGHT_ENGINE,
          Engine.COMPACT_ENGINE, Engine.FISSION, Engine.COMBUSTION_ENGINE
    };
    // Industrial (and primitive) Meks can use non-fusion engines under standard rules, but cannot use
    // any fusion engines other than standard.
    private final static int[] INDUSTRIAL_ENGINE_TYPES = {
          Engine.NORMAL_ENGINE, Engine.FUEL_CELL, Engine.FISSION, Engine.COMBUSTION_ENGINE
    };
    // LAMs can only use fusion engines that are contained entirely within the center torso.
    private final static int[] LAM_ENGINE_TYPES = {
          Engine.NORMAL_ENGINE, Engine.COMPACT_ENGINE
    };

    // Internal structure for non-industrial Meks
    private final static int[] STRUCTURE_TYPES = {
          EquipmentType.T_STRUCTURE_STANDARD, EquipmentType.T_STRUCTURE_ENDO_STEEL,
          EquipmentType.T_STRUCTURE_ENDO_PROTOTYPE, EquipmentType.T_STRUCTURE_REINFORCED,
          EquipmentType.T_STRUCTURE_COMPOSITE, EquipmentType.T_STRUCTURE_ENDO_COMPOSITE
    };

    // Internal structure for superheavy battleMeks
    private final static int[] SUPERHEAVY_STRUCTURE_TYPES = {
          EquipmentType.T_STRUCTURE_STANDARD, EquipmentType.T_STRUCTURE_ENDO_STEEL,
          EquipmentType.T_STRUCTURE_ENDO_COMPOSITE
    };

    private static final EquipmentType HYBRID_STRUCTURE = new DisplayOnlyStructure(
                Mek.FRANKEN_MEK_STRUCTURE_HYBRID);

    final private SpinnerNumberModel tonnageModel = new SpinnerNumberModel(20, 20, 100, 5);
    final private JSpinner spnTonnage = new JSpinner(tonnageModel);
    final private JCheckBox chkOmni = new JCheckBox("Omni");
    final private JCheckBox chkFrankenMek = new JCheckBox("FrankenMek");
    final private JComboBox<String> cbBaseType = new JComboBox<>();
    final private JComboBox<String> cbMotiveType = new JComboBox<>();
    final private TechComboBox<EquipmentType> cbStructure = new TechComboBox<>(EquipmentType::getName);
    final private TechComboBox<Engine> cbEngine = new TechComboBox<>(e -> e.getEngineName().replaceAll("^\\d+ ", ""));
    final private CustomComboBox<Integer> cbGyro = new CustomComboBox<>(Mek::getGyroTypeShortString);
    final private CustomComboBox<Integer> cbCockpit = new CustomComboBox<>(i -> Mek.getCockpitTypeString(i,
          isIndustrial()));
    final private TechComboBox<EquipmentType> cbEnhancement = new TechComboBox<>(EquipmentType::getName);
    final private JCheckBox chkFullHeadEject = new JCheckBox();
    final private JCheckBox chkDNICockpitMod = new JCheckBox();
    final private JCheckBox chkEICockpit = new JCheckBox();
    final private JCheckBox chkDamageInterruptCircuit = new JCheckBox();
    final private JButton btnResetChassis = new JButton();

    private ComboBoxModel<String> baseTypesModel;
    private ComboBoxModel<String> standardTypesModel;
    private ComboBoxModel<String> lamTypesModel;
    private ComboBoxModel<String> qvTypesModel;
    private ComboBoxModel<String> primitiveTypesModel;
    private ComboBoxModel<String> primitiveMotiveTypesModel;

    private boolean primitive = false;
    private int engineRating = 20;
    private boolean suppressListenerNotifications = false;

    private static final int[] GENERAL_COCKPITS = {
          Mek.COCKPIT_STANDARD, Mek.COCKPIT_SMALL, Mek.COCKPIT_COMMAND_CONSOLE,
          Mek.COCKPIT_SMALL_COMMAND_CONSOLE, Mek.COCKPIT_TORSO_MOUNTED,
          Mek.COCKPIT_DUAL, Mek.COCKPIT_INTERFACE, Mek.COCKPIT_VRRP
    };

    private static final int[] INDUSTRIAL_COCKPITS = {
          Mek.COCKPIT_INDUSTRIAL, Mek.COCKPIT_STANDARD, Mek.COCKPIT_COMMAND_CONSOLE, Mek.COCKPIT_TORSO_MOUNTED
    };

    private static final String[] ENHANCEMENT_NAMES = {
          EquipmentTypeLookup.IS_MASC, EquipmentTypeLookup.CLAN_MASC,
          EquipmentTypeLookup.TSM, EquipmentTypeLookup.P_TSM, EquipmentTypeLookup.SCM
    };

    private final ITechManager techManager;
    private String stdMotiveTooltip;
    private String lamMotiveTooltip;
    private String qvMotiveTooltip;

    private static class DisplayOnlyStructure extends EquipmentType {
        DisplayOnlyStructure(String name) {
            this.name = name;
            internalName = name;
        }
    }

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
        add(createLabel(resourceMap, "lblTonnage", "MekChassisView.spnTonnage.text",
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
        chkOmni.addActionListener(this);

        gbc.gridx = 3;
        gbc.gridy = 0;
        chkFrankenMek.setText(resourceMap.getString("MekChassisView.chkFrankenMek.text"));
        chkFrankenMek.setToolTipText(resourceMap.getString("MekChassisView.chkFrankenMek.tooltip"));
        add(chkFrankenMek, gbc);
        chkFrankenMek.setVisible(false);
        chkFrankenMek.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap, "lblBaseType", "MekChassisView.cbBaseType.text",
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
        add(createLabel(resourceMap, "lblMotiveType", "MekChassisView.cbMotiveType.text",
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
        add(createLabel(resourceMap, "lblStructure", "MekChassisView.cbStructure.text",
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
        add(createLabel(resourceMap, "lblEngine", "MekChassisView.cbEngine.text",
              "MekChassisView.cbEngine.tooltip"), gbc);
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
        add(createLabel(resourceMap, "lblGyro", "MekChassisView.cbGyro.text",
              "MekChassisView.cbGyro.tooltip"), gbc);
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
        add(createLabel(resourceMap, "lblCockpit", "MekChassisView.cbCockpit.text",
              "MekChassisView.cbCockpit.tooltip"), gbc);
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
        add(createLabel(resourceMap, "lblEnhancement", "MekChassisView.cbEnhancement.text",
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

        chkDNICockpitMod.setText(resourceMap.getString("MekChassisView.chkDNICockpitMod.text"));
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        add(chkDNICockpitMod, gbc);
        chkDNICockpitMod.setToolTipText(resourceMap.getString("MekChassisView.chkDNICockpitMod.tooltip"));
        chkDNICockpitMod.addActionListener(this);

        chkEICockpit.setText(resourceMap.getString("MekChassisView.chkEICockpit.text"));
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        add(chkEICockpit, gbc);
        chkEICockpit.setToolTipText(resourceMap.getString("MekChassisView.chkEICockpit.tooltip"));
        chkEICockpit.addActionListener(this);

        chkDamageInterruptCircuit.setText(resourceMap.getString("MekChassisView.chkDamageInterruptCircuit.text"));
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        add(chkDamageInterruptCircuit, gbc);
        chkDamageInterruptCircuit.setToolTipText(resourceMap.getString(
              "MekChassisView.chkDamageInterruptCircuit.tooltip"));
        chkDamageInterruptCircuit.addActionListener(this);

        btnResetChassis.setText(resourceMap.getString("MekChassisView.btnResetChassis.text"));
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth = 3;
        add(btnResetChassis, gbc);
        btnResetChassis.setToolTipText(resourceMap.getString("MekChassisView.btnResetChassis.tooltip"));
        btnResetChassis.addActionListener(this);
    }

    public void setFromEntity(Mek mek) {
        boolean previousSuppression = suppressListenerNotifications;
        suppressListenerNotifications = true;
        try {
            primitive = mek.isPrimitive();
            engineRating = mek.getEngine().getRating();
            refresh();
            setTonnage(mek.getWeight());
            setOmni(mek.isOmni());
            setFrankenMek(mek.isFrankenMek());
            chkOmni.setEnabled(!mek.isPrimitive() && !mek.isIndustrial()
                  && techManager.isLegal(Entity.getOmniAdvancement()));
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
            setStructureType(mek.isFrankenMek() ? mek.getFrankenMekStructureDisplayName()
                : EquipmentType.getStructureTypeName(mek.getStructureType(),
                TechConstants.isClan(mek.getStructureTechLevel())));
            setEngine(mek.getEngine());
            setGyroType(mek.getGyroType());
            setCockpitType(mek.getCockpitType());
            // A simple hasWorkingMisc() will not tell us whether we have IS or Clan MASC, so we need to search
            // the list for the first matching.
            Optional<MiscType> enh = mek.getMisc().stream().map(Mounted::getType)
                  .filter(et -> (et.hasFlag(MiscType.F_MASC) && !et.hasFlag(MiscTypeFlag.S_SUPERCHARGER))
                        || et.hasFlag(MiscType.F_TSM)
                        || et.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                        || et.hasFlag(MiscType.F_SCM)).findFirst();
            if (enh.isPresent()) {
                setEnhancement(enh.get());
            } else {
                setEnhancement(null);
            }
            setFullHeadEject(mek.hasFullHeadEject());
            setDNICockpitMod(mek.hasDNICockpitMod());
            setEICockpit(mek.hasEiCockpit());
            setDamageInterruptCircuit(mek.hasDamageInterruptCircuit());
            btnResetChassis.setEnabled(mek.isOmni());
        } finally {
            suppressListenerNotifications = previousSuppression;
        }
    }

    public void refreshFrankenMekState(Mek mek) {
        boolean previousSuppression = suppressListenerNotifications;
        suppressListenerNotifications = true;
        try {
            setFrankenMek(mek.isFrankenMek());
            refreshStructure();
            setStructureType(mek.getFrankenMekStructureDisplayName());
        } finally {
            suppressListenerNotifications = previousSuppression;
        }
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
        refreshDNICockpitMod();
        refreshEICockpit();
        refreshDamageInterruptCircuit();
        refreshFrankenMek();

        chkOmni.removeActionListener(this);
        chkOmni.setEnabled(!isPrimitive()
              && (getBaseTypeIndex() != BASE_TYPE_INDUSTRIAL)
              && (getBaseTypeIndex() != BASE_TYPE_LAM)
              && techManager.isLegal(Entity.getOmniAdvancement()));
        chkOmni.addActionListener(this);
    }

    private void refreshTonnage() {
        int min = getMinimumTonnage();
        int max = getMaximumTonnage();
        spnTonnage.removeChangeListener(this);
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
        cbStructure.removeActionListener(this);
        if (isFrankenMek()) {
            cbStructure.setEnabled(false);
            cbStructure.addActionListener(this);
            return;
        }
        cbStructure.setEnabled(true);
        EquipmentType prevStructure = (EquipmentType) cbStructure.getSelectedItem();
        cbStructure.removeAllItems();
        List<EquipmentType> availableStructures = getAvailableStructures();
        cbStructure.showTechBase(techManager.useMixedTech() || needsStructureTechBase(availableStructures));
        availableStructures.forEach(cbStructure::addItem);
        cbStructure.setSelectedItem(prevStructure);
        cbStructure.addActionListener(this);
        if (cbStructure.getSelectedIndex() < 0) {
            cbStructure.setSelectedIndex(0);
        }
    }

    public List<EquipmentType> getAvailableStructures() {
        List<EquipmentType> availableStructures = new ArrayList<>();
        boolean isClan = techManager.useClanTechBase();
        if (isIndustrial()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_INDUSTRIAL, isClan);
            addAvailableStructure(availableStructures, EquipmentType.get(name));
        } else if (isPrimitive()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_STANDARD, isClan);
            addAvailableStructure(availableStructures, EquipmentType.get(name));
        } else {
            int[] structureTypes = isSuperheavy() ?
                  SUPERHEAVY_STRUCTURE_TYPES : STRUCTURE_TYPES;
            for (int i : structureTypes) {
                String name = EquipmentType.getStructureTypeName(i, isClan);
                EquipmentType structure = EquipmentType.get(name);
                // LAMs cannot use bulky structure
                addAvailableStructure(availableStructures, structure);
                name = EquipmentType.getStructureTypeName(i, !isClan);
                EquipmentType structure2 = EquipmentType.get(name);
                addAvailableStructure(availableStructures, structure2);
            }
        }
        return availableStructures;
    }

    private void addAvailableStructure(List<EquipmentType> structures, EquipmentType structure) {
        if ((structure != null) && !structures.contains(structure) && techManager.isLegal(structure)
              && ((getBaseTypeIndex() != BASE_TYPE_LAM) || (structure.getNumCriticalSlots(null) == 0))) {
            structures.add(structure);
        }
    }

    private boolean needsStructureTechBase(List<EquipmentType> structures) {
        for (int first = 0; first < structures.size(); first++) {
            for (int second = first + 1; second < structures.size(); second++) {
                if ((structures.get(first) != structures.get(second))
                      && structures.get(first).getName().equals(structures.get(second).getName())) {
                    return true;
                }
            }
        }
        return false;
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
                if (techManager.isLegal(Mek.getGyroTechAdvancement(i))
                      && ((i != Mek.GYRO_XL) || (getBaseTypeIndex() != BASE_TYPE_LAM))) {
                    cbGyro.addItem(i);
                }
            }
        }
        if ((cbCockpit.getSelectedItem() != null)
              && cbCockpit.getSelectedItem().equals(Mek.COCKPIT_INTERFACE)) {
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
        if (((getBaseTypeIndex() == BASE_TYPE_STANDARD) || getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL)
              && (getMotiveTypeIndex() == MOTIVE_TYPE_TRIPOD)) {
            if (isIndustrial()) {
                cbCockpit.addItem(isSuperheavy() ?
                      Mek.COCKPIT_SUPERHEAVY_TRIPOD_INDUSTRIAL : Mek.COCKPIT_TRIPOD_INDUSTRIAL);
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
                // If the date is late enough, include primitive cockpit with advanced fire control
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
        if ((cbCockpit.getSelectedIndex() < 0)
              && (cbCockpit.getModel().getSize() > 0)) {
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
        chkFullHeadEject.setEnabled((cockpitType != null)
              && (cockpitType != Mek.COCKPIT_TORSO_MOUNTED)
              && (cockpitType != Mek.COCKPIT_VRRP)
              && (cockpitType != Mek.COCKPIT_COMMAND_CONSOLE)
              && techManager.isLegal(Mek.getFullHeadEjectAdvancement()));
        chkFullHeadEject.addActionListener(this);
    }

    private void refreshDNICockpitMod() {
        chkDNICockpitMod.removeActionListener(this);
        EquipmentType dniEquipment = EquipmentType.get("DNICockpitModification");
        boolean isLegal = (dniEquipment != null) && techManager.isLegal(dniEquipment);
        chkDNICockpitMod.setVisible(isLegal);
        if (!isLegal && chkDNICockpitMod.isSelected()) {
            chkDNICockpitMod.setSelected(false);
            notifyListeners(l -> l.dniCockpitModChanged(false));
        }
        chkDNICockpitMod.addActionListener(this);
    }

    private void refreshEICockpit() {
        chkEICockpit.removeActionListener(this);
        EquipmentType eiEquipment = EquipmentType.get("EIInterface");
        boolean isLegal = (eiEquipment != null) && techManager.isLegal(eiEquipment);
        chkEICockpit.setVisible(isLegal);
        if (!isLegal && chkEICockpit.isSelected()) {
            chkEICockpit.setSelected(false);
            notifyListeners(l -> l.eiCockpitChanged(false));
        }
        chkEICockpit.addActionListener(this);
    }

    private void refreshDamageInterruptCircuit() {
        chkDamageInterruptCircuit.removeActionListener(this);
        EquipmentType dicEquipment = EquipmentType.get("DamageInterruptCircuit");
        boolean isLegal = (dicEquipment != null) && techManager.isLegal(dicEquipment);
        chkDamageInterruptCircuit.setVisible(isLegal);
        if (!isLegal && chkDamageInterruptCircuit.isSelected()) {
            chkDamageInterruptCircuit.setSelected(false);
            notifyListeners(l -> l.damageInterruptCircuitChanged(false));
        }
        chkDamageInterruptCircuit.addActionListener(this);
    }

    private void refreshFrankenMek() {
        chkFrankenMek.removeActionListener(this);
        boolean isExperimentalOrUnofficial = techManager.getTechLevel().compareTo(SimpleTechLevel.EXPERIMENTAL) >= 0;
        chkFrankenMek.setVisible(isExperimentalOrUnofficial);
        if (!isExperimentalOrUnofficial && chkFrankenMek.isSelected()) {
            chkFrankenMek.setSelected(false);
            notifyListeners(l -> l.frankenMekChanged(false));
        }
        chkFrankenMek.addActionListener(this);
    }

    private void notifyListeners(Consumer<MekBuildListener> notifier) {
        if (suppressListenerNotifications) {
            return;
        }
        listeners.forEach(notifier);
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
        boolean allowNonFusion = isIndustrial() || isPrimitive() || (!isSuperheavy()
              && techManager.getTechLevel().compareTo(SimpleTechLevel.EXPERIMENTAL) >= 0);
        for (int i : engineTypes) {
            Engine e = new Engine(getEngineRating(), i, flags);
            if (e.engineValid && (e.isFusion() || allowNonFusion)
                  && techManager.isLegal(e)) {
                retVal.add(e);
            }
            // Only add the opposite tech base if the engine is different.
            if (isMixed && e.getSideTorsoCriticalSlots().length > 0) {
                e = new Engine(getEngineRating(), i, altFlags);
                if (e.engineValid && (e.isFusion() || allowNonFusion)
                      && techManager.isLegal(e)) {
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
        spnTonnage.removeChangeListener(this);
        spnTonnage.setValue((int) Math.ceil(tonnage));
        spnTonnage.addChangeListener(this);
    }

    public int clampTonnage(double tonnage) {
        return Math.min(getMaximumTonnage(), Math.max(getMinimumTonnage(), (int) Math.ceil(tonnage)));
    }

    public int getMinimumTonnage() {
        return techManager.isLegal(Mek.getTechAdvancement(Entity.ETYPE_MEK, false, false,
              EntityWeightClass.WEIGHT_ULTRA_LIGHT)) ? 10 : 20;
    }

    public int getMaximumTonnage() {
        if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            return 55;
        }
        if (((getBaseTypeIndex() == BASE_TYPE_STANDARD) || (getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL))
              && techManager.isLegal(Mek.getTechAdvancement(Entity.ETYPE_MEK, false,
              getBaseTypeIndex() == BASE_TYPE_INDUSTRIAL, EntityWeightClass.WEIGHT_SUPER_HEAVY))) {
            return 200;
        }
        return 100;
    }

    public boolean isOmni() {
        return chkOmni.isSelected() && chkOmni.isEnabled();
    }

    public void setOmni(boolean omni) {
        chkOmni.setSelected(omni);
        if (omni) {
            chkFrankenMek.setSelected(false);
        }
    }

    public boolean isFrankenMek() {
        return chkFrankenMek.isSelected() && chkFrankenMek.isVisible();
    }

    public void setFrankenMek(boolean frankenMek) {
        chkFrankenMek.setSelected(frankenMek);
        if (frankenMek) {
            chkOmni.setSelected(false);
        }
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

    public @Nullable EquipmentType getStructure() {
        EquipmentType selectedStructure = (EquipmentType) cbStructure.getSelectedItem();
        return (selectedStructure == HYBRID_STRUCTURE) ? null : selectedStructure;
    }

    @Deprecated(since = "0.51.0", forRemoval = true)
    public void setStructureType(EquipmentType structure) {
        cbStructure.setSelectedItem(structure);
    }

    public void setStructureType(String structureName) {
        cbStructure.removeActionListener(this);
        if (Mek.FRANKEN_MEK_STRUCTURE_HYBRID.equals(structureName)) {
            if (!containsStructure(HYBRID_STRUCTURE)) {
                cbStructure.addItem(HYBRID_STRUCTURE);
            }
            cbStructure.setSelectedItem(HYBRID_STRUCTURE);
        } else {
            EquipmentType structure = findStructure(structureName);
            if (structure == null) {
                structure = getFallbackStructure();
                LOGGER.warn("Could not find structure type '{}'; using '{}' instead.", structureName,
                      structure == null ? "no fallback structure" : structure.getName());
            }
            if ((structure != null) && !containsStructure(structure)) {
                cbStructure.addItem(structure);
            }
            if (structure != null) {
                cbStructure.setSelectedItem(structure);
            }
        }
        cbStructure.setEnabled(!isFrankenMek());
        cbStructure.addActionListener(this);
    }

    private boolean containsStructure(EquipmentType structure) {
        for (int index = 0; index < cbStructure.getItemCount(); index++) {
            if (cbStructure.getItemAt(index) == structure) {
                return true;
            }
        }
        return false;
    }

    private @Nullable EquipmentType findStructure(String structureName) {
        if (structureName == null) {
            return null;
        }
        EquipmentType structure = EquipmentType.get(structureName);
        if (structure != null) {
            return structure;
        }
        for (int index = 0; index < cbStructure.getItemCount(); index++) {
            EquipmentType item = cbStructure.getItemAt(index);
            if ((item != null) && item.getName().equals(structureName)) {
                return item;
            }
        }
        return getAvailableStructures().stream()
              .filter(item -> item.getName().equals(structureName))
              .findFirst()
              .orElse(null);
    }

    private @Nullable EquipmentType getFallbackStructure() {
        EquipmentType selectedStructure = getStructure();
        if (selectedStructure != null) {
            return selectedStructure;
        }
        List<EquipmentType> availableStructures = getAvailableStructures();
        if (!availableStructures.isEmpty()) {
            return availableStructures.get(0);
        }
        return EquipmentType.get(EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_STANDARD,
              techManager.useClanTechBase()));
    }

    public Engine getEngine() {
        Engine e = (Engine) cbEngine.getSelectedItem();
        if (null == e) {
            return null;
        }
        // Clan flag is specific to the engine. the superheavy and large flags depend on the mek
        // and rating and may have changed since the last refresh.
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
     * Select the first engine in the list that matches engine type and flags, ignoring any flags other than Clan. If no
     * match can be found based on type and flags, disregards Clan flag as well.
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
        Object value = cbGyro.getSelectedItem();

        if (value instanceof Integer intValue) {
            return intValue;
        }

        return 0;
    }

    public void setGyroType(int gyro) {
        cbGyro.setSelectedItem(gyro);
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

    public EquipmentType getEnhancement() {
        return (EquipmentType) cbEnhancement.getSelectedItem();
    }

    public void setEnhancement(EquipmentType enhancement) {
        cbEnhancement.setSelectedItem(enhancement);
    }

    @Deprecated(since = "0.51.0", forRemoval = true)
    public boolean hasFullHeadEject() {
        return chkFullHeadEject.isSelected() && chkFullHeadEject.isEnabled();
    }

    public void setFullHeadEject(boolean eject) {
        chkFullHeadEject.setSelected(eject);
    }

    @Deprecated(since = "0.51.0", forRemoval = true)
    public boolean hasDNICockpitMod() {
        return chkDNICockpitMod.isSelected() && chkDNICockpitMod.isEnabled();
    }

    public void setDNICockpitMod(boolean hasMod) {
        chkDNICockpitMod.setSelected(hasMod);
    }

    @Deprecated(since = "0.51.0", forRemoval = true)
    public boolean hasEICockpit() {
        return chkEICockpit.isSelected() && chkEICockpit.isEnabled();
    }

    public void setEICockpit(boolean hasEI) {
        chkEICockpit.setSelected(hasEI);
    }

    @Deprecated(since = "0.51.0", forRemoval = true)
    public boolean hasDamageInterruptCircuit() {
        return chkDamageInterruptCircuit.isSelected() && chkDamageInterruptCircuit.isEnabled();
    }

    public void setDamageInterruptCircuit(boolean hasDIC) {
        chkDamageInterruptCircuit.setSelected(hasDIC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (suppressListenerNotifications) {
            return;
        }
        if (e.getSource() == chkOmni) {
            if (chkOmni.isSelected() && chkFrankenMek.isSelected()) {
                setFrankenMek(false);
                notifyListeners(l -> l.frankenMekChanged(false));
            }
            notifyListeners(l -> l.omniChanged(isOmni()));
        } else if (e.getSource() == chkFrankenMek) {
            if (chkFrankenMek.isSelected() && chkOmni.isSelected()) {
                setOmni(false);
                notifyListeners(l -> l.omniChanged(false));
            }
            notifyListeners(l -> l.frankenMekChanged(isFrankenMek()));
        } else if ((e.getSource() == cbBaseType) || (e.getSource() == cbMotiveType)) {
            notifyListeners(l -> l.typeChanged(getBaseTypeIndex(), getMotiveTypeIndex(), getEntityType()));
        } else if ((e.getSource() == cbStructure) && !isFrankenMek()) {
            EquipmentType structure = getStructure();
            if (structure != null) {
                notifyListeners(l -> l.structureChanged(structure));
            }
        } else if (e.getSource() == cbEngine) {
            notifyListeners(l -> l.engineChanged(getEngine()));
        } else if (e.getSource() == cbGyro) {
            notifyListeners(l -> l.gyroChanged(getGyroType()));
        } else if (e.getSource() == cbCockpit) {
            notifyListeners(l -> l.cockpitChanged(getCockpitType()));
            refreshFullHeadEject();
        } else if (e.getSource() == cbEnhancement) {
            notifyListeners(l -> l.enhancementChanged(getEnhancement()));
        } else if (e.getSource() == chkFullHeadEject) {
            notifyListeners(l -> l.fullHeadEjectChanged(chkFullHeadEject.isSelected()));
        } else if (e.getSource() == chkDNICockpitMod) {
            notifyListeners(l -> l.dniCockpitModChanged(chkDNICockpitMod.isSelected()));
        } else if (e.getSource() == chkEICockpit) {
            notifyListeners(l -> l.eiCockpitChanged(chkEICockpit.isSelected()));
        } else if (e.getSource() == chkDamageInterruptCircuit) {
            notifyListeners(l -> l.damageInterruptCircuitChanged(chkDamageInterruptCircuit.isSelected()));
        } else if (e.getSource() == btnResetChassis) {
            notifyListeners(MekBuildListener::resetChassis);
        }
        refresh();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (suppressListenerNotifications) {
            return;
        }
        if (e.getSource() == spnTonnage) {
            notifyListeners(l -> l.tonnageChanged(getTonnage()));
        }
        // Change from standard to superheavy or reverse will cause the structure tab to call setEntity()
        // and so cause a refresh
    }
}
