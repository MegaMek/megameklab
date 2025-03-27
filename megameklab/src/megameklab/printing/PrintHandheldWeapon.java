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
package megameklab.printing;

import java.awt.geom.Rectangle2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import megamek.codeUtilities.StringUtility;
import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.HandheldWeapon;
import megamek.common.WeaponType;
import megameklab.util.CConfig;

/**
 * Lays out a record sheet block for a single handheld weapon unit
 */
public class PrintHandheldWeapon extends PrintEntity {

    /** Default radius for a pip */
    public static final double PIP_RADIUS = 3.5;
    /** Default minimum radius for pip */
    public static final double PIP_MIN_RADIUS = 1.6;
    /** Default stroke width for a pip */
    public static final double PIP_STROKE_WIDTH = 0.9;
    /** Default minimum amount of pips for a row */
    public static final int MIN_PIPS_PER_ROW = 8;
    /** Maximum amount of pips displayable, after that we leave blank */
    public static final int MAX_ARMOR_PIPS = 360;
    /** Maximum amount of pips displayable, after that we leave blank */
    public static final int MAX_AMMO_PIPS = 400;

    /** Threshold for large layout */
    public static final int LARGE_LAYOUT_THRESHOLD_ARMOR_PIPS = 65;
    public static final int LARGE_LAYOUT_THRESHOLD_WEAPON_TYPE_COUNT = 2;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_TYPE_COUNT = 2;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT1 = 200;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT2 = 120;

    private final HandheldWeapon handheldWeapon;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param handheldWeapon The handheld weapon to print
     * @param startPage      The print job page number for this sheet
     * @param options        Overrides the global options for which elements are
     *                       printed
     */
    public PrintHandheldWeapon(HandheldWeapon handheldWeapon, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.handheldWeapon = handheldWeapon;
    }

