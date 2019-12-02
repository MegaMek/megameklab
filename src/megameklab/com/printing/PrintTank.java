/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMek Team
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

import megamek.common.*;

/**
 * Configures record sheet for ground combat and support vehicles.
 */
public class PrintTank extends PrintEntity {

    /**
     * The vehicle currently being printed
     */
    private final Tank tank;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param tank The tank to print
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed
     */
    public PrintTank(Tank tank, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.tank = tank;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param tank The tank to print
     * @param startPage The print job page number for this sheet
     */
    public PrintTank(Tank tank, int startPage) {
        this(tank, startPage, new RecordSheetOptions());
    }

    @Override
    protected Entity getEntity() {
        return tank;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        if (tank.isSuperHeavy()) {
            return loc == SuperHeavyTank.LOC_FRONT
                    || loc == SuperHeavyTank.LOC_BODY
                    || loc == SuperHeavyTank.LOC_REAR;
        }
        return loc == Tank.LOC_FRONT
                || loc == Tank.LOC_BODY
                || loc == Tank.LOC_REAR;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        final String size = tank.isSuperHeavy() ? "superheavy" : "standard";
        if (tank.hasNoTurret()) {
            return "vehicle_noturret_" + size + ".svg";
        } else if (tank.hasNoDualTurret()) {
            return "vehicle_turret_" + size + ".svg";
        }
        return "vehicle_dualturret_" + size + ".svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        StringBuilder sb = new StringBuilder();
        if (tank.isSupportVehicle()) {
            sb.append(EntityWeightClass.getWeightClass(tank.getWeight(), tank))
                    .append(" ");
        } else if (tank.isSuperHeavy()) {
            sb.append("SuperHeavy ");
        }
        switch (tank.getMovementMode()) {
            case NAVAL:
            case HYDROFOIL:
            case SUBMARINE:
                sb.append("Naval ");
                break;
            case VTOL:
                // do nothing
                break;
            default:
                sb.append(tank.getMovementModeAsString()).append(" ");
                break;
        }
        if (tank.isSupportVehicle()) {
            sb.append("Support ");
        }
        if (tank instanceof VTOL) {
            sb.append("VTOL");
        } else {
            sb.append("Vehicle");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }
}
