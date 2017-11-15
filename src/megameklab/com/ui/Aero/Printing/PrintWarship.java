/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.Aero.Printing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.kitfox.svg.ImageSVG;
import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.ASFBay;
import megamek.common.Bay;
import megamek.common.Mounted;
import megamek.common.SmallCraftBay;
import megamek.common.Warship;
import megamek.common.WeaponType;
import megameklab.com.util.ImageHelper;

/**
 * 
 * @author arlith
 *
 */
public class PrintWarship implements Printable {

    public static double ARMOR_PIP_WIDTH = 4.5;
    public static double ARMOR_PIP_HEIGHT = 4.5;
    
    public static double ARMOR_PIP_WIDTH_SMALL = 2.25;
    public static double ARMOR_PIP_HEIGHT_SMALL = 2.25;

    public static int IS_PIP_WIDTH = 3;
    public static int IS_PIP_HEIGHT = 3;

    public static int PIPS_PER_ROW = 10;
    public static int MAX_PIP_ROWS = 10;
    
    /**
     * Specifies how many weapon bay lines can exist before no fluff image sheets are forced.  If there are too many
     * weapon bay lines, then the equipment list may be too small.
     */
    public static int WEAPON_LINES_FLUFF_LIMIT = 35;
    
    /**
     * If we have a large number of weapon lines, we should consider condensing nose & aft bays, to save space.
     */
    public static int WEAPON_LINES_NOSE_AFT_COMBINE = 50;

    public static enum ElementType {
        TEXT, ARMOR, INTERNALS, EQUIPMENT, IMAGE, TSPAN;
    }
    
    public static enum PrintType {
        NORMAL, SIDE_BY_SIDE_FIRST, SIDE_BY_SIDE_SECOND;
    }

    /**
     * Defines the different elements that can be printed to a Warship sheet.
     *
     */
    public static enum WarshipPrintElements {
        // Warship Data block
        COPYRIGHT("copyright", "Copyright Year", ElementType.TEXT),
        TYPE("type", "Type of Warship", ElementType.TEXT), 
        NAME("name", "Name of Warship", ElementType.TEXT), 
        TONNAGE("tonnage", "Tonnage of Warship", ElementType.TEXT), 
        TECH_BASE("techBase", "Indicates if this Warship is Clan or IS", ElementType.TEXT), 
        SAFE_THRUST("safeThrust", "Number of safe thrust points", ElementType.TEXT), 
        MAX_THRUST("maxThrust", "Maximum thrust points", ElementType.TEXT), 
        DS_CAPACITY("dsCapacity", "Dropship Capacity", ElementType.TEXT), 
        FIGHTERS("fighters", "Number of carried fighters", ElementType.TEXT), 
        SMALL_CRAFT("smallCraft", "Number of carried small craft", ElementType.TEXT), 
        LAUNCH_RATES("launchRate", "Launch rate for fighters/smallcraft", ElementType.TEXT),
        // Armor
        NOSE_ARMOR_PIPS("noseArmorPips", "Nose Armor Pips", ElementType.ARMOR), 
        NOSE_ARMOR_TEXT("noseArmorText", "Nose Armor total and threshold", ElementType.TEXT), 
        FL_ARMOR_PIPS("flArmorPips", "Fore-Left Armor Pips", ElementType.ARMOR), 
        FL_ARMOR_TEXT("flArmorText", "Fore-Left Armor total and threshold", ElementType.TEXT), 
        FR_ARMOR_PIPS("frArmorPips", "Fore-Right Armor Pips", ElementType.ARMOR), 
        FR_ARMOR_TEXT("frArmorText", "Fore-Right Armor total and threshold", ElementType.TEXT), 
        AL_ARMOR_PIPS("alArmorPips", "Aft-Left Armor Pips", ElementType.ARMOR), 
        AL_ARMOR_TEXT("alArmorText", "Aft-Left Armor total and threshold", ElementType.TEXT), 
        AR_ARMOR_PIPS("arArmorPips", "Aft-Right Armor Pips", ElementType.ARMOR), 
        AR_ARMOR_TEXT("arArmorText", "Aft-Right Armor total and threshold", ElementType.TEXT), 
        AFT_ARMOR_PIPS( "aftArmorPips", "Aft Armor Pips", ElementType.ARMOR), 
        AFT_ARMOR_TEXT( "aftArmorText", "Aft Armor total and threshold", ElementType.TEXT),
        // Internals
        SI_PIPS("siPips", "Structural Integrity Pips", ElementType.INTERNALS), 
        SI_TEXT("siText", "Structural Integrity Text", ElementType.TEXT), 
        KF_PIPS("kfPips", "K-F Drive Integrity Pips", ElementType.INTERNALS),
        KF_TEXT("kfText", "K-F Drive Integrity Text", ElementType.TEXT),
        SAIL_PIPS("sailPips","Sail Integrity Pips", ElementType.INTERNALS),
        SAIL_TEXT("sailText", "Sail Integrity Text", ElementType.TEXT),
        DC_PIPS("dcPips", "Docking Collar Pips", ElementType.INTERNALS),
        DC_TEXT("dcText", "Docking Collar Text", ElementType.TEXT),
        // Equipment
        INVENTORY("inventory", "List of weapons & equipment, ", ElementType.EQUIPMENT), 
        COST("cost", "Unit Cost", ElementType.TEXT), 
        BV("battlevalue", "Battlevalue", ElementType.TEXT),
        // Crew Data
        GUNNERY_SKILL("gunnerySkill", "Gunnery Skill", ElementType.TEXT), 
        PILOTING_SKILL("pilotingSkill", "Piloting Skill", ElementType.TEXT), 
        CREW("crew", "Number of Crew", ElementType.TEXT), 
        MARINES( "marines", "Number of Marines", ElementType.TEXT), 
        PASSENGERS("passengers", "Number Of Passengers", ElementType.TEXT), 
        ELEMENTALS("elementals", "Number Of Elementals", ElementType.TEXT), 
        ELEMENTALS_TSPAN("elementalsTspan", "Number Of Elementals", ElementType.TSPAN), 
        BATTLE_ARMOR("battleArmor", "Number of Battle Armor", ElementType.TEXT), 
        BATTLE_ARMOR_TSPAN( "battleArmorTspan", "Number of Battle Armor", ElementType.TSPAN), 
        OTHER("other", "Other occupants", ElementType.TEXT), 
        LIFE_BOATS("lifeBoats", "Number of life boats", ElementType.TEXT), 
        ESCAPE_PODS( "escapePods", "Number of Escape Pods", ElementType.TEXT),
        // Heat Data
        HEATSINKS("heatSinks", "Number of heatsinks", ElementType.TEXT), 
        DOUBLE_HEATSINKS("doubleHeatSinks", "Number of heatsinks", ElementType.TEXT), 
        NOSE_HEAT("noseHeat", "Heat generation for nose", ElementType.TEXT), 
        FORE_HEAT("foreHeat", "Heat generation for fore", ElementType.TEXT), 
        BROADSIDE_HEAT("broadsideHeat", "Heat generation for broadside", ElementType.TEXT), 
        AFT_LR_HEAT("aftSidesHeat", "Heat generation for left/right aft", ElementType.TEXT), 
        AFT_HEAT("aftHeat", "Heat generation for aft", ElementType.TEXT),
        FLUFF_IMG("fluffImage", "Heat generation for aft", ElementType.IMAGE)
        ;

