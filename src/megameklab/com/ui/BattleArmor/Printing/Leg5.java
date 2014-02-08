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
public class Leg5 {
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
((GeneralPath)shape).moveTo(180.362, 734.188);
((GeneralPath)shape).curveTo(180.362, 734.651, 180.152, 735.101, 179.733, 735.539);
((GeneralPath)shape).lineTo(179.669, 735.604);
((GeneralPath)shape).lineTo(176.00601, 739.463);
((GeneralPath)shape).curveTo(175.22902, 740.28, 174.56601, 740.912, 174.01901, 741.36);
((GeneralPath)shape).curveTo(173.47202, 741.805, 173.085, 742.029, 172.86002, 742.029);
((GeneralPath)shape).curveTo(172.62402, 742.029, 172.32901, 741.91797, 171.97702, 741.694);
((GeneralPath)shape).curveTo(171.62502, 741.469, 171.39902, 741.24097, 171.29602, 741.00397);
((GeneralPath)shape).curveTo(171.21002, 740.80695, 171.12701, 740.38794, 171.05002, 739.74695);
((GeneralPath)shape).curveTo(170.97302, 739.10596, 170.93501, 738.41394, 170.93501, 737.66595);
((GeneralPath)shape).curveTo(170.93501, 737.25995, 171.10501, 736.884, 171.445, 736.5369);
((GeneralPath)shape).curveTo(171.785, 736.1909, 172.16, 736.0189, 172.57101, 736.0189);
((GeneralPath)shape).curveTo(172.96202, 736.0189, 173.195, 736.37494, 173.26901, 737.09094);
((GeneralPath)shape).lineTo(173.28801, 737.27594);
((GeneralPath)shape).curveTo(173.35101, 737.8239, 173.42401, 738.2089, 173.50601, 738.42395);
((GeneralPath)shape).curveTo(173.59001, 738.64294, 173.70201, 738.751, 173.84401, 738.751);
((GeneralPath)shape).curveTo(173.90302, 738.751, 174.01302, 738.68695, 174.17601, 738.55896);
((GeneralPath)shape).curveTo(174.33801, 738.42896, 174.531, 738.25494, 174.757, 738.035);
((GeneralPath)shape).lineTo(178.52, 734.30597);
((GeneralPath)shape).curveTo(178.836, 733.99097, 179.119, 733.74994, 179.367, 733.58795);
((GeneralPath)shape).curveTo(179.617, 733.42596, 179.828, 733.34595, 180.002, 733.34595);
((GeneralPath)shape).curveTo(180.132, 733.34595, 180.224, 733.39496, 180.281, 733.4929);
((GeneralPath)shape).curveTo(180.336, 733.5929, 180.364, 733.75793, 180.364, 733.99194);
((GeneralPath)shape).lineTo(180.364, 734.188);
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

