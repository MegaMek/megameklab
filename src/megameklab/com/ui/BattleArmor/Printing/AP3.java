/*
 * MegaMekLab - Copyright (C) 2012
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

package megameklab.com.ui.BattleArmor.Printing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;





/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class AP3 {
    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g 
     *            Graphics context.
     */
    public static void paint(Graphics2D g) {
        Shape shape = null;
        Paint paint = null;
        //Stroke stroke = null;
        Area clip = null;
        
        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
        Shape clip_ = g.getClip();
AffineTransform defaultTransform_ = g.getTransform();
//  is CompositeGraphicsNode
float alpha__0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0 = g.getClip();
AffineTransform defaultTransform__0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
clip = new Area(g.getClip());
clip.intersect(new Area(new Rectangle2D.Double(0.0,0.0,612.0,792.0)));
g.setClip(clip);
// _0 is CompositeGraphicsNode
float alpha__0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0 = g.getClip();
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(217.204, 464.119);
((GeneralPath)shape).curveTo(217.204, 464.581, 216.995, 465.03098, 216.577, 465.47098);
((GeneralPath)shape).lineTo(216.511, 465.53497);
((GeneralPath)shape).lineTo(212.849, 469.39398);
((GeneralPath)shape).curveTo(212.071, 470.21097, 211.409, 470.843, 210.862, 471.28998);
((GeneralPath)shape).curveTo(210.315, 471.73697, 209.928, 471.96, 209.703, 471.96);
((GeneralPath)shape).curveTo(209.46701, 471.96, 209.173, 471.849, 208.821, 471.625);
((GeneralPath)shape).curveTo(208.468, 471.402, 208.242, 471.172, 208.13899, 470.934);
((GeneralPath)shape).curveTo(208.051, 470.73798, 207.96999, 470.319, 207.89299, 469.67798);
((GeneralPath)shape).curveTo(207.816, 469.03796, 207.77798, 468.34598, 207.77798, 467.598);
((GeneralPath)shape).curveTo(207.77798, 467.192, 207.94699, 466.815, 208.28798, 466.469);
((GeneralPath)shape).curveTo(208.62698, 466.123, 209.00198, 465.94998, 209.41298, 465.94998);
((GeneralPath)shape).curveTo(209.80399, 465.94998, 210.03697, 466.30698, 210.11098, 467.02298);
((GeneralPath)shape).lineTo(210.12898, 467.20697);
((GeneralPath)shape).curveTo(210.19199, 467.75696, 210.26598, 468.13998, 210.34898, 468.35596);
((GeneralPath)shape).curveTo(210.43198, 468.57297, 210.54399, 468.68195, 210.68698, 468.68195);
((GeneralPath)shape).curveTo(210.74498, 468.68195, 210.85599, 468.61795, 211.01797, 468.48895);
((GeneralPath)shape).curveTo(211.17998, 468.36096, 211.37297, 468.18497, 211.59897, 467.96497);
((GeneralPath)shape).lineTo(215.36197, 464.23596);
((GeneralPath)shape).curveTo(215.67697, 463.92096, 215.96097, 463.68198, 216.20897, 463.51996);
((GeneralPath)shape).curveTo(216.45697, 463.35797, 216.66797, 463.27795, 216.84196, 463.27795);
((GeneralPath)shape).curveTo(216.97197, 463.27795, 217.06497, 463.32697, 217.11996, 463.42496);
((GeneralPath)shape).curveTo(217.17697, 463.52396, 217.20296, 463.68896, 217.20296, 463.92294);
((GeneralPath)shape).lineTo(217.20296, 464.119);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_0;
g.setTransform(defaultTransform__0_0);
g.setClip(clip__0_0);
origAlpha = alpha__0;
g.setTransform(defaultTransform__0);
g.setClip(clip__0);
g.setTransform(defaultTransform_);
g.setClip(clip_);

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 208;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 464;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 612;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 792;
    }
}

