/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import static megamek.client.ui.clientGUI.ClientGUI.CG_FILEPATH_MUL;
import static megamek.client.ui.util.UIUtil.menuItem;
import static megamek.common.util.CollectionUtil.anyOneElement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import megamek.client.Client;
import megamek.client.ui.Messages;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.dialogs.UnitEditorDialog;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.dialogs.abstractDialogs.BVDisplayDialog;
import megamek.client.ui.dialogs.customMek.CustomMekDialog;
import megamek.client.ui.panels.phaseDisplay.lobby.LobbyErrors;
import megamek.client.ui.panels.phaseDisplay.lobby.LobbyUtility;
import megamek.client.ui.util.UIUtil;
import megamek.common.game.Game;
import megamek.common.interfaces.IEntityRemovalConditions;
import megamek.common.loaders.MekFileParser;
import megamek.common.preference.PreferenceManager;
import megamek.common.units.Crew;
import megamek.common.units.Entity;
import megamek.common.units.EntityListFile;
import megamek.common.util.C3Util;
import megamek.common.util.C3Util.C3CapacityException;
import megamek.common.util.C3Util.MismatchingC3MException;
import megamek.common.util.C3Util.MissingC3MException;
import megamek.logging.MMLogger;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.util.Strings;

public class ForceBuildUI extends JFrame implements ListSelectionListener, ActionListener {
    private static final MMLogger logger = MMLogger.create(ForceBuildUI.class);
    private final ResourceBundle menuResources = ResourceBundle.getBundle("megameklab.resources.Menu");
    private static final ResourceBundle dialogResources = ResourceBundle.getBundle("megameklab.resources.Dialogs");

    private static ForceBuildUI instance; // Singleton instance

    private final ArrayList<Entity> forceList = new ArrayList<>();
    private final MMLFileChooser loadUnitFileChooser = new MMLFileChooser();
    private JTable entityTable;
    private DefaultTableModel tableModel;
    private JLabel totalBVLabel;
    private JScrollPane scrollPane;
    private final JPopupMenu rowPopupMenu = new JPopupMenu();

    private final String mulFileName = null;
    private final Client client = UnitUtil.getDummyClient();

    private static final int COL_REMOVE = 0;
    private static final int COL_NAME = 1;
    private static final int COL_GUNNERY = 2;
    private static final int COL_PILOTING = 3;
    private static final int COL_BV = 4;

    static final String LMP_C3DISCONNECT = "C3DISCONNECT";
    static final String LMP_C3CONNECT = "C3CONNECT";
    static final String LMP_C3JOIN = "C3JOIN";
    static final String LMP_C3_FORM_NHC3 = "C3FORMNHC3";
    static final String LMP_C3_FORM_C3 = "C3FORMC3";
    static final String LMP_C3LM = "C3LM";
    static final String LMP_C3CM = "C3CM";
    private static final String NO_INFO = "|-1";

    private static final Integer[] SKILL_LEVELS = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

