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
package megameklab.ui.mek;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;

import java.util.List;

public class BMSummaryView extends IView {

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem gyroSummary = new GyroSummaryItem();
    private final SummaryItem cockpitSummary = new CockpitSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();
    private final SummaryItem armorSummary = new ArmorSummaryItem();
    private final SummaryItem jumpSummary = new JumpSummaryItem();
    private final SummaryItem equipmentSummary = new EquipmentSummaryItem();
    private final SummaryItem myomerSummary = new MyomerEnhancementSummaryItem();
    private final SummaryItem otherSummary = new OtherSummaryItem();

    private final List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary, gyroSummary,
            cockpitSummary, heatsinkSummary, armorSummary, jumpSummary, myomerSummary, equipmentSummary,
            otherSummary);

    private final SummaryView summaryView = new SummaryView(summaryItemList);

    public BMSummaryView(EntitySource eSource) {
        super(eSource);
        add(summaryView);
    }

    public void refresh() {
        summaryView.refresh(getEntity());
    }
}