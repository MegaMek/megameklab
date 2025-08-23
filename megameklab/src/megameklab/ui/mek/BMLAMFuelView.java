/*
 * Copyright (C) 2022-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.mek;

import static megamek.common.equipment.EquipmentTypeLookup.LAM_FUEL_TANK;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.units.Entity;
import megamek.common.equipment.EquipmentType;
import megamek.common.units.LandAirMek;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Mek;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.listeners.MekBuildListener;
import megameklab.ui.util.IView;
import megameklab.util.UnitUtil;

/**
 * This view block allows entering additional fuel tanks for LandAirMeks
 *
 * @author Simon (Juliez)
 */
public class BMLAMFuelView extends IView implements ChangeListener {

    private static final EquipmentType FUEL_TANK = MiscType.get(LAM_FUEL_TANK);

    private final List<MekBuildListener> listeners = new CopyOnWriteArrayList<>();

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
    private final SpinnerNumberModel fuelTonsSpnModel = new SpinnerNumberModel(0, 0, 84, 1);
    private final JSpinner fuelTonsSpn = new JSpinner(fuelTonsSpnModel);
    private final JLabel totalFuelPointsLabel = new JLabel();

    public BMLAMFuelView(EntitySource entitySource) {
        super(entitySource);
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        fuelTonsSpn.setToolTipText(resourceMap.getString("BMLAMFuelView.lblFuelPoints.tooltip"));
        fuelTonsSpn.addChangeListener(this);

        Box fuelRangePanel = Box.createHorizontalBox();
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.add(new JLabel(resourceMap.getString("BMLAMFuelView.spnFuel.text")));
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.add(fuelTonsSpn);
        fuelRangePanel.add(new JLabel(" tons"));
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        JPanel totalPointsPanel = new JPanel();
        totalPointsPanel.add(totalFuelPointsLabel);

        add(fuelRangePanel);
        add(totalPointsPanel);
        add(Box.createVerticalStrut(5));
    }

    public void setFromEntity(Mek mek) {
        fuelTonsSpnModel.setValue(fuelTanks(mek));
        updateFuelPointsLabel();
    }

    /**
     * Returns the current number of LAM Fuel Tanks on the Mek, including unallocated. Always returns 0 for non-LAM.
     *
     * @param mek The Mek unit
     *
     * @return The Mek's current LAM Fuel Tank count
     */
    private int fuelTanks(Mek mek) {
        return (mek instanceof LandAirMek) ? mek.countWorkingMisc(LAM_FUEL_TANK, -1) : 0;
    }

    private void updateFuelPointsLabel() {
        totalFuelPointsLabel.setText(resourceMap.getString("BMLAMFuelView.totalFuelLabel.text")
              + (80 * (1 + fuelTanks(getMek()))));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == fuelTonsSpn) {
            int currentTanks = fuelTanks(getMek());
            int newTanks = (int) fuelTonsSpn.getValue();
            if (newTanks > currentTanks) {
                addTanks(newTanks - currentTanks);
            } else {
                deleteTanks(currentTanks - newTanks);
            }
            updateFuelPointsLabel();
            listeners.forEach(l -> l.engineChanged(getMek().getEngine()));
        }
    }

    private void deleteTanks(int number) {
        // Remove unallocated fuel tanks first
        List<Mounted<?>> fuelTanks = getMek().getMisc().stream()
              .filter(mounted -> mounted.getType().equals(FUEL_TANK))
              .filter(mounted -> mounted.getLocation() == Entity.LOC_NONE)
              .collect(Collectors.toList());
        for (Mounted<?> fuelTank : fuelTanks) {
            if (number > 0) {
                UnitUtil.removeMounted(getMek(), fuelTank);
                number--;
            } else {
                return;
            }
        }
        // Must remove more, so take allocated fuel tanks
        fuelTanks = getMek().getMisc().stream()
              .filter(mounted -> mounted.getType().equals(FUEL_TANK))
              .collect(Collectors.toList());
        for (Mounted<?> fuelTank : fuelTanks) {
            if (number > 0) {
                UnitUtil.removeMounted(getMek(), fuelTank);
                number--;
            } else {
                return;
            }
        }
    }

    private void addTanks(int number) {
        for (int i = 0; i < number; i++) {
            try {
                Mounted<?> mount = Mounted.createMounted(getMek(), FUEL_TANK);
                getMek().addEquipment(mount, Entity.LOC_NONE, false);
            } catch (LocationFullException ignored) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    public void addListener(MekBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(MekBuildListener l) {
        listeners.remove(l);
    }
}
