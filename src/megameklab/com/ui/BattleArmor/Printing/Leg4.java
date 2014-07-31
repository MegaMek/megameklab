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
public class Leg4 {
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
((GeneralPath)shape).moveTo(180.362, 599.154);
((GeneralPath)shape).curveTo(180.362, 599.61597, 180.152, 600.066, 179.733, 600.505);
((GeneralPath)shape).lineTo(179.669, 600.57);
((GeneralPath)shape).lineTo(176.00601, 604.427);
((GeneralPath)shape).curveTo(175.22902, 605.244, 174.56601, 605.877, 174.01901, 606.324);
((GeneralPath)shape).curveTo(173.47202, 606.769, 173.085, 606.99396, 172.86002, 606.99396);
((GeneralPath)shape).curveTo(172.62402, 606.99396, 172.32901, 606.88196, 171.97702, 606.65894);
((GeneralPath)shape).curveTo(171.62502, 606.43396, 171.39902, 606.20496, 171.29602, 605.96796);
((GeneralPath)shape).curveTo(171.21002, 605.772, 171.12701, 605.35297, 171.05002, 604.712);
((GeneralPath)shape).curveTo(170.97302, 604.07196, 170.93501, 603.378, 170.93501, 602.631);
((GeneralPath)shape).curveTo(170.93501, 602.224, 171.10501, 601.849, 171.445, 601.503);
((GeneralPath)shape).curveTo(171.785, 601.156, 172.16, 600.984, 172.57101, 600.984);
((GeneralPath)shape).curveTo(172.96202, 600.984, 173.195, 601.341, 173.26901, 602.056);
((GeneralPath)shape).lineTo(173.28801, 602.24005);
((GeneralPath)shape).curveTo(173.35101, 602.79004, 173.42401, 603.17303, 173.50601, 603.3901);
((GeneralPath)shape).curveTo(173.59001, 603.60706, 173.70201, 603.71606, 173.84401, 603.71606);
((GeneralPath)shape).curveTo(173.90302, 603.71606, 174.01302, 603.65106, 174.17601, 603.5231);
((GeneralPath)shape).curveTo(174.339, 603.3951, 174.531, 603.21906, 174.757, 602.9991);
((GeneralPath)shape).lineTo(178.52, 599.2701);
((GeneralPath)shape).curveTo(178.836, 598.9551, 179.119, 598.71405, 179.367, 598.5541);
((GeneralPath)shape).curveTo(179.617, 598.3921, 179.828, 598.31006, 180.002, 598.31006);
((GeneralPath)shape).curveTo(180.132, 598.31006, 180.224, 598.3611, 180.281, 598.45703);
((GeneralPath)shape).curveTo(180.336, 598.557, 180.364, 598.723, 180.364, 598.95605);
((GeneralPath)shape).lineTo(180.364, 599.154);
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
        return 599;
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

