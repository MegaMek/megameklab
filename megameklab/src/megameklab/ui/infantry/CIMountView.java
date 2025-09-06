/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.ui.FlatTextBorder;
import megamek.common.units.Infantry;
import megamek.common.units.InfantryMount;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.TabScrollPane;

public class CIMountView extends IView implements ActionListener {

    private RefreshListener refresh = null;

    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";

    private final JButton btnSetMount = new JButton("Set Mount");
    private final JRadioButton radioButtonStats = new JRadioButton("Stats");
    private final JRadioButton radioButtonCustom = new JRadioButton("Custom");
    private final BeastMountTableModel tableModel = new BeastMountTableModel();
    private final JTable creatureTable = new JTable();
    private final JPanel creatureView = new JPanel();
    private final CardLayout equipmentLayout = new CardLayout();

    private final CICustomMountView customMountView;

    public CIMountView(EntitySource eSource) {
        super(eSource);
        customMountView = new CICustomMountView(eSource, this);
        creatureTable.setModel(tableModel);
        creatureTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        creatureTable.setShowGrid(false);
        creatureTable.setDoubleBuffered(true);
        TableCellRenderer renderer = tableModel.new Renderer();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            TableColumn column = creatureTable.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer);
        }
        creatureTable.getSelectionModel().addListSelectionListener(ev -> checkValid());

        setUpPanels();
        radioButtonStats.setSelected(true);
        refresh();
        checkValid();
    }

    private void setUpPanels() {
        JPanel databasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnSetMount, gbc);
        btnSetMount.addActionListener(this);

        ButtonGroup buttonGroupView = new ButtonGroup();
        buttonGroupView.add(radioButtonStats);
        buttonGroupView.add(radioButtonCustom);
        radioButtonStats.addActionListener(ev -> showCard(CARD_TABLE));
        radioButtonCustom.addActionListener(ev -> showCard(CARD_CUSTOM));
        JPanel btnPanel = new JPanel();
        btnPanel.add(radioButtonStats);
        btnPanel.add(radioButtonCustom);
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        databasePanel.add(btnPanel, gbc);

        creatureView.setLayout(equipmentLayout);

        gbc.insets = new Insets(2, 0, 0, 0);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(creatureView, gbc);

        setLayout(new BorderLayout());
        add(databasePanel, BorderLayout.CENTER);

        JPanel tableView = new JPanel(new GridLayout(1, 1));
        tableView.add(new JScrollPane(creatureTable));
        creatureView.add(tableView, CARD_TABLE);

        Border compundBorder = BorderFactory.createCompoundBorder(new FlatTextBorder(),
              new EmptyBorder(5, 5, 5, 5));
        customMountView.setBorder(compundBorder);

        // this outer panel prevents the customView from being stretched to fill the whole frame
        var outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outerPanel.add(customMountView);

        creatureView.add(new TabScrollPane(outerPanel), CARD_CUSTOM);
        customMountView.movementModeChanged();
        checkValid();
    }

    public void refresh() {
        customMountView.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        customMountView.addRefreshedListener(l);
    }

    void checkValid() {
        boolean valid;
        if (radioButtonStats.isSelected()) {
            valid = creatureTable.getSelectedRow() >= 0;
        } else {
            valid = !customMountView.txtMountName.getText().isEmpty();
            try {
                valid &= Double.parseDouble(customMountView.txtWeight.getText()) > 0;
            } catch (NumberFormatException ignored) {
                valid = false;
            }
        }
        btnSetMount.setEnabled(valid);
    }

    private InfantryMount selectedMount(int rowIndex) {
        if ((rowIndex >= 0) && (rowIndex < tableModel.getRowCount())) {
            return InfantryMount.sampleMounts.get(rowIndex);
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(btnSetMount)) {
            if (radioButtonStats.isSelected()) {
                int view = creatureTable.getSelectedRow();
                if (view < 0) {
                    // Nothing is selected
                    return;
                }
                int selected = creatureTable.convertRowIndexToModel(view);
                InfantryMount newMount = selectedMount(selected);
                if ((getInfantry().getMount() != null) && (getInfantry().getMount().movementMode().isSubmarine())
                      && ((newMount == null) || !newMount.movementMode().isSubmarine())) {
                    getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.SCUBA);
                }
                getInfantry().setMount(selectedMount(selected));
            } else {
                getInfantry().setMount(customMountView.customMount());
            }
        }
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }

    private void showCard(String card) {
        equipmentLayout.show(creatureView, card);
        checkValid();
    }
}
