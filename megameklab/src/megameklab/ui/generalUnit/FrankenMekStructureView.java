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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
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
public class FrankenMekStructureView extends BuildView implements ActionListener, ChangeListener {
    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

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
        chkMismatchedLegs.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent event) {
        if (ignoreEvents) {
            return;
        }
        if (event.getSource() == chkMismatchedLegs) {
            listeners.forEach(listener -> listener.frankenMekMismatchedLegsChanged(chkMismatchedLegs.isSelected()));
            return;
        }
        for (StructureLocationView locView : locationViews) {
            if (event.getSource() == locView.combo) {
                EquipmentType structure = (EquipmentType) locView.combo.getSelectedItem();
                listeners.forEach(listener -> listener.frankenMekStructureTypeChanged(locView.location, structure));
                return;
            }
        }
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

    private static class StructureLocationView extends JPanel {
        private final int location;
        private final SpinnerNumberModel model = new SpinnerNumberModel(20, 10, 200, 5);
        private final JSpinner spinner = new JSpinner(model);
          private final JLabel lblTonnage = new JLabel(I18N.getString(
              "MekChassisView.spnTonnage.text") + " ");
        private final TechComboBox<EquipmentType> combo = new TechComboBox<>(EquipmentType::getName);
        private final JLabel lblPips = new JLabel();
        private final TitledBorder border = BorderFactory.createTitledBorder(null, "",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION);

        private StructureLocationView(int location) {
            this.location = location;
            setBorder(border);
            setLayout(new GridBagLayout());

            JPanel panTonnage = new JPanel(new FlowLayout(FlowLayout.CENTER, STANDARD_INSETS.left, 0));
            panTonnage.setOpaque(false);
            panTonnage.add(lblTonnage);
            panTonnage.add(spinner);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = STANDARD_INSETS;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(panTonnage, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.CENTER;
            combo.setPrototypeDisplayValue(EquipmentType.get(EquipmentType.getStructureTypeName(
                  EquipmentType.T_STRUCTURE_ENDO_COMPOSITE, true)));
            add(combo, gbc);

            gbc.gridy++;
            lblPips.setFont(lblPips.getFont().deriveFont(Font.BOLD));
            add(lblPips, gbc);
        }

        private void updateLocation(String locName) {
            border.setTitle(locName);
        }

        private void updatePips(int pips) {
            lblPips.setText(Integer.toString(pips) + ((pips == 1) ? " point" : " points"));
        }
    }
}
