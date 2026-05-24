/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.equipment.EquipmentType;
import megamek.common.units.Mek;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.TechComboBox;

/**
 * View for FrankenMek structure allocation and type selection.
 */
public class FrankenMekStructureView extends BuildView implements ActionListener, ChangeListener, FocusListener {
    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");
    private static final String DEFAULT_DONOR_SOURCE_TYPE = "BattleMek";
    private static final String[] DONOR_SOURCE_TYPES = {
          DEFAULT_DONOR_SOURCE_TYPE, "OmniMek", "IndustrialMek"
    };

    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();
    private final List<StructureLocationView> locationViews = new ArrayList<>();
    private final Supplier<List<EquipmentType>> availableStructures;
    private final Supplier<int[][]> currentLayout;
    private final IntSupplier minimumChassisTonnage;
    private final IntSupplier maximumChassisTonnage;
    private final JPanel panLocations = new JPanel();
    private final JCheckBox chkMismatchedLegs = new JCheckBox(
          I18N.getString("FrankenMekStructureView.chkMismatchedLegs.text"));

    private int[][] layout;
    private boolean ignoreEvents = false;

    public FrankenMekStructureView(Supplier<List<EquipmentType>> availableStructures, Supplier<int[][]> currentLayout,
          IntSupplier minimumChassisTonnage, IntSupplier maximumChassisTonnage) {
        this.availableStructures = availableStructures;
        this.currentLayout = currentLayout;
        this.minimumChassisTonnage = minimumChassisTonnage;
        this.maximumChassisTonnage = maximumChassisTonnage;
        initUI();
    }

    public void addListener(ArmorAllocationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ArmorAllocationListener listener) {
        listeners.remove(listener);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = STANDARD_INSETS;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panLocations.setLayout(new GridBagLayout());
        add(panLocations, gbc);
        updateLayout();

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        chkMismatchedLegs.setToolTipText(I18N.getString("FrankenMekStructureView.chkMismatchedLegs.tooltip"));
        chkMismatchedLegs.setEnabled(false);
        add(chkMismatchedLegs, gbc);
    }

    private void updateLayout() {
        int[][] newLayout = currentLayout.get();
        if (layout == newLayout) {
            return;
        }
        layout = newLayout;
        panLocations.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        locationViews.clear();
        for (int[] row : layout) {
            JPanel panRow = new JPanel();
            panRow.setLayout(new BoxLayout(panRow, BoxLayout.X_AXIS));
            for (int loc : row) {
                if (loc >= 0) {
                    StructureLocationView locView = new StructureLocationView(loc);
                    locationViews.add(locView);
                    panRow.add(locView);
                    locView.spinner.addChangeListener(this);
                    locView.combo.addActionListener(this);
                    locView.txtDonorSource.addActionListener(this);
                    locView.txtDonorSource.addFocusListener(this);
                    locView.cbDonorSourceType.addActionListener(this);
                } else {
                    panRow.add(Box.createHorizontalGlue());
                }
            }
            panLocations.add(panRow, gbc);
            gbc.gridy++;
        }
        panLocations.revalidate();
        panLocations.repaint();
    }

