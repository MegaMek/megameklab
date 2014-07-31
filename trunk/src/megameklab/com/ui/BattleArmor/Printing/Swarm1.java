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
public class Swarm1 {
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
((GeneralPath)shape).moveTo(140.227, 194.05);
((GeneralPath)shape).curveTo(140.227, 194.51201, 140.017, 194.963, 139.599, 195.401);
((GeneralPath)shape).lineTo(139.534, 195.466);
((GeneralPath)shape).lineTo(135.871, 199.325);
((GeneralPath)shape).curveTo(135.09401, 200.142, 134.431, 200.775, 133.884, 201.221);
((GeneralPath)shape).curveTo(133.337, 201.66699, 132.95, 201.89099, 132.725, 201.89099);
((GeneralPath)shape).curveTo(132.48901, 201.89099, 132.194, 201.78099, 131.84201, 201.55598);
((GeneralPath)shape).curveTo(131.49, 201.33298, 131.26302, 201.10298, 131.16, 200.86598);
((GeneralPath)shape).curveTo(131.074, 200.66798, 130.991, 200.24898, 130.914, 199.60999);
((GeneralPath)shape).curveTo(130.837, 198.96898, 130.799, 198.277, 130.799, 197.52899);
((GeneralPath)shape).curveTo(130.799, 197.122, 130.969, 196.747, 131.30899, 196.4);
((GeneralPath)shape).curveTo(131.64899, 196.054, 132.02399, 195.88199, 132.435, 195.88199);
((GeneralPath)shape).curveTo(132.826, 195.88199, 133.05899, 196.23799, 133.133, 196.954);
((GeneralPath)shape).lineTo(133.152, 197.13899);
((GeneralPath)shape).curveTo(133.215, 197.68799, 133.288, 198.07199, 133.37099, 198.28699);
((GeneralPath)shape).curveTo(133.45499, 198.50398, 133.566, 198.61398, 133.70898, 198.61398);
((GeneralPath)shape).curveTo(133.76799, 198.61398, 133.87898, 198.55098, 134.03998, 198.42099);
((GeneralPath)shape).curveTo(134.20198, 198.29298, 134.39497, 198.11899, 134.62097, 197.898);
((GeneralPath)shape).lineTo(138.38397, 194.16899);
((GeneralPath)shape).curveTo(138.69997, 193.853, 138.98297, 193.61299, 139.23198, 193.45099);
((GeneralPath)shape).curveTo(139.48198, 193.28899, 139.69199, 193.20898, 139.86697, 193.20898);
((GeneralPath)shape).curveTo(139.99597, 193.20898, 140.08897, 193.25798, 140.14497, 193.35599);
((GeneralPath)shape).curveTo(140.19997, 193.456, 140.22797, 193.62099, 140.22797, 193.85599);
((GeneralPath)shape).lineTo(140.22797, 194.05);
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

