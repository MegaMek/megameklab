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

package megameklab.com.ui.Infantry.tabs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.EntityMovementMode;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7985608549543235815L;
	
	public static final int M_FOOT    = 0;
	public static final int M_JUMP    = 1;
	public static final int M_MOTOR   = 2;
	public static final int M_HOVER   = 3;
	public static final int M_TRACKED = 4;
	public static final int M_WHEELED = 5;

	
	private String[] techTypes =
        { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    private JComboBox techType = new JComboBox(techTypes);
    private String[] isTechLevels =
        { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private JComboBox techLevel = new JComboBox(isTechLevels);
    private String[] motiveTypeArray =
        { "Foot", "Jump", "Motorized", "Mechanized (Hover)", "Mechanized (Tracked)", "Mechanized (Wheeled)"};
    private JComboBox motiveType = new JComboBox(motiveTypeArray);
    private String[] squadSizeArray =
        { "1","2","3","4", "5", "6","7","8","9","10"};
    private JComboBox squadSize = new JComboBox(squadSizeArray);
    private String[] squadNArray =
        { "1","2","3","4","5"};
    private JComboBox squadN = new JComboBox(squadNArray);
    private String[] secondaryNArray =
        { "0","1","2"};
    private JComboBox secondaryN = new JComboBox(secondaryNArray);
	
    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);
    
    private Dimension maxSize = new Dimension();

    private RefreshListener refresh = null;

	private JPanel basicPanel;
	
	
	public StructureTab(Infantry unit) {
        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(basicPanel());
        refresh();
	}
	
	public JPanel basicPanel() {
        basicPanel = new JPanel(new SpringLayout());
        maxSize.setSize(110, 20);

        basicPanel.add(createLabel("Year:", maxSize));
        basicPanel.add(era);

        basicPanel.add(createLabel("Source/Era:", maxSize));
        basicPanel.add(source);

        basicPanel.add(createLabel("Tech:", maxSize));
        basicPanel.add(techType);
        basicPanel.add(createLabel("Tech Level:", maxSize));
        basicPanel.add(techLevel);

        basicPanel.add(createLabel("Motive Type:", maxSize));
        basicPanel.add(motiveType);
        basicPanel.add(createLabel("# Squads:", maxSize));
        basicPanel.add(squadN);
        basicPanel.add(createLabel("Squad Size:", maxSize));
        basicPanel.add(squadSize);
        
        basicPanel.add(createLabel("Secondary #:", maxSize));
        basicPanel.add(secondaryN);
        
        setFieldSize(motiveType, maxSize);
        setFieldSize(squadSize, maxSize);
        setFieldSize(squadN, maxSize);
        setFieldSize(secondaryN, maxSize);
        setFieldSize(era, maxSize);
        setFieldSize(source, maxSize);
        setFieldSize(techType, maxSize);
        setFieldSize(techLevel, maxSize);
        SpringLayoutHelper.setupSpringGrid(basicPanel, 2);
        return basicPanel;
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
	
	public void addAllActionListeners() {
		motiveType.addActionListener(this);
        squadN.addActionListener(this);
        squadSize.addActionListener(this);
        secondaryN.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
    }
	
	public void removeAllActionListeners() {
		motiveType.removeActionListener(this);
        squadN.removeActionListener(this);
        squadSize.removeActionListener(this);
        secondaryN.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
    }
	
	public void refresh() {
		removeAllActionListeners();
        era.setText(Integer.toString(getInfantry().getYear()));
        source.setText(getInfantry().getSource());
        
        if (getInfantry().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
        }
 
        switch(getInfantry().getMovementMode()) {
	        case INF_JUMP:
	        	motiveType.setSelectedIndex(M_JUMP);
	        	break;
	        case INF_MOTORIZED:
	        	motiveType.setSelectedIndex(M_MOTOR);
	        	break;
	        case HOVER:
	        	motiveType.setSelectedIndex(M_HOVER);
	        	break;
	        case TRACKED:
	        	motiveType.setSelectedIndex(M_TRACKED);
	        	break;
	        case WHEELED:
	        	motiveType.setSelectedIndex(M_WHEELED);
	        	break;
	        default:
	        	motiveType.setSelectedIndex(M_FOOT);
	        	break;
        }
        squadN.setSelectedIndex(getInfantry().getSquadN()-1);
        squadSize.setSelectedIndex(getInfantry().getSquadSize()-1);
        secondaryN.setSelectedIndex(getInfantry().getSecondaryN());
        
        if (getInfantry().isMixedTech()) {
            if (getInfantry().isClan()) {

                techType.setSelectedIndex(3);
                if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getInfantry().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getInfantry().isClan()) {

            techType.setSelectedIndex(1);
            if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getInfantry().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getInfantry().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        }
        addAllActionListeners();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox combo = (JComboBox) e.getSource();
			removeAllActionListeners();
			if (combo.equals(techLevel)) {
                int unitTechLevel = techLevel.getSelectedIndex();
                
                if (getInfantry().isClan()) {
                    switch (unitTechLevel) {
                        case 0:
                            getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                            getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            addAllActionListeners();
                            techType.setSelectedIndex(1);
                            removeAllActionListeners();
                            break;
                        case 1:
                        	getInfantry().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        	getInfantry().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                        	getInfantry().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        	getInfantry().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                        	getInfantry().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                        	getInfantry().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                        	getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                        	getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            break;
                    }

                } else {
                    switch (unitTechLevel) {
                        case 0:
                        	getInfantry().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        	getInfantry().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 1:
                        	getInfantry().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        	getInfantry().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            addAllActionListeners();
                            techType.setSelectedIndex(0);
                            removeAllActionListeners();
                            break;
                        case 2:
                        	getInfantry().setTechLevel(TechConstants.T_IS_ADVANCED);
                        	getInfantry().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                        	getInfantry().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        	getInfantry().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                        	getInfantry().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                        	getInfantry().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            break;
                    }

                }

                refresh.refreshArmor();
                refresh.refreshWeapons();
                addAllActionListeners();
                return;
			}
			else if (combo.equals(techType)) {
                if ((techType.getSelectedIndex() == 1) && (!getInfantry().isClan() || getInfantry().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    getInfantry().setTechLevel(TechConstants.T_CLAN_TW);
                    getInfantry().setArmorTechLevel(TechConstants.T_CLAN_TW);
                    getInfantry().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 0) && (getInfantry().isClan() || getInfantry().isMixedTech())) {
                    techLevel.removeAllItems();

                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }

                    getInfantry().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                    getInfantry().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                    getInfantry().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 2) && (!getInfantry().isMixedTech() || getInfantry().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    // only set techlevel and armor techlevel to advanced if
                    // we're not already experimental or unofficial
                    if ((getInfantry().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getInfantry().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                    	getInfantry().setTechLevel(TechConstants.T_IS_ADVANCED);
                    	getInfantry().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                    }
                    getInfantry().setMixedTech(true);
                } else if ((techType.getSelectedIndex() == 3) && (!getInfantry().isMixedTech() || !getInfantry().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    // only set techlevel and armor techlevel to advanced if
                    // we're not already experimental or unofficial
                    if ((getInfantry().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getInfantry().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                    	getInfantry().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                    	getInfantry().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                    }
                    getInfantry().setMixedTech(true);
                } else {
                    addAllActionListeners();
                    return;
                }
                addAllActionListeners();
                removeAllActionListeners();
            }
			else if (combo.equals(motiveType)) {
                switch(motiveType.getSelectedIndex()) {
                case M_JUMP:
                	getInfantry().setMovementMode(EntityMovementMode.INF_JUMP);
                	break;
                case M_MOTOR:
                	getInfantry().setMovementMode(EntityMovementMode.INF_MOTORIZED);
                	break;
                case M_HOVER:
                	getInfantry().setMovementMode(EntityMovementMode.HOVER);
                	break;
                case M_TRACKED:
                	getInfantry().setMovementMode(EntityMovementMode.TRACKED);
                	break;
                case M_WHEELED:
                	getInfantry().setMovementMode(EntityMovementMode.WHEELED);
                	break;
                default:
                	getInfantry().setMovementMode(EntityMovementMode.INF_LEG);
                }
                //first adjust max squad size if necessary
                int currentSquadSize = squadSize.getSelectedIndex()+1;
                int maxSquadSize = getMaxSquadSize();
                squadSize.removeAllItems();
                for (int i = 1; i <= maxSquadSize; i++) {
                    squadSize.addItem(i);
                }
                if(currentSquadSize > maxSquadSize) {
                	getInfantry().setSquadSize(maxSquadSize);
                	squadSize.setSelectedIndex(maxSquadSize-1);
                }
                //now adjust squad number if necessary
                int currentSquadN = squadN.getSelectedIndex()+1;
                int maxSquadN = getMaxSquadNumber();
                squadN.removeAllItems();
                for (int i = 1; i <= maxSquadN; i++) {
                    squadN.addItem(i);
                }
                if(currentSquadN > maxSquadN) {
                	getInfantry().setSquadN(maxSquadN);
                	squadN.setSelectedIndex(maxSquadN-1);
                }
			}
			else if (combo.equals(squadSize)) {
                getInfantry().setSquadSize(squadSize.getSelectedIndex() + 1);
                getInfantry().autoSetInternal();
                int currentSquadN = squadN.getSelectedIndex()+1;
                int maxSquadN = getMaxSquadNumber();
                squadN.removeAllItems();
                for (int i = 1; i <= maxSquadN; i++) {
                    squadN.addItem(i);
                }
                if(currentSquadN > maxSquadN) {
                	getInfantry().setSquadN(maxSquadN);
                	squadN.setSelectedIndex(maxSquadN-1);
                }
            }
			else if (combo.equals(squadN)) {
                getInfantry().setSquadN(squadN.getSelectedIndex() + 1);
                getInfantry().autoSetInternal();
                int currentSquadSize = squadSize.getSelectedIndex()+1;
                int maxSquadSize = getMaxSquadSize();
                squadSize.removeAllItems();
                for (int i = 1; i <= maxSquadSize; i++) {
                    squadSize.addItem(i);
                }
                if(currentSquadSize > maxSquadSize) {
                	getInfantry().setSquadSize(maxSquadSize);
                	squadSize.setSelectedIndex(maxSquadSize-1);
                }

            }
			else if (combo.equals(secondaryN)) {
                getInfantry().setSecondaryN(secondaryN.getSelectedIndex());
                checkMainWeapon();
            }
			addAllActionListeners();
	        refresh.refreshAll();
		}	
	}
	
	private int getMaxSquadSize() {
		int maxPlatoon = 30;
		int maxSquad = 10;
		switch(motiveType.getSelectedIndex()) {
		case M_HOVER:
			maxPlatoon = 20;
			maxSquad = 5;
			break;
		case M_TRACKED:
			maxPlatoon = 28;
			maxSquad = 7;
			break;
		case M_WHEELED:
			maxPlatoon = 24;
			maxSquad = 6;
			break;
		}
		int currentSquadN = squadN.getSelectedIndex()+1;
		if((maxPlatoon / currentSquadN) < maxSquad) {
			maxSquad = (maxPlatoon / currentSquadN);
		}
		return maxSquad;
	}
	
	private int getMaxSquadNumber() {
		int maxPlatoon = 30;
		switch(motiveType.getSelectedIndex()) {
		case M_HOVER:
			maxPlatoon = 20;
			break;
		case M_TRACKED:
			maxPlatoon = 28;
			break;
		case M_WHEELED:
			maxPlatoon = 24;
			break;
		}
		int maxSquadN = maxPlatoon;
		int currentSquadSize = squadSize.getSelectedIndex()+1;
		if((maxPlatoon / currentSquadSize) < maxSquadN) {
			maxSquadN = (maxPlatoon / currentSquadSize);
		}
		return maxSquadN;
	}
	
	public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void checkMainWeapon() {
    	Mounted existingInfantryMount = null;
    	for(Mounted m : unit.getWeaponList()) {
    		if(m.getType() instanceof InfantryWeapon && m.getLocation() == Infantry.LOC_INFANTRY) {
    			existingInfantryMount = m;
    			break;
    		}
    	}
    	if(null != existingInfantryMount) {
    		UnitUtil.removeMounted(unit, existingInfantryMount);
    	}
    	
    	//if there is more than one secondary weapon per squad, then add that
        // to the unit
        // otherwise add the primary weapon
    	if ((getInfantry().getSecondaryN() < 2) || (null == getInfantry().getSecondaryWeapon())) {
    		try {
                getInfantry().addEquipment(getInfantry().getPrimaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {
            	
            }
    	} else {
    		try {
                getInfantry().addEquipment(getInfantry().getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {
            	
            }
    	}
    }

}