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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    public ProtomekCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        topPanel.add(mainGunPanel);
        mainGunPanel.setLayout(new BoxLayout(mainGunPanel, BoxLayout.Y_AXIS));
        mainGunPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Main Gun",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        mainPanel.add(topPanel);
        
        middlePanel.add(leftPanel);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Left Arm",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        middlePanel.add(torsoPanel);
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.Y_AXIS));
        torsoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Torso", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        middlePanel.add(rightPanel);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Right Arm",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        mainPanel.add(middlePanel);

        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "General", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        bottomPanel.add(bodyPanel);
        mainPanel.add(bottomPanel);
        
        mainGunList = new ProtomekMountList(eSource, refresh, Protomech.LOC_MAINGUN);
        torsoList = new ProtomekMountList(eSource, refresh, Protomech.LOC_TORSO);
        leftList = new ProtomekMountList(eSource, refresh, Protomech.LOC_LARM);
        rightList = new ProtomekMountList(eSource, refresh, Protomech.LOC_RARM);
        bodyList = new ProtomekMountList(eSource, refresh, Protomech.LOC_BODY);

        mainGunPanel.add(mainGunList);
        torsoPanel.add(torsoList);
        leftPanel.add(leftList);
        rightPanel.add(rightList);
        bodyPanel.add(bodyList);

        mainGunPanel.add(mainGunSpace);
        torsoPanel.add(torsoSpace);
        torsoPanel.add(torsoWeight);
        leftPanel.add(leftSpace);
        leftPanel.add(leftWeight);
        rightPanel.add(rightSpace);
        rightPanel.add(rightWeight);

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
