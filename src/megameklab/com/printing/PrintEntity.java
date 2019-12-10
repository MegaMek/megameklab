/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.io.File;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import megamek.common.*;
import megameklab.com.util.RecordSheetEquipmentLine;
import megameklab.com.util.UnitUtil;
import org.apache.batik.anim.dom.SVGGraphicsElement;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRect;
import org.w3c.dom.svg.SVGRectElement;
import org.w3c.dom.svg.SVGTextContentElement;

import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.PilotOptions;
import megamek.common.options.Quirks;

/**
 * Base class for printing Entity record sheets
 * 
 * @author Neoancient
 *
 */
public abstract class PrintEntity extends PrintRecordSheet {
    
    // Armor types with special properties that should be identified on the record sheet
    private static final long AT_SPECIAL = (1 << EquipmentType.T_ARMOR_REACTIVE)
            | (1 << EquipmentType.T_ARMOR_REFLECTIVE)
            | (1 << EquipmentType.T_ARMOR_HARDENED)
            | (1 << EquipmentType.T_ARMOR_STEALTH)
            | (1 << EquipmentType.T_ARMOR_FERRO_LAMELLOR)
            | (1 << EquipmentType.T_ARMOR_STEALTH_VEHICLE)
            | (1 << EquipmentType.T_ARMOR_ANTI_PENETRATIVE_ABLATION)
            | (1 << EquipmentType.T_ARMOR_HEAT_DISSIPATING)
            | (1 << EquipmentType.T_ARMOR_IMPACT_RESISTANT)
            | (1 << EquipmentType.T_ARMOR_BALLISTIC_REINFORCED);
    
    /**
     * Creates an SVG object for the record sheet
     * 
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed 
     */
    protected PrintEntity(int startPage, RecordSheetOptions options) {
        super(startPage, options);
    }

    protected abstract Entity getEntity();
    
    /**
     * When printing from a MUL the pilot data is filled in unless the option has been disabled. This
     * allows a series of blank record sheets to be generated without including the generated pilot data.
     * If the crew name is "unnamed" then we are printing directly from MML or file/cache and the
     * pilot data should not be filled in.
     * 
     * @return Whether the pilot data should be filled in.
     */
    protected boolean showPilotInfo() {
        return options.showPilotData() && !getEntity().getCrew().getName().equalsIgnoreCase("unnamed");
    }

    /**
     * Space for misc equipment such as cargo space and SV chassis mods.
     *
     * @return A list of chassis mods, or an empty String if none
     */
    protected String formatFeatures() {
        return "";
    }

