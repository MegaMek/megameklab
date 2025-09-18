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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.geom.Rectangle2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import megamek.codeUtilities.StringUtility;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.HandheldWeapon;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;
import org.w3c.dom.svg.SVGTextElement;

/**
 * @author drake Lays out a record sheet block for a single handheld weapon unit
 */
public class PrintHandheldWeapon extends PrintEntity {

    /** Default radius for a pip */
    public static final double PIP_RADIUS = 3.6;
    /** Default minimum radius for pip */
    public static final double PIP_MIN_RADIUS = 1.6;
    /** Default stroke width for a pip */
    public static final double PIP_STROKE_WIDTH = 0.9;
    /** Default minimum spacing between the outer edges of pips */
    public static final double MIN_PIP_SPACING = 0.3;
    /** In case there are labels, offset the first label (and the whole bbox) */
    public static final double OFFSET_FIRST_LABEL = -3;
    /** Default/Fallback font size for labels */
    public static final double LABEL_DEFAULT_FONT_SIZE = 6;
    /** Minimum margin above an ammo label */
    public static final double MIN_MARGIN_ABOVE_LABEL = 0.2;
    /** Maximum margin above an ammo label */
    public static final double MAX_MARGIN_ABOVE_LABEL = 3;
    /** Default margin below an ammo label */
    public static final double MARGIN_BELOW_LABEL = 0;
    /** Maximum distance between pips relative to pip diameter (Horizontal) */
    public static final double PIP_MAX_DISTANCE_MULTIPLIER_X = 1;
    /** Maximum distance between pips relative to pip diameter (Vertical) */
    public static final double PIP_MAX_DISTANCE_MULTIPLIER_Y = 0.6;
    /** Default minimum amount of pips for a row */
    public static final int MIN_ARMOR_PIPS_PER_ROW = 8;
    /** Maximum amount of pips displayable, after that we leave blank */
    public static final int MAX_ARMOR_PIPS = 360;
    /** Maximum amount of pips displayable, after that we leave blank */
    public static final int MAX_AMMO_PIPS = 400;

    /** Threshold for large layout */
    public static final int LARGE_LAYOUT_THRESHOLD_ARMOR_PIPS = 65;
    public static final int LARGE_LAYOUT_THRESHOLD_WEAPON_TYPE_COUNT = 2;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_TYPE_COUNT = 2;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT1 = 200;
    public static final int LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT2 = 125;

    private final HandheldWeapon handheldWeapon;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param handheldWeapon The handheld weapon to print
     * @param startPage      The print job page number for this sheet
     * @param options        Overrides the global options for which elements are printed
     */
    public PrintHandheldWeapon(HandheldWeapon handheldWeapon, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.handheldWeapon = handheldWeapon;
    }

    /**
     * Simplified drawing routine for the armor pips
     *
     * @param svgRect             The SVG rectangle element to draw the pips in
     * @param pipsCount           The number of pips to draw
     * @param useAlternateColumns true if the pips will be drawn in alternate columns
     */
    private void drawArmorPips(SVGRectElement svgRect, int pipsCount, boolean useAlternateColumns) {
        if (pipsCount < 1) {
            return;
        }
        if (svgRect == null) {
            return;
        }
        Rectangle2D bbox = getRectBBox(svgRect);
        SVGElement target = (SVGElement) svgRect.getParentNode();
        double pipRadius = PIP_RADIUS;
        int pipsPerRow = MIN_ARMOR_PIPS_PER_ROW;
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
        pipsPerRow = Math.max(pipsPerRow, MIN_ARMOR_PIPS_PER_ROW);
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
        double spaceBetweenPipsY = bbox.getHeight() / rows;

        // Limit maximum Y spacing
        if (spaceBetweenPipsY > ((pipRadius + strokeWidth) * 2)) {
            spaceBetweenPipsY = ((pipRadius + strokeWidth) * 2);
        }
        double spaceBetweenPipsX = bbox.getWidth() / pipsPerRow;

        int remainingArmor = pipsCount;
        boolean isOdd = false;
        for (int r = 0; r < rows; r++) {
            final int pipsInRow = Math.min(pipsPerRow, remainingArmor);
            final double currY = bbox.getY() + pipRadius + (r * spaceBetweenPipsY);
            double currX = bbox.getX() + pipRadius + ((useAlternateColumns && isOdd) ? (spaceBetweenPipsX / 2) : 0);
            for (int c = 0; c < pipsInRow; c++) {
                Element pip = createPip(currX - pipRadius, currY - pipRadius, pipRadius, strokeWidth, PipType.CIRCLE,
                      FILL_WHITE, "armor", "GUN", false);
                target.appendChild(pip);
                remainingArmor--;
                currX += spaceBetweenPipsX;
            }
            isOdd = !isOdd;
        }
    }

