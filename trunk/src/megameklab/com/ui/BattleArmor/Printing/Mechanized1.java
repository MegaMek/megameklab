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
public class Mechanized1 {
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
((GeneralPath)shape).moveTo(91.491, 194.05);
((GeneralPath)shape).curveTo(91.491, 194.51201, 91.282, 194.963, 90.863, 195.401);
((GeneralPath)shape).lineTo(90.798, 195.466);
((GeneralPath)shape).lineTo(87.135994, 199.325);
((GeneralPath)shape).curveTo(86.35899, 200.142, 85.69599, 200.775, 85.148994, 201.221);
((GeneralPath)shape).curveTo(84.602, 201.66699, 84.215996, 201.89099, 83.99, 201.89099);
((GeneralPath)shape).curveTo(83.753, 201.89099, 83.459, 201.78099, 83.106995, 201.55598);
((GeneralPath)shape).curveTo(82.755, 201.33298, 82.52899, 201.10298, 82.424995, 200.86598);
((GeneralPath)shape).curveTo(82.338, 200.66798, 82.256996, 200.24898, 82.17899, 199.60999);
((GeneralPath)shape).curveTo(82.10199, 198.96898, 82.063995, 198.277, 82.063995, 197.52899);
((GeneralPath)shape).curveTo(82.063995, 197.122, 82.23399, 196.747, 82.574, 196.4);
((GeneralPath)shape).curveTo(82.913994, 196.054, 83.288994, 195.88199, 83.699, 195.88199);
((GeneralPath)shape).curveTo(84.090996, 195.88199, 84.323, 196.23799, 84.397995, 196.954);
((GeneralPath)shape).lineTo(84.41699, 197.13899);
((GeneralPath)shape).curveTo(84.479996, 197.68799, 84.55299, 198.07199, 84.635994, 198.28699);
((GeneralPath)shape).curveTo(84.718994, 198.50398, 84.83099, 198.61398, 84.97399, 198.61398);
((GeneralPath)shape).curveTo(85.03299, 198.61398, 85.14299, 198.55098, 85.30599, 198.42099);
((GeneralPath)shape).curveTo(85.467995, 198.29298, 85.660995, 198.11899, 85.88699, 197.898);
((GeneralPath)shape).lineTo(89.649994, 194.16899);
((GeneralPath)shape).curveTo(89.965, 193.853, 90.24899, 193.61299, 90.49799, 193.45099);
((GeneralPath)shape).curveTo(90.746994, 193.28899, 90.95799, 193.20898, 91.131996, 193.20898);
((GeneralPath)shape).curveTo(91.260994, 193.20898, 91.354, 193.25798, 91.409996, 193.35599);
((GeneralPath)shape).curveTo(91.465996, 193.456, 91.493, 193.62099, 91.493, 193.85599);
((GeneralPath)shape).lineTo(91.493, 194.05);
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
        return 194;
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

