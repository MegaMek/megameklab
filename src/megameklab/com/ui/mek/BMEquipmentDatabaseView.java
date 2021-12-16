/*
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
package megameklab.com.ui.mek;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.*;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.*;
import megameklab.com.util.*;

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
import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class BMEquipmentDatabaseView extends IView {

    private RefreshListener refresh;

    private final EquipmentTableModel masterEquipmentModel;
    private final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final EquipmentDatabaseTable masterEquipmentTable;

    private final AddAction addAction = new AddAction();
    private final JButton addButton = new JButton(addAction);
    private final JToggleButton showEnergyButton = new JToggleButton("Energy", true);
    private final JToggleButton showBallisticButton = new JToggleButton("Ballistic", true);
    private final JToggleButton showMissileButton = new JToggleButton("Missile", true);
    private final JToggleButton showArtilleryButton = new JToggleButton("Artillery", false);
    private final JToggleButton showPhysicalButton = new JToggleButton("Physical", false);
    private final JToggleButton showAmmoButton = new JToggleButton("Ammo");
    private final JToggleButton showOtherButton = new JToggleButton("Other");

    private final JToggleButton hideProtoButton = new JToggleButton("Prototype");
    private final JToggleButton hideOneShotButton = new JToggleButton("One Shot");
    private final JToggleButton hideTorpedoButton = new JToggleButton("Torpedoes");
    private final JToggleButton hideUnavailButton = new JToggleButton("Unavailable", true);

    private final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    private boolean tableMode = true;

    private final List<JToggleButton> filterToggles = List.of(showEnergyButton, showBallisticButton, showMissileButton,
            showArtilleryButton, showPhysicalButton, showAmmoButton, showOtherButton);

    private final List<Integer> allColumns = List.of(COL_NAME, COL_DAMAGE, COL_DIVISOR, COL_SPECIAL, COL_HEAT,
            COL_MRANGE, COL_RANGE, COL_SHOTS, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE, COL_DPRODUCTION,
            COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_CREW, COL_BV, COL_TON, COL_CRIT, COL_REF);

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    public BMEquipmentDatabaseView(EntitySource eSource) {
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
        updateVisibleColumns();

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
        filterToggles.forEach(typeFilterPanel::add);
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

    private void addEquipment(EquipmentType equip) {
        Mounted mount;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getMech())) {
                UnitUtil.updateTC(getMech(), equip);
            }
        } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
            UnitUtil.createSpreadMounts(getMech(), equip);
        } else {
            try {
                mount = new Mounted(getMech(), equip);
                getMech().addEquipment(mount, Entity.LOC_NONE, false);
                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
                }
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    // Preserving the method as it was before swapping out this DatabaseView (Dec21)
    // The equipmentList.addCrit(mount) call seems unnecessary, but I may be wrong
//        private void addEquipment(EquipmentType equip) {
//        boolean success = false;
//        Mounted mount = null;
//        boolean isMisc = equip instanceof MiscType;
//        if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
//            if (!UnitUtil.hasTargComp(getMech())) {
//                mount = UnitUtil.updateTC(getMech(), equip);
//                success = mount != null;
//            }
//        } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
//            mount = UnitUtil.createSpreadMounts(getMech(), equip);
//            success = mount != null;
//        } else {
//            try {
//                mount = new Mounted(getMech(), equip);
//                getMech().addEquipment(mount, Entity.LOC_NONE, false);
//                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
//                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
//                }
//                success = true;
//            } catch (LocationFullException lfe) {
//                // this can't happen, we add to Entity.LOC_NONE
//            }
//        }
//        if (success) {
//            equipmentList.addCrit(mount);
//        }
//    }

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

    /** Switches between the two table column modes (stats and fluff) */
    private void switchTableMode(ActionEvent e) {
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

    private boolean shouldShow(EquipmentType eType) {
        if (UnitUtil.isHeatSink(eType) || UnitUtil.isJumpJet(eType)) {
            return false;
        }
        if ((eType instanceof MiscType) && (eType.hasFlag(MiscType.F_TSM)
                || eType.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                || eType.hasFlag(MiscType.F_SCM)
                || (eType.hasFlag(MiscType.F_MASC) && !eType.hasSubType(MiscType.S_SUPERCHARGER)))) {
            return false;
        }
        if (eType instanceof MiscType && eType.hasFlag(MiscType.F_TRACKS)) {
            if (getMech() instanceof QuadVee) {
                return false;
            } else if (eType.hasSubType(MiscType.S_QUADVEE_WHEELS)) {
                return false;
            }
        }
        if ((shouldShowEnergy(eType) || shouldShowMissile(eType) || shouldShowBallistic(eType) || shouldShowAmmo(eType)
                || shouldShowArtillery(eType) || shouldShowPhysical(eType) || shouldShowOther(eType))
                && !shouldHidePrototype(eType) && !shouldHideOneShot(eType) && !shouldHideTorpedo(eType)) {

            if (!eSource.getTechManager().isLegal(eType) && hideUnavailButton.isSelected()) {
                return false;
            }
            if (txtFilter.getText().length() > 0) {
                String text = txtFilter.getText();
                return eType.getName().toLowerCase().contains(text.toLowerCase());
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean shouldShowEnergy(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showEnergyButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_ENERGY)
                || (wtype.hasFlag(WeaponType.F_PLASMA) && (wtype.getAmmoType() == AmmoType.T_PLASMA))));
    }

    private boolean shouldShowMissile(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showMissileButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_MISSILE)
                && (wtype.getAmmoType() != AmmoType.T_NA)) || (wtype.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR));
    }

    private boolean shouldShowBallistic(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showBallisticButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && (wtype.hasFlag(WeaponType.F_BALLISTIC) && (wtype.getAmmoType() != AmmoType.T_NA));
    }

    private boolean shouldShowArtillery(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showArtilleryButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech())
                && (wtype instanceof ArtilleryWeapon);
    }

    private boolean shouldShowPhysical(EquipmentType eType) {
        return showPhysicalButton.isSelected() && UnitUtil.isPhysicalWeapon(eType);
    }

    private boolean shouldShowAmmo(EquipmentType eType) {
        AmmoType atype = (eType instanceof AmmoType) ? (AmmoType) eType : null;
        return showAmmoButton.isSelected() && (atype != null) && UnitUtil.canUseAmmo(getMech(), atype, false);
    }

    private boolean shouldShowOther(EquipmentType eType) {
        return showOtherButton.isSelected() && UnitUtil.isMechEquipment(eType, getMech());
    }

    private boolean shouldHidePrototype(EquipmentType eType) {
        return hideProtoButton.isSelected() && (eType instanceof WeaponType) && eType.hasFlag(WeaponType.F_PROTOTYPE);
    }

    private boolean shouldHideOneShot(EquipmentType eType) {
        return hideOneShotButton.isSelected() && (eType instanceof WeaponType) && eType.hasFlag(WeaponType.F_ONESHOT);
    }

    private boolean shouldHideTorpedo(EquipmentType eType) {
        return hideTorpedoButton.isSelected() && (eType instanceof WeaponType)
                && (((WeaponType) eType).getAmmoType() == AmmoType.T_LRM_TORPEDO
                || ((WeaponType) eType).getAmmoType() == AmmoType.T_SRM_TORPEDO);
    }

    private void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }

    private void setColumnsVisible(List<Integer> columns, boolean visible) {
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
}