    public void layoutPips(SVGElement target, Rectangle2D bbox, int pipsCount) {
        // Define constants.
        // We work with square side lengths equal to 2*radius.
        final double S_min = 2 * PIP_MIN_RADIUS;
        final double S_max = 2 * PIP_RADIUS;

        // Get SVG rectangle dimensions.
        double W = bbox.getWidth();
        double H = bbox.getHeight();
        double startX = bbox.getX();
        double startY = bbox.getY();
        // Reserve stroke margin so squares (including stroke) remain inside.
        double effectiveWidth = W - PIP_STROKE_WIDTH;
        double effectiveHeight = H - PIP_STROKE_WIDTH;

        // We'll determine a grid configuration.
        // For multi-row (pipsCount > 8), we search for candidate column counts between
        // 8 and pipsCount.
        // For a single row (pipsCount ≤ 8), we force a grid of 8 columns (for spacing)
        // and 1 row.
        int bestCols = 0;
        int bestRows = 0;
        double bestSquareSize = 0;
        double bestGap = 0;

        if (pipsCount <= 8) {
            // Special case: one row but spacing computed as if there were 8 columns.
            bestCols = 8;
            bestRows = 1;
            // Horizontal: 8*S + 7*gap = effectiveWidth, so maximum S from width is
            // effectiveWidth/8.
            double S_horizontal_max = effectiveWidth / 8.0;
            // Vertical: since one row, S can be as high as effectiveHeight.
            double S_vertical_max = effectiveHeight;
            double S_candidate = Math.min(S_horizontal_max, S_vertical_max);
            // Also, to have gap <= S, we need S >= effectiveWidth/(2*8 - 1) =
            // effectiveWidth/15.
            double S_lower_from_gap = effectiveWidth / 15.0;
            if (S_candidate < S_lower_from_gap) {
                System.out.println("Calculated square size (" + S_candidate +
                        ") is too small given the spacing requirements.");
                return;
            }
            // Clamp to global bounds.
            S_candidate = Math.max(S_candidate, S_min);
            S_candidate = Math.min(S_candidate, S_max);
            double gap = (effectiveWidth - 8 * S_candidate) / 7.0;
            // Verify gap condition.
            if (gap < 0 || gap > S_candidate) {
                System.out.println("Invalid gap computed: " + gap);
                return;
            }
            bestSquareSize = S_candidate;
            bestGap = gap;
        } else {
            // pipsCount > 8: try candidate configurations with at least 8 columns.
            int candidateStartCols = 8;
            int candidateEndCols = pipsCount; // maximum possible columns is pipsCount (each pip in its own column)

            for (int cols = candidateStartCols; cols <= candidateEndCols; cols++) {
                int rows = (int) Math.ceil((double) pipsCount / cols);
                // For multi-row, we require that every full row (except possibly the last) has
                // at least 8 squares.
                // Since cols is at least 8, this condition is automatically met.

                // Horizontal equation: cols * S + (cols - 1) * gap = effectiveWidth.
                // For gap ≤ S, we need: S >= effectiveWidth / (2*cols - 1).
                double S_lower_from_gap = effectiveWidth / (2.0 * cols - 1);
                double S_horizontal_max = effectiveWidth / cols;

                // Vertical constraint:
                // If rows == 1 then S_vertical_max = effectiveHeight.
                // Otherwise, derive an upper bound from:
                // rows * S + (rows - 1) * ((effectiveWidth - cols*S)/(cols - 1)) <=
                // effectiveHeight.
                double S_vertical_max;
                if (rows == 1) {
                    S_vertical_max = effectiveHeight;
                } else if (cols != rows) {
                    S_vertical_max = (effectiveHeight * (cols - 1) - (rows - 1) * effectiveWidth) / (cols - rows);
                } else {
                    S_vertical_max = effectiveHeight / rows;
                }

                double S_candidate = Math.min(S_horizontal_max, S_vertical_max);
                if (S_candidate < S_lower_from_gap)
                    continue;
                // Enforce global min and max.
                S_candidate = Math.max(S_candidate, S_min);
                S_candidate = Math.min(S_candidate, S_max);

                double gap = (effectiveWidth - cols * S_candidate) / (cols - 1);
                if (gap < 0 || gap > S_candidate)
                    continue;
                if (rows * S_candidate + (rows - 1) * gap > effectiveHeight + 1e-9)
                    continue;

                if (S_candidate > bestSquareSize) {
                    bestSquareSize = S_candidate;
                    bestCols = cols;
                    bestRows = rows;
                    bestGap = gap;
                }
            }
        }

        if (bestSquareSize < S_min || bestSquareSize <= 0) {
            System.out.println("Calculated square size (" + bestSquareSize +
                    ") is less than the minimum allowed (" + S_min + "). No squares created.");
            return;
        }

        // Finally, create exactly pipsCount squares.
        // The grid is computed using bestCols and bestRows (which for pipsCount<=8 is
        // set to 8 and 1 respectively).
        int created = 0;
        for (int r = 0; r < bestRows && created < pipsCount; r++) {
            for (int c = 0; c < bestCols && created < pipsCount; c++) {
                double x = startX + (c * (bestSquareSize + bestGap));
                double y = startY + (r * (bestSquareSize + bestGap));
                Element pip = createSquarePip(x, y, bestSquareSize, PIP_STROKE_WIDTH);
                target.appendChild(pip);
                created++;
            }
        }
    }

