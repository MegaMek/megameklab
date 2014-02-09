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
public class Swarm5 {
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
((GeneralPath)shape).moveTo(140.227, 734.188);
((GeneralPath)shape).curveTo(140.227, 734.651, 140.017, 735.101, 139.599, 735.539);
((GeneralPath)shape).lineTo(139.534, 735.604);
((GeneralPath)shape).lineTo(135.871, 739.463);
((GeneralPath)shape).curveTo(135.09401, 740.28, 134.431, 740.912, 133.884, 741.36);
((GeneralPath)shape).curveTo(133.337, 741.805, 132.95, 742.029, 132.725, 742.029);
((GeneralPath)shape).curveTo(132.48901, 742.029, 132.194, 741.91797, 131.84201, 741.694);
((GeneralPath)shape).curveTo(131.49, 741.469, 131.26302, 741.24097, 131.16, 741.00397);
((GeneralPath)shape).curveTo(131.074, 740.80695, 130.991, 740.38794, 130.914, 739.74695);
((GeneralPath)shape).curveTo(130.837, 739.10596, 130.799, 738.41394, 130.799, 737.66595);
((GeneralPath)shape).curveTo(130.799, 737.25995, 130.969, 736.884, 131.30899, 736.5369);
((GeneralPath)shape).curveTo(131.64899, 736.1909, 132.02399, 736.0189, 132.435, 736.0189);
((GeneralPath)shape).curveTo(132.826, 736.0189, 133.05899, 736.37494, 133.133, 737.09094);
((GeneralPath)shape).lineTo(133.152, 737.27594);
((GeneralPath)shape).curveTo(133.215, 737.8239, 133.288, 738.2089, 133.37099, 738.42395);
((GeneralPath)shape).curveTo(133.45499, 738.64294, 133.566, 738.751, 133.70898, 738.751);
((GeneralPath)shape).curveTo(133.76799, 738.751, 133.87898, 738.68695, 134.03998, 738.55896);
((GeneralPath)shape).curveTo(134.20198, 738.42896, 134.39497, 738.25494, 134.62097, 738.035);
((GeneralPath)shape).lineTo(138.38397, 734.30597);
((GeneralPath)shape).curveTo(138.69997, 733.99097, 138.98297, 733.74994, 139.23198, 733.58795);
((GeneralPath)shape).curveTo(139.48198, 733.42596, 139.69199, 733.34595, 139.86697, 733.34595);
((GeneralPath)shape).curveTo(139.99597, 733.34595, 140.08897, 733.39496, 140.14497, 733.4929);
((GeneralPath)shape).curveTo(140.19997, 733.5929, 140.22797, 733.75793, 140.22797, 733.99194);
((GeneralPath)shape).lineTo(140.22797, 734.188);
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

