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
package megameklab.com.ui.Mek.Printing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.kitfox.svg.Path;
import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.logging.LogLevel;
import megameklab.com.MegaMekLab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RecordSheetEquipmentLine;
import megameklab.com.util.UnitUtil;

/**
 * @author Neoancient
 *
 */
public class PrintMechCommon implements Printable {
    
    private enum MechTextElements {
        TYPE ("type", m -> m.getChassis() + " " + m.getModel()),
        MP_WALK("mpWalk", m -> {
            if (m.hasTSM()) {
                return m.getWalkMP() + " [" + (m.getWalkMP() + 1) + "]";
            } else {
                return Integer.toString(m.getWalkMP());
            }
        }),
        MP_RUN("mpRun", m -> formatRunMp(m)),
        MP_JUMP("mpJump", m -> {
            if (m.hasUMU()) {
                return Integer.toString(m.getActiveUMUCount());
            } else {
                return Integer.toString(m.getJumpMP());
            }
        }),
        TONNAGE("tonnage", m -> Integer.toString((int) m.getWeight())),
        TECH_BASE("techBase", m -> formatTechBase(m)),
        RULES_LEVEL("rulesLevel", m -> formatRulesLevel(m)),
        ERA("era", m -> formatEra(m.getYear())),
        COST("cost", m -> formatCost(m)),
        BV("bv", m -> Integer.toString(m.calculateBattleValue())),
        PILOT_NAME("pilotName", m -> m.getCrew().getName(0),
                m -> !m.getCrew().getName().equalsIgnoreCase("unnamed")),
        GUNNERY_SKILL("gunnerySkill", m -> Integer.toString(m.getCrew().getGunnery(0)),
                m -> !m.getCrew().getName().equalsIgnoreCase("unnamed")),
        PILOTING_SKILL("pilotingSkill", m -> Integer.toString(m.getCrew().getPiloting(0)),
                m -> !m.getCrew().getName().equalsIgnoreCase("unnamed")),
        HEAT_SINK_TYPE("hsType", m -> formatHeatSinkType(m)),
        HEAT_SINK_COUNT("hsCount", m -> formatHeatSinkCount(m))
        ;
        
        private String elementName;
        private Function<Mech, String> provider;
        private Predicate<Mech> show;
        
        MechTextElements(String elementName, Function<Mech, String> provider) {
            this(elementName, provider, m -> true);
        }
        
        MechTextElements(String elementName, Function<Mech, String> provider, Predicate<Mech> show) {
            this.elementName = elementName;
            this.provider = provider;
            this.show = show;
        }
        
        public String getElementName() {
            return elementName;
        }
        
        public String getText(Mech mech) {
            return provider.apply(mech);
        }
        
        public boolean shouldWrite(Mech mech) {
            return show.test(mech);
        }
    }
    
    /**
     * IDs of fields to hide if there is an assigned pilot
     */
    private static final String[] CREW_BLANKS = {
            "blankPilotName", "blankGunnerySkill", "blankPilotingSkill"
    };
    
    /**
     * The current mech being printed.
     */
    private Mech mech = null;

    public PrintMechCommon(Mech mech) {
        this.mech = mech;
    }

