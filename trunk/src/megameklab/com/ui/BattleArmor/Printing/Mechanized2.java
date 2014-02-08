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
public class Mechanized2 {
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
((GeneralPath)shape).moveTo(91.491, 329.084);
((GeneralPath)shape).curveTo(91.491, 329.54602, 91.282, 329.996, 90.863, 330.43503);
((GeneralPath)shape).lineTo(90.798, 330.50003);
((GeneralPath)shape).lineTo(87.135994, 334.35904);
((GeneralPath)shape).curveTo(86.35899, 335.17603, 85.69599, 335.80905, 85.148994, 336.25504);
((GeneralPath)shape).curveTo(84.602, 336.70203, 84.215996, 336.92505, 83.99, 336.92505);
((GeneralPath)shape).curveTo(83.753, 336.92505, 83.459, 336.81506, 83.106995, 336.59006);
((GeneralPath)shape).curveTo(82.755, 336.36707, 82.52899, 336.13806, 82.424995, 335.90005);
((GeneralPath)shape).curveTo(82.338, 335.70206, 82.256996, 335.28305, 82.17899, 334.64404);
((GeneralPath)shape).curveTo(82.10199, 334.00305, 82.063995, 333.31104, 82.063995, 332.56305);
((GeneralPath)shape).curveTo(82.063995, 332.15604, 82.23399, 331.77905, 82.574, 331.43405);
((GeneralPath)shape).curveTo(82.914, 331.08905, 83.288994, 330.91705, 83.699, 330.91705);
((GeneralPath)shape).curveTo(84.090996, 330.91705, 84.323, 331.27304, 84.397995, 331.98807);
((GeneralPath)shape).lineTo(84.41699, 332.17307);
((GeneralPath)shape).curveTo(84.479996, 332.72107, 84.55299, 333.10507, 84.635994, 333.32108);
((GeneralPath)shape).curveTo(84.718994, 333.5381, 84.83099, 333.64706, 84.97399, 333.64706);
((GeneralPath)shape).curveTo(85.03299, 333.64706, 85.14299, 333.58206, 85.30599, 333.45407);
((GeneralPath)shape).curveTo(85.468994, 333.32608, 85.660995, 333.15106, 85.88699, 332.93106);
((GeneralPath)shape).lineTo(89.649994, 329.20206);
((GeneralPath)shape).curveTo(89.965, 328.88705, 90.24899, 328.64606, 90.49799, 328.48407);
((GeneralPath)shape).curveTo(90.746994, 328.32208, 90.95799, 328.24207, 91.131996, 328.24207);
((GeneralPath)shape).curveTo(91.260994, 328.24207, 91.354, 328.29108, 91.409996, 328.38907);
((GeneralPath)shape).curveTo(91.465996, 328.48807, 91.493, 328.65506, 91.493, 328.88705);
((GeneralPath)shape).lineTo(91.493, 329.084);
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
        return 83;
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

