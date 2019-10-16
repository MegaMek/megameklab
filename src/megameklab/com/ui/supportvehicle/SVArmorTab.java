/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.supportvehicle;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.ArmorAllocationView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.PatchworkArmorView;
import megameklab.com.ui.view.listeners.ArmorAllocationListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

import javax.swing.*;

/**
 *
 */
public class SVArmorTab extends ITab implements ArmorAllocationListener {

    private RefreshListener refresh = null;
    private final MVFArmorView panArmor;
    private final ArmorAllocationView panArmorAllocation;
    private final PatchworkArmorView panPatchwork;
    private final ITechManager techManager;

    SVArmorTab(EntitySource eSource, ITechManager techManager) {
        super(eSource);
        this.techManager = techManager;
        panArmor = new MVFArmorView(techManager, true);
        panPatchwork = new PatchworkArmorView(techManager);
        panArmorAllocation = new ArmorAllocationView(techManager, eSource.getEntity().getEntityType());

        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panArmor);
        panel.add(Box.createVerticalStrut(6));
        panel.add(panPatchwork);
        panel.add(Box.createGlue());
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panArmorAllocation);
        panel.add(Box.createGlue());
        add(panel);

        panArmor.setFromEntity(getEntity());
        panPatchwork.setFromEntity(getEntity());
        panArmorAllocation.setFromEntity(getEntity());

        addAllListeners();
    }

    private void addAllListeners() {
        panArmor.addListener(this);
        panArmorAllocation.addListener(this);
        panPatchwork.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        panArmor.setFromEntity(getEntity());
        if (getEntity().hasPatchworkArmor()) {
            panPatchwork.setFromEntity(getEntity());
            panPatchwork.setVisible(true);
        } else {
            panPatchwork.setVisible(false);
        }
        panArmorAllocation.setFromEntity(getEntity());
    }

    @Override
    public void armorTypeChanged(int at, int armorTechLevel) {
        if (at != EquipmentType.T_ARMOR_PATCHWORK) {
            UnitUtil.removeISorArmorMounts(getEntity(), false);
            getEntity().setArmorTechLevel(armorTechLevel);
            getEntity().setArmorType(at);
            if (at != EquipmentType.T_ARMOR_STANDARD) {
                getEntity().setBARRating(10);
            } else {
                getEntity().setArmorTechRating(panArmor.getTechRating());
                getEntity().setBARRating(panArmor.getBARRating());
            }
            panArmorAllocation.showPatchwork(false);
            panPatchwork.setVisible(false);
        } else {
            panPatchwork.setFromEntity(getEntity());
            panArmorAllocation.showPatchwork(true);
            panPatchwork.setVisible(true);
        }
        panArmor.setFromEntity(getEntity(), true);
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    @Override
    public void armorTechRatingChanged(int techRating) {
        getEntity().setArmorTechRating(techRating);
        panArmor.setFromEntity(getEntity());
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorBARRatingChanged(int bar) {
        getEntity().setBARRating(bar);
        panArmor.setFromEntity(getEntity());
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxTonnage = UnitUtil.getMaximumArmorTonnage(getEntity());
        getEntity().setArmorTonnage(maxTonnage);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getEntity());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getEntity())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getEntity());
        double totalTonnage = getEntity().getWeight();
        double remainingTonnage = totalTonnage - currentTonnage;
        if (getEntity().getWeightClass() != EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            remainingTonnage = TestEntity.floor(remainingTonnage, TestEntity.Ceil.HALFTON);
        }

        double maxArmor = Math.min(getEntity().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getEntity()));
        getEntity().setArmorTonnage(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getEntity());
        panArmor.addListener(this);

        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorTonnageChanged(double tonnage) {
        getEntity().setArmorTonnage(TestEntity.ceil(tonnage, TestEntity.Ceil.HALFTON));
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorFactorChanged(int points) {
        double weight = TestSupportVehicle.armorWeightPerPoint(getEntity()) * points;
        getEntity().setArmorTonnage(weight);
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshSummary();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        getEntity().initializeArmor(front, location);
        if (panArmor.getArmorType() == EquipmentType.T_ARMOR_PATCHWORK) {
            getEntity().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getEntity()));
        }
        panArmor.setFromEntity(getEntity(), true);
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void patchworkChanged(int location, EquipmentType armor) {
        UnitUtil.resetArmor(getEntity(), location);

        //TODO: move this construction data out of the ui
        int crits = 0;
        switch (EquipmentType.getArmorType(armor)) {
            case EquipmentType.T_ARMOR_STEALTH_VEHICLE:
            case EquipmentType.T_ARMOR_LIGHT_FERRO:
            case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            case EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO:
            case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
            case EquipmentType.T_ARMOR_REFLECTIVE:
            case EquipmentType.T_ARMOR_REACTIVE:
                crits = 1;
                break;
            case EquipmentType.T_ARMOR_HEAVY_FERRO:
                crits = 2;
                break;
        }
        if (getEntity().getEmptyCriticals(location) < crits) {
            JOptionPane .showMessageDialog(
                    null, armor.getName()
                            + " does not fit in location "
                            + getEntity().getLocationName(location)
                            + ". Resetting to Standard Armor in this location.",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            getEntity().setArmorType(EquipmentType.getArmorType(armor), location);
            getEntity().setArmorTechLevel(armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
            for (; crits > 0; crits--) {
                try {
                    getEntity().addEquipment( new Mounted(getEntity(), armor), location, false);
                } catch (LocationFullException ex) {
                }
            }
        }
        panArmor.setFromEntity(getEntity(), true);
        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }

    @Override
    public void autoAllocateArmor() {
        // Key location indices that have different values for support tanks and aero
        final int front, rear, body;
        if (getEntity() instanceof Aero) {
            front = FixedWingSupport.LOC_NOSE;
            rear = FixedWingSupport.LOC_AFT;
            body = FixedWingSupport.LOC_BODY;
        } else {
            front = Tank.LOC_FRONT;
            rear = (getEntity() instanceof LargeSupportTank) ? LargeSupportTank.LOC_REAR : Tank.LOC_REAR;
            body = Tank.LOC_BODY;
        }
        int pointsToAllocate = UnitUtil.getArmorPoints(getEntity(), getEntity().getLabArmorTonnage());

        for (int location = 0; location < getEntity().locations(); location++) {
            getEntity().initializeArmor(0, location);
        }

        // Discount body, as it's not armored
        int numLocations = getEntity().locations() - 1;

        // Make sure that the VTOL rotor has the 2 armor it should have
        if (getEntity() instanceof VTOL) {
            getEntity().initializeArmor(Math.min(pointsToAllocate, 2), VTOL.LOC_ROTOR);
            pointsToAllocate -= 2;
            numLocations--;
        }

        // Determine the percentage of total armor each location should get
        double otherPercent = 1.0 / numLocations;
        double remainingPercent = 1.0 - (otherPercent * (numLocations - 2));
        // Front should be slightly more armored and rear slightly less
        double frontPercent = remainingPercent * 0.6;
        double rearPercent = remainingPercent * 0.4;

        // With the percentage of total for each location, assign armor
        int allocatedPoints = 0;
        for (int location = 0; location < getEntity().locations(); location++) {
            if ((location == body) ||
                    ((getEntity() instanceof VTOL) && (location == VTOL.LOC_ROTOR))) {
                continue;
            }
            int armorToAllocate;
            if (location == front) {
                armorToAllocate = (int)(pointsToAllocate * frontPercent);
            } else if (location == rear) {
                armorToAllocate = (int)(pointsToAllocate * rearPercent);
            } else {
                armorToAllocate = (int)(pointsToAllocate * otherPercent);
            }
            getEntity().initializeArmor(armorToAllocate, location);
            allocatedPoints += armorToAllocate;
        }

        // Because of rounding, may have leftover armor: allocate it to front
        int unallocated = pointsToAllocate - allocatedPoints;
        int currentFrontArmor = getEntity().getOArmor(front);
        getEntity().initializeArmor(currentFrontArmor + unallocated, front);


        panArmorAllocation.setFromEntity(getEntity());
        refresh.refreshPreview();
        refresh.refreshSummary();
        refresh.refreshStatus();
    }
}
