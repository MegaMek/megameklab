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
package megameklab.ui.infantry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import megamek.common.options.IOption;
import megamek.common.options.PilotOptions;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

/**
 * Allows infantry to include cybernetic/prosthetic augmentation (e.g., Manei Domini).
 *
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 10);

        augPanel.add(new JLabel("Implant"), gbc);
        gbc.gridx = 1;
        augPanel.add(new JLabel("Description"), gbc);

        for (Enumeration<IOption> e = getInfantry().getCrew().getOptions(PilotOptions.MD_ADVANTAGES);
              e.hasMoreElements(); ) {
            final IOption opt = e.nextElement();
            gbc.gridx = 0;
            gbc.gridy++;
            JCheckBox chk = new JCheckBox(opt.getDisplayableName());
            chk.setActionCommand(opt.getName());
            augPanel.add(chk, gbc);
            chk.addActionListener(this);
            options.put(opt, chk);

            gbc.gridx++;
            augPanel.add(new JLabel(opt.getDescription()), gbc);
        }

        add(new JScrollPane(augPanel));
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
        if (e.getSource() instanceof JCheckBox) {
            getInfantry().getCrew()
                  .getOptions()
                  .getOption(e.getActionCommand())
                  .setValue(((JCheckBox) e.getSource()).isSelected());
        }
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshPreview();
        }
    }


}
