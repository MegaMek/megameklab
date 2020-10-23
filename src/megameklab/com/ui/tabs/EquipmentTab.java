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
package megameklab.com.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megamek.common.weapons.tag.TAGWeapon;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.EquipmentTableModel;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.XTableColumnModel;

/**
 * General purpose equipment tab
 * 
 * @author Neoancient
 *
 */
public class EquipmentTab extends ITab implements ActionListener {
    
    private static final long serialVersionUID = -7898648511851487701L;

    private enum EquipmentCategory {
        ENERGY ("Energy", (eq, en) -> (eq instanceof WeaponType)
                && !((WeaponType) eq).isCapital()
                && (eq.hasFlag(WeaponType.F_ENERGY)
                        || ((eq.hasFlag(WeaponType.F_PLASMA) && (((WeaponType) eq).getAmmoType() == AmmoType.T_PLASMA))))),
        BALLISTIC ("Ballistic", (eq, en) -> (eq instanceof WeaponType)
                && !((WeaponType) eq).isCapital()
                && eq.hasFlag(WeaponType.F_BALLISTIC)),
        MISSILE ("Missile", (eq, en) -> (eq instanceof WeaponType)
                && !((WeaponType) eq).isCapital()
                && eq.hasFlag(WeaponType.F_MISSILE)),
        ARTILLERY ("Artillery", (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_ARTILLERY)),
        CAPITAL ("Capital",
                (eq, en) -> (eq instanceof WeaponType) && ((WeaponType) eq).isCapital(),
                Entity::isLargeCraft),
        PHYSICAL ("Physical", (eq, en) -> UnitUtil.isPhysicalWeapon(eq),
                e -> e.hasETypeFlag(Entity.ETYPE_MECH) || e.hasETypeFlag(Entity.ETYPE_PROTOMECH)),
        WEAPON ("All Weapons", (eq, en) -> ENERGY.filter(eq, en) || BALLISTIC.filter(eq, en)
                || MISSILE.filter(eq, en) || CAPITAL.filter(eq, en) || PHYSICAL.filter(eq, en)),
        AMMO ("Ammo", (eq, en) -> (eq instanceof AmmoType) && !(eq instanceof BombType)
                && UnitUtil.canUseAmmo(en, (AmmoType) eq, false),
                e -> e.getWeightClass() != EntityWeightClass.WEIGHT_SMALL_SUPPORT),
        OTHER ("Other", (eq, en) -> ((eq instanceof MiscType)
                && !UnitUtil.isPhysicalWeapon(eq)
                && !UnitUtil.isJumpJet(eq)
                && !UnitUtil.isHeatSink(eq)
                && !eq.hasFlag(MiscType.F_TSM)
                && !eq.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                && !(eq.hasFlag(MiscType.F_MASC)
                        && !eq.hasSubType(MiscType.S_SUPERCHARGER)
                        && !eq.hasSubType(MiscType.S_JETBOOSTER))
                && !(en.hasETypeFlag(Entity.ETYPE_QUADVEE) && eq.hasFlag(MiscType.F_TRACKS))
                && !UnitUtil.isArmorOrStructure(eq)
                && !eq.hasFlag(MiscType.F_CHASSIS_MODIFICATION)
                && !(en.isSupportVehicle() && (eq.hasFlag(MiscType.F_BASIC_FIRECONTROL) || (eq.hasFlag(MiscType.F_ADVANCED_FIRECONTROL))))
                && !eq.hasFlag(MiscType.F_MAGNETIC_CLAMP)
                && !(eq.hasFlag(MiscType.F_PARTIAL_WING) && en.hasETypeFlag(Entity.ETYPE_PROTOMECH)))
                && !eq.hasFlag(MiscType.F_SPONSON_TURRET)
                && !eq.hasFlag(MiscType.F_PINTLE_TURRET)
                || (eq instanceof TAGWeapon));

