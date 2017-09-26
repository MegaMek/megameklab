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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import megamek.common.SmallCraft;
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
    
    private BayWeaponCriticalTree noseTree;
    private BayWeaponCriticalTree leftTree;
    private BayWeaponCriticalTree aftLeftTree;
    private BayWeaponCriticalTree rightTree;
    private BayWeaponCriticalTree aftRightTree;
    private BayWeaponCriticalTree aftTree;
    
    private JPanel nosePanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel aftLeftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel aftRightPanel = new JPanel();
    private JPanel aftPanel = new JPanel();
    
    private JPanel leftColumn = new JPanel();
    private JPanel midColumn = new JPanel();
    private JPanel rightColumn = new JPanel();
    private RefreshListener refresh;

    public DropshipCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        midColumn.setLayout(new BoxLayout(midColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        noseTree = new BayWeaponCriticalTree(SmallCraft.LOC_NOSE, eSource, refresh);
        leftTree = new BayWeaponCriticalTree(SmallCraft.LOC_LWING, eSource, refresh);
        aftLeftTree = new BayWeaponCriticalTree(SmallCraft.LOC_LWING, eSource, refresh, BayWeaponCriticalTree.AFT);
        rightTree = new BayWeaponCriticalTree(SmallCraft.LOC_RWING, eSource, refresh);
        aftRightTree = new BayWeaponCriticalTree(SmallCraft.LOC_RWING, eSource, refresh, BayWeaponCriticalTree.AFT);
        aftTree = new BayWeaponCriticalTree(SmallCraft.LOC_AFT, eSource, refresh);

        leftColumn.add(Box.createVerticalGlue());
        leftPanel.add(leftTree);
        leftTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Left Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        leftColumn.add(leftPanel);

        aftLeftPanel.add(aftLeftTree);
        aftLeftTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        aftLeftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Aft Left",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        leftColumn.add(aftLeftPanel);

        nosePanel.add(noseTree);
        noseTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        nosePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Nose",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        midColumn.add(nosePanel);

        midColumn.add(Box.createVerticalGlue());
        aftPanel.add(aftTree);
        aftTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        aftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Aft",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        midColumn.add(aftPanel);

        rightColumn.add(Box.createVerticalGlue());
        rightPanel.add(rightTree);
        rightTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Right Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        rightColumn.add(rightPanel);

        aftRightPanel.add(aftRightTree);
        aftRightTree.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        aftRightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Aft Right",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        rightColumn.add(aftRightPanel);

        mainPanel.add(leftColumn);
        mainPanel.add(midColumn);
        mainPanel.add(rightColumn);
        this.add(mainPanel);
        
        refresh();

    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        if (getSmallCraft().isSpheroid()) {
            ((TitledBorder)leftPanel.getBorder()).setTitle("Fore Left");
            ((TitledBorder)rightPanel.getBorder()).setTitle("Fore Right");
            leftTree.setFacing(BayWeaponCriticalTree.FORWARD);
            rightTree.setFacing(BayWeaponCriticalTree.FORWARD);
            aftLeftPanel.setVisible(true);
            aftRightPanel.setVisible(true);
        } else {
            ((TitledBorder)leftPanel.getBorder()).setTitle("Left Wing");
            ((TitledBorder)rightPanel.getBorder()).setTitle("Right Wing");
            leftTree.setFacing(BayWeaponCriticalTree.BOTH);
            rightTree.setFacing(BayWeaponCriticalTree.BOTH);
            aftLeftPanel.setVisible(false);
            aftRightPanel.setVisible(false);
        }

        synchronized (getSmallCraft()) {
            noseTree.rebuild();
            leftTree.rebuild();
            aftLeftTree.rebuild();
            rightTree.rebuild();
            aftRightTree.rebuild();
            aftTree.rebuild();
        }
    }
}
