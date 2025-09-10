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
package megameklab.ui.util;

import java.awt.Component;
import java.util.Objects;
import java.util.function.Function;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Version of JComboBox that simplifies rendering custom data types by taking a toString method in its contractors.
 * <p>
 * This class could use a more descriptive name.
 *
 * @author Neoancient
 */
public class CustomComboBox<T> extends JComboBox<T> {
    private String nullValue = "-error-";

    protected CustomComboBox() {
        super();
        setRenderer(new Renderer<>(Object::toString));
    }

    @SafeVarargs
    protected CustomComboBox(T... values) {
        super(values);
        setRenderer(new Renderer<>(Object::toString));
    }

    public CustomComboBox(Function<T, String> renderer) {
        super();
        setRenderer(new Renderer<>(renderer));
    }

    public CustomComboBox(T[] values, Function<T, String> renderer) {
        super(values);
        setRenderer(new Renderer<>(renderer));
    }

    public void setNullValue(String val) {
        nullValue = Objects.requireNonNullElse(val, "null");
    }

    class Renderer<U> extends JLabel implements ListCellRenderer<U> {
        private final Function<U, String> toString;

        protected Renderer(Function<U, String> toString) {
            super();
            this.toString = toString;
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends U> list, U value, int index,
              boolean isSelected, boolean cellHasFocus) {
            setOpaque(list.isOpaque());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());

            try {
                setText(toString.apply(value));
            } catch (Exception ignored) {
                setText(nullValue);
            }
            return this;
        }
    }
}
