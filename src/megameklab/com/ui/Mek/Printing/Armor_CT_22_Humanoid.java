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
public class Armor_CT_22_Humanoid {
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
((GeneralPath)shape).moveTo(480.648, 168.713);
((GeneralPath)shape).curveTo(482.039, 168.713, 483.06, 167.614, 483.06, 166.298);
((GeneralPath)shape).curveTo(483.06, 164.98201, 482.039, 163.884, 480.648, 163.884);
((GeneralPath)shape).curveTo(479.33102, 163.884, 478.234, 164.98201, 478.234, 166.298);
((GeneralPath)shape).curveTo(478.234, 167.614, 479.331, 168.713, 480.648, 168.713);
((GeneralPath)shape).lineTo(480.648, 168.713);
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
((GeneralPath)shape).moveTo(502.223, 168.713);
((GeneralPath)shape).curveTo(503.611, 168.713, 504.63498, 167.614, 504.63498, 166.298);
((GeneralPath)shape).curveTo(504.63498, 164.98201, 503.611, 163.884, 502.223, 163.884);
((GeneralPath)shape).curveTo(500.906, 163.884, 499.806, 164.98201, 499.806, 166.298);
((GeneralPath)shape).curveTo(499.806, 167.614, 500.905, 168.713, 502.223, 168.713);
((GeneralPath)shape).lineTo(502.223, 168.713);
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
((GeneralPath)shape).moveTo(491.436, 168.713);
((GeneralPath)shape).curveTo(492.824, 168.713, 493.848, 167.614, 493.848, 166.298);
((GeneralPath)shape).curveTo(493.848, 164.98201, 492.824, 163.884, 491.436, 163.884);
((GeneralPath)shape).curveTo(490.11902, 163.884, 489.019, 164.98201, 489.019, 166.298);
((GeneralPath)shape).curveTo(489.019, 167.614, 490.118, 168.713, 491.436, 168.713);
((GeneralPath)shape).lineTo(491.436, 168.713);
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
((GeneralPath)shape).moveTo(480.647, 151.078);
((GeneralPath)shape).curveTo(482.039, 151.078, 483.134, 149.98, 483.134, 148.66301);
((GeneralPath)shape).curveTo(483.134, 147.272, 482.038, 146.24802, 480.647, 146.24802);
((GeneralPath)shape).curveTo(479.331, 146.24802, 478.234, 147.27202, 478.234, 148.66301);
((GeneralPath)shape).curveTo(478.234, 149.98, 479.331, 151.078, 480.647, 151.078);
((GeneralPath)shape).lineTo(480.647, 151.078);
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
((GeneralPath)shape).moveTo(502.219, 151.078);
((GeneralPath)shape).curveTo(503.611, 151.078, 504.71, 149.98, 504.71, 148.66301);
((GeneralPath)shape).curveTo(504.71, 147.272, 503.61, 146.24802, 502.219, 146.24802);
((GeneralPath)shape).curveTo(500.90298, 146.24802, 499.806, 147.27202, 499.806, 148.66301);
((GeneralPath)shape).curveTo(499.806, 149.98, 500.902, 151.078, 502.219, 151.078);
((GeneralPath)shape).lineTo(502.219, 151.078);
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
((GeneralPath)shape).moveTo(491.435, 151.078);
((GeneralPath)shape).curveTo(492.824, 151.078, 493.923, 149.98, 493.923, 148.66301);
((GeneralPath)shape).curveTo(493.923, 147.272, 492.823, 146.24802, 491.435, 146.24802);
((GeneralPath)shape).curveTo(490.119, 146.24802, 489.019, 147.27202, 489.019, 148.66301);
((GeneralPath)shape).curveTo(489.019, 149.98, 490.118, 151.078, 491.435, 151.078);
((GeneralPath)shape).lineTo(491.435, 151.078);
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
((GeneralPath)shape).moveTo(480.647, 124.625);
((GeneralPath)shape).curveTo(482.039, 124.625, 483.134, 123.527, 483.134, 122.21);
((GeneralPath)shape).curveTo(483.134, 120.819, 482.038, 119.796, 480.647, 119.796);
((GeneralPath)shape).curveTo(479.331, 119.796, 478.234, 120.819, 478.234, 122.21);
((GeneralPath)shape).curveTo(478.234, 123.527, 479.331, 124.625, 480.647, 124.625);
((GeneralPath)shape).lineTo(480.647, 124.625);
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
((GeneralPath)shape).moveTo(502.219, 124.625);
((GeneralPath)shape).curveTo(503.611, 124.625, 504.71, 123.527, 504.71, 122.21);
((GeneralPath)shape).curveTo(504.71, 120.819, 503.61, 119.796, 502.219, 119.796);
((GeneralPath)shape).curveTo(500.90298, 119.796, 499.806, 120.819, 499.806, 122.21);
((GeneralPath)shape).curveTo(499.806, 123.527, 500.902, 124.625, 502.219, 124.625);
((GeneralPath)shape).lineTo(502.219, 124.625);
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
((GeneralPath)shape).moveTo(491.435, 124.625);
((GeneralPath)shape).curveTo(492.824, 124.625, 493.923, 123.527, 493.923, 122.21);
((GeneralPath)shape).curveTo(493.923, 120.819, 492.823, 119.796, 491.435, 119.796);
((GeneralPath)shape).curveTo(490.119, 119.796, 489.019, 120.819, 489.019, 122.21);
((GeneralPath)shape).curveTo(489.019, 123.527, 490.118, 124.625, 491.435, 124.625);
((GeneralPath)shape).lineTo(491.435, 124.625);
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
((GeneralPath)shape).moveTo(480.647, 133.442);
((GeneralPath)shape).curveTo(482.039, 133.442, 483.134, 132.344, 483.134, 131.02701);
((GeneralPath)shape).curveTo(483.134, 129.636, 482.038, 128.61201, 480.647, 128.61201);
((GeneralPath)shape).curveTo(479.331, 128.61201, 478.234, 129.63602, 478.234, 131.02701);
((GeneralPath)shape).curveTo(478.234, 132.345, 479.331, 133.442, 480.647, 133.442);
((GeneralPath)shape).lineTo(480.647, 133.442);
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
((GeneralPath)shape).moveTo(502.219, 133.442);
((GeneralPath)shape).curveTo(503.611, 133.442, 504.71, 132.344, 504.71, 131.02701);
((GeneralPath)shape).curveTo(504.71, 129.636, 503.61, 128.61201, 502.219, 128.61201);
((GeneralPath)shape).curveTo(500.90298, 128.61201, 499.806, 129.63602, 499.806, 131.02701);
((GeneralPath)shape).curveTo(499.806, 132.345, 500.902, 133.442, 502.219, 133.442);
((GeneralPath)shape).lineTo(502.219, 133.442);
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
((GeneralPath)shape).moveTo(491.435, 133.442);
((GeneralPath)shape).curveTo(492.824, 133.442, 493.923, 132.344, 493.923, 131.02701);
((GeneralPath)shape).curveTo(493.923, 129.636, 492.823, 128.61201, 491.435, 128.61201);
((GeneralPath)shape).curveTo(490.119, 128.61201, 489.019, 129.63602, 489.019, 131.02701);
((GeneralPath)shape).curveTo(489.019, 132.345, 490.118, 133.442, 491.435, 133.442);
((GeneralPath)shape).lineTo(491.435, 133.442);
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
((GeneralPath)shape).moveTo(480.647, 142.26);
((GeneralPath)shape).curveTo(482.039, 142.26, 483.134, 141.16199, 483.134, 139.845);
((GeneralPath)shape).curveTo(483.134, 138.454, 482.038, 137.43001, 480.647, 137.43001);
((GeneralPath)shape).curveTo(479.331, 137.43001, 478.234, 138.45401, 478.234, 139.845);
((GeneralPath)shape).curveTo(478.234, 141.163, 479.331, 142.26, 480.647, 142.26);
((GeneralPath)shape).lineTo(480.647, 142.26);
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
((GeneralPath)shape).moveTo(502.219, 142.26);
((GeneralPath)shape).curveTo(503.611, 142.26, 504.71, 141.16199, 504.71, 139.845);
((GeneralPath)shape).curveTo(504.71, 138.454, 503.61, 137.43001, 502.219, 137.43001);
((GeneralPath)shape).curveTo(500.90298, 137.43001, 499.806, 138.45401, 499.806, 139.845);
((GeneralPath)shape).curveTo(499.806, 141.163, 500.902, 142.26, 502.219, 142.26);
((GeneralPath)shape).lineTo(502.219, 142.26);
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
((GeneralPath)shape).moveTo(491.435, 142.26);
((GeneralPath)shape).curveTo(492.824, 142.26, 493.923, 141.16199, 493.923, 139.845);
((GeneralPath)shape).curveTo(493.923, 138.454, 492.823, 137.43001, 491.435, 137.43001);
((GeneralPath)shape).curveTo(490.119, 137.43001, 489.019, 138.45401, 489.019, 139.845);
((GeneralPath)shape).curveTo(489.019, 141.163, 490.118, 142.26, 491.435, 142.26);
((GeneralPath)shape).lineTo(491.435, 142.26);
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
((GeneralPath)shape).moveTo(480.647, 159.896);
((GeneralPath)shape).curveTo(482.039, 159.896, 483.134, 158.79799, 483.134, 157.481);
((GeneralPath)shape).curveTo(483.134, 156.09, 482.038, 155.06601, 480.647, 155.06601);
((GeneralPath)shape).curveTo(479.331, 155.06601, 478.234, 156.09001, 478.234, 157.481);
((GeneralPath)shape).curveTo(478.234, 158.798, 479.331, 159.896, 480.647, 159.896);
((GeneralPath)shape).lineTo(480.647, 159.896);
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
((GeneralPath)shape).moveTo(502.219, 159.896);
((GeneralPath)shape).curveTo(503.611, 159.896, 504.71, 158.79799, 504.71, 157.481);
((GeneralPath)shape).curveTo(504.71, 156.09, 503.61, 155.06601, 502.219, 155.06601);
((GeneralPath)shape).curveTo(500.90298, 155.06601, 499.806, 156.09001, 499.806, 157.481);
((GeneralPath)shape).curveTo(499.806, 158.798, 500.902, 159.896, 502.219, 159.896);
((GeneralPath)shape).lineTo(502.219, 159.896);
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
((GeneralPath)shape).moveTo(491.435, 159.896);
((GeneralPath)shape).curveTo(492.824, 159.896, 493.923, 158.79799, 493.923, 157.481);
((GeneralPath)shape).curveTo(493.923, 156.09, 492.823, 155.06601, 491.435, 155.06601);
((GeneralPath)shape).curveTo(490.119, 155.06601, 489.019, 156.09001, 489.019, 157.481);
((GeneralPath)shape).curveTo(489.019, 158.798, 490.118, 159.896, 491.435, 159.896);
((GeneralPath)shape).lineTo(491.435, 159.896);
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
((GeneralPath)shape).moveTo(496.826, 115.807);
((GeneralPath)shape).curveTo(498.14297, 115.807, 499.24, 114.709, 499.24, 113.392);
((GeneralPath)shape).curveTo(499.24, 112.075, 498.143, 110.977, 496.826, 110.977);
((GeneralPath)shape).curveTo(495.50998, 110.977, 494.414, 112.075, 494.414, 113.392);
((GeneralPath)shape).curveTo(494.414, 114.709, 495.51, 115.807, 496.826, 115.807);
((GeneralPath)shape).lineTo(496.826, 115.807);
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
((GeneralPath)shape).moveTo(486.039, 115.807);
((GeneralPath)shape).curveTo(487.359, 115.807, 488.456, 114.709, 488.456, 113.392);
((GeneralPath)shape).curveTo(488.456, 112.075, 487.359, 110.977, 486.039, 110.977);
((GeneralPath)shape).curveTo(484.723, 110.977, 483.627, 112.075, 483.627, 113.392);
((GeneralPath)shape).curveTo(483.627, 114.709, 484.723, 115.807, 486.039, 115.807);
((GeneralPath)shape).lineTo(486.039, 115.807);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_7_1;
g.setTransform(defaultTransform__0_7_1);
g.setClip(clip__0_7_1);
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
((GeneralPath)shape).moveTo(496.826, 177.531);
((GeneralPath)shape).curveTo(498.14297, 177.531, 499.24, 176.433, 499.24, 175.11601);
((GeneralPath)shape).curveTo(499.24, 173.79903, 498.143, 172.70102, 496.826, 172.70102);
((GeneralPath)shape).curveTo(495.50998, 172.70102, 494.414, 173.79903, 494.414, 175.11601);
((GeneralPath)shape).curveTo(494.414, 176.433, 495.51, 177.531, 496.826, 177.531);
((GeneralPath)shape).lineTo(496.826, 177.531);
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
((GeneralPath)shape).moveTo(486.039, 177.531);
((GeneralPath)shape).curveTo(487.359, 177.531, 488.456, 176.433, 488.456, 175.11601);
((GeneralPath)shape).curveTo(488.456, 173.79903, 487.359, 172.70102, 486.039, 172.70102);
((GeneralPath)shape).curveTo(484.723, 172.70102, 483.627, 173.79903, 483.627, 175.11601);
((GeneralPath)shape).curveTo(483.627, 176.433, 484.723, 177.531, 486.039, 177.531);
((GeneralPath)shape).lineTo(486.039, 177.531);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_8_1;
g.setTransform(defaultTransform__0_8_1);
g.setClip(clip__0_8_1);
origAlpha = alpha__0_8;
g.setTransform(defaultTransform__0_8);
g.setClip(clip__0_8);
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

