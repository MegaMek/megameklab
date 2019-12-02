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
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.dom.util.SAXDocumentFactory;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGRectElement;

import megamek.common.AmmoType;
import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentMessages;
import megamek.common.ITechnology;
import megamek.common.LAMPilot;
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadVee;
import megamek.common.annotations.Nullable;
import megameklab.com.MegaMekLab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RecordSheetEquipmentLine;
import megameklab.com.util.UnitUtil;

/**
 * Lays out a record sheet for a mech
 * 
 * @author Neoancient
 *
 */
public class PrintMech extends PrintEntity {
    
    /**
     * The current mech being printed.
     */
    private final Mech mech;
    
    /**
     * Creates an SVG object for the record sheet
     * 
     * @param mech The mech to print
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed 
     */
    public PrintMech(Mech mech, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.mech = mech;
    }
    
    /**
     * Creates an SVG object for the record sheet using the global printing options
     * 
     * @param mech The mech to print
     * @param startPage The print job page number for this sheet
     */
    public PrintMech(Mech mech, int startPage) {
        this(mech, startPage, new RecordSheetOptions());
    }
    
    @Override
    protected String getSVGFileName(int pageNumber) {
        if (mech.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            return "mech_quadvee.svg";
        } else if (mech.hasETypeFlag(Entity.ETYPE_QUAD_MECH)) {
            return "mech_quad_default.svg";
        } else if (mech.hasETypeFlag(Entity.ETYPE_TRIPOD_MECH)) {
            return "mech_tripod_default.svg";
        } else if (mech.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            return "mech_biped_lam.svg";
        } else {
            return "mech_biped_default.svg";
        }
    }
    
