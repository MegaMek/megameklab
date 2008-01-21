/*
 * MekWars - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */


package ui.views;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.EquipmentType;
import megamek.common.Mech;

public class ArmorView extends JPanel{
    
    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;

    public ArmorView(Mech unit) {
        JPanel mainPanel = new JPanel();
        JPanel armorPanel = new JPanel();
        JPanel masterPanel = new JPanel();
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        armorPanel.setLayout(new BoxLayout(armorPanel, BoxLayout.Y_AXIS));

        JPanel headPanel = new JPanel();
        JPanel torsoPanel = new JPanel();
        JPanel legPanel = new JPanel();

        JPanel laPanel = new JPanel();
        JPanel raPanel = new JPanel();
        JPanel llPanel = new JPanel();
        JPanel rlPanel = new JPanel();
        JPanel ltPanel = new JPanel();
        JPanel rtPanel = new JPanel();
        JPanel ctPanel = new JPanel();

        JPanel headArmorPanel = new JPanel();
        JPanel laArmorPanel = new JPanel();
        JPanel raArmorPanel = new JPanel();
        JPanel llArmorPanel = new JPanel();
        JPanel rlArmorPanel = new JPanel();
        JPanel ltArmorPanel = new JPanel();
        JPanel rtArmorPanel = new JPanel();
        JPanel ctArmorPanel = new JPanel();

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        headArmorPanel.setLayout(new BoxLayout(headArmorPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        synchronized (unit) {
            String armorName = EquipmentType.getArmorTypeName(unit.getArmorType());
            String isName = EquipmentType.getStructureTypeName(unit.getStructureType());

            if (armorName.equalsIgnoreCase("Standard"))
                armorName = "Armor";
            if (isName.equalsIgnoreCase("Standard"))
                isName = "Internal";
            for (int location = 0; location < unit.locations(); location++) {
                 Vector<String> armorNames = new Vector<String>(3, 1);
 
                armorNames.add(armorName + ": " + Math.max(0, unit.getArmor(location)) + "/" + unit.getOArmor(location));

                if (unit.hasRearArmor(location)) {
                    armorNames.add("" + armorName + "(r): " + Math.max(0, unit.getArmor(location, true)) + "/" + unit.getOArmor(location, true));
                }

                armorNames.add(isName + ": " + Math.max(0, unit.getInternal(location)) + "/" + unit.getOInternal(location));

                JList ArmorSlotList = new JList(armorNames);
                ArmorSlotList.setVisibleRowCount(armorNames.size());
                ArmorSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                ArmorSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                ArmorSlotList.setName("armor" + location);
                switch (location) {
                case Mech.LOC_HEAD:
                    headArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_LARM:
                    laArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_RARM:
                    raArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_CT:
                    ctArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_LT:
                    ltArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_RT:
                    rtArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_LLEG:
                    llArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                case Mech.LOC_RLEG:
                    rlArmorPanel.add(ArmorSlotList);
                    ArmorSlotList.setBackground(Color.green);
                    break;
                }

                mainPanel.add(headPanel);

                torsoPanel.add(laPanel);
                torsoPanel.add(ltPanel);
                torsoPanel.add(ctPanel);
                torsoPanel.add(rtPanel);
                torsoPanel.add(raPanel);
                mainPanel.add(torsoPanel);

                legPanel.add(llPanel);
                legPanel.add(rlPanel);
                mainPanel.add(legPanel);

                torsoPanel = new JPanel();
                legPanel = new JPanel();
                torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
                legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

                armorPanel.add(headArmorPanel);

                torsoPanel.add(laArmorPanel);
                torsoPanel.add(ltArmorPanel);
                torsoPanel.add(ctArmorPanel);
                torsoPanel.add(rtArmorPanel);
                torsoPanel.add(raArmorPanel);
                armorPanel.add(torsoPanel);

                legPanel.add(llArmorPanel);
                legPanel.add(rlArmorPanel);
                armorPanel.add(legPanel);

            }
        }
        masterPanel.add(mainPanel);
        masterPanel.add(armorPanel);
        this.add(masterPanel);
    }
}