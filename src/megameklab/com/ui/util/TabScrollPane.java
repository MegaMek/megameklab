/*
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.com.ui.util;

import javax.swing.*;
import java.awt.*;

/**
 * This JScrollPane is used for the tabs (Structure, Fluff etc) of the various unit UIs. It
 * is a standard JScrollPane that sets the Scrollbars to "as needed", removes the border and
 * sets the mouse wheel scroll speed to a sensible value.
 *
 * @author Simon (Juliez)
 */
public class TabScrollPane extends JScrollPane {

    public TabScrollPane(Component view) {
        super(view);
        initialize();
    }

    public TabScrollPane() {
        super();
        initialize();
    }

    private void initialize() {
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getVerticalScrollBar().setUnitIncrement(16);
        getHorizontalScrollBar().setUnitIncrement(16);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
