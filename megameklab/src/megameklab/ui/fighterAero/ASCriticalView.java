/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.formdev.flatlaf.FlatClientProperties;
import megamek.common.CriticalSlot;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.ui.SmallFontHelpTextLabel;
import megamek.common.units.Aero;
import megamek.common.verifier.TestAero;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.util.BAASBMDropTargetCriticalList;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.CriticalSlotsView;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * The Crit Slots view for a Fighter (Aerospace and Conventional)
 */
public class ASCriticalView extends IView implements CriticalSlotsView {

    private static final List<Integer> LOCATION_LIST = List.of(Aero.LOC_NOSE,
          Aero.LOC_LEFT_WING,
          Aero.LOC_RIGHT_WING,
          Aero.LOC_AFT,
          Aero.LOC_FUSELAGE);

    private BAASBMDropTargetCriticalList noseCrits;
    private BAASBMDropTargetCriticalList leftWingCrits;
    private BAASBMDropTargetCriticalList rightWingCrits;
    private BAASBMDropTargetCriticalList aftCrits;
    private BAASBMDropTargetCriticalList fuselageCrits;

    private final List<BAASBMDropTargetCriticalList> currentCritBlocks;

    private final JLabel noseWeaponSlots = new SmallFontHelpTextLabel();
    private final JLabel leftWeaponSlots = new SmallFontHelpTextLabel();
    private final JLabel rightWeaponSlots = new SmallFontHelpTextLabel();
    private final JLabel aftWeaponSlots = new SmallFontHelpTextLabel();

    private final JButton btnCopyLW = new JButton("Copy from Left Wing");
    private final JButton btnCopyRW = new JButton("Copy from Right Wing");

    private RefreshListener refreshListener;

    public ASCriticalView(EntitySource eSource, RefreshListener refreshListener) {
        super(eSource);
        this.refreshListener = refreshListener;
        constructUI();
        currentCritBlocks = List.of(noseCrits, leftWingCrits, rightWingCrits, aftCrits, fuselageCrits);
        refresh();
    }