    /**
     * Gets the number of armor pips for the handheld weapon
     *
     * @return The number of armor pips
     */
    private int getArmorPips() {
        return getEntity().getOArmor(HandheldWeapon.LOC_GUN);
    }

    /**
     * Gets the number of weapon types in the handheld weapon
     *
     * @return The number of weapon types
     */
    private int getWeaponsCount() {
        return (int) getEntity().getEquipment().stream()
              .filter(m -> m.getType() instanceof WeaponType)
              .map(m -> m.getType().getShortName())
              .distinct()
              .count();
    }

    /**
     * Gets the list of ammo types and their counts for the handheld weapon
     *
     * @return A list of ammo types and their counts
     */
    private List<Map.Entry<String, Integer>> getAmmoList() {
        List<Map.Entry<String, Integer>> pipsList = new ArrayList<>();
        getEntity().getEquipment().stream()
              .filter(m -> m.getType() instanceof AmmoType)
              .forEach(m -> {
                  String ammoName = m.getType().getShortName().replace("Ammo", "").replace("(Clan)", "");
                  int shotsLeft = m.getBaseShotsLeft();
                  boolean found = false;
                  for (Map.Entry<String, Integer> entry : pipsList) {
                      if (entry.getKey().equals(ammoName)) {
                          entry.setValue(entry.getValue() + shotsLeft);
                          found = true;
                          break;
                      }
                  }
                  if (!found) {
                      pipsList.add(new AbstractMap.SimpleEntry<>(ammoName, shotsLeft));
                  }
              });

        var oneShotWeapons = new HashMap<String, Integer>();
        for (Iterator<WeaponMounted> it = getEntity().getWeapons(); it.hasNext(); ) {
            WeaponType weapon = it.next().getType();
            var name = weapon.getName();
            if (weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                if (oneShotWeapons.containsKey(name)) {
                    oneShotWeapons.put(name, oneShotWeapons.get(name) + 1);
                } else {
                    oneShotWeapons.put(name, 1);
                }
            }

        }

        pipsList.addAll(oneShotWeapons.entrySet());

        return pipsList;
    }

