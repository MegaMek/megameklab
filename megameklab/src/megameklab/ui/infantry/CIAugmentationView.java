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
package megameklab.ui.infantry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import megamek.common.enums.MDAugmentationType;
import megamek.common.enums.ProstheticEnhancementType;
import megamek.common.options.IOption;
import megamek.common.options.OptionsConstants;
import megamek.common.options.PilotOptions;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.TabScrollPane;

/**
 * Allows infantry to include cybernetic/prosthetic augmentation (e.g. Manei Domini).
 *
 * @author (original) jtighe (torren@users.sourceforge.net)
 * @author Neoancient
 */
public class CIAugmentationView extends IView implements ActionListener {
    private RefreshListener refresh;

    private final HashMap<IOption, JCheckBox> options = new HashMap<>();

    // Prosthetic Enhancement controls (IO p.84)
    private JPanel prostheticPanel;
    private JComboBox<ProstheticEnhancementType> cbEnhancement1;
    private JComboBox<Integer> cbCount1;
    private JLabel lblEnhancement2;
    private JComboBox<ProstheticEnhancementType> cbEnhancement2;
    private JLabel lblCount2;
    private JComboBox<Integer> cbCount2;

    // Extraneous Limbs controls (IO p.84)
    private JPanel extraneousPanel;
    private JLabel lblExtraneousPair1;
    private JComboBox<ProstheticEnhancementType> cbExtraneousPair1;
    private JLabel lblExtraneousPair2;
    private JComboBox<ProstheticEnhancementType> cbExtraneousPair2;

    private boolean handleEvents;

    public CIAugmentationView(EntitySource eSource) {
        super(eSource);

        JPanel augPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        augPanel.add(new JLabel("Implant"), gbc);
        augPanel.add(new JLabel("Description"), gbc);
        gbc.weightx = 1;
        augPanel.add(Box.createHorizontalGlue(), gbc);
        gbc.weightx = 0;

        gbc.insets = new Insets(0, 10, 0, 10);
        for (Enumeration<IOption> e = getInfantry().getCrew().getOptions(PilotOptions.MD_ADVANTAGES);
              e.hasMoreElements(); ) {
            final IOption opt = e.nextElement();
            JCheckBox checkBox = new JCheckBox(opt.getDisplayableName());
            checkBox.setActionCommand(opt.getName());
            checkBox.addActionListener(this);
            options.put(opt, checkBox);

            gbc.gridy++;
            augPanel.add(checkBox, gbc);
            augPanel.add(new JLabel(opt.getDescription()), gbc);
        }

        // Prosthetic Enhancement panel (IO p.84)
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        prostheticPanel = createProstheticPanel();
        augPanel.add(prostheticPanel, gbc);

        // Extraneous Limbs panel (IO p.84)
        gbc.gridy++;
        extraneousPanel = createExtraneousPanel();
        augPanel.add(extraneousPanel, gbc);

        // make top-aligned
        gbc.gridy++;
        gbc.weighty = 1;
        augPanel.add(Box.createVerticalGlue(), gbc);

        setLayout(new GridLayout(1, 1));
        add(new TabScrollPane(augPanel));
        handleEvents = true;
    }

