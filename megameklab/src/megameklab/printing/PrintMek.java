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

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.MiscMounted;
import megamek.logging.MMLogger;
import megameklab.printing.reference.*;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import megameklab.util.UnitUtil;

/**
 * Lays out a record sheet for a Mek
 *
 * @author Neoancient
 */
public class PrintMek extends PrintEntity {
    private static final MMLogger logger = MMLogger.create(PrintMek.class);

    /**
     * The current Mek being printed.
     */
    private final Mek mek;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param mek       The Mek to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintMek(Mek mek, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.mek = mek;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        String base;
        if (mek.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            base = "mek_quadvee";
        } else if (mek.hasETypeFlag(Entity.ETYPE_QUAD_MEK)) {
            base = "mek_quad";
        } else if (mek.hasETypeFlag(Entity.ETYPE_TRIPOD_MEK)) {
            base = "mek_tripod";
        } else if (mek.hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            base = "mek_lam";
        } else {
            base = "mek_biped";
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
        if (mek.isSuperHeavy()) {
            sb.append("SuperHeavy ");
        }
        if (mek.isPrimitive()) {
            sb.append("Primitive ");
        }
        if ((mek instanceof LandAirMek) && (((LandAirMek) mek).getLAMType() == LandAirMek.LAM_BIMODAL)) {
            sb.append("Bimodal ");
        }
        // Leg configuration
        if (mek.hasETypeFlag(Entity.ETYPE_QUAD_MEK)
                && !mek.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            sb.append("Four-Legged ");
        } else if (mek.hasETypeFlag(Entity.ETYPE_TRIPOD_MEK)) {
            sb.append("Three-Legged ");
        }
        // mek type
        if (mek.hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            sb.append("Land-Air 'Mech");
        } else if (mek.hasETypeFlag(Entity.ETYPE_QUADVEE)) {
            if (mek.isOmni()) {
                sb.append("Omni");
            }
            sb.append("QuadVee");
        } else if (mek.isIndustrial()) {
            sb.append("IndustrialMech");
        } else if (mek.isOmni()) {
            sb.append("OmniMech");
        } else {
            sb.append("BattleMech");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }

    @Override
    public Entity getEntity() {
        return mek;
    }

    private void applyCoreComponentsCriticalDamage() {
        final int engineHits = getEngineHits();
        for (int i = 1; i <= engineHits; i++) {
            Element el = getSVGDocument().getElementById(ENGINE_HIT + i);
            if (el != null) {
                el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
        }
        final int gyroHits = getGyroHits();
        for (int i = 1; i <= gyroHits; i++) {
            Element el = getSVGDocument().getElementById(GYRO_HIT + i);
            if (el != null) {
                el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
        }
        final int sensorHits = getSensorHits();
        for (int i = 1; i <= sensorHits; i++) {
            Element el = getSVGDocument().getElementById(SENSOR_HIT + i);
            if (el != null) {
                el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
        }
        final int lifeSupportHits = getLifeSupportHits();
        for (int i = 1; i <= lifeSupportHits; i++) {
            Element el = getSVGDocument().getElementById(LIFE_SUPPORT_HIT + i);
            if (el != null) {
                el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
        }
        if (options.showPilotData()) {
            final int totalCrewMembers = getEntity().getCrew().getSlotCount(); 
            for (int crewMemberId = 0; crewMemberId < totalCrewMembers; crewMemberId++) {
                final int crewHits = getEntity().getCrew().getHits(crewMemberId);
                for (int k = 1; k <= crewHits; k++) {
                    final String elementId = CREW_HIT + crewMemberId + "_" + k;
                    Element el = getSVGDocument().getElementById(elementId);
                    if (el != null) {
                        el.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, "13pt"); 
                        el.setTextContent("X");
                        el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
                    }
                }
            }
        }
        if (mek.hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            final int avionicsHits = getAvionicsHits();
            for (int i = 1; i <= avionicsHits; i++) {
                Element el = getSVGDocument().getElementById(AVIONICS_HIT + i);
                if (el != null) {
                    el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
                }
            }
            final int landingGearHits = getAvionicsHits();
            for (int i = 1; i <= landingGearHits; i++) {
                Element el = getSVGDocument().getElementById(LANDING_GEAR_HIT + i);
                if (el != null) {
                    el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
                }
            }
        }
    }

    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        printShields();

        super.processImage(pageNum, pageFormat);

        for (int loc = 0; loc < mek.locations(); loc++) {
            Element critRect = getSVGDocument().getElementById(CRITS + mek.getLocationAbbr(loc));
            if (critRect instanceof SVGRectElement) {
                writeLocationCriticals(loc, (SVGRectElement) critRect);
            }
        }

        hideElement(HEAVY_DUTY_GYRO_PIP, mek.getGyroType() != Mek.GYRO_HEAVY_DUTY);

        if (options.showDamage()) {
            applyCoreComponentsCriticalDamage();
        }

        Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
        if (hsRect instanceof SVGRectElement) {
            drawHeatSinkPips((SVGRectElement) hsRect, mek.heatSinks(), getHeatsinkDamage());
        }

        if (mek.hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            Element si = getSVGDocument().getElementById(SI_PIPS);
            if (si instanceof SVGRectElement) {
                drawSIPips((SVGRectElement) si);
            } else {
                logger.error("Region siPips does not exist in template or is not a <rect>");
            }
        }

    }

    private int getShieldDamage(MiscMounted m) {
        if (!options.showDamage()) {
            return 0;
        }
        final int totalShield = m.getDamageAbsorption(mek, m.getLocation());
        final int remainingShield = m.getCurrentDamageCapacity(mek, m.getLocation());
        return totalShield - remainingShield;
    }

    private void printShields() {
        for (MiscMounted m : mek.getMisc()) {
            if (m.getType().isShield()) {
                String loc = mek.getLocationAbbr(m.getLocation());
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
                    ArmorPipLayout.addPips(this, element, m.getDamageAbsorption(mek, m.getLocation()),
                            PipType.CIRCLE, DEFAULT_PIP_STROKE, FILL_WHITE, getShieldDamage(m), useAlternateArmorGrouping());
                }
                element = getSVGDocument().getElementById(SHIELD_DA + loc);
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, m.getDamageAbsorption(mek, m.getLocation()),
                            PipType.DIAMOND, DEFAULT_PIP_STROKE, FILL_WHITE, getShieldDamage(m), useAlternateArmorGrouping());
                }
            }
        }
    }

    @Override
    protected void writeTextFields() {
        hideUnusedCrewElements();
        super.writeTextFields();
        if (mek.hasUMU()) {
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
        if (mek.hasWorkingMisc(MiscType.F_PARTIAL_WING)) {
            hideElement(PARTIAL_WING_BONUS, false);
        }

        if (mek instanceof LandAirMek lam) {
            if (lam.getLAMType() == LandAirMek.LAM_BIMODAL) {
                setTextField(MP_AIRMEK_WALK, "\u2014"); // em dash
                setTextField(MP_AIRMEK_RUN, "\u2014");
                setTextField(MP_AIRMEK_CRUISE, "\u2014");
                setTextField(MP_AIRMEK_FLANK, "\u2014");
            } else {
                setTextField(MP_AIRMEK_WALK, formatMovement(lam.getAirMekWalkMP()));
                setTextField(MP_AIRMEK_RUN, formatMovement(lam.getAirMekWalkMP() * 1.5));
                setTextField(MP_AIRMEK_CRUISE, formatMovement(lam.getAirMekCruiseMP()));
                setTextField(MP_AIRMEK_FLANK, formatMovement(lam.getAirMekCruiseMP() * 1.5));
            }
            setTextField(MP_SAFE_THRUST, Integer.toString(lam.getJumpMP()));
            setTextField(MP_MAX_THRUST, Integer.toString((int) Math.ceil(lam.getJumpMP() * 1.5)));
            if (showPilotInfo() && (lam.getCrew() instanceof LAMPilot)) {
                setTextField(ASF_GUNNERY_SKILL, Integer.toString(((LAMPilot) mek.getCrew()).getGunneryAero()));
                setTextField(ASF_PILOTING_SKILL, Integer.toString(((LAMPilot) mek.getCrew()).getPilotingAero()));
                hideElement(ASF_BLANK_GUNNERY_SKILL, true);
                hideElement(ASF_BLANK_PILOTING_SKILL, true);
            } else {
                hideElement(ASF_GUNNERY_SKILL);
                hideElement(ASF_PILOTING_SKILL);
            }
        } else if (mek instanceof QuadVee) {
            setTextField(MP_CRUISE, formatMovement(((QuadVee) mek).getCruiseMP(MPCalculationSetting.STANDARD)));
            setTextField(MP_FLANK, formatQuadVeeFlank());
            setTextField(LBL_VEE_MODE, ((QuadVee) mek).getMotiveTypeString() + "s");
        }
        setTextField(ENGINE_TYPE, mek.getEngine().getShortEngineName()
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
        if (mek.getStructureType() != EquipmentType.T_STRUCTURE_STANDARD) {
            setTextField(STRUCTURE_TYPE, EquipmentType.getStructureTypeName(mek.getStructureType()));
        }
    }

    private boolean loadArmorPips(int loc, boolean rear) {
        String locAbbr;
        switch (loc) {
            case Mek.LOC_HEAD:
                locAbbr = "Head";
                break;
            case Mek.LOC_RARM:
            case Mek.LOC_LARM:
                locAbbr = mek.getLocationAbbr(loc) + "rm";
                break;
            case Mek.LOC_RLEG:
            case Mek.LOC_LLEG:
                locAbbr = mek.getLocationAbbr(loc) + "eg";
                break;
            default:
                locAbbr = mek.getLocationAbbr(loc);
                break;
        }
        if (rear) {
            locAbbr += "_R";
        }
        if (rear) {
            Element element = getSVGDocument().getElementById(TEXT_ARMOR + mek.getLocationAbbr(loc) + "R");
            if (null != element) {
                element.setTextContent(String.format("( %d )", mek.getOArmor(loc, true)));
            }
        }

        NodeList nl = loadPipSVG(String.format("data/images/recordsheets/biped_pips/Armor_%s_%d_Humanoid.svg",
                locAbbr, mek.getOArmor(loc, rear)));
        if (null == nl) {
            return false;
        }
        return copyPipPattern(nl, CANON_ARMOR_PIPS, getArmorDamage(loc, rear));
    }

    private boolean loadISPips() {
        NodeList nl = loadPipSVG(String.format("data/images/recordsheets/biped_pips/BipedIS%d.svg",
                (int) mek.getWeight()));
        if (null == nl) {
            return false;
        }
        hideElement(STRUCTURE_PIPS);
        return copyPipPattern(nl, CANON_STRUCTURE_PIPS, 0); //TODO: Add damage to IS pips
    }

    private static final Pattern FIRST_MOVETO_PATTERN =
        Pattern.compile("^[Mm]\\s*(-?\\d*\\.?\\d+)\\s*[ ,]?\\s*(-?\\d*\\.?\\d+)");

    private @Nullable Point2D.Float getFirstMoveToCoordinate(Node n) {
        if (!(n instanceof Element)) {
            return null;
        }
        Element el = (Element) n;
        String dAttribute = el.getAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE);
        if (dAttribute == null || dAttribute.trim().isEmpty()) {
            logger.warn("Node has empty or missing 'd' attribute: " + n);
            return null;
        }

        Matcher matcher = FIRST_MOVETO_PATTERN.matcher(dAttribute.trim());
        if (matcher.find()) {
            try {
                float x = Float.parseFloat(matcher.group(1));
                float y = Float.parseFloat(matcher.group(2));
                return new Point2D.Float(x, y);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                logger.warn("Failed to parse coordinates from 'd' attribute: '" + dAttribute + "' - " + e.getMessage());
                return null;
            }
        } else {
            logger.warn("Could not find initial 'moveto' command in 'd' attribute: '" + dAttribute + "'");
            return null;
        }
    }
    
    private boolean copyPipPattern(NodeList nl, String parentName, int damage) {
        Element parent = getSVGDocument().getElementById(parentName);
        if (null == parent) {
            return false;
        }
        // Build temp list for sorting
        List<Node> tempNodes = new ArrayList<>(nl.getLength());
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i) instanceof Element el) {
                tempNodes.add(el);
            }
        }

