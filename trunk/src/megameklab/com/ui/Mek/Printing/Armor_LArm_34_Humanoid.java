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
public class Armor_LArm_34_Humanoid {
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
// _0_0_0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(415.398, 135.491);
((GeneralPath)shape).curveTo(413.73203, 135.345, 412.474, 133.839, 412.62, 132.179);
((GeneralPath)shape).curveTo(412.76898, 130.446, 414.26898, 129.252, 415.93298, 129.397);
((GeneralPath)shape).curveTo(417.65997, 129.548, 418.93198, 130.985, 418.78198, 132.71901);
((GeneralPath)shape).curveTo(418.637, 134.378, 417.129, 135.643, 415.398, 135.491);
((GeneralPath)shape).lineTo(415.398, 135.491);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0;
g.setTransform(defaultTransform__0_0_0_0);
g.setClip(clip__0_0_0_0);
float alpha__0_0_0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(414.66, 143.962);
((GeneralPath)shape).curveTo(412.993, 143.81801, 411.73, 142.31201, 411.876, 140.651);
((GeneralPath)shape).curveTo(412.031, 138.919, 413.523, 137.725, 415.19, 137.87);
((GeneralPath)shape).curveTo(416.918, 138.021, 418.19, 139.459, 418.038, 141.192);
((GeneralPath)shape).curveTo(417.893, 142.85, 416.383, 144.112, 414.66, 143.962);
((GeneralPath)shape).lineTo(414.66, 143.962);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_1;
g.setTransform(defaultTransform__0_0_0_1);
g.setClip(clip__0_0_0_1);
float alpha__0_0_0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(413.914, 152.434);
((GeneralPath)shape).curveTo(412.254, 152.289, 410.994, 150.78201, 411.138, 149.12201);
((GeneralPath)shape).curveTo(411.287, 147.39001, 412.79, 146.19801, 414.451, 146.34201);
((GeneralPath)shape).curveTo(416.177, 146.49301, 417.44998, 147.93001, 417.297, 149.66202);
((GeneralPath)shape).curveTo(417.156, 151.321, 415.648, 152.586, 413.914, 152.434);
((GeneralPath)shape).lineTo(413.914, 152.434);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_2;
g.setTransform(defaultTransform__0_0_0_2);
g.setClip(clip__0_0_0_2);
float alpha__0_0_0_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(416.141, 127.02);
((GeneralPath)shape).curveTo(414.479, 126.87399, 413.215, 125.368996, 413.35898, 123.709);
((GeneralPath)shape).curveTo(413.51398, 121.975, 415.00598, 120.781, 416.67297, 120.927);
((GeneralPath)shape).curveTo(418.40198, 121.079, 419.67398, 122.514, 419.52097, 124.246);
((GeneralPath)shape).curveTo(419.379, 125.907, 417.867, 127.172, 416.141, 127.02);
((GeneralPath)shape).lineTo(416.141, 127.02);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_3;
g.setTransform(defaultTransform__0_0_0_3);
g.setClip(clip__0_0_0_3);
float alpha__0_0_0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_4 = g.getClip();
AffineTransform defaultTransform__0_0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_4 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(419.104, 93.133);
((GeneralPath)shape).curveTo(417.441, 92.989006, 416.17502, 91.482, 416.325, 89.82);
((GeneralPath)shape).curveTo(416.47702, 88.091, 417.971, 86.895996, 419.638, 87.042);
((GeneralPath)shape).curveTo(421.367, 87.193, 422.64, 88.63, 422.488, 90.361);
((GeneralPath)shape).curveTo(422.341, 92.021, 420.829, 93.283, 419.104, 93.133);
((GeneralPath)shape).lineTo(419.104, 93.133);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_4;
g.setTransform(defaultTransform__0_0_0_4);
g.setClip(clip__0_0_0_4);
float alpha__0_0_0_5 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_5 = g.getClip();
AffineTransform defaultTransform__0_0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_5 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(418.365, 101.604);
((GeneralPath)shape).curveTo(416.69998, 101.46, 415.435, 99.952995, 415.582, 98.293);
((GeneralPath)shape).curveTo(415.731, 96.562, 417.232, 95.367, 418.895, 95.514);
((GeneralPath)shape).curveTo(420.624, 95.664, 421.897, 97.101, 421.74698, 98.832);
((GeneralPath)shape).curveTo(421.6, 100.493, 420.091, 101.756, 418.365, 101.604);
((GeneralPath)shape).lineTo(418.365, 101.604);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_5;
g.setTransform(defaultTransform__0_0_0_5);
g.setClip(clip__0_0_0_5);
float alpha__0_0_0_6 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_6 = g.getClip();
AffineTransform defaultTransform__0_0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_6 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(417.62, 110.076);
((GeneralPath)shape).curveTo(415.958, 109.932, 414.69998, 108.424995, 414.84198, 106.764);
((GeneralPath)shape).curveTo(414.994, 105.035, 416.49197, 103.84, 418.15497, 103.984);
((GeneralPath)shape).curveTo(419.88297, 104.134, 421.15598, 105.573, 421.00497, 107.303);
((GeneralPath)shape).curveTo(420.861, 108.963, 419.352, 110.23, 417.62, 110.076);
((GeneralPath)shape).lineTo(417.62, 110.076);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_6;
g.setTransform(defaultTransform__0_0_0_6);
g.setClip(clip__0_0_0_6);
float alpha__0_0_0_7 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_7 = g.getClip();
AffineTransform defaultTransform__0_0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_7 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(416.879, 118.547);
((GeneralPath)shape).curveTo(415.216, 118.402, 413.957, 116.895996, 414.099, 115.23399);
((GeneralPath)shape).curveTo(414.252, 113.50399, 415.751, 112.30999, 417.413, 112.45599);
((GeneralPath)shape).curveTo(419.14398, 112.607994, 420.415, 114.04299, 420.261, 115.774994);
((GeneralPath)shape).curveTo(420.115, 117.436, 418.611, 118.698, 416.879, 118.547);
((GeneralPath)shape).lineTo(416.879, 118.547);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_7;
g.setTransform(defaultTransform__0_0_0_7);
g.setClip(clip__0_0_0_7);
float alpha__0_0_0_8 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_8 = g.getClip();
AffineTransform defaultTransform__0_0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_8 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(421.416, 131.751);
((GeneralPath)shape).curveTo(419.75198, 131.60501, 418.49298, 130.1, 418.63898, 128.438);
((GeneralPath)shape).curveTo(418.78897, 126.707, 420.287, 125.512, 421.95197, 125.65501);
((GeneralPath)shape).curveTo(423.67798, 125.80801, 424.94797, 127.244, 424.79495, 128.97801);
((GeneralPath)shape).curveTo(424.652, 130.639, 423.148, 131.902, 421.416, 131.751);
((GeneralPath)shape).lineTo(421.416, 131.751);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_8;
g.setTransform(defaultTransform__0_0_0_8);
g.setClip(clip__0_0_0_8);
float alpha__0_0_0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9 = g.getClip();
AffineTransform defaultTransform__0_0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(420.676, 140.22);
((GeneralPath)shape).curveTo(419.01, 140.077, 417.75, 138.571, 417.899, 136.911);
((GeneralPath)shape).curveTo(418.047, 135.18, 419.54398, 133.984, 421.21, 134.129);
((GeneralPath)shape).curveTo(422.939, 134.28099, 424.20798, 135.718, 424.052, 137.451);
((GeneralPath)shape).curveTo(423.91, 139.11, 422.403, 140.372, 420.676, 140.22);
((GeneralPath)shape).lineTo(420.676, 140.22);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_9;
g.setTransform(defaultTransform__0_0_0_9);
g.setClip(clip__0_0_0_9);
float alpha__0_0_0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_10 = g.getClip();
AffineTransform defaultTransform__0_0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_10 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(419.935, 148.69);
((GeneralPath)shape).curveTo(418.274, 148.545, 417.011, 147.04001, 417.154, 145.379);
((GeneralPath)shape).curveTo(417.309, 143.648, 418.804, 142.453, 420.46698, 142.59999);
((GeneralPath)shape).curveTo(422.19397, 142.74998, 423.46698, 144.187, 423.31097, 145.91899);
((GeneralPath)shape).curveTo(423.169, 147.577, 421.665, 148.842, 419.935, 148.69);
((GeneralPath)shape).lineTo(419.935, 148.69);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_10;
g.setTransform(defaultTransform__0_0_0_10);
g.setClip(clip__0_0_0_10);
float alpha__0_0_0_11 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_11 = g.getClip();
AffineTransform defaultTransform__0_0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_11 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(419.192, 157.163);
((GeneralPath)shape).curveTo(417.52698, 157.017, 416.271, 155.50899, 416.41498, 153.851);
((GeneralPath)shape).curveTo(416.56497, 152.117, 418.06396, 150.927, 419.72797, 151.071);
((GeneralPath)shape).curveTo(421.45398, 151.221, 422.72296, 152.657, 422.56998, 154.39);
((GeneralPath)shape).curveTo(422.429, 156.05, 420.923, 157.314, 419.192, 157.163);
((GeneralPath)shape).lineTo(419.192, 157.163);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_11;
g.setTransform(defaultTransform__0_0_0_11);
g.setClip(clip__0_0_0_11);
float alpha__0_0_0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_12 = g.getClip();
AffineTransform defaultTransform__0_0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_12 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(418.452, 165.634);
((GeneralPath)shape).curveTo(416.787, 165.49, 415.529, 163.98401, 415.672, 162.324);
((GeneralPath)shape).curveTo(415.825, 160.59001, 417.322, 159.40001, 418.986, 159.543);
((GeneralPath)shape).curveTo(420.711, 159.69499, 421.981, 161.13, 421.828, 162.866);
((GeneralPath)shape).curveTo(421.687, 164.523, 420.182, 165.786, 418.452, 165.634);
((GeneralPath)shape).lineTo(418.452, 165.634);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_12;
g.setTransform(defaultTransform__0_0_0_12);
g.setClip(clip__0_0_0_12);
float alpha__0_0_0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_13 = g.getClip();
AffineTransform defaultTransform__0_0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_13 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(417.711, 174.105);
((GeneralPath)shape).curveTo(416.046, 173.961, 414.788, 172.455, 414.931, 170.795);
((GeneralPath)shape).curveTo(415.084, 169.061, 416.58, 167.871, 418.245, 168.01399);
((GeneralPath)shape).curveTo(419.971, 168.16599, 421.24, 169.601, 421.086, 171.336);
((GeneralPath)shape).curveTo(420.945, 172.995, 419.44, 174.258, 417.711, 174.105);
((GeneralPath)shape).lineTo(417.711, 174.105);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_13;
g.setTransform(defaultTransform__0_0_0_13);
g.setClip(clip__0_0_0_13);
float alpha__0_0_0_14 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_14 = g.getClip();
AffineTransform defaultTransform__0_0_0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_14 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(422.158, 123.276);
((GeneralPath)shape).curveTo(420.496, 123.132, 419.233, 121.626, 419.37698, 119.966);
((GeneralPath)shape).curveTo(419.53098, 118.236, 421.025, 117.041, 422.69098, 117.186005);
((GeneralPath)shape).curveTo(424.41797, 117.337006, 425.68698, 118.774, 425.537, 120.505005);
((GeneralPath)shape).curveTo(425.394, 122.166, 423.884, 123.428, 422.158, 123.276);
((GeneralPath)shape).lineTo(422.158, 123.276);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_14;
g.setTransform(defaultTransform__0_0_0_14);
g.setClip(clip__0_0_0_14);
float alpha__0_0_0_15 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_15 = g.getClip();
AffineTransform defaultTransform__0_0_0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_15 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(424.385, 97.861);
((GeneralPath)shape).curveTo(422.71902, 97.718, 421.45502, 96.211, 421.6, 94.55);
((GeneralPath)shape).curveTo(421.75, 92.82, 423.25, 91.624, 424.911, 91.772);
((GeneralPath)shape).curveTo(426.643, 91.920006, 427.91302, 93.357, 427.763, 95.089005);
((GeneralPath)shape).curveTo(427.615, 96.749, 426.111, 98.014, 424.385, 97.861);
((GeneralPath)shape).lineTo(424.385, 97.861);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_15;
g.setTransform(defaultTransform__0_0_0_15);
g.setClip(clip__0_0_0_15);
float alpha__0_0_0_16 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_16 = g.getClip();
AffineTransform defaultTransform__0_0_0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_16 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(425.125, 89.391);
((GeneralPath)shape).curveTo(423.459, 89.247, 422.195, 87.739, 422.342, 86.079);
((GeneralPath)shape).curveTo(422.49002, 84.347, 423.99402, 83.154, 425.652, 83.301);
((GeneralPath)shape).curveTo(427.385, 83.451004, 428.651, 84.886, 428.505, 86.619);
((GeneralPath)shape).curveTo(428.354, 88.279, 426.854, 89.542, 425.125, 89.391);
((GeneralPath)shape).lineTo(425.125, 89.391);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_16;
g.setTransform(defaultTransform__0_0_0_16);
g.setClip(clip__0_0_0_16);
float alpha__0_0_0_17 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_17 = g.getClip();
AffineTransform defaultTransform__0_0_0_17 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_17 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(423.641, 106.336);
((GeneralPath)shape).curveTo(421.973, 106.189995, 420.71698, 104.682, 420.86, 103.026);
((GeneralPath)shape).curveTo(421.012, 101.293, 422.50998, 100.098, 424.17398, 100.241);
((GeneralPath)shape).curveTo(425.90298, 100.394, 427.16998, 101.832, 427.02, 103.562);
((GeneralPath)shape).curveTo(426.876, 105.222, 425.365, 106.486, 423.641, 106.336);
((GeneralPath)shape).lineTo(423.641, 106.336);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_17;
g.setTransform(defaultTransform__0_0_0_17);
g.setClip(clip__0_0_0_17);
float alpha__0_0_0_18 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_18 = g.getClip();
AffineTransform defaultTransform__0_0_0_18 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_18 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(422.896, 114.806);
((GeneralPath)shape).curveTo(421.233, 114.659, 419.973, 113.153, 420.119, 111.494);
((GeneralPath)shape).curveTo(420.27298, 109.764, 421.76898, 108.568, 423.43298, 108.714005);
((GeneralPath)shape).curveTo(425.163, 108.865005, 426.429, 110.303, 426.279, 112.034004);
((GeneralPath)shape).curveTo(426.129, 113.691, 424.628, 114.957, 422.896, 114.806);
((GeneralPath)shape).lineTo(422.896, 114.806);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_18;
g.setTransform(defaultTransform__0_0_0_18);
g.setClip(clip__0_0_0_18);
float alpha__0_0_0_19 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_19 = g.getClip();
AffineTransform defaultTransform__0_0_0_19 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_19 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(426.693, 136.479);
((GeneralPath)shape).curveTo(425.029, 136.336, 423.764, 134.83, 423.91, 133.169);
((GeneralPath)shape).curveTo(424.06, 131.43901, 425.565, 130.24301, 427.228, 130.386);
((GeneralPath)shape).curveTo(428.954, 130.539, 430.22, 131.976, 430.068, 133.711);
((GeneralPath)shape).curveTo(429.928, 135.369, 428.423, 136.631, 426.693, 136.479);
((GeneralPath)shape).lineTo(426.693, 136.479);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_19;
g.setTransform(defaultTransform__0_0_0_19);
g.setClip(clip__0_0_0_19);
float alpha__0_0_0_20 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_20 = g.getClip();
AffineTransform defaultTransform__0_0_0_20 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_20 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(425.955, 144.95);
((GeneralPath)shape).curveTo(424.291, 144.805, 423.02298, 143.297, 423.16898, 141.638);
((GeneralPath)shape).curveTo(423.32397, 139.907, 424.822, 138.713, 426.485, 138.858);
((GeneralPath)shape).curveTo(428.21698, 139.009, 429.48398, 140.447, 429.32797, 142.177);
((GeneralPath)shape).curveTo(429.189, 143.838, 427.682, 145.1, 425.955, 144.95);
((GeneralPath)shape).lineTo(425.955, 144.95);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_20;
g.setTransform(defaultTransform__0_0_0_20);
g.setClip(clip__0_0_0_20);
float alpha__0_0_0_21 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_21 = g.getClip();
AffineTransform defaultTransform__0_0_0_21 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_21 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(425.209, 153.421);
((GeneralPath)shape).curveTo(423.55002, 153.279, 422.285, 151.77101, 422.42902, 150.11);
((GeneralPath)shape).curveTo(422.578, 148.379, 424.08502, 147.185, 425.74503, 147.33);
((GeneralPath)shape).curveTo(427.47302, 147.481, 428.73804, 148.918, 428.588, 150.65001);
((GeneralPath)shape).curveTo(428.445, 152.31, 426.941, 153.574, 425.209, 153.421);
((GeneralPath)shape).lineTo(425.209, 153.421);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_21;
g.setTransform(defaultTransform__0_0_0_21);
g.setClip(clip__0_0_0_21);
float alpha__0_0_0_22 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_22 = g.getClip();
AffineTransform defaultTransform__0_0_0_22 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_22 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(413.174, 160.904);
((GeneralPath)shape).curveTo(411.51202, 160.759, 410.252, 159.251, 410.397, 157.593);
((GeneralPath)shape).curveTo(410.547, 155.86101, 412.048, 154.669, 413.71, 154.814);
((GeneralPath)shape).curveTo(415.43698, 154.963, 416.71, 156.4, 416.559, 158.133);
((GeneralPath)shape).curveTo(416.414, 159.791, 414.904, 161.057, 413.174, 160.904);
((GeneralPath)shape).lineTo(413.174, 160.904);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_22;
g.setTransform(defaultTransform__0_0_0_22);
g.setClip(clip__0_0_0_22);
float alpha__0_0_0_23 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_23 = g.getClip();
AffineTransform defaultTransform__0_0_0_23 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_23 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(424.469, 161.892);
((GeneralPath)shape).curveTo(422.807, 161.75, 421.543, 160.241, 421.688, 158.58);
((GeneralPath)shape).curveTo(421.83798, 156.849, 423.343, 155.656, 425.00497, 155.80301);
((GeneralPath)shape).curveTo(426.73196, 155.95401, 427.998, 157.389, 427.84598, 159.121);
((GeneralPath)shape).curveTo(427.705, 160.78, 426.201, 162.045, 424.469, 161.892);
((GeneralPath)shape).lineTo(424.469, 161.892);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_23;
g.setTransform(defaultTransform__0_0_0_23);
g.setClip(clip__0_0_0_23);
float alpha__0_0_0_24 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_24 = g.getClip();
AffineTransform defaultTransform__0_0_0_24 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_24 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(412.434, 169.376);
((GeneralPath)shape).curveTo(410.77, 169.231, 409.511, 167.72401, 409.65698, 166.065);
((GeneralPath)shape).curveTo(409.805, 164.332, 411.30698, 163.14, 412.96997, 163.285);
((GeneralPath)shape).curveTo(414.69498, 163.435, 415.96796, 164.871, 415.81796, 166.604);
((GeneralPath)shape).curveTo(415.672, 168.264, 414.164, 169.528, 412.434, 169.376);
((GeneralPath)shape).lineTo(412.434, 169.376);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_24;
g.setTransform(defaultTransform__0_0_0_24);
g.setClip(clip__0_0_0_24);
float alpha__0_0_0_25 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_25 = g.getClip();
AffineTransform defaultTransform__0_0_0_25 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_25 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(423.729, 170.363);
((GeneralPath)shape).curveTo(422.06702, 170.21901, 420.802, 168.71101, 420.948, 167.052);
((GeneralPath)shape).curveTo(421.096, 165.32, 422.602, 164.126, 424.263, 164.273);
((GeneralPath)shape).curveTo(425.992, 164.42299, 427.256, 165.859, 427.106, 167.592);
((GeneralPath)shape).curveTo(426.965, 169.251, 425.459, 170.517, 423.729, 170.363);
((GeneralPath)shape).lineTo(423.729, 170.363);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_25;
g.setTransform(defaultTransform__0_0_0_25);
g.setClip(clip__0_0_0_25);
float alpha__0_0_0_26 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_26 = g.getClip();
AffineTransform defaultTransform__0_0_0_26 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_26 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(427.436, 128.007);
((GeneralPath)shape).curveTo(425.776, 127.861, 424.506, 126.356, 424.64902, 124.693);
((GeneralPath)shape).curveTo(424.807, 122.963, 426.30203, 121.769, 427.967, 121.917);
((GeneralPath)shape).curveTo(429.696, 122.068, 430.963, 123.502, 430.814, 125.235);
((GeneralPath)shape).curveTo(430.67, 126.895, 429.166, 128.158, 427.436, 128.007);
((GeneralPath)shape).lineTo(427.436, 128.007);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_26;
g.setTransform(defaultTransform__0_0_0_26);
g.setClip(clip__0_0_0_26);
float alpha__0_0_0_27 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_27 = g.getClip();
AffineTransform defaultTransform__0_0_0_27 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_27 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(430.4, 94.122);
((GeneralPath)shape).curveTo(428.735, 93.976, 427.46698, 92.469, 427.619, 90.809);
((GeneralPath)shape).curveTo(427.76898, 89.077995, 429.26398, 87.884995, 430.93198, 88.029);
((GeneralPath)shape).curveTo(432.662, 88.179, 433.93097, 89.618996, 433.77698, 91.349);
((GeneralPath)shape).curveTo(433.632, 93.01, 432.126, 94.271, 430.4, 94.122);
((GeneralPath)shape).lineTo(430.4, 94.122);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_27;
g.setTransform(defaultTransform__0_0_0_27);
g.setClip(clip__0_0_0_27);
float alpha__0_0_0_28 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_28 = g.getClip();
AffineTransform defaultTransform__0_0_0_28 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_28 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(431.141, 85.649);
((GeneralPath)shape).curveTo(429.47598, 85.504005, 428.211, 83.996, 428.361, 82.337006);
((GeneralPath)shape).curveTo(428.50998, 80.607, 430.007, 79.41401, 431.67398, 79.55701);
((GeneralPath)shape).curveTo(433.40298, 79.70801, 434.67197, 81.147, 434.51797, 82.878006);
((GeneralPath)shape).curveTo(434.372, 84.539, 432.869, 85.799, 431.141, 85.649);
((GeneralPath)shape).lineTo(431.141, 85.649);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_28;
g.setTransform(defaultTransform__0_0_0_28);
g.setClip(clip__0_0_0_28);
float alpha__0_0_0_29 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_29 = g.getClip();
AffineTransform defaultTransform__0_0_0_29 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_29 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(429.661, 102.593);
((GeneralPath)shape).curveTo(427.99802, 102.449005, 426.73, 100.941, 426.875, 99.28);
((GeneralPath)shape).curveTo(427.023, 97.551, 428.531, 96.354996, 430.193, 96.502);
((GeneralPath)shape).curveTo(431.922, 96.651, 433.188, 98.088, 433.038, 99.822);
((GeneralPath)shape).curveTo(432.892, 101.48, 431.389, 102.742, 429.661, 102.593);
((GeneralPath)shape).lineTo(429.661, 102.593);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_29;
g.setTransform(defaultTransform__0_0_0_29);
g.setClip(clip__0_0_0_29);
float alpha__0_0_0_30 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_30 = g.getClip();
AffineTransform defaultTransform__0_0_0_30 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_30 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(428.915, 111.064);
((GeneralPath)shape).curveTo(427.255, 110.91901, 425.991, 109.413, 426.133, 107.751);
((GeneralPath)shape).curveTo(426.289, 106.020996, 427.787, 104.826996, 429.451, 104.972);
((GeneralPath)shape).curveTo(431.18, 105.122, 432.44897, 106.56, 432.296, 108.291);
((GeneralPath)shape).curveTo(432.152, 109.95, 430.646, 111.217, 428.915, 111.064);
((GeneralPath)shape).lineTo(428.915, 111.064);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_30;
g.setTransform(defaultTransform__0_0_0_30);
g.setClip(clip__0_0_0_30);
float alpha__0_0_0_31 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_31 = g.getClip();
AffineTransform defaultTransform__0_0_0_31 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_31 is CompositeGraphicsNode
float alpha__0_0_0_31_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_31_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_31_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_31_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(435.676, 98.852);
((GeneralPath)shape).curveTo(434.013, 98.708, 432.745, 97.2, 432.88998, 95.538994);
((GeneralPath)shape).curveTo(433.038, 93.81, 434.546, 92.61399, 436.20798, 92.760994);
((GeneralPath)shape).curveTo(437.93698, 92.909996, 439.20297, 94.34699, 439.05298, 96.08099);
((GeneralPath)shape).curveTo(438.906, 97.739, 437.403, 99.001, 435.676, 98.852);
((GeneralPath)shape).lineTo(435.676, 98.852);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_31_0;
g.setTransform(defaultTransform__0_0_0_31_0);
g.setClip(clip__0_0_0_31_0);
float alpha__0_0_0_31_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_31_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_31_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_31_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(434.93, 107.323);
((GeneralPath)shape).curveTo(433.27, 107.178, 432.00598, 105.672, 432.14798, 104.009995);
((GeneralPath)shape).curveTo(432.304, 102.27999, 433.80197, 101.08599, 435.46597, 101.230995);
((GeneralPath)shape).curveTo(437.19498, 101.381, 438.46396, 102.81899, 438.31097, 104.549995);
((GeneralPath)shape).curveTo(438.167, 106.209, 436.661, 107.476, 434.93, 107.323);
((GeneralPath)shape).lineTo(434.93, 107.323);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_31_1;
g.setTransform(defaultTransform__0_0_0_31_1);
g.setClip(clip__0_0_0_31_1);
origAlpha = alpha__0_0_0_31;
g.setTransform(defaultTransform__0_0_0_31);
g.setClip(clip__0_0_0_31);
float alpha__0_0_0_32 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_32 = g.getClip();
AffineTransform defaultTransform__0_0_0_32 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_32 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(428.176, 119.536);
((GeneralPath)shape).curveTo(426.51498, 119.39101, 425.25, 117.885, 425.39398, 116.225006);
((GeneralPath)shape).curveTo(425.54398, 114.495, 427.04797, 113.299, 428.71198, 113.44301);
((GeneralPath)shape).curveTo(430.44098, 113.59601, 431.70798, 115.03401, 431.55197, 116.76501);
((GeneralPath)shape).curveTo(431.407, 118.426, 429.906, 119.689, 428.176, 119.536);
((GeneralPath)shape).lineTo(428.176, 119.536);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_32;
g.setTransform(defaultTransform__0_0_0_32);
g.setClip(clip__0_0_0_32);
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
        return 410;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 80;
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

