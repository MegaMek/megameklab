/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
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

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.BaConstructionUtil;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.Weapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.CriticalTransferHandler;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.BattleArmorUtil;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

/**
 * A component that display a table listing all the unallocated equipment for the squad and allows dragging of the
 * equipment to criticalSlots to mount it.
 *
 * @author Taharqa
 * @author arlith
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BABuildView extends IView implements ActionListener, MouseListener {
    private static final MMLogger logger = MMLogger.create(BABuildView.class);

    private final CriticalTableModel equipmentList;
    private final CriticalTransferHandler transferHandler;

    public List<Mounted<?>> getEquipment() {
        return equipmentList.getCrits();
    }

    private final Vector<Mounted<?>> masterEquipmentList = new Vector<>(10, 1);
    private final JTable equipmentTable = new JTable();

    public BABuildView(EntitySource eSource) {
        super(eSource);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(getBattleArmor(),
              CriticalTableModel.BUILD_TABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        transferHandler = new CriticalTransferHandler(eSource, null);
        equipmentTable.setTransferHandler(transferHandler);

        equipmentList.initColumnSizes(equipmentTable);
        TableColumn column;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            }
            column.setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new Dimension(450, 450));
        equipmentScroll.setPreferredSize(new Dimension(450, 450));
        equipmentScroll.setTransferHandler(transferHandler);

        mainPanel.add(equipmentScroll);
        equipmentTable.addMouseListener(this);

        this.add(mainPanel);
        setBorder(BorderFactory.createTitledBorder(
              BorderFactory.createEmptyBorder(), "Unallocated Equipment",
              TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {
        transferHandler.setRefresh(l);
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted<?> mount : getBattleArmor().getMisc()) {
            if ((mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE
                  && !mount.getType().hasFlag(MiscType.F_BA_MANIPULATOR)
                  && mount.getType().getNumCriticalSlots(getBattleArmor()) > 0)) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted<?> mount : getBattleArmor().getWeaponList()) {
            // Don't display weapons mounted in a detachable weapon pack
            if (mount.isDWPMounted() || mount.isAPMMounted()) {
                continue;
            }
            if ((mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)
                  && (BattleArmorUtil.isBattleArmorWeapon(mount.getType(), getBattleArmor())
                  || BattleArmorUtil.isBattleArmorAPWeapon(mount.getType()))) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted<?> mount : getBattleArmor().getAmmo()) {
            // Ignore ammo for one-shot launchers
            if ((mount.getLinkedBy() != null) && mount.getLinkedBy().isOneShot()) {
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

        masterEquipmentList.sort(StringUtils.mountedComparator());

        // Jump Jets
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isJumpJet(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // weapons and ammo
        Vector<Mounted<?>> weaponsNAmmoList = new Vector<>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon)
                  || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        weaponsNAmmoList.sort(StringUtils.mountedComparator());
        for (Mounted<?> mount : weaponsNAmmoList) {
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
        for (Mounted<?> mounted : masterEquipmentList) {
            equipmentList.addCrit(mounted);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getBattleArmor());
        equipmentList.refreshModel();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // On right-click, we want to generate menu items to add to specific
        // locations, but only if those locations are make sense
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            boolean hasDWP = getBattleArmor().hasWorkingMisc(MiscType.F_DETACHABLE_WEAPON_PACK);
            boolean hasAPM = getBattleArmor().hasWorkingMisc(MiscType.F_AP_MOUNT);

            final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            final Mounted<?> eq = (Mounted<?>) equipmentTable.getModel().getValueAt(
                  selectedRow, CriticalTableModel.EQUIPMENT);

            final String[] locNames = BattleArmor.MOUNT_LOC_NAMES;
            // A list of the valid locations we can add the selected eq to
            ArrayList<Integer> validLocs = new ArrayList<>();
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
                        item.addActionListener(evt -> mountEquipmentInLocation(location, selectedRow));
                        popup.add(item);
                    }
                }

                if (!UnitUtil.isArmor(eq.getType()) && !eq.isSquadSupportWeapon()) {
                    item = new JMenuItem("Make individual weapon");
                    item.addActionListener(evt -> {
                        eq.setLocation(BattleArmor.LOC_TROOPER_1);
                        ((BABuildTab) getParent().getParent()).refreshAll();
                    });
                    popup.add(item);
                }
            } else {
                if (!UnitUtil.isArmor(eq.getType())
                      && !(eq.getType() instanceof InfantryWeapon)
                      && !((eq.getType() instanceof WeaponType)
                      && (eq.getType().hasFlag(WeaponType.F_TASER)
                      || ((WeaponType) eq.getType()).getAmmoType() == AmmoType.AmmoTypeEnum.NARC))) {
                    item = new JMenuItem("Make squad weapon");
                    item.addActionListener(evt -> {
                        eq.setLocation(BattleArmor.LOC_SQUAD);
                        ((BABuildTab) getParent().getParent()).refreshAll();
                    });
                    popup.add(item);
                }
            }

            // Allow number of shots selection
            if ((getBattleArmor() != null) && (eq.getType() instanceof AmmoType ammoType)) {
                int maxNumShots = 4;
                int stepSize = 1;
                if (ammoType.getAmmoType() == AmmoType.AmmoTypeEnum.BA_TUBE) {
                    maxNumShots = 8;
                    stepSize = 2;
                }
                for (int i = ammoType.getShots(); i <= maxNumShots; i += stepSize) {
                    if (i == eq.getBaseShotsLeft()) {
                        continue;
                    }
                    item = new JMenuItem("Set Shots: " + i);
                    final int shots = i;
                    item.addActionListener(evt -> {
                        eq.setShotsLeft(shots);
                        ((BABuildTab) getParent().getParent()).refreshAll();
                    });
                    popup.add(item);
                }
            }

            // Allow making this a squad support weapon
            if ((eq.getType() instanceof WeaponType)
                  && !eq.isSquadSupportWeapon()
                  && !eq.getType().hasFlag(WeaponType.F_INFANTRY)
                  && eq.getLocation() == BattleArmor.LOC_SQUAD
                  && getBattleArmor().getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                item = new JMenuItem("Mount as squad support weapon");
                item.addActionListener(evt -> {
                    eq.setSquadSupportWeapon(true);
                    ((BABuildTab) getParent().getParent()).refreshAll();
                });
                popup.add(item);
            }

            // Adding ammo as a squad support mount is slightly different
            if ((eq.getType() instanceof AmmoType)
                  && !eq.getType().hasFlag(WeaponType.F_MISSILE)
                  && !eq.isSquadSupportWeapon()
                  && eq.getLocation() == BattleArmor.LOC_SQUAD
                  && getBattleArmor().getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                boolean enabled = false;
                for (Mounted<?> weapon : getBattleArmor().getWeaponList()) {
                    WeaponType weaponType = (WeaponType) weapon.getType();
                    if (weapon.isSquadSupportWeapon() && AmmoType.isAmmoValid(eq, weaponType)) {
                        enabled = true;
                    }
                }
                item = new JMenuItem("Mount as squad support weapon");
                item.setEnabled(enabled);
                item.setToolTipText("Ammo can only be squad mounted along with a weapon that uses it");
                item.addActionListener(evt -> {
                    eq.setSquadSupportWeapon(true);
                    ((BABuildTab) getParent().getParent()).refreshAll();
                });
                popup.add(item);
            }

            // Allow removing squad support weapon
            if (eq.isSquadSupportWeapon()) {
                item = new JMenuItem("Remove squad support weapon mount");
                item.addActionListener(evt -> {
                    eq.setSquadSupportWeapon(false);
                    // Can't have squad support weapon ammo with no squad support weapon
                    for (Mounted<?> ammo : getBattleArmor().getAmmo()) {
                        ammo.setSquadSupportWeapon(false);
                    }
                    ((BABuildTab) getParent().getParent()).refreshAll();
                });
                popup.add(item);
            }

            // See if we should allow linking this to a DWP
            if (eq.getType().canBeMountedOnBaDwp() && hasDWP) {
                for (final MiscMounted misc : getBattleArmor().getMisc()) {
                    // only allow mounting on allocated DWP to avoid unallocated equipment with links to other stuff
                    if (misc.is(EquipmentTypeLookup.BA_DWP)
                          && misc.getLinked() == null
                          && misc.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE) {

                        // misc is an allocated and empty (i.e., available) DWP
                        String locName = BattleArmor.MOUNT_LOC_NAMES[misc.getBaMountLoc()];
                        item = new JMenuItem("Mount in " + misc.getName() + " (" + locName + ")");
                        item.addActionListener(evt -> {
                            BattleArmorUtil.mountOnDwp(eq, misc);
                            ((BABuildTab) getParent().getParent()).refreshAll();
                        });
                        popup.add(item);
                    }
                }
            }

            // Should we allow mounting Ammo in a DWP?
            if ((eq.getType() instanceof AmmoType) && hasDWP && !eq.isDWPMounted()) {
                for (Mounted<?> misc : getBattleArmor().getMisc()) {
                    // If this isn't a DWP, skip
                    if (!misc.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)) {
                        continue;
                    }
                    // We only want to enable the menu item if the DWP has a
                    // mounted weapon, and we clicked on a valid ammo type
                    boolean enabled = false;
                    if (misc.getLinked() != null) {
                        EquipmentType equipmentType = misc.getLinked().getType();
                        if (equipmentType instanceof WeaponType weaponType) {
                            if (AmmoType.isAmmoValid(eq, weaponType)) {
                                enabled = true;
                            }
                        }
                    }
                    String locName;
                    if (misc.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                        locName = "None";
                    } else {
                        locName = BattleArmor.MOUNT_LOC_NAMES[misc.getBaMountLoc()];
                    }
                    item = new JMenuItem("Mount in " + misc.getName() + " (" + locName + ")");
                    item.setToolTipText("Ammo can only be mounted in a DWP with a valid weapon.");
                    item.setEnabled(enabled);
                    item.addActionListener(evt -> {
                        misc.getLinked().setLinked(eq);
                        eq.setDWPMounted(true);
                        ((BABuildTab) getParent().getParent()).refreshAll();
                    });
                    popup.add(item);
                }
            }

            // See if we should allow linking this to an AP Mount
            if (hasAPM && eq.getType().hasFlag(WeaponType.F_INFANTRY) && !eq.isAPMMounted()) {
                for (Mounted<?> misc : getBattleArmor().getMisc()) {
                    // If this isn't an allocated AP Mount, or it's a full AP Mount, skip
                    if (!misc.getType().hasFlag(MiscType.F_AP_MOUNT) || misc.getLinked() != null
                          || misc.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE) {
                        continue;
                    }

                    // Armored gloves can only carry 1 additional weapon, regardless of the number of gloves, TM p.171
                    if (misc.getType().hasFlag(MiscType.F_ARMORED_GLOVE)) {
                        boolean hasUsedGlove = false;
                        for (Mounted<?> m2 : getBattleArmor().getMisc()) {
                            if (m2.getType().hasFlag(MiscType.F_ARMORED_GLOVE) && (m2.getLinked() != null)) {
                                hasUsedGlove = true;
                            }
                        }
                        if (hasUsedGlove) {
                            continue;
                        }
                    } else if (eq.getType().hasFlag(WeaponType.F_INF_SUPPORT)) {
                        // Only armored gloves can carry infantry support weapons
                        continue;
                    }

                    String locName = BattleArmor.MOUNT_LOC_NAMES[misc.getBaMountLoc()];
                    item = new JMenuItem("Mount in " + misc.getName() + " (" + locName + ")");
                    item.addActionListener(evt -> {
                        BaConstructionUtil.mountOnApm(eq, misc);
                        ((BABuildTab) getParent().getParent()).refreshAll();
                    });
                    popup.add(item);
                }
            }

            popup.show(equipmentTable, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * When the user right-clicks on the equipment table, a context menu is generated that his menu items for each
     * possible location that is clicked. When the location is clicked, this is the method that adds the selected
     * equipment to the desired location.
     *
     */
    private void mountEquipmentInLocation(int location, int selectedRow) {
        Mounted<?> eq = (Mounted<?>) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        try {
            eq.setBaMountLoc(location);
        } catch (Exception ex) {
            logger.error("", ex);
        }

        UnitUtil.changeMountStatus(getBattleArmor(), eq, BattleArmor.LOC_SQUAD, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BABuildTab) getParent().getParent()).refreshAll();
    }
}
