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
package megameklab.com.ui.Dropship.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.Weapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AeroBayTransferHandler;
import megameklab.com.ui.util.BayWeaponCriticalTree;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;

/**
 * Shows unallocated equipment and presents menus options for adding equipment to bays.
 * 
 * @author Neoancient
 *
 */
public class AerospaceBuildView extends IView implements MouseListener {
    /**
    *
    */
   private static final long serialVersionUID = 799195356642563937L;
   
   private final List<BayWeaponCriticalTree> arcViews = new CopyOnWriteArrayList<>();
   public void addArcView(BayWeaponCriticalTree l) {
       arcViews.add(l);
   }

   private CriticalTableModel equipmentList;
   private Vector<Mounted> masterEquipmentList = new Vector<Mounted>(10, 1);
   private JTable equipmentTable = new JTable();
   private JScrollPane equipmentScroll = new JScrollPane();
   
   public AerospaceBuildView(EntitySource eSource, RefreshListener refresh) {
       super(eSource);

       equipmentList = new CriticalTableModel(getAero(), CriticalTableModel.BUILDTABLE);

       equipmentTable.setModel(equipmentList);
       equipmentTable.setDragEnabled(true);
       AeroBayTransferHandler cth = new AeroBayTransferHandler(eSource);
       equipmentTable.setTransferHandler(cth);
       TableColumn column = null;
       for (int i = 0; i < equipmentList.getColumnCount(); i++) {
           column = equipmentTable.getColumnModel().getColumn(i);
           if(i == 0) {
               column.setPreferredWidth(350);
           }
           column.setCellRenderer(equipmentList.getRenderer());

       }
       equipmentTable.setIntercellSpacing(new Dimension(0, 0));
       equipmentTable.setShowGrid(false);
       equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       equipmentTable.setDoubleBuffered(true);
       equipmentScroll.setViewportView(equipmentTable);
       equipmentScroll.setMinimumSize(new java.awt.Dimension(400, 400));
       equipmentScroll.setPreferredSize(new java.awt.Dimension(400, 400));
       equipmentScroll.setTransferHandler(cth);

       equipmentTable.addMouseListener(this);

       setLayout(new BorderLayout());
       this.add(equipmentScroll, BorderLayout.CENTER);
       setBorder(BorderFactory.createTitledBorder(
               BorderFactory.createEmptyBorder(), "Unallocated Equipment", 
               TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
   }

   public void addRefreshedListener(RefreshListener l) {
   }

   private void loadEquipmentTable() {
       equipmentList.removeAllCrits();
       masterEquipmentList.clear();
       for (Mounted mount : getAero().getMisc()) {
           if (mount.getLocation() == Entity.LOC_NONE) {
               masterEquipmentList.add(mount);
           }
       }
       for (Mounted mount : getAero().getTotalWeaponList()) {
           if (mount.getLocation() == Entity.LOC_NONE) {
               masterEquipmentList.add(mount);
           }
       }
       for (Mounted mount : getAero().getAmmo()) {
           if ((mount.getLocation() == Entity.LOC_NONE) && 
                   ((mount.getUsableShotsLeft() > 1) || 
                           (((AmmoType)mount.getType()).getAmmoType() == 
                               AmmoType.T_COOLANT_POD))) {
               masterEquipmentList.add(mount);
           }
       }

       Collections.sort(masterEquipmentList, StringUtils.mountedComparator());

       // weapons and ammo
       Vector<Mounted> weaponsNAmmoList = new Vector<Mounted>(10, 1);
       for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
           if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || 
                   (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
               weaponsNAmmoList.add(masterEquipmentList.get(pos));
               masterEquipmentList.remove(pos);
               pos--;
           }
       }
       Collections.sort(weaponsNAmmoList, StringUtils.mountedComparator());
       for (Mounted mount : weaponsNAmmoList) {
           equipmentList.addCrit(mount);
       }

       // Equipment
       for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
           if (masterEquipmentList.get(pos).getType() instanceof MiscType) {
               equipmentList.addCrit(masterEquipmentList.get(pos));
               masterEquipmentList.remove(pos);
               pos--;
           }
       }

       // everything else
       for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
           equipmentList.addCrit(masterEquipmentList.get(pos));
           masterEquipmentList.remove(pos);
           pos--;
       }
   }

   public void refresh() {
       loadEquipmentTable();
       fireTableRefresh();
   }

   public void actionPerformed(ActionEvent e) {
       fireTableRefresh();
   }

   private void fireTableRefresh() {
       equipmentList.updateUnit(getAero());
       equipmentList.refreshModel();
   }

   public CriticalTableModel getTableModel() {
       return equipmentList;
   }

   public JTable getTable() {
       return equipmentTable;
   }
   
   public void mouseClicked(MouseEvent e) {

   }

   public void mouseEntered(MouseEvent e) {

   }

   public void mouseExited(MouseEvent e) {

   }

   public void mousePressed(MouseEvent e) {
       // On right-click, we want to generate menu items to add to specific 
       //  locations, but only if those locations are make sense
       if (e.getButton() == MouseEvent.BUTTON3) {
           JPopupMenu popup = new JPopupMenu();
           JMenuItem item = null;

           if (equipmentTable.getSelectedRowCount() > 1) {
               List<Mounted> list = new ArrayList<>();
               for (int row : equipmentTable.getSelectedRows()) {
                   list.add((Mounted) equipmentTable.getModel().getValueAt(row, CriticalTableModel.EQUIPMENT));
               }
               for (BayWeaponCriticalTree l : arcViews) {
                   // Aerodyne small craft and dropships skip the aft side arcs
                   if (getAero().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                           && !getAero().isSpheroid() && !l.validForAerodyne()) {
                       continue;
                   }
                   if (list.stream().anyMatch(eq -> l.canAdd(eq))) {
                       item = new JMenuItem(l.getLocationName());
                       item.addActionListener(ev -> l.addToLocation(list));
                       popup.add(item);
                   }
               }
           } else {
               final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
               Mounted eq = (Mounted)equipmentTable.getModel().getValueAt(
                       selectedRow, CriticalTableModel.EQUIPMENT);
               for (BayWeaponCriticalTree l : arcViews) {
                   // Aerodyne small craft and dropships skip the aft side arcs
                   if (getAero().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                           && !getAero().isSpheroid() && !l.validForAerodyne()) {
                       continue;
                   }
                   
                   if (getAero().usesWeaponBays()) {
                       JMenu menu = new JMenu(l.getLocationName());
                       for (Mounted bay : l.baysFor(eq)) {
                           if (eq.getType() instanceof AmmoType) {
                               final int shotCount = ((AmmoType)eq.getType()).getShots();
                               JMenu locMenu = new JMenu(bay.getName());
                               for (int shots = shotCount; shots <= eq.getUsableShotsLeft(); shots += shotCount) {
                                   item = new JMenuItem("Add " + shots + ((shots > 1)?" shots" : " shot"));
                                   final int addShots = shots;
                                   item.addActionListener(ev -> l.addAmmoToBay(bay, eq, addShots));
                                   locMenu.add(item);
                               }
                               menu.add(locMenu);
                           } else {
                               item = new JMenuItem(bay.getName());
                               item.addActionListener(ev -> l.addToBay(bay, eq));
                               menu.add(item);
                           }
                       }
                       if (eq.getType() instanceof WeaponType) {
                           final EquipmentType bayType = ((WeaponType)eq.getType()).getBayType();
                           item = new JMenuItem("New " + bayType.getName());
                           item.addActionListener(ev -> l.addToNewBay(bayType,  eq));
                           menu.add(item);
                       }
                       if (menu.getItemCount() > 0) {
                           popup.add(menu);
                       } else if ((eq.getType() instanceof MiscType)
                               && l.canAdd(eq)) {
                           item = new JMenuItem(l.getLocationName());
                           item.addActionListener(ev -> l.addToLocation(eq));
                           popup.add(item);
                       }                           
                   } else {
                       item = new JMenuItem(l.getLocationName());
                       item.addActionListener(ev -> l.addToLocation(eq));
                       popup.add(item);
                   }
               }
           }
           
           popup.show(this, e.getX(), e.getY());
       }
   }

   public void mouseReleased(MouseEvent e) {

   }

}
