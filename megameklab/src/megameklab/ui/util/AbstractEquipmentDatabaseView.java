/*
 * Copyright (C) 2021-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import static megameklab.ui.util.EquipmentDatabaseCategory.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.WrapLayout;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.models.XTableColumnModel;
import megamek.client.ui.util.UIUtil;
import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.util.BattleArmorUtil;
import megameklab.util.CConfig;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

/**
 * A base class for creating an equipment database table that shows all equipment available to the unit and by default
 * includes filters such as an "Energy Weapon" toggle. In addition to the abstract methods, implementing classes may
 * override getUsedButtons() to control the shown filter buttons, shouldShow() to control the equipment filtering when
 * the standard filters are not used, and getVisibleTableColumns() to control the shown columns.
 */
public abstract class AbstractEquipmentDatabaseView extends IView {
    private static final MMLogger logger = MMLogger.create(AbstractEquipmentDatabaseView.class);

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
    protected final JToggleButton showIndustrialButton = new JToggleButton(INDUSTRIAL.getDisplayName());
    protected final JToggleButton showCapitalButton = new JToggleButton(CAPITAL.getDisplayName());
    protected final JToggleButton showAmmoButton = new JToggleButton(AMMO.getDisplayName());
    protected final JToggleButton showOtherButton = new JToggleButton(OTHER.getDisplayName());

    protected final JToggleButton hideProtoButton = new JToggleButton(PROTOTYPE.getDisplayName());
    protected final JToggleButton hideOneShotButton = new JToggleButton(ONE_SHOT.getDisplayName());
    protected final JToggleButton hideTorpedoButton = new JToggleButton(TORPEDO.getDisplayName());
    protected final JToggleButton hideAPButton = new JToggleButton(AP.getDisplayName());
    protected final JToggleButton hideUnusableAmmoButton = new JToggleButton(UNUSABLE_AMMO.getDisplayName(), true);
    protected final JToggleButton hideUnavailableButton = new JToggleButton(UNAVAILABLE.getDisplayName(), true);

    protected final JTextField txtFilter = new JTextField("", 15);
    private final JButton tableModeButton = new JButton("Switch Table Columns");
    private boolean tableMode = true;

    private final Map<JToggleButton, EquipmentDatabaseCategory> filterToggles = Map.of(showEnergyButton, ENERGY,
          showBallisticButton, BALLISTIC, showMissileButton, MISSILE, showArtilleryButton, ARTILLERY,
          showPhysicalButton, PHYSICAL, showCapitalButton, CAPITAL, showAmmoButton, AMMO, showOtherButton, OTHER,
          showIndustrialButton, INDUSTRIAL);

    private final Map<JToggleButton, EquipmentDatabaseCategory> hideToggles = Map.of(hideProtoButton, PROTOTYPE,
          hideOneShotButton, ONE_SHOT, hideTorpedoButton, TORPEDO, hideAPButton, AP,
          hideUnusableAmmoButton, UNUSABLE_AMMO, hideUnavailableButton, UNAVAILABLE);

    private static final String ADD_TEXT = "  << Add ";

    protected AbstractEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
        masterEquipmentModel = new EquipmentTableModel(eSource.getEntity(), eSource.getTechManager());
        masterEquipmentTable = new EquipmentDatabaseTable(masterEquipmentModel);
        masterEquipmentTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
              .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
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
        masterEquipmentModel.setData(Collections.list(EquipmentType.getAllTypes()));
        setupRowFilter();