        // Sorting the nodes by their coordinates in the 'd' attribute
        try {
            tempNodes.sort(Comparator.<Node, Float>comparing(n -> {
                Point2D.Float coord = getFirstMoveToCoordinate(n);
                return (coord != null) ? coord.y : Float.MAX_VALUE; // Primary sort: Y coordinate
            }).thenComparing(n -> {
                Point2D.Float coord = getFirstMoveToCoordinate(n);
                return (coord != null) ? coord.x : Float.MAX_VALUE; // Secondary sort: X coordinate
            }));
        } catch (Exception e) {
            logger.error("Error during sorting of pip nodes using 'd' attribute for parent '" + parentName + "': " + e.getMessage(), e);
        }
        
        // Append nodes and apply damage
        int remainingDamage = damage;
        for (Node node : tempNodes) {
            Element sortedElement = (Element) node;
            if (remainingDamage > 0) {
                remainingDamage--;
                // Set the fill attribute to black for damaged pips
                sortedElement.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
            parent.appendChild(getSVGDocument().importNode(sortedElement, true)); // Final append
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
            logger.error("Failed to open pip SVG file! Path: " + f.getName());
            return null;
        }

        if (doc == null) {
            logger.error("Failed to open pip SVG file! Path: " + f.getName());
            return null;
        }
        return doc.getElementsByTagName(SVGConstants.SVG_PATH_TAG);
    }

