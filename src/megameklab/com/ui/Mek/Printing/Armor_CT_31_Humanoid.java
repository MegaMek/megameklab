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
public class Armor_CT_31_Humanoid {
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
((GeneralPath)shape).moveTo(480.648, 156.251);
((GeneralPath)shape).curveTo(482.039, 156.251, 483.06, 155.15201, 483.06, 153.83601);
((GeneralPath)shape).curveTo(483.06, 152.52101, 482.039, 151.42201, 480.648, 151.42201);
((GeneralPath)shape).curveTo(479.33102, 151.42201, 478.234, 152.52101, 478.234, 153.83601);
((GeneralPath)shape).curveTo(478.234, 155.152, 479.331, 156.251, 480.648, 156.251);
((GeneralPath)shape).lineTo(480.648, 156.251);
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
((GeneralPath)shape).moveTo(502.223, 156.251);
((GeneralPath)shape).curveTo(503.611, 156.251, 504.63498, 155.15201, 504.63498, 153.83601);
((GeneralPath)shape).curveTo(504.63498, 152.52101, 503.611, 151.42201, 502.223, 151.42201);
((GeneralPath)shape).curveTo(500.906, 151.42201, 499.806, 152.52101, 499.806, 153.83601);
((GeneralPath)shape).curveTo(499.806, 155.152, 500.905, 156.251, 502.223, 156.251);
((GeneralPath)shape).lineTo(502.223, 156.251);
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
((GeneralPath)shape).moveTo(491.436, 156.251);
((GeneralPath)shape).curveTo(492.824, 156.251, 493.848, 155.15201, 493.848, 153.83601);
((GeneralPath)shape).curveTo(493.848, 152.52101, 492.824, 151.42201, 491.436, 151.42201);
((GeneralPath)shape).curveTo(490.11902, 151.42201, 489.019, 152.52101, 489.019, 153.83601);
((GeneralPath)shape).curveTo(489.019, 155.152, 490.118, 156.251, 491.436, 156.251);
((GeneralPath)shape).lineTo(491.436, 156.251);
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
((GeneralPath)shape).moveTo(480.648, 163.679);
((GeneralPath)shape).curveTo(482.039, 163.679, 483.06, 162.58, 483.06, 161.264);
((GeneralPath)shape).curveTo(483.06, 159.94801, 482.039, 158.85, 480.648, 158.85);
((GeneralPath)shape).curveTo(479.33102, 158.85, 478.234, 159.94801, 478.234, 161.264);
((GeneralPath)shape).curveTo(478.234, 162.58, 479.331, 163.679, 480.648, 163.679);
((GeneralPath)shape).lineTo(480.648, 163.679);
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
((GeneralPath)shape).moveTo(502.223, 163.679);
((GeneralPath)shape).curveTo(503.611, 163.679, 504.63498, 162.58, 504.63498, 161.264);
((GeneralPath)shape).curveTo(504.63498, 159.94801, 503.611, 158.85, 502.223, 158.85);
((GeneralPath)shape).curveTo(500.906, 158.85, 499.806, 159.94801, 499.806, 161.264);
((GeneralPath)shape).curveTo(499.806, 162.58, 500.905, 163.679, 502.223, 163.679);
((GeneralPath)shape).lineTo(502.223, 163.679);
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
((GeneralPath)shape).moveTo(491.436, 163.679);
((GeneralPath)shape).curveTo(492.824, 163.679, 493.848, 162.58, 493.848, 161.264);
((GeneralPath)shape).curveTo(493.848, 159.94801, 492.824, 158.85, 491.436, 158.85);
((GeneralPath)shape).curveTo(490.11902, 158.85, 489.019, 159.94801, 489.019, 161.264);
((GeneralPath)shape).curveTo(489.019, 162.58, 490.118, 163.679, 491.436, 163.679);
((GeneralPath)shape).lineTo(491.436, 163.679);
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
((GeneralPath)shape).moveTo(480.648, 178.534);
((GeneralPath)shape).curveTo(482.039, 178.534, 483.06, 177.435, 483.06, 176.119);
((GeneralPath)shape).curveTo(483.06, 174.80301, 482.039, 173.705, 480.648, 173.705);
((GeneralPath)shape).curveTo(479.33102, 173.705, 478.234, 174.80301, 478.234, 176.119);
((GeneralPath)shape).curveTo(478.234, 177.436, 479.331, 178.534, 480.648, 178.534);
((GeneralPath)shape).lineTo(480.648, 178.534);
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
((GeneralPath)shape).moveTo(502.223, 178.534);
((GeneralPath)shape).curveTo(503.611, 178.534, 504.63498, 177.435, 504.63498, 176.119);
((GeneralPath)shape).curveTo(504.63498, 174.80301, 503.611, 173.705, 502.223, 173.705);
((GeneralPath)shape).curveTo(500.906, 173.705, 499.806, 174.80301, 499.806, 176.119);
((GeneralPath)shape).curveTo(499.806, 177.436, 500.905, 178.534, 502.223, 178.534);
((GeneralPath)shape).lineTo(502.223, 178.534);
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
((GeneralPath)shape).moveTo(491.436, 178.534);
((GeneralPath)shape).curveTo(492.824, 178.534, 493.848, 177.435, 493.848, 176.119);
((GeneralPath)shape).curveTo(493.848, 174.80301, 492.824, 173.705, 491.436, 173.705);
((GeneralPath)shape).curveTo(490.11902, 173.705, 489.019, 174.80301, 489.019, 176.119);
((GeneralPath)shape).curveTo(489.019, 177.436, 490.118, 178.534, 491.436, 178.534);
((GeneralPath)shape).lineTo(491.436, 178.534);
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
((GeneralPath)shape).moveTo(480.648, 171.106);
((GeneralPath)shape).curveTo(482.039, 171.106, 483.06, 170.007, 483.06, 168.69101);
((GeneralPath)shape).curveTo(483.06, 167.37502, 482.039, 166.27701, 480.648, 166.27701);
((GeneralPath)shape).curveTo(479.33102, 166.27701, 478.234, 167.37502, 478.234, 168.69101);
((GeneralPath)shape).curveTo(478.234, 170.008, 479.331, 171.106, 480.648, 171.106);
((GeneralPath)shape).lineTo(480.648, 171.106);
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
((GeneralPath)shape).moveTo(502.223, 171.106);
((GeneralPath)shape).curveTo(503.611, 171.106, 504.63498, 170.007, 504.63498, 168.69101);
((GeneralPath)shape).curveTo(504.63498, 167.37502, 503.611, 166.27701, 502.223, 166.27701);
((GeneralPath)shape).curveTo(500.906, 166.27701, 499.806, 167.37502, 499.806, 168.69101);
((GeneralPath)shape).curveTo(499.806, 170.008, 500.905, 171.106, 502.223, 171.106);
((GeneralPath)shape).lineTo(502.223, 171.106);
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
((GeneralPath)shape).moveTo(491.436, 171.106);
((GeneralPath)shape).curveTo(492.824, 171.106, 493.848, 170.007, 493.848, 168.69101);
((GeneralPath)shape).curveTo(493.848, 167.37502, 492.824, 166.27701, 491.436, 166.27701);
((GeneralPath)shape).curveTo(490.11902, 166.27701, 489.019, 167.37502, 489.019, 168.69101);
((GeneralPath)shape).curveTo(489.019, 170.008, 490.118, 171.106, 491.436, 171.106);
((GeneralPath)shape).lineTo(491.436, 171.106);
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
((GeneralPath)shape).moveTo(480.647, 141.395);
((GeneralPath)shape).curveTo(482.039, 141.395, 483.134, 140.297, 483.134, 138.98001);
((GeneralPath)shape).curveTo(483.134, 137.589, 482.038, 136.56502, 480.647, 136.56502);
((GeneralPath)shape).curveTo(479.331, 136.56502, 478.234, 137.58902, 478.234, 138.98001);
((GeneralPath)shape).curveTo(478.234, 140.297, 479.331, 141.395, 480.647, 141.395);
((GeneralPath)shape).lineTo(480.647, 141.395);
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
((GeneralPath)shape).moveTo(502.219, 141.395);
((GeneralPath)shape).curveTo(503.611, 141.395, 504.71, 140.297, 504.71, 138.98001);
((GeneralPath)shape).curveTo(504.71, 137.589, 503.61, 136.56502, 502.219, 136.56502);
((GeneralPath)shape).curveTo(500.90298, 136.56502, 499.806, 137.58902, 499.806, 138.98001);
((GeneralPath)shape).curveTo(499.806, 140.297, 500.902, 141.395, 502.219, 141.395);
((GeneralPath)shape).lineTo(502.219, 141.395);
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
((GeneralPath)shape).moveTo(491.435, 141.395);
((GeneralPath)shape).curveTo(492.824, 141.395, 493.923, 140.297, 493.923, 138.98001);
((GeneralPath)shape).curveTo(493.923, 137.589, 492.823, 136.56502, 491.435, 136.56502);
((GeneralPath)shape).curveTo(490.119, 136.56502, 489.019, 137.58902, 489.019, 138.98001);
((GeneralPath)shape).curveTo(489.019, 140.297, 490.118, 141.395, 491.435, 141.395);
((GeneralPath)shape).lineTo(491.435, 141.395);
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
((GeneralPath)shape).moveTo(480.647, 119.112);
((GeneralPath)shape).curveTo(482.039, 119.112, 483.134, 118.014, 483.134, 116.697);
((GeneralPath)shape).curveTo(483.134, 115.306, 482.038, 114.283, 480.647, 114.283);
((GeneralPath)shape).curveTo(479.331, 114.283, 478.234, 115.306, 478.234, 116.697);
((GeneralPath)shape).curveTo(478.234, 118.014, 479.331, 119.112, 480.647, 119.112);
((GeneralPath)shape).lineTo(480.647, 119.112);
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
((GeneralPath)shape).moveTo(502.219, 119.112);
((GeneralPath)shape).curveTo(503.611, 119.112, 504.71, 118.014, 504.71, 116.697);
((GeneralPath)shape).curveTo(504.71, 115.306, 503.61, 114.283, 502.219, 114.283);
((GeneralPath)shape).curveTo(500.90298, 114.283, 499.806, 115.306, 499.806, 116.697);
((GeneralPath)shape).curveTo(499.806, 118.014, 500.902, 119.112, 502.219, 119.112);
((GeneralPath)shape).lineTo(502.219, 119.112);
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
((GeneralPath)shape).moveTo(491.435, 119.112);
((GeneralPath)shape).curveTo(492.824, 119.112, 493.923, 118.014, 493.923, 116.697);
((GeneralPath)shape).curveTo(493.923, 115.306, 492.823, 114.283, 491.435, 114.283);
((GeneralPath)shape).curveTo(490.119, 114.283, 489.019, 115.306, 489.019, 116.697);
((GeneralPath)shape).curveTo(489.019, 118.014, 490.118, 119.112, 491.435, 119.112);
((GeneralPath)shape).lineTo(491.435, 119.112);
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
((GeneralPath)shape).moveTo(480.647, 126.54);
((GeneralPath)shape).curveTo(482.039, 126.54, 483.134, 125.442, 483.134, 124.125);
((GeneralPath)shape).curveTo(483.134, 122.734, 482.038, 121.711, 480.647, 121.711);
((GeneralPath)shape).curveTo(479.331, 121.711, 478.234, 122.734, 478.234, 124.125);
((GeneralPath)shape).curveTo(478.234, 125.442, 479.331, 126.54, 480.647, 126.54);
((GeneralPath)shape).lineTo(480.647, 126.54);
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
((GeneralPath)shape).moveTo(502.219, 126.54);
((GeneralPath)shape).curveTo(503.611, 126.54, 504.71, 125.442, 504.71, 124.125);
((GeneralPath)shape).curveTo(504.71, 122.734, 503.61, 121.711, 502.219, 121.711);
((GeneralPath)shape).curveTo(500.90298, 121.711, 499.806, 122.734, 499.806, 124.125);
((GeneralPath)shape).curveTo(499.806, 125.442, 500.902, 126.54, 502.219, 126.54);
((GeneralPath)shape).lineTo(502.219, 126.54);
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
((GeneralPath)shape).moveTo(491.435, 126.54);
((GeneralPath)shape).curveTo(492.824, 126.54, 493.923, 125.442, 493.923, 124.125);
((GeneralPath)shape).curveTo(493.923, 122.734, 492.823, 121.711, 491.435, 121.711);
((GeneralPath)shape).curveTo(490.119, 121.711, 489.019, 122.734, 489.019, 124.125);
((GeneralPath)shape).curveTo(489.019, 125.442, 490.118, 126.54, 491.435, 126.54);
((GeneralPath)shape).lineTo(491.435, 126.54);
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
((GeneralPath)shape).moveTo(480.647, 133.967);
((GeneralPath)shape).curveTo(482.039, 133.967, 483.134, 132.86899, 483.134, 131.552);
((GeneralPath)shape).curveTo(483.134, 130.161, 482.038, 129.13701, 480.647, 129.13701);
((GeneralPath)shape).curveTo(479.331, 129.13701, 478.234, 130.16101, 478.234, 131.552);
((GeneralPath)shape).curveTo(478.234, 132.87, 479.331, 133.967, 480.647, 133.967);
((GeneralPath)shape).lineTo(480.647, 133.967);
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
((GeneralPath)shape).moveTo(502.219, 133.967);
((GeneralPath)shape).curveTo(503.611, 133.967, 504.71, 132.86899, 504.71, 131.552);
((GeneralPath)shape).curveTo(504.71, 130.161, 503.61, 129.13701, 502.219, 129.13701);
((GeneralPath)shape).curveTo(500.90298, 129.13701, 499.806, 130.16101, 499.806, 131.552);
((GeneralPath)shape).curveTo(499.806, 132.87, 500.902, 133.967, 502.219, 133.967);
((GeneralPath)shape).lineTo(502.219, 133.967);
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
((GeneralPath)shape).moveTo(491.435, 133.967);
((GeneralPath)shape).curveTo(492.824, 133.967, 493.923, 132.86899, 493.923, 131.552);
((GeneralPath)shape).curveTo(493.923, 130.161, 492.823, 129.13701, 491.435, 129.13701);
((GeneralPath)shape).curveTo(490.119, 129.13701, 489.019, 130.16101, 489.019, 131.552);
((GeneralPath)shape).curveTo(489.019, 132.87, 490.118, 133.967, 491.435, 133.967);
((GeneralPath)shape).lineTo(491.435, 133.967);
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
((GeneralPath)shape).moveTo(480.647, 148.823);
((GeneralPath)shape).curveTo(482.039, 148.823, 483.134, 147.72499, 483.134, 146.408);
((GeneralPath)shape).curveTo(483.134, 145.017, 482.038, 143.994, 480.647, 143.994);
((GeneralPath)shape).curveTo(479.331, 143.994, 478.234, 145.017, 478.234, 146.408);
((GeneralPath)shape).curveTo(478.234, 147.726, 479.331, 148.823, 480.647, 148.823);
((GeneralPath)shape).lineTo(480.647, 148.823);
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
((GeneralPath)shape).moveTo(502.219, 148.823);
((GeneralPath)shape).curveTo(503.611, 148.823, 504.71, 147.72499, 504.71, 146.408);
((GeneralPath)shape).curveTo(504.71, 145.017, 503.61, 143.994, 502.219, 143.994);
((GeneralPath)shape).curveTo(500.90298, 143.994, 499.806, 145.017, 499.806, 146.408);
((GeneralPath)shape).curveTo(499.806, 147.726, 500.902, 148.823, 502.219, 148.823);
((GeneralPath)shape).lineTo(502.219, 148.823);
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
((GeneralPath)shape).moveTo(491.435, 148.823);
((GeneralPath)shape).curveTo(492.824, 148.823, 493.923, 147.72499, 493.923, 146.408);
((GeneralPath)shape).curveTo(493.923, 145.017, 492.823, 143.994, 491.435, 143.994);
((GeneralPath)shape).curveTo(490.119, 143.994, 489.019, 145.017, 489.019, 146.408);
((GeneralPath)shape).curveTo(489.019, 147.726, 490.118, 148.823, 491.435, 148.823);
((GeneralPath)shape).lineTo(491.435, 148.823);
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
((GeneralPath)shape).moveTo(496.826, 111.684);
((GeneralPath)shape).curveTo(498.14297, 111.684, 499.24, 110.586, 499.24, 109.269);
((GeneralPath)shape).curveTo(499.24, 107.951996, 498.143, 106.854, 496.826, 106.854);
((GeneralPath)shape).curveTo(495.50998, 106.854, 494.414, 107.951996, 494.414, 109.269);
((GeneralPath)shape).curveTo(494.414, 110.586, 495.51, 111.684, 496.826, 111.684);
((GeneralPath)shape).lineTo(496.826, 111.684);
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
((GeneralPath)shape).moveTo(486.039, 111.684);
((GeneralPath)shape).curveTo(487.359, 111.684, 488.456, 110.586, 488.456, 109.269);
((GeneralPath)shape).curveTo(488.456, 107.951996, 487.359, 106.854, 486.039, 106.854);
((GeneralPath)shape).curveTo(484.723, 106.854, 483.627, 107.951996, 483.627, 109.269);
((GeneralPath)shape).curveTo(483.627, 110.586, 484.723, 111.684, 486.039, 111.684);
((GeneralPath)shape).lineTo(486.039, 111.684);
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

