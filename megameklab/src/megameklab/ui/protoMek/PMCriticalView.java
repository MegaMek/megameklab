/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.verifier.TestProtomech;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.ProtomekMountList;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final ProtomekMountList mainGunList;
    private final ProtomekMountList torsoList;
    private final ProtomekMountList leftList;
    private final ProtomekMountList rightList;
    private final ProtomekMountList bodyList;

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
        mainGunList = new ProtomekMountList(eSource, refresh, Protomech.LOC_MAINGUN);
        mainGunPanel.add(mainGunList);
        mainGunPanel.add(mainGunSpace);
        
        leftArmPanel.setBorder(CritCellUtil.locationBorder("Left Arm"));
        leftList = new ProtomekMountList(eSource, refresh, Protomech.LOC_LARM);
        leftArmPanel.add(leftList);
        leftArmPanel.add(leftSpace);
        leftArmPanel.add(leftWeight);

        Box torsoPanel = Box.createVerticalBox();
        torsoPanel.setBorder(CritCellUtil.locationBorder("Torso"));
        torsoList = new ProtomekMountList(eSource, refresh, Protomech.LOC_TORSO);
        torsoPanel.add(torsoList);
        torsoPanel.add(torsoSpace);
        torsoPanel.add(torsoWeight);

        rightArmPanel.setBorder(CritCellUtil.locationBorder("Right Arm"));
        rightList = new ProtomekMountList(eSource, refresh, Protomech.LOC_RARM);
        rightArmPanel.add(rightList);
        rightArmPanel.add(rightSpace);
        rightArmPanel.add(rightWeight);

        Box bodyPanel = Box.createVerticalBox();
        bodyPanel.setBorder(CritCellUtil.locationBorder("General"));
        bodyList = new ProtomekMountList(eSource, refresh, Protomech.LOC_BODY);
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
        
        Map<Integer, List<Mounted>> eqByLocation = getProtoMek().getEquipment().stream()
                .collect(Collectors.groupingBy(Mounted::getLocation));
        for (int location = 0; location < getProtoMek().locations(); location++) {
            int slotsUsed = 0;
            double weightUsed = 0.0;
            if (eqByLocation.containsKey(location)) {
                for (Mounted m : eqByLocation.get(location)) {
                    if (TestProtomech.requiresSlot(m.getType())) {
                        slotsUsed++;
                    }
                    weightUsed += m.getTonnage();
                }
            }

            switch (location) {
                case Protomech.LOC_TORSO:
                    torsoSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtoMek()));
                    torsoWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case Protomech.LOC_LARM:
                    leftSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtoMek()));
                    leftWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case Protomech.LOC_RARM:
                    rightSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtoMek()));
                    rightWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtoMek()) * 1000));
                    break;
                case Protomech.LOC_MAINGUN:
                    mainGunSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtoMek()));
                    break;
            }
        }
    }

}
