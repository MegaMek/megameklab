/*
 * MegaMekLab
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
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

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class CIMountView extends IView implements ActionListener, ChangeListener {

    private RefreshListener refresh = null;

    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";

    private final JButton btnSetMount = new JButton("Set Mount");
    private final JRadioButton rbtnStats = new JRadioButton("Stats");
    private final JRadioButton rbtnCustom = new JRadioButton("Custom");
    private final CreatureTableModel tableModel = new CreatureTableModel();
    private final JTable creatureTable = new JTable();
    private final JPanel creatureView = new JPanel();
    private final CardLayout equipmentLayout = new CardLayout();

    private final JTextField txtMountName = new JTextField();
    private final JComboBox<InfantryMount.BeastSize> cbSize = new CustomComboBox<>(InfantryMount.BeastSize::displayName);
    private final JTextField txtWeight = new JTextField();
    private final JSpinner spnMovementPoints = new JSpinner();
    private final JComboBox<EntityMovementMode> cbMovementMode = new JComboBox<>();
    private final JSpinner spnInfantryBonus = new JSpinner();
    private final JSpinner spnVehicleBonus = new JSpinner();
    private final JSpinner spnDamageDivisor = new JSpinner();
    private final JSpinner spnMaxWaterDepth = new JSpinner();
    private final JSpinner spnSecondaryGround = new JSpinner();
    private final JSpinner spnUWEndurance = new JSpinner();

    private final Dimension fieldSize = new Dimension(200, 28);
    private final Dimension spinnerSize = new Dimension(120, 28);
    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    public CIMountView(EntitySource eSource) {
        super(eSource);
        creatureTable.setModel(tableModel);
        creatureTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        creatureTable.setShowGrid(false);
        creatureTable.setDoubleBuffered(true);
        TableCellRenderer renderer = tableModel.new Renderer();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            TableColumn column = creatureTable.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer);
        }

        ButtonGroup bgroupView = new ButtonGroup();
        bgroupView.add(rbtnStats);
        bgroupView.add(rbtnCustom);

        setUpPanels();
        rbtnStats.setSelected(true);
        refresh();
    }

    private void setUpPanels() {
        JPanel databasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnSetMount, gbc);
        btnSetMount.addActionListener(this);

        JPanel btnPanel = new JPanel();
        rbtnStats.addActionListener(ev -> equipmentLayout.show(creatureView, CARD_TABLE));
        rbtnCustom.addActionListener(ev -> equipmentLayout.show(creatureView, CARD_CUSTOM));
        btnPanel.add(rbtnStats);
        btnPanel.add(rbtnCustom);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        databasePanel.add(btnPanel, gbc);

        creatureView.setLayout(equipmentLayout);

        gbc.insets = new Insets(2,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(creatureView, gbc);

        setLayout(new BorderLayout());
        this.add(databasePanel, BorderLayout.CENTER);

        JPanel tableView = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(creatureTable);
        tableView.add(scroll, gbc);

        creatureView.add(tableView, CARD_TABLE);

        JPanel customView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.REMAINDER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.txtMountName.text")), gbc);
        gbc.gridx = 1;
        txtMountName.setToolTipText(resourceMap.getString("CIMountView.txtMountName.tooltip"));
        setFieldSize(txtMountName, fieldSize);
        customView.add(txtMountName, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.cbSize.text")), gbc);
        gbc.gridx = 1;
        cbSize.setToolTipText(resourceMap.getString("CIMountView.cbSize.tooltip"));
        for (var size : InfantryMount.BeastSize.values()) {
            cbSize.addItem(size);
        }
        setFieldSize(cbSize, fieldSize);
        customView.add(cbSize, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.txtWeight.text")), gbc);
        gbc.gridx = 1;
        txtWeight.setToolTipText(resourceMap.getString("CIMountView.txtWeight.tooltip"));
        setFieldSize(txtWeight, spinnerSize);
        customView.add(txtWeight, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnMovementPoints.text")), gbc);
        gbc.gridx = 1;
        spnMovementPoints.setToolTipText(resourceMap.getString("CIMountView.spnMovementPoints.tooltip"));
        initializeSpinner(spnMovementPoints);
        spnMovementPoints.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        customView.add(spnMovementPoints, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.cbMovementMode.text")), gbc);
        gbc.gridx = 1;
        cbMovementMode.setToolTipText(resourceMap.getString("CIMountView.cbMovementMode.tooltip"));
        cbMovementMode.addItem(EntityMovementMode.INF_LEG);
        cbMovementMode.addItem(EntityMovementMode.INF_JUMP);
        cbMovementMode.addItem(EntityMovementMode.VTOL);
        cbMovementMode.addItem(EntityMovementMode.SUBMARINE);
        setFieldSize(cbMovementMode, fieldSize);
        customView.add(cbMovementMode, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnInfantryBonus.text")), gbc);
        gbc.gridx = 1;
        spnInfantryBonus.setToolTipText(resourceMap.getString("CIMountView.spnInfantryBonus.tooltip"));
        initializeSpinner(spnInfantryBonus);
        spnInfantryBonus.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        customView.add(spnInfantryBonus, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnVehicleBonus.text")), gbc);
        gbc.gridx = 1;
        spnVehicleBonus.setToolTipText(resourceMap.getString("CIMountView.spnVehicleBonus.tooltip"));
        initializeSpinner(spnVehicleBonus);
        spnVehicleBonus.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        customView.add(spnVehicleBonus, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnDamageDivisor.text")), gbc);
        gbc.gridx = 1;
        spnDamageDivisor.setToolTipText(resourceMap.getString("CIMountView.spnDamageDivisor.tooltip"));
        initializeSpinner(spnDamageDivisor);
        spnDamageDivisor.setModel(new SpinnerNumberModel(1.0, 1.0, Double.MAX_VALUE, 1.0));
        customView.add(spnDamageDivisor, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnMaxWaterDepth.text")), gbc);
        gbc.gridx = 1;
        spnMaxWaterDepth.setToolTipText(resourceMap.getString("CIMountView.spnMaxWaterDepth.tooltip"));
        spnMaxWaterDepth.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        initializeSpinner(spnMaxWaterDepth);
        customView.add(spnMaxWaterDepth, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnSecondaryGround.text")), gbc);
        gbc.gridx = 1;
        spnSecondaryGround.setToolTipText(resourceMap.getString("CIMountView.spnSecondaryGround.tooltip"));
        initializeSpinner(spnSecondaryGround);
        spnSecondaryGround.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        customView.add(spnSecondaryGround, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        customView.add(new JLabel(resourceMap.getString("CIMountView.spnUWEndurance.text")), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        spnUWEndurance.setToolTipText(resourceMap.getString("CIMountView.spnUWEndurance.tooltip"));
        initializeSpinner(spnUWEndurance);
        spnUWEndurance.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        customView.add(spnUWEndurance, gbc);

        creatureView.add(customView, CARD_CUSTOM);
    }

    private void initializeSpinner(JSpinner spinner) {
        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(UIManager.getColor("TextField.background"));
        setFieldSize(spinner, spinnerSize);
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        InfantryMount mount = getInfantry().getMount();
        if (mount != null) {
            txtMountName.setText(mount.getName());
            cbSize.setSelectedItem(mount.getSize());
            txtWeight.setText(String.valueOf(mount.getWeight()));
            spnMovementPoints.setValue(mount.getMP());
            cbMovementMode.setSelectedItem(mount.getMovementMode());
            spnInfantryBonus.setValue(mount.getBurstDamageDice());
            spnVehicleBonus.setValue(mount.getVehicleDamage());
            spnDamageDivisor.setValue(mount.getDamageDivisor());
            spnMaxWaterDepth.setValue(mount.getMaxWaterDepth());
            spnSecondaryGround.setValue(mount.getSecondaryGroundMP());
            spnUWEndurance.setValue(mount.getUWEndurance());
        }
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private InfantryMount selectedMount(int rowIndex) {
        if ((rowIndex >= 0) && (rowIndex < tableModel.getRowCount())) {
            return InfantryMount.sampleMounts.get(rowIndex);
        } else {
            return null;
        }
    }

    private double getCustomWeight() {
        double weight = 0.5;
        try {
            weight = Double.parseDouble(txtWeight.getText());
        } catch (NumberFormatException ex) {
            txtWeight.setText(String.valueOf(weight));
        }
        return weight;
    }

    private InfantryMount customMount() {
        return new InfantryMount(
                txtMountName.getText(),
                (InfantryMount.BeastSize) cbSize.getSelectedItem(),
                getCustomWeight(),
                (Integer) spnMovementPoints.getValue(),
                (EntityMovementMode) cbMovementMode.getSelectedItem(),
                (Integer) spnInfantryBonus.getValue(),
                (Integer) spnVehicleBonus.getValue(),
                (Double) spnDamageDivisor.getValue(),
                (Integer) spnMaxWaterDepth.getValue(),
                (Integer) spnSecondaryGround.getValue(),
                (Integer) spnUWEndurance.getValue()
        );
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        removeAllListeners();
        if (evt.getSource().equals(btnSetMount)) {
            if (rbtnStats.isSelected()) {
                int view = creatureTable.getSelectedRow();
                if (view < 0) {
                    // Nothing is selected
                    return;
                }
                int selected = creatureTable.convertRowIndexToModel(view);
                InfantryMount newMount = selectedMount(selected);
                if ((getInfantry().getMount() != null) && (getInfantry().getMount().getMovementMode().isSubmarine())
                        && ((newMount == null) || !newMount.getMovementMode().isSubmarine())) {
                    getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.SCUBA);
                }
                getInfantry().setMount(selectedMount(selected));
            } else {
                getInfantry().setMount(customMount());
            }
        }
        addAllListeners();
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }

    private void addAllListeners() {
    }

    private void removeAllListeners() {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        double value = (Double) field.getModel().getValue();
        getInfantry().setArmorDamageDivisor(value);
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }

    private static final class CreatureTableModel implements TableModel {
        private enum Columns {
            TYPE("CIMountView.colType", "CIMountView.colType.tooltip", SwingConstants.LEFT),
            SIZE("CIMountView.colSize", "CIMountView.colSize.tooltip", SwingConstants.LEFT),
            WEIGHT("CIMountView.colWeight", "CIMountView.colWeight.tooltip", SwingConstants.LEFT),
            MP("CIMountView.colMP", "CIMountView.colMP.tooltip", SwingConstants.LEFT),
            BONUS_DAMAGE("CIMountView.colBonusDamage", "CIMountView.colBonusDamage.tooltip", SwingConstants.CENTER),
            DIVISOR("CIMountView.colDivisor", "CIMountView.colDivisor.tooltip", SwingConstants.CENTER),
            TERRAIN("CIMountView.colTerrain", "CIMountView.colTerrain.tooltip", SwingConstants.LEFT);

            final String resId;
            final String tooltipId;
            final int alignment;

            Columns(String resId, String tooltipId, int alignment) {
                this.resId = resId;
                this.tooltipId = tooltipId;
                this.alignment = alignment;
            }
        }

        private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

        @Override
        public int getRowCount() {
            return InfantryMount.sampleMounts.size();
        }

        @Override
        public int getColumnCount() {
            return Columns.values().length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return resourceMap.getString(Columns.values()[columnIndex].resId);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var creature = InfantryMount.sampleMounts.get(rowIndex);
            switch (Columns.values()[columnIndex]) {
                case TYPE:
                    return creature.getName();
                case SIZE:
                    return creature.getSize().displayName();
                case WEIGHT:
                    return NumberFormat.getInstance().format(creature.getWeight());
                case MP:
                    return String.format("%d (%s)", creature.getMP(), creature.getMovementMode().toString());
                case BONUS_DAMAGE:
                    return String.format("%+d%s (%d)", creature.getBurstDamageDice(),
                            creature.getBurstDamageDice() > 0 ? "D6" : "", creature.getVehicleDamage());
                case DIVISOR:
                    return NumberFormat.getInstance().format(creature.getDamageDivisor());
                case TERRAIN:
                    if (creature.getSecondaryGroundMP() > 0) {
                        return resourceMap.getString("CIMountView.1groundMP");
                    } else if (creature.getMovementMode().isSubmarine()) {
                        return resourceMap.getString("CIMountView.asSubmarines");
                    } else if (creature.getMovementMode().isVTOL()) {
                        return resourceMap.getString("CIMountView.asVTOL");
                    } else {
                        return String.format(resourceMap.getString("CIMountView.waterDepth.format"),
                                creature.getMaxWaterDepth() + 1);
                    }
            }
            return "?";
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }

        int getAlignment(int columnIndex) {
            return Columns.values()[columnIndex].alignment;
        }

        String getTooltip(int columnIndex) {
            return resourceMap.getString(Columns.values()[columnIndex].tooltipId);
        }

        public class Renderer extends DefaultTableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus, int row,
                                                           int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                int actualCol = table.convertColumnIndexToModel(column);
                setHorizontalAlignment(getAlignment(actualCol));
                setToolTipText(getTooltip(actualCol));
                return this;
            }
        }
    }
}
