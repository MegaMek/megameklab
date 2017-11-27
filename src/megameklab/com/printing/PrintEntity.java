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
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.StringJoiner;

import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.Entity;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.PilotOptions;

/**
 * Base class for printing Entity record sheets
 * 
 * @author Neoancient
 *
 */
public abstract class PrintEntity extends PrintRecordSheet {
    
    /**
     * IDs of fields to hide if there is an assigned pilot
     */
    private static final String[] CREW_BLANKS = {
            "blankPilotName", "blankGunnerySkill", "blankPilotingSkill"
    };
    
    protected PrintEntity(int startPage) {
        super(startPage);
    }

    protected abstract Entity getEntity();
    
    @Override
    protected void printImage(Graphics2D g2d, PageFormat pageFormat, int pageNum) throws SVGException {
        SVGElement element = null;
        
        element = getSVGDiagram().getElement("tspanCopyright");
        if (null != element) {
            ((Tspan) element).setText(String.format(((Tspan) element).getText(),
                    Calendar.getInstance().get(Calendar.YEAR)));
            ((Text) element.getParent()).rebuild();
        }
        
        writeTextFields();
        drawArmor();
        drawStructure();
        SVGElement eqRect = getSVGDiagram().getElement("inventory");
        if (null != eqRect) {
            writeEquipment((Rect) eqRect);
        }
    }
    
    protected void writeTextFields() throws SVGException {
        setTextField("type", getEntity().getShortNameRaw());
        setTextField("mpWalk", formatWalk());
        setTextField("mpRun", formatRun());
        setTextField("mpJump", formatJump());
        setTextField("tonnage", Integer.toString((int) getEntity().getWeight()));
        setTextField("techBase", formatTechBase());
        setTextField("rulesLevel", formatRulesLevel());
        setTextField("era", formatEra(getEntity().getYear()));
        setTextField("cost", formatCost());
        setTextField("bv", Integer.toString(getEntity().calculateBattleValue()));
        
        if (!getEntity().getCrew().getName().equalsIgnoreCase("unnamed")) {
            for (String fieldName : CREW_BLANKS) {
                SVGElement svgEle = getSVGDiagram().getElement(fieldName);
                if (null != svgEle) {
                    svgEle.addAttribute("visibility", AnimationElement.AT_XML, "hidden");
                    svgEle.updateTime(0);
                }
            }
            setTextField("pilotName", getEntity().getCrew().getName(0));
            setTextField("gunnerySkill", Integer.toString(getEntity().getCrew().getGunnery(0)));
            setTextField("pilotingSkill", Integer.toString(getEntity().getCrew().getPiloting(0)));
            
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
                Rect rect = (Rect) getSVGDiagram().getElement("spas");
                if (null != rect) {
                    Rectangle2D bbox = rect.getBoundingBox();
                    SVGElement canvas = rect.getParent();
                    String spaText = "Abilities: " + spaList.toString();
                    double fontSize = FONT_SIZE_MEDIUM;
                    if (getTextLength(spaText, fontSize, canvas) > bbox.getWidth()) {
                        fontSize = bbox.getHeight() / 2.4;
                    }
                    double lineHeight = fontSize * 1.2;
                    addMultilineTextElement(canvas, bbox.getX(), bbox.getY() + lineHeight,
                            bbox.getWidth(), lineHeight, spaText, fontSize, "start", "normal",
                            "black", ' ');
                }
            }

        } else {
            setTextField("pilotName", "");
            setTextField("gunnerySkill", "");
            setTextField("pilotingSkill", "");
        }
    }
    
    protected void drawArmor() throws SVGException {
        
    }
    
    protected void drawStructure() throws SVGException {
        
    }
    
    protected void writeEquipment(Rect canvas) throws SVGException {
        
    }
    
    protected String formatWalk() {
        return Integer.toString(getEntity().getWalkMP());
    }
    
    protected String formatRun() {
        return Integer.toString(getEntity().getRunMP());
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
