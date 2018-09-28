/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.aerospace;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import megamek.common.Entity;
import megamek.common.Jumpship;
import megamek.common.Warship;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.BayWeaponCriticalTree;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

/**
 * For allocating Large Craft (and small craft) weapons to critical spaces. Aft side arcs on spheroid
 * small craft and dropships are implemented as rear-mounted weapons in the left/right side locations
 * but here they are shown as separate locations both to make it less confusing to the user and for the
 * need to maintain a separate count of the number of slots filled in that arc.
 * 
 * @author Neoancient
 *
 */
public class LargeCraftCriticalView extends IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3093586215625228103L;
    
    // Maximum number of arcs for small craft/dropship; aerodyne only use four, spheroid
    // and non-warship capital ships use 6, plus one for system-wide
    private static final int MAX_ARCS = 9;
    
    
    private JPanel nosePanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel bsLeftPanel = new JPanel();
    private JPanel aftLeftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel bsRightPanel = new JPanel();
    private JPanel aftRightPanel = new JPanel();
    private JPanel aftPanel = new JPanel();
    private JPanel sysPanel = new JPanel();
    
    private JPanel leftColumn = new JPanel();
    private JPanel midColumn = new JPanel();
    private JPanel rightColumn = new JPanel();
    
    private BayWeaponCriticalTree arcTrees[] = new BayWeaponCriticalTree[MAX_ARCS];
    private String aerodyneArcNames[];
    private String spheroidArcNames[];
    private String capitalArcNames[];
    private JLabel lblSlotCount[] = new JLabel[MAX_ARCS];
    private JLabel lblSlotsPerArc[] = new JLabel[MAX_ARCS];
    private JLabel lblExtraTonnage[] = new JLabel[MAX_ARCS];
    

    public LargeCraftCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        aerodyneArcNames = resourceMap.getString("DropshipCriticalView.aerodyneArcs.values").split(","); //$NON-NLS-1$
        spheroidArcNames = resourceMap.getString("DropshipCriticalView.spheroidArcs.values").split(","); //$NON-NLS-1$
        capitalArcNames = resourceMap.getString("DropshipCriticalView.capitalArcs.values").split(","); //$NON-NLS-1$
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        midColumn.setLayout(new BoxLayout(midColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        
        // These locations do not have the same indices for SC/DS and JS/WS/SS
        final int aftLeft = getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                Jumpship.LOC_ALS : TestSmallCraft.ARC_AFT_LEFT;
        final int aftRight = getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                Jumpship.LOC_ARS : TestSmallCraft.ARC_AFT_RIGHT;
        
        for (int arc = 0; arc < MAX_ARCS; arc++) {
            if (getAero().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                    && ((arc == TestSmallCraft.ARC_AFT_LEFT)
                            || (arc == TestSmallCraft.ARC_AFT_RIGHT))) {
                arcTrees[arc] = new BayWeaponCriticalTree(arc - 3, eSource, refresh, BayWeaponCriticalTree.AFT);
            } else {
                arcTrees[arc] = new BayWeaponCriticalTree(arc, eSource, refresh);
            }
        }

        leftColumn.add(Box.createVerticalGlue());
        leftPanel = createArcPanel(TestSmallCraft.ARC_FWD_LEFT, resourceMap);
        leftColumn.add(leftPanel);
        bsLeftPanel = createArcPanel(Warship.LOC_LBS, resourceMap);
        leftColumn.add(bsLeftPanel);
        aftLeftPanel = createArcPanel(aftLeft, resourceMap);
        leftColumn.add(aftLeftPanel);
        leftColumn.add(Box.createVerticalGlue());

        nosePanel = createArcPanel(TestSmallCraft.ARC_NOSE, resourceMap);
        midColumn.add(nosePanel);
        midColumn.add(Box.createVerticalGlue());
        sysPanel = createArcPanel(TestEntity.getSystemWideLocation(getAero()), resourceMap, false);
        midColumn.add(sysPanel);
        midColumn.add(Box.createVerticalGlue());
        aftPanel = createArcPanel(TestSmallCraft.ARC_AFT, resourceMap);
        midColumn.add(aftPanel);
        
        rightColumn.add(Box.createVerticalGlue());
        rightPanel = createArcPanel(TestSmallCraft.ARC_FWD_RIGHT, resourceMap);
        rightColumn.add(rightPanel);
        bsRightPanel = createArcPanel(Warship.LOC_RBS, resourceMap);
        rightColumn.add(bsRightPanel);
        aftRightPanel = createArcPanel(aftRight, resourceMap);
        rightColumn.add(aftRightPanel);
        rightColumn.add(Box.createVerticalGlue());

        mainPanel.add(leftColumn);
        mainPanel.add(midColumn);
        mainPanel.add(rightColumn);
        add(mainPanel);
        
        refresh();
    }
    
    private JPanel createArcPanel(int arc, ResourceBundle resourceMap) {
        return createArcPanel(arc, resourceMap, true);
    }
    
    private JPanel createArcPanel(int arc, ResourceBundle resourceMap, boolean isWeaponArc) {
        JPanel arcPanel = new JPanel(new GridBagLayout());
        arcTrees[arc].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        arcPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                        capitalArcNames[arc] : spheroidArcNames[arc],
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        arcPanel.add(arcTrees[arc], gbc);
        
        if (isWeaponArc) {
            lblSlotCount[arc] = new JLabel();
            JLabel lbl = new JLabel(resourceMap.getString("DropshipCriticalView.lblSlotCount.text")); //$NON-NLS-1$
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            arcPanel.add(lbl, gbc);
            gbc.gridx = 1;
            arcPanel.add(lblSlotCount[arc], gbc);
            lblSlotCount[arc].setToolTipText(resourceMap.getString("DropshipCriticalView.lblSlotCount.tooltip")); //$NON-NLS-1$
            
            lblSlotsPerArc[arc] = new JLabel();
            lbl = new JLabel(resourceMap.getString("DropshipCriticalView.lblMaxSlots.text")); //$NON-NLS-1$
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            arcPanel.add(lbl, gbc);
            gbc.gridx = 1;
            arcPanel.add(lblSlotsPerArc[arc], gbc);
            lblSlotsPerArc[arc].setToolTipText(resourceMap.getString("DropshipCriticalView.lblMaxSlots.tooltip")); //$NON-NLS-1$
            
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            lbl = new JLabel(resourceMap.getString("DropshipCriticalView.lblExtraWeight.text")); //$NON-NLS-1$
            arcPanel.add(lbl, gbc);
            lblExtraTonnage[arc] = new JLabel();
            gbc.gridx = 1;
            arcPanel.add(lblExtraTonnage[arc], gbc);
        } else {
            lblSlotCount[arc] = new JLabel();
            lblSlotsPerArc[arc] = new JLabel();
            lblExtraTonnage[arc] = new JLabel();
        }
        return arcPanel;
    }
    
    public void addAllocationListeners(AerospaceBuildView abv) {
        for (BayWeaponCriticalTree tree : arcTrees) {
            abv.addArcView(tree);
        }
    }

    public void updateRefresh(RefreshListener refresh) {
        for (BayWeaponCriticalTree tree : arcTrees) {
            tree.updateRefresh(refresh);
        }
    }

    public void refresh() {
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            if (getSmallCraft().isSpheroid()) {
                ((TitledBorder)leftPanel.getBorder()).setTitle(spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]);
                ((TitledBorder)rightPanel.getBorder()).setTitle(spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]);
                arcTrees[TestSmallCraft.ARC_FWD_LEFT].setFacing(BayWeaponCriticalTree.FORWARD);
                arcTrees[TestSmallCraft.ARC_FWD_RIGHT].setFacing(BayWeaponCriticalTree.FORWARD);
                aftLeftPanel.setVisible(true);
                aftRightPanel.setVisible(true);
            } else {
                ((TitledBorder)leftPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_LWING]);
                ((TitledBorder)rightPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_RWING]);
                arcTrees[TestSmallCraft.ARC_LWING].setFacing(BayWeaponCriticalTree.BOTH);
                arcTrees[TestSmallCraft.ARC_RWING].setFacing(BayWeaponCriticalTree.BOTH);
                aftLeftPanel.setVisible(false);
                aftRightPanel.setVisible(false);
            }
        }
        
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_WARSHIP)) {
            bsLeftPanel.setVisible(true);
            bsRightPanel.setVisible(true);
        } else {
            bsLeftPanel.setVisible(false);
            bsRightPanel.setVisible(false);
        }

        double[] extra = eSource.getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                ? TestSmallCraft.extraSlotCost(getSmallCraft())
                        : TestAdvancedAerospace.extraSlotCost(getJumpship());
        for (int arc = 0; arc < extra.length; arc++) {
            arcTrees[arc].rebuild();
            arcTrees[arc].repaint();
            lblSlotCount[arc].setText(String.valueOf(arcTrees[arc].getSlotCount()));
            lblSlotsPerArc[arc].setText(String.valueOf(TestAero.slotsPerArc(getAero())));
            lblExtraTonnage[arc].setText(String.valueOf(extra[arc]));
        }
        
    }
}
