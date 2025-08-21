/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.largeAero;

import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import megamek.client.ui.util.UIUtil;
import megamek.common.annotations.Nullable;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Jumpship;
import megamek.common.units.SmallCraft;
import megamek.common.units.Warship;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSmallCraft;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.util.BayWeaponCriticalTree;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * The Crit Slots view for Large Craft (including Small Craft) Aft side arcs on spheroid small craft and DropShips are
 * implemented as rear-mounted weapons in the left/right side locations but here they are shown as separate locations
 * both to make it less confusing to the user and for the need to maintain a separate count of the number of slots
 * filled in that arc.
 *
 * @author Neoancient
 * @author Simon (Juliez)
 */
public class LACriticalView extends IView {
    // Maximum number of arcs for small craft / DropShip; aerodyne only use four, spheroid
    // and non-WarShip capital ships use 6, plus one for system-wide
    private static final int MAX_ARCS = 9;

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private final JComponent fwdLeftPanel;
    private final JComponent bsLeftPanel;
    private final JComponent aftLeftPanel;
    private final JComponent rightPanel;
    private final JComponent bsRightPanel;
    private final JComponent aftRightPanel;

    private final BayWeaponCriticalTree[] arcTrees = new BayWeaponCriticalTree[MAX_ARCS];
    private final String[] aerodyneArcNames = resourceMap.getString("DropshipCriticalView.aerodyneArcs.values")
          .split(",");
    private final String[] spheroidArcNames = resourceMap.getString("DropshipCriticalView.spheroidArcs.values")
          .split(",");
    private final String[] capitalArcNames = resourceMap.getString("DropshipCriticalView.capitalArcs.values")
          .split(",");
    private final JLabel[] lblSlotCount = new JLabel[MAX_ARCS];
    private final JLabel[] lblExtraTonnage = new JLabel[MAX_ARCS];

    private final JButton btnCopyFLS = new JButton();
    private final JButton btnCopyFRS = new JButton();
    private final JButton btnCopyALS = new JButton();
    private final JButton btnCopyARS = new JButton();

    private RefreshListener refresh;

    public LACriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        Box mainPanel = Box.createHorizontalBox();
        Box leftColumn = Box.createVerticalBox();
        Box midColumn = Box.createVerticalBox();
        Box rightColumn = Box.createVerticalBox();

        // These locations do not have the same indices for SC/DS and JS/WS/SS
        final int aftLeft = isJumpShip() ? Jumpship.LOC_ALS : TestSmallCraft.ARC_AFT_LEFT;
        final int aftRight = isJumpShip() ? Jumpship.LOC_ARS : TestSmallCraft.ARC_AFT_RIGHT;

        for (int arc = 0; arc < MAX_ARCS; arc++) {
            if (isSmallCraft()
                  && ((arc == TestSmallCraft.ARC_AFT_LEFT)
                  || (arc == TestSmallCraft.ARC_AFT_RIGHT))) {
                arcTrees[arc] = new BayWeaponCriticalTree(arc - 4, eSource, refresh, BayWeaponCriticalTree.AFT);
            } else {
                arcTrees[arc] = new BayWeaponCriticalTree(arc, eSource, refresh);
            }
        }

