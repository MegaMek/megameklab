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
package megameklab.ui.supportVehicle;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;

import java.util.List;

/**
 * Shows current weight and space of support vehicle components.
 */
public class SVSummaryView extends IView {

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem fuelSummary = new FuelSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();
    private final SummaryItem controlsSummary = new ControlsSummaryItem();
    private final SummaryItem armorSummary = new ArmorSummaryItem();
    private final SummaryItem turretSummary = new TurretSummaryItem();
    private final SummaryItem amplifierSummary = new PowerAmplifierSummaryItem();
    private final SummaryItem equipmentSummary = new EquipmentSummaryItem();
    //bays

    private final List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary,
            fuelSummary, heatsinkSummary, controlsSummary, armorSummary, turretSummary,
            amplifierSummary, equipmentSummary);

    private final SummaryView summaryView = new SummaryView(summaryItemList);

    public SVSummaryView(EntitySource eSource) {
        super(eSource);
        add(summaryView);
    }

    public void refresh() {
        summaryView.refresh(getEntity());
    }



//
//    public void refresh() {
//        TestSupportVehicle testSV = new TestSupportVehicle(eSource.getEntity(),
//                entityVerifier.tankOption, null);
//        if (eSource.getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
//            lblWeightUnits.setText("Kg");
//        } else {
//            lblWeightUnits.setText("Tons");
//        }
//
//        txtStructTon.setText(formatWeight(testSV.getWeightStructure()));
//        txtEngineTon.setText(formatWeight(testSV.getWeightEngine()));
//        txtFuelTon.setText(formatWeight(testSV.getFuelTonnage()));
//        txtControlsTon.setText(formatWeight(testSV.getWeightControls()));
//        txtHeatTon.setText(formatWeight(testSV.getWeightHeatSinks()));
//        txtArmorTon.setText(formatWeight(testSV.getWeightArmor()));
//        txtTurretTon.setText(formatWeight(testSV.getTankWeightTurret()
//            + testSV.getTankWeightDualTurret()));
//        txtPowerAmpTon.setText(formatWeight(testSV.getWeightPowerAmp()));
//        txtWeaponTon.setText(formatWeight(testSV.getWeightWeapon()));
//        txtAmmoTon.setText(formatWeight(testSV.getWeightAmmo()));
//        txtEquipTon.setText(formatWeight(testSV.getWeightMiscEquip()));
//        txtBaysTon.setText(formatWeight(testSV.getWeightCarryingSpace()));
//
//        txtControlsCrit.setText(Integer.toString(testSV.getCrewSlots()));
//        txtArmorCrit.setText(Integer.toString(testSV.getArmorSlots()));
//        txtWeaponCrit.setText(Integer.toString(testSV.getWeaponSlots()));
//        txtAmmoCrit.setText(Integer.toString(testSV.getAmmoSlots()));
//        txtEquipCrit.setText(Integer.toString(testSV.getMiscEquipSlots()));
//        txtBaysCrit.setText(Integer.toString(testSV.getTransportSlots()));
//    }
}