    /**
     * Checks if the layout is large based on the number of pips and ammo types
     *
     * @return true if the layout is large, false otherwise
     */
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
        return ammoCount > LARGE_LAYOUT_THRESHOLD_AMMO_AMOUNT1;
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
        final String entityName = UnitUtil.getPrintName(getEntity());
        setTextField(TYPE, entityName + " (" + formatWeight(getEntity().getWeight()) + ")");

    }

    @Override
    protected void drawArmor() {
        String armorName = EquipmentType.getArmorTypeName(getEntity().getArmorType(HandheldWeapon.LOC_GUN));
        EquipmentType armor = EquipmentType.get(armorName);
        if (armor != null) {
            setTextField(ARMOR_TYPE, armor.getShortName(), true);
        }
        setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(HandheldWeapon.LOC_GUN),
              getEntity().getOArmor(HandheldWeapon.LOC_GUN));
        drawArmorStructurePips();
        drawAmmo();
    }

    /**
     * Draws the ammo pips for the handheld weapon. This method retrieves the ammo types and their counts, calculates
     * the optimal pip radius and decides if they should be displayed or not
     */
    private void drawAmmo() {
        final String idAmmo = AMMO_PIPS + getEntity().getLocationAbbr(HandheldWeapon.LOC_GUN);
        final Element elementAmmo = getSVGDocument().getElementById(idAmmo);
        if (elementAmmo instanceof SVGRectElement svgElement) {
            List<Map.Entry<String, Integer>> pipsList = getAmmoList();
            // Only draw if we have ammo
            if (!pipsList.isEmpty()) {
                final int totalAmmo = pipsList.stream().mapToInt(Map.Entry::getValue).sum();
                if (totalAmmo > MAX_AMMO_PIPS) {
                    // If total pips exceed max, don't draw anything
                    return;
                }
                // Check if we can fit the pips else we don't draw anything
                final boolean canFit = checkRadiusFit(PIP_MIN_RADIUS,
                      getRectBBox(svgElement),
                      pipsList,
                      pipsList.size() > 1,
                      LABEL_DEFAULT_FONT_SIZE);
                if (canFit) {
                    drawPipsFromList(svgElement, pipsList, false);
                }
            }
        }
    }

    private void drawPipsFromList(SVGRectElement svgElement, List<Map.Entry<String, Integer>> pipsList,
          boolean alternate) {
        drawPipsFromList(svgElement, pipsList, alternate, 0);
    }

    /**
     * Draws pips within the specified SVG rectangle element. Handles standard and alternating row layouts and optimizes
     * pip size.
     *
     * @param svgElement The SVGRectElement defining the bounding box.
     * @param pipsList   List of pips with ammo type and count.
     * @param alternate  True to use alternating row layout, false for standard grid.
     */
    private void drawPipsFromList(SVGRectElement svgElement, List<Map.Entry<String, Integer>> pipsList,
          boolean alternate, int minColumns) {
        if (pipsList == null || pipsList.isEmpty()) {
            return;
        }

        final double TOLERANCE = 1e-6;
        Rectangle2D bbox = getRectBBox(svgElement);
        final SVGElement target = (SVGElement) svgElement.getParentNode();

        // Label Setup
        final boolean needsLabels = pipsList.size() > 1;
        double actualLabelHeight = 0;
        SVGTextElement labelTemplate = null;
        Element headerLabel = getSVGDocument().getElementById("ammoLabel");
        if (needsLabels) {
            headerLabel.setTextContent("Ammo:");

            bbox = new Rectangle2D.Double(
                  bbox.getX(),
                  bbox.getY() + OFFSET_FIRST_LABEL,
                  bbox.getWidth(),
                  bbox.getHeight() - OFFSET_FIRST_LABEL
            );
            Element existingLabel = getSVGDocument().getElementById("ammoEntryLabel");
            if (existingLabel instanceof SVGTextElement) {
                labelTemplate = (SVGTextElement) existingLabel;
                double fontSize = LABEL_DEFAULT_FONT_SIZE;
                try {
                    String fontSizeStr = labelTemplate.getAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE);
                    if (!StringUtility.isNullOrBlank(fontSizeStr)) {fontSize = Double.parseDouble(fontSizeStr);}
                } catch (Exception e) {
                    // Could not read font size from 'ammoEntryLabel'!!
                }
                fontSize = Math.max(4, fontSize);
                actualLabelHeight = fontSize;
            } else {
                // Could not find label template 'ammoEntryLabel'. Using default text settings and size
                actualLabelHeight = LABEL_DEFAULT_FONT_SIZE;
            }
        } else {
            headerLabel.setTextContent("Ammo (%d):".formatted(pipsList.get(0).getValue()));
        }

        // Find Optimal Pip Radius
        double optimalRadius = findOptimalPipRadius(bbox, pipsList, needsLabels, actualLabelHeight);
        if (optimalRadius < PIP_MIN_RADIUS - TOLERANCE) { // Use tolerance check
            // Cannot fit ammo pips, skip drawing
            return;
        }

        // Layout Calculations
        double pipDiameter = 2 * optimalRadius;
        double pipStroke = PIP_STROKE_WIDTH;
        // Adjust stroke dynamically if radius is very small
        if (optimalRadius < (pipStroke * 2.5)) { // Slightly larger threshold than armor
            pipStroke = Math.max(PIP_STROKE_WIDTH / 3, optimalRadius / 2.5);
        }
        double layoutDiameter = pipDiameter + pipStroke;
        double maxGapX = Math.max(MIN_PIP_SPACING, pipDiameter * PIP_MAX_DISTANCE_MULTIPLIER_X);
        double maxGapY = Math.max(MIN_PIP_SPACING, pipDiameter * PIP_MAX_DISTANCE_MULTIPLIER_Y);

        // Determine FINAL Column Count (colsToUse)
        int maxColsPossible = (int) Math
              .floor((bbox.getWidth() + MIN_PIP_SPACING - TOLERANCE) / (layoutDiameter + MIN_PIP_SPACING));
        maxColsPossible = Math.max(1, maxColsPossible);
        int minColsRequired;
        double denominatorX = layoutDiameter + maxGapX;
        if (minColumns > 0) {
            minColsRequired = minColumns;
        } else if (denominatorX < TOLERANCE) {
            minColsRequired = Integer.MAX_VALUE;
        } else {
            minColsRequired = (int) Math.ceil((bbox.getWidth() + maxGapX - TOLERANCE) / denominatorX);
        }
        minColsRequired = Math.max(1, minColsRequired);

        int colsToUse = -1;
        boolean firstBlockProcessedHeightCheck;
        // loop to find colsToUse
        for (int colsToCheck = minColsRequired; colsToCheck <= maxColsPossible; colsToCheck++) {
            double totalRequiredHeight = 0;
            firstBlockProcessedHeightCheck = false;
            boolean fits = true;
            for (Map.Entry<String, Integer> entry : pipsList) {
                int pipsCount = entry.getValue();
                if (pipsCount <= 0) {
                    continue;
                }
                int rows = (int) Math.ceil((double) pipsCount / colsToCheck);
                if (needsLabels) {
                    if (firstBlockProcessedHeightCheck) {
                        totalRequiredHeight += MIN_MARGIN_ABOVE_LABEL;
                    }
                    totalRequiredHeight += actualLabelHeight;
                    totalRequiredHeight += MARGIN_BELOW_LABEL;
                } else {
                    if (firstBlockProcessedHeightCheck) {
                        totalRequiredHeight += MIN_PIP_SPACING;
                    }
                }
                int gapsInBlock = Math.max(0, rows - 1);
                totalRequiredHeight += rows * layoutDiameter;
                totalRequiredHeight += gapsInBlock * MIN_PIP_SPACING;
                firstBlockProcessedHeightCheck = true;
                if (totalRequiredHeight > bbox.getHeight() + TOLERANCE) {
                    fits = false;
                    break;
                }
            }
            if (fits) {
                colsToUse = colsToCheck;
                break;
            }
        }
        if (colsToUse == -1) {
            colsToUse = maxColsPossible; // Fallback
        }

        // Calculate Final Horizontal Spacing and Centering
        double spacingX;
        double effectiveWidth = bbox.getWidth(); // Start with full width for calculation base

        if (colsToUse <= 1) {
            spacingX = 0;
        } else {
            // Calculate initial spacing based on full width to estimate the shift
            double initialSpacingX = (bbox.getWidth() - colsToUse * layoutDiameter) / (colsToUse - 1);
            initialSpacingX = Math.max(MIN_PIP_SPACING, initialSpacingX);
            initialSpacingX = Math.min(initialSpacingX, maxGapX);

            if (alternate) {
                // If alternate mode, calculate the horizontal shift
                double horizontalShiftForWidthCalc = (layoutDiameter + initialSpacingX) / 2;
                // Reduce the effective width available for layout
                effectiveWidth = bbox.getWidth() - horizontalShiftForWidthCalc;
                // Ensure effectiveWidth is not less than minimum required width
                double minRequiredWidth = colsToUse * layoutDiameter + Math.max(0, colsToUse - 1) * MIN_PIP_SPACING;
                if (effectiveWidth < minRequiredWidth - TOLERANCE) {
                    effectiveWidth = minRequiredWidth;
                }
            }

            // Calculate final spacing based on the effective width
            spacingX = (effectiveWidth - colsToUse * layoutDiameter) / (colsToUse - 1);
            spacingX = Math.max(MIN_PIP_SPACING, spacingX); // Apply minimum spacing
            spacingX = Math.min(spacingX, maxGapX); // Apply maximum spacing gap
        }
        // Note: spacingX is the gap between the *stroke edges* implicitly,
        // the distance between *centers* is layoutDiameter + spacingX !!!!!

        // Recalculate the actual width the grid will occupy using the final clamped spacingX
        double gridWidth = colsToUse * layoutDiameter + Math.max(0, colsToUse - 1) * spacingX;
        // Center the potentially narrower grid within the original bbox width
        double horizontalMargin = Math.max(0, (bbox.getWidth() - gridWidth) / 2);
        // Base X for the center of the first pip in an un-offset row
        double firstPipCenterX_Base = bbox.getX() + horizontalMargin + optimalRadius + (pipStroke / 2);

        // Calculate Vertical Spacing
        List<Integer> rowsPerAmmo = new ArrayList<>();
        double totalMinVerticalHeight = 0;
        int expandableGaps = 0;
        boolean firstBlockDrawnVertCalc = false;
        for (Map.Entry<String, Integer> stringIntegerEntry : pipsList) {
            int pipsCount = stringIntegerEntry.getValue();
            if (pipsCount <= 0) {
                rowsPerAmmo.add(0);
                continue;
            }
            int rows = (int) Math.ceil((double) pipsCount / colsToUse);
            rowsPerAmmo.add(rows);
            int gapsInBlock = Math.max(0, rows - 1);

            if (needsLabels) {
                if (firstBlockDrawnVertCalc) {
                    totalMinVerticalHeight += MIN_MARGIN_ABOVE_LABEL;
                    expandableGaps++; // Gap between blocks (above label) is expandable
                }
                totalMinVerticalHeight += actualLabelHeight; // Label height
                totalMinVerticalHeight += MARGIN_BELOW_LABEL; // Fixed margin below label (but we expand it later with calculations)
            } else {
                // If no labels, gap between blocks is just pip spacing
                if (firstBlockDrawnVertCalc) {
                    totalMinVerticalHeight += MIN_PIP_SPACING;
                    expandableGaps++; // Gap between blocks (no labels) is expandable
                }
            }
            totalMinVerticalHeight += rows * layoutDiameter; // Height of pips themselves
            totalMinVerticalHeight += gapsInBlock * MIN_PIP_SPACING; // Min height of gaps within block
            expandableGaps += gapsInBlock; // Gaps within block are expandable

            firstBlockDrawnVertCalc = true;
        }
        // Fill remaining rowsPerAmmo
        while (rowsPerAmmo.size() < pipsList.size()) {rowsPerAmmo.add(0);}

        // Calculate extra spacing to distribute vertically
        double remainingHeight = bbox.getHeight() - totalMinVerticalHeight;
        double additionalSpacingY = (expandableGaps > 0 && remainingHeight > TOLERANCE) ?
              (remainingHeight / expandableGaps) :
              0;
        // Final vertical spacing between pip centers (includes layoutDiameter + calculated gap)
        double spacingY = MIN_PIP_SPACING + additionalSpacingY;
        spacingY = Math.max(MIN_PIP_SPACING, spacingY);
        spacingY = Math.min(spacingY, maxGapY);

        // Calculate effective margins using the distributed spacing
        double effectiveMarginAbove = MIN_MARGIN_ABOVE_LABEL + additionalSpacingY;
        effectiveMarginAbove = Math.max(MIN_MARGIN_ABOVE_LABEL, effectiveMarginAbove);
        effectiveMarginAbove = Math.min(effectiveMarginAbove, MAX_MARGIN_ABOVE_LABEL);

        double effectiveMarginBelow = MARGIN_BELOW_LABEL + additionalSpacingY * 0.2;

        // Finally we start drawing
        double currentY = bbox.getY(); // We start drawing from the top
        boolean firstBlockDrawn = false;

        for (int i = 0; i < pipsList.size(); i++) {
            Map.Entry<String, Integer> entry = pipsList.get(i);
            String ammoName = entry.getKey();
            int pipsCount = entry.getValue();
            int rowsForThisAmmo = rowsPerAmmo.get(i);

            // Skip drawing if no pips or rows allocated for this entry
            if (pipsCount <= 0 || rowsForThisAmmo <= 0) {
                continue;
            }

            // Add vertical spacing BEFORE drawing the block (label or pips)
            if (firstBlockDrawn) {
                // Use expandable margin if labels exist, otherwise use expandable pip gap
                currentY += needsLabels ? effectiveMarginAbove : spacingY;
            }

            // Draw label
            double blockContentStartY = currentY; // Y position where label/pips start for this block
            if (needsLabels) {
                double labelBaselineY = blockContentStartY + actualLabelHeight * 0.8;
                double labelStartX = firstPipCenterX_Base - optimalRadius - (pipStroke / 2);
                labelStartX = Math.max(bbox.getX(), labelStartX);
                Element label = createLabel(labelTemplate, "%s (%d):".formatted(ammoName, pipsCount), labelStartX,
                      labelBaselineY);
                target.appendChild(label);

                // Advance currentY past the label and its bottom margin
                currentY += actualLabelHeight + effectiveMarginBelow;
            }

            // Draw pips for this block
            double pipBlockStartY = currentY; // Y position where the pips start for this block
            int pipsDrawnInBlock = 0;

            for (int r = 0; r < rowsForThisAmmo; r++) { // r is 0-based row index within this ammo block
                // Calculate the center Y coordinate for pips in this row
                double pipCenterY = pipBlockStartY + optimalRadius + (pipStroke / 2) // Center of first row
                      + r * (layoutDiameter + spacingY); // Offset for subsequent rows

                double rowStartX;
                double horizontalShift; // for alternate mode
                boolean isEvenRow = alternate && (r % 2 == 1);

                if (isEvenRow && colsToUse > 1) {
                    // Calculate horizontal offset for even rows using the FINAL calculated spacingX
                    horizontalShift = (layoutDiameter + spacingX) / 2;
                    rowStartX = firstPipCenterX_Base + horizontalShift;
                } else {
                    // Odd rows or non-alternate mode start at the base X position
                    rowStartX = firstPipCenterX_Base;
                }

                // Determine the number of pips to draw in this specific row
                int pipsRemainingInBlock = pipsCount - pipsDrawnInBlock;
                int numPipsThisRow = Math.min(colsToUse, pipsRemainingInBlock);
                numPipsThisRow = Math.max(0, numPipsThisRow); // Ensure non-negative

                // Draw the pips for this row
                for (int c = 0; c < numPipsThisRow; c++) {
                    double pipCenterX = rowStartX + c * (layoutDiameter + spacingX);
                    Element pip = createPip(pipCenterX - optimalRadius, pipCenterY - optimalRadius, optimalRadius,
                          pipStroke, PipType.CIRCLE, FILL_WHITE, "ammo", ammoName, false);
                    target.appendChild(pip);
                }
                pipsDrawnInBlock += numPipsThisRow;
            }
            // Update the current Y position for the next block
            currentY = pipBlockStartY + rowsForThisAmmo * layoutDiameter + Math.max(0, rowsForThisAmmo - 1) * spacingY;

            firstBlockDrawn = true;
        }
    }

    /**
     * Finds the largest possible radius for pips that allows all ammo types (and labels) to fit within the bounding
     * box, respecting size and spacing constraints. Uses a binary search approach.
     *
     * @param bbox        Bounding box for drawing.
     * @param pipsList    List of ammo types and counts.
     * @param needsLabels Whether labels are required.
     * @param labelHeight Estimated height of one label row including margins.
     *
     * @return The optimal pip radius, or 0 if no fit is found.
     */
    private double findOptimalPipRadius(Rectangle2D bbox, List<Map.Entry<String, Integer>> pipsList,
          boolean needsLabels, double labelHeight) {

        double low = PIP_MIN_RADIUS;
        // Start high search bound slightly larger than default radius, limited by box dimensions
        double high = Math.min(PIP_RADIUS * 1.2, Math.min(bbox.getWidth(), bbox.getHeight()) / 2);
        double bestRadius = 0;
        int iterations = 10;
        final double SEARCH_TOLERANCE = 1e-4; // Tolerance for binary search convergence

        // Check if even the minimum radius fits before starting search
        if (!checkRadiusFit(PIP_MIN_RADIUS, bbox, pipsList, needsLabels, labelHeight)) {
            return 0; // Cannot fit even the smallest pips
        }

        // Binary search for the best radius
        for (int i = 0; i < iterations; i++) {
            double mid = low + (high - low) / 2;
            if (mid < PIP_MIN_RADIUS) {mid = PIP_MIN_RADIUS;}

            if (checkRadiusFit(mid, bbox, pipsList, needsLabels, labelHeight)) {
                // This radius fits, it's a potential candidate. Try larger.
                bestRadius = mid;
                low = mid;
            } else {
                // This radius doesn't fit, need smaller.
                high = mid;
            }
            // Break early if the search range is very small
            if (high - low < SEARCH_TOLERANCE) {break;}
        }

        // bestRadius now holds the largest value tested that fit.
        if (bestRadius >= PIP_MIN_RADIUS - SEARCH_TOLERANCE) {
            return Math.min(PIP_RADIUS, Math.max(PIP_MIN_RADIUS, bestRadius));
        } else {
            return 0;
        }
    }

    /**
     * Checks if pips of a given radius can fit all ammo types (and labels) within the bounding box. Iterates through
     * valid column counts to see if *any* configuration allows vertical fit using minimum spacing.
     */
    private boolean checkRadiusFit(double radiusToTest, Rectangle2D bbox, List<Map.Entry<String, Integer>> pipsList,
          boolean needsLabels, double labelHeight) {

        final double TOLERANCE = 1e-6; // Tolerance for floating point comparisons
        if (radiusToTest < PIP_MIN_RADIUS - TOLERANCE) {
            return false; // Definitely too small
        }

        double pipDiameter = 2 * radiusToTest;
        double layoutDiameter = pipDiameter + PIP_STROKE_WIDTH;
        double boxWidth = bbox.getWidth();
        double boxHeight = bbox.getHeight();

        // Check if a single pip even fits horizontally/vertically
        if (layoutDiameter > boxWidth + TOLERANCE || layoutDiameter > boxHeight + TOLERANCE) {
            return false;
        }

        // Determine the range of possible column counts for this radius
        int maxColsPossible = (int) Math
              .floor((boxWidth + MIN_PIP_SPACING + TOLERANCE) / (layoutDiameter + MIN_PIP_SPACING));
        maxColsPossible = Math.max(1, maxColsPossible); // Must be at least 1 column

        for (int colsToCheck = 1; colsToCheck <= maxColsPossible; colsToCheck++) {
            double totalRequiredHeight = 0;
            boolean firstBlockProcessed = false;
            boolean possibleWithThisColCount = true;

            // Calculate total minimum vertical height required for 'colsToCheck' columns
            for (Map.Entry<String, Integer> entry : pipsList) {
                int pipsCount = entry.getValue();
                if (pipsCount <= 0) {
                    continue;
                }

                // Calculate rows needed for this ammo type with 'colsToCheck' columns
                int rows = (int) Math.ceil((double) pipsCount / colsToCheck);
                if (rows == 0) {
                    rows = 1;
                }

                // Add height for label and margins if needed
                if (needsLabels) {
                    if (firstBlockProcessed) {
                        totalRequiredHeight += MIN_MARGIN_ABOVE_LABEL;
                    }
                    totalRequiredHeight += labelHeight;
                    totalRequiredHeight += MARGIN_BELOW_LABEL;
                } else {
                    // If no labels, add minimum pip spacing between blocks
                    if (firstBlockProcessed) {
                        totalRequiredHeight += MIN_PIP_SPACING;
                    }
                }

                // Add height for the pips themselves in this block
                int gapsInBlock = Math.max(0, rows - 1);
                totalRequiredHeight += rows * layoutDiameter; // Height of pip rows
                totalRequiredHeight += gapsInBlock * MIN_PIP_SPACING; // Minimum height of gaps within block

                firstBlockProcessed = true;

                // Check if the required height already exceeds the available box height
                if (totalRequiredHeight > boxHeight + TOLERANCE) {
                    possibleWithThisColCount = false; // This column count requires too much height
                    break;
                }
            }

            // If after checking all ammo types, the total height fits for this column count
            if (possibleWithThisColCount) {
                return true; // Found a valid column count that fits
            }
        }

        return false;
    }

    /**
     * Creates an SVG text element for an ammo label. Attempts to clone style from a template if provided.
     *
     * @param template Optional SVGTextElement to clone style from.
     * @param text     The text content for the label.
     * @param x        X coordinate for the label (usually text-anchor start).
     * @param y        Y coordinate for the label (baseline).
     *
     * @return SVG element representing the text label.
     */
    private Element createLabel(SVGTextElement template, String text, double x, double y) {
        SVGTextElement label;
        boolean cloned = false;
        if (template != null) {
            try {
                label = (SVGTextElement) template.cloneNode(false);

                // Remove attributes that should be unique or might cause issues
                label.removeAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE);
                label.removeAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE);
                label.removeAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE);
                label.removeAttributeNS(null, SVGConstants.CSS_VISIBILITY_PROPERTY);
                label.removeAttributeNS(null, SVGConstants.CSS_DISPLAY_PROPERTY);

                // Remove text content
                while (label.hasChildNodes()) {
                    label.removeChild(label.getFirstChild());
                }
                cloned = true;
            } catch (Exception e) {
                // Cloning failed, create a new label instead
                label = (SVGTextElement) getSVGDocument().createElementNS(SVGConstants.SVG_NAMESPACE_URI,
                      SVGConstants.SVG_TEXT_TAG);
            }
        } else {
            // Create a new text element with default attributes if no template
            label = (SVGTextElement) getSVGDocument().createElementNS(SVGConstants.SVG_NAMESPACE_URI,
                  SVGConstants.SVG_TEXT_TAG);
        }

        // Apply default styles if missing
        if (!cloned || !label.hasAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE)) {
            label.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, "Eurostile");
        }
        if (!cloned || !label.hasAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE)) {
            label.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, String.valueOf(LABEL_DEFAULT_FONT_SIZE));
        }
        if (!cloned || !label.hasAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE)) {
            label.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, FILL_BLACK);
        }
        if (!label.hasAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE)) {
            label.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, SVGConstants.SVG_START_VALUE);
        }

        label.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(x));
        label.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, Double.toString(y));

        Text textNode = getSVGDocument().createTextNode(text);
        label.appendChild(textNode);

        return label;
    }

    @Override
    protected void drawStructure() {
        // Not used
    }

    @Override
    protected void drawArmorStructurePips() {
        // Draws the armor pips using the specific armor drawing logic
        final String idArmor = ARMOR_PIPS + getEntity().getLocationAbbr(HandheldWeapon.LOC_GUN);
        Element elementArmor = getSVGDocument().getElementById(idArmor);
        if (elementArmor instanceof SVGRectElement svgRect) {
            final int armorPips = getArmorPips();
            if (armorPips <= MAX_ARMOR_PIPS && armorPips > 0) {
                // Use the dedicated armor pip drawing function which handles its own layout
                drawArmorPips(svgRect, armorPips, true); // Use alternate for armor
            }
        }
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return false;
    }

}
