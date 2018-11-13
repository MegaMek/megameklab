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
package megameklab.com.ui.protomek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.ProtomekMountList;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

/**
 * Displays protomech equipment by location
 * 
 * @author Neoancient
 *
 */
public class ProtomekCriticalView extends IView {
    private static final long serialVersionUID = 1556099212988732928L;
    
    private final JPanel mainGunPanel = new JPanel();
    private final JPanel torsoPanel = new JPanel();
    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel bodyPanel = new JPanel();

    private final ProtomekMountList mainGunList;
    private final ProtomekMountList torsoList;
    private final ProtomekMountList leftList;
    private final ProtomekMountList rightList;
    private final ProtomekMountList bodyList;

    private final JLabel mainGunSpace = new JLabel("",JLabel.LEFT);
    private final JLabel torsoSpace = new JLabel("",JLabel.LEFT);
    private final JLabel leftSpace = new JLabel("",JLabel.LEFT);
    private final JLabel rightSpace = new JLabel("",JLabel.LEFT);

    private final JLabel torsoWeight = new JLabel("",JLabel.LEFT);
    private final JLabel leftWeight = new JLabel("",JLabel.LEFT);
    private final JLabel rightWeight = new JLabel("",JLabel.LEFT);

    public ProtomekCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(mainGunPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(torsoPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        mainPanel.add(rightPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(bodyPanel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        mainGunPanel.setLayout(new GridBagLayout());
        mainGunPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Main Gun",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        mainGunList = new ProtomekMountList(eSource, refresh, Protomech.LOC_MAINGUN);
        gbc.gridy = 0;
        mainGunPanel.add(mainGunList, gbc);
        gbc.gridy++;
        mainGunPanel.add(mainGunSpace, gbc);
        
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Left Arm",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        leftList = new ProtomekMountList(eSource, refresh, Protomech.LOC_LARM);
        gbc.gridy = 0;
        leftPanel.add(leftList, gbc);
        gbc.gridy++;
        leftPanel.add(leftSpace, gbc);
        gbc.gridy++;
        leftPanel.add(leftWeight, gbc);
        gbc.gridy++;

        torsoPanel.setLayout(new GridBagLayout());
        torsoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Torso", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        torsoList = new ProtomekMountList(eSource, refresh, Protomech.LOC_TORSO);
        gbc.gridy = 0;
        torsoPanel.add(torsoList, gbc);
        gbc.gridy++;
        torsoPanel.add(torsoSpace, gbc);
        gbc.gridy++;
        torsoPanel.add(torsoWeight, gbc);
        gbc.gridy++;

        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Right Arm",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        rightList = new ProtomekMountList(eSource, refresh, Protomech.LOC_RARM);
        gbc.gridy = 0;
        rightPanel.add(rightList, gbc);
        gbc.gridy++;
        rightPanel.add(rightSpace, gbc);
        gbc.gridy++;
        rightPanel.add(rightWeight, gbc);

        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "General", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        bodyList = new ProtomekMountList(eSource, refresh, Protomech.LOC_BODY);
        bodyPanel.add(bodyList);

        this.add(mainPanel);

    }

    public void updateRefresh(RefreshListener refresh) {
        mainGunList.setRefresh(refresh);
        torsoList.setRefresh(refresh);
        leftList.setRefresh(refresh);
        rightList.setRefresh(refresh);
        bodyList.setRefresh(refresh);
    }

    public void refresh() {
        mainGunPanel.setVisible(getProtomech().hasMainGun());
        leftPanel.setVisible(!getProtomech().isQuad());
        rightPanel.setVisible(!getProtomech().isQuad());

        mainGunList.refreshContents();
        torsoList.refreshContents();
        leftList.refreshContents();
        rightList.refreshContents();
        bodyList.refreshContents();
        
        Map<Integer, List<Mounted>> eqByLocation = getProtomech().getEquipment().stream()
                .collect(Collectors.groupingBy(m -> m.getLocation()));
        for (int location = 0; location < getProtomech().locations(); location++) {
            int slotsUsed = 0;
            double weightUsed = 0.0;
            if (eqByLocation.containsKey(location)) {
                for (Mounted m : eqByLocation.get(location)) {
                    if (TestProtomech.requiresSlot(m.getType())) {
                        slotsUsed++;
                    }
                    weightUsed += m.getType().getTonnage(getProtomech());
                }
            }

            switch (location) {
                case Protomech.LOC_TORSO:
                    torsoSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    torsoWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_LARM:
                    leftSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    leftWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_RARM:
                    rightSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    rightWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_MAINGUN:
                    mainGunSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    break;
            }
        }
    }

}
