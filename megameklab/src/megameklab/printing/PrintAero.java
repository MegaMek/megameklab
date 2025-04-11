/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import megamek.common.*;
import megamek.common.bays.Bay;
import megamek.common.bays.StandardSeatCargoBay;
import megameklab.printing.reference.AeroHitLocation;
import megameklab.printing.reference.AeroToHitMods;
import megameklab.printing.reference.AirToGroundAttackTable;
import megameklab.printing.reference.ChangingFacingCostTable;
import megameklab.printing.reference.ControlRollTable;
import megameklab.printing.reference.RandomMovementTable;
import megameklab.printing.reference.ReferenceTable;
import megameklab.printing.reference.StraightMovementTable;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Lays out record sheet for non-capital aerospace units
 */
public class PrintAero extends PrintEntity {

    /**
     * The aerospace unit being printed
     */
    private final Aero aero;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param aero      The aerospace unit to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintAero(Aero aero, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.aero = aero;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (aero instanceof SmallCraft) {
            return aero.isSpheroid() ? "smallcraft_spheroid_default.svg" : "smallcraft_aerodyne_default.svg";
        }
        if (aero instanceof ConvFighter) {
            return "fighter_conventional_default.svg";
        }
        return "fighter_aerospace_default.svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        StringBuilder sb = new StringBuilder();
        if (aero instanceof SmallCraft) {
            if (aero.isSpheroid()) {
                sb.append("Spheroid");
            } else {
                sb.append("Aerodyne");
            }
            if (aero instanceof Dropship) {
                sb.append(" Dropship");
            } else {
                sb.append(" Small Craft");
            }
        } else if (aero.isSupportVehicle()) {
            if (aero.getMovementMode().equals(EntityMovementMode.STATION_KEEPING)) {
                sb.append("Satellite Support Vehicle");
            } else if (aero.getMovementMode().equals(EntityMovementMode.AIRSHIP)) {
                sb.append("Airship Support Vehicle");
            } else {
                sb.append("Fixed Wing Support Vehicle");

            }
        } else if (aero instanceof ConvFighter) {
            sb.append("Conventional Fighter");
        } else if (aero.isOmni()) {
            sb.append("OmniFighter");
        } else {
            sb.append("Aerospace Fighter");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }

    @Override
    public Entity getEntity() {
        return aero;
    }

    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        super.processImage(pageNum, pageFormat);
        if (aero.tracksHeat()) {
            Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
            if (hsRect instanceof SVGRectElement) {
                drawHeatSinkPips((SVGRectElement) hsRect, aero.getHeatSinks());
            }
        }
        if (aero.isBomber()) {
            Element storesRect = getSVGDocument().getElementById(BOMB_BOXES);
            if (storesRect instanceof SVGRectElement) {
                drawBombBoxes((SVGRectElement) storesRect, aero.getMaxBombPoints());
            }
        }
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(HS_TYPE, formatHeatSinkType());
        setTextField(HS_COUNT, formatHeatSinkCount());
        if (aero.getEngine() != null) {
            setTextField(ENGINE_TYPE, aero.getEngine().getShortEngineName().replaceAll("\\[.*]", "").trim());
        } else {
            setTextField(ENGINE_TYPE, "Unknown");
        }
    }

    @Override
    protected void drawArmorStructurePips() {
        super.drawArmorStructurePips();
        Element element = getElementById(SI_PIPS);
        if (null != element) {
            ArmorPipLayout.addPips(this,
                  element,
                  aero.getOSI(),
                  PipType.CIRCLE,
                  0.5,
                  FILL_WHITE,
                  useAlternateArmorGrouping());
        }
    }