    /**
     * Creates the panel for configuring prosthetic enhancement types and counts. Visible only when Enhanced or Improved
     * Enhanced prosthetic limbs are selected.
     */
    private JPanel createProstheticPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Prosthetic Enhancement Configuration (IO p.84)"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Slot 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Enhancement 1:"), gbc);

        gbc.gridx = 1;
        cbEnhancement1 = new JComboBox<>(ProstheticEnhancementType.values());
        cbEnhancement1.insertItemAt(null, 0);
        cbEnhancement1.setSelectedIndex(0);
        cbEnhancement1.addActionListener(this);
        panel.add(cbEnhancement1, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Count:"), gbc);

        gbc.gridx = 3;
        cbCount1 = new JComboBox<>(new Integer[] { 0, 1, 2 });
        cbCount1.addActionListener(this);
        panel.add(cbCount1, gbc);

        // Slot 2 (only for Improved Enhanced)
        gbc.gridx = 0;
        gbc.gridy = 1;
        lblEnhancement2 = new JLabel("Enhancement 2:");
        panel.add(lblEnhancement2, gbc);

        gbc.gridx = 1;
        cbEnhancement2 = new JComboBox<>(ProstheticEnhancementType.values());
        cbEnhancement2.insertItemAt(null, 0);
        cbEnhancement2.setSelectedIndex(0);
        cbEnhancement2.addActionListener(this);
        panel.add(cbEnhancement2, gbc);

        gbc.gridx = 2;
        lblCount2 = new JLabel("Count:");
        panel.add(lblCount2, gbc);

        gbc.gridx = 3;
        cbCount2 = new JComboBox<>(new Integer[] { 0, 1, 2 });
        cbCount2.addActionListener(this);
        panel.add(cbCount2, gbc);

        return panel;
    }

    /**
     * Creates the panel for configuring extraneous limb enhancement types. Visible only when Extraneous (Enhanced)
     * Limbs option is selected. Each pair always provides 2 items (no count selection needed).
     */
    private JPanel createExtraneousPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Extraneous Limbs Configuration (IO p.84)"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Pair 1 (always visible when panel is shown)
        gbc.gridx = 0;
        gbc.gridy = 0;
        lblExtraneousPair1 = new JLabel("Pair 1 (x2 items):");
        panel.add(lblExtraneousPair1, gbc);

        gbc.gridx = 1;
        cbExtraneousPair1 = new JComboBox<>(ProstheticEnhancementType.values());
        cbExtraneousPair1.insertItemAt(null, 0);
        cbExtraneousPair1.setSelectedIndex(0);
        cbExtraneousPair1.addActionListener(this);
        panel.add(cbExtraneousPair1, gbc);

        // Pair 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        lblExtraneousPair2 = new JLabel("Pair 2 (x2 items):");
        panel.add(lblExtraneousPair2, gbc);

        gbc.gridx = 1;
        cbExtraneousPair2 = new JComboBox<>(ProstheticEnhancementType.values());
        cbExtraneousPair2.insertItemAt(null, 0);
        cbExtraneousPair2.setSelectedIndex(0);
        cbExtraneousPair2.addActionListener(this);
        panel.add(cbExtraneousPair2, gbc);

        // Add note about conventional infantry only
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(new JLabel("<html><i>Conventional infantry only. Each pair provides 2 identical items.</i></html>"),
              gbc);

        return panel;
    }

    public void refresh() {
        handleEvents = false;

        int year = getInfantry().getYear();
        boolean isClan = getInfantry().isClan();
        boolean canUseWings = isFootInfantry();

        for (IOption opt : options.keySet()) {
            JCheckBox checkBox = options.get(opt);
            boolean isSelected = getInfantry().getCrew().getOptions().booleanOption(opt.getName());
            checkBox.setSelected(isSelected);

            // Enable/disable based on tech availability for the unit's year
            MDAugmentationType augType = MDAugmentationType.getByOptionName(opt.getName());
            if (augType != null) {
                boolean isAvailable = augType.isAvailableIn(year, isClan);

                // Apply wing-specific restrictions (IO p.85)
                // Note: Mutual exclusion between wing types is handled in actionPerformed
                // to allow single-click switching between glider and powered flight
                String optName = opt.getName();
                if (optName.equals(OptionsConstants.MD_PL_GLIDER)
                      || optName.equals(OptionsConstants.MD_PL_FLIGHT)) {
                    // Wings: only foot infantry can use them
                    isAvailable = isAvailable && canUseWings;
                }

                checkBox.setEnabled(isAvailable);
                // If not available and was selected, deselect it
                if (!isAvailable && isSelected) {
                    checkBox.setSelected(false);
                    getInfantry().getCrew().getOptions().getOption(opt.getName()).setValue(false);
                }
            }
        }

        // Update prosthetic enhancement controls
        refreshProstheticPanel();
        refreshExtraneousPanel();
        handleEvents = true;
    }

    /**
     * Returns true if the infantry is foot infantry (not motorized, mechanized, or beast-mounted). Per IO p.85, glider
     * and powered flight wings can only be used by foot infantry.
     */
    private boolean isFootInfantry() {
        return !getInfantry().isMechanized()
              && !getInfantry().getMovementMode().isMotorizedInfantry()
              && (getInfantry().getMount() == null);
    }

    /**
     * Updates the prosthetic enhancement panel visibility and values.
     */
    private void refreshProstheticPanel() {
        boolean hasEnhanced = getInfantry().hasAbility(OptionsConstants.MD_PL_ENHANCED);
        boolean hasImprovedEnhanced = getInfantry().hasAbility(OptionsConstants.MD_PL_I_ENHANCED);

        // Panel visible only when Enhanced or Improved Enhanced is selected
        prostheticPanel.setVisible(hasEnhanced || hasImprovedEnhanced);

        if (hasEnhanced || hasImprovedEnhanced) {
            // Slot 1 is always available
            ProstheticEnhancementType type1 = getInfantry().getProstheticEnhancement1();
            cbEnhancement1.setSelectedItem(type1);
            cbCount1.setSelectedItem(getInfantry().getProstheticEnhancement1Count());

            // Slot 2 only available for Improved Enhanced
            boolean slot2Visible = hasImprovedEnhanced;
            lblEnhancement2.setVisible(slot2Visible);
            cbEnhancement2.setVisible(slot2Visible);
            lblCount2.setVisible(slot2Visible);
            cbCount2.setVisible(slot2Visible);

            if (slot2Visible) {
                ProstheticEnhancementType type2 = getInfantry().getProstheticEnhancement2();
                cbEnhancement2.setSelectedItem(type2);
                cbCount2.setSelectedItem(getInfantry().getProstheticEnhancement2Count());
            }
            // Don't clear slot 2 data - preserve for re-selection
        }
    }

    /**
     * Updates the extraneous limbs panel visibility and values.
     * Per IO p.85, if wings are installed, only one pair of extraneous limbs is allowed.
     */
    private void refreshExtraneousPanel() {
        boolean hasExtraneous = getInfantry().hasAbility(OptionsConstants.MD_PL_EXTRA_LIMBS);

        // Panel visible only when Extraneous Limbs option is selected
        extraneousPanel.setVisible(hasExtraneous);

        if (hasExtraneous) {
            // Pair 1 is always available
            ProstheticEnhancementType pair1Type = getInfantry().getExtraneousPair1();
            cbExtraneousPair1.setSelectedItem(pair1Type);

            // Check if wings are installed - limits to 1 pair only (IO p.85)
            boolean hasWings = getInfantry().hasAbility(OptionsConstants.MD_PL_GLIDER)
                  || getInfantry().hasAbility(OptionsConstants.MD_PL_FLIGHT);
            boolean pair2Allowed = !hasWings;

            lblExtraneousPair2.setVisible(pair2Allowed);
            cbExtraneousPair2.setVisible(pair2Allowed);

            if (pair2Allowed) {
                ProstheticEnhancementType pair2Type = getInfantry().getExtraneousPair2();
                cbExtraneousPair2.setSelectedItem(pair2Type);
            } else {
                // Clear pair 2 data when wings restrict us to 1 pair
                getInfantry().setExtraneousPair2(null);
                cbExtraneousPair2.setSelectedItem(null);
            }
        }
        // Don't clear data when option is deselected - preserve for re-selection
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    /**
     * Handles mutually exclusive options by unchecking one when the other is selected.
     *
     * @param selectedOptionName  The option that was just selected
     * @param exclusiveOptionName The option that should be deselected
     */
    private void handleMutuallyExclusiveOption(String selectedOptionName, String exclusiveOptionName) {
        if (exclusiveOptionName == null) {
            return;
        }
        IOption otherOption = getInfantry().getCrew().getOptions().getOption(exclusiveOptionName);
        if ((otherOption != null) && otherOption.booleanValue()) {
            otherOption.setValue(false);
            // Update the checkbox in the UI without triggering another actionPerformed
            for (IOption opt : options.keySet()) {
                if (opt.getName().equals(exclusiveOptionName)) {
                    handleEvents = false;
                    options.get(opt).setSelected(false);
                    handleEvents = true;
                    break;
                }
            }
        }
    }

    /**
     * Clears all prosthetic enhancement data and resets UI controls.
     */
    private void clearAllProstheticData() {
        getInfantry().setProstheticEnhancement1(null);
        getInfantry().setProstheticEnhancement1Count(0);
        getInfantry().setProstheticEnhancement2(null);
        getInfantry().setProstheticEnhancement2Count(0);

        handleEvents = false;
        cbEnhancement1.setSelectedItem(null);
        cbCount1.setSelectedItem(0);
        cbEnhancement2.setSelectedItem(null);
        cbCount2.setSelectedItem(0);
        handleEvents = true;
    }

    /**
     * Clears all extraneous limb data and resets UI controls.
     */
    private void clearAllExtraneousData() {
        getInfantry().setExtraneousPair1(null);
        getInfantry().setExtraneousPair2(null);

        handleEvents = false;
        cbExtraneousPair1.setSelectedItem(null);
        cbExtraneousPair2.setSelectedItem(null);
        handleEvents = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!handleEvents) {
            return;
        }
        if (e.getSource() instanceof JCheckBox checkBox) {
            String optionName = e.getActionCommand();
            getInfantry().getCrew().getOptions().getOption(optionName).setValue(checkBox.isSelected());

            // Handle mutually exclusive options when selecting
            if (checkBox.isSelected()) {
                // Dermal Armor and Dermal Camo Armor are mutually exclusive
                if (optionName.equals(OptionsConstants.MD_DERMAL_ARMOR)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_DERMAL_CAMO_ARMOR);
                } else if (optionName.equals(OptionsConstants.MD_DERMAL_CAMO_ARMOR)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_DERMAL_ARMOR);
                }

                // Enhanced and Improved Enhanced prosthetics are mutually exclusive
                if (optionName.equals(OptionsConstants.MD_PL_ENHANCED)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_PL_I_ENHANCED);
                } else if (optionName.equals(OptionsConstants.MD_PL_I_ENHANCED)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_PL_ENHANCED);
                }

                // Glider and Powered Flight wings are mutually exclusive (IO p.85)
                if (optionName.equals(OptionsConstants.MD_PL_GLIDER)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_PL_FLIGHT);
                } else if (optionName.equals(OptionsConstants.MD_PL_FLIGHT)) {
                    handleMutuallyExclusiveOption(optionName, OptionsConstants.MD_PL_GLIDER);
                }
            }

            // Handle prosthetic panel visibility (don't clear data - preserve for re-selection)
            if (optionName.equals(OptionsConstants.MD_PL_ENHANCED)
                  || optionName.equals(OptionsConstants.MD_PL_I_ENHANCED)) {
                handleEvents = false;
                refreshProstheticPanel();
                handleEvents = true;
            }

            // Handle extraneous panel visibility (don't clear data - preserve for re-selection)
            if (optionName.equals(OptionsConstants.MD_PL_EXTRA_LIMBS)) {
                handleEvents = false;
                refreshExtraneousPanel();
                handleEvents = true;
            }

            // Handle wing changes - affects extraneous limb pair limits (IO p.85)
            if (optionName.equals(OptionsConstants.MD_PL_GLIDER)
                  || optionName.equals(OptionsConstants.MD_PL_FLIGHT)) {
                handleEvents = false;
                refreshExtraneousPanel();
                handleEvents = true;
            }

        } else if (e.getSource() == cbEnhancement1) {
            // Handle enhancement type 1 change
            ProstheticEnhancementType selectedType = (ProstheticEnhancementType) cbEnhancement1.getSelectedItem();
            getInfantry().setProstheticEnhancement1(selectedType);
            if (selectedType == null) {
                // Reset count to 0 when clearing the enhancement type
                getInfantry().setProstheticEnhancement1Count(0);
                handleEvents = false;
                cbCount1.setSelectedItem(0);
                handleEvents = true;
            } else if (getInfantry().getProstheticEnhancement1Count() == 0) {
                // Set default count of 1 if selecting a type and count is 0
                getInfantry().setProstheticEnhancement1Count(1);
                handleEvents = false;
                cbCount1.setSelectedItem(1);
                handleEvents = true;
            }

        } else if (e.getSource() == cbCount1) {
            // Handle count 1 change
            Integer selectedCount = (Integer) cbCount1.getSelectedItem();
            getInfantry().setProstheticEnhancement1Count(selectedCount != null ? selectedCount : 0);

        } else if (e.getSource() == cbEnhancement2) {
            // Handle enhancement type 2 change
            ProstheticEnhancementType selectedType = (ProstheticEnhancementType) cbEnhancement2.getSelectedItem();
            getInfantry().setProstheticEnhancement2(selectedType);
            if (selectedType == null) {
                // Reset count to 0 when clearing the enhancement type
                getInfantry().setProstheticEnhancement2Count(0);
                handleEvents = false;
                cbCount2.setSelectedItem(0);
                handleEvents = true;
            } else if (getInfantry().getProstheticEnhancement2Count() == 0) {
                // Set default count of 1 if selecting a type and count is 0
                getInfantry().setProstheticEnhancement2Count(1);
                handleEvents = false;
                cbCount2.setSelectedItem(1);
                handleEvents = true;
            }

        } else if (e.getSource() == cbCount2) {
            // Handle count 2 change
            Integer selectedCount = (Integer) cbCount2.getSelectedItem();
            getInfantry().setProstheticEnhancement2Count(selectedCount != null ? selectedCount : 0);

        } else if (e.getSource() == cbExtraneousPair1) {
            // Handle extraneous pair 1 type change
            ProstheticEnhancementType selectedType = (ProstheticEnhancementType) cbExtraneousPair1.getSelectedItem();
            getInfantry().setExtraneousPair1(selectedType);

        } else if (e.getSource() == cbExtraneousPair2) {
            // Handle extraneous pair 2 type change
            ProstheticEnhancementType selectedType = (ProstheticEnhancementType) cbExtraneousPair2.getSelectedItem();
            getInfantry().setExtraneousPair2(selectedType);
        }

        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
    }
}