    public void setFromEntity(Mek mek) {
        updateLayout();
        setVisible(mek.isFrankenMek());
        if (!mek.isFrankenMek()) {
            return;
        }

        ignoreEvents = true;
        List<EquipmentType> structures = availableStructures.get();
        for (StructureLocationView locView : locationViews) {
            int location = locView.location;
            boolean validLocation = location < mek.locations();
            locView.setVisible(validLocation);
            if (!validLocation) {
                continue;
            }

            int currentTonnage = mek.getFrankenMekStructureTonnage(location);
            int centerTorsoTonnage = mek.getFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO);
            SpinnerNumberModel model = locView.model;
            model.setMinimum(getMinimumTonnage(mek, location, currentTonnage, centerTorsoTonnage));
            model.setMaximum(getMaximumTonnage(mek, location, currentTonnage));
            model.setStepSize(5);
            locView.spinner.setEnabled(true);
            locView.updateLocation(mek.getLocationAbbr(location));
            locView.model.setValue(currentTonnage);
            locView.updatePips(mek.getFrankenMekInternalForLocation(location));

            locView.combo.removeAllItems();
            locView.combo.showTechBase(mek.isMixedTech() || needsStructureTechBase(structures));
            for (EquipmentType structure : structures) {
                locView.combo.addItem(structure);
            }
            EquipmentType selected = mek.getFrankenMekStructureEquipment(location);
            if ((selected != null) && !structures.contains(selected)) {
                locView.combo.addItem(selected);
            }
            locView.combo.setSelectedItem(selected);
            locView.txtDonorSource.setText(mek.getFrankenMekLocationSourceDisplayName(location));
            locView.cbDonorSourceType.setSelectedItem(getSelectableDonorSourceType(
                mek.getFrankenMekLocationSourceType(location)));
        }
        chkMismatchedLegs.setSelected(mek.hasMismatchedFrankenMekLegs());
        ignoreEvents = false;
    }

    private int getMinimumTonnage(Mek mek, int location, int currentTonnage, int centerTorsoTonnage) {
        int minimumTonnage = 10;
        if (location == Mek.LOC_CENTER_TORSO) {
            minimumTonnage = minimumChassisTonnage.getAsInt();
        } else if (mek.locationIsLeg(location)) {
            minimumTonnage = centerTorsoTonnage;
        }
        return Math.min(currentTonnage, minimumTonnage);
    }

    private int getMaximumTonnage(Mek mek, int location, int currentTonnage) {
        int maximumTonnage = location == Mek.LOC_CENTER_TORSO
              ? maximumChassisTonnage.getAsInt()
              : mek.getMaximumFrankenMekStructureTonnageForConstruction(location);
        return Math.max(currentTonnage, maximumTonnage);
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

    private String getSelectableDonorSourceType(String donorSourceType) {
        for (String sourceType : DONOR_SOURCE_TYPES) {
            if (sourceType.equals(donorSourceType)) {
                return sourceType;
            }
        }
        return DEFAULT_DONOR_SOURCE_TYPE;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (ignoreEvents) {
            return;
        }
        for (StructureLocationView locView : locationViews) {
            if (event.getSource() == locView.combo) {
                EquipmentType structure = (EquipmentType) locView.combo.getSelectedItem();
                listeners.forEach(listener -> listener.frankenMekStructureTypeChanged(locView.location, structure));
                return;
            }
            if ((event.getSource() == locView.txtDonorSource)
                  || (event.getSource() == locView.cbDonorSourceType)) {
                notifyFrankenMekLocationSourceChanged(locView);
                return;
            }
        }
    }

    private void notifyFrankenMekLocationSourceChanged(StructureLocationView locView) {
        String donorSourceType = (String) locView.cbDonorSourceType.getSelectedItem();
        listeners.forEach(listener -> listener.frankenMekLocationSourceChanged(locView.location,
              locView.txtDonorSource.getText(), donorSourceType));
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        if (ignoreEvents) {
            return;
        }
        for (StructureLocationView locView : locationViews) {
            if (event.getSource() == locView.spinner) {
                listeners.forEach(listener -> listener.frankenMekStructureTonnageChanged(
                      locView.location, locView.model.getNumber().intValue()));
                return;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent event) {
    }

    @Override
    public void focusLost(FocusEvent event) {
        if (ignoreEvents) {
            return;
        }
        for (StructureLocationView locView : locationViews) {
            if (event.getSource() == locView.txtDonorSource) {
                notifyFrankenMekLocationSourceChanged(locView);
                return;
            }
        }
    }

    private static class StructureLocationView extends JPanel {
        private static final Insets LOCATION_ROW_INSETS = new Insets(1, 4, 2, 4);

        private final int location;
        private final SpinnerNumberModel model = new SpinnerNumberModel(20, 10, 200, 5);
        private final JSpinner spinner = new JSpinner(model);
        private final JLabel lblTonnage = new JLabel(I18N.getString(
              "MekChassisView.spnTonnage.text") + " ", SwingConstants.RIGHT);
        private final TechComboBox<EquipmentType> combo = new TechComboBox<>(EquipmentType::getName);
        private final JLabel lblDonorSource = new JLabel(I18N.getString(
              "FrankenMekStructureView.lblDonorSource.text") + " ", SwingConstants.RIGHT);
        private final JTextField txtDonorSource = new JTextField(12);
        private final JLabel lblDonorSourceType = new JLabel(I18N.getString(
              "FrankenMekStructureView.lblDonorSourceType.text") + " ", SwingConstants.RIGHT);
        private final JComboBox<String> cbDonorSourceType = new JComboBox<>(DONOR_SOURCE_TYPES);
        private final JLabel lblPips = new JLabel();
        private final TitledBorder border = BorderFactory.createTitledBorder(null, "",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION);

        private StructureLocationView(int location) {
            this.location = location;
            setBorder(border);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = LOCATION_ROW_INSETS;
            gbc.gridy = 0;
            addLabeledControl(gbc, lblTonnage, spinner, false);

            combo.setPrototypeDisplayValue(EquipmentType.get(EquipmentType.getStructureTypeName(
                  EquipmentType.T_STRUCTURE_ENDO_COMPOSITE, true)));
            addUnlabeledControl(gbc, combo);

            txtDonorSource.setToolTipText(I18N.getString("FrankenMekStructureView.txtDonorSource.tooltip"));
            addLabeledControl(gbc, lblDonorSource, txtDonorSource, true);

            cbDonorSourceType.setPrototypeDisplayValue("IndustrialMek");
            cbDonorSourceType.setToolTipText(I18N.getString(
                "FrankenMekStructureView.cbDonorSourceType.tooltip"));
            addLabeledControl(gbc, lblDonorSourceType, cbDonorSourceType, true);

            lblPips.setFont(lblPips.getFont().deriveFont(Font.BOLD));
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 0.0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.CENTER;
            add(lblPips, gbc);
        }

        private void addLabeledControl(GridBagConstraints gbc, JLabel label, JComponent control, boolean fillControl) {
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = fillControl ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            add(control, gbc);
            gbc.gridy++;
        }

        private void addUnlabeledControl(GridBagConstraints gbc, JComponent control) {
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            add(control, gbc);
            gbc.gridy++;
        }

        private void updateLocation(String locName) {
            border.setTitle(locName);
        }

        private void updatePips(int pips) {
            lblPips.setText(Integer.toString(pips) + ((pips == 1) ? " point" : " points"));
        }
    }
}
