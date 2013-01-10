/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY  WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.ArtilleryWeapon;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.EquipmentListCellKeySelectionManager;
import megameklab.com.util.EquipmentListCellRenderer;
import megameklab.com.util.EquipmentTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

public class EquipmentView extends IView implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private static final int T_ENERGY    =  0;
    private static final int T_BALLISTIC =  1;
    private static final int T_MISSILE   =  2;
    private static final int T_ARTILLERY =  3;
    private static final int T_PHYSICAL  =  4;
    private static final int T_WEAPON    =  5;
	private static final int T_AMMO      =  6;
	private static final int T_OTHER     =  7;
	private static final int T_NUM       =  8;

    
    private RefreshListener refresh;

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");
    private JComboBox choiceType = new JComboBox();
    private JTextField txtFilter = new JTextField();
    
	private TableRowSorter<EquipmentTableModel> equipmentSorter;

    @SuppressWarnings("rawtypes")
    private CriticalTableModel equipmentList;
    private EquipmentTableModel masterEquipmentList;
    private JTable masterEquipmentTable = new JTable();
    private JScrollPane masterEquipmentScroll = new JScrollPane();
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    private String ADD_COMMAND = "ADD";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    private int jumpBoosterMP = 0;

    public static String getTypeName(int type) {
    	switch(type) {
    	case T_WEAPON:
    		return "All Weapons";
    	case T_ENERGY:
    		return "Energy Weapons";
    	case T_BALLISTIC:
        	return "Ballistic Weapons";
        case T_MISSILE:
           	return "Missile Weapons";
        case T_ARTILLERY:
    		return "Artillery Weapons";
        case T_PHYSICAL:
    		return "Physical Weapons";
    	case T_AMMO:
    		return "Ammunition";
    	case T_OTHER:
    		return "Other Equipment";
    	default:
    		return "?";
    	}
    }
    
    public EquipmentView(Mech unit) {
        super(unit);
        if (unit.hasWorkingMisc(MiscType.F_JUMP_BOOSTER)) {
            jumpBoosterMP = unit.getOriginalJumpMP();
        }

        equipmentList = new CriticalTableModel(unit, CriticalTableModel.WEAPONTABLE);
        equipmentTable.setModel(equipmentList);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        //equipmentTable.setMinimumSize(new Dimension(200,500));
        //equipmentTable.setPreferredSize(new Dimension(200,500));
       // equipmentTable.setMaximumSize(new Dimension(100,100));
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new java.awt.Dimension(300, 200));
        equipmentScroll.setPreferredSize(new java.awt.Dimension(300, 200));
        
        masterEquipmentList = new EquipmentTableModel(unit);
        masterEquipmentTable.setModel(masterEquipmentList);
        masterEquipmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumn column = null;
        for (int i = 0; i < EquipmentTableModel.N_COL; i++) {
            column = masterEquipmentTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(masterEquipmentList.getColumnWidth(i));
            column.setCellRenderer(masterEquipmentList.getRenderer());
        }
        masterEquipmentTable.setIntercellSpacing(new Dimension(0, 0));
        masterEquipmentTable.setShowGrid(false);
        //masterEquipmentTable.setMaximumSize(new Dimension(800,500));
        masterEquipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        masterEquipmentTable.setDoubleBuffered(true);
        masterEquipmentScroll.setViewportView(masterEquipmentTable);
        //masterEquipmentScroll.setMinimumSize(new java.awt.Dimension(500, 200));
        //masterEquipmentScroll.setPreferredSize(new java.awt.Dimension(500, 200));

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        ArrayList<EquipmentType> allTypes = new ArrayList<EquipmentType>();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            allTypes.add(eq);
        }

        masterEquipmentList.setData(allTypes);

        loadEquipmentTable();
        
        equipmentSorter = new TableRowSorter<EquipmentTableModel>(masterEquipmentList);
        masterEquipmentTable.setRowSorter(equipmentSorter);

        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
		for (int i = 0; i < T_NUM; i++) {
			typeModel.addElement(getTypeName(i));
		}
		choiceType.setModel(typeModel);
		choiceType.setSelectedIndex(0);
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
    
        filterEquipment();
        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('l');
        
        //layout
		GridBagConstraints gridBagConstraints;
		setLayout(new GridBagLayout());
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		add(choiceType, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		add(txtFilter, gridBagConstraints);
		
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(addButton, gridBagConstraints);
		
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		add(removeButton, gridBagConstraints);
		
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		add(removeAllButton, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(masterEquipmentScroll, gridBagConstraints);
		
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(equipmentScroll, gridBagConstraints);
        
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    /*
    @SuppressWarnings("unchecked")
    private void loadEquipmentCombo() {
        equipmentCombo.setRenderer(new EquipmentListCellRenderer(unit));
        equipmentCombo.setKeySelectionManager(new EquipmentListCellKeySelectionManager());
        equipmentCombo.removeAllItems();
        equipmentTypes = new Vector<EquipmentType>();

        for (EquipmentType eq : masterEquipmentList) {
            if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_TALON)) {
                continue;
            }
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {

                equipmentTypes.add(eq);
                equipmentCombo.addItem(eq);
            }
        }
    }
    */

    private void loadEquipmentTable() {
        
    	for (Mounted mount : unit.getWeaponList()) {
        	equipmentList.addCrit(mount);
        }
    	
    	for (Mounted mount : unit.getAmmo()) {
        	equipmentList.addCrit(mount);
        }
    	
    	List<EquipmentType> spreadAlreadyAdded = new ArrayList<EquipmentType>();
        
        for (Mounted mount : unit.getMisc()) {

            if (isHeatSink(mount) || mount.getType().hasFlag(MiscType.F_JUMP_JET)
                    || UnitUtil.isArmorOrStructure(mount.getType())) {
                continue;
            }
            //if (UnitUtil.isUnitEquipment(mount.getType(), unit) || UnitUtil.isUn) {
                if (UnitUtil.isSpreadEquipment(mount.getType()) && !spreadAlreadyAdded.contains(mount.getType())) {
                    equipmentList.addCrit(mount);
                    // keep track of spreadable equipment here, so it doesn't
                    // show up multiple times in the table
                    spreadAlreadyAdded.add(mount.getType());
                } else {
                    equipmentList.addCrit(mount);
                }
            //}
        }
        
        
    }

    /*
    private void loadHeatSinks() {
        int engineHeatSinks = UnitUtil.getBaseChassisHeatSinks(getMech(), getMech().hasCompactHeatSinks());
        for (Mounted mount : unit.getMisc()) {

            if (isHeatSink(mount)) {
                if (engineHeatSinks-- > 0) {
                    continue;
                }
                equipmentList.addCrit(mount);
            }
        }

    }
    */

    private void removeHeatSinks() {
        int location = 0;
        for (; location < equipmentList.getRowCount();) {

            Mounted mount = (Mounted) equipmentList.getValueAt(location, CriticalTableModel.EQUIPMENT);
            EquipmentType eq = mount.getType();
            if ((eq instanceof MiscType) && (isHeatSink(mount))) {
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

    public void setJumpBoosterMP(int mp) {
        jumpBoosterMP = mp;
    }

    public void refresh() {
        removeAllListeners();
        filterEquipment();
        updateEquipment();
        addAllListeners();
        fireTableRefresh();
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

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADD_COMMAND)) {
            boolean success = false;
            int view = masterEquipmentTable.getSelectedRow();
            if(view < 0) {
                //selection got filtered away
                return;
            }
            int selected = masterEquipmentTable.convertRowIndexToModel(view);
            EquipmentType equip = (EquipmentType) masterEquipmentList.getType(selected);
            Mounted mount = null;
            boolean isMisc = equip instanceof MiscType;
            if (isMisc && ((equip.hasFlag(MiscType.F_TSM) && !getMech().hasTSM()) || (equip.hasFlag(MiscType.F_INDUSTRIAL_TSM) && !getMech().hasIndustrialTSM()) || (equip.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING) && !unit.hasEnvironmentalSealing()) || (equip.hasFlag(MiscType.F_NULLSIG) && !getMech().hasNullSig()) || (equip.hasFlag(MiscType.F_VOIDSIG) && !getMech().hasVoidSig()) || (equip.hasFlag(MiscType.F_TRACKS) && !getMech().hasTracks()) || (equip.hasFlag(MiscType.F_PARTIAL_WING) && !getMech().hasWorkingMisc(MiscType.F_PARTIAL_WING)) || (equip.hasFlag(MiscType.F_CHAMELEON_SHIELD) && !getMech().hasChameleonShield()) || ((equip.hasFlag(MiscType.F_BLUE_SHIELD)) && !unit.hasWorkingMisc(MiscType.F_BLUE_SHIELD)))) {
                mount = UnitUtil.createSpreadMounts(getMech(), equip);
                success = mount != null;
            } else if (isMisc && equip.hasFlag(MiscType.F_JUMP_BOOSTER)) {
                String jumpMp = JOptionPane.showInputDialog(this, "How many Jump MP?");
                try {
                    setJumpBoosterMP(Integer.parseInt(jumpMp));
                    updateJumpMP();
                    if (!getMech().hasWorkingMisc(MiscType.F_JUMP_BOOSTER)) {
                        mount = UnitUtil.createSpreadMounts(getMech(), equip);
                        success = mount != null;
                    }
                } catch (NumberFormatException ex) {
                    // user didn't enter a number, don't add the jump booster
                }

            } else if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
                if (!UnitUtil.hasTargComp(unit)) {
                    mount = UnitUtil.updateTC(getMech(), equip);
                    success = mount != null;
                }
            } else {
                try {
                    mount = new Mounted(unit, equip);
                    getMech().addEquipment(mount, Entity.LOC_NONE, false);
                    success = true;
                } catch (LocationFullException lfe) {
                    // this can't happen, we add to Entity.LOC_NONE
                }
            }
            if (success) {
                equipmentList.addCrit(mount);
            }
        } else if (e.getActionCommand().equals(REMOVE_COMMAND)) {
            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();

            for (; count > 0; count--) {
                if (startRow > -1) {
                    EquipmentType et = ((Mounted)equipmentList.getValueAt(startRow, CriticalTableModel.EQUIPMENT)).getType();
                    if ((et instanceof MiscType) && et.hasFlag(MiscType.F_JUMP_BOOSTER)) {
                        setJumpBoosterMP(0);
                    }
                    equipmentList.removeMounted(startRow);
                    equipmentList.removeCrit(startRow);
                }
            }
            UnitUtil.reIndexCrits(unit);
        } else if (e.getActionCommand().equals(REMOVEALL_COMMAND)) {
            removeAllEquipment();
            UnitUtil.reIndexCrits(unit);
        } else {
            return;
        }
        fireTableRefresh();
    }

    public void updateEquipment() {
        removeHeatSinks();
        equipmentList.removeAllCrits();
        loadEquipmentTable();
    }

    public void removeAllEquipment() {
        removeHeatSinks();
        for (int count = 0; count < equipmentList.getRowCount(); count++) {
            equipmentList.removeMounted(count);
        }
        equipmentList.removeAllCrits();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(unit);
        equipmentList.refreshModel();
        //equipmentScroll.setPreferredSize(new Dimension(getWidth() * 90 / 100, getHeight() * 8 / 10));
        //equipmentScroll.repaint();
        updateJumpMP();
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
        }
    }

    private void updateJumpMP() {
        int mp = 0;
        if (jumpBoosterMP > 0) {
            mp = jumpBoosterMP;
        } else {
            for (Mounted mount : unit.getEquipment()) {
                if (mount.getType() instanceof MiscType) {
                    if (mount.getType().hasFlag(MiscType.F_JUMP_JET)) {
                        mp++;
                    }
                }
            }
        }
        unit.setOriginalJumpMP(mp);
    }

    public CriticalTableModel getEquipmentList() {
        return equipmentList;
    }
    
    private void filterEquipment() {
    	RowFilter<EquipmentTableModel, Integer> equipmentTypeFilter = null;
        final int nType = choiceType.getSelectedIndex();
        equipmentTypeFilter = new RowFilter<EquipmentTableModel,Integer>() {
        	@Override
        	public boolean include(Entry<? extends EquipmentTableModel, ? extends Integer> entry) {
        		EquipmentTableModel equipModel = entry.getModel();
        		EquipmentType etype = equipModel.getType(entry.getIdentifier());
        		WeaponType wtype = null;
        		if(etype instanceof WeaponType) {
        			wtype = (WeaponType)etype;
        		}
        		AmmoType atype = null;
        		if(etype instanceof AmmoType) {
        			atype = (AmmoType)etype;
        		}
        		if(!UnitUtil.isLegal(unit, etype.getTechLevel())) {
        			return false;
        		}
        		if(UnitUtil.isHeatSink(etype) || UnitUtil.isJumpJet(etype)) {
        			return false;
        		}
        		if ((nType == T_OTHER && UnitUtil.isMechEquipment(etype, (Mech)unit))
        				|| (nType == T_WEAPON && UnitUtil.isMechWeapon(etype, (Mech)unit))
        				|| (nType == T_ENERGY && UnitUtil.isMechWeapon(etype, (Mech)unit) 
        					&& wtype != null && (wtype.hasFlag(WeaponType.F_ENERGY) 
        					|| (wtype.hasFlag(WeaponType.F_PLASMA) && (wtype.getAmmoType() == AmmoType.T_PLASMA))))
        				|| (nType == T_BALLISTIC && UnitUtil.isMechWeapon(etype, (Mech)unit) 
        					&& wtype != null && (wtype.hasFlag(WeaponType.F_BALLISTIC) 
        							&& (wtype.getAmmoType() != AmmoType.T_NA)))
        				|| (nType == T_MISSILE && UnitUtil.isMechWeapon(etype, (Mech)unit) 
        					&& wtype != null && ((wtype.hasFlag(WeaponType.F_MISSILE) 
        							&& (wtype.getAmmoType() != AmmoType.T_NA)) || (wtype.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR)))
        				|| (nType == T_ARTILLERY && UnitUtil.isMechWeapon(etype, (Mech)unit)
        					&& wtype != null && wtype instanceof ArtilleryWeapon)
        				|| (nType == T_PHYSICAL && UnitUtil.isPhysicalWeapon(etype))     				
        				|| (nType == T_AMMO & atype != null && UnitUtil.canUseAmmo(unit, atype))
        				) {
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

}