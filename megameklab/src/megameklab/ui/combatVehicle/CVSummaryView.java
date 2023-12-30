/*
 * MegaMekLab - Copyright (C) 2008
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
package megameklab.ui.combatVehicle;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;

import java.util.List;

public class CVSummaryView extends IView {

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem propulsionSummary = new PropulsionSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();
    private final SummaryItem controlsSummary = new ControlsSummaryItem();
    private final SummaryItem armorSummary = new ArmorSummaryItem();
    private final SummaryItem jumpSummary = new JumpSummaryItem();
    private final SummaryItem turretSummary = new TurretSummaryItem();
    private final SummaryItem rearTurretSummary = new RearTurretSummaryItem();
    private final SummaryItem sponsonSummary = new SponsonTurretSummaryItem();
    private final SummaryItem amplifierSummary = new PowerAmplifierSummaryItem();
    private final SummaryItem equipmentSummary = new EquipmentSummaryItem();

    private final List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary,
            propulsionSummary, heatsinkSummary, controlsSummary, armorSummary, jumpSummary, turretSummary,
            rearTurretSummary, sponsonSummary, amplifierSummary, equipmentSummary);

    private final SummaryView summaryView = new SummaryView(summaryItemList, false);

    public CVSummaryView(EntitySource eSource) {
        super(eSource);
        add(summaryView);
    }

    public void refresh() {
        summaryView.refresh(getEntity());
    }
}