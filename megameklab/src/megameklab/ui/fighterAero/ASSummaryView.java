/*
 * Copyright (c) 2008, 2023 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;

import java.util.List;

/**
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author Simon (Juliez)
 */
public class ASSummaryView extends IView {

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem fuelSummary = new FuelSummaryItem();
    private final SummaryItem controlSummary = new ControlsSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();
    private final SummaryItem armorSummary = new ArmorSummaryItem();
    private final SummaryItem equipmentSummary = new EquipmentSummaryItem();
    private final SummaryItem otherSummary = new OtherSummaryItem();

    private final java.util.List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary,
            fuelSummary, controlSummary, heatsinkSummary, armorSummary, equipmentSummary,
            otherSummary);

    private final SummaryView summaryView = new SummaryView(summaryItemList, false);

    public ASSummaryView(EntitySource eSource) {
        super(eSource);
        add(summaryView);
    }

    public void refresh() {
        summaryView.refresh(getEntity());
    }
}