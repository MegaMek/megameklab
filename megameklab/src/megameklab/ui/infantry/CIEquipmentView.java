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
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.WrapLayout;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.models.XTableColumnModel;
import megamek.client.ui.util.UIUtil;
import megamek.common.SimpleTechLevel;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.ConvInfantry;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.InfantryUtil;

/**
 * @author (original) jtighe (torren@users.sourceforge.net)
 */
public class CIEquipmentView extends IView implements ActionListener {
    private RefreshListener refresh;

    /** The tech manager passed at construction; {@code eSource.getTechManager()} is not yet wired during construction. */
    private final ITechManager techManager;

    private final JButton showAllButton = new JButton("Show All");
    private final JButton addPrimaryButton = new JButton("Add Primary");
    private final JButton addSecondaryButton = new JButton("Add Secondary");
    private final JButton addDisposableButton = new JButton("Add Disposable");
    private final JButton removeDisposableButton = new JButton("Remove Disposable");

    private final JToggleButton showArchaicButton = new JToggleButton("Archaic");
    private final JToggleButton showPersonalButton = new JToggleButton("Personal", true);
    private final JToggleButton showSupportButton = new JToggleButton("Support");
    private final JToggleButton showDisposableButton = new JToggleButton("Disposable");
    private final JToggleButton hideUnavailableButton = new JToggleButton("Unavailable", true);
    private final List<JToggleButton> showToggles = new ArrayList<>(List.of(showArchaicButton, showPersonalButton,
          showSupportButton, showDisposableButton));

    private final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    private boolean tableMode = true;

    private final EquipmentTableModel masterEquipmentList;
    private final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final JTable masterEquipmentTable = new JTable();

    private final String ADD_PRIMARY_COMMAND = "ADD_PRIMARY";
    private final String ADD_SECONDARY_COMMAND = "ADD_SECONDARY";
    private final String ADD_DISPOSABLE_COMMAND = "ADD_DISPOSABLE";
    private final String REMOVE_DISPOSABLE_COMMAND = "REMOVE_DISPOSABLE";

    public CIEquipmentView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        this.techManager = techManager;

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
        TableColumn column;
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionListener selectionListener = evt -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType equipmentType = null;
            if (selected >= 0) {
                equipmentType = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addPrimaryButton.setEnabled((equipmentType != null)
                  && eSource.getTechManager().isLegal(equipmentType)
                  && !equipmentType.hasFlag(WeaponType.F_INF_SUPPORT));
            addSecondaryButton.setEnabled((equipmentType != null)
                  && eSource.getTechManager().isLegal(equipmentType)
                  && (TestInfantry.maxSecondaryWeapons(getInfantry()) > 0));
            addDisposableButton.setEnabled((equipmentType != null)
                  && eSource.getTechManager().isLegal(equipmentType)
                  && equipmentType.hasFlag(WeaponType.F_INF_DISPOSABLE)
                  && isDisposableTechLevel()
                  && !getInfantry().hasDisposableWeapon());
        };
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if (eq instanceof InfantryWeapon) {
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
        addSecondaryButton.setEnabled(TestInfantry.maxSecondaryWeapons(getInfantry()) > 0);
        addDisposableButton.setEnabled(isDisposableTechLevel() && !getInfantry().hasDisposableWeapon());
        removeDisposableButton.setEnabled(getInfantry().hasDisposableWeapon());
        addAllListeners();
    }

