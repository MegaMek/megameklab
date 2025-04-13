/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMek is distributed in the hope that it will be useful,
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
 */
package megameklab.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import megamek.common.Crew;
import megamek.common.Entity;
import megameklab.ui.MegaMekLabTabbedUI;

public class ForceBuildUI extends JFrame {

    private static ForceBuildUI instance; // Singleton instance

    private final ArrayList<Entity> forceList = new ArrayList<>();
    private JTable entityTable;
    private DefaultTableModel tableModel;
    private JLabel totalBVLabel;
    private JScrollPane scrollPane;
    
    private static final int COL_REMOVE = 0;
    private static final int COL_NAME = 1;
    private static final int COL_GUNNERY = 2;
    private static final int COL_PILOTING = 3;
    private static final int COL_BV = 4;

    private static final Integer[] SKILL_LEVELS = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    // Private constructor for Singleton
    private ForceBuildUI() {
        super();
        final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");
        setTitle(resourceMap.getString("ForceBuildDialog.windowName.text"));
        setMinimumSize(new Dimension(300, 200));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createCenterPane();
        packWindow();
    }

    /**
     * Gets the singleton instance of the ForceBuildWindow.
     * Creates the instance if it doesn't exist.
     *
     * @param parent The parent frame, used only on first creation.
     * @return The singleton ForceBuildWindow instance.
     */
    public static synchronized ForceBuildUI getInstance() {
        if (instance == null) {
            instance = new ForceBuildUI();
        }
        return instance;
    }

    /**
     * Gets/creates the singleton instance, adds an entity to it, and ensures it's visible.
     *
     * @param entity The entity to add.
     */
    public static synchronized void showAndAddEntity(Entity entity) {
        ForceBuildUI view = getInstance();
        view.addEntity(entity);
        view.setVisible(true);
    }

    /**
     * Gets/creates the singleton instance and ensures it's visible.
     */
    public static synchronized void showWindow() {
        ForceBuildUI view = getInstance();
        view.setVisible(true);
    }

    private void packWindow() {
        int rowHeight = entityTable.getRowHeight();
        int headerHeight = entityTable.getTableHeader().getPreferredSize().height;
        int tableHeight = Math.max(50, rowHeight * entityTable.getRowCount() + headerHeight + 5);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWindowHeight = (int) (screenSize.height * 0.85);

        Dimension tablePrefSize = entityTable.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(
                Math.max(300, tablePrefSize.width + 20),
                Math.min(tableHeight, maxWindowHeight - 100)
        ));

        pack();

