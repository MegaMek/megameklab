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
public class Naval_IS_3 {
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
((GeneralPath)shape).moveTo(491.682, 157.619);
((GeneralPath)shape).curveTo(491.682, 158.932, 490.61902, 159.99701, 489.30402, 159.99701);
((GeneralPath)shape).curveTo(487.989, 159.99701, 486.92203, 158.932, 486.92203, 157.619);
((GeneralPath)shape).curveTo(486.92203, 156.304, 487.98804, 155.239, 489.30402, 155.239);
((GeneralPath)shape).curveTo(490.619, 155.24, 491.682, 156.304, 491.682, 157.619);
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
((GeneralPath)shape).moveTo(491.682, 157.619);
((GeneralPath)shape).curveTo(491.682, 158.932, 490.61902, 159.99701, 489.303, 159.99701);
((GeneralPath)shape).curveTo(487.989, 159.99701, 486.922, 158.932, 486.922, 157.619);
((GeneralPath)shape).curveTo(486.922, 156.304, 487.987, 155.24, 489.303, 155.24);
((GeneralPath)shape).curveTo(490.61902, 155.24, 491.682, 156.304, 491.682, 157.619);
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
((GeneralPath)shape).moveTo(462.556, 157.619);
((GeneralPath)shape).curveTo(462.556, 158.933, 461.492, 159.99901, 460.177, 159.99901);
((GeneralPath)shape).curveTo(458.864, 159.99901, 457.798, 158.93501, 457.798, 157.619);
((GeneralPath)shape).curveTo(457.798, 156.30501, 458.862, 155.239, 460.177, 155.239);
((GeneralPath)shape).curveTo(461.491, 155.24, 462.556, 156.305, 462.556, 157.619);
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
((GeneralPath)shape).moveTo(462.556, 157.619);
((GeneralPath)shape).curveTo(462.556, 158.933, 461.492, 160.0, 460.176, 160.0);
((GeneralPath)shape).curveTo(458.863, 160.0, 457.796, 158.935, 457.796, 157.619);
((GeneralPath)shape).curveTo(457.796, 156.30501, 458.862, 155.24, 460.176, 155.24);
((GeneralPath)shape).curveTo(461.491, 155.24, 462.556, 156.305, 462.556, 157.619);
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
((GeneralPath)shape).moveTo(521.188, 157.619);
((GeneralPath)shape).curveTo(521.188, 158.933, 520.125, 159.99901, 518.81, 159.99901);
((GeneralPath)shape).curveTo(517.497, 159.99901, 516.43, 158.93501, 516.43, 157.619);
((GeneralPath)shape).curveTo(516.43, 156.30501, 517.496, 155.239, 518.81, 155.239);
((GeneralPath)shape).curveTo(520.127, 155.24, 521.188, 156.305, 521.188, 157.619);
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
((GeneralPath)shape).moveTo(521.188, 157.619);
((GeneralPath)shape).curveTo(521.188, 158.933, 520.125, 160.0, 518.81, 160.0);
((GeneralPath)shape).curveTo(517.495, 160.0, 516.43, 158.935, 516.43, 157.619);
((GeneralPath)shape).curveTo(516.43, 156.30501, 517.494, 155.24, 518.81, 155.24);
((GeneralPath)shape).curveTo(520.127, 155.24, 521.188, 156.305, 521.188, 157.619);
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
((GeneralPath)shape).moveTo(531.914, 157.619);
((GeneralPath)shape).curveTo(531.914, 158.933, 530.85, 159.99901, 529.535, 159.99901);
((GeneralPath)shape).curveTo(528.222, 159.99901, 527.15497, 158.93501, 527.15497, 157.619);
((GeneralPath)shape).curveTo(527.15497, 156.30501, 528.22095, 155.239, 529.535, 155.239);
((GeneralPath)shape).curveTo(530.849, 155.24, 531.914, 156.305, 531.914, 157.619);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.42, 532.922);
((GeneralPath)shape).curveTo(491.42, 531.609, 490.35602, 530.544, 489.04, 530.544);
((GeneralPath)shape).curveTo(487.726, 530.544, 486.659, 531.60803, 486.659, 532.922);
((GeneralPath)shape).curveTo(486.659, 534.23596, 487.725, 535.301, 489.04, 535.301);
((GeneralPath)shape).curveTo(490.355, 535.301, 491.42, 534.236, 491.42, 532.922);
g.setPaint(paint);
g.fill(shape);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.42, 532.922);
((GeneralPath)shape).curveTo(491.42, 531.609, 490.35602, 530.544, 489.04, 530.544);
((GeneralPath)shape).curveTo(487.726, 530.544, 486.659, 531.60803, 486.659, 532.922);
((GeneralPath)shape).curveTo(486.659, 534.23596, 487.725, 535.302, 489.04, 535.302);
((GeneralPath)shape).curveTo(490.355, 535.302, 491.42, 534.236, 491.42, 532.922);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(466.993, 532.922);
((GeneralPath)shape).curveTo(466.993, 531.607, 465.92902, 530.544, 464.613, 530.544);
((GeneralPath)shape).curveTo(463.30002, 530.544, 462.234, 531.607, 462.234, 532.922);
((GeneralPath)shape).curveTo(462.234, 534.235, 463.299, 535.301, 464.613, 535.301);
((GeneralPath)shape).curveTo(465.928, 535.301, 466.993, 534.236, 466.993, 532.922);
g.setPaint(paint);
g.fill(shape);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(466.993, 532.922);
((GeneralPath)shape).curveTo(466.993, 531.607, 465.928, 530.544, 464.613, 530.544);
((GeneralPath)shape).curveTo(463.30002, 530.544, 462.234, 531.607, 462.234, 532.922);
((GeneralPath)shape).curveTo(462.234, 534.235, 463.298, 535.302, 464.613, 535.302);
((GeneralPath)shape).curveTo(465.928, 535.302, 466.993, 534.236, 466.993, 532.922);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(516.755, 532.922);
((GeneralPath)shape).curveTo(516.755, 531.607, 515.689, 530.544, 514.375, 530.544);
((GeneralPath)shape).curveTo(513.061, 530.544, 511.995, 531.607, 511.995, 532.922);
((GeneralPath)shape).curveTo(511.995, 534.235, 513.059, 535.301, 514.375, 535.301);
((GeneralPath)shape).curveTo(515.688, 535.301, 516.755, 534.236, 516.755, 532.922);
g.setPaint(paint);
g.fill(shape);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.484f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(516.753, 532.922);
((GeneralPath)shape).curveTo(516.753, 531.607, 515.68896, 530.544, 514.375, 530.544);
((GeneralPath)shape).curveTo(513.061, 530.544, 511.995, 531.607, 511.995, 532.922);
((GeneralPath)shape).curveTo(511.995, 534.235, 513.059, 535.302, 514.375, 535.302);
((GeneralPath)shape).curveTo(515.688, 535.302, 516.753, 534.236, 516.753, 532.922);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 437.086);
((GeneralPath)shape).curveTo(467.05, 437.086, 465.724, 438.409, 465.724, 440.045);
((GeneralPath)shape).curveTo(465.724, 441.68103, 467.05, 443.00702, 468.684, 443.00702);
((GeneralPath)shape).curveTo(470.318, 443.00702, 471.645, 441.68103, 471.645, 440.045);
((GeneralPath)shape).curveTo(471.645, 438.409, 470.317, 437.086, 468.684, 437.086);
g.setPaint(paint);
g.fill(shape);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 437.087);
((GeneralPath)shape).curveTo(467.04898, 437.087, 465.723, 438.409, 465.723, 440.045);
((GeneralPath)shape).curveTo(465.723, 441.68103, 467.04898, 443.00702, 468.684, 443.00702);
((GeneralPath)shape).curveTo(470.317, 443.00702, 471.64398, 441.68103, 471.64398, 440.045);
((GeneralPath)shape).curveTo(471.64398, 438.409, 470.316, 437.087, 468.684, 437.087);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 437.086);
((GeneralPath)shape).curveTo(509.04898, 437.086, 507.724, 438.409, 507.724, 440.045);
((GeneralPath)shape).curveTo(507.724, 441.68103, 509.049, 443.00702, 510.684, 443.00702);
((GeneralPath)shape).curveTo(512.318, 443.00702, 513.645, 441.68103, 513.645, 440.045);
((GeneralPath)shape).curveTo(513.645, 438.409, 512.317, 437.086, 510.684, 437.086);
g.setPaint(paint);
g.fill(shape);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 437.087);
((GeneralPath)shape).curveTo(509.04898, 437.087, 507.723, 438.409, 507.723, 440.045);
((GeneralPath)shape).curveTo(507.723, 441.68103, 509.04898, 443.00702, 510.684, 443.00702);
((GeneralPath)shape).curveTo(512.317, 443.00702, 513.644, 441.68103, 513.644, 440.045);
((GeneralPath)shape).curveTo(513.644, 438.409, 512.316, 437.087, 510.684, 437.087);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 207.48);
((GeneralPath)shape).curveTo(467.05, 207.48, 465.724, 208.804, 465.724, 210.439);
((GeneralPath)shape).curveTo(465.724, 212.073, 467.05, 213.399, 468.684, 213.399);
((GeneralPath)shape).curveTo(470.318, 213.399, 471.645, 212.073, 471.645, 210.439);
((GeneralPath)shape).curveTo(471.645, 208.804, 470.317, 207.48, 468.684, 207.48);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_17;
g.setTransform(defaultTransform__0_17);
g.setClip(clip__0_17);
float alpha__0_18 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_18 = g.getClip();
AffineTransform defaultTransform__0_18 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_18 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 207.48);
((GeneralPath)shape).curveTo(467.04898, 207.48, 465.723, 208.805, 465.723, 210.439);
((GeneralPath)shape).curveTo(465.723, 212.07399, 467.04898, 213.4, 468.684, 213.4);
((GeneralPath)shape).curveTo(470.317, 213.4, 471.64398, 212.07399, 471.64398, 210.439);
((GeneralPath)shape).curveTo(471.644, 208.805, 470.316, 207.48, 468.684, 207.48);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_18;
g.setTransform(defaultTransform__0_18);
g.setClip(clip__0_18);
float alpha__0_19 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_19 = g.getClip();
AffineTransform defaultTransform__0_19 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_19 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 227.809);
((GeneralPath)shape).curveTo(467.05, 227.809, 465.724, 229.138, 465.724, 230.772);
((GeneralPath)shape).curveTo(465.724, 232.409, 467.05, 233.73201, 468.684, 233.73201);
((GeneralPath)shape).curveTo(470.318, 233.73201, 471.645, 232.40701, 471.645, 230.772);
((GeneralPath)shape).curveTo(471.645, 229.138, 470.317, 227.809, 468.684, 227.809);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_19;
g.setTransform(defaultTransform__0_19);
g.setClip(clip__0_19);
float alpha__0_20 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_20 = g.getClip();
AffineTransform defaultTransform__0_20 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_20 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(468.684, 227.809);
((GeneralPath)shape).curveTo(467.04898, 227.809, 465.723, 229.138, 465.723, 230.772);
((GeneralPath)shape).curveTo(465.723, 232.409, 467.04898, 233.733, 468.684, 233.733);
((GeneralPath)shape).curveTo(470.317, 233.733, 471.64398, 232.407, 471.64398, 230.772);
((GeneralPath)shape).curveTo(471.644, 229.138, 470.316, 227.809, 468.684, 227.809);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_20;
g.setTransform(defaultTransform__0_20);
g.setClip(clip__0_20);
float alpha__0_21 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_21 = g.getClip();
AffineTransform defaultTransform__0_21 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_21 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 207.48);
((GeneralPath)shape).curveTo(509.04898, 207.48, 507.724, 208.804, 507.724, 210.439);
((GeneralPath)shape).curveTo(507.724, 212.073, 509.049, 213.399, 510.684, 213.399);
((GeneralPath)shape).curveTo(512.318, 213.399, 513.645, 212.073, 513.645, 210.439);
((GeneralPath)shape).curveTo(513.645, 208.804, 512.317, 207.48, 510.684, 207.48);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_21;
g.setTransform(defaultTransform__0_21);
g.setClip(clip__0_21);
float alpha__0_22 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_22 = g.getClip();
AffineTransform defaultTransform__0_22 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_22 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 207.48);
((GeneralPath)shape).curveTo(509.04898, 207.48, 507.723, 208.805, 507.723, 210.439);
((GeneralPath)shape).curveTo(507.723, 212.07399, 509.04898, 213.4, 510.684, 213.4);
((GeneralPath)shape).curveTo(512.317, 213.4, 513.644, 212.07399, 513.644, 210.439);
((GeneralPath)shape).curveTo(513.644, 208.805, 512.316, 207.48, 510.684, 207.48);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_22;
g.setTransform(defaultTransform__0_22);
g.setClip(clip__0_22);
float alpha__0_23 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_23 = g.getClip();
AffineTransform defaultTransform__0_23 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_23 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 227.809);
((GeneralPath)shape).curveTo(509.04898, 227.809, 507.724, 229.138, 507.724, 230.772);
((GeneralPath)shape).curveTo(507.724, 232.409, 509.049, 233.73201, 510.684, 233.73201);
((GeneralPath)shape).curveTo(512.318, 233.73201, 513.645, 232.40701, 513.645, 230.772);
((GeneralPath)shape).curveTo(513.645, 229.138, 512.317, 227.809, 510.684, 227.809);
g.setPaint(paint);
g.fill(shape);
origAlpha = alpha__0_23;
g.setTransform(defaultTransform__0_23);
g.setClip(clip__0_23);
float alpha__0_24 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_24 = g.getClip();
AffineTransform defaultTransform__0_24 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_24 is ShapeNode
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(510.684, 227.809);
((GeneralPath)shape).curveTo(509.04898, 227.809, 507.723, 229.138, 507.723, 230.772);
((GeneralPath)shape).curveTo(507.723, 232.409, 509.04898, 233.733, 510.684, 233.733);
((GeneralPath)shape).curveTo(512.317, 233.733, 513.644, 232.407, 513.644, 230.772);
((GeneralPath)shape).curveTo(513.644, 229.138, 512.316, 227.809, 510.684, 227.809);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_24;
g.setTransform(defaultTransform__0_24);
g.setClip(clip__0_24);
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
        return 458;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 155;
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

