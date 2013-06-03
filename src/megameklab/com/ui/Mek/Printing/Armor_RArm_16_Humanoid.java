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
public class Armor_RArm_16_Humanoid {
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
((GeneralPath)shape).moveTo(561.133, 123.863);
((GeneralPath)shape).curveTo(562.801, 123.719, 564.059, 122.215, 563.913, 120.552);
((GeneralPath)shape).curveTo(563.76, 118.821, 562.267, 117.627, 560.598, 117.771);
((GeneralPath)shape).curveTo(558.87604, 117.923004, 557.599, 119.36201, 557.75305, 121.093);
((GeneralPath)shape).curveTo(557.898, 122.753, 559.402, 124.015, 561.133, 123.863);
((GeneralPath)shape).lineTo(561.133, 123.863);
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
((GeneralPath)shape).moveTo(562.631, 140.593);
((GeneralPath)shape).curveTo(564.297, 140.449, 565.557, 138.942, 565.409, 137.28);
((GeneralPath)shape).curveTo(565.257, 135.551, 563.766, 134.355, 562.1, 134.497);
((GeneralPath)shape).curveTo(560.37195, 134.652, 559.09595, 136.08899, 559.25195, 137.81999);
((GeneralPath)shape).curveTo(559.393, 139.479, 560.907, 140.745, 562.631, 140.593);
((GeneralPath)shape).lineTo(562.631, 140.593);
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
((GeneralPath)shape).moveTo(564.129, 157.321);
((GeneralPath)shape).curveTo(565.791, 157.176, 567.05304, 155.669, 566.908, 154.009);
((GeneralPath)shape).curveTo(566.754, 152.27701, 565.26, 151.084, 563.594, 151.228);
((GeneralPath)shape).curveTo(561.869, 151.37799, 560.594, 152.816, 560.75, 154.548);
((GeneralPath)shape).curveTo(560.889, 156.208, 562.4, 157.471, 564.129, 157.321);
((GeneralPath)shape).lineTo(564.129, 157.321);
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
// _0_0_0_0_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(565.626, 174.042);
((GeneralPath)shape).curveTo(567.289, 173.89801, 568.55, 172.391, 568.406, 170.73001);
((GeneralPath)shape).curveTo(568.251, 168.99901, 566.758, 167.80501, 565.092, 167.949);
((GeneralPath)shape).curveTo(563.367, 168.1, 562.091, 169.53801, 562.248, 171.26901);
((GeneralPath)shape).curveTo(562.386, 172.93, 563.897, 174.193, 565.626, 174.042);
((GeneralPath)shape).lineTo(565.626, 174.042);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_3;
g.setTransform(defaultTransform__0_0_0_0_3);
g.setClip(clip__0_0_0_0_3);
float alpha__0_0_0_0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_4 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(559.636, 107.14);
((GeneralPath)shape).curveTo(561.3, 106.993996, 562.558, 105.487, 562.41296, 103.826996);
((GeneralPath)shape).curveTo(562.259, 102.093994, 560.76996, 100.899994, 559.10596, 101.044);
((GeneralPath)shape).curveTo(557.36993, 101.195, 556.1, 102.633995, 556.251, 104.365);
((GeneralPath)shape).curveTo(556.396, 106.025, 557.907, 107.29, 559.636, 107.14);
((GeneralPath)shape).lineTo(559.636, 107.14);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_4;
g.setTransform(defaultTransform__0_0_0_0_4);
g.setClip(clip__0_0_0_0_4);
float alpha__0_0_0_0_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_5 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_5 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(558.139, 90.412);
((GeneralPath)shape).curveTo(559.803, 90.267006, 561.057, 88.76, 560.912, 87.099);
((GeneralPath)shape).curveTo(560.76196, 85.368996, 559.265, 84.176, 557.599, 84.32);
((GeneralPath)shape).curveTo(555.873, 84.47, 554.604, 85.909996, 554.752, 87.641);
((GeneralPath)shape).curveTo(554.902, 89.296, 556.407, 90.566, 558.139, 90.412);
((GeneralPath)shape).lineTo(558.139, 90.412);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
((GeneralPath)shape).moveTo(567.366, 131.732);
((GeneralPath)shape).curveTo(569.03406, 131.586, 570.29004, 130.081, 570.142, 128.41899);
((GeneralPath)shape).curveTo(569.99603, 126.68899, 568.49603, 125.49499, 566.83105, 125.64099);
((GeneralPath)shape).curveTo(565.1061, 125.79099, 563.8301, 127.22599, 563.9811, 128.95898);
((GeneralPath)shape).curveTo(564.126, 130.619, 565.639, 131.884, 567.366, 131.732);
((GeneralPath)shape).lineTo(567.366, 131.732);
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
((GeneralPath)shape).moveTo(556.07, 132.722);
((GeneralPath)shape).curveTo(557.734, 132.576, 558.995, 131.069, 558.849, 129.409);
((GeneralPath)shape).curveTo(558.7, 127.67799, 557.2, 126.482994, 555.535, 126.627);
((GeneralPath)shape).curveTo(553.81195, 126.779, 552.542, 128.216, 552.693, 129.948);
((GeneralPath)shape).curveTo(552.834, 131.61, 554.342, 132.873, 556.07, 132.722);
((GeneralPath)shape).lineTo(556.07, 132.722);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6_1;
g.setTransform(defaultTransform__0_0_0_0_6_1);
g.setClip(clip__0_0_0_0_6_1);
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
((GeneralPath)shape).moveTo(568.912, 148.462);
((GeneralPath)shape).curveTo(570.58, 148.31601, 571.83997, 146.81001, 571.691, 145.14801);
((GeneralPath)shape).curveTo(571.54297, 143.41801, 570.04596, 142.22202, 568.378, 142.37001);
((GeneralPath)shape).curveTo(566.653, 142.52, 565.372, 143.95801, 565.52997, 145.69202);
((GeneralPath)shape).curveTo(565.676, 147.347, 567.186, 148.612, 568.912, 148.462);
((GeneralPath)shape).lineTo(568.912, 148.462);
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
((GeneralPath)shape).moveTo(557.617, 149.45);
((GeneralPath)shape).curveTo(559.281, 149.306, 560.544, 147.802, 560.396, 146.14);
((GeneralPath)shape).curveTo(560.242, 144.406, 558.749, 143.213, 557.083, 143.358);
((GeneralPath)shape).curveTo(555.354, 143.509, 554.081, 144.944, 554.235, 146.679);
((GeneralPath)shape).curveTo(554.38, 148.341, 555.887, 149.602, 557.617, 149.45);
((GeneralPath)shape).lineTo(557.617, 149.45);
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
((GeneralPath)shape).moveTo(570.46, 165.187);
((GeneralPath)shape).curveTo(572.125, 165.04399, 573.383, 163.538, 573.23804, 161.87599);
((GeneralPath)shape).curveTo(573.08606, 160.144, 571.588, 158.95, 569.92706, 159.09799);
((GeneralPath)shape).curveTo(568.2001, 159.24599, 566.92303, 160.68399, 567.0751, 162.41599);
((GeneralPath)shape).curveTo(567.217, 164.076, 568.732, 165.34, 570.46, 165.187);
((GeneralPath)shape).lineTo(570.46, 165.187);
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
((GeneralPath)shape).moveTo(559.165, 166.173);
((GeneralPath)shape).curveTo(560.829, 166.035, 562.08997, 164.524, 561.93896, 162.863);
((GeneralPath)shape).curveTo(561.792, 161.13101, 560.29297, 159.93701, 558.631, 160.087);
((GeneralPath)shape).curveTo(556.90295, 160.233, 555.63196, 161.67201, 555.784, 163.404);
((GeneralPath)shape).curveTo(555.928, 165.062, 557.434, 166.328, 559.165, 166.173);
((GeneralPath)shape).lineTo(559.165, 166.173);
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
((GeneralPath)shape).moveTo(565.82, 115.003);
((GeneralPath)shape).curveTo(567.486, 114.856995, 568.749, 113.353, 568.599, 111.689995);
((GeneralPath)shape).curveTo(568.45, 109.95899, 566.957, 108.76499, 565.29, 108.91499);
((GeneralPath)shape).curveTo(563.561, 109.062, 562.285, 110.495995, 562.436, 112.229);
((GeneralPath)shape).curveTo(562.585, 113.891, 564.098, 115.154, 565.82, 115.003);
((GeneralPath)shape).lineTo(565.82, 115.003);
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
((GeneralPath)shape).moveTo(554.523, 115.993);
((GeneralPath)shape).curveTo(556.189, 115.849, 557.45404, 114.340996, 557.306, 112.67999);
((GeneralPath)shape).curveTo(557.15704, 110.94799, 555.66003, 109.75499, 553.992, 109.90099);
((GeneralPath)shape).curveTo(552.265, 110.05299, 550.99603, 111.48799, 551.142, 113.218994);
((GeneralPath)shape).curveTo(551.29, 114.879, 552.8, 116.144, 554.523, 115.993);
((GeneralPath)shape).lineTo(554.523, 115.993);
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
((GeneralPath)shape).moveTo(564.276, 98.277);
((GeneralPath)shape).curveTo(565.938, 98.133, 567.19403, 96.628, 567.049, 94.967);
((GeneralPath)shape).curveTo(566.898, 93.23801, 565.401, 92.043, 563.741, 92.189);
((GeneralPath)shape).curveTo(562.00806, 92.340004, 560.73505, 93.778, 560.887, 95.506004);
((GeneralPath)shape).curveTo(561.04, 97.169, 562.544, 98.433, 564.276, 98.277);
((GeneralPath)shape).lineTo(564.276, 98.277);
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
((GeneralPath)shape).moveTo(552.976, 99.267);
((GeneralPath)shape).curveTo(554.64, 99.123, 555.9, 97.615, 555.75903, 95.957);
((GeneralPath)shape).curveTo(555.606, 94.227, 554.10803, 93.032, 552.439, 93.177);
((GeneralPath)shape).curveTo(550.713, 93.328, 549.442, 94.765, 549.60004, 96.498);
((GeneralPath)shape).curveTo(549.743, 98.158, 551.247, 99.42, 552.976, 99.267);
((GeneralPath)shape).lineTo(552.976, 99.267);
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
        return 550;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 84;
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

