/*
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.mek;

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.listeners.MekBuildListener;
import megameklab.ui.util.IView;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static megamek.common.EquipmentTypeLookup.LAM_FUEL_TANK;

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

    public void setFromEntity(Mech mek) {
        fuelTonsSpnModel.setValue(fuelTanks(mek));
        updateFuelPointsLabel();
    }

    /**
     * Returns the current number of LAM Fuel Tanks on the Mek, including unallocated.
     * Always returns 0 for non-LAM.
     *
     * @param mek The Mek unit
     * @return The Mek's current LAM Fuel Tank count
     */
    private int fuelTanks(Mech mek) {
        return (mek instanceof LandAirMech) ? mek.countWorkingMisc(LAM_FUEL_TANK, -1) : 0;
    }

    private void updateFuelPointsLabel() {
        totalFuelPointsLabel.setText(resourceMap.getString("BMLAMFuelView.totalFuelLabel.text")
                + (80 * (1 + fuelTanks(getMech()))));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == fuelTonsSpn) {
            int currentTanks = fuelTanks(getMech());
            int newTanks = (int) fuelTonsSpn.getValue();
            if (newTanks > currentTanks) {
                addTanks(newTanks - currentTanks);
            } else {
                deleteTanks(currentTanks - newTanks);
            }
            updateFuelPointsLabel();
            listeners.forEach(l -> l.engineChanged(getMech().getEngine()));
        }
    }

    private void deleteTanks(int number) {
        // Remove unallocated fuel tanks first
        List<Mounted> fuelTanks = getMech().getMisc().stream()
                .filter(mounted -> mounted.getType().equals(FUEL_TANK))
                .filter(mounted -> mounted.getLocation() == Entity.LOC_NONE)
                .collect(Collectors.toList());
        for (Mounted fuelTank : fuelTanks) {
            if (number > 0) {
                UnitUtil.removeMounted(getMech(), fuelTank);
                number--;
            } else {
                return;
            }
        }
        // Must remove more, so take allocated fuel tanks
        fuelTanks = getMech().getMisc().stream()
                .filter(mounted -> mounted.getType().equals(FUEL_TANK))
                .collect(Collectors.toList());
        for (Mounted fuelTank : fuelTanks) {
            if (number > 0) {
                UnitUtil.removeMounted(getMech(), fuelTank);
                number--;
            } else {
                return;
            }
        }
    }

    private void addTanks(int number) {
        for (int i = 0; i < number; i++) {
            try {
                Mounted<?> mount = Mounted.createMounted(getMech(), FUEL_TANK);
                getMech().addEquipment(mount, Entity.LOC_NONE, false);
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
