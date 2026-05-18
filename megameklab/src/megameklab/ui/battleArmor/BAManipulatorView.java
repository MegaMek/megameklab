/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.ConstructionUtil;
import megamek.common.verifier.TestBattleArmor;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

import static megamek.common.battleArmor.BattleArmor.MOUNT_LOC_LEFT_ARM;
import static megamek.common.battleArmor.BattleArmor.MOUNT_LOC_RIGHT_ARM;
import static megamek.common.equipment.EquipmentTypeLookup.BA_MODULAR_EQUIPMENT_ADAPTOR;
import static megamek.common.verifier.TestBattleArmor.BAManipulator;

public class BAManipulatorView extends IView {

    private static final MMLogger LOGGER = MMLogger.create(BAManipulatorView.class);
    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");
    private static final String COMBO_ERROR =
          "Manipulator Combo choice could not be parsed to TestBattleArmor.BAManipulator object: %s";

    private static final MiscType MODULAR_MOUNT = (MiscType) EquipmentType.get(BA_MODULAR_EQUIPMENT_ADAPTOR);

    private final JCheckBox leftModularSelector = new JCheckBox(I18N.getString("BAManipulatorView.mea"));
    private final JCheckBox rightModularSelector = new JCheckBox(I18N.getString("BAManipulatorView.mea"));

    private final CustomComboBox<String> leftManipulatorSelect = new CustomComboBox<>(this::manipulatorDisplayName);
    private final CustomComboBox<String> rightManipulatorSelect = new CustomComboBox<>(this::manipulatorDisplayName);

    private final SpinnerNumberModel cargoLifterCapacityModel = new SpinnerNumberModel(0.5, 0.5, 80, 0.5);
    private final JSpinner cargoLifterCapacity = new JSpinner(cargoLifterCapacityModel);
    private final JLabel lblSize = createLabel(I18N.getString("BAManipulatorView.cargoCapacity"));

    private final ITechManager techManager;
    private RefreshListener refreshListener;

    /** Set to true whenever input fields are changed programmatically. Listeners ignore events when true. */
    private boolean ignoreEvents = false;

