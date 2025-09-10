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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.models.XTableColumnModel;
import megamek.common.TechConstants;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;

public class CIArmorView extends IView implements ActionListener, ChangeListener {
    private RefreshListener refresh = null;

    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";

    private final JButton btnSetArmor = new JButton("Set Armor");
    private final JButton btnRemoveArmor = new JButton("Remove Armor");
    private final JTextField txtFilter = new JTextField(12);
    private final JRadioButton radioButtonStats = new JRadioButton("Stats");
    private final JRadioButton radioButtonFluff = new JRadioButton("Fluff");
    private final JRadioButton radioButtonCustom = new JRadioButton("Custom");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");
    private final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final EquipmentTableModel masterEquipmentList;
    private final JTable masterEquipmentTable = new JTable();
    private final JScrollPane masterEquipmentScroll = new JScrollPane();
    private final JPanel equipmentView = new JPanel();
    private final CardLayout equipmentLayout = new CardLayout();

    JCheckBox chEncumber = new JCheckBox("Encumbering");
    JCheckBox chSpaceSuit = new JCheckBox("Space Suit");
    JCheckBox chDEST = new JCheckBox("DEST");
    JCheckBox chSneakCamo = new JCheckBox("Sneak (CAMO)");
    JCheckBox chSneakIR = new JCheckBox("Sneak (IR)");
    JCheckBox chSneakECM = new JCheckBox("Sneak (ECM)");
    private final JSpinner armorValue = new JSpinner(new SpinnerNumberModel(1.0, 0.5, 3.0, 0.5));

    private final JLabel lblSneakWarning = new JLabel("Warning: Setting both DEST and Sneak properties on custom armor "
          +
          "may cause issues in the display of the armor kit "
          +
          "information.");

