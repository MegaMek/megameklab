/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.printing;

import megamek.common.BattleArmor;
import megamek.common.Entity;

/**
 * Lays out a record sheet block for a single BattleArmor unit
 */
public class PrintBattleArmor extends PrintEntity {

    private final BattleArmor battleArmor;
    private final int squadIndex;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param battleArmor  The battlearmor to print
     * @param squadIndex   The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     * @param options      Overrides the global options for which elements are printed
     */
    public PrintBattleArmor(BattleArmor battleArmor, int squadIndex, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.battleArmor = battleArmor;
        this.squadIndex = squadIndex;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param battleArmor  The BattleArmor to print
     * @param squadIndex   The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     */
    public PrintBattleArmor(BattleArmor battleArmor, int squadIndex, int startPage) {
        this(battleArmor, startPage, squadIndex, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "battle_armor_squad.svg";
    }


    @Override
    protected Entity getEntity() {
        return battleArmor;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return false;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();

        setTextField(SQUAD, "BATTLE ARMOR: " + squadName() + " " + (squadIndex + 1));
    }

    private String squadName() {
        switch (battleArmor.getTroopers()) {
            case 6:
                return "LEVEL I";
            case 5:
                return battleArmor.isClan() ? "POINT" : "SQUAD";
            case 3:
                return "TREY";
            default:
            case 4:
                return "SQUAD";
        }
    }
}
