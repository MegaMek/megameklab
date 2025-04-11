/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.printing;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.InfantryCompartment;
import megamek.common.MiscType;
import megamek.common.SupportTank;
import megamek.common.Tank;
import megamek.common.Transporter;
import megamek.common.VTOL;
import megamek.common.bays.Bay;
import megamek.common.bays.StandardSeatCargoBay;
import megameklab.printing.reference.ClusterHitsTable;
import megameklab.printing.reference.GroundMovementRecord;
import megameklab.printing.reference.GroundToHitMods;
import megameklab.printing.reference.MovementCost;
import megameklab.printing.reference.ReferenceTable;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Configures record sheet for ground combat and support vehicles. When two units are printed on a single page, this is
 * responsible for one half of the page. Vehicles which are printed two per page should not use this class directly, but
 * instead use {@link PrintCompositeTankSheet}
 */
public class PrintTank extends PrintEntity {

    /**
     * The vehicle currently being printed
     */
    private final Tank tank;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param tank      The tank to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintTank(Tank tank, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.tank = tank;
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
                // fall through
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
        // Super heavy VTOLs don't have extra armor sections and use the same template as standard weight
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
        setTextField(ENGINE_TYPE, tank.getEngine().getShortEngineName().replaceAll("\\[.*]", "").trim());
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
    protected boolean supportsAlternateArmorGrouping() {
        if (tank.isSuperHeavy() && !(tank instanceof VTOL)) {
            return false;
        }
        return !tank.isNaval() || tank.hasNoDualTurret();
    }

    @Override
    public String formatFeatures() {
        StringJoiner sj = new StringJoiner(", ");
        List<String> chassisMods = tank.getMisc()
                                         .stream()
                                         .filter(m -> m.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION))
                                         .map(m -> m.getType().getShortName())
                                         .collect(Collectors.toList());
        if (!chassisMods.isEmpty()) {
            sj.add(String.join(", ", chassisMods) + (chassisMods.size() == 1 ? " Chassis Mod" : " Chassis Mods"));
        }
        if (tank.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            sj.add("Advanced Fire Control");
        } else if (tank.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
            sj.add("Basic Fire Control");
        }
        Map<String, Double> transport = new HashMap<>();
        Map<String, Integer> seating = new HashMap<>();
        for (Transporter t : tank.getTransports()) {
            if (t instanceof InfantryCompartment) {
                transport.merge("Infantry Compartment", t.getUnused(), Double::sum);
            } else if (t instanceof StandardSeatCargoBay) {
                seating.merge(t.getType(), (int) ((Bay) t).getCapacity(), Integer::sum);
                // SVs have separate Bay handling similar to Small Craft, with doors. CVs just have bulk cargo space.
            } else if (t instanceof Bay && !(tank instanceof SupportTank)) {
                transport.merge(t.getType(), ((Bay) t).getCapacity(), Double::sum);
            }
        }
        for (Map.Entry<String, Integer> e : seating.entrySet()) {
            sj.add(e.getValue() + " " + ((e.getValue() == 1) ? e.getKey().replace("Seats", "Seat") : e.getKey()));
        }
        for (Map.Entry<String, Double> e : transport.entrySet()) {
            sj.add(e.getKey() + " (" + formatWeight(e.getValue()) + ")");
        }
        return sj.toString();
    }

    @Override
    protected void drawFluffImage() {
        Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
        if (rect instanceof SVGRectElement) {
            if (!options.showCondensedReferenceCharts()) {
                Image fluffImage = getFluffImage();
                if (null != fluffImage) {
                    embedImage(fluffImage, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
                    hideElement(getSVGDocument().getElementById(NOTES));
                }
            } else {
                var table = new ClusterHitsTable(this, true);
                if (!table.required()) {
                    return;
                }
                Rectangle2D bindingBox = getRectBBox((SVGRectElement) rect);
                placeReferenceCharts(List.of(table),
                      rect.getParentNode(),
                      bindingBox.getX() - 3.0,
                      bindingBox.getY() - 6.0,
                      bindingBox.getWidth() + 6.0,
                      bindingBox.getHeight() + 12.0);
                hideElement(getSVGDocument().getElementById(NOTES));
            }
        }
    }

    @Override
    protected boolean includeReferenceCharts() {
        return options.showReferenceCharts();
    }

    @Override
    protected List<ReferenceTable> getRightSideReferenceTables() {
        List<ReferenceTable> list = new ArrayList<>();
        list.add(new GroundToHitMods(this));
        list.add(new MovementCost(this));
        ClusterHitsTable table = new ClusterHitsTable(this, false);
        if (table.required()) {
            list.add(table);
        }
        return list;
    }

    @Override
    protected void addReferenceCharts(PageFormat pageFormat) {
        super.addReferenceCharts(pageFormat);
        GroundMovementRecord table = new GroundMovementRecord(this, false, true);
        getSVGDocument().getDocumentElement()
              .appendChild(table.createTable(pageFormat.getImageableX(),
                    pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
                    pageFormat.getImageableWidth() * TABLE_RATIO,
                    pageFormat.getImageableHeight() * 0.2 - 3.0));
    }
}
