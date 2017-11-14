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
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.List;
import java.util.function.Function;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.Mech;
import megamek.common.logging.LogLevel;
import megameklab.com.MegaMekLab;
import megameklab.com.util.ImageHelper;

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
        ERA("era", m -> m.getSource())
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

    /**
     * A list of all warships to print.
     */
    private List<Mech> mechList;

    PrinterJob masterPrintJob;
    
    public PrintMechCommon(List<Mech> list, PrinterJob masterPrintJob) {
        mechList = list;
        this.masterPrintJob = masterPrintJob;
    }
    
    public void print(HashPrintRequestAttributeSet aset) {

        try {
            for (int pos = 0; pos < mechList.size(); pos++) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);
                mech = mechList.get(pos);
                pj.setJobName(mech.getChassis() + " " + mech.getModel());

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
            diagram.render(g2d);
        } catch (SVGException e) {
            e.printStackTrace();
        }
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