    // Mek armor and structure pips require special handling for rear armor and
    // superheavy head armor/IS
    @Override
    protected void drawArmorStructurePips() {
        final String FORMAT = "( %d )";
        boolean alternateMethod = useAlternateArmorGrouping();
        Element element;
        boolean structComplete = !alternateMethod && (mek instanceof BipedMek) && loadISPips();
        for (int loc = 0; loc < mek.locations(); loc++) {
            boolean frontComplete = false;
            boolean rearComplete = false;
            if (mek.isSuperHeavy() && (loc == Mek.LOC_HEAD)) {
                element = getElementById(ARMOR_PIPS + mek.getLocationAbbr(loc) + "_SH");
            } else {
                // For consistency, only use the canon pip layout on non-superheavies.
                // Otherwise superheavies may get a mix of pattern types.
                if (!mek.isSuperHeavy() && (mek instanceof BipedMek) && !alternateMethod) {
                    frontComplete = loadArmorPips(loc, false);
                    rearComplete = !mek.hasRearArmor(loc) || loadArmorPips(loc, true);
                    if (frontComplete && rearComplete) {
                        continue;
                    }
                }
                element = getElementById(ARMOR_PIPS + mek.getLocationAbbr(loc));
            }

            if ((null != element) && !frontComplete) {
                ArmorPipLayout.addPips(this, element, mek.getOArmor(loc),
                        PipType.forAT(mek.getArmorType(loc)), DEFAULT_PIP_STROKE, FILL_WHITE, getArmorDamage(loc, false), alternateMethod);
            }
            if ((loc > Mek.LOC_HEAD) && !structComplete) {
                element = getElementById(IS_PIPS + mek.getLocationAbbr(loc));
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, mek.getOInternal(loc), PipType.CIRCLE, DEFAULT_PIP_STROKE, FILL_WHITE, getStructureDamage(loc), alternateMethod);
                }
            }
            if (mek.hasRearArmor(loc) && !rearComplete) {
                element = getElementById(TEXT_ARMOR + mek.getLocationAbbr(loc) + "R");
                if (null != element) {
                    element.setTextContent(String.format(FORMAT, mek.getOArmor(loc, true)));
                }
                element = getElementById(ARMOR_PIPS + mek.getLocationAbbr(loc) + "R");
                if (null != element) {
                    ArmorPipLayout.addPips(this, element, mek.getOArmor(loc, true),
                            PipType.forAT(mek.getArmorType(loc)), DEFAULT_PIP_STROKE, FILL_WHITE, getArmorDamage(loc, true), alternateMethod);

                }
            }

        }
        if (mek.isSuperHeavy()) {
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
        int viewWidth = (int) bbox.getWidth();
        int viewHeight = (int) bbox.getHeight();
        int viewX = (int) bbox.getX();
        int viewY = (int) bbox.getY();

        double critX = viewX + viewWidth * 0.11;
        double critWidth = viewX + viewWidth - critX;
        double gap = 0;
        if (mek.getNumberOfCriticals(loc) > 6) {
            gap = viewHeight * 0.05;
        }
        double lineHeight = (viewHeight - gap) / mek.getNumberOfCriticals(loc);
        double currY = viewY;
        float fontSize = (float) Math.floor(lineHeight * 0.85f);

        Mounted<?> startingMount = null;
        int startingSlotIndex = 0;
        double startingMountY = 0;
        double endingMountY = 0;
        double connWidth = viewWidth * 0.02;

        double x = viewX + viewWidth * 0.075;
        x += addTextElement(canvas, x, viewY - 1, mek.getLocationName(loc),
                fontSize * 1.25f, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        if ((mek.isClan() && UnitUtil.hasAmmo(mek, loc))
                || (!mek.isClan() && (mek.hasCASEII(loc) || mek.locationHasCase(loc)))) {
            String text = "(CASE" + (mek.hasCASEII(loc) ? " II)" : ")");
            addTextElement(canvas, x + fontSize / 2, viewY - 1, text, fontSize,
                    SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
        }

        for (int slot = 0; slot < mek.getNumberOfCriticals(loc); slot++) {
            currY += lineHeight;
            if (slot == 6) {
                currY += gap;
            }
            addTextElement(canvas, viewX, currY, ((slot % 6) + 1) + ".", fontSize, SVGConstants.SVG_START_VALUE,
                    SVGConstants.SVG_BOLD_VALUE);
            CriticalSlot crit = mek.getCritical(loc, slot);
            String style = SVGConstants.SVG_BOLD_VALUE;
            String fill = FILL_BLACK;
            if ((null == crit)
                    || ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                            && (!crit.getMount().getType().isHittable()))) {
                style = SVGConstants.SVG_NORMAL_VALUE;
                fill = FILL_GREY;
                addTextElementToFit(canvas, critX, currY, critWidth, formatCritName(crit), fontSize,
                        SVGConstants.SVG_START_VALUE, style, fill);
            } else if (crit.isArmored()) {
                int damage = 0;
                final boolean isDamagedPip = damage > 0;
                if (damage > 0) {
                    damage--;
                }
                Element pip = createPip(critX, currY - fontSize * 0.8, fontSize * 0.4, 0.7, PipType.CIRCLE, isDamagedPip ? getDamageFillColor() : FILL_WHITE);
                canvas.appendChild(pip);
                addTextElement(canvas, critX + fontSize, currY, formatCritName(crit), fontSize,
                        SVGConstants.SVG_START_VALUE, style, fill);
            } else if ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                    && (crit.getMount().getType() instanceof MiscType)
                    && (crit.getMount().getType().hasFlag(MiscType.F_MODULAR_ARMOR))) {
                String critName = formatCritName(crit);
                final boolean isDamaged = options.showDamage() && crit.isDamaged();
                addTextElement(canvas, critX, currY, critName, fontSize, SVGConstants.SVG_START_VALUE, style, fill, isDamaged);
                x = critX + getTextLength(critName, fontSize);
                double remainingW = viewX + viewWidth - x;
                double spacing = remainingW / 6.0;
                double radius = spacing * 0.25;
                double y = currY - lineHeight + spacing;
                double y2 = currY - spacing;
                x += spacing;
                int damage = 0;
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        x -= spacing * 5.5;
                        y = y2;
                    }
                    final boolean isDamagedPip = damage > 0;
                    if (damage > 0) {
                        damage--;
                    }
                    Element pip = createPip(x, y, radius, 0.5, PipType.CIRCLE, isDamagedPip ? getDamageFillColor() : FILL_WHITE);
                    canvas.appendChild(pip);
                    x += spacing;
                }
            } else {
                final boolean isDamaged = options.showDamage() && crit.isDamaged();
                addTextElement(canvas, critX, currY, formatCritName(crit), fontSize,
                        SVGConstants.SVG_START_VALUE, style, fill, isDamaged);
            }
            Mounted<?> m = null;
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
        if ((null != startingMount) && (mek.getNumberOfCriticals(loc) - startingSlotIndex > 1)) {
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
        if (mek.getCrew().getSlotCount() == 3) {
            rect = getSVGDocument().getElementById(FLUFF_TRIPLE_PILOT);
        } else if (mek.getCrew().getSlotCount() == 2) {
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
                if ((mek.getCrew().getSlotCount() == 1) && !(mek instanceof LandAirMek)) {
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
        int viewX = (int) bbox.getX();
        int viewY = (int) bbox.getY();

        int si = mek.getOInternal(Mek.LOC_CT);
        int damage = 0;
        if (options.showDamage()) {
            damage = si - mek.getInternal(Mek.LOC_CT);
        }
        double size = 9.2;
        double radius = 2.8;
        int width = (int) (viewWidth / size);
        double strokeWidth = 1.72;
        int row1 = Math.min(si, width);
        int row2 = Math.max(0, si - width);

        double xPosition = viewX + (viewWidth - size * row1) * 0.5 + size * 0.5 - radius;
        double yPosition = viewY + (viewHeight - size * 2) * 0.5 + size * 0.5 - radius;
        for (int p = 0; p < si; p++) {
            if (p == width) {
                xPosition = viewX + (viewWidth - size * row2 + size - radius * 2) * 0.5;
                yPosition += viewHeight * 0.5;
            }
            final boolean isDamaged = damage > 0;
            if (damage > 0) {
                damage--;
            }
            final Element pip = createPip(xPosition, yPosition, radius, strokeWidth, PipType.CIRCLE, isDamaged ? getDamageFillColor() : FILL_WHITE);
            canvas.appendChild(pip);
            xPosition += size;
        }
    }

    @Override
    protected String formatWalk() {
        if (mek.hasTSM(false)) {
            return formatMovement(mek.getWalkMP(), mek.getWalkMP() + 1);
        } else {
            return super.formatWalk();
        }
    }

    @Override
    protected String formatRun() {
        double baseRun = mek.getWalkMP();
        double fullRun = baseRun;
        baseRun *= 1.5;
        if (mek.hasTSM(false)) {
            fullRun++;
        }
        if ((mek.getMASC() != null) && (mek.getSuperCharger() != null)) {
            fullRun = (int) Math.ceil(fullRun * 2.5);
        } else if ((mek.getMASC() != null) || (mek.getSuperCharger() != null)) {
            fullRun *= 2;
        } else {
            fullRun *= 1.5;
        }
        if (mek.hasMPReducingHardenedArmor()) {
            baseRun--;
            fullRun--;
        }
        return formatMovement(baseRun, fullRun);
    }

    private String formatQuadVeeFlank() {
        double baseFlank = ((QuadVee) mek).getCruiseMP(MPCalculationSetting.STANDARD);
        baseFlank *= 1.5;
        double fullFlank;
        if (mek.getSuperCharger() != null) {
            fullFlank = baseFlank * 2;
        } else {
            fullFlank = baseFlank;
        }
        if (mek.hasMPReducingHardenedArmor()) {
            baseFlank--;
            fullFlank--;
        }
        return formatMovement(baseFlank, fullFlank);
    }

    @Override
    protected String formatJump() {
        if (mek.hasUMU()) {
            return formatMovement(mek.getActiveUMUCount());
        } else {
            return super.formatJump();
        }
    }

    @Override
    protected boolean supportsAlternateArmorGrouping() {
        return true;
    }

    private String formatHeatSinkType() {
        if (mek.hasLaserHeatSinks()) {
            return "Laser Heat Sinks:";
        } else if (mek.hasDoubleHeatSinks()) {
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
        int hsCount = mek.heatSinks();
        int capacity = mek.getHeatCapacity(true, false);
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
            if (cs.getIndex() == Mek.SYSTEM_ENGINE) {
                StringBuilder sb = new StringBuilder();
                if (mek.isPrimitive()) {
                    sb.append("Primitive ");
                }
                switch (mek.getEngine().getEngineType()) {
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
                String name = mek.getSystemName(cs.getIndex());
                if (((cs.getIndex() >= Mek.ACTUATOR_UPPER_ARM) && (cs.getIndex() <= Mek.ACTUATOR_HAND))
                        || ((cs.getIndex() >= Mek.ACTUATOR_UPPER_LEG) && (cs.getIndex() <= Mek.ACTUATOR_FOOT))) {
                    name += " Actuator";
                } else if (cs.getIndex() == Mek.SYSTEM_COCKPIT) {
                    Optional<MiscMounted> robotics = mek.getMisc().stream()
                            .filter(m -> m.getType().hasFlag(MiscType.F_SRCS)).findAny();
                    if (robotics.isPresent()) {
                        name = robotics.get().getType().getShortName();
                    } else if (mek.getCockpitType() == Mek.COCKPIT_COMMAND_CONSOLE) {
                        if (mek.getCrewForCockpitSlot(Mek.LOC_HEAD, cs) == 0) {
                            name = EquipmentMessages.getString("SystemType.Cockpit.COCKPIT_STANDARD");
                        }
                    } else if ((mek.getCockpitType() == Mek.COCKPIT_DUAL)
                            || (mek.getCockpitType() == Mek.COCKPIT_QUADVEE)) {
                        name = mek.getCrew().getCrewType().getRoleName(mek.getCrewForCockpitSlot(Mek.LOC_HEAD, cs));
                    }
                }
                Objects.requireNonNull(name);
                return name.replace("Standard ", "");
            }
        } else {
            Mounted<?> m = cs.getMount();
            StringBuffer critName = new StringBuffer(m.getType().getShortName());
            if (mek.isMixedTech()) {
                if (mek.isClan() && (m.getType().getTechBase() == ITechnology.TECH_BASE_IS)
                        && (critName.indexOf("[IS]") < 0)) {
                    critName.append(" [IS]");
                } else if (!mek.isClan() && m.getType().isClan()
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
            } else if (m.isMekTurretMounted()) {
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
                    && (cs.getMount2().getType() instanceof MiscType)
                    && (cs.getMount2().getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK))) {
                int hs = 2;
                if (m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                    hs++;
                }
                if (cs.getMount2().getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                    hs++;
                }
                critName.replace(0, 1, Integer.toString(hs));
            }
            if (!mek.isMixedTech()) {
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
        ClusterHitsTable table = new ClusterHitsTable(this, false);
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

    protected int getHeatsinkDamage() {
        if (!options.showDamage()) {
            return 0;
        }
        return mek.damagedHeatSinks();
    }

    protected int getEngineHits() {
        if (!options.showDamage()) {
            return 0;
        }
        return mek.getEngineHits();
    }
    
    protected int getGyroHits() {
        if (!options.showDamage()) {
            return 0;
        }
        return mek.getGyroHits();
    }
    
    protected int getAvionicsHits() {
        if (!options.showDamage()) {
            return 0;
        }
        if (!(mek instanceof LandAirMek)) {
            return 0;
        }
        int totalHits = 0;
        for (int loc = 0; loc < mek.locations(); loc++) {
            totalHits += mek.getHitCriticals(CriticalSlot.TYPE_SYSTEM, LandAirMek.LAM_AVIONICS, loc);
        }
        return totalHits;
    }
    
    protected int getSensorHits() {
        if (!options.showDamage()) {
            return 0;
        }
        int totalHits = 0;
        for (int loc = 0; loc < mek.locations(); loc++) {
            totalHits += mek.getHitCriticals(CriticalSlot.TYPE_SYSTEM, Mek.SYSTEM_SENSORS, loc);
        }
        return totalHits;
    }
    
    protected int getLifeSupportHits() {
        if (!options.showDamage()) {
            return 0;
        }
        int totalHits = 0;
        for (int loc = 0; loc < mek.locations(); loc++) {
            totalHits += mek.getHitCriticals(CriticalSlot.TYPE_SYSTEM, Mek.SYSTEM_LIFE_SUPPORT, loc);
        }
        return totalHits;
    }
    
    protected int getLandingGearHits() {
        if (!options.showDamage()) {
            return 0;
        }
        if (!(mek instanceof LandAirMek)) {
            return 0;
        }
        int totalHits = 0;
        for (int loc = 0; loc < mek.locations(); loc++) {
            totalHits += mek.getHitCriticals(CriticalSlot.TYPE_SYSTEM, LandAirMek.LAM_LANDING_GEAR, loc);
        }
        return totalHits;
    }
    
}
