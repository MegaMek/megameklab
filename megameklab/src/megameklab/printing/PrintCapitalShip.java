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
package megameklab.printing;

import megamek.common.Jumpship;
import megamek.common.SpaceStation;
import megamek.common.UnitType;
import megamek.common.Warship;
import org.apache.batik.util.SVGConstants;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;

/**
 * Generates a record sheet image for JumpShips, WarShips, and space stations.
 *
 * @author arlith
 * @author Neoancient
 */
public class PrintCapitalShip extends PrintDropship {

    /** Default width for armor pip */
    public static final double ARMOR_PIP_WIDTH = 4.5;
    /** Default height for armor pip */
    public static final double ARMOR_PIP_HEIGHT = 4.5;
    /**
     * Amount to offset the armor block drop shadow as a fraction of pip
     * height/width
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
     * @param ship
     *            The ship to print
     * @param startPage
     *            The print job page number for this sheet
     * @param options
     *            Overrides the global options for which elements are printed
     */
    public PrintCapitalShip(Jumpship ship, int startPage, RecordSheetOptions options) {
        super(ship, startPage, options);
        this.ship = ship;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param ship
     *            The ship to print
     * @param startPage
     *            The print job page number for this sheet
     */
    public PrintCapitalShip(Jumpship ship, int startPage) {
        this(ship, startPage, new RecordSheetOptions());
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
        setTextField(TEXT_KFDRIVE, ship.getKFIntegrity());
        setTextField(TEXT_SAIL, ship.getSailIntegrity());
        setTextField(TEXT_DOCKING_COLLARS, ship.getDockingCollars().size());

        if (ship instanceof Warship) {
            printInternalRegion(SI_PIPS, ship.get0SI(), 100);
        }
        printInternalRegion(KF_PIPS, ship.getKFIntegrity(), 30);
        printInternalRegion(SAIL_PIPS, ship.getSailIntegrity(), 10);
        printInternalRegion(DC_PIPS, ship.getDockingCollars().size(), 10);
    }

    @Override
    protected void drawArmorStructurePips() {
        for (int loc = ship.firstArmorIndex(); loc < Jumpship.LOC_HULL; loc++) {
            final String id = ARMOR_PIPS + ship.getLocationAbbr(loc);
            Element element = getSVGDocument().getElementById(id);
            if (element instanceof SVGRectElement) {
                printArmorRegion((SVGRectElement) element, ship.getOArmor(loc));
            } else {
                LogManager.getLogger().error("No SVGRectElement found with id " + id);
            }
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param rectId
     *            The id of the rectangle element that describes the outline of the
     *            region to print pips
     * @param structure
     *            The number of structure pips
     * @param pipsPerBlock
     *            The maximum number of pips to draw in a single block
     */
    private void printInternalRegion(String rectId, int structure, int pipsPerBlock) {
        Element element = getSVGDocument().getElementById(rectId);
        if (element instanceof SVGRectElement) {
            printInternalRegion((SVGRectElement) element, structure, pipsPerBlock);
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param svgRect
     *            The rectangle that describes the outline of the region to print
     *            pips
     * @param structure
     *            The number of structure pips
     * @param pipsPerBlock
     *            The maximum number of pips to draw in a single block
     */
    private void printInternalRegion(SVGRectElement svgRect, int structure, int pipsPerBlock) {
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
        printPipBlock(startX, bbox.getY(), (SVGElement) svgRect.getParentNode(), pips,
                IS_PIP_WIDTH, IS_PIP_HEIGHT, FILL_WHITE, false);
        if (structure > pips) {
            printPipBlock(startX + blockWidth + IS_PIP_WIDTH, bbox.getY(), (SVGElement) svgRect.getParentNode(),
                    structure - pips, IS_PIP_WIDTH, IS_PIP_HEIGHT, FILL_WHITE, false);
        }
    }

    /**
     * Method to determine rectangle grid for armor or internal pips and draw it.
     *
     * @param svgRect
     *            A rectangle that outlines the border of the space for the armor
     *            block.
     * @param armor
     *            The amount of armor in the location
     */
    private void printArmorRegion(SVGRectElement svgRect, int armor) {
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
        // Partial rows are automatically centered horizontally. But if we have an incomplete block
        // that is the only one on the row, we should adjust the starting y as well.
        double actualHeight = blockHeight * rows;
        if (leftOver > 0 && (cols == 1 || numBlocks % cols == 1)) {
            int missingRows = MAX_PIP_ROWS - leftOver / PIPS_PER_ROW - 1;
            actualHeight -= ARMOR_PIP_HEIGHT * missingRows;
        }
        final double startY = bbox.getY() + ((bbox.getHeight() - actualHeight) / 2.0);

        double xpos = startX;
        double ypos = startY;
        int remainingBlocks = numBlocks;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                armor = printPipBlock(xpos, ypos, (SVGElement) svgRect.getParentNode(),
                        armor, pipWidth, pipHeight, "#ffffff", true);
                remainingBlocks--;
                xpos += blockWidth;
            }
            xpos = startX;
            ypos += blockWidth;
            // Check whether the last row is a short one.
            if (remainingBlocks < cols) {
                xpos += blockWidth / 2.0;
            }
        }
    }

    /**
     * Helper function to print a armor pip block. Can print up to 100 points of
     * armor. Any unprinted armor pips are returned.
     *
     * @param startX
     *            The x coordinate of the top left of the block
     * @param startY
     *            The y coordinate of the top left of the block
     * @param parent
     *            The parent node of the bounding rectangle
     * @param numPips
     *            The number of pips to print
     * @param shadow
     *            Whether to add a drop shadow
     * @return The Y location of the end of the block
     */
    private int printPipBlock(double startX, double startY, SVGElement parent, int numPips, double pipWidth,
            double pipHeight, String fillColor, boolean shadow) {

        final double shadowOffsetX = pipWidth * SHADOW_OFFSET;
        final double shadowOffsetY = pipHeight * SHADOW_OFFSET;
        double currX, currY;
        currY = startY;
        for (int row = 0; row < MAX_PIP_ROWS; row++) {
            int numRowPips = Math.min(numPips, PIPS_PER_ROW);
            // Adjust row start if it's not a complete row
            currX = startX + ((((PIPS_PER_ROW - numRowPips) / 2f) * pipWidth) + 0.5);
            for (int col = 0; col < numRowPips; col++) {
                if (shadow) {
                    parent.appendChild(createPip(pipWidth, pipHeight, FILL_SHADOW, currX + shadowOffsetX,
                            currY + shadowOffsetY, false));
                }
                parent.appendChild(createPip(pipWidth, pipHeight, fillColor, currX, currY, true));

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
            double currX, double currY, boolean stroke) {
        Element box = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_RECT_TAG);
        box.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(currX));
        box.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(currY));
        box.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pipWidth));
        box.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pipHeight));
        if (stroke) {
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(0.5));
        }
        box.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fillColor);
        return box;
    }
}