        private final String displayName;
        private final BiFunction<EquipmentType, Entity, Boolean> filter;
        private final Function<Entity, Boolean> showForEntity;
        
        EquipmentCategory(String displayName, BiFunction<EquipmentType, Entity, Boolean> filter) {
            this(displayName, filter, e -> true);
        }
        EquipmentCategory(String displayName,
                BiFunction<EquipmentType, Entity, Boolean> filter,
                Function<Entity, Boolean> showForEntity) {
            this.displayName = displayName;
            this.filter = filter;
            this.showForEntity = showForEntity;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public boolean show(Entity en) {
            return showForEntity.apply(en);
        }
        
        public boolean filter(EquipmentType eq, Entity en) {
            return filter.apply(eq, en);
        }
    }
    

    private RefreshListener refresh;

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");
    private JComboBox<EquipmentCategory> choiceType = new JComboBox<>();
    private JTextField txtFilter = new JTextField();

    private JRadioButton rbtnStats = new JRadioButton("Stats");
    final private JCheckBox chkShowAll = new JCheckBox("Show Unavailable");
    private JSpinner spnAddCount = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));

    private TableRowSorter<EquipmentTableModel> equipmentSorter;

    private CriticalTableModel equipmentList;
    private EquipmentTableModel masterEquipmentList;
    private JTable masterEquipmentTable = new JTable();
    private JTable equipmentTable = new JTable();

    private String ADD_COMMAND = "ADD";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    private static final Dimension SPINNER_SIZE = new Dimension(55, 25);

    public EquipmentTab(EntitySource eSource) {
        super(eSource);

        equipmentList = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.WEAPONTABLE);
        equipmentTable.setModel(equipmentList);
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        equipmentTable.setRowHeight((int) (equipmentTable.getRowHeight() * 1.2));
        TableColumn column;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if (i == CriticalTableModel.NAME) {
                column.setPreferredWidth(200);
            } else if (i == CriticalTableModel.SIZE) {
                column.setCellEditor(equipmentList.new SpinnerCellEditor());
            }
            column.setCellRenderer(equipmentList.getRenderer());

        }
        equipmentList.addTableModelListener(ev -> {
            if (refresh != null) {
                refresh.refreshStatus();
                refresh.refreshPreview();
                refresh.refreshBuild();
                refresh.refreshSummary();
            }
        });
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new java.awt.Dimension(300, 200));
        equipmentScroll.setPreferredSize(new java.awt.Dimension(300, 200));

        masterEquipmentList = new EquipmentTableModel(eSource.getEntity(), eSource.getTechManager());
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
            column = masterEquipmentTable.getColumnModel().getColumn(i);
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
            addButton.setEnabled((null != etype) && eSource.getTechManager().isLegal(etype));
        };
        masterEquipmentTable.getSelectionModel().addListSelectionListener(selectionListener);
        masterEquipmentTable.setDoubleBuffered(true);
        JScrollPane masterEquipmentScroll = new JScrollPane();
        masterEquipmentScroll.setViewportView(masterEquipmentTable);

        masterEquipmentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int view = target.getSelectedRow();
                    int selected = masterEquipmentTable.convertRowIndexToModel(view);
                    EquipmentType equip = masterEquipmentList.getType(selected);
                    addEquipment(equip);
                    fireTableRefresh();
                }
            }
        });

        masterEquipmentTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
        masterEquipmentTable.getActionMap().put("add", new EnterAction());

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            allTypes.add(eq);
        }

        masterEquipmentList.setData(allTypes);

        loadEquipmentTable();

        DefaultComboBoxModel<EquipmentCategory> typeModel = new DefaultComboBoxModel<>();
        for (EquipmentCategory cat : EquipmentCategory.values()) {
            if (cat.show(eSource.getEntity())) {
                typeModel.addElement(cat);
            }
        }
        choiceType.setModel(typeModel);
        choiceType.setSelectedIndex(0);
        choiceType.addActionListener(ev -> filterEquipment());
        choiceType.setRenderer(new CategoryListCellRenderer());

        txtFilter.setText("");
        txtFilter.setMinimumSize(new java.awt.Dimension(200, 28));
        txtFilter.setPreferredSize(new java.awt.Dimension(200, 28));
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterEquipment();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterEquipment();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterEquipment();
            }
        });

        filterEquipment();
        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('l');

        ButtonGroup bgroupView = new ButtonGroup();
        bgroupView.add(rbtnStats);
        JRadioButton rbtnFluff = new JRadioButton("Fluff");
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

        //layout
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();

        JPanel loadoutPanel = new JPanel(new GridBagLayout());
        JPanel databasePanel = new JPanel(new GridBagLayout());

        loadoutPanel.setBorder(BorderFactory.createTitledBorder("Current Loadout"));
        databasePanel.setBorder(BorderFactory.createTitledBorder("Equipment Database"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        databasePanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        databasePanel.add(choiceType, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        databasePanel.add(txtFilter, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        databasePanel.add(viewPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        databasePanel.add(new JLabel("Number to add:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        setFieldSize(spnAddCount, SPINNER_SIZE);
        databasePanel.add(spnAddCount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        loadoutPanel.add(removeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        loadoutPanel.add(removeAllButton, gbc);

        gbc.insets = new Insets(2,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        databasePanel.add(masterEquipmentScroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        loadoutPanel.add(equipmentScroll, gbc);

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(loadoutPanel),
                new JScrollPane(databasePanel));
        pane.setOneTouchExpandable(true);
        setLayout(new BorderLayout());
        add(pane, BorderLayout.CENTER);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void loadEquipmentTable() {

        for (Mounted mount : eSource.getEntity().getWeaponList()) {
            equipmentList.addCrit(mount);
        }

        for (Mounted mount : eSource.getEntity().getAmmo()) {
            equipmentList.addCrit(mount);
        }

        List<EquipmentType> spreadAlreadyAdded = new ArrayList<>();

        for (Mounted mount : eSource.getEntity().getMisc()) {
            
            EquipmentType etype = mount.getType();
            if (UnitUtil.isHeatSink(mount)
                    || etype.hasFlag(MiscType.F_JUMP_JET)
                    || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                    || etype.hasFlag(MiscType.F_TSM)
                    || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || (etype.hasFlag(MiscType.F_MASC) 
                            && !etype.hasSubType(MiscType.S_SUPERCHARGER)
                            && !etype.hasSubType(MiscType.S_JETBOOSTER))
                    || ((eSource.getEntity().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE
                        && etype.hasFlag(MiscType.F_TRACKS))
                    || etype.hasFlag(MiscType.F_CHASSIS_MODIFICATION)
                    || UnitUtil.isArmorOrStructure(etype)) {
                continue;
            }
            //if (UnitUtil.isUnitEquipment(mount.getType(), unit) || UnitUtil.isUn) {
                if (UnitUtil.isFixedLocationSpreadEquipment(etype) 
                        && !spreadAlreadyAdded.contains(etype)) {
                    equipmentList.addCrit(mount);
                    // keep track of spreadable equipment here, so it doesn't
                    // show up multiple times in the table
                    spreadAlreadyAdded.add(etype);
                } else {
                    equipmentList.addCrit(mount);
                }
            //}
        }


    }

    private void removeHeatSinks() {
        int location = 0;
        for (; location < equipmentList.getRowCount();) {

            Mounted mount = (Mounted) equipmentList.getValueAt(location, CriticalTableModel.EQUIPMENT);
            EquipmentType eq = mount.getType();
            if ((eq instanceof MiscType) && (UnitUtil.isHeatSink(mount))) {
                try {
                    equipmentList.removeCrit(location);
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                location++;
            }
        }
    }

    public void refresh() {
        removeAllListeners();
        filterEquipment();
        updateEquipment();
        addAllListeners();
        fireTableRefresh();
    }
    
    public void refreshTable() {
        filterEquipment();
    }

    private void removeAllListeners() {
        addButton.removeActionListener(this);
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        removeAllButton.addActionListener(this);
        addButton.setActionCommand(ADD_COMMAND);
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVEALL_COMMAND);
        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
    }

    private void addEquipment(EquipmentType equip) {
        boolean success = false;
        Mounted mount = null;
        boolean isMisc = equip instanceof MiscType;
        try {
            if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
                if (!UnitUtil.hasTargComp(eSource.getEntity())) {
                    mount = UnitUtil.updateTC(eSource.getEntity(), equip);
                    success = mount != null;
                }
            } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
                if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_MECH)) {
                    mount = UnitUtil.createSpreadMounts(getMech(), equip);
                } else {
                    int location = TestEntity.getSystemWideLocation(eSource.getEntity());
                    mount = new Mounted(eSource.getEntity(), equip);
                    eSource.getEntity().addEquipment(mount, location, false);
                }
                success = mount != null;
            } else {
                int count = (Integer)spnAddCount.getValue();
                if (equip instanceof AmmoType) {
                    if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
                        addProtomechAmmo(equip, count);
                        return;
                    } else if (eSource.getEntity().usesWeaponBays()) {
                        addLargeCraftAmmo(equip, count);
                        return;
                    } else if (eSource.getEntity().isAero()) {
                        addBodyAmmo(equip, count, Aero.LOC_FUSELAGE);
                        return;
                    } else if (eSource.getEntity() instanceof Tank) {
                        addBodyAmmo(equip, count, Tank.LOC_BODY);
                        return;
                    }
                }
                for (int i = 0; i < count; i++) {
                    mount = new Mounted(eSource.getEntity(), equip);
                    if ((eSource.getEntity().isFighter()
                            && equip instanceof MiscType) && equip.hasFlag(MiscType.F_BLUE_SHIELD)) {
                        getAero().addEquipment(mount, Aero.LOC_FUSELAGE, false);
                    } else {
                        eSource.getEntity().addEquipment(mount, Entity.LOC_NONE, false);
                    }
                    equipmentList.addCrit(mount);
                    if ((equip instanceof WeaponType) && (equip.hasFlag(WeaponType.F_ONESHOT)
                            || (((WeaponType) equip).getAmmoType() == AmmoType.T_INFANTRY))) {
                        UnitUtil.removeOneShotAmmo(eSource.getEntity());
                    }
                }
            }
        } catch (LocationFullException ex) {
            MegaMekLab.getLogger().error(getClass(), "addEquipment(EquipmentType)",
                    "Location full while trying to add " + equip.getName());
            JOptionPane.showMessageDialog(
                    this,"Could not add " + equip.getName(),
                    "Location Full", JOptionPane.ERROR_MESSAGE);
        }
        if (success) {
            equipmentList.addCrit(mount);
        }
    }

    private void addProtomechAmmo(EquipmentType ammo, int shots) throws LocationFullException {
        Mounted aMount = getProtomech().getAmmo().stream()
                .filter(m -> m.getType() == ammo).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted mount = new Mounted(getProtomech(), ammo);
            getProtomech().addEquipment(mount, Protomech.LOC_BODY, false);
            mount.setShotsLeft(shots);
            equipmentList.addCrit(mount);
        }
    }
    
    private void addLargeCraftAmmo(EquipmentType ammo, int count) throws LocationFullException {
        Mounted aMount = UnitUtil.findUnallocatedAmmo(getAero(), ammo);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + ((AmmoType)ammo).getShots() * count);
        } else {
            Mounted mount = new Mounted(getAero(), ammo);
            mount.setShotsLeft(((AmmoType)ammo).getShots() * count);
            getAero().addEquipment(mount, Entity.LOC_NONE, false);
            equipmentList.addCrit(mount);
        }
    }

    /**
     * Adds ammo to the correct location (body/fuselage) for aerospace and vehicles
     * @param equip The {@link AmmoType} to add
     * @param count The number of slots of ammo (usually tons)
     * @param loc   The location to add it
     * @throws LocationFullException If the location is full.
     */
    private void addBodyAmmo(EquipmentType equip, int count, int loc) throws LocationFullException {
        for (int i = 0; i < count; i++) {
            Mounted mount = new Mounted(getEntity(), equip);
            getEntity().addEquipment(mount, loc, false);
            equipmentList.addCrit(mount);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADD_COMMAND)) {
            int view = masterEquipmentTable.getSelectedRow();
            if(view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            addEquipment(equip);
        } else if (e.getActionCommand().equals(REMOVE_COMMAND)) {
            int[] selectedRows = equipmentTable.getSelectedRows();
            for (Integer row : selectedRows){
                equipmentList.removeMounted(row);
            }
            equipmentList.removeCrits(selectedRows);
        } else if (e.getActionCommand().equals(REMOVEALL_COMMAND)) {
            removeAllEquipment();
        } else {
            return;
        }
        fireTableRefresh();
    }

    private void updateEquipment() {
        removeHeatSinks();
        equipmentList.removeAllCrits();
        loadEquipmentTable();
    }

    private void removeAllEquipment() {
        removeHeatSinks();
        for (int count = 0; count < equipmentList.getRowCount(); count++) {
            equipmentList.removeMounted(count);
        }
        equipmentList.removeAllCrits();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(eSource.getEntity());
        equipmentList.refreshModel();
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshSummary();
        }
    }

    public CriticalTableModel getEquipmentList() {
        return equipmentList;
    }

    private void filterEquipment() {
        final EquipmentCategory nType = (EquipmentCategory) choiceType.getSelectedItem();
        RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = new RowFilter<EquipmentTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
                Entity entity = eSource.getEntity();
                EquipmentTableModel equipModel = entry.getModel();
                EquipmentType etype = equipModel.getType(entry.getIdentifier());
                if (!UnitUtil.isEntityEquipment(etype, entity)
                        || ((nType != null) && !nType.filter(etype, eSource.getEntity()))) {
                    return false;
                }
                if (!eSource.getTechManager().isLegal(etype) && !chkShowAll.isSelected()) {
                    return false;
                }
                if (txtFilter.getText().length() > 0) {
                    String text = txtFilter.getText();
                    return etype.getName().toLowerCase().contains(text.toLowerCase());
                } else {
                    return true;
                }
            }
        };
        equipmentSorter.setRowFilter(equipmentTypeFilter);
    }

    private void setEquipmentView() {
        XTableColumnModel columnModel = (XTableColumnModel)masterEquipmentTable.getColumnModel();
        if(rbtnStats.isSelected()) {
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_NAME), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DAMAGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_DIVISOR), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SPECIAL), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_HEAT), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_MRANGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_RANGE), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_SHOTS), true);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_BV), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), true);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), true);
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
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_TON), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_CRIT), false);
            columnModel.setColumnVisible(columnModel.getColumnByModelIndex(EquipmentTableModel.COL_REF), true);
        }
    }

    private class EnterAction extends AbstractAction {

        /**
         *
         */
        private static final long serialVersionUID = 8247993757008802162L;

        @Override
        public void actionPerformed(ActionEvent e) {
            int view = masterEquipmentTable.getSelectedRow();
            if(view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = masterEquipmentList.getType(selected);
            addEquipment(equip);
            fireTableRefresh();
        }
    }

    private static class CategoryListCellRenderer extends JLabel implements ListCellRenderer<EquipmentCategory> {
        private static final long serialVersionUID = -6019108605730297067L;
        
        @Override
        public Component getListCellRendererComponent(JList<? extends EquipmentCategory> list,
                EquipmentCategory value, int index, boolean isSelected, boolean cellHasFocus) {
            setOpaque(list.isOpaque());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());

            setText(value.getDisplayName());
            
            return this;
        }
    }


}
