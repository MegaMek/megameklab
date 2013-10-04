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
public class BipedIS30 {
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
((GeneralPath)shape).moveTo(499.722, 503.602);
((GeneralPath)shape).curveTo(498.74298, 503.78998, 497.77298, 503.136, 497.58398, 502.162);
((GeneralPath)shape).curveTo(497.39297, 501.18597, 498.047, 500.21597, 499.025, 500.026);
((GeneralPath)shape).curveTo(500.001, 499.837, 500.968, 500.491, 501.159, 501.465);
((GeneralPath)shape).curveTo(501.348, 502.441, 500.693, 503.412, 499.722, 503.602);
((GeneralPath)shape).lineTo(499.722, 503.602);
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
((GeneralPath)shape).moveTo(502.378, 517.282);
((GeneralPath)shape).curveTo(501.40298, 517.47, 500.43198, 516.816, 500.24, 515.841);
((GeneralPath)shape).curveTo(500.052, 514.866, 500.707, 513.898, 501.68198, 513.708);
((GeneralPath)shape).curveTo(502.65698, 513.517, 503.62698, 514.171, 503.81897, 515.146);
((GeneralPath)shape).curveTo(504.008, 516.122, 503.354, 517.092, 502.378, 517.282);
((GeneralPath)shape).lineTo(502.378, 517.282);
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
((GeneralPath)shape).moveTo(505.037, 530.964);
((GeneralPath)shape).curveTo(504.06097, 531.152, 503.09097, 530.49896, 502.90198, 529.523);
((GeneralPath)shape).curveTo(502.71097, 528.547, 503.365, 527.578, 504.34396, 527.388);
((GeneralPath)shape).curveTo(505.31897, 527.198, 506.28595, 527.852, 506.47696, 528.828);
((GeneralPath)shape).curveTo(506.669, 529.804, 506.015, 530.774, 505.037, 530.964);
((GeneralPath)shape).lineTo(505.037, 530.964);
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
((GeneralPath)shape).moveTo(507.698, 544.645);
((GeneralPath)shape).curveTo(506.722, 544.83405, 505.75198, 544.18, 505.56, 543.205);
((GeneralPath)shape).curveTo(505.372, 542.229, 506.027, 541.26, 507.00198, 541.06903);
((GeneralPath)shape).curveTo(507.977, 540.88, 508.947, 541.533, 509.136, 542.50903);
((GeneralPath)shape).curveTo(509.327, 543.484, 508.674, 544.455, 507.698, 544.645);
((GeneralPath)shape).lineTo(507.698, 544.645);
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
((GeneralPath)shape).moveTo(510.359, 558.325);
((GeneralPath)shape).curveTo(509.38, 558.516, 508.409, 557.862, 508.21802, 556.887);
((GeneralPath)shape).curveTo(508.03003, 555.911, 508.68503, 554.94104, 509.66302, 554.75);
((GeneralPath)shape).curveTo(510.63803, 554.562, 511.60403, 555.215, 511.79602, 556.19);
((GeneralPath)shape).curveTo(511.988, 557.167, 511.331, 558.138, 510.359, 558.325);
((GeneralPath)shape).lineTo(510.359, 558.325);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_5;
g.setTransform(defaultTransform__0_0_0_0_3_5);
g.setClip(clip__0_0_0_0_3_5);
float alpha__0_0_0_0_3_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_3_6 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_3_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_3_6 is ShapeNode
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
origAlpha = alpha__0_0_0_0_3_6;
g.setTransform(defaultTransform__0_0_0_0_3_6);
g.setClip(clip__0_0_0_0_3_6);
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
((GeneralPath)shape).moveTo(455.799, 503.602);
((GeneralPath)shape).curveTo(456.778, 503.78998, 457.74902, 503.136, 457.93903, 502.162);
((GeneralPath)shape).curveTo(458.12802, 501.18597, 457.47403, 500.21597, 456.49603, 500.026);
((GeneralPath)shape).curveTo(455.52203, 499.837, 454.55103, 500.491, 454.36203, 501.465);
((GeneralPath)shape).curveTo(454.173, 502.441, 454.827, 503.412, 455.799, 503.602);
((GeneralPath)shape).lineTo(455.799, 503.602);
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
((GeneralPath)shape).moveTo(453.143, 517.282);
((GeneralPath)shape).curveTo(454.11902, 517.47, 455.08902, 516.816, 455.281, 515.841);
((GeneralPath)shape).curveTo(455.469, 514.866, 454.814, 513.898, 453.83902, 513.708);
((GeneralPath)shape).curveTo(452.864, 513.517, 451.894, 514.171, 451.70203, 515.146);
((GeneralPath)shape).curveTo(451.514, 516.122, 452.168, 517.092, 453.143, 517.282);
((GeneralPath)shape).lineTo(453.143, 517.282);
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
((GeneralPath)shape).moveTo(450.483, 530.964);
((GeneralPath)shape).curveTo(451.46, 531.152, 452.43, 530.49896, 452.618, 529.523);
((GeneralPath)shape).curveTo(452.80902, 528.547, 452.155, 527.578, 451.177, 527.388);
((GeneralPath)shape).curveTo(450.201, 527.198, 449.234, 527.852, 449.043, 528.828);
((GeneralPath)shape).curveTo(448.852, 529.804, 449.506, 530.774, 450.483, 530.964);
((GeneralPath)shape).lineTo(450.483, 530.964);
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
((GeneralPath)shape).moveTo(447.822, 544.645);
((GeneralPath)shape).curveTo(448.79898, 544.83405, 449.76898, 544.18, 449.961, 543.205);
((GeneralPath)shape).curveTo(450.149, 542.229, 449.494, 541.26, 448.518, 541.06903);
((GeneralPath)shape).curveTo(447.543, 540.88, 446.573, 541.533, 446.383, 542.50903);
((GeneralPath)shape).curveTo(446.193, 543.484, 446.848, 544.455, 447.822, 544.645);
((GeneralPath)shape).lineTo(447.822, 544.645);
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
((GeneralPath)shape).moveTo(445.161, 558.325);
((GeneralPath)shape).curveTo(446.14, 558.516, 447.11102, 557.862, 447.30002, 556.887);
((GeneralPath)shape).curveTo(447.49002, 555.911, 446.833, 554.94104, 445.85803, 554.75);
((GeneralPath)shape).curveTo(444.88202, 554.562, 443.91605, 555.215, 443.72403, 556.19);
((GeneralPath)shape).curveTo(443.532, 557.167, 444.189, 558.138, 445.161, 558.325);
((GeneralPath)shape).lineTo(445.161, 558.325);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_5;
g.setTransform(defaultTransform__0_0_0_0_4_5);
g.setClip(clip__0_0_0_0_4_5);
float alpha__0_0_0_0_4_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4_6 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4_6 is ShapeNode
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
origAlpha = alpha__0_0_0_0_4_6;
g.setTransform(defaultTransform__0_0_0_0_4_6);
g.setClip(clip__0_0_0_0_4_6);
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
((GeneralPath)shape).moveTo(526.804, 448.594);
((GeneralPath)shape).curveTo(525.815, 448.681, 524.91504, 447.928, 524.831, 446.938);
((GeneralPath)shape).curveTo(524.744, 445.948, 525.495, 445.052, 526.486, 444.965);
((GeneralPath)shape).curveTo(527.47705, 444.87698, 528.37305, 445.63098, 528.46, 446.621);
((GeneralPath)shape).curveTo(528.545, 447.61, 527.794, 448.508, 526.804, 448.594);
((GeneralPath)shape).lineTo(526.804, 448.594);
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
((GeneralPath)shape).moveTo(528.027, 462.552);
((GeneralPath)shape).curveTo(527.03595, 462.639, 526.139, 461.887, 526.052, 460.897);
((GeneralPath)shape).curveTo(525.964, 459.907, 526.718, 459.01, 527.71, 458.923);
((GeneralPath)shape).curveTo(528.697, 458.836, 529.596, 459.588, 529.681, 460.578);
((GeneralPath)shape).curveTo(529.77, 461.568, 529.016, 462.464, 528.027, 462.552);
((GeneralPath)shape).lineTo(528.027, 462.552);
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
((GeneralPath)shape).moveTo(529.249, 476.51);
((GeneralPath)shape).curveTo(528.257, 476.59702, 527.35803, 475.845, 527.273, 474.855);
((GeneralPath)shape).curveTo(527.18604, 473.86502, 527.937, 472.96902, 528.92804, 472.881);
((GeneralPath)shape).curveTo(529.91907, 472.79602, 530.81604, 473.54602, 530.903, 474.535);
((GeneralPath)shape).curveTo(530.99, 475.528, 530.237, 476.423, 529.249, 476.51);
((GeneralPath)shape).lineTo(529.249, 476.51);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_3;
g.setTransform(defaultTransform__0_0_0_0_5_3);
g.setClip(clip__0_0_0_0_5_3);
float alpha__0_0_0_0_5_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5_4 is ShapeNode
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
origAlpha = alpha__0_0_0_0_5_4;
g.setTransform(defaultTransform__0_0_0_0_5_4);
g.setClip(clip__0_0_0_0_5_4);
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
((GeneralPath)shape).moveTo(429.886, 448.594);
((GeneralPath)shape).curveTo(430.878, 448.681, 431.774, 447.928, 431.862, 446.938);
((GeneralPath)shape).curveTo(431.949, 445.948, 431.195, 445.052, 430.204, 444.965);
((GeneralPath)shape).curveTo(429.216, 444.87698, 428.319, 445.63098, 428.233, 446.621);
((GeneralPath)shape).curveTo(428.145, 447.61, 428.897, 448.508, 429.886, 448.594);
((GeneralPath)shape).lineTo(429.886, 448.594);
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
((GeneralPath)shape).moveTo(428.666, 462.552);
((GeneralPath)shape).curveTo(429.65598, 462.639, 430.55298, 461.887, 430.641, 460.897);
((GeneralPath)shape).curveTo(430.727, 459.907, 429.977, 459.01, 428.985, 458.923);
((GeneralPath)shape).curveTo(427.995, 458.836, 427.098, 459.588, 427.00998, 460.578);
((GeneralPath)shape).curveTo(426.926, 461.568, 427.678, 462.464, 428.666, 462.552);
((GeneralPath)shape).lineTo(428.666, 462.552);
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
((GeneralPath)shape).moveTo(427.443, 476.51);
((GeneralPath)shape).curveTo(428.435, 476.59702, 429.331, 475.845, 429.419, 474.855);
((GeneralPath)shape).curveTo(429.506, 473.86502, 428.752, 472.96902, 427.76102, 472.881);
((GeneralPath)shape).curveTo(426.77002, 472.79602, 425.876, 473.54602, 425.78903, 474.535);
((GeneralPath)shape).curveTo(425.702, 475.528, 426.452, 476.423, 427.443, 476.51);
((GeneralPath)shape).lineTo(427.443, 476.51);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_3;
g.setTransform(defaultTransform__0_0_0_0_6_3);
g.setClip(clip__0_0_0_0_6_3);
float alpha__0_0_0_0_6_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_6_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_6_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_6_4 is ShapeNode
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
origAlpha = alpha__0_0_0_0_6_4;
g.setTransform(defaultTransform__0_0_0_0_6_4);
g.setClip(clip__0_0_0_0_6_4);
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
((GeneralPath)shape).moveTo(473.793, 449.014);
((GeneralPath)shape).curveTo(472.798, 449.014, 471.969, 448.18802, 471.969, 447.194);
((GeneralPath)shape).curveTo(471.969, 446.19998, 472.798, 445.372, 473.793, 445.372);
((GeneralPath)shape).curveTo(474.785, 445.372, 475.614, 446.2, 475.614, 447.194);
((GeneralPath)shape).curveTo(475.614, 448.188, 474.785, 449.014, 473.793, 449.014);
((GeneralPath)shape).lineTo(473.793, 449.014);
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
((GeneralPath)shape).moveTo(482.905, 449.014);
((GeneralPath)shape).curveTo(481.909, 449.014, 481.08, 448.18802, 481.08, 447.194);
((GeneralPath)shape).curveTo(481.08, 446.19998, 481.909, 445.372, 482.905, 445.372);
((GeneralPath)shape).curveTo(483.897, 445.372, 484.725, 446.2, 484.725, 447.194);
((GeneralPath)shape).curveTo(484.725, 448.188, 483.897, 449.014, 482.905, 449.014);
((GeneralPath)shape).lineTo(482.905, 449.014);
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
((GeneralPath)shape).moveTo(473.793, 459.552);
((GeneralPath)shape).curveTo(472.798, 459.552, 471.969, 458.725, 471.969, 457.731);
((GeneralPath)shape).curveTo(471.969, 456.73798, 472.798, 455.90997, 473.793, 455.90997);
((GeneralPath)shape).curveTo(474.785, 455.90997, 475.614, 456.73798, 475.614, 457.731);
((GeneralPath)shape).curveTo(475.614, 458.725, 474.785, 459.552, 473.793, 459.552);
((GeneralPath)shape).lineTo(473.793, 459.552);
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
((GeneralPath)shape).moveTo(482.905, 459.552);
((GeneralPath)shape).curveTo(481.909, 459.552, 481.08, 458.725, 481.08, 457.731);
((GeneralPath)shape).curveTo(481.08, 456.73798, 481.909, 455.90997, 482.905, 455.90997);
((GeneralPath)shape).curveTo(483.897, 455.90997, 484.725, 456.73798, 484.725, 457.731);
((GeneralPath)shape).curveTo(484.726, 458.725, 483.897, 459.552, 482.905, 459.552);
((GeneralPath)shape).lineTo(482.905, 459.552);
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
((GeneralPath)shape).moveTo(473.793, 470.09);
((GeneralPath)shape).curveTo(472.798, 470.09, 471.969, 469.262, 471.969, 468.268);
((GeneralPath)shape).curveTo(471.969, 467.276, 472.798, 466.448, 473.793, 466.448);
((GeneralPath)shape).curveTo(474.785, 466.448, 475.614, 467.276, 475.614, 468.268);
((GeneralPath)shape).curveTo(475.614, 469.262, 474.785, 470.09, 473.793, 470.09);
((GeneralPath)shape).lineTo(473.793, 470.09);
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
((GeneralPath)shape).moveTo(482.905, 470.09);
((GeneralPath)shape).curveTo(481.909, 470.09, 481.08, 469.262, 481.08, 468.268);
((GeneralPath)shape).curveTo(481.08, 467.276, 481.909, 466.448, 482.905, 466.448);
((GeneralPath)shape).curveTo(483.897, 466.448, 484.725, 467.276, 484.725, 468.268);
((GeneralPath)shape).curveTo(484.726, 469.262, 483.897, 470.09, 482.905, 470.09);
((GeneralPath)shape).lineTo(482.905, 470.09);
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
((GeneralPath)shape).moveTo(501.327, 428.625);
((GeneralPath)shape).curveTo(502.322, 428.625, 503.151, 427.797, 503.151, 426.802);
((GeneralPath)shape).curveTo(503.151, 425.81, 502.322, 424.982, 501.327, 424.982);
((GeneralPath)shape).curveTo(500.335, 424.982, 499.507, 425.81, 499.507, 426.802);
((GeneralPath)shape).curveTo(499.507, 427.797, 500.335, 428.625, 501.327, 428.625);
((GeneralPath)shape).lineTo(501.327, 428.625);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_11_0;
g.setTransform(defaultTransform__0_0_0_0_11_0);
g.setClip(clip__0_0_0_0_11_0);
origAlpha = alpha__0_0_0_0_11;
g.setTransform(defaultTransform__0_0_0_0_11);
g.setClip(clip__0_0_0_0_11);
float alpha__0_0_0_0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_12 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_12 is CompositeGraphicsNode
float alpha__0_0_0_0_12_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_12_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_12_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_12_0 is ShapeNode
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
origAlpha = alpha__0_0_0_0_12_0;
g.setTransform(defaultTransform__0_0_0_0_12_0);
g.setClip(clip__0_0_0_0_12_0);
float alpha__0_0_0_0_12_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_12_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_12_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_12_1 is ShapeNode
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
origAlpha = alpha__0_0_0_0_12_1;
g.setTransform(defaultTransform__0_0_0_0_12_1);
g.setClip(clip__0_0_0_0_12_1);
origAlpha = alpha__0_0_0_0_12;
g.setTransform(defaultTransform__0_0_0_0_12);
g.setClip(clip__0_0_0_0_12);
float alpha__0_0_0_0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_13 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_13 is CompositeGraphicsNode
float alpha__0_0_0_0_13_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_13_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_13_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_13_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.485, 449.014);
((GeneralPath)shape).curveTo(496.47998, 449.014, 497.306, 448.186, 497.306, 447.193);
((GeneralPath)shape).curveTo(497.306, 446.19998, 496.48, 445.37198, 495.485, 445.37198);
((GeneralPath)shape).curveTo(494.49298, 445.37198, 493.66397, 446.19998, 493.66397, 447.193);
((GeneralPath)shape).curveTo(493.66397, 448.186, 494.493, 449.014, 495.485, 449.014);
((GeneralPath)shape).lineTo(495.485, 449.014);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_13_0;
g.setTransform(defaultTransform__0_0_0_0_13_0);
g.setClip(clip__0_0_0_0_13_0);
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
((GeneralPath)shape).moveTo(493.905, 459.552);
((GeneralPath)shape).curveTo(494.898, 459.552, 495.726, 458.725, 495.726, 457.731);
((GeneralPath)shape).curveTo(495.726, 456.73798, 494.898, 455.90997, 493.905, 455.90997);
((GeneralPath)shape).curveTo(492.91, 455.90997, 492.08398, 456.73798, 492.08398, 457.731);
((GeneralPath)shape).curveTo(492.084, 458.725, 492.91, 459.552, 493.905, 459.552);
((GeneralPath)shape).lineTo(493.905, 459.552);
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
((GeneralPath)shape).moveTo(491.997, 470.089);
((GeneralPath)shape).curveTo(492.989, 470.089, 493.81802, 469.261, 493.81802, 468.267);
((GeneralPath)shape).curveTo(493.81802, 467.274, 492.989, 466.448, 491.997, 466.448);
((GeneralPath)shape).curveTo(491.002, 466.448, 490.177, 467.274, 490.177, 468.267);
((GeneralPath)shape).curveTo(490.177, 469.261, 491.002, 470.089, 491.997, 470.089);
((GeneralPath)shape).lineTo(491.997, 470.089);
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
// _0_0_0_0_16 is ShapeNode
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
origAlpha = alpha__0_0_0_0_16;
g.setTransform(defaultTransform__0_0_0_0_16);
g.setClip(clip__0_0_0_0_16);
float alpha__0_0_0_0_17 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_17 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_17 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_17 is CompositeGraphicsNode
float alpha__0_0_0_0_17_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_17_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_17_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_17_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(455.228, 428.625);
((GeneralPath)shape).curveTo(454.235, 428.625, 453.40698, 427.797, 453.40698, 426.802);
((GeneralPath)shape).curveTo(453.40698, 425.81, 454.235, 424.982, 455.228, 424.982);
((GeneralPath)shape).curveTo(456.223, 424.982, 457.048, 425.81, 457.048, 426.802);
((GeneralPath)shape).curveTo(457.048, 427.797, 456.223, 428.625, 455.228, 428.625);
((GeneralPath)shape).lineTo(455.228, 428.625);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_17_0;
g.setTransform(defaultTransform__0_0_0_0_17_0);
g.setClip(clip__0_0_0_0_17_0);
origAlpha = alpha__0_0_0_0_17;
g.setTransform(defaultTransform__0_0_0_0_17);
g.setClip(clip__0_0_0_0_17);
float alpha__0_0_0_0_18 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_18 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_18 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_18 is CompositeGraphicsNode
float alpha__0_0_0_0_18_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_18_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_18_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_18_0 is ShapeNode
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
origAlpha = alpha__0_0_0_0_18_0;
g.setTransform(defaultTransform__0_0_0_0_18_0);
g.setClip(clip__0_0_0_0_18_0);
float alpha__0_0_0_0_18_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_18_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_18_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_18_1 is ShapeNode
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
origAlpha = alpha__0_0_0_0_18_1;
g.setTransform(defaultTransform__0_0_0_0_18_1);
g.setClip(clip__0_0_0_0_18_1);
origAlpha = alpha__0_0_0_0_18;
g.setTransform(defaultTransform__0_0_0_0_18);
g.setClip(clip__0_0_0_0_18);
float alpha__0_0_0_0_19 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_19 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_19 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_19 is CompositeGraphicsNode
float alpha__0_0_0_0_19_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_19_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_19_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_19_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(461.072, 449.014);
((GeneralPath)shape).curveTo(460.076, 449.014, 459.248, 448.186, 459.248, 447.193);
((GeneralPath)shape).curveTo(459.248, 446.19998, 460.076, 445.37198, 461.072, 445.37198);
((GeneralPath)shape).curveTo(462.064, 445.37198, 462.893, 446.19998, 462.893, 447.193);
((GeneralPath)shape).curveTo(462.893, 448.186, 462.064, 449.014, 461.072, 449.014);
((GeneralPath)shape).lineTo(461.072, 449.014);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_19_0;
g.setTransform(defaultTransform__0_0_0_0_19_0);
g.setClip(clip__0_0_0_0_19_0);
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
((GeneralPath)shape).moveTo(462.652, 459.552);
((GeneralPath)shape).curveTo(461.659, 459.552, 460.831, 458.725, 460.831, 457.731);
((GeneralPath)shape).curveTo(460.831, 456.73798, 461.659, 455.90997, 462.652, 455.90997);
((GeneralPath)shape).curveTo(463.647, 455.90997, 464.47202, 456.73798, 464.47202, 457.731);
((GeneralPath)shape).curveTo(464.473, 458.725, 463.647, 459.552, 462.652, 459.552);
((GeneralPath)shape).lineTo(462.652, 459.552);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_20;
g.setTransform(defaultTransform__0_0_0_0_20);
g.setClip(clip__0_0_0_0_20);
float alpha__0_0_0_0_21 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_21 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_21 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_21 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(464.56, 470.089);
((GeneralPath)shape).curveTo(463.568, 470.089, 462.74, 469.261, 462.74, 468.267);
((GeneralPath)shape).curveTo(462.74, 467.274, 463.568, 466.448, 464.56, 466.448);
((GeneralPath)shape).curveTo(465.555, 466.448, 466.381, 467.274, 466.381, 468.267);
((GeneralPath)shape).curveTo(466.381, 469.261, 465.555, 470.089, 464.56, 470.089);
((GeneralPath)shape).lineTo(464.56, 470.089);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_21;
g.setTransform(defaultTransform__0_0_0_0_21);
g.setClip(clip__0_0_0_0_21);
float alpha__0_0_0_0_22 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_22 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_22 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_22 is ShapeNode
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
origAlpha = alpha__0_0_0_0_22;
g.setTransform(defaultTransform__0_0_0_0_22);
g.setClip(clip__0_0_0_0_22);
float alpha__0_0_0_0_23 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_23 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_23 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_23 is CompositeGraphicsNode
float alpha__0_0_0_0_23_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_23_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_23_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_23_0 is ShapeNode
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
origAlpha = alpha__0_0_0_0_23_0;
g.setTransform(defaultTransform__0_0_0_0_23_0);
g.setClip(clip__0_0_0_0_23_0);
float alpha__0_0_0_0_23_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_23_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_23_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_23_1 is ShapeNode
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
origAlpha = alpha__0_0_0_0_23_1;
g.setTransform(defaultTransform__0_0_0_0_23_1);
g.setClip(clip__0_0_0_0_23_1);
origAlpha = alpha__0_0_0_0_23;
g.setTransform(defaultTransform__0_0_0_0_23);
g.setClip(clip__0_0_0_0_23);
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

