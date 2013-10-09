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

package megameklab.com.ui.Vehicle.Printing;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class Naval_Turret_IS_3 {
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 344.305);
((GeneralPath)shape).curveTo(492.263, 342.992, 491.199, 341.927, 489.883, 341.927);
((GeneralPath)shape).curveTo(488.57, 341.927, 487.504, 342.993, 487.504, 344.305);
((GeneralPath)shape).curveTo(487.504, 345.621, 488.568, 346.686, 489.883, 346.686);
((GeneralPath)shape).curveTo(491.198, 346.686, 492.263, 345.621, 492.263, 344.305);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_0;
g.setTransform(defaultTransform__0_0);
g.setClip(clip__0_0);
float alpha__0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_1 = g.getClip();
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 344.305);
((GeneralPath)shape).curveTo(492.263, 342.992, 491.198, 341.926, 489.883, 341.926);
((GeneralPath)shape).curveTo(488.57, 341.926, 487.50198, 342.99298, 487.50198, 344.305);
((GeneralPath)shape).curveTo(487.50198, 345.621, 488.568, 346.686, 489.883, 346.686);
((GeneralPath)shape).curveTo(491.197, 346.686, 492.263, 345.621, 492.263, 344.305);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_1;
g.setTransform(defaultTransform__0_1);
g.setClip(clip__0_1);
float alpha__0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_2 = g.getClip();
AffineTransform defaultTransform__0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 325.305);
((GeneralPath)shape).curveTo(492.263, 323.992, 491.199, 322.927, 489.883, 322.927);
((GeneralPath)shape).curveTo(488.57, 322.927, 487.504, 323.993, 487.504, 325.305);
((GeneralPath)shape).curveTo(487.504, 326.621, 488.568, 327.686, 489.883, 327.686);
((GeneralPath)shape).curveTo(491.198, 327.686, 492.263, 326.621, 492.263, 325.305);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_2;
g.setTransform(defaultTransform__0_2);
g.setClip(clip__0_2);
float alpha__0_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_3 = g.getClip();
AffineTransform defaultTransform__0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 325.305);
((GeneralPath)shape).curveTo(492.263, 323.992, 491.198, 322.926, 489.883, 322.926);
((GeneralPath)shape).curveTo(488.57, 322.926, 487.50198, 323.99298, 487.50198, 325.305);
((GeneralPath)shape).curveTo(487.50198, 326.621, 488.568, 327.686, 489.883, 327.686);
((GeneralPath)shape).curveTo(491.197, 327.686, 492.263, 326.621, 492.263, 325.305);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3;
g.setTransform(defaultTransform__0_3);
g.setClip(clip__0_3);
float alpha__0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_4 = g.getClip();
AffineTransform defaultTransform__0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 308.305);
((GeneralPath)shape).curveTo(492.263, 306.992, 491.199, 305.927, 489.883, 305.927);
((GeneralPath)shape).curveTo(488.57, 305.927, 487.504, 306.993, 487.504, 308.305);
((GeneralPath)shape).curveTo(487.504, 309.621, 488.568, 310.686, 489.883, 310.686);
((GeneralPath)shape).curveTo(491.198, 310.686, 492.263, 309.621, 492.263, 308.305);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_4;
g.setTransform(defaultTransform__0_4);
g.setClip(clip__0_4);
float alpha__0_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_5 = g.getClip();
AffineTransform defaultTransform__0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(492.263, 308.305);
((GeneralPath)shape).curveTo(492.263, 306.992, 491.198, 305.926, 489.883, 305.926);
((GeneralPath)shape).curveTo(488.57, 305.926, 487.50198, 306.99298, 487.50198, 308.305);
((GeneralPath)shape).curveTo(487.50198, 309.621, 488.568, 310.686, 489.883, 310.686);
((GeneralPath)shape).curveTo(491.197, 310.686, 492.263, 309.621, 492.263, 308.305);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_5;
g.setTransform(defaultTransform__0_5);
g.setClip(clip__0_5);
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
        return 488;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 306;
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

