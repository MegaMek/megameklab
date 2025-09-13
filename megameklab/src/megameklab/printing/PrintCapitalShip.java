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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.geom.Rectangle2D;
import java.util.concurrent.atomic.AtomicInteger;

import megamek.common.units.Jumpship;
import megamek.common.units.SpaceStation;
import megamek.common.units.UnitType;
import megamek.common.units.Warship;
import megamek.logging.MMLogger;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Generates a record sheet image for JumpShips, WarShips, and space stations.
 *
 * @author arlith
 * @author Neoancient
 */
public class PrintCapitalShip extends PrintDropship {
    private static final MMLogger logger = MMLogger.create(PrintCapitalShip.class);

    /** Default width for armor pip */
    public static final double ARMOR_PIP_WIDTH = 4.5;
    /** Default height for armor pip */
    public static final double ARMOR_PIP_HEIGHT = 4.5;
    /**
     * Amount to offset the armor block drop shadow as a fraction of pip height/width
     */
    public static final double SHADOW_OFFSET = 0.3;

    /** Default width for structure pips */
    public static final int IS_PIP_WIDTH = 4;
    /** Default height for structure pips */
    public static final int IS_PIP_HEIGHT = 4;

    /** Default width of armor block in number of pips */
    public static final int PIPS_PER_ROW = 10;
    /** Default height of armor block in number of pips */
    public static final int MAX_PIP_ROWS = 10;

    /**
     * The ship being printed
     */
    private final Jumpship ship;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param ship      The ship to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintCapitalShip(Jumpship ship, int startPage, RecordSheetOptions options) {
        super(ship, startPage, options);
        this.ship = ship;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (pageNumber > 0) {
            return "advaero_reverse.svg";
        }
        if (ship instanceof Warship) {
            return "warship_default.svg";
        } else if (ship instanceof SpaceStation) {
            return "spacestation_default.svg";
        } else {
            return "jumpship_default.svg";
        }
    }

    @Override
    protected String getRecordSheetTitle() {
        return UnitType.getTypeDisplayableName(ship.getUnitType())
              + " Record Sheet";
    }

    @Override
    int noseHeat() {
        return ship.getHeatInArc(Jumpship.LOC_NOSE, false);
    }

    @Override
    int foreLeftHeat() {
        return ship.getHeatInArc(Jumpship.LOC_FLS, false);
    }

    @Override
    int foreRightHeat() {
        return ship.getHeatInArc(Jumpship.LOC_FRS, false);
    }

    @Override
    int aftLeftHeat() {
        return ship.getHeatInArc(Jumpship.LOC_ALS, false);
    }

    @Override
    int aftRightHeat() {
        return ship.getHeatInArc(Jumpship.LOC_ARS, false);
    }

    @Override
    int aftHeat() {
        return ship.getHeatInArc(Jumpship.LOC_AFT, false);
    }

    @Override
    int broadsidesLeftHeat() {
        if (ship instanceof Warship) {
            return ship.getHeatInArc(Warship.LOC_LBS, false);
        }
        return 0;
    }

    @Override
    int broadsidesRightHeat() {
        if (ship instanceof Warship) {
            return ship.getHeatInArc(Warship.LOC_RBS, false);
        }
        return 0;
    }

    @Override
    protected void drawStructure() {
        setTextField(TEXT_KF_DRIVE, ship.getKFIntegrity());
        setTextField(TEXT_SAIL, ship.getSailIntegrity());
        setTextField(TEXT_DOCKING_COLLARS, ship.getDockingCollars().size());

        if (ship instanceof Warship) {
            printInternalRegion(SI_PIPS, ship.getOSI(), (ship.getOSI() - ship.getSI()), 100, "SI");
        }
        printInternalRegion(KF_PIPS, ship.getOKFIntegrity(), (ship.getOKFIntegrity() - ship.getKFIntegrity()), 30,
              "KF");
        printInternalRegion(SAIL_PIPS, ship.getOSailIntegrity(), (ship.getOSailIntegrity() - ship.getSailIntegrity())
              , 10, "SAIL");
        final int collarCount = ship.getDockingCollars().size();
        final int collarDamage = getCollarDamage();
        printInternalRegion(DC_PIPS, collarCount, collarDamage, 10, "DC");
    }