    private void removeAllListeners() {
        addPrimaryButton.removeActionListener(this);
        addSecondaryButton.removeActionListener(this);
        addDisposableButton.removeActionListener(this);
        removeDisposableButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addPrimaryButton.addActionListener(this);
        addSecondaryButton.addActionListener(this);
        addDisposableButton.addActionListener(this);
        removeDisposableButton.addActionListener(this);
        addPrimaryButton.setActionCommand(ADD_PRIMARY_COMMAND);
        addSecondaryButton.setActionCommand(ADD_SECONDARY_COMMAND);
        addDisposableButton.setActionCommand(ADD_DISPOSABLE_COMMAND);
        removeDisposableButton.setActionCommand(REMOVE_DISPOSABLE_COMMAND);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case ADD_PRIMARY_COMMAND, ADD_SECONDARY_COMMAND ->
                  addMainWeapon(evt.getActionCommand().equals(ADD_SECONDARY_COMMAND));
            case ADD_DISPOSABLE_COMMAND -> addDisposableWeapon();
            case REMOVE_DISPOSABLE_COMMAND -> getInfantry().equipDisposableWeapon(null);
            default -> {
                return;
            }
        }
        refresh.refreshAll();
    }

    /**
     * @return the currently selected weapon in the table, or null if none / filtered away
     */
    private EquipmentType selectedEquipment() {
        int view = masterEquipmentTable.getSelectedRow();
        if (view < 0) {
            return null;
        }
        return masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(view));
    }

    private void addMainWeapon(boolean isSecondary) {
        if (selectedEquipment() instanceof InfantryWeapon weapon) {
            InfantryUtil.replaceMainWeapon(getInfantry(), weapon, isSecondary);
            if (weapon.hasFlag(WeaponType.F_TAG)) {
                getInfantry().setSpecializations(getInfantry().getSpecializations() | ConvInfantry.TAG_TROOPS);
                getInfantry().setSecondaryWeaponsPerSquad(2);
            } else if (isSecondary && (getInfantry().getSecondaryWeaponsPerSquad() == 0)) {
                getInfantry().setSecondaryWeaponsPerSquad(1);
            }
        }
    }

    private void addDisposableWeapon() {
        if ((selectedEquipment() instanceof InfantryWeapon weapon)
              && weapon.hasFlag(WeaponType.F_INF_DISPOSABLE)
              && eSource.getTechManager().isLegal(weapon)) {
            getInfantry().equipDisposableWeapon(weapon);
        }
    }

    /**
     * @return true if the game's tech level is Advanced or higher, where the Advanced Disposable Weapon rule (TO:AR
     * p.106) is available
     */
    private boolean isDisposableTechLevel() {
        return techManager.getTechLevel().ordinal() >= SimpleTechLevel.ADVANCED.ordinal();
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
                if (!(etype instanceof InfantryWeapon weapon)) {
                    return false;
                }
                if (getInfantry().getSquadSize() < (getInfantry().getSecondaryWeaponsPerSquad() * weapon.getCrew())) {
                    return false;
                }
                if ((showArchaicButton.isSelected() && etype.hasFlag(WeaponType.F_INF_ARCHAIC))
                      || (showPersonalButton.isSelected() && !etype.hasFlag(WeaponType.F_INF_ARCHAIC)
                      && !etype.hasFlag(WeaponType.F_INF_SUPPORT))
                      || (showSupportButton.isSelected() && etype.hasFlag(WeaponType.F_INF_SUPPORT))
                      || (showDisposableButton.isSelected() && etype.hasFlag(WeaponType.F_INF_DISPOSABLE))
                ) {
                    if (eSource.getTechManager() != null
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
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE),
              false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING),
              !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE),
              !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
              !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON), !tableMode);
        columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT),
              !tableMode);
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
        JPanel userInfoPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        userInfoPanel.setOpaque(false);
        JButton gotItButton = new JButton("Got it!");
        gotItButton.setForeground(UIUtil.uiYellow());
        gotItButton.addActionListener(e -> {
            userInfoPanel.setVisible(false);
            CConfig.setParam(CConfig.NAG_EQUIPMENT_CTRL_CLICK, Boolean.toString(false));
            CConfig.saveConfig();
        });
        var userInfoText = new JLabel("Note: Ctrl-Click a filter to add it to the selected filters.");
        userInfoText.setForeground(UIUtil.uiYellow());
        userInfoPanel.add(userInfoText);
        userInfoPanel.add(Box.createHorizontalStrut(15));
        userInfoPanel.add(gotItButton);
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

        buttonPanel.add(new JLabel("Show: "));
        showToggles.forEach(button -> {
            button.addActionListener(this::toggleEquipment);
            buttonPanel.add(button);
        });
        showAllButton.addActionListener(e -> showAllEquipment());
        buttonPanel.add(showAllButton);

        var showTogglesPanel = Box.createVerticalBox();
        if (CConfig.getBooleanParam(CConfig.NAG_EQUIPMENT_CTRL_CLICK)) {
            showTogglesPanel.add(getUserInfoPanel());
        }
        showTogglesPanel.add(buttonPanel);
        showTogglesPanel.setBackground(UIManager.getColor("Table.background"));
        showTogglesPanel.setOpaque(true);
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

        buttonPanel.add(new JLabel("Hide: "));
        hideUnavailableButton.addActionListener(e -> filterEquipment());
        buttonPanel.add(hideUnavailableButton);

        var hideTogglesPanel = Box.createHorizontalBox();
        hideTogglesPanel.add(buttonPanel);
        hideTogglesPanel.setBackground(UIManager.getColor("Table.background"));
        hideTogglesPanel.setOpaque(true);
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
        buttonPanel.add(addPrimaryButton);
        buttonPanel.add(addSecondaryButton);
        buttonPanel.add(addDisposableButton);
        buttonPanel.add(removeDisposableButton);

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
