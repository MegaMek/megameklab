/*
 * MegaMekLab - Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
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

import megamek.client.generator.RandomNameGenerator;
import megamek.client.ui.swing.util.FluffImageHelper;
import megamek.codeUtilities.StringUtility;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.eras.Era;
import megamek.common.eras.Eras;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.PilotOptions;
import megamek.common.options.Quirks;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import org.apache.batik.anim.dom.SVGGraphicsElement;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRect;
import org.w3c.dom.svg.SVGRectElement;
import org.w3c.dom.svg.SVGTextContentElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import static megamek.common.EquipmentType.*;

/**
 * Base class for printing Entity record sheets
 * 
 * @author Neoancient
 */
public abstract class PrintEntity extends PrintRecordSheet {
    
    /**
     * Creates an SVG object for the record sheet
     * 
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed 
     */
    protected PrintEntity(int startPage, RecordSheetOptions options) {
        super(startPage, options);
    }

    public abstract Entity getEntity();

    @Override
    public List<String> getBookmarkNames() {
        return Collections.singletonList(entityName());
    }
    
    /**
     * When printing from a MUL the pilot data is filled in unless the option has been disabled. This
     * allows a series of blank record sheets to be generated without including the generated pilot data.
     * If the crew name is "unnamed" then we are printing directly from MML or file/cache and the
     * pilot data should not be filled in.
     * 
     * @return Whether the pilot data should be filled in.
     */
    protected boolean showPilotInfo() {
        return options.showPilotData()
                && !getEntity().getCrew().getName().startsWith(RandomNameGenerator.UNNAMED);
    }

    /**
     * @return Whether the total weapon heat and dissipation should be shown on the record sheet
     */
    protected boolean showHeatProfile() {
        return getEntity().tracksHeat() && options.showHeatProfile();
    }

    /**
     * @return A String showing the total weapon heat and dissipation.
     */
    protected String heatProfileText() {
        int heat = getEntity().getEquipment().stream().mapToInt(m -> m.getType().getHeat()).sum();
        return "Total Heat (Dissipation): " + heat + " (" + getEntity().getHeatCapacity() + ")";
    }

    /**
     * Space for misc equipment such as cargo space and SV chassis mods.
     *
     * @return A list of misc equipment, or an empty String if none
     */
    public String formatFeatures() {
        return "";
    }

    /**
     * Space for various miscellaneous notes about the unit, such as restrictions.
     *
     * @return A list of misc notes, or an empty String if none
     */
    public String formatMiscNotes() {
        return "";
    }

    /**
     * Builds the string to display for the quirks block. Returns an empty string if quirks are
     * disabled (or if the unit has no quirks).
     * 
     * @return The text to display for the unit's quirks.
     */
    public String formatQuirks() {
        if (options.showQuirks()) {
            StringJoiner sj = new StringJoiner(", ");
            Quirks quirks = getEntity().getQuirks();
            for (Enumeration<IOptionGroup> optionGroups = quirks.getGroups(); optionGroups.hasMoreElements();) {
                IOptionGroup optiongroup = optionGroups.nextElement();
                if (quirks.count(optiongroup.getKey()) > 0) {
                    for (Enumeration<IOption> options = optiongroup.getOptions(); options.hasMoreElements();) {
                        IOption option = options.nextElement();
                        if (option != null && option.booleanValue()) {
                            sj.add(option.getDisplayableNameWithValue());
                        }
                    }
                }
            }
            return sj.toString();
        } else {
            return "";
        }
    }

    /**
     * Fuel block used by aerospace unit. Fuel for other units, such as non-aerospace support vehicles,
     * is for strategic fuel use and not relevant for the record sheet.
     *
     * @return A string containing the amount of fuel and cost per thrust point for aerospace units,
     *         otherwise an empty string.
     */
    public String formatTacticalFuel() {
        return "";
    }

    /**
     * Converts a weight to a String, either in kg or tons as appropriate to the Entity,
     * labeled with the measurement unit.
     * @param weight The weight in tons
     * @return       The formatted weight with units
     */
    String formatWeight(double weight) {
        if ((getEntity() instanceof BattleArmor)
                || (getEntity() instanceof Protomech)
                || getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            return DecimalFormat.getInstance().format(weight * 1000) + " kg";
        } else {
            return DecimalFormat.getInstance().format(weight)
                    + ((weight == 1) ? " ton)" : " tons");
        }
    }
    
