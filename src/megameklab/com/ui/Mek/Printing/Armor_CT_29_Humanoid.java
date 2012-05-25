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
public class Armor_CT_29_Humanoid {
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
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(2.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0;
g.setTransform(defaultTransform__0_0);
g.setClip(clip__0_0);
float alpha__0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_1 = g.getClip();
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1 is CompositeGraphicsNode
float alpha__0_1_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_1_0 = g.getClip();
AffineTransform defaultTransform__0_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.648, 161.203);
((GeneralPath)shape).curveTo(482.039, 161.203, 483.06, 160.104, 483.06, 158.78801);
((GeneralPath)shape).curveTo(483.06, 157.473, 482.039, 156.37401, 480.648, 156.37401);
((GeneralPath)shape).curveTo(479.33102, 156.37401, 478.234, 157.473, 478.234, 158.78801);
((GeneralPath)shape).curveTo(478.234, 160.104, 479.331, 161.203, 480.648, 161.203);
((GeneralPath)shape).lineTo(480.648, 161.203);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_1_0;
g.setTransform(defaultTransform__0_1_0);
g.setClip(clip__0_1_0);
float alpha__0_1_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_1_1 = g.getClip();
AffineTransform defaultTransform__0_1_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.223, 161.203);
((GeneralPath)shape).curveTo(503.611, 161.203, 504.63498, 160.104, 504.63498, 158.78801);
((GeneralPath)shape).curveTo(504.63498, 157.473, 503.611, 156.37401, 502.223, 156.37401);
((GeneralPath)shape).curveTo(500.906, 156.37401, 499.806, 157.473, 499.806, 158.78801);
((GeneralPath)shape).curveTo(499.806, 160.104, 500.905, 161.203, 502.223, 161.203);
((GeneralPath)shape).lineTo(502.223, 161.203);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_1_1;
g.setTransform(defaultTransform__0_1_1);
g.setClip(clip__0_1_1);
float alpha__0_1_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_1_2 = g.getClip();
AffineTransform defaultTransform__0_1_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.436, 161.203);
((GeneralPath)shape).curveTo(492.824, 161.203, 493.848, 160.104, 493.848, 158.78801);
((GeneralPath)shape).curveTo(493.848, 157.473, 492.824, 156.37401, 491.436, 156.37401);
((GeneralPath)shape).curveTo(490.11902, 156.37401, 489.019, 157.473, 489.019, 158.78801);
((GeneralPath)shape).curveTo(489.019, 160.104, 490.118, 161.203, 491.436, 161.203);
((GeneralPath)shape).lineTo(491.436, 161.203);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_1_2;
g.setTransform(defaultTransform__0_1_2);
g.setClip(clip__0_1_2);
origAlpha = alpha__0_1;
g.setTransform(defaultTransform__0_1);
g.setClip(clip__0_1);
float alpha__0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_2 = g.getClip();
AffineTransform defaultTransform__0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2 is CompositeGraphicsNode
float alpha__0_2_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_2_0 = g.getClip();
AffineTransform defaultTransform__0_2_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.648, 169.456);
((GeneralPath)shape).curveTo(482.039, 169.456, 483.06, 168.357, 483.06, 167.041);
((GeneralPath)shape).curveTo(483.06, 165.725, 482.039, 164.627, 480.648, 164.627);
((GeneralPath)shape).curveTo(479.33102, 164.627, 478.234, 165.725, 478.234, 167.041);
((GeneralPath)shape).curveTo(478.234, 168.357, 479.331, 169.456, 480.648, 169.456);
((GeneralPath)shape).lineTo(480.648, 169.456);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_2_0;
g.setTransform(defaultTransform__0_2_0);
g.setClip(clip__0_2_0);
float alpha__0_2_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_2_1 = g.getClip();
AffineTransform defaultTransform__0_2_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.223, 169.456);
((GeneralPath)shape).curveTo(503.611, 169.456, 504.63498, 168.357, 504.63498, 167.041);
((GeneralPath)shape).curveTo(504.63498, 165.725, 503.611, 164.627, 502.223, 164.627);
((GeneralPath)shape).curveTo(500.906, 164.627, 499.806, 165.725, 499.806, 167.041);
((GeneralPath)shape).curveTo(499.806, 168.357, 500.905, 169.456, 502.223, 169.456);
((GeneralPath)shape).lineTo(502.223, 169.456);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_2_1;
g.setTransform(defaultTransform__0_2_1);
g.setClip(clip__0_2_1);
float alpha__0_2_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_2_2 = g.getClip();
AffineTransform defaultTransform__0_2_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.436, 169.456);
((GeneralPath)shape).curveTo(492.824, 169.456, 493.848, 168.357, 493.848, 167.041);
((GeneralPath)shape).curveTo(493.848, 165.725, 492.824, 164.627, 491.436, 164.627);
((GeneralPath)shape).curveTo(490.11902, 164.627, 489.019, 165.725, 489.019, 167.041);
((GeneralPath)shape).curveTo(489.019, 168.357, 490.118, 169.456, 491.436, 169.456);
((GeneralPath)shape).lineTo(491.436, 169.456);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_2_2;
g.setTransform(defaultTransform__0_2_2);
g.setClip(clip__0_2_2);
origAlpha = alpha__0_2;
g.setTransform(defaultTransform__0_2);
g.setClip(clip__0_2);
float alpha__0_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_3 = g.getClip();
AffineTransform defaultTransform__0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3 is CompositeGraphicsNode
float alpha__0_3_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_3_0 = g.getClip();
AffineTransform defaultTransform__0_3_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.648, 177.709);
((GeneralPath)shape).curveTo(482.039, 177.709, 483.06, 176.61, 483.06, 175.294);
((GeneralPath)shape).curveTo(483.06, 173.97801, 482.039, 172.88, 480.648, 172.88);
((GeneralPath)shape).curveTo(479.33102, 172.88, 478.234, 173.97801, 478.234, 175.294);
((GeneralPath)shape).curveTo(478.234, 176.61, 479.331, 177.709, 480.648, 177.709);
((GeneralPath)shape).lineTo(480.648, 177.709);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3_0;
g.setTransform(defaultTransform__0_3_0);
g.setClip(clip__0_3_0);
float alpha__0_3_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_3_1 = g.getClip();
AffineTransform defaultTransform__0_3_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.223, 177.709);
((GeneralPath)shape).curveTo(503.611, 177.709, 504.63498, 176.61, 504.63498, 175.294);
((GeneralPath)shape).curveTo(504.63498, 173.97801, 503.611, 172.88, 502.223, 172.88);
((GeneralPath)shape).curveTo(500.906, 172.88, 499.806, 173.97801, 499.806, 175.294);
((GeneralPath)shape).curveTo(499.806, 176.61, 500.905, 177.709, 502.223, 177.709);
((GeneralPath)shape).lineTo(502.223, 177.709);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3_1;
g.setTransform(defaultTransform__0_3_1);
g.setClip(clip__0_3_1);
float alpha__0_3_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_3_2 = g.getClip();
AffineTransform defaultTransform__0_3_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.436, 177.709);
((GeneralPath)shape).curveTo(492.824, 177.709, 493.848, 176.61, 493.848, 175.294);
((GeneralPath)shape).curveTo(493.848, 173.97801, 492.824, 172.88, 491.436, 172.88);
((GeneralPath)shape).curveTo(490.11902, 172.88, 489.019, 173.97801, 489.019, 175.294);
((GeneralPath)shape).curveTo(489.019, 176.61, 490.118, 177.709, 491.436, 177.709);
((GeneralPath)shape).lineTo(491.436, 177.709);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3_2;
g.setTransform(defaultTransform__0_3_2);
g.setClip(clip__0_3_2);
origAlpha = alpha__0_3;
g.setTransform(defaultTransform__0_3);
g.setClip(clip__0_3);
float alpha__0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_4 = g.getClip();
AffineTransform defaultTransform__0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4 is CompositeGraphicsNode
float alpha__0_4_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_4_0 = g.getClip();
AffineTransform defaultTransform__0_4_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 144.696);
((GeneralPath)shape).curveTo(482.039, 144.696, 483.134, 143.59799, 483.134, 142.281);
((GeneralPath)shape).curveTo(483.134, 140.89, 482.038, 139.86601, 480.647, 139.86601);
((GeneralPath)shape).curveTo(479.331, 139.86601, 478.234, 140.89001, 478.234, 142.281);
((GeneralPath)shape).curveTo(478.234, 143.599, 479.331, 144.696, 480.647, 144.696);
((GeneralPath)shape).lineTo(480.647, 144.696);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_4_0;
g.setTransform(defaultTransform__0_4_0);
g.setClip(clip__0_4_0);
float alpha__0_4_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_4_1 = g.getClip();
AffineTransform defaultTransform__0_4_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 144.696);
((GeneralPath)shape).curveTo(503.611, 144.696, 504.71, 143.59799, 504.71, 142.281);
((GeneralPath)shape).curveTo(504.71, 140.89, 503.61, 139.86601, 502.219, 139.86601);
((GeneralPath)shape).curveTo(500.90298, 139.86601, 499.806, 140.89001, 499.806, 142.281);
((GeneralPath)shape).curveTo(499.806, 143.599, 500.902, 144.696, 502.219, 144.696);
((GeneralPath)shape).lineTo(502.219, 144.696);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_4_1;
g.setTransform(defaultTransform__0_4_1);
g.setClip(clip__0_4_1);
float alpha__0_4_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_4_2 = g.getClip();
AffineTransform defaultTransform__0_4_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 144.696);
((GeneralPath)shape).curveTo(492.824, 144.696, 493.923, 143.59799, 493.923, 142.281);
((GeneralPath)shape).curveTo(493.923, 140.89, 492.823, 139.86601, 491.435, 139.86601);
((GeneralPath)shape).curveTo(490.119, 139.86601, 489.019, 140.89001, 489.019, 142.281);
((GeneralPath)shape).curveTo(489.019, 143.599, 490.118, 144.696, 491.435, 144.696);
((GeneralPath)shape).lineTo(491.435, 144.696);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_4_2;
g.setTransform(defaultTransform__0_4_2);
g.setClip(clip__0_4_2);
origAlpha = alpha__0_4;
g.setTransform(defaultTransform__0_4);
g.setClip(clip__0_4);
float alpha__0_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_5 = g.getClip();
AffineTransform defaultTransform__0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5 is CompositeGraphicsNode
float alpha__0_5_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_5_0 = g.getClip();
AffineTransform defaultTransform__0_5_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 119.937);
((GeneralPath)shape).curveTo(482.039, 119.937, 483.134, 118.839, 483.134, 117.521996);
((GeneralPath)shape).curveTo(483.134, 116.131, 482.038, 115.107994, 480.647, 115.107994);
((GeneralPath)shape).curveTo(479.331, 115.107994, 478.234, 116.131, 478.234, 117.521996);
((GeneralPath)shape).curveTo(478.234, 118.839, 479.331, 119.937, 480.647, 119.937);
((GeneralPath)shape).lineTo(480.647, 119.937);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_5_0;
g.setTransform(defaultTransform__0_5_0);
g.setClip(clip__0_5_0);
float alpha__0_5_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_5_1 = g.getClip();
AffineTransform defaultTransform__0_5_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 119.937);
((GeneralPath)shape).curveTo(503.611, 119.937, 504.71, 118.839, 504.71, 117.521996);
((GeneralPath)shape).curveTo(504.71, 116.131, 503.61, 115.107994, 502.219, 115.107994);
((GeneralPath)shape).curveTo(500.90298, 115.107994, 499.806, 116.131, 499.806, 117.521996);
((GeneralPath)shape).curveTo(499.806, 118.839, 500.902, 119.937, 502.219, 119.937);
((GeneralPath)shape).lineTo(502.219, 119.937);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_5_1;
g.setTransform(defaultTransform__0_5_1);
g.setClip(clip__0_5_1);
float alpha__0_5_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_5_2 = g.getClip();
AffineTransform defaultTransform__0_5_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 119.937);
((GeneralPath)shape).curveTo(492.824, 119.937, 493.923, 118.839, 493.923, 117.521996);
((GeneralPath)shape).curveTo(493.923, 116.131, 492.823, 115.107994, 491.435, 115.107994);
((GeneralPath)shape).curveTo(490.119, 115.107994, 489.019, 116.131, 489.019, 117.521996);
((GeneralPath)shape).curveTo(489.019, 118.839, 490.118, 119.937, 491.435, 119.937);
((GeneralPath)shape).lineTo(491.435, 119.937);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_5_2;
g.setTransform(defaultTransform__0_5_2);
g.setClip(clip__0_5_2);
origAlpha = alpha__0_5;
g.setTransform(defaultTransform__0_5);
g.setClip(clip__0_5);
float alpha__0_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_6 = g.getClip();
AffineTransform defaultTransform__0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6 is CompositeGraphicsNode
float alpha__0_6_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_6_0 = g.getClip();
AffineTransform defaultTransform__0_6_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 111.684);
((GeneralPath)shape).curveTo(482.039, 111.684, 483.134, 110.586, 483.134, 109.269);
((GeneralPath)shape).curveTo(483.134, 107.878, 482.038, 106.854996, 480.647, 106.854996);
((GeneralPath)shape).curveTo(479.331, 106.854996, 478.234, 107.878, 478.234, 109.269);
((GeneralPath)shape).curveTo(478.234, 110.586, 479.331, 111.684, 480.647, 111.684);
((GeneralPath)shape).lineTo(480.647, 111.684);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_6_0;
g.setTransform(defaultTransform__0_6_0);
g.setClip(clip__0_6_0);
float alpha__0_6_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_6_1 = g.getClip();
AffineTransform defaultTransform__0_6_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 111.684);
((GeneralPath)shape).curveTo(503.611, 111.684, 504.71, 110.586, 504.71, 109.269);
((GeneralPath)shape).curveTo(504.71, 107.878, 503.61, 106.854996, 502.219, 106.854996);
((GeneralPath)shape).curveTo(500.90298, 106.854996, 499.806, 107.878, 499.806, 109.269);
((GeneralPath)shape).curveTo(499.806, 110.586, 500.902, 111.684, 502.219, 111.684);
((GeneralPath)shape).lineTo(502.219, 111.684);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_6_1;
g.setTransform(defaultTransform__0_6_1);
g.setClip(clip__0_6_1);
float alpha__0_6_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_6_2 = g.getClip();
AffineTransform defaultTransform__0_6_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 111.684);
((GeneralPath)shape).curveTo(492.824, 111.684, 493.923, 110.586, 493.923, 109.269);
((GeneralPath)shape).curveTo(493.923, 107.878, 492.823, 106.854996, 491.435, 106.854996);
((GeneralPath)shape).curveTo(490.119, 106.854996, 489.019, 107.878, 489.019, 109.269);
((GeneralPath)shape).curveTo(489.019, 110.586, 490.118, 111.684, 491.435, 111.684);
((GeneralPath)shape).lineTo(491.435, 111.684);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_6_2;
g.setTransform(defaultTransform__0_6_2);
g.setClip(clip__0_6_2);
origAlpha = alpha__0_6;
g.setTransform(defaultTransform__0_6);
g.setClip(clip__0_6);
float alpha__0_7 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_7 = g.getClip();
AffineTransform defaultTransform__0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7 is CompositeGraphicsNode
float alpha__0_7_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_7_0 = g.getClip();
AffineTransform defaultTransform__0_7_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 128.19);
((GeneralPath)shape).curveTo(482.039, 128.19, 483.134, 127.092, 483.134, 125.775);
((GeneralPath)shape).curveTo(483.134, 124.384, 482.038, 123.361, 480.647, 123.361);
((GeneralPath)shape).curveTo(479.331, 123.361, 478.234, 124.384, 478.234, 125.775);
((GeneralPath)shape).curveTo(478.234, 127.093, 479.331, 128.19, 480.647, 128.19);
((GeneralPath)shape).lineTo(480.647, 128.19);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_7_0;
g.setTransform(defaultTransform__0_7_0);
g.setClip(clip__0_7_0);
float alpha__0_7_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_7_1 = g.getClip();
AffineTransform defaultTransform__0_7_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 128.19);
((GeneralPath)shape).curveTo(503.611, 128.19, 504.71, 127.092, 504.71, 125.775);
((GeneralPath)shape).curveTo(504.71, 124.384, 503.61, 123.361, 502.219, 123.361);
((GeneralPath)shape).curveTo(500.90298, 123.361, 499.806, 124.384, 499.806, 125.775);
((GeneralPath)shape).curveTo(499.806, 127.093, 500.902, 128.19, 502.219, 128.19);
((GeneralPath)shape).lineTo(502.219, 128.19);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_7_1;
g.setTransform(defaultTransform__0_7_1);
g.setClip(clip__0_7_1);
float alpha__0_7_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_7_2 = g.getClip();
AffineTransform defaultTransform__0_7_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 128.19);
((GeneralPath)shape).curveTo(492.824, 128.19, 493.923, 127.092, 493.923, 125.775);
((GeneralPath)shape).curveTo(493.923, 124.384, 492.823, 123.361, 491.435, 123.361);
((GeneralPath)shape).curveTo(490.119, 123.361, 489.019, 124.384, 489.019, 125.775);
((GeneralPath)shape).curveTo(489.019, 127.093, 490.118, 128.19, 491.435, 128.19);
((GeneralPath)shape).lineTo(491.435, 128.19);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_7_2;
g.setTransform(defaultTransform__0_7_2);
g.setClip(clip__0_7_2);
origAlpha = alpha__0_7;
g.setTransform(defaultTransform__0_7);
g.setClip(clip__0_7);
float alpha__0_8 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_8 = g.getClip();
AffineTransform defaultTransform__0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8 is CompositeGraphicsNode
float alpha__0_8_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_8_0 = g.getClip();
AffineTransform defaultTransform__0_8_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 136.443);
((GeneralPath)shape).curveTo(482.039, 136.443, 483.134, 135.34499, 483.134, 134.028);
((GeneralPath)shape).curveTo(483.134, 132.637, 482.038, 131.613, 480.647, 131.613);
((GeneralPath)shape).curveTo(479.331, 131.613, 478.234, 132.63701, 478.234, 134.028);
((GeneralPath)shape).curveTo(478.234, 135.346, 479.331, 136.443, 480.647, 136.443);
((GeneralPath)shape).lineTo(480.647, 136.443);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_8_0;
g.setTransform(defaultTransform__0_8_0);
g.setClip(clip__0_8_0);
float alpha__0_8_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_8_1 = g.getClip();
AffineTransform defaultTransform__0_8_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 136.443);
((GeneralPath)shape).curveTo(503.611, 136.443, 504.71, 135.34499, 504.71, 134.028);
((GeneralPath)shape).curveTo(504.71, 132.637, 503.61, 131.613, 502.219, 131.613);
((GeneralPath)shape).curveTo(500.90298, 131.613, 499.806, 132.63701, 499.806, 134.028);
((GeneralPath)shape).curveTo(499.806, 135.346, 500.902, 136.443, 502.219, 136.443);
((GeneralPath)shape).lineTo(502.219, 136.443);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_8_1;
g.setTransform(defaultTransform__0_8_1);
g.setClip(clip__0_8_1);
float alpha__0_8_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_8_2 = g.getClip();
AffineTransform defaultTransform__0_8_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 136.443);
((GeneralPath)shape).curveTo(492.824, 136.443, 493.923, 135.34499, 493.923, 134.028);
((GeneralPath)shape).curveTo(493.923, 132.637, 492.823, 131.613, 491.435, 131.613);
((GeneralPath)shape).curveTo(490.119, 131.613, 489.019, 132.63701, 489.019, 134.028);
((GeneralPath)shape).curveTo(489.019, 135.346, 490.118, 136.443, 491.435, 136.443);
((GeneralPath)shape).lineTo(491.435, 136.443);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_8_2;
g.setTransform(defaultTransform__0_8_2);
g.setClip(clip__0_8_2);
origAlpha = alpha__0_8;
g.setTransform(defaultTransform__0_8);
g.setClip(clip__0_8);
float alpha__0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_9 = g.getClip();
AffineTransform defaultTransform__0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9 is CompositeGraphicsNode
float alpha__0_9_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_9_0 = g.getClip();
AffineTransform defaultTransform__0_9_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(480.647, 152.95);
((GeneralPath)shape).curveTo(482.039, 152.95, 483.134, 151.85199, 483.134, 150.535);
((GeneralPath)shape).curveTo(483.134, 149.144, 482.038, 148.12001, 480.647, 148.12001);
((GeneralPath)shape).curveTo(479.331, 148.12001, 478.234, 149.14401, 478.234, 150.535);
((GeneralPath)shape).curveTo(478.234, 151.852, 479.331, 152.95, 480.647, 152.95);
((GeneralPath)shape).lineTo(480.647, 152.95);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9_0;
g.setTransform(defaultTransform__0_9_0);
g.setClip(clip__0_9_0);
float alpha__0_9_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_9_1 = g.getClip();
AffineTransform defaultTransform__0_9_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.219, 152.95);
((GeneralPath)shape).curveTo(503.611, 152.95, 504.71, 151.85199, 504.71, 150.535);
((GeneralPath)shape).curveTo(504.71, 149.144, 503.61, 148.12001, 502.219, 148.12001);
((GeneralPath)shape).curveTo(500.90298, 148.12001, 499.806, 149.14401, 499.806, 150.535);
((GeneralPath)shape).curveTo(499.806, 151.852, 500.902, 152.95, 502.219, 152.95);
((GeneralPath)shape).lineTo(502.219, 152.95);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9_1;
g.setTransform(defaultTransform__0_9_1);
g.setClip(clip__0_9_1);
float alpha__0_9_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_9_2 = g.getClip();
AffineTransform defaultTransform__0_9_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.435, 152.95);
((GeneralPath)shape).curveTo(492.824, 152.95, 493.923, 151.85199, 493.923, 150.535);
((GeneralPath)shape).curveTo(493.923, 149.144, 492.823, 148.12001, 491.435, 148.12001);
((GeneralPath)shape).curveTo(490.119, 148.12001, 489.019, 149.14401, 489.019, 150.535);
((GeneralPath)shape).curveTo(489.019, 151.852, 490.118, 152.95, 491.435, 152.95);
((GeneralPath)shape).lineTo(491.435, 152.95);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9_2;
g.setTransform(defaultTransform__0_9_2);
g.setClip(clip__0_9_2);
origAlpha = alpha__0_9;
g.setTransform(defaultTransform__0_9);
g.setClip(clip__0_9);
float alpha__0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_10 = g.getClip();
AffineTransform defaultTransform__0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_10 is CompositeGraphicsNode
float alpha__0_10_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_10_0 = g.getClip();
AffineTransform defaultTransform__0_10_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_10_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 185.962);
((GeneralPath)shape).curveTo(498.14297, 185.962, 499.24, 184.864, 499.24, 183.54701);
((GeneralPath)shape).curveTo(499.24, 182.23003, 498.143, 181.13202, 496.826, 181.13202);
((GeneralPath)shape).curveTo(495.50998, 181.13202, 494.414, 182.23003, 494.414, 183.54701);
((GeneralPath)shape).curveTo(494.414, 184.864, 495.51, 185.962, 496.826, 185.962);
((GeneralPath)shape).lineTo(496.826, 185.962);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_10_0;
g.setTransform(defaultTransform__0_10_0);
g.setClip(clip__0_10_0);
float alpha__0_10_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_10_1 = g.getClip();
AffineTransform defaultTransform__0_10_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_10_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 185.962);
((GeneralPath)shape).curveTo(487.359, 185.962, 488.456, 184.864, 488.456, 183.54701);
((GeneralPath)shape).curveTo(488.456, 182.23003, 487.359, 181.13202, 486.039, 181.13202);
((GeneralPath)shape).curveTo(484.723, 181.13202, 483.627, 182.23003, 483.627, 183.54701);
((GeneralPath)shape).curveTo(483.627, 184.864, 484.723, 185.962, 486.039, 185.962);
((GeneralPath)shape).lineTo(486.039, 185.962);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_10_1;
g.setTransform(defaultTransform__0_10_1);
g.setClip(clip__0_10_1);
origAlpha = alpha__0_10;
g.setTransform(defaultTransform__0_10);
g.setClip(clip__0_10);
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
        return 0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 0;
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

