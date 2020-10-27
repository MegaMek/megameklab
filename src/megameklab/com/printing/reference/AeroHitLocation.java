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
package megameklab.com.printing.reference;

import megamek.common.Jumpship;
import megamek.common.SmallCraft;
import megameklab.com.printing.PrintAero;

/**
 *
 */
public class AeroHitLocation extends ReferenceTable {
    private final static double[] capitalOffsets = {0.1, 0.3, 0.55, 0.8};
    private final static double[] standardOffsets = {0.1, 0.3, 0.5, 0.7, 0.9};

    public AeroHitLocation(PrintAero sheet) {
        super(sheet, (sheet.getEntity() instanceof Jumpship) ? capitalOffsets : standardOffsets);
        if (sheet.getEntity() instanceof Jumpship) {
            setHeaders(bundle.getString("dieRoll2d6"), bundle.getString("nose"),
                    bundle.getString("side"), bundle.getString("aft"));
            addAdvancedAeroRows();
        } else {
            setHeaders(bundle.getString("dieRoll2d6"), bundle.getString("nose"),
                    bundle.getString("side"), bundle.getString("aft"),
                    bundle.getString("aboveBelow"));
            if (sheet.getEntity() instanceof SmallCraft) {
                addSCDSRows();
            } else {
                addFighterRows();
            }
        }
    }

