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
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.ui.FlatTextBorder;
import megamek.client.ui.WrapLayout;
import megamek.common.units.ConvInfantry;
import megamek.common.units.InfantryMount;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.TabScrollPane;

public class CIMountView extends IView implements ActionListener {

    private RefreshListener refresh = null;

    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";

    private final JButton addMountButton = new JButton("Add Mount");
    private final JToggleButton createCustomMountButton = new JToggleButton("Create Custom Mount");
    private final BeastMountTableModel tableModel = new BeastMountTableModel();
    private final JTable mountTable = new JTable();
    private final JPanel mountPanel = new JPanel();
    private final CardLayout mountLayout = new CardLayout();

    private final CICustomMountView customMountView;

    public CIMountView(EntitySource eSource) {
        super(eSource);
        customMountView = new CICustomMountView(eSource, this);
        mountTable.setModel(tableModel);
        mountTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        mountTable.setShowGrid(false);
        mountTable.setDoubleBuffered(true);
        TableCellRenderer renderer = tableModel.new Renderer();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            TableColumn column = mountTable.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer);
        }
        JScrollPane mountTableScroll = new JScrollPane();
        mountTableScroll.setViewportView(mountTable);
        mountTable.getSelectionModel().addListSelectionListener(ev -> checkValid());

        mountPanel.setLayout(mountLayout);
        mountPanel.add(mountTableScroll, CARD_TABLE);
        mountPanel.add(getCustomMountPanel(), CARD_CUSTOM);

        setEquipmentView();

        setLayout(new BorderLayout());
        add(getControlPanel(), BorderLayout.PAGE_START);
        add(mountPanel, BorderLayout.CENTER);
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
        if (createCustomMountButton.isSelected()) {
            valid = !customMountView.txtMountName.getText().isEmpty();
            try {
                valid &= Double.parseDouble(customMountView.txtWeight.getText()) > 0;
            } catch (NumberFormatException ignored) {
                valid = false;
            }
        } else {
            valid = mountTable.getSelectedRow() >= 0;
        }
        addMountButton.setEnabled(valid);
    }

    private InfantryMount selectedMount(int rowIndex) {
        if ((rowIndex >= 0) && (rowIndex < tableModel.getRowCount())) {
            return InfantryMount.sampleMounts.get(rowIndex);
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(addMountButton)) {
            if (createCustomMountButton.isSelected()) {
                getInfantry().setMount(customMountView.customMount());
            } else {
                int view = mountTable.getSelectedRow();
                if (view < 0) {
                    // Nothing is selected
                    return;
                }
                int selected = mountTable.convertRowIndexToModel(view);
                InfantryMount newMount = selectedMount(selected);
                if ((getInfantry().getMount() != null) && (getInfantry().getMount().movementMode().isSubmarine())
                      && ((newMount == null) || !newMount.movementMode().isSubmarine())) {
                    getInfantry().setSpecializations(getInfantry().getSpecializations() & ~ConvInfantry.SCUBA);
                }
                getInfantry().setMount(selectedMount(selected));
            }
        }
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }

    private void setEquipmentView() {
        if (createCustomMountButton.isSelected()) {
            mountLayout.show(mountPanel, CARD_CUSTOM);
        } else {
            mountLayout.show(mountPanel, CARD_TABLE);
        }

        checkValid();
    }

    private TabScrollPane getCustomMountPanel() {
        customMountView.setBorder(new FlatTextBorder());

        // This outer panel prevents the customView from being stretched to fill the whole frame
        var outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outerPanel.add(customMountView);
        customMountView.movementModeChanged();

        return new TabScrollPane(outerPanel);
    }

    /** Creates the control panel with the filters and buttons. */
    private JComponent getControlPanel() {
        Box controlPanel = Box.createVerticalBox();
        controlPanel.add(getAddCreateCustomButtonsPanel());
        controlPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        return controlPanel;
    }

    /**
     * Constructs and returns the Panel containing the Add and Create Custom buttons.
     */
    private Component getAddCreateCustomButtonsPanel() {
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
        addMountButton.addActionListener(this);
        buttonPanel.add(addMountButton);
        createCustomMountButton.addActionListener(e -> setEquipmentView());
        buttonPanel.add(createCustomMountButton);

        var addCreateCustomButtonsPanel = Box.createHorizontalBox();
        addCreateCustomButtonsPanel.add(buttonPanel);
        addCreateCustomButtonsPanel.setBackground(UIManager.getColor("Table.background"));
        addCreateCustomButtonsPanel.setOpaque(true);
        return addCreateCustomButtonsPanel;
    }
}
