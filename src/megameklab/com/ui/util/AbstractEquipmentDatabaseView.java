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
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.annotations.Nullable;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static megameklab.com.ui.util.EquipmentDatabaseCategory.*;

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
        setupRowFilter();

        setLayout(new BorderLayout());
        add(buttonPanel(), BorderLayout.PAGE_START);
        add(new JScrollPane(masterEquipmentTable), BorderLayout.CENTER);
    }

    /**
     * Sets the visible table columns for this equipment view. The available columns are
     * from {@link EquipmentTableModel} and are obtained by a call to the abstract method
     * getVisibleTableColumns().
     */
    private void updateVisibleColumns() {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        Collection<Integer> shownColumns = getVisibleTableColumns(tableMode);
        if ((shownColumns == null) || shownColumns.isEmpty()) {
            columnModel.setAllColumnsVisible();
            LogManager.getLogger().warn("Received empty or null list of table columns to show!");
            return;
        }
        columnModel.allTableColumns
                .forEach(c -> columnModel.setColumnVisible(c, shownColumns.contains(c.getModelIndex())));
    }

    /**
     * This method is called to find the table columns to display when the table is
     * first displayed and when the Table Column Mode button is pressed. The parameter
     * tableMode changes between true and false for each press of the Table Column Mode button.
     * Note that this method can return any dynamic result but is only called at start (when a
     * new unit is displayed or upon unit reset) and when the Table Column Mode button is pressed.
     * @param tableMode indicates which of two table column sets are to be shown. May be ignored,
     *                  especially if useSwitchTableColumns() is overridden to return false.
     * @return A Collection of columns from {@link EquipmentTableModel} that should be shown
     */
    protected abstract Collection<Integer> getVisibleTableColumns(boolean tableMode);

    /**
     * Adds the given equipment to the entity a number of times equal to count.
     * When the "Add Multiple" button is not used, count will always be 1. The
     * "Add Multiple" button is only available when useAddMultiple() is overridden
     * to return true.
     * Implementing classes must provide a method that covers all entity types that could
     * be coupled to their view.
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
     * This method may be overridden to control behaviour. By default, it returns true.<BR>
     * When it returns true, the "Add" button is shown and adding Equipment with the Enter key
     * is possible.<BR>
     * When this returns false, adding equipment to the unit is generally disabled and
     * none of the "Add" buttons is shown. In this case (only), the addEquipment() method
     * can be given an empty method body.
     */
    protected boolean allowAdd() {
        return true;
    }

    /**
     * When this returns true, the Text Filter textfield is shown. By default, this method returns true.
     */
    protected boolean useTextFilter() {
        return true;
    }

    /**
     * When this returns true, the "Switch Table Columns" button is shown. By default, this method returns true.
     */
    protected boolean useSwitchTableColumns() {
        return true;
    }

    private JComponent buttonPanel() {
        Box buttonPanel = Box.createVerticalBox();
        if (!Collections.disjoint(getUsedButtons(), EquipmentDatabaseCategory.getShowFilters())) {
            buttonPanel.add(getTypeFilterPanel());
        }
        if (!Collections.disjoint(getUsedButtons(), EquipmentDatabaseCategory.getHideFilters())) {
            buttonPanel.add(getHideFilterPanel());
        }
        if (allowAdd() || useTextFilter() || useSwitchTableColumns()) {
            buttonPanel.add(getMiscPanel());
        }
        return buttonPanel;
    }

    /**
     * Constructs and returns the Panel containing the "Show:" filter toggles.
     */
    private Component getTypeFilterPanel() {
        var typeFilterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        // The following listener deals with resizing problems of WrapLayout
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                typeFilterPanel.invalidate();
                super.componentResized(e);
            }
        });

        var showAllButton = new JButton("Show All");
        showAllButton.addActionListener(this::showAllEquipmentTypes);
        filterToggles.forEach(b -> b.addActionListener(this::filterToggleHandler));

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
        return typeFilterPanel;
    }

    /**
     * Constructs and returns the Panel containing "Hide:" filter toggles
     */
    private Component getHideFilterPanel() {
        var hideFilterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        hideFilterPanel.add(new JLabel("Hide: "));

        if (getUsedButtons().contains(PROTOTYPE)) {
            hideFilterPanel.add(hideProtoButton);
            hideProtoButton.addItemListener(e -> equipmentSorter.sort());
        }
        if (getUsedButtons().contains(ONE_SHOT)) {
            hideFilterPanel.add(hideOneShotButton);
            hideOneShotButton.addItemListener(e -> equipmentSorter.sort());
        }
        if (getUsedButtons().contains(TORPEDO)) {
            hideFilterPanel.add(hideTorpedoButton);
            hideTorpedoButton.addItemListener(e -> equipmentSorter.sort());
        }
        if (getUsedButtons().contains(AP)) {
            hideFilterPanel.add(hideAPButton);
            hideAPButton.addItemListener(e -> equipmentSorter.sort());
        }
        if (getUsedButtons().contains(UNAVAILABLE)) {
            hideFilterPanel.add(hideUnavailButton);
            hideUnavailButton.addItemListener(e -> equipmentSorter.sort());
        }
        return hideFilterPanel;
    }

    /**
     * Constructs and returns the Panel containing the Add buttons, the Text Filter and
     * the Table Mode button.
     */
    private Component getMiscPanel() {
        var miscPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        if (allowAdd()) {
            addButton.setMnemonic('A');
            miscPanel.add(addButton);
            if (useAddMultipleButton()) {
                addMultipleButton.addActionListener(e -> addSelectedEquipment((int) addMultipleCount.getValue()));
                addMultipleCount.addChangeListener(e -> addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue()));
                addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue());
                miscPanel.add(new JLabel(" - "));
                miscPanel.add(addMultipleButton);
                miscPanel.add(addMultipleCount);
                miscPanel.add(new JLabel(" - "));
            }
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
            miscPanel.add(new JLabel("Text Filter: "));
            miscPanel.add(txtFilter);
            miscPanel.add(cancelTextFilter);
        }
        if (useSwitchTableColumns()) {
            miscPanel.add(tableModeButton);
            tableModeButton.addActionListener(this::switchTableMode);
        }
        return miscPanel;
    }





    /**
     * Called from the Add and Add Multiple buttons and the {@link AddAction} (when pressing Enter)
     * to add equipment to the unit.
     */
    private void addSelectedEquipment(int count) {
        assert count >= 1;
        if (allowAdd() && masterEquipmentTable.getSelectedRowCount() >= 1) {
            int selected = masterEquipmentTable.convertRowIndexToModel(masterEquipmentTable.getSelectedRow());
            EquipmentType equip = masterEquipmentModel.getType(selected);
            if (canLegallyBeAddedToUnit(equip)) {
                addEquipment(equip, count);
                fireTableRefresh();
            }
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

    /** Called from the Table Column Mode button to switch between two table column modes. */
    private void switchTableMode(ActionEvent e) {
        tableMode = !tableMode;
        updateVisibleColumns();
    }

    /**
     * Called from the type filter toggles (energy, ballistic, etc.) to trigger a re-filter.
     * Ctrl-clicking deselects all other toggles.
     */
    private void filterToggleHandler(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            filterToggles.forEach(button -> button.setSelected(e.getSource() == button));
        }
        equipmentSorter.sort();
    }

    /**
     * Called from the Show All button to activate all type filter toggles.
     */
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

    /**
     * Disables/Enables the Add and Add Multiple buttons depending on availability of the
     * selected equipment.
     */

    private final ListSelectionListener selectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentModel.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addButton.setEnabled(canLegallyBeAddedToUnit(etype));
            addMultipleButton.setEnabled(canLegallyBeAddedToUnit(etype));
        }
    };

    /** Returns true when the given equipment is available to be added to the unit. */
    private boolean canLegallyBeAddedToUnit(@Nullable EquipmentType equipment) {
        return (equipment != null) && eSource.getTechManager().isLegal(equipment);
    }

    private void setupRowFilter() {
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

    /**
     * Returns true when the given equipment should show up in the database table. This method
     * checks if the equipment is available to the unit type at all and if the filter toggles
     * and text filter show or hide it.
     * This may be overridden to exclude or include equipment based on other evaluations.
     * @param equipment The equipment type to be shown or hidden
     * @return True when the equipment should be shown, false otherwise
     */
    protected boolean shouldShow(EquipmentType equipment) {
        return isEquipmentForEntity(equipment)
                && includedByFilters(equipment)
                && !hiddenEquipment(equipment)
                && (eSource.getTechManager().isLegal(equipment) || !hideUnavailButton.isSelected())
                && allowedByTextFilter(equipment);
    }

    /**
     * Returns true when the given equipment is allowed to be shown by an entry in the Text Filter.
     * This is always true when the Text Filter is empty. Otherwise the name, tech base and rules
     * ref table columns are checked against the text filter.
     */
    private boolean allowedByTextFilter(EquipmentType equipment) {
        String lowerCaseSearchString = txtFilter.getText().toLowerCase();
        return txtFilter.getText().isBlank()
                || equipment.getName().toLowerCase().contains(lowerCaseSearchString)
                || EquipmentTableModel.getTechBaseAsString(equipment).toLowerCase().contains(lowerCaseSearchString)
                || equipment.getRulesRefs().toLowerCase().contains(lowerCaseSearchString);
    }

    /**
     * Returns true when the given equipment is available at all to the unit type of the
     * current unit. For example, filters out Capital weapons for Meks.
     */
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

    /**
     * Returns true when the filter toggles allow the given equipment to be shown.
     */
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

    /**
     * Returns true when the Hide toggles hide the given equipment.
     */
    private boolean hiddenEquipment(EquipmentType equipment) {
        return (hideProtoButton.isSelected() && PROTOTYPE.passesFilter(equipment, getEntity()))
                || (hideOneShotButton.isSelected() && ONE_SHOT.passesFilter(equipment, getEntity()))
                || (hideTorpedoButton.isSelected() && TORPEDO.passesFilter(equipment, getEntity()))
                || (hideAPButton.isSelected() && AP.passesFilter(equipment, getEntity()));
    }

    /**
     * The Action used for the Add button and the Enter keypress on the table.
     */
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
            setIntercellSpacing(new Dimension(2, 0));
            setShowGrid(false);
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setDoubleBuffered(true);
            createDefaultColumnsFromModel();
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            super.tableChanged(e);
            updateRowHeights();
        }

        /** Sets all the row heights to the correct value. JTables don't do this automatically. */
        private void updateRowHeights() {
            if (getRowCount() >= 1) {
                Component comp = prepareRenderer(getCellRenderer(0, 0), 0, 0);
                int rowHeight = comp.getPreferredSize().height;
                for (int row = 0; row < getRowCount(); row++) {
                    setRowHeight(row, rowHeight);
                }
            }
        }
    }

}

