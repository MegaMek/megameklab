/*
 * MegaMekLab - Copyright (C) 2017
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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
package megameklab.com.ui.supportvehicle;

import megamek.common.Entity;
import megamek.common.ITechManager;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.PatchworkArmorView;
import megameklab.com.ui.view.SVArmorView;
import megameklab.com.util.ITab;

import javax.swing.*;

/**
 *
 */
public class SVArmorTab extends ITab {

    private final SVArmorView panArmor;
    private final ArmorAllocationView panArmorAllocation;
    private final PatchworkArmorView panPatchwork;

    SVArmorTab(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        panArmor = new SVArmorView(techManager);
        panPatchwork = new PatchworkArmorView(techManager);
        panArmorAllocation = new ArmorAllocationView(techManager, Entity.ETYPE_TANK);

        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panArmor);
        panel.add(Box.createVerticalStrut(6));
        panel.add(panPatchwork);
        panel.add(Box.createGlue());
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panArmorAllocation);
        panel.add(Box.createGlue());
        add(panel);

        panArmor.setFromEntity(getEntity());
        panPatchwork.setFromEntity(getEntity());
        panArmorAllocation.setFromEntity(getEntity());

        addAllListeners();
    }

    private void addAllListeners() {

    }

    public void refresh() {
        panArmor.setFromEntity(getEntity());
        if (getEntity().hasPatchworkArmor()) {
            panPatchwork.setFromEntity(getEntity());
            panPatchwork.setVisible(true);
        } else {
            panPatchwork.setVisible(false);
        }
        panArmorAllocation.setFromEntity(getEntity());
    }
}
