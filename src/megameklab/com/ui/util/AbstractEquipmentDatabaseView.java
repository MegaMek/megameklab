/*
 * MegaMekLab
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.com.ui.util;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static megameklab.com.ui.util.EquipmentDatabaseCategory.*;
import static megameklab.com.ui.util.EquipmentTableModel.*;

/**
 * A base class for creating an equipment database table that shows all equipment available to the
 * unit and by default includes filters such as an "Energy Weapon" toggle.
 * In addition to the abstract methods, implementing classes may override
 * getUsedButtons() to control the shown filter buttons,
 * shouldShow() to control the equipment filtering when the standard filters are not used, and
 * updateVisibleColumns() to control the shown columns. updateVisibleColumns() may make use
 * of boolean tableMode which is toggled by the "Switch Table Mode" button.
 */
public abstract class AbstractEquipmentDatabaseView extends IView {

    protected RefreshListener refresh;

    private final EquipmentTableModel masterEquipmentModel;
    protected final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final EquipmentDatabaseTable masterEquipmentTable;

    private final AddAction addAction = new AddAction();
    private final JButton addButton = new JButton(addAction);
    private final JButton addMultipleButton = new JButton();
    private final SpinnerNumberModel addMultipleModel = new SpinnerNumberModel(5, 2, null, 1);
    private final JSpinner addMultipleCount = new JSpinner(addMultipleModel);
    protected final JToggleButton showEnergyButton = new JToggleButton(ENERGY.getDisplayName(), true);
    protected final JToggleButton showBallisticButton = new JToggleButton(BALLISTIC.getDisplayName(), true);
    protected final JToggleButton showMissileButton = new JToggleButton(MISSILE.getDisplayName(), true);
    protected final JToggleButton showArtilleryButton = new JToggleButton(ARTILLERY.getDisplayName());
    protected final JToggleButton showPhysicalButton = new JToggleButton(PHYSICAL.getDisplayName());
    protected final JToggleButton showCapitalButton = new JToggleButton(CAPITAL.getDisplayName());
    protected final JToggleButton showAmmoButton = new JToggleButton(AMMO.getDisplayName());
    protected final JToggleButton showOtherButton = new JToggleButton(OTHER.getDisplayName());

    protected final JToggleButton hideProtoButton = new JToggleButton(PROTOTYPE.getDisplayName());
    protected final JToggleButton hideOneShotButton = new JToggleButton(ONE_SHOT.getDisplayName());
    protected final JToggleButton hideTorpedoButton = new JToggleButton(TORPEDO.getDisplayName());
    protected final JToggleButton hideAPButton = new JToggleButton(AP.getDisplayName());
    protected final JToggleButton hideUnavailButton = new JToggleButton(UNAVAILABLE.getDisplayName(), true);

    protected final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    protected boolean tableMode = true;

    private final List<JToggleButton> filterToggles = List.of(showEnergyButton, showBallisticButton, showMissileButton,
            showArtilleryButton, showPhysicalButton, showCapitalButton, showAmmoButton, showOtherButton);

    protected final List<Integer> allColumns = List.of(COL_NAME, COL_DAMAGE, COL_DIVISOR, COL_SPECIAL, COL_HEAT,
            COL_MRANGE, COL_RANGE, COL_SHOTS, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE, COL_DPRODUCTION,
            COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_CREW, COL_BV, COL_TON, COL_CRIT, COL_REF);

    private static final String ADD_TEXT = "  << Add ";

