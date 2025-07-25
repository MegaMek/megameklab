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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.models.XTableColumnModel;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.MiscType;
import megamek.common.TechConstants;
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
    private final JRadioButton rbtnStats = new JRadioButton("Stats");
    private final JRadioButton rbtnFluff = new JRadioButton("Fluff");
    private final JRadioButton rbtnCustom = new JRadioButton("Custom");
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

    private final JLabel lblSneakWarning = new JLabel("Warning: Setting both DEST and Sneak properties on custom armor " +
                                                         "may cause issues in the display of the armor kit " +
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
        masterEquipmentScroll.setMinimumSize(new Dimension(200,200));
        masterEquipmentScroll.setPreferredSize(new Dimension(200,200));

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

        ButtonGroup bgroupView = new ButtonGroup();
        bgroupView.add(rbtnStats);
        bgroupView.add(rbtnFluff);
        bgroupView.add(rbtnCustom);

        rbtnStats.setSelected(true);
        rbtnStats.addActionListener(ev -> setEquipmentView());
        rbtnFluff.addActionListener(ev -> setEquipmentView());
        rbtnCustom.addActionListener(ev -> setEquipmentView());
        chkShowAll.addActionListener(ev -> filterEquipment());
        
        setUpPanels();
        rbtnStats.setSelected(true);
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
        btnPanel.add(rbtnStats);
        btnPanel.add(rbtnFluff);
        btnPanel.add(rbtnCustom);
        btnPanel.add(chkShowAll);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        databasePanel.add(btnPanel, gbc);
        
        equipmentView.setLayout(equipmentLayout);

        gbc.insets = new Insets(2,0,0,0);
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

        if (getInfantry().hasDEST() &&
              (getInfantry().hasSneakCamo() || getInfantry().hasSneakIR() || getInfantry().hasSneakECM())) {
            lblSneakWarning.setVisible(true);
        } else {
            lblSneakWarning.setVisible(false);
        }

        filterEquipment();
        btnRemoveArmor.setEnabled(hasArmor());
        rbtnCustom.setEnabled(getInfantry().getArmorKit() == null);
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
                rbtnCustom.setEnabled(false);
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
            rbtnCustom.setEnabled(true);
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
        if (rbtnCustom.isSelected()) {
            equipmentLayout.show(equipmentView, CARD_CUSTOM);
            btnSetArmor.setEnabled(false);
            return;
        }
        equipmentLayout.show(equipmentView, CARD_TABLE);
        btnSetArmor.setEnabled(true);
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        if (rbtnStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TLEVEL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TRATING), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPROTOTYPE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPRODUCTION), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DCOMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DEXTINCT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DREINTRO), false);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TLEVEL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TRATING), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPROTOTYPE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPRODUCTION), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DCOMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DEXTINCT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DREINTRO), true);
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

    private final ListSelectionListener selectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            btnSetArmor.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        }
    };
}
