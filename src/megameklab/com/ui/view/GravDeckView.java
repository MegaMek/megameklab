/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import megamek.common.Jumpship;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAdvancedAerospace;
import megameklab.com.ui.view.listeners.AdvancedAeroBuildListener;

/**
 * Sets number and sizes of gravity decks on advanced aerospace units.
 * 
 * @author Neoancient
 *
 */
public class GravDeckView extends BuildView implements ActionListener, TableModelListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -956614936951473086L;
    
    private final List<AdvancedAeroBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(AdvancedAeroBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(AdvancedAeroBuildListener l) {
        listeners.remove(l);
    }

    private final static String ACTION_ADD = "ADD";
    private final static String ACTION_REMOVE = "REMOVE";
    
    private final GravDeckTableModel model = new GravDeckTableModel();
    private final JTable tblGravDecks = new JTable(model);
    private final JButton btnAdd = new JButton();
    private final JButton btnRemove = new JButton();
    
    private int maxDecks;
    
    public GravDeckView() {
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        setLayout(new BorderLayout());
        add(new JScrollPane(tblGravDecks), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        btnAdd.setText(resourceMap.getString("GravDeckView.btnAdd.text"));
        btnAdd.setToolTipText(resourceMap.getString("GravDeckView.btnAdd.tooltip"));
        btnAdd.setActionCommand(ACTION_ADD);
        btnAdd.addActionListener(this);
        btnPanel.add(btnAdd);
        btnRemove.setText(resourceMap.getString("GravDeckView.btnRemove.text"));
        btnRemove.setToolTipText(resourceMap.getString("GravDeckView.btnRemove.tooltip"));
        btnRemove.setActionCommand(ACTION_REMOVE);
        btnRemove.addActionListener(this);
        btnPanel.add(btnRemove);
        add(btnPanel, BorderLayout.NORTH);
        
        model.addTableModelListener(this);
        tblGravDecks.getSelectionModel().addListSelectionListener(ev -> updateButtons());
        tblGravDecks.setPreferredScrollableViewportSize(new Dimension(400, 72));
        tblGravDecks.setToolTipText(resourceMap.getString("GravDeckView.tblGravDecks.tooltip")); //$NON-NLS-1$
    }
    
    public void setFromEntity(Jumpship ship) {
        model.setData(ship.getGravDecks());
        model.setMaxSize(TestAdvancedAerospace.getMaxGravDeckDiameter(ship));
        setMaxDecks(TestAdvancedAerospace.getMaxGravDecks(ship));
    }
    
    public void setMaxDecks(int max) {
        maxDecks = max;
        updateButtons();
    }
    
    private void updateButtons() {
        btnAdd.setEnabled(model.getData().size() < maxDecks);
        btnRemove.setEnabled((model.getData().size() > 0)
                && (tblGravDecks.getSelectedRow() >= 0));
    }
    
    public void actionPerformed(ActionEvent ev) {
        if (ev.getActionCommand().equals(ACTION_ADD)) {
            model.addDeck();
        } else if (ev.getActionCommand().equals(ACTION_REMOVE)) {
            int selected = tblGravDecks.getSelectedRow();
            if (selected >= 0) {
                model.removeDeck(tblGravDecks.convertRowIndexToModel(selected));
            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        listeners.forEach(l -> l.gravDecksChanged(model.getData()));
        updateButtons();
    }

    private static class GravDeckTableModel extends AbstractTableModel {
        
        /**
         * 
         */
        private static final long serialVersionUID = 2769156811527423574L;
        
        final static int COL_DIAMETER = 0;
        final static int COL_SIZE     = 1;
        final static int COL_TONNAGE  = 2;
        final static int NUM_COLS     = 3;
        
        private final String[] colNames;
        private List<Integer> deckSizes = new ArrayList<>();
        private int maxSize = 250;
        
        GravDeckTableModel() {
            ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
            colNames = resourceMap.getString("GravDeckView.columnNames.values").split(","); //$NON-NLS-1$    
        }

        void setData(List<Integer> data) {
            deckSizes.clear();
            deckSizes.addAll(data);
            fireTableDataChanged();
        }
        
        List<Integer> getData() {
            return deckSizes;
        }
        
        void setMaxSize(int size) {
            maxSize = size;
        }
        
        void addDeck() {
            deckSizes.add(100);
            fireTableDataChanged();
        }
        
        void removeDeck(int row) {
            deckSizes.remove(row);
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return deckSizes.size();
        }

        @Override
        public String getColumnName(int column) {
            return colNames[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == COL_DIAMETER;
        }

        @Override
        public int getColumnCount() {
            return NUM_COLS;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if ((columnIndex == COL_DIAMETER) && (rowIndex >= 0) && (rowIndex < deckSizes.size())) {
                try {
                    int newSize = Integer.parseInt((String) aValue);
                    if ((newSize > 0) && (newSize <= maxSize)) {
                        deckSizes.set(rowIndex, newSize);
                    }
                } catch (NumberFormatException ex) {
                    // Do nothing; revert to prior value
                } finally {
                    fireTableDataChanged();
                }
            } else {
                super.setValueAt(aValue, rowIndex, columnIndex);
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case COL_DIAMETER:
                    return Integer.toString(deckSizes.get(rowIndex));
                case COL_SIZE:
                    if (deckSizes.get(rowIndex) < Jumpship.GRAV_DECK_STANDARD_MAX) {
                        return "Standard";
                    } else if (deckSizes.get(rowIndex) <= Jumpship.GRAV_DECK_LARGE_MAX) {
                        return "Large";
                    } else {
                        return "Huge";
                    }
                case COL_TONNAGE:
                    if (deckSizes.get(rowIndex) < Jumpship.GRAV_DECK_STANDARD_MAX) {
                        return "50";
                    } else if (deckSizes.get(rowIndex) <= Jumpship.GRAV_DECK_LARGE_MAX) {
                        return "100";
                    } else {
                        return "500";
                    }
            }
            return null;
        }
        
    }
}
