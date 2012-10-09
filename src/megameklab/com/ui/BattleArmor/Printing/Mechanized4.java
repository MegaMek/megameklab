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

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class Mechanized4 {
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
((GeneralPath)shape).moveTo(91.491, 599.154);
((GeneralPath)shape).curveTo(91.491, 599.61597, 91.282, 600.066, 90.863, 600.505);
((GeneralPath)shape).lineTo(90.798, 600.57);
((GeneralPath)shape).lineTo(87.135994, 604.427);
((GeneralPath)shape).curveTo(86.35899, 605.244, 85.69599, 605.877, 85.148994, 606.324);
((GeneralPath)shape).curveTo(84.602, 606.769, 84.215996, 606.99396, 83.99, 606.99396);
((GeneralPath)shape).curveTo(83.753, 606.99396, 83.459, 606.88196, 83.106995, 606.65894);
((GeneralPath)shape).curveTo(82.755, 606.43396, 82.52899, 606.20496, 82.424995, 605.96796);
((GeneralPath)shape).curveTo(82.338, 605.772, 82.256996, 605.35297, 82.17899, 604.712);
((GeneralPath)shape).curveTo(82.10199, 604.07196, 82.063995, 603.378, 82.063995, 602.631);
((GeneralPath)shape).curveTo(82.063995, 602.224, 82.23399, 601.849, 82.574, 601.503);
((GeneralPath)shape).curveTo(82.913994, 601.156, 83.288994, 600.984, 83.699, 600.984);
((GeneralPath)shape).curveTo(84.090996, 600.984, 84.323, 601.341, 84.397995, 602.056);
((GeneralPath)shape).lineTo(84.41699, 602.24005);
((GeneralPath)shape).curveTo(84.479996, 602.79004, 84.55299, 603.17303, 84.635994, 603.3901);
((GeneralPath)shape).curveTo(84.718994, 603.60706, 84.83099, 603.71606, 84.97399, 603.71606);
((GeneralPath)shape).curveTo(85.03299, 603.71606, 85.14299, 603.65106, 85.30599, 603.5231);
((GeneralPath)shape).curveTo(85.468994, 603.3951, 85.660995, 603.21906, 85.88699, 602.9991);
((GeneralPath)shape).lineTo(89.649994, 599.2701);
((GeneralPath)shape).curveTo(89.965, 598.9551, 90.24899, 598.71405, 90.49799, 598.5541);
((GeneralPath)shape).curveTo(90.746994, 598.3921, 90.95799, 598.31006, 91.131996, 598.31006);
((GeneralPath)shape).curveTo(91.260994, 598.31006, 91.354, 598.3611, 91.409996, 598.45703);
((GeneralPath)shape).curveTo(91.465996, 598.557, 91.493, 598.723, 91.493, 598.95605);
((GeneralPath)shape).lineTo(91.493, 599.154);
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

