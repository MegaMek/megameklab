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
public class Leg1 {
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
((GeneralPath)shape).moveTo(180.362, 194.05);
((GeneralPath)shape).curveTo(180.362, 194.51201, 180.152, 194.963, 179.733, 195.401);
((GeneralPath)shape).lineTo(179.669, 195.466);
((GeneralPath)shape).lineTo(176.00601, 199.325);
((GeneralPath)shape).curveTo(175.22902, 200.142, 174.56601, 200.775, 174.01901, 201.221);
((GeneralPath)shape).curveTo(173.47202, 201.66699, 173.085, 201.89099, 172.86002, 201.89099);
((GeneralPath)shape).curveTo(172.62402, 201.89099, 172.32901, 201.78099, 171.97702, 201.55598);
((GeneralPath)shape).curveTo(171.62502, 201.33298, 171.39902, 201.10298, 171.29602, 200.86598);
((GeneralPath)shape).curveTo(171.21002, 200.66798, 171.12701, 200.24898, 171.05002, 199.60999);
((GeneralPath)shape).curveTo(170.97302, 198.96898, 170.93501, 198.277, 170.93501, 197.52899);
((GeneralPath)shape).curveTo(170.93501, 197.122, 171.10501, 196.747, 171.445, 196.4);
((GeneralPath)shape).curveTo(171.785, 196.054, 172.16, 195.88199, 172.57101, 195.88199);
((GeneralPath)shape).curveTo(172.96202, 195.88199, 173.195, 196.23799, 173.26901, 196.954);
((GeneralPath)shape).lineTo(173.28801, 197.13899);
((GeneralPath)shape).curveTo(173.35101, 197.68799, 173.42401, 198.07199, 173.50601, 198.28699);
((GeneralPath)shape).curveTo(173.59001, 198.50398, 173.70201, 198.61398, 173.84401, 198.61398);
((GeneralPath)shape).curveTo(173.90302, 198.61398, 174.01302, 198.55098, 174.17601, 198.42099);
((GeneralPath)shape).curveTo(174.33801, 198.29298, 174.531, 198.11899, 174.757, 197.898);
((GeneralPath)shape).lineTo(178.52, 194.16899);
((GeneralPath)shape).curveTo(178.836, 193.853, 179.119, 193.61299, 179.367, 193.45099);
((GeneralPath)shape).curveTo(179.617, 193.28899, 179.828, 193.20898, 180.002, 193.20898);
((GeneralPath)shape).curveTo(180.132, 193.20898, 180.224, 193.25798, 180.281, 193.35599);
((GeneralPath)shape).curveTo(180.336, 193.456, 180.364, 193.62099, 180.364, 193.85599);
((GeneralPath)shape).lineTo(180.364, 194.05);
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

