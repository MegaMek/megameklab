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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.battlefieldSupport;

import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import megamek.client.ui.WrapLayout;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megameklab.ui.BugReportHelper;
import megameklab.ui.ForceBuildUI;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;

/**
 * The minimal status bar for the standalone Battlefield Support Asset editor. Assets have no construction rules or
 * validation, so this bar only offers Add to Force and Refresh actions and shows the (non-veteran) cost in both BV and
 * BSP.
 */
public class BFSStatusBar extends ITab {

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");
    private final JLabel costLabel = new JLabel();
    private final JButton refreshButton = new JButton(I18N.getString("BFSStatusBar.refresh.text"));
    private RefreshListener refresh;

    public BFSStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        setBorder(new MatteBorder(1, 0, 0, 0, UIManager.getColor("Separator.foreground")));
        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 8));

        JButton btnAddToForce = new JButton(I18N.getString("BFSStatusBar.addToForce.text"));
        btnAddToForce.setToolTipText(I18N.getString("BFSStatusBar.addToForce.tooltip"));
        btnAddToForce.addActionListener(evt -> ForceBuildUI.showAndAddEntity(getEntity()));

        refreshButton.setToolTipText(I18N.getString("BFSStatusBar.refresh.tooltip"));
        refreshButton.setEnabled(false);
        refreshButton.addActionListener(evt -> refresh.refreshAll());

        add(btnAddToForce);
        add(refreshButton);
        add(costLabel);
        add(BugReportHelper.createButton(parent));
    }

    public void refresh() {
        if (getEntity() instanceof BattlefieldSupportAsset asset) {
            NumberFormat format = NumberFormat.getInstance();
            costLabel.setText(I18N.getString("BFSStatusBar.cost.text")
                  .formatted(format.format(asset.getBv()), asset.getBsp()));
            costLabel.setToolTipText(I18N.getString("BFSStatusBar.cost.tooltip"));
        }
    }

    public void addRefreshedListener(RefreshListener refreshListener) {
        refresh = Objects.requireNonNull(refreshListener);
        refreshButton.setEnabled(true);
    }
}
