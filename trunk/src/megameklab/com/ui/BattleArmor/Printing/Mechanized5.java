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
public class Mechanized5 {
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
((GeneralPath)shape).moveTo(91.491, 734.188);
((GeneralPath)shape).curveTo(91.491, 734.651, 91.282, 735.101, 90.863, 735.539);
((GeneralPath)shape).lineTo(90.798, 735.604);
((GeneralPath)shape).lineTo(87.135994, 739.463);
((GeneralPath)shape).curveTo(86.35899, 740.28, 85.69599, 740.912, 85.148994, 741.36);
((GeneralPath)shape).curveTo(84.602, 741.805, 84.215996, 742.029, 83.99, 742.029);
((GeneralPath)shape).curveTo(83.753, 742.029, 83.459, 741.91797, 83.106995, 741.694);
((GeneralPath)shape).curveTo(82.755, 741.469, 82.52899, 741.24097, 82.424995, 741.00397);
((GeneralPath)shape).curveTo(82.338, 740.80695, 82.256996, 740.38794, 82.17899, 739.74695);
((GeneralPath)shape).curveTo(82.10199, 739.10596, 82.063995, 738.41394, 82.063995, 737.66595);
((GeneralPath)shape).curveTo(82.063995, 737.25995, 82.23399, 736.884, 82.574, 736.5369);
((GeneralPath)shape).curveTo(82.913994, 736.1909, 83.288994, 736.0189, 83.699, 736.0189);
((GeneralPath)shape).curveTo(84.090996, 736.0189, 84.323, 736.37494, 84.397995, 737.09094);
((GeneralPath)shape).lineTo(84.41699, 737.27594);
((GeneralPath)shape).curveTo(84.479996, 737.8239, 84.55299, 738.2089, 84.635994, 738.42395);
((GeneralPath)shape).curveTo(84.718994, 738.64294, 84.83099, 738.751, 84.97399, 738.751);
((GeneralPath)shape).curveTo(85.03299, 738.751, 85.14299, 738.68695, 85.30599, 738.55896);
((GeneralPath)shape).curveTo(85.467995, 738.42896, 85.660995, 738.25494, 85.88699, 738.035);
((GeneralPath)shape).lineTo(89.649994, 734.30597);
((GeneralPath)shape).curveTo(89.965, 733.99097, 90.24899, 733.74994, 90.49799, 733.58795);
((GeneralPath)shape).curveTo(90.746994, 733.42596, 90.95799, 733.34595, 91.131996, 733.34595);
((GeneralPath)shape).curveTo(91.260994, 733.34595, 91.354, 733.39496, 91.409996, 733.4929);
((GeneralPath)shape).curveTo(91.465996, 733.5929, 91.493, 733.75793, 91.493, 733.99194);
((GeneralPath)shape).lineTo(91.493, 734.188);
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
        return 734;
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

