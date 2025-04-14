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

import static megamek.client.ui.swing.util.UIUtil.menuItem;
import static megamek.common.util.CollectionUtil.anyOneElement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import megamek.client.Client;
import megamek.client.ui.swing.CustomMekDialog;
import megamek.client.ui.swing.GUIPreferences;
import megamek.client.ui.swing.lobby.LobbyErrors;
import megamek.client.ui.swing.lobby.LobbyUtility;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Crew;
import megamek.common.Entity;
import megamek.common.Game;
import megamek.common.Player;
import megamek.common.options.OptionsConstants;
import megamek.common.preference.PreferenceManager;
import megamek.common.util.C3Util;
import megamek.common.util.C3Util.C3CapacityException;
import megamek.common.util.C3Util.MismatchingC3MException;
import megamek.common.util.C3Util.MissingC3MException;
import megameklab.util.CConfig;
import megameklab.ui.dialog.PrintQueueDialog;

public class ForceBuildUI extends JFrame implements ListSelectionListener, ActionListener{

    private static ForceBuildUI instance; // Singleton instance

    private final ArrayList<Entity> forceList = new ArrayList<>();
    private JTable entityTable;
    private DefaultTableModel tableModel;
    private JLabel totalBVLabel;
    private JScrollPane scrollPane;
    private JPopupMenu rowPopupMenu = new JPopupMenu();

    private Client client;
    private Game   game;
    private Player player;
    
    private static final int COL_REMOVE = 0;
    private static final int COL_NAME = 1;
    private static final int COL_GUNNERY = 2;
    private static final int COL_PILOTING = 3;
    private static final int COL_BV = 4;

    static final String LMP_C3DISCONNECT = "C3DISCONNECT";
    static final String LMP_C3CONNECT = "C3CONNECT";
    static final String LMP_C3JOIN = "C3JOIN";
    static final String LMP_C3FORMNHC3 = "C3FORMNHC3";
    static final String LMP_C3FORMC3 = "C3FORMC3";
    static final String LMP_C3LM = "C3LM";
    static final String LMP_C3CM = "C3CM";
    private static final String NOINFO = "|-1";

    private static final Integer[] SKILL_LEVELS = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static int lastPlayerId = 1;

