/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.WrapLayout;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.models.XTableColumnModel;
import megamek.common.TechConstants;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.util.EquipmentTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.TabScrollPane;
import megameklab.util.CConfig;

public class CIArmorView extends IView implements ActionListener, ChangeListener {
    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
    private RefreshListener refresh = null;

    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";

    private final JButton addArmorButton = new JButton(resourceMap.getString("CIArmorView.addArmor"));
    private final JButton removeArmorButton = new JButton(resourceMap.getString("CIArmorView.removeArmor"));

    private final JToggleButton hideUnavailableButton = new JToggleButton(
          resourceMap.getString("CIArmorView.unavailable"), true);
    private final JToggleButton createCustomArmorButton = new JToggleButton(
          resourceMap.getString("CIArmorView.createCustomArmor"));

    private final JTextField txtFilter = new JTextField("", 15);
    private final JButton cancelTextFilter = new JButton("X");
    private final JButton tableModeButton = new JButton(resourceMap.getString("CIArmorView.switchTableColumns"));
    private boolean tableMode = true;

    private final EquipmentTableModel masterEquipmentList;
    private final TableRowSorter<EquipmentTableModel> equipmentSorter;
    private final JTable masterEquipmentTable = new JTable();
    private final JPanel armorPanel = new JPanel();
    private final CardLayout armorLayout = new CardLayout();

    private final JTextField armorName = new JTextField(getInfantry().getCustomArmorName() != null ?
          getInfantry().getCustomArmorName() : "", 10);
    private final JSpinner armorValue = new JSpinner(new SpinnerNumberModel(1.0, 0.5, 3.0, 0.5));
    private final JCheckBox chEncumber = new JCheckBox(resourceMap.getString("CIArmorView.encumbering"));
    private final JCheckBox chSpaceSuit = new JCheckBox(resourceMap.getString("CIArmorView.spaceSuit"));
    private final JCheckBox chDEST = new JCheckBox(resourceMap.getString("CIArmorView.dest"));
    private final JCheckBox chSneakCamo = new JCheckBox(resourceMap.getString("CIArmorView.sneakCamo"));
    private final JCheckBox chSneakIR = new JCheckBox(resourceMap.getString("CIArmorView.sneakIr"));
    private final JCheckBox chSneakECM = new JCheckBox(resourceMap.getString("CIArmorView.sneakEcm"));

