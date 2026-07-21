/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import static megamek.common.equipment.AmmoType.AmmoTypeEnum.CRUISE_MISSILE;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.GAUSS_HEAVY;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.HAG;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.IGAUSS_HEAVY;
import static megamek.common.equipment.AmmoType.AmmoTypeEnum.MAGSHOT;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.WrapLayout;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.models.XTableColumnModel;
import megamek.client.ui.util.UIUtil;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponType;
import megamek.common.interfaces.ITechManager;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.autoCannons.ACWeapon;
import megamek.common.weapons.autoCannons.LBXACWeapon;
import megamek.common.weapons.autoCannons.RifleWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.gaussRifles.GaussWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.InfantryUtil;

/**
 * Shows options for infantry field guns/field artillery
 *
 * @author Neoancient
 */
public class CIFieldGunTableView extends IView implements ActionListener {
    private RefreshListener refresh;

    private final JButton showAllButton = new JButton("Show All");
    private final JButton addFieldGunButton = new JButton("Add Field Gun");
    private final JButton removeFieldGunButton = new JButton("Remove Field Gun");

    private final JToggleButton showFieldGunButton = new JToggleButton("Field Gun", true);
    private final JToggleButton showArtilleryButton = new JToggleButton("Artillery");
    private final JToggleButton showArtilleryCannonButton = new JToggleButton("Artillery Cannon");
    private final JToggleButton hideUnavailableButton = new JToggleButton("Unavailable", true);
    private final List<JToggleButton> showToggles = new ArrayList<>(List.of(showFieldGunButton, showArtilleryButton,
          showArtilleryCannonButton));

    private final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    private boolean tableMode = true;

    private final EquipmentTableModel masterEquipmentList;
    private final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final JTable masterEquipmentTable = new JTable();