    protected AbstractEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);

        masterEquipmentModel = new EquipmentTableModel(eSource.getEntity(), eSource.getTechManager());
        masterEquipmentTable = new EquipmentDatabaseTable(masterEquipmentModel);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
        masterEquipmentTable.getActionMap().put("add", addAction);

        equipmentSorter = new TableRowSorter<>(masterEquipmentModel);
        for (int col = 0; col < EquipmentTableModel.N_COL; col++) {
            TableColumn column = masterEquipmentTable.getColumnModel().getColumn(col);
            column.setPreferredWidth(masterEquipmentModel.getColumnWidth(col));
            column.setCellRenderer(masterEquipmentModel.getRenderer());
            equipmentSorter.setComparator(col, masterEquipmentModel.getSorter(col));
        }
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(EquipmentTableModel.COL_NAME, SortOrder.ASCENDING));
        equipmentSorter.setSortKeys(sortKeys);
        masterEquipmentTable.setRowSorter(equipmentSorter);

        masterEquipmentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    addSelectedEquipment(1);
                }
            }
        });

        masterEquipmentModel.setData(Collections.list(EquipmentType.getAllTypes()));
        addRowFilter();

        setLayout(new BorderLayout());
        add(buttonPanel(), BorderLayout.PAGE_START);
        add(new JScrollPane(masterEquipmentTable), BorderLayout.CENTER);
    }

    /**
     * Sets the visible table columns for this equipment view. The available columns are
     * from {@link EquipmentTableModel}. The method setColumnsVisible() may be used which
     * takes a List of Columns and a visibility value.
     */
    protected abstract void updateVisibleColumns();

    /**
     * Adds the given equipment to the entity a number of times equal to count.
     * When the "Add Multiple" button is not used, count will always be 1. The
     * "Add Multiple" button is only available when useAddMultiple() is overridden
     * to return true.
     * Implementing classes must provide a method that covers all entities that could
     * be coupled to their view unless all add buttons are hidden.
     */
    protected abstract void addEquipment(EquipmentType equip, int count);

    /**
     * Returns the filter toggles and buttons to be used in this Equipment Database View.
     * By default, this method returns the standard buttons suitable for the entity as defined in
     * EquipmentDatabaseCategory. It may be overridden, e.g. to hide all filter buttons by
     * returning an empty Set.
     */
    protected Set<EquipmentDatabaseCategory> getUsedButtons() {
        return Arrays.stream(EquipmentDatabaseCategory.values())
                .filter(category -> category.showFilterFor(getEntity()))
                .collect(Collectors.toSet());
    }

    /**
     * When this returns true, the Add Multiple button is used. This button together with a count
     * JSpinner allows adding multiples of equipment at once. By default, this method returns false.
     */
    protected boolean useAddMultipleButton() {
        return false;
    }

    /**
     * When this returns true, the "Add" button is shown. By default, this method returns true.
     * When it is overridden to return false, the "Add" button is hidden but adding equipment is
     * still possible by pressing Enter in the table.
     */
    protected boolean useAddButton() {
        return true;
    }

    /**
     * When this returns true, the Text Filter textfield is shown. By default, this method returns true.
     */
    protected boolean useTextFilter() {
        return true;
    }

    private JComponent buttonPanel() {
        Box result = Box.createVerticalBox();

        var showAllButton = new JButton("Show All");
        showAllButton.addActionListener(this::showAllEquipmentTypes);
        filterToggles.forEach(b -> b.addActionListener(this::filterToggleHandler));

        hideProtoButton.addItemListener(e -> equipmentSorter.sort());
        hideOneShotButton.addItemListener(e -> equipmentSorter.sort());
        hideTorpedoButton.addItemListener(e -> equipmentSorter.sort());
        hideUnavailButton.addItemListener(e -> equipmentSorter.sort());

        tableModeButton.addActionListener(this::switchTableMode);

        var typeFilterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        typeFilterPanel.add(new JLabel("Show: "));
        if (getUsedButtons().contains(ENERGY)) {
            typeFilterPanel.add(showEnergyButton);
        }
        if (getUsedButtons().contains(BALLISTIC)) {
            typeFilterPanel.add(showBallisticButton);
        }
        if (getUsedButtons().contains(MISSILE)) {
            typeFilterPanel.add(showMissileButton);
        }
        if (getUsedButtons().contains(ARTILLERY)) {
            typeFilterPanel.add(showArtilleryButton);
        }
        if (getUsedButtons().contains(PHYSICAL)) {
            typeFilterPanel.add(showPhysicalButton);
        }
        if (getUsedButtons().contains(CAPITAL)) {
            typeFilterPanel.add(showCapitalButton);
        }
        if (getUsedButtons().contains(AMMO)) {
            typeFilterPanel.add(showAmmoButton);
        }
        if (getUsedButtons().contains(OTHER)) {
            typeFilterPanel.add(showOtherButton);
        }
        typeFilterPanel.add(showAllButton);

        var specialFilterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        specialFilterPanel.add(new JLabel("Hide: "));
        if (getUsedButtons().contains(PROTOTYPE)) {
            specialFilterPanel.add(hideProtoButton);
        }
        if (getUsedButtons().contains(ONE_SHOT)) {
            specialFilterPanel.add(hideOneShotButton);
        }
        if (getUsedButtons().contains(TORPEDO)) {
            specialFilterPanel.add(hideTorpedoButton);
        }
        if (getUsedButtons().contains(AP)) {
            specialFilterPanel.add(hideAPButton);
        }
        if (getUsedButtons().contains(UNAVAILABLE)) {
            specialFilterPanel.add(hideUnavailButton);
        }

        var textFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (useAddButton()) {
            addButton.setMnemonic('A');
            textFilterPanel.add(addButton);
        }
        if (useAddMultipleButton()) {
            addMultipleButton.addActionListener(e -> addSelectedEquipment((int) addMultipleCount.getValue()));
            addMultipleCount.addChangeListener(e -> addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue()));
            addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue());
            textFilterPanel.add(new JLabel(" - "));
            textFilterPanel.add(addMultipleButton);
            textFilterPanel.add(addMultipleCount);
            textFilterPanel.add(new JLabel(" - "));
        }
        if (useTextFilter()) {
            txtFilter.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    equipmentSorter.sort();
                }
                public void insertUpdate(DocumentEvent e) {
                    equipmentSorter.sort();
                }
                public void removeUpdate(DocumentEvent e) {
                    equipmentSorter.sort();
                }
            });
            var cancelTextFilter = new JButton("X");
            cancelTextFilter.setForeground(GUIPreferences.getInstance().getWarningColor());
            cancelTextFilter.addActionListener(e -> txtFilter.setText(""));
            textFilterPanel.add(new JLabel("Text Filter: "));
            textFilterPanel.add(txtFilter);
            textFilterPanel.add(cancelTextFilter);
            textFilterPanel.add(tableModeButton);
        }

        result.add(typeFilterPanel);
        result.add(specialFilterPanel);
        if (useAddButton() || useAddMultipleButton() || useTextFilter()) {
            result.add(textFilterPanel);
        }

        return result;
    }

    private void addSelectedEquipment(int count) {
        assert count >= 1;
        int selectedRow = masterEquipmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int selected = masterEquipmentTable.convertRowIndexToModel(selectedRow);
            EquipmentType equip = masterEquipmentModel.getType(selected);
            addEquipment(equip, count);
            fireTableRefresh();
        }
    }

    public void setRefresh(RefreshListener newRefresh) {
        refresh = newRefresh;
    }

    public void refreshTable() {
        updateVisibleColumns();
        equipmentSorter.sort();
    }

    private void fireTableRefresh() {
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshSummary();
            refresh.refreshEquipment();
        }
    }

    /** Switches between two table column modes */
    protected void switchTableMode(ActionEvent e) {
        tableMode = !tableMode;
        updateVisibleColumns();
    }

    /** Handles the type filter toggles (energy, ballistic, etc.); Ctrl-clicking deselects all other toggles. */
    private void filterToggleHandler(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            filterToggles.forEach(button -> button.setSelected(e.getSource() == button));
        }
        equipmentSorter.sort();
    }

    private void showAllEquipmentTypes(ActionEvent e) {
        showEnergyButton.setSelected(true);
        showBallisticButton.setSelected(true);
        showMissileButton.setSelected(true);
        showArtilleryButton.setSelected(true);
        showAmmoButton.setSelected(true);
        showPhysicalButton.setSelected(true);
        showCapitalButton.setSelected(true);
        showOtherButton.setSelected(true);
        equipmentSorter.sort();
    }

    private final ListSelectionListener selectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentModel.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addButton.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        }
    };

    private void addRowFilter() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                return shouldShow(etype);
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
        equipmentSorter.sort();
    }

    protected boolean shouldShow(EquipmentType equipment) {
        return isEquipmentForEntity(equipment)
                && includedByFilters(equipment)
                && !hiddenEquipment(equipment)
                && (eSource.getTechManager().isLegal(equipment) || !hideUnavailButton.isSelected())
                && allowedByTextFilter(equipment);
    }

    private boolean allowedByTextFilter(EquipmentType equipment) {
        String lowerCaseSearchString = txtFilter.getText().toLowerCase();
        return txtFilter.getText().isBlank()
                || equipment.getName().toLowerCase().contains(lowerCaseSearchString)
                || EquipmentTableModel.getTechBaseAsString(equipment).toLowerCase().contains(lowerCaseSearchString)
                || equipment.getRulesRefs().toLowerCase().contains(lowerCaseSearchString);
    }

    private boolean isEquipmentForEntity(EquipmentType equipment) {
        if (equipment instanceof AmmoType) {
            // Only ammo for equipped weapons is listed, therefore no need to filter by unit type
            return true;
        }
        if (getEntity() instanceof Mech) {
            // FIXME: This is handled strangely in UnitUtil: MekEquipment does not include weapons
            return UnitUtil.isMechEquipment(equipment, (Mech) getEntity())
                    || UnitUtil.isMechWeapon(equipment, getEntity())
                    || UnitUtil.isPhysicalWeapon(equipment);
        } else if (getEntity() instanceof BattleArmor) {
            // FIXME: This is handled strangely in UnitUtil: BAAPWeapons are not BAEquipment
            return UnitUtil.isBAEquipment(equipment, getBattleArmor())
                    || UnitUtil.isBattleArmorAPWeapon(equipment);
        } else {
            return UnitUtil.isEntityEquipment(equipment, getEntity());
        }
    }

    private boolean includedByFilters(EquipmentType equipment) {
        return (showEnergyButton.isSelected() && ENERGY.passesFilter(equipment, getEntity()))
                || (showMissileButton.isSelected() && MISSILE.passesFilter(equipment, getEntity()))
                || (showBallisticButton.isSelected() && BALLISTIC.passesFilter(equipment, getEntity()))
                || (showArtilleryButton.isSelected() && ARTILLERY.passesFilter(equipment, getEntity()))
                || (showPhysicalButton.isSelected() && PHYSICAL.passesFilter(equipment, getEntity()))
                || (showCapitalButton.isSelected() && CAPITAL.passesFilter(equipment, getEntity()))
                || (showAmmoButton.isSelected() && AMMO.passesFilter(equipment, getEntity()))
                || (showOtherButton.isSelected() && OTHER.passesFilter(equipment, getEntity()));
    }

    private boolean hiddenEquipment(EquipmentType equipment) {
        return (hideProtoButton.isSelected() && PROTOTYPE.passesFilter(equipment, getEntity()))
                || (hideOneShotButton.isSelected() && ONE_SHOT.passesFilter(equipment, getEntity()))
                || (hideTorpedoButton.isSelected() && TORPEDO.passesFilter(equipment, getEntity()))
                || (hideAPButton.isSelected() && AP.passesFilter(equipment, getEntity()));
    }

    protected void setColumnsVisible(List<Integer> columns, boolean visible) {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        columns.forEach(c -> columnModel.setColumnVisible(columnModel.getColumnByModelIndex(c), visible));
    }

    private class AddAction extends AbstractAction {
        AddAction() {
            super(ADD_TEXT);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addSelectedEquipment(1);
        }
    }

    /** A specialized table used for the equipment database.*/
    private static class EquipmentDatabaseTable extends JTable {

        private EquipmentDatabaseTable(EquipmentTableModel dm) {
            super(dm, new XTableColumnModel());
            setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            setIntercellSpacing(new Dimension(0, 0));
            setShowGrid(false);
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setDoubleBuffered(true);
            createDefaultColumnsFromModel();
        }
    }

}

