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

package megameklab.com.ui.util;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;


public class UnitUtil{
    /**
     * tells is EquipementType is armor or Structure that uses crits/mounted
     * @param eq
     * @return
     */
    public static boolean isArmorOrStructure(EquipmentType eq){
        return  eq instanceof MiscType && ( eq.hasFlag(MiscType.F_ENDO_STEEL) || eq.hasFlag(MiscType.F_FERRO_FIBROUS) || eq.hasFlag(MiscType.F_TSM));
    } 
    /**
     * Returns the number of crits used by EquipmentType eq, 1 if armor or structure EquipmentType
     * @param unit
     * @param eq
     * @return
     */
    public static int getCritsUsed(Mech unit, EquipmentType eq){
        if ( UnitUtil.isArmorOrStructure(eq) ){
            return 1;
        }
        
        return eq.getCriticals(unit);
    }
    /**
     * Removes a Mounted object from the units various equipment lists
     * @param unit
     * @param eq
     */
    public static void removeMounted(Mech unit, EquipmentType eq) {
        Mounted equipment = null;
        for (Mounted mount : unit.getEquipment()) {
            if (mount.getType().getInternalName().equals(eq.getInternalName())) {
                equipment = mount;
                break;
            }
        }
        
        if ( equipment == null ){
            return;
        }
        
        UnitUtil.removeCriticals(unit, equipment);
        
        unit.getEquipment().remove(equipment);

        if (equipment.getType() instanceof MiscType) {
            unit.getMisc().remove(equipment);
        } else if (equipment.getType() instanceof AmmoType) {
            unit.getAmmo().remove(equipment);
        } else {
            unit.getWeaponList().remove(equipment);
        }

    }

/**
 * Sets the corresponding critical slots to null for the Mounted object.
 * @param unit
 * @param eq
 */
    public static void removeCriticals(Mech unit, Mounted eq){
        int location = eq.getLocation();
        if (location != Mech.LOC_NONE) {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            for (int slot = 0; slot < unit.getNumberOfCriticals(location) && critsUsed > 0; slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if (cs != null && cs.getIndex() == unit.getEquipmentNum(eq)) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                    --critsUsed;
                }
            }
        }
    }
    
    public static void resetCriticalsAndMounts(Mech unit){
        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++ ){
            for ( int slot = 0; slot < unit.getNumberOfCriticals(location); slot++ ){
                CriticalSlot cs = unit.getCritical(location, slot);
                
                if ( cs != null && cs.getType() == CriticalSlot.TYPE_EQUIPMENT ){
                    cs = null;
                    unit.setCritical(location, slot, cs);
                }
            }
        }
        
        for ( Mounted mount : unit.getEquipment() ){
            mount.setLocation(Mech.LOC_NONE,false);
        }
        
    }
}