    public CIArmorView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
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
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            TableColumn column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionListener selectionListener = e -> {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addArmorButton.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        };
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(evt -> {
            int view = masterEquipmentTable.getSelectedRow();
            if (view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            addArmorButton.setEnabled((equip instanceof MiscType) && (equip.hasFlag(MiscType.F_ARMOR_KIT)));
        });

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_ARMOR_KIT))) {
                allTypes.add(eq);
            }
        }

        armorPanel.setLayout(armorLayout);
        armorPanel.add(masterEquipmentScroll, CARD_TABLE);
        armorPanel.add(getCustomArmorPanel(), CARD_CUSTOM);

        masterEquipmentList.setData(allTypes);

        if (getInfantry().getArmorKit() == null && getInfantry().hasArmor()) {
            createCustomArmorButton.setSelected(true);
        }

        setEquipmentView();

        setLayout(new BorderLayout());
        add(getControlPanel(), BorderLayout.PAGE_START);
        add(armorPanel, BorderLayout.CENTER);
    }

    public void refresh() {
        removeAllListeners();
        armorName.setText(getInfantry().getCustomArmorName() != null ?
              getInfantry().getCustomArmorName() : "");
        armorName.addCaretListener(ev -> checkCustomArmorValid());
        armorValue.setValue(getInfantry().getCustomArmorDamageDivisor());
        chEncumber.setSelected(getInfantry().isArmorEncumbering());
        chSpaceSuit.setSelected(getInfantry().hasSpaceSuit());
        chDEST.setSelected(getInfantry().hasDEST());
        chSneakCamo.setSelected(getInfantry().hasSneakCamo());
        chSneakIR.setSelected(getInfantry().hasSneakIR());
        chSneakECM.setSelected(getInfantry().hasSneakECM());
        if (getInfantry().getTechLevel() < TechConstants.T_TW_ALL) {
            armorName.setEnabled(false);
            armorValue.setEnabled(false);
            chEncumber.setEnabled(false);
            chSpaceSuit.setEnabled(false);
            chDEST.setEnabled(false);
            chSneakCamo.setEnabled(false);
            chSneakIR.setEnabled(false);
            chSneakECM.setEnabled(false);
        } else {
            armorName.setEnabled(true);
            armorValue.setEnabled(true);
            chEncumber.setEnabled(true);
            chSpaceSuit.setEnabled(true);
            chDEST.setEnabled(true);
            chSneakCamo.setEnabled(true);
            chSneakIR.setEnabled(true);
            chSneakECM.setEnabled(true);
        }

        filterEquipment();
        removeArmorButton.setEnabled(getInfantry().hasArmor());
        createCustomArmorButton.setEnabled(getInfantry().getArmorKit() == null);
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        removeAllListeners();
        if (evt.getSource().equals(addArmorButton)) {
            if (createCustomArmorButton.isSelected()) {
                getInfantry().setCustomArmorDamageDivisor((Double) armorValue.getModel().getValue());
                getInfantry().setCustomArmorName(armorName.getText());
                getInfantry().setArmorEncumbering(chEncumber.isSelected());
                getInfantry().setSpaceSuit(chSpaceSuit.isSelected());
                getInfantry().setDEST(chDEST.isSelected());
                getInfantry().setSneakCamo(chSneakCamo.isSelected());
                getInfantry().setSneakIR(chSneakIR.isSelected());
                getInfantry().setSneakECM(chSneakECM.isSelected());
            } else {
                int view = masterEquipmentTable.getSelectedRow();
                if (view < 0) {
                    // selection got filtered away
                    return;
                }
                int selected = masterEquipmentTable.convertRowIndexToModel(view);
                EquipmentType equip = masterEquipmentList.getType(selected);
                if ((equip instanceof MiscType) && (equip.hasFlag(MiscType.F_ARMOR_KIT))) {
                    getInfantry().setArmorKit(equip);
                    createCustomArmorButton.setEnabled(false);
                }
            }
        } else if (evt.getSource().equals(removeArmorButton)) {
            getInfantry().setArmorKit(null);
            getInfantry().setCustomArmorName(null);
            getInfantry().setCustomArmorDamageDivisor(1.0);
            getInfantry().setArmorEncumbering(false);
            getInfantry().setSpaceSuit(false);
            getInfantry().setDEST(false);
            getInfantry().setSneakCamo(false);
            getInfantry().setSneakIR(false);
            getInfantry().setSneakECM(false);
            createCustomArmorButton.setEnabled(true);
            if (createCustomArmorButton.isSelected()) {
                checkCustomArmorValid();
            }
        } else if (evt.getSource().equals(chEncumber)) {
            checkCustomArmorValid();
        } else if (evt.getSource().equals(chSpaceSuit)) {
            checkCustomArmorValid();
        } else if (evt.getSource().equals(chDEST)) {
            if (chDEST.isSelected()) {
                chSneakCamo.setSelected(false);
                chSneakIR.setSelected(false);
                chSneakECM.setSelected(false);
            }
            checkCustomArmorValid();
        } else if (evt.getSource().equals(chSneakCamo)) {
            if (chSneakCamo.isSelected()) {
                chDEST.setSelected(false);
            }
            checkCustomArmorValid();
        } else if (evt.getSource().equals(chSneakIR)) {
            if (chSneakIR.isSelected()) {
                chDEST.setSelected(false);
            }
            checkCustomArmorValid();
        } else if (evt.getSource().equals(chSneakECM)) {
            if (chSneakECM.isSelected()) {
                chDEST.setSelected(false);
            }
            checkCustomArmorValid();
        }
        addAllListeners();
        if (evt.getSource().equals(addArmorButton) || evt.getSource().equals(removeArmorButton)) {
            if (refresh != null) {
                refresh.refreshStructure();
                refresh.refreshStatus();
                refresh.refreshPreview();
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent evt) {
        if (evt.getSource().equals(armorValue)) {
            checkCustomArmorValid();
        }
    }

    private void addAllListeners() {
        addArmorButton.addActionListener(this);
        removeArmorButton.addActionListener(this);
        armorValue.addChangeListener(this);
        chEncumber.addActionListener(this);
        chSpaceSuit.addActionListener(this);
        chDEST.addActionListener(this);
        chSneakCamo.addActionListener(this);
        chSneakIR.addActionListener(this);
        chSneakECM.addActionListener(this);
    }

    private void removeAllListeners() {
        addArmorButton.removeActionListener(this);
        removeArmorButton.removeActionListener(this);
        armorValue.removeChangeListener(this);
        chEncumber.removeActionListener(this);
        chSpaceSuit.removeActionListener(this);
        chDEST.removeActionListener(this);
        chSneakCamo.removeActionListener(this);
        chSneakIR.removeActionListener(this);
        chSneakECM.removeActionListener(this);
    }

    private void filterEquipment() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if (!(etype instanceof MiscType) || !(etype.hasFlag(MiscType.F_ARMOR_KIT))) {
                    return false;
                } else if ((null != eSource.getTechManager())
                      && !eSource.getTechManager().isLegal(etype) && hideUnavailableButton.isSelected()) {
                    return false;
                } else if (!etype.isAvailableIn(getInfantry().getTechLevelYear(),
                      CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                    return false;
                } else if (!txtFilter.getText().isBlank()) {
                    return etype.getName().toLowerCase().contains(txtFilter.getText().toLowerCase());
                } else {
                    return true;
                }
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
    }

    private void switchTableMode() {
        tableMode = !tableMode;
        setEquipmentView();
    }

    private void setEquipmentView() {
        if (createCustomArmorButton.isSelected()) {
            hideUnavailableButton.setEnabled(false);
            checkCustomArmorValid();
            txtFilter.setText("");
            txtFilter.setEnabled(false);
            cancelTextFilter.setEnabled(false);
            tableModeButton.setEnabled(false);
            armorLayout.show(armorPanel, CARD_CUSTOM);
        } else {
            hideUnavailableButton.setEnabled(true);
            addArmorButton.setEnabled(true);
            txtFilter.setEnabled(true);
            cancelTextFilter.setEnabled(true);
            tableModeButton.setEnabled(true);
            armorLayout.show(armorPanel, CARD_TABLE);
            XTableColumnModel columnModel = (XTableColumnModel) masterEquipmentTable.getColumnModel();
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MEDIUM_RANGE),
                  false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_LEVEL),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH_RATING),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PROTOTYPE),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_PRODUCTION),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_COMMON),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_EXTINCT),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DATE_REINTRODUCED),
                  !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), !tableMode);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), !tableMode);
        }
    }

    /**
     * Checks if custom armor is valid and enables the add armor button if so
     */
    private void checkCustomArmorValid() {
        addArmorButton.setEnabled(!armorName.getText().isBlank() || (Double) armorValue.getModel().getValue() != 1.0 ||
              chEncumber.isSelected() || chSpaceSuit.isSelected() || chDEST.isSelected() || chSneakCamo.isSelected() ||
              chSneakIR.isSelected() || chSneakECM.isSelected());
    }

    /** Creates the custom armor panel **/
    private TabScrollPane getCustomArmorPanel() {
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel customArmorPanel = new JPanel(new GridBagLayout());
        customArmorPanel.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("CIArmorView.customArmor")));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = BuildView.STANDARD_INSETS;

        gbc.gridy = 0;
        customArmorPanel.add(new StandardBuildLabel(resourceMap.getString("CIArmorView.name")), gbc);
        customArmorPanel.add(armorName, gbc);

        gbc.gridy++;
        customArmorPanel.add(new StandardBuildLabel(resourceMap.getString("CIArmorView.damageDivisor")), gbc);
        customArmorPanel.add(armorValue, gbc);
        JFormattedTextField damageDivisorTextField = ((DefaultEditor) armorValue.getEditor()).getTextField();
        damageDivisorTextField.setEditable(false);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        customArmorPanel.add(chEncumber, gbc);
        customArmorPanel.add(chSpaceSuit, gbc);
        gbc.gridy++;
        customArmorPanel.add(chDEST, gbc);
        customArmorPanel.add(chSneakCamo, gbc);
        gbc.gridy++;
        customArmorPanel.add(chSneakIR, gbc);
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        customArmorPanel.add(chSneakECM, gbc);

        wrapperPanel.add(customArmorPanel);

        return new TabScrollPane(wrapperPanel);
    }

    /** Creates the control panel with the filters and buttons. */
    private JComponent getControlPanel() {
        Box controlPanel = Box.createVerticalBox();
        controlPanel.add(getHideTogglesPanel());
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(getAddRemoveCreateCustomButtonsPanel());
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(getTextFilterAndTableModeButtonPanel());
        controlPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        return controlPanel;
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
        buttonPanel.add(new JLabel(resourceMap.getString("CIArmorView.hide")));
        hideUnavailableButton.addActionListener(e -> filterEquipment());
        buttonPanel.add(hideUnavailableButton);

        var hideTogglesPanel = Box.createHorizontalBox();
        hideTogglesPanel.add(buttonPanel);
        hideTogglesPanel.setBackground(UIManager.getColor("Table.background"));
        hideTogglesPanel.setOpaque(true);
        return hideTogglesPanel;
    }

    /**
     * Constructs and returns the Panel containing the Add, Remove, and Create Custom buttons.
     */
    private Component getAddRemoveCreateCustomButtonsPanel() {
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
        buttonPanel.add(addArmorButton);
        buttonPanel.add(removeArmorButton);
        createCustomArmorButton.addActionListener(e -> setEquipmentView());
        buttonPanel.add(createCustomArmorButton);

        var addRemoveCreateCustomButtonsPanel = Box.createHorizontalBox();
        addRemoveCreateCustomButtonsPanel.add(buttonPanel);
        addRemoveCreateCustomButtonsPanel.setBackground(UIManager.getColor("Table.background"));
        addRemoveCreateCustomButtonsPanel.setOpaque(true);
        return addRemoveCreateCustomButtonsPanel;
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
        textAndButtonPanel.add(new JLabel(resourceMap.getString("CIArmorView.textFilter")));
        textAndButtonPanel.add(txtFilter);
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
