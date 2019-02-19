/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.BattleArmor.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.Weapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.BattleArmor.tabs.BuildTab;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.CriticalTransferHandler;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

/**
 * A component that display a table listing all of the unallocated equipment for
 * the squad and allows dragging of the equipment to criticals to mount it.
 *
 * @author Taharqa
 * @author arlith
 *
 */
public class BuildView extends IView implements ActionListener, MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<Mounted>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    public BuildView(EntitySource eSource) {
        super(eSource);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(getBattleArmor(), CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        CriticalTransferHandler cth = new CriticalTransferHandler(eSource, null);
        equipmentTable.setTransferHandler(cth);

        equipmentList.initColumnSizes(equipmentTable);
        TableColumn column = null;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            }
            column.setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // equipmentScroll.setToolTipText("");
        // equipmentScroll.setPreferredSize(new Dimension(getWidth(),
        // getHeight()));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new java.awt.Dimension(450, 450));
        equipmentScroll.setPreferredSize(new java.awt.Dimension(450, 450));
        equipmentScroll.setTransferHandler(cth);

        mainPanel.add(equipmentScroll);
        equipmentTable.addMouseListener(this);

        this.add(mainPanel);
        // loadEquipmentTable();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Unallocated Equipment",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted mount : getBattleArmor().getMisc()) {
            if ((mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE
                    && !mount.getType().hasFlag(MiscType.F_BA_MANIPULATOR)
                    && !mount.getName().contains("BA Standard"))) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getBattleArmor().getWeaponList()) {
            // Don't display weapons mounted in a detachable weapon pack
            if (mount.isDWPMounted() || mount.isAPMMounted()) {
                continue;
            }
            if ((mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)
                    && (UnitUtil.isBattleArmorWeapon(mount.getType(), getBattleArmor())
                            || UnitUtil.isBattleArmorAPWeapon(mount.getType()))) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getBattleArmor().getAmmo()) {
            // Ignore ammo for one-shot launchers
            if (mount.getLinkedBy() != null && mount.getLinkedBy().isOneShot()) {
                continue;
            }
            // Ignore DWP-mounted ammo
            if (mount.isDWPMounted()) {
                continue;
            }
            if (mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.mountedComparator());

        // Jump Jets
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isJumpJet(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // weapons and ammo
        Vector<Mounted> weaponsNAmmoList = new Vector<Mounted>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon)
                    || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
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
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType)
                    && !UnitUtil.isTSM(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // armor
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isArmor(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            equipmentList.addCrit(masterEquipmentList.get(pos));
        }

    }

    public void refresh() {
        removeAllListeners();
        loadEquipmentTable();
        fireTableRefresh();
        addAllListeners();
    }

    private void removeAllListeners() {
    }

    private void addAllListeners() {
    }

    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getBattleArmor());
        equipmentList.refreshModel();
        // equipmentScroll.setPreferredSize(new Dimension(getWidth() * 90 / 100,
        // getHeight() * 90 / 100));
        // equipmentScroll.setBounds(0, 0, getWidth(), getHeight());
        // equipmentScroll.repaint();
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
        // locations, but only if those locations are make sense
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            final Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow,
                    CriticalTableModel.EQUIPMENT);

            final String[] locNames = BattleArmor.MOUNT_LOC_NAMES;
            // A list of the valid locations we can add the selected eq to
            ArrayList<Integer> validLocs = new ArrayList<Integer>();
            int numLocs = BattleArmor.MOUNT_NUM_LOCS;
            for (int loc = 0; loc < numLocs; loc++) {
                if (TestBattleArmor.isMountLegal(getBattleArmor(), eq, loc)) {
                    validLocs.add(loc);
                }
            }

            if (eq.getLocation() == BattleArmor.LOC_SQUAD && !(eq.getType() instanceof InfantryWeapon)) {
                // Add a menu item for each potential location
                for (Integer location : validLocs) {
                    if (UnitUtil.isValidLocation(getBattleArmor(), eq.getType(), location)) {
                        item = new JMenuItem("Add to " + locNames[location]);

                        final int loc = location;
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                mountEquipmentInLocation(loc, selectedRow);
                            }
                        });
                        popup.add(item);
                    }
                }

                if (!UnitUtil.isArmor(eq.getType()) && !eq.isSquadSupportWeapon()) {
                    item = new JMenuItem("Make individual weapon");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eq.setLocation(BattleArmor.LOC_TROOPER_1);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            } else {
                if (!UnitUtil.isArmor(eq.getType()) && !(eq.getType() instanceof InfantryWeapon)
                        && !((eq.getType() instanceof WeaponType) && (eq.getType().hasFlag(WeaponType.F_TASER)
                                || ((WeaponType) eq.getType()).getAmmoType() == AmmoType.T_NARC))) {
                    item = new JMenuItem("Make squad weapon");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eq.setLocation(BattleArmor.LOC_SQUAD);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            }

            // Allow number of shots selection
            if ((getBattleArmor() instanceof BattleArmor) && eq.getType() instanceof AmmoType) {
                AmmoType at = (AmmoType) eq.getType();
                int maxNumShots = 4;
                int stepSize = 1;
                if (at.getAmmoType() == AmmoType.T_BA_TUBE) {
                    maxNumShots = 8;
                    stepSize = 2;
                }
                for (int i = at.getShots(); i <= maxNumShots; i += stepSize) {
                    if (i == eq.getBaseShotsLeft()) {
                        continue;
                    }
                    item = new JMenuItem("Set Shots: " + i);
                    final int shots = i;
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eq.setShotsLeft(shots);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            }

            // Allow making this a squad support weapon
            if ((eq.getType() instanceof WeaponType) && !eq.isSquadSupportWeapon()
                    && !eq.getType().hasFlag(WeaponType.F_INFANTRY) && eq.getLocation() == BattleArmor.LOC_SQUAD
                    && getBattleArmor().getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                item = new JMenuItem("Mount as squad support weapon");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        eq.setSquadSupportWeapon(true);
                        ((BuildTab) getParent().getParent()).refreshAll();
                    }
                });
                popup.add(item);
            }

            // Adding ammo as a squad support mount is slightly different
            if ((eq.getType() instanceof AmmoType) && !eq.getType().hasFlag(WeaponType.F_MISSILE)
                    && !eq.isSquadSupportWeapon() && eq.getLocation() == BattleArmor.LOC_SQUAD
                    && getBattleArmor().getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                boolean enabled = false;
                for (Mounted weapon : getBattleArmor().getWeaponList()) {
                    WeaponType wtype = (WeaponType) weapon.getType();
                    if (weapon.isSquadSupportWeapon() && AmmoType.isAmmoValid(eq, wtype)) {
                        enabled = true;
                    }
                }
                item = new JMenuItem("Mount as squad support weapon");
                item.setEnabled(enabled);
                item.setToolTipText("Ammo can only be squad mounted along " + "with a weapon that uses it");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        eq.setSquadSupportWeapon(true);
                        ((BuildTab) getParent().getParent()).refreshAll();
                    }
                });
                popup.add(item);
            }

            // Allow removing squad support weapon
            if (eq.isSquadSupportWeapon()) {
                item = new JMenuItem("Remove squad support weapon mount");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        eq.setSquadSupportWeapon(false);
                        // Can't have squad support weapon ammo with no
                        // squad support weapon
                        for (Mounted ammo : getBattleArmor().getAmmo()) {
                            ammo.setSquadSupportWeapon(false);
                        }
                        ((BuildTab) getParent().getParent()).refreshAll();
                    }
                });
                popup.add(item);
            }

            // See if we should allow linking this to a DWP
            if (getBattleArmor().hasWorkingMisc(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && !eq.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && !eq.getType().hasFlag(WeaponType.F_MISSILE) && !(eq.getType() instanceof AmmoType)
                    && !eq.isDWPMounted() && (getBattleArmor()).canMountDWP()) {
                for (Mounted m : getBattleArmor().getMisc()) {
                    // If this isn't a DWP or it's a full DWP, skip
                    if (!m.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK) || m.getLinked() != null) {
                        continue;
                    }

                    String locName;
                    if (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                        locName = "None";
                    } else {
                        locName = BattleArmor.MOUNT_LOC_NAMES[m.getBaMountLoc()];
                    }
                    item = new JMenuItem("Mount in " + m.getName() + " (" + locName + ")");
                    final Mounted dwp = m;
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eq.setLinked(dwp);
                            dwp.setLinked(eq);
                            eq.setDWPMounted(true);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            }

            // Should we allow mounting Ammo in a DWP?
            if ((eq.getType() instanceof AmmoType) && getBattleArmor().hasWorkingMisc(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && !eq.isDWPMounted() && (getBattleArmor()).canMountDWP()) {
                for (final Mounted m : getBattleArmor().getMisc()) {
                    // If this isn't a DWP, skip
                    if (!m.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)) {
                        continue;
                    }
                    // We only want to enable the menu item if the DWP has a
                    // mounted weapon and we clicked on a valid ammo type
                    boolean enabled = false;
                    if (m.getLinked() != null) {
                        EquipmentType etype = m.getLinked().getType();
                        if (etype instanceof WeaponType) {
                            WeaponType wtype = (WeaponType) etype;
                            if (AmmoType.isAmmoValid(eq, wtype)) {
                                enabled = true;
                            }
                        }
                    }
                    String locName;
                    if (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                        locName = "None";
                    } else {
                        locName = BattleArmor.MOUNT_LOC_NAMES[m.getBaMountLoc()];
                    }
                    item = new JMenuItem("Mount in " + m.getName() + " (" + locName + ")");
                    item.setToolTipText("Ammo can only be mounted in a DWP " + "with a valid weapon.");
                    item.setEnabled(enabled);
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            m.getLinked().setLinked(eq);
                            eq.setDWPMounted(true);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            }

            // Right-clicked on a DWP that has an attached weapon
            if (eq.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK) && eq.getLinked() != null) {
                item = new JMenuItem("Remove attached weapon");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Mounted attached = eq.getLinked();
                        attached.setDWPMounted(false);
                        eq.setLinked(null);
                        eq.setLinkedBy(null);
                        attached.setLinked(null);
                        attached.setLinkedBy(null);
                        // Remove any attached ammo
                        for (Mounted ammo : getBattleArmor().getAmmo()) {
                            if (attached.equals(ammo.getLinkedBy())) {
                                ammo.setDWPMounted(false);
                                ammo.setLinked(null);
                                ammo.setLinkedBy(null);
                            }
                        }
                        ((BuildTab) getParent().getParent()).refreshAll();
                    }
                });
                popup.add(item);
            }

            // See if we should allow linking this to an AP Mount
            if (getBattleArmor().hasWorkingMisc(MiscType.F_AP_MOUNT) && eq.getType().hasFlag(WeaponType.F_INFANTRY)
                    && !eq.isAPMMounted()) {
                for (Mounted m : getBattleArmor().getMisc()) {
                    // If this isn't an AP Mount or it's a full AP Mount, skip
                    if (!m.getType().hasFlag(MiscType.F_AP_MOUNT) || m.getLinked() != null) {
                        continue;
                    }

                    // Armored gloves can only carry 1 additional weapon,
                    // regardless of the number of gloves
                    if (m.getType().hasFlag(MiscType.F_ARMORED_GLOVE)) {
                        boolean hasUsedGlove = false;
                        for (Mounted m2 : getBattleArmor().getMisc()) {
                            if (m2.getType().hasFlag(MiscType.F_ARMORED_GLOVE) && (m2.getLinked() != null)) {
                                hasUsedGlove = true;
                            }
                        }
                        if (hasUsedGlove) {
                            continue;
                        }
                    }

                    // Only armored gloves can carry infantry support weapons
                    if (!m.getType().hasFlag(MiscType.F_ARMORED_GLOVE)
                            && eq.getType().hasFlag(WeaponType.F_INF_SUPPORT)) {
                        continue;
                    }

                    String locName;
                    if (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                        locName = "None";
                    } else {
                        locName = BattleArmor.MOUNT_LOC_NAMES[m.getBaMountLoc()];
                    }
                    item = new JMenuItem("Mount in " + m.getName() + " (" + locName + ")");
                    final Mounted apm = m;
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eq.setLinked(apm);
                            apm.setLinked(eq);
                            eq.setAPMMounted(true);
                            ((BuildTab) getParent().getParent()).refreshAll();
                        }
                    });
                    popup.add(item);
                }
            }

            // Right-clicked on a AP Mount that has an attached weapon
            if (eq.getType().hasFlag(MiscType.F_AP_MOUNT) && eq.getLinked() != null) {
                item = new JMenuItem("Remove attached weapon");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Mounted attached = eq.getLinked();
                        attached.setAPMMounted(false);
                        eq.setLinked(null);
                        eq.setLinkedBy(null);
                        attached.setLinked(null);
                        attached.setLinkedBy(null);
                        ((BuildTab) getParent().getParent()).refreshAll();
                    }
                });
                popup.add(item);
            }
            popup.show(this, e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    /**
     * When the user right-clicks on the equipment table, a context menu is
     * generated that his menu items for each possible location that is clicked.
     * When the location is clicked, this is the method that adds the selected
     * equipment to the desired location.
     *
     * @param location
     * @param selectedRow
     */
    private void mountEquipmentInLocation(int location, int selectedRow) {
        Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        try {
            eq.setBaMountLoc(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        UnitUtil.changeMountStatus(getBattleArmor(), eq, BattleArmor.LOC_SQUAD, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BuildTab) getParent().getParent()).refreshAll();
    }

}
