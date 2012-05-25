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
public class Armor_RLeg_40_Humanoid {
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
((GeneralPath)shape).moveTo(515.919, 203.726);
((GeneralPath)shape).curveTo(517.48, 203.333, 518.44403, 201.74, 518.031, 200.107);
((GeneralPath)shape).curveTo(517.645, 198.545, 516.052, 197.58499, 514.485, 197.976);
((GeneralPath)shape).curveTo(512.85297, 198.38, 511.896, 199.977, 512.287, 201.54);
((GeneralPath)shape).curveTo(512.692, 203.171, 514.292, 204.132, 515.919, 203.726);
((GeneralPath)shape).lineTo(515.919, 203.726);
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
((GeneralPath)shape).moveTo(518.021, 212.191);
((GeneralPath)shape).curveTo(519.584, 211.799, 520.542, 210.202, 520.137, 208.56999);
((GeneralPath)shape).curveTo(519.744, 207.008, 518.153, 206.049, 516.58905, 206.441);
((GeneralPath)shape).curveTo(514.95605, 206.844, 513.99805, 208.442, 514.387, 210.004);
((GeneralPath)shape).curveTo(514.795, 211.636, 516.396, 212.597, 518.021, 212.191);
((GeneralPath)shape).lineTo(518.021, 212.191);
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
((GeneralPath)shape).moveTo(520.128, 220.658);
((GeneralPath)shape).curveTo(521.68896, 220.268, 522.651, 218.67, 522.245, 217.03801);
((GeneralPath)shape).curveTo(521.856, 215.47601, 520.257, 214.51701, 518.693, 214.906);
((GeneralPath)shape).curveTo(517.065, 215.313, 516.104, 216.91101, 516.49097, 218.47101);
((GeneralPath)shape).curveTo(516.902, 220.104, 518.499, 221.064, 520.128, 220.658);
((GeneralPath)shape).lineTo(520.128, 220.658);
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
((GeneralPath)shape).moveTo(522.233, 229.122);
((GeneralPath)shape).curveTo(523.798, 228.73299, 524.75397, 227.138, 524.347, 225.502);
((GeneralPath)shape).curveTo(523.959, 223.942, 522.36, 222.983, 520.797, 223.373);
((GeneralPath)shape).curveTo(519.16797, 223.78, 518.211, 225.37401, 518.598, 226.935);
((GeneralPath)shape).curveTo(519.005, 228.571, 520.599, 229.528, 522.233, 229.122);
((GeneralPath)shape).lineTo(522.233, 229.122);
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
((GeneralPath)shape).moveTo(524.333, 237.589);
((GeneralPath)shape).curveTo(525.894, 237.201, 526.856, 235.60501, 526.452, 233.96901);
((GeneralPath)shape).curveTo(526.05804, 232.406, 524.46405, 231.45001, 522.901, 231.83801);
((GeneralPath)shape).curveTo(521.271, 232.24602, 520.309, 233.83902, 520.7, 235.40302);
((GeneralPath)shape).curveTo(521.105, 237.036, 522.704, 237.997, 524.333, 237.589);
((GeneralPath)shape).lineTo(524.333, 237.589);
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
((GeneralPath)shape).moveTo(526.443, 246.053);
((GeneralPath)shape).curveTo(528.002, 245.66399, 528.963, 244.069, 528.553, 242.433);
((GeneralPath)shape).curveTo(528.16, 240.874, 526.565, 239.914, 525.00397, 240.305);
((GeneralPath)shape).curveTo(523.376, 240.709, 522.415, 242.308, 522.805, 243.86899);
((GeneralPath)shape).curveTo(523.212, 245.501, 524.808, 246.461, 526.443, 246.053);
((GeneralPath)shape).lineTo(526.443, 246.053);
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
// _0_0_0_0_6 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(528.546, 254.52);
((GeneralPath)shape).curveTo(530.11005, 254.128, 531.06903, 252.531, 530.658, 250.901);
((GeneralPath)shape).curveTo(530.267, 249.338, 528.672, 248.378, 527.11005, 248.77);
((GeneralPath)shape).curveTo(525.478, 249.173, 524.51904, 250.77301, 524.9111, 252.33301);
((GeneralPath)shape).curveTo(525.315, 253.968, 526.913, 254.927, 528.546, 254.52);
((GeneralPath)shape).lineTo(528.546, 254.52);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_6;
g.setTransform(defaultTransform__0_0_0_0_6);
g.setClip(clip__0_0_0_0_6);
float alpha__0_0_0_0_7 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_7 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_7 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(530.65, 262.985);
((GeneralPath)shape).curveTo(532.21204, 262.593, 533.171, 260.998, 532.763, 259.36798);
((GeneralPath)shape).curveTo(532.369, 257.804, 530.775, 256.84497, 529.211, 257.236);
((GeneralPath)shape).curveTo(527.583, 257.63998, 526.622, 259.24, 527.013, 260.79898);
((GeneralPath)shape).curveTo(527.419, 262.433, 529.017, 263.392, 530.65, 262.985);
((GeneralPath)shape).lineTo(530.65, 262.985);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_7;
g.setTransform(defaultTransform__0_0_0_0_7);
g.setClip(clip__0_0_0_0_7);
float alpha__0_0_0_0_8 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_8 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_8 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(532.756, 271.45);
((GeneralPath)shape).curveTo(534.32, 271.06, 535.27496, 269.46503, 534.86395, 267.83102);
((GeneralPath)shape).curveTo(534.4769, 266.26804, 532.881, 265.31003, 531.319, 265.70203);
((GeneralPath)shape).curveTo(529.688, 266.10303, 528.72894, 267.70502, 529.11896, 269.265);
((GeneralPath)shape).curveTo(529.524, 270.897, 531.122, 271.856, 532.756, 271.45);
((GeneralPath)shape).lineTo(532.756, 271.45);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_8;
g.setTransform(defaultTransform__0_0_0_0_8);
g.setClip(clip__0_0_0_0_8);
float alpha__0_0_0_0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_9 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_9 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(534.857, 279.917);
((GeneralPath)shape).curveTo(536.419, 279.524, 537.37897, 277.931, 536.973, 276.298);
((GeneralPath)shape).curveTo(536.57904, 274.736, 534.98505, 273.779, 533.418, 274.167);
((GeneralPath)shape).curveTo(531.793, 274.57, 530.827, 276.173, 531.21906, 277.72998);
((GeneralPath)shape).curveTo(531.626, 279.363, 533.228, 280.323, 534.857, 279.917);
((GeneralPath)shape).lineTo(534.857, 279.917);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_9;
g.setTransform(defaultTransform__0_0_0_0_9);
g.setClip(clip__0_0_0_0_9);
float alpha__0_0_0_0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_10 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_10 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(536.963, 288.381);
((GeneralPath)shape).curveTo(538.526, 287.993, 539.484, 286.39603, 539.075, 284.764);
((GeneralPath)shape).curveTo(538.684, 283.203, 537.091, 282.245, 535.524, 282.634);
((GeneralPath)shape).curveTo(533.897, 283.039, 532.935, 284.637, 533.32697, 286.198);
((GeneralPath)shape).curveTo(533.735, 287.831, 535.331, 288.791, 536.963, 288.381);
((GeneralPath)shape).lineTo(536.963, 288.381);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_10;
g.setTransform(defaultTransform__0_0_0_0_10);
g.setClip(clip__0_0_0_0_10);
float alpha__0_0_0_0_11 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_11 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_11 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(539.067, 296.847);
((GeneralPath)shape).curveTo(540.629, 296.45798, 541.589, 294.861, 541.18, 293.22897);
((GeneralPath)shape).curveTo(540.79, 291.66696, 539.193, 290.70798, 537.62897, 291.09998);
((GeneralPath)shape).curveTo(536.001, 291.503, 535.03796, 293.09998, 535.42694, 294.66296);
((GeneralPath)shape).curveTo(535.837, 296.296, 537.434, 297.256, 539.067, 296.847);
((GeneralPath)shape).lineTo(539.067, 296.847);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_11;
g.setTransform(defaultTransform__0_0_0_0_11);
g.setClip(clip__0_0_0_0_11);
float alpha__0_0_0_0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_12 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_12 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(527.335, 201.091);
((GeneralPath)shape).curveTo(528.892, 200.698, 529.856, 199.104, 529.44403, 197.472);
((GeneralPath)shape).curveTo(529.056, 195.909, 527.46405, 194.948, 525.9, 195.339);
((GeneralPath)shape).curveTo(524.268, 195.74301, 523.307, 197.34401, 523.7, 198.90201);
((GeneralPath)shape).curveTo(524.104, 200.536, 525.703, 201.497, 527.335, 201.091);
((GeneralPath)shape).lineTo(527.335, 201.091);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_12;
g.setTransform(defaultTransform__0_0_0_0_12);
g.setClip(clip__0_0_0_0_12);
float alpha__0_0_0_0_13 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_13 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_13 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(513.956, 195.228);
((GeneralPath)shape).curveTo(515.518, 194.83499, 516.481, 193.241, 516.068, 191.608);
((GeneralPath)shape).curveTo(515.682, 190.045, 514.088, 189.085, 512.524, 189.477);
((GeneralPath)shape).curveTo(510.891, 189.88101, 509.934, 191.47801, 510.323, 193.041);
((GeneralPath)shape).curveTo(510.729, 194.673, 512.328, 195.633, 513.956, 195.228);
((GeneralPath)shape).lineTo(513.956, 195.228);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
((GeneralPath)shape).moveTo(525.373, 192.592);
((GeneralPath)shape).curveTo(526.93097, 192.2, 527.894, 190.607, 527.483, 188.97299);
((GeneralPath)shape).curveTo(527.094, 187.411, 525.503, 186.44899, 523.938, 186.84099);
((GeneralPath)shape).curveTo(522.30597, 187.245, 521.346, 188.844, 521.739, 190.40399);
((GeneralPath)shape).curveTo(522.142, 192.038, 523.742, 192.999, 525.373, 192.592);
((GeneralPath)shape).lineTo(525.373, 192.592);
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
((GeneralPath)shape).moveTo(529.438, 209.556);
((GeneralPath)shape).curveTo(530.998, 209.165, 531.959, 207.571, 531.552, 205.936);
((GeneralPath)shape).curveTo(531.161, 204.373, 529.566, 203.417, 528.004, 203.806);
((GeneralPath)shape).curveTo(526.37305, 204.212, 525.413, 205.81, 525.80304, 207.37);
((GeneralPath)shape).curveTo(526.209, 209.001, 527.811, 209.961, 529.438, 209.556);
((GeneralPath)shape).lineTo(529.438, 209.556);
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
((GeneralPath)shape).moveTo(531.542, 218.022);
((GeneralPath)shape).curveTo(533.101, 217.633, 534.06396, 216.03601, 533.657, 214.401);
((GeneralPath)shape).curveTo(533.268, 212.838, 531.669, 211.882, 530.10596, 212.27);
((GeneralPath)shape).curveTo(528.475, 212.67801, 527.51794, 214.276, 527.90295, 215.834);
((GeneralPath)shape).curveTo(528.318, 217.47, 529.911, 218.428, 531.542, 218.022);
((GeneralPath)shape).lineTo(531.542, 218.022);
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
// _0_0_0_0_17 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(533.647, 226.487);
((GeneralPath)shape).curveTo(535.20996, 226.094, 536.16797, 224.501, 535.76196, 222.866);
((GeneralPath)shape).curveTo(535.36993, 221.305, 533.77496, 220.345, 532.20795, 220.73799);
((GeneralPath)shape).curveTo(530.57996, 221.14299, 529.621, 222.73698, 530.0109, 224.29799);
((GeneralPath)shape).curveTo(530.417, 225.937, 532.015, 226.893, 533.647, 226.487);
((GeneralPath)shape).lineTo(533.647, 226.487);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_17;
g.setTransform(defaultTransform__0_0_0_0_17);
g.setClip(clip__0_0_0_0_17);
float alpha__0_0_0_0_18 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_18 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_18 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_18 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(535.749, 234.955);
((GeneralPath)shape).curveTo(537.309, 234.563, 538.27, 232.968, 537.866, 231.335);
((GeneralPath)shape).curveTo(537.473, 229.774, 535.879, 228.81401, 534.315, 229.20201);
((GeneralPath)shape).curveTo(532.684, 229.60901, 531.724, 231.20302, 532.116, 232.766);
((GeneralPath)shape).curveTo(532.521, 234.402, 534.118, 235.361, 535.749, 234.955);
((GeneralPath)shape).lineTo(535.749, 234.955);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_18;
g.setTransform(defaultTransform__0_0_0_0_18);
g.setClip(clip__0_0_0_0_18);
float alpha__0_0_0_0_19 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_19 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_19 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_19 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(537.856, 243.417);
((GeneralPath)shape).curveTo(539.418, 243.03001, 540.377, 241.43501, 539.96704, 239.79901);
((GeneralPath)shape).curveTo(539.57806, 238.23901, 537.98004, 237.279, 536.41907, 237.67001);
((GeneralPath)shape).curveTo(534.79205, 238.07501, 533.82605, 239.67502, 534.21906, 241.23401);
((GeneralPath)shape).curveTo(534.628, 242.867, 536.223, 243.828, 537.856, 243.417);
((GeneralPath)shape).lineTo(537.856, 243.417);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
((GeneralPath)shape).moveTo(539.959, 251.885);
((GeneralPath)shape).curveTo(541.522, 251.495, 542.48, 249.899, 542.07196, 248.267);
((GeneralPath)shape).curveTo(541.67993, 246.704, 540.084, 245.746, 538.52094, 246.135);
((GeneralPath)shape).curveTo(536.8919, 246.539, 535.93396, 248.14, 536.32495, 249.698);
((GeneralPath)shape).curveTo(536.729, 251.333, 538.329, 252.294, 539.959, 251.885);
((GeneralPath)shape).lineTo(539.959, 251.885);
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
((GeneralPath)shape).moveTo(542.064, 260.349);
((GeneralPath)shape).curveTo(543.624, 259.95898, 544.585, 258.364, 544.17303, 256.733);
((GeneralPath)shape).curveTo(543.783, 255.169, 542.184, 254.209, 540.624, 254.602);
((GeneralPath)shape).curveTo(538.99603, 255.005, 538.03705, 256.60602, 538.42505, 258.164);
((GeneralPath)shape).curveTo(538.834, 259.797, 540.432, 260.756, 542.064, 260.349);
((GeneralPath)shape).lineTo(542.064, 260.349);
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
((GeneralPath)shape).moveTo(544.171, 268.814);
((GeneralPath)shape).curveTo(545.734, 268.42398, 546.689, 266.828, 546.28204, 265.19598);
((GeneralPath)shape).curveTo(545.88904, 263.633, 544.29205, 262.675, 542.73303, 263.06497);
((GeneralPath)shape).curveTo(541.101, 263.46997, 540.13904, 265.06897, 540.53204, 266.62796);
((GeneralPath)shape).curveTo(540.94, 268.262, 542.536, 269.222, 544.171, 268.814);
((GeneralPath)shape).lineTo(544.171, 268.814);
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
// _0_0_0_0_23 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(546.272, 277.28);
((GeneralPath)shape).curveTo(547.831, 276.88998, 548.792, 275.293, 548.38495, 273.663);
((GeneralPath)shape).curveTo(547.9899, 272.1, 546.3989, 271.141, 544.8339, 271.53198);
((GeneralPath)shape).curveTo(543.20294, 271.93698, 542.24194, 273.53598, 542.63495, 275.09497);
((GeneralPath)shape).curveTo(543.042, 276.728, 544.638, 277.69, 546.272, 277.28);
((GeneralPath)shape).lineTo(546.272, 277.28);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_23;
g.setTransform(defaultTransform__0_0_0_0_23);
g.setClip(clip__0_0_0_0_23);
float alpha__0_0_0_0_24 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_24 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_24 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_24 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(548.376, 285.746);
((GeneralPath)shape).curveTo(549.938, 285.359, 550.899, 283.762, 550.48895, 282.129);
((GeneralPath)shape).curveTo(550.10095, 280.567, 548.5029, 279.61, 546.93994, 279.998);
((GeneralPath)shape).curveTo(545.3129, 280.40598, 544.34796, 282.0, 544.74097, 283.56097);
((GeneralPath)shape).curveTo(545.148, 285.195, 546.743, 286.157, 548.376, 285.746);
((GeneralPath)shape).lineTo(548.376, 285.746);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_24;
g.setTransform(defaultTransform__0_0_0_0_24);
g.setClip(clip__0_0_0_0_24);
float alpha__0_0_0_0_25 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_25 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_25 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_25 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(550.479, 294.211);
((GeneralPath)shape).curveTo(552.042, 293.822, 552.998, 292.227, 552.591, 290.594);
((GeneralPath)shape).curveTo(552.202, 289.031, 550.606, 288.07498, 549.042, 288.465);
((GeneralPath)shape).curveTo(547.41296, 288.868, 546.453, 290.465, 546.844, 292.027);
((GeneralPath)shape).curveTo(547.253, 293.661, 548.849, 294.621, 550.479, 294.211);
((GeneralPath)shape).lineTo(550.479, 294.211);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_25;
g.setTransform(defaultTransform__0_0_0_0_25);
g.setClip(clip__0_0_0_0_25);
float alpha__0_0_0_0_26 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_26 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_26 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_26 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(522.68, 206.642);
((GeneralPath)shape).curveTo(524.24, 206.25099, 525.203, 204.656, 524.78796, 203.023);
((GeneralPath)shape).curveTo(524.40497, 201.459, 522.80896, 200.5, 521.24194, 200.89);
((GeneralPath)shape).curveTo(519.61395, 201.296, 518.65497, 202.894, 519.048, 204.454);
((GeneralPath)shape).curveTo(519.448, 206.089, 521.049, 207.047, 522.68, 206.642);
((GeneralPath)shape).lineTo(522.68, 206.642);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_26;
g.setTransform(defaultTransform__0_0_0_0_26);
g.setClip(clip__0_0_0_0_26);
float alpha__0_0_0_0_27 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_27 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_27 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_27 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(524.642, 215.139);
((GeneralPath)shape).curveTo(526.205, 214.748, 527.166, 213.15201, 526.75604, 211.518);
((GeneralPath)shape).curveTo(526.36707, 209.95601, 524.77405, 209.0, 523.20605, 209.39);
((GeneralPath)shape).curveTo(521.577, 209.795, 520.61804, 211.392, 521.00806, 212.953);
((GeneralPath)shape).curveTo(521.415, 214.585, 523.014, 215.544, 524.642, 215.139);
((GeneralPath)shape).lineTo(524.642, 215.139);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_27;
g.setTransform(defaultTransform__0_0_0_0_27);
g.setClip(clip__0_0_0_0_27);
float alpha__0_0_0_0_28 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_28 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_28 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_28 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(526.603, 223.64);
((GeneralPath)shape).curveTo(528.166, 223.25099, 529.13104, 221.653, 528.71906, 220.019);
((GeneralPath)shape).curveTo(528.33203, 218.458, 526.7361, 217.499, 525.17004, 217.886);
((GeneralPath)shape).curveTo(523.54205, 218.294, 522.58105, 219.894, 522.968, 221.452);
((GeneralPath)shape).curveTo(523.379, 223.087, 524.978, 224.046, 526.603, 223.64);
((GeneralPath)shape).lineTo(526.603, 223.64);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_28;
g.setTransform(defaultTransform__0_0_0_0_28);
g.setClip(clip__0_0_0_0_28);
float alpha__0_0_0_0_29 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_29 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_29 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_29 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(528.565, 232.137);
((GeneralPath)shape).curveTo(530.131, 231.743, 531.089, 230.14899, 530.68, 228.51399);
((GeneralPath)shape).curveTo(530.291, 226.95499, 528.694, 225.99399, 527.12897, 226.38599);
((GeneralPath)shape).curveTo(525.50397, 226.79099, 524.542, 228.38599, 524.93, 229.94899);
((GeneralPath)shape).curveTo(525.337, 231.584, 526.935, 232.541, 528.565, 232.137);
((GeneralPath)shape).lineTo(528.565, 232.137);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_29;
g.setTransform(defaultTransform__0_0_0_0_29);
g.setClip(clip__0_0_0_0_29);
float alpha__0_0_0_0_30 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_30 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_30 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_30 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(531.093, 240.504);
((GeneralPath)shape).curveTo(532.656, 240.11299, 533.617, 238.51599, 533.20905, 236.884);
((GeneralPath)shape).curveTo(532.817, 235.321, 531.22406, 234.363, 529.6611, 234.753);
((GeneralPath)shape).curveTo(528.03204, 235.158, 527.07104, 236.753, 527.4601, 238.317);
((GeneralPath)shape).curveTo(527.865, 239.948, 529.462, 240.909, 531.093, 240.504);
((GeneralPath)shape).lineTo(531.093, 240.504);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_30;
g.setTransform(defaultTransform__0_0_0_0_30);
g.setClip(clip__0_0_0_0_30);
float alpha__0_0_0_0_31 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_31 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_31 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_31 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(533.202, 248.968);
((GeneralPath)shape).curveTo(534.76404, 248.58101, 535.723, 246.983, 535.31305, 245.352);
((GeneralPath)shape).curveTo(534.92206, 243.791, 533.327, 242.83101, 531.762, 243.22101);
((GeneralPath)shape).curveTo(530.13605, 243.62701, 529.17303, 245.22601, 529.56604, 246.785);
((GeneralPath)shape).curveTo(529.972, 248.418, 531.568, 249.38, 533.202, 248.968);
((GeneralPath)shape).lineTo(533.202, 248.968);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_31;
g.setTransform(defaultTransform__0_0_0_0_31);
g.setClip(clip__0_0_0_0_31);
float alpha__0_0_0_0_32 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_32 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_32 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_32 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(535.303, 257.434);
((GeneralPath)shape).curveTo(536.868, 257.046, 537.826, 255.44899, 537.417, 253.818);
((GeneralPath)shape).curveTo(537.028, 252.25499, 535.43097, 251.297, 533.868, 251.687);
((GeneralPath)shape).curveTo(532.237, 252.08899, 531.27997, 253.691, 531.67, 255.249);
((GeneralPath)shape).curveTo(532.074, 256.884, 533.674, 257.842, 535.303, 257.434);
((GeneralPath)shape).lineTo(535.303, 257.434);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_32;
g.setTransform(defaultTransform__0_0_0_0_32);
g.setClip(clip__0_0_0_0_32);
float alpha__0_0_0_0_33 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_33 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_33 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_33 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(537.409, 265.9);
((GeneralPath)shape).curveTo(538.97, 265.512, 539.927, 263.913, 539.519, 262.283);
((GeneralPath)shape).curveTo(539.12897, 260.72, 537.534, 259.762, 535.972, 260.151);
((GeneralPath)shape).curveTo(534.33997, 260.557, 533.381, 262.157, 533.774, 263.714);
((GeneralPath)shape).curveTo(534.18, 265.349, 535.778, 266.308, 537.409, 265.9);
((GeneralPath)shape).lineTo(537.409, 265.9);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_33;
g.setTransform(defaultTransform__0_0_0_0_33);
g.setClip(clip__0_0_0_0_33);
float alpha__0_0_0_0_34 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_34 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_34 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_34 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(539.517, 274.366);
((GeneralPath)shape).curveTo(541.078, 273.97598, 542.03503, 272.38, 541.62805, 270.746);
((GeneralPath)shape).curveTo(541.23505, 269.184, 539.6401, 268.225, 538.08105, 268.615);
((GeneralPath)shape).curveTo(536.44507, 269.021, 535.4861, 270.62, 535.87805, 272.179);
((GeneralPath)shape).curveTo(536.285, 273.813, 537.882, 274.774, 539.517, 274.366);
((GeneralPath)shape).lineTo(539.517, 274.366);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_34;
g.setTransform(defaultTransform__0_0_0_0_34);
g.setClip(clip__0_0_0_0_34);
float alpha__0_0_0_0_35 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_35 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_35 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_35 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(541.617, 282.831);
((GeneralPath)shape).curveTo(543.18, 282.442, 544.138, 280.847, 543.729, 279.21298);
((GeneralPath)shape).curveTo(543.338, 277.65097, 541.745, 276.693, 540.18, 277.08298);
((GeneralPath)shape).curveTo(538.549, 277.48697, 537.588, 279.08698, 537.98, 280.64597);
((GeneralPath)shape).curveTo(538.39, 282.279, 539.987, 283.24, 541.617, 282.831);
((GeneralPath)shape).lineTo(541.617, 282.831);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_35;
g.setTransform(defaultTransform__0_0_0_0_35);
g.setClip(clip__0_0_0_0_35);
float alpha__0_0_0_0_36 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_36 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_36 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_36 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(543.721, 291.296);
((GeneralPath)shape).curveTo(545.284, 290.908, 546.242, 289.31, 545.834, 287.679);
((GeneralPath)shape).curveTo(545.444, 286.116, 543.84796, 285.158, 542.286, 285.54898);
((GeneralPath)shape).curveTo(540.656, 285.95297, 539.692, 287.55, 540.08704, 289.111);
((GeneralPath)shape).curveTo(540.493, 290.744, 542.091, 291.704, 543.721, 291.296);
((GeneralPath)shape).lineTo(543.721, 291.296);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_36;
g.setTransform(defaultTransform__0_0_0_0_36);
g.setClip(clip__0_0_0_0_36);
float alpha__0_0_0_0_37 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_37 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_37 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_37 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(545.683, 299.795);
((GeneralPath)shape).curveTo(547.246, 299.406, 548.204, 297.80902, 547.79596, 296.177);
((GeneralPath)shape).curveTo(547.40594, 294.614, 545.80994, 293.656, 544.248, 294.047);
((GeneralPath)shape).curveTo(542.618, 294.451, 541.65497, 296.049, 542.049, 297.609);
((GeneralPath)shape).curveTo(542.456, 299.242, 544.053, 300.202, 545.683, 299.795);
((GeneralPath)shape).lineTo(545.683, 299.795);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_37;
g.setTransform(defaultTransform__0_0_0_0_37);
g.setClip(clip__0_0_0_0_37);
float alpha__0_0_0_0_38 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_38 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_38 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_38 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(520.718, 198.141);
((GeneralPath)shape).curveTo(522.281, 197.753, 523.24, 196.15701, 522.83, 194.524);
((GeneralPath)shape).curveTo(522.44104, 192.962, 520.84705, 192.003, 519.284, 192.394);
((GeneralPath)shape).curveTo(517.652, 192.798, 516.692, 194.392, 517.083, 195.957);
((GeneralPath)shape).curveTo(517.49, 197.59, 519.087, 198.55, 520.718, 198.141);
((GeneralPath)shape).lineTo(520.718, 198.141);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_38;
g.setTransform(defaultTransform__0_0_0_0_38);
g.setClip(clip__0_0_0_0_38);
float alpha__0_0_0_0_39 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0_39 = g.getClip();
AffineTransform defaultTransform__0_0_0_0_39 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0_39 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(518.755, 189.643);
((GeneralPath)shape).curveTo(520.31903, 189.254, 521.279, 187.658, 520.869, 186.02501);
((GeneralPath)shape).curveTo(520.479, 184.46301, 518.88403, 183.50401, 517.32104, 183.895);
((GeneralPath)shape).curveTo(515.69006, 184.3, 514.73004, 185.89601, 515.12006, 187.459);
((GeneralPath)shape).curveTo(515.528, 189.091, 517.126, 190.051, 518.755, 189.643);
((GeneralPath)shape).lineTo(518.755, 189.643);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_0_39;
g.setTransform(defaultTransform__0_0_0_0_39);
g.setClip(clip__0_0_0_0_39);
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
        return 510;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 184;
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

