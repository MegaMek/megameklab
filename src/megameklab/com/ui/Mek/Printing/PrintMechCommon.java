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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mech;
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
        BV("bv", m -> Integer.toString(m.calculateBattleValue()))
        ;
        
        private String elementName;
        private Function<Mech, String> provider;
        
        MechTextElements(String elementName, Function<Mech, String> provider) {
            this.elementName = elementName;
            this.provider = provider;
        }
        
        public String getElementName() {
            return elementName;
        }
        
        public String getText(Mech mech) {
            return provider.apply(mech);
        }
    }
    
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
        for (MechTextElements element : MechTextElements.values()) {
            SVGElement svgEle = diagram.getElement(element.getElementName());
            
            // Ignore elements that don't exist
            if (null == svgEle) {
                continue;
            }
            ((Text) svgEle).getContent().clear();
            ((Text) svgEle).appendText(element.getText(mech));
            ((Text) svgEle).rebuild();
        }
    }
    
    private void writeEquipment(Rect svgRect) throws SVGException {
        Map<Integer, Map<RecordSheetEquipmentLine,Integer>> eqMap = new TreeMap<>();
        Map<String,Integer> ammo = new HashMap<>();
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
            String ammoLine = ammo.entrySet().stream()
                    .map(e -> String.format("(%s): %d", e.getKey(), e.getValue()))
                    .collect(Collectors.joining(", "));
            Text newText = new Text();
            newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
            newText.addAttribute("font-size", AnimationElement.AT_XML, Double.toString(fontSize));
            newText.addAttribute("font-weight", AnimationElement.AT_XML, "normal");
            newText.addAttribute("text-anchor", AnimationElement.AT_CSS, "start");
            newText.addAttribute("x", AnimationElement.AT_XML, Double.toString(viewX));
            newText.addAttribute("y", AnimationElement.AT_XML, Double.toString(viewY + viewHeight - lineHeight));
            newText.appendText("Ammo: " + ammoLine);
            canvas.loaderAddChild(null, newText);
            newText.rebuild();
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
    private void addTextElement(SVGElement parent, int x, int y, String text, double fontSize, String anchor, String weight)
            throws SVGException {
        Text newText = new Text();
        newText.appendText(text);
        
        newText.addAttribute("x", AnimationElement.AT_XML, x + "");
        newText.addAttribute("y", AnimationElement.AT_XML, y + "");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, Double.toString(fontSize));
        newText.addAttribute("font-weight", AnimationElement.AT_XML, weight);
        newText.addAttribute("text-anchor", AnimationElement.AT_CSS, anchor);
        parent.loaderAddChild(null, newText);
        newText.rebuild();
    }
}
