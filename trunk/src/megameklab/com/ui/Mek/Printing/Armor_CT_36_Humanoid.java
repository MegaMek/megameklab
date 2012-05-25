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
public class Armor_CT_36_Humanoid {
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
((GeneralPath)shape).moveTo(480.648, 156.198);
((GeneralPath)shape).curveTo(482.039, 156.198, 483.06, 155.099, 483.06, 153.783);
((GeneralPath)shape).curveTo(483.06, 152.468, 482.039, 151.369, 480.648, 151.369);
((GeneralPath)shape).curveTo(479.33102, 151.369, 478.234, 152.468, 478.234, 153.783);
((GeneralPath)shape).curveTo(478.234, 155.1, 479.331, 156.198, 480.648, 156.198);
((GeneralPath)shape).lineTo(480.648, 156.198);
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
((GeneralPath)shape).moveTo(502.223, 156.198);
((GeneralPath)shape).curveTo(503.611, 156.198, 504.63498, 155.099, 504.63498, 153.783);
((GeneralPath)shape).curveTo(504.63498, 152.468, 503.611, 151.369, 502.223, 151.369);
((GeneralPath)shape).curveTo(500.906, 151.369, 499.806, 152.468, 499.806, 153.783);
((GeneralPath)shape).curveTo(499.806, 155.1, 500.905, 156.198, 502.223, 156.198);
((GeneralPath)shape).lineTo(502.223, 156.198);
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
((GeneralPath)shape).moveTo(491.436, 156.198);
((GeneralPath)shape).curveTo(492.824, 156.198, 493.848, 155.099, 493.848, 153.783);
((GeneralPath)shape).curveTo(493.848, 152.468, 492.824, 151.369, 491.436, 151.369);
((GeneralPath)shape).curveTo(490.11902, 151.369, 489.019, 152.468, 489.019, 153.783);
((GeneralPath)shape).curveTo(489.019, 155.1, 490.118, 156.198, 491.436, 156.198);
((GeneralPath)shape).lineTo(491.436, 156.198);
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
((GeneralPath)shape).moveTo(480.648, 178.875);
((GeneralPath)shape).curveTo(482.039, 178.875, 483.06, 177.776, 483.06, 176.46);
((GeneralPath)shape).curveTo(483.06, 175.145, 482.039, 174.046, 480.648, 174.046);
((GeneralPath)shape).curveTo(479.33102, 174.046, 478.234, 175.145, 478.234, 176.46);
((GeneralPath)shape).curveTo(478.234, 177.777, 479.331, 178.875, 480.648, 178.875);
((GeneralPath)shape).lineTo(480.648, 178.875);
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
((GeneralPath)shape).moveTo(502.223, 178.875);
((GeneralPath)shape).curveTo(503.611, 178.875, 504.63498, 177.776, 504.63498, 176.46);
((GeneralPath)shape).curveTo(504.63498, 175.145, 503.611, 174.046, 502.223, 174.046);
((GeneralPath)shape).curveTo(500.906, 174.046, 499.806, 175.145, 499.806, 176.46);
((GeneralPath)shape).curveTo(499.806, 177.777, 500.905, 178.875, 502.223, 178.875);
((GeneralPath)shape).lineTo(502.223, 178.875);
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
((GeneralPath)shape).moveTo(491.436, 178.875);
((GeneralPath)shape).curveTo(492.824, 178.875, 493.848, 177.776, 493.848, 176.46);
((GeneralPath)shape).curveTo(493.848, 175.145, 492.824, 174.046, 491.436, 174.046);
((GeneralPath)shape).curveTo(490.11902, 174.046, 489.019, 175.145, 489.019, 176.46);
((GeneralPath)shape).curveTo(489.019, 177.777, 490.118, 178.875, 491.436, 178.875);
((GeneralPath)shape).lineTo(491.436, 178.875);
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
((GeneralPath)shape).moveTo(491.436, 190.214);
((GeneralPath)shape).curveTo(492.824, 190.214, 493.848, 189.115, 493.848, 187.79901);
((GeneralPath)shape).curveTo(493.848, 186.48302, 492.824, 185.38501, 491.436, 185.38501);
((GeneralPath)shape).curveTo(490.11902, 185.38501, 489.019, 186.48302, 489.019, 187.79901);
((GeneralPath)shape).curveTo(489.019, 189.115, 490.118, 190.214, 491.436, 190.214);
((GeneralPath)shape).lineTo(491.436, 190.214);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3_0;
g.setTransform(defaultTransform__0_3_0);
g.setClip(clip__0_3_0);
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
((GeneralPath)shape).moveTo(480.648, 167.537);
((GeneralPath)shape).curveTo(482.039, 167.537, 483.06, 166.438, 483.06, 165.12201);
((GeneralPath)shape).curveTo(483.06, 163.80602, 482.039, 162.70801, 480.648, 162.70801);
((GeneralPath)shape).curveTo(479.33102, 162.70801, 478.234, 163.80602, 478.234, 165.12201);
((GeneralPath)shape).curveTo(478.234, 166.438, 479.331, 167.537, 480.648, 167.537);
((GeneralPath)shape).lineTo(480.648, 167.537);
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
((GeneralPath)shape).moveTo(502.223, 167.537);
((GeneralPath)shape).curveTo(503.611, 167.537, 504.63498, 166.438, 504.63498, 165.12201);
((GeneralPath)shape).curveTo(504.63498, 163.80602, 503.611, 162.70801, 502.223, 162.70801);
((GeneralPath)shape).curveTo(500.906, 162.70801, 499.806, 163.80602, 499.806, 165.12201);
((GeneralPath)shape).curveTo(499.806, 166.438, 500.905, 167.537, 502.223, 167.537);
((GeneralPath)shape).lineTo(502.223, 167.537);
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
((GeneralPath)shape).moveTo(491.436, 167.537);
((GeneralPath)shape).curveTo(492.824, 167.537, 493.848, 166.438, 493.848, 165.12201);
((GeneralPath)shape).curveTo(493.848, 163.80602, 492.824, 162.70801, 491.436, 162.70801);
((GeneralPath)shape).curveTo(490.11902, 162.70801, 489.019, 163.80602, 489.019, 165.12201);
((GeneralPath)shape).curveTo(489.019, 166.438, 490.118, 167.537, 491.436, 167.537);
((GeneralPath)shape).lineTo(491.436, 167.537);
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
((GeneralPath)shape).moveTo(480.647, 122.183);
((GeneralPath)shape).curveTo(482.039, 122.183, 483.134, 121.085, 483.134, 119.768);
((GeneralPath)shape).curveTo(483.134, 118.377, 482.038, 117.354, 480.647, 117.354);
((GeneralPath)shape).curveTo(479.331, 117.354, 478.234, 118.377, 478.234, 119.768);
((GeneralPath)shape).curveTo(478.234, 121.085, 479.331, 122.183, 480.647, 122.183);
((GeneralPath)shape).lineTo(480.647, 122.183);
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
((GeneralPath)shape).moveTo(502.219, 122.183);
((GeneralPath)shape).curveTo(503.611, 122.183, 504.71, 121.085, 504.71, 119.768);
((GeneralPath)shape).curveTo(504.71, 118.377, 503.61, 117.354, 502.219, 117.354);
((GeneralPath)shape).curveTo(500.90298, 117.354, 499.806, 118.377, 499.806, 119.768);
((GeneralPath)shape).curveTo(499.806, 121.085, 500.902, 122.183, 502.219, 122.183);
((GeneralPath)shape).lineTo(502.219, 122.183);
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
((GeneralPath)shape).moveTo(491.435, 122.183);
((GeneralPath)shape).curveTo(492.824, 122.183, 493.923, 121.085, 493.923, 119.768);
((GeneralPath)shape).curveTo(493.923, 118.377, 492.823, 117.354, 491.435, 117.354);
((GeneralPath)shape).curveTo(490.119, 117.354, 489.019, 118.377, 489.019, 119.768);
((GeneralPath)shape).curveTo(489.019, 121.085, 490.118, 122.183, 491.435, 122.183);
((GeneralPath)shape).lineTo(491.435, 122.183);
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
((GeneralPath)shape).moveTo(480.647, 110.844);
((GeneralPath)shape).curveTo(482.039, 110.844, 483.134, 109.746, 483.134, 108.429);
((GeneralPath)shape).curveTo(483.134, 107.038, 482.038, 106.015, 480.647, 106.015);
((GeneralPath)shape).curveTo(479.331, 106.015, 478.234, 107.038, 478.234, 108.429);
((GeneralPath)shape).curveTo(478.234, 109.746, 479.331, 110.844, 480.647, 110.844);
((GeneralPath)shape).lineTo(480.647, 110.844);
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
((GeneralPath)shape).moveTo(502.219, 110.844);
((GeneralPath)shape).curveTo(503.611, 110.844, 504.71, 109.746, 504.71, 108.429);
((GeneralPath)shape).curveTo(504.71, 107.038, 503.61, 106.015, 502.219, 106.015);
((GeneralPath)shape).curveTo(500.90298, 106.015, 499.806, 107.038, 499.806, 108.429);
((GeneralPath)shape).curveTo(499.806, 109.746, 500.902, 110.844, 502.219, 110.844);
((GeneralPath)shape).lineTo(502.219, 110.844);
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
((GeneralPath)shape).moveTo(491.435, 110.844);
((GeneralPath)shape).curveTo(492.824, 110.844, 493.923, 109.746, 493.923, 108.429);
((GeneralPath)shape).curveTo(493.923, 107.038, 492.823, 106.015, 491.435, 106.015);
((GeneralPath)shape).curveTo(490.119, 106.015, 489.019, 107.038, 489.019, 108.429);
((GeneralPath)shape).curveTo(489.019, 109.746, 490.118, 110.844, 491.435, 110.844);
((GeneralPath)shape).lineTo(491.435, 110.844);
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
((GeneralPath)shape).moveTo(480.647, 133.521);
((GeneralPath)shape).curveTo(482.039, 133.521, 483.134, 132.42299, 483.134, 131.106);
((GeneralPath)shape).curveTo(483.134, 129.715, 482.038, 128.692, 480.647, 128.692);
((GeneralPath)shape).curveTo(479.331, 128.692, 478.234, 129.715, 478.234, 131.106);
((GeneralPath)shape).curveTo(478.234, 132.423, 479.331, 133.521, 480.647, 133.521);
((GeneralPath)shape).lineTo(480.647, 133.521);
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
((GeneralPath)shape).moveTo(502.219, 133.521);
((GeneralPath)shape).curveTo(503.611, 133.521, 504.71, 132.42299, 504.71, 131.106);
((GeneralPath)shape).curveTo(504.71, 129.715, 503.61, 128.692, 502.219, 128.692);
((GeneralPath)shape).curveTo(500.90298, 128.692, 499.806, 129.715, 499.806, 131.106);
((GeneralPath)shape).curveTo(499.806, 132.423, 500.902, 133.521, 502.219, 133.521);
((GeneralPath)shape).lineTo(502.219, 133.521);
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
((GeneralPath)shape).moveTo(491.435, 133.521);
((GeneralPath)shape).curveTo(492.824, 133.521, 493.923, 132.42299, 493.923, 131.106);
((GeneralPath)shape).curveTo(493.923, 129.715, 492.823, 128.692, 491.435, 128.692);
((GeneralPath)shape).curveTo(490.119, 128.692, 489.019, 129.715, 489.019, 131.106);
((GeneralPath)shape).curveTo(489.019, 132.423, 490.118, 133.521, 491.435, 133.521);
((GeneralPath)shape).lineTo(491.435, 133.521);
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
((GeneralPath)shape).moveTo(480.647, 144.859);
((GeneralPath)shape).curveTo(482.039, 144.859, 483.134, 143.76099, 483.134, 142.444);
((GeneralPath)shape).curveTo(483.134, 141.053, 482.038, 140.03, 480.647, 140.03);
((GeneralPath)shape).curveTo(479.331, 140.03, 478.234, 141.053, 478.234, 142.444);
((GeneralPath)shape).curveTo(478.234, 143.762, 479.331, 144.859, 480.647, 144.859);
((GeneralPath)shape).lineTo(480.647, 144.859);
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
((GeneralPath)shape).moveTo(502.219, 144.859);
((GeneralPath)shape).curveTo(503.611, 144.859, 504.71, 143.76099, 504.71, 142.444);
((GeneralPath)shape).curveTo(504.71, 141.053, 503.61, 140.03, 502.219, 140.03);
((GeneralPath)shape).curveTo(500.90298, 140.03, 499.806, 141.053, 499.806, 142.444);
((GeneralPath)shape).curveTo(499.806, 143.762, 500.902, 144.859, 502.219, 144.859);
((GeneralPath)shape).lineTo(502.219, 144.859);
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
((GeneralPath)shape).moveTo(491.435, 144.859);
((GeneralPath)shape).curveTo(492.824, 144.859, 493.923, 143.76099, 493.923, 142.444);
((GeneralPath)shape).curveTo(493.923, 141.053, 492.823, 140.03, 491.435, 140.03);
((GeneralPath)shape).curveTo(490.119, 140.03, 489.019, 141.053, 489.019, 142.444);
((GeneralPath)shape).curveTo(489.019, 143.762, 490.118, 144.859, 491.435, 144.859);
((GeneralPath)shape).lineTo(491.435, 144.859);
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
((GeneralPath)shape).moveTo(496.826, 184.544);
((GeneralPath)shape).curveTo(498.14297, 184.544, 499.24, 183.446, 499.24, 182.12901);
((GeneralPath)shape).curveTo(499.24, 180.81203, 498.143, 179.71402, 496.826, 179.71402);
((GeneralPath)shape).curveTo(495.50998, 179.71402, 494.414, 180.81203, 494.414, 182.12901);
((GeneralPath)shape).curveTo(494.414, 183.446, 495.51, 184.544, 496.826, 184.544);
((GeneralPath)shape).lineTo(496.826, 184.544);
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
((GeneralPath)shape).moveTo(486.039, 184.544);
((GeneralPath)shape).curveTo(487.359, 184.544, 488.456, 183.446, 488.456, 182.12901);
((GeneralPath)shape).curveTo(488.456, 180.81203, 487.359, 179.71402, 486.039, 179.71402);
((GeneralPath)shape).curveTo(484.723, 179.71402, 483.627, 180.81203, 483.627, 182.12901);
((GeneralPath)shape).curveTo(483.627, 183.446, 484.723, 184.544, 486.039, 184.544);
((GeneralPath)shape).lineTo(486.039, 184.544);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9_1;
g.setTransform(defaultTransform__0_9_1);
g.setClip(clip__0_9_1);
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
((GeneralPath)shape).moveTo(496.826, 116.513);
((GeneralPath)shape).curveTo(498.14297, 116.513, 499.24, 115.415, 499.24, 114.098);
((GeneralPath)shape).curveTo(499.24, 112.781, 498.143, 111.683, 496.826, 111.683);
((GeneralPath)shape).curveTo(495.50998, 111.683, 494.414, 112.781, 494.414, 114.098);
((GeneralPath)shape).curveTo(494.414, 115.415, 495.51, 116.513, 496.826, 116.513);
((GeneralPath)shape).lineTo(496.826, 116.513);
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
((GeneralPath)shape).moveTo(486.039, 116.513);
((GeneralPath)shape).curveTo(487.359, 116.513, 488.456, 115.415, 488.456, 114.098);
((GeneralPath)shape).curveTo(488.456, 112.781, 487.359, 111.683, 486.039, 111.683);
((GeneralPath)shape).curveTo(484.723, 111.683, 483.627, 112.781, 483.627, 114.098);
((GeneralPath)shape).curveTo(483.627, 115.415, 484.723, 116.513, 486.039, 116.513);
((GeneralPath)shape).lineTo(486.039, 116.513);
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
float alpha__0_11 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_11 = g.getClip();
AffineTransform defaultTransform__0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11 is CompositeGraphicsNode
float alpha__0_11_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_11_0 = g.getClip();
AffineTransform defaultTransform__0_11_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 173.206);
((GeneralPath)shape).curveTo(498.14297, 173.206, 499.24, 172.10799, 499.24, 170.791);
((GeneralPath)shape).curveTo(499.24, 169.47401, 498.143, 168.376, 496.826, 168.376);
((GeneralPath)shape).curveTo(495.50998, 168.376, 494.414, 169.47401, 494.414, 170.791);
((GeneralPath)shape).curveTo(494.414, 172.10799, 495.51, 173.206, 496.826, 173.206);
((GeneralPath)shape).lineTo(496.826, 173.206);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_11_0;
g.setTransform(defaultTransform__0_11_0);
g.setClip(clip__0_11_0);
float alpha__0_11_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_11_1 = g.getClip();
AffineTransform defaultTransform__0_11_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 173.206);
((GeneralPath)shape).curveTo(487.359, 173.206, 488.456, 172.10799, 488.456, 170.791);
((GeneralPath)shape).curveTo(488.456, 169.47401, 487.359, 168.376, 486.039, 168.376);
((GeneralPath)shape).curveTo(484.723, 168.376, 483.627, 169.47401, 483.627, 170.791);
((GeneralPath)shape).curveTo(483.627, 172.10799, 484.723, 173.206, 486.039, 173.206);
((GeneralPath)shape).lineTo(486.039, 173.206);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_11_1;
g.setTransform(defaultTransform__0_11_1);
g.setClip(clip__0_11_1);
origAlpha = alpha__0_11;
g.setTransform(defaultTransform__0_11);
g.setClip(clip__0_11);
float alpha__0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_12 = g.getClip();
AffineTransform defaultTransform__0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_12 is CompositeGraphicsNode
float alpha__0_12_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_12_0 = g.getClip();
AffineTransform defaultTransform__0_12_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_12_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 161.867);
((GeneralPath)shape).curveTo(498.14297, 161.867, 499.24, 160.769, 499.24, 159.45201);
((GeneralPath)shape).curveTo(499.24, 158.13503, 498.143, 157.03702, 496.826, 157.03702);
((GeneralPath)shape).curveTo(495.50998, 157.03702, 494.414, 158.13503, 494.414, 159.45201);
((GeneralPath)shape).curveTo(494.414, 160.769, 495.51, 161.867, 496.826, 161.867);
((GeneralPath)shape).lineTo(496.826, 161.867);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_12_0;
g.setTransform(defaultTransform__0_12_0);
g.setClip(clip__0_12_0);
float alpha__0_12_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_12_1 = g.getClip();
AffineTransform defaultTransform__0_12_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_12_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 161.867);
((GeneralPath)shape).curveTo(487.359, 161.867, 488.456, 160.769, 488.456, 159.45201);
((GeneralPath)shape).curveTo(488.456, 158.13503, 487.359, 157.03702, 486.039, 157.03702);
((GeneralPath)shape).curveTo(484.723, 157.03702, 483.627, 158.13503, 483.627, 159.45201);
((GeneralPath)shape).curveTo(483.627, 160.769, 484.723, 161.867, 486.039, 161.867);
((GeneralPath)shape).lineTo(486.039, 161.867);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_12_1;
g.setTransform(defaultTransform__0_12_1);
g.setClip(clip__0_12_1);
origAlpha = alpha__0_12;
g.setTransform(defaultTransform__0_12);
g.setClip(clip__0_12);
float alpha__0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_13 = g.getClip();
AffineTransform defaultTransform__0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_13 is CompositeGraphicsNode
float alpha__0_13_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_13_0 = g.getClip();
AffineTransform defaultTransform__0_13_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_13_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 150.529);
((GeneralPath)shape).curveTo(498.14297, 150.529, 499.24, 149.431, 499.24, 148.11401);
((GeneralPath)shape).curveTo(499.24, 146.79802, 498.143, 145.70001, 496.826, 145.70001);
((GeneralPath)shape).curveTo(495.50998, 145.70001, 494.414, 146.79802, 494.414, 148.11401);
((GeneralPath)shape).curveTo(494.414, 149.431, 495.51, 150.529, 496.826, 150.529);
((GeneralPath)shape).lineTo(496.826, 150.529);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_13_0;
g.setTransform(defaultTransform__0_13_0);
g.setClip(clip__0_13_0);
float alpha__0_13_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_13_1 = g.getClip();
AffineTransform defaultTransform__0_13_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_13_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 150.529);
((GeneralPath)shape).curveTo(487.359, 150.529, 488.456, 149.431, 488.456, 148.11401);
((GeneralPath)shape).curveTo(488.456, 146.79802, 487.359, 145.70001, 486.039, 145.70001);
((GeneralPath)shape).curveTo(484.723, 145.70001, 483.627, 146.79802, 483.627, 148.11401);
((GeneralPath)shape).curveTo(483.627, 149.431, 484.723, 150.529, 486.039, 150.529);
((GeneralPath)shape).lineTo(486.039, 150.529);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_13_1;
g.setTransform(defaultTransform__0_13_1);
g.setClip(clip__0_13_1);
origAlpha = alpha__0_13;
g.setTransform(defaultTransform__0_13);
g.setClip(clip__0_13);
float alpha__0_14 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_14 = g.getClip();
AffineTransform defaultTransform__0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_14 is CompositeGraphicsNode
float alpha__0_14_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_14_0 = g.getClip();
AffineTransform defaultTransform__0_14_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_14_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 139.19);
((GeneralPath)shape).curveTo(498.14297, 139.19, 499.24, 138.092, 499.24, 136.77501);
((GeneralPath)shape).curveTo(499.24, 135.45901, 498.143, 134.36101, 496.826, 134.36101);
((GeneralPath)shape).curveTo(495.50998, 134.36101, 494.414, 135.45901, 494.414, 136.77501);
((GeneralPath)shape).curveTo(494.414, 138.093, 495.51, 139.19, 496.826, 139.19);
((GeneralPath)shape).lineTo(496.826, 139.19);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_14_0;
g.setTransform(defaultTransform__0_14_0);
g.setClip(clip__0_14_0);
float alpha__0_14_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_14_1 = g.getClip();
AffineTransform defaultTransform__0_14_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_14_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 139.19);
((GeneralPath)shape).curveTo(487.359, 139.19, 488.456, 138.092, 488.456, 136.77501);
((GeneralPath)shape).curveTo(488.456, 135.45901, 487.359, 134.36101, 486.039, 134.36101);
((GeneralPath)shape).curveTo(484.723, 134.36101, 483.627, 135.45901, 483.627, 136.77501);
((GeneralPath)shape).curveTo(483.627, 138.093, 484.723, 139.19, 486.039, 139.19);
((GeneralPath)shape).lineTo(486.039, 139.19);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_14_1;
g.setTransform(defaultTransform__0_14_1);
g.setClip(clip__0_14_1);
origAlpha = alpha__0_14;
g.setTransform(defaultTransform__0_14);
g.setClip(clip__0_14);
float alpha__0_15 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_15 = g.getClip();
AffineTransform defaultTransform__0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_15 is CompositeGraphicsNode
float alpha__0_15_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_15_0 = g.getClip();
AffineTransform defaultTransform__0_15_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_15_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 127.852);
((GeneralPath)shape).curveTo(498.14297, 127.852, 499.24, 126.754, 499.24, 125.437);
((GeneralPath)shape).curveTo(499.24, 124.120995, 498.143, 123.022995, 496.826, 123.022995);
((GeneralPath)shape).curveTo(495.50998, 123.022995, 494.414, 124.120995, 494.414, 125.437);
((GeneralPath)shape).curveTo(494.414, 126.754, 495.51, 127.852, 496.826, 127.852);
((GeneralPath)shape).lineTo(496.826, 127.852);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_15_0;
g.setTransform(defaultTransform__0_15_0);
g.setClip(clip__0_15_0);
float alpha__0_15_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_15_1 = g.getClip();
AffineTransform defaultTransform__0_15_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_15_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 127.852);
((GeneralPath)shape).curveTo(487.359, 127.852, 488.456, 126.754, 488.456, 125.437);
((GeneralPath)shape).curveTo(488.456, 124.120995, 487.359, 123.022995, 486.039, 123.022995);
((GeneralPath)shape).curveTo(484.723, 123.022995, 483.627, 124.120995, 483.627, 125.437);
((GeneralPath)shape).curveTo(483.627, 126.754, 484.723, 127.852, 486.039, 127.852);
((GeneralPath)shape).lineTo(486.039, 127.852);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_15_1;
g.setTransform(defaultTransform__0_15_1);
g.setClip(clip__0_15_1);
origAlpha = alpha__0_15;
g.setTransform(defaultTransform__0_15);
g.setClip(clip__0_15);
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

