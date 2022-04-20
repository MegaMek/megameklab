/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import megamek.common.ITechnology;

import java.util.function.Function;

/**
 * ComboBox for equipment that implement ITechnology.
 * Has a boolean flag that can toggle prefixing "Clan" or "IS" to the equipment name.
 * 
 * @author Neoancient
 */
public class TechComboBox<T extends ITechnology> extends CustomComboBox<T> {
    private boolean showTechBase = false;

    public TechComboBox(Function<T, String> toString) {
        super();
        setRenderer(new Renderer<>(t -> getTechName(toString.apply(t), t.getTechBase())));
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
