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

package megameklab.com.ui.Mek.Printing;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class BipedIS25 {
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
// _0_0 is CompositeGraphicsNode
float alpha__0_0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0 = g.getClip();
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0 is CompositeGraphicsNode
float alpha__0_0_0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0 is CompositeGraphicsNode
float alpha__0_0_0_0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.48, 423.47);
((GeneralPath)shape).curveTo(481.48502, 423.47, 480.659, 422.642, 480.659, 421.648);
((GeneralPath)shape).curveTo(480.659, 420.656, 481.485, 419.828, 482.48, 419.828);
((GeneralPath)shape).curveTo(483.47202, 419.828, 484.30103, 420.656, 484.30103, 421.648);
((GeneralPath)shape).curveTo(484.302, 422.642, 483.473, 423.47, 482.48, 423.47);
((GeneralPath)shape).lineTo(482.48, 423.47);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_0;
g.setTransform(defaultTransform__0_0_0_0_0);
g.setClip(clip__0_0_0_0_0);
float alpha__0_0_0_0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.181, 415.8);
((GeneralPath)shape).curveTo(477.185, 415.8, 476.356, 414.972, 476.356, 413.97998);
((GeneralPath)shape).curveTo(476.356, 412.986, 477.185, 412.158, 478.181, 412.158);
((GeneralPath)shape).curveTo(479.173, 412.158, 480.001, 412.986, 480.001, 413.97998);
((GeneralPath)shape).curveTo(480.001, 414.972, 479.173, 415.8, 478.181, 415.8);
((GeneralPath)shape).lineTo(478.181, 415.8);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_1;
g.setTransform(defaultTransform__0_0_0_0_1);
g.setClip(clip__0_0_0_0_1);
float alpha__0_0_0_0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(474.077, 423.47);
((GeneralPath)shape).curveTo(473.085, 423.47, 472.25598, 422.642, 472.25598, 421.648);
((GeneralPath)shape).curveTo(472.25598, 420.656, 473.085, 419.828, 474.077, 419.828);
((GeneralPath)shape).curveTo(475.072, 419.828, 475.897, 420.656, 475.897, 421.648);
((GeneralPath)shape).curveTo(475.897, 422.642, 475.072, 423.47, 474.077, 423.47);
((GeneralPath)shape).lineTo(474.077, 423.47);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_2;
g.setTransform(defaultTransform__0_0_0_0_2);
g.setClip(clip__0_0_0_0_2);
float alpha__0_0_0_0_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3 is CompositeGraphicsNode
float alpha__0_0_0_0_3_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(500.253, 506.338);
((GeneralPath)shape).curveTo(499.274, 506.526, 498.304, 505.872, 498.115, 504.898);
((GeneralPath)shape).curveTo(497.92398, 503.922, 498.578, 502.952, 499.556, 502.76202);
((GeneralPath)shape).curveTo(500.532, 502.57303, 501.499, 503.22702, 501.69, 504.20102);
((GeneralPath)shape).curveTo(501.879, 505.178, 501.225, 506.148, 500.253, 506.338);
((GeneralPath)shape).lineTo(500.253, 506.338);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_0;
g.setTransform(defaultTransform__0_0_0_0_3_0);
g.setClip(clip__0_0_0_0_3_0);
float alpha__0_0_0_0_3_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(513.015, 572.006);
((GeneralPath)shape).curveTo(512.039, 572.198, 511.069, 571.544, 510.88, 570.568);
((GeneralPath)shape).curveTo(510.69, 569.592, 511.344, 568.622, 512.31903, 568.43097);
((GeneralPath)shape).curveTo(513.29706, 568.243, 514.263, 568.896, 514.45605, 569.87195);
((GeneralPath)shape).curveTo(514.646, 570.848, 513.989, 571.817, 513.015, 572.006);
((GeneralPath)shape).lineTo(513.015, 572.006);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_1;
g.setTransform(defaultTransform__0_0_0_0_3_1);
g.setClip(clip__0_0_0_0_3_1);
float alpha__0_0_0_0_3_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(503.441, 522.755);
((GeneralPath)shape).curveTo(502.466, 522.943, 501.496, 522.289, 501.30402, 521.314);
((GeneralPath)shape).curveTo(501.11603, 520.33905, 501.77103, 519.37103, 502.74503, 519.181);
((GeneralPath)shape).curveTo(503.72003, 518.99005, 504.69003, 519.64404, 504.88202, 520.619);
((GeneralPath)shape).curveTo(505.072, 521.595, 504.418, 522.564, 503.441, 522.755);
((GeneralPath)shape).lineTo(503.441, 522.755);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_2;
g.setTransform(defaultTransform__0_0_0_0_3_2);
g.setClip(clip__0_0_0_0_3_2);
float alpha__0_0_0_0_3_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(506.633, 539.173);
((GeneralPath)shape).curveTo(505.65698, 539.36096, 504.68698, 538.70795, 504.498, 537.732);
((GeneralPath)shape).curveTo(504.30698, 536.756, 504.961, 535.787, 505.93997, 535.597);
((GeneralPath)shape).curveTo(506.91498, 535.407, 507.88196, 536.061, 508.07297, 537.037);
((GeneralPath)shape).curveTo(508.265, 538.013, 507.61, 538.983, 506.633, 539.173);
((GeneralPath)shape).lineTo(506.633, 539.173);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_3;
g.setTransform(defaultTransform__0_0_0_0_3_3);
g.setClip(clip__0_0_0_0_3_3);
float alpha__0_0_0_0_3_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_4 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(509.826, 555.59);
((GeneralPath)shape).curveTo(508.84998, 555.77905, 507.87997, 555.125, 507.688, 554.15);
((GeneralPath)shape).curveTo(507.5, 553.174, 508.155, 552.205, 509.12997, 552.01404);
((GeneralPath)shape).curveTo(510.10498, 551.825, 511.07498, 552.478, 511.26398, 553.45404);
((GeneralPath)shape).curveTo(511.455, 554.43, 510.802, 555.4, 509.826, 555.59);
((GeneralPath)shape).lineTo(509.826, 555.59);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_4;
g.setTransform(defaultTransform__0_0_0_0_3_4);
g.setClip(clip__0_0_0_0_3_4);
float alpha__0_0_0_0_3_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_5 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_5 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(497.06, 489.92);
((GeneralPath)shape).curveTo(496.08398, 490.11002, 495.11398, 489.45602, 494.923, 488.48102);
((GeneralPath)shape).curveTo(494.732, 487.50403, 495.389, 486.535, 496.364, 486.34503);
((GeneralPath)shape).curveTo(497.33902, 486.15503, 498.30902, 486.80902, 498.5, 487.78503);
((GeneralPath)shape).curveTo(498.689, 488.761, 498.035, 489.731, 497.06, 489.92);
((GeneralPath)shape).lineTo(497.06, 489.92);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_5;
g.setTransform(defaultTransform__0_0_0_0_3_5);
g.setClip(clip__0_0_0_0_3_5);
origAlpha = alpha__0_0_0_0_3;
g.setTransform(defaultTransform__0_0_0_0_3);
g.setClip(clip__0_0_0_0_3);
float alpha__0_0_0_0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4 is CompositeGraphicsNode
float alpha__0_0_0_0_4_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(455.267, 506.338);
((GeneralPath)shape).curveTo(456.246, 506.526, 457.217, 505.872, 457.407, 504.898);
((GeneralPath)shape).curveTo(457.596, 503.922, 456.94202, 502.952, 455.96402, 502.76202);
((GeneralPath)shape).curveTo(454.99002, 502.57303, 454.019, 503.22702, 453.83002, 504.20102);
((GeneralPath)shape).curveTo(453.641, 505.178, 454.295, 506.148, 455.267, 506.338);
((GeneralPath)shape).lineTo(455.267, 506.338);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_0;
g.setTransform(defaultTransform__0_0_0_0_4_0);
g.setClip(clip__0_0_0_0_4_0);
float alpha__0_0_0_0_4_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(442.505, 572.006);
((GeneralPath)shape).curveTo(443.48102, 572.198, 444.45102, 571.544, 444.64, 570.568);
((GeneralPath)shape).curveTo(444.83102, 569.592, 444.177, 568.622, 443.199, 568.43097);
((GeneralPath)shape).curveTo(442.223, 568.243, 441.25702, 568.896, 441.065, 569.87195);
((GeneralPath)shape).curveTo(440.875, 570.848, 441.53, 571.817, 442.505, 572.006);
((GeneralPath)shape).lineTo(442.505, 572.006);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_1;
g.setTransform(defaultTransform__0_0_0_0_4_1);
g.setClip(clip__0_0_0_0_4_1);
float alpha__0_0_0_0_4_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(452.078, 522.755);
((GeneralPath)shape).curveTo(453.05402, 522.943, 454.02402, 522.289, 454.216, 521.314);
((GeneralPath)shape).curveTo(454.404, 520.33905, 453.749, 519.37103, 452.77402, 519.181);
((GeneralPath)shape).curveTo(451.799, 518.99005, 450.829, 519.64404, 450.63702, 520.619);
((GeneralPath)shape).curveTo(450.449, 521.595, 451.104, 522.564, 452.078, 522.755);
((GeneralPath)shape).lineTo(452.078, 522.755);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_2;
g.setTransform(defaultTransform__0_0_0_0_4_2);
g.setClip(clip__0_0_0_0_4_2);
float alpha__0_0_0_0_4_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(448.888, 539.173);
((GeneralPath)shape).curveTo(449.865, 539.36096, 450.835, 538.70795, 451.023, 537.732);
((GeneralPath)shape).curveTo(451.21402, 536.756, 450.56, 535.787, 449.582, 535.597);
((GeneralPath)shape).curveTo(448.606, 535.407, 447.639, 536.061, 447.448, 537.037);
((GeneralPath)shape).curveTo(447.256, 538.013, 447.91, 538.983, 448.888, 539.173);
((GeneralPath)shape).lineTo(448.888, 539.173);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_3;
g.setTransform(defaultTransform__0_0_0_0_4_3);
g.setClip(clip__0_0_0_0_4_3);
float alpha__0_0_0_0_4_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_4 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(445.695, 555.59);
((GeneralPath)shape).curveTo(446.67102, 555.77905, 447.64102, 555.125, 447.833, 554.15);
((GeneralPath)shape).curveTo(448.021, 553.174, 447.366, 552.205, 446.39102, 552.01404);
((GeneralPath)shape).curveTo(445.41602, 551.825, 444.446, 552.478, 444.25504, 553.45404);
((GeneralPath)shape).curveTo(444.066, 554.43, 444.72, 555.4, 445.695, 555.59);
((GeneralPath)shape).lineTo(445.695, 555.59);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_4;
g.setTransform(defaultTransform__0_0_0_0_4_4);
g.setClip(clip__0_0_0_0_4_4);
float alpha__0_0_0_0_4_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_5 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_5 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(458.46, 489.92);
((GeneralPath)shape).curveTo(459.436, 490.11002, 460.406, 489.45602, 460.598, 488.48102);
((GeneralPath)shape).curveTo(460.788, 487.50403, 460.13098, 486.535, 459.156, 486.34503);
((GeneralPath)shape).curveTo(458.181, 486.15503, 457.211, 486.80902, 457.022, 487.78503);
((GeneralPath)shape).curveTo(456.831, 488.761, 457.485, 489.731, 458.46, 489.92);
((GeneralPath)shape).lineTo(458.46, 489.92);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_5;
g.setTransform(defaultTransform__0_0_0_0_4_5);
g.setClip(clip__0_0_0_0_4_5);
origAlpha = alpha__0_0_0_0_4;
g.setTransform(defaultTransform__0_0_0_0_4);
g.setClip(clip__0_0_0_0_4);
float alpha__0_0_0_0_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5 is CompositeGraphicsNode
float alpha__0_0_0_0_5_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(525.585, 434.635);
((GeneralPath)shape).curveTo(524.594, 434.72202, 523.697, 433.97, 523.61005, 432.98);
((GeneralPath)shape).curveTo(523.52203, 431.99002, 524.27606, 431.09302, 525.26807, 431.006);
((GeneralPath)shape).curveTo(526.25507, 430.919, 527.15106, 431.67102, 527.2391, 432.66);
((GeneralPath)shape).curveTo(527.326, 433.651, 526.573, 434.548, 525.585, 434.635);
((GeneralPath)shape).lineTo(525.585, 434.635);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_0;
g.setTransform(defaultTransform__0_0_0_0_5_0);
g.setClip(clip__0_0_0_0_5_0);
float alpha__0_0_0_0_5_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(527.211, 453.246);
((GeneralPath)shape).curveTo(526.222, 453.333, 525.322, 452.58002, 525.238, 451.59);
((GeneralPath)shape).curveTo(525.151, 450.6, 525.902, 449.704, 526.893, 449.617);
((GeneralPath)shape).curveTo(527.88403, 449.529, 528.78, 450.283, 528.867, 451.273);
((GeneralPath)shape).curveTo(528.952, 452.263, 528.201, 453.16, 527.211, 453.246);
((GeneralPath)shape).lineTo(527.211, 453.246);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_1;
g.setTransform(defaultTransform__0_0_0_0_5_1);
g.setClip(clip__0_0_0_0_5_1);
float alpha__0_0_0_0_5_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(528.841, 471.857);
((GeneralPath)shape).curveTo(527.85, 471.944, 526.953, 471.192, 526.866, 470.202);
((GeneralPath)shape).curveTo(526.778, 469.212, 527.53204, 468.315, 528.52405, 468.228);
((GeneralPath)shape).curveTo(529.51105, 468.141, 530.41003, 468.893, 530.49506, 469.883);
((GeneralPath)shape).curveTo(530.583, 470.874, 529.829, 471.77, 528.841, 471.857);
((GeneralPath)shape).lineTo(528.841, 471.857);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_2;
g.setTransform(defaultTransform__0_0_0_0_5_2);
g.setClip(clip__0_0_0_0_5_2);
float alpha__0_0_0_0_5_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(530.469, 490.469);
((GeneralPath)shape).curveTo(529.47797, 490.556, 528.581, 489.804, 528.493, 488.814);
((GeneralPath)shape).curveTo(528.409, 487.824, 529.16, 486.927, 530.151, 486.84);
((GeneralPath)shape).curveTo(531.142, 486.75198, 532.038, 487.505, 532.125, 488.495);
((GeneralPath)shape).curveTo(532.21, 489.485, 531.46, 490.381, 530.469, 490.469);
((GeneralPath)shape).lineTo(530.469, 490.469);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_3;
g.setTransform(defaultTransform__0_0_0_0_5_3);
g.setClip(clip__0_0_0_0_5_3);
origAlpha = alpha__0_0_0_0_5;
g.setTransform(defaultTransform__0_0_0_0_5);
g.setClip(clip__0_0_0_0_5);
float alpha__0_0_0_0_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6 is CompositeGraphicsNode
float alpha__0_0_0_0_6_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(431.106, 434.635);
((GeneralPath)shape).curveTo(432.097, 434.72202, 432.99698, 433.97, 433.08398, 432.98);
((GeneralPath)shape).curveTo(433.168, 431.99002, 432.417, 431.09302, 431.426, 431.006);
((GeneralPath)shape).curveTo(430.435, 430.919, 429.541, 431.67102, 429.452, 432.66);
((GeneralPath)shape).curveTo(429.367, 433.651, 430.118, 434.548, 431.106, 434.635);
((GeneralPath)shape).lineTo(431.106, 434.635);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_0;
g.setTransform(defaultTransform__0_0_0_0_6_0);
g.setClip(clip__0_0_0_0_6_0);
float alpha__0_0_0_0_6_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(429.479, 453.246);
((GeneralPath)shape).curveTo(430.471, 453.333, 431.367, 452.58002, 431.45502, 451.59);
((GeneralPath)shape).curveTo(431.54202, 450.6, 430.78802, 449.704, 429.79703, 449.617);
((GeneralPath)shape).curveTo(428.80902, 449.529, 427.91202, 450.283, 427.82602, 451.273);
((GeneralPath)shape).curveTo(427.737, 452.263, 428.49, 453.16, 429.479, 453.246);
((GeneralPath)shape).lineTo(429.479, 453.246);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_1;
g.setTransform(defaultTransform__0_0_0_0_6_1);
g.setClip(clip__0_0_0_0_6_1);
float alpha__0_0_0_0_6_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(427.852, 471.857);
((GeneralPath)shape).curveTo(428.843, 471.944, 429.74, 471.192, 429.827, 470.202);
((GeneralPath)shape).curveTo(429.914, 469.212, 429.163, 468.315, 428.172, 468.228);
((GeneralPath)shape).curveTo(427.181, 468.141, 426.285, 468.893, 426.197, 469.883);
((GeneralPath)shape).curveTo(426.112, 470.874, 426.863, 471.77, 427.852, 471.857);
((GeneralPath)shape).lineTo(427.852, 471.857);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_2;
g.setTransform(defaultTransform__0_0_0_0_6_2);
g.setClip(clip__0_0_0_0_6_2);
float alpha__0_0_0_0_6_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(426.223, 490.469);
((GeneralPath)shape).curveTo(427.214, 490.556, 428.111, 489.804, 428.198, 488.814);
((GeneralPath)shape).curveTo(428.286, 487.824, 427.532, 486.927, 426.542, 486.84);
((GeneralPath)shape).curveTo(425.55298, 486.75198, 424.65698, 487.505, 424.569, 488.495);
((GeneralPath)shape).curveTo(424.48102, 489.485, 425.234, 490.381, 426.223, 490.469);
((GeneralPath)shape).lineTo(426.223, 490.469);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_3;
g.setTransform(defaultTransform__0_0_0_0_6_3);
g.setClip(clip__0_0_0_0_6_3);
origAlpha = alpha__0_0_0_0_6;
g.setTransform(defaultTransform__0_0_0_0_6);
g.setClip(clip__0_0_0_0_6);
float alpha__0_0_0_0_7 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_7 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_7 is CompositeGraphicsNode
float alpha__0_0_0_0_7_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_7_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_7_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_7_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(473.793, 452.526);
((GeneralPath)shape).curveTo(472.798, 452.526, 471.969, 451.699, 471.969, 450.705);
((GeneralPath)shape).curveTo(471.969, 449.71198, 472.798, 448.88397, 473.793, 448.88397);
((GeneralPath)shape).curveTo(474.785, 448.88397, 475.614, 449.71198, 475.614, 450.705);
((GeneralPath)shape).curveTo(475.614, 451.699, 474.785, 452.526, 473.793, 452.526);
((GeneralPath)shape).lineTo(473.793, 452.526);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_7_0;
g.setTransform(defaultTransform__0_0_0_0_7_0);
g.setClip(clip__0_0_0_0_7_0);
float alpha__0_0_0_0_7_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_7_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_7_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_7_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.905, 452.526);
((GeneralPath)shape).curveTo(481.909, 452.526, 481.08, 451.699, 481.08, 450.705);
((GeneralPath)shape).curveTo(481.08, 449.71198, 481.909, 448.88397, 482.905, 448.88397);
((GeneralPath)shape).curveTo(483.897, 448.88397, 484.725, 449.71198, 484.725, 450.705);
((GeneralPath)shape).curveTo(484.726, 451.699, 483.897, 452.526, 482.905, 452.526);
((GeneralPath)shape).lineTo(482.905, 452.526);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_7_1;
g.setTransform(defaultTransform__0_0_0_0_7_1);
g.setClip(clip__0_0_0_0_7_1);
origAlpha = alpha__0_0_0_0_7;
g.setTransform(defaultTransform__0_0_0_0_7);
g.setClip(clip__0_0_0_0_7);
float alpha__0_0_0_0_8 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_8 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_8 is CompositeGraphicsNode
float alpha__0_0_0_0_8_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_8_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_8_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_8_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(473.793, 438.477);
((GeneralPath)shape).curveTo(472.798, 438.477, 471.969, 437.65, 471.969, 436.65598);
((GeneralPath)shape).curveTo(471.969, 435.66296, 472.798, 434.83496, 473.793, 434.83496);
((GeneralPath)shape).curveTo(474.785, 434.83496, 475.614, 435.66296, 475.614, 436.65598);
((GeneralPath)shape).curveTo(475.614, 437.649, 474.785, 438.477, 473.793, 438.477);
((GeneralPath)shape).lineTo(473.793, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_8_0;
g.setTransform(defaultTransform__0_0_0_0_8_0);
g.setClip(clip__0_0_0_0_8_0);
float alpha__0_0_0_0_8_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_8_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_8_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_8_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.905, 438.477);
((GeneralPath)shape).curveTo(481.909, 438.477, 481.08, 437.65, 481.08, 436.65598);
((GeneralPath)shape).curveTo(481.08, 435.66296, 481.909, 434.83496, 482.905, 434.83496);
((GeneralPath)shape).curveTo(483.897, 434.83496, 484.725, 435.66296, 484.725, 436.65598);
((GeneralPath)shape).curveTo(484.726, 437.649, 483.897, 438.477, 482.905, 438.477);
((GeneralPath)shape).lineTo(482.905, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_8_1;
g.setTransform(defaultTransform__0_0_0_0_8_1);
g.setClip(clip__0_0_0_0_8_1);
origAlpha = alpha__0_0_0_0_8;
g.setTransform(defaultTransform__0_0_0_0_8);
g.setClip(clip__0_0_0_0_8);
float alpha__0_0_0_0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_9 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_9 is CompositeGraphicsNode
float alpha__0_0_0_0_9_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_9_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_9_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_9_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(473.793, 466.577);
((GeneralPath)shape).curveTo(472.798, 466.577, 471.969, 465.75, 471.969, 464.75598);
((GeneralPath)shape).curveTo(471.969, 463.76297, 472.798, 462.93497, 473.793, 462.93497);
((GeneralPath)shape).curveTo(474.785, 462.93497, 475.614, 463.76297, 475.614, 464.75598);
((GeneralPath)shape).curveTo(475.614, 465.75, 474.785, 466.577, 473.793, 466.577);
((GeneralPath)shape).lineTo(473.793, 466.577);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_9_0;
g.setTransform(defaultTransform__0_0_0_0_9_0);
g.setClip(clip__0_0_0_0_9_0);
float alpha__0_0_0_0_9_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_9_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_9_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_9_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.905, 466.577);
((GeneralPath)shape).curveTo(481.909, 466.577, 481.08, 465.75, 481.08, 464.75598);
((GeneralPath)shape).curveTo(481.08, 463.76297, 481.909, 462.93497, 482.905, 462.93497);
((GeneralPath)shape).curveTo(483.897, 462.93497, 484.725, 463.76297, 484.725, 464.75598);
((GeneralPath)shape).curveTo(484.726, 465.75, 483.897, 466.577, 482.905, 466.577);
((GeneralPath)shape).lineTo(482.905, 466.577);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_9_1;
g.setTransform(defaultTransform__0_0_0_0_9_1);
g.setClip(clip__0_0_0_0_9_1);
origAlpha = alpha__0_0_0_0_9;
g.setTransform(defaultTransform__0_0_0_0_9);
g.setClip(clip__0_0_0_0_9);
float alpha__0_0_0_0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_10 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_10 is CompositeGraphicsNode
float alpha__0_0_0_0_10_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_10_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_10_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_10_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(473.793, 480.627);
((GeneralPath)shape).curveTo(472.798, 480.627, 471.969, 479.80002, 471.969, 478.806);
((GeneralPath)shape).curveTo(471.969, 477.813, 472.798, 476.985, 473.793, 476.985);
((GeneralPath)shape).curveTo(474.785, 476.985, 475.614, 477.813, 475.614, 478.806);
((GeneralPath)shape).curveTo(475.614, 479.8, 474.785, 480.627, 473.793, 480.627);
((GeneralPath)shape).lineTo(473.793, 480.627);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_10_0;
g.setTransform(defaultTransform__0_0_0_0_10_0);
g.setClip(clip__0_0_0_0_10_0);
float alpha__0_0_0_0_10_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_10_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_10_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_10_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.905, 480.627);
((GeneralPath)shape).curveTo(481.909, 480.627, 481.08, 479.80002, 481.08, 478.806);
((GeneralPath)shape).curveTo(481.08, 477.813, 481.909, 476.985, 482.905, 476.985);
((GeneralPath)shape).curveTo(483.897, 476.985, 484.725, 477.813, 484.725, 478.806);
((GeneralPath)shape).curveTo(484.726, 479.8, 483.897, 480.627, 482.905, 480.627);
((GeneralPath)shape).lineTo(482.905, 480.627);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_10_1;
g.setTransform(defaultTransform__0_0_0_0_10_1);
g.setClip(clip__0_0_0_0_10_1);
origAlpha = alpha__0_0_0_0_10;
g.setTransform(defaultTransform__0_0_0_0_10);
g.setClip(clip__0_0_0_0_10);
float alpha__0_0_0_0_11 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_11 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_11 is CompositeGraphicsNode
float alpha__0_0_0_0_11_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_11_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_11_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_11_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(506.989, 438.477);
((GeneralPath)shape).curveTo(507.98502, 438.477, 508.81403, 437.649, 508.81403, 436.655);
((GeneralPath)shape).curveTo(508.81403, 435.663, 507.98502, 434.83398, 506.989, 434.83398);
((GeneralPath)shape).curveTo(505.997, 434.83398, 505.169, 435.663, 505.169, 436.655);
((GeneralPath)shape).curveTo(505.169, 437.648, 505.997, 438.477, 506.989, 438.477);
((GeneralPath)shape).lineTo(506.989, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_11_0;
g.setTransform(defaultTransform__0_0_0_0_11_0);
g.setClip(clip__0_0_0_0_11_0);
float alpha__0_0_0_0_11_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_11_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_11_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_11_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.485, 438.477);
((GeneralPath)shape).curveTo(496.47998, 438.477, 497.306, 437.649, 497.306, 436.655);
((GeneralPath)shape).curveTo(497.306, 435.663, 496.48, 434.83398, 495.485, 434.83398);
((GeneralPath)shape).curveTo(494.49298, 434.83398, 493.66397, 435.663, 493.66397, 436.655);
((GeneralPath)shape).curveTo(493.664, 437.648, 494.493, 438.477, 495.485, 438.477);
((GeneralPath)shape).lineTo(495.485, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_11_1;
g.setTransform(defaultTransform__0_0_0_0_11_1);
g.setClip(clip__0_0_0_0_11_1);
origAlpha = alpha__0_0_0_0_11;
g.setTransform(defaultTransform__0_0_0_0_11);
g.setClip(clip__0_0_0_0_11);
float alpha__0_0_0_0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_12 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_12 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.485, 452.526);
((GeneralPath)shape).curveTo(496.47998, 452.526, 497.306, 451.698, 497.306, 450.705);
((GeneralPath)shape).curveTo(497.306, 449.71198, 496.48, 448.88397, 495.485, 448.88397);
((GeneralPath)shape).curveTo(494.49298, 448.88397, 493.66397, 449.71198, 493.66397, 450.705);
((GeneralPath)shape).curveTo(493.66397, 451.698, 494.493, 452.526, 495.485, 452.526);
((GeneralPath)shape).lineTo(495.485, 452.526);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_12;
g.setTransform(defaultTransform__0_0_0_0_12);
g.setClip(clip__0_0_0_0_12);
float alpha__0_0_0_0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_13 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_13 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(493.507, 466.577);
((GeneralPath)shape).curveTo(494.50198, 466.577, 495.331, 465.75, 495.331, 464.75598);
((GeneralPath)shape).curveTo(495.331, 463.761, 494.50198, 462.934, 493.507, 462.934);
((GeneralPath)shape).curveTo(492.51398, 462.934, 491.68597, 463.761, 491.68597, 464.75598);
((GeneralPath)shape).curveTo(491.686, 465.75, 492.514, 466.577, 493.507, 466.577);
((GeneralPath)shape).lineTo(493.507, 466.577);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_13;
g.setTransform(defaultTransform__0_0_0_0_13);
g.setClip(clip__0_0_0_0_13);
float alpha__0_0_0_0_14 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_14 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_14 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(501.238, 429.972);
((GeneralPath)shape).curveTo(502.23, 429.972, 503.06, 429.14398, 503.06, 428.15);
((GeneralPath)shape).curveTo(503.06, 427.15698, 502.23, 426.331, 501.238, 426.331);
((GeneralPath)shape).curveTo(500.24402, 426.331, 499.418, 427.15698, 499.418, 428.15);
((GeneralPath)shape).curveTo(499.418, 429.144, 500.244, 429.972, 501.238, 429.972);
((GeneralPath)shape).lineTo(501.238, 429.972);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_14;
g.setTransform(defaultTransform__0_0_0_0_14);
g.setClip(clip__0_0_0_0_14);
float alpha__0_0_0_0_15 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_15 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_15 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(500.614, 480.627);
((GeneralPath)shape).curveTo(501.61002, 480.627, 502.43802, 479.799, 502.43802, 478.80502);
((GeneralPath)shape).curveTo(502.43802, 477.812, 501.61002, 476.984, 500.614, 476.984);
((GeneralPath)shape).curveTo(499.622, 476.984, 498.793, 477.812, 498.793, 478.80502);
((GeneralPath)shape).curveTo(498.793, 479.799, 499.622, 480.627, 500.614, 480.627);
((GeneralPath)shape).lineTo(500.614, 480.627);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_15;
g.setTransform(defaultTransform__0_0_0_0_15);
g.setClip(clip__0_0_0_0_15);
float alpha__0_0_0_0_16 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_16 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_16 is CompositeGraphicsNode
float alpha__0_0_0_0_16_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_16_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_16_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_16_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(449.567, 438.477);
((GeneralPath)shape).curveTo(448.572, 438.477, 447.74298, 437.649, 447.74298, 436.655);
((GeneralPath)shape).curveTo(447.74298, 435.663, 448.572, 434.83398, 449.567, 434.83398);
((GeneralPath)shape).curveTo(450.559, 434.83398, 451.38498, 435.663, 451.38498, 436.655);
((GeneralPath)shape).curveTo(451.386, 437.648, 450.56, 438.477, 449.567, 438.477);
((GeneralPath)shape).lineTo(449.567, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_16_0;
g.setTransform(defaultTransform__0_0_0_0_16_0);
g.setClip(clip__0_0_0_0_16_0);
float alpha__0_0_0_0_16_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_16_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_16_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_16_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(461.072, 438.477);
((GeneralPath)shape).curveTo(460.076, 438.477, 459.248, 437.649, 459.248, 436.655);
((GeneralPath)shape).curveTo(459.248, 435.663, 460.076, 434.83398, 461.072, 434.83398);
((GeneralPath)shape).curveTo(462.064, 434.83398, 462.893, 435.663, 462.893, 436.655);
((GeneralPath)shape).curveTo(462.894, 437.648, 462.064, 438.477, 461.072, 438.477);
((GeneralPath)shape).lineTo(461.072, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_16_1;
g.setTransform(defaultTransform__0_0_0_0_16_1);
g.setClip(clip__0_0_0_0_16_1);
origAlpha = alpha__0_0_0_0_16;
g.setTransform(defaultTransform__0_0_0_0_16);
g.setClip(clip__0_0_0_0_16);
float alpha__0_0_0_0_17 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_17 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_17 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_17 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(461.072, 452.526);
((GeneralPath)shape).curveTo(460.076, 452.526, 459.248, 451.698, 459.248, 450.705);
((GeneralPath)shape).curveTo(459.248, 449.71198, 460.076, 448.88397, 461.072, 448.88397);
((GeneralPath)shape).curveTo(462.064, 448.88397, 462.893, 449.71198, 462.893, 450.705);
((GeneralPath)shape).curveTo(462.893, 451.698, 462.064, 452.526, 461.072, 452.526);
((GeneralPath)shape).lineTo(461.072, 452.526);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_17;
g.setTransform(defaultTransform__0_0_0_0_17);
g.setClip(clip__0_0_0_0_17);
float alpha__0_0_0_0_18 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_18 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_18 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_18 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(463.051, 466.577);
((GeneralPath)shape).curveTo(462.055, 466.577, 461.227, 465.75, 461.227, 464.75598);
((GeneralPath)shape).curveTo(461.227, 463.761, 462.055, 462.934, 463.051, 462.934);
((GeneralPath)shape).curveTo(464.043, 462.934, 464.869, 463.761, 464.869, 464.75598);
((GeneralPath)shape).curveTo(464.869, 465.75, 464.043, 466.577, 463.051, 466.577);
((GeneralPath)shape).lineTo(463.051, 466.577);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_18;
g.setTransform(defaultTransform__0_0_0_0_18);
g.setClip(clip__0_0_0_0_18);
float alpha__0_0_0_0_19 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_19 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_19 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_19 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(455.318, 429.972);
((GeneralPath)shape).curveTo(454.326, 429.972, 453.498, 429.14398, 453.498, 428.15);
((GeneralPath)shape).curveTo(453.498, 427.15698, 454.326, 426.331, 455.318, 426.331);
((GeneralPath)shape).curveTo(456.31198, 426.331, 457.138, 427.15698, 457.138, 428.15);
((GeneralPath)shape).curveTo(457.139, 429.144, 456.313, 429.972, 455.318, 429.972);
((GeneralPath)shape).lineTo(455.318, 429.972);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_19;
g.setTransform(defaultTransform__0_0_0_0_19);
g.setClip(clip__0_0_0_0_19);
float alpha__0_0_0_0_20 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_20 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_20 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_20 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(455.547, 480.627);
((GeneralPath)shape).curveTo(454.552, 480.627, 453.723, 479.80002, 453.723, 478.806);
((GeneralPath)shape).curveTo(453.723, 477.81198, 454.552, 476.985, 455.547, 476.985);
((GeneralPath)shape).curveTo(456.539, 476.985, 457.367, 477.81198, 457.367, 478.806);
((GeneralPath)shape).curveTo(457.367, 479.80002, 456.539, 480.627, 455.547, 480.627);
((GeneralPath)shape).lineTo(455.547, 480.627);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_20;
g.setTransform(defaultTransform__0_0_0_0_20);
g.setClip(clip__0_0_0_0_20);
origAlpha = alpha__0_0_0_0;
g.setTransform(defaultTransform__0_0_0_0);
g.setClip(clip__0_0_0_0);
origAlpha = alpha__0_0_0;
g.setTransform(defaultTransform__0_0_0);
g.setClip(clip__0_0_0);
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
        return 425;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 412;
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

