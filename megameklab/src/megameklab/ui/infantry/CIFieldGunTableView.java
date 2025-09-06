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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import static megamek.common.equipment.AmmoType.AmmoTypeEnum.CRUISE_MISSILE;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.GAUSS_HEAVY;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.HAG;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.IGAUSS_HEAVY;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.MAGSHOT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.models.XTableColumnModel;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponType;
import megamek.common.interfaces.ITechManager;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.autoCannons.ACWeapon;
import megamek.common.weapons.autoCannons.LBXACWeapon;
import megamek.common.weapons.autoCannons.RifleWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.gaussRifles.GaussWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.InfantryUtil;

/**
 * Shows options for infantry field guns/field artillery
 *
 * @author Neoancient
 */
public class CIFieldGunTableView extends IView implements ActionListener {
    private final static int T_ALL = 0;
    private final static int T_GUN = 1;
    private final static int T_ARTILLERY = 2;
    private final static int T_ARTILLERY_CANNON = 3;
    private final static int T_NUM = 4;

    private RefreshListener refresh;

    private final JButton btnSetGun = new JButton("Set Field Gun");
    private final JButton btnRemoveGun = new JButton("Remove Field Gun");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");

    private final JComboBox<String> choiceType = new JComboBox<>();
    private final JTextField txtFilter = new JTextField(12);

    private final JRadioButton radioButtonStats = new JRadioButton("Stats");
    private final JRadioButton radioButtonFluff = new JRadioButton("Fluff");

    private final TableRowSorter<EquipmentTableModel> equipmentSorter;

    private final EquipmentTableModel masterEquipmentList;
    private final JTable masterEquipmentTable = new JTable();

    public static String getTypeName(int type) {
        return switch (type) {
            case T_ALL -> "All Weapons";
            case T_GUN -> "Field Gun";
            case T_ARTILLERY -> "Artillery";
            case T_ARTILLERY_CANNON -> "Artillery Cannon";
            default -> "?";
        };
    }