        leftColumn.add(Box.createVerticalGlue());
        fwdLeftPanel = createArcPanel(TestSmallCraft.ARC_FWD_LEFT, resourceMap, true, btnCopyFRS);
        btnCopyFRS.setText("Copy from " + (isJumpShip() ?
              capitalArcNames[TestSmallCraft.ARC_FWD_RIGHT] : spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]));
        leftColumn.add(fwdLeftPanel);
        leftColumn.add(Box.createVerticalGlue());
        JButton btnCopyRBS = new JButton();
        bsLeftPanel = createArcPanel(Warship.LOC_LBS, resourceMap, true, btnCopyRBS);
        btnCopyRBS.setText("Copy from " + capitalArcNames[Warship.LOC_RBS]);
        leftColumn.add(bsLeftPanel);
        leftColumn.add(Box.createVerticalGlue());
        aftLeftPanel = createArcPanel(aftLeft, resourceMap, true, btnCopyARS);
        btnCopyARS.setText("Copy from " + (isJumpShip() ? capitalArcNames[aftRight] : spheroidArcNames[aftRight]));
        leftColumn.add(aftLeftPanel);
        leftColumn.add(Box.createVerticalGlue());

        JComponent nosePanel = createArcPanel(TestSmallCraft.ARC_NOSE, resourceMap, true, null);
        midColumn.add(nosePanel);
        midColumn.add(Box.createVerticalGlue());
        JComponent sysPanel = createArcPanel(TestEntity.getSystemWideLocation(getAero()), resourceMap, false, null);
        sysPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        midColumn.add(sysPanel);
        midColumn.add(Box.createVerticalGlue());
        JComponent aftPanel = createArcPanel(TestSmallCraft.ARC_AFT, resourceMap, true, null);
        midColumn.add(aftPanel);

        rightColumn.add(Box.createVerticalGlue());
        rightPanel = createArcPanel(TestSmallCraft.ARC_FWD_RIGHT, resourceMap, true, btnCopyFLS);
        btnCopyFLS.setText("Copy from " + (isJumpShip() ?
              capitalArcNames[TestSmallCraft.ARC_FWD_LEFT] : spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]));
        rightColumn.add(rightPanel);
        rightColumn.add(Box.createVerticalGlue());
        JButton btnCopyLBS = new JButton();
        bsRightPanel = createArcPanel(Warship.LOC_RBS, resourceMap, true, btnCopyLBS);
        btnCopyLBS.setText("Copy from " + capitalArcNames[Warship.LOC_LBS]);
        rightColumn.add(bsRightPanel);
        rightColumn.add(Box.createVerticalGlue());
        aftRightPanel = createArcPanel(aftRight, resourceMap, true, btnCopyALS);
        btnCopyALS.setText("Copy from " + (isJumpShip() ? capitalArcNames[aftLeft] : spheroidArcNames[aftLeft]));
        rightColumn.add(aftRightPanel);
        rightColumn.add(Box.createVerticalGlue());

        mainPanel.add(leftColumn);
        mainPanel.add(midColumn);
        mainPanel.add(rightColumn);
        add(mainPanel);

        btnCopyFLS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_FLS, Jumpship.LOC_FRS);
            } else {
                copyLocation(SmallCraft.LOC_LEFT_WING, SmallCraft.LOC_RIGHT_WING, true, !getAero().isSpheroid());
            }
        });
        btnCopyFRS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_FRS, Jumpship.LOC_FLS);
            } else {
                copyLocation(SmallCraft.LOC_RIGHT_WING, SmallCraft.LOC_LEFT_WING, true, !getAero().isSpheroid());
            }
        });
        btnCopyALS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_ALS, Jumpship.LOC_ARS);
            } else {
                copyLocation(SmallCraft.LOC_LEFT_WING, SmallCraft.LOC_RIGHT_WING, false, true);
            }
        });
        btnCopyARS.addActionListener(ev -> {
            if (getAero() instanceof Jumpship) {
                copyLocation(Jumpship.LOC_ARS, Jumpship.LOC_ALS);
            } else {
                copyLocation(SmallCraft.LOC_RIGHT_WING, SmallCraft.LOC_LEFT_WING, false, true);
            }
        });
        btnCopyLBS.addActionListener(ev -> copyLocation(Warship.LOC_LBS, Warship.LOC_RBS));
        btnCopyRBS.addActionListener(ev -> copyLocation(Warship.LOC_RBS, Warship.LOC_LBS));

        refresh();
    }

    private JComponent createArcPanel(int arc, ResourceBundle resourceMap, boolean isWeaponArc,
          @Nullable JButton copyButton) {
        Box arcPanel = Box.createVerticalBox();
        String arcTitle = isJumpShip() ? capitalArcNames[arc] : spheroidArcNames[arc];
        arcPanel.setBorder(CritCellUtil.locationBorder(arcTitle));
        arcPanel.add(arcTrees[arc]);

        lblSlotCount[arc] = new JLabel();
        lblSlotCount[arc].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        lblSlotCount[arc].setToolTipText(resourceMap.getString("DropshipCriticalView.lblSlotCount.tooltip"));
        lblExtraTonnage[arc] = new JLabel();
        lblExtraTonnage[arc].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        lblExtraTonnage[arc].setToolTipText(resourceMap.getString("DropshipCriticalView.lblExtraWeight.tooltip"));

        if (isWeaponArc) {
            arcPanel.add(lblSlotCount[arc]);
            arcPanel.add(lblExtraTonnage[arc]);
            if (null != copyButton) {
                arcPanel.add(Box.createVerticalStrut(2));
                copyButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                arcPanel.add(copyButton);
            }
        }
        return arcPanel;
    }

    public void addAllocationListeners(LABuildView abv) {
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
        if (isSmallCraft()) {
            if (getSmallCraft().isSpheroid()) {
                ((TitledBorder) fwdLeftPanel.getBorder()).setTitle(spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]);
                ((TitledBorder) rightPanel.getBorder()).setTitle(spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]);
                arcTrees[TestSmallCraft.ARC_FWD_LEFT].setFacing(BayWeaponCriticalTree.FORWARD);
                arcTrees[TestSmallCraft.ARC_FWD_RIGHT].setFacing(BayWeaponCriticalTree.FORWARD);
                aftLeftPanel.setVisible(true);
                aftRightPanel.setVisible(true);
                btnCopyFLS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_FWD_LEFT]);
                btnCopyFRS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_FWD_RIGHT]);
                btnCopyALS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_AFT_LEFT]);
                btnCopyARS.setText("Copy from " + spheroidArcNames[TestSmallCraft.ARC_AFT_RIGHT]);
            } else {
                ((TitledBorder) fwdLeftPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_LEFT_WING]);
                ((TitledBorder) rightPanel.getBorder()).setTitle(aerodyneArcNames[TestSmallCraft.ARC_RIGHT_WING]);
                arcTrees[TestSmallCraft.ARC_LEFT_WING].setFacing(BayWeaponCriticalTree.BOTH);
                arcTrees[TestSmallCraft.ARC_RIGHT_WING].setFacing(BayWeaponCriticalTree.BOTH);
                aftLeftPanel.setVisible(false);
                aftRightPanel.setVisible(false);
                btnCopyFLS.setText("Copy from " + aerodyneArcNames[TestSmallCraft.ARC_LEFT_WING]);
                btnCopyFRS.setText("Copy from " + aerodyneArcNames[TestSmallCraft.ARC_RIGHT_WING]);
            }
        }

        bsLeftPanel.setVisible(isWarShip());
        bsRightPanel.setVisible(isWarShip());

        double[] extra = isSmallCraft()
              ? TestSmallCraft.extraSlotCost(getSmallCraft())
              : TestAdvancedAerospace.extraSlotCost(getJumpship());
        for (int arc = 0; arc < extra.length; arc++) {
            arcTrees[arc].rebuild();
            lblSlotCount[arc].setText(resourceMap.getString("DropshipCriticalView.lblSlotCount.text")
                  + " " + arcTrees[arc].getSlotCount() + " / " + TestAero.slotsPerArc(getAero()));

            lblExtraTonnage[arc].setText(resourceMap.getString("DropshipCriticalView.lblExtraWeight.text")
                  + " " + (extra[arc] > 0 ? extra[arc] : "--"));
            lblExtraTonnage[arc].setForeground(extra[arc] > 0 ? null : UIUtil.uiGray());
        }
    }

    private void copyLocation(int from, int to) {
        copyLocation(from, to, true, true);
    }

    private void copyLocation(int from, int to, boolean forward, boolean rear) {
        try {
            UnitUtil.copyLocationEquipment(getAero(), from, to, forward, rear);
        } catch (LocationFullException ex) {
            PopupMessages.showLocationFullError(null);
        }
        if (null != refresh) {
            refresh.refreshAll();
        }
    }
}
