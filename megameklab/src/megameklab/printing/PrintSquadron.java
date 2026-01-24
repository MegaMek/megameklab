/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.printing;

import megamek.common.units.Entity;
import megamek.common.units.FighterSquadron;

public class PrintSquadron extends PrintEntity {
    private final FighterSquadron squadron;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintSquadron(FighterSquadron squadron, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.squadron = squadron;
    }

    @Override
    public Entity getEntity() {
        return squadron;
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "squadron.svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        return "Fighter Squadron Record Sheet";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(TEXT_SI, squadron.getOSI());
        setTextField(TOTAL_FUEL, squadron.getFuel());
    }
}