        setLayout(new BorderLayout());
        add(setupControlPanel(), BorderLayout.PAGE_START);
        add(new JScrollPane(masterEquipmentTable), BorderLayout.CENTER);
        updateFilterToggleVisibility();
        addListeners();
    }

    private void addListeners() {
        // Enable the Add and Add Multiple buttons depending on availability of the
        // selected equipment
        masterEquipmentTable.getSelectionModel().addListSelectionListener(e -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentModel.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addButton.setEnabled(canLegallyBeAddedToUnit(etype));
            addMultipleButton.setEnabled(canLegallyBeAddedToUnit(etype));
        });
        // Double-clicking adds the clicked equipment
        masterEquipmentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    addSelectedEquipment(1);
                }
            }
        });
    }

    /**
     * Sets the visible table columns for this equipment view. The available columns are from
     * {@link EquipmentTableModel} and are obtained by a call to the abstract method getVisibleTableColumns().
     */
    private void updateVisibleColumns() {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        Collection<Integer> shownColumns = getVisibleTableColumns(tableMode);
        if ((shownColumns == null) || shownColumns.isEmpty()) {
            columnModel.setAllColumnsVisible();
            logger.warn("Received empty or null list of table columns to show!");
            return;
        }
        columnModel.getAllTableColumns()
              .forEach(c -> columnModel.setColumnVisible(c, shownColumns.contains(c.getModelIndex())));
    }

    /**
     * This method is called to find the table columns to display when the table is first displayed and when the Table
     * Column Mode button is pressed. The parameter tableMode changes between true and false for each press of the Table
     * Column Mode button. Note that this method can return any dynamic result but is only called at start (when a new
     * unit is displayed or upon unit reset) and when the Table Column Mode button is pressed.
     *
     * @param tableMode indicates which of two table column sets are to be shown. May be ignored, especially if
     *                  useSwitchTableColumns() is overridden to return false.
     *
     * @return A Collection of columns from {@link EquipmentTableModel} that should be shown
     */
    protected abstract Collection<Integer> getVisibleTableColumns(boolean tableMode);

    /**
     * Adds the given equipment to the entity a number of times equal to count. When the "Add Multiple" button is not
     * used, count will always be 1. The "Add Multiple" button is only available when useAddMultiple() is overridden to
     * return true. Implementing classes must provide a method that covers all entity types that could be coupled to
     * their view.
     */
    protected abstract void addEquipment(EquipmentType equip, int count);

    /**
     * @return the filter toggles and buttons to be used in this Equipment Database View. By default, this method
     *       returns the standard buttons suitable for the entity as defined in EquipmentDatabaseCategory. It may be
     *       overridden, e.g. to hide all filter buttons by returning an empty Set. When doing this, shouldShow() should
     *       be overridden to prevent the equipment being filtered by the state of the unavailable buttons.
     */
    protected Set<EquipmentDatabaseCategory> getUsedButtons() {
        return Arrays.stream(EquipmentDatabaseCategory.values())
              .filter(category -> category.showFilterFor(getEntity()))
              .collect(Collectors.toSet());
    }

    /**
     * When this returns true, the Add Multiple button is used. This button together with a count JSpinner allows adding
     * multiples of equipment at once. By default, this method returns false.
     */
    protected boolean useAddMultipleButton() {
        return false;
    }

    /**
     * This method may be overridden to disallow adding equipment from this database view. By default, it returns true.
     * When it returns true, the "Add" button is shown and adding Equipment with the Enter key is possible. When this
     * returns false, adding equipment to the unit is generally disabled and none of the "Add" buttons is shown. In this
     * case (only), the addEquipment() method can be given an empty method body.
     */
    protected boolean allowAdd() {
        return true;
    }

    /**
     * When this returns true, the Text Filter TextField is shown. By default, this method returns true.
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

    /** Creates the control panel with the filters and buttons. */
    private JComponent setupControlPanel() {
        Box buttonPanel = Box.createVerticalBox();

        if (!Collections.disjoint(getUsedButtons(), EquipmentDatabaseCategory.getShowFilters())) {
            buttonPanel.add(setupTypeFilterPanel());
            buttonPanel.add(Box.createVerticalStrut(4));
        }
        if (!Collections.disjoint(getUsedButtons(), EquipmentDatabaseCategory.getHideFilters())) {
            buttonPanel.add(setupHideFilterPanel());
            buttonPanel.add(Box.createVerticalStrut(4));
        }
        if (allowAdd() || useTextFilter() || useSwitchTableColumns()) {
            buttonPanel.add(setupMiscPanel());
        }
        buttonPanel.setBorder(new EmptyBorder(5, 2, 5, 2));
        return buttonPanel;
    }

    /**
     * Creates a small info panel ("Ctrl-Click selects only that equipment"). Has a dismiss button that will prevent it
     * from being shown again.
     */
    private JComponent setupUserInfoPanel() {
        Box userInfoPanel = Box.createHorizontalBox();
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
        userInfoPanel.add(Box.createHorizontalGlue());
        userInfoPanel.setOpaque(true);
        userInfoPanel.setBorder(new EmptyBorder(5, 5, 1, 5));
        return userInfoPanel;
    }

    /**
     * Constructs and returns the Panel containing the "Show:" filter toggles.
     */
    private Component setupTypeFilterPanel() {
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

        var showAllButton = new JButton("Show All");
        showAllButton.addActionListener(e -> showAllEquipmentTypes());
        filterToggles.keySet().forEach(button -> button.addActionListener(this::filterToggleHandler));
        buttonPanel.add(showEnergyButton);
        buttonPanel.add(showBallisticButton);
        buttonPanel.add(showMissileButton);
        if (!(getEntity() instanceof Jumpship)) {
            // TO:AUE p.96 and p.217: JS/WS/SS cannot mount artillery at all
            buttonPanel.add(showArtilleryButton);
        }
        buttonPanel.add(showPhysicalButton);
        buttonPanel.add(showIndustrialButton);
        buttonPanel.add(showCapitalButton);
        buttonPanel.add(showAmmoButton);
        buttonPanel.add(showOtherButton);
        buttonPanel.add(showAllButton);
        updateFilterToggleVisibility();

        var buttonAndInfoPanel = Box.createVerticalBox();
        if (CConfig.getBooleanParam(CConfig.NAG_EQUIPMENT_CTRL_CLICK)) {
            buttonAndInfoPanel.add(setupUserInfoPanel());
        }
        buttonAndInfoPanel.add(buttonPanel);

        var typeFilterPanel = Box.createHorizontalBox();
        typeFilterPanel.add(new JLabel("Show: "));
        typeFilterPanel.add(buttonAndInfoPanel);
        typeFilterPanel.setBackground(UIUtil.alternateTableBGColor());
        typeFilterPanel.setOpaque(true);
        typeFilterPanel.setBorder(new EmptyBorder(0, 8, 0, 8));
        return typeFilterPanel;
    }

    private void updateFilterToggleVisibility() {
        // Show/hide toggles as appropriate for this unit
        filterToggles.forEach((toggle, category) -> toggle.setVisible(getUsedButtons().contains(category)));
        hideToggles.forEach((toggle, category) -> toggle.setVisible(getUsedButtons().contains(category)));
        // Deselect hidden toggles
        filterToggles.entrySet().stream()
              .filter(entry -> !getUsedButtons().contains(entry.getValue()))
              .forEach(entry -> entry.getKey().setSelected(false));
        hideToggles.entrySet().stream()
              .filter(entry -> !getUsedButtons().contains(entry.getValue()))
              .forEach(entry -> entry.getKey().setSelected(false));
    }

    /**
     * Constructs and returns the Panel containing "Hide:" filter toggles
     */
    private Component setupHideFilterPanel() {
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

        hideToggles.keySet().forEach(button -> button.addItemListener(e -> equipmentSorter.sort()));
        buttonPanel.add(hideProtoButton);
        buttonPanel.add(hideOneShotButton);
        buttonPanel.add(hideTorpedoButton);
        buttonPanel.add(hideAPButton);
        buttonPanel.add(hideUnusableAmmoButton);
        buttonPanel.add(hideUnavailableButton);

        var hideFilterPanel = Box.createHorizontalBox();
        hideFilterPanel.add(new JLabel("Hide: "));
        hideFilterPanel.add(buttonPanel);
        hideFilterPanel.setBackground(UIUtil.alternateTableBGColor());
        hideFilterPanel.setOpaque(true);
        hideFilterPanel.setBorder(new EmptyBorder(0, 8, 0, 8));
        return hideFilterPanel;
    }

    /**
     * Constructs and returns the Panel containing the Add buttons, the Text Filter and the Table Mode button.
     */
    private Component setupMiscPanel() {
        var miscPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        if (allowAdd()) {
            addButton.setMnemonic('A');
            addButton.setEnabled(false);
            miscPanel.add(addButton);
            miscPanel.add(Box.createHorizontalStrut(15));
            if (useAddMultipleButton()) {
                addMultipleButton.addActionListener(e -> addSelectedEquipment((int) addMultipleCount.getValue()));
                addMultipleCount
                      .addChangeListener(e -> addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue()));
                addMultipleButton.setText(ADD_TEXT + addMultipleCount.getValue());
                addMultipleButton.setEnabled(false);
                miscPanel.add(addMultipleButton);
                miscPanel.add(addMultipleCount);
                miscPanel.add(Box.createHorizontalStrut(15));
            }
        }
        if (useTextFilter()) {
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
            var cancelTextFilter = new JButton("X");
            cancelTextFilter.setForeground(GUIPreferences.getInstance().getWarningColor());
            cancelTextFilter.addActionListener(e -> txtFilter.setText(""));
            miscPanel.add(new JLabel("Text Filter: "));
            miscPanel.add(txtFilter);
            miscPanel.add(cancelTextFilter);
            miscPanel.add(Box.createHorizontalStrut(15));
        }
        if (useSwitchTableColumns()) {
            miscPanel.add(tableModeButton);
            tableModeButton.addActionListener(e -> switchTableMode());
        }
        miscPanel.setBackground(UIUtil.alternateTableBGColor());
        miscPanel.setOpaque(true);
        miscPanel.setBorder(new EmptyBorder(0, 8, 0, 8));
        return miscPanel;
    }

    /**
     * Called from the Add and Add Multiple buttons and the {@link AddAction} (when pressing Enter) to add equipment to
     * the unit. Forwards to the overridable addEquipment() method.
     */
    private void addSelectedEquipment(int count) {
        if (count < 1) {
            throw new AssertionError("Trying to add equipment with a count of less than 1!");
        }

        if (allowAdd() && (masterEquipmentTable.getSelectedRowCount() >= 1)) {
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
        updateFilterToggleVisibility();
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

    /**
     * Called from the Table Column Mode button to switch between two table column modes.
     */
    private void switchTableMode() {
        tableMode = !tableMode;
        updateVisibleColumns();
    }

    /**
     * Called from the type filter toggles (energy, ballistic, etc.) to trigger a re-filter. Contrary to standard
     * JToggleButton behavior, normal clicking will deselect all other filter toggles while Ctrl-clicking adds the
     * clicked filter toggle.
     */
    private void filterToggleHandler(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == 0) {
            filterToggles.keySet().forEach(button -> button.setSelected(e.getSource() == button));
        }
        equipmentSorter.sort();
    }

    /**
     * Called from the Show All button to activate all shown type filter toggles.
     */
    private void showAllEquipmentTypes() {
        // Select all buttons that are displayed for this unit
        filterToggles.entrySet().stream()
              .filter(entry -> getUsedButtons().contains(entry.getValue()))
              .forEach(entry -> entry.getKey().setSelected(true));
        equipmentSorter.sort();
    }

    /**
     * @return true when the given equipment is available to be added to the unit.
     */
    private boolean canLegallyBeAddedToUnit(@Nullable EquipmentType equipment) {
        return (equipment != null) && eSource.getTechManager().isLegal(equipment);
    }

    private void setupRowFilter() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                // Note: append `&& eSource.getTechManager().getGameYear() >= etype.getProductionDate()`
                // or `etype.getCommonDate()` in case we wish to change the availability start year from
                // the prototype/introduction date to something later.
                return shouldShow(etype);
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
        equipmentSorter.sort();
    }

    /**
     * Returns true when the given equipment should show up in the database table. This method checks if the equipment
     * is available to the unit type at all and if the filter toggles and text filter show or hide it. This may be
     * overridden to exclude or include equipment based on other evaluations. For example, by returning true only for
     * equipment that is part of the unit an inventory can be created although this inventory will not show the
     * equipment counts. Another option is to reduce the shown equipment to a predefined warehouse content such as an
     * MHQ Campaign inventory, although here also, an equipment count is (currently) not supported.
     *
     * @param equipment The equipment type to be shown or hidden
     *
     * @return True when the equipment should be shown, false otherwise
     */
    protected boolean shouldShow(EquipmentType equipment) {
        return isEquipmentForEntity(equipment)
              && includedByFilters(equipment)
              && !hiddenEquipment(equipment)
              && (eSource.getTechManager().isLegal(equipment) || !hideUnavailableButton.isSelected())
              && allowedByTextFilter(equipment);
    }

    /**
     * @return true when the given equipment is allowed to be shown by an entry in the Text Filter. This is always true
     *       when the Text Filter is empty. Otherwise, the name, tech base and rules ref table columns are checked
     *       against the text filter.
     */
    private boolean allowedByTextFilter(EquipmentType equipment) {
        XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
        boolean techVisible = columnModel
              .isColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH));
        boolean rulesVisible = columnModel
              .isColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF));
        String techSearchString = EquipmentTableModel.getTechBaseAsString(equipment).toLowerCase();
        String rulesSearchString = equipment.getRulesRefs().toLowerCase();
        String lowerCaseSearchString = txtFilter.getText().toLowerCase();
        return txtFilter.getText().isBlank()
              || equipment.getName().toLowerCase().contains(lowerCaseSearchString)
              || (techVisible && techSearchString.contains(lowerCaseSearchString))
              || (rulesVisible && rulesSearchString.contains(lowerCaseSearchString));
    }

    /**
     * @return true when the given equipment is available at all to the unit type of the current unit. For example,
     *       filters out Capital weapons for Meks.
     */
    private boolean isEquipmentForEntity(EquipmentType equipment) {
        if (equipment instanceof AmmoType) {
            // Only ammo for equipped weapons is listed, therefore no need to filter by unit
            // type
            return true;
        }

        if (getEntity() instanceof Mek) {
            // FIXME : This is handled strangely in UnitUtil: MekEquipment does not include
            // weapons
            return MekUtil.isMekEquipment(equipment, (Mek) getEntity())
                  || MekUtil.isMekWeapon(equipment, getEntity())
                  || UnitUtil.isPhysicalWeapon(equipment);
        } else if (getEntity() instanceof BattleArmor) {
            // FIXME : This is handled strangely in UnitUtil: BAAPWeapons are not
            // BAEquipment
            return BattleArmorUtil.isBAEquipment(equipment, getBattleArmor())
                  || BattleArmorUtil.isBattleArmorAPWeapon(equipment);
        } else {
            return UnitUtil.isEntityEquipment(equipment, getEntity());
        }
    }

    /**
     * @return true when the filter toggles allow the given equipment to be shown.
     */
    private boolean includedByFilters(EquipmentType equipment) {
        return (showEnergyButton.isSelected() && ENERGY.passesFilter(equipment, getEntity()))
              || (showMissileButton.isSelected() && MISSILE.passesFilter(equipment, getEntity()))
              || (showBallisticButton.isSelected() && BALLISTIC.passesFilter(equipment, getEntity()))
              || (showArtilleryButton.isSelected() && ARTILLERY.passesFilter(equipment, getEntity()))
              || (showPhysicalButton.isSelected() && PHYSICAL.passesFilter(equipment, getEntity()))
              || (showIndustrialButton.isSelected() && INDUSTRIAL.passesFilter(equipment, getEntity()))
              || (showCapitalButton.isSelected() && CAPITAL.passesFilter(equipment, getEntity()))
              || (showAmmoButton.isSelected() && AMMO.passesFilter(equipment, getEntity()))
              || (showOtherButton.isSelected() && OTHER.passesFilter(equipment, getEntity()));
    }

    /**
     * @return true when the Hide toggles hide the given equipment.
     */
    private boolean hiddenEquipment(EquipmentType equipment) {
        return (hideProtoButton.isSelected() && PROTOTYPE.passesFilter(equipment, getEntity()))
              || (hideOneShotButton.isSelected() && ONE_SHOT.passesFilter(equipment, getEntity()))
              || (hideTorpedoButton.isSelected() && TORPEDO.passesFilter(equipment, getEntity()))
              || (hideAPButton.isSelected() && AP.passesFilter(equipment, getEntity()))
              || (hideUnusableAmmoButton.isSelected() && UNUSABLE_AMMO.passesFilter(equipment, getEntity()));
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

    /** A specialized table used for the equipment database. */
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

        /**
         * Sets all the row heights to the correct value. JTables don't do this automatically.
         */
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
