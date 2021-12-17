package megameklab.com.ui.util;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.EquipmentType;
import megameklab.com.ui.EntitySource;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public abstract class AbstractEquipmentDatabaseView extends IView {

    public enum Toggles { ENERGY, MISSILE, BALLISTIC, ARTILLERY, PHYSICAL, AMMO, OTHER, PROTOTYPE,
    ONE_SHOT, TORPEDOES, UNAVAILABLE, TABLE_MODE }

    protected RefreshListener refresh;

    private final EquipmentTableModel masterEquipmentModel;
    protected  TableRowSorter<EquipmentTableModel> equipmentSorter;
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

    public AbstractEquipmentDatabaseView(EntitySource eSource) {
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
                    addSelectedEquipment(e);
                }
            }
        });

        masterEquipmentModel.setData(Collections.list(EquipmentType.getAllTypes()));
        addRowFilter();

        setLayout(new BorderLayout());
        add(buttonPanel(), BorderLayout.PAGE_START);
        add(new JScrollPane(masterEquipmentTable), BorderLayout.CENTER);
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
        if (getUsedButtons().contains(Toggles.ENERGY)) {
            typeFilterPanel.add(showEnergyButton);
        }
        if (getUsedButtons().contains(Toggles.BALLISTIC)) {
            typeFilterPanel.add(showBallisticButton);
        }
        if (getUsedButtons().contains(Toggles.MISSILE)) {
            typeFilterPanel.add(showMissileButton);
        }
        if (getUsedButtons().contains(Toggles.ARTILLERY)) {
            typeFilterPanel.add(showArtilleryButton);
        }
        if (getUsedButtons().contains(Toggles.AMMO)) {
            typeFilterPanel.add(showAmmoButton);
        }
        if (getUsedButtons().contains(Toggles.OTHER)) {
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

    private void addSelectedEquipment(AWTEvent e) {
        int selectedRow = masterEquipmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int selected = masterEquipmentTable.convertRowIndexToModel(selectedRow);
            EquipmentType equip = masterEquipmentModel.getType(selected);
            addEquipment(equip);
            fireTableRefresh();
        }
    }

    protected abstract void addEquipment(EquipmentType equip);

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
    }

    protected abstract boolean shouldShow(EquipmentType eType);

    protected abstract void updateVisibleColumns();

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
            addSelectedEquipment(e);
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

    protected abstract EnumSet<Toggles> getUsedButtons();

}

