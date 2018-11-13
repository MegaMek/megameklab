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

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadVee;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.MekBuildListener;

/**
 * Construction options and systems for Meks.
 * 
 * @author Neoancient
 *
 */
public class MekChassisView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2620071922845931509L;

    List<MekBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(MekBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(MekBuildListener l) {
        listeners.remove(l);
    }
    
    public static final int BASE_TYPE_STANDARD         = 0;
    public static final int BASE_TYPE_INDUSTRIAL       = 1;
    public static final int BASE_TYPE_LAM              = 2;
    public static final int BASE_TYPE_QUADVEE          = 3;

    public static final int MOTIVE_TYPE_BIPED          = 0;
    public static final int MOTIVE_TYPE_QUAD           = 1;
    public static final int MOTIVE_TYPE_TRIPOD         = 2;

    public static final int MOTIVE_TYPE_LAM_STD        = 0;
    public static final int MOTIVE_TYPE_LAM_BM         = 1;

    public static final int MOTIVE_TYPE_QV_TRACKED     = 0;
    public static final int MOTIVE_TYPE_QV_WHEELED     = 1;

    // Engines that can be used by mechs and the order they appear in the combobox
    private final static int[] ENGINE_TYPES = {
            Engine.NORMAL_ENGINE, Engine.XL_ENGINE, Engine.XXL_ENGINE, Engine.FUEL_CELL, Engine.LIGHT_ENGINE,
            Engine.COMPACT_ENGINE, Engine.FISSION, Engine.COMBUSTION_ENGINE
    };
    // Industrial (and primitive) mechs can use non-fusion engines under standard rules, but cannot use
    // any fusion engines other than standard.
    private final static int[] INDUSTRIAL_ENGINE_TYPES = {
            Engine.NORMAL_ENGINE, Engine.FUEL_CELL, Engine.FISSION, Engine.COMBUSTION_ENGINE
    };
    // LAMs can only use fusion engines that are contained entirely within the center torso.
    private final static int[] LAM_ENGINE_TYPES = {
            Engine.NORMAL_ENGINE, Engine.COMPACT_ENGINE
    };
    
    // Internal structure for non-industrial mechs
    private final static int[] STRUCTURE_TYPES = {
            EquipmentType.T_STRUCTURE_STANDARD, EquipmentType.T_STRUCTURE_ENDO_STEEL,
            EquipmentType.T_STRUCTURE_ENDO_PROTOTYPE, EquipmentType.T_STRUCTURE_REINFORCED,
            EquipmentType.T_STRUCTURE_COMPOSITE, EquipmentType.T_STRUCTURE_ENDO_COMPOSITE
    };

    // Internal structure for superheavy battlemechs
    private final static int[] SUPERHEAVY_STRUCTURE_TYPES = {
            EquipmentType.T_STRUCTURE_STANDARD, EquipmentType.T_STRUCTURE_ENDO_STEEL,
            EquipmentType.T_STRUCTURE_ENDO_COMPOSITE
    };

    final private SpinnerNumberModel tonnageModel = new SpinnerNumberModel(20, 20, 100, 5);
    final private JSpinner spnTonnage = new JSpinner(tonnageModel);
    final private JCheckBox chkOmni = new JCheckBox("Omni");
    final private JComboBox<String> cbBaseType = new JComboBox<>();
    final private JComboBox<String> cbMotiveType = new JComboBox<String>();
    final private TechComboBox<EquipmentType> cbStructure = new TechComboBox<>(EquipmentType::getName);
    final private TechComboBox<Engine> cbEngine = new TechComboBox<>(e -> e.getEngineName().replaceAll("^\\d+ ", ""));
    final private CustomComboBox<Integer> cbGyro = new CustomComboBox<>(g -> Mech.getGyroTypeShortString(g));
    final private CustomComboBox<Integer> cbCockpit = new CustomComboBox<>(c -> Mech.getCockpitTypeString(c));
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
    
    private static final int[] GENERAL_COCKPITS = {
            Mech.COCKPIT_STANDARD, Mech.COCKPIT_SMALL, Mech.COCKPIT_COMMAND_CONSOLE,
            Mech.COCKPIT_TORSO_MOUNTED, Mech.COCKPIT_DUAL, Mech.COCKPIT_INTERFACE,
            Mech.COCKPIT_VRRP
    };
    
    private static final String[] ENHANCEMENT_NAMES = {
            "ISMASC", "CLMASC", "TSM", "ISSuperCooledMyomer"
    };
    
    public static final EquipmentType EJECTION_SEAT = EquipmentType.get("Ejection Seat (Industrial Mech)");
    
    private final ITechManager techManager;
    private String stdMotiveTooltip;
    private String lamMotiveTooltip;
    private String qvMotiveTooltip;
    
    public MekChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        baseTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.baseType.values").split(",")); //$NON-NLS-1$
        standardTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.motiveType.values").split(",")); //$NON-NLS-1$
        lamTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.lamType.values").split(",")); //$NON-NLS-1$
        qvTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.qvType.values").split(",")); //$NON-NLS-1$
        primitiveTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.primitiveType.values").split(",")); //$NON-NLS-1$
        primitiveMotiveTypesModel = new DefaultComboBoxModel<>(resourceMap.getString("MekChassisView.primitiveMotiveType.values").split(",")); //$NON-NLS-1$
        stdMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.tooltip"); //$NON-NLS-1$
        lamMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.LAM.tooltip"); //$NON-NLS-1$
        qvMotiveTooltip = resourceMap.getString("MekChassisView.cbMotiveType.QuadVee.tooltip"); //$NON-NLS-1$
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("MekChassisView.spnTonnage.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        setFieldSize(spnTonnage, spinnerSize);
        spnTonnage.setToolTipText(resourceMap.getString("MekChassisView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
        add(spnTonnage, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        chkOmni.setText(resourceMap.getString("MekChassisView.chkOmni.text")); //$NON-NLS-1$
        chkOmni.setToolTipText(resourceMap.getString("MekChassisView.chkOmni.tooltip")); //$NON-NLS-1$
        add(chkOmni, gbc);
        chkOmni.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbBaseType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        setFieldSize(cbBaseType, controlSize);
        cbBaseType.setToolTipText(resourceMap.getString("MekChassisView.cbBaseType.tooltip")); //$NON-NLS-1$
        add(cbBaseType, gbc);
        cbBaseType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbMotiveType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        setFieldSize(cbMotiveType, controlSize);
        cbMotiveType.setToolTipText(resourceMap.getString("MekChassisView.cbMotiveType.tooltip")); //$NON-NLS-1$
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbStructure.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        setFieldSize(cbStructure, controlSize);
        cbStructure.setToolTipText(resourceMap.getString("MekChassisView.cbStructure.tooltip")); //$NON-NLS-1$
        add(cbStructure, gbc);
        cbStructure.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbEngine.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        setFieldSize(cbEngine, controlSize);
        cbEngine.setToolTipText(resourceMap.getString("MekChassisView.cbEngine.tooltip")); //$NON-NLS-1$
        add(cbEngine, gbc);
        cbEngine.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbGyro.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        setFieldSize(cbGyro, controlSize);
        cbGyro.setToolTipText(resourceMap.getString("MekChassisView.cbGyro.tooltip")); //$NON-NLS-1$
        add(cbGyro, gbc);
        cbGyro.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbCockpit.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        setFieldSize(cbCockpit, controlSize);
        cbCockpit.setToolTipText(resourceMap.getString("MekChassisView.cbCockpit.tooltip")); //$NON-NLS-1$
        add(cbCockpit, gbc);
        cbCockpit.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("MekChassisView.cbEnhancement.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        setFieldSize(cbEnhancement, controlSize);
        cbEnhancement.setNullValue(resourceMap.getString("MekChassisView.cbEnhancement.null")); //$NON-NLS-1$
        cbEnhancement.setToolTipText(resourceMap.getString("MekChassisView.cbEnhancement.tooltip")); //$NON-NLS-1$
        add(cbEnhancement, gbc);
        cbEnhancement.addActionListener(this);
        
        chkFullHeadEject.setText(resourceMap.getString("MekChassisView.chkFullHeadEject.text")); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        add(chkFullHeadEject, gbc);
        chkFullHeadEject.setToolTipText(resourceMap.getString("MekChassisView.chkFullHeadEject.tooltip")); //$NON-NLS-1$
        chkFullHeadEject.addActionListener(this);
        
        btnResetChassis.setText(resourceMap.getString("MekChassisView.btnResetChassis.text")); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        add(btnResetChassis, gbc);
        btnResetChassis.setToolTipText(resourceMap.getString("MekChassisView.btnResetChassis.tooltip")); //$NON-NLS-1$
        btnResetChassis.addActionListener(this);
    }

    public void setFromEntity(Mech mech) {
        primitive = mech.isPrimitive();
        engineRating = mech.getEngine().getRating();
        refresh();
        setTonnage(mech.getWeight());
        setOmni(mech.isOmni());
        chkOmni.setEnabled(!mech.isPrimitive() && !mech.isIndustrial()
                && techManager.isLegal(Entity.getOmniAdvancement()));
        cbBaseType.removeActionListener(this);
        cbMotiveType.removeActionListener(this);
        if (primitive) {
            cbBaseType.setModel(primitiveTypesModel);
        } else {
            cbBaseType.setModel(baseTypesModel);
        }
        if (mech instanceof LandAirMech) {
            chkOmni.setEnabled(false);
            setBaseTypeIndex(BASE_TYPE_LAM);
            cbMotiveType.setModel(lamTypesModel);
            setMotiveTypeIndex(((LandAirMech)mech).getLAMType());
            cbMotiveType.setToolTipText(lamMotiveTooltip);
        } else if (mech instanceof QuadVee) {
            setBaseTypeIndex(BASE_TYPE_QUADVEE);
            cbMotiveType.setModel(qvTypesModel);
            setMotiveTypeIndex(((QuadVee)mech).getMotiveType());
            cbMotiveType.setToolTipText(qvMotiveTooltip);
        } else {
            setBaseTypeIndex(mech.isIndustrial()? BASE_TYPE_INDUSTRIAL : BASE_TYPE_STANDARD);
            if (primitive) {
                cbMotiveType.setModel(primitiveMotiveTypesModel);
            } else {
                cbMotiveType.setModel(standardTypesModel);
            }
            if ((mech.getEntityType() & Entity.ETYPE_TRIPOD_MECH) != 0) {
                setMotiveTypeIndex(MOTIVE_TYPE_TRIPOD);
            } else if ((mech.getEntityType() & Entity.ETYPE_QUAD_MECH) != 0) {
                setMotiveTypeIndex(MOTIVE_TYPE_QUAD);
            } else {
                setMotiveTypeIndex(MOTIVE_TYPE_BIPED);
            }
            cbMotiveType.setToolTipText(stdMotiveTooltip);
        }
        cbBaseType.addActionListener(this);
        cbMotiveType.addActionListener(this);
        setStructureType(EquipmentType.getStructureTypeName(mech.getStructureType(),
                TechConstants.isClan(mech.getStructureTechLevel())));
        setEngine(mech.getEngine());
        setGyroType(mech.getGyroType());
        setCockpitType(mech.getCockpitType());
        // A simple hasWorkingMisc() will not tell us whether we have IS or Clan MASC, so we need to search
        // the list for the first matching.
        Optional<EquipmentType> enh = mech.getMisc().stream().map(Mounted::getType)
                .filter(et -> (et.hasFlag(MiscType.F_MASC) && et.getSubType() == 0)
                    || et.hasFlag(MiscType.F_TSM)
                    || et.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || et.hasFlag(MiscType.F_SCM)).findFirst();
        if (enh.isPresent()) {
            setEnhancement(enh.get());
        } else {
            setEnhancement(null);
        }
        setFullHeadEject(mech.hasFullHeadEject());
        btnResetChassis.setEnabled(mech.isOmni());
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
        chkOmni.setEnabled(!isPrimitive()
                && (getBaseTypeIndex() != BASE_TYPE_INDUSTRIAL)
                && (getBaseTypeIndex() != BASE_TYPE_LAM)
                && techManager.isLegal(Entity.getOmniAdvancement()));
        chkOmni.addActionListener(this);
    }

    private void refreshTonnage() {
        int min = 20;
        int max = 100;
        spnTonnage.removeChangeListener(this);
        if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            max = 55;
        } else if ((getBaseTypeIndex() == BASE_TYPE_STANDARD)
                && techManager.isLegal(Mech.getTechAdvancement(Entity.ETYPE_MECH, false, false,
                        EntityWeightClass.WEIGHT_SUPER_HEAVY))) {
            max = 200;;
        }
        if (techManager.isLegal(Mech.getTechAdvancement(Entity.ETYPE_MECH, false, false,
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
        EquipmentType prevStructure = (EquipmentType)cbStructure.getSelectedItem();
        cbStructure.removeAllItems();
        cbStructure.showTechBase(isMixed);
        // Primitive/retro can only use standard/industrial structure. Industrial can only use industrial
        // at standard rules level. Superheavies can only use standard.
        if (isIndustrial()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_INDUSTRIAL, isClan);
            cbStructure.addItem(EquipmentType.get(name));
        } else if (isPrimitive()) {
            String name = EquipmentType.getStructureTypeName(EquipmentType.T_STRUCTURE_STANDARD, isClan);
            cbStructure.addItem(EquipmentType.get(name));
        } else {
            int[] structureTypes = isSuperheavy()?
                    SUPERHEAVY_STRUCTURE_TYPES : STRUCTURE_TYPES;
            for (int i : structureTypes) {
                String name = EquipmentType.getStructureTypeName(i, isClan);
                EquipmentType structure = EquipmentType.get(name);
                // LAMs cannot use bulky structure
                if ((null != structure) && techManager.isLegal(structure)
                        && ((getBaseTypeIndex() != BASE_TYPE_LAM)
                                || (structure.getCriticals(null) == 0))) {
                    cbStructure.addItem(structure);
                }
                name = EquipmentType.getStructureTypeName(i, !isClan);
                EquipmentType structure2 = EquipmentType.get(name);
                if ((null != structure2) && (structure2 != structure) 
                        && techManager.isLegal(structure2)
                        && ((getBaseTypeIndex() != BASE_TYPE_LAM)
                                || (structure2.getCriticals(null) == 0))) {
                    cbStructure.addItem(structure2);
                    // If we are allowing the opposite tech base it may be because we are using mixed
                    // tech but it also may be that we are in the transitional early Clan stage when
                    // IS equipment is available without a mixed base.
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
        Engine prevEngine = (Engine)cbEngine.getSelectedItem();
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
        Integer prev = (Integer)cbGyro.getSelectedItem();
        cbGyro.removeAllItems();
        if (isSuperheavy()) {
            cbGyro.addItem(Mech.GYRO_SUPERHEAVY);
        } else if (isPrimitive() || isIndustrial()) {
            cbGyro.addItem(Mech.GYRO_STANDARD);
        } else {
            for (int i = 0; i <= Mech.GYRO_NONE; i++) {
                if (techManager.isLegal(Mech.getGyroTechAdvancement(i))
                        && ((i != Mech.GYRO_XL) || (getBaseTypeIndex() !=  BASE_TYPE_LAM))) {
                    cbGyro.addItem(i);
                }
            }
        }
        cbGyro.setSelectedItem(prev);
        cbGyro.addActionListener(this);
        if (cbGyro.getSelectedIndex() < 0) {
            cbGyro.setSelectedIndex(0);
        }
    }
    
    private void refreshCockpit() {
        cbCockpit.removeActionListener(this);
        Integer prev = (Integer)cbCockpit.getSelectedItem();
        cbCockpit.removeAllItems();
        if ((getBaseTypeIndex() == BASE_TYPE_STANDARD) && (getMotiveTypeIndex() == MOTIVE_TYPE_TRIPOD)) {
            cbCockpit.addItem(isSuperheavy()? Mech.COCKPIT_SUPERHEAVY_TRIPOD : Mech.COCKPIT_TRIPOD);
        } else if (getBaseTypeIndex() == BASE_TYPE_LAM) {
            cbCockpit.addItem(Mech.COCKPIT_STANDARD);
            cbCockpit.addItem(Mech.COCKPIT_SMALL);
        } else if (getBaseTypeIndex() == BASE_TYPE_QUADVEE) {
            cbCockpit.addItem(Mech.COCKPIT_QUADVEE);
        } else if (isSuperheavy()) {
            cbCockpit.addItem(isIndustrial()? Mech.COCKPIT_SUPERHEAVY_INDUSTRIAL : Mech.COCKPIT_SUPERHEAVY);
        } else if (isPrimitive()) {
            cbCockpit.addItem(isIndustrial()? Mech.COCKPIT_PRIMITIVE_INDUSTRIAL : Mech.COCKPIT_PRIMITIVE);
        } else if (isIndustrial()) {
            cbCockpit.addItem(Mech.COCKPIT_INDUSTRIAL);
            if (techManager.isLegal(Mech.getIndustrialAdvFireConTA())) {
                cbCockpit.addItem(Mech.COCKPIT_STANDARD);
            }
        } else {
            for (int cockpitType : GENERAL_COCKPITS) {
                if (techManager.isLegal(Mech.getCockpitTechAdvancement(cockpitType))) {
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
        EquipmentType prev = (EquipmentType)cbEnhancement.getSelectedItem();
        cbEnhancement.removeAllItems();
        cbEnhancement.addItem(null);
        if (!isSuperheavy() && !isPrimitive()) {
            if (isIndustrial()) {
                EquipmentType eq = EquipmentType.get("Industrial TSM"); //$NON-NLS-1$
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
        chkFullHeadEject.setEnabled((getCockpitType() != Mech.COCKPIT_TORSO_MOUNTED)
                && (getCockpitType() != Mech.COCKPIT_VRRP)
                && (getCockpitType() != Mech.COCKPIT_COMMAND_CONSOLE)
                && techManager.isLegal(Mech.getFullHeadEjectAdvancement()));
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
        spnTonnage.setValue(Integer.valueOf((int)Math.ceil(tonnage)));
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
            return Entity.ETYPE_LAND_AIR_MECH;
        } else if (getBaseTypeIndex() == BASE_TYPE_QUADVEE) {
            return Entity.ETYPE_QUADVEE;
        } else if (getMotiveTypeIndex() == MOTIVE_TYPE_TRIPOD) {
            return Entity.ETYPE_TRIPOD_MECH;
        } else if (getMotiveTypeIndex() == MOTIVE_TYPE_QUAD) {
            return Entity.ETYPE_QUAD_MECH;
        } else {
            return Entity.ETYPE_BIPED_MECH;
        }
    }

    public int getMotiveTypeIndex() {
        return cbMotiveType.getSelectedIndex();
    }
    
    public void setMotiveTypeIndex(int index) {
        cbMotiveType.setSelectedIndex(index);
    }
    
    public EquipmentType getStructure() {
        return (EquipmentType)cbStructure.getSelectedItem();
    }
    
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
        // Clan and large flags are specific to the engine. the superheavy flag depends on the mech
        // and may have changed since the last refresh.
        int flags = e.getFlags() & (Engine.CLAN_ENGINE | Engine.LARGE_ENGINE);
        if (isSuperheavy()) {
            flags |= Engine.SUPERHEAVY_ENGINE;
        }
        return new Engine(getEngineRating(), e.getEngineType(), flags);
    }

    /**
     * Select the first engine in the list that matches engine type and flags,
     * ignoring any flags other than Clan. If no match can be found based on type and flags,
     * disregards Clan flag as well.
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
        return (Integer)cbGyro.getSelectedItem();
    }
    
    public void setGyroType(int gyro) {
        cbGyro.setSelectedItem(gyro);
    }

    public int getCockpitType() {
        return (Integer)cbCockpit.getSelectedItem();
    }
    
    public void setCockpitType(int cockpit) {
        cbCockpit.setSelectedItem(cockpit);
    }
    
    public EquipmentType getEnhancement() {
        return (EquipmentType)cbEnhancement.getSelectedItem();
    }
    
    public void setEnhancement(EquipmentType enhancement) {
        cbEnhancement.setSelectedItem(enhancement);
    }
    
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
