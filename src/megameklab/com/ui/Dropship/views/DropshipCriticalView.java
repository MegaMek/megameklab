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
package megameklab.com.ui.Dropship.views;

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

import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.BayWeaponCriticalTree;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

/**
 * For allocating Small Craft and Dropship weapons to critical spaces. Aft side arcs are implemented
 * as rear-mounted weapons in the left/right side locations but here they are shown as separate locations
 * both to make it less confusing to the user and for the need to maintain a separate count of the number
 * of slots filled in that arc.
 *
 * @author Neoancient
 *
 */
public class DropshipCriticalView extends IView {

    /**
     *
     */
    private static final long serialVersionUID = -3093586215625228103L;

    // Maximum number of arcs for small craft/dropship; aerodyne only use four
    private static final int NUM_ARCS = 6;

    private JPanel nosePanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel aftLeftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel aftRightPanel = new JPanel();
    private JPanel aftPanel = new JPanel();

    private JPanel leftColumn = new JPanel();
    private JPanel midColumn = new JPanel();
    private JPanel rightColumn = new JPanel();

    private BayWeaponCriticalTree arcTrees[] = new BayWeaponCriticalTree[NUM_ARCS];
    private String aerodyneArcNames[];
    private String spheroidArcNames[];
    private JLabel lblSlotCount[] = new JLabel[NUM_ARCS];
    private JLabel lblExtraTonnage[] = new JLabel[NUM_ARCS];


    public DropshipCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        aerodyneArcNames = resourceMap.getString("DropshipCriticalView.aerodyneArcs.values").split(","); //$NON-NLS-1$
        spheroidArcNames = resourceMap.getString("DropshipCriticalView.spheroidArcs.values").split(","); //$NON-NLS-1$
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        midColumn.setLayout(new BoxLayout(midColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        for (int arc = 0; arc < NUM_ARCS; arc++) {
            if (arc < getSmallCraft().locations()) {
                arcTrees[arc] = new BayWeaponCriticalTree(arc, eSource, refresh);
            } else {
                arcTrees[arc] = new BayWeaponCriticalTree(arc - 3, eSource, refresh, BayWeaponCriticalTree.AFT);
            }
        }

        leftColumn.add(Box.createVerticalGlue());
        leftPanel = createArcPanel(TestSmallCraft.ARC_FWD_LEFT, resourceMap);
        leftColumn.add(leftPanel);
        aftLeftPanel = createArcPanel(TestSmallCraft.ARC_AFT_LEFT, resourceMap);
        leftColumn.add(aftLeftPanel);
        leftColumn.add(Box.createVerticalGlue());

        nosePanel = createArcPanel(TestSmallCraft.ARC_NOSE, resourceMap);
        midColumn.add(nosePanel);
        midColumn.add(Box.createVerticalGlue());
        aftPanel = createArcPanel(TestSmallCraft.ARC_AFT, resourceMap);
        midColumn.add(aftPanel);

        rightColumn.add(Box.createVerticalGlue());
        rightPanel = createArcPanel(TestSmallCraft.ARC_FWD_RIGHT, resourceMap);
        rightColumn.add(rightPanel);
        aftRightPanel = createArcPanel(TestSmallCraft.ARC_AFT_RIGHT, resourceMap);
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
                BorderFactory.createEmptyBorder(), spheroidArcNames[arc],
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
            tree.updateRefresh(refresh);
        }
    }

    public void refresh() {
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

        double[] extra = TestSmallCraft.extraSlotCost(getSmallCraft());
        for (int arc = 0; arc < extra.length; arc++) {
            arcTrees[arc].rebuild();
            arcTrees[arc].repaint();
            lblSlotCount[arc].setText(String.valueOf(arcTrees[arc].getSlotCount()));
            lblExtraTonnage[arc].setText(String.valueOf(extra[arc]));
        }

    }
}
