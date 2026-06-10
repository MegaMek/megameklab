/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.infantry;

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
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.models.XTableColumnModel;
import megamek.common.SimpleTechLevel;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.ConvInfantry;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.InfantryUtil;

/**
 * @author (original) jtighe (torren@users.sourceforge.net)
 */
public class CIEquipmentView extends IView implements ActionListener {
    private static final int T_ARCHAIC = 0;
    private static final int T_PERSONAL = 1;
    private static final int T_SUPPORT = 2;
    private static final int T_DISPOSABLE = 3;
    private static final int T_WEAPON = 4;

    private RefreshListener refresh;

    /** The tech manager passed at construction; {@code eSource.getTechManager()} is not yet wired during construction. */
    private final ITechManager techManager;

    private final JButton addPrimaryButton = new JButton("Add Primary");
    private final JButton addSecondaryButton = new JButton("Add Secondary");
    private final JButton addDisposableButton = new JButton("Add Disposable");
    private final JButton removeDisposableButton = new JButton("Remove Disposable");
    private final JComboBox<String> choiceType = new JComboBox<>();
    /**
     * Maps each {@code choiceType} dropdown position to its category constant (the list of categories is
     * tech-dependent).
     */
    private final List<Integer> categoryTypes = new ArrayList<>();
    private final ActionListener categoryListener = evt -> filterEquipment();
    private final JTextField txtFilter = new JTextField(12);

    private final JRadioButton radioButtonStats = new JRadioButton("Stats");
    private final JRadioButton radioButtonFluff = new JRadioButton("Fluff");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");

    private final TableRowSorter<EquipmentTableModel> equipmentSorter;

    private final EquipmentTableModel masterEquipmentList;
    private final JTable masterEquipmentTable = new JTable();

    private final String ADD_PRIMARY_COMMAND = "ADD_PRIMARY";
    private final String ADD_SECONDARY_COMMAND = "ADD_SECONDARY";
    private final String ADD_DISPOSABLE_COMMAND = "ADD_DISPOSABLE";
    private final String REMOVE_DISPOSABLE_COMMAND = "REMOVE_DISPOSABLE";

    public static String getTypeName(int type) {
        return switch (type) {
            case T_WEAPON -> "All Weapons";
            case T_ARCHAIC -> "Archaic Weapons";
            case T_PERSONAL -> "Personal Weapons";
            case T_SUPPORT -> "Support Weapons";
            case T_DISPOSABLE -> "Disposable Weapons";
            default -> "?";
        };
    }

