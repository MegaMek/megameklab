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
public class AP5 {
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
((GeneralPath)shape).moveTo(217.204, 734.188);
((GeneralPath)shape).curveTo(217.204, 734.651, 216.995, 735.101, 216.577, 735.539);
((GeneralPath)shape).lineTo(216.511, 735.604);
((GeneralPath)shape).lineTo(212.849, 739.463);
((GeneralPath)shape).curveTo(212.071, 740.28, 211.409, 740.912, 210.862, 741.36);
((GeneralPath)shape).curveTo(210.315, 741.805, 209.928, 742.029, 209.703, 742.029);
((GeneralPath)shape).curveTo(209.46701, 742.029, 209.173, 741.91797, 208.821, 741.694);
((GeneralPath)shape).curveTo(208.468, 741.469, 208.242, 741.24097, 208.13899, 741.00397);
((GeneralPath)shape).curveTo(208.051, 740.80695, 207.96999, 740.38794, 207.89299, 739.74695);
((GeneralPath)shape).curveTo(207.816, 739.10596, 207.77798, 738.41394, 207.77798, 737.66595);
((GeneralPath)shape).curveTo(207.77798, 737.25995, 207.94699, 736.884, 208.28798, 736.5369);
((GeneralPath)shape).curveTo(208.62698, 736.1909, 209.00198, 736.0189, 209.41298, 736.0189);
((GeneralPath)shape).curveTo(209.80399, 736.0189, 210.03697, 736.37494, 210.11098, 737.09094);
((GeneralPath)shape).lineTo(210.12898, 737.27594);
((GeneralPath)shape).curveTo(210.19199, 737.8239, 210.26598, 738.2089, 210.34898, 738.42395);
((GeneralPath)shape).curveTo(210.43198, 738.64294, 210.54399, 738.751, 210.68698, 738.751);
((GeneralPath)shape).curveTo(210.74498, 738.751, 210.85599, 738.68695, 211.01797, 738.55896);
((GeneralPath)shape).curveTo(211.17998, 738.42896, 211.37297, 738.25494, 211.59897, 738.035);
((GeneralPath)shape).lineTo(215.36197, 734.30597);
((GeneralPath)shape).curveTo(215.67697, 733.99097, 215.96097, 733.74994, 216.20897, 733.58795);
((GeneralPath)shape).curveTo(216.45697, 733.42596, 216.66797, 733.34595, 216.84196, 733.34595);
((GeneralPath)shape).curveTo(216.97197, 733.34595, 217.06497, 733.39496, 217.11996, 733.4929);
((GeneralPath)shape).curveTo(217.17697, 733.5929, 217.20296, 733.75793, 217.20296, 733.99194);
((GeneralPath)shape).lineTo(217.20296, 734.188);
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

