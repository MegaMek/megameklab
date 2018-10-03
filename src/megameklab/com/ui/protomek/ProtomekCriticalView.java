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

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.loaders.MtfFile;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CritListCellRenderer;
import megameklab.com.util.DropTargetCriticalList;
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
    
    private JPanel mainGunPanel = new JPanel();
    private JPanel torsoPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();

    private JLabel mainGunSpace = new JLabel("",JLabel.LEFT);
    private JLabel torsoSpace = new JLabel("",JLabel.LEFT);
    private JLabel leftSpace = new JLabel("",JLabel.LEFT);
    private JLabel rightSpace = new JLabel("",JLabel.LEFT);

    private JLabel torsoWeight = new JLabel("",JLabel.LEFT);
    private JLabel leftWeight = new JLabel("",JLabel.LEFT);
    private JLabel rightWeight = new JLabel("",JLabel.LEFT);

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty = false;

    public ProtomekCriticalView(EntitySource eSource, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

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

        this.add(mainPanel);

    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        mainGunPanel.removeAll();
        leftPanel.removeAll();
        torsoPanel.removeAll();
        rightPanel.removeAll();
        bodyPanel.removeAll();

        mainGunPanel.setVisible(getProtomech().hasMainGun());
        leftPanel.setVisible(!getProtomech().isQuad());
        rightPanel.setVisible(!getProtomech().isQuad());

        Map<Integer, List<Mounted>> equipByLoc = getProtomech().getEquipment().stream()
                .collect(Collectors.groupingBy(Mounted::getLocation));
        for (int location = 0; location < getProtomech().locations(); location++) {
            List<Mounted> equipment = equipByLoc.get(location);
            Vector<String> critNames = new Vector<>(1, 1);
            int slotsUsed = 0;
            double weightUsed = 0.0;
            
            if ((null == equipment) || equipment.isEmpty()) {
                critNames.add(MtfFile.EMPTY);
            } else {
                for (Mounted m : equipment) {
                    StringBuffer critName = new StringBuffer(m.getName());
                    if (critName.length() > 25) {
                        critName.setLength(25);
                        critName.append("...");
                    }
                    if (m.isRearMounted()) {
                        critName.append(" (R)");
                    }
                    critNames.add(critName.toString());
                    if (TestProtomech.requiresSlot(m.getType())) {
                        slotsUsed++;
                        weightUsed += m.getType().getTonnage(getProtomech(), location);
                    }
                }
            }

            JList<String> criticalSlotList;
            if (location == Protomech.LOC_BODY) {
                criticalSlotList = new JList<>();
                DefaultListModel<String> model = new DefaultListModel<>();
                critNames.forEach(crit -> model.addElement(crit)); 
                criticalSlotList.setModel(model);
                criticalSlotList.setCellRenderer(new CritListCellRenderer(getProtomech(), true));
            } else {
                criticalSlotList = new DropTargetCriticalList<String>(critNames, eSource, refresh, showEmpty);
            }
            criticalSlotList.setVisibleRowCount(critNames.size());
            criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
            criticalSlotList.setName(Integer.toString(location));
            criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
            switch (location) {
                case Protomech.LOC_TORSO:
                    torsoPanel.add(criticalSlotList);
                    torsoSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    torsoWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_LARM:
                    leftPanel.add(criticalSlotList);
                    leftSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    leftWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_RARM:
                    rightPanel.add(criticalSlotList);
                    rightSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    rightWeight.setText(String.format("Weight: %3.0f/%3.0f", weightUsed * 1000,
                            TestProtomech.maxWeightByLocation(location, getProtomech()) * 1000));
                    break;
                case Protomech.LOC_BODY:
                    bodyPanel.add(criticalSlotList);
                    break;
                case Protomech.LOC_MAINGUN:
                    mainGunPanel.add(criticalSlotList);
                    mainGunSpace.setText("Slots: " + slotsUsed
                            + "/" + TestProtomech.maxSlotsByLocation(location, getProtomech()));
                    break;
            }
            mainGunPanel.add(mainGunSpace);
            torsoPanel.add(torsoSpace);
            torsoPanel.add(torsoWeight);
            leftPanel.add(leftSpace);
            leftPanel.add(leftWeight);
            rightPanel.add(rightSpace);
            rightPanel.add(rightWeight);

            torsoPanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
            mainGunPanel.repaint();
            bodyPanel.repaint();
        }
    }

}