    private void constructUI() {
        noseCrits = new BAASBMDropTargetCriticalList(new ArrayList<>(), eSource, refreshListener, this);
        leftWingCrits = new BAASBMDropTargetCriticalList(new ArrayList<>(), eSource, refreshListener, this);
        rightWingCrits = new BAASBMDropTargetCriticalList(new ArrayList<>(), eSource, refreshListener, this);
        aftCrits = new BAASBMDropTargetCriticalList(new ArrayList<>(), eSource, refreshListener, this);
        fuselageCrits = new BAASBMDropTargetCriticalList(new ArrayList<>(), eSource, refreshListener, this);

        btnCopyRW.putClientProperty(FlatClientProperties.STYLE_CLASS, "small");
        btnCopyLW.putClientProperty(FlatClientProperties.STYLE_CLASS, "small");

        Box mainPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box middlePanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();
        Box leftWingPanel = Box.createVerticalBox();
        Box rightWingPanel = Box.createVerticalBox();
        Box nosePanel = Box.createVerticalBox();
        Box aftPanel = Box.createVerticalBox();
        Box fuselagePanel = Box.createVerticalBox();

        noseWeaponSlots.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        leftWeaponSlots.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        rightWeaponSlots.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        aftWeaponSlots.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        nosePanel.setBorder(CritCellUtil.locationBorder("Nose"));
        leftWingPanel.setBorder(CritCellUtil.locationBorder("Left Wing"));
        rightWingPanel.setBorder(CritCellUtil.locationBorder("Right Wing"));
        aftPanel.setBorder(CritCellUtil.locationBorder("Aft"));
        fuselagePanel.setBorder(CritCellUtil.locationBorder("Fuselage"));

        nosePanel.add(noseCrits);
        nosePanel.add(noseWeaponSlots);
        leftWingPanel.add(leftWingCrits);
        leftWingPanel.add(leftWeaponSlots);
        leftWingPanel.add(btnCopyRW);
        rightWingPanel.add(rightWingCrits);
        rightWingPanel.add(rightWeaponSlots);
        rightWingPanel.add(btnCopyLW);
        aftPanel.add(aftCrits);
        aftPanel.add(aftWeaponSlots);
        fuselagePanel.add(fuselageCrits);

        btnCopyLW.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCopyRW.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCopyLW.addActionListener(ev -> copyLocation(Aero.LOC_LEFT_WING, Aero.LOC_RIGHT_WING));
        btnCopyRW.addActionListener(ev -> copyLocation(Aero.LOC_RIGHT_WING, Aero.LOC_LEFT_WING));

        leftPanel.add(leftWingPanel);
        middlePanel.add(nosePanel);
        middlePanel.add(fuselagePanel);
        middlePanel.add(aftPanel);
        rightPanel.add(rightWingPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    public void refresh() {
        Aero aero = getAero();

        for (int location : LOCATION_LIST) {
            Vector<String> critData = new Vector<>(5);

            for (int slot = 0; slot < aero.getNumberOfCriticalSlots(location); slot++) {
                CriticalSlot cs = aero.getCritical(location, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    critData.add(cs.getMount().getName() + ":" + slot);
                }
            }

            if (critData.isEmpty()) {
                // In a completely empty location, display a single empty slot
                critData.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
            }

            BAASBMDropTargetCriticalList critList = critListFor(location);
            critList.setListData(critData);
            critList.setVisibleRowCount(critData.size());
            critList.setName(location + "");

            if (location != Aero.LOC_FUSELAGE) {
                int numWeapons = TestAero.usedWeaponSlots(aero, location);
                int availableSpace = TestAero.availableSpace(getAero())[location];
                String usedCritText = "Weapons: " + numWeapons + " / " + availableSpace;
                weaponSpaceLabelFor(location).setText(usedCritText);
            }
        }
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refreshListener = refresh;
        noseCrits.setRefresh(refresh);
        leftWingCrits.setRefresh(refresh);
        rightWingCrits.setRefresh(refresh);
        aftCrits.setRefresh(refresh);
        fuselageCrits.setRefresh(refresh);
    }

    private BAASBMDropTargetCriticalList critListFor(int location) {
        return switch (location) {
            case Aero.LOC_NOSE -> noseCrits;
            case Aero.LOC_LEFT_WING -> leftWingCrits;
            case Aero.LOC_RIGHT_WING -> rightWingCrits;
            case Aero.LOC_AFT -> aftCrits;
            default -> fuselageCrits;
        };
    }

    private JLabel weaponSpaceLabelFor(int location) {
        return switch (location) {
            case Aero.LOC_NOSE -> noseWeaponSlots;
            case Aero.LOC_LEFT_WING -> leftWeaponSlots;
            case Aero.LOC_RIGHT_WING -> rightWeaponSlots;
            default -> aftWeaponSlots;
        };
    }

    private void copyLocation(int from, int to) {
        try {
            UnitUtil.copyLocationEquipment(getAero(), from, to);
        } catch (LocationFullException ex) {
            PopupMessages.showLocationFullError(null);
        }
        refreshListener.scheduleRefresh();
    }

    @Override
    public void markUnavailableLocations(int location) {
        currentCritBlocks.stream()
              .filter(b -> b.getCritLocation() != location)
              .forEach(b -> b.setDarkened(true));
    }

    @Override
    public void markUnavailableLocations(Mounted<?> equipment) {
        if (equipment != null) {
            currentCritBlocks.stream()
                  .filter(b -> shouldDarkenLocation(b, equipment))
                  .forEach(b -> b.setDarkened(true));
        }
    }

    private boolean shouldDarkenLocation(BAASBMDropTargetCriticalList critList, Mounted<?> equipment) {
        return !UnitUtil.isValidLocation(getAero(), equipment.getType(), critList.getCritLocation())
              || isSpaceRestricted(critList.getCritLocation(), equipment);
    }

    private boolean isSpaceRestricted(int location, Mounted<?> equipment) {
        return location != Aero.LOC_FUSELAGE
              && TestAero.usesWeaponSlot(getAero(), equipment.getType())
              && !TestAero.hasFreeWeaponSlot(getAero(),location);
    }

    @Override
    public void unMarkAllLocations() {
        currentCritBlocks.forEach(b -> b.setDarkened(false));
    }
}
