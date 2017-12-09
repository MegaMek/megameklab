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

import com.kitfox.svg.Path;
import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.UnitRole;
import megamek.common.UnitRoleHandler;
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
        drawFluffImage();
    }
    
    protected void writeTextFields() throws SVGException {
        setTextField("title", getRecordSheetTitle().toUpperCase());
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
        UnitRole role = UnitRoleHandler.getRoleFor(getEntity());
        if (role == UnitRole.UNDETERMINED) {
            hideElement("lblRole", true);
            hideElement("role", true);
        } else {
            setTextField("role", role.toString());
        }
        
        for (int i = 0; i < getEntity().getCrew().getSlotCount(); i++) {
            // If we have multiple named crew for the unit, change the "Name:" label to
            // the label of the slot. This will usually require adjusting the position of the
            // name or the length of the blank.
            double nameOffset = 0;
            if (getEntity().getCrew().getSlotCount() > 1) {
                SVGElement element = getSVGDiagram().getElement("crewName" + i);
                if (null != element) {
                    double oldWidth = ((Text) element).getBoundingBox().getWidth();
                    ((Text) element).getContent().clear();
                    ((Text) element).appendText(getEntity().getCrew().getCrewType().getRoleName(i) + ":");
                    ((Text) element).rebuild();
                    nameOffset = ((Text) element).getBoundingBox().getWidth() - oldWidth;
                }
            }
            if (!getEntity().getCrew().getName().equalsIgnoreCase("unnamed")) {
                SVGElement element = getSVGDiagram().getElement("blanksCrew" + i);
                if (null != element) {
                    hideElement(element);
                }
                if (nameOffset != 0) {
                    element = getSVGDiagram().getElement("pilotName" + i);
                    if (null != element) {
                        if (element.hasAttribute("x", AnimationElement.AT_XML)) {
                            element.setAttribute("x", AnimationElement.AT_XML,
                                    Double.toString(nameOffset + Double.valueOf(element.getPresAbsolute("x").getStringValue())));
                        } else {
                            element.addAttribute("x", AnimationElement.AT_XML,
                                    Double.toString(((Text) element).getBoundingBox().getX() + nameOffset));
                        }
                    }
                }
                setTextField("pilotName" + i, getEntity().getCrew().getName(i), true);
                setTextField("gunnerySkill" + i, Integer.toString(getEntity().getCrew().getGunnery(i)), true);
                setTextField("pilotingSkill" + i, Integer.toString(getEntity().getCrew().getPiloting(i)), true);
                
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
                    Rect rect = (Rect) getSVGDiagram().getElement("spas" + getEntity().getCrew().getSlotCount());
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
                setTextField("pilotName" + i, null);
                setTextField("gunnerySkill" + i, null);
                setTextField("pilotingSkill" + i, null);
                if (nameOffset != 0) {
                    SVGElement element = getSVGDiagram().getElement("blankCrewName" + i);
                    if (null != element) {
                        double w = ((Path) element).getBoundingBox().getWidth();
                        element.setAttribute("d", AnimationElement.AT_XML,
                                String.format("M %f,0 %f,0", nameOffset, w - nameOffset));
                    }
                }
            }
        }
    }
    
    protected void drawArmor() throws SVGException {
        if (!getEntity().hasPatchworkArmor()) {
            if ((AT_SPECIAL & (1 << getEntity().getArmorType(1))) != 0) {
                String[] atName = EquipmentType.getArmorTypeName(getEntity().getArmorType(1)).split("\\-");
                setTextField("armorType", atName[0]);
                if (atName.length > 1) {
                    setTextField("armorType2", atName[1]);
                }
            } else {
                hideElement("armorType", true);
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
                    String eleName = "patchwork" + getEntity().getLocationAbbr(loc);
                    int index = atName.indexOf('-');
                    if ((index < 0) || (getSVGDiagram().getElement(eleName + "2") == null)) {
                        setTextField("patchwork" + getEntity().getLocationAbbr(loc), atName);
                    } else {
                        setTextField("patchwork" + getEntity().getLocationAbbr(loc),
                                atName.substring(0, index));
                        setTextField("patchwork" + getEntity().getLocationAbbr(loc) + "2",
                                atName.substring(index + 1));
                    }
                    hasSpecial = true;
                }
            }
            if (hasSpecial) {
                setTextField("armorType", EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK));
            } else {
                hideElement("armorType", true);
            }
        }
        final String FORMAT = "( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField("textArmor_" + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOArmor(loc)));
            setTextField("textIS_" + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, getEntity().getOInternal(loc)));
        }
        drawArmorStructurePips();
        getSVGDiagram().updateTime(0);
    }

    /**
     * Add armor and structure pips for each location.
     * 
     * @throws SVGException
     */
    protected void drawArmorStructurePips() throws SVGException {
        SVGElement element = null;
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            if ((getEntity() instanceof Mech) && ((Mech) getEntity()).isSuperHeavy() && (loc == Mech.LOC_HEAD)) {
                element = getSVGDiagram().getElement("armorPips" + getEntity().getLocationAbbr(loc) + "_SH");
            } else {
                element = getSVGDiagram().getElement("armorPips" + getEntity().getLocationAbbr(loc));
            }
            if (null != element) {
                addPips(element, getEntity().getOArmor(loc), isCenterlineLocation(loc),
                        PipType.forAT(getEntity().getArmorType(loc)));
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
     * @param loc
     * @return
     */
    protected abstract boolean isCenterlineLocation(int loc);
    
    protected void drawStructure() throws SVGException {
        
    }
    
    protected void writeEquipment(Rect canvas) throws SVGException {
        
    }
    
    protected void drawFluffImage() throws SVGException {
        
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
