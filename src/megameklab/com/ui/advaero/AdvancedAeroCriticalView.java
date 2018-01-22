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
package megameklab.com.ui.advaero;

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

import megamek.common.Jumpship;
import megamek.common.Warship;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Dropship.views.AerospaceBuildView;
import megameklab.com.ui.util.BayWeaponCriticalTree;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

/**
 * For allocating advanced aerospace weapons to critical spaces.
 * 
 * @author Neoancient
 *
 */
public class AdvancedAeroCriticalView extends IView {
        
    /**
     * 
     */
    private static final long serialVersionUID = 1284488491964063L;

    // Maximum number of arcs for warships; jumpships/space stations only use six
    private static final int NUM_ARCS = 8;
    
    private JPanel nosePanel = new JPanel();
    private JPanel fwdleftPanel = new JPanel();
    private JPanel bsLeftPanel = new JPanel();
    private JPanel aftLeftPanel = new JPanel();
    private JPanel fwdrightPanel = new JPanel();
    private JPanel bsRightPanel = new JPanel();
    private JPanel aftRightPanel = new JPanel();
    private JPanel aftPanel = new JPanel();
    
    private JPanel leftColumn = new JPanel();
    private JPanel midColumn = new JPanel();
    private JPanel rightColumn = new JPanel();
    
    private BayWeaponCriticalTree arcTrees[] = new BayWeaponCriticalTree[NUM_ARCS];
    private String[] arcNames;
    private JLabel lblSlotCount[] = new JLabel[NUM_ARCS];
    private JLabel lblExtraTonnage[] = new JLabel[NUM_ARCS];
    

    public AdvancedAeroCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        arcNames = resourceMap.getString("AdvancedAeroCriticalView.arcs.values").split(","); //$NON-NLS-1$
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        midColumn.setLayout(new BoxLayout(midColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        
        for (int arc = 0; arc < getJumpship().locations(); arc++) {
            arcTrees[arc] = new BayWeaponCriticalTree(arc, eSource, refresh);
        }

        leftColumn.add(Box.createVerticalGlue());
        fwdleftPanel = createArcPanel(Jumpship.LOC_FLS, resourceMap);
        leftColumn.add(fwdleftPanel);
        if (Warship.LOC_LBS < getJumpship().locations()) {
            bsLeftPanel = createArcPanel(Warship.LOC_LBS, resourceMap);
            leftColumn.add(bsLeftPanel);
        }
        aftLeftPanel = createArcPanel(Jumpship.LOC_ALS, resourceMap);
        leftColumn.add(aftLeftPanel);
        leftColumn.add(Box.createVerticalGlue());

        nosePanel = createArcPanel(Jumpship.LOC_NOSE, resourceMap);
        midColumn.add(nosePanel);
        midColumn.add(Box.createVerticalGlue());
        aftPanel = createArcPanel(Jumpship.LOC_AFT, resourceMap);
        midColumn.add(aftPanel);
        
        rightColumn.add(Box.createVerticalGlue());
        fwdrightPanel = createArcPanel(Jumpship.LOC_FRS, resourceMap);
        rightColumn.add(fwdrightPanel);
        if (Warship.LOC_RBS < getJumpship().locations()) {
            bsRightPanel = createArcPanel(Warship.LOC_RBS, resourceMap);
            rightColumn.add(bsRightPanel);
        }
        aftRightPanel = createArcPanel(Jumpship.LOC_ARS, resourceMap);
        rightColumn.add(aftRightPanel);
        rightColumn.add(Box.createVerticalGlue());

        mainPanel.add(leftColumn);
        mainPanel.add(midColumn);
        mainPanel.add(rightColumn);
        add(mainPanel);
        
        refresh();

    }
    
    private JPanel createArcPanel(int arc, ResourceBundle resourceMap) {
        JPanel arcPanel = new JPanel(new GridBagLayout());
        arcTrees[arc].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        arcPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), arcNames[arc],
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        arcPanel.add(arcTrees[arc], gbc);
        
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
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        lbl = new JLabel(String.format(resourceMap.getString("DropshipCriticalView.lblMaxSlots.format"), //$NON-NLS-1$
                TestSmallCraft.SLOTS_PER_ARC));
        lbl.setToolTipText(resourceMap.getString("DropshipCriticalView.lblMaxSlots.format")); //$NON-NLS-1$
        arcPanel.add(lbl, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        lbl = new JLabel(resourceMap.getString("DropshipCriticalView.lblExtraWeight.text")); //$NON-NLS-1$
        arcPanel.add(lbl, gbc);
        lblExtraTonnage[arc] = new JLabel();
        gbc.gridx = 1;
        arcPanel.add(lblExtraTonnage[arc], gbc);
        
        return arcPanel;
    }
    
    public void addAllocationListeners(AerospaceBuildView abv) {
        for (BayWeaponCriticalTree tree : arcTrees) {
            abv.addArcView(tree);
        }
    }

    public void updateRefresh(RefreshListener refresh) {
        for (BayWeaponCriticalTree tree : arcTrees) {
            if (null != tree) {
                tree.updateRefresh(refresh);
            }
        }
    }

    public void refresh() {
        double[] extra = TestAdvancedAerospace.extraSlotCost(getJumpship());
        for (int arc = 0; arc < extra.length; arc++) {
            arcTrees[arc].rebuild();
            arcTrees[arc].repaint();
            lblSlotCount[arc].setText(String.valueOf(arcTrees[arc].getSlotCount()));
            lblExtraTonnage[arc].setText(String.valueOf(extra[arc]));
        }
        
    }
}