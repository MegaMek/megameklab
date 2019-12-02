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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
            // Only take the first word; strip "Support Vehicles"
            sb.append(tank.getWeightClassName().replaceAll(" .*", " "));
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

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(MOVEMENT_TYPE, tank.getMovementModeAsString());
        setTextField(ENGINE_TYPE, tank.getEngine().getEngineName()
                .replaceAll("\\d+|\\[.*]", "").trim());
        if (tank.getOriginalJumpMP() > 0) {
            setTextField(MP_JUMP, tank.getOriginalJumpMP());
        } else {
            hideElement(MP_JUMP, true);
            hideElement(LBL_JUMP, true);
        }
    }

    @Override
    protected String formatFeatures() {
        StringJoiner sj = new StringJoiner(", ");
        for (Mounted m : tank.getMisc()) {
            if (m.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION)) {
                sj.add(m.getType().getShortName());
            }
        }
        Map<String, Double> transport = new HashMap<>();
        for (Transporter t : tank.getTransports()) {
            if (t instanceof TroopSpace) {
                transport.merge("Infantry Bay", t.getUnused(), Double::sum);
            } else if (t instanceof Bay) {
                transport.merge(((Bay) t).getType(), ((Bay) t).getCapacity(), Double::sum);
            }
        }
        for (Map.Entry<String, Double> e : transport.entrySet()) {
            sj.add(e.getKey() + " (" + DecimalFormat.getInstance().format(e.getValue())
                    + ((e.getValue() == 1)? " ton)" : " tons)"));
        }
        return sj.toString();
    }
}
