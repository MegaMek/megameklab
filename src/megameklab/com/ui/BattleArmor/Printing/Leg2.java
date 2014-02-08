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
public class Leg2 {
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
((GeneralPath)shape).moveTo(180.362, 329.084);
((GeneralPath)shape).curveTo(180.362, 329.54602, 180.152, 329.996, 179.733, 330.43503);
((GeneralPath)shape).lineTo(179.669, 330.50003);
((GeneralPath)shape).lineTo(176.00601, 334.35904);
((GeneralPath)shape).curveTo(175.22902, 335.17603, 174.56601, 335.80905, 174.01901, 336.25504);
((GeneralPath)shape).curveTo(173.47202, 336.70203, 173.085, 336.92505, 172.86002, 336.92505);
((GeneralPath)shape).curveTo(172.62402, 336.92505, 172.32901, 336.81506, 171.97702, 336.59006);
((GeneralPath)shape).curveTo(171.62502, 336.36707, 171.39902, 336.13806, 171.29602, 335.90005);
((GeneralPath)shape).curveTo(171.21002, 335.70206, 171.12701, 335.28305, 171.05002, 334.64404);
((GeneralPath)shape).curveTo(170.97302, 334.00305, 170.93501, 333.31104, 170.93501, 332.56305);
((GeneralPath)shape).curveTo(170.93501, 332.15604, 171.10501, 331.77905, 171.445, 331.43405);
((GeneralPath)shape).curveTo(171.785, 331.08804, 172.16, 330.91705, 172.57101, 330.91705);
((GeneralPath)shape).curveTo(172.96202, 330.91705, 173.195, 331.27304, 173.26901, 331.98807);
((GeneralPath)shape).lineTo(173.28801, 332.17307);
((GeneralPath)shape).curveTo(173.35101, 332.72107, 173.42401, 333.10507, 173.50601, 333.32108);
((GeneralPath)shape).curveTo(173.59001, 333.5381, 173.70201, 333.64706, 173.84401, 333.64706);
((GeneralPath)shape).curveTo(173.90302, 333.64706, 174.01302, 333.58206, 174.17601, 333.45407);
((GeneralPath)shape).curveTo(174.339, 333.32608, 174.531, 333.15106, 174.757, 332.93106);
((GeneralPath)shape).lineTo(178.52, 329.20206);
((GeneralPath)shape).curveTo(178.836, 328.88705, 179.119, 328.64606, 179.367, 328.48407);
((GeneralPath)shape).curveTo(179.617, 328.32208, 179.828, 328.24207, 180.002, 328.24207);
((GeneralPath)shape).curveTo(180.132, 328.24207, 180.224, 328.29108, 180.281, 328.38907);
((GeneralPath)shape).curveTo(180.336, 328.48807, 180.364, 328.65506, 180.364, 328.88705);
((GeneralPath)shape).lineTo(180.364, 329.084);
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
        return 171;
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

