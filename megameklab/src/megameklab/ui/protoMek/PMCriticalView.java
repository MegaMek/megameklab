/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;

import megamek.common.equipment.Mounted;
import megamek.common.units.ProtoMek;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.IView;
import megameklab.ui.util.ProtoMekMountList;
import megameklab.ui.util.RefreshListener;

/**
 * The Crit Slots view for a ProtoMek
 *
 * @author neoancient
 * @author Simon (Juliez)
 */
public class PMCriticalView extends IView {

    private final Box mainGunPanel = Box.createVerticalBox();
    private final Box leftArmPanel = Box.createVerticalBox();
    private final Box rightArmPanel = Box.createVerticalBox();

    private final ProtoMekMountList mainGunList;
    private final ProtoMekMountList torsoList;
    private final ProtoMekMountList leftList;
    private final ProtoMekMountList rightList;
    private final ProtoMekMountList bodyList;

    private final JLabel mainGunSpace = new JLabel();
    private final JLabel torsoSpace = new JLabel();
    private final JLabel leftSpace = new JLabel();
    private final JLabel rightSpace = new JLabel();

    private final JLabel torsoWeight = new JLabel();
    private final JLabel leftWeight = new JLabel();
    private final JLabel rightWeight = new JLabel();

    public PMCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        Box mainPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box middlePanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();

        mainGunSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        torsoSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        leftSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        rightSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        torsoWeight.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        leftWeight.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        rightWeight.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        mainGunPanel.setBorder(CritCellUtil.locationBorder("Main Gun"));
        mainGunList = new ProtoMekMountList(eSource, refresh, ProtoMek.LOC_MAIN_GUN);
        mainGunPanel.add(mainGunList);
        mainGunPanel.add(mainGunSpace);

        leftArmPanel.setBorder(CritCellUtil.locationBorder("Left Arm"));
        leftList = new ProtoMekMountList(eSource, refresh, ProtoMek.LOC_LEFT_ARM);
        leftArmPanel.add(leftList);
        leftArmPanel.add(leftSpace);
        leftArmPanel.add(leftWeight);

        Box torsoPanel = Box.createVerticalBox();
        torsoPanel.setBorder(CritCellUtil.locationBorder("Torso"));
        torsoList = new ProtoMekMountList(eSource, refresh, ProtoMek.LOC_TORSO);
        torsoPanel.add(torsoList);
        torsoPanel.add(torsoSpace);
        torsoPanel.add(torsoWeight);

        rightArmPanel.setBorder(CritCellUtil.locationBorder("Right Arm"));
        rightList = new ProtoMekMountList(eSource, refresh, ProtoMek.LOC_RIGHT_ARM);
        rightArmPanel.add(rightList);
        rightArmPanel.add(rightSpace);
        rightArmPanel.add(rightWeight);

        Box bodyPanel = Box.createVerticalBox();
        bodyPanel.setBorder(CritCellUtil.locationBorder("General"));
        bodyList = new ProtoMekMountList(eSource, refresh, ProtoMek.LOC_BODY);
        bodyPanel.add(bodyList);

        leftPanel.add(leftArmPanel);
        middlePanel.add(mainGunPanel);
        middlePanel.add(torsoPanel);
        middlePanel.add(bodyPanel);
        rightPanel.add(rightArmPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        mainGunList.setRefresh(refresh);
        torsoList.setRefresh(refresh);
        leftList.setRefresh(refresh);
        rightList.setRefresh(refresh);
        bodyList.setRefresh(refresh);
    }

    public void refresh() {
        mainGunPanel.setVisible(getProtoMek().hasMainGun());
        leftArmPanel.setVisible(!getProtoMek().isQuad());
        rightArmPanel.setVisible(!getProtoMek().isQuad());

        mainGunList.refreshContents();
        torsoList.refreshContents();
        leftList.refreshContents();
        rightList.refreshContents();
        bodyList.refreshContents();

        Map<Integer, List<Mounted<?>>> eqByLocation = getProtoMek().getEquipment().stream()
              .collect(Collectors.groupingBy(Mounted::getLocation));
        for (int location = 0; location < getProtoMek().locations(); location++) {
            int slotsUsed = 0;
            double weightUsed = 0.0;
            if (eqByLocation.containsKey(location)) {
                for (Mounted<?> m : eqByLocation.get(location)) {
                    if (TestProtoMek.requiresSlot(m.getType())) {
                        slotsUsed++;
                    }
                    weightUsed += m.getTonnage();
                }
            }

            switch (location) {
                case ProtoMek.LOC_TORSO:
                    torsoSpace.setText("Slots: " + slotsUsed
                          + "/" + TestProtoMek.maxSlotsByLocation(location, getProtoMek()));
                    torsoWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                          TestProtoMek.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case ProtoMek.LOC_LEFT_ARM:
                    leftSpace.setText("Slots: " + slotsUsed
                          + "/" + TestProtoMek.maxSlotsByLocation(location, getProtoMek()));
                    leftWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                          TestProtoMek.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case ProtoMek.LOC_RIGHT_ARM:
                    rightSpace.setText("Slots: " + slotsUsed
                          + "/" + TestProtoMek.maxSlotsByLocation(location, getProtoMek()));
                    rightWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                          TestProtoMek.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case ProtoMek.LOC_MAIN_GUN:
                    mainGunSpace.setText("Slots: " + slotsUsed
                          + "/" + TestProtoMek.maxSlotsByLocation(location, getProtoMek()));
                    break;
            }
        }
    }

}
