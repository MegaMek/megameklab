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
public class AP1 {
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
((GeneralPath)shape).moveTo(217.204, 194.05);
((GeneralPath)shape).curveTo(217.204, 194.51201, 216.995, 194.963, 216.577, 195.401);
((GeneralPath)shape).lineTo(216.511, 195.466);
((GeneralPath)shape).lineTo(212.849, 199.325);
((GeneralPath)shape).curveTo(212.071, 200.142, 211.409, 200.775, 210.862, 201.221);
((GeneralPath)shape).curveTo(210.315, 201.66699, 209.928, 201.89099, 209.703, 201.89099);
((GeneralPath)shape).curveTo(209.46701, 201.89099, 209.173, 201.78099, 208.821, 201.55598);
((GeneralPath)shape).curveTo(208.468, 201.33298, 208.242, 201.10298, 208.13899, 200.86598);
((GeneralPath)shape).curveTo(208.051, 200.66798, 207.96999, 200.24898, 207.89299, 199.60999);
((GeneralPath)shape).curveTo(207.816, 198.96898, 207.77798, 198.277, 207.77798, 197.52899);
((GeneralPath)shape).curveTo(207.77798, 197.122, 207.94699, 196.747, 208.28798, 196.4);
((GeneralPath)shape).curveTo(208.62698, 196.054, 209.00198, 195.88199, 209.41298, 195.88199);
((GeneralPath)shape).curveTo(209.80399, 195.88199, 210.03697, 196.23799, 210.11098, 196.954);
((GeneralPath)shape).lineTo(210.12898, 197.13899);
((GeneralPath)shape).curveTo(210.19199, 197.68799, 210.26598, 198.07199, 210.34898, 198.28699);
((GeneralPath)shape).curveTo(210.43198, 198.50398, 210.54399, 198.61398, 210.68698, 198.61398);
((GeneralPath)shape).curveTo(210.74498, 198.61398, 210.85599, 198.55098, 211.01797, 198.42099);
((GeneralPath)shape).curveTo(211.17998, 198.29298, 211.37297, 198.11899, 211.59897, 197.898);
((GeneralPath)shape).lineTo(215.36197, 194.16899);
((GeneralPath)shape).curveTo(215.67697, 193.853, 215.96097, 193.61299, 216.20897, 193.45099);
((GeneralPath)shape).curveTo(216.45697, 193.28899, 216.66797, 193.20898, 216.84196, 193.20898);
((GeneralPath)shape).curveTo(216.97197, 193.20898, 217.06497, 193.25798, 217.11996, 193.35599);
((GeneralPath)shape).curveTo(217.17697, 193.456, 217.20296, 193.62099, 217.20296, 193.85599);
((GeneralPath)shape).lineTo(217.20296, 194.05);
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

