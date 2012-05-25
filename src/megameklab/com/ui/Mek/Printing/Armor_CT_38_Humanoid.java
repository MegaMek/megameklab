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
public class Armor_CT_38_Humanoid {
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
((GeneralPath)shape).moveTo(480.648, 158.466);
((GeneralPath)shape).curveTo(482.039, 158.466, 483.06, 157.367, 483.06, 156.05101);
((GeneralPath)shape).curveTo(483.06, 154.73601, 482.039, 153.63701, 480.648, 153.63701);
((GeneralPath)shape).curveTo(479.33102, 153.63701, 478.234, 154.73601, 478.234, 156.05101);
((GeneralPath)shape).curveTo(478.234, 157.367, 479.331, 158.466, 480.648, 158.466);
((GeneralPath)shape).lineTo(480.648, 158.466);
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
((GeneralPath)shape).moveTo(502.223, 158.466);
((GeneralPath)shape).curveTo(503.611, 158.466, 504.63498, 157.367, 504.63498, 156.05101);
((GeneralPath)shape).curveTo(504.63498, 154.73601, 503.611, 153.63701, 502.223, 153.63701);
((GeneralPath)shape).curveTo(500.906, 153.63701, 499.806, 154.73601, 499.806, 156.05101);
((GeneralPath)shape).curveTo(499.806, 157.367, 500.905, 158.466, 502.223, 158.466);
((GeneralPath)shape).lineTo(502.223, 158.466);
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
((GeneralPath)shape).moveTo(491.436, 158.466);
((GeneralPath)shape).curveTo(492.824, 158.466, 493.848, 157.367, 493.848, 156.05101);
((GeneralPath)shape).curveTo(493.848, 154.73601, 492.824, 153.63701, 491.436, 153.63701);
((GeneralPath)shape).curveTo(490.11902, 153.63701, 489.019, 154.73601, 489.019, 156.05101);
((GeneralPath)shape).curveTo(489.019, 157.367, 490.118, 158.466, 491.436, 158.466);
((GeneralPath)shape).lineTo(491.436, 158.466);
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
((GeneralPath)shape).moveTo(480.648, 179.631);
((GeneralPath)shape).curveTo(482.039, 179.631, 483.06, 178.532, 483.06, 177.216);
((GeneralPath)shape).curveTo(483.06, 175.901, 482.039, 174.802, 480.648, 174.802);
((GeneralPath)shape).curveTo(479.33102, 174.802, 478.234, 175.901, 478.234, 177.216);
((GeneralPath)shape).curveTo(478.234, 178.533, 479.331, 179.631, 480.648, 179.631);
((GeneralPath)shape).lineTo(480.648, 179.631);
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
((GeneralPath)shape).moveTo(502.223, 179.631);
((GeneralPath)shape).curveTo(503.611, 179.631, 504.63498, 178.532, 504.63498, 177.216);
((GeneralPath)shape).curveTo(504.63498, 175.901, 503.611, 174.802, 502.223, 174.802);
((GeneralPath)shape).curveTo(500.906, 174.802, 499.806, 175.901, 499.806, 177.216);
((GeneralPath)shape).curveTo(499.806, 178.533, 500.905, 179.631, 502.223, 179.631);
((GeneralPath)shape).lineTo(502.223, 179.631);
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
((GeneralPath)shape).moveTo(491.436, 179.631);
((GeneralPath)shape).curveTo(492.824, 179.631, 493.848, 178.532, 493.848, 177.216);
((GeneralPath)shape).curveTo(493.848, 175.901, 492.824, 174.802, 491.436, 174.802);
((GeneralPath)shape).curveTo(490.11902, 174.802, 489.019, 175.901, 489.019, 177.216);
((GeneralPath)shape).curveTo(489.019, 178.533, 490.118, 179.631, 491.436, 179.631);
((GeneralPath)shape).lineTo(491.436, 179.631);
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
((GeneralPath)shape).curveTo(493.848, 186.48401, 492.824, 185.38501, 491.436, 185.38501);
((GeneralPath)shape).curveTo(490.11902, 185.38501, 489.019, 186.48401, 489.019, 187.79901);
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
((GeneralPath)shape).moveTo(480.648, 169.048);
((GeneralPath)shape).curveTo(482.039, 169.048, 483.06, 167.949, 483.06, 166.63301);
((GeneralPath)shape).curveTo(483.06, 165.31702, 482.039, 164.21901, 480.648, 164.21901);
((GeneralPath)shape).curveTo(479.33102, 164.21901, 478.234, 165.31702, 478.234, 166.63301);
((GeneralPath)shape).curveTo(478.234, 167.949, 479.331, 169.048, 480.648, 169.048);
((GeneralPath)shape).lineTo(480.648, 169.048);
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
((GeneralPath)shape).moveTo(502.223, 169.048);
((GeneralPath)shape).curveTo(503.611, 169.048, 504.63498, 167.949, 504.63498, 166.63301);
((GeneralPath)shape).curveTo(504.63498, 165.31702, 503.611, 164.21901, 502.223, 164.21901);
((GeneralPath)shape).curveTo(500.906, 164.21901, 499.806, 165.31702, 499.806, 166.63301);
((GeneralPath)shape).curveTo(499.806, 167.949, 500.905, 169.048, 502.223, 169.048);
((GeneralPath)shape).lineTo(502.223, 169.048);
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
((GeneralPath)shape).moveTo(491.436, 169.048);
((GeneralPath)shape).curveTo(492.824, 169.048, 493.848, 167.949, 493.848, 166.63301);
((GeneralPath)shape).curveTo(493.848, 165.31702, 492.824, 164.21901, 491.436, 164.21901);
((GeneralPath)shape).curveTo(490.11902, 164.21901, 489.019, 165.31702, 489.019, 166.63301);
((GeneralPath)shape).curveTo(489.019, 167.949, 490.118, 169.048, 491.436, 169.048);
((GeneralPath)shape).lineTo(491.436, 169.048);
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
((GeneralPath)shape).moveTo(480.647, 126.718);
((GeneralPath)shape).curveTo(482.039, 126.718, 483.134, 125.62, 483.134, 124.303);
((GeneralPath)shape).curveTo(483.134, 122.912, 482.038, 121.889, 480.647, 121.889);
((GeneralPath)shape).curveTo(479.331, 121.889, 478.234, 122.912, 478.234, 124.303);
((GeneralPath)shape).curveTo(478.234, 125.62, 479.331, 126.718, 480.647, 126.718);
((GeneralPath)shape).lineTo(480.647, 126.718);
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
((GeneralPath)shape).moveTo(502.219, 126.718);
((GeneralPath)shape).curveTo(503.611, 126.718, 504.71, 125.62, 504.71, 124.303);
((GeneralPath)shape).curveTo(504.71, 122.912, 503.61, 121.889, 502.219, 121.889);
((GeneralPath)shape).curveTo(500.90298, 121.889, 499.806, 122.912, 499.806, 124.303);
((GeneralPath)shape).curveTo(499.806, 125.62, 500.902, 126.718, 502.219, 126.718);
((GeneralPath)shape).lineTo(502.219, 126.718);
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
((GeneralPath)shape).moveTo(491.435, 126.718);
((GeneralPath)shape).curveTo(492.824, 126.718, 493.923, 125.62, 493.923, 124.303);
((GeneralPath)shape).curveTo(493.923, 122.912, 492.823, 121.889, 491.435, 121.889);
((GeneralPath)shape).curveTo(490.119, 121.889, 489.019, 122.912, 489.019, 124.303);
((GeneralPath)shape).curveTo(489.019, 125.62, 490.118, 126.718, 491.435, 126.718);
((GeneralPath)shape).lineTo(491.435, 126.718);
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
((GeneralPath)shape).moveTo(480.647, 116.135);
((GeneralPath)shape).curveTo(482.039, 116.135, 483.134, 115.037, 483.134, 113.72);
((GeneralPath)shape).curveTo(483.134, 112.329, 482.038, 111.306, 480.647, 111.306);
((GeneralPath)shape).curveTo(479.331, 111.306, 478.234, 112.329, 478.234, 113.72);
((GeneralPath)shape).curveTo(478.234, 115.038, 479.331, 116.135, 480.647, 116.135);
((GeneralPath)shape).lineTo(480.647, 116.135);
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
((GeneralPath)shape).moveTo(502.219, 116.135);
((GeneralPath)shape).curveTo(503.611, 116.135, 504.71, 115.037, 504.71, 113.72);
((GeneralPath)shape).curveTo(504.71, 112.329, 503.61, 111.306, 502.219, 111.306);
((GeneralPath)shape).curveTo(500.90298, 111.306, 499.806, 112.329, 499.806, 113.72);
((GeneralPath)shape).curveTo(499.806, 115.038, 500.902, 116.135, 502.219, 116.135);
((GeneralPath)shape).lineTo(502.219, 116.135);
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
((GeneralPath)shape).moveTo(491.435, 116.135);
((GeneralPath)shape).curveTo(492.824, 116.135, 493.923, 115.037, 493.923, 113.72);
((GeneralPath)shape).curveTo(493.923, 112.329, 492.823, 111.306, 491.435, 111.306);
((GeneralPath)shape).curveTo(490.119, 111.306, 489.019, 112.329, 489.019, 113.72);
((GeneralPath)shape).curveTo(489.019, 115.038, 490.118, 116.135, 491.435, 116.135);
((GeneralPath)shape).lineTo(491.435, 116.135);
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
((GeneralPath)shape).moveTo(480.647, 137.3);
((GeneralPath)shape).curveTo(482.039, 137.3, 483.134, 136.202, 483.134, 134.88501);
((GeneralPath)shape).curveTo(483.134, 133.494, 482.038, 132.47101, 480.647, 132.47101);
((GeneralPath)shape).curveTo(479.331, 132.47101, 478.234, 133.494, 478.234, 134.88501);
((GeneralPath)shape).curveTo(478.234, 136.203, 479.331, 137.3, 480.647, 137.3);
((GeneralPath)shape).lineTo(480.647, 137.3);
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
((GeneralPath)shape).moveTo(502.219, 137.3);
((GeneralPath)shape).curveTo(503.611, 137.3, 504.71, 136.202, 504.71, 134.88501);
((GeneralPath)shape).curveTo(504.71, 133.494, 503.61, 132.47101, 502.219, 132.47101);
((GeneralPath)shape).curveTo(500.90298, 132.47101, 499.806, 133.494, 499.806, 134.88501);
((GeneralPath)shape).curveTo(499.806, 136.203, 500.902, 137.3, 502.219, 137.3);
((GeneralPath)shape).lineTo(502.219, 137.3);
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
((GeneralPath)shape).moveTo(491.435, 137.3);
((GeneralPath)shape).curveTo(492.824, 137.3, 493.923, 136.202, 493.923, 134.88501);
((GeneralPath)shape).curveTo(493.923, 133.494, 492.823, 132.47101, 491.435, 132.47101);
((GeneralPath)shape).curveTo(490.119, 132.47101, 489.019, 133.494, 489.019, 134.88501);
((GeneralPath)shape).curveTo(489.019, 136.203, 490.118, 137.3, 491.435, 137.3);
((GeneralPath)shape).lineTo(491.435, 137.3);
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
((GeneralPath)shape).moveTo(480.647, 147.883);
((GeneralPath)shape).curveTo(482.039, 147.883, 483.134, 146.78499, 483.134, 145.468);
((GeneralPath)shape).curveTo(483.134, 144.077, 482.038, 143.054, 480.647, 143.054);
((GeneralPath)shape).curveTo(479.331, 143.054, 478.234, 144.077, 478.234, 145.468);
((GeneralPath)shape).curveTo(478.234, 146.786, 479.331, 147.883, 480.647, 147.883);
((GeneralPath)shape).lineTo(480.647, 147.883);
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
((GeneralPath)shape).moveTo(502.219, 147.883);
((GeneralPath)shape).curveTo(503.611, 147.883, 504.71, 146.78499, 504.71, 145.468);
((GeneralPath)shape).curveTo(504.71, 144.077, 503.61, 143.054, 502.219, 143.054);
((GeneralPath)shape).curveTo(500.90298, 143.054, 499.806, 144.077, 499.806, 145.468);
((GeneralPath)shape).curveTo(499.806, 146.786, 500.902, 147.883, 502.219, 147.883);
((GeneralPath)shape).lineTo(502.219, 147.883);
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
((GeneralPath)shape).moveTo(491.435, 147.883);
((GeneralPath)shape).curveTo(492.824, 147.883, 493.923, 146.78499, 493.923, 145.468);
((GeneralPath)shape).curveTo(493.923, 144.077, 492.823, 143.054, 491.435, 143.054);
((GeneralPath)shape).curveTo(490.119, 143.054, 489.019, 144.077, 489.019, 145.468);
((GeneralPath)shape).curveTo(489.019, 146.786, 490.118, 147.883, 491.435, 147.883);
((GeneralPath)shape).lineTo(491.435, 147.883);
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
((GeneralPath)shape).moveTo(496.826, 184.922);
((GeneralPath)shape).curveTo(498.14297, 184.922, 499.24, 183.82399, 499.24, 182.507);
((GeneralPath)shape).curveTo(499.24, 181.19002, 498.143, 180.09201, 496.826, 180.09201);
((GeneralPath)shape).curveTo(495.50998, 180.09201, 494.414, 181.19002, 494.414, 182.507);
((GeneralPath)shape).curveTo(494.414, 183.82399, 495.51, 184.922, 496.826, 184.922);
((GeneralPath)shape).lineTo(496.826, 184.922);
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
((GeneralPath)shape).moveTo(486.039, 184.922);
((GeneralPath)shape).curveTo(487.359, 184.922, 488.456, 183.82399, 488.456, 182.507);
((GeneralPath)shape).curveTo(488.456, 181.19002, 487.359, 180.09201, 486.039, 180.09201);
((GeneralPath)shape).curveTo(484.723, 180.09201, 483.627, 181.19002, 483.627, 182.507);
((GeneralPath)shape).curveTo(483.627, 183.82399, 484.723, 184.922, 486.039, 184.922);
((GeneralPath)shape).lineTo(486.039, 184.922);
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
((GeneralPath)shape).moveTo(496.826, 121.426);
((GeneralPath)shape).curveTo(498.14297, 121.426, 499.24, 120.328, 499.24, 119.011);
((GeneralPath)shape).curveTo(499.24, 117.694, 498.143, 116.596, 496.826, 116.596);
((GeneralPath)shape).curveTo(495.50998, 116.596, 494.414, 117.694, 494.414, 119.011);
((GeneralPath)shape).curveTo(494.414, 120.328, 495.51, 121.426, 496.826, 121.426);
((GeneralPath)shape).lineTo(496.826, 121.426);
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
((GeneralPath)shape).moveTo(486.039, 121.426);
((GeneralPath)shape).curveTo(487.359, 121.426, 488.456, 120.328, 488.456, 119.011);
((GeneralPath)shape).curveTo(488.456, 117.694, 487.359, 116.596, 486.039, 116.596);
((GeneralPath)shape).curveTo(484.723, 116.596, 483.627, 117.694, 483.627, 119.011);
((GeneralPath)shape).curveTo(483.627, 120.328, 484.723, 121.426, 486.039, 121.426);
((GeneralPath)shape).lineTo(486.039, 121.426);
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
((GeneralPath)shape).moveTo(496.826, 110.844);
((GeneralPath)shape).curveTo(498.14297, 110.844, 499.24, 109.746, 499.24, 108.429);
((GeneralPath)shape).curveTo(499.24, 107.112, 498.143, 106.014, 496.826, 106.014);
((GeneralPath)shape).curveTo(495.50998, 106.014, 494.414, 107.112, 494.414, 108.429);
((GeneralPath)shape).curveTo(494.414, 109.746, 495.51, 110.844, 496.826, 110.844);
((GeneralPath)shape).lineTo(496.826, 110.844);
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
((GeneralPath)shape).moveTo(486.039, 110.844);
((GeneralPath)shape).curveTo(487.359, 110.844, 488.456, 109.746, 488.456, 108.429);
((GeneralPath)shape).curveTo(488.456, 107.112, 487.359, 106.014, 486.039, 106.014);
((GeneralPath)shape).curveTo(484.723, 106.014, 483.627, 107.112, 483.627, 108.429);
((GeneralPath)shape).curveTo(483.627, 109.746, 484.723, 110.844, 486.039, 110.844);
((GeneralPath)shape).lineTo(486.039, 110.844);
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
((GeneralPath)shape).moveTo(496.826, 174.34);
((GeneralPath)shape).curveTo(498.14297, 174.34, 499.24, 173.24199, 499.24, 171.925);
((GeneralPath)shape).curveTo(499.24, 170.60802, 498.143, 169.51001, 496.826, 169.51001);
((GeneralPath)shape).curveTo(495.50998, 169.51001, 494.414, 170.60802, 494.414, 171.925);
((GeneralPath)shape).curveTo(494.414, 173.24199, 495.51, 174.34, 496.826, 174.34);
((GeneralPath)shape).lineTo(496.826, 174.34);
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
((GeneralPath)shape).moveTo(486.039, 174.34);
((GeneralPath)shape).curveTo(487.359, 174.34, 488.456, 173.24199, 488.456, 171.925);
((GeneralPath)shape).curveTo(488.456, 170.60802, 487.359, 169.51001, 486.039, 169.51001);
((GeneralPath)shape).curveTo(484.723, 169.51001, 483.627, 170.60802, 483.627, 171.925);
((GeneralPath)shape).curveTo(483.627, 173.24199, 484.723, 174.34, 486.039, 174.34);
((GeneralPath)shape).lineTo(486.039, 174.34);
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
((GeneralPath)shape).moveTo(496.826, 163.757);
((GeneralPath)shape).curveTo(498.14297, 163.757, 499.24, 162.659, 499.24, 161.34201);
((GeneralPath)shape).curveTo(499.24, 160.02502, 498.143, 158.92702, 496.826, 158.92702);
((GeneralPath)shape).curveTo(495.50998, 158.92702, 494.414, 160.02502, 494.414, 161.34201);
((GeneralPath)shape).curveTo(494.414, 162.659, 495.51, 163.757, 496.826, 163.757);
((GeneralPath)shape).lineTo(496.826, 163.757);
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
((GeneralPath)shape).moveTo(486.039, 163.757);
((GeneralPath)shape).curveTo(487.359, 163.757, 488.456, 162.659, 488.456, 161.34201);
((GeneralPath)shape).curveTo(488.456, 160.02502, 487.359, 158.92702, 486.039, 158.92702);
((GeneralPath)shape).curveTo(484.723, 158.92702, 483.627, 160.02502, 483.627, 161.34201);
((GeneralPath)shape).curveTo(483.627, 162.659, 484.723, 163.757, 486.039, 163.757);
((GeneralPath)shape).lineTo(486.039, 163.757);
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
((GeneralPath)shape).moveTo(496.826, 153.174);
((GeneralPath)shape).curveTo(498.14297, 153.174, 499.24, 152.07599, 499.24, 150.759);
((GeneralPath)shape).curveTo(499.24, 149.44301, 498.143, 148.345, 496.826, 148.345);
((GeneralPath)shape).curveTo(495.50998, 148.345, 494.414, 149.44301, 494.414, 150.759);
((GeneralPath)shape).curveTo(494.414, 152.077, 495.51, 153.174, 496.826, 153.174);
((GeneralPath)shape).lineTo(496.826, 153.174);
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
((GeneralPath)shape).moveTo(486.039, 153.174);
((GeneralPath)shape).curveTo(487.359, 153.174, 488.456, 152.07599, 488.456, 150.759);
((GeneralPath)shape).curveTo(488.456, 149.44301, 487.359, 148.345, 486.039, 148.345);
((GeneralPath)shape).curveTo(484.723, 148.345, 483.627, 149.44301, 483.627, 150.759);
((GeneralPath)shape).curveTo(483.627, 152.077, 484.723, 153.174, 486.039, 153.174);
((GeneralPath)shape).lineTo(486.039, 153.174);
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
((GeneralPath)shape).moveTo(496.826, 142.592);
((GeneralPath)shape).curveTo(498.14297, 142.592, 499.24, 141.49399, 499.24, 140.177);
((GeneralPath)shape).curveTo(499.24, 138.86101, 498.143, 137.763, 496.826, 137.763);
((GeneralPath)shape).curveTo(495.50998, 137.763, 494.414, 138.86101, 494.414, 140.177);
((GeneralPath)shape).curveTo(494.414, 141.494, 495.51, 142.592, 496.826, 142.592);
((GeneralPath)shape).lineTo(496.826, 142.592);
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
((GeneralPath)shape).moveTo(486.039, 142.592);
((GeneralPath)shape).curveTo(487.359, 142.592, 488.456, 141.49399, 488.456, 140.177);
((GeneralPath)shape).curveTo(488.456, 138.86101, 487.359, 137.763, 486.039, 137.763);
((GeneralPath)shape).curveTo(484.723, 137.763, 483.627, 138.86101, 483.627, 140.177);
((GeneralPath)shape).curveTo(483.627, 141.494, 484.723, 142.592, 486.039, 142.592);
((GeneralPath)shape).lineTo(486.039, 142.592);
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
float alpha__0_16 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_16 = g.getClip();
AffineTransform defaultTransform__0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_16 is CompositeGraphicsNode
float alpha__0_16_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_16_0 = g.getClip();
AffineTransform defaultTransform__0_16_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_16_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(496.826, 132.009);
((GeneralPath)shape).curveTo(498.14297, 132.009, 499.24, 130.911, 499.24, 129.59401);
((GeneralPath)shape).curveTo(499.24, 128.27802, 498.143, 127.18001, 496.826, 127.18001);
((GeneralPath)shape).curveTo(495.50998, 127.18001, 494.414, 128.27802, 494.414, 129.59401);
((GeneralPath)shape).curveTo(494.414, 130.912, 495.51, 132.009, 496.826, 132.009);
((GeneralPath)shape).lineTo(496.826, 132.009);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_16_0;
g.setTransform(defaultTransform__0_16_0);
g.setClip(clip__0_16_0);
float alpha__0_16_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_16_1 = g.getClip();
AffineTransform defaultTransform__0_16_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_16_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(486.039, 132.009);
((GeneralPath)shape).curveTo(487.359, 132.009, 488.456, 130.911, 488.456, 129.59401);
((GeneralPath)shape).curveTo(488.456, 128.27802, 487.359, 127.18001, 486.039, 127.18001);
((GeneralPath)shape).curveTo(484.723, 127.18001, 483.627, 128.27802, 483.627, 129.59401);
((GeneralPath)shape).curveTo(483.627, 130.912, 484.723, 132.009, 486.039, 132.009);
((GeneralPath)shape).lineTo(486.039, 132.009);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_16_1;
g.setTransform(defaultTransform__0_16_1);
g.setClip(clip__0_16_1);
origAlpha = alpha__0_16;
g.setTransform(defaultTransform__0_16);
g.setClip(clip__0_16);
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