    public CIFieldGunTableView(EntitySource eSource, ITechManager techManager) {
        super(eSource);

        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), techManager);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        equipmentSorter = new TableRowSorter<>(masterEquipmentList);
        for (int col = 0; col < EquipmentTableModel.N_COL; col++) {
            equipmentSorter.setComparator(col, masterEquipmentList.getSorter(col));
        }
        masterEquipmentTable.setRowSorter(equipmentSorter);
        ArrayList<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey(EquipmentTableModel.COL_NAME, SortOrder.ASCENDING));
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
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addFieldGunButton.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        });
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int view = masterEquipmentTable.getSelectedRow();
            addFieldGunButton.setEnabled(view >= 0);
        });

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if (!(eq instanceof WeaponType weaponType) || (weaponType.isCapital())) {
                continue;
            }

            if ((eq instanceof ACWeapon)
                  || (eq instanceof UACWeapon)
                  || (eq instanceof RifleWeapon)
                  || (eq instanceof ArtilleryCannonWeapon)
                  || (eq instanceof LBXACWeapon)) {
                allTypes.add(eq);
            }

            var ammoType = weaponType.getAmmoType();
            if ((eq instanceof GaussWeapon) && !ammoType.isAnyOf(GAUSS_HEAVY, IGAUSS_HEAVY, MAGSHOT, HAG)) {
                allTypes.add(eq);
            }

            if ((eq instanceof ArtilleryWeapon)
                  && !eq.hasFlag(WeaponType.F_BA_WEAPON)
                  && !eq.hasFlag(WeaponType.F_BOMB_WEAPON)
                  && (ammoType != CRUISE_MISSILE)) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);
        setEquipmentView();

        setLayout(new BorderLayout());
        add(getControlPanel(), BorderLayout.PAGE_START);
        add(masterEquipmentScroll, BorderLayout.CENTER);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        filterEquipment();
        removeFieldGunButton.setEnabled(getInfantry().hasFieldWeapon());
        addAllListeners();
    }

    private void removeAllListeners() {
        addFieldGunButton.removeActionListener(this);
        removeFieldGunButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addFieldGunButton.addActionListener(this);
        removeFieldGunButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFieldGunButton) {
            int view = masterEquipmentTable.getSelectedRow();
            if (view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equipmentType = masterEquipmentList.getType(selected);
            int maximumFieldGuns;
            if (TestInfantry.isFieldArtilleryType(equipmentType)) {
                maximumFieldGuns = 1;
            } else {
                maximumFieldGuns = getInfantry().getOriginalTrooperCount()
                      / TestInfantry.fieldGunCrewRequirement(equipmentType, getInfantry());
                // If 0 (too few troopers to operate), still add 1 field gun; will show as invalid
                maximumFieldGuns = Math.max(maximumFieldGuns, 1);
            }
            InfantryUtil.replaceFieldGun(getInfantry(), (WeaponType) equipmentType, maximumFieldGuns);
            refresh.refreshAll();

        } else if (e.getSource() == removeFieldGunButton) {
            InfantryUtil.replaceFieldGun(getInfantry(), null, 0);
            refresh.refreshAll();
        }
    }

    private void toggleEquipment(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == 0) {
            showToggles.forEach(button -> button.setSelected(e.getSource() == button));
        }
        filterEquipment();
    }

    private void showAllEquipment() {
        showToggles.forEach(button -> button.setSelected(true));
        filterEquipment();
    }

    private void filterEquipment() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if ((showFieldGunButton.isSelected() && !(etype instanceof ArtilleryWeapon)
                      && !(etype instanceof ArtilleryCannonWeapon))
                      || (showArtilleryButton.isSelected() && etype instanceof ArtilleryWeapon)
                      || (showArtilleryCannonButton.isSelected() && etype instanceof ArtilleryCannonWeapon)
                ) {
                    if (null != eSource.getTechManager()
                          && !eSource.getTechManager().isLegal(etype)
                          && hideUnavailableButton.isSelected()) {
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

    private void switchTableMode() {
        tableMode = !tableMode;
        setEquipmentView();
    }

    private void setEquipmentView() {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
              !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
              !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), !tableMode);
    }

    /** Creates the control panel with the filters and buttons. */
    private JComponent getControlPanel() {
        Box controlPanel = Box.createVerticalBox();
        controlPanel.add(getShowTogglesPanel());
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(getHideTogglesPanel());
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(getAddRemoveButtonsPanel());
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(getTextFilterAndTableModeButtonPanel());
        controlPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        return controlPanel;
    }

    /**
     * Creates a small info panel. Has a dismiss button that will prevent it from being shown again.
     */
    private JComponent getUserInfoPanel() {
        Box userInfoPanel = Box.createHorizontalBox();
        userInfoPanel.setOpaque(false);
        JButton gotItButton = new JButton("Got it!");
        gotItButton.setForeground(UIUtil.uiYellow());
        gotItButton.addActionListener(e -> {
            userInfoPanel.setVisible(false);
            CConfig.setParam(CConfig.NAG_EQUIPMENT_CTRL_CLICK, Boolean.toString(false));
            CConfig.saveConfig();
        });
        JLabel userInfoText = new JLabel("<html>Note: Ctrl-Click a filter to add it to the selected filters.</html>") {
            @Override
            public Dimension getMaximumSize()
            {
                return getPreferredSize();
            }
        };
        userInfoText.setForeground(UIUtil.uiYellow());
        userInfoPanel.add(userInfoText);
        userInfoPanel.add(Box.createHorizontalStrut(5));
        userInfoPanel.add(gotItButton);
        userInfoPanel.add(Box.createHorizontalGlue());
        userInfoPanel.setBorder(new EmptyBorder(4, 5, 0, 0));
        return userInfoPanel;
    }

    /**
     * Constructs and returns the Panel containing the "Show:" toggles.
     */
    private Component getShowTogglesPanel() {
        var buttonPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        // The following listener deals with resizing problems of WrapLayout
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                buttonPanel.invalidate();
                super.componentResized(e);
            }
        });

        showToggles.forEach(button -> {
            button.addActionListener(this::toggleEquipment);
            buttonPanel.add(button);
        });
        showAllButton.addActionListener(e -> showAllEquipment());
        buttonPanel.add(showAllButton);

        var buttonAndInfoPanel = Box.createVerticalBox();
        if (CConfig.getBooleanParam(CConfig.NAG_EQUIPMENT_CTRL_CLICK)) {
            buttonAndInfoPanel.add(getUserInfoPanel());
        }
        buttonAndInfoPanel.add(buttonPanel);

        var showTogglesPanel = Box.createHorizontalBox();
        showTogglesPanel.add(new JLabel("Show: "));
        showTogglesPanel.add(buttonAndInfoPanel);
        showTogglesPanel.setBackground(UIManager.getColor("Table.background"));
        showTogglesPanel.setOpaque(true);
        showTogglesPanel.setBorder(new EmptyBorder(0, 4, 0, 4));
        return showTogglesPanel;
    }

    /**
     * Constructs and returns the Panel containing the "Hide:" toggles.
     */
    private Component getHideTogglesPanel() {
        var buttonPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        // The following listener deals with resizing problems of WrapLayout
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                buttonPanel.invalidate();
                super.componentResized(e);
            }
        });

        hideUnavailableButton.addActionListener(e -> filterEquipment());
        buttonPanel.add(hideUnavailableButton);

        var hideTogglesPanel = Box.createHorizontalBox();
        hideTogglesPanel.add(new JLabel("Hide: "));
        hideTogglesPanel.add(buttonPanel);
        hideTogglesPanel.setBackground(UIManager.getColor("Table.background"));
        hideTogglesPanel.setOpaque(true);
        hideTogglesPanel.setBorder(new EmptyBorder(0, 4, 0, 4));
        return hideTogglesPanel;
    }

    /**
     * Constructs and returns the Panel containing the Add and Remove buttons.
     */
    private Component getAddRemoveButtonsPanel() {
        var buttonPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        // The following listener deals with resizing problems of WrapLayout
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                buttonPanel.invalidate();
                super.componentResized(e);
            }
        });
        buttonPanel.add(addFieldGunButton);
        buttonPanel.add(removeFieldGunButton);

        var addRemoveButtonsPanel = Box.createHorizontalBox();
        addRemoveButtonsPanel.add(buttonPanel);
        addRemoveButtonsPanel.setBackground(UIManager.getColor("Table.background"));
        addRemoveButtonsPanel.setOpaque(true);
        return addRemoveButtonsPanel;
    }

    /**
     * Constructs and returns the Panel containing the Text Filter and the Table Mode button.
     */
    private Component getTextFilterAndTableModeButtonPanel() {
        var textAndButtonPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        textAndButtonPanel.setOpaque(false);
        // The following listener deals with resizing problems of WrapLayout
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                textAndButtonPanel.invalidate();
                super.componentResized(e);
            }
        });
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent evt) {
                equipmentSorter.sort();
            }

            @Override
            public void insertUpdate(DocumentEvent evt) {
                equipmentSorter.sort();
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                equipmentSorter.sort();
            }
        });
        textAndButtonPanel.add(new JLabel("Text Filter: "));
        textAndButtonPanel.add(txtFilter);
        var cancelTextFilter = new JButton("X");
        cancelTextFilter.setForeground(GUIPreferences.getInstance().getWarningColor());
        cancelTextFilter.addActionListener(e -> txtFilter.setText(""));
        textAndButtonPanel.add(cancelTextFilter);
        textAndButtonPanel.add(Box.createHorizontalStrut(15));
        textAndButtonPanel.add(tableModeButton);
        tableModeButton.addActionListener(e -> switchTableMode());

        var textFilterAndTableModeButtonPanel = Box.createHorizontalBox();
        textFilterAndTableModeButtonPanel.add(textAndButtonPanel);
        textFilterAndTableModeButtonPanel.setBackground(UIManager.getColor("Table.background"));
        textFilterAndTableModeButtonPanel.setOpaque(true);
        return textFilterAndTableModeButtonPanel;
    }
}
