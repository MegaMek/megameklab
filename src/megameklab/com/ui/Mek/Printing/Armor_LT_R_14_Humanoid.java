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
public class Armor_LT_R_14_Humanoid {
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
// _0_0_0_0_0 is CompositeGraphicsNode
float alpha__0_0_0_0_0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_0_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(465.657, 343.77);
((GeneralPath)shape).curveTo(464.52502, 343.77, 463.66, 342.83798, 463.66, 341.77298);
((GeneralPath)shape).curveTo(463.66, 340.645, 464.525, 339.71198, 465.657, 339.71198);
((GeneralPath)shape).curveTo(466.78802, 339.71198, 467.71802, 340.64398, 467.71802, 341.77298);
((GeneralPath)shape).curveTo(467.718, 342.838, 466.788, 343.77, 465.657, 343.77);
((GeneralPath)shape).lineTo(465.657, 343.77);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_0_0;
g.setTransform(defaultTransform__0_0_0_0_0_0);
g.setClip(clip__0_0_0_0_0_0);
origAlpha = alpha__0_0_0_0_0;
g.setTransform(defaultTransform__0_0_0_0_0);
g.setClip(clip__0_0_0_0_0);
float alpha__0_0_0_0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_1 is CompositeGraphicsNode
float alpha__0_0_0_0_1_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_1_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_1_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(465.657, 349.158);
((GeneralPath)shape).curveTo(464.52502, 349.158, 463.66, 348.22598, 463.66, 347.16098);
((GeneralPath)shape).curveTo(463.66, 346.033, 464.525, 345.09998, 465.657, 345.09998);
((GeneralPath)shape).curveTo(466.78802, 345.09998, 467.71802, 346.03198, 467.71802, 347.16098);
((GeneralPath)shape).curveTo(467.718, 348.226, 466.788, 349.158, 465.657, 349.158);
((GeneralPath)shape).lineTo(465.657, 349.158);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_1_0;
g.setTransform(defaultTransform__0_0_0_0_1_0);
g.setClip(clip__0_0_0_0_1_0);
origAlpha = alpha__0_0_0_0_1;
g.setTransform(defaultTransform__0_0_0_0_1);
g.setClip(clip__0_0_0_0_1);
float alpha__0_0_0_0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_2 is CompositeGraphicsNode
float alpha__0_0_0_0_2_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_2_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_2_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_2_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(465.658, 327.606);
((GeneralPath)shape).curveTo(464.593, 327.606, 463.66, 326.74197, 463.66, 325.612);
((GeneralPath)shape).curveTo(463.66, 324.481, 464.59302, 323.55, 465.658, 323.55);
((GeneralPath)shape).curveTo(466.788, 323.55, 467.718, 324.481, 467.718, 325.612);
((GeneralPath)shape).curveTo(467.718, 326.742, 466.788, 327.606, 465.658, 327.606);
((GeneralPath)shape).lineTo(465.658, 327.606);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_2_0;
g.setTransform(defaultTransform__0_0_0_0_2_0);
g.setClip(clip__0_0_0_0_2_0);
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
((GeneralPath)shape).moveTo(449.872, 343.77);
((GeneralPath)shape).curveTo(448.74002, 343.77, 447.875, 342.83798, 447.875, 341.77298);
((GeneralPath)shape).curveTo(447.875, 340.645, 448.74, 339.71198, 449.872, 339.71198);
((GeneralPath)shape).curveTo(451.00302, 339.71198, 451.933, 340.64398, 451.933, 341.77298);
((GeneralPath)shape).curveTo(451.933, 342.838, 451.003, 343.77, 449.872, 343.77);
((GeneralPath)shape).lineTo(449.872, 343.77);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3_0;
g.setTransform(defaultTransform__0_0_0_0_3_0);
g.setClip(clip__0_0_0_0_3_0);
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
((GeneralPath)shape).moveTo(457.766, 327.606);
((GeneralPath)shape).curveTo(456.702, 327.606, 455.768, 326.74197, 455.768, 325.612);
((GeneralPath)shape).curveTo(455.768, 324.481, 456.702, 323.55, 457.766, 323.55);
((GeneralPath)shape).curveTo(458.897, 323.55, 459.827, 324.481, 459.827, 325.612);
((GeneralPath)shape).curveTo(459.825, 326.742, 458.896, 327.606, 457.766, 327.606);
((GeneralPath)shape).lineTo(457.766, 327.606);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4_0;
g.setTransform(defaultTransform__0_0_0_0_4_0);
g.setClip(clip__0_0_0_0_4_0);
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
((GeneralPath)shape).moveTo(449.873, 327.606);
((GeneralPath)shape).curveTo(448.80798, 327.606, 447.875, 326.74197, 447.875, 325.612);
((GeneralPath)shape).curveTo(447.875, 324.481, 448.808, 323.55, 449.873, 323.55);
((GeneralPath)shape).curveTo(451.003, 323.55, 451.93298, 324.481, 451.93298, 325.612);
((GeneralPath)shape).curveTo(451.933, 326.742, 451.003, 327.606, 449.873, 327.606);
((GeneralPath)shape).lineTo(449.873, 327.606);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_5_0;
g.setTransform(defaultTransform__0_0_0_0_5_0);
g.setClip(clip__0_0_0_0_5_0);
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
((GeneralPath)shape).moveTo(465.658, 332.994);
((GeneralPath)shape).curveTo(464.593, 332.994, 463.66, 332.12997, 463.66, 331.0);
((GeneralPath)shape).curveTo(463.66, 329.869, 464.59302, 328.938, 465.658, 328.938);
((GeneralPath)shape).curveTo(466.788, 328.938, 467.718, 329.869, 467.718, 331.0);
((GeneralPath)shape).curveTo(467.718, 332.129, 466.788, 332.994, 465.658, 332.994);
((GeneralPath)shape).lineTo(465.658, 332.994);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_0;
g.setTransform(defaultTransform__0_0_0_0_6_0);
g.setClip(clip__0_0_0_0_6_0);
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
((GeneralPath)shape).moveTo(457.766, 343.77);
((GeneralPath)shape).curveTo(456.702, 343.77, 455.768, 342.90598, 455.768, 341.776);
((GeneralPath)shape).curveTo(455.768, 340.645, 456.702, 339.714, 457.766, 339.714);
((GeneralPath)shape).curveTo(458.897, 339.714, 459.827, 340.645, 459.827, 341.776);
((GeneralPath)shape).curveTo(459.825, 342.905, 458.896, 343.77, 457.766, 343.77);
((GeneralPath)shape).lineTo(457.766, 343.77);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_7_0;
g.setTransform(defaultTransform__0_0_0_0_7_0);
g.setClip(clip__0_0_0_0_7_0);
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
((GeneralPath)shape).moveTo(457.766, 349.158);
((GeneralPath)shape).curveTo(456.702, 349.158, 455.768, 348.29398, 455.768, 347.164);
((GeneralPath)shape).curveTo(455.768, 346.033, 456.702, 345.102, 457.766, 345.102);
((GeneralPath)shape).curveTo(458.897, 345.102, 459.827, 346.033, 459.827, 347.164);
((GeneralPath)shape).curveTo(459.825, 348.293, 458.896, 349.158, 457.766, 349.158);
((GeneralPath)shape).lineTo(457.766, 349.158);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_8_0;
g.setTransform(defaultTransform__0_0_0_0_8_0);
g.setClip(clip__0_0_0_0_8_0);
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
((GeneralPath)shape).moveTo(449.873, 332.994);
((GeneralPath)shape).curveTo(448.80798, 332.994, 447.875, 332.12997, 447.875, 331.0);
((GeneralPath)shape).curveTo(447.875, 329.869, 448.808, 328.938, 449.873, 328.938);
((GeneralPath)shape).curveTo(451.003, 328.938, 451.93298, 329.869, 451.93298, 331.0);
((GeneralPath)shape).curveTo(451.933, 332.129, 451.003, 332.994, 449.873, 332.994);
((GeneralPath)shape).lineTo(449.873, 332.994);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_9_0;
g.setTransform(defaultTransform__0_0_0_0_9_0);
g.setClip(clip__0_0_0_0_9_0);
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
((GeneralPath)shape).moveTo(465.658, 338.381);
((GeneralPath)shape).curveTo(464.593, 338.381, 463.66, 337.517, 463.66, 336.38702);
((GeneralPath)shape).curveTo(463.66, 335.256, 464.59302, 334.325, 465.658, 334.325);
((GeneralPath)shape).curveTo(466.788, 334.325, 467.718, 335.256, 467.718, 336.38702);
((GeneralPath)shape).curveTo(467.718, 337.517, 466.788, 338.381, 465.658, 338.381);
((GeneralPath)shape).lineTo(465.658, 338.381);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_10_0;
g.setTransform(defaultTransform__0_0_0_0_10_0);
g.setClip(clip__0_0_0_0_10_0);
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
((GeneralPath)shape).moveTo(457.766, 332.994);
((GeneralPath)shape).curveTo(456.702, 332.994, 455.768, 332.12997, 455.768, 331.0);
((GeneralPath)shape).curveTo(455.768, 329.869, 456.702, 328.938, 457.766, 328.938);
((GeneralPath)shape).curveTo(458.897, 328.938, 459.827, 329.869, 459.827, 331.0);
((GeneralPath)shape).curveTo(459.825, 332.13, 458.896, 332.994, 457.766, 332.994);
((GeneralPath)shape).lineTo(457.766, 332.994);
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
((GeneralPath)shape).moveTo(457.766, 338.382);
((GeneralPath)shape).curveTo(456.702, 338.382, 455.768, 337.51797, 455.768, 336.388);
((GeneralPath)shape).curveTo(455.768, 335.257, 456.702, 334.326, 457.766, 334.326);
((GeneralPath)shape).curveTo(458.897, 334.326, 459.827, 335.257, 459.827, 336.388);
((GeneralPath)shape).curveTo(459.825, 337.518, 458.896, 338.382, 457.766, 338.382);
((GeneralPath)shape).lineTo(457.766, 338.382);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_12_0;
g.setTransform(defaultTransform__0_0_0_0_12_0);
g.setClip(clip__0_0_0_0_12_0);
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
((GeneralPath)shape).moveTo(449.873, 338.381);
((GeneralPath)shape).curveTo(448.80798, 338.381, 447.875, 337.517, 447.875, 336.38702);
((GeneralPath)shape).curveTo(447.875, 335.256, 448.808, 334.325, 449.873, 334.325);
((GeneralPath)shape).curveTo(451.003, 334.325, 451.93298, 335.256, 451.93298, 336.38702);
((GeneralPath)shape).curveTo(451.933, 337.517, 451.003, 338.381, 449.873, 338.381);
((GeneralPath)shape).lineTo(449.873, 338.381);
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
        return 448;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 324;
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

