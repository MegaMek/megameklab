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
import megameklab.com.util.ImageHelper;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Configures record sheet for ground combat and support vehicles. When two units are printed
 * on a single page, this is responsible for one half of the page. Vehicles which are printed
 * two per page should not use this class directly, but instead use {@link PrintCompositeTankSheet}
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
    public Entity getEntity() {
        return tank;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        String subtype;
        switch (tank.getMovementMode()) {
            case VTOL:
                subtype = "vtol";
                break;
            case NAVAL:
            case HYDROFOIL:
                subtype = "naval";
                break;
            case SUBMARINE:
                subtype = "submarine";
                break;
            case WIGE:
                if (!tank.isSuperHeavy()) {
                    subtype = "wige";
                    break;
                }
                //fall through
            default:
                subtype = "vehicle";
                break;
        }
        String turret;
        if (tank.hasNoTurret()) {
            turret = "noturret";
        } else if (tank.hasNoDualTurret()) {
            turret = "turret";
        } else {
            turret = "dualturret";
        }
        // Superheavy VTOLs don't have extra armor sections and use the same template as standard weight
        String weight = (tank.isSuperHeavy() && !(tank instanceof VTOL)) ? "superheavy" : "standard";
        return String.format("%s_%s_%s.svg", subtype, turret, weight);
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
            setTextField(MP_JUMP, formatJump());
        } else {
            hideElement(MP_JUMP, true);
            hideElement(LBL_JUMP, true);
        }
        if (tank.getMovementMode().equals(EntityMovementMode.TRACKED)) {
            hideElement(TRACKS, false);
        } else if (tank.getMovementMode().equals(EntityMovementMode.WHEELED)) {
            hideElement(WHEELS, false);
        }
    }

    @Override
    protected String formatRun() {
        if (tank.hasWorkingMisc(MiscType.F_MASC)) {
            return formatMovement(tank.getWalkMP() * 1.5, tank.getWalkMP() * 2);
        } else {
            return formatMovement(tank.getWalkMP() * 1.5);
        }
    }

    @Override
    public String formatFeatures() {
        StringJoiner sj = new StringJoiner(", ");
        List<String> chassisMods = tank.getMisc().stream().filter(m -> m.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION))
                .map(m -> m.getType().getShortName())
                .collect(Collectors.toList());
        if (!chassisMods.isEmpty()) {
            sj.add(String.join(", ", chassisMods)
                    + (chassisMods.size() == 1 ? " Chassis Mod" : " Chassis Mods"));
        }
        if (tank.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            sj.add("Advanced Fire Control");
        } else if (tank.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
            sj.add("Basic Fire Control");
        }
        Map<String, Double> transport = new HashMap<>();
        Map<String, Integer> seating = new HashMap<>();
        for (Transporter t : tank.getTransports()) {
            if (t instanceof TroopSpace) {
                transport.merge("Infantry Bay", t.getUnused(), Double::sum);
            } else if (t instanceof StandardSeatCargoBay) {
                seating.merge(((Bay) t).getType(), (int) ((Bay) t).getCapacity(), Integer::sum);
            } else if (t instanceof Bay) {
                transport.merge(((Bay) t).getType(), ((Bay) t).getCapacity(), Double::sum);
            }
        }
        for (Map.Entry<String, Integer> e : seating.entrySet()) {
            sj.add(e.getValue() + " " + ((e.getValue() == 1) ?
                    e.getKey().replace("Seats", "Seat") : e.getKey()));
        }
        for (Map.Entry<String, Double> e : transport.entrySet()) {
            sj.add(e.getKey() + " (" + formatWeight(e.getValue()) + ")");
        }
        return sj.toString();
    }

    @Override
    protected void drawFluffImage() {
        File f;
        if (tank.getMovementMode().equals(EntityMovementMode.NAVAL)
                || tank.getMovementMode().equals(EntityMovementMode.HYDROFOIL)
                || tank.getMovementMode().equals(EntityMovementMode.SUBMARINE)) {
            f = ImageHelper.getFluffFile(tank, ImageHelper.imageNaval);
        } else if (tank instanceof LargeSupportTank) {
            f = ImageHelper.getFluffFile(tank, ImageHelper.imageLargeSupportVehicle);
        } else {
            f = ImageHelper.getFluffFile(tank, ImageHelper.imageVehicle);
        }
        if (null != f) {
            Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
            if (rect instanceof SVGRectElement) {
                embedImage(f, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
            }
            hideElement(getSVGDocument().getElementById(NOTES));
        }
    }
}
