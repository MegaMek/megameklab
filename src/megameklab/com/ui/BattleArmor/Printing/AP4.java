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
public class AP4 {
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
((GeneralPath)shape).moveTo(217.204, 599.154);
((GeneralPath)shape).curveTo(217.204, 599.61597, 216.995, 600.066, 216.577, 600.505);
((GeneralPath)shape).lineTo(216.511, 600.57);
((GeneralPath)shape).lineTo(212.849, 604.427);
((GeneralPath)shape).curveTo(212.071, 605.244, 211.409, 605.877, 210.862, 606.324);
((GeneralPath)shape).curveTo(210.315, 606.769, 209.928, 606.99396, 209.703, 606.99396);
((GeneralPath)shape).curveTo(209.46701, 606.99396, 209.173, 606.88196, 208.821, 606.65894);
((GeneralPath)shape).curveTo(208.468, 606.43396, 208.242, 606.20496, 208.13899, 605.96796);
((GeneralPath)shape).curveTo(208.051, 605.772, 207.96999, 605.35297, 207.89299, 604.712);
((GeneralPath)shape).curveTo(207.816, 604.07196, 207.77798, 603.378, 207.77798, 602.631);
((GeneralPath)shape).curveTo(207.77798, 602.224, 207.94699, 601.849, 208.28798, 601.503);
((GeneralPath)shape).curveTo(208.62698, 601.156, 209.00198, 600.984, 209.41298, 600.984);
((GeneralPath)shape).curveTo(209.80399, 600.984, 210.03697, 601.341, 210.11098, 602.056);
((GeneralPath)shape).lineTo(210.12898, 602.24005);
((GeneralPath)shape).curveTo(210.19199, 602.79004, 210.26598, 603.17303, 210.34898, 603.3901);
((GeneralPath)shape).curveTo(210.43198, 603.60706, 210.54399, 603.71606, 210.68698, 603.71606);
((GeneralPath)shape).curveTo(210.74498, 603.71606, 210.85599, 603.65106, 211.01797, 603.5231);
((GeneralPath)shape).curveTo(211.17996, 603.3951, 211.37297, 603.21906, 211.59897, 602.9991);
((GeneralPath)shape).lineTo(215.36197, 599.2701);
((GeneralPath)shape).curveTo(215.67697, 598.9551, 215.96097, 598.71405, 216.20897, 598.5541);
((GeneralPath)shape).curveTo(216.45697, 598.3921, 216.66797, 598.31006, 216.84196, 598.31006);
((GeneralPath)shape).curveTo(216.97197, 598.31006, 217.06497, 598.3611, 217.11996, 598.45703);
((GeneralPath)shape).curveTo(217.17697, 598.557, 217.20296, 598.723, 217.20296, 598.95605);
((GeneralPath)shape).lineTo(217.20296, 599.154);
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

