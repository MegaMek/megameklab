/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

package megameklab.com.ui.util;

import java.util.function.Function;

import megamek.common.ITechnology;

/**
 * ComboBox for equipment that implement ITechnology. Has a boolean flag that
 * can toggle prefixing "Clan" or "IS" to the equipment name.
 *
 * @author Neoancient
 *
 */
public class TechComboBox<T extends ITechnology> extends CustomComboBox<T> {

    /**
     *
     */
    private static final long serialVersionUID = -4155090672011004423L;

    private boolean showTechBase = false;

    public TechComboBox(Function<T, String> toString) {
        super();
        setRenderer(new Renderer<T>(t -> getTechName(toString.apply(t), t.getTechBase())));
    }

    public TechComboBox(T[] values, Function<T, String> toString) {
        super(values);
        setRenderer(new Renderer<T>(t -> getTechName(toString.apply(t), t.getTechBase())));
    }

    public void showTechBase(boolean show) {
        showTechBase = show;
    }

    private String getTechName(String name, int techBase) {
        StringBuilder sb = new StringBuilder();
        if (showTechBase) {
            if (techBase == ITechnology.TECH_BASE_CLAN) {
                sb.append("Clan ");
            } else if (techBase == ITechnology.TECH_BASE_IS) {
                sb.append("IS ");
            }
        }
        sb.append(name);
        return sb.toString();
    }

}