    @Override
    void writeArmorStructureTextFields() {
        final String FORMAT = "%d ( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(loc),
                  String.format(FORMAT, aero.getThresh(loc), getEntity().getOArmor(loc)));
        }
        setTextField(TEXT_SI, aero.getOSI());
    }

    @Override
    public String formatTacticalFuel() {
        return "Fuel Points: " + DecimalFormat.getInstance().format(aero.getOriginalFuel());
    }

    @Override
    public String formatFeatures() {
        StringJoiner sj = new StringJoiner(", ");
        if ((aero.getCockpitType() != Aero.COCKPIT_STANDARD) && (aero.getCockpitType() != Aero.COCKPIT_PRIMITIVE)) {
            sj.add(aero.getCockpitTypeString());
        }
        if (aero.isSupportVehicle()) {
            List<String> chassisMods = aero.getMisc()
                                             .stream()
                                             .filter(m -> m.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION))
                                             .map(m -> m.getType().getShortName())
                                             .collect(Collectors.toList());
            if (!chassisMods.isEmpty()) {
                sj.add(String.join(", ", chassisMods) + (chassisMods.size() == 1 ? " Chassis Mod" : " Chassis Mods"));
            }
        } else if ((aero instanceof ConvFighter) && aero.isVSTOL()) {
            sj.add("VSTOL Equipment");
        }
        if (aero.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            sj.add("Advanced Fire Control");
        } else if (aero.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
            sj.add("Basic Fire Control");
        }
        Map<String, Double> transport = new HashMap<>();
        Map<String, Integer> seating = new HashMap<>();
        for (Transporter t : aero.getTransports()) {
            if (t instanceof InfantryCompartment) {
                transport.merge("Infantry Compartment", t.getUnused(), Double::sum);
            } else if (t instanceof StandardSeatCargoBay) {
                seating.merge(t.getType(), (int) ((Bay) t).getCapacity(), Integer::sum);
                // include cargo bays for fighters and fixed wing, but small craft get a block for transport bays
            } else if (t instanceof Bay && !((Bay) t).isQuarters() && !(aero instanceof SmallCraft)) {
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
        Image fluffImage = getFluffImage();
        if (null != fluffImage) {
            Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
            if (rect instanceof SVGRectElement) {
                embedImage(fluffImage, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
            }
            hideElement(getSVGDocument().getElementById(NOTES));
        }
    }

    private String formatHeatSinkType() {
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return "Double Heat Sinks:";
        } else {
            return "Heat Sinks:";
        }
    }

    private String formatHeatSinkCount() {
        int hsCount = aero.getHeatSinks();
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return String.format("%d (%d)", hsCount, hsCount * 2);
        } else {
            return Integer.toString(hsCount);
        }
    }

    private void drawBombBoxes(SVGRectElement rect, int capacity) {
        if (capacity == 0) {
            // Turn label invisible
            hideElement(EXTERNAL_STORES, true);
            return;
        }
        final Element canvas = (Element) rect.getParentNode();
        final Rectangle2D bbox = getRectBBox(rect);
        int nRows = (capacity + 4) / 5;
        double boxWidth = bbox.getWidth() / 5.0;
        double boxHeight = bbox.getHeight() / 4.0;
        double yPos = bbox.getMinY() + 1.0;
        for (int r = 0; r < nRows; r++) {
            int cols;
            if ((r + 1 < nRows) || (capacity % 5) == 0) {
                cols = 5;
            } else {
                cols = capacity % 5;
            }
            double xPos = bbox.getCenterX() - cols * boxWidth * 0.5 + 1.0;
            for (int c = 0; c < cols; c++) {
                Element path = createRoundedRectangle(xPos,
                      yPos,
                      boxWidth - 2.0,
                      boxHeight - 2.0,
                      4.3,
                      2.375,
                      0.966,
                      FILL_BLACK);
                canvas.appendChild(path);
                xPos += boxWidth;
            }
            yPos += boxHeight;
        }
        Element key = getSVGDocument().getElementById(EXTERNAL_STORES_KEY);
        if (null != key) {
            key.setAttributeNS(null,
                  SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                  SVGConstants.SVG_TRANSLATE_VALUE + "(0," + yPos + ")");
        }
    }

    @Override
    protected String formatWalk() {
        return Integer.toString(getEntity().getWalkMP());
    }

    @Override
    protected String formatRun() {
        return Integer.toString(getEntity().getRunMP());
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return true;
    }

    @Override
    protected boolean includeReferenceCharts() {
        return options.showReferenceCharts();
    }

    @Override
    protected List<ReferenceTable> getRightSideReferenceTables() {
        List<ReferenceTable> list = new ArrayList<>();
        list.add(new AeroToHitMods(this));
        list.add(new ControlRollTable(this));
        if (aero.isFighter() || ((aero instanceof SmallCraft) && !aero.isSpheroid())) {
            list.add(new StraightMovementTable(this));
        }
        if (!(aero instanceof ConvFighter) && !(aero instanceof SpaceStation)) {
            list.add(new ChangingFacingCostTable(this));
        }
        // This goes at the bottom for other units
        if (aero instanceof Warship) {
            list.add(new RandomMovementTable(this, true));
        }
        return list;
    }

    @Override
    protected void addReferenceCharts(PageFormat pageFormat) {
        super.addReferenceCharts(pageFormat);
        ReferenceTable table = new AeroHitLocation(this);
        if ((getEntity() instanceof Jumpship) ||
                  (getEntity().getMovementMode().equals(EntityMovementMode.STATION_KEEPING))) {
            getSVGDocument().getDocumentElement()
                  .appendChild(table.createTable(pageFormat.getImageableX(),
                        pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
                        pageFormat.getImageableWidth() * TABLE_RATIO,
                        pageFormat.getImageableHeight() * 0.2 - 3.0));
        } else {
            double x = pageFormat.getImageableX();
            double height = pageFormat.getImageableHeight() * (1 - TABLE_RATIO);
            getSVGDocument().getDocumentElement()
                  .appendChild(table.createTable(x,
                        pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
                        pageFormat.getImageableWidth() * 0.5 - 3.0,
                        height - 3.0));
            x += pageFormat.getImageableWidth() * 0.5;
            table = new AirToGroundAttackTable(this);
            getSVGDocument().getDocumentElement()
                  .appendChild(table.createTable(x,
                        pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
                        pageFormat.getImageableWidth() * (TABLE_RATIO - 0.5),
                        height * 0.5 - 3.0));
            table = new RandomMovementTable(this, false);
            getSVGDocument().getDocumentElement()
                  .appendChild(table.createTable(x,
                        pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0 + height * 0.5,
                        pageFormat.getImageableWidth() * (TABLE_RATIO - 0.5),
                        height * 0.5 - 3.0));
        }
    }
}
