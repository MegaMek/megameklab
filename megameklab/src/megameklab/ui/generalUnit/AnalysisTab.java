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

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.Timer;

import megamek.client.ui.dialogs.unitSelectorDialogs.DamageAnalysisPanel;
import megamek.client.ui.util.UIUtil;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;

/**
 * A top-level editor tab showing the unit's damage analysis: the damage-vs-range curves and the
 * direction/reach radars from megamek's {@link DamageAnalysisPanel}. Refreshes itself with the
 * same debounce pattern as {@link PreviewTab}: updates are deferred while the tab is hidden and
 * applied when it is shown, so the charts follow the unit as it is built.
 */
public class AnalysisTab extends ITab {
    private static final int REFRESH_DEBOUNCE_DELAY_MS = 100;

    private final DamageAnalysisPanel analysisPanel = new DamageAnalysisPanel();
    private final Timer refreshTimer = new Timer(REFRESH_DEBOUNCE_DELAY_MS, event -> performUpdate());
    private boolean refreshPending;

    public AnalysisTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        refreshTimer.setRepeats(false);
        // The panel's own minimum is sized for the unit selector; the editor pane can be narrower,
        // and the charts degrade gracefully at small sizes.
        analysisPanel.setMinimumSize(UIUtil.scaleForGUI(400, 500));
        add(analysisPanel, BorderLayout.CENTER);
        addComponentListener(refreshOnShow);
    }

    public void update() {
        analysisPanel.setEntity(eSource.getEntity());
    }

    private void performUpdate() {
        if (!isVisible()) {
            refreshPending = true;
            return;
        }
        refreshPending = false;
        update();
    }

    private void scheduleUpdate() {
        refreshPending = true;
        if (isVisible()) {
            refreshTimer.restart();
        }
    }

    public void refresh() {
        scheduleUpdate();
    }

    @Override
    public void removeNotify() {
        refreshTimer.stop();
        super.removeNotify();
    }

    private final ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent event) {
            if (refreshPending) {
                scheduleUpdate();
            } else {
                refreshTimer.stop();
                update();
            }
        }
    };
}
