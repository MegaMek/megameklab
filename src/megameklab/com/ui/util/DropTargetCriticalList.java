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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.Vector;

import javax.swing.JList;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.weapons.Weapon;

public class DropTargetCriticalList extends JList implements DropTargetListener {

    /**
     * 
     */
    private static final long serialVersionUID = 6847511182922982125L;
    private Mech unit;
    private RefreshListener refresh;

    public DropTargetCriticalList(Vector<String> vector, Mech unit, RefreshListener refresh) {
        super(vector);
        new DropTarget(this, this);
        this.unit = unit;
        this.refresh = refresh;
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void drop(DropTargetDropEvent dtde) {

        if (dtde.getSource() instanceof DropTarget) {
            int location = Integer.parseInt(this.getName());
            Transferable t = dtde.getTransferable();
            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK);
            try {
                String mountName = (String) t.getTransferData(DataFlavor.stringFlavor);
                Mounted eq = null;
                for (  Mounted mount : unit.getEquipment() ){
                    if ( mount.getLocation() == Entity.LOC_NONE && mount.getName().equals(mountName)){
                        eq = mount;
                        break;
                    }
                }
                if ( eq.getType() instanceof Weapon ){
                    for ( Mounted mount : unit.getWeaponList() ){
                        if ( mount.getLocation() == Entity.LOC_NONE && mount == eq){
                            mount.setLocation(location);
                            break;
                        }
                    }
                }else if ( eq.getType()  instanceof AmmoType ){
                    for ( Mounted mount : unit.getAmmo() ){
                        if ( mount.getLocation() == Entity.LOC_NONE && mount == eq){
                            mount.setLocation(location);
                            break;
                        }
                    }
                }else{
                    for ( Mounted mount : unit.getMisc() ){
                        if ( mount.getLocation() == Entity.LOC_NONE && mount == eq){
                            mount.setLocation(location);
                            break;
                        }
                    }
                }

                unit.addEquipment(eq, location,false);
                if (refresh != null) {
                    refresh.refreshAll();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
}