        /**
         * The name of this element, used to look up elements in the SVG file.
         */
        String elementName;

        /**
         * A text description what data the element represents.
         */
        String description;

        /**
         * A type that defines what kind of data is displayed.
         */
        ElementType type;

        /**
         * 
         * @param n
         * @param d
         * @param t
         */
        private WarshipPrintElements(String n, String d, ElementType t) {
            elementName = n;
            description = d;
            type = t;
        }

        /**
         * 
         * @return
         */
        public String getElementName() {
            return elementName;
        }

        /**
         * 
         * @return
         */
        public String getElementDescription() {
            return description;
        }

        /**
         * 
         * @return
         */
        public ElementType getType() {
            return type;
        }

        /**
         * 
         */
        public String toString() {
            return "WarshipPrintElements." + getElementName();
        }
    }

    /**
     * The current warship being printed.
     */
    private Warship warship = null;

    /**
     * The index of the first page of the record sheet.
     */
    private int firstPage;

    PrinterJob masterPrintJob;
    
    // These are some global variables related to equipment printing, to cut down on method signature length
    // Column positions
    int nameX;
    int locX;
    int htX;
    int srvX;
    int mrvX;
    int lrvX;
    int ervX;
    
    // Equipment rectangle bounds
    int viewWidth;
    int viewHeight;
    int viewX;
    int viewY;
    
    PrintType cargoPrintType;
    PrintType gravPrintType;
    
    int eqNormalSize;
    int eqHeaderSize;
    
    List<WeaponBayText> capitalWeapTexts;
    List<WeaponBayText> standardWeapTexts;
    
    /**
     * Determines if we should not print the fluff image.  Some warships have so much equipment, that we should use
     * a sheet without the fluff image, to make sure there's enough room in the equipment table.
     * 
     */
    boolean noFluffImg = false;