    /**
     * Builds the string to display for the quirks block. Returns an empty string if quirks are
     * disabled (or if the unit has no quirks).
     * 
     * @return The text to display for the unit's quirks.
     */
    protected String formatQuirks() {
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
    
    @Override
    protected void processImage(int pageNum, PageFormat pageFormat) {
        Element element;
        
        element = getSVGDocument().getElementById(COPYRIGHT);
        if (null != element) {
            element.setTextContent(String.format(element.getTextContent(),
                    Calendar.getInstance().get(Calendar.YEAR)));
        }
        
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
    }
    
    protected void writeTextFields() {
        setTextField(TITLE, getRecordSheetTitle().toUpperCase());
        setTextField(TYPE, getEntity().getShortNameRaw());
        setTextField(MP_WALK, formatWalk());
        setTextField(MP_RUN, formatRun());
        setTextField(MP_JUMP, formatJump());
        setTextField(TONNAGE, NumberFormat.getInstance().format((int) getEntity().getWeight()));
        setTextField(TECH_BASE, formatTechBase());
        setTextField(RULES_LEVEL, formatRulesLevel());
        setTextField(ERA, formatEra(getEntity().getYear()));
        setTextField(COST, formatCost());
        // If we're using a MUL to print generic sheets we also want to ignore any BV adjustments
        // for C3 networks or pilot skills.
        setTextField(BV, NumberFormat.getInstance().format(getEntity()
                .calculateBattleValue(!showPilotInfo(), !showPilotInfo())));
        UnitRole role = UnitRoleHandler.getRoleFor(getEntity());
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
                        if (null != prev) {
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
                        String spaText = "Abilities: " + spaList.toString();
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
                && (getEntity().hasBARArmor(getEntity().firstArmorIndex()))) {
            setTextField(ARMOR_TYPE, "BAR: " + getEntity().getBARRating(firstArmorLocation()));
        } else if (!getEntity().hasPatchworkArmor()) {
            if ((AT_SPECIAL & (1 << getEntity().getArmorType(1))) != 0) {
                String[] atName = EquipmentType.getArmorTypeName(getEntity().getArmorType(1)).split("-");
                setTextField(ARMOR_TYPE, atName[0]);
                if (atName.length > 1) {
                    setTextField(ARMOR_TYPE_2, atName[1]);
                }
            } else {
                hideElement(ARMOR_TYPE, true);
            }
        } else {
            boolean hasSpecial = false;
            for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
                if (((AT_SPECIAL & (1 << getEntity().getArmorType(loc))) != 0)
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
                hideElement(ARMOR_TYPE, true);
            }
        }
        final String FORMAT = "( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOArmor(loc)));
            setTextField(TEXT_IS + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOInternal(loc)));
        }
        drawArmorStructurePips();
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
                        PipType.forAT(getEntity().getArmorType(loc)), 0.5);
            }
            element = getSVGDocument().getElementById(STRUCTURE_PIPS + getEntity().getLocationAbbr(loc));
            if (null != element) {
                ArmorPipLayout.addPips(this, element, getEntity().getOInternal(loc),
                        PipType.CIRCLE, 0.5);
            }
        }
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
    
    /**
     * Identifies which locations are on the unit's centerline and should have armor and structure
     * pips laid out with left-right symmetry
     * 
     * @param loc The location to check
     * @return    Whether the location is along the unit's centerline
     */
    protected abstract boolean isCenterlineLocation(int loc);
    
    protected void drawStructure() {
        
    }

    protected void writeEquipment(SVGRectElement svgRect) {
        Map<Integer, Map<RecordSheetEquipmentLine,Integer>> eqMap = new TreeMap<>();
        Map<String,Integer> ammo = new TreeMap<>();
        for (Mounted m : getEntity().getEquipment()) {
            if ((m.getType() instanceof AmmoType)
                    && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = m.getType().getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    String munition = ((AmmoType) m.getType()).getSubMunitionName().replace("(Clan) ", "");
                    shortName = shortName.replace(munition, "");
                    ammo.merge(shortName.trim(), m.getBaseShotsLeft(), Integer::sum);
                }
                continue;
            }
            if ((m.getType() instanceof AmmoType)
                    || (m.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(m.getType(), getEntity() instanceof Mech)) {
                continue;
            }
            if (getEntity().hasETypeFlag(Entity.ETYPE_QUADVEE)
                    && (m.getType() instanceof MiscType)
                    && m.getType().hasFlag(MiscType.F_TRACKS)) {
                continue;
            }
            eqMap.putIfAbsent(m.getLocation(), new HashMap<>());
            RecordSheetEquipmentLine line = new RecordSheetEquipmentLine(m);
            eqMap.get(m.getLocation()).merge(line, 1, Integer::sum);
        }

        Rectangle2D bbox = getRectBBox(svgRect);
        Element canvas = (Element) svgRect.getParentNode();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();

        int qtyX = (int) Math.round(viewX + viewWidth * 0.037);
        int nameX = (int) Math.round(viewX + viewWidth * 0.08);
        int locX = (int) Math.round(viewX + viewWidth * 0.41);
        int heatX = (int) Math.round(viewX + viewWidth * 0.48);
        int dmgX = (int) Math.round(viewX + viewWidth * 0.53);
        int minX = (int) Math.round(viewX + viewWidth * 0.75);
        int shortX = (int) Math.round(viewX + viewWidth * 0.82);
        int medX = (int) Math.round(viewX + viewWidth * 0.89);
        int longX = (int) Math.round(viewX + viewWidth * 0.96);

        int indent = (int) Math.round(viewWidth * 0.02);

        int currY = viewY + 10;

        float fontSize = FONT_SIZE_MEDIUM;
        float lineHeight = getFontHeight(fontSize) * 1.2f;

        addTextElement(canvas, qtyX, currY, "Qty", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, nameX + indent, currY, "Type", fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, locX,  currY, "Loc", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        if (getEntity().tracksHeat() || getEntity().isLargeCraft()) {
            addTextElement(canvas, heatX, currY, "Ht", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        }
        addTextElement(canvas, dmgX, currY, "Dmg", fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, minX, currY, "Min", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, shortX, currY, "Sht", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, medX, currY, "Med", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, longX, currY, "Lng", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        currY += lineHeight * 1.2;

        int lines = 0;
        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                int rows = line.nRows();
                if ((rows == 1) && (getTextLength(line.getNameField(0,
                        getEntity().isMixedTech()), fontSize) > locX - nameX)) {
                    rows++;
                }
                lines += rows;
            }
        }
        if (lines > 11) {
            lineHeight = getFontHeight(fontSize) * 1.0f;
        }
        if (lines > 13) {
            lineHeight = getFontHeight(fontSize) * 0.8f;
        }
        if (lines > 16) {
            fontSize = FONT_SIZE_SMALL;
        }
        if (lines > 20) {
            fontSize = FONT_SIZE_VSMALL;
        }

        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                for (int row = 0; row < line.nRows(); row++) {
                    if (row == 0) {
                        addTextElement(canvas, qtyX, currY, Integer.toString(eqMap.get(loc).get(line)), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                        lines = addMultilineTextElement(canvas, nameX, currY, locX - nameX - indent, lineHeight,
                                line.getNameField(row, getEntity().isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);

                    } else {
                        lines = addMultilineTextElement(canvas, nameX + indent, currY, locX - nameX - indent, lineHeight,
                                line.getNameField(row, getEntity().isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    }
                    addTextElement(canvas, locX,  currY, line.getLocationField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    if (getEntity().tracksHeat() || getEntity().isLargeCraft()) {
                        addTextElement(canvas, heatX, currY, line.getHeatField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    }
                    lines = Math.max(lines, addMultilineTextElement(canvas, dmgX, currY, minX - dmgX - fontSize, lineHeight,
                            line.getDamageField(row), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE));
                    addTextElement(canvas, minX, currY, line.getMinField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, shortX, currY, line.getShortField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, medX, currY, line.getMediumField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, longX, currY, line.getLongField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    currY += lineHeight * lines;
                }
            }
        }

        String features = formatFeatures();
        String quirksText = formatQuirks();

        if (ammo.size() + features.length() + quirksText.length() > 0) {
            Element svgGroup = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            canvas.appendChild(svgGroup);
            lines = 0;
            if (ammo.size() > 0) {
                lines = addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, 0, viewWidth * 0.95, lineHeight,
                        "Ammo: " + ammo.entrySet().stream()
                                .map(e -> String.format("(%s) %d", e.getKey(), e.getValue()))
                                .collect(Collectors.joining(", ")), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (features.length() > 0) {
                lines += addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025,
                        lines * lineHeight, viewWidth * 0.95, lineHeight,
                        "Features " + features, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (quirksText.length() > 0) {
                lines += addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, lines * lineHeight,
                        viewWidth * 0.95, lineHeight,
                        "Quirks: " + quirksText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            svgGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                    String.format("%s(0,%f)", SVGConstants.SVG_TRANSLATE_VALUE,
                            viewY + viewHeight - lines * lineHeight));
        }

    }

    protected void drawFluffImage() {
        
    }
    
    private void drawEraIcon() {
        File iconFile;
        if (getEntity().getYear() < 2781) {
            iconFile = new File("data/images/recordsheets/era_starleague.png");
        } else if (getEntity().getYear() < 3050) {
            iconFile = new File("data/images/recordsheets/era_sw.png");
        } else if (getEntity().getYear() < 3061) {
            iconFile = new File("data/images/recordsheets/era_claninvasion.png");
        } else if (getEntity().getYear() < 3068) {
            iconFile = new File("data/images/recordsheets/era_civilwar.png");
        } else if (getEntity().getYear() < 3086) {
            iconFile = new File("data/images/recordsheets/era_jihad.png");
        } else {
            iconFile = new File("data/images/recordsheets/era_darkage.png");
        }
        Element rect = getSVGDocument().getElementById(ERA_ICON);
        if (rect instanceof SVGRectElement) {
            embedImage(iconFile,
                    (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
        }
    }
    
    protected String formatWalk() {
        return Integer.toString(getEntity().getWalkMP());
    }
    
    protected String formatRun() {
        return getEntity().getRunMPasString();
    }
    
    protected String formatJump() {
        return Integer.toString(getEntity().getJumpMP());
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
        return getEntity().getStaticTechLevel().toString().substring(0, 1)
                + getEntity().getStaticTechLevel().toString().substring(1).toLowerCase();
    }
    
    private static String formatEra(int year) {
        if (year < 2571) {
            return "Age of War";
        } else if (year < 2781) {
            return "Star League";
        } else if (year < 2901) {
            return "Early Succession War";
        } else if (year < 3050) {
            return "Late Succession War";
        } else if (year < 3062) {
            return "Clan Invasion";
        } else if (year < 3068) {
            return "Civil War";
        } else if (year < 3086) {
            return "Jihad";
        } else if (year < 3101) {
            return "Early Republic";
        } else if (year < 3131) {
            return "Late Republic";
        } else {
            return "Dark Ages";
        }
    }
    
    protected String formatCost() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        return nf.format(getEntity().getCost(true)) + " C-bills";
    }
    
}