    @Override
    protected void drawArmorStructurePips() {
        for (int loc = ship.firstArmorIndex(); loc < Jumpship.LOC_HULL; loc++) {
            final String id = ARMOR_PIPS + ship.getLocationAbbr(loc);
            Element element = getSVGDocument().getElementById(id);
            if (element instanceof SVGRectElement) {
                printArmorRegion((SVGRectElement) element, ship.getOArmor(loc),
                      (ship.getOArmor(loc) - ship.getArmor(loc)), ship.getLocationAbbr(loc));
            } else {
                logger.error("No SVGRectElement found with id {}", id);
            }
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param rectId       The id of the rectangle element that describes the outline of the region to print pips
     * @param structure    The number of structure pips
     * @param damage       The number of structure pips that are damaged
     * @param pipsPerBlock The maximum number of pips to draw in a single block
     * @param location     Location abbreviation for the region
     */
    private void printInternalRegion(String rectId, int structure, int damage, int pipsPerBlock,
          String location) {
        Element element = getSVGDocument().getElementById(rectId);
        if (element instanceof SVGRectElement) {
            printInternalRegion((SVGRectElement) element, structure, damage, pipsPerBlock, location);
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param svgRect      The rectangle that describes the outline of the region to print pips
     * @param structure    The number of structure pips
     * @param damage       The number of structure pips that are damaged
     * @param pipsPerBlock The maximum number of pips to draw in a single block
     */
    private void printInternalRegion(SVGRectElement svgRect, int structure, int damage, int pipsPerBlock,
          String location) {
        Rectangle2D bbox = getRectBBox(svgRect);
        final double blockWidth = PIPS_PER_ROW * IS_PIP_WIDTH;
        int pips;
        double startX;
        if (structure > pipsPerBlock) {
            pips = structure / 2;
            startX = bbox.getCenterX() - blockWidth - IS_PIP_WIDTH * 0.5;
        } else {
            pips = structure;
            startX = bbox.getCenterX() - blockWidth * 0.5;
        }
        AtomicInteger remainingDamage = new AtomicInteger(damage);
        printPipBlock(startX, bbox.getY(), (SVGElement) svgRect.getParentNode(), pips,
              IS_PIP_WIDTH, IS_PIP_HEIGHT, FILL_WHITE, false, remainingDamage, "structure", location);
        if (structure > pips) {
            printPipBlock(startX + blockWidth + IS_PIP_WIDTH, bbox.getY(), (SVGElement) svgRect.getParentNode(),
                  structure - pips, IS_PIP_WIDTH, IS_PIP_HEIGHT, FILL_WHITE, false, remainingDamage,
                  "structure", location);
        }
    }

    /**
     * Method to determine rectangle grid for armor or internal pips and draw it.
     *
     * @param svgRect A rectangle that outlines the border of the space for the armor block.
     * @param armor   The amount of armor in the location
     */
    private void printArmorRegion(SVGRectElement svgRect, int armor, int damage, String location) {
        Rectangle2D bbox = getRectBBox(svgRect);

        double pipWidth = ARMOR_PIP_WIDTH;
        double pipHeight = ARMOR_PIP_HEIGHT;

        // Size of each block include 0.5 pip margin on each side
        double blockHeight = (MAX_PIP_ROWS + 1) * pipHeight;
        double blockWidth = (PIPS_PER_ROW + 1) * pipWidth;

        int numBlocks = (int) Math.ceil((float) armor / (MAX_PIP_ROWS * PIPS_PER_ROW));
        int rows = 1;
        int cols = 1;
        if (bbox.getWidth() > bbox.getHeight()) {
            // landscape; as many columns as needed to fit everything in one or two rows
            cols = numBlocks;
            if ((numBlocks * blockWidth) > bbox.getWidth()) {
                rows++;
                cols = (numBlocks + 1) / 2;
            }
        } else {
            // portrait; as many rows as needed to fit everything in one or two columns
            rows = numBlocks;
            if ((numBlocks * blockHeight) > bbox.getHeight()) {
                cols++;
                rows = (numBlocks + 1) / 2;
            }
        }
        // Check the ratio of the space required to space available. If either exceeds,
        // scale both
        // dimensions down equally to fit.
        double ratio = Math.max((rows * blockHeight) / bbox.getHeight(), (cols * blockWidth) / bbox.getWidth());
        if (ratio > 1.0) {
            pipHeight /= ratio;
            pipWidth /= ratio;
            blockHeight /= ratio;
            blockWidth /= ratio;
        }
        // Center on edge closest to ship outline
        final double startX = bbox.getX() + ((bbox.getWidth() - (blockWidth * cols - ARMOR_PIP_WIDTH)) / 2.0);
        int leftOver = armor % (MAX_PIP_ROWS * PIPS_PER_ROW);
        // Partial rows are automatically centered horizontally. But if we have an
        // incomplete block
        // that is the only one on the row, we should adjust the starting y as well.
        double actualHeight = blockHeight * rows;
        if (leftOver > 0 && (cols == 1 || numBlocks % cols == 1)) {
            int missingRows = MAX_PIP_ROWS - leftOver / PIPS_PER_ROW - 1;
            actualHeight -= ARMOR_PIP_HEIGHT * missingRows;
        }
        final double startY = bbox.getY() + ((bbox.getHeight() - actualHeight) / 2.0);

        double xPosition = startX;
        double yPosition = startY;
        int remainingBlocks = numBlocks;
        AtomicInteger remainingDamage = new AtomicInteger(damage);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                armor = printPipBlock(xPosition, yPosition, (SVGElement) svgRect.getParentNode(),
                      armor, pipWidth, pipHeight, FILL_WHITE, true, remainingDamage, "armor", location);
                remainingBlocks--;
                xPosition += blockWidth;
            }
            xPosition = startX;
            yPosition += blockWidth;
            // Check whether the last row is a short one.
            if (remainingBlocks < cols) {
                xPosition += blockWidth / 2.0;
            }
        }
    }

    /**
     * Helper function to print an armor pip block. Can print up to 100 points of armor. Any unprinted armor pips are
     * returned.
     *
     * @param startX  The x coordinate of the top left of the block
     * @param startY  The y coordinate of the top left of the block
     * @param parent  The parent node of the bounding rectangle
     * @param numPips The number of pips to print
     * @param shadow  Whether to add a drop shadow
     *
     * @return The Y location of the end of the block
     */
    private int printPipBlock(double startX, double startY, SVGElement parent, int numPips, double pipWidth,
          double pipHeight, String fillColor, boolean shadow, AtomicInteger remainingDamage,
          String className, String location) {

        final double shadowOffsetX = pipWidth * SHADOW_OFFSET;
        final double shadowOffsetY = pipHeight * SHADOW_OFFSET;
        double currX, currY;
        currY = startY;
        for (int row = 0; row < MAX_PIP_ROWS; row++) {
            int numRowPips = Math.min(numPips, PIPS_PER_ROW);
            // Adjust row start if it's not a complete row
            currX = startX + ((((PIPS_PER_ROW - numRowPips) / 2f) * pipWidth) + 0.5);
            for (int col = 0; col < numRowPips; col++) {
                boolean isDamaged = (remainingDamage.decrementAndGet() >= 0);
                if (shadow) {
                    parent.appendChild(createPip(pipWidth, pipHeight, FILL_SHADOW, currX + shadowOffsetX,
                          currY + shadowOffsetY, false, null, null));
                }
                final Element pip = createPip(pipWidth, pipHeight, isDamaged ? getDamageFillColor() : fillColor, currX,
                      currY, true, className, location);
                parent.appendChild(pip);

                currX += pipWidth;
                numPips--;
                // Check to see if we're done
                if (numPips <= 0) {
                    return 0;
                }
            }
            currY += pipHeight;
        }
        return numPips;
    }

    private Element createPip(double pipWidth, double pipHeight, String fillColor,
          double currX, double currY, boolean stroke, String className, String location) {
        Element box = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_RECT_TAG);
        box.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(currX));
        box.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(currY));
        box.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pipWidth));
        box.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pipHeight));
        if (stroke) {
            String classAttr = "pip square";
            if (className != null && !className.isEmpty()) {
                classAttr += " " + className;
            }
            box.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, classAttr);
            if (location != null && !location.isEmpty()) {
                box.setAttributeNS(null, "loc", location);
            }
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(0.5));
        }
        box.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fillColor);
        return box;
    }

    @Override
    public boolean supportsAlternateArmorGrouping() {
        return false;
    }
}
