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

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import megamek.common.EquipmentType;

public class CriticalTable extends JTable implements DragGestureListener, DragSourceListener {

    /**
     *
     */
    private static final long serialVersionUID = -5215375829853683877L;
    private DragSource dragSource;

    public CriticalTable() {
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        if (dge.getTriggerEvent().getSource() instanceof CriticalTable ) {
            CriticalTable crit = (CriticalTable) dge.getTriggerEvent().getSource();
            MouseEvent me = ((MouseEvent) dge.getTriggerEvent());
            int row = crit.rowAtPoint(me.getPoint());
            EquipmentType eq = null;
            try{
                eq = (EquipmentType) crit.getModel().getValueAt(row, CriticalTableModel.EQUIPMENT);
            }catch(Exception ex){
                eq = null;
            }
            if ( eq != null ){
                Transferable t = new StringSelection(eq.getInternalName());
                dragSource.startDrag(dge, DragSource.DefaultCopyDrop, t, this);
            }
        }
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {

    }

    public void dragEnter(DragSourceDragEvent dsde) {
        // TODO Auto-generated method stub

    }

    public void dragExit(DragSourceEvent dse) {
        // TODO Auto-generated method stub

    }

    public void dragOver(DragSourceDragEvent dsde) {
        // TODO Auto-generated method stub

    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

}