    public void printOptimalPipLayout(SVGRectElement svgRect, int count) {
        if (count < 1) {
            return;
        }
        SVGElement target = (SVGElement) svgRect.getParentNode();
        if (target == null) {
            return; // No parent element to append to
        }
        // Get the rectangle parameters (assumed available via getters)
        Rectangle2D svgRectBBox = getRectBBox(svgRect);
        double rectX = svgRectBBox.getX();
        double rectY = svgRectBBox.getY();
        double rectWidth = svgRectBBox.getWidth();
        double rectHeight = svgRectBBox.getHeight();

        double DIAMETER_MAX = PIP_RADIUS * 2;
        double DIAMETER_MIN = PIP_MIN_RADIUS * 2;

        double bestPipSize = -1;
        double bestGap = 0;
        int bestCols = 0;
        int bestRows = 0;

        // If count > 1, we start from 2 columns because a single column with multiple
        // rows is disallowed.
        int startCols = (count == 1) ? 1 : 2;
        for (int cols = startCols; cols <= count; cols++) {
            int rows = (int) Math.ceil((double) count / cols);

            double candidateGap;
            double candidatePipSize;

            // CASE: Single row (many columns)
            if (rows == 1) {
                candidateGap = PIP_STROKE_WIDTH; // minimal gap
                candidatePipSize = (rectWidth - (cols - 1) * candidateGap) / cols;
                // Must also fit vertically.
                if (candidatePipSize > rectHeight) {
                    continue; // candidate doesn't fit
                }
            }
            // CASE: Multiple rows
            else {
                // Start with minimal allowed gap.
                candidateGap = PIP_STROKE_WIDTH;
                candidatePipSize = (rectWidth - (cols - 1) * candidateGap) / cols;
                double verticalOccupancy = rows * candidatePipSize + (rows - 1) * candidateGap;

                if (verticalOccupancy > rectHeight) {
                    // If the grid is too tall, try adjusting gap if possible (only if rows !=
                    // cols).
                    if (rows != cols) {
                        candidateGap = (rectHeight * cols - rows * rectWidth) / (rows - cols);
                        candidatePipSize = (rectWidth - (cols - 1) * candidateGap) / cols;
                        if (candidateGap < PIP_STROKE_WIDTH || candidateGap > candidatePipSize) {
                            continue; // invalid candidate, skip it
                        }
                    } else {
                        continue; // cannot adjust gap if rows == cols, so skip
                    }
                }
            }

            // Clamp square size to SQUARE_MAX_WIDTH if necessary.
            if (candidatePipSize > DIAMETER_MAX) {
                candidatePipSize = DIAMETER_MAX;
                if (cols > 1) {
                    candidateGap = (rectWidth - cols * candidatePipSize) / (cols - 1);
                } else {
                    candidateGap = PIP_STROKE_WIDTH;
                }
                double verticalOccupancy = rows * candidatePipSize + (rows - 1) * candidateGap;
                if (verticalOccupancy > rectHeight) {
                    continue;
                }
            }

            if (candidatePipSize < DIAMETER_MIN) {
                continue;
            }

            // Choose candidate if it produces a larger square.
            if (candidatePipSize > bestPipSize) {
                bestPipSize = candidatePipSize;
                bestGap = candidateGap;
                bestCols = cols;
                bestRows = rows;
            }
        }

        // Abort if no candidate yields a valid square size.
        if (bestPipSize < DIAMETER_MIN) {
            System.out.println("Calculated square size (" + bestPipSize
                    + ") is less than the minimum allowed (" + DIAMETER_MIN + "). Aborting.");
            return;
        }

        double xStep = (bestPipSize + bestGap);
        double yStep = (bestPipSize + bestGap);
        if (yStep > (bestPipSize * 2)) {
            yStep = bestPipSize * 2;
        }

        // Now, loop through rows and columns (top-left aligned).
        // If there are more grid cells than the count, we stop after creating 'count'
        // squares.
        int createdCount = 0;
        for (int row = 0; row < bestRows && createdCount < count; row++) {
            for (int col = 0; col < bestCols && createdCount < count; col++) {
                double x = rectX + col * yStep;
                double y = rectY + row * xStep;
                // Create the square using the helper function.
                Element pip = createSquarePip(x, y, bestPipSize, PIP_STROKE_WIDTH);
                target.appendChild(pip);
                createdCount++;
            }
        }
    }

