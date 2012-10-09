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
public class Swarm3 {
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
((GeneralPath)shape).moveTo(140.227, 464.119);
((GeneralPath)shape).curveTo(140.227, 464.581, 140.017, 465.03098, 139.599, 465.47098);
((GeneralPath)shape).lineTo(139.534, 465.53497);
((GeneralPath)shape).lineTo(135.871, 469.39398);
((GeneralPath)shape).curveTo(135.09401, 470.21097, 134.431, 470.843, 133.884, 471.28998);
((GeneralPath)shape).curveTo(133.337, 471.736, 132.95, 471.96, 132.725, 471.96);
((GeneralPath)shape).curveTo(132.48901, 471.96, 132.194, 471.849, 131.84201, 471.625);
((GeneralPath)shape).curveTo(131.49, 471.402, 131.26302, 471.172, 131.16, 470.934);
((GeneralPath)shape).curveTo(131.074, 470.73798, 130.991, 470.319, 130.914, 469.67798);
((GeneralPath)shape).curveTo(130.837, 469.03796, 130.799, 468.34598, 130.799, 467.598);
((GeneralPath)shape).curveTo(130.799, 467.192, 130.969, 466.815, 131.30899, 466.469);
((GeneralPath)shape).curveTo(131.64899, 466.123, 132.02399, 465.94998, 132.435, 465.94998);
((GeneralPath)shape).curveTo(132.826, 465.94998, 133.05899, 466.30698, 133.133, 467.02298);
((GeneralPath)shape).lineTo(133.152, 467.20697);
((GeneralPath)shape).curveTo(133.215, 467.75696, 133.288, 468.13998, 133.37099, 468.35596);
((GeneralPath)shape).curveTo(133.45499, 468.57297, 133.566, 468.68195, 133.70898, 468.68195);
((GeneralPath)shape).curveTo(133.76799, 468.68195, 133.87898, 468.61795, 134.03998, 468.48895);
((GeneralPath)shape).curveTo(134.20198, 468.36096, 134.39497, 468.18497, 134.62097, 467.96497);
((GeneralPath)shape).lineTo(138.38397, 464.23596);
((GeneralPath)shape).curveTo(138.69997, 463.92096, 138.98297, 463.68198, 139.23198, 463.51996);
((GeneralPath)shape).curveTo(139.48198, 463.35797, 139.69199, 463.27795, 139.86697, 463.27795);
((GeneralPath)shape).curveTo(139.99597, 463.27795, 140.08897, 463.32697, 140.14497, 463.42496);
((GeneralPath)shape).curveTo(140.19997, 463.52396, 140.22797, 463.68896, 140.22797, 463.92294);
((GeneralPath)shape).lineTo(140.22797, 464.119);
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
        return 464;
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

