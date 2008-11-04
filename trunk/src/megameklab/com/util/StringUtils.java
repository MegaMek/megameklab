/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.util;

import java.util.Comparator;

import megamek.common.EquipmentType;

public class StringUtils{ 
 
    public static Comparator<? super EquipmentType> equipmentTypeComparator() {
        return new Comparator<EquipmentType>() {
            public int compare(EquipmentType eq1, EquipmentType eq2) {
                String s1 = eq1.getName().toLowerCase();
                String s2 = eq2.getName().toLowerCase();
                return s1.compareTo(s2);
            }
        };
    }
    
}