    /**
     * Creates a square pip SVG element.
     * 
     * @param x           X coordinate of the top-left corner
     * @param y           Y coordinate of the top-left corner
     * @param size        Size of the square
     * @param strokeWidth Width of the stroke
     * @return SVG element representing a square pip
     */
    private Element createSquarePip(double x, double y, double size, double strokeWidth) {
        Element square = getSVGDocument().createElementNS(SVGConstants.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
        square.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(x));
        square.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, Double.toString(y));
        square.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, Double.toString(size));
        square.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, Double.toString(size));
        square.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, Double.toString(strokeWidth));
        square.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, "black");
        square.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "none");
        return square;
    }

    public void createPips(SVGRectElement svgRect, int pipsCount) {
        // Get the bounding box of the given rectangle.
        Rectangle2D svgRectBBox = getRectBBox(svgRect);
        double rectX = svgRectBBox.getX();
        double rectY = svgRectBBox.getY();
        double rectWidth = svgRectBBox.getWidth();
        double rectHeight = svgRectBBox.getHeight();

        // Constants (assumed to be defined elsewhere in your code)
        final double minRadius = PIP_MIN_RADIUS; // minimum allowed pip radius
        final double maxRadius = PIP_RADIUS; // maximum (and starting) pip radius
        final double stroke = PIP_STROKE_WIDTH; // stroke width (drawn outside the pip)

        // For a pip of radius r, its drawn boundary extends r+stroke from its center.
        // To guarantee a gap of one stroke between adjacent pips,
        // the minimal center-to-center distance must be:
        // d = 2*r + 3*stroke

        // --- 1. Check one-row layout using maxRadius ---
        // For a one-row layout:
        // The required width is:
        // widthOneRow = 2*(maxRadius+stroke) + (pipsCount-1) * (2*maxRadius+3*stroke)
        // and the required height is:
        // heightOneRow = 2*(maxRadius+stroke)
        double oneRowRequiredWidth = 2 * (maxRadius + stroke) + (pipsCount - 1) * (2 * maxRadius + 3 * stroke);
        double oneRowRequiredHeight = 2 * (maxRadius + stroke);
        if (oneRowRequiredWidth <= rectWidth && oneRowRequiredHeight <= rectHeight) {
            // One row fits: compute pip centers such that the leftmost pip is flush.
            // For the first pip, we want:
            // centerX = rectX + (maxRadius + stroke)
            // Then each subsequent pip is offset by (2*maxRadius+3*stroke)
            double bestPipRadius = maxRadius;
            SVGElement target = (SVGElement) svgRect.getParentNode();
            for (int j = 0; j < pipsCount; j++) {
                double x = rectX + (bestPipRadius + stroke) + j * (2 * bestPipRadius + 3 * stroke);
                double y = rectY + (bestPipRadius + stroke); // single row (top-aligned)
                Element pip = createPip(x, y, bestPipRadius, stroke);
                target.appendChild(pip);
            }
            return;
        }

        // --- 2. Determine optimal grid layout ---
        // Try possible numbers of rows (from 1 up to pipsCount).
        // For a given candidate grid of (rows x cols) where cols =
        // ceil(pipsCount/rows),
        // the pips must fit horizontally and vertically.
        // Horizontally the drawn pip areas (including stroke) will span:
        // totalWidth = 2*(r+stroke) + (cols-1) * (2*r+3*stroke)
        // which rearranges to: 2*cols*r + (3*cols - 1)*stroke.
        // So the maximum r allowed horizontally is:
        // r <= (rectWidth - (3*cols - 1)*stroke) / (2 * cols)
        // Similarly, vertically:
        // r <= (rectHeight - (3*rows - 1)*stroke) / (2 * rows)
        // The candidate pip radius is the minimum of these two and must not exceed
        // maxRadius.
        double bestCandidateRadius = -1;
        int bestRows = 0;
        int bestCols = 0;
        for (int rows = 1; rows <= pipsCount; rows++) {
            int cols = (int) Math.ceil((double) pipsCount / rows);
            double candidateRHoriz = (rectWidth - (3 * cols - 1) * stroke) / (2 * cols);
            double candidateRVert = (rectHeight - (3 * rows - 1) * stroke) / (2 * rows);
            double candidateR = Math.min(candidateRHoriz, candidateRVert);
            candidateR = Math.min(candidateR, maxRadius);
            if (candidateR >= minRadius && candidateR > bestCandidateRadius) {
                bestCandidateRadius = candidateR;
                bestRows = rows;
                bestCols = cols;
            }
        }

        if (bestCandidateRadius < minRadius) {
            System.out.println("Calculated optimal pip radius is less than minimum radius. No pips created.");
            return;
        }

        // --- 3. Place pips using the chosen grid ---
        // With grid top-left anchored at (rectX, rectY), the pip centers are computed
        // so that:
        // centerX = rectX + (bestCandidateRadius + stroke) +
        // col*(2*bestCandidateRadius+3*stroke)
        // centerY = rectY + (bestCandidateRadius + stroke) +
        // row*(2*bestCandidateRadius+3*stroke)
        // This guarantees that the top-left pip is flush with the rectangle's top-left
        // border:
        // (centerX - (r+stroke)) = rectX and similarly for y.
        SVGElement target = (SVGElement) svgRect.getParentNode();
        int pipCountPlaced = 0;
        for (int row = 0; row < bestRows && pipCountPlaced < pipsCount; row++) {
            for (int col = 0; col < bestCols && pipCountPlaced < pipsCount; col++) {
                double x = rectX + (bestCandidateRadius + stroke) + col * (2 * bestCandidateRadius + 3 * stroke);
                double y = rectY + (bestCandidateRadius + stroke) + row * (2 * bestCandidateRadius + 3 * stroke);
                Element pip = createPip(x, y, bestCandidateRadius, stroke);
                target.appendChild(pip);
                pipCountPlaced++;
            }
        }
    }

    private void printArmorPips(SVGRectElement svgRect, int pipsCount, boolean useAlternateColumns) {
        if (pipsCount < 1) {
            return;
        }
        if (svgRect == null) {
            return;
        }
        Rectangle2D bbox = getRectBBox(svgRect);
        SVGElement target = (SVGElement) svgRect.getParentNode();
        printArmorPipsRectangle(target, bbox, pipsCount, useAlternateColumns);
    }

    private void printArmorPipsRectangle(SVGElement target, Rectangle2D bbox, int pipsCount,
            boolean useAlternateColumns) {
        double pipRadius = PIP_RADIUS;
        int pipsPerRow = MIN_PIPS_PER_ROW;
        double strokeWidth = PIP_STROKE_WIDTH;
        final double pipResizedRadiusRatio = useAlternateColumns ? 0.6 : 0.4;
        double aspectRatio = bbox.getWidth() / bbox.getHeight();
        // Calculate pipsPerRow, based on aspect ratio
        if (useAlternateColumns) {
            pipsPerRow = (int) Math.ceil(Math.sqrt(pipsCount * (aspectRatio * 0.4)));
        } else {
            pipsPerRow = (int) Math.ceil((pipsCount / aspectRatio) * 0.6);
        }
        final int maxPipsPerRow = (int) ((bbox.getWidth() - PIP_MIN_RADIUS) / (PIP_MIN_RADIUS + strokeWidth * 2));
        pipsPerRow = Math.min(pipsPerRow, maxPipsPerRow);
        pipsPerRow = Math.max(pipsPerRow, MIN_PIPS_PER_ROW);
        // Then derive rows from pipsPerRow
        int rows = (int) Math.ceil((double) pipsCount / pipsPerRow);

        // Calculate pip radius to fit within available space
        double heightPerPip = bbox.getHeight() / rows;
        double widthPerPip = bbox.getWidth() / pipsPerRow;
        pipRadius = Math.min(PIP_RADIUS, Math.min(heightPerPip, widthPerPip) * pipResizedRadiusRatio);
        // Adjust stroke width proportionally if radius gets small
        if (pipRadius < (strokeWidth * 3)) {
            strokeWidth = Math.max(PIP_STROKE_WIDTH / 3, (pipRadius / 3));
        }
        // Calculate vertical spacing to distribute pips more evenly
        double spaceBetweenPipsY = (bbox.getHeight() - (useAlternateColumns ? pipRadius : 0)) / (rows + 0.5);

        // Limit maximum Y spacing
        if (spaceBetweenPipsY > ((pipRadius + (strokeWidth * 2)) * 2)) {
            spaceBetweenPipsY = ((pipRadius + (strokeWidth * 2)) * 2);
        }
        double spaceBetweenPipsX = bbox.getWidth() / (pipsPerRow + 0.5);

        int remainingArmor = pipsCount;
        boolean isOdd = false;
        for (int r = 0; r < rows; r++) {
            final int pipsInRow = Math.min(pipsPerRow, remainingArmor);
            final double currY = bbox.getY() + pipRadius + (r * spaceBetweenPipsY);
            double currX = bbox.getX() + pipRadius + ((useAlternateColumns && isOdd) ? (spaceBetweenPipsX / 2) : 0);
            for (int c = 0; c < pipsInRow; c++) {
                Element pip = createPip(currX, currY, pipRadius, strokeWidth);
                target.appendChild(pip);
                remainingArmor--;
                currX += spaceBetweenPipsX;
            }
            isOdd = !isOdd;
        }
    }

    private int getArmorPips() {
        return handheldWeapon.getOArmor(HandheldWeapon.LOC_GUN);
    }

    private int getWeaponsCount() {
        return (int) handheldWeapon.getEquipment().stream()
                .filter(m -> m.getType() instanceof WeaponType)
                .map(m -> m.getType().getShortName())
                .distinct()
                .count();
    }

    private List<Map.Entry<String, Integer>> getAmmoList() {
        List<Map.Entry<String, Integer>> ammoDetails = new ArrayList<>();
        handheldWeapon.getEquipment().stream()
                .filter(m -> m.getType() instanceof AmmoType)
                .forEach(m -> {
                    String ammoName = m.getType().getShortName().replace("Ammo", "").replace("(Clan)", "");
                    int shotsLeft = m.getBaseShotsLeft();
                    boolean found = false;
                    for (Map.Entry<String, Integer> entry : ammoDetails) {
                        if (entry.getKey().equals(ammoName)) {
                            entry.setValue(entry.getValue() + shotsLeft);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ammoDetails.add(new AbstractMap.SimpleEntry<>(ammoName, shotsLeft));
                    }
                });
        return ammoDetails;
    }

    public boolean isLargeLayout() {
        if (getArmorPips() > LARGE_LAYOUT_THRESHOLD_ARMOR_PIPS) {
            return true;
        }
        if (getWeaponsCount() > LARGE_LAYOUT_THRESHOLD_WEAPON_TYPE_COUNT) {
            return true;
        }
        List<Map.Entry<String, Integer>> ammoList = getAmmoList();
        if (ammoList.size() > LARGE_LAYOUT_THRESHOLD_AMMO_TYPE_COUNT) {
            return true;
        }
        int ammoCount = ammoList.stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        if (ammoList.size() > 1 && ammoCount > LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT2) {
            return true;
        }
        if (ammoCount > LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT1) {
            return true;
        }
        return false;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (isLargeLayout()) {
            return "handheld_weapon_large.svg";
        }
        return "handheld_weapon_standard.svg";
    }

    @Override
    public Entity getEntity() {
        return handheldWeapon;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        final String entityName = CConfig.getMekNameArrangement().printChassis(getEntity())
                + (StringUtility.isNullOrBlank(getEntity().getModel()) ? "" : " " + getEntity().getModel());
        setTextField(TYPE, entityName + " (" + formatWeight(handheldWeapon.getWeight()) + ")");
    }

    @Override
    protected void drawArmor() {
        super.drawArmor();
        String armorName = EquipmentType.getArmorTypeName(handheldWeapon.getArmorType(HandheldWeapon.LOC_GUN));
        EquipmentType armor = EquipmentType.get(armorName);
        if (armor != null) {
            setTextField(ARMOR_TYPE, armor.getShortName(), true);
        }
    }

    @Override
    void writeArmorStructureTextFields() {
        setTextField(TEXT_ARMOR + handheldWeapon.getLocationAbbr(HandheldWeapon.LOC_GUN),
                handheldWeapon.getOArmor(HandheldWeapon.LOC_GUN));
    }

    @Override
    protected void drawArmorStructurePips() {
        final String idArmor = ARMOR_PIPS + handheldWeapon.getLocationAbbr(HandheldWeapon.LOC_GUN);
        Element elementArmor = getSVGDocument().getElementById(idArmor);
        if (elementArmor instanceof SVGRectElement) {
            final int armorPips = getArmorPips();
            if (armorPips <= MAX_ARMOR_PIPS) {
                printArmorPips((SVGRectElement) elementArmor, armorPips, true);
            }
        }
        final String idAmmo = AMMO_PIPS + handheldWeapon.getLocationAbbr(HandheldWeapon.LOC_GUN);
        Element elementAmmo = getSVGDocument().getElementById(idAmmo);
        if (elementAmmo instanceof SVGRectElement) {
            List<Map.Entry<String, Integer>> ammoDetails = new ArrayList<>();
            handheldWeapon.getEquipment().stream()
                    .filter(m -> m.getType() instanceof AmmoType)
                    .forEach(m -> {
                        String ammoName = m.getType().getShortName().replace("Ammo", "").replace("(Clan)", "");
                        int shotsLeft = m.getBaseShotsLeft();
                        boolean found = false;
                        System.out.println(ammoName);
                        for (Map.Entry<String, Integer> entry : ammoDetails) {
                            if (entry.getKey().equals(ammoName)) {
                                entry.setValue(entry.getValue() + shotsLeft);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            ammoDetails.add(new AbstractMap.SimpleEntry<>(ammoName, shotsLeft));
                        }
                    });
            int ammoCount = ammoDetails.stream()
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            if (ammoDetails.size() > 0 && ammoCount <= MAX_AMMO_PIPS) {
                if (ammoDetails.size() == 1) {
                    Rectangle2D bbox = getRectBBox((SVGRectElement) elementAmmo);
                    SVGElement target = (SVGElement) elementAmmo.getParentNode();
                    layoutPips(target, bbox, ammoCount);
                } else {
                    Rectangle2D bbox = getRectBBox((SVGRectElement) elementAmmo);
                    System.out.println("Full bounding box: " + bbox);
                    SVGElement target = (SVGElement) elementAmmo.getParentNode();
                    Element ammoMainLabel = getSVGDocument().getElementById("ammoLabel");
                    if (ammoMainLabel != null) {
                        ammoMainLabel.setTextContent(ammoDetails.get(0).getKey() + ':');
                    }
                    double totalHeight = bbox.getHeight();
                    double textHeight = 8.4; // Approximate text height for labels
                    double totalTextSpace = textHeight * (ammoDetails.size() - 1);
                    double availablePipSpace = totalHeight - totalTextSpace;
                    System.out.println("Total Height: " + totalHeight);
                    System.out.println("Available pip space: " + availablePipSpace);
                    double currentY = bbox.getY();
                    // First entry (already has label)
                    double firstProportion = Math.max(MIN_PIPS_PER_ROW * 2, ammoDetails.get(0).getValue())
                            / (double) ammoCount;
                    double firstPipHeight = availablePipSpace * firstProportion;

                    // Create bounding box for first entry
                    Rectangle2D firstBBox = new Rectangle2D.Double(
                            bbox.getX(),
                            currentY,
                            bbox.getWidth(),
                            firstPipHeight);
                    System.out.println("First ammo type bounding box: " + firstBBox);

                    // Draw pips for first entry
                    layoutPips(target, firstBBox, ammoDetails.get(0).getValue());
                    currentY += firstPipHeight;

                    // Process remaining entries
                    for (int j = 1; j < ammoDetails.size(); j++) {
                        // Create text label for this ammo type
                        Element ammoLabel = (Element) ammoMainLabel.cloneNode(true);
                        ammoLabel.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(21));
                        ammoLabel.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE,
                                String.valueOf(currentY + (textHeight / 1.3)));
                        ammoLabel.setTextContent(ammoDetails.get(j).getKey() + ":");
                        ammoLabel.removeAttribute("id");
                        target.appendChild(ammoLabel);
                        currentY += textHeight;
                        double proportion = Math.max(MIN_PIPS_PER_ROW * 2, ammoDetails.get(j).getValue())
                                / (double) ammoCount;
                        double pipHeight = availablePipSpace * proportion;

                        // Create bounding box for this ammo type's pips
                        Rectangle2D ammoTypeBBox = new Rectangle2D.Double(
                                bbox.getX(),
                                currentY,
                                bbox.getWidth(),
                                pipHeight);
                        System.out.println("Ammo type bounding box: " + ammoTypeBBox);

                        // Draw pips for this ammo type
                        layoutPips(target, ammoTypeBBox, ammoDetails.get(j).getValue());

                        // Update position for next ammo type
                        currentY += pipHeight;
                    }
                }

            }

        }
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

}