    public CIArmorView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
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
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            TableColumn column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionListener selectionListener = e -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            btnSetArmor.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        };
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int view = masterEquipmentTable.getSelectedRow();
            if (view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            btnSetArmor.setEnabled((equip instanceof MiscType) && (equip.hasFlag(MiscType.F_ARMOR_KIT)));
        });
        masterEquipmentScroll.setMinimumSize(new Dimension(200, 200));
        masterEquipmentScroll.setPreferredSize(new Dimension(200, 200));

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_ARMOR_KIT))) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);

        txtFilter.setText("");
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterEquipment();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterEquipment();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterEquipment();
            }
        });

        ButtonGroup buttonGroupView = new ButtonGroup();
        buttonGroupView.add(radioButtonStats);
        buttonGroupView.add(radioButtonFluff);
        buttonGroupView.add(radioButtonCustom);

        radioButtonStats.setSelected(true);
        radioButtonStats.addActionListener(ev -> setEquipmentView());
        radioButtonFluff.addActionListener(ev -> setEquipmentView());
        radioButtonCustom.addActionListener(ev -> setEquipmentView());
        chkShowAll.addActionListener(ev -> filterEquipment());

        setUpPanels();
        radioButtonStats.setSelected(true);
        setEquipmentView();
        refresh();
    }

    private void setUpPanels() {
        JPanel databasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnSetArmor, gbc);
        btnSetArmor.addActionListener(this);

        gbc.gridx = 1;
        databasePanel.add(btnRemoveArmor, gbc);
        btnRemoveArmor.addActionListener(this);

        JPanel btnPanel = new JPanel();
        btnPanel.add(radioButtonStats);
        btnPanel.add(radioButtonFluff);
        btnPanel.add(radioButtonCustom);
        btnPanel.add(chkShowAll);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        databasePanel.add(btnPanel, gbc);

        equipmentView.setLayout(equipmentLayout);

        gbc.insets = new Insets(2, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(equipmentView, gbc);

        setLayout(new BorderLayout());
        add(databasePanel, BorderLayout.CENTER);

        JPanel tableView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tableView.add(new JLabel("Filter:"), gbc);

        gbc.gridx = 1;
        tableView.add(txtFilter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tableView.add(masterEquipmentScroll, gbc);

        equipmentView.add(tableView, CARD_TABLE);

        JPanel customView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        customView.add(new StandardBuildLabel("Damage Divisor:"), gbc);
        customView.add(armorValue, gbc);
        JFormattedTextField textField = ((DefaultEditor) armorValue.getEditor()).getTextField();
        textField.setEditable(false);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        customView.add(chEncumber, gbc);
        customView.add(chSpaceSuit, gbc);
        gbc.gridy++;
        customView.add(chDEST, gbc);
        customView.add(chSneakCamo, gbc);
        gbc.gridy++;
        customView.add(chSneakIR, gbc);
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        customView.add(chSneakECM, gbc);

        lblSneakWarning.setForeground(Color.RED);
        lblSneakWarning.setVisible(false);
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        customView.add(lblSneakWarning, gbc);

        equipmentView.add(customView, CARD_CUSTOM);
    }

    public void refresh() {
        removeAllListeners();
        armorValue.setValue(getInfantry().getCustomArmorDamageDivisor());
        chEncumber.setSelected(getInfantry().isArmorEncumbering());
        chSpaceSuit.setSelected(getInfantry().hasSpaceSuit());
        chDEST.setSelected(getInfantry().hasDEST());
        chSneakCamo.setSelected(getInfantry().hasSneakCamo());
        chSneakIR.setSelected(getInfantry().hasSneakIR());
        chSneakECM.setSelected(getInfantry().hasSneakECM());
        if (getInfantry().getTechLevel() < TechConstants.T_TW_ALL) {
            armorValue.setEnabled(false);
            chEncumber.setEnabled(false);
            chSpaceSuit.setEnabled(false);
            chDEST.setEnabled(false);
            chSneakCamo.setEnabled(false);
            chSneakIR.setEnabled(false);
            chSneakECM.setEnabled(false);
        } else {
            armorValue.setEnabled(true);
            chEncumber.setEnabled(true);
            chSpaceSuit.setEnabled(true);
            chDEST.setEnabled(true);
            chSneakCamo.setEnabled(true);
            chSneakIR.setEnabled(true);
            chSneakECM.setEnabled(true);
        }

        lblSneakWarning.setVisible(getInfantry().hasDEST() &&
              (getInfantry().hasSneakCamo() || getInfantry().hasSneakIR() || getInfantry().hasSneakECM()));

        filterEquipment();
        btnRemoveArmor.setEnabled(hasArmor());
        radioButtonCustom.setEnabled(getInfantry().getArmorKit() == null);
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        removeAllListeners();
        if (evt.getSource().equals(btnSetArmor)) {
            int view = masterEquipmentTable.getSelectedRow();
            if (view < 0) {
                // selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            if ((equip instanceof MiscType) && (equip.hasFlag(MiscType.F_ARMOR_KIT))) {
                getInfantry().setArmorKit(equip);
                radioButtonCustom.setEnabled(false);
            }
        } else if (evt.getSource().equals(btnRemoveArmor)) {
            getInfantry().setArmorKit(null);
            getInfantry().setCustomArmorDamageDivisor(1.0);
            getInfantry().setArmorEncumbering(false);
            getInfantry().setSpaceSuit(false);
            getInfantry().setDEST(false);
            getInfantry().setSneakCamo(false);
            getInfantry().setSneakIR(false);
            getInfantry().setSneakECM(false);
            radioButtonCustom.setEnabled(true);
        }

        if (evt.getSource().equals(chEncumber)) {
            getInfantry().setArmorEncumbering(chEncumber.isSelected());
        } else if (evt.getSource().equals(chSpaceSuit)) {
            getInfantry().setSpaceSuit(chSpaceSuit.isSelected());
        } else if (evt.getSource().equals(chDEST)) {
            getInfantry().setDEST(chDEST.isSelected());
        } else if (evt.getSource().equals(chSneakCamo)) {
            getInfantry().setSneakCamo(chSneakCamo.isSelected());
        } else if (evt.getSource().equals(chSneakIR)) {
            getInfantry().setSneakIR(chSneakIR.isSelected());
        } else if (evt.getSource().equals(chSneakECM)) {
            getInfantry().setSneakECM(chSneakECM.isSelected());
        }
        addAllListeners();
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
    }

    private void addAllListeners() {
        chEncumber.addActionListener(this);
        chSpaceSuit.addActionListener(this);
        chDEST.addActionListener(this);
        chSneakCamo.addActionListener(this);
        chSneakIR.addActionListener(this);
        chSneakECM.addActionListener(this);
        armorValue.addChangeListener(this);
    }

    private void removeAllListeners() {
        chEncumber.removeActionListener(this);
        chSpaceSuit.removeActionListener(this);
        chDEST.removeActionListener(this);
        chSneakCamo.removeActionListener(this);
        chSneakIR.removeActionListener(this);
        chSneakECM.removeActionListener(this);
        armorValue.removeChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        double value = (Double) field.getModel().getValue();
        getInfantry().setCustomArmorDamageDivisor(value);
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }

    private void filterEquipment() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if (!(etype instanceof MiscType) || !(etype.hasFlag(MiscType.F_ARMOR_KIT))) {
                    return false;
                } else if ((null != eSource.getTechManager())
                      && !eSource.getTechManager().isLegal(etype) && !chkShowAll.isSelected()) {
                    return false;
                } else if (!etype.isAvailableIn(getInfantry().getTechLevelYear(),
                      CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                    return false;
                } else if (!txtFilter.getText().isBlank()) {
                    return etype.getName().toLowerCase().contains(txtFilter.getText().toLowerCase());
                } else {
                    return true;
                }
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
    }

    public void setEquipmentView() {
        if (radioButtonCustom.isSelected()) {
            equipmentLayout.show(equipmentView, CARD_CUSTOM);
            btnSetArmor.setEnabled(false);
            return;
        }
        equipmentLayout.show(equipmentView, CARD_TABLE);
        btnSetArmor.setEnabled(true);
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        if (radioButtonStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
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
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
                  true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        }
    }

    private boolean hasArmor() {
        return getInfantry().getArmorKit() != null
              || !getInfantry().getArmorDesc().equals("1.0");
    }

}