    // Private constructor for Singleton
    private ForceBuildUI() {
        super();
        setTitle(dialogResources.getString("ForceBuildDialog.windowName.text"));
        setMinimumSize(new Dimension(300, 200));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createCenterPane();
        packWindow();
        setLocationRelativeTo(null);
        CConfig.getForceBuildPosition().ifPresent(this::setLocation);
        loadUnitFileChooser.setDialogTitle(menuResources.getString("dialog.chooseUnit.title"));
        loadUnitFileChooser.setFileFilter(new FileNameExtensionFilter("Unit files", "mtf", "blk"));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                final boolean willTerminate = !MegaMekLabTabbedUI.isOpen() &&
                      (!StartupGUI.hasInstance() ||
                            !StartupGUI.getInstance().isShowing());
                if (willTerminate) {
                    if (CConfig.getBooleanParam(CConfig.MISC_APPLICATION_EXIT_PROMPT) && noTabsOpenExitPrompt()) {
                        System.exit(0);
                    }
                } else {
                    setVisible(false);
                }
            }
        });
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
    }

    private boolean noTabsOpenExitPrompt() {
        if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS)) {
            return true;
        }
        int exitPrompt = JOptionPane.showConfirmDialog(null,
              "Would you like to exit MegaMekLab?",
              "Confirm Close",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE);
        return exitPrompt == JOptionPane.YES_OPTION;
    }

    /**
     * Gets the singleton instance of the ForceBuildWindow. Creates the instance if it doesn't exist.
     *
     * @return The singleton ForceBuildWindow instance.
     */
    public static synchronized ForceBuildUI getInstance() {
        if (instance == null) {
            instance = new ForceBuildUI();
        }
        return instance;
    }

    /**
     * Verify if the singleton instance of ForceBuildWindow exists.
     *
     * @return The singleton ForceBuildWindow instance.
     */
    public static synchronized boolean hasInstance() {
        return instance != null;
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
     * Gets the size of the force list.
     *
     */
    public static synchronized int getForceSize() {
        if (instance == null) {
            return 0;
        }
        return instance.forceList.size();
    }

    public synchronized DefaultTableModel getTableModel() {
        return instance.tableModel;
    }

    public static synchronized ResourceBundle getDialogResources() {
        return dialogResources;
    }

    /**
     * Gets/creates the singleton instance and ensures it's visible.
     */
    public static synchronized void showWindow() {
        ForceBuildUI view = getInstance();
        view.setVisible(true);
    }

    public Game getGame() {
        return client.getGame();
    }

    private void packWindow() {
        int rowHeight = entityTable.getRowHeight();
        int headerHeight = entityTable.getTableHeader().getPreferredSize().height;
        int tableHeight = Math.max(50, rowHeight * entityTable.getRowCount() + headerHeight + 5);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWindowHeight = (int) (screenSize.height * 0.85);

        Dimension tablePrefSize = entityTable.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(Math.max(300, tablePrefSize.width + 20),
              Math.min(tableHeight + 20, maxWindowHeight - 100)));

        pack();

        if (getHeight() > maxWindowHeight) {
            setSize(getWidth(), maxWindowHeight);
        }
    }

    public void clear() {
        forceList.clear();
        tableModel.setRowCount(0);
        totalBVLabel.setText("Total BV: 0");
        packWindow();
    }

    /**
     * Adds an entity to the force list and updates the table.
     */
    public void addEntity(Entity entityToAdd) {
        int foundIndex = -1;
        for (int i = 0; i < forceList.size(); i++) {
            if (forceList.get(i) == entityToAdd) {
                foundIndex = i;
                break;
            }
        }
        Entity entity;
        // Handle duplicates
        if (foundIndex >= 0) {
            int choicePrompt = JOptionPane.showConfirmDialog(null,
                  dialogResources.getString("ForceBuildDialog.duplicateDialog.body.text"),
                  dialogResources.getString("ForceBuildDialog.duplicateDialog.title.text"),
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE);
            if (choicePrompt != JOptionPane.YES_OPTION) {
                final int foundIndexFinal = foundIndex;
                SwingUtilities.invokeLater(() -> {
                    entityTable.setRowSelectionInterval(foundIndexFinal, foundIndexFinal);
                    entityTable.scrollRectToVisible(entityTable.getCellRect(foundIndexFinal, 0, true));
                    this.toFront();
                    this.requestFocus();
                });
                return;
            }
            entity = UnitUtil.cloneUnit(entityToAdd);
            if (entity != null) {
                UnitUtil.resetUnit(entity);
                entity.setCrew(new Crew(entity.defaultCrewType()));
            }
        } else {
            entity = entityToAdd;
        }

        if (entity != null) {
            if (entity.getOwner() == null) {
                UnitUtil.updateLoadedUnit(entity);
            }

            if (entity.getCrew() == null) {
                entity.setCrew(new Crew(entity.defaultCrewType()));
            }

            entity.getCrew().setName("", 0);

            if (entity.getId() == -1) {
                entity.setId(client.getGame().getNextEntityId());
            }
            client.getGame().addEntity(entity, false);
            forceList.add(entity);
            C3Util.wireC3(client.getGame(), entity);
            final int newRowIndex = forceList.size() - 1;
            rebuildTable();
            packWindow();
            SwingUtilities.invokeLater(() -> {
                entityTable.setRowSelectionInterval(newRowIndex, newRowIndex);
                entityTable.scrollRectToVisible(entityTable.getCellRect(newRowIndex, 0, true));
            });
        }
    }

    // Instance method to remove an entity
    public void removeEntity(Entity entity) {
        for (int i = 0; i < forceList.size(); i++) {
            if (forceList.get(i) == entity) {
                removeEntityById(i);
                return;
            }
        }
    }

    // Instance method to remove an entity
    public void removeEntityById(int index) {
        if (index >= 0 && index < forceList.size()) {
            tableModel.removeRow(index);
            Entity entity = forceList.remove(index);
            C3Util.disconnectFromNetwork(client.getGame(), List.of(entity));
            client.getGame().removeEntity(entity.getId(), IEntityRemovalConditions.REMOVE_UNKNOWN);
            entity.setCrew(new Crew(entity.defaultCrewType()));
            refreshTableContent();
        }
    }

    // Instance method to remove all entities
    public void removeAllEntities() {
        for (int index = forceList.size() - 1; index >= 0; index--) {
            tableModel.removeRow(index);
            Entity entity = forceList.remove(index);
            C3Util.disconnectFromNetwork(client.getGame(), List.of(entity));
            client.getGame().removeEntity(entity.getId(), IEntityRemovalConditions.REMOVE_UNKNOWN);
            entity.setCrew(new Crew(entity.defaultCrewType()));
        }
        refreshTableContent();
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
            instance.rebuildTable();
            instance.packWindow();
        }
    }

    private void refreshTableContent() {
        int totalBV = 0;
        for (int i = 0; i < forceList.size(); i++) {
            Entity entity = forceList.get(i);
            int bv = entity.calculateBattleValue();
            Integer gunnery = null;
            Integer piloting = null;
            int gSkill = entity.getCrew().getGunnery();
            int pSkill = entity.getCrew().getPiloting();
            if (gSkill >= 0) {
                gunnery = gSkill;
            }
            if (pSkill >= 0) {
                piloting = pSkill;
            }
            tableModel.setValueAt(UnitFormatter.getCell(entity), i, COL_NAME);
            tableModel.setValueAt(gunnery, i, COL_GUNNERY);
            tableModel.setValueAt(piloting, i, COL_PILOTING);
            tableModel.setValueAt(bv, i, COL_BV);
            totalBV += bv;
        }
        totalBVLabel.setText("Total BV: " + totalBV);
    }

    void rebuildTable() {
        int selectedRow = entityTable.getSelectedRow();
        int scrollValue = scrollPane.getVerticalScrollBar().getValue();
        // Clear existing rows
        tableModel.setRowCount(0);

        int totalBV = 0;
        for (Entity entity : forceList) {
            int bv = entity.calculateBattleValue();
            Integer gunnery = null;
            Integer piloting = null;

            int gSkill = entity.getCrew().getGunnery();
            int pSkill = entity.getCrew().getPiloting();
            if (gSkill >= 0) {
                gunnery = gSkill;
            }
            if (pSkill >= 0) {
                piloting = pSkill;
            }

            tableModel.addRow(new Object[] { UIManager.getIcon("InternalFrame.closeIcon"),
                                             UnitFormatter.getCell(entity), gunnery, piloting, bv });
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
     */
    public ArrayList<Entity> getAllEntities() {
        return forceList;
    }

    private void showPopup(Component component, int x, int y) {
        List<Entity> selectedEntities = new ArrayList<>();
        int[] selection = entityTable.getSelectedRows();
        if (selection.length == 0) {
            return;
        }
        for (int i : selection) {
            if (i >= 0 && i < forceList.size()) {
                Entity entity = forceList.get(i);
                selectedEntities.add(entity);
            }
        }
        populatePopupMenu(selectedEntities);
        rowPopupMenu.show(component, x, y);
    }

    private void populatePopupMenu(List<Entity> selectedEntities) {
        rowPopupMenu.removeAll();

        // --- Open Editor ---
        JMenuItem viewItem = new JMenuItem(menuResources.getString("ForceBuildUI.popup.openEditor.text"));
        Font currentFont = viewItem.getFont();
        viewItem.setFont(currentFont.deriveFont(Font.BOLD));
        viewItem.setMnemonic(KeyEvent.VK_O);
        viewItem.addActionListener(e -> openEntityInEditor(selectedEntities.get(0)));
        rowPopupMenu.add(viewItem);

        // --- Edit Pilot/Equipment/Ammo ---
        JMenuItem editItem = new JMenuItem(menuResources.getString("ForceBuildUI.popup.editPilotEquip.text"));
        editItem.setMnemonic(KeyEvent.VK_E);
        editItem.addActionListener(e -> openEntityConfiguration(selectedEntities.get(0)));
        rowPopupMenu.add(editItem);


        // --- Edit Damage ---
        JMenuItem editDamage = new JMenuItem(menuResources.getString("ForceBuildUI.popup.editDamage.text"));
        editDamage.setMnemonic(KeyEvent.VK_D);
        editDamage.addActionListener(e -> {
            Entity entity = selectedEntities.get(0);
            UnitEditorDialog med = new UnitEditorDialog(null, entity);
            med.setVisible(true);
            med.dispose();
            MegaMekLabTabbedUI.refreshEntity(entity);
        });
        rowPopupMenu.add(editDamage);

        // --- C3 Menu ---
        rowPopupMenu.add(c3Menu(true, selectedEntities, client, instance));

        // --- BV Calculations --
        final JMenuItem miCurrentUnitBVBreakdown = new JMenuItem(menuResources.getString(
              "ForceBuildUI.popup.BVBreakdown.text"));
        miCurrentUnitBVBreakdown.setName("miCurrentUnitBVBreakdown");
        miCurrentUnitBVBreakdown.setMnemonic(KeyEvent.VK_U);
        miCurrentUnitBVBreakdown.addActionListener(evt -> new BVDisplayDialog(null, selectedEntities.get(0)).setVisible(
              true));
        rowPopupMenu.add(miCurrentUnitBVBreakdown);

        // --- Duplicate Item ---
        JMenuItem duplicateItem = new JMenuItem(menuResources.getString("ForceBuildUI.popup.duplicate.text"));
        duplicateItem.addActionListener(e -> {
            for (Entity entity : selectedEntities) {
                Entity clonedEntity = UnitUtil.cloneUnit(entity);
                if (clonedEntity != null) {
                    UnitUtil.resetUnit(clonedEntity);
                    clonedEntity.setCrew(new Crew(entity.defaultCrewType()));
                    addEntity(clonedEntity);
                }
            }
        });
        rowPopupMenu.add(duplicateItem);

        // --- Delete Item ---
        rowPopupMenu.addSeparator();
        JMenuItem removeItem = new JMenuItem(menuResources.getString("ForceBuildUI.popup.remove.text"));
        removeItem.addActionListener(e -> {
            for (Entity entity : selectedEntities) {
                removeEntity(entity);
            }
        });
        rowPopupMenu.add(removeItem);
        // --- Delete All Item ---
        JMenuItem removeAllItem = new JMenuItem(menuResources.getString("ForceBuildUI.popup.removeAll.text"));
        removeAllItem.addActionListener(e -> {
            if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS) ||
                  (JOptionPane.YES_OPTION ==
                        JOptionPane.showConfirmDialog(null,
                              dialogResources.getString("ForceBuildDialog.removeAllDialog.body.text"),
                              dialogResources.getString("ForceBuildDialog.removeAllDialog.title.text"),
                              JOptionPane.YES_NO_OPTION,
                              JOptionPane.WARNING_MESSAGE))) {
                removeAllEntities();
            }
        });
        rowPopupMenu.add(removeAllItem);
    }

    private void createCenterPane() {
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnNames = { "", "Name", "Gunnery", "Piloting", "BV" };
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
                        if (aValue instanceof Integer skillValue) {
                            if (column == COL_GUNNERY) {
                                entity.getCrew().setGunnery(skillValue, entity.getCrew().getCrewType().getGunnerPos());
                            } else { // Must be COL_PILOTING
                                entity.getCrew().setPiloting(skillValue, entity.getCrew().getCrewType().getPilotPos());
                            }
                            needsBvUpdate = true;
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
        entityTable.setTransferHandler(new ForceListTransferHandler(this));
        entityTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        entityTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int col = entityTable.columnAtPoint(e.getPoint());
                    if (col == COL_REMOVE) {
                        return;
                    }
                    int row = entityTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < forceList.size()) {
                        openEntityInEditor(forceList.get(row));
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
                    int col = entityTable.columnAtPoint(e.getPoint());
                    if (col == COL_REMOVE) {
                        return;
                    }
                    if (entityTable.getSelectedRowCount() <= 1) {
                        // Select row under cursor if no selection exists or there is only 1 selection (this allows to reselect with right click)
                        int row = entityTable.rowAtPoint(e.getPoint());
                        if (row >= 0) {
                            entityTable.setRowSelectionInterval(row, row);
                        } else {
                            return;
                        }
                    }
                    if (entityTable.getSelectedRowCount() == 0) {
                        return;
                    }
                    ForceBuildUI.this.showPopup(e.getComponent(), e.getX(), e.getY());
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
        removeColumn.setCellEditor(new ButtonEditor(this));

        // Name Column
        TableColumn nameColumn = columnModel.getColumn(COL_NAME);
        nameColumn.setPreferredWidth(250);
        nameColumn.setMinWidth(200);

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
        final Border originalOuterBorder = scrollPane.getBorder();
        final int highlightThickness = 3;
        final Border highlightInnerBorder = BorderFactory.createLineBorder(Color.YELLOW,
              highlightThickness); // Define highlight visual
        final Border baseBorder = getBaseBorder(highlightThickness, originalOuterBorder);

        // Highlighted border: Original L&F border (if any) + actual highlight
        final Border activeHighlightBorder = (originalOuterBorder != null) ?
              BorderFactory.createCompoundBorder(originalOuterBorder,
                    highlightInnerBorder) :
              highlightInnerBorder; // Use only highlight if no original border

        scrollPane.setBorder(baseBorder); // Set initial border which includes padding

        // --- DnD Border Highlighting ---
        try {
            entityTable.getDropTarget().addDropTargetListener(new DropTargetAdapter() {

                private boolean isMulDrag(DropTargetDragEvent dropTargetDragEvent) {
                    return dropTargetDragEvent.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
                }

                @Override
                public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
                    if (isMulDrag(dropTargetDragEvent)) {
                        scrollPane.setBorder(activeHighlightBorder);
                    } else {
                        scrollPane.setBorder(baseBorder);
                    }
                    scrollPane.repaint();
                }

                @Override
                public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
                    if (isMulDrag(dropTargetDragEvent)) {
                        if (scrollPane.getBorder() != activeHighlightBorder) {
                            scrollPane.setBorder(activeHighlightBorder);
                            scrollPane.repaint();
                        }
                    } else {
                        if (scrollPane.getBorder() != baseBorder) {
                            scrollPane.setBorder(baseBorder);
                            scrollPane.repaint();
                        }
                    }
                }

                @Override
                public void dragExit(DropTargetEvent dte) {
                    scrollPane.setBorder(baseBorder);
                    scrollPane.repaint();
                }

                @Override
                public void drop(DropTargetDropEvent dropTargetDropEvent) {
                    scrollPane.setBorder(baseBorder);
                    scrollPane.repaint();
                }
            });
        } catch (TooManyListenersException e) {
            logger.error("Could not add custom DropTargetListener for border highlighting.", e);
        }
        // --- End DnD Border Highlighting ---

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // --- Bottom Panel ---
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        // Print Force Button
        JButton printButton = new JButton(dialogResources.getString("ForceBuildDialog.print.text"));
        printButton.setToolTipText(dialogResources.getString("ForceBuildDialog.print.toolTipText"));
        printButton.addActionListener(e -> new PrintQueueDialog(instance,
              false,
              getAllEntities(),
              false,
              "").setVisible(true));
        buttonPanel.add(printButton, BorderLayout.WEST);

        // Print Force Button
        JButton pdfExportbutton = new JButton(dialogResources.getString("ForceBuildDialog.exportToPDF.text"));
        pdfExportbutton.setToolTipText(dialogResources.getString("ForceBuildDialog.exportToPDF.toolTipText"));
        pdfExportbutton.addActionListener(e -> new PrintQueueDialog(instance,
              true,
              getAllEntities(),
              false,
              "").setVisible(true));
        buttonPanel.add(pdfExportbutton, BorderLayout.WEST);

        // Export MUL
        Icon saveIcon = UIManager.getIcon("FileView.floppyDriveIcon");
        JButton mulExportButton = new JButton(saveIcon);
        mulExportButton.setToolTipText(dialogResources.getString("ForceBuildDialog.saveToMul.toolTipText"));
        mulExportButton.setFocusable(false);
        mulExportButton.addActionListener(e -> exportAsMul());
        buttonPanel.add(mulExportButton, BorderLayout.WEST);

        // Load from Cache
        Icon openIcon = UIManager.getIcon("Tree.openIcon");
        JButton loadFromCacheButton = new JButton(openIcon);
        loadFromCacheButton.setToolTipText(dialogResources.getString("ForceBuildDialog.loadFromCache.toolTipText"));
        loadFromCacheButton.setFocusable(false);
        loadFromCacheButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Left click - directly load from cache
                    selectAndLoadUnitFromCache();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Right click - show popup menu
                    JPopupMenu menu = createLoadUnitPopupMenu();
                    Point location = new Point(5, loadFromCacheButton.getHeight());
                    menu.show(loadFromCacheButton, location.x, location.y);
                }
            }
        });

        buttonPanel.add(loadFromCacheButton, BorderLayout.WEST);

        bottomPanel.add(buttonPanel, BorderLayout.WEST);

        // Total BV Label
        totalBVLabel = new JLabel("Total BV: 0", SwingConstants.RIGHT);
        totalBVLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        bottomPanel.add(totalBVLabel, BorderLayout.EAST);

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);


        rebuildTable();

        add(centerPanel, BorderLayout.CENTER);
    }

    private static Border getBaseBorder(int highlightThickness, Border originalOuterBorder) {
        final Border paddingInnerBorder = BorderFactory.createEmptyBorder(highlightThickness,
              highlightThickness,
              highlightThickness,
              highlightThickness); // Padding matching highlight thickness

        // Base border: Original L&F border (if any) + padding for highlight space
        // Use only padding if no original border
        return (originalOuterBorder != null) ?
              BorderFactory.createCompoundBorder(originalOuterBorder, paddingInnerBorder) :
              paddingInnerBorder;
    }

    /**
     * Creates the popup menu with options for loading units
     */
    private JPopupMenu createLoadUnitPopupMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem fromCache = new JMenuItem("Load from cache");
        fromCache.addActionListener(e -> selectAndLoadUnitFromCache());
        menu.add(fromCache);

        JMenuItem fromFile = new JMenuItem("Load from file");
        fromFile.addActionListener(e -> selectAndLoadUnitFromFile());
        menu.add(fromFile);

        return menu;
    }


    private void addEntities(List<Entity> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        for (var entity : entities) {
            if (entity != null) {
                addEntity(entity);
            }
        }
        rebuildTable();
    }

    public void selectAndLoadUnitFromCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog, dialog -> {
            List<Entity> selectedEntities = dialog.getChosenEntities();
            warnOnInvalid(selectedEntities);
            addEntities(selectedEntities);
        });
        try {
            List<Entity> selectedEntities = viewer.getChosenEntities();
            if (selectedEntities != null && !selectedEntities.isEmpty()) {
                warnOnInvalid(selectedEntities);
                addEntities(selectedEntities);
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    public void selectAndLoadUnitFromFile() {
        loadUnitFileChooser.setCurrentDirectory(new File(CConfig.getParam(CConfig.FILE_LAST_DIRECTORY)));
        int result = loadUnitFileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = loadUnitFileChooser.getSelectedFile();
        Entity loadedUnit;
        try {
            loadedUnit = new MekFileParser(selectedFile).getEntity();
            if (loadedUnit == null) {
                throw new Exception("File is empty or invalid.");
            }
            warnOnInvalid(loadedUnit);
            CConfig.setMostRecentFile(selectedFile.toString());
        } catch (Exception ex) {
            PopupMessages.showFileReadError(null, selectedFile.toString(), ex.getMessage());
            return;
        }
        addEntity(loadedUnit);
    }

    private void warnOnInvalid(List<Entity> entity) {
        for (var e : entity) {
            warnOnInvalid(e);
        }
    }

    private void warnOnInvalid(Entity entity) {
        var report = UnitUtil.validateUnit(entity);
        if (!report.isBlank()) {
            PopupMessages.showUnitInvalidWarning(null, report);
        }
    }

    private void openEntityInEditor(Entity entity) {
        MegaMekLabTabbedUI.showEditorForEntity(entity);
        getInstance().toFront();
        getInstance().requestFocus();
    }

    /**
     * Returns a command string token containing the IDs of the given entities and a leading | E.g. |2,14,44,22
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
    private static JMenu c3Menu(boolean enabled, List<Entity> entities, Client client, ActionListener listener) {
        JMenu menu = new JMenu("C3");
        if (entities.stream().anyMatch(Entity::hasAnyC3System)) {

            menu.add(menuItem("Disconnect", LMP_C3DISCONNECT + NO_INFO + enToken(entities), enabled, listener));

            if (entities.stream().anyMatch(e -> e.hasC3MM() || e.hasC3M())) {
                boolean allCM = entities.stream().allMatch(Entity::isC3CompanyCommander);
                menu.add(menuItem("Set as C3 Company Master",
                      LMP_C3CM + NO_INFO + enToken(entities),
                      !allCM,
                      listener));
                boolean allLM = entities.stream().allMatch(Entity::isC3IndependentMaster);
                menu.add(menuItem("Set as C3 Lance Master", LMP_C3LM + NO_INFO + enToken(entities), !allLM, listener));
            }

            // Special treatment if exactly a C3SSSM is selected
            if (entities.size() == 4) {
                long countM = entities.stream().filter(Entity::hasC3M).count();
                long countS = entities.stream().filter(Entity::hasC3S).count();
                if (countM == 1 && countS == 3) {
                    Entity master = entities.stream().filter(Entity::hasC3M).findAny().orElseThrow();
                    menu.add(menuItem("Form C3 Lance",
                          LMP_C3_FORM_C3 + "|" + master.getId() + enToken(entities),
                          true,
                          listener));
                }
            }

            // Special treatment if a group of NhC3 is selected
            if (entities.size() > 1 && entities.size() <= 6) {
                Entity master = anyOneElement(entities);
                if (entities.stream().allMatch(e -> C3Util.sameNhC3System(master, e))) {
                    menu.add(menuItem("Form C3 Lance",
                          LMP_C3_FORM_NHC3 + "|" + master.getId() + enToken(entities),
                          true,
                          listener));
                }
            }

            Entity entity = entities.stream().filter(Entity::hasAnyC3System).findAny().orElseThrow();
            // ideally, find one slave or C3i/NC3/Nova to get some connection options
            entity = entities.stream().filter(e -> e.hasC3S() || e.hasNhC3()).findAny().orElse(entity);
            Game game = client.getGame();
            ArrayList<String> usedNetIds = new ArrayList<>();

            for (Entity other : client.getEntitiesVector()) {
                // ignore enemies and self; only link the same type of C3
                if (entity.isEnemyOf(other) ||
                      entity.equals(other) ||
                      (entity.hasC3i() != other.hasC3i()) ||
                      (entity.hasNavalC3() != other.hasNavalC3()) ||
                      (entity.hasNovaCEWS() != other.hasNovaCEWS()) ||
                      !other.hasAnyC3System() ||
                      other.hasC3S()) {
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
                        menu.add(menuItem(item,
                              LMP_C3JOIN + "|" + other.getId() + enToken(entities),
                              nodes != 0,
                              listener));
                        usedNetIds.add(other.getC3NetId());
                    }

                } else if (other.isC3CompanyCommander() && other.hasC3MM()) {
                    String item = "<HTML>Connect to " + other.getShortNameRaw() + idString(game, other.getId());
                    item += " (" + other.getC3NetId() + ")";
                    item += (nodes == 0 ? " - full" : " - " + nodes + " free spots");
                    menu.add(menuItem(item,
                          LMP_C3CONNECT + "|" + other.getId() + enToken(entities),
                          nodes != 0,
                          listener));

                } else if (other.isC3CompanyCommander() == entity.hasC3M() && !entity.isC3CompanyCommander()) {
                    String item = "<HTML>Connect to " + other.getShortNameRaw() + idString(game, other.getId());
                    item += " (" + other.getC3NetId() + ")";
                    if (entity.C3MasterIs(other)) {
                        item += " - already connected";
                        nodes = 0;
                    } else {
                        item += (nodes == 0 ? " - full" : " - " + nodes + " free spots");
                    }
                    menu.add(menuItem(item,
                          LMP_C3CONNECT + "|" + other.getId() + enToken(entities),
                          nodes != 0,
                          listener));
                }
            }
        }
        menu.setEnabled(enabled && menu.getItemCount() > 0);
        return menu;
    }

    private void exportAsMul() {
        if (forceList.isEmpty()) {
            return;
        }
        var fileChooser = new JFileChooser(".");
        fileChooser.setDialogTitle(Messages.getString("ClientGUI.saveUnitListFileDialog.title"));
        var filter = new FileNameExtensionFilter(Messages.getString("ClientGUI.descriptionMULFiles"),
              CG_FILEPATH_MUL);
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File(Strings.isNotBlank(mulFileName) ?
              mulFileName :
              forceList.get(0).getShortName() + " etc." + CG_FILEPATH_MUL));

        if (!(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) ||
              fileChooser.getSelectedFile() == null) {
            return;
        }
        File file = fileChooser.getSelectedFile();
        if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(CG_FILEPATH_MUL)) {
            file = new File(file + "." + CG_FILEPATH_MUL);
        }

        try {
            EntityListFile.saveTo(file, forceList);
        } catch (IOException e) {
            logger.errorDialog(e, "Failed to save units to file: {}", "Error", e.getMessage());
        }
    }

    /**
     * Opens the CustomMekDialog for the given entity.
     *
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
        refreshTableContent();
        MegaMekLabTabbedUI.refreshEntity(entity);

        if (cmd.isOkay() && (cmd.getStatus() != CustomMekDialog.DONE)) {
            Entity nextEnt = cmd.getNextEntity(cmd.getStatus() == CustomMekDialog.NEXT);
            cmd.dispose();
            openEntityConfiguration(nextEnt);
        } else {
            cmd.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            return;
        }
        refreshTableContent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = UnitUtil.getDummyClient().getGame();
        StringTokenizer st = new StringTokenizer(e.getActionCommand(), "|");
        String command = st.nextToken();
        String info = st.nextToken();
        Set<Entity> entities = LobbyUtility.getEntities(game, st.nextToken());
        int master = -1;
        try {
            switch (command) {
                case LMP_C3DISCONNECT:
                    C3Util.disconnectFromNetwork(game, entities);
                    break;

                case LMP_C3CM:
                    C3Util.setCompanyMaster(entities);
                    break;

                case LMP_C3LM:
                    C3Util.setLanceMaster(entities);
                    break;

                case LMP_C3JOIN:
                    master = Integer.parseInt(info);
                    C3Util.joinNh(game, entities, master, false);
                    break;

                case LMP_C3CONNECT:
                    master = Integer.parseInt(info);
                    C3Util.connect(game, entities, master, false);
                    break;

                case LMP_C3_FORM_C3:
                    master = Integer.parseInt(info);
                    C3Util.connect(game, entities, master, true);
                    break;

                case LMP_C3_FORM_NHC3:
                    master = Integer.parseInt(info);
                    C3Util.joinNh(game, entities, master, true);
                    break;
            }
            refreshTableContent();
            for (Entity entity : entities) {
                MegaMekLabTabbedUI.refreshEntity(entity);
            }
            if (master > -1) {
                Entity masterUnit = game.getEntity(master);
                if (masterUnit != null && (!entities.contains(masterUnit))) {
                    MegaMekLabTabbedUI.refreshEntity(masterUnit);
                }
            }
        } catch (MissingC3MException missing) {
            LobbyErrors.showOnlyC3M(this);
        } catch (MismatchingC3MException mismatch) {
            LobbyErrors.showSameC3(this);
        } catch (C3CapacityException capacity) {
            LobbyErrors.showExceedC3Capacity(this);
        }
    }

}