    public CIEquipmentView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        this.techManager = techManager;

        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), techManager);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        equipmentSorter = new TableRowSorter<>(masterEquipmentList);
        for (int col = 0; col < EquipmentTableModel.N_COL; col++) {
            equipmentSorter.setComparator(col, masterEquipmentList.getSorter(col));
        }
        masterEquipmentTable.setRowSorter(equipmentSorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(EquipmentTableModel.COL_NAME, SortOrder.ASCENDING));
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
        ListSelectionListener selectionListener = evt -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType equipmentType = null;
            if (selected >= 0) {
                equipmentType = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addPrimaryButton.setEnabled((null != equipmentType)
                  && eSource.getTechManager().isLegal(equipmentType)
                  && !equipmentType.hasFlag(WeaponType.F_INF_SUPPORT));
            addSecondaryButton.setEnabled((null != equipmentType)
                  && eSource.getTechManager().isLegal(equipmentType)
                  && (TestInfantry.maxSecondaryWeapons(getInfantry()) > 0));
        };
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if (eq instanceof InfantryWeapon) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);

        rebuildCategoryChoices();

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
        radioButtonStats.addActionListener(ev -> setEquipmentView());
        radioButtonFluff.addActionListener(ev -> setEquipmentView());
        chkShowAll.addActionListener(ev -> filterEquipment());
        JPanel viewPanel = new JPanel(new GridLayout(0, 3));
        viewPanel.add(radioButtonStats);
        viewPanel.add(radioButtonFluff);
        viewPanel.add(chkShowAll);
        setEquipmentView();

        JPanel btnPanel = new JPanel(new GridLayout(0, 3));
        btnPanel.add(addPrimaryButton);
        btnPanel.add(addSecondaryButton);
        btnPanel.add(addDisposableButton);
        btnPanel.add(removeDisposableButton);

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
        rebuildCategoryChoices();
        filterEquipment();
        addSecondaryButton.setEnabled(TestInfantry.maxSecondaryWeapons(getInfantry()) > 0);
        removeDisposableButton.setEnabled(getInfantry().hasDisposableWeapon());
        updateAddDisposableButton();
        addAllListeners();
    }

    private void removeAllListeners() {
        addPrimaryButton.removeActionListener(this);
        addSecondaryButton.removeActionListener(this);
        addDisposableButton.removeActionListener(this);
        removeDisposableButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addPrimaryButton.addActionListener(this);
        addSecondaryButton.addActionListener(this);
        addDisposableButton.addActionListener(this);
        removeDisposableButton.addActionListener(this);
        addPrimaryButton.setActionCommand(ADD_PRIMARY_COMMAND);
        addSecondaryButton.setActionCommand(ADD_SECONDARY_COMMAND);
        addDisposableButton.setActionCommand(ADD_DISPOSABLE_COMMAND);
        removeDisposableButton.setActionCommand(REMOVE_DISPOSABLE_COMMAND);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case ADD_PRIMARY_COMMAND, ADD_SECONDARY_COMMAND ->
                  addMainWeapon(evt.getActionCommand().equals(ADD_SECONDARY_COMMAND));
            case ADD_DISPOSABLE_COMMAND -> addDisposableWeapon();
            case REMOVE_DISPOSABLE_COMMAND -> getInfantry().equipDisposableWeapon(null);
            default -> {
                return;
            }
        }
        refresh.refreshAll();
    }

    /**
     * @return the currently selected weapon in the table, or null if none / filtered away
     */
    private EquipmentType selectedEquipment() {
        int view = masterEquipmentTable.getSelectedRow();
        if (view < 0) {
            return null;
        }
        return masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(view));
    }

    private void addMainWeapon(boolean isSecondary) {
        if (selectedEquipment() instanceof InfantryWeapon weapon) {
            InfantryUtil.replaceMainWeapon(getInfantry(), weapon, isSecondary);
            if (weapon.hasFlag(WeaponType.F_TAG)) {
                getInfantry().setSpecializations(getInfantry().getSpecializations() | ConvInfantry.TAG_TROOPS);
                getInfantry().setSecondaryWeaponsPerSquad(2);
            } else if (isSecondary && (getInfantry().getSecondaryWeaponsPerSquad() == 0)) {
                getInfantry().setSecondaryWeaponsPerSquad(1);
            }
        }
    }

    private void addDisposableWeapon() {
        if ((selectedEquipment() instanceof InfantryWeapon weapon)
              && weapon.hasFlag(WeaponType.F_INF_DISPOSABLE)
              && eSource.getTechManager().isLegal(weapon)) {
            getInfantry().equipDisposableWeapon(weapon);
        }
    }

    /**
     * @return true if the game's tech level is Advanced or higher, where the Advanced Disposable Weapon rule (TO:AR
     *       p.106) is available
     */
    private boolean isDisposableTechLevel() {
        return techManager.getTechLevel().ordinal() >= SimpleTechLevel.ADVANCED.ordinal();
    }

    /**
     * (Re)builds the weapon-category filter dropdown. The "Disposable Weapons" category is only offered at Advanced
     * tech level or higher; the previously selected category is preserved when possible. Called on construction and on
     * each refresh so a tech-level change updates the available categories.
     */
    private void rebuildCategoryChoices() {
        int previousType = ((choiceType.getSelectedIndex() >= 0) && (choiceType.getSelectedIndex()
              < categoryTypes.size()))
              ? categoryTypes.get(choiceType.getSelectedIndex())
              : T_PERSONAL;
        choiceType.removeActionListener(categoryListener);
        DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<>();
        categoryTypes.clear();
        int[] order = isDisposableTechLevel()
              ? new int[] { T_ARCHAIC, T_PERSONAL, T_SUPPORT, T_DISPOSABLE, T_WEAPON }
              : new int[] { T_ARCHAIC, T_PERSONAL, T_SUPPORT, T_WEAPON };
        for (int type : order) {
            typeModel.addElement(getTypeName(type));
            categoryTypes.add(type);
        }
        choiceType.setModel(typeModel);
        int index = categoryTypes.indexOf(previousType);
        choiceType.setSelectedIndex((index >= 0) ? index : categoryTypes.indexOf(T_PERSONAL));
        choiceType.addActionListener(categoryListener);
    }

    /**
     * Enables the Add Disposable button whenever the game is at Advanced tech level or higher, where the Disposable
     * Weapon rule (TO:AR p.106) is available. Unlike Add Primary/Secondary, this does not require a selection; the
     * action is a no-op unless a Disposable Weapon is actually selected.
     */
    private void updateAddDisposableButton() {
        addDisposableButton.setEnabled(isDisposableTechLevel());
    }

    private void filterEquipment() {
        final int selectedIndex = choiceType.getSelectedIndex();
        final int nType = ((selectedIndex >= 0) && (selectedIndex < categoryTypes.size()))
              ? categoryTypes.get(selectedIndex)
              : T_PERSONAL;
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if (!(etype instanceof InfantryWeapon weapon)) {
                    return false;
                }
                if (getInfantry().getSquadSize() < (getInfantry().getSecondaryWeaponsPerSquad() * weapon.getCrew())) {
                    return false;
                }
                if ((nType == T_WEAPON)
                      || ((nType == T_ARCHAIC) && etype.hasFlag(WeaponType.F_INF_ARCHAIC))
                      || ((nType == T_PERSONAL)
                      && !etype.hasFlag(WeaponType.F_INF_ARCHAIC)
                      && !etype.hasFlag(WeaponType.F_INF_SUPPORT))
                      || ((nType == T_SUPPORT) && etype.hasFlag(WeaponType.F_INF_SUPPORT))
                      || ((nType == T_DISPOSABLE) && etype.hasFlag(WeaponType.F_INF_DISPOSABLE))
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
        if (radioButtonStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        } else {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE),
                  true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
                  true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
                  true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        }
    }

}