        if (getHeight() > maxWindowHeight) {
            setSize(getWidth(), maxWindowHeight);
        }
    }

    /**
     * Adds an entity to the force list and updates the table.
     */
    public void addEntity(Entity entity) {
        if (entity.getCrew() == null) {
            entity.setCrew(new Crew(entity.defaultCrewType()));
        }
        entity.getCrew().setName(null, 0);
        forceList.add(entity);
        updateTableAndTotal();
        packWindow();
    }

    // Instance method to remove an entity
    public void removeEntity(int index) {
        if (index >= 0 && index < forceList.size()) {
            Entity entity = forceList.remove(index);
            entity.setCrew(new Crew(entity.defaultCrewType()));
            tableModel.removeRow(index);
            updateTotalBVLabelOnly();
            packWindow();
        }
    }

    private void updateTotalBVLabelOnly() {
        int totalBV = 0;
        for (Entity entity : forceList) {
            totalBV += entity.calculateBattleValue();
        }
        totalBVLabel.setText("Total BV: " + totalBV);
    }

    private void updateTableAndTotal() {
        // Clear existing rows
        tableModel.setRowCount(0);

        int totalBV = 0;
        for (Entity entity : forceList) {
            int bv = entity.calculateBattleValue();
            Integer gunnery = null;
            Integer piloting = null;

            ;
            int gSkill = entity.getCrew().getGunnery();
            int pSkill = entity.getCrew().getPiloting();
            if (gSkill >= 0) gunnery = gSkill;
            if (pSkill >= 0) piloting = pSkill;

            tableModel.addRow(new Object[]{UIManager.getIcon("InternalFrame.closeIcon"), entity.getDisplayName(), gunnery, piloting, bv});
            totalBV += bv;
        }

        totalBVLabel.setText("Total BV: " + totalBV);
    }

    public void createCenterPane() {
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"", "Name", "Gunnery", "Piloting", "BV"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == COL_REMOVE || column == COL_GUNNERY || column == COL_PILOTING;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == COL_BV) {
                    return Integer.class;
                }
                if (columnIndex == COL_GUNNERY || columnIndex == COL_PILOTING) {
                    return Integer.class;
                }
                if (columnIndex == COL_REMOVE) {
                    return JButton.class;
                }
                return String.class;
            }
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (row >= 0 && row < forceList.size()) {
                    Entity entity = forceList.get(row);
                    boolean needsBvUpdate = false;

                    Integer skillValue = (Integer) aValue;

                    if (column == COL_GUNNERY) {
                        entity.getCrew().setGunnery(skillValue);
                        needsBvUpdate = true;
                    } else if (column == COL_PILOTING) {
                        entity.getCrew().setPiloting(skillValue);
                        needsBvUpdate = true;
                    }
                    super.setValueAt(aValue, row, column);
                    if (needsBvUpdate) {
                        int newBv = entity.calculateBattleValue();
                        super.setValueAt(newBv, row, COL_BV);
                        updateTotalBVLabelOnly();
                    }
                } else {
                    super.setValueAt(aValue, row, column);
                }
            }
        };

        entityTable = new JTable(tableModel);
        entityTable.setFillsViewportHeight(true);
        entityTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        entityTable.setDragEnabled(true);
        entityTable.setDropMode(DropMode.INSERT_ROWS);
        entityTable.setTransferHandler(new ForceListTransferHandler());
        entityTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); // Recommended for row reordering

        entityTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = entityTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < forceList.size()) {
                        Entity entityToFind = forceList.get(row);
                        MegaMekLabTabbedUI.showEditorForEntity(entityToFind);
                        getInstance().toFront();
                        getInstance().requestFocus();
                    }
                }
            }
        });

        TableColumnModel columnModel = entityTable.getColumnModel();

        // Remove Button Column
        TableColumn removeColumn = columnModel.getColumn(COL_REMOVE);
        removeColumn.setPreferredWidth(30);
        removeColumn.setMinWidth(30);
        removeColumn.setMaxWidth(40);
        removeColumn.setCellRenderer(new ButtonRenderer());
        removeColumn.setCellEditor(new ButtonEditor());

        // Name Column
        TableColumn nameColumn = columnModel.getColumn(COL_NAME);
        nameColumn.setPreferredWidth(200);
        nameColumn.setMinWidth(150);

        // Gunnery Column
        TableColumn gunneryColumn = columnModel.getColumn(COL_GUNNERY);
        JComboBox<Integer> gunneryComboBox = new JComboBox<>(SKILL_LEVELS);
        gunneryColumn.setCellEditor(new DefaultCellEditor(gunneryComboBox));
        gunneryColumn.setPreferredWidth(70);
        gunneryColumn.setMinWidth(50);

        // Piloting Column
        TableColumn pilotingColumn = columnModel.getColumn(COL_PILOTING);
        JComboBox<Integer> pilotingComboBox = new JComboBox<>(SKILL_LEVELS);
        pilotingColumn.setCellEditor(new DefaultCellEditor(pilotingComboBox));
        pilotingColumn.setPreferredWidth(70);
        pilotingColumn.setMinWidth(50);

        // BV Column
        TableColumn bvColumn = columnModel.getColumn(COL_BV);
        bvColumn.setPreferredWidth(70);
        bvColumn.setMinWidth(50);

        // Right-align values
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        bvColumn.setCellRenderer(rightRenderer);
        gunneryColumn.setCellRenderer(rightRenderer);
        pilotingColumn.setCellRenderer(rightRenderer);

        scrollPane = new JScrollPane(entityTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // --- Bottom Panel ---
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        // Print Force Button
        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            System.out.println("Print Force button clicked");
        });
        buttonPanel.add(printButton, BorderLayout.WEST);

        // Print Force Button
        JButton pdfExportbutton = new JButton("Export PDF");
        pdfExportbutton.addActionListener(e -> {
            System.out.println("PDF Export button clicked");
        });
        buttonPanel.add(pdfExportbutton, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.WEST);

        // Total BV Label
        totalBVLabel = new JLabel("Total BV: 0", SwingConstants.RIGHT);
        totalBVLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        bottomPanel.add(totalBVLabel, BorderLayout.EAST);

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);


        updateTableAndTotal();

        add(centerPanel, BorderLayout.CENTER);
    }

    // Inner class for rendering the button in the table cell
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
            setMargin(new Insets(0, 0, 0, 0));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
             if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            return this;
        }
    }

    class ForceListTransferHandler extends TransferHandler {
        private final DataFlavor localObjectFlavor = new DataFlavor(Integer.class, "Integer Row Index");
        private int[] rows = null;

        @Override
        protected Transferable createTransferable(JComponent c) {
            JTable table = (JTable) c;
            rows = table.getSelectedRows();
            if (rows == null || rows.length == 0) {
                return null;
            }
            if (rows.length > 1) {
                return null;
            }
            return new RowTransferable(rows[0]);
        }

        @Override
        public boolean canImport(TransferSupport support) {
            if (!support.isDrop() || !support.isDataFlavorSupported(localObjectFlavor)) {
                return false;
            }
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            // Do not allow dropping onto the source row
            if (rows != null && dl.getRow() == rows[0]) {
                return false;
            }
            return true;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            JTable target = (JTable) support.getComponent();
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            int index = dl.getRow();
            int max = tableModel.getRowCount();
            if (index < 0 || index > max) {
                index = max;
            }
            try {
                Integer rowFrom = (Integer) support.getTransferable().getTransferData(localObjectFlavor);
                if (rowFrom != -1 && rowFrom != index) {
                    // Adjust drop index if dragging downwards
                    int dropIndex = (rowFrom < index) ? index - 1 : index;
                    // Reorder the underlying forceList
                    Entity movedEntity = forceList.remove(rowFrom.intValue());
                    forceList.add(dropIndex, movedEntity);
                    updateTableAndTotal();
                    // Select the moved row after the update
                    target.setRowSelectionInterval(dropIndex, dropIndex);
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            cleanup(action == MOVE);
        }

        private void cleanup(boolean move) {
            rows = null;
        }

        // Helper Transferable class
        class RowTransferable implements Transferable {
            private Integer row;

            public RowTransferable(Integer row) {
                this.row = row;
            }

            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{localObjectFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return localObjectFlavor.equals(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                if (!isDataFlavorSupported(flavor)) {
                    throw new UnsupportedFlavorException(flavor);
                }
                return row;
            }
        }
    }

    // Inner class for handling button clicks in the table cell
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private final JButton button;
        private int currentRow;

        public ButtonEditor() {
            button = new JButton();
            button.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setOpaque(true);
            button.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.currentRow = row;
             if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            removeEntity(currentRow);
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