    private void addSCDSRows() {
        addRow("2", bundle.getString("nose") + "/" + bundle.getString("crew"),
                bundle.getString("aft") + "/" + bundle.getString("lifeSupport"),
                bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("nose") + "/" + bundle.getString("weapon"));
        addRow("3", bundle.getString("nose") + "/" + bundle.getString("avionics"),
                bundle.getString("aft") + "/" + bundle.getString("control"),
                bundle.getString("nose") + "/" + bundle.getString("fcs"),
                bundle.getString("nose") + "/" + bundle.getString("fcs"));
        addRow("4", bundle.getString("rside") + "/" + bundle.getString("weapon"),
                bundle.getString("rside") + "/" + bundle.getString("weapon"),
                bundle.getString("nose") + "/" + bundle.getString("sensors"),
                bundle.getString("nose") + "/" + bundle.getString("sensors"));
        addRow("5", bundle.getString("rside") + "/" + bundle.getString("thruster"),
                bundle.getString("rside") + "/" + bundle.getString("door"),
                bundle.getString("side") + "/" + bundle.getString("thruster"),
                bundle.getString("side") + "/" + bundle.getString("thruster"));
        addRow("6", bundle.getString("nose") + "/" + bundle.getString("fcs"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("side") + "/" + bundle.getString("cargo"),
                bundle.getString("side") + "/" + bundle.getString("cargo"));
        addRow("7", bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("side") + "/" + bundle.getString("weapon"),
                bundle.getString("side") + "/" + bundle.getString("weapon"));
        addRow("8", bundle.getString("nose") + "/" + bundle.getString("control"),
                bundle.getString("aft") + "/" + bundle.getString("collar"),
                bundle.getString("side") + "/" + bundle.getString("door"),
                bundle.getString("side") + "/" + bundle.getString("door"));
        addRow("9", bundle.getString("lside") + "/" + bundle.getString("thruster"),
                bundle.getString("lside") + "/" + bundle.getString("door"),
                bundle.getString("side") + "/" + bundle.getString("thruster"),
                bundle.getString("side") + "/" + bundle.getString("thruster"));
        addRow("10", bundle.getString("lside") + "/" + bundle.getString("weapon"),
                bundle.getString("lside") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("avionics"),
                bundle.getString("aft") + "/" + bundle.getString("avionics"));
        addRow("11", bundle.getString("nose") + "/" + bundle.getString("sensors"),
                bundle.getString("aft") + "/" + bundle.getString("gear"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("aft") + "/" + bundle.getString("engine"));
        addRow("12", bundle.getString("nose") + "/" + bundle.getString("kfBoom"),
                bundle.getString("aft") + "/" + bundle.getString("fuel"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"));
    }

    private void addAdvancedAeroRows() {
        addRow("2", bundle.getString("nose") + "/" + bundle.getString("lifeSupport"),
                bundle.getString("aft") + "/" + bundle.getString("fuel"),
                bundle.getString("nose") + "/" + bundle.getString("avionics"));
        addRow("3", bundle.getString("nose") + "/" + bundle.getString("control"),
                bundle.getString("aft") + "/" + bundle.getString("avionics"),
                bundle.getString("frontSide") + "/" + bundle.getString("sensors"));
        addRow("4", bundle.getString("foreRight") + "/" + bundle.getString("weapon"),
                bundle.getString("aftRight") + "/" + bundle.getString("weapon"),
                bundle.getString("frontSide") + "/" + bundle.getString("frontSideWeapon"));
        addRow("5", bundle.getString("foreRight") + "/" + bundle.getString("thruster"),
                bundle.getString("aftRight") + "/" + bundle.getString("thruster"),
                bundle.getString("frontSide") + "/" + bundle.getString("dockingCollar"));
        addRow("6", bundle.getString("nose") + "/" + bundle.getString("cic"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("frontSide") + "/" + bundle.getString("kfDrive"));
        addRow("7", bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("aftSide") + "/" + bundle.getString("broadsideWeapon"));
        addRow("8", bundle.getString("nose") + "/" + bundle.getString("sensors"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("aftSide") + "/" + bundle.getString("gravDeck"));
        addRow("9", bundle.getString("foreLeft") + "/" + bundle.getString("thruster"),
                bundle.getString("aftLeft") + "/" + bundle.getString("thruster"),
                bundle.getString("aftSide") + "/" + bundle.getString("door"));
        addRow("10", bundle.getString("foreLeft") + "/" + bundle.getString("weapon"),
                bundle.getString("aftLeft") + "/" + bundle.getString("weapon"),
                bundle.getString("aftSide") + "/" + bundle.getString("aftSideWeapon"));
        addRow("11", bundle.getString("nose") + "/" + bundle.getString("crew"),
                bundle.getString("aft") + "/" + bundle.getString("control"),
                bundle.getString("aft") + "/" + bundle.getString("cargo"));
        addRow("12", bundle.getString("nose") + "/" + bundle.getString("kfDrive"),
                bundle.getString("aft") + "/" + bundle.getString("kfDrive"),
                bundle.getString("aft") + "/" + bundle.getString("engine"));
    }

    private void addFighterRows() {
        addRow("2", bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("nose") + "/" + bundle.getString("weapon"));
        addRow("3", bundle.getString("nose") + "/" + bundle.getString("sensors"),
                bundle.getString("aft") + "/" + bundle.getString("heatSink"),
                bundle.getString("wing") + "/" + bundle.getString("gear"),
                bundle.getString("wing") + "/" + bundle.getString("gear"));
        addRow("4", bundle.getString("rwing") + "/" + bundle.getString("heatSink"),
                bundle.getString("rwing") + "/" + bundle.getString("fuel"),
                bundle.getString("nose") + "/" + bundle.getString("sensors"),
                bundle.getString("nose") + "/" + bundle.getString("sensors"));
        addRow("5", bundle.getString("rwing") + "/" + bundle.getString("weapon"),
                bundle.getString("rwing") + "/" + bundle.getString("weapon"),
                bundle.getString("nose") + "/" + bundle.getString("crew"),
                bundle.getString("nose") + "/" + bundle.getString("crew"));
        addRow("6", bundle.getString("nose") + "/" + bundle.getString("avionics"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("wing") + "/" + bundle.getString("weapon"),
                bundle.getString("wing") + "/" + bundle.getString("weapon"));
        addRow("7", bundle.getString("nose") + "/" + bundle.getString("control"),
                bundle.getString("aft") + "/" + bundle.getString("control"),
                bundle.getString("wing") + "/" + bundle.getString("avionics"),
                bundle.getString("nose") + "/" + bundle.getString("avionics"));
        addRow("8", bundle.getString("nose") + "/" + bundle.getString("fcs"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("wing") + "/" + bundle.getString("bomb"),
                bundle.getString("wing") + "/" + bundle.getString("weapon"));
        addRow("9", bundle.getString("lwing") + "/" + bundle.getString("weapon"),
                bundle.getString("lwing") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("control"),
                bundle.getString("aft") + "/" + bundle.getString("control"));
        addRow("10", bundle.getString("lwing") + "/" + bundle.getString("heatSink"),
                bundle.getString("lwing") + "/" + bundle.getString("fuel"),
                bundle.getString("aft") + "/" + bundle.getString("engine"),
                bundle.getString("aft") + "/" + bundle.getString("engine"));
        addRow("11", bundle.getString("nose") + "/" + bundle.getString("gear"),
                bundle.getString("aft") + "/" + bundle.getString("heatSink"),
                bundle.getString("wing") + "/" + bundle.getString("gear"),
                bundle.getString("wing") + "/" + bundle.getString("gear"));
        addRow("12", bundle.getString("nose") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"),
                bundle.getString("aft") + "/" + bundle.getString("weapon"));
    }


}
