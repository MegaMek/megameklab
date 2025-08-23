/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import megamek.codeUtilities.MathUtility;
import megamek.common.TechConstants;
import megamek.common.enums.TechRating;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.LargeSupportTank;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.ArmorAllocationView;
import megameklab.ui.generalUnit.MVFArmorView;
import megameklab.ui.generalUnit.PatchworkArmorView;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 *
 */
public class SVArmorTab extends ITab implements ArmorAllocationListener {

    private RefreshListener refresh = null;
    private final MVFArmorView panArmor;
    private final ArmorAllocationView panArmorAllocation;
    private final PatchworkArmorView panPatchwork;
    private final ITechManager techManager;

    public SVArmorTab(EntitySource eSource, ITechManager techManager) {
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
            ArmorType armor = ArmorType.of(at, TechConstants.isClan(armorTechLevel));
            getEntity().setBARRating(armor.getBAR());
            getEntity().setArmorTechRating(panArmor.getTechRating());
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
    public void armorTechRatingChanged(TechRating techRating) {
        getEntity().setArmorTechRating(techRating);
        getEntity().recalculateTechAdvancement();
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
            remainingTonnage = TestEntity.floor(remainingTonnage, Ceil.HALF_TON);
        }

        double maxArmor = MathUtility.clamp(getEntity().getArmorWeight() + remainingTonnage, 0,
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
        getEntity().setArmorTonnage(TestEntity.ceil(tonnage, Ceil.HALF_TON));
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
    public void patchworkChanged(int location, ArmorType armor) {
        UnitUtil.resetArmor(getEntity(), location);

        int crits = armor.getPatchworkSlotsMekSV();
        if (getEntity().getEmptyCriticalSlots(location) < crits) {
            JOptionPane.showMessageDialog(
                  null, armor.getName()
                        + " does not fit in location "
                        + getEntity().getLocationName(location)
                        + ". Resetting to Standard Armor in this location.",
                  "Error",
                  JOptionPane.INFORMATION_MESSAGE);
            getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD, location);
            getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOX_SET);
        } else {
            getEntity().setArmorType(armor.getArmorType(), location);
            getEntity().setArmorTechLevel(armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
            getEntity().setBARRating(armor.getBAR(), location);
            for (; crits > 0; crits--) {
                try {
                    getEntity().addEquipment(Mounted.createMounted(getEntity(), armor), location, false);
                } catch (LocationFullException ignored) {
                }
            }
        }
        getEntity().setArmorTonnage(panArmorAllocation.getTotalArmorWeight(getEntity()));
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
        int pointsToAllocate = TestEntity.getArmorPoints(getEntity());

        for (int location = 0; location < getEntity().locations(); location++) {
            getEntity().initializeArmor(0, location);
        }

        // Discount body, as it's not armored
        int numLocations = getEntity().locations() - 1;
        // Aerospace units also have a wings location for squadrons. It's irrelevant for support
        // vehicles, but it's still there.
        if (getEntity().isAero()) {
            numLocations--;
        }

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
            if ((location == body)
                  || ((getEntity() instanceof VTOL) && (location == VTOL.LOC_ROTOR))
                  || ((getEntity().isAero()) && (location == FixedWingSupport.LOC_WINGS))) {
                continue;
            }
            int armorToAllocate;
            if (location == front) {
                armorToAllocate = (int) (pointsToAllocate * frontPercent);
            } else if (location == rear) {
                armorToAllocate = (int) (pointsToAllocate * rearPercent);
            } else {
                armorToAllocate = (int) (pointsToAllocate * otherPercent);
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
