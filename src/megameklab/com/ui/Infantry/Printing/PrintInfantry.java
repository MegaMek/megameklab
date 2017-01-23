/*
 * MegaMekLab - Copyright (C) 2017 The MegaMek Team
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
package megameklab.com.ui.Infantry.Printing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.Compute;
import megamek.common.Infantry;
import megamek.common.TargetRollModifier;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.util.ImageHelper;

/**
 * @author Neoancient
 *
 */
public class PrintInfantry implements Printable {
	
	/* Id tags of elements in the SVG file */
	private final static String ID_FLUFF_IMAGE = "imageFluff";
	private final static String ID_PLATOON = "platoon";
	private final static String ID_PLATOON_NAME = "platoon_name";
	private final static String ID_SOLDIER = "soldier_";
	private final static String ID_NO_SOLDIER = "no_soldier_";
	private final static String ID_DAMAGE = "damage_";
	private final static String ID_RANGE_MOD = "range_mod_";

    private Infantry infantry = null;
    private ArrayList<Infantry> infantryList;
    PrinterJob masterPrintJob;
    private int currentPosition;
    
    public PrintInfantry(ArrayList<Infantry> list, PrinterJob masterPrintJob) {
    	infantryList = list;
        this.masterPrintJob = masterPrintJob;
    }

    
	/* (non-Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex != 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
	}

	public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }
        
        SVGDiagram diagram = ImageHelper.loadSVGImage(new File("data/images/recordsheets/Conventional_Infantry_template.svg"));
        
        
        try {
        	Tspan tspan = (Tspan)diagram.getElement("tspan_year");
        	tspan.setText(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        	((Text)tspan.getParent()).rebuild();

            int stop = Math.min(4, infantryList.size() - currentPosition);
            for (int pos = 0; pos < 4; pos++) {
            	String infId = "_" + (pos + 1);
            	if (pos >= infantryList.size() - currentPosition) {
            		diagram.getElement(ID_PLATOON + infId).addAttribute("display",
            				AnimationElement.AT_XML, "none");
            		continue;
            	}
        		infantry = infantryList.get(pos + currentPosition);
        		Text txtElem = (Text)diagram.getElement(ID_PLATOON_NAME + infId);
        		tspan = new Tspan();
        		tspan.setText(infantry.getShortName() + " " + (pos + 1));
        		txtElem.loaderAddChild(null, tspan);
        		txtElem.rebuild();

        		for (int j = 1; j <= 30; j++) {
    				txtElem = (Text)diagram.getElement(ID_DAMAGE + j + infId);
    				tspan = new Tspan();
        			if (j > infantry.getShootingStrength()) {
//        				diagram.getElement(ID_SOLDIER + j + infId)
//        					.addAttribute("display", AnimationElement.AT_XML, "none");
//        				diagram.getElement(ID_NO_SOLDIER + j + infId)
//    						.removeAttribute("display", AnimationElement.AT_XML);
        				tspan.setText("—");
        			} else {
        				tspan.setText(String.valueOf((int)Math.round(infantry.getDamagePerTrooper()
                				* j)));
        			}
    				txtElem.loaderAddChild(null, tspan);
            		txtElem.rebuild();
        		}
        		InfantryWeapon rangeWeapon = infantry.getPrimaryWeapon();
        		if (infantry.getSecondaryWeapon() != null && infantry.getSecondaryN() > 1) {
        			rangeWeapon = infantry.getSecondaryWeapon();
        		}
        		for (int j = 0; j <= 21; j++) {
    				txtElem = (Text)diagram.getElement(ID_RANGE_MOD + j + infId);
    				tspan = new Tspan();
        			if (rangeWeapon.getInfantryRange() * 3 < j) {
        				tspan.setText("—");
        			} else {
	        			int mod = Compute.getInfantryRangeMods(j, rangeWeapon)
	        					.getModifiers().stream().mapToInt(TargetRollModifier::getValue).sum();
        				tspan.setText(String.format("%+d", mod));
        			}
        			txtElem.loaderAddChild(null, tspan);
            		txtElem.rebuild();
        		}
        	}
            diagram.render(g2d);
        } catch (SVGException ex) {
        	ex.printStackTrace();
        }

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }
    
    public void print(HashPrintRequestAttributeSet aset) {

        try {
            for (; currentPosition < infantryList.size(); currentPosition += 4) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                infantry = infantryList.get(currentPosition);
                pj.setJobName(infantry.getChassis() + " " + infantry.getModel());

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
}