    public BAManipulatorView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        this.techManager = techManager;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = BuildView.STANDARD_INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(createLabel(I18N.getString("BAManipulatorView.leftArm")), gbc);
        add(leftManipulatorSelect, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(leftModularSelector, gbc);

        gbc.gridy++;
        add(Box.createVerticalStrut(10), gbc);

        gbc.gridy++;
        add(createLabel(I18N.getString("BAManipulatorView.rightArm")), gbc);
        add(rightManipulatorSelect, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(rightModularSelector, gbc);

        gbc.gridy++;
        add(Box.createVerticalStrut(10), gbc);

        gbc.gridy++;
        add(lblSize, gbc);
        add(cargoLifterCapacity, gbc);

        leftManipulatorSelect.addActionListener(this::manipulatorSelected);
        rightManipulatorSelect.addActionListener(this::manipulatorSelected);
        cargoLifterCapacity.addChangeListener(this::cargoSizeEdited);
        leftModularSelector.addActionListener(this::modularSelected);
        rightModularSelector.addActionListener(this::modularSelected);
    }

    public void addRefreshedListener(RefreshListener l) {
        refreshListener = l;
    }

    private void doRefresh() {
        if (refreshListener != null) {
            setFromEntity();
            refreshListener.refreshBuild();
            refreshListener.refreshSummary();
            refreshListener.refreshPreview();
            refreshListener.refreshStatus();
        }
    }

    public JLabel createLabel(String text) {
        return new JLabel(text, SwingConstants.RIGHT);
    }

    /**
     * Extracts the actual name of the manipulator from the full equipment name
     */
    private String manipulatorDisplayName(String name) {
        int start = name.indexOf("[");
        if (start > 0) {
            return name.substring(start + 1).replace("]", "");
        }
        return name;
    }

    void setFromEntity() {
        try {
            ignoreEvents = true;

            Vector<String> validManipulators = new Vector<>();
            validManipulators.add(BattleArmor.MANIPULATOR_TYPE_STRINGS[BattleArmor.MANIPULATOR_NONE]);
            for (BAManipulator manipulator : BAManipulator.values()) {
                EquipmentType et = manipulator.miscType();
                if ((null != et) && techManager.isLegal(et)) {
                    validManipulators.add(et.getName());
                }
            }

            leftManipulatorSelect.setModel(new DefaultComboBoxModel<>(validManipulators));
            rightManipulatorSelect.setModel(new DefaultComboBoxModel<>(validManipulators));

            BAManipulator manipulator = BAManipulator.getManipulator(getBattleArmor().getLeftManipulatorName());
            if (manipulator != null) {
                leftManipulatorSelect.setSelectedItem(BattleArmor.MANIPULATOR_NAME_STRINGS[manipulator.type]);
            }

            manipulator = BAManipulator.getManipulator(getBattleArmor().getRightManipulatorName());
            if (manipulator != null) {
                rightManipulatorSelect.setSelectedItem(BattleArmor.MANIPULATOR_NAME_STRINGS[manipulator.type]);
                rightManipulatorSelect.setEnabled(!manipulator.pairMounted);
                Mounted<?> leftManipulator = getBattleArmor().getLeftManipulator();
                if (leftManipulator != null && leftManipulator.is(EquipmentTypeLookup.BA_MANIPULATOR_CARGO_LIFTER)) {
                    cargoLifterCapacityModel.setValue(leftManipulator.getSize());
                    cargoLifterCapacity.setVisible(true);
                } else {
                    cargoLifterCapacity.setVisible(false);
                }
                lblSize.setVisible(cargoLifterCapacity.isVisible());
            }

            leftModularSelector.setSelected(
                  getBattleArmor().hasMiscInMountLocation(BA_MODULAR_EQUIPMENT_ADAPTOR, MOUNT_LOC_LEFT_ARM));
            rightModularSelector.setSelected(
                  getBattleArmor().hasMiscInMountLocation(BA_MODULAR_EQUIPMENT_ADAPTOR, MOUNT_LOC_RIGHT_ARM));

            if (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD) {
                leftModularSelector.setEnabled(false);
                rightModularSelector.setEnabled(false);
                leftManipulatorSelect.setEnabled(false);
                rightManipulatorSelect.setEnabled(false);
            } else {
                leftModularSelector.setEnabled(true);
                rightModularSelector.setEnabled(true);
                leftManipulatorSelect.setEnabled(true);
                rightManipulatorSelect.setEnabled(manipulator == null || !manipulator.pairMounted);
            }
        } finally {
            ignoreEvents = false;
        }
    }

    /**
     * Eventhandler for selecting a manipulator in one of the two dropdowns
     *
     * @param event the Swing event
     */
    public void manipulatorSelected(ActionEvent event) {
        if (ignoreEvents) {
            return;
        }
        int location = event.getSource() == leftManipulatorSelect ? MOUNT_LOC_LEFT_ARM : MOUNT_LOC_RIGHT_ARM;
        String name = (String) ((JComboBox<?>) event.getSource()).getSelectedItem();
        BAManipulator manipulatorItem = BAManipulator.getManipulator(name);
        if (manipulatorItem == null) {
            throw new IllegalStateException(COMBO_ERROR.formatted(name));
        }
        MiscMounted currentManipulator = getBattleArmor().getManipulator(location);
        // only react if the manipulator has actually changed
        if (currentManipulator == null || !currentManipulator.is(manipulatorItem.internalName)) {
            setManipulators(manipulatorItem, location);
            doRefresh();
        }
    }

    /**
     * Eventhandler for (de)selecting one of the two Modular Equipment Adaptors
     *
     * @param event the Swing event
     */
    public void modularSelected(ActionEvent event) {
        if (ignoreEvents) {
            return;
        }
        JCheckBox modularCheckBox = (JCheckBox) event.getSource();
        int location = event.getSource() == leftModularSelector ? MOUNT_LOC_LEFT_ARM : MOUNT_LOC_RIGHT_ARM;

        if (modularCheckBox.isSelected()) {
            if (getBattleArmor().hasMiscInMountLocation(BA_MODULAR_EQUIPMENT_ADAPTOR, location)) {
                // equipment is already there, no action needed
                return;
            }
            MiscMounted modularMountMisc = new MiscMounted(getBattleArmor(), MODULAR_MOUNT);
            if (!TestBattleArmor.isMountLegal(getBattleArmor(), modularMountMisc, location)) {
                handleNoRoomForModularMount(modularCheckBox);
                return;
            }

            try {
                modularMountMisc.setBaMountLoc(location);
                getBattleArmor().addEquipment(modularMountMisc, BattleArmor.LOC_SQUAD, false);
                doRefresh();
            } catch (LocationFullException ignored) {
                // currently isn't thrown on BA
            }
        } else {
            var modularMount = getModularMount(location);
            if (modularMount.isPresent()) {
                ConstructionUtil.removeMounted(getBattleArmor(), modularMount.get());
                doRefresh();
            }
        }
    }

    private void handleNoRoomForModularMount(JCheckBox checkBox) {
        JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this),
              I18N.getString("BAManipulatorView.meaCannotAdd"));
        ignoreEvents = true;
        checkBox.setSelected(false);
        ignoreEvents = false;
    }

    /**
     * Eventhandler for changing the cargo lifter size value
     *
     * @param event the Swing event
     */
    public void cargoSizeEdited(ChangeEvent event) {
        if (ignoreEvents) {
            return;
        }
        setManipulatorSize(MOUNT_LOC_LEFT_ARM, cargoLifterCapacityModel.getNumber().doubleValue());
        setManipulatorSize(MOUNT_LOC_RIGHT_ARM, cargoLifterCapacityModel.getNumber().doubleValue());
        doRefresh();
    }

    /**
     * Adds and removes manipulator MiscMounteds on the unit so that the manipulator on the given mountLoc arm is the
     * one given as newManipulator (which may be none). Also updates the other arm if necessary.
     *
     * @param newManipulator The new manipulator type
     * @param mountLoc       one of the two arm locations (MOUNT_LOC_x_ARM)
     */
    private void setManipulators(BAManipulator newManipulator, int mountLoc) {
        MiscMounted currentManipulator = getBattleArmor().getManipulator(mountLoc);
        setManipulator(newManipulator, mountLoc);

        if (newManipulator.pairMounted) {
            setManipulator(newManipulator, otherArm(mountLoc));

        } else if (currentManipulator != null && isPairedManipulator(currentManipulator.getType())) {
            // when the previous manipulator was pair-mounted but the new one is not, remove the old on the other arm
            MiscMounted secondManipulator = getBattleArmor().getManipulator(otherArm(mountLoc));
            if (secondManipulator != null) {
                UnitUtil.removeMounted(getBattleArmor(), secondManipulator);
            }
        }
    }

    /**
     * Adds and removes manipulator MiscMounteds on the unit so that the manipulator on the given mountLoc arm is the
     * one given as newManipulator (which may be none). Does not touch the other arm. Should only be called from
     * setManipulators().
     *
     * @param newManipulator The new manipulator type
     * @param mountLoc       one of the two arm locations (MOUNT_LOC_x_ARM)
     */
    private void setManipulator(BAManipulator newManipulator, int mountLoc) {
        MiscMounted currentManipulator = getBattleArmor().getManipulator(mountLoc);
        if (currentManipulator != null) {
            ConstructionUtil.removeMounted(getBattleArmor(), currentManipulator);
        }
        if (newManipulator != BAManipulator.NONE) {
            try {
                Mounted<?> manipulator = getBattleArmor().addEquipment(newManipulator.miscType(),
                      BattleArmor.LOC_SQUAD);
                manipulator.setBaMountLoc(mountLoc);
            } catch (LocationFullException ex) {
                LOGGER.error("Could not mount {}", newManipulator, ex);
            }
        }
    }

    private int otherArm(int armLocation) {
        return armLocation == MOUNT_LOC_LEFT_ARM ? MOUNT_LOC_RIGHT_ARM : MOUNT_LOC_LEFT_ARM;
    }

    private Optional<MiscMounted> getManipulator(int mountLoc) {
        return getBattleArmor().getMisc().stream()
              .filter(m -> m.getBaMountLoc() == mountLoc)
              .filter(m -> m.getType().hasFlag(MiscType.F_BA_MANIPULATOR))
              .findFirst();
    }

    private Optional<MiscMounted> getModularMount(int mountLoc) {
        return getBattleArmor().getMisc().stream()
              .filter(m -> m.getBaMountLoc() == mountLoc)
              .filter(m -> m.is(BA_MODULAR_EQUIPMENT_ADAPTOR))
              .findFirst();
    }

    private boolean isPairedManipulator(EquipmentType eq) {
        BAManipulator manipulator = BAManipulator.getManipulator(eq.getInternalName());
        return manipulator != null && manipulator.pairMounted;
    }

    private void setManipulatorSize(int mountLoc, double size) {
        getManipulator(mountLoc).ifPresent(manipulator -> manipulator.setSize(size));
    }
}
