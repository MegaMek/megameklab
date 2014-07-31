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
public class Leg3 {
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
((GeneralPath)shape).moveTo(180.362, 464.119);
((GeneralPath)shape).curveTo(180.362, 464.581, 180.152, 465.03098, 179.733, 465.47098);
((GeneralPath)shape).lineTo(179.669, 465.53497);
((GeneralPath)shape).lineTo(176.00601, 469.39398);
((GeneralPath)shape).curveTo(175.22902, 470.21097, 174.56601, 470.843, 174.01901, 471.28998);
((GeneralPath)shape).curveTo(173.47202, 471.736, 173.085, 471.96, 172.86002, 471.96);
((GeneralPath)shape).curveTo(172.62402, 471.96, 172.32901, 471.849, 171.97702, 471.625);
((GeneralPath)shape).curveTo(171.62502, 471.402, 171.39902, 471.172, 171.29602, 470.934);
((GeneralPath)shape).curveTo(171.21002, 470.73798, 171.12701, 470.319, 171.05002, 469.67798);
((GeneralPath)shape).curveTo(170.97302, 469.03796, 170.93501, 468.34598, 170.93501, 467.598);
((GeneralPath)shape).curveTo(170.93501, 467.192, 171.10501, 466.815, 171.445, 466.469);
((GeneralPath)shape).curveTo(171.785, 466.123, 172.16, 465.94998, 172.57101, 465.94998);
((GeneralPath)shape).curveTo(172.96202, 465.94998, 173.195, 466.30698, 173.26901, 467.02298);
((GeneralPath)shape).lineTo(173.28801, 467.20697);
((GeneralPath)shape).curveTo(173.35101, 467.75696, 173.42401, 468.13998, 173.50601, 468.35596);
((GeneralPath)shape).curveTo(173.59001, 468.57297, 173.70201, 468.68195, 173.84401, 468.68195);
((GeneralPath)shape).curveTo(173.90302, 468.68195, 174.01302, 468.61795, 174.17601, 468.48895);
((GeneralPath)shape).curveTo(174.33801, 468.36096, 174.531, 468.18497, 174.757, 467.96497);
((GeneralPath)shape).lineTo(178.52, 464.23596);
((GeneralPath)shape).curveTo(178.836, 463.92096, 179.119, 463.68198, 179.367, 463.51996);
((GeneralPath)shape).curveTo(179.617, 463.35797, 179.828, 463.27795, 180.002, 463.27795);
((GeneralPath)shape).curveTo(180.132, 463.27795, 180.224, 463.32697, 180.281, 463.42496);
((GeneralPath)shape).curveTo(180.336, 463.52396, 180.364, 463.68896, 180.364, 463.92294);
((GeneralPath)shape).lineTo(180.364, 464.119);
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

