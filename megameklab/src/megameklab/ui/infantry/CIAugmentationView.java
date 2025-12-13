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
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        // make top-aligned
        gbc.gridy++;
        gbc.weighty = 1;
        augPanel.add(Box.createVerticalGlue(), gbc);

        setLayout(new GridLayout(1, 1));
        add(new TabScrollPane(augPanel));
        handleEvents = true;
    }

    public void refresh() {
        handleEvents = false;
        for (IOption opt : options.keySet()) {
            options.get(opt).setSelected(getInfantry().getCrew().getOptions().booleanOption(opt.getName()));
        }
        handleEvents = true;
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!handleEvents) {
            return;
        }
        if (e.getSource() instanceof JCheckBox checkBox) {
            String optionName = e.getActionCommand();
            getInfantry().getCrew().getOptions().getOption(optionName).setValue(checkBox.isSelected());

            // Dermal Armor and Dermal Camo Armor are mutually exclusive
            if (checkBox.isSelected()) {
                String mutuallyExclusiveOption = null;
                if (optionName.equals(OptionsConstants.MD_DERMAL_ARMOR)) {
                    mutuallyExclusiveOption = OptionsConstants.MD_DERMAL_CAMO_ARMOR;
                } else if (optionName.equals(OptionsConstants.MD_DERMAL_CAMO_ARMOR)) {
                    mutuallyExclusiveOption = OptionsConstants.MD_DERMAL_ARMOR;
                }

                if (mutuallyExclusiveOption != null) {
                    IOption otherOption = getInfantry().getCrew().getOptions().getOption(mutuallyExclusiveOption);
                    if ((otherOption != null) && otherOption.booleanValue()) {
                        otherOption.setValue(false);
                        // Update the checkbox in the UI without triggering another actionPerformed
                        for (IOption opt : options.keySet()) {
                            if (opt.getName().equals(mutuallyExclusiveOption)) {
                                handleEvents = false;
                                options.get(opt).setSelected(false);
                                handleEvents = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshPreview();
        }
    }
}
