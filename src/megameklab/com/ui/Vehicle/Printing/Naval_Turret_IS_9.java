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
public class Naval_Turret_IS_9 {
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
((GeneralPath)shape).moveTo(481.766, 333.354);
((GeneralPath)shape).curveTo(481.766, 332.039, 480.69998, 330.976, 479.386, 330.976);
((GeneralPath)shape).curveTo(478.072, 330.976, 477.00598, 332.039, 477.00598, 333.354);
((GeneralPath)shape).curveTo(477.00598, 334.668, 478.07098, 335.732, 479.386, 335.732);
((GeneralPath)shape).curveTo(480.699, 335.732, 481.766, 334.668, 481.766, 333.354);
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
((GeneralPath)shape).moveTo(481.766, 333.354);
((GeneralPath)shape).curveTo(481.766, 332.039, 480.69998, 330.976, 479.386, 330.976);
((GeneralPath)shape).curveTo(478.072, 330.976, 477.00598, 332.039, 477.00598, 333.354);
((GeneralPath)shape).curveTo(477.00598, 334.668, 478.07098, 335.732, 479.386, 335.732);
((GeneralPath)shape).curveTo(480.699, 335.732, 481.766, 334.668, 481.766, 333.354);
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
((GeneralPath)shape).moveTo(481.766, 307.88);
((GeneralPath)shape).curveTo(481.766, 306.566, 480.69998, 305.499, 479.386, 305.499);
((GeneralPath)shape).curveTo(478.072, 305.499, 477.00598, 306.56598, 477.00598, 307.88);
((GeneralPath)shape).curveTo(477.00598, 309.193, 478.07098, 310.26, 479.386, 310.26);
((GeneralPath)shape).curveTo(480.699, 310.26, 481.766, 309.193, 481.766, 307.88);
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
((GeneralPath)shape).moveTo(481.766, 307.88);
((GeneralPath)shape).curveTo(481.766, 306.566, 480.69998, 305.501, 479.386, 305.501);
((GeneralPath)shape).curveTo(478.072, 305.501, 477.00598, 306.566, 477.00598, 307.88);
((GeneralPath)shape).curveTo(477.00598, 309.193, 478.07098, 310.26, 479.386, 310.26);
((GeneralPath)shape).curveTo(480.699, 310.26, 481.766, 309.193, 481.766, 307.88);
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
((GeneralPath)shape).moveTo(492.263, 344.305);
((GeneralPath)shape).curveTo(492.263, 342.992, 491.199, 341.927, 489.883, 341.927);
((GeneralPath)shape).curveTo(488.57, 341.927, 487.504, 342.993, 487.504, 344.305);
((GeneralPath)shape).curveTo(487.504, 345.621, 488.569, 346.686, 489.883, 346.686);
((GeneralPath)shape).curveTo(491.198, 346.686, 492.263, 345.621, 492.263, 344.305);
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
((GeneralPath)shape).moveTo(492.263, 344.305);
((GeneralPath)shape).curveTo(492.263, 342.992, 491.198, 341.926, 489.883, 341.926);
((GeneralPath)shape).curveTo(488.57, 341.926, 487.50198, 342.99298, 487.50198, 344.305);
((GeneralPath)shape).curveTo(487.50198, 345.621, 488.56897, 346.686, 489.883, 346.686);
((GeneralPath)shape).curveTo(491.197, 346.686, 492.263, 345.621, 492.263, 344.305);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_5;
g.setTransform(defaultTransform__0_5);
g.setClip(clip__0_5);
float alpha__0_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_6 = g.getClip();
AffineTransform defaultTransform__0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(488.549, 333.354);
((GeneralPath)shape).curveTo(488.549, 332.039, 487.48502, 330.976, 486.169, 330.976);
((GeneralPath)shape).curveTo(484.85602, 330.976, 483.79, 332.039, 483.79, 333.354);
((GeneralPath)shape).curveTo(483.79, 334.668, 484.855, 335.732, 486.169, 335.732);
((GeneralPath)shape).curveTo(487.484, 335.732, 488.549, 334.668, 488.549, 333.354);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_6;
g.setTransform(defaultTransform__0_6);
g.setClip(clip__0_6);
float alpha__0_7 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_7 = g.getClip();
AffineTransform defaultTransform__0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(488.549, 333.354);
((GeneralPath)shape).curveTo(488.549, 332.039, 487.484, 330.976, 486.169, 330.976);
((GeneralPath)shape).curveTo(484.85602, 330.976, 483.788, 332.039, 483.788, 333.354);
((GeneralPath)shape).curveTo(483.788, 334.668, 484.85498, 335.732, 486.169, 335.732);
((GeneralPath)shape).curveTo(487.483, 335.732, 488.549, 334.668, 488.549, 333.354);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_7;
g.setTransform(defaultTransform__0_7);
g.setClip(clip__0_7);
float alpha__0_8 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_8 = g.getClip();
AffineTransform defaultTransform__0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(488.549, 307.88);
((GeneralPath)shape).curveTo(488.549, 306.566, 487.48502, 305.499, 486.169, 305.499);
((GeneralPath)shape).curveTo(484.85602, 305.499, 483.79, 306.56598, 483.79, 307.88);
((GeneralPath)shape).curveTo(483.79, 309.193, 484.855, 310.26, 486.169, 310.26);
((GeneralPath)shape).curveTo(487.484, 310.26, 488.549, 309.193, 488.549, 307.88);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_8;
g.setTransform(defaultTransform__0_8);
g.setClip(clip__0_8);
float alpha__0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_9 = g.getClip();
AffineTransform defaultTransform__0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(488.549, 307.88);
((GeneralPath)shape).curveTo(488.549, 306.566, 487.484, 305.501, 486.169, 305.501);
((GeneralPath)shape).curveTo(484.85602, 305.501, 483.788, 306.566, 483.788, 307.88);
((GeneralPath)shape).curveTo(483.788, 309.193, 484.85498, 310.26, 486.169, 310.26);
((GeneralPath)shape).curveTo(487.483, 310.26, 488.549, 309.193, 488.549, 307.88);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9;
g.setTransform(defaultTransform__0_9);
g.setClip(clip__0_9);
float alpha__0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_10 = g.getClip();
AffineTransform defaultTransform__0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_10 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.332, 333.354);
((GeneralPath)shape).curveTo(495.332, 332.039, 494.268, 330.976, 492.953, 330.976);
((GeneralPath)shape).curveTo(491.638, 330.976, 490.573, 332.039, 490.573, 333.354);
((GeneralPath)shape).curveTo(490.573, 334.668, 491.638, 335.732, 492.953, 335.732);
((GeneralPath)shape).curveTo(494.268, 335.732, 495.332, 334.668, 495.332, 333.354);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_10;
g.setTransform(defaultTransform__0_10);
g.setClip(clip__0_10);
float alpha__0_11 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_11 = g.getClip();
AffineTransform defaultTransform__0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.332, 333.354);
((GeneralPath)shape).curveTo(495.332, 332.039, 494.268, 330.976, 492.953, 330.976);
((GeneralPath)shape).curveTo(491.638, 330.976, 490.573, 332.039, 490.573, 333.354);
((GeneralPath)shape).curveTo(490.573, 334.668, 491.638, 335.732, 492.953, 335.732);
((GeneralPath)shape).curveTo(494.268, 335.732, 495.332, 334.668, 495.332, 333.354);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_11;
g.setTransform(defaultTransform__0_11);
g.setClip(clip__0_11);
float alpha__0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_12 = g.getClip();
AffineTransform defaultTransform__0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_12 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.332, 307.88);
((GeneralPath)shape).curveTo(495.332, 306.566, 494.268, 305.499, 492.953, 305.499);
((GeneralPath)shape).curveTo(491.638, 305.499, 490.573, 306.56598, 490.573, 307.88);
((GeneralPath)shape).curveTo(490.573, 309.193, 491.638, 310.26, 492.953, 310.26);
((GeneralPath)shape).curveTo(494.268, 310.26, 495.332, 309.193, 495.332, 307.88);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_12;
g.setTransform(defaultTransform__0_12);
g.setClip(clip__0_12);
float alpha__0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_13 = g.getClip();
AffineTransform defaultTransform__0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_13 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.332, 307.88);
((GeneralPath)shape).curveTo(495.332, 306.566, 494.268, 305.501, 492.953, 305.501);
((GeneralPath)shape).curveTo(491.638, 305.501, 490.573, 306.566, 490.573, 307.88);
((GeneralPath)shape).curveTo(490.573, 309.193, 491.638, 310.26, 492.953, 310.26);
((GeneralPath)shape).curveTo(494.268, 310.26, 495.332, 309.193, 495.332, 307.88);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_13;
g.setTransform(defaultTransform__0_13);
g.setClip(clip__0_13);
float alpha__0_14 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_14 = g.getClip();
AffineTransform defaultTransform__0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_14 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.115, 333.354);
((GeneralPath)shape).curveTo(502.115, 332.039, 501.051, 330.976, 499.736, 330.976);
((GeneralPath)shape).curveTo(498.423, 330.976, 497.35498, 332.039, 497.35498, 333.354);
((GeneralPath)shape).curveTo(497.35498, 334.668, 498.42297, 335.732, 499.736, 335.732);
((GeneralPath)shape).curveTo(501.051, 335.732, 502.115, 334.668, 502.115, 333.354);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_14;
g.setTransform(defaultTransform__0_14);
g.setClip(clip__0_14);
float alpha__0_15 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_15 = g.getClip();
AffineTransform defaultTransform__0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_15 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.115, 333.354);
((GeneralPath)shape).curveTo(502.115, 332.039, 501.051, 330.976, 499.735, 330.976);
((GeneralPath)shape).curveTo(498.422, 330.976, 497.35498, 332.039, 497.35498, 333.354);
((GeneralPath)shape).curveTo(497.35498, 334.668, 498.421, 335.732, 499.735, 335.732);
((GeneralPath)shape).curveTo(501.051, 335.732, 502.115, 334.668, 502.115, 333.354);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_15;
g.setTransform(defaultTransform__0_15);
g.setClip(clip__0_15);
float alpha__0_16 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_16 = g.getClip();
AffineTransform defaultTransform__0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_16 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.115, 307.88);
((GeneralPath)shape).curveTo(502.115, 306.566, 501.051, 305.499, 499.736, 305.499);
((GeneralPath)shape).curveTo(498.423, 305.499, 497.35498, 306.56598, 497.35498, 307.88);
((GeneralPath)shape).curveTo(497.35498, 309.193, 498.42297, 310.26, 499.736, 310.26);
((GeneralPath)shape).curveTo(501.051, 310.26, 502.115, 309.193, 502.115, 307.88);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_16;
g.setTransform(defaultTransform__0_16);
g.setClip(clip__0_16);
float alpha__0_17 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_17 = g.getClip();
AffineTransform defaultTransform__0_17 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_17 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.115, 307.88);
((GeneralPath)shape).curveTo(502.115, 306.566, 501.051, 305.501, 499.735, 305.501);
((GeneralPath)shape).curveTo(498.422, 305.501, 497.35498, 306.566, 497.35498, 307.88);
((GeneralPath)shape).curveTo(497.35498, 309.193, 498.421, 310.26, 499.735, 310.26);
((GeneralPath)shape).curveTo(501.051, 310.26, 502.115, 309.193, 502.115, 307.88);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_17;
g.setTransform(defaultTransform__0_17);
g.setClip(clip__0_17);
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
        return 477;
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

