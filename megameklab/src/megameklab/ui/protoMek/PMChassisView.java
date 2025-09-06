/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.ProtoMek;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.ProtoMekBuildListener;
import megameklab.ui.util.CustomComboBox;

/**
 * Construction options and systems for ProtoMeks.
 *
 * @author Neoancient
 */
public class PMChassisView extends BuildView implements ActionListener, ChangeListener {
    List<ProtoMekBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(ProtoMekBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(ProtoMekBuildListener l) {
        listeners.remove(l);
    }

    public static final int MOTIVE_TYPE_BIPED = 0;
    public static final int MOTIVE_TYPE_QUAD = 1;
    public static final int MOTIVE_TYPE_GLIDER = 2;

    private String[] motiveTypeNames;
    final private SpinnerNumberModel tonnageModel = new SpinnerNumberModel(2, 2, 15, 1);
    final private JSpinner spnTonnage = new JSpinner(tonnageModel);
    final private CustomComboBox<Integer> cbMotiveType = new CustomComboBox<>(i -> motiveTypeNames[i]);
    final private JCheckBox chkMainGun = new JCheckBox();

    final private JCheckBox chkMyomerBooster = new JCheckBox();
    final private JCheckBox chkPartialWing = new JCheckBox();
    final private JCheckBox chkMagneticClamps = new JCheckBox();
    final private JCheckBox chkISInterface = new JCheckBox();

    private EquipmentType myomerBooster = null;
    private EquipmentType partialWing = null;
    private EquipmentType magneticClamps = null;

    private final ITechManager techManager;

    public PMChassisView(ITechManager techManager) {
        this.techManager = techManager;
        // Get the equipment based on the correct flags rather than relying on magic String literals.
        for (Enumeration<EquipmentType> e = EquipmentType.getAllTypes(); e.hasMoreElements(); ) {
            final EquipmentType eq = e.nextElement();
            if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT)) {
                if (eq.hasFlag(MiscType.F_MASC)) {
                    myomerBooster = eq;
                } else if (eq.hasFlag(MiscType.F_PARTIAL_WING)) {
                    partialWing = eq;
                } else if (eq.hasFlag(MiscType.F_MAGNETIC_CLAMP)) {
                    magneticClamps = eq;
                }
            }
        }
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        motiveTypeNames = resourceMap.getString("ProtomekChassisView.cbMotiveType.values").split(",");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblTonnage", "ProtomekChassisView.spnTonnage.text",
              "ProtomekChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        spnTonnage.setToolTipText(resourceMap.getString("ProtomekChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblMotiveType", "ProtomekChassisView.cbMotiveType.text"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        cbMotiveType.addItem(MOTIVE_TYPE_BIPED);
        cbMotiveType.addItem(MOTIVE_TYPE_QUAD);
        cbMotiveType.addItem(MOTIVE_TYPE_GLIDER);
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        chkMainGun.setText(resourceMap.getString("ProtomekChassisView.chkMainGun.text"));
        chkMainGun.setToolTipText(resourceMap.getString("ProtomekChassisView.chkMainGun.tooltip"));
        add(chkMainGun, gbc);
        chkMainGun.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblEnhancements", "ProtomekChassisView.lblEnhancements.text"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        chkMyomerBooster.setText(resourceMap.getString("ProtomekChassisView.chkMyomerBooster.text"));
        chkMyomerBooster.setToolTipText(resourceMap.getString("ProtomekChassisView.chkMyomerBooster.tooltip"));
        add(chkMyomerBooster, gbc);
        chkMyomerBooster.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        chkPartialWing.setText(resourceMap.getString("ProtomekChassisView.chkPartialWing.text"));
        chkPartialWing.setToolTipText(resourceMap.getString("ProtomekChassisView.chkPartialWing.tooltip"));
        add(chkPartialWing, gbc);
        chkPartialWing.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        chkMagneticClamps.setText(resourceMap.getString("ProtomekChassisView.chkMagneticClamps.text"));
        chkMagneticClamps.setToolTipText(resourceMap.getString("ProtomekChassisView.chkMagneticClamps.tooltip"));
        add(chkMagneticClamps, gbc);
        chkMagneticClamps.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        chkISInterface.setText(resourceMap.getString("ProtomekChassisView.chkISInterface.text"));
        chkISInterface.setToolTipText(resourceMap.getString("ProtomekChassisView.chkISInterface.tooltip"));
        add(chkISInterface, gbc);
        chkISInterface.addActionListener(this);
    }

    public void setFromEntity(ProtoMek proto) {
        refresh();
        setTonnage(proto.getWeight());
        cbMotiveType.removeActionListener(this);
        if (proto.isGlider()) {
            cbMotiveType.setSelectedItem(MOTIVE_TYPE_GLIDER);
        } else if (proto.isQuad()) {
            cbMotiveType.setSelectedItem(MOTIVE_TYPE_QUAD);
        } else {
            cbMotiveType.setSelectedItem(MOTIVE_TYPE_BIPED);
        }
        cbMotiveType.addActionListener(this);

        chkMyomerBooster.setSelected(proto.hasMisc(MiscType.F_MASC));
        chkPartialWing.setSelected(proto.hasMisc(MiscType.F_PARTIAL_WING));
        chkMagneticClamps.setSelected(proto.hasMisc(MiscType.F_MAGNETIC_CLAMP));
        chkISInterface.setSelected(proto.hasInterfaceCockpit());
        chkMainGun.setSelected(proto.hasMainGun());
    }

    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbMotiveType.setEnabled(false);
    }

    public boolean isUltraHeavy() {
        return getTonnage() > TestProtoMek.MAX_STD_TONNAGE;
    }

    public void refresh() {
        refreshTonnage();
        chkMyomerBooster.setVisible((null != myomerBooster) && techManager.isLegal(myomerBooster));
        chkPartialWing.setVisible((null != partialWing) && techManager.isLegal(partialWing));
        chkMagneticClamps.setVisible((null != magneticClamps)
              && (getMotiveType() == MOTIVE_TYPE_BIPED)
              && techManager.isLegal(magneticClamps));
        chkISInterface.setVisible(techManager.isLegal(ProtoMek.TA_INTERFACE_COCKPIT));
    }

    private void refreshTonnage() {
        int min = (int) TestProtoMek.MIN_TONNAGE;
        int max = (int) TestProtoMek.MAX_STD_TONNAGE;
        spnTonnage.removeChangeListener(this);
        if (techManager.isLegal(ProtoMek.TA_ULTRA)) {
            max = (int) TestProtoMek.MAX_TONNAGE;
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

    public double getTonnage() {
        return tonnageModel.getNumber().doubleValue();
    }

    public void setTonnage(double tonnage) {
        spnTonnage.setValue((int) Math.ceil(tonnage));
    }

    public int getMotiveType() {
        Object value = cbMotiveType.getSelectedItem();

        if (value instanceof Integer intValue) {
            return intValue;
        }

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbMotiveType) {
            listeners.forEach(l -> l.typeChanged(getMotiveType()));
        } else if (e.getSource() == chkMainGun) {
            listeners.forEach(l -> l.mainGunChanged(chkMainGun.isSelected()));
        } else if (e.getSource() == chkMyomerBooster) {
            listeners.forEach(l -> l.setEnhancement(myomerBooster, chkMyomerBooster.isSelected()));
        } else if (e.getSource() == chkPartialWing) {
            listeners.forEach(l -> l.setEnhancement(partialWing, chkPartialWing.isSelected()));
        } else if (e.getSource() == chkMagneticClamps) {
            listeners.forEach(l -> l.setEnhancement(magneticClamps, chkMagneticClamps.isSelected()));
        } else if (e.getSource() == chkISInterface) {
            listeners.forEach(l -> l.setISInterface(chkISInterface.isSelected()));
        }
        refresh();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        }
    }
}