    @Override
    protected void processImage(int pageNum, PageFormat pageFormat) {
        super.processImage(pageNum, pageFormat);
        writeTextFields();
        drawArmor();
        drawStructure();
        Element eqRect = getSVGDocument().getElementById(INVENTORY);
        if (eqRect instanceof SVGRectElement) {
            writeEquipment((SVGRectElement) eqRect);
        }
        if (options.showEraIcon()) {
            drawEraIcon();
        }
        drawFluffImage();
        if ((pageNum == 0) && includeReferenceCharts()) {
            addReferenceCharts(pageFormat);
        }
    }
    
    protected void writeTextFields() {
        setTextField(TITLE, getRecordSheetTitle().toUpperCase());
        setTextField(TYPE, entityName());
        setTextField(MP_WALK, formatWalk());
        setTextField(MP_RUN, formatRun());
        setTextField(MP_JUMP, formatJump());
        if (getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            setTextField(TONNAGE, NumberFormat.getInstance()
                    .format((int) (getEntity().getWeight() * 1000)) + " kg");
        } else {
            setTextField(TONNAGE, NumberFormat.getInstance().format((int) getEntity().getWeight()));
        }
        setTextField(TECH_BASE, formatTechBase());
        setTextField(RULES_LEVEL, formatRulesLevel());
        setTextField(COST, formatCost());
        // If we're using a MUL to print generic sheets we also want to ignore any BV adjustments
        // for C3 networks or pilot skills.
        setTextField(BV, NumberFormat.getInstance().format(getEntity()
                .calculateBattleValue(!showPilotInfo(), !showPilotInfo())));
        UnitRole role = getEntity().getRole();
        if (!options.showRole() || (role == UnitRole.UNDETERMINED)) {
            hideElement(LBL_ROLE, true);
            hideElement(ROLE, true);
        } else {
            setTextField(ROLE, role.toString());
        }
        
        // If we need to fill in names of crew slots we will need to reposition blanks/name fields.
        // This will require building the graphics tree so we measure the elements.
        if (getEntity().getCrew().getCrewType() != CrewType.SINGLE) {
            build();
        }
        hideUnusedCrewElements();
        for (int i = 0; i < getEntity().getCrew().getSlotCount(); i++) {
            // If we have multiple named crew for the unit, change the "Name:" label to
            // the label of the slot. This will usually require adjusting the position of the
            // name or the length of the blank.
            double nameOffset = 0;
            if (getEntity().getCrew().getSlotCount() > 1) {
                Element element = getSVGDocument().getElementById(CREW_NAME + i);
                if (null != element) {
                    float oldWidth = ((SVGTextContentElement) element).getComputedTextLength();
                    element.setTextContent(getEntity().getCrew().getCrewType().getRoleName(i) + ":");
                    nameOffset = SVGLocatableSupport.getBBox(element).getWidth() - oldWidth;
                }
            }
            if (showPilotInfo()) {
                Element element = getSVGDocument().getElementById(BLANKS_CREW + i);
                if (null != element) {
                    hideElement(element);
                }
                if (nameOffset != 0) {
                    element = getSVGDocument().getElementById(PILOT_NAME + i);
                    if (null != element) {
                        double offset = nameOffset;
                        String prev = element.getAttribute(SVGConstants.SVG_X_ATTRIBUTE);
                        if (!prev.isBlank()) {
                            offset += Double.parseDouble(prev);
                        } else {
                            offset += ((SVGTextContentElement) element).getStartPositionOfChar(0).getX();
                        }
                        element.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(offset));
                    }
                }
                setTextField(PILOT_NAME + i, getEntity().getCrew().getName(i), true);
                setTextField(GUNNERY_SKILL + i, Integer.toString(getEntity().getCrew().getGunnery(i)), true);
                setTextField(PILOTING_SKILL + i, Integer.toString(getEntity().getCrew().getPiloting(i)), true);
                
                StringJoiner spaList = new StringJoiner(", ");
                PilotOptions spas = getEntity().getCrew().getOptions();
                for (Enumeration<IOptionGroup> optionGroups = spas.getGroups(); optionGroups.hasMoreElements();) {
                    IOptionGroup optiongroup = optionGroups.nextElement();
                    if (spas.count(optiongroup.getKey()) > 0) {
                        for (Enumeration<IOption> options = optiongroup.getOptions(); options.hasMoreElements();) {
                            IOption option = options.nextElement();
                            if (option != null && option.booleanValue()) {
                                spaList.add(option.getDisplayableNameWithValue().replaceAll(" \\(.*?\\)", ""));
                            }
                        }
                    }
                }
                if (spaList.length() > 0) {
                    Element rect = getSVGDocument().getElementById(SPAS + (getEntity().getCrew().getSlotCount() - 1));
                    if (rect instanceof SVGRectElement) {
                        Rectangle2D bbox = getRectBBox((SVGRectElement) rect);
                        Element canvas = (Element) rect.getParentNode();
                        String spaText = "Abilities: " + spaList;
                        float fontSize = FONT_SIZE_MEDIUM;
                        if (getTextLength(spaText, fontSize) > bbox.getWidth()) {
                            fontSize = (float) bbox.getHeight() / 2.4f;
                        }
                        double lineHeight = fontSize * 1.2;
                        addMultilineTextElement(canvas, bbox.getX(), bbox.getY() + lineHeight,
                                bbox.getWidth(), lineHeight, spaText, fontSize,
                                SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                                FILL_BLACK, ' ');
                    }
                }
    
            } else {
                setTextField(PILOT_NAME + i, null);
                setTextField(GUNNERY_SKILL + i, null);
                setTextField(PILOTING_SKILL + i, null);
                if (nameOffset != 0) {
                    Element element = getSVGDocument().getElementById(BLANK_CREW_NAME + i);
                    if (null != element) {
                        SVGRect rect = ((SVGGraphicsElement) element).getBBox();
                        element.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE,
                                String.format("M %f,%f %f,%f", rect.getX() + nameOffset, rect.getY(),
                                        rect.getX() + rect.getWidth(), rect.getY()));
                    }
                }
            }
        }
    }

    protected void hideUnusedCrewElements() {
        for (int i = 0; i < 3; i++) {
            final boolean hide = i >= getEntity().getCrew().getSlotCount();
            hideElement(CREW_DAMAGE + i, hide);
            hideElement(PILOT_NAME + i, hide);
            hideElement(BLANK_CREW_NAME + i, hide || showPilotInfo());
            hideElement(CREW_NAME + i, hide);
            hideElement(GUNNERY_SKILL + i, hide);
            hideElement(BLANK_GUNNERY_SKILL + i, hide || showPilotInfo());
            hideElement(GUNNERY_SKILL_TEXT + i, hide);
            hideElement(PILOTING_SKILL + i, hide);
            hideElement(BLANK_PILOTING_SKILL + i, hide || showPilotInfo());
            hideElement(PILOTING_SKILL_TEXT + i, hide);
        }
    }

    protected void drawArmor() {
        if (getEntity().isSupportVehicle()
                && (getEntity().hasBARArmor(firstArmorLocation()))) {
            setTextField(ARMOR_TYPE, "BAR: " + getEntity().getBARRating(firstArmorLocation()));
        } else if (!getEntity().hasPatchworkArmor()) {
            final int at = getEntity().getArmorType(firstArmorLocation());
            String armorType = (at == T_ARMOR_STANDARD) ? "Standard Armor" : EquipmentType.getArmorTypeName(at);
            if (getEntity().hasBARArmor(firstArmorLocation())) {
                armorType += ", BAR: " + getEntity().getBARRating(firstArmorLocation());
            }
            setTextField(ARMOR_TYPE, armorType);
        } else {
            boolean hasSpecial = false;
            for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
                if ((getEntity().getArmorType(loc) != T_ARMOR_STANDARD)
                        && (getEntity().getArmorType(loc) != T_ARMOR_BA_STANDARD)
                        && (getEntity().getArmorType(loc) != T_ARMOR_STANDARD_PROTOMEK)
                        // Stealth armor loses special properties when used with patchwork, so we don't
                        // need to show it.
                        && (getEntity().getArmorType(loc) != EquipmentType.T_ARMOR_STEALTH)
                        && (getEntity().getArmorType(loc) != EquipmentType.T_ARMOR_STEALTH_VEHICLE)) {
                    String atName = EquipmentType.getArmorTypeName(getEntity().getArmorType(loc));
                    String eleName = PATCHWORK + getEntity().getLocationAbbr(loc);
                    int index = atName.indexOf('-');
                    if ((index < 0) || (getSVGDocument().getElementById(eleName + "2") == null)) {
                        setTextField(PATCHWORK + getEntity().getLocationAbbr(loc), atName);
                    } else {
                        setTextField(PATCHWORK + getEntity().getLocationAbbr(loc),
                                atName.substring(0, index));
                        setTextField(PATCHWORK + getEntity().getLocationAbbr(loc) + "2",
                                atName.substring(index + 1));
                    }
                    hasSpecial = true;
                }
            }
            if (hasSpecial) {
                setTextField(ARMOR_TYPE, EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK));
            } else {
                setTextField(ARMOR_TYPE, "Standard Armor");
            }
        }
        writeArmorStructureTextFields();
        drawArmorStructurePips();
    }

    /**
     * Fills in the numeral values for armor and structure levels
     */
    void writeArmorStructureTextFields() {
        final String FORMAT = "( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOArmor(loc)));
            setTextField(TEXT_IS + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOInternal(loc)));
        }
    }

    /**
     * Add armor and structure pips for each location.
     */
    protected void drawArmorStructurePips() {
        Element element;
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            if ((getEntity() instanceof Mech) && getEntity().isSuperHeavy() && (loc == Mech.LOC_HEAD)) {
                element = getSVGDocument().getElementById(ARMOR_PIPS + getEntity().getLocationAbbr(loc) + "_SH");
            } else {
                element = getSVGDocument().getElementById(ARMOR_PIPS + getEntity().getLocationAbbr(loc));
            }
            if (null != element) {
                ArmorPipLayout.addPips(this, element, getEntity().getOArmor(loc),
                        PipType.forAT(getEntity().getArmorType(loc)), 0.5, FILL_WHITE);
            }
            element = getSVGDocument().getElementById(STRUCTURE_PIPS + getEntity().getLocationAbbr(loc));
            if (null != element) {
                ArmorPipLayout.addPips(this, element, getEntity().getOInternal(loc),
                        PipType.CIRCLE, 0.5, structurePipFill());
            }
        }
    }

    /**
     * @return The color to use in the inside of structure pips
     */
    String structurePipFill() {
        return FILL_WHITE;
    }
    
    /**
     * Identifies the index of the first location that can be armored. For vehicles this should be 1
     * to skip the body.
     * 
     * @return The lowest location index that can be armored.
     */
    protected int firstArmorLocation() {
        return 0;
    }
    
    protected void drawStructure() {
        
    }

    /**
     * Fills in the weapons and inventory section of the record sheet.
     *
     * @param svgRect The bounds of the text region
     */
    protected void writeEquipment(SVGRectElement svgRect) {
        new InventoryWriter(this, svgRect).writeEquipment();
        if (!CConfig.scaleUnits().equals(RSScale.HEXES)) {
            setTextField(UNIT_SCALE, "(" + CConfig.scaleUnits().fullName + ")");
        }
    }

    protected void drawFluffImage() { }

    protected @Nullable Image getFluffImage() {
        return FluffImageHelper.getRecordSheetFluffImage(getEntity());
    }

    private void drawEraIcon() {
        Era era = Eras.getEra(getEntity().getYear());
        Element rect = getSVGDocument().getElementById(ERA_ICON);
        if ((rect instanceof SVGRectElement) && era.hasIcon()) {
            File iconFile = new File(Configuration.universeImagesDir(), era.iconFilePath());
            embedImage(iconFile, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
        }
    }

    /**
     * @param svgRect The <rect> defining the bounds of the heat sink pip region
     * @param hsCount The number of heat sink pips to draw
     */
    void drawHeatSinkPips(SVGRectElement svgRect, int hsCount) {
        Rectangle2D bbox = getRectBBox(svgRect);
        Element canvas = (Element) svgRect.getParentNode();
        double viewWidth = bbox.getWidth();
        double viewHeight = bbox.getHeight();
        double viewX = bbox.getX();
        double viewY = bbox.getY();
        if (viewWidth > viewHeight) {
            drawHeatSinkPipsLandscape(canvas, hsCount, viewX, viewY, viewWidth, viewHeight);
            return;
        }

        // r = 3.5
        // spacing = 9.66
        // stroke width = 0.9
        double size = Math.min(9.66, viewHeight / 10);
        int cols = (int) (viewWidth / size);
        int rows = (int) (viewHeight / size);

        // Use 10 pips/column unless there are too many sinks for the space.
        if (hsCount <= cols * 10) {
            rows = 10;
        }
        // The rare unit with this many heat sinks will require us to shrink the pips
        while (hsCount > rows * cols) {
            // Figure out how much we will have to shrink to add another column
            double nextCol = (cols + 1.0) / cols;
            // First check whether we can shrink them less than what is required for a new column
            if (cols * (int) (rows * nextCol) > hsCount) {
                rows = (int) Math.ceil((double) hsCount / cols);
                size = viewHeight / rows;
            } else {
                cols++;
                size *= viewWidth / (cols * size);
                rows = (int) (viewHeight / size);
            }
        }
        double radius = size * 0.36;
        double strokeWidth = 0.9;
        for (int i = 0; i < hsCount; i++) {
            int row = i % rows;
            int col = i / rows;
            Element pip = createPip(viewX + size * col, viewY + size * row, radius, strokeWidth);
            canvas.appendChild(pip);
        }
    }

    void drawHeatSinkPipsLandscape(Element canvas, int hsCount, double viewX, double viewY,
                                   double viewWidth, double viewHeight) {
        double size = Math.min(9.66, viewWidth / 10);
        int cols = (int) (viewWidth / size);
        int rows = (int) (viewHeight / size);

        // Use 10 pips/row unless there are too many sinks for the space.
        if (hsCount <= rows * 10) {
            cols = 10;
        }
        // The rare unit with this many heat sinks will require us to shrink the pips
        while (hsCount > cols * rows) {
            // Figure out how much we will have to shrink to add another column
            double nextCol = (rows + 1.0) / rows;
            // First check whether we can shrink them less than what is required for a new column
            if (rows * (int) (cols * nextCol) > hsCount) {
                cols = (int) Math.ceil((double) hsCount / rows);
                size = viewHeight / cols;
            } else {
                rows++;
                size *= viewWidth / (rows * size);
                cols = (int) (viewHeight / size);
            }
        }
        double radius = size * 0.36;
        double strokeWidth = 0.9;
        for (int i = 0; i < hsCount; i++) {
            int col = i % cols;
            int row = i / cols;
            Element pip = createPip(viewX + size * col, viewY + size * row, radius, strokeWidth);
            canvas.appendChild(pip);
        }
    }

    /**
     * Applies the current scale to a movement point value and adds the units indicator.
     * If the units are hexes, the value is rounded up.
     *
     * @param mp The movement points
     * @return   The formatted movement string
     */
    protected String formatMovement(double mp) {
        return CConfig.formatScale(mp, true);
    }

    /**
     * Applies the current scale to a pair of movement point values, puts the second in brackets,
     * and adds the units indicator. This is used for cases when equipment may give a temporary
     * boost to MP, such as MASC.
     * If the units are hexes, the value is rounded up.
     *
     * @param baseMP The base movement points
     * @param fullMP The full movement points
     * @return   The formatted movement string
     */
    protected String formatMovement(double baseMP, double fullMP) {
        if (fullMP > baseMP) {
            return CConfig.formatScale(baseMP, false) + " ["
                    + CConfig.formatScale(fullMP, false) + "] "
                    + CConfig.scaleUnits().abbreviation;
        } else {
            return CConfig.formatScale(baseMP, true);
        }
    }

    protected String formatWalk() {
        return formatMovement(getEntity().getWalkMP());
    }
    
    protected String formatRun() {
        return formatMovement(getEntity().getWalkMP() * 1.5);
    }
    
    protected String formatJump() {
        return formatMovement(getEntity().getJumpMP());
    }

    protected String formatTechBase() {
        if (getEntity().isMixedTech()) {
            return "Mixed";
        } else if (getEntity().isClan()) {
            return "Clan";
        } else {
            return "Inner Sphere";
        }
    }
    
    protected String formatRulesLevel() {
        SimpleTechLevel level;
        if (options.useEraBaseProgression()) {
            level = getEntity().getSimpleLevel(getEntity().getYear(), getEntity().isClan());
        } else {
            level = getEntity().getStaticTechLevel();
        }
        return level.toString().substring(0, 1)
                + level.toString().substring(1).toLowerCase();
    }
    
    protected String formatCost() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        return nf.format(getEntity().getCost(true)) + " C-bills";
    }

    private String entityName() {
        return CConfig.getMekNameArrangement().printChassis(getEntity())
                + (StringUtility.isNullOrBlank(getEntity().getModel()) ? "" : " " + getEntity().getModel());
    }

}
