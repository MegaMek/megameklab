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
    private final JButton btnRemoveMount = new JButton("Remove Mount");
    private final JRadioButton rbtnStats = new JRadioButton("Stats");
    private final JRadioButton rbtnCustom = new JRadioButton("Custom");
    private final CreatureTableModel tableModel = new CreatureTableModel();
    private final JTable creatureTable = new JTable();
    private final JPanel creatureView = new JPanel();
    private final CardLayout equipmentLayout = new CardLayout();

    private final JComboBox<InfantryMount.BeastSize> cbSize = new JComboBox<>();
    private final JTextField weight = new JTextField();
    private final JSpinner spnMovmentPoints = new JSpinner();
    private final JComboBox<EntityMovementMode> cbMovmentMode = new JComboBox<>();
    private final JSpinner spnInfantryBonus = new JSpinner();
    private final JSpinner spnVehicleBonus = new JSpinner();
    private final JSpinner spnDamageDivisor = new JSpinner();
    private final JSpinner spnSecondaryGround = new JSpinner();
    private final JSpinner spnUWEndurance = new JSpinner();

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

        rbtnStats.setSelected(true);

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

        gbc.gridx = 1;
        databasePanel.add(btnRemoveMount, gbc);
        btnRemoveMount.addActionListener(this);

        JPanel btnPanel = new JPanel();
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

        /*
        JPanel customView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        customView.add(new JLabel("Damage Divisor:"), gbc);
        gbc.gridx = 1;
        customView.add(armorValue, gbc);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) armorValue.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(UIManager.getColor("TextField.background"));

        chEncumber.setText("Encumbering");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        customView.add(chEncumber, gbc);

        chSpaceSuit.setText("Space Suit");
        gbc.gridx = 1;
        gbc.gridy = 1;
        customView.add(chSpaceSuit, gbc);

        chDEST.setText("DEST");
        gbc.gridx = 0;
        gbc.gridy = 2;
        customView.add(chDEST, gbc);

        chSneakCamo.setText("Sneak (CAMO)");
        gbc.gridx = 1;
        gbc.gridy = 2;
        customView.add(chSneakCamo, gbc);

        chSneakIR.setText("Sneak (IR)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        customView.add(chSneakIR, gbc);

        chSneakECM.setText("Sneak (ECM)");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        customView.add(chSneakECM, gbc);

        equipmentView.add(customView, CARD_CUSTOM);

         */
    }

    public JLabel createLabel(String text, Dimension maxSize) {
        JLabel label = new JLabel(text, SwingConstants.TRAILING);
        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        /*
        removeAllListeners();
        armorValue.setValue(getInfantry().getArmorDamageDivisor());
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
        filterEquipment();
        btnRemoveArmor.setEnabled(hasArmor());
        rbtnCustom.setEnabled(getInfantry().getArmorKit() == null);
        addAllListeners();

         */
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private InfantryMount getMount(int rowIndex) {
        if ((rowIndex >= 0) && (rowIndex < tableModel.getRowCount())) {
            return InfantryMount.sampleMounts.get(rowIndex);
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        removeAllListeners();
        if (evt.getSource().equals(btnSetMount)) {
            int view = creatureTable.getSelectedRow();
            if (view < 0) {
                // selection got filtered away
                return;
            }
            int selected = creatureTable.convertRowIndexToModel(view);
            InfantryMount newMount = getMount(selected);
            if ((getInfantry().getMount() != null) && (getInfantry().getMount().getMovementMode().isSubmarine())
                    && ((newMount  == null) || !newMount.getMovementMode().isSubmarine())) {
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.SCUBA);
            }
            getInfantry().setMount(getMount(selected));
            rbtnCustom.setEnabled(false);
        } else if (evt.getSource().equals(btnRemoveMount)) {
            getInfantry().setMount(null);
            rbtnCustom.setEnabled(true);
        }
        addAllListeners();
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
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

    private boolean hasMount() {
        return getInfantry().getMount() != null;
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