    // Private constructor for Singleton
    private ForceBuildUI() {
        super();
        final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");
        setTitle(resourceMap.getString("ForceBuildDialog.windowName.text"));
        setMinimumSize(new Dimension(300, 200));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createPopupMenu();
        createCenterPane();
        packWindow();
        setLocationRelativeTo(null);
        CConfig.getForceBuildPosition().ifPresent(this::setLocation);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                if (isVisible()) {
                    CConfig.writeForceBuildPosition(instance);
                }
            }
            @Override
            public void componentResized(ComponentEvent e) {
                if (isVisible()) {
                    CConfig.writeForceBuildPosition(instance);
                }
            }
        });

        client = new Client("", "", 0);
        game = client.getGame();
        game.getOptions().getOption(OptionsConstants.RPG_PILOT_ADVANTAGES).setValue(true);
        game.getOptions().getOption(OptionsConstants.RPG_MANEI_DOMINI).setValue(true);
        player = new Player(1, "Nobody");
        game.addPlayer(1, player);
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
                Math.min(tableHeight + 20, maxWindowHeight - 100)
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
        entity.getCrew().setName("", 0);
        for (int i = 0; i < forceList.size(); i++) {
            if (forceList.get(i) == entity) {
                // Entity already exists, select its row and bring window to front
                final int rowIndex = i;
                SwingUtilities.invokeLater(() -> {
                    entityTable.setRowSelectionInterval(rowIndex, rowIndex);
                    // Ensure the selected row is visible
                    entityTable.scrollRectToVisible(entityTable.getCellRect(rowIndex, 0, true));
                    this.toFront();
                    this.requestFocus();
                });
                return; // Don't add the duplicate
            }
        }
        entity.setOwner(player);
        entity.setId(game.getNextEntityId());
        game.addEntity(entity, false);
        forceList.add(entity);
        C3Util.wireC3(game, entity);
        final int newRowIndex = forceList.size() - 1;
        updateTableAndTotal();
        packWindow();
        SwingUtilities.invokeLater(() -> {
            entityTable.setRowSelectionInterval(newRowIndex, newRowIndex);
            entityTable.scrollRectToVisible(entityTable.getCellRect(newRowIndex, 0, true));
        });
    }

    // Instance method to remove an entity
    public void removeEntity(int index) {
        if (index >= 0 && index < forceList.size()) {
            Entity entity = forceList.remove(index);
            entity.setCrew(new Crew(entity.defaultCrewType()));
            entity.setGame(null);
            entity.setOwner(new Player(ForceBuildUI.lastPlayerId++, "Nobody"));
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

    /**
     * Updates the table and total BV label.
     */
    public static void refresh() {
        if (instance != null) {
            instance.updateTableAndTotal();
            instance.packWindow();
        }
    }

    private void updateTableAndTotal() {
        int selectedRow = entityTable.getSelectedRow();
        int scrollValue = scrollPane.getVerticalScrollBar().getValue();
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

            tableModel.addRow(new Object[]{UIManager.getIcon("InternalFrame.closeIcon"), entity.getShortNameRaw(), gunnery, piloting, bv});
            totalBV += bv;
        }

        totalBVLabel.setText("Total BV: " + totalBV);
        if (selectedRow >= 0 && selectedRow < tableModel.getRowCount()) {
            entityTable.setRowSelectionInterval(selectedRow, selectedRow);
        }
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollValue));
    }

    /**
     * Gets the list of all entities in the force.
     * 
     * @return
     */
    public ArrayList<Entity> getAllEntities() {
        return forceList;
    }

    private void createPopupMenu() {
        rowPopupMenu.removeAll();

        JMenuItem editItem = new JMenuItem("Edit Pilot/Equipment...");
        Font currentFont = editItem.getFont();
        editItem.setFont(currentFont.deriveFont(Font.BOLD));
        editItem.addActionListener(e -> {
            int selectedRow = entityTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < forceList.size()) {
                openEntityConfiguration(forceList.get(selectedRow));
            }
        });
        rowPopupMenu.add(editItem);

        JMenuItem viewItem = new JMenuItem("Show Editor");
        viewItem.addActionListener(e -> {
            int selectedRow = entityTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < forceList.size()) {
                openEntityInEditor(forceList.get(selectedRow));
            }
        });
        rowPopupMenu.add(viewItem);

        // --- C3 Menu ---
        rowPopupMenu.add(c3Menu(true, forceList, client, instance));

        // --- Delete Item ---
        rowPopupMenu.addSeparator();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(e -> {
            int selectedRow = entityTable.getSelectedRow();
            if (selectedRow >= 0) {
                removeEntity(selectedRow);
            }
        });
        rowPopupMenu.add(deleteItem);
    }

    private void createCenterPane() {
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

                    // Only attempt cast if it's a skill column
                    if (column == COL_GUNNERY || column == COL_PILOTING) {
                        if (aValue instanceof Integer) {
                            Integer skillValue = (Integer) aValue;
                            if (column == COL_GUNNERY) {
                                entity.getCrew().setGunnery(skillValue);
                                needsBvUpdate = true;
                            } else { // Must be COL_PILOTING
                                entity.getCrew().setPiloting(skillValue);
                                needsBvUpdate = true;
                            }
                        }
                    }
                    super.setValueAt(aValue, row, column);

                    if (needsBvUpdate) {
                        int newBv = entity.calculateBattleValue();
                        super.setValueAt(newBv, row, COL_BV);
                        updateTotalBVLabelOnly();
                        MegaMekLabTabbedUI.refreshEntity(entity);
                    }
                } else {
                    super.setValueAt(aValue, row, column);
                }
            }
        };

        entityTable = new JTable(tableModel);
        entityTable.setFillsViewportHeight(true);
        entityTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        entityTable.getTableHeader().setReorderingAllowed(false);

        entityTable.setDragEnabled(true);
        entityTable.setDropMode(DropMode.INSERT_ROWS);
        entityTable.setTransferHandler(new ForceListTransferHandler());
        entityTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        entityTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = entityTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < forceList.size()) {
                        openEntityConfiguration(forceList.get(row));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
            
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = entityTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        // Select the row that was right-clicked
                        if (entityTable.getSelectedRow() != row) {
                            entityTable.setRowSelectionInterval(row, row);
                        }
                        if (row < forceList.size()) {
                            Entity selectedEntity = forceList.get(row);
                            configureAndShowPopupMenu(selectedEntity, e);
                        }
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
            new PrintQueueDialog(instance, false, getAllEntities(), false, "").setVisible(true);
        });
        buttonPanel.add(printButton, BorderLayout.WEST);

        // Print Force Button
        JButton pdfExportbutton = new JButton("Export PDF");
        pdfExportbutton.addActionListener(e -> {
            new PrintQueueDialog(instance, true, getAllEntities(), false, "").setVisible(true);
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

    private void openEntityInEditor(Entity entity) {
        MegaMekLabTabbedUI.showEditorForEntity(entity);
        getInstance().toFront();
        getInstance().requestFocus();
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

    /**
     * Returns a command string token containing the IDs of the given entities and a
     * leading |
     * E.g. |2,14,44,22
     */
    private static String enToken(Collection<Entity> entities) {
        if (entities.isEmpty()) {
            return "|-1";
        }
        List<String> ids = entities.stream().map(Entity::getId).map(Object::toString).collect(Collectors.toList());
        return "|" + String.join(",", ids);
    }

    static String idString(Game game, int id) {
        if (PreferenceManager.getClientPreferences().getShowUnitId()) {
            return " <FONT" + UIUtil.colorString(UIUtil.uiGray()) + ">[" + id + "]</FONT>";
        } else {
            return "";
        }
    }

    /** Returns the C3 computer submenu. */
    private static JMenu c3Menu(boolean enabled, Collection<Entity> entities, Client client,
            ActionListener listener) {
        JMenu menu = new JMenu("C3");

        if (entities.stream().anyMatch(Entity::hasAnyC3System)) {

            menu.add(menuItem("Disconnect", LMP_C3DISCONNECT + NOINFO + enToken(entities), enabled, listener));

            if (entities.stream().anyMatch(e -> e.hasC3MM() || e.hasC3M())) {
                boolean allCM = entities.stream().allMatch(Entity::isC3CompanyCommander);
                menu.add(menuItem("Set as C3 Company Master", LMP_C3CM + NOINFO + enToken(entities), !allCM, listener));
                boolean allLM = entities.stream().allMatch(Entity::isC3IndependentMaster);
                menu.add(menuItem("Set as C3 Lance Master", LMP_C3LM + NOINFO + enToken(entities), !allLM, listener));
            }

            // Special treatment if exactly a C3SSSM is selected
            if (entities.size() == 4) {
                long countM = entities.stream().filter(Entity::hasC3M).count();
                long countS = entities.stream().filter(Entity::hasC3S).count();
                if (countM == 1 && countS == 3) {
                    Entity master = entities.stream().filter(Entity::hasC3M).findAny().get();
                    menu.add(menuItem("Form C3 Lance", LMP_C3FORMC3 + "|" + master.getId() + enToken(entities), true,
                            listener));
                }
            }

            // Special treatment if a group of NhC3 is selected
            if (entities.size() > 1 && entities.size() <= 6) {
                Entity master = anyOneElement(entities);
                if (entities.stream().allMatch(e -> C3Util.sameNhC3System(master, e))) {
                    menu.add(menuItem("Form C3 Lance", LMP_C3FORMNHC3 + "|" + master.getId() + enToken(entities), true,
                            listener));
                }
            }

            Entity entity = entities.stream().filter(Entity::hasAnyC3System).findAny().get();
            // ideally, find one slave or C3i/NC3/Nova to get some connection options
            entity = entities.stream().filter(e -> e.hasC3S() || e.hasNhC3()).findAny().orElse(entity);
            Game game = client.getGame();
            ArrayList<String> usedNetIds = new ArrayList<>();

            for (Entity other : client.getEntitiesVector()) {
                // ignore enemies and self; only link the same type of C3
                if (entity.isEnemyOf(other) || entity.equals(other)
                        || (entity.hasC3i() != other.hasC3i())
                        || (entity.hasNavalC3() != other.hasNavalC3())
                        || (entity.hasNovaCEWS() != other.hasNovaCEWS())
                        || !other.hasAnyC3System() || other.hasC3S()) {
                    continue;
                }
                // maximum depth of a c3 network is 2 levels.
                Entity eCompanyMaster = other.getC3Master();
                if ((eCompanyMaster != null) && (eCompanyMaster.getC3Master() != eCompanyMaster)) {
                    continue;
                }
                int nodes = other.calculateFreeC3Nodes();
                if (other.hasC3MM() && entity.hasC3M() && other.C3MasterIs(other)) {
                    nodes = other.calculateFreeC3MNodes();
                }
                if (entity.C3MasterIs(other)) {
                    nodes++;
                }
                if ((entity.hasNhC3()) && entity.onSameC3NetworkAs(other)) {
                    nodes++;
                }

                if (other.hasNhC3()) {
                    // Don't add the following checks to the line above
                    if (!entity.onSameC3NetworkAs(other) && !usedNetIds.contains(other.getC3NetId())) {
                        String item = "<HTML>Join " + other.getShortNameRaw() + idString(game, other.getId());
                        item += " (" + other.getC3NetId() + ")";
                        item += (nodes == 0 ? " - full" : " - " + nodes + " free spots");
                        menu.add(menuItem(item, LMP_C3JOIN + "|" + other.getId() + enToken(entities), nodes != 0,
                                listener));
                        usedNetIds.add(other.getC3NetId());
                    }

                } else if (other.isC3CompanyCommander() && other.hasC3MM()) {
                    String item = "<HTML>Connect to " + other.getShortNameRaw() + idString(game, other.getId());
                    item += " (" + other.getC3NetId() + ")";
                    item += (nodes == 0 ? " - full" : " - " + nodes + " free spots");
                    menu.add(menuItem(item, LMP_C3CONNECT + "|" + other.getId() + enToken(entities), nodes != 0,
                            listener));

                } else if (other.isC3CompanyCommander() == entity.hasC3M()
                        && !entity.isC3CompanyCommander()) {
                    String item = "<HTML>Connect to " + other.getShortNameRaw() + idString(game, other.getId());
                    item += " (" + other.getC3NetId() + ")";
                    if (entity.C3MasterIs(other)) {
                        item += " - already connected";
                        nodes = 0;
                    } else {
                        item += (nodes == 0 ? " - full" : " - " + nodes + " free spots");
                    }
                    menu.add(menuItem(item, LMP_C3CONNECT + "|" + other.getId() + enToken(entities), nodes != 0,
                            listener));
                }
            }
        }
        menu.setEnabled(enabled && menu.getItemCount() > 0);
        return menu;
    }


    private void configureAndShowPopupMenu(Entity selectedEntity, MouseEvent e) {
        createPopupMenu();
        rowPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Opens the CustomMekDialog for the given entity.
     * @param entity The entity to configure.
     */
    private void openEntityConfiguration(Entity entity) {
        CustomMekDialog cmd = new CustomMekDialog(instance, client, List.of(entity), true, false);
        cmd.setSize(new Dimension(GUIPreferences.getInstance().getCustomUnitWidth(),
                GUIPreferences.getInstance().getCustomUnitHeight()));
        cmd.refreshOptions();
        cmd.refreshQuirks();
        cmd.refreshPartReps();
        cmd.setTitle(entity.getShortName());
        cmd.setVisible(true);
        // Update table in case of BV or Pilot changes
        updateTableAndTotal();
        MegaMekLabTabbedUI.refreshEntity(entity);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            return;
        }
        updateTableAndTotal();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        StringTokenizer st = new StringTokenizer(e.getActionCommand(), "|");
        String command = st.nextToken();
        String info = st.nextToken();
        Set<Entity> entities = LobbyUtility.getEntities(game, st.nextToken());
        System.out.println(e.getActionCommand());
        System.out.println(entities);
        try {
        switch (command) {
            case LMP_C3DISCONNECT:
                C3Util.disconnectFromNetwork(game, forceList);
                break;

            case LMP_C3CM:
                C3Util.setCompanyMaster(forceList);
                break;

            case LMP_C3LM:
                C3Util.setLanceMaster(entities);
                break;

            case LMP_C3JOIN:
                int master = Integer.parseInt(info);
                C3Util.joinNh(game, entities, master, false);
                break;

            case LMP_C3CONNECT:
                master = Integer.parseInt(info);
                C3Util.connect(game, entities, master, false);
                break;

            case LMP_C3FORMC3:
                master = Integer.parseInt(info);
                C3Util.connect(game, entities, master, true);
                break;

            case LMP_C3FORMNHC3:
                master = Integer.parseInt(info);
                C3Util.joinNh(game, entities, master, true);
                break;
        }
        } catch (MissingC3MException missing) {
            LobbyErrors.showOnlyC3M(this);
        } catch (MismatchingC3MException mismatch) {
            LobbyErrors.showSameC3(this);
        } catch (C3CapacityException capacity) {
            LobbyErrors.showExceedC3Capacity(this);
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
