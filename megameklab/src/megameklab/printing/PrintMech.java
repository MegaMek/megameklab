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

import megamek.common.Entity;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.MiscMounted;
import megameklab.printing.reference.*;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import megameklab.util.UnitUtil;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.dom.util.SAXDocumentFactory;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.*;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Lays out a record sheet for a Mek
 * 
 * @author Neoancient
 */
public class PrintMech extends PrintEntity {

    /**
     * The current Mek being printed.
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
    
    @Override
    protected String getSVGFileName(int pageNumber) {
        String base;
        if (mech.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            base = "mech_quadvee";
        } else if (mech.hasETypeFlag(Entity.ETYPE_QUAD_MECH)) {
            base = "mech_quad";
        } else if (mech.hasETypeFlag(Entity.ETYPE_TRIPOD_MECH)) {
            base = "mech_tripod";
        } else if (mech.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            base = "mech_lam";
        } else {
            base = "mech_biped";
        }
        if (options.useTacOpsHeat()) {
            return base + "_toheat.svg";
        } else {
            return base + "_default.svg";
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
    public Entity getEntity() {
        return mech;
    }
    
    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        printShields();
        
        super.processImage(pageNum, pageFormat);

        for (int loc = 0; loc < mech.locations(); loc++) {
            Element critRect = getSVGDocument().getElementById(CRITS + mech.getLocationAbbr(loc));
            if (critRect instanceof SVGRectElement) {
                writeLocationCriticals(loc, (SVGRectElement) critRect);
            }
        }
        
        hideElement(HEAVY_DUTY_GYRO_PIP, mech.getGyroType() != Mech.GYRO_HEAVY_DUTY);
        
        Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
        if (hsRect instanceof SVGRectElement) {
            drawHeatSinkPips((SVGRectElement) hsRect, mech.heatSinks());
        }

        if (mech.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            Element si = getSVGDocument().getElementById(SI_PIPS);
            if (si instanceof SVGRectElement) {
                drawSIPips((SVGRectElement) si);
            } else {
                LogManager.getLogger().error("Region siPips does not exist in template or is not a <rect>");
            }
        }
        
    }
    
    private void printShields() {
        for (MiscMounted m : mech.getMisc()) {
            if (m.getType().isShield()) {
                String loc = mech.getLocationAbbr(m.getLocation());
                Element element = getSVGDocument().getElementById(ARMOR_DIAGRAM + loc);
                if (null != element) {
                    hideElement(element, true);
                }
                element = getSVGDocument().getElementById(SHIELD + loc);
                if (null != element) {
                    hideElement(element, false);
                }
                element = getSVGDocument().getElementById(SHIELD_DC + loc);
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, m.getCurrentDamageCapacity(mech, m.getLocation()),
                            PipType.CIRCLE);
                }
                element = getSVGDocument().getElementById(SHIELD_DA + loc);
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, m.getDamageAbsorption(mech, m.getLocation()),
                            PipType.DIAMOND);
                }
            }
        }
    }

    @Override
    protected void writeTextFields() {
        hideUnusedCrewElements();
        super.writeTextFields();
        if (mech.hasUMU()) {
            Element svgEle = getSVGDocument().getElementById(LBL_JUMP);
            if (null != svgEle) {
                svgEle.setTextContent("Underwater:");
            }
        }

        setTextField(HS_TYPE, formatHeatSinkType());
        setTextField(HS_COUNT, formatHeatSinkCount());
        setTextField(MINUS_9_MP, formatHeatMovementPenalty(9));
        setTextField(MINUS_8_MP, formatHeatMovementPenalty(8));
        setTextField(MINUS_7_MP, formatHeatMovementPenalty(7));
        setTextField(MINUS_6_MP, formatHeatMovementPenalty(6));
        setTextField(MINUS_5_MP, formatHeatMovementPenalty(5));
        setTextField(MINUS_4_MP, formatHeatMovementPenalty(4));
        setTextField(MINUS_3_MP, formatHeatMovementPenalty(3));
        setTextField(MINUS_2_MP, formatHeatMovementPenalty(2));
        setTextField(MINUS_1_MP, formatHeatMovementPenalty(1));
        if (mech.hasWorkingMisc(MiscType.F_PARTIAL_WING)) {
            hideElement(PARTIAL_WING_BONUS, false);
        }
        
        if (mech instanceof LandAirMech) {
            LandAirMech lam = (LandAirMech) mech;
            if (lam.getLAMType() == LandAirMech.LAM_BIMODAL) {
                setTextField(MP_AIRMECH_WALK, "\u2014"); // em dash
                setTextField(MP_AIRMECH_RUN, "\u2014");
                setTextField(MP_AIRMECH_CRUISE, "\u2014");
                setTextField(MP_AIRMECH_FLANK, "\u2014");
            } else {
                setTextField(MP_AIRMECH_WALK, formatMovement(lam.getAirMechWalkMP()));
                setTextField(MP_AIRMECH_RUN, formatMovement(lam.getAirMechWalkMP() * 1.5));
                setTextField(MP_AIRMECH_CRUISE, formatMovement(lam.getAirMechCruiseMP()));
                setTextField(MP_AIRMECH_FLANK, formatMovement(lam.getAirMechCruiseMP() * 1.5));
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
            setTextField(MP_CRUISE, formatMovement(((QuadVee) mech).getCruiseMP(MPCalculationSetting.STANDARD)));
            setTextField(MP_FLANK, formatQuadVeeFlank());
            setTextField(LBL_VEE_MODE, ((QuadVee) mech).getMotiveTypeString() + "s");
        }
        setTextField(ENGINE_TYPE, mech.getEngine().getShortEngineName()
                .replaceAll("\\[.*]", "").trim());
    }

    @Override
    protected void hideUnusedCrewElements() {
        super.hideUnusedCrewElements();
        hideElement(WARRIOR_DATA_SINGLE, getEntity().getCrew().getSlotCount() != 1);
        hideElement(WARRIOR_DATA_DUAL, getEntity().getCrew().getSlotCount() != 2);
        hideElement(WARRIOR_DATA_TRIPLE, getEntity().getCrew().getSlotCount() != 3);
    }

    @Override
    protected void drawStructure() {
        if (mech.getStructureType() != EquipmentType.T_STRUCTURE_STANDARD) {
            setTextField(STRUCTURE_TYPE, EquipmentType.getStructureTypeName(mech.getStructureType()));
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
        File f = new File(filename);
        if (!f.exists()) {
            return null;
        }
        Document doc;
        try (InputStream is = new FileInputStream(f)) {
            DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
            final String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXDocumentFactory df = new SAXDocumentFactory(impl, parser);
            doc = df.createDocument(f.toURI().toASCIIString(), is);
        } catch (Exception e) {
            LogManager.getLogger().error("Failed to open pip SVG file! Path: " + f.getName());
            return null;
        }

        if (doc == null) {
            LogManager.getLogger().error("Failed to open pip SVG file! Path: " + f.getName());
            return null;
        } else {
            return doc.getElementsByTagName(SVGConstants.SVG_PATH_TAG);
        }
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
                ArmorPipLayout.addPips(this, element, mech.getOArmor(loc),
                        PipType.forAT(mech.getArmorType(loc)));

            }
            if ((loc > Mech.LOC_HEAD) && !structComplete) {
                element = getSVGDocument().getElementById(IS_PIPS + mech.getLocationAbbr(loc));
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, mech.getOInternal(loc));
                }
            }
            if (mech.hasRearArmor(loc) && !rearComplete) {
                element = getSVGDocument().getElementById(TEXT_ARMOR + mech.getLocationAbbr(loc) + "R");
                if (null != element) {
                    element.setTextContent(String.format(FORMAT, mech.getOArmor(loc, true)));
                }
                element = getSVGDocument().getElementById(ARMOR_PIPS + mech.getLocationAbbr(loc) + "R");
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, mech.getOArmor(loc, true),
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
        int startingSlotIndex = 0;
        double startingMountY = 0;
        double endingMountY = 0;
        double connWidth = viewWidth * 0.02;
        
        double x = viewX + viewWidth * 0.075;
        x += addTextElement(canvas, x, viewY - 1, mech.getLocationName(loc),
                fontSize * 1.25f, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        if ((mech.isClan() && UnitUtil.hasAmmo(mech, loc))
                || (!mech.isClan() && (mech.hasCASEII(loc) || mech.locationHasCase(loc)))) {
            String text = "(CASE" + (mech.hasCASEII(loc) ? " II)" : ")");
            addTextElement(canvas, x + fontSize / 2, viewY - 1, text, fontSize,
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
                    && (crit.getMount().getType().isHittable())) {
                m = crit.getMount();
            }
            if ((startingMount != null) && (startingMount != m) && (slot - startingSlotIndex > 1)) {
                connectSlots(canvas, critX - 1, startingMountY, connWidth, endingMountY - startingMountY);
            }
            if (m != startingMount) {
                startingMount = m;
                startingSlotIndex = slot;
                if (null != m) {
                    startingMountY = currY - lineHeight * 0.6;
                }
            } else {
                endingMountY = currY;
            }
        }
        // Check whether we need to add a bracket for the last piece of equipment.
        if ((null != startingMount) && (mech.getNumberOfCriticals(loc) - startingSlotIndex > 1)) {
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
            if (options.showCondensedReferenceCharts()) {
                List<ReferenceTable> tables = new ArrayList<>();
                tables.add(new MekLocationAndClusterTable(this));
                // Multi-crew cockpits and LAMs have a larger crew panel that doesn't leave room
                // for two tables so we leave off the punch/kick.
                if ((mech.getCrew().getSlotCount() == 1) && !(mech instanceof LandAirMech)) {
                    tables.add(new PunchKickLocation(this));
                }
                Rectangle2D bbox = getRectBBox((SVGRectElement) rect);
                placeReferenceCharts(tables, rect.getParentNode(), bbox.getX(), bbox.getY(),
                        bbox.getWidth() + 6.0, bbox.getHeight() + 6.0);
            } else {
                embedImage(getFluffImage(),
                        (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
            }
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
        if (mech.hasTSM(false)) {
            return formatMovement(mech.getWalkMP(), mech.getWalkMP() + 1);
        } else {
            return super.formatWalk();
        }
    }

    @Override
    protected String formatRun() {
        double baseRun = mech.getWalkMP();
        double fullRun = baseRun;
        baseRun *= 1.5;
        if (mech.hasTSM(false)) {
            fullRun++;
        }
        if ((mech.getMASC() != null) && (mech.getSuperCharger() != null)) {
            fullRun = (int) Math.ceil(fullRun * 2.5);
        } else if ((mech.getMASC() != null) || (mech.getSuperCharger() != null)) {
            fullRun *= 2;
        } else {
            fullRun *= 1.5;
        }
        if (mech.hasMPReducingHardenedArmor()) {
            baseRun--;
            fullRun--;
        }
        return formatMovement(baseRun, fullRun);
    }
    
    private String formatQuadVeeFlank() {
        double baseFlank = ((QuadVee) mech).getCruiseMP(MPCalculationSetting.STANDARD);
        baseFlank *= 1.5;
        double fullFlank;
        if (mech.getSuperCharger() != null) {
            fullFlank = baseFlank * 2;
        } else {
            fullFlank = baseFlank;
        }
        if (mech.hasMPReducingHardenedArmor()) {
            baseFlank--;
            fullFlank--;
        }
        return formatMovement(baseFlank, fullFlank);
    }
    
    @Override
    protected String formatJump() {
        if (mech.hasUMU()) {
            return formatMovement(mech.getActiveUMUCount());
        } else {
            return super.formatJump();
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

    private String formatHeatMovementPenalty(int penalty) {
        String penaltyString = "-" + CConfig.formatScale(penalty, true) + " Movement";
        if (CConfig.scaleUnits() == RSScale.HEXES) {
            penaltyString += " Points";
        }
        return penaltyString;
    }
    
    private String formatHeatSinkCount() {
        int hsCount = mech.heatSinks();
        int capacity = mech.getHeatCapacity(true, false);
        if (hsCount != capacity) {
            return String.format("%d (%d)", hsCount, capacity);
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
                        sb.append("I.C.E.");
                        break;
                    case Engine.NORMAL_ENGINE:
                        sb.append("Fusion");
                        break;
                    case Engine.XL_ENGINE:
                        sb.append("XL Fusion");
                        break;
                    case Engine.LIGHT_ENGINE:
                        sb.append("Light Fusion");
                        break;
                    case Engine.XXL_ENGINE:
                        sb.append("XXL Fusion");
                        break;
                    case Engine.COMPACT_ENGINE:
                        sb.append("Compact Fusion");
                        break;
                    case Engine.FUEL_CELL:
                        sb.append("Fuel Cell");
                        break;
                    case Engine.FISSION:
                        sb.append("Fission");
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
                    Optional<MiscMounted> robotics = mech.getMisc().stream()
                            .filter(m -> m.getType().hasFlag(MiscType.F_SRCS)).findAny();
                    if (robotics.isPresent()) {
                        name = robotics.get().getType().getShortName();
                    } else if (mech.getCockpitType() == Mech.COCKPIT_COMMAND_CONSOLE) {
                        if (mech.getCrewForCockpitSlot(Mech.LOC_HEAD, cs) == 0) {
                            name = EquipmentMessages.getString("SystemType.Cockpit.COCKPIT_STANDARD");
                        }
                    } else if ((mech.getCockpitType() == Mech.COCKPIT_DUAL)
                            || (mech.getCockpitType() == Mech.COCKPIT_QUADVEE)) {
                        name = mech.getCrew().getCrewType().getRoleName(mech.getCrewForCockpitSlot(Mech.LOC_HEAD, cs));
                    }
                }
                Objects.requireNonNull(name);
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
                critName.append(m.getType().getName());
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
                    && (m.getType().equals(cs.getMount2().getType()))) {
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
            if (UnitUtil.isMineDispenser(m.getType()) || UnitUtil.isRemoteSensorDispenser(m.getType())) {
                critName.append(" (").append(m.getBaseShotsLeft()).append(")");
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
        // Trim trailing spaces.
        while (buffer.charAt(buffer.length() - 1) == ' ') {
            buffer.setLength(buffer.length() - 1);
        }
        buffer.trimToSize();
    }

    @Override
    protected boolean includeReferenceCharts() {
        return options.showReferenceCharts();
    }

    @Override
    protected List<ReferenceTable> getRightSideReferenceTables() {
        List<ReferenceTable> list = new ArrayList<>();
        list.add(new MekHitLocation(this));
        list.add(new GroundToHitMods(this));
        list.add(new PhysicalAttacks(this));
        list.add(new PunchLocation(this));
        list.add(new KickLocation(this));
        list.add(new MekFallTable(this));
        ClusterHitsTable table = new ClusterHitsTable(this);
        if (table.required()) {
            list.add(table);
        }
        return list;
    }

    @Override
    protected void addReferenceCharts(PageFormat pageFormat) {
        super.addReferenceCharts(pageFormat);
        GroundMovementRecord table = new GroundMovementRecord(this, true, true);
        getSVGDocument().getDocumentElement().appendChild(table.createTable(pageFormat.getImageableX(),
                pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
                pageFormat.getImageableWidth() * TABLE_RATIO, pageFormat.getImageableHeight() * 0.2 - 3.0));
    }
}
