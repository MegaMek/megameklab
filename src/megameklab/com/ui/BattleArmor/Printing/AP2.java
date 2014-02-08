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

import java.awt.*;
import java.awt.geom.*;





/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class AP2 {
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
        Stroke stroke = null;
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
((GeneralPath)shape).moveTo(217.204, 329.084);
((GeneralPath)shape).curveTo(217.204, 329.54602, 216.995, 329.996, 216.577, 330.43503);
((GeneralPath)shape).lineTo(216.511, 330.50003);
((GeneralPath)shape).lineTo(212.849, 334.35904);
((GeneralPath)shape).curveTo(212.071, 335.17603, 211.409, 335.80905, 210.862, 336.25504);
((GeneralPath)shape).curveTo(210.315, 336.70203, 209.928, 336.92505, 209.703, 336.92505);
((GeneralPath)shape).curveTo(209.46701, 336.92505, 209.173, 336.81506, 208.821, 336.59006);
((GeneralPath)shape).curveTo(208.468, 336.36707, 208.242, 336.13806, 208.13899, 335.90005);
((GeneralPath)shape).curveTo(208.051, 335.70206, 207.96999, 335.28305, 207.89299, 334.64404);
((GeneralPath)shape).curveTo(207.816, 334.00305, 207.77798, 333.31104, 207.77798, 332.56305);
((GeneralPath)shape).curveTo(207.77798, 332.15604, 207.94699, 331.77905, 208.28798, 331.43405);
((GeneralPath)shape).curveTo(208.62698, 331.08804, 209.00198, 330.91705, 209.41298, 330.91705);
((GeneralPath)shape).curveTo(209.80399, 330.91705, 210.03697, 331.27304, 210.11098, 331.98807);
((GeneralPath)shape).lineTo(210.12898, 332.17307);
((GeneralPath)shape).curveTo(210.19199, 332.72107, 210.26598, 333.10507, 210.34898, 333.32108);
((GeneralPath)shape).curveTo(210.43198, 333.5381, 210.54399, 333.64706, 210.68698, 333.64706);
((GeneralPath)shape).curveTo(210.74498, 333.64706, 210.85599, 333.58206, 211.01797, 333.45407);
((GeneralPath)shape).curveTo(211.17996, 333.32608, 211.37297, 333.15106, 211.59897, 332.93106);
((GeneralPath)shape).lineTo(215.36197, 329.20206);
((GeneralPath)shape).curveTo(215.67697, 328.88705, 215.96097, 328.64606, 216.20897, 328.48407);
((GeneralPath)shape).curveTo(216.45697, 328.32208, 216.66797, 328.24207, 216.84196, 328.24207);
((GeneralPath)shape).curveTo(216.97197, 328.24207, 217.06497, 328.29108, 217.11996, 328.38907);
((GeneralPath)shape).curveTo(217.17697, 328.48807, 217.20296, 328.65506, 217.20296, 328.88705);
((GeneralPath)shape).lineTo(217.20296, 329.084);
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
        return 329;
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