    @Override
    protected String getRecordSheetTitle() {
        StringBuilder sb = new StringBuilder();
        // General qualifier
        if (mech.isSuperHeavy()) {
            sb.append("SuperHeavy ");
        }
        if (mech.isPrimitive()) {
            sb.append("Primitive ");
        }
        if ((mech instanceof LandAirMech) && (((LandAirMech) mech).getLAMType() == LandAirMech.LAM_BIMODAL)) {
            sb.append("Bimodal ");
        }
        // Leg configuration
        if (mech.hasETypeFlag(Entity.ETYPE_QUAD_MECH)
                && !mech.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            sb.append("Four-Legged ");
        } else if (mech.hasETypeFlag(Entity.ETYPE_TRIPOD_MECH)) {
            sb.append("Three-Legged ");
        }
        // mech type
        if (mech.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            sb.append("Land-Air 'Mech");
        } else if (mech.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            if (mech.isOmni()) {
                sb.append("Omni");
            }
            sb.append("QuadVee");
        } else if (mech.isIndustrial()) {
            sb.append("IndustrialMech");
        } else if (mech.isOmni()) {
            sb.append("OmniMech");
        } else {
            sb.append("BattleMech");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }
    
    @Override
    protected Entity getEntity() {
        return mech;
    }
    
    @Override
    public void printImage(Graphics2D g2d, PageFormat pageFormat, int pageNum) {
        printShields();
        
        super.printImage(g2d, pageFormat, pageNum);

        for (int loc = 0; loc < mech.locations(); loc++) {
            Element critRect = getSVGDocument().getElementById(CRITS + mech.getLocationAbbr(loc));
            if (critRect instanceof SVGRectElement) {
                writeLocationCriticals(loc, (SVGRectElement) critRect);
            }
        }
        
        hideElement(HEAVY_DUTY_GYRO_PIP, mech.getGyroType() != Mech.GYRO_HEAVY_DUTY);
        
        Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
        if (hsRect instanceof SVGRectElement) {
            drawHeatSinkPips((SVGRectElement) hsRect);
        }

        if (mech.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            Element si = getSVGDocument().getElementById(SI_PIPS);
            if (si instanceof SVGRectElement) {
                drawSIPips((SVGRectElement) si);
            } else {
                MegaMekLab.getLogger().error(getClass(), "PrintImage(Graphics2D, PageFormat, int)",
                        "Region siPips does not exist in template or is not a <rect>");
            }
        }
        
    }
    
    private void printShields() {
        for (Mounted m : mech.getMisc()) {
            if (((MiscType) m.getType()).isShield()) {
                String loc = mech.getLocationAbbr(m.getLocation());
                Element element;
                element = getSVGDocument().getElementById(ARMOR_DIAGRAM + loc);
                if (null != element) {
                    hideElement(element, true);
                }
                element = getSVGDocument().getElementById(SHIELD + loc);
                if (null != element) {
                    hideElement(element, false);
                }
                element = getSVGDocument().getElementById(SHIELD_DC + loc);
                if (null != element) {
                    addPips(element, m.getBaseDamageCapacity(), false, PipType.CIRCLE);
                }
                element = getSVGDocument().getElementById(SHIELD_DA + loc);
                if (null != element) {
                    addPips(element, m.getBaseDamageAbsorptionRate(), false, PipType.DIAMOND);
                }
            }
        }
    }

    @Override
    protected void writeTextFields() {
        hideUnusedCrewElements();
        super.writeTextFields();
        if (mech.hasUMU()) {
            Element svgEle = getSVGDocument().getElementById(MP_JUMP_LABEL);
            if (null != svgEle) {
                svgEle.setTextContent("Underwater:");
            }
        }

        setTextField(HS_TYPE, formatHeatSinkType());
        setTextField(HS_COUNT, formatHeatSinkCount());
        
        if (mech instanceof LandAirMech) {
            LandAirMech lam = (LandAirMech) mech;
            if (lam.getLAMType() == LandAirMech.LAM_BIMODAL) {
                setTextField(MP_AIRMECH_WALK, "\u2014"); // em dash
                setTextField(MP_AIRMECH_RUN, "\u2014");
                setTextField(MP_AIRMECH_CRUISE, "\u2014");
                setTextField(MP_AIRMECH_FLANK, "\u2014");
            } else {
                setTextField(MP_AIRMECH_WALK, Integer.toString(lam.getAirMechWalkMP()));
                setTextField(MP_AIRMECH_RUN, Integer.toString(lam.getAirMechRunMP()));
                setTextField(MP_AIRMECH_CRUISE, Integer.toString(lam.getAirMechCruiseMP()));
                setTextField(MP_AIRMECH_FLANK, Integer.toString(lam.getAirMechFlankMP()));
            }
            setTextField(MP_SAFE_THRUST, Integer.toString(lam.getJumpMP()));
            setTextField(MP_MAX_THRUST, Integer.toString((int) Math.ceil(lam.getJumpMP() * 1.5)));
            if (showPilotInfo() && (lam.getCrew() instanceof LAMPilot)) {
                setTextField(ASF_GUNNERY_SKILL, Integer.toString(((LAMPilot) mech.getCrew()).getGunneryAero()));
                setTextField(ASF_PILOTING_SKILL, Integer.toString(((LAMPilot) mech.getCrew()).getPilotingAero()));
                hideElement(ASF_BLANK_GUNNERY_SKILL, true);
                hideElement(ASF_BLANK_PILOTING_SKILL, true);
            } else {
                hideElement(ASF_GUNNERY_SKILL);
                hideElement(ASF_PILOTING_SKILL);
            }
        } else if (mech instanceof QuadVee) {
            setTextField(MP_CRUISE, Integer.toString(((QuadVee) mech).getCruiseMP(false, false, false)));
            setTextField(MP_FLANK, formatQuadVeeFlank());
            setTextField(LBL_VEE_MODE, ((QuadVee) mech).getMotiveTypeString() + "s");
        }
    }

    private void hideUnusedCrewElements() {
        final String[] NAMES = {SINGLE, DUAL, TRIPLE};
        for (int i = 0; i < 3; i++) {
            hideElement(WARRIOR_DATA + NAMES[i], getEntity().getCrew().getSlotCount() != i + 1);
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

    private boolean loadArmorPips(int loc, boolean rear) {
        String locAbbr;
        switch(loc) {
            case Mech.LOC_HEAD:
                locAbbr = "Head";
                break;
            case Mech.LOC_RARM:
            case Mech.LOC_LARM:
                locAbbr = mech.getLocationAbbr(loc) + "rm";
                break;
            case Mech.LOC_RLEG:
            case Mech.LOC_LLEG:
                locAbbr = mech.getLocationAbbr(loc) + "eg";
                break;
            default:
                locAbbr = mech.getLocationAbbr(loc);
                break;
        }
        if (rear) {
            locAbbr += "_R";
        }
        if (rear) {
            Element element = getSVGDocument().getElementById(TEXT_ARMOR + mech.getLocationAbbr(loc) + "R");
            if (null != element) {
                element.setTextContent(String.format("( %d )", mech.getOArmor(loc, true)));
            }
        }

        NodeList nl = loadPipSVG(String.format("data/images/recordsheets/biped_pips/Armor_%s_%d_Humanoid.svg",
                locAbbr, mech.getOArmor(loc, rear)));
        if (null == nl) {
            return false;
        }
        return copyPipPattern(nl, CANON_ARMOR_PIPS);
    }
    
    private boolean loadISPips() {
        NodeList nl = loadPipSVG(String.format("data/images/recordsheets/biped_pips/BipedIS%d.svg",
                (int) mech.getWeight()));
        if (null == nl) {
            return false;
        }
        hideElement(STRUCTURE_PIPS);
        return copyPipPattern(nl, CANON_STRUCTURE_PIPS);
    }

    private boolean copyPipPattern(NodeList nl, String parentName) {
        Element parent = getSVGDocument().getElementById(parentName);
        if (null == parent) {
            return false;
        }
        for (int node = 0; node < nl.getLength(); node++) {
            final Node wn = nl.item(node);
            parent.appendChild(getSVGDocument().importNode(wn, true));
        }
        return true;
    }

    private @Nullable NodeList loadPipSVG(String filename) {
        final String METHOD_NAME = "loadPipsSVG(int, int)"; //$NON-NLS-1$
        File f = new File(filename);
        if (!f.exists()) {
            return null;
        }
        Document doc;
        try {
            InputStream is = new FileInputStream(f);
            DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
            final String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXDocumentFactory df = new SAXDocumentFactory(impl, parser);
            doc = df.createDocument(f.toURI().toASCIIString(), is);
        } catch (Exception e) {
            MegaMekLab.getLogger().error(PrintRecordSheet.class, METHOD_NAME,
                    "Failed to open pip SVG file! Path: " + f.getName());
            return null;
        }
        if (null == doc) {
            MegaMekLab.getLogger().error(PrintRecordSheet.class, METHOD_NAME,
                    "Failed to open pip SVG file! Path: " + f.getName());
            return null;
        }
        return doc.getElementsByTagName(SVGConstants.SVG_PATH_TAG);
    }
    
    // Mech armor and structure pips require special handling for rear armor and superheavy head armor/IS
    @Override
    protected void drawArmorStructurePips() {
        final String FORMAT = "( %d )";
        Element element;
        boolean structComplete = (mech instanceof BipedMech) && loadISPips();
        for (int loc = 0; loc < mech.locations(); loc++) {
            boolean frontComplete = false;
            boolean rearComplete = false;
            if (mech.isSuperHeavy() && (loc == Mech.LOC_HEAD)) {
                element = getSVGDocument().getElementById(ARMOR_PIPS + mech.getLocationAbbr(loc) + "_SH");
            } else {
                // For consistency, only use the canon pip layout on non-superheavies.
                // Otherwise superheavies may get a mix of pattern types.
                if (!mech.isSuperHeavy() && (mech instanceof BipedMech)) {
                    frontComplete = loadArmorPips(loc, false);
                    rearComplete = !mech.hasRearArmor(loc) || loadArmorPips(loc, true);
                    if (frontComplete && rearComplete) {
                        continue;
                    }
                }
                element = getSVGDocument().getElementById(ARMOR_PIPS + mech.getLocationAbbr(loc));
            }
            if ((null != element) && !frontComplete) {
                addPips(element, mech.getOArmor(loc),
                        (loc == Mech.LOC_HEAD) || (loc == Mech.LOC_CT) || (loc == Mech.LOC_CLEG),
                        PipType.forAT(mech.getArmorType(loc)));
                //                        setArmorPips(element, mech.getOArmor(loc), true);
                //                      (loc == Mech.LOC_HEAD) || (loc == Mech.LOC_CT));
            }
            if ((loc > Mech.LOC_HEAD) && !structComplete) {
                element = getSVGDocument().getElementById(IS_PIPS + mech.getLocationAbbr(loc));
                if (null != element) {
                    addPips(element, mech.getOInternal(loc),
                            (loc == Mech.LOC_CT) || (loc == Mech.LOC_CLEG));
                }
            }
            if (mech.hasRearArmor(loc) && !rearComplete) {
                element = getSVGDocument().getElementById(TEXT_ARMOR + mech.getLocationAbbr(loc) + "R");
                if (null != element) {
                    element.setTextContent(String.format(FORMAT, mech.getOArmor(loc, true)));
                }
                element = getSVGDocument().getElementById(ARMOR_PIPS + mech.getLocationAbbr(loc) + "R");
                if (null != element) {
                    addPips(element, mech.getOArmor(loc, true), loc == Mech.LOC_CT,
                            PipType.forAT(mech.getArmorType(loc)));
                }
            }
            
        }
        if (mech.isSuperHeavy()) {
            element = getSVGDocument().getElementById(IS_PIPS_HD);
            if (null != element) {
                hideElement(element, true);
            }
            element = getSVGDocument().getElementById(IS_PIPS_HD_SH);
            if (null != element) {
                hideElement(element, false);
            }
        }
    }
    
    @Override
    protected boolean isCenterlineLocation(int loc) {
        return (loc == Mech.LOC_HEAD)
                || (loc == Mech.LOC_CT)
                || (loc == Mech.LOC_CLEG);
    }
    
    @Override
    protected void writeEquipment(SVGRectElement svgRect) {
        Map<Integer, Map<RecordSheetEquipmentLine,Integer>> eqMap = new TreeMap<>();
        Map<String,Integer> ammo = new TreeMap<>();
        for (Mounted m : mech.getEquipment()) {
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
                    || !UnitUtil.isPrintableEquipment(m.getType(), true)) {
                continue;
            }
            if (mech.hasETypeFlag(Entity.ETYPE_QUADVEE)
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
        addTextElement(canvas, heatX, currY, "Ht", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
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
                if ((rows == 1) && (getTextLength(line.getNameField(0, mech.isMixedTech()), fontSize) > locX - nameX)) {
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
                                line.getNameField(row, mech.isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);

                    } else {
                        lines = addMultilineTextElement(canvas, nameX + indent, currY, locX - nameX - indent, lineHeight,
                                line.getNameField(row, mech.isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    }
                    addTextElement(canvas, locX,  currY, line.getLocationField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, heatX, currY, line.getHeatField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
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
        
        String quirksText = formatQuirks();

        if ((ammo.size() > 0) || (quirksText.length() > 0)) {
            Element svgGroup = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            canvas.appendChild(svgGroup);
            lines = 0; 
            if (ammo.size() > 0) {
                lines = addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, 0, viewWidth * 0.95, lineHeight,
                        "Ammo: " + ammo.entrySet().stream()
                        .map(e -> String.format("(%s) %d", e.getKey(), e.getValue()))
                        .collect(Collectors.joining(", ")), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
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
    
    private void writeLocationCriticals(int loc, SVGRectElement svgRect) {
        Rectangle2D bbox = getRectBBox(svgRect);
        Element canvas = (Element) svgRect.getParentNode();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();

        double critX = viewX + viewWidth * 0.11;
        double critWidth = viewX + viewWidth - critX;
        double gap = 0;
        if (mech.getNumberOfCriticals(loc) > 6) {
            gap = viewHeight * 0.05;
        }
        double lineHeight = (viewHeight - gap) / mech.getNumberOfCriticals(loc);
        double currY = viewY;
        float fontSize = (float) Math.floor(lineHeight * 0.85f);
        
        Mounted startingMount = null;
        double startingMountY = 0;
        double endingMountY = 0;
        double connWidth = viewWidth * 0.02;
        
        double x = viewX + viewWidth * 0.075;
        x += addTextElement(canvas, x, viewY - 1, mech.getLocationName(loc),
                fontSize * 1.25f, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        if (mech.isClan() && UnitUtil.hasAmmo(mech, loc) && !mech.hasCASEII(loc)) {
            addTextElement(canvas, x + fontSize / 2, viewY - 1, "(CASE)", fontSize,
                    SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
        }
        
        for (int slot = 0; slot < mech.getNumberOfCriticals(loc); slot++) {
            currY += lineHeight;
            if (slot == 6) {
                currY += gap;
            }
            addTextElement(canvas, viewX, currY, ((slot % 6) + 1) + ".", fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
            CriticalSlot crit = mech.getCritical(loc, slot);
            String style = SVGConstants.SVG_BOLD_VALUE;
            String fill = FILL_BLACK;
            if ((null == crit)
                    || ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                            && (!crit.getMount().getType().isHittable()))) {
                style = SVGConstants.SVG_NORMAL_VALUE;
                fill = FILL_GREY;
                addTextElementToFit(canvas, critX, currY, critWidth, formatCritName(crit), fontSize, SVGConstants.SVG_START_VALUE, style, fill);
            } else if (crit.isArmored()) {
                Element pip = createPip(critX, currY - fontSize * 0.8, fontSize * 0.4, 0.7);
                canvas.appendChild(pip);
                addTextElement(canvas, critX + fontSize, currY, formatCritName(crit), fontSize, SVGConstants.SVG_START_VALUE, style, fill);
            } else if ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                    && (crit.getMount().getType() instanceof MiscType)
                    && (crit.getMount().getType().hasFlag(MiscType.F_MODULAR_ARMOR))) {
                String critName = formatCritName(crit);
                addTextElement(canvas, critX, currY, critName, fontSize, SVGConstants.SVG_START_VALUE, style, fill);
                x = critX + getTextLength(critName, fontSize);
                double remainingW = viewX + viewWidth - x;
                double spacing = remainingW / 6.0;
                double radius = spacing * 0.25;
                double y = currY - lineHeight + spacing;
                double y2 = currY - spacing;
                x += spacing;
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        x -= spacing * 5.5;
                        y = y2;
                    }
                    Element pip = createPip(x, y, radius, 0.5);
                    canvas.appendChild(pip);
                    x += spacing;
                }
            } else {
                addTextElement(canvas, critX, currY, formatCritName(crit), fontSize,
                        SVGConstants.SVG_START_VALUE, style, fill);
            }
            Mounted m = null;
            if ((null != crit) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                    && (crit.getMount().getType().isHittable())
                    && (crit.getMount().getType().getCriticals(mech) > (mech.isSuperHeavy()? 2 : 1))) {
                m = crit.getMount();
            }
            if ((startingMount != null) && (startingMount != m)) {
                connectSlots(canvas, critX - 1, startingMountY, connWidth, endingMountY - startingMountY);
            }
            if (m != startingMount) {
                startingMount = m;
                if (null != m) {
                    startingMountY = currY - lineHeight * 0.6;
                }
            } else {
                endingMountY = currY;
            }
        }
        if ((null != startingMount) && (mech.getNumberOfCriticals(startingMount.getType(), loc) > 1)) {
            connectSlots(canvas, critX - 1, startingMountY, connWidth, endingMountY - startingMountY);
        }
    }

    private void connectSlots(Element canvas, double x, double y, double w,
            double h) {
        Element p = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        p.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE,
                "M " + x + " " + y
                + " h " + (-w)
                + " v " + h
                + " h " + w);
        p.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
        p.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, "0.72");
        p.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
        canvas.appendChild(p);
    }
    
    @Override
    protected void drawFluffImage() {
        Element rect;
        if (mech.getCrew().getSlotCount() == 3) {
            rect = getSVGDocument().getElementById(FLUFF_TRIPLE_PILOT);
        } else if (mech.getCrew().getSlotCount() == 2) {
            rect = getSVGDocument().getElementById(FLUFF_DUAL_PILOT);
        } else {
            rect = getSVGDocument().getElementById(FLUFF_SINGLE_PILOT);
        }
        if (rect instanceof SVGRectElement) {
            embedImage(ImageHelper.getFluffFile(mech, ImageHelper.imageMech),
                    (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
        }
    }

    private void drawHeatSinkPips(SVGRectElement svgRect) {
        Rectangle2D bbox = getRectBBox(svgRect);
        Element canvas = (Element) svgRect.getParentNode();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();

        int hsCount = mech.heatSinks();

        // r = 3.5
        // spacing = 9.66
        // stroke width = 0.9
        double size = 9.66;
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

    private void drawSIPips(SVGRectElement svgRect) {
        Rectangle2D bbox = getRectBBox(svgRect);
        Element canvas = (Element) svgRect.getParentNode();
        int viewWidth = (int) bbox.getWidth();
        int viewHeight = (int) bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();

        int si = mech.getOInternal(Mech.LOC_CT);

        double size = 9.2;
        double radius = 2.8;
        int width = (int) (viewWidth / size);
        double strokeWidth = 1.72;
        int row1 = Math.min(si, width);
        int row2 = Math.max(0, si - width);

        double xpos = viewX + (viewWidth - size * row1) * 0.5 + size * 0.5 - radius;
        double ypos = viewY + (viewHeight - size * 2) * 0.5 + size * 0.5 - radius;
        for (int p = 0; p < si; p++) {
            if (p == width) {
                xpos = viewX + (viewWidth - size * row2 + size - radius * 2) * 0.5;
                ypos += viewHeight * 0.5;
            }
            final Element pip = createPip(xpos, ypos, radius, strokeWidth);
            canvas.appendChild(pip);
            xpos += size;
        }
    }

    @Override
    protected String formatWalk() {
        if (mech.hasTSM()) {
            return mech.getWalkMP() + " [" + (mech.getWalkMP() + 1) + "]";
        } else {
            return Integer.toString(mech.getWalkMP());
        }
    }
    
    @Override
    protected String formatRun() {
        int mp = mech.getWalkMP();
        if (mech.hasTSM()) {
            mp++;
        }
        if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            mp = (int) Math.ceil(mp * 2.5);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            mp *= 2;
        } else {
            mp = (int) Math.ceil(mp * 1.5);
        }
        if (mech.hasMPReducingHardenedArmor()) {
            mp--;
        }
        if (mp > mech.getRunMPwithoutMASC()) {
            return mech.getRunMPwithoutMASC() + " [" + mp + "]";
        } else {
            return Integer.toString(mech.getRunMP());
        }
    }
    
    private String formatQuadVeeFlank() {
        int mp = ((QuadVee) mech).getCruiseMP(false, false, false);
        int noSupercharger = (int) Math.ceil(mp * 1.5);
        if (mech.getSuperCharger() != null) {
            mp *= 2;
        } else {
            mp = noSupercharger;
        }
        if (mech.hasMPReducingHardenedArmor()) {
            mp--;
            noSupercharger--;
        }
        if (mp > noSupercharger) {
            return noSupercharger + " [" + mp + "]";
        } else {
            return Integer.toString(mp);
        }
    }
    
    @Override
    protected String formatJump() {
        if (mech.hasUMU()) {
            return Integer.toString(mech.getActiveUMUCount());
        } else {
            return Integer.toString(mech.getJumpMP());
        }
    }

    private String formatHeatSinkType() {
        if (mech.hasLaserHeatSinks()) {
            return "Laser Heat Sinks:";
        } else if (mech.hasDoubleHeatSinks()) {
            return "Double Heat Sinks:";
        } else {
            return "Heat Sinks:";
        }
    }
    
    private String formatHeatSinkCount() {
        int hsCount = mech.heatSinks();
        if (mech.hasDoubleHeatSinks()) {
            return String.format("%d (%d)", hsCount, hsCount * 2);
        } else {
            return Integer.toString(hsCount);
        }
    }
    
    private String formatCritName(CriticalSlot cs) {
        if (null == cs) {
            return "Roll Again";
        } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
            if (cs.getIndex() == Mech.SYSTEM_ENGINE) {
                StringBuilder sb = new StringBuilder();
                if (mech.isPrimitive()) {
                    sb.append("Primitive ");
                }
                switch (mech.getEngine().getEngineType()) {
                    case Engine.COMBUSTION_ENGINE:
                        sb.append("I.C.E."); //$NON-NLS-1$
                        break;
                    case Engine.NORMAL_ENGINE:
                        sb.append("Fusion"); //$NON-NLS-1$
                        break;
                    case Engine.XL_ENGINE:
                        sb.append("XL Fusion"); //$NON-NLS-1$
                        break;
                    case Engine.LIGHT_ENGINE:
                        sb.append("Light Fusion"); //$NON-NLS-1$
                        break;
                    case Engine.XXL_ENGINE:
                        sb.append("XXL Fusion"); //$NON-NLS-1$
                        break;
                    case Engine.COMPACT_ENGINE:
                        sb.append("Compact Fusion"); //$NON-NLS-1$
                        break;
                    case Engine.FUEL_CELL:
                        sb.append("Fuel Cell"); //$NON-NLS-1$
                        break;
                    case Engine.FISSION:
                        sb.append("Fission"); //$NON-NLS-1$
                        break;
                }
                sb.append(" Engine");
                return sb.toString();
            } else {
                String name = mech.getSystemName(cs.getIndex());
                if (((cs.getIndex() >= Mech.ACTUATOR_UPPER_ARM) && (cs.getIndex() <= Mech.ACTUATOR_HAND))
                        || ((cs.getIndex() >= Mech.ACTUATOR_UPPER_LEG) && (cs.getIndex() <= Mech.ACTUATOR_FOOT))) {
                    name += " Actuator";
                } else if (cs.getIndex() == Mech.SYSTEM_COCKPIT) {
                    if (mech.getCockpitType() == Mech.COCKPIT_COMMAND_CONSOLE) {
                        if (mech.getCrewForCockpitSlot(Mech.LOC_HEAD, cs) == 0) {
                            name = EquipmentMessages.getString("SystemType.Cockpit.COCKPIT_STANDARD");
                        }
                    } else if ((mech.getCockpitType() == Mech.COCKPIT_DUAL)
                            || (mech.getCockpitType() == Mech.COCKPIT_QUADVEE)) {
                        name = mech.getCrew().getCrewType().getRoleName(mech.getCrewForCockpitSlot(Mech.LOC_HEAD, cs));
                    }
                }
                assert (name != null);
                return name.replace("Standard ", "");
            }
        } else {
            Mounted m = cs.getMount();
            StringBuffer critName = new StringBuffer(m.getType().getShortName());
            if (mech.isMixedTech()) {
                if (mech.isClan() && (m.getType().getTechBase() == ITechnology.TECH_BASE_IS)
                        && (critName.indexOf("[IS]") < 0)) {
                    critName.append(" [IS]");
                } else if (!mech.isClan() && m.getType().isClan()
                        && (critName.indexOf("[Clan]") < 0)) {
                    critName.append(" [Clan]");
                }
            }

            if (UnitUtil.isTSM(m.getType())) {
                critName.setLength(0);
                critName.append("Triple-Strength Myomer");
            }

            if (m.isRearMounted()) {
                critName.append(" (R)");
            } else if (m.isMechTurretMounted()) {
                critName.append(" (T)");
            } else if ((m.getType() instanceof AmmoType)
                    && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                AmmoType ammo = (AmmoType) m.getType();

                critName = new StringBuffer("Ammo (");
                appendAmmoCritName(critName, ammo);
                critName.append(") ");
                int shots = m.getBaseShotsLeft();
                if ((cs.getMount2() != null) && (cs.getMount2().getType() instanceof AmmoType)) {
                    shots += cs.getMount2().getBaseShotsLeft();
                }
                critName.append(shots);
            } else if ((cs.getMount2() != null)
                    && (m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_HEAT_SINK))
                    && (m.getType() == cs.getMount2().getType())) {
                critName.insert(0, "2 ").append("s");
            } else if ((cs.getMount2() != null)
                    && (m.getType() instanceof MiscType) && (m.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK))
                    && (cs.getMount2().getType() instanceof MiscType) && (cs.getMount2().getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK))) {
                int hs = 2;
                if (m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                    hs++;
                }
                if (cs.getMount2().getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                    hs++;
                }
                critName.replace(0, 1, Integer.toString(hs));
            }
            if (!mech.isMixedTech()) {
                int startPos = critName.indexOf("[Clan]");
                if (startPos < 0) {
                    startPos = critName.indexOf("(Clan)");
                }
                if (startPos >= 0) {
                    critName.delete(startPos, startPos + "[Clan]".length());
                    critName.trimToSize();
                }
            }
            return critName.toString();
        }
    }
    
    private void appendAmmoCritName(StringBuffer buffer, AmmoType ammo) {
        // Remove Text (Clan) from the name
        buffer.append(ammo.getShortName().replace('(', '.').replace(')', '.').replaceAll(".Clan.", "").trim());
        // Remove any additional Ammo text.
        if (buffer.toString().endsWith("Ammo")) {
            buffer.setLength(buffer.length() - 5);
            buffer.trimToSize();
        }
        
        String submunitionName = ammo.getSubMunitionName().replace("(Clan) ", "");
        int index = buffer.indexOf(submunitionName);
        if (index >= 0) {
            buffer.delete(index, index + submunitionName.length());
            buffer.trimToSize();
        }

        // Trim trailing spaces.
        while (buffer.charAt(buffer.length() - 1) == ' ') {
            buffer.setLength(buffer.length() - 1);
        }
        buffer.trimToSize();
    }
}
