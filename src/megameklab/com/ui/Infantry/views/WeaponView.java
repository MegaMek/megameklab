/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Infantry.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Infantry;
import megamek.common.WeaponType;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.EquipmentTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.XTableColumnModel;

public class WeaponView extends IView implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 3978675469713289404L;

    private static final int T_ARCHAIC   =  0;
    private static final int T_PERSONAL  =  1;
    private static final int T_SUPPORT   =  2;
    private static final int T_WEAPON    =  3;
    private static final int T_NUM       =  4;


    private RefreshListener refresh;

    private JButton addPrimaryButton = new JButton("Add Primary");
    private JButton addSecondaryButton = new JButton("Add Secondary");
    private JComboBox<String> choiceType = new JComboBox<String>();
    private JTextField txtFilter = new JTextField();

    private JRadioButton rbtnStats = new JRadioButton("Stats");
    private JRadioButton rbtnFluff = new JRadioButton("Fluff");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");

    private TableRowSorter<EquipmentTableModel> equipmentSorter;

    private EquipmentTableModel masterEquipmentList;
    private JTable masterEquipmentTable = new JTable();
    private JScrollPane masterEquipmentScroll = new JScrollPane();

    private String ADDP_COMMAND = "ADDPRIMARY";
    private String ADDS_COMMAND = "ADDSECONDARY";

    public static String getTypeName(int type) {
        switch(type) {
        case T_WEAPON:
            return "All Weapons";
        case T_ARCHAIC:
            return "Archaic Weapons";
        case T_PERSONAL:
            return "Personal Weapons";
        case T_SUPPORT:
            return "Support Weapons";
        default:
            return "?";
        }
    }

    public WeaponView(EntitySource eSource, ITechManager techManager) {
        super(eSource);

        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), techManager);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        equipmentSorter = new TableRowSorter<EquipmentTableModel>(masterEquipmentList);
        equipmentSorter.setComparator(EquipmentTableModel.COL_DAMAGE, new WeaponDamageSorter());
        equipmentSorter.setComparator(EquipmentTableModel.COL_COST, new FormattedNumberSorter());
        masterEquipmentTable.setRowSorter(equipmentSorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(EquipmentTableModel.COL_NAME, SortOrder.ASCENDING));
        equipmentSorter.setSortKeys(sortKeys);
        XTableColumnModel equipColumnModel = new XTableColumnModel();
        masterEquipmentTable.setColumnModel(equipColumnModel);
        masterEquipmentTable.createDefaultColumnsFromModel();
        TableColumn column = null;
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        masterEquipmentScroll.setMinimumSize(new Dimension(200,200));
        masterEquipmentScroll.setPreferredSize(new Dimension(200,200));

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<EquipmentType>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if(eq instanceof InfantryWeapon) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);

        DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < T_NUM; i++) {
            typeModel.addElement(getTypeName(i));
        }
        choiceType.setModel(typeModel);
        choiceType.setSelectedIndex(1);
        choiceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterEquipment();
            }
        });

        txtFilter.setText("");
        txtFilter.setMinimumSize(new java.awt.Dimension(200, 28));
        txtFilter.setPreferredSize(new java.awt.Dimension(200, 28));
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                filterEquipment();
            }
            public void insertUpdate(DocumentEvent e) {
                filterEquipment();
            }
            public void removeUpdate(DocumentEvent e) {
                filterEquipment();
            }
        });

        ButtonGroup bgroupView = new ButtonGroup();
        bgroupView.add(rbtnStats);
        bgroupView.add(rbtnFluff);

        rbtnStats.setSelected(true);
        rbtnStats.addActionListener(ev -> setEquipmentView());
        rbtnFluff.addActionListener(ev -> setEquipmentView());
        chkShowAll.addActionListener(ev -> filterEquipment());
        JPanel viewPanel = new JPanel(new GridLayout(0,3));
        viewPanel.add(rbtnStats);
        viewPanel.add(rbtnFluff);
        viewPanel.add(chkShowAll);
        setEquipmentView();

        JPanel btnPanel = new JPanel(new GridLayout(0,2));
        btnPanel.add(addPrimaryButton);
        btnPanel.add(addSecondaryButton);

        //layout
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();

        JPanel databasePanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        databasePanel.add(choiceType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        databasePanel.add(txtFilter, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        databasePanel.add(viewPanel, gbc);

        gbc.insets = new Insets(2,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(masterEquipmentScroll, gbc);

        setLayout(new BorderLayout());
        this.add(databasePanel, BorderLayout.CENTER);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        filterEquipment();
        if(TestInfantry.maxSecondaryWeapons(getInfantry()) > 0) {
            addSecondaryButton.setEnabled(true);
        } else {
            addSecondaryButton.setEnabled(false);
        }
        addAllListeners();
    }

    private void removeAllListeners() {
        addPrimaryButton.removeActionListener(this);
        addSecondaryButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addPrimaryButton.addActionListener(this);
        addSecondaryButton.addActionListener(this);
        addPrimaryButton.setActionCommand(ADDP_COMMAND);
        addSecondaryButton.setActionCommand(ADDS_COMMAND);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADDP_COMMAND) ||
                e.getActionCommand().equals(ADDS_COMMAND)) {
            boolean isSecondary = e.getActionCommand().equals(ADDS_COMMAND);
            int view = masterEquipmentTable.getSelectedRow();
            if(view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            if (equip instanceof InfantryWeapon) {
                UnitUtil.replaceMainWeapon(getInfantry(), (InfantryWeapon) equip, isSecondary);
                if (equip.hasFlag(WeaponType.F_TAG)) {
                    getInfantry().setSpecializations(getInfantry().getSpecializations() | Infantry.TAG_TROOPS);
                    getInfantry().setSecondaryN(2);
                } else if (getInfantry().getSecondaryN() == 0) {
                    getInfantry().setSecondaryN(1);
                }
            }
        } else {
            return;
        }
        refresh.refreshAll();
    }

    private void filterEquipment() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = null;
        final int nType = choiceType.getSelectedIndex();
        equipmentTypeFilter = new RowFilter<EquipmentTableModel,Integer>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if(!(etype instanceof InfantryWeapon)) {
                    return false;
                }
                InfantryWeapon weapon = (InfantryWeapon)etype;
                if(getInfantry().getSquadSize() < (getInfantry().getSecondaryN() * weapon.getCrew())) {
                    return false;
                }
                if ((nType == T_WEAPON)
                        || ((nType == T_ARCHAIC) && etype.hasFlag(WeaponType.F_INF_ARCHAIC))
                        || ((nType == T_PERSONAL) && !etype.hasFlag(WeaponType.F_INF_ARCHAIC) && !etype.hasFlag(WeaponType.F_INF_SUPPORT))
                        || ((nType == T_SUPPORT) && etype.hasFlag(WeaponType.F_INF_SUPPORT))
                        ) {
                    if (null != eSource.getTechManager()
                            && !eSource.getTechManager().isLegal(etype)
                            && !chkShowAll.isSelected()) {
                        return false;
                    }
                    if(txtFilter.getText().length() > 0) {
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

    public void setEquipmentView() {
        XTableColumnModel columnModel = (XTableColumnModel)masterEquipmentTable.getColumnModel();
        if(rbtnStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TLEVEL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TRATING), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPROTOTYPE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPRODUCTION), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DCOMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DEXTINCT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DREINTRO), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        } else {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TECH), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TLEVEL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TRATING), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPROTOTYPE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPRODUCTION), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DCOMMON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DEXTINCT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DREINTRO), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        }
    }



    /**
     * A comparator for integers written as strings with "-" sorted to the bottom always
     * @author Jay Lawson
     *
     */
    public class WeaponIntegerSorter implements Comparator<String> {

        @Override
        public int compare(String s0, String s1) {
            if(s0.equals("-") && s1.equals("-")) {
                return 0;
            } else if(s0.equals("-")) {
                return 1;
            } else if(s1.equals("-")) {
                return -1;
            } else {
                //get the numbers associated with each string
                int r0 = Integer.parseInt(s0);
                int r1 = Integer.parseInt(s1);
                return ((Comparable<Integer>)r1).compareTo(r0);
            }
        }
    }

    public class WeaponDamageSorter implements Comparator<String> {

        @Override
        public int compare(String s0, String s1) {
            //get the numbers associated with each string
            double r1 = parseDamage(s1);
            double r0 = parseDamage(s0);
            return ((Comparable<Double>)r1).compareTo(r0);
        }

        /**
         * Extracts a numeric value from the damage string for use in sorting by damage. If a String
         * contains any non-digit character, that character and anything after it is ignored. If the string
         * starts with a non-digit character the return value is 0.
         * 
         * @param s A weapon damage string
         * @return  The value to use for sorting.
         */
        private double parseDamage(String s) {
            s = s.replaceAll("[^0-9\\.]+", "/");
            if (s.contains("/")) {
                s = s.substring(0, s.indexOf("/"));
            }
            if (s.length() > 0) {
                return Double.parseDouble(s);
            } else {
                return 0;
            }
        }
    }

    /**
     * A comparator for numbers that have been formatted with DecimalFormat
     * @author Jay Lawson
     *
     */
    public static class FormattedNumberSorter implements Comparator<String> {

        @Override
        public int compare(String s0, String s1) {
            //lets find the weight class integer for each name
            DecimalFormat format = new DecimalFormat();
            int l0 = 0;
            try {
                l0 = format.parse(s0).intValue();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            int l1 = 0;
            try {
                l1 = format.parse(s1).intValue();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            return ((Comparable<Integer>)l0).compareTo(l1);
        }
    }
    
    private ListSelectionListener selectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selected = masterEquipmentTable.getSelectedRow();
            EquipmentType etype = null;
            if (selected >= 0) {
                etype = masterEquipmentList.getType(masterEquipmentTable.convertRowIndexToModel(selected));
            }
            addPrimaryButton.setEnabled((null != etype)
                    && eSource.getTechManager().isLegal(etype)
                    && !etype.hasFlag(WeaponType.F_INF_SUPPORT));
            addSecondaryButton.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        }
        
    };
}