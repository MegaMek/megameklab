/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;

import megamek.common.Mech;

public class PrintMech implements Printable {

    protected Image awtImage = null;
    private Mech mech = null;
    
    public PrintMech (Image image, Mech unit) {
        awtImage = image;
        mech = unit;
        
        System.out.println("Width: " + awtImage.getWidth(null));
        System.out.println("Height: " + awtImage.getHeight(null));
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        if (awtImage != null) {
            printImage(g2d, awtImage);
            return Printable.PAGE_EXISTS;
        } else
            return Printable.NO_SUCH_PAGE;
    }

    public void printImage(Graphics2D g2d, Image image) {
        System.out.println("printImage(Graphics2D g2d, Image image)");
        if ((image == null) || (g2d == null))
            return;
        int x = 18;
        int y = 18;
        g2d.drawImage(image, x, y, 558, 738, null);
        Font font = new Font("Serif", Font.PLAIN, 12);
        g2d.setFont(font);
        //x = x
        //y - 41
        g2d.drawString(mech.getChassis()+" "+mech.getModel(), 51, 118);
        g2d.drawString(Integer.toString(mech.getWalkMP()), 81, 143);
        g2d.drawString(Integer.toString(mech.getRunMP()), 81, 154);
        g2d.drawString(Integer.toString(mech.getJumpMP()), 81, 165);
        g2d.drawString(Float.toString(mech.getWeight()), 175, 133);

        if ( mech.isClan() ) {
            g2d.drawString("X", 208, 155);
        } else {
            g2d.drawString("X", 208, 165);
        }
        
        //Cost/BV
        g2d.drawString(Integer.toString(mech.calculateBattleValue(true)), 161, 349);

        DecimalFormat myFormatter = new DecimalFormat("#,###.##");
        g2d.drawString(myFormatter.format(mech.getCost())+" C", 56, 349);
        
        //Armor
        font = new Font("Serif", Font.BOLD, 11);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_HEAD)), 490, 45);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT)), 438, 59);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT)), 514, 59);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT)), 478, 220);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LARM)), 402, 215);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RARM)), 550, 215);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LLEG)), 393, 271);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RLEG)), 558, 271);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_LT,true)), 406, 361);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_CT,true)), 484, 277);
        g2d.drawString(Integer.toString(mech.getArmor(Mech.LOC_RT,true)), 549, 361);
        //Internal
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LT)), 436, 403);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RT)), 529, 403);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LARM)), 395, 478);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RARM)), 535, 480);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_CT)), 464, 510);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_LLEG)), 407, 540);
        g2d.drawString(Integer.toString(mech.getInternal(Mech.LOC_RLEG)), 523, 540);
        
        //Heat Sinks
        g2d.drawString(Integer.toString(mech.heatSinks()), 503, 598);
        if ( mech.hasDoubleHeatSinks() ) {
            g2d.drawString(Integer.toString(mech.heatSinks()*2), 524, 598);
            g2d.drawString("X", 530, 723);
        }else {
            g2d.drawString(Integer.toString(mech.heatSinks()), 524, 598);
            g2d.drawString("X", 530, 708);
        }
    }

    public void print() {

        try {
            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.defaultPage();
                paper.setImageableArea(0, 0, 612, 792);
                paper.setSize(612, 792);
                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                pj.setPrintable(this, pageFormat);
                pj.setJobName(mech.getChassis()+" "+mech.getModel());
                
                pj.print();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}