/*
 * Copyright (C) 2021-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * This JScrollPane is used for the tabs (Structure, Fluff etc.) of the various unit UIs. It is a standard JScrollPane
 * that sets the Scrollbars to "as needed", removes the border and sets the mouse wheel scroll speed to a sensible
 * value.
 *
 * @author Simon (Juliez)
 */
public class TabScrollPane extends JScrollPane {

    /**
     * Creates a standardized borderless scroll pane with suitable scroll increment for one of the main tabs in MML
     * (BMBuildTab etc.).
     *
     * @param view The tab component (BMBuildTab etc.) to show in the scroll pane
     */
    public TabScrollPane(Component view) {
        super(view);
        initialize();
    }

    /**
     * Creates a standardized borderless scroll pane with suitable scroll increment for one of the main tabs in MML
     * (BMBuildTab etc.).
     */
    public TabScrollPane() {
        super();
        initialize();
    }

    /**
     * Creates a tab scroll pane with the given ComponentListener attached to it. The ComponentListener can be used,
     * e.g., to update the tab when it is activated, {@link ComponentListener#componentShown(ComponentEvent)}. (When a
     * tab is clicked, the "Shown" event is fired for the component directly attached as the tab, but not any
     * subcomponents.)
     *
     * @param view              The tab component (BMBuildTab etc.) to show in the scroll pane
     * @param componentListener The ComponentListener to attach to the scroll pane
     */
    public TabScrollPane(Component view, ComponentListener componentListener) {
        super(view);
        initialize();
        addComponentListener(componentListener);
    }

    private void initialize() {
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getVerticalScrollBar().setUnitIncrement(16);
        getHorizontalScrollBar().setUnitIncrement(16);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
