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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import megamek.common.Entity;
import megamek.common.Jumpship;
import megamek.common.LocationFullException;
import megamek.common.SmallCraft;
import megamek.common.Warship;
import megamek.common.annotations.Nullable;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.BayWeaponCriticalTree;
import megameklab.com.ui.util.LocationBorder;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

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
    
    private JButton btnCopyFLS = new JButton();
    private JButton btnCopyFRS = new JButton();
    private JButton btnCopyALS = new JButton();
    private JButton btnCopyARS = new JButton();
    private JButton btnCopyLBS = new JButton();
    private JButton btnCopyRBS = new JButton();
    
    private RefreshListener refresh;

    public LargeCraftCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

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
                arcTrees[arc] = new BayWeaponCriticalTree(arc - 4, eSource, refresh, BayWeaponCriticalTree.AFT);
            } else {
                arcTrees[arc] = new BayWeaponCriticalTree(arc, eSource, refresh);
            }
        }

        leftColumn.add(Box.createVerticalGlue());
        leftPanel = createArcPanel(TestSmallCraft.ARC_FWD_LEFT, resourceMap, true, btnCopyFRS);
        btnCopyFRS.setText("Copy from " + (getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                capitalArcNames[TestSmallCraft.ARC_FWD_RIGHT] : spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]));
        leftColumn.add(leftPanel);
        bsLeftPanel = createArcPanel(Warship.LOC_LBS, resourceMap, true, btnCopyRBS);
        btnCopyRBS.setText("Copy from " + capitalArcNames[Warship.LOC_RBS]);
        leftColumn.add(bsLeftPanel);
        aftLeftPanel = createArcPanel(aftLeft, resourceMap, true, btnCopyARS);
        btnCopyARS.setText("Copy from " + (getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                capitalArcNames[aftRight] : spheroidArcNames[aftRight]));
        leftColumn.add(aftLeftPanel);
        leftColumn.add(Box.createVerticalGlue());

        nosePanel = createArcPanel(TestSmallCraft.ARC_NOSE, resourceMap, true, null);
        midColumn.add(nosePanel);
        midColumn.add(Box.createVerticalGlue());
        sysPanel = createArcPanel(TestEntity.getSystemWideLocation(getAero()), resourceMap, false, null);
        midColumn.add(sysPanel);
        midColumn.add(Box.createVerticalGlue());
        aftPanel = createArcPanel(TestSmallCraft.ARC_AFT, resourceMap, true, null);
        midColumn.add(aftPanel);
        
        rightColumn.add(Box.createVerticalGlue());
        rightPanel = createArcPanel(TestSmallCraft.ARC_FWD_RIGHT, resourceMap, true, btnCopyFLS);
        btnCopyFLS.setText("Copy from " + (getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                capitalArcNames[TestSmallCraft.ARC_FWD_LEFT] : spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]));
        rightColumn.add(rightPanel);
        bsRightPanel = createArcPanel(Warship.LOC_RBS, resourceMap, true, btnCopyLBS);
        btnCopyLBS.setText("Copy from " + capitalArcNames[Warship.LOC_LBS]);
        rightColumn.add(bsRightPanel);
        aftRightPanel = createArcPanel(aftRight, resourceMap, true, btnCopyALS);
        btnCopyALS.setText("Copy from " + (getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)?
                capitalArcNames[aftLeft] : spheroidArcNames[aftLeft]));
        rightColumn.add(aftRightPanel);
        rightColumn.add(Box.createVerticalGlue());

        mainPanel.add(leftColumn);
        mainPanel.add(midColumn);
        mainPanel.add(rightColumn);
        add(mainPanel);
        
        btnCopyFLS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_FLS, Jumpship.LOC_FRS);
            } else if (getAero().isSpheroid()) {
                copyLocation(SmallCraft.LOC_LWING, SmallCraft.LOC_RWING, true, false);
            } else {
                copyLocation(SmallCraft.LOC_LWING, SmallCraft.LOC_RWING, true, true);
            }
        });
        btnCopyFRS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_FRS, Jumpship.LOC_FLS);
            } else if (getAero().isSpheroid()) {
                copyLocation(SmallCraft.LOC_RWING, SmallCraft.LOC_LWING, true, false);
            } else {
                copyLocation(SmallCraft.LOC_RWING, SmallCraft.LOC_LWING, true, true);
            }
        });
        btnCopyALS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_ALS, Jumpship.LOC_ARS);
            } else {
                copyLocation(SmallCraft.LOC_LWING, SmallCraft.LOC_RWING, false, true);
            }
        });
        btnCopyARS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_ARS, Jumpship.LOC_ALS);
            } else {
                copyLocation(SmallCraft.LOC_RWING, SmallCraft.LOC_LWING, false, true);
            }
        });
        btnCopyLBS.addActionListener(ev -> copyLocation(Warship.LOC_LBS, Warship.LOC_RBS));
        btnCopyRBS.addActionListener(ev -> copyLocation(Warship.LOC_RBS, Warship.LOC_LBS));

        refresh();
    }
    
    private JPanel createArcPanel(int arc, ResourceBundle resourceMap,
            boolean isWeaponArc, @Nullable JButton copyButton) {
        JPanel arcPanel = new JPanel(new GridBagLayout());
        arcTrees[arc].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
        String arcTitle = getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP) ? capitalArcNames[arc] : spheroidArcNames[arc];
        arcPanel.setBorder(BorderFactory.createTitledBorder(
                new LocationBorder(Color.BLACK, 2f),
                " " + arcTitle + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

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
            
            if (null != copyButton) {
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                arcPanel.add(copyButton, gbc);
            }
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
        this.refresh = refresh;
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
                btnCopyFLS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]);
                btnCopyFRS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]);
                btnCopyALS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_AFT_LEFT]);
                btnCopyARS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_AFT_RIGHT]);
            } else {
                ((TitledBorder)leftPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_LWING]);
                ((TitledBorder)rightPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_RWING]);
                arcTrees[TestSmallCraft.ARC_LWING].setFacing(BayWeaponCriticalTree.BOTH);
                arcTrees[TestSmallCraft.ARC_RWING].setFacing(BayWeaponCriticalTree.BOTH);
                aftLeftPanel.setVisible(false);
                aftRightPanel.setVisible(false);
                btnCopyFLS.setText("Copy from " + aerodyneArcNames[TestSmallCraft.ARC_LWING]);
                btnCopyFRS.setText("Copy from " + aerodyneArcNames[TestSmallCraft.ARC_RWING]);
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
    
    private void copyLocation(int from, int to) {
        copyLocation(from, to, true, true);
    }
    
    private void copyLocation(int from, int to, boolean forward, boolean rear) {
        try {
            UnitUtil.copyLocationEquipment(getAero(), from, to, forward, rear);
        } catch (LocationFullException ex) {
            JOptionPane.showMessageDialog(this, "Insufficient space", "Location Full",
                    JOptionPane.WARNING_MESSAGE);
        }
        if (null != refresh) {
            refresh.refreshAll();
        }
    }
}
