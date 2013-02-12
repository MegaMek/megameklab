/*
 * MechSelectorDialog.java - Copyright (C) 2002,2004 Josh Yockey
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */

package megameklab.com.ui.dialog;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.Messages;
import megamek.client.ui.swing.AdvancedSearchDialog;
import megamek.client.ui.swing.MechViewPanel;
import megamek.client.ui.swing.UnitFailureDialog;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.Entity;
import megamek.common.EntityWeightClass;
import megamek.common.MechFileParser;
import megamek.common.MechSearchFilter;
import megamek.common.MechSummary;
import megamek.common.MechSummaryCache;
import megamek.common.MechView;
import megamek.common.TechConstants;
import megamek.common.UnitType;
import megamek.common.loaders.EntityLoadingException;

/**
 *
 * @author  Jay Lawson <jaylawson39 at yahoo.com>
 * This is a heavily reworked version of the original MechSelectorDialog which
 * brings up a list of units for the player to select to add to their forces.
 * The original list has been changed to a sortable table and a text filter
 * is used for advanced searching.
 */
public class UnitViewerDialog extends JDialog implements KeyListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 8144354264100884817L;

    private JButton btnSelect;
    private JButton btnClose;
    private JButton btnAdvSearch;
    private JButton btnResetSearch;
    private JComboBox comboType;
    private JComboBox comboWeight;
    private JLabel lblFilter;
    private JLabel lblImage;
    private JLabel lblType;
    private JLabel lblWeight;
    private JPanel panelFilterBtns;
    private JPanel panelSearchBtns;
    private JPanel panelOKBtns;
    private JScrollPane scrTableUnits;
    private JTable tableUnits;
    JTextField txtFilter;
    private MechViewPanel panelMekView;

    private StringBuffer searchBuffer = new StringBuffer();
    private long lastSearch = 0;
    // how long after a key is typed does a new search begin
    private final static int KEY_TIMEOUT = 1000;

    JFrame parentFrame;

    private MechSummary[] mechs;

    private Entity chosenEntity;
    private MechSummary chosenMechSummary;

    private MechTableModel unitModel;
    private MechSearchFilter searchFilter;

    private UnitLoadingDialog unitLoadingDialog;
    AdvancedSearchDialog asd;

    private TableRowSorter<MechTableModel> sorter;

    private int unitType = 0;

    /** Creates new form UnitSelectorDialog */
    public UnitViewerDialog(JFrame frame, UnitLoadingDialog uld, int type) {
        super(frame, true);
        parentFrame = frame;
        unitLoadingDialog = uld;
        unitType = type;
        unitModel = new MechTableModel();
        initComponents();
        setLocationRelativeTo(parentFrame);
        asd = new AdvancedSearchDialog(parentFrame);
        populateUnits();
        setVisible(true);
    }

    private void initComponents() {
        GridBagConstraints c;

        panelFilterBtns = new JPanel();
        panelSearchBtns = new JPanel();
        panelOKBtns = new JPanel();

        scrTableUnits = new JScrollPane();
        tableUnits = new JTable();
        tableUnits.addKeyListener(this);
        panelMekView = new MechViewPanel();

        comboType = new JComboBox();
        comboWeight = new JComboBox();
        txtFilter = new JTextField();

        btnSelect = new JButton();
        btnClose = new JButton();
        btnAdvSearch = new JButton();
        btnResetSearch = new JButton();

        lblType = new JLabel(Messages.getString("MechSelectorDialog.m_labelType"));
        lblWeight = new JLabel(Messages.getString("MechSelectorDialog.m_labelWeightClass"));
        lblFilter = new JLabel(Messages.getString("MechSelectorDialog.m_labelFilter"));
        lblImage = new JLabel();

        getContentPane().setLayout(new GridBagLayout());

        scrTableUnits.setMinimumSize(new java.awt.Dimension(500, 400));
        scrTableUnits.setPreferredSize(new java.awt.Dimension(500, 400));

        tableUnits.setModel(unitModel);
        tableUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<MechTableModel>(unitModel);
        tableUnits.setRowSorter(sorter);
        tableUnits.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                refreshUnitView();
            }
        });
        TableColumn column = null;
        for (int i = 0; i < MechTableModel.N_COL; i++) {
            column = tableUnits.getColumnModel().getColumn(i);
            if (i == MechTableModel.COL_CHASSIS) {
                column.setPreferredWidth(125);
            }
            else if((i == MechTableModel.COL_MODEL)
                || (i == MechTableModel.COL_COST)) {
                column.setPreferredWidth(75);
            }
            else if((i == MechTableModel.COL_WEIGHT)
                || (i == MechTableModel.COL_BV)) {
                column.setPreferredWidth(50);
            }
            else {
                column.setPreferredWidth(25);
            }
        }
        tableUnits.setFont(new Font("Monospaced", Font.PLAIN, 12)); //$NON-NLS-1$
        scrTableUnits.setViewportView(tableUnits);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.0;
        c.weighty = 1.0;
        getContentPane().add(scrTableUnits, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        getContentPane().add(panelMekView, c);

        panelFilterBtns.setMinimumSize(new java.awt.Dimension(300, 120));
        panelFilterBtns.setPreferredSize(new java.awt.Dimension(300, 120));
        panelFilterBtns.setLayout(new GridBagLayout());

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(lblType, c);

        DefaultComboBoxModel techModel = new DefaultComboBoxModel();
        for (int i = 0; i < TechConstants.SIZE; i++) {
            techModel.addElement(TechConstants.getLevelDisplayableName(i));
        }
        techModel.setSelectedItem(TechConstants.getLevelDisplayableName(0));
        comboType.setModel(techModel);
        comboType.setMinimumSize(new java.awt.Dimension(200, 27));
        comboType.setPreferredSize(new java.awt.Dimension(200, 27));
        comboType.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(comboType, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(lblWeight, c);

        DefaultComboBoxModel weightModel = new DefaultComboBoxModel();
        for (int i = 0; i < EntityWeightClass.SIZE; i++) {
            weightModel.addElement(EntityWeightClass.getClassName(i));
        }
        weightModel.addElement(Messages.getString("MechSelectorDialog.All")); //$NON-NLS-1$
        weightModel.setSelectedItem(EntityWeightClass.getClassName(0));
        comboWeight.setModel(weightModel);
        comboWeight.setSelectedItem(Messages.getString("MechSelectorDialog.All"));
        comboWeight.setMinimumSize(new java.awt.Dimension(200, 27));
        comboWeight.setPreferredSize(new java.awt.Dimension(200, 27));
        comboWeight.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(comboWeight, c);

        txtFilter.setText("");
        txtFilter.setMinimumSize(new java.awt.Dimension(200, 28));
        txtFilter.setPreferredSize(new java.awt.Dimension(200, 28));
        txtFilter.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                    filterUnits();
                }
                public void insertUpdate(DocumentEvent e) {
                    filterUnits();
                }
                public void removeUpdate(DocumentEvent e) {
                    filterUnits();
                }
            });
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(txtFilter, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        panelFilterBtns.add(lblFilter, c);

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText(""); // NOI18N
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 4;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panelFilterBtns.add(lblImage, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.0;
        c.insets = new java.awt.Insets(10, 10, 10, 0);
        getContentPane().add(panelFilterBtns, c);

        panelSearchBtns.setLayout(new GridBagLayout());

        btnAdvSearch.setText(Messages.getString("MechSelectorDialog.AdvSearch")); //$NON-NLS-1$
        btnAdvSearch.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panelSearchBtns.add(btnAdvSearch, c);

        btnResetSearch.setText(Messages.getString("MechSelectorDialog.Reset")); //$NON-NLS-1$
        btnResetSearch.addActionListener(this);
        btnResetSearch.setEnabled(false);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panelSearchBtns.add(btnResetSearch, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.0;
        c.insets = new java.awt.Insets(10, 10, 10, 0);
        getContentPane().add(panelSearchBtns, c);


        panelOKBtns.setLayout(new GridBagLayout());


        btnSelect.setText(Messages.getString("MechSelectorDialog.m_bPick"));
        btnSelect.addActionListener(this);
        panelOKBtns.add(btnSelect, new GridBagConstraints());

        btnClose.setText(Messages.getString("Close"));
        btnClose.addActionListener(this);
        panelOKBtns.add(btnClose, new GridBagConstraints());

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        getContentPane().add(panelOKBtns, c);

        pack();
    }

    private void select() {
        chosenMechSummary = getSelectedMechSummary();
        chosenEntity = getSelectedEntity();
        setVisible(false);
    }

    private void cancel() {
        chosenMechSummary = null;
        chosenEntity = null;
        setVisible(false);
    }

    private void filterUnits() {
        RowFilter<MechTableModel, Integer> unitTypeFilter = null;
        final int nType = comboType.getSelectedIndex();
        final int nClass = comboWeight.getSelectedIndex();
        //final int nUnit = comboUnitType.getSelectedIndex() - 1;
        //If current expression doesn't parse, don't update.
        try {
            unitTypeFilter = new RowFilter<MechTableModel,Integer>() {
                @Override
                public boolean include(Entry<? extends MechTableModel, ? extends Integer> entry) {
                    MechTableModel mechModel = entry.getModel();
                    MechSummary mech = mechModel.getMechSummary(entry.getIdentifier());
                    if (/* Weight */
                            ((nClass == EntityWeightClass.SIZE) || (mech.getWeightClass() == nClass)) &&
                            /*Technology Level*/
                            ((nType == TechConstants.T_ALL)
                                || (nType == mech.getType())
                                || ((nType == TechConstants.T_IS_TW_ALL)
                                    && ((mech.getType() <= TechConstants.T_IS_TW_NON_BOX)
                                     || (mech.getType() == TechConstants.T_INTRO_BOXSET)))
                                || ((nType == TechConstants.T_TW_ALL)
                                    && ((mech.getType() <= TechConstants.T_IS_TW_NON_BOX)
                                     || (mech.getType() <= TechConstants.T_INTRO_BOXSET)
                                     || (mech.getType() <= TechConstants.T_CLAN_TW)))
                                || ((nType == TechConstants.T_ALL_IS)
                                    && ((mech.getType() <= TechConstants.T_IS_TW_NON_BOX)
                                     || (mech.getType() == TechConstants.T_INTRO_BOXSET)
                                     || (mech.getType() == TechConstants.T_IS_ADVANCED)
                                     || (mech.getType() == TechConstants.T_IS_EXPERIMENTAL)
                                     || (mech.getType() == TechConstants.T_IS_UNOFFICIAL)))
                                || ((nType == TechConstants.T_ALL_CLAN)
                                    && ((mech.getType() == TechConstants.T_CLAN_TW)
                                     || (mech.getType() == TechConstants.T_CLAN_ADVANCED)
                                     || (mech.getType() == TechConstants.T_CLAN_EXPERIMENTAL)
                                     || (mech.getType() == TechConstants.T_CLAN_UNOFFICIAL))))
                            && ((((unitType != -1) && mech.getUnitType().equals(UnitType.getTypeName(unitType))) || (unitType == -1)))
                            /*Advanced Search*/
                            && ((searchFilter==null) || MechSearchFilter.isMatch(mech, searchFilter))) {
                        //yuck, I have to pull up a full Entity to get MechView to search in
                        //TODO: why not put mechview into the mech summary itself?
                        if(txtFilter.getText().length() > 0) {
                            String text = txtFilter.getText();
                            return mech.getName().toLowerCase().contains(text.toLowerCase());
                        }
                        return true;
                    }
                    return false;
                }
            };
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(unitTypeFilter);
    }

    private Entity getSelectedEntity() {
        MechSummary ms = getSelectedMechSummary();
        if(null == ms) {
            return null;
        }
        try {
            // For some unknown reason the base path gets screwed up after you
            // print so this sets the source file to the full path.
            Entity entity = new MechFileParser(ms.getSourceFile(), ms.getEntryName()).getEntity();
            return entity;
       } catch (EntityLoadingException ex) {
           System.out.println("Unable to load mech: " + ms.getSourceFile() + ": " + ms.getEntryName() + ": " + ex.getMessage());
           ex.printStackTrace();
           return null;
      }
    }

    public Entity getChosenEntity() {
        return chosenEntity;
    }

    public MechSummary getChosenMechSummary() {
        return chosenMechSummary;
    }

    void refreshUnitView() {
        boolean populateTextFields = true;

        Entity selectedUnit = getSelectedEntity();
        // null entity, so load a default unit.
        if (selectedUnit == null) {
            panelMekView.reset();
            lblImage.setIcon(null);
            return;
        }

        MechView mechView = null;
        try {
            mechView = new MechView(selectedUnit, false);
        } catch (Exception e) {
            e.printStackTrace();
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields && (mechView != null)) {
            panelMekView.setMech(selectedUnit);
        } else {
            panelMekView.reset();
        }

//        clientgui.loadPreviewImage(lblImage, selectedUnit, client.getLocalPlayer());
    }

    private MechSummary getSelectedMechSummary() {
        int view = tableUnits.getSelectedRow();
        if(view < 0) {
            //selection got filtered away
            return null;
        }
        int selected = tableUnits.convertRowIndexToModel(view);
        // else
        MechSummary ms = mechs[selected];
        return ms;
    }

     public void populateUnits() {
         // Loading mechs can take a while, so it will have its own thread.
         // This prevents the UI from freezing, and allows the
         // "Please wait..." dialog to behave properly on various Java VMs.
         mechs = MechSummaryCache.getInstance().getAllMechs();

         // break out if there are no units to filter
         if (mechs == null) {
             System.err.println("No units to filter!");
         } else {
             unitModel.setData(mechs);
         }
         filterUnits();

         //initialize with the units sorted alphabetically by chassis
         ArrayList<SortKey> sortlist = new ArrayList<SortKey>();
         sortlist.add(new SortKey(MechTableModel.COL_CHASSIS,SortOrder.ASCENDING));
         //sortlist.add(new RowSorter.SortKey(MechTableModel.COL_MODEL,SortOrder.ASCENDING));
         tableUnits.getRowSorter().setSortKeys(sortlist);
         ((DefaultRowSorter<?, ?>)tableUnits.getRowSorter()).sort();

         tableUnits.invalidate(); // force re-layout of window
         pack();
         //setLocation(computeDesiredLocation());

         unitLoadingDialog.setVisible(false);

         final Map<String, String> hFailedFiles = MechSummaryCache.getInstance()
                 .getFailedFiles();
         if ((hFailedFiles != null) && (hFailedFiles.size() > 0)) {
             new UnitFailureDialog(parentFrame, hFailedFiles); // self-showing
                                                                     // dialog
         }
     }

     @Override
     public void setVisible(boolean visible) {
         asd.clearValues();
         searchFilter=null;
         filterUnits();
         super.setVisible(visible);
     }


    /**
     * A table model for displaying work items
     */
    public class MechTableModel extends AbstractTableModel {

            /**
             *
             */
            private static final long serialVersionUID = -5457068129532709857L;
            private final static int COL_CHASSIS = 0;
            private final static int COL_MODEL = 1;
            private final static int COL_WEIGHT = 2;
            private final static int COL_BV = 3;
            private final static int COL_YEAR = 4;
            private final static int COL_COST = 5;
            private final static int COL_LEVEL = 6;
            private final static int N_COL = 7;

            private MechSummary[] data = new MechSummary[0];

            public int getRowCount() {
                return data.length;
            }

            public int getColumnCount() {
                return N_COL;
            }

            @Override
            public String getColumnName(int column) {
                switch(column) {
                    case COL_MODEL:
                        return "Model";
                    case COL_CHASSIS:
                        return "Chassis";
                    case COL_WEIGHT:
                        return "Weight";
                    case COL_BV:
                        return "BV";
                    case COL_YEAR:
                        return "Year";
                    case COL_COST:
                        return "Price";
                    case COL_LEVEL:
                         return "Level";
                    default:
                        return "?";
                }
            }

            @Override
            public Class<?> getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

            public MechSummary getMechSummary(int i) {
                return data[i];
            }

            //fill table with values
            public void setData(MechSummary[] ms) {
                data = ms;
                fireTableDataChanged();
            }

            public Object getValueAt(int row, int col) {
                MechSummary ms = data[row];
                if(col == COL_MODEL) {
                    return ms.getModel();
                }
                if(col == COL_CHASSIS) {
                    return ms.getChassis();
                }
                if(col == COL_WEIGHT) {
                    return ms.getTons();
                }
                if(col == COL_BV) {
                    return ms.getBV();
                }
                if(col == COL_YEAR) {
                    return ms.getYear();
                }
                if(col == COL_COST) {
                    //return NumberFormat.getInstance().format(ms.getCost());
                    return ms.getCost();
                }
                if (col == COL_LEVEL) {
                    return ms.getLevel();
                }
                return "?";
            }

    }

    public void keyReleased(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent event = new ActionEvent(btnSelect,
                    ActionEvent.ACTION_PERFORMED, ""); //$NON-NLS-1$
            actionPerformed(event);
        }
        long curTime = System.currentTimeMillis();
        if ((curTime - lastSearch) > KEY_TIMEOUT) {
            searchBuffer = new StringBuffer();
        }
        lastSearch = curTime;
        searchBuffer.append(ke.getKeyChar());
        searchFor(searchBuffer.toString().toLowerCase());
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource().equals(comboType) || ev.getSource().equals(comboWeight)) {
            filterUnits();
        } else if (ev.getSource().equals(btnSelect)) {
            select();
        } else if (ev.getSource().equals(btnClose)) {
            setVisible(false);
        } else if(ev.getSource().equals(btnAdvSearch)) {
            searchFilter = asd.showDialog();
            btnResetSearch.setEnabled(searchFilter!=null);
            //TurretFacingDialog tfd = new TurretFacingDialog(clientgui.frame, "test", "test2", (Mech)getSelectedEntity(), null, clientgui);
            //tfd.setVisible(true);
            filterUnits();
        } else if(ev.getSource().equals(btnResetSearch)) {
            asd.clearValues();
            searchFilter=null;
            btnResetSearch.setEnabled(false);
            filterUnits();
        }
    }

    private void searchFor(String search) {
        for (int i = 0; i < mechs.length; i++) {
            if (mechs[i].getName().toLowerCase().startsWith(search)) {
                int selected = tableUnits.convertRowIndexToView(i);
                if (selected > -1) {
                    tableUnits.changeSelection(selected, 0, false, false);
                    break;
                }
            }
        }
    }

    public void enableResetButton(boolean b) {
        btnResetSearch.setEnabled(b);
    }
 }
