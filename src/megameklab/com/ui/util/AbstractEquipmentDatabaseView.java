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

/**
 * A base class for creating an equipment database table that shows all equipment available to the
 * unit and by default includes filters such as an "Energy Weapon" toggle.
 * In addition to the abstract methods, implementing classes may override
 * getUsedButtons() to control the shown filter buttons,
 * shouldShow() to control the equipment filtering when the standard filters are not used,
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
    protected final JToggleButton showEnergyButton = new JToggleButton("Energy", true);
    protected final JToggleButton showBallisticButton = new JToggleButton("Ballistic", true);
    protected final JToggleButton showMissileButton = new JToggleButton("Missile", true);
    protected final JToggleButton showArtilleryButton = new JToggleButton("Artillery", false);
    protected final JToggleButton showPhysicalButton = new JToggleButton("Physical", false);
    protected final JToggleButton showAmmoButton = new JToggleButton("Ammo");
    protected final JToggleButton showOtherButton = new JToggleButton("Other");

    protected final JToggleButton hideProtoButton = new JToggleButton("Prototype");
    protected final JToggleButton hideOneShotButton = new JToggleButton("One Shot");
    protected final JToggleButton hideTorpedoButton = new JToggleButton("Torpedoes");
    protected final JToggleButton hideUnavailButton = new JToggleButton("Unavailable", true);

    protected final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    protected boolean tableMode = true;

    private final List<JToggleButton> filterToggles = List.of(showEnergyButton, showBallisticButton, showMissileButton,
            showArtilleryButton, showPhysicalButton, showAmmoButton, showOtherButton);

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
                    addSelectedEquipment();
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
     * Adds the given equipment to the entity. Implementing classes must provide a method that
     * covers all entities that could be coupled to their view unless the add button is hidden.
     */
    protected abstract void addEquipment(EquipmentType equip);

    /**
     * Returns the filter toggles and buttons to be used in this Equipment Database View.
     * By default, this method returns the standard buttons suitable for the entity as defined in
     * EquipmentDatabaseCategory. It may be overridden, e.g. to hide all filter buttons by
     * returning an empty EnumSet.
     */
    protected Set<EquipmentDatabaseCategory> getUsedButtons() {
        return Arrays.stream(EquipmentDatabaseCategory.values())
                .filter(category -> category.show(getEntity()))
                .collect(Collectors.toSet());
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

        addButton.setMnemonic('A');

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
        if (getUsedButtons().contains(AMMO)) {
            typeFilterPanel.add(showAmmoButton);
        }
        if (getUsedButtons().contains(OTHER)) {
            typeFilterPanel.add(showOtherButton);
        }
        typeFilterPanel.add(showAllButton);

        var specialFilterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        specialFilterPanel.add(new JLabel("Hide: "));
        specialFilterPanel.add(hideProtoButton);
        specialFilterPanel.add(hideOneShotButton);
        specialFilterPanel.add(hideTorpedoButton);
        specialFilterPanel.add(hideUnavailButton);

        var textFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textFilterPanel.add(addButton);
        textFilterPanel.add(new JLabel("Text Filter: "));
        textFilterPanel.add(txtFilter);
        textFilterPanel.add(cancelTextFilter);
        textFilterPanel.add(tableModeButton);

        result.add(typeFilterPanel);
        result.add(specialFilterPanel);
        result.add(textFilterPanel);

        return result;
    }

    private void addSelectedEquipment() {
        int selectedRow = masterEquipmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int selected = masterEquipmentTable.convertRowIndexToModel(selectedRow);
            EquipmentType equip = masterEquipmentModel.getType(selected);
            addEquipment(equip);
            fireTableRefresh();
        }
    }

    public void setRefresh(RefreshListener newRefresh) {
        refresh = newRefresh;
    }

    public void refreshTable() {
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
        return txtFilter.getText().isBlank()
                || equipment.getName().toLowerCase().contains(txtFilter.getText().toLowerCase());
    }

    private boolean isEquipmentForEntity(EquipmentType equipment) {
        if (equipment instanceof AmmoType) {
            // Only ammo for equipped weapons is listed, therefore no need to filter by unit type
            return true;
        }
        if (getEntity() instanceof Mech) {
            // FIXME: This is handled differently in UnitUtil: MekEquipment does not include weapons
            return UnitUtil.isMechEquipment(equipment, (Mech) getEntity())
                    || UnitUtil.isMechWeapon(equipment, getEntity())
                    || UnitUtil.isPhysicalWeapon(equipment);
        } else {
            return UnitUtil.isEntityEquipment(equipment, getEntity());
        }
    }

    private boolean includedByFilters(EquipmentType equipment) {
        return (showEnergyButton.isSelected() && EquipmentDatabaseCategory.ENERGY.filter(equipment, getEntity()))
                || (showMissileButton.isSelected() && EquipmentDatabaseCategory.MISSILE.filter(equipment, getEntity()))
                || (showBallisticButton.isSelected() && EquipmentDatabaseCategory.BALLISTIC.filter(equipment, getEntity()))
                || (showArtilleryButton.isSelected() && EquipmentDatabaseCategory.ARTILLERY.filter(equipment, getEntity()))
                || (showPhysicalButton.isSelected() && EquipmentDatabaseCategory.PHYSICAL.filter(equipment, getEntity()))
                || (showAmmoButton.isSelected() && EquipmentDatabaseCategory.AMMO.filter(equipment, getEntity()))
                || (showOtherButton.isSelected() && EquipmentDatabaseCategory.OTHER.filter(equipment, getEntity()));
    }

    private boolean hiddenEquipment(EquipmentType equipment) {
        return ((hideProtoButton.isSelected()) && EquipmentDatabaseCategory.PROTOTYPE.filter(equipment, getEntity()))
                || ((hideOneShotButton.isSelected()) && EquipmentDatabaseCategory.ONE_SHOT.filter(equipment, getEntity()))
                || ((hideTorpedoButton.isSelected()) && EquipmentDatabaseCategory.TORPEDO.filter(equipment, getEntity()));
    }

    protected void setColumnsVisible(List<Integer> columns, boolean visible) {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        columns.forEach(c -> columnModel.setColumnVisible(columnModel.getColumnByModelIndex(c), visible));
    }

    private class AddAction extends AbstractAction {

        AddAction() {
            super("  << Add  ");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addSelectedEquipment();
        }
    }

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

