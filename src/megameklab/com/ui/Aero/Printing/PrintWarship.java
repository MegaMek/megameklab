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
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

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

    public static int ARMOR_PIP_WIDTH = 5;
    public static int ARMOR_PIP_HEIGHT = 5;

    public static int IS_PIP_WIDTH = 3;
    public static int IS_PIP_HEIGHT = 3;

    public static int PIPS_PER_ROW = 10;
    public static int MAX_PIP_ROWS = 10;

    public static enum ElementType {
        TEXT, ARMOR, INTERNALS, EQUIPMENT, IMAGE, TSPAN;
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
     * A list of all warships to print.
     */
    private ArrayList<Warship> warshipList;

    PrinterJob masterPrintJob;
    
 // Column positions
    int nameX;
    int locX;
    int htX;
    int srvX;
    int mrvX;
    int lrvX;
    int ervX;
    
    int eqNormalSize = 6;
    int eqHeaderSize = 8;

    public PrintWarship(ArrayList<Warship> list, PrinterJob masterPrintJob) {
        warshipList = list;
        this.masterPrintJob = masterPrintJob;

    }

    /**
     * 
     * @param aset
     */
    public void print(HashPrintRequestAttributeSet aset) {

        try {
            for (int pos = 0; pos < warshipList.size(); pos++) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);
                warship = warshipList.get(pos);
                pj.setJobName(warship.getChassis() + " " + warship.getModel());

                try {
                    pj.print(aset);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.gc();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     */
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }

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

        SVGDiagram diagram;
        diagram = ImageHelper.loadSVGImage(new File("data/images/recordsheets/Warship_default.svg"));
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
                } else if (element.getType().equals(ElementType.IMAGE)) {
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
    @SuppressWarnings("unchecked")
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
        } else if (element.equals(WarshipPrintElements.ELEMENTALS)) {
            text = "0";
        } else if (element.equals(WarshipPrintElements.MARINES)) {
            text = "0";
        } else if (element.equals(WarshipPrintElements.COST)) {
            text = NumberFormat.getNumberInstance(Locale.getDefault()).format(warship.getCost(false));
        } else if (element.equals(WarshipPrintElements.BV)) {
            text = NumberFormat.getNumberInstance(Locale.getDefault()).format(warship.calculateBattleValue(true, true));
        } else {
            // Print a blank for any element we don't currently handle
        }
        textEle.getContent().add(text);
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
            int pips = (int) Math.floor(structure + 0f / pipsPerBlock);
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
            pips = (int) Math.ceil(structure + 0f / pipsPerBlock);
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

        int halfBlockHeight = (int) ((MAX_PIP_ROWS * ARMOR_PIP_HEIGHT / 2f) + 0.5);
        int halfBlockWidth = (int) ((PIPS_PER_ROW * ARMOR_PIP_WIDTH / 2f) + 0.5);

        // Armor comes in blocks of 100 pips
        int numBlocks = (int) Math.ceil(armor / 100f);
        double aspectRatio = bbox.getWidth() / bbox.getHeight();
        // Use a single column if we only have a small number blocks
        if (numBlocks <= 3) {
            int startX, startY;
            if (aspectRatio >= 1) { // Landscape
                if (numBlocks == 1) {
                    startX = (int) bbox.getX() + (int) (bbox.getWidth() * 0.5) - halfBlockWidth;
                } else if (numBlocks == 2) { // Center blocks if we have an even
                                             // number
                    startX = (int) bbox.getX() + (int) (bbox.getWidth() * 0.333) - halfBlockWidth;
                } else {
                    startX = (int) bbox.getX();
                }
                startY = (int) bbox.getY() + (int) (bbox.getHeight() / 2 + 0.5) - halfBlockHeight;
            } else { // Portrait
                startX = (int) bbox.getX() + (int) (bbox.getWidth() / 2 + 0.5) - halfBlockWidth;
                if (numBlocks == 1) {
                    startY = (int) bbox.getY() + (int) (bbox.getHeight() * 0.33) - halfBlockHeight;
                } else if (numBlocks == 2) { // Center blocks if we have an even
                                             // number
                    startY = (int) bbox.getY() + (int) (bbox.getHeight() * 0.5) - halfBlockHeight;
                } else {
                    startY = (int) bbox.getY();
                }
            }
            while (armor > 0) {
                armor = printPipBlock(startX, startY, svgRect.getParent(), armor, ARMOR_PIP_WIDTH, ARMOR_PIP_HEIGHT,
                        "none");
                if (aspectRatio >= 1) { // Landscape
                    startX += (PIPS_PER_ROW + 1) * ARMOR_PIP_WIDTH;
                } else { // Portrait
                    startY += (MAX_PIP_ROWS + 1) * ARMOR_PIP_HEIGHT;
                }
            }
        } else { // Double column layout

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
    private int printPipBlock(int startX, int startY, SVGElement parent, int numPips, int pipWidth, int pipHeight,
            String fillColor) throws SVGException {

        int currX, currY;
        currY = startY;
        for (int row = 0; row < 10; row++) {
            int numRowPips = Math.min(numPips, PIPS_PER_ROW);
            // Adjust row start if it's not a complete row
            currX = startX + (int) ((10 - numRowPips) / 2f * pipWidth + 0.5);
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
        int viewWidth = (int)bbox.getWidth();
        //int viewHeight = (int)bbox.getHeight();
        int viewX = (int)bbox.getX();
        int viewY = (int)bbox.getY();
        
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
        
        // Sort weapons in capital/standard
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
        
        SVGElement canvas = svgRect.getRoot();
        int lineHeight;
        int currY = viewY + 10;

        if (capitalWeapons.size() > 0) {
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
    
            currY = printWeaponsText(capitalWeapons, true, canvas, currY);
            
            currY += lineHeight;
        }
        
        if (standardWeapons.size() > 0) {
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
    
            currY = printWeaponsText(standardWeapons, false, canvas, currY);
            currY += lineHeight;
        }

        if (warship.getTotalGravDeck() > 0) {
            lineHeight = addTextElement(canvas, nameX, currY, "Grav Decks:", eqNormalSize, "start", "bold");
            currY += lineHeight;
            int count = 1;
            for (int i = 0; i < warship.getGravDeck(); i++) {
                String gravString = "Grav Deck #" + count + ": Standard";
                lineHeight = addTextElement(canvas, nameX, currY, gravString, eqNormalSize, "start");
                currY += lineHeight;
                count++;
            }
            for (int i = 0; i < warship.getGravDeckLarge(); i++) {
                String gravString = "Grav Deck #" + count + ": Large";
                lineHeight = addTextElement(canvas, nameX, currY, gravString, eqNormalSize, "start");
                currY += lineHeight;
                count++;
            }
            for (int i = 0; i < warship.getGravDeckHuge(); i++) {
                String gravString = "Grav Deck #" + count + ": Huge";
                lineHeight = addTextElement(canvas, nameX, currY, gravString, eqNormalSize, "start");
                currY += lineHeight;
                count++;
            }
        }
        currY += lineHeight;
        
        if (warship.getTransportBays().size() > 0) {
            lineHeight = addTextElement(canvas, nameX, currY, "Cargo:", eqNormalSize, "start", "bold");
            currY += lineHeight;
            int count = 1;
            for (Bay bay : warship.getTransportBays()) {
                String bayString = "Bay #" + count + ": " + bay.getType() 
                    + "  (" + (int)bay.getCapacity() + ") (Doors " + bay.getDoors() + ")";
                lineHeight = addTextElement(canvas, nameX, currY, bayString, eqNormalSize, "start");
                currY += lineHeight;
                count++;
            }
        }
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
    private int printWeaponsText(List<Mounted> weapons, boolean isCapital, SVGElement canvas, int currY)
            throws SVGException {
        int lineHeight;
        for (Mounted bay : weapons) {
            Map<WeaponType, Integer> weapCount = new HashMap<>();
            for (Integer wId : bay.getBayWeapons()) {
                Mounted weap = warship.getEquipment(wId);
                WeaponType wtype = (WeaponType) weap.getType();
                if (weapCount.containsKey(wtype)) {
                    weapCount.put(wtype, weapCount.get(wtype) + 1);
                } else {
                    weapCount.put(wtype, 1);
                }
            }
            boolean first = true;
            for (WeaponType wtype : weapCount.keySet()) {
                lineHeight = addWeaponText(canvas, first, currY, weapCount.get(wtype), wtype.getName(), isCapital,
                        warship.getLocationAbbr(bay.getLocation()), wtype.getHeat(), wtype.getShortAV(),
                        wtype.getMedAV(), wtype.getLongAV(), wtype.getExtAV());
                currY += lineHeight;
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
            String loc, int heat, double srv, double mrv, double lrv, double erv) throws SVGException {

        String srvTxt, mrvTxt, lrvTxt, ervTxt;
        if (isCapital) { // Print out capital damage for weapon total
            srvTxt = srv == 0 ? "-" : (int)srv * num + "";
            mrvTxt = mrv == 0 ? "-" : (int)mrv * num + "";
            lrvTxt = lrv == 0 ? "-" : (int)lrv * num + "";
            ervTxt = erv == 0 ? "-" : (int)erv * num + "";
        } else { // Print out capital and standard damages
            srvTxt = srv == 0 ? "-" : (int)Math.round(srv * num / 10) + " (" + srv * num + ")";
            mrvTxt = mrv == 0 ? "-" : (int)Math.round(mrv * num / 10) + " (" + mrv * num + ")";
            lrvTxt = lrv == 0 ? "-" : (int)Math.round(lrv * num / 10) + " (" + lrv * num + ")";
            ervTxt = erv == 0 ? "-" : (int)Math.round(erv * num / 10) + " (" + erv * num + ")";
        }

        String nameString = num + "  " + name;
        int localNameX = nameX;
        if (!first) {
            localNameX += 5;
        }
        int lineHeight = addTextElement(canvas, localNameX, currY, nameString, eqNormalSize, "start");
        addTextElement(canvas, locX, currY, loc, eqNormalSize, "middle");
        addTextElement(canvas, htX,  currY, heat + "", eqNormalSize, "middle");
        addTextElement(canvas, srvX, currY, srvTxt, eqNormalSize, "middle");
        addTextElement(canvas, mrvX, currY, mrvTxt, eqNormalSize, "middle");
        addTextElement(canvas, lrvX, currY, lrvTxt, eqNormalSize, "middle");
        addTextElement(canvas, ervX, currY, ervTxt, eqNormalSize, "middle");

        return lineHeight;
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
    @SuppressWarnings("unchecked")
    private int addTextElement(SVGElement parent, int x, int y, String text, int fontSize, String anchor, String weight)
            throws SVGException {
        Text newText = new Text();
        newText.getContent().add(text);
        
        newText.addAttribute("x", AnimationElement.AT_XML, x + "");
        newText.addAttribute("y", AnimationElement.AT_XML, y + "");
        newText.addAttribute("font-family", AnimationElement.AT_XML, "EurostileLTStd");
        newText.addAttribute("font-size", AnimationElement.AT_XML, fontSize + "");
        newText.addAttribute("font-weight", AnimationElement.AT_XML, weight);
        newText.addAttribute("text-anchor", AnimationElement.AT_CSS, anchor);
        parent.loaderAddChild(null, newText);
        newText.rebuild();
        return newText.getShape().getBounds().height;
    }

}