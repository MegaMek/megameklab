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
public class Swarm2 {
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
((GeneralPath)shape).moveTo(140.227, 329.084);
((GeneralPath)shape).curveTo(140.227, 329.54602, 140.017, 329.996, 139.599, 330.43503);
((GeneralPath)shape).lineTo(139.534, 330.50003);
((GeneralPath)shape).lineTo(135.871, 334.35904);
((GeneralPath)shape).curveTo(135.09401, 335.17603, 134.431, 335.80905, 133.884, 336.25504);
((GeneralPath)shape).curveTo(133.337, 336.70203, 132.95, 336.92505, 132.725, 336.92505);
((GeneralPath)shape).curveTo(132.48901, 336.92505, 132.194, 336.81506, 131.84201, 336.59006);
((GeneralPath)shape).curveTo(131.49, 336.36707, 131.26302, 336.13806, 131.16, 335.90005);
((GeneralPath)shape).curveTo(131.074, 335.70206, 130.991, 335.28305, 130.914, 334.64404);
((GeneralPath)shape).curveTo(130.837, 334.00305, 130.799, 333.31104, 130.799, 332.56305);
((GeneralPath)shape).curveTo(130.799, 332.15604, 130.969, 331.77905, 131.30899, 331.43405);
((GeneralPath)shape).curveTo(131.64899, 331.08804, 132.02399, 330.91705, 132.435, 330.91705);
((GeneralPath)shape).curveTo(132.826, 330.91705, 133.05899, 331.27304, 133.133, 331.98807);
((GeneralPath)shape).lineTo(133.152, 332.17307);
((GeneralPath)shape).curveTo(133.215, 332.72107, 133.288, 333.10507, 133.37099, 333.32108);
((GeneralPath)shape).curveTo(133.45499, 333.5381, 133.566, 333.64706, 133.70898, 333.64706);
((GeneralPath)shape).curveTo(133.76799, 333.64706, 133.87898, 333.58206, 134.03998, 333.45407);
((GeneralPath)shape).curveTo(134.20198, 333.32608, 134.39497, 333.15106, 134.62097, 332.93106);
((GeneralPath)shape).lineTo(138.38397, 329.20206);
((GeneralPath)shape).curveTo(138.69997, 328.88705, 138.98297, 328.64606, 139.23198, 328.48407);
((GeneralPath)shape).curveTo(139.48198, 328.32208, 139.69199, 328.24207, 139.86697, 328.24207);
((GeneralPath)shape).curveTo(139.99597, 328.24207, 140.08897, 328.29108, 140.14497, 328.38907);
((GeneralPath)shape).curveTo(140.19997, 328.48807, 140.22797, 328.65506, 140.22797, 328.88705);
((GeneralPath)shape).lineTo(140.22797, 329.084);
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
        return 131;
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