    public PrintWarship(Warship warship, int firstPage) {
        this.warship = warship;
        this.firstPage = firstPage;
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
        if (g2d == null) {
            return;
        }

        // Sort weapons in capital/standard for later printing
        // Also use number of weapon bays to determine what sheet type to load
        List<Mounted> standardWeapons = new ArrayList<>();
        List<Mounted> capitalWeapons = new ArrayList<>();
        for (Mounted m : warship.getWeaponList()) {
            WeaponType wtype = (WeaponType)m.getType();
            if (wtype.isCapital()) {
                capitalWeapons.add(m);
            } else {
                standardWeapons.add(m);
            }
        }
        boolean combineNoseAftWeaponBays = false;
        int loopCount = 0;
        int weaponLines;
        String wsSheetSVG = "data/images/recordsheets/Warship_default.svg";
        
        do {
            weaponLines = 4 + 4;
            capitalWeapTexts = computeWeaponBayTexts(capitalWeapons, combineNoseAftWeaponBays);
            standardWeapTexts = computeWeaponBayTexts(standardWeapons, combineNoseAftWeaponBays);
            for (WeaponBayText wbt : capitalWeapTexts) {
                weaponLines += wbt.weapons.size();
            }
            for (WeaponBayText wbt : standardWeapTexts) {
                weaponLines += wbt.weapons.size();
            }
            if (weaponLines > WEAPON_LINES_FLUFF_LIMIT) {
                wsSheetSVG = "data/images/recordsheets/Warship_no_fluff.svg";
                noFluffImg = true;
            } else {
                wsSheetSVG = "data/images/recordsheets/Warship_default.svg";
                noFluffImg = false;
            }
            // If we have to try again, combine nose/aft weapon bays
            combineNoseAftWeaponBays = true;
            loopCount++;
        } while (weaponLines >= WEAPON_LINES_NOSE_AFT_COMBINE && loopCount <= 1);

        SVGDiagram diagram;
        diagram = ImageHelper.loadSVGImage(new File(wsSheetSVG));
        if (diagram == null) {
            System.out.println("Failed to open Warship SVG file! Path: ata/images/recordsheets/Warship_default.svg");
            return;
        }
        diagram.setDeviceViewport(
                new Rectangle(0, 0, (int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight()));

        try {
            // Set visibility based on techbase
            if (warship.isClan()) {
                SVGElement ele = diagram.getElement(WarshipPrintElements.BATTLE_ARMOR_TSPAN.getElementName());
                if (ele != null) {
                    ele.addAttribute("display", AnimationElement.AT_XML, "none");
                    ((Tspan) ele).setText("");
                }
                ((Text) ele.getParent()).rebuild();
                ele = diagram.getElement(WarshipPrintElements.BATTLE_ARMOR.getElementName());
                if (ele != null) {
                    ele.addAttribute("display", AnimationElement.AT_XML, "none");
                }
            } else {
                SVGElement ele = diagram.getElement(WarshipPrintElements.ELEMENTALS_TSPAN.getElementName());
                if (ele != null) {
                    ele.addAttribute("display", AnimationElement.AT_XML, "none");
                    ((Tspan) ele).setText("");
                }
                ((Text) ele.getParent()).rebuild();
                ele = diagram.getElement(WarshipPrintElements.ELEMENTALS.getElementName());
                if (ele != null) {
                    ele.addAttribute("display", AnimationElement.AT_XML, "none");
                }
            }

            for (WarshipPrintElements element : WarshipPrintElements.values()) {
                SVGElement svgEle = diagram.getElement(element.getElementName());

                // Ignore elements that don't exist
                if (svgEle == null) {
                    continue;
                }

                // Process element
                if (element.getType().equals(ElementType.TEXT)) {
                    setElementTextValue((Text) svgEle, element);
                } else if (element.getType().equals(ElementType.ARMOR)) {
                    printArmorRegion((Rect) svgEle, element);
                } else if (element.getType().equals(ElementType.INTERNALS)) {
                    printInternalRegion((Rect) svgEle, element);
                } else if (element.getType().equals(ElementType.EQUIPMENT)) {
                    printEquipmentRegion((Rect) svgEle, element);
                } else if (element.getType().equals(ElementType.IMAGE) && !noFluffImg) {
                    printImage((Rect) svgEle, element);
                } else if (element.getType().equals(ElementType.TSPAN)) {
                    // Do nothing - these are just references to elements in the
                    // SVG
                } else {
                    // Error
                }
            }
            diagram.render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
        // g2d.scale(pageFormat.getImageableWidth(),
        // pageFormat.getImageableHeight());
    }

    /**
     * Get the text value to fill in for certain elements. These represent
     * simple text values, like gunnery/piloting skill or unit name.
     * 
     * @throws SVGException
     *
     */
    private void setElementTextValue(Text textEle, WarshipPrintElements element) throws SVGException {
        textEle.getContent().clear();

        String text = "";
        if (element.equals(WarshipPrintElements.TYPE)) {
            text = warship.getChassis() + " " + warship.getModel();
        } else if (element.equals(WarshipPrintElements.TONNAGE)) {
            text = NumberFormat.getNumberInstance(Locale.getDefault()).format(warship.getWeight());
        } else if (element.equals(WarshipPrintElements.TECH_BASE)) {
            if (warship.isClan()) {
                text = "Clan";

            } else {
                text = "Inner Sphere";
            }
        } else if (element.equals(WarshipPrintElements.SAFE_THRUST)) {
            text = warship.getOriginalWalkMP() + "";
        } else if (element.equals(WarshipPrintElements.MAX_THRUST)) {
            text = (int) Math.ceil(warship.getOriginalWalkMP() * 1.5) + "";
        } else if (element.equals(WarshipPrintElements.DS_CAPACITY)) {
            text = warship.getDockingCollars().size() + "";
        } else if (element.equals(WarshipPrintElements.FIGHTERS)) {
            int asfCapacity = 0;
            for (Bay t : warship.getTransportBays()) {
                if (t instanceof ASFBay) {
                    asfCapacity += ((ASFBay) t).getCapacity();
                }
            }
            text = asfCapacity + "";
        } else if (element.equals(WarshipPrintElements.SMALL_CRAFT)) {
            int scCapacity = 0;
            for (Bay t : warship.getTransportBays()) {
                if (t instanceof SmallCraftBay) {
                    scCapacity += ((SmallCraftBay) t).getCapacity();
                }
            }
            text = scCapacity + "";
        } else if (element.equals(WarshipPrintElements.LAUNCH_RATES)) {
            int asfDoors = 0;
            int scDoors = 0;
            for (Bay t : warship.getTransportBays()) {
                if (t instanceof SmallCraftBay) {
                    scDoors += ((SmallCraftBay) t).getDoors();
                }
                if (t instanceof ASFBay) {
                    asfDoors += ((ASFBay) t).getDoors();
                }
            }
            text = asfDoors + " / " + scDoors;
        } else if (element.equals(WarshipPrintElements.NOSE_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_NOSE) + " (" + warship.getOArmor(Warship.LOC_NOSE) + ")";
        } else if (element.equals(WarshipPrintElements.FL_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_FLS) + " (" + warship.getOArmor(Warship.LOC_FLS) + ")";
        } else if (element.equals(WarshipPrintElements.FR_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_FRS) + " (" + warship.getOArmor(Warship.LOC_FRS) + ")";
        } else if (element.equals(WarshipPrintElements.AL_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_ALS) + " (" + warship.getOArmor(Warship.LOC_ALS) + ")";
        } else if (element.equals(WarshipPrintElements.AR_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_ARS) + " (" + warship.getOArmor(Warship.LOC_ARS) + ")";
        } else if (element.equals(WarshipPrintElements.AFT_ARMOR_TEXT)) {
            text = warship.getThresh(Warship.LOC_AFT) + " (" + warship.getOArmor(Warship.LOC_AFT) + ")";
        } else if (element.equals(WarshipPrintElements.SI_TEXT)) {
            text = warship.getSI() + "";
        } else if (element.equals(WarshipPrintElements.KF_TEXT)) {
            text = warship.getKFIntegrity() + "";
        } else if (element.equals(WarshipPrintElements.SAIL_TEXT)) {
            text = warship.getSailIntegrity() + "";
        } else if (element.equals(WarshipPrintElements.DC_TEXT)) {
            text = warship.getDockingCollars().size() + "";
        } else if (element.equals(WarshipPrintElements.GUNNERY_SKILL)) {
            text = ""; // TODO: Print Pilots
        } else if (element.equals(WarshipPrintElements.PILOTING_SKILL)) {
            text = ""; // TODO: Print Pilots
        } else if (element.equals(WarshipPrintElements.CREW)) {
            text = warship.getNCrew() + "";
        } else if (element.equals(WarshipPrintElements.PASSENGERS)) {
            text = warship.getNPassenger() + "";
        } else if (element.equals(WarshipPrintElements.OTHER)) {
            text = warship.getNOtherCrew() + "";
        } else if (element.equals(WarshipPrintElements.LIFE_BOATS)) {
            text = warship.getLifeBoats() + "";
        } else if (element.equals(WarshipPrintElements.ESCAPE_PODS)) {
            text = warship.getEscapePods() + "";
        } else if (element.equals(WarshipPrintElements.HEATSINKS)) {
            text = warship.getHeatSinks() + "";
        } else if (element.equals(WarshipPrintElements.DOUBLE_HEATSINKS)) {
            // Double heatsinks
            if (warship.getHeatType() == 1) {
                text += "(" + warship.getHeatSinks() * 2 + ")";
            }
        } else if (element.equals(WarshipPrintElements.NOSE_HEAT)) {
            text = warship.getHeatInArc(Warship.LOC_NOSE, false) + "";
        } else if (element.equals(WarshipPrintElements.AFT_HEAT)) {
            text = warship.getHeatInArc(Warship.LOC_AFT, false) + "";
        } else if (element.equals(WarshipPrintElements.FORE_HEAT)) {
            text = warship.getHeatInArc(Warship.LOC_FLS, false) + " / " + warship.getHeatInArc(Warship.LOC_FRS, false);
        } else if (element.equals(WarshipPrintElements.AFT_LR_HEAT)) {
            text = warship.getHeatInArc(Warship.LOC_ALS, false) + " / " + warship.getHeatInArc(Warship.LOC_ARS, false);
        } else if (element.equals(WarshipPrintElements.BROADSIDE_HEAT)) {
            text = warship.getHeatInArc(Warship.LOC_LBS, false) + " / " + warship.getHeatInArc(Warship.LOC_RBS, false);
        } else if (element.equals(WarshipPrintElements.ELEMENTALS) 
                || element.equals(WarshipPrintElements.BATTLE_ARMOR)) {
            text = warship.getNBattleArmor() + "";
        } else if (element.equals(WarshipPrintElements.MARINES)) {
            text = warship.getNMarines() + "";
        } else if (element.equals(WarshipPrintElements.COST)) {
            text = NumberFormat.getNumberInstance(Locale.getDefault()).format(warship.getCost(false));
        } else if (element.equals(WarshipPrintElements.BV)) {
            text = NumberFormat.getNumberInstance(Locale.getDefault()).format(warship.calculateBattleValue(true, true));
        } else {
            // Print a blank for any element we don't currently handle
        }
        textEle.appendText(text);
        textEle.rebuild();
    }
    
    /**
     * Print an image, like the fluff image.
     *
     * @param boundingRect
     * @param element
     * @throws SVGException
     */
    private void printImage(Rect boundingRect, WarshipPrintElements element) throws SVGException {
        Rectangle2D bbox = boundingRect.getBoundingBox();
        int viewWidth = (int)bbox.getWidth();
        int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();
        
        File fluffFile = new File(warship.getFluff().getMMLImagePath());
        // Don't do anything if we don't have a good file.
        if (!fluffFile.exists()) {
            return;
        }

        ImageSVG img = new ImageSVG();
        img.addAttribute("x", AnimationElement.AT_XML, viewX + "");
        img.addAttribute("y", AnimationElement.AT_XML, viewY + "");
        img.addAttribute("width", AnimationElement.AT_XML, viewWidth + "");
        img.addAttribute("height", AnimationElement.AT_XML, viewHeight + "");
        // Not sure why this is necessary, but the xlink:href attribute needs to be set for ImageSVG to work
        // However, the actual URI that gets used is defined in xml:base
        img.addAttribute("xlink:href", AnimationElement.AT_XML, fluffFile.toURI().toString());
        img.addAttribute("xml:base", AnimationElement.AT_XML, fluffFile.toURI().toString());        
        boundingRect.getRoot().loaderAddChild(null, img);
        img.updateTime(0);
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param svgRect
     * @param element
     * @throws SVGException
     */
    private void printInternalRegion(Rect svgRect, WarshipPrintElements element) throws SVGException {
        Rectangle2D bbox = svgRect.getBoundingBox();

        int structure = 0;
        int pipsPerBlock = 10;
        if (element.equals(WarshipPrintElements.SI_PIPS)) {
            structure = warship.get0SI();
            pipsPerBlock = 100;
        } else if (element.equals(WarshipPrintElements.KF_PIPS)) {
            structure = warship.getKFIntegrity();
            pipsPerBlock = 30;
        } else if (element.equals(WarshipPrintElements.SAIL_PIPS)) {
            structure = warship.getSailIntegrity();
        } else if (element.equals(WarshipPrintElements.DC_PIPS)) {
            structure = warship.getDockingCollars().size();
        }

        // Print in two blocks
        if (structure > pipsPerBlock) {
            // Block 1
            int pips = structure / 2;
            int startX, startY;
            double aspectRatio = (bbox.getWidth() / bbox.getHeight());
            if (aspectRatio >= 1) { // Landscape - 2 columns
                startX = (int) bbox.getX() + (int) (bbox.getWidth() / 4 + 0.5) - (PIPS_PER_ROW * IS_PIP_WIDTH / 2);
                startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            } else { // Portrait - stacked 1 atop another
                startX = (int) bbox.getX() + (int) (bbox.getWidth() / 2 + 0.5) - (PIPS_PER_ROW * IS_PIP_WIDTH / 2);
                startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            }
            printPipBlock(startX, startY, svgRect.getParent(), pips, IS_PIP_WIDTH, IS_PIP_HEIGHT, "white");

            // Block 2
            if (aspectRatio >= 1) { // Landscape - 2 columns
                startX = (int) bbox.getX() + (int) (3 * bbox.getWidth() / 4 + 0.5) - (PIPS_PER_ROW * IS_PIP_WIDTH / 2);
            } else { // Portrait - stacked 1 atop another
                startY = (int) bbox.getY() + IS_PIP_HEIGHT * (pips / PIPS_PER_ROW + 1);
            }
            pips = (int) Math.ceil(structure / 2.0);
            printPipBlock(startX, startY, svgRect.getParent(), pips, IS_PIP_WIDTH, IS_PIP_HEIGHT, "white");
        } else { // Print in one block
            int startX = (int) bbox.getX() + (int) (bbox.getWidth() / 2 + 0.5) - (PIPS_PER_ROW * IS_PIP_WIDTH / 2);
            int startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            printPipBlock(startX, startY, svgRect.getParent(), structure, IS_PIP_WIDTH, IS_PIP_HEIGHT, "white");
        }
    }

    /**
     * Method to determine rectangle grid for armor or internal pips and draw
     * it.
     *
     * @param svgRect
     * @param element
     * @throws SVGException
     */
    private void printArmorRegion(Rect svgRect, WarshipPrintElements element) throws SVGException {
        Rectangle2D bbox = svgRect.getBoundingBox();

        int loc = 0;
        if (element.equals(WarshipPrintElements.NOSE_ARMOR_PIPS)) {
            loc = Warship.LOC_NOSE;
        } else if (element.equals(WarshipPrintElements.FL_ARMOR_PIPS)) {
            loc = Warship.LOC_FLS;
        } else if (element.equals(WarshipPrintElements.FR_ARMOR_PIPS)) {
            loc = Warship.LOC_FRS;
        } else if (element.equals(WarshipPrintElements.AL_ARMOR_PIPS)) {
            loc = Warship.LOC_ALS;
        } else if (element.equals(WarshipPrintElements.AR_ARMOR_PIPS)) {
            loc = Warship.LOC_ARS;
        } else if (element.equals(WarshipPrintElements.AFT_ARMOR_PIPS)) {
            loc = Warship.LOC_AFT;
        }
        int armor = warship.getOArmor(loc);

        double pipWidth = ARMOR_PIP_WIDTH;
        double pipHeight = ARMOR_PIP_HEIGHT;;

        int halfBlockHeight = (int) ((MAX_PIP_ROWS * pipHeight / 2f) + 0.5);
        int halfBlockWidth = (int) ((PIPS_PER_ROW * pipWidth / 2f) + 0.5);

        // Armor comes in blocks of 100 pips
        int numBlocks = (int) Math.ceil(armor / 100f);
        double aspectRatio = bbox.getWidth() / bbox.getHeight();
        double startX, startY;
        // If we have a large number of blocks, we need to shrinke the size
        if (numBlocks > 6) {
            pipWidth = ARMOR_PIP_WIDTH_SMALL;
            pipHeight = ARMOR_PIP_HEIGHT_SMALL;
            halfBlockHeight = (int) ((MAX_PIP_ROWS * pipHeight / 2f) + 0.5);
            halfBlockWidth = (int) ((PIPS_PER_ROW * pipWidth / 2f) + 0.5);

            // Two columns, because we have at least 7 blocks
            int colBreak;
            if (numBlocks < 8) {
                colBreak = 3;
            } else if (numBlocks < 10) {
                colBreak = 4;
            } else {
                colBreak = 5;
            }
            
            if (aspectRatio >= 1) { // Landscape
                startX = bbox.getX();
                startY = bbox.getY() + (bbox.getHeight() * 0.25);
            } else { // Portrait
                startX = bbox.getX() + (bbox.getWidth() * 0.25);
                startY = bbox.getY();    
            }
            int count = 0;
            while (armor > 0) {
                armor = printPipBlock(startX, startY, svgRect.getParent(), armor, pipWidth, pipHeight, "none");
                count++;
                if (aspectRatio >= 1) { // Landscape
                    // Have last block in middle
                    if ((armor <= 100) && ((numBlocks % 2) == 1)) {
                        startY = bbox.getY() + (bbox.getHeight() * 0.25) + halfBlockHeight + pipHeight;
                        startX += (PIPS_PER_ROW + 1) * pipWidth; 
                    } else if (count == colBreak) { // Check for start of new column
                        startY += (MAX_PIP_ROWS + 1) * pipHeight;
                        startX = bbox.getX();
                    } else {
                        startX += (PIPS_PER_ROW + 1) * pipWidth;
                    }
                } else { // Portrait
                    // Have last block in middle
                    if ((armor <= 100) && ((numBlocks % 2) == 1)) {
                        startX = bbox.getX() + (bbox.getWidth() * 0.25) + halfBlockWidth + pipWidth;
                        startY += (MAX_PIP_ROWS + 1) * pipHeight;
                    } else if (count == colBreak) { // Check for start of new column
                        startX += (PIPS_PER_ROW + 1) * pipWidth;
                        startY = bbox.getY();
                    } else {
                        startY += (MAX_PIP_ROWS + 1) * pipHeight;
                    }
                }
            }            
        } else if (numBlocks <= 3) { // Use a single column if we only have a small number blocks
            if (aspectRatio >= 1) { // Landscape
                if (numBlocks == 1) {
                    startX = bbox.getX() + (bbox.getWidth() * 0.5) - halfBlockWidth;
                } else if (numBlocks == 2) { // Center blocks if we have an even
                                             // number
                    startX = bbox.getX() + (bbox.getWidth() * 0.333) - halfBlockWidth;
                } else {
                    startX = bbox.getX();
                }
                startY = (int) bbox.getY() + (bbox.getHeight() / 2 + 0.5) - halfBlockHeight;
            } else { // Portrait
                startX = (int) bbox.getX() + (bbox.getWidth() / 2 + 0.5) - halfBlockWidth;
                if (numBlocks == 1) {
                    startY =  bbox.getY() + (bbox.getHeight() * 0.33) - halfBlockHeight;
                } else if (numBlocks == 2) { // Center blocks if we have an even number
                    startY = bbox.getY() + (bbox.getHeight() * 0.5) - halfBlockHeight;
                } else {
                    startY = bbox.getY();
                }
            }
            while (armor > 0) {
                armor = printPipBlock(startX, startY, svgRect.getParent(), armor, pipWidth, pipHeight,
                        "none");
                if (aspectRatio >= 1) { // Landscape
                    startX += (PIPS_PER_ROW + 1) * pipWidth;
                } else { // Portrait
                    startY += (MAX_PIP_ROWS + 1) * pipHeight;
                }
            }
        } else { // Double column layout
            int colBreak;
            if (numBlocks < 6) {
                colBreak = 2;
            } else {
                colBreak = 3;
            }
            startX = bbox.getX();
            startY = bbox.getY();
            int count = 0;
            while (armor > 0) {
                armor = printPipBlock(startX, startY, svgRect.getParent(), armor, pipWidth, pipHeight, "none");
                count++;
                if (aspectRatio >= 1) { // Landscape
                    // Have last block in middle
                    if ((armor <= 100) && (numBlocks == 5)) {
                        startY = bbox.getY() + (bbox.getHeight() * 0.5) - halfBlockHeight;
                    }
                    // Check for start of new column
                    if (count == colBreak) {
                        startY = bbox.getY() + (MAX_PIP_ROWS + 1) * pipHeight;
                        startX = bbox.getX();
                    } else {
                        startX += (PIPS_PER_ROW + 1) * pipWidth;
                    }
                } else { // Portrait
                    // Have last block in middle
                    if ((armor <= 100) && (numBlocks == 5)) {
                        startX = bbox.getX() + (bbox.getWidth() * 0.5) - halfBlockWidth;
                    }
                    // Check for start of new column
                    if (count == colBreak) {
                        startX = bbox.getX() + (PIPS_PER_ROW + 1) * pipWidth;
                        startY = bbox.getY();
                    } else {
                        startY += (MAX_PIP_ROWS + 1) * pipHeight;
                    }
                }
            }
        }
    }

    /**
     * Helper function to print a armor pip block. Can print up to 100 points of
     * armor. Any unprinted armor pips are returned.
     *
     * @param startX
     * @param startY
     * @param parent
     * @param numPips
     * @return The Y location of the end of the block
     * @throws SVGException
     */
    private int printPipBlock(double startX, double startY, SVGElement parent, int numPips, double pipWidth,
            double pipHeight, String fillColor) throws SVGException {

        double currX, currY;
        currY = startY;
        for (int row = 0; row < 10; row++) {
            int numRowPips = Math.min(numPips, PIPS_PER_ROW);
            // Adjust row start if it's not a complete row
            currX = startX + ((10 - numRowPips) / 2f * pipWidth + 0.5);
            for (int col = 0; col < numRowPips; col++) {
                Rect svgRect = new Rect();
                svgRect.addAttribute("x", AnimationElement.AT_XML, currX + "");
                svgRect.addAttribute("y", AnimationElement.AT_XML, currY + "");
                svgRect.addAttribute("width", AnimationElement.AT_XML, pipWidth + "");
                svgRect.addAttribute("height", AnimationElement.AT_XML, pipHeight + "");
                svgRect.addAttribute("stroke", AnimationElement.AT_CSS, "black");
                svgRect.addAttribute("stroke-width", AnimationElement.AT_CSS, "0.5");
                svgRect.addAttribute("fill", AnimationElement.AT_CSS, fillColor);
                svgRect.updateTime(0);
                parent.loaderAddChild(null, svgRect);
                parent.updateTime(0);
                currX += pipWidth;
                numPips--;
                // Check to see if we're done
                if (numPips <= 0) {
                    return 0;
                }
            }
            currY += pipHeight;
        }
        return numPips;
    }

    /**
     * Print out all information related to weapons and equipment.
     *
     * @param svgRect   The bounding area that equipment info can be drawn into.
     * @param element   The print element we are printing for.
     *
     * @throws SVGException
     */
    private void printEquipmentRegion(Rect svgRect, WarshipPrintElements element) throws SVGException {
        Rectangle2D bbox = svgRect.getBoundingBox();
        viewWidth = (int)bbox.getWidth();
        viewHeight = (int)bbox.getHeight();
        viewX = (int)bbox.getX();
        viewY = (int)bbox.getY();
        
        int nameColWidth  = (int)(viewWidth * 0.4);
        int otherColWidth = (int)(viewWidth * 0.11);
        int otherColOffset = (int)(otherColWidth / 4 + 0.5);

        // Column positions
        nameX = viewX;
        locX = viewX + nameColWidth + 0 * otherColWidth + otherColOffset;
        htX  = viewX + nameColWidth + 1 * otherColWidth + otherColOffset;
        srvX = viewX + nameColWidth + 2 * otherColWidth + otherColOffset;
        mrvX = viewX + nameColWidth + 3 * otherColWidth + otherColOffset;
        lrvX = viewX + nameColWidth + 4 * otherColWidth + otherColOffset;
        ervX = viewX + nameColWidth + 5 * otherColWidth + otherColOffset;

        cargoPrintType = PrintType.NORMAL;
        gravPrintType = PrintType.NORMAL;

        eqNormalSize = 6;
        eqHeaderSize = 8;

        SVGElement canvas = svgRect.getRoot();
        int lineHeight;
        int currY = viewY + 10;

        adjustEquipmentFont(capitalWeapTexts, standardWeapTexts, canvas);

        // Print Capital Scale Weapon Info
        if (capitalWeapTexts.size() > 0) {
            // Capital Scale line
            lineHeight = addTextElement(canvas, nameX, currY, "Capital Scale", eqNormalSize, "start", "bold");
            addTextElement(canvas, srvX, currY, "(1-12)",  eqNormalSize, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "(13-24)", eqNormalSize, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "(25-40)", eqNormalSize, "middle", "bold");
            addTextElement(canvas, ervX, currY, "(41-50)", eqNormalSize, "middle", "bold");
            currY += lineHeight + (int)(0.5 * lineHeight);
    
            // Capital Bay Line
            lineHeight = addTextElement(canvas, nameX, currY, "Bay", eqHeaderSize, "start", "bold");
            addTextElement(canvas, locX, currY, "Loc", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, htX,  currY, "Ht", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, srvX, currY, "SRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "MRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "LRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, ervX, currY, "ERV", eqHeaderSize, "middle", "bold");
            currY += lineHeight;

            currY = printWeaponsText(capitalWeapTexts, true, canvas, currY);

            currY += lineHeight;
        }

        // Print Standard Scale Weapon Info
        if (standardWeapTexts.size() > 0) {
            // Standard Scale line
            lineHeight = addTextElement(canvas, nameX, currY, "Standard Scale", eqNormalSize, "start", "bold");
            addTextElement(canvas, srvX, currY, "(1-6)",  eqNormalSize, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "(7-12)", eqNormalSize, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "(13-20)", eqNormalSize, "middle", "bold");
            addTextElement(canvas, ervX, currY, "(21-25)", eqNormalSize, "middle", "bold");
            currY += lineHeight + (int)(0.5 * lineHeight);
    
            // Standard Bay Line
            lineHeight = addTextElement(canvas, nameX, currY, "Bay", eqHeaderSize, "start", "bold");
            addTextElement(canvas, locX, currY, "Loc", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, htX,  currY, "Ht", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, srvX, currY, "SRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "MRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "LRV", eqHeaderSize, "middle", "bold");
            addTextElement(canvas, ervX, currY, "ERV", eqHeaderSize, "middle", "bold");
            currY += lineHeight;

            currY = printWeaponsText(standardWeapTexts, false, canvas, currY);
            currY += lineHeight;
        }

        // Print GravDeck Info
        currY = printGravDecks(canvas, currY, gravPrintType);

        // Print Transport Bay Info
        currY = printCargoInfo(canvas, currY, cargoPrintType);
        
    }

    /**
     * We want the equipment font to be as large as possible, some sometimes we need to adjust things to make the text
     * fit.  This involves estimating how large the text area will be and adjust font, and also determining is grav
     * deck and cargo info should be side-by-side.
     *
     * @param capitalWeapTexts
     * @param standardWeapTexts
     * @param canvas
     * @throws SVGException
     */
    private void adjustEquipmentFont(List<WeaponBayText> capitalWeapTexts, List<WeaponBayText> standardWeapTexts,
            SVGElement canvas) throws SVGException {
        // Try to Estimate size of equipment block, to scale text
        //   Estimating lines between section as 1 lines, and header lines as 3
        int numGravDeckLines = 2 + warship.getTotalGravDeck();
        int numCargoLines = 2 + warship.getTransportBays().size();

        int weaponLines = 4 + 4;
        for (WeaponBayText wbt : capitalWeapTexts) {
            weaponLines += wbt.weapons.size();
        }
        for (WeaponBayText wbt : standardWeapTexts) {
            weaponLines += wbt.weapons.size();
        }
        
        Text newText = new Text();
        newText.appendText("Naval AC 25 (100 rounds)");        
        newText.addAttribute("x", AnimationElement.AT_XML, "0");
        newText.addAttribute("y", AnimationElement.AT_XML, "0");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, eqNormalSize + "");
        canvas.loaderAddChild(null, newText);
        newText.rebuild();
        
        double textHeight = newText.getShape().getBounds().getHeight();
        if ((weaponLines * textHeight) > (viewHeight * 0.75)) {
            gravPrintType = PrintType.SIDE_BY_SIDE_FIRST;
            cargoPrintType = PrintType.SIDE_BY_SIDE_SECOND;
            numGravDeckLines = Math.max(numGravDeckLines, numCargoLines);
            numCargoLines = numGravDeckLines;
        }
        int totalLines = weaponLines + numGravDeckLines + numCargoLines;

        while (totalLines * textHeight >= viewHeight && eqNormalSize > 5) {
            eqNormalSize--;
            eqHeaderSize--;
            newText.removeAttribute("font-size", AnimationElement.AT_XML);
            newText.addAttribute("font-size", AnimationElement.AT_XML, eqNormalSize + "");
            newText.rebuild();
            textHeight = newText.getShape().getBounds().getHeight();
            
            if ((weaponLines * textHeight) > (viewHeight * 0.75)) {
                gravPrintType = PrintType.SIDE_BY_SIDE_FIRST;
                cargoPrintType = PrintType.SIDE_BY_SIDE_SECOND;
                numGravDeckLines = Math.max(numGravDeckLines, numCargoLines);
                numCargoLines = numGravDeckLines;
            } else {
                gravPrintType = PrintType.NORMAL;
                cargoPrintType = PrintType.NORMAL;
                numGravDeckLines = 2 + warship.getTotalGravDeck();
                numCargoLines = 2 + warship.getTransportBays().size();                
            }
        }
        canvas.removeChild(newText);
    }

    /**
     * Convenience method for printing information related to grav decks
     * @param canvas
     * @param currY
     * @return
     * @throws SVGException 
     */
    private int printGravDecks(SVGElement canvas, int currY, PrintType pt) throws SVGException {
        int lineHeight;
        int printX;
        int origY = currY;
        switch (pt) {
            case NORMAL:
            case SIDE_BY_SIDE_FIRST:
            default:
                printX = nameX;
                break;
            case SIDE_BY_SIDE_SECOND:
                printX = viewWidth / 2;
                break;
        }
        if (warship.getTotalGravDeck() > 0) {
            lineHeight = addTextElement(canvas, printX, currY, "Grav Decks:", eqNormalSize, "start", "bold");
            currY += lineHeight;
            int count = 1;
            for (int size : warship.getGravDecks()) {
                String gravString = "Grav Deck #" + count + ": " + size + "-meters";
                lineHeight = addTextElement(canvas, printX, currY, gravString, eqNormalSize, "start");
                currY += lineHeight;
                count++;
            }
            currY += lineHeight;
        }
        
        switch (pt) {
            case NORMAL:
            default:
                return currY;
            case SIDE_BY_SIDE_FIRST:
            case SIDE_BY_SIDE_SECOND:
                return origY;
        }
    }
    
    /**
     * Convenience method for printing infor related to cargo & transport bays.
     *
     * @param canvas
     * @param currY
     * @return
     * @throws SVGException
     */
    private int printCargoInfo(SVGElement canvas, int currY, PrintType pt) throws SVGException {
        int lineHeight;
        int printX;
        int origY = currY;
        switch (pt) {
            case NORMAL:
            case SIDE_BY_SIDE_FIRST:
            default:
                printX = nameX;
                break;
            case SIDE_BY_SIDE_SECOND:
                printX = viewWidth / 2;
                break;
        }
        if (warship.getTransportBays().size() > 0) {
            lineHeight = addTextElement(canvas, printX, currY, "Cargo:", eqNormalSize, "start", "bold");
            currY += lineHeight;
            // We can have multiple Bay instances within one conceptual bay on the ship
            // We need to gather all bays with the same ID to print out the string
            Map<Integer, List<Bay>> bayMap = new TreeMap<>();
            for (Bay bay : warship.getTransportBays()) {
                List<Bay> bays = bayMap.get(bay.getBayNumber());
                if (bays == null) {
                    bays = new ArrayList<>();
                    bays.add(bay);
                    bayMap.put(bay.getBayNumber(), bays);
                } else {
                    bays.add(bay);
                }
            }
            // Print each bay
            for (Integer bayNum : bayMap.keySet()) {
                StringBuilder bayTypeString = new StringBuilder();
                StringBuilder bayCapacityString = new StringBuilder();
                bayCapacityString.append(" (");
                List<Bay> bays = bayMap.get(bayNum);
                // Display larger storage first
                java.util.Collections.sort(bays, new Comparator<Bay>(){

                    @Override
                    public int compare(Bay b1, Bay b2) {
                        return (int)(b2.getCapacity() - b1.getCapacity());
                    }});
                int doors = 0;
                for (int i = 0; i < bays.size(); i++) {
                    Bay b = bays.get(i);
                    bayTypeString.append(b.getType());
                    if (b.getClass().getName().contains("Cargo")) {
                        double capacity = b.getCapacity();
                        int wholePart = (int)capacity;
                        double fractPart = capacity - wholePart;
                        if (fractPart == 0) {
                            bayCapacityString.append(String.format("%1$d", wholePart));
                        } else {
                            bayCapacityString.append(String.format("%1$.1f", b.getCapacity()));
                        }
                    } else {
                        bayCapacityString.append((int)b.getCapacity());
                    }
                    if (i + 1 <  bays.size()) {
                        bayTypeString.append("/");
                        bayCapacityString.append("/");
                    }
                    doors = Math.max(doors, b.getDoors());
                }
                bayCapacityString.append(")");
                String bayString = "Bay #" + bayNum + ": " + bayTypeString
                    + bayCapacityString + " (Doors " + doors + ")";
                lineHeight = addTextElement(canvas, printX, currY, bayString, eqNormalSize, "start");
                currY += lineHeight;
            }
            currY += lineHeight;
        }
        switch (pt) {
            case NORMAL:
            default:
                return currY;
            case SIDE_BY_SIDE_FIRST:
            case SIDE_BY_SIDE_SECOND:
                return origY;
        }
    }
    
    /**
     * Iterate through a list of weapons and create information about what weapons belong in what bays, how many, the
     * bay damage, and also condense entries when possible.
     * 
     * @param weapons
     * @return
     */
    private List<WeaponBayText> computeWeaponBayTexts(List<Mounted> weapons, boolean combineNoseAftBays) {
        // Collection info on weapons to print
        List<WeaponBayText> weaponBayTexts = new ArrayList<>();
        for (Mounted bay : weapons) {
            WeaponBayText wbt = new WeaponBayText(bay.getLocation(), combineNoseAftBays);
            for (Integer wId : bay.getBayWeapons()) {
                Mounted weap = warship.getEquipment(wId);
                wbt.addBayWeapon(weap);
            }
            // Combine or add
            boolean combined = false;
            for (WeaponBayText combine : weaponBayTexts) {
                if (combine.canCombine(wbt)) {
                    combine.combine(wbt);
                    combined = true;
                    break;
                }
            }
            if (!combined) {
                weaponBayTexts.add(wbt);
            }
        }
        Collections.sort(weaponBayTexts);

        return weaponBayTexts;
    }

    /**
     * Iterate through all of the given weapons and print out information in the inventory table.
     *
     * @param weapons       The weapons to print info for.
     * @param isCapital     Determines damage scale.
     * @param canvas        The SVGElement to add text to.
     * @param currY         The height location for text elements.
     *
     * @return  The current height after all text has been added.
     * @throws SVGException
     */
    private int printWeaponsText(List<WeaponBayText> weaponBayTexts, boolean isCapital, SVGElement canvas, int currY)
            throws SVGException {
        // Print info
        for (WeaponBayText wbt : weaponBayTexts) {
            boolean first = true;
            int numBayWeapons = wbt.weapons.keySet().size();
            int bayHeat = 0;
            double baySRV, bayMRV, bayLRV, bayERV;
            baySRV = bayMRV = bayLRV = bayERV = 0;
            double standardBaySRV, standardBayMRV, standardBayLRV, standardBayERV;
            standardBaySRV = standardBayMRV = standardBayLRV = standardBayERV = 0;
            for (WeaponType wtype : wbt.weapons.keySet()) {
                int numWeapons = wbt.weapons.get(wtype);
                bayHeat += wtype.getHeat() * numWeapons;
                if (isCapital) {
                    baySRV += wtype.getShortAV() * numWeapons;
                    bayMRV += wtype.getMedAV() * numWeapons;
                    bayLRV += wtype.getLongAV() * numWeapons;
                    bayERV += wtype.getExtAV() * numWeapons;
                } else {
                    baySRV += Math.round(wtype.getShortAV() * numWeapons / 10);
                    bayMRV += Math.round(wtype.getMedAV() * numWeapons / 10);
                    bayLRV += Math.round(wtype.getLongAV() * numWeapons / 10);
                    bayERV += Math.round(wtype.getExtAV() * numWeapons / 10);
                    standardBaySRV += wtype.getShortAV() * numWeapons;
                    standardBayMRV += wtype.getMedAV() * numWeapons;
                    standardBayLRV += wtype.getLongAV() * numWeapons;
                    standardBayERV += wtype.getExtAV() * numWeapons;
                }
            }

            for (WeaponType wtype : wbt.weapons.keySet()) {
                String locString = "";
                for (int i = 0; i < wbt.loc.size(); i++) {
                    locString += warship.getLocationAbbr(wbt.loc.get(i));
                    if (i + 1 < wbt.loc.size()) {
                        locString += "/";
                    }
                }
                String nameString;
                if (wbt.weaponAmmo.containsKey(wtype)) {
                    Mounted ammo = wbt.weaponAmmo.get(wtype);
                    nameString = wtype.getName() + " (" + ammo.getBaseShotsLeft() + " " + "rounds)";
                } else {
                    nameString = wtype.getName();
                }
                if (first & numBayWeapons > 1) {
                    nameString += ",";
                }
                currY = addWeaponText(canvas, first, currY, wbt.weapons.get(wtype), nameString, isCapital,
                        locString, bayHeat, new double[] { baySRV, bayMRV, bayLRV, bayERV },
                        new double[] { standardBaySRV, standardBayMRV, standardBayLRV, standardBayERV });
                first = false;
            }
        }

        return currY;
    }
    
    /**
     * Convenience method for adding the inventory table row for a weapon. This consists of a series of text elements
     * that cover all of the required weapon stats.
     * 
     * @param canvas    The element to add the text elements to.
     * @param first     Flag that determines if this is the first weapon in a bay; subsequent weapons are indented
     * @param currY     The height for the text element.
     * @param num       Number of weapons of this type, for damage computations
     * @param name      The name of the weapon.
     * @param isCapital Determines damage scale.
     * @param loc       The location, in abbreviated form.
     * @param heat      Heat value.
     * @param srv       Short range attack value.
     * @param mrv       Medium range attack value.
     * @param lrv       Long range attack value.
     * @param erv       Extreme range attack value.
     *
     * @return  The height of text added, used for layout.
     * @throws SVGException
     */
    private int addWeaponText(SVGElement canvas, boolean first, int currY, int num, String name, boolean isCapital,
            String loc, int heat, double[] capitalAV, double[] standardAV) throws SVGException {
        String srvTxt, mrvTxt, lrvTxt, ervTxt;
        String slSRV, slMRV, slLRV, slERV;
        slSRV = slMRV = slLRV = slERV = "";
        boolean secondLine = false;
        if (isCapital) { // Print out capital damage for weapon total
            srvTxt = capitalAV[0] == 0 ? "-" : (int)capitalAV[0] + "";
            mrvTxt = capitalAV[1] == 0 ? "-" : (int)capitalAV[1] + "";
            lrvTxt = capitalAV[2] == 0 ? "-" : (int)capitalAV[2] + "";
            ervTxt = capitalAV[3] == 0 ? "-" : (int)capitalAV[3] + "";
        } else { // Print out capital and standard damages
            if (standardAV[0] >= 100 && standardAV[1] >= 100) {
                secondLine = true;
                srvTxt = capitalAV[0] == 0 ? "-" : (int)capitalAV[0] + "";
                mrvTxt = capitalAV[1] == 0 ? "-" : (int)capitalAV[1] + "";
                lrvTxt = capitalAV[2] == 0 ? "-" : (int)capitalAV[2] + "";
                ervTxt = capitalAV[3] == 0 ? "-" : (int)capitalAV[3] + "";
                slSRV =  capitalAV[0] == 0 ? "" : " (" + standardAV[0] + ")";
                slMRV =  capitalAV[1] == 0 ? "" : " (" + standardAV[1] + ")";
                slLRV =  capitalAV[2] == 0 ? "" : " (" + standardAV[2] + ")";
                slERV =  capitalAV[3] == 0 ? "" : " (" + standardAV[3] + ")";
            } else {
                srvTxt = capitalAV[0] == 0 ? "-" : (int)capitalAV[0] + " (" + standardAV[0] + ")";
                mrvTxt = capitalAV[1] == 0 ? "-" : (int)capitalAV[1] + " (" + standardAV[1] + ")";
                lrvTxt = capitalAV[2] == 0 ? "-" : (int)capitalAV[2] + " (" + standardAV[2] + ")";
                ervTxt = capitalAV[3] == 0 ? "-" : (int)capitalAV[3] + " (" + standardAV[3] + ")";
            }
        }
        String nameString = num + "  " + name;
        String heatTxt;
        int localNameX = nameX;
        if (!first) {
            localNameX += 5;
            loc = "";
            heatTxt = "";
            srvTxt = mrvTxt = lrvTxt = ervTxt = "";
        } else {
            heatTxt = heat + "";
        }

        int lineHeight = addTextElement(canvas, localNameX, currY, nameString, eqNormalSize, "start");
        addTextElement(canvas, locX, currY, loc,     eqNormalSize, "middle");
        addTextElement(canvas, htX,  currY, heatTxt, eqNormalSize, "middle");
        addTextElement(canvas, srvX, currY, srvTxt,  eqNormalSize, "middle");
        addTextElement(canvas, mrvX, currY, mrvTxt,  eqNormalSize, "middle");
        addTextElement(canvas, lrvX, currY, lrvTxt,  eqNormalSize, "middle");
        addTextElement(canvas, ervX, currY, ervTxt,  eqNormalSize, "middle");
        currY += lineHeight;
        if (secondLine) {
            addTextElement(canvas, srvX, currY, slSRV,  eqNormalSize, "middle");
            addTextElement(canvas, mrvX, currY, slMRV,  eqNormalSize, "middle");
            addTextElement(canvas, lrvX, currY, slLRV,  eqNormalSize, "middle");
            addTextElement(canvas, ervX, currY, slERV,  eqNormalSize, "middle");
            currY += lineHeight;
        }
        return currY;
    }
    
    /**
     *
     */
    private int addTextElement(SVGElement parent, int x, int y, String text, int fontSize, String anchor)
            throws SVGException {
        return addTextElement(parent, x, y, text, fontSize, anchor, "normal");
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
     * @return  The height of the text just added, to aid in layout.
     * @throws SVGException
     */
    private int addTextElement(SVGElement parent, int x, int y, String text, int fontSize, String anchor, String weight)
            throws SVGException {
        Text newText = new Text();
        newText.appendText(text);
        
        newText.addAttribute("x", AnimationElement.AT_XML, x + "");
        newText.addAttribute("y", AnimationElement.AT_XML, y + "");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "Eurostile");
        newText.addAttribute("font-size", AnimationElement.AT_XML, fontSize + "");
        newText.addAttribute("font-weight", AnimationElement.AT_XML, weight);
        newText.addAttribute("text-anchor", AnimationElement.AT_CSS, anchor);
        parent.loaderAddChild(null, newText);
        newText.rebuild();
        return newText.getShape().getBounds().height;
    }

}