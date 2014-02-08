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
public class Mechanized3 {
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
((GeneralPath)shape).moveTo(91.491, 464.119);
((GeneralPath)shape).curveTo(91.491, 464.581, 91.282, 465.03098, 90.863, 465.47098);
((GeneralPath)shape).lineTo(90.798, 465.53497);
((GeneralPath)shape).lineTo(87.135994, 469.39398);
((GeneralPath)shape).curveTo(86.35899, 470.21097, 85.69599, 470.843, 85.148994, 471.28998);
((GeneralPath)shape).curveTo(84.602, 471.736, 84.215996, 471.96, 83.99, 471.96);
((GeneralPath)shape).curveTo(83.753, 471.96, 83.459, 471.849, 83.106995, 471.625);
((GeneralPath)shape).curveTo(82.755, 471.402, 82.52899, 471.172, 82.424995, 470.934);
((GeneralPath)shape).curveTo(82.338, 470.73798, 82.256996, 470.319, 82.17899, 469.67798);
((GeneralPath)shape).curveTo(82.10199, 469.03796, 82.063995, 468.34598, 82.063995, 467.598);
((GeneralPath)shape).curveTo(82.063995, 467.192, 82.23399, 466.815, 82.574, 466.469);
((GeneralPath)shape).curveTo(82.914, 466.123, 83.288994, 465.94998, 83.699, 465.94998);
((GeneralPath)shape).curveTo(84.090996, 465.94998, 84.323, 466.30698, 84.397995, 467.02298);
((GeneralPath)shape).lineTo(84.41699, 467.20697);
((GeneralPath)shape).curveTo(84.479996, 467.75696, 84.55299, 468.13998, 84.635994, 468.35596);
((GeneralPath)shape).curveTo(84.718994, 468.57193, 84.83099, 468.68195, 84.97399, 468.68195);
((GeneralPath)shape).curveTo(85.03299, 468.68195, 85.14299, 468.61795, 85.30599, 468.48895);
((GeneralPath)shape).curveTo(85.467995, 468.36096, 85.660995, 468.18497, 85.88699, 467.96497);
((GeneralPath)shape).lineTo(89.649994, 464.23596);
((GeneralPath)shape).curveTo(89.965, 463.92096, 90.24899, 463.68198, 90.49799, 463.51996);
((GeneralPath)shape).curveTo(90.746994, 463.35797, 90.95799, 463.27795, 91.131996, 463.27795);
((GeneralPath)shape).curveTo(91.260994, 463.27795, 91.354, 463.32697, 91.409996, 463.42496);
((GeneralPath)shape).curveTo(91.465996, 463.52295, 91.493, 463.68896, 91.493, 463.92294);
((GeneralPath)shape).lineTo(91.493, 464.119);
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