    /**
     * 
     */
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
    }

    /**
     * 
     * @param g2d
     * @param pageFormat
     */
    public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        final String METHOD_NAME = "printImage(Graphics2D, PageFormat";
        
        if (g2d == null) {
            return;
        }

        String mechSheetSVG = "data/images/recordsheets/Biped_Mech_default.svg";
        
        SVGDiagram diagram;
        diagram = ImageHelper.loadSVGImage(new File(mechSheetSVG));
        if (null == diagram) {
            MegaMekLab.getLogger().log(getClass(), METHOD_NAME,
                    LogLevel.ERROR,
                    "Failed to open Mech SVG file! Path: data/images/recordsheets/" + mechSheetSVG);
            return;
        }
        diagram.setDeviceViewport(
                new Rectangle(0, 0, (int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight()));

        try {
            writeTextFields(diagram);
            SVGElement eqRect = diagram.getElement("inventory");
            if (null != eqRect) {
                writeEquipment((Rect) eqRect);
            }
            
            for (int loc = 0; loc < mech.locations(); loc++) {
                SVGElement critRect = diagram.getElement("crits_" + mech.getLocationAbbr(loc));
                if (null != critRect) {
                    writeLocationCriticals(loc, (Rect) critRect);
                }
            }
            
            if (mech.getGyroType() == Mech.GYRO_HEAVY_DUTY) {
                SVGElement pip = diagram.getElement("heavyDutyGyroPip");
                if ((null != pip) && pip.hasAttribute("visibility", AnimationElement.AT_XML)) {
                    pip.setAttribute("visibility", AnimationElement.AT_XML, "visible");
                }
                pip.updateTime(0);
            }
            
            SVGElement hsRect = diagram.getElement("heatSinkPips");
            if (null != hsRect) {
                drawHeatSinkPips((Rect) hsRect);
            }
            diagram.render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
    }

    private void writeTextFields(SVGDiagram diagram) throws SVGException {
        if (mech.hasUMU()) {
            SVGElement svgEle = diagram.getElement("mpJumpLabel");
            if (null != svgEle) {
                ((Tspan) svgEle).setText("Underwater:");
                ((Text) svgEle.getParent()).rebuild();
            }
        }
        if (!mech.getCrew().getName().equalsIgnoreCase("unnamed")) {
            for (String fieldName : CREW_BLANKS) {
                SVGElement svgEle = diagram.getElement(fieldName);
                if (null != svgEle) {
                    svgEle.addAttribute("visibility", AnimationElement.AT_XML, "hidden");
                    svgEle.updateTime(0);
                }
            }
        }
        for (MechTextElements element : MechTextElements.values()) {
            SVGElement svgEle = diagram.getElement(element.getElementName());
            
            // Ignore elements that don't exist
            if (null == svgEle) {
                continue;
            }
            ((Text) svgEle).getContent().clear();
            if (element.shouldWrite(mech)) {
                ((Text) svgEle).appendText(element.getText(mech));
            }
            ((Text) svgEle).rebuild();
        }
    }
    
    private void writeEquipment(Rect svgRect) throws SVGException {
        Map<Integer, Map<RecordSheetEquipmentLine,Integer>> eqMap = new TreeMap<>();
        Map<String,Integer> ammo = new TreeMap<>();
        for (Mounted m : mech.getEquipment()) {
            if (m.getType() instanceof AmmoType) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = ((AmmoType) m.getType()).getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    shortName = shortName.replace("-capable", "");
                    ammo.merge(shortName, m.getBaseShotsLeft(), Integer::sum);
                }
                continue;
            }
            if ((m.getType() instanceof AmmoType)
                    || (m.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(m.getType(), true)) {
                continue;
            }
            eqMap.putIfAbsent(m.getLocation(), new HashMap<>());
            RecordSheetEquipmentLine line = new RecordSheetEquipmentLine(m);
            eqMap.get(m.getLocation()).merge(line, 1, Integer::sum);
        }
        
        Rectangle2D bbox = svgRect.getBoundingBox();
        SVGElement canvas = svgRect.getRoot();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();
        
        int qtyX = (int) Math.round(viewX + viewWidth * 0.037);
        int nameX = (int) Math.round(viewX + viewWidth * 0.08);
        int locX = (int) Math.round(viewX + viewWidth * 0.41);
        int heatX = (int) Math.round(viewX + viewWidth * 0.48);
        int dmgX = (int) Math.round(viewX + viewWidth * 0.53);
        int minX = (int) Math.round(viewX + viewWidth * 0.72);
        int shortX = (int) Math.round(viewX + viewWidth * 0.8);
        int medX = (int) Math.round(viewX + viewWidth * 0.88);
        int longX = (int) Math.round(viewX + viewWidth * 0.96);
        
        int indent = (int) Math.round(viewWidth * 0.02);
        
        int currY = viewY + 10;
        
        double fontSize = viewHeight * 0.044;
        double lineHeight = getFontHeight(fontSize, canvas) * 1.2;
        
        addTextElement(canvas, qtyX, currY, "Qty", fontSize, "middle", "bold");
        addTextElement(canvas, nameX + indent, currY, "Type", fontSize, "start", "bold");
        addTextElement(canvas, locX,  currY, "Loc", fontSize, "middle", "bold");
        addTextElement(canvas, heatX, currY, "Ht", fontSize, "middle", "bold");
        addTextElement(canvas, dmgX, currY, "Dmg", fontSize, "start", "bold");
        addTextElement(canvas, minX, currY, "Min", fontSize, "middle", "bold");
        addTextElement(canvas, shortX, currY, "Sht", fontSize, "middle", "bold");
        addTextElement(canvas, medX, currY, "Med", fontSize, "middle", "bold");
        addTextElement(canvas, longX, currY, "Lng", fontSize, "middle", "bold");
        currY += lineHeight;

        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                for (int row = 0; row < line.nRows(); row++) {
                    if (row == 0) {
                        addTextElement(canvas, qtyX, currY, Integer.toString(eqMap.get(loc).get(line)), fontSize, "middle", "normal");
                        addTextElement(canvas, nameX, currY, line.getNameField(row), fontSize, "start", "normal");
                    } else {
                        addTextElement(canvas, nameX + indent, currY, line.getNameField(row), fontSize, "start", "normal");
                    }
                    addTextElement(canvas, locX,  currY, line.getLocationField(row), fontSize, "middle", "normal");
                    addTextElement(canvas, heatX, currY, line.getHeatField(row), fontSize, "middle", "normal");
                    addTextElement(canvas, dmgX, currY, line.getDamageField(row), fontSize, "start", "normal");
                    addTextElement(canvas, minX, currY, line.getMinField(row), fontSize, "middle", "normal");
                    addTextElement(canvas, shortX, currY, line.getShortField(row), fontSize, "middle", "normal");
                    addTextElement(canvas, medX, currY, line.getMediumField(row), fontSize, "middle", "normal");
                    addTextElement(canvas, longX, currY, line.getLongField(row), fontSize, "middle", "normal");
                    currY += lineHeight;
                }
            }
        }
        
        if (ammo.size() > 0) {
            List<String> lines = new ArrayList<>();
            String line = "Ammo: ";
            double commaLen = getTextLength(", ", fontSize, canvas);
            double currX = getTextLength(line, fontSize, canvas);

            boolean first = true;
            for (String name : ammo.keySet()) {
                String str = String.format("(%s) %d", name, ammo.get(name));
                double len = getTextLength(str, fontSize, canvas);
                if (!first) {
                    len += commaLen;
                }
                if (currX + len < viewWidth) {
                    if (!first) {
                        line += ", ";
                    } else {
                        first = false;
                    }
                    line += str;
                } else {
                    lines.add(line);
                    line = str;
                    currX = indent;
                }
            }
            lines.add(line);
            
            currY = (int) (viewY + viewHeight - lines.size() * lineHeight);
            for (String l : lines) {
                Text newText = new Text();
                newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
                newText.addAttribute("font-size", AnimationElement.AT_XML, Double.toString(fontSize));
                newText.addAttribute("font-weight", AnimationElement.AT_XML, "normal");
                newText.addAttribute("text-anchor", AnimationElement.AT_CSS, "start");
                newText.addAttribute("x", AnimationElement.AT_XML, Double.toString(viewX));
                newText.addAttribute("y", AnimationElement.AT_XML, Double.toString(currY));
                newText.appendText(l);
                canvas.loaderAddChild(null, newText);
                newText.rebuild();
            }
        }

    }
    
    private void writeLocationCriticals(int loc, Rect svgRect) throws SVGException {
        Rectangle2D bbox = svgRect.getBoundingBox();
        SVGElement canvas = svgRect.getRoot();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();
        
        double rollX = viewX;
        double critX = viewX + viewWidth * 0.11;
        double gap = 0;
        if (mech.getNumberOfCriticals(loc) > 6) {
            gap = viewHeight * 0.05;
        }
        double lineHeight = (viewHeight - gap) / mech.getNumberOfCriticals(loc);
        double currY = viewY;
        double fontSize = lineHeight * 0.9;
        
        Mounted startingMount = null;
        double startingMountY = 0;
        double endingMountY = 0;
        double connWidth = viewWidth * 0.02;
        
        for (int slot = 0; slot < mech.getNumberOfCriticals(loc); slot++) {
            currY += lineHeight;
            if (slot == 6) {
                currY += gap;
            }
            addTextElement(canvas, rollX, currY, ((slot % 6) + 1) + ".", fontSize, "start", "bold");
            CriticalSlot crit = mech.getCritical(loc, slot);
            String style = "bold";
            String fill = "#000000";
            if ((null == crit)
                    || ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                            && (!crit.getMount().getType().isHittable()))) {
                style = "standard";
                fill = "#3f3f3f";
                addTextElement(canvas, critX, currY, formatCritName(crit), fontSize, "start", style, fill);
            } else if (crit.isArmored()) {
                SVGElement pip = createPip(critX, currY - fontSize * 0.8, fontSize * 0.4, 0.7);
                canvas.loaderAddChild(null, pip);
                canvas.updateTime(0);
                addTextElement(canvas, critX + fontSize, currY, formatCritName(crit), fontSize, "start", style, fill);
            } else if ((crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                    && (crit.getMount().getType() instanceof MiscType)
                    && (crit.getMount().getType().hasFlag(MiscType.F_MODULAR_ARMOR))) {
                String critName = formatCritName(crit);
                addTextElement(canvas, critX, currY, critName, fontSize, "start", style, fill);
                double x = critX + getTextLength(critName, fontSize, canvas);
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
                    SVGElement pip = createPip(x, y, radius, 0.5);
                    canvas.loaderAddChild(null, pip);
                    canvas.updateTime(0);
                    x += spacing;
                }
            } else {
                addTextElement(canvas, critX, currY, formatCritName(crit), fontSize, "start", style, fill);
            }
            Mounted m = null;
            if ((null != crit) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                    && (crit.getMount().getType().isHittable())
                    && (crit.getMount().getType().getCriticals(mech) > 1)) {
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
        if ((null != startingMount) && (startingMount.getType().getCriticals(mech) > 1)) {
            connectSlots(canvas, critX - 1, startingMountY, connWidth, endingMountY - startingMountY);
        }
    }

    private void connectSlots(SVGElement canvas, double x, double y, double w,
            double h) throws SVGElementException, SVGException {
        Path p = new Path();
        p.addAttribute("d", AnimationElement.AT_XML,
                "M " + x + " " + y
                + " h " + (-w)
                + " v " + h
                + " h " + w);
        p.addAttribute("stroke", AnimationElement.AT_CSS, "black");
        p.addAttribute("stroke-width", AnimationElement.AT_CSS, "0.72");
        p.addAttribute("fill", AnimationElement.AT_CSS, "none");
        p.updateTime(0);
        canvas.loaderAddChild(null, p);
        canvas.updateTime(0);
    }
    
    private void drawHeatSinkPips(Rect svgRect) throws SVGException {
        Rectangle2D bbox = svgRect.getBoundingBox();
        SVGElement canvas = svgRect.getRoot();
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
                size = viewWidth / (cols * size);
                rows = (int) (viewHeight / size);
            }
        }
        double radius = size * 0.36;
        double strokeWidth = 0.9;
        for (int i = 0; i < hsCount; i++) {
            int row = i % rows;
            int col = i / rows;
            SVGElement pip = this.createPip(viewX + size * col, viewY + size * row, radius, strokeWidth);
            canvas.loaderAddChild(null, pip);
            canvas.updateTime(0);
        }
    }
    
    private double getFontHeight(double fontSize, SVGElement canvas) throws SVGException {
        Text newText = new Text();
        newText.appendText("Medium Laser");        
        newText.addAttribute("x", AnimationElement.AT_XML, "0");
        newText.addAttribute("y", AnimationElement.AT_XML, "0");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, Double.toString(fontSize));
        canvas.loaderAddChild(null, newText);
        newText.rebuild();
        
        double textHeight = newText.getShape().getBounds().getHeight();

        canvas.removeChild(newText);
        return textHeight;
    }
    
    private double getTextLength(String text, double fontSize, SVGElement canvas) throws SVGException {
        Text newText = new Text();
        newText.appendText(text);        
        newText.addAttribute("x", AnimationElement.AT_XML, "0");
        newText.addAttribute("y", AnimationElement.AT_XML, "0");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, Double.toString(fontSize));
        canvas.loaderAddChild(null, newText);
        newText.rebuild();
        
        double width = newText.getShape().getBounds().getWidth();

        canvas.removeChild(newText);
        return width;
    }
    
    private static String formatRunMp(Mech mech) {
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
        if (mp > mech.getRunMP()) {
            return mech.getRunMP() + " [" + mp + "]";
        } else {
            return Integer.toString(mech.getRunMP());
        }
    }
    
    private static String formatTechBase(Mech mech) {
        if (mech.isMixedTech()) {
            return "Mixed";
        } else if (mech.isClan()) {
            return "Clan";
        } else {
            return "Inner Sphere";
        }
    }
    
    private static String formatRulesLevel(Mech mech) {
        return mech.getStaticTechLevel().toString().substring(0, 1)
                + mech.getStaticTechLevel().toString().substring(1).toLowerCase();
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
    
    private static String formatCost(Mech mech) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        return nf.format(mech.getCost(true)) + " C-bills";
    }
    
    private static String formatHeatSinkType(Mech mech) {
        if (mech.hasLaserHeatSinks()) {
            return "Laser Heat Sinks:";
        } else if (mech.hasDoubleHeatSinks()) {
            return "Double Heat Sinks:";
        } else {
            return "Heat Sinks";
        }
    }
    
    private static String formatHeatSinkCount(Mech mech) {
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
                String name = mech.getSystemName(cs.getIndex()).replace("Standard ", "");
                if (((cs.getIndex() >= Mech.ACTUATOR_UPPER_ARM) && (cs.getIndex() <= Mech.ACTUATOR_HAND))
                        || ((cs.getIndex() >= Mech.ACTUATOR_UPPER_LEG) && (cs.getIndex() <= Mech.ACTUATOR_FOOT))) {
                    name += " Actuator";
                }
                return name;
            }
        } else {
            Mounted m = cs.getMount();
            StringBuffer critName = new StringBuffer(UnitUtil.getCritName(mech, m.getType()));

            if (UnitUtil.isTSM(m.getType())) {
                critName.setLength(0);
                critName.append("Triple-Strength Myomer");
            }

            if (m.isRearMounted()) {
                critName.append(" (R)");
            } else if (m.isMechTurretMounted()) {
                critName.append(" (T)");
            } else if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                AmmoType ammo = (AmmoType) m.getType();

                critName = new StringBuffer("Ammo (");
                // Remove Text (Clan) from the name
                critName.append(ammo.getShortName().replace('(', '.').replace(')', '.').replaceAll(".Clan.", "").trim());
                // Remove any additional Ammo text.
                if (critName.toString().endsWith("Ammo")) {
                    critName.setLength(critName.length() - 5);
                    critName.trimToSize();
                }

                // Remove Capable with the name
                if (critName.indexOf("-capable") > -1) {
                    int startPos = critName.indexOf("-capable");
                    critName.delete(startPos, startPos + "-capable".length());
                    critName.trimToSize();
                }

                // Trim trailing spaces.
                while (critName.charAt(critName.length() - 1) == ' ') {
                    critName.setLength(critName.length() - 1);
                }
                critName.trimToSize();
                critName.append(") ");
                critName.append(m.getUsableShotsLeft());
            }

            if (cs.getMount2() != null) {
                critName.append(" | ");
                if (!(cs.getMount2().getType() instanceof AmmoType)) {
                    critName.append(UnitUtil.getCritName(mech, cs.getMount2().getType()));
                } else {
                    AmmoType ammo = (AmmoType)cs.getMount2().getType();
                    critName.append(ammo.getShortName().replace('(', '.').replace(')', '.').replaceAll(".Clan.", "").trim());
                    // Remove any additional Ammo text.
                    if (critName.toString().endsWith("Ammo")) {
                        critName.setLength(critName.length() - 5);
                        critName.trimToSize();
                    }

                    // Remove Capable with the name
                    if (critName.indexOf("-capable") > -1) {
                        int startPos = critName.indexOf("-capable");
                        critName.delete(startPos, startPos + "-capable".length());
                        critName.trimToSize();
                    }

                    // Trim trailing spaces.
                    while (critName.charAt(critName.length() - 1) == ' ') {
                        critName.setLength(critName.length() - 1);
                    }
                    critName.trimToSize();
                    critName.append(") ");
                    critName.append(m.getUsableShotsLeft());
                }
            }
            if (!mech.isMixedTech()) {
                int startPos = critName.indexOf("[Clan]");
                if (startPos >= 0) {
                    critName.delete(startPos, startPos + "[Clan]".length());
                    critName.trimToSize();
                }
            }
            return critName.toString();
        }
    }

    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent.  The height of the text is
     * returned, to aid in layout.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     *
     * @throws SVGException
     */
    private void addTextElement(SVGElement parent, double x, double y, String text,
            double fontSize, String anchor, String weight) throws SVGException {
        addTextElement(parent, x, y, text, fontSize, anchor, weight, "#000000");
    }
    
    private void addTextElement(SVGElement parent, double x, double y, String text,
            double fontSize, String anchor, String weight, String fill)
            throws SVGException {
        Text newText = new Text();
        newText.appendText(text);
        
        newText.addAttribute("x", AnimationElement.AT_XML, x + "");
        newText.addAttribute("y", AnimationElement.AT_XML, y + "");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, fontSize + "px");
        newText.addAttribute("font-weight", AnimationElement.AT_XML, weight);
        newText.addAttribute("text-anchor", AnimationElement.AT_CSS, anchor);
        newText.addAttribute("fill", AnimationElement.AT_XML, fill);
        parent.loaderAddChild(null, newText);
        newText.rebuild();
    }
    
    private final static double CONST_C = 0.55191502449;
    private final static String FMT_CURVE = " c %f %f,%f %f,%f %f";
    
    /**
     * Approximates a circle using four bezier curves.
     * 
     * @param x      Position of left of bounding rectangle.
     * @param y      Position of top of bounding rectangle.
     * @param radius Radius of the circle
     * @return       A Path describing the circle
     * @throws SVGException
     */
    private Path createPip(double x, double y, double radius, double strokeWidth) throws SVGException {
        // c is the length of each control line
        double c = CONST_C * radius;
        Path path = new Path();
        path.addAttribute("fill", AnimationElement.AT_CSS, "none");
        path.addAttribute("stroke", AnimationElement.AT_CSS, "black");
        path.addAttribute("stroke-width", AnimationElement.AT_CSS, Double.toString(strokeWidth));
        
        // Move to start of circle, at (1, 0)
        StringBuilder d = new StringBuilder("M").append(x + radius * 2).append(",").append(y + radius);
        // Draw arcs anticlockwise. The coordinates are relative to the beginning of the arc.
        d.append(String.format(FMT_CURVE, 0.0, -c, c - radius, -radius, -radius, -radius));
        d.append(String.format(FMT_CURVE, -c, 0.0, -radius, radius - c, -radius, radius));
        d.append(String.format(FMT_CURVE, 0.0, c, radius - c, radius, radius, radius));
        d.append(String.format(FMT_CURVE, c, 0.0, radius, c - radius, radius, -radius));
        path.addAttribute("d", AnimationElement.AT_XML, d.toString());
        path.updateTime(0);
        return path;
    }
}
