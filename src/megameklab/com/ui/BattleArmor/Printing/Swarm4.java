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
public class Swarm4 {
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
((GeneralPath)shape).moveTo(140.227, 599.154);
((GeneralPath)shape).curveTo(140.227, 599.61597, 140.017, 600.066, 139.599, 600.505);
((GeneralPath)shape).lineTo(139.534, 600.57);
((GeneralPath)shape).lineTo(135.871, 604.427);
((GeneralPath)shape).curveTo(135.09401, 605.244, 134.431, 605.877, 133.884, 606.324);
((GeneralPath)shape).curveTo(133.337, 606.769, 132.95, 606.99396, 132.725, 606.99396);
((GeneralPath)shape).curveTo(132.48901, 606.99396, 132.194, 606.88196, 131.84201, 606.65894);
((GeneralPath)shape).curveTo(131.49, 606.43396, 131.26302, 606.20496, 131.16, 605.96796);
((GeneralPath)shape).curveTo(131.074, 605.772, 130.991, 605.35297, 130.914, 604.712);
((GeneralPath)shape).curveTo(130.837, 604.07196, 130.799, 603.378, 130.799, 602.631);
((GeneralPath)shape).curveTo(130.799, 602.224, 130.969, 601.849, 131.30899, 601.503);
((GeneralPath)shape).curveTo(131.64899, 601.156, 132.02399, 600.984, 132.435, 600.984);
((GeneralPath)shape).curveTo(132.826, 600.984, 133.05899, 601.341, 133.133, 602.056);
((GeneralPath)shape).lineTo(133.152, 602.24005);
((GeneralPath)shape).curveTo(133.215, 602.79004, 133.288, 603.17303, 133.37099, 603.3901);
((GeneralPath)shape).curveTo(133.45499, 603.60706, 133.566, 603.71606, 133.70898, 603.71606);
((GeneralPath)shape).curveTo(133.76799, 603.71606, 133.87898, 603.65106, 134.03998, 603.5231);
((GeneralPath)shape).curveTo(134.20198, 603.3951, 134.39497, 603.21906, 134.62097, 602.9991);
((GeneralPath)shape).lineTo(138.38397, 599.2701);
((GeneralPath)shape).curveTo(138.69997, 598.9551, 138.98297, 598.71405, 139.23198, 598.5541);
((GeneralPath)shape).curveTo(139.48198, 598.3921, 139.69199, 598.31006, 139.86697, 598.31006);
((GeneralPath)shape).curveTo(139.99597, 598.31006, 140.08897, 598.3611, 140.14497, 598.45703);
((GeneralPath)shape).curveTo(140.19997, 598.557, 140.22797, 598.723, 140.22797, 598.95605);
((GeneralPath)shape).lineTo(140.22797, 599.154);
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