    public CIFieldGunTableView(EntitySource eSource, ITechManager techManager) {
        super(eSource);

        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), techManager);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        equipmentSorter = new TableRowSorter<>(masterEquipmentList);
        for (int col = 0; col < EquipmentTableModel.N_COL; col++) {
            equipmentSorter.setComparator(col, masterEquipmentList.getSorter(col));
        }
        masterEquipmentTable.setRowSorter(equipmentSorter);
        ArrayList<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey(EquipmentTableModel.COL_NAME, SortOrder.ASCENDING));
        equipmentSorter.setSortKeys(sortKeys);
        XTableColumnModel equipColumnModel = new XTableColumnModel();
        masterEquipmentTable.setColumnModel(equipColumnModel);
        masterEquipmentTable.createDefaultColumnsFromModel();
        TableColumn column;
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            btnSetGun.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        });
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int view = masterEquipmentTable.getSelectedRow();
            btnSetGun.setEnabled(view >= 0);
        });
        masterEquipmentScroll.setMinimumSize(new Dimension(200, 200));
        masterEquipmentScroll.setPreferredSize(new Dimension(200, 200));

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if (!(eq instanceof WeaponType weaponType) || (weaponType.isCapital())) {
                continue;
            }

            if ((eq instanceof ACWeapon)
                  || (eq instanceof UACWeapon)
                  || (eq instanceof RifleWeapon)
                  || (eq instanceof ArtilleryCannonWeapon)
                  || (eq instanceof LBXACWeapon)) {
                allTypes.add(eq);
            }

            var ammoType = weaponType.getAmmoType();
            if ((eq instanceof GaussWeapon) && !ammoType.isAnyOf(GAUSS_HEAVY, IGAUSS_HEAVY, MAGSHOT, HAG)) {
                allTypes.add(eq);
            }

            if ((eq instanceof ArtilleryWeapon)
                  && !eq.hasFlag(WeaponType.F_BA_WEAPON)
                  && !eq.hasFlag(WeaponType.F_BOMB_WEAPON)
                  && (ammoType != CRUISE_MISSILE)) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);

        DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<>();
        for (int i = 0; i < T_NUM; i++) {
            typeModel.addElement(getTypeName(i));
        }
        choiceType.setModel(typeModel);
        choiceType.setSelectedIndex(1);
        choiceType.addActionListener(evt -> filterEquipment());

        txtFilter.setText("");
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent evt) {
                filterEquipment();
            }

            @Override
            public void insertUpdate(DocumentEvent evt) {
                filterEquipment();
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                filterEquipment();
            }
        });

        ButtonGroup buttonGroupView = new ButtonGroup();
        buttonGroupView.add(radioButtonStats);
        buttonGroupView.add(radioButtonFluff);

        radioButtonStats.setSelected(true);
        radioButtonStats.addActionListener(evt -> setEquipmentView());
        radioButtonFluff.addActionListener(evt -> setEquipmentView());
        chkShowAll.addActionListener(evt -> filterEquipment());
        JPanel viewPanel = new JPanel(new GridLayout(0, 3));
        viewPanel.add(radioButtonStats);
        viewPanel.add(radioButtonFluff);
        viewPanel.add(chkShowAll);
        setEquipmentView();

        JPanel btnPanel = new JPanel(new GridLayout(0, 2));
        btnPanel.add(btnSetGun);
        btnPanel.add(btnRemoveGun);

        // layout

        JPanel databasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnPanel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        databasePanel.add(choiceType, gbc);
        databasePanel.add(txtFilter, gbc);
        gbc.weightx = 1;
        databasePanel.add(viewPanel, gbc);

        gbc.insets = new Insets(2, 0, 0, 0);
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        databasePanel.add(masterEquipmentScroll, gbc);

        setLayout(new BorderLayout());
        add(databasePanel, BorderLayout.CENTER);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        filterEquipment();
        btnRemoveGun.setEnabled(getInfantry().hasFieldWeapon());
        addAllListeners();
    }

    private void removeAllListeners() {
        btnSetGun.removeActionListener(this);
        btnRemoveGun.removeActionListener(this);
    }

    private void addAllListeners() {
        btnSetGun.addActionListener(this);
        btnRemoveGun.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSetGun) {
            int view = masterEquipmentTable.getSelectedRow();
            if (view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equipmentType = masterEquipmentList.getType(selected);
            int maximumFieldGuns;
            if (TestInfantry.isFieldArtilleryType(equipmentType)) {
                maximumFieldGuns = 1;
            } else {
                maximumFieldGuns = getInfantry().getOriginalTrooperCount()
                      / TestInfantry.fieldGunCrewRequirement(equipmentType, getInfantry());
                // If 0 (too few troopers to operate), still add 1 field gun; will show as invalid
                maximumFieldGuns = Math.max(maximumFieldGuns, 1);
            }
            InfantryUtil.replaceFieldGun(getInfantry(), (WeaponType) equipmentType, maximumFieldGuns);
            refresh.refreshAll();

        } else if (e.getSource() == btnRemoveGun) {
            InfantryUtil.replaceFieldGun(getInfantry(), null, 0);
            refresh.refreshAll();
        }
    }

    private void filterEquipment() {
        final int nType = choiceType.getSelectedIndex();
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if ((nType == T_ALL)
                      || ((nType == T_GUN)
                      && !(etype instanceof ArtilleryWeapon)
                      && !(etype instanceof ArtilleryCannonWeapon))
                      || ((nType == T_ARTILLERY) && etype instanceof ArtilleryWeapon)
                      || ((nType == T_ARTILLERY_CANNON) && etype instanceof ArtilleryCannonWeapon)
                ) {
                    if (null != eSource.getTechManager()
                          && !eSource.getTechManager().isLegal(etype)
                          && !chkShowAll.isSelected()) {
                        return false;
                    }

                    if (!txtFilter.getText().isBlank()) {
                        String text = txtFilter.getText();
                        return etype.getName().toLowerCase().contains(text.toLowerCase());
                    } else {
                        return true;
                    }
                }
                return false;
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
    }

    public void setEquipmentView() {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);

        boolean stats = radioButtonStats.isSelected();
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
              !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
              !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), !stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), stats);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), stats);
    }
}
