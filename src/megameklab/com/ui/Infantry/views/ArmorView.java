/*
 * MegaMekLab - Copyright (C) 2008
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.Infantry.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.MiscType;
import megamek.common.TechConstants;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.EquipmentTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.XTableColumnModel;

public class ArmorView extends IView implements ActionListener, ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private RefreshListener refresh = null;
    
    private final static String CARD_TABLE = "table";
    private final static String CARD_CUSTOM = "custom";
    
    private JButton btnSetArmor = new JButton("Set Armor");    
    private JButton btnRemoveArmor = new JButton("Remove Armor");    
    private JTextField txtFilter = new JTextField();
    private JRadioButton rbtnStats = new JRadioButton("Stats");
    private JRadioButton rbtnFluff = new JRadioButton("Fluff");
    private JRadioButton rbtnCustom = new JRadioButton("Custom");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");
    private TableRowSorter<EquipmentTableModel> equipmentSorter;
    private EquipmentTableModel masterEquipmentList;
    private JTable masterEquipmentTable = new JTable();
    private JScrollPane masterEquipmentScroll = new JScrollPane();
    private JPanel equipmentView = new JPanel();
    private CardLayout equipmentLayout = new CardLayout();

    JCheckBox chEncumber = new JCheckBox();
    JCheckBox chSpaceSuit = new JCheckBox();
    JCheckBox chDEST = new JCheckBox();
    JCheckBox chSneakCamo = new JCheckBox();
    JCheckBox chSneakIR = new JCheckBox();
    JCheckBox chSneakECM = new JCheckBox();
    private JSpinner armorValue = new JSpinner(new SpinnerNumberModel(1.0, 0.5, 3.0, 0.5));
    
    public ArmorView(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), techManager);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        equipmentSorter = new TableRowSorter<EquipmentTableModel>(masterEquipmentList);
        equipmentSorter.setComparator(EquipmentTableModel.COL_DIVISOR, new DamageDivisorSorter());
        equipmentSorter.setComparator(EquipmentTableModel.COL_COST, new WeaponView.FormattedNumberSorter());
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
        masterEquipmentTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                int view = masterEquipmentTable.getSelectedRow();
                if(view < 0) {
                    //selection got filtered away
                    return;
                }
                int selected = masterEquipmentTable.convertRowIndexToModel(view);
                EquipmentType equip = masterEquipmentList.getType(selected);
                btnSetArmor.setEnabled(equip.hasFlag(MiscType.F_ARMOR_KIT));
            }
        });
        masterEquipmentScroll.setMinimumSize(new Dimension(200,200));
        masterEquipmentScroll.setPreferredSize(new Dimension(200,200));

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<EquipmentType>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            if(eq.hasFlag(MiscType.F_ARMOR_KIT)) {
                allTypes.add(eq);
            }
        }

        masterEquipmentList.setData(allTypes);

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
        bgroupView.add(rbtnCustom);

        rbtnStats.setSelected(true);
        rbtnStats.addActionListener(ev -> setEquipmentView());
        rbtnFluff.addActionListener(ev -> setEquipmentView());
        rbtnCustom.addActionListener(ev -> setEquipmentView());
        chkShowAll.addActionListener(ev -> filterEquipment());
        
        setUpPanels();
        rbtnStats.setSelected(true);
        setEquipmentView();
        refresh();
    }
    
    private void setUpPanels() {
        JPanel databasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        databasePanel.add(btnSetArmor, gbc);
        btnSetArmor.addActionListener(this);

        gbc.gridx = 1;
        databasePanel.add(btnRemoveArmor, gbc);
        btnRemoveArmor.addActionListener(this);

        JPanel btnPanel = new JPanel();
        btnPanel.add(rbtnStats);
        btnPanel.add(rbtnFluff);
        btnPanel.add(rbtnCustom);
        btnPanel.add(chkShowAll);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        databasePanel.add(btnPanel, gbc);
        
        equipmentView.setLayout(equipmentLayout);

        gbc.insets = new Insets(2,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(equipmentView, gbc);

        setLayout(new BorderLayout());
        this.add(databasePanel, BorderLayout.CENTER);
        
        JPanel tableView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tableView.add(new JLabel("Filter:"), gbc);
        
        gbc.gridx = 1;
        tableView.add(txtFilter, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tableView.add(masterEquipmentScroll, gbc);
        
        equipmentView.add(tableView, CARD_TABLE);

        JPanel customView = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        customView.add(new JLabel("Damage Divisor:"), gbc);
        gbc.gridx = 1;
        customView.add(armorValue, gbc);
        JFormattedTextField tf = ((JSpinner.DefaultEditor)armorValue.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        
        chEncumber.setText("Encumbering");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        customView.add(chEncumber, gbc);
        
        chSpaceSuit.setText("Space Suit");
        gbc.gridx = 1;
        gbc.gridy = 1;
        customView.add(chSpaceSuit, gbc);

        chDEST.setText("DEST");
        gbc.gridx = 0;
        gbc.gridy = 2;
        customView.add(chDEST, gbc);

        chSneakCamo.setText("Sneak (CAMO)");
        gbc.gridx = 1;
        gbc.gridy = 2;
        customView.add(chSneakCamo, gbc);

        chSneakIR.setText("Sneak (IR)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        customView.add(chSneakIR, gbc);

        chSneakECM.setText("Sneak (ECM)");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        customView.add(chSneakECM, gbc);

        equipmentView.add(customView, CARD_CUSTOM);        
    }
    
    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, maxSize);
        return label;
    }
    
    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        removeAllListeners();
        armorValue.setValue((double)getInfantry().getDamageDivisor());
        chEncumber.setSelected(getInfantry().isArmorEncumbering());
        chSpaceSuit.setSelected(getInfantry().hasSpaceSuit());
        chDEST.setSelected(getInfantry().hasDEST());
        chSneakCamo.setSelected(getInfantry().hasSneakCamo());
        chSneakIR.setSelected(getInfantry().hasSneakIR());
        chSneakECM.setSelected(getInfantry().hasSneakECM());
        if(getInfantry().getTechLevel() < TechConstants.T_TW_ALL) {
            armorValue.setEnabled(false);
            chEncumber.setEnabled(false);
            chSpaceSuit.setEnabled(false);
            chDEST.setEnabled(false);
            chSneakCamo.setEnabled(false);
            chSneakIR.setEnabled(false);
            chSneakECM.setEnabled(false);
        } else {            
            armorValue.setEnabled(true);
            chEncumber.setEnabled(true);
            chSpaceSuit.setEnabled(true);
            chDEST.setEnabled(true);
            chSneakCamo.setEnabled(true);
            chSneakIR.setEnabled(true);
            chSneakECM.setEnabled(true);
        }
        filterEquipment();
        btnRemoveArmor.setEnabled(hasArmor());
        rbtnCustom.setEnabled(getInfantry().getArmorKit() == null);
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource().equals(btnSetArmor)) {
            int view = masterEquipmentTable.getSelectedRow();
            if(view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            if(equip.hasFlag(MiscType.F_ARMOR_KIT)) {
                getInfantry().setArmorKit(equip);
                rbtnCustom.setEnabled(false);
            }
        } else if (arg0.getSource().equals(btnRemoveArmor)) {
            getInfantry().setArmorKit(null);
            getInfantry().setDamageDivisor(1.0);
            getInfantry().setArmorEncumbering(false);
            getInfantry().setSpaceSuit(false);
            getInfantry().setDEST(false);
            getInfantry().setSneakCamo(false);
            getInfantry().setSneakIR(false);
            getInfantry().setSneakECM(false);
            rbtnCustom.setEnabled(true);
        }
        if (arg0.getSource().equals(chEncumber)) {
            getInfantry().setArmorEncumbering(chEncumber.isSelected());
        } 
        else if (arg0.getSource().equals(chSpaceSuit)) {
            getInfantry().setSpaceSuit(chSpaceSuit.isSelected());
        } 
        else if (arg0.getSource().equals(chDEST)) {
            getInfantry().setDEST(chDEST.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakCamo)) {
            getInfantry().setSneakCamo(chSneakCamo.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakIR)) {
            getInfantry().setSneakIR(chSneakIR.isSelected());
        } 
        else if (arg0.getSource().equals(chSneakECM)) {
            getInfantry().setSneakECM(chSneakECM.isSelected());
        } 
        addAllListeners();
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
    }

    private void addAllListeners() {
        chEncumber.addActionListener(this);
        chSpaceSuit.addActionListener(this);
        chDEST.addActionListener(this);
        chSneakCamo.addActionListener(this);
        chSneakIR.addActionListener(this);
        chSneakECM.addActionListener(this);
        armorValue.addChangeListener(this);
    }

    private void removeAllListeners() {
        chEncumber.removeActionListener(this);
        chSpaceSuit.removeActionListener(this);
        chDEST.removeActionListener(this);
        chSneakCamo.removeActionListener(this);
        chSneakIR.removeActionListener(this);
        chSneakECM.removeActionListener(this);
        armorValue.removeChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        double value = (Double) field.getModel().getValue();      
        getInfantry().setDamageDivisor(value);
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }
    
    private void filterEquipment() {
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = null;
        equipmentTypeFilter = new RowFilter<EquipmentTableModel,Integer>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if(!(etype.hasFlag(MiscType.F_ARMOR_KIT))) {
                    return false;
                }
                if(null != eSource.getTechManager()
                        && !eSource.getTechManager().isLegal(etype)
                        && !chkShowAll.isSelected()) {
                    return false;
                }
                if (!etype.isAvailableIn(getInfantry().getTechLevelYear())) {
                    return false;
                }
                if (txtFilter.getText().length() > 0) {
                    return etype.getName().toLowerCase().contains(txtFilter.getText().toLowerCase());
                }
                return true;
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
    }

    public void setEquipmentView() {
        if (rbtnCustom.isSelected()) {
            equipmentLayout.show(equipmentView, CARD_CUSTOM);
            btnSetArmor.setEnabled(false);
            return;
        }
        equipmentLayout.show(equipmentView, CARD_TABLE);
        btnSetArmor.setEnabled(true);
        XTableColumnModel columnModel = (XTableColumnModel)masterEquipmentTable.getColumnModel();
        if(rbtnStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), false);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPROTOTYPE), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DPRODUCTION), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DCOMMON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DEXTINCT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DREINTRO), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_COST), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CREW), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        }
    }
    
    private boolean hasArmor() {
        return getInfantry().getArmorKit() != null
                || !getInfantry().getArmorDesc().equals("1.0");
    }
    
    /**
     * A comparator for damage divisor that sorts by numeric value first, then considers an appended
     * "E" (indicating encumbering).
     * 
     * @author Neoancient
     *
     */
    public static class DamageDivisorSorter implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            double d1 = 0;
            try {
                if (s1.endsWith("E")) {
                    d1 = Double.parseDouble(s1.replace("E", "")) - 0.1;
                } else {
                    d1 = Double.parseDouble(s1.replace("E", ""));
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            double d2 = 0;
            try {
                if (s2.endsWith("E")) {
                    d2 = Double.parseDouble(s2.replace("E", "")) - 0.1;
                } else {
                    d2 = Double.parseDouble(s2.replace("E", ""));
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            if (d1 > d2) {
                return -1;
            } else if (d1 < d2) {
                return 1;
            } else {
                return 0;
            }
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
            btnSetArmor.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        }
        
    };
}